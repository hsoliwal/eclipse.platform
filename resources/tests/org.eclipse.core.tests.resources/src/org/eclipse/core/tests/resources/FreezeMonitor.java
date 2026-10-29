/*******************************************************************************
 * Copyright (c) 2016 Google, Inc and others.
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
 *******************************************************************************/

package org.eclipse.core.tests.resources;

import java.lang.management.*;
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.jobs.Job;

public class FreezeMonitor {

	public static final long FROZEN_TEST_TIMEOUT_MS = 60_000;

	private static /* @Nullable */ Job monitorJob;

	/**
	 * Will dump JVM threads if test runs over one minute
	 */
	public static void expectCompletionInAMinute() {
		expectCompletionIn(FROZEN_TEST_TIMEOUT_MS);
	}

	/**
	 * Will dump JVM threads if test runs over given time
	 */
	public static void expectCompletionIn(final long timeout) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		done();
		monitorJob = new Job("FreezeMonitor") {
			@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
			public IStatus run(IProgressMonitor monitor) {
				if (monitor.isCanceled()) {
					return Status.CANCEL_STATUS;
				}
				StringBuilder result = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
				result.append("Possible frozen test case\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				ThreadMXBean threadStuff = ManagementFactory.getThreadMXBean();
				ThreadInfo[] allThreads = threadStuff.getThreadInfo(threadStuff.getAllThreadIds(), 200);
				for (ThreadInfo threadInfo : allThreads) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					if (threadInfo == null) {
						continue;
					}
					result.append("\""); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					result.append(threadInfo.getThreadName()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					result.append("\": "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					result.append(threadInfo.getThreadState()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					result.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					final StackTraceElement[] elements = threadInfo.getStackTrace();
					for (StackTraceElement element : elements) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
						result.append("    "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
						result.append(element); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
						result.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					}
					result.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				}
				System.out.println(result.toString());
				return Status.OK_STATUS;
			}

			@Override
			public boolean belongsTo(Object family) {
				return FreezeMonitor.class == family;
			}
		};
		monitorJob.schedule(timeout);
	}

	public static void done() {
		if (monitorJob != null) {
			monitorJob.cancel();
			monitorJob = null;
		}
	}
}