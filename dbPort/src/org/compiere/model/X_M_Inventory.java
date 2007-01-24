/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
/** Generated Model for M_Inventory
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_M_Inventory extends PO
{
/** Standard Constructor
@param ctx context
@param M_Inventory_ID id
@param trxName transaction
*/
public X_M_Inventory (Properties ctx, int M_Inventory_ID, String trxName)
{
super (ctx, M_Inventory_ID, trxName);
/** if (M_Inventory_ID == 0)
{
setC_DocType_ID (0);
setDocAction (null);	// CO
setDocStatus (null);	// DR
setDocumentNo (null);
setIsApproved (false);
setM_Inventory_ID (0);
setM_Warehouse_ID (0);
setMovementDate (new Timestamp(System.currentTimeMillis()));	// @#Date@
setPosted (false);
setProcessed (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_Inventory (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=321 */
public static final int Table_ID=MTable.getTable_ID("M_Inventory");

/** TableName=M_Inventory */
public static final String Table_Name="M_Inventory";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_Inventory");

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
StringBuffer sb = new StringBuffer ("X_M_Inventory[").append(get_ID()).append("]");
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
/** Set Approval Amount.
@param ApprovalAmt Document Approval Amount */
public void setApprovalAmt (BigDecimal ApprovalAmt)
{
set_Value ("ApprovalAmt", ApprovalAmt);
}
/** Get Approval Amount.
@return Document Approval Amount */
public BigDecimal getApprovalAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("ApprovalAmt");
if (bd == null) return Env.ZERO;
return bd;
}
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
/** Set Document Type.
@param C_DocType_ID Document type or rules */
public void setC_DocType_ID (int C_DocType_ID)
{
if (C_DocType_ID < 0) throw new IllegalArgumentException ("C_DocType_ID is mandatory.");
set_Value ("C_DocType_ID", Integer.valueOf(C_DocType_ID));
}
/** Get Document Type.
@return Document type or rules */
public int getC_DocType_ID() 
{
Integer ii = (Integer)get_Value("C_DocType_ID");
if (ii == null) return 0;
return ii.intValue();
}
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

/** DocAction AD_Reference_ID=135 */
public static final int DOCACTION_AD_Reference_ID=135;
/** <None> = -- */
public static final String DOCACTION_None = "--";
/** Approve = AP */
public static final String DOCACTION_Approve = "AP";
/** Close = CL */
public static final String DOCACTION_Close = "CL";
/** Complete = CO */
public static final String DOCACTION_Complete = "CO";
/** Invalidate = IN */
public static final String DOCACTION_Invalidate = "IN";
/** Post = PO */
public static final String DOCACTION_Post = "PO";
/** Prepare = PR */
public static final String DOCACTION_Prepare = "PR";
/** Reverse - Accrual = RA */
public static final String DOCACTION_Reverse_Accrual = "RA";
/** Reverse - Correct = RC */
public static final String DOCACTION_Reverse_Correct = "RC";
/** Re-activate = RE */
public static final String DOCACTION_Re_Activate = "RE";
/** Reject = RJ */
public static final String DOCACTION_Reject = "RJ";
/** Void = VO */
public static final String DOCACTION_Void = "VO";
/** Wait Complete = WC */
public static final String DOCACTION_WaitComplete = "WC";
/** Unlock = XL */
public static final String DOCACTION_Unlock = "XL";
/** Set Document Action.
@param DocAction The targeted status of the document */
public void setDocAction (String DocAction)
{
if (DocAction == null) throw new IllegalArgumentException ("DocAction is mandatory");
if (DocAction.equals("--") || DocAction.equals("AP") || DocAction.equals("CL") || DocAction.equals("CO") || DocAction.equals("IN") || DocAction.equals("PO") || DocAction.equals("PR") || DocAction.equals("RA") || DocAction.equals("RC") || DocAction.equals("RE") || DocAction.equals("RJ") || DocAction.equals("VO") || DocAction.equals("WC") || DocAction.equals("XL"));
 else throw new IllegalArgumentException ("DocAction Invalid value - " + DocAction + " - Reference_ID=135 - -- - AP - CL - CO - IN - PO - PR - RA - RC - RE - RJ - VO - WC - XL");
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

/** DocStatus AD_Reference_ID=131 */
public static final int DOCSTATUS_AD_Reference_ID=131;
/** Unknown = ?? */
public static final String DOCSTATUS_Unknown = "??";
/** Approved = AP */
public static final String DOCSTATUS_Approved = "AP";
/** Closed = CL */
public static final String DOCSTATUS_Closed = "CL";
/** Completed = CO */
public static final String DOCSTATUS_Completed = "CO";
/** Drafted = DR */
public static final String DOCSTATUS_Drafted = "DR";
/** Invalid = IN */
public static final String DOCSTATUS_Invalid = "IN";
/** In Progress = IP */
public static final String DOCSTATUS_InProgress = "IP";
/** Not Approved = NA */
public static final String DOCSTATUS_NotApproved = "NA";
/** Reversed = RE */
public static final String DOCSTATUS_Reversed = "RE";
/** Voided = VO */
public static final String DOCSTATUS_Voided = "VO";
/** Waiting Confirmation = WC */
public static final String DOCSTATUS_WaitingConfirmation = "WC";
/** Waiting Payment = WP */
public static final String DOCSTATUS_WaitingPayment = "WP";
/** Set Document Status.
@param DocStatus The current status of the document */
public void setDocStatus (String DocStatus)
{
if (DocStatus == null) throw new IllegalArgumentException ("DocStatus is mandatory");
if (DocStatus.equals("??") || DocStatus.equals("AP") || DocStatus.equals("CL") || DocStatus.equals("CO") || DocStatus.equals("DR") || DocStatus.equals("IN") || DocStatus.equals("IP") || DocStatus.equals("NA") || DocStatus.equals("RE") || DocStatus.equals("VO") || DocStatus.equals("WC") || DocStatus.equals("WP"));
 else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=131 - ?? - AP - CL - CO - DR - IN - IP - NA - RE - VO - WC - WP");
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
/** Set Generate List.
@param GenerateList Generate List */
public void setGenerateList (String GenerateList)
{
if (GenerateList != null && GenerateList.length() > 1)
{
log.warning("Length > 1 - truncated");
GenerateList = GenerateList.substring(0,0);
}
set_Value ("GenerateList", GenerateList);
}
/** Get Generate List.
@return Generate List */
public String getGenerateList() 
{
return (String)get_Value("GenerateList");
}
/** Set Approved.
@param IsApproved Indicates if this document requires approval */
public void setIsApproved (boolean IsApproved)
{
set_Value ("IsApproved", Boolean.valueOf(IsApproved));
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
/** Set Phys.Inventory.
@param M_Inventory_ID Parameters for a Physical Inventory */
public void setM_Inventory_ID (int M_Inventory_ID)
{
if (M_Inventory_ID < 1) throw new IllegalArgumentException ("M_Inventory_ID is mandatory.");
set_ValueNoCheck ("M_Inventory_ID", Integer.valueOf(M_Inventory_ID));
}
/** Get Phys.Inventory.
@return Parameters for a Physical Inventory */
public int getM_Inventory_ID() 
{
Integer ii = (Integer)get_Value("M_Inventory_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Perpetual Inventory.
@param M_PerpetualInv_ID Rules for generating physical inventory */
public void setM_PerpetualInv_ID (int M_PerpetualInv_ID)
{
if (M_PerpetualInv_ID <= 0) set_ValueNoCheck ("M_PerpetualInv_ID", null);
 else 
set_ValueNoCheck ("M_PerpetualInv_ID", Integer.valueOf(M_PerpetualInv_ID));
}
/** Get Perpetual Inventory.
@return Rules for generating physical inventory */
public int getM_PerpetualInv_ID() 
{
Integer ii = (Integer)get_Value("M_PerpetualInv_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Warehouse.
@param M_Warehouse_ID Storage Warehouse and Service Point */
public void setM_Warehouse_ID (int M_Warehouse_ID)
{
if (M_Warehouse_ID < 1) throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
set_Value ("M_Warehouse_ID", Integer.valueOf(M_Warehouse_ID));
}
/** Get Warehouse.
@return Storage Warehouse and Service Point */
public int getM_Warehouse_ID() 
{
Integer ii = (Integer)get_Value("M_Warehouse_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Movement Date.
@param MovementDate Date a product was moved in or out of inventory */
public void setMovementDate (Timestamp MovementDate)
{
if (MovementDate == null) throw new IllegalArgumentException ("MovementDate is mandatory.");
set_Value ("MovementDate", MovementDate);
}
/** Get Movement Date.
@return Date a product was moved in or out of inventory */
public Timestamp getMovementDate() 
{
return (Timestamp)get_Value("MovementDate");
}
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
/** Set Update Quantities.
@param UpdateQty Update Quantities */
public void setUpdateQty (String UpdateQty)
{
if (UpdateQty != null && UpdateQty.length() > 1)
{
log.warning("Length > 1 - truncated");
UpdateQty = UpdateQty.substring(0,0);
}
set_Value ("UpdateQty", UpdateQty);
}
/** Get Update Quantities.
@return Update Quantities */
public String getUpdateQty() 
{
return (String)get_Value("UpdateQty");
}

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
}
