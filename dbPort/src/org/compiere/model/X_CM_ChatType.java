/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
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
/** Generated Model for CM_ChatType
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_CM_ChatType extends PO
{
/** Standard Constructor
@param ctx context
@param CM_ChatType_ID id
@param trxName transaction
*/
public X_CM_ChatType (Properties ctx, int CM_ChatType_ID, String trxName)
{
super (ctx, CM_ChatType_ID, trxName);
/** if (CM_ChatType_ID == 0)
{
setAD_Table_ID (0);
setCM_ChatType_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_CM_ChatType (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=874 */
public static final int Table_ID=874;

/** TableName=CM_ChatType */
public static final String Table_Name="CM_ChatType";

protected static KeyNamePair Model = new KeyNamePair(874,"CM_ChatType");

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
StringBuffer sb = new StringBuffer ("X_CM_ChatType[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID < 1) throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
set_Value ("AD_Table_ID", new Integer(AD_Table_ID));
}
/** Get Table.
@return Database Table information */
public int getAD_Table_ID() 
{
Integer ii = (Integer)get_Value("AD_Table_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Chat Type.
@param CM_ChatType_ID Type of discussion / chat */
public void setCM_ChatType_ID (int CM_ChatType_ID)
{
if (CM_ChatType_ID < 1) throw new IllegalArgumentException ("CM_ChatType_ID is mandatory.");
set_ValueNoCheck ("CM_ChatType_ID", new Integer(CM_ChatType_ID));
}
/** Get Chat Type.
@return Type of discussion / chat */
public int getCM_ChatType_ID() 
{
Integer ii = (Integer)get_Value("CM_ChatType_ID");
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

/** ModerationType AD_Reference_ID=395 */
public static final int MODERATIONTYPE_AD_Reference_ID=395;
/** After Publishing = A */
public static final String MODERATIONTYPE_AfterPublishing = "A";
/** Before Publishing = B */
public static final String MODERATIONTYPE_BeforePublishing = "B";
/** Not moderated = N */
public static final String MODERATIONTYPE_NotModerated = "N";
/** Set Moderation Type.
@param ModerationType Type of moderation */
public void setModerationType (String ModerationType)
{
if (ModerationType == null || ModerationType.equals("A") || ModerationType.equals("B") || ModerationType.equals("N"));
 else throw new IllegalArgumentException ("ModerationType Invalid value - " + ModerationType + " - Reference_ID=395 - A - B - N");
if (ModerationType != null && ModerationType.length() > 1)
{
log.warning("Length > 1 - truncated");
ModerationType = ModerationType.substring(0,0);
}
set_Value ("ModerationType", ModerationType);
}
/** Get Moderation Type.
@return Type of moderation */
public String getModerationType() 
{
return (String)get_Value("ModerationType");
}
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 120)
{
log.warning("Length > 120 - truncated");
Name = Name.substring(0,119);
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
}
