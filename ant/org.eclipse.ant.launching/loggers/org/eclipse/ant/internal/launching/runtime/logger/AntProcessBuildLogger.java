/*******************************************************************************
 * Copyright (c) 2000, 2025 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Matt Conway - Patch for Bug 28052
 *******************************************************************************/

package org.eclipse.ant.internal.launching.runtime.logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;
import org.eclipse.ant.internal.core.AbstractEclipseBuildLogger;
import org.eclipse.ant.internal.core.IAntCoreConstants;
import org.eclipse.ant.internal.launching.AntLaunch;
import org.eclipse.ant.internal.launching.AntLaunching;
import org.eclipse.ant.internal.launching.AntLaunchingUtil;
import org.eclipse.ant.internal.launching.launchConfigurations.AntProcess;
import org.eclipse.ant.internal.launching.launchConfigurations.AntStreamMonitor;
import org.eclipse.ant.internal.launching.launchConfigurations.AntStreamsProxy;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;

public class AntProcessBuildLogger extends NullBuildLogger {

	private long fStartTime;

	/**
	 * Associated process - discovered as needed to log messages
	 */
	private AntProcess fProcess = null;

	protected void logMessage(String message, BuildEvent event, int overridePriority) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		int priority = overridePriority;
		if (priority == -1) {
			priority = event.getPriority();
		}

		if (priority > getMessageOutputLevel()) {
			return;
		}
		AntProcess antProcess = getAntProcess(fProcessId);
		if (antProcess == null) {
			return;
		}

		StringBuffer fullMessage = new StringBuffer();
		if (!loggingToLogFile()) {
			fullMessage.append(System.lineSeparator());
		}
		if (event.getException() == null && event.getTask() != null && !fEmacsMode) {
			adornMessage(event, fullMessage);
		} else {
			fullMessage.append(message);
		}
		message = fullMessage.toString();

		if (loggingToLogFile()) {
			logMessageToLogFile(message, priority);
		} else {
			AntStreamMonitor monitor = getMonitor(priority);
			monitor.append(message);
		}
	}

	/**
	 * Builds a right justified task prefix for the given build event, placing it in the given string buffer. Creates the hyperlinks for the task
	 * prefix.
	 *
	 * @param event
	 *            build event
	 * @param fullMessage
	 *            buffer to place task prefix in
	 */
	private void adornMessage(BuildEvent event, StringBuffer fullMessage) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		String name = event.getTask().getTaskName();
		if (name == null) {
			name = "null"; //$NON-NLS-1$
		}
		Location location = event.getTask().getLocation();
		StringBuffer column = new StringBuffer();
		int size = AntLaunching.LEFT_COLUMN_SIZE - (name.length() + 3);
		for (int i = 0; i < size; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			column.append(' ');
		}
		StringBuilder labelBuff = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		labelBuff.append('['); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		labelBuff.append(name); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		labelBuff.append("] "); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		int offset = Math.max(size, 0) + 1;
		String label = labelBuff.toString();
		if (event.getMessage() == null) {
			return;
		}
		try {
			BufferedReader r = new BufferedReader(new StringReader(event.getMessage()));
			String line = r.readLine();
			fullMessage.append(column);
			appendAndLink(fullMessage, location, label, offset, line);
			line = r.readLine();
			while (line != null) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				fullMessage.append(System.lineSeparator());
				fullMessage.append(column);
				appendAndLink(fullMessage, location, label, offset, line);
				line = r.readLine();
			}
		}
		catch (IOException e) {
			if (event.getMessage() != null) {
				fullMessage.append(label).append(event.getMessage());
			}
		}
	}

	private void appendAndLink(StringBuffer fullMessage, Location location, String label, int offset, String line) {
		fullMessage.append(label);
		fullMessage.append(line);
		if (location != null) {
			String newLine = (label + line).trim(); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
			IRegion region = new Region(offset, label.length() - 3); // only
			// want
			// the
			// name
			// length
			// "[name] "
			AntProcess antProcess = getAntProcess(fProcessId);
			AntLaunch antLaunch = (AntLaunch) antProcess.getLaunch();
			antLaunch.addLinkDescriptor(newLine, location.getFileName(), location.getLineNumber(), region.getOffset(), region.getLength());
		}
	}

	private AntStreamMonitor getMonitor(int priority) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		AntStreamsProxy proxy = (AntStreamsProxy) fProcess.getStreamsProxy();
		AntStreamMonitor monitor = null;
		switch (priority) {
			case Project.MSG_INFO:
				monitor = (AntStreamMonitor) proxy.getOutputStreamMonitor();
				break;
			case Project.MSG_ERR:
				monitor = (AntStreamMonitor) proxy.getErrorStreamMonitor();
				break;
			case Project.MSG_DEBUG:
				monitor = (AntStreamMonitor) proxy.getDebugStreamMonitor();
				break;
			case Project.MSG_WARN:
				monitor = (AntStreamMonitor) proxy.getWarningStreamMonitor();
				break;
			case Project.MSG_VERBOSE:
				monitor = (AntStreamMonitor) proxy.getVerboseStreamMonitor();
				break;
			default:
				break;
		}
		return monitor;
	}

	private void logMessageToLogFile(String message, int priority) {
		if (priority == Project.MSG_ERR) {
			getErrorPrintStream().println(message);
		} else {
			getOutputPrintStream().println(message);
		}
	}

	/**
	 * Returns the associated Ant process, finding it if necessary, if not already found.
	 */
	protected AntProcess getAntProcess(String processId) {
		if (fProcess == null && processId != null) {
			for (IProcess process : DebugPlugin.getDefault().getLaunchManager().getProcesses()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				if (process instanceof AntProcess && processId.equals(process.getAttribute(AbstractEclipseBuildLogger.ANT_PROCESS_ID))) {
					fProcess = (AntProcess) process;
					break;
				}
			}
		}
		return fProcess;
	}

	@Override
	public void buildStarted(BuildEvent event) {
		fStartTime = System.currentTimeMillis();
	}

	@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void buildFinished(BuildEvent event) {
		String message = handleException(event);
		if (message != null) {
			try {
				BufferedReader r = new BufferedReader(new StringReader(message));
				String line = r.readLine();
				logMessage(line, event, Project.MSG_ERR);
				line = r.readLine();
				AntProcess antProcess = getAntProcess(fProcessId);
				while (line != null) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					logMessage(line, event, Project.MSG_ERR);
					if (!message.startsWith("Total time:")) { //$NON-NLS-1$
						AntLaunchingUtil.linkBuildFailedMessage(line, antProcess);
					}
					line = r.readLine();
				}
				logMessage(IAntCoreConstants.EMPTY_STRING, event, Project.MSG_ERR);
			}
			catch (IOException e) {
				// do nothing
			}
		}
		fHandledException = null;
		if (!(event.getException() instanceof OperationCanceledException)) {
			logMessage(getTimeString(System.currentTimeMillis() - fStartTime), event, fMessageOutputLevel);
		}
		fProcess = null;
		event.getProject().removeBuildListener(this);
	}

	private String getTimeString(long milliseconds) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		long seconds = milliseconds / 1000;
		long minutes = seconds / 60;
		seconds = seconds % 60;

		StringBuilder result = new StringBuilder(RuntimeMessages.AntProcessBuildLogger_Total_time); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		if (minutes > 0) {
			result.append(minutes); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			if (minutes > 1) {
				result.append(RuntimeMessages.AntProcessBuildLogger__minutes_2); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			} else {
				result.append(RuntimeMessages.AntProcessBuildLogger__minute_3); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			}
		}
		if (seconds > 0) {
			if (minutes > 0) {
				result.append(' '); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			}
			result.append(seconds); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

			if (seconds > 1) {
				result.append(RuntimeMessages.AntProcessBuildLogger__seconds_4); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			} else {
				result.append(RuntimeMessages.AntProcessBuildLogger__second_5); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			}
		}
		if (seconds == 0 && minutes == 0) {
			result.append(milliseconds); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			result.append(RuntimeMessages.AntProcessBuildLogger__milliseconds_6); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}

		result.append(System.lineSeparator()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		return result.toString();
	}

	@Override
	public void messageLogged(BuildEvent event) {
		if (event.getPriority() > getMessageOutputLevel()) {
			return;
		}
		if (event.getMessage() != null && event.getMessage().length() > 0) {
			logMessage(event.getMessage(), event, -1);
		}
	}

	@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void targetStarted(BuildEvent event) {
		if (Project.MSG_INFO > getMessageOutputLevel()) {
			return;
		}
		Target target = event.getTarget();
		StringBuilder msg = new StringBuilder(System.lineSeparator()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		String targetName = target.getName();
		msg.append(targetName); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		msg.append(':'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		String message = msg.toString();
		Location location = target.getLocation();
		if (location != null && location != Location.UNKNOWN_LOCATION) {
			IRegion region = new Region(0, targetName.length());
			AntProcess antProcess = getAntProcess(fProcessId);
			AntLaunch antLaunch = (AntLaunch) antProcess.getLaunch();
			antLaunch.addLinkDescriptor(message, location.getFileName(), location.getLineNumber(), region.getOffset(), region.getLength());
		}
		logMessage(message, event, Project.MSG_INFO);
	}

	private boolean loggingToLogFile() {
		// check if user has designated to log to a log file
		return getErrorPrintStream() != null && getErrorPrintStream() != System.err;
	}
}
