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

import java.awt.Insets;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.UIManager;
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
	private boolean isTableCellEditor = false;
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
		
		setBorders();

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

    /**
     * {@inheritDoc}
     * Overridden to allow menu selection in a table editor
     */
    protected void selectNextPossibleValue() {
        int si;

        if ( comboBox.isPopupVisible() ) {
            si = listBox.getSelectedIndex();
        }
        else {
            si = comboBox.getSelectedIndex();
        }

        if ( si < comboBox.getModel().getSize() - 1 ) {
            listBox.setSelectedIndex( si + 1 );
            listBox.ensureIndexIsVisible( si + 1 );
//            if ( !isTableCellEditor ) {
                if (!(UIManager.getBoolean("ComboBox.noActionOnKeyNavigation") && comboBox.isPopupVisible())) {
                    comboBox.setSelectedIndex(si+1);
                }
//            }
            comboBox.repaint();
        }
    }

    /**
     * {@inheritDoc}
     * Overridden to allow menu selection in a table editor
     */
    protected void selectPreviousPossibleValue() {
        int si;

        if ( comboBox.isPopupVisible() ) {
            si = listBox.getSelectedIndex();
        }
        else {
            si = comboBox.getSelectedIndex();
        }

        if ( si > 0 ) {
            listBox.setSelectedIndex( si - 1 );
            listBox.ensureIndexIsVisible( si - 1 );
//            if ( !isTableCellEditor ) {
                if (!(UIManager.getBoolean("ComboBox.noActionOnKeyNavigation") && comboBox.isPopupVisible())) {
                    comboBox.setSelectedIndex(si-1);
                }
//            }
            comboBox.repaint();
        }
    }

	public void setTableCellEditor(boolean isTableCellEditor) {
		
		if (this.isTableCellEditor != isTableCellEditor)
		{
			this.isTableCellEditor = isTableCellEditor;
			setBorders();
			isMinimumSizeDirty = true;
			// Display size should be OK
		}
		
	}

	public void setBorders() {
				
		//  Determine if the comboBox is active or not
		boolean comboActive = comboBox.isVisible();
		if (!comboActive || !isTableCellEditor)
		{
			return;
		}

		// Visible and a table cell editor
		// Remove the comboBoxEditor border entirely
		ComboBoxEditor comboBoxEditor = comboBox.getEditor();
		if (comboBoxEditor != null && comboBoxEditor.getEditorComponent() instanceof JComponent)
		{
			((JComponent) comboBoxEditor.getEditorComponent()).setBorder(BorderFactory.createEmptyBorder());
		}

		// For the arrow button, we only want the border on the inside edge.
		// If the button is not null
		if (arrowButton != null)
		{
			// The outer border removes the edges of the arrowButtonBorder
			arrowButton.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createEmptyBorder(-2, 0, -2, -2),
					BorderFactory.createCompoundBorder(
							UIManager.getBorder("ComboBox.arrowButtonBorder"),
							BorderFactory.createEmptyBorder(2, 3, 2, 3))));
			arrowButton.setMargin(new Insets(0, 2, 0, 2));
		}			

	}
	
}   //  AdempiereComboBoxUI
