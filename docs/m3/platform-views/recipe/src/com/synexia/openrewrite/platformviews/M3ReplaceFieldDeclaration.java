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
import org.openrewrite.java.tree.Statement;

/**
 * Replaces the declaration of one field of each listed class with the given
 * source, verbatim (type, modifiers and initializer); the field's leading
 * comment is kept. A fixed point once the declaration equals the source,
 * whitespace ignored. Used where a view swaps its per-element store for a lane
 * that keeps the same {@code List} contract.
 */
public final class M3ReplaceFieldDeclaration extends Recipe {
	@Option(displayName = "Declaring classes", description = "Fully qualified classes that declare the field.",
			example = "org.eclipse.debug.internal.ui.views.breakpoints.BreakpointContainer")
	private final List<String> declaringClasses;

	@Option(displayName = "Field name", description = "Name of the field (a declaration with one variable).",
			example = "fBreakpoints")
	private final String fieldName;

	@Option(displayName = "Field source", description = "The complete new declaration, ending with a semicolon.",
			example = "private final java.util.List<Object> fItems = new java.util.ArrayList<>();")
	private final String fieldSource;

	@JsonCreator
	public M3ReplaceFieldDeclaration(@JsonProperty("declaringClasses") List<String> declaringClasses,
			@JsonProperty("fieldName") String fieldName, @JsonProperty("fieldSource") String fieldSource) {
		this.declaringClasses = declaringClasses == null ? List.of() : List.copyOf(declaringClasses);
		this.fieldName = fieldName;
		this.fieldSource = fieldSource;
	}

	public List<String> getDeclaringClasses() {
		return declaringClasses;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldSource() {
		return fieldSource;
	}

	@Override
	public String getDisplayName() {
		return "Replace a field declaration (upstream port)";
	}

	@Override
	public String getDescription() {
		return "Replace the declaration of a named field of each listed class with the given source, verbatim; a fixed point once equal.";
	}

	@Override
	public TreeVisitor<?, ExecutionContext> getVisitor() {
		return new JavaIsoVisitor<>() {
			@Override
			public J.VariableDeclarations visitVariableDeclarations(J.VariableDeclarations field, ExecutionContext ctx) {
				J.VariableDeclarations v = super.visitVariableDeclarations(field, ctx);
				if (v.getVariables().size() != 1 || !fieldName.equals(v.getVariables().get(0).getSimpleName())
						|| !isField(getCursor()) || !listed(getCursor())) {
					return v;
				}
				J.VariableDeclarations replacement = parseField();
				if (squeeze(v.printTrimmed(getCursor())).equals(squeeze(replacement.printTrimmed(getCursor())))) {
					return v;
				}
				return replacement.withPrefix(v.getPrefix());
			}
		};
	}

	private static boolean isField(Cursor cursor) {
		Cursor block = cursor.getParentTreeCursor();
		return block.getValue() instanceof J.Block && block.getParentTreeCursor().getValue() instanceof J.ClassDeclaration;
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

	private J.VariableDeclarations parseField() {
		String wrapper = "class M3FieldTemplate {\n\t" + fieldSource + "\n}\n";
		List<SourceFile> parsed = JavaParser.fromJavaVersion().build()
				.parse(new InMemoryExecutionContext(Throwable::printStackTrace), wrapper).toList();
		J.CompilationUnit cu = (J.CompilationUnit) parsed.get(0);
		List<Statement> members = cu.getClasses().get(0).getBody().getStatements();
		if (members.size() != 1 || !(members.get(0) instanceof J.VariableDeclarations v)) {
			throw new IllegalArgumentException("fieldSource must be exactly one field declaration");
		}
		return v;
	}

	static String squeeze(String text) {
		return text.replaceAll("\\s+", "");
	}
}
