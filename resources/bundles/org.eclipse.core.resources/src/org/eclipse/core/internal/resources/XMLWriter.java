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
 *     James Blackburn (Broadcom Corp.) - ongoing development
 *******************************************************************************/
package org.eclipse.core.internal.resources;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple XML writer.
 */
public class XMLWriter extends PrintWriter {
	protected int tab;
	protected String lineSeparator;

	/* constants */
	protected static final String XML_VERSION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"; //$NON-NLS-1$

	public XMLWriter(OutputStream output, String separator) {
		super(new OutputStreamWriter(output, StandardCharsets.UTF_8));
		tab = 0;
		lineSeparator = separator;
		println(XML_VERSION);
	}

	public void endTag(String name) {
		tab--;
		printTag('/' + name, null); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
	}

	@Override
	public void println(String x) {
		super.print(x);
		super.print(lineSeparator);
	}

	public void printSimpleTag(String name, Object value) {
		if (value != null) {
			printTag(name, null, true, false);
			print(getEscaped(String.valueOf(value)));
			printTag('/' + name, null, false, true); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		}
	}

	public void printTabulation() {
		for (int i = 0; i < tab; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			super.print('\t');
		}
	}

	public void printTag(String name, HashMap<String, Object> parameters) {
		printTag(name, parameters, true, true);
	}

	public void printTag(String name, HashMap<String, Object> parameters, boolean shouldTab, boolean newLine) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		StringBuilder sb = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		sb.append("<"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		sb.append(name); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		if (parameters != null) {
			for (Map.Entry<String, Object> entry : parameters.entrySet()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				sb.append(" "); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				String key = entry.getKey();
				sb.append(key); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				sb.append("=\""); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				sb.append(getEscaped(String.valueOf(entry.getValue()))); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				sb.append("\""); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			}
		}
		sb.append(">"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		if (shouldTab) {
			printTabulation();
		}
		if (newLine) {
			println(sb.toString());
		} else {
			print(sb.toString());
		}
	}

	public void startTag(String name, HashMap<String, Object> parameters) {
		startTag(name, parameters, true);
	}

	public void startTag(String name, HashMap<String, Object> parameters, boolean newLine) {
		printTag(name, parameters, true, newLine);
		tab++;
	}

	private static void appendEscapedChar(StringBuilder buffer, char c) {
		String replacement = getReplacement(c);
		if (replacement != null) {
			buffer.append('&'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			buffer.append(replacement); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			buffer.append(';'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		} else {
			buffer.append(c); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
	}

	public static String getEscaped(String s) {
		StringBuilder result = new StringBuilder(s.length() + 10); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		for (int i = 0; i < s.length(); ++i) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			appendEscapedChar(result, s.charAt(i));
		}
		return result.toString();
	}

	private static String getReplacement(char c) {
		// Encode special XML characters into the equivalent character references.
		// These five are defined by default for all XML documents.
		switch (c) {
			case '<' :
				return "lt"; //$NON-NLS-1$
			case '>' :
				return "gt"; //$NON-NLS-1$
			case '"' :
				return "quot"; //$NON-NLS-1$
			case '\'' :
				return "apos"; //$NON-NLS-1$
			case '&' :
				return "amp"; //$NON-NLS-1$
		}
		return null;
	}
}
