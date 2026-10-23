/*******************************************************************************
 * Copyright (c) 2000, 2017 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.compare.internal;

import java.util.ArrayList;

import org.eclipse.jface.text.IDocument;

/**
 * No API yet.
 */
public class DocumentManager {

	private static final boolean DEBUG= false;

	private static ArrayList<Object> fgKeys= new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
	private static ArrayList<IDocument> fgValues= new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW

	public static IDocument get(Object o) {

		for (int i= 0; i < fgKeys.size(); i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			if (fgKeys.get(i) == o) {
				return fgValues.get(i);
			}
		}
		return null;
	}

	public static void put(Object o, IDocument document) {
		if (DEBUG) {
			System.out.println("DocumentManager.put: " + document); //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		}
		for (int i= 0; i < fgKeys.size(); i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			if (fgKeys.get(i) == o) {
				fgValues.set(i, document);
				return;
			}
		}
		fgKeys.add(o);
		fgValues.add(document);
	}

	public static void remove(IDocument document) {
		if (document != null) {
			if (DEBUG) {
				System.out.println("DocumentManager.remove: " + document); //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
			}
			for (int i= 0; i < fgValues.size(); i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				if (fgValues.get(i) == document) {
					fgKeys.remove(i);
					fgValues.remove(i);
					return;
				}
			}
			if (DEBUG) {
				System.out.println("DocumentManager.remove: not found"); //$NON-NLS-1$
			}
		}
	}

	public static void dump() {
		if (DEBUG) {
			System.out.println("DocumentManager: managed docs:" + fgValues.size()); //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		}
	}
}
