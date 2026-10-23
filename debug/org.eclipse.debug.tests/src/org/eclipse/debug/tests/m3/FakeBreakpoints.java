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

import java.util.Objects;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.internal.ui.breakpoints.provisional.IBreakpointOrganizer;
import org.eclipse.debug.internal.ui.views.breakpoints.BreakpointContainer;
import org.eclipse.debug.internal.ui.views.breakpoints.ElementComparator;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IModelDelta;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;

/**
 * Workspace-free stand-ins for the Breakpoints view model: breakpoints with a
 * marker-like identity, a comparator with the cost profile of
 * {@code ElementComparator} (model id and marker type text, then label and
 * line), a file organizer and a deterministic delta printer. They let the view
 * model run in a plain JVM against the stock jar and against the lane
 * implementation with the same script.
 */
public final class FakeBreakpoints {

	private FakeBreakpoints() {
	}

	/** A breakpoint whose identity is its id (as a marker id), with a mutable line and file. */
	public static final class Bp implements IBreakpoint {
		final int id;
		String model;
		String file;
		int line;

		public Bp(int id, String model, String file, int line) {
			this.id = id;
			this.model = model;
			this.file = file;
			this.line = line;
		}

		@Override
		public boolean equals(Object o) {
			return o instanceof Bp b && b.id == id;
		}

		@Override
		public int hashCode() {
			return Integer.hashCode(id) * 31 + 7;
		}

		@Override
		public String toString() {
			return "bp" + id; //$NON-NLS-1$
		}

		String label() {
			return file + " [line: " + line + "]"; //$NON-NLS-1$ //$NON-NLS-2$
		}

		@Override
		public <T> T getAdapter(Class<T> adapter) {
			return null;
		}

		@Override
		public void delete() {
		}

		@Override
		public IMarker getMarker() {
			return null;
		}

		@Override
		public void setMarker(IMarker marker) {
		}

		@Override
		public String getModelIdentifier() {
			return model;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

		@Override
		public void setEnabled(boolean enabled) {
		}

		@Override
		public boolean isRegistered() {
			return true;
		}

		@Override
		public void setRegistered(boolean registered) {
		}

		@Override
		public boolean isPersisted() {
			return true;
		}

		@Override
		public void setPersisted(boolean registered) {
		}
	}

	/** A category with value identity, like a file or project category. */
	public static final class Category implements IAdaptable {
		final String name;

		public Category(String name) {
			this.name = name;
		}

		@Override
		public boolean equals(Object o) {
			return o instanceof Category c && c.name.equals(name);
		}

		@Override
		public int hashCode() {
			return name.hashCode();
		}

		@Override
		public String toString() {
			return name;
		}

		@Override
		public <T> T getAdapter(Class<T> adapter) {
			return null;
		}
	}

	/**
	 * Orders like {@code ElementComparator} for breakpoints (model id and type
	 * text first, then the label up to the line, then the line number) and
	 * containers by category name. It counts its calls, and its order epoch can
	 * be changed to model a sort preference change.
	 */
	public static final class Order extends ElementComparator {
		public long calls;
		public int epoch;
		public boolean byLineDescending;

		public Order() {
			super(null);
		}

		@Override
		public int compare(Object a, Object b) {
			calls++;
			if (a instanceof Bp x && b instanceof Bp y) {
				int r = (x.model + " " + "lineBreakpointMarker").compareTo(y.model + " " + "lineBreakpointMarker"); //$NON-NLS-1$ //$NON-NLS-2$
				if (r != 0) {
					return r;
				}
				String l1 = x.label();
				String l2 = y.label();
				String f1 = l1.substring(0, l1.indexOf(':'));
				String f2 = l2.substring(0, l2.indexOf(':'));
				r = f1.compareTo(f2);
				if (r != 0) {
					return r;
				}
				r = Integer.compare(x.line, y.line);
				return byLineDescending ? -r : r;
			}
			if (a instanceof BreakpointContainer c1 && b instanceof BreakpointContainer c2) {
				return String.valueOf(c1.getCategory()).compareTo(String.valueOf(c2.getCategory()));
			}
			return -1;
		}

		/** The lane implementation reads this to know when the order changed. */
		protected int orderEpoch() {
			return epoch;
		}
	}

	/** Groups breakpoints by file; allocates the category array per call like the platform organizers. */
	public static final class FileOrganizer implements IBreakpointOrganizer {
		@Override
		public IAdaptable[] getCategories(IBreakpoint breakpoint) {
			return new IAdaptable[] { new Category(((Bp) breakpoint).file) };
		}

		@Override
		public IAdaptable[] getCategories() {
			return null;
		}

		@Override
		public String getLabel() {
			return "File"; //$NON-NLS-1$
		}

		@Override
		public ImageDescriptor getImageDescriptor() {
			return null;
		}

		@Override
		public String getIdentifier() {
			return "m3.file"; //$NON-NLS-1$
		}

		@Override
		public String getOthersLabel() {
			return "Others"; //$NON-NLS-1$
		}

		@Override
		public void addPropertyChangeListener(IPropertyChangeListener listener) {
		}

		@Override
		public void removePropertyChangeListener(IPropertyChangeListener listener) {
		}

		@Override
		public void addBreakpoint(IBreakpoint breakpoint, IAdaptable category) {
		}

		@Override
		public void removeBreakpoint(IBreakpoint breakpoint, IAdaptable category) {
		}

		@Override
		public boolean canAdd(IBreakpoint breakpoint, IAdaptable category) {
			return true;
		}

		@Override
		public boolean canRemove(IBreakpoint breakpoint, IAdaptable category) {
			return true;
		}

		@Override
		public void dispose() {
		}
	}

	/** @return a stable label for a delta element */
	static String label(Object element) {
		if (element instanceof BreakpointContainer c) {
			return "C[" + c.getCategory() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		return Objects.toString(element);
	}

	/** Appends a deterministic dump of a delta tree. */
	static void dump(IModelDelta delta, String indent, StringBuilder out) {
		out.append(indent).append(label(delta.getElement())).append(" f=").append(delta.getFlags()) //$NON-NLS-1$
				.append(" i=").append(delta.getIndex()).append(" n=").append(delta.getChildCount()).append('\n'); //$NON-NLS-1$ //$NON-NLS-2$
		for (IModelDelta child : delta.getChildDeltas()) {
			dump(child, indent + "  ", out); //$NON-NLS-1$
		}
	}

	/** Appends a deterministic dump of a container tree. */
	static void dump(BreakpointContainer container, String indent, StringBuilder out) {
		out.append(indent).append(label(container)).append(" bps=").append(container.getBreakpoints().length).append(':'); //$NON-NLS-1$
		for (Object child : container.getChildren()) {
			if (!(child instanceof BreakpointContainer)) {
				out.append(' ').append(label(child));
			}
		}
		out.append('\n');
		for (BreakpointContainer child : container.getContainers()) {
			dump(child, indent + "  ", out); //$NON-NLS-1$
		}
	}
}
