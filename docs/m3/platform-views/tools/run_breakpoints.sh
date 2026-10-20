#!/usr/bin/env bash
# Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
# SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
# Leaf B1 (Breakpoints view on lanes): recorded differential stock jar vs lanes, unit tests, JaCoCo coverage and the
# benchmark. Headless (no display), so no ui_lock. Build first with build_breakpoints.sh.
# Usage: run_breakpoints.sh [diff|test|bench|all] [bench sizes...]
set -e
. "$(dirname "$0")/pv_env.sh"
H=$OUT/harness/classes
OURS=$OUT/ours/classes
R=$OUT/b1
mkdir -p "$R"
J="$JAVA_HOME/bin/java"
BASE="$STOCK_DEBUG_UI;$STOCK_DEBUG_CORE;$STOCK_JFACE;$STOCK_SWT;$DEPS"
CPS="$H;$BASE"
CPO="$H;$OURS;$BASE"
INC="org.eclipse.debug.internal.ui.m3.*:org.eclipse.debug.internal.ui.views.breakpoints.BreakpointContainer*:org.eclipse.debug.internal.ui.views.breakpoints.ElementComparator*"
what=${1:-all}; shift || true

if [ "$what" = diff ] || [ "$what" = all ]; then
  "$J" -cp "$CPS" org.eclipse.debug.tests.m3.M3BreakpointsDifferential "$R/diff-stock.txt" 40 400
  "$J" -javaagent:"$JACOCO_AGENT"=destfile="$R/jacoco-diff.exec",includes="$INC" -cp "$CPO" \
    org.eclipse.debug.tests.m3.M3BreakpointsDifferential "$R/diff-ours.txt" 40 400
  echo "differential: $(wc -l < "$R/diff-stock.txt") trace lines, mismatching lines: $(diff "$R/diff-stock.txt" "$R/diff-ours.txt" | grep -c '^[<>]')"
fi

if [ "$what" = test ] || [ "$what" = all ]; then
  for mode in default linear; do
    P=""; [ $mode = linear ] && P="-Dorg.eclipse.debug.ui.m3.linearInsert=true"
    "$J" $P -javaagent:"$JACOCO_AGENT"=destfile="$R/jacoco-test-$mode.exec",includes="$INC" -jar "$JUNIT" execute \
      --class-path "$CPO" --select-class org.eclipse.debug.tests.m3.M3SortedLaneTest --select-class org.eclipse.debug.tests.m3.BreakpointContainerLaneTest --details=summary --disable-banner 2>&1 \
      | grep -E 'tests (successful|failed|found)' | sed "s/^/$mode: /"
  done
  B=$WT/debug/org.eclipse.debug.ui/ui
  "$J" -jar "$JACOCO_CLI" report "$R"/jacoco-*.exec --classfiles "$OURS/org/eclipse/debug/internal/ui/m3" \
    --classfiles "$OURS/org/eclipse/debug/internal/ui/views/breakpoints" --sourcefiles "$B" \
    --csv "$R/coverage.csv" --html "$R/coverage-html" > /dev/null
  awk -F, 'NR>1{printf "%s.%s line %d/%d branch %d/%d\n", $2, $3, $9, $8+$9, $7, $6+$7}' "$R/coverage.csv"
fi

if [ "$what" = bench ] || [ "$what" = all ]; then
  sizes=${*:-"100 1000 10000"}
  for n in $sizes; do
    reps=5; [ "$n" -ge 10000 ] && reps=3
    for side in stock ours; do
      cp="$CPS"; [ $side = ours ] && cp="$CPO"
      "$J" -Xmx3g -Xms3g -XX:+UseParallelGC -cp "$cp" org.eclipse.debug.tests.m3.M3BreakpointsBench $side "$n" $reps 1 100 \
        $([ "$n" -ge 10000 ] && echo 10000) | tee -a "$R/bench.tsv"
    done
  done
fi
