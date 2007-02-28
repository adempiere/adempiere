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
/** Generated Model for AD_ReportView_Col
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_AD_ReportView_Col extends PO
{
/** Standard Constructor
@param ctx context
@param AD_ReportView_Col_ID id
@param trxName transaction
*/
public X_AD_ReportView_Col (Properties ctx, int AD_ReportView_Col_ID, String trxName)
{
super (ctx, AD_ReportView_Col_ID, trxName);
/** if (AD_ReportView_Col_ID == 0)
{
setAD_ReportView_Col_ID (0);
setAD_ReportView_ID (0);
setFunctionColumn (null);
setIsGroupFunction (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_ReportView_Col (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=428 */
public static final int Table_ID=MTable.getTable_ID("AD_ReportView_Col");

/** TableName=AD_ReportView_Col */
public static final String Table_Name="AD_ReportView_Col";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_ReportView_Col");

protected BigDecimal accessLevel = BigDecimal.valueOf(4);
/** AccessLevel
@return 4 - System 
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
StringBuffer sb = new StringBuffer ("X_AD_ReportView_Col[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Column.
@param AD_Column_ID Column in the table */
public void setAD_Column_ID (int AD_Column_ID)
{
if (AD_Column_ID <= 0) set_Value ("AD_Column_ID", null);
 else 
set_Value ("AD_Column_ID", Integer.valueOf(AD_Column_ID));
}
/** Get Column.
@return Column in the table */
public int getAD_Column_ID() 
{
Integer ii = (Integer)get_Value("AD_Column_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Column_ID */
public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";
/** Set Report view Column.
@param AD_ReportView_Col_ID Report view Column */
public void setAD_ReportView_Col_ID (int AD_ReportView_Col_ID)
{
if (AD_ReportView_Col_ID < 1) throw new IllegalArgumentException ("AD_ReportView_Col_ID is mandatory.");
set_ValueNoCheck ("AD_ReportView_Col_ID", Integer.valueOf(AD_ReportView_Col_ID));
}
/** Get Report view Column.
@return Report view Column */
public int getAD_ReportView_Col_ID() 
{
Integer ii = (Integer)get_Value("AD_ReportView_Col_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_ReportView_Col_ID */
public static final String COLUMNNAME_AD_ReportView_Col_ID = "AD_ReportView_Col_ID";
/** Set Report View.
@param AD_ReportView_ID View used to generate this report */
public void setAD_ReportView_ID (int AD_ReportView_ID)
{
if (AD_ReportView_ID < 1) throw new IllegalArgumentException ("AD_ReportView_ID is mandatory.");
set_ValueNoCheck ("AD_ReportView_ID", Integer.valueOf(AD_ReportView_ID));
}
/** Get Report View.
@return View used to generate this report */
public int getAD_ReportView_ID() 
{
Integer ii = (Integer)get_Value("AD_ReportView_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_ReportView_ID()));
}
/** Column name AD_ReportView_ID */
public static final String COLUMNNAME_AD_ReportView_ID = "AD_ReportView_ID";
/** Set Function Column.
@param FunctionColumn Overwrite Column with Function  */
public void setFunctionColumn (String FunctionColumn)
{
if (FunctionColumn == null) throw new IllegalArgumentException ("FunctionColumn is mandatory.");
if (FunctionColumn.length() > 60)
{
log.warning("Length > 60 - truncated");
FunctionColumn = FunctionColumn.substring(0,59);
}
set_Value ("FunctionColumn", FunctionColumn);
}
/** Get Function Column.
@return Overwrite Column with Function  */
public String getFunctionColumn() 
{
return (String)get_Value("FunctionColumn");
}
/** Column name FunctionColumn */
public static final String COLUMNNAME_FunctionColumn = "FunctionColumn";
/** Set SQL Group Function.
@param IsGroupFunction This function will generate a Group By Clause */
public void setIsGroupFunction (boolean IsGroupFunction)
{
set_Value ("IsGroupFunction", Boolean.valueOf(IsGroupFunction));
}
/** Get SQL Group Function.
@return This function will generate a Group By Clause */
public boolean isGroupFunction() 
{
Object oo = get_Value("IsGroupFunction");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsGroupFunction */
public static final String COLUMNNAME_IsGroupFunction = "IsGroupFunction";
}
