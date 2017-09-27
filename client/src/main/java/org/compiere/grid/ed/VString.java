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

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;

import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretListener;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.RecordInfo;
import org.compiere.model.GridField;
import org.compiere.model.MRole;
import org.compiere.model.Obscure;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Data Binding:
 *		VEditors call fireVetoableChange(m_columnName, null, getText());
 *		GridController (for Single-Row) and VCellExitor (for Multi-Row)
 *      listen to Vetoable Change Listener (vetoableChange)
 *		then set the value for that column in the current row in the table
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VString.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 */
public final class VString extends CTextField
	implements VEditor, ActionListener, FocusListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8487860095322876086L;
	
	/** Max Display Length - 60 */
	public static final int MAXDISPLAY_LENGTH = org.compiere.model.GridField.MAXDISPLAY_LENGTH;

	/******************************************************************************
	 *	Mouse Listener
	 */
	final class VString_mouseAdapter extends MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee adaptee
		 */
		VString_mouseAdapter(VString adaptee)
		{
			m_adaptee = adaptee;
		}	//	VString_mouseAdapter

		private VString m_adaptee;

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

	}	//	VText_mouseAdapter
	
	/**
	 *	IDE Bean Constructor for 30 character updateable field
	 */
	public VString()
	{
		this("String", false, false, true, 30, 30, "", null);
	}	//	VString

	/**
	 *	Detail Constructor
	 *  @param columnName column name
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param displayLength display length
	 *  @param fieldLength field length
	 *  @param VFormat format
	 *  @param ObscureType obscure type
	 */
	public VString (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayLength, int fieldLength, String VFormat, String ObscureType)
	{
		super(displayLength>MAXDISPLAY_LENGTH ? MAXDISPLAY_LENGTH : displayLength);
		super.setName(columnName);
		m_columnName = columnName;
		if (VFormat == null)
			VFormat = "";
		m_VFormat = VFormat;
		m_fieldLength = fieldLength;
		if (m_VFormat.length() != 0 || m_fieldLength != 0)
			setDocument(new MDocString(m_VFormat, m_fieldLength, this));
		if (m_VFormat.length() != 0)
			setCaret(new VOvrCaret());
		//
		setMandatory(mandatory);
		if (ObscureType != null && ObscureType.length() > 0)
		{
			m_obscure = new Obscure ("", ObscureType);
			m_stdFont = getFont();
			m_obscureFont = new Font("SansSerif", Font.ITALIC, m_stdFont.getSize());
		}

		//	Editable
		if (isReadOnly || !isUpdateable)
		{
			setEditable(false);
			setBackground(AdempierePLAF.getFieldBackground_Inactive());
		}

		this.addKeyListener(this);
		this.addActionListener(this);
		this.addFocusListener(this);
		addMouseListener(new VString_mouseAdapter(this));
		//	Popup for Editor
		if (fieldLength > displayLength)
		{			
			mEditor = new CMenuItem (Msg.getMsg(Env.getCtx(), "Editor"), Env.getImageIcon("Editor16.gif"));
			mEditor.addActionListener(this);
			popupMenu.add(mEditor);
		}
		setForeground(AdempierePLAF.getTextColor_Normal());
		setBackground(AdempierePLAF.getFieldBackground_Normal());
	}	//	VString

	/**
	 *  Dispose
	 */
	public void dispose()
	{
		m_mField = null;
	}   //  dispose

	/**	Popup Menu				*/
	JPopupMenu 					popupMenu = new JPopupMenu();
	/** Editor Menu Item		*/
	private CMenuItem 			mEditor;
	/** Grid Field				*/
	private GridField      		m_mField = null;
	/** Column Name				*/
	private String				m_columnName;
	private String				m_oldText;
	private String				m_initialText;
	private String				m_VFormat;
	/** Field Length				*/
	private int					m_fieldLength;
	/**	Obcure Setting				*/
	private Obscure				m_obscure = null;
	private Font				m_stdFont = null;
	private Font				m_obscureFont = null;
	/**	Setting new value			*/
	private volatile boolean	m_setting = false;
	/**	Field in focus				*/
	private volatile boolean	m_infocus = false;

	/**	Logger	*/
	private static CLogger log = CLogger.getCLogger (VString.class);

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
		if (!isEditable() || !isEnabled())
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
		return super.getText();		// optionally obscured
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
		else if(e.getActionCommand().equals(RecordInfo.CHANGE_LOG_COMMAND))
		{
			RecordInfo.start(m_mField);
			return;
		}

		//  Invoke Editor
		if (e.getSource() == mEditor)
		{
			String s = Editor.startEditor(this, Msg.translate(Env.getCtx(), m_columnName), 
				getText(), isEditable(), m_fieldLength);
			setText(s);
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
			RecordInfo.addMenu(this, popupMenu);
	}   //  setField

	@Override
	public GridField getField() {
		return m_mField;
	}
	
	/**
	 *  Feature Request [1707462]
	 *  Set VFormat
	 *  @param strMask mask
	 *  @author fer_luck
	 */
	public void setVFormat (String strMask)
	{
		m_VFormat = strMask;
		//Get the actual caret from the field, if there's no
		//caret then just catch the exception and continue
		//creating the new caret.
		try{
			CaretListener [] cl = this.getCaretListeners();
			this.removeCaretListener(cl[0]);
		} catch(ClassCastException ex ){
			log.fine("VString.setVFormat - No caret Listeners");
		}
		
		//hengsin: [ adempiere-Bugs-1891037 ], preserve current data before change of format		
		String s = getText();
		setDocument(new MDocString(m_VFormat, m_fieldLength, this));
		setText(s);
	}   //  setVFormat
	
	/**
	 * 	Set Text (optionally obscured)
	 *	@param text text
	 */
	public void setText (String text)
	{
		if (m_obscure != null && !m_infocus)
		{
			super.setFont(m_obscureFont);
			super.setText (m_obscure.getObscuredValue(text));
			super.setForeground(Color.gray);
		}
		else
		{
			if (m_stdFont != null)
			{
				super.setFont(m_stdFont);
				super.setForeground(AdempierePLAF.getTextColor_Normal());
			}
			super.setText (text);
		}
	}	//	setText

	
	/**
	 * 	Get Text (clear)
	 *	@return text
	 */
	public String getText ()
	{
		String text = super.getText();
		if (m_obscure != null && text != null && text.length() > 0)
		{
			if (text.equals(m_obscure.getObscuredValue()))
				text = m_obscure.getClearValue();
		}
		return text;
	}	//	getText

	/**
	 *  Feature Request [1707462]
	 *  Get VFormat
	 *  @return strMask mask
	 *  @author fer_luck
	 */
	public String getVFormat ()
	{
		return this.m_VFormat;
	}	//	getVFormat
	
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

	@Override
	public void setFont(Font f) {
		super.setFont(f);
		m_stdFont = f;
		m_obscureFont = new Font("SansSerif", Font.ITALIC, m_stdFont.getSize());
	}
	
}	//	VString