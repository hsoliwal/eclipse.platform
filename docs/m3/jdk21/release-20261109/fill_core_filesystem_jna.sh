#!/usr/bin/env bash
# Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
# SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
#
# JDK21 fill for eclipse.platform org.eclipse.core.filesystem (FFM -> JNA), the master landing of the 2026-10-20 leaf
# 3c1ea6cbfb (branch m3/jdk21-compat-20261020, never content-merged), without deleting tracked files:
#   1. Win32Handler.java = the JNA form of 3c1ea6cbfb (upstream 9dc9a9359b + the four later upstream fixes, among them
#      7d58287614 createFileInfo, which master carries on the FFM form); the script refuses when master's FFM form has
#      moved past the one the leaf was cut from, except by that fix (3-way check below)
#   2. build.properties: source.. = src/   (the jextract FFM bindings under src-gen/ stay tracked, out of the build)
#   3. .classpath: the src-gen source entry goes (IDE build follows Tycho)
#   4. MANIFEST.MF: Import-Package com.sun.jna, com.sun.jna.platform.win32 [5.14.0,6.0.0) (com.sun.jna 5.19.1 is in
#      eclipse-sdk-prereqs.target)
# Usage: fill_core_filesystem_jna.sh <eclipse.platform worktree>   (needs 3c1ea6cbfb and f383091628 fetched)
# Prints step TAB path per changed file. Fixed point.
set -euo pipefail
W="$1"; cd "$W"; export LC_ALL=C
LEAF=3c1ea6cbfb33717291a687c4a625975d5ac70c43; BASE=f383091628a567244fb5be926a53944d0213dde5
B=resources/bundles/org.eclipse.core.filesystem; J=$B/src/org/eclipse/core/internal/filesystem/local/Win32Handler.java
row() { printf '%s\t%s\n' "$1" "$2"; }
# 1. guard: master's FFM Win32Handler may differ from the leaf's base only by the createFileInfo fix the leaf carries
git diff "$BASE" master -- "$J" | grep -E '^[+-][^+-]' | grep -v -E 'createFileInfo|fileInfo = new FileInfo\(\);|real casing|faster method can be used|setName\(|getFileName\(\) instanceof Path name|roots don.t have names|network shares|drives |^\+\s*\}|^\+\s*return fileInfo;|^[+-]\s*$' \
	&& { echo "REFUSED: master Win32Handler moved past the leaf base; port by hand" >&2; exit 1; } || true
if ! git show "$LEAF:$J" | cmp -s - "$J"; then git show "$LEAF:$J" > "$J"; row restore "$J"; fi
# 2. build.properties: drop the src-gen continuation of source..
P=$B/build.properties
if grep -q 'src-gen' "$P"; then
	awk -v BINMODE=3 '/^source\.\. = src\/,\\\r?$/ { cr = ($0 ~ /\r$/) ? "\r" : ""; print "source.. = src/" cr; skip = 1; next }
		skip && /^[ \t]+src-gen\/\r?$/ { skip = 0; next } { skip = 0; print }' "$P" > "$P.fill.tmp" && mv "$P.fill.tmp" "$P"; row buildprops "$P"
fi
# 3. .classpath: remove the src-gen entry (single line or block up to </classpathentry>)
C=$B/.classpath
if grep -q 'path="src-gen"' "$C"; then
	awk -v BINMODE=3 '/<classpathentry kind="src" path="src-gen"\/>/ { next }
		/<classpathentry kind="src" path="src-gen">/ { blk = 1; next } blk { if ($0 ~ /<\/classpathentry>/) blk = 0; next } { print }' "$C" > "$C.fill.tmp" \
		&& mv "$C.fill.tmp" "$C"; row classpath "$C"
fi
# 4. MANIFEST.MF Import-Package (appended before any trailing blank line)
M=$B/META-INF/MANIFEST.MF
if ! grep -q '^Import-Package: com.sun.jna' "$M"; then
	cr=""; [ "$(head -1 "$M" | tr -dc '\r' | wc -c)" -gt 0 ] && cr=$'\r'
	awk -v BINMODE=3 -v cr="$cr" '{ l[NR] = $0 } END { n = NR; while (n > 0 && l[n] ~ /^\r?$/) n--
		for (i = 1; i <= n; i++) print l[i]
		print "Import-Package: com.sun.jna;version=\"[5.14.0,6.0.0)\"," cr; print " com.sun.jna.platform.win32;version=\"[5.14.0,6.0.0)\"" cr
		for (i = n + 1; i <= NR; i++) print l[i] }' "$M" > "$M.fill.tmp" && mv "$M.fill.tmp" "$M"; row manifest "$M"
fi
