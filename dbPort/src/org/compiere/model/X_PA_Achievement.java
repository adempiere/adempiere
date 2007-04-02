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
/** Generated Model for PA_Achievement
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_PA_Achievement extends PO
{
/** Standard Constructor
@param ctx context
@param PA_Achievement_ID id
@param trxName transaction
*/
public X_PA_Achievement (Properties ctx, int PA_Achievement_ID, String trxName)
{
super (ctx, PA_Achievement_ID, trxName);
/** if (PA_Achievement_ID == 0)
{
setIsAchieved (false);
setManualActual (Env.ZERO);
setName (null);
setPA_Achievement_ID (0);
setPA_Measure_ID (0);
setSeqNo (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_PA_Achievement (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=438 */
public static final int Table_ID=MTable.getTable_ID("PA_Achievement");

/** TableName=PA_Achievement */
public static final String Table_Name="PA_Achievement";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"PA_Achievement");

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
StringBuffer sb = new StringBuffer ("X_PA_Achievement[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Document Date.
@param DateDoc Date of the Document */
public void setDateDoc (Timestamp DateDoc)
{
set_Value ("DateDoc", DateDoc);
}
/** Get Document Date.
@return Date of the Document */
public Timestamp getDateDoc() 
{
return (Timestamp)get_Value("DateDoc");
}
/** Column name DateDoc */
public static final String COLUMNNAME_DateDoc = "DateDoc";
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
/** Set Achieved.
@param IsAchieved The goal is achieved */
public void setIsAchieved (boolean IsAchieved)
{
set_Value ("IsAchieved", Boolean.valueOf(IsAchieved));
}
/** Get Achieved.
@return The goal is achieved */
public boolean isAchieved() 
{
Object oo = get_Value("IsAchieved");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsAchieved */
public static final String COLUMNNAME_IsAchieved = "IsAchieved";
/** Set Manual Actual.
@param ManualActual Manually entered actual value */
public void setManualActual (BigDecimal ManualActual)
{
if (ManualActual == null) throw new IllegalArgumentException ("ManualActual is mandatory.");
set_Value ("ManualActual", ManualActual);
}
/** Get Manual Actual.
@return Manually entered actual value */
public BigDecimal getManualActual() 
{
BigDecimal bd = (BigDecimal)get_Value("ManualActual");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name ManualActual */
public static final String COLUMNNAME_ManualActual = "ManualActual";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Note.
@param Note Optional additional user defined information */
public void setNote (String Note)
{
if (Note != null && Note.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Note = Note.substring(0,1999);
}
set_Value ("Note", Note);
}
/** Get Note.
@return Optional additional user defined information */
public String getNote() 
{
return (String)get_Value("Note");
}
/** Column name Note */
public static final String COLUMNNAME_Note = "Note";
/** Set Achievement.
@param PA_Achievement_ID Performance Achievement */
public void setPA_Achievement_ID (int PA_Achievement_ID)
{
if (PA_Achievement_ID < 1) throw new IllegalArgumentException ("PA_Achievement_ID is mandatory.");
set_ValueNoCheck ("PA_Achievement_ID", Integer.valueOf(PA_Achievement_ID));
}
/** Get Achievement.
@return Performance Achievement */
public int getPA_Achievement_ID() 
{
Integer ii = (Integer)get_Value("PA_Achievement_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PA_Achievement_ID */
public static final String COLUMNNAME_PA_Achievement_ID = "PA_Achievement_ID";
/** Set Measure.
@param PA_Measure_ID Concrete Performance Measurement */
public void setPA_Measure_ID (int PA_Measure_ID)
{
if (PA_Measure_ID < 1) throw new IllegalArgumentException ("PA_Measure_ID is mandatory.");
set_ValueNoCheck ("PA_Measure_ID", Integer.valueOf(PA_Measure_ID));
}
/** Get Measure.
@return Concrete Performance Measurement */
public int getPA_Measure_ID() 
{
Integer ii = (Integer)get_Value("PA_Measure_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PA_Measure_ID */
public static final String COLUMNNAME_PA_Measure_ID = "PA_Measure_ID";
/** Set Sequence.
@param SeqNo Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_Value ("SeqNo", Integer.valueOf(SeqNo));
}
/** Get Sequence.
@return Method of ordering records;
 lowest number comes first */
public int getSeqNo() 
{
Integer ii = (Integer)get_Value("SeqNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name SeqNo */
public static final String COLUMNNAME_SeqNo = "SeqNo";
}
