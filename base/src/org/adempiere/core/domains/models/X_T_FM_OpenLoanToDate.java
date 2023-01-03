/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;

/** Generated Model for T_FM_OpenLoanToDate
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4 - $Id$ */
public class X_T_FM_OpenLoanToDate extends PO implements I_T_FM_OpenLoanToDate, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230102L;

    /** Standard Constructor */
    public X_T_FM_OpenLoanToDate (Properties ctx, int T_FM_OpenLoanToDate_ID, String trxName)
    {
      super (ctx, T_FM_OpenLoanToDate_ID, trxName);
      /** if (T_FM_OpenLoanToDate_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_T_FM_OpenLoanToDate (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_T_FM_OpenLoanToDate[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Account No.
		@param AccountNo 
		Account Number
	  */
	public void setAccountNo (String AccountNo)
	{
		set_Value (COLUMNNAME_AccountNo, AccountNo);
	}

	/** Get Account No.
		@return Account Number
	  */
	public String getAccountNo () 
	{
		return (String)get_Value(COLUMNNAME_AccountNo);
	}

	public org.adempiere.core.domains.models.I_AD_PInstance getAD_PInstance() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_PInstance)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_PInstance.Table_Name)
			.getPO(getAD_PInstance_ID(), get_TrxName());	}

	/** Set Process Instance.
		@param AD_PInstance_ID 
		Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID)
	{
		if (AD_PInstance_ID < 1) 
			set_Value (COLUMNNAME_AD_PInstance_ID, null);
		else 
			set_Value (COLUMNNAME_AD_PInstance_ID, Integer.valueOf(AD_PInstance_ID));
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

	public org.adempiere.core.domains.models.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_C_BPartner)MTable.get(getCtx(), org.adempiere.core.domains.models.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

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

	public org.adempiere.core.domains.models.I_C_Currency getC_Currency() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_C_Currency)MTable.get(getCtx(), org.adempiere.core.domains.models.I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
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

	public org.adempiere.core.domains.models.I_C_DocType getC_DocType() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_C_DocType)MTable.get(getCtx(), org.adempiere.core.domains.models.I_C_DocType.Table_Name)
			.getPO(getC_DocType_ID(), get_TrxName());	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_Value (COLUMNNAME_C_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_C_Invoice getC_Invoice() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_C_Invoice)MTable.get(getCtx(), org.adempiere.core.domains.models.I_C_Invoice.Table_Name)
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

	/** Set Capital Amount.
		@param CapitalAmt Capital Amount	  */
	public void setCapitalAmt (BigDecimal CapitalAmt)
	{
		set_Value (COLUMNNAME_CapitalAmt, CapitalAmt);
	}

	/** Get Capital Amount.
		@return Capital Amount	  */
	public BigDecimal getCapitalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CapitalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Current Capital Amount.
		@param CurrentCapitalAmt Current Capital Amount	  */
	public void setCurrentCapitalAmt (BigDecimal CurrentCapitalAmt)
	{
		set_Value (COLUMNNAME_CurrentCapitalAmt, CurrentCapitalAmt);
	}

	/** Get Current Capital Amount.
		@return Current Capital Amount	  */
	public BigDecimal getCurrentCapitalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CurrentCapitalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Current Due Fee Amount.
		@param CurrentDueFeeAmt Current Due Fee Amount	  */
	public void setCurrentDueFeeAmt (BigDecimal CurrentDueFeeAmt)
	{
		set_Value (COLUMNNAME_CurrentDueFeeAmt, CurrentDueFeeAmt);
	}

	/** Get Current Due Fee Amount.
		@return Current Due Fee Amount	  */
	public BigDecimal getCurrentDueFeeAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CurrentDueFeeAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Current Dunning Amount.
		@param CurrentDunningAmt Current Dunning Amount	  */
	public void setCurrentDunningAmt (BigDecimal CurrentDunningAmt)
	{
		set_Value (COLUMNNAME_CurrentDunningAmt, CurrentDunningAmt);
	}

	/** Get Current Dunning Amount.
		@return Current Dunning Amount	  */
	public BigDecimal getCurrentDunningAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CurrentDunningAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Current Dunning Tax Amount.
		@param CurrentDunningTaxAmt Current Dunning Tax Amount	  */
	public void setCurrentDunningTaxAmt (BigDecimal CurrentDunningTaxAmt)
	{
		set_Value (COLUMNNAME_CurrentDunningTaxAmt, CurrentDunningTaxAmt);
	}

	/** Get Current Dunning Tax Amount.
		@return Current Dunning Tax Amount	  */
	public BigDecimal getCurrentDunningTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CurrentDunningTaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Current Fee Amount.
		@param CurrentFeeAmt Current Fee Amount	  */
	public void setCurrentFeeAmt (BigDecimal CurrentFeeAmt)
	{
		set_Value (COLUMNNAME_CurrentFeeAmt, CurrentFeeAmt);
	}

	/** Get Current Fee Amount.
		@return Current Fee Amount	  */
	public BigDecimal getCurrentFeeAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CurrentFeeAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Current Interest Amount.
		@param CurrentInterestAmt Current Interest Amount	  */
	public void setCurrentInterestAmt (BigDecimal CurrentInterestAmt)
	{
		set_Value (COLUMNNAME_CurrentInterestAmt, CurrentInterestAmt);
	}

	/** Get Current Interest Amount.
		@return Current Interest Amount	  */
	public BigDecimal getCurrentInterestAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CurrentInterestAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Current Tax Amount.
		@param CurrentTaxAmt Current Tax Amount	  */
	public void setCurrentTaxAmt (BigDecimal CurrentTaxAmt)
	{
		set_Value (COLUMNNAME_CurrentTaxAmt, CurrentTaxAmt);
	}

	/** Get Current Tax Amount.
		@return Current Tax Amount	  */
	public BigDecimal getCurrentTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CurrentTaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Document Date.
		@param DateDoc 
		Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc)
	{
		set_Value (COLUMNNAME_DateDoc, DateDoc);
	}

	/** Get Document Date.
		@return Date of the Document
	  */
	public Timestamp getDateDoc () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDoc);
	}

	/** Set Date Invoiced.
		@param DateInvoiced 
		Date printed on Invoice
	  */
	public void setDateInvoiced (Timestamp DateInvoiced)
	{
		set_Value (COLUMNNAME_DateInvoiced, DateInvoiced);
	}

	/** Get Date Invoiced.
		@return Date printed on Invoice
	  */
	public Timestamp getDateInvoiced () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateInvoiced);
	}

	/** Set Date To.
		@param DateTo 
		End date of a date range
	  */
	public void setDateTo (Timestamp DateTo)
	{
		set_Value (COLUMNNAME_DateTo, DateTo);
	}

	/** Get Date To.
		@return End date of a date range
	  */
	public Timestamp getDateTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTo);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
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

	/** Set Quantity of Fees (Due).
		@param DueFeesQty 
		Quantity of Fees (Due) for Loan
	  */
	public void setDueFeesQty (BigDecimal DueFeesQty)
	{
		set_Value (COLUMNNAME_DueFeesQty, DueFeesQty);
	}

	/** Get Quantity of Fees (Due).
		@return Quantity of Fees (Due) for Loan
	  */
	public BigDecimal getDueFeesQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DueFeesQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Fee Amount.
		@param FeeAmt 
		Fee amount in invoice currency
	  */
	public void setFeeAmt (BigDecimal FeeAmt)
	{
		set_Value (COLUMNNAME_FeeAmt, FeeAmt);
	}

	/** Get Fee Amount.
		@return Fee amount in invoice currency
	  */
	public BigDecimal getFeeAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FeeAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity of Fees.
		@param FeesQty 
		Quantity of Fees for Loan
	  */
	public void setFeesQty (BigDecimal FeesQty)
	{
		set_Value (COLUMNNAME_FeesQty, FeesQty);
	}

	/** Get Quantity of Fees.
		@return Quantity of Fees for Loan
	  */
	public BigDecimal getFeesQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FeesQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.adempiere.core.domains.models.I_FM_Account getFM_Account() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Account)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Account.Table_Name)
			.getPO(getFM_Account_ID(), get_TrxName());	}

	/** Set Financial Account.
		@param FM_Account_ID Financial Account	  */
	public void setFM_Account_ID (int FM_Account_ID)
	{
		if (FM_Account_ID < 1) 
			set_Value (COLUMNNAME_FM_Account_ID, null);
		else 
			set_Value (COLUMNNAME_FM_Account_ID, Integer.valueOf(FM_Account_ID));
	}

	/** Get Financial Account.
		@return Financial Account	  */
	public int getFM_Account_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Account_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_FM_Agreement getFM_Agreement() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Agreement)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Agreement.Table_Name)
			.getPO(getFM_Agreement_ID(), get_TrxName());	}

	/** Set Agreement.
		@param FM_Agreement_ID Agreement	  */
	public void setFM_Agreement_ID (int FM_Agreement_ID)
	{
		if (FM_Agreement_ID < 1) 
			set_Value (COLUMNNAME_FM_Agreement_ID, null);
		else 
			set_Value (COLUMNNAME_FM_Agreement_ID, Integer.valueOf(FM_Agreement_ID));
	}

	/** Get Agreement.
		@return Agreement	  */
	public int getFM_Agreement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Agreement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_FM_AgreementType getFM_AgreementType() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_AgreementType)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_AgreementType.Table_Name)
			.getPO(getFM_AgreementType_ID(), get_TrxName());	}

	/** Set Agreement Type.
		@param FM_AgreementType_ID Agreement Type	  */
	public void setFM_AgreementType_ID (int FM_AgreementType_ID)
	{
		if (FM_AgreementType_ID < 1) 
			set_Value (COLUMNNAME_FM_AgreementType_ID, null);
		else 
			set_Value (COLUMNNAME_FM_AgreementType_ID, Integer.valueOf(FM_AgreementType_ID));
	}

	/** Get Agreement Type.
		@return Agreement Type	  */
	public int getFM_AgreementType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_AgreementType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_FM_Product getFM_Product() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Product)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Product.Table_Name)
			.getPO(getFM_Product_ID(), get_TrxName());	}

	/** Set Financial Product.
		@param FM_Product_ID Financial Product	  */
	public void setFM_Product_ID (int FM_Product_ID)
	{
		if (FM_Product_ID < 1) 
			set_Value (COLUMNNAME_FM_Product_ID, null);
		else 
			set_Value (COLUMNNAME_FM_Product_ID, Integer.valueOf(FM_Product_ID));
	}

	/** Get Financial Product.
		@return Financial Product	  */
	public int getFM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Interest Amount.
		@param InterestAmt 
		Interest Amount
	  */
	public void setInterestAmt (BigDecimal InterestAmt)
	{
		set_Value (COLUMNNAME_InterestAmt, InterestAmt);
	}

	/** Get Interest Amount.
		@return Interest Amount
	  */
	public BigDecimal getInterestAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InterestAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Open Fees Quantity.
		@param OpenFeesQty 
		Open Fees Quantity for a Loan
	  */
	public void setOpenFeesQty (BigDecimal OpenFeesQty)
	{
		set_Value (COLUMNNAME_OpenFeesQty, OpenFeesQty);
	}

	/** Get Open Fees Quantity.
		@return Open Fees Quantity for a Loan
	  */
	public BigDecimal getOpenFeesQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OpenFeesQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Paid Fees Quantity.
		@param PaidFeesQty Paid Fees Quantity	  */
	public void setPaidFeesQty (BigDecimal PaidFeesQty)
	{
		set_Value (COLUMNNAME_PaidFeesQty, PaidFeesQty);
	}

	/** Get Paid Fees Quantity.
		@return Paid Fees Quantity	  */
	public BigDecimal getPaidFeesQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PaidFeesQty);
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

	/** Set Payment date.
		@param PayDate 
		Date Payment made
	  */
	public void setPayDate (Timestamp PayDate)
	{
		set_Value (COLUMNNAME_PayDate, PayDate);
	}

	/** Get Payment date.
		@return Date Payment made
	  */
	public Timestamp getPayDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PayDate);
	}

	/** PaymentFrequency AD_Reference_ID=54019 */
	public static final int PAYMENTFREQUENCY_AD_Reference_ID=54019;
	/** Daily = D */
	public static final String PAYMENTFREQUENCY_Daily = "D";
	/** Monthly = M */
	public static final String PAYMENTFREQUENCY_Monthly = "M";
	/** Quarterly = Q */
	public static final String PAYMENTFREQUENCY_Quarterly = "Q";
	/** Semi-yearly = S */
	public static final String PAYMENTFREQUENCY_Semi_Yearly = "S";
	/** Yearly = Y */
	public static final String PAYMENTFREQUENCY_Yearly = "Y";
	/** Twice Monthly = T */
	public static final String PAYMENTFREQUENCY_TwiceMonthly = "T";
	/** Single Fee = F */
	public static final String PAYMENTFREQUENCY_SingleFee = "F";
	/** Weekly = W */
	public static final String PAYMENTFREQUENCY_Weekly = "W";
	/** Set Payment Frequency.
		@param PaymentFrequency 
		Payment Frequency
	  */
	public void setPaymentFrequency (String PaymentFrequency)
	{

		set_Value (COLUMNNAME_PaymentFrequency, PaymentFrequency);
	}

	/** Get Payment Frequency.
		@return Payment Frequency
	  */
	public String getPaymentFrequency () 
	{
		return (String)get_Value(COLUMNNAME_PaymentFrequency);
	}

	/** Status AD_Reference_ID=54012 */
	public static final int STATUS_AD_Reference_ID=54012;
	/** Agreement = A */
	public static final String STATUS_Agreement = "A";
	/** Transaction = T */
	public static final String STATUS_Transaction = "T";
	/** Account = C */
	public static final String STATUS_Account = "C";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		set_Value (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
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