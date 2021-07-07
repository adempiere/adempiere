/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.print;

//import java.util.logging.*;

/**
 *	Print Data Column.
 * 	Optional Meta Data of Columns
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: PrintDataColumn.java,v 1.2 2006/07/30 00:53:02 jjanke Exp $
 * 
 *  @author Michael McKay, mckayERP@gmail.com
 * 		<li>BR [ <a href="https://github.com/adempiere/adempiere/issues/431">#431</a> ] Report Groups do not handle single values well
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * 	@See: https://github.com/adempiere/adempiere/issues/2873
 */
public class PrintDataColumn
{
	/**
	 * 	Print Data Column
	 *
	 * 	@param columnId Column
	 * 	@param columnName Column Name
	 * 	@param displayType Display Type
	 * 	@param columnSize Column Size
	 *  @param alias Alias in query or the same as column name or null
	 *  @param isPageBreak if true force page break after function
	 *  @param printFormatItemId reference
	 */
	public PrintDataColumn (int columnId, String columnName,
		int displayType, int columnSize,
		String alias, boolean isPageBreak, int printFormatItemId, boolean isHideGrandTotal)
	{
		this.columnId = columnId;
		this.columnName = columnName;
		//
		this.displayType = displayType;
		this.columnSize = columnSize;
		//
		this.alias = alias;
		if (alias == null)
			alias = columnName;
		pageBreak = isPageBreak;
		this.printFormatItemId = printFormatItemId;
		this.isHideGrandTotal = isHideGrandTotal;
	}	//	PrintDataColumn

	private int			columnId;
	private String		columnName;
	private int			displayType;
	private int			columnSize;
	private String		alias;
	private boolean		pageBreak;
	private String 		formatPattern;
	private int 		printFormatItemId;
	private boolean		isHideGrandTotal;
	
	/** The sort order index or -1 if not set.  
	 *  Lower numbers are sorted first.  If there
	 *  is no sort order index or the sort order index
	 *  is the same for two columns, sorting will be
	 *  assumed to occur in the order of display of 
	 *  the columns.  The sort order affects the level
	 *  of the groups in the report.   
	 */
	private int			sortOrderIndex = -1;

	/** The display order index or -1 if not set.  
	 *  Lower numbers are displayed on the left.  If there
	 *  is no display order index (<0) or the display order index
	 *  is the same for two columns, the display order is undefined.   
	 */
	private int			displayOrderIndex = -1;

	/*************************************************************************/

	/**
	 * 	Get AD_Column_ID
	 * 	@return AD_Column_ID
	 */
	public int getAD_Column_ID()
	{
		return columnId;
	}	//	getAD_Column_ID

	/**
	 * 	Get Column Name
	 * 	@return column name
	 */
	public String getColumnName()
	{
		return columnName;
	}	//	getColumnName

	/**
	 * 	Get Display Type
	 * 	@return display type
	 */
	public int getDisplayType()
	{
		return displayType;
	}	//	getDisplayType

	/**
	 * 	Get Alias Name
	 * 	@return alias column name
	 */
	public String getAlias()
	{
		return alias;
	}	//	getAlias

	/**
	 *	Column has Alias.
	 *  (i.e. has a key)
	 * 	@return true if Alias
	 */
	public boolean hasAlias()
	{
		return !columnName.equals(alias);
	}	//	hasAlias

	/**
	 * 	Column value forces page break
	 * 	@return true if page break
	 */
	public boolean isPageBreak()
	{
		return pageBreak;
	}	//	isPageBreak
	
	/**
	 * Get Print Format Item reference
	 * @return
	 */
	public int getPrinformatItemId() {
		return printFormatItemId;
	}
	
	/**
	 * Is exclude of total calculation
	 * @return
	 */
	public boolean isHideGrandTotal() {
		return isHideGrandTotal;
	}

	/**
	 *	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("PrintDataColumn[");
		sb.append("ID=").append(columnId)
			.append("-").append(columnName);
		if (hasAlias())
			sb.append("(").append(alias).append(")");
		sb.append(",DisplayType=").append(displayType)
			.append(",Size=").append(columnSize)
			.append("]");
		return sb.toString();
	}	//	toString

	public void setFormatPattern(String formatPattern) {
		this.formatPattern = formatPattern;
	}
	
	public String getFormatPattern() {
		return formatPattern;
	}
	
	/**
	 * @return the sortOrderIndex
	 */
	public int getSortOrderIndex() {
		return sortOrderIndex;
	}
	
	/**
	 * @param sortOrderIndex the sortOrderIndex to set
	 */
	public void setSortOrderIndex(int sortOrderIndex) {
		this.sortOrderIndex = sortOrderIndex;
	}
	
	/**
	 * @return the displayOrderIndex
	 */
	public int getDisplayOrderIndex() {
		return displayOrderIndex;
	}
	
	/**
	 * @param displayOrderIndex the displayOrderIndex to set
	 */
	public void setDisplayOrderIndex(int displayOrderIndex) {
		this.displayOrderIndex = displayOrderIndex;
	}

}	//	PrintDataColumn
