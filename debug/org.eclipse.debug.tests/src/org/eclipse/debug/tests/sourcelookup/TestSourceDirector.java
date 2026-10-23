/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
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
package org.eclipse.debug.tests.sourcelookup;

import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupDirector;

public class TestSourceDirector extends AbstractSourceLookupDirector {


	@Override
	public Object getSourceElement(Object element) {
		if (element instanceof String) {
			return element.toString() + System.currentTimeMillis(); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		} else if (element instanceof IStackFrame frame) {
			return frame.getModelIdentifier() + System.currentTimeMillis(); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		}
		return super.getSourceElement(element);
	}

	@Override
	public void initializeParticipants() {
	}
}
