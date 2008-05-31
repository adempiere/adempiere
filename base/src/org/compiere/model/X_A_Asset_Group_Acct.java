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
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for A_Asset_Group_Acct
 *  @author Adempiere (generated) 
 *  @version Release 3.5.1a - $Id$ */
public class X_A_Asset_Group_Acct extends PO implements I_A_Asset_Group_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_A_Asset_Group_Acct (Properties ctx, int A_Asset_Group_Acct_ID, String trxName)
    {
      super (ctx, A_Asset_Group_Acct_ID, trxName);
      /** if (A_Asset_Group_Acct_ID == 0)
        {
			setA_Asset_Group_Acct_ID (0);
			setA_Asset_Group_ID (0);
			setA_Depreciation_Calc_Type (0);
			setA_Split_Percent (Env.ZERO);
			setC_AcctSchema_ID (0);
			setConventionType (0);
			setDepreciationType (0);
			setPostingType (null);
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Group_Acct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_A_Asset_Group_Acct[")
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

	/** Set A_Asset_Group_Acct_ID.
		@param A_Asset_Group_Acct_ID A_Asset_Group_Acct_ID	  */
	public void setA_Asset_Group_Acct_ID (int A_Asset_Group_Acct_ID)
	{
		if (A_Asset_Group_Acct_ID < 1)
			 throw new IllegalArgumentException ("A_Asset_Group_Acct_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_A_Asset_Group_Acct_ID, Integer.valueOf(A_Asset_Group_Acct_ID));
	}

	/** Get A_Asset_Group_Acct_ID.
		@return A_Asset_Group_Acct_ID	  */
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

	public I_A_Asset_Group getA_Asset_Group() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_A_Asset_Group.Table_Name);
        I_A_Asset_Group result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_A_Asset_Group)constructor.newInstance(new Object[] {getCtx(), new Integer(getA_Asset_Group_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Asset Group.
		@param A_Asset_Group_ID 
		Group of Assets
	  */
	public void setA_Asset_Group_ID (int A_Asset_Group_ID)
	{
		if (A_Asset_Group_ID < 1)
			 throw new IllegalArgumentException ("A_Asset_Group_ID is mandatory.");
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

	/** Set A_Depreciation_ID.
		@param A_Depreciation_ID A_Depreciation_ID	  */
	public void setA_Depreciation_ID (int A_Depreciation_ID)
	{
		if (A_Depreciation_ID < 1) 
			set_Value (COLUMNNAME_A_Depreciation_ID, null);
		else 
			set_Value (COLUMNNAME_A_Depreciation_ID, Integer.valueOf(A_Depreciation_ID));
	}

	/** Get A_Depreciation_ID.
		@return A_Depreciation_ID	  */
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

	/** Set A_Disposal_Gain.
		@param A_Disposal_Gain A_Disposal_Gain	  */
	public void setA_Disposal_Gain (int A_Disposal_Gain)
	{
		set_Value (COLUMNNAME_A_Disposal_Gain, Integer.valueOf(A_Disposal_Gain));
	}

	/** Get A_Disposal_Gain.
		@return A_Disposal_Gain	  */
	public int getA_Disposal_Gain () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Disposal_Gain);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
		if (A_Reval_Cal_Method != null && A_Reval_Cal_Method.length() > 22)
		{
			log.warning("Length > 22 - truncated");
			A_Reval_Cal_Method = A_Reval_Cal_Method.substring(0, 22);
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

	/** Set A_Split_Percent.
		@param A_Split_Percent A_Split_Percent	  */
	public void setA_Split_Percent (BigDecimal A_Split_Percent)
	{
		if (A_Split_Percent == null)
			throw new IllegalArgumentException ("A_Split_Percent is mandatory.");
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
			 throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
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
		if (PostingType == null) throw new IllegalArgumentException ("PostingType is mandatory");
		if (PostingType.equals("A") || PostingType.equals("B") || PostingType.equals("E") || PostingType.equals("S") || PostingType.equals("R")); else throw new IllegalArgumentException ("PostingType Invalid value - " + PostingType + " - Reference_ID=125 - A - B - E - S - R");
		if (PostingType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			PostingType = PostingType.substring(0, 1);
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
}