# SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project
# SPDX-License-Identifier: Apache-2.0
"""Replay the existing SDK, frozen JUnit clients, javac/javap and real Equinox proof."""
from pathlib import Path
import argparse
import hashlib
import json
import os
import shutil
import subprocess
import zipfile

parser = argparse.ArgumentParser(description=__doc__)
for option in ("platform", "work", "jdk", "maven", "maven-cache", "deps", "junit-console"):
    parser.add_argument("--" + option, required=True, type=Path)
args = parser.parse_args()
crate = Path(__file__).resolve().parent.parent
source = args.platform.resolve()
work = args.work.resolve()
if work == crate or work in crate.parents or crate in work.parents:
    raise SystemExit("Use a separate scratch directory for proof output")
if work == source or work in source.parents or source in work.parents:
    raise SystemExit("Proof output must be separate from the Eclipse source checkout")
if work.exists() and any(work.iterdir()):
    raise SystemExit("Use a fresh empty proof directory")
work.mkdir(parents=True, exist_ok=True)
evidence = work / "evidence"
evidence.mkdir()
jdk = args.jdk.resolve()
maven = args.maven.resolve()
cache = args.maven_cache.resolve()
deps = args.deps.resolve()
console = args.junit_console.resolve()
env = os.environ.copy()
env["JAVA_HOME"] = str(jdk)
env["PATH"] = str(jdk / "bin") + os.pathsep + env["PATH"]
pins = json.loads((crate / "SOURCE_PINS.json").read_text())
revision = subprocess.check_output(["git", "-C", str(source), "rev-parse", "HEAD"], text=True).strip()
if revision != pins["target_revision"]:
    raise SystemExit("Eclipse source revision drift")
if subprocess.check_output(["git", "-C", str(source), "status", "--porcelain=v1"], text=True).strip():
    raise SystemExit("Use a clean Eclipse source checkout")
for record in pins["inputs"]:
    if hashlib.sha256((source / record["path"]).read_bytes()).hexdigest() != record["sha256"]:
        raise SystemExit("Source input drift: " + record["path"])
jars = {
    "common": deps / "org.eclipse.equinox.common-3.21.0.jar",
    "osgi": deps / "org.eclipse.osgi-3.24.300.jar",
    "assertj": deps / "assertj-core-3.27.5.jar",
    "junit_console": console,
}
for name, path in jars.items():
    if hashlib.sha256(path.read_bytes()).hexdigest() != pins["dependencies"][name]["sha256"]:
        raise SystemExit("Dependency drift: " + name)

def run(name, command):
    command = list(map(str, command))
    log = evidence / (name + ".log")
    with log.open("w") as output:
        process = subprocess.run(command, stdout=output, stderr=subprocess.STDOUT,
                                 env=env, cwd=work, timeout=180)
    (evidence / (name + ".json")).write_text(json.dumps({
        "stage": name, "command": command, "exit_code": process.returncode,
        "log_sha256": hashlib.sha256(log.read_bytes()).hexdigest()
    }, indent=2) + "\n")
    print(name, "exit=" + str(process.returncode), flush=True)
    if process.returncode:
        print("\n".join(log.read_text().splitlines()[-30:]), flush=True)
        raise SystemExit(process.returncode)
    return log.read_text()

bundle_path = Path("runtime/bundles/org.eclipse.core.jobs")
for variant in ("baseline", "candidate"):
    shutil.copytree(source / bundle_path, work / variant / bundle_path)
mvn_base = [maven, "-o", "-B", "-ntp", "-Dmaven.repo.local=" + str(cache)]
def contracts(variant):
    return run(variant + "-contracts", mvn_base + ["-f", crate / "proof/pom.xml",
        "-Dproof.source.root=" + str(work / variant),
        "-Dproof.build.directory=" + str(work / (variant + "-contracts")), "test"])
contracts("baseline")
run("recipe-sdk", mvn_base + ["-f", crate / "pom.xml",
    "-Dcanonical.classpath=" + str(work / "baseline-contracts/classes"),
    "-Dmaterialize.dir=" + str(work / "generated/runtime"), "test"])
resource = crate / "resources/com/synexia/rewrite/hash-pinned-java/m3-eclipse-rules-forwarding-20261018"
emitted = []
for line in (resource / "manifest.tsv").read_text().splitlines():
    if not line or line.startswith("#"):
        continue
    path, before, after, _template = line.split("\t")
    generated = work / "generated/runtime" / path
    destination = work / "candidate/runtime" / path
    if hashlib.sha256(generated.read_bytes()).hexdigest() != after:
        raise SystemExit("SDK output hash mismatch")
    if before == "ABSENT":
        if destination.exists():
            raise SystemExit("Occupied addition")
    elif hashlib.sha256(destination.read_bytes()).hexdigest() != before:
        raise SystemExit("Candidate preimage changed")
    destination.parent.mkdir(parents=True, exist_ok=True)
    shutil.copyfile(generated, destination)
    emitted.append({"path": "runtime/" + path, "sha256": after})
if len(emitted) != 2:
    raise SystemExit("Incomplete SDK output")
contracts("candidate")

runtime_cp = os.pathsep.join(map(str, [jars["common"], jars["osgi"]]))
fixture_paths = [record["path"] for record in pins["upstream_fixtures"]]
for record in pins["upstream_fixtures"]:
    if hashlib.sha256((source / record["path"]).read_bytes()).hexdigest() != record["sha256"]:
        raise SystemExit("Upstream fixture drift: " + record["path"])
for variant in ("baseline", "candidate"):
    bundle = work / variant / bundle_path
    classes = work / (variant + "-bundle-classes")
    java_sources = sorted((bundle / "src").rglob("*.java"))
    run(variant + "-bundle-compile", [jdk / "bin/javac", "--release", "17", "-Xlint:all",
        "-cp", runtime_cp, "-d", classes, *java_sources])
    for path in sorted((bundle / "src").rglob("*")):
        if path.is_file() and path.suffix != ".java":
            destination = classes / path.relative_to(bundle / "src")
            destination.parent.mkdir(parents=True, exist_ok=True)
            shutil.copyfile(path, destination)
    test_classes = work / (variant + "-upstream-test-classes")
    cp = os.pathsep.join(map(str, [classes, *jars.values()]))
    run(variant + "-upstream-test-compile", [jdk / "bin/javac", "--release", "17", "-Xlint:all",
        "-cp", cp, "-d", test_classes, *[source / path for path in fixture_paths]])
    cp = os.pathsep.join([str(test_classes), str(work / (variant + "-contracts/test-classes")), cp])
    run(variant + "-full-bundle-tests", [jdk / "bin/java", "-jar", console, "execute",
        "--disable-ansi-colors", "--class-path", cp,
        "--select-class", "org.eclipse.core.tests.runtime.jobs.MultiRuleTest",
        "--select-class", "com.synexia.eclipse.contract.MultiRuleContractTest",
        "--fail-if-no-tests", "--reports-dir", evidence / (variant + "-junit"), "--details", "summary"])
    run(variant + "-public-api", [jdk / "bin/javap", "-public", "-s", "-classpath", classes,
                                "org.eclipse.core.runtime.jobs.MultiRule"])
    run(variant + "-bytecode", [jdk / "bin/javap", "-private", "-c", "-classpath", classes,
                              "org.eclipse.core.runtime.jobs.MultiRule"])
if (evidence / "baseline-public-api.log").read_bytes() != (evidence / "candidate-public-api.log").read_bytes():
    raise SystemExit("Public API changed")
before_diagnostics = (evidence / "baseline-bundle-compile.log").read_text().replace(str(work / "baseline"), "<input>")
after_diagnostics = (evidence / "candidate-bundle-compile.log").read_text().replace(str(work / "candidate"), "<input>")
if before_diagnostics != after_diagnostics:
    raise SystemExit("Bundle diagnostics changed")
bytecode = (evidence / "candidate-bytecode.log").read_text()
if bytecode.count("// Method org/eclipse/core/internal/jobs/m3/M3Rules.") != 7:
    raise SystemExit("Seven public forwarding calls required")

launcher = work / "osgi-launcher"
run("osgi-launcher-compile", [jdk / "bin/javac", "--release", "17", "-Xlint:all", "-Werror",
    "-cp", jars["osgi"], "-d", launcher, crate / "proof/JobsEquinoxProof.java"])
client_classes = work / "osgi-client"
run("osgi-client-compile", [jdk / "bin/javac", "--release", "17", "-Xlint:all", "-Werror",
    "-cp", str(work / "baseline-bundle-classes") + os.pathsep + str(jars["common"]),
    "-d", client_classes, crate / "proof/MultiRuleOsgiClient.java"])
client_jar = work / "client.jar"
client_manifest = ("Manifest-Version: 1.0\nBundle-ManifestVersion: 2\n"
    "Bundle-SymbolicName: com.synexia.m3.rules.osgi.client\nBundle-Version: 1.0.0\n"
    "Bundle-RequiredExecutionEnvironment: JavaSE-17\n"
    "Require-Bundle: org.eclipse.core.jobs,org.eclipse.equinox.common\n\n")
with zipfile.ZipFile(client_jar, "w", zipfile.ZIP_DEFLATED) as archive:
    archive.writestr("META-INF/MANIFEST.MF", client_manifest)
    for path in sorted(client_classes.rglob("*.class")):
        archive.write(path, path.relative_to(client_classes).as_posix())
dependency_list = work / "osgi-dependencies.list"
dependency_list.write_text(str(jars["common"]) + "\n")
for variant in ("baseline", "candidate"):
    bundle = work / variant / bundle_path
    classes = work / (variant + "-bundle-classes")
    host = work / (variant + "-jobs.jar")
    with zipfile.ZipFile(host, "w", zipfile.ZIP_DEFLATED) as archive:
        archive.writestr("META-INF/MANIFEST.MF", (bundle / "META-INF/MANIFEST.MF").read_bytes())
        for path in sorted(classes.rglob("*")):
            if path.is_file():
                archive.write(path, path.relative_to(classes).as_posix())
        for name in ("plugin.xml", "plugin.properties", "about.html"):
            archive.write(bundle / name, name)
    run(variant + "-equinox", [jdk / "bin/java", "-cp", str(launcher) + os.pathsep + str(jars["osgi"]),
        "JobsEquinoxProof", work / (variant + "-framework"), dependency_list, host, client_jar,
        str(variant == "candidate").lower()])
(evidence / "qualification.json").write_text(json.dumps({
    "scope": "Complete MultiRule forwarding owner",
    "target_revision": revision, "emitted": emitted,
    "recipe_sdk_tests": 5, "frozen_contract_tests_per_variant": 13,
    "full_bundle_tests_per_variant": 16, "original_upstream_tests_included": 3,
    "real_equinox": True, "osgi_client_assertions_per_variant": 14,
    "public_api_equal": True, "forwarding_call_sites": 7,
    "whole_eclipse_qualified": False, "full_tycho_pde_ci_qualified": False
}, indent=2) + "\n")
print("RULES_QUALIFIED: recipe, contracts, complete Jobs source compilation, upstream tests, API and real Equinox", flush=True)
