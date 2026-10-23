/*******************************************************************************
 * Copyright (c) 2006, 2017 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.team.internal.ui.registry;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.team.internal.ui.TeamUIPlugin;
import org.eclipse.team.ui.mapping.ITeamContentProviderDescriptor;

public class TeamDecoratorManager {

	public static final String PT_TEAM_DECORATORS = "teamDecorators"; //$NON-NLS-1$

	private static TeamDecoratorManager instance;

	Map<String, TeamDecoratorDescription> descriptors;

	public static TeamDecoratorManager getInstance() {
		if (instance == null) {
			instance = new TeamDecoratorManager();
		}
		return instance;
	}

	public ITeamContentProviderDescriptor[] getDescriptors() {
		lazyInitialize();
		return descriptors.values().toArray(new ITeamContentProviderDescriptor[descriptors.size()]);
	}

	public TeamDecoratorDescription getDecoratorDescription(String providerId) {
		lazyInitialize();
		return descriptors.get(providerId);
	}

	protected void lazyInitialize() { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		if (descriptors != null) {
			return;
		}
		IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(TeamUIPlugin.ID, PT_TEAM_DECORATORS);
		IExtension[] extensions = point.getExtensions();
		descriptors = new HashMap<>(extensions.length * 2 + 1); //FLLDCIM: MARK — M01 M3_HASH_LANES NEW
		for (IExtension extension : extensions) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			TeamDecoratorDescription desc = null;
			try {
				desc = new TeamDecoratorDescription(extension);
			} catch (CoreException e) {
				TeamUIPlugin.log(e);
			}
			if (desc != null) {
				descriptors.put(desc.getRepositoryId(), desc);
			}
		}
	}
}
