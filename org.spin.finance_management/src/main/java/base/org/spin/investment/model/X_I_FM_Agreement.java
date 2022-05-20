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
package org.spin.investment.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for I_FM_Agreement
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_I_FM_Agreement extends PO implements I_I_FM_Agreement, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220507L;

    /** Standard Constructor */
    public X_I_FM_Agreement (Properties ctx, int I_FM_Agreement_ID, String trxName)
    {
      super (ctx, I_FM_Agreement_ID, trxName);
      /** if (I_FM_Agreement_ID == 0)
        {
			setI_FM_Agreement_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_FM_Agreement (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_FM_Agreement[")
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

	/** Set Agreement Type Value.
		@param AgreementTypeValue Agreement Type Value	  */
	public void setAgreementTypeValue (String AgreementTypeValue)
	{
		set_Value (COLUMNNAME_AgreementTypeValue, AgreementTypeValue);
	}

	/** Get Agreement Type Value.
		@return Agreement Type Value	  */
	public String getAgreementTypeValue () 
	{
		return (String)get_Value(COLUMNNAME_AgreementTypeValue);
	}

	/** Set Business Partner Key.
		@param BPartnerValue 
		Key of the Business Partner
	  */
	public void setBPartnerValue (String BPartnerValue)
	{
		set_Value (COLUMNNAME_BPartnerValue, BPartnerValue);
	}

	/** Get Business Partner Key.
		@return Key of the Business Partner
	  */
	public String getBPartnerValue () 
	{
		return (String)get_Value(COLUMNNAME_BPartnerValue);
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

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
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

	/** Set Document Type Name.
		@param DocTypeName 
		Name of the Document Type
	  */
	public void setDocTypeName (String DocTypeName)
	{
		set_Value (COLUMNNAME_DocTypeName, DocTypeName);
	}

	/** Get Document Type Name.
		@return Name of the Document Type
	  */
	public String getDocTypeName () 
	{
		return (String)get_Value(COLUMNNAME_DocTypeName);
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

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
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

	/** Set Financial Product Value.
		@param FinancialProductValue 
		Used for Import Loan Movements
	  */
	public void setFinancialProductValue (String FinancialProductValue)
	{
		set_Value (COLUMNNAME_FinancialProductValue, FinancialProductValue);
	}

	/** Get Financial Product Value.
		@return Used for Import Loan Movements
	  */
	public String getFinancialProductValue () 
	{
		return (String)get_Value(COLUMNNAME_FinancialProductValue);
	}

	public org.spin.investment.model.I_FM_Account getFM_Account() throws RuntimeException
    {
		return (org.spin.investment.model.I_FM_Account)MTable.get(getCtx(), org.spin.investment.model.I_FM_Account.Table_Name)
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

	public org.spin.investment.model.I_FM_Agreement getFM_Agreement() throws RuntimeException
    {
		return (org.spin.investment.model.I_FM_Agreement)MTable.get(getCtx(), org.spin.investment.model.I_FM_Agreement.Table_Name)
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

	public org.spin.investment.model.I_FM_AgreementType getFM_AgreementType() throws RuntimeException
    {
		return (org.spin.investment.model.I_FM_AgreementType)MTable.get(getCtx(), org.spin.investment.model.I_FM_AgreementType.Table_Name)
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

	public org.spin.investment.model.I_FM_Amortization getFM_Amortization() throws RuntimeException
    {
		return (org.spin.investment.model.I_FM_Amortization)MTable.get(getCtx(), org.spin.investment.model.I_FM_Amortization.Table_Name)
			.getPO(getFM_Amortization_ID(), get_TrxName());	}

	/** Set Loan Amortization.
		@param FM_Amortization_ID Loan Amortization	  */
	public void setFM_Amortization_ID (int FM_Amortization_ID)
	{
		if (FM_Amortization_ID < 1) 
			set_Value (COLUMNNAME_FM_Amortization_ID, null);
		else 
			set_Value (COLUMNNAME_FM_Amortization_ID, Integer.valueOf(FM_Amortization_ID));
	}

	/** Get Loan Amortization.
		@return Loan Amortization	  */
	public int getFM_Amortization_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Amortization_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.spin.investment.model.I_FM_Product getFM_Product() throws RuntimeException
    {
		return (org.spin.investment.model.I_FM_Product)MTable.get(getCtx(), org.spin.investment.model.I_FM_Product.Table_Name)
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

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Import for Agreement ID.
		@param I_FM_Agreement_ID Import for Agreement ID	  */
	public void setI_FM_Agreement_ID (int I_FM_Agreement_ID)
	{
		if (I_FM_Agreement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_FM_Agreement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_FM_Agreement_ID, Integer.valueOf(I_FM_Agreement_ID));
	}

	/** Get Import for Agreement ID.
		@return Import for Agreement ID	  */
	public int getI_FM_Agreement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_FM_Agreement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** I_IsImported AD_Reference_ID=319 */
	public static final int I_ISIMPORTED_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String I_ISIMPORTED_Yes = "Y";
	/** No = N */
	public static final String I_ISIMPORTED_No = "N";
	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (String I_IsImported)
	{

		set_Value (COLUMNNAME_I_IsImported, I_IsImported);
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public String getI_IsImported () 
	{
		return (String)get_Value(COLUMNNAME_I_IsImported);
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

	/** Set ISO Currency Code.
		@param ISO_Code 
		Three letter ISO 4217 Code of the Currency
	  */
	public void setISO_Code (String ISO_Code)
	{
		set_Value (COLUMNNAME_ISO_Code, ISO_Code);
	}

	/** Get ISO Currency Code.
		@return Three letter ISO 4217 Code of the Currency
	  */
	public String getISO_Code () 
	{
		return (String)get_Value(COLUMNNAME_ISO_Code);
	}

	/** IsPaid AD_Reference_ID=319 */
	public static final int ISPAID_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISPAID_Yes = "Y";
	/** No = N */
	public static final String ISPAID_No = "N";
	/** Set Paid.
		@param IsPaid 
		The document is paid
	  */
	public void setIsPaid (String IsPaid)
	{

		set_Value (COLUMNNAME_IsPaid, IsPaid);
	}

	/** Get Paid.
		@return The document is paid
	  */
	public String getIsPaid () 
	{
		return (String)get_Value(COLUMNNAME_IsPaid);
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

	/** Set Period No.
		@param PeriodNo 
		Unique Period Number
	  */
	public void setPeriodNo (int PeriodNo)
	{
		set_Value (COLUMNNAME_PeriodNo, Integer.valueOf(PeriodNo));
	}

	/** Get Period No.
		@return Unique Period Number
	  */
	public int getPeriodNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PeriodNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Text Message.
		@param Text 
		This field allows define a text message with a text very long
	  */
	public void setText (String Text)
	{
		set_Value (COLUMNNAME_Text, Text);
	}

	/** Get Text Message.
		@return This field allows define a text message with a text very long
	  */
	public String getText () 
	{
		return (String)get_Value(COLUMNNAME_Text);
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

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo 
		Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}
}