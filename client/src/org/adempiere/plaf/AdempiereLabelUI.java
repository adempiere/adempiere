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

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.event.ActionEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ComponentInputMapUIResource;
import javax.swing.plaf.metal.MetalLabelUI;

import sun.swing.UIAction;

/**
 * 	Adempiere Label UI
 *	
 *  @author Jorg Janke
 *  @version $Id: CompiereLabelUI.java,v 1.2 2005/12/05 02:38:28 jjanke Exp $
 */
public class AdempiereLabelUI extends MetalLabelUI
{
	/** Singleton				*/
    protected static AdempiereLabelUI adempiereLabelUI = new AdempiereLabelUI();

    /**
     * 	Create UI
     *	@param c component
     *	@return singleton
     */
    public static AdempiereLabelUI createUI(JComponent c) 
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
	 * 	Compiere Label UI Actions
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
			if (key.equals(PRESS))
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
