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
package org.compiere.minigrid;

import org.compiere.model.GridField;

/**
 *  Info Column Details
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ColumnInfo.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 */
public class ColumnInfo
{
	/**
	 *  Create Info Column (r/o and not color column)
	 *
	 *  @param colHeader Column Header
	 *  @param colSQL    SQL select code for column
	 *  @param colClass  class of column - determines display
	 */
	public ColumnInfo (String colHeader, String colSQL, Class<?> colClass)
	{
		this(colHeader, colSQL, colClass, true, false, null);
	}   //  ColumnInfo

	/**
	 * Create Info Column (r/o and not color column)
	 *
	 * @param colHeader Column Header
	 * @param colSQL SQL select code for column
	 * @param colClass class of column - determines display
	 * @param readOnly column is read only
	 */
	public ColumnInfo(String colHeader, String colSQL, Class<?> colClass, boolean readOnly)
	{
		this(colHeader, colSQL, colClass, readOnly, false, null);
	} // ColumnInfo

	/**
	 *  Create Info Column (r/o and not color column)
	 *
	 *  @param colHeader Column Header
	 *  @param colSQL    SQL select code for column
	 *  @param colClass  class of column - determines display
	 *  @param keyPairColSQL  SQL select for the ID of the for the displayed column
	 */
	public ColumnInfo (String colHeader, String colSQL, Class<?> colClass, String keyPairColSQL)
	{
		this(colHeader, colSQL, colClass, true, false, keyPairColSQL);
	}   //  ColumnInfo

	/**
	 * Create Info Column (r/o and not color column)
	 *
	 * @param colHeader Column Header
	 * @param colSQL SQL select code for column
	 * @param colClass class of column - determines display
	 * @param keyPairColSQL SQL select for the ID of the for the displayed
	 *            column
	 * @param readOnly column is read only
	 */
	public ColumnInfo(String colHeader, String colSQL, Class<?> colClass, String keyPairColSQL, boolean readOnly)
	{
		this(colHeader, colSQL, colClass, readOnly, false, keyPairColSQL);
	} // ColumnInfo

	/**
	 *  Create Info Column
	 *
	 *  @param colHeader Column Header
	 *  @param colSQL    SQL select code for column
	 *  @param colClass  class of column - determines display
	 *  @param readOnly  column is read only
	 *  @param colorColumn   if true, value of column determines foreground color
	 *  @param keyPairColSQL  SQL select for the ID of the for the displayed column
	 */
	public ColumnInfo (String colHeader, String colSQL, Class<?> colClass, 
		boolean readOnly, boolean colorColumn, String keyPairColSQL)
	{
		this(colHeader, colSQL, colClass, 0 ,
				readOnly,  colorColumn, keyPairColSQL, true);
	}
	
	/**
	 *  Create Info Column
	 *
	 *  @param colHeader Column Header
	 *  @param colSQL    SQL select code for column
	 *  @param colClass  class of column - determines display
	 *  @param readOnly  column is read only
	 *  @param colorColumn   if true, value of column determines foreground color
	 *  @param keyPairColSQL  SQL select for the ID of the for the displayed column
	 */
	public ColumnInfo (String colHeader, String colSQL, Class<?> colClass, int displayType,
		boolean readOnly, boolean colorColumn, String keyPairColSQL)
	{
		this(colHeader, colSQL, colClass, 0 ,
				readOnly,  colorColumn, keyPairColSQL, true);		
	}

	/**
	 *  Create Info Column
	 *
	 *  @param colHeader Column Header
	 *  @param colSQL    SQL select code for column
	 *  @param colClass  class of column - determines display
	 *  @param readOnly  column is read only
	 *  @param colorColumn   if true, value of column determines foreground color
	 *  @param keyPairColSQL  SQL select for the ID of the for the displayed column
	 *  @param visible  if true, the column will be visible.  False, it will be hidden.
	 */
	public ColumnInfo (String colHeader, String colSQL, Class<?> colClass, int displayType,
		boolean readOnly, boolean colorColumn, String keyPairColSQL, boolean visible)
	{
		setColHeader(colHeader);
		setColSQL(colSQL);
		setColClass(colClass);
		setReadOnly(readOnly);
		setColorColumn(colorColumn);
		setKeyPairColSQL(keyPairColSQL);
		setDisplayType(displayType);
		setVisibility(visible);
	}   //  ColumnInfo
	
	/**
	 *  Create Info Column
	 *
	 *  @param colHeader Column Header
	 *  @param colSQL    SQL select code for column
	 *  @param colClass  class of column - determines display
	 *  @param readOnly  column is read only
	 *  @param colorColumn   if true, value of column determines foreground color
	 *  @param keyPairColSQL  SQL select for the ID of the for the displayed column
	 *  @param colWidth  optional fixed width for column (-1 autosizes)
	 */
	public ColumnInfo (String colHeader, String colSQL, Class<?> colClass, 
		boolean readOnly, boolean colorColumn, String keyPairColSQL, int colWidth)
	{
		setColHeader(colHeader);
		setColSQL(colSQL);
		setColClass(colClass);
		setReadOnly(readOnly);
		setColorColumn(colorColumn);
		setKeyPairColSQL(keyPairColSQL);
		m_colWidth = colWidth;
	}   //  ColumnInfo


	private String      m_colHeader;
	private String      m_colSQL;
	private Class<?>	m_colClass;
	private boolean     m_readOnly;
	private boolean     m_colorColumn;
	private String      m_keyPairColSQL = "";
	private int      	m_DisplayType;
	private boolean		m_isVisible;

	private int			m_colWidth = -1;
	private GridField	m_gridField;

	public int getColWidth() {
		return m_colWidth;
	}

	public void setColWidth(int colWidth) {
		m_colWidth = colWidth;
	}
	/**
	 * 	Get Col Class
	 *	@return class
	 */
	public Class<?> getColClass()
	{
		return m_colClass;
	}
	/**
	 * 	Get Col Header
	 *	@return header
	 */
	public String getColHeader()
	{
		return m_colHeader;
	}
	/**
	 * 	Get Col SQL
	 *	@return sql
	 */
	public String getColSQL()
	{
		return m_colSQL;
	}
	/**
	 * 	ReadOnly
	 *	@return r/o
	 */
	public boolean isReadOnly()
	{
		return m_readOnly;
	}
	/**
	 * 	Set ColClass
	 *	@param colClass class
	 */
	public void setColClass(Class<?> colClass)
	{
		m_colClass = colClass;
	}
	/**
	 * 	Set Col Header
	 *	@param colHeader header
	 */
	public void setColHeader(String colHeader)
	{
		m_colHeader = colHeader;
		if (colHeader != null)
		{
			int index = colHeader.indexOf('&');
			if (index != -1)
				m_colHeader = colHeader.substring(0, index) + colHeader.substring(index+1); 
		}
	}
	/**
	 * 	Set Col SQL
	 *	@param colSQL sql
	 */
	public void setColSQL(String colSQL)
	{
		m_colSQL = colSQL;
	}
	/**
	 * 	Set Read Only
	 *	@param readOnly r/o
	 */
	public void setReadOnly(boolean readOnly)
	{
		m_readOnly = readOnly;
	}
	/**
	 * 	Set Color Column
	 *	@param colorColumn color
	 */
	public void setColorColumn(boolean colorColumn)
	{
		m_colorColumn = colorColumn;
	}
	/**
	 * 	ColorColumn
	 *	@return true if color column
	 */
	public boolean isColorColumn()
	{
		return m_colorColumn;
	}
	/**
	 *  Add ID column SQL for the displayed column
	 *  The Class for this should be KeyNamePair
	 *  @param keyPairColSQL
	 */
	public void setKeyPairColSQL(String keyPairColSQL)
	{
		m_keyPairColSQL = keyPairColSQL;
		if (m_keyPairColSQL == null)
			m_keyPairColSQL = "";
	}
	/**
	 * 	Get Key Pair Col SQL
	 *	@return sql
	 */
	public String getKeyPairColSQL()
	{
		return m_keyPairColSQL;
	}
	/**
	 * 	Key Pair Col
	 *	@return column
	 */
	public boolean isKeyPairCol()
	{
		return m_keyPairColSQL.length() > 0;
	}
	
	/**
	 * 	Set Display Type
	 *	@param display Type
	 */
	public void setDisplayType(int displayType)
	{
		m_DisplayType = displayType;
	}
	/**
	 * 	Display Type
	 *	@return display Type
	 */
	public int getDisplayType()
	{
		return m_DisplayType;
	}

	/**
	 * @param m_isVisible the m_isVisible to set
	 */
	public void setVisibility(boolean m_isVisible) {
		this.m_isVisible = m_isVisible;
	}

	/**
	 * @return the m_isVisible
	 */
	public boolean getVisibility() {
		return m_isVisible;
	}

	public GridField getGridField()
	{
		return m_gridField;
	}

	public void setGridField(GridField gridField)
	{
		m_gridField = gridField;
	}
}   //  infoColumn
