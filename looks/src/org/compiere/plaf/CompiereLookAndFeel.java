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

import javax.swing.*;
import javax.swing.plaf.metal.*;

/**
 *  Compiere Look & Feel.
 *  We wanted a nice UI not the battleship gray based stuff.
 *  I guess a matter of taste.
 *  <code>
 *  :
 *  UIManager.setLookAndFeel(new com.adempiere.plaf.AdempiereLookAndFeel());
 *  // or UIManager.setLookAndFeel("com.adempiere.plaf.AdempiereLookAndFeel");
 *  </code>
 *
 *  @author     Jorg Janke
 *  @version    $Id: AdempiereLookAndFeel.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 */
public class CompiereLookAndFeel extends MetalLookAndFeel
{
	/**
	 *  Constructor
	 */
	public CompiereLookAndFeel()
	{
		super();
	//	System.setProperty("awt.visualbell", "true");
	}   //  AdempiereLookAndFeel

	/** The name                    */
	public static final     String  NAME = "Compiere";

	/** The Theme                   */
	private static          CompiereTheme   s_compiereTheme = new CompiereThemeBlueMetal();
	private static          MetalTheme      s_theme = s_compiereTheme;

	/** Paint Round Corners         */
	protected static boolean ROUND = false;

	/**
	 *  The Name
	 *  @return Name
	 */
	public String getName()
	{
		return NAME;
	}   //  getName

	/**
	 *  The ID
	 *  @return Name
	 */
	public String getID()
	{
		return NAME;
	}   //  getID

	/**
	 *  The Description
	 *  @return description
	 */
	public String getDescription()
	{
		return "Compiere Look & Feel - (c) 2001-2005 Jorg Janke";
	}   //  getDescription

	
	/**************************************************************************
	 *  Get/Create Defaults
	 *  @return UI Defaults
	 */
	public UIDefaults getDefaults()
	{
	//	System.out.println("CompiereLookAndFeel.getDefaults");
		//  Theme already created/set
		MetalLookAndFeel.setCurrentTheme(s_theme);
		UIDefaults defaults = super.getDefaults();  // calls init..Defaults
		return defaults;
	}   //  getDefaults

	/**
	 * Creates the mapping from UI class IDs to <code>ComponentUI</code> classes,
	 * putting the ID-<code>ComponentUI</code> pairs in the passed-in defaults table.
	 * Each <code>JComponent</code> class specifies its own UI class ID string.
	 *
	 * @param table UI Defaults
	 */
	protected void initClassDefaults(UIDefaults table)
	{
	//	System.out.println("CompiereLookAndFeel.initClassDefaults");
		super.initClassDefaults( table);
		//  Overwrite
		putDefault (table, "PanelUI");
		putDefault (table, "ButtonUI");
		putDefault (table, "ToggleButtonUI");
		putDefault (table, "TabbedPaneUI");
		putDefault (table, "TableHeaderUI");
		putDefault (table, "RadioButtonUI");
		putDefault (table, "CheckBoxUI");
		putDefault (table, "ComboBoxUI");
		putDefault (table, "MenuUI");
		putDefault (table, "MenuBarUI");
		putDefault (table, "MenuItemUI");
		putDefault (table, "CheckBoxMenuItemUI");
		putDefault (table, "ToolBarUI");
		putDefault (table, "RootPaneUI");
		putDefault (table, "ViewportUI");
		putDefault (table, "SplitPaneUI");
		putDefault (table, "ScrollPaneUI");
		putDefault (table, "LabelUI");
		putDefault (table, "ToolTipUI");
		putDefault (table, "TextAreaUI");

	}   //  initClassDefaults

	/**
	 *  Put "uiKey - ClassName" pair in UIDefaults
	 *  @param table
	 *  @param uiKey
	 */
	private void putDefault (UIDefaults table, String uiKey)
	{
		try
		{
			String className = "org.compiere.plaf.Compiere" + uiKey;
			table.put(uiKey, className);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}   //  putDefault

	/**
	 *  For overwriting Component defaults
	 *  @param table
	 */
	protected void initSystemColorDefaults (UIDefaults table)
	{
	//	System.out.println("CompiereLookAndFeel.initSystemColorDefaults");
		super.initSystemColorDefaults( table);

		// we made the color a bit darker
	//	table.put("textHighlight", CompiereUtils.getTranslucentColor(getTextHighlightColor(), 128));
	}   //  initSystemColorDefaults

	/**
	 *  For overwriting Component defaults
	 *  @param table
	 */
	protected void initComponentDefaults (UIDefaults table)
	{
	//	System.out.println("CompiereLookAndFeel.initComponentDefaults");
		super.initComponentDefaults( table);

		//  ComboBox defaults
		Color c = table.getColor("TextField.background");
		table.put("ComboBox.background", c);
		table.put("ComboBox.listBackground", c);
	}   //  initComponentDefaults

	
	/**************************************************************************
	 *  Create Default Thems
	 */
	protected void createDefaultTheme()
	{
		setCurrentTheme(s_theme);
	}   //  createDefaultTheme

	/**
	 *  Set Current Theme
	 *  @param theme metal theme
	 */
	public static void setCurrentTheme (MetalTheme theme)
	{
		if (theme != null)
			s_theme = theme;
		MetalLookAndFeel.setCurrentTheme(s_theme);
	}   //  setCurrentTheme

	/**
	 *  Get Current Theme
	 *  @return Metal Theme
	 */
	public static MetalTheme getCurrentTheme()
	{
		return s_theme;
	}   //  getCurrentTheme

	/**
	 *  Get Compiere Theme
	 *  @return Metal Theme
	 */
	public static CompiereTheme getCompiereTheme()
	{
		return s_compiereTheme;
	}   //  getCurrentTheme

	/**
	 *  Error Feedback.
	 *  <p>
	 *  Invoked when the user attempts an invalid operation,
	 *  such as pasting into an uneditable <code>JTextField</code>
	 *  that has focus.
	 *  </p>
	 *  <p>
	 *  If the user has enabled visual error indication on
	 *  the desktop, this method will flash the caption bar
	 *  of the active window. The user can also set the
	 *  property awt.visualbell=true to achieve the same
	 *  results.
	 *  </p>
	 *  @param component Component the error occured in, may be
	 *			null indicating the error condition is
	 *			not directly associated with a
	 *			<code>Component</code>.
	 */
	public void provideErrorFeedback (Component component)
	{
		super.provideErrorFeedback (component);
	}   //  provideErrorFeedback

}   //  AdempiereLookAndFeel
