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
package org.compiere.swing;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JTextField;
import javax.swing.text.Document;

import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.model.GridField;

/**
 *  Adempiere Text Field
 *
 *  @author     Jorg Janke
 *  @version    $Id: CTextField.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 * 
 * @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 */
public class CTextField extends JTextField 
	implements CEditor, KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 460529728891168659L;

	/**
	 * Constructs a new <code>TextField</code>.  A default model is created,
	 * the initial string is <code>null</code>,
	 * and the number of columns is set to 0.
	 */
	public CTextField()
	{
		super();
		init();
	}   //  CTextField

	/**
	 * Constructs a new <code>TextField</code> initialized with the
	 * specified text. A default model is created and the number of
	 * columns is 0.
	 *
	 * @param text the text to be displayed, or <code>null</code>
	 */
	public CTextField (String text)
	{
		super (text);
		init();
	}   //  CTextField

	/**
	 * Constructs a new empty <code>TextField</code> with the specified
	 * number of columns.
	 * A default model is created and the initial string is set to
	 * <code>null</code>.
	 *
	 * @param columns  the number of columns to use to calculate
	 *   the preferred width; if columns is set to zero, the
	 *   preferred width will be whatever naturally results from
	 *   the component implementation
	 */
	public CTextField (int columns)
	{
		super (columns);
		init();
	}   //  CTextField

	/**
	 * Constructs a new <code>TextField</code> initialized with the
	 * specified text and columns.  A default model is created.
	 *
	 * @param text the text to be displayed, or <code>null</code>
	 * @param columns  the number of columns to use to calculate
	 *   the preferred width; if columns is set to zero, the
	 *   preferred width will be whatever naturally results from
	 *   the component implementation
	 */
	public CTextField (String text, int columns)
	{
		super (text, columns);
		init();
	}   //  CTextField

	/**
	 * Constructs a new <code>JTextField</code> that uses the given text
	 * storage model and the given number of columns.
	 * This is the constructor through which the other constructors feed.
	 * If the document is <code>null</code>, a default model is created.
	 *
	 * @param doc  the text storage to use; if this is <code>null</code>,
	 *		a default will be provided by calling the
	 *		<code>createDefaultModel</code> method
	 * @param text  the initial string to display, or <code>null</code>
	 * @param columns  the number of columns to use to calculate
	 *   the preferred width >= 0; if <code>columns</code>
	 *   is set to zero, the preferred width will be whatever
	 *   naturally results from the component implementation
	 * @exception IllegalArgumentException if <code>columns</code> < 0
	 */
	public CTextField (Document doc, String text, int columns)
	{
		super (doc, text, columns);
		init();
	}   //  CTextField

	/**
	 *  Initialization
	 */
	private void init()
	{
		setBackground (false);
		set_oldValue();
	}   //  init

	/*************************************************************************/

	/** Mandatory (default false)   */
	private boolean m_mandatory = false;
	
	/** Old value - for future comparison */
	private Object m_oldValue;

	/**
	 *	Set Editor Mandatory
	 *  @param mandatory true, if you have to enter data
	 */
	public void setMandatory (boolean mandatory)
	{
		m_mandatory = mandatory;
		setBackground(false);
	}   //  setMandatory

	/**
	 *	Is Field mandatory
	 *  @return true, if mandatory
	 */
	public boolean isMandatory()
	{
		return m_mandatory;
	}   //  isMandatory

	/**
	 *	Enable Editor
	 *  @param rw true, if you can enter/select data
	 */
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
	public boolean isReadWrite()
	{
		return super.isEditable();
	}   //  isReadWrite


	/**
	 *  Set Background based on editable / mandatory / error
	 *  @param error if true, set background to error color, otherwise mandatory/editable
	 */
	public void setBackground (boolean error)
	{
		if (error)
			setBackground(AdempierePLAF.getFieldBackground_Error());
		else if (!isReadWrite())
			setBackground(AdempierePLAF.getFieldBackground_Inactive());
		else if (m_mandatory)
			setBackground(AdempierePLAF.getFieldBackground_Mandatory());
		else
			setBackground(AdempierePLAF.getFieldBackground_Normal());
	}   //  setBackground

	/**
	 *  Set Background
	 *  @param bg background
	 */
	public void setBackground (Color bg)
	{
		if (bg.equals(getBackground()))
			return;
		super.setBackground(bg);
	}   //  setBackground

	/**
	 *	Set Editor to value
	 *  @param value value of the editor
	 */
	public void setValue (Object value)
	{
		if (value == null)
			setText("");
		else
			setText(value.toString());
	}   //  setValue

	/**
	 *	Return Editor value
	 *  @return current value
	 */
	public Object getValue()
	{
		return getText();
	}   //  getValue

	/**
	 *  Return Display Value
	 *  @return displayed String value
	 */
	public String getDisplay()
	{
		return getText();
	}   //  getDisplay


	/**
	 * 	key Pressed
	 *	@see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 *	@param e
	 */
	public void keyPressed(KeyEvent e)
	{
	}	//	keyPressed

	/**
	 * 	key Released
	 *	@see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 *	@param e
	 */
	public void keyReleased(KeyEvent e)
	{
	}	//	keyReleased

	/**
	 * 	key Typed
	 *	@see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 *	@param e
	 */
	public void keyTyped(KeyEvent e)
	{
	}	//	keyTyped

	/**
	 * Set the old value of the field.  For use in future comparisons.
	 * The old value must be explicitly set though this call.
	 * @param m_oldValue
	 */
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
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

}   //  CTextField
