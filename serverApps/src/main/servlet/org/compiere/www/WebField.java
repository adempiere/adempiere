/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.www;

import org.apache.ecs.*;
import org.apache.ecs.xhtml.*;
import org.compiere.model.*;
import org.compiere.util.*;

/**
 *	Web Field.
 *	
 *  @author Jorg Janke
 *  @version $Id: WebField.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class WebField
{
	/**
	 * 	Web Field
	 *	@param wsc session context
	 *	@param columnName column
	 *	@param name label
	 *	@param description description
	 *	@param displayType display type
	 *	@param fieldLength field length
	 *	@param displayLength optional display length
	 *	@param longField if true spans 3 columns
	 *	@param readOnly read only
	 *	@param mandatory mandatory
	 *	@param error erro status
	 *	@param hasDependents has dependent fields
	 *	@param hasCallout has callout functions
	 */
	public WebField (WebSessionCtx wsc,
		String columnName, String name, String description,
		int displayType, int fieldLength, int displayLength, boolean longField, 
		boolean readOnly, boolean mandatory, boolean error, 
		boolean hasDependents, boolean hasCallout)
	{
		super ();
		m_wsc = wsc;
		m_columnName = columnName;
		if (name == null || name.length() == 0)
			m_name = columnName;
		else
			m_name = name;
		if (description != null && description.length() > 0)
			m_description = description;
		//
		m_displayType = displayType;
		m_fieldLength = fieldLength;
		m_displayLength = displayLength;
		if (m_displayLength == 0)
			m_displayLength = 20;	//	default length	
		m_longField = longField;
		//
		m_readOnly = readOnly;
		m_mandatory = mandatory;
		m_error = error;
		m_hasDependents = hasDependents;
		m_hasCallout = hasCallout;
		//
		
	}	//	WebField
	
	/**	CSS Field Mandatory Class				*/
	public static final String C_MANDATORY = "Cmandatory";
	/**	CSS Field Error Class					*/
	public static final String C_ERROR     = "Cerror";

	/** 	Web Session Context		*/
	private WebSessionCtx		m_wsc;
	
	private String 	m_columnName;
	private String	m_name;
	private String	m_description;
	//
	private int		m_displayType;
	private int		m_fieldLength;
	private int		m_displayLength;
	private boolean	m_longField;
	//
	private boolean	m_readOnly;
	private boolean	m_mandatory;
	private boolean	m_error;
	private boolean	m_hasDependents;
	private boolean	m_hasCallout;
	
	/**
	 * 	Get the field Label
	 *	@return label
	 */
	public td getLabel()
	{
		if (m_displayType == DisplayType.YesNo)
			return new td(WebEnv.NBSP);
		//
		label myLabel = new label(m_columnName + "F", null, Util.maskHTML(m_name));
		myLabel.setID(m_columnName + "L");
		if (m_description != null)
			myLabel.setTitle(Util.maskHTML(m_description));
		//
		td td = new td()
			.addElement(myLabel)
			.setAlign(AlignType.RIGHT)
			.setVAlign(AlignType.TOP);
		return td;
	}	//	getLabel
	
	/**
	 * 	Create Left Top aligned TD
	 *	@param element element
	 *	@return td table data
	 */
	private td createTD (Element element)
	{
		td td = new td()
			.addElement(element)
			.setAlign(AlignType.LEFT)
			.setVAlign(AlignType.TOP);
		if (m_longField)
			td.setColSpan(3);
		return td;
	}	//	createTD

	
	/**
	 * 	Get Field
	 *	@param lookup lookup
	 *	@param data data
	 *	@return field
	 */
	public td getField (Lookup lookup, Object data)
	{
		String dataValue = (data == null) ? "" : data.toString();
		//
		if (m_displayType == DisplayType.Search
			|| m_displayType == DisplayType.Location
			|| m_displayType == DisplayType.Account)
		{
			String dataDisplay = "";
			if (lookup != null && data != null)
				dataDisplay = lookup.getDisplay(data);
			return getPopupField (dataDisplay, dataValue);
		}
		
		if (DisplayType.isLookup(m_displayType) 
			|| m_displayType == DisplayType.Locator)
			return getSelectField(lookup, dataValue);
		
		if (m_displayType == DisplayType.YesNo)
			return getCheckField (dataValue);

		if (m_displayType == DisplayType.Button)
			return getButtonField ();
		
		if (DisplayType.isDate(m_displayType))
			return getDateField(data);
		else if (DisplayType.isNumeric(m_displayType))
			return getNumberField(data);
		
		//	Strings
		if (m_displayType == DisplayType.Text)
			return getTextField (dataValue, 3);
		else if (m_displayType == DisplayType.TextLong)
			return getTextField (dataValue, 10);
		else if (m_displayType == DisplayType.Memo)
			return getTextField (dataValue, 15);
		return getStringField(dataValue);
	}	//	getField

	
	/**
	 * 	Create String Field
	 * 	@param data initial value
	 *	@return td
	 */
	private td getStringField (String data)
	{
		input string = new input(input.TYPE_TEXT, m_columnName, Util.maskHTML(data));
		string.setID(m_columnName + "F");
		string.setSize(m_displayLength);
		if (m_fieldLength > 0)
			string.setMaxlength(m_fieldLength);
		//
		string.setDisabled(m_readOnly);
		if (m_error)
			string.setClass(C_ERROR);
		else if (m_mandatory)
			string.setClass(C_MANDATORY);
		//
		if (m_hasDependents || m_hasCallout)
			string.setOnChange("startUpdate(this);");
//			string.setOnChange("dynDisplay();");
		//
		return createTD(string);
	}	//	getStringField
	
	/**
	 * 	Create Text Field
	 * 	@param data initial value
	 * 	@param rows no of rows
	 *	@return td
	 */
	private td getTextField (String data, int rows)
	{
		textarea text = new textarea (m_columnName, rows, m_displayLength)
			.addElement(Util.maskHTML(data));
		text.setID(m_columnName + "F");
		text.setDisabled(m_readOnly);
		if (m_error)
			text.setClass(C_ERROR);
		else if (m_mandatory)
			text.setClass(C_MANDATORY);
		//
		if (m_hasDependents || m_hasCallout)
			text.setOnChange("startUpdate(this);");
		//
		return createTD(text);
	}	//	getTextField

	
	/**
	 * 	Create Date Field
	 * 	@param data initial value
	 *	@return td
	 */
	private td getDateField (Object data)
	{
		String formattedData = "";
		if (data == null)
			;
		else if (m_displayType == DisplayType.DateTime)
			formattedData = m_wsc.dateTimeFormat.format(data); 
		else
			formattedData = m_wsc.dateFormat.format(data);

		input string = new input(input.TYPE_TEXT, m_columnName, formattedData);
		string.setID(m_columnName + "F");
		string.setSize(m_displayLength);
		if (m_fieldLength > 0)
			string.setMaxlength(m_fieldLength);
		//
		string.setDisabled(m_readOnly);
		if (m_error)
			string.setClass(C_ERROR);
		else if (m_mandatory)
			string.setClass(C_MANDATORY);
		//
		if (m_hasDependents || m_hasCallout)
			string.setOnChange("startUpdate(this);");
		//
		return createTD(string);
	}	//	getDateField
	
	/**
	 * 	Create Number Field
	 * 	@param data initial value
	 *	@return td
	 */
	private td getNumberField (Object data)
	{
		String formattedData = "";
		if (data == null)
			;
		else if (m_displayType == DisplayType.Amount)
			formattedData = m_wsc.amountFormat.format(data);
		else if (m_displayType == DisplayType.Number
			|| m_displayType == DisplayType.CostPrice)
			formattedData = m_wsc.numberFormat.format(data);
		else if (m_displayType == DisplayType.Quantity)
			formattedData = m_wsc.quantityFormat.format(data);
		else if (m_displayType == DisplayType.Integer)
			formattedData = m_wsc.integerFormat.format(data);
		else
			formattedData = data.toString();
		//
		input string = new input(input.TYPE_TEXT, m_columnName, formattedData);
		string.setID(m_columnName + "F");
		string.setSize(m_displayLength);
		if (m_fieldLength > 0)
			string.setMaxlength(m_fieldLength);
		//
		string.setDisabled(m_readOnly);
		if (m_error)
			string.setClass(C_ERROR);
		else if (m_mandatory)
			string.setClass(C_MANDATORY);
		//
		if (m_hasDependents || m_hasCallout)
			string.setOnChange("startUpdate(this);");
		//
		return createTD(string);
	}	//	getNumberField
	
	
	/**
	 * 	Create Checkbox Field
	 * 	@param data initial value
	 *	@return td
	 */
	private td getCheckField (String data)
	{
		boolean check = data != null 
			&& (data.equals("true") || data.equals("Y"));
		//
		input cb = new input (input.TYPE_CHECKBOX, m_columnName, "true")
			.setChecked(check)
			.addElement(m_name);
		cb.setID(m_columnName + "F");
		cb.setDisabled(m_readOnly);
		if (m_error)
			cb.setClass(C_ERROR);
	//	else if (m_mandatory)             //  looks odd
	//		cb.setClass(C_MANDATORY);
		//
		if (m_hasDependents || m_hasCallout)
			cb.setOnChange("startUpdate(this);");
		//
		return createTD(cb);
	}	//	getCheckField

	/**
	 * 	Get Popup Field (lookup, location, account, ..)
	 *	@param dataDisplay data to be displayed
	 *	@param dataValue data of value field
	 *	@return td
	 */
	private td getPopupField (String dataDisplay, String dataValue)
	{
		//  The hidden data field        Name=columnName
		input hidden = new input (input.TYPE_HIDDEN, m_columnName, dataValue);
		hidden.setID(m_columnName + "D");
		
		//  The display field       Name=columnName, ID=FcolumnName
		input display = new input(input.TYPE_TEXT, m_columnName, Util.maskHTML(dataDisplay));
	//	display.setSize(field.getDisplayLength()).setMaxlength(field.getFieldLength());
		display.setID(m_columnName + "F");
		display.setReadOnly(true);
		
		//  The button              Name=columnName, ID=BcolumnName
		input button = new input (input.TYPE_IMAGE, m_columnName, "x");
		button.setID(m_columnName + "B");
		String gif = "PickOpen10.gif";
		if (m_displayType == DisplayType.Location)
			gif = "Location10.gif";
		else if (m_displayType == DisplayType.Account)
			gif = "Account10.gif";
		else if (m_columnName.equals("C_BPartner_ID"))
			gif = "BPartner10.gif";
		else if (m_columnName.equals("M_Product_ID"))
			gif = "Product10.gif";
		button.setSrc(WebEnv.getImageDirectory(gif));
		button.setBorder(1);
		if (m_displayType == DisplayType.Location)
			button.setOnClick("startLocation('" + m_columnName + "');return false;");
		else if (m_displayType == DisplayType.Account)
			button.setOnClick("startAccount('" + m_columnName + "');return false;");
		else
			button.setOnClick("startLookup('" + m_columnName + "');return false;");
		//
		if (m_error)
			display.setClass(C_ERROR);
		else if (m_mandatory)
			display.setClass(C_MANDATORY);
		//
		if (m_hasDependents || m_hasCallout)
		//	hidden.setOnChange("startUpdate(this);");
			display.setOnChange("startUpdate(this);");
		//
		return createTD(hidden)
			.addElement(display)
			.addElement(button);
	}	//	getPopupField

	/**
	 * 	Get Select Field
	 *	@param lookup lookup
	 *	@param dataValue default value
	 *	@return selction td
	 */
	private td getSelectField (Lookup lookup, String dataValue)
	{
		select sel = new select(m_columnName, getOptions(lookup, dataValue));
		sel.setID(m_columnName);
		sel.setDisabled(m_readOnly);
		if (m_error)
			sel.setClass(C_ERROR);
		else if (m_mandatory)
			sel.setClass(C_MANDATORY);
		//
		if (m_hasDependents || m_hasCallout)
			sel.setOnChange("startUpdate(this);");
		//
		return createTD(sel);
	}	//	getSelectField

	/**
	 * 	Get Array of options
	 *	@param lookup lookup
	 *	@param dataValue default value
	 *	@return selction td
	 */
	private option[] getOptions (Lookup lookup, String dataValue)
	{
		if (lookup == null)
			return new option[0];
		//
		Object[] list = lookup.getData (m_mandatory, true, !m_readOnly, false)
			.toArray();    //  if r/o also inactive
		int size = list.length;
		option[] options = new option[size];
		for (int i = 0; i < size; i++)
		{
			boolean isNumber = list[0] instanceof KeyNamePair;
			String key = null;
			if (m_displayType == DisplayType.Locator)
			{
				MLocator loc = (MLocator)list[i];
				key = String.valueOf(loc.getM_Locator_ID());
				String name = Util.maskHTML(loc.getValue());
				options[i] = new option(key).addElement(name);
			}
			else if (isNumber)
			{
				KeyNamePair p = (KeyNamePair)list[i];
				key = String.valueOf(p.getKey());
				String name = Util.maskHTML(p.getName());
				options[i] = new option(key).addElement(name);
			}
			else
			{
				ValueNamePair p = (ValueNamePair)list[i];
				key = p.getValue();
				if (key == null || key.length() == 0)
					key = "??";
				String name = p.getName();
				if (name == null || name.length() == 0)
					name = "???";
				name = Util.maskHTML(name);
				options[i] = new option(key).addElement(name);
			}
			if (dataValue.equals(key))
				options[i].setSelected(true);
		}
		return options;
	}	//	getOptions

	
	/**
	 * 	Get Button Field
	 *	@return Button 
	 */
	private td getButtonField ()
	{
		input button = new input(input.TYPE_BUTTON, m_columnName, Util.maskHTML(m_name));
		button.setID(m_columnName);
		button.setDisabled(m_readOnly);
		button.setOnClick("startButton(this);");
		return createTD(button)
			.setAlign(AlignType.LEFT);	//	overwrite
	}	//	getButtonField
	
}	//	WebField
