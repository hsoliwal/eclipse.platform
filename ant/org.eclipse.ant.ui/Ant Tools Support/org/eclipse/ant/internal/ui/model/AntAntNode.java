/*******************************************************************************
 * Copyright (c) 2005, 2011 IBM Corporation and others.
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
package org.eclipse.ant.internal.ui.model;

import java.io.File;

import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.FileUtils;
import org.eclipse.ant.internal.core.IAntCoreConstants;
import org.eclipse.ant.internal.ui.editor.AntEditorCompletionProcessor;
import org.xml.sax.Attributes;

public class AntAntNode extends AntTaskNode {

	private String fFile;

	public AntAntNode(Task task, Attributes attributes) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		super(task);
		StringBuilder label = new StringBuilder("ant "); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		fFile = attributes.getValue(IAntCoreConstants.DIR);
		if (fFile != null) {
			if (!FileUtils.isAbsolutePath(fFile)) {
				File basedir = task.getProject().getBaseDir();
				if (basedir != null) {
					fFile = basedir.getAbsolutePath() + File.separatorChar + fFile; //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
				}
			}
			label.append(fFile); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			label.append(File.separatorChar); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		} else {
			File basedir = task.getProject().getBaseDir();
			if (basedir != null) {
				fFile = basedir.getAbsolutePath();
			}
		}
		String fileName = attributes.getValue(IAntModelConstants.ATTR_ANT_FILE);
		if (fileName == null) {
			fileName = "build.xml"; //$NON-NLS-1$
		}
		label.append(fileName); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		if (fFile == null || FileUtils.isAbsolutePath(fileName)) {
			fFile = fileName;
		} else {
			fFile += File.separatorChar + fileName; //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		}

		String more = attributes.getValue(IAntModelConstants.ATTR_TARGET);
		if (more != null) {
			label.append(' '); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			label.append(more); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		setBaseLabel(label.toString());
	}

	@Override
	public String getReferencedElement(int offset) {
		if (fFile != null) {
			String textToSearch = getAntModel().getText(getOffset(), offset - getOffset());
			if (textToSearch != null && textToSearch.length() != 0) {
				String attributeString = AntEditorCompletionProcessor.getAttributeStringFromDocumentStringToPrefix(textToSearch);
				if (IAntModelConstants.ATTR_ANT_FILE.equals(attributeString)) {
					return fFile;
				}
			}
		}
		return null;
	}
}
