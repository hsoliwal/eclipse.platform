/*******************************************************************************
 * Copyright (c) 2000, 2017, 2023 IBM Corporation and others.
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
 *     Nikifor Fedorov (ArSysOp) - Use equinox preferences APIs in UserStringMappings #497
 *******************************************************************************/

package org.eclipse.team.internal.core;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.team.core.Team;
import org.osgi.service.prefs.BackingStoreException;

public class UserStringMappings {

	public static final Integer BINARY = Integer.valueOf(Team.BINARY);
	public static final Integer TEXT = Integer.valueOf(Team.TEXT);
	public static final Integer UNKNOWN = Integer.valueOf(Team.UNKNOWN);

	private static final String PREF_TEAM_SEPARATOR = "\n"; //$NON-NLS-1$
	private static final String EMPTY_PREF = ""; //$NON-NLS-1$

	private final String fKey;

	private Map<String, Integer> fMap;

	public UserStringMappings(String key) {
		fKey = key;
		InstanceScope.INSTANCE.getNode(TeamPlugin.ID).addPreferenceChangeListener(this::preferenceChanged);
	}

	public Map<String, Integer> referenceMap() {
		if (fMap == null) {
			fMap = loadMappingsFromPreferences();
		}
		return fMap;
	}

	public void addStringMappings(String[] names, int[] types) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		Assert.isTrue(names.length == types.length);
		final Map<String, Integer> map = referenceMap();

		for (int i = 0; i < names.length; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			int type = types[i];
			switch (type) {
			case Team.BINARY:
				map.put(names[i], BINARY);
				break;
			case Team.TEXT:
				map.put(names[i], TEXT);
				break;
			case Team.UNKNOWN:
				map.put(names[i], UNKNOWN);
				break;
			default:
				throw new IllegalArgumentException(Integer.toString(type));
			}
		}
		save();
	}

	public void setStringMappings(String[] names, int[] types) {
		Assert.isTrue(names.length == types.length);
		referenceMap().clear();
		addStringMappings(names, types);
	}

	public int getType(String string) {
		if (string == null) {
			return Team.UNKNOWN;
		}
		final Integer type = referenceMap().get(string);
		return type != null ? type.intValue() : Team.UNKNOWN;
	}

	public void save() { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		// Now set into preferences
		final StringBuilder buffer = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		for (String filename : fMap.keySet()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			buffer.append(filename); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			buffer.append(PREF_TEAM_SEPARATOR); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			final Integer type = fMap.get(filename);
			buffer.append(type); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			buffer.append(PREF_TEAM_SEPARATOR); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		try {
			IEclipsePreferences node = InstanceScope.INSTANCE.getNode(TeamPlugin.ID);
			node.put(fKey, buffer.toString());
			node.flush();
		} catch (BackingStoreException ex) {
			TeamPlugin.log(IStatus.ERROR, ex.getMessage(), ex);
		}
	}

	protected Map<String, Integer> loadMappingsFromPreferences() {
		final Map<String, Integer> result = new HashMap<>(); //FLLDCIM: MARK — M01 M3_HASH_LANES NEW

		if (!nodeAccessibleAndExists(fKey)) {
			return result;
		}
		final StringTokenizer tok = new StringTokenizer(mappings(), PREF_TEAM_SEPARATOR);
		try {
			while (tok.hasMoreElements()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				final String name = tok.nextToken();
				final String mode = tok.nextToken();
				result.put(name, Integer.valueOf(mode));
			}
		} catch (NoSuchElementException e) {
		}
		return result;
	}

	private String mappings() {
		return Optional.ofNullable(InstanceScope.INSTANCE.getNode(TeamPlugin.ID)) //
				.map(node -> node.get(fKey, null)) //
				.orElse(EMPTY_PREF);
	}

	private boolean nodeAccessibleAndExists(String key) {
		try {
			IEclipsePreferences node = InstanceScope.INSTANCE.getNode(TeamPlugin.ID);
			return Stream.of(node.keys()).anyMatch(candidate -> key.equals(candidate));
		} catch (BackingStoreException e) {
			TeamPlugin.log(IStatus.ERROR, e.getMessage(), e);
			return false;
		}
	}

	private void preferenceChanged(PreferenceChangeEvent event) {
		if (fKey.equals(event.getKey())) {
			fMap = null;
		}
	}
}
