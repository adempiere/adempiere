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

/** Generated Model for A_Asset_Transfer
 *  @author Adempiere (generated) 
 *  @version Release 3.5.1a - $Id$ */
public class X_A_Asset_Transfer extends PO implements I_A_Asset_Transfer, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_A_Asset_Transfer (Properties ctx, int A_Asset_Transfer_ID, String trxName)
    {
      super (ctx, A_Asset_Transfer_ID, trxName);
      /** if (A_Asset_Transfer_ID == 0)
        {
			setA_Accumdepreciation_Acct_New (0);
			setA_Asset_Acct_New (0);
			setA_Asset_Transfer_ID (0);
			setA_Depreciation_Acct_New (0);
			setA_Disposal_Loss_New (0);
			setA_Disposal_Revenue_New (0);
			setA_Period_End (0);
// @SQL=SELECT A_Period_End FROM A_Asset_Acct WHERE A_Asset_Acct.A_Asset_Acct_ID=@A_Asset_Acct_ID@
			setA_Period_Start (0);
// @SQL=SELECT A_Period_Start FROM A_Asset_Acct WHERE A_Asset_Acct.A_Asset_Acct_ID=@A_Asset_Acct_ID@
			setA_Split_Percent (Env.ZERO);
// @SQL=SELECT A_Split_Percent FROM A_Asset_Acct WHERE A_Asset_Acct.A_Asset_Acct_ID=@A_Asset_Acct_ID@
			setA_Transfer_Balance (true);
// Y
			setA_Transfer_Balance_IS (false);
			setDateAcct (new Timestamp(System.currentTimeMillis()));
// @Date@
			setProcessed (false);
			setProcessing (false);
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

	/** Set A_Accumdepreciation_Acct_New.
		@param A_Accumdepreciation_Acct_New A_Accumdepreciation_Acct_New	  */
	public void setA_Accumdepreciation_Acct_New (int A_Accumdepreciation_Acct_New)
	{
		set_Value (COLUMNNAME_A_Accumdepreciation_Acct_New, Integer.valueOf(A_Accumdepreciation_Acct_New));
	}

	/** Get A_Accumdepreciation_Acct_New.
		@return A_Accumdepreciation_Acct_New	  */
	public int getA_Accumdepreciation_Acct_New () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Accumdepreciation_Acct_New);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Accumdepreciation_Acct_Str.
		@param A_Accumdepreciation_Acct_Str A_Accumdepreciation_Acct_Str	  */
	public void setA_Accumdepreciation_Acct_Str (String A_Accumdepreciation_Acct_Str)
	{

		if (A_Accumdepreciation_Acct_Str != null && A_Accumdepreciation_Acct_Str.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			A_Accumdepreciation_Acct_Str = A_Accumdepreciation_Acct_Str.substring(0, 40);
		}
		set_Value (COLUMNNAME_A_Accumdepreciation_Acct_Str, A_Accumdepreciation_Acct_Str);
	}

	/** Get A_Accumdepreciation_Acct_Str.
		@return A_Accumdepreciation_Acct_Str	  */
	public String getA_Accumdepreciation_Acct_Str () 
	{
		return (String)get_Value(COLUMNNAME_A_Accumdepreciation_Acct_Str);
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

	/** Set A_Asset_Acct_New.
		@param A_Asset_Acct_New A_Asset_Acct_New	  */
	public void setA_Asset_Acct_New (int A_Asset_Acct_New)
	{
		set_Value (COLUMNNAME_A_Asset_Acct_New, Integer.valueOf(A_Asset_Acct_New));
	}

	/** Get A_Asset_Acct_New.
		@return A_Asset_Acct_New	  */
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

		if (A_Asset_Acct_Str != null && A_Asset_Acct_Str.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			A_Asset_Acct_Str = A_Asset_Acct_Str.substring(0, 40);
		}
		set_Value (COLUMNNAME_A_Asset_Acct_Str, A_Asset_Acct_Str);
	}

	/** Get A_Asset_Acct_Str.
		@return A_Asset_Acct_Str	  */
	public String getA_Asset_Acct_Str () 
	{
		return (String)get_Value(COLUMNNAME_A_Asset_Acct_Str);
	}

	/** Set Asset.
		@param A_Asset_ID 
		Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1) 
			set_Value (COLUMNNAME_A_Asset_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
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

	/** Set A_Asset_Transfer_ID.
		@param A_Asset_Transfer_ID A_Asset_Transfer_ID	  */
	public void setA_Asset_Transfer_ID (int A_Asset_Transfer_ID)
	{
		if (A_Asset_Transfer_ID < 1)
			 throw new IllegalArgumentException ("A_Asset_Transfer_ID is mandatory.");
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

	/** Set A_Depreciation_Acct_New.
		@param A_Depreciation_Acct_New A_Depreciation_Acct_New	  */
	public void setA_Depreciation_Acct_New (int A_Depreciation_Acct_New)
	{
		set_Value (COLUMNNAME_A_Depreciation_Acct_New, Integer.valueOf(A_Depreciation_Acct_New));
	}

	/** Get A_Depreciation_Acct_New.
		@return A_Depreciation_Acct_New	  */
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

		if (A_Depreciation_Acct_Str != null && A_Depreciation_Acct_Str.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			A_Depreciation_Acct_Str = A_Depreciation_Acct_Str.substring(0, 40);
		}
		set_ValueNoCheck (COLUMNNAME_A_Depreciation_Acct_Str, A_Depreciation_Acct_Str);
	}

	/** Get A_Depreciation_Acct_Str.
		@return A_Depreciation_Acct_Str	  */
	public String getA_Depreciation_Acct_Str () 
	{
		return (String)get_Value(COLUMNNAME_A_Depreciation_Acct_Str);
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

	/** Set A_Disposal_Loss_New.
		@param A_Disposal_Loss_New A_Disposal_Loss_New	  */
	public void setA_Disposal_Loss_New (int A_Disposal_Loss_New)
	{
		set_Value (COLUMNNAME_A_Disposal_Loss_New, Integer.valueOf(A_Disposal_Loss_New));
	}

	/** Get A_Disposal_Loss_New.
		@return A_Disposal_Loss_New	  */
	public int getA_Disposal_Loss_New () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Disposal_Loss_New);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Disposal_Loss_Str.
		@param A_Disposal_Loss_Str A_Disposal_Loss_Str	  */
	public void setA_Disposal_Loss_Str (String A_Disposal_Loss_Str)
	{

		if (A_Disposal_Loss_Str != null && A_Disposal_Loss_Str.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			A_Disposal_Loss_Str = A_Disposal_Loss_Str.substring(0, 40);
		}
		set_ValueNoCheck (COLUMNNAME_A_Disposal_Loss_Str, A_Disposal_Loss_Str);
	}

	/** Get A_Disposal_Loss_Str.
		@return A_Disposal_Loss_Str	  */
	public String getA_Disposal_Loss_Str () 
	{
		return (String)get_Value(COLUMNNAME_A_Disposal_Loss_Str);
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

	/** Set A_Disposal_Revenue_New.
		@param A_Disposal_Revenue_New A_Disposal_Revenue_New	  */
	public void setA_Disposal_Revenue_New (int A_Disposal_Revenue_New)
	{
		set_Value (COLUMNNAME_A_Disposal_Revenue_New, Integer.valueOf(A_Disposal_Revenue_New));
	}

	/** Get A_Disposal_Revenue_New.
		@return A_Disposal_Revenue_New	  */
	public int getA_Disposal_Revenue_New () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Disposal_Revenue_New);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Disposal_Revenue_Str.
		@param A_Disposal_Revenue_Str A_Disposal_Revenue_Str	  */
	public void setA_Disposal_Revenue_Str (String A_Disposal_Revenue_Str)
	{

		if (A_Disposal_Revenue_Str != null && A_Disposal_Revenue_Str.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			A_Disposal_Revenue_Str = A_Disposal_Revenue_Str.substring(0, 40);
		}
		set_ValueNoCheck (COLUMNNAME_A_Disposal_Revenue_Str, A_Disposal_Revenue_Str);
	}

	/** Get A_Disposal_Revenue_Str.
		@return A_Disposal_Revenue_Str	  */
	public String getA_Disposal_Revenue_Str () 
	{
		return (String)get_Value(COLUMNNAME_A_Disposal_Revenue_Str);
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

	/** Set A_Transfer_Balance_IS.
		@param A_Transfer_Balance_IS A_Transfer_Balance_IS	  */
	public void setA_Transfer_Balance_IS (boolean A_Transfer_Balance_IS)
	{
		set_Value (COLUMNNAME_A_Transfer_Balance_IS, Boolean.valueOf(A_Transfer_Balance_IS));
	}

	/** Get A_Transfer_Balance_IS.
		@return A_Transfer_Balance_IS	  */
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

	/** C_AcctSchema_ID AD_Reference_ID=136 */
	public static final int C_ACCTSCHEMA_ID_AD_Reference_ID=136;
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

	/** C_Period_ID AD_Reference_ID=233 */
	public static final int C_PERIOD_ID_AD_Reference_ID=233;
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
		if (DateAcct == null)
			throw new IllegalArgumentException ("DateAcct is mandatory.");
		set_Value (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
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
		if (PostingType != null && PostingType.length() > 1)
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
}