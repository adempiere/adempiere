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

/** Generated Interface for I_BankStatement
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_I_BankStatement 
{

    /** TableName=I_BankStatement */
    public static final String Table_Name = "I_BankStatement";

    /** AD_Table_ID=600 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

    /** Load Meta Data */

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

    /** Column name C_BankStatement_ID */
    public static final String COLUMNNAME_C_BankStatement_ID = "C_BankStatement_ID";

	/** Set Bank Statement.
	  * Bank Statement of account
	  */
	public void setC_BankStatement_ID (int C_BankStatement_ID);

	/** Get Bank Statement.
	  * Bank Statement of account
	  */
	public int getC_BankStatement_ID();

	public I_C_BankStatement getC_BankStatement() throws RuntimeException;

    /** Column name C_BankStatementLine_ID */
    public static final String COLUMNNAME_C_BankStatementLine_ID = "C_BankStatementLine_ID";

	/** Set Bank statement line.
	  * Line on a statement from this Bank
	  */
	public void setC_BankStatementLine_ID (int C_BankStatementLine_ID);

	/** Get Bank statement line.
	  * Line on a statement from this Bank
	  */
	public int getC_BankStatementLine_ID();

	public I_C_BankStatementLine getC_BankStatementLine() throws RuntimeException;

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

	public I_C_Payment getC_Payment() throws RuntimeException;

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

    /** Column name CreatePayment */
    public static final String COLUMNNAME_CreatePayment = "CreatePayment";

	/** Set Create Payment	  */
	public void setCreatePayment (String CreatePayment);

	/** Get Create Payment	  */
	public String getCreatePayment();

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

    /** Column name EftAmt */
    public static final String COLUMNNAME_EftAmt = "EftAmt";

	/** Set EFT Amount.
	  * Electronic Funds Transfer Amount
	  */
	public void setEftAmt (BigDecimal EftAmt);

	/** Get EFT Amount.
	  * Electronic Funds Transfer Amount
	  */
	public BigDecimal getEftAmt();

    /** Column name EftCheckNo */
    public static final String COLUMNNAME_EftCheckNo = "EftCheckNo";

	/** Set EFT Check No.
	  * Electronic Funds Transfer Check No
	  */
	public void setEftCheckNo (String EftCheckNo);

	/** Get EFT Check No.
	  * Electronic Funds Transfer Check No
	  */
	public String getEftCheckNo();

    /** Column name EftCurrency */
    public static final String COLUMNNAME_EftCurrency = "EftCurrency";

	/** Set EFT Currency.
	  * Electronic Funds Transfer Currency
	  */
	public void setEftCurrency (String EftCurrency);

	/** Get EFT Currency.
	  * Electronic Funds Transfer Currency
	  */
	public String getEftCurrency();

    /** Column name EftMemo */
    public static final String COLUMNNAME_EftMemo = "EftMemo";

	/** Set EFT Memo.
	  * Electronic Funds Transfer Memo
	  */
	public void setEftMemo (String EftMemo);

	/** Get EFT Memo.
	  * Electronic Funds Transfer Memo
	  */
	public String getEftMemo();

    /** Column name EftPayee */
    public static final String COLUMNNAME_EftPayee = "EftPayee";

	/** Set EFT Payee.
	  * Electronic Funds Transfer Payee information
	  */
	public void setEftPayee (String EftPayee);

	/** Get EFT Payee.
	  * Electronic Funds Transfer Payee information
	  */
	public String getEftPayee();

    /** Column name EftPayeeAccount */
    public static final String COLUMNNAME_EftPayeeAccount = "EftPayeeAccount";

	/** Set EFT Payee Account.
	  * Electronic Funds Transfer Payyee Account Information
	  */
	public void setEftPayeeAccount (String EftPayeeAccount);

	/** Get EFT Payee Account.
	  * Electronic Funds Transfer Payyee Account Information
	  */
	public String getEftPayeeAccount();

    /** Column name EftReference */
    public static final String COLUMNNAME_EftReference = "EftReference";

	/** Set EFT Reference.
	  * Electronic Funds Transfer Reference
	  */
	public void setEftReference (String EftReference);

	/** Get EFT Reference.
	  * Electronic Funds Transfer Reference
	  */
	public String getEftReference();

    /** Column name EftStatementDate */
    public static final String COLUMNNAME_EftStatementDate = "EftStatementDate";

	/** Set EFT Statement Date.
	  * Electronic Funds Transfer Statement Date
	  */
	public void setEftStatementDate (Timestamp EftStatementDate);

	/** Get EFT Statement Date.
	  * Electronic Funds Transfer Statement Date
	  */
	public Timestamp getEftStatementDate();

    /** Column name EftStatementLineDate */
    public static final String COLUMNNAME_EftStatementLineDate = "EftStatementLineDate";

	/** Set EFT Statement Line Date.
	  * Electronic Funds Transfer Statement Line Date
	  */
	public void setEftStatementLineDate (Timestamp EftStatementLineDate);

	/** Get EFT Statement Line Date.
	  * Electronic Funds Transfer Statement Line Date
	  */
	public Timestamp getEftStatementLineDate();

    /** Column name EftStatementReference */
    public static final String COLUMNNAME_EftStatementReference = "EftStatementReference";

	/** Set EFT Statement Reference.
	  * Electronic Funds Transfer Statement Reference
	  */
	public void setEftStatementReference (String EftStatementReference);

	/** Get EFT Statement Reference.
	  * Electronic Funds Transfer Statement Reference
	  */
	public String getEftStatementReference();

    /** Column name EftTrxID */
    public static final String COLUMNNAME_EftTrxID = "EftTrxID";

	/** Set EFT Trx ID.
	  * Electronic Funds Transfer Transaction ID
	  */
	public void setEftTrxID (String EftTrxID);

	/** Get EFT Trx ID.
	  * Electronic Funds Transfer Transaction ID
	  */
	public String getEftTrxID();

    /** Column name EftTrxType */
    public static final String COLUMNNAME_EftTrxType = "EftTrxType";

	/** Set EFT Trx Type.
	  * Electronic Funds Transfer Transaction Type
	  */
	public void setEftTrxType (String EftTrxType);

	/** Get EFT Trx Type.
	  * Electronic Funds Transfer Transaction Type
	  */
	public String getEftTrxType();

    /** Column name EftValutaDate */
    public static final String COLUMNNAME_EftValutaDate = "EftValutaDate";

	/** Set EFT Effective Date.
	  * Electronic Funds Transfer Valuta (effective) Date
	  */
	public void setEftValutaDate (Timestamp EftValutaDate);

	/** Get EFT Effective Date.
	  * Electronic Funds Transfer Valuta (effective) Date
	  */
	public Timestamp getEftValutaDate();

    /** Column name I_BankStatement_ID */
    public static final String COLUMNNAME_I_BankStatement_ID = "I_BankStatement_ID";

	/** Set Import Bank Statement.
	  * Import of the Bank Statement
	  */
	public void setI_BankStatement_ID (int I_BankStatement_ID);

	/** Get Import Bank Statement.
	  * Import of the Bank Statement
	  */
	public int getI_BankStatement_ID();

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

    /** Column name IsReversal */
    public static final String COLUMNNAME_IsReversal = "IsReversal";

	/** Set Reversal.
	  * This is a reversing transaction
	  */
	public void setIsReversal (boolean IsReversal);

	/** Get Reversal.
	  * This is a reversing transaction
	  */
	public boolean isReversal();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name LineDescription */
    public static final String COLUMNNAME_LineDescription = "LineDescription";

	/** Set Line Description.
	  * Description of the Line
	  */
	public void setLineDescription (String LineDescription);

	/** Get Line Description.
	  * Description of the Line
	  */
	public String getLineDescription();

    /** Column name MatchStatement */
    public static final String COLUMNNAME_MatchStatement = "MatchStatement";

	/** Set Match Statement	  */
	public void setMatchStatement (String MatchStatement);

	/** Get Match Statement	  */
	public String getMatchStatement();

    /** Column name Memo */
    public static final String COLUMNNAME_Memo = "Memo";

	/** Set Memo.
	  * Memo Text
	  */
	public void setMemo (String Memo);

	/** Get Memo.
	  * Memo Text
	  */
	public String getMemo();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name PaymentDocumentNo */
    public static final String COLUMNNAME_PaymentDocumentNo = "PaymentDocumentNo";

	/** Set Payment Document No.
	  * Document number of the Payment
	  */
	public void setPaymentDocumentNo (String PaymentDocumentNo);

	/** Get Payment Document No.
	  * Document number of the Payment
	  */
	public String getPaymentDocumentNo();

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

    /** Column name ReferenceNo */
    public static final String COLUMNNAME_ReferenceNo = "ReferenceNo";

	/** Set Reference No.
	  * Your customer or vendor number at the Business Partner's site
	  */
	public void setReferenceNo (String ReferenceNo);

	/** Get Reference No.
	  * Your customer or vendor number at the Business Partner's site
	  */
	public String getReferenceNo();

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

    /** Column name StatementDate */
    public static final String COLUMNNAME_StatementDate = "StatementDate";

	/** Set Statement date.
	  * Date of the statement
	  */
	public void setStatementDate (Timestamp StatementDate);

	/** Get Statement date.
	  * Date of the statement
	  */
	public Timestamp getStatementDate();

    /** Column name StatementLineDate */
    public static final String COLUMNNAME_StatementLineDate = "StatementLineDate";

	/** Set Statement Line Date.
	  * Date of the Statement Line
	  */
	public void setStatementLineDate (Timestamp StatementLineDate);

	/** Get Statement Line Date.
	  * Date of the Statement Line
	  */
	public Timestamp getStatementLineDate();

    /** Column name StmtAmt */
    public static final String COLUMNNAME_StmtAmt = "StmtAmt";

	/** Set Statement amount.
	  * Statement Amount
	  */
	public void setStmtAmt (BigDecimal StmtAmt);

	/** Get Statement amount.
	  * Statement Amount
	  */
	public BigDecimal getStmtAmt();

    /** Column name TrxAmt */
    public static final String COLUMNNAME_TrxAmt = "TrxAmt";

	/** Set Transaction Amount.
	  * Amount of a transaction
	  */
	public void setTrxAmt (BigDecimal TrxAmt);

	/** Get Transaction Amount.
	  * Amount of a transaction
	  */
	public BigDecimal getTrxAmt();

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

    /** Column name ValutaDate */
    public static final String COLUMNNAME_ValutaDate = "ValutaDate";

	/** Set Effective date.
	  * Date when money is available
	  */
	public void setValutaDate (Timestamp ValutaDate);

	/** Get Effective date.
	  * Date when money is available
	  */
	public Timestamp getValutaDate();
}
