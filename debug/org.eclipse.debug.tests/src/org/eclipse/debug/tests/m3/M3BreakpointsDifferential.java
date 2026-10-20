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

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.debug.internal.ui.views.breakpoints.BreakpointContainer;

/**
 * Recorded differential of the Breakpoints view model. Run once with the stock
 * {@code org.eclipse.debug.ui} jar and once with the lane classes in front of
 * it; the two traces must be identical. Each line is one step of a seeded
 * script: the operation, the delta it produced, the container tree, the
 * viewer's count and page answers, membership and index answers.
 * <p>
 * Usage: {@code M3BreakpointsDifferential <out-file> <seeds> <steps>}
 * </p>
 */
public final class M3BreakpointsDifferential {

	private M3BreakpointsDifferential() {
	}

	public static void main(String[] args) throws IOException {
		Path out = Path.of(args[0]);
		int seeds = Integer.parseInt(args[1]);
		int steps = Integer.parseInt(args[2]);
		try (Writer w = Files.newBufferedWriter(out, StandardCharsets.UTF_8)) {
			for (int seed = 1; seed <= seeds; seed++) {
				for (boolean grouped : new boolean[] { false, true }) {
					run(w, seed, grouped, steps);
				}
			}
		}
	}

	static void run(Writer w, long seed, boolean grouped, int steps) throws IOException {
		M3BreakpointsScript s = new M3BreakpointsScript(seed, grouped, 3 + (int) (seed % 7));
		w.write("== seed " + seed + (grouped ? " grouped" : " flat") + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		for (int step = 0; step < steps; step++) {
			StringBuilder line = new StringBuilder();
			int op = s.random.nextInt(100);
			if (op < 35) {
				int k = 1 + s.random.nextInt(op < 5 ? 60 : 6);
				line.append("add ").append(k).append('\n'); //$NON-NLS-1$
				FakeBreakpoints.dump(s.add(k), "  ", line); //$NON-NLS-1$
			} else if (op < 55) {
				int k = 1 + s.random.nextInt(4);
				line.append("remove ").append(k).append('\n'); //$NON-NLS-1$
				FakeBreakpoints.dump(s.remove(k), "  ", line); //$NON-NLS-1$
			} else if (op < 75) {
				int k = 1 + s.random.nextInt(5);
				line.append("change ").append(k).append('\n'); //$NON-NLS-1$
				s.change(k);
			} else if (op < 80) {
				boolean rebuild = s.random.nextBoolean();
				line.append("sort rebuild=").append(rebuild).append('\n'); //$NON-NLS-1$
				s.sort(rebuild);
			} else if (op < 90) {
				line.append("move\n"); //$NON-NLS-1$
				FakeBreakpoints.dump(s.move(), "  ", line); //$NON-NLS-1$
			} else if (op < 93) {
				line.append("clearOne\n"); //$NON-NLS-1$
				FakeBreakpoints.dump(s.clearOne(), "  ", line); //$NON-NLS-1$
			} else {
				line.append("pages ").append(s.pages(s.root, 1 + s.random.nextInt(9))).append('\n'); //$NON-NLS-1$
				for (BreakpointContainer c : s.root.getContainers()) {
					line.append("  pages ").append(FakeBreakpoints.label(c)).append(' ').append(s.pages(c, 3)).append('\n'); //$NON-NLS-1$
				}
			}
			FakeBreakpoints.dump(s.root, "  ", line); //$NON-NLS-1$
			line.append("  answers ").append(s.answers()).append('\n'); //$NON-NLS-1$
			w.write(line.toString());
		}
		w.write("rebuilds " + s.rebuilds + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
