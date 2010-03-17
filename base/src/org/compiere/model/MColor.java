/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
package org.compiere.model;

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.plaf.CompiereColor;
import org.compiere.util.DB;

/**
 *  Color Persistent Object Model
 *  (DisplayType=27)
 *
 *  @author Jorg Janke
 *  @version $Id: MColor.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MColor extends X_AD_Color
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8672637038417465668L;

	/**
	 *  Color Model
	 *  @param ctx context
	 *  @param AD_Color_ID color
	 *	@param trxName transaction
	 */
	public MColor(Properties ctx, int AD_Color_ID, String trxName)
	{
		super (ctx, AD_Color_ID, trxName);
		if (AD_Color_ID == 0)
			setName("-/-");
	}   //  MColor

	/**
	 *  String Representation
	 *  @return string
	 */
	public String toString()
	{
		return "MColor[ID=" + get_ID() + " - " + getName() + "]";
	}   //  toString

	/**
	 *  Load Special data (images, ..).
	 *  To be extended by sub-classes
	 *  @param rs result set
	 *  @param index zero based index
	 *  @return value
	 *  @throws SQLException
	 */
	protected Object loadSpecial (ResultSet rs, int index) throws SQLException
	{
		log.config(p_info.getColumnName(index));
		if (index == get_ColumnIndex("ColorType"))
			return rs.getString(index+1);
		return null;
	}   //  loadSpecial


	/**
	 *  Save Special Data.
	 *      AD_Image_ID (Background)
	 *  @param value value
	 *  @param index index
	 *  @return SQL code for INSERT VALUES clause
	 */
	protected String saveNewSpecial (Object value, int index)
	{
		String colName = p_info.getColumnName(index);
		String colValue = value == null ? "null" : value.getClass().toString();
		log.fine(colName + "=" + colValue);
		if (value == null)
			return "NULL";
		return value.toString();
	}   //  saveNewSpecial

	
	/**************************************************************************
	 *  Get AdempiereColor.
	 *  see org.compiere.grid.ed.VColor#getAdempiereColor
	 *  @return AdempiereColor
	 */
	public CompiereColor getAdempiereColor()
	{
		if (get_ID() == 0)
			return null;

		//  Color Type
		String ColorType = (String)getColorType();
		if (ColorType == null)
		{
			log.log(Level.SEVERE, "MColor.getAdempiereColor - No ColorType");
			return null;
		}
		CompiereColor cc = null;
		//
		if (ColorType.equals(CompiereColor.TYPE_FLAT))
		{
			cc = new CompiereColor(getColor(true), true);
		}
		else if (ColorType.equals(CompiereColor.TYPE_GRADIENT))
		{
			int RepeatDistance = getRepeatDistance();
			String StartPoint = getStartPoint();
			int startPoint = StartPoint == null ? 0 : Integer.parseInt(StartPoint);
			cc = new CompiereColor(getColor(true), getColor(false), startPoint, RepeatDistance);
		}
		else if (ColorType.equals(CompiereColor.TYPE_LINES))
		{
			int LineWidth = getLineWidth();
			int LineDistance = getLineDistance();
			cc = new CompiereColor(getColor(false), getColor(true), LineWidth, LineDistance);
		}
		else if (ColorType.equals(CompiereColor.TYPE_TEXTURE))
		{
			int AD_Image_ID = getAD_Image_ID();
			String url = getURL(AD_Image_ID);
			if (url == null)
				return null;
			BigDecimal ImageAlpha = getImageAlpha();
			float compositeAlpha = ImageAlpha == null ? 0.7f : ImageAlpha.floatValue();
			cc = new CompiereColor(url, getColor(true), compositeAlpha);
		}
		return cc;
	}   //  getAdempiereColor

	/**
	 *  Get Color
	 *  @param primary true if primary false if secondary
	 *  @return Color
	 */
	private Color getColor (boolean primary)
	{
		int red = primary ? getRed() : getRed_1();
		int green = primary ? getGreen() : getGreen_1();
		int blue = primary ? getBlue() : getBlue_1();
		//
		return new Color (red, green, blue);
	}   //  getColor

	/**
	 *  Get URL from Image
	 *  @param AD_Image_ID image
	 *  @return URL as String or null
	 */
	private String getURL (int AD_Image_ID)
	{
		if (AD_Image_ID == 0)
			return null;
		final String whereClause = I_AD_Image.COLUMNNAME_AD_Image_ID+"=?";
		//
		MImage retValue = new Query(getCtx(),I_AD_Image.Table_Name,whereClause,null)
		.setParameters(AD_Image_ID)
		.first();

		return retValue.getImageURL();
	}   //  getURL

}   //  MColor
