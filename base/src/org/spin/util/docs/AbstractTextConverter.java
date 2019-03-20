/*************************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.util.docs;

import org.compiere.util.Util;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * Abstract class for Text converter
 * @see: https://github.com/adempiere/adempiere/issues/1934
 * For formst reference use: http://www.sphinx-doc.org/en/master/usage/restructuredtext/basics.html
 */
public abstract class AbstractTextConverter {

	/**
	 * Default constructor
	 */
	public AbstractTextConverter() {
		//	
	}
	
	/**
	 * Add simple text
	 * @param text
	 * @return
	 */
	public abstract AbstractTextConverter addText(String text);
	
	/**
	 * Add simple text
	 * @param text
	 * @param margin
	 * @return
	 */
	public abstract AbstractTextConverter addText(String text, int margin);
	
	/**
	 * Add a text as bold
	 * @param text
	 * @return
	 */
	public abstract AbstractTextConverter addBold(String text);
	
	/**
	 * Add text as Italic
	 * @param text
	 * @return
	 */
	public abstract AbstractTextConverter addItalic(String text);
	
	/**
	 * Add text as Strikethrough
	 * @param text
	 * @return
	 */
	public abstract AbstractTextConverter addStrikethrough(String text);
	
	/**
	 * Add text as code
	 * @param text
	 * @return
	 */
	public abstract AbstractTextConverter addCode(String text);
	
	/**
	 * Add Quote with specific level
	 * @param text
	 * @param level
	 * @return
	 */
	public abstract AbstractTextConverter addQuote(String text, int level);
	
	/**
	 * Add Quote
	 * @param text
	 * @param level
	 * @return
	 */
	public abstract AbstractTextConverter addQuote(String text);
	
	/**
	 * Add Quote with numeric and with specific level
	 * @param text
	 * @param level
	 * @return
	 */
	public abstract AbstractTextConverter addQuoteNumeric(String text, int level);
	
	/**
	 * Add Numeric Quote
	 * @param text
	 * @return
	 */
	public abstract AbstractTextConverter addQuoteNumeric(String text);
	
	/**
	 * Add Section
	 * @param text
	 * @return
	 */
	public abstract AbstractTextConverter addSection(String text);
	
	/**
	 * Add sub-Section
	 * @param text
	 * @return
	 */
	public abstract AbstractTextConverter addSubSection(String text);
	
	/**
	 * Add subsubsection
	 * @param text
	 * @return
	 */
	public abstract AbstractTextConverter addSubSubSection(String text);
	
	/**
	 * Add Paragraph
	 * @param text
	 * @return
	 */
	public abstract AbstractTextConverter addParagraph(String text);
	
	/**
	 * Add Part
	 * @param text
	 * @return
	 */
	public abstract AbstractTextConverter addPart(String text);
	
	/**
	 * Add Chapter
	 * @param text
	 * @return
	 */
	public abstract AbstractTextConverter addChapter(String text);
	
	/**
	 * Add External Link
	 * @param text
	 * @param link
	 * @return
	 */
	public abstract AbstractTextConverter addExternalLink(String text, String link);
	
	/**
	 * Add Note
	 * @param text
	 * @return
	 */
	public abstract AbstractTextConverter addNote(String text);
	
	/**
	 * Add a table for text
	 * @param table
	 * @return
	 */
	public abstract AbstractTextConverter addTable(TableTextConverter table);
	
	/**
	 * Clear values
	 */
	public abstract void clear();
	
	/**
	 * Get File extension
	 * @return
	 */
	public abstract String getExtension();
	
	/**
	 * Add new Line
	 * @return
	 */
	public abstract AbstractTextConverter newLine();
	
	/**
	 * Add Internal Link
	 * @param internalLink
	 * @return
	 */
	public abstract AbstractTextConverter addSeeAlso(String internalLink);
	
	/**
	 * Add internal link with name
	 * @param name
	 * @param internalLink
	 * @return
	 */
	public abstract AbstractTextConverter addSeeAlso(String name, String internalLink);
	
	/**
	 * Add Header Index Name used for make link from other file
	 * @param indexName
	 * @return
	 */
	public abstract AbstractTextConverter addHeaderIndexName(String indexName);
	
	/**
	 * Add Comment that is not view for end user
	 * @param comment
	 * @return
	 */
	public abstract AbstractTextConverter addComment(String comment);
	
	/**
	 * Get file name for index
	 * @return
	 */
	public String getIndexFileName() {
		return null;
	}
	
	/**
	 * Get Main index name for global index
	 * @return
	 */
	public String getMainIndexFileName() {
		return null;
	}
	
	/**
	 * Add Main index reference
	 * @param title
	 * @param path
	 * @param margin
	 * @return
	 */
	public AbstractTextConverter getMainIndex() {
		return this;
	}
	
	/**
	 * add translation text
	 * @param text
	 * @return
	 */
	public abstract AbstractTextConverter addTranslationText(String text);
	
	
	/**
	 * Validate if is numeric
	 * @param value
	 * @return
	 */
	public boolean isNumeric(String value) {
		if(Util.isEmpty(value)) {
			return false;
		}
		//	
		return value.matches("[+-]?\\d*(\\.\\d+)?");
	}
}
