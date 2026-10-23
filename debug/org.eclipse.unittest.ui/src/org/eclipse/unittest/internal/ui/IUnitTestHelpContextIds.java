/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
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
 *******************************************************************************/
package org.eclipse.unittest.internal.ui;

import org.eclipse.unittest.internal.UnitTestPlugin;

/**
 * Help context ids for the Unit Test UI.
 */
public interface IUnitTestHelpContextIds {
	String PREFIX = UnitTestPlugin.PLUGIN_ID + '.'; //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT

	// Actions
	String COPYTRACE_ACTION = PREFIX + "copy_trace_action_context"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
	String COPYFAILURELIST_ACTION = PREFIX + "copy_failure_list_action_context"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
	String ENABLEFILTER_ACTION = PREFIX + "enable_filter_action_context"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
	String OPENEDITORATLINE_ACTION = PREFIX + "open_editor_atline_action_context"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
	String OPENTEST_ACTION = PREFIX + "open_test_action_context"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
	String RERUN_ACTION = PREFIX + "rerun_test_action_context"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
	String GOTO_REFERENCED_TEST_ACTION_CONTEXT = PREFIX + "goto_referenced_test_action_context"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
	String OUTPUT_SCROLL_LOCK_ACTION = PREFIX + "scroll_lock"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT

	// view parts
	String RESULTS_VIEW = PREFIX + "results_view_context"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
	String RESULTS_VIEW_TOGGLE_ORIENTATION_ACTION = PREFIX + "results_view_toggle_call_mode_action_context"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT

	// Dialogs
	String TEST_SELECTION_DIALOG = PREFIX + "test_selection_context"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
	String RESULT_COMPARE_DIALOG = PREFIX + "result_compare_context"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT

}
