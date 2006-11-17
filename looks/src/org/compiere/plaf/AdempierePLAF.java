/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;
//import org.compiere.process.OrgOwnership;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *  Variable Pluggable Look And Feel.
 *  Provides an easy access to the required currently active PLAF information
 *
 *  @author     Jorg Janke
 *  @version    $Id: AdempierePLAF.java,v 1.3 2006/07/30 00:52:23 jjanke Exp $
 */
public final class AdempierePLAF
{
	/** Version tag */
	public static final String  VERSION = "R1.4.0";
	/** Key of Client Property to paint in AdempiereColor    */
	public static final String  BACKGROUND = "AdempiereBackground";
	/** Key of Client Property for Rectangle Items - if exists, the standard background is used */
	public static final String  BACKGROUND_FILL = "AdempiereBackgroundFill";
	/** Key of Client Property for CPanel               */
	public static final String  TABLEVEL = "AdempiereTabLevel";

	/**	Logger			*/
	private static Logger log = Logger.getLogger(AdempierePLAF.class.getName());
	
	/****** Background *******************************************************/

	/**
	 *  Return Normal field background color "text".
	 *  Windows = white
	 *  @return Color
	 */
	public static Color getFieldBackground_Normal()
	{
		//  window => white
		return ColorBlind.getDichromatColor(UIManager.getColor("text"));
	}   //  getFieldBackground_Normal

	/**
	 *  Return Error field background (AdempiereTheme)
	 *  @return Color
	 */
	public static Color getFieldBackground_Error()
	{
		return ColorBlind.getDichromatColor(AdempiereThemeInnova.error);
	}   //  getFieldBackground_Error

	/**
	 *  Return Mandatory field background color (AdempiereTheme)
	 *  @return Color
	 */
	public static Color getFieldBackground_Mandatory()
	{
		return ColorBlind.getDichromatColor(AdempiereThemeInnova.mandatory);
	}   //  getFieldBackground_Mandatory

	/**
	 *  Return Inactive field background color (AdempiereTheme)
	 *  @return Color
	 */
	public static Color getFieldBackground_Inactive()
	{
		return ColorBlind.getDichromatColor(AdempiereThemeInnova.inactive);
	}   //  getFieldBackground_Inactive

	/**
	 *  Return form background color "control".
	 *  Windows = lightGray
	 *  @return Color
	 */
	public static Color getFormBackground()
	{
		return ColorBlind.getDichromatColor(UIManager.getColor("control"));
	}   //  getFormBackground

	/**
	 *	Info Background Color "info"
	 *  Windows = info (light yellow)
	 *  @return Color
	 */
	public static Color getInfoBackground()
	{
		return ColorBlind.getDichromatColor(AdempiereThemeInnova.info);
	}   //  getInfoBackground


	/****** Text *************************************************************/

	/**
	 *	Normal field text foreground color "textText"
	 *  Windows = black
	 *  @return Color
	 */
	public static Color getTextColor_Normal()
	{
		return ColorBlind.getDichromatColor(UIManager.getColor("textText"));
	}   //  getText_Normal

	/**
	 *	OK Text Foreground Color (Theme)
	 *  @return Color
	 */
	public static Color getTextColor_OK()
	{
		return ColorBlind.getDichromatColor(AdempiereThemeInnova.txt_ok);
	}   //  getText_OK

	/**
	 *	Issue Text Foreground Color (Theme)
	 *  @return Color
	 */
	public static Color getTextColor_Issue()
	{
		return ColorBlind.getDichromatColor(AdempiereThemeInnova.txt_error);
	}   //  getText_Issue

	/**
	 *  Label Text foreground Color "controlText"
	 *  Windows = black
	 *  @return Color
	 */
	public static Color getTextColor_Label()
	{
		return ColorBlind.getDichromatColor(UIManager.getColor("controlText"));
	}   //  getTextColor_Label

	/**
	 * 	Get Primary1
	 *	@return primary 1
	 */
	public static Color getPrimary1()
	{
		return ColorBlind.getDichromatColor(AdempiereThemeInnova.primary1);
	}
	/**
	 * 	Get Primary2
	 *	@return primary 2
	 */
	public static Color getPrimary2()
	{
		return ColorBlind.getDichromatColor(AdempiereThemeInnova.primary2);
	}
	/**
	 * 	Get Primary3
	 *	@return primary 3
	 */
	public static Color getPrimary3()
	{
		return ColorBlind.getDichromatColor(AdempiereThemeInnova.primary3);
	}
	/**
	 * 	Get Secondary 1
	 *	@return secondary 1
	 */
	public static Color getSecondary1()
	{
		return ColorBlind.getDichromatColor(AdempiereThemeInnova.secondary1);
	}
	/**
	 * 	Get Secondary 2
	 *	@return secondary 2
	 */
	public static Color getSecondary2()
	{
		return ColorBlind.getDichromatColor(AdempiereThemeInnova.secondary2);
	}
	/**
	 * 	Get Secondary 3
	 *	@return secondary 3
	 */
	public static Color getSecondary3()
	{
		return ColorBlind.getDichromatColor(AdempiereThemeInnova.secondary3);
	}


	/****** Fonts ************************************************************/

	/**
	 *  Get Header Font (window/label font)
	 *  @return font
	 */
	public static Font getFont_Header()
	{
		return CompiereTheme.windowFont;
	//	return UIManager.getFont("Label.font");
	}   //  getFont_Header

	/**
	 *  Get Field Font
	 *  @return font
	 */
	public static Font getFont_Field()
	{
		return CompiereTheme.userFont;
	//	return UIManager.getFont("TextField.font");
	}   //  getFont_Field

	/**
	 *  Get Label Font
	 *  @return font
	 */
	public static Font getFont_Label()
	{
		return CompiereTheme.controlFont;
	//	return UIManager.getFont("Label.font");
	}   //  setFont_Label

	/**
	 *  Get Small (report) Font
	 *  @return font
	 */
	public static Font getFont_Small()
	{
		return CompiereTheme.smallFont;
	}   //  setFont_Small

	/****** Available L&F ****************************************************/

	/** Availablle Looks    */
	private static ValueNamePair[]  s_looks = null;
	/** Default PLAF        */
	private static ValueNamePair    s_defaultPLAF = null;
	/** Availablle Themes   */
	private static ValueNamePair[]  s_themes = null;

	private static ValueNamePair    s_vp_adempiereTheme = null;
	private static ValueNamePair    s_vp_metalTheme = null;
	private static ValueNamePair    s_vp_kunststoffTheme = null;
	//e-evolution vpj-cd 19102006
	private static ValueNamePair    s_vp_jgoodiesTheme = null;
	//e-evolution vpj-cd 1910200sky

	/** PLAF class list
		com.sun.java.swing.plaf.windows.WindowsLookAndFeel
		com.sun.java.swing.plaf.motif.MotifLookAndFeel
		javax.swing.plaf.metal.MetalLookAndFeel

	/**
	 *  Static Initializer.
	 *  - Fill available PLAFs and Themes
	 */
	static
	{
		ArrayList<ValueNamePair> plafList = new ArrayList<ValueNamePair>();
		//e-evolution vpj-cd 19102006
		ValueNamePair vp = null;
		ArrayList<ValueNamePair> themeList = new ArrayList<ValueNamePair>();
		
		try
		{
			Class c = Class.forName("com.jgoodies.looks.plastic.PlasticLookAndFeel");
			vp = new ValueNamePair("org.adempiere.plaf.AdempiereLookAndFeel", org.adempiere.plaf.AdempiereLookAndFeel.NAME);
			plafList.add(vp);
			s_vp_jgoodiesTheme = new ValueNamePair("org.compiere.plaf.AdempiereTheme", AdempiereThemeInnova.NAME);
			themeList.add (s_vp_jgoodiesTheme);
		}
		catch (Exception e)
		{
		//	log.severe("Kuststoff not found");
		}
		//e-evolution vpj-cd 19102006
		//vpj-cd ValueNamePair vp = new ValueNamePair("org.compiere.plaf.CompiereLookAndFeel", CompiereLookAndFeel.NAME);
		vp = new ValueNamePair("org.compiere.plaf.CompiereLookAndFeel", CompiereLookAndFeel.NAME);
		plafList.add (vp);
		//  Themes
		//vpj-cd ArrayList<ValueNamePair> themeList = new ArrayList<ValueNamePair>();
		s_vp_adempiereTheme = new ValueNamePair("org.compiere.plaf.CompiereThemeBlueMetal", CompiereThemeBlueMetal.NAME);
		themeList.add (s_vp_adempiereTheme);
		themeList.add (new ValueNamePair("org.compiere.plaf.CompiereThemeIce", CompiereThemeIce.NAME));
		//	Metal
		s_vp_metalTheme = new ValueNamePair("javax.swing.plaf.metal.DefaultMetalTheme", "Steel");
		themeList.add (s_vp_metalTheme);
		themeList.add (new ValueNamePair("javax.swing.plaf.metal.OceanTheme", "Ocean"));
//		themeList.add (new ValueNamePair("javax.swing.plaf.metal.MetalHighContrastTheme", "Java High Contrast"));
		//
		themeList.add (new ValueNamePair("org.compiere.plaf.AquaTheme", "Aqua"));
		themeList.add (new ValueNamePair("org.compiere.plaf.CharcoalTheme", "Charcoal"));
		themeList.add (new ValueNamePair("org.compiere.plaf.ContrastTheme", "Contrast"));
		themeList.add (new ValueNamePair("org.compiere.plaf.EmeraldTheme", "Emerald"));
		themeList.add (new ValueNamePair("org.compiere.plaf.RubyTheme", "Ruby"));

		//  Discover and Install - Kuststoff
		try
		{
			Class c = Class.forName("com.incors.plaf.kunststoff.KunststoffLookAndFeel");
			vp = new ValueNamePair("com.incors.plaf.kunststoff.KunststoffLookAndFeel", "Kunststoff");
			plafList.add(vp);
			vp = new ValueNamePair("com.incors.plaf.kunststoff.KunststoffTheme", "Kuststoff");
			themeList.add(vp);
			s_vp_kunststoffTheme = vp;
		}
		catch (Exception e)
		{
		//	log.severe("Kuststoff not found");
		}

		//  Install discovered PLAFs
		for (int i = 0; i < plafList.size(); i++)
		{
			vp = (ValueNamePair)plafList.get(i);
			UIManager.installLookAndFeel(vp.getName(), vp.getValue());
		}

		//  Fill Available PLAFs
		plafList = new ArrayList<ValueNamePair>();
		UIManager.LookAndFeelInfo[] lfInfo = UIManager.getInstalledLookAndFeels();
		for (int i = 0; i < lfInfo.length; i++)
		{
			vp = new ValueNamePair (lfInfo[i].getClassName(), lfInfo[i].getName());
			plafList.add(vp);
                        //begin vpj-cd e-evolution 26 oct 2006
			//if (lfInfo[i].getName().equals(CompiereLookAndFeel.NAME))
			if (lfInfo[i].getName().equals(org.adempiere.plaf.AdempiereLookAndFeel.NAME))
			//end vpj-cd e-evolution 26 oct 2006
			{
				s_defaultPLAF = vp;
				log.finest(vp.getName() + " (default)");
			}
			else
				log.finest(vp.getName());
		}
		s_looks = new ValueNamePair[plafList.size()];
		plafList.toArray(s_looks);

		//  Fill Available Themes
		s_themes = new ValueNamePair[themeList.size()];
		themeList.toArray(s_themes);
		//
	//	printPLAFDefaults();
	}   //  static Initializer


	/**
	 *  Get available Look And Feels
	 *  @return Array of ValueNamePair with name and class of Look and Feel
	 */
	public static ValueNamePair[] getPLAFs()
	{
		return s_looks;
	}   //  getPLAFs

	/**
	 *  Get the list of available Metal Themes if the current L&F is a Metal L&F
	 *  @return Array of Strings with Names of Metal Themes
	 */
	public static ValueNamePair[] getThemes ()
	{
		if (UIManager.getLookAndFeel() instanceof MetalLookAndFeel)
			return s_themes;
		return new ValueNamePair[0];
	}   //  getThemes

	
	/**************************************************************************
	 *  Set PLAF based on Ini Properties
	 *  @param win Optional Window
	 */
	public static void setPLAF (Window win)
	{
		String look = Ini.getProperty(Ini.P_UI_LOOK);
		String lookTheme = Ini.getProperty(Ini.P_UI_THEME);
		//  Search for PLAF
		ValueNamePair plaf = null;
		for (int i = 0; i < s_looks.length; i++)
		{
			if (s_looks[i].getName().equals(look))
			{
				plaf = s_looks[i];
				break;
			}
		}
		//  Search for Theme
		ValueNamePair theme = null;
		for (int i = 0; i < s_themes.length; i++)
		{
			if (s_themes[i].getName().equals(lookTheme))
			{
				theme = s_themes[i];
				break;
			}
		}
		//  Set PLAF
		setPLAF (plaf == null ? s_defaultPLAF : plaf, theme,   win);
	}   //  setPLAF

	/**
	 *  Set PLAF and update Ini
	 *
	 *  @param plaf     ValueNamePair of the PLAF to be set
	 *  @param theme    Optional Theme name
	 *  @param win      Optional Window
	 */
	public static void setPLAF (ValueNamePair plaf, ValueNamePair theme, Window win)
	{
		if (plaf == null)
			return;
		log.config(plaf	+ (theme == null ? "" : (" - " + theme)));

		//  Look & Feel
		try
		{
			UIManager.setLookAndFeel(plaf.getValue());
		}
		catch (Exception e)
		{
			log.severe(e.getMessage());
		}
		LookAndFeel laf = UIManager.getLookAndFeel();
		Ini.setProperty(Ini.P_UI_LOOK, plaf.getName());

		//  Optional Theme
		Ini.setProperty(Ini.P_UI_THEME, "");
		//  Default Theme
		if (theme == null && laf instanceof MetalLookAndFeel)
		{
			String className = laf.getClass().getName();
			if (className.equals("javax.swing.plaf.metal.MetalLookAndFeel"))
				theme = s_vp_metalTheme;
			else if (className.equals("org.compiere.plaf.CompiereLookAndFeel"))
				theme = s_vp_adempiereTheme;
			else if (className.equals("com.incors.plaf.kunststoff.KunststoffLookAndFeel"))
				theme = s_vp_kunststoffTheme;
			//e-evolution vpj-cd
			else if (className.equals("org.adempiere.plaf.AdempiereLookAndFeel"))
				theme = s_vp_jgoodiesTheme;
			//e-evolution vpj-cd
		}
		if (theme != null && laf instanceof MetalLookAndFeel && theme.getValue().length() > 0)
		{
			try
			{
				Class c = Class.forName(theme.getValue());
				MetalTheme t = (MetalTheme)c.newInstance();
				if (t instanceof CompiereTheme)
					AdempiereThemeInnova.load();
				if (laf instanceof CompiereLookAndFeel)
					CompiereLookAndFeel.setCurrentTheme(t);
				else
					MetalLookAndFeel.setCurrentTheme(t);
				//
				boolean flat = Ini.isPropertyBool(Ini.P_UI_FLAT);
				CompiereTheme.setTheme(t, flat);  //  copies it if not AdempiereTheme
				Ini.setProperty(Ini.P_UI_THEME, theme.getName());
			}
			catch (Exception e)
			{
				log.severe("Theme - " + e.getMessage());
			}
		}
		updateUI (win);
		log.config(plaf + " - " + theme);
	//	printPLAFDefaults();
	}   //  setPLAF

	/**
	 *  Update UI of this and parent Windows
	 *  @param win window
	 */
	public static void updateUI (Window win)
	{
		if (win == null)
			return;
		Window c = win;
		do
		{
			SwingUtilities.updateComponentTreeUI(c);
			c.invalidate();
			c.pack();
			c.validate();
			c.repaint();
			c = c.getOwner();
		}
		while (c != null);
	}   //  updateUI

	/**
	 *  Reset PLAF Settings
	 *  @param win Window to be reset
	 */
	public static void reset (Window win)
	{
		//  Clean Theme Properties
		AdempiereThemeInnova.reset ();      //  sets properties
		AdempierePLAF.setPLAF (win);
	}   //  reset

	/**
	 *  Print current UIDefaults
	 */
	public static void printPLAFDefaults ()
	{
		System.out.println(UIManager.getLookAndFeel());
		Object[] keys = UIManager.getLookAndFeelDefaults().keySet().toArray();
		Arrays.sort(keys);
		char lastStart = ' ';
		for (int i = 0; i < keys.length; i++)
		{
			StringBuffer sb = new StringBuffer();
			sb.append(keys[i]).append(" = ").append(UIManager.get(keys[i]));
			if (keys[i].toString().charAt(0) != lastStart)
			{
				System.out.println();
				lastStart = keys[i].toString().charAt(0);
			}
			System.out.println(sb);
		}
	}   //  printPLAFDefaults

	/**
	 *  Is AdempiereL&F the active L&F
	 *  @return true if L&F is Adempiere
	 */
	public static boolean isActive()
	{
		return UIManager.getLookAndFeel() instanceof CompiereLookAndFeel;
	}   //  isActive

	/*************************************************************************/

	static ResourceBundle   s_res = ResourceBundle.getBundle("org.compiere.plaf.PlafRes");

	/**
	 *  Create OK Button
	 *  @return OK button
	 */
	public static CButton getOKButton()
	{
		CButton b = new CButton();
		b.setIcon(new ImageIcon(AdempierePLAF.class.getResource("icons/Ok24.gif")));
		b.setMargin(new Insets(0,10,0,10));
		b.setToolTipText (s_res.getString("OK"));
		return b;
	}   //  getOKButton

	/**
	 *  Create Cancel Button
	 *  @return Cancel button
	 */
	public static CButton getCancelButton()
	{
		CButton b = new CButton();
		b.setIcon(new ImageIcon(AdempierePLAF.class.getResource("icons/Cancel24.gif")));
		b.setMargin(new Insets(0,10,0,10));
		b.setToolTipText (s_res.getString("Cancel"));
		return b;
	}   //  getCancelButton

	/**
	 *  Center Window on Screen and show it
	 *  @param window window
	 */
	public static void showCenterScreen (Window window)
	{
		window.pack();
		Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension wSize = window.getSize();
		window.setLocation(((sSize.width-wSize.width)/2), ((sSize.height-wSize.height)/2));
		window.toFront();
		window.setVisible(true);
	}	//	showCenterScreen

	
	/**************************************************************************
	 *  Start Class With Adempiere Look or Adempiere PLAF Editor
	 *  @param args first parameter is class to start, if none start PLAF Editor
	 */
	public static void main (String[] args)
	{
		String jVersion = System.getProperty("java.version");
		if (!(jVersion.startsWith("1.5")))
		{
			JOptionPane.showMessageDialog (null,
				"Require Java Version 1.5 or up - Not " + jVersion,
				"AdempierePLAF - Version Conflict",
				JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		//  set the defined PLAF
		Ini.loadProperties (true);
		AdempiereThemeInnova.load ();
		setPLAF (null);
		//
		if (args.length == 0)
		{
			AdempierePLAFFrame frame = new AdempierePLAFFrame();
			return;
		}

		String className = args[0];
		//  find class
		Class startClass = null;
		try
		{
			startClass = Class.forName(className);
		}
		catch (Exception e)
		{
			log.severe("Did not find: " + className);
			e.printStackTrace();
			System.exit(1);
		}

		//  try static main method
		try
		{
			Method[] methods = startClass.getMethods();
			for (int i = 0; i < methods.length; i++)
			{
				if (Modifier.isStatic(methods[i].getModifiers()) && methods[i].getName().equals("main"))
				{
					String[] startArgs = new String[args.length-1];
					for (int ii = 1; ii < args.length; ii++)
						startArgs[ii-i] = args[ii];
					methods[i].invoke(null, new Object[] {startArgs});
				}
				return;
			}
		}
		catch (Exception ee)
		{
			log.severe("Problems invoking main");
			ee.printStackTrace();
		}

		//  start the class
		try
		{
			startClass.newInstance();
		}
		catch (Exception e)
		{
			log.severe("Cannot start: " + className);
			e.printStackTrace();
			System.exit(1);
		}
	}   //  main

}   //  AdempierePLAF

/**
 *  Frame to display Editor
 */
class AdempierePLAFFrame extends CFrame
{
	/**
	 *  Frame to display Editor
	 */
	public AdempierePLAFFrame()
	{
		super("AdempierePLAF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdempierePLAF.class.getResource("icons/CL16.gif")));
		AdempierePLAF.showCenterScreen(this);
	}   //  AdempierePLAFFrame

	/**
	 *  Show Editor
	 *  @param e event
	 */
	protected void processWindowEvent (WindowEvent e)
	{
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_OPENED)
		{
			AdempierePLAFEditor ed = new AdempierePLAFEditor(this, true);
			dispose();
		}
	}   //  processWindowEvents
}   //  AdempierePLAFFrame
