/*******************************************************************************
 * Copyright (c) 2000, 2015 IBM Corporation and others.
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
package org.eclipse.core.internal.resources;

import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.*;

public class ResourceStatus extends Status implements IResourceStatus {
	IPath path;

	public ResourceStatus(int type, int code, IPath path, String message, Throwable exception) {
		super(type, ResourcesPlugin.PI_RESOURCES, code, message, exception);
		this.path = path;
	}

	public ResourceStatus(int code, String message) {
		this(getSeverity(code), code, null, message, null);
	}

	public ResourceStatus(int code, IPath path, String message) {
		this(getSeverity(code), code, path, message, null);
	}

	public ResourceStatus(int code, IPath path, String message, Throwable exception) {
		this(getSeverity(code), code, path, message, exception);
	}

	/**
	 * @see IResourceStatus#getPath()
	 */
	@Override
	public IPath getPath() {
		return path;
	}

	protected static int getSeverity(int code) {
		return code == 0 ? 0 : 1 << (code % 100 / 33);
	}

	// for debug only
	private String getTypeName() {
		switch (getSeverity()) {
			case IStatus.OK :
				return "OK"; //$NON-NLS-1$
			case IStatus.ERROR :
				return "ERROR"; //$NON-NLS-1$
			case IStatus.INFO :
				return "INFO"; //$NON-NLS-1$
			case IStatus.WARNING :
				return "WARNING"; //$NON-NLS-1$
			default :
				return String.valueOf(getSeverity());
		}
	}

	// for debug only
	@Override
	public String toString() {
		return "[type: " + getTypeName() + "], [path: " + getPath() + "], [message: " + getMessage() + "], [plugin: " + getPlugin() + "], [exception: " + getException() + "]\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	}
}
