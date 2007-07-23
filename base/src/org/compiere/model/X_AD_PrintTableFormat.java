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
/** Generated Model for AD_PrintTableFormat
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_AD_PrintTableFormat extends PO
{
/** Standard Constructor
@param ctx context
@param AD_PrintTableFormat_ID id
@param trxName transaction
*/
public X_AD_PrintTableFormat (Properties ctx, int AD_PrintTableFormat_ID, String trxName)
{
super (ctx, AD_PrintTableFormat_ID, trxName);
/** if (AD_PrintTableFormat_ID == 0)
{
setAD_PrintTableFormat_ID (0);
setIsDefault (false);
setIsPaintBoundaryLines (false);
setIsPaintHLines (false);
setIsPaintHeaderLines (true);	// Y
setIsPaintVLines (false);
setIsPrintFunctionSymbols (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_PrintTableFormat (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=AD_PrintTableFormat */
public static final String Table_Name="AD_PrintTableFormat";

/** AD_Table_ID=523 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

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
StringBuffer sb = new StringBuffer ("X_AD_PrintTableFormat[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Print Table Format.
@param AD_PrintTableFormat_ID Table Format in Reports */
public void setAD_PrintTableFormat_ID (int AD_PrintTableFormat_ID)
{
if (AD_PrintTableFormat_ID < 1) throw new IllegalArgumentException ("AD_PrintTableFormat_ID is mandatory.");
set_ValueNoCheck ("AD_PrintTableFormat_ID", Integer.valueOf(AD_PrintTableFormat_ID));
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
/** Set Footer Center.
@param FooterCenter Content of the center portion of the footer. */
public void setFooterCenter (String FooterCenter)
{
if (FooterCenter != null && FooterCenter.length() > 255)
{
log.warning("Length > 255 - truncated");
FooterCenter = FooterCenter.substring(0,254);
}
set_Value ("FooterCenter", FooterCenter);
}
/** Get Footer Center.
@return Content of the center portion of the footer. */
public String getFooterCenter() 
{
return (String)get_Value("FooterCenter");
}
/** Column name FooterCenter */
public static final String COLUMNNAME_FooterCenter = "FooterCenter";
/** Set Footer Left.
@param FooterLeft Content of the left portion of the footer. */
public void setFooterLeft (String FooterLeft)
{
if (FooterLeft != null && FooterLeft.length() > 255)
{
log.warning("Length > 255 - truncated");
FooterLeft = FooterLeft.substring(0,254);
}
set_Value ("FooterLeft", FooterLeft);
}
/** Get Footer Left.
@return Content of the left portion of the footer. */
public String getFooterLeft() 
{
return (String)get_Value("FooterLeft");
}
/** Column name FooterLeft */
public static final String COLUMNNAME_FooterLeft = "FooterLeft";
/** Set Footer Right.
@param FooterRight Content of the right portion of the footer. */
public void setFooterRight (String FooterRight)
{
if (FooterRight != null && FooterRight.length() > 255)
{
log.warning("Length > 255 - truncated");
FooterRight = FooterRight.substring(0,254);
}
set_Value ("FooterRight", FooterRight);
}
/** Get Footer Right.
@return Content of the right portion of the footer. */
public String getFooterRight() 
{
return (String)get_Value("FooterRight");
}
/** Column name FooterRight */
public static final String COLUMNNAME_FooterRight = "FooterRight";

/** FunctBG_PrintColor_ID AD_Reference_ID=266 */
public static final int FUNCTBG_PRINTCOLOR_ID_AD_Reference_ID=266;
/** Set Function BG Color.
@param FunctBG_PrintColor_ID Function Background Color */
public void setFunctBG_PrintColor_ID (int FunctBG_PrintColor_ID)
{
if (FunctBG_PrintColor_ID <= 0) set_Value ("FunctBG_PrintColor_ID", null);
 else 
set_Value ("FunctBG_PrintColor_ID", Integer.valueOf(FunctBG_PrintColor_ID));
}
/** Get Function BG Color.
@return Function Background Color */
public int getFunctBG_PrintColor_ID() 
{
Integer ii = (Integer)get_Value("FunctBG_PrintColor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name FunctBG_PrintColor_ID */
public static final String COLUMNNAME_FunctBG_PrintColor_ID = "FunctBG_PrintColor_ID";

/** FunctFG_PrintColor_ID AD_Reference_ID=266 */
public static final int FUNCTFG_PRINTCOLOR_ID_AD_Reference_ID=266;
/** Set Function Color.
@param FunctFG_PrintColor_ID Function Foreground Color */
public void setFunctFG_PrintColor_ID (int FunctFG_PrintColor_ID)
{
if (FunctFG_PrintColor_ID <= 0) set_Value ("FunctFG_PrintColor_ID", null);
 else 
set_Value ("FunctFG_PrintColor_ID", Integer.valueOf(FunctFG_PrintColor_ID));
}
/** Get Function Color.
@return Function Foreground Color */
public int getFunctFG_PrintColor_ID() 
{
Integer ii = (Integer)get_Value("FunctFG_PrintColor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name FunctFG_PrintColor_ID */
public static final String COLUMNNAME_FunctFG_PrintColor_ID = "FunctFG_PrintColor_ID";

/** Funct_PrintFont_ID AD_Reference_ID=267 */
public static final int FUNCT_PRINTFONT_ID_AD_Reference_ID=267;
/** Set Function Font.
@param Funct_PrintFont_ID Function row Font */
public void setFunct_PrintFont_ID (int Funct_PrintFont_ID)
{
if (Funct_PrintFont_ID <= 0) set_Value ("Funct_PrintFont_ID", null);
 else 
set_Value ("Funct_PrintFont_ID", Integer.valueOf(Funct_PrintFont_ID));
}
/** Get Function Font.
@return Function row Font */
public int getFunct_PrintFont_ID() 
{
Integer ii = (Integer)get_Value("Funct_PrintFont_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Funct_PrintFont_ID */
public static final String COLUMNNAME_Funct_PrintFont_ID = "Funct_PrintFont_ID";

/** HdrLine_PrintColor_ID AD_Reference_ID=266 */
public static final int HDRLINE_PRINTCOLOR_ID_AD_Reference_ID=266;
/** Set Header Line Color.
@param HdrLine_PrintColor_ID Table header row line color */
public void setHdrLine_PrintColor_ID (int HdrLine_PrintColor_ID)
{
if (HdrLine_PrintColor_ID <= 0) set_Value ("HdrLine_PrintColor_ID", null);
 else 
set_Value ("HdrLine_PrintColor_ID", Integer.valueOf(HdrLine_PrintColor_ID));
}
/** Get Header Line Color.
@return Table header row line color */
public int getHdrLine_PrintColor_ID() 
{
Integer ii = (Integer)get_Value("HdrLine_PrintColor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name HdrLine_PrintColor_ID */
public static final String COLUMNNAME_HdrLine_PrintColor_ID = "HdrLine_PrintColor_ID";
/** Set Header Stroke.
@param HdrStroke Width of the Header Line Stroke */
public void setHdrStroke (BigDecimal HdrStroke)
{
set_Value ("HdrStroke", HdrStroke);
}
/** Get Header Stroke.
@return Width of the Header Line Stroke */
public BigDecimal getHdrStroke() 
{
BigDecimal bd = (BigDecimal)get_Value("HdrStroke");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name HdrStroke */
public static final String COLUMNNAME_HdrStroke = "HdrStroke";

/** HdrStrokeType AD_Reference_ID=312 */
public static final int HDRSTROKETYPE_AD_Reference_ID=312;
/** Dash-Dotted Line = 2 */
public static final String HDRSTROKETYPE_Dash_DottedLine = "2";
/** Dashed Line = D */
public static final String HDRSTROKETYPE_DashedLine = "D";
/** Solid Line = S */
public static final String HDRSTROKETYPE_SolidLine = "S";
/** Dotted Line = d */
public static final String HDRSTROKETYPE_DottedLine = "d";
/** Set Header Stroke Type.
@param HdrStrokeType Type of the Header Line Stroke */
public void setHdrStrokeType (String HdrStrokeType)
{
if (HdrStrokeType == null || HdrStrokeType.equals("2") || HdrStrokeType.equals("D") || HdrStrokeType.equals("S") || HdrStrokeType.equals("d"));
 else throw new IllegalArgumentException ("HdrStrokeType Invalid value - " + HdrStrokeType + " - Reference_ID=312 - 2 - D - S - d");
if (HdrStrokeType != null && HdrStrokeType.length() > 1)
{
log.warning("Length > 1 - truncated");
HdrStrokeType = HdrStrokeType.substring(0,0);
}
set_Value ("HdrStrokeType", HdrStrokeType);
}
/** Get Header Stroke Type.
@return Type of the Header Line Stroke */
public String getHdrStrokeType() 
{
return (String)get_Value("HdrStrokeType");
}
/** Column name HdrStrokeType */
public static final String COLUMNNAME_HdrStrokeType = "HdrStrokeType";

/** HdrTextBG_PrintColor_ID AD_Reference_ID=266 */
public static final int HDRTEXTBG_PRINTCOLOR_ID_AD_Reference_ID=266;
/** Set Header Row BG Color.
@param HdrTextBG_PrintColor_ID Background color of header row */
public void setHdrTextBG_PrintColor_ID (int HdrTextBG_PrintColor_ID)
{
if (HdrTextBG_PrintColor_ID <= 0) set_Value ("HdrTextBG_PrintColor_ID", null);
 else 
set_Value ("HdrTextBG_PrintColor_ID", Integer.valueOf(HdrTextBG_PrintColor_ID));
}
/** Get Header Row BG Color.
@return Background color of header row */
public int getHdrTextBG_PrintColor_ID() 
{
Integer ii = (Integer)get_Value("HdrTextBG_PrintColor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name HdrTextBG_PrintColor_ID */
public static final String COLUMNNAME_HdrTextBG_PrintColor_ID = "HdrTextBG_PrintColor_ID";

/** HdrTextFG_PrintColor_ID AD_Reference_ID=266 */
public static final int HDRTEXTFG_PRINTCOLOR_ID_AD_Reference_ID=266;
/** Set Header Row Color.
@param HdrTextFG_PrintColor_ID Foreground color if the table header row */
public void setHdrTextFG_PrintColor_ID (int HdrTextFG_PrintColor_ID)
{
if (HdrTextFG_PrintColor_ID <= 0) set_Value ("HdrTextFG_PrintColor_ID", null);
 else 
set_Value ("HdrTextFG_PrintColor_ID", Integer.valueOf(HdrTextFG_PrintColor_ID));
}
/** Get Header Row Color.
@return Foreground color if the table header row */
public int getHdrTextFG_PrintColor_ID() 
{
Integer ii = (Integer)get_Value("HdrTextFG_PrintColor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name HdrTextFG_PrintColor_ID */
public static final String COLUMNNAME_HdrTextFG_PrintColor_ID = "HdrTextFG_PrintColor_ID";

/** Hdr_PrintFont_ID AD_Reference_ID=267 */
public static final int HDR_PRINTFONT_ID_AD_Reference_ID=267;
/** Set Header Row Font.
@param Hdr_PrintFont_ID Header row Font */
public void setHdr_PrintFont_ID (int Hdr_PrintFont_ID)
{
if (Hdr_PrintFont_ID <= 0) set_Value ("Hdr_PrintFont_ID", null);
 else 
set_Value ("Hdr_PrintFont_ID", Integer.valueOf(Hdr_PrintFont_ID));
}
/** Get Header Row Font.
@return Header row Font */
public int getHdr_PrintFont_ID() 
{
Integer ii = (Integer)get_Value("Hdr_PrintFont_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Hdr_PrintFont_ID */
public static final String COLUMNNAME_Hdr_PrintFont_ID = "Hdr_PrintFont_ID";
/** Set Header Center.
@param HeaderCenter Content of the center portion of the header. */
public void setHeaderCenter (String HeaderCenter)
{
if (HeaderCenter != null && HeaderCenter.length() > 255)
{
log.warning("Length > 255 - truncated");
HeaderCenter = HeaderCenter.substring(0,254);
}
set_Value ("HeaderCenter", HeaderCenter);
}
/** Get Header Center.
@return Content of the center portion of the header. */
public String getHeaderCenter() 
{
return (String)get_Value("HeaderCenter");
}
/** Column name HeaderCenter */
public static final String COLUMNNAME_HeaderCenter = "HeaderCenter";
/** Set Header Left.
@param HeaderLeft Content of the left portion of the header. */
public void setHeaderLeft (String HeaderLeft)
{
if (HeaderLeft != null && HeaderLeft.length() > 255)
{
log.warning("Length > 255 - truncated");
HeaderLeft = HeaderLeft.substring(0,254);
}
set_Value ("HeaderLeft", HeaderLeft);
}
/** Get Header Left.
@return Content of the left portion of the header. */
public String getHeaderLeft() 
{
return (String)get_Value("HeaderLeft");
}
/** Column name HeaderLeft */
public static final String COLUMNNAME_HeaderLeft = "HeaderLeft";
/** Set Header Right.
@param HeaderRight Content of the right portion of the header. */
public void setHeaderRight (String HeaderRight)
{
if (HeaderRight != null && HeaderRight.length() > 255)
{
log.warning("Length > 255 - truncated");
HeaderRight = HeaderRight.substring(0,254);
}
set_Value ("HeaderRight", HeaderRight);
}
/** Get Header Right.
@return Content of the right portion of the header. */
public String getHeaderRight() 
{
return (String)get_Value("HeaderRight");
}
/** Column name HeaderRight */
public static final String COLUMNNAME_HeaderRight = "HeaderRight";
/** Set Image attached.
@param ImageIsAttached The image to be printed is attached to the record */
public void setImageIsAttached (boolean ImageIsAttached)
{
set_Value ("ImageIsAttached", Boolean.valueOf(ImageIsAttached));
}
/** Get Image attached.
@return The image to be printed is attached to the record */
public boolean isImageIsAttached() 
{
Object oo = get_Value("ImageIsAttached");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name ImageIsAttached */
public static final String COLUMNNAME_ImageIsAttached = "ImageIsAttached";
/** Set Image URL.
@param ImageURL URL of  image */
public void setImageURL (String ImageURL)
{
if (ImageURL != null && ImageURL.length() > 120)
{
log.warning("Length > 120 - truncated");
ImageURL = ImageURL.substring(0,119);
}
set_Value ("ImageURL", ImageURL);
}
/** Get Image URL.
@return URL of  image */
public String getImageURL() 
{
return (String)get_Value("ImageURL");
}
/** Column name ImageURL */
public static final String COLUMNNAME_ImageURL = "ImageURL";
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
/** Set Paint Boundary Lines.
@param IsPaintBoundaryLines Paint table boundary lines */
public void setIsPaintBoundaryLines (boolean IsPaintBoundaryLines)
{
set_Value ("IsPaintBoundaryLines", Boolean.valueOf(IsPaintBoundaryLines));
}
/** Get Paint Boundary Lines.
@return Paint table boundary lines */
public boolean isPaintBoundaryLines() 
{
Object oo = get_Value("IsPaintBoundaryLines");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPaintBoundaryLines */
public static final String COLUMNNAME_IsPaintBoundaryLines = "IsPaintBoundaryLines";
/** Set Paint Horizontal Lines.
@param IsPaintHLines Paint horizontal lines */
public void setIsPaintHLines (boolean IsPaintHLines)
{
set_Value ("IsPaintHLines", Boolean.valueOf(IsPaintHLines));
}
/** Get Paint Horizontal Lines.
@return Paint horizontal lines */
public boolean isPaintHLines() 
{
Object oo = get_Value("IsPaintHLines");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPaintHLines */
public static final String COLUMNNAME_IsPaintHLines = "IsPaintHLines";
/** Set Paint Header Lines.
@param IsPaintHeaderLines Paint Lines over/under the Header Line  */
public void setIsPaintHeaderLines (boolean IsPaintHeaderLines)
{
set_Value ("IsPaintHeaderLines", Boolean.valueOf(IsPaintHeaderLines));
}
/** Get Paint Header Lines.
@return Paint Lines over/under the Header Line  */
public boolean isPaintHeaderLines() 
{
Object oo = get_Value("IsPaintHeaderLines");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPaintHeaderLines */
public static final String COLUMNNAME_IsPaintHeaderLines = "IsPaintHeaderLines";
/** Set Paint Vertical Lines.
@param IsPaintVLines Paint vertical lines */
public void setIsPaintVLines (boolean IsPaintVLines)
{
set_Value ("IsPaintVLines", Boolean.valueOf(IsPaintVLines));
}
/** Get Paint Vertical Lines.
@return Paint vertical lines */
public boolean isPaintVLines() 
{
Object oo = get_Value("IsPaintVLines");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPaintVLines */
public static final String COLUMNNAME_IsPaintVLines = "IsPaintVLines";
/** Set Print Function Symbols.
@param IsPrintFunctionSymbols Print Symbols for Functions (Sum, Average, Count) */
public void setIsPrintFunctionSymbols (boolean IsPrintFunctionSymbols)
{
set_Value ("IsPrintFunctionSymbols", Boolean.valueOf(IsPrintFunctionSymbols));
}
/** Get Print Function Symbols.
@return Print Symbols for Functions (Sum, Average, Count) */
public boolean isPrintFunctionSymbols() 
{
Object oo = get_Value("IsPrintFunctionSymbols");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPrintFunctionSymbols */
public static final String COLUMNNAME_IsPrintFunctionSymbols = "IsPrintFunctionSymbols";
/** Set Line Stroke.
@param LineStroke Width of the Line Stroke */
public void setLineStroke (BigDecimal LineStroke)
{
set_Value ("LineStroke", LineStroke);
}
/** Get Line Stroke.
@return Width of the Line Stroke */
public BigDecimal getLineStroke() 
{
BigDecimal bd = (BigDecimal)get_Value("LineStroke");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name LineStroke */
public static final String COLUMNNAME_LineStroke = "LineStroke";

/** LineStrokeType AD_Reference_ID=312 */
public static final int LINESTROKETYPE_AD_Reference_ID=312;
/** Dash-Dotted Line = 2 */
public static final String LINESTROKETYPE_Dash_DottedLine = "2";
/** Dashed Line = D */
public static final String LINESTROKETYPE_DashedLine = "D";
/** Solid Line = S */
public static final String LINESTROKETYPE_SolidLine = "S";
/** Dotted Line = d */
public static final String LINESTROKETYPE_DottedLine = "d";
/** Set Line Stroke Type.
@param LineStrokeType Type of the Line Stroke */
public void setLineStrokeType (String LineStrokeType)
{
if (LineStrokeType == null || LineStrokeType.equals("2") || LineStrokeType.equals("D") || LineStrokeType.equals("S") || LineStrokeType.equals("d"));
 else throw new IllegalArgumentException ("LineStrokeType Invalid value - " + LineStrokeType + " - Reference_ID=312 - 2 - D - S - d");
if (LineStrokeType != null && LineStrokeType.length() > 1)
{
log.warning("Length > 1 - truncated");
LineStrokeType = LineStrokeType.substring(0,0);
}
set_Value ("LineStrokeType", LineStrokeType);
}
/** Get Line Stroke Type.
@return Type of the Line Stroke */
public String getLineStrokeType() 
{
return (String)get_Value("LineStrokeType");
}
/** Column name LineStrokeType */
public static final String COLUMNNAME_LineStrokeType = "LineStrokeType";

/** Line_PrintColor_ID AD_Reference_ID=266 */
public static final int LINE_PRINTCOLOR_ID_AD_Reference_ID=266;
/** Set Line Color.
@param Line_PrintColor_ID Table line color */
public void setLine_PrintColor_ID (int Line_PrintColor_ID)
{
if (Line_PrintColor_ID <= 0) set_Value ("Line_PrintColor_ID", null);
 else 
set_Value ("Line_PrintColor_ID", Integer.valueOf(Line_PrintColor_ID));
}
/** Get Line Color.
@return Table line color */
public int getLine_PrintColor_ID() 
{
Integer ii = (Integer)get_Value("Line_PrintColor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Line_PrintColor_ID */
public static final String COLUMNNAME_Line_PrintColor_ID = "Line_PrintColor_ID";
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
}
