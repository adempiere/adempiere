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
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.TextUI;

import org.adempiere.plaf.AdempierePLAF;

/**
 *  Adempiere Colored Field with external popup editor.
 *  It extends ComboBox for UI consistency purposes
 *
 *  @author     Jorg Janke
 *  @version    $Id: CField.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 */
public class CField extends JComboBox
	implements CEditor, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2570380982649145678L;

	public static CField createNumeric(NumberFormat format)
	{
		return null;
	}
	public static CField createNumeric()
	{
		return createNumeric(new DecimalFormat());
	}
	public static CField createDate(DateFormat format)
	{
		return null;
	}
	public static CField createDate()
	{
		return createDate(new SimpleDateFormat());
	}
	public static CField createText(Pattern p)
	{
		return null;
	}
	public static CField createText(int length)
	{
		return null;
	}

	/*************************************************************************/

	public CField ()
	{
		this (null, null, "");
	}

	/**
	 *  Construct Adempiere Field with external popup editor
	 *
	 *  @param editor   the (validating) editor
	 *  @param cFieldPopup  the popup dialog
	 *  @param title    title for popup
	 */
	public CField (CFieldEditor editor, Class<?> cFieldPopup, String title)
	{
		super(new Object[] {"1", "2"});
		if (editor != null)
//			setEditor (editor);
		setEditable(true);
		m_title = title;

		//  Check popup
		if (cFieldPopup != null)
		{
			Class<?>[] interfaces = cFieldPopup.getInterfaces();
			boolean found = false;
			for (int i = 0; i < interfaces.length; i++)
			{
				if (interfaces[i].equals(CFieldPopup.class))
				{
					found = true;
					break;
				}
			}
			if (!found)
				throw new IllegalArgumentException ("Popup class must be CFieldPopup");
		}
		super.addActionListener(this);
	}   //  CField

	private CFieldEditor    m_editor = null;
	private Class<?>           m_popupClass = null;
	private String          m_title = null;
	private Object          m_oldValue = null;

	/*************************************************************************/

	/** Icon        */
	private Icon m_icon = null;

	/**
	 *  Set Icon of arrow button to icon
	 *  @param defaultIcon Icon to be displayed
	 */
	public void setIcon (Icon defaultIcon)
	{
		m_icon = defaultIcon;
	}   //  setIcon

	/**
	 *  Get Icon of arrow button to icon
	 *  @return defaultIcon Icon to be displayed
	 */
	public Icon getIcon ()
	{
		return m_icon;
	}   //  getIcon


	/**
	 *  Set UI and re-set Icon for arrow button
	 *  @param ui
	 */
	public void setUI (TextUI ui)
	{
		super.setUI(ui);
	}   //  setUI

	/**
	 *  Display Popup.
	 *  Called from AdempiereComboPopup and allows to implement
	 *  alternative actions than showing the popup
	 *  @return if true, the popup should be displayed
	 */
	public boolean displayPopup()
	{
		if (m_popupClass == null)
			return false;
		//
		try
		{
			//  Get Owner & Create Popup Instance
			Window win = SwingUtilities.getWindowAncestor(this);
			CFieldPopup popup = null;
			if (win instanceof Dialog)
			{
				Constructor<?> constructor = m_popupClass.getConstructor
					(new Class<?>[] {Dialog.class, String.class, Boolean.class});
				popup = (CFieldPopup)constructor.newInstance(new Object[]
					{(Dialog)win, m_title, new Boolean(true)});
			}
			else if (win instanceof Frame)
			{
				Constructor<?> constructor = m_popupClass.getConstructor
					(new Class[] {Frame.class, String.class, Boolean.class});
				popup = (CFieldPopup)constructor.newInstance(new Object[]
					{(Frame)win, m_title, new Boolean(true)});
			}
			if (popup == null)
				return false;
			//  Start Popup
			popup.setValue (m_editor.getItem());
			popup.setFormat (m_editor.getFormat());
			popup.show();
			m_editor.setItem (popup.getValue());
			popup = null;
		}
		catch (Exception e)
		{
			notifyUser (e);
		}
		//
		return false;
	}   //  displayPopup

	/**
	 *  Notify User of a Ptoblem with starting popup
	 *  @param e Exception
	 */
	public void notifyUser (Exception e)
	{
		JOptionPane.showMessageDialog(this, e.toString(), "Field Error", JOptionPane.ERROR_MESSAGE);
	}   //  notify User

	/*************************************************************************/

	/** Mandatory (default false)   */
	private boolean m_mandatory = false;

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
		if (super.isEnabled() != rw)
			super.setEnabled (rw);
		setBackground(false);
	}   //  setReadWrite

	/**
	 *	Is it possible to edit
	 *  @return true, if editable
	 */
	public boolean isReadWrite()
	{
		return super.isEnabled();
	}   //  isReadWrite


	/**
	 *  Set Background based on editable / mandatory / error
	 *  @param error if true, set background to error color, otherwise mandatory/editable
	 */
	public void setBackground (boolean error)
	{
		Color bg = null;
		if (error)
			bg = AdempierePLAF.getFieldBackground_Error();
		else if (!isReadWrite())
			bg = AdempierePLAF.getFieldBackground_Inactive();
		else if (m_mandatory)
			bg = AdempierePLAF.getFieldBackground_Mandatory();
		else
			bg = AdempierePLAF.getFieldBackground_Normal();
		if (bg.equals(m_editor.getBackground()))
			return;
		m_editor.setBackground(bg);
	}   //  setBackground

	/**
	 *	Set Editor to value
	 *  @param value value of the editor
	 */
	public void setValue (Object value)
	{
		m_oldValue = value;
	//	super.setSelectedItem(value);
	}   //  setValue

	/**
	 *	Return Editor value
	 *  @return current value
	 */
	public Object getValue()
	{
		return null;//super.getSelectedItem();
	}   //  getValue

	/**
	 *  Return Display Value
	 *  @return displayed String value
	 */
	public String getDisplay()
	{
	//	if (super.getSelectedItem() == null)
			return "";
	//	return super.getSelectedItem().toString();
	}   //  getDisplay

	/*************************************************************************/

	/**
	 *  Action Listener
	 *  @param e ActionEvent
	 */
	public void actionPerformed(ActionEvent e)
	{
		//  Do er have a change?
		Object newValue = getValue();
		if ((newValue != null && newValue.equals(m_oldValue))
			|| (newValue == null && m_oldValue == null) )
			return;
		super.firePropertyChange("DataChanged", m_oldValue, newValue);
		m_oldValue = newValue;
	}   //     //  actionPerformed
	
	@Override
	public void setBackground(Color bg)
	{
		m_editor.setBackground(bg);
	}

}   //  CField
