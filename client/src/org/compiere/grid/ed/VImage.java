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

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import org.adempiere.exceptions.ValueChangeListener;
import org.compiere.model.GridField;
import org.compiere.model.MImage;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *  Image Display of AD_Iamge_ID
 *
 *  @author  Jorg Janke
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 *  
 *  @version 3.9.4
 */
public class VImage extends JButton
	implements VEditor, ActionListener, FocusListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 368261613546756534L;

	/**
	 *  Image Editor
	 *  @param columnName column name
	 *  @param WindowNo window no
	 */
	public VImage (String columnName, int WindowNo)
	{
		this(columnName, WindowNo, false);
	}
	
	/**
	 *  Image Editor
	 *  @param columnName column name
	 *  @param WindowNo window no
	 *  @param tableCellEditor true if the editor will be used in a table cell
	 */
	public VImage (String columnName, int WindowNo, boolean tableCellEditor)
	{
		super("-");
		m_columnName = columnName;
		m_WindowNo = WindowNo;
		super.addActionListener(this);
		setTableCellEditor(tableCellEditor);
	}   //  VImage

	/**
	 *  Dispose
	 */
	public void dispose()
	{
		m_mImage = null;
	}   //  dispose

	/** WindowNo                */
	private int     m_WindowNo;
	/** The Image Model         */
	private MImage  m_mImage = null;
	/** Mandatory flag          */
	private boolean m_mandatory = false;
	/** Column Name             */
	private String	m_columnName = "AD_Image_ID";
	private Object currentValue;
	private Object value;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VImage.class);

	/**
	 *  Set Value
	 *  @param value
	 */
	public void setValue(Object value)
	{
		this.value = value;
		currentValue = this.value;
		updateDisplay();
	}   //  setValue

	/**
	 *  Get Value
	 *  @return value
	 */
	public Object getValue()
	{
		if (m_mImage == null || m_mImage.get_ID() == 0)
			return null;
		return new Integer(m_mImage.get_ID());
	}   //  getValue

	/**
	 *  Get Display Value
	 *  @return image name
	 */
	public String getDisplay()
	{
		return m_mImage.getName();
	}   //  getDisplay

	/**
	 *  Set ReadWrite
	 *  @param rw
	 */
	public void setReadWrite (boolean rw)
	{
		if (isEnabled() != rw)
			setEnabled (rw);
	}   //  setReadWrite

	/**
	 *  Get ReadWrite
	 *  @return true if rw
	 */
	public boolean isReadWrite()
	{
		return super.isEnabled();
	}   //  getReadWrite

	/**
	 *  Set Mandatory
	 *  @param mandatory
	 */
	public void setMandatory (boolean mandatory)
	{
		m_mandatory = mandatory;
	}   //  setMandatory

	/**
	 *  Get Mandatory
	 *  @return true if mandatory
	 */
	public boolean isMandatory()
	{
		return m_mandatory;
	}   //  isMandatory

	/**
	 *  Set Background - nop
	 *  @param color
	 */
	public void setBackground(Color color)
	{
	}   //  setBackground

	/**
	 *  Set Background - nop
	 */
	public void setBackground()
	{
	}   //  setBackground

	/**
	 *  Set Background - nop
	 *  @param error
	 */
	public void setBackground(boolean error)
	{
	}   //  setBackground

	/**
	 *  Property Change
	 *  @param evt
	 */
	public void propertyChange(PropertyChangeEvent evt)
	{
		if (evt.getPropertyName().equals(org.compiere.model.GridField.PROPERTY))
			setValue(evt.getNewValue());
	}   //  propertyChange

	/**
	 *  ActionListener - start dialog and set value
	 *  @param e
	 */
	public void actionPerformed (ActionEvent e)
	{
		VImageDialog vid = new VImageDialog(Env.getWindow(m_WindowNo), m_mImage);
		vid.setVisible(true);
		int AD_Image_ID = vid.getAD_Image_ID();
		Integer newValue = null;
		if (AD_Image_ID != 0)
			newValue = new Integer (AD_Image_ID);
		//
		m_mImage = null;	//	force reload
		
		currentValue = newValue;
		updateDisplay();
		
	}   //  actionPerformed

	private void updateDisplay() {
		
		final int COLUMNSIZE = 220;
		
		int newValue = 0;
		if (currentValue instanceof Integer)
			newValue = ((Integer)currentValue).intValue();
		if (newValue == 0)
		{
			m_mImage = null;
			super.setText("-");
			super.setIcon(null);
			super.setToolTipText(null);
			return;
		}
		//  Get/Create Image
		if (m_mImage == null || newValue != m_mImage.get_ID())
			m_mImage = MImage.get (Env.getCtx(), newValue);
		//
		log.fine(m_mImage.toString());
		super.setText(null);
		
		if (m_mImage.getImage()==null)
			return;
		
		ImageIcon image = new ImageIcon(m_mImage.getImage());
		if (image.getIconHeight() > COLUMNSIZE || image.getIconWidth() > COLUMNSIZE) {
			if (image.getIconHeight() > image.getIconWidth()) {
				super.setIcon(new ImageIcon(m_mImage.getImage().getScaledInstance(-1, COLUMNSIZE, Image.SCALE_DEFAULT)));
			} else {
				super.setIcon(new ImageIcon(m_mImage.getImage().getScaledInstance(COLUMNSIZE, -1, Image.SCALE_DEFAULT)));
			}
		} else {
			// the image is smaller than the COLUMNSIZE
			super.setIcon(m_mImage.getIcon());
		}
		super.setToolTipText(m_mImage.getName());
		invalidate();
	}

	//	Field for Value Preference
	private GridField          m_mField = null;
	private Object oldValue;
	private boolean isTableCellEditor;
	/**
	 *  Set Field/WindowNo for ValuePreference (NOP)
	 *  @param mField
	 */
	public void setField (GridField mField)
	{
		m_mField = mField;
	}   //  setField

	@Override
	public GridField getField() {
		return m_mField;
	}

	@Override
	public void addValueChangeListener(ValueChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JComponent getComponent() {
		return this;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		//  The focus lost event triggers data binding, except when the
		//  focus is lost temporarily by say switching windows.
		
		//  Ignore temporary focus events such as switching windows.
		if (e.isTemporary())
			return;

		//  If the parent is null, the component may already have been removed
		//  from the container - typically the table.  In this case, we don't
		//  need to save the value.
		if (getParent() == null)
			return;
		
		try
		{
			fireVetoableChange (m_columnName, value, currentValue);
			setValue(currentValue);
		}
		catch (PropertyVetoException pve)	
		{
		}

	}
	
	@Override
	public boolean hasChanged() {
		
		if (oldValue == null && getValue() != null
			|| oldValue != null && !oldValue.equals(getValue()))
			return false;
		else
			return true;
		
	}

	@Override
	public void set_oldValue() {
		
		oldValue = getValue();
		
	}

	@Override
	public void setTableCellEditor(boolean isTableCellEditor) {
		this.isTableCellEditor = isTableCellEditor;
	}

	@Override
	public boolean isTableCellEditor() {
		return isTableCellEditor;
	}
}   //  VImage
