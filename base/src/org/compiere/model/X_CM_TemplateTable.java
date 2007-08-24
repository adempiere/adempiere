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

/** Generated Model for CM_TemplateTable
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_CM_TemplateTable extends PO implements I_CM_TemplateTable, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_CM_TemplateTable (Properties ctx, int CM_TemplateTable_ID, String trxName)
    {
      super (ctx, CM_TemplateTable_ID, trxName);
      /** if (CM_TemplateTable_ID == 0)        {			setAD_Table_ID (0);
			setCM_TemplateTable_ID (0);
			setCM_Template_ID (0);
			setName (null);
} */
    }

    /** Load Constructor */
    public X_CM_TemplateTable (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_CM_TemplateTable[")
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

	/** Set Template Table.
		@param CM_TemplateTable_ID 
		CM Template Table Link
	  */
	public void setCM_TemplateTable_ID (int CM_TemplateTable_ID)
	{
		if (CM_TemplateTable_ID < 1)
			 throw new IllegalArgumentException ("CM_TemplateTable_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CM_TemplateTable_ID, Integer.valueOf(CM_TemplateTable_ID));
	}

	/** Get Template Table.
		@return CM Template Table Link
	  */
	public int getCM_TemplateTable_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_TemplateTable_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_CM_Template getI_CM_Template() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_CM_Template.Table_Name);
        I_CM_Template result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_CM_Template)constructor.newInstance(new Object[] {getCtx(), new Integer(getCM_Template_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Template.
		@param CM_Template_ID 
		Template defines how content is displayed
	  */
	public void setCM_Template_ID (int CM_Template_ID)
	{
		if (CM_Template_ID < 1)
			 throw new IllegalArgumentException ("CM_Template_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CM_Template_ID, Integer.valueOf(CM_Template_ID));
	}

	/** Get Template.
		@return Template defines how content is displayed
	  */
	public int getCM_Template_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_Template_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		if (Name.length() > 120)
		{
			log.warning("Length > 120 - truncated");
			Name = Name.substring(0, 119);
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

	/** Set Other SQL Clause.
		@param OtherClause 
		Other SQL Clause
	  */
	public void setOtherClause (String OtherClause)
	{
		if (OtherClause != null && OtherClause.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			OtherClause = OtherClause.substring(0, 1999);
		}
		set_Value (COLUMNNAME_OtherClause, OtherClause);
	}

	/** Get Other SQL Clause.
		@return Other SQL Clause
	  */
	public String getOtherClause () 
	{
		return (String)get_Value(COLUMNNAME_OtherClause);
	}

	/** Set Sql WHERE.
		@param WhereClause 
		Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause)
	{
		if (WhereClause != null && WhereClause.length() > 2000)
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