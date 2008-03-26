/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_PrintTableFormat
 *  @author Adempiere (generated) 
 *  @version Release 3.4.0s - $Id$ */
public class X_AD_PrintTableFormat extends PO implements I_AD_PrintTableFormat, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_PrintTableFormat (Properties ctx, int AD_PrintTableFormat_ID, String trxName)
    {
      super (ctx, AD_PrintTableFormat_ID, trxName);
      /** if (AD_PrintTableFormat_ID == 0)
        {
			setAD_PrintTableFormat_ID (0);
			setIsDefault (false);
			setIsPaintBoundaryLines (false);
			setIsPaintHLines (false);
			setIsPaintHeaderLines (true);
// Y
			setIsPaintVLines (false);
			setIsPrintFunctionSymbols (false);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_AD_PrintTableFormat (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_AD_PrintTableFormat[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Print Table Format.
		@param AD_PrintTableFormat_ID 
		Table Format in Reports
	  */
	public void setAD_PrintTableFormat_ID (int AD_PrintTableFormat_ID)
	{
		if (AD_PrintTableFormat_ID < 1)
			 throw new IllegalArgumentException ("AD_PrintTableFormat_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_PrintTableFormat_ID, Integer.valueOf(AD_PrintTableFormat_ID));
	}

	/** Get Print Table Format.
		@return Table Format in Reports
	  */
	public int getAD_PrintTableFormat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintTableFormat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{

		if (Description != null && Description.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			Description = Description.substring(0, 255);
		}
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Footer Center.
		@param FooterCenter 
		Content of the center portion of the footer.
	  */
	public void setFooterCenter (String FooterCenter)
	{

		if (FooterCenter != null && FooterCenter.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			FooterCenter = FooterCenter.substring(0, 255);
		}
		set_Value (COLUMNNAME_FooterCenter, FooterCenter);
	}

	/** Get Footer Center.
		@return Content of the center portion of the footer.
	  */
	public String getFooterCenter () 
	{
		return (String)get_Value(COLUMNNAME_FooterCenter);
	}

	/** Set Footer Left.
		@param FooterLeft 
		Content of the left portion of the footer.
	  */
	public void setFooterLeft (String FooterLeft)
	{

		if (FooterLeft != null && FooterLeft.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			FooterLeft = FooterLeft.substring(0, 255);
		}
		set_Value (COLUMNNAME_FooterLeft, FooterLeft);
	}

	/** Get Footer Left.
		@return Content of the left portion of the footer.
	  */
	public String getFooterLeft () 
	{
		return (String)get_Value(COLUMNNAME_FooterLeft);
	}

	/** Set Footer Right.
		@param FooterRight 
		Content of the right portion of the footer.
	  */
	public void setFooterRight (String FooterRight)
	{

		if (FooterRight != null && FooterRight.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			FooterRight = FooterRight.substring(0, 255);
		}
		set_Value (COLUMNNAME_FooterRight, FooterRight);
	}

	/** Get Footer Right.
		@return Content of the right portion of the footer.
	  */
	public String getFooterRight () 
	{
		return (String)get_Value(COLUMNNAME_FooterRight);
	}

	/** FunctBG_PrintColor_ID AD_Reference_ID=266 */
	public static final int FUNCTBG_PRINTCOLOR_ID_AD_Reference_ID=266;
	/** Set Function BG Color.
		@param FunctBG_PrintColor_ID 
		Function Background Color
	  */
	public void setFunctBG_PrintColor_ID (int FunctBG_PrintColor_ID)
	{
		if (FunctBG_PrintColor_ID < 1) 
			set_Value (COLUMNNAME_FunctBG_PrintColor_ID, null);
		else 
			set_Value (COLUMNNAME_FunctBG_PrintColor_ID, Integer.valueOf(FunctBG_PrintColor_ID));
	}

	/** Get Function BG Color.
		@return Function Background Color
	  */
	public int getFunctBG_PrintColor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FunctBG_PrintColor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** FunctFG_PrintColor_ID AD_Reference_ID=266 */
	public static final int FUNCTFG_PRINTCOLOR_ID_AD_Reference_ID=266;
	/** Set Function Color.
		@param FunctFG_PrintColor_ID 
		Function Foreground Color
	  */
	public void setFunctFG_PrintColor_ID (int FunctFG_PrintColor_ID)
	{
		if (FunctFG_PrintColor_ID < 1) 
			set_Value (COLUMNNAME_FunctFG_PrintColor_ID, null);
		else 
			set_Value (COLUMNNAME_FunctFG_PrintColor_ID, Integer.valueOf(FunctFG_PrintColor_ID));
	}

	/** Get Function Color.
		@return Function Foreground Color
	  */
	public int getFunctFG_PrintColor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FunctFG_PrintColor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Funct_PrintFont_ID AD_Reference_ID=267 */
	public static final int FUNCT_PRINTFONT_ID_AD_Reference_ID=267;
	/** Set Function Font.
		@param Funct_PrintFont_ID 
		Function row Font
	  */
	public void setFunct_PrintFont_ID (int Funct_PrintFont_ID)
	{
		if (Funct_PrintFont_ID < 1) 
			set_Value (COLUMNNAME_Funct_PrintFont_ID, null);
		else 
			set_Value (COLUMNNAME_Funct_PrintFont_ID, Integer.valueOf(Funct_PrintFont_ID));
	}

	/** Get Function Font.
		@return Function row Font
	  */
	public int getFunct_PrintFont_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Funct_PrintFont_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** HdrLine_PrintColor_ID AD_Reference_ID=266 */
	public static final int HDRLINE_PRINTCOLOR_ID_AD_Reference_ID=266;
	/** Set Header Line Color.
		@param HdrLine_PrintColor_ID 
		Table header row line color
	  */
	public void setHdrLine_PrintColor_ID (int HdrLine_PrintColor_ID)
	{
		if (HdrLine_PrintColor_ID < 1) 
			set_Value (COLUMNNAME_HdrLine_PrintColor_ID, null);
		else 
			set_Value (COLUMNNAME_HdrLine_PrintColor_ID, Integer.valueOf(HdrLine_PrintColor_ID));
	}

	/** Get Header Line Color.
		@return Table header row line color
	  */
	public int getHdrLine_PrintColor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HdrLine_PrintColor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Header Stroke.
		@param HdrStroke 
		Width of the Header Line Stroke
	  */
	public void setHdrStroke (BigDecimal HdrStroke)
	{
		set_Value (COLUMNNAME_HdrStroke, HdrStroke);
	}

	/** Get Header Stroke.
		@return Width of the Header Line Stroke
	  */
	public BigDecimal getHdrStroke () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HdrStroke);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** HdrStrokeType AD_Reference_ID=312 */
	public static final int HDRSTROKETYPE_AD_Reference_ID=312;
	/** Solid Line = S */
	public static final String HDRSTROKETYPE_SolidLine = "S";
	/** Dashed Line = D */
	public static final String HDRSTROKETYPE_DashedLine = "D";
	/** Dotted Line = d */
	public static final String HDRSTROKETYPE_DottedLine = "d";
	/** Dash-Dotted Line = 2 */
	public static final String HDRSTROKETYPE_Dash_DottedLine = "2";
	/** Set Header Stroke Type.
		@param HdrStrokeType 
		Type of the Header Line Stroke
	  */
	public void setHdrStrokeType (String HdrStrokeType)
	{

		if (HdrStrokeType == null || HdrStrokeType.equals("S") || HdrStrokeType.equals("D") || HdrStrokeType.equals("d") || HdrStrokeType.equals("2")); else throw new IllegalArgumentException ("HdrStrokeType Invalid value - " + HdrStrokeType + " - Reference_ID=312 - S - D - d - 2");
		if (HdrStrokeType != null && HdrStrokeType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			HdrStrokeType = HdrStrokeType.substring(0, 1);
		}
		set_Value (COLUMNNAME_HdrStrokeType, HdrStrokeType);
	}

	/** Get Header Stroke Type.
		@return Type of the Header Line Stroke
	  */
	public String getHdrStrokeType () 
	{
		return (String)get_Value(COLUMNNAME_HdrStrokeType);
	}

	/** HdrTextBG_PrintColor_ID AD_Reference_ID=266 */
	public static final int HDRTEXTBG_PRINTCOLOR_ID_AD_Reference_ID=266;
	/** Set Header Row BG Color.
		@param HdrTextBG_PrintColor_ID 
		Background color of header row
	  */
	public void setHdrTextBG_PrintColor_ID (int HdrTextBG_PrintColor_ID)
	{
		if (HdrTextBG_PrintColor_ID < 1) 
			set_Value (COLUMNNAME_HdrTextBG_PrintColor_ID, null);
		else 
			set_Value (COLUMNNAME_HdrTextBG_PrintColor_ID, Integer.valueOf(HdrTextBG_PrintColor_ID));
	}

	/** Get Header Row BG Color.
		@return Background color of header row
	  */
	public int getHdrTextBG_PrintColor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HdrTextBG_PrintColor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** HdrTextFG_PrintColor_ID AD_Reference_ID=266 */
	public static final int HDRTEXTFG_PRINTCOLOR_ID_AD_Reference_ID=266;
	/** Set Header Row Color.
		@param HdrTextFG_PrintColor_ID 
		Foreground color if the table header row
	  */
	public void setHdrTextFG_PrintColor_ID (int HdrTextFG_PrintColor_ID)
	{
		if (HdrTextFG_PrintColor_ID < 1) 
			set_Value (COLUMNNAME_HdrTextFG_PrintColor_ID, null);
		else 
			set_Value (COLUMNNAME_HdrTextFG_PrintColor_ID, Integer.valueOf(HdrTextFG_PrintColor_ID));
	}

	/** Get Header Row Color.
		@return Foreground color if the table header row
	  */
	public int getHdrTextFG_PrintColor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HdrTextFG_PrintColor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Hdr_PrintFont_ID AD_Reference_ID=267 */
	public static final int HDR_PRINTFONT_ID_AD_Reference_ID=267;
	/** Set Header Row Font.
		@param Hdr_PrintFont_ID 
		Header row Font
	  */
	public void setHdr_PrintFont_ID (int Hdr_PrintFont_ID)
	{
		if (Hdr_PrintFont_ID < 1) 
			set_Value (COLUMNNAME_Hdr_PrintFont_ID, null);
		else 
			set_Value (COLUMNNAME_Hdr_PrintFont_ID, Integer.valueOf(Hdr_PrintFont_ID));
	}

	/** Get Header Row Font.
		@return Header row Font
	  */
	public int getHdr_PrintFont_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Hdr_PrintFont_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Header Center.
		@param HeaderCenter 
		Content of the center portion of the header.
	  */
	public void setHeaderCenter (String HeaderCenter)
	{

		if (HeaderCenter != null && HeaderCenter.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			HeaderCenter = HeaderCenter.substring(0, 255);
		}
		set_Value (COLUMNNAME_HeaderCenter, HeaderCenter);
	}

	/** Get Header Center.
		@return Content of the center portion of the header.
	  */
	public String getHeaderCenter () 
	{
		return (String)get_Value(COLUMNNAME_HeaderCenter);
	}

	/** Set Header Left.
		@param HeaderLeft 
		Content of the left portion of the header.
	  */
	public void setHeaderLeft (String HeaderLeft)
	{

		if (HeaderLeft != null && HeaderLeft.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			HeaderLeft = HeaderLeft.substring(0, 255);
		}
		set_Value (COLUMNNAME_HeaderLeft, HeaderLeft);
	}

	/** Get Header Left.
		@return Content of the left portion of the header.
	  */
	public String getHeaderLeft () 
	{
		return (String)get_Value(COLUMNNAME_HeaderLeft);
	}

	/** Set Header Right.
		@param HeaderRight 
		Content of the right portion of the header.
	  */
	public void setHeaderRight (String HeaderRight)
	{

		if (HeaderRight != null && HeaderRight.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			HeaderRight = HeaderRight.substring(0, 255);
		}
		set_Value (COLUMNNAME_HeaderRight, HeaderRight);
	}

	/** Get Header Right.
		@return Content of the right portion of the header.
	  */
	public String getHeaderRight () 
	{
		return (String)get_Value(COLUMNNAME_HeaderRight);
	}

	/** Set Image attached.
		@param ImageIsAttached 
		The image to be printed is attached to the record
	  */
	public void setImageIsAttached (boolean ImageIsAttached)
	{
		set_Value (COLUMNNAME_ImageIsAttached, Boolean.valueOf(ImageIsAttached));
	}

	/** Get Image attached.
		@return The image to be printed is attached to the record
	  */
	public boolean isImageIsAttached () 
	{
		Object oo = get_Value(COLUMNNAME_ImageIsAttached);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Image URL.
		@param ImageURL 
		URL of  image
	  */
	public void setImageURL (String ImageURL)
	{

		if (ImageURL != null && ImageURL.length() > 120)
		{
			log.warning("Length > 120 - truncated");
			ImageURL = ImageURL.substring(0, 120);
		}
		set_Value (COLUMNNAME_ImageURL, ImageURL);
	}

	/** Get Image URL.
		@return URL of  image
	  */
	public String getImageURL () 
	{
		return (String)get_Value(COLUMNNAME_ImageURL);
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Paint Boundary Lines.
		@param IsPaintBoundaryLines 
		Paint table boundary lines
	  */
	public void setIsPaintBoundaryLines (boolean IsPaintBoundaryLines)
	{
		set_Value (COLUMNNAME_IsPaintBoundaryLines, Boolean.valueOf(IsPaintBoundaryLines));
	}

	/** Get Paint Boundary Lines.
		@return Paint table boundary lines
	  */
	public boolean isPaintBoundaryLines () 
	{
		Object oo = get_Value(COLUMNNAME_IsPaintBoundaryLines);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Paint Horizontal Lines.
		@param IsPaintHLines 
		Paint horizontal lines
	  */
	public void setIsPaintHLines (boolean IsPaintHLines)
	{
		set_Value (COLUMNNAME_IsPaintHLines, Boolean.valueOf(IsPaintHLines));
	}

	/** Get Paint Horizontal Lines.
		@return Paint horizontal lines
	  */
	public boolean isPaintHLines () 
	{
		Object oo = get_Value(COLUMNNAME_IsPaintHLines);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Paint Header Lines.
		@param IsPaintHeaderLines 
		Paint Lines over/under the Header Line 
	  */
	public void setIsPaintHeaderLines (boolean IsPaintHeaderLines)
	{
		set_Value (COLUMNNAME_IsPaintHeaderLines, Boolean.valueOf(IsPaintHeaderLines));
	}

	/** Get Paint Header Lines.
		@return Paint Lines over/under the Header Line 
	  */
	public boolean isPaintHeaderLines () 
	{
		Object oo = get_Value(COLUMNNAME_IsPaintHeaderLines);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Paint Vertical Lines.
		@param IsPaintVLines 
		Paint vertical lines
	  */
	public void setIsPaintVLines (boolean IsPaintVLines)
	{
		set_Value (COLUMNNAME_IsPaintVLines, Boolean.valueOf(IsPaintVLines));
	}

	/** Get Paint Vertical Lines.
		@return Paint vertical lines
	  */
	public boolean isPaintVLines () 
	{
		Object oo = get_Value(COLUMNNAME_IsPaintVLines);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print Function Symbols.
		@param IsPrintFunctionSymbols 
		Print Symbols for Functions (Sum, Average, Count)
	  */
	public void setIsPrintFunctionSymbols (boolean IsPrintFunctionSymbols)
	{
		set_Value (COLUMNNAME_IsPrintFunctionSymbols, Boolean.valueOf(IsPrintFunctionSymbols));
	}

	/** Get Print Function Symbols.
		@return Print Symbols for Functions (Sum, Average, Count)
	  */
	public boolean isPrintFunctionSymbols () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrintFunctionSymbols);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line Stroke.
		@param LineStroke 
		Width of the Line Stroke
	  */
	public void setLineStroke (BigDecimal LineStroke)
	{
		set_Value (COLUMNNAME_LineStroke, LineStroke);
	}

	/** Get Line Stroke.
		@return Width of the Line Stroke
	  */
	public BigDecimal getLineStroke () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LineStroke);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** LineStrokeType AD_Reference_ID=312 */
	public static final int LINESTROKETYPE_AD_Reference_ID=312;
	/** Solid Line = S */
	public static final String LINESTROKETYPE_SolidLine = "S";
	/** Dashed Line = D */
	public static final String LINESTROKETYPE_DashedLine = "D";
	/** Dotted Line = d */
	public static final String LINESTROKETYPE_DottedLine = "d";
	/** Dash-Dotted Line = 2 */
	public static final String LINESTROKETYPE_Dash_DottedLine = "2";
	/** Set Line Stroke Type.
		@param LineStrokeType 
		Type of the Line Stroke
	  */
	public void setLineStrokeType (String LineStrokeType)
	{

		if (LineStrokeType == null || LineStrokeType.equals("S") || LineStrokeType.equals("D") || LineStrokeType.equals("d") || LineStrokeType.equals("2")); else throw new IllegalArgumentException ("LineStrokeType Invalid value - " + LineStrokeType + " - Reference_ID=312 - S - D - d - 2");
		if (LineStrokeType != null && LineStrokeType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			LineStrokeType = LineStrokeType.substring(0, 1);
		}
		set_Value (COLUMNNAME_LineStrokeType, LineStrokeType);
	}

	/** Get Line Stroke Type.
		@return Type of the Line Stroke
	  */
	public String getLineStrokeType () 
	{
		return (String)get_Value(COLUMNNAME_LineStrokeType);
	}

	/** Line_PrintColor_ID AD_Reference_ID=266 */
	public static final int LINE_PRINTCOLOR_ID_AD_Reference_ID=266;
	/** Set Line Color.
		@param Line_PrintColor_ID 
		Table line color
	  */
	public void setLine_PrintColor_ID (int Line_PrintColor_ID)
	{
		if (Line_PrintColor_ID < 1) 
			set_Value (COLUMNNAME_Line_PrintColor_ID, null);
		else 
			set_Value (COLUMNNAME_Line_PrintColor_ID, Integer.valueOf(Line_PrintColor_ID));
	}

	/** Get Line Color.
		@return Table line color
	  */
	public int getLine_PrintColor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line_PrintColor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");

		if (Name.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			Name = Name.substring(0, 60);
		}
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }
}