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
/** Generated Model for C_Currency
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_C_Currency extends PO
{
/** Standard Constructor
@param ctx context
@param C_Currency_ID id
@param trxName transaction
*/
public X_C_Currency (Properties ctx, int C_Currency_ID, String trxName)
{
super (ctx, C_Currency_ID, trxName);
/** if (C_Currency_ID == 0)
{
setC_Currency_ID (0);
setCostingPrecision (0);	// 4
setDescription (null);
setISO_Code (null);
setIsEMUMember (false);	// N
setIsEuro (false);	// N
setStdPrecision (0);	// 2
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Currency (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=141 */
public static final int Table_ID=MTable.getTable_ID("C_Currency");

/** TableName=C_Currency */
public static final String Table_Name="C_Currency";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_Currency");

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
StringBuffer sb = new StringBuffer ("X_C_Currency[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID < 1) throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
set_ValueNoCheck ("C_Currency_ID", Integer.valueOf(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Costing Precision.
@param CostingPrecision Rounding used costing calculations */
public void setCostingPrecision (int CostingPrecision)
{
set_Value ("CostingPrecision", Integer.valueOf(CostingPrecision));
}
/** Get Costing Precision.
@return Rounding used costing calculations */
public int getCostingPrecision() 
{
Integer ii = (Integer)get_Value("CostingPrecision");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Symbol.
@param CurSymbol Symbol of the currency (opt used for printing only) */
public void setCurSymbol (String CurSymbol)
{
if (CurSymbol != null && CurSymbol.length() > 10)
{
log.warning("Length > 10 - truncated");
CurSymbol = CurSymbol.substring(0,9);
}
set_Value ("CurSymbol", CurSymbol);
}
/** Get Symbol.
@return Symbol of the currency (opt used for printing only) */
public String getCurSymbol() 
{
return (String)get_Value("CurSymbol");
}
/** Set Description.
@param Description Optional short description of the record */
public void setDescription (String Description)
{
if (Description == null) throw new IllegalArgumentException ("Description is mandatory.");
if (Description.length() > 255)
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
/** Set EMU Entry Date.
@param EMUEntryDate Date when the currency joined / will join the EMU */
public void setEMUEntryDate (Timestamp EMUEntryDate)
{
set_Value ("EMUEntryDate", EMUEntryDate);
}
/** Get EMU Entry Date.
@return Date when the currency joined / will join the EMU */
public Timestamp getEMUEntryDate() 
{
return (Timestamp)get_Value("EMUEntryDate");
}
/** Set EMU Rate.
@param EMURate Official rate to the Euro */
public void setEMURate (BigDecimal EMURate)
{
set_Value ("EMURate", EMURate);
}
/** Get EMU Rate.
@return Official rate to the Euro */
public BigDecimal getEMURate() 
{
BigDecimal bd = (BigDecimal)get_Value("EMURate");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set ISO Currency Code.
@param ISO_Code Three letter ISO 4217 Code of the Currency */
public void setISO_Code (String ISO_Code)
{
if (ISO_Code == null) throw new IllegalArgumentException ("ISO_Code is mandatory.");
if (ISO_Code.length() > 3)
{
log.warning("Length > 3 - truncated");
ISO_Code = ISO_Code.substring(0,2);
}
set_Value ("ISO_Code", ISO_Code);
}
/** Get ISO Currency Code.
@return Three letter ISO 4217 Code of the Currency */
public String getISO_Code() 
{
return (String)get_Value("ISO_Code");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getISO_Code());
}
/** Set EMU Member.
@param IsEMUMember This currency is member if the European Monetary Union */
public void setIsEMUMember (boolean IsEMUMember)
{
set_Value ("IsEMUMember", Boolean.valueOf(IsEMUMember));
}
/** Get EMU Member.
@return This currency is member if the European Monetary Union */
public boolean isEMUMember() 
{
Object oo = get_Value("IsEMUMember");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set The Euro Currency.
@param IsEuro This currency is the Euro */
public void setIsEuro (boolean IsEuro)
{
set_Value ("IsEuro", Boolean.valueOf(IsEuro));
}
/** Get The Euro Currency.
@return This currency is the Euro */
public boolean isEuro() 
{
Object oo = get_Value("IsEuro");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Standard Precision.
@param StdPrecision Rule for rounding  calculated amounts */
public void setStdPrecision (int StdPrecision)
{
set_Value ("StdPrecision", Integer.valueOf(StdPrecision));
}
/** Get Standard Precision.
@return Rule for rounding  calculated amounts */
public int getStdPrecision() 
{
Integer ii = (Integer)get_Value("StdPrecision");
if (ii == null) return 0;
return ii.intValue();
}
}
