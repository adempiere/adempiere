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
/** Generated Model for M_RMA
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_M_RMA extends PO
{
/** Standard Constructor
@param ctx context
@param M_RMA_ID id
@param trxName transaction
*/
public X_M_RMA (Properties ctx, int M_RMA_ID, String trxName)
{
super (ctx, M_RMA_ID, trxName);
/** if (M_RMA_ID == 0)
{
setC_DocType_ID (0);
setDocAction (null);	// CO
setDocStatus (null);	// DR
setDocumentNo (null);
setInOut_ID (0);
setIsApproved (false);
setIsSOTrx (false);	// @IsSOTrx@
setM_RMAType_ID (0);
setM_RMA_ID (0);
setName (null);
setProcessed (false);
setSalesRep_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_RMA (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=M_RMA */
public static final String Table_Name="M_RMA";

/** AD_Table_ID=661 */
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
StringBuffer sb = new StringBuffer ("X_M_RMA[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Amount.
@param Amt Amount */
public void setAmt (BigDecimal Amt)
{
set_Value ("Amt", Amt);
}
/** Get Amount.
@return Amount */
public BigDecimal getAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("Amt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Amt */
public static final String COLUMNNAME_Amt = "Amt";
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

/** C_DocType_ID AD_Reference_ID=321 */
public static final int C_DOCTYPE_ID_AD_Reference_ID=321;
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
/** Column name C_DocType_ID */
public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";
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
/** In Progress = IP */
public static final String DOCSTATUS_InProgress = "IP";
/** Waiting Payment = WP */
public static final String DOCSTATUS_WaitingPayment = "WP";
/** Set Document Status.
@param DocStatus The current status of the document */
public void setDocStatus (String DocStatus)
{
if (DocStatus == null) throw new IllegalArgumentException ("DocStatus is mandatory");
if (DocStatus.equals("WC") || DocStatus.equals("DR") || DocStatus.equals("CO") || DocStatus.equals("AP") || DocStatus.equals("NA") || DocStatus.equals("VO") || DocStatus.equals("IN") || DocStatus.equals("RE") || DocStatus.equals("CL") || DocStatus.equals("??") || DocStatus.equals("IP") || DocStatus.equals("WP"));
 else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=131 - WC - DR - CO - AP - NA - VO - IN - RE - CL - ?? - IP - WP");
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
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Column name Help */
public static final String COLUMNNAME_Help = "Help";

/** InOut_ID AD_Reference_ID=337 */
public static final int INOUT_ID_AD_Reference_ID=337;
/** Set Shipment/Receipt.
@param InOut_ID Material Shipment Document */
public void setInOut_ID (int InOut_ID)
{
if (InOut_ID < 1) throw new IllegalArgumentException ("InOut_ID is mandatory.");
set_ValueNoCheck ("InOut_ID", Integer.valueOf(InOut_ID));
}
/** Get Shipment/Receipt.
@return Material Shipment Document */
public int getInOut_ID() 
{
Integer ii = (Integer)get_Value("InOut_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name InOut_ID */
public static final String COLUMNNAME_InOut_ID = "InOut_ID";
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
/** Column name IsApproved */
public static final String COLUMNNAME_IsApproved = "IsApproved";
/** Set Sales Transaction.
@param IsSOTrx This is a Sales Transaction */
public void setIsSOTrx (boolean IsSOTrx)
{
set_Value ("IsSOTrx", Boolean.valueOf(IsSOTrx));
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
/** Set RMA Type.
@param M_RMAType_ID Return Material Authorization Type */
public void setM_RMAType_ID (int M_RMAType_ID)
{
if (M_RMAType_ID < 1) throw new IllegalArgumentException ("M_RMAType_ID is mandatory.");
set_Value ("M_RMAType_ID", Integer.valueOf(M_RMAType_ID));
}
/** Get RMA Type.
@return Return Material Authorization Type */
public int getM_RMAType_ID() 
{
Integer ii = (Integer)get_Value("M_RMAType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_RMAType_ID */
public static final String COLUMNNAME_M_RMAType_ID = "M_RMAType_ID";
/** Set RMA.
@param M_RMA_ID Return Material Authorization */
public void setM_RMA_ID (int M_RMA_ID)
{
if (M_RMA_ID < 1) throw new IllegalArgumentException ("M_RMA_ID is mandatory.");
set_ValueNoCheck ("M_RMA_ID", Integer.valueOf(M_RMA_ID));
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
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 60)
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

/** SalesRep_ID AD_Reference_ID=190 */
public static final int SALESREP_ID_AD_Reference_ID=190;
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
}
