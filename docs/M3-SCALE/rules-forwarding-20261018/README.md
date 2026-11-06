<!-- SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project -->
<!-- SPDX-License-Identifier: Apache-2.0 -->
# Eclipse Jobs Rules forwarding receiver

The public `MultiRule` facade forwards all seven operations to the internal
`org.eclipse.core.internal.jobs.m3.M3Rules` array owner. The receiver contains two
generated runtime files. Its existing public descriptors, final child-array
storage, JavaSE-17 bundle requirement, and caller scheduling-rule callbacks remain
the receiving contract.

## Canonical source and frozen tooling

The canonical Rules recipe is owned by
[hsoliwal/com.synexia at 2b61cc39df74eefe44152a16b6098ebfe53c3f8d](https://github.com/hsoliwal/com.synexia/tree/2b61cc39df74eefe44152a16b6098ebfe53c3f8d/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-rules-forwarding-20261018).
This directory preserves a frozen public source mirror: all **67 committed crate
files** and the **four SDK source owners** selected by its existing POM. The POM's
relative source layout is preserved, so reproduction uses the public mirrored
source directly with genuine OpenRewrite dependencies. It does not require access
to the canonical repository to compile this snapshot.

The runtime outputs were already emitted by the real OpenRewrite `RecipeRun` and
match the committed [materialization receipt](tooling/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-rules-forwarding-20261018/proof/materialization.json).
Preparing this handoff verified their hashes and preserved their bytes.

The shared `M3HashPinnedTextSnapshotRecipe` is bound to
[hsoliwal/com.synexia at 9a04e7cf94e2b47570fed61ed508e50dcd80edb1](https://github.com/hsoliwal/com.synexia/blob/9a04e7cf94e2b47570fed61ed508e50dcd80edb1/synexia-openrewrite-recipes/src/main/java/com/synexia/rewrite/M3HashPinnedTextSnapshotRecipe.java).
Its SHA256 is `58c94f4b87a6e4a53268808bf0c635fe7bd56e2864cb8fe46483c85efba771d0` and Git blob is
`cc5f86ab579d2f4b1b9bc4c26b4592d2d8142244`. The final canonical publication tree is
`d26411a4886ddb3cfb2590306b0753caa60c1a84`. The predecessor at the historical Rules
commit remains recorded as Git blob `9d0788d3717e017d58345c5c3e62022c2915a3c8`.
All 67 Rules crate files and the other three SDK owners retain their verified
`2b61cc39df74eefe44152a16b6098ebfe53c3f8d` source binding. Every mirrored source
and runtime output byte is preserved; this update completes the canonical
metadata binding.

[SOURCE_RECEIPT.json](SOURCE_RECEIPT.json) records the exact source binding and
closed path set. [FILE_PROVENANCE.tsv](FILE_PROVENANCE.tsv) records the Git blob,
SHA256, origin, license, and binding status of every mirrored source file and both
generated runtime outputs. The three receiver-owned metadata files are listed
separately in the receipt and are versioned by the receiving repository commit;
the manifests do not claim circular hashes of themselves.

## Qualified source and publication parent

The recorded qualification and local receiver review use source baseline
`69291c18babd233a94b742798b3d1078979bf78c`. The public branch is to be created on the
newer master commit `2bfb19847bd09bf28749194d489e8696b4a997b1`, tree
`57536bcc9922de83b8399e309a1d3fa07a96335e`, preserving the fork's intervening work.

The publisher verified the [exact intervening comparison](https://github.com/hsoliwal/eclipse.platform/compare/69291c18babd233a94b742798b3d1078979bf78c...2bfb19847bd09bf28749194d489e8696b4a997b1):
**seven commits and 29 changed paths**, all under `.m3/` or the single workflow
`.github/workflows/m3-shared-bootstrap-sdk-20261018.yml`. No runtime path, root
`AGENTS.md`, or member of this 76-path receiver packet changed. The existing
`MultiRule.java` preimage and the absent `M3Rules.java` addition therefore remain
the admitted runtime inputs at the publication parent.

`SOURCE_RECEIPT.json` distinguishes `qualified_source_base` from
`publication_parent` and records the comparison scope and unchanged preimage
identities. The same 76 entries are composed with the newer parent's tree. The
local worktree, all 71 mirrored files, both runtime outputs, and the historical
execution evidence stay at their qualified identities. No tests were rerun for
this metadata integration update; full platform CI remains a separate gate.

## Actual bounded gates

The frozen evidence records **17 driver stages with exit code zero**:

| Gate | Recorded result |
|---|---|
| Real OpenRewrite 8.17.1 recipe | 5 tests passed |
| Frozen client contract | 13 tests passed, zero skipped, against each variant |
| Entire Jobs production source compilation | 35 baseline / 36 candidate sources with Java 21, release 17 |
| Full-bundle contract and unchanged upstream MultiRule tests | 16 tests passed, zero skipped, against each variant, including 3 upstream tests |
| Public API comparison | Identical descriptors |
| Forwarding bytecode inspection | 7 candidate calls to M3Rules |
| Real Equinox external client | 14 assertions passed against each variant |

The changed runtime classes and new Java proof classes passed `-Xlint:all -Werror`.
The entire pre-existing Jobs bundle produces eight existing warnings; the recorded
baseline and candidate diagnostics match. The Equinox probe uses the real Jobs
manifest and real dependencies, compiles its external client once against the
baseline, and runs that same client against both bundles.

These are the carried, committed execution receipts. The handoff preparation
checks source identity, provenance, the closed file set, and whitespace; it does
not relabel packaging checks as a new execution of those gates. Full Tycho/PDE
platform CI and complete Eclipse integration remain separate qualifications. The
packet makes no unmeasured speed or universal memory-reduction claim.

A separate compatibility run used this unchanged public POM and the selected
shared Text owner. All **five existing SDK tests passed**, with zero failures,
errors, or skips. The four SDK sources compiled with the existing Java 21
`-Xlint:all -Werror` settings, and both actual `RecipeRun` outputs were identical
to the existing runtime files. The fresh receipt, log, and Surefire XML are kept
separately from the frozen 67-file crate; their hashes are recorded in
`SOURCE_RECEIPT.json`. The full 17-stage driver was not rerun for this owner update.

## Handoff review

Source readback verifies all 71 mirrored files and both existing runtime outputs.
The scoped provenance contains 73 exact source/output records, and the complete
receiver change set contains 76 paths, including these three metadata files.

The full `git diff --check` returns **exit code 2** with 34 inherited whitespace
findings in six frozen crate files:

| Frozen file | Inherited finding |
|---|---|
| `LICENSE-EPL-2.0` | Two trailing spaces in the exact license text |
| `evidence/baseline-contracts.log` | Maven `[INFO] ` lines |
| `evidence/candidate-contracts.log` | Maven `[INFO] ` lines |
| `evidence/recipe-sdk.log` | Maven `[INFO] ` lines |
| `evidence/baseline-full-bundle-tests.log` | Final blank line in raw output |
| `evidence/candidate-full-bundle-tests.log` | Final blank line in raw output |

These six files match the canonical source blobs byte for byte. Their original
license and raw execution evidence bytes are preserved. The separate check of
the three receiver metadata files and two generated runtime files passes with
exit code 0; the check of all remaining 70 paths also passes with exit code 0.
No whitespace attributes or checks were changed.

## Reproduce from public source

Use a separate **clean** checkout of `hsoliwal/eclipse.platform` at
`69291c18babd233a94b742798b3d1078979bf78c`, an empty proof output directory, Java 21, Maven 3.9.12, the exact
dependencies in the mirrored `SOURCE_PINS.json`, and a populated genuine Maven
cache. The proof driver uses Maven offline and rejects a dirty baseline checkout,
changed source or dependency hashes, nonempty proof output, and occupied additions.
The receiving worktree containing the generated changes is not the clean baseline
input to this command.

From the repository root:

```sh
cd docs/M3-SCALE/rules-forwarding-20261018/tooling/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-rules-forwarding-20261018
python3 proof/reproduce.py \
  --platform /path/to/clean-pinned-eclipse.platform \
  --work /path/to/empty-proof-output \
  --jdk /path/to/jdk21 \
  --maven /path/to/apache-maven-3.9.12/bin/mvn \
  --maven-cache /path/to/genuine-maven-repository \
  --deps /path/to/pinned-eclipse-dependencies \
  --junit-console /path/to/junit-platform-console-standalone-1.10.2.jar
```

The recipe's physical root is the existing `runtime/` reactor. Manifest paths start
with `bundles/org.eclipse.core.jobs/...`; repository paths add the exact `runtime/`
prefix. The unchanged driver obtains product postimages from successful SDK
results and records fresh logs, exit codes, JUnit XML, and output hashes.

## Notices and licenses

The full [Apache-2.0 license](tooling/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-rules-forwarding-20261018/LICENSE-APACHE-2.0),
[EPL-2.0 license](tooling/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-rules-forwarding-20261018/LICENSE-EPL-2.0), and
[NOTICE](tooling/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-rules-forwarding-20261018/NOTICE) are copied byte for byte. Eclipse source snapshots,
the forwarding postimage, and the derived M3Rules owner retain EPL-2.0 and the
existing IBM/Eclipse notices. The recipe SDK, independent proof tooling, and this
receiver documentation use Apache-2.0. Mirroring the tooling does not relicense the
emitted Eclipse code.

See the mirrored [README](tooling/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-rules-forwarding-20261018/README.md),
[CONTRACT](tooling/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-rules-forwarding-20261018/CONTRACT.md),
[CATALOGUE](tooling/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-rules-forwarding-20261018/CATALOGUE.tsv), and
[COVERAGE](tooling/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-rules-forwarding-20261018/COVERAGE.tsv) for exact behavior and unfinished boundaries.
