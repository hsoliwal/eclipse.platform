/*******************************************************************************
 * Copyright (c) 2005, 2016 IBM Corporation and others.
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

package org.eclipse.ant.internal.launching.debug;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.sourcelookup.ISourceContainerType;
import org.eclipse.debug.core.sourcelookup.containers.AbstractSourceContainer;
import org.eclipse.debug.core.sourcelookup.containers.LocalFileStorage;

public class AntSourceContainer extends AbstractSourceContainer {

	private final IWorkspaceRoot fRoot;

	public AntSourceContainer() {
		fRoot = ResourcesPlugin.getWorkspace().getRoot();
	}

	@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public Object[] findSourceElements(String path) throws CoreException {
		ArrayList<IStorage> sources = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
		File osFile = new File(path);
		if (osFile.exists()) {
			try {
				IPath canonicalPath = IPath.fromOSString(osFile.getCanonicalPath());
				IFile[] files = fRoot.findFilesForLocationURI(canonicalPath.makeAbsolute().toFile().toURI());
				if (files.length > 0) {
					for (IFile file : files) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
						sources.add(file);
					}
				} else {
					sources.add(new LocalFileStorage(osFile));
				}
			}
			catch (IOException e) {
				// do nothing
			}
		}
		return sources.toArray();
	}

	@Override
	public String getName() {
		return AntDebugMessages.AntSourceContainer_0;
	}

	@Override
	public ISourceContainerType getType() {
		return null;
	}
}