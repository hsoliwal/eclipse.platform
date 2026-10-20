# Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
# SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
"""Delta gate (brief rule 24): classify every full-rebuild call site of a view.

Read-only, deterministic, stdlib only. For each Java file of a view scope it masks comments and literals,
finds the chain of enclosing method declarations of every rebuild call site, builds a name-based call graph
over the scope, and asks which kind of callback reaches the chain:

  MODEL  a model-change handler (resource/marker deltas, debug events, launch/breakpoint/expression/memory
         listeners, document and console events, sync/diff/compare events, test-run events, model proxies)
  INPUT  an input switch (selection or debug-context change, part activation, inputChanged)
  PREF   a preference/property change (sort, filter, layout, columns, theme)
  USER   a user action (Action.run, widget/selection/key/mouse events, dialogs)
  INIT   part/control/page creation or the view's own input setter
  OTHER  nothing above (needs reading)

Call kinds: refresh() / refresh(true|false) (ROOT), refresh(<element>) (SUBTREE: a full rebuild when the
element is the input), setInput(, removeAll(), clearAll(, setItemCount(0).
Name-based reachability over-approximates on purpose: the gate may flag too much, never too little.
The verdict column is mechanical: FAIL for ROOT/setInput/removeAll/clearAll reached from MODEL,
SUBTREE-REVIEW for refresh(element) reached from MODEL, JUSTIFIED-* otherwise; DELTA_GATE.tsv carries the
human review of each FAIL for converted views.

Usage: python -I delta_gate.py <repo-root> <scopes.tsv> <out.tsv>
scopes.tsv: view_id <TAB> space-separated repo-relative paths (files or directories; %20 for a space).
"""
import os
import re
import sys

REBUILD = re.compile(r"(?<![\w$])(refresh\(\s*(?:true|false)?\s*\)|refresh\((?!\s*(?:true|false)?\s*\))|setInput\(|removeAll\(\s*\)|clearAll\(|setItemCount\(\s*0\s*\))")
# a declaration has a type token before the name; a call has . ( ; { } = , ! ? : -> or a keyword such as return
DECL_BEFORE = re.compile(r"[\w$>\]]\s*$")
CALL_KEYWORDS = ("return", "else", "throw", "case", "yield", "do")
METHOD = re.compile(r"([A-Za-z_$][\w$]*)\s*\(([^;{}()]*(?:\([^()]*\)[^;{}()]*)*)\)\s*(?:throws\s+[\w.$,\s]+)?\{")
CALL = re.compile(r"([A-Za-z_$][\w$]*)\s*\(")
# a Job or runnable scheduled here runs later: link the hop by name (over-approximation, see module doc)
ASYNC_TARGETS = {"run", "runInUIThread", "runInWorkspace"}
JOB_TAG = "@Job"
JOB_CLASS = re.compile(r"\bclass\s+[\w$]+(?:<[^>{]*>)?\s+extends\s+[\w.$]*Job\b[^{]*\{")
METHOD_REF = re.compile(r"::\s*([A-Za-z_$][\w$]*)")
KEYWORDS = {"if", "for", "while", "switch", "catch", "synchronized", "return", "new", "else", "try", "do",
            "super", "this", "throw", "assert", "case", "finally"}
ROOTS = {
    "MODEL": {
        "resourceChanged", "handleDebugEvents", "breakpointsAdded", "breakpointsRemoved", "breakpointsChanged",
        "breakpointManagerEnablementChanged", "launchesAdded", "launchesRemoved", "launchesChanged",
        "launchesTerminated", "launchAdded", "launchRemoved", "launchChanged", "launchConfigurationAdded",
        "launchConfigurationChanged", "launchConfigurationRemoved", "memoryBlocksAdded", "memoryBlocksRemoved",
        "expressionsAdded", "expressionsRemoved", "expressionsChanged", "expressionsMoved", "expressionsInserted",
        "expressionAdded", "expressionRemoved", "expressionChanged", "modelChanged", "updateModel",
        "documentChanged", "documentAboutToBeChanged", "consolesAdded", "consolesRemoved", "matchFound",
        "lineAppended", "streamAppended", "syncInfoChanged", "syncInfoSetReset", "syncInfoSetErrors",
        "diffsChanged", "compareInputChanged", "contentChanged", "setAdded", "setRemoved", "nameChanged",
        "defaultSetChanged", "resourcesChanged", "participantsAdded", "participantsRemoved", "historyChanged",
        "antModelChanged", "registryChanged", "memoryRenderingBindingsChanged", "sessionStarted", "sessionEnded",
        "sessionStopped", "sessionTerminated", "testAdded", "runningBegins", "testStarted", "testEnded",
        "testFailed", "testReran", "sessionLaunched", "sessionRemoved", "sessionAdded", "handleChanges",
        "propertyChanged", "patternMatched", "contextEvent_model"},
    "INPUT": {"selectionChanged", "debugContextChanged", "partActivated", "partVisible", "partInputChanged",
              "inputChanged", "contextActivated", "contextEvent"},
    "PREF": {"propertyChange", "preferenceChange"},
}
USER_ROOTS = {"run", "runWithEvent", "widgetSelected", "widgetDefaultSelected", "handleEvent", "keyPressed",
              "keyReleased", "mouseDown", "mouseUp", "mouseDoubleClick", "doubleClick", "modifyText",
              "checkStateChanged", "execute", "linkActivated", "open", "okPressed", "buttonPressed",
              "handleDefaultSelected", "textChanged"}
INIT_ROOTS = {"createPartControl", "createControl", "createViewer", "createContents", "init", "initialize",
              "configureViewer", "createDialogArea", "initializeViewer", "setInput", "setVisible", "createPage",
              "initializePage", "createTreeViewer", "createTableViewer", "dispose"}


def mask(text):
    """Blank comments and string/char literals, keeping line breaks and offsets."""
    out = list(text)
    i, n = 0, len(text)
    while i < n:
        c = text[i]
        if text.startswith("//", i):
            j = text.find("\n", i)
            j = n if j < 0 else j
            for k in range(i, j):
                out[k] = " "
            i = j
        elif text.startswith("/*", i) or text.startswith('"""', i):
            end = "*/" if text[i + 1] == "*" else '"""'
            j = text.find(end, i + 2)
            j = n if j < 0 else j + len(end)
            for k in range(i, j):
                if text[k] != "\n":
                    out[k] = " "
            i = j
        elif c == '"' or c == "'":
            j = i + 1
            while j < n and text[j] != c and text[j] != "\n":
                j += 2 if text[j] == "\\" else 1
            for k in range(i + 1, min(j, n)):
                out[k] = " "
            i = j + 1
        else:
            i += 1
    return "".join(out)


def methods(masked):
    """Return [(name, body_start, body_end)] for every method/ctor body, via brace matching."""
    close, stack = {}, []
    for i, c in enumerate(masked):
        if c == "{":
            stack.append(i)
        elif c == "}" and stack:
            close[stack.pop()] = i
    jobs = [(close[m.end() - 1], m.end() - 1) for m in JOB_CLASS.finditer(masked) if m.end() - 1 in close]
    found = []
    for m in METHOD.finditer(masked):
        name = m.group(1)
        brace = m.end() - 1
        if name not in KEYWORDS and brace in close:
            # run() of a named Job subclass is reached through schedule(): tag it so only schedule() links to it
            if name in ASYNC_TARGETS and any(s < brace < e for (e, s) in jobs):
                name += JOB_TAG
            found.append((name, brace, close[brace]))
    return sorted(found, key=lambda t: t[1])


def analyse(path, rel):
    with open(path, encoding="utf-8", errors="replace") as f:
        text = f.read()
    masked = mask(text)
    meths = methods(masked)
    calls = {}
    for name, s, e in meths:
        body = list(masked[s:e])
        for (_, s2, e2) in meths:
            if s < s2 and e2 < e:
                body[s2 - s:e2 - s] = " " * (e2 - s2)
        body = "".join(body)
        found = {c for c in CALL.findall(body) if c not in KEYWORDS} | set(METHOD_REF.findall(body))
        if "schedule" in found:
            found |= {t + JOB_TAG for t in ASYNC_TARGETS}
        calls.setdefault(name, set()).update(found)
    sites = []
    for m in REBUILD.finditer(masked):
        pos = m.start()
        before = masked[max(0, pos - 80):pos]
        if masked[pos - 1:pos] != "." and DECL_BEFORE.search(before) and not before.rstrip().endswith(CALL_KEYWORDS):
            continue
        line = masked.count("\n", 0, pos) + 1
        chain = [name for (name, s, e) in meths if s < pos < e]
        g = m.group(1)
        kind = g.split("(")[0]
        if kind == "refresh":
            kind = "refresh()" if re.match(r"refresh\(\s*(?:true|false)?\s*\)", g) else "refresh(elem)"
        snippet = text[pos + 1:pos + 60].split("\n")[0].strip()
        sites.append((rel, line, kind, chain, snippet))
    return calls, sites


def reach(calls, roots):
    seen, todo = {}, [(r, r) for r in sorted(roots) if r in calls]
    while todo:
        name, root = todo.pop(0)
        if name in seen:
            continue
        seen[name] = root
        for c in sorted(calls.get(name, ())):
            if c not in seen:
                todo.append((c, root))
    return seen


def classify(chain, reached, user):
    for cat in ("MODEL", "INPUT", "PREF"):
        for name in chain:
            if name in reached[cat]:
                return cat, reached[cat][name]
    for name in reversed(chain):
        if name in INIT_ROOTS:
            return "INIT", name
        if name in user or name in USER_ROOTS:
            return "USER", name
    return "OTHER", chain[-1] if chain else "-"


def verdict(cat, kind):
    if cat == "MODEL":
        return "SUBTREE-REVIEW" if kind == "refresh(elem)" else "FAIL"
    return {"INPUT": "JUSTIFIED-input-switch", "PREF": "REVIEW-pref", "USER": "JUSTIFIED-user",
            "INIT": "JUSTIFIED-initial", "OTHER": "REVIEW"}[cat]


def files_of(root, rel):
    rel = rel.replace("%20", " ")
    p = os.path.join(root, rel)
    if os.path.isfile(p):
        yield rel
        return
    for d, _, fs in os.walk(p):
        for f in sorted(fs):
            if f.endswith(".java"):
                yield os.path.relpath(os.path.join(d, f), root).replace("\\", "/")


def main(root, scopes, out):
    rows = []
    with open(scopes, encoding="utf-8") as f:
        specs = [l.rstrip("\n").split("\t") for l in f if l.strip() and not l.startswith("#")]
    for view, paths in specs:
        calls, sites = {}, []
        for rel in sorted({r for p in paths.split() for r in files_of(root, p)}):
            c, s = analyse(os.path.join(root, rel), rel)
            for k, v in c.items():
                calls.setdefault(k, set()).update(v)
            sites.extend(s)
        reached = {cat: reach(calls, names) for cat, names in ROOTS.items()}
        user = reach(calls, USER_ROOTS)
        for rel, line, kind, chain, snippet in sites:
            cat, via = classify(chain, reached, user)
            rows.append((view, "%s:%d" % (rel, line), kind, ">".join(chain) or "-", cat, via,
                         verdict(cat, kind), snippet.replace("\t", " ")))
    with open(out, "w", encoding="utf-8", newline="") as f:
        f.write("view_id\tsite\tcall\tenclosing_chain\treached_by\troot\tmechanical_verdict\tsnippet\n")
        for r in rows:
            f.write("\t".join(r) + "\n")
    counts = {}
    for r in rows:
        c = counts.setdefault(r[0], {})
        c[r[6]] = c.get(r[6], 0) + 1
    for v in sorted(counts):
        print(v + "\t" + " ".join("%s=%d" % kv for kv in sorted(counts[v].items())))


if __name__ == "__main__":
    main(*sys.argv[1:4])
