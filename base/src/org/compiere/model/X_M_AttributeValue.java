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

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_AttributeValue
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1b - $Id$ */
public class X_M_AttributeValue extends PO implements I_M_AttributeValue, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_AttributeValue (Properties ctx, int M_AttributeValue_ID, String trxName)
    {
      super (ctx, M_AttributeValue_ID, trxName);
      /** if (M_AttributeValue_ID == 0)
        {
			setM_AttributeValue_ID (0);
			setM_Attribute_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_M_AttributeValue (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_M_AttributeValue[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			Description = Description.substring(0, 255);
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

	/** Set Attribute Value.
		@param M_AttributeValue_ID 
		Product Attribute Value
	  */
	public void setM_AttributeValue_ID (int M_AttributeValue_ID)
	{
		if (M_AttributeValue_ID < 1)
			 throw new IllegalArgumentException ("M_AttributeValue_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_AttributeValue_ID, Integer.valueOf(M_AttributeValue_ID));
	}

	/** Get Attribute Value.
		@return Product Attribute Value
	  */
	public int getM_AttributeValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Attribute getM_Attribute() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_Attribute.Table_Name);
        I_M_Attribute result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Attribute)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Attribute_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Attribute.
		@param M_Attribute_ID 
		Product Attribute
	  */
	public void setM_Attribute_ID (int M_Attribute_ID)
	{
		if (M_Attribute_ID < 1)
			 throw new IllegalArgumentException ("M_Attribute_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Attribute_ID, Integer.valueOf(M_Attribute_ID));
	}

	/** Get Attribute.
		@return Product Attribute
	  */
	public int getM_Attribute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Attribute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			Name = Name.substring(0, 60);
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
			Value = Value.substring(0, 40);
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
}