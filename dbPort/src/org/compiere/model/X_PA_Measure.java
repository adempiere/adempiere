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
/** Generated Model for PA_Measure
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_PA_Measure extends PO
{
/** Standard Constructor
@param ctx context
@param PA_Measure_ID id
@param trxName transaction
*/
public X_PA_Measure (Properties ctx, int PA_Measure_ID, String trxName)
{
super (ctx, PA_Measure_ID, trxName);
/** if (PA_Measure_ID == 0)
{
setMeasureDataType (null);	// T
setMeasureType (null);	// M
setName (null);
setPA_Measure_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_PA_Measure (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=441 */
public static final int Table_ID=MTable.getTable_ID("PA_Measure");

/** TableName=PA_Measure */
public static final String Table_Name="PA_Measure";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"PA_Measure");

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
StringBuffer sb = new StringBuffer ("X_PA_Measure[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Project Type.
@param C_ProjectType_ID Type of the project */
public void setC_ProjectType_ID (int C_ProjectType_ID)
{
if (C_ProjectType_ID <= 0) set_Value ("C_ProjectType_ID", null);
 else 
set_Value ("C_ProjectType_ID", Integer.valueOf(C_ProjectType_ID));
}
/** Get Project Type.
@return Type of the project */
public int getC_ProjectType_ID() 
{
Integer ii = (Integer)get_Value("C_ProjectType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ProjectType_ID */
public static final String COLUMNNAME_C_ProjectType_ID = "C_ProjectType_ID";
/** Set Calculation Class.
@param CalculationClass Java Class for calculation, implementing Interface Measure */
public void setCalculationClass (String CalculationClass)
{
if (CalculationClass != null && CalculationClass.length() > 60)
{
log.warning("Length > 60 - truncated");
CalculationClass = CalculationClass.substring(0,59);
}
set_Value ("CalculationClass", CalculationClass);
}
/** Get Calculation Class.
@return Java Class for calculation, implementing Interface Measure */
public String getCalculationClass() 
{
return (String)get_Value("CalculationClass");
}
/** Column name CalculationClass */
public static final String COLUMNNAME_CalculationClass = "CalculationClass";
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
/** Set Manual Actual.
@param ManualActual Manually entered actual value */
public void setManualActual (BigDecimal ManualActual)
{
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
/** Set Note.
@param ManualNote Note for manual entry */
public void setManualNote (String ManualNote)
{
if (ManualNote != null && ManualNote.length() > 2000)
{
log.warning("Length > 2000 - truncated");
ManualNote = ManualNote.substring(0,1999);
}
set_Value ("ManualNote", ManualNote);
}
/** Get Note.
@return Note for manual entry */
public String getManualNote() 
{
return (String)get_Value("ManualNote");
}
/** Column name ManualNote */
public static final String COLUMNNAME_ManualNote = "ManualNote";

/** MeasureDataType AD_Reference_ID=369 */
public static final int MEASUREDATATYPE_AD_Reference_ID=369;
/** Status Qty/Amount = S */
public static final String MEASUREDATATYPE_StatusQtyAmount = "S";
/** Qty/Amount in Time = T */
public static final String MEASUREDATATYPE_QtyAmountInTime = "T";
/** Set Measure Data Type.
@param MeasureDataType Type of data - Status or in Time */
public void setMeasureDataType (String MeasureDataType)
{
if (MeasureDataType == null) throw new IllegalArgumentException ("MeasureDataType is mandatory");
if (MeasureDataType.equals("S") || MeasureDataType.equals("T"));
 else throw new IllegalArgumentException ("MeasureDataType Invalid value - " + MeasureDataType + " - Reference_ID=369 - S - T");
if (MeasureDataType.length() > 1)
{
log.warning("Length > 1 - truncated");
MeasureDataType = MeasureDataType.substring(0,0);
}
set_Value ("MeasureDataType", MeasureDataType);
}
/** Get Measure Data Type.
@return Type of data - Status or in Time */
public String getMeasureDataType() 
{
return (String)get_Value("MeasureDataType");
}
/** Column name MeasureDataType */
public static final String COLUMNNAME_MeasureDataType = "MeasureDataType";

/** MeasureType AD_Reference_ID=231 */
public static final int MEASURETYPE_AD_Reference_ID=231;
/** Achievements = A */
public static final String MEASURETYPE_Achievements = "A";
/** Calculated = C */
public static final String MEASURETYPE_Calculated = "C";
/** Manual = M */
public static final String MEASURETYPE_Manual = "M";
/** Project = P */
public static final String MEASURETYPE_Project = "P";
/** Request = Q */
public static final String MEASURETYPE_Request = "Q";
/** Ratio = R */
public static final String MEASURETYPE_Ratio = "R";
/** User defined = U */
public static final String MEASURETYPE_UserDefined = "U";
/** Set Measure Type.
@param MeasureType Determines how the actual performance is derived */
public void setMeasureType (String MeasureType)
{
if (MeasureType == null) throw new IllegalArgumentException ("MeasureType is mandatory");
if (MeasureType.equals("A") || MeasureType.equals("C") || MeasureType.equals("M") || MeasureType.equals("P") || MeasureType.equals("Q") || MeasureType.equals("R") || MeasureType.equals("U"));
 else throw new IllegalArgumentException ("MeasureType Invalid value - " + MeasureType + " - Reference_ID=231 - A - C - M - P - Q - R - U");
if (MeasureType.length() > 1)
{
log.warning("Length > 1 - truncated");
MeasureType = MeasureType.substring(0,0);
}
set_Value ("MeasureType", MeasureType);
}
/** Get Measure Type.
@return Determines how the actual performance is derived */
public String getMeasureType() 
{
return (String)get_Value("MeasureType");
}
/** Column name MeasureType */
public static final String COLUMNNAME_MeasureType = "MeasureType";
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
/** Set Benchmark.
@param PA_Benchmark_ID Performance Benchmark */
public void setPA_Benchmark_ID (int PA_Benchmark_ID)
{
if (PA_Benchmark_ID <= 0) set_Value ("PA_Benchmark_ID", null);
 else 
set_Value ("PA_Benchmark_ID", Integer.valueOf(PA_Benchmark_ID));
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
/** Set Reporting Hierarchy.
@param PA_Hierarchy_ID Optional Reporting Hierarchy - If not selected the default hierarchy trees are used. */
public void setPA_Hierarchy_ID (int PA_Hierarchy_ID)
{
if (PA_Hierarchy_ID <= 0) set_Value ("PA_Hierarchy_ID", null);
 else 
set_Value ("PA_Hierarchy_ID", Integer.valueOf(PA_Hierarchy_ID));
}
/** Get Reporting Hierarchy.
@return Optional Reporting Hierarchy - If not selected the default hierarchy trees are used. */
public int getPA_Hierarchy_ID() 
{
Integer ii = (Integer)get_Value("PA_Hierarchy_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PA_Hierarchy_ID */
public static final String COLUMNNAME_PA_Hierarchy_ID = "PA_Hierarchy_ID";
/** Set Measure Calculation.
@param PA_MeasureCalc_ID Calculation method for measuring performance */
public void setPA_MeasureCalc_ID (int PA_MeasureCalc_ID)
{
if (PA_MeasureCalc_ID <= 0) set_Value ("PA_MeasureCalc_ID", null);
 else 
set_Value ("PA_MeasureCalc_ID", Integer.valueOf(PA_MeasureCalc_ID));
}
/** Get Measure Calculation.
@return Calculation method for measuring performance */
public int getPA_MeasureCalc_ID() 
{
Integer ii = (Integer)get_Value("PA_MeasureCalc_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PA_MeasureCalc_ID */
public static final String COLUMNNAME_PA_MeasureCalc_ID = "PA_MeasureCalc_ID";
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
/** Set Ratio.
@param PA_Ratio_ID Performace Ratio */
public void setPA_Ratio_ID (int PA_Ratio_ID)
{
if (PA_Ratio_ID <= 0) set_Value ("PA_Ratio_ID", null);
 else 
set_Value ("PA_Ratio_ID", Integer.valueOf(PA_Ratio_ID));
}
/** Get Ratio.
@return Performace Ratio */
public int getPA_Ratio_ID() 
{
Integer ii = (Integer)get_Value("PA_Ratio_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PA_Ratio_ID */
public static final String COLUMNNAME_PA_Ratio_ID = "PA_Ratio_ID";
/** Set Request Type.
@param R_RequestType_ID Type of request (e.g. Inquiry, Complaint, ..) */
public void setR_RequestType_ID (int R_RequestType_ID)
{
if (R_RequestType_ID <= 0) set_Value ("R_RequestType_ID", null);
 else 
set_Value ("R_RequestType_ID", Integer.valueOf(R_RequestType_ID));
}
/** Get Request Type.
@return Type of request (e.g. Inquiry, Complaint, ..) */
public int getR_RequestType_ID() 
{
Integer ii = (Integer)get_Value("R_RequestType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_RequestType_ID */
public static final String COLUMNNAME_R_RequestType_ID = "R_RequestType_ID";
}
