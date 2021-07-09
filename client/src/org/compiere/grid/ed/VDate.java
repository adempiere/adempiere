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
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;

import org.adempiere.plaf.AdempiereDateUI;
import org.compiere.apps.AEnv;
import org.compiere.swing.CButton;
import org.compiere.swing.CTextPane;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.SwingEnv;

/**
 *	Date Edit.
 * 	Maintains data as a Timestamp.  The editor uses a text field and a button.
 *  The button opens a date/time calendar tool.  The format of the date/time
 *  is set by the display type.  
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VDate.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 
 *  @author Michael McKay, 
 * 		<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 *
 * @version 3.9.4
 */
public class VDate extends VEditorAbstract
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5265412413586160999L;
	
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
		this(columnName, mandatory, isReadOnly, isUpdateable, displayType, title, false);
	}
	
	/**
	 *	Create right aligned Date field
	 *  @param columnName column name
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param displayType display type
	 *  @param title title
	 *  @param tableCellEditor true if the editor will be used in a table cell
	 */
	public VDate(String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayType, String title, boolean tableCellEditor)
	{

		super(columnName, mandatory, isReadOnly, isUpdateable, tableCellEditor);
		
		textEditor = (CTextPane) getEditorComponent();	
		button = getButtonComponent();

		this.title = title;
		if (displayType == DisplayType.DateTime || displayType == DisplayType.Time)
			this.displayType = displayType;		//	default = Date

		//	***	Text	***
		if (this.displayType == DisplayType.Date)
		{
			((JTextPane) textEditor).setCaret(new VOvrCaret());
		}
						
		//  Sets the date/time format to apply based on the display type.
		setFormat();
		
		//  Need to call this here since the editable nature
		//  of the Date field depends on the display type
		//  which is not available to the super class on
		//  initialization.
		setEditable(isEditable());
		
	}	//	VDate


	/**
	 *	Set Document
	 *  @param doc doc
	 */
	protected void setDocument(Document doc)
	{
		textEditor.setDocument(doc);
	}	//	getDocument

	protected int			displayType = DisplayType.Date;
	private String			title;
	//
	private SimpleDateFormat	m_format;

	/** The Text Field          */
	private CTextPane		textEditor;
	/** The Button              */
	private CButton			button;
	
	/**
	 * Define the ui class ID. It will be added to the 
	 * look and feel to define the ui class to use.
	 */
	private final static String uiClassID = "DateUI";

	@Override
    public String getUIClassID() {
        return uiClassID ;
    }

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VDate.class);
	
//	/**
//	 *	Set ReadWrite - field is always r/o for Time or DateTime
//	 *  @param value value
//	 */
//	@Override
//	public void setReadWrite (boolean value)
//	{
//		super.setReadWrite(value);
//		
//		// Special case
//		if (m_displayType != DisplayType.Date && m_text != null)
//		{
//			m_text.setEditable(false);
//			m_text.setFocusable(false);
////			setBackground(false);
//		}
//
//	}	//	setReadWrite


	/**
	 * 	Set Format
	 *  Required when Format/Locale changed
	 */
	public void setFormat()
	{
		m_format = DisplayType.getDateFormat(displayType);
		
		if (!(textEditor.getStyledDocument() instanceof MDocDate))
			textEditor.setStyledDocument(new MDocDate(displayType, m_format, textEditor, title));
		else
			((MDocDate) textEditor.getStyledDocument()).setFormat(m_format);
		
		((AdempiereDateUI) ui).setFormat(m_format);
		
	}	//	setFormat
	
	public SimpleDateFormat getFormat() {
		return m_format;
	}

	/**
	 * 	Request Focus
	 */
	public void requestFocus ()
	{
		textEditor.requestFocus ();
	}	//	requestFocus

	@Override
	public String setDisplayBasedOnValue (Object value)
	{
		log.finest("Value=" + value);
		
		Timestamp timestamp = convertValue(value);

		return displayTimestamp(timestamp);
		
	}	//	setValue

	private String displayTimestamp(Timestamp timestamp) {

		String display = "";
		
		// Display the value
		if (timestamp != null)
		{
			//  convert to display value
			display = m_format.format(timestamp);
		}
		else
		{
			display = "";
		}
		textEditor.setText(display);

		return display;
	}

	/**
	 * Convert the provided object to a Date
	 * @param value - null, Date, Timestamp or String.  For strings, if the string can't be parsed, returns null.
	 * @return the equivalent Date or null.  Throws IllegalArgumentException if the value is not one of the 
	 * expected types.
	 */
	private Timestamp convertValue(Object value) {
		
		//  Trap null values
		if (value == null || (value instanceof String && ((String) value).isEmpty()))
		{
			return null;
		}
		else if (value instanceof Timestamp)
		{
			return (Timestamp) value;
		}
		else if (value instanceof Date)
		{
			return new Timestamp( ((Date) value).getTime());
		}
		else if (value instanceof String)
		{
			String strValue = value.toString();
			//	String values - most likely in YYYY-MM-DD	(JDBC format)
			try
			{
				Date date = DisplayType.getDateFormat_JDBC().parse (strValue);
				return new Timestamp(date.getTime());
			}
			catch (ParseException pe0)
			{
			//	Try local string format
				try
				{
					Date date = m_format.parse(strValue);
					return new Timestamp(date.getTime());
				}
				catch (ParseException pe1)
				{
					log.log(Level.SEVERE, "setValue - " + pe1.getMessage());
					return null;
				}
			}
		}
		else
		{
			throw new IllegalArgumentException("Unhandeled value type: " + value);
		}

	}

	/**
	 *	Return Editor value
	 *  @return value
	 */
	public Timestamp getTimestamp()
	{
		
		if (getValue() == null || !(getValue() instanceof Timestamp))
			return null;
		
		return new Timestamp(((Timestamp) getValue()).getTime());
		
	}	//	getTimestamp()

//	/**
//	 *	Return Editor value (Timestamp)
//	 *  @return value
//	 */
//	public Object getValue()
//	{
//		return getTimestamp();
//	}	//	getValue


	
	protected void actionButton() {

		button.setEnabled(false);
		
		Timestamp newDate = startCalendar(this, getTimestamp(), m_format, displayType, title);
		
		displayTimestamp(newDate);
		
		button.setEnabled(true);
		textEditor.requestFocus();
		
	}

	protected void actionText() {
		
		if (!isCurrentValueValid())
		{
			actionButton();
		}
		
	}

	
	public void actionPerformed(ActionEvent e) 
	{
		super.actionPerformed(e);
		
		if (e.getSource().equals(textEditor))
			actionText();
		else if (e.getSource().equals(button))
			actionButton();
		
	}
//	private void bindData(Object newValue) {
//
//		Timestamp newDate = convertValue(newValue);
//
//		try
//		{
//			//  Inform listeners (if any) of the change.  If there are
//			//  listeners, they may either veto the change or set the value
//			fireVetoableChange (m_columnName, value, newDate);
//			
//			//  No veto, so the value was set by the listeners or 
//			//  we have to set it now.
//			if ((newDate == null || !newDate.equals(value)) && (newDate != null || value != null))
//			{
//				setValue(newDate);
//			}
//		}
//		catch (PropertyVetoException pve)	
//		{
//			// Reset the value and display
//			// to the previous value
//			setValue(value);
//			
//		}
//	}

//	/**
//	 *  Action Listener Interface (Text)
//	 *  @param listener listener
//	 *///  addActionListener
//
//	/**************************************************************************
//	 *	Key Listener Interface
//	 *  @param e Event
//	 */
//	public void keyTyped(KeyEvent e)	{}
//	public void keyPressed(KeyEvent e)	{}
//
//	/**
//	 *	Key Listener.
//	 *		- Escape 		- Restore old Text
//	 *		- firstChange	- signal change
//	 *  @param e event
//	 */
//	public void keyReleased(KeyEvent e)
//	{
//		log.finest("Key=" + e.getKeyCode() + " - " + e.getKeyChar() 
//			+ " -> " + m_text.getText());
//		//  ESC - revert to the set value
//		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
//			m_text.setText(m_oldText);
//	}	//	keyReleased

//	/**
//	 *	Focus Gained	- Save for Escape
//	 *  @param e event
//	 */
//	public void focusGained (FocusEvent e)
//	{
//	}	//	focusGained
//
//	/**
//	 *	Data Binding to to GridController.
//	 *  @param e event
//	 */
//	public void focusLost (FocusEvent e)
//	{
//		//  Ignore temporary changes in focus
//		if (e.isTemporary())
//			return;
//		
//		//  If the parent is null, the component may already have been removed
//		//  from the container - typically the table.  In this case, we don't
//		//  need to save the value.
//		if (getParent() == null)
//			return;
//		
//		if (!m_oldText.equals(m_text.getText()))
//		{
//			//  Something changed.  Try to bind the change
//			bindData(m_text.getText());
//			
//		}
//		
//		if (value == null && isMandatory() ) 
//		{
//			
//			actionButton();
//			
//		}
//		
//	}	//	focusLost

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
		Frame frame = SwingEnv.getFrame(jc);
		//	Actual Call
		Calendar cal = new Calendar(frame, title, value, displayType);
		AEnv.showCenterWindow(frame, cal);
		Timestamp result = cal.getTimestamp();
		log.config( "Result=" + result);
		if (result == null)
			result = value;		//	original
		else if (result.compareTo(new Timestamp(-1))==0)
			result = null;
		cal = null;
		return result;
	}	//	startCalendar

//	/**
//	 *  Set Field/WindowNo for ValuePreference
//	 *  @param mField MField
//	 */
//	public void setField (GridField mField)
//	{
//		m_mField = mField;
//		if (m_mField != null
//			&& MRole.getDefault().isShowPreference())
//			ValuePreference.addMenu (this, popupMenu);
//		if (m_mField != null)
//			RecordInfo.addMenu(this, popupMenu);
//	}	//  setField

//	@Override
//	public GridField getField() {
//		return m_mField;
//	}
	
	/**
	 * 	Set Enabled
	 *	@param enabled enabled
	 */
	public void setEnabled (boolean enabled)
	{
		super.setEnabled(enabled);
		textEditor.setEnabled(enabled);
		button.setEnabled(enabled);
		if (enabled)
			button.setReadWrite(isReadWrite());
	}	//	setEnabled

	@Override
	public String getDisplay() {
		return textEditor.getText();
	}

	@Override
	protected Object getCurrentValue() {
		
		String strValue = getDisplay();
		Object retValue = null;
		
		//	String values - most likely in YYYY-MM-DD	(JDBC format)
		if (!strValue.isEmpty())
		{
			// If the display is showing the hint string, return null
			String hintText = ((MDocDate) textEditor.getStyledDocument()).getHint();
			if (hintText != null && strValue.equals(hintText))
			{
				// Null dates are allowed. The mandatory logic will
				// deal with any issues.
				setCurrentValueValid(true);
				return null;
			}
			
			// Try the date format associated with the display type
			try
			{
				Date date = DisplayType.getDateFormat_JDBC().parse (strValue);
				retValue = new Timestamp(date.getTime());
			}
			catch (ParseException pe0)
			{
				//	If that fails, try the local string format
				try
				{
					Date date = m_format.parse(strValue);
					retValue = new Timestamp(date.getTime());
				}
				catch (ParseException pe1)
				{
					// Not a date we recognize
					setCurrentValueValid(false);
					return null;
				}
			}
		}
		
		setCurrentValueValid(true);
		return retValue;
	}

	@Override
	public void setEditable(boolean editable) {
		
		super.setEditable(editable);
		if (textEditor != null)
		{
			// Special case - only dates are editable
			// Everything else requires the dialog
			if (displayType != DisplayType.Date)
			{
				textEditor.setEditable(false);
				textEditor.setFocusable(false);
			}
		}
	}

	@Override
	public void setBackground(Color bg) {
		
		if (textEditor != null)
		{
			textEditor.setBackground(bg);
			
			StyledDocument doc = textEditor.getStyledDocument();
			if (doc instanceof MDocDate)
			{
				((MDocDate) doc).setBackground(bg);
			}
		}
	}
	
	@Override
	protected void handleInvalidValue() {
		actionButton();
	}	
	
}	//	VDate