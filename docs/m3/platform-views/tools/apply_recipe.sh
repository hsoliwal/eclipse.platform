#!/usr/bin/env bash
# Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
# SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
# Apply a platform-views hook recipe twice with M3PerFileApply; the second pass must report UNCHANGED.
# Usage: apply_recipe.sh <recipe-name> <file>...   (run under build_lock.sh platform-views)
set -e
. "$(dirname "$0")/pv_env.sh"
C=K:/m3-work/syn-recipes/synexia-openrewrite-recipes/crates/m3-viewport-catalogue-recipes
CP="$C/target/classes;$(cat $C/target/cp.txt)"
RC=$OUT/recipe-classes
mkdir -p "$RC"
"$JAVA_HOME/bin/javac" --release 17 -parameters -nowarn -proc:none -cp "$CP" -d "$RC" \
  $(find "$WT/docs/m3/platform-views/recipe/src" -name '*.java')
CPR="$(cygpath -w "$WT/docs/m3/platform-views/recipe");$RC;$CP"
[ -n "$EXTRA_RECIPE_DIR" ] && CPR="$(cygpath -w "$EXTRA_RECIPE_DIR");$CPR"
NAME=$1; shift
for pass in 1 2; do
  echo "recipe pass $pass: $("$JAVA_HOME/bin/java" -Xmx1g -Djava.io.tmpdir="$(cygpath -w "$OUT/tmp")" -cp "$CPR" \
    com.synexia.openrewrite.viewport.M3PerFileApply "$NAME" "$@" 2>&1 | grep -oE '^(CHANGED|UNCHANGED|ERROR).*' | tr '\n' ' ')"
  # M3PerFileApply prints changed statements with CRLF on Windows; the sources use LF
  sed -i 's/\r$//' "$@"
done
