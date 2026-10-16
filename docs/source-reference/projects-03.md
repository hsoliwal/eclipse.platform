# Project/module records 3

> Inspected source revision: `0de5d9acb5cff6b133cb1c55f6844720088c7d92`. Source/manifest observation only; builds and tests were not run.

[Reference home](README.md) · [Selected contracts](CONTRACTS.md) · [Coverage](COVERAGE.md)

## U0061

### `runtime/bundles/org.eclipse.e4.core.contexts`

- Source: [runtime/bundles/org.eclipse.e4.core.contexts](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.contexts); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.contexts/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.contexts/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.e4.core.contexts`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.e4.core.contexts`, version `1.13.300.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.e4.core.contexts;version="1.7.0";uses:="org.osgi.framework"`, `org.eclipse.e4.core.internal.contexts;x-friends:="org.eclipse.e4.core.tests,org.eclipse.e4.ui.workbench,org.eclipse.e4.ui.workbench.swt"`, `org.eclipse.e4.core.internal.contexts.osgi;x-internal:=true`
- Import-Package: `jakarta.inject;version="[2.0.0,3.0.0)"`, `org.osgi.framework;version="[1.6.0,2)"`, `org.osgi.service.event;version="[1.3.0,2.0.0)"`
- Require-Bundle: `org.eclipse.e4.core.di`
- Packaging/source configuration: [runtime/bundles/org.eclipse.e4.core.contexts/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.contexts/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.e4.core.contexts`](packages-10.md#p0292), [`org.eclipse.e4.core.internal.contexts`](packages-10.md#p0299), [`org.eclipse.e4.core.internal.contexts.osgi`](packages-10.md#p0300)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0062

### `runtime/bundles/org.eclipse.e4.core.di`

- Source: [runtime/bundles/org.eclipse.e4.core.di](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.e4.core.di`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.e4.core.di`, version `1.9.1000.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.e4.core.di;version="1.7.0";uses:="org.eclipse.e4.core.di.suppliers"`, `org.eclipse.e4.core.di.suppliers;version="1.7.0"`, `org.eclipse.e4.core.internal.di;x-friends:="org.eclipse.e4.core.contexts,org.eclipse.e4.ui.workbench"`, `org.eclipse.e4.core.internal.di.osgi;x-internal:=true`, `org.eclipse.e4.core.internal.di.shared;x-friends:="org.eclipse.e4.core.contexts,org.eclipse.e4.core.di.extensions.supplier"`
- Import-Package: `org.eclipse.osgi.framework.log;version="1.1.0"`, `org.osgi.framework;version="[1.8.0,2.0.0)"`, `org.osgi.util.tracker;version="[1.5.1,2.0.0)"`
- Require-Bundle: `org.eclipse.e4.core.di.annotations;bundle-version="[1.4.0,2.0.0)";visibility:=reexport`
- Packaging/source configuration: [runtime/bundles/org.eclipse.e4.core.di/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.e4.core.di`](packages-10.md#p0293), [`org.eclipse.e4.core.di.suppliers`](packages-10.md#p0298), [`org.eclipse.e4.core.internal.di`](packages-11.md#p0301), [`org.eclipse.e4.core.internal.di.osgi`](packages-11.md#p0302), [`org.eclipse.e4.core.internal.di.shared`](packages-11.md#p0303)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0063

### `runtime/bundles/org.eclipse.e4.core.di.annotations`

- Source: [runtime/bundles/org.eclipse.e4.core.di.annotations](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di.annotations); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di.annotations/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di.annotations/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.e4.core.di.annotations`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.e4.core.di.annotations`, version `1.8.500.qualifier`, declared name `%Bundle-Name`; execution environment `JavaSE-17`
- Export-Package: `org.eclipse.e4.core.di.annotations;version="1.6.0"`
- Import-Package: `jakarta.inject;version="[2.0.0,3.0.0)"`
- Packaging/source configuration: [runtime/bundles/org.eclipse.e4.core.di.annotations/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di.annotations/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.e4.core.di.annotations`](packages-10.md#p0294)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0064

### `runtime/bundles/org.eclipse.e4.core.di.extensions`

- Source: [runtime/bundles/org.eclipse.e4.core.di.extensions](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di.extensions); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di.extensions/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di.extensions/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.e4.core.di.extensions`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.e4.core.di.extensions`, version `0.18.300.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.e4.core.di.extensions;version="0.16.0"`
- Import-Package: `jakarta.inject;version="[2.0.0,3.0.0)"`, `org.osgi.framework;version="[1.0.0,2.0.0)"`
- Packaging/source configuration: [runtime/bundles/org.eclipse.e4.core.di.extensions/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di.extensions/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.e4.core.di.extensions`](packages-10.md#p0295)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0065

### `runtime/bundles/org.eclipse.e4.core.di.extensions.supplier`

- Source: [runtime/bundles/org.eclipse.e4.core.di.extensions.supplier](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di.extensions.supplier); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di.extensions.supplier/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di.extensions.supplier/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.e4.core.di.extensions.supplier`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.e4.core.di.extensions.supplier`, version `0.17.1200.qualifier`, declared name `%Bundle-Name`; execution environment `JavaSE-17`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.e4.core.di.internal.extensions;version="0.15.0";x-friends:="org.eclipse.e4.ui.di"`, `org.eclipse.e4.core.di.internal.extensions.util;version="0.15.0";x-internal:=true`
- Import-Package: `jakarta.annotation;version="[2.0.0,4.0.0)"`, `org.eclipse.core.runtime.preferences;version="3.3.0"`, `org.eclipse.e4.core.contexts;version="1.6.0"`, `org.eclipse.e4.core.di`, `org.eclipse.e4.core.di.annotations;version="1.6.0"`, `org.eclipse.e4.core.di.extensions;version="0.15.0"`, `org.eclipse.e4.core.di.suppliers;version="[1.7.0,2)"`, `org.osgi.framework;version="[1.8.0,2.0.0)"`, `org.osgi.service.event;version="[1.3.0,2.0.0)"`, `org.osgi.service.log;version="[1.4.0,2)"`, `org.osgi.util.tracker;version="[1.5.3,2.0.0)"`
- Packaging/source configuration: [runtime/bundles/org.eclipse.e4.core.di.extensions.supplier/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.di.extensions.supplier/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.e4.core.di.internal.extensions`](packages-10.md#p0296), [`org.eclipse.e4.core.di.internal.extensions.util`](packages-10.md#p0297)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0066

### `runtime/bundles/org.eclipse.e4.core.services`

- Source: [runtime/bundles/org.eclipse.e4.core.services](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.services); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.services/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.services/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.e4.core.services`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.e4.core.services`, version `2.5.400.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.e4.core.internal.services;x-friends:="org.eclipse.e4.ui.workbench.swt"`, `org.eclipse.e4.core.internal.services.about;x-internal:=true`, `org.eclipse.e4.core.services.about`, `org.eclipse.e4.core.services.adapter;x-friends:="org.eclipse.e4.ui.workbench.swt,org.eclipse.ui.workbench"`, `org.eclipse.e4.core.services.contributions; x-friends:="org.eclipse.e4.ui.workbench,  org.eclipse.e4.ui.css.swt,  org.eclipse.e4.ui.workbench.renderers.swt,  org.eclipse.e4.ui.workbench.swt,  org.eclipse.e4.ui.bindings,  org.eclipse.ui.workbench"`, `org.eclipse.e4.core.services.events`, `org.eclipse.e4.core.services.log; x-friends:="org.eclipse.e4.ui.bindings,  org.eclipse.e4.ui.services,  org.eclipse.e4.ui.workbench,  org.eclipse.e4.ui.workbench.renderers.swt,  org.eclipse.e4.ui.workbench.swt,  org.eclipse.ui.workbench,  org.eclipse.e4.core.commands"`, `org.eclipse.e4.core.services.nls`, `org.eclipse.e4.core.services.statusreporter;x-friends:="org.eclipse.e4.ui.workbench.swt,org.eclipse.e4.ui.progress,org.eclipse.ui.ide"`, `org.eclipse.e4.core.services.translation`
- Import-Package: `jakarta.annotation;version="[2.0.0,4.0.0)"`, `jakarta.inject;version="[2.0.0,3.0.0)"`, `org.eclipse.osgi.service.debug;version="1.1.0"`, `org.eclipse.osgi.service.localization;version="1.1.0"`, `org.eclipse.osgi.util;version="[1.1.0,2.0.0)"`, `org.osgi.framework;version="[1.5.0,2)"`, `org.osgi.service.event;version="[1.4.0,2.0.0)";resolution:=optional`, `org.osgi.service.log;version="[1.4.0,2)"`, `org.osgi.util.tracker;version="[1.5.0,2)"`
- Require-Bundle: `org.eclipse.equinox.common;bundle-version="3.4.0"`, `org.eclipse.equinox.preferences;bundle-version="3.3.0"`, `org.eclipse.core.jobs;bundle-version="3.5.0"`, `org.eclipse.e4.core.di`, `org.eclipse.e4.core.contexts`
- Packaging/source configuration: [runtime/bundles/org.eclipse.e4.core.services/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.e4.core.services/build.properties); observed property starts `output.. = bin/`; `bin.includes = META-INF/,\`; `source.. = src/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.e4.core.internal.services`](packages-11.md#p0304), [`org.eclipse.e4.core.internal.services.about`](packages-11.md#p0305), [`org.eclipse.e4.core.services.about`](packages-11.md#p0316), [`org.eclipse.e4.core.services.adapter`](packages-11.md#p0317), [`org.eclipse.e4.core.services.contributions`](packages-11.md#p0318), [`org.eclipse.e4.core.services.events`](packages-11.md#p0319), [`org.eclipse.e4.core.services.log`](packages-11.md#p0320), [`org.eclipse.e4.core.services.nls`](packages-11.md#p0321), [`org.eclipse.e4.core.services.statusreporter`](packages-11.md#p0322), [`org.eclipse.e4.core.services.translation`](packages-11.md#p0323)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0067

### `runtime/features`

- Source: [runtime/features](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/features); implementation/build; activation must be checked below
- Discovery evidence: parent module declaration; no local descriptor
- Reactor reachability: reachable through parsed non-profile module declarations
- Packaging/source configuration: [runtime/features/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/features/build.properties); observed property starts `pom.model.artifactId=runtime-features`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0068

### `runtime/features/org.eclipse.core.runtime.feature`

- Source: [runtime/features/org.eclipse.core.runtime.feature](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/features/org.eclipse.core.runtime.feature); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/features/org.eclipse.core.runtime.feature/.project), [feature.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/features/org.eclipse.core.runtime.feature/feature.xml)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.runtime.feature`; natures: `org.eclipse.pde.FeatureNature`
- Distribution feature: `org.eclipse.core.runtime.feature`; included plug-ins `org.eclipse.core.runtime`, `org.eclipse.core.variables`, `org.eclipse.core.contenttype`, `org.eclipse.equinox.preferences`, `org.eclipse.core.expressions`, `org.eclipse.core.jobs`; included features `org.eclipse.equinox.core.feature`
- Packaging/source configuration: [runtime/features/org.eclipse.core.runtime.feature/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/features/org.eclipse.core.runtime.feature/build.properties); observed property starts `bin.includes = feature.xml,\`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0069

### `runtime/features/org.eclipse.core.tools-feature`

- Source: [runtime/features/org.eclipse.core.tools-feature](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/features/org.eclipse.core.tools-feature); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/features/org.eclipse.core.tools-feature/.project), [feature.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/features/org.eclipse.core.tools-feature/feature.xml)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.tools-feature`; natures: `org.eclipse.pde.FeatureNature`
- Distribution feature: `org.eclipse.core.tools`; included plug-ins `org.eclipse.core.tools`, `org.eclipse.core.tools.resources`, `org.eclipse.core.resources.spysupport`; included features none declared
- Packaging/source configuration: [runtime/features/org.eclipse.core.tools-feature/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/features/org.eclipse.core.tools-feature/build.properties); observed property starts `bin.includes = feature.xml, feature.properties`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0070

### `runtime/tests`

- Source: [runtime/tests](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests); implementation/build; activation must be checked below
- Discovery evidence: parent module declaration; no local descriptor
- Reactor reachability: reachable through parsed non-profile module declarations
- Packaging/source configuration: [runtime/tests/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/build.properties); observed property starts `pom.model.artifactId=runtime-tests`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0071

### `runtime/tests/org.eclipse.core.contenttype.tests`

- Source: [runtime/tests/org.eclipse.core.contenttype.tests](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.contenttype.tests); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.contenttype.tests/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.contenttype.tests/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.contenttype.tests`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.core.contenttype.tests`, version `1.4.200.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.internal.contenttype.tests`
- Import-Package: `org.assertj.core.api`, `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`
- Require-Bundle: `org.eclipse.core.contenttype;bundle-version="3.6"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`
- Packaging/source configuration: [runtime/tests/org.eclipse.core.contenttype.tests/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.contenttype.tests/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = test.xml,\`; `pom.model.packaging = eclipse-test-plugin`; `pom.model.property.testClass = org.eclipse.core.internal.contenttype.tests.AllContenttypeTests`; `pom.model.property.tycho.surefire.useUIHarness = false`; `pom.model.property.tycho.surefire.useUIThread = false`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.internal.contenttype.tests`](packages-03.md#p0088)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0072

### `runtime/tests/org.eclipse.core.expressions.tests`

- Source: [runtime/tests/org.eclipse.core.expressions.tests](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.expressions.tests); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.expressions.tests/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.expressions.tests/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.expressions.tests`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.core.expressions.tests`, version `3.7.800.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.internal.expressions.tests`
- Import-Package: `org.assertj.core.api`, `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.function;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`, `org.mockito`, `org.mockito.stubbing`
- Require-Bundle: `org.eclipse.core.expressions;bundle-version="[3.4.100,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`
- Packaging/source configuration: [runtime/tests/org.eclipse.core.expressions.tests/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.expressions.tests/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = plugin.xml,\`; `pom.model.property.testClass = org.eclipse.core.internal.expressions.tests.AllExpressionTests`; `pom.model.property.tycho.surefire.useUIHarness = true`; `pom.model.property.tycho.surefire.useUIThread = true`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.ant.core`](packages-01.md#p0004), [`org.eclipse.core.internal.expressions.tests`](packages-04.md#p0093)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0073

### `runtime/tests/org.eclipse.core.tests.harness`

- Source: [runtime/tests/org.eclipse.core.tests.harness](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.harness); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.harness/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.harness/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.tests.harness`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.core.tests.harness`, version `3.17.600.qualifier`, declared name `Eclipse Core Tests Harness`; execution environment `JavaSE-17`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.tests.harness;version="2.0"`, `org.eclipse.core.tests.harness.session`
- Import-Package: `javax.xml.parsers`, `org.apache.felix.scr.info`, `org.osgi.service.event`, `org.osgi.service.component`, `org.osgi.util.promise`, `org.osgi.util.function`, `net.bytebuddy`, `org.apiguardian.api`, `org.assertj.core.api`, `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.condition;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.extension;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.migrationsupport;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.params;version="[5.14.0,6.0.0)"`, `org.junit.platform.commons;version="[1.14.0,2.0.0)"`, `org.junit.platform.runner;version="[1.14.0,2.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`, `org.opentest4j`, `org.w3c.dom`, `org.xml.sax`
- Require-Bundle: `org.junit`, `org.eclipse.test.performance`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.jdt.junit.runtime`, `org.eclipse.jdt.junit4.runtime`, `org.eclipse.jdt.junit5.runtime`, `org.eclipse.pde.junit.runtime`, `junit-platform-launcher;bundle-version="[1.14.0,2.0.0)"`, `junit-platform-engine;bundle-version="[1.14.0,2.0.0)"`, `junit-jupiter-engine;bundle-version="[5.14.0,6.0.0)"`, `junit-vintage-engine;bundle-version="[5.14.0,6.0.0)"`, `junit-platform-suite-commons;bundle-version="[1.14.0,2.0.0)"`, `junit-platform-suite-engine;bundle-version="[1.14.0,2.0.0)"`
- Packaging/source configuration: [runtime/tests/org.eclipse.core.tests.harness/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.harness/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = .,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.tests.harness`](packages-05.md#p0133), [`org.eclipse.core.tests.harness.session`](packages-05.md#p0134), [`org.eclipse.core.tests.harness.session.customization`](packages-05.md#p0135), [`org.eclipse.core.tests.harness.session.samples`](packages-05.md#p0136), [`org.eclipse.core.tests.session`](packages-06.md#p0173)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0074

### `runtime/tests/org.eclipse.core.tests.runtime`

- Source: [runtime/tests/org.eclipse.core.tests.runtime](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.runtime); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.runtime/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.runtime/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.tests.runtime`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.core.tests.runtime`, version `3.21.900.qualifier`, declared name `Eclipse Core Tests Runtime`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.core.tests.runtime.RuntimeTestsPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.tests.internal.preferences`, `org.eclipse.core.tests.internal.runtime`, `org.eclipse.core.tests.runtime`, `org.eclipse.core.tests.runtime.jobs`, `org.eclipse.core.tests.runtime.perf`
- Import-Package: `org.assertj.core.api`, `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.condition;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.extension;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.function;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.io;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`
- Require-Bundle: `org.eclipse.test.performance;resolution:=optional`, `org.eclipse.core.runtime;bundle-version="3.29.0"`, `org.eclipse.core.tests.harness;bundle-version="3.11.0"`
- Packaging/source configuration: [runtime/tests/org.eclipse.core.tests.runtime/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.runtime/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = plugin.xml,\`; `pom.model.packaging = eclipse-test-plugin`; `pom.model.property.testClass = org.eclipse.core.tests.runtime.AutomatedRuntimeTests`; `pom.model.property.defaultSigning-excludeInnerJars = true`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.tests.internal.preferences`](packages-05.md#p0150), [`org.eclipse.core.tests.internal.runtime`](packages-06.md#p0154), [`org.eclipse.core.tests.runtime`](packages-06.md#p0170), [`org.eclipse.core.tests.runtime.jobs`](packages-06.md#p0171), [`org.eclipse.core.tests.runtime.perf`](packages-06.md#p0172)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0075

### `runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/compatibility/bundle01`

- Source: [runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/compatibility/bundle01](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/compatibility/bundle01); test or test fixture (path classification)
- Discovery evidence: [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/compatibility/bundle01/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Bundle identity: `bundle01`, version `1.0.0`, declared name `bundle01`; execution environment `not declared`
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0076

### `runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/platformURL/platform.test.underscore`

- Source: [runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/platformURL/platform.test.underscore](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/platformURL/platform.test.underscore); test or test fixture (path classification)
- Discovery evidence: [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/platformURL/platform.test.underscore/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Bundle identity: `platform.test.underscore`, version `1.1.0.r321_v20060816`, declared name ``; execution environment `not declared`
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0077

### `runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/platformURL/platform_test_underscore`

- Source: [runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/platformURL/platform_test_underscore](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/platformURL/platform_test_underscore); test or test fixture (path classification)
- Discovery evidence: [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/platformURL/platform_test_underscore/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Bundle identity: `platform_test_underscore`, version `1.0.0`, declared name ``; execution environment `not declared`
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0078

### `runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/platformURL/platform_test_underscore_2.0.0`

- Source: [runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/platformURL/platform_test_underscore_2.0.0](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/platformURL/platform_test_underscore_2.0.0); test or test fixture (path classification)
- Discovery evidence: [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.core.tests.runtime/Plugin_Testing/platformURL/platform_test_underscore_2.0.0/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Bundle identity: `platform_test_underscore`, version `2.0.0`, declared name ``; execution environment `not declared`
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0079

### `runtime/tests/org.eclipse.e4.core.javax.tests`

- Source: [runtime/tests/org.eclipse.e4.core.javax.tests](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.e4.core.javax.tests); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.e4.core.javax.tests/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.e4.core.javax.tests/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.e4.core.javax.tests`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.e4.core.javax.tests`, version `1.3.300.qualifier`, declared name `E4 Core Tests`; execution environment `JavaSE-17`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.e4.core.internal.tests.contexts.inject;x-internal:=true`, `org.eclipse.e4.core.javax.tests`
- Import-Package: `javax.annotation;version="[1.3.0,2.0.0)"`, `javax.inject;version="[1.0.0,2.0.0)"`, `org.junit.jupiter.api;version="[5.12.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.12.0,2.0.0)"`
- Require-Bundle: `org.eclipse.e4.core.di`, `org.eclipse.e4.core.contexts`
- Packaging/source configuration: [runtime/tests/org.eclipse.e4.core.javax.tests/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.e4.core.javax.tests/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`; `pom.model.property.testClass = org.eclipse.e4.core.javax.tests.CoreTestSuite`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.e4.core.internal.tests.contexts.inject`](packages-11.md#p0309), [`org.eclipse.e4.core.javax.tests`](packages-11.md#p0315)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0080

### `runtime/tests/org.eclipse.e4.core.tests`

- Source: [runtime/tests/org.eclipse.e4.core.tests](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.e4.core.tests); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.e4.core.tests/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.e4.core.tests/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.e4.core.tests/pom.xml)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.e4.core.tests`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Declared Maven coordinates: `org.eclipse.platform:org.eclipse.e4.core.tests:1.3.700-SNAPSHOT`; packaging `eclipse-test-plugin`; parent `org.eclipse.platform:eclipse.platform.runtime:4.42.0-SNAPSHOT`, relative path `../..`
- Bundle identity: `org.eclipse.e4.core.tests`, version `1.3.700.qualifier`, declared name `E4 Core Tests`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.e4.core.internal.tests.CoreTestsActivator`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.e4.core.internal.tests;x-internal:=true`, `org.eclipse.e4.core.internal.tests.about;x-internal:=true`, `org.eclipse.e4.core.internal.tests.contexts;x-internal:=true`, `org.eclipse.e4.core.internal.tests.contexts.inject;x-internal:=true`, `org.eclipse.e4.core.internal.tests.contexts.performance;x-internal:=true`, `org.eclipse.e4.core.internal.tests.di;x-internal:=true`, `org.eclipse.e4.core.internal.tests.di.extensions;x-internal:=true`, `org.eclipse.e4.core.internal.tests.manual;x-internal:=true`, `org.eclipse.e4.core.internal.tests.nls;x-internal:=true`, `org.eclipse.e4.core.tests`
- Import-Package: `jakarta.annotation;version="[2.0.0,4.0.0)"`, `jakarta.inject;version="[2.0.0,3.0.0)"`, `org.assertj.core.api`, `org.eclipse.osgi.service.datalocation`, `org.eclipse.osgi.service.debug`, `org.junit.jupiter.api;version="[5.12.0,6.0.0)"`, `org.junit.jupiter.api.function;version="[5.12.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.12.0,2.0.0)"`, `org.osgi.framework;version="[1.10.0,2.0.0)"`, `org.osgi.service.component;version="1.3.0"`, `org.osgi.service.component.propertytypes;version="[1.4.0,2.0.0)"`, `org.osgi.service.event;version="1.3.0"`, `org.osgi.util.tracker;version="[1.5.0,2.0.0)"`
- Require-Bundle: `org.eclipse.equinox.preferences;bundle-version="3.3.0"`, `org.eclipse.e4.core.di`, `org.eclipse.e4.core.di.extensions`, `org.eclipse.e4.core.di.extensions.supplier`, `org.eclipse.e4.core.contexts`, `org.eclipse.equinox.registry;bundle-version="3.5.0"`, `org.eclipse.core.tests.harness;bundle-version="3.6.0"`, `org.eclipse.e4.core.services;bundle-version="1.1.0"`
- Packaging/source configuration: [runtime/tests/org.eclipse.e4.core.tests/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/tests/org.eclipse.e4.core.tests/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.e4.core.internal.tests`](packages-11.md#p0306), [`org.eclipse.e4.core.internal.tests.about`](packages-11.md#p0307), [`org.eclipse.e4.core.internal.tests.contexts`](packages-11.md#p0308), [`org.eclipse.e4.core.internal.tests.contexts.inject`](packages-11.md#p0309), [`org.eclipse.e4.core.internal.tests.contexts.performance`](packages-11.md#p0310), [`org.eclipse.e4.core.internal.tests.di`](packages-11.md#p0311), [`org.eclipse.e4.core.internal.tests.di.extensions`](packages-11.md#p0312), [`org.eclipse.e4.core.internal.tests.manual`](packages-11.md#p0313), [`org.eclipse.e4.core.internal.tests.nls`](packages-11.md#p0314), [`org.eclipse.e4.core.tests`](packages-11.md#p0324)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0081

### `team`

- Source: [team](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team); implementation/build; activation must be checked below
- Discovery evidence: [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/pom.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Declared Maven coordinates: `org.eclipse.platform:eclipse.platform.team:4.42.0-SNAPSHOT`; packaging `pom`; parent `org.eclipse.platform:eclipse.platform:4.42.0-SNAPSHOT`, relative path `omitted; effective parent convention applies`
- Aggregates: [`team/bundles`](projects-03.md#u0082) (non-profile declaration), [`team/tests`](projects-04.md#u0096) (non-profile declaration), [`team/examples/org.eclipse.compare.examples`](projects-04.md#u0093) (non-profile declaration), [`team/examples/org.eclipse.compare.examples.xml`](projects-04.md#u0094) (non-profile declaration), [`team/examples/org.eclipse.team.examples.filesystem`](projects-04.md#u0095) (non-profile declaration)
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0082

### `team/bundles`

- Source: [team/bundles](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles); implementation/build; activation must be checked below
- Discovery evidence: parent module declaration; no local descriptor
- Reactor reachability: reachable through parsed non-profile module declarations
- Packaging/source configuration: [team/bundles/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/build.properties); observed property starts `pom.model.artifactId=team-bundles`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0083

### `team/bundles/org.eclipse.compare`

- Source: [team/bundles/org.eclipse.compare](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.compare); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.compare/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.compare/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.compare`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.compare`, version `3.12.300.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.compare.internal.CompareUIPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.compare; uses:="org.eclipse.compare.internal,  org.eclipse.compare.structuremergeviewer,  org.eclipse.core.resources,  org.eclipse.core.runtime,  org.eclipse.core.runtime.content,  org.eclipse.jface.action,  org.eclipse.jface.operation,  org.eclipse.jface.preference,  org.eclipse.jface.resource,  org.eclipse.jface.text,  org.eclipse.jface.util,  org.eclipse.jface.viewers,  org.eclipse.swt.custom,  org.eclipse.swt.graphics,  org.eclipse.swt.widgets,  org.eclipse.ui,  org.eclipse.ui.plugin,  org.eclipse.ui.services,  org.eclipse.ui.texteditor"`, `org.eclipse.compare.contentmergeviewer; uses:="org.eclipse.compare,  org.eclipse.compare.internal,  org.eclipse.compare.rangedifferencer,  org.eclipse.compare.structuremergeviewer,  org.eclipse.core.runtime,  org.eclipse.jface.action,  org.eclipse.jface.text,  org.eclipse.jface.text.source,  org.eclipse.jface.util,  org.eclipse.jface.viewers,  org.eclipse.swt.events,  org.eclipse.swt.graphics,  org.eclipse.swt.widgets,  org.eclipse.ui"`, `org.eclipse.compare.internal;x-friends:="org.eclipse.team.ui,org.eclipse.team.tests.core"`, `org.eclipse.compare.internal.merge;x-internal:=true`, `org.eclipse.compare.internal.patch;x-friends:="org.eclipse.team.ui"`, `org.eclipse.compare.patch; uses:="org.eclipse.compare,  org.eclipse.core.resources,  org.eclipse.core.runtime,  org.eclipse.jface.resource,  org.eclipse.swt.widgets,  org.eclipse.ui"`, `org.eclipse.compare.structuremergeviewer; uses:="org.eclipse.compare,  org.eclipse.compare.contentmergeviewer,  org.eclipse.core.runtime,  org.eclipse.jface.action,  org.eclipse.jface.text,  org.eclipse.jface.util,  org.eclipse.jface.viewers,  org.eclipse.swt.events,  org.eclipse.swt.graphics,  org.eclipse.swt.widgets,  org.eclipse.ui,  org.eclipse.ui.services,  org.eclipse.ui.texteditor"`, `org.eclipse.compare.unifieddiff;x-internal:=true`, `org.eclipse.compare.unifieddiff.internal;x-friends:="org.eclipse.team.tests.core"`
- Require-Bundle: `org.eclipse.ui;bundle-version="[3.206.0,4.0.0)"`, `org.eclipse.core.resources;bundle-version="[3.4.0,4.0.0)"`, `org.eclipse.jface.text;bundle-version="[3.8.0,4.0.0)"`, `org.eclipse.ui.ide;bundle-version="[3.3.0,4.0.0)"`, `org.eclipse.ui.views;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.workbench.texteditor;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.core.expressions;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.ui.editors;bundle-version="[3.19.0,4.0.0)"`, `org.eclipse.ui.forms;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.compare.core;bundle-version="[3.5.0,4.0.0)";visibility:=reexport`, `org.eclipse.ui.navigator;bundle-version="[3.2.0,4.0.0)";resolution:=optional`
- Packaging/source configuration: [team/bundles/org.eclipse.compare/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.compare/build.properties); observed property starts `source.. = compare/`; `output.. = bin/`; `bin.includes = icons/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.compare`](packages-03.md#p0062), [`org.eclipse.compare.contentmergeviewer`](packages-03.md#p0063), [`org.eclipse.compare.internal`](packages-03.md#p0067), [`org.eclipse.compare.internal.merge`](packages-03.md#p0070), [`org.eclipse.compare.internal.patch`](packages-03.md#p0071), [`org.eclipse.compare.patch`](packages-03.md#p0073), [`org.eclipse.compare.structuremergeviewer`](packages-03.md#p0075), [`org.eclipse.compare.unifieddiff`](packages-03.md#p0078), [`org.eclipse.compare.unifieddiff.internal`](packages-03.md#p0079)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0084

### `team/bundles/org.eclipse.compare.core`

- Source: [team/bundles/org.eclipse.compare.core](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.compare.core); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.compare.core/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.compare.core/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.compare.core`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.compare.core`, version `3.8.900.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.compare.internal.core;x-friends:="org.eclipse.compare"`, `org.eclipse.compare.internal.core.patch;x-friends:="org.eclipse.compare,org.eclipse.team.ui"`, `org.eclipse.compare.patch;core=split;mandatory:=core;uses:="org.eclipse.core.runtime"`, `org.eclipse.compare.rangedifferencer;uses:="org.eclipse.core.runtime"`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`
- Packaging/source configuration: [team/bundles/org.eclipse.compare.core/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.compare.core/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.compare.internal.core`](packages-03.md#p0068), [`org.eclipse.compare.internal.core.patch`](packages-03.md#p0069), [`org.eclipse.compare.patch`](packages-03.md#p0073), [`org.eclipse.compare.rangedifferencer`](packages-03.md#p0074)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0085

### `team/bundles/org.eclipse.compare.win32`

- Source: [team/bundles/org.eclipse.compare.win32](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.compare.win32); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.compare.win32/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.compare.win32/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.compare.win32`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.compare.win32`, version `1.3.1000.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.compare.internal.win32.Activator`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.compare.internal.win32;x-internal:=true`
- Require-Bundle: `org.eclipse.compare;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.core.filesystem;bundle-version="[1.2.0,2.0.0)"`, `org.eclipse.ui.forms;bundle-version="[3.4.0,4.0.0)"`, `org.eclipse.ui.workbench;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.jface;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.core.resources;bundle-version="[3.5.0,4.0.0)"`
- Packaging/source configuration: [team/bundles/org.eclipse.compare.win32/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.compare.win32/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`; `pom.model.property.skipAPIAnalysis = true`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.compare.internal.win32`](packages-03.md#p0072)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0086

### `team/bundles/org.eclipse.core.net`

- Source: [team/bundles/org.eclipse.core.net](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.core.net); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.core.net/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.core.net/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.net`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.core.net`, version `1.5.900.qualifier`, declared name `%PLUGIN_NAME`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.core.internal.net.Activator`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.internal.net;x-friends:="org.eclipse.ui.net"`, `org.eclipse.core.net.proxy`
- Import-Package: `com.sun.jna;version="[5.14.0,6.0.0)"`, `com.sun.jna.platform.win32;version="[5.14.0,6.0.0)"`, `com.sun.jna.ptr;version="[5.14.0,6.0.0)"`, `com.sun.jna.win32;version="[5.14.0,6.0.0)"`
- Require-Bundle: `org.eclipse.equinox.security;bundle-version="[1.0.0,2.0.0)"`, `org.eclipse.equinox.common;bundle-version="3.12.0"`, `org.eclipse.equinox.preferences;bundle-version="3.2.200"`, `org.eclipse.osgi;bundle-version="3.4.0"`, `org.eclipse.equinox.registry;bundle-version="3.4.0"`
- Packaging/source configuration: [team/bundles/org.eclipse.core.net/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.core.net/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.internal.net`](packages-04.md#p0104), [`org.eclipse.core.net.internal.proxy.linux`](packages-04.md#p0118), [`org.eclipse.core.net.internal.proxy.win32`](packages-04.md#p0119), [`org.eclipse.core.net.proxy`](packages-04.md#p0120)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0087

### `team/bundles/org.eclipse.jsch.core`

- Source: [team/bundles/org.eclipse.jsch.core](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.jsch.core); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.jsch.core/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.jsch.core/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.jsch.core`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.jsch.core`, version `1.5.900.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.jsch.internal.core.JSchCorePlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.jsch.core;uses:="com.jcraft.jsch,org.eclipse.core.runtime"`, `org.eclipse.jsch.internal.core;x-friends:="org.eclipse.jsch.ui"`
- Import-Package: `com.jcraft.jsch;version="[0.1.50,3.0.0)"`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.core.net;bundle-version="[1.0.0,2.0.0)"`
- Packaging/source configuration: [team/bundles/org.eclipse.jsch.core/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.jsch.core/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.jsch.core`](packages-13.md#p0373), [`org.eclipse.jsch.internal.core`](packages-13.md#p0374)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0088

### `team/bundles/org.eclipse.jsch.ui`

- Source: [team/bundles/org.eclipse.jsch.ui](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.jsch.ui); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.jsch.ui/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.jsch.ui/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.jsch.ui`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.jsch.ui`, version `1.6.200.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Lifecycle activator: `org.eclipse.jsch.internal.ui.JSchUIPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.jsch.internal.ui;x-internal:=true`, `org.eclipse.jsch.internal.ui.authenticator;x-internal:=true`, `org.eclipse.jsch.internal.ui.preference;x-internal:=true`, `org.eclipse.jsch.ui;uses:="com.jcraft.jsch"`
- Import-Package: `com.jcraft.jsch;version="[0.1.50,3.0.0)"`
- Require-Bundle: `org.eclipse.ui;bundle-version="[3.208.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.jsch.core;bundle-version="[1.2.0,2.0.0)"`
- Packaging/source configuration: [team/bundles/org.eclipse.jsch.ui/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.jsch.ui/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.jsch.internal.ui`](packages-13.md#p0375), [`org.eclipse.jsch.internal.ui.authenticator`](packages-13.md#p0376), [`org.eclipse.jsch.internal.ui.preference`](packages-13.md#p0377), [`org.eclipse.jsch.ui`](packages-13.md#p0378)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0089

### `team/bundles/org.eclipse.team.core`

- Source: [team/bundles/org.eclipse.team.core](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.team.core); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.team.core/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.team.core/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.team.core`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.team.core`, version `3.10.1200.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.team.internal.core.TeamPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.team.core; uses:="org.eclipse.core.resources,  org.eclipse.core.resources.team,  org.eclipse.core.runtime,  org.eclipse.core.runtime.content,  org.eclipse.team.core.history,  org.eclipse.team.core.mapping,  org.eclipse.team.core.subscribers"`, `org.eclipse.team.core.diff;uses:="org.eclipse.core.runtime"`, `org.eclipse.team.core.diff.provider;uses:="org.eclipse.core.runtime,org.eclipse.team.core.diff"`, `org.eclipse.team.core.history;uses:="org.eclipse.core.filesystem,org.eclipse.core.resources,org.eclipse.core.runtime"`, `org.eclipse.team.core.history.provider;uses:="org.eclipse.core.resources,org.eclipse.team.core.history"`, `org.eclipse.team.core.importing.provisional;x-friends:="org.eclipse.pde.core,org.eclipse.team.cvs.core"`, `org.eclipse.team.core.mapping; uses:="org.eclipse.core.resources,  org.eclipse.core.resources.mapping,  org.eclipse.core.runtime,  org.eclipse.core.runtime.content,  org.eclipse.core.runtime.jobs,  org.eclipse.team.core,  org.eclipse.team.core.diff,  org.eclipse.team.core.history"`, `org.eclipse.team.core.mapping.provider; uses:="org.eclipse.core.resources,  org.eclipse.core.resources.mapping,  org.eclipse.core.runtime,  org.eclipse.core.runtime.jobs,  org.eclipse.team.core,  org.eclipse.team.core.diff,  org.eclipse.team.core.diff.provider,  org.eclipse.team.core.history,  org.eclipse.team.core.mapping"`, `org.eclipse.team.core.subscribers; uses:="org.eclipse.core.resources,  org.eclipse.core.resources.mapping,  org.eclipse.core.runtime,  org.eclipse.team.core.diff,  org.eclipse.team.core.mapping,  org.eclipse.team.core.mapping.provider,  org.eclipse.team.core.synchronize,  org.eclipse.team.core.variants"`, `org.eclipse.team.core.synchronize; uses:="org.eclipse.core.resources,  org.eclipse.core.runtime,  org.eclipse.team.core,  org.eclipse.team.core.variants"`, `org.eclipse.team.core.variants; uses:="org.eclipse.core.resources,  org.eclipse.core.runtime,  org.eclipse.team.core.subscribers,  org.eclipse.team.core.synchronize"`, `org.eclipse.team.internal.core; x-friends:="org.eclipse.team.cvs.core,  org.eclipse.team.cvs.ssh2,  org.eclipse.team.cvs.ui,  org.eclipse.team.ui,  org.eclipse.team.tests.core"`, `org.eclipse.team.internal.core.history; x-friends:="org.eclipse.team.cvs.core,  org.eclipse.team.cvs.ssh2,  org.eclipse.team.cvs.ui,  org.eclipse.team.ui,  org.eclipse.team.tests.core"`, `org.eclipse.team.internal.core.importing;x-internal:=true`, `org.eclipse.team.internal.core.mapping; x-friends:="org.eclipse.team.cvs.core,  org.eclipse.team.cvs.ssh2,  org.eclipse.team.cvs.ui,  org.eclipse.team.ui,  org.eclipse.team.tests.core"`, `org.eclipse.team.internal.core.subscribers; x-friends:="org.eclipse.team.cvs.core,  org.eclipse.team.cvs.ssh2,  org.eclipse.team.cvs.ui,  org.eclipse.team.ui,  org.eclipse.team.tests.core"`
- Require-Bundle: `org.eclipse.core.resources;bundle-version="[3.17.0,4.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.core.filesystem;bundle-version="[1.1.0,2.0.0)"`, `org.eclipse.compare.core;bundle-version="[3.5.200,4.0.0)"`
- Packaging/source configuration: [team/bundles/org.eclipse.team.core/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.team.core/build.properties); observed property starts `bin.includes = about.html,\`; `source.. = src/`; `output.. = bin/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.team.core`](packages-13.md#p0380), [`org.eclipse.team.core.diff`](packages-13.md#p0381), [`org.eclipse.team.core.diff.provider`](packages-13.md#p0382), [`org.eclipse.team.core.history`](packages-13.md#p0383), [`org.eclipse.team.core.history.provider`](packages-13.md#p0384), [`org.eclipse.team.core.importing.provisional`](packages-13.md#p0385), [`org.eclipse.team.core.mapping`](packages-13.md#p0386), [`org.eclipse.team.core.mapping.provider`](packages-13.md#p0387), [`org.eclipse.team.core.subscribers`](packages-13.md#p0388), [`org.eclipse.team.core.synchronize`](packages-13.md#p0389), [`org.eclipse.team.core.variants`](packages-13.md#p0390), [`org.eclipse.team.internal.core`](packages-14.md#p0402), [`org.eclipse.team.internal.core.history`](packages-14.md#p0403), [`org.eclipse.team.internal.core.importing`](packages-14.md#p0404), [`org.eclipse.team.internal.core.mapping`](packages-14.md#p0405), [`org.eclipse.team.internal.core.subscribers`](packages-14.md#p0406)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0090

### `team/bundles/org.eclipse.team.genericeditor.diff.extension`

- Source: [team/bundles/org.eclipse.team.genericeditor.diff.extension](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.team.genericeditor.diff.extension); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.team.genericeditor.diff.extension/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.team.genericeditor.diff.extension/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.team.genericeditor.diff.extension`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.team.genericeditor.diff.extension`, version `1.3.100.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Export-Package: `org.eclipse.team.internal.genericeditor.diff.extension.partitioner;x-internal:=true`, `org.eclipse.team.internal.genericeditor.diff.extension.presentation;x-internal:=true`, `org.eclipse.team.internal.genericeditor.diff.extension.rules;x-internal:=true`
- Require-Bundle: `org.eclipse.core.filebuffers`, `org.eclipse.jface.text;bundle-version="[3.11.100,4.0.0)"`, `org.eclipse.ui;bundle-version="[3.208.0,4.0.0)"`, `org.eclipse.ui.genericeditor;bundle-version="1.4.100"`
- Packaging/source configuration: [team/bundles/org.eclipse.team.genericeditor.diff.extension/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/team/bundles/org.eclipse.team.genericeditor.diff.extension/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = plugin.xml,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.team.internal.genericeditor.diff.extension.partitioner`](packages-14.md#p0407), [`org.eclipse.team.internal.genericeditor.diff.extension.presentation`](packages-14.md#p0408), [`org.eclipse.team.internal.genericeditor.diff.extension.rules`](packages-14.md#p0409)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

