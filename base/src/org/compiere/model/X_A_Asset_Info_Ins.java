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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for A_Asset_Info_Ins
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_A_Asset_Info_Ins extends PO implements I_A_Asset_Info_Ins, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_A_Asset_Info_Ins (Properties ctx, int A_Asset_Info_Ins_ID, String trxName)
    {
      super (ctx, A_Asset_Info_Ins_ID, trxName);
      /** if (A_Asset_Info_Ins_ID == 0)
        {
			setA_Asset_ID (0);
			setA_Asset_Info_Ins_ID (0);
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Info_Ins (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_A_Asset_Info_Ins[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Fixed Asset.
		@param A_Asset_ID 
		Fixed Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Asset_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
	}

	/** Get Fixed Asset.
		@return Fixed Asset used internally or by customers
	  */
	public int getA_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Asset_Info_Ins_ID.
		@param A_Asset_Info_Ins_ID A_Asset_Info_Ins_ID	  */
	public void setA_Asset_Info_Ins_ID (int A_Asset_Info_Ins_ID)
	{
		if (A_Asset_Info_Ins_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Info_Ins_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Info_Ins_ID, Integer.valueOf(A_Asset_Info_Ins_ID));
	}

	/** Get A_Asset_Info_Ins_ID.
		@return A_Asset_Info_Ins_ID	  */
	public int getA_Asset_Info_Ins_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Info_Ins_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Asset_Info_Ins_ID()));
    }

	/** Set Asset Insurance Premium.
		@param A_Ins_Premium Asset Insurance Premium	  */
	public void setA_Ins_Premium (BigDecimal A_Ins_Premium)
	{
		set_Value (COLUMNNAME_A_Ins_Premium, A_Ins_Premium);
	}

	/** Get Asset Insurance Premium.
		@return Asset Insurance Premium	  */
	public BigDecimal getA_Ins_Premium () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Ins_Premium);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Asset Insurance Value.
		@param A_Ins_Value Asset Insurance Value	  */
	public void setA_Ins_Value (BigDecimal A_Ins_Value)
	{
		set_Value (COLUMNNAME_A_Ins_Value, A_Ins_Value);
	}

	/** Get Asset Insurance Value.
		@return Asset Insurance Value	  */
	public BigDecimal getA_Ins_Value () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Ins_Value);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Insurance Company.
		@param A_Insurance_Co Insurance Company	  */
	public void setA_Insurance_Co (String A_Insurance_Co)
	{
		set_Value (COLUMNNAME_A_Insurance_Co, A_Insurance_Co);
	}

	/** Get Insurance Company.
		@return Insurance Company	  */
	public String getA_Insurance_Co () 
	{
		return (String)get_Value(COLUMNNAME_A_Insurance_Co);
	}

	/** Set Asset Policy No.
		@param A_Policy_No Asset Policy No	  */
	public void setA_Policy_No (String A_Policy_No)
	{
		set_Value (COLUMNNAME_A_Policy_No, A_Policy_No);
	}

	/** Get Asset Policy No.
		@return Asset Policy No	  */
	public String getA_Policy_No () 
	{
		return (String)get_Value(COLUMNNAME_A_Policy_No);
	}

	/** Set Asset Renewal Date.
		@param A_Renewal_Date Asset Renewal Date	  */
	public void setA_Renewal_Date (Timestamp A_Renewal_Date)
	{
		set_Value (COLUMNNAME_A_Renewal_Date, A_Renewal_Date);
	}

	/** Get Asset Renewal Date.
		@return Asset Renewal Date	  */
	public Timestamp getA_Renewal_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_A_Renewal_Date);
	}

	/** Set Asset Replace Cost.
		@param A_Replace_Cost Asset Replace Cost	  */
	public void setA_Replace_Cost (BigDecimal A_Replace_Cost)
	{
		set_Value (COLUMNNAME_A_Replace_Cost, A_Replace_Cost);
	}

	/** Get Asset Replace Cost.
		@return Asset Replace Cost	  */
	public BigDecimal getA_Replace_Cost () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Replace_Cost);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Description.
		@param Text Description	  */
	public void setText (String Text)
	{
		set_Value (COLUMNNAME_Text, Text);
	}

	/** Get Description.
		@return Description	  */
	public String getText () 
	{
		return (String)get_Value(COLUMNNAME_Text);
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
}