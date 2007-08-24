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

/** Generated Model for M_Attribute
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_M_Attribute extends PO implements I_M_Attribute, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_Attribute (Properties ctx, int M_Attribute_ID, String trxName)
    {
      super (ctx, M_Attribute_ID, trxName);
      /** if (M_Attribute_ID == 0)        {			setAttributeValueType (null);
// S
			setIsInstanceAttribute (false);
			setIsMandatory (false);
			setM_Attribute_ID (0);
			setName (null);
} */
    }

    /** Load Constructor */
    public X_M_Attribute (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_Attribute[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

/** AttributeValueType AD_Reference_ID=326 */
public static final int ATTRIBUTEVALUETYPE_AD_Reference_ID=326;/** List = L */
public static final String ATTRIBUTEVALUETYPE_List = "L";/** Number = N */
public static final String ATTRIBUTEVALUETYPE_Number = "N";/** String (max 40) = S */
public static final String ATTRIBUTEVALUETYPE_StringMax40 = "S";
	/** Set Attribute Value Type.
		@param AttributeValueType 
		Type of Attribute Value
	  */
	public void setAttributeValueType (String AttributeValueType)
	{
if (AttributeValueType == null) throw new IllegalArgumentException ("AttributeValueType is mandatory");if (AttributeValueType.equals("L") || AttributeValueType.equals("N") || AttributeValueType.equals("S")); else throw new IllegalArgumentException ("AttributeValueType Invalid value - " + AttributeValueType + " - Reference_ID=326 - L - N - S");		if (AttributeValueType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			AttributeValueType = AttributeValueType.substring(0, 0);
		}
		set_Value (COLUMNNAME_AttributeValueType, AttributeValueType);
	}

	/** Get Attribute Value Type.
		@return Type of Attribute Value
	  */
	public String getAttributeValueType () 
	{
		return (String)get_Value(COLUMNNAME_AttributeValueType);
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

	/** Set Instance Attribute.
		@param IsInstanceAttribute 
		The product attribute is specific to the instance (like Serial No, Lot or Guarantee Date)
	  */
	public void setIsInstanceAttribute (boolean IsInstanceAttribute)
	{
		set_Value (COLUMNNAME_IsInstanceAttribute, Boolean.valueOf(IsInstanceAttribute));
	}

	/** Get Instance Attribute.
		@return The product attribute is specific to the instance (like Serial No, Lot or Guarantee Date)
	  */
	public boolean isInstanceAttribute () 
	{
		Object oo = get_Value(COLUMNNAME_IsInstanceAttribute);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Mandatory.
		@param IsMandatory 
		Data entry is required in this column
	  */
	public void setIsMandatory (boolean IsMandatory)
	{
		set_Value (COLUMNNAME_IsMandatory, Boolean.valueOf(IsMandatory));
	}

	/** Get Mandatory.
		@return Data entry is required in this column
	  */
	public boolean isMandatory () 
	{
		Object oo = get_Value(COLUMNNAME_IsMandatory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_AttributeSearch getI_M_AttributeSearch() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_AttributeSearch.Table_Name);
        I_M_AttributeSearch result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_AttributeSearch)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_AttributeSearch_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Attribute Search.
		@param M_AttributeSearch_ID 
		Common Search Attribute 
	  */
	public void setM_AttributeSearch_ID (int M_AttributeSearch_ID)
	{
		if (M_AttributeSearch_ID <= 0) 		set_Value (COLUMNNAME_M_AttributeSearch_ID, null);
 else 
		set_Value (COLUMNNAME_M_AttributeSearch_ID, Integer.valueOf(M_AttributeSearch_ID));
	}

	/** Get Attribute Search.
		@return Common Search Attribute 
	  */
	public int getM_AttributeSearch_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSearch_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}