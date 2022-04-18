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

package org.compiere.grid.ed;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import org.compiere.apps.RecordInfo;
import org.compiere.model.GridField;
import org.compiere.swing.CRadioButton;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Radio Button Control - selects one of a number of options. Created to enable
 *  variant selection on the BOM Drop form.  Copied from VCheckbox.
 *
 *  @author Michael McKay, mckayERP@gmail.com
 *  
 */
public class VRadioButton extends CRadioButton
	implements VEditor, ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9199643773556184995L;

	/******************************************************************************
	 *	Mouse Listener
	 */
	final class VRadioButton_mouseAdapter extends MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee adaptee
		 */
		VRadioButton_mouseAdapter(VRadioButton adaptee)
		{
			m_adaptee = adaptee;
		}	//	mouseAdapter

		private VRadioButton m_adaptee;

		/**
		 *	Mouse Listener
		 *  @param e event
		 */
		public void mouseClicked(MouseEvent e)
		{
			//	popup menu
			if (SwingUtilities.isRightMouseButton(e))
				m_adaptee.popupMenu.show((Component)e.getSource(), e.getX(), e.getY());
		}	//	mouseClicked

	}
	
	/**
	 *	Default Constructor
	 */
	public VRadioButton()
	{
		this("", false, false, true, "", null, false);
	}	//	VCheckBox

	/**
	 *	Standard Constructor
	 *  @param columnName
	 *  @param mandatory
	 *  @param isReadOnly
	 *  @param isUpdateable
	 *  @param title
	 *  @param description
	 *  @param tableEditor
	 */
	public VRadioButton(String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		String title, String description, boolean tableEditor)
	{
		super();
		super.setName(columnName);
		this.columnName = columnName;
		setMandatory(mandatory);
		//
		if (isReadOnly || !isUpdateable)
			setEditable(false);
		else
			setEditable(true);

		//  Normal
		if (!tableEditor)
		{
			setText(title);
			if (description != null && description.length() > 0)
				setToolTipText(description);
		}
		else
		{
			setHorizontalAlignment(JLabel.CENTER);
		}
		//
		this.addActionListener(this);
		addMouseListener(new VRadioButton_mouseAdapter(this));
	}	//	VCheckBox

	/** Mnemonic saved			*/
	private char	savedMnemonic = 0;

	/**
	 *  Dispose
	 */
	public void dispose()
	{
	}   //  dispose

	private String		columnName;
	private GridField 	field;
	//	Popup
	JPopupMenu 			popupMenu = new JPopupMenu();
	private Object 		oldValue;

	/**
	 *	Set Editable
	 *  @param value
	 */
	public void setEditable (boolean value)
	{
		super.setReadWrite(value);
	}	//	setEditable

	/**
	 *	IsEditable
	 *  @return true if editable
	 */
	public boolean isEditable()
	{
		return super.isReadWrite();
	}	//	isEditable

	/**
	 *	Set Editor to value
	 *  @param value
	 */
	public void setValue (Object value)
	{
		boolean sel = false;
		if (value != null)
		{
			if (value instanceof Boolean)
				sel = ((Boolean)value).booleanValue();
			else
				sel = "Y".equals(value);
		}
		setSelected(sel);
	}	//	setValue

	/**
	 *  Property Change Listener
	 *  @param evt
	 */
	public void propertyChange (PropertyChangeEvent evt)
	{
		if (evt.getPropertyName().equals(org.compiere.model.GridField.PROPERTY))
			setValue(evt.getNewValue());
	}   //  propertyChange

	/**
	 *	Return Editor value
	 *  @return value
	 */
	public Object getValue()
	{
		return Boolean.valueOf(isSelected());
	}	//	getValue

	/**
	 *  Return Display Value
	 *  @return value
	 */
	public String getDisplay()
	{
		String value = isSelected() ? "Y" : "N";
		return Msg.translate(Env.getCtx(), value);
	}   //  getDisplay

	/**
	 *	Set Background (nop)
	 */
	public void setBackground()
	{
	}	//	setBackground

	/**
	 *	Action Listener	- data binding
	 *  @param e
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(RecordInfo.CHANGE_LOG_COMMAND))
		{
			RecordInfo.start(field);
			return;
		}
		//	ADebug.info("VCheckBox.actionPerformed");
		try
		{
			fireVetoableChange(columnName, null, getValue());
		}
		catch (PropertyVetoException pve)
		{
		}
	}	//	actionPerformed

	/**
	 *  Set Field/WindowNo for ValuePreference (NOP)
	 *  @param mField
	 */
	public void setField (org.compiere.model.GridField mField)
	{
		field = mField;
		if (field != null)
			RecordInfo.addMenu(this, popupMenu);
	}   //  setField

	@Override
	public GridField getField() {
		return field;
	}
	
	/**
	 * @return Returns the savedMnemonic.
	 */
	public char getSavedMnemonic ()
	{
		return savedMnemonic;
	}	//	getSavedMnemonic
	
	/**
	 * @param savedMnemonic The savedMnemonic to set.
	 */
	public void setSavedMnemonic (char savedMnemonic)
	{
		this.savedMnemonic = savedMnemonic;
	}	//	getSavedMnemonic
	/**
	 * Set the old value of the field.  For use in future comparisons.
	 * The old value must be explicitly set though this call.
	 * @param oldValue
	 */
	public void set_oldValue() {
		this.oldValue = getValue();
	}
	/**
	 * Get the old value of the field explicitly set in the past
	 * @return
	 */
	public Object get_oldValue() {
		return oldValue;
	}
	/**
	 * Has the field changed over time?
	 * @return true if the old value is different than the current.
	 */
	public boolean hasChanged() {
		// Both or either could be null
		if(getValue() != null)
			if(oldValue != null)
				return !oldValue.equals(getValue());
			else
				return true;
		else  // getValue() is null
			if(oldValue != null)
				return true;
			else
				return false;
	}

}	//	VCheckBox
