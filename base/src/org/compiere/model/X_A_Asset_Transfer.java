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

/** Generated Model for A_Asset_Transfer
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_A_Asset_Transfer extends PO implements I_A_Asset_Transfer, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_A_Asset_Transfer (Properties ctx, int A_Asset_Transfer_ID, String trxName)
    {
      super (ctx, A_Asset_Transfer_ID, trxName);
      /** if (A_Asset_Transfer_ID == 0)
        {
			setA_Accumdepreciation_New_Acct (0);
			setA_Asset_ID (0);
			setA_Asset_New_Acct (0);
			setA_Asset_Transfer_ID (0);
			setA_CapvsExp (null);
// 'Cap'
			setA_Depreciation_New_Acct (0);
			setA_Disposal_Loss_New_Acct (0);
			setA_Disposal_Revenue_New_Acct (0);
			setA_Period_End (0);
			setA_Period_Start (0);
			setA_Split_Percent (Env.ZERO);
// 1
			setA_Transfer_Balance (true);
// Y
			setA_Transfer_Balance_IS (false);
			setC_AcctSchema_ID (0);
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDocAction (null);
// CO
			setDocStatus (null);
// DR
			setPostingType (null);
// A
			setProcessed (false);
// N
			setProcessing (false);
// N
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Transfer (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_A_Asset_Transfer[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_ValidCombination getA_Accumdepreciation_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Accumdepreciation_Acct(), get_TrxName());	}

	/** Set Accumulated Depreciation Account.
		@param A_Accumdepreciation_Acct Accumulated Depreciation Account	  */
	public void setA_Accumdepreciation_Acct (int A_Accumdepreciation_Acct)
	{
		set_ValueNoCheck (COLUMNNAME_A_Accumdepreciation_Acct, Integer.valueOf(A_Accumdepreciation_Acct));
	}

	/** Get Accumulated Depreciation Account.
		@return Accumulated Depreciation Account	  */
	public int getA_Accumdepreciation_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Accumdepreciation_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Accumdepreciation_Acct_() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Accumdepreciation_Acct_New(), get_TrxName());	}

	/** Set New Accum Depreciation Acct.
		@param A_Accumdepreciation_Acct_New New Accum Depreciation Acct	  */
	public void setA_Accumdepreciation_Acct_New (int A_Accumdepreciation_Acct_New)
	{
		set_Value (COLUMNNAME_A_Accumdepreciation_Acct_New, Integer.valueOf(A_Accumdepreciation_Acct_New));
	}

	/** Get New Accum Depreciation Acct.
		@return New Accum Depreciation Acct	  */
	public int getA_Accumdepreciation_Acct_New () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Accumdepreciation_Acct_New);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Old Asset Cost Acct.
		@param A_Accumdepreciation_Acct_Str Old Asset Cost Acct	  */
	public void setA_Accumdepreciation_Acct_Str (String A_Accumdepreciation_Acct_Str)
	{
		set_ValueNoCheck (COLUMNNAME_A_Accumdepreciation_Acct_Str, A_Accumdepreciation_Acct_Str);
	}

	/** Get Old Asset Cost Acct.
		@return Old Asset Cost Acct	  */
	public String getA_Accumdepreciation_Acct_Str () 
	{
		return (String)get_Value(COLUMNNAME_A_Accumdepreciation_Acct_Str);
	}

	public I_C_ValidCombination getA_Accumdepreciation_New_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Accumdepreciation_New_Acct(), get_TrxName());	}

	/** Set Accumulated Depreciation Account (new).
		@param A_Accumdepreciation_New_Acct Accumulated Depreciation Account (new)	  */
	public void setA_Accumdepreciation_New_Acct (int A_Accumdepreciation_New_Acct)
	{
		set_Value (COLUMNNAME_A_Accumdepreciation_New_Acct, Integer.valueOf(A_Accumdepreciation_New_Acct));
	}

	/** Get Accumulated Depreciation Account (new).
		@return Accumulated Depreciation Account (new)	  */
	public int getA_Accumdepreciation_New_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Accumdepreciation_New_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Accumulated Depreciation (fiscal).
		@param A_Accumulated_Depr_F Accumulated Depreciation (fiscal)	  */
	public void setA_Accumulated_Depr_F (BigDecimal A_Accumulated_Depr_F)
	{
		set_Value (COLUMNNAME_A_Accumulated_Depr_F, A_Accumulated_Depr_F);
	}

	/** Get Accumulated Depreciation (fiscal).
		@return Accumulated Depreciation (fiscal)	  */
	public BigDecimal getA_Accumulated_Depr_F () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Accumulated_Depr_F);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_C_ValidCombination getA_Asset_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Asset_Acct(), get_TrxName());	}

	/** Set Asset Acct.
		@param A_Asset_Acct Asset Acct	  */
	public void setA_Asset_Acct (int A_Asset_Acct)
	{
		set_ValueNoCheck (COLUMNNAME_A_Asset_Acct, Integer.valueOf(A_Asset_Acct));
	}

	/** Get Asset Acct.
		@return Asset Acct	  */
	public int getA_Asset_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Asset_Acct_ID.
		@param A_Asset_Acct_ID A_Asset_Acct_ID	  */
	public void setA_Asset_Acct_ID (int A_Asset_Acct_ID)
	{
		if (A_Asset_Acct_ID < 1) 
			set_Value (COLUMNNAME_A_Asset_Acct_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_Acct_ID, Integer.valueOf(A_Asset_Acct_ID));
	}

	/** Get A_Asset_Acct_ID.
		@return A_Asset_Acct_ID	  */
	public int getA_Asset_Acct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Acct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Asset_Acct_() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Asset_Acct_New(), get_TrxName());	}

	/** Set New Asset Cost Acct.
		@param A_Asset_Acct_New New Asset Cost Acct	  */
	public void setA_Asset_Acct_New (int A_Asset_Acct_New)
	{
		set_Value (COLUMNNAME_A_Asset_Acct_New, Integer.valueOf(A_Asset_Acct_New));
	}

	/** Get New Asset Cost Acct.
		@return New Asset Cost Acct	  */
	public int getA_Asset_Acct_New () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Acct_New);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Asset_Acct_Str.
		@param A_Asset_Acct_Str A_Asset_Acct_Str	  */
	public void setA_Asset_Acct_Str (String A_Asset_Acct_Str)
	{
		set_ValueNoCheck (COLUMNNAME_A_Asset_Acct_Str, A_Asset_Acct_Str);
	}

	/** Get A_Asset_Acct_Str.
		@return A_Asset_Acct_Str	  */
	public String getA_Asset_Acct_Str () 
	{
		return (String)get_Value(COLUMNNAME_A_Asset_Acct_Str);
	}

	public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException
    {
		return (org.compiere.model.I_A_Asset)MTable.get(getCtx(), org.compiere.model.I_A_Asset.Table_Name)
			.getPO(getA_Asset_ID(), get_TrxName());	}

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

	public I_C_ValidCombination getA_Asset_New_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Asset_New_Acct(), get_TrxName());	}

	/** Set Asset Acct (new).
		@param A_Asset_New_Acct Asset Acct (new)	  */
	public void setA_Asset_New_Acct (int A_Asset_New_Acct)
	{
		set_Value (COLUMNNAME_A_Asset_New_Acct, Integer.valueOf(A_Asset_New_Acct));
	}

	/** Get Asset Acct (new).
		@return Asset Acct (new)	  */
	public int getA_Asset_New_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_New_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Asset_Transfer_ID.
		@param A_Asset_Transfer_ID A_Asset_Transfer_ID	  */
	public void setA_Asset_Transfer_ID (int A_Asset_Transfer_ID)
	{
		if (A_Asset_Transfer_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Transfer_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Transfer_ID, Integer.valueOf(A_Asset_Transfer_ID));
	}

	/** Get A_Asset_Transfer_ID.
		@return A_Asset_Transfer_ID	  */
	public int getA_Asset_Transfer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Transfer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Asset_Transfer_ID()));
    }

	/** A_CapvsExp AD_Reference_ID=53277 */
	public static final int A_CAPVSEXP_AD_Reference_ID=53277;
	/** Capital = Cap */
	public static final String A_CAPVSEXP_Capital = "Cap";
	/** Expense = Exp */
	public static final String A_CAPVSEXP_Expense = "Exp";
	/** Set Capital/Expense.
		@param A_CapvsExp Capital/Expense	  */
	public void setA_CapvsExp (String A_CapvsExp)
	{

		set_Value (COLUMNNAME_A_CapvsExp, A_CapvsExp);
	}

	/** Get Capital/Expense.
		@return Capital/Expense	  */
	public String getA_CapvsExp () 
	{
		return (String)get_Value(COLUMNNAME_A_CapvsExp);
	}

	public I_C_ValidCombination getA_Depreciation_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Depreciation_Acct(), get_TrxName());	}

	/** Set Depreciation Account.
		@param A_Depreciation_Acct Depreciation Account	  */
	public void setA_Depreciation_Acct (int A_Depreciation_Acct)
	{
		set_ValueNoCheck (COLUMNNAME_A_Depreciation_Acct, Integer.valueOf(A_Depreciation_Acct));
	}

	/** Get Depreciation Account.
		@return Depreciation Account	  */
	public int getA_Depreciation_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Depreciation_Acct_() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Depreciation_Acct_New(), get_TrxName());	}

	/** Set New Depreciation Exp Acct.
		@param A_Depreciation_Acct_New New Depreciation Exp Acct	  */
	public void setA_Depreciation_Acct_New (int A_Depreciation_Acct_New)
	{
		set_Value (COLUMNNAME_A_Depreciation_Acct_New, Integer.valueOf(A_Depreciation_Acct_New));
	}

	/** Get New Depreciation Exp Acct.
		@return New Depreciation Exp Acct	  */
	public int getA_Depreciation_Acct_New () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Acct_New);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Depreciation_Acct_Str.
		@param A_Depreciation_Acct_Str A_Depreciation_Acct_Str	  */
	public void setA_Depreciation_Acct_Str (String A_Depreciation_Acct_Str)
	{
		set_ValueNoCheck (COLUMNNAME_A_Depreciation_Acct_Str, A_Depreciation_Acct_Str);
	}

	/** Get A_Depreciation_Acct_Str.
		@return A_Depreciation_Acct_Str	  */
	public String getA_Depreciation_Acct_Str () 
	{
		return (String)get_Value(COLUMNNAME_A_Depreciation_Acct_Str);
	}

	public I_C_ValidCombination getA_Depreciation_New_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Depreciation_New_Acct(), get_TrxName());	}

	/** Set Depreciation Acct (new).
		@param A_Depreciation_New_Acct Depreciation Acct (new)	  */
	public void setA_Depreciation_New_Acct (int A_Depreciation_New_Acct)
	{
		set_Value (COLUMNNAME_A_Depreciation_New_Acct, Integer.valueOf(A_Depreciation_New_Acct));
	}

	/** Get Depreciation Acct (new).
		@return Depreciation Acct (new)	  */
	public int getA_Depreciation_New_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_New_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Loss on Disposal.
		@param A_Disposal_Loss Loss on Disposal	  */
	public void setA_Disposal_Loss (int A_Disposal_Loss)
	{
		set_Value (COLUMNNAME_A_Disposal_Loss, Integer.valueOf(A_Disposal_Loss));
	}

	/** Get Loss on Disposal.
		@return Loss on Disposal	  */
	public int getA_Disposal_Loss () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Disposal_Loss);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Disposal_Loss_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Disposal_Loss_Acct(), get_TrxName());	}

	/** Set Disposal Loss Acct.
		@param A_Disposal_Loss_Acct Disposal Loss Acct	  */
	public void setA_Disposal_Loss_Acct (int A_Disposal_Loss_Acct)
	{
		set_ValueNoCheck (COLUMNNAME_A_Disposal_Loss_Acct, Integer.valueOf(A_Disposal_Loss_Acct));
	}

	/** Get Disposal Loss Acct.
		@return Disposal Loss Acct	  */
	public int getA_Disposal_Loss_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Disposal_Loss_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Disposal_Loss_() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Disposal_Loss_New(), get_TrxName());	}

	/** Set New Disposal Loss.
		@param A_Disposal_Loss_New New Disposal Loss	  */
	public void setA_Disposal_Loss_New (int A_Disposal_Loss_New)
	{
		set_Value (COLUMNNAME_A_Disposal_Loss_New, Integer.valueOf(A_Disposal_Loss_New));
	}

	/** Get New Disposal Loss.
		@return New Disposal Loss	  */
	public int getA_Disposal_Loss_New () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Disposal_Loss_New);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Disposal_Loss_New_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Disposal_Loss_New_Acct(), get_TrxName());	}

	/** Set Disposal Loss Acct (new).
		@param A_Disposal_Loss_New_Acct Disposal Loss Acct (new)	  */
	public void setA_Disposal_Loss_New_Acct (int A_Disposal_Loss_New_Acct)
	{
		set_Value (COLUMNNAME_A_Disposal_Loss_New_Acct, Integer.valueOf(A_Disposal_Loss_New_Acct));
	}

	/** Get Disposal Loss Acct (new).
		@return Disposal Loss Acct (new)	  */
	public int getA_Disposal_Loss_New_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Disposal_Loss_New_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Disposal Loss Str.
		@param A_Disposal_Loss_Str Disposal Loss Str	  */
	public void setA_Disposal_Loss_Str (String A_Disposal_Loss_Str)
	{
		set_ValueNoCheck (COLUMNNAME_A_Disposal_Loss_Str, A_Disposal_Loss_Str);
	}

	/** Get Disposal Loss Str.
		@return Disposal Loss Str	  */
	public String getA_Disposal_Loss_Str () 
	{
		return (String)get_Value(COLUMNNAME_A_Disposal_Loss_Str);
	}

	/** Set Disposal Revenue.
		@param A_Disposal_Revenue Disposal Revenue	  */
	public void setA_Disposal_Revenue (int A_Disposal_Revenue)
	{
		set_Value (COLUMNNAME_A_Disposal_Revenue, Integer.valueOf(A_Disposal_Revenue));
	}

	/** Get Disposal Revenue.
		@return Disposal Revenue	  */
	public int getA_Disposal_Revenue () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Disposal_Revenue);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Disposal_Revenue_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Disposal_Revenue_Acct(), get_TrxName());	}

	/** Set Disposal Revenue Acct.
		@param A_Disposal_Revenue_Acct Disposal Revenue Acct	  */
	public void setA_Disposal_Revenue_Acct (int A_Disposal_Revenue_Acct)
	{
		set_ValueNoCheck (COLUMNNAME_A_Disposal_Revenue_Acct, Integer.valueOf(A_Disposal_Revenue_Acct));
	}

	/** Get Disposal Revenue Acct.
		@return Disposal Revenue Acct	  */
	public int getA_Disposal_Revenue_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Disposal_Revenue_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Disposal_Revenue_() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Disposal_Revenue_New(), get_TrxName());	}

	/** Set New Disposal Revenue.
		@param A_Disposal_Revenue_New New Disposal Revenue	  */
	public void setA_Disposal_Revenue_New (int A_Disposal_Revenue_New)
	{
		set_Value (COLUMNNAME_A_Disposal_Revenue_New, Integer.valueOf(A_Disposal_Revenue_New));
	}

	/** Get New Disposal Revenue.
		@return New Disposal Revenue	  */
	public int getA_Disposal_Revenue_New () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Disposal_Revenue_New);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Disposal_Revenue_New_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Disposal_Revenue_New_Acct(), get_TrxName());	}

	/** Set Disposal Revenue Acct (new).
		@param A_Disposal_Revenue_New_Acct Disposal Revenue Acct (new)	  */
	public void setA_Disposal_Revenue_New_Acct (int A_Disposal_Revenue_New_Acct)
	{
		set_Value (COLUMNNAME_A_Disposal_Revenue_New_Acct, Integer.valueOf(A_Disposal_Revenue_New_Acct));
	}

	/** Get Disposal Revenue Acct (new).
		@return Disposal Revenue Acct (new)	  */
	public int getA_Disposal_Revenue_New_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Disposal_Revenue_New_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Disposal Revenue Str.
		@param A_Disposal_Revenue_Str Disposal Revenue Str	  */
	public void setA_Disposal_Revenue_Str (String A_Disposal_Revenue_Str)
	{
		set_ValueNoCheck (COLUMNNAME_A_Disposal_Revenue_Str, A_Disposal_Revenue_Str);
	}

	/** Get Disposal Revenue Str.
		@return Disposal Revenue Str	  */
	public String getA_Disposal_Revenue_Str () 
	{
		return (String)get_Value(COLUMNNAME_A_Disposal_Revenue_Str);
	}

	/** Set A_Period_End.
		@param A_Period_End A_Period_End	  */
	public void setA_Period_End (int A_Period_End)
	{
		set_ValueNoCheck (COLUMNNAME_A_Period_End, Integer.valueOf(A_Period_End));
	}

	/** Get A_Period_End.
		@return A_Period_End	  */
	public int getA_Period_End () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Period_End);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Period_Start.
		@param A_Period_Start A_Period_Start	  */
	public void setA_Period_Start (int A_Period_Start)
	{
		set_ValueNoCheck (COLUMNNAME_A_Period_Start, Integer.valueOf(A_Period_Start));
	}

	/** Get A_Period_Start.
		@return A_Period_Start	  */
	public int getA_Period_Start () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Period_Start);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Split Percent.
		@param A_Split_Percent Split Percent	  */
	public void setA_Split_Percent (BigDecimal A_Split_Percent)
	{
		set_ValueNoCheck (COLUMNNAME_A_Split_Percent, A_Split_Percent);
	}

	/** Get Split Percent.
		@return Split Percent	  */
	public BigDecimal getA_Split_Percent () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Split_Percent);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set A_Transfer_Balance.
		@param A_Transfer_Balance A_Transfer_Balance	  */
	public void setA_Transfer_Balance (boolean A_Transfer_Balance)
	{
		set_Value (COLUMNNAME_A_Transfer_Balance, Boolean.valueOf(A_Transfer_Balance));
	}

	/** Get A_Transfer_Balance.
		@return A_Transfer_Balance	  */
	public boolean isA_Transfer_Balance () 
	{
		Object oo = get_Value(COLUMNNAME_A_Transfer_Balance);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Transferred Balance.
		@param A_Transfer_Balance_IS 
		Indicates whether the balance for a Fixed Asset is transferred to the new Fixed Asset or not
	  */
	public void setA_Transfer_Balance_IS (boolean A_Transfer_Balance_IS)
	{
		set_Value (COLUMNNAME_A_Transfer_Balance_IS, Boolean.valueOf(A_Transfer_Balance_IS));
	}

	/** Get Is Transferred Balance.
		@return Indicates whether the balance for a Fixed Asset is transferred to the new Fixed Asset or not
	  */
	public boolean isA_Transfer_Balance_IS () 
	{
		Object oo = get_Value(COLUMNNAME_A_Transfer_Balance_IS);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_C_AcctSchema getC_AcctSchema() throws RuntimeException
    {
		return (org.compiere.model.I_C_AcctSchema)MTable.get(getCtx(), org.compiere.model.I_C_AcctSchema.Table_Name)
			.getPO(getC_AcctSchema_ID(), get_TrxName());	}

	/** Set Accounting Schema.
		@param C_AcctSchema_ID 
		Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_AcctSchema_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
	}

	/** Get Accounting Schema.
		@return Rules for accounting
	  */
	public int getC_AcctSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_AcctSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Period getC_Period() throws RuntimeException
    {
		return (org.compiere.model.I_C_Period)MTable.get(getCtx(), org.compiere.model.I_C_Period.Table_Name)
			.getPO(getC_Period_ID(), get_TrxName());	}

	/** Set Period.
		@param C_Period_ID 
		Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID)
	{
		if (C_Period_ID < 1) 
			set_Value (COLUMNNAME_C_Period_ID, null);
		else 
			set_Value (COLUMNNAME_C_Period_ID, Integer.valueOf(C_Period_ID));
	}

	/** Get Period.
		@return Period of the Calendar
	  */
	public int getC_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Account Date.
		@param DateAcct 
		Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct)
	{
		set_Value (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
	}

	/** Set Document Date.
		@param DateDoc 
		Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc)
	{
		set_Value (COLUMNNAME_DateDoc, DateDoc);
	}

	/** Get Document Date.
		@return Date of the Document
	  */
	public Timestamp getDateDoc () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDoc);
	}

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** <None> = -- */
	public static final String DOCACTION_None = "--";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{

		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Posted.
		@param Posted 
		Posting status
	  */
	public void setPosted (boolean Posted)
	{
		set_Value (COLUMNNAME_Posted, Boolean.valueOf(Posted));
	}

	/** Get Posted.
		@return Posting status
	  */
	public boolean isPosted () 
	{
		Object oo = get_Value(COLUMNNAME_Posted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** PostingType AD_Reference_ID=125 */
	public static final int POSTINGTYPE_AD_Reference_ID=125;
	/** Actual = A */
	public static final String POSTINGTYPE_Actual = "A";
	/** Budget = B */
	public static final String POSTINGTYPE_Budget = "B";
	/** Commitment = E */
	public static final String POSTINGTYPE_Commitment = "E";
	/** Statistical = S */
	public static final String POSTINGTYPE_Statistical = "S";
	/** Reservation = R */
	public static final String POSTINGTYPE_Reservation = "R";
	/** Set Posting Type.
		@param PostingType 
		The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType)
	{

		set_Value (COLUMNNAME_PostingType, PostingType);
	}

	/** Get Posting Type.
		@return The type of posted amount for the transaction
	  */
	public String getPostingType () 
	{
		return (String)get_Value(COLUMNNAME_PostingType);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Processed On.
		@param ProcessedOn 
		The date+time (expressed in decimal format) when the document has been processed
	  */
	public void setProcessedOn (BigDecimal ProcessedOn)
	{
		set_Value (COLUMNNAME_ProcessedOn, ProcessedOn);
	}

	/** Get Processed On.
		@return The date+time (expressed in decimal format) when the document has been processed
	  */
	public BigDecimal getProcessedOn () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ProcessedOn);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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