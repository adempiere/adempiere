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

/** Generated Interface for I_FM_Agreement
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3
 */
public interface I_I_FM_Agreement 
{

    /** TableName=I_FM_Agreement */
    public static final String Table_Name = "I_FM_Agreement";

    /** AD_Table_ID=54409 */
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

    /** Column name AgreementTypeValue */
    public static final String COLUMNNAME_AgreementTypeValue = "AgreementTypeValue";

	/** Set Agreement Type Value	  */
	public void setAgreementTypeValue (String AgreementTypeValue);

	/** Get Agreement Type Value	  */
	public String getAgreementTypeValue();

    /** Column name BPartnerValue */
    public static final String COLUMNNAME_BPartnerValue = "BPartnerValue";

	/** Set Business Partner Key.
	  * Key of the Business Partner
	  */
	public void setBPartnerValue (String BPartnerValue);

	/** Get Business Partner Key.
	  * Key of the Business Partner
	  */
	public String getBPartnerValue();

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

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DocTypeName */
    public static final String COLUMNNAME_DocTypeName = "DocTypeName";

	/** Set Document Type Name.
	  * Name of the Document Type
	  */
	public void setDocTypeName (String DocTypeName);

	/** Get Document Type Name.
	  * Name of the Document Type
	  */
	public String getDocTypeName();

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

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

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

    /** Column name FinancialProductValue */
    public static final String COLUMNNAME_FinancialProductValue = "FinancialProductValue";

	/** Set Financial Product Value.
	  * Used for Import Loan Movements
	  */
	public void setFinancialProductValue (String FinancialProductValue);

	/** Get Financial Product Value.
	  * Used for Import Loan Movements
	  */
	public String getFinancialProductValue();

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

    /** Column name FM_Amortization_ID */
    public static final String COLUMNNAME_FM_Amortization_ID = "FM_Amortization_ID";

	/** Set Loan Amortization	  */
	public void setFM_Amortization_ID (int FM_Amortization_ID);

	/** Get Loan Amortization	  */
	public int getFM_Amortization_ID();

	public org.adempiere.core.domains.models.I_FM_Amortization getFM_Amortization() throws RuntimeException;

    /** Column name FM_Product_ID */
    public static final String COLUMNNAME_FM_Product_ID = "FM_Product_ID";

	/** Set Financial Product	  */
	public void setFM_Product_ID (int FM_Product_ID);

	/** Get Financial Product	  */
	public int getFM_Product_ID();

	public org.adempiere.core.domains.models.I_FM_Product getFM_Product() throws RuntimeException;

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_FM_Agreement_ID */
    public static final String COLUMNNAME_I_FM_Agreement_ID = "I_FM_Agreement_ID";

	/** Set Import for Agreement ID	  */
	public void setI_FM_Agreement_ID (int I_FM_Agreement_ID);

	/** Get Import for Agreement ID	  */
	public int getI_FM_Agreement_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (String I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public String getI_IsImported();

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

    /** Column name ISO_Code */
    public static final String COLUMNNAME_ISO_Code = "ISO_Code";

	/** Set ISO Currency Code.
	  * Three letter ISO 4217 Code of the Currency
	  */
	public void setISO_Code (String ISO_Code);

	/** Get ISO Currency Code.
	  * Three letter ISO 4217 Code of the Currency
	  */
	public String getISO_Code();

    /** Column name IsPaid */
    public static final String COLUMNNAME_IsPaid = "IsPaid";

	/** Set Paid.
	  * The document is paid
	  */
	public void setIsPaid (String IsPaid);

	/** Get Paid.
	  * The document is paid
	  */
	public String getIsPaid();

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

    /** Column name PeriodNo */
    public static final String COLUMNNAME_PeriodNo = "PeriodNo";

	/** Set Period No.
	  * Unique Period Number
	  */
	public void setPeriodNo (int PeriodNo);

	/** Get Period No.
	  * Unique Period Number
	  */
	public int getPeriodNo();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

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

    /** Column name Text */
    public static final String COLUMNNAME_Text = "Text";

	/** Set Text Message.
	  * This field allows define a text message with a text very long
	  */
	public void setText (String Text);

	/** Get Text Message.
	  * This field allows define a text message with a text very long
	  */
	public String getText();

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

    /** Column name ValidFrom */
    public static final String COLUMNNAME_ValidFrom = "ValidFrom";

	/** Set Valid from.
	  * Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom);

	/** Get Valid from.
	  * Valid from including this date (first day)
	  */
	public Timestamp getValidFrom();

    /** Column name ValidTo */
    public static final String COLUMNNAME_ValidTo = "ValidTo";

	/** Set Valid to.
	  * Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo);

	/** Get Valid to.
	  * Valid to including this date (last day)
	  */
	public Timestamp getValidTo();
}
