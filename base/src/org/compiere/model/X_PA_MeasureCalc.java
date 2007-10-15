/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import org.compiere.util.*;

/** Generated Model for PA_MeasureCalc
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_PA_MeasureCalc extends PO implements I_PA_MeasureCalc, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PA_MeasureCalc (Properties ctx, int PA_MeasureCalc_ID, String trxName)
    {
      super (ctx, PA_MeasureCalc_ID, trxName);
      /** if (PA_MeasureCalc_ID == 0)        {			setAD_Table_ID (0);
			setDateColumn (null);
// x.Date
			setEntityType (null);
// U
			setKeyColumn (null);
			setName (null);
			setOrgColumn (null);
// x.AD_Org_ID
			setPA_MeasureCalc_ID (0);
			setSelectClause (null);
// SELECT ... FROM ...
			setWhereClause (null);
// WHERE ...
} */
    }

    /** Load Constructor */
    public X_PA_MeasureCalc (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_PA_MeasureCalc[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Table getI_AD_Table() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1)
			 throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
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

	/** Set B.Partner Column.
		@param BPartnerColumn 
		Fully qualified Business Partner key column (C_BPartner_ID)
	  */
	public void setBPartnerColumn (String BPartnerColumn)
	{
		if (BPartnerColumn != null && BPartnerColumn.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			BPartnerColumn = BPartnerColumn.substring(0, 59);
		}
		set_Value (COLUMNNAME_BPartnerColumn, BPartnerColumn);
	}

	/** Get B.Partner Column.
		@return Fully qualified Business Partner key column (C_BPartner_ID)
	  */
	public String getBPartnerColumn () 
	{
		return (String)get_Value(COLUMNNAME_BPartnerColumn);
	}

	/** Set Date Column.
		@param DateColumn 
		Fully qualified date column
	  */
	public void setDateColumn (String DateColumn)
	{
		if (DateColumn == null)
			throw new IllegalArgumentException ("DateColumn is mandatory.");
		if (DateColumn.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			DateColumn = DateColumn.substring(0, 59);
		}
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
		if (Description != null && Description.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			Description = Description.substring(0, 254);
		}
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
		if (EntityType.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			EntityType = EntityType.substring(0, 40);
		}
		set_Value (COLUMNNAME_EntityType, EntityType);
	}

	/** Get Entity Type.
		@return Dictionary Entity Type; Determines ownership and synchronization
	  */
	public String getEntityType () 
	{
		return (String)get_Value(COLUMNNAME_EntityType);
	}

	/** Set Key Column.
		@param KeyColumn 
		Key Column for Table
	  */
	public void setKeyColumn (String KeyColumn)
	{
		if (KeyColumn == null)
			throw new IllegalArgumentException ("KeyColumn is mandatory.");
		if (KeyColumn.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			KeyColumn = KeyColumn.substring(0, 59);
		}
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
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		if (Name.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			Name = Name.substring(0, 59);
		}
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

	/** Set Org Column.
		@param OrgColumn 
		Fully qualified Organization column (AD_Org_ID)
	  */
	public void setOrgColumn (String OrgColumn)
	{
		if (OrgColumn == null)
			throw new IllegalArgumentException ("OrgColumn is mandatory.");
		if (OrgColumn.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			OrgColumn = OrgColumn.substring(0, 59);
		}
		set_Value (COLUMNNAME_OrgColumn, OrgColumn);
	}

	/** Get Org Column.
		@return Fully qualified Organization column (AD_Org_ID)
	  */
	public String getOrgColumn () 
	{
		return (String)get_Value(COLUMNNAME_OrgColumn);
	}

	/** Set Measure Calculation.
		@param PA_MeasureCalc_ID 
		Calculation method for measuring performance
	  */
	public void setPA_MeasureCalc_ID (int PA_MeasureCalc_ID)
	{
		if (PA_MeasureCalc_ID < 1)
			 throw new IllegalArgumentException ("PA_MeasureCalc_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PA_MeasureCalc_ID, Integer.valueOf(PA_MeasureCalc_ID));
	}

	/** Get Measure Calculation.
		@return Calculation method for measuring performance
	  */
	public int getPA_MeasureCalc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_MeasureCalc_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Column.
		@param ProductColumn 
		Fully qualified Product column (M_Product_ID)
	  */
	public void setProductColumn (String ProductColumn)
	{
		if (ProductColumn != null && ProductColumn.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			ProductColumn = ProductColumn.substring(0, 59);
		}
		set_Value (COLUMNNAME_ProductColumn, ProductColumn);
	}

	/** Get Product Column.
		@return Fully qualified Product column (M_Product_ID)
	  */
	public String getProductColumn () 
	{
		return (String)get_Value(COLUMNNAME_ProductColumn);
	}

	/** Set Sql SELECT.
		@param SelectClause 
		SQL SELECT clause
	  */
	public void setSelectClause (String SelectClause)
	{
		if (SelectClause == null)
			throw new IllegalArgumentException ("SelectClause is mandatory.");
		if (SelectClause.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			SelectClause = SelectClause.substring(0, 1999);
		}
		set_Value (COLUMNNAME_SelectClause, SelectClause);
	}

	/** Get Sql SELECT.
		@return SQL SELECT clause
	  */
	public String getSelectClause () 
	{
		return (String)get_Value(COLUMNNAME_SelectClause);
	}

	/** Set Sql WHERE.
		@param WhereClause 
		Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause)
	{
		if (WhereClause == null)
			throw new IllegalArgumentException ("WhereClause is mandatory.");
		if (WhereClause.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			WhereClause = WhereClause.substring(0, 1999);
		}
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