/*******************************************************************************
 * Copyright (c) 2004, 2013 John-Mason P. Shackelford and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     John-Mason P. Shackelford - initial API and implementation
 *     IBM Corporation - Bug 73411, 84342, on-going bug fixing
 *******************************************************************************/
package org.eclipse.ant.internal.ui.editor.formatter;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XmlTagFormatter {

	public static class AttributePair {

		private final String fAttribute;
		private final String fValue;
		private final char fQuote;

		public AttributePair(String attribute, String value, char attributeQuote) {
			fAttribute = attribute;
			fValue = value;
			fQuote = attributeQuote;
		}

		public String getAttribute() {
			return fAttribute;
		}

		public String getValue() {
			return fValue;
		}

		public char getQuote() {
			return fQuote;
		}
	}

	protected static class ParseException extends Exception {

		private static final long serialVersionUID = 1L;

		public ParseException(String message) {
			super(message);
		}
	}

	protected static class Tag {

		private final List<AttributePair> fAttributes = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW

		private boolean fClosed;

		private String fElementName;

		public void addAttribute(String attribute, String value, char quote) {
			fAttributes.add(new AttributePair(attribute, value, quote));
		}

		public int attributeCount() {
			return fAttributes.size();
		}

		public AttributePair getAttributePair(int i) {
			return fAttributes.get(i);
		}

		public String getElementName() {
			return this.fElementName;
		}

		public boolean isClosed() {
			return fClosed;
		}

		public int minimumLength() { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
			int length = 2; // for the < >
			if (this.isClosed()) {
				length++; // if we need to add an />
			}
			length += this.getElementName().length();
			if (this.attributeCount() > 0 || this.isClosed()) {
				length++;
			}
			for (int i = 0; i < this.attributeCount(); i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				AttributePair attributePair = this.getAttributePair(i);
				length += attributePair.getAttribute().length();
				length += attributePair.getValue().length();
				length += 4; // equals sign, quote characters & trailing space
			}
			if (this.attributeCount() > 0 && !this.isClosed()) {
				length--;
			}
			return length;
		}

		public void setAttributes(List<AttributePair> attributePair) {
			fAttributes.clear();
			fAttributes.addAll(attributePair);
		}

		public void setClosed(boolean closed) {
			fClosed = closed;
		}

		public void setElementName(String elementName) {
			fElementName = elementName;
		}

		@Override //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
		public String toString() {
			StringBuilder sb = new StringBuilder(500); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
			sb.append('<'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			sb.append(this.getElementName()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			if (this.attributeCount() > 0 || this.isClosed()) {
				sb.append(' '); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			}

			for (int i = 0; i < this.attributeCount(); i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				AttributePair attributePair = this.getAttributePair(i);
				sb.append(attributePair.getAttribute()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				sb.append('='); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				sb.append(attributePair.getQuote()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				sb.append(attributePair.getValue()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				sb.append(attributePair.getQuote()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				if (this.isClosed() || i != this.attributeCount() - 1) {
					sb.append(' '); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				}
			}
			if (this.isClosed()) {
				sb.append('/'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			}
			sb.append('>'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			return sb.toString();
		}
	}

	protected static class TagFormatter {

		private int countChar(char searchChar, String inTargetString) {
			StringCharacterIterator iter = new StringCharacterIterator(inTargetString);
			int i = 0;
			if (iter.first() == searchChar) {
				i++;
			}
			while (iter.getIndex() < iter.getEndIndex()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				if (iter.next() == searchChar) {
					i++;
				}
			}
			return i;
		}

		public String format(Tag tag, FormattingPreferences prefs, String indent, String lineDelimiter) {
			if (prefs.wrapLongTags() && lineRequiresWrap(indent + tag.toString(), prefs.getMaximumLineWidth(), prefs.getTabWidth())) { //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
				return wrapTag(tag, prefs, indent, lineDelimiter);
			}
			return tag.toString();
		}

		protected boolean lineRequiresWrap(String line, int lineWidth, int tabWidth) {
			return tabExpandedLineWidth(line, tabWidth) > lineWidth;
		}

		/**
		 * @param line
		 *            the line in which spaces are to be expanded
		 * @param tabWidth
		 *            number of spaces to substitute for a tab
		 * @return length of the line with tabs expanded to spaces
		 */
		protected int tabExpandedLineWidth(String line, int tabWidth) {
			int tabCount = countChar('\t', line);
			return (line.length() - tabCount) + (tabCount * tabWidth);
		}

		protected String wrapTag(Tag tag, FormattingPreferences prefs, String indent, String lineDelimiter) { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD
			StringBuilder sb = new StringBuilder(1024); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
			sb.append('<'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			sb.append(tag.getElementName()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			sb.append(' '); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

			if (tag.attributeCount() > 0) {
				AttributePair pair = tag.getAttributePair(0);
				sb.append(pair.getAttribute()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				sb.append('='); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				sb.append(pair.getQuote()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				sb.append(tag.getAttributePair(0).getValue()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				sb.append(pair.getQuote()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			}

			if (tag.attributeCount() > 1) {
				char[] extraIndent = new char[tag.getElementName().length() + 2];
				Arrays.fill(extraIndent, ' ');
				for (int i = 1; i < tag.attributeCount(); i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
					AttributePair pair = tag.getAttributePair(i);
					sb.append(lineDelimiter); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					sb.append(indent); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					sb.append(extraIndent); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					sb.append(pair.getAttribute()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					sb.append('='); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					sb.append(pair.getQuote()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					sb.append(pair.getValue()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
					sb.append(pair.getQuote()); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				}
			}

			if (prefs.alignElementCloseChar()) {
				sb.append(lineDelimiter); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
				sb.append(indent); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			} else if (tag.isClosed()) {
				sb.append(' '); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			}

			if (tag.isClosed()) {
				sb.append('/'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			}
			sb.append('>'); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
			return sb.toString();
		}
	}

	// if object creation is an issue, use static methods or a flyweight
	// pattern
	protected static class TagParser {

		private String fElementName;

		private String fParseText;

		protected List<AttributePair> getAttibutes(String elementText) throws ParseException { //FLLDCIM: MARK — ML METHOD_SPLIT METHOD

			class Mode {
				private int mode;

				public void setAttributeNameSearching() {
					mode = 0;
				}

				public void setAttributeNameFound() {
					mode = 1;
				}

				public void setAttributeValueSearching() {
					mode = 2;
				}

				public void setAttributeValueFound() {
					mode = 3;
				}

				public void setFinished() {
					mode = 4;
				}

				public boolean isAttributeNameSearching() {
					return mode == 0;
				}

				public boolean isAttributeNameFound() {
					return mode == 1;
				}

				public boolean isAttributeValueSearching() {
					return mode == 2;
				}

				public boolean isAttributeValueFound() {
					return mode == 3;
				}

				public boolean isFinished() {
					return mode == 4;
				}
			}

			List<AttributePair> attributePairs = new ArrayList<>(); //FLLDCIM: MARK — M02 M3_ARRAY_LANES NEW

			CharacterIterator iter = new StringCharacterIterator(elementText.substring(getElementName(elementText).length() + 2));

			// state for finding attributes
			Mode mode = new Mode();
			mode.setAttributeNameSearching();
			char attributeQuote = '"';
			StringBuilder currentAttributeName = null;
			StringBuilder currentAttributeValue = null;

			char c = iter.first();
			while (iter.getIndex() < iter.getEndIndex()) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP

				switch (c) {

					case '"':
					case '\'':

						if (mode.isAttributeValueSearching()) {

							// start of an attribute value
							attributeQuote = c;
							mode.setAttributeValueFound();
							currentAttributeValue = new StringBuilder(1024); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW

						} else if (mode.isAttributeValueFound() && attributeQuote == c) {

							// we've completed a pair!
							AttributePair pair = new AttributePair(currentAttributeName.toString(), currentAttributeValue.toString(), attributeQuote);

							attributePairs.add(pair);

							// start looking for another attribute
							mode.setAttributeNameSearching();

						} else if (mode.isAttributeValueFound() && attributeQuote != c) {

							// this quote character is part of the attribute value
							currentAttributeValue.append(c); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

						} else {
							// this is no place for a quote!
							throw new ParseException("Unexpected '" + c //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
									+ "' when parsing:\n\t" + elementText); //$NON-NLS-1$
						}
						break;

					case '=':

						if (mode.isAttributeValueFound()) {

							// this character is part of the attribute value
							currentAttributeValue.append(c); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

						} else if (mode.isAttributeNameFound()) {

							// end of the name, now start looking for the value
							mode.setAttributeValueSearching();

						} else {
							// this is no place for an equals sign!
							throw new ParseException("Unexpected '" + c //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
									+ "' when parsing:\n\t" + elementText); //$NON-NLS-1$
						}
						break;

					case '/':
					case '>':
						if (mode.isAttributeValueFound()) {
							// attribute values are CDATA, add it all
							currentAttributeValue.append(c); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
						} else if (mode.isAttributeNameSearching()) {
							mode.setFinished();
						} else if (mode.isFinished()) {
							// consume the remaining characters
						} else {
							// we aren't ready to be done!
							throw new ParseException("Unexpected '" + c //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
									+ "' when parsing:\n\t" + elementText); //$NON-NLS-1$
						}
						break;

					default:

						if (mode.isAttributeValueFound()) {
							// attribute values are CDATA, add it all
							currentAttributeValue.append(c); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL

						} else if (mode.isFinished()) {
							if (!Character.isWhitespace(c)) {
								throw new ParseException("Unexpected '" + c //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
										+ "' when parsing:\n\t" + elementText); //$NON-NLS-1$
							}
						} else {
							if (!Character.isWhitespace(c)) {
								if (mode.isAttributeNameSearching()) {
									// we found the start of an attribute name
									mode.setAttributeNameFound();
									currentAttributeName = new StringBuilder(255); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE NEW
									currentAttributeName.append(c); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
								} else if (mode.isAttributeNameFound()) {
									currentAttributeName.append(c); //FLLDCIM: MARK — M14 M3STRING_COMPOSITE CALL
								}
							}
						}
						break;
				}

				c = iter.next();
			}
			if (!mode.isFinished()) {
				throw new ParseException("Element did not complete normally."); //$NON-NLS-1$
			}
			return attributePairs;
		}

		/**
		 * @param tagText
		 *            text of an XML tag
		 * @return extracted XML element name
		 */
		protected String getElementName(String tagText) throws ParseException {
			if (!tagText.equals(this.fParseText) || this.fElementName == null) {
				int endOfTag = tagEnd(tagText);
				if ((tagText.length() > 2) && (endOfTag > 1)) {
					this.fParseText = tagText;
					this.fElementName = tagText.substring(1, endOfTag);
				} else {
					throw new ParseException("No element name for the tag:\n\t" //$NON-NLS-1$ //FLLDCIM: MARK — M33 M3STRING_COMPOSITE CONCAT
							+ tagText);
				}
			}
			return fElementName;
		}

		protected boolean isClosed(String tagText) {
			return tagText.charAt(tagText.lastIndexOf('>') - 1) == '/';
		}

		/**
		 * @return a fully populated tag
		 */
		public Tag parse(String tagText) throws ParseException {
			Tag tag = new Tag();
			tag.setElementName(getElementName(tagText));
			tag.setAttributes(getAttibutes(tagText));
			tag.setClosed(isClosed(tagText));
			return tag;
		}

		private int tagEnd(String text) {
			// This is admittedly a little loose, but we don't want the
			// formatter to be too strict...
			// http://www.w3.org/TR/2000/REC-xml-20001006#NT-Name
			for (int i = 1; i < text.length(); i++) { //FLLDCIM: MARK — M32 LOOP_TO_LANE LOOP
				char c = text.charAt(i);
				if (!Character.isLetterOrDigit(c) && c != ':' && c != '.' && c != '-' && c != '_') {
					return i;
				}
			}
			return -1;
		}
	}

	public static String format(String tagText, FormattingPreferences prefs, String indent, String lineDelimiter) {

		Tag tag;
		if (tagText.startsWith("</") || tagText.startsWith("<%") //$NON-NLS-1$ //$NON-NLS-2$
				|| tagText.startsWith("<?") || tagText.startsWith("<[")) { //$NON-NLS-1$ //$NON-NLS-2$
			return tagText;
		}
		try {
			tag = new TagParser().parse(tagText);
		}
		catch (ParseException e) {
			// if we can't parse the tag, give up and leave the text as is.
			return tagText;
		}
		return new TagFormatter().format(tag, prefs, indent, lineDelimiter);
	}
}