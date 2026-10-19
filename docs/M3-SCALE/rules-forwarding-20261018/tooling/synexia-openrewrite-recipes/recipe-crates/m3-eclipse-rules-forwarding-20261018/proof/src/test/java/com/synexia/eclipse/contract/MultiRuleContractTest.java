// SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project
// SPDX-License-Identifier: Apache-2.0
package com.synexia.eclipse.contract;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.junit.jupiter.api.Test;

/** Frozen client observations, executed unchanged against the donor and generated owner. */
final class MultiRuleContractTest {
    private static final class ProbeFailure extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }
    private static final class Rule implements ISchedulingRule {
        final String name;
        int mask;
        final List<String> trace;
        ProbeFailure failure;
        ProbeFailure debugFailure;
        boolean acceptsNull;
        Rule(String name, int mask, List<String> trace) {
            this.name = name;
            this.mask = mask;
            this.trace = trace;
        }
        private void observe(String operation, ISchedulingRule target) {
            if (trace != null) {
                String targetName = target == null ? "null" : target instanceof Rule r ? r.name : "multi";
                trace.add(name + "." + operation + "(" + targetName + ")");
            }
            if (failure != null) throw failure;
        }
        @Override public boolean contains(ISchedulingRule target) {
            observe("contains", target);
            return target == null ? acceptsNull : target instanceof Rule r && (mask & r.mask) == r.mask;
        }
        @Override public boolean isConflicting(ISchedulingRule target) {
            observe("conflict", target);
            return target == null ? acceptsNull : target instanceof Rule r && (mask & r.mask) != 0;
        }
        @Override public boolean equals(Object ignored) {
            throw new AssertionError("Scheduling rules do not authorize equality-based deduplication");
        }
        @Override public int hashCode() {
            throw new AssertionError("Scheduling rules do not authorize hash-based predicate caching");
        }
        @Override public String toString() {
            if (debugFailure != null) throw debugFailure;
            return name;
        }
    }
    private static final class ProjectedRule extends MultiRule {
        private final Supplier<ISchedulingRule[]> projection;
        ProjectedRule(Supplier<ISchedulingRule[]> projection) {
            super();
            this.projection = projection;
        }
        @Override public ISchedulingRule[] getChildren() { return projection.get(); }
    }
    private static void children(MultiRule rule, ISchedulingRule... expected) {
        ISchedulingRule[] actual = rule.getChildren();
        assertEquals(expected.length, actual.length);
        for (int index = 0; index < expected.length; index++) assertSame(expected[index], actual[index]);
    }

    @Test void combinesIdentityNullAndContainmentWithoutExtraCallbacks() {
        var trace = new ArrayList<String>();
        var a = new Rule("a", 1, trace);
        var ab = new Rule("ab", 3, trace);
        var c = new Rule("c", 4, trace);
        assertNull(MultiRule.combine(null, null));
        assertSame(a, MultiRule.combine(a, null));
        assertSame(a, MultiRule.combine(null, a));
        assertSame(a, MultiRule.combine(a, a));
        assertTrue(trace.isEmpty());
        assertSame(ab, MultiRule.combine(ab, a));
        assertEquals(List.of("ab.contains(a)"), trace);
        trace.clear();
        assertSame(ab, MultiRule.combine(a, ab));
        assertEquals(List.of("a.contains(ab)", "ab.contains(a)"), trace);
        trace.clear();
        children((MultiRule) MultiRule.combine(a, c), a, c);
        assertEquals(List.of("a.contains(c)", "c.contains(a)"), trace);
        assertThrows(NullPointerException.class, () -> MultiRule.combine((ISchedulingRule[]) null));
        assertNull(MultiRule.combine(new ISchedulingRule[] {null, null}));
    }

    @Test void arrayCombineUsesLeftToRightReductionAndOriginalReturnIdentity() {
        var trace = new ArrayList<String>();
        var a = new Rule("a", 1, trace);
        var ab = new Rule("ab", 3, trace);
        var abc = new Rule("abc", 7, trace);
        assertSame(abc, MultiRule.combine(new ISchedulingRule[] {null, a, a, ab, null, abc}));
        assertEquals(List.of("a.contains(ab)", "ab.contains(a)",
                "ab.contains(abc)", "abc.contains(ab)"), trace);
    }

    @Test void flattenPreservesDuplicatesNullsOrderAndDefensiveCopies() {
        var a = new Rule("a", 1, null);
        var b = new Rule("b", 2, null);
        ISchedulingRule[] input = {a, new MultiRule(b, a), null, a};
        var multi = new MultiRule(input);
        children(multi, a, b, a, null, a);
        Arrays.fill(input, null);
        ISchedulingRule[] copy = multi.getChildren();
        Arrays.fill(copy, null);
        children(multi, a, b, a, null, a);
        assertEquals("MultiRule[a,b,a,null,a]", multi.toString());
        assertEquals("MultiRule[]", new MultiRule().toString());
        assertThrows(NullPointerException.class, () -> new MultiRule((ISchedulingRule[]) null));
    }

    @Test void flattenCallsEachProjectionOnceAndObservesInputMutationInOrder() {
        var a = new Rule("a", 1, null);
        var b = new Rule("b", 2, null);
        var c = new Rule("c", 4, null);
        ISchedulingRule[] input = new ISchedulingRule[2];
        int[] calls = {0};
        input[0] = new ProjectedRule(() -> {
            calls[0]++;
            input[1] = c;
            return new ISchedulingRule[] {a, b};
        });
        input[1] = a;
        children(new MultiRule(input), a, b, c);
        assertEquals(1, calls[0]);
    }

    @Test void flattenIsOneLevelEvenWhenAnOverriddenProjectionContainsMultiRule() {
        var a = new Rule("a", 1, null);
        var nested = new MultiRule(a);
        var projection = new ProjectedRule(() -> new ISchedulingRule[] {nested});
        children(new MultiRule(projection), nested);
        var trace = new ArrayList<String>();
        var invalid = new ProjectedRule(() -> { trace.add("first"); return null; });
        var later = new ProjectedRule(() -> { trace.add("later"); return new ISchedulingRule[0]; });
        assertThrows(NullPointerException.class, () -> new MultiRule(invalid, later));
        assertEquals(List.of("first"), trace);
    }

    @Test void containsUsesOtherMajorOrderAndShortCircuitsTheFirstMissingChild() {
        var trace = new ArrayList<String>();
        var a = new Rule("a", 1, trace);
        var b = new Rule("b", 2, trace);
        var x = new Rule("x", 2, null);
        var y = new Rule("y", 4, null);
        var z = new Rule("z", 1, null);
        var mine = new MultiRule(a, b);
        assertFalse(mine.contains(new MultiRule(x, y, z)));
        assertEquals(List.of("a.contains(x)", "b.contains(x)", "a.contains(y)", "b.contains(y)"), trace);
        trace.clear();
        assertTrue(mine.contains(new MultiRule(x, z)));
        assertEquals(List.of("a.contains(x)", "b.contains(x)", "a.contains(z)"), trace);
    }

    @Test void conflictUsesOtherMajorOrderAndStopsAtFirstMatchingPair() {
        var trace = new ArrayList<String>();
        var a = new Rule("a", 1, trace);
        var b = new Rule("b", 2, trace);
        var x = new Rule("x", 4, null);
        var y = new Rule("y", 2, null);
        var z = new Rule("z", 1, null);
        assertTrue(new MultiRule(a, b).isConflicting(new MultiRule(x, y, z)));
        assertEquals(List.of("a.conflict(x)", "b.conflict(x)", "a.conflict(y)", "b.conflict(y)"), trace);
    }

    @Test void selfAndEmptyRulesDoNotEvaluateChildrenOrProjectedChildren() {
        var failing = new Rule("failing", 1, null);
        failing.failure = new ProbeFailure();
        var mine = new MultiRule(failing, null);
        var empty = new MultiRule();
        assertTrue(mine.contains(mine));
        assertTrue(mine.isConflicting(mine));
        assertTrue(empty.contains(new MultiRule()));
        assertFalse(empty.isConflicting(new MultiRule()));
        assertTrue(empty.isConflicting(empty));
        assertFalse(empty.contains(failing));
        assertFalse(empty.isConflicting(failing));
        assertTrue(mine.contains(empty));
        assertFalse(mine.isConflicting(empty));
        var projected = new ProjectedRule(() -> { throw new AssertionError("self must not project"); });
        assertTrue(projected.contains(projected));
        assertTrue(projected.isConflicting(projected));
    }

    @Test void preservesNullChildAndNullArgumentBehaviorAtTheObservedPosition() {
        var a = new Rule("a", 1, null);
        var b = new Rule("b", 2, null);
        var mine = new MultiRule(a, null, b);
        assertTrue(mine.contains(a));
        assertTrue(mine.isConflicting(a));
        assertThrows(NullPointerException.class, () -> mine.contains(b));
        assertThrows(NullPointerException.class, () -> mine.isConflicting(b));
        a.acceptsNull = true;
        assertTrue(mine.contains(null));
        assertTrue(mine.isConflicting(null));
        a.acceptsNull = false;
        assertThrows(NullPointerException.class, () -> mine.contains(null));
        assertThrows(NullPointerException.class, () -> mine.isConflicting(null));
        assertFalse(new MultiRule().contains(null));
        assertFalse(new MultiRule().isConflicting(null));
    }

    @Test void exceptionsRetainIdentityAndLaterCallbacksAreNotInvoked() {
        var trace = new ArrayList<String>();
        var first = new Rule("first", 1, trace);
        var later = new Rule("later", 2, trace);
        var target = new Rule("target", 4, null);
        var failure = new ProbeFailure();
        first.failure = failure;
        assertSame(failure, assertThrows(ProbeFailure.class, () -> MultiRule.combine(first, later)));
        assertEquals(List.of("first.contains(later)"), trace);
        trace.clear();
        var mine = new MultiRule(first, later);
        assertSame(failure, assertThrows(ProbeFailure.class, () -> mine.contains(target)));
        assertEquals(List.of("first.contains(target)"), trace);
        trace.clear();
        assertSame(failure, assertThrows(ProbeFailure.class, () -> mine.isConflicting(target)));
        assertEquals(List.of("first.conflict(target)"), trace);
        var projected = new ProjectedRule(() -> { throw failure; });
        assertSame(failure, assertThrows(ProbeFailure.class, () -> new MultiRule(projected)));
        first.debugFailure = failure;
        assertSame(failure, assertThrows(ProbeFailure.class, mine::toString));
    }

    @Test void mutableRulesRemainLiveAndNeverBecomeCachedPredicateResults() {
        var a = new Rule("a", 1, null);
        var b = new Rule("b", 2, null);
        var mine = new MultiRule(a);
        assertFalse(mine.contains(b));
        assertFalse(mine.isConflicting(b));
        a.mask = 3;
        assertTrue(mine.contains(b));
        assertTrue(mine.isConflicting(b));
        a.mask = 1;
        assertFalse(mine.contains(b));
        assertFalse(mine.isConflicting(b));
    }

    @Test void finiteSetOracleCoversAllFourBitRulePairsAndTargets() {
        long observations = 0;
        for (int left = 0; left < 16; left++) for (int right = 0; right < 16; right++) {
            var mine = new MultiRule(new Rule("left", left, null), new Rule("right", right, null));
            for (int target = 0; target < 16; target++) {
                var one = new Rule("one", target, null);
                boolean containsOne = (left & target) == target || (right & target) == target;
                assertEquals(containsOne, mine.contains(one));
                assertEquals(((left | right) & target) != 0, mine.isConflicting(one));
                observations += 2;
                for (int second = 0; second < 16; second++) {
                    var other = new MultiRule(one, new Rule("two", second, null));
                    boolean containsTwo = (left & second) == second || (right & second) == second;
                    assertEquals(containsOne && containsTwo, mine.contains(other));
                    assertEquals(((left | right) & (target | second)) != 0, mine.isConflicting(other));
                    observations += 2;
                }
            }
        }
        assertEquals(139264L, observations);
    }

    @Test void largeFlatteningRetainsAllReferencesWithoutRetainedInputAliases() {
        var rule = new Rule("child", 1, null);
        ISchedulingRule[] block = new ISchedulingRule[8192];
        Arrays.fill(block, rule);
        var nested = new MultiRule(block);
        ISchedulingRule[] input = new ISchedulingRule[32];
        Arrays.fill(input, nested);
        var result = new MultiRule(input);
        Arrays.fill(block, null);
        Arrays.fill(input, null);
        ISchedulingRule[] children = result.getChildren();
        assertEquals(262144, children.length);
        for (ISchedulingRule child : children) assertSame(rule, child);
    }
}
