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

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;

import org.compiere.apps.AEnv;
import org.compiere.swing.CButton;
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
 * 
 *  @author Michael McKay, 
 * 		<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 */
public final class VNumber extends VEditorAbstract
	implements ActionListener, VManagedEditor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1473227631807352033L;

	/**
	 * Define the ui class ID. It will be added to the 
	 * look and feel to define the ui class to use.
	 */
	private final static String uiClassID = "NumberUI";

	@Override
    public String getUIClassID() {
        return uiClassID ;
    }


	/** Automatically pop up calculator */
	public final static boolean AUTO_POPUP = false;
	
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
		this(columnName, mandatory, isReadOnly, isUpdateable, displayType, title, false);
	}
	
	/**
	 *	Create right aligned Number field.
	 *	no popup, if WindowNo == 0 (for IDs)
	 *  @param columnName column name
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param displayType display type
	 *  @param title title
	 *  @param tableCellEditor true if the editor will be used in a table cell
	 */
	public VNumber(String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		int displayType, String title, boolean tableCellEditor)
	{
		super(columnName, mandatory, isReadOnly, isUpdateable, tableCellEditor);
		
		editor = (CTextField) getEditorComponent();
		button = getButtonComponent();
		
		m_title = title;
		setDisplayType(displayType);
		
	}	//	VNumber

	protected int			m_displayType;	//  Currency / UoM via Context
	private DecimalFormat	m_format;
	private String			m_title;

	private boolean			m_rangeSet = false;
	private Double			m_minValue;
	private Double			m_maxValue;
	
	/**  The Field                  */
	private CTextField		editor;
	/** The Button                  */
	private CButton		    button;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VNumber.class);


	
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
		editor.setDocument (new MDocNumber(displayType, m_format, editor, m_title));
	}   //  setDisplayType

	/**
	 *  Return Display Value
	 *  @return value
	 */
	public String getDisplay()
	{
		return editor.getText();
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
		log.fine(e.getActionCommand());

		if (e.getSource() == button )
		{
			button.setEnabled(false);
			String str = startCalculator(this, editor.getText(), m_format, m_displayType, m_title, ' ');
			editor.setText(str);
			button.setEnabled(true);
		}
	}	//	actionPerformed

	private Object convertTextToValue() {
		
		if (editor == null || editor.getText() == null || editor.getText().length() == 0)
			return null;
		
		String value = editor.getText();
			
		if (value.equals(".") || value.equals(",") || value.equals("-"))
			value = "0";
		// arboleda - solve bug [ 1759771 ] Parse exception when you enter ".." in a numeric field
		if (value.contains("..")) {
			value = value.replace("..", ".");
			editor.setText(value);
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
		
		editor.setText(m_format.format(0));
		if (m_displayType == DisplayType.Integer)
			return new Integer(0);
		
		return Env.ZERO;
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
		if ( "*+-/%".indexOf(operator) > -1 )
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
	
	@Override
	protected Object getCurrentValue() {
		
		String error = null;

		Object oo = convertTextToValue();
		
		if (oo == null)
		{
			// Set the error if the editor is mandatory, otherwise
			// pass the null value as valid
			error = this.isMandatory() ? "Mandatory" : null;
		}
		else if (m_rangeSet)
		{
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
		} // oo not null and m_RangeSet == true
		
		if (error != null)
		{
			setCurrentValueValid(false);
			log.warning(error);
		}
		else
			setCurrentValueValid(true);

		return oo;
	}

	@Override
	protected String setDisplayBasedOnValue(Object value) {
		
		String text;
		if (value == null)
			text = "";
		else
			text = m_format.format(value);
		
		editor.setText (text);
		
		if (!editor.isEditable() || !editor.isEnabled())
			editor.setCaretPosition(0);
		else
			editor.selectAll();  // User input will overwrite the current value
		
		return editor.getText();
	}

	@Override
	public void setEditable(boolean editable) {
		if (editor != null)
			editor.setEditable(editable);
		if (button != null)
			button.setEnabled(editable);
	}

	@Override
	public boolean isEditable() {
		
		if (editor != null)
			return editor.isEditable();
		return false;
	}
	
	@Override
	protected void handleInvalidValue() {
		button.setEnabled(false);
		String str = startCalculator(this, editor.getText(), m_format, m_displayType, m_title, ' ');
		editor.setText(str);
		button.setEnabled(true);
	}

}	//	VNumber
