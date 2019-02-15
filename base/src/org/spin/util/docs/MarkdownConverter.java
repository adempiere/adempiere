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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * Markdown converter, it can be used for export a simple String to reStructuredText format
 * @see: https://github.com/adempiere/adempiere/issues/1934
 * For formst reference use: http://www.sphinx-doc.org/en/master/usage/restructuredtext/basics.html
 */
public class MarkdownConverter extends AbstractTextConverter implements IIndex {

	/**
	 * Standard constructor
	 */
	public MarkdownConverter() {
		super();
		//	
		formattedText = new StringBuffer();
		translatedText = new StringBuffer();
	}
	
	/**	Formatted text	*/
	private StringBuffer formattedText;
	/**	Translated text	*/
	private StringBuffer translatedText;
	/**	Previous level	*/
	private int previouslevel = -1;
	/**	Logging								*/
	private CLogger	log = CLogger.getCLogger(getClass());
	/**	Main index added	*/
	private MarkdownConverter mainIndex;
	
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
	public AbstractTextConverter addStrikethrough(String text) {
		if(Util.isEmpty(text)) {
			return this;
		}
		log.fine("Strikethrough=" + text);
		//	Format
		addText(formatStrikethrough(text));
		return this;
	}
	
	@Override
	public AbstractTextConverter addCode(String text) {
		if(Util.isEmpty(text)) {
			return this;
		}
		log.fine("addCode=" + text);
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
		addText(formatExternalLink(text, link));
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
		formattedText.append(formatSeeAlso(name, internalLink));
		return this;
	}
	
	@Override
	public AbstractTextConverter addHeaderIndexName(String indexName) {
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
				String columnSeparator = " | ";
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
							headerLine.append(getParalelChar(columnText, "-"));
						}
					}
					//	For first
					if(isFirst) {
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
		//	**phrase**
		formattedValue.append("**").append(text.trim()).append("**");
		return formattedValue.toString();
	}
	
	/**
	 * Format as Italic
	 * @param text
	 * @return
	 */
	private String formatItalic(String text) {
		StringBuffer formattedValue = new StringBuffer();
		//	*phrase* 
		formattedValue.append("*").append(text.trim()).append("*");
		return formattedValue.toString();
	}
	
	/**
	 * Format as Strikethrough
	 * @param text
	 * @return
	 */
	private String formatStrikethrough(String text) {
		StringBuffer formattedValue = new StringBuffer();
		//	~~phrase~~ 
		formattedValue.append("~~").append(text.trim()).append("~~");
		return formattedValue.toString();
	}
	
	/**
	 * Format as Code
	 * @param text
	 * @return
	 */
	private String formatCode(String text) {
		StringBuffer formattedValue = new StringBuffer();
		//```
		//code
		//```
		formattedValue.append("```").append(Env.NL).append(text.trim()).append(Env.NL).append("```");
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
		String type = "-";
		if(isNumeric) {
			type = "1.";
		}
		formattedValue.append(leftSpace).append(type).append(" ").append(text);
		return formattedValue.toString();
	}
	
	/**
	 * Format as Quote
	 * @param text
	 * @return
	 */
	private String formatIndex(String text, int level) {
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
		String type = "-";
		formattedValue.append(leftSpace).append(type).append(" ").append(text);
		return formattedValue.toString();
	}
	
	/**
	 * Format Header
	 * @param text
	 * @param charValue
	 * @return
	 */
	private String formatHeader(String text, String charValue) {
		StringBuffer formattedValue = new StringBuffer(text.trim());
		formattedValue.append(Env.NL);
		formattedValue.append(charValue).append(" ").append(text.trim());
		formattedValue.append(Env.NL);
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
		StringBuffer formattedValue = new StringBuffer();
		//	
		formattedValue.append("[").append(text.trim()).append("]").append("(").append(link).append(")");
		return formattedValue.toString();
	}
	
	/**
	 * Format Section
	 * @param text
	 * @return
	 */
	private String formatSection(String text) {
		return formatHeader(text, "#");
	}
	
	/**
	 * Format Subsection
	 * @param text
	 * @return
	 */
	private String formatSubSection(String text) {
		return formatHeader(text, "##");
	}
	
	/**
	 * Format Subsubsection
	 * @param text
	 * @return
	 */
	private String formatSubSubSection(String text) {
		return formatHeader(text, "###");
	}
	
	/**
	 * Format paragraphs
	 * @param text
	 * @return
	 */
	private String formatParagraphs(String text) {
		return formatHeader(text, "");
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
		formattedValue.append("```")
			.append(Env.NL)
			.append(text.trim())
			.append(Env.NL)
			.append("```");
		return formattedValue.toString();
	}
	
	/**
	 * See Also
	 * @param internalLink
	 * @return
	 */
	private String formatSeeAlso(String name, String internalLink) {
		//	.. seealso::
		    //	:ref:`vdufun`
		StringBuffer formattedValue = new StringBuffer(Env.NL);
		//	
		if(Util.isEmpty(name)) {
			name = internalLink;
		}
		formattedValue.append(formatExternalLink(name, internalLink.trim() + "." + getExtension()));
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
		log.fine("toString=" + formattedText.toString());
		return formattedText.toString();
	}
	
	public static void main(String[] args) {
		MarkdownConverter formatter = new MarkdownConverter();
		formatter.addSection("Section");
		formatter.addSubSection("SubSection");
		formatter.addSubSubSection("SubSubSection");
		formatter.addChapter("Chapter");
		formatter.addPart("Part");
		formatter.addBold("Hi everybody");
		formatter.newLine();
		formatter.newLine();
		formatter.addBold("Hi");
		formatter.newLine();
		formatter.addItalic("Hello");
		formatter.newLine();
		formatter.addStrikethrough("Hello");
		formatter.newLine();
		formatter.addText("Hi all");
		formatter.newLine();
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
		previouslevel = -1;
	}

	@Override
	public String getExtension() {
		return "md";
	}
	
	@Override
	public String getIndexFileName() {
		return "index.md";
	}
	
	@Override
	public String getMainIndexFileName() {
		return "mkdocs.yml";
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
	public AbstractTextConverter getMainIndex() {
		return mainIndex;
	}
	
	/**
	 * Add link to main index
	 * @param title
	 * @param name
	 * @param folder
	 * @param margin
	 */
	private void addToMainIndex(String title, String name, String folder, int margin) {
		if(mainIndex == null) {
			mainIndex = new MarkdownConverter();
			mainIndex.addText("site_name: ERPCyA - Documentation");
			mainIndex.newLine();
			mainIndex.newLine();
			mainIndex.addText("nav:");
			mainIndex.newLine();
			mainIndex.newLine();
		}
		//	
		String fileName = "";
		if(margin == -1) {
			margin = 1;
		}
		if(!Util.isEmpty(folder)) {
			fileName = folder + File.separator + name.trim() + "." + getExtension();
		}
		for (char value : fileName.toCharArray())  { 
            // checking character in string 
            if (value == File.separatorChar) {
            	margin++;
            } 
        }
		mainIndex.newLine();
		mainIndex.addText(formatIndex(title + ": " + (Util.isEmpty(fileName)? "":"'" + fileName + "'") , margin*4));
	}

	@Override
	public AbstractTextConverter addTranslationText(String text) {
		translatedText.append(text);
		return this;
	}

	@Override
	public void addIndex(String title, String name, String folder, int margin) {
		addToMainIndex(title, name, folder, -1);
		addText(formatIndex(formatExternalLink(title, name.trim() + "." + getExtension()), margin));
	}

	@Override
	public void addGroup(String title, String name, int margin) {
		addToMainIndex(title, null, null, margin);
	}

	@Override
	public void addTreeDefinition(int maxdepth, boolean isnumbered) {
		newLine();
	}
}
