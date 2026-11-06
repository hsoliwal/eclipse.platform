# Project/module records 2

> Inspected source revision: `0de5d9acb5cff6b133cb1c55f6844720088c7d92`. Source/manifest observation only; builds and tests were not run.

[Reference home](README.md) · [Selected contracts](CONTRACTS.md) · [Coverage](COVERAGE.md)

## U0031

### `resources/bundles/org.eclipse.core.filesystem`

- Source: [resources/bundles/org.eclipse.core.filesystem](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.filesystem`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.core.filesystem`, version `1.12.0.qualifier`, declared name `%pluginName`; execution environment `JavaSE-25`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.filesystem;uses:="org.eclipse.core.runtime"`, `org.eclipse.core.filesystem.provider;uses:="org.eclipse.core.filesystem,org.eclipse.core.runtime"`, `org.eclipse.core.internal.filesystem;x-internal:=true`, `org.eclipse.core.internal.filesystem.local;x-internal:=true`, `org.eclipse.core.internal.filesystem.local.unix;x-internal:=true`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`
- Packaging/source configuration: [resources/bundles/org.eclipse.core.filesystem/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem/build.properties); observed property starts `source.. = src/,\`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`com.microsoft.windows`](packages-01.md#p0001), [`org.eclipse.core.filesystem`](packages-03.md#p0085), [`org.eclipse.core.filesystem.provider`](packages-03.md#p0086), [`org.eclipse.core.internal.filesystem`](packages-04.md#p0095), [`org.eclipse.core.internal.filesystem.local`](packages-04.md#p0096), [`org.eclipse.core.internal.filesystem.local.linux`](packages-04.md#p0097), [`org.eclipse.core.internal.filesystem.local.nio`](packages-04.md#p0098), [`org.eclipse.core.internal.filesystem.local.unix`](packages-04.md#p0099)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0032

### `resources/bundles/org.eclipse.core.filesystem.linux.aarch64`

- Source: [resources/bundles/org.eclipse.core.filesystem.linux.aarch64](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.aarch64); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.aarch64/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.aarch64/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.aarch64/pom.xml)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.filesystem.linux.aarch64`; natures: `org.eclipse.pde.PluginNature`
- Declared Maven coordinates: `org.eclipse.platform:org.eclipse.core.filesystem.linux.aarch64:1.4.200-SNAPSHOT`; packaging `eclipse-plugin`; parent `org.eclipse.platform:eclipse.platform.resources:4.42.0-SNAPSHOT`, relative path `../../`
- Bundle identity: `org.eclipse.core.filesystem.linux.aarch64`, version `1.4.200.qualifier`, declared name `%fragmentName`; execution environment `not declared`
- Fragment host: `org.eclipse.core.filesystem;bundle-version="[1.1.0,2.0.0)"`
- Packaging/source configuration: [resources/bundles/org.eclipse.core.filesystem.linux.aarch64/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.aarch64/build.properties); observed property starts `bin.includes = META-INF/,\`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0033

### `resources/bundles/org.eclipse.core.filesystem.linux.loongarch64`

- Source: [resources/bundles/org.eclipse.core.filesystem.linux.loongarch64](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.loongarch64); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.loongarch64/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.loongarch64/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.loongarch64/pom.xml)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.filesystem.linux.loongarch64`; natures: `org.eclipse.pde.PluginNature`
- Declared Maven coordinates: `org.eclipse.platform:org.eclipse.core.filesystem.linux.loongarch64:1.4.200-SNAPSHOT`; packaging `eclipse-plugin`; parent `org.eclipse.platform:eclipse.platform.resources:4.42.0-SNAPSHOT`, relative path `../../`
- Bundle identity: `org.eclipse.core.filesystem.linux.loongarch64`, version `1.4.200.qualifier`, declared name `%fragmentName`; execution environment `not declared`
- Fragment host: `org.eclipse.core.filesystem;bundle-version="[1.1.0,2.0.0)"`
- Packaging/source configuration: [resources/bundles/org.eclipse.core.filesystem.linux.loongarch64/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.loongarch64/build.properties); observed property starts `bin.includes = META-INF/,\`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0034

### `resources/bundles/org.eclipse.core.filesystem.linux.ppc64le`

- Source: [resources/bundles/org.eclipse.core.filesystem.linux.ppc64le](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.ppc64le); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.ppc64le/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.ppc64le/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.ppc64le/pom.xml)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.filesystem.linux.ppc64le`; natures: `org.eclipse.pde.PluginNature`
- Declared Maven coordinates: `org.eclipse.platform:org.eclipse.core.filesystem.linux.ppc64le:1.4.200-SNAPSHOT`; packaging `eclipse-plugin`; parent `org.eclipse.platform:eclipse.platform.resources:4.42.0-SNAPSHOT`, relative path `../../`
- Bundle identity: `org.eclipse.core.filesystem.linux.ppc64le`, version `1.4.200.qualifier`, declared name `%fragmentName`; execution environment `not declared`
- Fragment host: `org.eclipse.core.filesystem;bundle-version="[1.1.0,2.0.0)"`
- Packaging/source configuration: [resources/bundles/org.eclipse.core.filesystem.linux.ppc64le/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.ppc64le/build.properties); observed property starts `bin.includes = META-INF/,\`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0035

### `resources/bundles/org.eclipse.core.filesystem.linux.x86_64`

- Source: [resources/bundles/org.eclipse.core.filesystem.linux.x86_64](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.x86_64); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.x86_64/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.x86_64/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.x86_64/pom.xml)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.filesystem.linux.x86_64`; natures: `org.eclipse.pde.PluginNature`
- Declared Maven coordinates: `org.eclipse.platform:org.eclipse.core.filesystem.linux.x86_64:1.2.500-SNAPSHOT`; packaging `eclipse-plugin`; parent `org.eclipse.platform:eclipse.platform.resources:4.42.0-SNAPSHOT`, relative path `../../`
- Bundle identity: `org.eclipse.core.filesystem.linux.x86_64`, version `1.2.500.qualifier`, declared name `%fragmentName`; execution environment `not declared`
- Fragment host: `org.eclipse.core.filesystem;bundle-version="[1.11.500,2.0.0)"`
- Packaging/source configuration: [resources/bundles/org.eclipse.core.filesystem.linux.x86_64/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.linux.x86_64/build.properties); observed property starts `bin.includes = META-INF/,\`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0036

### `resources/bundles/org.eclipse.core.filesystem.macosx`

- Source: [resources/bundles/org.eclipse.core.filesystem.macosx](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.macosx); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.macosx/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.macosx/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.macosx/pom.xml)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.filesystem.macosx`; natures: 
- Declared Maven coordinates: `org.eclipse.platform:org.eclipse.core.filesystem.macosx:1.3.400-SNAPSHOT`; packaging `eclipse-plugin`; parent `org.eclipse.platform:eclipse.platform.resources:4.42.0-SNAPSHOT`, relative path `../../`
- Bundle identity: `org.eclipse.core.filesystem.macosx`, version `1.3.400.qualifier`, declared name `%fragmentName`; execution environment `not declared`
- Fragment host: `org.eclipse.core.filesystem;bundle-version="[1.7.200,2.0.0)"`
- Packaging/source configuration: [resources/bundles/org.eclipse.core.filesystem.macosx/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.filesystem.macosx/build.properties); observed property starts `bin.includes = os/,\`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0037

### `resources/bundles/org.eclipse.core.resources`

- Source: [resources/bundles/org.eclipse.core.resources](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.resources); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.resources/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.resources/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.resources`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.core.resources`, version `3.24.200.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.core.resources.ResourcesPlugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.internal.dtree;x-internal:=true`, `org.eclipse.core.internal.events;x-internal:=true`, `org.eclipse.core.internal.localstore;x-internal:=true`, `org.eclipse.core.internal.properties;x-internal:=true`, `org.eclipse.core.internal.propertytester;x-internal:=true`, `org.eclipse.core.internal.refresh;x-friends:="org.eclipse.ui.ide.application"`, `org.eclipse.core.internal.resources;x-friends:="org.eclipse.ui.ide.application,org.eclipse.ui.ide"`, `org.eclipse.core.internal.resources.mapping;x-internal:=true`, `org.eclipse.core.internal.resources.projectvariables;x-internal:=true`, `org.eclipse.core.internal.resources.refresh.win32;x-internal:=true`, `org.eclipse.core.internal.utils;x-friends:="org.eclipse.core.tools"`, `org.eclipse.core.internal.watson;x-internal:=true`, `org.eclipse.core.resources; uses:="org.eclipse.core.internal.events,  org.eclipse.core.internal.resources,  org.eclipse.core.runtime,  org.eclipse.core.runtime.content,  org.eclipse.core.runtime.jobs,  org.eclipse.core.runtime.preferences,  org.osgi.framework"`, `org.eclipse.core.resources.filtermatchers;uses:="org.eclipse.core.filesystem,org.eclipse.core.resources"`, `org.eclipse.core.resources.mapping;uses:="org.eclipse.core.resources,org.eclipse.core.runtime"`, `org.eclipse.core.resources.refresh;uses:="org.eclipse.core.internal.refresh,org.eclipse.core.resources,org.eclipse.core.runtime"`, `org.eclipse.core.resources.team; uses:="org.eclipse.core.internal.resources,  org.eclipse.core.resources,  org.eclipse.core.runtime,  org.eclipse.core.runtime.jobs"`, `org.eclipse.core.resources.undo.snapshot;uses:="org.eclipse.core.resources,org.eclipse.core.runtime"`, `org.eclipse.core.resources.variableresolvers;uses:="org.eclipse.core.resources"`
- Import-Package: `com.sun.jna;version="[5.17.0,6.0.0)"`, `org.eclipse.osgi.service.datalocation;version="[1.4.0,2.0.0)"`
- Require-Bundle: `org.eclipse.ant.core;bundle-version="[3.7.0,4.0.0)";resolution:=optional`, `org.eclipse.core.expressions;bundle-version="[3.9.0,4.0.0)"`, `org.eclipse.core.filesystem;bundle-version="[1.11.0,2.0.0)"`, `org.eclipse.core.runtime;bundle-version="[3.34.0,4.0.0)"`
- Packaging/source configuration: [resources/bundles/org.eclipse.core.resources/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.resources/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `source.ant_tasks/resources-ant.jar = src_ant/`; `output.ant_tasks/resources-ant.jar = bin_ant/`; `bin.includes = plugin.xml,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.internal.dtree`](packages-03.md#p0089), [`org.eclipse.core.internal.events`](packages-03.md#p0090), [`org.eclipse.core.internal.localstore`](packages-04.md#p0103), [`org.eclipse.core.internal.properties`](packages-04.md#p0106), [`org.eclipse.core.internal.propertytester`](packages-04.md#p0107), [`org.eclipse.core.internal.refresh`](packages-04.md#p0108), [`org.eclipse.core.internal.resources`](packages-04.md#p0109), [`org.eclipse.core.internal.resources.mapping`](packages-04.md#p0110), [`org.eclipse.core.internal.resources.projectvariables`](packages-04.md#p0111), [`org.eclipse.core.internal.resources.refresh.win32`](packages-04.md#p0112), [`org.eclipse.core.internal.resources.undo.snapshot`](packages-04.md#p0113), [`org.eclipse.core.internal.utils`](packages-04.md#p0115), [`org.eclipse.core.internal.watson`](packages-04.md#p0117), [`org.eclipse.core.resources`](packages-05.md#p0121), [`org.eclipse.core.resources.ant`](packages-05.md#p0122), [`org.eclipse.core.resources.filtermatchers`](packages-05.md#p0123), [`org.eclipse.core.resources.mapping`](packages-05.md#p0124), [`org.eclipse.core.resources.refresh`](packages-05.md#p0125), [`org.eclipse.core.resources.team`](packages-05.md#p0126), [`org.eclipse.core.resources.undo.snapshot`](packages-05.md#p0127), [`org.eclipse.core.resources.variableresolvers`](packages-05.md#p0128)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0038

### `resources/bundles/org.eclipse.core.resources.spysupport`

- Source: [resources/bundles/org.eclipse.core.resources.spysupport](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.resources.spysupport); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.resources.spysupport/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.resources.spysupport/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.resources.spysupport`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.core.resources.spysupport`, version `1.6.0.qualifier`, declared name `Core Resource Management Spy Support Fragment`; execution environment `JavaSE-17`
- Fragment host: `org.eclipse.core.resources;bundle-version="[3.0.0,4.0.0)"`
- Export-Package: `org.eclipse.core.internal.dtree;x-friends:="org.eclipse.core.tools.resources"`, `org.eclipse.core.internal.properties;x-friends:="org.eclipse.core.tools.resources"`, `org.eclipse.core.internal.resources;x-friends:="org.eclipse.core.tools.resources"`
- Packaging/source configuration: [resources/bundles/org.eclipse.core.resources.spysupport/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.resources.spysupport/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes=about.html, META-INF/,.`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.internal.dtree`](packages-03.md#p0089), [`org.eclipse.core.internal.resources`](packages-04.md#p0109)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0039

### `resources/bundles/org.eclipse.core.tools.resources`

- Source: [resources/bundles/org.eclipse.core.tools.resources](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.tools.resources); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.tools.resources/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.tools.resources/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.tools.resources/pom.xml)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.tools.resources`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Declared Maven coordinates: `org.eclipse.platform:org.eclipse.core.tools.resources:1.8.300-SNAPSHOT`; packaging `eclipse-plugin`; parent `org.eclipse.platform:eclipse.platform.resources:4.42.0-SNAPSHOT`, relative path `../../`
- Bundle identity: `org.eclipse.core.tools.resources`, version `1.8.300.qualifier`, declared name `Core Resources Tools`; execution environment `JavaSE-21`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.tools.resources; uses:="org.eclipse.core.internal.events,  org.eclipse.core.internal.resources,  org.eclipse.core.resources,  org.eclipse.core.tools,  org.eclipse.jface.action,  org.eclipse.jface.text,  org.eclipse.jface.viewers,  org.eclipse.swt.widgets,  org.eclipse.ui,  org.eclipse.ui.part"`, `org.eclipse.core.tools.resources.markers; uses:="org.eclipse.core.resources,  org.eclipse.core.runtime,  org.eclipse.jface.action,  org.eclipse.jface.resource,  org.eclipse.jface.viewers,  org.eclipse.swt.widgets,  org.eclipse.ui,  org.eclipse.ui.part,  org.eclipse.ui.views.properties"`
- Require-Bundle: `org.eclipse.core.resources`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.jface.text`, `org.eclipse.ui`, `org.eclipse.ui.editors`, `org.eclipse.ui.ide`, `org.eclipse.ui.views`, `org.eclipse.core.tools`
- Packaging/source configuration: [resources/bundles/org.eclipse.core.tools.resources/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/bundles/org.eclipse.core.tools.resources/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = plugin.xml,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.tools.resources`](packages-06.md#p0177), [`org.eclipse.core.tools.resources.markers`](packages-06.md#p0178), [`org.eclipse.core.tools.resources.metadata`](packages-06.md#p0179)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0040

### `resources/examples`

- Source: [resources/examples](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/examples); example/template (path classification)
- Discovery evidence: [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/examples/pom.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Declared Maven coordinates: `org.eclipse.platform:eclipse.platform.resources.examples:4.42.0-SNAPSHOT`; packaging `pom`; parent `org.eclipse.platform:eclipse.platform.resources:4.42.0-SNAPSHOT`, relative path `omitted; effective parent convention applies`
- Packaging/source configuration: [resources/examples/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/examples/build.properties); observed property starts `pom.model.artifactId=resources-examples`. Continued values require reading the linked file
- Aggregates: [`resources/examples/org.eclipse.ui.examples.filesystem`](projects-02.md#u0041) (non-profile declaration)
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0041

### `resources/examples/org.eclipse.ui.examples.filesystem`

- Source: [resources/examples/org.eclipse.ui.examples.filesystem](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/examples/org.eclipse.ui.examples.filesystem); example/template (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/examples/org.eclipse.ui.examples.filesystem/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/examples/org.eclipse.ui.examples.filesystem/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.ui.examples.filesystem`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.ui.examples.filesystem`, version `1.3.0.qualifier`, declared name `Efs Examples Plug-in`; execution environment `JavaSE-25`
- Activation policy: `lazy`
- Require-Bundle: `org.eclipse.core.filesystem`, `org.eclipse.core.resources`, `org.eclipse.ui.ide`, `org.eclipse.ui;bundle-version="3.208.0"`, `org.eclipse.osgi`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`
- Packaging/source configuration: [resources/examples/org.eclipse.ui.examples.filesystem/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/examples/org.eclipse.ui.examples.filesystem/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.internal.filesystem.memory`](packages-04.md#p0100), [`org.eclipse.core.internal.filesystem.zip`](packages-04.md#p0101), [`org.eclipse.ui.examples.filesystem`](packages-18.md#p0536)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0042

### `resources/tests`

- Source: [resources/tests](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests); implementation/build; activation must be checked below
- Discovery evidence: [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/pom.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Declared Maven coordinates: `org.eclipse.platform:eclipse.platform.resources.tests:4.42.0-SNAPSHOT`; packaging `pom`; parent `org.eclipse.platform:eclipse.platform.resources:4.42.0-SNAPSHOT`, relative path `omitted; effective parent convention applies`
- Packaging/source configuration: [resources/tests/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/build.properties); observed property starts `pom.model.artifactId=tesources-tests`. Continued values require reading the linked file
- Aggregates: [`resources/tests/org.eclipse.core.tests.filesystem.feature`](projects-02.md#u0043) (non-profile declaration), [`resources/tests/org.eclipse.core.tests.resources`](projects-02.md#u0044) (non-profile declaration), [`resources/tests/org.eclipse.core.tests.resources.saveparticipant`](projects-02.md#u0045) (non-profile declaration), [`resources/tests/org.eclipse.core.tests.resources.saveparticipant1`](projects-02.md#u0046) (non-profile declaration), [`resources/tests/org.eclipse.core.tests.resources.saveparticipant2`](projects-02.md#u0047) (non-profile declaration), [`resources/tests/org.eclipse.core.tests.resources.saveparticipant3`](projects-02.md#u0048) (non-profile declaration)
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0043

### `resources/tests/org.eclipse.core.tests.filesystem.feature`

- Source: [resources/tests/org.eclipse.core.tests.filesystem.feature](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.filesystem.feature); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.filesystem.feature/.project), [feature.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.filesystem.feature/feature.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.core.tests.filesystem.feature`; natures: `org.eclipse.pde.FeatureNature`
- Distribution feature: `org.eclipse.core.tests.filesystem.feature`; included plug-ins `org.eclipse.core.filesystem.linux.x86_64`, `org.eclipse.core.filesystem.macosx`; included features none declared
- Packaging/source configuration: [resources/tests/org.eclipse.core.tests.filesystem.feature/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.filesystem.feature/build.properties); observed property starts `bin.includes = feature.xml,\`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0044

### `resources/tests/org.eclipse.core.tests.resources`

- Source: [resources/tests/org.eclipse.core.tests.resources](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources/META-INF/MANIFEST.MF), [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources/pom.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.core.tests.resources`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Declared Maven coordinates: `org.eclipse.platform:org.eclipse.core.tests.resources:3.24.200-SNAPSHOT`; packaging `eclipse-test-plugin`; parent `org.eclipse.platform:eclipse.platform.resources.tests:4.42.0-SNAPSHOT`, relative path `omitted; effective parent convention applies`
- Bundle identity: `org.eclipse.core.tests.resources`, version `3.24.200.qualifier`, declared name `Eclipse Core Tests Resources`; execution environment `JavaSE-25`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.tests.filesystem`, `org.eclipse.core.tests.internal.alias`, `org.eclipse.core.tests.internal.builders`, `org.eclipse.core.tests.internal.dtree`, `org.eclipse.core.tests.internal.filesystem.bogus`, `org.eclipse.core.tests.internal.filesystem.broken`, `org.eclipse.core.tests.internal.filesystem.bug369177`, `org.eclipse.core.tests.internal.filesystem.ram`, `org.eclipse.core.tests.internal.filesystem.remote`, `org.eclipse.core.tests.internal.filesystem.wrapper`, `org.eclipse.core.tests.internal.localstore`, `org.eclipse.core.tests.internal.mapping`, `org.eclipse.core.tests.internal.properties`, `org.eclipse.core.tests.internal.propertytester`, `org.eclipse.core.tests.internal.resources`, `org.eclipse.core.tests.internal.utils`, `org.eclipse.core.tests.internal.watson`, `org.eclipse.core.tests.resources`, `org.eclipse.core.tests.resources.content`, `org.eclipse.core.tests.resources.perf`, `org.eclipse.core.tests.resources.refresh`, `org.eclipse.core.tests.resources.regression`, `org.eclipse.core.tests.resources.session`, `org.eclipse.core.tests.resources.usecase`, `org.eclipse.core.tests.resources.util`
- Import-Package: `org.assertj.core.api`, `org.eclipse.osgi.service.debug`, `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.extension;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.function;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.io;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.params;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.params.provider;version="[5.14.0,6.0.0)"`, `org.junit.platform.suite.api;version="[1.14.0,2.0.0)"`, `org.mockito`
- Require-Bundle: `org.eclipse.core.resources`, `org.eclipse.core.tests.harness`, `org.eclipse.core.filesystem`, `org.eclipse.core.runtime`, `org.eclipse.pde.junit.runtime;bundle-version="3.5.0"`
- Packaging/source configuration: [resources/tests/org.eclipse.core.tests.resources/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = plugin.xml,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.tests.filesystem`](packages-05.md#p0132), [`org.eclipse.core.tests.internal.alias`](packages-05.md#p0137), [`org.eclipse.core.tests.internal.builders`](packages-05.md#p0138), [`org.eclipse.core.tests.internal.dtree`](packages-05.md#p0139), [`org.eclipse.core.tests.internal.events`](packages-05.md#p0140), [`org.eclipse.core.tests.internal.filesystem.bogus`](packages-05.md#p0141), [`org.eclipse.core.tests.internal.filesystem.broken`](packages-05.md#p0142), [`org.eclipse.core.tests.internal.filesystem.bug369177`](packages-05.md#p0143), [`org.eclipse.core.tests.internal.filesystem.bug440110`](packages-05.md#p0144), [`org.eclipse.core.tests.internal.filesystem.ram`](packages-05.md#p0145), [`org.eclipse.core.tests.internal.filesystem.remote`](packages-05.md#p0146), [`org.eclipse.core.tests.internal.filesystem.wrapper`](packages-05.md#p0147), [`org.eclipse.core.tests.internal.localstore`](packages-05.md#p0148), [`org.eclipse.core.tests.internal.mapping`](packages-05.md#p0149), [`org.eclipse.core.tests.internal.properties`](packages-06.md#p0151), [`org.eclipse.core.tests.internal.propertytester`](packages-06.md#p0152), [`org.eclipse.core.tests.internal.resources`](packages-06.md#p0153), [`org.eclipse.core.tests.internal.utils`](packages-06.md#p0155), [`org.eclipse.core.tests.internal.watson`](packages-06.md#p0156), [`org.eclipse.core.tests.resources`](packages-06.md#p0158), [`org.eclipse.core.tests.resources.content`](packages-06.md#p0159), [`org.eclipse.core.tests.resources.perf`](packages-06.md#p0160), [`org.eclipse.core.tests.resources.refresh`](packages-06.md#p0161), [`org.eclipse.core.tests.resources.regression`](packages-06.md#p0162), [`org.eclipse.core.tests.resources.session`](packages-06.md#p0167), [`org.eclipse.core.tests.resources.usecase`](packages-06.md#p0168), [`org.eclipse.core.tests.resources.util`](packages-06.md#p0169)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0045

### `resources/tests/org.eclipse.core.tests.resources.saveparticipant`

- Source: [resources/tests/org.eclipse.core.tests.resources.saveparticipant](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.core.tests.resources.saveparticipant`; natures: `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.core.tests.resources.saveparticipant`, version `3.6.300.qualifier`, declared name `Eclipse Core Tests Save Participant`; execution environment `JavaSE-17`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.tests.resources.saveparticipant`
- Import-Package: `org.junit.jupiter.api;version="[5.14.0,6.0.0)"`, `org.junit.jupiter.api.extension;version="[5.14.0,6.0.0)"`
- Require-Bundle: `org.eclipse.core.resources`, `org.eclipse.core.tests.harness`, `org.eclipse.core.runtime`, `org.eclipse.core.tests.resources`, `org.eclipse.core.tests.resources.saveparticipant1`, `org.eclipse.core.tests.resources.saveparticipant2`, `org.eclipse.core.tests.resources.saveparticipant3`, `org.eclipse.core.filesystem`
- Packaging/source configuration: [resources/tests/org.eclipse.core.tests.resources.saveparticipant/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = .,\`; `pom.model.packaging = eclipse-test-plugin`; `pom.model.property.testClass = org.eclipse.core.tests.resources.saveparticipant.SaveManagerTest`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.tests.resources.saveparticipant`](packages-06.md#p0163)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0046

### `resources/tests/org.eclipse.core.tests.resources.saveparticipant1`

- Source: [resources/tests/org.eclipse.core.tests.resources.saveparticipant1](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant1); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant1/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant1/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.core.tests.resources.saveparticipant1`; natures: `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.core.tests.resources.saveparticipant1`, version `3.6.400.qualifier`, declared name `Save Participant 1`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.core.tests.resources.saveparticipant1.SaveParticipant1Plugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.tests.resources.saveparticipant1`
- Require-Bundle: `org.eclipse.core.resources`, `org.eclipse.core.tests.resources`, `org.eclipse.core.runtime`
- Packaging/source configuration: [resources/tests/org.eclipse.core.tests.resources.saveparticipant1/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant1/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = .,\`; `pom.model.packaging = eclipse-test-plugin`; `pom.model.property.failIfNoTests = false`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.tests.resources.saveparticipant1`](packages-06.md#p0164)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0047

### `resources/tests/org.eclipse.core.tests.resources.saveparticipant2`

- Source: [resources/tests/org.eclipse.core.tests.resources.saveparticipant2](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant2); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant2/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant2/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.core.tests.resources.saveparticipant2`; natures: `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.core.tests.resources.saveparticipant2`, version `3.6.400.qualifier`, declared name `Save Participant 2`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.core.tests.resources.saveparticipant2.SaveParticipant2Plugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.tests.resources.saveparticipant2`
- Require-Bundle: `org.eclipse.core.resources`, `org.eclipse.core.tests.resources`, `org.eclipse.core.runtime`
- Packaging/source configuration: [resources/tests/org.eclipse.core.tests.resources.saveparticipant2/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant2/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = .,\`; `pom.model.packaging = eclipse-test-plugin`; `pom.model.property.failIfNoTests = false`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.tests.resources.saveparticipant2`](packages-06.md#p0165)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0048

### `resources/tests/org.eclipse.core.tests.resources.saveparticipant3`

- Source: [resources/tests/org.eclipse.core.tests.resources.saveparticipant3](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant3); test or test fixture (path classification)
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant3/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant3/META-INF/MANIFEST.MF)
- Reactor reachability: reachable through parsed non-profile module declarations
- Eclipse workspace identity: `org.eclipse.core.tests.resources.saveparticipant3`; natures: `org.eclipse.jdt.core.javanature`
- Bundle identity: `org.eclipse.core.tests.resources.saveparticipant3`, version `3.6.400.qualifier`, declared name `Save Participant 3`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.core.tests.resources.saveparticipant3.SaveParticipant3Plugin`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.tests.resources.saveparticipant3`
- Require-Bundle: `org.eclipse.core.resources`, `org.eclipse.core.tests.resources`, `org.eclipse.core.runtime`
- Packaging/source configuration: [resources/tests/org.eclipse.core.tests.resources.saveparticipant3/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources.saveparticipant3/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = .,\`; `pom.model.packaging = eclipse-test-plugin`; `pom.model.property.failIfNoTests = false`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.tests.resources.saveparticipant3`](packages-06.md#p0166)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0049

### `resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bug485227`

- Source: [resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bug485227](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bug485227); test or test fixture (path classification)
- Discovery evidence: [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bug485227/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Bundle identity: `org.eclipse.bug485227`, version `1.0.0`, declared name `org.eclipse.bug485227`; execution environment `not declared`
- Activation policy: `lazy`
- Require-Bundle: `org.eclipse.core.runtime`, `org.eclipse.core.contenttype`
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0050

### `resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle01`

- Source: [resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle01](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle01); test or test fixture (path classification)
- Discovery evidence: [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle01/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Bundle identity: `org.eclipse.bundle01`, version `1.0`, declared name `org.eclipse.bundle01`; execution environment `not declared`
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0051

### `resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle02`

- Source: [resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle02](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle02); test or test fixture (path classification)
- Discovery evidence: [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle02/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Bundle identity: `org.eclipse.bundle02`, version `1.0`, declared name `org.eclipse.bundle02`; execution environment `not declared`
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0052

### `resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle03`

- Source: [resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle03](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle03); test or test fixture (path classification)
- Discovery evidence: [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle03/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Bundle identity: `org.eclipse.bundle03`, version `1.0`, declared name `org.eclipse.bundle03`; execution environment `not declared`
- Require-Bundle: `org.eclipse.core.tests.resources`
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0053

### `resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle04`

- Source: [resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle04](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle04); test or test fixture (path classification)
- Discovery evidence: [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/resources/tests/org.eclipse.core.tests.resources/Plugin_Testing/content/bundle04/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Bundle identity: `org.eclipse.bundle04`, version `1.0`, declared name `org.eclipse.bundle04`; execution environment `not declared`
- Activation policy: `lazy`
- Require-Bundle: `org.eclipse.core.tests.resources`
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0054

### `runtime`

- Source: [runtime](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime); implementation/build; activation must be checked below
- Discovery evidence: [pom.xml](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/pom.xml)
- Reactor reachability: reachable through parsed non-profile module declarations
- Declared Maven coordinates: `org.eclipse.platform:eclipse.platform.runtime:4.42.0-SNAPSHOT`; packaging `pom`; parent `org.eclipse.platform:eclipse.platform:4.42.0-SNAPSHOT`, relative path `omitted; effective parent convention applies`
- Aggregates: [`runtime/bundles`](projects-02.md#u0055) (non-profile declaration), [`runtime/features`](projects-03.md#u0067) (non-profile declaration), [`runtime/tests`](projects-03.md#u0070) (non-profile declaration)
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0055

### `runtime/bundles`

- Source: [runtime/bundles](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles); implementation/build; activation must be checked below
- Discovery evidence: parent module declaration; no local descriptor
- Reactor reachability: reachable through parsed non-profile module declarations
- Packaging/source configuration: [runtime/bundles/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/build.properties); observed property starts `pom.model.artifactId=runtime-bundles`. Continued values require reading the linked file
- No named Java declaration from the read source set is assigned to this metadata path by nearest-owner accounting; this does not make feature/product/resource content empty
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0056

### `runtime/bundles/org.eclipse.core.contenttype`

- Source: [runtime/bundles/org.eclipse.core.contenttype](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.contenttype); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.contenttype/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.contenttype/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.contenttype`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.core.contenttype`, version `3.9.1000.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.internal.content;x-internal:=true`, `org.eclipse.core.runtime.content;uses:="org.eclipse.core.internal.content,org.eclipse.core.runtime,org.eclipse.core.runtime.preferences"`
- Import-Package: `javax.xml.parsers`, `org.eclipse.osgi.service.debug`, `org.eclipse.osgi.util;version="1.0.0"`, `org.xml.sax`, `org.xml.sax.ext`, `org.xml.sax.helpers`
- Require-Bundle: `org.eclipse.equinox.preferences;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.equinox.registry;bundle-version="[3.2.0,4.0.0)"`, `org.eclipse.equinox.common;bundle-version="[3.13.0,4.0.0)"`
- Packaging/source configuration: [runtime/bundles/org.eclipse.core.contenttype/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.contenttype/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.internal.content`](packages-03.md#p0087), [`org.eclipse.core.runtime.content`](packages-05.md#p0130)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0057

### `runtime/bundles/org.eclipse.core.expressions`

- Source: [runtime/bundles/org.eclipse.core.expressions](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.expressions); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.expressions/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.expressions/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.expressions`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.core.expressions`, version `3.9.700.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.expressions;uses:="org.eclipse.core.internal.expressions,org.eclipse.core.runtime,org.w3c.dom"`, `org.eclipse.core.internal.expressions;x-internal:=true`, `org.eclipse.core.internal.expressions.propertytester;x-internal:=true`, `org.eclipse.core.internal.expressions.util;x-internal:=true`
- Import-Package: `org.w3c.dom`
- Require-Bundle: `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`
- Packaging/source configuration: [runtime/bundles/org.eclipse.core.expressions/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.expressions/build.properties); observed property starts `bin.includes = plugin.xml,\`; `source.. = src/`; `output.. = bin/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.expressions`](packages-03.md#p0080), [`org.eclipse.core.internal.expressions`](packages-04.md#p0091), [`org.eclipse.core.internal.expressions.propertytester`](packages-04.md#p0092), [`org.eclipse.core.internal.expressions.util`](packages-04.md#p0094)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0058

### `runtime/bundles/org.eclipse.core.jobs`

- Source: [runtime/bundles/org.eclipse.core.jobs](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.jobs); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.jobs/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.jobs/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.jobs`; natures: `org.eclipse.pde.PluginNature`, `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.core.jobs`, version `3.15.900.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.core.internal.jobs.JobActivator`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.internal.jobs;x-friends:="org.eclipse.core.tests.runtime.jobs"`, `org.eclipse.core.runtime.jobs;uses:="org.eclipse.core.internal.jobs,org.eclipse.core.runtime"`
- Import-Package: `org.eclipse.osgi.service.debug;version="[1.1.0,2)"`, `org.eclipse.osgi.util`, `org.osgi.framework;version="[1.6.0,2)"`
- Require-Bundle: `org.eclipse.equinox.common;bundle-version="[3.8.0,4.0.0)"`
- Packaging/source configuration: [runtime/bundles/org.eclipse.core.jobs/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.jobs/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = META-INF/,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.internal.jobs`](packages-04.md#p0102), [`org.eclipse.core.runtime.jobs`](packages-05.md#p0131)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0059

### `runtime/bundles/org.eclipse.core.runtime`

- Source: [runtime/bundles/org.eclipse.core.runtime](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.runtime); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.runtime/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.runtime/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.runtime`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`, `org.eclipse.pde.api.tools.apiAnalysisNature`
- Bundle identity: `org.eclipse.core.runtime`, version `3.35.100.qualifier`, declared name `%pluginName`; execution environment `JavaSE-17`
- Lifecycle activator: `org.eclipse.core.internal.runtime.PlatformActivator`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.internal.preferences.legacy;x-internal:=true`, `org.eclipse.core.internal.runtime;x-internal:=true`, `org.eclipse.core.runtime;version="3.8.0"; uses:="org.eclipse.core.runtime.content,  org.eclipse.core.runtime.preferences,  org.eclipse.osgi.service.datalocation,  org.eclipse.osgi.service.resolver,  org.osgi.framework"`
- Require-Bundle: `org.eclipse.osgi;bundle-version="[3.23.0,4.0.0)";visibility:=reexport`, `org.eclipse.equinox.common;bundle-version="[3.20.0,4.0.0)";visibility:=reexport`, `org.eclipse.core.jobs;bundle-version="[3.15.0,4.0.0)";visibility:=reexport`, `org.eclipse.equinox.registry;bundle-version="[3.12.0,4.0.0)";visibility:=reexport`, `org.eclipse.equinox.preferences;bundle-version="[3.12.0,4.0.0)";visibility:=reexport`, `org.eclipse.core.contenttype;bundle-version="[3.9.0,4.0.0)";visibility:=reexport`, `org.eclipse.equinox.app;bundle-version="1.7.0";visibility:=reexport`
- Packaging/source configuration: [runtime/bundles/org.eclipse.core.runtime/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.runtime/build.properties); observed property starts `bin.includes = .options,\`; `source.. = src/`; `output.. = bin/`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.internal.preferences.legacy`](packages-04.md#p0105), [`org.eclipse.core.internal.runtime`](packages-04.md#p0114), [`org.eclipse.core.runtime`](packages-05.md#p0129)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

## U0060

### `runtime/bundles/org.eclipse.core.tools`

- Source: [runtime/bundles/org.eclipse.core.tools](https://github.com/hsoliwal/eclipse.platform/tree/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.tools); implementation/build; activation must be checked below
- Discovery evidence: [.project](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.tools/.project), [MANIFEST.MF](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.tools/META-INF/MANIFEST.MF)
- Reactor reachability: outside parsed non-profile module reachability; retained in scope
- Eclipse workspace identity: `org.eclipse.core.tools`; natures: `org.eclipse.jdt.core.javanature`, `org.eclipse.pde.PluginNature`
- Bundle identity: `org.eclipse.core.tools`, version `1.10.200.qualifier`, declared name `%pluginName`; execution environment `JavaSE-21`
- Activation policy: `lazy`
- Export-Package: `org.eclipse.core.tools;x-internal:=true`, `org.eclipse.core.tools.metadata;x-internal:=true`, `org.eclipse.core.tools.runtime;x-internal:=true`
- Require-Bundle: `org.eclipse.core.resources;bundle-version="[3.1.0,4.0.0)";resolution:=optional`, `org.eclipse.core.runtime;bundle-version="[3.29.0,4.0.0)"`, `org.eclipse.jface.text`, `org.eclipse.ui`, `org.eclipse.ui.views`, `org.eclipse.jdt.core;bundle-version="[3.36.0,4.0.0)"`, `org.eclipse.jdt.ui;bundle-version="[3.5.0,4.0.0)"`, `org.eclipse.ltk.core.refactoring`, `org.eclipse.core.filebuffers`, `org.eclipse.ui.ide`, `org.eclipse.search`, `org.eclipse.jdt.core.manipulation`
- Packaging/source configuration: [runtime/bundles/org.eclipse.core.tools/build.properties](https://github.com/hsoliwal/eclipse.platform/blob/0de5d9acb5cff6b133cb1c55f6844720088c7d92/runtime/bundles/org.eclipse.core.tools/build.properties); observed property starts `source.. = src/`; `output.. = bin/`; `bin.includes = plugin.xml,\`. Continued values require reading the linked file
- Owns declared Java namespaces: [`org.eclipse.core.tools`](packages-06.md#p0174), [`org.eclipse.core.tools.metadata`](packages-06.md#p0175), [`org.eclipse.core.tools.nls`](packages-06.md#p0176), [`org.eclipse.core.tools.runtime`](packages-06.md#p0180), [`org.eclipse.core.tools.search`](packages-07.md#p0181)
- Semantic status: metadata accounted; behavior is specified only where [selected contracts](CONTRACTS.md) names an inspected implementation. A bundle name, export or lifecycle header alone does not prove API stability, thread safety or successful activation

