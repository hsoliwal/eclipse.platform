/*******************************************************************************
 * Copyright (c) 2002, 2006 IBM Corporation and others.
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

/**
 * A set of utility methods for dealing with byte arrays.
 */
public class ByteUtil {

	/**
	 * Helper method that creates a string representation for a byte array.
	 *
	 * @param byteArray a byte array to be represented as string
	 * @param max a maximum number of bytes to be considered - if zero, there is
	 * no maximum.
	 * @return a byte array string representation
	 */
	public static String byteArrayToString(byte[] byteArray, int max) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		StringBuilder result = new StringBuilder(byteArray.length * 2 + 2); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		int bytesToBeShown = (max > 0) ? (Math.min(max, byteArray.length)) : byteArray.length;
		result.append('['); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		for (int i = 0; i < bytesToBeShown; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			result.append(byteArray[i]); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			result.append(','); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		// adds an ellipsis if there is too much bytes to show
		if (max > 0 && max < byteArray.length) {
			result.append("..."); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		// or remove the trailing comma
		} else {
			result.deleteCharAt(result.length() - 1);
		}
		result.append(']'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		return result.toString();
	}
}
