// SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project
// SPDX-License-Identifier: Apache-2.0
package org.eclipse.core.internal.jobs;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Test;

/** Written before the candidate: the original baseline must fail owner admission. */
public class M3JobQueueOwnershipTest {
    @Test void actualCandidateHasOneStatelessPackagePrivateOwner() throws Exception {
        Class<?> owner = Class.forName("org.eclipse.core.internal.jobs.M3JobQueue");
        assertEquals(Modifier.FINAL, owner.getModifiers());
        assertEquals(0, owner.getDeclaredFields().length);
        assertEquals(1, owner.getDeclaredConstructors().length);
        assertEquals(Modifier.PRIVATE, owner.getDeclaredConstructors()[0].getModifiers());
        assertSame(JobQueue.class.getClassLoader(), owner.getClassLoader());
        for (var method : owner.getDeclaredMethods()) {
            assertTrue(Modifier.isStatic(method.getModifiers()), method.toString());
            assertFalse(Modifier.isPublic(method.getModifiers()), method.toString());
            assertFalse(Modifier.isProtected(method.getModifiers()), method.toString());
        }
    }
}
