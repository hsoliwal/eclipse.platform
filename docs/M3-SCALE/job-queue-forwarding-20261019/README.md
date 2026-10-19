<!-- SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project -->
<!-- SPDX-License-Identifier: Apache-2.0 -->

# Eclipse Jobs: JobQueue forwarding receiver

The existing `JobQueue` facade forwards its declared queue operations to the
package-private, stateless `M3JobQueue` owner in `org.eclipse.core.internal.jobs`.
The two product files are actual outputs of `com.synexia.eclipse.JobQueueForwarding20261019` through the
unchanged canonical JavaSnapshot owner and the real OpenRewrite SDK.

The queue retains its original state and constructors, protected final `dummy`,
public/protected API, anonymous head identity, identity-based linking and live
iterator reachability. Callback order, wait stamps, both boolean modes,
advancing `hasNext()`, exceptions, clear and resort behavior follow the original
Eclipse donor. The [contract](canonical/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-job-queue-forwarding-20261019/CONTRACT.md) and frozen tests record the
observed baseline behavior. The extraction makes no performance claim.

## Receiver base and exact product cut

This receiver descends from Platform master
`0708647baec7b8122c55685e7ac3cab759ab23b9`, complete tree `fcfe2ef28458bba9d69519544171050f9e090f9c`. That complete tree is
identical to qualified donor commit `c962a6fa6c9e0845714d14709ec19f023d8c2731`. The source
qualification therefore applies without a changed preimage. The original
Jobs manifest remains `3.15.901.qualifier` with `JavaSE-17`.

| Product path under `runtime/bundles/org.eclipse.core.jobs/src/org/eclipse/core/internal/jobs/` | Actual emitted Git blob |
|---|---|
| `JobQueue.java` | `46e399693506b771d2d403285819966900bc5771` |
| `M3JobQueue.java` | `366ffd68b4e6313ab487e41dc17dd93f6dd595a1` |

Only the existing `JobQueue.java` changes; `M3JobQueue.java` is added. All 76
prior Rules receiver entries and all other base paths retain their identities.
The original copyright notice, `2003, 2012 IBM Corporation and others`, is
preserved exactly, followed by separate 2026 modification attribution.

## Recorded qualification

| Gate | Recorded result |
|---|---|
| Original queue and upstream regressions | 38 tests, zero failures/errors/skips |
| Original owner-admission counterexample | Expected ClassNotFoundException because M3JobQueue was absent |
| Actual SDK recipe | 9 tests, zero failures/errors/skips; exactly two emitted files |
| Final candidate runtime | 39 tests, zero failures/errors/skips |
| Full original/candidate Jobs compilation | 36/37 sources; Java 17 class format |
| Strict ECJ with original Jobs preferences | Both exit 0, zero diagnostics |
| API and class comparison | 47 original public/protected surfaces exact; 45 unaffected product classes and 52 test classes byte-identical |
| Real Equinox | Original and candidate exit 0 using the same client compiled against the original bundle |

The [full receipt](canonical/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-job-queue-forwarding-20261019/RECEIPT.json) links the actual commands, raw logs,
JUnit XML, source/dependency hashes, and observed process exit codes. Javac
records the same eight original production warnings plus one original upstream
test warning in each variant. The proof includes 28 independent queue contract
tests, both original JobQueue tests and eight selected original Job regressions.
Real Equinox exercises conflicting-job waiting, sleeping-job wake/cancel, queue
identity and live iteration with Jobs `3.15.901.qualifier`.

Original compiler, fixture, support-source and notice counterexamples remain
preserved. The donor-notice correction went through actual SDK re-emission and
final runtime qualification. The warning-count prose amendment is explicitly
archived; frozen behavioral assertions remain unchanged. The selector list's
canonical ledger classification is `FIRST_PARTY_NO_HEADER` / `FIRST_PARTY_METADATA`;
the sealed eight-line selector file itself is unchanged.

Use the [complete reproduction guide](canonical/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-job-queue-forwarding-20261019/proof/REPRODUCE.md) for the
original/candidate SDK, compilation, API and Equinox procedures. The recipe SDK
uses Java 21; product compilation uses `--release 17`. The guide's consolidated
Equinox helper records separately executed commands and does not claim another
full guide replay. Receiver preparation runs no tests or compilers.

Hosted receiving CI, the full Platform Tycho/PDE reactor, OrderedLock forwarding
and whole-Eclipse acceptance are outside this packet's qualified scope. The
separate CI caller recipe is published independently and does not change these
runtime-source bindings.

## Published source and reproduction layout

The complete source mirror and the two output recipe bindings use
[hsoliwal/com.synexia at `6a7bad0fe6322228fb59bcc563163b4e35fb80ce`](https://github.com/hsoliwal/com.synexia/tree/6a7bad0fe6322228fb59bcc563163b4e35fb80ce/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-job-queue-forwarding-20261019),
canonical tree `0e34f1874a84c51ed1b64d93dc211fb224202044`. The mirror retains all **144 frozen
crate files** and exactly **four unchanged SDK owners** in their original
`synexia-openrewrite-recipes` directory layout. The POM's
`${project.basedir}/../../src/main/java` default still resolves; this crate
compiles its 3 required shared owners from that location.

[SOURCE_RECEIPT.json](SOURCE_RECEIPT.json) binds each public source and output to
its exact canonical path, commit, tree, Git blob, SHA-256 and license.
[FILE_PROVENANCE.tsv](FILE_PROVENANCE.tsv) preserves every original nine-column
lineage field and records public license/notice mappings separately. The three
receiver metadata files are versioned by the receiver Git tree; their records
make no circular self-hash or future-commit claim.

The closed receiver set is **153 blobs**: 144 crate files,
four SDK owners, two actual SDK product outputs and three scoped metadata files.
The copied canonical evidence retains its original execution revisions and
timestamps. Runtime source bindings remain fixed at the canonical publication
above when a later, separately qualified CI caller packet is added.

The original and derived Eclipse sources retain EPL-2.0. First-party recipe,
proof and receiver metadata use Apache-2.0. The complete
[Apache license](canonical/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-job-queue-forwarding-20261019/LICENSE-APACHE-2.0),
[EPL license](canonical/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-job-queue-forwarding-20261019/LICENSE-EPL-2.0), and
[crate NOTICE](canonical/synexia-openrewrite-recipes/recipe-crates/m3-eclipse-job-queue-forwarding-20261019/NOTICE) are unchanged copies. Existing source headers
remain intact. The shared owner lineage's historical notice reference resolves
to the [immutable public UI notice](https://github.com/hsoliwal/eclipse.platform.ui/blob/52f64fe74beb3f8763cefd28651c5d5ab01d5d92/docs/M3-SCALE/runtime-forwarding-20261018/canonical/synexia-openrewrite-recipes/recipe-crates/m3-jface-forwarding-gate-20261018/NOTICE). Recorded tool output
retains its original attribution and does not acquire a new software license
through a metadata label.
