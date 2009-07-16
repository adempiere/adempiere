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
package org.eevolution.form;

import org.compiere.minigrid.ColumnInfo;


public class BrowserColumn extends ColumnInfo
{
	
	private int m_AD_Column_ID = -1;
	private String m_ColumnName = null;
	
	
	/**
	 *  Create Info Column (r/o and not color column)
	 *
	 *  @param colHeader Column Header
	 *  @param colSQL    SQL select code for column
	 *  @param colClass  class of column - determines display
	 */
	public BrowserColumn (String columnName, int AD_Column_ID, String colHeader, String colSQL, Class<?> colClass)
	{
		super(colHeader, colSQL, colClass);
		m_AD_Column_ID = AD_Column_ID;
		m_ColumnName = columnName;
	}   //  Info_Column

	/**
	 *  Create Info Column (r/o and not color column)
	 *
	 *  @param colHeader Column Header
	 *  @param colSQL    SQL select code for column
	 *  @param colClass  class of column - determines display
	 *  @param IDcolSQL  SQL select for the ID of the for the displayed column (KeyNamePair)
	 */
	public BrowserColumn (String TableName,int AD_Column_ID, String colHeader, String colSQL, Class<?> colClass, String IDcolSQL)
	{
		super(colHeader, colSQL, colClass, true, false, IDcolSQL);
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
	 public BrowserColumn (String colHeader, String colSQL, Class<?> colClass, boolean readOnly)
	 {
	    super(colHeader, colSQL, colClass, readOnly, false, null);
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
	public BrowserColumn (String colHeader, String colSQL, Class<?> colClass, 
		boolean readOnly, boolean colorColumn, String IDcolSQL)
	{
		super(colHeader, colSQL, colClass, readOnly, colorColumn, IDcolSQL);
	}   //  Info_Column
	public void setIDcolSQL(String IDcolSQL)
	{
		super.setKeyPairColSQL(IDcolSQL);
	}
	public String getIDcolSQL()
	{
		return super.getKeyPairColSQL();
	}
	public boolean isIDcol()
	{
		return super.isKeyPairCol();
	}
	
	public int getAD_Column_ID()
	{
		return m_AD_Column_ID;
	}
	
	public String getColumnName()
	{
		return m_ColumnName;
	}
}   //  infoColum
