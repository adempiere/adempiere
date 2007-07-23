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
/** Generated Model for AD_UserMail
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_AD_UserMail extends PO
{
/** Standard Constructor
@param ctx context
@param AD_UserMail_ID id
@param trxName transaction
*/
public X_AD_UserMail (Properties ctx, int AD_UserMail_ID, String trxName)
{
super (ctx, AD_UserMail_ID, trxName);
/** if (AD_UserMail_ID == 0)
{
setAD_UserMail_ID (0);
setAD_User_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_UserMail (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=AD_UserMail */
public static final String Table_Name="AD_UserMail";

/** AD_Table_ID=782 */
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
StringBuffer sb = new StringBuffer ("X_AD_UserMail[").append(get_ID()).append("]");
return sb.toString();
}
/** Set User Mail.
@param AD_UserMail_ID Mail sent to the user */
public void setAD_UserMail_ID (int AD_UserMail_ID)
{
if (AD_UserMail_ID < 1) throw new IllegalArgumentException ("AD_UserMail_ID is mandatory.");
set_ValueNoCheck ("AD_UserMail_ID", Integer.valueOf(AD_UserMail_ID));
}
/** Get User Mail.
@return Mail sent to the user */
public int getAD_UserMail_ID() 
{
Integer ii = (Integer)get_Value("AD_UserMail_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_UserMail_ID */
public static final String COLUMNNAME_AD_UserMail_ID = "AD_UserMail_ID";
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID < 1) throw new IllegalArgumentException ("AD_User_ID is mandatory.");
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_User_ID()));
}
/** Column name AD_User_ID */
public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";
/** Set Delivery Confirmation.
@param DeliveryConfirmation EMail Delivery confirmation */
public void setDeliveryConfirmation (String DeliveryConfirmation)
{
if (DeliveryConfirmation != null && DeliveryConfirmation.length() > 120)
{
log.warning("Length > 120 - truncated");
DeliveryConfirmation = DeliveryConfirmation.substring(0,119);
}
set_ValueNoCheck ("DeliveryConfirmation", DeliveryConfirmation);
}
/** Get Delivery Confirmation.
@return EMail Delivery confirmation */
public String getDeliveryConfirmation() 
{
return (String)get_Value("DeliveryConfirmation");
}
/** Column name DeliveryConfirmation */
public static final String COLUMNNAME_DeliveryConfirmation = "DeliveryConfirmation";

/** IsDelivered AD_Reference_ID=319 */
public static final int ISDELIVERED_AD_Reference_ID=319;
/** No = N */
public static final String ISDELIVERED_No = "N";
/** Yes = Y */
public static final String ISDELIVERED_Yes = "Y";
/** Set Delivered.
@param IsDelivered Delivered */
public void setIsDelivered (String IsDelivered)
{
if (IsDelivered == null || IsDelivered.equals("N") || IsDelivered.equals("Y"));
 else throw new IllegalArgumentException ("IsDelivered Invalid value - " + IsDelivered + " - Reference_ID=319 - N - Y");
if (IsDelivered != null && IsDelivered.length() > 1)
{
log.warning("Length > 1 - truncated");
IsDelivered = IsDelivered.substring(0,0);
}
set_ValueNoCheck ("IsDelivered", IsDelivered);
}
/** Get Delivered.
@return Delivered */
public String getIsDelivered() 
{
return (String)get_Value("IsDelivered");
}
/** Column name IsDelivered */
public static final String COLUMNNAME_IsDelivered = "IsDelivered";
/** Set Mail Text.
@param MailText Text used for Mail message */
public void setMailText (String MailText)
{
if (MailText != null && MailText.length() > 2000)
{
log.warning("Length > 2000 - truncated");
MailText = MailText.substring(0,1999);
}
set_Value ("MailText", MailText);
}
/** Get Mail Text.
@return Text used for Mail message */
public String getMailText() 
{
return (String)get_Value("MailText");
}
/** Column name MailText */
public static final String COLUMNNAME_MailText = "MailText";
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
/** Set Mail Template.
@param R_MailText_ID Text templates for mailings */
public void setR_MailText_ID (int R_MailText_ID)
{
if (R_MailText_ID <= 0) set_ValueNoCheck ("R_MailText_ID", null);
 else 
set_ValueNoCheck ("R_MailText_ID", Integer.valueOf(R_MailText_ID));
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
/** Set Subject.
@param Subject Email Message Subject */
public void setSubject (String Subject)
{
if (Subject != null && Subject.length() > 255)
{
log.warning("Length > 255 - truncated");
Subject = Subject.substring(0,254);
}
set_Value ("Subject", Subject);
}
/** Get Subject.
@return Email Message Subject */
public String getSubject() 
{
return (String)get_Value("Subject");
}
/** Column name Subject */
public static final String COLUMNNAME_Subject = "Subject";
/** Set Mail Message.
@param W_MailMsg_ID Web Store Mail Message Template */
public void setW_MailMsg_ID (int W_MailMsg_ID)
{
if (W_MailMsg_ID <= 0) set_ValueNoCheck ("W_MailMsg_ID", null);
 else 
set_ValueNoCheck ("W_MailMsg_ID", Integer.valueOf(W_MailMsg_ID));
}
/** Get Mail Message.
@return Web Store Mail Message Template */
public int getW_MailMsg_ID() 
{
Integer ii = (Integer)get_Value("W_MailMsg_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name W_MailMsg_ID */
public static final String COLUMNNAME_W_MailMsg_ID = "W_MailMsg_ID";
}
