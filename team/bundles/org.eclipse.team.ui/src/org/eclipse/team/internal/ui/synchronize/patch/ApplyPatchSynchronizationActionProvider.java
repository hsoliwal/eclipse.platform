/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.team.internal.ui.synchronize.patch;

import org.eclipse.compare.internal.patch.HunkDiffNode;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.team.internal.ui.mapping.ResourceMarkAsMergedHandler;
import org.eclipse.team.internal.ui.mapping.ResourceMergeHandler;
import org.eclipse.team.ui.mapping.SynchronizationActionProvider;
import org.eclipse.team.ui.synchronize.ISynchronizePageConfiguration;

public class ApplyPatchSynchronizationActionProvider extends
		SynchronizationActionProvider {

	public ApplyPatchSynchronizationActionProvider() {
		super();
	}

	@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	protected void initialize() {
		super.initialize();
		final ISynchronizePageConfiguration configuration = getSynchronizePageConfiguration();
		// Use custom handlers, disabled for hunks.
		registerHandler(MERGE_ACTION_ID, new ResourceMergeHandler( //FLLDCIM: MARK — T06  ANONYMOUS
				configuration, false) { //FLLDCIM: MARK — T06  IMPLEMENTS
			@Override
			public void updateEnablement(IStructuredSelection selection) {
				super.updateEnablement(selection);
				// disable merge for hunks
				Object[] elements = getOperation().getElements();
				for (Object element : elements) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					if (element instanceof HunkDiffNode) {
						setEnabled(false);
						return;
					}
				}
			}
		});
		registerHandler(MARK_AS_MERGE_ACTION_ID,
				new ResourceMarkAsMergedHandler(configuration) { //FLLDCIM: MARK — T06  ANONYMOUS
			@Override
			public void updateEnablement(IStructuredSelection selection) {
				super.updateEnablement(selection);
				// disable mark as merged for hunks
				Object[] elements = getOperation().getElements();
				for (Object element : elements) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					if (element instanceof HunkDiffNode) {
						setEnabled(false);
						return;
					}
				}
			}
		});
		// 'Overwrite' action is not shown, see
		// ApplyPatchModelSynchronizeParticipant.ApplyPatchModelSynchronizeParticipantActionGroup.addToContextMenu(String,
		// Action, IMenuManager)
	}
}
