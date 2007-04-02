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
/** Generated Model for PA_Benchmark
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_PA_Benchmark extends PO
{
/** Standard Constructor
@param ctx context
@param PA_Benchmark_ID id
@param trxName transaction
*/
public X_PA_Benchmark (Properties ctx, int PA_Benchmark_ID, String trxName)
{
super (ctx, PA_Benchmark_ID, trxName);
/** if (PA_Benchmark_ID == 0)
{
setAccumulationType (null);
setName (null);
setPA_Benchmark_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_PA_Benchmark (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=833 */
public static final int Table_ID=MTable.getTable_ID("PA_Benchmark");

/** TableName=PA_Benchmark */
public static final String Table_Name="PA_Benchmark";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"PA_Benchmark");

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
StringBuffer sb = new StringBuffer ("X_PA_Benchmark[").append(get_ID()).append("]");
return sb.toString();
}

/** AccumulationType AD_Reference_ID=370 */
public static final int ACCUMULATIONTYPE_AD_Reference_ID=370;
/** Average = A */
public static final String ACCUMULATIONTYPE_Average = "A";
/** Sum = S */
public static final String ACCUMULATIONTYPE_Sum = "S";
/** Set Accumulation Type.
@param AccumulationType How to accumulate data on time axis */
public void setAccumulationType (String AccumulationType)
{
if (AccumulationType == null) throw new IllegalArgumentException ("AccumulationType is mandatory");
if (AccumulationType.equals("A") || AccumulationType.equals("S"));
 else throw new IllegalArgumentException ("AccumulationType Invalid value - " + AccumulationType + " - Reference_ID=370 - A - S");
if (AccumulationType.length() > 1)
{
log.warning("Length > 1 - truncated");
AccumulationType = AccumulationType.substring(0,0);
}
set_Value ("AccumulationType", AccumulationType);
}
/** Get Accumulation Type.
@return How to accumulate data on time axis */
public String getAccumulationType() 
{
return (String)get_Value("AccumulationType");
}
/** Column name AccumulationType */
public static final String COLUMNNAME_AccumulationType = "AccumulationType";
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
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Column name Help */
public static final String COLUMNNAME_Help = "Help";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Benchmark.
@param PA_Benchmark_ID Performance Benchmark */
public void setPA_Benchmark_ID (int PA_Benchmark_ID)
{
if (PA_Benchmark_ID < 1) throw new IllegalArgumentException ("PA_Benchmark_ID is mandatory.");
set_ValueNoCheck ("PA_Benchmark_ID", Integer.valueOf(PA_Benchmark_ID));
}
/** Get Benchmark.
@return Performance Benchmark */
public int getPA_Benchmark_ID() 
{
Integer ii = (Integer)get_Value("PA_Benchmark_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PA_Benchmark_ID */
public static final String COLUMNNAME_PA_Benchmark_ID = "PA_Benchmark_ID";
}
