/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.adempiere.plaf;

import java.awt.*;

import javax.swing.*;
import javax.swing.plaf.metal.*;

/**
 *  Compiere Look & Feel.
 *  We wanted a nice UI not the battleship gray based stuff.
 *  I guess a matter of taste.
 *  <code>
 *  :
 *  UIManager.setLookAndFeel(new com.compiere.plaf.CompiereLookAndFeel());
 *  // or UIManager.setLookAndFeel("com.compiere.plaf.CompiereLookAndFeel");
 *  </code>
 *
 *  @author     Jorg Janke
 *  @version    $Id: CompiereLookAndFeel.java,v 1.19 2005/12/05 02:38:28 jjanke Exp $
 */
public class AdempiereLookAndFeel extends com.jgoodies.looks.plastic.PlasticLookAndFeel
{
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
	private static          AdempiereTheme  s_adempiereTheme = new org.adempiere.plaf.AdempiereTheme();
	private static          AdempiereTheme      s_theme = s_adempiereTheme;

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
		return "Adempiere Look & Feel - (c) 2001-2005 Victor Perez";
	}   //  getDescription

	
	/**************************************************************************
	 *  Get/Create Defaults
	 *  @return UI Defaults
	 */
	public UIDefaults getDefaults()
	{
	//	System.out.println("CompiereLookAndFeel.getDefaults");
		//  Theme already created/set
		//MetalLookAndFeel.setCurrentTheme(s_theme);
                com.jgoodies.looks.plastic.PlasticLookAndFeel.setCurrentTheme(s_theme);
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
		/*putDefault (table, "PanelUI");
		putDefault (table, "ButtonUI");
		putDefault (table, "ToggleButtonUI");
		putDefault (table, "TabbedPaneUI");
		putDefault (table, "TableHeaderUI");
		putDefault (table, "RadioButtonUI");*/
		//putDefault (table, "CheckBoxUI");
		putDefault (table, "ComboBoxUI");
		/*putDefault (table, "MenuUI");
		putDefault (table, "MenuBarUI");
		putDefault (table, "MenuItemUI");
		putDefault (table, "CheckBoxMenuItemUI");
		putDefault (table, "ToolBarUI");
		putDefault (table, "RootPaneUI");
		putDefault (table, "ViewportUI");
		putDefault (table, "SplitPaneUI");
		putDefault (table, "ScrollPaneUI");*/
		putDefault (table, "LabelUI");
		/*putDefault (table, "ToolTipUI");
		putDefault (table, "TextAreaUI");*/

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
		
		// globalqss
		Class lf = com.jgoodies.looks.plastic.PlasticLookAndFeel.class;
		table.put("Tree.openIcon", makeIcon(lf, "icons/TreeOpen.gif"));
		table.put("Tree.closedIcon", makeIcon(lf, "icons/TreeClosed.gif"));
		table.put("Tree.leafIcon", makeIcon(lf, "icons/TreeLeaf.gif"));
		
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
	public static void setCurrentTheme (org.adempiere.plaf.AdempiereTheme theme)
	{
		if (theme != null)
			s_theme = theme;
		MetalLookAndFeel.setCurrentTheme(s_theme);
	}   //  setCurrentTheme

	/**
	 *  Get Current Theme
	 *  @return Metal Theme
	 */
	public static AdempiereTheme getCurrentTheme()
	{
		return s_theme;
	}   //  getCurrentTheme

	/**
	 *  Get Compiere Theme
	 *  @return Metal Theme
	 */
	public static AdempiereTheme getAdempiereTheme()
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

}   //  CompiereLookAndFeel
