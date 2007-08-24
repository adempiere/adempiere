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

/** Generated Model for M_Locator
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_M_Locator extends PO implements I_M_Locator, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_Locator (Properties ctx, int M_Locator_ID, String trxName)
    {
      super (ctx, M_Locator_ID, trxName);
      /** if (M_Locator_ID == 0)        {			setIsDefault (false);
			setM_Locator_ID (0);
			setM_Warehouse_ID (0);
			setPriorityNo (0);
// 50
			setValue (null);
			setX (null);
			setY (null);
			setZ (null);
} */
    }

    /** Load Constructor */
    public X_M_Locator (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_M_Locator[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1)
			 throw new IllegalArgumentException ("M_Locator_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Warehouse getI_M_Warehouse() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_Warehouse.Table_Name);
        I_M_Warehouse result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Warehouse)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Warehouse_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1)
			 throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Relative Priority.
		@param PriorityNo 
		Where inventory should be picked from first
	  */
	public void setPriorityNo (int PriorityNo)
	{
		set_Value (COLUMNNAME_PriorityNo, Integer.valueOf(PriorityNo));
	}

	/** Get Relative Priority.
		@return Where inventory should be picked from first
	  */
	public int getPriorityNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PriorityNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		if (Value.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			Value = Value.substring(0, 39);
		}
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }

	/** Set Aisle (X).
		@param X 
		X dimension, e.g., Aisle
	  */
	public void setX (String X)
	{
		if (X == null)
			throw new IllegalArgumentException ("X is mandatory.");
		if (X.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			X = X.substring(0, 59);
		}
		set_Value (COLUMNNAME_X, X);
	}

	/** Get Aisle (X).
		@return X dimension, e.g., Aisle
	  */
	public String getX () 
	{
		return (String)get_Value(COLUMNNAME_X);
	}

	/** Set Bin (Y).
		@param Y 
		Y dimension, e.g., Bin
	  */
	public void setY (String Y)
	{
		if (Y == null)
			throw new IllegalArgumentException ("Y is mandatory.");
		if (Y.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			Y = Y.substring(0, 59);
		}
		set_Value (COLUMNNAME_Y, Y);
	}

	/** Get Bin (Y).
		@return Y dimension, e.g., Bin
	  */
	public String getY () 
	{
		return (String)get_Value(COLUMNNAME_Y);
	}

	/** Set Level (Z).
		@param Z 
		Z dimension, e.g., Level
	  */
	public void setZ (String Z)
	{
		if (Z == null)
			throw new IllegalArgumentException ("Z is mandatory.");
		if (Z.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			Z = Z.substring(0, 59);
		}
		set_Value (COLUMNNAME_Z, Z);
	}

	/** Get Level (Z).
		@return Z dimension, e.g., Level
	  */
	public String getZ () 
	{
		return (String)get_Value(COLUMNNAME_Z);
	}
}