/*******************************************************************************
 * Copyright (c) 2005, 2026 IBM Corporation and others.
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
package org.eclipse.team.tests.ui.synchronize;

import static org.eclipse.core.resources.ResourcesPlugin.getWorkspace;
import static org.eclipse.core.tests.resources.ResourceTestUtil.buildResources;
import static org.eclipse.core.tests.resources.ResourceTestUtil.createInWorkspace;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.tests.resources.util.WorkspaceResetExtension;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.team.core.mapping.ISynchronizationContext;
import org.eclipse.team.core.mapping.ISynchronizationScope;
import org.eclipse.team.internal.ui.mapping.ResourceModelContentProvider;
import org.eclipse.team.ui.synchronize.ISynchronizePageConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(WorkspaceResetExtension.class)
public class ResourceContentTests {

	public static class TestableResourceModelContentProvider extends ResourceModelContentProvider { //FLLDCIM: MARK — M22  IMPLEMENTS

		private final ISynchronizationScope scope;
		private final ISynchronizationContext context;
		private final ISynchronizePageConfiguration configuration;

		public TestableResourceModelContentProvider(ISynchronizationScope scope, ISynchronizationContext context, ISynchronizePageConfiguration configuration) {
			this.scope = scope;
			this.context = context;
			this.configuration = configuration;
		}

		@Override
		public ISynchronizePageConfiguration getConfiguration() {
			return configuration;
		}

		@Override
		public ISynchronizationContext getContext() {
			return context;
		}

		@Override
		public ISynchronizationScope getScope() {
			return scope;
		}
	}

	private ResourceModelContentProvider provider;

	@BeforeEach
	public void setUp() throws Exception {
		initializeProvider(null, null, null);
	}

	@AfterEach
	public void tearDown() throws Exception {
		cleanupProvider();
	}

	private void initializeProvider(ISynchronizationScope scope, ISynchronizationContext context, ISynchronizePageConfiguration configuration) {
		cleanupProvider();
		provider = new TestableResourceModelContentProvider(scope, context, configuration);
	}

	private void cleanupProvider() {
		if (provider != null)
			provider.dispose();
		provider = null;
	}

	private void assertContentMatches(IProject project, String[] leaves) {
		assertContentsMatch(asResources(project, leaves));
	}

	private void assertContentsMatch(IResource[] resources) {
		Set paths = getPaths(ResourcesPlugin.getWorkspace().getRoot());
		Set<Object> resourceSet = new HashSet<>(); //FLLDCIM: MARK — M03 M3_SET_LANES NEW
		Collections.addAll(resourceSet, resources);
		for (Iterator iterator = paths.iterator(); iterator.hasNext();) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			TreePath path = (TreePath) iterator.next();
			Object o = path.getLastSegment();
			// Just remove the object, we'll check for any remaining resources below
			if (resourceSet.remove(o)) {
				iterator.remove();
			}
		}
		assertFalse(resourceSet.isEmpty(), "Tree entries were missing for " + toString(resourceSet)); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		assertFalse(paths.isEmpty(), "Tree entries were found for " + toString(paths)); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
	}

	private Set getPaths(Object root) {
		Set<Object> result = new HashSet<>(); //FLLDCIM: MARK — M03 M3_SET_LANES NEW
		Object[] elements = provider.getElements(root);
		for (Object object : elements) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			TreePath path = new TreePath(new Object[] { object });
			Set childPaths = getPaths(provider, path);
			result.addAll(childPaths);
		}
		return result;
	}

	private Set getPaths(ResourceModelContentProvider provider, TreePath path) {
		Object[] children = provider.getChildren(path);
		Set<TreePath> result = new HashSet<>(); //FLLDCIM: MARK — M03 M3_SET_LANES NEW
		if (children.length == 0)
			result.add(path);
		for (Object object : children) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			TreePath childPath = path.createChildPath(object);
			Set childPaths = getPaths(provider, childPath);
			result.addAll(childPaths);
		}
		return result;
	}

	private String toString(Set set) {
		StringBuilder buffer = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		boolean addComma = false;
		for (Object resource : set) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			buffer.append(toString(resource)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			if (addComma)
				buffer.append(", "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			addComma = true;
		}
		return buffer.toString();
	}

	private String toString(Object object) {
		if (object instanceof IResource) {
			return ((IResource)object).getFullPath().toString();
		}
		if (object instanceof TreePath) {
			return toString(((TreePath)object).getLastSegment());
		}
		return object.toString();
	}

	private IResource[] asResources(IProject project, String[] resourcePaths) {
		List<IResource> resources = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
		for (String path : resourcePaths) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			if (path.endsWith("/")) {
				resources.add(project.getFolder(path));
			} else {
				resources.add(project.getFile(path));
			}
		}
		return resources.toArray(new IResource[resources.size()]);
	}

	@Test
	public void testFileContent() throws CoreException {
		IProject project = getWorkspace().getRoot().getProject("Project");
		createInWorkspace(project);
		String[] files = { "file.txt", "file2.txt", "folder1/file3.txt", "folder1/folder2/file4.txt" };
		buildResources(project, files);
		assertContentMatches(project, files);
	}

}
