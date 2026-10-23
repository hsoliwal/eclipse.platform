/*******************************************************************************
 * Copyright (c) 2017, 2026 Google, Inc and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Stefan Xenos (Google) - Initial implementation
 *   Andrey Loskutov (loskutov@gmx.de) - many different extensions
 *******************************************************************************/
package org.eclipse.debug.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import org.eclipse.core.internal.jobs.JobManager;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.internal.ui.launchConfigurations.LaunchConfigurationManager;
import org.eclipse.debug.tests.launching.LaunchConfigurationTests;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;


public final class TestUtil {

	/**
	 * The default test timeout in milliseconds
	 */
	public static long DEFAULT_TIMEOUT = 30_000;

	private TestUtil() {
	}

	/**
	 * Call this in the tearDown method of every test to clean up state that can
	 * otherwise leak through SWT between tests.
	 */
	public static void cleanUp(String owner) {
		// Ensure that the Thread.interrupted() flag didn't leak.
		assertFalse(Thread.interrupted(), "The main thread should not be interrupted at the end of a test");

		// Wait for any outstanding jobs to finish. Protect against deadlock by
		// terminating the wait after a timeout.
		boolean timedOut = waitForJobs(owner, 5, 5000);
		if (timedOut) {
			// We don't expect any extra jobs run during the test: try to cancel them
			log(IStatus.INFO, owner, "Trying to cancel running jobs: " + getRunningOrWaitingJobs(null)); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
			getRunningOrWaitingJobs(null).forEach(Job::cancel);
			waitForJobs(owner, 5, 1000);
		}

		// Ensure that the Thread.interrupted() flag didn't leak.
		assertFalse(Thread.interrupted(), "The main thread should not be interrupted at the end of a test");
	}

	public static void log(int severity, String owner, String message, Throwable... optionalError) {
		Throwable error = null;
		if (optionalError != null && optionalError.length > 0) {
			error = optionalError[0];
		}
		Bundle testBundle = FrameworkUtil.getBundle(TestUtil.class);
		Status status = new Status(severity, testBundle.getSymbolicName(), "[" + owner + "] " + message, error); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		ILog.of(testBundle).log(status);
	}

	/**
	 * Process all queued UI events. If called from background thread, does
	 * nothing.
	 */
	public static void processUIEvents() {
		Display display = Display.getCurrent();
		if (display != null && !display.isDisposed()) {
			while (display.readAndDispatch()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				// Keep pumping events until the queue is empty
			}
		}
	}

	/**
	 * Process all queued UI events. If called from background thread, just
	 * waits
	 *
	 * @param millis max wait time to process events
	 */
	public static void processUIEvents(final long millis) throws Exception {
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < millis) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			Display display = Display.getCurrent();
			if (display != null && !display.isDisposed()) {
				while (display.readAndDispatch()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					// loop until the queue is empty
				}
			} else {
				Thread.sleep(10);
			}
		}
	}

	/**
	 * Waits while given condition is {@code true} for a given amount of
	 * milliseconds. If the actual wait time exceeds given timeout and condition
	 * will be still {@code true}, throws AssertionFailedError with given
	 * message.
	 * <p>
	 * Will process UI events while waiting in UI thread, if called from
	 * background thread, just waits.
	 *
	 * @param condition function which will be evaluated while waiting
	 * @param timeout max wait time in milliseconds to wait on given condition
	 * @param errorMessage message which will be used to construct the failure
	 *            exception in case the condition will still return {@code true}
	 *            after given timeout
	 */
	public static void waitWhile(BooleanSupplier condition, long timeout, Supplier<String> errorMessage) throws Exception {
		long start = System.currentTimeMillis();
		Display display = Display.getCurrent();
		while (System.currentTimeMillis() - start < timeout && condition.getAsBoolean()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			if (display != null && !display.isDisposed()) {
				if (!display.readAndDispatch()) {
					Thread.sleep(0);
				}
			} else {
				Thread.sleep(5);
			}
		}
		Boolean stillTrue = condition.getAsBoolean();
		if (stillTrue) {
			fail(errorMessage.get());
		}
	}

	/**
	 * Waits while given condition is {@code true} for the time defined as
	 * {@code #TEST_TIMEOUT}. If the actual wait time exceeds that timeout and
	 * condition will be still {@code true}, throws {@link AssertionError} with
	 * given message.
	 * <p>
	 * Will process UI events while waiting in UI thread, if called from
	 * background thread, just waits.
	 * </p>
	 *
	 * @param condition function which will be evaluated while waiting
	 * @param errorMessage message which will be used to construct the failure
	 *            exception in case the condition will still return {@code true}
	 *            after given timeout
	 */
	public static void waitWhile(BooleanSupplier condition, Supplier<String> errorMessage) throws Exception {
		waitWhile(condition, DEFAULT_TIMEOUT, errorMessage);
	}

	/**
	 * A simplified variant of
	 * {@link #waitWhile(BooleanSupplier, long, Supplier)}.
	 * <p>
	 * Waits while given condition is {@code true} for a given amount of
	 * milliseconds.
	 * </p>
	 * Will process UI events while waiting in UI thread, if called from
	 * background thread, just waits.
	 *
	 * @param condition function which will be evaluated while waiting
	 * @param timeout max wait time in milliseconds to wait on given condition
	 * @return value of condition when method returned
	 */
	public static boolean waitWhile(Supplier<Boolean> condition, long timeout) throws Exception {
		Supplier<Boolean> nonNullCondition = condition != null ? condition : () -> true;
		long start = System.currentTimeMillis();
		Display display = Display.getCurrent();
		while (System.currentTimeMillis() - start < timeout && nonNullCondition.get()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			Thread.yield();
			if (display != null && !display.isDisposed()) {
				if (!display.readAndDispatch()) {
					Thread.sleep(1);
				}
			} else {
				Thread.sleep(5);
			}
		}
		return nonNullCondition.get();
	}

	/**
	 * Utility for waiting until the execution of jobs of any family has
	 * finished or timeout is reached. If no jobs are running, the method waits
	 * given minimum wait time. While this method is waiting for jobs, UI events
	 * are processed.
	 *
	 * @param owner name of the caller which will be logged as prefix if the
	 *            wait times out
	 * @param jobFamily the jobFamily to wait for
	 * @param minTimeMs minimum wait time in milliseconds
	 * @param maxTimeMs maximum wait time in milliseconds
	 * @return true if the method timed out, false if all the jobs terminated
	 *         before the timeout
	 */
	public static boolean waitForJobs(String owner, Object jobFamily, long minTimeMs, long maxTimeMs) {
		return waitForJobs(owner, jobFamily, minTimeMs, maxTimeMs, (Object[]) null);
	}

	/**
	 * Utility for waiting until the execution of jobs of any family has finished or timeout is reached. If no jobs are running, the method waits
	 * given minimum wait time. While this method is waiting for jobs, UI events are processed.
	 *
	 * @param owner
	 *            name of the caller which will be logged as prefix if the wait times out
	 * @param minTimeMs
	 *            minimum wait time in milliseconds
	 * @param maxTimeMs
	 *            maximum wait time in milliseconds
	 * @param excludedFamilies
	 *            optional list of job families to NOT wait for
	 *
	 * @return true if the method timed out, false if all the jobs terminated before the timeout
	 */
	public static boolean waitForJobs(String owner, long minTimeMs, long maxTimeMs, Object... excludedFamilies) {
		return waitForJobs(owner, null, minTimeMs, maxTimeMs, excludedFamilies);
	}

	public static boolean waitForJobs(String owner, Object jobFamily, long minTimeMs, long maxTimeMs, Object... excludedFamilies) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		if (maxTimeMs < minTimeMs) {
			throw new IllegalArgumentException("Max time is smaller as min time!");
		}
		Job.getJobManager().wakeUp(jobFamily);
		final long start = System.nanoTime();
		while (System.nanoTime() - start < minTimeMs * 1_000_000) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			processUIEvents();
			try {
				Thread.sleep(Math.min(10, minTimeMs));
			} catch (InterruptedException e) {
				// Uninterruptable
			}
		}
		while (!Job.getJobManager().isIdle()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			List<Job> jobs = getRunningOrWaitingJobs(jobFamily, excludedFamilies);
			if (jobs.isEmpty()) {
				// only uninteresting jobs running
				break;
			}
			jobs.forEach(Job::wakeUp);

			if (runningJobs.containsAll(jobs)) {
				// There are only jobs which runs already quite some time, don't
				// wait for them to avoid test timeouts
				dumpRunningOrWaitingJobs(owner, jobs);
				return true;
			}

			if (System.nanoTime() - start >= maxTimeMs * 1_000_000) {
				dumpRunningOrWaitingJobs(owner, jobs);
				return true;
			}
			processUIEvents();
			Thread.yield();
		}
		runningJobs.clear();
		return false;
	}
	static Set<Job> runningJobs = new LinkedHashSet<>();

	private static void dumpRunningOrWaitingJobs(String owner, List<Job> jobs) {
		String message = "Some job is still running or waiting to run: " + getDump(jobs); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		log(IStatus.ERROR, owner, message, new RuntimeException(message));
	}

	private static String getDump(List<Job> jobs) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		if (jobs.isEmpty()) {
			return "";
		}
		// clear "old" running jobs, we only remember most recent
		runningJobs.clear();
		StringBuilder sb = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		for (Job job : jobs) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			runningJobs.add(job);
			sb.append("\n'").append(job.toString()).append("'/"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			sb.append(job.getClass().getName()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			sb.append(":").append(JobManager.printState(job)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			Thread thread = job.getThread();
			if (thread != null) {
				ThreadInfo[] threadInfos = ManagementFactory.getThreadMXBean().getThreadInfo(new long[] {
						thread.threadId() }, true, true);
				if (threadInfos[0] != null) {
					sb.append("\nthread info: ").append(threadInfos[0]); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				}
			}
			sb.append(", "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		sb.setLength(sb.length() - 2);
		return sb.toString();
	}

	public static List<Job> getRunningOrWaitingJobs(Object jobFamily, Object... excludedFamilies) {
		List<Job> running = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
		Job[] jobs = Job.getJobManager().find(jobFamily);
		for (Job job : jobs) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			if (isRunningOrWaitingJob(job) && !belongsToFamilies(job, excludedFamilies)) {
				running.add(job);
			}
		}
		return running;
	}

	private static boolean isRunningOrWaitingJob(Job job) {
		int state = job.getState();
		return (state == Job.RUNNING || state == Job.WAITING);
	}

	private static boolean belongsToFamilies(Job job, Object... excludedFamilies) {
		if (excludedFamilies == null || excludedFamilies.length == 0) {
			return false;
		}
		for (Object family : excludedFamilies) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			if (job.belongsTo(family)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the launch manager.
	 *
	 * @return launch manager
	 */
	public static ILaunchManager getLaunchManager() {
		return DebugPlugin.getDefault().getLaunchManager();
	}

	/**
	 * Returns the singleton instance of the <code>LaunchConfigurationManager</code>
	 *
	 * @return the singleton instance of the <code>LaunchConfigurationManager</code>
	 */
	public static LaunchConfigurationManager getLaunchConfigurationManager() {
		return DebugUIPlugin.getDefault().getLaunchConfigurationManager();
	}

	/**
	 * Returns a launch configuration with the given name, creating one if required.
	 *
	 * @param configurationName configuration name
	 * @return launch configuration
	 */
	public static ILaunchConfiguration getLaunchConfiguration(String configurationName) throws CoreException {
		ILaunchManager manager = getLaunchManager();
		ILaunchConfiguration[] configurations = manager.getLaunchConfigurations();
		for (ILaunchConfiguration config : configurations) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			if (config.getName().equals(configurationName)) {
				return config;
			}
		}
		 ILaunchConfigurationType type = getLaunchManager().getLaunchConfigurationType(LaunchConfigurationTests.ID_TEST_LAUNCH_TYPE);
		 ILaunchConfigurationWorkingCopy wc = type.newInstance(null, configurationName);
		 ILaunchConfiguration saved = wc.doSave();
		 return saved;
	}

}
