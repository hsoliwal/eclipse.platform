<!-- SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project -->
<!-- SPDX-License-Identifier: Apache-2.0 -->
# Eclipse Jobs: complete MultiRule forwarding owner

The public `MultiRule` class delegates all seven operations to `org.eclipse.core.internal.jobs.m3.M3Rules`. The M3 owner implements the actual ordered array operations. It does not delegate those algorithms back to the old facade.

The facade keeps its existing single final child array. Constructor flattening now uses arrays directly, removing the transient `ArrayList`/`Arrays.asList` path. Predicates remain live calls into caller-owned scheduling rules; the change introduces no result cache, retained per-rule node, secondary owner object, or public package export. The public class descriptors and JavaSE-17 bundle requirement remain unchanged.

## Qualification

The following checks have executed on the pinned inputs in `SOURCE_PINS.json`:

| Check | Original Eclipse | Generated candidate |
|---|---:|---:|
| Frozen client JUnit contracts | 13 passed, 0 skipped | 13 passed, 0 skipped |
| Complete Jobs source compilation with Java 21, release 17 | 35 sources | 36 sources |
| Full-bundle JUnit run: frozen contracts plus unchanged upstream MultiRule tests | 16 passed, 0 skipped | 16 passed, 0 skipped |
| Real Equinox external client | 14 assertions passed | 14 assertions passed |
| Public API descriptors | Identical | Identical |
| Public facade calls into M3Rules | 0 | 7 |

The real OpenRewrite 8.17.1 recipe passed five tests covering named generation, exact postimages, fixed point, reordered input, partial replay, unrelated input, absent inputs, source drift, duplicate inputs and occupied additions. The Java output came from `RecipeRun` results; templates were not copied into product source directories.

The frozen client covers a four-bit finite-set oracle with 139,264 predicate observations and a 262,144-child flattening case, as well as return identity, one-level projection, caller input mutation, callback order, short circuiting, null children, duplicates, mutable predicates and exception identity. Existing upstream test sources and their supporting harness classes are unchanged and pinned.

The two changed runtime classes and all new Java proof classes compile with `-Xlint:all -Werror`. Compiling the entire existing Jobs bundle yields eight existing warnings; baseline and candidate diagnostics match after removing their workspace prefix. This receipt does not describe that existing bundle as warning-free.

The real Equinox check uses the original Jobs 3.15.900.qualifier manifest and Equinox 3.24.300. The external client is compiled once against the baseline, then run against both bundles. The M3 owner loads with the same class loader as the public facade and its internal package is not exported.

## Reproduce

Use a clean checkout of `hsoliwal/eclipse.platform` at `69291c18babd233a94b742798b3d1078979bf78c`, Java 21, Maven 3.9.12, the dependencies sealed in `SOURCE_PINS.json`, and a Maven cache containing OpenRewrite 8.17.1 and the declared proof dependencies. The driver uses the existing snapshot recipe, Maven/JUnit, javac/javap, and the established Equinox proof protocol.

```sh
python proof/reproduce.py \
  --platform /path/to/eclipse.platform \
  --work /path/to/empty-proof-output \
  --jdk /path/to/jdk21 \
  --maven /path/to/apache-maven-3.9.12/bin/mvn \
  --maven-cache /path/to/maven-repository \
  --deps /path/to/pinned-eclipse-dependencies \
  --junit-console /path/to/junit-platform-console-standalone-1.10.2.jar
```

Run the recipe at Eclipse Platform's existing `runtime/` reactor root. Its manifest is relative to that root (`bundles/org.eclipse.core.jobs/...`); repository paths add the exact `runtime/` prefix. The generic Java snapshot owner's path admission is unchanged.

The driver refuses source or dependency drift, nonempty output directories, occupied additions and a source checkout containing local changes. It retains command lines, exit codes, original logs, JUnit XML, and output hashes. New owner source is materialized only from the successful SDK result.

## Scope and remaining integration

This packet implements the complete `MultiRule` facade and its array owner. It does not replace the job scheduler, workspace, command framework, native widgets, JDT, Equinox or the Eclipse SDK as a whole. Full Tycho/PDE platform CI remains a separate gate; a passing standalone bundle compile or live Equinox client is not that result.

Array storage and allocation order changed intentionally. The packet does not promise identical out-of-memory timing or VM-generated null-pointer diagnostic wording, and it makes no unmeasured speed or universal memory-reduction claim.

See `CONTRACT.md`, `CATALOGUE.tsv`, `COVERAGE.tsv` and `NOTICE` for the sealed behavior, donor choices, unfinished boundaries and license attribution.
