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
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;

import sun.swing.*;

/**
 * 	Adempiere Label UI
 *	
 *  @author Jorg Janke
 *  @version $Id: AdempiereLabelUI.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 */
public class CompiereLabelUI extends MetalLabelUI
{
	/** Singleton				*/
    protected static CompiereLabelUI adempiereLabelUI = new CompiereLabelUI();

    /**
     * 	Create UI
     *	@param c component
     *	@return singleton
     */
    public static CompiereLabelUI createUI(JComponent c) 
    {
    	return adempiereLabelUI;
    }	//	createUI
	
	/**
	 * 	Install Keyboard Actions
	 *	@param l label
	 */
	protected void installKeyboardActions (JLabel l)
	{
	//	super.installKeyboardActions(l);
        int dka = l.getDisplayedMnemonic();
		if (dka != 0)
		{
			Component lf = l.getLabelFor();
			if (lf != null)
			{
				ActionMap actionMap = l.getActionMap();
				actionMap.put(PRESS, ACTION_PRESS);
				InputMap inputMap = SwingUtilities.getUIInputMap (l, JComponent.WHEN_IN_FOCUSED_WINDOW);
				if (inputMap == null)
				{
					inputMap = new ComponentInputMapUIResource (l);
					SwingUtilities.replaceUIInputMap (l, JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
				}
				inputMap.clear ();
				inputMap.put (KeyStroke.getKeyStroke (dka, 
					ActionEvent.SHIFT_MASK+ActionEvent.CTRL_MASK, false), PRESS);
			}
		}
	}	//	installKeyboardActions
	
	
	/**	Action Name					*/
	private static final String PRESS   = "press";
	/** Press Action				*/
	private static PressAction	ACTION_PRESS = new PressAction();
	
	/**
	 * 	Adempiere Label UI Actions
	 */
    private static class PressAction extends UIAction
	{

		PressAction ()
		{
			super (PRESS);
		}

		public void actionPerformed (ActionEvent e)
		{
			JLabel label = (JLabel)e.getSource ();
			String key = getName ();
			if (key == PRESS)
			{
				doPress (label);
			}
		}	//	actionPerformed

		/**
		 * 	Do Press - Focus the Field
		 *	@param label label
		 */
		private void doPress (JLabel label)
		{
			Component labelFor = label.getLabelFor ();
			if (labelFor != null && labelFor.isEnabled ())
			{
				Component owner = label.getLabelFor ();
				if (owner instanceof Container
					&& ((Container)owner).isFocusCycleRoot ())
				{
					owner.requestFocus ();
				}
				else
				{
				 	if (owner instanceof Container) 
				 	{
				 	    Container container = (Container)owner;
				 	    if (container.isFocusCycleRoot()) 
				 	    {
				 	    	FocusTraversalPolicy policy = container.getFocusTraversalPolicy();
				 	    	Component comp = policy.getDefaultComponent(container);
				 	    	if (comp != null) 
				 	    	{
				 	    		comp.requestFocus();
				 	    		return;
				 	    	}
				 	    }
				 	    Container rootAncestor = container.getFocusCycleRootAncestor();
				 	    if (rootAncestor != null) 
				 	    {
				 	    	FocusTraversalPolicy policy = rootAncestor.getFocusTraversalPolicy();
				 	    	Component comp = policy.getComponentAfter(rootAncestor, container);
				 	    	if (comp != null && SwingUtilities.isDescendingFrom(comp, container)) 
				 	    	{
				 	    		comp.requestFocus();
				 	    		return;
				 	    	}
				 	    }
				 	}
			        if (owner.isFocusable()) 
			        {
				 	    owner.requestFocus();
				 	    return;
			        }
			        //	No Forcus
				}
			}
		}	//	doPress
	}	//	PressAction

}	//	AdempiereLabelUI
