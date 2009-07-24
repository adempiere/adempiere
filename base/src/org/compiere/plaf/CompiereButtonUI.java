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

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.plaf.metal.MetalButtonUI;

/**
 *  Button UI
 *
 *  @author     Jorg Janke
 *  @version    $Id: AdempiereButtonUI.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 */
public class CompiereButtonUI extends MetalButtonUI
{
	/**
	 *  Static Create UI
	 *  @param c
	 *  @return Adempiere Button UI
	 */
	public static ComponentUI createUI (JComponent c)
	{
		return s_buttonUI;
	}   //  createUI

	/** UI shared   */
	private static CompiereButtonUI s_buttonUI = new CompiereButtonUI();

	
	/**************************************************************************
	 *  Install Defaults
	 *  @param b
	 */
	public void installDefaults(AbstractButton b)
	{
		super.installDefaults(b);
		b.setOpaque(false);
	}   //  installDefaults

	/**
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
	 *  @see #paint
	 *  @see javax.swing.JComponent#paintComponent
	 */
	public void update(Graphics g, JComponent c)
	{
	//	System.out.println(c.getClass() + " ** " + ((JButton)c).getText() + " ** " + c.isOpaque());
		if (c.isOpaque())
			CompiereUtils.fillRectange((Graphics2D)g, c, CompiereLookAndFeel.ROUND);
		paint (g, c);
	}   //  update

	/**
	 *  Paint 3D boxes
	 *  @param g
	 *  @param c
	 */
	public void paint (Graphics g, JComponent c)
	{
		super.paint( g, c);
		AbstractButton b = (AbstractButton) c;
		ButtonModel model = b.getModel();
		boolean in = model.isPressed() || model.isSelected();
		//
		if (b.isBorderPainted())
			CompiereUtils.paint3Deffect((Graphics2D)g, c, CompiereLookAndFeel.ROUND, !in);
	}   //  paint

	/**
	 *  Don't get selected Color - use default (otherwise the pressed button is gray)
	 *  @param g
	 *  @param b
	 */
	protected void paintButtonPressed(Graphics g, AbstractButton b)
	{
	//	if (b.isContentAreaFilled())
	//	{
	//		Dimension size = b.getSize();
	//		g.setColor(getSelectColor());
	//		g.fillRect(0, 0, size.width, size.height);
	//	}
	}   //  paintButtonPressed

	/**
	 * 	Is Tool Bar Button
	 *	@param c
	 *	@return true if toolbar
	 */
	boolean isToolBarButton(JComponent c) 
	{
        return (c.getParent() instanceof JToolBar);
    }	//	isToolBarButton
	
	
	/**
	 * 	Create Button Listener
	 *	@param b button
	 *	@return listener
	 */
	protected BasicButtonListener createButtonListener (AbstractButton b)
	{
		return new CompiereButtonListener(b);
	}	//	createButtonListener
	
}   //  AdempiereButtonUI
