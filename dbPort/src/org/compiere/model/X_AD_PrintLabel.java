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
/** Generated Model for AD_PrintLabel
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_AD_PrintLabel extends PO
{
/** Standard Constructor
@param ctx context
@param AD_PrintLabel_ID id
@param trxName transaction
*/
public X_AD_PrintLabel (Properties ctx, int AD_PrintLabel_ID, String trxName)
{
super (ctx, AD_PrintLabel_ID, trxName);
/** if (AD_PrintLabel_ID == 0)
{
setAD_LabelPrinter_ID (0);
setAD_PrintLabel_ID (0);
setAD_Table_ID (0);
setIsLandscape (false);
setLabelHeight (0);
setLabelWidth (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_PrintLabel (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=570 */
public static final int Table_ID=MTable.getTable_ID("AD_PrintLabel");

/** TableName=AD_PrintLabel */
public static final String Table_Name="AD_PrintLabel";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_PrintLabel");

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
StringBuffer sb = new StringBuffer ("X_AD_PrintLabel[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Label printer.
@param AD_LabelPrinter_ID Label Printer Definition */
public void setAD_LabelPrinter_ID (int AD_LabelPrinter_ID)
{
if (AD_LabelPrinter_ID < 1) throw new IllegalArgumentException ("AD_LabelPrinter_ID is mandatory.");
set_Value ("AD_LabelPrinter_ID", Integer.valueOf(AD_LabelPrinter_ID));
}
/** Get Label printer.
@return Label Printer Definition */
public int getAD_LabelPrinter_ID() 
{
Integer ii = (Integer)get_Value("AD_LabelPrinter_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_LabelPrinter_ID */
public static final String COLUMNNAME_AD_LabelPrinter_ID = "AD_LabelPrinter_ID";
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
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID < 1) throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
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
/** Column name AD_Table_ID */
public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";
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
/** Set Landscape.
@param IsLandscape Landscape orientation */
public void setIsLandscape (boolean IsLandscape)
{
set_Value ("IsLandscape", Boolean.valueOf(IsLandscape));
}
/** Get Landscape.
@return Landscape orientation */
public boolean isLandscape() 
{
Object oo = get_Value("IsLandscape");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsLandscape */
public static final String COLUMNNAME_IsLandscape = "IsLandscape";
/** Set Label Height.
@param LabelHeight Height of the label */
public void setLabelHeight (int LabelHeight)
{
set_Value ("LabelHeight", Integer.valueOf(LabelHeight));
}
/** Get Label Height.
@return Height of the label */
public int getLabelHeight() 
{
Integer ii = (Integer)get_Value("LabelHeight");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name LabelHeight */
public static final String COLUMNNAME_LabelHeight = "LabelHeight";
/** Set Label Width.
@param LabelWidth Width of the Label */
public void setLabelWidth (int LabelWidth)
{
set_Value ("LabelWidth", Integer.valueOf(LabelWidth));
}
/** Get Label Width.
@return Width of the Label */
public int getLabelWidth() 
{
Integer ii = (Integer)get_Value("LabelWidth");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name LabelWidth */
public static final String COLUMNNAME_LabelWidth = "LabelWidth";
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
/** Set Printer Name.
@param PrinterName Name of the Printer */
public void setPrinterName (String PrinterName)
{
if (PrinterName != null && PrinterName.length() > 40)
{
log.warning("Length > 40 - truncated");
PrinterName = PrinterName.substring(0,39);
}
set_Value ("PrinterName", PrinterName);
}
/** Get Printer Name.
@return Name of the Printer */
public String getPrinterName() 
{
return (String)get_Value("PrinterName");
}
/** Column name PrinterName */
public static final String COLUMNNAME_PrinterName = "PrinterName";
}
