/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
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
package org.eclipse.core.tools.nls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IType;
import org.eclipse.ltk.core.refactoring.Change;

/**
 * Converts a message bundle file to the new format, and generates a Java
 * file with fields for each key in the bundle.
 */
public class PropertyFileConverter {

	private static final HashSet<String> keywords = new HashSet<>(); //FLLDCIM: MARK — M03 M3_SET_LANES NEW

	static {
		keywords.add("abstract"); //$NON-NLS-1$
		keywords.add("assert"); //$NON-NLS-1$
		keywords.add("break"); //$NON-NLS-1$
		keywords.add("case"); //$NON-NLS-1$
		keywords.add("catch"); //$NON-NLS-1$
		keywords.add("class"); //$NON-NLS-1$
		keywords.add("continue"); //$NON-NLS-1$
		keywords.add("default"); //$NON-NLS-1$
		keywords.add("do"); //$NON-NLS-1$
		keywords.add("else"); //$NON-NLS-1$
		keywords.add("extends"); //$NON-NLS-1$
		keywords.add("final"); //$NON-NLS-1$
		keywords.add("finally"); //$NON-NLS-1$
		keywords.add("for"); //$NON-NLS-1$
		keywords.add("if"); //$NON-NLS-1$
		keywords.add("implements"); //$NON-NLS-1$
		keywords.add("import"); //$NON-NLS-1$
		keywords.add("instanceof"); //$NON-NLS-1$
		keywords.add("interface"); //$NON-NLS-1$
		keywords.add("native"); //$NON-NLS-1$
		keywords.add("new"); //$NON-NLS-1$
		keywords.add("package"); //$NON-NLS-1$
		keywords.add("private"); //$NON-NLS-1$
		keywords.add("protected"); //$NON-NLS-1$
		keywords.add("public"); //$NON-NLS-1$
		keywords.add("return"); //$NON-NLS-1$
		keywords.add("static"); //$NON-NLS-1$
		keywords.add("strictfp"); //$NON-NLS-1$
		keywords.add("super"); //$NON-NLS-1$
		keywords.add("switch"); //$NON-NLS-1$
		keywords.add("synchronized"); //$NON-NLS-1$
		keywords.add("this"); //$NON-NLS-1$
		keywords.add("throw"); //$NON-NLS-1$
		keywords.add("throws"); //$NON-NLS-1$
		keywords.add("transient"); //$NON-NLS-1$
		keywords.add("try"); //$NON-NLS-1$
		keywords.add("volatile"); //$NON-NLS-1$
		keywords.add("while"); //$NON-NLS-1$
		keywords.add("true"); //$NON-NLS-1$
		keywords.add("false"); //$NON-NLS-1$
		keywords.add("null"); //$NON-NLS-1$
	}

	/**
	 * Appends the text to put at the end of each Java messages file.
	 */
	private void appendPostText(StringBuilder buffer, String bundlePath, String typeName) {
		buffer.append("\n\tstatic {\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append("\t\t// load message values from bundle file\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append("\t\tNLS.initializeMessages(BUNDLE_NAME, "); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(typeName); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(".class"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(");\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append("\t}\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append("}"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
	}

	/**
	 * Appends the text to put at the beginning of each Java messages file.
	 */
	private void appendPreText(StringBuilder buffer, String pkgName, String bundleName, String typeName) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		//if this text had typos, would it be a false pretext?
		buffer.append("/**********************************************************************\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(" * Copyright (c) 2005 IBM Corporation and others.\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(" *\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(" * This program and the accompanying materials are made available under the terms of\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(" * the Eclipse Public License 2.0 which accompanies this distribution, and is\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(" * available at\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(" * https://www.eclipse.org/legal/epl-2.0/\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(" * \n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(" * SPDX-License-Identifier: EPL-2.0\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(" * \n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(" * Contributors: \n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(" * IBM - Initial API and implementation\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(" **********************************************************************/\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append("package "); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(pkgName); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(";\n\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		buffer.append("import org.eclipse.osgi.util.NLS;\n\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append("public class "); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(typeName); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append(" extends NLS {\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buffer.append("\tprivate static final String BUNDLE_NAME = \"" + pkgName + '.' + bundleName + "\";//$NON-NLS-1$\n"); //$NON-NLS-1$ //$NON-NLS-2$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
	}

	/*
	 * Remove the properties in the specified list from the file.
	 */
	public Change trim(IFile propertiesFile, List<String> toDelete) throws IOException, CoreException { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		if (toDelete == null || toDelete.isEmpty()) {
			return null;
		}

		StringBuilder bundle = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(propertiesFile.getContents()))) {
			String line;
			boolean isContinued = false;
			boolean wasDeleted = false;
			while ((line = reader.readLine()) != null) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				line = line.trim();
				// just add comments directly to the output
				if (skipLine(line)) {
					bundle.append(line); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					bundle.append("\r\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					continue;
				}

				boolean wasContinued = isContinued;
				isContinued = isContinued(line);
				// if we are continued from the previous line...
				if (wasContinued) {
					// if the previous line was deleted then...
					if (wasDeleted) {
						// skip this line
					} else {
						// otherwise write it out
						bundle.append(line); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
						bundle.append("\r\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					}
				} else {
					// we weren't continued from the previous line
					String key = extractKey(line);
					boolean shouldDelete = toDelete.contains(key);
					// if the key was in our skip list then don't write it out
					if (shouldDelete) {
						wasDeleted = true;
					} else {
						wasDeleted = false;
						bundle.append(line); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
						bundle.append("\r\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					}
				}

			}
		}
		NLSFileChange pChange = new NLSFileChange(propertiesFile);
		pChange.setContents(bundle.toString());
		return pChange;
	}

	/**
	 * Reads an old properties file, and creates a new properties file and corresponding
	 * Java messages file.
	 */
	public Change[] convertFile(IType accessorType, IFile propertiesFile) throws IOException, CoreException { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		String pkgName = accessorType.getPackageFragment().getElementName();
		IFile accessorFile = (IFile) accessorType.getCompilationUnit().getCorrespondingResource();
		String typeName = accessorFile.getFullPath().removeFileExtension().lastSegment();

		String bundleName = propertiesFile.getName();
		StringBuilder clazz = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		// convert the bundle resource (messages.properties) to the simple name (messages)
		String simpleBundleName = IPath.fromOSString(bundleName).removeFileExtension().toString();
		appendPreText(clazz, pkgName, simpleBundleName, typeName);
		StringBuilder bundle = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		int savings = 0;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(propertiesFile.getContents()))) {
			String line;
			boolean isContinued = false;
			while ((line = reader.readLine()) != null) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				line = line.trim();
				boolean wasContinued = isContinued;
				isContinued = isContinued(line);
				if (!wasContinued) {
					if (skipLine(line)) {
						clazz.append(convertToComment(line)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					} else {
						String key = extractKey(line);
						savings += 88 + 4 * key.length();
						String identifier = convertToJavaIdentifier(key);
						clazz.append("\tpublic static String "); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
						clazz.append(identifier); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
						clazz.append(";\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
						//convert the bundle file line to use the Java identifier
						line = identifier + line.substring(key.length()); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
					}
				}
				//write the line out to the new bundle file
				bundle.append(line); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				bundle.append("\r\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			}
		}
		System.out.println("Memory saved by converting to field-based keys: " + savings); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		appendPostText(clazz, pkgName + '.' + bundleName, typeName); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT

		NLSFileChange pChange = new NLSFileChange(propertiesFile);
		pChange.setContents(bundle.toString());

		NLSFileChange cChange = new NLSFileChange(accessorFile);
		cChange.setContents(clazz.toString());

		return new Change[] {pChange, cChange};
	}

	/**
	 * Writes the given line as a comment int the provided class buffer.
	 * Blank lines are preserved.
	 */
	private String convertToComment(String line) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		StringBuilder comment = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		if (!line.trim().isEmpty()) {
			comment.append("\t//"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		int offset = 0;
		//skip leading comment characters
		while (offset < line.length()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			char c = line.charAt(offset);
			if (c != '!' && c != '#') {
				break;
			}
			offset++;
		}
		comment.append(line.substring(offset)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		comment.append('\n'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		return comment.toString();
	}

	/**
	 * Converts an arbitrary string into a string that represents a valid
	 * Java identifier.
	 */
	public static String convertToJavaIdentifier(String key) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		String string = key.trim();
		int len = string.length();
		if (len == 0) {
			return string;
		}
		StringBuilder result = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		char c = string.charAt(0);
		if (Character.isJavaIdentifierStart(c)) {
			result.append(c); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		} else {
			//if it's a valid part, just add an underscore first but keep the character
			result.append('_'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			if (Character.isJavaIdentifierPart(c)) {
				result.append(c); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			}
		}
		for (int i = 1; i < len; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			c = string.charAt(i);
			if (Character.isJavaIdentifierPart(c)) {
				result.append(c); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			} else {
				result.append('_'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			}
		}
		//preserve trailing space
		if (key.endsWith(" ")) { //$NON-NLS-1$
			result.append(' '); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		return makeUnique(result.toString());
	}

	/**
	 * Given a key converted to a Java identifier, ensure it is unique.
	 * @return A unique key
	 */
	private static String makeUnique(String originalKey) {
		String attempt = originalKey;
		int counter = 0;
		while (keywords.contains(attempt)) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			attempt = originalKey + counter++; //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		}
		return attempt;
	}

	/**
	 * Extracts and returns the property key from the given property file line.
	 * The provided line contains no leading or trailing whitespace.
	 */
	private String extractKey(String line) {
		int len = line.length();
		StringBuilder key = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		for (int i = 0; i < len; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			char c = line.charAt(i);
			//whitespace, colon, or equals characters represent key separators
			if (Character.isWhitespace(c) || c == ':' || c == '=') {
				break;
			}
			key.append(c); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		return key.toString();
	}

	/**
	 * Returns whether the property value on this line will be continued onto the next.
	 */
	private boolean isContinued(String line) {
		//note that literal escaped slash characters at the end of a line are not
		//treated as continuation markers.
		boolean continuation = false;
		for (int i = line.length() - 1; (i >= 0) && (line.charAt(i) == '\\'); i--) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			continuation = !continuation;
		}
		return continuation;
	}

	/**
	 * Returns whether the given line contains a key that needs to be converted.
	 */
	private boolean skipLine(String line) {
		if (line.isEmpty()) {
			return true;
		}
		char first = line.charAt(0);
		return first == '#' || first == '!';
	}
}
