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
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;

import javax.swing.JPopupMenu;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.FieldRecordInfo;
import org.compiere.apps.ScriptEditor;
import org.compiere.model.GridField;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CTextArea;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Text Control (JTextArea embedded in JScrollPane)
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VText.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class VText extends CTextArea
	implements VEditor, KeyListener, ActionListener, FocusListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2873467246871800195L;

	/*****************************************************************************/

	/**
	 *	Mouse Listener
	 */
	final class VText_mouseAdapter extends MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee VText
		 */
		VText_mouseAdapter(VText adaptee)
		{
			this.adaptee = adaptee;
		}	//	VText_mouseAdapter

		private VText adaptee;

		/**
		 *	Mouse Listener
		 *  @param e event
		 */
		public void mouseClicked(MouseEvent e)
		{
			//	popup menu
			if (SwingUtilities.isRightMouseButton(e))
				adaptee.popupMenu.show((Component)e.getSource(), e.getX(), e.getY());
		}	//	mouse Clicked

	}	//	VText_mouseAdapter
	
	/**
	 *	Standard Constructor
	 *  @param columnName column name
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param displayLength display length
	 *  @param fieldLength field length
	 */
	public VText (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayLength, int fieldLength)
	{
		super (fieldLength < 300 ? 2 : 3, 50);
		super.setName(columnName);
		LookAndFeel.installBorder(this, "TextField.border");

		//  Create Editor
		setColumns(displayLength>VString.MAXDISPLAY_LENGTH ? VString.MAXDISPLAY_LENGTH : displayLength);	//  46
		setForeground(AdempierePLAF.getTextColor_Normal());
		setBackground(AdempierePLAF.getFieldBackground_Normal());

		setLineWrap(true);
		setWrapStyleWord(true);
		setMandatory(mandatory);
		m_columnName = columnName;
		m_fieldLength = fieldLength;

		if (isReadOnly || !isUpdateable)
			setReadWrite(false);
		addKeyListener(this);
		addFocusListener(this);

		//	Popup
		addMouseListener(new VText_mouseAdapter(this));
		if (columnName.equals("Script"))
			menuEditor = new CMenuItem(Msg.getMsg(Env.getCtx(), "Script"), Env.getImageIcon("Script16.gif"));
		else
			menuEditor = new CMenuItem(Msg.getMsg(Env.getCtx(), "Editor"), Env.getImageIcon("Editor16.gif"));
		menuEditor.addActionListener(this);
		popupMenu.add(menuEditor);
	}	//	VText

	/**
	 *  Dispose
	 */
	public void dispose()
	{
	}   //  dispose

	JPopupMenu          		popupMenu = new JPopupMenu();
	private CMenuItem 			menuEditor;
	private int					m_fieldLength;
	private String				m_columnName;
	private String				m_oldText;
	private String				m_initialText;
	private volatile boolean	m_setting = false;
	private GridField m_mField;

	/**
	 *	Set Editor to value
	 *  @param value value
	 */
	public void setValue(Object value)
	{
		if (value == null)
			m_oldText = "";
		else
			m_oldText = value.toString();
		if (m_setting)
			return;
		super.setValue(m_oldText);
		m_initialText = m_oldText;
		//	Always position Top 
		setCaretPosition(0);
	}	//	setValue

	/**
	 *  Property Change Listener
	 *  @param evt event
	 */
	public void propertyChange (PropertyChangeEvent evt)
	{
		if (evt.getPropertyName().equals(org.compiere.model.GridField.PROPERTY))
			setValue(evt.getNewValue());
	}   //  propertyChange

	/**
	 *	ActionListener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == menuEditor)
		{
			menuEditor.setEnabled(false);
			String s = null;
			if (m_columnName.equals("Script") || m_columnName.endsWith("_Script"))
				s = ScriptEditor.start (
						Env.getFrame(this.getParent()),
						Msg.translate(Env.getCtx(), m_columnName), getText(), isEditable(), 
						findWindowNo());
			else
				s = Editor.startEditor (this, Msg.translate(Env.getCtx(), m_columnName), 
					getText(), isEditable(), m_fieldLength);
			menuEditor.setEnabled(true);
			//	Data Binding
			try
			{
				fireVetoableChange(m_columnName, m_oldText, s);
			}
			catch (PropertyVetoException pve)	{}
		}
		else if (e.getActionCommand().equals(FieldRecordInfo.CHANGE_LOG_COMMAND))
		{
			FieldRecordInfo.start(m_mField);
			return;
		}
	}	//	actionPerformed

	private int findWindowNo() {
		Container c = this.getParent();		
		return c != null ? Env.getWindowNo(c) : 0;
	}

	/**
	 *  Action Listener Interface - NOP
	 *  @param listener listener
	 */
	public void addActionListener(ActionListener listener)
	{
	}   //  addActionListener

	/**************************************************************************
	 *	Key Listener Interface
	 *  @param e event
	 */
	public void keyTyped(KeyEvent e)	{}
	public void keyPressed(KeyEvent e)	{}

	/**
	 * 	Key Released
	 *	if Escape restore old Text.
	 *  @param e event
	 */
	public void keyReleased(KeyEvent e)
	{
		//  ESC
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			setText(m_initialText);
	}	//	keyReleased

	/**
	 *  Set Field/WindowNo for ValuePreference (NOP)
	 *  @param mField field model
	 */
	public void setField (org.compiere.model.GridField mField)
	{
		m_mField = mField;
		if (m_mField != null)
			FieldRecordInfo.addMenu(this, popupMenu);
	}   //  setField

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
		m_setting = true;
		try
		{
			fireVetoableChange(m_columnName, m_oldText, getText());
		}
		catch (PropertyVetoException pve)	{}
		m_setting = false;
	}

}	//	VText
