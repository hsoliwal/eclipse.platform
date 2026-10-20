# Eclipse Platform views on one model, deltas end to end

Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
SPDX-License-Identifier: Apache-2.0 OR EPL-2.0

User, 2026-10-20: "all of eclipse implemented the same way", "all delta based view", "resize and layout change the
port", and "problems view is the example of how a tree view should [work] with all its api".

This track covers the views in hsoliwal/eclipse.platform:

- Debug: Variables, Expressions, Registers, Breakpoints, Debug, Modules, Memory, and both launch views.
- Console.
- Test runner (org.eclipse.unittest.ui).
- Team: Synchronize, History, and the compare structure viewers.
- Ant: the view and the editor outline.
- Help and UA trees.
- Terminal.
- The core.tools spy views.

The JFace agent (hsoliwal/eclipse.platform.ui, branch `m3/jface-viewport-20261019`) owns the viewers, the Problems view
(the reference tree view) and every platform.ui view. Search results and Project Explorer live there, not here.

## Rule (brief rule 24, memory "one model + delta processing")

- **One store.** A view keeps one lane model per widget stack. JFace element maps and model element trees are thin views
  over it, never second copies or per-element hash maps.
- **Deltas only.** On a model change a view applies only a delta: insert, remove, update or move runs, turned into an
  M3Patch.
- **No rebuild in a change handler.** A change handler never calls root `refresh()`, `setInput()`, or clear-and-re-add.
  This covers debug events, launch, breakpoint and expression listeners, team/sync deltas, console document events and
  resource deltas.
- **Resize and layout are viewport deltas.** Only rows entering or leaving the window are materialized or released.

## Shared parts (reused, never copied)

- **Viewer delta API**, from the JFace branch: `org.eclipse.jface.viewers.m3.M3ViewerDelta` (lane-backed op list:
  ADD/REMOVE/UPDATE/REFRESH) and `M3Viewers.apply/post/flush/refreshLater`.
  - `post` debounces with a superseding generation and applies at most 4 x delay after the first post.
  - Bundles import the package; nothing is copied.
- **Reference tree view and generic tree-view test.** The Problems view is the reference
  (`docs/m3/markers-view/REFERENCE_TREE_VIEW.md`, `TREE_VIEW_API_MATRIX.tsv`, `FALLBACKS_TO_DELTAS.md` on that branch).
  - Each tree view here reuses its parts and its generic test once they are published.
  - Until then this track does inventory, measurement and the delta gate (coordinator, 2026-10-20).

## Evidence in this directory

| File | What |
|---|---|
| `ECLIPSE_PLATFORM_VIEW_INVENTORY.tsv` | One row per view. Columns: viewer, provider, change source, update path, duplicate stores, full-rebuild sites, target delta path, `delta_gate`, upstream issues, what to measure. Every one of the 160 cited file:line references was checked to exist. |
| `DELTA_GATE.tsv` | Every rebuild call site in each view scope (342 sites), classified mechanically by `tools/delta_gate.py`, plus a hand review of the sites on change paths. |
| `tools/delta_gate.py`, `tools/scopes.tsv` | The classifier and the view scopes (stdlib Python, read-only, deterministic). |
| `DELTA_CENSUS_BASELINE.tsv`, `DELTA_CENSUS_FILES_BASELINE.tsv` | `C:/m3-superset/tools/delta_census.sh` before any change: 458 main / 148 test sites in this fork at 0708647bae. |

### How the gate classifies a site

`delta_gate.py` masks comments and literals and finds the chain of enclosing methods of each call. It then builds a
name-based call graph over the view scope and asks which callback reaches the chain:

- **MODEL:** a model-change handler.
- **INPUT:** a selection, debug-context or part switch.
- **PREF:** a preference or property change.
- **USER:** an action or widget event.
- **INIT:** part or control creation.

A `schedule()` call links to `run()` of Job subclasses in scope, and lambdas and anonymous runnables belong to their
enclosing method. Unqualified calls inside viewer subclasses count; declarations do not.

Name-based reachability over-approximates on purpose. The `review` column is the authority for the views this track
converts. A view passes the gate when every MODEL-reached site is gone or justified with file:line (user Refresh,
initial input, input switch). The differential must also count zero full refreshes during the event sequence.

## Inventory summary (step 1)

| View | delta_gate | Why |
|---|---|---|
| Variables, Expressions, Registers, Modules | FAIL | Every debug event fires CONTENT on the input (`VariablesViewEventHandler.java:52`, `ExpressionEventHandler.java:71`). `TreeModelContentProvider.handleContent` then calls `viewer.refresh(input)` (`TreeModelContentProvider.java:1377`), and expansion is saved and restored as ModelDelta trees around it. |
| Debug view | FAIL | Thread, target and launch CONTENT deltas cause subtree refreshes, and the default `refreshRoot` refreshes the launch manager (`DebugEventHandler.java:157`). |
| Breakpoints | PASS on the path | Adds, removes and changes are already ModelDelta ADDED/REMOVED/STATE. The cost is the second copy: `BreakpointContainer` keeps ArrayLists with linear `contains`, linear comparator scans and `add(index)`, so a bulk add is O(n^2) comparator calls. `breakpointsChanged` is O(n*m) (`BreakpointManagerContentProvider.java:537-545`). |
| Memory | FAIL | A memory CONTENT change refreshes the whole rendering table. |
| e4 Launch view | FAIL | Any configuration or launch change resets the view with `setInput` (`LaunchViewImpl.java:276`). |
| Test runner | FAIL | Every `testAdded` sets needs-refresh, and the next tick calls `setInput` (`TestViewer.java:560`). The source's own TODO says a parent refresh would do. |
| Synchronize (legacy pages) | FAIL | Adds and removes are already batched deltas. Resets rebuild everything (`AbstractSynchronizeModelProvider.java:381`), hierarchical additions rebuild a whole project subtree, and expansion is snapshotted and restored on every event. |
| Synchronize (model pages) | FAIL | `diffsChanged` refreshes the whole model subtree (`SynchronizationContentProvider.java:424,426`) or a whole project (`ResourceModelContentProvider.java:464`). |
| History (local) | FAIL | Any delta on the file, marker-only included, re-reads every state and calls `setInput` (`LocalHistoryPage.java:855`). |
| Compare structure viewers | FAIL | Every re-diff builds a new root and calls `refresh(root)` plus `expandToLevel(3)` (`StructureDiffViewer.java:534`). |
| Ant view and Ant outline | FAIL | A build-file change re-parses on the notification thread and calls a full `refresh()` (`AntView.java:161`). Every reconcile refreshes the outline (`AntEditorContentOutlinePage.java:368`). |
| Console | PASS on the path | Document events are already text deltas. Head trim at the high-water mark rewrites the offset of every partition (`IOConsolePartitioner.java:640-670`). |
| Help, Cheat sheets, Tips, Intro, Terminal | PASS | Their rebuilds are user actions, input switches or initial input. |
| core.tools spy views | FAIL | These are developer tools and low priority. |

## Fork state

- **Fork distance.** hsoliwal/eclipse.platform master is 25 commits ahead of and 15 behind eclipse-platform/master
  (90 files; measured with `gh api compare` on 2026-10-20). Upstream sync is the upstream-sync agent's job.
- **Branch.** This branch starts from fork master 0708647bae.
- **Upstream overlap.** Two of the missing upstream commits touch the Console ("Avoid NPE when ConsoleManager service is
  not available", "Fix lost wakeup ... ConsoleLineTracker"). A Console leaf merges upstream first.

## Next (steps 2-5)

1. **Measure before changing.** For each candidate below, measure time, allocations and retained heap per delta of 1,
   100 and 10^4 elements, headless where possible:
   - Breakpoints: 10^4 breakpoints, model level, headless.
   - Variables: a 10^5-element array.
   - Test runner: 10^4 to 10^5 tests announced.
   - Synchronize: 10^5 changes. This needs a workspace, so it is a PDE test.
   - Console: 10^6 lines.

   UI runs go only through `C:/m3-superset/tools/ui_lock.sh platform-views`. The three worst by the numbers are
   converted.
2. **Convert view by view.** Each view gets lanes instead of per-element stores, deltas through `M3ViewerDelta` or
   `IModelDelta`, a stock-vs-ours differential (rows, selection, expansion, labels and full-refresh count = 0, plus a
   resize sweep with counters), 99% coverage on new code, and an `OPTIMIZATION_LEDGER.tsv` row.
3. **List missing forks.** `MISSING_FORKS.md` records the repos "all of Eclipse" needs but the user has not forked:
   jdt.ui, pde, EGit.
