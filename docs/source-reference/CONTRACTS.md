# Eclipse Platform: build boundaries and selected lifecycle contracts

[Reference home](README.md) · [Structural coverage](COVERAGE.md)

## Scope and evidence

This guide describes the public `hsoliwal/eclipse.platform` snapshot `0de5d9acb5cff6b133cb1c55f6844720088c7d92`. The fork retains Eclipse Platform's runtime, resources, debug, team, Ant, user-assistance, terminal and update components. Its M3 introduction describes research directions; it is not evidence of an implemented M3 engine, performance improvement or runtime replacement. Upstream authorship, existing license/notice files and source-level terms remain authoritative. [Repository introduction][readme] · [License][license] · [Notice][notice]

The inventory accounts for all tracked Java declarations and metadata at this snapshot. The behavioral review below is deliberately narrower: jobs, compound scheduling rules and workspace batching. It follows actual implementation bodies and selected tests, rather than assigning semantics from package/type names. Internal implementation types are evidence, not recommendations for client dependencies.

**Build, tests, generated Javadoc, GUI/native behavior and cross-platform compatibility: NOT RUN.** Examples are source-reviewed, unexecuted usage sketches inside a properly configured Eclipse/OSGi application. This pass changes Markdown only.

## Repository and project responsibility map

The root POM declares 15 direct module entries. Some are concrete bundle directories; others aggregate subtrees. The `runtime`, `resources` and `terminal` POMs, for example, divide their contents into bundles, tests and features/examples. Metadata paths, Maven module declarations, OSGi bundles, Eclipse workspace projects and Java packages are overlapping identities, not interchangeable counts. [Root POM][root-pom] · [Runtime POM][runtime-pom] · [Resources POM][resources-pom] · [Terminal POM][terminal-pom]

| Area | Boundary and reader entry point |
| --- | --- |
| `runtime/` | Core runtime services, content types, expressions and job scheduling; bundles/features/tests are separate build inputs |
| `resources/` | Workspace resources and file-system abstraction, with examples and test suites; this is the owner of workspace operation semantics |
| `debug/` | Language-neutral launch/debug/breakpoint model and related UI/external-tool integration |
| `team/` | Team/provider integration, synchronization and comparison workflows; a framework for integrations rather than a claim that every VCS is included |
| `ant/` | Ant core, launching and UI integration, plus distinct core/UI test bundles |
| `ua/` | User assistance, including help-related components and associated tests/documentation |
| `terminal/` | Terminal bundles, installable features and tests; connector and display behavior depends on the selected implementation |
| `update/` | Update configurator bundle and its tests, not a substitute name for all p2 behavior |
| `platform/` | Platform/SDK branding and packaging inputs |
| `releng/`, `docs/` | Development setup and contributor/API documentation; presence here alone does not add a root Maven module |

The source map is grounded in the retained [project introduction][readme] and [root module list][root-pom]; the full [reference index](README.md) links every metadata-backed path and declared Java namespace. SWT's native widget implementation, Platform UI's workbench/JFace code and SDK-wide assembly belong to other repositories. An import of one of their packages is a dependency boundary, not source ownership here.

### Two concrete bundle/dependency boundaries

`org.eclipse.core.jobs` exports `org.eclipse.core.runtime.jobs`. Its `org.eclipse.core.internal.jobs` export is restricted by `x-friends`, so the internal classes used below are not general client API. The manifest declares JavaSE-17, lazy activation through `JobActivator`, a required `org.eclipse.equinox.common` bundle and OSGi/debug imports. Those are requirements/activation metadata, not evidence that startup succeeded. [Jobs manifest][jobs-manifest]

`org.eclipse.core.resources` exports the public resource, mapping, refresh, team, snapshot and variable-resolver namespaces alongside explicitly internal/friend-qualified exports. It declares JavaSE-17, lazy `ResourcesPlugin` activation, required runtime/expressions/filesystem bundles, optional Ant integration and JNA/OSGi-location imports. Its workspace implementation consumes the jobs service for scheduling rather than creating an unrelated synchronization model. [Resources manifest][resources-manifest] · [WorkManager][work-manager]

## Build configuration and verification contract

The checked-in root parent is `org.eclipse:eclipse-platform-parent:4.42.0-SNAPSHOT`, resolved relative to `../eclipse-platform-parent` when available. The `build-individual-bundles` profile provides the Eclipse repository for parent resolution; `.mvn/maven.config` enables that profile and eager target resolution. The build extension is `org.eclipse.tycho:tycho-build:5.0.2`. Maven/Tycho's effective model and target resolution were not executed. [Root POM][root-pom] · [Maven flags][maven-config] · [Extension][extensions]

From an appropriately provisioned checkout, the repository's entry command is:

```sh
mvn clean verify
```

This is a documented entry point, not an instruction executed for this PR. Parent resolution and p2/Maven artifacts require the configured repositories or a sufficient cache. Pomless Tycho projects explain why not every metadata-backed bundle/feature has an individual checked-in POM. A lexical list of `<modules>` does not fully evaluate those generated models.

The pinned Jenkins lane selects `temurin-jdk25-latest` and `apache-maven-latest`, runs under Xvnc and enables `bree-libs`, `api-check` and `javadoc`. Its command also enables `maven.test.failure.ignore=true`, disables skipping the baseline comparison and disables compiler fail-on-warning. The selected JDK 25 build lane is different from a bundle's declared JavaSE-17 execution environment; neither value alone proves repository-wide minimum compatibility. Historical tool versions in AGENTS prose should not override these concrete pinned configuration values. [Jenkinsfile][jenkins] · [Jobs manifest][jobs-manifest] · [Resources manifest][resources-manifest]

Before calling a later build successful, retain the exact SHA, commands, JDK/Maven/toolchain versions, OS/display setup, target resolution and these outputs:

- `**/target/surefire-reports/TEST-*.xml`: executed test outcomes and skips
- `**/target/apianalysis/*.xml`: API analysis results
- `**/target/compilelogs/*.xml`: compiler diagnostics
- Maven/Javadoc output and the selected job's exit/quality-gate state

The Jenkins configuration can continue despite test failures, and it allows empty test-result archives. A green command or an empty report collection therefore does not establish that every intended test ran and passed. Read the actual reports and configuration. Contributor/API-versioning rules remain in the existing [API hub][api-hub], [version numbering guide][versions] and [contribution guide][contributing].

## Job lifecycle: scheduling is cooperative, state observations are transient

The client API is `org.eclipse.core.runtime.jobs.Job`; it delegates state handling through `InternalJob` to `JobManager`. A new job is `NONE`, a scheduled job is `WAITING` or delayed `SLEEPING`, and an executing job is `RUNNING`. Completed jobs can be scheduled again. `getState()` is only a recent observation; another thread may change the state before the caller acts. Do not use it as a lock or a durable completion certificate. [Job][job] · [InternalJob][internal-job]

### Scheduling and mutable configuration

- Call `schedule()` or `schedule(delay)`, rather than invoking `run` directly. The latter is the framework callback.
- `InternalJob.schedule` first checks `shouldSchedule()`. `JobManager.scheduleInternal` rejects a negative delay and a shut-down manager, records an internal about-to-schedule state and queues the scheduling event before handing the job to the scheduler.
- Scheduling an already waiting/sleeping job does not add another queue entry. Scheduling while it is running records one later execution; a later call replaces the stored delay.
- Priorities are scheduling hints. The public API promises ordering for equal-priority/equal-delay jobs with conflicting rules, not a total ordering of all jobs.
- Set the rule, system/user flags and job group before scheduling. The manager rejects changing the rule unless the state is `NONE`; `InternalJob` similarly guards system/user flags and the job-group setter.

These details come from the complete `schedule`, `scheduleInternal`, `setRule`, `setSystem`, `setUser` and `setJobGroup` bodies and the public API contract. They do not establish fairness, a fixed worker count or any measured throughput. [Scheduling implementation][manager] · [Client lifecycle][job] · [Configuration guards][internal-job]

### Cancellation and completion

`cancel()` is a request, not forced thread termination. Waiting/sleeping jobs are removed from scheduling. For a running job, `JobManager.cancel` captures its monitor, records the first cancellation request under the state lock, then invokes monitor cancellation and the job's `canceling()` hook outside that lock. It returns false for the running case; the job must cooperate. `canceling()` is not called repeatedly for the same run simply because the client calls `cancel()` again. [Cancellation body][manager] · [Cancellation tests][job-tests]

The normal `run(IProgressMonitor)` path returns a non-null `IStatus`. `Job.create(String, ICoreRunnable)` wraps a runnable, maps its `CoreException` status into a new `Status` carrying the exception, and returns `Status.OK_STATUS` on normal return. The sibling `IJobFunction` overload returns the function's status directly. Neither factory should be described as swallowing arbitrary exceptions or proving work succeeded. [Factory bodies][job]

Asynchronous completion is an explicit different protocol: return the exact `Job.ASYNC_FINISH` sentinel, identify the execution thread with `setThread`, and later call `done(result)` outside the dynamic scope of `run`. `JobManager.endJob` compares the sentinel by identity and leaves the job running for asynchronous completion. For normal completion it records the result, clears monitor/thread references, changes state to `NONE` and may schedule the stored next run. A warning/error result outside a job group is logged. Missing the asynchronous `done` call can leave the operation outstanding. [Completion body][manager] · [Asynchronous API][job]

`JobTest.testAsynchJob` exercises thread handover, normal completion, reuse and cancellation; `testCanceling` checks that repeated cancellation invokes the hook once. These are inspected test bodies, not test results. `testDone` in the same class is explicitly disabled in this snapshot and must not be cited as a passing guarantee. [Tests][job-tests]

### Joining and ownership hazards

`join(timeout, monitor)` waits on a listener/semaphore, removes its listener in `finally`, checks monitor cancellation and returns false when its deadline expires. Zero timeout means unbounded waiting. Canceling the join monitor does not cancel the underlying job. The manager rejects self-join, negative timeout and unbounded joining within the same throttled job group. Under manager suspension, waiting/sleeping jobs are not awaited. These are special return/exception paths, so a returned join value must be interpreted with lifecycle context. [Join body][manager] · [Public join contract][job]

Holding an application lock needed by the joined job can deadlock. The inspected join body also uses the lock manager's wait/release hooks and treats interruption according to its blocking policy; this is not a general guarantee that every join on every thread immediately throws on interruption. Do not replace those relationships with a plain thread join, cache the state forever or claim arbitrary client state is made thread-safe by scheduling.

A source-reviewed example of the factory/cancellation protocol is:

```java
import org.eclipse.core.runtime.ICoreRunnable;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;

// rule is supplied by the component that owns the affected resource.
Job createScan(ISchedulingRule rule) {
    Job job = Job.create("Inspect resources", (ICoreRunnable) monitor -> {
        if (monitor.isCanceled()) {
            throw new OperationCanceledException();
        }
        // Perform a bounded unit of work and check cancellation between units.
        // Marshal any UI access through the owning UI framework's contract.
    });
    job.setRule(rule);
    job.schedule();
    return job;
}
```

The monitor belongs to the run callback; its public contract limits its validity to that invocation. The plug-in that schedules work remains responsible for its own shutdown/cancellation strategy and any resources its callback opens. The scheduler does not make a caller's I/O, UI access or cleanup correct automatically. [Run and shutdown contracts][job] · [Manager shutdown diagnostics][manager]

## Scheduling-rule composition: `MultiRule` owns an array, not child mutability

`ISchedulingRule.contains` describes permitted nesting and must obey the documented partial-order rules. `isConflicting` must be reflexive, symmetric and consistent, report conflicts for contained rules, and return false for unknown rules. These are obligations on custom implementations; the interface declaration is not a proof that every implementation satisfies them. Conflicting managed jobs must not run concurrently. [Rule interface][rule]

`MultiRule.combine(a,b)` returns an existing rule when they are identical, one is null or one contains the other; otherwise it creates a compound rule. The array overload skips null elements and can return null when none remains. Do not assume the result is always a `MultiRule` or always a new allocation. [Complete MultiRule implementation][multi-rule]

The constructor flattens child `MultiRule` instances into a new array; `getChildren()` returns a clone. Thus callers cannot replace the stored array elements through that return value. The children themselves are still shared rule objects, not deep-copied immutable values. The constructor does not filter null entries in its input, unlike `combine(array)`; do not rely on null children being safe for later containment/conflict traversal.

For another compound rule, `contains` requires every incoming child to be covered by some local child. `isConflicting` returns true as soon as one relevant child pair conflicts. For a simple rule, both operations delegate to children. The implementation supplies no independent locking for mutation inside custom child rules. [Implementation][multi-rule]

`MultiRuleTest.testCombine`, `testContains` and `testIsConflicting` inspect reuse, null combination, nested containment and symmetric conflict examples using `PathRule`. They have not been executed here. A correct resource-specific rule remains the caller's obligation. [Compound-rule tests][multi-rule-tests]

## Workspace batches: notification boundaries are not rollback transactions

`IWorkspace.run(ICoreRunnable, ISchedulingRule, int, IProgressMonitor)` is the public batch boundary. Its action executes in the caller's invocation; `Workspace.run` does not schedule a separate job. The supplied rule controls conflicts with other workspace changes. Work outside that rule can fail. A null rule means no scheduling restriction from that parameter, not unrestricted correctness for arbitrary changes. The overload without a rule uses the workspace root and `AVOID_UPDATE`. [Public contract][workspace-api] · [Implementation][workspace]

`AVOID_UPDATE` defers notifications until the end when no other thread modifies the workspace concurrently. Without it, periodic notifications are permitted. Other option bits are ignored by this method's documented contract. This conditional notification guarantee matters: it is not a promise that arbitrary concurrent operations produce one universal event. [IWorkspace.run][workspace-api]

The inspected implementation performs these steps:

1. Convert the monitor and prepare the operation. `prepareOperation` interrupts conflicting autobuild work, checks in through the work manager and rejects a closed workspace.
2. Begin the workspace tree operation and, when requested, enter the notification-avoidance scope.
3. Release the internal workspace lock depth around `action.run`, while retaining the scheduling-rule relationship established at check-in. `beginUnprotected` is not the same thing as ending the rule.
4. On `OperationCanceledException`, or `CoreException` with CANCEL severity, mark the operation canceled and propagate the exception.
5. In `finally`, finish the monitor, end notification avoidance, restore the old lock depth and end the operation.

`WorkManager.checkIn` rejects workspace-tree-locked modification, acquires the job scheduling rule and workspace lock, and tracks failed check-in per thread. `checkOut` balances operation state, releases the workspace lock and ends the rule in a finally block. These are internal lifecycle requirements, not public entry points for clients. [WorkManager bodies][work-manager] · [Workspace.run][workspace]

At the outer end-operation boundary, `Workspace.endOperation` compares trees when required, broadcasts post-change notifications, requests a snapshot, restores tree immutability and checks out in finally. It delegates top-level build completion after that. The selected wrapper has no compensating-action/rollback mechanism that restores all resource changes after `action.run` throws. Therefore do not read the word “atomic” as a database-style all-or-nothing rollback promise. Resource-operation-specific failure/partial-effect contracts still apply. [End-operation body][workspace]

### Source-backed operation example

The inspected `IFileTest.testWriteRule` creates a file under `createRule`, then replaces existing content under `modifyRule`, wrapping each action in `workspace.run(..., AVOID_UPDATE, ...)`. It asserts one notification for each isolated operation. The test establishes intended coverage; it was not run during this pass. [Test body][file-tests]

A corresponding usage sketch, assuming an existing open project and an existing file, is:

```java
import java.nio.charset.StandardCharsets;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ICoreRunnable;
import org.eclipse.core.runtime.IProgressMonitor;

void replaceExisting(IWorkspace workspace, IFile file, IProgressMonitor monitor)
        throws CoreException {
    ICoreRunnable action = child -> file.write(
        "replacement".getBytes(StandardCharsets.UTF_8), false, false, false, child);
    workspace.run(action, workspace.getRuleFactory().modifyRule(file),
        IWorkspace.AVOID_UPDATE, monitor);
}
```

Creation requires the appropriate creation rule and resource preconditions, as in the test. This sketch is not permission to overwrite user files, a transaction wrapper or a substitute for checking individual `IFile` flags/error contracts. It demonstrates rule ownership and the batch boundary only.

## Verification gaps and next review boundaries

- Other Platform packages, launch/debug/team/help/terminal lifecycles and file-system/native operations remain indexed but not fully semantically reviewed in this guide
- Tests cited here were read; their execution, skips, timing and platform coverage remain unknown
- Effective Tycho models, inherited dependencies, OSGi services/extensions and generated outputs still need an executed configuration/build review
- Shutdown, nested-rule composition, UI-thread interaction, cancellation and partial resource failure deserve targeted regression coverage before changing these implementations
- M3-style reuse or scheduling changes must preserve these observable protocols and produce independent evidence; a matching signature or source inventory is insufficient

The [coverage ledger](COVERAGE.md) intentionally leaves full package/API semantics, native package-documentation reconciliation and runtime qualification open.

[readme]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/README.md

[license]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/LICENSE

[notice]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/NOTICE

[root-pom]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/pom.xml

[runtime-pom]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/pom.xml

[resources-pom]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/pom.xml

[terminal-pom]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/pom.xml

[jobs-manifest]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.jobs/META-INF/MANIFEST.MF

[resources-manifest]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.resources/META-INF/MANIFEST.MF

[maven-config]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/.mvn/maven.config

[extensions]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/.mvn/extensions.xml

[jenkins]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/Jenkinsfile

[api-hub]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/docs/API_Central.md

[versions]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/docs/VersionNumbering.md

[contributing]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/CONTRIBUTING.md

[job]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.jobs/src/org/eclipse/core/runtime/jobs/Job.java

[internal-job]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.jobs/src/org/eclipse/core/internal/jobs/InternalJob.java

[manager]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.jobs/src/org/eclipse/core/internal/jobs/JobManager.java

[job-tests]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.runtime/src/org/eclipse/core/tests/runtime/jobs/JobTest.java

[rule]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.jobs/src/org/eclipse/core/runtime/jobs/ISchedulingRule.java

[multi-rule]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.jobs/src/org/eclipse/core/runtime/jobs/MultiRule.java

[multi-rule-tests]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.runtime/src/org/eclipse/core/tests/runtime/jobs/MultiRuleTest.java

[workspace-api]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.resources/src/org/eclipse/core/resources/IWorkspace.java

[workspace]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.resources/src/org/eclipse/core/internal/resources/Workspace.java

[work-manager]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.resources/src/org/eclipse/core/internal/resources/WorkManager.java

[file-tests]: https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources/src/org/eclipse/core/tests/resources/IFileTest.java
