# Project/module records 4

> Inspected source revision: `0de5d9acb5cff6b133cb1c55f6844720088c7d92`. Source/manifest observation only; builds and tests were not run.

[Reference home](README.md) · [Selected contracts](CONTRACTS.md) · [Coverage](COVERAGE.md)

## U0091

### `team/bundles/org.eclipse.team.ui`

- Source: [team/bundles/org.eclipse.team.ui](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.team.ui); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.team.ui/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.team.ui/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.team.ui`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.team.ui`, version `3.12.300.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.team.internal.ui.TeamUIPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.team.internal.ui;x-friends:="org.eclipse.team.cvs.ssh2,org.eclipse.team.cvs.ui,org.eclipse.team.tests.core"`, `org.eclipse.team.internal.ui.actions;x-friends:="org.eclipse.team.cvs.ssh2,org.eclipse.team.cvs.ui,org.eclipse.team.tests.core"`, `org.eclipse.team.internal.ui.dialogs;x-friends:="org.eclipse.team.cvs.ssh2,org.eclipse.team.cvs.ui,org.eclipse.team.tests.core"`, `org.eclipse.team.internal.ui.history;x-friends:="org.eclipse.team.cvs.ssh2,org.eclipse.team.cvs.ui,org.eclipse.team.tests.core"`, `org.eclipse.team.internal.ui.mapping;x-friends:="org.eclipse.team.cvs.ssh2,org.eclipse.team.cvs.ui,org.eclipse.team.tests.core"`, `org.eclipse.team.internal.ui.preferences;x-friends:="org.eclipse.team.cvs.ssh2,org.eclipse.team.cvs.ui,org.eclipse.team.tests.core"`, `org.eclipse.team.internal.ui.registry;x-friends:="org.eclipse.team.cvs.ssh2,org.eclipse.team.cvs.ui,org.eclipse.team.tests.core"`, `org.eclipse.team.internal.ui.synchronize;x-friends:="org.eclipse.team.cvs.ssh2,org.eclipse.team.cvs.ui,org.eclipse.team.tests.core"`, `org.eclipse.team.internal.ui.synchronize.actions;x-friends:="org.eclipse.team.cvs.ssh2,org.eclipse.team.cvs.ui,org.eclipse.team.tests.core"`, `org.eclipse.team.internal.ui.synchronize.patch;x-friends:="org.eclipse.jdt.ui,org.eclipse.team.tests.core"`, `org.eclipse.team.internal.ui.wizards;x-friends:="org.eclipse.team.cvs.ssh2,org.eclipse.team.cvs.ui,org.eclipse.team.tests.core"`, `org.eclipse.team.ui; uses:="org.eclipse.compare,  org.eclipse.compare.structuremergeviewer,  org.eclipse.core.resources,  org.eclipse.core.runtime,  org.eclipse.core.runtime.jobs,  org.eclipse.jface.action,  org.eclipse.jface.dialogs,  org.eclipse.jface.operation,  org.eclipse.jface.resource,  org.eclipse.jface.util,  org.eclipse.jface.viewers,  org.eclipse.jface.wizard,  org.eclipse.swt.graphics,  org.eclipse.swt.widgets,  org.eclipse.team.ui.history,  org.eclipse.team.ui.mapping,  org.eclipse.team.ui.synchronize,  org.eclipse.ui,  org.eclipse.ui.part"`, `org.eclipse.team.ui.history; uses:="org.eclipse.compare,  org.eclipse.compare.structuremergeviewer,  org.eclipse.core.resources,  org.eclipse.core.runtime,  org.eclipse.jface.action,  org.eclipse.jface.text.revisions,  org.eclipse.jface.util,  org.eclipse.jface.viewers,  org.eclipse.swt.graphics,  org.eclipse.swt.widgets,  org.eclipse.team.ui,  org.eclipse.ui,  org.eclipse.ui.part,  org.eclipse.ui.texteditor"`, `org.eclipse.team.ui.mapping; uses:="org.eclipse.compare,  org.eclipse.compare.structuremergeviewer,  org.eclipse.core.commands,  org.eclipse.core.resources,  org.eclipse.core.resources.mapping,  org.eclipse.core.runtime,  org.eclipse.jface.action,  org.eclipse.jface.preference,  org.eclipse.jface.resource,  org.eclipse.jface.util,  org.eclipse.jface.viewers,  org.eclipse.swt.graphics,  org.eclipse.team.core.diff,  org.eclipse.team.core.mapping,  org.eclipse.team.ui,  org.eclipse.team.ui.synchronize,  org.eclipse.ui,  org.eclipse.ui.actions,  org.eclipse.ui.navigator"`, `org.eclipse.team.ui.synchronize; uses:="org.eclipse.compare,  org.eclipse.compare.structuremergeviewer,  org.eclipse.core.resources,  org.eclipse.core.resources.mapping,  org.eclipse.core.runtime,  org.eclipse.core.runtime.jobs,  org.eclipse.jface.action,  org.eclipse.jface.dialogs,  org.eclipse.jface.operation,  org.eclipse.jface.resource,  org.eclipse.jface.util,  org.eclipse.jface.viewers,  org.eclipse.jface.wizard,  org.eclipse.swt.graphics,  org.eclipse.swt.widgets,  org.eclipse.team.core.diff,  org.eclipse.team.core.mapping,  org.eclipse.team.core.mapping.provider,  org.eclipse.team.core.subscribers,  org.eclipse.team.core.synchronize,  org.eclipse.team.internal.core.subscribers,  org.eclipse.team.internal.ui.synchronize,  org.eclipse.team.ui,  org.eclipse.team.ui.mapping,  org.eclipse.ui,  org.eclipse.ui.actions,  org.eclipse.ui.part"`
- Require-Bundle: `org.eclipse.ui.ide;bundle-version="[3.3.0,4.0.0)";resolution:=optional`, `org.eclipse.core.resources;bundle-version="[3.3.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.team.core;bundle-version="[3.6.0,4.0.0)"`, `org.eclipse.ui;bundle-version="[3.208.0,4.0.0)"`, `org.eclipse.compare;bundle-version="[3.3.0,4.0.0)"`, `org.eclipse.ui.forms;bundle-version="[3.3.0,4.0.0)"`, `org.eclipse.ui.navigator;bundle-version="[3.3.0,4.0.0)"`, `org.eclipse.jface.text;bundle-version="[3.3.0,4.0.0)"`, `org.eclipse.ui.editors;bundle-version="[3.3.0,4.0.0)"`, `org.eclipse.core.expressions;bundle-version="[3.6.0,4.0.0)"`
- Packaging/source configuration: [team/bundles/org.eclipse.team.ui/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.team.ui/build.properties); observed property starts `bin.includes = about.html,\`; `source.. = src/`; `output.. = bin/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.team.internal.ui`](packages-14.md#p0410), [`org.eclipse.team.internal.ui.actions`](packages-14.md#p0411), [`org.eclipse.team.internal.ui.dialogs`](packages-14.md#p0412), [`org.eclipse.team.internal.ui.history`](packages-14.md#p0413), [`org.eclipse.team.internal.ui.mapping`](packages-14.md#p0414), [`org.eclipse.team.internal.ui.preferences`](packages-14.md#p0415), [`org.eclipse.team.internal.ui.registry`](packages-14.md#p0416), [`org.eclipse.team.internal.ui.synchronize`](packages-14.md#p0417), [`org.eclipse.team.internal.ui.synchronize.actions`](packages-14.md#p0418), [`org.eclipse.team.internal.ui.synchronize.patch`](packages-14.md#p0419), [`org.eclipse.team.internal.ui.wizards`](packages-14.md#p0420), [`org.eclipse.team.ui`](packages-15.md#p0426), [`org.eclipse.team.ui.history`](packages-15.md#p0427), [`org.eclipse.team.ui.mapping`](packages-15.md#p0428), [`org.eclipse.team.ui.synchronize`](packages-15.md#p0429)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0092

### `team/bundles/org.eclipse.ui.net`

- Source: [team/bundles/org.eclipse.ui.net](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.ui.net); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.ui.net/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.ui.net/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.ui.net`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.ui.net`, version `1.6.100.qualifier`, declared name `%PLUGIN_NAME`; execution environment `JavaSE-21`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.ui.internal.net;x-internal:=true`, `org.eclipse.ui.internal.net.auth;x-internal:=true`
- Require-Bundle: `org.eclipse.ui;bundle-version="[3.208.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.core.net;bundle-version="[1.0.0,2.0.0)"`
- Packaging/source configuration: [team/bundles/org.eclipse.ui.net/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.ui.net/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.ui.internal.net`](packages-20.md#p0575), [`org.eclipse.ui.internal.net.auth`](packages-20.md#p0576)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0093

### `team/examples/org.eclipse.compare.examples`

- Source: [team/examples/org.eclipse.compare.examples](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/examples/org.eclipse.compare.examples); example/template (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/examples/org.eclipse.compare.examples/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/examples/org.eclipse.compare.examples/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.compare.examples`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.compare.examples`, version `3.6.0.qualifier`, declared name `%pluginName`; execution environment `JavaSE-25`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.compare.examples.structurecreator`
- Require-Bundle: `org.eclipse.ui;bundle-version="3.208.0"`, `org.eclipse.core.resources`, `org.eclipse.compare`, `org.eclipse.jface.text`, `org.eclipse.core.runtime`
- Packaging/source configuration: [team/examples/org.eclipse.compare.examples/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/examples/org.eclipse.compare.examples/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = doc-html/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.compare.examples.structurecreator`](packages-03.md#p0064)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0094

### `team/examples/org.eclipse.compare.examples.xml`

- Source: [team/examples/org.eclipse.compare.examples.xml](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/examples/org.eclipse.compare.examples.xml); example/template (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/examples/org.eclipse.compare.examples.xml/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/examples/org.eclipse.compare.examples.xml/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.compare.examples.xml`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.compare.examples.xml`, version `3.7.200.qualifier`, declared name `%pluginName`; execution environment `JavaSE-25`
- Lifecycle activator: `org.eclipse.compare.examples.xml.XMLPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.compare.examples.xml`, `org.eclipse.compare.examples.xml.ui`
- Import-Package: `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`
- Require-Bundle: `org.eclipse.core.runtime`, `org.eclipse.core.resources`, `org.eclipse.compare`, `org.eclipse.ui;bundle-version="3.208.0"`, `org.eclipse.jface.text`
- Packaging/source configuration: [team/examples/org.eclipse.compare.examples.xml/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/examples/org.eclipse.compare.examples.xml/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = doc-html/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.compare.examples.xml`](packages-03.md#p0065), [`org.eclipse.compare.examples.xml.ui`](packages-03.md#p0066)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0095

### `team/examples/org.eclipse.team.examples.filesystem`

- Source: [team/examples/org.eclipse.team.examples.filesystem](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/examples/org.eclipse.team.examples.filesystem); example/template (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/examples/org.eclipse.team.examples.filesystem/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/examples/org.eclipse.team.examples.filesystem/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.team.examples.filesystem`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.team.examples.filesystem`, version `3.9.0.qualifier`, declared name `%pluginName`; execution environment `JavaSE-25`
- Lifecycle activator: `org.eclipse.team.examples.filesystem.FileSystemPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.team.examples.filesystem`, `org.eclipse.team.examples.filesystem.history`, `org.eclipse.team.examples.filesystem.subscriber`, `org.eclipse.team.examples.filesystem.ui`, `org.eclipse.team.examples.localhistory`, `org.eclipse.team.examples.model`, `org.eclipse.team.examples.model.mapping`, `org.eclipse.team.examples.model.ui`, `org.eclipse.team.examples.model.ui.mapping`, `org.eclipse.team.examples.pessimistic`, `org.eclipse.team.examples.pessimistic.ui`
- Require-Bundle: `org.eclipse.core.runtime`, `org.eclipse.ui.ide;resolution:=optional`, `org.eclipse.ui.views;resolution:=optional`, `org.eclipse.jface.text;resolution:=optional`, `org.eclipse.ui.workbench.texteditor;resolution:=optional`, `org.eclipse.ui.editors;resolution:=optional`, `org.eclipse.core.resources`, `org.eclipse.team.core`, `org.eclipse.team.ui`, `org.eclipse.compare`, `org.eclipse.ui`, `org.eclipse.core.filesystem`, `org.eclipse.ui.navigator`
- Packaging/source configuration: [team/examples/org.eclipse.team.examples.filesystem/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/examples/org.eclipse.team.examples.filesystem/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = about.html,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.team.examples.filesystem`](packages-14.md#p0391), [`org.eclipse.team.examples.filesystem.history`](packages-14.md#p0392), [`org.eclipse.team.examples.filesystem.subscriber`](packages-14.md#p0393), [`org.eclipse.team.examples.filesystem.ui`](packages-14.md#p0394), [`org.eclipse.team.examples.localhistory`](packages-14.md#p0395), [`org.eclipse.team.examples.model`](packages-14.md#p0396), [`org.eclipse.team.examples.model.mapping`](packages-14.md#p0397), [`org.eclipse.team.examples.model.ui`](packages-14.md#p0398), [`org.eclipse.team.examples.model.ui.mapping`](packages-14.md#p0399), [`org.eclipse.team.examples.pessimistic`](packages-14.md#p0400), [`org.eclipse.team.examples.pessimistic.ui`](packages-14.md#p0401)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0096

### `team/tests`

- Source: [team/tests](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/tests); implementation/build; activation must be checked below
- Discovery evidence: parent module declaration; no local descriptor
- Reactor reachability: reachable through parsed non-profile module declarations
- Packaging/source configuration: [team/tests/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/tests/build.properties); observed property starts `pom.model.artifactId=team-tests`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0097

### `team/tests/org.eclipse.compare.tests`

- Source: [team/tests/org.eclipse.compare.tests](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/tests/org.eclipse.compare.tests); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/tests/org.eclipse.compare.tests/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/tests/org.eclipse.compare.tests/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.compare.tests`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.compare.tests`, version `3.9.100.qualifier`, declared name `%pluginName`; execution environment `JavaSE-25`
- Lifecycle activator: `org.eclipse.compare.tests.CompareTestPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.compare.tests`, `org.eclipse.compare.tests.performance`
- Import-Package: `org.assertj.core.api`, `org.assertj.core.api.iterable`, `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.extension;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`
- Require-Bundle: `org.eclipse.compare`, `org.eclipse.jface.text`, `org.eclipse.jface`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.ui;bundle-version="3.208.0"`, `org.eclipse.ui.ide`, `org.eclipse.ui.workbench.texteditor`, `org.eclipse.core.resources`, `org.eclipse.core.tests.resources`, `org.eclipse.core.tests.harness`, `org.eclipse.core.filesystem`, `org.eclipse.team.ui`
- Packaging/source configuration: [team/tests/org.eclipse.compare.tests/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/tests/org.eclipse.compare.tests/build.properties); observed property starts `bin.includes = plugin.properties,\`; `source.. = src/`; `output.. = bin/`; `pom.model.property.testClass = org.eclipse.compare.tests.AllCompareTests`; `pom.model.property.tycho.surefire.useUIHarness = true`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.compare.tests`](packages-03.md#p0076), [`org.eclipse.compare.tests.performance`](packages-03.md#p0077), [`testPackage`](packages-20.md#p0596)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0098

### `team/tests/org.eclipse.core.tests.net`

- Source: [team/tests/org.eclipse.core.tests.net](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/tests/org.eclipse.core.tests.net); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/tests/org.eclipse.core.tests.net/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/tests/org.eclipse.core.tests.net/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.tests.net`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.core.tests.net`, version `1.6.500.qualifier`, declared name `Net Tests Plug-in`; execution environment `JavaSE-25`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.tests.net`
- Import-Package: `org.assertj.core.api`, `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.core.net;bundle-version="[1.0.0,2.0.0)"`
- Packaging/source configuration: [team/tests/org.eclipse.core.tests.net/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/tests/org.eclipse.core.tests.net/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`; `pom.model.property.testSuite = ${project.artifactId}`; `pom.model.property.testClass = org.eclipse.core.tests.net.AllNetTests`; `pom.model.property.skipTests = true`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.tests.net`](packages-06.md#p0157)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0099

### `team/tests/org.eclipse.team.tests.core`

- Source: [team/tests/org.eclipse.team.tests.core](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/tests/org.eclipse.team.tests.core); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/tests/org.eclipse.team.tests.core/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/tests/org.eclipse.team.tests.core/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.team.tests.core`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.team.tests.core`, version `3.11.100.qualifier`, declared name `%pluginName`; execution environment `JavaSE-25`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.team.tests.core`, `org.eclipse.team.tests.core.mapping`, `org.eclipse.team.tests.core.regression`, `org.eclipse.team.tests.ui`, `org.eclipse.team.tests.ui.synchronize`
- Import-Package: `org.assertj.core.api`, `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.extension;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.function;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`
- Require-Bundle: `org.eclipse.ui.ide;resolution:=optional`, `org.eclipse.ui.views;resolution:=optional`, `org.eclipse.jface.text;resolution:=optional`, `org.eclipse.ui.workbench.texteditor;resolution:=optional`, `org.eclipse.ui.editors;resolution:=optional`, `org.eclipse.core.tests.harness`, `org.eclipse.core.resources`, `org.eclipse.core.runtime`, `org.eclipse.team.core`, `org.eclipse.team.ui`, `org.eclipse.compare`, `org.eclipse.ui`, `org.eclipse.core.tests.resources`, `org.eclipse.ui.navigator`, `org.eclipse.core.filesystem`, `org.eclipse.compare.tests`
- Packaging/source configuration: [team/tests/org.eclipse.team.tests.core/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/tests/org.eclipse.team.tests.core/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = about.html,\`; `pom.model.packaging = eclipse-test-plugin`; `pom.model.property.testClass = org.eclipse.team.tests.core.AllTests`; `pom.model.property.tycho.surefire.useUIHarness = true`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.team.internal.ui.synchronize`](packages-14.md#p0417), [`org.eclipse.team.tests.core`](packages-15.md#p0421), [`org.eclipse.team.tests.core.mapping`](packages-15.md#p0422), [`org.eclipse.team.tests.core.regression`](packages-15.md#p0423), [`org.eclipse.team.tests.ui`](packages-15.md#p0424), [`org.eclipse.team.tests.ui.synchronize`](packages-15.md#p0425)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0100

### `terminal`

- Source: [terminal](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal); implementation/build; activation must be checked below
- Discovery evidence: [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/pom.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Declared Maven coordinates: `org.eclipse.platform:eclipse.platform.terminal:4.42.0-SNAPSHOT`; packaging `pom`; parent `org.eclipse.platform:eclipse.platform:4.42.0-SNAPSHOT`, relative path `omitted; effective parent convention applies`
- Aggregates: [`terminal/bundles`](projects-04.md#u0101) (non-profile declaration), [`terminal/features`](projects-04.md#u0109) (non-profile declaration), [`terminal/tests`](projects-04.md#u0111) (non-profile declaration)
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0101

### `terminal/bundles`

- Source: [terminal/bundles](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles); implementation/build; activation must be checked below
- Discovery evidence: parent module declaration; no local descriptor
- Reactor reachability: reachable through parsed non-profile module declarations
- Packaging/source configuration: [terminal/bundles/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/build.properties); observed property starts `pom.model.artifactId=terminal-plugins`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0102

### `terminal/bundles/org.eclipse.terminal.connector.local`

- Source: [terminal/bundles/org.eclipse.terminal.connector.local](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.local); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.local/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.local/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.terminal.connector.local`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.terminal.connector.local`, version `1.1.200.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.terminal.connector.local.activator.UIPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.terminal.connector.local.controls;x-internal:=true`, `org.eclipse.terminal.connector.local.launcher;x-internal:=true`
- Import-Package: `org.eclipse.cdt.utils.pty;mandatory:=native`, `org.eclipse.terminal.connector;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.connector.process;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.view.core;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.view.ui;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.view.ui.launcher;version="[1.0.0,2.0.0)"`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.33.0,4)"`, `org.eclipse.core.variables;bundle-version="[3.6.500,4)"`, `org.eclipse.debug.ui;bundle-version="[3.18.800,4)"`, `org.eclipse.ui;bundle-version="[3.208.0,4)"`
- Packaging/source configuration: [terminal/bundles/org.eclipse.terminal.connector.local/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.local/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.terminal.connector.local.activator`](packages-15.md#p0431), [`org.eclipse.terminal.connector.local.controls`](packages-15.md#p0432), [`org.eclipse.terminal.connector.local.launcher`](packages-15.md#p0433)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0103

### `terminal/bundles/org.eclipse.terminal.connector.process`

- Source: [terminal/bundles/org.eclipse.terminal.connector.process](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.process); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.process/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.process/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.terminal.connector.process`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.terminal.connector.process`, version `1.1.100.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.terminal.connector.process.internal.UIPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.terminal.connector.process;version="1.0.100"`, `org.eclipse.terminal.connector.process.internal;x-internal:=true`
- Import-Package: `org.eclipse.cdt.utils.pty;mandatory:=native`, `org.eclipse.cdt.utils.spawner;mandatory:=native`, `org.eclipse.terminal.connector;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.connector.provider;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.view.core;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.view.ui;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.view.ui.launcher;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.view.ui.streams;version="[1.0.0,2.0.0)"`
- Require-Bundle: `org.eclipse.core.expressions;bundle-version="[3.9.400,4)"`, `org.eclipse.core.resources;bundle-version="[3.22.200,4)";resolution:=optional`, `org.eclipse.core.runtime;bundle-version="[3.33.0,4)"`, `org.eclipse.ui;bundle-version="[3.208.0,4)"`
- Packaging/source configuration: [terminal/bundles/org.eclipse.terminal.connector.process/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.process/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.terminal.connector.process`](packages-15.md#p0434), [`org.eclipse.terminal.connector.process.internal`](packages-15.md#p0435)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0104

### `terminal/bundles/org.eclipse.terminal.connector.ssh`

- Source: [terminal/bundles/org.eclipse.terminal.connector.ssh](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.ssh); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.ssh/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.ssh/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.terminal.connector.ssh`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.terminal.connector.ssh`, version `1.1.100.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.terminal.connector.ssh.activator.UIPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.terminal.connector.ssh.connector;x-internal:=true`, `org.eclipse.terminal.connector.ssh.controls;x-internal:=true`, `org.eclipse.terminal.connector.ssh.launcher;x-internal:=true`
- Import-Package: `com.jcraft.jsch;version="[0.1.31,3.0.0)"`, `org.eclipse.terminal.connector;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.connector.provider;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.view.core;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.view.ui;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.view.ui.launcher;version="[1.0.0,2.0.0)"`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.33.0,4)"`, `org.eclipse.equinox.security;bundle-version="[1.4.600,2)"`, `org.eclipse.ui;bundle-version="[3.208.0,4)"`, `org.eclipse.jsch.core;bundle-version="[1.5.600,2.0.0)"`
- Packaging/source configuration: [terminal/bundles/org.eclipse.terminal.connector.ssh/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.ssh/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.terminal.connector.ssh.activator`](packages-15.md#p0437), [`org.eclipse.terminal.connector.ssh.connector`](packages-15.md#p0438), [`org.eclipse.terminal.connector.ssh.controls`](packages-15.md#p0439), [`org.eclipse.terminal.connector.ssh.launcher`](packages-15.md#p0440), [`org.eclipse.terminal.connector.ssh.nls`](packages-15.md#p0441)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0105

### `terminal/bundles/org.eclipse.terminal.connector.telnet`

- Source: [terminal/bundles/org.eclipse.terminal.connector.telnet](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.telnet); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.telnet/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.telnet/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.terminal.connector.telnet`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.terminal.connector.telnet`, version `1.1.100.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.terminal.connector.telnet.activator.UIPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.terminal.connector.telnet.connector;x-internal:=true`, `org.eclipse.terminal.connector.telnet.controls;x-internal:=true`, `org.eclipse.terminal.connector.telnet.launcher;x-internal:=true`
- Import-Package: `org.eclipse.terminal.connector;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.connector.provider;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.view.core;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.view.ui.launcher;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.view.ui;version="[1.0.0,2.0.0)"`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.33.0,4)"`, `org.eclipse.ui;bundle-version="[3.208.0,4)"`
- Packaging/source configuration: [terminal/bundles/org.eclipse.terminal.connector.telnet/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.connector.telnet/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.terminal.connector.telnet.activator`](packages-15.md#p0442), [`org.eclipse.terminal.connector.telnet.connector`](packages-15.md#p0443), [`org.eclipse.terminal.connector.telnet.controls`](packages-15.md#p0444), [`org.eclipse.terminal.connector.telnet.launcher`](packages-15.md#p0445), [`org.eclipse.terminal.connector.telnet.nls`](packages-15.md#p0446)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0106

### `terminal/bundles/org.eclipse.terminal.control`

- Source: [terminal/bundles/org.eclipse.terminal.control](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.control); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.control/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.control/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.terminal.control`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.terminal.control`, version `1.1.200.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.terminal.internal.control.impl.TerminalPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.terminal.connector;version="1.0.100"; uses:="org.eclipse.core.runtime,  org.eclipse.jface.dialogs,  org.eclipse.jface.fieldassist,  org.eclipse.swt.widgets,  org.eclipse.terminal.control"`, `org.eclipse.terminal.connector.provider;version="1.0.100";uses:="org.eclipse.terminal.connector"`, `org.eclipse.terminal.control;version="1.0.100"; uses:="org.eclipse.swt.dnd,  org.eclipse.swt.graphics,  org.eclipse.swt.widgets,  org.eclipse.terminal.connector,  org.eclipse.terminal.model"`, `org.eclipse.terminal.internal.connector;x-internal:=true`, `org.eclipse.terminal.internal.control.impl;x-internal:=true;x-friends:="org.eclipse.terminal.view.ui"`, `org.eclipse.terminal.internal.emulator;x-internal:=true`, `org.eclipse.terminal.internal.model;x-internal:=true`, `org.eclipse.terminal.internal.preferences;x-internal:=true;x-friends:="org.eclipse.terminal.view.ui"`, `org.eclipse.terminal.internal.textcanvas;x-internal:=true`, `org.eclipse.terminal.model;version="1.0.100";uses:="org.eclipse.swt.graphics"`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.33.0,4.0.0)"`, `org.eclipse.ui;bundle-version="[3.208.0,4.0.0)"`
- Packaging/source configuration: [terminal/bundles/org.eclipse.terminal.control/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.control/build.properties); observed property starts `bin.includes = .,\`; `source.. = src/`; `output.. = bin/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.terminal.connector`](packages-15.md#p0430), [`org.eclipse.terminal.connector.provider`](packages-15.md#p0436), [`org.eclipse.terminal.control`](packages-15.md#p0447), [`org.eclipse.terminal.internal.connector`](packages-15.md#p0448), [`org.eclipse.terminal.internal.control.impl`](packages-15.md#p0449), [`org.eclipse.terminal.internal.emulator`](packages-15.md#p0450), [`org.eclipse.terminal.internal.model`](packages-16.md#p0451), [`org.eclipse.terminal.internal.preferences`](packages-16.md#p0452), [`org.eclipse.terminal.internal.textcanvas`](packages-16.md#p0456), [`org.eclipse.terminal.model`](packages-16.md#p0457)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0107

### `terminal/bundles/org.eclipse.terminal.view.core`

- Source: [terminal/bundles/org.eclipse.terminal.view.core](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.view.core); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.view.core/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.view.core/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.terminal.view.core`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.terminal.view.core`, version `1.0.100.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.terminal.view.core.internal.CoreBundleActivator`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.terminal.view.core;version="1.0.0"`, `org.eclipse.terminal.view.core.internal;x-internal:=true`
- Require-Bundle: `org.eclipse.core.expressions;bundle-version="[3.9.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.33.0,4.0.0)"`
- Packaging/source configuration: [terminal/bundles/org.eclipse.terminal.view.core/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.view.core/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.terminal.view.core`](packages-16.md#p0459), [`org.eclipse.terminal.view.core.internal`](packages-16.md#p0460)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0108

### `terminal/bundles/org.eclipse.terminal.view.ui`

- Source: [terminal/bundles/org.eclipse.terminal.view.ui](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.view.ui); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.view.ui/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.view.ui/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.terminal.view.ui`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.terminal.view.ui`, version `1.1.300.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.terminal.view.ui.internal.UIPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.terminal.view.ui;version="1.0.100"`, `org.eclipse.terminal.view.ui.internal;x-internal:=true`, `org.eclipse.terminal.view.ui.internal.actions;x-internal:=true`, `org.eclipse.terminal.view.ui.internal.dialogs;x-internal:=true`, `org.eclipse.terminal.view.ui.internal.handler;x-internal:=true`, `org.eclipse.terminal.view.ui.internal.listeners;x-internal:=true`, `org.eclipse.terminal.view.ui.internal.local.showin;x-internal:=true`, `org.eclipse.terminal.view.ui.internal.local.showin.detectors;x-internal:=true`, `org.eclipse.terminal.view.ui.internal.preferences;x-internal:=true`, `org.eclipse.terminal.view.ui.internal.tabs;x-internal:=true`, `org.eclipse.terminal.view.ui.internal.view;x-internal:=true`, `org.eclipse.terminal.view.ui.launcher;version="1.0.100"`, `org.eclipse.terminal.view.ui.streams;version="1.0.100"`
- Import-Package: `org.eclipse.terminal.connector;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.connector.provider;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.control;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.model;version="[1.0.0,2.0.0)"`
- Require-Bundle: `org.eclipse.core.expressions;bundle-version="[3.9.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.33.0,4)"`, `org.eclipse.core.resources;bundle-version="[3.22.0,4.0.0)";resolution:=optional`, `org.eclipse.core.variables;bundle-version="[3.6.0,4.0.0)"`, `org.eclipse.debug.ui;bundle-version="[3.18.0,4.0.0)";resolution:=optional`, `org.eclipse.swt;bundle-version="[3.135.0,4.0.0)"`, `org.eclipse.ui;bundle-version="[3.208.0,4.0.0)"`, `org.eclipse.ui.ide;bundle-version="[3.22.0,4.0.0)";resolution:=optional`, `org.eclipse.ui.editors;bundle-version="[3.20.0,4.0.0)";resolution:=optional`, `org.eclipse.text;bundle-version="[3.14.0,4.0.0)";resolution:=optional`, `org.eclipse.terminal.view.core;bundle-version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.control;bundle-version="[1.0.0,2.0.0)"`
- Packaging/source configuration: [terminal/bundles/org.eclipse.terminal.view.ui/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/bundles/org.eclipse.terminal.view.ui/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.terminal.view.ui`](packages-16.md#p0461), [`org.eclipse.terminal.view.ui.internal`](packages-16.md#p0462), [`org.eclipse.terminal.view.ui.internal.actions`](packages-16.md#p0463), [`org.eclipse.terminal.view.ui.internal.dialogs`](packages-16.md#p0464), [`org.eclipse.terminal.view.ui.internal.handler`](packages-16.md#p0465), [`org.eclipse.terminal.view.ui.internal.listeners`](packages-16.md#p0466), [`org.eclipse.terminal.view.ui.internal.local.showin`](packages-16.md#p0467), [`org.eclipse.terminal.view.ui.internal.local.showin.detectors`](packages-16.md#p0468), [`org.eclipse.terminal.view.ui.internal.preferences`](packages-16.md#p0469), [`org.eclipse.terminal.view.ui.internal.tabs`](packages-16.md#p0470), [`org.eclipse.terminal.view.ui.internal.view`](packages-16.md#p0471), [`org.eclipse.terminal.view.ui.launcher`](packages-16.md#p0472), [`org.eclipse.terminal.view.ui.streams`](packages-16.md#p0473)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0109

### `terminal/features`

- Source: [terminal/features](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/features); implementation/build; activation must be checked below
- Discovery evidence: parent module declaration; no local descriptor
- Reactor reachability: reachable through parsed non-profile module declarations
- Packaging/source configuration: [terminal/features/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/features/build.properties); observed property starts `pom.model.artifactId=terminal-features`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0110

### `terminal/features/org.eclipse.terminal.feature`

- Source: [terminal/features/org.eclipse.terminal.feature](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/features/org.eclipse.terminal.feature); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/features/org.eclipse.terminal.feature/.project), [feature.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/features/org.eclipse.terminal.feature/feature.xml)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.terminal.feature`; natures: `org.eclipse.pde.FeatureNature`
- Distribution feature: `org.eclipse.terminal.feature`; included plug-ins `org.eclipse.terminal.control`, `org.eclipse.terminal.view.core`, `org.eclipse.terminal.view.ui`, `org.eclipse.terminal.connector.process`, `org.eclipse.terminal.connector.local`, `org.eclipse.terminal.connector.telnet`, `org.eclipse.terminal.connector.ssh`; included features none declared
- Packaging/source configuration: [terminal/features/org.eclipse.terminal.feature/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/features/org.eclipse.terminal.feature/build.properties); observed property starts `bin.includes = feature.xml,\`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0111

### `terminal/tests`

- Source: [terminal/tests](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/tests); implementation/build; activation must be checked below
- Discovery evidence: parent module declaration; no local descriptor
- Reactor reachability: reachable through parsed non-profile module declarations
- Packaging/source configuration: [terminal/tests/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/tests/build.properties); observed property starts `pom.model.artifactId=terminal-tests`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0112

### `terminal/tests/org.eclipse.terminal.test`

- Source: [terminal/tests/org.eclipse.terminal.test](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/tests/org.eclipse.terminal.test); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/tests/org.eclipse.terminal.test/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/tests/org.eclipse.terminal.test/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.terminal.test`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.terminal.test`, version `1.1.100.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Export-Package: `org.eclipse.terminal.internal.connector;x-internal:=true`, `org.eclipse.terminal.internal.emulator;x-internal:=true`, `org.eclipse.terminal.internal.model;x-internal:=true`, `org.eclipse.terminal.internal.speedtest;x-internal:=true`, `org.eclipse.terminal.internal.test.terminalcanvas;x-internal:=true`, `org.eclipse.terminal.internal.test.ui;x-internal:=true`, `org.eclipse.terminal.internal.textcanvas;x-internal:=true`, `org.eclipse.terminal.model`, `org.eclipse.terminal.test`
- Import-Package: `org.junit.jupiter.api;version="[5.9.3,6.0.0)"`, `org.junit.jupiter.api.function;version="[5.9.3,6.0.0)"`, `org.junit.platform.suite.api;version="[1.9.3,2.0.0)"`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.33.0,4)"`, `org.eclipse.ui;bundle-version="[3.208.0,4)"`, `org.opentest4j;bundle-version="[1.3.0,2)"`, `org.eclipse.terminal.control;bundle-version="1.0.0"`, `org.eclipse.terminal.view.ui;bundle-version="[1.1.200,2.0.0)"`
- Packaging/source configuration: [terminal/tests/org.eclipse.terminal.test/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/terminal/tests/org.eclipse.terminal.test/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`; `pom.model.property.testClass = org.eclipse.terminal.test.AutomatedIntegrationSuite`; `pom.model.property.tycho.surefire.useUIHarness = true`; `pom.model.property.tycho.surefire.useUIThread = true`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.terminal.internal.connector`](packages-15.md#p0448), [`org.eclipse.terminal.internal.emulator`](packages-15.md#p0450), [`org.eclipse.terminal.internal.model`](packages-16.md#p0451), [`org.eclipse.terminal.internal.speedtest`](packages-16.md#p0453), [`org.eclipse.terminal.internal.test.terminalcanvas`](packages-16.md#p0454), [`org.eclipse.terminal.internal.test.ui`](packages-16.md#p0455), [`org.eclipse.terminal.internal.textcanvas`](packages-16.md#p0456), [`org.eclipse.terminal.model`](packages-16.md#p0457), [`org.eclipse.terminal.test`](packages-16.md#p0458), [`org.eclipse.terminal.view.ui.tests`](packages-16.md#p0474)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0113

### `ua`

- Source: [ua](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua); implementation/build; activation must be checked below
- Discovery evidence: [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/pom.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Declared Maven coordinates: `eclipse.platform.ua:eclipse.platform.ua:4.42.0-SNAPSHOT`; packaging `pom`; parent `org.eclipse.platform:eclipse.platform:4.42.0-SNAPSHOT`, relative path `omitted; effective parent convention applies`
- Aggregates: [`ua/org.eclipse.help`](projects-04.md#u0116) (non-profile declaration), [`ua/org.eclipse.help.base`](projects-04.md#u0118) (non-profile declaration), [`ua/org.eclipse.help.ui`](projects-04.md#u0119) (non-profile declaration), [`ua/org.eclipse.help.webapp`](projects-04.md#u0120) (non-profile declaration), [`ua/org.eclipse.help-feature`](projects-04.md#u0117) (non-profile declaration), [`ua/org.eclipse.ua.tests`](projects-05.md#u0128) (non-profile declaration), [`ua/org.eclipse.ua.tests.doc`](projects-05.md#u0129) (non-profile declaration), [`ua/org.eclipse.ui.cheatsheets`](projects-05.md#u0130) (non-profile declaration), [`ua/org.eclipse.ui.intro`](projects-05.md#u0131) (non-profile declaration), [`ua/org.eclipse.ui.intro.universal`](projects-05.md#u0135) (non-profile declaration), [`ua/org.eclipse.ui.intro.quicklinks`](projects-05.md#u0132) (non-profile declaration), [`ua/org.eclipse.ui.intro.quicklinks.examples`](projects-05.md#u0133) (non-profile declaration), [`ua/org.eclipse.ui.intro.solstice.examples`](projects-05.md#u0134) (non-profile declaration), [`ua/org.eclipse.tips.core`](projects-05.md#u0121) (non-profile declaration), [`ua/org.eclipse.tips.ui`](projects-05.md#u0127) (non-profile declaration), [`ua/org.eclipse.tips.ide`](projects-05.md#u0124) (non-profile declaration), [`ua/org.eclipse.tips.json`](projects-05.md#u0125) (non-profile declaration), [`ua/org.eclipse.tips.feature`](projects-05.md#u0123) (non-profile declaration), [`ua/org.eclipse.tips.tests`](projects-05.md#u0126) (non-profile declaration), [`ua/infocenter-web/infocenter-product`](projects-04.md#u0115) (non-profile declaration), [`ua/infocenter-web/infocenter-app`](projects-04.md#u0114) (non-profile declaration)
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0114

### `ua/infocenter-web/infocenter-app`

- Source: [ua/infocenter-web/infocenter-app](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/infocenter-web/infocenter-app); implementation/build; activation must be checked below
- Discovery evidence: [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/infocenter-web/infocenter-app/pom.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Declared Maven coordinates: `org.eclipse.help:infocenter-app:4.42.0-SNAPSHOT`; packaging `war`; parent `eclipse.platform.ua:eclipse.platform.ua:4.42.0-SNAPSHOT`, relative path `../..`
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0115

### `ua/infocenter-web/infocenter-product`

- Source: [ua/infocenter-web/infocenter-product](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/infocenter-web/infocenter-product); implementation/build; activation must be checked below
- Discovery evidence: [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/infocenter-web/infocenter-product/pom.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Declared Maven coordinates: `org.eclipse.help:infocenter-product:1.0.0-SNAPSHOT`; packaging `eclipse-repository`; parent `eclipse.platform.ua:eclipse.platform.ua:4.42.0-SNAPSHOT`, relative path `../..`
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0116

### `ua/org.eclipse.help`

- Source: [ua/org.eclipse.help](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.help`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.help`, version `3.11.300.qualifier`, declared name `%help_plugin_name`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.help.internal.HelpPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.help;uses:="org.eclipse.core.expressions"`, `org.eclipse.help.internal; x-friends:="org.eclipse.help.base,  org.eclipse.help.ui,  org.eclipse.help.webapp,  org.eclipse.ui.intro,  org.eclipse.ua.tests,  org.eclipse.ui.cheatsheets,  org.eclipse.ua.tests.doc"`, `org.eclipse.help.internal.context; x-friends:="org.eclipse.help.base,  org.eclipse.help.ui,  org.eclipse.ua.tests,  org.eclipse.help.webapp"`, `org.eclipse.help.internal.criteria; x-friends:="org.eclipse.help.base,  org.eclipse.help.webapp,  org.eclipse.ua.tests,  org.eclipse.help.ui"`, `org.eclipse.help.internal.dynamic; x-friends:="org.eclipse.ua.tests,  org.eclipse.help.ui,  org.eclipse.help.base,  org.eclipse.ui.intro,  org.eclipse.help.webapp,  org.eclipse.ui.cheatsheets"`, `org.eclipse.help.internal.entityresolver; x-friends:="org.eclipse.help.base,  org.eclipse.help.ui,  org.eclipse.ui.cheatsheets,  org.eclipse.ua.tests"`, `org.eclipse.help.internal.extension;x-friends:="org.eclipse.help.webapp,org.eclipse.help.base,org.eclipse.ua.tests"`, `org.eclipse.help.internal.index; x-friends:="org.eclipse.help.webapp,  org.eclipse.ua.tests,  org.eclipse.help.ui,  org.eclipse.help.base"`, `org.eclipse.help.internal.toc; x-friends:="org.eclipse.help.base,  org.eclipse.help.ui,  org.eclipse.help.webapp,  org.eclipse.ua.tests,  org.eclipse.ua.tests.doc"`, `org.eclipse.help.internal.util; x-friends:="org.eclipse.help.base,  org.eclipse.help.ui,  org.eclipse.help.webapp,  org.eclipse.ua.tests,  org.eclipse.ui.intro.universal,  org.eclipse.ui.intro"`
- Import-Package: `javax.xml.parsers`, `javax.xml.transform`, `javax.xml.transform.dom`, `javax.xml.transform.stream`, `org.w3c.dom`, `org.xml.sax`, `org.xml.sax.helpers`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.core.expressions;bundle-version="[3.4.200,4.0.0)";visibility:=reexport`
- Packaging/source configuration: [ua/org.eclipse.help/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = livehelp.js,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.help`](packages-11.md#p0325), [`org.eclipse.help.internal`](packages-11.md#p0328), [`org.eclipse.help.internal.context`](packages-12.md#p0336), [`org.eclipse.help.internal.criteria`](packages-12.md#p0337), [`org.eclipse.help.internal.dynamic`](packages-12.md#p0338), [`org.eclipse.help.internal.entityresolver`](packages-12.md#p0339), [`org.eclipse.help.internal.extension`](packages-12.md#p0340), [`org.eclipse.help.internal.index`](packages-12.md#p0341), [`org.eclipse.help.internal.toc`](packages-12.md#p0347), [`org.eclipse.help.internal.util`](packages-12.md#p0348)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0117

### `ua/org.eclipse.help-feature`

- Source: [ua/org.eclipse.help-feature](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help-feature); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help-feature/.project), [feature.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help-feature/feature.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.help-feature`; natures: `org.eclipse.pde.FeatureNature`
- Distribution feature: `org.eclipse.help`; included plug-ins `org.eclipse.equinox.http.jetty`, `org.eclipse.equinox.http.registry`, `org.eclipse.equinox.http.servlet`, `org.eclipse.equinox.jsp.jasper`, `org.eclipse.equinox.jsp.jasper.registry`, `org.eclipse.help.base`, `org.eclipse.help.ui`, `org.eclipse.help.webapp`, `org.eclipse.core.net`, `org.eclipse.equinox.security`; included features none declared
- Packaging/source configuration: [ua/org.eclipse.help-feature/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help-feature/build.properties); observed property starts `bin.includes = feature.xml,\`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0118

### `ua/org.eclipse.help.base`

- Source: [ua/org.eclipse.help.base](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help.base); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help.base/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help.base/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.help.base`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.help.base`, version `4.6.300.qualifier`, declared name `%help_base_plugin_name`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.help.internal.base.HelpBasePlugin`
- Activation policy: `lazy`
- Export-Package: `org.apache.lucene.demo.html;x-internal:=true`, `org.eclipse.help.base;uses:="org.eclipse.help"`, `org.eclipse.help.browser`, `org.eclipse.help.internal.base; x-friends:="org.eclipse.help.ui,  org.eclipse.help.webapp,  org.eclipse.ua.tests,  org.eclipse.ui.intro,  org.eclipse.ua.tests.doc"`, `org.eclipse.help.internal.base.remote;x-friends:="org.eclipse.ua.tests,org.eclipse.help.webapp,org.eclipse.help.ui"`, `org.eclipse.help.internal.base.scope;x-friends:="org.eclipse.help.webapp,org.eclipse.ua.tests,org.eclipse.help.ui"`, `org.eclipse.help.internal.base.util;x-friends:="org.eclipse.help.ui,org.eclipse.help.webapp,org.eclipse.ua.tests"`, `org.eclipse.help.internal.browser;x-friends:="org.eclipse.help.ui,org.eclipse.ua.tests"`, `org.eclipse.help.internal.browser.macosx;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.help.internal.protocols; x-friends:="org.eclipse.help.base,  org.eclipse.help.ui,  org.eclipse.help.webapp,  org.eclipse.ua.tests"`, `org.eclipse.help.internal.search;x-friends:="org.eclipse.help.ui,org.eclipse.help.webapp,org.eclipse.ua.tests"`, `org.eclipse.help.internal.search.federated;x-friends:="org.eclipse.help.ui,org.eclipse.ua.tests"`, `org.eclipse.help.internal.server;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.help.internal.standalone;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.help.internal.validation;x-friends:="org.eclipse.ua.tests,org.eclipse.ua.tests.doc"`, `org.eclipse.help.internal.workingset;x-friends:="org.eclipse.help.ui,org.eclipse.help.webapp,org.eclipse.ua.tests"`, `org.eclipse.help.internal.xhtml; x-friends:="org.eclipse.help.ui,  org.eclipse.ua.tests,  org.eclipse.ui.intro,  org.eclipse.help.webapp"`, `org.eclipse.help.search;uses:="org.eclipse.core.runtime,org.eclipse.help,org.xml.sax"`, `org.eclipse.help.server`, `org.eclipse.help.standalone`
- Import-Package: `org.apache.lucene.analysis;version="[10.0.0,11.0.0)"`, `org.apache.lucene.analysis.br;version="[10.0.0,11.0.0)"`, `org.apache.lucene.analysis.cjk;version="[10.0.0,11.0.0)"`, `org.apache.lucene.analysis.cn.smart;version="[10.0.0,11.0.0)"`, `org.apache.lucene.analysis.core;version="[10.0.0,11.0.0)"`, `org.apache.lucene.analysis.cz;version="[10.0.0,11.0.0)"`, `org.apache.lucene.analysis.de;version="[10.0.0,11.0.0)"`, `org.apache.lucene.analysis.el;version="[10.0.0,11.0.0)"`, `org.apache.lucene.analysis.en;version="[10.0.0,11.0.0)"`, `org.apache.lucene.analysis.fr;version="[10.0.0,11.0.0)"`, `org.apache.lucene.analysis.miscellaneous;version="[10.0.0,11.0.0)"`, `org.apache.lucene.analysis.nl;version="[10.0.0,11.0.0)"`, `org.apache.lucene.analysis.ru;version="[10.0.0,11.0.0)"`, `org.apache.lucene.analysis.standard;version="[10.0.0,11.0.0)"`, `org.apache.lucene.analysis.tokenattributes;version="[10.0.0,11.0.0)"`, `org.apache.lucene.analysis.util;version="[10.0.0,11.0.0)"`, `org.apache.lucene.document;version="[10.0.0,11.0.0)"`, `org.apache.lucene.index;version="[10.0.0,11.0.0)"`, `org.apache.lucene.search;version="[10.0.0,11.0.0)"`, `org.apache.lucene.store;version="[10.0.0,11.0.0)"`, `org.apache.lucene.util;version="[10.0.0,11.0.0)"`, `org.eclipse.equinox.http.jetty;resolution:=optional`
- Require-Bundle: `org.eclipse.ant.core;bundle-version="[3.2.200,4.0.0)";resolution:=optional`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.help;bundle-version="[3.5.0,4.0.0)";visibility:=reexport`, `org.eclipse.core.net;bundle-version="[1.2.200,2.0.0)"`
- Packaging/source configuration: [ua/org.eclipse.help.base/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help.base/build.properties); observed property starts `source.. = src/,\`; `output.. = bin/`; `bin.includes = doc/,\`; `source.ant_tasks/helpbase-ant.jar = src_ant/`; `output.ant_tasks/helpbase-ant.jar = bin2/`; `pom.model.property.defaultSigning-excludeInnerJars = true`; `pom.model.property.tycho.buildqualifier.format = 'v${buildTimestamp}'`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.apache.lucene.demo.html`](packages-01.md#p0003), [`org.eclipse.help.base`](packages-11.md#p0326), [`org.eclipse.help.browser`](packages-11.md#p0327), [`org.eclipse.help.internal.base`](packages-11.md#p0329), [`org.eclipse.help.internal.base.ant`](packages-11.md#p0330), [`org.eclipse.help.internal.base.remote`](packages-12.md#p0331), [`org.eclipse.help.internal.base.scope`](packages-12.md#p0332), [`org.eclipse.help.internal.base.util`](packages-12.md#p0333), [`org.eclipse.help.internal.browser`](packages-12.md#p0334), [`org.eclipse.help.internal.browser.macosx`](packages-12.md#p0335), [`org.eclipse.help.internal.protocols`](packages-12.md#p0342), [`org.eclipse.help.internal.search`](packages-12.md#p0343), [`org.eclipse.help.internal.search.federated`](packages-12.md#p0344), [`org.eclipse.help.internal.server`](packages-12.md#p0345), [`org.eclipse.help.internal.standalone`](packages-12.md#p0346), [`org.eclipse.help.internal.validation`](packages-12.md#p0349), [`org.eclipse.help.internal.workingset`](packages-12.md#p0356), [`org.eclipse.help.internal.xhtml`](packages-12.md#p0357), [`org.eclipse.help.search`](packages-12.md#p0358), [`org.eclipse.help.server`](packages-12.md#p0359), [`org.eclipse.help.standalone`](packages-12.md#p0360)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0119

### `ua/org.eclipse.help.ui`

- Source: [ua/org.eclipse.help.ui](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help.ui); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help.ui/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help.ui/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.help.ui`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.help.ui`, version `4.8.600.qualifier`, declared name `%help_system_plugin_name`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.help.ui.internal.HelpUIPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.help.ui;uses:="org.eclipse.help.search,org.eclipse.jface.preference,org.eclipse.swt.widgets"`, `org.eclipse.help.ui.browser; uses:="org.eclipse.core.runtime,  org.eclipse.jface.action,  org.eclipse.jface.viewers,  org.eclipse.ui"`, `org.eclipse.help.ui.internal;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.help.ui.internal.browser;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.help.ui.internal.browser.embedded;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.help.ui.internal.dynamic;x-internal:=true`, `org.eclipse.help.ui.internal.handlers;x-internal:=true`, `org.eclipse.help.ui.internal.preferences;x-internal:=true`, `org.eclipse.help.ui.internal.search;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.help.ui.internal.util;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.help.ui.internal.views;x-friends:="org.eclipse.ui.cheatsheets,org.eclipse.ua.tests"`
- Require-Bundle: `org.eclipse.help.base;bundle-version="[4.0.0,5.0.0)";visibility:=reexport`, `org.eclipse.ui;bundle-version="[3.206.200,4.0.0)";visibility:=reexport`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)";visibility:=reexport`, `org.eclipse.ui.forms;bundle-version="[3.5.0,4.0.0)"`
- Packaging/source configuration: [ua/org.eclipse.help.ui/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help.ui/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = icons/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.help.ui`](packages-13.md#p0361), [`org.eclipse.help.ui.browser`](packages-13.md#p0362), [`org.eclipse.help.ui.internal`](packages-13.md#p0363), [`org.eclipse.help.ui.internal.browser`](packages-13.md#p0364), [`org.eclipse.help.ui.internal.browser.embedded`](packages-13.md#p0365), [`org.eclipse.help.ui.internal.dynamic`](packages-13.md#p0366), [`org.eclipse.help.ui.internal.handlers`](packages-13.md#p0367), [`org.eclipse.help.ui.internal.preferences`](packages-13.md#p0368), [`org.eclipse.help.ui.internal.search`](packages-13.md#p0369), [`org.eclipse.help.ui.internal.util`](packages-13.md#p0370), [`org.eclipse.help.ui.internal.views`](packages-13.md#p0371)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0120

### `ua/org.eclipse.help.webapp`

- Source: [ua/org.eclipse.help.webapp](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help.webapp); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help.webapp/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help.webapp/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help.webapp/pom.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.help.webapp`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Declared Maven coordinates: `org.eclipse.platform:org.eclipse.help.webapp:3.12.700-SNAPSHOT`; packaging `eclipse-plugin`; parent `eclipse.platform.ua:eclipse.platform.ua:4.42.0-SNAPSHOT`, relative path `omitted; effective parent convention applies`
- Bundle identity: `org.eclipse.help.webapp`, version `3.12.700.qualifier`, declared name `%help_webapp_plugin_name`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.help.internal.webapp.HelpWebappPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.help.internal.webapp;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.help.internal.webapp.data;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.help.internal.webapp.parser;x-internal:=true`, `org.eclipse.help.internal.webapp.service;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.help.internal.webapp.servlet;x-friends:="org.eclipse.ua.tests,org.eclipse.ua.tests.doc"`, `org.eclipse.help.internal.webapp.utils;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.help.webapp`
- Import-Package: `javax.servlet;version="3.1.0"`, `javax.servlet.http;version="3.1.0"`, `org.osgi.service.http.whiteboard.propertytypes;version="[1.1.0,2.0.0)"`
- Require-Bundle: `org.eclipse.help.base;bundle-version="[4.3.200,5.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.equinox.jsp.jasper.registry;bundle-version="1.0.100"`, `org.eclipse.jdt.core.compiler.batch;bundle-version="[3.33.0,4.0.0)"`
- Packaging/source configuration: [ua/org.eclipse.help.webapp/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.help.webapp/build.properties); observed property starts `source.. = src/`; `bin.includes = plugin.xml,\`; `output.. = bin/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.help.internal.webapp`](packages-12.md#p0350), [`org.eclipse.help.internal.webapp.data`](packages-12.md#p0351), [`org.eclipse.help.internal.webapp.parser`](packages-12.md#p0352), [`org.eclipse.help.internal.webapp.service`](packages-12.md#p0353), [`org.eclipse.help.internal.webapp.servlet`](packages-12.md#p0354), [`org.eclipse.help.internal.webapp.utils`](packages-12.md#p0355), [`org.eclipse.help.webapp`](packages-13.md#p0372)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

