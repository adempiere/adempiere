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
/** Generated Model for C_Period
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_C_Period extends PO
{
/** Standard Constructor
@param ctx context
@param C_Period_ID id
@param trxName transaction
*/
public X_C_Period (Properties ctx, int C_Period_ID, String trxName)
{
super (ctx, C_Period_ID, trxName);
/** if (C_Period_ID == 0)
{
setC_Period_ID (0);
setC_Year_ID (0);
setName (null);
setPeriodNo (0);
setPeriodType (null);	// S
setStartDate (new Timestamp(System.currentTimeMillis()));
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Period (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=C_Period */
public static final String Table_Name="C_Period";

/** AD_Table_ID=145 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

protected BigDecimal accessLevel = BigDecimal.valueOf(2);
/** AccessLevel
@return 2 - Client 
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
StringBuffer sb = new StringBuffer ("X_C_Period[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Period.
@param C_Period_ID Period of the Calendar */
public void setC_Period_ID (int C_Period_ID)
{
if (C_Period_ID < 1) throw new IllegalArgumentException ("C_Period_ID is mandatory.");
set_ValueNoCheck ("C_Period_ID", Integer.valueOf(C_Period_ID));
}
/** Get Period.
@return Period of the Calendar */
public int getC_Period_ID() 
{
Integer ii = (Integer)get_Value("C_Period_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Period_ID */
public static final String COLUMNNAME_C_Period_ID = "C_Period_ID";
/** Set Year.
@param C_Year_ID Calendar Year */
public void setC_Year_ID (int C_Year_ID)
{
if (C_Year_ID < 1) throw new IllegalArgumentException ("C_Year_ID is mandatory.");
set_ValueNoCheck ("C_Year_ID", Integer.valueOf(C_Year_ID));
}
/** Get Year.
@return Calendar Year */
public int getC_Year_ID() 
{
Integer ii = (Integer)get_Value("C_Year_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Year_ID */
public static final String COLUMNNAME_C_Year_ID = "C_Year_ID";
/** Set End Date.
@param EndDate Last effective date (inclusive) */
public void setEndDate (Timestamp EndDate)
{
set_Value ("EndDate", EndDate);
}
/** Get End Date.
@return Last effective date (inclusive) */
public Timestamp getEndDate() 
{
return (Timestamp)get_Value("EndDate");
}
/** Column name EndDate */
public static final String COLUMNNAME_EndDate = "EndDate";
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
/** Set Period No.
@param PeriodNo Unique Period Number */
public void setPeriodNo (int PeriodNo)
{
set_Value ("PeriodNo", Integer.valueOf(PeriodNo));
}
/** Get Period No.
@return Unique Period Number */
public int getPeriodNo() 
{
Integer ii = (Integer)get_Value("PeriodNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PeriodNo */
public static final String COLUMNNAME_PeriodNo = "PeriodNo";

/** PeriodType AD_Reference_ID=115 */
public static final int PERIODTYPE_AD_Reference_ID=115;
/** Adjustment Period = A */
public static final String PERIODTYPE_AdjustmentPeriod = "A";
/** Standard Calendar Period = S */
public static final String PERIODTYPE_StandardCalendarPeriod = "S";
/** Set Period Type.
@param PeriodType Period Type */
public void setPeriodType (String PeriodType)
{
if (PeriodType == null) throw new IllegalArgumentException ("PeriodType is mandatory");
if (PeriodType.equals("A") || PeriodType.equals("S"));
 else throw new IllegalArgumentException ("PeriodType Invalid value - " + PeriodType + " - Reference_ID=115 - A - S");
if (PeriodType.length() > 1)
{
log.warning("Length > 1 - truncated");
PeriodType = PeriodType.substring(0,0);
}
set_ValueNoCheck ("PeriodType", PeriodType);
}
/** Get Period Type.
@return Period Type */
public String getPeriodType() 
{
return (String)get_Value("PeriodType");
}
/** Column name PeriodType */
public static final String COLUMNNAME_PeriodType = "PeriodType";
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", Boolean.valueOf(Processing));
}
/** Get Process Now.
@return Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Processing */
public static final String COLUMNNAME_Processing = "Processing";
/** Set Start Date.
@param StartDate First effective day (inclusive) */
public void setStartDate (Timestamp StartDate)
{
if (StartDate == null) throw new IllegalArgumentException ("StartDate is mandatory.");
set_Value ("StartDate", StartDate);
}
/** Get Start Date.
@return First effective day (inclusive) */
public Timestamp getStartDate() 
{
return (Timestamp)get_Value("StartDate");
}
/** Column name StartDate */
public static final String COLUMNNAME_StartDate = "StartDate";
}
