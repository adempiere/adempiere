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

/** Generated Model for C_Element
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1t - $Id$ */
public class X_C_Element extends PO implements I_C_Element, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_Element (Properties ctx, int C_Element_ID, String trxName)
    {
      super (ctx, C_Element_ID, trxName);
      /** if (C_Element_ID == 0)
        {
			setAD_Tree_ID (0);
			setC_Element_ID (0);
			setElementType (null);
// A
			setIsBalancing (false);
			setIsNaturalAccount (false);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_C_Element (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_C_Element[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Tree getAD_Tree() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_Tree.Table_Name);
        I_AD_Tree result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Tree)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Tree_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Tree.
		@param AD_Tree_ID 
		Identifies a Tree
	  */
	public void setAD_Tree_ID (int AD_Tree_ID)
	{
		if (AD_Tree_ID < 1)
			 throw new IllegalArgumentException ("AD_Tree_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_Tree_ID, Integer.valueOf(AD_Tree_ID));
	}

	/** Get Tree.
		@return Identifies a Tree
	  */
	public int getAD_Tree_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tree_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Element.
		@param C_Element_ID 
		Accounting Element
	  */
	public void setC_Element_ID (int C_Element_ID)
	{
		if (C_Element_ID < 1)
			 throw new IllegalArgumentException ("C_Element_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Element_ID, Integer.valueOf(C_Element_ID));
	}

	/** Get Element.
		@return Accounting Element
	  */
	public int getC_Element_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Element_ID);
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

	/** ElementType AD_Reference_ID=116 */
	public static final int ELEMENTTYPE_AD_Reference_ID=116;
	/** Account = A */
	public static final String ELEMENTTYPE_Account = "A";
	/** User defined = U */
	public static final String ELEMENTTYPE_UserDefined = "U";
	/** Set Type.
		@param ElementType 
		Element Type (account or user defined)
	  */
	public void setElementType (String ElementType)
	{
		if (ElementType == null) throw new IllegalArgumentException ("ElementType is mandatory");
		if (ElementType.equals("A") || ElementType.equals("U")); else throw new IllegalArgumentException ("ElementType Invalid value - " + ElementType + " - Reference_ID=116 - A - U");
		if (ElementType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			ElementType = ElementType.substring(0, 1);
		}
		set_ValueNoCheck (COLUMNNAME_ElementType, ElementType);
	}

	/** Get Type.
		@return Element Type (account or user defined)
	  */
	public String getElementType () 
	{
		return (String)get_Value(COLUMNNAME_ElementType);
	}

	/** Set Balancing.
		@param IsBalancing 
		All transactions within an element value must balance (e.g. cost centers)
	  */
	public void setIsBalancing (boolean IsBalancing)
	{
		set_Value (COLUMNNAME_IsBalancing, Boolean.valueOf(IsBalancing));
	}

	/** Get Balancing.
		@return All transactions within an element value must balance (e.g. cost centers)
	  */
	public boolean isBalancing () 
	{
		Object oo = get_Value(COLUMNNAME_IsBalancing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Natural Account.
		@param IsNaturalAccount 
		The primary natural account
	  */
	public void setIsNaturalAccount (boolean IsNaturalAccount)
	{
		set_Value (COLUMNNAME_IsNaturalAccount, Boolean.valueOf(IsNaturalAccount));
	}

	/** Get Natural Account.
		@return The primary natural account
	  */
	public boolean isNaturalAccount () 
	{
		Object oo = get_Value(COLUMNNAME_IsNaturalAccount);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Value Format.
		@param VFormat 
		Format of the value; Can contain fixed format elements, Variables: "_lLoOaAcCa09"
	  */
	public void setVFormat (String VFormat)
	{

		if (VFormat != null && VFormat.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			VFormat = VFormat.substring(0, 40);
		}
		set_Value (COLUMNNAME_VFormat, VFormat);
	}

	/** Get Value Format.
		@return Format of the value; Can contain fixed format elements, Variables: "_lLoOaAcCa09"
	  */
	public String getVFormat () 
	{
		return (String)get_Value(COLUMNNAME_VFormat);
	}
}