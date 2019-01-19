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

/** Generated Model for A_Depreciation_Exp
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_A_Depreciation_Exp extends PO implements I_A_Depreciation_Exp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_A_Depreciation_Exp (Properties ctx, int A_Depreciation_Exp_ID, String trxName)
    {
      super (ctx, A_Depreciation_Exp_ID, trxName);
      /** if (A_Depreciation_Exp_ID == 0)
        {
			setA_Accumulated_Depr_Delta (Env.ZERO);
// 0
			setA_Accumulated_Depr_F_Delta (Env.ZERO);
// 0
			setA_Asset_Cost_Delta (Env.ZERO);
// 0
			setA_Asset_ID (0);
			setA_Depreciation_Exp_ID (0);
			setA_Entry_Type (null);
			setA_Period (0);
			setDescription (null);
			setExpense (Env.ZERO);
// 0
			setExpense_F (Env.ZERO);
// 0
			setIsDepreciated (false);
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_A_Depreciation_Exp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_A_Depreciation_Exp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_ValidCombination getA_Account_Number_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getA_Account_Number_Acct(), get_TrxName());	}

	/** Set A_Account_Number_Acct.
		@param A_Account_Number_Acct A_Account_Number_Acct	  */
	public void setA_Account_Number_Acct (int A_Account_Number_Acct)
	{
		set_Value (COLUMNNAME_A_Account_Number_Acct, Integer.valueOf(A_Account_Number_Acct));
	}

	/** Get A_Account_Number_Acct.
		@return A_Account_Number_Acct	  */
	public int getA_Account_Number_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Account_Number_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Accumulated Depreciation.
		@param A_Accumulated_Depr Accumulated Depreciation	  */
	public void setA_Accumulated_Depr (BigDecimal A_Accumulated_Depr)
	{
		set_Value (COLUMNNAME_A_Accumulated_Depr, A_Accumulated_Depr);
	}

	/** Get Accumulated Depreciation.
		@return Accumulated Depreciation	  */
	public BigDecimal getA_Accumulated_Depr () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Accumulated_Depr);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Accumulated Depreciation (delta).
		@param A_Accumulated_Depr_Delta Accumulated Depreciation (delta)	  */
	public void setA_Accumulated_Depr_Delta (BigDecimal A_Accumulated_Depr_Delta)
	{
		set_Value (COLUMNNAME_A_Accumulated_Depr_Delta, A_Accumulated_Depr_Delta);
	}

	/** Get Accumulated Depreciation (delta).
		@return Accumulated Depreciation (delta)	  */
	public BigDecimal getA_Accumulated_Depr_Delta () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Accumulated_Depr_Delta);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Accumulated Depreciation - fiscal (delta).
		@param A_Accumulated_Depr_F_Delta Accumulated Depreciation - fiscal (delta)	  */
	public void setA_Accumulated_Depr_F_Delta (BigDecimal A_Accumulated_Depr_F_Delta)
	{
		set_Value (COLUMNNAME_A_Accumulated_Depr_F_Delta, A_Accumulated_Depr_F_Delta);
	}

	/** Get Accumulated Depreciation - fiscal (delta).
		@return Accumulated Depreciation - fiscal (delta)	  */
	public BigDecimal getA_Accumulated_Depr_F_Delta () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Accumulated_Depr_F_Delta);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_A_Asset_Addition getA_Asset_Addition() throws RuntimeException
    {
		return (org.compiere.model.I_A_Asset_Addition)MTable.get(getCtx(), org.compiere.model.I_A_Asset_Addition.Table_Name)
			.getPO(getA_Asset_Addition_ID(), get_TrxName());	}

	/** Set Asset Addition.
		@param A_Asset_Addition_ID Asset Addition	  */
	public void setA_Asset_Addition_ID (int A_Asset_Addition_ID)
	{
		if (A_Asset_Addition_ID < 1) 
			set_Value (COLUMNNAME_A_Asset_Addition_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_Addition_ID, Integer.valueOf(A_Asset_Addition_ID));
	}

	/** Get Asset Addition.
		@return Asset Addition	  */
	public int getA_Asset_Addition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Addition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fixed Asset Cost.
		@param A_Asset_Cost 
		Cost of acquisition of the Fixed Asset
	  */
	public void setA_Asset_Cost (BigDecimal A_Asset_Cost)
	{
		set_Value (COLUMNNAME_A_Asset_Cost, A_Asset_Cost);
	}

	/** Get Fixed Asset Cost.
		@return Cost of acquisition of the Fixed Asset
	  */
	public BigDecimal getA_Asset_Cost () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Asset_Cost);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Delta Asset Cost.
		@param A_Asset_Cost_Delta Delta Asset Cost	  */
	public void setA_Asset_Cost_Delta (BigDecimal A_Asset_Cost_Delta)
	{
		set_Value (COLUMNNAME_A_Asset_Cost_Delta, A_Asset_Cost_Delta);
	}

	/** Get Delta Asset Cost.
		@return Delta Asset Cost	  */
	public BigDecimal getA_Asset_Cost_Delta () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Asset_Cost_Delta);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_A_Asset_Disposed getA_Asset_Disposed() throws RuntimeException
    {
		return (org.compiere.model.I_A_Asset_Disposed)MTable.get(getCtx(), org.compiere.model.I_A_Asset_Disposed.Table_Name)
			.getPO(getA_Asset_Disposed_ID(), get_TrxName());	}

	/** Set Asset Disposed.
		@param A_Asset_Disposed_ID Asset Disposed	  */
	public void setA_Asset_Disposed_ID (int A_Asset_Disposed_ID)
	{
		if (A_Asset_Disposed_ID < 1) 
			set_Value (COLUMNNAME_A_Asset_Disposed_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_Disposed_ID, Integer.valueOf(A_Asset_Disposed_ID));
	}

	/** Get Asset Disposed.
		@return Asset Disposed	  */
	public int getA_Asset_Disposed_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Disposed_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_Value (COLUMNNAME_A_Asset_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
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

	/** Set Remaining Amt.
		@param A_Asset_Remaining Remaining Amt	  */
	public void setA_Asset_Remaining (BigDecimal A_Asset_Remaining)
	{
		set_Value (COLUMNNAME_A_Asset_Remaining, A_Asset_Remaining);
	}

	/** Get Remaining Amt.
		@return Remaining Amt	  */
	public BigDecimal getA_Asset_Remaining () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Asset_Remaining);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Remaining Amt (fiscal).
		@param A_Asset_Remaining_F Remaining Amt (fiscal)	  */
	public void setA_Asset_Remaining_F (BigDecimal A_Asset_Remaining_F)
	{
		set_Value (COLUMNNAME_A_Asset_Remaining_F, A_Asset_Remaining_F);
	}

	/** Get Remaining Amt (fiscal).
		@return Remaining Amt (fiscal)	  */
	public BigDecimal getA_Asset_Remaining_F () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Asset_Remaining_F);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_A_Depreciation_Entry getA_Depreciation_Entry() throws RuntimeException
    {
		return (org.compiere.model.I_A_Depreciation_Entry)MTable.get(getCtx(), org.compiere.model.I_A_Depreciation_Entry.Table_Name)
			.getPO(getA_Depreciation_Entry_ID(), get_TrxName());	}

	/** Set Depreciation Entry.
		@param A_Depreciation_Entry_ID Depreciation Entry	  */
	public void setA_Depreciation_Entry_ID (int A_Depreciation_Entry_ID)
	{
		if (A_Depreciation_Entry_ID < 1) 
			set_Value (COLUMNNAME_A_Depreciation_Entry_ID, null);
		else 
			set_Value (COLUMNNAME_A_Depreciation_Entry_ID, Integer.valueOf(A_Depreciation_Entry_ID));
	}

	/** Get Depreciation Entry.
		@return Depreciation Entry	  */
	public int getA_Depreciation_Entry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Entry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Depreciation_Exp_ID.
		@param A_Depreciation_Exp_ID A_Depreciation_Exp_ID	  */
	public void setA_Depreciation_Exp_ID (int A_Depreciation_Exp_ID)
	{
		if (A_Depreciation_Exp_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Depreciation_Exp_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Depreciation_Exp_ID, Integer.valueOf(A_Depreciation_Exp_ID));
	}

	/** Get A_Depreciation_Exp_ID.
		@return A_Depreciation_Exp_ID	  */
	public int getA_Depreciation_Exp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Exp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Depreciation_Exp_ID()));
    }

	/** A_Entry_Type AD_Reference_ID=53257 */
	public static final int A_ENTRY_TYPE_AD_Reference_ID=53257;
	/** Depreciation = DEP */
	public static final String A_ENTRY_TYPE_Depreciation = "DEP";
	/** Disposals = DIS */
	public static final String A_ENTRY_TYPE_Disposals = "DIS";
	/** Forecasts = FOR */
	public static final String A_ENTRY_TYPE_Forecasts = "FOR";
	/** New = NEW */
	public static final String A_ENTRY_TYPE_New = "NEW";
	/** Splits = SPL */
	public static final String A_ENTRY_TYPE_Splits = "SPL";
	/** Transfers = TRN */
	public static final String A_ENTRY_TYPE_Transfers = "TRN";
	/** Set Entry Type.
		@param A_Entry_Type Entry Type	  */
	public void setA_Entry_Type (String A_Entry_Type)
	{

		set_Value (COLUMNNAME_A_Entry_Type, A_Entry_Type);
	}

	/** Get Entry Type.
		@return Entry Type	  */
	public String getA_Entry_Type () 
	{
		return (String)get_Value(COLUMNNAME_A_Entry_Type);
	}

	/** Set Asset Period.
		@param A_Period Asset Period	  */
	public void setA_Period (int A_Period)
	{
		set_Value (COLUMNNAME_A_Period, Integer.valueOf(A_Period));
	}

	/** Get Asset Period.
		@return Asset Period	  */
	public int getA_Period () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Period);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getCR_Account() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getCR_Account_ID(), get_TrxName());	}

	/** Set Account (credit).
		@param CR_Account_ID 
		Account used
	  */
	public void setCR_Account_ID (int CR_Account_ID)
	{
		if (CR_Account_ID < 1) 
			set_Value (COLUMNNAME_CR_Account_ID, null);
		else 
			set_Value (COLUMNNAME_CR_Account_ID, Integer.valueOf(CR_Account_ID));
	}

	/** Get Account (credit).
		@return Account used
	  */
	public int getCR_Account_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CR_Account_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getDR_Account() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getDR_Account_ID(), get_TrxName());	}

	/** Set Account (debit).
		@param DR_Account_ID 
		Account used
	  */
	public void setDR_Account_ID (int DR_Account_ID)
	{
		if (DR_Account_ID < 1) 
			set_Value (COLUMNNAME_DR_Account_ID, null);
		else 
			set_Value (COLUMNNAME_DR_Account_ID, Integer.valueOf(DR_Account_ID));
	}

	/** Get Account (debit).
		@return Account used
	  */
	public int getDR_Account_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DR_Account_ID);
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

	/** Set Expense.
		@param Expense Expense	  */
	public void setExpense (BigDecimal Expense)
	{
		set_Value (COLUMNNAME_Expense, Expense);
	}

	/** Get Expense.
		@return Expense	  */
	public BigDecimal getExpense () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Expense);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Expense (fiscal).
		@param Expense_F Expense (fiscal)	  */
	public void setExpense_F (BigDecimal Expense_F)
	{
		set_Value (COLUMNNAME_Expense_F, Expense_F);
	}

	/** Get Expense (fiscal).
		@return Expense (fiscal)	  */
	public BigDecimal getExpense_F () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Expense_F);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Depreciate.
		@param IsDepreciated 
		The asset will be depreciated
	  */
	public void setIsDepreciated (boolean IsDepreciated)
	{
		set_Value (COLUMNNAME_IsDepreciated, Boolean.valueOf(IsDepreciated));
	}

	/** Get Depreciate.
		@return The asset will be depreciated
	  */
	public boolean isDepreciated () 
	{
		Object oo = get_Value(COLUMNNAME_IsDepreciated);
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
}