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
/** Generated Model for R_Request
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_R_Request extends PO
{
/** Standard Constructor
@param ctx context
@param R_Request_ID id
@param trxName transaction
*/
public X_R_Request (Properties ctx, int R_Request_ID, String trxName)
{
super (ctx, R_Request_ID, trxName);
/** if (R_Request_ID == 0)
{
setConfidentialType (null);	// C
setConfidentialTypeEntry (null);	// C
setDocumentNo (null);
setDueType (null);	// 5
setIsEscalated (false);
setIsInvoiced (false);
setIsSelfService (false);	// N
setPriority (null);	// 5
setProcessed (false);
setR_RequestType_ID (0);
setR_Request_ID (0);
setRequestAmt (Env.ZERO);
setSalesRep_ID (0);	// @AD_User_ID@
setSummary (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_R_Request (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=R_Request */
public static final String Table_Name="R_Request";

/** AD_Table_ID=417 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

protected BigDecimal accessLevel = BigDecimal.valueOf(7);
/** AccessLevel
@return 7 - System - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_R_Request[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Role.
@param AD_Role_ID Responsibility Role */
public void setAD_Role_ID (int AD_Role_ID)
{
if (AD_Role_ID <= 0) set_Value ("AD_Role_ID", null);
 else 
set_Value ("AD_Role_ID", Integer.valueOf(AD_Role_ID));
}
/** Get Role.
@return Responsibility Role */
public int getAD_Role_ID() 
{
Integer ii = (Integer)get_Value("AD_Role_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Role_ID */
public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID <= 0) set_ValueNoCheck ("AD_Table_ID", null);
 else 
set_ValueNoCheck ("AD_Table_ID", Integer.valueOf(AD_Table_ID));
}
/** Get Table.
@return Database Table information */
public int getAD_Table_ID() 
{
Integer ii = (Integer)get_Value("AD_Table_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Table_ID */
public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";
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
/** Set Asset.
@param A_Asset_ID Asset used internally or by customers */
public void setA_Asset_ID (int A_Asset_ID)
{
if (A_Asset_ID <= 0) set_Value ("A_Asset_ID", null);
 else 
set_Value ("A_Asset_ID", Integer.valueOf(A_Asset_ID));
}
/** Get Asset.
@return Asset used internally or by customers */
public int getA_Asset_ID() 
{
Integer ii = (Integer)get_Value("A_Asset_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name A_Asset_ID */
public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";
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

/** C_InvoiceRequest_ID AD_Reference_ID=336 */
public static final int C_INVOICEREQUEST_ID_AD_Reference_ID=336;
/** Set Request Invoice.
@param C_InvoiceRequest_ID The generated invoice for this request */
public void setC_InvoiceRequest_ID (int C_InvoiceRequest_ID)
{
if (C_InvoiceRequest_ID <= 0) set_ValueNoCheck ("C_InvoiceRequest_ID", null);
 else 
set_ValueNoCheck ("C_InvoiceRequest_ID", Integer.valueOf(C_InvoiceRequest_ID));
}
/** Get Request Invoice.
@return The generated invoice for this request */
public int getC_InvoiceRequest_ID() 
{
Integer ii = (Integer)get_Value("C_InvoiceRequest_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_InvoiceRequest_ID */
public static final String COLUMNNAME_C_InvoiceRequest_ID = "C_InvoiceRequest_ID";
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
/** Set Order.
@param C_Order_ID Order */
public void setC_Order_ID (int C_Order_ID)
{
if (C_Order_ID <= 0) set_Value ("C_Order_ID", null);
 else 
set_Value ("C_Order_ID", Integer.valueOf(C_Order_ID));
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
/** Set Close Date.
@param CloseDate Close Date */
public void setCloseDate (Timestamp CloseDate)
{
set_Value ("CloseDate", CloseDate);
}
/** Get Close Date.
@return Close Date */
public Timestamp getCloseDate() 
{
return (Timestamp)get_Value("CloseDate");
}
/** Column name CloseDate */
public static final String COLUMNNAME_CloseDate = "CloseDate";

/** ConfidentialType AD_Reference_ID=340 */
public static final int CONFIDENTIALTYPE_AD_Reference_ID=340;
/** Public Information = A */
public static final String CONFIDENTIALTYPE_PublicInformation = "A";
/** Partner Confidential = C */
public static final String CONFIDENTIALTYPE_PartnerConfidential = "C";
/** Internal = I */
public static final String CONFIDENTIALTYPE_Internal = "I";
/** Private Information = P */
public static final String CONFIDENTIALTYPE_PrivateInformation = "P";
/** Set Confidentiality.
@param ConfidentialType Type of Confidentiality */
public void setConfidentialType (String ConfidentialType)
{
if (ConfidentialType == null) throw new IllegalArgumentException ("ConfidentialType is mandatory");
if (ConfidentialType.equals("A") || ConfidentialType.equals("C") || ConfidentialType.equals("I") || ConfidentialType.equals("P"));
 else throw new IllegalArgumentException ("ConfidentialType Invalid value - " + ConfidentialType + " - Reference_ID=340 - A - C - I - P");
if (ConfidentialType.length() > 1)
{
log.warning("Length > 1 - truncated");
ConfidentialType = ConfidentialType.substring(0,0);
}
set_Value ("ConfidentialType", ConfidentialType);
}
/** Get Confidentiality.
@return Type of Confidentiality */
public String getConfidentialType() 
{
return (String)get_Value("ConfidentialType");
}
/** Column name ConfidentialType */
public static final String COLUMNNAME_ConfidentialType = "ConfidentialType";

/** ConfidentialTypeEntry AD_Reference_ID=340 */
public static final int CONFIDENTIALTYPEENTRY_AD_Reference_ID=340;
/** Public Information = A */
public static final String CONFIDENTIALTYPEENTRY_PublicInformation = "A";
/** Partner Confidential = C */
public static final String CONFIDENTIALTYPEENTRY_PartnerConfidential = "C";
/** Internal = I */
public static final String CONFIDENTIALTYPEENTRY_Internal = "I";
/** Private Information = P */
public static final String CONFIDENTIALTYPEENTRY_PrivateInformation = "P";
/** Set Entry Confidentiality.
@param ConfidentialTypeEntry Confidentiality of the individual entry */
public void setConfidentialTypeEntry (String ConfidentialTypeEntry)
{
if (ConfidentialTypeEntry == null) throw new IllegalArgumentException ("ConfidentialTypeEntry is mandatory");
if (ConfidentialTypeEntry.equals("A") || ConfidentialTypeEntry.equals("C") || ConfidentialTypeEntry.equals("I") || ConfidentialTypeEntry.equals("P"));
 else throw new IllegalArgumentException ("ConfidentialTypeEntry Invalid value - " + ConfidentialTypeEntry + " - Reference_ID=340 - A - C - I - P");
if (ConfidentialTypeEntry.length() > 1)
{
log.warning("Length > 1 - truncated");
ConfidentialTypeEntry = ConfidentialTypeEntry.substring(0,0);
}
set_Value ("ConfidentialTypeEntry", ConfidentialTypeEntry);
}
/** Get Entry Confidentiality.
@return Confidentiality of the individual entry */
public String getConfidentialTypeEntry() 
{
return (String)get_Value("ConfidentialTypeEntry");
}
/** Column name ConfidentialTypeEntry */
public static final String COLUMNNAME_ConfidentialTypeEntry = "ConfidentialTypeEntry";
/** Set Complete Plan.
@param DateCompletePlan Planned Completion Date */
public void setDateCompletePlan (Timestamp DateCompletePlan)
{
set_Value ("DateCompletePlan", DateCompletePlan);
}
/** Get Complete Plan.
@return Planned Completion Date */
public Timestamp getDateCompletePlan() 
{
return (Timestamp)get_Value("DateCompletePlan");
}
/** Column name DateCompletePlan */
public static final String COLUMNNAME_DateCompletePlan = "DateCompletePlan";
/** Set Date last action.
@param DateLastAction Date this request was last acted on */
public void setDateLastAction (Timestamp DateLastAction)
{
set_ValueNoCheck ("DateLastAction", DateLastAction);
}
/** Get Date last action.
@return Date this request was last acted on */
public Timestamp getDateLastAction() 
{
return (Timestamp)get_Value("DateLastAction");
}
/** Column name DateLastAction */
public static final String COLUMNNAME_DateLastAction = "DateLastAction";
/** Set Last Alert.
@param DateLastAlert Date when last alert were sent */
public void setDateLastAlert (Timestamp DateLastAlert)
{
set_Value ("DateLastAlert", DateLastAlert);
}
/** Get Last Alert.
@return Date when last alert were sent */
public Timestamp getDateLastAlert() 
{
return (Timestamp)get_Value("DateLastAlert");
}
/** Column name DateLastAlert */
public static final String COLUMNNAME_DateLastAlert = "DateLastAlert";
/** Set Date next action.
@param DateNextAction Date that this request should be acted on */
public void setDateNextAction (Timestamp DateNextAction)
{
set_Value ("DateNextAction", DateNextAction);
}
/** Get Date next action.
@return Date that this request should be acted on */
public Timestamp getDateNextAction() 
{
return (Timestamp)get_Value("DateNextAction");
}
/** Column name DateNextAction */
public static final String COLUMNNAME_DateNextAction = "DateNextAction";
/** Set Start Plan.
@param DateStartPlan Planned Start Date */
public void setDateStartPlan (Timestamp DateStartPlan)
{
set_Value ("DateStartPlan", DateStartPlan);
}
/** Get Start Plan.
@return Planned Start Date */
public Timestamp getDateStartPlan() 
{
return (Timestamp)get_Value("DateStartPlan");
}
/** Column name DateStartPlan */
public static final String COLUMNNAME_DateStartPlan = "DateStartPlan";
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
set_Value ("DocumentNo", DocumentNo);
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

/** DueType AD_Reference_ID=222 */
public static final int DUETYPE_AD_Reference_ID=222;
/** Overdue = 3 */
public static final String DUETYPE_Overdue = "3";
/** Due = 5 */
public static final String DUETYPE_Due = "5";
/** Scheduled = 7 */
public static final String DUETYPE_Scheduled = "7";
/** Set Due type.
@param DueType Status of the next action for this Request */
public void setDueType (String DueType)
{
if (DueType == null) throw new IllegalArgumentException ("DueType is mandatory");
if (DueType.equals("3") || DueType.equals("5") || DueType.equals("7"));
 else throw new IllegalArgumentException ("DueType Invalid value - " + DueType + " - Reference_ID=222 - 3 - 5 - 7");
if (DueType.length() > 1)
{
log.warning("Length > 1 - truncated");
DueType = DueType.substring(0,0);
}
set_Value ("DueType", DueType);
}
/** Get Due type.
@return Status of the next action for this Request */
public String getDueType() 
{
return (String)get_Value("DueType");
}
/** Column name DueType */
public static final String COLUMNNAME_DueType = "DueType";
/** Set End Time.
@param EndTime End of the time span */
public void setEndTime (Timestamp EndTime)
{
set_Value ("EndTime", EndTime);
}
/** Get End Time.
@return End of the time span */
public Timestamp getEndTime() 
{
return (Timestamp)get_Value("EndTime");
}
/** Column name EndTime */
public static final String COLUMNNAME_EndTime = "EndTime";
/** Set Escalated.
@param IsEscalated This request has been escalated */
public void setIsEscalated (boolean IsEscalated)
{
set_Value ("IsEscalated", Boolean.valueOf(IsEscalated));
}
/** Get Escalated.
@return This request has been escalated */
public boolean isEscalated() 
{
Object oo = get_Value("IsEscalated");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsEscalated */
public static final String COLUMNNAME_IsEscalated = "IsEscalated";
/** Set Invoiced.
@param IsInvoiced Is this invoiced? */
public void setIsInvoiced (boolean IsInvoiced)
{
set_Value ("IsInvoiced", Boolean.valueOf(IsInvoiced));
}
/** Get Invoiced.
@return Is this invoiced? */
public boolean isInvoiced() 
{
Object oo = get_Value("IsInvoiced");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsInvoiced */
public static final String COLUMNNAME_IsInvoiced = "IsInvoiced";
/** Set Self-Service.
@param IsSelfService This is a Self-Service entry or this entry can be changed via Self-Service */
public void setIsSelfService (boolean IsSelfService)
{
set_ValueNoCheck ("IsSelfService", Boolean.valueOf(IsSelfService));
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
/** Set Last Result.
@param LastResult Result of last contact */
public void setLastResult (String LastResult)
{
if (LastResult != null && LastResult.length() > 2000)
{
log.warning("Length > 2000 - truncated");
LastResult = LastResult.substring(0,1999);
}
set_Value ("LastResult", LastResult);
}
/** Get Last Result.
@return Result of last contact */
public String getLastResult() 
{
return (String)get_Value("LastResult");
}
/** Column name LastResult */
public static final String COLUMNNAME_LastResult = "LastResult";
/** Set Change Request.
@param M_ChangeRequest_ID BOM (Engineering) Change Request */
public void setM_ChangeRequest_ID (int M_ChangeRequest_ID)
{
if (M_ChangeRequest_ID <= 0) set_Value ("M_ChangeRequest_ID", null);
 else 
set_Value ("M_ChangeRequest_ID", Integer.valueOf(M_ChangeRequest_ID));
}
/** Get Change Request.
@return BOM (Engineering) Change Request */
public int getM_ChangeRequest_ID() 
{
Integer ii = (Integer)get_Value("M_ChangeRequest_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_ChangeRequest_ID */
public static final String COLUMNNAME_M_ChangeRequest_ID = "M_ChangeRequest_ID";

/** M_FixChangeNotice_ID AD_Reference_ID=351 */
public static final int M_FIXCHANGENOTICE_ID_AD_Reference_ID=351;
/** Set Fixed in.
@param M_FixChangeNotice_ID Fixed in Change Notice */
public void setM_FixChangeNotice_ID (int M_FixChangeNotice_ID)
{
if (M_FixChangeNotice_ID <= 0) set_Value ("M_FixChangeNotice_ID", null);
 else 
set_Value ("M_FixChangeNotice_ID", Integer.valueOf(M_FixChangeNotice_ID));
}
/** Get Fixed in.
@return Fixed in Change Notice */
public int getM_FixChangeNotice_ID() 
{
Integer ii = (Integer)get_Value("M_FixChangeNotice_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_FixChangeNotice_ID */
public static final String COLUMNNAME_M_FixChangeNotice_ID = "M_FixChangeNotice_ID";
/** Set Shipment/Receipt.
@param M_InOut_ID Material Shipment Document */
public void setM_InOut_ID (int M_InOut_ID)
{
if (M_InOut_ID <= 0) set_Value ("M_InOut_ID", null);
 else 
set_Value ("M_InOut_ID", Integer.valueOf(M_InOut_ID));
}
/** Get Shipment/Receipt.
@return Material Shipment Document */
public int getM_InOut_ID() 
{
Integer ii = (Integer)get_Value("M_InOut_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_InOut_ID */
public static final String COLUMNNAME_M_InOut_ID = "M_InOut_ID";

/** M_ProductSpent_ID AD_Reference_ID=162 */
public static final int M_PRODUCTSPENT_ID_AD_Reference_ID=162;
/** Set Product Used.
@param M_ProductSpent_ID Product/Resource/Service used in Request */
public void setM_ProductSpent_ID (int M_ProductSpent_ID)
{
if (M_ProductSpent_ID <= 0) set_Value ("M_ProductSpent_ID", null);
 else 
set_Value ("M_ProductSpent_ID", Integer.valueOf(M_ProductSpent_ID));
}
/** Get Product Used.
@return Product/Resource/Service used in Request */
public int getM_ProductSpent_ID() 
{
Integer ii = (Integer)get_Value("M_ProductSpent_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_ProductSpent_ID */
public static final String COLUMNNAME_M_ProductSpent_ID = "M_ProductSpent_ID";
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_Value ("M_Product_ID", null);
 else 
set_Value ("M_Product_ID", Integer.valueOf(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Product_ID */
public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";
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

/** NextAction AD_Reference_ID=219 */
public static final int NEXTACTION_AD_Reference_ID=219;
/** Follow up = F */
public static final String NEXTACTION_FollowUp = "F";
/** None = N */
public static final String NEXTACTION_None = "N";
/** Set Next action.
@param NextAction Next Action to be taken */
public void setNextAction (String NextAction)
{
if (NextAction == null || NextAction.equals("F") || NextAction.equals("N"));
 else throw new IllegalArgumentException ("NextAction Invalid value - " + NextAction + " - Reference_ID=219 - F - N");
if (NextAction != null && NextAction.length() > 1)
{
log.warning("Length > 1 - truncated");
NextAction = NextAction.substring(0,0);
}
set_Value ("NextAction", NextAction);
}
/** Get Next action.
@return Next Action to be taken */
public String getNextAction() 
{
return (String)get_Value("NextAction");
}
/** Column name NextAction */
public static final String COLUMNNAME_NextAction = "NextAction";

/** Priority AD_Reference_ID=154 */
public static final int PRIORITY_AD_Reference_ID=154;
/** Urgent = 1 */
public static final String PRIORITY_Urgent = "1";
/** High = 3 */
public static final String PRIORITY_High = "3";
/** Medium = 5 */
public static final String PRIORITY_Medium = "5";
/** Low = 7 */
public static final String PRIORITY_Low = "7";
/** Minor = 9 */
public static final String PRIORITY_Minor = "9";
/** Set Priority.
@param Priority Indicates if this request is of a high, medium or low priority. */
public void setPriority (String Priority)
{
if (Priority == null) throw new IllegalArgumentException ("Priority is mandatory");
if (Priority.equals("1") || Priority.equals("3") || Priority.equals("5") || Priority.equals("7") || Priority.equals("9"));
 else throw new IllegalArgumentException ("Priority Invalid value - " + Priority + " - Reference_ID=154 - 1 - 3 - 5 - 7 - 9");
if (Priority.length() > 1)
{
log.warning("Length > 1 - truncated");
Priority = Priority.substring(0,0);
}
set_Value ("Priority", Priority);
}
/** Get Priority.
@return Indicates if this request is of a high, medium or low priority. */
public String getPriority() 
{
return (String)get_Value("Priority");
}
/** Column name Priority */
public static final String COLUMNNAME_Priority = "Priority";

/** PriorityUser AD_Reference_ID=154 */
public static final int PRIORITYUSER_AD_Reference_ID=154;
/** Urgent = 1 */
public static final String PRIORITYUSER_Urgent = "1";
/** High = 3 */
public static final String PRIORITYUSER_High = "3";
/** Medium = 5 */
public static final String PRIORITYUSER_Medium = "5";
/** Low = 7 */
public static final String PRIORITYUSER_Low = "7";
/** Minor = 9 */
public static final String PRIORITYUSER_Minor = "9";
/** Set User Importance.
@param PriorityUser Priority of the issue for the User */
public void setPriorityUser (String PriorityUser)
{
if (PriorityUser == null || PriorityUser.equals("1") || PriorityUser.equals("3") || PriorityUser.equals("5") || PriorityUser.equals("7") || PriorityUser.equals("9"));
 else throw new IllegalArgumentException ("PriorityUser Invalid value - " + PriorityUser + " - Reference_ID=154 - 1 - 3 - 5 - 7 - 9");
if (PriorityUser != null && PriorityUser.length() > 1)
{
log.warning("Length > 1 - truncated");
PriorityUser = PriorityUser.substring(0,0);
}
set_Value ("PriorityUser", PriorityUser);
}
/** Get User Importance.
@return Priority of the issue for the User */
public String getPriorityUser() 
{
return (String)get_Value("PriorityUser");
}
/** Column name PriorityUser */
public static final String COLUMNNAME_PriorityUser = "PriorityUser";
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
/** Set Quantity Invoiced.
@param QtyInvoiced Invoiced Quantity */
public void setQtyInvoiced (BigDecimal QtyInvoiced)
{
set_Value ("QtyInvoiced", QtyInvoiced);
}
/** Get Quantity Invoiced.
@return Invoiced Quantity */
public BigDecimal getQtyInvoiced() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyInvoiced");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtyInvoiced */
public static final String COLUMNNAME_QtyInvoiced = "QtyInvoiced";
/** Set Quantity Plan.
@param QtyPlan Planned Quantity */
public void setQtyPlan (BigDecimal QtyPlan)
{
set_Value ("QtyPlan", QtyPlan);
}
/** Get Quantity Plan.
@return Planned Quantity */
public BigDecimal getQtyPlan() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyPlan");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtyPlan */
public static final String COLUMNNAME_QtyPlan = "QtyPlan";
/** Set Quantity Used.
@param QtySpent Quantity used for this event */
public void setQtySpent (BigDecimal QtySpent)
{
set_Value ("QtySpent", QtySpent);
}
/** Get Quantity Used.
@return Quantity used for this event */
public BigDecimal getQtySpent() 
{
BigDecimal bd = (BigDecimal)get_Value("QtySpent");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtySpent */
public static final String COLUMNNAME_QtySpent = "QtySpent";
/** Set Category.
@param R_Category_ID Request Category */
public void setR_Category_ID (int R_Category_ID)
{
if (R_Category_ID <= 0) set_Value ("R_Category_ID", null);
 else 
set_Value ("R_Category_ID", Integer.valueOf(R_Category_ID));
}
/** Get Category.
@return Request Category */
public int getR_Category_ID() 
{
Integer ii = (Integer)get_Value("R_Category_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_Category_ID */
public static final String COLUMNNAME_R_Category_ID = "R_Category_ID";
/** Set Group.
@param R_Group_ID Request Group */
public void setR_Group_ID (int R_Group_ID)
{
if (R_Group_ID <= 0) set_Value ("R_Group_ID", null);
 else 
set_Value ("R_Group_ID", Integer.valueOf(R_Group_ID));
}
/** Get Group.
@return Request Group */
public int getR_Group_ID() 
{
Integer ii = (Integer)get_Value("R_Group_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_Group_ID */
public static final String COLUMNNAME_R_Group_ID = "R_Group_ID";
/** Set Mail Template.
@param R_MailText_ID Text templates for mailings */
public void setR_MailText_ID (int R_MailText_ID)
{
if (R_MailText_ID <= 0) set_Value ("R_MailText_ID", null);
 else 
set_Value ("R_MailText_ID", Integer.valueOf(R_MailText_ID));
}
/** Get Mail Template.
@return Text templates for mailings */
public int getR_MailText_ID() 
{
Integer ii = (Integer)get_Value("R_MailText_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_MailText_ID */
public static final String COLUMNNAME_R_MailText_ID = "R_MailText_ID";

/** R_RequestRelated_ID AD_Reference_ID=341 */
public static final int R_REQUESTRELATED_ID_AD_Reference_ID=341;
/** Set Related Request.
@param R_RequestRelated_ID Related Request (Master Issue, ..) */
public void setR_RequestRelated_ID (int R_RequestRelated_ID)
{
if (R_RequestRelated_ID <= 0) set_Value ("R_RequestRelated_ID", null);
 else 
set_Value ("R_RequestRelated_ID", Integer.valueOf(R_RequestRelated_ID));
}
/** Get Related Request.
@return Related Request (Master Issue, ..) */
public int getR_RequestRelated_ID() 
{
Integer ii = (Integer)get_Value("R_RequestRelated_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_RequestRelated_ID */
public static final String COLUMNNAME_R_RequestRelated_ID = "R_RequestRelated_ID";
/** Set Request Type.
@param R_RequestType_ID Type of request (e.g. Inquiry, Complaint, ..) */
public void setR_RequestType_ID (int R_RequestType_ID)
{
if (R_RequestType_ID < 1) throw new IllegalArgumentException ("R_RequestType_ID is mandatory.");
set_Value ("R_RequestType_ID", Integer.valueOf(R_RequestType_ID));
}
/** Get Request Type.
@return Type of request (e.g. Inquiry, Complaint, ..) */
public int getR_RequestType_ID() 
{
Integer ii = (Integer)get_Value("R_RequestType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_RequestType_ID */
public static final String COLUMNNAME_R_RequestType_ID = "R_RequestType_ID";
/** Set Request.
@param R_Request_ID Request from a Business Partner or Prospect */
public void setR_Request_ID (int R_Request_ID)
{
if (R_Request_ID < 1) throw new IllegalArgumentException ("R_Request_ID is mandatory.");
set_ValueNoCheck ("R_Request_ID", Integer.valueOf(R_Request_ID));
}
/** Get Request.
@return Request from a Business Partner or Prospect */
public int getR_Request_ID() 
{
Integer ii = (Integer)get_Value("R_Request_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_Request_ID */
public static final String COLUMNNAME_R_Request_ID = "R_Request_ID";
/** Set Resolution.
@param R_Resolution_ID Request Resolution */
public void setR_Resolution_ID (int R_Resolution_ID)
{
if (R_Resolution_ID <= 0) set_Value ("R_Resolution_ID", null);
 else 
set_Value ("R_Resolution_ID", Integer.valueOf(R_Resolution_ID));
}
/** Get Resolution.
@return Request Resolution */
public int getR_Resolution_ID() 
{
Integer ii = (Integer)get_Value("R_Resolution_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_Resolution_ID */
public static final String COLUMNNAME_R_Resolution_ID = "R_Resolution_ID";
/** Set Standard Response.
@param R_StandardResponse_ID Request Standard Response  */
public void setR_StandardResponse_ID (int R_StandardResponse_ID)
{
if (R_StandardResponse_ID <= 0) set_Value ("R_StandardResponse_ID", null);
 else 
set_Value ("R_StandardResponse_ID", Integer.valueOf(R_StandardResponse_ID));
}
/** Get Standard Response.
@return Request Standard Response  */
public int getR_StandardResponse_ID() 
{
Integer ii = (Integer)get_Value("R_StandardResponse_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_StandardResponse_ID */
public static final String COLUMNNAME_R_StandardResponse_ID = "R_StandardResponse_ID";
/** Set Status.
@param R_Status_ID Request Status */
public void setR_Status_ID (int R_Status_ID)
{
if (R_Status_ID <= 0) set_Value ("R_Status_ID", null);
 else 
set_Value ("R_Status_ID", Integer.valueOf(R_Status_ID));
}
/** Get Status.
@return Request Status */
public int getR_Status_ID() 
{
Integer ii = (Integer)get_Value("R_Status_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_Status_ID */
public static final String COLUMNNAME_R_Status_ID = "R_Status_ID";
/** Set Record ID.
@param Record_ID Direct internal record ID */
public void setRecord_ID (int Record_ID)
{
if (Record_ID <= 0) set_ValueNoCheck ("Record_ID", null);
 else 
set_ValueNoCheck ("Record_ID", Integer.valueOf(Record_ID));
}
/** Get Record ID.
@return Direct internal record ID */
public int getRecord_ID() 
{
Integer ii = (Integer)get_Value("Record_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Record_ID */
public static final String COLUMNNAME_Record_ID = "Record_ID";
/** Set Request Amount.
@param RequestAmt Amount associated with this request */
public void setRequestAmt (BigDecimal RequestAmt)
{
if (RequestAmt == null) throw new IllegalArgumentException ("RequestAmt is mandatory.");
set_Value ("RequestAmt", RequestAmt);
}
/** Get Request Amount.
@return Amount associated with this request */
public BigDecimal getRequestAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("RequestAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name RequestAmt */
public static final String COLUMNNAME_RequestAmt = "RequestAmt";
/** Set Result.
@param Result Result of the action taken */
public void setResult (String Result)
{
if (Result != null && Result.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Result = Result.substring(0,1999);
}
set_Value ("Result", Result);
}
/** Get Result.
@return Result of the action taken */
public String getResult() 
{
return (String)get_Value("Result");
}
/** Column name Result */
public static final String COLUMNNAME_Result = "Result";

/** SalesRep_ID AD_Reference_ID=286 */
public static final int SALESREP_ID_AD_Reference_ID=286;
/** Set Sales Representative.
@param SalesRep_ID Sales Representative or Company Agent */
public void setSalesRep_ID (int SalesRep_ID)
{
if (SalesRep_ID < 1) throw new IllegalArgumentException ("SalesRep_ID is mandatory.");
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
/** Set Start Date.
@param StartDate First effective day (inclusive) */
public void setStartDate (Timestamp StartDate)
{
set_Value ("StartDate", StartDate);
}
/** Get Start Date.
@return First effective day (inclusive) */
public Timestamp getStartDate() 
{
return (Timestamp)get_Value("StartDate");
}
/** Column name StartDate */
public static final String COLUMNNAME_StartDate = "StartDate";
/** Set Start Time.
@param StartTime Time started */
public void setStartTime (Timestamp StartTime)
{
set_Value ("StartTime", StartTime);
}
/** Get Start Time.
@return Time started */
public Timestamp getStartTime() 
{
return (Timestamp)get_Value("StartTime");
}
/** Column name StartTime */
public static final String COLUMNNAME_StartTime = "StartTime";
/** Set Summary.
@param Summary Textual summary of this request */
public void setSummary (String Summary)
{
if (Summary == null) throw new IllegalArgumentException ("Summary is mandatory.");
if (Summary.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Summary = Summary.substring(0,1999);
}
set_Value ("Summary", Summary);
}
/** Get Summary.
@return Textual summary of this request */
public String getSummary() 
{
return (String)get_Value("Summary");
}
/** Column name Summary */
public static final String COLUMNNAME_Summary = "Summary";

/** TaskStatus AD_Reference_ID=366 */
public static final int TASKSTATUS_AD_Reference_ID=366;
/**  0% Not Started = 0 */
public static final String TASKSTATUS_0NotStarted = "0";
/**  20% Started = 2 */
public static final String TASKSTATUS_20Started = "2";
/**  40% Busy = 4 */
public static final String TASKSTATUS_40Busy = "4";
/**  60% Good Progress = 6 */
public static final String TASKSTATUS_60GoodProgress = "6";
/**  80% Nearly Done = 8 */
public static final String TASKSTATUS_80NearlyDone = "8";
/**  90% Finishing = 9 */
public static final String TASKSTATUS_90Finishing = "9";
/**  95% Almost Done = A */
public static final String TASKSTATUS_95AlmostDone = "A";
/**  99% Cleaning up = C */
public static final String TASKSTATUS_99CleaningUp = "C";
/** 100% Complete = D */
public static final String TASKSTATUS_100Complete = "D";
/** Set Task Status.
@param TaskStatus Status of the Task */
public void setTaskStatus (String TaskStatus)
{
if (TaskStatus == null || TaskStatus.equals("0") || TaskStatus.equals("2") || TaskStatus.equals("4") || TaskStatus.equals("6") || TaskStatus.equals("8") || TaskStatus.equals("9") || TaskStatus.equals("A") || TaskStatus.equals("C") || TaskStatus.equals("D"));
 else throw new IllegalArgumentException ("TaskStatus Invalid value - " + TaskStatus + " - Reference_ID=366 - 0 - 2 - 4 - 6 - 8 - 9 - A - C - D");
if (TaskStatus != null && TaskStatus.length() > 1)
{
log.warning("Length > 1 - truncated");
TaskStatus = TaskStatus.substring(0,0);
}
set_Value ("TaskStatus", TaskStatus);
}
/** Get Task Status.
@return Status of the Task */
public String getTaskStatus() 
{
return (String)get_Value("TaskStatus");
}
/** Column name TaskStatus */
public static final String COLUMNNAME_TaskStatus = "TaskStatus";
}
