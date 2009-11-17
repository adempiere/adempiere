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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AEnv;
import org.compiere.apps.FieldRecordInfo;
import org.compiere.model.GridField;
import org.compiere.model.MRole;
import org.compiere.swing.CButton;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

/**
 *	Date Edit.
 * 	Maintains data as Timestamp
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VDate.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class VDate extends JComponent
	implements VEditor, ActionListener, KeyListener, FocusListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9090302458969544529L;

	/*****************************************************************************
	 *	Mouse Listener for Popup Menu
	 */
	class VDate_mouseAdapter extends java.awt.event.MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee adaptee
		 */
		VDate_mouseAdapter(VDate adaptee)
		{
			this.adaptee = adaptee;
		}	//	VLookup_mouseAdapter

		private VDate adaptee;

		/**
		 *	Mouse Listener
		 *  @param e MouseEvent
		 */
		public void mouseClicked(MouseEvent e)
		{
		//	System.out.println("mouseClicked " + e.getID() + " " + e.getSource().getClass().toString());
			//	popup menu
			if (SwingUtilities.isRightMouseButton(e))
				adaptee.popupMenu.show((Component)e.getSource(), e.getX(), e.getY());
		}	//	mouse Clicked

	}	//	VLookup_mouseAdapter
	
	/**
	 *  IDE Bean Constructor
	 */
	public VDate()
	{
		this (DisplayType.Date);
	}   //  VDate

	/**
	 *  Simple Constructor
	 *  @param displayType display Type
	 */
	public VDate (int displayType)
	{
		this("Date", false, false, true, displayType, "Date");
	}   //  VDate

	/**
	 *	Create right aligned Date field
	 *  @param columnName column name
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param displayType display type
	 *  @param title title
	 */
	public VDate(String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayType, String title)
	{
		super();
		super.setName(columnName);
		m_columnName = columnName;
		m_title = title;
		//
		LookAndFeel.installBorder(this, "TextField.border");
		this.setLayout(new BorderLayout());
		this.setFocusable(false);
		//  Size
		this.setPreferredSize(m_text.getPreferredSize());
		int height = m_text.getPreferredSize().height;
		setMinimumSize(new Dimension (30,height));

		VDate_mouseAdapter mouse = new VDate_mouseAdapter(this);    //  popup
		m_text.addMouseListener(mouse);
		
		//	***	Text	***
		m_text.setBorder(null);
		m_text.setHorizontalAlignment(JTextField.TRAILING);
		if (m_displayType == DisplayType.Date)
		{
			m_text.addFocusListener(this);
			m_text.addKeyListener(this);
			m_text.setCaret(new VOvrCaret());
		}
		else if (m_displayType == DisplayType.DateTime)
			m_text.setColumns(20);
		//	Background
		setMandatory(mandatory);
		this.add(m_text, BorderLayout.CENTER);
		//
		if (displayType == DisplayType.DateTime || displayType == DisplayType.Time)
			m_displayType = displayType;		//	default = Date
		setFormat();

		//	***	Button	***
		m_button.setIcon(Env.getImageIcon("Calendar10.gif"));
		m_button.setMargin(new Insets(0, 0, 0, 0));
		m_button.setPreferredSize(new Dimension(height, height));
		m_button.addActionListener(this);
		m_button.setFocusable(false);
		this.add(m_button, BorderLayout.EAST);

		//	Prefereed Size
		this.setPreferredSize(this.getPreferredSize());		//	causes r/o to be the same length

		//	ReadWrite
		if (isReadOnly || !isUpdateable)
			setReadWrite(false);
		else
			setReadWrite(true);
	}	//	VDate

	/**
	 *  Dispose
	 */
	public void dispose()
	{
		m_text = null;
		m_button = null;
		m_mField = null;
	}   //  dispose

	/**
	 *	Set Document
	 *  @param doc doc
	 */
	protected void setDocument(Document doc)
	{
		m_text.setDocument(doc);
	}	//	getDocument

	private String			m_columnName;
	protected int			m_displayType = DisplayType.Date;
	private String			m_title;
	private boolean			m_setting;
	private String			m_oldText;
	private String			m_initialText;
	//
	private SimpleDateFormat	m_format;
	//
	private boolean			m_readWrite;
	private boolean			m_mandatory;

	/** The Text Field          */
	private CTextField		m_text = new CTextField (12);
	/** The Button              */
	private CButton			m_button = new CButton();

	//	Popup
	JPopupMenu 				popupMenu = new JPopupMenu();
	//	Field for Value Preference
	private GridField          m_mField = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VDate.class);
	
	/**
	 *	Set ReadWrite - field is always r/o for Time or DateTime
	 *  @param value value
	 */
	public void setReadWrite (boolean value)
	{
		m_readWrite = value;
	//	this.setFocusable(value);
		//	editor
		if (m_displayType == DisplayType.Date)
			m_text.setReadWrite(value);		//	sets Background
		else
		{
			m_text.setEditable(false);
			m_text.setFocusable(false);
			setBackground(false);
		}

		//	Don't show button if not ReadWrite
		if (m_button.isVisible() != value)
			m_button.setVisible(value);
		if (m_button.isEnabled() != value)
			m_button.setEnabled(value);
	}	//	setReadWrite

	/**
	 *	IsReadWrite
	 *  @return true if rw
	 */
	public boolean isReadWrite()
	{
		return m_readWrite;
	}	//	isReadWrite

	/**
	 *	Set Mandatory (and back bolor)
	 *  @param mandatory mandatory
	 */
	public void setMandatory (boolean mandatory)
	{
		m_mandatory = mandatory;
		m_text.setMandatory(mandatory);
		setBackground(false);
	}	//	setMandatory

	/**
	 *	Is it mandatory
	 *  @return true if mandatory
	 */
	public boolean isMandatory()
	{
		return m_mandatory;
	}	//	isMandatory

	/**
	 *  Set Background based on ReadWrite / mandatory / error
	 *  @param error if true, set background to error color, otherwise mandatory/ReadWrite
	 */
	public void setBackground (boolean error)
	{
		if (error)
			m_text.setBackground(AdempierePLAF.getFieldBackground_Error());
		else if (!m_readWrite)
			m_text.setBackground(AdempierePLAF.getFieldBackground_Inactive());
		else if (m_mandatory)
			m_text.setBackground(AdempierePLAF.getFieldBackground_Mandatory());
		else
			m_text.setBackground(AdempierePLAF.getFieldBackground_Normal());
	}	//	setBackground

	/**
	 *  Set Foreground
	 *  @param fg color
	 */
	public void setForeground(Color fg)
	{
		m_text.setForeground(fg);
	}   //  setForeground

	/**
	 * 	Set Format
	 *  Required when Format/Locale changed
	 */
	public void setFormat()
	{
		m_format = DisplayType.getDateFormat(m_displayType);
		if (m_displayType == DisplayType.Date)
			m_text.setDocument(new MDocDate(m_displayType, m_format, m_text, m_title));
	}	//	setFormat

	/**
	 * 	Request Focus
	 */
	public void requestFocus ()
	{
		m_text.requestFocus ();
	}	//	requestFocus

	/**
	 *	Set Editor to value
	 *  @param value timestamp/date or String to be parsed
	 */
	public void setValue (Object value)
	{
		log.finest("Value=" + value);
		m_oldText = "";
		if (value == null)
			;
		else if (value instanceof java.util.Date)
			m_oldText = m_format.format(value);
		else
		{
			String strValue = value.toString();
			//	String values - most likely in YYYY-MM-DD	(JDBC format)
			try
			{
				java.util.Date date = DisplayType.getDateFormat_JDBC().parse (strValue);
				m_oldText = m_format.format(date);		//	convert to display value
			}
			catch (ParseException pe0)
			{
			//	Try local string format
				try
				{
					java.util.Date date = m_format.parse(strValue);
					m_oldText = m_format.format(date);
				}
				catch (ParseException pe1)
				{
					log.log(Level.SEVERE, "setValue - " + pe1.getMessage());
					m_oldText = "";
				}
			}
		}
		if (m_setting)
			return;
		m_text.setText(m_oldText);
		m_initialText = m_oldText;
	}	//	setValue

	/**
	 *  Property Change Listener
	 *  @param evt event
	 */
	public void propertyChange (PropertyChangeEvent evt)
	{
		if (evt.getPropertyName().equals(GridField.PROPERTY))
			setValue(evt.getNewValue());
	}   //  propertyChange

	/**
	 *	Return Editor value
	 *  @return value
	 */
	public Timestamp getTimestamp()
	{
		if (m_text == null)
			return null;
		String value = m_text.getText();
		if (value == null || value.length() == 0)
			return null;
		//
		Timestamp ts = null;
		try
		{
			java.util.Date date = m_format.parse(value);
			ts = new Timestamp(date.getTime());
		}
		catch (ParseException pe)
		{
			log.fine(pe.getMessage());
		}
		return ts;
	}	//	getValue

	/**
	 *	Return Editor value (Timestamp)
	 *  @return value
	 */
	public Object getValue()
	{
		return getTimestamp();
	}	//	getValue

	/**
	 *  Return Display Value
	 *  @return display value
	 */
	public String getDisplay()
	{
		return m_text.getText();
	}   //  getDisplay

	
	/**************************************************************************
	 *	Action Listener (Button)
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		//  Preference
		if (e.getActionCommand().equals(ValuePreference.NAME))
		{
			if (MRole.getDefault().isShowPreference())
				ValuePreference.start (m_mField, getValue(), getDisplay());
			return;
		}
		else if (e.getActionCommand().equals(FieldRecordInfo.CHANGE_LOG_COMMAND))
		{
			FieldRecordInfo.start(m_mField);
			return;
		}
		if (e.getSource() == m_button)
		{
			m_button.setEnabled(false);
			setValue(startCalendar(this, getTimestamp(), m_format, m_displayType, m_title));
			try
			{
				fireVetoableChange (m_columnName, m_oldText, getValue());
			}
			catch (PropertyVetoException pve)	{}
			m_button.setEnabled(true);
			m_text.requestFocus();
		}
	}	//	actionPerformed

	/**
	 *  Action Listener Interface (Text)
	 *  @param listener listener
	 *///  addActionListener

	/**************************************************************************
	 *	Key Listener Interface
	 *  @param e Event
	 */
	public void keyTyped(KeyEvent e)	{}
	public void keyPressed(KeyEvent e)	{}

	/**
	 *	Key Listener.
	 *		- Escape 		- Restore old Text
	 *		- firstChange	- signal change
	 *  @param e event
	 */
	public void keyReleased(KeyEvent e)
	{
		log.finest("Key=" + e.getKeyCode() + " - " + e.getKeyChar() 
			+ " -> " + m_text.getText());
		//  ESC
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			m_text.setText(m_initialText);
		m_setting = true;
		try
		{
			Timestamp ts = getTimestamp();		//	getValue
			if (ts == null)						//	format error - just indicate change
				fireVetoableChange (m_columnName, m_oldText, null);
			else
				fireVetoableChange (m_columnName, m_oldText, ts);
		}
		catch (PropertyVetoException pve)	{}
		m_setting = false;
	}	//	keyReleased

	/**
	 *	Focus Gained	- Save for Escape
	 *  @param e event
	 */
	public void focusGained (FocusEvent e)
	{
	}	//	focusGained

	/**
	 *	Data Binding to to GridController.
	 *  @param e event
	 */
	public void focusLost (FocusEvent e)
	{
		//  did not get Focus first
		if (e.isTemporary())
			return;
	//	log.config( "VDate.focusLost");
		if (m_text == null || m_text.getText() == null)
			return;
		Object value = getValue();
		if (value == null && isMandatory() ) {
			// teo_sarca [ 1660595 ] Date field: incorrect functionality on paste
			// setValue(startCalendar(this, getTimestamp(), m_format, m_displayType, m_title));
			Timestamp ts = startCalendar(this, getTimestamp(), m_format, m_displayType, m_title);
			if (ts != null) {
				setValue(ts);
			}
			else {
				setValue(m_oldText);
			}
		}
		else
			setValue(value);
	}	//	focusLost

	/**
	 *	Invalid Entry - Start Calendar
	 *  @param jc parent
	 *  @param value value
	 *  @param format format
	 *  @param displayType display type
	 *  @param title title
	 *  @return formatted Date
	 */
	public static Timestamp startCalendar(Container jc, Timestamp value,
		SimpleDateFormat format, int displayType, String title)
	{
		log.config("Date=" + value);

		//	Find frame
		Frame frame = Env.getFrame(jc);
		//	Actual Call
		Calendar cal = new Calendar(frame, title, value, displayType);
		AEnv.showCenterWindow(frame, cal);
		Timestamp result = cal.getTimestamp();
		log.config( "Result=" + result);
		if (result == null && !cal.isCancel()) // F3P: added check for 'isCancel',
			result = value;		//	original
		cal = null;
		return result;
	}	//	startCalendar

	/**
	 *  Set Field/WindowNo for ValuePreference
	 *  @param mField MField
	 */
	public void setField (GridField mField)
	{
		m_mField = mField;
		if (m_mField != null
			&& MRole.getDefault().isShowPreference())
			ValuePreference.addMenu (this, popupMenu);
		if (m_mField != null)
			FieldRecordInfo.addMenu(this, popupMenu);
	}	//  setField

	/**
	 * 	Set Enabled
	 *	@param enabled enabled
	 */
	public void setEnabled (boolean enabled)
	{
		super.setEnabled(enabled);
		m_text.setEnabled(enabled);
		m_button.setEnabled(enabled);
		if (enabled)
			m_button.setReadWrite(m_readWrite);
	}	//	setEnabled

	/**************************************************************************
	 * 	Remove Action Listner
	 * 	@param l Action Listener
	 */
	public void removeActionListener(ActionListener l)
	{
		listenerList.remove(ActionListener.class, l);
	}	//	removeActionListener

	/**
	 * 	Add Action Listner
	 * 	@param l Action Listener
	 */
	public void addActionListener(ActionListener l)
	{
		listenerList.add(ActionListener.class, l);
	}	//	addActionListener

	/**
	 * 	Fire Action Event to listeners
	 *
	protected void fireActionPerformed()
	{
		int modifiers = 0;
		AWTEvent currentEvent = EventQueue.getCurrentEvent();
		if (currentEvent instanceof InputEvent)
			modifiers = ((InputEvent)currentEvent).getModifiers();
		else if (currentEvent instanceof ActionEvent)
			modifiers = ((ActionEvent)currentEvent).getModifiers();
		ActionEvent ae = new ActionEvent (this, ActionEvent.ACTION_PERFORMED,
			"VDate", EventQueue.getMostRecentEventTime(), modifiers);

		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		// Process the listeners last to first, notifying those that are interested in this event
		for (int i = listeners.length-2; i>=0; i-=2)
		{
			if (listeners[i]==ActionListener.class)
			{
				((ActionListener)listeners[i+1]).actionPerformed(ae);
			}
		}
	}	//	fireActionPerformed
	/**/
}	//	VDate