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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for A_Depreciation_Workfile
 *  @author Adempiere (generated) 
 *  @version Release 3.6.0LTS - $Id$ */
public class X_A_Depreciation_Workfile extends PO implements I_A_Depreciation_Workfile, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20100614L;

    /** Standard Constructor */
    public X_A_Depreciation_Workfile (Properties ctx, int A_Depreciation_Workfile_ID, String trxName)
    {
      super (ctx, A_Depreciation_Workfile_ID, trxName);
      /** if (A_Depreciation_Workfile_ID == 0)
        {
			setA_Asset_ID (0);
			setA_Asset_Life_Years (0);
			setA_Depreciation_Workfile_ID (0);
			setA_QTY_Current (Env.ZERO);
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

	/** Set Asset Cost.
		@param A_Asset_Cost Asset Cost	  */
	public void setA_Asset_Cost (BigDecimal A_Asset_Cost)
	{
		set_Value (COLUMNNAME_A_Asset_Cost, A_Asset_Cost);
	}

	/** Get Asset Cost.
		@return Asset Cost	  */
	public BigDecimal getA_Asset_Cost () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Asset_Cost);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Asset.
		@param A_Asset_ID 
		Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Asset_ID, null);
		else 
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

	/** Set Asset Life Current Year.
		@param A_Asset_Life_Current_Year Asset Life Current Year	  */
	public void setA_Asset_Life_Current_Year (BigDecimal A_Asset_Life_Current_Year)
	{
		set_Value (COLUMNNAME_A_Asset_Life_Current_Year, A_Asset_Life_Current_Year);
	}

	/** Get Asset Life Current Year.
		@return Asset Life Current Year	  */
	public BigDecimal getA_Asset_Life_Current_Year () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Asset_Life_Current_Year);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Asset Life Years.
		@param A_Asset_Life_Years Asset Life Years	  */
	public void setA_Asset_Life_Years (int A_Asset_Life_Years)
	{
		set_Value (COLUMNNAME_A_Asset_Life_Years, Integer.valueOf(A_Asset_Life_Years));
	}

	/** Get Asset Life Years.
		@return Asset Life Years	  */
	public int getA_Asset_Life_Years () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Life_Years);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Base Amount.
		@param A_Base_Amount Base Amount	  */
	public void setA_Base_Amount (BigDecimal A_Base_Amount)
	{
		set_Value (COLUMNNAME_A_Base_Amount, A_Base_Amount);
	}

	/** Get Base Amount.
		@return Base Amount	  */
	public BigDecimal getA_Base_Amount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Base_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Calc. Accumulated Depr..
		@param A_Calc_Accumulated_Depr Calc. Accumulated Depr.	  */
	public void setA_Calc_Accumulated_Depr (BigDecimal A_Calc_Accumulated_Depr)
	{
		set_Value (COLUMNNAME_A_Calc_Accumulated_Depr, A_Calc_Accumulated_Depr);
	}

	/** Get Calc. Accumulated Depr..
		@return Calc. Accumulated Depr.	  */
	public BigDecimal getA_Calc_Accumulated_Depr () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Calc_Accumulated_Depr);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Curr. Dep. Exp..
		@param A_Curr_Dep_Exp Curr. Dep. Exp.	  */
	public void setA_Curr_Dep_Exp (BigDecimal A_Curr_Dep_Exp)
	{
		set_Value (COLUMNNAME_A_Curr_Dep_Exp, A_Curr_Dep_Exp);
	}

	/** Get Curr. Dep. Exp..
		@return Curr. Dep. Exp.	  */
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

	/** Set Depreciation Workfile.
		@param A_Depreciation_Workfile_ID Depreciation Workfile	  */
	public void setA_Depreciation_Workfile_ID (int A_Depreciation_Workfile_ID)
	{
		if (A_Depreciation_Workfile_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Depreciation_Workfile_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Depreciation_Workfile_ID, Integer.valueOf(A_Depreciation_Workfile_ID));
	}

	/** Get Depreciation Workfile.
		@return Depreciation Workfile	  */
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

	/** Set A_Life_Period.
		@param A_Life_Period A_Life_Period	  */
	public void setA_Life_Period (int A_Life_Period)
	{
		set_Value (COLUMNNAME_A_Life_Period, Integer.valueOf(A_Life_Period));
	}

	/** Get A_Life_Period.
		@return A_Life_Period	  */
	public int getA_Life_Period () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Life_Period);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Forecast Period.
		@param A_Period_Forecast Forecast Period	  */
	public void setA_Period_Forecast (BigDecimal A_Period_Forecast)
	{
		set_Value (COLUMNNAME_A_Period_Forecast, A_Period_Forecast);
	}

	/** Get Forecast Period.
		@return Forecast Period	  */
	public BigDecimal getA_Period_Forecast () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Period_Forecast);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Period Posted.
		@param A_Period_Posted Period Posted	  */
	public void setA_Period_Posted (int A_Period_Posted)
	{
		set_Value (COLUMNNAME_A_Period_Posted, Integer.valueOf(A_Period_Posted));
	}

	/** Get Period Posted.
		@return Period Posted	  */
	public int getA_Period_Posted () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Period_Posted);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Prior. Year Accumulated Depr..
		@param A_Prior_Year_Accumulated_Depr Prior. Year Accumulated Depr.	  */
	public void setA_Prior_Year_Accumulated_Depr (BigDecimal A_Prior_Year_Accumulated_Depr)
	{
		set_Value (COLUMNNAME_A_Prior_Year_Accumulated_Depr, A_Prior_Year_Accumulated_Depr);
	}

	/** Get Prior. Year Accumulated Depr..
		@return Prior. Year Accumulated Depr.	  */
	public BigDecimal getA_Prior_Year_Accumulated_Depr () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Prior_Year_Accumulated_Depr);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param A_QTY_Current Quantity	  */
	public void setA_QTY_Current (BigDecimal A_QTY_Current)
	{
		set_Value (COLUMNNAME_A_QTY_Current, A_QTY_Current);
	}

	/** Get Quantity.
		@return Quantity	  */
	public BigDecimal getA_QTY_Current () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_QTY_Current);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Salvage Value.
		@param A_Salvage_Value Salvage Value	  */
	public void setA_Salvage_Value (BigDecimal A_Salvage_Value)
	{
		set_Value (COLUMNNAME_A_Salvage_Value, A_Salvage_Value);
	}

	/** Get Salvage Value.
		@return Salvage Value	  */
	public BigDecimal getA_Salvage_Value () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Salvage_Value);
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
	/** Set PostingType.
		@param PostingType 
		The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType)
	{

		set_Value (COLUMNNAME_PostingType, PostingType);
	}

	/** Get PostingType.
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
}