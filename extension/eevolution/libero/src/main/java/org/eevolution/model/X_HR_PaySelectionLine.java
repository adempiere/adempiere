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
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for HR_PaySelectionLine
 *  @author Adempiere (generated) 
 *  @version Release 3.5.4a - $Id$ */
public class X_HR_PaySelectionLine extends PO implements I_HR_PaySelectionLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20091211L;

    /** Standard Constructor */
    public X_HR_PaySelectionLine (Properties ctx, int HR_PaySelectionLine_ID, String trxName)
    {
      super (ctx, HR_PaySelectionLine_ID, trxName);
      /** if (HR_PaySelectionLine_ID == 0)
        {
			setDifferenceAmt (Env.ZERO);
			setDiscountAmt (Env.ZERO);
			setHR_PaySelectionLine_ID (0);
			setHR_PaySelection_ID (0);
			setIsManual (false);
			setIsSOTrx (false);
			setLine (0);
// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM C_PaySelectionLine WHERE C_PaySelection_ID=@C_PaySelection_ID@
			setOpenAmt (Env.ZERO);
			setPayAmt (Env.ZERO);
			setPaymentRule (null);
// S
			setProcessed (false);
// N
        } */
    }

    /** Load Constructor */
    public X_HR_PaySelectionLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_PaySelectionLine[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Difference.
		@param DifferenceAmt 
		Difference Amount
	  */
	public void setDifferenceAmt (BigDecimal DifferenceAmt)
	{
		set_ValueNoCheck (COLUMNNAME_DifferenceAmt, DifferenceAmt);
	}

	/** Get Difference.
		@return Difference Amount
	  */
	public BigDecimal getDifferenceAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DifferenceAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Discount Amount.
		@param DiscountAmt 
		Calculated amount of discount
	  */
	public void setDiscountAmt (BigDecimal DiscountAmt)
	{
		set_ValueNoCheck (COLUMNNAME_DiscountAmt, DiscountAmt);
	}

	/** Get Discount Amount.
		@return Calculated amount of discount
	  */
	public BigDecimal getDiscountAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DiscountAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.eevolution.model.I_HR_Movement getHR_Movement() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Movement)MTable.get(getCtx(), org.eevolution.model.I_HR_Movement.Table_Name)
			.getPO(getHR_Movement_ID(), get_TrxName());	}

	/** Set Payroll Movement.
		@param HR_Movement_ID Payroll Movement	  */
	public void setHR_Movement_ID (int HR_Movement_ID)
	{
		if (HR_Movement_ID < 1) 
			set_Value (COLUMNNAME_HR_Movement_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Movement_ID, Integer.valueOf(HR_Movement_ID));
	}

	/** Get Payroll Movement.
		@return Payroll Movement	  */
	public int getHR_Movement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Movement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_PaySelectionCheck getHR_PaySelectionCheck() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_PaySelectionCheck)MTable.get(getCtx(), org.eevolution.model.I_HR_PaySelectionCheck.Table_Name)
			.getPO(getHR_PaySelectionCheck_ID(), get_TrxName());	}

	/** Set Payroll Pay Selection Check ID.
		@param HR_PaySelectionCheck_ID 
		Payroll Payment Selection Check
	  */
	public void setHR_PaySelectionCheck_ID (int HR_PaySelectionCheck_ID)
	{
		if (HR_PaySelectionCheck_ID < 1) 
			set_Value (COLUMNNAME_HR_PaySelectionCheck_ID, null);
		else 
			set_Value (COLUMNNAME_HR_PaySelectionCheck_ID, Integer.valueOf(HR_PaySelectionCheck_ID));
	}

	/** Get Payroll Pay Selection Check ID.
		@return Payroll Payment Selection Check
	  */
	public int getHR_PaySelectionCheck_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_PaySelectionCheck_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payroll Payment Selection Line ID.
		@param HR_PaySelectionLine_ID 
		Payroll Payment Selection Line
	  */
	public void setHR_PaySelectionLine_ID (int HR_PaySelectionLine_ID)
	{
		if (HR_PaySelectionLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_PaySelectionLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_PaySelectionLine_ID, Integer.valueOf(HR_PaySelectionLine_ID));
	}

	/** Get Payroll Payment Selection Line ID.
		@return Payroll Payment Selection Line
	  */
	public int getHR_PaySelectionLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_PaySelectionLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_PaySelection getHR_PaySelection() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_PaySelection)MTable.get(getCtx(), org.eevolution.model.I_HR_PaySelection.Table_Name)
			.getPO(getHR_PaySelection_ID(), get_TrxName());	}

	/** Set Payroll Payment Selection ID.
		@param HR_PaySelection_ID 
		Payroll Payment Selection
	  */
	public void setHR_PaySelection_ID (int HR_PaySelection_ID)
	{
		if (HR_PaySelection_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_PaySelection_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_PaySelection_ID, Integer.valueOf(HR_PaySelection_ID));
	}

	/** Get Payroll Payment Selection ID.
		@return Payroll Payment Selection
	  */
	public int getHR_PaySelection_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_PaySelection_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Manual.
		@param IsManual 
		This is a manual process
	  */
	public void setIsManual (boolean IsManual)
	{
		set_Value (COLUMNNAME_IsManual, Boolean.valueOf(IsManual));
	}

	/** Get Manual.
		@return This is a manual process
	  */
	public boolean isManual () 
	{
		Object oo = get_Value(COLUMNNAME_IsManual);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sales Transaction.
		@param IsSOTrx 
		This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx)
	{
		set_Value (COLUMNNAME_IsSOTrx, Boolean.valueOf(IsSOTrx));
	}

	/** Get Sales Transaction.
		@return This is a Sales Transaction
	  */
	public boolean isSOTrx () 
	{
		Object oo = get_Value(COLUMNNAME_IsSOTrx);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Open Amount.
		@param OpenAmt 
		Open item amount
	  */
	public void setOpenAmt (BigDecimal OpenAmt)
	{
		set_ValueNoCheck (COLUMNNAME_OpenAmt, OpenAmt);
	}

	/** Get Open Amount.
		@return Open item amount
	  */
	public BigDecimal getOpenAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OpenAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Payment amount.
		@param PayAmt 
		Amount being paid
	  */
	public void setPayAmt (BigDecimal PayAmt)
	{
		set_Value (COLUMNNAME_PayAmt, PayAmt);
	}

	/** Get Payment amount.
		@return Amount being paid
	  */
	public BigDecimal getPayAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PayAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** PaymentRule AD_Reference_ID=195 */
	public static final int PAYMENTRULE_AD_Reference_ID=195;
	/** Cash = B */
	public static final String PAYMENTRULE_Cash = "B";
	/** Credit Card = K */
	public static final String PAYMENTRULE_CreditCard = "K";
	/** Direct Deposit = T */
	public static final String PAYMENTRULE_DirectDeposit = "T";
	/** Check = S */
	public static final String PAYMENTRULE_Check = "S";
	/** On Credit = P */
	public static final String PAYMENTRULE_OnCredit = "P";
	/** Direct Debit = D */
	public static final String PAYMENTRULE_DirectDebit = "D";
	/** Mixed = M */
	public static final String PAYMENTRULE_Mixed = "M";
	/** Set Payment Rule.
		@param PaymentRule 
		How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule)
	{

		set_Value (COLUMNNAME_PaymentRule, PaymentRule);
	}

	/** Get Payment Rule.
		@return How you pay the invoice
	  */
	public String getPaymentRule () 
	{
		return (String)get_Value(COLUMNNAME_PaymentRule);
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
}