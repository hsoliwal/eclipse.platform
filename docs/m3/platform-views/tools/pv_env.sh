# Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
# SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
# Shared paths for the platform-views track. Source it: . docs/m3/platform-views/tools/pv_env.sh
# All build, test and benchmark output goes to S:/m3-scratch/platform-views (C:, D:, E:, G: are never written).
export WT=${WT:-S:/m3-scratch/worktrees/eclipse.platform}
export OUT=${OUT:-S:/m3-scratch/platform-views}
export TEMP=$OUT/tmp TMP=$OUT/tmp
export JAVA_HOME="C:/Program Files/Java/jdk-21"
M2=$(cygpath -m "$HOME/.m2/repository")
P=$M2/org/eclipse/platform
# latest released version of a platform artifact in the local Maven repository
pv_jar() { local v; v=$(ls "$P/$1" | grep -E '^[0-9]' | sort -V | tail -1); echo "$P/$1/$v/$1-$v.jar"; }
# official stock references (Maven Central releases): the "stock" side of every differential
export STOCK_DEBUG_UI=$(pv_jar org.eclipse.debug.ui)
export STOCK_DEBUG_CORE=$(pv_jar org.eclipse.debug.core)
export STOCK_CONSOLE=$(pv_jar org.eclipse.ui.console)
export STOCK_UNITTEST_UI=${STOCK_UNITTEST_UI:-}
export STOCK_SWT=$P/org.eclipse.swt.win32.win32.x86_64/3.135.0/org.eclipse.swt.win32.win32.x86_64-3.135.0.jar
# JFace under test: the JFace agent's candidate classes (branch m3/jface-viewport-20261019) when built, else stock
export JFACE_CAND=${JFACE_CAND:-S:/m3-scratch/jface/cand/classes}
export STOCK_JFACE=$(pv_jar org.eclipse.jface)
DEPS=""
for a in org.eclipse.equinox.common org.eclipse.core.runtime org.eclipse.core.jobs org.eclipse.core.resources \
         org.eclipse.equinox.registry org.eclipse.equinox.preferences org.eclipse.core.commands org.eclipse.osgi \
         org.eclipse.core.expressions org.eclipse.core.variables org.eclipse.core.filesystem org.eclipse.core.contenttype \
         org.eclipse.jface.text org.eclipse.text org.eclipse.ui.workbench org.eclipse.ui.ide; do
  DEPS="$DEPS;$(pv_jar $a)"
done
export DEPS=${DEPS#;}
export JUNIT=$M2/org/junit/platform/junit-platform-console-standalone/1.11.4/junit-platform-console-standalone-1.11.4.jar
export JACOCO_AGENT=$M2/org/jacoco/org.jacoco.agent/0.8.15/org.jacoco.agent-0.8.15-runtime.jar
export JACOCO_CLI=$M2/org/jacoco/org.jacoco.cli/0.8.15/org.jacoco.cli-0.8.15-nodeps.jar
mkdir -p "$OUT/tmp"
