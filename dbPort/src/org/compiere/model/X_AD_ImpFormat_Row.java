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
/** Generated Model for AD_ImpFormat_Row
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_AD_ImpFormat_Row extends PO
{
/** Standard Constructor
@param ctx context
@param AD_ImpFormat_Row_ID id
@param trxName transaction
*/
public X_AD_ImpFormat_Row (Properties ctx, int AD_ImpFormat_Row_ID, String trxName)
{
super (ctx, AD_ImpFormat_Row_ID, trxName);
/** if (AD_ImpFormat_Row_ID == 0)
{
setAD_Column_ID (0);
setAD_ImpFormat_ID (0);
setAD_ImpFormat_Row_ID (0);
setDataType (null);
setDecimalPoint (null);	// .
setDivideBy100 (false);
setName (null);
setSeqNo (0);	// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM AD_ImpFormat_Row WHERE AD_ImpFormat_ID=@AD_ImpFormat_ID@
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_ImpFormat_Row (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=382 */
public static final int Table_ID=MTable.getTable_ID("AD_ImpFormat_Row");

/** TableName=AD_ImpFormat_Row */
public static final String Table_Name="AD_ImpFormat_Row";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_ImpFormat_Row");

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
StringBuffer sb = new StringBuffer ("X_AD_ImpFormat_Row[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Column.
@param AD_Column_ID Column in the table */
public void setAD_Column_ID (int AD_Column_ID)
{
if (AD_Column_ID < 1) throw new IllegalArgumentException ("AD_Column_ID is mandatory.");
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
/** Set Import Format.
@param AD_ImpFormat_ID Import Format */
public void setAD_ImpFormat_ID (int AD_ImpFormat_ID)
{
if (AD_ImpFormat_ID < 1) throw new IllegalArgumentException ("AD_ImpFormat_ID is mandatory.");
set_ValueNoCheck ("AD_ImpFormat_ID", Integer.valueOf(AD_ImpFormat_ID));
}
/** Get Import Format.
@return Import Format */
public int getAD_ImpFormat_ID() 
{
Integer ii = (Integer)get_Value("AD_ImpFormat_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_ImpFormat_ID */
public static final String COLUMNNAME_AD_ImpFormat_ID = "AD_ImpFormat_ID";
/** Set Format Field.
@param AD_ImpFormat_Row_ID Format Field */
public void setAD_ImpFormat_Row_ID (int AD_ImpFormat_Row_ID)
{
if (AD_ImpFormat_Row_ID < 1) throw new IllegalArgumentException ("AD_ImpFormat_Row_ID is mandatory.");
set_ValueNoCheck ("AD_ImpFormat_Row_ID", Integer.valueOf(AD_ImpFormat_Row_ID));
}
/** Get Format Field.
@return Format Field */
public int getAD_ImpFormat_Row_ID() 
{
Integer ii = (Integer)get_Value("AD_ImpFormat_Row_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_ImpFormat_Row_ID */
public static final String COLUMNNAME_AD_ImpFormat_Row_ID = "AD_ImpFormat_Row_ID";
/** Set Callout.
@param Callout Fully qualified class names and method - separated by semicolons */
public void setCallout (String Callout)
{
if (Callout != null && Callout.length() > 60)
{
log.warning("Length > 60 - truncated");
Callout = Callout.substring(0,59);
}
set_Value ("Callout", Callout);
}
/** Get Callout.
@return Fully qualified class names and method - separated by semicolons */
public String getCallout() 
{
return (String)get_Value("Callout");
}
/** Column name Callout */
public static final String COLUMNNAME_Callout = "Callout";
/** Set Constant Value.
@param ConstantValue Constant value */
public void setConstantValue (String ConstantValue)
{
if (ConstantValue != null && ConstantValue.length() > 60)
{
log.warning("Length > 60 - truncated");
ConstantValue = ConstantValue.substring(0,59);
}
set_Value ("ConstantValue", ConstantValue);
}
/** Get Constant Value.
@return Constant value */
public String getConstantValue() 
{
return (String)get_Value("ConstantValue");
}
/** Column name ConstantValue */
public static final String COLUMNNAME_ConstantValue = "ConstantValue";
/** Set Data Format.
@param DataFormat Format String in Java Notation, e.g. ddMMyy */
public void setDataFormat (String DataFormat)
{
if (DataFormat != null && DataFormat.length() > 20)
{
log.warning("Length > 20 - truncated");
DataFormat = DataFormat.substring(0,19);
}
set_Value ("DataFormat", DataFormat);
}
/** Get Data Format.
@return Format String in Java Notation, e.g. ddMMyy */
public String getDataFormat() 
{
return (String)get_Value("DataFormat");
}
/** Column name DataFormat */
public static final String COLUMNNAME_DataFormat = "DataFormat";

/** DataType AD_Reference_ID=210 */
public static final int DATATYPE_AD_Reference_ID=210;
/** Constant = C */
public static final String DATATYPE_Constant = "C";
/** Date = D */
public static final String DATATYPE_Date = "D";
/** Number = N */
public static final String DATATYPE_Number = "N";
/** String = S */
public static final String DATATYPE_String = "S";
/** Set Data Type.
@param DataType Type of data */
public void setDataType (String DataType)
{
if (DataType == null) throw new IllegalArgumentException ("DataType is mandatory");
if (DataType.equals("C") || DataType.equals("D") || DataType.equals("N") || DataType.equals("S"));
 else throw new IllegalArgumentException ("DataType Invalid value - " + DataType + " - Reference_ID=210 - C - D - N - S");
if (DataType.length() > 1)
{
log.warning("Length > 1 - truncated");
DataType = DataType.substring(0,0);
}
set_Value ("DataType", DataType);
}
/** Get Data Type.
@return Type of data */
public String getDataType() 
{
return (String)get_Value("DataType");
}
/** Column name DataType */
public static final String COLUMNNAME_DataType = "DataType";
/** Set Decimal Point.
@param DecimalPoint Decimal Point in the data file - if any */
public void setDecimalPoint (String DecimalPoint)
{
if (DecimalPoint == null) throw new IllegalArgumentException ("DecimalPoint is mandatory.");
if (DecimalPoint.length() > 1)
{
log.warning("Length > 1 - truncated");
DecimalPoint = DecimalPoint.substring(0,0);
}
set_Value ("DecimalPoint", DecimalPoint);
}
/** Get Decimal Point.
@return Decimal Point in the data file - if any */
public String getDecimalPoint() 
{
return (String)get_Value("DecimalPoint");
}
/** Column name DecimalPoint */
public static final String COLUMNNAME_DecimalPoint = "DecimalPoint";
/** Set Divide by 100.
@param DivideBy100 Divide number by 100 to get correct amount */
public void setDivideBy100 (boolean DivideBy100)
{
set_Value ("DivideBy100", Boolean.valueOf(DivideBy100));
}
/** Get Divide by 100.
@return Divide number by 100 to get correct amount */
public boolean isDivideBy100() 
{
Object oo = get_Value("DivideBy100");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name DivideBy100 */
public static final String COLUMNNAME_DivideBy100 = "DivideBy100";
/** Set End No.
@param EndNo End No */
public void setEndNo (int EndNo)
{
set_Value ("EndNo", Integer.valueOf(EndNo));
}
/** Get End No.
@return End No */
public int getEndNo() 
{
Integer ii = (Integer)get_Value("EndNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name EndNo */
public static final String COLUMNNAME_EndNo = "EndNo";
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
/** Set Script.
@param Script Dynamic Java Language Script to calculate result */
public void setScript (String Script)
{
if (Script != null && Script.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Script = Script.substring(0,1999);
}
set_Value ("Script", Script);
}
/** Get Script.
@return Dynamic Java Language Script to calculate result */
public String getScript() 
{
return (String)get_Value("Script");
}
/** Column name Script */
public static final String COLUMNNAME_Script = "Script";
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
/** Set Start No.
@param StartNo Starting number/position */
public void setStartNo (int StartNo)
{
set_Value ("StartNo", Integer.valueOf(StartNo));
}
/** Get Start No.
@return Starting number/position */
public int getStartNo() 
{
Integer ii = (Integer)get_Value("StartNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name StartNo */
public static final String COLUMNNAME_StartNo = "StartNo";
}
