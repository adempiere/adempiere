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
/** Generated Model for A_Asset_Delivery
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_A_Asset_Delivery extends PO
{
/** Standard Constructor
@param ctx context
@param A_Asset_Delivery_ID id
@param trxName transaction
*/
public X_A_Asset_Delivery (Properties ctx, int A_Asset_Delivery_ID, String trxName)
{
super (ctx, A_Asset_Delivery_ID, trxName);
/** if (A_Asset_Delivery_ID == 0)
{
setA_Asset_Delivery_ID (0);
setA_Asset_ID (0);
setMovementDate (new Timestamp(System.currentTimeMillis()));
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_A_Asset_Delivery (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=541 */
public static final int Table_ID=MTable.getTable_ID("A_Asset_Delivery");

/** TableName=A_Asset_Delivery */
public static final String Table_Name="A_Asset_Delivery";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"A_Asset_Delivery");

protected BigDecimal accessLevel = BigDecimal.valueOf(3);
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
StringBuffer sb = new StringBuffer ("X_A_Asset_Delivery[").append(get_ID()).append("]");
return sb.toString();
}
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID <= 0) set_ValueNoCheck ("AD_User_ID", null);
 else 
set_ValueNoCheck ("AD_User_ID", Integer.valueOf(AD_User_ID));
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
/** Set Asset Delivery.
@param A_Asset_Delivery_ID Delivery of Asset */
public void setA_Asset_Delivery_ID (int A_Asset_Delivery_ID)
{
if (A_Asset_Delivery_ID < 1) throw new IllegalArgumentException ("A_Asset_Delivery_ID is mandatory.");
set_ValueNoCheck ("A_Asset_Delivery_ID", Integer.valueOf(A_Asset_Delivery_ID));
}
/** Get Asset Delivery.
@return Delivery of Asset */
public int getA_Asset_Delivery_ID() 
{
Integer ii = (Integer)get_Value("A_Asset_Delivery_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name A_Asset_Delivery_ID */
public static final String COLUMNNAME_A_Asset_Delivery_ID = "A_Asset_Delivery_ID";
/** Set Asset.
@param A_Asset_ID Asset used internally or by customers */
public void setA_Asset_ID (int A_Asset_ID)
{
if (A_Asset_ID < 1) throw new IllegalArgumentException ("A_Asset_ID is mandatory.");
set_ValueNoCheck ("A_Asset_ID", Integer.valueOf(A_Asset_ID));
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
/** Set Delivery Confirmation.
@param DeliveryConfirmation EMail Delivery confirmation */
public void setDeliveryConfirmation (String DeliveryConfirmation)
{
if (DeliveryConfirmation != null && DeliveryConfirmation.length() > 120)
{
log.warning("Length > 120 - truncated");
DeliveryConfirmation = DeliveryConfirmation.substring(0,119);
}
set_Value ("DeliveryConfirmation", DeliveryConfirmation);
}
/** Get Delivery Confirmation.
@return EMail Delivery confirmation */
public String getDeliveryConfirmation() 
{
return (String)get_Value("DeliveryConfirmation");
}
/** Column name DeliveryConfirmation */
public static final String COLUMNNAME_DeliveryConfirmation = "DeliveryConfirmation";
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
/** Set EMail Address.
@param EMail Electronic Mail Address */
public void setEMail (String EMail)
{
if (EMail != null && EMail.length() > 60)
{
log.warning("Length > 60 - truncated");
EMail = EMail.substring(0,59);
}
set_ValueNoCheck ("EMail", EMail);
}
/** Get EMail Address.
@return Electronic Mail Address */
public String getEMail() 
{
return (String)get_Value("EMail");
}
/** Column name EMail */
public static final String COLUMNNAME_EMail = "EMail";
/** Set Lot No.
@param Lot Lot number (alphanumeric) */
public void setLot (String Lot)
{
if (Lot != null && Lot.length() > 40)
{
log.warning("Length > 40 - truncated");
Lot = Lot.substring(0,39);
}
set_ValueNoCheck ("Lot", Lot);
}
/** Get Lot No.
@return Lot number (alphanumeric) */
public String getLot() 
{
return (String)get_Value("Lot");
}
/** Column name Lot */
public static final String COLUMNNAME_Lot = "Lot";
/** Set Shipment/Receipt Line.
@param M_InOutLine_ID Line on Shipment or Receipt document */
public void setM_InOutLine_ID (int M_InOutLine_ID)
{
if (M_InOutLine_ID <= 0) set_ValueNoCheck ("M_InOutLine_ID", null);
 else 
set_ValueNoCheck ("M_InOutLine_ID", Integer.valueOf(M_InOutLine_ID));
}
/** Get Shipment/Receipt Line.
@return Line on Shipment or Receipt document */
public int getM_InOutLine_ID() 
{
Integer ii = (Integer)get_Value("M_InOutLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_InOutLine_ID */
public static final String COLUMNNAME_M_InOutLine_ID = "M_InOutLine_ID";
/** Set Product Download.
@param M_ProductDownload_ID Product downloads */
public void setM_ProductDownload_ID (int M_ProductDownload_ID)
{
if (M_ProductDownload_ID <= 0) set_Value ("M_ProductDownload_ID", null);
 else 
set_Value ("M_ProductDownload_ID", Integer.valueOf(M_ProductDownload_ID));
}
/** Get Product Download.
@return Product downloads */
public int getM_ProductDownload_ID() 
{
Integer ii = (Integer)get_Value("M_ProductDownload_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_ProductDownload_ID */
public static final String COLUMNNAME_M_ProductDownload_ID = "M_ProductDownload_ID";
/** Set Message ID.
@param MessageID EMail Message ID */
public void setMessageID (String MessageID)
{
if (MessageID != null && MessageID.length() > 120)
{
log.warning("Length > 120 - truncated");
MessageID = MessageID.substring(0,119);
}
set_ValueNoCheck ("MessageID", MessageID);
}
/** Get Message ID.
@return EMail Message ID */
public String getMessageID() 
{
return (String)get_Value("MessageID");
}
/** Column name MessageID */
public static final String COLUMNNAME_MessageID = "MessageID";
/** Set Movement Date.
@param MovementDate Date a product was moved in or out of inventory */
public void setMovementDate (Timestamp MovementDate)
{
if (MovementDate == null) throw new IllegalArgumentException ("MovementDate is mandatory.");
set_ValueNoCheck ("MovementDate", MovementDate);
}
/** Get Movement Date.
@return Date a product was moved in or out of inventory */
public Timestamp getMovementDate() 
{
return (Timestamp)get_Value("MovementDate");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getMovementDate()));
}
/** Column name MovementDate */
public static final String COLUMNNAME_MovementDate = "MovementDate";
/** Set Referrer.
@param Referrer Referring web address */
public void setReferrer (String Referrer)
{
if (Referrer != null && Referrer.length() > 255)
{
log.warning("Length > 255 - truncated");
Referrer = Referrer.substring(0,254);
}
set_ValueNoCheck ("Referrer", Referrer);
}
/** Get Referrer.
@return Referring web address */
public String getReferrer() 
{
return (String)get_Value("Referrer");
}
/** Column name Referrer */
public static final String COLUMNNAME_Referrer = "Referrer";
/** Set Remote Addr.
@param Remote_Addr Remote Address */
public void setRemote_Addr (String Remote_Addr)
{
if (Remote_Addr != null && Remote_Addr.length() > 60)
{
log.warning("Length > 60 - truncated");
Remote_Addr = Remote_Addr.substring(0,59);
}
set_ValueNoCheck ("Remote_Addr", Remote_Addr);
}
/** Get Remote Addr.
@return Remote Address */
public String getRemote_Addr() 
{
return (String)get_Value("Remote_Addr");
}
/** Column name Remote_Addr */
public static final String COLUMNNAME_Remote_Addr = "Remote_Addr";
/** Set Remote Host.
@param Remote_Host Remote host Info */
public void setRemote_Host (String Remote_Host)
{
if (Remote_Host != null && Remote_Host.length() > 60)
{
log.warning("Length > 60 - truncated");
Remote_Host = Remote_Host.substring(0,59);
}
set_ValueNoCheck ("Remote_Host", Remote_Host);
}
/** Get Remote Host.
@return Remote host Info */
public String getRemote_Host() 
{
return (String)get_Value("Remote_Host");
}
/** Column name Remote_Host */
public static final String COLUMNNAME_Remote_Host = "Remote_Host";
/** Set Serial No.
@param SerNo Product Serial Number  */
public void setSerNo (String SerNo)
{
if (SerNo != null && SerNo.length() > 40)
{
log.warning("Length > 40 - truncated");
SerNo = SerNo.substring(0,39);
}
set_ValueNoCheck ("SerNo", SerNo);
}
/** Get Serial No.
@return Product Serial Number  */
public String getSerNo() 
{
return (String)get_Value("SerNo");
}
/** Column name SerNo */
public static final String COLUMNNAME_SerNo = "SerNo";
/** Set URL.
@param URL Full URL address - e.g. http://www.adempiere.org */
public void setURL (String URL)
{
if (URL != null && URL.length() > 120)
{
log.warning("Length > 120 - truncated");
URL = URL.substring(0,119);
}
set_ValueNoCheck ("URL", URL);
}
/** Get URL.
@return Full URL address - e.g. http://www.adempiere.org */
public String getURL() 
{
return (String)get_Value("URL");
}
/** Column name URL */
public static final String COLUMNNAME_URL = "URL";
/** Set Version No.
@param VersionNo Version Number */
public void setVersionNo (String VersionNo)
{
if (VersionNo != null && VersionNo.length() > 20)
{
log.warning("Length > 20 - truncated");
VersionNo = VersionNo.substring(0,19);
}
set_ValueNoCheck ("VersionNo", VersionNo);
}
/** Get Version No.
@return Version Number */
public String getVersionNo() 
{
return (String)get_Value("VersionNo");
}
/** Column name VersionNo */
public static final String COLUMNNAME_VersionNo = "VersionNo";
}
