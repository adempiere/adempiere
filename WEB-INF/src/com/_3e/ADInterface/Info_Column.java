/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package com._3e.ADInterface;

/**
 *  Info Column Details
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: Info_Column.java,v 1.7 2005/12/17 19:55:47 jjanke Exp $
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

