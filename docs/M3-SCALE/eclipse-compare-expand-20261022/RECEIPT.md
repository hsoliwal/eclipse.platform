# Eclipse Compare public override compatibility — receiver receipt

Date: 22 October 2026

## Original authority and minimal successor

- Original owner: `hsoliwal/eclipse.platform` master
  `eadfb7a2226d9f73c1302fbae249a56e01fb5d83`.
- Exact original Git blob:
  `f8c509f3772a0220e0a4f53100d6ea4e1184129c`,
  matching the pinned Eclipse aggregator revision.
- File:
  `team/bundles/org.eclipse.compare/compare/org/eclipse/compare/structuremergeviewer/DiffTreeViewer.java`.
- Sole edit:
  `protected void expandSelection()` → `public void expandSelection()`.
  This matches `AbstractTreeViewer`'s existing public method. The entire
  expansion algorithm, original Java25 syntax, neighboring methods, public
  identities, JNI/native code and Eclipse history remain untouched.

## Recipe-first provenance

- Canonical private Synexia recipe PR #13136, commit
  `1dbbeccd0cbadbaffd3e338bca0a8b34103f9414`.
- First meta-recipe `com.synexia.eclipse.CompareTextBootstrap20261022`
  uses a Java21 AST to generate the precise update to the existing
  `M3HashPinnedTextSnapshotRecipe` source admission. It allows only
  this one Java25 source, this named task crate, and two exact SHA-256
  fingerprints. Other Java source remains outside text-recipe authority.
- Second recipe `com.synexia.eclipse.CompareExpand20261022`
  generates this one modifier change through that sealed admission.
- The standard OpenRewrite Java21 parser was tested and rejected for this
  source because of its existing Java25 `_ ->` unnamed lambda. The
  `ChangeMethodAccessLevel` donor was also tested and did not match without
  complete Tycho attribution. No generic text fallback was enabled.

## Actual qualification

| Gate | Evidence | Status |
| --- | --- | --- |
| Meta Java AST recipe | JDK21, OpenRewrite 8.17.1 | 6/6 passed |
| Compare sealed source recipe | JDK21, reused Synexia engine | 6/6 passed |
| Drift, duplicates, fixed point and output fingerprints | Both recipe suites | Passed |
| Review diff | Exactly one modifier change, `git diff --check` | Passed |
| Real Java25 Tycho `org.eclipse.compare` compiler | Eclipse aggregator, Java toolchains | Progressed beyond Compare |
| Whole Eclipse aggregator packaging | `-Pbree-libs -DskipTests verify` | Next stop: Javadoc external OSGi TLS trust |
| API baseline, SWT/JFace/Compare GUI and OS-native tests | Platform matrix | Pending |

The next failure concerns the separate forked Javadoc process fetching
`https://docs.osgi.org` over an environment proxy. It is not a reason to
rewrite Eclipse Compare source. The assembled SDK, documentation generation,
and platform-native proof gates must still pass before promotion.

Keep this receiver PR draft pending the canonical recipe review and downstream
qualification; preserve all unrelated incoming changes and git history.
