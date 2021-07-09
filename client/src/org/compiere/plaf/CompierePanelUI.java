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

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;

/**
 *  Panel UI.
 *  The default properties can be set via
 *  <pre>
 *  AdempierePanelUI.setDefaultBackground (new AdempiereColor());
 *  </pre>
 *  The individual Panel can set the background type by setting the
 *  parameter via
 *  <pre>
 *  putClientProperty(AdempierePanelUI.BACKGROUND, new AdempiereColor());
 *  </pre>
 *  @see org.compiere.swing.CPanel
 *
 *  @author     Jorg Janke
 *  @version    $Id: AdempierePanelUI.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 */
public class CompierePanelUI extends BasicPanelUI
{
	/**
	 *  Static Create UI
	 *  @param c JComponent
	 *  @return Adempiere Panel UI
	 */
	public static ComponentUI createUI (JComponent c)
	{
	//	return new AdempierePanelUI();
		return s_panelUI;
	}   //  createUI

	/** UI                  */
	private static CompierePanelUI  s_panelUI = new CompierePanelUI();

	
	/**************************************************************************
	 * Install Defaults
	 * @param p Panel
	 */
	protected void installDefaults (JPanel p)
	{
		super.installDefaults(p);
		/** If enabled, all windows are with Adempiere Background,
		 * 	but Sun dialogs (print ..) are "patchy" as they are opaque		**
	//	System.out.println ("BG=" + p.getClientProperty(AdempierePLAF.BACKGROUND));
		if (s_setDefault || p.getClientProperty(AdempierePLAF.BACKGROUND) == null)
			p.putClientProperty (AdempierePLAF.BACKGROUND, s_default);
		/** **/
	}   //  installDefaults

	
	/**************************************************************************
	 *  Update.
	 *  This method is invoked by <code>JComponent</code> when the specified
	 *  component is being painted.
	 *
	 *  By default this method will fill the specified component with
	 *  its background color (if its <code>opaque</code> property is
	 *  <code>true</code>) and then immediately call <code>paint</code>.
	 *
	 *  @param g the <code>Graphics</code> context in which to paint
	 *  @param c the component being painted
	 *	
	 *  @see javax.swing.JComponent#paintComponent
	 */
	public void update (Graphics g, JComponent c)
	{
	//	AdempiereUtils.printParents (c);
		if (c.isOpaque())
			updateIt (g, c);
		paint (g, c);   //  does nothing
	}   //  update

	/**
	 *  Print background based on AdempiereColor or flat background if not found
	 *  @param g
	 *  @param c
	 */
	static void updateIt (Graphics g, JComponent c)
	{
	//	System.out.print("Panel " + c.getName());
	//	System.out.print(" Bounds=" + c.getBounds().toString());
	//	System.out.print(" - Background: ");

		//  Get AdempiereColor
		CompiereColor bg = null;
		try
		{
			bg = (CompiereColor)c.getClientProperty(CompiereLookAndFeel.BACKGROUND);
		}
		catch (Exception e)
		{
			System.err.println("AdempierePanelUI - ClientProperty: " + e.getMessage());
		}
		//  paint adempiere background
		if (bg != null)
		{
	//		System.out.print(bg);
			bg.paint (g, c);
		}
		else
		{
	//		System.out.print(c.getBackground());
			g.setColor(c.getBackground());
			g.fillRect(0,0, c.getWidth(), c.getHeight());
		}
	//	System.out.println();
	}   //  updateIt

	/*************************************************************************/


}   //  AdempierePanel
