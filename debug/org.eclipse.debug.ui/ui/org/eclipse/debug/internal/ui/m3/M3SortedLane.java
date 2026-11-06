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
package org.eclipse.debug.internal.ui.m3;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.RandomAccess;

/**
 * An ordered list held in lanes: the elements in one array, an equals-hash
 * membership table over the same elements (open addressing, no entry
 * objects), and one inversion bit per adjacent pair. It is a drop-in
 * replacement for an {@code ArrayList} that a view keeps in comparator order:
 * every {@link java.util.List} method keeps the {@code ArrayList} answer.
 * <ul>
 * <li>{@link #contains(Object)} is O(1) expected instead of an equals scan;</li>
 * <li>{@link #insertSorted(Object, Comparator, int)} returns the position the
 * classic backward scan would choose (after the last element that compares
 * less, scanning from the end), with O(log n) comparisons while the lane is
 * sorted and the classic scan otherwise;</li>
 * <li>{@link #range(int, int)} copies one window of elements, not the whole
 * list.</li>
 * </ul>
 * The lane is sorted when no adjacent pair is inverted under the tracked
 * comparator. Inversion bits are kept exact on every mutation; a change of an
 * element's sort key is reported with {@link #elementChanged(Object)}, and a
 * change of the comparator's behaviour with a new epoch. Membership relies on
 * the {@code hashCode}/{@code equals} contract of the elements.
 * <p>
 * Not thread-safe, like the {@code ArrayList} it replaces.
 * </p>
 */
public final class M3SortedLane<E> extends AbstractList<E> implements RandomAccess {

	/**
	 * When {@code true} (system property
	 * {@code org.eclipse.debug.ui.m3.linearInsert}), sorted inserts always use
	 * the classic backward scan.
	 */
	static final boolean LINEAR_INSERT = Boolean.getBoolean("org.eclipse.debug.ui.m3.linearInsert"); //$NON-NLS-1$

	/** Change batches larger than this mark the order unknown instead of re-checking each element. */
	public static final int CHANGE_SCAN_LIMIT = 32;

	private static final Object[] NO_ITEMS = {};
	private static final byte[] NO_BITS = {};
	private static final Object NULL_KEY = new Object();

	private Object[] items = NO_ITEMS;
	private int size;
	/** {@code inverted[i] == 1} when {@code order.compare(items[i], items[i + 1]) > 0}; valid while {@code order != null}. */
	private byte[] inverted = NO_BITS;
	private int inversions;
	private Comparator<Object> order;
	private int epoch;
	/**
	 * The inversion bits are unknown (a large batch of key changes): the next
	 * sorted insert recomputes them, mutations until then skip them.
	 */
	private boolean stale;
	/** Membership table: power-of-two capacity, at most half full, linear probing. */
	private Object[] table = NO_ITEMS;

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		Objects.checkIndex(index, size);
		return (E) items[index];
	}

	@Override
	public boolean contains(Object o) {
		if (size == 0) {
			return false;
		}
		Object key = o == null ? NULL_KEY : o;
		int mask = table.length - 1;
		for (int i = slot(key, mask);; i = (i + 1) & mask) {
			Object t = table[i];
			if (t == null) {
				return false;
			}
			if (t == key || key != NULL_KEY && t != NULL_KEY && key.equals(t)) {
				return true;
			}
		}
	}

	@Override
	public int indexOf(Object o) {
		if (!contains(o)) {
			return -1;
		}
		return scan(o, 0);
	}

	/* first index at or after from holding an element equal to o, or -1 (locals: the loop calls equals) */
	private int scan(Object o, int from) {
		Object[] es = items;
		int n = size;
		for (int i = from; i < n; i++) {
			if (Objects.equals(o, es[i])) {
				return i;
			}
		}
		return -1;
	}

	/* whether the inversion bits are maintained on mutation */
	private boolean tracking() {
		return order != null && !stale;
	}

	@Override
	public void add(int index, E element) {
		Objects.checkIndex(index, size + 1);
		grow(size + 1);
		System.arraycopy(items, index, items, index + 1, size - index);
		items[index] = element;
		size++;
		tableAdd(element);
		if (tracking()) {
			insertBits(index);
		}
		modCount++;
	}

	@Override
	public boolean add(E element) {
		add(size, element);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		Objects.checkIndex(index, size);
		Object removed = items[index];
		if (tracking()) {
			removeBits(index);
		}
		System.arraycopy(items, index + 1, items, index, size - index - 1);
		items[--size] = null;
		tableRemove(removed);
		modCount++;
		return (E) removed;
	}

	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		if (index < 0) {
			return false;
		}
		remove(index);
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		Object[] added = c.toArray();
		for (Object e : added) {
			@SuppressWarnings("unchecked")
			E element = (E) e;
			add(size, element);
		}
		return added.length != 0;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		Objects.requireNonNull(c);
		Collection<?> gone = c instanceof M3SortedLane || c.size() <= 8 ? c : copyOf(c);
		boolean bits = tracking();
		int w = 0;
		int prevKept = -1;
		boolean runInOrder = true; // every pair from the previous kept element up to r is in order
		for (int r = 0; r < size; r++) {
			Object e = items[r];
			boolean pairInOrder = !bits || r == 0 || inverted[r - 1] == 0;
			if (gone.contains(e)) {
				runInOrder &= pairInOrder;
				continue;
			}
			if (bits && w > 0) {
				// read the old pair bit before the compacted pair (w - 1 <= r - 1) is written
				byte bit;
				if (prevKept == r - 1) {
					bit = inverted[r - 1];
				} else {
					bit = runInOrder && pairInOrder ? 0 : bit(items[w - 1], e);
				}
				inverted[w - 1] = bit;
			}
			items[w++] = e;
			prevKept = r;
			runInOrder = true;
		}
		if (w == size) {
			return false;
		}
		Arrays.fill(items, w, size, null);
		if (bits) {
			Arrays.fill(inverted, Math.max(0, w - 1), Math.max(0, size - 1), (byte) 0);
		}
		size = w;
		rebuildTable(); // one O(n) pass; the membership table is not touched while it answers gone.contains
		if (bits) {
			recount();
		}
		modCount++;
		return true;
	}

	@Override
	public void clear() {
		Arrays.fill(items, 0, size, null);
		Arrays.fill(inverted, (byte) 0);
		size = 0;
		inversions = 0;
		stale = false;
		table = NO_ITEMS;
		modCount++;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(items, size);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < size) {
			return (T[]) Arrays.copyOf(items, size, a.getClass());
		}
		System.arraycopy(items, 0, a, 0, size);
		if (a.length > size) {
			a[size] = null;
		}
		return a;
	}

	/**
	 * Inserts {@code element} where the classic backward scan puts it: after
	 * the last element that compares less, scanning from the end; at the end
	 * when {@code comparator} is {@code null}.
	 *
	 * @param element    the element; {@code null} is not inserted
	 * @param comparator the order, or {@code null} to append
	 * @param orderEpoch changes whenever the comparator's behaviour changes
	 *                   (for example a sort preference); the inversion bits
	 *                   are recomputed then
	 * @return the index, or -1 when the element is {@code null} or already
	 *         contained
	 */
	public int insertSorted(E element, Comparator<Object> comparator, int orderEpoch) {
		if (element == null || contains(element)) {
			return -1;
		}
		int index = size;
		if (comparator != null) {
			if (comparator != order || orderEpoch != epoch || stale) {
				track(comparator, orderEpoch);
			}
			index = inversions == 0 && !LINEAR_INSERT ? lowerBound(element) : backwardScan(element);
		}
		add(index, element);
		return index;
	}

	/**
	 * Re-checks the order around {@code element} after its sort key changed.
	 *
	 * @param element the element whose key changed
	 */
	public void elementChanged(Object element) {
		if (!tracking() || !contains(element)) {
			return;
		}
		// every occurrence: a list may hold the same element more than once (addAll)
		for (int index = scan(element, 0); index >= 0; index = scan(element, index + 1)) {
			if (index > 0) {
				setBit(index - 1, bit(items[index - 1], items[index]));
			}
			if (index < size - 1) {
				setBit(index, bit(items[index], items[index + 1]));
			}
		}
	}

	/**
	 * Re-checks the order after the sort keys of {@code elements} changed. Up to
	 * {@link #CHANGE_SCAN_LIMIT} elements are re-checked in place (two
	 * comparisons each); a larger batch that touches this lane only marks the
	 * order unknown, and the next sorted insert recomputes it with one pass of
	 * {@code size - 1} comparisons, about the cost of one classic scan.
	 *
	 * @param elements the elements whose keys changed
	 */
	public void elementsChanged(Object[] elements) {
		if (!tracking()) {
			return;
		}
		if (elements.length > CHANGE_SCAN_LIMIT) {
			for (Object e : elements) {
				if (contains(e)) {
					stale = true;
					return;
				}
			}
			return;
		}
		for (Object e : elements) {
			elementChanged(e);
		}
	}

	/**
	 * Copies {@code length} elements from {@code index}, clipped like
	 * {@code ElementContentProvider.getElements}: {@code null} when
	 * {@code index} is past the end.
	 *
	 * @param index  the first index
	 * @param length the number of elements wanted
	 * @return the elements, or {@code null}
	 */
	public Object[] range(int index, int length) {
		if (index < size && index + length > size) {
			length = size - index;
		}
		if (index + length <= size) {
			Object[] sub = new Object[length];
			System.arraycopy(items, index, sub, 0, length);
			return sub;
		}
		return null;
	}

	/** @return whether no adjacent pair is inverted under the tracked comparator */
	public boolean isSorted() {
		return order == null || !stale && inversions == 0;
	}

	private int lowerBound(Object element) {
		int lo = 0;
		int hi = size;
		while (lo < hi) {
			int mid = (lo + hi) >>> 1;
			if (order.compare(items[mid], element) < 0) {
				lo = mid + 1;
			} else {
				hi = mid;
			}
		}
		return lo;
	}

	private int backwardScan(Object element) {
		int index = size;
		for (; index > 0; index--) {
			if (order.compare(items[index - 1], element) < 0) {
				break;
			}
		}
		return index;
	}

	private void track(Comparator<Object> comparator, int orderEpoch) {
		order = comparator;
		stale = false;
		epoch = orderEpoch;
		inverted = size > 1 ? new byte[items.length] : inverted;
		for (int i = 0; i < size - 1; i++) {
			inverted[i] = bit(items[i], items[i + 1]);
		}
		recount();
	}

	private byte bit(Object left, Object right) {
		return order.compare(left, right) > 0 ? (byte) 1 : (byte) 0;
	}

	private void setBit(int pair, byte bit) {
		inversions += bit - inverted[pair];
		inverted[pair] = bit;
	}

	private void recount() {
		int n = 0;
		for (int i = 0; i < size - 1; i++) {
			n += inverted[i];
		}
		inversions = n;
	}

	/* pairs after an insert at index: (index-1, index) and (index, index+1) replace (index-1, index) */
	private void insertBits(int index) {
		if (inverted.length < items.length) {
			inverted = Arrays.copyOf(inverted, items.length);
		}
		int pairsAfter = size - 1 - index; // pairs right of the new element, shifted by one
		if (pairsAfter > 0) {
			if (index > 0) {
				inversions -= inverted[index - 1];
			}
			System.arraycopy(inverted, index, inverted, index + 1, pairsAfter - 1);
			inverted[index] = 0;
			setBit(index, bit(items[index], items[index + 1]));
		}
		if (index > 0) {
			inverted[index - 1] = 0;
			setBit(index - 1, bit(items[index - 1], items[index]));
		}
	}

	/* pairs before a removal at index: (index-1, index) and (index, index+1) merge into (index-1, index+1) */
	private void removeBits(int index) {
		byte left = index > 0 ? inverted[index - 1] : 0;
		byte right = index < size - 1 ? inverted[index] : 0;
		inversions -= left + right;
		if (index < size - 1) {
			System.arraycopy(inverted, index + 1, inverted, index, size - 2 - index);
		}
		if (index > 0 && index < size - 1) {
			// transitivity: both pairs in order means the merged pair is in order
			inverted[index - 1] = left == 0 && right == 0 ? 0 : bit(items[index - 1], items[index + 1]);
			inversions += inverted[index - 1];
		}
		if (size >= 2) {
			inverted[size - 2] = 0;
		}
	}

	private void grow(int needed) {
		if (needed > items.length) {
			items = Arrays.copyOf(items, Math.max(needed, Math.max(8, items.length + (items.length >> 1))));
		}
		if (needed * 2 > table.length) {
			rehash(Integer.highestOneBit(Math.max(8, needed * 2 - 1)) << 1);
		}
	}

	private void rehash(int capacity) {
		Object[] old = table;
		table = new Object[capacity];
		int mask = capacity - 1;
		for (Object t : old) {
			if (t != null) {
				int i = slot(t, mask);
				while (table[i] != null) {
					i = (i + 1) & mask;
				}
				table[i] = t;
			}
		}
	}

	private void tableAdd(Object element) {
		Object key = element == null ? NULL_KEY : element;
		int mask = table.length - 1;
		int i = slot(key, mask);
		while (table[i] != null) {
			i = (i + 1) & mask;
		}
		table[i] = key;
	}

	/*
	 * Removes the slot holding exactly this reference, then closes the probe gap
	 * (backward shift). An element whose hashCode changed while in the lane is
	 * not found at its home slot: the table is then rebuilt from the elements.
	 */
	private void tableRemove(Object element) {
		Object key = element == null ? NULL_KEY : element;
		int mask = table.length - 1;
		int i = slot(key, mask);
		for (int probes = 0; table[i] != key; i = (i + 1) & mask) {
			if (table[i] == null || ++probes > mask) {
				rebuildTable();
				return;
			}
		}
		table[i] = null;
		for (int j = (i + 1) & mask; table[j] != null; j = (j + 1) & mask) {
			int home = slot(table[j], mask);
			// move table[j] into the gap when its home is not in the cyclic range (i, j]
			if (i <= j ? home <= i || home > j : home <= i && home > j) {
				table[i] = table[j];
				table[j] = null;
				i = j;
			}
		}
	}

	private void rebuildTable() {
		Arrays.fill(table, null);
		for (int i = 0; i < size; i++) {
			tableAdd(items[i]);
		}
	}

	private static int slot(Object key, int mask) {
		int h = key.hashCode() * 0x9E3779B9;
		return (h ^ (h >>> 16)) & mask;
	}

	/**
	 * Returns a lane holding the elements of {@code c} in iteration order: an
	 * {@code ArrayList}-compatible list whose {@code contains} is O(1).
	 *
	 * @param <T> the element type
	 * @param c   the elements
	 * @return a new lane
	 */
	public static <T> M3SortedLane<T> of(Collection<? extends T> c) {
		M3SortedLane<T> lane = new M3SortedLane<>();
		lane.addAll(c);
		return lane;
	}

	private static M3SortedLane<Object> copyOf(Collection<?> c) {
		return of(c);
	}
}
