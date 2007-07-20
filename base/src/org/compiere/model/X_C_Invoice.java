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
/** Generated Model for C_Invoice
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_C_Invoice extends PO
{
/** Standard Constructor
@param ctx context
@param C_Invoice_ID id
@param trxName transaction
*/
public X_C_Invoice (Properties ctx, int C_Invoice_ID, String trxName)
{
super (ctx, C_Invoice_ID, trxName);
/** if (C_Invoice_ID == 0)
{
setC_BPartner_ID (0);
setC_BPartner_Location_ID (0);
setC_Currency_ID (0);	// @C_Currency_ID@
setC_DocTypeTarget_ID (0);
setC_DocType_ID (0);	// 0
setC_Invoice_ID (0);
setC_PaymentTerm_ID (0);
setDateAcct (new Timestamp(System.currentTimeMillis()));	// @#Date@
setDateInvoiced (new Timestamp(System.currentTimeMillis()));	// @#Date@
setDocAction (null);	// CO
setDocStatus (null);	// DR
setDocumentNo (null);
setGrandTotal (Env.ZERO);
setIsApproved (false);	// @IsApproved@
setIsDiscountPrinted (false);
setIsInDispute (false);	// N
setIsPaid (false);
setIsPayScheduleValid (false);
setIsPrinted (false);
setIsSOTrx (false);	// @IsSOTrx@
setIsSelfService (false);
setIsTaxIncluded (false);
setIsTransferred (false);
setM_PriceList_ID (0);
setPaymentRule (null);	// P
setPosted (false);	// N
setProcessed (false);
setSendEMail (false);
setTotalLines (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Invoice (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=C_Invoice */
public static final String Table_Name="C_Invoice";

/** AD_Table_ID=318 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

protected BigDecimal accessLevel = BigDecimal.valueOf(1);
/** AccessLevel
@return 1 - Org 
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
StringBuffer sb = new StringBuffer ("X_C_Invoice[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_OrgTrx_ID AD_Reference_ID=130 */
public static final int AD_ORGTRX_ID_AD_Reference_ID=130;
/** Set Trx Organization.
@param AD_OrgTrx_ID Performing or initiating organization */
public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
{
if (AD_OrgTrx_ID <= 0) set_Value ("AD_OrgTrx_ID", null);
 else 
set_Value ("AD_OrgTrx_ID", Integer.valueOf(AD_OrgTrx_ID));
}
/** Get Trx Organization.
@return Performing or initiating organization */
public int getAD_OrgTrx_ID() 
{
Integer ii = (Integer)get_Value("AD_OrgTrx_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_OrgTrx_ID */
public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID <= 0) set_Value ("AD_User_ID", null);
 else 
set_Value ("AD_User_ID", Integer.valueOf(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_User_ID */
public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";
/** Set Activity.
@param C_Activity_ID Business Activity */
public void setC_Activity_ID (int C_Activity_ID)
{
if (C_Activity_ID <= 0) set_Value ("C_Activity_ID", null);
 else 
set_Value ("C_Activity_ID", Integer.valueOf(C_Activity_ID));
}
/** Get Activity.
@return Business Activity */
public int getC_Activity_ID() 
{
Integer ii = (Integer)get_Value("C_Activity_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Activity_ID */
public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID < 1) throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
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
/** Set Partner Location.
@param C_BPartner_Location_ID Identifies the (ship to) address for this Business Partner */
public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
{
if (C_BPartner_Location_ID < 1) throw new IllegalArgumentException ("C_BPartner_Location_ID is mandatory.");
set_Value ("C_BPartner_Location_ID", Integer.valueOf(C_BPartner_Location_ID));
}
/** Get Partner Location.
@return Identifies the (ship to) address for this Business Partner */
public int getC_BPartner_Location_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_Location_ID */
public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";
/** Set Campaign.
@param C_Campaign_ID Marketing Campaign */
public void setC_Campaign_ID (int C_Campaign_ID)
{
if (C_Campaign_ID <= 0) set_Value ("C_Campaign_ID", null);
 else 
set_Value ("C_Campaign_ID", Integer.valueOf(C_Campaign_ID));
}
/** Get Campaign.
@return Marketing Campaign */
public int getC_Campaign_ID() 
{
Integer ii = (Integer)get_Value("C_Campaign_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Campaign_ID */
public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";
/** Set Cash Journal Line.
@param C_CashLine_ID Cash Journal Line */
public void setC_CashLine_ID (int C_CashLine_ID)
{
if (C_CashLine_ID <= 0) set_Value ("C_CashLine_ID", null);
 else 
set_Value ("C_CashLine_ID", Integer.valueOf(C_CashLine_ID));
}
/** Get Cash Journal Line.
@return Cash Journal Line */
public int getC_CashLine_ID() 
{
Integer ii = (Integer)get_Value("C_CashLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_CashLine_ID */
public static final String COLUMNNAME_C_CashLine_ID = "C_CashLine_ID";

/** C_Charge_ID AD_Reference_ID=200 */
public static final int C_CHARGE_ID_AD_Reference_ID=200;
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
/** Set Currency Type.
@param C_ConversionType_ID Currency Conversion Rate Type */
public void setC_ConversionType_ID (int C_ConversionType_ID)
{
if (C_ConversionType_ID <= 0) set_Value ("C_ConversionType_ID", null);
 else 
set_Value ("C_ConversionType_ID", Integer.valueOf(C_ConversionType_ID));
}
/** Get Currency Type.
@return Currency Conversion Rate Type */
public int getC_ConversionType_ID() 
{
Integer ii = (Integer)get_Value("C_ConversionType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ConversionType_ID */
public static final String COLUMNNAME_C_ConversionType_ID = "C_ConversionType_ID";
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID < 1) throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
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

/** C_DocTypeTarget_ID AD_Reference_ID=170 */
public static final int C_DOCTYPETARGET_ID_AD_Reference_ID=170;
/** Set Target Document Type.
@param C_DocTypeTarget_ID Target document type for conversing documents */
public void setC_DocTypeTarget_ID (int C_DocTypeTarget_ID)
{
if (C_DocTypeTarget_ID < 1) throw new IllegalArgumentException ("C_DocTypeTarget_ID is mandatory.");
set_Value ("C_DocTypeTarget_ID", Integer.valueOf(C_DocTypeTarget_ID));
}
/** Get Target Document Type.
@return Target document type for conversing documents */
public int getC_DocTypeTarget_ID() 
{
Integer ii = (Integer)get_Value("C_DocTypeTarget_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_DocTypeTarget_ID */
public static final String COLUMNNAME_C_DocTypeTarget_ID = "C_DocTypeTarget_ID";
/** Set Document Type.
@param C_DocType_ID Document type or rules */
public void setC_DocType_ID (int C_DocType_ID)
{
if (C_DocType_ID < 0) throw new IllegalArgumentException ("C_DocType_ID is mandatory.");
set_ValueNoCheck ("C_DocType_ID", Integer.valueOf(C_DocType_ID));
}
/** Get Document Type.
@return Document type or rules */
public int getC_DocType_ID() 
{
Integer ii = (Integer)get_Value("C_DocType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_DocType_ID */
public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";
/** Set Invoice.
@param C_Invoice_ID Invoice Identifier */
public void setC_Invoice_ID (int C_Invoice_ID)
{
if (C_Invoice_ID < 1) throw new IllegalArgumentException ("C_Invoice_ID is mandatory.");
set_ValueNoCheck ("C_Invoice_ID", Integer.valueOf(C_Invoice_ID));
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
/** Set Order.
@param C_Order_ID Order */
public void setC_Order_ID (int C_Order_ID)
{
if (C_Order_ID <= 0) set_ValueNoCheck ("C_Order_ID", null);
 else 
set_ValueNoCheck ("C_Order_ID", Integer.valueOf(C_Order_ID));
}
/** Get Order.
@return Order */
public int getC_Order_ID() 
{
Integer ii = (Integer)get_Value("C_Order_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Order_ID */
public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";
/** Set Payment Term.
@param C_PaymentTerm_ID The terms of Payment (timing, discount) */
public void setC_PaymentTerm_ID (int C_PaymentTerm_ID)
{
if (C_PaymentTerm_ID < 1) throw new IllegalArgumentException ("C_PaymentTerm_ID is mandatory.");
set_Value ("C_PaymentTerm_ID", Integer.valueOf(C_PaymentTerm_ID));
}
/** Get Payment Term.
@return The terms of Payment (timing, discount) */
public int getC_PaymentTerm_ID() 
{
Integer ii = (Integer)get_Value("C_PaymentTerm_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_PaymentTerm_ID */
public static final String COLUMNNAME_C_PaymentTerm_ID = "C_PaymentTerm_ID";
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
/** Set Project.
@param C_Project_ID Financial Project */
public void setC_Project_ID (int C_Project_ID)
{
if (C_Project_ID <= 0) set_Value ("C_Project_ID", null);
 else 
set_Value ("C_Project_ID", Integer.valueOf(C_Project_ID));
}
/** Get Project.
@return Financial Project */
public int getC_Project_ID() 
{
Integer ii = (Integer)get_Value("C_Project_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Project_ID */
public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";
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
/** Set Copy From.
@param CopyFrom Copy From Record */
public void setCopyFrom (String CopyFrom)
{
if (CopyFrom != null && CopyFrom.length() > 1)
{
log.warning("Length > 1 - truncated");
CopyFrom = CopyFrom.substring(0,0);
}
set_Value ("CopyFrom", CopyFrom);
}
/** Get Copy From.
@return Copy From Record */
public String getCopyFrom() 
{
return (String)get_Value("CopyFrom");
}
/** Column name CopyFrom */
public static final String COLUMNNAME_CopyFrom = "CopyFrom";
/** Set Create lines from.
@param CreateFrom Process which will generate a new document lines based on an existing document */
public void setCreateFrom (String CreateFrom)
{
if (CreateFrom != null && CreateFrom.length() > 1)
{
log.warning("Length > 1 - truncated");
CreateFrom = CreateFrom.substring(0,0);
}
set_Value ("CreateFrom", CreateFrom);
}
/** Get Create lines from.
@return Process which will generate a new document lines based on an existing document */
public String getCreateFrom() 
{
return (String)get_Value("CreateFrom");
}
/** Column name CreateFrom */
public static final String COLUMNNAME_CreateFrom = "CreateFrom";
/** Set Account Date.
@param DateAcct Accounting Date */
public void setDateAcct (Timestamp DateAcct)
{
if (DateAcct == null) throw new IllegalArgumentException ("DateAcct is mandatory.");
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
/** Set Date Invoiced.
@param DateInvoiced Date printed on Invoice */
public void setDateInvoiced (Timestamp DateInvoiced)
{
if (DateInvoiced == null) throw new IllegalArgumentException ("DateInvoiced is mandatory.");
set_Value ("DateInvoiced", DateInvoiced);
}
/** Get Date Invoiced.
@return Date printed on Invoice */
public Timestamp getDateInvoiced() 
{
return (Timestamp)get_Value("DateInvoiced");
}
/** Column name DateInvoiced */
public static final String COLUMNNAME_DateInvoiced = "DateInvoiced";
/** Set Date Ordered.
@param DateOrdered Date of Order */
public void setDateOrdered (Timestamp DateOrdered)
{
set_ValueNoCheck ("DateOrdered", DateOrdered);
}
/** Get Date Ordered.
@return Date of Order */
public Timestamp getDateOrdered() 
{
return (Timestamp)get_Value("DateOrdered");
}
/** Column name DateOrdered */
public static final String COLUMNNAME_DateOrdered = "DateOrdered";
/** Set Date printed.
@param DatePrinted Date the document was printed. */
public void setDatePrinted (Timestamp DatePrinted)
{
set_Value ("DatePrinted", DatePrinted);
}
/** Get Date printed.
@return Date the document was printed. */
public Timestamp getDatePrinted() 
{
return (Timestamp)get_Value("DatePrinted");
}
/** Column name DatePrinted */
public static final String COLUMNNAME_DatePrinted = "DatePrinted";
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

/** DocAction AD_Reference_ID=135 */
public static final int DOCACTION_AD_Reference_ID=135;
/** Complete = CO */
public static final String DOCACTION_Complete = "CO";
/** Approve = AP */
public static final String DOCACTION_Approve = "AP";
/** Reject = RJ */
public static final String DOCACTION_Reject = "RJ";
/** Post = PO */
public static final String DOCACTION_Post = "PO";
/** Void = VO */
public static final String DOCACTION_Void = "VO";
/** Close = CL */
public static final String DOCACTION_Close = "CL";
/** Reverse - Correct = RC */
public static final String DOCACTION_Reverse_Correct = "RC";
/** Reverse - Accrual = RA */
public static final String DOCACTION_Reverse_Accrual = "RA";
/** Invalidate = IN */
public static final String DOCACTION_Invalidate = "IN";
/** Re-activate = RE */
public static final String DOCACTION_Re_Activate = "RE";
/** <None> = -- */
public static final String DOCACTION_None = "--";
/** Wait Complete = WC */
public static final String DOCACTION_WaitComplete = "WC";
/** Prepare = PR */
public static final String DOCACTION_Prepare = "PR";
/** Unlock = XL */
public static final String DOCACTION_Unlock = "XL";
/** Set Document Action.
@param DocAction The targeted status of the document */
public void setDocAction (String DocAction)
{
if (DocAction == null) throw new IllegalArgumentException ("DocAction is mandatory");
if (DocAction.equals("CO") || DocAction.equals("AP") || DocAction.equals("RJ") || DocAction.equals("PO") || DocAction.equals("VO") || DocAction.equals("CL") || DocAction.equals("RC") || DocAction.equals("RA") || DocAction.equals("IN") || DocAction.equals("RE") || DocAction.equals("--") || DocAction.equals("WC") || DocAction.equals("PR") || DocAction.equals("XL"));
 else throw new IllegalArgumentException ("DocAction Invalid value - " + DocAction + " - Reference_ID=135 - CO - AP - RJ - PO - VO - CL - RC - RA - IN - RE - -- - WC - PR - XL");
if (DocAction.length() > 2)
{
log.warning("Length > 2 - truncated");
DocAction = DocAction.substring(0,1);
}
set_Value ("DocAction", DocAction);
}
/** Get Document Action.
@return The targeted status of the document */
public String getDocAction() 
{
return (String)get_Value("DocAction");
}
/** Column name DocAction */
public static final String COLUMNNAME_DocAction = "DocAction";

/** DocStatus AD_Reference_ID=131 */
public static final int DOCSTATUS_AD_Reference_ID=131;
/** Waiting Confirmation = WC */
public static final String DOCSTATUS_WaitingConfirmation = "WC";
/** In Progress = IP */
public static final String DOCSTATUS_InProgress = "IP";
/** Drafted = DR */
public static final String DOCSTATUS_Drafted = "DR";
/** Completed = CO */
public static final String DOCSTATUS_Completed = "CO";
/** Approved = AP */
public static final String DOCSTATUS_Approved = "AP";
/** Not Approved = NA */
public static final String DOCSTATUS_NotApproved = "NA";
/** Voided = VO */
public static final String DOCSTATUS_Voided = "VO";
/** Invalid = IN */
public static final String DOCSTATUS_Invalid = "IN";
/** Reversed = RE */
public static final String DOCSTATUS_Reversed = "RE";
/** Closed = CL */
public static final String DOCSTATUS_Closed = "CL";
/** Unknown = ?? */
public static final String DOCSTATUS_Unknown = "??";
/** Waiting Payment = WP */
public static final String DOCSTATUS_WaitingPayment = "WP";
/** Set Document Status.
@param DocStatus The current status of the document */
public void setDocStatus (String DocStatus)
{
if (DocStatus == null) throw new IllegalArgumentException ("DocStatus is mandatory");
if (DocStatus.equals("WC") || DocStatus.equals("IP") || DocStatus.equals("DR") || DocStatus.equals("CO") || DocStatus.equals("AP") || DocStatus.equals("NA") || DocStatus.equals("VO") || DocStatus.equals("IN") || DocStatus.equals("RE") || DocStatus.equals("CL") || DocStatus.equals("??") || DocStatus.equals("WP"));
 else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=131 - WC - IP - DR - CO - AP - NA - VO - IN - RE - CL - ?? - WP");
if (DocStatus.length() > 2)
{
log.warning("Length > 2 - truncated");
DocStatus = DocStatus.substring(0,1);
}
set_Value ("DocStatus", DocStatus);
}
/** Get Document Status.
@return The current status of the document */
public String getDocStatus() 
{
return (String)get_Value("DocStatus");
}
/** Column name DocStatus */
public static final String COLUMNNAME_DocStatus = "DocStatus";
/** Set Document No.
@param DocumentNo Document sequence number of the document */
public void setDocumentNo (String DocumentNo)
{
if (DocumentNo == null) throw new IllegalArgumentException ("DocumentNo is mandatory.");
if (DocumentNo.length() > 30)
{
log.warning("Length > 30 - truncated");
DocumentNo = DocumentNo.substring(0,29);
}
set_ValueNoCheck ("DocumentNo", DocumentNo);
}
/** Get Document No.
@return Document sequence number of the document */
public String getDocumentNo() 
{
return (String)get_Value("DocumentNo");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getDocumentNo());
}
/** Column name DocumentNo */
public static final String COLUMNNAME_DocumentNo = "DocumentNo";
/** Set Generate To.
@param GenerateTo Generate To */
public void setGenerateTo (String GenerateTo)
{
if (GenerateTo != null && GenerateTo.length() > 1)
{
log.warning("Length > 1 - truncated");
GenerateTo = GenerateTo.substring(0,0);
}
set_Value ("GenerateTo", GenerateTo);
}
/** Get Generate To.
@return Generate To */
public String getGenerateTo() 
{
return (String)get_Value("GenerateTo");
}
/** Column name GenerateTo */
public static final String COLUMNNAME_GenerateTo = "GenerateTo";
/** Set Grand Total.
@param GrandTotal Total amount of document */
public void setGrandTotal (BigDecimal GrandTotal)
{
if (GrandTotal == null) throw new IllegalArgumentException ("GrandTotal is mandatory.");
set_ValueNoCheck ("GrandTotal", GrandTotal);
}
/** Get Grand Total.
@return Total amount of document */
public BigDecimal getGrandTotal() 
{
BigDecimal bd = (BigDecimal)get_Value("GrandTotal");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name GrandTotal */
public static final String COLUMNNAME_GrandTotal = "GrandTotal";

/** InvoiceCollectionType AD_Reference_ID=394 */
public static final int INVOICECOLLECTIONTYPE_AD_Reference_ID=394;
/** Dunning = D */
public static final String INVOICECOLLECTIONTYPE_Dunning = "D";
/** Collection Agency = C */
public static final String INVOICECOLLECTIONTYPE_CollectionAgency = "C";
/** Legal Procedure = L */
public static final String INVOICECOLLECTIONTYPE_LegalProcedure = "L";
/** Uncollectable = U */
public static final String INVOICECOLLECTIONTYPE_Uncollectable = "U";
/** Set Collection Status.
@param InvoiceCollectionType Invoice Collection Status */
public void setInvoiceCollectionType (String InvoiceCollectionType)
{
if (InvoiceCollectionType == null || InvoiceCollectionType.equals("D") || InvoiceCollectionType.equals("C") || InvoiceCollectionType.equals("L") || InvoiceCollectionType.equals("U"));
 else throw new IllegalArgumentException ("InvoiceCollectionType Invalid value - " + InvoiceCollectionType + " - Reference_ID=394 - D - C - L - U");
if (InvoiceCollectionType != null && InvoiceCollectionType.length() > 1)
{
log.warning("Length > 1 - truncated");
InvoiceCollectionType = InvoiceCollectionType.substring(0,0);
}
set_Value ("InvoiceCollectionType", InvoiceCollectionType);
}
/** Get Collection Status.
@return Invoice Collection Status */
public String getInvoiceCollectionType() 
{
return (String)get_Value("InvoiceCollectionType");
}
/** Column name InvoiceCollectionType */
public static final String COLUMNNAME_InvoiceCollectionType = "InvoiceCollectionType";
/** Set Approved.
@param IsApproved Indicates if this document requires approval */
public void setIsApproved (boolean IsApproved)
{
set_ValueNoCheck ("IsApproved", Boolean.valueOf(IsApproved));
}
/** Get Approved.
@return Indicates if this document requires approval */
public boolean isApproved() 
{
Object oo = get_Value("IsApproved");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsApproved */
public static final String COLUMNNAME_IsApproved = "IsApproved";
/** Set Discount Printed.
@param IsDiscountPrinted Print Discount on Invoice and Order */
public void setIsDiscountPrinted (boolean IsDiscountPrinted)
{
set_Value ("IsDiscountPrinted", Boolean.valueOf(IsDiscountPrinted));
}
/** Get Discount Printed.
@return Print Discount on Invoice and Order */
public boolean isDiscountPrinted() 
{
Object oo = get_Value("IsDiscountPrinted");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsDiscountPrinted */
public static final String COLUMNNAME_IsDiscountPrinted = "IsDiscountPrinted";
/** Set In Dispute.
@param IsInDispute Document is in dispute */
public void setIsInDispute (boolean IsInDispute)
{
set_Value ("IsInDispute", Boolean.valueOf(IsInDispute));
}
/** Get In Dispute.
@return Document is in dispute */
public boolean isInDispute() 
{
Object oo = get_Value("IsInDispute");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsInDispute */
public static final String COLUMNNAME_IsInDispute = "IsInDispute";
/** Set Paid.
@param IsPaid The document is paid */
public void setIsPaid (boolean IsPaid)
{
set_Value ("IsPaid", Boolean.valueOf(IsPaid));
}
/** Get Paid.
@return The document is paid */
public boolean isPaid() 
{
Object oo = get_Value("IsPaid");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPaid */
public static final String COLUMNNAME_IsPaid = "IsPaid";
/** Set Pay Schedule valid.
@param IsPayScheduleValid Is the Payment Schedule is valid */
public void setIsPayScheduleValid (boolean IsPayScheduleValid)
{
set_ValueNoCheck ("IsPayScheduleValid", Boolean.valueOf(IsPayScheduleValid));
}
/** Get Pay Schedule valid.
@return Is the Payment Schedule is valid */
public boolean isPayScheduleValid() 
{
Object oo = get_Value("IsPayScheduleValid");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPayScheduleValid */
public static final String COLUMNNAME_IsPayScheduleValid = "IsPayScheduleValid";
/** Set Printed.
@param IsPrinted Indicates if this document / line is printed */
public void setIsPrinted (boolean IsPrinted)
{
set_ValueNoCheck ("IsPrinted", Boolean.valueOf(IsPrinted));
}
/** Get Printed.
@return Indicates if this document / line is printed */
public boolean isPrinted() 
{
Object oo = get_Value("IsPrinted");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPrinted */
public static final String COLUMNNAME_IsPrinted = "IsPrinted";
/** Set Sales Transaction.
@param IsSOTrx This is a Sales Transaction */
public void setIsSOTrx (boolean IsSOTrx)
{
set_ValueNoCheck ("IsSOTrx", Boolean.valueOf(IsSOTrx));
}
/** Get Sales Transaction.
@return This is a Sales Transaction */
public boolean isSOTrx() 
{
Object oo = get_Value("IsSOTrx");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSOTrx */
public static final String COLUMNNAME_IsSOTrx = "IsSOTrx";
/** Set Self-Service.
@param IsSelfService This is a Self-Service entry or this entry can be changed via Self-Service */
public void setIsSelfService (boolean IsSelfService)
{
set_Value ("IsSelfService", Boolean.valueOf(IsSelfService));
}
/** Get Self-Service.
@return This is a Self-Service entry or this entry can be changed via Self-Service */
public boolean isSelfService() 
{
Object oo = get_Value("IsSelfService");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSelfService */
public static final String COLUMNNAME_IsSelfService = "IsSelfService";
/** Set Price includes Tax.
@param IsTaxIncluded Tax is included in the price  */
public void setIsTaxIncluded (boolean IsTaxIncluded)
{
set_Value ("IsTaxIncluded", Boolean.valueOf(IsTaxIncluded));
}
/** Get Price includes Tax.
@return Tax is included in the price  */
public boolean isTaxIncluded() 
{
Object oo = get_Value("IsTaxIncluded");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsTaxIncluded */
public static final String COLUMNNAME_IsTaxIncluded = "IsTaxIncluded";
/** Set Transferred.
@param IsTransferred Transferred to General Ledger (i.e. accounted) */
public void setIsTransferred (boolean IsTransferred)
{
set_ValueNoCheck ("IsTransferred", Boolean.valueOf(IsTransferred));
}
/** Get Transferred.
@return Transferred to General Ledger (i.e. accounted) */
public boolean isTransferred() 
{
Object oo = get_Value("IsTransferred");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsTransferred */
public static final String COLUMNNAME_IsTransferred = "IsTransferred";
/** Set Price List.
@param M_PriceList_ID Unique identifier of a Price List */
public void setM_PriceList_ID (int M_PriceList_ID)
{
if (M_PriceList_ID < 1) throw new IllegalArgumentException ("M_PriceList_ID is mandatory.");
set_Value ("M_PriceList_ID", Integer.valueOf(M_PriceList_ID));
}
/** Get Price List.
@return Unique identifier of a Price List */
public int getM_PriceList_ID() 
{
Integer ii = (Integer)get_Value("M_PriceList_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_PriceList_ID */
public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";
/** Set RMA.
@param M_RMA_ID Return Material Authorization */
public void setM_RMA_ID (int M_RMA_ID)
{
if (M_RMA_ID <= 0) set_Value ("M_RMA_ID", null);
 else 
set_Value ("M_RMA_ID", Integer.valueOf(M_RMA_ID));
}
/** Get RMA.
@return Return Material Authorization */
public int getM_RMA_ID() 
{
Integer ii = (Integer)get_Value("M_RMA_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_RMA_ID */
public static final String COLUMNNAME_M_RMA_ID = "M_RMA_ID";
/** Set Order Reference.
@param POReference Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner */
public void setPOReference (String POReference)
{
if (POReference != null && POReference.length() > 20)
{
log.warning("Length > 20 - truncated");
POReference = POReference.substring(0,19);
}
set_Value ("POReference", POReference);
}
/** Get Order Reference.
@return Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner */
public String getPOReference() 
{
return (String)get_Value("POReference");
}
/** Column name POReference */
public static final String COLUMNNAME_POReference = "POReference";

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
/** Set Payment Rule.
@param PaymentRule How you pay the invoice */
public void setPaymentRule (String PaymentRule)
{
if (PaymentRule == null) throw new IllegalArgumentException ("PaymentRule is mandatory");
if (PaymentRule.equals("B") || PaymentRule.equals("K") || PaymentRule.equals("T") || PaymentRule.equals("S") || PaymentRule.equals("P") || PaymentRule.equals("D"));
 else throw new IllegalArgumentException ("PaymentRule Invalid value - " + PaymentRule + " - Reference_ID=195 - B - K - T - S - P - D");
if (PaymentRule.length() > 1)
{
log.warning("Length > 1 - truncated");
PaymentRule = PaymentRule.substring(0,0);
}
set_Value ("PaymentRule", PaymentRule);
}
/** Get Payment Rule.
@return How you pay the invoice */
public String getPaymentRule() 
{
return (String)get_Value("PaymentRule");
}
/** Column name PaymentRule */
public static final String COLUMNNAME_PaymentRule = "PaymentRule";
/** Set Posted.
@param Posted Posting status */
public void setPosted (boolean Posted)
{
set_Value ("Posted", Boolean.valueOf(Posted));
}
/** Get Posted.
@return Posting status */
public boolean isPosted() 
{
Object oo = get_Value("Posted");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Posted */
public static final String COLUMNNAME_Posted = "Posted";
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_ValueNoCheck ("Processed", Boolean.valueOf(Processed));
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
/** Set Referenced Invoice.
@param Ref_Invoice_ID Referenced Invoice */
public void setRef_Invoice_ID (int Ref_Invoice_ID)
{
if (Ref_Invoice_ID <= 0) set_Value ("Ref_Invoice_ID", null);
 else 
set_Value ("Ref_Invoice_ID", Integer.valueOf(Ref_Invoice_ID));
}
/** Get Referenced Invoice.
@return Referenced Invoice */
public int getRef_Invoice_ID() 
{
Integer ii = (Integer)get_Value("Ref_Invoice_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Ref_Invoice_ID */
public static final String COLUMNNAME_Ref_Invoice_ID = "Ref_Invoice_ID";

/** SalesRep_ID AD_Reference_ID=190 */
public static final int SALESREP_ID_AD_Reference_ID=190;
/** Set Sales Representative.
@param SalesRep_ID Sales Representative or Company Agent */
public void setSalesRep_ID (int SalesRep_ID)
{
if (SalesRep_ID <= 0) set_Value ("SalesRep_ID", null);
 else 
set_Value ("SalesRep_ID", Integer.valueOf(SalesRep_ID));
}
/** Get Sales Representative.
@return Sales Representative or Company Agent */
public int getSalesRep_ID() 
{
Integer ii = (Integer)get_Value("SalesRep_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name SalesRep_ID */
public static final String COLUMNNAME_SalesRep_ID = "SalesRep_ID";
/** Set Send EMail.
@param SendEMail Enable sending Document EMail */
public void setSendEMail (boolean SendEMail)
{
set_Value ("SendEMail", Boolean.valueOf(SendEMail));
}
/** Get Send EMail.
@return Enable sending Document EMail */
public boolean isSendEMail() 
{
Object oo = get_Value("SendEMail");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name SendEMail */
public static final String COLUMNNAME_SendEMail = "SendEMail";
/** Set Total Lines.
@param TotalLines Total of all document lines */
public void setTotalLines (BigDecimal TotalLines)
{
if (TotalLines == null) throw new IllegalArgumentException ("TotalLines is mandatory.");
set_ValueNoCheck ("TotalLines", TotalLines);
}
/** Get Total Lines.
@return Total of all document lines */
public BigDecimal getTotalLines() 
{
BigDecimal bd = (BigDecimal)get_Value("TotalLines");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name TotalLines */
public static final String COLUMNNAME_TotalLines = "TotalLines";

/** User1_ID AD_Reference_ID=134 */
public static final int USER1_ID_AD_Reference_ID=134;
/** Set User List 1.
@param User1_ID User defined list element #1 */
public void setUser1_ID (int User1_ID)
{
if (User1_ID <= 0) set_Value ("User1_ID", null);
 else 
set_Value ("User1_ID", Integer.valueOf(User1_ID));
}
/** Get User List 1.
@return User defined list element #1 */
public int getUser1_ID() 
{
Integer ii = (Integer)get_Value("User1_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name User1_ID */
public static final String COLUMNNAME_User1_ID = "User1_ID";

/** User2_ID AD_Reference_ID=137 */
public static final int USER2_ID_AD_Reference_ID=137;
/** Set User List 2.
@param User2_ID User defined list element #2 */
public void setUser2_ID (int User2_ID)
{
if (User2_ID <= 0) set_Value ("User2_ID", null);
 else 
set_Value ("User2_ID", Integer.valueOf(User2_ID));
}
/** Get User List 2.
@return User defined list element #2 */
public int getUser2_ID() 
{
Integer ii = (Integer)get_Value("User2_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name User2_ID */
public static final String COLUMNNAME_User2_ID = "User2_ID";
}
