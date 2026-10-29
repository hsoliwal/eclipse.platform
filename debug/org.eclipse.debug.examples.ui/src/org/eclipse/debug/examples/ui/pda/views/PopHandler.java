/*******************************************************************************
 * Copyright (c) 2008, 2018 Wind River Systems and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Wind River Systems - initial API and implementation
 *     IBM Corporation - bug fixing
 *******************************************************************************/
package org.eclipse.debug.examples.ui.pda.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.examples.core.pda.model.PDAThread;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * Pops a selected value off the data stack. The selection does <b>not</b> have to be
 * the top element on the stack.
 */
public class PopHandler extends AbstractDataStackViewHandler {

	@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	protected void doExecute(DataStackView view, PDAThread thread, ISelection selection) throws ExecutionException {
		TreeViewer viewer = (TreeViewer)view.getViewer();
		Object popee = selection instanceof IStructuredSelection i2
			? i2.getFirstElement() : null;
		if (popee != null) {
			try {
				IValue[] stack = thread.getDataStack();
				List<IValue> restore = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
				for (int i = 0; i < stack.length; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					Object value = stack[i];
					if (popee.equals(value)) {
						// pop & stop
						thread.popData();
						break;
					} else {
						// remember value to push back on
						restore.add(thread.popData());
					}
				}
				while (!restore.isEmpty()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					IValue value = restore.remove(restore.size() - 1);
					thread.pushData(value.getValueString());
				}
			} catch (DebugException e) {
				throw new ExecutionException("Failed to execute push command", e); //$NON-NLS-1$
			}
			viewer.refresh(); //FLLDCIM: MARK — M21 FULL_REBUILD_TO_DELTA CALL
		}
	}

}
