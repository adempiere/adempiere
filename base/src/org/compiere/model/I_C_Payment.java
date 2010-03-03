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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_Payment
 *  @author Adempiere (generated) 
 *  @version Release 3.5.4a
 */
public interface I_C_Payment 
{

    /** TableName=C_Payment */
    public static final String Table_Name = "C_Payment";

    /** AD_Table_ID=335 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

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

    /** Column name C_Activity_ID */
    public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";

	/** Set Activity.
	  * Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID);

	/** Get Activity.
	  * Business Activity
	  */
	public int getC_Activity_ID();

	public I_C_Activity getC_Activity() throws RuntimeException;

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

	public I_C_BankAccount getC_BankAccount() throws RuntimeException;

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

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BP_BankAccount_ID */
    public static final String COLUMNNAME_C_BP_BankAccount_ID = "C_BP_BankAccount_ID";

	/** Set Partner Bank Account.
	  * Bank Account of the Business Partner
	  */
	public void setC_BP_BankAccount_ID (int C_BP_BankAccount_ID);

	/** Get Partner Bank Account.
	  * Bank Account of the Business Partner
	  */
	public int getC_BP_BankAccount_ID();

	public I_C_BP_BankAccount getC_BP_BankAccount() throws RuntimeException;

    /** Column name C_Campaign_ID */
    public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";

	/** Set Campaign.
	  * Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID);

	/** Get Campaign.
	  * Marketing Campaign
	  */
	public int getC_Campaign_ID();

	public I_C_Campaign getC_Campaign() throws RuntimeException;

    /** Column name C_CashBook_ID */
    public static final String COLUMNNAME_C_CashBook_ID = "C_CashBook_ID";

	/** Set Cash Book.
	  * Cash Book for recording petty cash transactions
	  */
	public void setC_CashBook_ID (int C_CashBook_ID);

	/** Get Cash Book.
	  * Cash Book for recording petty cash transactions
	  */
	public int getC_CashBook_ID();

	public I_C_CashBook getC_CashBook() throws RuntimeException;

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

	public I_C_Charge getC_Charge() throws RuntimeException;

    /** Column name C_ConversionType_ID */
    public static final String COLUMNNAME_C_ConversionType_ID = "C_ConversionType_ID";

	/** Set Currency Type.
	  * Currency Conversion Rate Type
	  */
	public void setC_ConversionType_ID (int C_ConversionType_ID);

	/** Get Currency Type.
	  * Currency Conversion Rate Type
	  */
	public int getC_ConversionType_ID();

	public I_C_ConversionType getC_ConversionType() throws RuntimeException;

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

	public I_C_Currency getC_Currency() throws RuntimeException;

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

	public I_C_DocType getC_DocType() throws RuntimeException;

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

	public I_C_Invoice getC_Invoice() throws RuntimeException;

    /** Column name C_Order_ID */
    public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";

	/** Set Order.
	  * Order
	  */
	public void setC_Order_ID (int C_Order_ID);

	/** Get Order.
	  * Order
	  */
	public int getC_Order_ID();

	public I_C_Order getC_Order() throws RuntimeException;

    /** Column name C_PaymentBatch_ID */
    public static final String COLUMNNAME_C_PaymentBatch_ID = "C_PaymentBatch_ID";

	/** Set Payment Batch.
	  * Payment batch for EFT
	  */
	public void setC_PaymentBatch_ID (int C_PaymentBatch_ID);

	/** Get Payment Batch.
	  * Payment batch for EFT
	  */
	public int getC_PaymentBatch_ID();

	public I_C_PaymentBatch getC_PaymentBatch() throws RuntimeException;

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

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public I_C_Project getC_Project() throws RuntimeException;

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

    /** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";

	/** Set Document Action.
	  * The targeted status of the document
	  */
	public void setDocAction (String DocAction);

	/** Get Document Action.
	  * The targeted status of the document
	  */
	public String getDocAction();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

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

    /** Column name IsAllocated */
    public static final String COLUMNNAME_IsAllocated = "IsAllocated";

	/** Set Allocated.
	  * Indicates if the payment has been allocated
	  */
	public void setIsAllocated (boolean IsAllocated);

	/** Get Allocated.
	  * Indicates if the payment has been allocated
	  */
	public boolean isAllocated();

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

    /** Column name IsOnline */
    public static final String COLUMNNAME_IsOnline = "IsOnline";

	/** Set Online Access.
	  * Can be accessed online 
	  */
	public void setIsOnline (boolean IsOnline);

	/** Get Online Access.
	  * Can be accessed online 
	  */
	public boolean isOnline();

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

    /** Column name IsPrepayment */
    public static final String COLUMNNAME_IsPrepayment = "IsPrepayment";

	/** Set Prepayment.
	  * The Payment/Receipt is a Prepayment
	  */
	public void setIsPrepayment (boolean IsPrepayment);

	/** Get Prepayment.
	  * The Payment/Receipt is a Prepayment
	  */
	public boolean isPrepayment();

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

    /** Column name IsReconciled */
    public static final String COLUMNNAME_IsReconciled = "IsReconciled";

	/** Set Reconciled.
	  * Payment is reconciled with bank statement
	  */
	public void setIsReconciled (boolean IsReconciled);

	/** Get Reconciled.
	  * Payment is reconciled with bank statement
	  */
	public boolean isReconciled();

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

    /** Column name OProcessing */
    public static final String COLUMNNAME_OProcessing = "OProcessing";

	/** Set Online Processing.
	  * This payment can be processed online
	  */
	public void setOProcessing (String OProcessing);

	/** Get Online Processing.
	  * This payment can be processed online
	  */
	public String getOProcessing();

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

    /** Column name Posted */
    public static final String COLUMNNAME_Posted = "Posted";

	/** Set Posted.
	  * Posting status
	  */
	public void setPosted (boolean Posted);

	/** Get Posted.
	  * Posting status
	  */
	public boolean isPosted();

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

    /** Column name ProcessedOn */
    public static final String COLUMNNAME_ProcessedOn = "ProcessedOn";

	/** Set Processed On.
	  * The date+time (expressed in decimal format) when the document has been processed
	  */
	public void setProcessedOn (BigDecimal ProcessedOn);

	/** Get Processed On.
	  * The date+time (expressed in decimal format) when the document has been processed
	  */
	public BigDecimal getProcessedOn();

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

    /** Column name R_AuthCode_DC */
    public static final String COLUMNNAME_R_AuthCode_DC = "R_AuthCode_DC";

	/** Set Authorization Code (DC).
	  * Authorization Code Delayed Capture returned
	  */
	public void setR_AuthCode_DC (String R_AuthCode_DC);

	/** Get Authorization Code (DC).
	  * Authorization Code Delayed Capture returned
	  */
	public String getR_AuthCode_DC();

    /** Column name R_AvsAddr */
    public static final String COLUMNNAME_R_AvsAddr = "R_AvsAddr";

	/** Set Address verified.
	  * This address has been verified
	  */
	public void setR_AvsAddr (String R_AvsAddr);

	/** Get Address verified.
	  * This address has been verified
	  */
	public String getR_AvsAddr();

    /** Column name R_AvsZip */
    public static final String COLUMNNAME_R_AvsZip = "R_AvsZip";

	/** Set Zip verified.
	  * The Zip Code has been verified
	  */
	public void setR_AvsZip (String R_AvsZip);

	/** Get Zip verified.
	  * The Zip Code has been verified
	  */
	public String getR_AvsZip();

    /** Column name R_CVV2Match */
    public static final String COLUMNNAME_R_CVV2Match = "R_CVV2Match";

	/** Set CVV Match.
	  * Credit Card Verification Code Match
	  */
	public void setR_CVV2Match (boolean R_CVV2Match);

	/** Get CVV Match.
	  * Credit Card Verification Code Match
	  */
	public boolean isR_CVV2Match();

    /** Column name Ref_Payment_ID */
    public static final String COLUMNNAME_Ref_Payment_ID = "Ref_Payment_ID";

	/** Set Referenced Payment	  */
	public void setRef_Payment_ID (int Ref_Payment_ID);

	/** Get Referenced Payment	  */
	public int getRef_Payment_ID();

	public I_C_Payment getRef_Payment() throws RuntimeException;

    /** Column name Reversal_ID */
    public static final String COLUMNNAME_Reversal_ID = "Reversal_ID";

	/** Set Reversal ID.
	  * ID of document reversal
	  */
	public void setReversal_ID (int Reversal_ID);

	/** Get Reversal ID.
	  * ID of document reversal
	  */
	public int getReversal_ID();

	public I_C_Payment getReversal() throws RuntimeException;

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

    /** Column name R_PnRef_DC */
    public static final String COLUMNNAME_R_PnRef_DC = "R_PnRef_DC";

	/** Set Reference (DC).
	  * Payment Reference Delayed Capture
	  */
	public void setR_PnRef_DC (String R_PnRef_DC);

	/** Get Reference (DC).
	  * Payment Reference Delayed Capture
	  */
	public String getR_PnRef_DC();

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

    /** Column name User1_ID */
    public static final String COLUMNNAME_User1_ID = "User1_ID";

	/** Set User List 1.
	  * User defined list element #1
	  */
	public void setUser1_ID (int User1_ID);

	/** Get User List 1.
	  * User defined list element #1
	  */
	public int getUser1_ID();

	public I_C_ElementValue getUser1() throws RuntimeException;

    /** Column name User2_ID */
    public static final String COLUMNNAME_User2_ID = "User2_ID";

	/** Set User List 2.
	  * User defined list element #2
	  */
	public void setUser2_ID (int User2_ID);

	/** Get User List 2.
	  * User defined list element #2
	  */
	public int getUser2_ID();

	public I_C_ElementValue getUser2() throws RuntimeException;

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
