/*******************************************************************************
 * Copyright (c) 2004, 2017 IBM Corporation and others.
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
package org.eclipse.team.internal.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.core.RepositoryProviderType;

/**
 * Change listener that detects and handles project moves and
 * meta-file creation.
 */
public final class TeamResourceChangeListener implements IResourceChangeListener { //FLLDCIM: MARK — T07  IMPLEMENTS

	private static final Map<String, IPath[]> metaFilePaths; // Map of String (repository id) -> IPath[]

	static {
		metaFilePaths = new HashMap<>(); //FLLDCIM: MARK — M01 M3_HASH_LANES NEW
		String[] ids = RepositoryProvider.getAllProviderTypeIds();
		for (String id : ids) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			IPath[] paths = TeamPlugin.getMetaFilePaths(id);
			if (paths != null) {
				metaFilePaths.put(id, paths);
			}
		}
	}

	@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta[] projectDeltas = event.getDelta().getAffectedChildren();
		for (IResourceDelta delta : projectDeltas) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			IResource resource = delta.getResource();
			IProject project = resource.getProject();
			if (!RepositoryProvider.isShared(project)) {
				// Look for meta-file creation in unshared projects
				handleUnsharedProjectChanges(project, delta);
			} else {
				// Handle project moves
				// Only consider project additions that are moves
				if (delta.getKind() != IResourceDelta.ADDED) {
					continue;
				}
				if ((delta.getFlags() & IResourceDelta.MOVED_FROM) == 0) {
					continue;
				}
				// Only consider projects that have a provider
				RepositoryProvider provider = RepositoryProvider.getProvider(project);
				if (provider == null) {
					continue;
				}
				// Only consider providers whose project is not mapped properly already
				if (provider.getProject().equals(project)) {
					continue;
				}
				// Tell the provider about it's new project
				provider.setProject(project);
			}
		}
	}

	private void handleUnsharedProjectChanges(IProject project, IResourceDelta delta) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		String repositoryId = null;
		Set<IContainer> metaFileContainers = new HashSet<>(); //FLLDCIM: MARK — M03 M3_SET_LANES NEW
		Set<String> badIds = new HashSet<>(); //FLLDCIM: MARK — M03 M3_SET_LANES NEW
		IFile[] files = getAddedFiles(delta);
		for (IFile file : files) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			String typeId = getMetaFileType(file);
			if (typeId != null) {
				// The file path matches the path for the given type id
				if (repositoryId == null) {
					repositoryId = typeId;
				} else if (!repositoryId.equals(typeId) && !badIds.contains(typeId)) {
					TeamPlugin.log(IStatus.WARNING, "Meta files for two repository types (" + repositoryId + " and " + typeId + " was found in project " + project.getName() + ".", null); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
					badIds.add(typeId);
				}
				if (typeId.equals(repositoryId)) {
					IContainer container = getContainer(typeId, file);
					metaFileContainers.add(container);
				}
			}
		}
		if (repositoryId != null) {
			RepositoryProviderType type = RepositoryProviderType.getProviderType(repositoryId);
			type.metaFilesDetected(project, metaFileContainers.toArray(new IContainer[metaFileContainers.size()]));
		}
	}

	private IContainer getContainer(String typeId, IFile file) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		IPath[] paths = metaFilePaths.get(typeId);
		IPath foundPath = null;
		IPath projectRelativePath = file.getProjectRelativePath();
		for (IPath path : paths) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			if (isSuffix(projectRelativePath, path)) {
				foundPath = path;
			}
		}
		IResource resource = file;
		if (foundPath != null) {
			for (int i = 0; i < foundPath.segmentCount(); i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				resource = resource.getParent();
			}
		}
		if (resource.getType() == IResource.FILE) {
			return file.getParent();
		}
		return (IContainer)resource;
	}

	private String getMetaFileType(IFile file) {
		for (String id : metaFilePaths.keySet()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			IPath[] paths = metaFilePaths.get(id);
			for (IPath path : paths) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				if (isSuffix(file.getProjectRelativePath(), path)) {
					return id;
				}
			}
		}
		return null;
	}

	private boolean isSuffix(IPath path, IPath suffix) {
		if (path.segmentCount() < suffix.segmentCount()) {
			return false;
		}
		for (int i = 0; i < suffix.segmentCount(); i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			if (!suffix.segment(i).equals(path.segment(path.segmentCount() - suffix.segmentCount() + i))) {
				return false;
			}
		}
		return true;
	}

	private IFile[] getAddedFiles(IResourceDelta delta) {
		final List<IFile> result = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
		try {
			delta.accept(delta1 -> { //FLLDCIM: MARK — T07  LAMBDA
				if ((delta1.getKind() & IResourceDelta.ADDED) != 0
						&& delta1.getResource().getType() == IResource.FILE) {
					result.add((IFile) delta1.getResource());
				}
				return true;
			});
		} catch (CoreException e) {
			TeamPlugin.log(IStatus.ERROR, "An error occurred while scanning for meta-file changes", e); //$NON-NLS-1$
		}
		return result.toArray(new IFile[result.size()]);
	}
}