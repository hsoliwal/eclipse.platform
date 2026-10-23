# Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
# SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
"""Checks that every repo-relative path.java[:line[-line][,line]] cited in a TSV exists and its lines are in range.

Usage: python -I check_refs.py <repo-root> <file.tsv>...   Prints MISSING/BADLINE rows and a count; exit 1 on any.
"""
import os
import re
import sys

REF = re.compile(r"((?:[\w.-]+/)+(?:Ant Tools Support/|Ant Editor/)?(?:[\w.-]+/)*[\w$.-]+\.java)(?::([0-9][0-9,\-]*))?")


def main(root, files):
    bad = checked = 0
    lengths = {}
    for tsv in files:
        with open(tsv, encoding="utf-8") as f:
            text = f.read()
        for m in REF.finditer(text):
            path, lines = m.group(1), m.group(2)
            full = os.path.join(root, path)
            if not os.path.isfile(full):
                continue  # short names (e.g. "PackageCache.java") are not repo paths
            checked += 1
            if path not in lengths:
                with open(full, encoding="utf-8", errors="replace") as j:
                    lengths[path] = sum(1 for _ in j)
            for n in re.split(r"[,\-]", lines or ""):
                if n and int(n) > lengths[path]:
                    print("BADLINE", path, n, lengths[path])
                    bad += 1
    print("checked", checked, "references,", bad, "bad")
    return 1 if bad else 0


if __name__ == "__main__":
    sys.exit(main(sys.argv[1], sys.argv[2:]))
