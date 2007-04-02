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
/** Generated Model for K_Comment
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_K_Comment extends PO
{
/** Standard Constructor
@param ctx context
@param K_Comment_ID id
@param trxName transaction
*/
public X_K_Comment (Properties ctx, int K_Comment_ID, String trxName)
{
super (ctx, K_Comment_ID, trxName);
/** if (K_Comment_ID == 0)
{
setIsPublic (true);	// Y
setK_Comment_ID (0);
setK_Entry_ID (0);
setRating (0);
setTextMsg (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_K_Comment (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=613 */
public static final int Table_ID=MTable.getTable_ID("K_Comment");

/** TableName=K_Comment */
public static final String Table_Name="K_Comment";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"K_Comment");

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
StringBuffer sb = new StringBuffer ("X_K_Comment[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Session.
@param AD_Session_ID User Session Online or Web */
public void setAD_Session_ID (int AD_Session_ID)
{
if (AD_Session_ID <= 0) set_ValueNoCheck ("AD_Session_ID", null);
 else 
set_ValueNoCheck ("AD_Session_ID", Integer.valueOf(AD_Session_ID));
}
/** Get Session.
@return User Session Online or Web */
public int getAD_Session_ID() 
{
Integer ii = (Integer)get_Value("AD_Session_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Session_ID */
public static final String COLUMNNAME_AD_Session_ID = "AD_Session_ID";
/** Set Public.
@param IsPublic Public can read entry */
public void setIsPublic (boolean IsPublic)
{
set_Value ("IsPublic", Boolean.valueOf(IsPublic));
}
/** Get Public.
@return Public can read entry */
public boolean isPublic() 
{
Object oo = get_Value("IsPublic");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPublic */
public static final String COLUMNNAME_IsPublic = "IsPublic";
/** Set Entry Comment.
@param K_Comment_ID Knowledge Entry Comment */
public void setK_Comment_ID (int K_Comment_ID)
{
if (K_Comment_ID < 1) throw new IllegalArgumentException ("K_Comment_ID is mandatory.");
set_ValueNoCheck ("K_Comment_ID", Integer.valueOf(K_Comment_ID));
}
/** Get Entry Comment.
@return Knowledge Entry Comment */
public int getK_Comment_ID() 
{
Integer ii = (Integer)get_Value("K_Comment_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getK_Comment_ID()));
}
/** Column name K_Comment_ID */
public static final String COLUMNNAME_K_Comment_ID = "K_Comment_ID";
/** Set Entry.
@param K_Entry_ID Knowledge Entry */
public void setK_Entry_ID (int K_Entry_ID)
{
if (K_Entry_ID < 1) throw new IllegalArgumentException ("K_Entry_ID is mandatory.");
set_ValueNoCheck ("K_Entry_ID", Integer.valueOf(K_Entry_ID));
}
/** Get Entry.
@return Knowledge Entry */
public int getK_Entry_ID() 
{
Integer ii = (Integer)get_Value("K_Entry_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name K_Entry_ID */
public static final String COLUMNNAME_K_Entry_ID = "K_Entry_ID";
/** Set Rating.
@param Rating Classification or Importance */
public void setRating (int Rating)
{
set_Value ("Rating", Integer.valueOf(Rating));
}
/** Get Rating.
@return Classification or Importance */
public int getRating() 
{
Integer ii = (Integer)get_Value("Rating");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Rating */
public static final String COLUMNNAME_Rating = "Rating";
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
}
