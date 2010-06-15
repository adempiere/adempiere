/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for A_Asset_Spread
 *  @author Adempiere (generated) 
 *  @version Release 3.6.0LTS - $Id$ */
public class X_A_Asset_Spread extends PO implements I_A_Asset_Spread, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20100614L;

    /** Standard Constructor */
    public X_A_Asset_Spread (Properties ctx, int A_Asset_Spread_ID, String trxName)
    {
      super (ctx, A_Asset_Spread_ID, trxName);
      /** if (A_Asset_Spread_ID == 0)
        {
			setA_Asset_Spread_ID (0);
			setA_Period_1 (Env.ZERO);
			setA_Period_10 (Env.ZERO);
			setA_Period_11 (Env.ZERO);
			setA_Period_12 (Env.ZERO);
			setA_Period_13 (Env.ZERO);
			setA_Period_14 (Env.ZERO);
			setA_Period_2 (Env.ZERO);
			setA_Period_3 (Env.ZERO);
			setA_Period_4 (Env.ZERO);
			setA_Period_5 (Env.ZERO);
			setA_Period_6 (Env.ZERO);
			setA_Period_7 (Env.ZERO);
			setA_Period_8 (Env.ZERO);
			setA_Period_9 (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Spread (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_A_Asset_Spread[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Asset Spread.
		@param A_Asset_Spread_ID Asset Spread	  */
	public void setA_Asset_Spread_ID (int A_Asset_Spread_ID)
	{
		if (A_Asset_Spread_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Spread_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Spread_ID, Integer.valueOf(A_Asset_Spread_ID));
	}

	/** Get Asset Spread.
		@return Asset Spread	  */
	public int getA_Asset_Spread_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Spread_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Asset_Spread_ID()));
    }

	/** Set Asset Spread Type.
		@param A_Asset_Spread_Type Asset Spread Type	  */
	public void setA_Asset_Spread_Type (String A_Asset_Spread_Type)
	{
		set_Value (COLUMNNAME_A_Asset_Spread_Type, A_Asset_Spread_Type);
	}

	/** Get Asset Spread Type.
		@return Asset Spread Type	  */
	public String getA_Asset_Spread_Type () 
	{
		return (String)get_Value(COLUMNNAME_A_Asset_Spread_Type);
	}

	/** Set Period 1.
		@param A_Period_1 Period 1	  */
	public void setA_Period_1 (BigDecimal A_Period_1)
	{
		set_Value (COLUMNNAME_A_Period_1, A_Period_1);
	}

	/** Get Period 1.
		@return Period 1	  */
	public BigDecimal getA_Period_1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Period 10.
		@param A_Period_10 Period 10	  */
	public void setA_Period_10 (BigDecimal A_Period_10)
	{
		set_Value (COLUMNNAME_A_Period_10, A_Period_10);
	}

	/** Get Period 10.
		@return Period 10	  */
	public BigDecimal getA_Period_10 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_10);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Period 11.
		@param A_Period_11 Period 11	  */
	public void setA_Period_11 (BigDecimal A_Period_11)
	{
		set_Value (COLUMNNAME_A_Period_11, A_Period_11);
	}

	/** Get Period 11.
		@return Period 11	  */
	public BigDecimal getA_Period_11 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_11);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Period 12.
		@param A_Period_12 Period 12	  */
	public void setA_Period_12 (BigDecimal A_Period_12)
	{
		set_Value (COLUMNNAME_A_Period_12, A_Period_12);
	}

	/** Get Period 12.
		@return Period 12	  */
	public BigDecimal getA_Period_12 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_12);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Period 13.
		@param A_Period_13 Period 13	  */
	public void setA_Period_13 (BigDecimal A_Period_13)
	{
		set_Value (COLUMNNAME_A_Period_13, A_Period_13);
	}

	/** Get Period 13.
		@return Period 13	  */
	public BigDecimal getA_Period_13 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_13);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Period 14.
		@param A_Period_14 Period 14	  */
	public void setA_Period_14 (BigDecimal A_Period_14)
	{
		set_Value (COLUMNNAME_A_Period_14, A_Period_14);
	}

	/** Get Period 14.
		@return Period 14	  */
	public BigDecimal getA_Period_14 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_14);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Period 2.
		@param A_Period_2 Period 2	  */
	public void setA_Period_2 (BigDecimal A_Period_2)
	{
		set_Value (COLUMNNAME_A_Period_2, A_Period_2);
	}

	/** Get Period 2.
		@return Period 2	  */
	public BigDecimal getA_Period_2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Period 3.
		@param A_Period_3 Period 3	  */
	public void setA_Period_3 (BigDecimal A_Period_3)
	{
		set_Value (COLUMNNAME_A_Period_3, A_Period_3);
	}

	/** Get Period 3.
		@return Period 3	  */
	public BigDecimal getA_Period_3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Period 4.
		@param A_Period_4 Period 4	  */
	public void setA_Period_4 (BigDecimal A_Period_4)
	{
		set_Value (COLUMNNAME_A_Period_4, A_Period_4);
	}

	/** Get Period 4.
		@return Period 4	  */
	public BigDecimal getA_Period_4 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_4);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Period 5.
		@param A_Period_5 Period 5	  */
	public void setA_Period_5 (BigDecimal A_Period_5)
	{
		set_Value (COLUMNNAME_A_Period_5, A_Period_5);
	}

	/** Get Period 5.
		@return Period 5	  */
	public BigDecimal getA_Period_5 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_5);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Period 6.
		@param A_Period_6 Period 6	  */
	public void setA_Period_6 (BigDecimal A_Period_6)
	{
		set_Value (COLUMNNAME_A_Period_6, A_Period_6);
	}

	/** Get Period 6.
		@return Period 6	  */
	public BigDecimal getA_Period_6 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_6);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Period 7.
		@param A_Period_7 Period 7	  */
	public void setA_Period_7 (BigDecimal A_Period_7)
	{
		set_Value (COLUMNNAME_A_Period_7, A_Period_7);
	}

	/** Get Period 7.
		@return Period 7	  */
	public BigDecimal getA_Period_7 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_7);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Period 8.
		@param A_Period_8 Period 8	  */
	public void setA_Period_8 (BigDecimal A_Period_8)
	{
		set_Value (COLUMNNAME_A_Period_8, A_Period_8);
	}

	/** Get Period 8.
		@return Period 8	  */
	public BigDecimal getA_Period_8 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_8);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Period 9.
		@param A_Period_9 Period 9	  */
	public void setA_Period_9 (BigDecimal A_Period_9)
	{
		set_Value (COLUMNNAME_A_Period_9, A_Period_9);
	}

	/** Get Period 9.
		@return Period 9	  */
	public BigDecimal getA_Period_9 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_9);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}
}