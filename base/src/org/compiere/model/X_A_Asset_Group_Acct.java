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
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for A_Asset_Group_Acct
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_A_Asset_Group_Acct extends PO implements I_A_Asset_Group_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_A_Asset_Group_Acct (Properties ctx, int A_Asset_Group_Acct_ID, String trxName)
    {
      super (ctx, A_Asset_Group_Acct_ID, trxName);
      /** if (A_Asset_Group_Acct_ID == 0)
        {
			setA_Accumdepreciation_Acct (0);
			setA_Asset_Acct (0);
			setA_Asset_Group_Acct_ID (0);
			setA_Asset_Group_ID (0);
			setA_Depreciation_Acct (0);
			setA_Depreciation_F_ID (0);
			setA_Depreciation_ID (0);
			setA_Disposal_Loss_Acct (0);
			setA_Disposal_Revenue_Acct (0);
			setA_Split_Percent (Env.ZERO);
// 1
			setC_AcctSchema_ID (0);
			setPostingType (null);
// 'A'
			setUseLifeMonths_F (0);
// 0
			setUseLifeYears_F (0);
// 0
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Group_Acct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_A_Asset_Group_Acct[")
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
		set_Value (COLUMNNAME_A_Accumdepreciation_Acct, Integer.valueOf(A_Accumdepreciation_Acct));
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

	public I_C_ValidCombination getA_Asset_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Asset_Acct(), get_TrxName());	}

	/** Set Asset Acct.
		@param A_Asset_Acct Asset Acct	  */
	public void setA_Asset_Acct (int A_Asset_Acct)
	{
		set_Value (COLUMNNAME_A_Asset_Acct, Integer.valueOf(A_Asset_Acct));
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

	/** Set Asset Group Accounting.
		@param A_Asset_Group_Acct_ID Asset Group Accounting	  */
	public void setA_Asset_Group_Acct_ID (int A_Asset_Group_Acct_ID)
	{
		if (A_Asset_Group_Acct_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Group_Acct_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Group_Acct_ID, Integer.valueOf(A_Asset_Group_Acct_ID));
	}

	/** Get Asset Group Accounting.
		@return Asset Group Accounting	  */
	public int getA_Asset_Group_Acct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Group_Acct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Asset_Group_Acct_ID()));
    }

	public org.compiere.model.I_A_Asset_Group getA_Asset_Group() throws RuntimeException
    {
		return (org.compiere.model.I_A_Asset_Group)MTable.get(getCtx(), org.compiere.model.I_A_Asset_Group.Table_Name)
			.getPO(getA_Asset_Group_ID(), get_TrxName());	}

	/** Set Asset Group.
		@param A_Asset_Group_ID 
		Group of Assets
	  */
	public void setA_Asset_Group_ID (int A_Asset_Group_ID)
	{
		if (A_Asset_Group_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Group_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Group_ID, Integer.valueOf(A_Asset_Group_ID));
	}

	/** Get Asset Group.
		@return Group of Assets
	  */
	public int getA_Asset_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_A_Asset_Spread getA_Asset_Spread_T() throws RuntimeException
    {
		return (org.compiere.model.I_A_Asset_Spread)MTable.get(getCtx(), org.compiere.model.I_A_Asset_Spread.Table_Name)
			.getPO(getA_Asset_Spread_Type(), get_TrxName());	}

	/** Set Spread Type.
		@param A_Asset_Spread_Type Spread Type	  */
	public void setA_Asset_Spread_Type (int A_Asset_Spread_Type)
	{
		set_Value (COLUMNNAME_A_Asset_Spread_Type, Integer.valueOf(A_Asset_Spread_Type));
	}

	/** Get Spread Type.
		@return Spread Type	  */
	public int getA_Asset_Spread_Type () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Spread_Type);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Depreciation_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Depreciation_Acct(), get_TrxName());	}

	/** Set Depreciation Account.
		@param A_Depreciation_Acct Depreciation Account	  */
	public void setA_Depreciation_Acct (int A_Depreciation_Acct)
	{
		set_Value (COLUMNNAME_A_Depreciation_Acct, Integer.valueOf(A_Depreciation_Acct));
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

	public org.compiere.model.I_A_Depreciation_Method getA_Depreciation_Calc_T() throws RuntimeException
    {
		return (org.compiere.model.I_A_Depreciation_Method)MTable.get(getCtx(), org.compiere.model.I_A_Depreciation_Method.Table_Name)
			.getPO(getA_Depreciation_Calc_Type(), get_TrxName());	}

	/** Set Calculation Type.
		@param A_Depreciation_Calc_Type Calculation Type	  */
	public void setA_Depreciation_Calc_Type (int A_Depreciation_Calc_Type)
	{
		set_ValueNoCheck (COLUMNNAME_A_Depreciation_Calc_Type, Integer.valueOf(A_Depreciation_Calc_Type));
	}

	/** Get Calculation Type.
		@return Calculation Type	  */
	public int getA_Depreciation_Calc_Type () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Calc_Type);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_A_Depreciation_Convention getA_Depreciation_Conv_F() throws RuntimeException
    {
		return (org.compiere.model.I_A_Depreciation_Convention)MTable.get(getCtx(), org.compiere.model.I_A_Depreciation_Convention.Table_Name)
			.getPO(getA_Depreciation_Conv_F_ID(), get_TrxName());	}

	/** Set Depreciation Convention (fiscal).
		@param A_Depreciation_Conv_F_ID Depreciation Convention (fiscal)	  */
	public void setA_Depreciation_Conv_F_ID (int A_Depreciation_Conv_F_ID)
	{
		if (A_Depreciation_Conv_F_ID < 1) 
			set_Value (COLUMNNAME_A_Depreciation_Conv_F_ID, null);
		else 
			set_Value (COLUMNNAME_A_Depreciation_Conv_F_ID, Integer.valueOf(A_Depreciation_Conv_F_ID));
	}

	/** Get Depreciation Convention (fiscal).
		@return Depreciation Convention (fiscal)	  */
	public int getA_Depreciation_Conv_F_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Conv_F_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_A_Depreciation_Convention getA_Depreciation_Conv() throws RuntimeException
    {
		return (org.compiere.model.I_A_Depreciation_Convention)MTable.get(getCtx(), org.compiere.model.I_A_Depreciation_Convention.Table_Name)
			.getPO(getA_Depreciation_Conv_ID(), get_TrxName());	}

	/** Set Convention Type.
		@param A_Depreciation_Conv_ID Convention Type	  */
	public void setA_Depreciation_Conv_ID (int A_Depreciation_Conv_ID)
	{
		if (A_Depreciation_Conv_ID < 1) 
			set_Value (COLUMNNAME_A_Depreciation_Conv_ID, null);
		else 
			set_Value (COLUMNNAME_A_Depreciation_Conv_ID, Integer.valueOf(A_Depreciation_Conv_ID));
	}

	/** Get Convention Type.
		@return Convention Type	  */
	public int getA_Depreciation_Conv_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Conv_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_A_Depreciation getA_Depreciation_F() throws RuntimeException
    {
		return (org.compiere.model.I_A_Depreciation)MTable.get(getCtx(), org.compiere.model.I_A_Depreciation.Table_Name)
			.getPO(getA_Depreciation_F_ID(), get_TrxName());	}

	/** Set Depreciation (fiscal).
		@param A_Depreciation_F_ID Depreciation (fiscal)	  */
	public void setA_Depreciation_F_ID (int A_Depreciation_F_ID)
	{
		if (A_Depreciation_F_ID < 1) 
			set_Value (COLUMNNAME_A_Depreciation_F_ID, null);
		else 
			set_Value (COLUMNNAME_A_Depreciation_F_ID, Integer.valueOf(A_Depreciation_F_ID));
	}

	/** Get Depreciation (fiscal).
		@return Depreciation (fiscal)	  */
	public int getA_Depreciation_F_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_F_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_A_Depreciation getA_Depreciation() throws RuntimeException
    {
		return (org.compiere.model.I_A_Depreciation)MTable.get(getCtx(), org.compiere.model.I_A_Depreciation.Table_Name)
			.getPO(getA_Depreciation_ID(), get_TrxName());	}

	/** Set Depreciation.
		@param A_Depreciation_ID Depreciation	  */
	public void setA_Depreciation_ID (int A_Depreciation_ID)
	{
		if (A_Depreciation_ID < 1) 
			set_Value (COLUMNNAME_A_Depreciation_ID, null);
		else 
			set_Value (COLUMNNAME_A_Depreciation_ID, Integer.valueOf(A_Depreciation_ID));
	}

	/** Get Depreciation.
		@return Depreciation	  */
	public int getA_Depreciation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Depreciation_Manual_Amount.
		@param A_Depreciation_Manual_Amount A_Depreciation_Manual_Amount	  */
	public void setA_Depreciation_Manual_Amount (BigDecimal A_Depreciation_Manual_Amount)
	{
		set_Value (COLUMNNAME_A_Depreciation_Manual_Amount, A_Depreciation_Manual_Amount);
	}

	/** Get A_Depreciation_Manual_Amount.
		@return A_Depreciation_Manual_Amount	  */
	public BigDecimal getA_Depreciation_Manual_Amount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Depreciation_Manual_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** A_Depreciation_Manual_Period AD_Reference_ID=53256 */
	public static final int A_DEPRECIATION_MANUAL_PERIOD_AD_Reference_ID=53256;
	/** Period = PR */
	public static final String A_DEPRECIATION_MANUAL_PERIOD_Period = "PR";
	/** Yearly = YR */
	public static final String A_DEPRECIATION_MANUAL_PERIOD_Yearly = "YR";
	/** Set A_Depreciation_Manual_Period.
		@param A_Depreciation_Manual_Period A_Depreciation_Manual_Period	  */
	public void setA_Depreciation_Manual_Period (String A_Depreciation_Manual_Period)
	{

		set_Value (COLUMNNAME_A_Depreciation_Manual_Period, A_Depreciation_Manual_Period);
	}

	/** Get A_Depreciation_Manual_Period.
		@return A_Depreciation_Manual_Period	  */
	public String getA_Depreciation_Manual_Period () 
	{
		return (String)get_Value(COLUMNNAME_A_Depreciation_Manual_Period);
	}

	public org.compiere.model.I_A_Depreciation_Method getA_Depreciation_Method_F() throws RuntimeException
    {
		return (org.compiere.model.I_A_Depreciation_Method)MTable.get(getCtx(), org.compiere.model.I_A_Depreciation_Method.Table_Name)
			.getPO(getA_Depreciation_Method_F_ID(), get_TrxName());	}

	/** Set Depreciation Method (fiscal).
		@param A_Depreciation_Method_F_ID Depreciation Method (fiscal)	  */
	public void setA_Depreciation_Method_F_ID (int A_Depreciation_Method_F_ID)
	{
		if (A_Depreciation_Method_F_ID < 1) 
			set_Value (COLUMNNAME_A_Depreciation_Method_F_ID, null);
		else 
			set_Value (COLUMNNAME_A_Depreciation_Method_F_ID, Integer.valueOf(A_Depreciation_Method_F_ID));
	}

	/** Get Depreciation Method (fiscal).
		@return Depreciation Method (fiscal)	  */
	public int getA_Depreciation_Method_F_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Method_F_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_A_Depreciation_Method getA_Depreciation_Method() throws RuntimeException
    {
		return (org.compiere.model.I_A_Depreciation_Method)MTable.get(getCtx(), org.compiere.model.I_A_Depreciation_Method.Table_Name)
			.getPO(getA_Depreciation_Method_ID(), get_TrxName());	}

	/** Set Depreciation Method.
		@param A_Depreciation_Method_ID Depreciation Method	  */
	public void setA_Depreciation_Method_ID (int A_Depreciation_Method_ID)
	{
		if (A_Depreciation_Method_ID < 1) 
			set_Value (COLUMNNAME_A_Depreciation_Method_ID, null);
		else 
			set_Value (COLUMNNAME_A_Depreciation_Method_ID, Integer.valueOf(A_Depreciation_Method_ID));
	}

	/** Get Depreciation Method.
		@return Depreciation Method	  */
	public int getA_Depreciation_Method_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Method_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_A_Depreciation_Table_Header getA_Depreciation_Table_Header() throws RuntimeException
    {
		return (org.compiere.model.I_A_Depreciation_Table_Header)MTable.get(getCtx(), org.compiere.model.I_A_Depreciation_Table_Header.Table_Name)
			.getPO(getA_Depreciation_Table_Header_ID(), get_TrxName());	}

	/** Set A_Depreciation_Table_Header_ID.
		@param A_Depreciation_Table_Header_ID A_Depreciation_Table_Header_ID	  */
	public void setA_Depreciation_Table_Header_ID (int A_Depreciation_Table_Header_ID)
	{
		if (A_Depreciation_Table_Header_ID < 1) 
			set_Value (COLUMNNAME_A_Depreciation_Table_Header_ID, null);
		else 
			set_Value (COLUMNNAME_A_Depreciation_Table_Header_ID, Integer.valueOf(A_Depreciation_Table_Header_ID));
	}

	/** Get A_Depreciation_Table_Header_ID.
		@return A_Depreciation_Table_Header_ID	  */
	public int getA_Depreciation_Table_Header_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Table_Header_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Variable Percent.
		@param A_Depreciation_Variable_Perc Variable Percent	  */
	public void setA_Depreciation_Variable_Perc (BigDecimal A_Depreciation_Variable_Perc)
	{
		set_Value (COLUMNNAME_A_Depreciation_Variable_Perc, A_Depreciation_Variable_Perc);
	}

	/** Get Variable Percent.
		@return Variable Percent	  */
	public BigDecimal getA_Depreciation_Variable_Perc () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Depreciation_Variable_Perc);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Variable Percent (fiscal).
		@param A_Depreciation_Variable_Perc_F Variable Percent (fiscal)	  */
	public void setA_Depreciation_Variable_Perc_F (BigDecimal A_Depreciation_Variable_Perc_F)
	{
		set_Value (COLUMNNAME_A_Depreciation_Variable_Perc_F, A_Depreciation_Variable_Perc_F);
	}

	/** Get Variable Percent (fiscal).
		@return Variable Percent (fiscal)	  */
	public BigDecimal getA_Depreciation_Variable_Perc_F () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Depreciation_Variable_Perc_F);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_C_ValidCombination getA_Disposal_Loss_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Disposal_Loss_Acct(), get_TrxName());	}

	/** Set Disposal Loss Acct.
		@param A_Disposal_Loss_Acct Disposal Loss Acct	  */
	public void setA_Disposal_Loss_Acct (int A_Disposal_Loss_Acct)
	{
		set_Value (COLUMNNAME_A_Disposal_Loss_Acct, Integer.valueOf(A_Disposal_Loss_Acct));
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

	public I_C_ValidCombination getA_Disposal_Revenue_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Disposal_Revenue_Acct(), get_TrxName());	}

	/** Set Disposal Revenue Acct.
		@param A_Disposal_Revenue_Acct Disposal Revenue Acct	  */
	public void setA_Disposal_Revenue_Acct (int A_Disposal_Revenue_Acct)
	{
		set_Value (COLUMNNAME_A_Disposal_Revenue_Acct, Integer.valueOf(A_Disposal_Revenue_Acct));
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

	public I_C_ValidCombination getA_Reval_Accumdep_Offset_() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Reval_Accumdep_Offset_Cur(), get_TrxName());	}

	/** Set Revaluation Accumulated Depreciation Offset for Current Year.
		@param A_Reval_Accumdep_Offset_Cur Revaluation Accumulated Depreciation Offset for Current Year	  */
	public void setA_Reval_Accumdep_Offset_Cur (int A_Reval_Accumdep_Offset_Cur)
	{
		set_Value (COLUMNNAME_A_Reval_Accumdep_Offset_Cur, Integer.valueOf(A_Reval_Accumdep_Offset_Cur));
	}

	/** Get Revaluation Accumulated Depreciation Offset for Current Year.
		@return Revaluation Accumulated Depreciation Offset for Current Year	  */
	public int getA_Reval_Accumdep_Offset_Cur () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Accumdep_Offset_Cur);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Reval_Accumdep_Offset_Pr() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Reval_Accumdep_Offset_Prior(), get_TrxName());	}

	/** Set Revaluation Accumulated Depreciation Offset for Prior Year.
		@param A_Reval_Accumdep_Offset_Prior Revaluation Accumulated Depreciation Offset for Prior Year	  */
	public void setA_Reval_Accumdep_Offset_Prior (int A_Reval_Accumdep_Offset_Prior)
	{
		set_Value (COLUMNNAME_A_Reval_Accumdep_Offset_Prior, Integer.valueOf(A_Reval_Accumdep_Offset_Prior));
	}

	/** Get Revaluation Accumulated Depreciation Offset for Prior Year.
		@return Revaluation Accumulated Depreciation Offset for Prior Year	  */
	public int getA_Reval_Accumdep_Offset_Prior () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Accumdep_Offset_Prior);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Reval_Adep_Offset_Cur_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Reval_Adep_Offset_Cur_Acct(), get_TrxName());	}

	/** Set A_Reval_Accumdep_Offset_Cur.
		@param A_Reval_Adep_Offset_Cur_Acct A_Reval_Accumdep_Offset_Cur	  */
	public void setA_Reval_Adep_Offset_Cur_Acct (int A_Reval_Adep_Offset_Cur_Acct)
	{
		set_Value (COLUMNNAME_A_Reval_Adep_Offset_Cur_Acct, Integer.valueOf(A_Reval_Adep_Offset_Cur_Acct));
	}

	/** Get A_Reval_Accumdep_Offset_Cur.
		@return A_Reval_Accumdep_Offset_Cur	  */
	public int getA_Reval_Adep_Offset_Cur_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Adep_Offset_Cur_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Reval_Adep_Offset_Prior_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Reval_Adep_Offset_Prior_Acct(), get_TrxName());	}

	/** Set A_Reval_Accumdep_Offset_Prior.
		@param A_Reval_Adep_Offset_Prior_Acct A_Reval_Accumdep_Offset_Prior	  */
	public void setA_Reval_Adep_Offset_Prior_Acct (int A_Reval_Adep_Offset_Prior_Acct)
	{
		set_Value (COLUMNNAME_A_Reval_Adep_Offset_Prior_Acct, Integer.valueOf(A_Reval_Adep_Offset_Prior_Acct));
	}

	/** Get A_Reval_Accumdep_Offset_Prior.
		@return A_Reval_Accumdep_Offset_Prior	  */
	public int getA_Reval_Adep_Offset_Prior_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Adep_Offset_Prior_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** A_Reval_Cal_Method AD_Reference_ID=53259 */
	public static final int A_REVAL_CAL_METHOD_AD_Reference_ID=53259;
	/** Default = DFT */
	public static final String A_REVAL_CAL_METHOD_Default = "DFT";
	/** Inception to date = IDF */
	public static final String A_REVAL_CAL_METHOD_InceptionToDate = "IDF";
	/** Year Balances = YBF */
	public static final String A_REVAL_CAL_METHOD_YearBalances = "YBF";
	/** Set A_Reval_Cal_Method.
		@param A_Reval_Cal_Method A_Reval_Cal_Method	  */
	public void setA_Reval_Cal_Method (String A_Reval_Cal_Method)
	{

		set_Value (COLUMNNAME_A_Reval_Cal_Method, A_Reval_Cal_Method);
	}

	/** Get A_Reval_Cal_Method.
		@return A_Reval_Cal_Method	  */
	public String getA_Reval_Cal_Method () 
	{
		return (String)get_Value(COLUMNNAME_A_Reval_Cal_Method);
	}

	public I_C_ValidCombination getA_Reval_Cost_Off() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Reval_Cost_Offset(), get_TrxName());	}

	/** Set Revaluation Cost Offset for Current Year.
		@param A_Reval_Cost_Offset Revaluation Cost Offset for Current Year	  */
	public void setA_Reval_Cost_Offset (int A_Reval_Cost_Offset)
	{
		set_Value (COLUMNNAME_A_Reval_Cost_Offset, Integer.valueOf(A_Reval_Cost_Offset));
	}

	/** Get Revaluation Cost Offset for Current Year.
		@return Revaluation Cost Offset for Current Year	  */
	public int getA_Reval_Cost_Offset () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Cost_Offset);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Reval_Cost_Offset_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Reval_Cost_Offset_Acct(), get_TrxName());	}

	/** Set Reval Cost Offset Acct.
		@param A_Reval_Cost_Offset_Acct Reval Cost Offset Acct	  */
	public void setA_Reval_Cost_Offset_Acct (int A_Reval_Cost_Offset_Acct)
	{
		set_Value (COLUMNNAME_A_Reval_Cost_Offset_Acct, Integer.valueOf(A_Reval_Cost_Offset_Acct));
	}

	/** Get Reval Cost Offset Acct.
		@return Reval Cost Offset Acct	  */
	public int getA_Reval_Cost_Offset_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Cost_Offset_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Reval_Cost_Offset_Pr() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Reval_Cost_Offset_Prior(), get_TrxName());	}

	/** Set Revaluation Cost Offset for Prior Year.
		@param A_Reval_Cost_Offset_Prior Revaluation Cost Offset for Prior Year	  */
	public void setA_Reval_Cost_Offset_Prior (int A_Reval_Cost_Offset_Prior)
	{
		set_Value (COLUMNNAME_A_Reval_Cost_Offset_Prior, Integer.valueOf(A_Reval_Cost_Offset_Prior));
	}

	/** Get Revaluation Cost Offset for Prior Year.
		@return Revaluation Cost Offset for Prior Year	  */
	public int getA_Reval_Cost_Offset_Prior () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Cost_Offset_Prior);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Reval_Cost_Offset_Prior_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Reval_Cost_Offset_Prior_Acct(), get_TrxName());	}

	/** Set Reval Cost Offset Prior Acct.
		@param A_Reval_Cost_Offset_Prior_Acct Reval Cost Offset Prior Acct	  */
	public void setA_Reval_Cost_Offset_Prior_Acct (int A_Reval_Cost_Offset_Prior_Acct)
	{
		set_Value (COLUMNNAME_A_Reval_Cost_Offset_Prior_Acct, Integer.valueOf(A_Reval_Cost_Offset_Prior_Acct));
	}

	/** Get Reval Cost Offset Prior Acct.
		@return Reval Cost Offset Prior Acct	  */
	public int getA_Reval_Cost_Offset_Prior_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Cost_Offset_Prior_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Reval_Depexp_Off() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Reval_Depexp_Offset(), get_TrxName());	}

	/** Set Revaluation Expense Offs.
		@param A_Reval_Depexp_Offset Revaluation Expense Offs	  */
	public void setA_Reval_Depexp_Offset (int A_Reval_Depexp_Offset)
	{
		set_Value (COLUMNNAME_A_Reval_Depexp_Offset, Integer.valueOf(A_Reval_Depexp_Offset));
	}

	/** Get Revaluation Expense Offs.
		@return Revaluation Expense Offs	  */
	public int getA_Reval_Depexp_Offset () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Depexp_Offset);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getA_Reval_Depexp_Offset_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Reval_Depexp_Offset_Acct(), get_TrxName());	}

	/** Set Reval Depexp Offset Acct.
		@param A_Reval_Depexp_Offset_Acct Reval Depexp Offset Acct	  */
	public void setA_Reval_Depexp_Offset_Acct (int A_Reval_Depexp_Offset_Acct)
	{
		set_Value (COLUMNNAME_A_Reval_Depexp_Offset_Acct, Integer.valueOf(A_Reval_Depexp_Offset_Acct));
	}

	/** Get Reval Depexp Offset Acct.
		@return Reval Depexp Offset Acct	  */
	public int getA_Reval_Depexp_Offset_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Depexp_Offset_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Split Percent.
		@param A_Split_Percent Split Percent	  */
	public void setA_Split_Percent (BigDecimal A_Split_Percent)
	{
		set_Value (COLUMNNAME_A_Split_Percent, A_Split_Percent);
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
			set_Value (COLUMNNAME_C_AcctSchema_ID, null);
		else 
			set_Value (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
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

	public org.compiere.model.I_A_Depreciation_Convention getConventionT() throws RuntimeException
    {
		return (org.compiere.model.I_A_Depreciation_Convention)MTable.get(getCtx(), org.compiere.model.I_A_Depreciation_Convention.Table_Name)
			.getPO(getConventionType(), get_TrxName());	}

	/** Set ConventionType.
		@param ConventionType ConventionType	  */
	public void setConventionType (int ConventionType)
	{
		set_ValueNoCheck (COLUMNNAME_ConventionType, Integer.valueOf(ConventionType));
	}

	/** Get ConventionType.
		@return ConventionType	  */
	public int getConventionType () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ConventionType);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_A_Depreciation getDepreciationT() throws RuntimeException
    {
		return (org.compiere.model.I_A_Depreciation)MTable.get(getCtx(), org.compiere.model.I_A_Depreciation.Table_Name)
			.getPO(getDepreciationType(), get_TrxName());	}

	/** Set DepreciationType.
		@param DepreciationType DepreciationType	  */
	public void setDepreciationType (int DepreciationType)
	{
		set_ValueNoCheck (COLUMNNAME_DepreciationType, Integer.valueOf(DepreciationType));
	}

	/** Get DepreciationType.
		@return DepreciationType	  */
	public int getDepreciationType () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DepreciationType);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Usable Life - Months.
		@param UseLifeMonths 
		Months of the usable life of the asset
	  */
	public void setUseLifeMonths (int UseLifeMonths)
	{
		set_Value (COLUMNNAME_UseLifeMonths, Integer.valueOf(UseLifeMonths));
	}

	/** Get Usable Life - Months.
		@return Months of the usable life of the asset
	  */
	public int getUseLifeMonths () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UseLifeMonths);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Use Life - Months (fiscal).
		@param UseLifeMonths_F Use Life - Months (fiscal)	  */
	public void setUseLifeMonths_F (int UseLifeMonths_F)
	{
		set_Value (COLUMNNAME_UseLifeMonths_F, Integer.valueOf(UseLifeMonths_F));
	}

	/** Get Use Life - Months (fiscal).
		@return Use Life - Months (fiscal)	  */
	public int getUseLifeMonths_F () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UseLifeMonths_F);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Usable Life - Years.
		@param UseLifeYears 
		Years of the usable life of the asset
	  */
	public void setUseLifeYears (int UseLifeYears)
	{
		set_Value (COLUMNNAME_UseLifeYears, Integer.valueOf(UseLifeYears));
	}

	/** Get Usable Life - Years.
		@return Years of the usable life of the asset
	  */
	public int getUseLifeYears () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UseLifeYears);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Use Life - Years (fiscal).
		@param UseLifeYears_F Use Life - Years (fiscal)	  */
	public void setUseLifeYears_F (int UseLifeYears_F)
	{
		set_Value (COLUMNNAME_UseLifeYears_F, Integer.valueOf(UseLifeYears_F));
	}

	/** Get Use Life - Years (fiscal).
		@return Use Life - Years (fiscal)	  */
	public int getUseLifeYears_F () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UseLifeYears_F);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}