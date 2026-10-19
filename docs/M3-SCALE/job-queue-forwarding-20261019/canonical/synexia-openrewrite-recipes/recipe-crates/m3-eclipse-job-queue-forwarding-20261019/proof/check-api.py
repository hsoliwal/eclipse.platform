#!/usr/bin/env python3
# SPDX-FileCopyrightText: 2026 Hitesh Soliwal and Contributors to the Synexia Project
# SPDX-License-Identifier: Apache-2.0
"""Run the genuine JDK javap and compare the original compiled Jobs surfaces."""
import argparse
import hashlib
import json
from pathlib import Path
import subprocess

parser = argparse.ArgumentParser()
parser.add_argument('--jdk', type=Path, required=True)
parser.add_argument('--baseline', type=Path, required=True)
parser.add_argument('--candidate', type=Path, required=True)
parser.add_argument('--dependencies', required=True)
parser.add_argument('--output', type=Path, required=True)
args = parser.parse_args()
args.output.mkdir(parents=True, exist_ok=True)

def classes(root):
    return {p.relative_to(root).as_posix(): p for p in root.rglob('*.class')}

def digest(path):
    return hashlib.sha256(path.read_bytes()).hexdigest()

before, after = classes(args.baseline), classes(args.candidate)
removed = sorted(before.keys() - after.keys())
added = sorted(after.keys() - before.keys())
assert removed == ['org/eclipse/core/internal/jobs/JobQueue$2.class'], removed
assert added == ['org/eclipse/core/internal/jobs/M3JobQueue$1.class',
                 'org/eclipse/core/internal/jobs/M3JobQueue.class'], added
common = sorted(before.keys() & after.keys())
changed_bodies = {'org/eclipse/core/internal/jobs/JobQueue.class',
                  'org/eclipse/core/internal/jobs/JobQueue$1.class'}
unchanged = [name for name in common if name not in changed_bodies]
for name in unchanged:
    assert before[name].read_bytes() == after[name].read_bytes(), name
for path in list(before.values()) + list(after.values()):
    data = path.read_bytes()
    assert data[:4] == bytes.fromhex('cafebabe')
    assert int.from_bytes(data[6:8], 'big') == 61, path

commands = []
for name, root in [('baseline', args.baseline), ('candidate', args.candidate)]:
    names = [path[:-6].replace('/', '.') for path in common]
    command = [str(args.jdk / 'bin/javap'), '-protected', '-s', '-constants',
               '-classpath', str(root) + ':' + args.dependencies, *names]
    result = subprocess.run(command, capture_output=True, check=False, timeout=60)
    (args.output / (name + '-api.txt')).write_bytes(result.stdout)
    (args.output / (name + '-api.stderr')).write_bytes(result.stderr)
    commands.append({'name': name, 'command': command, 'exitCode': result.returncode})
    assert result.returncode == 0, result.stderr.decode()
assert (args.output / 'baseline-api.txt').read_bytes() == (args.output / 'candidate-api.txt').read_bytes(), 'public/protected API changed'
for name, root, types in [
        ('candidate-forwarding', args.candidate,
         ['org.eclipse.core.internal.jobs.JobQueue', 'org.eclipse.core.internal.jobs.JobQueue$1']),
        ('baseline-iterator', args.baseline, ['org.eclipse.core.internal.jobs.JobQueue$2']),
        ('candidate-iterator', args.candidate, ['org.eclipse.core.internal.jobs.M3JobQueue$1'])]:
    command = [str(args.jdk / 'bin/javap'), '-c', '-p', '-s', '-classpath', str(root), *types]
    result = subprocess.run(command, capture_output=True, check=False, timeout=60)
    (args.output / (name + '.txt')).write_bytes(result.stdout)
    (args.output / (name + '.stderr')).write_bytes(result.stderr)
    commands.append({'name': name, 'command': command, 'exitCode': result.returncode})
    assert result.returncode == 0, result.stderr.decode()
forwarding = (args.output / 'candidate-forwarding.txt').read_text()
owner_calls = [line.strip() for line in forwarding.splitlines()
               if 'invokestatic' in line and 'M3JobQueue.' in line]
assert len(owner_calls) == 11, owner_calls
receipt = {
    'schema': 'M3_JOB_QUEUE_JDK_API_V1',
    'baselineClasses': len(before), 'candidateClasses': len(after),
    'identicalPublicProtectedSurfaces': len(common),
    'unchangedOriginalClassBytes': len(unchanged),
    'removedPrivateIteratorImplementation': removed, 'addedOwnerAndIterator': added,
    'allClassMajorVersions': 61,
    'publicProtectedApiSha256': digest(args.output / 'baseline-api.txt'),
    'facadeAndHeadStaticOwnerCalls': owner_calls,
    'oneArgumentConstructor': 'delegates to original two-argument constructor',
    'commands': commands,
    'classPins': {name: {'baseline': digest(before[name]) if name in before else None,
                        'candidate': digest(after[name]) if name in after else None}
                  for name in sorted(before.keys() | after.keys())},
}
(args.output / 'receipt.json').write_text(json.dumps(receipt, indent=2) + '\n')
print(json.dumps({key: receipt[key] for key in ['baselineClasses', 'candidateClasses',
      'identicalPublicProtectedSurfaces', 'unchangedOriginalClassBytes', 'allClassMajorVersions']}))
