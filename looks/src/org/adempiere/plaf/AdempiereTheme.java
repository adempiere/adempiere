package org.adempiere.plaf;


import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.logging.Logger;

import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

//import org.compiere.plaf.ColorBlind;
import org.compiere.util.Ini;

import com.jgoodies.looks.plastic.PlasticScrollBarUI;
import com.jgoodies.looks.plastic.theme.*;


/**
 * A theme based on GTK2-Gilouche and tango project iconset.
 * It has been optimized to work with Suse SLED 10 default desktop settings.
 * 
 *
 * @author vpj-cd
 * @version $Revision: 1.0 $
 */
public class AdempiereTheme extends com.jgoodies.looks.plastic.theme.DesertBluer {

    
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
		return getName();
	}	//	toString
	
        
        	
	/** Static Initializer		*/
	static
	{
		s_theme = new org.adempiere.plaf.AdempiereTheme();
	}
	
        	/**
	 *  Set Theme to current Metal Theme and copy it
	 */
	public static void setTheme ()
	{
		log.fine("");		
		org.adempiere.plaf.AdempiereTheme copyFrom = new org.adempiere.plaf.AdempiereTheme();
		boolean flat = Ini.isPropertyBool(Ini.P_UI_FLAT);
		setTheme (copyFrom, flat);
	}   //  setTheme

	/**
	 *  Set Theme to current Metal Theme and copy it
	 *  @param copyFrom theme
	 *  @param flat flat colors
	 */
	public static void setTheme (org.adempiere.plaf.AdempiereTheme copyFrom, boolean flat)
	{
		if (copyFrom == null || copyFrom instanceof org.adempiere.plaf.AdempiereTheme)
			return;
		log.fine(copyFrom.getName() + " - Flat=" + flat);
		//  May not be correct, if Themes overwrites default methods
		primary1 = copyFrom.getPrimaryControlDarkShadow();
		primary2 = copyFrom.getPrimaryControlShadow();
		primary3 = copyFrom.getPrimaryControl();
		secondary1 = copyFrom.getControlDarkShadow();
		secondary2 = copyFrom.getControlShadow();
		secondary3 = copyFrom.getControl();
		org.compiere.plaf.AdempierePanelUI.setDefaultBackground(new org.compiere.plaf.AdempiereColor(secondary3, flat));
		white = copyFrom.getPrimaryControlHighlight();
		black = copyFrom.getPrimaryControlInfo();
		//
		controlFont = copyFrom.getControlTextFont();
		systemFont = copyFrom.getSystemTextFont();
		userFont = copyFrom.getUserTextFont();
		smallFont = copyFrom.getSubTextFont();
		menuFont = copyFrom.getMenuTextFont();
		windowFont = copyFrom.getWindowTitleFont();
	}   //  setTheme
        
        
	//-------------------------------------------------
	/** Theme Name	*/
	protected static String			s_name = "Adempiere Theme";
        public static final String	NAME = s_name;
	/**	Logger					*/
	protected static Logger 	log = Logger.getLogger(org.adempiere.plaf.AdempiereTheme.class.getName());
	/** Active Theme			*/
	protected static AdempiereTheme	s_theme = null;
    
	/** Blue 51,51,102          */
	public static ColorUIResource primary0 = new ColorUIResource(103, 152, 203);
	/** Blue 102, 102, 153      */
	//protected static ColorUIResource primary1;
	public static ColorUIResource primary1 = new ColorUIResource( 101,  138,  187);
	/** Blue 153, 153, 204      */
	public static ColorUIResource primary2 = new ColorUIResource(103, 152,  203); 
	/** Blue 204, 204, 255      */
	public static ColorUIResource primary3= new ColorUIResource(233, 238, 245); // 
           
	/** Black                   */
	public final ColorUIResource secondary0 = new ColorUIResource(0, 0, 0);
	/** Gray 102, 102, 102      */
	//protected static ColorUIResource secondary1;
	public static ColorUIResource secondary1= new ColorUIResource(190, 179, 153);
	/** Gray 153, 153, 153      */
	//protected static ColorUIResource secondary2;
	public static ColorUIResource secondary2= new ColorUIResource(246, 239, 224);
	/** BlueGray 214, 224, 234 - background */
	//protected static ColorUIResource secondary3;
	public static ColorUIResource secondary3=  new ColorUIResource(251, 248, 241);
	/** White                   */
	public final ColorUIResource secondary4 = new ColorUIResource(255, 255, 255);

	/** Black                   */
	public static ColorUIResource black = BLACK;
	/** White                   */
	public static ColorUIResource white =  WHITE;

	/** Background for mandatory fields */
	public static ColorUIResource mandatory =  new ColorUIResource(233, 238, 245); // blueish 
	/** Background for fields in error 180,220,143  */
	public static ColorUIResource error = new ColorUIResource(220, 241, 203); // green ;
	/** Background for inactive fields  */
	public static ColorUIResource inactive = new ColorUIResource(241,239,222); //234, 234, 234); 
	/** Background for info fields      */
	public static ColorUIResource info =  new ColorUIResource(251, 248, 251); // somewhat white

	/** Foreground Text OK        */
	public static ColorUIResource txt_ok =new ColorUIResource(0, 153, 255); // blue ;
	/** Foreground Text Error     */
	public static ColorUIResource txt_error = new ColorUIResource(255, 0, 51); // red ;

	/** Control font            */
	public static FontUIResource controlFont;
	/** System font             */
	public static FontUIResource systemFont;
	/** User font               */
	public static FontUIResource userFont;
	/** Small font              */
	public static FontUIResource smallFont;
	/** Window Title font       */
	public static FontUIResource windowFont;
	/** Menu font               */
	public static FontUIResource menuFont;

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
		return AdempiereColorBlind.getDichromatColorUIResource(primary1);
	}
	public ColorUIResource getPrimary2()
	{
		return AdempiereColorBlind.getDichromatColorUIResource(primary2);
	}
	public ColorUIResource getPrimary3()
	{
		return AdempiereColorBlind.getDichromatColorUIResource(primary3);
	}

	/**
	 *  Get Seconary 1 (gray in default Metal Theme)
	 *  @return color
	 */
	public ColorUIResource getSecondary0()
	{
		return AdempiereColorBlind.getDichromatColorUIResource(secondary0);
	}
	public ColorUIResource getSecondary1()
	{
		return AdempiereColorBlind.getDichromatColorUIResource(secondary1);
	}
	public ColorUIResource getSecondary2()
	{
		return AdempiereColorBlind.getDichromatColorUIResource(secondary2);
	}
	public ColorUIResource getSecondary3()
	{
		return AdempiereColorBlind.getDichromatColorUIResource(secondary3);
	}
	public ColorUIResource getSecondary4()
	{
		return AdempiereColorBlind.getDichromatColorUIResource(secondary4);
	}

	public ColorUIResource getBlack()
	{
		return AdempiereColorBlind.getDichromatColorUIResource(black);
	}
	public ColorUIResource getWhite()
	{
		return AdempiereColorBlind.getDichromatColorUIResource(white);
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

	//  Static property info

	private static final String P_Primary1      = "#ColorPrimary1";
	private static final String P_Primary2      = "#ColorPrimary2";
	private static final String P_Primary3      = "#ColorPrimary3";
	private static final String P_Secondary1    = "#ColorSecondary1";
	private static final String P_Secondary2    = "#ColorSecondary2";
	private static final String P_Secondary3    = "#ColorSecondary3";
	private static final String P_Black         = "#ColorBlack";
	private static final String P_White         = "#ColorWhite";
	private static final String P_Error         = "#ColorError";
	private static final String P_Info          = "#ColorInfo";
	private static final String P_Mandatory     = "#ColorMandatory";
	private static final String P_Inactive      = "#ColorInactive";
	private static final String P_Txt_OK        = "#ColorTextOK";
	private static final String P_Txt_Error     = "#ColorTextError";
	//
	private static final String P_Control       = "#FontControl";
	private static final String P_System        = "#FontSystem";
	private static final String P_User          = "#FontUser";
	private static final String P_Small         = "#FontSmall";
	private static final String P_Window        = "#FontWindow";
	private static final String P_Menu          = "#FontMenu";
	/** Background Color		*/
	protected static final String P_AdempiereColor = "#AdempiereColor";

	/**
	 *  Save information in Properties
	 */
	public static void save ()
	{
		//log.config(AdempiereColor.getDefaultBackground().toString());
		//
		Ini.setProperty(P_Primary1, getColorAsString(primary1));
		Ini.setProperty(P_Primary2, getColorAsString(primary2));
		Ini.setProperty(P_Primary3, getColorAsString(primary3));
		Ini.setProperty(P_Secondary1, getColorAsString(secondary1));
		Ini.setProperty(P_Secondary2, getColorAsString(secondary2));
		Ini.setProperty(P_Secondary3, getColorAsString(secondary3));
		Ini.setProperty(P_Error, getColorAsString(error));
		Ini.setProperty(P_Info, getColorAsString(info));
		Ini.setProperty(P_Mandatory, getColorAsString(mandatory));
		Ini.setProperty(P_Inactive, getColorAsString(inactive));
		Ini.setProperty(P_White, getColorAsString(white));
		Ini.setProperty(P_Black, getColorAsString(black));
		Ini.setProperty(P_Txt_OK, getColorAsString(txt_ok));
		Ini.setProperty(P_Txt_Error, getColorAsString(txt_error));
		//
		Ini.setProperty(P_Control, ((Font)controlFont).toString());
		Ini.setProperty(P_System, ((Font)systemFont).toString());
		Ini.setProperty(P_User, ((Font)userFont).toString());
		Ini.setProperty(P_Small, ((Font)smallFont).toString());
		Ini.setProperty(P_Window, ((Font)windowFont).toString());
		Ini.setProperty(P_Menu, ((Font)menuFont).toString());
		//
		//AdempiereColor cc = org.adempiere.plaf.AdempiereColor.getDefaultBackground();
		//Ini.setProperty(P_CompiereColor, cc.toString());
	}   //  save
	
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
	 *  Load Properties from Ini
	 */
	public static void load ()
	{
		primary1 = parseColor (Ini.getProperty(P_Primary1), primary1);
		primary2 = parseColor (Ini.getProperty(P_Primary2), primary2);
		primary3 = parseColor (Ini.getProperty(P_Primary3), primary3);
		secondary1 = parseColor (Ini.getProperty(P_Secondary1), secondary1);
		secondary2 = parseColor (Ini.getProperty(P_Secondary2), secondary2);
		secondary3 = parseColor (Ini.getProperty(P_Secondary3), secondary3);
		error = parseColor(Ini.getProperty(P_Error), error);
		info = parseColor(Ini.getProperty(P_Info), info);
		mandatory = parseColor(Ini.getProperty(P_Mandatory), mandatory);
		inactive = parseColor(Ini.getProperty(P_Inactive), inactive);
		white = parseColor(Ini.getProperty(P_White), white);
		black = parseColor(Ini.getProperty(P_Black), black);
		txt_ok = parseColor(Ini.getProperty(P_Txt_OK), txt_ok);
		txt_error = parseColor(Ini.getProperty(P_Txt_Error), txt_error);
		//
		controlFont = parseFont(Ini.getProperty(P_Control), controlFont);
		systemFont = parseFont(Ini.getProperty(P_System), systemFont);
		userFont = parseFont(Ini.getProperty(P_User), userFont);
		smallFont = parseFont(Ini.getProperty(P_Small), smallFont);
		windowFont = parseFont(Ini.getProperty(P_Window), windowFont);
		menuFont = parseFont(Ini.getProperty(P_Menu), menuFont);
		//
		//AdempiereColor cc = AdempiereColor.parse(Ini.getProperty(P_AdempiereColor));
		//AdempiereColor.setDefaultBackground(cc);
	}   //  load
        
        	/**
	 * 	Set Default Colors
	 */
	public void setDefault()
	{
	}	//	setDefault

	/**
	 *  Reset Info in Properties
	 */
	public static void reset ()
	{
/**
		Ini.remove (P_Primary1);
		Ini.remove (P_Primary2);
		Ini.remove (P_Primary3);
		Ini.remove (P_Secondary1);
		Ini.remove (P_Secondary2);
		Ini.remove (P_Secondary3);
		Ini.remove (P_Error);
		Ini.remove (P_Info);
		Ini.remove (P_Mandatory);
		Ini.remove (P_Inactive);
		Ini.remove (P_White);
		Ini.remove (P_Black);
		Ini.remove (P_Txt_OK);
		Ini.remove (P_Txt_Error);
		//
		Ini.remove (P_Control);
		Ini.remove (P_System);
		Ini.remove (P_User);
		Ini.remove (P_Small);
		Ini.remove (P_Window);
		Ini.remove (P_Menu);
		//  CompiereColor
		Ini.remove(P_CompiereColor);
**/
		//  Initialize
		Ini.setProperty(Ini.P_UI_LOOK, org.adempiere.plaf.AdempiereLookAndFeel.NAME);
		Ini.setProperty(Ini.P_UI_THEME, s_name);
		//
		if (s_theme != null)
			s_theme.setDefault();

		//  Background
	//	CompiereColor cc = new CompiereColor(SystemColor.control);      //  flat Windows 212-208-200
	//	CompiereColor cc = new CompiereColor(secondary3);               //  flat Metal   204-204-204
		//AdempiereColor cc = new AdempiereColor(secondary3, false);
		//AdempiereColor.setDefaultBackground (cc);
		//
		save();    //  save properties
	}   //  reset

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
	protected static ColorUIResource parseColor (String information, ColorUIResource stdColor)
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
				b = Integer.parseInt(information.substring(information.indexOf("b=")+2, information.indexOf("]")));
			else
			{
				b = Integer.parseInt(information.substring(information.indexOf("b=")+2, information.indexOf(",a=")));
				a = Integer.parseInt(information.substring(information.indexOf("a=")+2, information.indexOf("]")));
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
	private static FontUIResource parseFont(String information, FontUIResource stdFont)
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
			int size = Integer.parseInt(information.substring(information.indexOf(",size=")+6, information.lastIndexOf("]")));
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

	//-----------------------------------------------------


    public ColorUIResource getFocusColor() {
        return getPrimary2();
    }
    
    public ColorUIResource getPrimaryControlShadow() {
        return getPrimary3();
    }

    public ColorUIResource getMenuSelectedBackground() {
        return getPrimary1();
    }
    public ColorUIResource getMenuSelectedForeground() {
        return WHITE;
    }
    
    public ColorUIResource getMenuItemBackground() {
        return WHITE;
    }
	
    public ColorUIResource getToggleButtonCheckColor() {
        return new ColorUIResource( 220, 241, 203);
    }

    public void addCustomEntriesToTable(UIDefaults table) {
        super.addCustomEntriesToTable(table);
        Object[] uiDefaults =
            {
                "ScrollBar.thumbHighlight",
                getPrimaryControlHighlight(),
                PlasticScrollBarUI.MAX_BUMPS_WIDTH_KEY,
                new Integer(22),
                PlasticScrollBarUI.MAX_BUMPS_WIDTH_KEY, new Integer(30),
                //"TabbedPane.selected", getWhite(),
                "TabbedPane.selectHighlight",  new ColorUIResource(231, 218, 188),
                };
                
        table.putDefaults(uiDefaults);
    }

}
