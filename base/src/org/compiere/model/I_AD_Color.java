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

import java.util.*;
import java.sql.Timestamp;
import java.math.*;
import org.compiere.util.*;

    /** Generated Interface for AD_Color
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:21.281
     */
    public interface I_AD_Color 
{

    /** TableName=AD_Color */
    public static final String Table_Name = "AD_Color";

    /** AD_Table_ID=457 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = new BigDecimal(4);

    /** Load Meta Data */

    /** Column name AD_Color_ID */
    public static final String COLUMNNAME_AD_Color_ID = "AD_Color_ID";

	/** Set System Color.
	  * Color for backgrounds or indicators
	  */
	public void setAD_Color_ID (int AD_Color_ID);

	/** Get System Color.
	  * Color for backgrounds or indicators
	  */
	public int getAD_Color_ID();

    /** Column name AD_Image_ID */
    public static final String COLUMNNAME_AD_Image_ID = "AD_Image_ID";

	/** Set Image.
	  * Image or Icon
	  */
	public void setAD_Image_ID (int AD_Image_ID);

	/** Get Image.
	  * Image or Icon
	  */
	public int getAD_Image_ID();

	public I_AD_Image getI_AD_Image() throws Exception;

    /** Column name Alpha */
    public static final String COLUMNNAME_Alpha = "Alpha";

	/** Set Alpha.
	  * Color Alpha value 0-255
	  */
	public void setAlpha (int Alpha);

	/** Get Alpha.
	  * Color Alpha value 0-255
	  */
	public int getAlpha();

    /** Column name Alpha_1 */
    public static final String COLUMNNAME_Alpha_1 = "Alpha_1";

	/** Set 2nd Alpha.
	  * Alpha value for second color
	  */
	public void setAlpha_1 (int Alpha_1);

	/** Get 2nd Alpha.
	  * Alpha value for second color
	  */
	public int getAlpha_1();

    /** Column name Blue */
    public static final String COLUMNNAME_Blue = "Blue";

	/** Set Blue.
	  * Color RGB blue value
	  */
	public void setBlue (int Blue);

	/** Get Blue.
	  * Color RGB blue value
	  */
	public int getBlue();

    /** Column name Blue_1 */
    public static final String COLUMNNAME_Blue_1 = "Blue_1";

	/** Set 2nd Blue.
	  * RGB value for second color
	  */
	public void setBlue_1 (int Blue_1);

	/** Get 2nd Blue.
	  * RGB value for second color
	  */
	public int getBlue_1();

    /** Column name ColorType */
    public static final String COLUMNNAME_ColorType = "ColorType";

	/** Set Color Type.
	  * Color presentation for this color
	  */
	public void setColorType (String ColorType);

	/** Get Color Type.
	  * Color presentation for this color
	  */
	public String getColorType();

    /** Column name Green */
    public static final String COLUMNNAME_Green = "Green";

	/** Set Green.
	  * RGB value 
	  */
	public void setGreen (int Green);

	/** Get Green.
	  * RGB value 
	  */
	public int getGreen();

    /** Column name Green_1 */
    public static final String COLUMNNAME_Green_1 = "Green_1";

	/** Set 2nd Green.
	  * RGB value for second color
	  */
	public void setGreen_1 (int Green_1);

	/** Get 2nd Green.
	  * RGB value for second color
	  */
	public int getGreen_1();

    /** Column name ImageAlpha */
    public static final String COLUMNNAME_ImageAlpha = "ImageAlpha";

	/** Set Image Alpha .
	  * Image Texture Composite Alpha
	  */
	public void setImageAlpha (BigDecimal ImageAlpha);

	/** Get Image Alpha .
	  * Image Texture Composite Alpha
	  */
	public BigDecimal getImageAlpha();

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

    /** Column name LineDistance */
    public static final String COLUMNNAME_LineDistance = "LineDistance";

	/** Set Line Distance.
	  * Distance between lines
	  */
	public void setLineDistance (int LineDistance);

	/** Get Line Distance.
	  * Distance between lines
	  */
	public int getLineDistance();

    /** Column name LineWidth */
    public static final String COLUMNNAME_LineWidth = "LineWidth";

	/** Set Line Width.
	  * Width of the lines
	  */
	public void setLineWidth (int LineWidth);

	/** Get Line Width.
	  * Width of the lines
	  */
	public int getLineWidth();

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

    /** Column name Red */
    public static final String COLUMNNAME_Red = "Red";

	/** Set Red.
	  * RGB value
	  */
	public void setRed (int Red);

	/** Get Red.
	  * RGB value
	  */
	public int getRed();

    /** Column name Red_1 */
    public static final String COLUMNNAME_Red_1 = "Red_1";

	/** Set 2nd Red.
	  * RGB value for second color
	  */
	public void setRed_1 (int Red_1);

	/** Get 2nd Red.
	  * RGB value for second color
	  */
	public int getRed_1();

    /** Column name RepeatDistance */
    public static final String COLUMNNAME_RepeatDistance = "RepeatDistance";

	/** Set Repeat Distance.
	  * Distance in points to repeat gradient color - or zero
	  */
	public void setRepeatDistance (int RepeatDistance);

	/** Get Repeat Distance.
	  * Distance in points to repeat gradient color - or zero
	  */
	public int getRepeatDistance();

    /** Column name StartPoint */
    public static final String COLUMNNAME_StartPoint = "StartPoint";

	/** Set Start Point.
	  * Start point of the gradient colors
	  */
	public void setStartPoint (String StartPoint);

	/** Get Start Point.
	  * Start point of the gradient colors
	  */
	public String getStartPoint();
}
