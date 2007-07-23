/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

/** Generated Model - DO NOT CHANGE */
import java.util.*;
import java.sql.*;
import java.math.*;
import org.compiere.util.*;
/** Generated Model for I_BankStatement
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_I_BankStatement extends PO
{
/** Standard Constructor
@param ctx context
@param I_BankStatement_ID id
@param trxName transaction
*/
public X_I_BankStatement (Properties ctx, int I_BankStatement_ID, String trxName)
{
super (ctx, I_BankStatement_ID, trxName);
/** if (I_BankStatement_ID == 0)
{
setI_BankStatement_ID (0);
setI_IsImported (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_I_BankStatement (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=I_BankStatement */
public static final String Table_Name="I_BankStatement";

/** AD_Table_ID=600 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

protected BigDecimal accessLevel = BigDecimal.valueOf(2);
/** AccessLevel
@return 2 - Client 
*/
protected int get_AccessLevel()
{
return accessLevel.intValue();
}
/** Load Meta Data
@param ctx context
@return PO Info
*/
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
/** Info
@return info
*/
public String toString()
{
StringBuffer sb = new StringBuffer ("X_I_BankStatement[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Business Partner Key.
@param BPartnerValue Key of the Business Partner */
public void setBPartnerValue (String BPartnerValue)
{
if (BPartnerValue != null && BPartnerValue.length() > 40)
{
log.warning("Length > 40 - truncated");
BPartnerValue = BPartnerValue.substring(0,39);
}
set_Value ("BPartnerValue", BPartnerValue);
}
/** Get Business Partner Key.
@return Key of the Business Partner */
public String getBPartnerValue() 
{
return (String)get_Value("BPartnerValue");
}
/** Column name BPartnerValue */
public static final String COLUMNNAME_BPartnerValue = "BPartnerValue";
/** Set Bank Account No.
@param BankAccountNo Bank Account Number */
public void setBankAccountNo (String BankAccountNo)
{
if (BankAccountNo != null && BankAccountNo.length() > 20)
{
log.warning("Length > 20 - truncated");
BankAccountNo = BankAccountNo.substring(0,19);
}
set_Value ("BankAccountNo", BankAccountNo);
}
/** Get Bank Account No.
@return Bank Account Number */
public String getBankAccountNo() 
{
return (String)get_Value("BankAccountNo");
}
/** Column name BankAccountNo */
public static final String COLUMNNAME_BankAccountNo = "BankAccountNo";
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_Value ("C_BPartner_ID", null);
 else 
set_Value ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_ID */
public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";
/** Set Bank Account.
@param C_BankAccount_ID Account at the Bank */
public void setC_BankAccount_ID (int C_BankAccount_ID)
{
if (C_BankAccount_ID <= 0) set_Value ("C_BankAccount_ID", null);
 else 
set_Value ("C_BankAccount_ID", Integer.valueOf(C_BankAccount_ID));
}
/** Get Bank Account.
@return Account at the Bank */
public int getC_BankAccount_ID() 
{
Integer ii = (Integer)get_Value("C_BankAccount_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BankAccount_ID */
public static final String COLUMNNAME_C_BankAccount_ID = "C_BankAccount_ID";
/** Set Bank statement line.
@param C_BankStatementLine_ID Line on a statement from this Bank */
public void setC_BankStatementLine_ID (int C_BankStatementLine_ID)
{
if (C_BankStatementLine_ID <= 0) set_Value ("C_BankStatementLine_ID", null);
 else 
set_Value ("C_BankStatementLine_ID", Integer.valueOf(C_BankStatementLine_ID));
}
/** Get Bank statement line.
@return Line on a statement from this Bank */
public int getC_BankStatementLine_ID() 
{
Integer ii = (Integer)get_Value("C_BankStatementLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BankStatementLine_ID */
public static final String COLUMNNAME_C_BankStatementLine_ID = "C_BankStatementLine_ID";
/** Set Bank Statement.
@param C_BankStatement_ID Bank Statement of account */
public void setC_BankStatement_ID (int C_BankStatement_ID)
{
if (C_BankStatement_ID <= 0) set_Value ("C_BankStatement_ID", null);
 else 
set_Value ("C_BankStatement_ID", Integer.valueOf(C_BankStatement_ID));
}
/** Get Bank Statement.
@return Bank Statement of account */
public int getC_BankStatement_ID() 
{
Integer ii = (Integer)get_Value("C_BankStatement_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BankStatement_ID */
public static final String COLUMNNAME_C_BankStatement_ID = "C_BankStatement_ID";
/** Set Charge.
@param C_Charge_ID Additional document charges */
public void setC_Charge_ID (int C_Charge_ID)
{
if (C_Charge_ID <= 0) set_Value ("C_Charge_ID", null);
 else 
set_Value ("C_Charge_ID", Integer.valueOf(C_Charge_ID));
}
/** Get Charge.
@return Additional document charges */
public int getC_Charge_ID() 
{
Integer ii = (Integer)get_Value("C_Charge_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Charge_ID */
public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID <= 0) set_Value ("C_Currency_ID", null);
 else 
set_Value ("C_Currency_ID", Integer.valueOf(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Currency_ID */
public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";
/** Set Invoice.
@param C_Invoice_ID Invoice Identifier */
public void setC_Invoice_ID (int C_Invoice_ID)
{
if (C_Invoice_ID <= 0) set_Value ("C_Invoice_ID", null);
 else 
set_Value ("C_Invoice_ID", Integer.valueOf(C_Invoice_ID));
}
/** Get Invoice.
@return Invoice Identifier */
public int getC_Invoice_ID() 
{
Integer ii = (Integer)get_Value("C_Invoice_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Invoice_ID */
public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";
/** Set Payment.
@param C_Payment_ID Payment identifier */
public void setC_Payment_ID (int C_Payment_ID)
{
if (C_Payment_ID <= 0) set_Value ("C_Payment_ID", null);
 else 
set_Value ("C_Payment_ID", Integer.valueOf(C_Payment_ID));
}
/** Get Payment.
@return Payment identifier */
public int getC_Payment_ID() 
{
Integer ii = (Integer)get_Value("C_Payment_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Payment_ID */
public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";
/** Set Charge amount.
@param ChargeAmt Charge Amount */
public void setChargeAmt (BigDecimal ChargeAmt)
{
set_Value ("ChargeAmt", ChargeAmt);
}
/** Get Charge amount.
@return Charge Amount */
public BigDecimal getChargeAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("ChargeAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name ChargeAmt */
public static final String COLUMNNAME_ChargeAmt = "ChargeAmt";
/** Set Charge Name.
@param ChargeName Name of the Charge */
public void setChargeName (String ChargeName)
{
if (ChargeName != null && ChargeName.length() > 60)
{
log.warning("Length > 60 - truncated");
ChargeName = ChargeName.substring(0,59);
}
set_Value ("ChargeName", ChargeName);
}
/** Get Charge Name.
@return Name of the Charge */
public String getChargeName() 
{
return (String)get_Value("ChargeName");
}
/** Column name ChargeName */
public static final String COLUMNNAME_ChargeName = "ChargeName";
/** Set Create Payment.
@param CreatePayment Create Payment */
public void setCreatePayment (String CreatePayment)
{
if (CreatePayment != null && CreatePayment.length() > 1)
{
log.warning("Length > 1 - truncated");
CreatePayment = CreatePayment.substring(0,0);
}
set_Value ("CreatePayment", CreatePayment);
}
/** Get Create Payment.
@return Create Payment */
public String getCreatePayment() 
{
return (String)get_Value("CreatePayment");
}
/** Column name CreatePayment */
public static final String COLUMNNAME_CreatePayment = "CreatePayment";
/** Set Account Date.
@param DateAcct Accounting Date */
public void setDateAcct (Timestamp DateAcct)
{
set_Value ("DateAcct", DateAcct);
}
/** Get Account Date.
@return Accounting Date */
public Timestamp getDateAcct() 
{
return (Timestamp)get_Value("DateAcct");
}
/** Column name DateAcct */
public static final String COLUMNNAME_DateAcct = "DateAcct";
/** Set Description.
@param Description Optional short description of the record */
public void setDescription (String Description)
{
if (Description != null && Description.length() > 255)
{
log.warning("Length > 255 - truncated");
Description = Description.substring(0,254);
}
set_Value ("Description", Description);
}
/** Get Description.
@return Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set EFT Amount.
@param EftAmt Electronic Funds Transfer Amount */
public void setEftAmt (BigDecimal EftAmt)
{
set_Value ("EftAmt", EftAmt);
}
/** Get EFT Amount.
@return Electronic Funds Transfer Amount */
public BigDecimal getEftAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("EftAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name EftAmt */
public static final String COLUMNNAME_EftAmt = "EftAmt";
/** Set EFT Check No.
@param EftCheckNo Electronic Funds Transfer Check No */
public void setEftCheckNo (String EftCheckNo)
{
if (EftCheckNo != null && EftCheckNo.length() > 20)
{
log.warning("Length > 20 - truncated");
EftCheckNo = EftCheckNo.substring(0,19);
}
set_Value ("EftCheckNo", EftCheckNo);
}
/** Get EFT Check No.
@return Electronic Funds Transfer Check No */
public String getEftCheckNo() 
{
return (String)get_Value("EftCheckNo");
}
/** Column name EftCheckNo */
public static final String COLUMNNAME_EftCheckNo = "EftCheckNo";
/** Set EFT Currency.
@param EftCurrency Electronic Funds Transfer Currency */
public void setEftCurrency (String EftCurrency)
{
if (EftCurrency != null && EftCurrency.length() > 20)
{
log.warning("Length > 20 - truncated");
EftCurrency = EftCurrency.substring(0,19);
}
set_Value ("EftCurrency", EftCurrency);
}
/** Get EFT Currency.
@return Electronic Funds Transfer Currency */
public String getEftCurrency() 
{
return (String)get_Value("EftCurrency");
}
/** Column name EftCurrency */
public static final String COLUMNNAME_EftCurrency = "EftCurrency";
/** Set EFT Memo.
@param EftMemo Electronic Funds Transfer Memo */
public void setEftMemo (String EftMemo)
{
if (EftMemo != null && EftMemo.length() > 2000)
{
log.warning("Length > 2000 - truncated");
EftMemo = EftMemo.substring(0,1999);
}
set_Value ("EftMemo", EftMemo);
}
/** Get EFT Memo.
@return Electronic Funds Transfer Memo */
public String getEftMemo() 
{
return (String)get_Value("EftMemo");
}
/** Column name EftMemo */
public static final String COLUMNNAME_EftMemo = "EftMemo";
/** Set EFT Payee.
@param EftPayee Electronic Funds Transfer Payee information */
public void setEftPayee (String EftPayee)
{
if (EftPayee != null && EftPayee.length() > 255)
{
log.warning("Length > 255 - truncated");
EftPayee = EftPayee.substring(0,254);
}
set_Value ("EftPayee", EftPayee);
}
/** Get EFT Payee.
@return Electronic Funds Transfer Payee information */
public String getEftPayee() 
{
return (String)get_Value("EftPayee");
}
/** Column name EftPayee */
public static final String COLUMNNAME_EftPayee = "EftPayee";
/** Set EFT Payee Account.
@param EftPayeeAccount Electronic Funds Transfer Payyee Account Information */
public void setEftPayeeAccount (String EftPayeeAccount)
{
if (EftPayeeAccount != null && EftPayeeAccount.length() > 40)
{
log.warning("Length > 40 - truncated");
EftPayeeAccount = EftPayeeAccount.substring(0,39);
}
set_Value ("EftPayeeAccount", EftPayeeAccount);
}
/** Get EFT Payee Account.
@return Electronic Funds Transfer Payyee Account Information */
public String getEftPayeeAccount() 
{
return (String)get_Value("EftPayeeAccount");
}
/** Column name EftPayeeAccount */
public static final String COLUMNNAME_EftPayeeAccount = "EftPayeeAccount";
/** Set EFT Reference.
@param EftReference Electronic Funds Transfer Reference */
public void setEftReference (String EftReference)
{
if (EftReference != null && EftReference.length() > 60)
{
log.warning("Length > 60 - truncated");
EftReference = EftReference.substring(0,59);
}
set_Value ("EftReference", EftReference);
}
/** Get EFT Reference.
@return Electronic Funds Transfer Reference */
public String getEftReference() 
{
return (String)get_Value("EftReference");
}
/** Column name EftReference */
public static final String COLUMNNAME_EftReference = "EftReference";
/** Set EFT Statement Date.
@param EftStatementDate Electronic Funds Transfer Statement Date */
public void setEftStatementDate (Timestamp EftStatementDate)
{
set_Value ("EftStatementDate", EftStatementDate);
}
/** Get EFT Statement Date.
@return Electronic Funds Transfer Statement Date */
public Timestamp getEftStatementDate() 
{
return (Timestamp)get_Value("EftStatementDate");
}
/** Column name EftStatementDate */
public static final String COLUMNNAME_EftStatementDate = "EftStatementDate";
/** Set EFT Statement Line Date.
@param EftStatementLineDate Electronic Funds Transfer Statement Line Date */
public void setEftStatementLineDate (Timestamp EftStatementLineDate)
{
set_Value ("EftStatementLineDate", EftStatementLineDate);
}
/** Get EFT Statement Line Date.
@return Electronic Funds Transfer Statement Line Date */
public Timestamp getEftStatementLineDate() 
{
return (Timestamp)get_Value("EftStatementLineDate");
}
/** Column name EftStatementLineDate */
public static final String COLUMNNAME_EftStatementLineDate = "EftStatementLineDate";
/** Set EFT Statement Reference.
@param EftStatementReference Electronic Funds Transfer Statement Reference */
public void setEftStatementReference (String EftStatementReference)
{
if (EftStatementReference != null && EftStatementReference.length() > 60)
{
log.warning("Length > 60 - truncated");
EftStatementReference = EftStatementReference.substring(0,59);
}
set_Value ("EftStatementReference", EftStatementReference);
}
/** Get EFT Statement Reference.
@return Electronic Funds Transfer Statement Reference */
public String getEftStatementReference() 
{
return (String)get_Value("EftStatementReference");
}
/** Column name EftStatementReference */
public static final String COLUMNNAME_EftStatementReference = "EftStatementReference";
/** Set EFT Trx ID.
@param EftTrxID Electronic Funds Transfer Transaction ID */
public void setEftTrxID (String EftTrxID)
{
if (EftTrxID != null && EftTrxID.length() > 40)
{
log.warning("Length > 40 - truncated");
EftTrxID = EftTrxID.substring(0,39);
}
set_Value ("EftTrxID", EftTrxID);
}
/** Get EFT Trx ID.
@return Electronic Funds Transfer Transaction ID */
public String getEftTrxID() 
{
return (String)get_Value("EftTrxID");
}
/** Column name EftTrxID */
public static final String COLUMNNAME_EftTrxID = "EftTrxID";
/** Set EFT Trx Type.
@param EftTrxType Electronic Funds Transfer Transaction Type */
public void setEftTrxType (String EftTrxType)
{
if (EftTrxType != null && EftTrxType.length() > 20)
{
log.warning("Length > 20 - truncated");
EftTrxType = EftTrxType.substring(0,19);
}
set_Value ("EftTrxType", EftTrxType);
}
/** Get EFT Trx Type.
@return Electronic Funds Transfer Transaction Type */
public String getEftTrxType() 
{
return (String)get_Value("EftTrxType");
}
/** Column name EftTrxType */
public static final String COLUMNNAME_EftTrxType = "EftTrxType";
/** Set EFT Effective Date.
@param EftValutaDate Electronic Funds Transfer Valuta (effective) Date */
public void setEftValutaDate (Timestamp EftValutaDate)
{
set_Value ("EftValutaDate", EftValutaDate);
}
/** Get EFT Effective Date.
@return Electronic Funds Transfer Valuta (effective) Date */
public Timestamp getEftValutaDate() 
{
return (Timestamp)get_Value("EftValutaDate");
}
/** Column name EftValutaDate */
public static final String COLUMNNAME_EftValutaDate = "EftValutaDate";
/** Set ISO Currency Code.
@param ISO_Code Three letter ISO 4217 Code of the Currency */
public void setISO_Code (String ISO_Code)
{
if (ISO_Code != null && ISO_Code.length() > 3)
{
log.warning("Length > 3 - truncated");
ISO_Code = ISO_Code.substring(0,2);
}
set_Value ("ISO_Code", ISO_Code);
}
/** Get ISO Currency Code.
@return Three letter ISO 4217 Code of the Currency */
public String getISO_Code() 
{
return (String)get_Value("ISO_Code");
}
/** Column name ISO_Code */
public static final String COLUMNNAME_ISO_Code = "ISO_Code";
/** Set Import Bank Statement.
@param I_BankStatement_ID Import of the Bank Statement */
public void setI_BankStatement_ID (int I_BankStatement_ID)
{
if (I_BankStatement_ID < 1) throw new IllegalArgumentException ("I_BankStatement_ID is mandatory.");
set_ValueNoCheck ("I_BankStatement_ID", Integer.valueOf(I_BankStatement_ID));
}
/** Get Import Bank Statement.
@return Import of the Bank Statement */
public int getI_BankStatement_ID() 
{
Integer ii = (Integer)get_Value("I_BankStatement_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name I_BankStatement_ID */
public static final String COLUMNNAME_I_BankStatement_ID = "I_BankStatement_ID";
/** Set Import Error Message.
@param I_ErrorMsg Messages generated from import process */
public void setI_ErrorMsg (String I_ErrorMsg)
{
if (I_ErrorMsg != null && I_ErrorMsg.length() > 2000)
{
log.warning("Length > 2000 - truncated");
I_ErrorMsg = I_ErrorMsg.substring(0,1999);
}
set_Value ("I_ErrorMsg", I_ErrorMsg);
}
/** Get Import Error Message.
@return Messages generated from import process */
public String getI_ErrorMsg() 
{
return (String)get_Value("I_ErrorMsg");
}
/** Column name I_ErrorMsg */
public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";
/** Set Imported.
@param I_IsImported Has this import been processed */
public void setI_IsImported (boolean I_IsImported)
{
set_Value ("I_IsImported", Boolean.valueOf(I_IsImported));
}
/** Get Imported.
@return Has this import been processed */
public boolean isI_IsImported() 
{
Object oo = get_Value("I_IsImported");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name I_IsImported */
public static final String COLUMNNAME_I_IsImported = "I_IsImported";
/** Set Interest Amount.
@param InterestAmt Interest Amount */
public void setInterestAmt (BigDecimal InterestAmt)
{
set_Value ("InterestAmt", InterestAmt);
}
/** Get Interest Amount.
@return Interest Amount */
public BigDecimal getInterestAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("InterestAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name InterestAmt */
public static final String COLUMNNAME_InterestAmt = "InterestAmt";
/** Set Invoice Document No.
@param InvoiceDocumentNo Document Number of the Invoice */
public void setInvoiceDocumentNo (String InvoiceDocumentNo)
{
if (InvoiceDocumentNo != null && InvoiceDocumentNo.length() > 30)
{
log.warning("Length > 30 - truncated");
InvoiceDocumentNo = InvoiceDocumentNo.substring(0,29);
}
set_Value ("InvoiceDocumentNo", InvoiceDocumentNo);
}
/** Get Invoice Document No.
@return Document Number of the Invoice */
public String getInvoiceDocumentNo() 
{
return (String)get_Value("InvoiceDocumentNo");
}
/** Column name InvoiceDocumentNo */
public static final String COLUMNNAME_InvoiceDocumentNo = "InvoiceDocumentNo";
/** Set Reversal.
@param IsReversal This is a reversing transaction */
public void setIsReversal (boolean IsReversal)
{
set_Value ("IsReversal", Boolean.valueOf(IsReversal));
}
/** Get Reversal.
@return This is a reversing transaction */
public boolean isReversal() 
{
Object oo = get_Value("IsReversal");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsReversal */
public static final String COLUMNNAME_IsReversal = "IsReversal";
/** Set Line No.
@param Line Unique line for this document */
public void setLine (int Line)
{
set_Value ("Line", Integer.valueOf(Line));
}
/** Get Line No.
@return Unique line for this document */
public int getLine() 
{
Integer ii = (Integer)get_Value("Line");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Line */
public static final String COLUMNNAME_Line = "Line";
/** Set Line Description.
@param LineDescription Description of the Line */
public void setLineDescription (String LineDescription)
{
if (LineDescription != null && LineDescription.length() > 255)
{
log.warning("Length > 255 - truncated");
LineDescription = LineDescription.substring(0,254);
}
set_Value ("LineDescription", LineDescription);
}
/** Get Line Description.
@return Description of the Line */
public String getLineDescription() 
{
return (String)get_Value("LineDescription");
}
/** Column name LineDescription */
public static final String COLUMNNAME_LineDescription = "LineDescription";
/** Set Match Statement.
@param MatchStatement Match Statement */
public void setMatchStatement (String MatchStatement)
{
if (MatchStatement != null && MatchStatement.length() > 1)
{
log.warning("Length > 1 - truncated");
MatchStatement = MatchStatement.substring(0,0);
}
set_Value ("MatchStatement", MatchStatement);
}
/** Get Match Statement.
@return Match Statement */
public String getMatchStatement() 
{
return (String)get_Value("MatchStatement");
}
/** Column name MatchStatement */
public static final String COLUMNNAME_MatchStatement = "MatchStatement";
/** Set Memo.
@param Memo Memo Text */
public void setMemo (String Memo)
{
if (Memo != null && Memo.length() > 255)
{
log.warning("Length > 255 - truncated");
Memo = Memo.substring(0,254);
}
set_Value ("Memo", Memo);
}
/** Get Memo.
@return Memo Text */
public String getMemo() 
{
return (String)get_Value("Memo");
}
/** Column name Memo */
public static final String COLUMNNAME_Memo = "Memo";
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name != null && Name.length() > 60)
{
log.warning("Length > 60 - truncated");
Name = Name.substring(0,59);
}
set_Value ("Name", Name);
}
/** Get Name.
@return Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
}
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Payment Document No.
@param PaymentDocumentNo Document number of the Payment */
public void setPaymentDocumentNo (String PaymentDocumentNo)
{
if (PaymentDocumentNo != null && PaymentDocumentNo.length() > 30)
{
log.warning("Length > 30 - truncated");
PaymentDocumentNo = PaymentDocumentNo.substring(0,29);
}
set_Value ("PaymentDocumentNo", PaymentDocumentNo);
}
/** Get Payment Document No.
@return Document number of the Payment */
public String getPaymentDocumentNo() 
{
return (String)get_Value("PaymentDocumentNo");
}
/** Column name PaymentDocumentNo */
public static final String COLUMNNAME_PaymentDocumentNo = "PaymentDocumentNo";
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", Boolean.valueOf(Processed));
}
/** Get Processed.
@return The document has been processed */
public boolean isProcessed() 
{
Object oo = get_Value("Processed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Processed */
public static final String COLUMNNAME_Processed = "Processed";
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", Boolean.valueOf(Processing));
}
/** Get Process Now.
@return Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Processing */
public static final String COLUMNNAME_Processing = "Processing";
/** Set Reference No.
@param ReferenceNo Your customer or vendor number at the Business Partner's site */
public void setReferenceNo (String ReferenceNo)
{
if (ReferenceNo != null && ReferenceNo.length() > 40)
{
log.warning("Length > 40 - truncated");
ReferenceNo = ReferenceNo.substring(0,39);
}
set_Value ("ReferenceNo", ReferenceNo);
}
/** Get Reference No.
@return Your customer or vendor number at the Business Partner's site */
public String getReferenceNo() 
{
return (String)get_Value("ReferenceNo");
}
/** Column name ReferenceNo */
public static final String COLUMNNAME_ReferenceNo = "ReferenceNo";
/** Set Routing No.
@param RoutingNo Bank Routing Number */
public void setRoutingNo (String RoutingNo)
{
if (RoutingNo != null && RoutingNo.length() > 20)
{
log.warning("Length > 20 - truncated");
RoutingNo = RoutingNo.substring(0,19);
}
set_Value ("RoutingNo", RoutingNo);
}
/** Get Routing No.
@return Bank Routing Number */
public String getRoutingNo() 
{
return (String)get_Value("RoutingNo");
}
/** Column name RoutingNo */
public static final String COLUMNNAME_RoutingNo = "RoutingNo";
/** Set Statement date.
@param StatementDate Date of the statement */
public void setStatementDate (Timestamp StatementDate)
{
set_Value ("StatementDate", StatementDate);
}
/** Get Statement date.
@return Date of the statement */
public Timestamp getStatementDate() 
{
return (Timestamp)get_Value("StatementDate");
}
/** Column name StatementDate */
public static final String COLUMNNAME_StatementDate = "StatementDate";
/** Set Statement Line Date.
@param StatementLineDate Date of the Statement Line */
public void setStatementLineDate (Timestamp StatementLineDate)
{
set_Value ("StatementLineDate", StatementLineDate);
}
/** Get Statement Line Date.
@return Date of the Statement Line */
public Timestamp getStatementLineDate() 
{
return (Timestamp)get_Value("StatementLineDate");
}
/** Column name StatementLineDate */
public static final String COLUMNNAME_StatementLineDate = "StatementLineDate";
/** Set Statement amount.
@param StmtAmt Statement Amount */
public void setStmtAmt (BigDecimal StmtAmt)
{
set_Value ("StmtAmt", StmtAmt);
}
/** Get Statement amount.
@return Statement Amount */
public BigDecimal getStmtAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("StmtAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name StmtAmt */
public static final String COLUMNNAME_StmtAmt = "StmtAmt";
/** Set Transaction Amount.
@param TrxAmt Amount of a transaction */
public void setTrxAmt (BigDecimal TrxAmt)
{
set_Value ("TrxAmt", TrxAmt);
}
/** Get Transaction Amount.
@return Amount of a transaction */
public BigDecimal getTrxAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("TrxAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name TrxAmt */
public static final String COLUMNNAME_TrxAmt = "TrxAmt";

/** TrxType AD_Reference_ID=215 */
public static final int TRXTYPE_AD_Reference_ID=215;
/** Authorization = A */
public static final String TRXTYPE_Authorization = "A";
/** Credit (Payment) = C */
public static final String TRXTYPE_CreditPayment = "C";
/** Delayed Capture = D */
public static final String TRXTYPE_DelayedCapture = "D";
/** Voice Authorization = F */
public static final String TRXTYPE_VoiceAuthorization = "F";
/** Sales = S */
public static final String TRXTYPE_Sales = "S";
/** Void = V */
public static final String TRXTYPE_Void = "V";
/** Set Transaction Type.
@param TrxType Type of credit card transaction */
public void setTrxType (String TrxType)
{
if (TrxType == null || TrxType.equals("A") || TrxType.equals("C") || TrxType.equals("D") || TrxType.equals("F") || TrxType.equals("S") || TrxType.equals("V"));
 else throw new IllegalArgumentException ("TrxType Invalid value - " + TrxType + " - Reference_ID=215 - A - C - D - F - S - V");
if (TrxType != null && TrxType.length() > 20)
{
log.warning("Length > 20 - truncated");
TrxType = TrxType.substring(0,19);
}
set_Value ("TrxType", TrxType);
}
/** Get Transaction Type.
@return Type of credit card transaction */
public String getTrxType() 
{
return (String)get_Value("TrxType");
}
/** Column name TrxType */
public static final String COLUMNNAME_TrxType = "TrxType";
/** Set Effective date.
@param ValutaDate Date when money is available */
public void setValutaDate (Timestamp ValutaDate)
{
set_Value ("ValutaDate", ValutaDate);
}
/** Get Effective date.
@return Date when money is available */
public Timestamp getValutaDate() 
{
return (Timestamp)get_Value("ValutaDate");
}
/** Column name ValutaDate */
public static final String COLUMNNAME_ValutaDate = "ValutaDate";
}
