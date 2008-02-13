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
	protected static CLogger	log = CLogger.getCLogger(WWindow.class);
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
		boolean hasDependents, boolean hasCallout, int AD_Process_ID,
		int AD_Window_ID, int AD_Record_ID, int AD_Table_ID, int fieldNumber, Object defaultvalue, 
		String callOut, GridTab mTab, GridField mField, MRole mRole)
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
		m_defaultObject = defaultvalue; 
		m_displayType = displayType;
		m_processID = AD_Process_ID;
		m_windowID = AD_Window_ID;
		m_tableID = AD_Table_ID;
		m_recordID = AD_Record_ID;
		m_fieldLength = fieldLength;
		m_displayLength = displayLength;
		if (m_displayLength <= 22)
			m_displayLength = 22;	//	default length
		else
			m_displayLength = 44;	//	default length
		m_longField = longField;
		//
		m_readOnly = readOnly;
		m_mandatory = mandatory;
		m_error = error;
		m_hasDependents = hasDependents;
		m_hasCallout = hasCallout;
		m_callOut = callOut;
		m_fieldNumber = fieldNumber;
		m_Tab = mTab;
		m_Field = mField;
		m_Role = mRole;
		
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
	private String  m_callOut;
	private GridTab m_Tab;
	private GridField m_Field;
	private MRole m_Role;
	//
	private Object	m_defaultObject;
	private int		m_displayType;
	private int		m_processID;
	private int		m_windowID;
	private int		m_tableID;
	private int		m_recordID;
	private int		m_fieldLength;
	private int		m_displayLength;
	private boolean	m_longField;
	//
	private boolean	m_readOnly;
	private boolean	m_mandatory;
	private boolean	m_error;
	private boolean	m_hasDependents;
	private boolean	m_hasCallout;
	private int	m_fieldNumber;
	private Object	m_dataDisplay;
	//Modified by Rob Klein 4/29/07
	
	/**
	 * 	Get the field Label
	 *	@return label
	 */
	public td getLabel()
	{
		if (m_displayType == DisplayType.YesNo||m_displayType == DisplayType.Button)
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
			|| m_displayType == DisplayType.Account
			|| m_displayType == DisplayType.PAttribute)
		{
			String dataDisplay = "";
			if (lookup != null && data != null)
				dataDisplay = lookup.getDisplay(data);
			return getPopupField (dataDisplay, dataValue);
		}
		
		if (DisplayType.isLookup(m_displayType) 
			|| m_displayType == DisplayType.Locator){		
			return getSelectField(lookup, dataValue);}
		
		if (m_displayType == DisplayType.YesNo){			
			return getCheckField (dataValue);}

		if (m_displayType == DisplayType.Button){
			return getButtonField ();
		}
		//Modified by Rob Klein 4/29/07
		if (DisplayType.isDate(m_displayType))
		{
			return getPopupDateField(data);
		}
		else if (DisplayType.isNumeric(m_displayType)){
			return getNumberField(data);
		}
		
		//	Strings
		if (m_displayType == DisplayType.Text){
			return getTextField (dataValue, 3);
		}
		else if (m_displayType == DisplayType.TextLong){
			return getTextField (dataValue, 10);
		}
		else if (m_displayType == DisplayType.Memo){
			return getTextField (dataValue, 15);
		}
		
		//other
		//if (m_displayType == DisplayType.PAttribute){
		//		return getPopupField(dataDisplay, dataValue);}
		
		if (m_displayType == DisplayType.Assignment){
				return getAssignmentField(data);}
		return getStringField(dataValue);
	}	//	getField

	
	/**
	 * 	Create String Field
	 * 	@param data initial value
	 *	@return td
	 */
	private td getStringField (String data)
	{
		input string = null;
		
		Boolean isEncrypted = false;
		
		if(m_Field != null)
			isEncrypted = m_Field.isEncryptedField();
			
		
		if(isEncrypted)
			string = new input(input.TYPE_PASSWORD, m_columnName, Util.maskHTML(data));
		else
			string = new input(input.TYPE_TEXT, m_columnName, Util.maskHTML(data));
		
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
	 * 	Create Assignment Field
	 * 	@param data initial value
	 *	@return td
	 */
	private td getAssignmentField (Object data)
	{
		
		input string = new input(input.TYPE_TEXT, m_columnName, Util.maskHTML(""));
		if (m_fieldLength > 0)
			string.setMaxlength(m_fieldLength);
		//
		string.setDisabled(true);
		if (m_error)
			string.setClass(C_ERROR);
		else if (m_mandatory)
			string.setClass(C_MANDATORY);
		//
		if (m_hasDependents || m_hasCallout)
			string.setOnChange("startUpdate(this);");
		return createTD(string).addElement("Not Yet Supported");
	}
	
	
	/**
	 * 	Create Number Field
	 * 	@param data initial value
	 *	@return td
	 */
	private td getNumberField (Object data)
	{
		String formattedData = "";		
//Modified by Rob Klein 4/29/07
		if (data == null)
			if (m_displayType == DisplayType.Amount	)
				formattedData = m_wsc.amountFormat.format(0.00);
			else if (m_displayType == DisplayType.Number
				|| m_displayType == DisplayType.CostPrice)
				formattedData = m_wsc.numberFormat.format(0.00);
			else if (m_displayType == DisplayType.Integer)
				formattedData = m_wsc.integerFormat.format(0);
			else
				formattedData = "0";
		//else if (m_displayType == DisplayType.Amount)			
				//formattedData = m_wsc.amountFormat.format(data);			
		//else if (m_displayType == DisplayType.Number
			//|| m_displayType == DisplayType.CostPrice)
			//formattedData = m_wsc.numberFormat.format(data);
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
		log.info("0");
		//  The hidden data field        Name=columnName
		input hidden = new input (input.TYPE_HIDDEN, m_columnName, dataValue);
		hidden.setID(m_columnName + "D");
		//Modified by Rob Klein 4/29/07
		input display = null;
		m_dataDisplay = null;
		//  The display field       Name=columnName, ID=FcolumnName		
			display = new input(input.TYPE_TEXT, m_columnName, Util.maskHTML(dataDisplay));
			m_dataDisplay = dataDisplay;
			display.setID(m_columnName + "F");
			display.setReadOnly(true);
		
		//Modified by Rob Klein 4/29/07
		//  The button              Name=columnName, ID=BcolumnName
		input button = new input (input.TYPE_IMAGE, m_columnName+ "B", "x");
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
		//Set PopUp
		button.setSrc(WebEnv.getImageDirectory(gif));
		button.setBorder(1);
		if (m_displayType == DisplayType.Location)
			button.setOnClick("startLocation('" + m_columnName + "');return false;");
		else if (m_displayType == DisplayType.Account)
			//modified by rob klein 4/29/07
			button.setOnClick("startLookup('" + m_columnName + "', "+m_processID+", 1);return false;");
		else			
			//modified by rob klein 4/29/07
			button.setOnClick("startLookup('" + m_columnName + "', "+m_processID+", 1);return false;");
		
		//Start Popup Menu
		//Add by Rob Klein 6/6/2007		
		a buttonFlyout = null;
		String menu = null;		
		
		if(m_Field != null)
		{
			menu = getpopUpMenu ();		
			//	Popmenu
			if(menu!=null){
				buttonFlyout = new a("#", "");
				buttonFlyout.addElement(new img(WebEnv.getImageDirectory("menufly10.gif")).setBorder(0));
				buttonFlyout.setID(m_columnName + "PV");
				buttonFlyout.setOnMouseOver("dropdownmenu(this, event, 'menu1["+m_fieldNumber+"]')");
				buttonFlyout.setOnMouseOut("delayhidemenu()");		
			}
		}
		if (m_error)
			display.setClass(C_ERROR);
		else if (m_mandatory)
			display.setClass(C_MANDATORY);
		//
		if (m_hasDependents || m_hasCallout){
			display.setOnBlur("startUpdate("+m_columnName + "D);");			
		}
		
		//
		if(m_Field != null)
		{
			div popup = new div(menu);
			popup.setClass("anylinkcss");
			popup.setID("menu1["+m_fieldNumber+"]");
			return createTD(hidden)		
			.addElement(display)
			.addElement(button)
			.addElement(buttonFlyout)
			.addElement(popup);						
			}
		else
		{			
			return createTD(hidden)
				.addElement(display)
				.addElement(button);
			}
	}	//	getPopupField
	
	
	/**
	 * 	Get Popup Field (lookup, location, account, ..)
	 *	@param dataDisplay data to be displayed
	 *	@param dataValue data of value field
	 *	@return td
	 */
	private td getPopupDateField ( Object data)
	{
		//  The hidden data field        Name=columnName
		
		String dataValue = (data == null) ? "" : data.toString();	
				
		input hidden = new input (input.TYPE_HIDDEN, m_columnName+"D", dataValue);
		hidden.setID(m_columnName + "D");
		input display = null;
		//  The display field       Name=columnName, ID=FcolumnName
		String formattedData = "";
		if (data == null)
			;
		else if (m_displayType == DisplayType.DateTime){
			if (dataValue.equals("@#Date@"))
				formattedData = m_wsc.dateTimeFormat.format(new java.util.Date());
			else
				formattedData = m_wsc.dateTimeFormat.format(data);			
			}
		else if (m_displayType == DisplayType.Date){
			if (dataValue.equals("@#Date@"))
				formattedData =  m_wsc.dateFormat.format(new java.util.Date());
			else
				formattedData = m_wsc.dateFormat.format(data);			
			}		
		display = new input(input.TYPE_TEXT, m_columnName, formattedData);
		display.setID(m_columnName + "F"+m_fieldNumber);
		display.setReadOnly(true);		
		//  The button              Name=columnName, ID=BcolumnName
		input button = new input (input.TYPE_IMAGE, m_columnName+ "B", "x");
		button.setID(m_columnName + "B");		
		button.setSrc(WebEnv.getImageDirectory("Calendar10.gif"));
		
		
		if (m_displayType == DisplayType.Date){			
			button.setOnClick("return showCalendar('"+m_columnName+ "F"+m_fieldNumber+"', '%m/%d/%Y');");
		}
		else if (m_displayType == DisplayType.DateTime){
			button.setOnClick("showCalendar('"+m_columnName+ "F"+m_fieldNumber+"', '%b %d, %Y %H:%M:%S %p', '24');return false;");
		}
		//
		if (m_error)
			display.setClass(C_ERROR);
		else if (m_mandatory)
			display.setClass(C_MANDATORY);
		//
		if (m_hasDependents || m_hasCallout)
			display.setOnChange("startUpdate(this);");
		//
		return createTD(hidden)
			.addElement(display)
			.addElement(button.setBorder(0));
	}	//	getPopupField

	/**
	 * 	Get Select Field
	 *	@param lookup lookup
	 *	@param dataValue default value
	 *	@return selction td
	 */
	private td getSelectField (Lookup lookup, String dataValue)
	{		
		
		
		if (dataValue.length()<1 && m_defaultObject != null)	{			
			dataValue = m_defaultObject.toString();		
		}		
				
		String dataDisplay = "";
		if (lookup != null && dataValue != null && dataValue.length() > 0)
			dataDisplay = lookup.getDisplay(dataValue);
				
		option[] ops = getOptions(lookup, dataValue);
		select sel = new select(m_columnName, ops);		
		sel.setID(m_columnName);
		sel.setDisabled(m_readOnly);
		
		if (m_error)
			sel.setClass(C_ERROR);
		else if (m_mandatory)
			sel.setClass(C_MANDATORY);
		
		if (m_hasDependents || m_hasCallout)
			sel.setOnChange("startUpdate(this);");
		
		//Start Setting up popUpMenu
		//Add by Rob Klein 6/6/2007
		a buttonFlyout = null;
		String menu = null;		
		
		if(m_Field != null && !m_readOnly)
		{						
			//	Popmenu create
			menu = getpopUpMenu ();
			if(menu!=null){
			buttonFlyout = new a("#", "");
			buttonFlyout.addElement(new img(WebEnv.getImageDirectory("menufly10.gif")).setBorder(0));
			buttonFlyout.setID(m_columnName + "PV");
			buttonFlyout.setOnMouseOver("dropdownmenu(this, event, 'menu1["+m_fieldNumber+"]')");
			buttonFlyout.setOnMouseOut("delayhidemenu()");}
			
		}
		//
		div popup = new div(menu);
		popup.setClass("anylinkcss");
		popup.setID("menu1["+m_fieldNumber+"]");
		return createTD(sel).addElement(buttonFlyout).addElement(popup);	
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
		//boolean keyFound = false;
		//
				
		NamePair value = null;
		Object[] list = lookup.getData (m_mandatory, true, !m_readOnly, false)
			.toArray();    //  if r/o also inactive
		int size = list.length;		
		option[] options = new option[size];
		
		if (size == 0 && dataValue.length()>0){
			
			value = lookup.getDirect(dataValue, false, false);		
			if (value != null){				
				options = new option[2];
				if (dataValue.length()<1){
					options[0] = new option("-1").addElement("&nbsp;").setSelected(true);
					options[1] = new option(value.getID()).addElement(value.getName());
					m_dataDisplay =value.getName();
					}
				else
				{
					options[0] = new option("-1").addElement("&nbsp;");
					options[1] = new option(value.getID()).addElement(value.getName()).setSelected(true);
					m_dataDisplay =value.getName();
					}
			}
			return options;
		}
		
		for (int i = 0; i < size; i++)
		{
			boolean isNumber = list[0] instanceof KeyNamePair;
			String key = null;
			if (m_displayType == DisplayType.Locator)
			{
				MLocator loc = (MLocator)list[i];
				key = String.valueOf(loc.getM_Locator_ID());
				String name = Util.maskHTML(loc.getValue());
				if (dataValue.equals(key)){
					options[i] = new option(key).addElement(name).setSelected(true);
					m_dataDisplay = name;}
				else
					options[i] = new option(key).addElement(name);				
			}
			else if (isNumber)
			{
				KeyNamePair p = (KeyNamePair)list[i];
				key = String.valueOf(p.getKey());
				String name = Util.maskHTML(p.getName());
				if (dataValue.equals(key)){
					options[i] = new option(key).addElement(name).setSelected(true);
					m_dataDisplay =name;}
				else
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
				if (dataValue.equals(key)){
					options[i] = new option(key).addElement(name).setSelected(true);
					m_dataDisplay =name;}
				else
					options[i] = new option(key).addElement(name);
			}			
		}
		//If no key found then default to first value
		//if (!keyFound && size>0)
			//options[0].setSelected(true);
		return options;
	}	//	getOptions

	
	/**
	 * 	Get Button Field
	 *	@return Button 
	 */
	private td getButtonField ()
	{
		//Modified by Rob Klein 4/29/07
		input button = new input("button", m_columnName, "  "+Util.maskHTML(m_name));		
		button.setID(m_columnName);
		button.setClass("processbtn");
		button.setDisabled(m_readOnly);		
		button.setOnClick("startButton("+m_processID+", "+m_windowID+", "+m_recordID+", "+m_tableID+", '"+ m_columnName+"');");
		return createTD(button)
			.setAlign(AlignType.LEFT);	//	overwrite
	}	//	getButtonField
	
	
	/**
	 * 	Get Popup Menu
	 *	@return Menu String 
	 */
	private String getpopUpMenu ()
	{
		//Start Popup Menu
		//Add by Rob Klein 6/6/2007
		a buttonZoom = null;
		a buttonValuePref = null;		
		String menu = null;
		Boolean tableAccess =false;	
		
			if(m_dataDisplay!=null){
			//	Set ValuePreference
			//	Add by Rob Klein 6/6/2007
			buttonValuePref = new a("#", (new img(WebEnv.getImageDirectory("vPreference10.gif")).setBorder(0))+"  Preference");
			buttonValuePref.setID(m_columnName + "PV");			
			buttonValuePref.setOnClick("startValuePref(" + m_displayType + ", '"+Util.maskHTML(m_dataDisplay.toString())+ "', '"
					+ m_Field.getValue()+ "', '"+m_Field.getHeader()+ "', '"+m_Field.getColumnName()+ "', "
					+ Env.getAD_User_ID(m_wsc.ctx)+ ", " + Env.getAD_Org_ID(m_wsc.ctx) + ", "+Env.getAD_Client_ID(m_wsc.ctx)
					+ ", "+m_Field.getAD_Window_ID()+");return false;");
			menu = ""+buttonValuePref+" \n";	
			}
			
			//Set Zoom			
			StringBuffer sql = null;
			int refID = m_Field.getAD_Reference_Value_ID();
			Object recordID =0;
			if (m_displayType == DisplayType.List ){
				sql = new StringBuffer ("SELECT AD_Table_ID " 
						+ "FROM AD_Table WHERE TableName = 'AD_Reference'");				
				
				recordID = refID;
			}
			else if (refID > 0){
				sql = new StringBuffer ("SELECT AD_Table_ID " 
						+ "FROM AD_Ref_Table WHERE AD_Reference_ID = "+refID);
				recordID =m_Field.getValue();
			}
			else{	
				sql = new StringBuffer ("SELECT AD_Table_ID " 
						+ "FROM AD_Table WHERE TableName = '"+m_columnName.replace("_ID", "")+"'");
				recordID =m_Field.getValue();
			}
			int tableID = DB.getSQLValue(null, sql.toString());		
			
			tableAccess = m_Role.isTableAccess(tableID, false);
			if(tableAccess==true){			
				buttonZoom = new a("#", (new img(WebEnv.getImageDirectory("Zoom10.gif")).setBorder(0))+"  Zoom");
				buttonZoom.setID(m_columnName + "Z");			
				buttonZoom.setOnClick("startZoom(" + tableID + ", "+recordID+");return false;");
				if(m_dataDisplay!=null) 
					menu = menu + ""+buttonZoom+"\n";
				else
					menu = ""+buttonZoom+"\n";
					
			}
		return menu;
	}	//	getpopUpMenu
	
}	//	WebField
