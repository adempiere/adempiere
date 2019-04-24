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

/** Generated Model for A_Depreciation_Workfile
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_A_Depreciation_Workfile extends PO implements I_A_Depreciation_Workfile, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_A_Depreciation_Workfile (Properties ctx, int A_Depreciation_Workfile_ID, String trxName)
    {
      super (ctx, A_Depreciation_Workfile_ID, trxName);
      /** if (A_Depreciation_Workfile_ID == 0)
        {
			setA_Accumulated_Depr (Env.ZERO);
// 0
			setA_Accumulated_Depr_F (Env.ZERO);
// 0
			setA_Asset_Cost (Env.ZERO);
// 0
			setA_Asset_ID (0);
			setA_Asset_Life_Current_Year (Env.ZERO);
// 0
			setA_Asset_Life_Years (0);
// 0
			setA_Asset_Life_Years_F (0);
// 0
			setA_Curr_Dep_Exp (Env.ZERO);
// 0
			setA_Depreciation_Workfile_ID (0);
			setA_Life_Period (0);
// 0
			setA_Life_Period_F (0);
// 0
			setA_QTY_Current (Env.ZERO);
			setA_Salvage_Value (Env.ZERO);
// 0
			setA_Tip_Finantare (null);
// 'P'
			setProcessed (false);
// N
			setUseLifeMonths (0);
// 0
			setUseLifeMonths_F (0);
// 0
			setUseLifeYears (0);
// 0
			setUseLifeYears_F (0);
// 0
        } */
    }

    /** Load Constructor */
    public X_A_Depreciation_Workfile (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_A_Depreciation_Workfile[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set A_Asset_Life_Current_Year.
		@param A_Asset_Life_Current_Year A_Asset_Life_Current_Year	  */
	public void setA_Asset_Life_Current_Year (BigDecimal A_Asset_Life_Current_Year)
	{
		set_Value (COLUMNNAME_A_Asset_Life_Current_Year, A_Asset_Life_Current_Year);
	}

	/** Get A_Asset_Life_Current_Year.
		@return A_Asset_Life_Current_Year	  */
	public BigDecimal getA_Asset_Life_Current_Year () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Asset_Life_Current_Year);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Life Years.
		@param A_Asset_Life_Years Life Years	  */
	public void setA_Asset_Life_Years (int A_Asset_Life_Years)
	{
		set_Value (COLUMNNAME_A_Asset_Life_Years, Integer.valueOf(A_Asset_Life_Years));
	}

	/** Get Life Years.
		@return Life Years	  */
	public int getA_Asset_Life_Years () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Life_Years);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Life Years (fiscal).
		@param A_Asset_Life_Years_F Life Years (fiscal)	  */
	public void setA_Asset_Life_Years_F (int A_Asset_Life_Years_F)
	{
		set_Value (COLUMNNAME_A_Asset_Life_Years_F, Integer.valueOf(A_Asset_Life_Years_F));
	}

	/** Get Life Years (fiscal).
		@return Life Years (fiscal)	  */
	public int getA_Asset_Life_Years_F () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Life_Years_F);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Remaining Amt.
		@param A_Asset_Remaining Remaining Amt	  */
	public void setA_Asset_Remaining (BigDecimal A_Asset_Remaining)
	{
		set_ValueNoCheck (COLUMNNAME_A_Asset_Remaining, A_Asset_Remaining);
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
		set_ValueNoCheck (COLUMNNAME_A_Asset_Remaining_F, A_Asset_Remaining_F);
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

	/** Set A_Base_Amount.
		@param A_Base_Amount A_Base_Amount	  */
	public void setA_Base_Amount (BigDecimal A_Base_Amount)
	{
		set_Value (COLUMNNAME_A_Base_Amount, A_Base_Amount);
	}

	/** Get A_Base_Amount.
		@return A_Base_Amount	  */
	public BigDecimal getA_Base_Amount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Base_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set A_Calc_Accumulated_Depr.
		@param A_Calc_Accumulated_Depr A_Calc_Accumulated_Depr	  */
	public void setA_Calc_Accumulated_Depr (BigDecimal A_Calc_Accumulated_Depr)
	{
		set_Value (COLUMNNAME_A_Calc_Accumulated_Depr, A_Calc_Accumulated_Depr);
	}

	/** Get A_Calc_Accumulated_Depr.
		@return A_Calc_Accumulated_Depr	  */
	public BigDecimal getA_Calc_Accumulated_Depr () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Calc_Accumulated_Depr);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set A_Curr_Dep_Exp.
		@param A_Curr_Dep_Exp A_Curr_Dep_Exp	  */
	public void setA_Curr_Dep_Exp (BigDecimal A_Curr_Dep_Exp)
	{
		set_Value (COLUMNNAME_A_Curr_Dep_Exp, A_Curr_Dep_Exp);
	}

	/** Get A_Curr_Dep_Exp.
		@return A_Curr_Dep_Exp	  */
	public BigDecimal getA_Curr_Dep_Exp () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Curr_Dep_Exp);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Current Period.
		@param A_Current_Period Current Period	  */
	public void setA_Current_Period (int A_Current_Period)
	{
		set_Value (COLUMNNAME_A_Current_Period, Integer.valueOf(A_Current_Period));
	}

	/** Get Current Period.
		@return Current Period	  */
	public int getA_Current_Period () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Current_Period);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fixed Asset Balances.
		@param A_Depreciation_Workfile_ID 
		Fixed Asset Balances
	  */
	public void setA_Depreciation_Workfile_ID (int A_Depreciation_Workfile_ID)
	{
		if (A_Depreciation_Workfile_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Depreciation_Workfile_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Depreciation_Workfile_ID, Integer.valueOf(A_Depreciation_Workfile_ID));
	}

	/** Get Fixed Asset Balances.
		@return Fixed Asset Balances
	  */
	public int getA_Depreciation_Workfile_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Workfile_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Depreciation_Workfile_ID()));
    }

	/** Set SL Expense/Period.
		@param A_Expense_SL SL Expense/Period	  */
	public void setA_Expense_SL (BigDecimal A_Expense_SL)
	{
		throw new IllegalArgumentException ("A_Expense_SL is virtual column");	}

	/** Get SL Expense/Period.
		@return SL Expense/Period	  */
	public BigDecimal getA_Expense_SL () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Expense_SL);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set SL Expense/Period (fiscal).
		@param A_Expense_SL_F SL Expense/Period (fiscal)	  */
	public void setA_Expense_SL_F (BigDecimal A_Expense_SL_F)
	{
		throw new IllegalArgumentException ("A_Expense_SL_F is virtual column");	}

	/** Get SL Expense/Period (fiscal).
		@return SL Expense/Period (fiscal)	  */
	public BigDecimal getA_Expense_SL_F () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Expense_SL_F);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_A_FundingMode getA_FundingMode() throws RuntimeException
    {
		return (org.compiere.model.I_A_FundingMode)MTable.get(getCtx(), org.compiere.model.I_A_FundingMode.Table_Name)
			.getPO(getA_FundingMode_ID(), get_TrxName());	}

	/** Set Asset Funding Mode.
		@param A_FundingMode_ID Asset Funding Mode	  */
	public void setA_FundingMode_ID (int A_FundingMode_ID)
	{
		if (A_FundingMode_ID < 1) 
			set_Value (COLUMNNAME_A_FundingMode_ID, null);
		else 
			set_Value (COLUMNNAME_A_FundingMode_ID, Integer.valueOf(A_FundingMode_ID));
	}

	/** Get Asset Funding Mode.
		@return Asset Funding Mode	  */
	public int getA_FundingMode_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_FundingMode_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Life Periods.
		@param A_Life_Period Life Periods	  */
	public void setA_Life_Period (int A_Life_Period)
	{
		set_Value (COLUMNNAME_A_Life_Period, Integer.valueOf(A_Life_Period));
	}

	/** Get Life Periods.
		@return Life Periods	  */
	public int getA_Life_Period () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Life_Period);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Life Period (fiscal).
		@param A_Life_Period_F Life Period (fiscal)	  */
	public void setA_Life_Period_F (int A_Life_Period_F)
	{
		set_Value (COLUMNNAME_A_Life_Period_F, Integer.valueOf(A_Life_Period_F));
	}

	/** Get Life Period (fiscal).
		@return Life Period (fiscal)	  */
	public int getA_Life_Period_F () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Life_Period_F);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Life periods (max).
		@param A_Life_Period_Max Life periods (max)	  */
	public void setA_Life_Period_Max (int A_Life_Period_Max)
	{
		throw new IllegalArgumentException ("A_Life_Period_Max is virtual column");	}

	/** Get Life periods (max).
		@return Life periods (max)	  */
	public int getA_Life_Period_Max () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Life_Period_Max);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Life periods (min).
		@param A_Life_Period_Min Life periods (min)	  */
	public void setA_Life_Period_Min (int A_Life_Period_Min)
	{
		throw new IllegalArgumentException ("A_Life_Period_Min is virtual column");	}

	/** Get Life periods (min).
		@return Life periods (min)	  */
	public int getA_Life_Period_Min () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Life_Period_Min);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Period_Forecast.
		@param A_Period_Forecast A_Period_Forecast	  */
	public void setA_Period_Forecast (BigDecimal A_Period_Forecast)
	{
		set_Value (COLUMNNAME_A_Period_Forecast, A_Period_Forecast);
	}

	/** Get A_Period_Forecast.
		@return A_Period_Forecast	  */
	public BigDecimal getA_Period_Forecast () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_Forecast);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set A_Period_Posted.
		@param A_Period_Posted A_Period_Posted	  */
	public void setA_Period_Posted (int A_Period_Posted)
	{
		set_Value (COLUMNNAME_A_Period_Posted, Integer.valueOf(A_Period_Posted));
	}

	/** Get A_Period_Posted.
		@return A_Period_Posted	  */
	public int getA_Period_Posted () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Period_Posted);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Prior_Year_Accumulated_Depr.
		@param A_Prior_Year_Accumulated_Depr A_Prior_Year_Accumulated_Depr	  */
	public void setA_Prior_Year_Accumulated_Depr (BigDecimal A_Prior_Year_Accumulated_Depr)
	{
		set_Value (COLUMNNAME_A_Prior_Year_Accumulated_Depr, A_Prior_Year_Accumulated_Depr);
	}

	/** Get A_Prior_Year_Accumulated_Depr.
		@return A_Prior_Year_Accumulated_Depr	  */
	public BigDecimal getA_Prior_Year_Accumulated_Depr () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Prior_Year_Accumulated_Depr);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Fixed Asset Current Qty.
		@param A_QTY_Current 
		Fixed Asset Current Quantity
	  */
	public void setA_QTY_Current (BigDecimal A_QTY_Current)
	{
		set_Value (COLUMNNAME_A_QTY_Current, A_QTY_Current);
	}

	/** Get Fixed Asset Current Qty.
		@return Fixed Asset Current Quantity
	  */
	public BigDecimal getA_QTY_Current () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_QTY_Current);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Asset Salvage Value.
		@param A_Salvage_Value Asset Salvage Value	  */
	public void setA_Salvage_Value (BigDecimal A_Salvage_Value)
	{
		set_Value (COLUMNNAME_A_Salvage_Value, A_Salvage_Value);
	}

	/** Get Asset Salvage Value.
		@return Asset Salvage Value	  */
	public BigDecimal getA_Salvage_Value () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Salvage_Value);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** A_Tip_Finantare AD_Reference_ID=53361 */
	public static final int A_TIP_FINANTARE_AD_Reference_ID=53361;
	/** Cofinantare = C */
	public static final String A_TIP_FINANTARE_Cofinantare = "C";
	/** Proprie = P */
	public static final String A_TIP_FINANTARE_Proprie = "P";
	/** Terti = T */
	public static final String A_TIP_FINANTARE_Terti = "T";
	/** Set Financing Type.
		@param A_Tip_Finantare 
		Financing Type
	  */
	public void setA_Tip_Finantare (String A_Tip_Finantare)
	{

		set_Value (COLUMNNAME_A_Tip_Finantare, A_Tip_Finantare);
	}

	/** Get Financing Type.
		@return Financing Type
	  */
	public String getA_Tip_Finantare () 
	{
		return (String)get_Value(COLUMNNAME_A_Tip_Finantare);
	}

	/** Set Own contribution.
		@param A_Valoare_Cofinantare Own contribution	  */
	public void setA_Valoare_Cofinantare (BigDecimal A_Valoare_Cofinantare)
	{
		set_Value (COLUMNNAME_A_Valoare_Cofinantare, A_Valoare_Cofinantare);
	}

	/** Get Own contribution.
		@return Own contribution	  */
	public BigDecimal getA_Valoare_Cofinantare () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Valoare_Cofinantare);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Third contribution.
		@param A_Valoare_Tert Third contribution	  */
	public void setA_Valoare_Tert (BigDecimal A_Valoare_Tert)
	{
		set_Value (COLUMNNAME_A_Valoare_Tert, A_Valoare_Tert);
	}

	/** Get Third contribution.
		@return Third contribution	  */
	public BigDecimal getA_Valoare_Tert () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Valoare_Tert);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Asset Depreciation Date.
		@param AssetDepreciationDate 
		Date of last depreciation
	  */
	public void setAssetDepreciationDate (Timestamp AssetDepreciationDate)
	{
		set_Value (COLUMNNAME_AssetDepreciationDate, AssetDepreciationDate);
	}

	/** Get Asset Depreciation Date.
		@return Date of last depreciation
	  */
	public Timestamp getAssetDepreciationDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AssetDepreciationDate);
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