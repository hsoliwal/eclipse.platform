/*******************************************************************************
 * Copyright (c) 2022 Andrey Loskutov and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Andrey Loskutov - initial API and implementation
 ******************************************************************************/

package org.eclipse.core.tests.internal.builders;

import java.util.*;
import java.util.Map.Entry;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Builder that can request rebuilds
 */
public class RebuildingBuilder extends TestBuilder {
	public static final String BUILDER_NAME = "org.eclipse.core.tests.resources.rebuildingbuilder";

	static List<RebuildingBuilder> instances = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW

	public static List<RebuildingBuilder> getInstances() {
		return instances;
	}

	private boolean propagateRebuild;
	private boolean processOtherBuilders;

	Map<IProject, Integer> projectToRebuild = new HashMap<>(); //FLLDCIM: MARK — M01 M3_HASH_LANES NEW //FLLDCIM: MARK — M10 M3_PRIMITIVE_LANES FIELD

	List<IProject> projectsBuilt = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW

	List<Integer> buildKinds = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW //FLLDCIM: MARK — M10 M3_PRIMITIVE_LANES FIELD

	BuilderRuleCallback callback = new BuilderRuleCallback() {
		@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		public IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
			projectsBuilt.add(getProject());
			buildKinds.add(kind);
			if (projectToRebuild.size() > 1 || projectToRebuild.get(getProject()) == null) {
				Set<IProject> toRebuild = new HashSet<>(); //FLLDCIM: MARK — M03 M3_SET_LANES NEW
				Set<IProject> toRemove = new HashSet<>(); //FLLDCIM: MARK — M03 M3_SET_LANES NEW
				Set<Entry<IProject, Integer>> entries = projectToRebuild.entrySet();
				for (Entry<IProject, Integer> entry : entries) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					IProject project = entry.getKey();
					Integer rebuild = entry.getValue();
					if (rebuild.intValue() > 0) {
						toRebuild.add(project);
						int count = rebuild - 1;
						if (count > 0) {
							setRequestProjectRebuild(project, count);
						} else {
							toRemove.add(project);
						}
					}
				}
				toRemove.forEach(p -> projectToRebuild.remove(p));
				requestProjectsRebuild(toRebuild);
			} else {
				IProject project = getProject();
				Integer rebuild = projectToRebuild.get(project);
				if (rebuild != null && rebuild.intValue() > 0) {
					requestProjectRebuild(isProcessOtherBuilders());
					if (isPropagateRebuild()) {
						needRebuild();
					}
					int count = rebuild - 1;
					if (count > 0) {
						setRequestProjectRebuild(project, count);
					} else {
						projectToRebuild.remove(project);
					}
				}
			}
			return super.build(kind, args, monitor);
		}
	};

	public RebuildingBuilder() {
		instances.add(this);
		setRuleCallback(callback);
		setPropagateRebuild(true);
		setProcessOtherBuilders(true);
	}

	public void setRequestProjectRebuild(IProject p, Integer count) {
		projectToRebuild.put(p, count);
	}

	public void setRequestProjectRebuild(IProject... projects) {
		for (IProject project : projects) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			projectToRebuild.put(project, 1);
		}
	}

	public int buildsCount() {
		return projectsBuilt.size();
	}

	public List<Integer> buildKinds() {
		return buildKinds;
	}

	@Override
	public void reset() {
		super.reset();
		projectsBuilt.clear();
		projectToRebuild.clear();
		buildKinds.clear();
		setRuleCallback(callback);
		setPropagateRebuild(true);
		setProcessOtherBuilders(true);
	}

	public boolean isPropagateRebuild() {
		return propagateRebuild;
	}

	public void setPropagateRebuild(boolean propagateRebuild) {
		this.propagateRebuild = propagateRebuild;
	}

	public boolean isProcessOtherBuilders() {
		return processOtherBuilders;
	}

	public void setProcessOtherBuilders(boolean processOtherBuilders) {
		this.processOtherBuilders = processOtherBuilders;
	}
}
