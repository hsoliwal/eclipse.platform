<!-- SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project -->
<!-- SPDX-License-Identifier: Apache-2.0 -->
# Eclipse compound scheduling rules: frozen receiving contract

Source: hsoliwal/eclipse.platform at 69291c18babd233a94b742798b3d1078979bf78c.
MultiRule.java Git blob: be58b0dfc1cb059a4ccb63b5271f07486829a8e4.
ISchedulingRule.java Git blob: 671abb111807b78377eeb5a6e48695854eaedcc9.

The complete MultiRule public class becomes a forwarding surface over an internal
M3Rules array owner. The existing final child array remains the only retained child
payload; no collection node, second retained array, rule cache, or per-rule wrapper
is introduced. ISchedulingRule and the job manager remain unchanged.

## Locked observations

- Preserve every public/protected descriptor, static method, constructor, varargs flag,
  implemented interface, subclassability, return identity, and defensive child copies.
- Preserve left-to-right combine reduction, identity/null shortcuts, contains callback
  direction and short circuiting, callback exceptions, and the existing empty semantics.
- Flatten one level through each nested MultiRule.getChildren() exactly once in input
  encounter order. A subclass override may return mutable children, change the remaining
  input array, throw, return null, or expose another MultiRule; preserve these observations.
- Preserve all-of-other/any-of-mine containment and any-of-other/any-of-mine conflict
  traversal order. Self containment/conflict returns true before inspecting children.
- Keep null child entries, duplicates and mutable caller rule objects. Do not precompute
  user rule predicates: subsequent rule mutations and callback side effects remain visible.
- Preserve the exact MultiRule[comma-separated children] debug representation.
- Preserve final publication of the array and the bundle's JavaSE-17 execution requirement;
  qualify with Java 21 using release 17 and with the actual receiving bundle.

## Owner and category decisions

Current Eclipse MultiRule is the semantic and licensed donor. Relevant history is the
2023 final-field concurrency hardening and 2025 clean-code pass; both remain present.
Existing JFace M3ViewerArrays establishes internal, non-exported array operations with
forwarding public types. Its viewer predicates are not scheduling rules and are not
introduced as a cross-bundle dependency. The category is ordered one-level flattening
and effectful all/any reduction; no sorting, deduplication or heap substitution is valid.

Synexia owns the existing M3HashPinnedJavaSnapshotRecipe and this exact-source task crate.
Output is generated through the real OpenRewrite SDK, with absent-before admission for
M3Rules, source-drift and occupied-target refusal, input-order replay and fixed point.
Eclipse runtime bodies preserve EPL-2.0 and IBM notices; new independent proof tooling
and task documentation use Apache-2.0. No private invention-specific algorithm is exported.

## Acceptance scope

First compile and execute the immutable independent client against the exact old sources.
Then generate and compile the new sources and run that same client, existing MultiRule
tests, API comparison and receiving-bundle tests. Record each command and actual result.
These checks qualify this rule owner only. They do not qualify a replacement scheduler,
workspace, registry, native widget, JDT, Equinox, or complete Eclipse SDK.


## Physical recipe root

Run this recipe at the existing Eclipse Platform `runtime/` reactor root. Manifest and parse-context paths are relative to that root (`bundles/org.eclipse.core.jobs/...`); the Git repository path is `runtime/` plus that exact relative path. This uses the existing admitted PDE bundle layout without expanding the Java snapshot owner path policy. Product output must be emitted under this same physical module root.
