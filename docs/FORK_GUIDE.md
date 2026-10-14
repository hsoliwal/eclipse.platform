# Exploring this Eclipse Platform fork

A practical starting point for readers who want to understand workspace resources,
background jobs, launch/debug infrastructure, or team integration without getting
lost in the whole Eclipse SDK.

[Command cheat sheet](COMMAND_CHEATSHEET.md) · [Repository README](../README.md) ·
[API guidelines](API_Central.md) · [Official Eclipse FAQs](FAQ/The_Official_Eclipse_FAQs.md)

## Choosing your route

| You want to… | Start here |
| --- | --- |
| Understand what the repository contains | [Source map](#finding-the-source) |
| Build and test a component | [Component quick start](#building-a-component) |
| Assemble a complete Eclipse SDK | Use the [SDK aggregator](https://github.com/hsoliwal/eclipse.platform.releng.aggregator), a separate repository |
| Investigate M3-related work | [Fork scope](#understanding-the-fork) and the existing `.m3` documentation |
| Report a reproducible problem | [Contribution checklist](#contributing-a-useful-result) |

## Understanding the fork

Eclipse Platform supplies shared services used by the Eclipse IDE and Eclipse-based
applications. A **bundle** is an OSGi component; a **feature** groups installable
components; a **p2 repository** supplies installable Eclipse artifacts; the
**reactor** is the collection of Maven/Tycho projects participating in one build.

This repository is `hsoliwal/eclipse.platform`, forked from
[`eclipse-platform/eclipse.platform`](https://github.com/eclipse-platform/eclipse.platform).
The underlying Platform implementation remains upstream Eclipse work.

The public downstream additions inspected for this guide include:

- M3 programme framing in the README and [convergence notes](../SYNEXIA_CONVERGENCE_MODEL.md)
- [Atom/pattern analysis configuration](../.m3/atom-pattern/README.md)
- A separate [content-bound analysis runner](../.m3/atomize-patternize/README.md)
- Fork-specific [rights and provenance notes](../M3-SYNEXIA-RIGHTS.md)

These are entry points for investigation. Their presence does not establish a
faster Platform, a replaced collection implementation, successful native builds,
or upstream endorsement. The native Maven/Tycho build and regression tests remain
separate from analysis receipts. External recipe artifacts described under `.m3`
are prerequisites for those optional lanes; they are not part of this public
checkout's ordinary component quick start.

For a fresh view of downstream changes, compare the fork with its merge base,
not just two moving README pages. See the commands in the cheat sheet. Current
upstream and fork tips can diverge in both directions.

## Finding the source

| Area | What to inspect | Useful question |
| --- | --- | --- |
| [resources](../resources) | `bundles/`, `examples/`, `tests/` | How do workspace changes become resource deltas? |
| [runtime](../runtime) | Core runtime, jobs, expressions and tests | Which scheduling rule protects this operation? |
| [debug](../debug) | Launch configurations, breakpoints, debug models | Is this a launch configuration issue or a debug-model issue? |
| [team](../team) | Synchronization and compare/merge integration | Which contract owns a resource difference? |
| [ua](../ua) | Help, cheatsheets and user assistance | Where does the help content or behavior live? |
| [ant](../ant) | Ant integration and tests | Does the failure require Eclipse's Ant launch environment? |
| [terminal](../terminal) | Terminal view and connectors | Is the issue in the widget, connector or remote endpoint? |
| [platform](../platform) | Platform/SDK branding and packaging inputs | Is this a product asset rather than a service implementation? |

Start at [pom.xml](../pom.xml) for reactor membership. Some bundle directories
are **pomless**: Tycho's extension derives their Maven model from Eclipse metadata.
A missing per-bundle `pom.xml` is not by itself a missing module. Inspect
`META-INF/MANIFEST.MF`, `build.properties`, and the containing reactor instead.

SWT and Platform UI have their own repositories. Building this component repository
is not equivalent to building every Eclipse component or assembling a complete SDK.

## Preparing the tools

For the inspected revision, use **JDK 25** as the local starting point: the
[Jenkinsfile](../Jenkinsfile) selects `temurin-jdk25-latest`. Use **Maven 3.9.12 or
newer** to align with the matching 4.42 aggregator's documented build requirements;
its inspected reusable workflow selects Maven 3.9.16. These are the guide's
recommended baseline, not a claim that every component has that Java execution
environment minimum.

The checked-in [extension](../.mvn/extensions.xml) is Tycho **5.0.2**, and the
[root POM](../pom.xml) requests `eclipse-platform-parent:4.42.0-SNAPSHOT`.
Older JDK 21 guidance in `AGENTS.md` does not describe the current Jenkins tool
selection. Always recheck the files for the revision you build.

You also need:

- Git and network access to the configured Maven and Eclipse/p2 repositories
- A working display/native UI environment for suites that use SWT or UI harnesses
- Adequate disk and memory for target-platform downloads and generated outputs
- For CI-like `-Pbree-libs` builds, matching JDK toolchains as described in the
  [upstream build guide](https://github.com/eclipse-platform/eclipse.platform.releng.aggregator/wiki/Platform-Build#using-bree-libs)

Check which Java Maven actually uses, not only which `java` is first on `PATH`:

```sh
java -version
mvn -version
```

## Building a component

Run these commands in a POSIX shell. The Maven commands also work in other shells
when their argument syntax is preserved.

```sh
git clone https://github.com/hsoliwal/eclipse.platform.git
cd eclipse.platform
git rev-parse HEAD
git status --short
mvn -version

# Build production resources and its regression-test bundle together.
mvn clean verify -Pbuild-individual-bundles \
  -pl :org.eclipse.core.resources,:org.eclipse.core.tests.resources
```

The [default Maven configuration](../.mvn/maven.config) already enables
`build-individual-bundles`; it is explicit above to make the dependency-resolution
assumption visible. The root profile makes the Eclipse-hosted parent-POM repository
available. Parent snapshot or p2 resolution still requires working network access
and available artifacts; the profile does not guarantee an offline build.

The selected [resource test bundle](../resources/tests/org.eclipse.core.tests.resources/pom.xml)
uses `AutomatedResourceTests`, requests filesystem test-feature dependencies, and
sets `useUIHarness` and `useUIThread` to false. That is a specific test-bundle
configuration, not a promise that every Platform test is headless.

If you changed a different production bundle, select that bundle and its tests
or run the component reactor from the root:

```sh
mvn clean verify
```

A tests-only selection can resolve the production bundle from the target platform
instead of compiling your edit. Review the reactor summary before interpreting a
passing test. `-DskipTests` is useful for compilation/package checks but provides
no regression-test evidence.

## Following a real resource problem

Suppose refreshing a project produces an unexpected resource-change notification.
Use this source-reading exercise before changing implementation code:

```sh
# Locate the public contract and its consumers.
git grep -n 'interface IResourceChangeListener' -- resources
git grep -n 'addResourceChangeListener' -- resources/tests

# Find the implementation and regression-test entry point.
git grep -n 'class Workspace' -- resources/bundles
git grep -n 'AutomatedResourceTests' -- resources/tests
```

1. Read the API contract and the relevant regression tests.
2. Reduce the report to a small workspace operation and expected delta.
3. Keep test workspace paths disposable; avoid a workspace containing valuable projects.
4. Select production and tests together using the quick-start command.
5. Inspect `target/surefire-reports` under the participating test bundle and record
   actual test counts, failures and skips.

For jobs or cancellation behavior, apply the same pattern under `runtime/` and
read [Progress Reporting](Progress_Reporting.md) and
[Status Handling Best Practices](Status_Handling_Best_Practices.md). The useful
contribution is a reproducible contract or counterexample, not an unmeasured
claim that a proposed optimization helps every workload.

## Troubleshooting the first build

| Symptom | Check first | Avoid |
| --- | --- | --- |
| Non-resolvable parent POM | `4.42.0-SNAPSHOT`, active individual-bundle profile, repository connectivity and Maven settings | Replacing the parent with an unrelated release just to silence resolution |
| Unsupported Java version or class-file error | `mvn -version`, `JAVA_HOME`, current extension/parent and requested toolchain | Assuming the old JDK note is the current build launcher |
| No display / SWT initialization failure | Whether this suite uses UI, OS libraries, display session; on Linux consider an installed Xvfb | Treating skipped UI tests as a pass |
| Tests pass but changed code is not exercised | Reactor contains production and test bundles; inspect resolved dependencies and baseline-replacement warnings | Running only the test bundle and assuming local source was included |
| API-baseline warning | `target/apianalysis`, [version rules](VersionNumbering.md) and [API evolution](Evolving-Java-based-APIs.md) | Disabling checks instead of understanding a compatibility change |
| Download or cached-artifact failure | Exact failing coordinate/URL, snapshot availability and network/proxy settings | Deleting the entire Maven cache as the first action |

Keep the first relevant error and Maven command. Later errors can be consequences
of the first failed module. Reproduce narrowly before increasing concurrency.

## Contributing a useful result

For fork-specific analysis or documentation, use this fork's
[issues](https://github.com/hsoliwal/eclipse.platform/issues) and
[pull requests](https://github.com/hsoliwal/eclipse.platform/pulls). For upstream
Eclipse behavior, read [CONTRIBUTING.md](../CONTRIBUTING.md) and the linked upstream
rules, including contributor-agreement and attribution requirements.

Include: exact commit, OS/architecture, `mvn -version`, test selector, reproduction,
expected/actual result, and relevant reports. A performance report also needs a
baseline, workload, warm-up/measurement method and regressions. Preserve upstream
copyright, [LICENSE](../LICENSE), [NOTICE](../NOTICE), and per-file notices.

## Evidence and maintenance

Source reviewed on **2026-10-14** at
[`13ed21538032`](https://github.com/hsoliwal/eclipse.platform/tree/13ed2153803215c2739015d621ffefb61217ecc1).
The fork comparison inspected then showed additive `.m3` analysis/provenance and
README changes relative to its upstream merge base. This guide adds reader
navigation; it does not certify all fork code or advertise an upstream release.
Commands were checked against repository configuration and source paths; builds
and tests were **not executed** for this documentation update. Recheck toolchain,
parent versions and source links when updating the guide.
