<!-- SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project -->
<!-- SPDX-License-Identifier: Apache-2.0 -->
# JobQueue forwarding implementation plan

Canonical crate: `m3-eclipse-job-queue-forwarding-20261019`.
The parent-approved scope is the exact Eclipse JobQueue implementation at Platform
`c962a6fa6c9e0845714d14709ec19f023d8c2731`, plus one stateless package-private
owner in `org.eclipse.core.internal.jobs`. OrderedLock is outside this cut.

1. Freeze source identity and the original contract. Author independent JUnit
   observations against real InternalJob and scheduling-rule callbacks, run the
   unchanged upstream baseline first, and preserve every observed refusal.
2. Author two Java snapshot templates: the existing facade with unchanged fields,
   constructors and public/protected descriptors, and M3JobQueue with no fields.
   Preserve linked-list semantics and all four boolean combinations (three used
   by JobManager). Do not substitute a heap or add per-job storage.
3. Execute the unchanged canonical Java snapshot owner through real OpenRewrite.
   Check exact outputs, fixed point, drift/missing/duplicate/occupied/alias
   refusals, partial replay, decoys and input permutations. Materialize only
   actual SDK results into a separate candidate bundle copy.
4. Compile all real Jobs sources at release 17, execute the sealed contract and
   selected unchanged upstream Jobs tests, compare public/protected class APIs,
   and resolve/run the actual candidate bundle under genuine Equinox.
5. Seal source/license lineage, exact input/output/toolchain/class identities,
   raw Maven XML/logs, refusal evidence and a closed per-file inventory. Prepare
   publication JSON chunks no larger than 150 KB for the parent. No remote writes.

## Review focus

Live wait-stamp reads and scheduling-rule effects; the unusual advancing
`hasNext()` iterator; orphan links after `clear()`; null/assertion timing;
constructor and protected dummy shape; no API widening or extra retained state;
actual SDK output identity; honest bounded proof and bundle version 3.15.901.

## Execution ledger

- Source inventory completed before this plan. JobQueue blob
  `0a4c0940b5cdfe1b3a586895ad59b8ae1695fe8a`, SHA-256
  `96089bb406f2a3b9573c2a6a196c0806ecc564254b3ae1343bb0dac793e86d01`.
- Ruling: the parent supplies the approved design and isolated crate/scratch
  layout. This plan executes inside those paths; no shared checkout, global
  ledger, branch or canonical SDK owner is edited.
- Pre-flight: the sealed baseline tests consume real Eclipse Jobs sources; SDK
  emission produces the only two production candidate differences consumed by
  the same tests. The bundle manifest is an exact c962 input, not a recipe target.
- Ruling: the original 36-source Jobs bundle produces three javac lint warnings
  (two overloads and one unchecked conversion). The first blanket -Werror run
  correctly refused before tests; evidence is retained. Full-source qualification
  keeps -Xlint:all and requires exact warning parity, without upstream edits.
- Baseline observation: real JobManager validates each test rule against its own
  sentinel rule. The initial fixture logger incorrectly cast that sentinel to the
  test Rule, producing 8 setup errors. Preserve the full 30/0/8/0 XML and source;
  log foreign rules safely without changing any enqueue expectation. No candidate
  template exists at this point.
- Independent review found an unsupported donor copyright end-year in both
  authored templates. Initial SDK 8/0/0/0 and candidate 39/0/0/0 evidence is
  retained, but those templates are not publishable. An added exact-notice
  regression failed 1/1/0/0 before correction. Restore the complete original
  2003, 2012 notice, add separate 2026 modification attribution, and re-emit via
  the same SDK owner. The frozen behavioral contract remains unchanged.
- Final exact qualification complete: SDK 9/0/0/0; candidate runtime 39/0/0/0;
  baseline 38/0/0/0; actual ECJ 36/37 sources with no diagnostics; 47 identical
  API surfaces, 45 unchanged production class files and 52 unchanged test class
  files; both genuine Equinox runs exit 0. All 15 captured process exits were
  read back from their original completed sessions, without reruns.
- Preservation check: all 13 original worktrees clean, all four shared SDK owner
  blobs unchanged, frozen composition verifier unchanged. Final work is closed
  inventory/provenance and bounded publication chunks, with no remote writes.
