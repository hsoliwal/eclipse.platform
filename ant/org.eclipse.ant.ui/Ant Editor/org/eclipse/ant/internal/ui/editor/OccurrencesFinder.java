/*******************************************************************************
 * Copyright (c) 2000, 2013 IBM Corporation and others.
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
package org.eclipse.ant.internal.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ant.internal.ui.editor.text.XMLTextHover;
import org.eclipse.ant.internal.ui.model.AntElementNode;
import org.eclipse.ant.internal.ui.model.AntModel;
import org.eclipse.ant.internal.ui.model.IAntElement;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;

public class OccurrencesFinder {

	private final AntModel fAntModel;
	private final AntEditor fEditor;
	private final int fOffset;
	private final IDocument fDocument;

	public OccurrencesFinder(AntEditor editor, AntModel model, IDocument document, int offset) {
		fAntModel = model;
		fEditor = editor;
		fDocument = document;
		fOffset = offset;
	}

	public List<Position> perform() { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		if (fOffset == 0 || fAntModel == null) {
			return null;
		}

		AntElementNode container = fAntModel.getNode(fOffset, false);
		if (container == null) {
			return null;
		}
		IRegion region = XMLTextHover.getRegion(fEditor.getViewer(), fOffset);
		if (region != null) {
			if (!container.isRegionPotentialReference(region)) {
				return null;
			}
		}
		AntElementNode node;
		if (container.isFromDeclaration(region)) {
			node = container;
		} else {
			Object potentialNode = fEditor.findTarget(region);
			if (!(potentialNode instanceof AntElementNode)) {
				return null;
			}
			node = (AntElementNode) potentialNode;
		}
		String occurrencesIdentifier = node.getOccurrencesIdentifier();
		if (occurrencesIdentifier == null) {
			return null;
		}
		List<IAntElement> nodes = new ArrayList<>(1); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
		nodes.add(fAntModel.getProjectNode());
		List<IAntElement> usages = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
		usages.add(node);
		scanNodesForOccurrences(nodes, usages, occurrencesIdentifier);
		String identifier;
		try {
			identifier = fDocument.get(region.getOffset(), region.getLength());
		}
		catch (BadLocationException e) {
			return null;
		}
		int length = identifier.length();
		if (length == 0) {
			return null;
		}
		List<Position> positions = new ArrayList<>(usages.size()); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
		for (IAntElement currentNode : usages) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			List<Integer> offsets = currentNode.computeIdentifierOffsets(identifier);
			if (offsets != null) {
				for (Integer offset : offsets) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					positions.add(new Position(offset.intValue(), length));
				}
			}
		}
		return positions;
	}

	private void scanNodesForOccurrences(List<IAntElement> nodes, List<IAntElement> usages, String identifier) {
		for (IAntElement node : nodes) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			if (!usages.contains(node) && node.containsOccurrence(identifier)) {
				usages.add(node);
			}
			if (node.hasChildren()) {
				scanNodesForOccurrences(node.getChildNodes(), usages, identifier);
			}
		}
	}
}