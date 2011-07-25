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

import org.compiere.apps.FieldRecordInfo;
import org.compiere.model.GridField;
import org.compiere.swing.CCheckBox;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Checkbox Control
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VCheckBox.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class VCheckBox extends CCheckBox
	implements VEditor, ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9199643773556184995L;

	/******************************************************************************
	 *	Mouse Listener
	 */
	final class VCheckBox_mouseAdapter extends MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee adaptee
		 */
		VCheckBox_mouseAdapter(VCheckBox adaptee)
		{
			m_adaptee = adaptee;
		}	//	VNumber_mouseAdapter

		private VCheckBox m_adaptee;

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
	public VCheckBox()
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
	public VCheckBox(String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		String title, String description, boolean tableEditor)
	{
		super();
		super.setName(columnName);
		m_columnName = columnName;
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
		addMouseListener(new VCheckBox_mouseAdapter(this));
	}	//	VCheckBox

	/** Mnemonic saved			*/
	private char	m_savedMnemonic = 0;

	/**
	 *  Dispose
	 */
	public void dispose()
	{
	}   //  dispose

	private String		m_columnName;
	private GridField m_mField;
	//	Popup
	JPopupMenu 				popupMenu = new JPopupMenu();

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
		return new Boolean (isSelected());
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
		if (e.getActionCommand().equals(FieldRecordInfo.CHANGE_LOG_COMMAND))
		{
			FieldRecordInfo.start(m_mField);
			return;
		}
		//	ADebug.info("VCheckBox.actionPerformed");
		try
		{
			fireVetoableChange(m_columnName, null, getValue());
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
		m_mField = mField;
		if (m_mField != null)
			FieldRecordInfo.addMenu(this, popupMenu);
	}   //  setField

	@Override
	public GridField getField() {
		return m_mField;
	}
	
	/**
	 * @return Returns the savedMnemonic.
	 */
	public char getSavedMnemonic ()
	{
		return m_savedMnemonic;
	}	//	getSavedMnemonic
	
	/**
	 * @param savedMnemonic The savedMnemonic to set.
	 */
	public void setSavedMnemonic (char savedMnemonic)
	{
		m_savedMnemonic = savedMnemonic;
	}	//	getSavedMnemonic

}	//	VCheckBox
