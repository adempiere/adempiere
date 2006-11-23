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
/** Generated Model for AD_Find
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_AD_Find extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Find_ID id
@param trxName transaction
*/
public X_AD_Find (Properties ctx, int AD_Find_ID, String trxName)
{
super (ctx, AD_Find_ID, trxName);
/** if (AD_Find_ID == 0)
{
setAD_Column_ID (0);
setAD_Find_ID (0);
setAndOr (null);	// A
setFind_ID (Env.ZERO);
setOperation (null);	// ==
setValue (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Find (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=404 */
public static final int Table_ID=404;

/** TableName=AD_Find */
public static final String Table_Name="AD_Find";

protected static KeyNamePair Model = new KeyNamePair(404,"AD_Find");

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
StringBuffer sb = new StringBuffer ("X_AD_Find[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_Column_ID AD_Reference_ID=251 */
public static final int AD_COLUMN_ID_AD_Reference_ID=251;
/** Set Column.
@param AD_Column_ID Column in the table */
public void setAD_Column_ID (int AD_Column_ID)
{
if (AD_Column_ID < 1) throw new IllegalArgumentException ("AD_Column_ID is mandatory.");
set_Value ("AD_Column_ID", new Integer(AD_Column_ID));
}
/** Get Column.
@return Column in the table */
public int getAD_Column_ID() 
{
Integer ii = (Integer)get_Value("AD_Column_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Find.
@param AD_Find_ID Find */
public void setAD_Find_ID (int AD_Find_ID)
{
if (AD_Find_ID < 1) throw new IllegalArgumentException ("AD_Find_ID is mandatory.");
set_ValueNoCheck ("AD_Find_ID", new Integer(AD_Find_ID));
}
/** Get Find.
@return Find */
public int getAD_Find_ID() 
{
Integer ii = (Integer)get_Value("AD_Find_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_Find_ID()));
}

/** AndOr AD_Reference_ID=204 */
public static final int ANDOR_AD_Reference_ID=204;
/** And = A */
public static final String ANDOR_And = "A";
/** Or = O */
public static final String ANDOR_Or = "O";
/** Set And/Or.
@param AndOr Logical operation: AND or OR */
public void setAndOr (String AndOr)
{
if (AndOr == null) throw new IllegalArgumentException ("AndOr is mandatory");
if (AndOr.equals("A") || AndOr.equals("O"));
 else throw new IllegalArgumentException ("AndOr Invalid value - " + AndOr + " - Reference_ID=204 - A - O");
if (AndOr.length() > 1)
{
log.warning("Length > 1 - truncated");
AndOr = AndOr.substring(0,0);
}
set_Value ("AndOr", AndOr);
}
/** Get And/Or.
@return Logical operation: AND or OR */
public String getAndOr() 
{
return (String)get_Value("AndOr");
}
/** Set Find_ID.
@param Find_ID Find_ID */
public void setFind_ID (BigDecimal Find_ID)
{
if (Find_ID == null) throw new IllegalArgumentException ("Find_ID is mandatory.");
set_Value ("Find_ID", Find_ID);
}
/** Get Find_ID.
@return Find_ID */
public BigDecimal getFind_ID() 
{
BigDecimal bd = (BigDecimal)get_Value("Find_ID");
if (bd == null) return Env.ZERO;
return bd;
}

/** Operation AD_Reference_ID=205 */
public static final int OPERATION_AD_Reference_ID=205;
/** != = != */
public static final String OPERATION_NotEq = "!=";
/** < = << */
public static final String OPERATION_Le = "<<";
/** <= = <= */
public static final String OPERATION_LeEq = "<=";
/**  = = == */
public static final String OPERATION_Eq = "==";
/** >= = >= */
public static final String OPERATION_GtEq = ">=";
/** > = >> */
public static final String OPERATION_Gt = ">>";
/** |<x>| = AB */
public static final String OPERATION_X = "AB";
/** sql = SQ */
public static final String OPERATION_Sql = "SQ";
/**  ~ = ~~ */
public static final String OPERATION_Like = "~~";
/** Set Operation.
@param Operation Compare Operation */
public void setOperation (String Operation)
{
if (Operation == null) throw new IllegalArgumentException ("Operation is mandatory");
if (Operation.equals("!=") || Operation.equals("<<") || Operation.equals("<=") || Operation.equals("==") || Operation.equals(">=") || Operation.equals(">>") || Operation.equals("AB") || Operation.equals("SQ") || Operation.equals("~~"));
 else throw new IllegalArgumentException ("Operation Invalid value - " + Operation + " - Reference_ID=205 - != - << - <= - == - >= - >> - AB - SQ - ~~");
if (Operation.length() > 2)
{
log.warning("Length > 2 - truncated");
Operation = Operation.substring(0,1);
}
set_Value ("Operation", Operation);
}
/** Get Operation.
@return Compare Operation */
public String getOperation() 
{
return (String)get_Value("Operation");
}
/** Set Search Key.
@param Value Search key for the record in the format required - must be unique */
public void setValue (String Value)
{
if (Value == null) throw new IllegalArgumentException ("Value is mandatory.");
if (Value.length() > 40)
{
log.warning("Length > 40 - truncated");
Value = Value.substring(0,39);
}
set_Value ("Value", Value);
}
/** Get Search Key.
@return Search key for the record in the format required - must be unique */
public String getValue() 
{
return (String)get_Value("Value");
}
/** Set Value To.
@param Value2 Value To */
public void setValue2 (String Value2)
{
if (Value2 != null && Value2.length() > 40)
{
log.warning("Length > 40 - truncated");
Value2 = Value2.substring(0,39);
}
set_Value ("Value2", Value2);
}
/** Get Value To.
@return Value To */
public String getValue2() 
{
return (String)get_Value("Value2");
}
}
