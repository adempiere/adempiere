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
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_PaySelectionLine
 *  @author Adempiere (generated) 
 *  @version Release 3.6.0LTS - $Id$ */
public class X_C_PaySelectionLine extends PO implements I_C_PaySelectionLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20100614L;

    /** Standard Constructor */
    public X_C_PaySelectionLine (Properties ctx, int C_PaySelectionLine_ID, String trxName)
    {
      super (ctx, C_PaySelectionLine_ID, trxName);
      /** if (C_PaySelectionLine_ID == 0)
        {
			setC_Invoice_ID (0);
			setC_PaySelection_ID (0);
			setC_PaySelectionLine_ID (0);
			setDifferenceAmt (Env.ZERO);
			setDiscountAmt (Env.ZERO);
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
    public X_C_PaySelectionLine (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_C_PaySelectionLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Invoice getC_Invoice() throws RuntimeException
    {
		return (I_C_Invoice)MTable.get(getCtx(), I_C_Invoice.Table_Name)
			.getPO(getC_Invoice_ID(), get_TrxName());	}

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_PaySelectionCheck getC_PaySelectionCheck() throws RuntimeException
    {
		return (I_C_PaySelectionCheck)MTable.get(getCtx(), I_C_PaySelectionCheck.Table_Name)
			.getPO(getC_PaySelectionCheck_ID(), get_TrxName());	}

	/** Set Pay Selection Check.
		@param C_PaySelectionCheck_ID 
		Payment Selection Check
	  */
	public void setC_PaySelectionCheck_ID (int C_PaySelectionCheck_ID)
	{
		if (C_PaySelectionCheck_ID < 1) 
			set_Value (COLUMNNAME_C_PaySelectionCheck_ID, null);
		else 
			set_Value (COLUMNNAME_C_PaySelectionCheck_ID, Integer.valueOf(C_PaySelectionCheck_ID));
	}

	/** Get Pay Selection Check.
		@return Payment Selection Check
	  */
	public int getC_PaySelectionCheck_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PaySelectionCheck_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_PaySelection getC_PaySelection() throws RuntimeException
    {
		return (I_C_PaySelection)MTable.get(getCtx(), I_C_PaySelection.Table_Name)
			.getPO(getC_PaySelection_ID(), get_TrxName());	}

	/** Set Payment Selection.
		@param C_PaySelection_ID 
		Payment Selection
	  */
	public void setC_PaySelection_ID (int C_PaySelection_ID)
	{
		if (C_PaySelection_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_PaySelection_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_PaySelection_ID, Integer.valueOf(C_PaySelection_ID));
	}

	/** Get Payment Selection.
		@return Payment Selection
	  */
	public int getC_PaySelection_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PaySelection_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payment Selection Line.
		@param C_PaySelectionLine_ID 
		Payment Selection Line
	  */
	public void setC_PaySelectionLine_ID (int C_PaySelectionLine_ID)
	{
		if (C_PaySelectionLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_PaySelectionLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_PaySelectionLine_ID, Integer.valueOf(C_PaySelectionLine_ID));
	}

	/** Get Payment Selection Line.
		@return Payment Selection Line
	  */
	public int getC_PaySelectionLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PaySelectionLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getC_PaySelectionLine_ID()));
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