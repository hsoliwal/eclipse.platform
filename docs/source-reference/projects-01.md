# Project/module records 1

> Inspected source revision: `0de5d9acb5cff6b133cb1c55f6844720088c7d92`. Source/manifest observation only; builds and tests were not run.

[Reference home](README.md) · [Selected contracts](CONTRACTS.md) · [Coverage](COVERAGE.md)

## U0001

### `.`

- Source: [.](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/.); implementation/build; activation must be checked below
- Discovery evidence: [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/pom.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Declared Maven coordinates: `org.eclipse.platform:eclipse.platform:4.42.0-SNAPSHOT`; packaging `pom`; parent `org.eclipse:eclipse-platform-parent:4.42.0-SNAPSHOT`, relative path `../eclipse-platform-parent`
- Aggregates: [`update/org.eclipse.update.configurator`](projects-05.md#u0136) (non-profile declaration), [`update/org.eclipse.update.configurator.tests`](projects-05.md#u0137) (non-profile declaration), [`ant/org.eclipse.ant.core`](projects-01.md#u0004) (non-profile declaration), [`ant/org.eclipse.ant.launching`](projects-01.md#u0005) (non-profile declaration), [`ant/org.eclipse.ant.ui`](projects-01.md#u0008) (non-profile declaration), [`ant/org.eclipse.ant.tests.core`](projects-01.md#u0006) (non-profile declaration), [`ant/org.eclipse.ant.tests.ui`](projects-01.md#u0007) (non-profile declaration), [`platform/org.eclipse.platform`](projects-01.md#u0026) (non-profile declaration), [`platform/org.eclipse.sdk`](projects-01.md#u0027) (non-profile declaration), [`runtime`](projects-02.md#u0054) (non-profile declaration), [`team`](projects-03.md#u0081) (non-profile declaration), [`resources`](projects-01.md#u0029) (non-profile declaration), [`debug`](projects-01.md#u0009) (non-profile declaration), [`ua`](projects-04.md#u0113) (non-profile declaration), [`terminal`](projects-04.md#u0100) (non-profile declaration)
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0002

### `.m3/atom-pattern`

- Source: [.m3/atom-pattern](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/.m3/atom-pattern); fork research/tooling overlay
- Discovery evidence: [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/.m3/atom-pattern/pom.xml)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Declared Maven coordinates: `com.synexia.intake:repository-hierarchy-intake:1`; packaging `pom`; parent `none declared`, relative path `omitted; effective parent convention applies`
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0003

### `.m3/openrewrite-recipes`

- Source: [.m3/openrewrite-recipes](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/.m3/openrewrite-recipes); fork research/tooling overlay
- Discovery evidence: [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/.m3/openrewrite-recipes/pom.xml)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Declared Maven coordinates: `com.synexia.m3.bootstrap:m3-recipe-first-bootstrap:1.0.0-SNAPSHOT`; packaging `jar (Maven default; effective model not evaluated)`; parent `none declared`, relative path `omitted; effective parent convention applies`
- Owns declared Java namespaces: [`com.synexia.m3.bootstrap`](packages-01.md#p0002)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0004

### `ant/org.eclipse.ant.core`

- Source: [ant/org.eclipse.ant.core](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.core); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.core/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.core/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ant.core`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.ant.core`, version `3.7.1100.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.ant.core.AntCorePlugin`
- Activation policy: `lazy;exclude:="org.eclipse.ant.internal.core.contentDescriber"`
- Export-Package: `org.eclipse.ant.core; uses:="org.eclipse.ant.internal.core,  org.eclipse.core.runtime,  org.eclipse.equinox.app,  org.osgi.framework"`, `org.eclipse.ant.internal.core; x-friends:="org.eclipse.ant.ui,  org.eclipse.ant.launching,  org.eclipse.ant.tests.ui,  org.eclipse.ant.tests.core"`, `org.eclipse.ant.internal.core.ant;x-friends:="org.eclipse.ant.launching"`, `org.eclipse.ant.internal.core.contentDescriber;x-internal:=true`
- Require-Bundle: `org.eclipse.core.variables;bundle-version="[3.1.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`
- Packaging/source configuration: [ant/org.eclipse.ant.core/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.core/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `output.lib/antsupportlib.jar = src_ant_bin/`; `source.lib/antsupportlib.jar = src_ant/`; `bin.includes = plugin.xml,\`; `pom.model.property.defaultSigning-excludeInnerJars = true`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.ant.core`](packages-01.md#p0004), [`org.eclipse.ant.internal.core`](packages-01.md#p0005), [`org.eclipse.ant.internal.core.ant`](packages-01.md#p0006), [`org.eclipse.ant.internal.core.contentDescriber`](packages-01.md#p0007)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0005

### `ant/org.eclipse.ant.launching`

- Source: [ant/org.eclipse.ant.launching](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.launching); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.launching/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.launching/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ant.launching`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.ant.launching`, version `1.4.1200.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.ant.internal.launching.AntLaunching`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.ant.internal.launching;x-friends:="org.eclipse.ant.ui,org.eclipse.ant.tests.ui"`, `org.eclipse.ant.internal.launching.debug;x-friends:="org.eclipse.ant.ui,org.eclipse.ant.tests.ui"`, `org.eclipse.ant.internal.launching.debug.model;x-friends:="org.eclipse.ant.ui,org.eclipse.ant.tests.ui"`, `org.eclipse.ant.internal.launching.launchConfigurations;x-friends:="org.eclipse.ant.ui,org.eclipse.ant.tests.ui"`, `org.eclipse.ant.internal.launching.remote;x-internal:=true`, `org.eclipse.ant.internal.launching.remote.inputhandler;x-internal:=true`, `org.eclipse.ant.internal.launching.remote.logger;x-internal:=true`, `org.eclipse.ant.internal.launching.runtime.logger;x-friends:="org.eclipse.ant.ui"`, `org.eclipse.ant.launching`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.debug.core;bundle-version="[3.12.0,4.0.0)"`, `org.eclipse.jdt.launching;bundle-version="[3.12.0,4.0.0)"`, `org.eclipse.core.variables;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ant.core;bundle-version="[3.2.0,4.0.0)"`, `org.apache.ant;bundle-version="1.9.4"`, `org.eclipse.core.externaltools;bundle-version="[1.0.0,2.0.0)"`, `org.eclipse.text;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.jdt.core;bundle-version="[3.36.0,4.0.0)"`, `org.eclipse.core.resources;bundle-version="[3.5.0,4.0.0)"`
- Packaging/source configuration: [ant/org.eclipse.ant.launching/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.launching/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`; `source.lib/loggers.jar = loggers/`; `output.lib/loggers.jar = loggers_bin/`; `pom.model.property.defaultSigning-excludeInnerJars = true`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.ant.internal.launching`](packages-01.md#p0008), [`org.eclipse.ant.internal.launching.debug`](packages-01.md#p0009), [`org.eclipse.ant.internal.launching.debug.model`](packages-01.md#p0010), [`org.eclipse.ant.internal.launching.launchConfigurations`](packages-01.md#p0011), [`org.eclipse.ant.internal.launching.remote`](packages-01.md#p0012), [`org.eclipse.ant.internal.launching.remote.inputhandler`](packages-01.md#p0013), [`org.eclipse.ant.internal.launching.remote.logger`](packages-01.md#p0014), [`org.eclipse.ant.internal.launching.runtime.logger`](packages-01.md#p0015), [`org.eclipse.ant.launching`](packages-02.md#p0039)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0006

### `ant/org.eclipse.ant.tests.core`

- Source: [ant/org.eclipse.ant.tests.core](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.tests.core); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.tests.core/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.tests.core/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ant.tests.core`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.ant.tests.core`, version `3.8.100.qualifier`, declared name `%pluginName`; execution environment `JavaSE-25`
- Lifecycle activator: `org.eclipse.ant.tests.core.testplugin.AntTestPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.ant.tests.core`, `org.eclipse.ant.tests.core.testplugin`, `org.eclipse.ant.tests.core.tests`
- Import-Package: `org.assertj.core.api`, `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.function;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`
- Require-Bundle: `org.eclipse.ui.ide;resolution:=optional`, `org.apache.ant;bundle-version="1.9.4"`, `org.eclipse.core.resources`, `org.eclipse.ui;bundle-version="3.208.0"`, `org.eclipse.ant.core`, `org.eclipse.core.runtime`
- Packaging/source configuration: [ant/org.eclipse.ant.tests.core/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.tests.core/build.properties); observed property starts `bin.includes = plugin.xml,\`; `source.anttestscore.jar = test plugin/,\`; `source.lib/antTestsSupport.jar = test support/`; `output.lib/antTestsSupport.jar = test_support_bin/`; `output.anttestscore.jar = bin/`; `pom.model.packaging = eclipse-test-plugin`; `pom.model.property.testClass = org.eclipse.ant.tests.core.AutomatedAntSuite`; `pom.model.property.tycho.surefire.testClassesDirectory = target/anttestscore.jar-classes`; `pom.model.property.tycho.surefire.useUIHarness = true`; `pom.model.property.tycho.surefire.useUIThread = true`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.ant.tests.core`](packages-02.md#p0040), [`org.eclipse.ant.tests.core.support.inputHandlers`](packages-02.md#p0041), [`org.eclipse.ant.tests.core.support.tasks`](packages-02.md#p0042), [`org.eclipse.ant.tests.core.support.testloggers`](packages-02.md#p0043), [`org.eclipse.ant.tests.core.support.types`](packages-02.md#p0044), [`org.eclipse.ant.tests.core.testplugin`](packages-02.md#p0045), [`org.eclipse.ant.tests.core.tests`](packages-02.md#p0046)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0007

### `ant/org.eclipse.ant.tests.ui`

- Source: [ant/org.eclipse.ant.tests.ui](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.tests.ui); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.tests.ui/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.tests.ui/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ant.tests.ui`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.ant.tests.ui`, version `3.12.500.qualifier`, declared name `%pluginName`; execution environment `JavaSE-25`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.ant.tests.ui`, `org.eclipse.ant.tests.ui.debug`, `org.eclipse.ant.tests.ui.dtd`, `org.eclipse.ant.tests.ui.editor`, `org.eclipse.ant.tests.ui.editor.formatter`, `org.eclipse.ant.tests.ui.editor.performance`, `org.eclipse.ant.tests.ui.editor.support`, `org.eclipse.ant.tests.ui.externaltools`, `org.eclipse.ant.tests.ui.performance`, `org.eclipse.ant.tests.ui.separateVM`, `org.eclipse.ant.tests.ui.testplugin`
- Import-Package: `org.assertj.core.api`, `org.assertj.core.api.iterable`, `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`, `org.junit.jupiter.api.extension;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.function;version="[5.14.0,6.0.0)"`
- Require-Bundle: `org.eclipse.ui.ide;resolution:=optional`, `org.eclipse.jface.text;resolution:=optional`, `org.eclipse.ui.workbench.texteditor;resolution:=optional`, `org.eclipse.ui.editors;resolution:=optional`, `org.apache.ant;bundle-version="1.9.2"`, `org.eclipse.core.resources`, `org.eclipse.jdt.core`, `org.eclipse.jdt.launching`, `org.eclipse.ui`, `org.eclipse.ant.core`, `org.eclipse.debug.core;bundle-version="[3.12.0,4.0.0)"`, `org.eclipse.debug.ui`, `org.eclipse.ui.externaltools`, `org.eclipse.ant.ui`, `org.eclipse.core.runtime`, `org.eclipse.test.performance`, `org.eclipse.ui.console`, `org.eclipse.ant.launching`, `org.eclipse.core.externaltools`
- Packaging/source configuration: [ant/org.eclipse.ant.tests.ui/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.tests.ui/build.properties); observed property starts `bin.includes = plugin.xml,\`; `source.anttestsui.jar = test plugin/,\`; `source.lib/antUITestsSupport.jar = test support/`; `output.lib/antUITestsSupport.jar = test_support_bin/`; `output.anttestsui.jar = bin/`; `pom.model.packaging = eclipse-test-plugin`; `pom.model.property.testClass = org.eclipse.ant.tests.ui.testplugin.AntUITests`; `pom.model.property.tycho.surefire.testClassesDirectory = target/anttestsui.jar-classes`; `pom.model.property.tycho.surefire.useUIHarness = true`; `pom.model.property.tycho.surefire.useUIThread = true`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.ant.tests.ui`](packages-02.md#p0047), [`org.eclipse.ant.tests.ui.debug`](packages-02.md#p0048), [`org.eclipse.ant.tests.ui.dtd`](packages-02.md#p0049), [`org.eclipse.ant.tests.ui.editor`](packages-02.md#p0050), [`org.eclipse.ant.tests.ui.editor.formatter`](packages-02.md#p0051), [`org.eclipse.ant.tests.ui.editor.performance`](packages-02.md#p0052), [`org.eclipse.ant.tests.ui.editor.support`](packages-02.md#p0053), [`org.eclipse.ant.tests.ui.externaltools`](packages-02.md#p0054), [`org.eclipse.ant.tests.ui.performance`](packages-02.md#p0055), [`org.eclipse.ant.tests.ui.separateVM`](packages-02.md#p0056), [`org.eclipse.ant.tests.ui.support.inputHandlers`](packages-02.md#p0057), [`org.eclipse.ant.tests.ui.support.tasks`](packages-02.md#p0058), [`org.eclipse.ant.tests.ui.support.types`](packages-02.md#p0059), [`org.eclipse.ant.tests.ui.testplugin`](packages-02.md#p0060)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0008

### `ant/org.eclipse.ant.ui`

- Source: [ant/org.eclipse.ant.ui](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.ui); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.ui/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.ui/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ant.ui`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.ant.ui`, version `3.10.700.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.ant.internal.ui.AntUIPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.ant.internal.ui;x-internal:=true`, `org.eclipse.ant.internal.ui.console;x-internal:=true`, `org.eclipse.ant.internal.ui.datatransfer;x-internal:=true`, `org.eclipse.ant.internal.ui.debug.model;x-internal:=true`, `org.eclipse.ant.internal.ui.dtd;x-internal:=true`, `org.eclipse.ant.internal.ui.dtd.schema;x-internal:=true`, `org.eclipse.ant.internal.ui.dtd.util;x-internal:=true`, `org.eclipse.ant.internal.ui.editor;x-internal:=true`, `org.eclipse.ant.internal.ui.editor.actions;x-internal:=true`, `org.eclipse.ant.internal.ui.editor.formatter;x-internal:=true`, `org.eclipse.ant.internal.ui.editor.outline;x-internal:=true`, `org.eclipse.ant.internal.ui.editor.templates;x-internal:=true`, `org.eclipse.ant.internal.ui.editor.text;x-internal:=true`, `org.eclipse.ant.internal.ui.editor.utils;x-internal:=true`, `org.eclipse.ant.internal.ui.launchConfigurations;x-internal:=true`, `org.eclipse.ant.internal.ui.model;x-friends:="org.eclipse.ant.tests.ui"`, `org.eclipse.ant.internal.ui.preferences;x-internal:=true`, `org.eclipse.ant.internal.ui.refactoring;x-internal:=true`, `org.eclipse.ant.internal.ui.views;x-internal:=true`, `org.eclipse.ant.internal.ui.views.actions;x-internal:=true`, `org.eclipse.ant.ui.launching`
- Require-Bundle: `org.eclipse.ui.ide;bundle-version="[3.2.0,4.0.0)";resolution:=optional`, `org.eclipse.ui.views;bundle-version="[3.2.0,4.0.0)";resolution:=optional`, `org.eclipse.jface.text;bundle-version="[3.5.0,4.0.0)";resolution:=optional`, `org.eclipse.ui.workbench.texteditor;bundle-version="[3.5.0,4.0.0)";resolution:=optional`, `org.eclipse.ui.editors;bundle-version="[3.23.0,4.0.0)";resolution:=optional`, `org.apache.ant;bundle-version="1.9.4"`, `org.eclipse.ant.core;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.core.filesystem;bundle-version="[1.0.0,2.0.0)"`, `org.eclipse.core.resources;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.core.variables;bundle-version="[3.1.0,4.0.0)"`, `org.eclipse.ui.externaltools;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.console;bundle-version="[3.1.0,4.0.0)"`, `org.eclipse.debug.core;bundle-version="[3.12.0,4.0.0)"`, `org.eclipse.debug.ui;bundle-version="[3.13.0,4.0.0)"`, `org.eclipse.jdt.ui;bundle-version="[3.15.0,4.0.0)"`, `org.eclipse.jdt.debug.ui;bundle-version="[3.9.0,4.0.0)"`, `org.eclipse.jdt.launching;bundle-version="[3.11.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.jdt.core;bundle-version="[3.36.0,4.0.0)"`, `org.eclipse.team.core;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ltk.core.refactoring;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.core.expressions;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.jdt.junit;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.ant.launching;bundle-version="[1.0.0,2.0.0)"`, `org.eclipse.core.externaltools;bundle-version="[1.0.0,2.0.0)"`
- Packaging/source configuration: [ant/org.eclipse.ant.ui/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ant/org.eclipse.ant.ui/build.properties); observed property starts `source.. = Ant Tools Support/,\`; `output.. = bin/`; `source.lib/antrunner.jar = Ant Runner Support/`; `output.lib/antrunner.jar = ant_runner_support_bin/`; `bin.includes = icons/,\`; `pom.model.property.defaultSigning-excludeInnerJars = true`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.ant.internal.ui`](packages-01.md#p0016), [`org.eclipse.ant.internal.ui.antsupport`](packages-01.md#p0017), [`org.eclipse.ant.internal.ui.antsupport.inputhandler`](packages-01.md#p0018), [`org.eclipse.ant.internal.ui.console`](packages-01.md#p0019), [`org.eclipse.ant.internal.ui.datatransfer`](packages-01.md#p0020), [`org.eclipse.ant.internal.ui.debug.model`](packages-01.md#p0021), [`org.eclipse.ant.internal.ui.dtd`](packages-01.md#p0022), [`org.eclipse.ant.internal.ui.dtd.schema`](packages-01.md#p0023), [`org.eclipse.ant.internal.ui.dtd.util`](packages-01.md#p0024), [`org.eclipse.ant.internal.ui.editor`](packages-01.md#p0025), [`org.eclipse.ant.internal.ui.editor.actions`](packages-01.md#p0026), [`org.eclipse.ant.internal.ui.editor.formatter`](packages-01.md#p0027), [`org.eclipse.ant.internal.ui.editor.outline`](packages-01.md#p0028), [`org.eclipse.ant.internal.ui.editor.templates`](packages-01.md#p0029), [`org.eclipse.ant.internal.ui.editor.text`](packages-01.md#p0030), [`org.eclipse.ant.internal.ui.editor.tools`](packages-02.md#p0031), [`org.eclipse.ant.internal.ui.editor.utils`](packages-02.md#p0032), [`org.eclipse.ant.internal.ui.launchConfigurations`](packages-02.md#p0033), [`org.eclipse.ant.internal.ui.model`](packages-02.md#p0034), [`org.eclipse.ant.internal.ui.preferences`](packages-02.md#p0035), [`org.eclipse.ant.internal.ui.refactoring`](packages-02.md#p0036), [`org.eclipse.ant.internal.ui.views`](packages-02.md#p0037), [`org.eclipse.ant.internal.ui.views.actions`](packages-02.md#p0038), [`org.eclipse.ant.ui.launching`](packages-03.md#p0061)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0009

### `debug`

- Source: [debug](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug); implementation/build; activation must be checked below
- Discovery evidence: [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/pom.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Declared Maven coordinates: `org.eclipse.platform:eclipse.platform.debug:4.42.0-SNAPSHOT`; packaging `pom`; parent `org.eclipse.platform:eclipse.platform:4.42.0-SNAPSHOT`, relative path `omitted; effective parent convention applies`
- Aggregates: [`debug/org.eclipse.core.externaltools`](projects-01.md#u0010) (non-profile declaration), [`debug/org.eclipse.core.variables`](projects-01.md#u0011) (non-profile declaration), [`debug/org.eclipse.debug.core`](projects-01.md#u0012) (non-profile declaration), [`debug/org.eclipse.debug.examples.core`](projects-01.md#u0013) (non-profile declaration), [`debug/org.eclipse.debug.examples.memory`](projects-01.md#u0014) (non-profile declaration), [`debug/org.eclipse.debug.examples.mixedmode`](projects-01.md#u0015) (non-profile declaration), [`debug/org.eclipse.debug.examples.ui`](projects-01.md#u0016) (non-profile declaration), [`debug/org.eclipse.debug.tests`](projects-01.md#u0018) (non-profile declaration), [`debug/org.eclipse.debug.ui`](projects-01.md#u0019) (non-profile declaration), [`debug/org.eclipse.debug.ui.launchview`](projects-01.md#u0020) (non-profile declaration), [`debug/org.eclipse.debug.ui.launchview.tests`](projects-01.md#u0021) (non-profile declaration), [`debug/org.eclipse.ui.console`](projects-01.md#u0022) (non-profile declaration), [`debug/org.eclipse.ui.externaltools`](projects-01.md#u0023) (non-profile declaration), [`debug/org.eclipse.unittest.ui`](projects-01.md#u0024) (non-profile declaration), [`debug/org.eclipse.debug.terminal`](projects-01.md#u0017) (non-profile declaration)
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0010

### `debug/org.eclipse.core.externaltools`

- Source: [debug/org.eclipse.core.externaltools](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.core.externaltools); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.core.externaltools/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.core.externaltools/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.core.externaltools`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.core.externaltools`, version `1.3.600.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.externaltools.internal;x-friends:="org.eclipse.ant.launching,org.eclipse.ui.externaltools,org.eclipse.ant.ui"`, `org.eclipse.core.externaltools.internal.launchConfigurations;x-friends:="org.eclipse.ant.launching,org.eclipse.ui.externaltools"`, `org.eclipse.core.externaltools.internal.model;x-friends:="org.eclipse.ant.launching,org.eclipse.ui.externaltools,org.eclipse.ant.ui"`, `org.eclipse.core.externaltools.internal.registry;x-friends:="org.eclipse.ui.externaltools"`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.debug.core;bundle-version="[3.9.0,4.0.0)"`, `org.eclipse.core.variables;bundle-version="[3.2.800,4.0.0)"`
- Packaging/source configuration: [debug/org.eclipse.core.externaltools/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.core.externaltools/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.externaltools.internal`](packages-03.md#p0081), [`org.eclipse.core.externaltools.internal.launchConfigurations`](packages-03.md#p0082), [`org.eclipse.core.externaltools.internal.model`](packages-03.md#p0083), [`org.eclipse.core.externaltools.internal.registry`](packages-03.md#p0084)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0011

### `debug/org.eclipse.core.variables`

- Source: [debug/org.eclipse.core.variables](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.core.variables); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.core.variables/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.core.variables/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.core.variables`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.core.variables`, version `3.6.900.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.core.variables.VariablesPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.internal.variables;x-internal:=true`, `org.eclipse.core.variables;uses:="org.eclipse.core.runtime"`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`
- Packaging/source configuration: [debug/org.eclipse.core.variables/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.core.variables/build.properties); observed property starts `bin.includes = plugin.xml,\`; `source.. = src/`; `output.. = bin/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.internal.variables`](packages-04.md#p0116), [`org.eclipse.core.variables`](packages-07.md#p0182)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0012

### `debug/org.eclipse.debug.core`

- Source: [debug/org.eclipse.debug.core](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.core); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.core/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.core/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.debug.core`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.debug.core`, version `3.24.100.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.debug.core.DebugPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.debug.core; uses:="org.eclipse.core.resources,  org.eclipse.core.runtime,  org.eclipse.debug.core.model,  org.eclipse.debug.core.sourcelookup,  org.osgi.framework,  org.w3c.dom"`, `org.eclipse.debug.core.commands;uses:="org.eclipse.core.runtime,org.eclipse.core.runtime.jobs,org.eclipse.debug.core"`, `org.eclipse.debug.core.model; uses:="org.eclipse.core.resources,  org.eclipse.core.runtime,  org.eclipse.core.runtime.jobs,  org.eclipse.debug.core"`, `org.eclipse.debug.core.sourcelookup;uses:="org.eclipse.core.runtime,org.eclipse.debug.core,org.eclipse.debug.core.model"`, `org.eclipse.debug.core.sourcelookup.containers; uses:="org.eclipse.core.resources,  org.eclipse.core.runtime,  org.eclipse.debug.core,  org.eclipse.debug.core.sourcelookup,  org.w3c.dom"`, `org.eclipse.debug.internal.core; x-friends:="org.eclipse.debug.ui,  org.eclipse.debug.tests,  org.eclipse.debug.examples.mixedmode,  org.eclipse.jdt.launching,  org.eclipse.ui.console"`, `org.eclipse.debug.internal.core.commands;x-friends:="org.eclipse.debug.ui"`, `org.eclipse.debug.internal.core.groups;x-friends:="org.eclipse.debug.ui"`, `org.eclipse.debug.internal.core.groups.observer;x-internal:=true`, `org.eclipse.debug.internal.core.sourcelookup;x-friends:="org.eclipse.debug.ui"`, `org.eclipse.debug.internal.core.sourcelookup.containers;x-friends:="org.eclipse.debug.ui"`, `org.eclipse.debug.internal.core.variables;x-friends:="org.eclipse.debug.ui,org.eclipse.jdt.debug.ui"`
- Require-Bundle: `org.eclipse.core.resources;bundle-version="[3.18.0,4.0.0)";visibility:=reexport`, `org.eclipse.core.variables;bundle-version="[3.2.800,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.core.filesystem;bundle-version="[1.11.0,2.0.0)"`, `org.eclipse.core.expressions;bundle-version="[3.4.0,4.0.0)"`
- Packaging/source configuration: [debug/org.eclipse.debug.core/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.core/build.properties); observed property starts `bin.includes = .options,\`; `source.. = core/`; `output.. = bin/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.debug.core`](packages-07.md#p0183), [`org.eclipse.debug.core.commands`](packages-07.md#p0184), [`org.eclipse.debug.core.model`](packages-07.md#p0185), [`org.eclipse.debug.core.sourcelookup`](packages-07.md#p0186), [`org.eclipse.debug.core.sourcelookup.containers`](packages-07.md#p0187), [`org.eclipse.debug.internal.core`](packages-07.md#p0210), [`org.eclipse.debug.internal.core.commands`](packages-08.md#p0211), [`org.eclipse.debug.internal.core.groups`](packages-08.md#p0212), [`org.eclipse.debug.internal.core.groups.observer`](packages-08.md#p0213), [`org.eclipse.debug.internal.core.sourcelookup`](packages-08.md#p0214), [`org.eclipse.debug.internal.core.sourcelookup.containers`](packages-08.md#p0215), [`org.eclipse.debug.internal.core.variables`](packages-08.md#p0216)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0013

### `debug/org.eclipse.debug.examples.core`

- Source: [debug/org.eclipse.debug.examples.core](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.core); example/template (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.core/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.core/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.debug.examples.core`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.debug.examples.core`, version `1.8.0.qualifier`, declared name `%Bundle-Name`; execution environment `JavaSE-25`
- Lifecycle activator: `org.eclipse.debug.examples.core.pda.DebugCorePlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.debug.examples.core.midi.launcher;x-friends:="org.eclipse.debug.examples.ui"`, `org.eclipse.debug.examples.core.pda;x-friends:="org.eclipse.debug.examples.ui"`, `org.eclipse.debug.examples.core.pda.breakpoints;x-friends:="org.eclipse.debug.examples.ui"`, `org.eclipse.debug.examples.core.pda.launcher;x-friends:="org.eclipse.debug.examples.ui"`, `org.eclipse.debug.examples.core.pda.model;x-friends:="org.eclipse.debug.examples.ui"`, `org.eclipse.debug.examples.core.pda.protocol;x-friends:="org.eclipse.debug.examples.ui"`, `org.eclipse.debug.examples.core.pda.sourcelookup;x-friends:="org.eclipse.debug.examples.ui"`, `org.eclipse.debug.examples.pdavm;x-friends:="org.eclipse.debug.examples.ui"`
- Require-Bundle: `org.eclipse.core.runtime`, `org.eclipse.debug.core;bundle-version="3.9.0"`
- Packaging/source configuration: [debug/org.eclipse.debug.examples.core/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.core/build.properties); observed property starts `source.. = src/,\`; `output.. = bin/`; `bin.includes = plugin.xml,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.debug.examples.core.midi.launcher`](packages-07.md#p0188), [`org.eclipse.debug.examples.core.pda`](packages-07.md#p0189), [`org.eclipse.debug.examples.core.pda.breakpoints`](packages-07.md#p0190), [`org.eclipse.debug.examples.core.pda.launcher`](packages-07.md#p0191), [`org.eclipse.debug.examples.core.pda.model`](packages-07.md#p0192), [`org.eclipse.debug.examples.core.pda.protocol`](packages-07.md#p0193), [`org.eclipse.debug.examples.core.pda.sourcelookup`](packages-07.md#p0194), [`org.eclipse.debug.examples.pdavm`](packages-07.md#p0199)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0014

### `debug/org.eclipse.debug.examples.memory`

- Source: [debug/org.eclipse.debug.examples.memory](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.memory); example/template (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.memory/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.memory/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.debug.examples.memory`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.debug.examples.memory`, version `1.105.0.qualifier`, declared name `%Bundle-Name`; execution environment `JavaSE-25`
- Lifecycle activator: `org.eclipse.debug.examples.internal.memory.MemoryViewSamplePlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.debug.examples.internal.memory;x-internal:=true`, `org.eclipse.debug.examples.internal.memory.core;x-internal:=true`, `org.eclipse.debug.examples.internal.memory.engine;x-internal:=true`, `org.eclipse.debug.examples.internal.memory.launchconfig;x-internal:=true`
- Require-Bundle: `org.eclipse.ui`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.debug.ui;bundle-version="3.10.0"`
- Packaging/source configuration: [debug/org.eclipse.debug.examples.memory/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.memory/build.properties); observed property starts `bin.includes = .classpath,\`; `output.. = bin/`; `source.. = src/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.debug.examples.internal.memory`](packages-07.md#p0195), [`org.eclipse.debug.examples.internal.memory.core`](packages-07.md#p0196), [`org.eclipse.debug.examples.internal.memory.engine`](packages-07.md#p0197), [`org.eclipse.debug.examples.internal.memory.launchconfig`](packages-07.md#p0198)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0015

### `debug/org.eclipse.debug.examples.mixedmode`

- Source: [debug/org.eclipse.debug.examples.mixedmode](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.mixedmode); example/template (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.mixedmode/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.mixedmode/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.debug.examples.mixedmode`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.debug.examples.mixedmode`, version `1.5.0.qualifier`, declared name `%Bundle-Name`; execution environment `JavaSE-25`
- Lifecycle activator: `org.eclipse.debug.internal.examples.mixedmode.Activator`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.debug.internal.examples.mixedmode;x-internal:=true`
- Require-Bundle: `org.eclipse.ui;bundle-version="3.208.0"`, `org.eclipse.core.runtime`, `org.eclipse.debug.ui;bundle-version="3.10.0"`
- Packaging/source configuration: [debug/org.eclipse.debug.examples.mixedmode/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.mixedmode/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.debug.internal.examples.mixedmode`](packages-08.md#p0217)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0016

### `debug/org.eclipse.debug.examples.ui`

- Source: [debug/org.eclipse.debug.examples.ui](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.ui); example/template (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.ui/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.ui/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.debug.examples.ui`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.debug.examples.ui`, version `1.10.0.qualifier`, declared name `%Bundle-Name`; execution environment `JavaSE-25`
- Lifecycle activator: `org.eclipse.debug.examples.ui.pda.DebugUIPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.debug.examples.ui.midi.adapters;x-internal:=true`, `org.eclipse.debug.examples.ui.midi.detailpanes;x-internal:=true`, `org.eclipse.debug.examples.ui.midi.launcher;x-internal:=true`, `org.eclipse.debug.examples.ui.pda;x-internal:=true`, `org.eclipse.debug.examples.ui.pda.adapters;x-internal:=true`, `org.eclipse.debug.examples.ui.pda.breakpoints;x-internal:=true`, `org.eclipse.debug.examples.ui.pda.editor;x-internal:=true`, `org.eclipse.debug.examples.ui.pda.launcher;x-internal:=true`, `org.eclipse.debug.examples.ui.pda.presentation;x-internal:=true`, `org.eclipse.debug.examples.ui.pda.views;x-internal:=true`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.ui`, `org.eclipse.debug.ui;bundle-version="3.10.0"`, `org.eclipse.jface.text`, `org.eclipse.ui.editors`, `org.eclipse.ui.workbench.texteditor`, `org.eclipse.ui.ide`, `org.eclipse.debug.examples.core;bundle-version="1.3.300"`, `org.eclipse.core.expressions`
- Packaging/source configuration: [debug/org.eclipse.debug.examples.ui/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.examples.ui/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = plugin.xml,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.debug.examples.ui.midi.adapters`](packages-07.md#p0200), [`org.eclipse.debug.examples.ui.midi.detailpanes`](packages-07.md#p0201), [`org.eclipse.debug.examples.ui.midi.launcher`](packages-07.md#p0202), [`org.eclipse.debug.examples.ui.pda`](packages-07.md#p0203), [`org.eclipse.debug.examples.ui.pda.adapters`](packages-07.md#p0204), [`org.eclipse.debug.examples.ui.pda.breakpoints`](packages-07.md#p0205), [`org.eclipse.debug.examples.ui.pda.editor`](packages-07.md#p0206), [`org.eclipse.debug.examples.ui.pda.launcher`](packages-07.md#p0207), [`org.eclipse.debug.examples.ui.pda.presentation`](packages-07.md#p0208), [`org.eclipse.debug.examples.ui.pda.views`](packages-07.md#p0209)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0017

### `debug/org.eclipse.debug.terminal`

- Source: [debug/org.eclipse.debug.terminal](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.terminal); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.terminal/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.terminal/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.debug.terminal`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.debug.terminal`, version `1.0.300.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Activation policy: `lazy`
- Import-Package: `org.eclipse.terminal.connector;version="[1.0.0,2.0.0)"`, `org.eclipse.terminal.control;version="[1.0.0,2.0.0)"`
- Require-Bundle: `org.eclipse.core.runtime`, `org.eclipse.debug.core;bundle-version="3.23.0"`, `org.eclipse.cdt.core.native;bundle-version="[6.4.0,7.0.0)"`, `org.eclipse.ui;bundle-version="3.207.100"`, `org.eclipse.ui.console`
- Packaging/source configuration: [debug/org.eclipse.debug.terminal/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.terminal/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.debug.terminal`](packages-09.md#p0262), [`org.eclipse.debug.terminal.ui`](packages-09.md#p0263)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0018

### `debug/org.eclipse.debug.tests`

- Source: [debug/org.eclipse.debug.tests](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.tests); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.tests/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.tests/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.debug.tests`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.debug.tests`, version `3.15.400.qualifier`, declared name `%pluginName`; execution environment `JavaSE-25`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.debug.tests`, `org.eclipse.debug.tests.breakpoint`, `org.eclipse.debug.tests.console`, `org.eclipse.debug.tests.expressions`, `org.eclipse.debug.tests.launching`, `org.eclipse.debug.tests.logicalstructure`, `org.eclipse.debug.tests.sourcelookup`, `org.eclipse.debug.tests.statushandlers`, `org.eclipse.debug.tests.stepfilters`, `org.eclipse.debug.tests.ui`, `org.eclipse.debug.tests.view.memory`, `org.eclipse.debug.tests.viewer.model`
- Import-Package: `org.assertj.core.api;version="3.24.2"`, `org.assertj.core.api.iterable`, `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.extension;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.function;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.io;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.params;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.params.provider;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`, `org.opentest4j;version="[1.3.0,2.0.0)"`
- Require-Bundle: `org.eclipse.ui;bundle-version="[3.208.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.34.0,4.0.0)"`, `org.eclipse.debug.ui;bundle-version="[3.21.0,4.0.0)"`, `org.eclipse.core.filesystem;bundle-version="[1.11.0,2.0.0)"`, `org.eclipse.test.performance;bundle-version="[3.21.0,4.0.0)"`, `org.eclipse.ui.externaltools;bundle-version="[3.7.0,4.0.0)"`, `org.eclipse.ui.console;bundle-version="[3.17.0,4.0.0)"`, `org.eclipse.jface.text;bundle-version="[3.30.0,4.0.0)"`, `org.eclipse.ui.workbench.texteditor;bundle-version="[3.20.0,4.0.0)"`
- Packaging/source configuration: [debug/org.eclipse.debug.tests/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.tests/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`; `pom.model.property.testClass = org.eclipse.debug.tests.AutomatedSuite`; `pom.model.property.tycho.surefire.useUIHarness = true`; `pom.model.property.tycho.surefire.useUIThread = true`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.debug.tests`](packages-09.md#p0264), [`org.eclipse.debug.tests.breakpoint`](packages-09.md#p0265), [`org.eclipse.debug.tests.console`](packages-09.md#p0266), [`org.eclipse.debug.tests.expressions`](packages-09.md#p0267), [`org.eclipse.debug.tests.launching`](packages-09.md#p0268), [`org.eclipse.debug.tests.logicalstructure`](packages-09.md#p0269), [`org.eclipse.debug.tests.sourcelookup`](packages-09.md#p0270), [`org.eclipse.debug.tests.statushandlers`](packages-10.md#p0271), [`org.eclipse.debug.tests.stepfilters`](packages-10.md#p0272), [`org.eclipse.debug.tests.ui`](packages-10.md#p0273), [`org.eclipse.debug.tests.view.memory`](packages-10.md#p0274), [`org.eclipse.debug.tests.viewer.model`](packages-10.md#p0275)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0019

### `debug/org.eclipse.debug.ui`

- Source: [debug/org.eclipse.debug.ui](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.ui); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.ui/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.ui/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.debug.ui`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.debug.ui`, version `3.22.100.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.debug.internal.ui.DebugUIPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.debug.internal.ui; x-friends:="org.eclipse.jdt.debug.ui,  org.eclipse.wst.jsdt.debug.ui,  org.eclipse.jdt.debug.tests,  org.eclipse.debug.tests,  org.eclipse.debug.examples.ui,  org.eclipse.debug.examples.mixedmode,  org.eclipse.debug.ui.launchview"`, `org.eclipse.debug.internal.ui.actions;x-internal:=true`, `org.eclipse.debug.internal.ui.actions.breakpointGroups;x-internal:=true`, `org.eclipse.debug.internal.ui.actions.breakpointSortBy;x-internal:=true`, `org.eclipse.debug.internal.ui.actions.breakpoints;x-friends:="org.eclipse.jdt.debug.ui"`, `org.eclipse.debug.internal.ui.actions.expressions;x-internal:=true`, `org.eclipse.debug.internal.ui.actions.variables;x-internal:=true`, `org.eclipse.debug.internal.ui.actions.variables.details;x-internal:=true`, `org.eclipse.debug.internal.ui.breakpoints.provisional; x-friends:="org.eclipse.debug.examples.ui,  org.eclipse.jdt.debug.ui,  org.eclipse.cdt.dsf.ui,  org.eclipse.cdt.dsf.gdb.ui,  org.eclipse.debug.tests"`, `org.eclipse.debug.internal.ui.commands.actions;x-internal:=true`, `org.eclipse.debug.internal.ui.contextlaunching;x-internal:=true`, `org.eclipse.debug.internal.ui.contexts;x-internal:=true`, `org.eclipse.debug.internal.ui.elements.adapters;x-friends:="org.eclipse.jdt.debug.ui"`, `org.eclipse.debug.internal.ui.groups;x-friends:="org.eclipse.debug.ui"`, `org.eclipse.debug.internal.ui.hover;x-internal:=true`, `org.eclipse.debug.internal.ui.importexport.breakpoints;x-internal:=true`, `org.eclipse.debug.internal.ui.importexport.launchconfigurations;x-internal:=true`, `org.eclipse.debug.internal.ui.launchConfigurations;x-friends:="org.eclipse.debug.tests"`, `org.eclipse.debug.internal.ui.memory;x-internal:=true`, `org.eclipse.debug.internal.ui.memory.provisional;x-internal:=true`, `org.eclipse.debug.internal.ui.model.elements;x-friends:="org.eclipse.debug.examples.ui,org.eclipse.jdt.debug.ui,org.eclipse.wst.jsdt.debug.ui"`, `org.eclipse.debug.internal.ui.preferences;x-internal:=true`, `org.eclipse.debug.internal.ui.quickaccess;x-internal:=true`, `org.eclipse.debug.internal.ui.sourcelookup;x-friends:="org.eclipse.debug.tests,org.eclipse.jdt.debug.ui"`, `org.eclipse.debug.internal.ui.sourcelookup.browsers;x-internal:=true`, `org.eclipse.debug.internal.ui.stringsubstitution;x-friends:="org.eclipse.jdt.debug.ui"`, `org.eclipse.debug.internal.ui.viewers;x-friends:="org.eclipse.debug.examples.ui"`, `org.eclipse.debug.internal.ui.viewers.breadcrumb;x-internal:=true`, `org.eclipse.debug.internal.ui.viewers.model;x-friends:="org.eclipse.debug.tests,org.eclipse.jdt.debug.tests,org.eclipse.debug.examples.ui"`, `org.eclipse.debug.internal.ui.viewers.model.provisional; x-friends:="org.eclipse.cdt.dsf.gdb.ui,  org.eclipse.debug.internal.ui.views.memory.renderings;x-internal:=true,  org.eclipse.cdt.dsf.ui,  org.eclipse.debug.examples.ui,  org.eclipse.debug.tests,  org.eclipse.jdt.debug.ui,  org.eclipse.wst.jsdt.debug.ui"`, `org.eclipse.debug.internal.ui.viewers.provisional; x-friends:="org.eclipse.debug.tests,  org.eclipse.jdt.debug.ui,  org.eclipse.wst.jsdt.debug.ui,  org.eclipse.debug.examples.ui"`, `org.eclipse.debug.internal.ui.viewers.update;x-friends:="org.eclipse.debug.examples.ui,org.eclipse.jdt.debug.ui,org.eclipse.wst.jsdt.debug.ui"`, `org.eclipse.debug.internal.ui.views;x-internal:=true`, `org.eclipse.debug.internal.ui.views.breakpoints;x-friends:="org.eclipse.debug.tests"`, `org.eclipse.debug.internal.ui.views.console;x-friends:="org.eclipse.ant.ui"`, `org.eclipse.debug.internal.ui.views.expression;x-internal:=true`, `org.eclipse.debug.internal.ui.views.launch;x-internal:=true`, `org.eclipse.debug.internal.ui.views.memory;x-internal:=true`, `org.eclipse.debug.internal.ui.views.memory.renderings;x-internal:=true`, `org.eclipse.debug.internal.ui.views.modules;x-internal:=true`, `org.eclipse.debug.internal.ui.views.registers;x-internal:=true`, `org.eclipse.debug.internal.ui.views.variables;x-friends:="org.eclipse.jdt.debug.ui,org.eclipse.debug.examples.ui,org.eclipse.debug.tests"`, `org.eclipse.debug.internal.ui.views.variables.details;x-friends:="org.eclipse.jdt.debug.ui,org.eclipse.wst.jsdt.debug.ui"`, `org.eclipse.debug.ui; uses:="org.eclipse.core.commands,  org.eclipse.core.commands.operations,  org.eclipse.core.resources,  org.eclipse.core.runtime,  org.eclipse.core.runtime.jobs,  org.eclipse.core.variables,  org.eclipse.debug.core,  org.eclipse.debug.core.model,  org.eclipse.debug.internal.ui.launchConfigurations,  org.eclipse.debug.ui.actions,  org.eclipse.debug.ui.contexts,  org.eclipse.debug.ui.memory,  org.eclipse.debug.ui.sourcelookup,  org.eclipse.jface.action,  org.eclipse.jface.dialogs,  org.eclipse.jface.operation,  org.eclipse.jface.preference,  org.eclipse.jface.resource,  org.eclipse.jface.text,  org.eclipse.jface.text.source,  org.eclipse.jface.util,  org.eclipse.jface.viewers,  org.eclipse.swt.events,  org.eclipse.swt.graphics,  org.eclipse.swt.widgets,  org.eclipse.ui,  org.eclipse.ui.console,  org.eclipse.ui.dialogs,  org.eclipse.ui.model,  org.eclipse.ui.part,  org.eclipse.ui.progress,  org.eclipse.ui.texteditor"`, `org.eclipse.debug.ui.actions; uses:="org.eclipse.core.commands,  org.eclipse.core.resources,  org.eclipse.core.runtime,  org.eclipse.debug.core,  org.eclipse.debug.core.model,  org.eclipse.debug.internal.ui,  org.eclipse.debug.internal.ui.actions,  org.eclipse.debug.internal.ui.actions.breakpoints,  org.eclipse.debug.internal.ui.launchConfigurations,  org.eclipse.debug.ui.contexts,  org.eclipse.jface.action,  org.eclipse.jface.operation,  org.eclipse.jface.resource,  org.eclipse.jface.text,  org.eclipse.jface.text.source,  org.eclipse.jface.viewers,  org.eclipse.swt.widgets,  org.eclipse.ui,  org.eclipse.ui.actions,  org.eclipse.ui.menus,  org.eclipse.ui.services,  org.eclipse.ui.texteditor"`, `org.eclipse.debug.ui.console; uses:="org.eclipse.core.resources,  org.eclipse.debug.core.model,  org.eclipse.jface.text,  org.eclipse.swt.graphics,  org.eclipse.ui.console"`, `org.eclipse.debug.ui.contexts;uses:="org.eclipse.debug.core,org.eclipse.jface.viewers,org.eclipse.ui"`, `org.eclipse.debug.ui.memory; uses:="org.eclipse.core.runtime,  org.eclipse.debug.core,  org.eclipse.debug.core.model,  org.eclipse.debug.internal.ui.views.memory.renderings,  org.eclipse.debug.ui,  org.eclipse.jface.action,  org.eclipse.jface.util,  org.eclipse.jface.viewers,  org.eclipse.swt.graphics,  org.eclipse.swt.widgets,  org.eclipse.ui"`, `org.eclipse.debug.ui.sourcelookup; uses:="org.eclipse.core.runtime,  org.eclipse.debug.core,  org.eclipse.debug.core.sourcelookup,  org.eclipse.debug.core.sourcelookup.containers,  org.eclipse.debug.ui,  org.eclipse.jface.dialogs,  org.eclipse.jface.resource,  org.eclipse.swt.graphics,  org.eclipse.swt.widgets,  org.eclipse.ui,  org.eclipse.ui.part"`, `org.eclipse.debug.ui.stringsubstitution;uses:="org.eclipse.core.variables,org.eclipse.swt.widgets"`
- Import-Package: `org.eclipse.ui.forms.widgets`
- Require-Bundle: `org.eclipse.core.variables;bundle-version="[3.6.0,4.0.0)"`, `org.eclipse.ui;bundle-version="[3.208.0,4.0.0)"`, `org.eclipse.ui.console;bundle-version="[3.17.0,4.0.0)"`, `org.eclipse.help;bundle-version="[3.11.0,4.0.0)"`, `org.eclipse.debug.core;bundle-version="[3.23.0,4.0.0)";visibility:=reexport`, `org.eclipse.jface.text;bundle-version="[3.30.0,4.0.0)"`, `org.eclipse.ui.workbench.texteditor;bundle-version="[3.20.0,4.0.0)"`, `org.eclipse.ui.ide;bundle-version="[3.23.0,4.0.0)"`, `org.eclipse.ui.editors;bundle-version="[3.21.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.34.0,4.0.0)"`, `org.eclipse.core.filesystem;bundle-version="[1.11.0,2.0.0)"`, `org.eclipse.e4.ui.services;bundle-version="[1.6.0,2.0.0)"`
- Packaging/source configuration: [debug/org.eclipse.debug.ui/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.ui/build.properties); observed property starts `bin.includes = icons/,\`; `source.. = ui/`; `output.. = bin/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.debug.internal.ui`](packages-08.md#p0218), [`org.eclipse.debug.internal.ui.actions`](packages-08.md#p0219), [`org.eclipse.debug.internal.ui.actions.breakpointGroups`](packages-08.md#p0220), [`org.eclipse.debug.internal.ui.actions.breakpointSortBy`](packages-08.md#p0221), [`org.eclipse.debug.internal.ui.actions.breakpoints`](packages-08.md#p0222), [`org.eclipse.debug.internal.ui.actions.expressions`](packages-08.md#p0223), [`org.eclipse.debug.internal.ui.actions.variables`](packages-08.md#p0224), [`org.eclipse.debug.internal.ui.actions.variables.details`](packages-08.md#p0225), [`org.eclipse.debug.internal.ui.breakpoints.provisional`](packages-08.md#p0226), [`org.eclipse.debug.internal.ui.codemining`](packages-08.md#p0227), [`org.eclipse.debug.internal.ui.commands.actions`](packages-08.md#p0228), [`org.eclipse.debug.internal.ui.contextlaunching`](packages-08.md#p0229), [`org.eclipse.debug.internal.ui.contexts`](packages-08.md#p0230), [`org.eclipse.debug.internal.ui.elements.adapters`](packages-08.md#p0231), [`org.eclipse.debug.internal.ui.groups`](packages-08.md#p0232), [`org.eclipse.debug.internal.ui.hover`](packages-08.md#p0233), [`org.eclipse.debug.internal.ui.importexport.breakpoints`](packages-08.md#p0234), [`org.eclipse.debug.internal.ui.importexport.launchconfigurations`](packages-08.md#p0235), [`org.eclipse.debug.internal.ui.launchConfigurations`](packages-08.md#p0236), [`org.eclipse.debug.internal.ui.memory`](packages-08.md#p0237), [`org.eclipse.debug.internal.ui.memory.provisional`](packages-08.md#p0238), [`org.eclipse.debug.internal.ui.model.elements`](packages-08.md#p0239), [`org.eclipse.debug.internal.ui.preferences`](packages-08.md#p0240), [`org.eclipse.debug.internal.ui.quickaccess`](packages-09.md#p0241), [`org.eclipse.debug.internal.ui.sourcelookup`](packages-09.md#p0242), [`org.eclipse.debug.internal.ui.sourcelookup.browsers`](packages-09.md#p0243), [`org.eclipse.debug.internal.ui.stringsubstitution`](packages-09.md#p0244), [`org.eclipse.debug.internal.ui.viewers`](packages-09.md#p0245), [`org.eclipse.debug.internal.ui.viewers.breadcrumb`](packages-09.md#p0246), [`org.eclipse.debug.internal.ui.viewers.model`](packages-09.md#p0247), [`org.eclipse.debug.internal.ui.viewers.model.provisional`](packages-09.md#p0248), [`org.eclipse.debug.internal.ui.viewers.provisional`](packages-09.md#p0249), [`org.eclipse.debug.internal.ui.viewers.update`](packages-09.md#p0250), [`org.eclipse.debug.internal.ui.views`](packages-09.md#p0251), [`org.eclipse.debug.internal.ui.views.breakpoints`](packages-09.md#p0252), [`org.eclipse.debug.internal.ui.views.console`](packages-09.md#p0253), [`org.eclipse.debug.internal.ui.views.expression`](packages-09.md#p0254), [`org.eclipse.debug.internal.ui.views.launch`](packages-09.md#p0255), [`org.eclipse.debug.internal.ui.views.memory`](packages-09.md#p0256), [`org.eclipse.debug.internal.ui.views.memory.renderings`](packages-09.md#p0257), [`org.eclipse.debug.internal.ui.views.modules`](packages-09.md#p0258), [`org.eclipse.debug.internal.ui.views.registers`](packages-09.md#p0259), [`org.eclipse.debug.internal.ui.views.variables`](packages-09.md#p0260), [`org.eclipse.debug.internal.ui.views.variables.details`](packages-09.md#p0261), [`org.eclipse.debug.ui`](packages-10.md#p0276), [`org.eclipse.debug.ui.actions`](packages-10.md#p0277), [`org.eclipse.debug.ui.console`](packages-10.md#p0278), [`org.eclipse.debug.ui.contexts`](packages-10.md#p0279), [`org.eclipse.debug.ui.memory`](packages-10.md#p0289), [`org.eclipse.debug.ui.sourcelookup`](packages-10.md#p0290), [`org.eclipse.debug.ui.stringsubstitution`](packages-10.md#p0291)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0020

### `debug/org.eclipse.debug.ui.launchview`

- Source: [debug/org.eclipse.debug.ui.launchview](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.ui.launchview); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.ui.launchview/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.ui.launchview/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.debug.ui.launchview`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.debug.ui.launchview`, version `1.2.200.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.debug.ui.launchview;x-friends:="org.eclipse.debug.ui.launchview.tests"`, `org.eclipse.debug.ui.launchview.services;x-internal:=true`
- Import-Package: `jakarta.annotation;version="[2.0.0,4.0.0)"`, `jakarta.inject;version="[2.0.0,3.0.0)"`
- Require-Bundle: `org.eclipse.ui;bundle-version="3.208.0"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.e4.ui.model.workbench`, `org.eclipse.debug.ui`, `org.eclipse.e4.core.di.annotations`, `org.eclipse.e4.ui.di`, `org.eclipse.e4.ui.services`
- Packaging/source configuration: [debug/org.eclipse.debug.ui.launchview/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.ui.launchview/build.properties); observed property starts `output.. = bin/`; `bin.includes = META-INF/,\`; `source.. = src/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.debug.ui.launchview`](packages-10.md#p0280), [`org.eclipse.debug.ui.launchview.internal`](packages-10.md#p0281), [`org.eclipse.debug.ui.launchview.internal.impl`](packages-10.md#p0282), [`org.eclipse.debug.ui.launchview.internal.launcher`](packages-10.md#p0283), [`org.eclipse.debug.ui.launchview.internal.model`](packages-10.md#p0284), [`org.eclipse.debug.ui.launchview.internal.view`](packages-10.md#p0285), [`org.eclipse.debug.ui.launchview.services`](packages-10.md#p0286)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0021

### `debug/org.eclipse.debug.ui.launchview.tests`

- Source: [debug/org.eclipse.debug.ui.launchview.tests](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.ui.launchview.tests); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.ui.launchview.tests/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.ui.launchview.tests/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.debug.ui.launchview.tests`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.debug.ui.launchview.tests`, version `1.2.0.qualifier`, declared name `%pluginName`; execution environment `JavaSE-25`
- Activation policy: `lazy`
- Import-Package: `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`
- Require-Bundle: `org.eclipse.ui;bundle-version="[3.6.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.debug.ui.launchview;bundle-version="[1.0.2,2.0.0)"`
- Packaging/source configuration: [debug/org.eclipse.debug.ui.launchview.tests/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.debug.ui.launchview.tests/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`; `pom.model.property.testClass = org.eclipse.debug.ui.launchview.tests.AutomatedSuite`; `pom.model.property.tycho.surefire.useUIHarness = true`; `pom.model.property.tycho.surefire.useUIThread = true`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.debug.ui.launchview.tests`](packages-10.md#p0287), [`org.eclipse.debug.ui.launchview.tests.launchview`](packages-10.md#p0288)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0022

### `debug/org.eclipse.ui.console`

- Source: [debug/org.eclipse.ui.console](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.ui.console); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.ui.console/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.ui.console/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ui.console`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.ui.console`, version `3.17.200.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.ui.console.ConsolePlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.ui.console; uses:="org.eclipse.core.runtime,  org.eclipse.core.runtime.jobs,  org.eclipse.jface.action,  org.eclipse.jface.resource,  org.eclipse.jface.text,  org.eclipse.jface.text.source,  org.eclipse.jface.util,  org.eclipse.swt.custom,  org.eclipse.swt.events,  org.eclipse.swt.graphics,  org.eclipse.swt.widgets,  org.eclipse.ui,  org.eclipse.ui.console.actions,  org.eclipse.ui.part,  org.eclipse.ui.plugin,  org.osgi.framework"`, `org.eclipse.ui.console.actions; uses:="org.eclipse.jface.action,  org.eclipse.jface.text,  org.eclipse.ui.console,  org.eclipse.ui.texteditor"`, `org.eclipse.ui.internal.console;x-internal:=true`
- Import-Package: `org.eclipse.compare;resolution:=optional`, `org.eclipse.compare.structuremergeviewer;resolution:=optional`
- Require-Bundle: `org.eclipse.ui;bundle-version="[3.208.0,4.0.0)"`, `org.eclipse.jface.text;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.ui.workbench.texteditor;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.core.expressions;bundle-version="[3.4.0,4.0.0)"`, `org.eclipse.core.variables;bundle-version="[3.2.800,4.0.0)"`, `org.eclipse.debug.core;bundle-version="[3.16.0,4.0.0)"`
- Packaging/source configuration: [debug/org.eclipse.ui.console/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.ui.console/build.properties); observed property starts `bin.includes = icons/,\`; `source.. = src/`; `output.. = bin/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.ui.console`](packages-18.md#p0534), [`org.eclipse.ui.console.actions`](packages-18.md#p0535), [`org.eclipse.ui.internal.console`](packages-19.md#p0555), [`org.eclipse.ui.internal.console.ansi`](packages-19.md#p0556), [`org.eclipse.ui.internal.console.ansi.commands`](packages-19.md#p0557), [`org.eclipse.ui.internal.console.ansi.participants`](packages-19.md#p0558), [`org.eclipse.ui.internal.console.ansi.preferences`](packages-19.md#p0559), [`org.eclipse.ui.internal.console.ansi.utils`](packages-19.md#p0560)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0023

### `debug/org.eclipse.ui.externaltools`

- Source: [debug/org.eclipse.ui.externaltools](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.ui.externaltools); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.ui.externaltools/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.ui.externaltools/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ui.externaltools`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.ui.externaltools`, version `3.7.200.qualifier`, declared name `%Plugin.name`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.ui.externaltools.internal.model.ExternalToolsPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.ui.externaltools.internal.launchConfigurations;x-friends:="org.eclipse.ant.ui"`, `org.eclipse.ui.externaltools.internal.menu;x-internal:=true`, `org.eclipse.ui.externaltools.internal.model;x-friends:="org.eclipse.ant.ui"`, `org.eclipse.ui.externaltools.internal.program.launchConfigurations;x-friends:="org.eclipse.ant.ui"`, `org.eclipse.ui.externaltools.internal.ui;x-friends:="org.eclipse.ant.ui"`, `org.eclipse.ui.externaltools.internal.variables;x-internal:=true`
- Require-Bundle: `org.eclipse.ui.ide;bundle-version="[3.2.0,4.0.0)";resolution:=optional`, `org.eclipse.core.variables;bundle-version="[3.2.800,4.0.0)"`, `org.eclipse.ui;bundle-version="[3.208.0,4.0.0)"`, `org.eclipse.debug.ui;bundle-version="[3.10.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.core.externaltools;bundle-version="[1.0.300,2.0.0)"`
- Packaging/source configuration: [debug/org.eclipse.ui.externaltools/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.ui.externaltools/build.properties); observed property starts `source.. = External Tools Base/,\`; `bin.includes = icons/,\`; `output.. = bin/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.ui.externaltools.internal.launchConfigurations`](packages-18.md#p0537), [`org.eclipse.ui.externaltools.internal.menu`](packages-18.md#p0538), [`org.eclipse.ui.externaltools.internal.model`](packages-18.md#p0539), [`org.eclipse.ui.externaltools.internal.program.launchConfigurations`](packages-18.md#p0540), [`org.eclipse.ui.externaltools.internal.ui`](packages-19.md#p0541), [`org.eclipse.ui.externaltools.internal.variables`](packages-19.md#p0542)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0024

### `debug/org.eclipse.unittest.ui`

- Source: [debug/org.eclipse.unittest.ui](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.unittest.ui); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.unittest.ui/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.unittest.ui/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.unittest.ui`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.unittest.ui`, version `1.2.300.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.unittest.internal.UnitTestPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.unittest.launcher`, `org.eclipse.unittest.model;uses:="org.eclipse.debug.core"`, `org.eclipse.unittest.ui; uses:="org.eclipse.debug.core,  org.eclipse.jface.action,  org.eclipse.swt.widgets,  org.eclipse.unittest.launcher,  org.eclipse.unittest.model"`
- Require-Bundle: `org.eclipse.ui.ide;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.jface.text;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.ui;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.debug.ui;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.compare;bundle-version="[3.5.0,4.0.0)"`
- Packaging/source configuration: [debug/org.eclipse.unittest.ui/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/debug/org.eclipse.unittest.ui/build.properties); observed property starts `bin.includes = plugin.xml,\`; `source.. = src/`; `output.. = bin/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.unittest.internal`](packages-20.md#p0582), [`org.eclipse.unittest.internal.junitXmlReport`](packages-20.md#p0583), [`org.eclipse.unittest.internal.launcher`](packages-20.md#p0584), [`org.eclipse.unittest.internal.model`](packages-20.md#p0585), [`org.eclipse.unittest.internal.ui`](packages-20.md#p0586), [`org.eclipse.unittest.internal.ui.history`](packages-20.md#p0587), [`org.eclipse.unittest.launcher`](packages-20.md#p0588), [`org.eclipse.unittest.model`](packages-20.md#p0589), [`org.eclipse.unittest.ui`](packages-20.md#p0590)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0025

### `docs`

- Source: [docs](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/docs); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/docs/.project)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.platform.docs`; natures: 
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0026

### `platform/org.eclipse.platform`

- Source: [platform/org.eclipse.platform](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/platform/org.eclipse.platform); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/platform/org.eclipse.platform/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/platform/org.eclipse.platform/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.platform`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.platform`, version `4.42.0.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Export-Package: `org.eclipse.platform.internal;x-internal:=true`
- Require-Bundle: `org.eclipse.ui.intro;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.cheatsheets;bundle-version="[3.2.0,4.0.0)";resolution:=optional`, `org.eclipse.ui.forms;bundle-version="[3.2.0,4.0.0)";resolution:=optional`, `org.eclipse.ui;bundle-version="[3.2.0,4.0.0)";resolution:=optional`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`
- Packaging/source configuration: [platform/org.eclipse.platform/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/platform/org.eclipse.platform/build.properties); observed property starts `bin.includes = about.html,\`; `source.. = src-intro`; `output.. = bin`; `pom.model.property.tycho.buildqualifier.format = 'v${buildTimestamp}'`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.platform.internal`](packages-13.md#p0379)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0027

### `platform/org.eclipse.sdk`

- Source: [platform/org.eclipse.sdk](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/platform/org.eclipse.sdk); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/platform/org.eclipse.sdk/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/platform/org.eclipse.sdk/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.sdk`; natures: `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.sdk`, version `4.42.0.qualifier`, declared name `%pluginName`; execution environment `not declared`
- Require-Bundle: `org.eclipse.ui;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.help.ui;bundle-version="[4.0.0,5.0.0)"`, `org.eclipse.help.base;bundle-version="[4.0.0,5.0.0)"`, `org.eclipse.ui.cheatsheets;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.ide.application;bundle-version="[1.0.0,2.0.0)"`, `org.eclipse.platform;bundle-version="[4.8.0,5.0.0)"`
- Packaging/source configuration: [platform/org.eclipse.sdk/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/platform/org.eclipse.sdk/build.properties); observed property starts `bin.includes = about.html,\`; `pom.model.property.tycho.buildqualifier.format = 'v${buildTimestamp}'`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0028

### `releng/org.eclipse.platform.setup`

- Source: [releng/org.eclipse.platform.setup](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/releng/org.eclipse.platform.setup); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/releng/org.eclipse.platform.setup/.project)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.platform.setup`; natures: 
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0029

### `resources`

- Source: [resources](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources); implementation/build; activation must be checked below
- Discovery evidence: [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/pom.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Declared Maven coordinates: `org.eclipse.platform:eclipse.platform.resources:4.42.0-SNAPSHOT`; packaging `pom`; parent `org.eclipse.platform:eclipse.platform:4.42.0-SNAPSHOT`, relative path `omitted; effective parent convention applies`
- Aggregates: [`resources/bundles`](projects-01.md#u0030) (non-profile declaration), [`resources/examples`](projects-02.md#u0040) (non-profile declaration), [`resources/tests`](projects-02.md#u0042) (non-profile declaration)
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0030

### `resources/bundles`

- Source: [resources/bundles](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles); implementation/build; activation must be checked below
- Discovery evidence: parent module declaration; no local descriptor
- Reactor reachability: reachable through parsed non-profile module declarations
- Packaging/source configuration: [resources/bundles/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/build.properties); observed property starts `pom.model.artifactId=resources-bundles`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

