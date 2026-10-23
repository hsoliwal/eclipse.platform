#!/usr/bin/env bash
# Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
# SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
# Leaf B1 (Breakpoints view on lanes): apply the hook recipe twice, compile the changed org.eclipse.debug.ui classes
# against the stock jar ("ours" = these classes in front of the stock jar) and the harness.
# Run under the build lock: bash C:/m3-superset/tools/build_lock.sh platform-views bash .../build_breakpoints.sh [norecipe]
set -e
. "$(dirname "$0")/pv_env.sh"
B=$WT/debug/org.eclipse.debug.ui/ui/org/eclipse/debug/internal/ui
if [ "$1" != norecipe ]; then
  bash "$(dirname "$0")/apply_recipe.sh" com.synexia.openrewrite.platformviews.BreakpointsViewLanes \
    "$B/views/breakpoints/BreakpointContainer.java" "$B/views/breakpoints/ElementComparator.java" \
    "$B/model/elements/BreakpointManagerContentProvider.java"
fi
J="$JAVA_HOME/bin/javac"
CP="$STOCK_DEBUG_UI;$STOCK_DEBUG_CORE;$STOCK_JFACE;$STOCK_SWT;$DEPS"
OURS=$OUT/ours/classes
rm -rf "$OURS" && mkdir -p "$OURS"
"$J" --release 21 -encoding UTF-8 -nowarn -proc:none -g -cp "$CP" -d "$OURS" \
  "$B/m3/M3SortedLane.java" "$B/views/breakpoints/BreakpointContainer.java" "$B/views/breakpoints/ElementComparator.java" \
  "$B/model/elements/BreakpointManagerContentProvider.java" 2>&1 | grep -E "error|warning: \[" | head -40 || true
echo "ours classes: $(find "$OURS" -name '*.class' | wc -l)"
H=$OUT/harness/classes
rm -rf "$H" && mkdir -p "$H"
"$J" --release 21 -encoding UTF-8 -nowarn -proc:none -g -cp "$OURS;$CP;$JUNIT" -d "$H" \
  "$WT"/debug/org.eclipse.debug.tests/src/org/eclipse/debug/tests/m3/*.java 2>&1 | grep -E "error" | head -40 || true
echo "harness classes: $(find "$H" -name '*.class' | wc -l)"
