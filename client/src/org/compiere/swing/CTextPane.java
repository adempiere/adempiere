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
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.model.GridField;

/**
 * A text pane using a styled document.  Intended as a
 * replacement for CTextField where text formats are required
 * 
 * Note the previous version of this class was renamed to 
 * CScrollingTextPane.
 * 
 * @author Michael McKay, mckayERP@gmail.com
 *
 * @version 3.9.4
 * 
 */
public class CTextPane extends JTextPane implements CEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4847023846617728899L;

	/*************************************************************************/

	/** Mandatory (default false)   */
	private boolean mandatory = false;
	
	/** Old value - for future comparison */
	private Object m_oldValue;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// Not implemented

	}

	/**
	 *	Enable Editor
	 *  @param rw true, if you can enter/select data
	 */
	@Override
	public void setReadWrite (boolean rw)
	{
		if (super.isEditable() != rw)
			super.setEditable (rw);
		setBackground(false);
	}   //  setEditable

	/**
	 *	Is it possible to edit
	 *  @return true, if editable
	 */
	@Override
	public boolean isReadWrite()
	{
		return super.isEditable();
	}   //  isReadWrite


	/**
	 *	Set Editor Mandatory
	 *  @param mandatory true, if you have to enter data
	 */
	@Override
	public void setMandatory (boolean mandatory)
	{
		this.mandatory = mandatory;
		setBackground(false);
	}   //  setMandatory


	@Override
	public boolean isMandatory() {
		return mandatory;
	}

	/**
	 *  Set Background based on editable / mandatory / error
	 *  @param error if true, set background to error color, otherwise mandatory/editable
	 */
	@Override
	public void setBackground (boolean error)
	{
		if (error)
			setBackground(AdempierePLAF.getFieldBackground_Error());
		else if (!isReadWrite())
			setBackground(AdempierePLAF.getFieldBackground_Inactive());
		else if (mandatory)
			setBackground(AdempierePLAF.getFieldBackground_Mandatory());
		else
			setBackground(AdempierePLAF.getFieldBackground_Normal());
	}   //  setBackground

	/**
	 * 	Set Background
	 *	@param color color
	 */
	@Override
	public void setBackground (Color color)
	{
		if (color.equals(getBackground()))
			return;
		super.setBackground(color);
	}	//	setBackground

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}

	@Override
	public void setValue(Object value) {
		if (value == null)
			setText("");
		else
			setText(value.toString());
	}

	@Override
	public Object getValue() {
		return getText();
	}

	@Override
	public String getDisplay() {
		return getText();
	}

	/**
	 * Set the old value of the field.  For use in future comparisons.
	 * The old value must be explicitly set though this call.
	 * @param m_oldValue
	 */
	@Override
	public void set_oldValue() {
		this.m_oldValue = getValue();
	}

	/**
	 * Get the old value of the field explicitly set in the past
	 * @return
	 */
	public Object get_oldValue() {
		return m_oldValue;
	}
	
	/**
	 * Has the field changed over time?
	 * @return true if the old value is different than the current.
	 */
	@Override
	public boolean hasChanged() {
		// Both or either could be null
		if(getValue() != null)
			if(m_oldValue != null)
				return !m_oldValue.equals(getValue());
			else
				return true;
		else  // getValue() is null
			if(m_oldValue != null)
				return true;
			else
				return false;
	}

	/**
	 *  A hack to make the processKeyBinding method of the JComponent visible publicly.
	 *  
	 * @param ks
	 * @param e
	 * @param pressed
	 * @return
	 */
	// TODO - @Override?
    public boolean processKeyBinding(KeyStroke ks, KeyEvent e, boolean pressed) {
    	return super.processKeyBinding(ks, e, WHEN_FOCUSED, pressed);
    }
    
	@Override
	public void addValueChangeListener(ValueChangeListener listener) {
		// TODO Auto-generated method stub
	}

	@Override
	public GridField getField() {
		// TODO Auto-generated method stub
		return null;
	}

}
