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
/** Generated Model for AD_Attachment
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:52.203 */
public class X_AD_Attachment extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Attachment_ID id
@param trxName transaction
*/
public X_AD_Attachment (Properties ctx, int AD_Attachment_ID, String trxName)
{
super (ctx, AD_Attachment_ID, trxName);
/** if (AD_Attachment_ID == 0)
{
setAD_Attachment_ID (0);
setAD_Table_ID (0);
setRecord_ID (0);
setTitle (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Attachment (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=254 */
public static final int Table_ID=254;

/** TableName=AD_Attachment */
public static final String Table_Name="AD_Attachment";

protected static KeyNamePair Model = new KeyNamePair(254,"AD_Attachment");

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
StringBuffer sb = new StringBuffer ("X_AD_Attachment[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Attachment.
@param AD_Attachment_ID Attachment for the document */
public void setAD_Attachment_ID (int AD_Attachment_ID)
{
if (AD_Attachment_ID < 1) throw new IllegalArgumentException ("AD_Attachment_ID is mandatory.");
set_ValueNoCheck ("AD_Attachment_ID", new Integer(AD_Attachment_ID));
}
/** Get Attachment.
@return Attachment for the document */
public int getAD_Attachment_ID() 
{
Integer ii = (Integer)get_Value("AD_Attachment_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID < 1) throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
set_ValueNoCheck ("AD_Table_ID", new Integer(AD_Table_ID));
}
/** Get Table.
@return Database Table information */
public int getAD_Table_ID() 
{
Integer ii = (Integer)get_Value("AD_Table_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set BinaryData.
@param BinaryData Binary Data */
public void setBinaryData (byte[] BinaryData)
{
set_ValueNoCheck ("BinaryData", BinaryData);
}
/** Get BinaryData.
@return Binary Data */
public byte[] getBinaryData() 
{
return (byte[])get_Value("BinaryData");
}
/** Set Record ID.
@param Record_ID Direct internal record ID */
public void setRecord_ID (int Record_ID)
{
if (Record_ID < 0) throw new IllegalArgumentException ("Record_ID is mandatory.");
set_ValueNoCheck ("Record_ID", new Integer(Record_ID));
}
/** Get Record ID.
@return Direct internal record ID */
public int getRecord_ID() 
{
Integer ii = (Integer)get_Value("Record_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Text Message.
@param TextMsg Text Message */
public void setTextMsg (String TextMsg)
{
if (TextMsg != null && TextMsg.length() > 2000)
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
}
