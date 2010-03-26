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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
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
import java.net.URL;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.apps.FieldRecordInfo;
import org.compiere.model.GridField;
import org.compiere.model.MRole;
import org.compiere.swing.CButton;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * 	URL Editor
 *	
 *  @author Jorg Janke
 *  @version $Id: VURL.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class VURL extends JComponent
	implements VEditor, ActionListener, KeyListener, FocusListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -350536181025487190L;

	/******************************************************************************
	 *	Mouse Listener
	 */
	final class VURL_mouseAdapter extends MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee adaptee
		 */
		VURL_mouseAdapter(VURL adaptee)
		{
			m_adaptee = adaptee;
		}	//	VString_mouseAdapter

		private VURL m_adaptee;

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

	}	//	VURL_mouseAdapter
	
	/**
	 * 	IDE Constructor
	 */
	public VURL ()
	{
		this ("URL", false, false, true, 20, 60);
	}	//	VURL
	
	/**
	 *	Detail Constructor
	 *  @param columnName column name
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param displayLength display length
	 *  @param fieldLength field length
	 */
	public VURL (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayLength, int fieldLength)
	{
		super.setName(columnName);
		m_columnName = columnName;
		m_fieldLength = fieldLength;
		m_mandatory = mandatory;
		LookAndFeel.installBorder(this, "TextField.border");
		this.setLayout(new BorderLayout());
		//  Size
		this.setPreferredSize(m_text.getPreferredSize());
		int height = m_text.getPreferredSize().height;

		//	***	Text	***
		m_text = new CTextField(displayLength>VString.MAXDISPLAY_LENGTH ? VString.MAXDISPLAY_LENGTH : displayLength);
		m_text.setEditable(isReadOnly);
		m_text.setFocusable(true);
		m_text.setBorder(null);
		m_text.setHorizontalAlignment(JTextField.LEADING);

		//	Background
		setMandatory(mandatory);
		this.add(m_text, BorderLayout.CENTER);

		//	***	Button	***
		m_button.setIcon(Env.getImageIcon("Online10.gif"));	//	should be 10
		m_button.setMargin(new Insets(0, 0, 0, 0));
		m_button.setPreferredSize(new Dimension(height, height));
		m_button.addActionListener(this);
		m_button.setFocusable(false);
		this.add(m_button, BorderLayout.EAST);

		//	Preferred Size
		this.setPreferredSize(this.getPreferredSize());		//	causes r/o to be the same length
		//	ReadWrite
		if (isReadOnly || !isUpdateable)
			setReadWrite(false);
		else
			setReadWrite(true);

		m_text.addKeyListener(this);
		m_text.addFocusListener(this);
		m_text.addActionListener(this);
		m_text.addMouseListener(new VURL_mouseAdapter(this));
		//	Popup for Editor
		if (fieldLength > displayLength)
		{			
			mEditor = new CMenuItem (Msg.getMsg(Env.getCtx(), "Editor"), Env.getImageIcon("Editor16.gif"));
			mEditor.addActionListener(this);
			popupMenu.add(mEditor);
		}
		setForeground(AdempierePLAF.getTextColor_Normal());
		setBackground(AdempierePLAF.getFieldBackground_Normal());
	}	//	VURL

	/**	Logger	*/
	private static CLogger log = CLogger.getCLogger (VURL.class);
	/** Column Name				*/
	private String				m_columnName;
	/** The Text				*/
	private CTextField			m_text = new CTextField();
	private boolean				m_readWrite;
	private boolean				m_mandatory;
	/** The Button              */
	private CButton				m_button = new CButton();
	/**	Popup Menu				*/
	JPopupMenu 					popupMenu = new JPopupMenu();
	/** Editor Menu Item		*/
	private CMenuItem 			mEditor;
	/** Grid Field				*/
	private GridField      		m_mField = null;

	private String				m_oldText;
	private String				m_initialText;
	/**	Setting new value			*/
	private volatile boolean	m_setting = false;
	/**	Field in focus				*/
	private volatile boolean	m_infocus = false;
	/** Field Length				*/
	private int					m_fieldLength;

	/**
	 * 	Dispose resources
	 */
	public void dispose()
	{
		m_text = null;
		m_button = null;
		m_mField = null;
	}	//	dispose

	/**
	 * 	Set Mandatory
	 * 	@param mandatory mandatory
	 */
	public void setMandatory (boolean mandatory)
	{
		m_mandatory = mandatory;
		m_text.setMandatory(mandatory);
		setBackground (false);
	}	//	setMandatory

	/**
	 * 	Get Mandatory
	 *  @return mandatory
	 */
	public boolean isMandatory()
	{
		return m_mandatory;
	}	//	isMandatory

	/**
	 * 	Set ReadWrite
	 * 	@param rw read/write
	 */
	public void setReadWrite (boolean rw)
	{
		m_readWrite = rw;
		m_text.setReadWrite(rw);
		setBackground (false);
	}	//	setReadWrite

	/**
	 * 	Is Read Write
	 * 	@return read write
	 */
	public boolean isReadWrite()
	{
		return m_readWrite;
	}	//	isReadWrite

	/**
	 * 	Set Foreground
	 * 	@param color color
	 */
	public void setForeground (Color color)
	{
		m_text.setForeground(color);
	}	//	SetForeground

	/**
	 * 	Set Background
	 * 	@param error Error
	 */
	public void setBackground (boolean error)
	{
		if (error)
			setBackground(AdempierePLAF.getFieldBackground_Error());
		else if (!m_readWrite)
			setBackground(AdempierePLAF.getFieldBackground_Inactive());
		else if (m_mandatory)
			setBackground(AdempierePLAF.getFieldBackground_Mandatory());
		else
			setBackground(AdempierePLAF.getFieldBackground_Normal());
	}	//	setBackground

	/**
	 * 	Set Background
	 * 	@param color Color
	 */
	public void setBackground (Color color)
	{
		m_text.setBackground(color);
	}	//	setBackground

	/**
	 *	Set Editor to value
	 *  @param value value
	 */
	public void setValue(Object value)
	{
	//	log.config( "VString.setValue", value);
		if (value == null)
			m_oldText = "";
		else
			m_oldText = value.toString();
		//	only set when not updated here
		if (m_setting)
			return;
		setText (m_oldText);
		m_initialText = m_oldText;
		//	If R/O left justify 
		if (!m_text.isEditable() || !isEnabled())
			m_text.setCaretPosition(0);
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
	 *	Return Editor value
	 *  @return value
	 */
	public Object getValue()
	{
		return getText();
	}	//	getValue

	/**
	 *  Return Display Value
	 *  @return value
	 */
	public String getDisplay()
	{
		return m_text.getText();
	}   //  getDisplay


	/**
	 *	Key Released.
	 *	if Escape Restore old Text
	 *  @param e event
	 */
	public void keyReleased(KeyEvent e)
	{
		log.finest("Key=" + e.getKeyCode() + " - " + e.getKeyChar()
			+ " -> " + getText());
		//  ESC
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			setText(m_initialText);
	}	//	keyReleased

	/**
	 * 	Key Pressed
	 *	@param e ignored
	 */
	public void keyPressed (KeyEvent e)
	{
	}	//	keyPressed
	/**
	 * 	Key Typed
	 *	@param e ignored
	 */
	public void keyTyped (KeyEvent e)
	{
	}	//	keyTyped
	
	/**
	 * 	Add Action Listener
	 *	@param listener listener
	 */
	public void addActionListener (ActionListener listener)
	{
		m_text.addActionListener(listener);
	}	//	addActionListener
	
	/**
	 *	Data Binding to MTable (via GridController)	-	Enter pressed
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(ValuePreference.NAME))
		{
			if (MRole.getDefault().isShowPreference())
				ValuePreference.start (m_mField, getValue());
			return;
		}
		else if (e.getActionCommand().equals(FieldRecordInfo.CHANGE_LOG_COMMAND))
		{
			FieldRecordInfo.start(m_mField);
			return;
		}

		//  Invoke Editor
		else if (e.getSource() == mEditor)
		{
			String s = Editor.startEditor(this, Msg.translate(Env.getCtx(), m_columnName), 
				getText(), m_text.isEditable(), m_fieldLength);
			setText(s);
		}
		//	
		else if (e.getSource() == m_button)
		{
			action_button();
			return;
		}
		//  Data Binding
		try
		{
			fireVetoableChange(m_columnName, m_oldText, getText());
		}
		catch (PropertyVetoException pve)	
		{
		}
	}	//	actionPerformed

	/**
	 * 	Action button pressed - show URL
	 */
	private void action_button()
	{
		String urlString = m_text.getText();
		String message = null;
		if (urlString != null && urlString.length() > 0)
		{
			try
			{
				URL url = new URL(urlString);
				Env.startBrowser(urlString);
				return;
			}
			catch (Exception e)
			{
				message = e.getMessage();
			}
		}
		ADialog.warn(0, this, "URLnotValid", message);
	}	//	action button
	
	/**
	 *  Set Field/WindowNo for ValuePreference
	 *  @param mField field
	 */
	public void setField (GridField mField)
	{
		m_mField = mField;
		if (m_mField != null
			&& MRole.getDefault().isShowPreference())
			ValuePreference.addMenu (this, popupMenu);
		if (m_mField != null)
			FieldRecordInfo.addMenu(this, popupMenu);
	}   //  setField

	
	/**
	 * 	Set Text
	 *	@param text text
	 */
	public void setText (String text)
	{
		m_text.setText (text);
	}	//	setText

	
	/**
	 * 	Get Text (clear)
	 *	@return text
	 */
	public String getText ()
	{
		String text = m_text.getText();
		return text;
	}	//	getText

	/**
	 * 	Focus Gained.
	 * 	Enabled with Obscure
	 *	@param e event
	 */
	public void focusGained (FocusEvent e)
	{
		m_infocus = true;
		setText(getText());		//	clear
	}	//	focusGained

	/**
	 * 	Focus Lost
	 * 	Enabled with Obscure
	 *	@param e event
	 */
	public void focusLost (FocusEvent e)
	{
		m_setting = true;
		try
		{
			String clear = getText();
			if (clear.length() > m_fieldLength)
				clear = clear.substring(0, m_fieldLength);
			fireVetoableChange (m_columnName, m_oldText, clear);
		}
		catch (PropertyVetoException pve)	
		{
		}
		m_setting = false;

		m_infocus = false;
		setText(getText());		//	obscure
	}	//	focus Lost

	
}	//	VURL
