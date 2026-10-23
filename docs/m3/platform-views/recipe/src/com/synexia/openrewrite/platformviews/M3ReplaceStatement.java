/*******************************************************************************
 * Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0, or (at your option) the Eclipse
 * Public License 2.0, which are available at
 * https://www.apache.org/licenses/LICENSE-2.0 and
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
 *
 * Contributors:
 *     Hitesh Soliwal (Synexia) - initial API and implementation
 *******************************************************************************/
package com.synexia.openrewrite.platformviews;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.openrewrite.Cursor;
import org.openrewrite.ExecutionContext;
import org.openrewrite.InMemoryExecutionContext;
import org.openrewrite.Option;
import org.openrewrite.Recipe;
import org.openrewrite.SourceFile;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.JavaParser;
import org.openrewrite.java.tree.J;
import org.openrewrite.java.tree.JavaType;
import org.openrewrite.java.tree.Space;
import org.openrewrite.java.tree.Statement;

/**
 * Replaces statements of a named method of each listed class: every statement
 * (at any depth: blocks, if/else, loops, try, switch cases, synchronized,
 * lambdas) whose text, whitespace ignored, equals {@code statement} becomes
 * {@code replacement}, verbatim. With {@code occurrence} only the n-th match
 * (1-based) is replaced. A fixed point once the text no longer occurs. Used to
 * swap a view's {@code viewer.refresh()} in a change handler for a delta call.
 */
public final class M3ReplaceStatement extends Recipe {
	@Option(displayName = "Declaring classes", description = "Fully qualified classes (nested classes with $).",
			example = "org.eclipse.pde.internal.ui.editor.plugin.LibrarySection")
	private final List<String> declaringClasses;

	@Option(displayName = "Method name", description = "Simple name of the method.", example = "modelChanged")
	private final String methodName;

	@Option(displayName = "Statement", description = "The statement to replace, as written (whitespace ignored).",
			example = "fLibraryTable.refresh();")
	private final String statement;

	@Option(displayName = "Replacement", description = "The new statement, verbatim.",
			example = "M3ModelChangedDeltas.refresh(fLibraryTable, event, o -> o);")
	private final String replacement;

	@Option(displayName = "Occurrence", description = "Replace only the n-th match (1-based); all when absent.",
			example = "1", required = false)
	private final Integer occurrence;

	@JsonCreator
	public M3ReplaceStatement(@JsonProperty("declaringClasses") List<String> declaringClasses,
			@JsonProperty("methodName") String methodName, @JsonProperty("statement") String statement,
			@JsonProperty("replacement") String replacement, @JsonProperty("occurrence") Integer occurrence) {
		this.declaringClasses = declaringClasses == null ? List.of() : List.copyOf(declaringClasses);
		this.methodName = methodName;
		this.statement = statement;
		this.replacement = replacement;
		this.occurrence = occurrence;
	}

	public List<String> getDeclaringClasses() {
		return declaringClasses;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getStatement() {
		return statement;
	}

	public String getReplacement() {
		return replacement;
	}

	public Integer getOccurrence() {
		return occurrence;
	}

	@Override
	public String getDisplayName() {
		return "Replace a statement in a method (upstream port)";
	}

	@Override
	public String getDescription() {
		return "Replace matching statements of a named method of each listed class with the given statement, verbatim.";
	}

	@Override
	public TreeVisitor<?, ExecutionContext> getVisitor() {
		String key = squeeze(statement.endsWith(";") ? statement.substring(0, statement.length() - 1) : statement);
		return new JavaIsoVisitor<>() {
			private int seen;

			@Override
			public J.MethodDeclaration visitMethodDeclaration(J.MethodDeclaration method, ExecutionContext ctx) {
				if (!methodName.equals(method.getSimpleName()) || !listed(getCursor())) {
					return method;
				}
				seen = 0;
				return super.visitMethodDeclaration(method, ctx);
			}

			/*
			 * Statements are replaced in their block: a statement replaced by a different
			 * kind (an invocation by an if) must not travel back through the visit method
			 * of its original kind.
			 */
			@Override
			public J.Block visitBlock(J.Block block, ExecutionContext ctx) {
				J.Block b = super.visitBlock(block, ctx);
				J.MethodDeclaration m = getCursor().firstEnclosing(J.MethodDeclaration.class);
				if (m == null || !methodName.equals(m.getSimpleName()) || !listed(getCursor()) || insideReplacement()) {
					return b;
				}
				return b.withStatements(b.getStatements().stream().map(s -> replaced(s)).toList());
			}

			/* a block inside an earlier replacement (which may contain the replaced statement): a fixed point */
			private boolean insideReplacement() {
				String done = squeeze(replacement);
				for (Cursor c = getCursor().getParentTreeCursor(); c != null && !(c.getValue() instanceof J.MethodDeclaration); c = c
						.getParent()) {
					if (c.getValue() instanceof Statement s && !(s instanceof J.Block)
							&& squeeze(s.withPrefix(Space.EMPTY).printTrimmed(c)).equals(done)) {
						return true;
					}
				}
				return false;
			}

			private Statement replaced(Statement s) {
				String text = squeeze(s.withPrefix(Space.EMPTY).printTrimmed(getCursor())); // comments live in the prefix
				if (text.endsWith(";")) {
					text = text.substring(0, text.length() - 1);
				}
				if (!text.equals(key)) {
					return s;
				}
				seen++;
				if (occurrence != null && occurrence != seen) {
					return s;
				}
				return parseStatement().withPrefix(s.getPrefix());
			}
		};
	}

	private boolean listed(Cursor cursor) {
		J.ClassDeclaration c = cursor.firstEnclosing(J.ClassDeclaration.class);
		if (c == null) {
			return false;
		}
		JavaType.FullyQualified type = c.getType();
		String fqn = type != null ? type.getFullyQualifiedName() : c.getSimpleName();
		J.CompilationUnit cu = cursor.firstEnclosing(J.CompilationUnit.class);
		String pkg = cu != null && cu.getPackageDeclaration() != null
				? cu.getPackageDeclaration().getExpression().printTrimmed() + "." : "";
		return declaringClasses.contains(fqn.contains(".") ? fqn : pkg + fqn);
	}

	private Statement parseStatement() {
		String wrapper = "class M3StatementTemplate {\n\tvoid m3() {\n\t\t" + replacement + "\n\t}\n}\n";
		List<SourceFile> parsed = JavaParser.fromJavaVersion().build()
				.parse(new InMemoryExecutionContext(Throwable::printStackTrace), wrapper).toList();
		J.CompilationUnit cu = (J.CompilationUnit) parsed.get(0);
		J.MethodDeclaration m = (J.MethodDeclaration) cu.getClasses().get(0).getBody().getStatements().get(0);
		List<Statement> statements = m.getBody().getStatements();
		if (statements.size() != 1) {
			throw new IllegalArgumentException("replacement must be exactly one statement");
		}
		return statements.get(0);
	}

	static String squeeze(String text) {
		return text.replaceAll("\\s+", "");
	}
}
