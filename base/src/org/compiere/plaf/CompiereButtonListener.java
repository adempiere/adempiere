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

import java.awt.event.InputEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.AbstractButton;
import javax.swing.InputMap;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ComponentInputMapUIResource;
import javax.swing.plaf.basic.BasicButtonListener;

/**
 * 	Button Listener
 *	
 *  @author Jorg Janke
 *  @version $Id: AdempiereButtonListener.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 */
public class CompiereButtonListener extends BasicButtonListener
{
	/**
	 * 	Adempiere Button Listener
	 *	@param b button
	 */
	public CompiereButtonListener (AbstractButton b)
	{
		super (b);
	}	//	AdempiereButtonListener
	
	/**
	 * 	Install Keyboard Actions
	 *	@param c component
	 */
	public void installKeyboardActions (JComponent c)
	{
		super.installKeyboardActions (c);
		updateMnemonicBindingX ((AbstractButton)c);
	}	//	installKeyboardActions

	/**
	 * 	Property Change
	 *	@param e event
	 */
	public void propertyChange (PropertyChangeEvent e)
	{
		String prop = e.getPropertyName();
		if (prop.equals(AbstractButton.MNEMONIC_CHANGED_PROPERTY))
			updateMnemonicBindingX ((AbstractButton)e.getSource());
		else
			super.propertyChange (e);
	}	//	propertyChange
	
	/**
	 * 	Update Mnemonic Binding
	 *	@param b button
	 */
    void updateMnemonicBindingX (AbstractButton b) 
    {
    	int m = b.getMnemonic();
    	if (m != 0) 
    	{
    	    InputMap map = SwingUtilities.getUIInputMap(b, JComponent.WHEN_IN_FOCUSED_WINDOW);

    	    if (map == null) 
    	    {
    	    	map = new ComponentInputMapUIResource(b);
    	    	SwingUtilities.replaceUIInputMap(b, JComponent.WHEN_IN_FOCUSED_WINDOW, map);
    	    }
    	    map.clear();
    	    String className = b.getClass().getName();
    	    int mask = InputEvent.ALT_MASK;		//	Default Buttons
    	    if (b instanceof JCheckBox 			//	In Tab
    	    	|| className.indexOf("VButton") != -1)
    	    	mask = InputEvent.SHIFT_MASK + InputEvent.CTRL_MASK;
    	    map.put(KeyStroke.getKeyStroke(m, mask, false), "pressed");
    	    map.put(KeyStroke.getKeyStroke(m, mask, true), "released");
    	    map.put(KeyStroke.getKeyStroke(m, 0, true), "released");
    	}
    	else 
    	{
    		InputMap map = SwingUtilities.getUIInputMap(b, JComponent.WHEN_IN_FOCUSED_WINDOW);
    		if (map != null)
    			map.clear();
    	}
    }	//	updateMnemonicBindingX
    
}	//	AdempiereButtonListener
