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

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ScriptEditor;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CTextArea;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Text Control (JTextArea embedded in JScrollPane)
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VMemo.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public class VMemo extends CTextArea
	implements VEditor, KeyListener, FocusListener, ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9218826231484540367L;

	/*****************************************************************************/

	/**
	 *	Mouse Listener
	 */
	final class VMemo_mouseAdapter extends MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee
		 */
		VMemo_mouseAdapter(VMemo adaptee)
		{
			this.adaptee = adaptee;
		}	//	VMemo_mouseAdapter

		private VMemo adaptee;

		/**
		 *	Mouse Listener
		 *  @param e
		 */
		public void mouseClicked(MouseEvent e)
		{
			//	popup menu
			if (SwingUtilities.isRightMouseButton(e))
				adaptee.popupMenu.show((Component)e.getSource(), e.getX(), e.getY());
		}	//	mouse Clicked



	}	//	VMemo_mouseAdapter
	
	
	/**
	 *	IDE Baan Constructor
	 */
	public VMemo()
	{
		this("", false, false, true, 60, 4000);
	}	//	VMemo

	/**
	 *	Standard Constructor
	 *  @param columnName
	 *  @param mandatory
	 *  @param isReadOnly
	 *  @param isUpdateable
	 *  @param displayLength
	 *  @param fieldLength
	 */
	public VMemo (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayLength, int fieldLength)
	{
		super (fieldLength/80, 50);
		super.setName(columnName);
		LookAndFeel.installBorder(this, "TextField.border");
		this.addFocusListener(this);    //  to activate editor

		//  Create Editor
		setColumns(displayLength>VString.MAXDISPLAY_LENGTH ? VString.MAXDISPLAY_LENGTH : displayLength);	//  46
		setForeground(AdempierePLAF.getTextColor_Normal());
		setBackground(AdempierePLAF.getFieldBackground_Normal());

		setLineWrap(true);
		setWrapStyleWord(true);
		addFocusListener(this);
		setInputVerifier(new CInputVerifier()); //Must be set AFTER addFocusListener in order to work
		setMandatory(mandatory);
		m_columnName = columnName;
		m_fieldLength = fieldLength;

		if (isReadOnly || !isUpdateable)
			setReadWrite(false);
		addKeyListener(this);

		//	Popup
		addMouseListener(new VMemo_mouseAdapter(this));
		if (columnName.equals("Script"))
			menuEditor = new CMenuItem(Msg.getMsg(Env.getCtx(), "Script"), Env.getImageIcon("Script16.gif"));
		else
			menuEditor = new CMenuItem(Msg.getMsg(Env.getCtx(), "Editor"), Env.getImageIcon("Editor16.gif"));
		menuEditor.addActionListener(this);
		popupMenu.add(menuEditor);
	}	//	VMemo

	/**
	 *  Dispose
	 */
	public void dispose()
	{
	}   //  dispose

	JPopupMenu          popupMenu = new JPopupMenu();
	private CMenuItem 	menuEditor;
	private int			m_fieldLength;

	private String		m_columnName;
	private String		m_oldText = "";
	private boolean		m_firstChange;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VMemo.class);

	/**
	 *	Set Editor to value
	 *  @param value
	 */
	public void setValue(Object value)
	{
		super.setValue(value);
		m_firstChange = true;
		//	Always position Top 
		setCaretPosition(0);
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
	 *	ActionListener
	 *  @param e
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
			setValue(s);
			try
			{
				fireVetoableChange(m_columnName, null, getText());
				m_oldText = getText();
			}
			catch (PropertyVetoException pve)	{}
		}
	}	//	actionPerformed
	
	private int findWindowNo() {
		Container c = this.getParent();		
		return c != null ? Env.getWindowNo(c) : 0;
	}

	/**
	 *  Action Listener Interface - NOP
	 *  @param listener
	 */
	public void addActionListener(ActionListener listener)
	{
	}   //  addActionListener

	/**************************************************************************
	 *	Key Listener Interface
	 *  @param e
	 */
	public void keyTyped(KeyEvent e)	{}
	public void keyPressed(KeyEvent e)	{}

	/**
	 *	Escape 	- Restore old Text.
	 *  Indicate Change
	 *  @param e
	 */
	public void keyReleased(KeyEvent e)
	{
		//  ESC
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !getText().equals(m_oldText))
		{
			log.fine( "VMemo.keyReleased - ESC");
			setText(m_oldText);
			return;
		}
	}	//	keyReleased

	/**
	 *	Focus Gained	- Save for Escape
	 *  @param e
	 */
	public void focusGained (FocusEvent e)
	{
		log.config(e.paramString());
		if (e.getSource() instanceof VMemo)
			requestFocus();
		else
			m_oldText = getText();
	}	//	focusGained

	/**
	 *	Data Binding to MTable (via GridController)
	 *  @param e
	 */
	public void focusLost (FocusEvent e)
	{
		//  Indicate Change
		log.fine( "focusLost");
		try
		{
			String text = getText();
			fireVetoableChange(m_columnName, text, null);   //  No data committed - done when focus lost !!!
		}
		catch (PropertyVetoException pve)	{}
	}	//	focusLost

	/*************************************************************************/

	/**
	 *  Set Field/WindowNo for ValuePreference (NOP)
	 *  @param mField
	 */
	public void setField (org.compiere.model.GridField mField)
	{
	}   //  setField



private class CInputVerifier extends InputVerifier {

	 public boolean verify(JComponent input) {


		//NOTE: We return true no matter what since the InputVerifier is only introduced to fireVetoableChange in due time
		if (getText() == null && m_oldText == null)
			return true;
		else if (getText().equals(m_oldText))
			return true;
		//
		try
		{
			String text = getText();
			fireVetoableChange(m_columnName, null, text);
			m_oldText = text;
			return true;
		}
		catch (PropertyVetoException pve)	{}
		return true;

	 } // verify

   } // CInputVerifier




}	//	VMemo
