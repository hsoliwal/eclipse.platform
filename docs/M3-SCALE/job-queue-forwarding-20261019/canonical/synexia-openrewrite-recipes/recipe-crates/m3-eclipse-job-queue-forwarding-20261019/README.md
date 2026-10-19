<!-- SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project -->
<!-- SPDX-License-Identifier: Apache-2.0 -->
# M3 Eclipse JobQueue forwarding

This crate qualifies the exact Eclipse JobQueue implementation as a stateless,
same-package M3 owner, with the existing Eclipse class retaining its fields,
constructors, protected head and public API. It emits two Java files through the
unchanged canonical M3HashPinnedJavaSnapshotRecipe. There is no new retained queue
state, per-job wrapper, exported owner class, heap replacement or speedup claim.

The target is Eclipse Platform c962a6fa6c9e0845714d14709ec19f023d8c2731, relative to
its runtime/ root. Jobs remains version 3.15.901.qualifier with JavaSE-17. The
semantic donor is the exact original Eclipse JobQueue, not an assumed equivalent
primitive M3 scheduler. This is one completed queue cut; it does not replace
JobManager, WorkerPool, OrderedLock or the rest of Eclipse.

## Generated receiver changes

| Target in runtime/ | Role | Final SHA-256 |
|---|---|---|
| bundles/org.eclipse.core.jobs/src/org/eclipse/core/internal/jobs/JobQueue.java | Existing facade; nine operations, head initialization and head run behavior forward | b7e5e148c62e2e6f9574e27cd7ff418a1c98454199c0b955beb077a0a3e8b592 |
| bundles/org.eclipse.core.jobs/src/org/eclipse/core/internal/jobs/M3JobQueue.java | Absent-before package-private stateless owner | 791329d5c9ffd7bff03e74efbf7986811270255621645dbea6f61cb1f0408a22 |

The one-argument constructor still delegates to the two-argument constructor.
The anonymous head remains JobQueue$1. The iterator retains a JobQueue reference
and its original pointer; its private implementation moves from JobQueue$2 to
M3JobQueue$1. Live wait-stamp reads, scheduling-rule callback order, identity,
exceptions, orphan links after clear, resort failure state and the advancing
hasNext behavior are intentionally preserved. CONTRACT.md defines the boundary.

## Executed qualification

| Gate | Actual result |
|---|---|
| Original frozen contract and unchanged upstream tests | 38 tests, 0 failures, 0 errors, 0 skipped |
| Original ownership admission counterexample | 1 test, 0 failures, 1 expected ClassNotFoundException error; M3JobQueue absent |
| Final real OpenRewrite SDK | 9 tests, 0 failures, 0 errors, 0 skipped; exact two-file emission and fixed point |
| Final emitted candidate runtime gate | 39 tests, 0 failures, 0 errors, 0 skipped |
| Full original and candidate javac | 36 and 37 production sources, release 17; the same 8 production warnings plus 1 unchanged upstream test warning |
| Actual ECJ with original bundle preferences | 36 and 37 sources; both exit 0, no diagnostics |
| Original compiled API surface | 47 matched original class surfaces have identical javap public/protected descriptors |
| Compiled preservation | 45 unaffected original class files and all 52 compiled test class files are exactly equal; all production classes have major version 61 |
| Genuine Equinox baseline and candidate | Both exit 0 with the same client compiled against the original API; real host wiring, conflicting jobs, delayed wakeup and cancellation pass |

The runtime count comprises 28 independent real-InternalJob contract tests,
2 unchanged upstream JobQueueTest methods and 8 selected unchanged JobTest
regressions. The candidate adds the owner-admission test. The independent spectrum
includes 48 seeded insertion permutations of 64 entries, all four boolean
combinations, extreme stamps, live callback effects and unusual iterator use.
The eight upstream JobTest method names and all 13 upstream test/support source
pins are in proof/. No assertion was weakened for the candidate.

SDK refusal cases cover missing or drifted source, occupied additions, duplicates
and colliding normalized path aliases. Partial replay, unrelated Java/text decoys,
input permutations and independent serial/two-worker executions agree on exact
text. A solitary normalized alias is governed by the unchanged owner's existing
policy; no stronger path-policy claim is made.

The finite gates are bounded evidence. No full Platform Tycho reactor, remote CI,
performance benchmark or whole-Eclipse implementation claim is made by this crate.

## Evidence, reproduction and lineage

SOURCE_PINS.json, proof/baseline-bundle-files.json and proof/inputs/ bind all 52
original bundle files, the three unchanged canonical recipe owners, the actual
44 dependency jars, compiler settings and compiled classes. The manifest is not
a recipe target. The two emitted files retain the exact original Eclipse notice
and carry separate 2026 modification attribution. Upstream source remains EPL-2.0;
original proof code and metadata are Apache-2.0. See NOTICE and the license files.

proof/REPRODUCE.md supplies the actual Maven, ECJ, javap and Equinox sequence.
Raw XML/logs and exact command receipts are in evidence/. The final reproduction
helper consolidates separately executed Equinox commands; no additional end-to-end
rerun of the assembled guide is claimed.

Preserved failures include the initial blanket -Werror refusal, a fixture's
incorrect assumption about JobManager's validation sentinel, one missing actual
upstream support source, the expected absent-owner counterexample, and the exact
notice-preservation regression. Independent review caught an incorrect donor end
year in the first templates; those initial runtime-qualified bytes are retained
only as historical evidence, and were replaced through real SDK emission. The
original prose seal is also retained beside a factual correction from the early
three-diagnostic report to the completed baseline's full warning count. Behavioral
test source and expectations remained fixed.

The named recipe is com.synexia.eclipse.JobQueueForwarding20261019. Its physical
edit scope is PACKAGE, restricted to the two enumerated Java paths. This crate
contains no alternate compiler, fake Eclipse API, replacement recipe engine or
published binary. No receiver worktree, remote ref, default branch, shared recipe
owner, global provenance ledger or canonical-home file was modified by its author.
