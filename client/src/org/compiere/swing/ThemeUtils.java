package org.compiere.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalTheme;

import org.compiere.plaf.CompiereColor;

public final class ThemeUtils {

	/**	Logger					*/
	private static Logger 	log = Logger.getLogger(ThemeUtils.class.getName());
	
	/**
	 *  Parses Color into String representation.
	 *  Required as SystemColors and Alpha Colors have different formats
	 *  @param c Color
	 *  @return [r=102,g=102,b=153,a=255]
	 *  @see #parseColor
	 */
	public static String getColorAsString (Color c)
	{
		if (c == null)
			c = SystemColor.control;
		StringBuffer sb = new StringBuffer("[r=").append(c.getRed())
			.append(",g=").append(c.getGreen())
			.append(",b=").append(c.getBlue())
			.append(",a=").append(c.getAlpha())
			.append("]");
	//	System.out.println(sb.toString());
		return sb.toString();
	}   //  getColorAsString
	
	/**
	 *  Parse Color.
	 *  <p>
	 *  Color - [r=102,g=102,b=153,a=0]
	 *
	 *  @param information string information to be parsed
	 *  @param stdColor color used if info cannot parsed
	 *  @return color
	 *  @see #getColorAsString
	 */
	public static ColorUIResource parseColor (String information, ColorUIResource stdColor)
	{
		if (information == null 
			|| information.length() == 0
			|| information.trim().length() == 0)
			return stdColor;
	//	System.out.print("ParseColor=" + info);
		try
		{
			int r = Integer.parseInt(information.substring(information.indexOf("r=")+2, information.indexOf(",g=")));
			int g = Integer.parseInt(information.substring(information.indexOf("g=")+2, information.indexOf(",b=")));
			int b = 0;
			int a = 255;
			if (information.indexOf("a=") == -1)
				b = Integer.parseInt(information.substring(information.indexOf("b=")+2, information.indexOf(']')));
			else
			{
				b = Integer.parseInt(information.substring(information.indexOf("b=")+2, information.indexOf(",a=")));
				a = Integer.parseInt(information.substring(information.indexOf("a=")+2, information.indexOf(']')));
			}
			ColorUIResource retValue = new ColorUIResource(new Color(r, g, b, a));
		//	System.out.println(" - " + retValue.toString());
			return retValue;
		}
		catch (Exception e)
		{
			log.config(information + " - cannot parse: " + e.toString());
		}
		return stdColor;
	}   //  parseColor

	/**
	 *  Parse Font
	 *  <p>
	 *  javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
	 *
	 *  @param information string information to be parsed
	 *  @param stdFont font used if info cannot be parsed
	 *  @return font
	 */
	public static FontUIResource parseFont(String information, FontUIResource stdFont)
	{
		if (information == null
			|| information.length() == 0
			|| information.trim().length() == 0)
			return stdFont;
	//	System.out.print("ParseFont=" + info);
		try
		{
			String name = information.substring(information.indexOf("name=")+5, information.indexOf(",style="));
			String s = information.substring(information.indexOf("style=")+6, information.indexOf(",size="));
			int style = Font.PLAIN;
			if (s.equals("bold"))
				style = Font.BOLD;
			else if (s.equals("italic"))
				style = Font.ITALIC;
			else if (s.equals("bolditalic"))
				style = Font.BOLD | Font.ITALIC;
			int size = Integer.parseInt(information.substring(information.indexOf(",size=")+6, information.lastIndexOf(']')));
			FontUIResource retValue = new FontUIResource(name,style,size);
		//	System.out.println(" - " + retValue.toString());
			return retValue;
		}
		catch (Exception e)
		{
			log.config(information + " - cannot parse: " + e.toString());
		}
		return stdFont;
	}   //  parseFont
	
	//  Static property info
	public static final String P_Primary1      = "#ColorPrimary1";
	public static final String P_Primary2      = "#ColorPrimary2";
	public static final String P_Primary3      = "#ColorPrimary3";
	public static final String P_Secondary1    = "#ColorSecondary1";
	public static final String P_Secondary2    = "#ColorSecondary2";
	public static final String P_Secondary3    = "#ColorSecondary3";
	public static final String P_Black         = "#ColorBlack";
	public static final String P_White         = "#ColorWhite";
	public static final String P_Error         = "#ColorError";
	public static final String P_Info          = "#ColorInfo";
	public static final String P_Mandatory     = "#ColorMandatory";
	public static final String P_Inactive      = "#ColorInactive";
	public static final String P_Txt_OK        = "#ColorTextOK";
	public static final String P_Txt_Error     = "#ColorTextError";
	//
	public static final String P_Control       = "#FontControl";
	public static final String P_System        = "#FontSystem";
	public static final String P_User          = "#FontUser";
	public static final String P_Small         = "#FontSmall";
	public static final String P_Window        = "#FontWindow";
	public static final String P_Menu          = "#FontMenu";

	/**
	 *  Save information in Properties
	 */
	public static void save (Properties properties, MetalTheme theme)
	{
		log.config(CompiereColor.getDefaultBackground().toString());
		//
		properties.setProperty(P_Primary1, getColorAsString(theme.getPrimaryControlDarkShadow()));
		properties.setProperty(P_Primary2, getColorAsString(theme.getPrimaryControlShadow()));
		properties.setProperty(P_Primary3, getColorAsString(theme.getPrimaryControl()));
		properties.setProperty(P_Secondary1, getColorAsString(theme.getControlDarkShadow()));
		properties.setProperty(P_Secondary2, getColorAsString(theme.getControlShadow()));
		properties.setProperty(P_Secondary3, getColorAsString(theme.getControl()));
		properties.setProperty(P_Txt_OK, getColorAsString(theme.getUserTextColor()));
		
		if ( theme instanceof ExtendedTheme ) {
			ExtendedTheme e = (ExtendedTheme)theme;
			properties.setProperty(P_Error, getColorAsString(e.getErrorBackground()));
			properties.setProperty(P_Txt_Error, getColorAsString(e.getErrorForeground()));
			properties.setProperty(P_Mandatory, getColorAsString(e.getMandatoryBackground()));
			properties.setProperty(P_Inactive, getColorAsString(e.getInactiveBackground()));
			properties.setProperty(P_White, getColorAsString(e.getWhite()));
			properties.setProperty(P_Black, getColorAsString(e.getBlack()));
			properties.setProperty(P_Info, getColorAsString(e.getInfoBackground()));
		} else {
			properties.setProperty(P_White, getColorAsString(Color.white));
			properties.setProperty(P_Black, getColorAsString(Color.black));
			properties.setProperty(P_Error, getColorAsString(ExtendedTheme.DEFAULT_ERROR_BG));
			properties.setProperty(P_Txt_Error, getColorAsString(ExtendedTheme.DEFAULT_ERROR_FG));
			properties.setProperty(P_Mandatory, getColorAsString(ExtendedTheme.DEFAULT_MANDATORY_BG));
			properties.setProperty(P_Inactive, getColorAsString(ExtendedTheme.DEFAULT_INACTIVE_BG));
			properties.setProperty(P_Info, getColorAsString(theme.getPrimaryControl()));
		}
		
		//
		properties.setProperty(P_Control, (theme.getControlTextFont()).toString());
		properties.setProperty(P_System, (theme.getSystemTextFont()).toString());
		properties.setProperty(P_User, (theme.getUserTextFont()).toString());
		properties.setProperty(P_Small, (theme.getSubTextFont()).toString());
		properties.setProperty(P_Window, (theme.getWindowTitleFont()).toString());
		properties.setProperty(P_Menu, (theme.getMenuTextFont()).toString());
		
	}   //  save

	
	/**
	 *  Load Properties from properties
	 */
	public static void load (Properties properties, ExtendedTheme theme)
	{
		Map<String, Object> p = new HashMap<String, Object>();
		p.put(P_Primary1, parseColor (properties.getProperty(P_Primary1),null));
		p.put(P_Primary2, parseColor (properties.getProperty(P_Primary2),null));
		p.put(P_Primary3, parseColor (properties.getProperty(P_Primary3), null));
		p.put(P_Secondary1, parseColor (properties.getProperty(P_Secondary1), null));
		p.put(P_Secondary2, parseColor (properties.getProperty(P_Secondary2), null));
		p.put(P_Secondary3, parseColor (properties.getProperty(P_Secondary3), null));
		p.put(P_Error, parseColor(properties.getProperty(P_Error), null));
		p.put(P_Info, parseColor(properties.getProperty(P_Info), null));
		p.put(P_Mandatory, parseColor(properties.getProperty(P_Mandatory), null));
		p.put(P_Inactive, parseColor(properties.getProperty(P_Inactive), null));
		p.put(P_White,parseColor(properties.getProperty(P_White), null));
		p.put(P_Black, parseColor(properties.getProperty(P_Black), null));
		p.put(P_Txt_OK, parseColor(properties.getProperty(P_Txt_OK), null));
		p.put(P_Txt_Error,parseColor(properties.getProperty(P_Txt_Error), null));
		//
		p.put(P_Control, parseFont(properties.getProperty(P_Control), null));
		p.put(P_System, parseFont(properties.getProperty(P_System), null));
		p.put(P_User, parseFont(properties.getProperty(P_User), null));
		p.put(P_Small, parseFont(properties.getProperty(P_Small), null));
		p.put(P_Window, parseFont(properties.getProperty(P_Window), null));
		p.put(P_Menu, parseFont(properties.getProperty(P_Menu), null));
		//
		theme.setUIProperties(p);
	}   //  load
}
