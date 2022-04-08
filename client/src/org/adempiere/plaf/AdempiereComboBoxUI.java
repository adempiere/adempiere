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

import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxButton;

import com.jgoodies.looks.plastic.PlasticComboBoxUI;

/**
 *  Compiere ComboBox UI.
 *  The ComboBox is opaque - with opaque arrow button and textfield background
 *
 *  @author     Jorg Janke
 *  @version    $Id: CompiereComboBoxUI.java,v 1.10 2005/10/09 19:01:37 jjanke Exp $
 */
public class AdempiereComboBoxUI extends PlasticComboBoxUI
{
	/**
	 *  Create UI
	 *  @param c
	 *  @return new instance of CompiereComboBoxUI
	 */
	public static ComponentUI createUI(JComponent c)
	{
		return new AdempiereComboBoxUI();
	}   //  CreateUI

	/*************************************************************************/

	static int s_no = 0;
	/**
	 *  Install UI - Set ComboBox opaque.
	 *  Bug in Metal: arrowButton gets Mouse Events, so add the JComboBox
	 *  MouseListeners to the arrowButton
	 *  @see org.compiere.swing.CComboBox#addMouseListener(MouseListener)
	 *  @param c componrnt
	 */
	public void installUI (JComponent c)
	{
		MouseListener[] ml = c.getMouseListeners();
		super.installUI(c);
		//c.setOpaque(false);
		//
		for (int i = 0; i < ml.length; i++)
		{
		//	System.out.println("adding " + c.getClass().getName());
			arrowButton.addMouseListener(ml[i]);
		}

	}   //  installUI

	/*************************************************************************/

	/**
	 *  Create opaque button
	 *  @return opaque button
	 */
	protected JButton createArrowButton()
	{
		JButton button = super.createArrowButton();
		return button;
	}   //  createArrowButton

	public JButton getArrowButton()
	{
		return arrowButton;
	}

	/**
	 *  Set Icon  of arrow button
	 *  @param defaultIcon
	 */
	public void setIcon(Icon defaultIcon)
	{
		((MetalComboBoxButton)arrowButton).setComboIcon(defaultIcon);
	}   //  setIcon

	/*************************************************************************/

	/**
	 *  Create Popup
	 *  @return AdempiereComboPopup
	 */
	protected ComboPopup createPopup()
	{
		AdempiereComboPopup newPopup = new AdempiereComboPopup( comboBox );
		newPopup.getAccessibleContext().setAccessibleParent(comboBox);
		return newPopup;
	}   //  createPopup

}   //  AdempiereComboBoxUI
