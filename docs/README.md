# Eclipse Platform: source exploration and contributor quick reference

This directory collects the Platform's API and contributor documentation. Start here
when you want to locate an implementation, understand its contract, and choose a
bounded validation task in this downstream fork. The [project README](../README.md)
retains the upstream introduction and explains the fork's experimental direction.
That direction is not a compatibility or performance result.

## Choose your layer

| Question | Start in | Read alongside the code |
| --- | --- | --- |
| Who owns workspace files and resource changes? | [resources](../resources) | [API Central](API_Central.md) |
| How are jobs scheduled and cancelled? | [runtime](../runtime) | [Progress Reporting](Progress_Reporting.md) |
| How are launches and breakpoints represented? | [debug](../debug) | [API evolution](Evolving-Java-based-APIs.md) |
| How do team providers integrate comparison and synchronization? | [team](../team) | [Version numbering](VersionNumbering.md) |
| Where are help and cheatsheets implemented? | [ua](../ua) | [Documentation style](Eclipse_Doc_Style_Guide.md) |
| Where do Ant and terminals fit? | [ant](../ant), [terminal](../terminal) | [Official FAQs](FAQ/The_Official_Eclipse_FAQs.md) |

These are OSGi/Eclipse bundles, not one stand-alone Java application. The root
[pom.xml](../pom.xml) aggregates the areas. A bundle's MANIFEST.MF describes its
runtime identity and dependencies; build.properties describes packaged source and
resources. Some bundles are pomless: absence of a local pom.xml does not mean that
Tycho cannot build them.

## Prerequisites and build boundaries

Use a JDK 25 development environment matching the current [Jenkinsfile](../Jenkinsfile).
Some older contributor notes mention JDK 21; do not infer the current build JDK
from an individual bundle's execution environment. Maven 3.9.12 is the documented
baseline in [agent contributor guidance](../AGENTS.md). Check `mvn -version` to
confirm which Java Maven actually uses.

The [Tycho extension](../.mvn/extensions.xml) currently selects 5.0.2.
[Default Maven options](../.mvn/maven.config) enable
`build-individual-bundles` and eager target resolution. The root POM requests
`eclipse-platform-parent:4.42.0-SNAPSHOT`; it searches the Eclipse repository when
the sibling parent is absent. Network access to Maven/p2 repositories, an available
matching snapshot parent, and a graphical display for UI tests are separate
prerequisites. This repository alone is not the complete Eclipse SDK reactor.

## Command cheat sheet

Run these from the repository root unless noted. They are source-checked examples,
not commands executed or tests passed as part of this documentation change.

```sh
java -version
mvn -version
git status --short
# A bounded production bundle build; this is not a test run.
mvn compile -pl :org.eclipse.core.jobs -Pbuild-individual-bundles
# Include the changed implementation and its runtime tests in one reactor.
mvn verify -pl :org.eclipse.core.jobs,:org.eclipse.core.tests.runtime -Pbuild-individual-bundles
# Broader validation after resolving toolchains, display and target dependencies.
mvn clean verify -Pbree-libs -Papi-check -Pjavadoc
```

Use `verify` for Tycho integration tests rather than stopping at `test`.
Do not copy CI's `maven.test.failure.ignore=true` into a command whose exit code
you intend to use as a test gate. Inspect `target/surefire-reports`,
`target/compilelogs` and `target/apianalysis` under participating modules.
A missing report is not a passing suite.

## Worked exploration: job state and cancellation

1. Open [Job.java](../runtime/bundles/org.eclipse.core.jobs/src/org/eclipse/core/runtime/jobs/Job.java).
   Its contract describes NONE, WAITING, RUNNING and SLEEPING, and explicitly says
   that priority is a scheduling hint rather than strict ordering.
2. Follow `schedule`, `cancel`, `join` and `setRule` into the internal jobs
   implementation using your IDE's declaration/call hierarchy navigation. Keep the
   public contract distinct from implementation details.
3. Open [AllJobTests](../runtime/tests/org.eclipse.core.tests.runtime/src/org/eclipse/core/tests/runtime/jobs/AllJobTests.java).
   It names focused tests for rules, queueing, events, groups and deadlocks. Choose
   the test class whose contract matches your change rather than treating a timing
   benchmark as a substitute for scheduling tests.
4. Before proposing reuse or caching, list what must remain unchanged: rule
   exclusion, cancellation, job completion, listener behavior and progress.
   Reproduce both a normal completion and an interrupted/cancelled path.
5. Record the exact selected tests, reports, JDK, parent/target versions and source
   revision when you run validation. A compile-only result cannot establish
   cancellation correctness; runtime tests cannot alone establish UI responsiveness.

For changes to public APIs, use [API Central](API_Central.md),
[API evolution](Evolving-Java-based-APIs.md), [coding conventions](Coding_Conventions.md)
and [version numbering](VersionNumbering.md). Preserve existing licenses, notices
and upstream attribution. Send fork-specific findings to this fork and follow
[CONTRIBUTING](../CONTRIBUTING.md) for upstream contributions.

## Debugging exercise: a file changes but a resource listener misses it

Start with a disposable test workspace, a project and one file. The goal is to
distinguish filesystem visibility, workspace notification and consumer behavior,
rather than fixing all three by repeatedly refreshing the entire workspace.

1. Read [IWorkspace](../resources/bundles/org.eclipse.core.resources/src/org/eclipse/core/resources/IWorkspace.java)
   and register the listener with an explicit event mask. Its one-argument
   registration includes POST_CHANGE, PRE_CLOSE and PRE_DELETE, not every event.
   Remove the listener when its owner is disposed.
2. Inspect the event type before reading its payload.
   [IResourceChangeEvent](../resources/bundles/org.eclipse.core.resources/src/org/eclipse/core/resources/IResourceChangeEvent.java)
   distinguishes a workspace-rooted delta for POST_CHANGE from the project handle
   supplied for PRE_CLOSE/PRE_DELETE. Do not assume every event contains a delta.
3. For an observed delta, separate `getKind()` from `getFlags()`.
   [IResourceDelta](../resources/bundles/org.eclipse.core.resources/src/org/eclipse/core/resources/IResourceDelta.java)
   uses ADDED/REMOVED/CHANGED for the kind, and CONTENT, MOVED_FROM, MOVED_TO and
   MARKERS as flags. A marker-only change is not a content edit. Follow move paths
   before interpreting a remove/add pair as unrelated resources.
4. Compare editing through the workspace API with editing the underlying file
   outside Eclipse and then refreshing it. Capture which event, path, kind and flags
   arrive in each case. Wait using the existing test helpers, not arbitrary sleeps.
5. Use [IResourceChangeListenerTest](../resources/tests/org.eclipse.core.tests.resources/src/org/eclipse/core/tests/resources/IResourceChangeListenerTest.java)
   as the fixture and assertion reference. It includes delta verification plus
   build/refresh synchronization helpers. Use its disposable test resources;
   do not run workspace-deleting fixture code against a real development workspace.
6. If a listener needs to perform a subsequent resource change, respect the event's
   mutation contract: POST_CHANGE notification closes the workspace for changes.
   Arrange follow-up work after notification, with an appropriate scheduling rule,
   rather than mutating the workspace inside that callback.

A candidate targeted command, unexecuted here, is:

```sh
mvn verify -pl :org.eclipse.core.resources,:org.eclipse.core.tests.resources -Pbuild-individual-bundles -Dtest=IResourceChangeListenerTest
```

| Symptom | First distinction to check | Useful evidence |
| --- | --- | --- |
| No callback | Registration lifetime and event mask versus whether the workspace saw the external edit | Mask, refresh completion, listener registration/removal |
| Callback but no content edit | Delta kind versus CONTENT/MARKERS flags | Event type, resource path, kind and flags |
| Change rejected during callback | Notification-time mutation restrictions versus scheduling-rule coverage | Event type and exception stack |
| Duplicate processing | One logical change may appear in multiple notifications; listener work must tolerate this | Ordered event trace and consumer state |
| Maven stops before tests | Parent/target resolution versus an assertion failure | First resolution error and presence/absence of test reports |

Do not repair missing notifications by editing workspace metadata directly.
`IWorkspace` reserves that area for Platform/API access. API annotations such as
`@noimplement` and `@noextend` also matter: consuming these interfaces does not
authorize replacing their implementations.
