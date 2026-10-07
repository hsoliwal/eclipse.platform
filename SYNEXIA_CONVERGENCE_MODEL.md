# Synexia Convergence / Delivery Model

**Repository role:** DELIVERY_TARGET  
**Repository:** `hsoliwal/eclipse.platform`  
**Convergence workspace:** `hsoliwal/com.synexia`

Synexia is the convergence workspace. This repository receives polished, verified Synexia exports
and is not the canonical experimentation workspace.


## Hard repository boundary

| Layer | Authority | Boundary |
| --- | --- | --- |
| Eclipse Platform upstream source | Eclipse Platform/upstream rights holders | Existing file licenses, notices, APIs and behavior remain authoritative. An M3 edit to an upstream file does **not** convert its license. |
| Fork-specific M3 delivery surface | This Eclipse Platform fork as delivery/product target | `.m3/**`, M3/Synexia policy files, M3 workflows and explicitly reviewed target edits are integration work; provenance, not path naming alone, determines ownership/license. |
| Canonical reusable recipes/provenance | Synexia | Synexia is the recipe/convergence source, not a runtime dependency of Eclipse Platform. |
| External donors | Original rights holders | Preserve donor license, attribution and exact provenance; no silent copying or relicensing. |

**Authority rule:** Synexia can propose/qualify a transformation; Eclipse build/tests and explicit
target review decide whether it is admitted. No recipe, scheduler, LLM or donor has automatic
promotion authority.

**History rule:** unreleased fork work may be consolidated to keep the first-release line clean while
retaining upstream ancestry. Once a release/tag is published, that released history is immutable.

```text
Synexia inventory/atomize/compare -> Maven/OpenRewrite recipe -> implement/verify/converge
 -> sealed revision + postimage hashes + receipts -> this target -> target verification
```

Target-specific findings return only as explicit evidence/proposals; they do not silently redefine
Synexia.

For M3 String/array delivery preserve:
```text
M3 surface -> MIndex/M3 mapping + shadow ABI -> precompute/metadata/search facts
           -> canonical IDs/views -> JNI/native storage/execution
```
Precompute is semantic memory above physical storage. Java primitive arrays are compatibility,
ingress/export projections when the converged owner is native-backed. Joined arrays/strings use
descriptor/ID composition where supported; flattening is an explicit boundary.

Synexia-derived code is applied through its exported Maven/OpenRewrite recipe and sealed postimages.
Preserve target public contracts unless explicitly unlocked.

Verification:
```text
diff -> lint/static analysis -> compile -> recipe replay/refusal/fixed point
     -> tests -> JNI/native runtime -> target runtime
```

A delivery records Synexia revision, recipe/task crate, exact hashes, receipts, target delta and
unresolved gaps. This policy creates no runtime dependency on Synexia.
