# Eclipse Platform command cheat sheet

[Start with the fork guide](FORK_GUIDE.md) · [README](../README.md)

Commands below assume the repository root, Git, JDK 25 and an appropriate Maven
3.9.x installation. See the guide for network, target-platform and UI prerequisites.
They are source-grounded examples, not a record of a successful local test run.

## Inspect before building

```sh
git status --short
git rev-parse HEAD
java -version
mvn -version
cat .mvn/maven.config
cat .mvn/extensions.xml
```

`mvn -version` identifies the JVM launching Maven. Bundle execution environments
and the JDKs selected by BREE toolchains are separate concepts.

## Choose the smallest useful build

| Goal | Command | What the result means |
| --- | --- | --- |
| Resource implementation plus regression tests | `mvn clean verify -pl :org.eclipse.core.resources,:org.eclipse.core.tests.resources` | Both selected bundles participate; inspect test reports |
| Resource test bundle only | `mvn verify -pl :org.eclipse.core.tests.resources` | Useful for an unchanged target; not proof your production edit was built |
| Component reactor | `mvn clean verify` | Builds this repository's selected modules, not the whole SDK |
| Compilation/package check | `mvn clean verify -DskipTests` | Tests are skipped |
| API checks | `mvn verify -Papi-check` | Requires the configured baseline; examine `target/apianalysis` |
| Javadoc check | `mvn verify -Pjavadoc -DfailOnJavadocErrors=true` | Requires configured documentation dependencies/toolchains |

The checked-in configuration activates `-Pbuild-individual-bundles`. If invoking
Maven in a context that does not pick it up, pass that profile explicitly.
Do not copy CI's `-Dmaven.test.failure.ignore=true` into a strict local gate.

## Trace source and metadata

```sh
git grep -n 'interface IResource' -- resources
git grep -n 'ISchedulingRule' -- runtime
git grep -n 'ILaunchConfiguration' -- debug
git grep -n 'Bundle-RequiredExecutionEnvironment' -- '*MANIFEST.MF'
git ls-files '*pom.xml' '*MANIFEST.MF' '*build.properties'
```

No POM in a bundle directory? Inspect the containing reactor and Tycho's pomless
extension before concluding that the bundle cannot participate in a build.

## Locate evidence after a run

```sh
find . -path '*/target/surefire-reports/*' -type f
find . -path '*/target/apianalysis/*' -type f
find . -path '*/target/compilelogs/*' -type f
```

Check actual test counts, failures and skips. Existing output from an earlier run
must not be attributed to the current commit.

## Inspect fork differences without changing your working tree

In a fresh clone, add the upstream remote once. If it already exists, inspect its
URL with `git remote -v` rather than replacing it blindly.

```sh
git remote add upstream https://github.com/eclipse-platform/eclipse.platform.git
git fetch upstream master
git log --oneline --left-right upstream/master...HEAD
git diff --stat upstream/master...HEAD
git diff --name-only upstream/master...HEAD
```

The three-dot diff reports the fork side since the merge base. It is not a complete
list of changes newly present at the current upstream tip. Fetch does not merge.

## A compact issue report

- Exact commit and clean/dirty state
- OS, architecture, display setup where relevant
- Maven version and Java home from `mvn -version`
- Selected bundles and the full command
- Minimal reproduction and expected/actual result
- First meaningful error and relevant test/API report
- Whether source, tests, target platform or runtime differs from upstream

Review logs for credentials or personal paths before posting publicly.
