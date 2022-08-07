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
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for T_FM_OpenLoanToDate
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3
 */
public interface I_T_FM_OpenLoanToDate 
{

    /** TableName=T_FM_OpenLoanToDate */
    public static final String Table_Name = "T_FM_OpenLoanToDate";

    /** AD_Table_ID=54620 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AccountNo */
    public static final String COLUMNNAME_AccountNo = "AccountNo";

	/** Set Account No.
	  * Account Number
	  */
	public void setAccountNo (String AccountNo);

	/** Get Account No.
	  * Account Number
	  */
	public String getAccountNo();

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AD_PInstance_ID */
    public static final String COLUMNNAME_AD_PInstance_ID = "AD_PInstance_ID";

	/** Set Process Instance.
	  * Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID);

	/** Get Process Instance.
	  * Instance of the process
	  */
	public int getAD_PInstance_ID();

	public org.adempiere.core.domains.models.I_AD_PInstance getAD_PInstance() throws RuntimeException;

    /** Column name CapitalAmt */
    public static final String COLUMNNAME_CapitalAmt = "CapitalAmt";

	/** Set Capital Amount	  */
	public void setCapitalAmt (BigDecimal CapitalAmt);

	/** Get Capital Amount	  */
	public BigDecimal getCapitalAmt();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.adempiere.core.domains.models.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public org.adempiere.core.domains.models.I_C_Currency getC_Currency() throws RuntimeException;

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public org.adempiere.core.domains.models.I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public org.adempiere.core.domains.models.I_C_Invoice getC_Invoice() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name CurrentCapitalAmt */
    public static final String COLUMNNAME_CurrentCapitalAmt = "CurrentCapitalAmt";

	/** Set Current Capital Amount	  */
	public void setCurrentCapitalAmt (BigDecimal CurrentCapitalAmt);

	/** Get Current Capital Amount	  */
	public BigDecimal getCurrentCapitalAmt();

    /** Column name CurrentDueFeeAmt */
    public static final String COLUMNNAME_CurrentDueFeeAmt = "CurrentDueFeeAmt";

	/** Set Current Due Fee Amount	  */
	public void setCurrentDueFeeAmt (BigDecimal CurrentDueFeeAmt);

	/** Get Current Due Fee Amount	  */
	public BigDecimal getCurrentDueFeeAmt();

    /** Column name CurrentDunningAmt */
    public static final String COLUMNNAME_CurrentDunningAmt = "CurrentDunningAmt";

	/** Set Current Dunning Amount	  */
	public void setCurrentDunningAmt (BigDecimal CurrentDunningAmt);

	/** Get Current Dunning Amount	  */
	public BigDecimal getCurrentDunningAmt();

    /** Column name CurrentDunningTaxAmt */
    public static final String COLUMNNAME_CurrentDunningTaxAmt = "CurrentDunningTaxAmt";

	/** Set Current Dunning Tax Amount	  */
	public void setCurrentDunningTaxAmt (BigDecimal CurrentDunningTaxAmt);

	/** Get Current Dunning Tax Amount	  */
	public BigDecimal getCurrentDunningTaxAmt();

    /** Column name CurrentFeeAmt */
    public static final String COLUMNNAME_CurrentFeeAmt = "CurrentFeeAmt";

	/** Set Current Fee Amount	  */
	public void setCurrentFeeAmt (BigDecimal CurrentFeeAmt);

	/** Get Current Fee Amount	  */
	public BigDecimal getCurrentFeeAmt();

    /** Column name CurrentInterestAmt */
    public static final String COLUMNNAME_CurrentInterestAmt = "CurrentInterestAmt";

	/** Set Current Interest Amount	  */
	public void setCurrentInterestAmt (BigDecimal CurrentInterestAmt);

	/** Get Current Interest Amount	  */
	public BigDecimal getCurrentInterestAmt();

    /** Column name CurrentTaxAmt */
    public static final String COLUMNNAME_CurrentTaxAmt = "CurrentTaxAmt";

	/** Set Current Tax Amount	  */
	public void setCurrentTaxAmt (BigDecimal CurrentTaxAmt);

	/** Get Current Tax Amount	  */
	public BigDecimal getCurrentTaxAmt();

    /** Column name DateDoc */
    public static final String COLUMNNAME_DateDoc = "DateDoc";

	/** Set Document Date.
	  * Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc);

	/** Get Document Date.
	  * Date of the Document
	  */
	public Timestamp getDateDoc();

    /** Column name DateInvoiced */
    public static final String COLUMNNAME_DateInvoiced = "DateInvoiced";

	/** Set Date Invoiced.
	  * Date printed on Invoice
	  */
	public void setDateInvoiced (Timestamp DateInvoiced);

	/** Get Date Invoiced.
	  * Date printed on Invoice
	  */
	public Timestamp getDateInvoiced();

    /** Column name DateTo */
    public static final String COLUMNNAME_DateTo = "DateTo";

	/** Set Date To.
	  * End date of a date range
	  */
	public void setDateTo (Timestamp DateTo);

	/** Get Date To.
	  * End date of a date range
	  */
	public Timestamp getDateTo();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name DueDate */
    public static final String COLUMNNAME_DueDate = "DueDate";

	/** Set Due Date.
	  * Date when the payment is due
	  */
	public void setDueDate (Timestamp DueDate);

	/** Get Due Date.
	  * Date when the payment is due
	  */
	public Timestamp getDueDate();

    /** Column name DueFeesQty */
    public static final String COLUMNNAME_DueFeesQty = "DueFeesQty";

	/** Set Quantity of Fees (Due).
	  * Quantity of Fees (Due) for Loan
	  */
	public void setDueFeesQty (BigDecimal DueFeesQty);

	/** Get Quantity of Fees (Due).
	  * Quantity of Fees (Due) for Loan
	  */
	public BigDecimal getDueFeesQty();

    /** Column name FeeAmt */
    public static final String COLUMNNAME_FeeAmt = "FeeAmt";

	/** Set Fee Amount.
	  * Fee amount in invoice currency
	  */
	public void setFeeAmt (BigDecimal FeeAmt);

	/** Get Fee Amount.
	  * Fee amount in invoice currency
	  */
	public BigDecimal getFeeAmt();

    /** Column name FeesQty */
    public static final String COLUMNNAME_FeesQty = "FeesQty";

	/** Set Quantity of Fees.
	  * Quantity of Fees for Loan
	  */
	public void setFeesQty (BigDecimal FeesQty);

	/** Get Quantity of Fees.
	  * Quantity of Fees for Loan
	  */
	public BigDecimal getFeesQty();

    /** Column name FM_Account_ID */
    public static final String COLUMNNAME_FM_Account_ID = "FM_Account_ID";

	/** Set Financial Account	  */
	public void setFM_Account_ID (int FM_Account_ID);

	/** Get Financial Account	  */
	public int getFM_Account_ID();

	public org.adempiere.core.domains.models.I_FM_Account getFM_Account() throws RuntimeException;

    /** Column name FM_Agreement_ID */
    public static final String COLUMNNAME_FM_Agreement_ID = "FM_Agreement_ID";

	/** Set Agreement	  */
	public void setFM_Agreement_ID (int FM_Agreement_ID);

	/** Get Agreement	  */
	public int getFM_Agreement_ID();

	public org.adempiere.core.domains.models.I_FM_Agreement getFM_Agreement() throws RuntimeException;

    /** Column name FM_AgreementType_ID */
    public static final String COLUMNNAME_FM_AgreementType_ID = "FM_AgreementType_ID";

	/** Set Agreement Type	  */
	public void setFM_AgreementType_ID (int FM_AgreementType_ID);

	/** Get Agreement Type	  */
	public int getFM_AgreementType_ID();

	public org.adempiere.core.domains.models.I_FM_AgreementType getFM_AgreementType() throws RuntimeException;

    /** Column name FM_Product_ID */
    public static final String COLUMNNAME_FM_Product_ID = "FM_Product_ID";

	/** Set Financial Product	  */
	public void setFM_Product_ID (int FM_Product_ID);

	/** Get Financial Product	  */
	public int getFM_Product_ID();

	public org.adempiere.core.domains.models.I_FM_Product getFM_Product() throws RuntimeException;

    /** Column name InterestAmt */
    public static final String COLUMNNAME_InterestAmt = "InterestAmt";

	/** Set Interest Amount.
	  * Interest Amount
	  */
	public void setInterestAmt (BigDecimal InterestAmt);

	/** Get Interest Amount.
	  * Interest Amount
	  */
	public BigDecimal getInterestAmt();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsSOTrx */
    public static final String COLUMNNAME_IsSOTrx = "IsSOTrx";

	/** Set Sales Transaction.
	  * This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx);

	/** Get Sales Transaction.
	  * This is a Sales Transaction
	  */
	public boolean isSOTrx();

    /** Column name OpenFeesQty */
    public static final String COLUMNNAME_OpenFeesQty = "OpenFeesQty";

	/** Set Open Fees Quantity.
	  * Open Fees Quantity for a Loan
	  */
	public void setOpenFeesQty (BigDecimal OpenFeesQty);

	/** Get Open Fees Quantity.
	  * Open Fees Quantity for a Loan
	  */
	public BigDecimal getOpenFeesQty();

    /** Column name PaidFeesQty */
    public static final String COLUMNNAME_PaidFeesQty = "PaidFeesQty";

	/** Set Paid Fees Quantity	  */
	public void setPaidFeesQty (BigDecimal PaidFeesQty);

	/** Get Paid Fees Quantity	  */
	public BigDecimal getPaidFeesQty();

    /** Column name PayAmt */
    public static final String COLUMNNAME_PayAmt = "PayAmt";

	/** Set Payment amount.
	  * Amount being paid
	  */
	public void setPayAmt (BigDecimal PayAmt);

	/** Get Payment amount.
	  * Amount being paid
	  */
	public BigDecimal getPayAmt();

    /** Column name PayDate */
    public static final String COLUMNNAME_PayDate = "PayDate";

	/** Set Payment date.
	  * Date Payment made
	  */
	public void setPayDate (Timestamp PayDate);

	/** Get Payment date.
	  * Date Payment made
	  */
	public Timestamp getPayDate();

    /** Column name PaymentFrequency */
    public static final String COLUMNNAME_PaymentFrequency = "PaymentFrequency";

	/** Set Payment Frequency.
	  * Payment Frequency
	  */
	public void setPaymentFrequency (String PaymentFrequency);

	/** Get Payment Frequency.
	  * Payment Frequency
	  */
	public String getPaymentFrequency();

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (String Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public String getStatus();

    /** Column name TaxAmt */
    public static final String COLUMNNAME_TaxAmt = "TaxAmt";

	/** Set Tax Amount.
	  * Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt);

	/** Get Tax Amount.
	  * Tax Amount for a document
	  */
	public BigDecimal getTaxAmt();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();
}
