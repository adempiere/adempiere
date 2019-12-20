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
package org.compiere.grid.ed;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;

import javax.faces.event.FacesListener;
import javax.swing.ComboBoxModel;
import javax.swing.JComponent;

import org.adempiere.plaf.AdempiereComboBoxUI;
import org.compiere.model.GridField;
import org.compiere.model.MLocator;
import org.compiere.model.MLookup;
import org.compiere.swing.CComboBox;
import org.compiere.util.CLogger;
import org.compiere.util.KeyNamePair;
import org.compiere.util.NamePair;
import org.compiere.util.ValueNamePair;

/**
 *  Combobox with KeyNamePair/ValueNamePair or Locator.
 *  <p>
 *  It has the same height as a TextField
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VComboBox.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 
 *  @author 	Michael McKay
 *  				<li>release/380 add changes to record and compare values similar to
 *  					ADEMPIERE-72
 */
public class VComboBox extends CComboBox implements VEditor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7632613004262943867L;

	/** The old Value - for comparison at future points in time.	*/
	private Object				m_oldValue;

	private boolean haveFocus;

	private boolean isTableCellEditor;

	/**
	 *  Constructor
	 */
	public VComboBox()
	{
		super();
//		common_init();
	}
	public VComboBox(Object[] items)
	{
		super(items);
//		common_init();
	}
	public VComboBox(ComboBoxModel model)
	{
		super(model);
//		common_init();
	}	//	VComboBox

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VComboBox.class);
	
	/**
	 *  Common Setup
	 *
	private void common_init()
	{
		LookAndFeel.installColorsAndFont(this, "TextField.background", "TextField.foreground", "TextField.font");
		setForeground(AdempierePLAF.getTextColor_Normal());
		setBackground(AdempierePLAF.getFieldBackground_Normal());
		setPreferredSize(s_text.getPreferredSize());
	//	this.setKeySelectionManager(new ComboSelectionManager());
	}   //  common_init

	/** Reference Field         *
	private static  JTextField  s_text = new JTextField(VTextField.DISPLAY_SIZE);


	/**
	 *  Set Selected item to key if exists
	 *  @param key
	 */
	public void setValue (int key)
	{
		setValue(String.valueOf(key));
	}   //  setValue


	/**
	 *  Get Display
	 *  @return displayed string
	 */
	public String getDisplay()
	{
		if (getSelectedItem() == null)
			return "";
		//
		if (getSelectedItem() instanceof String)
			return (String) getSelectedItem();
		
		NamePair p = (NamePair)getSelectedItem();
		if (p == null)
			return "";
		return p.getName();
	}   //  getDisplay

	@Override
	protected boolean isMatchingFilter(Object element) 
	{
		if (element instanceof NamePair)
			element = ((NamePair)element).getName();
		return super.isMatchingFilter(element);
	}	

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
	public void dispose() {
		
	}
	
	@Override
	public JComponent getComponent() {
		return this;
	}
	
	@Override
	public void setTableCellEditor(boolean isTableCellEditor) {
		
		this.isTableCellEditor = isTableCellEditor;
		((AdempiereComboBoxUI) this.getUI()).setTableCellEditor(isTableCellEditor);
		
	}
	
	@Override
	public boolean isTableCellEditor() {
		return isTableCellEditor;
	}
	
}	//	VComboBox
