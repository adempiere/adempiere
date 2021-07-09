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
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableHeaderUI;

/**
 *  Table Header UI
 *  3D effect
 *
 *  @author     Jorg Janke
 *  @version    $Id: AdempiereTableHeaderUI.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 */
public class CompiereTableHeaderUI extends BasicTableHeaderUI
{
	/**
	 *  Static Create UI
	 *  @param c Component
	 *  @return Adempiere TableHeader UI
	 */
	public static ComponentUI createUI(JComponent c)
	{
		return new CompiereTableHeaderUI();
	}   //  createUI


	/**
	 *  Install UI - set not Opaque
	 *  @param c
	 */
	public void installUI(JComponent c)
	{
		super.installUI(c);
		//  TableHeader is in JViewpoiunt, which is Opaque
		//  When UI created, TableHeader not added to viewpoint
		//c.setOpaque(true);
		//c.putClientProperty(AdempierePLAF.BACKGROUND_FILL, "Y");
	}   //  installUI

	
	/**************************************************************************
	 *  Update -
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
	 *  @see #paint
	 *  @see javax.swing.JComponent#paintComponent
	 */
	public void update(Graphics g, JComponent c)
	{
	//	AdempiereUtils.printParents (c);     //  Parent is JViewpoint
		if (c.isOpaque())   //  flat
			CompiereUtils.fillRectange((Graphics2D)g, c, CompiereLookAndFeel.ROUND);
		//
		paint (g, c);
	}   //  update

	/**
	 *  Paint 3D box
	 *  @param g
	 *  @param c
	 */
	public void paint(Graphics g, JComponent c)
	{
		super.paint( g, c);
		CompiereUtils.paint3Deffect((Graphics2D)g, c, CompiereLookAndFeel.ROUND, true);
	}   //  paint

}   //  AdempiereTableHeader
