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
/** Generated Model for AD_AlertRule
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_AD_AlertRule extends PO
{
/** Standard Constructor
@param ctx context
@param AD_AlertRule_ID id
@param trxName transaction
*/
public X_AD_AlertRule (Properties ctx, int AD_AlertRule_ID, String trxName)
{
super (ctx, AD_AlertRule_ID, trxName);
/** if (AD_AlertRule_ID == 0)
{
setAD_AlertRule_ID (0);
setAD_Alert_ID (0);
setFromClause (null);
setIsValid (true);	// Y
setName (null);
setSelectClause (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_AlertRule (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=593 */
public static final int Table_ID=MTable.getTable_ID("AD_AlertRule");

/** TableName=AD_AlertRule */
public static final String Table_Name="AD_AlertRule";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_AlertRule");

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
StringBuffer sb = new StringBuffer ("X_AD_AlertRule[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Alert Rule.
@param AD_AlertRule_ID Definition of the alert element */
public void setAD_AlertRule_ID (int AD_AlertRule_ID)
{
if (AD_AlertRule_ID < 1) throw new IllegalArgumentException ("AD_AlertRule_ID is mandatory.");
set_ValueNoCheck ("AD_AlertRule_ID", Integer.valueOf(AD_AlertRule_ID));
}
/** Get Alert Rule.
@return Definition of the alert element */
public int getAD_AlertRule_ID() 
{
Integer ii = (Integer)get_Value("AD_AlertRule_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Alert.
@param AD_Alert_ID Adempiere Alert */
public void setAD_Alert_ID (int AD_Alert_ID)
{
if (AD_Alert_ID < 1) throw new IllegalArgumentException ("AD_Alert_ID is mandatory.");
set_ValueNoCheck ("AD_Alert_ID", Integer.valueOf(AD_Alert_ID));
}
/** Get Alert.
@return Adempiere Alert */
public int getAD_Alert_ID() 
{
Integer ii = (Integer)get_Value("AD_Alert_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID <= 0) set_Value ("AD_Table_ID", null);
 else 
set_Value ("AD_Table_ID", Integer.valueOf(AD_Table_ID));
}
/** Get Table.
@return Database Table information */
public int getAD_Table_ID() 
{
Integer ii = (Integer)get_Value("AD_Table_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Error Msg.
@param ErrorMsg Error Msg */
public void setErrorMsg (String ErrorMsg)
{
if (ErrorMsg != null && ErrorMsg.length() > 2000)
{
log.warning("Length > 2000 - truncated");
ErrorMsg = ErrorMsg.substring(0,1999);
}
set_Value ("ErrorMsg", ErrorMsg);
}
/** Get Error Msg.
@return Error Msg */
public String getErrorMsg() 
{
return (String)get_Value("ErrorMsg");
}
/** Set Sql FROM.
@param FromClause SQL FROM clause */
public void setFromClause (String FromClause)
{
if (FromClause == null) throw new IllegalArgumentException ("FromClause is mandatory.");
if (FromClause.length() > 2000)
{
log.warning("Length > 2000 - truncated");
FromClause = FromClause.substring(0,1999);
}
set_Value ("FromClause", FromClause);
}
/** Get Sql FROM.
@return SQL FROM clause */
public String getFromClause() 
{
return (String)get_Value("FromClause");
}
/** Set Valid.
@param IsValid Element is valid */
public void setIsValid (boolean IsValid)
{
set_Value ("IsValid", Boolean.valueOf(IsValid));
}
/** Get Valid.
@return Element is valid */
public boolean isValid() 
{
Object oo = get_Value("IsValid");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
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
/** Set Other SQL Clause.
@param OtherClause Other SQL Clause */
public void setOtherClause (String OtherClause)
{
if (OtherClause != null && OtherClause.length() > 2000)
{
log.warning("Length > 2000 - truncated");
OtherClause = OtherClause.substring(0,1999);
}
set_Value ("OtherClause", OtherClause);
}
/** Get Other SQL Clause.
@return Other SQL Clause */
public String getOtherClause() 
{
return (String)get_Value("OtherClause");
}
/** Set Post Processing.
@param PostProcessing Process SQL after executing the query */
public void setPostProcessing (String PostProcessing)
{
if (PostProcessing != null && PostProcessing.length() > 2000)
{
log.warning("Length > 2000 - truncated");
PostProcessing = PostProcessing.substring(0,1999);
}
set_Value ("PostProcessing", PostProcessing);
}
/** Get Post Processing.
@return Process SQL after executing the query */
public String getPostProcessing() 
{
return (String)get_Value("PostProcessing");
}
/** Set Pre Processing.
@param PreProcessing Process SQL before executing the query */
public void setPreProcessing (String PreProcessing)
{
if (PreProcessing != null && PreProcessing.length() > 2000)
{
log.warning("Length > 2000 - truncated");
PreProcessing = PreProcessing.substring(0,1999);
}
set_Value ("PreProcessing", PreProcessing);
}
/** Get Pre Processing.
@return Process SQL before executing the query */
public String getPreProcessing() 
{
return (String)get_Value("PreProcessing");
}
/** Set Sql SELECT.
@param SelectClause SQL SELECT clause */
public void setSelectClause (String SelectClause)
{
if (SelectClause == null) throw new IllegalArgumentException ("SelectClause is mandatory.");
if (SelectClause.length() > 2000)
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
