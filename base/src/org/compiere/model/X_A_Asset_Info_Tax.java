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

/** Generated Model for A_Asset_Info_Tax
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_A_Asset_Info_Tax extends PO implements I_A_Asset_Info_Tax, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_A_Asset_Info_Tax (Properties ctx, int A_Asset_Info_Tax_ID, String trxName)
    {
      super (ctx, A_Asset_Info_Tax_ID, trxName);
      /** if (A_Asset_Info_Tax_ID == 0)
        {
			setA_Asset_ID (0);
			setA_Asset_Info_Tax_ID (0);
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Info_Tax (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_A_Asset_Info_Tax[")
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

	/** Set Asset Info Tax.
		@param A_Asset_Info_Tax_ID Asset Info Tax	  */
	public void setA_Asset_Info_Tax_ID (int A_Asset_Info_Tax_ID)
	{
		if (A_Asset_Info_Tax_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Info_Tax_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Info_Tax_ID, Integer.valueOf(A_Asset_Info_Tax_ID));
	}

	/** Get Asset Info Tax.
		@return Asset Info Tax	  */
	public int getA_Asset_Info_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Info_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Asset_Info_Tax_ID()));
    }

	/** A_Finance_Meth AD_Reference_ID=53271 */
	public static final int A_FINANCE_METH_AD_Reference_ID=53271;
	/** Capitalized Lease = CL */
	public static final String A_FINANCE_METH_CapitalizedLease = "CL";
	/** Non-Capitalized Lease = NL */
	public static final String A_FINANCE_METH_Non_CapitalizedLease = "NL";
	/** Owned = OW */
	public static final String A_FINANCE_METH_Owned = "OW";
	/** Rented = RE */
	public static final String A_FINANCE_METH_Rented = "RE";
	/** Set Asset Finance Method.
		@param A_Finance_Meth Asset Finance Method	  */
	public void setA_Finance_Meth (String A_Finance_Meth)
	{

		set_Value (COLUMNNAME_A_Finance_Meth, A_Finance_Meth);
	}

	/** Get Asset Finance Method.
		@return Asset Finance Method	  */
	public String getA_Finance_Meth () 
	{
		return (String)get_Value(COLUMNNAME_A_Finance_Meth);
	}

	/** Set Asset Investment CR.
		@param A_Investment_CR Asset Investment CR	  */
	public void setA_Investment_CR (int A_Investment_CR)
	{
		set_Value (COLUMNNAME_A_Investment_CR, Integer.valueOf(A_Investment_CR));
	}

	/** Get Asset Investment CR.
		@return Asset Investment CR	  */
	public int getA_Investment_CR () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Investment_CR);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Asset New Used.
		@param A_New_Used Asset New Used	  */
	public void setA_New_Used (boolean A_New_Used)
	{
		set_Value (COLUMNNAME_A_New_Used, Boolean.valueOf(A_New_Used));
	}

	/** Get Asset New Used.
		@return Asset New Used	  */
	public boolean isA_New_Used () 
	{
		Object oo = get_Value(COLUMNNAME_A_New_Used);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Account State.
		@param A_State 
		State of the Credit Card or Account holder
	  */
	public void setA_State (String A_State)
	{
		set_Value (COLUMNNAME_A_State, A_State);
	}

	/** Get Account State.
		@return State of the Credit Card or Account holder
	  */
	public String getA_State () 
	{
		return (String)get_Value(COLUMNNAME_A_State);
	}

	/** Set Asset Tax Entity.
		@param A_Tax_Entity Asset Tax Entity	  */
	public void setA_Tax_Entity (String A_Tax_Entity)
	{
		set_Value (COLUMNNAME_A_Tax_Entity, A_Tax_Entity);
	}

	/** Get Asset Tax Entity.
		@return Asset Tax Entity	  */
	public String getA_Tax_Entity () 
	{
		return (String)get_Value(COLUMNNAME_A_Tax_Entity);
	}

	/** Set Text Message.
		@param TextMsg 
		Text Message
	  */
	public void setTextMsg (String TextMsg)
	{
		set_Value (COLUMNNAME_TextMsg, TextMsg);
	}

	/** Get Text Message.
		@return Text Message
	  */
	public String getTextMsg () 
	{
		return (String)get_Value(COLUMNNAME_TextMsg);
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