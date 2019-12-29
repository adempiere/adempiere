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
package org.compiere.plaf;

import java.awt.Font;
import java.util.logging.Logger;

import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalTheme;

import org.compiere.swing.ColorBlind;
import org.compiere.swing.ExtendedTheme;

/**
 *  Compiere User definable Theme (if used in Metal L&F).
 *  In other Environments, it provides UI extensions (e.g. Error Color)
 *
 *  @author     Jorg Janke
 *  @version    $Id: AdempiereTheme.java,v 1.4 2006/07/30 00:52:23 jjanke Exp $
 */
public class CompiereTheme extends MetalTheme
{
	/**
	 *  Constructor - nop
	 */
	public CompiereTheme()
	{
	}   //  AdempiereTheme

	/**
	 *  Return Theme Name
	 *  @return Theme Name
	 */
	public String getName()
	{
		return s_name;
	}   //  getName

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		return s_name;
	}	//	toString
	
	/**
	 * 	Set Default Colors
	 */
	protected void setDefault()
	{
	}	//	setDefault
	
	/** Static Initializer		*/
	static
	{
		s_theme = new CompiereThemeBlueMetal();
	}
	
	/** Theme Name	*/
	protected static String			s_name = "Compiere Theme";
	/**	Logger					*/
	protected static Logger 	log = Logger.getLogger(CompiereTheme.class.getName());
	/** Active Theme			*/
	protected static CompiereTheme	s_theme = null;

	/** Blue 51,51,102          */
	protected static ColorUIResource primary0 = new ColorUIResource(51, 51, 102);
	/** Blue 102, 102, 153      */
	protected static ColorUIResource primary1;
	/** Blue 153, 153, 204      */
	protected static ColorUIResource primary2;
	/** Blue 204, 204, 255      */
	protected static ColorUIResource primary3;

	/** Black                   */
	protected final ColorUIResource secondary0 = new ColorUIResource(0, 0, 0);
	/** Gray 102, 102, 102      */
	protected static ColorUIResource secondary1;
	/** Gray 153, 153, 153      */
	protected static ColorUIResource secondary2;
	/** BlueGray 214, 224, 234 - background */
	protected static ColorUIResource secondary3;
	/** White                   */
	protected final ColorUIResource secondary4 = new ColorUIResource(255, 255, 255);

	/** Black                   */
	protected static ColorUIResource black;
	/** White                   */
	protected static ColorUIResource white;

	/** Background for mandatory fields */
	protected static ColorUIResource mandatory;
	/** Background for fields in error  */
	protected static ColorUIResource error;
	/** Background for inactive fields  */
	protected static ColorUIResource inactive;
	/** Background for info fields      */
	protected static ColorUIResource info;

	/** Foreground Text OK        */
	protected static ColorUIResource txt_ok;
	/** Foreground Text Error     */
	protected static ColorUIResource txt_error;

	/** Control font            */
	protected static FontUIResource controlFont;
	/** System font             */
	protected static FontUIResource systemFont;
	/** User font               */
	protected static FontUIResource userFont;
	/** Small font              */
	protected static FontUIResource smallFont;
	/** Window Title font       */
	protected static FontUIResource windowFont;
	/** Menu font               */
	protected static FontUIResource menuFont;

	/** Default Font            */
	public static final String      FONT_DEFAULT = "Dialog";
	/** Default Font Size       */
	public static final int         FONT_SIZE = 12;


	/**************************************************************************
	 *  Get Primary 1 (blue in default Metal Theme)
	 *  @return color
	 */
	public ColorUIResource getPrimary1()
	{
		return ColorBlind.getDichromatColorUIResource(primary1);
	}
	public ColorUIResource getPrimary2()
	{
		return ColorBlind.getDichromatColorUIResource(primary2);
	}
	public ColorUIResource getPrimary3()
	{
		return ColorBlind.getDichromatColorUIResource(primary3);
	}

	/**
	 *  Get Seconary 1 (gray in default Metal Theme)
	 *  @return color
	 */
	public ColorUIResource getSecondary0()
	{
		return ColorBlind.getDichromatColorUIResource(secondary0);
	}
	public ColorUIResource getSecondary1()
	{
		return ColorBlind.getDichromatColorUIResource(secondary1);
	}
	public ColorUIResource getSecondary2()
	{
		return ColorBlind.getDichromatColorUIResource(secondary2);
	}
	public ColorUIResource getSecondary3()
	{
		return ColorBlind.getDichromatColorUIResource(secondary3);
	}
	public ColorUIResource getSecondary4()
	{
		return ColorBlind.getDichromatColorUIResource(secondary4);
	}

	public ColorUIResource getBlack()
	{
		return ColorBlind.getDichromatColorUIResource(black);
	}
	public ColorUIResource getWhite()
	{
		return ColorBlind.getDichromatColorUIResource(white);
	}

	/**
	 *  Control Font (plain)
	 *  @return font
	 */
	protected static FontUIResource _getControlTextFont()
	{
		if (controlFont == null)
		{
			try
			{
				controlFont = new FontUIResource(Font.getFont("swing.plaf.metal.controlFont",
					new Font(FONT_DEFAULT, Font.PLAIN, FONT_SIZE)));
			}
			catch (Exception e)
			{
				controlFont = new FontUIResource(FONT_DEFAULT, Font.PLAIN, FONT_SIZE);
			}
		}
		return controlFont;
	}
	public FontUIResource getControlTextFont() {return _getControlTextFont();}

	/**
	 *  System Font
	 *  @return font
	 */
	protected static FontUIResource _getSystemTextFont()
	{
		if (systemFont == null)
		{
			try
			{
				systemFont = new FontUIResource(Font.getFont("swing.plaf.metal.systemFont",
					new Font(FONT_DEFAULT, Font.PLAIN, FONT_SIZE)));
			}
			catch (Exception e)
			{
				systemFont =  new FontUIResource(FONT_DEFAULT, Font.PLAIN, FONT_SIZE);
			}
		}
		return systemFont;
	}
	public FontUIResource getSystemTextFont() {return _getSystemTextFont();}

	/**
	 *  User Font
	 *  @return font
	 */
	protected static FontUIResource _getUserTextFont()
	{
		if (userFont == null)
		{
			try
			{
				userFont = new FontUIResource(Font.getFont("swing.plaf.metal.userFont",
					new Font(FONT_DEFAULT, Font.PLAIN, FONT_SIZE)));
			}
			catch (Exception e)
			{
				userFont =  new FontUIResource(FONT_DEFAULT, Font.PLAIN, FONT_SIZE);
			}
		}
		return userFont;
	}
	public FontUIResource getUserTextFont() {return _getUserTextFont();}

	/**
	 *  Menu
	 *  @return font
	 */
	protected static FontUIResource _getMenuTextFont()
	{
		if (menuFont == null)
		{
			try
			{
				menuFont = new FontUIResource(Font.getFont("swing.plaf.metal.menuFont",
					new Font(FONT_DEFAULT, Font.PLAIN, FONT_SIZE)));
			}
			catch (Exception e)
			{
				menuFont = new FontUIResource(FONT_DEFAULT, Font.PLAIN, FONT_SIZE);
			}
		}
		return menuFont;
	}
	public FontUIResource getMenuTextFont() {return _getMenuTextFont();}

	/**
	 *  Window Title
	 *  @return font
	 */
	protected static FontUIResource _getWindowTitleFont()
	{
		if (windowFont == null)
		{
			try
			{
				windowFont = new FontUIResource(Font.getFont("swing.plaf.metal.windowFont",
					new Font(FONT_DEFAULT, Font.BOLD, FONT_SIZE+2)));
			}
			catch (Exception e)
			{
				windowFont = new FontUIResource(FONT_DEFAULT, Font.BOLD, FONT_SIZE+2);
			}
		}
		return windowFont;
	}
	public FontUIResource getWindowTitleFont() {return _getWindowTitleFont();}

	/**
	 *  Sub Text
	 *  @return font
	 */
	protected static FontUIResource _getSubTextFont()
	{
		if (smallFont == null)
		{
			try
			{
				smallFont = new FontUIResource(Font.getFont("swing.plaf.metal.smallFont",
					new Font(FONT_DEFAULT, Font.PLAIN, FONT_SIZE-2)));
			}
			catch (Exception e)
			{
				smallFont = new FontUIResource(FONT_DEFAULT, Font.PLAIN, FONT_SIZE-2);
			}
		}
		return smallFont;
	}
	public FontUIResource getSubTextFont() {return _getSubTextFont();}

	@Override
	public void addCustomEntriesToTable(UIDefaults table) {
		super.addCustomEntriesToTable(table);
		Object[] defaults =
		{
			ExtendedTheme.ERROR_BG_KEY,
			error,
			ExtendedTheme.ERROR_FG_KEY,
			txt_error,
			ExtendedTheme.INACTIVE_BG_KEY,
			inactive,
			ExtendedTheme.INFO_BG_KEY,
			info,
			ExtendedTheme.MANDATORY_BG_KEY,
			mandatory
		};
		table.putDefaults(defaults);
	}
}   //  AdempiereTheme
