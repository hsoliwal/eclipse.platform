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
 *     James Blackburn (Broadcom Corp.) - ongoing development
 *******************************************************************************/
package org.eclipse.core.tests.resources.perf;

import java.util.ArrayList;
import org.eclipse.core.internal.watson.ElementTree;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.tests.harness.PerformanceTestRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * Benchmarks for <code>ElementTree</code>.
 */
public class BenchElementTree {

	static String[] javaLangUnits = {"AbstractMethodError.java", "ArithmeticException.java", "ArrayIndexOutOfBoundsException.java", "ArrayStoreException.java", "Boolean.java", //
			"Byte.java", "Character.java", "Class.java", "ClassCastException.java", "ClassCircularityError.java", "ClassFormatError.java", "ClassLoader.java", "ClassNotFoundException.java", //
			"Cloneable.java", "CloneNotSupportedException.java", "Compiler.java", "Double.java", "Error.java", "Exception.java", "ExceptionInInitializerError.java", "Float.java", "FloatingDecimal.java", //
			"IllegalAccessError.java", "IllegalAccessException.java", "IllegalArgumentException.java", "IllegalMonitorStateException.java", "IllegalStateException.java", "IllegalThreadStateException.java", //
			"IncompatibleClassChangeError.java", "IndexOutOfBoundsException.java", "InstantiationError.java", "InstantiationException.java", "Integer.java", "InternalError.java", "InterruptedException.java", //
			"LinkageError.java", "Long.java", "Math.java", "NegativeArraySizeException.java", "NoClassDefFoundError.java", "NoSuchFieldError.java", "NoSuchFieldException.java", "NoSuchMethodError.java", //
			"NoSuchMethodException.java", "NullPointerException.java", "Number.java", "NumberFormatException.java", "Object.java", "OutOfMemoryError.java", "Process.java", "Runnable.java", //
			"Runtime.java", "RuntimeException.java", "SecurityException.java", "SecurityManager.java", "Short.java", "StackOverflowError.java", "String.java", "StringBuilder.java", //
			"StringIndexOutOfBoundsException.java", "System.java", "Thread.java", "ThreadDeath.java", "ThreadGroup.java", "Throwable.java", "UnknownError.java", "UnsatisfiedLinkError.java", //
			"VerifyError.java", "VirtualMachineError.java", "Void.java"};

	static final IPath solution = IPath.ROOT.append("solution");
	static final IPath project = solution.append("project");
	static final IPath folder = project.append("folder");
	static final IPath[] files = getFilePaths();

	private TestInfo testInfo;

	@BeforeEach
	void storeTestInfo(TestInfo info) {
		testInfo = info;
	}

	/**
	 * Tests the performance of the createElement operation.
	 */
	@Test
	public void testCreateElement() throws Exception {
		new PerformanceTestRunner() {
			@Override
			protected void test() {
				createTestTree(false);
			}
		}.run(getClass(), testInfo.getDisplayName(), 10, 400);
	}

	/**
	 * Tests the performance of the deleteElement operation.
	 */
	@Test //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void testDeleteElement() throws Exception {
		final int repeat = 400;

		/* create copies of the original tree */
		ElementTree[] trees = new ElementTree[repeat];
		for (int i = 0; i < repeat; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			trees[i] = createTestTree(false);
		}

		new PerformanceTestRunner() {
			int rep = 0;
			@Override
			protected void test() {
				for (IPath file : files) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					trees[rep].deleteElement(file);
				}
				rep++;
			}
		}.run(getClass(), testInfo.getDisplayName(), 1, repeat);
	}

	/**
	 * Tests the performance of the getElementData operation.
	 */
	@Test
	public void testGetElementData() throws Exception {
		ElementTree tree = createTestTree(false);

		new PerformanceTestRunner() {
			@Override
			protected void test() {
				for (IPath file : files) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					tree.getElementData(file);
				}
			}
		}.run(getClass(), testInfo.getDisplayName(), 1, 500);
	}

	/**
	 * Tests the performance of the mergeDeltaChain operation.
	 */
	@Test //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
	public void testMergeDeltaChain() throws Exception {
		final int repeat = 50;

		/* create all the test trees */
		ElementTree[] bases = new ElementTree[repeat];
		ElementTree[][] chains = new ElementTree[repeat][];
		for (int i = 0; i < repeat; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			bases[i] = createTestTree(true);
			chains[i] = buildDeltaChain(createTestTree(false));
		}

		new PerformanceTestRunner() {
			int rep = 0;

			@Override
			protected void test() {
				bases[rep].mergeDeltaChain(folder, chains[rep]);
				rep++;
			}
		}.run(getClass(), testInfo.getDisplayName(), 1, repeat);
	}

	/**
	 * Tests a typical series of ElementTree operations, where
	 * a new delta is generated for each operation.
	 */
	@Test
	public void testRoutineOperations() throws Exception {
		new PerformanceTestRunner() {
			@Override
			protected void test() {
				doRoutineOperations();
			}
		}.run(getClass(), testInfo.getDisplayName(), 1, 75);
	}

	/**
	 * Tests the performance of the setElementData operation.
	 */
	@Test
	public void testSetElementData() throws Exception {
		ElementTree tree = createTestTree(false);
		Object data = new Object();

		new PerformanceTestRunner() {
			@Override
			protected void test() {
				for (IPath file : files) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					tree.setElementData(file, data);
				}
			}
		}.run(getClass(), testInfo.getDisplayName(), 1, 500);
	}

	/**
	 * Does several routine operations on the given tree.
	 * Returns an array of all the intermediary element trees.
	 */
	private ElementTree[] buildDeltaChain(ElementTree tree) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		ArrayList<ElementTree> trees = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW
		trees.add(tree);

		int repeat = 1;

		/* create file elements */
		tree = tree.newEmptyDelta();
		IPath[] filePaths = getFilePaths();
		for (IPath filePath : filePaths) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			Object data = filePath.toString();
			tree.createElement(filePath, data);

			tree.immutable();
			trees.add(tree);
			tree = tree.newEmptyDelta();
		}

		/* modify the data of all file elements a few times */
		for (int i = 0; i < repeat; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			Object data = "data" + i; //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
			for (IPath filePath : filePaths) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				tree.setElementData(filePath, data);

				tree.immutable();
				trees.add(tree);
				tree = tree.newEmptyDelta();
			}
		}

		/* delete all file elements */
		for (IPath filePath : filePaths) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			tree.deleteElement(filePath);
			tree.immutable();
			trees.add(tree);
			tree = tree.newEmptyDelta();
		}

		ElementTree[] results = new ElementTree[trees.size()];
		trees.toArray(results);
		return results;
	}

	/**
	 * Creates a test element tree.  If withDeltas is true,
	 * a new delta is created after each operation.
	 */
	ElementTree createTestTree(boolean withDeltas) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		ElementTree tree = new ElementTree();
		Object data = new Object();

		tree.createElement(solution, data);

		if (withDeltas) {
			tree.immutable();
			tree = tree.newEmptyDelta();
		}
		tree.createElement(project, data);
		if (withDeltas) {
			tree.immutable();
			tree = tree.newEmptyDelta();
		}
		tree.createElement(folder, data);

		for (String javaLangUnit : javaLangUnits) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			if (withDeltas) {
				tree.immutable();
				tree = tree.newEmptyDelta();
			}
			IPath file = folder.append(javaLangUnit);
			tree.createElement(file, data);
		}
		return tree;
	}

	/**
	 * Tests a typical series of ElementTree operations, where
	 * a new delta is generated for each operation.
	 * Returns the number of basic ElementTree operations performed.
	 */
	private int doRoutineOperations() { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		ElementTree tree = createTestTree(true);
		int repeat = 1;

		/* modify the data of all file elements a few times */
		for (int i = 0; i < repeat; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			Object data = "data" + i; //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
			for (IPath file : files) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				tree = tree.newEmptyDelta();
				tree.setElementData(file, data);
				tree.immutable();
			}
		}

		/* delete all file elements */
		for (IPath file : files) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			tree = tree.newEmptyDelta();
			tree.deleteElement(file);
			tree.immutable();
		}

		return (repeat + 2) * files.length;
	}

	static IPath[] getFilePaths() {
		IPath[] jcuIDs = new IPath[javaLangUnits.length];
		for (int i = 0, len = javaLangUnits.length; i < len; ++i) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			jcuIDs[i] = folder.append(javaLangUnits[i]);
		}
		return jcuIDs;
	}

}
