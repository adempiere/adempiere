/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_Payment
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_I_Payment 
{

    /** TableName=I_Payment */
    public static final String Table_Name = "I_Payment";

    /** AD_Table_ID=597 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

    /** Load Meta Data */

    /** Column name A_City */
    public static final String COLUMNNAME_A_City = "A_City";

	/** Set Account City.
	  * City or the Credit Card or Account Holder
	  */
	public void setA_City (String A_City);

	/** Get Account City.
	  * City or the Credit Card or Account Holder
	  */
	public String getA_City();

    /** Column name A_Country */
    public static final String COLUMNNAME_A_Country = "A_Country";

	/** Set Account Country.
	  * Country
	  */
	public void setA_Country (String A_Country);

	/** Get Account Country.
	  * Country
	  */
	public String getA_Country();

    /** Column name A_EMail */
    public static final String COLUMNNAME_A_EMail = "A_EMail";

	/** Set Account EMail.
	  * Email Address
	  */
	public void setA_EMail (String A_EMail);

	/** Get Account EMail.
	  * Email Address
	  */
	public String getA_EMail();

    /** Column name A_Ident_DL */
    public static final String COLUMNNAME_A_Ident_DL = "A_Ident_DL";

	/** Set Driver License.
	  * Payment Identification - Driver License
	  */
	public void setA_Ident_DL (String A_Ident_DL);

	/** Get Driver License.
	  * Payment Identification - Driver License
	  */
	public String getA_Ident_DL();

    /** Column name A_Ident_SSN */
    public static final String COLUMNNAME_A_Ident_SSN = "A_Ident_SSN";

	/** Set Social Security No.
	  * Payment Identification - Social Security No
	  */
	public void setA_Ident_SSN (String A_Ident_SSN);

	/** Get Social Security No.
	  * Payment Identification - Social Security No
	  */
	public String getA_Ident_SSN();

    /** Column name A_Name */
    public static final String COLUMNNAME_A_Name = "A_Name";

	/** Set Account Name.
	  * Name on Credit Card or Account holder
	  */
	public void setA_Name (String A_Name);

	/** Get Account Name.
	  * Name on Credit Card or Account holder
	  */
	public String getA_Name();

    /** Column name A_State */
    public static final String COLUMNNAME_A_State = "A_State";

	/** Set Account State.
	  * State of the Credit Card or Account holder
	  */
	public void setA_State (String A_State);

	/** Get Account State.
	  * State of the Credit Card or Account holder
	  */
	public String getA_State();

    /** Column name A_Street */
    public static final String COLUMNNAME_A_Street = "A_Street";

	/** Set Account Street.
	  * Street address of the Credit Card or Account holder
	  */
	public void setA_Street (String A_Street);

	/** Get Account Street.
	  * Street address of the Credit Card or Account holder
	  */
	public String getA_Street();

    /** Column name A_Zip */
    public static final String COLUMNNAME_A_Zip = "A_Zip";

	/** Set Account Zip/Postal.
	  * Zip Code of the Credit Card or Account Holder
	  */
	public void setA_Zip (String A_Zip);

	/** Get Account Zip/Postal.
	  * Zip Code of the Credit Card or Account Holder
	  */
	public String getA_Zip();

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

    /** Column name BankAccountNo */
    public static final String COLUMNNAME_BankAccountNo = "BankAccountNo";

	/** Set Bank Account No.
	  * Bank Account Number
	  */
	public void setBankAccountNo (String BankAccountNo);

	/** Get Bank Account No.
	  * Bank Account Number
	  */
	public String getBankAccountNo();

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

	public I_C_BPartner getC_BPartner() throws Exception;

    /** Column name C_BankAccount_ID */
    public static final String COLUMNNAME_C_BankAccount_ID = "C_BankAccount_ID";

	/** Set Bank Account.
	  * Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID);

	/** Get Bank Account.
	  * Account at the Bank
	  */
	public int getC_BankAccount_ID();

	public I_C_BankAccount getC_BankAccount() throws Exception;

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public I_C_Charge getC_Charge() throws Exception;

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

	public I_C_Currency getC_Currency() throws Exception;

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

	public I_C_DocType getC_DocType() throws Exception;

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

	public I_C_Invoice getC_Invoice() throws Exception;

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Payment.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Payment.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

	public I_C_Payment getC_Payment() throws Exception;

    /** Column name ChargeAmt */
    public static final String COLUMNNAME_ChargeAmt = "ChargeAmt";

	/** Set Charge amount.
	  * Charge Amount
	  */
	public void setChargeAmt (BigDecimal ChargeAmt);

	/** Get Charge amount.
	  * Charge Amount
	  */
	public BigDecimal getChargeAmt();

    /** Column name ChargeName */
    public static final String COLUMNNAME_ChargeName = "ChargeName";

	/** Set Charge Name.
	  * Name of the Charge
	  */
	public void setChargeName (String ChargeName);

	/** Get Charge Name.
	  * Name of the Charge
	  */
	public String getChargeName();

    /** Column name CheckNo */
    public static final String COLUMNNAME_CheckNo = "CheckNo";

	/** Set Check No.
	  * Check Number
	  */
	public void setCheckNo (String CheckNo);

	/** Get Check No.
	  * Check Number
	  */
	public String getCheckNo();

    /** Column name CreditCardExpMM */
    public static final String COLUMNNAME_CreditCardExpMM = "CreditCardExpMM";

	/** Set Exp. Month.
	  * Expiry Month
	  */
	public void setCreditCardExpMM (int CreditCardExpMM);

	/** Get Exp. Month.
	  * Expiry Month
	  */
	public int getCreditCardExpMM();

    /** Column name CreditCardExpYY */
    public static final String COLUMNNAME_CreditCardExpYY = "CreditCardExpYY";

	/** Set Exp. Year.
	  * Expiry Year
	  */
	public void setCreditCardExpYY (int CreditCardExpYY);

	/** Get Exp. Year.
	  * Expiry Year
	  */
	public int getCreditCardExpYY();

    /** Column name CreditCardNumber */
    public static final String COLUMNNAME_CreditCardNumber = "CreditCardNumber";

	/** Set Number.
	  * Credit Card Number 
	  */
	public void setCreditCardNumber (String CreditCardNumber);

	/** Get Number.
	  * Credit Card Number 
	  */
	public String getCreditCardNumber();

    /** Column name CreditCardType */
    public static final String COLUMNNAME_CreditCardType = "CreditCardType";

	/** Set Credit Card.
	  * Credit Card (Visa, MC, AmEx)
	  */
	public void setCreditCardType (String CreditCardType);

	/** Get Credit Card.
	  * Credit Card (Visa, MC, AmEx)
	  */
	public String getCreditCardType();

    /** Column name CreditCardVV */
    public static final String COLUMNNAME_CreditCardVV = "CreditCardVV";

	/** Set Verification Code.
	  * Credit Card Verification code on credit card
	  */
	public void setCreditCardVV (String CreditCardVV);

	/** Get Verification Code.
	  * Credit Card Verification code on credit card
	  */
	public String getCreditCardVV();

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

    /** Column name DateTrx */
    public static final String COLUMNNAME_DateTrx = "DateTrx";

	/** Set Transaction Date.
	  * Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx);

	/** Get Transaction Date.
	  * Transaction Date
	  */
	public Timestamp getDateTrx();

    /** Column name DiscountAmt */
    public static final String COLUMNNAME_DiscountAmt = "DiscountAmt";

	/** Set Discount Amount.
	  * Calculated amount of discount
	  */
	public void setDiscountAmt (BigDecimal DiscountAmt);

	/** Get Discount Amount.
	  * Calculated amount of discount
	  */
	public BigDecimal getDiscountAmt();

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

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name I_Payment_ID */
    public static final String COLUMNNAME_I_Payment_ID = "I_Payment_ID";

	/** Set Import Payment.
	  * Import Payment
	  */
	public void setI_Payment_ID (int I_Payment_ID);

	/** Get Import Payment.
	  * Import Payment
	  */
	public int getI_Payment_ID();

    /** Column name InvoiceDocumentNo */
    public static final String COLUMNNAME_InvoiceDocumentNo = "InvoiceDocumentNo";

	/** Set Invoice Document No.
	  * Document Number of the Invoice
	  */
	public void setInvoiceDocumentNo (String InvoiceDocumentNo);

	/** Get Invoice Document No.
	  * Document Number of the Invoice
	  */
	public String getInvoiceDocumentNo();

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

    /** Column name IsDelayedCapture */
    public static final String COLUMNNAME_IsDelayedCapture = "IsDelayedCapture";

	/** Set Delayed Capture.
	  * Charge after Shipment
	  */
	public void setIsDelayedCapture (boolean IsDelayedCapture);

	/** Get Delayed Capture.
	  * Charge after Shipment
	  */
	public boolean isDelayedCapture();

    /** Column name IsOverUnderPayment */
    public static final String COLUMNNAME_IsOverUnderPayment = "IsOverUnderPayment";

	/** Set Over/Under Payment.
	  * Over-Payment (unallocated) or Under-Payment (partial payment)
	  */
	public void setIsOverUnderPayment (boolean IsOverUnderPayment);

	/** Get Over/Under Payment.
	  * Over-Payment (unallocated) or Under-Payment (partial payment)
	  */
	public boolean isOverUnderPayment();

    /** Column name IsReceipt */
    public static final String COLUMNNAME_IsReceipt = "IsReceipt";

	/** Set Receipt.
	  * This is a sales transaction (receipt)
	  */
	public void setIsReceipt (boolean IsReceipt);

	/** Get Receipt.
	  * This is a sales transaction (receipt)
	  */
	public boolean isReceipt();

    /** Column name IsSelfService */
    public static final String COLUMNNAME_IsSelfService = "IsSelfService";

	/** Set Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public void setIsSelfService (boolean IsSelfService);

	/** Get Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public boolean isSelfService();

    /** Column name Micr */
    public static final String COLUMNNAME_Micr = "Micr";

	/** Set Micr.
	  * Combination of routing no, account and check no
	  */
	public void setMicr (String Micr);

	/** Get Micr.
	  * Combination of routing no, account and check no
	  */
	public String getMicr();

    /** Column name Orig_TrxID */
    public static final String COLUMNNAME_Orig_TrxID = "Orig_TrxID";

	/** Set Original Transaction ID.
	  * Original Transaction ID
	  */
	public void setOrig_TrxID (String Orig_TrxID);

	/** Get Original Transaction ID.
	  * Original Transaction ID
	  */
	public String getOrig_TrxID();

    /** Column name OverUnderAmt */
    public static final String COLUMNNAME_OverUnderAmt = "OverUnderAmt";

	/** Set Over/Under Payment.
	  * Over-Payment (unallocated) or Under-Payment (partial payment) Amount
	  */
	public void setOverUnderAmt (BigDecimal OverUnderAmt);

	/** Get Over/Under Payment.
	  * Over-Payment (unallocated) or Under-Payment (partial payment) Amount
	  */
	public BigDecimal getOverUnderAmt();

    /** Column name PONum */
    public static final String COLUMNNAME_PONum = "PONum";

	/** Set PO Number.
	  * Purchase Order Number
	  */
	public void setPONum (String PONum);

	/** Get PO Number.
	  * Purchase Order Number
	  */
	public String getPONum();

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

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name R_AuthCode */
    public static final String COLUMNNAME_R_AuthCode = "R_AuthCode";

	/** Set Authorization Code.
	  * Authorization Code returned
	  */
	public void setR_AuthCode (String R_AuthCode);

	/** Get Authorization Code.
	  * Authorization Code returned
	  */
	public String getR_AuthCode();

    /** Column name R_Info */
    public static final String COLUMNNAME_R_Info = "R_Info";

	/** Set Info.
	  * Response info
	  */
	public void setR_Info (String R_Info);

	/** Get Info.
	  * Response info
	  */
	public String getR_Info();

    /** Column name R_PnRef */
    public static final String COLUMNNAME_R_PnRef = "R_PnRef";

	/** Set Reference.
	  * Payment reference
	  */
	public void setR_PnRef (String R_PnRef);

	/** Get Reference.
	  * Payment reference
	  */
	public String getR_PnRef();

    /** Column name R_RespMsg */
    public static final String COLUMNNAME_R_RespMsg = "R_RespMsg";

	/** Set Response Message.
	  * Response message
	  */
	public void setR_RespMsg (String R_RespMsg);

	/** Get Response Message.
	  * Response message
	  */
	public String getR_RespMsg();

    /** Column name R_Result */
    public static final String COLUMNNAME_R_Result = "R_Result";

	/** Set Result.
	  * Result of transmission
	  */
	public void setR_Result (String R_Result);

	/** Get Result.
	  * Result of transmission
	  */
	public String getR_Result();

    /** Column name RoutingNo */
    public static final String COLUMNNAME_RoutingNo = "RoutingNo";

	/** Set Routing No.
	  * Bank Routing Number
	  */
	public void setRoutingNo (String RoutingNo);

	/** Get Routing No.
	  * Bank Routing Number
	  */
	public String getRoutingNo();

    /** Column name Swipe */
    public static final String COLUMNNAME_Swipe = "Swipe";

	/** Set Swipe.
	  * Track 1 and 2 of the Credit Card
	  */
	public void setSwipe (String Swipe);

	/** Get Swipe.
	  * Track 1 and 2 of the Credit Card
	  */
	public String getSwipe();

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

    /** Column name TenderType */
    public static final String COLUMNNAME_TenderType = "TenderType";

	/** Set Tender type.
	  * Method of Payment
	  */
	public void setTenderType (String TenderType);

	/** Get Tender type.
	  * Method of Payment
	  */
	public String getTenderType();

    /** Column name TrxType */
    public static final String COLUMNNAME_TrxType = "TrxType";

	/** Set Transaction Type.
	  * Type of credit card transaction
	  */
	public void setTrxType (String TrxType);

	/** Get Transaction Type.
	  * Type of credit card transaction
	  */
	public String getTrxType();

    /** Column name VoiceAuthCode */
    public static final String COLUMNNAME_VoiceAuthCode = "VoiceAuthCode";

	/** Set Voice authorization code.
	  * Voice Authorization Code from credit card company
	  */
	public void setVoiceAuthCode (String VoiceAuthCode);

	/** Get Voice authorization code.
	  * Voice Authorization Code from credit card company
	  */
	public String getVoiceAuthCode();

    /** Column name WriteOffAmt */
    public static final String COLUMNNAME_WriteOffAmt = "WriteOffAmt";

	/** Set Write-off Amount.
	  * Amount to write-off
	  */
	public void setWriteOffAmt (BigDecimal WriteOffAmt);

	/** Get Write-off Amount.
	  * Amount to write-off
	  */
	public BigDecimal getWriteOffAmt();
}
