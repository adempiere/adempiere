/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.compiere.swing;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ComponentInputMapUIResource;

import org.adempiere.exceptions.ValueChangeListener;
import org.compiere.model.GridField;

/**
 * Adempiere Radio Button
 * 
 * @author Michael McKay, mckayERP@gmail.com
 */
public class CRadioButton extends JRadioButton implements CEditor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6115543971487470944L;

	/**
	 * Creates a radio button button with no text, no icon.
	 */
	public CRadioButton() {
		super();
		init();
	}

	/**
	 * Creates a radio button with an icon.
	 * 
	 * @param icon
	 *            the Icon image to display
	 */
	public CRadioButton(Icon icon) {
		super(icon);
		init();
	}

	/**
	 * Creates a radio button with an icon and specifies whether or not it is
	 * initially selected.
	 * 
	 * @param icon
	 *            the Icon image to display
	 * @param selected
	 *            a boolean value indicating the initial selection state. If
	 *            <code>true</code> the radio button is selected
	 */
	public CRadioButton(Icon icon, boolean selected) {
		super(icon, selected);
		init();
	}

	/**
	 * Creates a radio button with text.
	 * 
	 * @param text
	 *            the text of the radio button.
	 */
	public CRadioButton(String text) {
		super(text);
		init();
	}

	/**
	 * Creates a radio button where properties are taken from the Action supplied.
	 * 
	 * @param a
	 */
	public CRadioButton(Action a) {
		super(a);
		init();
	}

	/**
	 * Creates a radio button with text and specifies whether or not it is
	 * initially selected.
	 * 
	 * @param text
	 *            the text of the radio button.
	 * @param selected
	 *            a boolean value indicating the initial selection state. If
	 *            <code>true</code> the radio button is selected
	 */
	public CRadioButton(String text, boolean selected) {
		super(text, selected);
		init();
	}

	/**
	 * Creates a radio button with the specified text and
	 * icon.
	 * 
	 * @param text
	 *            the text of the radio button.
	 * @param icon
	 *            the Icon image to display
	 */
	public CRadioButton(String text, Icon icon) {
		super(text, icon, false);
		init();
	}

	/**
	 * Creates a radio button with text and icon, and specifies whether or not it
	 * is initially selected.
	 * 
	 * @param text
	 *            the text of the radio button.
	 * @param icon
	 *            the Icon image to display
	 * @param selected
	 *            a boolean value indicating the initial selection state. If
	 *            <code>true</code> the radio button is selected
	 */
	public CRadioButton(String text, Icon icon, boolean selected) {
		super(text, icon, selected);
		init();
	}

	/**
	 * Common Init
	 */
	private void init() {
		// Default to transparent, works better under windows look and feel
		setOpaque(false);
	} // init

	/** ********************************************************************** */

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
		if (super.isEnabled() != rw)
			super.setEnabled(rw);
		setBackground(false);
		m_readWrite = rw;
	} // setEditable

	/**
	 * Is it possible to edit
	 * 
	 * @return true, if editable
	 */
	public boolean isReadWrite() {
		return m_readWrite;
	} // isEditable

	/**
	 * Set Background based on editable/mandatory/error - ignored -
	 * 
	 * @param error
	 *            if true, set background to error color, otherwise
	 *            mandatory/editable
	 */
	public void setBackground(boolean error) {
	} // setBackground

	/**
	 * Set Background
	 * 
	 * @param bg
	 */
	public void setBackground(Color bg) {
		if (bg.equals(getBackground()))
			return;
		super.setBackground(bg);
	} // setBackground

	/** Retain value */
	private Object m_value = null;

	/**
	 * Set Editor to value. Interpret Y/N and Boolean
	 * 
	 * @param value
	 *            value of the editor
	 */
	public void setValue(Object value) {
		m_value = value;
		boolean sel = false;
		if (value == null)
			sel = false;
		else if (value.toString().equals("Y"))
			sel = true;
		else if (value.toString().equals("N"))
			sel = false;
		else if (value instanceof Boolean)
			sel = ((Boolean) value).booleanValue();
		else {
			try {
				sel = Boolean.getBoolean(value.toString());
			} catch (Exception e) {
			}
		}
		this.setSelected(sel);
	} // setValue

	/**
	 * Return Editor value
	 * 
	 * @return current value as String or Boolean
	 */
	public Object getValue() {
		if (m_value instanceof String)
			return super.isSelected() ? "Y" : "N";
		return Boolean.valueOf(isSelected());
	} // getValue

	/**
	 * Return Display Value
	 * 
	 * @return displayed String value
	 */
	public String getDisplay() {
		if (m_value instanceof String)
			return super.isSelected() ? "Y" : "N";
		return Boolean.toString(super.isSelected());
	} // getDisplay

	/**
	 * Set Text
	 * 
	 * @param mnemonicLabel
	 *            text
	 */
	public void setText(String mnemonicLabel) {
		super.setText(createMnemonic(mnemonicLabel));
	} // setText

	/**
	 * Create Mnemonics of text containing "&". Based on MS notation of &Help =>
	 * H is Mnemonics Creates ALT_
	 * 
	 * @param text
	 *            test with Mnemonics
	 * @return text w/o &
	 */
	private String createMnemonic(String text) {
		if (text == null)
			return text;
		int pos = text.indexOf('&');
		if (pos != -1) // We have a nemonic
		{
			char ch = text.charAt(pos + 1);
			if (ch != ' ') // &_ - is the & character
			{
				setMnemonic(ch);
				return text.substring(0, pos) + text.substring(pos + 1);
			}
		}
		return text;
	} // createMnemonic

	/**
	 * Overrides the JCheckBox.setMnemonic() method, setting modifier keys to
	 * CTRL+SHIFT.
	 * 
	 * @param mnemonic
	 *            The mnemonic character code.
	 */
	public void setMnemonic(int mnemonic) {
		super.setMnemonic(mnemonic);

		InputMap map = SwingUtilities.getUIInputMap(this,
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		if (map == null) {
			map = new ComponentInputMapUIResource(this);
			SwingUtilities.replaceUIInputMap(this,
					JComponent.WHEN_IN_FOCUSED_WINDOW, map);
		}
		map.clear();
		String className = this.getClass().getName();
		int mask = InputEvent.ALT_MASK; // Default Buttons
		if (this instanceof JRadioButton // In Tab
				|| className.indexOf("VButton") != -1)
			mask = InputEvent.SHIFT_MASK + InputEvent.CTRL_MASK;
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

} // CCheckBox
