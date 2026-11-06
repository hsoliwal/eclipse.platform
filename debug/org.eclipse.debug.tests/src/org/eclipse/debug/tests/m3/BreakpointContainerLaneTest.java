/*******************************************************************************
 * Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0, or (at your option) the Eclipse
 * Public License 2.0, which are available at
 * https://www.apache.org/licenses/LICENSE-2.0 and
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
 *
 * Contributors:
 *     Hitesh Soliwal (Synexia) - initial API and implementation
 *******************************************************************************/
package org.eclipse.debug.tests.m3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.debug.internal.ui.viewers.model.provisional.IModelDelta;
import org.eclipse.debug.internal.ui.viewers.model.provisional.ModelDelta;
import org.eclipse.debug.internal.ui.views.breakpoints.BreakpointContainer;
import org.eclipse.debug.tests.m3.FakeBreakpoints.Bp;
import org.junit.jupiter.api.Test;

/**
 * The Breakpoints view container path the recorded differential does not
 * reach: a container without a comparator (insertion order). The stock
 * comparator's order epoch reads the Debug UI preference store and is covered
 * only in a running workbench; the headless harness overrides it.
 */
public class BreakpointContainerLaneTest {

	@Test
	public void withoutComparatorBreakpointsKeepInsertionOrder() {
		BreakpointContainer container = new BreakpointContainer(null, null);
		ModelDelta delta = new ModelDelta("input", IModelDelta.NO_CHANGE); //$NON-NLS-1$
		Bp a = new Bp(1, "m", "B.java", 9); //$NON-NLS-1$ //$NON-NLS-2$
		Bp b = new Bp(2, "m", "A.java", 1); //$NON-NLS-1$ //$NON-NLS-2$
		container.addBreakpoint(a, delta);
		container.addBreakpoint(b, delta);
		container.addBreakpoint(a, delta); // already contained: ignored
		assertArrayEquals(new Object[] { a, b }, container.getChildren());
		assertEquals(2, container.getChildrenCount());
		assertArrayEquals(new Object[] { b }, container.getChildrenRange(1, 5));
	}

}
