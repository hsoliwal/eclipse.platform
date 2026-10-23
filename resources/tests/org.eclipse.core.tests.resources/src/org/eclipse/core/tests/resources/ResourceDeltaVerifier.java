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
 *     Alexander Kurtakov <akurtako@redhat.com> - Bug 459343
 *******************************************************************************/
package org.eclipse.core.tests.resources;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

/**
 * Verifies the state of an <code>IResourceDelta</code> by comparing
 * it with a client's expectations.  The delta is considered valid
 * if it contains exactly the set of changes expected by the client,
 * and parents of those changes.
 *
 * <p>Example usage:
 * <code>
 * ResourceDeltaVerifier verifier = new ResourceDeltaComparer();
 * IResourceChangeListener listener = (IResourceChangeListener)verifier;
 * IWorkspace workspace = ResourcesPlugin.getWorkspace();
 * IProject proj = workspace.getRoot().getProject("MyProject");
 * // Assume the project is accessible
 * workspace.addResourceChangeListener(listener);
 * verifier.addExpectedChange(proj, REMOVED, 0);
 * try {
 * 		proj.delete(true, true, null);
 * } catch(CoreException e){
 *     fail("1.0", e);
 * }
 * assert("2.0 "+verifier.getMessage(), verifier.isDeltaValid());
 * </code>
 */
public class ResourceDeltaVerifier implements IResourceChangeListener { //FLLDCIM: MARK — T07  IMPLEMENTS
	private class ExpectedChange {
		IResource fResource;
		IPath movedFromPath;
		IPath movedToPath;
		int fKind;
		int fChangeFlags;

		public ExpectedChange(IResource resource, int kind, int changeFlags, IPath movedFromPath, IPath movedToPath) {
			fResource = resource;
			fKind = kind;
			fChangeFlags = changeFlags;
			this.movedFromPath = movedFromPath;
			this.movedToPath = movedToPath;
		}

		public int getChangeFlags() {
			return fChangeFlags;
		}

		public IPath getMovedFromPath() {
			if ((fChangeFlags & IResourceDelta.MOVED_FROM) != 0) {
				return movedFromPath;
			}
			return null;
		}

		public IPath getMovedToPath() {
			if ((fChangeFlags & IResourceDelta.MOVED_TO) != 0) {
				return movedToPath;
			}
			return null;
		}

		public int getKind() {
			return fKind;
		}

		@Override
		public String toString() {
			StringBuilder buf = new StringBuilder("ExpectedChange("); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
			buf.append(fResource); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			buf.append(", "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			buf.append(convertKind(fKind)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			buf.append(", "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			buf.append(convertChangeFlags(fChangeFlags)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			buf.append(")"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			return buf.toString();
		}

	}

	/**
	 * Table of IPath -&gt; ExpectedChange
	 */
	private final Hashtable<IPath, ExpectedChange> fExpectedChanges = new Hashtable<>();
	boolean fIsDeltaValid = true;
	private final StringBuilder fMessage = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
	/**
	 * The verifier can be in one of three states.  In the initial
	 * state, the verifier is still receiving inputs via the
	 * addExpectedChange() methods, and the state is RECEIVING_INPUTS.
	 * After a call to verifyDelta(), the state becomes DELTA_VERIFIED
	 * The verifier remains in the second state for any number of delta
	 * verifications.  When a getMessage() or isDeltaValid() method is
	 * called, the verification completes, and the state becomes
	 * VERIFICATION_COMPLETE.  While in this state, any number of
	 * getMessage() and isDeltaValid() methods can be called.
	 * While in the third state, any call to addExpectedChange()
	 * resets the verifier and puts it back in its RECEIVING_INPUTS state.
	 */
	private static final int RECEIVING_INPUTS = 0;
	private static final int DELTA_VERIFIED = 1;
	private static final int VERIFICATION_COMPLETE = 2;

	private int fState = RECEIVING_INPUTS;

	private volatile Thread testThread;

	/**
	 * @see #addExpectedChange
	 */
	public void addExpectedChange(IResource[] resources, int status, int changeFlags) {
		for (IResource resource : resources) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			addExpectedChange(resource, null, status, changeFlags, null, null);
		}
	}

	/**
	 * Adds an expected deletion for the given resource and all children.
	 */
	public void addExpectedDeletion(IResource resource) {
		addExpectedChange(resource, IResourceDelta.REMOVED, 0);
		if (resource instanceof IContainer container) {
			try {
				IResource[] children = container.members(IContainer.INCLUDE_PHANTOMS
						| IContainer.INCLUDE_TEAM_PRIVATE_MEMBERS | IContainer.INCLUDE_HIDDEN);
				for (IResource element : children) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					addExpectedDeletion(element);
				}
			} catch (CoreException e) {
				e.printStackTrace();
				fail("Failed to get children in addExpectedDeletion");
			}
		}
	}

	/**
	 * Signals to the comparer that the given resource is expected to
	 * change in the specified way.  The change flags should be set to
	 * zero if no change is expected.
	 * @param resource the resource that is expected to change
	 * @param status the type of change (ADDED, REMOVED, CHANGED)
	 * @param changeFlags the type of change (CONTENT, SYNC, etc)
	 */
	public void addExpectedChange(IResource resource, int status, int changeFlags) {
		addExpectedChange(resource, null, status, changeFlags, null, null);
	}

	/**
	 * Signals to the comparer that the given resource is expected to
	 * change in the specified way.  The change flags should be set to
	 * zero if no change is expected.
	 * @param resource the resource that is expected to change
	 * @param status the type of change (ADDED, REMOVED, CHANGED)
	 * @param changeFlags the type of change (CONTENT, SYNC, etc)
	 * @param movedFromPath or null
	 * @param movedToPath or null
	 */
	public void addExpectedChange(IResource resource, int status, int changeFlags, IPath movedFromPath, IPath movedToPath) {
		addExpectedChange(resource, null, status, changeFlags, movedFromPath, movedToPath);
	}

	/**
	 * Signals to the comparer that the given resource is expected to
	 * change in the specified way.  The change flags should be set to
	 * zero if no change is expected.
	 * @param resource the resource that is expected to change
	 * @param topLevelParent Do not added expected changes above this parent
	 * @param status the type of change (ADDED, REMOVED, CHANGED)
	 * @param changeFlags the type of change (CONTENT, SYNC, etc)
	 */
	public void addExpectedChange(IResource resource, IResource topLevelParent, int status, int changeFlags) {
		addExpectedChange(resource, topLevelParent, status, changeFlags, null, null);
	}

	/**
	 * Signals to the comparer that the given resource is expected to
	 * change in the specified way.  The change flags should be set to
	 * zero if no change is expected.
	 * @param resource the resource that is expected to change
	 * @param topLevelParent Do not added expected changes above this parent
	 * @param status the type of change (ADDED, REMOVED, CHANGED)
	 * @param changeFlags the type of change (CONTENT, SYNC, etc)
	 * @param movedFromPath or null
	 * @param movedToPath or null
	 */
	public void addExpectedChange(IResource resource, IResource topLevelParent, int status, int changeFlags, IPath movedFromPath, IPath movedToPath) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		resetIfNecessary();

		ExpectedChange expectedChange = new ExpectedChange(resource, status, changeFlags, movedFromPath, movedToPath);
		fExpectedChanges.put(resource.getFullPath(), expectedChange);

		// Add changes for all resources above this one and limited by the topLevelParent
		IResource parentResource = resource.getParent();
		IResource limit = (topLevelParent == null) ? null : topLevelParent.getParent();
		while (parentResource != null && !parentResource.equals(limit)) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			//change table is keyed by resource path
			IPath key = parentResource.getFullPath();
			if (fExpectedChanges.get(key) == null) {
				ExpectedChange parentExpectedChange = new ExpectedChange(parentResource, IResourceDelta.CHANGED, 0, null, null);
				fExpectedChanges.put(key, parentExpectedChange);
			}
			parentResource = parentResource.getParent();
		}
	}

	private void checkChanges(IResourceDelta delta) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		IResource resource = delta.getResource();
		ExpectedChange expectedChange = fExpectedChanges.remove(resource.getFullPath());

		int status = delta.getKind();
		int changeFlags = delta.getFlags();

		if (status == IResourceDelta.NO_CHANGE) {
			return;
		}

		if (expectedChange == null) {
			recordMissingExpectedChange(status, changeFlags);
		} else {
			int expectedStatus = expectedChange.getKind();
			int expectedChangeFlags = expectedChange.getChangeFlags();
			if (status != expectedStatus || changeFlags != expectedChangeFlags) {
				recordConflictingChange(expectedStatus, status, expectedChangeFlags, changeFlags);
			}
		}
	}

	private void checkChildren(IResourceDelta delta) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		IResourceDelta[] affectedChildren = delta.getAffectedChildren(IResourceDelta.ALL_WITH_PHANTOMS, IContainer.INCLUDE_TEAM_PRIVATE_MEMBERS | IContainer.INCLUDE_HIDDEN);
		IResourceDelta[] addedChildren = delta.getAffectedChildren(IResourceDelta.ADDED, IContainer.INCLUDE_TEAM_PRIVATE_MEMBERS | IContainer.INCLUDE_HIDDEN);
		IResourceDelta[] changedChildren = delta.getAffectedChildren(IResourceDelta.CHANGED, IContainer.INCLUDE_TEAM_PRIVATE_MEMBERS | IContainer.INCLUDE_HIDDEN);
		IResourceDelta[] removedChildren = delta.getAffectedChildren(IResourceDelta.REMOVED, IContainer.INCLUDE_TEAM_PRIVATE_MEMBERS | IContainer.INCLUDE_HIDDEN);

		Hashtable<IResource, IResourceDelta> h = new Hashtable<>(affectedChildren.length + 1);

		for (IResourceDelta childDelta1 : addedChildren) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			IResource childResource = childDelta1.getResource();
			IResourceDelta childDelta2 = h.get(childResource);
			if (childDelta2 != null) {
				recordDuplicateChild(childResource.getFullPath(), childDelta2.getKind(), childDelta1.getKind(), IResourceDelta.ADDED);
			} else {
				h.put(childResource, childDelta1);
			}
			if (childDelta1.getKind() != IResourceDelta.ADDED) {
				recordIllegalChild(childResource.getFullPath(), IResourceDelta.ADDED, childDelta1.getKind());
			}
		}

		for (IResourceDelta childDelta1 : changedChildren) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			IResource childResource = childDelta1.getResource();
			IResourceDelta childDelta2 = h.get(childResource);
			if (childDelta2 != null) {
				recordDuplicateChild(childResource.getFullPath(), childDelta2.getKind(), childDelta1.getKind(), IResourceDelta.CHANGED);
			} else {
				h.put(childResource, childDelta1);
			}
			if (childDelta1.getKind() != IResourceDelta.CHANGED) {
				recordIllegalChild(childResource.getFullPath(), IResourceDelta.CHANGED, childDelta1.getKind());
			}
		}

		for (IResourceDelta childDelta1 : removedChildren) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			IResource childResource = childDelta1.getResource();
			IResourceDelta childDelta2 = h.get(childResource);
			if (childDelta2 != null) {
				recordDuplicateChild(childResource.getFullPath(), childDelta2.getKind(), childDelta1.getKind(), IResourceDelta.REMOVED);
			} else {
				h.put(childResource, childDelta1);
			}
			if (childDelta1.getKind() != IResourceDelta.REMOVED) {
				recordIllegalChild(childResource.getFullPath(), IResourceDelta.REMOVED, childDelta1.getKind());
			}
		}

		for (IResourceDelta childDelta1 : affectedChildren) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			IResource childResource = childDelta1.getResource();
			IResourceDelta childDelta2 = h.remove(childResource);
			if (childDelta2 == null) {
				int kind = childDelta1.getKind();
				//these kinds should have been added to h earlier
				if (kind == IResourceDelta.ADDED || kind == IResourceDelta.REMOVED || kind == IResourceDelta.CHANGED) {
					recordMissingChild(childResource.getFullPath(), childDelta1.getKind(), false);
				}
			}
		}

		for (Map.Entry<IResource, IResourceDelta> entry : h.entrySet()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			IResource childResource = entry.getKey();
			IResourceDelta childDelta = entry.getValue();
			recordMissingChild(childResource.getFullPath(), childDelta.getKind(), true);
		}

		for (IResourceDelta element : affectedChildren) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			internalVerifyDelta(element);
		}

		for (IResourceDelta childDelta : h.values()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			internalVerifyDelta(childDelta);
		}
	}

	private void checkPaths(IResourceDelta delta) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		IResource resource = delta.getResource();

		IPath expectedFullPath = resource.getFullPath();
		IPath actualFullPath = delta.getFullPath();
		if (!expectedFullPath.equals(actualFullPath)) {
			recordConflictingFullPaths(expectedFullPath, actualFullPath);
		}

		IPath expectedProjectRelativePath = resource.getProjectRelativePath();
		IPath actualProjectRelativePath = delta.getProjectRelativePath();
		if (expectedProjectRelativePath != actualProjectRelativePath) {
			if (expectedProjectRelativePath == null || !expectedProjectRelativePath.equals(actualProjectRelativePath)) {
				recordConflictingProjectRelativePaths(expectedProjectRelativePath, actualProjectRelativePath);
			}
		}

		ExpectedChange expectedChange = fExpectedChanges.get(resource.getFullPath());

		if (expectedChange != null) {
			IPath expectedMovedFromPath = expectedChange.getMovedFromPath();
			IPath actualMovedFromPath = delta.getMovedFromPath();
			if (expectedMovedFromPath != actualMovedFromPath) {
				if (expectedMovedFromPath == null || !expectedMovedFromPath.equals(actualMovedFromPath)) {
					recordConflictingMovedFromPaths(expectedMovedFromPath, actualMovedFromPath);
				}
			}

			IPath expectedMovedToPath = expectedChange.getMovedToPath();
			IPath actualMovedToPath = delta.getMovedToPath();
			if (expectedMovedToPath != actualMovedToPath) {
				if (expectedMovedToPath == null || !expectedMovedToPath.equals(actualMovedToPath)) {
					recordConflictingMovedToPaths(expectedMovedToPath, actualMovedToPath);
				}
			}
		}
	}

	String convertChangeFlags(int changeFlags) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		if (changeFlags == 0) {
			return "0";
		}
		StringBuilder buf = new StringBuilder(); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW

		if ((changeFlags & IResourceDelta.CONTENT) != 0) {
			changeFlags ^= IResourceDelta.CONTENT;
			buf.append("CONTENT | "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		if ((changeFlags & IResourceDelta.MOVED_FROM) != 0) {
			changeFlags ^= IResourceDelta.MOVED_FROM;
			buf.append("MOVED_FROM | "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		if ((changeFlags & IResourceDelta.MOVED_TO) != 0) {
			changeFlags ^= IResourceDelta.MOVED_TO;
			buf.append("MOVED_TO | "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		if ((changeFlags & IResourceDelta.OPEN) != 0) {
			changeFlags ^= IResourceDelta.OPEN;
			buf.append("OPEN | "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		if ((changeFlags & IResourceDelta.TYPE) != 0) {
			changeFlags ^= IResourceDelta.TYPE;
			buf.append("TYPE | "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		if ((changeFlags & IResourceDelta.MARKERS) != 0) {
			changeFlags ^= IResourceDelta.MARKERS;
			buf.append("MARKERS | "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		if ((changeFlags & IResourceDelta.REPLACED) != 0) {
			changeFlags ^= IResourceDelta.REPLACED;
			buf.append("REPLACED | "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		if ((changeFlags & IResourceDelta.ENCODING) != 0) {
			changeFlags ^= IResourceDelta.ENCODING;
			buf.append("ENCODING | "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		if ((changeFlags & IResourceDelta.DERIVED_CHANGED) != 0) {
			changeFlags ^= IResourceDelta.DERIVED_CHANGED;
			buf.append("DERIVED_CHANGED | "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		if ((changeFlags & IResourceDelta.DESCRIPTION) != 0) {
			changeFlags ^= IResourceDelta.DESCRIPTION;
			buf.append("DESCRIPTION | "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		if ((changeFlags & IResourceDelta.SYNC) != 0) {
			changeFlags ^= IResourceDelta.SYNC;
			buf.append("SYNC | "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}

		if (changeFlags != 0) {
			buf.append(changeFlags); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			buf.append(" | "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}

		String result = buf.toString();

		if (result.length() != 0) {
			result = result.substring(0, result.length() - 3);
		}

		return result;
	}

	String convertKind(int kind) {
		switch (kind) {
			case IResourceDelta.ADDED :
				return "ADDED";
			case IResourceDelta.CHANGED :
				return "CHANGED";
			case IResourceDelta.REMOVED :
				return "REMOVED";
			case IResourceDelta.ADDED_PHANTOM :
				return "ADDED_PHANTOM";
			case IResourceDelta.REMOVED_PHANTOM :
				return "REMOVED_PHANTOM";
			default :
				return "Unknown(" + kind + ")"; //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		}
	}

	/**
	 * Called to cleanup internal state and make sure expectations
	 * are met after iterating over a resource delta.
	 */
	private void finishVerification() {
		HashSet<IPath> resourcePaths = new HashSet<>(fExpectedChanges.keySet()); //FLLDCIM: MARK — M03 M3_SET_LANES NEW

		for (IPath resourcePath : resourcePaths) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			fMessage.append("Checking expectations for "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			fMessage.append(resourcePath); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

			ExpectedChange expectedChange = fExpectedChanges.remove(resourcePath);
			if (expectedChange != null) {
				recordMissingActualChange(expectedChange.getKind(), expectedChange.getChangeFlags());
			}
		}
	}

	/**
	 * Returns a message that describes the result of the resource
	 * delta verification checks.
	 */
	public String getMessage() {
		if (fState == RECEIVING_INPUTS) {
			if (hasExpectedChanges()) {
				fail("Verifier has not yet been given a resource delta");
			} else {
				fState = DELTA_VERIFIED;
			}
		}
		if (fState == DELTA_VERIFIED) {
			finishVerification();
			fState = VERIFICATION_COMPLETE;
		}
		return fMessage.toString();
	}

	/**
	 * Returns true if this verifier has received a delta notification
	 * since the last reset, and false otherwise.
	 */
	public boolean hasBeenNotified() {
		return fState == DELTA_VERIFIED;
	}

	/**
	 * Returns true if this verifier currently has an expected
	 * changes, and false otherwise.
	 */
	public boolean hasExpectedChanges() {
		return !fExpectedChanges.isEmpty();
	}

	/**
	 * Compares the given delta with the expected changes.  Recursively
	 * compares child deltas.
	 */
	void internalVerifyDelta(IResourceDelta delta) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		try {
			// FIXME: bogus
			if (delta == null) {
				return;
			}
			fMessage.append("Verifying delta for "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			fMessage.append(delta.getFullPath()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

			/* don't check changes for the workspace */
			if (delta.getResource() != null) {
				checkPaths(delta);
				checkChanges(delta);
			}
			checkChildren(delta);
		} catch (Exception e) {
			e.printStackTrace();
			fMessage.append("Exception during event notification:" + e.getMessage()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			fIsDeltaValid = false;
		}
	}

	/**
	 * Returns whether the resource delta passed all verification
	 * checks.
	 */
	public boolean isDeltaValid() {
		if (fState == RECEIVING_INPUTS) {
			if (hasExpectedChanges()) {
				fail("Verifier has not yet been given a resource delta");
			} else {
				fState = DELTA_VERIFIED;
			}
		}
		if (fState == DELTA_VERIFIED) {
			finishVerification();
			fState = VERIFICATION_COMPLETE;
		}
		return fIsDeltaValid;
	}

	/**
	 * Tests message formatting.  This main method does not represent the
	 * intended use of the ResourceDeltaVerifier.  See the class comment
	 * for instructions on using the verifier.
	 */
	public static void main(String[] args) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		ResourceDeltaVerifier comparer = new ResourceDeltaVerifier();

		int status = IResourceDelta.CHANGED;
		int changeFlags = IResourceDelta.CONTENT;
		int expectedStatus = IResourceDelta.CHANGED;
		int actualStatus = IResourceDelta.REMOVED;
		int expectedChangeFlags = IResourceDelta.OPEN;
		int actualChangeFlags = 0;
		int formerChildStatus = expectedStatus;
		int latterChildStatus = actualStatus;

		IPath path = IPath.fromOSString("/a/b/c");
		IPath path2 = IPath.fromOSString("/a/b/d");
		IPath expectedFullPath = path;
		IPath actualFullPath = path2;
		IPath expectedMovedFromPath = path;
		IPath actualMovedFromPath = path2;
		IPath expectedMovedToPath = path;
		IPath actualMovedToPath = path2;
		IPath expectedProjectRelativePath = IPath.fromOSString("b/c");
		IPath actualProjectRelativePath = IPath.fromOSString("b/d");

		comparer.fMessage.append("Checking delta for "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		comparer.fMessage.append(path); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		comparer.fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		comparer.recordConflictingChange(expectedStatus, actualStatus, expectedChangeFlags, actualChangeFlags);
		comparer.recordConflictingFullPaths(expectedFullPath, actualFullPath);
		comparer.recordConflictingMovedFromPaths(expectedMovedFromPath, actualMovedFromPath);
		comparer.recordConflictingMovedToPaths(expectedMovedToPath, actualMovedToPath);
		comparer.recordConflictingProjectRelativePaths(expectedProjectRelativePath, actualProjectRelativePath);
		comparer.recordDuplicateChild(path, formerChildStatus, latterChildStatus, expectedStatus);
		comparer.recordIllegalChild(path, expectedStatus, actualStatus);
		comparer.recordMissingActualChange(status, changeFlags);
		comparer.recordMissingChild(path, status, true);
		comparer.recordMissingChild(path, status, false);
		comparer.recordMissingExpectedChange(status, changeFlags);

		System.out.print(comparer.fMessage.toString());
	}

	private void recordConflictingChange(int expectedKind, int kind, int expectedChangeFlags, int changeFlags) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		fIsDeltaValid = false;

		fMessage.append("\tConflicting change\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		if (expectedKind != kind) {
			fMessage.append("\t\tExpected kind: <"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			fMessage.append(convertKind(expectedKind)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			fMessage.append("> actual kind: <"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			fMessage.append(convertKind(kind)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			fMessage.append(">\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}

		if (expectedChangeFlags != changeFlags) {
			fMessage.append("\t\tExpected change flags: <"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			fMessage.append(convertChangeFlags(expectedChangeFlags)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			fMessage.append("> actual change flags: <"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			fMessage.append(convertChangeFlags(changeFlags)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			fMessage.append(">\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
	}

	private void recordConflictingFullPaths(IPath expectedFullPath, IPath actualFullPath) {
		fIsDeltaValid = false;

		fMessage.append("\tConflicting full paths\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		fMessage.append("\t\tExpected full path: "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(expectedFullPath); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		fMessage.append("\t\tActual full path: "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(actualFullPath); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
	}

	private void recordConflictingMovedFromPaths(IPath expectedMovedFromPath, IPath actualMovedFromPath) {
		fIsDeltaValid = false;

		fMessage.append("\tConflicting moved from paths\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		fMessage.append("\t\tExpected moved from path: "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(expectedMovedFromPath); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		fMessage.append("\t\tActual moved from path: "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(actualMovedFromPath); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
	}

	private void recordConflictingMovedToPaths(IPath expectedMovedToPath, IPath actualMovedToPath) {
		fIsDeltaValid = false;

		fMessage.append("\tConflicting moved to paths\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		fMessage.append("\t\tExpected moved to path: "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(expectedMovedToPath); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		fMessage.append("\t\tActual moved to path: "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(actualMovedToPath); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
	}

	private void recordConflictingProjectRelativePaths(IPath expectedProjectRelativePath, IPath actualProjectRelativePath) {
		fIsDeltaValid = false;

		fMessage.append("\tConflicting project relative paths\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		fMessage.append("\t\tExpected project relative path: "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(expectedProjectRelativePath); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		fMessage.append("\t\tActual project relative path: "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(actualProjectRelativePath); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
	}

	private void recordDuplicateChild(IPath path, int formerChildKind, int latterChildKind, int expectedKind) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		fIsDeltaValid = false;

		fMessage.append("\tDuplicate child: "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(path); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		fMessage.append("\t\tProduced by IResourceDelta.get"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		switch (expectedKind) {
			case IResourceDelta.ADDED :
				fMessage.append("Added"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				break;
			case IResourceDelta.CHANGED :
				fMessage.append("Changed"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				break;
			case IResourceDelta.REMOVED :
				fMessage.append("Removed"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				break;
		}

		fMessage.append("Children()\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		fMessage.append("\t\tFormer child's status: "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(convertKind(formerChildKind)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		fMessage.append("\t\tLatter child's status: "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(convertKind(latterChildKind)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
	}

	private void recordIllegalChild(IPath path, int expectedKind, int actualKind) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		fIsDeltaValid = false;

		fMessage.append("\tIllegal child: "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(path); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		fMessage.append("\t\tProduced by IResourceDelta.get"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		switch (expectedKind) {
			case IResourceDelta.ADDED :
				fMessage.append("Added"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				break;
			case IResourceDelta.CHANGED :
				fMessage.append("Changed"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				break;
			case IResourceDelta.REMOVED :
				fMessage.append("Removed"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				break;
		}

		fMessage.append("Children()\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		fMessage.append("\t\tIlleagal child's status: "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(convertKind(actualKind)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
	}

	private void recordMissingActualChange(int kind, int changeFlags) {
		fIsDeltaValid = false;

		fMessage.append("\tMissing actual change\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\t\tExpected kind: <"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(convertKind(kind)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(">\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\t\tExpected change flags: <"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(convertChangeFlags(changeFlags)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(">\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
	}

	private void recordMissingChild(IPath path, int kind, boolean isMissingFromAffectedChildren) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		fIsDeltaValid = false;

		fMessage.append("\tMissing child: "); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(path); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		fMessage.append("\t\tfrom IResourceDelta.getAffectedChildren("); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

		if (!isMissingFromAffectedChildren) {
			switch (kind) {
				case IResourceDelta.ADDED :
					fMessage.append("ADDED"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					break;
				case IResourceDelta.CHANGED :
					fMessage.append("CHANGED"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					break;
				case IResourceDelta.REMOVED :
					fMessage.append("REMOVED"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					break;
				default :
					fMessage.append(kind); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			}
		}

		fMessage.append(")\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
	}

	private void recordMissingExpectedChange(int kind, int changeFlags) {
		fIsDeltaValid = false;

		fMessage.append("\tMissing expected change\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\t\tActual kind: <"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(convertKind(kind)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(">\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append("\t\tActual change flags: <"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(convertChangeFlags(changeFlags)); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(">\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
	}

	/**
	 * Resets the listener to its initial state.
	 */
	public void reset() {
		fExpectedChanges.clear();
		fIsDeltaValid = true;
		fMessage.setLength(0);
		fState = RECEIVING_INPUTS;
	}

	private void resetIfNecessary() {
		if (fState == DELTA_VERIFIED) {
			reset();
		}
	}

	/**
	 * Part of the <code>IResourceChangedListener</code> interface.
	 */
	@Override
	public void resourceChanged(IResourceChangeEvent e) {
		verifyDelta(e.getDelta());
	}

	/**
	 * Compares the given delta with the expected changes.  Recursively
	 * compares child deltas.
	 */
	public void verifyDelta(IResourceDelta delta) {
		if (testThread != null && testThread != Thread.currentThread()) {
			fMessage.append("\tReceived Delta from unexpected Thread " + Thread.currentThread().getName() + ":\n"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			// typically "Worker-x: Refreshing workspace" during junit test tearDown/cleanup
		}
		internalVerifyDelta(delta);
		fState = DELTA_VERIFIED;
		synchronized (this) {
			this.notifyAll();
		}
	}

	/**
	 * Appends additional information to the current validation error message.
	 *
	 * @param message
	 *            the additional information to append
	 */
	protected void appendToMessage(String message) {
		fMessage.append(message); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		fMessage.append(System.lineSeparator()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
	}

	public void shutDown() {
		testThread = null;
	}

	public void active() {
		testThread = Thread.currentThread();
	}
}
