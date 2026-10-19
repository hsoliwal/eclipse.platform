<!-- SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project -->
<!-- SPDX-License-Identifier: Apache-2.0 -->
# Exact Eclipse JobQueue forwarding contract

## Admission and ownership

The semantic donor is the entire original EPL-2.0 JobQueue at Platform commit
`c962a6fa6c9e0845714d14709ec19f023d8c2731`, path
`runtime/bundles/org.eclipse.core.jobs/src/org/eclipse/core/internal/jobs/JobQueue.java`.
Its Git blob is `0a4c0940b5cdfe1b3a586895ad59b8ae1695fe8a`; SHA-256 is
`96089bb406f2a3b9573c2a6a196c0806ecc564254b3ae1343bb0dac793e86d01`.
The queue's own implementation is the admitted semantic donor. Existing M3 bulk
schedulers, primitive heaps and semaphore queues have different contracts; their
presence does not establish interchangeability with this intrusive Jobs queue.

The reusable transformation owner remains the unchanged canonical
`com.synexia.rewrite.M3HashPinnedJavaSnapshotRecipe`. This crate has exactly two
Java targets relative to Platform's `runtime/` root: replace `JobQueue.java` and
add absent-before `M3JobQueue.java` in the same `org.eclipse.core.internal.jobs`
package. No other production file, shared recipe owner, policy, descriptor,
package export, dependency, scheduler or lock implementation is a target.
The bundle keeps c962's exact manifest, version `3.15.901.qualifier`, and
`Bundle-RequiredExecutionEnvironment: JavaSE-17`.

## Facade and state

JobQueue remains a public final class implementing `Iterable<InternalJob>`.
Both public constructor descriptors and every declared public/protected member
are retained, including the protected final `InternalJob dummy` and both private
final boolean fields. The one-argument constructor retains priority overtaking.
The anonymous head remains `JobQueue$1`, with its public `run(IProgressMonitor)`
returning the same `Status.OK_STATUS`. Allocation remains the facade's identity
boundary; head initialization and run behavior dispatch to the owner.

Every declared queue operation delegates to the same-package, package-private
final M3JobQueue. That owner has no fields, no instance exposed to callers, and
only static operations. InternalJob access is not widened. There is still one
head per queue and no per-job wrapper, side index, cached key or duplicate queue
state. An iterator retains one reference to its original JobQueue and one live
pointer, matching the original queue reachability. The representation remains
an intrusive doubly linked list and retains its linear insertion work.
No throughput, latency, memory improvement or whole-Eclipse coverage is claimed.

## Ordering, effects and exceptional behavior

All four public boolean combinations are admitted. JobManager's waiting queue
uses `(false, true)`, sleeping queue uses `(true, true)`, and waiting-thread queue
uses `(false, false)`; `(true, false)` remains supported by the public constructor.
The underlying InternalJob comparator and real scheduling rules decide ordering.
Equal comparator times are FIFO when priority overtaking is enabled. Identity is
preserved even for jobs whose `equals` and `hashCode` deliberately coincide.

Each attempted overtake first checks for the dummy. Positive earlier wait stamps
can override both priority and conflict barriers. The new job's wait-stamp getter
is read once for `> 0` and again for `<`; these calls may return different values
or change other live state. A nonpositive stamp does not read the existing job's
stamp. Comparator and scheduling-rule effects retain their original order and
receiver identities. Actual InternalJob.isConflicting can invoke the existing
job's scheduling rule; no normalized or cached substitute is introduced.

Insertion asserts a null next link followed by a null previous link before any
queue ordering callback, then writes the original four links in order. Failed
callbacks propagate their original exception object without a partial insertion.
Live callbacks can clear the queue, and the remainder of the same operation sees
that effect as in the original source. This is not a new promise of arbitrary
thread safety: queue operations retain the existing caller-held JobManager lock
contract and add no synchronization.

`remove` acts through the supplied job's links, even if it belongs to another
queue; detached removal is harmless. `dequeue` and `peek` use the dummy's previous
link and return null for an empty queue. `clear` resets only the head's two links:
old entries retain their links. `resort` removes before enqueueing; an enqueue
failure leaves the entry removed instead of rolling it back. Null and assertion
exceptions retain their type and ordering. New implementation stack frames and
private iterator implementation names are not a compatibility promise.

## Iterator and text observations

The unusual original iterator is preserved intentionally. `hasNext()` advances
its pointer; `next()` only returns that pointer. Before the first `hasNext()`,
`next()` returns the dummy. Repeated `next()` returns the same value. After a false
`hasNext()`, `next()` returns null and another `hasNext()` throws
NullPointerException. Iterator removal always throws UnsupportedOperationException.
Removing the current entry makes its next advancing test terminate via its null
previous link. Iterators remain live across queue mutation and are independent.

Iterable defaults retain their original order. `toString()` first collects the
iterator's job identities into an ArrayList, then formats them, preserving
observable element text effects against that snapshot.

## Executable proof boundaries

The 28 original-contract JUnit tests and the ownership admission test were written
before candidate templates. Real unchanged JobQueue and InternalJob are compiled
with the complete original 36-source Jobs bundle, genuine Common and OSGi jars,
and real JUnit. No test doubles replace Eclipse classes. The fixture subclasses
actual InternalJob only at legitimately overridable hooks and implements actual
ISchedulingRule callbacks. The upstream JobQueueTest and eight named JobTest
methods run unchanged, with their exact upstream support sources.

The original ownership test must fail because M3JobQueue is absent; the candidate
must satisfy it. The same contract suite runs against actual SDK-emitted source.
The finite spectrum includes 48 seeded permutations of 64 entries, extreme wait
stamps, four modes, callbacks, exceptions and live iterators. It is bounded evidence,
not a general proof for all programs or concurrency schedules.

Full-source compilation uses JDK 21 javac with `--release 17 -Xlint:all` for both
original and candidate. The completed original compilation has eight production
warnings (two overloads, three fall-throughs, one unchecked conversion and two
constructor escapes), plus one unchanged upstream test constructor-escape warning.
Candidate warning identities must be identical. The initial blanket -Werror run
reported three diagnostics before refusing; its evidence is preserved. The full
warning-count observation amends only the original contract's reporting prose,
with the original prose seal retained under evidence/contract-prose-amendment.
No donor source or behavioral assertion changes for a new lint rule. Actual
receiver ECJ preferences are also qualified when executing the compiler gate.
Public/protected API descriptors of original named Jobs classes are compared,
and genuine Equinox bundle resolution and client execution verify host wiring.
The proof does not stand in for the full Platform Tycho reactor or remote CI.

## SDK boundaries

The class-backed named recipe must emit exactly the two hash-pinned templates via
real OpenRewrite, then reach an exact no-change fixed point. Missing or drifted
preimages, occupied owner additions, duplicate targets and colliding normalized
path aliases must refuse. Unrelated text/Java decoys remain untouched; mixed
before/after input, input order and bounded independent executions agree. A
solitary normalized path spelling is governed by the unchanged owner's existing
path policy; this crate makes no stronger path-validation claim.

All raw failures and setup corrections are retained with their original inputs.
The two original fixture/setup corrections observed so far are JobManager's
foreign sentinel validation rule and the missing actual AsynchExecThread support
source. Neither changed a product method or an enqueue/iterator expectation.
Only actual SDK results may be installed into the separate candidate copy.
