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
/** Generated Model for AD_PrintPaper
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_AD_PrintPaper extends PO
{
/** Standard Constructor
@param ctx context
@param AD_PrintPaper_ID id
@param trxName transaction
*/
public X_AD_PrintPaper (Properties ctx, int AD_PrintPaper_ID, String trxName)
{
super (ctx, AD_PrintPaper_ID, trxName);
/** if (AD_PrintPaper_ID == 0)
{
setAD_PrintPaper_ID (0);
setCode (null);	// iso-a4
setIsDefault (false);
setIsLandscape (true);	// Y
setMarginBottom (0);	// 36
setMarginLeft (0);	// 36
setMarginRight (0);	// 36
setMarginTop (0);	// 36
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_PrintPaper (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=492 */
public static final int Table_ID=MTable.getTable_ID("AD_PrintPaper");

/** TableName=AD_PrintPaper */
public static final String Table_Name="AD_PrintPaper";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_PrintPaper");

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
StringBuffer sb = new StringBuffer ("X_AD_PrintPaper[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Print Paper.
@param AD_PrintPaper_ID Printer paper definition */
public void setAD_PrintPaper_ID (int AD_PrintPaper_ID)
{
if (AD_PrintPaper_ID < 1) throw new IllegalArgumentException ("AD_PrintPaper_ID is mandatory.");
set_ValueNoCheck ("AD_PrintPaper_ID", Integer.valueOf(AD_PrintPaper_ID));
}
/** Get Print Paper.
@return Printer paper definition */
public int getAD_PrintPaper_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintPaper_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_PrintPaper_ID */
public static final String COLUMNNAME_AD_PrintPaper_ID = "AD_PrintPaper_ID";
/** Set Validation code.
@param Code Validation Code */
public void setCode (String Code)
{
if (Code == null) throw new IllegalArgumentException ("Code is mandatory.");
if (Code.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Code = Code.substring(0,1999);
}
set_Value ("Code", Code);
}
/** Get Validation code.
@return Validation Code */
public String getCode() 
{
return (String)get_Value("Code");
}
/** Column name Code */
public static final String COLUMNNAME_Code = "Code";
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

/** DimensionUnits AD_Reference_ID=375 */
public static final int DIMENSIONUNITS_AD_Reference_ID=375;
/** Inch = I */
public static final String DIMENSIONUNITS_Inch = "I";
/** MM = M */
public static final String DIMENSIONUNITS_MM = "M";
/** Set Dimension Units.
@param DimensionUnits Units of Dimension */
public void setDimensionUnits (String DimensionUnits)
{
if (DimensionUnits == null || DimensionUnits.equals("I") || DimensionUnits.equals("M"));
 else throw new IllegalArgumentException ("DimensionUnits Invalid value - " + DimensionUnits + " - Reference_ID=375 - I - M");
if (DimensionUnits != null && DimensionUnits.length() > 1)
{
log.warning("Length > 1 - truncated");
DimensionUnits = DimensionUnits.substring(0,0);
}
set_Value ("DimensionUnits", DimensionUnits);
}
/** Get Dimension Units.
@return Units of Dimension */
public String getDimensionUnits() 
{
return (String)get_Value("DimensionUnits");
}
/** Column name DimensionUnits */
public static final String COLUMNNAME_DimensionUnits = "DimensionUnits";
/** Set Default.
@param IsDefault Default value */
public void setIsDefault (boolean IsDefault)
{
set_Value ("IsDefault", Boolean.valueOf(IsDefault));
}
/** Get Default.
@return Default value */
public boolean isDefault() 
{
Object oo = get_Value("IsDefault");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsDefault */
public static final String COLUMNNAME_IsDefault = "IsDefault";
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
/** Set Bottom Margin.
@param MarginBottom Bottom Space in 1/72 inch */
public void setMarginBottom (int MarginBottom)
{
set_Value ("MarginBottom", Integer.valueOf(MarginBottom));
}
/** Get Bottom Margin.
@return Bottom Space in 1/72 inch */
public int getMarginBottom() 
{
Integer ii = (Integer)get_Value("MarginBottom");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name MarginBottom */
public static final String COLUMNNAME_MarginBottom = "MarginBottom";
/** Set Left Margin.
@param MarginLeft Left Space in 1/72 inch */
public void setMarginLeft (int MarginLeft)
{
set_Value ("MarginLeft", Integer.valueOf(MarginLeft));
}
/** Get Left Margin.
@return Left Space in 1/72 inch */
public int getMarginLeft() 
{
Integer ii = (Integer)get_Value("MarginLeft");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name MarginLeft */
public static final String COLUMNNAME_MarginLeft = "MarginLeft";
/** Set Right Margin.
@param MarginRight Right Space in 1/72 inch */
public void setMarginRight (int MarginRight)
{
set_Value ("MarginRight", Integer.valueOf(MarginRight));
}
/** Get Right Margin.
@return Right Space in 1/72 inch */
public int getMarginRight() 
{
Integer ii = (Integer)get_Value("MarginRight");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name MarginRight */
public static final String COLUMNNAME_MarginRight = "MarginRight";
/** Set Top Margin.
@param MarginTop Top Space in 1/72 inch */
public void setMarginTop (int MarginTop)
{
set_Value ("MarginTop", Integer.valueOf(MarginTop));
}
/** Get Top Margin.
@return Top Space in 1/72 inch */
public int getMarginTop() 
{
Integer ii = (Integer)get_Value("MarginTop");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name MarginTop */
public static final String COLUMNNAME_MarginTop = "MarginTop";
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
/** Set Size X.
@param SizeX X (horizontal) dimension size */
public void setSizeX (BigDecimal SizeX)
{
set_Value ("SizeX", SizeX);
}
/** Get Size X.
@return X (horizontal) dimension size */
public BigDecimal getSizeX() 
{
BigDecimal bd = (BigDecimal)get_Value("SizeX");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name SizeX */
public static final String COLUMNNAME_SizeX = "SizeX";
/** Set Size Y.
@param SizeY Y (vertical) dimension size */
public void setSizeY (BigDecimal SizeY)
{
set_Value ("SizeY", SizeY);
}
/** Get Size Y.
@return Y (vertical) dimension size */
public BigDecimal getSizeY() 
{
BigDecimal bd = (BigDecimal)get_Value("SizeY");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name SizeY */
public static final String COLUMNNAME_SizeY = "SizeY";
}
