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

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for A_Asset_Change
 *  @author Adempiere (generated) 
 *  @version Release 3.5.1a - $Id$ */
public class X_A_Asset_Change extends PO implements I_A_Asset_Change, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_A_Asset_Change (Properties ctx, int A_Asset_Change_ID, String trxName)
    {
      super (ctx, A_Asset_Change_ID, trxName);
      /** if (A_Asset_Change_ID == 0)
        {
			setA_Asset_Change_ID (0);
			setA_Asset_ID (0);
			setChangeType (null);
			setTextDetails (null);
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Change (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_A_Asset_Change[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set A_Accumdepreciation_Acct.
		@param A_Accumdepreciation_Acct A_Accumdepreciation_Acct	  */
	public void setA_Accumdepreciation_Acct (int A_Accumdepreciation_Acct)
	{
		set_Value (COLUMNNAME_A_Accumdepreciation_Acct, Integer.valueOf(A_Accumdepreciation_Acct));
	}

	/** Get A_Accumdepreciation_Acct.
		@return A_Accumdepreciation_Acct	  */
	public int getA_Accumdepreciation_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Accumdepreciation_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Asset_Acct.
		@param A_Asset_Acct A_Asset_Acct	  */
	public void setA_Asset_Acct (int A_Asset_Acct)
	{
		set_Value (COLUMNNAME_A_Asset_Acct, Integer.valueOf(A_Asset_Acct));
	}

	/** Get A_Asset_Acct.
		@return A_Asset_Acct	  */
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

	/** Set A_Asset_Addition_ID.
		@param A_Asset_Addition_ID A_Asset_Addition_ID	  */
	public void setA_Asset_Addition_ID (int A_Asset_Addition_ID)
	{
		if (A_Asset_Addition_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Addition_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Addition_ID, Integer.valueOf(A_Asset_Addition_ID));
	}

	/** Get A_Asset_Addition_ID.
		@return A_Asset_Addition_ID	  */
	public int getA_Asset_Addition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Addition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Asset_Change_ID.
		@param A_Asset_Change_ID A_Asset_Change_ID	  */
	public void setA_Asset_Change_ID (int A_Asset_Change_ID)
	{
		if (A_Asset_Change_ID < 1)
			 throw new IllegalArgumentException ("A_Asset_Change_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_A_Asset_Change_ID, Integer.valueOf(A_Asset_Change_ID));
	}

	/** Get A_Asset_Change_ID.
		@return A_Asset_Change_ID	  */
	public int getA_Asset_Change_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Change_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Asset_Change_ID()));
    }

	/** Set A_Asset_CreateDate.
		@param A_Asset_CreateDate A_Asset_CreateDate	  */
	public void setA_Asset_CreateDate (Timestamp A_Asset_CreateDate)
	{
		set_Value (COLUMNNAME_A_Asset_CreateDate, A_Asset_CreateDate);
	}

	/** Get A_Asset_CreateDate.
		@return A_Asset_CreateDate	  */
	public Timestamp getA_Asset_CreateDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_A_Asset_CreateDate);
	}

	/** A_Asset_ID AD_Reference_ID=53258 */
	public static final int A_ASSET_ID_AD_Reference_ID=53258;
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

	/** Set Asset Retirement.
		@param A_Asset_Retirement_ID 
		Internally used asset is not longer used.
	  */
	public void setA_Asset_Retirement_ID (int A_Asset_Retirement_ID)
	{
		if (A_Asset_Retirement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Retirement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Retirement_ID, Integer.valueOf(A_Asset_Retirement_ID));
	}

	/** Get Asset Retirement.
		@return Internally used asset is not longer used.
	  */
	public int getA_Asset_Retirement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Retirement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Asset_RevalDate.
		@param A_Asset_RevalDate A_Asset_RevalDate	  */
	public void setA_Asset_RevalDate (Timestamp A_Asset_RevalDate)
	{
		set_Value (COLUMNNAME_A_Asset_RevalDate, A_Asset_RevalDate);
	}

	/** Get A_Asset_RevalDate.
		@return A_Asset_RevalDate	  */
	public Timestamp getA_Asset_RevalDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_A_Asset_RevalDate);
	}

	/** A_Asset_Spread_Type AD_Reference_ID=53268 */
	public static final int A_ASSET_SPREAD_TYPE_AD_Reference_ID=53268;
	/** Set A_Asset_Spread_Type.
		@param A_Asset_Spread_Type A_Asset_Spread_Type	  */
	public void setA_Asset_Spread_Type (int A_Asset_Spread_Type)
	{
		set_Value (COLUMNNAME_A_Asset_Spread_Type, Integer.valueOf(A_Asset_Spread_Type));
	}

	/** Get A_Asset_Spread_Type.
		@return A_Asset_Spread_Type	  */
	public int getA_Asset_Spread_Type () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Spread_Type);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Depreciation_Acct.
		@param A_Depreciation_Acct A_Depreciation_Acct	  */
	public void setA_Depreciation_Acct (int A_Depreciation_Acct)
	{
		set_Value (COLUMNNAME_A_Depreciation_Acct, Integer.valueOf(A_Depreciation_Acct));
	}

	/** Get A_Depreciation_Acct.
		@return A_Depreciation_Acct	  */
	public int getA_Depreciation_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** A_Depreciation_Calc_Type AD_Reference_ID=53266 */
	public static final int A_DEPRECIATION_CALC_TYPE_AD_Reference_ID=53266;
	/** Set A_Depreciation_Calc_Type.
		@param A_Depreciation_Calc_Type A_Depreciation_Calc_Type	  */
	public void setA_Depreciation_Calc_Type (int A_Depreciation_Calc_Type)
	{
		set_Value (COLUMNNAME_A_Depreciation_Calc_Type, Integer.valueOf(A_Depreciation_Calc_Type));
	}

	/** Get A_Depreciation_Calc_Type.
		@return A_Depreciation_Calc_Type	  */
	public int getA_Depreciation_Calc_Type () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Calc_Type);
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

		if (A_Depreciation_Manual_Period == null || A_Depreciation_Manual_Period.equals("PR") || A_Depreciation_Manual_Period.equals("YR")); else throw new IllegalArgumentException ("A_Depreciation_Manual_Period Invalid value - " + A_Depreciation_Manual_Period + " - Reference_ID=53256 - PR - YR");
		if (A_Depreciation_Manual_Period != null && A_Depreciation_Manual_Period.length() > 2)
		{
			log.warning("Length > 2 - truncated");
			A_Depreciation_Manual_Period = A_Depreciation_Manual_Period.substring(0, 2);
		}
		set_Value (COLUMNNAME_A_Depreciation_Manual_Period, A_Depreciation_Manual_Period);
	}

	/** Get A_Depreciation_Manual_Period.
		@return A_Depreciation_Manual_Period	  */
	public String getA_Depreciation_Manual_Period () 
	{
		return (String)get_Value(COLUMNNAME_A_Depreciation_Manual_Period);
	}

	/** A_Depreciation_Table_Header_ID AD_Reference_ID=53265 */
	public static final int A_DEPRECIATION_TABLE_HEADER_ID_AD_Reference_ID=53265;
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

	/** Set A_Depreciation_Variable_Perc.
		@param A_Depreciation_Variable_Perc A_Depreciation_Variable_Perc	  */
	public void setA_Depreciation_Variable_Perc (BigDecimal A_Depreciation_Variable_Perc)
	{
		set_Value (COLUMNNAME_A_Depreciation_Variable_Perc, A_Depreciation_Variable_Perc);
	}

	/** Get A_Depreciation_Variable_Perc.
		@return A_Depreciation_Variable_Perc	  */
	public BigDecimal getA_Depreciation_Variable_Perc () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Depreciation_Variable_Perc);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set A_Disposal_Loss.
		@param A_Disposal_Loss A_Disposal_Loss	  */
	public void setA_Disposal_Loss (int A_Disposal_Loss)
	{
		set_Value (COLUMNNAME_A_Disposal_Loss, Integer.valueOf(A_Disposal_Loss));
	}

	/** Get A_Disposal_Loss.
		@return A_Disposal_Loss	  */
	public int getA_Disposal_Loss () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Disposal_Loss);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Disposal_Revenue.
		@param A_Disposal_Revenue A_Disposal_Revenue	  */
	public void setA_Disposal_Revenue (int A_Disposal_Revenue)
	{
		set_Value (COLUMNNAME_A_Disposal_Revenue, Integer.valueOf(A_Disposal_Revenue));
	}

	/** Get A_Disposal_Revenue.
		@return A_Disposal_Revenue	  */
	public int getA_Disposal_Revenue () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Disposal_Revenue);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** A_Parent_Asset_ID AD_Reference_ID=53258 */
	public static final int A_PARENT_ASSET_ID_AD_Reference_ID=53258;
	/** Set A_Parent_Asset_ID.
		@param A_Parent_Asset_ID A_Parent_Asset_ID	  */
	public void setA_Parent_Asset_ID (int A_Parent_Asset_ID)
	{
		if (A_Parent_Asset_ID < 1) 
			set_Value (COLUMNNAME_A_Parent_Asset_ID, null);
		else 
			set_Value (COLUMNNAME_A_Parent_Asset_ID, Integer.valueOf(A_Parent_Asset_ID));
	}

	/** Get A_Parent_Asset_ID.
		@return A_Parent_Asset_ID	  */
	public int getA_Parent_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Parent_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Period_End.
		@param A_Period_End A_Period_End	  */
	public void setA_Period_End (int A_Period_End)
	{
		set_Value (COLUMNNAME_A_Period_End, Integer.valueOf(A_Period_End));
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
		set_Value (COLUMNNAME_A_Period_Start, Integer.valueOf(A_Period_Start));
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

	/** Set A_QTY_Current.
		@param A_QTY_Current A_QTY_Current	  */
	public void setA_QTY_Current (BigDecimal A_QTY_Current)
	{
		set_Value (COLUMNNAME_A_QTY_Current, A_QTY_Current);
	}

	/** Get A_QTY_Current.
		@return A_QTY_Current	  */
	public BigDecimal getA_QTY_Current () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_QTY_Current);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set A_QTY_Original.
		@param A_QTY_Original A_QTY_Original	  */
	public void setA_QTY_Original (BigDecimal A_QTY_Original)
	{
		set_Value (COLUMNNAME_A_QTY_Original, A_QTY_Original);
	}

	/** Get A_QTY_Original.
		@return A_QTY_Original	  */
	public BigDecimal getA_QTY_Original () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_QTY_Original);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set A_Reval_Accumdep_Offset_Cur.
		@param A_Reval_Accumdep_Offset_Cur A_Reval_Accumdep_Offset_Cur	  */
	public void setA_Reval_Accumdep_Offset_Cur (int A_Reval_Accumdep_Offset_Cur)
	{
		set_Value (COLUMNNAME_A_Reval_Accumdep_Offset_Cur, Integer.valueOf(A_Reval_Accumdep_Offset_Cur));
	}

	/** Get A_Reval_Accumdep_Offset_Cur.
		@return A_Reval_Accumdep_Offset_Cur	  */
	public int getA_Reval_Accumdep_Offset_Cur () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Accumdep_Offset_Cur);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Reval_Accumdep_Offset_Prior.
		@param A_Reval_Accumdep_Offset_Prior A_Reval_Accumdep_Offset_Prior	  */
	public void setA_Reval_Accumdep_Offset_Prior (int A_Reval_Accumdep_Offset_Prior)
	{
		set_Value (COLUMNNAME_A_Reval_Accumdep_Offset_Prior, Integer.valueOf(A_Reval_Accumdep_Offset_Prior));
	}

	/** Get A_Reval_Accumdep_Offset_Prior.
		@return A_Reval_Accumdep_Offset_Prior	  */
	public int getA_Reval_Accumdep_Offset_Prior () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Accumdep_Offset_Prior);
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

		if (A_Reval_Cal_Method == null || A_Reval_Cal_Method.equals("DFT") || A_Reval_Cal_Method.equals("IDF") || A_Reval_Cal_Method.equals("YBF")); else throw new IllegalArgumentException ("A_Reval_Cal_Method Invalid value - " + A_Reval_Cal_Method + " - Reference_ID=53259 - DFT - IDF - YBF");
		if (A_Reval_Cal_Method != null && A_Reval_Cal_Method.length() > 3)
		{
			log.warning("Length > 3 - truncated");
			A_Reval_Cal_Method = A_Reval_Cal_Method.substring(0, 3);
		}
		set_Value (COLUMNNAME_A_Reval_Cal_Method, A_Reval_Cal_Method);
	}

	/** Get A_Reval_Cal_Method.
		@return A_Reval_Cal_Method	  */
	public String getA_Reval_Cal_Method () 
	{
		return (String)get_Value(COLUMNNAME_A_Reval_Cal_Method);
	}

	/** Set A_Reval_Cost_Offset.
		@param A_Reval_Cost_Offset A_Reval_Cost_Offset	  */
	public void setA_Reval_Cost_Offset (int A_Reval_Cost_Offset)
	{
		set_Value (COLUMNNAME_A_Reval_Cost_Offset, Integer.valueOf(A_Reval_Cost_Offset));
	}

	/** Get A_Reval_Cost_Offset.
		@return A_Reval_Cost_Offset	  */
	public int getA_Reval_Cost_Offset () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Cost_Offset);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Reval_Cost_Offset_Prior.
		@param A_Reval_Cost_Offset_Prior A_Reval_Cost_Offset_Prior	  */
	public void setA_Reval_Cost_Offset_Prior (int A_Reval_Cost_Offset_Prior)
	{
		set_Value (COLUMNNAME_A_Reval_Cost_Offset_Prior, Integer.valueOf(A_Reval_Cost_Offset_Prior));
	}

	/** Get A_Reval_Cost_Offset_Prior.
		@return A_Reval_Cost_Offset_Prior	  */
	public int getA_Reval_Cost_Offset_Prior () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Cost_Offset_Prior);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Reval_Depexp_Offset.
		@param A_Reval_Depexp_Offset A_Reval_Depexp_Offset	  */
	public void setA_Reval_Depexp_Offset (int A_Reval_Depexp_Offset)
	{
		set_Value (COLUMNNAME_A_Reval_Depexp_Offset, Integer.valueOf(A_Reval_Depexp_Offset));
	}

	/** Get A_Reval_Depexp_Offset.
		@return A_Reval_Depexp_Offset	  */
	public int getA_Reval_Depexp_Offset () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Depexp_Offset);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Salvage_Value.
		@param A_Salvage_Value A_Salvage_Value	  */
	public void setA_Salvage_Value (BigDecimal A_Salvage_Value)
	{
		set_Value (COLUMNNAME_A_Salvage_Value, A_Salvage_Value);
	}

	/** Get A_Salvage_Value.
		@return A_Salvage_Value	  */
	public BigDecimal getA_Salvage_Value () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Salvage_Value);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set A_Split_Percent.
		@param A_Split_Percent A_Split_Percent	  */
	public void setA_Split_Percent (BigDecimal A_Split_Percent)
	{
		set_Value (COLUMNNAME_A_Split_Percent, A_Split_Percent);
	}

	/** Get A_Split_Percent.
		@return A_Split_Percent	  */
	public BigDecimal getA_Split_Percent () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Split_Percent);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Ad_User_ID AD_Reference_ID=286 */
	public static final int AD_USER_ID_AD_Reference_ID=286;
	/** Set Ad_User_ID.
		@param Ad_User_ID Ad_User_ID	  */
	public void setAd_User_ID (int Ad_User_ID)
	{
		if (Ad_User_ID < 1) 
			set_Value (COLUMNNAME_Ad_User_ID, null);
		else 
			set_Value (COLUMNNAME_Ad_User_ID, Integer.valueOf(Ad_User_ID));
	}

	/** Get Ad_User_ID.
		@return Ad_User_ID	  */
	public int getAd_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ad_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set AssetAccumDepreciationAmt.
		@param AssetAccumDepreciationAmt AssetAccumDepreciationAmt	  */
	public void setAssetAccumDepreciationAmt (BigDecimal AssetAccumDepreciationAmt)
	{
		set_ValueNoCheck (COLUMNNAME_AssetAccumDepreciationAmt, AssetAccumDepreciationAmt);
	}

	/** Get AssetAccumDepreciationAmt.
		@return AssetAccumDepreciationAmt	  */
	public BigDecimal getAssetAccumDepreciationAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AssetAccumDepreciationAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set AssetBookValueAmt.
		@param AssetBookValueAmt AssetBookValueAmt	  */
	public void setAssetBookValueAmt (BigDecimal AssetBookValueAmt)
	{
		set_ValueNoCheck (COLUMNNAME_AssetBookValueAmt, AssetBookValueAmt);
	}

	/** Get AssetBookValueAmt.
		@return AssetBookValueAmt	  */
	public BigDecimal getAssetBookValueAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AssetBookValueAmt);
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
		set_ValueNoCheck (COLUMNNAME_AssetDepreciationDate, AssetDepreciationDate);
	}

	/** Get Asset Depreciation Date.
		@return Date of last depreciation
	  */
	public Timestamp getAssetDepreciationDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AssetDepreciationDate);
	}

	/** Set Asset Disposal Date.
		@param AssetDisposalDate 
		Date when the asset is/was disposed
	  */
	public void setAssetDisposalDate (Timestamp AssetDisposalDate)
	{
		set_Value (COLUMNNAME_AssetDisposalDate, AssetDisposalDate);
	}

	/** Get Asset Disposal Date.
		@return Date when the asset is/was disposed
	  */
	public Timestamp getAssetDisposalDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AssetDisposalDate);
	}

	/** Set Market value Amount.
		@param AssetMarketValueAmt 
		Market value of the asset
	  */
	public void setAssetMarketValueAmt (BigDecimal AssetMarketValueAmt)
	{
		set_ValueNoCheck (COLUMNNAME_AssetMarketValueAmt, AssetMarketValueAmt);
	}

	/** Get Market value Amount.
		@return Market value of the asset
	  */
	public BigDecimal getAssetMarketValueAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AssetMarketValueAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set In Service Date.
		@param AssetServiceDate 
		Date when Asset was put into service
	  */
	public void setAssetServiceDate (Timestamp AssetServiceDate)
	{
		set_ValueNoCheck (COLUMNNAME_AssetServiceDate, AssetServiceDate);
	}

	/** Get In Service Date.
		@return Date when Asset was put into service
	  */
	public Timestamp getAssetServiceDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AssetServiceDate);
	}

	/** Set Asset value.
		@param AssetValueAmt 
		Book Value of the asset
	  */
	public void setAssetValueAmt (BigDecimal AssetValueAmt)
	{
		set_ValueNoCheck (COLUMNNAME_AssetValueAmt, AssetValueAmt);
	}

	/** Get Asset value.
		@return Book Value of the asset
	  */
	public BigDecimal getAssetValueAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AssetValueAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_C_AcctSchema getC_AcctSchema() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_AcctSchema.Table_Name);
        I_C_AcctSchema result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_AcctSchema)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_AcctSchema_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

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

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** C_BPartner_Location_ID AD_Reference_ID=159 */
	public static final int C_BPARTNER_LOCATION_ID_AD_Reference_ID=159;
	/** Set Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** C_Location_ID AD_Reference_ID=133 */
	public static final int C_LOCATION_ID_AD_Reference_ID=133;
	/** Set Address.
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address.
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Combination.
		@param C_ValidCombination_ID 
		Valid Account Combination
	  */
	public void setC_ValidCombination_ID (int C_ValidCombination_ID)
	{
		if (C_ValidCombination_ID < 1) 
			set_Value (COLUMNNAME_C_ValidCombination_ID, null);
		else 
			set_Value (COLUMNNAME_C_ValidCombination_ID, Integer.valueOf(C_ValidCombination_ID));
	}

	/** Get Combination.
		@return Valid Account Combination
	  */
	public int getC_ValidCombination_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ValidCombination_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ChangeAmt.
		@param ChangeAmt ChangeAmt	  */
	public void setChangeAmt (BigDecimal ChangeAmt)
	{
		set_ValueNoCheck (COLUMNNAME_ChangeAmt, ChangeAmt);
	}

	/** Get ChangeAmt.
		@return ChangeAmt	  */
	public BigDecimal getChangeAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ChangeAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ChangeDate.
		@param ChangeDate ChangeDate	  */
	public void setChangeDate (Timestamp ChangeDate)
	{
		set_ValueNoCheck (COLUMNNAME_ChangeDate, ChangeDate);
	}

	/** Get ChangeDate.
		@return ChangeDate	  */
	public Timestamp getChangeDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ChangeDate);
	}

	/** ChangeType AD_Reference_ID=53273 */
	public static final int CHANGETYPE_AD_Reference_ID=53273;
	/** Addition = ADD */
	public static final String CHANGETYPE_Addition = "ADD";
	/** Balance = BAL */
	public static final String CHANGETYPE_Balance = "BAL";
	/** Create = CRT */
	public static final String CHANGETYPE_Create = "CRT";
	/** Depreciation = DEP */
	public static final String CHANGETYPE_Depreciation = "DEP";
	/** Disposal = DIS */
	public static final String CHANGETYPE_Disposal = "DIS";
	/** Expense = EXP */
	public static final String CHANGETYPE_Expense = "EXP";
	/** Forecast = FOR */
	public static final String CHANGETYPE_Forecast = "FOR";
	/** Import = IMP */
	public static final String CHANGETYPE_Import = "IMP";
	/** Revaluation = RVL */
	public static final String CHANGETYPE_Revaluation = "RVL";
	/** Setup = SET */
	public static final String CHANGETYPE_Setup = "SET";
	/** Split = SPL */
	public static final String CHANGETYPE_Split = "SPL";
	/** Transfer = TRN */
	public static final String CHANGETYPE_Transfer = "TRN";
	/** Update = UPD */
	public static final String CHANGETYPE_Update = "UPD";
	/** Usage = USE */
	public static final String CHANGETYPE_Usage = "USE";
	/** Set ChangeType.
		@param ChangeType ChangeType	  */
	public void setChangeType (String ChangeType)
	{
		if (ChangeType == null) throw new IllegalArgumentException ("ChangeType is mandatory");
		if (ChangeType.equals("ADD") || ChangeType.equals("BAL") || ChangeType.equals("CRT") || ChangeType.equals("DEP") || ChangeType.equals("DIS") || ChangeType.equals("EXP") || ChangeType.equals("FOR") || ChangeType.equals("IMP") || ChangeType.equals("RVL") || ChangeType.equals("SET") || ChangeType.equals("SPL") || ChangeType.equals("TRN") || ChangeType.equals("UPD") || ChangeType.equals("USE")); else throw new IllegalArgumentException ("ChangeType Invalid value - " + ChangeType + " - Reference_ID=53273 - ADD - BAL - CRT - DEP - DIS - EXP - FOR - IMP - RVL - SET - SPL - TRN - UPD - USE");
		if (ChangeType.length() > 3)
		{
			log.warning("Length > 3 - truncated");
			ChangeType = ChangeType.substring(0, 3);
		}
		set_ValueNoCheck (COLUMNNAME_ChangeType, ChangeType);
	}

	/** Get ChangeType.
		@return ChangeType	  */
	public String getChangeType () 
	{
		return (String)get_Value(COLUMNNAME_ChangeType);
	}

	/** ConventionType AD_Reference_ID=53267 */
	public static final int CONVENTIONTYPE_AD_Reference_ID=53267;
	/** Set ConventionType.
		@param ConventionType ConventionType	  */
	public void setConventionType (int ConventionType)
	{
		set_Value (COLUMNNAME_ConventionType, Integer.valueOf(ConventionType));
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

	/** DepreciationType AD_Reference_ID=53264 */
	public static final int DEPRECIATIONTYPE_AD_Reference_ID=53264;
	/** Set DepreciationType.
		@param DepreciationType DepreciationType	  */
	public void setDepreciationType (int DepreciationType)
	{
		set_Value (COLUMNNAME_DepreciationType, Integer.valueOf(DepreciationType));
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

	/** Set Disposed.
		@param IsDisposed 
		The asset is disposed
	  */
	public void setIsDisposed (boolean IsDisposed)
	{
		set_Value (COLUMNNAME_IsDisposed, Boolean.valueOf(IsDisposed));
	}

	/** Get Disposed.
		@return The asset is disposed
	  */
	public boolean isDisposed () 
	{
		Object oo = get_Value(COLUMNNAME_IsDisposed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Fully depreciated.
		@param IsFullyDepreciated 
		The asset is fully depreciated
	  */
	public void setIsFullyDepreciated (boolean IsFullyDepreciated)
	{
		set_Value (COLUMNNAME_IsFullyDepreciated, Boolean.valueOf(IsFullyDepreciated));
	}

	/** Get Fully depreciated.
		@return The asset is fully depreciated
	  */
	public boolean isFullyDepreciated () 
	{
		Object oo = get_Value(COLUMNNAME_IsFullyDepreciated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set In Possession.
		@param IsInPosession 
		The asset is in the possession of the organization
	  */
	public void setIsInPosession (boolean IsInPosession)
	{
		set_Value (COLUMNNAME_IsInPosession, Boolean.valueOf(IsInPosession));
	}

	/** Get In Possession.
		@return The asset is in the possession of the organization
	  */
	public boolean isInPosession () 
	{
		Object oo = get_Value(COLUMNNAME_IsInPosession);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Owned.
		@param IsOwned 
		The asset is owned by the organization
	  */
	public void setIsOwned (boolean IsOwned)
	{
		set_Value (COLUMNNAME_IsOwned, Boolean.valueOf(IsOwned));
	}

	/** Get Owned.
		@return The asset is owned by the organization
	  */
	public boolean isOwned () 
	{
		Object oo = get_Value(COLUMNNAME_IsOwned);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Life use.
		@param LifeUseUnits 
		Units of use until the asset is not usable anymore
	  */
	public void setLifeUseUnits (int LifeUseUnits)
	{
		set_ValueNoCheck (COLUMNNAME_LifeUseUnits, Integer.valueOf(LifeUseUnits));
	}

	/** Get Life use.
		@return Units of use until the asset is not usable anymore
	  */
	public int getLifeUseUnits () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LifeUseUnits);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Lot No.
		@param Lot 
		Lot number (alphanumeric)
	  */
	public void setLot (String Lot)
	{

		if (Lot != null && Lot.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			Lot = Lot.substring(0, 40);
		}
		set_ValueNoCheck (COLUMNNAME_Lot, Lot);
	}

	/** Get Lot No.
		@return Lot number (alphanumeric)
	  */
	public String getLot () 
	{
		return (String)get_Value(COLUMNNAME_Lot);
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
	/** Set PostingType.
		@param PostingType 
		The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType)
	{

		if (PostingType == null || PostingType.equals("A") || PostingType.equals("B") || PostingType.equals("E") || PostingType.equals("S") || PostingType.equals("R")); else throw new IllegalArgumentException ("PostingType Invalid value - " + PostingType + " - Reference_ID=125 - A - B - E - S - R");
		if (PostingType != null && PostingType.length() > 10)
		{
			log.warning("Length > 10 - truncated");
			PostingType = PostingType.substring(0, 10);
		}
		set_Value (COLUMNNAME_PostingType, PostingType);
	}

	/** Get PostingType.
		@return The type of posted amount for the transaction
	  */
	public String getPostingType () 
	{
		return (String)get_Value(COLUMNNAME_PostingType);
	}

	/** Set Serno.
		@param Serno Serno	  */
	public void setSerno (String Serno)
	{

		if (Serno != null && Serno.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			Serno = Serno.substring(0, 40);
		}
		set_ValueNoCheck (COLUMNNAME_Serno, Serno);
	}

	/** Get Serno.
		@return Serno	  */
	public String getSerno () 
	{
		return (String)get_Value(COLUMNNAME_Serno);
	}

	/** Set Details.
		@param TextDetails Details	  */
	public void setTextDetails (String TextDetails)
	{
		if (TextDetails == null)
			throw new IllegalArgumentException ("TextDetails is mandatory.");

		if (TextDetails.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			TextDetails = TextDetails.substring(0, 60);
		}
		set_ValueNoCheck (COLUMNNAME_TextDetails, TextDetails);
	}

	/** Get Details.
		@return Details	  */
	public String getTextDetails () 
	{
		return (String)get_Value(COLUMNNAME_TextDetails);
	}

	/** Set Usable Life - Months.
		@param UseLifeMonths 
		Months of the usable life of the asset
	  */
	public void setUseLifeMonths (int UseLifeMonths)
	{
		set_ValueNoCheck (COLUMNNAME_UseLifeMonths, Integer.valueOf(UseLifeMonths));
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

	/** Set Usable Life - Years.
		@param UseLifeYears 
		Years of the usable life of the asset
	  */
	public void setUseLifeYears (int UseLifeYears)
	{
		set_ValueNoCheck (COLUMNNAME_UseLifeYears, Integer.valueOf(UseLifeYears));
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

	/** Set Use units.
		@param UseUnits 
		Currently used units of the assets
	  */
	public void setUseUnits (int UseUnits)
	{
		set_Value (COLUMNNAME_UseUnits, Integer.valueOf(UseUnits));
	}

	/** Get Use units.
		@return Currently used units of the assets
	  */
	public int getUseUnits () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UseUnits);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Versionno.
		@param Versionno Versionno	  */
	public void setVersionno (String Versionno)
	{

		if (Versionno != null && Versionno.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			Versionno = Versionno.substring(0, 40);
		}
		set_ValueNoCheck (COLUMNNAME_Versionno, Versionno);
	}

	/** Get Versionno.
		@return Versionno	  */
	public String getVersionno () 
	{
		return (String)get_Value(COLUMNNAME_Versionno);
	}
}