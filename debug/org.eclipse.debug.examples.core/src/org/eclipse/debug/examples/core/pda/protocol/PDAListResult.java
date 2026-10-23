/*******************************************************************************
 * Copyright (c) 2008, 2018 Wind River Systems and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Wind River Systems - initial API and implementation
 *     IBM Corporation - bug fixing
 *******************************************************************************/
package org.eclipse.debug.examples.core.pda.protocol;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * @see PDADataCommand
 */

public class PDAListResult extends PDACommandResult {

	final public String[] fValues;

	PDAListResult(String response) {
		super(response);
		StringTokenizer st = new StringTokenizer(response, "|"); //$NON-NLS-1$
		List<String> valuesList = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW

		while (st.hasMoreTokens()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			String token = st.nextToken();
			if (token.length() != 0) {
				valuesList.add(token);
			}
		}

		fValues = new String[valuesList.size()];
		for (int i = 0; i < valuesList.size(); i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			fValues[i] = valuesList.get(i);
		}
	}
}
