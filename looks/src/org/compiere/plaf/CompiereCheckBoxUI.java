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
 * Contributor(s): kisitomomo                                                 *
 *****************************************************************************/
package org.compiere.plaf;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.*;
import javax.swing.plaf.metal.MetalCheckBoxUI;
import java.awt.Color;
import java.awt.Graphics;
import org.adempiere.plaf.*;

import com.jgoodies.looks.plastic.PlasticLookAndFeel;
/**
 *  Check Box UI
 *
 *  @author     Jorg Janke
 *  @version    $Id: AdempiereCheckBoxUI.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 *  
 *  globalqss: integrate kisitomomo patch
 *     [ 1612136 ] checkbox look and feel improved
 *     https://sourceforge.net/tracker/index.php?func=detail&aid=1612136&group_id=176962&atid=879334
 */
public class CompiereCheckBoxUI extends MetalCheckBoxUI
{
	/**
	 *  Create UI
	 *  @param b
	 *  @return ComponentUI
	 */
	public static ComponentUI createUI (JComponent b)
	{
		
		return s_checkBoxUI;
	}   //  createUI

	/** UI shared   */
	private static CompiereCheckBoxUI s_checkBoxUI = new CompiereCheckBoxUI();

	
	/**************************************************************************
	 *  Install Defaults
	 *  @param b
	 */
	public void installDefaults (AbstractButton b)
	{
		super.installDefaults(b);
		b.setOpaque(false);
	}   //  installDefaults

	/**
	 * 	Create Button Listener
	 *	@param b button
	 *	@return listener
	 */
	protected BasicButtonListener createButtonListener (AbstractButton b)
	{
		return new CompiereButtonListener(b);
	}	//	createButtonListener
	
//kisito code
	
	@Override
	protected Color getDisabledTextColor() {
	    return AdempiereTheme.DARKEN_STOP;
	}	
	private static void drawCheck(Graphics g, int x, int y) {
		g.translate(x, y);
		g.drawLine(3, 5, 3, 5);
		g.fillRect(3, 6, 2, 2);
		g.drawLine(4, 8, 9, 3);
		g.drawLine(5, 8, 9, 4);
		g.drawLine(5, 9, 9, 5);
		g.translate(-x, -y);
	}
	
	public synchronized void paint(java.awt.Graphics g, JComponent c) {
		
		super.paint(g,c);
		JCheckBox chk=(JCheckBox)c;
		if(!chk.isEnabled()) {
		g.setColor(PlasticLookAndFeel.getControlDarkShadow());
		g.drawRect(0, 3, 11, 11);
		if(chk.getModel().isSelected())
		{
			drawCheck(g,0,4);
		}
	}
	}
	// end kisito
}   //  AdempiereCheckBoxUI

 	  	 
