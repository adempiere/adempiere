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

/** Generated Model for A_Asset_Reval_Index
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_A_Asset_Reval_Index extends PO implements I_A_Asset_Reval_Index, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_A_Asset_Reval_Index (Properties ctx, int A_Asset_Reval_Index_ID, String trxName)
    {
      super (ctx, A_Asset_Reval_Index_ID, trxName);
      /** if (A_Asset_Reval_Index_ID == 0)
        {
			setA_Asset_Reval_Index_ID (0);
			setA_Effective_Date (new Timestamp( System.currentTimeMillis() ));
			setA_Reval_Code (null);
			setA_Reval_Multiplier (null);
			setA_Reval_Rate (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Reval_Index (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_A_Asset_Reval_Index[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Asset Reval Index.
		@param A_Asset_Reval_Index_ID Asset Reval Index	  */
	public void setA_Asset_Reval_Index_ID (int A_Asset_Reval_Index_ID)
	{
		if (A_Asset_Reval_Index_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Reval_Index_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Reval_Index_ID, Integer.valueOf(A_Asset_Reval_Index_ID));
	}

	/** Get Asset Reval Index.
		@return Asset Reval Index	  */
	public int getA_Asset_Reval_Index_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Reval_Index_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Asset_Reval_Index_ID()));
    }

	/** Set Effective Date.
		@param A_Effective_Date Effective Date	  */
	public void setA_Effective_Date (Timestamp A_Effective_Date)
	{
		set_Value (COLUMNNAME_A_Effective_Date, A_Effective_Date);
	}

	/** Get Effective Date.
		@return Effective Date	  */
	public Timestamp getA_Effective_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_A_Effective_Date);
	}

	/** A_Reval_Code AD_Reference_ID=53262 */
	public static final int A_REVAL_CODE_AD_Reference_ID=53262;
	/** Revaluation Code #1 = R01 */
	public static final String A_REVAL_CODE_RevaluationCode1 = "R01";
	/** Revaluation Code #2 = R02 */
	public static final String A_REVAL_CODE_RevaluationCode2 = "R02";
	/** Revaluation Code #3 = R03 */
	public static final String A_REVAL_CODE_RevaluationCode3 = "R03";
	/** Set Reval. Code.
		@param A_Reval_Code Reval. Code	  */
	public void setA_Reval_Code (String A_Reval_Code)
	{

		set_Value (COLUMNNAME_A_Reval_Code, A_Reval_Code);
	}

	/** Get Reval. Code.
		@return Reval. Code	  */
	public String getA_Reval_Code () 
	{
		return (String)get_Value(COLUMNNAME_A_Reval_Code);
	}

	/** A_Reval_Multiplier AD_Reference_ID=53260 */
	public static final int A_REVAL_MULTIPLIER_AD_Reference_ID=53260;
	/** Factor = FAC */
	public static final String A_REVAL_MULTIPLIER_Factor = "FAC";
	/** Index = IND */
	public static final String A_REVAL_MULTIPLIER_Index = "IND";
	/** Set Reval. Multiplier.
		@param A_Reval_Multiplier Reval. Multiplier	  */
	public void setA_Reval_Multiplier (String A_Reval_Multiplier)
	{

		set_Value (COLUMNNAME_A_Reval_Multiplier, A_Reval_Multiplier);
	}

	/** Get Reval. Multiplier.
		@return Reval. Multiplier	  */
	public String getA_Reval_Multiplier () 
	{
		return (String)get_Value(COLUMNNAME_A_Reval_Multiplier);
	}

	/** Set Reval. Rate.
		@param A_Reval_Rate Reval. Rate	  */
	public void setA_Reval_Rate (BigDecimal A_Reval_Rate)
	{
		set_Value (COLUMNNAME_A_Reval_Rate, A_Reval_Rate);
	}

	/** Get Reval. Rate.
		@return Reval. Rate	  */
	public BigDecimal getA_Reval_Rate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Reval_Rate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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