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
package org.adempiere.plaf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;

import org.compiere.plaf.CompiereLookAndFeel;
import org.compiere.plaf.CompiereThemeBlueMetal;
import org.compiere.plaf.CompiereThemeIce;
import org.compiere.swing.CButton;
import org.compiere.swing.ColorBlind;
import org.compiere.swing.ExtendedTheme;
import org.compiere.util.Ini;
import org.compiere.util.ValueNamePair;

import com.jgoodies.looks.plastic.PlasticTheme;

/**
 *  Variable Pluggable Look And Feel.
 *  Provides an easy access to the required currently active PLAF information
 *
 *  @author     Jorg Janke
 *  @version    $Id: AdempierePLAF.java,v 1.3 2006/07/30 00:52:23 jjanke Exp $
 *  
 *  @author Low Heng Sin
 *  @version 2006-11-27
 */
public final class AdempierePLAF
{
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
	 *  Return Error field background
	 *  @return Color
	 */
	public static Color getFieldBackground_Error()
	{
		Color c = UIManager.getColor(ExtendedTheme.ERROR_BG_KEY);
		if (c == null) 
			c = ExtendedTheme.DEFAULT_ERROR_BG;
		return ColorBlind.getDichromatColor(c);
	}   //  getFieldBackground_Error

	/**
	 *  Return Mandatory field background color
	 *  @return Color
	 */
	public static Color getFieldBackground_Mandatory()
	{
		Color c = UIManager.getColor(ExtendedTheme.MANDATORY_BG_KEY);
		if (c == null)
			c = ExtendedTheme.DEFAULT_MANDATORY_BG;
		return ColorBlind.getDichromatColor(c);
	}   //  getFieldBackground_Mandatory

	/**
	 *  Return Inactive field background color
	 *  @return Color
	 */
	public static Color getFieldBackground_Inactive()
	{
		Color c = UIManager.getColor(ExtendedTheme.INACTIVE_BG_KEY);
		if ( c != null )
			return ColorBlind.getDichromatColor(c);
		else
			return getFieldBackground_Normal();
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
		Color c = UIManager.getColor(ExtendedTheme.INFO_BG_KEY);
		if (c == null) 
			c = UIManager.getColor("info");
		return ColorBlind.getDichromatColor(c);
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
		return getTextColor_Normal();
	}   //  getText_OK

	/**
	 *	Issue Text Foreground Color (Theme)
	 *  @return Color
	 */
	public static Color getTextColor_Issue()
	{
		Color c = UIManager.getColor(ExtendedTheme.ERROR_FG_KEY);
		if (c == null)
			c = ExtendedTheme.DEFAULT_ERROR_FG;
		return ColorBlind.getDichromatColor(c);
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
		return ColorBlind.getDichromatColor(MetalLookAndFeel.getCurrentTheme().getPrimaryControlDarkShadow());
	}
	/**
	 * 	Get Primary2
	 *	@return primary 2
	 */
	public static Color getPrimary2()
	{
		return ColorBlind.getDichromatColor(MetalLookAndFeel.getCurrentTheme().getPrimaryControlShadow());
	}
	/**
	 * 	Get Primary3
	 *	@return primary 3
	 */
	public static Color getPrimary3()
	{
		return ColorBlind.getDichromatColor(MetalLookAndFeel.getCurrentTheme().getPrimaryControl());
	}
	/**
	 * 	Get Secondary 1
	 *	@return secondary 1
	 */
	public static Color getSecondary1()
	{
		return ColorBlind.getDichromatColor(MetalLookAndFeel.getCurrentTheme().getControlDarkShadow());
	}
	/**
	 * 	Get Secondary 2
	 *	@return secondary 2
	 */
	public static Color getSecondary2()
	{
		return ColorBlind.getDichromatColor(MetalLookAndFeel.getCurrentTheme().getControlShadow());
	}
	/**
	 * 	Get Secondary 3
	 *	@return secondary 3
	 */
	public static Color getSecondary3()
	{
		return ColorBlind.getDichromatColor(MetalLookAndFeel.getCurrentTheme().getControl());
	}


	/****** Fonts ************************************************************/

	/**
	 *  Get Header Font (window title font)
	 *  @return font
	 */
	public static Font getFont_Header()
	{
		return MetalLookAndFeel.getWindowTitleFont();
	}   //  getFont_Header

	/**
	 *  Get Field Font
	 *  @return font
	 */
	public static Font getFont_Field()
	{
		return UIManager.getFont("TextField.font");
	}   //  getFont_Field

	/**
	 *  Get Label Font
	 *  @return font
	 */
	public static Font getFont_Label()
	{
		return UIManager.getFont("Label.font");
	}   //  setFont_Label

	/**
	 *  Get Small (report) Font
	 *  @return font
	 */
	public static Font getFont_Small()
	{
		return MetalLookAndFeel.getSubTextFont();
	}   //  setFont_Small

	/****** Available L&F ****************************************************/

	/** Available Looks    */
	private static ValueNamePair[]  s_looks = null;
	/** Default PLAF        */
	private static ValueNamePair    s_defaultPLAF = null;
	/** Available Themes   */
	private static ValueNamePair[]  s_metalThemes = null;
	private static ValueNamePair[]  s_plasticThemes = null;

	//default theme
	private static ValueNamePair    s_vp_compiereTheme = null;
	private static ValueNamePair    s_vp_metalTheme = null;
	//e-evolution vpj-cd 19102006
	private static ValueNamePair    s_vp_adempiereTheme = null;
	//e-evolution vpj-cd 1910200sky

	/**
	 *  Static Initializer.
	 *  - Fill available PLAFs and Themes
	 */
	static
	{
		ArrayList<ValueNamePair> plafList = new ArrayList<ValueNamePair>();
		//e-evolution vpj-cd 19102006
		ValueNamePair vp = null;
		ArrayList<ValueNamePair> metalThemes = new ArrayList<ValueNamePair>();
		ArrayList<ValueNamePair> plasticThemes = new ArrayList<ValueNamePair>();
		
		try
		{
			Class.forName("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
			vp = new ValueNamePair("org.adempiere.plaf.AdempiereLookAndFeel", org.adempiere.plaf.AdempiereLookAndFeel.NAME);
			plafList.add(vp);
			s_vp_adempiereTheme = new ValueNamePair("org.adempiere.plaf.AdempiereTheme", AdempiereThemeInnova.NAME);
			plasticThemes.add (s_vp_adempiereTheme);
			
			List installedThemes = AdempiereLookAndFeel.getInstalledThemes();
			for(Object t : installedThemes) {
				MetalTheme theme = (MetalTheme)t;
				vp = new ValueNamePair(theme.getClass().getName(), theme.getName());
				plasticThemes.add(vp);
			}
		}
		catch (Exception e)
		{
			log.severe("Plastic look and feel not found");
		}
		//e-evolution vpj-cd 19102006
		vp = new ValueNamePair("org.compiere.plaf.CompiereLookAndFeel", CompiereLookAndFeel.NAME);
		plafList.add (vp);
		
		//Metal
		s_vp_compiereTheme = new ValueNamePair("org.compiere.plaf.CompiereThemeBlueMetal", CompiereThemeBlueMetal.NAME);
		metalThemes.add (s_vp_compiereTheme);
		metalThemes.add (new ValueNamePair("org.compiere.plaf.CompiereThemeIce", CompiereThemeIce.NAME));
		s_vp_metalTheme = new ValueNamePair("javax.swing.plaf.metal.OceanTheme", "Ocean");
		metalThemes.add (s_vp_metalTheme);
		metalThemes.add (new ValueNamePair("javax.swing.plaf.metal.DefaultMetalTheme", "Steel"));
		metalThemes.add (new ValueNamePair("org.compiere.plaf.AquaTheme", "Aqua"));
		metalThemes.add (new ValueNamePair("org.compiere.plaf.CharcoalTheme", "Charcoal"));
		metalThemes.add (new ValueNamePair("org.compiere.plaf.ContrastTheme", "Contrast"));
		metalThemes.add (new ValueNamePair("org.compiere.plaf.EmeraldTheme", "Emerald"));
		metalThemes.add (new ValueNamePair("org.compiere.plaf.RubyTheme", "Ruby"));

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
			if (lfInfo[i].getName().equals(org.adempiere.plaf.AdempiereLookAndFeel.NAME))
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
		s_metalThemes = new ValueNamePair[metalThemes.size()];
		metalThemes.toArray(s_metalThemes);
		
		s_plasticThemes = new ValueNamePair[plasticThemes.size()];
		plasticThemes.toArray(s_plasticThemes);
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
	 *  Get the list of available Metal or Plastic Themes.
	 *  @return Array of Strings with Names of Metal Themes
	 */
	public static ValueNamePair[] getThemes ()
	{
		LookAndFeel l = UIManager.getLookAndFeel();
		if ( l instanceof AdempiereLookAndFeel)
			return s_plasticThemes;
		else if ( l instanceof MetalLookAndFeel)
			return s_metalThemes;
		return new ValueNamePair[0];
	}   //  getThemes

	
	/**************************************************************************
	 *  Set PLAF based on Ini Properties
	 */
	public static void setPLAF ()
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
		for (int i = 0; i < s_metalThemes.length; i++)
		{
			if (s_metalThemes[i].getName().equals(lookTheme))
			{
				theme = s_metalThemes[i];
				break;
			}
		}
		
		if (theme == null) 
		{
			for (int i = 0; i < s_plasticThemes.length; i++)
			{
				if (s_plasticThemes[i].getName().equals(lookTheme))
				{
					theme = s_plasticThemes[i];
					break;
				}
			}
		}
		//  Set PLAF
		setPLAF (plaf == null ? s_defaultPLAF : plaf, theme, true);
	}   //  setPLAF

	/**
	 *  Set PLAF and update Ini
	 *
	 *  @param plaf     ValueNamePair of the PLAF to be set
	 *  @param theme    Optional Theme name
	 *  @param upateIni     Update setting to INI
	 */
	public static void setPLAF (ValueNamePair plaf, ValueNamePair theme, boolean updateIni)
	{
		if (plaf == null)
			return;
		log.config(plaf	+ (theme == null ? "" : (" - " + theme)));

		//	  Look & Feel
		Class lafClass = null;
		try {
			lafClass = Class.forName(plaf.getValue());
		}
		catch (Exception e)
		{
			log.severe(e.getMessage());
			return;
		}
		
		if ( updateIni ) {
			Ini.setProperty(Ini.P_UI_LOOK, plaf.getName());
			//  Optional Theme
			Ini.setProperty(Ini.P_UI_THEME, "");
		}
		
		//  Default Theme
		boolean metal = MetalLookAndFeel.class.isAssignableFrom(lafClass);
		boolean adempiere = AdempiereLookAndFeel.class.isAssignableFrom(lafClass);
		boolean compiere = CompiereLookAndFeel.class.isAssignableFrom(lafClass);
		if (theme == null && metal)
		{
			if (compiere)
				theme = s_vp_compiereTheme;
			else if (adempiere)
				theme = s_vp_adempiereTheme;
			else
				theme = s_vp_metalTheme;
		}
		if (theme != null && metal && theme.getValue().length() > 0)
		{
			try
			{
				Class c = Class.forName(theme.getValue());
				MetalTheme t = (MetalTheme)c.newInstance();
				if (compiere)
					CompiereLookAndFeel.setCurrentTheme(t);
				else if (adempiere && t instanceof PlasticTheme)
					AdempiereLookAndFeel.setCurrentTheme((PlasticTheme)t);
				else 
					MetalLookAndFeel.setCurrentTheme(t);
				//
				if (updateIni)
					Ini.setProperty(Ini.P_UI_THEME, theme.getName());
			}
			catch (Exception e)
			{
				log.severe("Theme - " + e.getMessage());
			}
		}
		try
		{
			UIManager.setLookAndFeel((LookAndFeel)lafClass.newInstance());
		}
		catch (Exception e)
		{
			log.severe(e.getMessage());
		}
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
	 */
	public static void reset ()
	{
		//  Clean Theme Properties
		AdempierePLAF.setPLAF ();
	}  //  reset

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
		return UIManager.getLookAndFeel() instanceof AdempiereLookAndFeel;
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

	/**
	 * Invoke the correct method to set current metal based theme.
	 * Supported look and feel are Metal, Plastic and Compiere.  
	 * @param laf Metal based look and feel
	 * @param theme Metal based theme
	 */
	public static void setCurrentMetalTheme(MetalLookAndFeel laf, MetalTheme theme)
	{
		if (laf instanceof CompiereLookAndFeel)
			CompiereLookAndFeel.setCurrentTheme(theme);
		else if (laf instanceof AdempiereLookAndFeel && theme instanceof PlasticTheme)
			AdempiereLookAndFeel.setCurrentTheme((PlasticTheme)theme);
		else 
			MetalLookAndFeel.setCurrentTheme(theme);
	}
	
	/**************************************************************************
	 *  Start Class With Adempiere Look
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
		setPLAF ();
		//
		if (args.length == 0)
		{
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
