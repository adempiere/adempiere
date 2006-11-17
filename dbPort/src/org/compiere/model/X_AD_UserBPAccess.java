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
/** Generated Model for AD_UserBPAccess
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:54.453 */
public class X_AD_UserBPAccess extends PO
{
/** Standard Constructor
@param ctx context
@param AD_UserBPAccess_ID id
@param trxName transaction
*/
public X_AD_UserBPAccess (Properties ctx, int AD_UserBPAccess_ID, String trxName)
{
super (ctx, AD_UserBPAccess_ID, trxName);
/** if (AD_UserBPAccess_ID == 0)
{
setAD_UserBPAccess_ID (0);
setAD_User_ID (0);
setBPAccessType (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_UserBPAccess (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=813 */
public static final int Table_ID=813;

/** TableName=AD_UserBPAccess */
public static final String Table_Name="AD_UserBPAccess";

protected static KeyNamePair Model = new KeyNamePair(813,"AD_UserBPAccess");

protected BigDecimal accessLevel = new BigDecimal(2);
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
StringBuffer sb = new StringBuffer ("X_AD_UserBPAccess[").append(get_ID()).append("]");
return sb.toString();
}
/** Set User BP Access.
@param AD_UserBPAccess_ID User/concat access to Business Partner information and resources */
public void setAD_UserBPAccess_ID (int AD_UserBPAccess_ID)
{
if (AD_UserBPAccess_ID < 1) throw new IllegalArgumentException ("AD_UserBPAccess_ID is mandatory.");
set_ValueNoCheck ("AD_UserBPAccess_ID", new Integer(AD_UserBPAccess_ID));
}
/** Get User BP Access.
@return User/concat access to Business Partner information and resources */
public int getAD_UserBPAccess_ID() 
{
Integer ii = (Integer)get_Value("AD_UserBPAccess_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID < 1) throw new IllegalArgumentException ("AD_User_ID is mandatory.");
set_Value ("AD_User_ID", new Integer(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** BPAccessType AD_Reference_ID=358 */
public static final int BPACCESSTYPE_AD_Reference_ID=358;
/** Assets, Download = A */
public static final String BPACCESSTYPE_AssetsDownload = "A";
/** Business Documents = B */
public static final String BPACCESSTYPE_BusinessDocuments = "B";
/** Requests = R */
public static final String BPACCESSTYPE_Requests = "R";
/** Set Access Type.
@param BPAccessType Type of Access of the user/contact to Business Partner information and resources */
public void setBPAccessType (String BPAccessType)
{
if (BPAccessType == null) throw new IllegalArgumentException ("BPAccessType is mandatory");
if (BPAccessType.equals("A") || BPAccessType.equals("B") || BPAccessType.equals("R"));
 else throw new IllegalArgumentException ("BPAccessType Invalid value - " + BPAccessType + " - Reference_ID=358 - A - B - R");
if (BPAccessType.length() > 1)
{
log.warning("Length > 1 - truncated");
BPAccessType = BPAccessType.substring(0,0);
}
set_Value ("BPAccessType", BPAccessType);
}
/** Get Access Type.
@return Type of Access of the user/contact to Business Partner information and resources */
public String getBPAccessType() 
{
return (String)get_Value("BPAccessType");
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
if (DocBaseType == null || DocBaseType.equals("APC") || DocBaseType.equals("API") || DocBaseType.equals("APP") || DocBaseType.equals("ARC") || DocBaseType.equals("ARF") || DocBaseType.equals("ARI") || DocBaseType.equals("ARR") || DocBaseType.equals("CMA") || DocBaseType.equals("CMB") || DocBaseType.equals("CMC") || DocBaseType.equals("GLD") || DocBaseType.equals("GLJ") || DocBaseType.equals("MMI") || DocBaseType.equals("MMM") || DocBaseType.equals("MMP") || DocBaseType.equals("MMR") || DocBaseType.equals("MMS") || DocBaseType.equals("MXI") || DocBaseType.equals("MXP") || DocBaseType.equals("PJI") || DocBaseType.equals("POO") || DocBaseType.equals("POR") || DocBaseType.equals("SOO"));
 else throw new IllegalArgumentException ("DocBaseType Invalid value - " + DocBaseType + " - Reference_ID=183 - APC - API - APP - ARC - ARF - ARI - ARR - CMA - CMB - CMC - GLD - GLJ - MMI - MMM - MMP - MMR - MMS - MXI - MXP - PJI - POO - POR - SOO");
if (DocBaseType != null && DocBaseType.length() > 10)
{
log.warning("Length > 10 - truncated");
DocBaseType = DocBaseType.substring(0,9);
}
set_Value ("DocBaseType", DocBaseType);
}
/** Get Document BaseType.
@return Logical type of document */
public String getDocBaseType() 
{
return (String)get_Value("DocBaseType");
}
/** Set Request Type.
@param R_RequestType_ID Type of request (e.g. Inquiry, Complaint, ..) */
public void setR_RequestType_ID (int R_RequestType_ID)
{
if (R_RequestType_ID <= 0) set_Value ("R_RequestType_ID", null);
 else 
set_Value ("R_RequestType_ID", new Integer(R_RequestType_ID));
}
/** Get Request Type.
@return Type of request (e.g. Inquiry, Complaint, ..) */
public int getR_RequestType_ID() 
{
Integer ii = (Integer)get_Value("R_RequestType_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
