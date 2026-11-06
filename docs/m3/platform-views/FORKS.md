# Forks for "all of Eclipse"

Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
SPDX-License-Identifier: Apache-2.0 OR EPL-2.0

"All of Eclipse implemented the same way" needs three repositories outside eclipse.platform and eclipse.platform.ui. The
user approved forking them on 2026-10-20 ("YES"). This file replaces the planned MISSING_FORKS.md.

GitHub allows one fork per network. For PDE the existing fork hsoliwal/initiative31-eclipse.pde (same network as
eclipse-pde/eclipse.pde) is used, with eclipse-pde/eclipse.pde as the `upstream` remote.

| Repository | Fork | Distance to upstream master (2026-10-20) | Branch, worktree | Draft PR |
|---|---|---|---|---|
| eclipse-jdt/eclipse.jdt.ui | hsoliwal/eclipse.jdt.ui | identical | m3/views-viewport-20261020, S:/m3-scratch/worktrees/eclipse.jdt.ui | hsoliwal/eclipse.jdt.ui#1 |
| eclipse-egit/egit | hsoliwal/egit | identical | m3/views-viewport-20261020, S:/m3-scratch/worktrees/egit | hsoliwal/egit#1 |
| eclipse-pde/eclipse.pde | hsoliwal/initiative31-eclipse.pde | 7 ahead, 111 behind | m3/views-viewport-20261020, S:/m3-scratch/worktrees/eclipse.pde | hsoliwal/initiative31-eclipse.pde#3 |

## Views and step-1 evidence

Each fork has `docs/m3/views/`, containing `VIEW_INVENTORY.tsv`, `DELTA_GATE.tsv`, `DELTA_CENSUS_BASELINE.tsv` and
`README.md`. The census sites are counted by `delta_census.sh` (main code), which now includes the three repos. The
gate columns count rebuild sites reached from a model listener.

| Repository | Views | Census sites | Gate: FAIL / refresh(element) |
|---|---|---|---|
| jdt.ui | Package Explorer, Java Outline, Type Hierarchy, Call Hierarchy, Projects/Packages/Types/Members, Javadoc/Declaration | 341 | 16 / 10 |
| egit | Staging, History, Repositories, Synchronize, Reflog, Interactive Rebase, Compare tree | 188 | 34 / 6 |
| pde | Plug-ins, Target Platform State, Dependencies, Features, Registry, manifest editor sections, target editor sections | 658 | 100 / 10 |

## Worst full rebuilds found (hand-checked)

- **Java Outline.** `JavaOutlineViewer.reconcile(delta)` calls `refresh(true)` on every reconcile, after each typing
  pause. Its Javadoc says it "incrementally updates"
  (`org.eclipse.jdt.ui/ui/org/eclipse/jdt/internal/ui/javaeditor/JavaOutlinePage.java:395-402`).
- **Package Explorer.** Many Java element delta kinds become `postRefresh` of a parent, grandparent or project subtree
  (`PackageExplorerContentProvider.java:579-804`).
- **Type Hierarchy.** Every hierarchy change recomputes the hierarchy and refreshes all its viewers
  (`TypeHierarchyViewPart.java:273,781-783,1209-1231`).
- **EGit Staging.** Every index diff change calls `setInput` of a newly built tree, with a String-to-element HashMap
  built before and after to restore expansion (`staging/StagingView.java:752-754,2845-2884,2923`).
- **EGit Repositories.** Every refs, index or config event calls `refresh(true)` on the whole navigator, debounced
  (`repository/RepositoriesView.java:206-210,1105-1144`).
- **EGit History.** A refs change re-walks the history and resets the commit table (`history/GitHistoryPage.java`,
  `CommitGraphTable.java:540`).
- **PDE Plug-ins.** Any changed or removed plug-in refreshes the whole view. The source comment says "Don't know
  exactly what change - the safest way out is to refresh", although `PluginModelDelta` carries the changed entries
  (`views/plugins/PluginsView.java:937-957`).
- **PDE Target Platform State.** `stateResolved` refreshes the whole tree although `StateDelta` lists the changed
  bundles (`views/target/StateViewPage.java:260-305`).
- **PDE manifest editor sections.** A header property change refreshes the whole section viewer, for example
  `ExportPackageSection.java:459-505`.

Order of work: the eclipse.platform views come first, then these repos in the same way, using:

- the reference parts from the Problems view;
- delta-only change handlers;
- PartVisibility;
- resize and layout as deltas;
- a differential and a ledger.
