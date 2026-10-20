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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.internal.ui.breakpoints.provisional.IBreakpointOrganizer;
import org.eclipse.debug.internal.ui.model.elements.ElementContentProvider;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IModelDelta;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IPresentationContext;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IViewerUpdate;
import org.eclipse.debug.internal.ui.viewers.model.provisional.ModelDelta;
import org.eclipse.debug.internal.ui.views.breakpoints.BreakpointContainer;
import org.eclipse.debug.tests.m3.FakeBreakpoints.Bp;
import org.eclipse.debug.tests.m3.FakeBreakpoints.FileOrganizer;
import org.eclipse.debug.tests.m3.FakeBreakpoints.Order;

/**
 * A seeded script of Breakpoints view model operations, the same calls the
 * view's content provider makes: batches of added, removed and changed
 * breakpoints, sort changes (with and without the view's rebuild), moves
 * between containers, child counts and pages of children. Every step records
 * the delta, the container tree and the answers, so that the stock jar and the
 * lane implementation can be compared line by line (recorded differential).
 */
public final class M3BreakpointsScript {

	static final String INPUT = "input"; //$NON-NLS-1$

	final Random random;
	final Order order = new Order();
	final boolean grouped;
	final int files;
	final List<Bp> present = new ArrayList<>();
	BreakpointContainer root;
	int nextId;
	/** Full rebuilds requested by the script (sort changes the view answers with a rebuild): stays at the scripted count. */
	int rebuilds;
	private final Method changedHook;
	private final Method childCount;
	private final Method childPage;

	public M3BreakpointsScript(long seed, boolean grouped, int files) {
		this.random = new Random(seed);
		this.grouped = grouped;
		this.files = files;
		this.root = newRoot();
		this.changedHook = method(BreakpointContainer.class, "breakpointsChanged", IBreakpoint[].class); //$NON-NLS-1$
		this.childCount = method(ElementContentProvider.class, "getChildCount", Object.class, IPresentationContext.class, //$NON-NLS-1$
				IViewerUpdate.class);
		this.childPage = method(ElementContentProvider.class, "getChildren", Object.class, int.class, int.class, //$NON-NLS-1$
				IPresentationContext.class, IViewerUpdate.class);
	}

	/** @return whether the lane hook {@code breakpointsChanged} exists (the lane implementation is on the class path) */
	public boolean lanes() {
		return changedHook != null;
	}

	private static Method method(Class<?> type, String name, Class<?>... parameters) {
		try {
			Method m = type.getDeclaredMethod(name, parameters);
			m.setAccessible(true);
			return m;
		} catch (NoSuchMethodException e) {
			return null;
		}
	}

	BreakpointContainer newRoot() {
		IBreakpointOrganizer[] organizers = grouped ? new IBreakpointOrganizer[] { new FileOrganizer() } : null;
		BreakpointContainer container = new BreakpointContainer(organizers, order);
		container.initDefaultContainers(new ModelDelta(INPUT, IModelDelta.NO_CHANGE));
		return container;
	}

	Bp newBreakpoint() {
		int id = nextId++;
		return new Bp(id, random.nextInt(8) == 0 ? "m3.other" : "m3.java", "File" + random.nextInt(files) + ".java", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				1 + random.nextInt(5000));
	}

	/** The content provider's breakpointsAdded. */
	public ModelDelta add(int k) {
		ModelDelta delta = new ModelDelta(INPUT, 0, IModelDelta.NO_CHANGE, -1);
		for (int i = 0; i < k; i++) {
			Bp bp = newBreakpoint();
			present.add(bp);
			root.addBreakpoint(bp, delta);
		}
		delta.setChildCount(root.getChildren().length);
		return delta;
	}

	/** The content provider's breakpointsRemoved. */
	public ModelDelta remove(int k) {
		ModelDelta delta = new ModelDelta(INPUT, IModelDelta.NO_CHANGE);
		for (int i = 0; i < k && !present.isEmpty(); i++) {
			Bp bp = present.remove(random.nextInt(present.size()));
			root.removeBreakpoint(bp, delta);
		}
		return delta;
	}

	/** Lines move (an edit above them): the content provider's breakpointsChanged re-checks their order. */
	public void change(int k) {
		List<IBreakpoint> changed = new ArrayList<>();
		for (int i = 0; i < k && !present.isEmpty(); i++) {
			Bp bp = present.get(random.nextInt(present.size()));
			bp.line = 1 + random.nextInt(5000);
			changed.add(bp);
		}
		notifyChanged(changed.toArray(new IBreakpoint[0]));
	}

	void notifyChanged(IBreakpoint[] bps) {
		if (changedHook != null) {
			try {
				changedHook.invoke(root, (Object) bps);
			} catch (ReflectiveOperationException e) {
				throw new IllegalStateException(e);
			}
		}
	}

	/** The sort action: flips the order; with {@code rebuild} the view rebuilds its containers (sortContainers). */
	public void sort(boolean rebuild) {
		order.byLineDescending = !order.byLineDescending;
		order.epoch++;
		if (rebuild) {
			rebuilds++;
			root = newRoot();
			ModelDelta delta = new ModelDelta(INPUT, IModelDelta.NO_CHANGE);
			for (Bp bp : present) {
				root.addBreakpoint(bp, delta);
			}
		}
	}

	/** Drag and drop between categories: the static move helpers of BreakpointContainer. */
	public ModelDelta move() {
		ModelDelta delta = new ModelDelta(INPUT, IModelDelta.NO_CHANGE);
		BreakpointContainer[] containers = root.getContainers();
		if (containers.length < 2 || present.isEmpty()) {
			return delta;
		}
		BreakpointContainer from = containers[random.nextInt(containers.length)];
		BreakpointContainer to = containers[random.nextInt(containers.length)];
		IBreakpoint[] bps = from.getBreakpoints();
		if (bps.length == 0 || from == to) {
			return delta;
		}
		IBreakpoint bp = bps[random.nextInt(bps.length)];
		BreakpointContainer.removeBreakpoint(from, bp, delta.addNode(from, IModelDelta.STATE));
		if (!to.contains(bp)) {
			BreakpointContainer.addBreakpoint(to, bp, delta.addNode(to, IModelDelta.STATE));
		}
		return delta;
	}

	/** Empties one category container: the static removeAll helper. */
	public ModelDelta clearOne() {
		ModelDelta delta = new ModelDelta(INPUT, IModelDelta.NO_CHANGE);
		BreakpointContainer[] containers = root.getContainers();
		if (containers.length == 0) {
			return delta;
		}
		BreakpointContainer c = containers[random.nextInt(containers.length)];
		List<IBreakpoint> gone = Arrays.asList(c.getBreakpoints());
		BreakpointContainer.removeAll(c, delta);
		present.removeAll(gone);
		for (IBreakpoint bp : gone) {
			if (root.contains(bp)) {
				present.add((Bp) bp);
			}
		}
		return delta;
	}

	/** The viewer's child count and page requests, as the content provider answers them. */
	public String pages(BreakpointContainer c, int pageSize) {
		StringBuilder out = new StringBuilder();
		try {
			int n = (Integer) childCount.invoke(c, INPUT, null, null);
			out.append("count=").append(n); //$NON-NLS-1$
			for (int index : new int[] { 0, n / 2, Math.max(0, n - pageSize), n, n + 3 }) {
				Object[] page = (Object[]) childPage.invoke(c, INPUT, index, pageSize, null, null);
				out.append(" [").append(index).append(':'); //$NON-NLS-1$
				if (page == null) {
					out.append("null"); //$NON-NLS-1$
				} else {
					for (Object o : page) {
						out.append(' ').append(FakeBreakpoints.label(o));
					}
				}
				out.append(']');
			}
		} catch (ReflectiveOperationException e) {
			throw new IllegalStateException(e);
		}
		return out.toString();
	}

	/** Membership and index answers for present and absent breakpoints. */
	public String answers() {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < 4 && !present.isEmpty(); i++) {
			Bp bp = present.get(random.nextInt(present.size()));
			out.append(bp).append(" in=").append(root.contains(bp)).append(" idx=").append(root.getChildIndex(bp)) //$NON-NLS-1$ //$NON-NLS-2$
					.append(" containers=").append(root.getContainers(bp).length).append("; "); //$NON-NLS-1$ //$NON-NLS-2$
		}
		Bp absent = new Bp(-1 - random.nextInt(100), "m3.java", "File0.java", 1); //$NON-NLS-1$ //$NON-NLS-2$
		out.append(absent).append(" in=").append(root.contains(absent)).append(" idx=").append(root.getChildIndex(absent)); //$NON-NLS-1$ //$NON-NLS-2$
		return out.toString();
	}
}
