// SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project
// SPDX-License-Identifier: Apache-2.0
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;

/** An external bundle exercises the public Jobs API through its actual OSGi wire. */
public final class MultiRuleOsgiClient {
    private MultiRuleOsgiClient() { }
    private static final class Rule implements ISchedulingRule {
        private final int mask;
        Rule(int mask) { this.mask = mask; }
        @Override public boolean contains(ISchedulingRule target) {
            return target instanceof Rule other && (mask & other.mask) == other.mask;
        }
        @Override public boolean isConflicting(ISchedulingRule target) {
            return target instanceof Rule other && (mask & other.mask) != 0;
        }
        @Override public String toString() { return Integer.toString(mask); }
    }
    private static void check(boolean condition) {
        if (!condition) throw new AssertionError("public Jobs operation failed across the OSGi wire");
    }
    public static void main(String[] ignored) {
        var a = new Rule(1);
        var b = new Rule(2);
        var c = new Rule(4);
        check(MultiRule.combine(a, null) == a);
        check(MultiRule.combine(null, b) == b);
        check(MultiRule.combine(new ISchedulingRule[] {null, a, a}) == a);
        var combined = (MultiRule) MultiRule.combine(a, b);
        check(combined.contains(a));
        check(combined.contains(b));
        check(!combined.contains(c));
        check(combined.isConflicting(b));
        check(!combined.isConflicting(c));
        check(combined.contains(new MultiRule(b, a)));
        check(combined.isConflicting(new MultiRule(c, b)));
        ISchedulingRule[] children = combined.getChildren();
        check(children.length == 2 && children[0] == a && children[1] == b);
        children[0] = null;
        check(combined.getChildren()[0] == a);
        var nested = new MultiRule(combined, c);
        check(nested.getChildren().length == 3);
        check("MultiRule[1,2,4]".equals(nested.toString()));
        System.out.println("MULTIRULE_OSGI_CLIENT assertions=14");
    }
}
