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

/*
 * @(#)ContrastTheme.java 1.10 04/07/26
 */
import javax.swing.UIDefaults;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 * This class describes a higher-contrast Metal Theme.
 * 
 * @version 1.10 07/26/04
 * @author Michael C. Albers, Jorg Janke
 */
public class ContrastTheme extends DefaultMetalTheme
{

	public String getName ()
	{
		return "Contrast";
	}

	private final ColorUIResource primary1
		= new ColorUIResource (0, 0, 0);

	private final ColorUIResource primary2
		= new ColorUIResource (204, 204, 204);

	private final ColorUIResource primary3
		= new ColorUIResource (255, 255, 255);

	private final ColorUIResource primaryHighlight
		= new ColorUIResource (102, 102, 102);

	private final ColorUIResource secondary2
		= new ColorUIResource (204, 204, 204);

	private final ColorUIResource secondary3
		= new ColorUIResource (255, 255, 255);

	private final ColorUIResource controlHighlight
		= new ColorUIResource (102, 102, 102);

	protected ColorUIResource getPrimary1 ()
	{
		return primary1;
	}

	protected ColorUIResource getPrimary2 ()
	{
		return primary2;
	}

	protected ColorUIResource getPrimary3 ()
	{
		return primary3;
	}

	public ColorUIResource getPrimaryControlHighlight ()
	{
		return primaryHighlight;
	}

	protected ColorUIResource getSecondary2 ()
	{
		return secondary2;
	}

	protected ColorUIResource getSecondary3 ()
	{
		return secondary3;
	}

	public ColorUIResource getControlHighlight ()
	{
		return super.getSecondary3 ();
	}

	public ColorUIResource getFocusColor ()
	{
		return getBlack ();
	}

	public ColorUIResource getTextHighlightColor ()
	{
		return getBlack ();
	}

	public ColorUIResource getHighlightedTextColor ()
	{
		return getWhite ();
	}

	public ColorUIResource getMenuSelectedBackground ()
	{
		return getBlack ();
	}

	public ColorUIResource getMenuSelectedForeground ()
	{
		return getWhite ();
	}

	public ColorUIResource getAcceleratorForeground ()
	{
		return getBlack ();
	}

	public ColorUIResource getAcceleratorSelectedForeground ()
	{
		return getWhite ();
	}

	public void addCustomEntriesToTable (UIDefaults table)
	{
		Border blackLineBorder = new BorderUIResource 
			(new LineBorder(getBlack ()));
		Border whiteLineBorder = new BorderUIResource(
			new LineBorder(getWhite()));
		//
		Object textBorder = new BorderUIResource (new CompoundBorder 
			(blackLineBorder, new BasicBorders.MarginBorder()));

		//	Enhancements
		Object[] defaults = new Object[]
		{
			"ToolTip.border", blackLineBorder, 
			"TitledBorder.border", blackLineBorder, 
			"TextField.border", textBorder,
			"PasswordField.border", textBorder, 
			"TextArea.border", textBorder,
			"TextPane.border", textBorder, 
			"EditorPane.border", textBorder,
			//
			"ComboBox.background", getWindowBackground (),
			"ComboBox.foreground", getUserTextColor (),
			"ComboBox.selectionBackground", getTextHighlightColor (),
			"ComboBox.selectionForeground", getHighlightedTextColor (),
			"ProgressBar.foreground", getUserTextColor (),
			"ProgressBar.background", getWindowBackground (),
			"ProgressBar.selectionForeground", getWindowBackground (),
			"ProgressBar.selectionBackground", getUserTextColor (),
			"OptionPane.errorDialog.border.background", getPrimary1 (),
			"OptionPane.errorDialog.titlePane.foreground", getPrimary3 (),
			"OptionPane.errorDialog.titlePane.background", getPrimary1 (),
			"OptionPane.errorDialog.titlePane.shadow", getPrimary2 (),
			"OptionPane.questionDialog.border.background", getPrimary1 (),
			"OptionPane.questionDialog.titlePane.foreground", getPrimary3 (),
			"OptionPane.questionDialog.titlePane.background", getPrimary1 (),
			"OptionPane.questionDialog.titlePane.shadow", getPrimary2 (),
			"OptionPane.warningDialog.border.background", getPrimary1 (),
			"OptionPane.warningDialog.titlePane.foreground", getPrimary3 (),
			"OptionPane.warningDialog.titlePane.background", getPrimary1 (),
			"OptionPane.warningDialog.titlePane.shadow", getPrimary2 (),
		};
		table.putDefaults (defaults);
	}
}
