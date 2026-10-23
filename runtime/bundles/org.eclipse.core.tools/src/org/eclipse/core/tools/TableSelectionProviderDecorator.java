/*******************************************************************************
 * Copyright (c) 2002, 2005 IBM Corporation and others.
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
 *******************************************************************************/
package org.eclipse.core.tools;

import java.util.Iterator;
import org.eclipse.core.runtime.PerformanceStats;
import org.eclipse.jface.viewers.*;

/**
 * This class provides a decorator for ISelectionProviders that use
 * TableViewer as their viewer and PluginStats as the basis for their data
 * model. The only affected method is <code>getSelection()</code>, which will
 * return a string that closely resembles the table view of this object.
 */
public class TableSelectionProviderDecorator implements ISelectionProvider {

	/** The decorated selection provider. */
	private final ISelectionProvider selectionProvider;

	/**
	 * Constructs a <code>TableSelectionProviderDecorator</code> having
	 * the given selection provider as its decorated object.
	 *
	 * @param selectionProvider the selection provider to be decorated
	 */
	public TableSelectionProviderDecorator(ISelectionProvider selectionProvider) {
		this.selectionProvider = selectionProvider;
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionProvider.addSelectionChangedListener(listener); //FLLDCIM: MARK — T04 VIEWER_SELECTION_DELTA CALL
	}

	/**
	 * Returns the current selection for this provider. If the selection is a
	 * structured selection made of <code>PluginStats</code> elements, this method
	 * will return a structured selection of strings that resemble the table view
	 * of this data.
	 *
	 * @return the current selection, printed in table view format
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public ISelection getSelection() {
		// gets the original selection object
		ISelection selection = selectionProvider.getSelection();

		// in these cases the original selection will be returned
		if (selection == null || selection.isEmpty() || !(selection instanceof IStructuredSelection structuredSelection)) {
			return selection;
		}

		// constructs a list with the selected elements
		StringBuilder copyText = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		copyText.append(headerPluginStats()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		copyText.append('\n'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		for (Iterator<?> selectionIter = structuredSelection.iterator(); selectionIter.hasNext();) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			Object obj = selectionIter.next();
			if (obj instanceof PerformanceStats) {
				copyText.append(prettyPluginStats((PerformanceStats) obj)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				copyText.append('\n'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			}
		}
		return new StructuredSelection(copyText);

	}

	private String headerPluginStats() {
		String retString = ""; //$NON-NLS-1$
		retString += Messages.stats_eventHeader + "\t"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		retString += Messages.stats_blameHeader + "\t"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		retString += Messages.stats_contextHeader + "\t"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		retString += Messages.stats_countHeader + "\t"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		retString += Messages.stats_timeHeader + "\t"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		return retString;
	}

	private String prettyPluginStats(PerformanceStats stats) {
		String retString = ""; //$NON-NLS-1$
		retString += stats.getEvent() + "\t\t"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		retString += stats.getBlameString() + "\t\t"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		retString += stats.getContext() + "\t\t"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		retString += stats.getRunCount() + "\t"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		retString += stats.getRunningTime() + "\t"; //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		return retString;
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionProvider.removeSelectionChangedListener(listener);
	}

	@Override
	public void setSelection(ISelection selection) {
		selectionProvider.setSelection(selection);
	}

}
