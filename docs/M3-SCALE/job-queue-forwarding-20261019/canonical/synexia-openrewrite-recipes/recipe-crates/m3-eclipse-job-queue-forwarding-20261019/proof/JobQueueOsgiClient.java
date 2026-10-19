// SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project
// SPDX-License-Identifier: Apache-2.0
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.core.internal.jobs.InternalJob;
import org.eclipse.core.internal.jobs.JobQueue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;

/** Real bundle client: its Jobs classes must be wired to the installed host. */
public final class JobQueueOsgiClient {
    private JobQueueOsgiClient() { }
    private static void require(boolean value, String message) {
        if (!value) throw new AssertionError(message);
    }
    private static final class Entry extends InternalJob {
        Entry(String name) { super(name); }
        @Override protected IStatus run(IProgressMonitor monitor) { return Status.OK_STATUS; }
    }
    private static final class Rule implements ISchedulingRule {
        @Override public boolean contains(ISchedulingRule other) { return this == other; }
        @Override public boolean isConflicting(ISchedulingRule other) { return this == other; }
    }
    public static void main(String[] args) throws Exception {
        JobQueue queue = new JobQueue(false);
        Entry first = new Entry("first"), second = new Entry("second");
        require(queue.isEmpty() && queue.peek() == null && queue.dequeue() == null, "empty queue");
        queue.enqueue(first); queue.enqueue(second);
        require(queue.peek() == first, "equal-time FIFO head");
        Iterator<InternalJob> iterator = queue.iterator();
        require(iterator.hasNext() && iterator.next() == first, "iterator first");
        require(iterator.next() == first, "iterator next is a live pointer read");
        require(iterator.hasNext() && iterator.next() == second, "iterator second");
        require(!iterator.hasNext() && iterator.next() == null, "iterator terminal null");
        require(queue.dequeue() == first && queue.dequeue() == second, "identity FIFO dequeue");
        require(queue.isEmpty(), "queue drained");

        Rule rule = new Rule();
        CountDownLatch started = new CountDownLatch(1), release = new CountDownLatch(1);
        List<String> order = java.util.Collections.synchronizedList(new ArrayList<>());
        Job holding = new Job("M3 queue OSGi conflict holder") {
            @Override protected IStatus run(IProgressMonitor monitor) {
                order.add("holder"); started.countDown();
                try {
                    if (!release.await(10, TimeUnit.SECONDS)) return Status.CANCEL_STATUS;
                } catch (InterruptedException interrupted) {
                    Thread.currentThread().interrupt(); return Status.CANCEL_STATUS;
                }
                return Status.OK_STATUS;
            }
        };
        Job waiting = new Job("M3 queue OSGi conflict waiter") {
            @Override protected IStatus run(IProgressMonitor monitor) {
                order.add("waiter"); return Status.OK_STATUS;
            }
        };
        holding.setRule(rule); waiting.setRule(rule);
        try {
            holding.schedule(); require(started.await(10, TimeUnit.SECONDS), "holder started");
            waiting.schedule(); require(waiting.getState() == Job.WAITING, "conflicting waiter stays queued");
        } finally {
            release.countDown();
        }
        require(holding.join(10000, null) && waiting.join(10000, null), "conflict jobs completed");
        require(holding.getResult().isOK() && waiting.getResult().isOK(), "conflict jobs succeeded");
        require(order.equals(List.of("holder", "waiter")), "conflict execution order");

        AtomicInteger runs = new AtomicInteger();
        Job sleeper = new Job("M3 queue OSGi wakeup") {
            @Override protected IStatus run(IProgressMonitor monitor) {
                runs.incrementAndGet(); return Status.OK_STATUS;
            }
        };
        sleeper.schedule(60000); require(sleeper.getState() == Job.SLEEPING, "delayed job is sleeping");
        sleeper.wakeUp(); require(sleeper.join(10000, null), "woken job completed");
        require(runs.get() == 1 && sleeper.getResult().isOK(), "woken job ran once");
        sleeper.schedule(60000); require(sleeper.cancel(), "sleeping cancellation");
        require(sleeper.getState() == Job.NONE && runs.get() == 1, "canceled sleeper does not run");
        System.out.println("OSGI_CLIENT_PASS queueIdentity=true liveIterator=true conflictWait=true sleepingWakeCancel=true");
    }
}
