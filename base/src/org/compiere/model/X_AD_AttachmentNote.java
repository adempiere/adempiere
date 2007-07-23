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
/** Generated Model for AD_AttachmentNote
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_AD_AttachmentNote extends PO
{
/** Standard Constructor
@param ctx context
@param AD_AttachmentNote_ID id
@param trxName transaction
*/
public X_AD_AttachmentNote (Properties ctx, int AD_AttachmentNote_ID, String trxName)
{
super (ctx, AD_AttachmentNote_ID, trxName);
/** if (AD_AttachmentNote_ID == 0)
{
setAD_AttachmentNote_ID (0);
setAD_Attachment_ID (0);
setAD_User_ID (0);
setTextMsg (null);
setTitle (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_AttachmentNote (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=AD_AttachmentNote */
public static final String Table_Name="AD_AttachmentNote";

/** AD_Table_ID=705 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

protected BigDecimal accessLevel = BigDecimal.valueOf(6);
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
StringBuffer sb = new StringBuffer ("X_AD_AttachmentNote[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Attachment Note.
@param AD_AttachmentNote_ID Personal Attachment Note */
public void setAD_AttachmentNote_ID (int AD_AttachmentNote_ID)
{
if (AD_AttachmentNote_ID < 1) throw new IllegalArgumentException ("AD_AttachmentNote_ID is mandatory.");
set_ValueNoCheck ("AD_AttachmentNote_ID", Integer.valueOf(AD_AttachmentNote_ID));
}
/** Get Attachment Note.
@return Personal Attachment Note */
public int getAD_AttachmentNote_ID() 
{
Integer ii = (Integer)get_Value("AD_AttachmentNote_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_AttachmentNote_ID */
public static final String COLUMNNAME_AD_AttachmentNote_ID = "AD_AttachmentNote_ID";
/** Set Attachment.
@param AD_Attachment_ID Attachment for the document */
public void setAD_Attachment_ID (int AD_Attachment_ID)
{
if (AD_Attachment_ID < 1) throw new IllegalArgumentException ("AD_Attachment_ID is mandatory.");
set_ValueNoCheck ("AD_Attachment_ID", Integer.valueOf(AD_Attachment_ID));
}
/** Get Attachment.
@return Attachment for the document */
public int getAD_Attachment_ID() 
{
Integer ii = (Integer)get_Value("AD_Attachment_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Attachment_ID */
public static final String COLUMNNAME_AD_Attachment_ID = "AD_Attachment_ID";
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID < 1) throw new IllegalArgumentException ("AD_User_ID is mandatory.");
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
/** Set Text Message.
@param TextMsg Text Message */
public void setTextMsg (String TextMsg)
{
if (TextMsg == null) throw new IllegalArgumentException ("TextMsg is mandatory.");
if (TextMsg.length() > 2000)
{
log.warning("Length > 2000 - truncated");
TextMsg = TextMsg.substring(0,1999);
}
set_Value ("TextMsg", TextMsg);
}
/** Get Text Message.
@return Text Message */
public String getTextMsg() 
{
return (String)get_Value("TextMsg");
}
/** Column name TextMsg */
public static final String COLUMNNAME_TextMsg = "TextMsg";
/** Set Title.
@param Title Name this entity is referred to as */
public void setTitle (String Title)
{
if (Title == null) throw new IllegalArgumentException ("Title is mandatory.");
if (Title.length() > 60)
{
log.warning("Length > 60 - truncated");
Title = Title.substring(0,59);
}
set_Value ("Title", Title);
}
/** Get Title.
@return Name this entity is referred to as */
public String getTitle() 
{
return (String)get_Value("Title");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getTitle());
}
/** Column name Title */
public static final String COLUMNNAME_Title = "Title";
}
