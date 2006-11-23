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
/** Generated Model for CM_WikiToken
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_CM_WikiToken extends PO
{
/** Standard Constructor
@param ctx context
@param CM_WikiToken_ID id
@param trxName transaction
*/
public X_CM_WikiToken (Properties ctx, int CM_WikiToken_ID, String trxName)
{
super (ctx, CM_WikiToken_ID, trxName);
/** if (CM_WikiToken_ID == 0)
{
setCM_WikiToken_ID (0);
setName (null);
setTokenType (null);	// I
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_CM_WikiToken (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=905 */
public static final int Table_ID=905;

/** TableName=CM_WikiToken */
public static final String Table_Name="CM_WikiToken";

protected static KeyNamePair Model = new KeyNamePair(905,"CM_WikiToken");

protected BigDecimal accessLevel = new BigDecimal(7);
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
StringBuffer sb = new StringBuffer ("X_CM_WikiToken[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID <= 0) set_ValueNoCheck ("AD_Table_ID", null);
 else 
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
/** Set Wiki Token.
@param CM_WikiToken_ID Wiki Token */
public void setCM_WikiToken_ID (int CM_WikiToken_ID)
{
if (CM_WikiToken_ID < 1) throw new IllegalArgumentException ("CM_WikiToken_ID is mandatory.");
set_ValueNoCheck ("CM_WikiToken_ID", new Integer(CM_WikiToken_ID));
}
/** Get Wiki Token.
@return Wiki Token */
public int getCM_WikiToken_ID() 
{
Integer ii = (Integer)get_Value("CM_WikiToken_ID");
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
/** Set Macro.
@param Macro Macro */
public void setMacro (String Macro)
{
if (Macro != null && Macro.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Macro = Macro.substring(0,1999);
}
set_Value ("Macro", Macro);
}
/** Get Macro.
@return Macro */
public String getMacro() 
{
return (String)get_Value("Macro");
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
/** Set Sql SELECT.
@param SelectClause SQL SELECT clause */
public void setSelectClause (String SelectClause)
{
if (SelectClause != null && SelectClause.length() > 2000)
{
log.warning("Length > 2000 - truncated");
SelectClause = SelectClause.substring(0,1999);
}
set_Value ("SelectClause", SelectClause);
}
/** Get Sql SELECT.
@return SQL SELECT clause */
public String getSelectClause() 
{
return (String)get_Value("SelectClause");
}

/** TokenType AD_Reference_ID=397 */
public static final int TOKENTYPE_AD_Reference_ID=397;
/** External Link = E */
public static final String TOKENTYPE_ExternalLink = "E";
/** Internal Link = I */
public static final String TOKENTYPE_InternalLink = "I";
/** SQL Command = Q */
public static final String TOKENTYPE_SQLCommand = "Q";
/** Style = S */
public static final String TOKENTYPE_Style = "S";
/** Set TokenType.
@param TokenType Wiki Token Type */
public void setTokenType (String TokenType)
{
if (TokenType == null) throw new IllegalArgumentException ("TokenType is mandatory");
if (TokenType.equals("E") || TokenType.equals("I") || TokenType.equals("Q") || TokenType.equals("S"));
 else throw new IllegalArgumentException ("TokenType Invalid value - " + TokenType + " - Reference_ID=397 - E - I - Q - S");
if (TokenType.length() > 1)
{
log.warning("Length > 1 - truncated");
TokenType = TokenType.substring(0,0);
}
set_Value ("TokenType", TokenType);
}
/** Get TokenType.
@return Wiki Token Type */
public String getTokenType() 
{
return (String)get_Value("TokenType");
}
/** Set Sql WHERE.
@param WhereClause Fully qualified SQL WHERE clause */
public void setWhereClause (String WhereClause)
{
if (WhereClause != null && WhereClause.length() > 2000)
{
log.warning("Length > 2000 - truncated");
WhereClause = WhereClause.substring(0,1999);
}
set_Value ("WhereClause", WhereClause);
}
/** Get Sql WHERE.
@return Fully qualified SQL WHERE clause */
public String getWhereClause() 
{
return (String)get_Value("WhereClause");
}
}
