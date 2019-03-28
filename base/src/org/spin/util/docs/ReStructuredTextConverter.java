/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.									  *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.spin.util.docs;

import java.util.ArrayList;
import java.util.List;

import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * reStructuredText converter, it can be used for export a simple String to reStructuredText format
 * @see: https://github.com/adempiere/adempiere/issues/1934
 * For formst reference use: http://www.sphinx-doc.org/en/master/usage/restructuredtext/basics.html
 */
public class ReStructuredTextConverter extends AbstractTextConverter implements IIndex {

	/**
	 * Standard constructor
	 */
	public ReStructuredTextConverter() {
		super();
		//	
		formattedText = new StringBuffer();
		translatedText = new StringBuffer();
		urlSource = new StringBuffer();
	}
	
	/**	Formatted text	*/
	private StringBuffer formattedText;
	/**	Translated text	*/
	private StringBuffer translatedText;
	/**	Previous level	*/
	private int previouslevel = -1;
	/**	Url source	*/
	private StringBuffer urlSource;
	/**	Logging								*/
	private CLogger	log = CLogger.getCLogger(getClass());
	
	@Override
	public AbstractTextConverter addText(String text) {
		formattedText.append(formatText(text));
		log.fine("addText=" + text);
		return this;
	}
	
	/**
	 * Format Text
	 * @param text
	 * @return
	 */
	private String formatText(String text) {
		return text.replaceAll("(?i)<p */?>", "")
				.replaceAll("(?i)<br */?>\\n", "\n")
				.replaceAll("(?i)<br */?>", "* ")
				.replaceAll("(?i)<b */?>", "\\\\ **")
				.replaceAll("(?i)</b */?>", "**\\\\ ");
	}
	
	@Override
	public AbstractTextConverter addBold(String text) {
		if(Util.isEmpty(text)) {
			return this;
		}
		log.fine("addBold=" + text);
		//	Format
		addText(formatBold(text));
		return this;
	}
	
	@Override
	public AbstractTextConverter addItalic(String text) {
		if(Util.isEmpty(text)) {
			return this;
		}
		log.fine("addItalic=" + text);
		//	Format
		addText(formatItalic(text));
		return this;
	}
	
	@Override
	public AbstractTextConverter addCode(String text) {
		if(Util.isEmpty(text)) {
			return this;
		}
		log.fine("addCode=" + text);
		newLine();
		newLine();
		//	Format
		addText(formatCode(text));
		newLine();
		return this;
	}
	
	@Override
	public AbstractTextConverter addQuote(String text, int level) {
		if(Util.isEmpty(text)) {
			return this;
		}
		log.fine("addQuote=" + text);
		newLine();
		//	Format
		addText(formatQuote(text, false, level));
		return this;
	}
	
	
	@Override
	public AbstractTextConverter addQuote(String text) {
		return addQuote(text, 0);
	}
	
	
	@Override
	public AbstractTextConverter addQuoteNumeric(String text, int level) {
		if(Util.isEmpty(text)) {
			return this;
		}
		log.fine("addQuoteNumeric=" + text);
		newLine();
		//	Format
		addText(formatQuote(text, true, level));
		return this;
	}
	
	@Override
	public AbstractTextConverter addQuoteNumeric(String text) {
		return addQuoteNumeric(text, 0);
	}
	
	@Override
	public AbstractTextConverter addSection(String text) {
		if(Util.isEmpty(text)) {
			return this;
		}
		log.fine("addSection=" + text);
		//	Format
		newLine();
		addText(formatSection(text));
		newLine();
		return this;
	}
	
	@Override
	public AbstractTextConverter addSubSection(String text) {
		if(Util.isEmpty(text)) {
			return this;
		}
		log.fine("addSubSection=" + text);
		//	Format
		newLine();
		addText(formatSubSection(text));
		newLine();
		return this;
	}
	
	@Override
	public AbstractTextConverter addSubSubSection(String text) {
		if(Util.isEmpty(text)) {
			return this;
		}
		log.fine("addSubSubSection=" + text);
		//	Format
		newLine();
		addText(formatSubSubSection(text));
		newLine();
		return this;
	}
	
	@Override
	public AbstractTextConverter addParagraph(String text) {
		if(Util.isEmpty(text)) {
			return this;
		}
		log.fine("addParagraph=" + text);
		//	Format
		newLine();
		addText(formatParagraphs(text));
		newLine();
		return this;
	}
	
	@Override
	public AbstractTextConverter addPart(String text) {
		if(Util.isEmpty(text)) {
			return this;
		}
		log.fine("addPart=" + text);
		//	Format
		newLine();
		addText(formatPart(text));
		newLine();
		return this;
	}
	
	@Override
	public AbstractTextConverter addChapter(String text) {
		if(Util.isEmpty(text)) {
			return this;
		}
		log.fine("addChapter=" + text);
		//	Format
		newLine();
		addText(formatChapter(text));
		newLine();
		return this;
	}
	
	@Override
	public AbstractTextConverter addExternalLink(String text, String link) {
		if(Util.isEmpty(text)
				|| Util.isEmpty(link)) {
			return this;
		}
		log.fine("addExternalLink=" + text);
		addText(formatExternalLinkText(text));
		urlSource.append(formatExternalLink(text, link));
		return this;
	}
	
	@Override
	public AbstractTextConverter addSeeAlso(String internalLink) {
		return addSeeAlso(null, internalLink);
	}
	
	@Override
	public AbstractTextConverter addSeeAlso(String name, String internalLink) {
		if(Util.isEmpty(internalLink)) {
			return this;
		}
		log.fine("addSeeAlso=" + internalLink);
		formattedText.append(formatSeeAlso(internalLink));
		return this;
	}
	
	@Override
	public AbstractTextConverter addHeaderIndexName(String indexName) {
		if(Util.isEmpty(indexName)) {
			return this;
		}
		log.fine("addSeeAlso=" + indexName);
		addText(formatHeaderIndexName(indexName));
		return this;
	}
	
	@Override
	public AbstractTextConverter addComment(String comment) {
		if(Util.isEmpty(comment)) {
			return this;
		}
		log.fine("addComment=" + comment);
		addText(formatComment(comment));
		return this;
	}
	
	@Override
	public AbstractTextConverter addNote(String text) {
		if(Util.isEmpty(text)) {
			return this;
		}
		addText(formatNote(text));
		return this;
	}

	@Override
	public AbstractTextConverter addTable(TableTextConverter table) {
		log.fine("addTable=" + table);
		//	
		if(table.getRows().size() != 0) {
			List<String> firstRow = table.getRow(0);
			if(firstRow.size() != 0) {
				String columnSeparator = "  ";
				int[] maxSizes = new int[firstRow.size()];
				newLine();
				boolean isFirst = true;
				StringBuffer headerLine = new StringBuffer();
				for(List<String> row : table.getRows()) {
					newLine();
					StringBuffer text = new StringBuffer();
					for(int columnIndex = 0; columnIndex < maxSizes.length; columnIndex++) {
						String columnText = "";
						if(columnIndex < row.size()) {
							maxSizes[columnIndex] = table.getMaxColumnSize(columnIndex);
							columnText = row.get(columnIndex);
							if(Util.isEmpty(columnText)) {
								columnText = "";
							}
						}
						//	Validate 0
						int size = maxSizes[columnIndex];
						if(size > 0) {
							if(isNumeric(columnText)) {
								columnText = String.format("%1$" + size + "s", columnText);
							} else {
								columnText = String.format("%1$-" + size + "s", columnText);
							}
						}
						if(text.length() > 0) {
							text.append(columnSeparator);
						}
						text.append(columnText);
						//	
						if(isFirst) {
							//	Separator for new column
							if(headerLine.length() > 0) {
								headerLine.append(columnSeparator);
							}
							headerLine.append(getParalelChar(columnText, "="));
						}
					}
					//	For first
					if(isFirst) {
						addText(headerLine.toString());
						newLine();
						addText(text.toString());
						newLine();
						addText(headerLine.toString());
						isFirst = false;
					} else {
						addText(text.toString());
					}
				}
				//	last for table
				newLine();
				addText(headerLine.toString());
				newLine();
			}
		}
		return this;
	}
	
	/**
	 * Format text as bold
	 * @param text
	 * @return
	 */
	private String formatBold(String text) {
		StringBuffer formattedValue = new StringBuffer();
		//	\ **phrase**\ 
		formattedValue.append("\\ **").append(text.trim()).append("**\\ ");
		return formattedValue.toString();
	}
	
	/**
	 * Format as Italic
	 * @param text
	 * @return
	 */
	private String formatItalic(String text) {
		StringBuffer formattedValue = new StringBuffer();
		//	\ *phrase*\ 
		formattedValue.append("\\ *").append(text.trim()).append("*\\ ");
		return formattedValue.toString();
	}
	
	/**
	 * Format as Code
	 * @param text
	 * @return
	 */
	private String formatCode(String text) {
		StringBuffer formattedValue = new StringBuffer();
		//	``code``
		formattedValue.append("``").append(text.trim()).append("``");
		return formattedValue.toString();
	}
	
	/**
	 * Format as Quote
	 * @param text
	 * @return
	 */
	private String formatQuote(String text, boolean isNumeric, int level) {
		//	Add Space
		if(previouslevel != level) {
			newLine();
		}
		previouslevel = level;
		StringBuffer formattedValue = new StringBuffer();
		//	
		String leftSpace = "";
		if(level > 0) {
			leftSpace = String.format("%1$" + level + "s", ""); 
		}
		String type = "*";
		if(isNumeric) {
			type = "#.";
		}
		formattedValue.append(leftSpace).append(type).append(" ").append(text);
		return formattedValue.toString();
	}
	
	/**
	 * Format Header
	 * @param text
	 * @param underValue
	 * @return
	 */
	private String formatHeader(String text, String underValue) {
		StringBuffer formattedValue = new StringBuffer(text.trim());
		formattedValue.append(Env.NL);
		formattedValue.append(getParalelChar(text.trim(), underValue));
		return formattedValue.toString();
	}
	
	/**
	 * Format Main header
	 * @param text
	 * @param underValue
	 * @return
	 */
	private String formatDoubleHeader(String text, String underValue) {
		text = text.trim();
		StringBuffer formattedValue = new StringBuffer();
		formattedValue.append(getParalelChar(text, underValue));
		formattedValue.append(Env.NL);
		formattedValue.append(text);
		formattedValue.append(Env.NL);
		formattedValue.append(getParalelChar(text, underValue));
		return formattedValue.toString();
	}
	
	/**
	 * Format External Link Text
	 * @param text
	 * @return
	 */
	private String formatExternalLinkText(String text) {
		StringBuffer formattedValue = new StringBuffer();
		formattedValue.append("`").append(text.trim()).append("`_");
		return formattedValue.toString();
	}
	
	/**
	 * Format Link
	 * @param text
	 * @param link
	 * @return
	 */
	private String formatExternalLink(String text, String link) {
		//	.. _distributed scaling: http://en.wikipedia.org/wiki/CAP_theorem
		StringBuffer formattedValue = new StringBuffer(Env.NL);
		//	
		formattedValue.append(".. _").append(text.trim()).append(": ").append(link);
		return formattedValue.toString();
	}
	
	/**
	 * Format Section
	 * @param text
	 * @return
	 */
	private String formatSection(String text) {
		return formatDoubleHeader(text, "=");
	}
	
	/**
	 * Format Subsection
	 * @param text
	 * @return
	 */
	private String formatSubSection(String text) {
		return formatHeader(text, "=");
	}
	
	/**
	 * Format Subsubsection
	 * @param text
	 * @return
	 */
	private String formatSubSubSection(String text) {
		return formatHeader(text, "-");
	}
	
	/**
	 * Format paragraphs
	 * @param text
	 * @return
	 */
	private String formatParagraphs(String text) {
		return formatHeader(text, "\"");
	}
	
	/**
	 * Format Parts
	 * @param text
	 * @return
	 */
	private String formatPart(String text) {
		return formatHeader(text, "#");
	}
	
	/**
	 * Formnat Chapters
	 * @param text
	 * @return
	 */
	private String formatChapter(String text) {
		return formatHeader(text, "*");
	}
	
	/**
	 * Get under character
	 * @param text
	 * @param underValue
	 * @return
	 */
	private String getParalelChar(String text, String underValue) {
		return String.format("%1$" + text.length() + "s", "").replace(" ", underValue); 
	}
	
	/**
	 * Format note
	 * @param text
	 * @return
	 */
	private String formatNote(String text) {
		//	.. _distributed scaling: http://en.wikipedia.org/wiki/CAP_theorem
		StringBuffer formattedValue = new StringBuffer(Env.NL);
		//	
		formattedValue.append(".. note::")
			.append(Env.NL)
			.append("    ").append(text.trim())
			.append(Env.NL);
		return formattedValue.toString();
	}
	
	/**
	 * See Also
	 * @param internalLink
	 * @return
	 */
	private String formatSeeAlso(String internalLink) {
		//	.. seealso::
		    //	:ref:`vdufun`
		StringBuffer formattedValue = new StringBuffer(Env.NL);
		//	
		formattedValue.append(".. seealso::")
			.append(Env.NL)
			.append("    :ref:`").append(internalLink).append("`")
			.append(Env.NL);
		return formattedValue.toString();
	}
	
	/**
	 * Format Header link
	 * @param indexName
	 * @return
	 */
	private String formatHeaderIndexName(String indexName) {
		//	.. _api/db/security:
		StringBuffer formattedValue = new StringBuffer(Env.NL);
		//	
		formattedValue.append(".. _")
			.append(indexName).append(":")
			.append(Env.NL);
		return formattedValue.toString();
	}
	
	/**
	 * Format Comment
	 * @param internalLink
	 * @return
	 */
	private String formatComment(String comment) {
		//	.. _api/db/security:
		StringBuffer formattedValue = new StringBuffer(Env.NL);
		//	
		formattedValue.append(".. ")
			.append(comment.replaceAll("\\n", "\n.."))
			.append(Env.NL);
		return formattedValue.toString();
	}
	
	@Override
	public AbstractTextConverter newLine() {
		addText(Env.NL);
		return this;
	}
	
	@Override
	public String toString() {
		if(urlSource.length() > 0) {
			formattedText.append(Env.NL);
			formattedText.append(urlSource);
			urlSource = new StringBuffer();
		}
		log.fine("toString=" + formattedText.toString());
		return formattedText.toString();
	}
	
	public static void main(String[] args) {
		ReStructuredTextConverter formatter = new ReStructuredTextConverter();
		formatter.addSection("Section");
		formatter.addSubSection("SubSection");
		formatter.addSubSubSection("SubSubSection");
		formatter.addChapter("Chapter");
		formatter.addPart("Part");
		formatter.addBold("Hi everybody");
		formatter.newLine();
		formatter.newLine();
		formatter.addBold("Hi");
		formatter.addItalic("Hello");
		formatter.addText("Hi all");
		formatter.addQuote("Items");
		formatter.addQuote("Item 1", 1);
		formatter.addQuote("Item 2", 1);
		formatter.addQuote("Item 2.1", 2);
		formatter.addQuote("Item 2.2", 2);
		formatter.addQuote("Item 2.3", 2);
		formatter.addQuote("Item 3", 1);
		formatter.addQuoteNumeric("Other");
		formatter.addQuoteNumeric("Description 1", 1);
		formatter.addQuoteNumeric("Description 2", 2);
		formatter.addQuoteNumeric("Description 2.1", 3);
		formatter.addQuoteNumeric("Description 2.2", 3);
		formatter.addQuoteNumeric("Description 2.3", 3);
		formatter.addQuoteNumeric("Description 3", 2);
		formatter.addText("This is a collection of key documentation gathered from the ADempiere wiki and the collective experience of the "
				+ "ADempiere Development Community. The aim of this collection is to provide a searchable and usable source of project documentation that will "
				+ "improve on the data contained in the wiki while enhancing the readers experience. ");
		formatter.addExternalLink("ADempiere Test", "http://demo.erpya.com:8888");
		formatter.addText(" si no le interesa revise tambien ");
		formatter.addExternalLink("ADempiere Test 1 ", "http://demo.erpya.com:8888/webui/");
		formatter.addCode("String a = \"Epale\";");
		TableTextConverter table = new TableTextConverter();
		ArrayList<String> row = new ArrayList<>();
		row.add("Value");
		row.add("Name");
		row.add("Description");
		table.addRow(row);
		row = new ArrayList<>();
		row.add("Test");
		row.add("Test Process");
		row.add("A Test Process");
		table.addRow(row);
		row = new ArrayList<>();
		row.add("Production");
		row.add("Production Process");
		row.add("A Production Process");
		table.addRow(row);
		row = new ArrayList<>();
		row.add("Report");
		row.add("Test Report");
		row.add("A Test Report");
		table.addRow(row);
		//	
		formatter.addTable(table);
		//	Get it gfor a file
		System.out.println(formatter.toString());
	}

	@Override
	public void clear() {
		formattedText = new StringBuffer();
		urlSource = new StringBuffer();
		previouslevel = -1;
	}

	@Override
	public String getExtension() {
		return "rst";
	}
	
	@Override
	public String getIndexFileName() {
		return "index.rst";
	}

	@Override
	public AbstractTextConverter addText(String text, int margin) {
		if(Util.isEmpty(text)) {
			return this;
		}
		//	Add Margin
		String leftSpace = "";
		if(margin > 0) {
			leftSpace = String.format("%1$" + margin + "s", "");
		}
		addText(leftSpace + text);
		return this;
	}

	@Override
	public void addTreeDefinition(int maxdepth, boolean isnumbered) {
		formattedText.append(".. toctree::");
		newLine();
		addText(":maxdepth: " + maxdepth, 4);
		newLine();
		if(isnumbered) {
			addText(":numbered:", 4);
		}
		newLine();
	}

	@Override
	public AbstractTextConverter addTranslationText(String text) {
		translatedText.append(text);
		return this;
	}
	
	@Override
	public AbstractTextConverter addStrikethrough(String text) {
		return addText(text);
	}

	@Override
	public void addIndex(String title, String name, String folder, int margin) {
		addText(name, margin);
	}

	@Override
	public void addGroup(String title, String name, int margin) {
		
	}
}
