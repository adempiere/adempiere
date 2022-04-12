/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Yamel Senih ysenih@erpya.com                                          *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.spin.util;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.MColor;
import org.compiere.model.MImage;
import org.compiere.plaf.CompiereColor;


/**
 * Util class that replace the base calling to swing view
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class ColorUtil {

	/**
	 *  Get Color
	 *  @return AdempiereColor or null
	 */
	public static CompiereColor getColor(Properties context, int colorId)
	{
		if (colorId == 0)
			return null;
		MColor mc = new MColor(context, colorId, null);
		return getAdempiereColor(mc);
	}   //  getColor
	
	/**************************************************************************
	 *  Get AdempiereColor.
	 *  see org.compiere.grid.ed.VColor#getAdempiereColor
	 *  @return AdempiereColor
	 */
	public static CompiereColor getAdempiereColor(MColor color)
	{
		if (color.get_ID() == 0)
			return null;

		//  Color Type
		String ColorType = (String)color.getColorType();
		if (ColorType == null)
		{
			return null;
		}
		CompiereColor cc = null;
		//
		if (ColorType.equals(CompiereColor.TYPE_FLAT))
		{
			cc = new CompiereColor(getColor(color, true), true);
		}
		else if (ColorType.equals(CompiereColor.TYPE_GRADIENT))
		{
			int RepeatDistance = color.getRepeatDistance();
			String StartPoint = color.getStartPoint();
			int startPoint = StartPoint == null ? 0 : Integer.parseInt(StartPoint);
			cc = new CompiereColor(getColor(color, true), getColor(color, false), startPoint, RepeatDistance);
		}
		else if (ColorType.equals(CompiereColor.TYPE_LINES))
		{
			int LineWidth = color.getLineWidth();
			int LineDistance = color.getLineDistance();
			cc = new CompiereColor(getColor(color, false), getColor(color, true), LineWidth, LineDistance);
		}
		else if (ColorType.equals(CompiereColor.TYPE_TEXTURE))
		{
			int AD_Image_ID = color.getAD_Image_ID();
			String url = getURL(color, AD_Image_ID);
			if (url == null)
				return null;
			BigDecimal ImageAlpha = color.getImageAlpha();
			float compositeAlpha = ImageAlpha == null ? 0.7f : ImageAlpha.floatValue();
			cc = new CompiereColor(url, getColor(color, true), compositeAlpha);
		}
		return cc;
	}   //  getAdempiereColor

	/**
	 *  Get Color
	 *  @param primary true if primary false if secondary
	 *  @return Color
	 */
	private static Color getColor (MColor color, boolean primary)
	{
		int red = primary ? color.getRed() : color.getRed_1();
		int green = primary ? color.getGreen() : color.getGreen_1();
		int blue = primary ? color.getBlue() : color.getBlue_1();
		//
		return new Color (red, green, blue);
	}   //  getColor

	/**
	 *  Get URL from Image
	 *  @param AD_Image_ID image
	 *  @return URL as String or null
	 */
	private static String getURL (MColor color, int AD_Image_ID)
	{
		if (AD_Image_ID == 0)
			return null;
		return MImage.get(color.getCtx(), AD_Image_ID, null).getImageURL();
	}   //  getURL
}
