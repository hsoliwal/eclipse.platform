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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.eclipse.debug.internal.ui.m3.M3SortedLane;
import org.junit.jupiter.api.Test;

/**
 * {@link M3SortedLane} against an {@code ArrayList} oracle: every list answer,
 * and the insert position of the classic backward comparator scan, after
 * seeded random sequences of every mutation (including key changes, order
 * changes, duplicates from {@code addAll} and {@code null}).
 */
public class M3SortedLaneTest {

	/** An element with identity by id and a mutable sort key. */
	static final class Item {
		final int id;
		int key;

		Item(int id, int key) {
			this.id = id;
			this.key = key;
		}

		@Override
		public boolean equals(Object o) {
			return o instanceof Item i && i.id == id;
		}

		@Override
		public int hashCode() {
			return id % 7; // many collisions on purpose: probing and backward shift
		}

		@Override
		public String toString() {
			return id + ":" + key; //$NON-NLS-1$
		}
	}

	static final class Order implements Comparator<Object> {
		boolean descending;

		@Override
		public int compare(Object a, Object b) {
			int r = Integer.compare(a == null ? -1 : ((Item) a).key, b == null ? -1 : ((Item) b).key);
			return descending ? -r : r;
		}
	}

	/** The stock insertBreakpoint: contains check, then the backward scan. */
	static int oracleInsert(List<Object> list, Item e, Comparator<Object> c) {
		if (list.contains(e)) {
			return -1;
		}
		int index = list.size();
		for (; c != null && index > 0; index--) {
			if (c.compare(list.get(index - 1), e) < 0) {
				break;
			}
		}
		list.add(index, e);
		return index;
	}

	static boolean sorted(List<Object> list, Comparator<Object> c) {
		for (int i = 0; i + 1 < list.size(); i++) {
			if (c.compare(list.get(i), list.get(i + 1)) > 0) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void randomScriptsMatchArrayList() {
		for (long seed = 1; seed <= 300; seed++) {
			run(seed, 400);
		}
	}

	private void run(long seed, int steps) {
		Random r = new Random(seed);
		Order order = new Order();
		List<Object> oracle = new ArrayList<>();
		M3SortedLane<Object> lane = new M3SortedLane<>();
		int nextId = 0;
		int epoch = 0;
		boolean tracked = false;
		int trackedEpoch = -1;
		for (int step = 0; step < steps; step++) {
			int op = r.nextInt(100);
			String what = "seed " + seed + " step " + step + " op " + op; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			if (op < 30) {
				Item e = r.nextInt(10) == 0 && !oracle.isEmpty() ? (Item) pick(r, oracle) : new Item(nextId++, r.nextInt(50));
				Comparator<Object> c = r.nextInt(20) == 0 ? null : order;
				tracked |= c != null;
				int expected = oracleInsert(oracle, e, c);
				assertEquals(expected, lane.insertSorted(e, c, epoch), what);
				// a rejected insert (null or already present) returns before the lane looks at the order
				trackedEpoch = c != null && expected >= 0 ? epoch : trackedEpoch;
			} else if (op < 40 && !oracle.isEmpty()) {
				int i = r.nextInt(oracle.size());
				assertSame(oracle.remove(i), lane.remove(i), what);
			} else if (op < 48) {
				Object o = r.nextBoolean() && !oracle.isEmpty() ? pick(r, oracle) : new Item(-1 - r.nextInt(5), 0);
				assertEquals(oracle.remove(o), lane.remove(o), what);
			} else if (op < 55 && !oracle.isEmpty()) {
				// keys change in place, then the owner reports them: one, a few, or a batch above the scan limit
				int kind = r.nextInt(4);
				if (kind == 0) {
					Item e = (Item) pick(r, oracle);
					e.key = r.nextInt(50);
					lane.elementChanged(e);
					lane.elementChanged(new Item(-7, 0)); // absent: no effect
				} else {
					int n = kind == 3 ? M3SortedLane.CHANGE_SCAN_LIMIT + 1 + r.nextInt(8) : 1 + r.nextInt(3);
					Object[] changed = new Object[n];
					for (int i = 0; i < n; i++) {
						Item e = r.nextInt(6) == 0 ? new Item(-9, 0) : (Item) pick(r, oracle);
						e.key = r.nextInt(50);
						changed[i] = e;
					}
					lane.elementsChanged(changed);
					lane.elementsChanged(kind == 3 ? absent(M3SortedLane.CHANGE_SCAN_LIMIT + 1) : new Object[0]);
				}
			} else if (op < 58) {
				order.descending = !order.descending;
				epoch++;
			} else if (op < 63) {
				List<Object> gone = new ArrayList<>();
				int n = r.nextInt(r.nextBoolean() ? 4 : 20);
				for (int i = 0; i < n && !oracle.isEmpty(); i++) {
					gone.add(r.nextInt(4) == 0 ? new Item(-1, 0) : pick(r, oracle));
				}
				Object arg = r.nextInt(3) == 0 ? M3SortedLane.of(gone) : gone;
				assertEquals(oracle.removeAll(gone), lane.removeAll((java.util.Collection<?>) arg), what);
			} else if (op < 66) {
				List<Object> more = new ArrayList<>();
				int n = r.nextInt(5);
				for (int i = 0; i < n; i++) {
					more.add(r.nextInt(5) == 0 && !oracle.isEmpty() ? pick(r, oracle) : new Item(nextId++, r.nextInt(50)));
				}
				assertEquals(oracle.addAll(more), lane.addAll(more), what);
			} else if (op < 69) {
				int i = r.nextInt(oracle.size() + 1);
				Item e = new Item(nextId++, r.nextInt(50));
				oracle.add(i, e);
				lane.add(i, e);
			} else if (op < 71) {
				Object e = r.nextBoolean() ? null : new Item(nextId++, r.nextInt(50));
				assertEquals(oracle.add(e), lane.add(e), what);
			} else if (op < 73 && !oracle.isEmpty()) {
				Iterator<Object> a = oracle.iterator();
				Iterator<Object> b = lane.iterator();
				int skip = r.nextInt(oracle.size());
				for (int i = 0; i <= skip; i++) {
					assertSame(a.next(), b.next(), what);
				}
				a.remove();
				b.remove();
			} else if (op < 74) {
				oracle.clear();
				lane.clear();
			} else {
				Object probe = r.nextBoolean() && !oracle.isEmpty() ? pick(r, oracle) : r.nextInt(8) == 0 ? null : new Item(-3, 0);
				assertEquals(oracle.contains(probe), lane.contains(probe), what);
				assertEquals(oracle.indexOf(probe), lane.indexOf(probe), what);
			}
			assertEquals(oracle, lane, what);
			assertEquals(oracle.size(), lane.size(), what);
			if (tracked && trackedEpoch == epoch && lane.isSorted()) {
				// the lane may only claim sortedness that holds; it may know less (untracked appends)
				assertTrue(sorted(oracle, order) || oracle.contains(null), what);
			}
		}
		assertArrayEquals(oracle.toArray(), lane.toArray());
	}

	private static Object[] absent(int n) {
		Object[] items = new Object[n];
		for (int i = 0; i < n; i++) {
			items[i] = new Item(-100 - i, 0);
		}
		return items;
	}

	private static Object pick(Random r, List<Object> list) {
		Object o = list.get(r.nextInt(list.size()));
		for (int i = 0; o == null && i < list.size(); i++) {
			o = list.get(i);
		}
		return o == null ? new Item(-5, 0) : o; // an absent item when the list holds only nulls
	}

	@Test
	public void rangeIsClippedLikeGetElements() {
		M3SortedLane<Object> lane = new M3SortedLane<>();
		for (int i = 0; i < 10; i++) {
			lane.add(Integer.valueOf(i));
		}
		assertArrayEquals(new Object[] { 2, 3, 4 }, lane.range(2, 3));
		assertArrayEquals(new Object[] { 8, 9 }, lane.range(8, 5));
		assertArrayEquals(new Object[0], lane.range(10, 0));
		assertNull(lane.range(10, 3));
		assertNull(lane.range(12, 1));
	}

	@Test
	public void toArrayFollowsTheListContract() {
		M3SortedLane<Object> lane = M3SortedLane.of(List.of("a", "b")); //$NON-NLS-1$ //$NON-NLS-2$
		String[] big = { "x", "y", "z", "w" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		assertSame(big, lane.toArray(big));
		assertArrayEquals(new String[] { "a", "b", null, "w" }, big); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		String[] exact = new String[2];
		assertSame(exact, lane.toArray(exact));
		assertArrayEquals(new String[] { "a", "b" }, lane.toArray(new String[0])); //$NON-NLS-1$ //$NON-NLS-2$
		assertFalse(lane.addAll(List.of()));
		assertFalse(lane.removeAll(List.of("q"))); //$NON-NLS-1$
		assertTrue(lane.removeAll(lane));
		assertTrue(lane.isEmpty());
		assertTrue(lane.isSorted());
		assertThrows(IndexOutOfBoundsException.class, () -> lane.get(0));
		assertThrows(NullPointerException.class, () -> lane.removeAll(null));
	}

	@Test
	public void iteratorFailsFastLikeArrayList() {
		M3SortedLane<Object> lane = M3SortedLane.of(List.of("a", "b", "c")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		Iterator<Object> it = lane.iterator();
		it.next();
		lane.add("d"); //$NON-NLS-1$
		assertThrows(ConcurrentModificationException.class, it::next);
	}

	@Test
	public void changedHashCodeDoesNotLoseOrHangTheTable() {
		Item[] items = new Item[40];
		M3SortedLane<Object> lane = new M3SortedLane<>();
		for (int i = 0; i < items.length; i++) {
			items[i] = new Item(i, i);
			lane.add(items[i]);
		}
		Object moved = new Object() {
			int hash = 1;

			@Override
			public int hashCode() {
				return hash++;
			}
		};
		lane.add(moved);
		lane.remove(lane.size() - 1); // its hash changed since it was added: the table is rebuilt
		assertEquals(Arrays.asList((Object[]) items), lane);
		for (Item item : items) {
			assertTrue(lane.contains(item));
		}
	}

	@Test
	public void insertWithoutComparatorAppendsAndNullIsRejected() {
		M3SortedLane<Object> lane = new M3SortedLane<>();
		assertEquals(-1, lane.insertSorted(null, null, 0));
		assertEquals(0, lane.insertSorted(new Item(1, 5), null, 0));
		assertEquals(1, lane.insertSorted(new Item(2, 1), null, 0));
		assertEquals(-1, lane.insertSorted(new Item(1, 9), null, 0));
		lane.elementChanged(new Item(1, 0)); // untracked: nothing to re-check
		assertTrue(lane.isSorted());
		Order order = new Order();
		assertEquals(0, lane.insertSorted(new Item(3, 0), order, 0)); // starts tracking: (5, 1) is inverted
		assertFalse(lane.isSorted());
	}
}
