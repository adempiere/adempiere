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
/** Generated Model for AD_PrintLabelLine
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_AD_PrintLabelLine extends PO
{
/** Standard Constructor
@param ctx context
@param AD_PrintLabelLine_ID id
@param trxName transaction
*/
public X_AD_PrintLabelLine (Properties ctx, int AD_PrintLabelLine_ID, String trxName)
{
super (ctx, AD_PrintLabelLine_ID, trxName);
/** if (AD_PrintLabelLine_ID == 0)
{
setAD_LabelPrinterFunction_ID (0);
setAD_PrintLabelLine_ID (0);
setAD_PrintLabel_ID (0);
setLabelFormatType (null);	// F
setName (null);
setSeqNo (0);
setXPosition (0);
setYPosition (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_PrintLabelLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=569 */
public static final int Table_ID=MTable.getTable_ID("AD_PrintLabelLine");

/** TableName=AD_PrintLabelLine */
public static final String Table_Name="AD_PrintLabelLine";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_PrintLabelLine");

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
StringBuffer sb = new StringBuffer ("X_AD_PrintLabelLine[").append(get_ID()).append("]");
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
/** Set Label printer Function.
@param AD_LabelPrinterFunction_ID Function of Label Printer */
public void setAD_LabelPrinterFunction_ID (int AD_LabelPrinterFunction_ID)
{
if (AD_LabelPrinterFunction_ID < 1) throw new IllegalArgumentException ("AD_LabelPrinterFunction_ID is mandatory.");
set_Value ("AD_LabelPrinterFunction_ID", Integer.valueOf(AD_LabelPrinterFunction_ID));
}
/** Get Label printer Function.
@return Function of Label Printer */
public int getAD_LabelPrinterFunction_ID() 
{
Integer ii = (Integer)get_Value("AD_LabelPrinterFunction_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_LabelPrinterFunction_ID */
public static final String COLUMNNAME_AD_LabelPrinterFunction_ID = "AD_LabelPrinterFunction_ID";
/** Set Print Label Line.
@param AD_PrintLabelLine_ID Print Label Line Format */
public void setAD_PrintLabelLine_ID (int AD_PrintLabelLine_ID)
{
if (AD_PrintLabelLine_ID < 1) throw new IllegalArgumentException ("AD_PrintLabelLine_ID is mandatory.");
set_ValueNoCheck ("AD_PrintLabelLine_ID", Integer.valueOf(AD_PrintLabelLine_ID));
}
/** Get Print Label Line.
@return Print Label Line Format */
public int getAD_PrintLabelLine_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintLabelLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_PrintLabelLine_ID */
public static final String COLUMNNAME_AD_PrintLabelLine_ID = "AD_PrintLabelLine_ID";
/** Set Print Label.
@param AD_PrintLabel_ID Label Format to print */
public void setAD_PrintLabel_ID (int AD_PrintLabel_ID)
{
if (AD_PrintLabel_ID < 1) throw new IllegalArgumentException ("AD_PrintLabel_ID is mandatory.");
set_ValueNoCheck ("AD_PrintLabel_ID", Integer.valueOf(AD_PrintLabel_ID));
}
/** Get Print Label.
@return Label Format to print */
public int getAD_PrintLabel_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintLabel_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_PrintLabel_ID */
public static final String COLUMNNAME_AD_PrintLabel_ID = "AD_PrintLabel_ID";

/** LabelFormatType AD_Reference_ID=280 */
public static final int LABELFORMATTYPE_AD_Reference_ID=280;
/** Field = F */
public static final String LABELFORMATTYPE_Field = "F";
/** Text = T */
public static final String LABELFORMATTYPE_Text = "T";
/** Set Label Format Type.
@param LabelFormatType Label Format Type */
public void setLabelFormatType (String LabelFormatType)
{
if (LabelFormatType == null) throw new IllegalArgumentException ("LabelFormatType is mandatory");
if (LabelFormatType.equals("F") || LabelFormatType.equals("T"));
 else throw new IllegalArgumentException ("LabelFormatType Invalid value - " + LabelFormatType + " - Reference_ID=280 - F - T");
if (LabelFormatType.length() > 1)
{
log.warning("Length > 1 - truncated");
LabelFormatType = LabelFormatType.substring(0,0);
}
set_Value ("LabelFormatType", LabelFormatType);
}
/** Get Label Format Type.
@return Label Format Type */
public String getLabelFormatType() 
{
return (String)get_Value("LabelFormatType");
}
/** Column name LabelFormatType */
public static final String COLUMNNAME_LabelFormatType = "LabelFormatType";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Print Text.
@param PrintName The label text to be printed on a document or correspondence. */
public void setPrintName (String PrintName)
{
if (PrintName != null && PrintName.length() > 60)
{
log.warning("Length > 60 - truncated");
PrintName = PrintName.substring(0,59);
}
set_Value ("PrintName", PrintName);
}
/** Get Print Text.
@return The label text to be printed on a document or correspondence. */
public String getPrintName() 
{
return (String)get_Value("PrintName");
}
/** Column name PrintName */
public static final String COLUMNNAME_PrintName = "PrintName";
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getSeqNo()));
}
/** Column name SeqNo */
public static final String COLUMNNAME_SeqNo = "SeqNo";
/** Set X Position.
@param XPosition Absolute X (horizontal) position in 1/72 of an inch */
public void setXPosition (int XPosition)
{
set_Value ("XPosition", Integer.valueOf(XPosition));
}
/** Get X Position.
@return Absolute X (horizontal) position in 1/72 of an inch */
public int getXPosition() 
{
Integer ii = (Integer)get_Value("XPosition");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name XPosition */
public static final String COLUMNNAME_XPosition = "XPosition";
/** Set Y Position.
@param YPosition Absolute Y (vertical) position in 1/72 of an inch */
public void setYPosition (int YPosition)
{
set_Value ("YPosition", Integer.valueOf(YPosition));
}
/** Get Y Position.
@return Absolute Y (vertical) position in 1/72 of an inch */
public int getYPosition() 
{
Integer ii = (Integer)get_Value("YPosition");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name YPosition */
public static final String COLUMNNAME_YPosition = "YPosition";
}
