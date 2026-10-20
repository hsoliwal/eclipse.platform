# Leaf B1 coverage (JaCoCo 0.8.15)

Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
SPDX-License-Identifier: Apache-2.0 OR EPL-2.0

The runs are `tools/run_breakpoints.sh diff` and `tools/run_breakpoints.sh test`. Coverage is merged from:

- the recorded differential;
- `M3SortedLaneTest` and `BreakpointContainerLaneTest`, run once in default mode and once with
  `-Dorg.eclipse.debug.ui.m3.linearInsert=true`.

The report is written to `S:/m3-scratch/platform-views/b1/coverage-html` and `coverage.csv`.

| New code | Lines | Branches |
|---|---|---|
| `org.eclipse.debug.internal.ui.m3.M3SortedLane` (new class) | 246/246 (100%) | 191/192 (99.5%) |
| `BreakpointContainer`: `insertBreakpoint` body, `getChildrenCount`, `getChildrenRange`, `breakpointsChanged`, `getChildCount`/`getChildren` bodies | all covered | all covered |
| `ElementComparator.orderEpoch()` | not run headless | - |
| `BreakpointManagerContentProvider`: `getChildCount`/`getChildren` bodies, `InputData.breakpointsChanged` (two changed statements) | not run headless | - |

Uncovered, with reasons:

- **`M3SortedLane.tableRemove`, branch `++probes > mask`.** The membership table is never more than half full, so a
  probe always reaches an empty slot first. The guard only stops an endless loop if that invariant is ever broken. The
  other half of the condition (an empty slot reached, as when an element's hashCode changed) is covered by
  `changedHashCodeDoesNotLoseOrHangTheTable`.
- **`ElementComparator.orderEpoch()`.** It reads `DebugUIPlugin.getDefault().getPreferenceStore()`, and outside OSGi
  `getDefault()` cannot construct the plug-in (no bundle). The harness comparator overrides it.
- **The `BreakpointManagerContentProvider` changes.** They run only with a live `DebugPlugin` breakpoint manager, which
  needs a PDE JUnit run in a workbench. That run was not done; the existing `org.eclipse.debug.tests` breakpoint tests
  cover these methods there.
