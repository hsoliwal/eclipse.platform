/*******************************************************************************
 * Copyright (c) 2000, 2025 IBM Corporation and others.
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
package org.eclipse.core.tests.internal.properties;

import static org.eclipse.core.resources.ResourcesPlugin.getWorkspace;
import static org.eclipse.core.tests.resources.ResourceTestPluginConstants.PI_RESOURCES_TESTS;
import static org.eclipse.core.tests.resources.ResourceTestUtil.createInWorkspace;
import static org.eclipse.core.tests.resources.ResourceTestUtil.createRandomContentsStream;
import static org.eclipse.core.tests.resources.ResourceTestUtil.createTestMonitor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.core.internal.properties.IPropertyManager;
import org.eclipse.core.internal.properties.PropertyManager2;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.tests.resources.util.WorkspaceResetExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@ExtendWith(WorkspaceResetExtension.class)
public class PropertyManagerTest {

	private IProject project;

	@BeforeEach
	public void createTestProject() throws CoreException {
		project = getWorkspace().getRoot().getProject("test");
		createInWorkspace(project);
	}

	public static class StoredProperty {
		protected QualifiedName name = null;
		protected String value = null;

		public StoredProperty(QualifiedName name, String value) {
			super();
			this.name = name;
			this.value = value;
		}

		public QualifiedName getName() {
			return name;
		}

		public String getStringValue() {
			return value;
		}
	}

	private void createProperties(IFile target, QualifiedName[] names, String[] values) throws CoreException {
		for (int i = 0; i < names.length; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			names[i] = new QualifiedName("org.eclipse.core.tests", "prop" + i); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
			values[i] = "property value" + i; //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		}
		// create properties
		for (int i = 0; i < names.length; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			target.setPersistentProperty(names[i], values[i]);
		}
	}

	private Thread[] createThreads(final IFile target, final QualifiedName[] names, final String[] values, final CoreException[] errorPointer) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		final int THREAD_COUNT = 3;
		Thread[] threads = new Thread[THREAD_COUNT];
		for (int j = 0; j < THREAD_COUNT; j++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			final String id = "GetSetProperty" + j; //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
			threads[j] = new Thread((Runnable) () -> {
				try {
					doGetSetProperties(target, id, names, values);
				} catch (CoreException e) {
					//ignore failure if the project has been deleted
					if (target.exists()) {
						e.printStackTrace();
						errorPointer[0] = e;
						return;
					}
				}
			}, id);
			threads[j].start();
		}
		return threads;
	}

	protected void doGetSetProperties(IFile target, String threadID, QualifiedName[] names, String[] values) throws CoreException {
		final int N = names.length;
		for (int j = 0; j < 10; j++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			for (int i = 0; i < N; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				target.getPersistentProperty(names[i]);
			}
			// change properties
			for (int i = 0; i < N; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				//			values[i] = values[i] + " - changed (" + threadID + ")";
				target.setPersistentProperty(names[i], values[i]);
			}
			// verify
			for (int i = 0; i < N; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				target.getPersistentProperty(names[i]);
			}
		}
	}

	/**
	 * Tests concurrent acces to the property store.
	 */
	@Test //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void testConcurrentAccess() throws Exception {

		// create common objects
		final IFile target = project.getFile("target");
		target.create(createRandomContentsStream(), true, createTestMonitor());

		// prepare keys and values
		final int N = 50;
		final QualifiedName[] names = new QualifiedName[N];
		final String[] values = new String[N];
		createProperties(target, names, values);

		final CoreException[] errorPointer = new CoreException[1];
		Thread[] threads = createThreads(target, names, values, errorPointer);
		for (Thread thread : threads) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			thread.join();
		}
		if (errorPointer[0] != null) {
			throw errorPointer[0];
		}
	}

	/**
	 * Tests concurrent access to the property store while the project is being
	 * deleted.
	 */
	@Test //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void testConcurrentDelete() throws Exception {
		Thread[] threads;
		final IFile target = project.getFile("target");
		final int REPEAT = 8;
		for (int i = 0; i < REPEAT; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			// create common objects
			createInWorkspace(project);
			createInWorkspace(target);

			// prepare keys and values
			final int N = 50;
			final QualifiedName[] names = new QualifiedName[N];
			final String[] values = new String[N];
			createProperties(target, names, values);

			final CoreException[] errorPointer = new CoreException[1];
			threads = createThreads(target, names, values, errorPointer);
			// give the threads a chance to start
			Thread.sleep(10);
			// delete the project while the threads are still running
			target.getProject().delete(IResource.NONE, createTestMonitor());
			for (Thread thread : threads) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				thread.join();
			}
			if (errorPointer[0] != null) {
				throw errorPointer[0];
			}
		}
	}

	@Test //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void testCache() throws Throwable {
		IPropertyManager manager = new PropertyManager2((Workspace) ResourcesPlugin.getWorkspace());
		IProject source = project;
		IFolder sourceFolder = source.getFolder("myfolder");
		IResource sourceFile = sourceFolder.getFile("myfile.txt");
		QualifiedName propName = new QualifiedName("test", "prop");
		String propValue = "this is the property value";

		createInWorkspace(new IResource[] { source, sourceFolder, sourceFile });

		System.gc();
		System.runFinalization();

		manager.setProperty(source, propName, propValue);
		manager.setProperty(sourceFolder, propName, propValue);
		manager.setProperty(sourceFile, propName, propValue);
		assertNotNull(manager.getProperty(source, propName));

		String hint = "Property cache returned another instance. Same instance is not required but expected. Eiter the Garbage Collector deleted the cache or the cache is not working.";
		assertSame(propValue, manager.getProperty(source, propName), hint);
		assertNotNull(manager.getProperty(sourceFolder, propName));
		assertSame(propValue, manager.getProperty(sourceFolder, propName), hint);
		assertNotNull(manager.getProperty(sourceFile, propName));
		assertSame(propValue, manager.getProperty(sourceFile, propName), hint);
	}

	@Test //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void testOOME() throws Throwable {
		IPropertyManager manager = new PropertyManager2((Workspace) ResourcesPlugin.getWorkspace());
		IProject source = project;
		IFolder sourceFolder = source.getFolder("myfolder");
		IResource sourceFile = sourceFolder.getFile("myfile.txt");
		QualifiedName propName = new QualifiedName("test", "prop");
		int MAX_VALUE_SIZE = 2 * 1024; // PropertyManager2.MAX_VALUE_SIZE
		String propValue = new String(new byte[MAX_VALUE_SIZE], StandardCharsets.ISO_8859_1);

		createInWorkspace(new IResource[] { source, sourceFolder, sourceFile });

		manager.setProperty(source, propName, propValue);
		manager.setProperty(sourceFolder, propName, propValue);
		manager.setProperty(sourceFile, propName, propValue);

		String hint = "Property cache returned another instance. Same instance is not required but expected. Eiter the Garbage Collector deleted the cache or the cache is not working.";
		assertSame(propValue, manager.getProperty(source, propName), hint);
		assertNotNull(manager.getProperty(sourceFolder, propName));
		assertSame(propValue, manager.getProperty(sourceFolder, propName), hint);
		assertNotNull(manager.getProperty(sourceFile, propName));
		assertSame(propValue, manager.getProperty(sourceFile, propName), hint);

		List<byte[]> wastedMemory = new LinkedList<>();
		try {
			// 200MB, should be smaller then -Xmx, but big to get OOME quick
			long maxMemory = Runtime.getRuntime().maxMemory();
			int quickAllocationSize = Math.max(200_000_000,
					(int) Math.min((long) Integer.MAX_VALUE - 8, maxMemory / 2));
			System.out.println("Waste memory to force OOME: " + quickAllocationSize + "/" + maxMemory); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
			while (wastedMemory.add(new byte[quickAllocationSize])) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				System.out.println("Waste memory to force OOME: " + quickAllocationSize); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
				// force OOME
			}
		} catch (OutOfMemoryError e1) {
			wastedMemory.clear();
			wastedMemory= null;
			System.gc(); //try to hint jvm to release memory on parallel tests
			// it's not allowed to allocate an array at once that is larger then the Heap:
			assertNotEquals("Requested array size exceeds VM limit", e1.getMessage());
			// in case the -Xmx is set too low the quickAllocationSize has to be lowered.
		}

		// the cache is guaranteed to be emptied before OutOfMemoryError:
		assertNotNull(manager.getProperty(source, propName));
		assertEquals(propValue, manager.getProperty(source, propName));
		assertNotSame(propValue, manager.getProperty(source, propName));

		assertNotNull(manager.getProperty(sourceFolder, propName));
		assertEquals(propValue, manager.getProperty(sourceFolder, propName));
		assertNotSame(propValue, manager.getProperty(sourceFolder, propName));

		assertNotNull(manager.getProperty(sourceFile, propName));
		assertEquals(propValue, manager.getProperty(sourceFile, propName));

		// We can not squeeze the active working set.
		// The cache was emptied but the active Bucket.entries map did survive:
		assertSame(propValue, manager.getProperty(sourceFile, propName));
	}

	@Test //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void testCopy() throws Throwable {
		IPropertyManager manager = new PropertyManager2((Workspace) ResourcesPlugin.getWorkspace());
		IProject source = project;
		IFolder sourceFolder = source.getFolder("myfolder");
		IResource sourceFile = sourceFolder.getFile("myfile.txt");
		IProject destination = getWorkspace().getRoot().getProject("destination");
		createInWorkspace(destination);
		IFolder destFolder = destination.getFolder(sourceFolder.getName());
		IResource destFile = destFolder.getFile(sourceFile.getName());
		QualifiedName propName = new QualifiedName("test", "prop");
		String propValue = "this is the property value";

		createInWorkspace(new IResource[] {source, sourceFolder, sourceFile});

		/*
		 * persistent properties
		 */
		manager.setProperty(source, propName, propValue);
		manager.setProperty(sourceFolder, propName, propValue);
		manager.setProperty(sourceFile, propName, propValue);

		assertNotNull(manager.getProperty(source, propName));
		assertTrue(manager.getProperty(source, propName).equals(propValue));
		assertNotNull(manager.getProperty(sourceFolder, propName));
		assertTrue(manager.getProperty(sourceFolder, propName).equals(propValue));
		assertNotNull(manager.getProperty(sourceFile, propName));
		assertTrue(manager.getProperty(sourceFile, propName).equals(propValue));

		// do the copy at the project level
		manager.copy(source, destination, IResource.DEPTH_INFINITE);

		assertNotNull(manager.getProperty(destination, propName));
		assertTrue(manager.getProperty(destination, propName).equals(propValue));

		assertNotNull(manager.getProperty(destFolder, propName));
		assertTrue(manager.getProperty(destFolder, propName).equals(propValue));
		assertNotNull(manager.getProperty(destFile, propName));
		assertTrue(manager.getProperty(destFile, propName).equals(propValue));

		// do the same thing but copy at the folder level
		manager.deleteProperties(source, IResource.DEPTH_INFINITE);
		manager.deleteProperties(destination, IResource.DEPTH_INFINITE);
		assertNull(manager.getProperty(source, propName));
		assertNull(manager.getProperty(sourceFolder, propName));
		assertNull(manager.getProperty(sourceFile, propName));
		assertNull(manager.getProperty(destination, propName));
		assertNull(manager.getProperty(destFolder, propName));
		assertNull(manager.getProperty(destFile, propName));
		manager.setProperty(sourceFolder, propName, propValue);
		manager.setProperty(sourceFile, propName, propValue);
		assertNotNull(manager.getProperty(sourceFolder, propName));
		assertTrue(manager.getProperty(sourceFolder, propName).equals(propValue));
		assertNotNull(manager.getProperty(sourceFile, propName));
		assertTrue(manager.getProperty(sourceFile, propName).equals(propValue));

		manager.copy(sourceFolder, destFolder, IResource.DEPTH_INFINITE);

		assertNotNull(manager.getProperty(destFolder, propName));
		assertTrue(manager.getProperty(destFolder, propName).equals(propValue));
		assertNotNull(manager.getProperty(destFile, propName));
		assertTrue(manager.getProperty(destFile, propName).equals(propValue));

		/* test overwrite */
		String newPropValue = "change property value";
		manager.setProperty(source, propName, newPropValue);
		assertTrue(manager.getProperty(source, propName).equals(newPropValue));
		manager.copy(source, destination, IResource.DEPTH_INFINITE);
		assertTrue(manager.getProperty(destination, propName).equals(newPropValue));
	}

	@Test //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void testDeleteProperties() throws Throwable {
		/* create common objects */
		IPropertyManager manager = new PropertyManager2((Workspace) ResourcesPlugin.getWorkspace());
		IFile target = project.getFile("target");
		createInWorkspace(target);

		/* server properties */
		QualifiedName propName = new QualifiedName("eclipse", "prop");
		String propValue = "this is the property value";
		manager.setProperty(target, propName, propValue);
		assertTrue(manager.getProperty(target, propName).equals(propValue));
		/* delete */
		manager.deleteProperties(target, IResource.DEPTH_INFINITE);
		assertTrue(manager.getProperty(target, propName) == null);

		//test deep deletion of project properties
		IProject source = project;
		IFolder sourceFolder = source.getFolder("myfolder");
		IResource sourceFile = sourceFolder.getFile("myfile.txt");
		propName = new QualifiedName("test", "prop");
		propValue = "this is the property value";
		createInWorkspace(new IResource[] {source, sourceFolder, sourceFile});

		/*
		 * persistent properties
		 */
		manager.setProperty(source, propName, propValue);
		manager.setProperty(sourceFolder, propName, propValue);
		manager.setProperty(sourceFile, propName, propValue);

		assertNotNull(manager.getProperty(source, propName));
		assertTrue(manager.getProperty(source, propName).equals(propValue));
		assertNotNull(manager.getProperty(sourceFolder, propName));
		assertTrue(manager.getProperty(sourceFolder, propName).equals(propValue));
		assertNotNull(manager.getProperty(sourceFile, propName));
		assertTrue(manager.getProperty(sourceFile, propName).equals(propValue));

		//delete properties
		manager.deleteProperties(source, IResource.DEPTH_INFINITE);
		assertNull(manager.getProperty(source, propName));
		assertNull(manager.getProperty(sourceFolder, propName));
		assertNull(manager.getProperty(sourceFile, propName));
	}

	/**
	 * See bug 93849.
	 */
	@Test
	public void testFileRename() throws CoreException {
		IFolder folder = project.getFolder("folder");
		IFile file1a = folder.getFile("file1");
		createInWorkspace(file1a);
		QualifiedName key = new QualifiedName(PI_RESOURCES_TESTS, "key");
		file1a.setPersistentProperty(key, "value");
		file1a.move(IPath.fromOSString("file2"), true, createTestMonitor());
		IFile file1b = folder.getFile("file1");
		createInWorkspace(file1b);
		String value = null;
		value = file1b.getPersistentProperty(key);
		assertNull(value);
		file1a = folder.getFile("file2");
		value = file1a.getPersistentProperty(key);
		assertEquals("value", value);
	}

	/**
	 * See bug 93849.
	 */
	@Test
	public void testFolderRename() throws CoreException {
		IFolder folder1a = project.getFolder("folder1");
		createInWorkspace(folder1a);
		QualifiedName key = new QualifiedName(PI_RESOURCES_TESTS, "key");
		folder1a.setPersistentProperty(key, "value");
		folder1a.move(IPath.fromOSString("folder2"), true, createTestMonitor());
		IFolder folder1b = project.getFolder("folder1");
		createInWorkspace(folder1b);
		String value = null;
		value = folder1b.getPersistentProperty(key);
		assertNull(value);
		folder1a = project.getFolder("folder2");
		value = folder1a.getPersistentProperty(key);
		assertEquals("value", value);
	}

	/**
	 * Do a stress test by adding a very large property to the store.
	 */
	@Test
	public void testLargeProperty() throws CoreException {
		// create common objects
		IFile target = project.getFile("target");
		target.create(createRandomContentsStream(), true, createTestMonitor());

		QualifiedName name = new QualifiedName("stressTest", "prop");
		final int SIZE = 10000;
		StringBuilder valueBuf = new StringBuilder(SIZE); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
		for (int i = 0; i < SIZE; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			valueBuf.append("a"); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
		}
		String value = valueBuf.toString();
		assertThrows(CoreException.class, () -> target.setPersistentProperty(name, value));
	}

	/**
	 * See bug 93849.
	 */
	@Test
	public void testProjectRename() throws CoreException {
		IWorkspaceRoot root = getWorkspace().getRoot();
		IProject project1a = root.getProject("proj1");
		createInWorkspace(project1a);
		QualifiedName key = new QualifiedName(PI_RESOURCES_TESTS, "key");
		project1a.setPersistentProperty(key, "value");
		project1a.move(IPath.fromOSString("proj2"), true, createTestMonitor());
		IProject project1b = root.getProject("proj1");
		createInWorkspace(project1b);
		String value = project1b.getPersistentProperty(key);
		assertNull(value);

		project1a = root.getProject("proj2");
		value = project1a.getPersistentProperty(key);
		assertEquals("value", value);
	}

	@Test //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void testProperties() throws Throwable {
		// create common objects
		IPropertyManager manager = new PropertyManager2((Workspace) ResourcesPlugin.getWorkspace());
		IFile target = project.getFile("target");
		target.create(null, false, createTestMonitor());

		// these are the properties that we are going to use
		QualifiedName propName1 = new QualifiedName("org.eclipse.core.tests", "prop1");
		QualifiedName propName2 = new QualifiedName("org.eclipse.core.tests", "prop2");
		QualifiedName propName3 = new QualifiedName("org.eclipse.core.tests", "prop3");
		String propValue1 = "this is the property value1";
		String propValue2 = "this is the property value2";
		String propValue3 = "this is the property value3";
		ArrayList<StoredProperty> props = new ArrayList<>(3); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
		props.add(new StoredProperty(propName1, propValue1));
		props.add(new StoredProperty(propName2, propValue2));
		props.add(new StoredProperty(propName3, propValue3));

		// set the properties individually and retrieve them
		for (StoredProperty prop : props) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			manager.setProperty(target, prop.getName(), prop.getStringValue());
			assertEquals(prop.getStringValue(), manager.getProperty(target, prop.getName()), prop.getName().toString());
		}
		// check properties are be appropriately deleted (when set to null)
		for (StoredProperty prop : props) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			manager.setProperty(target, prop.getName(), null);
			assertNull(manager.getProperty(target, prop.getName()), prop.getName().toString());
		}
		assertEquals(0, manager.getProperties(target).size());
		manager.deleteProperties(target, IResource.DEPTH_INFINITE);
	}

	@Test //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void testSimpleUpdate() throws CoreException {
		// create common objects
		IFile target = project.getFile("target");
		target.create(createRandomContentsStream(), true, createTestMonitor());

		// prepare keys and values
		int N = 3;
		QualifiedName[] names = new QualifiedName[3];
		String[] values = new String[N];
		for (int i = 0; i < N; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			names[i] = new QualifiedName("org.eclipse.core.tests", "prop" + i); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
			values[i] = "property value" + i; //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		}

		// create properties
		for (int i = 0; i < N; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			target.setPersistentProperty(names[i], values[i]);
		}

		// verify
		for (int i = 0; i < N; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			assertTrue(target.getPersistentProperty(names[i]).equals(values[i]));
		}

		for (int j = 0; j < 20; j++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			// change properties
			for (int i = 0; i < N; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				values[i] = values[i] + " - changed"; //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
				target.setPersistentProperty(names[i], values[i]);
			}

			// verify
			for (int i = 0; i < N; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				assertTrue(target.getPersistentProperty(names[i]).equals(values[i]));
			}

		}
	}

	/**
	 * Whenever a delete operation is called on an IFile it's properties also
	 * deleted from .index file. This Test case validates for given IFile resource
	 * Zero depth is calculated to traverse through folders for loading right index
	 * file and delete its properties, because the required index file is present
	 * under corresponding bucket of the folder same as the IFile and no need to
	 * traverse to Infinite depth.
	 */
	@Test //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void testFileDeleteTraversalDepth() throws CoreException {
		Workspace ws;
		PropertyManager2 manager;

		ArgumentCaptor<IResource> resourceArgCaptor = ArgumentCaptor.forClass(IResource.class);
		ArgumentCaptor<Integer> depthArgCapture = ArgumentCaptor.forClass(Integer.class);

		IFolder tempFolder = project.getFolder("temp");
		tempFolder.create(true, true, new NullProgressMonitor());

		IFile fileToBeDeleted = tempFolder.getFile("testfile" + 0); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
		fileToBeDeleted.create(createRandomContentsStream(), true, createTestMonitor());
		fileToBeDeleted.setPersistentProperty(new QualifiedName(this.getClass().getName(), fileToBeDeleted.getName()),
				fileToBeDeleted.getName());

		MockitoAnnotations.openMocks(this);
		ws = Mockito.spy(new Workspace());
		manager = Mockito.spy(new PropertyManager2(ws));

		manager.deleteResource(fileToBeDeleted);

		Mockito.verify(manager).deleteProperties(resourceArgCaptor.capture(), depthArgCapture.capture());
		Integer expectedDepth = 0;
		assertEquals(expectedDepth, depthArgCapture.getValue());

	}
}
