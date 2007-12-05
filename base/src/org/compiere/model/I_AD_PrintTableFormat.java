/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_PrintTableFormat
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_AD_PrintTableFormat 
{

    /** TableName=AD_PrintTableFormat */
    public static final String Table_Name = "AD_PrintTableFormat";

    /** AD_Table_ID=523 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_PrintTableFormat_ID */
    public static final String COLUMNNAME_AD_PrintTableFormat_ID = "AD_PrintTableFormat_ID";

	/** Set Print Table Format.
	  * Table Format in Reports
	  */
	public void setAD_PrintTableFormat_ID (int AD_PrintTableFormat_ID);

	/** Get Print Table Format.
	  * Table Format in Reports
	  */
	public int getAD_PrintTableFormat_ID();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name FooterCenter */
    public static final String COLUMNNAME_FooterCenter = "FooterCenter";

	/** Set Footer Center.
	  * Content of the center portion of the footer.
	  */
	public void setFooterCenter (String FooterCenter);

	/** Get Footer Center.
	  * Content of the center portion of the footer.
	  */
	public String getFooterCenter();

    /** Column name FooterLeft */
    public static final String COLUMNNAME_FooterLeft = "FooterLeft";

	/** Set Footer Left.
	  * Content of the left portion of the footer.
	  */
	public void setFooterLeft (String FooterLeft);

	/** Get Footer Left.
	  * Content of the left portion of the footer.
	  */
	public String getFooterLeft();

    /** Column name FooterRight */
    public static final String COLUMNNAME_FooterRight = "FooterRight";

	/** Set Footer Right.
	  * Content of the right portion of the footer.
	  */
	public void setFooterRight (String FooterRight);

	/** Get Footer Right.
	  * Content of the right portion of the footer.
	  */
	public String getFooterRight();

    /** Column name FunctBG_PrintColor_ID */
    public static final String COLUMNNAME_FunctBG_PrintColor_ID = "FunctBG_PrintColor_ID";

	/** Set Function BG Color.
	  * Function Background Color
	  */
	public void setFunctBG_PrintColor_ID (int FunctBG_PrintColor_ID);

	/** Get Function BG Color.
	  * Function Background Color
	  */
	public int getFunctBG_PrintColor_ID();

    /** Column name FunctFG_PrintColor_ID */
    public static final String COLUMNNAME_FunctFG_PrintColor_ID = "FunctFG_PrintColor_ID";

	/** Set Function Color.
	  * Function Foreground Color
	  */
	public void setFunctFG_PrintColor_ID (int FunctFG_PrintColor_ID);

	/** Get Function Color.
	  * Function Foreground Color
	  */
	public int getFunctFG_PrintColor_ID();

    /** Column name Funct_PrintFont_ID */
    public static final String COLUMNNAME_Funct_PrintFont_ID = "Funct_PrintFont_ID";

	/** Set Function Font.
	  * Function row Font
	  */
	public void setFunct_PrintFont_ID (int Funct_PrintFont_ID);

	/** Get Function Font.
	  * Function row Font
	  */
	public int getFunct_PrintFont_ID();

    /** Column name HdrLine_PrintColor_ID */
    public static final String COLUMNNAME_HdrLine_PrintColor_ID = "HdrLine_PrintColor_ID";

	/** Set Header Line Color.
	  * Table header row line color
	  */
	public void setHdrLine_PrintColor_ID (int HdrLine_PrintColor_ID);

	/** Get Header Line Color.
	  * Table header row line color
	  */
	public int getHdrLine_PrintColor_ID();

    /** Column name HdrStroke */
    public static final String COLUMNNAME_HdrStroke = "HdrStroke";

	/** Set Header Stroke.
	  * Width of the Header Line Stroke
	  */
	public void setHdrStroke (BigDecimal HdrStroke);

	/** Get Header Stroke.
	  * Width of the Header Line Stroke
	  */
	public BigDecimal getHdrStroke();

    /** Column name HdrStrokeType */
    public static final String COLUMNNAME_HdrStrokeType = "HdrStrokeType";

	/** Set Header Stroke Type.
	  * Type of the Header Line Stroke
	  */
	public void setHdrStrokeType (String HdrStrokeType);

	/** Get Header Stroke Type.
	  * Type of the Header Line Stroke
	  */
	public String getHdrStrokeType();

    /** Column name HdrTextBG_PrintColor_ID */
    public static final String COLUMNNAME_HdrTextBG_PrintColor_ID = "HdrTextBG_PrintColor_ID";

	/** Set Header Row BG Color.
	  * Background color of header row
	  */
	public void setHdrTextBG_PrintColor_ID (int HdrTextBG_PrintColor_ID);

	/** Get Header Row BG Color.
	  * Background color of header row
	  */
	public int getHdrTextBG_PrintColor_ID();

    /** Column name HdrTextFG_PrintColor_ID */
    public static final String COLUMNNAME_HdrTextFG_PrintColor_ID = "HdrTextFG_PrintColor_ID";

	/** Set Header Row Color.
	  * Foreground color if the table header row
	  */
	public void setHdrTextFG_PrintColor_ID (int HdrTextFG_PrintColor_ID);

	/** Get Header Row Color.
	  * Foreground color if the table header row
	  */
	public int getHdrTextFG_PrintColor_ID();

    /** Column name Hdr_PrintFont_ID */
    public static final String COLUMNNAME_Hdr_PrintFont_ID = "Hdr_PrintFont_ID";

	/** Set Header Row Font.
	  * Header row Font
	  */
	public void setHdr_PrintFont_ID (int Hdr_PrintFont_ID);

	/** Get Header Row Font.
	  * Header row Font
	  */
	public int getHdr_PrintFont_ID();

    /** Column name HeaderCenter */
    public static final String COLUMNNAME_HeaderCenter = "HeaderCenter";

	/** Set Header Center.
	  * Content of the center portion of the header.
	  */
	public void setHeaderCenter (String HeaderCenter);

	/** Get Header Center.
	  * Content of the center portion of the header.
	  */
	public String getHeaderCenter();

    /** Column name HeaderLeft */
    public static final String COLUMNNAME_HeaderLeft = "HeaderLeft";

	/** Set Header Left.
	  * Content of the left portion of the header.
	  */
	public void setHeaderLeft (String HeaderLeft);

	/** Get Header Left.
	  * Content of the left portion of the header.
	  */
	public String getHeaderLeft();

    /** Column name HeaderRight */
    public static final String COLUMNNAME_HeaderRight = "HeaderRight";

	/** Set Header Right.
	  * Content of the right portion of the header.
	  */
	public void setHeaderRight (String HeaderRight);

	/** Get Header Right.
	  * Content of the right portion of the header.
	  */
	public String getHeaderRight();

    /** Column name ImageIsAttached */
    public static final String COLUMNNAME_ImageIsAttached = "ImageIsAttached";

	/** Set Image attached.
	  * The image to be printed is attached to the record
	  */
	public void setImageIsAttached (boolean ImageIsAttached);

	/** Get Image attached.
	  * The image to be printed is attached to the record
	  */
	public boolean isImageIsAttached();

    /** Column name ImageURL */
    public static final String COLUMNNAME_ImageURL = "ImageURL";

	/** Set Image URL.
	  * URL of  image
	  */
	public void setImageURL (String ImageURL);

	/** Get Image URL.
	  * URL of  image
	  */
	public String getImageURL();

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

    /** Column name IsPaintBoundaryLines */
    public static final String COLUMNNAME_IsPaintBoundaryLines = "IsPaintBoundaryLines";

	/** Set Paint Boundary Lines.
	  * Paint table boundary lines
	  */
	public void setIsPaintBoundaryLines (boolean IsPaintBoundaryLines);

	/** Get Paint Boundary Lines.
	  * Paint table boundary lines
	  */
	public boolean isPaintBoundaryLines();

    /** Column name IsPaintHLines */
    public static final String COLUMNNAME_IsPaintHLines = "IsPaintHLines";

	/** Set Paint Horizontal Lines.
	  * Paint horizontal lines
	  */
	public void setIsPaintHLines (boolean IsPaintHLines);

	/** Get Paint Horizontal Lines.
	  * Paint horizontal lines
	  */
	public boolean isPaintHLines();

    /** Column name IsPaintHeaderLines */
    public static final String COLUMNNAME_IsPaintHeaderLines = "IsPaintHeaderLines";

	/** Set Paint Header Lines.
	  * Paint Lines over/under the Header Line 
	  */
	public void setIsPaintHeaderLines (boolean IsPaintHeaderLines);

	/** Get Paint Header Lines.
	  * Paint Lines over/under the Header Line 
	  */
	public boolean isPaintHeaderLines();

    /** Column name IsPaintVLines */
    public static final String COLUMNNAME_IsPaintVLines = "IsPaintVLines";

	/** Set Paint Vertical Lines.
	  * Paint vertical lines
	  */
	public void setIsPaintVLines (boolean IsPaintVLines);

	/** Get Paint Vertical Lines.
	  * Paint vertical lines
	  */
	public boolean isPaintVLines();

    /** Column name IsPrintFunctionSymbols */
    public static final String COLUMNNAME_IsPrintFunctionSymbols = "IsPrintFunctionSymbols";

	/** Set Print Function Symbols.
	  * Print Symbols for Functions (Sum, Average, Count)
	  */
	public void setIsPrintFunctionSymbols (boolean IsPrintFunctionSymbols);

	/** Get Print Function Symbols.
	  * Print Symbols for Functions (Sum, Average, Count)
	  */
	public boolean isPrintFunctionSymbols();

    /** Column name LineStroke */
    public static final String COLUMNNAME_LineStroke = "LineStroke";

	/** Set Line Stroke.
	  * Width of the Line Stroke
	  */
	public void setLineStroke (BigDecimal LineStroke);

	/** Get Line Stroke.
	  * Width of the Line Stroke
	  */
	public BigDecimal getLineStroke();

    /** Column name LineStrokeType */
    public static final String COLUMNNAME_LineStrokeType = "LineStrokeType";

	/** Set Line Stroke Type.
	  * Type of the Line Stroke
	  */
	public void setLineStrokeType (String LineStrokeType);

	/** Get Line Stroke Type.
	  * Type of the Line Stroke
	  */
	public String getLineStrokeType();

    /** Column name Line_PrintColor_ID */
    public static final String COLUMNNAME_Line_PrintColor_ID = "Line_PrintColor_ID";

	/** Set Line Color.
	  * Table line color
	  */
	public void setLine_PrintColor_ID (int Line_PrintColor_ID);

	/** Get Line Color.
	  * Table line color
	  */
	public int getLine_PrintColor_ID();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();
}
