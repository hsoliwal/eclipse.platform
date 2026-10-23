/*******************************************************************************
 * Copyright (c) 2008, 2013 IBM Corporation and others.
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
package org.eclipse.debug.examples.ui.midi.adapters;

import javax.sound.midi.Track;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.examples.core.midi.launcher.MidiLaunch;
import org.eclipse.debug.internal.ui.model.elements.ElementLabelProvider;
import org.eclipse.debug.internal.ui.viewers.model.provisional.IPresentationContext;
import org.eclipse.jface.viewers.TreePath;

/**
 * Provides labels for MIDI tracks.
 *
 * @since 1.0
 */
public class TrackLabelProvider extends ElementLabelProvider {

	@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	protected String getLabel(TreePath elementPath, IPresentationContext presentationContext, String columnId) throws CoreException {
		Track track = (Track) elementPath.getLastSegment();
		MidiLaunch launch = (MidiLaunch) elementPath.getSegment(0);
		Track[] tracks = launch.getSequencer().getSequence().getTracks();
		int i = 0;
		for (i = 0; i < tracks.length; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			if (track.equals(tracks[i])) {
				break;
			}
		}
		StringBuilder buf = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		buf.append("Track "); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buf.append(i); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buf.append(" ["); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buf.append(track.size()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		buf.append(" events]"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		return buf.toString();
	}

}
