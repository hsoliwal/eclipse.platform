# Release readiness for M3JDK21 (Java 21): eclipse.platform

Measured on 2026-11-09 against fork master `483f1264447994657d71149e55c93c77bf93c4b3` (upstream master `5cccf6adf8ce`).
Tools and raw evidence: [`docs/m3/jdk21/release-20261109`](../jdk21/release-20261109/). Every gate ran locally on CPU; fork CI runs are not counted
(startup failures and queue starvation on this account, 2026-11).

| # | Gate | Verdict | Evidence |
| --- | --- | --- | --- |
| G1 | No Java 22+ requirement on master: BREE above JavaSE-21 (0), build/IDE pins above 21 (0), unnamed `_` (0), `import module` (0) | PASS | CENSUS.tsv (code view; hits inside strings, text blocks and comments: CENSUS_MASKED.tsv) |
| G2 | Java-21 compile gate: JDK 25 javac `--release 21` minus `--release 25` over 27 bundles (2343 files, 39 roots); 1614 baseline rows cancel | FAIL on master (residue 255, GATE_RESIDUE_MASTER.tsv); PASS with this PR (residue 0) | GATE_RESIDUE.tsv, GATE_SUMMARY.tsv |
| G2b | Parse gate: every tracked Java source (5123 files, test bundles included, test-input folders excluded), JDK 25 javac `--release 21` stopped after parsing | PASS | PARSE_GATE.tsv |
| G3 | Recipe fixed point (`apply_jdk21.sh` + `apply_jdk21_pins.sh` change nothing on master) | PASS | RECIPE_CHANGES.tsv |
| G4 | Metadata consistent: Tycho bundle/feature versions match their pom, no EE above 21 in a Java-21 bundle | PASS | META.tsv |
| G5 | Full Tycho build of this repository on JDK 21 | PENDING | not run locally (disk and time budget); the javac gate G2 covers language level and API |
| G6 | Eclipse SDK product build and launch on JDK 21 | PENDING | aggregator verdict, eclipse.platform.releng.aggregator docs/m3/release/RELEASE_READINESS.md |

## What this PR changes

Fork master `483f12644479` carries the 2026-10-20 metadata delta (27 bundles at JavaSE-21, no unnamed `_`), but not the
org.eclipse.core.filesystem JNA leaf 3c1ea6cbfb of branch `m3/jdk21-compat-20261020` (diverged, never content-merged).
`--release 21` rejects the java.lang.foreign Win32Handler and the jextract bindings under src-gen: 255 rows
(GATE_RESIDUE_MASTER.tsv), the only residue of the 27 bundles.

Fill (`fill_core_filesystem_jna.sh`), no tracked file deleted:

| Step | File |
| --- | --- |
| Win32Handler.java = the JNA form of 3c1ea6cbfb (upstream 9dc9a9359b plus 2168d5bcdc, 6e4cd9bb33, 5df6d33f7f, 7d58287614). Master's FFM form differs from the leaf's base f383091628 only by 7d58287614 (createFileInfo), which the JNA form already carries; the script refuses if master moves further | src/.../local/Win32Handler.java |
| `source.. = src/`: the jextract bindings stay tracked under src-gen/, out of the Tycho build | build.properties |
| src-gen source entry removed, so the IDE build matches Tycho | .classpath |
| `Import-Package: com.sun.jna, com.sun.jna.platform.win32 [5.14.0,6.0.0)`; com.sun.jna 5.19.1 is in eclipse-sdk-prereqs.target | META-INF/MANIFEST.MF |

Gate after the fill: 2343 files, 39 roots, 1614 rows in both runs (classpath noise), residue 0.

## HOLD (user action)

1. Approve deleting `resources/bundles/org.eclipse.core.filesystem/src-gen/com/microsoft/windows/*.java` (6 files, dead
   once src-gen left the build); 3c1ea6cbfb deleted them, this PR keeps them.
2. Runtime check of LocalFile/Win32Handler on Windows with JDK 21 (core.tests.resources filesystem suite): not run here.

## Re-run

```bash
git clone --depth 1 https://github.com/hsoliwal/eclipse.platform.git && git -C eclipse.platform remote add upstream <upstream>
git -C eclipse.platform fetch --depth 1 --filter=blob:none upstream master:refs/remotes/upstream/master
bash docs/m3/jdk21/release-20261109/gate_master.sh eclipse.platform    # paths at the top of the script; JAVAC = JDK 25 javac
bash docs/m3/jdk21/release-20261109/meta_check.sh eclipse.platform > META.tsv
```

The gate compiles every bundle that upstream master declares JavaSE-22 or newer, plus the 2026-10-20 list, against the
newest jar of every bundle in a p2 pool. Errors in both runs are classpath noise (APIs newer than the pool); what remains
after `--release 21` is the Java 22+ residue and must be empty.
