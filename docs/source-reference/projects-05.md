# Project/module records 5

> Inspected source revision: `0de5d9acb5cff6b133cb1c55f6844720088c7d92`. Source/manifest observation only; builds and tests were not run.

[Reference home](README.md) · [Selected contracts](CONTRACTS.md) · [Coverage](COVERAGE.md)

## U0121

### `ua/org.eclipse.tips.core`

- Source: [ua/org.eclipse.tips.core](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.core); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.core/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.core/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.tips.core`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.tips.core`, version `0.3.500.qualifier`, declared name `%Bundle-Name`; execution environment `JavaSE-17`
- Export-Package: `org.eclipse.tips.core;uses:="org.eclipse.core.runtime,org.eclipse.osgi.util"`, `org.eclipse.tips.core.internal;x-internal:=true`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="3.29.0"`
- Packaging/source configuration: [ua/org.eclipse.tips.core/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.core/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.tips.core`](packages-16.md#p0475), [`org.eclipse.tips.core.internal`](packages-16.md#p0476)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0122

### `ua/org.eclipse.tips.examples`

- Source: [ua/org.eclipse.tips.examples](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.examples); example/template (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.examples/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.examples/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.tips.examples`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.tips.examples`, version `0.4.0.qualifier`, declared name `Tip of the Day Examples`; execution environment `JavaSE-21`
- Import-Package: `org.osgi.framework;version="[1.8.0,2.0.0)"`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="3.29.0"`, `org.eclipse.tips.core;bundle-version="0.1.0"`, `org.eclipse.tips.json;bundle-version="0.1.0"`, `org.eclipse.tips.ui;bundle-version="0.1.0"`, `org.eclipse.e4.core.commands;bundle-version="0.1.0"`, `org.eclipse.ui;bundle-version="3.208.0"`
- Packaging/source configuration: [ua/org.eclipse.tips.examples/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.examples/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.tips.examples`](packages-16.md#p0477), [`org.eclipse.tips.examples.browserfunction`](packages-16.md#p0478), [`org.eclipse.tips.examples.eclipsetips`](packages-16.md#p0479), [`org.eclipse.tips.examples.java.java9`](packages-16.md#p0480), [`org.eclipse.tips.examples.json`](packages-17.md#p0481), [`org.eclipse.tips.examples.swttip`](packages-17.md#p0482), [`org.eclipse.tips.examples.test`](packages-17.md#p0483), [`org.eclipse.tips.examples.tips`](packages-17.md#p0484), [`org.eclipse.tips.examples.tipsframework`](packages-17.md#p0485)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0123

### `ua/org.eclipse.tips.feature`

- Source: [ua/org.eclipse.tips.feature](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.feature); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.feature/.project), [feature.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.feature/feature.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.tips.feature`; natures: `org.eclipse.pde.FeatureNature`
- Distribution feature: `org.eclipse.tips.feature`; included plug-ins `org.eclipse.tips.ui`, `org.eclipse.tips.core`, `org.eclipse.tips.json`, `org.eclipse.tips.ide`; included features none declared
- Packaging/source configuration: [ua/org.eclipse.tips.feature/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.feature/build.properties); observed property starts `bin.includes = feature.xml,\`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0124

### `ua/org.eclipse.tips.ide`

- Source: [ua/org.eclipse.tips.ide](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.ide); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.ide/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.ide/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.tips.ide`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.tips.ide`, version `0.4.200.qualifier`, declared name `%Bundle-Name`; execution environment `JavaSE-21`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.tips.ide.internal;x-internal:=true`
- Import-Package: `jakarta.annotation;version="[2.0.0,4.0.0)"`, `org.osgi.service.event;version="[1.4.0,2.0.0)"`, `org.osgi.service.event.propertytypes;version="[1.4.0,2.0.0)"`
- Require-Bundle: `org.eclipse.ui;bundle-version="3.208.0"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.core.expressions;bundle-version="3.0.0"`, `org.eclipse.e4.ui.workbench;bundle-version="1.0.0"`, `org.eclipse.tips.core;bundle-version="0.1.0"`, `org.eclipse.tips.ui;bundle-version="0.1.0"`, `org.eclipse.tips.json`
- Packaging/source configuration: [ua/org.eclipse.tips.ide/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.ide/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.tips.ide.internal`](packages-17.md#p0486), [`org.eclipse.tips.ide.internal.provider`](packages-17.md#p0487)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0125

### `ua/org.eclipse.tips.json`

- Source: [ua/org.eclipse.tips.json](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.json); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.json/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.json/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.tips.json`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.tips.json`, version `0.3.500.qualifier`, declared name `%Bundle-Name`; execution environment `JavaSE-17`
- Export-Package: `org.eclipse.tips.json;uses:="org.eclipse.core.runtime,org.eclipse.tips.core"`, `org.eclipse.tips.json.internal;x-internal:=true`
- Import-Package: `com.google.gson;version="[2.11.0,3.0.0)"`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="3.29.0"`, `com.google.gson`, `org.eclipse.tips.core;bundle-version="0.1.0"`
- Packaging/source configuration: [ua/org.eclipse.tips.json/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.json/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.tips.json`](packages-17.md#p0488), [`org.eclipse.tips.json.internal`](packages-17.md#p0489)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0126

### `ua/org.eclipse.tips.tests`

- Source: [ua/org.eclipse.tips.tests](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.tests); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.tests/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.tests/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.tips.tests`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.tips.tests`, version `1.3.0.qualifier`, declared name `Tip of the Day Tests`; execution environment `JavaSE-21`
- Import-Package: `com.google.gson;version="[2.8.6,3.0.0)"`, `org.assertj.core.api`, `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.function;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`
- Require-Bundle: `org.eclipse.tips.ide;bundle-version="0.1.0"`, `org.eclipse.core.runtime;bundle-version="3.29.0"`, `org.eclipse.swt`, `org.eclipse.jface;bundle-version="3.39.0"`, `org.eclipse.tips.core;bundle-version="0.1.0"`, `org.eclipse.tips.ui;bundle-version="0.1.0"`, `org.eclipse.tips.json;bundle-version="0.1.0"`
- Packaging/source configuration: [ua/org.eclipse.tips.tests/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.tests/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`; `pom.model.property.testClass = org.eclipse.tips.tests.AllTipsTests`; `pom.model.property.tycho.surefire.useUIHarness = true`; `pom.model.property.tycho.surefire.useUIThread = true`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.tips.core`](packages-16.md#p0475), [`org.eclipse.tips.json.internal`](packages-17.md#p0489), [`org.eclipse.tips.manual.tests`](packages-17.md#p0490), [`org.eclipse.tips.tests`](packages-17.md#p0491), [`org.eclipse.tips.util`](packages-17.md#p0495)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0127

### `ua/org.eclipse.tips.ui`

- Source: [ua/org.eclipse.tips.ui](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.ui); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.ui/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.ui/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.tips.ui`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.tips.ui`, version `0.4.300.qualifier`, declared name `%Bundle-Name`; execution environment `JavaSE-21`
- Export-Package: `org.eclipse.tips.ui;uses:="org.eclipse.swt.widgets"`, `org.eclipse.tips.ui.internal; uses:="org.eclipse.jface.dialogs,  org.eclipse.osgi.util,  org.eclipse.swt.browser,  org.eclipse.swt.graphics,  org.eclipse.swt.widgets,  org.eclipse.tips.core,  org.eclipse.tips.core.internal"`, `org.eclipse.tips.ui.internal.util;x-friends:="org.eclipse.tips.tests"`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="3.29.0"`, `org.eclipse.jface;bundle-version="3.39.0"`, `org.eclipse.tips.core;bundle-version="0.1.0"`
- Packaging/source configuration: [ua/org.eclipse.tips.ui/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.tips.ui/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.tips.ui`](packages-17.md#p0492), [`org.eclipse.tips.ui.internal`](packages-17.md#p0493), [`org.eclipse.tips.ui.internal.util`](packages-17.md#p0494)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0128

### `ua/org.eclipse.ua.tests`

- Source: [ua/org.eclipse.ua.tests](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ua.tests); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ua.tests/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ua.tests/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ua.tests/pom.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ua.tests`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Declared Maven coordinates: `org.eclipse.platform:org.eclipse.ua.tests:3.7.500-SNAPSHOT`; packaging `eclipse-test-plugin`; parent `eclipse.platform.ua:eclipse.platform.ua:4.42.0-SNAPSHOT`, relative path `..`
- Bundle identity: `org.eclipse.ua.tests`, version `3.7.500.qualifier`, declared name `User Assistance Test`; execution environment `JavaSE-21`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.ua.tests`, `org.eclipse.ua.tests.browser.servlet`, `org.eclipse.ua.tests.cheatsheet`, `org.eclipse.ua.tests.cheatsheet.composite`, `org.eclipse.ua.tests.cheatsheet.execution`, `org.eclipse.ua.tests.cheatsheet.other`, `org.eclipse.ua.tests.cheatsheet.parser`, `org.eclipse.ua.tests.cheatsheet.performance`, `org.eclipse.ua.tests.cheatsheet.util`, `org.eclipse.ua.tests.help`, `org.eclipse.ua.tests.help.criteria`, `org.eclipse.ua.tests.help.dynamic`, `org.eclipse.ua.tests.help.index`, `org.eclipse.ua.tests.help.other`, `org.eclipse.ua.tests.help.performance`, `org.eclipse.ua.tests.help.preferences`, `org.eclipse.ua.tests.help.remote`, `org.eclipse.ua.tests.help.scope`, `org.eclipse.ua.tests.help.search`, `org.eclipse.ua.tests.help.toc`, `org.eclipse.ua.tests.help.util`, `org.eclipse.ua.tests.help.webapp`, `org.eclipse.ua.tests.help.webapp.service`, `org.eclipse.ua.tests.help.webextension`, `org.eclipse.ua.tests.intro`, `org.eclipse.ua.tests.intro.anchors`, `org.eclipse.ua.tests.intro.contentdetect`, `org.eclipse.ua.tests.intro.other`, `org.eclipse.ua.tests.intro.parser`, `org.eclipse.ua.tests.intro.performance`, `org.eclipse.ua.tests.intro.util`, `org.eclipse.ua.tests.util`
- Import-Package: `javax.servlet;version="3.1.0"`, `javax.servlet.http;version="3.1.0"`, `org.apache.lucene.index;version="[10.0.0,11.0.0)"`, `org.apache.lucene.search;version="[10.0.0,11.0.0)"`, `org.apache.lucene.store;version="[10.0.0,11.0.0)"`, `org.apache.lucene.util;version="[10.0.0,11.0.0)"`, `org.assertj.core.api`, `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.function;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`
- Require-Bundle: `org.eclipse.help.ui`, `org.eclipse.help.webapp`, `org.eclipse.test.performance`, `org.eclipse.ui.cheatsheets`, `org.eclipse.ui.intro`, `org.eclipse.ui.intro.universal`, `org.eclipse.ui.forms`, `org.eclipse.equinox.jsp.jasper.registry;bundle-version="1.0.100"`, `org.eclipse.equinox.http.registry;bundle-version="1.3.100"`
- Packaging/source configuration: [ua/org.eclipse.ua.tests/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ua.tests/build.properties); observed property starts `bin.includes = plugin.xml,\`; `output.. = bin/`; `source.. = base/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.ua.tests`](packages-17.md#p0496), [`org.eclipse.ua.tests.browser.servlet`](packages-17.md#p0497), [`org.eclipse.ua.tests.cheatsheet`](packages-17.md#p0498), [`org.eclipse.ua.tests.cheatsheet.composite`](packages-17.md#p0499), [`org.eclipse.ua.tests.cheatsheet.execution`](packages-17.md#p0500), [`org.eclipse.ua.tests.cheatsheet.other`](packages-17.md#p0501), [`org.eclipse.ua.tests.cheatsheet.parser`](packages-17.md#p0502), [`org.eclipse.ua.tests.cheatsheet.performance`](packages-17.md#p0503), [`org.eclipse.ua.tests.cheatsheet.util`](packages-17.md#p0504), [`org.eclipse.ua.tests.help`](packages-17.md#p0510), [`org.eclipse.ua.tests.help.criteria`](packages-18.md#p0511), [`org.eclipse.ua.tests.help.dynamic`](packages-18.md#p0512), [`org.eclipse.ua.tests.help.index`](packages-18.md#p0513), [`org.eclipse.ua.tests.help.other`](packages-18.md#p0514), [`org.eclipse.ua.tests.help.performance`](packages-18.md#p0515), [`org.eclipse.ua.tests.help.preferences`](packages-18.md#p0516), [`org.eclipse.ua.tests.help.remote`](packages-18.md#p0517), [`org.eclipse.ua.tests.help.scope`](packages-18.md#p0518), [`org.eclipse.ua.tests.help.search`](packages-18.md#p0519), [`org.eclipse.ua.tests.help.toc`](packages-18.md#p0520), [`org.eclipse.ua.tests.help.util`](packages-18.md#p0521), [`org.eclipse.ua.tests.help.webapp`](packages-18.md#p0522), [`org.eclipse.ua.tests.help.webapp.service`](packages-18.md#p0523), [`org.eclipse.ua.tests.help.webextension`](packages-18.md#p0524), [`org.eclipse.ua.tests.intro`](packages-18.md#p0525), [`org.eclipse.ua.tests.intro.anchors`](packages-18.md#p0526), [`org.eclipse.ua.tests.intro.contentdetect`](packages-18.md#p0527), [`org.eclipse.ua.tests.intro.other`](packages-18.md#p0528), [`org.eclipse.ua.tests.intro.parser`](packages-18.md#p0529), [`org.eclipse.ua.tests.intro.performance`](packages-18.md#p0530), [`org.eclipse.ua.tests.intro.util`](packages-18.md#p0531), [`org.eclipse.ua.tests.util`](packages-18.md#p0532)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0129

### `ua/org.eclipse.ua.tests.doc`

- Source: [ua/org.eclipse.ua.tests.doc](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ua.tests.doc); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ua.tests.doc/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ua.tests.doc/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ua.tests.doc/pom.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ua.tests.doc`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Declared Maven coordinates: `org.eclipse.platform:org.eclipse.ua.tests.doc:1.3.200-SNAPSHOT`; packaging `eclipse-test-plugin`; parent `eclipse.platform.ua:eclipse.platform.ua:4.42.0-SNAPSHOT`, relative path `..`
- Bundle identity: `org.eclipse.ua.tests.doc`, version `1.3.200.qualifier`, declared name `%Bundle-Name`; execution environment `JavaSE-21`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.ua.tests.doc`, `org.eclipse.ua.tests.doc.internal;x-internal:=true`, `org.eclipse.ua.tests.doc.internal.actions;x-internal:=true`, `org.eclipse.ua.tests.doc.internal.dialogs;x-internal:=true`, `org.eclipse.ua.tests.doc.internal.linkchecker;x-internal:=true`
- Import-Package: `javax.servlet;version="3.1.0"`, `javax.servlet.http;version="3.1.0"`, `org.assertj.core.api`, `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`
- Require-Bundle: `org.eclipse.ui`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.equinox.http.registry;bundle-version="1.0.200"`, `org.eclipse.help;bundle-version="3.4.0"`, `org.eclipse.help.webapp;bundle-version="3.4.0"`, `org.eclipse.help.base;bundle-version="3.4.0"`, `org.eclipse.equinox.simpleconfigurator.manipulator;bundle-version="2.0.100"`, `org.eclipse.equinox.frameworkadmin;bundle-version="2.0.200"`
- Packaging/source configuration: [ua/org.eclipse.ua.tests.doc/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ua.tests.doc/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.ua.tests.doc`](packages-17.md#p0505), [`org.eclipse.ua.tests.doc.internal`](packages-17.md#p0506), [`org.eclipse.ua.tests.doc.internal.actions`](packages-17.md#p0507), [`org.eclipse.ua.tests.doc.internal.dialogs`](packages-17.md#p0508), [`org.eclipse.ua.tests.doc.internal.linkchecker`](packages-17.md#p0509)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0130

### `ua/org.eclipse.ui.cheatsheets`

- Source: [ua/org.eclipse.ui.cheatsheets](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.cheatsheets); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.cheatsheets/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.cheatsheets/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ui.cheatsheets`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.ui.cheatsheets`, version `3.9.300.qualifier`, declared name `%PLUGIN_NAME`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.ui.internal.cheatsheets.CheatSheetPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.ui.cheatsheets; uses:="org.eclipse.core.runtime,  org.eclipse.help,  org.eclipse.jface.action,  org.eclipse.swt.widgets"`, `org.eclipse.ui.internal.cheatsheets;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.internal.cheatsheets.actions;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.internal.cheatsheets.composite.explorer;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.internal.cheatsheets.composite.model;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.internal.cheatsheets.composite.parser;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.internal.cheatsheets.composite.views;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.internal.cheatsheets.data;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.internal.cheatsheets.dialogs;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.internal.cheatsheets.handlers;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.internal.cheatsheets.registry;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.internal.cheatsheets.state;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.internal.cheatsheets.views;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.internal.provisional.cheatsheets;x-friends:="org.eclipse.ua.tests"`
- Require-Bundle: `org.eclipse.ui.forms;bundle-version="[3.14.0,4.0.0)"`, `org.eclipse.help.ui;bundle-version="[4.6.0,5.0.0)";resolution:=optional`
- Packaging/source configuration: [ua/org.eclipse.ui.cheatsheets/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.cheatsheets/build.properties); observed property starts `bin.includes = plugin.properties,\`; `source.. = src/`; `output.. = bin/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.ui.cheatsheets`](packages-18.md#p0533), [`org.eclipse.ui.internal.cheatsheets`](packages-19.md#p0543), [`org.eclipse.ui.internal.cheatsheets.actions`](packages-19.md#p0544), [`org.eclipse.ui.internal.cheatsheets.composite.explorer`](packages-19.md#p0545), [`org.eclipse.ui.internal.cheatsheets.composite.model`](packages-19.md#p0546), [`org.eclipse.ui.internal.cheatsheets.composite.parser`](packages-19.md#p0547), [`org.eclipse.ui.internal.cheatsheets.composite.views`](packages-19.md#p0548), [`org.eclipse.ui.internal.cheatsheets.data`](packages-19.md#p0549), [`org.eclipse.ui.internal.cheatsheets.dialogs`](packages-19.md#p0550), [`org.eclipse.ui.internal.cheatsheets.handlers`](packages-19.md#p0551), [`org.eclipse.ui.internal.cheatsheets.registry`](packages-19.md#p0552), [`org.eclipse.ui.internal.cheatsheets.state`](packages-19.md#p0553), [`org.eclipse.ui.internal.cheatsheets.views`](packages-19.md#p0554), [`org.eclipse.ui.internal.provisional.cheatsheets`](packages-20.md#p0577)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0131

### `ua/org.eclipse.ui.intro`

- Source: [ua/org.eclipse.ui.intro](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ui.intro`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.ui.intro`, version `3.8.200.qualifier`, declared name `%plugin_name`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.ui.internal.intro.impl.IntroPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.ui.internal.intro.impl;x-friends:="org.eclipse.ui.intro.universal,org.eclipse.ua.tests"`, `org.eclipse.ui.internal.intro.impl.html;x-internal:=true`, `org.eclipse.ui.internal.intro.impl.model;x-friends:="org.eclipse.ua.tests,org.eclipse.ui.intro.universal,org.eclipse.ui.intro.quicklinks"`, `org.eclipse.ui.internal.intro.impl.model.loader;x-friends:="org.eclipse.ua.tests,org.eclipse.ui.intro.universal"`, `org.eclipse.ui.internal.intro.impl.model.url;x-internal:=true`, `org.eclipse.ui.internal.intro.impl.model.util;x-internal:=true`, `org.eclipse.ui.internal.intro.impl.model.viewer;x-internal:=true`, `org.eclipse.ui.internal.intro.impl.parts;x-internal:=true`, `org.eclipse.ui.internal.intro.impl.presentations;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.internal.intro.impl.swt;x-internal:=true`, `org.eclipse.ui.internal.intro.impl.util;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.intro.config; uses:="org.eclipse.core.runtime,  org.eclipse.swt.widgets,  org.eclipse.ui,  org.eclipse.ui.forms.widgets,  org.eclipse.ui.internal.intro.impl.model,  org.eclipse.ui.intro,  org.eclipse.ui.part,  org.w3c.dom"`, `org.eclipse.ui.intro.contentproviders; uses:="org.eclipse.swt.widgets,  org.eclipse.ui.forms.widgets,  org.eclipse.ui.intro,  org.eclipse.ui.intro.config"`
- Import-Package: `jakarta.annotation;version="[2.0.0,4.0.0)"`, `jakarta.inject;version="[2.0.0,3.0.0)"`, `javax.xml.parsers`, `javax.xml.transform`, `javax.xml.transform.dom`, `javax.xml.transform.stream`, `org.eclipse.e4.core.di.annotations`, `org.w3c.dom`, `org.xml.sax`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.help.base;bundle-version="[4.0.0,5.0.0)";resolution:=optional`, `org.eclipse.ui;bundle-version="[3.6.0,4.0.0)"`, `org.eclipse.ui.forms;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.e4.ui.workbench`, `org.eclipse.e4.ui.model.workbench`
- Packaging/source configuration: [ua/org.eclipse.ui.intro/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = plugin.xml,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.ui.internal.intro.impl`](packages-19.md#p0561), [`org.eclipse.ui.internal.intro.impl.html`](packages-19.md#p0562), [`org.eclipse.ui.internal.intro.impl.model`](packages-19.md#p0563), [`org.eclipse.ui.internal.intro.impl.model.loader`](packages-19.md#p0564), [`org.eclipse.ui.internal.intro.impl.model.url`](packages-19.md#p0565), [`org.eclipse.ui.internal.intro.impl.model.util`](packages-19.md#p0566), [`org.eclipse.ui.internal.intro.impl.model.viewer`](packages-19.md#p0567), [`org.eclipse.ui.internal.intro.impl.parts`](packages-19.md#p0568), [`org.eclipse.ui.internal.intro.impl.presentations`](packages-19.md#p0569), [`org.eclipse.ui.internal.intro.impl.swt`](packages-19.md#p0570), [`org.eclipse.ui.internal.intro.impl.util`](packages-20.md#p0571), [`org.eclipse.ui.intro.config`](packages-20.md#p0578), [`org.eclipse.ui.intro.contentproviders`](packages-20.md#p0579)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0132

### `ua/org.eclipse.ui.intro.quicklinks`

- Source: [ua/org.eclipse.ui.intro.quicklinks](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.quicklinks); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.quicklinks/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.quicklinks/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ui.intro.quicklinks`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.ui.intro.quicklinks`, version `1.3.100.qualifier`, declared name `%plugin_name`; execution environment `JavaSE-21`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.ui.intro.quicklinks;version="1.0.0"; uses:="org.eclipse.jface.viewers,  org.eclipse.swt.graphics,  org.eclipse.swt.widgets,  org.eclipse.ui.forms.widgets,  org.eclipse.ui.intro.config"`
- Import-Package: `org.osgi.framework;version="[1.8.0,2.0.0)"`
- Require-Bundle: `org.eclipse.ui.intro;bundle-version="3.4.0"`, `org.eclipse.ui.workbench;bundle-version="3.108.0"`, `org.eclipse.jface;bundle-version="3.39.0"`, `org.eclipse.ui.forms;bundle-version="3.7.0"`, `org.eclipse.core.runtime;bundle-version="3.29.0"`
- Packaging/source configuration: [ua/org.eclipse.ui.intro.quicklinks/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.quicklinks/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.ui.intro.quicklinks`](packages-20.md#p0580)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0133

### `ua/org.eclipse.ui.intro.quicklinks.examples`

- Source: [ua/org.eclipse.ui.intro.quicklinks.examples](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.quicklinks.examples); example/template (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.quicklinks.examples/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.quicklinks.examples/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ui.intro.quicklinks.examples`; natures: `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.ui.intro.quicklinks.examples`, version `1.3.100.qualifier`, declared name `%plugin_name`; execution environment `JavaSE-17`
- Packaging/source configuration: [ua/org.eclipse.ui.intro.quicklinks.examples/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.quicklinks.examples/build.properties); observed property starts `bin.includes = META-INF/,\`; `pom.model.property.skipAPIAnalysis = true`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0134

### `ua/org.eclipse.ui.intro.solstice.examples`

- Source: [ua/org.eclipse.ui.intro.solstice.examples](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.solstice.examples); example/template (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.solstice.examples/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.solstice.examples/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ui.intro.solstice.examples`; natures: `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.ui.intro.solstice.examples`, version `1.3.100.qualifier`, declared name `Examples of Extending the Solstice theme organization`; execution environment `JavaSE-17`
- Packaging/source configuration: [ua/org.eclipse.ui.intro.solstice.examples/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.solstice.examples/build.properties); observed property starts `bin.includes = META-INF/,\`; `pom.model.property.skipAPIAnalysis = true`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0135

### `ua/org.eclipse.ui.intro.universal`

- Source: [ua/org.eclipse.ui.intro.universal](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.universal); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.universal/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.universal/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ui.intro.universal`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.ui.intro.universal`, version `3.6.200.qualifier`, declared name `%plugin_name`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.ui.internal.intro.universal.UniversalIntroPlugin`
- Activation policy: `lazy;exclude:="org.eclipse.ui.internal.intro.universal.contentdetect"`
- Export-Package: `org.eclipse.ui.internal.intro.universal;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.internal.intro.universal.contentdetect;x-friends:="org.eclipse.ua.tests"`, `org.eclipse.ui.internal.intro.universal.util;x-internal:=true`, `org.eclipse.ui.intro.universal;uses:="org.eclipse.core.runtime"`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.help;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.ui;bundle-version="[3.6.0,4.0.0)"`, `org.eclipse.ui.intro;bundle-version="[3.4.0,4.0.0)"`
- Packaging/source configuration: [ua/org.eclipse.ui.intro.universal/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/ua/org.eclipse.ui.intro.universal/build.properties); observed property starts `bin.includes = plugin.xml,\`; `source.. = src/`; `output.. = bin/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.ui.internal.intro.universal`](packages-20.md#p0572), [`org.eclipse.ui.internal.intro.universal.contentdetect`](packages-20.md#p0573), [`org.eclipse.ui.internal.intro.universal.util`](packages-20.md#p0574), [`org.eclipse.ui.intro.universal`](packages-20.md#p0581)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0136

### `update/org.eclipse.update.configurator`

- Source: [update/org.eclipse.update.configurator](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/update/org.eclipse.update.configurator); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/update/org.eclipse.update.configurator/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/update/org.eclipse.update.configurator/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.update.configurator`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.update.configurator`, version `3.5.1200.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.update.internal.configurator.ConfigurationActivator`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.update.configurator`, `org.eclipse.update.internal.configurator;x-friends:="org.eclipse.update.core"`, `org.eclipse.update.internal.configurator.branding;x-friends:="org.eclipse.update.core"`
- Import-Package: `javax.xml.parsers`, `org.w3c.dom`, `org.xml.sax`, `org.xml.sax.helpers`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="3.29.0"`
- Packaging/source configuration: [update/org.eclipse.update.configurator/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/update/org.eclipse.update.configurator/build.properties); observed property starts `bin.includes = .options,\`; `source.. = src/`; `output.. = bin/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.update.configurator`](packages-20.md#p0591), [`org.eclipse.update.internal.configurator`](packages-20.md#p0593), [`org.eclipse.update.internal.configurator.branding`](packages-20.md#p0594)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0137

### `update/org.eclipse.update.configurator.tests`

- Source: [update/org.eclipse.update.configurator.tests](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/update/org.eclipse.update.configurator.tests); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/update/org.eclipse.update.configurator.tests/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/update/org.eclipse.update.configurator.tests/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.update.configurator.tests`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.update.configurator.tests`, version `0.2.200.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.update.configurator.tests`, `org.eclipse.update.internal.configurator.tests`
- Import-Package: `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.io;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`
- Require-Bundle: `org.eclipse.core.runtime`, `org.eclipse.update.configurator`
- Packaging/source configuration: [update/org.eclipse.update.configurator.tests/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/update/org.eclipse.update.configurator.tests/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`; `pom.model.property.testClass = org.eclipse.update.configurator.tests.AutomatedConfiguratorSuite`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.update.configurator.tests`](packages-20.md#p0592), [`org.eclipse.update.internal.configurator.tests`](packages-20.md#p0595)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

