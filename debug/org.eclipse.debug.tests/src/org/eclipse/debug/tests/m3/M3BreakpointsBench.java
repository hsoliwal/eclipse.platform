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

import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.function.LongSupplier;

import org.eclipse.debug.internal.ui.views.breakpoints.BreakpointContainer;

/**
 * Breakpoints view model benchmark (OPTIMIZATION_PASS.md): for a view holding
 * {@code n} breakpoints, a delta of {@code k} added, removed and changed
 * breakpoints and the viewer's page requests. Reports wall time (median of the
 * repetitions), comparator calls, bytes allocated by the thread and the
 * retained heap of the model. Same script on the stock jar and on the lanes.
 * <p>
 * Usage: {@code M3BreakpointsBench <side> <n> <reps> <k>...} prints TSV rows
 * {@code side n grouped operation k median_ms comparator_calls alloc_bytes}.
 * </p>
 */
public final class M3BreakpointsBench {

	private static final com.sun.management.ThreadMXBean THREADS = (com.sun.management.ThreadMXBean) ManagementFactory
			.getThreadMXBean();

	private M3BreakpointsBench() {
	}

	public static void main(String[] args) {
		String side = args[0];
		int n = Integer.parseInt(args[1]);
		int reps = Integer.parseInt(args[2]);
		int[] ks = Arrays.stream(args, 3, args.length).mapToInt(Integer::parseInt).toArray();
		for (boolean grouped : new boolean[] { false, true }) {
			row(side, n, grouped, "build", n, reps, s -> 0, s -> { //$NON-NLS-1$
				s.add(n);
				return s.order.calls;
			});
			for (int k : ks) {
				row(side, n, grouped, "add", k, reps, setup(n), s -> delta(s, () -> { //$NON-NLS-1$
					s.add(k);
					return 0;
				}));
				row(side, n, grouped, "remove", k, reps, setup(n), s -> delta(s, () -> { //$NON-NLS-1$
					s.remove(k);
					return 0;
				}));
				row(side, n, grouped, "change+add1", k, reps, setup(n), s -> delta(s, () -> { //$NON-NLS-1$
					s.change(k);
					s.add(1);
					return 0;
				}));
			}
			row(side, n, grouped, "pages100x50", 100, reps, setup(n), s -> delta(s, () -> { //$NON-NLS-1$
				for (int p = 0; p < 100; p++) {
					s.pages(s.root, 50);
				}
				return 0;
			}));
		}
		retained(side, n);
	}

	interface Op {
		long run(M3BreakpointsScript s);
	}

	interface Setup {
		long prepare(M3BreakpointsScript s);
	}

	static Setup setup(int n) {
		return s -> {
			s.add(n);
			return 0;
		};
	}

	static long delta(M3BreakpointsScript s, LongSupplier op) {
		long before = s.order.calls;
		op.getAsLong();
		return s.order.calls - before;
	}

	static void row(String side, int n, boolean grouped, String name, int k, int reps, Setup setup, Op op) {
		long[] nanos = new long[reps];
		long calls = 0;
		long alloc = 0;
		for (int r = 0; r < reps; r++) {
			M3BreakpointsScript s = new M3BreakpointsScript(42 + r, grouped, Math.max(3, n / 50));
			setup.prepare(s);
			System.gc();
			long a0 = THREADS.getCurrentThreadAllocatedBytes();
			long t0 = System.nanoTime();
			calls = op.run(s);
			nanos[r] = System.nanoTime() - t0;
			alloc = THREADS.getCurrentThreadAllocatedBytes() - a0;
		}
		Arrays.sort(nanos);
		System.out.printf("%s\t%d\t%s\t%s\t%d\t%.3f\t%d\t%d%n", side, n, grouped ? "grouped" : "flat", name, k, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				nanos[reps / 2] / 1e6, calls, alloc);
	}

	static void retained(String side, int n) {
		for (boolean grouped : new boolean[] { false, true }) {
			M3BreakpointsScript s = new M3BreakpointsScript(7, grouped, Math.max(3, n / 50));
			s.add(n);
			long with = used();
			BreakpointContainer model = s.root;
			s.root = null;
			int children = model.getChildren().length;
			model = null;
			long without = used();
			// the breakpoints stay reachable through the script: the difference is the view model alone
			System.out.printf("%s\t%d\t%s\tretained_model_bytes\t%d\t0\t%d\t%d%n", side, n, grouped ? "grouped" : "flat", n, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					children, with - without);
		}
	}

	static long used() {
		for (int i = 0; i < 3; i++) {
			System.gc();
		}
		return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
	}
}
