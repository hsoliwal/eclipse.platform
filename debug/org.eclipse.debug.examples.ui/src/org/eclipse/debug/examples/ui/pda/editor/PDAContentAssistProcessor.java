/*******************************************************************************
 * Copyright (c) 2005, 2018 IBM Corporation and others.
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
 *     Bjorn Freeman-Benson - initial API and implementation
 *******************************************************************************/
package org.eclipse.debug.examples.ui.pda.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

public class PDAContentAssistProcessor implements IContentAssistProcessor {

	@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
		int index = offset - 1;
		StringBuilder prefix = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		IDocument document = viewer.getDocument();
		while (index > 0) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			try {
				char prev = document.getChar(index);
				if (Character.isWhitespace(prev)) {
					break;
				}
				prefix.insert(0, prev);
				index--;
			} catch (BadLocationException e) {
			}
		}

		List<CompletionProposal> proposals = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
		String[] keywords = PDAScanner.fgKeywords;
		if (prefix.length() > 0) {
			String word = prefix.toString();
			for (int i = 0; i < keywords.length; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				String keyword = keywords[i];
				if (keyword.startsWith(word) && word.length() < keyword.length()) {
					proposals.add(new CompletionProposal(keyword + " ", index + 1, offset - (index + 1), keyword.length() + 1)); //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
				}
			}
		} else {
			// propose all keywords
			for (int i = 0; i < keywords.length; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				String keyword = keywords[i];
				proposals.add(new CompletionProposal(keyword + " ", offset, 0, keyword.length() + 1)); //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
			}
		}
		if (!proposals.isEmpty()) {
			return proposals.toArray(new ICompletionProposal[proposals.size()]);
		}
		return null;
	}

	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
		return null;
	}

	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		return null;
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	@Override
	public String getErrorMessage() {
		return null;
	}

	@Override
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}
}
