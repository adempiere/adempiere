/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
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
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_ChartDatasource
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_AD_ChartDatasource extends PO implements I_AD_ChartDatasource, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_AD_ChartDatasource (Properties ctx, int AD_ChartDatasource_ID, String trxName)
    {
      super (ctx, AD_ChartDatasource_ID, trxName);
      /** if (AD_ChartDatasource_ID == 0)
        {
			setAD_ChartDatasource_ID (0);
			setAD_Chart_ID (0);
			setEntityType (null);
// D
			setFromClause (null);
			setName (null);
			setValueColumn (null);
			setWhereClause (null);
// WHERE ...
        } */
    }

    /** Load Constructor */
    public X_AD_ChartDatasource (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_AD_ChartDatasource[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Chart Datasource.
		@param AD_ChartDatasource_ID Chart Datasource	  */
	public void setAD_ChartDatasource_ID (int AD_ChartDatasource_ID)
	{
		if (AD_ChartDatasource_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ChartDatasource_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ChartDatasource_ID, Integer.valueOf(AD_ChartDatasource_ID));
	}

	/** Get Chart Datasource.
		@return Chart Datasource	  */
	public int getAD_ChartDatasource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ChartDatasource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Chart getAD_Chart() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Chart)MTable.get(getCtx(), org.compiere.model.I_AD_Chart.Table_Name)
			.getPO(getAD_Chart_ID(), get_TrxName());	}

	/** Set Chart.
		@param AD_Chart_ID Chart	  */
	public void setAD_Chart_ID (int AD_Chart_ID)
	{
		if (AD_Chart_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Chart_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Chart_ID, Integer.valueOf(AD_Chart_ID));
	}

	/** Get Chart.
		@return Chart	  */
	public int getAD_Chart_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Chart_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
			.getPO(getAD_Table_ID(), get_TrxName());	}

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Category Column.
		@param CategoryColumn 
		Fully qualified data category column
	  */
	public void setCategoryColumn (String CategoryColumn)
	{
		set_Value (COLUMNNAME_CategoryColumn, CategoryColumn);
	}

	/** Get Category Column.
		@return Fully qualified data category column
	  */
	public String getCategoryColumn () 
	{
		return (String)get_Value(COLUMNNAME_CategoryColumn);
	}

	/** Set Date Column.
		@param DateColumn 
		Fully qualified date column
	  */
	public void setDateColumn (String DateColumn)
	{
		set_Value (COLUMNNAME_DateColumn, DateColumn);
	}

	/** Get Date Column.
		@return Fully qualified date column
	  */
	public String getDateColumn () 
	{
		return (String)get_Value(COLUMNNAME_DateColumn);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** EntityType AD_Reference_ID=389 */
	public static final int ENTITYTYPE_AD_Reference_ID=389;
	/** Set Entity Type.
		@param EntityType 
		Dictionary Entity Type; Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType)
	{

		set_Value (COLUMNNAME_EntityType, EntityType);
	}

	/** Get Entity Type.
		@return Dictionary Entity Type; Determines ownership and synchronization
	  */
	public String getEntityType () 
	{
		return (String)get_Value(COLUMNNAME_EntityType);
	}

	/** Set Sql FROM.
		@param FromClause 
		SQL FROM clause
	  */
	public void setFromClause (String FromClause)
	{
		set_Value (COLUMNNAME_FromClause, FromClause);
	}

	/** Get Sql FROM.
		@return SQL FROM clause
	  */
	public String getFromClause () 
	{
		return (String)get_Value(COLUMNNAME_FromClause);
	}

	/** Set Key Column.
		@param KeyColumn 
		Key Column for Table
	  */
	public void setKeyColumn (String KeyColumn)
	{
		set_Value (COLUMNNAME_KeyColumn, KeyColumn);
	}

	/** Get Key Column.
		@return Key Column for Table
	  */
	public String getKeyColumn () 
	{
		return (String)get_Value(COLUMNNAME_KeyColumn);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Series Column.
		@param SeriesColumn Series Column	  */
	public void setSeriesColumn (String SeriesColumn)
	{
		set_Value (COLUMNNAME_SeriesColumn, SeriesColumn);
	}

	/** Get Series Column.
		@return Series Column	  */
	public String getSeriesColumn () 
	{
		return (String)get_Value(COLUMNNAME_SeriesColumn);
	}

	/** Set Time Offset.
		@param TimeOffset 
		Number of time units to offset displayed chart data from the current date.
	  */
	public void setTimeOffset (int TimeOffset)
	{
		set_Value (COLUMNNAME_TimeOffset, Integer.valueOf(TimeOffset));
	}

	/** Get Time Offset.
		@return Number of time units to offset displayed chart data from the current date.
	  */
	public int getTimeOffset () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TimeOffset);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}

	/** Set Value Column.
		@param ValueColumn 
		Fully qualified data value column
	  */
	public void setValueColumn (String ValueColumn)
	{
		set_Value (COLUMNNAME_ValueColumn, ValueColumn);
	}

	/** Get Value Column.
		@return Fully qualified data value column
	  */
	public String getValueColumn () 
	{
		return (String)get_Value(COLUMNNAME_ValueColumn);
	}

	/** Set Sql WHERE.
		@param WhereClause 
		Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause)
	{
		set_Value (COLUMNNAME_WhereClause, WhereClause);
	}

	/** Get Sql WHERE.
		@return Fully qualified SQL WHERE clause
	  */
	public String getWhereClause () 
	{
		return (String)get_Value(COLUMNNAME_WhereClause);
	}
}