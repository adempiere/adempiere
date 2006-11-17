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
/** Generated Model for C_BP_EDI
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:56.39 */
public class X_C_BP_EDI extends PO
{
/** Standard Constructor
@param ctx context
@param C_BP_EDI_ID id
@param trxName transaction
*/
public X_C_BP_EDI (Properties ctx, int C_BP_EDI_ID, String trxName)
{
super (ctx, C_BP_EDI_ID, trxName);
/** if (C_BP_EDI_ID == 0)
{
setAD_Sequence_ID (0);
setC_BP_EDI_ID (0);
setC_BPartner_ID (0);
setCustomerNo (null);
setEDIType (null);
setEMail_Error_To (null);
setEMail_Info_To (null);
setIsAudited (false);
setIsInfoSent (false);
setM_Warehouse_ID (0);
setName (null);
setReceiveInquiryReply (false);
setReceiveOrderReply (false);
setSendInquiry (false);
setSendOrder (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_BP_EDI (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=366 */
public static final int Table_ID=366;

/** TableName=C_BP_EDI */
public static final String Table_Name="C_BP_EDI";

protected static KeyNamePair Model = new KeyNamePair(366,"C_BP_EDI");

protected BigDecimal accessLevel = new BigDecimal(3);
/** AccessLevel
@return 3 - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_C_BP_EDI[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_Sequence_ID AD_Reference_ID=128 */
public static final int AD_SEQUENCE_ID_AD_Reference_ID=128;
/** Set Sequence.
@param AD_Sequence_ID Document Sequence */
public void setAD_Sequence_ID (int AD_Sequence_ID)
{
if (AD_Sequence_ID < 1) throw new IllegalArgumentException ("AD_Sequence_ID is mandatory.");
set_Value ("AD_Sequence_ID", new Integer(AD_Sequence_ID));
}
/** Get Sequence.
@return Document Sequence */
public int getAD_Sequence_ID() 
{
Integer ii = (Integer)get_Value("AD_Sequence_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set EDI Definition.
@param C_BP_EDI_ID Electronic Data Interchange */
public void setC_BP_EDI_ID (int C_BP_EDI_ID)
{
if (C_BP_EDI_ID < 1) throw new IllegalArgumentException ("C_BP_EDI_ID is mandatory.");
set_ValueNoCheck ("C_BP_EDI_ID", new Integer(C_BP_EDI_ID));
}
/** Get EDI Definition.
@return Electronic Data Interchange */
public int getC_BP_EDI_ID() 
{
Integer ii = (Integer)get_Value("C_BP_EDI_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID < 1) throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
set_Value ("C_BPartner_ID", new Integer(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Customer No.
@param CustomerNo EDI Identification Number  */
public void setCustomerNo (String CustomerNo)
{
if (CustomerNo == null) throw new IllegalArgumentException ("CustomerNo is mandatory.");
if (CustomerNo.length() > 20)
{
log.warning("Length > 20 - truncated");
CustomerNo = CustomerNo.substring(0,19);
}
set_Value ("CustomerNo", CustomerNo);
}
/** Get Customer No.
@return EDI Identification Number  */
public String getCustomerNo() 
{
return (String)get_Value("CustomerNo");
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

/** EDIType AD_Reference_ID=201 */
public static final int EDITYPE_AD_Reference_ID=201;
/** EDIFACT = E */
public static final String EDITYPE_EDIFACT = "E";
/** Email EDI = M */
public static final String EDITYPE_EmailEDI = "M";
/** ASC X12  = X */
public static final String EDITYPE_ASCX12 = "X";
/** Set EDI Type.
@param EDIType EDI Type */
public void setEDIType (String EDIType)
{
if (EDIType == null) throw new IllegalArgumentException ("EDIType is mandatory");
if (EDIType.equals("E") || EDIType.equals("M") || EDIType.equals("X"));
 else throw new IllegalArgumentException ("EDIType Invalid value - " + EDIType + " - Reference_ID=201 - E - M - X");
if (EDIType.length() > 1)
{
log.warning("Length > 1 - truncated");
EDIType = EDIType.substring(0,0);
}
set_Value ("EDIType", EDIType);
}
/** Get EDI Type.
@return EDI Type */
public String getEDIType() 
{
return (String)get_Value("EDIType");
}
/** Set Error EMail.
@param EMail_Error_To Email address to send error messages to */
public void setEMail_Error_To (String EMail_Error_To)
{
if (EMail_Error_To == null) throw new IllegalArgumentException ("EMail_Error_To is mandatory.");
if (EMail_Error_To.length() > 60)
{
log.warning("Length > 60 - truncated");
EMail_Error_To = EMail_Error_To.substring(0,59);
}
set_Value ("EMail_Error_To", EMail_Error_To);
}
/** Get Error EMail.
@return Email address to send error messages to */
public String getEMail_Error_To() 
{
return (String)get_Value("EMail_Error_To");
}
/** Set From EMail.
@param EMail_From Full EMail address used to send requests - e.g. edi@organization.com */
public void setEMail_From (String EMail_From)
{
if (EMail_From != null && EMail_From.length() > 60)
{
log.warning("Length > 60 - truncated");
EMail_From = EMail_From.substring(0,59);
}
set_Value ("EMail_From", EMail_From);
}
/** Get From EMail.
@return Full EMail address used to send requests - e.g. edi@organization.com */
public String getEMail_From() 
{
return (String)get_Value("EMail_From");
}
/** Set From EMail Password.
@param EMail_From_Pwd Password of the sending EMail address */
public void setEMail_From_Pwd (String EMail_From_Pwd)
{
if (EMail_From_Pwd != null && EMail_From_Pwd.length() > 20)
{
log.warning("Length > 20 - truncated");
EMail_From_Pwd = EMail_From_Pwd.substring(0,19);
}
set_Value ("EMail_From_Pwd", EMail_From_Pwd);
}
/** Get From EMail Password.
@return Password of the sending EMail address */
public String getEMail_From_Pwd() 
{
return (String)get_Value("EMail_From_Pwd");
}
/** Set From EMail User ID.
@param EMail_From_Uid User ID of the sending EMail address (on default SMTP Host) - e.g. edi */
public void setEMail_From_Uid (String EMail_From_Uid)
{
if (EMail_From_Uid != null && EMail_From_Uid.length() > 20)
{
log.warning("Length > 20 - truncated");
EMail_From_Uid = EMail_From_Uid.substring(0,19);
}
set_Value ("EMail_From_Uid", EMail_From_Uid);
}
/** Get From EMail User ID.
@return User ID of the sending EMail address (on default SMTP Host) - e.g. edi */
public String getEMail_From_Uid() 
{
return (String)get_Value("EMail_From_Uid");
}
/** Set Info EMail.
@param EMail_Info_To EMail address to send informational messages and copies */
public void setEMail_Info_To (String EMail_Info_To)
{
if (EMail_Info_To == null) throw new IllegalArgumentException ("EMail_Info_To is mandatory.");
if (EMail_Info_To.length() > 60)
{
log.warning("Length > 60 - truncated");
EMail_Info_To = EMail_Info_To.substring(0,59);
}
set_Value ("EMail_Info_To", EMail_Info_To);
}
/** Get Info EMail.
@return EMail address to send informational messages and copies */
public String getEMail_Info_To() 
{
return (String)get_Value("EMail_Info_To");
}
/** Set To EMail.
@param EMail_To EMail address to send requests to - e.g. edi@manufacturer.com  */
public void setEMail_To (String EMail_To)
{
if (EMail_To != null && EMail_To.length() > 60)
{
log.warning("Length > 60 - truncated");
EMail_To = EMail_To.substring(0,59);
}
set_Value ("EMail_To", EMail_To);
}
/** Get To EMail.
@return EMail address to send requests to - e.g. edi@manufacturer.com  */
public String getEMail_To() 
{
return (String)get_Value("EMail_To");
}
/** Set Activate Audit.
@param IsAudited Activate Audit Trail of what numbers are generated */
public void setIsAudited (boolean IsAudited)
{
set_Value ("IsAudited", new Boolean(IsAudited));
}
/** Get Activate Audit.
@return Activate Audit Trail of what numbers are generated */
public boolean isAudited() 
{
Object oo = get_Value("IsAudited");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Send Info.
@param IsInfoSent Send informational messages and copies */
public void setIsInfoSent (boolean IsInfoSent)
{
set_Value ("IsInfoSent", new Boolean(IsInfoSent));
}
/** Get Send Info.
@return Send informational messages and copies */
public boolean isInfoSent() 
{
Object oo = get_Value("IsInfoSent");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Warehouse.
@param M_Warehouse_ID Storage Warehouse and Service Point */
public void setM_Warehouse_ID (int M_Warehouse_ID)
{
if (M_Warehouse_ID < 1) throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
set_Value ("M_Warehouse_ID", new Integer(M_Warehouse_ID));
}
/** Get Warehouse.
@return Storage Warehouse and Service Point */
public int getM_Warehouse_ID() 
{
Integer ii = (Integer)get_Value("M_Warehouse_ID");
if (ii == null) return 0;
return ii.intValue();
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
/** Set Received Inquiry Reply.
@param ReceiveInquiryReply Received Inquiry Reply */
public void setReceiveInquiryReply (boolean ReceiveInquiryReply)
{
set_Value ("ReceiveInquiryReply", new Boolean(ReceiveInquiryReply));
}
/** Get Received Inquiry Reply.
@return Received Inquiry Reply */
public boolean isReceiveInquiryReply() 
{
Object oo = get_Value("ReceiveInquiryReply");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Receive Order Reply.
@param ReceiveOrderReply Receive Order Reply */
public void setReceiveOrderReply (boolean ReceiveOrderReply)
{
set_Value ("ReceiveOrderReply", new Boolean(ReceiveOrderReply));
}
/** Get Receive Order Reply.
@return Receive Order Reply */
public boolean isReceiveOrderReply() 
{
Object oo = get_Value("ReceiveOrderReply");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Send Inquiry.
@param SendInquiry Quantity Availability Inquiry */
public void setSendInquiry (boolean SendInquiry)
{
set_Value ("SendInquiry", new Boolean(SendInquiry));
}
/** Get Send Inquiry.
@return Quantity Availability Inquiry */
public boolean isSendInquiry() 
{
Object oo = get_Value("SendInquiry");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Send Order.
@param SendOrder Send Order */
public void setSendOrder (boolean SendOrder)
{
set_Value ("SendOrder", new Boolean(SendOrder));
}
/** Get Send Order.
@return Send Order */
public boolean isSendOrder() 
{
Object oo = get_Value("SendOrder");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
}
