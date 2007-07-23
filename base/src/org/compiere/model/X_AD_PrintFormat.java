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
/** Generated Model for AD_PrintFormat
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_AD_PrintFormat extends PO
{
/** Standard Constructor
@param ctx context
@param AD_PrintFormat_ID id
@param trxName transaction
*/
public X_AD_PrintFormat (Properties ctx, int AD_PrintFormat_ID, String trxName)
{
super (ctx, AD_PrintFormat_ID, trxName);
/** if (AD_PrintFormat_ID == 0)
{
setAD_PrintColor_ID (0);
setAD_PrintFont_ID (0);
setAD_PrintFormat_ID (0);	// 0
setAD_PrintPaper_ID (0);
setAD_Table_ID (0);
setFooterMargin (0);
setHeaderMargin (0);
setIsDefault (false);
setIsForm (false);
setIsStandardHeaderFooter (true);	// Y
setIsTableBased (true);	// Y
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_PrintFormat (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=AD_PrintFormat */
public static final String Table_Name="AD_PrintFormat";

/** AD_Table_ID=493 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

protected BigDecimal accessLevel = BigDecimal.valueOf(7);
/** AccessLevel
@return 7 - System - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_AD_PrintFormat[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Print Color.
@param AD_PrintColor_ID Color used for printing and display */
public void setAD_PrintColor_ID (int AD_PrintColor_ID)
{
if (AD_PrintColor_ID < 1) throw new IllegalArgumentException ("AD_PrintColor_ID is mandatory.");
set_Value ("AD_PrintColor_ID", Integer.valueOf(AD_PrintColor_ID));
}
/** Get Print Color.
@return Color used for printing and display */
public int getAD_PrintColor_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintColor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_PrintColor_ID */
public static final String COLUMNNAME_AD_PrintColor_ID = "AD_PrintColor_ID";
/** Set Print Font.
@param AD_PrintFont_ID Maintain Print Font */
public void setAD_PrintFont_ID (int AD_PrintFont_ID)
{
if (AD_PrintFont_ID < 1) throw new IllegalArgumentException ("AD_PrintFont_ID is mandatory.");
set_Value ("AD_PrintFont_ID", Integer.valueOf(AD_PrintFont_ID));
}
/** Get Print Font.
@return Maintain Print Font */
public int getAD_PrintFont_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintFont_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_PrintFont_ID */
public static final String COLUMNNAME_AD_PrintFont_ID = "AD_PrintFont_ID";
/** Set Print Format.
@param AD_PrintFormat_ID Data Print Format */
public void setAD_PrintFormat_ID (int AD_PrintFormat_ID)
{
if (AD_PrintFormat_ID < 1) throw new IllegalArgumentException ("AD_PrintFormat_ID is mandatory.");
set_ValueNoCheck ("AD_PrintFormat_ID", Integer.valueOf(AD_PrintFormat_ID));
}
/** Get Print Format.
@return Data Print Format */
public int getAD_PrintFormat_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintFormat_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_PrintFormat_ID */
public static final String COLUMNNAME_AD_PrintFormat_ID = "AD_PrintFormat_ID";
/** Set Print Paper.
@param AD_PrintPaper_ID Printer paper definition */
public void setAD_PrintPaper_ID (int AD_PrintPaper_ID)
{
if (AD_PrintPaper_ID < 1) throw new IllegalArgumentException ("AD_PrintPaper_ID is mandatory.");
set_Value ("AD_PrintPaper_ID", Integer.valueOf(AD_PrintPaper_ID));
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
/** Set Print Table Format.
@param AD_PrintTableFormat_ID Table Format in Reports */
public void setAD_PrintTableFormat_ID (int AD_PrintTableFormat_ID)
{
if (AD_PrintTableFormat_ID <= 0) set_Value ("AD_PrintTableFormat_ID", null);
 else 
set_Value ("AD_PrintTableFormat_ID", Integer.valueOf(AD_PrintTableFormat_ID));
}
/** Get Print Table Format.
@return Table Format in Reports */
public int getAD_PrintTableFormat_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintTableFormat_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_PrintTableFormat_ID */
public static final String COLUMNNAME_AD_PrintTableFormat_ID = "AD_PrintTableFormat_ID";
/** Set Report View.
@param AD_ReportView_ID View used to generate this report */
public void setAD_ReportView_ID (int AD_ReportView_ID)
{
if (AD_ReportView_ID <= 0) set_ValueNoCheck ("AD_ReportView_ID", null);
 else 
set_ValueNoCheck ("AD_ReportView_ID", Integer.valueOf(AD_ReportView_ID));
}
/** Get Report View.
@return View used to generate this report */
public int getAD_ReportView_ID() 
{
Integer ii = (Integer)get_Value("AD_ReportView_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_ReportView_ID */
public static final String COLUMNNAME_AD_ReportView_ID = "AD_ReportView_ID";
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID < 1) throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
set_ValueNoCheck ("AD_Table_ID", Integer.valueOf(AD_Table_ID));
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
/** Set Create Copy.
@param CreateCopy Create Copy */
public void setCreateCopy (String CreateCopy)
{
if (CreateCopy != null && CreateCopy.length() > 1)
{
log.warning("Length > 1 - truncated");
CreateCopy = CreateCopy.substring(0,0);
}
set_Value ("CreateCopy", CreateCopy);
}
/** Get Create Copy.
@return Create Copy */
public String getCreateCopy() 
{
return (String)get_Value("CreateCopy");
}
/** Column name CreateCopy */
public static final String COLUMNNAME_CreateCopy = "CreateCopy";
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
/** Set Footer Margin.
@param FooterMargin Margin of the Footer in 1/72 of an inch */
public void setFooterMargin (int FooterMargin)
{
set_Value ("FooterMargin", Integer.valueOf(FooterMargin));
}
/** Get Footer Margin.
@return Margin of the Footer in 1/72 of an inch */
public int getFooterMargin() 
{
Integer ii = (Integer)get_Value("FooterMargin");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name FooterMargin */
public static final String COLUMNNAME_FooterMargin = "FooterMargin";
/** Set Header Margin.
@param HeaderMargin Margin of the Header in 1/72 of an inch */
public void setHeaderMargin (int HeaderMargin)
{
set_Value ("HeaderMargin", Integer.valueOf(HeaderMargin));
}
/** Get Header Margin.
@return Margin of the Header in 1/72 of an inch */
public int getHeaderMargin() 
{
Integer ii = (Integer)get_Value("HeaderMargin");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name HeaderMargin */
public static final String COLUMNNAME_HeaderMargin = "HeaderMargin";
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
/** Set Form.
@param IsForm If Selected, a Form is printed, if not selected a columnar List report */
public void setIsForm (boolean IsForm)
{
set_Value ("IsForm", Boolean.valueOf(IsForm));
}
/** Get Form.
@return If Selected, a Form is printed, if not selected a columnar List report */
public boolean isForm() 
{
Object oo = get_Value("IsForm");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsForm */
public static final String COLUMNNAME_IsForm = "IsForm";
/** Set Standard Header/Footer.
@param IsStandardHeaderFooter The standard Header and Footer is used */
public void setIsStandardHeaderFooter (boolean IsStandardHeaderFooter)
{
set_Value ("IsStandardHeaderFooter", Boolean.valueOf(IsStandardHeaderFooter));
}
/** Get Standard Header/Footer.
@return The standard Header and Footer is used */
public boolean isStandardHeaderFooter() 
{
Object oo = get_Value("IsStandardHeaderFooter");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsStandardHeaderFooter */
public static final String COLUMNNAME_IsStandardHeaderFooter = "IsStandardHeaderFooter";
/** Set Table Based.
@param IsTableBased Table based List Reporting */
public void setIsTableBased (boolean IsTableBased)
{
set_ValueNoCheck ("IsTableBased", Boolean.valueOf(IsTableBased));
}
/** Get Table Based.
@return Table based List Reporting */
public boolean isTableBased() 
{
Object oo = get_Value("IsTableBased");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsTableBased */
public static final String COLUMNNAME_IsTableBased = "IsTableBased";

/** JasperProcess_ID AD_Reference_ID=400 */
public static final int JASPERPROCESS_ID_AD_Reference_ID=400;
/** Set Jasper Process.
@param JasperProcess_ID The Jasper Process used by the printengine if any process defined */
public void setJasperProcess_ID (int JasperProcess_ID)
{
if (JasperProcess_ID <= 0) set_Value ("JasperProcess_ID", null);
 else 
set_Value ("JasperProcess_ID", Integer.valueOf(JasperProcess_ID));
}
/** Get Jasper Process.
@return The Jasper Process used by the printengine if any process defined */
public int getJasperProcess_ID() 
{
Integer ii = (Integer)get_Value("JasperProcess_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name JasperProcess_ID */
public static final String COLUMNNAME_JasperProcess_ID = "JasperProcess_ID";
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
