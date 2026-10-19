#!/usr/bin/env python3
# SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project
# SPDX-License-Identifier: Apache-2.0
"""Reproduce the recorded javac/package/genuine-Equinox commands; no API substitutes."""
import argparse
import hashlib
import json
from pathlib import Path
import subprocess
import zipfile

parser = argparse.ArgumentParser()
for option in ['jdk', 'dependencies', 'baseline-bundle', 'baseline-classes',
               'candidate-bundle', 'candidate-classes', 'output']:
    parser.add_argument('--' + option, type=Path, required=True)
args = parser.parse_args()
args.output.mkdir(parents=True, exist_ok=False)
proof = Path(__file__).resolve().parent
common = args.dependencies / 'org.eclipse.equinox.common-3.21.0.jar'
osgi = args.dependencies / 'org.eclipse.osgi-3.24.300.jar'
classpath = ':'.join(map(str, [args.baseline_classes, common, osgi]))
commands = []

def run(name, command):
    with (args.output / (name + '.stdout')).open('wb') as out, \
            (args.output / (name + '.stderr')).open('wb') as err:
        result = subprocess.run(command, stdout=out, stderr=err, timeout=60)
    commands.append({'name': name, 'command': command, 'exitCode': result.returncode})
    (args.output / 'commands.json').write_text(json.dumps(commands, indent=2) + '\n')
    if result.returncode:
        raise SystemExit(name + ' refused: ' + str(result.returncode))

def jar(destination, entries):
    with zipfile.ZipFile(destination, 'w', compression=zipfile.ZIP_DEFLATED, compresslevel=9) as stream:
        for name, data in sorted(entries.items(), key=lambda item: (item[0] != 'META-INF/MANIFEST.MF', item[0])):
            info = zipfile.ZipInfo(name, (1980, 1, 1, 0, 0, 0))
            info.compress_type = zipfile.ZIP_DEFLATED
            info.external_attr = 0o100644 << 16
            stream.writestr(info, data)

for name in ['JobQueueOsgiClient', 'JobQueueEquinoxProof']:
    destination = args.output / name
    destination.mkdir()
    run(name + '-compile', [str(args.jdk / 'bin/javac'), '--release', '17', '-Xlint:all', '-Werror',
                           '-cp', classpath, '-d', str(destination), str(proof / (name + '.java'))])
for mode, bundle, classes in [('baseline', args.baseline_bundle, args.baseline_classes),
                              ('candidate', args.candidate_bundle, args.candidate_classes)]:
    entries = {p.relative_to(classes).as_posix(): p.read_bytes() for p in classes.rglob('*') if p.is_file()}
    for p in (bundle / 'META-INF').rglob('*'):
        if p.is_file():
            entries[p.relative_to(bundle).as_posix()] = p.read_bytes()
    for name in ['plugin.properties', '.options', 'about.html', 'plugin.xml']:
        entries[name] = (bundle / name).read_bytes()
    jar(args.output / (mode + '-jobs.jar'), entries)
manifest = ('Manifest-Version: 1.0\r\nBundle-ManifestVersion: 2\r\n'
            'Bundle-SymbolicName: org.eclipse.core.tests.runtime.jobs\r\nBundle-Version: 1.0.0\r\n'
            'Bundle-RequiredExecutionEnvironment: JavaSE-17\r\n'
            'Require-Bundle: org.eclipse.core.jobs;bundle-version="[3.15.901,4.0.0)",\r\n'
            ' org.eclipse.equinox.common;bundle-version="[3.8.0,4.0.0)"\r\n\r\n')
client = args.output / 'JobQueueOsgiClient'
entries = {p.relative_to(client).as_posix(): p.read_bytes() for p in client.rglob('*.class')}
entries['META-INF/MANIFEST.MF'] = manifest.encode()
jar(args.output / 'client.jar', entries)
for mode in ['baseline', 'candidate']:
    run(mode, [str(args.jdk / 'bin/java'), '-cp', str(osgi) + ':' + str(args.output / 'JobQueueEquinoxProof'),
               'JobQueueEquinoxProof', str(args.output / (mode + '-storage')), str(common),
               str(args.output / (mode + '-jobs.jar')), str(args.output / 'client.jar'),
               str(mode == 'candidate').lower()])
(args.output / 'jar-pins.json').write_text(json.dumps({p.name: hashlib.sha256(p.read_bytes()).hexdigest()
        for p in args.output.glob('*.jar')}, indent=2) + '\n')
print('Genuine Equinox baseline and candidate commands completed successfully.')
