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
/** Generated Model for C_DocType
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:57.187 */
public class X_C_DocType extends PO
{
/** Standard Constructor
@param ctx context
@param C_DocType_ID id
@param trxName transaction
*/
public X_C_DocType (Properties ctx, int C_DocType_ID, String trxName)
{
super (ctx, C_DocType_ID, trxName);
/** if (C_DocType_ID == 0)
{
setC_DocType_ID (0);
setDocBaseType (null);
setDocumentCopies (0);	// 1
setGL_Category_ID (0);
setHasCharges (false);
setIsCreateCounter (true);	// Y
setIsDefault (false);
setIsDefaultCounterDoc (false);
setIsDocNoControlled (true);	// Y
setIsInTransit (false);
setIsIndexed (false);
setIsPickQAConfirm (false);
setIsSOTrx (false);
setIsShipConfirm (false);
setIsSplitWhenDifference (false);	// N
setName (null);
setPrintName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_DocType (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=217 */
public static final int Table_ID=217;

/** TableName=C_DocType */
public static final String Table_Name="C_DocType";

protected static KeyNamePair Model = new KeyNamePair(217,"C_DocType");

protected BigDecimal accessLevel = new BigDecimal(6);
/** AccessLevel
@return 6 - System - Client 
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
StringBuffer sb = new StringBuffer ("X_C_DocType[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Print Format.
@param AD_PrintFormat_ID Data Print Format */
public void setAD_PrintFormat_ID (int AD_PrintFormat_ID)
{
if (AD_PrintFormat_ID <= 0) set_Value ("AD_PrintFormat_ID", null);
 else 
set_Value ("AD_PrintFormat_ID", new Integer(AD_PrintFormat_ID));
}
/** Get Print Format.
@return Data Print Format */
public int getAD_PrintFormat_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintFormat_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** C_DocTypeDifference_ID AD_Reference_ID=170 */
public static final int C_DOCTYPEDIFFERENCE_ID_AD_Reference_ID=170;
/** Set Difference Document.
@param C_DocTypeDifference_ID Document type for generating in dispute Shipments */
public void setC_DocTypeDifference_ID (int C_DocTypeDifference_ID)
{
if (C_DocTypeDifference_ID <= 0) set_Value ("C_DocTypeDifference_ID", null);
 else 
set_Value ("C_DocTypeDifference_ID", new Integer(C_DocTypeDifference_ID));
}
/** Get Difference Document.
@return Document type for generating in dispute Shipments */
public int getC_DocTypeDifference_ID() 
{
Integer ii = (Integer)get_Value("C_DocTypeDifference_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** C_DocTypeInvoice_ID AD_Reference_ID=170 */
public static final int C_DOCTYPEINVOICE_ID_AD_Reference_ID=170;
/** Set Document Type for Invoice.
@param C_DocTypeInvoice_ID Document type used for invoices generated from this sales document */
public void setC_DocTypeInvoice_ID (int C_DocTypeInvoice_ID)
{
if (C_DocTypeInvoice_ID <= 0) set_Value ("C_DocTypeInvoice_ID", null);
 else 
set_Value ("C_DocTypeInvoice_ID", new Integer(C_DocTypeInvoice_ID));
}
/** Get Document Type for Invoice.
@return Document type used for invoices generated from this sales document */
public int getC_DocTypeInvoice_ID() 
{
Integer ii = (Integer)get_Value("C_DocTypeInvoice_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** C_DocTypeProforma_ID AD_Reference_ID=170 */
public static final int C_DOCTYPEPROFORMA_ID_AD_Reference_ID=170;
/** Set Document Type for ProForma.
@param C_DocTypeProforma_ID Document type used for pro forma invoices generated from this sales document */
public void setC_DocTypeProforma_ID (int C_DocTypeProforma_ID)
{
if (C_DocTypeProforma_ID <= 0) set_Value ("C_DocTypeProforma_ID", null);
 else 
set_Value ("C_DocTypeProforma_ID", new Integer(C_DocTypeProforma_ID));
}
/** Get Document Type for ProForma.
@return Document type used for pro forma invoices generated from this sales document */
public int getC_DocTypeProforma_ID() 
{
Integer ii = (Integer)get_Value("C_DocTypeProforma_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** C_DocTypeShipment_ID AD_Reference_ID=170 */
public static final int C_DOCTYPESHIPMENT_ID_AD_Reference_ID=170;
/** Set Document Type for Shipment.
@param C_DocTypeShipment_ID Document type used for shipments generated from this sales document */
public void setC_DocTypeShipment_ID (int C_DocTypeShipment_ID)
{
if (C_DocTypeShipment_ID <= 0) set_Value ("C_DocTypeShipment_ID", null);
 else 
set_Value ("C_DocTypeShipment_ID", new Integer(C_DocTypeShipment_ID));
}
/** Get Document Type for Shipment.
@return Document type used for shipments generated from this sales document */
public int getC_DocTypeShipment_ID() 
{
Integer ii = (Integer)get_Value("C_DocTypeShipment_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Document Type.
@param C_DocType_ID Document type or rules */
public void setC_DocType_ID (int C_DocType_ID)
{
if (C_DocType_ID < 0) throw new IllegalArgumentException ("C_DocType_ID is mandatory.");
set_ValueNoCheck ("C_DocType_ID", new Integer(C_DocType_ID));
}
/** Get Document Type.
@return Document type or rules */
public int getC_DocType_ID() 
{
Integer ii = (Integer)get_Value("C_DocType_ID");
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

/** DocBaseType AD_Reference_ID=183 */
public static final int DOCBASETYPE_AD_Reference_ID=183;
/** AP Credit Memo = APC */
public static final String DOCBASETYPE_APCreditMemo = "APC";
/** AP Invoice = API */
public static final String DOCBASETYPE_APInvoice = "API";
/** AP Payment = APP */
public static final String DOCBASETYPE_APPayment = "APP";
/** AR Credit Memo = ARC */
public static final String DOCBASETYPE_ARCreditMemo = "ARC";
/** AR Pro Forma Invoice = ARF */
public static final String DOCBASETYPE_ARProFormaInvoice = "ARF";
/** AR Invoice = ARI */
public static final String DOCBASETYPE_ARInvoice = "ARI";
/** AR Receipt = ARR */
public static final String DOCBASETYPE_ARReceipt = "ARR";
/** Payment Allocation = CMA */
public static final String DOCBASETYPE_PaymentAllocation = "CMA";
/** Bank Statement = CMB */
public static final String DOCBASETYPE_BankStatement = "CMB";
/** Cash Journal = CMC */
public static final String DOCBASETYPE_CashJournal = "CMC";
/** GL Document = GLD */
public static final String DOCBASETYPE_GLDocument = "GLD";
/** GL Journal = GLJ */
public static final String DOCBASETYPE_GLJournal = "GLJ";
/** Material Physical Inventory = MMI */
public static final String DOCBASETYPE_MaterialPhysicalInventory = "MMI";
/** Material Movement = MMM */
public static final String DOCBASETYPE_MaterialMovement = "MMM";
/** Material Production = MMP */
public static final String DOCBASETYPE_MaterialProduction = "MMP";
/** Material Receipt = MMR */
public static final String DOCBASETYPE_MaterialReceipt = "MMR";
/** Material Delivery = MMS */
public static final String DOCBASETYPE_MaterialDelivery = "MMS";
/** Match Invoice = MXI */
public static final String DOCBASETYPE_MatchInvoice = "MXI";
/** Match PO = MXP */
public static final String DOCBASETYPE_MatchPO = "MXP";
/** Project Issue = PJI */
public static final String DOCBASETYPE_ProjectIssue = "PJI";
/** Purchase Order = POO */
public static final String DOCBASETYPE_PurchaseOrder = "POO";
/** Purchase Requisition = POR */
public static final String DOCBASETYPE_PurchaseRequisition = "POR";
/** Sales Order = SOO */
public static final String DOCBASETYPE_SalesOrder = "SOO";
/** Set Document BaseType.
@param DocBaseType Logical type of document */
public void setDocBaseType (String DocBaseType)
{
if (DocBaseType == null) throw new IllegalArgumentException ("DocBaseType is mandatory");
if (DocBaseType.equals("APC") || DocBaseType.equals("API") || DocBaseType.equals("APP") || DocBaseType.equals("ARC") || DocBaseType.equals("ARF") || DocBaseType.equals("ARI") || DocBaseType.equals("ARR") || DocBaseType.equals("CMA") || DocBaseType.equals("CMB") || DocBaseType.equals("CMC") || DocBaseType.equals("GLD") || DocBaseType.equals("GLJ") || DocBaseType.equals("MMI") || DocBaseType.equals("MMM") || DocBaseType.equals("MMP") || DocBaseType.equals("MMR") || DocBaseType.equals("MMS") || DocBaseType.equals("MXI") || DocBaseType.equals("MXP") || DocBaseType.equals("PJI") || DocBaseType.equals("POO") || DocBaseType.equals("POR") || DocBaseType.equals("SOO"));
 else throw new IllegalArgumentException ("DocBaseType Invalid value - " + DocBaseType + " - Reference_ID=183 - APC - API - APP - ARC - ARF - ARI - ARR - CMA - CMB - CMC - GLD - GLJ - MMI - MMM - MMP - MMR - MMS - MXI - MXP - PJI - POO - POR - SOO");
if (DocBaseType.length() > 3)
{
log.warning("Length > 3 - truncated");
DocBaseType = DocBaseType.substring(0,2);
}
set_Value ("DocBaseType", DocBaseType);
}
/** Get Document BaseType.
@return Logical type of document */
public String getDocBaseType() 
{
return (String)get_Value("DocBaseType");
}

/** DocNoSequence_ID AD_Reference_ID=128 */
public static final int DOCNOSEQUENCE_ID_AD_Reference_ID=128;
/** Set Document Sequence.
@param DocNoSequence_ID Document sequence determines the numbering of documents */
public void setDocNoSequence_ID (int DocNoSequence_ID)
{
if (DocNoSequence_ID <= 0) set_Value ("DocNoSequence_ID", null);
 else 
set_Value ("DocNoSequence_ID", new Integer(DocNoSequence_ID));
}
/** Get Document Sequence.
@return Document sequence determines the numbering of documents */
public int getDocNoSequence_ID() 
{
Integer ii = (Integer)get_Value("DocNoSequence_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** DocSubTypeSO AD_Reference_ID=148 */
public static final int DOCSUBTYPESO_AD_Reference_ID=148;
/** Quotation = OB */
public static final String DOCSUBTYPESO_Quotation = "OB";
/** Proposal = ON */
public static final String DOCSUBTYPESO_Proposal = "ON";
/** Prepay Order = PR */
public static final String DOCSUBTYPESO_PrepayOrder = "PR";
/** Return Material = RM */
public static final String DOCSUBTYPESO_ReturnMaterial = "RM";
/** Standard Order = SO */
public static final String DOCSUBTYPESO_StandardOrder = "SO";
/** On Credit Order = WI */
public static final String DOCSUBTYPESO_OnCreditOrder = "WI";
/** Warehouse Order = WP */
public static final String DOCSUBTYPESO_WarehouseOrder = "WP";
/** POS Order = WR */
public static final String DOCSUBTYPESO_POSOrder = "WR";
/** Set SO Sub Type.
@param DocSubTypeSO Sales Order Sub Type */
public void setDocSubTypeSO (String DocSubTypeSO)
{
if (DocSubTypeSO == null) throw new IllegalArgumentException ("DocSubTypeSO is mandatory");
if (DocSubTypeSO == null || DocSubTypeSO.equals("OB") || DocSubTypeSO.equals("ON") || DocSubTypeSO.equals("PR") || DocSubTypeSO.equals("RM") || DocSubTypeSO.equals("SO") || DocSubTypeSO.equals("WI") || DocSubTypeSO.equals("WP") || DocSubTypeSO.equals("WR"));
 else throw new IllegalArgumentException ("DocSubTypeSO Invalid value - " + DocSubTypeSO + " - Reference_ID=148 - OB - ON - PR - RM - SO - WI - WP - WR");
if (DocSubTypeSO != null && DocSubTypeSO.length() > 2)
{
log.warning("Length > 2 - truncated");
DocSubTypeSO = DocSubTypeSO.substring(0,1);
}
set_Value ("DocSubTypeSO", DocSubTypeSO);
}
/** Get SO Sub Type.
@return Sales Order Sub Type */
public String getDocSubTypeSO() 
{
return (String)get_Value("DocSubTypeSO");
}
/** Set Document Copies.
@param DocumentCopies Number of copies to be printed */
public void setDocumentCopies (int DocumentCopies)
{
set_Value ("DocumentCopies", new Integer(DocumentCopies));
}
/** Get Document Copies.
@return Number of copies to be printed */
public int getDocumentCopies() 
{
Integer ii = (Integer)get_Value("DocumentCopies");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Document Note.
@param DocumentNote Additional information for a Document */
public void setDocumentNote (String DocumentNote)
{
if (DocumentNote != null && DocumentNote.length() > 2000)
{
log.warning("Length > 2000 - truncated");
DocumentNote = DocumentNote.substring(0,1999);
}
set_Value ("DocumentNote", DocumentNote);
}
/** Get Document Note.
@return Additional information for a Document */
public String getDocumentNote() 
{
return (String)get_Value("DocumentNote");
}
/** Set GL Category.
@param GL_Category_ID General Ledger Category */
public void setGL_Category_ID (int GL_Category_ID)
{
if (GL_Category_ID < 1) throw new IllegalArgumentException ("GL_Category_ID is mandatory.");
set_Value ("GL_Category_ID", new Integer(GL_Category_ID));
}
/** Get GL Category.
@return General Ledger Category */
public int getGL_Category_ID() 
{
Integer ii = (Integer)get_Value("GL_Category_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Charges.
@param HasCharges Charges can be added to the document */
public void setHasCharges (boolean HasCharges)
{
set_Value ("HasCharges", new Boolean(HasCharges));
}
/** Get Charges.
@return Charges can be added to the document */
public boolean isHasCharges() 
{
Object oo = get_Value("HasCharges");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Pro forma Invoice.
@param HasProforma Indicates if Pro Forma Invoices can be generated from this document */
public void setHasProforma (boolean HasProforma)
{
set_Value ("HasProforma", new Boolean(HasProforma));
}
/** Get Pro forma Invoice.
@return Indicates if Pro Forma Invoices can be generated from this document */
public boolean isHasProforma() 
{
Object oo = get_Value("HasProforma");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Create Counter Document.
@param IsCreateCounter Create Counter Document */
public void setIsCreateCounter (boolean IsCreateCounter)
{
set_Value ("IsCreateCounter", new Boolean(IsCreateCounter));
}
/** Get Create Counter Document.
@return Create Counter Document */
public boolean isCreateCounter() 
{
Object oo = get_Value("IsCreateCounter");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Default.
@param IsDefault Default value */
public void setIsDefault (boolean IsDefault)
{
set_Value ("IsDefault", new Boolean(IsDefault));
}
/** Get Default.
@return Default value */
public boolean isDefault() 
{
Object oo = get_Value("IsDefault");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Default Counter Document.
@param IsDefaultCounterDoc The document type is the default counter document type */
public void setIsDefaultCounterDoc (boolean IsDefaultCounterDoc)
{
set_Value ("IsDefaultCounterDoc", new Boolean(IsDefaultCounterDoc));
}
/** Get Default Counter Document.
@return The document type is the default counter document type */
public boolean isDefaultCounterDoc() 
{
Object oo = get_Value("IsDefaultCounterDoc");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Document is Number Controlled.
@param IsDocNoControlled The document has a document sequence */
public void setIsDocNoControlled (boolean IsDocNoControlled)
{
set_Value ("IsDocNoControlled", new Boolean(IsDocNoControlled));
}
/** Get Document is Number Controlled.
@return The document has a document sequence */
public boolean isDocNoControlled() 
{
Object oo = get_Value("IsDocNoControlled");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set In Transit.
@param IsInTransit Movement is in transit */
public void setIsInTransit (boolean IsInTransit)
{
set_Value ("IsInTransit", new Boolean(IsInTransit));
}
/** Get In Transit.
@return Movement is in transit */
public boolean isInTransit() 
{
Object oo = get_Value("IsInTransit");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Indexed.
@param IsIndexed Index the document for the internal search engine */
public void setIsIndexed (boolean IsIndexed)
{
set_Value ("IsIndexed", new Boolean(IsIndexed));
}
/** Get Indexed.
@return Index the document for the internal search engine */
public boolean isIndexed() 
{
Object oo = get_Value("IsIndexed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Pick/QA Confirmation.
@param IsPickQAConfirm Require Pick or QA Confirmation before processing */
public void setIsPickQAConfirm (boolean IsPickQAConfirm)
{
set_Value ("IsPickQAConfirm", new Boolean(IsPickQAConfirm));
}
/** Get Pick/QA Confirmation.
@return Require Pick or QA Confirmation before processing */
public boolean isPickQAConfirm() 
{
Object oo = get_Value("IsPickQAConfirm");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Sales Transaction.
@param IsSOTrx This is a Sales Transaction */
public void setIsSOTrx (boolean IsSOTrx)
{
set_Value ("IsSOTrx", new Boolean(IsSOTrx));
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
/** Set Ship/Receipt Confirmation.
@param IsShipConfirm Require Ship or Receipt Confirmation before processing */
public void setIsShipConfirm (boolean IsShipConfirm)
{
set_Value ("IsShipConfirm", new Boolean(IsShipConfirm));
}
/** Get Ship/Receipt Confirmation.
@return Require Ship or Receipt Confirmation before processing */
public boolean isShipConfirm() 
{
Object oo = get_Value("IsShipConfirm");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Split when Difference.
@param IsSplitWhenDifference Split document when there is a difference */
public void setIsSplitWhenDifference (boolean IsSplitWhenDifference)
{
set_Value ("IsSplitWhenDifference", new Boolean(IsSplitWhenDifference));
}
/** Get Split when Difference.
@return Split document when there is a difference */
public boolean isSplitWhenDifference() 
{
Object oo = get_Value("IsSplitWhenDifference");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getName());
}
/** Set Print Text.
@param PrintName The label text to be printed on a document or correspondence. */
public void setPrintName (String PrintName)
{
if (PrintName == null) throw new IllegalArgumentException ("PrintName is mandatory.");
if (PrintName.length() > 60)
{
log.warning("Length > 60 - truncated");
PrintName = PrintName.substring(0,59);
}
set_Value ("PrintName", PrintName);
}
/** Get Print Text.
@return The label text to be printed on a document or correspondence. */
public String getPrintName() 
{
return (String)get_Value("PrintName");
}
}
