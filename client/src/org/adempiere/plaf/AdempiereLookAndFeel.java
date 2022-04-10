/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.plaf;

import java.awt.Color;
import java.awt.Component;

import javax.swing.UIDefaults;

import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticTheme;

/**
 *  Adempiere Look & Feel, based on JGoodies look and feel
 *  @author vpj-cd, Low Heng Sin
 */
public class AdempiereLookAndFeel extends com.jgoodies.looks.plastic.Plastic3DLookAndFeel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5681571518701552477L;

	/**
	 *  Constructor
	 */
	public AdempiereLookAndFeel()
	{
		super();
	//	System.setProperty("awt.visualbell", "true");
	}   //  CompiereLookAndFeel

	/** The name                    */
	public static final     String  NAME = "Adempiere";

	/** The Theme                   */
	private static          PlasticTheme  s_adempiereTheme = new org.adempiere.plaf.AdempiereTheme();
	private static          PlasticTheme      s_theme = s_adempiereTheme;

	/** Paint Round Corners         */
	protected static boolean ROUND = false;
	
	public static final String  TABLEVEL = "TabLevel";
	
	public static final String HIDE_IF_ONE_TAB = "adempiere.hideIfOneTab";
	
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
		return "Adempiere Look & Feel - (c) 2001-2005 Victor Perez";
	}   //  getDescription

	
	/**************************************************************************
	 *  Get/Create Defaults
	 *  @return UI Defaults
	 */
	public UIDefaults getDefaults()
	{
        //com.jgoodies.looks.plastic.PlasticLookAndFeel.setCurrentTheme(s_theme);
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
		//System.out.println("AdempiereLookAndFeel.initClassDefaults");
		super.initClassDefaults( table);
		//  Overwrite
		putDefault (table, "ComboBoxUI");
		putDefault (table, "LabelUI");
		putDefault (table, "TabbedPaneUI");

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
			String className = "org.adempiere.plaf.Adempiere" + uiKey;
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
		super.initSystemColorDefaults( table);
	}   //  initSystemColorDefaults

	/**
	 *  For overwriting Component defaults
	 *  @param table
	 */
	protected void initComponentDefaults (UIDefaults table)
	{
		super.initComponentDefaults( table);

		//  ComboBox defaults
		Color c = table.getColor("TextField.background");
		table.put("ComboBox.background", c);
		table.put("ComboBox.listBackground", c);
		
		// globalqss
		Class lf = com.jgoodies.looks.plastic.PlasticLookAndFeel.class;
		table.put("Tree.openIcon", makeIcon(lf, "icons/TreeOpen.gif"));
		table.put("Tree.closedIcon", makeIcon(lf, "icons/TreeClosed.gif"));
		table.put("Tree.leafIcon", makeIcon(lf, "icons/TreeLeaf.gif"));
		
	}   //  initComponentDefaults

	
	/**
	 *  Set Current Theme
	 *  @param theme metal theme
	 */
	public static void setCurrentTheme (PlasticTheme theme)
	{
		if (theme != null) {
			s_theme = theme;
			PlasticLookAndFeel.setCurrentTheme(s_theme);
		}
	}   //  setCurrentTheme

	/**
	 *  Get Current Theme
	 *  @return Metal Theme
	 */
	public static PlasticTheme getCurrentTheme()
	{
		return s_theme;
	}   //  getCurrentTheme

	/**
	 *  Get default adempiere theme
	 *  @return Metal Theme
	 */
	public static PlasticTheme getDefaultTheme()
	{
		return s_adempiereTheme;
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
