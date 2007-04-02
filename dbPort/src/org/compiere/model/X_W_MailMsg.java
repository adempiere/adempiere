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
/** Generated Model for W_MailMsg
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_W_MailMsg extends PO
{
/** Standard Constructor
@param ctx context
@param W_MailMsg_ID id
@param trxName transaction
*/
public X_W_MailMsg (Properties ctx, int W_MailMsg_ID, String trxName)
{
super (ctx, W_MailMsg_ID, trxName);
/** if (W_MailMsg_ID == 0)
{
setMailMsgType (null);
setMessage (null);
setName (null);
setSubject (null);
setW_MailMsg_ID (0);
setW_Store_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_W_MailMsg (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=780 */
public static final int Table_ID=MTable.getTable_ID("W_MailMsg");

/** TableName=W_MailMsg */
public static final String Table_Name="W_MailMsg";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"W_MailMsg");

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
StringBuffer sb = new StringBuffer ("X_W_MailMsg[").append(get_ID()).append("]");
return sb.toString();
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
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";

/** MailMsgType AD_Reference_ID=342 */
public static final int MAILMSGTYPE_AD_Reference_ID=342;
/** Subscribe = LS */
public static final String MAILMSGTYPE_Subscribe = "LS";
/** UnSubscribe = LU */
public static final String MAILMSGTYPE_UnSubscribe = "LU";
/** Order Acknowledgement = OA */
public static final String MAILMSGTYPE_OrderAcknowledgement = "OA";
/** Payment Acknowledgement = PA */
public static final String MAILMSGTYPE_PaymentAcknowledgement = "PA";
/** Payment Error = PE */
public static final String MAILMSGTYPE_PaymentError = "PE";
/** User Account = UA */
public static final String MAILMSGTYPE_UserAccount = "UA";
/** User Password = UP */
public static final String MAILMSGTYPE_UserPassword = "UP";
/** User Verification = UV */
public static final String MAILMSGTYPE_UserVerification = "UV";
/** Request = WR */
public static final String MAILMSGTYPE_Request = "WR";
/** Set Message Type.
@param MailMsgType Mail Message Type */
public void setMailMsgType (String MailMsgType)
{
if (MailMsgType == null) throw new IllegalArgumentException ("MailMsgType is mandatory");
if (MailMsgType.equals("LS") || MailMsgType.equals("LU") || MailMsgType.equals("OA") || MailMsgType.equals("PA") || MailMsgType.equals("PE") || MailMsgType.equals("UA") || MailMsgType.equals("UP") || MailMsgType.equals("UV") || MailMsgType.equals("WR"));
 else throw new IllegalArgumentException ("MailMsgType Invalid value - " + MailMsgType + " - Reference_ID=342 - LS - LU - OA - PA - PE - UA - UP - UV - WR");
if (MailMsgType.length() > 2)
{
log.warning("Length > 2 - truncated");
MailMsgType = MailMsgType.substring(0,1);
}
set_Value ("MailMsgType", MailMsgType);
}
/** Get Message Type.
@return Mail Message Type */
public String getMailMsgType() 
{
return (String)get_Value("MailMsgType");
}
/** Column name MailMsgType */
public static final String COLUMNNAME_MailMsgType = "MailMsgType";
/** Set Message.
@param Message EMail Message */
public void setMessage (String Message)
{
if (Message == null) throw new IllegalArgumentException ("Message is mandatory.");
if (Message.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Message = Message.substring(0,1999);
}
set_Value ("Message", Message);
}
/** Get Message.
@return EMail Message */
public String getMessage() 
{
return (String)get_Value("Message");
}
/** Column name Message */
public static final String COLUMNNAME_Message = "Message";
/** Set Message 2.
@param Message2 Optional second part of the EMail Message */
public void setMessage2 (String Message2)
{
if (Message2 != null && Message2.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Message2 = Message2.substring(0,1999);
}
set_Value ("Message2", Message2);
}
/** Get Message 2.
@return Optional second part of the EMail Message */
public String getMessage2() 
{
return (String)get_Value("Message2");
}
/** Column name Message2 */
public static final String COLUMNNAME_Message2 = "Message2";
/** Set Message 3.
@param Message3 Optional third part of the EMail Message */
public void setMessage3 (String Message3)
{
if (Message3 != null && Message3.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Message3 = Message3.substring(0,1999);
}
set_Value ("Message3", Message3);
}
/** Get Message 3.
@return Optional third part of the EMail Message */
public String getMessage3() 
{
return (String)get_Value("Message3");
}
/** Column name Message3 */
public static final String COLUMNNAME_Message3 = "Message3";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Subject.
@param Subject Email Message Subject */
public void setSubject (String Subject)
{
if (Subject == null) throw new IllegalArgumentException ("Subject is mandatory.");
if (Subject.length() > 255)
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
if (W_MailMsg_ID < 1) throw new IllegalArgumentException ("W_MailMsg_ID is mandatory.");
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
/** Set Web Store.
@param W_Store_ID A Web Store of the Client */
public void setW_Store_ID (int W_Store_ID)
{
if (W_Store_ID < 1) throw new IllegalArgumentException ("W_Store_ID is mandatory.");
set_Value ("W_Store_ID", Integer.valueOf(W_Store_ID));
}
/** Get Web Store.
@return A Web Store of the Client */
public int getW_Store_ID() 
{
Integer ii = (Integer)get_Value("W_Store_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name W_Store_ID */
public static final String COLUMNNAME_W_Store_ID = "W_Store_ID";
}
