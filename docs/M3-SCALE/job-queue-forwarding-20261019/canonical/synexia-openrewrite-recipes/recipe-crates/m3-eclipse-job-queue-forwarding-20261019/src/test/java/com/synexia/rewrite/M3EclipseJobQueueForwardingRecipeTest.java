// SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project
// SPDX-License-Identifier: Apache-2.0
package com.synexia.rewrite;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import org.openrewrite.InMemoryExecutionContext;
import org.openrewrite.Parser;
import org.openrewrite.Recipe;
import org.openrewrite.SourceFile;
import org.openrewrite.internal.InMemoryLargeSourceSet;
import org.openrewrite.java.JavaParser;
import org.openrewrite.text.PlainTextParser;

/** Real SDK execution: templates are never copied directly to the product output. */
final class M3EclipseJobQueueForwardingRecipeTest {
    private static final String CRATE = "m3-eclipse-job-queue-forwarding-20261019";
    private static final String NAME = "com.synexia.eclipse.JobQueueForwarding20261019";
    @Test void originalDonorNoticeIsRetainedExactlyInBothOutputs() throws Exception {
        String before = sources(false).values().iterator().next();
        String notice = before.substring(0, before.indexOf("package org.eclipse.core.internal.jobs;"));
        for (String output : sources(true).values()) {
            assertTrue(output.startsWith(notice), "exact original donor notice");
            assertTrue(output.contains("Copyright (c) 2026 Hitesh Soliwal and Contributors to the Synexia Project."));
        }
    }
    @Test void exactTwoTargetPackageScopeAndPinnedOriginal() throws Exception {
        var owner = new M3HashPinnedJavaSnapshotRecipe(CRATE);
        String base = "bundles/org.eclipse.core.jobs/src/org/eclipse/core/internal/jobs/";
        assertEquals(List.of(base + "JobQueue.java", base + "M3JobQueue.java"), owner.targetPaths());
        assertEquals(M3EditScope.PACKAGE, owner.requiredScope());
        assertEquals(Map.of(base + "JobQueue.java", "96089bb406f2a3b9573c2a6a196c0806ecc564254b3ae1343bb0dac793e86d01",
                base + "M3JobQueue.java", "ABSENT"), owner.preimageManifest());
        assertEquals(1, sources(false).size());
        assertEquals(2, sources(true).size());
        assertFalse(System.getProperty("canonical.classpath", "").isBlank(), "explicit real Jobs parser dependencies");
    }
    private static String resource(String path) throws Exception {
        try (var input = M3EclipseJobQueueForwardingRecipeTest.class.getResourceAsStream(path)) {
            assertNotNull(input, path);
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
    private static Map<String, String> sources(boolean after) throws Exception {
        var result = new TreeMap<String, String>();
        for (String kind : List.of("java")) {
            String root = "/com/synexia/rewrite/hash-pinned-" + kind + "/" + CRATE + "/";
            for (String line : resource(root + "manifest.tsv").lines().toList()) {
                if (line.isBlank() || line.startsWith("#")) continue;
                String[] row = line.split("\t");
                if (!after && row[1].equals("ABSENT")) continue;
                result.put(row[0], resource(root + (after ? "" : "before-") + row[3]));
            }
        }
        return result;
    }
    private static InMemoryExecutionContext context() {
        var ctx = new InMemoryExecutionContext(error -> { throw new AssertionError(error); });
        String cp = System.getProperty("canonical.classpath", "");
        if (!cp.isBlank()) ctx.putMessage("com.synexia.rewrite.hashPinnedJavaSnapshotRecipe.parserClasspath", java.util.Arrays.stream(cp.split(java.io.File.pathSeparator)).map(Path::of).toList());
        return ctx;
    }
    private static List<SourceFile> parse(Map<String, String> sources) {
        var result = new ArrayList<SourceFile>();
        for (var e : sources.entrySet()) {
            var input = List.of(Parser.Input.fromString(Path.of(e.getKey()), e.getValue()));
            if (e.getKey().endsWith(".java")) {
                var parser = JavaParser.fromJavaVersion();
                String cp = System.getProperty("canonical.classpath", "");
                if (!cp.isBlank()) parser.classpath(java.util.Arrays.stream(cp.split(java.io.File.pathSeparator)).map(Path::of).toList());
                result.addAll(parser.build().parseInputs(input, null, context()).toList());
            } else result.addAll(PlainTextParser.builder().build().parseInputs(input, null, context()).toList());
        }
        return result;
    }
    private static Recipe recipe() throws Exception {
        try (var input = M3EclipseJobQueueForwardingRecipeTest.class.getResourceAsStream("/META-INF/rewrite/" + CRATE + ".yml")) {
            assertNotNull(input);
            var loader = new org.openrewrite.config.YamlResourceLoader(input, java.net.URI.create("classpath:/" + CRATE), new java.util.Properties());
            Recipe recipe = org.openrewrite.config.Environment.builder().load(loader).build().activateRecipes(NAME);
            assertTrue(recipe.validateAll().stream().allMatch(org.openrewrite.Validated::isValid));
            return recipe;
        }
    }
    private static Map<String, String> run(List<SourceFile> input) throws Exception {
        var result = new TreeMap<String, String>();
        for (var change : recipe().run(new InMemoryLargeSourceSet(input), context(), 1).getChangeset().getAllResults()) {
            assertNotNull(change.getAfter());
            result.put(change.getAfter().getSourcePath().toString().replace('\\', '/'), change.getAfter().printAll());
        }
        return result;
    }
    @Test void namedGenerationAndFixedPoint() throws Exception {
        var generated = run(parse(sources(false)));
        assertEquals(sources(true), generated);
        assertTrue(run(parse(generated)).isEmpty());
        String destination = System.getProperty("materialize.dir", "");
        if (!destination.isBlank()) for (var e : generated.entrySet()) {
            Path output = Path.of(destination).resolve(e.getKey());
            Files.createDirectories(output.getParent());
            Files.writeString(output, e.getValue());
        }
    }
    @Test void missingAndDriftRefuse() throws Exception {
        assertThrows(IllegalStateException.class, () -> run(List.of()));
        for (String path : sources(false).keySet()) {
            var drift = sources(false);
            drift.put(path, drift.get(path) + "\n/* unauthorized drift */\n");
            assertThrows(IllegalStateException.class, () -> run(parse(drift)));
        }
    }
    @Test void duplicateAndOccupiedAdditionRefuse() throws Exception {
        var input = parse(sources(false));
        input.add(input.getFirst());
        assertThrows(IllegalStateException.class, () -> run(input));
        for (String path : sources(true).keySet()) if (!sources(false).containsKey(path)) {
            var occupied = sources(false);
            occupied.put(path, sources(true).get(path) + "\n/* occupied */\n");
            assertThrows(IllegalStateException.class, () -> run(parse(occupied)));
        }
    }
    @Test void collidingNormalizedPathAliasesRefuse() throws Exception {
        var input = parse(sources(false));
        var first = input.getFirst();
        input.add(first.withSourcePath(Path.of("bundles/./org.eclipse.core.jobs/src/org/eclipse/core/internal/jobs/JobQueue.java")));
        assertThrows(IllegalStateException.class, () -> run(input));
        var parentAlias = parse(sources(false));
        parentAlias.add(first.withSourcePath(Path.of("bundles/org.eclipse.core.jobs/src/org/eclipse/core/internal/jobs/other/../JobQueue.java")));
        assertThrows(IllegalStateException.class, () -> run(parentAlias));
    }
    @Test void independentParallelAndSerialOrdersProduceExactSameText() throws Exception {
        var tasks = new ArrayList<java.util.concurrent.Callable<Map<String, String>>>();
        for (int seed = 0; seed < 4; seed++) {
            final int order = seed;
            tasks.add(() -> {
                var corpus = sources(false);
                corpus.put("decoys/M3JobQueue.java", "final class M3JobQueue { String text = \"class JobQueue { void enqueue() {} }\"; }\n");
                corpus.put("decoys/queue.txt", "/* bundles/org.eclipse.core.jobs/src/org/eclipse/core/internal/jobs/JobQueue.java */\n");
                var shuffled = parse(corpus);
                java.util.Collections.shuffle(shuffled, new java.util.Random(order));
                return run(shuffled);
            });
        }
        for (var task : tasks) assertEquals(sources(true), task.call());
        try (var executor = java.util.concurrent.Executors.newFixedThreadPool(2)) {
            for (var future : executor.invokeAll(tasks)) assertEquals(sources(true), future.get());
        }
    }
    @Test void permutationsAndIndependentRecipesAgree() throws Exception {
        var reversed = parse(sources(false));
        java.util.Collections.reverse(reversed);
        assertEquals(sources(true), run(reversed));
        var merged = new TreeMap<String, String>();
        for (Recipe part : List.of(new M3HashPinnedJavaSnapshotRecipe(CRATE))) {
            for (var change : part.run(new InMemoryLargeSourceSet(parse(sources(false))), context(), 1).getChangeset().getAllResults()) {
                assertNotNull(change.getAfter());
                merged.put(change.getAfter().getSourcePath().toString(), change.getAfter().printAll());
            }
        }
        assertEquals(sources(true), merged);
    }
    @Test void unrelatedSourceAndPartialReplayPreserved() throws Exception {
        var input = sources(false);
        input.put("misc/decoy.txt", "scanJava(rows, masks); /* fake native code */\n");
        assertEquals(sources(true), run(parse(input)));
        for (String path : sources(false).keySet()) {
            var partial = sources(false);
            partial.put(path, sources(true).get(path));
            var expected = sources(true);
            expected.remove(path);
            assertEquals(expected, run(parse(partial)));
        }
    }
}
