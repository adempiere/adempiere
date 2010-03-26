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

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;

import org.compiere.apps.AEnv;
import org.compiere.apps.FieldRecordInfo;
import org.compiere.model.GridField;
import org.compiere.model.MRole;
import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

/**
 *	Number Control
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VNumber.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>BF [ 1739516 ] Warning on numeric field with range set
 * 			<li>BF [ 1834393 ] VNumber.setFocusable not working
 */
public final class VNumber extends JComponent
	implements VEditor, ActionListener, KeyListener, FocusListener, VManagedEditor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -516704171367798447L;
	/**	Number of Columns (12)		*/
	public final static int SIZE = 12;
	/** Automatically pop up calculator */
	public final static boolean AUTO_POPUP = false;
	
	JPopupMenu          		popupMenu = new JPopupMenu();
	
	/******************************************************************************
	 *	Mouse Listener
	 */
	final class VNumber_mouseAdapter extends MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee adaptee
		 */
		VNumber_mouseAdapter(VNumber adaptee)
		{
			m_adaptee = adaptee;
		}	//	VNumber_mouseAdapter

		private VNumber m_adaptee;

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
	 *  IDE Bean Constructor
	 */
	public VNumber()
	{
		this("Number", false, false, true, DisplayType.Number, "Number");
	}   //  VNumber

	/**
	 *	Create right aligned Number field.
	 *	no popup, if WindowNo == 0 (for IDs)
	 *  @param columnName column name
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param displayType display type
	 *  @param title title
	 */
	public VNumber(String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayType, String title)
	{
		super();
		super.setName(columnName);
		m_columnName = columnName;
		m_title = title;
		setDisplayType(displayType);
		//
		LookAndFeel.installBorder(this, "TextField.border");
		this.setLayout(new BorderLayout());

		//	***	Text	***
		m_text.setBorder(null);
		m_text.setHorizontalAlignment(JTextField.TRAILING);
		m_text.addKeyListener(this);
		m_text.addFocusListener(this);
		//	Background
		setMandatory(mandatory);
		this.add(m_text, BorderLayout.CENTER);

		//	***	Button	***
		m_button.setIcon(Env.getImageIcon("Calculator10.gif"));
		m_button.setMargin(new Insets(0, 0, 0, 0));
		m_button.setFocusable(false);
		m_button.addActionListener(this);
		this.add (m_button, BorderLayout.EAST);

		//  Size
		setColumns(SIZE, 0);	
		//	ReadWrite
		if (isReadOnly || !isUpdateable)
			setReadWrite(false);
		else
			setReadWrite(true);
		
		m_text.addMouseListener(new VNumber_mouseAdapter(this));
	}	//	VNumber

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
	 *  @param doc document
	 */
	protected void setDocument(Document doc)
	{
		m_text.setDocument(doc);
	}	//	getDocument

	private String			m_columnName;
	protected int			m_displayType;	//  Currency / UoM via Context
	private DecimalFormat	m_format;
	private String			m_title;
	private boolean			m_setting;
	private String			m_oldText;
	private String			m_initialText;

	private boolean			m_rangeSet = false;
	private Double			m_minValue;
	private Double			m_maxValue;
	private boolean			m_modified = false;
	
	/**  The Field                  */
	private CTextField		m_text = new CTextField(SIZE);	//	Standard
	/** The Button                  */
	private CButton		    m_button = new CButton();

	private GridField          m_mField = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VNumber.class);

	/**
	 * Select all the number text.
	 */
	public void selectAll()
	{
		m_text.selectAll();
	}

	/**
	 * 	Set no of Columns
	 *	@param columns columns
	 *  @param height 0 to use default
	 */
	public void setColumns (int columns, int height)
	{
		m_text.setPreferredSize(null);
		m_text.setMinimumSize(null);
		m_text.setColumns(columns);
		if (height > 0) {
			Dimension size = m_text.getPreferredSize();
			if (height > size.height)			//	default 16
				size.height = height;
			if (CComboBox.FIELD_HIGHT-4 > size.height)
				size.height = VLookup.FIELD_HIGHT-4;
			m_text.setPreferredSize(size);
			m_text.setMinimumSize(size);
		} else {
			StringBuffer s = new StringBuffer();
			for(int i = 0; i < SIZE; i++) {
				s.append("0");
			}
			CTextField f = new CTextField(s.toString());
			m_text.setPreferredSize(f.getPreferredSize());
			m_text.setMinimumSize(f.getMinimumSize());
		}
		this.setPreferredSize(m_text.getPreferredSize());		//	causes r/o to be the same length
		this.setMinimumSize(m_text.getMinimumSize());
		int h = m_text.getPreferredSize().height;
		m_button.setPreferredSize(new Dimension(h, h));
	}	//	setColumns
	
	/**
	 *	Set Range with min & max
	 *  @param minValue min value
	 *  @param maxValue max value
	 *	@return true, if accepted
	 */
	public boolean setRange(Double minValue, Double maxValue)
	{
		m_rangeSet = true;
		m_minValue = minValue;
		m_maxValue = maxValue;
		return m_rangeSet;
	}	//	setRange

	/**
	 *	Set Range with min & max = parse US style number w/o Gouping
	 *  @param minValue min value
	 *  @param maxValue max value
	 *  @return true if accepted
	 */
	public boolean setRange(String minValue, String maxValue)
	{
		if (minValue == null || maxValue == null)
			return false;
		try
		{
			m_minValue = Double.valueOf(minValue);
			m_maxValue = Double.valueOf(maxValue);
		}
		catch (NumberFormatException nfe)
		{
			return false;
		}
		m_rangeSet = true;
		return m_rangeSet;
	}	//	setRange

	/**
	 *  Set and check DisplayType
	 *  @param displayType display type
	 */
	public void setDisplayType (int displayType)
	{
		m_displayType = displayType;
		if (!DisplayType.isNumeric(displayType))
			m_displayType = DisplayType.Number;
		m_format = DisplayType.getNumberFormat(displayType);
		m_text.setDocument (new MDocNumber(displayType, m_format, m_text, m_title));
	}   //  setDisplayType

	/**
	 *	Set ReadWrite
	 *  @param value value
	 */
	public void setReadWrite (boolean value)
	{
		if (m_text.isReadWrite() != value)
			m_text.setReadWrite(value);
		if (m_button.isReadWrite() != value)
			m_button.setReadWrite(value);
		//	Don't show button if not ReadWrite
		if (m_button.isVisible() != value)
			m_button.setVisible(value);
		setFocusable(value == true);
	}	//	setReadWrite

	/**
	 *	IsReadWrite
	 *  @return true if rw
	 */
	public boolean isReadWrite()
	{
		return m_text.isReadWrite();
	}	//	isReadWrite

	/**
	 *	Set Mandatory (and back bolor)
	 *  @param mandatory mandatory
	 */
	public void setMandatory (boolean mandatory)
	{
		m_text.setMandatory(mandatory);
	}	//	setMandatory

	/**
	 *	Is it mandatory
	 *  @return true if mandatory
	 */
	public boolean isMandatory()
	{
		return m_text.isMandatory();
	}	//	isMandatory

	/**
	 *	Set Background
	 *  @param color color
	 */
	public void setBackground(Color color)
	{
		m_text.setBackground(color);
	}	//	setBackground

	/**
	 *	Set Background
	 *  @param error error
	 */
	public void setBackground (boolean error)
	{
		m_text.setBackground(error);
	}	//	setBackground

	/**
	 *  Set Foreground
	 *  @param fg foreground
	 */
	public void setForeground(Color fg)
	{
		m_text.setForeground(fg);
	}   //  setForeground

	/**
	 *	Set Editor to value
	 *  @param value value
	 */
	public void setValue(Object value)
	{
		log.finest("Value=" + value);
		if (value == null)
			m_oldText = "";
		else
			m_oldText = m_format.format(value);
		//	only set when not updated here
		if (m_setting)
			return;
		m_text.setText (m_oldText);
		m_initialText = m_oldText;
		m_modified = false;
	}	//	setValue

	/**
	 * 	Request Focus
	 */
	public void requestFocus ()
	{
		m_text.requestFocus ();
	}	//	requestFocus
	
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
	 *  @return value value (big decimal or integer)
	 */
	public Object getValue()
	{
		if (m_text == null || m_text.getText() == null || m_text.getText().length() == 0)
			return null;
		String value = m_text.getText();
		//	return 0 if text deleted
		if (value == null || value.length() == 0)
		{
			if (!m_modified)
				return null;
			if (m_displayType == DisplayType.Integer)
				return new Integer(0);
			return Env.ZERO;
		}
		if (value.equals(".") || value.equals(",") || value.equals("-"))
			value = "0";
		// arboleda - solve bug [ 1759771 ] Parse exception when you enter ".." in a numeric field
		if (value.equals("..")) {
			value = "0";
			m_text.setText(".");
		}
		try
		{
			Number number = m_format.parse(value);
			value = number.toString();      //	converts it to US w/o thousands
			BigDecimal bd = new BigDecimal(value);
			if (m_displayType == DisplayType.Integer)
				return new Integer(bd.intValue());
			if (bd.signum() == 0)
				return bd;
			return bd.setScale(m_format.getMaximumFractionDigits(), BigDecimal.ROUND_HALF_UP);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Value=" + value, e);
		}
		m_text.setText(m_format.format(0));
		if (m_displayType == DisplayType.Integer)
			return new Integer(0);
		return Env.ZERO;
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
	 * 	Get Title
	 *	@return title
	 */
	public String getTitle()
	{
		return m_title;
	}	//	getTitle

	/**
	 * 	Plus - add one.
	 * 	Also sets Value
	 *	@return new value
	 */
	public Object plus()
	{
		Object value = getValue();
		if (value == null)
		{
			if (m_displayType == DisplayType.Integer)
				value = new Integer(0);
			else
				value = Env.ZERO;
		}
		//	Add
		if (value instanceof BigDecimal)
			value = ((BigDecimal)value).add(Env.ONE);
		else
			value = new Integer(((Integer)value).intValue() + 1);
		//
		setValue(value);
		return value;
	}	//	plus
	
	/**
	 * 	Minus - subtract one, but not below minimum.
	 * 	Also sets Value
	 *	@param minimum minimum
	 *	@return new value
	 */
	public Object minus (int minimum)
	{
		Object value = getValue();
		if (value == null)
		{
			if (m_displayType == DisplayType.Integer)
				value = new Integer(minimum);
			else
				value = new BigDecimal(minimum);
			setValue(value);
			return value;
		}
		
		//	Subtract
		if (value instanceof BigDecimal)
		{
			BigDecimal bd = ((BigDecimal)value).subtract(Env.ONE);
			BigDecimal min = new BigDecimal(minimum);
			if (bd.compareTo(min) < 0)
				value = min;
			else
				value = bd;
		}
		else
		{
			int i = ((Integer)value).intValue();
			i--;
			if (i < minimum)
				i = minimum;
			value = new Integer(i);
		}
		//
		setValue(value);
		return value;
	}	//	minus
	
	/**************************************************************************
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		log.config(e.getActionCommand());
		if (ValuePreference.NAME.equals(e.getActionCommand()))
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

		if (e.getSource() == m_button )
		{
			m_button.setEnabled(false);
			String str = startCalculator(this, m_text.getText(), m_format, m_displayType, m_title, ' ');
			m_text.setText(str);
			m_button.setEnabled(true);
			try
			{
				fireVetoableChange (m_columnName, m_oldText, getValue());
			}
			catch (PropertyVetoException pve)	{}
			m_text.requestFocus();
		}
	}	//	actionPerformed

	/**************************************************************************
	 *	Key Listener Interface
	 *  @param e event
	 */
	public void keyTyped(KeyEvent e)    {}
	public void keyPressed(KeyEvent e)  {}

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
		
		m_modified = true;
		m_setting = true;
		try
		{
			if (e.getKeyCode() == KeyEvent.VK_ENTER)	//	10
			{
				fireVetoableChange (m_columnName, m_oldText, getValue());
				fireActionPerformed();
			}
			// else	
			// {				
				//	indicate change
				// fireVetoableChange (m_columnName, m_oldText, null);
			// }
		}
		catch (PropertyVetoException pve)	{}
		m_setting = false;
	}	//	keyReleased

	/**
	 *	Focus Gained
	 *  @param e event
	 */
	public void focusGained (FocusEvent e)
	{
		if (m_text != null)
			m_text.selectAll();
	}	//	focusGained

	/**
	 *	Data Binding to MTable (via GridController.vetoableChange).
	 *  @param e event
	 */
	public void focusLost (FocusEvent e)
	{
		//	APanel - Escape
		// hengsin: bug [ 1890205 ]
		/*
		if (e.getOppositeComponent() instanceof AGlassPane)
		{
			m_text.setText(m_initialText);
			return;
		}*/
		commitChanges();
	}   //  focusLost

	public void commitChanges() {
		Object oo = getValue();
		if (m_rangeSet)
		{
			String error = null;
			if (oo instanceof Integer)
			{
				Integer ii = (Integer)oo;
				if (ii  < m_minValue)
				{
					error = oo + " < " + m_minValue;
					oo = new Integer(m_minValue.intValue());
				}
				else if (ii > m_maxValue)
				{
					error = oo + " > " + m_maxValue;
					oo = new Integer(m_maxValue.intValue());
				}
			}
			else if (oo instanceof BigDecimal)
			{
				BigDecimal bd = (BigDecimal)oo;
				if (bd.doubleValue()  < m_minValue)
				{
					error = oo + " < " + m_minValue;
					oo = new BigDecimal(m_minValue);
				}
				else if (bd.doubleValue() > m_maxValue)
				{
					error = oo + " > " + m_maxValue;
					oo = new BigDecimal(m_maxValue);
				}
			}
			if (error != null)
				log.warning(error);
		}
		try
		{
			fireVetoableChange (m_columnName, m_initialText, oo);
			fireActionPerformed();
		}
		catch (PropertyVetoException pve)	
		{}
	}

	/**
	 *	Invalid Entry - Start Calculator
	 *  @param jc parent
	 *  @param value value
	 *  @param format format
	 *  @param displayType display type
	 *  @param title title
	 *  @return value
	 * @deprecated Use {@link #startCalculator(Container,String,DecimalFormat,int,String,char)} instead
	 */
	public static String startCalculator(Container jc, String value,
		DecimalFormat format, int displayType, String title)
	{
		return startCalculator(jc, value, format, displayType, title, ' ');
	}	//	startCalculator

	/**
	 *	Invalid Entry - Start Calculator
	 *  @param jc parent
	 * @param value value
	 * @param format format
	 * @param displayType display type
	 * @param title title
	 * @param operator optional math operator +-/*
	 *  @return value
	 */
	public static String startCalculator(Container jc, String value,
		DecimalFormat format, int displayType, String title, char operator)
	{
		log.config("Value=" + value);
		BigDecimal startValue = new BigDecimal(0.0);
		try
		{
			if (value != null && value.length() > 0)
			{
				Number number = format.parse(value);
				startValue = new BigDecimal (number.toString());
			}
		}
		catch (ParseException pe)
		{
			log.info("InvalidEntry - " + pe.getMessage());
		}
		
		//	Find frame
		Frame frame = Env.getFrame(jc);
		//	Actual Call
		Calculator calc = new Calculator(frame, title,
			displayType, format, startValue);
		if ( "*+-/".indexOf(operator) > -1 )
			calc.handleInput(operator);
		AEnv.showCenterWindow(frame, calc);
		BigDecimal result = calc.getNumber();
		log.config( "Result=" + result);
		//
		calc = null;
		if (result != null)
			return format.format(result);
		else
			return value;		//	original value
	}	//	startCalculator

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
	
	/*
	 * BF [ 1834393 ] VNumber.setFocusable not working
	 */
	@Override
	public void setFocusable(boolean value) {
		m_text.setFocusable(value);
	}

	
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
	 */
	protected void fireActionPerformed()
	{
		int modifiers = 0;
		AWTEvent currentEvent = EventQueue.getCurrentEvent();
		if (currentEvent instanceof InputEvent)
			modifiers = ((InputEvent)currentEvent).getModifiers();
		else if (currentEvent instanceof ActionEvent)
			modifiers = ((ActionEvent)currentEvent).getModifiers();
		ActionEvent ae = new ActionEvent (this, ActionEvent.ACTION_PERFORMED,
			"VNumber", EventQueue.getMostRecentEventTime(), modifiers);

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

	public boolean isDirty() {
		return m_modified;
	}

	public void rollbackChanges() {
		m_text.setText (m_oldText);
		m_initialText = m_oldText;
		m_modified = false;
	}

}	//	VNumber
