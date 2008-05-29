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
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_TaxBase
 *  @author Adempiere (generated) 
 *  @version Release 3.5.1a - $Id$ */
public class X_C_TaxBase extends PO implements I_C_TaxBase, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_TaxBase (Properties ctx, int C_TaxBase_ID, String trxName)
    {
      super (ctx, C_TaxBase_ID, trxName);
      /** if (C_TaxBase_ID == 0)
        {
			setC_TaxBase_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_C_TaxBase (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_C_TaxBase[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Base AD_Reference_ID=53240 */
	public static final int BASE_AD_Reference_ID=53240;
	/** Cost = C */
	public static final String BASE_Cost = "C";
	/** Price = P */
	public static final String BASE_Price = "P";
	/** Quantity = Q */
	public static final String BASE_Quantity = "Q";
	/** Set Base.
		@param Base 
		Calculation Base
	  */
	public void setBase (String Base)
	{

		if (Base == null || Base.equals("C") || Base.equals("P") || Base.equals("Q")); else throw new IllegalArgumentException ("Base Invalid value - " + Base + " - Reference_ID=53240 - C - P - Q");
		if (Base != null && Base.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			Base = Base.substring(0, 1);
		}
		set_Value (COLUMNNAME_Base, Base);
	}

	/** Get Base.
		@return Calculation Base
	  */
	public String getBase () 
	{
		return (String)get_Value(COLUMNNAME_Base);
	}

	/** Set C_TaxBase_ID.
		@param C_TaxBase_ID C_TaxBase_ID	  */
	public void setC_TaxBase_ID (int C_TaxBase_ID)
	{
		if (C_TaxBase_ID < 1)
			 throw new IllegalArgumentException ("C_TaxBase_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_TaxBase_ID, Integer.valueOf(C_TaxBase_ID));
	}

	/** Get C_TaxBase_ID.
		@return C_TaxBase_ID	  */
	public int getC_TaxBase_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_TaxBase_ID);
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

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{

		if (Help != null && Help.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			Help = Help.substring(0, 2000);
		}
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
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

	/** Set Percentage.
		@param Percentage 
		Percent of the entire amount
	  */
	public void setPercentage (int Percentage)
	{
		set_Value (COLUMNNAME_Percentage, Integer.valueOf(Percentage));
	}

	/** Get Percentage.
		@return Percent of the entire amount
	  */
	public int getPercentage () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Percentage);
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