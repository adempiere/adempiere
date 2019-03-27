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

/** Generated Model for T_CombinedAging
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_T_CombinedAging extends PO implements I_T_CombinedAging, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_T_CombinedAging (Properties ctx, int T_CombinedAging_ID, String trxName)
    {
      super (ctx, T_CombinedAging_ID, trxName);
      /** if (T_CombinedAging_ID == 0)
        {
			setAD_PInstance_ID (0);
			setC_BP_Group_ID (0);
			setC_BPartner_ID (0);
			setC_Currency_ID (0);
			setDue0 (Env.ZERO);
			setDue0_30 (Env.ZERO);
			setDue0_7 (Env.ZERO);
			setDue1_7 (Env.ZERO);
			setDue31_60 (Env.ZERO);
			setDue31_Plus (Env.ZERO);
			setDue61_90 (Env.ZERO);
			setDue61_Plus (Env.ZERO);
			setDue8_30 (Env.ZERO);
			setDue91_Plus (Env.ZERO);
			setDueAmt (Env.ZERO);
			setDueDate (new Timestamp( System.currentTimeMillis() ));
			setIsSOTrx (false);
			setListSources (false);
			setOpenAmt (Env.ZERO);
			setPastDue1_30 (Env.ZERO);
			setPastDue1_7 (Env.ZERO);
			setPastDue31_60 (Env.ZERO);
			setPastDue31_Plus (Env.ZERO);
			setPastDue61_90 (Env.ZERO);
			setPastDue61_Plus (Env.ZERO);
			setPastDue8_30 (Env.ZERO);
			setPastDue91_Plus (Env.ZERO);
			setPastDueAmt (Env.ZERO);
			setStatementDate (new Timestamp( System.currentTimeMillis() ));
			setTrxAmt (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_T_CombinedAging (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_T_CombinedAging[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_PInstance getAD_PInstance() throws RuntimeException
    {
		return (org.compiere.model.I_AD_PInstance)MTable.get(getCtx(), org.compiere.model.I_AD_PInstance.Table_Name)
			.getPO(getAD_PInstance_ID(), get_TrxName());	}

	/** Set Process Instance.
		@param AD_PInstance_ID 
		Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID)
	{
		if (AD_PInstance_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_PInstance_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_PInstance_ID, Integer.valueOf(AD_PInstance_ID));
	}

	/** Get Process Instance.
		@return Instance of the process
	  */
	public int getAD_PInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getAccount() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getAccount_ID(), get_TrxName());	}

	/** Set Account.
		@param Account_ID 
		Account used
	  */
	public void setAccount_ID (int Account_ID)
	{
		throw new IllegalArgumentException ("Account_ID is virtual column");	}

	/** Get Account.
		@return Account used
	  */
	public int getAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Account_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Open Posted Amount.
		@param AmtAcctOpenPosted Open Posted Amount	  */
	public void setAmtAcctOpenPosted (BigDecimal AmtAcctOpenPosted)
	{
		throw new IllegalArgumentException ("AmtAcctOpenPosted is virtual column");	}

	/** Get Open Posted Amount.
		@return Open Posted Amount	  */
	public BigDecimal getAmtAcctOpenPosted () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtAcctOpenPosted);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Open Source Amount.
		@param AmtAcctOpenSource Open Source Amount	  */
	public void setAmtAcctOpenSource (BigDecimal AmtAcctOpenSource)
	{
		throw new IllegalArgumentException ("AmtAcctOpenSource is virtual column");	}

	/** Get Open Source Amount.
		@return Open Source Amount	  */
	public BigDecimal getAmtAcctOpenSource () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtAcctOpenSource);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Revalue Diff.
		@param AmtRevalDiff 
		Revaluation difference
	  */
	public void setAmtRevalDiff (BigDecimal AmtRevalDiff)
	{
		throw new IllegalArgumentException ("AmtRevalDiff is virtual column");	}

	/** Get Revalue Diff.
		@return Revaluation difference
	  */
	public BigDecimal getAmtRevalDiff () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtRevalDiff);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException
    {
		return (org.compiere.model.I_C_Activity)MTable.get(getCtx(), org.compiere.model.I_C_Activity.Table_Name)
			.getPO(getC_Activity_ID(), get_TrxName());	}

	/** Set Activity.
		@param C_Activity_ID 
		Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID)
	{
		if (C_Activity_ID < 1) 
			set_Value (COLUMNNAME_C_Activity_ID, null);
		else 
			set_Value (COLUMNNAME_C_Activity_ID, Integer.valueOf(C_Activity_ID));
	}

	/** Get Activity.
		@return Business Activity
	  */
	public int getC_Activity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Activity_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BP_Group getC_BP_Group() throws RuntimeException
    {
		return (org.compiere.model.I_C_BP_Group)MTable.get(getCtx(), org.compiere.model.I_C_BP_Group.Table_Name)
			.getPO(getC_BP_Group_ID(), get_TrxName());	}

	/** Set Business Partner Group.
		@param C_BP_Group_ID 
		Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID)
	{
		if (C_BP_Group_ID < 1) 
			set_Value (COLUMNNAME_C_BP_Group_ID, null);
		else 
			set_Value (COLUMNNAME_C_BP_Group_ID, Integer.valueOf(C_BP_Group_ID));
	}

	/** Get Business Partner Group.
		@return Business Partner Group
	  */
	public int getC_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	public org.compiere.model.I_C_Campaign getC_Campaign() throws RuntimeException
    {
		return (org.compiere.model.I_C_Campaign)MTable.get(getCtx(), org.compiere.model.I_C_Campaign.Table_Name)
			.getPO(getC_Campaign_ID(), get_TrxName());	}

	/** Set Campaign.
		@param C_Campaign_ID 
		Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID)
	{
		if (C_Campaign_ID < 1) 
			set_Value (COLUMNNAME_C_Campaign_ID, null);
		else 
			set_Value (COLUMNNAME_C_Campaign_ID, Integer.valueOf(C_Campaign_ID));
	}

	/** Get Campaign.
		@return Marketing Campaign
	  */
	public int getC_Campaign_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Campaign_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException
    {
		return (org.compiere.model.I_C_Currency)MTable.get(getCtx(), org.compiere.model.I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Currency_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_InvoicePaySchedule getC_InvoicePaySchedule() throws RuntimeException
    {
		return (org.compiere.model.I_C_InvoicePaySchedule)MTable.get(getCtx(), org.compiere.model.I_C_InvoicePaySchedule.Table_Name)
			.getPO(getC_InvoicePaySchedule_ID(), get_TrxName());	}

	/** Set Invoice Payment Schedule.
		@param C_InvoicePaySchedule_ID 
		Invoice Payment Schedule
	  */
	public void setC_InvoicePaySchedule_ID (int C_InvoicePaySchedule_ID)
	{
		if (C_InvoicePaySchedule_ID < 1) 
			set_Value (COLUMNNAME_C_InvoicePaySchedule_ID, null);
		else 
			set_Value (COLUMNNAME_C_InvoicePaySchedule_ID, Integer.valueOf(C_InvoicePaySchedule_ID));
	}

	/** Get Invoice Payment Schedule.
		@return Invoice Payment Schedule
	  */
	public int getC_InvoicePaySchedule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_InvoicePaySchedule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getC_Invoice_ID(), get_TrxName());	}

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
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

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getC_Payment_ID(), get_TrxName());	}

	/** Set Payment.
		@param C_Payment_ID 
		Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID)
	{
		if (C_Payment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Payment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Payment_ID, Integer.valueOf(C_Payment_ID));
	}

	/** Get Payment.
		@return Payment identifier
	  */
	public int getC_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException
    {
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_Name)
			.getPO(getC_Project_ID(), get_TrxName());	}

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_Value (COLUMNNAME_C_Project_ID, null);
		else 
			set_Value (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Days due.
		@param DaysDue 
		Number of days due (negative: due in number of days)
	  */
	public void setDaysDue (int DaysDue)
	{
		set_Value (COLUMNNAME_DaysDue, Integer.valueOf(DaysDue));
	}

	/** Get Days due.
		@return Number of days due (negative: due in number of days)
	  */
	public int getDaysDue () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DaysDue);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Due Today.
		@param Due0 Due Today	  */
	public void setDue0 (BigDecimal Due0)
	{
		set_Value (COLUMNNAME_Due0, Due0);
	}

	/** Get Due Today.
		@return Due Today	  */
	public BigDecimal getDue0 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due0);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due Today-30.
		@param Due0_30 Due Today-30	  */
	public void setDue0_30 (BigDecimal Due0_30)
	{
		set_Value (COLUMNNAME_Due0_30, Due0_30);
	}

	/** Get Due Today-30.
		@return Due Today-30	  */
	public BigDecimal getDue0_30 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due0_30);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due Today-7.
		@param Due0_7 Due Today-7	  */
	public void setDue0_7 (BigDecimal Due0_7)
	{
		set_Value (COLUMNNAME_Due0_7, Due0_7);
	}

	/** Get Due Today-7.
		@return Due Today-7	  */
	public BigDecimal getDue0_7 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due0_7);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 1-7.
		@param Due1_7 Due 1-7	  */
	public void setDue1_7 (BigDecimal Due1_7)
	{
		set_Value (COLUMNNAME_Due1_7, Due1_7);
	}

	/** Get Due 1-7.
		@return Due 1-7	  */
	public BigDecimal getDue1_7 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due1_7);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 31-60.
		@param Due31_60 Due 31-60	  */
	public void setDue31_60 (BigDecimal Due31_60)
	{
		set_Value (COLUMNNAME_Due31_60, Due31_60);
	}

	/** Get Due 31-60.
		@return Due 31-60	  */
	public BigDecimal getDue31_60 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due31_60);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due > 31.
		@param Due31_Plus Due > 31	  */
	public void setDue31_Plus (BigDecimal Due31_Plus)
	{
		set_Value (COLUMNNAME_Due31_Plus, Due31_Plus);
	}

	/** Get Due > 31.
		@return Due > 31	  */
	public BigDecimal getDue31_Plus () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due31_Plus);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 61-90.
		@param Due61_90 Due 61-90	  */
	public void setDue61_90 (BigDecimal Due61_90)
	{
		set_Value (COLUMNNAME_Due61_90, Due61_90);
	}

	/** Get Due 61-90.
		@return Due 61-90	  */
	public BigDecimal getDue61_90 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due61_90);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due > 61.
		@param Due61_Plus Due > 61	  */
	public void setDue61_Plus (BigDecimal Due61_Plus)
	{
		set_Value (COLUMNNAME_Due61_Plus, Due61_Plus);
	}

	/** Get Due > 61.
		@return Due > 61	  */
	public BigDecimal getDue61_Plus () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due61_Plus);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 8-30.
		@param Due8_30 Due 8-30	  */
	public void setDue8_30 (BigDecimal Due8_30)
	{
		set_Value (COLUMNNAME_Due8_30, Due8_30);
	}

	/** Get Due 8-30.
		@return Due 8-30	  */
	public BigDecimal getDue8_30 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due8_30);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due > 91.
		@param Due91_Plus Due > 91	  */
	public void setDue91_Plus (BigDecimal Due91_Plus)
	{
		set_Value (COLUMNNAME_Due91_Plus, Due91_Plus);
	}

	/** Get Due > 91.
		@return Due > 91	  */
	public BigDecimal getDue91_Plus () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due91_Plus);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount due.
		@param DueAmt 
		Amount of the payment due
	  */
	public void setDueAmt (BigDecimal DueAmt)
	{
		set_Value (COLUMNNAME_DueAmt, DueAmt);
	}

	/** Get Amount due.
		@return Amount of the payment due
	  */
	public BigDecimal getDueAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DueAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due Date.
		@param DueDate 
		Date when the payment is due
	  */
	public void setDueDate (Timestamp DueDate)
	{
		set_Value (COLUMNNAME_DueDate, DueDate);
	}

	/** Get Due Date.
		@return Date when the payment is due
	  */
	public Timestamp getDueDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DueDate);
	}

	/** Set Include Payments.
		@param IsIncludePayments 
		Include payments in the aging report
	  */
	public void setIsIncludePayments (boolean IsIncludePayments)
	{
		set_Value (COLUMNNAME_IsIncludePayments, Boolean.valueOf(IsIncludePayments));
	}

	/** Get Include Payments.
		@return Include payments in the aging report
	  */
	public boolean isIncludePayments () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludePayments);
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

	/** Set List Sources.
		@param ListSources 
		List Report Line Sources
	  */
	public void setListSources (boolean ListSources)
	{
		set_Value (COLUMNNAME_ListSources, Boolean.valueOf(ListSources));
	}

	/** Get List Sources.
		@return List Report Line Sources
	  */
	public boolean isListSources () 
	{
		Object oo = get_Value(COLUMNNAME_ListSources);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Open Amount.
		@param OpenAmt 
		Open item amount
	  */
	public void setOpenAmt (BigDecimal OpenAmt)
	{
		set_Value (COLUMNNAME_OpenAmt, OpenAmt);
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

	/** Set Past Due 1-30.
		@param PastDue1_30 Past Due 1-30	  */
	public void setPastDue1_30 (BigDecimal PastDue1_30)
	{
		set_Value (COLUMNNAME_PastDue1_30, PastDue1_30);
	}

	/** Get Past Due 1-30.
		@return Past Due 1-30	  */
	public BigDecimal getPastDue1_30 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PastDue1_30);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Past Due 1-7.
		@param PastDue1_7 Past Due 1-7	  */
	public void setPastDue1_7 (BigDecimal PastDue1_7)
	{
		set_Value (COLUMNNAME_PastDue1_7, PastDue1_7);
	}

	/** Get Past Due 1-7.
		@return Past Due 1-7	  */
	public BigDecimal getPastDue1_7 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PastDue1_7);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Past Due 31-60.
		@param PastDue31_60 Past Due 31-60	  */
	public void setPastDue31_60 (BigDecimal PastDue31_60)
	{
		set_Value (COLUMNNAME_PastDue31_60, PastDue31_60);
	}

	/** Get Past Due 31-60.
		@return Past Due 31-60	  */
	public BigDecimal getPastDue31_60 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PastDue31_60);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Past Due > 31.
		@param PastDue31_Plus Past Due > 31	  */
	public void setPastDue31_Plus (BigDecimal PastDue31_Plus)
	{
		set_Value (COLUMNNAME_PastDue31_Plus, PastDue31_Plus);
	}

	/** Get Past Due > 31.
		@return Past Due > 31	  */
	public BigDecimal getPastDue31_Plus () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PastDue31_Plus);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Past Due 61-90.
		@param PastDue61_90 Past Due 61-90	  */
	public void setPastDue61_90 (BigDecimal PastDue61_90)
	{
		set_Value (COLUMNNAME_PastDue61_90, PastDue61_90);
	}

	/** Get Past Due 61-90.
		@return Past Due 61-90	  */
	public BigDecimal getPastDue61_90 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PastDue61_90);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Past Due > 61.
		@param PastDue61_Plus Past Due > 61	  */
	public void setPastDue61_Plus (BigDecimal PastDue61_Plus)
	{
		set_Value (COLUMNNAME_PastDue61_Plus, PastDue61_Plus);
	}

	/** Get Past Due > 61.
		@return Past Due > 61	  */
	public BigDecimal getPastDue61_Plus () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PastDue61_Plus);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Past Due 8-30.
		@param PastDue8_30 Past Due 8-30	  */
	public void setPastDue8_30 (BigDecimal PastDue8_30)
	{
		set_Value (COLUMNNAME_PastDue8_30, PastDue8_30);
	}

	/** Get Past Due 8-30.
		@return Past Due 8-30	  */
	public BigDecimal getPastDue8_30 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PastDue8_30);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Past Due > 91.
		@param PastDue91_Plus Past Due > 91	  */
	public void setPastDue91_Plus (BigDecimal PastDue91_Plus)
	{
		set_Value (COLUMNNAME_PastDue91_Plus, PastDue91_Plus);
	}

	/** Get Past Due > 91.
		@return Past Due > 91	  */
	public BigDecimal getPastDue91_Plus () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PastDue91_Plus);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Past Due.
		@param PastDueAmt Past Due	  */
	public void setPastDueAmt (BigDecimal PastDueAmt)
	{
		set_Value (COLUMNNAME_PastDueAmt, PastDueAmt);
	}

	/** Get Past Due.
		@return Past Due	  */
	public BigDecimal getPastDueAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PastDueAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Statement date.
		@param StatementDate 
		Date of the statement
	  */
	public void setStatementDate (Timestamp StatementDate)
	{
		set_Value (COLUMNNAME_StatementDate, StatementDate);
	}

	/** Get Statement date.
		@return Date of the statement
	  */
	public Timestamp getStatementDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StatementDate);
	}

	/** Set Transaction Amount.
		@param TrxAmt 
		Amount of a transaction
	  */
	public void setTrxAmt (BigDecimal TrxAmt)
	{
		set_Value (COLUMNNAME_TrxAmt, TrxAmt);
	}

	/** Get Transaction Amount.
		@return Amount of a transaction
	  */
	public BigDecimal getTrxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TrxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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