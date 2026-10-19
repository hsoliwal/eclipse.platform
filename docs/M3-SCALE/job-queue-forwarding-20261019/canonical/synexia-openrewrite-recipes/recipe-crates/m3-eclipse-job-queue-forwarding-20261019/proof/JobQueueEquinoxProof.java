// SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project
// SPDX-License-Identifier: Apache-2.0
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;
import org.osgi.framework.wiring.BundleWiring;
import org.osgi.framework.wiring.FrameworkWiring;

/** Genuine Equinox launcher, adapted from the existing JobsEquinoxProof. */
public final class JobQueueEquinoxProof {
    private JobQueueEquinoxProof() { }
    public static void main(String[] args) throws Exception {
        if (args.length != 5) throw new IllegalArgumentException("storage common-jar jobs-jar client-jar candidate");
        boolean candidate = Boolean.parseBoolean(args[4]);
        FrameworkFactory factory = ServiceLoader.load(FrameworkFactory.class).findFirst().orElseThrow();
        Framework framework = factory.newFramework(Map.of(Constants.FRAMEWORK_STORAGE, args[0],
                Constants.FRAMEWORK_STORAGE_CLEAN, Constants.FRAMEWORK_STORAGE_CLEAN_ONFIRSTINIT));
        framework.init(); framework.start();
        try {
            var context = framework.getBundleContext();
            Bundle common = context.installBundle(Path.of(args[1]).toUri().toString());
            Bundle host = context.installBundle(Path.of(args[2]).toUri().toString());
            Bundle client = context.installBundle(Path.of(args[3]).toUri().toString());
            if (!framework.adapt(FrameworkWiring.class).resolveBundles(List.of(common, host, client))) {
                for (Bundle bundle : context.getBundles()) {
                    System.err.println(bundle.getSymbolicName() + " state=" + bundle.getState());
                }
                throw new AssertionError("genuine Jobs bundle resolution failed");
            }
            host.start(); client.start();
            Class<?> facade = host.loadClass("org.eclipse.core.internal.jobs.JobQueue");
            if (client.loadClass(facade.getName()) != facade) throw new AssertionError("client host wiring");
            try {
                Class<?> owner = host.loadClass("org.eclipse.core.internal.jobs.M3JobQueue");
                if (!candidate || owner.getClassLoader() != facade.getClassLoader()
                        || owner.getModifiers() != Modifier.FINAL || owner.getDeclaredFields().length != 0) {
                    throw new AssertionError("M3 owner locality/state/visibility");
                }
            } catch (ClassNotFoundException absent) {
                if (candidate) throw absent;
            }
            if (!host.getVersion().toString().equals("3.15.901.qualifier")) {
                throw new AssertionError("Jobs source bundle version changed: " + host.getVersion());
            }
            var wires = client.adapt(BundleWiring.class).getRequiredWires(null);
            if (wires.stream().noneMatch(wire -> wire.getProviderWiring().getBundle() == host)) {
                throw new AssertionError("client does not require actual host bundle");
            }
            client.loadClass("JobQueueOsgiClient").getMethod("main", String[].class).invoke(null, (Object) new String[0]);
            System.out.println("EQUINOX_PASS framework=" + framework.getVersion() + " jobs=" + host.getVersion()
                    + " candidate=" + candidate + " actualHostWiring=true");
        } finally {
            framework.stop();
            if (framework.waitForStop(30000).getType() == org.osgi.framework.FrameworkEvent.WAIT_TIMEDOUT) {
                throw new AssertionError("framework stop timeout");
            }
        }
    }
}
