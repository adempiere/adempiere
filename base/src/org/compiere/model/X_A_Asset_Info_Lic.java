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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for A_Asset_Info_Lic
 *  @author Adempiere (generated) 
 *  @version Release 3.5.1a - $Id$ */
public class X_A_Asset_Info_Lic extends PO implements I_A_Asset_Info_Lic, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_A_Asset_Info_Lic (Properties ctx, int A_Asset_Info_Lic_ID, String trxName)
    {
      super (ctx, A_Asset_Info_Lic_ID, trxName);
      /** if (A_Asset_Info_Lic_ID == 0)
        {
			setA_Asset_ID (0);
			setA_Asset_Info_Lic_ID (0);
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Info_Lic (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_A_Asset_Info_Lic[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Asset.
		@param A_Asset_ID 
		Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1)
			 throw new IllegalArgumentException ("A_Asset_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
	}

	/** Get Asset.
		@return Asset used internally or by customers
	  */
	public int getA_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Asset_Info_Lic_ID.
		@param A_Asset_Info_Lic_ID A_Asset_Info_Lic_ID	  */
	public void setA_Asset_Info_Lic_ID (int A_Asset_Info_Lic_ID)
	{
		if (A_Asset_Info_Lic_ID < 1)
			 throw new IllegalArgumentException ("A_Asset_Info_Lic_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_A_Asset_Info_Lic_ID, Integer.valueOf(A_Asset_Info_Lic_ID));
	}

	/** Get A_Asset_Info_Lic_ID.
		@return A_Asset_Info_Lic_ID	  */
	public int getA_Asset_Info_Lic_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Info_Lic_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Asset_Info_Lic_ID()));
    }

	/** Set A_Issuing_Agency.
		@param A_Issuing_Agency A_Issuing_Agency	  */
	public void setA_Issuing_Agency (String A_Issuing_Agency)
	{

		if (A_Issuing_Agency != null && A_Issuing_Agency.length() > 22)
		{
			log.warning("Length > 22 - truncated");
			A_Issuing_Agency = A_Issuing_Agency.substring(0, 22);
		}
		set_Value (COLUMNNAME_A_Issuing_Agency, A_Issuing_Agency);
	}

	/** Get A_Issuing_Agency.
		@return A_Issuing_Agency	  */
	public String getA_Issuing_Agency () 
	{
		return (String)get_Value(COLUMNNAME_A_Issuing_Agency);
	}

	/** Set A_License_Fee.
		@param A_License_Fee A_License_Fee	  */
	public void setA_License_Fee (BigDecimal A_License_Fee)
	{
		set_Value (COLUMNNAME_A_License_Fee, A_License_Fee);
	}

	/** Get A_License_Fee.
		@return A_License_Fee	  */
	public BigDecimal getA_License_Fee () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_License_Fee);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set A_License_No.
		@param A_License_No A_License_No	  */
	public void setA_License_No (String A_License_No)
	{

		if (A_License_No != null && A_License_No.length() > 120)
		{
			log.warning("Length > 120 - truncated");
			A_License_No = A_License_No.substring(0, 120);
		}
		set_Value (COLUMNNAME_A_License_No, A_License_No);
	}

	/** Get A_License_No.
		@return A_License_No	  */
	public String getA_License_No () 
	{
		return (String)get_Value(COLUMNNAME_A_License_No);
	}

	/** Set A_Renewal_Date.
		@param A_Renewal_Date A_Renewal_Date	  */
	public void setA_Renewal_Date (Timestamp A_Renewal_Date)
	{
		set_Value (COLUMNNAME_A_Renewal_Date, A_Renewal_Date);
	}

	/** Get A_Renewal_Date.
		@return A_Renewal_Date	  */
	public Timestamp getA_Renewal_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_A_Renewal_Date);
	}

	/** Set Account State.
		@param A_State 
		State of the Credit Card or Account holder
	  */
	public void setA_State (String A_State)
	{

		if (A_State != null && A_State.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			A_State = A_State.substring(0, 60);
		}
		set_Value (COLUMNNAME_A_State, A_State);
	}

	/** Get Account State.
		@return State of the Credit Card or Account holder
	  */
	public String getA_State () 
	{
		return (String)get_Value(COLUMNNAME_A_State);
	}

	/** Set Text.
		@param Text Text	  */
	public void setText (String Text)
	{

		if (Text != null && Text.length() > 510)
		{
			log.warning("Length > 510 - truncated");
			Text = Text.substring(0, 510);
		}
		set_Value (COLUMNNAME_Text, Text);
	}

	/** Get Text.
		@return Text	  */
	public String getText () 
	{
		return (String)get_Value(COLUMNNAME_Text);
	}
}