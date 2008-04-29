/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.apps.search;

/**
 *  Info Column Details
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: Info_Column.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public class Info_Column
{
	/**
	 *  Create Info Column (r/o and not color column)
	 *
	 *  @param colHeader Column Header
	 *  @param colSQL    SQL select code for column
	 *  @param colClass  class of column - determines display
	 */
	public Info_Column (String colHeader, String colSQL, Class colClass)
	{
		this(colHeader, colSQL, colClass, true, false, null);
	}   //  Info_Column

	/**
	 *  Create Info Column (r/o and not color column)
	 *
	 *  @param colHeader Column Header
	 *  @param colSQL    SQL select code for column
	 *  @param colClass  class of column - determines display
	 *  @param IDcolSQL  SQL select for the ID of the for the displayed column (KeyNamePair)
	 */
	public Info_Column (String colHeader, String colSQL, Class colClass, String IDcolSQL)
	{
		this(colHeader, colSQL, colClass, true, false, IDcolSQL);
	}   //  Info_Column
	
	/**
	 *  Create Info Column (not color column)
	 *
	 *  @param colHeader Column Header
	 *  @param colSQL    SQL select code for column
	 *  @param colClass  class of column - determines display
	 *  @param readOnly  column is read only
	 *  @author ashley
	 */
	 public Info_Column (String colHeader, String colSQL, Class colClass, boolean readOnly)
	 {
	    this(colHeader, colSQL, colClass, readOnly, false, null);
	 }   //  Info_Column

	/**
	 *  Create Info Column
	 *
	 *  @param colHeader Column Header
	 *  @param colSQL    SQL select code for column
	 *  @param colClass  class of column - determines display
	 *  @param readOnly  column is read only
	 *  @param colorColumn   if true, value of column determines foreground color
	 *  @param IDcolSQL  SQL select for the ID of the for the displayed column
	 */
	public Info_Column (String colHeader, String colSQL, Class colClass, 
		boolean readOnly, boolean colorColumn, String IDcolSQL)
	{
		setColHeader(colHeader);
		setColSQL(colSQL);
		setColClass(colClass);
		setReadOnly(readOnly);
		setColorColumn(colorColumn);
		setIDcolSQL(IDcolSQL);
	}   //  Info_Column


	private String      m_colHeader;
	private String      m_colSQL;
	private Class       m_colClass;
	private boolean     m_readOnly;
	private boolean     m_colorColumn;
	private String      m_IDcolSQL = "";

	public Class getColClass()
	{
		return m_colClass;
	}
	public String getColHeader()
	{
		return m_colHeader;
	}
	public String getColSQL()
	{
		return m_colSQL;
	}
	public boolean isReadOnly()
	{
		return m_readOnly;
	}
	public void setColClass(Class colClass)
	{
		m_colClass = colClass;
	}
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
	public void setColSQL(String colSQL)
	{
		m_colSQL = colSQL;
	}
	public void setReadOnly(boolean readOnly)
	{
		m_readOnly = readOnly;
	}
	public void setColorColumn(boolean colorColumn)
	{
		m_colorColumn = colorColumn;
	}
	public boolean isColorColumn()
	{
		return m_colorColumn;
	}
	/**
	 *  Add ID column SQL for the displayed column
	 *  The Class for this should be KeyNamePair
	 */
	public void setIDcolSQL(String IDcolSQL)
	{
		m_IDcolSQL = IDcolSQL;
		if (m_IDcolSQL == null)
			m_IDcolSQL = "";
	}
	public String getIDcolSQL()
	{
		return m_IDcolSQL;
	}
	public boolean isIDcol()
	{
		return m_IDcolSQL.length() > 0;
	}
}   //  infoColumn
