/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM - Initial API and implementation
 *     Hitesh Soliwal and Contributors to the Synexia Project - M3 rule forwarding
 *******************************************************************************/
package org.eclipse.core.runtime.jobs;

// Modified 2026: forward compound rule behavior to the internal M3 array owner.

import org.eclipse.core.internal.jobs.m3.M3Rules;

/**
 * A MultiRule is a compound scheduling rule that represents a fixed group of child
 * scheduling rules.  A MultiRule conflicts with another rule if any of its children conflict
 * with that rule.  More formally, a compound rule represents a logical intersection
 * of its child rules with respect to the <code>isConflicting</code> equivalence
 * relation.
 * <p>
 * A MultiRule will never contain other MultiRules as children.  If a MultiRule is provided
 * as a child, its children will be added instead.
 * </p>
 *
 * @since 3.0
 * @noextend This class is not intended to be subclassed by clients.
 */
public class MultiRule implements ISchedulingRule {
	private final ISchedulingRule[] rules;

	/**
	 * Returns a scheduling rule that encompasses all provided rules.  The resulting
	 * rule may or may not be an instance of <code>MultiRule</code>.  If all
	 * provided rules are <code>null</code> then the result will be
	 * <code>null</code>.
	 *
	 * @param ruleArray An array of scheduling rules, some of which may be <code>null</code>
	 * @return a combined scheduling rule, or <code>null</code>
	 * @since 3.1
	 */
	public static ISchedulingRule combine(ISchedulingRule[] ruleArray) {
		return M3Rules.combine(ruleArray);
	}

	/**
	 * Returns a scheduling rule that encompasses both provided rules.  The resulting
	 * rule may or may not be an instance of <code>MultiRule</code>.  If both
	 * provided rules are <code>null</code> then the result will be
	 * <code>null</code>.
	 *
	 * @param rule1 a scheduling rule, or <code>null</code>
	 * @param rule2 another scheduling rule, or <code>null</code>
	 * @return a combined scheduling rule, or <code>null</code>
	 */
	public static ISchedulingRule combine(ISchedulingRule rule1, ISchedulingRule rule2) {
		return M3Rules.combine(rule1, rule2);
	}

	/**
	 * Creates a new scheduling rule that composes a set of nested rules.
	 *
	 * @param nestedRules the nested rules for this compound rule.
	 */
	public MultiRule(ISchedulingRule ... nestedRules) {
		this.rules = M3Rules.flatten(nestedRules);
	}

	/**
	 * Returns the child rules within this rule.
	 * @return the child rules
	 */
	public ISchedulingRule[] getChildren() {
		return M3Rules.children(rules);
	}

	@Override
	public boolean contains(ISchedulingRule rule) {
		return M3Rules.contains(this, rules, rule);
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		return M3Rules.isConflicting(this, rules, rule);
	}

	/*
	 * For debugging purposes only.
	 */
	@Override
	public String toString() {
		return M3Rules.describe(rules);
	}
}
