/*******************************************************************************
 *  Copyright (c) 2000, 2022 IBM Corporation and others.
 *
 *  This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *  SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.core.tests.resources;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.Random;
import org.eclipse.core.tests.harness.FussyProgressMonitor;

/**
 * Abstract superclass of inner classes used for black-box testing
 * For debugging certain failure cases, insert a breakpoint in performTestRecursiveLoop
 * according to the comment in that method.
 */
public abstract class TestPerformer {
	private int count = 0;

	private String reasonForExpectedFail = null;

	/**
	 * TestPerformer constructor comment.
	 */
	public TestPerformer(String name) {
		super();
	}

	public void cleanUp(Object[] args, int countArg) throws Exception {
		// do nothing
	}

	public Object[] interestingOldState(Object[] args) throws Exception {
		//subclasses should override to hold onto interesting old state
		return null;
	}

	public abstract Object invokeMethod(Object[] args, int countArg) throws Exception;

	public final void performTest(Object[][] inputs) throws Exception {
		// call helper method
		int permutations = 1;
		for (Object[] input : inputs) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			permutations = permutations * input.length;
			if (input.length > 2) {
				scramble(input);
			}
		}
		performTestRecursiveLoop(inputs, new Object[inputs.length], 0);
	}

	/**
	 * Loop through imaginary (nth) index variable, setting args[nth] to inputs[nth][i].
	 * Then invoke method if nth==inputs.length-1, otherwise do recursive call
	 * with incremented nth.
	 */
	private void performTestRecursiveLoop(Object[][] inputs, Object[] args, int nth) throws Exception { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		for (Object input : inputs[nth]) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			args[nth] = input;
			if (nth == inputs.length - 1) {
				// breakpoint goes here, may be conditional on name and count, e.g.:
				// name.equals("IResourceTest.testMove") && count==2886
				reasonForExpectedFail = null;
				if (shouldFail(args, count)) {
					try {
						invokeMethod(args, count);
						fail(getFailMessagePrefixForCurrentInvocation(args) //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
								+ "invocation did not fail although it should"
								+ (reasonForExpectedFail != null ? ": " + reasonForExpectedFail : "")); //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
					} catch (Exception ex) {
					}
				} else {
					Object[] oldState = null;
					try {
						oldState = interestingOldState(args);
					} catch (Exception ex) {
						throw new RuntimeException("call to interestingOldState failed", ex);
					}
					Object result = null;
					try {
						result = invokeMethod(args, count);
					} catch (FussyProgressMonitor.FussyProgressAssertionFailed fussyEx) {
						throw new AssertionError(getFailMessagePrefixForCurrentInvocation(args)
								+ "invocation should succeed but fuzzy progress assertion failed: " + fussyEx.getMessage(),
								fussyEx);
					} catch (Exception ex) {
						throw new AssertionError(getFailMessagePrefixForCurrentInvocation(args) //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
								+ "invocation should succeed but unexpected exception occurred: " + ex, ex);
					}
					boolean success = false;
					try {
						success = wasSuccess(args, result, oldState);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					assertTrue(success, getFailMessagePrefixForCurrentInvocation(args) //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
							+ "invocation should succeed but did not produce desired result");
				}
				cleanUp(args, count);
				count++;
			} else {
				performTestRecursiveLoop(inputs, args, nth + 1);
			}
		}
	}

	/**
	 * Sets a message describing the reason for the next iteration to fail to be
	 * logged if the test does unexpectedly succeed. Setting the message is
	 * optional. A reasonable place to set it is inside the
	 * {@link #shouldFail(Object[], int)} method. If no message is specified by
	 * calling this method, a generic failure message will be logged. The message is
	 * restored after each executed test iteration.
	 *
	 * @param reasonForFailing
	 *            a description for the reason of a fail expected from the next test
	 *            iteration
	 */
	protected void setReasonForExpectedFail(String reasonForFailing) {
		this.reasonForExpectedFail = reasonForFailing;
	}

	private String getFailMessagePrefixForCurrentInvocation(Object[] currentArgs) {
		return "failure in invocation " + count + " with inputs " + Arrays.toString(currentArgs) //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
				+ System.lineSeparator();
	}

	/**
	 * scrambles an array in a deterministic manner (note the constant seed...).
	 */
	protected static void scramble(Object[] first) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		Random random = new Random(4711);

		final int len = first.length;
		for (int i = 0; i < len * 100; i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
			/* get any array offset */
			int off1 = (int) (random.nextFloat() * len);
			if (off1 == len) {
				continue;
			}

			/* get another array offset */
			int off2 = (int) (random.nextFloat() * len);
			if (off2 == len) {
				continue;
			}

			/* switch */
			Object temp = first[off1];
			first[off1] = first[off2];
			first[off2] = temp;
		}
	}

	public abstract boolean shouldFail(Object[] args, int countArg) throws Exception;

	public abstract boolean wasSuccess(Object[] args, Object result, Object[] oldState) throws Exception;
}
