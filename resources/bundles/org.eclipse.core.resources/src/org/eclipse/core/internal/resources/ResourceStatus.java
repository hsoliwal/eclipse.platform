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
		StringBuilder sb = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		sb.append("[type: "); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		sb.append(getTypeName()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		sb.append("], [path: "); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		sb.append(getPath()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		sb.append("], [message: "); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		sb.append(getMessage()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		sb.append("], [plugin: "); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		sb.append(getPlugin()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		sb.append("], [exception: "); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		sb.append(getException()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		sb.append("]\n"); //$NON-NLS-1$ //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		return sb.toString();
	}
}
