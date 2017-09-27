/********************************************************************* 
* This file is part of Adempiere ERP Bazaar                          * 
* http://www.adempiere.org                                           * 
*                                                                    * 
* Copyright (C) 1999 - 2006 Compiere Inc.                            * 
* Copyright (C) Contributors                                         * 
*                                                                    * 
* This program is free software; you can redistribute it and/or      * 
* modify it under the terms of the GNU General Public License        * 
* as published by the Free Software Foundation; either version 2     * 
* of the License, or (at your option) any later version.             * 
*                                                                    * 
* This program is distributed in the hope that it will be useful,    * 
* but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
* GNU General Public License for more details.                       * 
*                                                                    * 
* You should have received a copy of the GNU General Public License  * 
* along with this program; if not, write to the Free Software        * 
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
* MA 02110-1301, USA.                                                * 
*                                                                    * 
* Contributors:                                                      * 
*  - Bahman Movaqar (bmovaqar@users.sf.net)                          * 
**********************************************************************/ 
package org.compiere.swing;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ComponentInputMapUIResource;

import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.model.GridField;
import org.compiere.plaf.CompiereColor;
import org.compiere.plaf.CompiereLookAndFeel;

/**
 * Adempiere Button supporting colored Background
 * 
 * @author Jorg Janke
 * @version $Id: CButton.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 */
public class CButton extends JButton implements CEditor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 70278798402299341L;

	/**
	 * Creates a button with no set text or icon.
	 */
	public CButton() {
		this(null, null);
	} // CButton

	/**
	 * Creates a button with an icon.
	 * 
	 * @param icon
	 *            the Icon image to display on the button
	 */
	public CButton(Icon icon) {
		this(null, icon);
	} // CButton

	/**
	 * Creates a button with text.
	 * 
	 * @param text
	 *            the text of the button
	 */
	public CButton(String text) {
		this(text, null);
	} // CButton

	/**
	 * Creates a button where properties are taken from the <code>Action</code>
	 * supplied.
	 * 
	 * @param a
	 *            the <code>Action</code> used to specify the new button
	 * 
	 * @since 1.3
	 */
	public CButton(Action a) {
		super(a);
	} // CButton

	/**
	 * Creates a button with initial text and an icon.
	 * 
	 * @param text
	 *            the text of the button
	 * @param icon
	 *            the Icon image to display on the button
	 */
	public CButton(String text, Icon icon) {
		super(text, icon);
	} // CButton

	/***************************************************************************
	 * Set Background - Differentiates between system & user call. If User Call,
	 * sets Opaque & ContextAreaFilled to true
	 * 
	 * @param bg
	 *            background color
	 */
	public void setBackground(Color bg) {
		if (bg.equals(getBackground()))
			return;
		super.setBackground(bg);
		setBackgroundColor(new CompiereColor(bg));
		this.repaint();
	} // setBackground

	/**
	 * Set Background - NOP
	 * 
	 * @param error
	 *            error
	 */
	public void setBackground(boolean error) {
	} // setBackground

	/**
	 * Set Standard Background
	 */
	public void setBackgroundColor() {
		setBackgroundColor(null);
	} // setBackground

	/**
	 * Set Background
	 * 
	 * @param bg
	 *            AdempiereColor for Background, if null set standard background
	 */
	public void setBackgroundColor(CompiereColor bg) {
		if (bg == null)
			bg = new CompiereColor(AdempierePLAF.getFormBackground());
		putClientProperty(CompiereLookAndFeel.BACKGROUND, bg);
		super.setBackground(bg.getFlatColor());
		this.repaint();
	} // setBackground

	/**
	 * Get Background
	 * 
	 * @return Color for Background
	 */
	public CompiereColor getBackgroundColor() {
		try {
			return (CompiereColor) getClientProperty(CompiereLookAndFeel.BACKGROUND);
		} catch (Exception e) {
			System.err.println("CButton - ClientProperty: " + e.getMessage());
		}
		return null;
	} // getBackgroundColor

	/** Mandatory (default false) */
	private boolean m_mandatory = false;

	/** Read-Write */
	private boolean m_readWrite = true;

	/**
	 * Set Editor Mandatory
	 * 
	 * @param mandatory
	 *            true, if you have to enter data
	 */
	public void setMandatory(boolean mandatory) {
		m_mandatory = mandatory;
		setBackground(false);
	} // setMandatory

	/**
	 * Is Field mandatory
	 * 
	 * @return true, if mandatory
	 */
	public boolean isMandatory() {
		return m_mandatory;
	} // isMandatory

	/**
	 * Enable Editor
	 * 
	 * @param rw
	 *            true, if you can enter/select data
	 */
	public void setReadWrite(boolean rw) {
		if (isEnabled() != rw)
			setEnabled(rw);
		m_readWrite = rw;
	} // setReadWrite

	/**
	 * Is it possible to edit
	 * 
	 * @return true, if editable
	 */
	public boolean isReadWrite() {
		return m_readWrite;
	} // isReadWrite

	/**
	 * Set Editor to value
	 * 
	 * @param value
	 *            value of the editor
	 */
	public void setValue(Object value) {
		if (value == null)
			setText("");
		else
			setText(value.toString());
	} // setValue

	/**
	 * Return Editor value
	 * 
	 * @return current value
	 */
	public Object getValue() {
		return getText();
	} // getValue

	/**
	 * Return Display Value
	 * 
	 * @return displayed String value
	 */
	public String getDisplay() {
		return getText();
	} // getDisplay

	/**
	 * Set Text & Mnemonic
	 * 
	 * @param text
	 *            text
	 */
	public void setText(String text) {
		if (text == null) {
			super.setText(text);
			return;
		}
		int pos = text.indexOf('&');
		if (pos != -1) // We have a nemonic - creates ALT-_
		{
			int mnemonic = text.toUpperCase().charAt(pos + 1);
			if (mnemonic != ' ') {
				setMnemonic(mnemonic);
				text = text.substring(0, pos) + text.substring(pos + 1);
			}
		}
		super.setText(text);
		if (getName() == null)
			setName(text);
	} // setText

	/**
	 * Set Tool Tip Text & Mnemonic
	 * 
	 * @param text
	 *            text
	 */
	public void setToolTipText(String text) {
		if (text == null) {
			//super.setText(text); Angelo Dabala' (genied) fixed, was calling the wrong method
			super.setToolTipText(text);
			return;
		}
		int pos = text.indexOf('&');
		if (pos != -1) // We have a nemonic - creates ALT-_
		{
			int mnemonic = text.toUpperCase().charAt(pos + 1);
			if (mnemonic != ' ') {
				setMnemonic(mnemonic);
				text = text.substring(0, pos) + text.substring(pos + 1);
			}
		}
		super.setToolTipText(text);
		if (getName() == null)
			setName(text);
	} // setToolTipText

	/**
	 * Set Action Command
	 * 
	 * @param actionCommand
	 *            command
	 */
	public void setActionCommand(String actionCommand) {
		super.setActionCommand(actionCommand);
		if (getName() == null && actionCommand != null
				&& actionCommand.length() > 0)
			setName(actionCommand);
	} // setActionCommand

	/**
	 * Overrides the JButton.setMnemonic() method, setting modifier keys to
	 * CTRL+ALT.
	 * 
	 * @param mnemonic
	 *            The mnemonic character code.
	 */
	public void setMnemonic(int mnemonic) {
		super.setMnemonic(mnemonic);

		// Angelo Dabala' (genied) avoid to register Ctrl+Alt modifier mask without mnemonic
		if (mnemonic==KeyEvent.VK_UNDEFINED) {
			return;
		}
		
		InputMap map = SwingUtilities.getUIInputMap(this,
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		if (map == null) {
			map = new ComponentInputMapUIResource(this);
			SwingUtilities.replaceUIInputMap(this,
					JComponent.WHEN_IN_FOCUSED_WINDOW, map);
		}
		map.clear();
		int mask = InputEvent.ALT_MASK + InputEvent.CTRL_MASK; // Default
																// Buttons
		map.put(KeyStroke.getKeyStroke(mnemonic, mask, false), "pressed");
		map.put(KeyStroke.getKeyStroke(mnemonic, mask, true), "released");
		map.put(KeyStroke.getKeyStroke(mnemonic, 0, true), "released");
		setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, map);
	} // setMnemonic

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// Not used
		
	}

	@Override
	public void addValueChangeListener(ValueChangeListener listener) {
		// Not used
		
	}

	@Override
	public GridField getField() {
		// TODO Auto-generated method stub
		return null;
	}
} // CButton
