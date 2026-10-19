<!-- SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project -->
<!-- SPDX-License-Identifier: Apache-2.0 -->
# Reproduce the JobQueue qualification

Run in a separate writable scratch directory. The reference Platform worktree
must be the exact c962 commit in SOURCE_PINS.json (or a tree-identical commit).
The recorded receiver at 52f3 has the same 51 nonmanifest bundle files;
proof/base-transfer.json records the only change to reach c962. Never write
compilation output into a reference checkout.

The recorded tools are Temurin 21.0.12.1+1, Maven 3.9.12, OpenRewrite 8.17.1,
Surefire 3.2.5, Jupiter 5.10.2, ECJ 3.46.100 and the genuine dependency jars in
proof/inputs/dependency-jars.json. Acquisition receipts and Maven paths are beside
that inventory. These are a bounded proof runner, not an Eclipse full-target or
Tycho reactor claim. Normal Maven Central resolution may populate a new cache;
`-o` below uses the already populated recorded cache.

Set absolute paths (use a scratch path without spaces for ECJ argument files):

```bash
CRATE=/absolute/path/to/m3-eclipse-job-queue-forwarding-20261019
PLATFORM=/absolute/path/to/c962-platform-reference
WORK=/tmp/m3-job-queue-replay
JDK=/absolute/path/to/jdk21
MVN=/absolute/path/to/maven/bin/mvn
M2=/absolute/path/to/maven-cache
DEPS=/absolute/path/to/eclipse-deps
TEST_DEPS=/absolute/path/to/test-deps
export JAVA_HOME="$JDK"
export PATH="$JDK/bin:$PATH"
mkdir -p "$WORK"
cp -a "$PLATFORM/runtime/bundles/org.eclipse.core.jobs" "$WORK/baseline-bundle"
```

Verify every copied bundle file against proof/baseline-bundle-files.json. If using
52f3 as the reference, substitute only proof/inputs/org.eclipse.core.jobs.MANIFEST.MF
in the scratch baseline and check all 52 pins again. Upstream test source bytes
are already in proof/src/test/java and separately pinned in
proof/upstream-source-pins.json. The exact frozen behavioral test is unchanged
from the original successful baseline. The original prose seal and the later
warning-count-only correction are both retained.

The baseline compiles all 36 real production sources and runs 38 tests:

```bash
SELECTED='JobTest#testAsynchJobConflict+testRescheduleWaiting+testRescheduleSimple+testRescheduleDelay+testCancelAboutToSchedule+testCancelFromAboutToRun+testJoinWithTimeout+testSetRule'
"$MVN" -o -B -ntp -f "$CRATE/proof/pom.xml" \
  -Dmaven.repo.local="$M2" -Djobs.bundle.root="$WORK/baseline-bundle" \
  -Declipse.deps.dir="$DEPS" -Dtest.deps.dir="$TEST_DEPS" \
  -Dproof.build.dir="$WORK/baseline-build" \
  -Dtest="JobQueueContractTest,JobQueueTest,$SELECTED" test
```

The separately recorded original-baseline ownership admission refuses with
ClassNotFoundException for M3JobQueue (one test, one error). Its raw report is in
evidence/baseline-owner-expected-red. It is an expected counterexample, not a
passing baseline test, and is not mixed into the 38-test baseline count.

Run the existing class-backed SDK recipe and materialize its actual results:

```bash
PARSER_CP="$WORK/baseline-build/classes:$DEPS/org.eclipse.equinox.common-3.21.0.jar:$DEPS/org.eclipse.osgi-3.24.300.jar"
"$MVN" -o -B -ntp -f "$CRATE/pom.xml" -Dmaven.repo.local="$M2" \
  -Dcanonical.classpath="$PARSER_CP" -Dmaterialize.dir="$WORK/generated" test
cp -a "$WORK/baseline-bundle" "$WORK/candidate-bundle"
cp "$WORK/generated/bundles/org.eclipse.core.jobs/src/org/eclipse/core/internal/jobs/JobQueue.java" \
  "$WORK/candidate-bundle/src/org/eclipse/core/internal/jobs/JobQueue.java"
cp "$WORK/generated/bundles/org.eclipse.core.jobs/src/org/eclipse/core/internal/jobs/M3JobQueue.java" \
  "$WORK/candidate-bundle/src/org/eclipse/core/internal/jobs/M3JobQueue.java"
```

The SDK count is 9/0/0/0. Verify that generated contains exactly two files and both
SHA-256 values equal the manifest. Confirm that the candidate differs from the
baseline only at those two paths. Do not copy templates directly to the candidate.
If the crate is mirrored away from canonical Synexia, supply
`-Drecipe.owner.root=/path/to/the/three/pinned/canonical/Java/owners` to the SDK
POM; those owner bytes must match proof/inputs/canonical-owners.json.

The same tests plus the now-present owner admission total 39/0/0/0:

```bash
"$MVN" -o -B -ntp -f "$CRATE/proof/pom.xml" \
  -Dmaven.repo.local="$M2" -Djobs.bundle.root="$WORK/candidate-bundle" \
  -Declipse.deps.dir="$DEPS" -Dtest.deps.dir="$TEST_DEPS" \
  -Dproof.build.dir="$WORK/candidate-build" \
  -Dtest="JobQueueContractTest,M3JobQueueOwnershipTest,JobQueueTest,$SELECTED" test
python3 "$CRATE/proof/check-api.py" --jdk "$JDK" \
  --baseline "$WORK/baseline-build/classes" --candidate "$WORK/candidate-build/classes" \
  --dependencies "$DEPS/org.eclipse.equinox.common-3.21.0.jar:$DEPS/org.eclipse.osgi-3.24.300.jar" \
  --output "$WORK/api"
```

Read the complete compiler logs. Javac has eight identical pre-existing production
warnings and one identical upstream-test warning in both builds. The warning
parity receipt retains their exact messages and locations. The JDK API gate
requires 47 matching original class surfaces, 45 unchanged original class files,
and class-file major version 61 throughout. The private anonymous iterator moves
from JobQueue$2 to M3JobQueue$1; the dummy remains JobQueue$1.

Run actual ECJ with each bundle's unchanged preferences and complete source list:

```bash
ECJ=/absolute/path/to/ecj-3.46.100.jar
for KIND in baseline candidate; do
  rg --files "$WORK/$KIND-bundle/src" | rg '\.java$' | sort > "$WORK/$KIND-sources.args"
  "$JDK/bin/java" -jar "$ECJ" \
    -properties "$WORK/$KIND-bundle/.settings/org.eclipse.jdt.core.prefs" \
    --release 17 -proc:none -d "$WORK/ecj-$KIND/classes" \
    -cp "$DEPS/org.eclipse.equinox.common-3.21.0.jar:$DEPS/org.eclipse.osgi-3.24.300.jar" \
    -log "$WORK/ecj-$KIND.xml" @"$WORK/$KIND-sources.args"
done
```

The recorded actual ECJ XML contains 36 baseline and 37 candidate source entries,
48 and 49 class outputs respectively, no problems, and exit code 0. No preference
or warning severity is changed.

Finally, package the source-built bundles with their exact c962 manifest/resources
and compile one client against the original baseline. Reuse that same client in
both genuine Equinox frameworks:

```bash
python3 "$CRATE/proof/equinox.py" --jdk "$JDK" --dependencies "$DEPS" \
  --baseline-bundle "$WORK/baseline-bundle" --baseline-classes "$WORK/baseline-build/classes" \
  --candidate-bundle "$WORK/candidate-bundle" --candidate-classes "$WORK/candidate-build/classes" \
  --output "$WORK/equinox"
```

The client and launcher source, actual compile commands, jar hashes and both real
framework outputs are in evidence/equinox. Both runs reported actual host wiring,
queue identity, live iterator behavior, conflicting jobs and sleeping/wake/cancel
success with Jobs 3.15.901.qualifier and Equinox 3.24.300.v20260721-1251. This script
consolidates those separately executed commands; no additional end-to-end rerun of
the assembled reproduction guide is claimed.
