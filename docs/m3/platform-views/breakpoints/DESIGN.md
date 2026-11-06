# Breakpoints view on lanes (leaf B1)

Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
SPDX-License-Identifier: Apache-2.0 OR EPL-2.0

## What the view did

The Breakpoints view already receives deltas. The breakpoint manager reports added, removed and changed breakpoints,
and `BreakpointManagerContentProvider` turns each one into a `ModelDelta` (ADDED, REMOVED or STATE). The cost sat in
the second copy of the model, `BreakpointContainer`. It holds one `ArrayList<IBreakpoint>` per container (root and
categories), and every operation on it was linear:

| Operation | Stock cost | Where |
|---|---|---|
| contains (every add, remove, change, filter) | O(n) `equals` scan | `BreakpointContainer.java` `fBreakpoints.contains` |
| insert in comparator order | O(n) comparator calls (backward scan); each call reads the preference store, both markers and both labels | `insertBreakpoint`, `ElementComparator.doCompare` |
| bulk add of n breakpoints | O(n^2) comparator calls | `breakpointsAdded`, `createRootContainer` (organizer/sort/filter switches) |
| child count for the viewer | O(n) array copy just to read `.length` | `getChildCount` |
| one page of children for the virtual tree | O(n) array copy per page | `getChildren(parent, index, length, ...)` |
| a batch of m changed breakpoints | O(n*m): `Arrays.asList(filtered).contains` per breakpoint | `InputData.breakpointsChanged` |

## What it does now

There is still one store per container, and it is still a `List<IBreakpoint>`, so the rest of the class is unchanged.
It is now an `M3SortedLane` (`org.eclipse.debug.internal.ui.m3`), made of lanes:

- **elements:** an `Object[]`;
- **membership table:** an equals-hash open-addressing table of the same references, capacity a power of two, at most
  half full, linear probing, backward-shift deletion, and no entry objects;
- **inversion bits:** one byte per adjacent pair, set when the pair is inverted under the tracked comparator, plus a
  running count.

| Operation | Now |
|---|---|
| contains | O(1) expected |
| insert | Same position as the stock backward scan. While no adjacent pair is inverted, the position is the lower bound by binary search, O(log n) comparator calls. Otherwise the stock scan runs. |
| child count | O(1) (`getChildrenCount`) |
| page of children | O(window) copy (`getChildrenRange`), with the same clipping as `ElementContentProvider.getElements` |
| changed breakpoints | O(1) membership per breakpoint. `breakpointsChanged` re-checks the order before the batch is applied, because labels or lines, and so sort keys, may have moved. Up to 32 changed breakpoints are re-checked in place (two comparisons each). A larger batch marks the order unknown in O(m), and the next insert recomputes it once with n-1 comparisons, about the cost of one stock scan. |

### Why the insert position is identical

The stock scan puts the new breakpoint after the last element that compares less, scanning from the end. When no
adjacent pair is inverted and the comparator is a consistent order, "compares less than the new element" is true for
a prefix of the list and false after it. The scan therefore stops at the end of that prefix, which is exactly the lower
bound.

The inversion bits are kept exact on every mutation:

- **insert:** two comparisons;
- **remove:** none when both neighbouring pairs were in order (transitivity), otherwise one;
- **removeAll/addAll:** the same rules applied run by run;
- **a key change** reported by `breakpointsChanged`: two comparisons per occurrence for up to 32 changes, or "unknown" for a larger batch;
- **a change of the comparator's behaviour:** a new order epoch, here the sorting-order preference, recomputes all the
  bits once.

Whenever the bits report an inversion, the lane uses the stock scan itself.

Assumption: sort keys change only through breakpoint change events, a sort or organizer switch (which rebuilds the
containers), or the sorting-order preference (the epoch). The kill switch `-Dorg.eclipse.debug.ui.m3.linearInsert=true`
forces the stock scan everywhere.

## Changes to upstream files

All changes are made by recipe `com.synexia.openrewrite.platformviews.BreakpointsViewLanes` in
`docs/m3/platform-views/recipe/`. The second pass reports UNCHANGED.

- `BreakpointContainer`:
  - the field type becomes `M3SortedLane<IBreakpoint>` (`M3ReplaceFieldDeclaration`, the recipe in this track);
  - `insertBreakpoint` becomes one `insertSorted` call;
  - new members `getChildrenCount`, `getChildrenRange` and `breakpointsChanged`;
  - `getChildCount` and `getChildren(5)` delegate to the new members.
- `ElementComparator`: new member `orderEpoch()`.
- `BreakpointManagerContentProvider`:
  - `getChildCount` and `getChildren(5)` ask the container for a count and a page;
  - `InputData.breakpointsChanged` re-checks the order of the changed breakpoints first and tests membership against
    a lane.
- The debug.ui `MANIFEST.MF` exports `org.eclipse.debug.internal.ui.m3` to `org.eclipse.debug.tests`.

## Proof

- **Recorded differential**, stock `org.eclipse.debug.ui` 3.22.0 jar vs the lanes in front of it (`tools/run_breakpoints.sh diff`).
  - Script: 40 seeds, flat and grouped by file, 400 steps each.
  - Operations: add, remove and change batches; sort switches with and without the view's rebuild; drag and drop moves;
    clearing a category; child counts and pages; membership and index answers.
  - Each step records the delta, the container tree and the answers.
  - Result: 390,850 trace lines, 0 mismatching. The script requests no full rebuild beyond the scripted sort rebuilds,
    and the counter matches on both sides.
- **Unit tests:** `M3SortedLaneTest` checks every `List` answer and the stock scan position against an `ArrayList`
  oracle over 300 seeded scripts, with duplicates, nulls, key changes and order flips. `BreakpointContainerLaneTest`
  covers a container without a comparator. Result: 7/7 pass, with and without the kill switch.
- **Coverage:** see `COVERAGE.md`.
- **Numbers:** see `../OPTIMIZATION_LEDGER.tsv`, measured by `tools/run_breakpoints.sh bench`.

## Not covered headless

The provider methods run only with a live `DebugPlugin` breakpoint manager, which needs a PDE JUnit run in a workbench:

- `BreakpointManagerContentProvider.getChildCount` and `getChildren`;
- `InputData.breakpointsChanged`;
- `ElementComparator.orderEpoch()`, which reads the Debug UI preference store. The harness comparator overrides it.

These provider changes are covered by the existing `org.eclipse.debug.tests` breakpoint tests in a workbench run, which
was not run here.
