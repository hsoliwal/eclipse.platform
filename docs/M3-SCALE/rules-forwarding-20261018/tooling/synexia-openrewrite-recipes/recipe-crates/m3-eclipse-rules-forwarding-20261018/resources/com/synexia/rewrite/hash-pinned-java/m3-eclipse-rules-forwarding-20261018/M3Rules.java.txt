/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
 * Copyright (c) 2026 Hitesh Soliwal and Contributors to the Synexia Project.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM - Initial compound scheduling rule implementation
 *     Hitesh Soliwal and Contributors to the Synexia Project - M3 array owner
 *******************************************************************************/
package org.eclipse.core.internal.jobs.m3;

import java.util.Arrays;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;

/**
 * Internal array operations for the existing compound scheduling rule contract.
 * The facade retains the single final child array; this owner retains no state.
 * Rule predicates remain live callbacks and are never memoized.
 *
 * @noreference This class is not intended to be referenced by clients.
 */
public final class M3Rules {
	private M3Rules() {
	}

	public static ISchedulingRule combine(ISchedulingRule[] ruleArray) {
		ISchedulingRule result = null;
		for (ISchedulingRule element : ruleArray) {
			if (element == null) {
				continue;
			}
			if (result == null) {
				result = element;
				continue;
			}
			result = combine(result, element);
		}
		return result;
	}

	public static ISchedulingRule combine(ISchedulingRule rule1, ISchedulingRule rule2) {
		if (rule1 == rule2) {
			return rule1;
		}
		if (rule1 == null) {
			return rule2;
		}
		if (rule2 == null) {
			return rule1;
		}
		if (rule1.contains(rule2)) {
			return rule1;
		}
		if (rule2.contains(rule1)) {
			return rule2;
		}
		return new MultiRule(rule1, rule2);
	}

	/**
	 * Capture each nested projection once, in encounter order. A two-pass size
	 * calculation would repeat effectful getChildren() calls and is not valid.
	 */
	public static ISchedulingRule[] flatten(ISchedulingRule[] nestedRules) {
		ISchedulingRule[] flat = new ISchedulingRule[nestedRules.length];
		int size = 0;
		for (ISchedulingRule nested : nestedRules) {
			if (nested instanceof MultiRule multi) {
				ISchedulingRule[] children = multi.getChildren();
				flat = capacity(flat, size, children.length);
				System.arraycopy(children, 0, flat, size, children.length);
				size += children.length;
			} else {
				flat = capacity(flat, size, 1);
				flat[size++] = nested;
			}
		}
		return size == flat.length ? flat : Arrays.copyOf(flat, size);
	}

	private static ISchedulingRule[] capacity(ISchedulingRule[] flat, int used, int additional) {
		int minimum = used + additional;
		if (minimum < 0) {
			throw new OutOfMemoryError("Required array length is too large"); //$NON-NLS-1$
		}
		if (minimum <= flat.length) {
			return flat;
		}
		long preferred = (long) flat.length + Math.max(1, flat.length >> 1);
		int length = (int) Math.min(Integer.MAX_VALUE, Math.max(minimum, preferred));
		return Arrays.copyOf(flat, length);
	}

	public static ISchedulingRule[] children(ISchedulingRule[] rules) {
		return rules.clone();
	}

	public static boolean contains(MultiRule receiver, ISchedulingRule[] rules, ISchedulingRule rule) {
		if (receiver == rule) {
			return true;
		}
		if (rule instanceof MultiRule multi) {
			ISchedulingRule[] otherRules = multi.getChildren();
			for (ISchedulingRule other : otherRules) {
				boolean found = false;
				for (int mine = 0; !found && mine < rules.length; mine++) {
					found = rules[mine].contains(other);
				}
				if (!found) {
					return false;
				}
			}
			return true;
		}
		for (ISchedulingRule mine : rules) {
			if (mine.contains(rule)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isConflicting(MultiRule receiver, ISchedulingRule[] rules, ISchedulingRule rule) {
		if (receiver == rule) {
			return true;
		}
		if (rule instanceof MultiRule multi) {
			ISchedulingRule[] otherRules = multi.getChildren();
			for (ISchedulingRule other : otherRules) {
				for (ISchedulingRule mine : rules) {
					if (mine.isConflicting(other)) {
						return true;
					}
				}
			}
		} else {
			for (ISchedulingRule mine : rules) {
				if (mine.isConflicting(rule)) {
					return true;
				}
			}
		}
		return false;
	}

	public static String describe(ISchedulingRule[] rules) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("MultiRule["); //$NON-NLS-1$
		for (int index = 0; index < rules.length; index++) {
			buffer.append(rules[index]);
			if (index != rules.length - 1) {
				buffer.append(',');
			}
		}
		buffer.append(']');
		return buffer.toString();
	}
}
