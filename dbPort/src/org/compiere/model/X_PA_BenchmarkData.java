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
/** Generated Model for PA_BenchmarkData
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:56:02.25 */
public class X_PA_BenchmarkData extends PO
{
/** Standard Constructor
@param ctx context
@param PA_BenchmarkData_ID id
@param trxName transaction
*/
public X_PA_BenchmarkData (Properties ctx, int PA_BenchmarkData_ID, String trxName)
{
super (ctx, PA_BenchmarkData_ID, trxName);
/** if (PA_BenchmarkData_ID == 0)
{
setBenchmarkDate (new Timestamp(System.currentTimeMillis()));
setBenchmarkValue (Env.ZERO);
setName (null);
setPA_BenchmarkData_ID (0);
setPA_Benchmark_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_PA_BenchmarkData (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=834 */
public static final int Table_ID=834;

/** TableName=PA_BenchmarkData */
public static final String Table_Name="PA_BenchmarkData";

protected static KeyNamePair Model = new KeyNamePair(834,"PA_BenchmarkData");

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
StringBuffer sb = new StringBuffer ("X_PA_BenchmarkData[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Date.
@param BenchmarkDate Benchmark Date */
public void setBenchmarkDate (Timestamp BenchmarkDate)
{
if (BenchmarkDate == null) throw new IllegalArgumentException ("BenchmarkDate is mandatory.");
set_Value ("BenchmarkDate", BenchmarkDate);
}
/** Get Date.
@return Benchmark Date */
public Timestamp getBenchmarkDate() 
{
return (Timestamp)get_Value("BenchmarkDate");
}
/** Set Value.
@param BenchmarkValue Benchmark Value */
public void setBenchmarkValue (BigDecimal BenchmarkValue)
{
if (BenchmarkValue == null) throw new IllegalArgumentException ("BenchmarkValue is mandatory.");
set_Value ("BenchmarkValue", BenchmarkValue);
}
/** Get Value.
@return Benchmark Value */
public BigDecimal getBenchmarkValue() 
{
BigDecimal bd = (BigDecimal)get_Value("BenchmarkValue");
if (bd == null) return Env.ZERO;
return bd;
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
/** Set Benchmark Data.
@param PA_BenchmarkData_ID Performance Benchmark Data Point */
public void setPA_BenchmarkData_ID (int PA_BenchmarkData_ID)
{
if (PA_BenchmarkData_ID < 1) throw new IllegalArgumentException ("PA_BenchmarkData_ID is mandatory.");
set_ValueNoCheck ("PA_BenchmarkData_ID", new Integer(PA_BenchmarkData_ID));
}
/** Get Benchmark Data.
@return Performance Benchmark Data Point */
public int getPA_BenchmarkData_ID() 
{
Integer ii = (Integer)get_Value("PA_BenchmarkData_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Benchmark.
@param PA_Benchmark_ID Performance Benchmark */
public void setPA_Benchmark_ID (int PA_Benchmark_ID)
{
if (PA_Benchmark_ID < 1) throw new IllegalArgumentException ("PA_Benchmark_ID is mandatory.");
set_ValueNoCheck ("PA_Benchmark_ID", new Integer(PA_Benchmark_ID));
}
/** Get Benchmark.
@return Performance Benchmark */
public int getPA_Benchmark_ID() 
{
Integer ii = (Integer)get_Value("PA_Benchmark_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
