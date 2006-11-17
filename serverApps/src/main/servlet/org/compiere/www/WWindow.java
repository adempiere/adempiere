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

import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.ecs.*;
import org.apache.ecs.xhtml.*;
import org.compiere.model.*;
import org.compiere.util.*;

/**
 *  Web Window Servlet
 *
 *  @author Jorg Janke
 *  @version  $Id: WWindow.java,v 1.3 2006/07/30 00:53:21 jjanke Exp $
 */
public class WWindow extends HttpServlet
{
	/**	Logger			*/
	protected static CLogger	log = CLogger.getCLogger(WWindow.class);
	
	/**
	 *  Initialize global variables
	 *  @param config
	 *  @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init (config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("WWindow.init");
	}   //  init
	
	/**
	 * Get Servlet information
	 * @return info
	 */
	public String getServletInfo()
	{
		return "adempiere Web Window";
	}	//	getServletInfo

	/**
	 * Clean up resources
	 */
	public void destroy()
	{
		log.fine("destroy");
	}   //  destroy

	/** Window Number Counter                   */
	private static int          s_WindowNo  = 1;
	/** Form Name                               */
	protected static final String FORM_NAME   = "WForm";

	/**  Hidden Parameter   Command - Button    */
	private static final String P_Command   = "PCommand";
	/** Hidden Parameter - Tab No               */
	private static final String P_Tab       = "PTab";
	/** Hidden Parameter - MultiRow Row No      */
	private static final String P_MR_RowNo  = "PMRRowNo";
	/** Hidden Parameter - Changed Field for Callout/etc.	*/
	private static final String P_ChangedColumn = "ChangedColumn";

	/** Multi Row Lines per Screen          */
	private static final int    MAX_LINES   = 12;
	/** Indicator for last line             */
	private static final int    LAST_LINE   = 999999;

	/** Error Indicator                     */
	private static final String ERROR       = " ERROR! ";

	/**
	 *  Process the HTTP Get request - Initial Call.
	 *  <br>
	 *  http://localhost/adempiere/WWindow?AD_Window_ID=123
	 *  <br>
	 *  Create Window with request parameters
	 *  AD_Window_ID
	 *  AD_Menu_ID
	 *
	 *  Clean up old/existing window
	 *
	 *  @param request
	 *  @param response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//  Get Session attributes
		HttpSession sess = request.getSession();
		WebSessionCtx wsc = WebSessionCtx.get(request);
		if (wsc == null)
		{
			WebUtil.createTimeoutPage(request, response, this, null);
			return;
		}

		//  Parameter: AD_Window_ID
		int AD_Window_ID = WebUtil.getParameterAsInt(request, "AD_Window_ID");
		//  Get Parameter: Menu_ID
		int AD_Menu_ID = WebUtil.getParameterAsInt(request, "AD_Menu_ID");
		//
		log.info("AD_Window_ID=" + AD_Window_ID
			+ "; AD_Menu_ID=" + AD_Menu_ID);

		//  Clean up old Window
		WWindowStatus ws = WWindowStatus.get(request);
		if (ws != null)
		{
			int WindowNo = ws.mWindow.getWindowNo();
			log.fine("Disposing - WindowNo=" + WindowNo + ", ID=" + ws.mWindow.getAD_Window_ID());
			ws.mWindow.dispose();
			Env.clearWinContext(wsc.ctx, WindowNo);
		}

		/**
		 *  New Window data
		 */
		GridWindowVO mWindowVO = GridWindowVO.create (wsc.ctx, s_WindowNo++, AD_Window_ID, AD_Menu_ID);
		if (mWindowVO == null)
		{
			String msg = Msg.translate(wsc.ctx, "AD_Window_ID") + " "
				+ Msg.getMsg(wsc.ctx, "NotFound") + ", ID=" + AD_Window_ID + "/" + AD_Menu_ID;
			WebUtil.createErrorPage(request, response, this, msg);
			sess.setAttribute(WWindowStatus.NAME, null);
			return;
		}
		//  Create New Window
		ws = new WWindowStatus(mWindowVO);
		sess.setAttribute(WWindowStatus.NAME, ws);


		//  Query
		ws.curTab.query(ws.mWindow.isTransaction());
		ws.curTab.navigate(0);

		/**
		 *  Build Page
		 */
		WebDoc doc = null;
		if (ws.curTab.isSingleRow())
			doc = getSR_Form (request.getRequestURI(), wsc, ws);
		else
			doc = getMR_Form (request.getRequestURI(), wsc, ws);

		//	fini
		log.fine("Fini");
	//	log.trace(log.l6_Database, doc.toString());
		WebUtil.createResponse (request, response, this, null, doc, true);
		log.fine("Closed");
	}   //  doGet


	/**************************************************************************
	 *  Process the HTTP Post request
	 *
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		WebEnv.dump(request);
		//  Get Session Info
	  	WebSessionCtx wsc = WebSessionCtx.get(request);
		WWindowStatus ws = WWindowStatus.get(request);
		if (wsc == null || ws == null)
		{
			if (wsc == null)
				WebUtil.createTimeoutPage(request, response, this, "No Context");
			else
				doGet(request, response);
			return;
		}
		//  Get Parameter: Command
		String p_cmd = WebUtil.getParameter (request, P_Command);
		String column = WebUtil.getParameter (request, P_ChangedColumn);
		log.info("Cmd=" + p_cmd + " - ChangedColumn=" + column);

		//	Changed Column
		if (column != null && column.length() > 0)
		{
			updateFields(request, wsc, ws);
		}
		else	//	Exit & Commands
		{
			if (p_cmd.equals("Exit"))
			{
				MSession cSession = MSession.get(wsc.ctx, false);
				if (cSession != null)
					cSession.logout();
				WebUtil.createLoginPage(request, response, this, ws.ctx, "Exit");
				return;
			}
			executeCommand(request, p_cmd, wsc, ws);
		}
		

		/**************************************************
		 *  Build Page
		 */
		WebDoc doc = null;
		//  Create Simgle/Multi Row
		if (ws.curTab.isSingleRow())
			doc = getSR_Form (request.getRequestURI(), wsc, ws);
		else
			doc = getMR_Form (request.getRequestURI(), wsc, ws);

		//
		log.fine("Fini");
	//	log.trace(log.l6_Database, doc.toString());
		WebUtil.createResponse (request, response, this, null, doc, true);
		log.fine("Closed");
	}   //  doPost

	
	/**************************************************************************
	 *  Execute Command.
	 *
	 *  @param request request
	 *  @param p_cmd command
	 *  @param wsc session context
	 *  @param ws window status
	 */
	private void executeCommand (HttpServletRequest request, 
		String p_cmd, WebSessionCtx wsc, WWindowStatus ws)
	{
		//  Get Parameter: Command and Tab changes
		String p_tab = WebUtil.getParameter (request, P_Tab);
		String p_row = WebUtil.getParameter (request, P_MR_RowNo);    //  MR Row Command
		log.config(p_cmd + " - Tab=" + p_tab + " - Row=" + p_row);

		/**
		 *  Multi-Row Selection (i.e. display single row)
		 */
		if (p_row != null && p_row.length() > 0)
		{
			try
			{
				int newRowNo = Integer.parseInt (p_row);
				ws.curTab.navigate(newRowNo);
				ws.curTab.setSingleRow(true);
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "Parse RowNo="+ p_row, e);
			}
		}

		/**
		 *  Tab Change
		 */
		else if (p_tab != null && p_tab.length() > 0)
		{
			int newTabNo = 0;
			try
			{
				newTabNo = Integer.parseInt (p_tab);
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "Parse TabNo="+ p_tab, e);
			}
			//  move to detail
			if (newTabNo > ws.curTab.getTabNo())
			{
				ws.curTab = ws.mWindow.getTab(newTabNo);
				ws.curTab.query(false);
				ws.curTab.navigate(0);
			}
			//  move back
			else if (newTabNo < ws.curTab.getTabNo())
			{
				ws.curTab = ws.mWindow.getTab(newTabNo);
				ws.curTab.dataRefresh();
			}
		}

		/**
		 *  Multi-Row Toggle
		 */
		else if (p_cmd.equals("Multi"))
		{
			boolean single = ws.curTab.isSingleRow();
			ws.curTab.setSingleRow(!single);
			if (single)
				ws.curTab.navigate(0);
		}

		/**
		 *  Position Commands
		 */
		else if (p_cmd.equals("First"))
		{
			ws.curTab.navigate(0);
		}
		else if (p_cmd.equals("Next"))
		{
			ws.curTab.navigateRelative(+1);     //  multi row is positioned at last displayed row
		}
		else if (p_cmd.equals("Previous"))
		{
			int rows = ws.curTab.isSingleRow() ? -1 : -2*MAX_LINES;
			ws.curTab.navigateRelative(rows);
		}
		else if (p_cmd.equals("Last"))
		{
			ws.curTab.navigateRelative(999999);
		}


		/**
		 *  Find
		 */
		else if (p_cmd.equals("Find"))
		{
			/** @todo Find */
		}

		/**
		 *  Refresh
		 */
		else if (p_cmd.equals("Refresh"))
		{
			ws.curTab.dataRefreshAll();
		}

		/**
		 *  Attachment
		 */
		else if (p_cmd.equals("Attachment"))
		{
			/** @todo Attachment */
		}

		/**
		 *  History
		 */
		else if (p_cmd.equals("History"))
		{
			if (ws.mWindow.isTransaction() && ws.curTab.getWindowNo() == 0)
			{
				ws.curTab.query( !ws.curTab.isOnlyCurrentRows() );
				ws.curTab.navigate(0);
			}
		}

		/**
		 *  Report
		 */
		else if (p_cmd.equals("Report"))
		{
			/** @todo Report */
		}

		/**
		 *  Print
		 */
		else if (p_cmd.equals("Print"))
		{
			/** @todo Print */
		}

		/**
		 *  New
		 */
		else if (p_cmd.equals("New"))
		{
			if (!ws.curTab.dataNew(false))
				ws.curTab.dataIgnore();
		}

		/**
		 *  Delete
		 */
		else if (p_cmd.equals("Delete"))
		{
			ws.curTab.dataDelete();
		}

		/**
		 *  Save - Check for changed values
		 */
		else if (p_cmd.equals("Save"))
		{
			executeSave (request, wsc, ws);
		}
	}   //  executeCommand

	/**
	 *  Execute Save
	 *  @param request request
	 *  @param wsc web session
	 *  @param ws
	 */
	private void executeSave (HttpServletRequest request, WebSessionCtx wsc, WWindowStatus ws)
	{
		log.info("");
		boolean error = updateFields(request, wsc, ws);
		
		//  Check Mandatory
		log.fine("Mandatory check");
		int size = ws.curTab.getFieldCount();
		for (int i = 0; i < size; i++)
		{
			GridField field = ws.curTab.getField(i);
			if (field.isMandatory(true))        //  context check
			{
				Object value = field.getValue();
				if (value == null || value.toString().length() == 0)
				{
					field.setInserting (true);  //  set editable otherwise deadlock
					field.setError(true);
					field.setErrorValue(value == null ? null : value.toString());
					if (!error)
						error = true;
					log.info("Mandatory Error: " + field.getColumnName());
				}
				else
					field.setError(false);
			}
		}

		if (error)
			return;

		//  save it - of errors ignore changes
		if (!ws.curTab.dataSave(true))
			ws.curTab.dataIgnore();
		log.fine("done");
	}   //  executeSave

	/**
	 * 	Update Field Values from Parameter
	 *	@param request request
	 *	@param wsc session context
	 *	@param ws window status
	 *	@return true if error
	 */
	private boolean updateFields(HttpServletRequest request, WebSessionCtx wsc, WWindowStatus ws)
	{
		boolean error = false;
		try
		{
			String enc = request.getCharacterEncoding();
			if (enc == null)
				request.setCharacterEncoding(WebEnv.ENCODING);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Set CharacterEndocung=" + WebEnv.ENCODING, e);
		}
		//  loop through parameters
		Enumeration en = request.getParameterNames();
		while (en.hasMoreElements())
		{
			String key = (String)en.nextElement();
			//  ignore hidden commands
			if (key.equals(P_Command)
				|| key.equals(P_ChangedColumn)
				|| key.equals(P_MR_RowNo) 
				|| key.equals(P_Tab))
				continue;
			GridField mField = ws.curTab.getField(key);
		//	log.fine("executeSave - Key=" + key + " - " + mField);
			//  we found a writable field
			if (mField != null && mField.isEditable(true))
			{
				String value = WebUtil.getParameter(request, key);
				Object dbValue = mField.getValue();
				boolean fieldError = false;
				String columnName = mField.getColumnName();
				log.finest(columnName 
					+ ": " + (dbValue==null ? "null" : dbValue.toString()) 
					+ " -> " + (value==null ? "null" : value.toString()));
					//  same = both null
				if (dbValue == null && value == null)
					continue;
				//   new value null
				else if (dbValue != null && value == null)
					ws.curTab.setValue (mField, null);
				//  from null to new value
				else if (dbValue == null && value != null)
					fieldError = !setFieldValue (wsc, ws, mField, value);
				//  same
				else if (dbValue.equals(value))
					continue;
				else
					fieldError = !setFieldValue (wsc, ws, mField, value);
				//
				if (!error && fieldError)
				{
					log.info("Error: " + mField.getColumnName());
					error = true;
				}
			}
		}   //  for all parameteres
		
		//	Re-Do Changed Column to overwrite
		String columnName = WebUtil.getParameter (request, P_ChangedColumn);
		if (columnName != null && columnName.length() > 0)
		{
			GridField mField = ws.curTab.getField(columnName);
			if (mField != null)
			{
				String value = WebUtil.getParameter(request, columnName);
				Object newValue = getFieldValue (wsc, mField, value);
				if (!ERROR.equals(newValue))
				{
					//	De-Selected Check Boxes are null 
					if (newValue == null && mField.getDisplayType() == DisplayType.YesNo)
						newValue = "N";
					log.fine("ChangedColumn: " + columnName + "=" + newValue);
					ws.curTab.setValue(mField, newValue);
				}
			}
		}
		return error;
	}	//	updateFields

	
	/**************************************************************************
	 *  Set Field Value
	 *  @param wsc web session
	 *  @param ws window status
	 *  @param mField field
	 *  @param value as String
	 *  @return true if correct
	 */
	private boolean setFieldValue (WebSessionCtx wsc, WWindowStatus ws, 
		GridField mField, String value)
	{
		Object newValue = getFieldValue (wsc, mField, value);
		if (ERROR.equals(newValue))
		{
			mField.setErrorValue(value);
			return false;
		}
		Object dbValue = mField.getValue();
		if ((newValue == null && dbValue != null)
				|| (newValue != null && !newValue.equals(dbValue)))
			ws.curTab.setValue(mField, newValue);
		return true;
	}   //  setFieldValue

	/**
	 *  Get Field value (convert value to datatype of MField)
	 *  @param wsc session context
	 *  @param mField field
	 *  @param value String Value
	 *  @return converted Field Value
	 */
	private Object getFieldValue (WebSessionCtx wsc, GridField mField, String value)
	{
		if (value == null || value.length() == 0)
			return null;

		int dt = mField.getDisplayType();
		String columnName = mField.getColumnName();

		//  BigDecimal
		if (DisplayType.isNumeric(dt))
		{
			BigDecimal bd = null;
			try
			{
				Number nn = null;
				if (dt == DisplayType.Amount)
					nn = wsc.amountFormat.parse(value);
				else if (dt == DisplayType.Quantity)
					nn = wsc.quantityFormat.parse(value);
				else	//	 DisplayType.CostPrice
					nn = wsc.numberFormat.parse(value);
				if (nn instanceof BigDecimal)
					bd = (BigDecimal)nn;
				else
					bd = new BigDecimal(nn.toString());
			}
			catch (Exception e)
			{
				log.warning("BigDecimal: " + columnName + "=" + value + ERROR);
				return ERROR;
			}
			log.fine("BigDecimal: " + columnName + "=" + value + " -> " + bd);
			return bd;
		}

		//  ID
		else if (DisplayType.isID(dt))
		{
			Integer ii = null;
			try
			{
				ii = new Integer (value);
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, "ID: " + columnName + "=" + value, e);
				ii = null;
			}
			//  -1 indicates NULL
			if (ii.intValue() == -1)
				ii = null;
			log.fine("ID: " + columnName + "=" + value + " -> " + ii);
			return ii;
		}

		//  Date/Time
		else if (DisplayType.isDate(dt))
		{
			Timestamp ts = null;
			try
			{
				java.util.Date d = null;
				if (dt == DisplayType.Date)
					d = wsc.dateFormat.parse(value);
				else
					d = wsc.dateTimeFormat.parse(value);
				ts = new Timestamp(d.getTime());
			}
			catch (Exception e)
			{
				log.warning("Date: " + columnName + "=" + value + ERROR);
				return ERROR;
			}
			log.fine("Date: " + columnName + "=" + value + " -> " + ts);
			return ts;
		}

		//  Checkbox
		else if (dt == DisplayType.YesNo)
		{
			Boolean retValue = Boolean.FALSE;
			if (value.equals("true"))
				retValue = Boolean.TRUE;
			log.fine("YesNo: " + columnName + "=" + value + " -> " + retValue);
			return retValue;
		}

		//  treat as string
		log.fine(columnName + "=" + value);
		return value;
	}   //  getFieldValue


	/**************************************************************************
	 *	Return SingleRow Form details
	 *  @param action action
	 *  @param wsc web session context
	 *  @param ws window status
	 *  @return Form
	 */
	private WebDoc getSR_Form (String action, WebSessionCtx wsc, WWindowStatus ws)
	{
		log.fine("Tab=" + ws.curTab.getTabNo());

		/**********************
		 *  For all Fields
		 */
		table table = new table()
			.setAlign(AlignType.CENTER);
	//	table.setBorder(1).setBorderColor("#00FF00");	//	debug field lines
		StringBuffer scriptSrc = new StringBuffer();
		//
		tr line = new tr();
		if (ws.curTab.isDisplayed())
		{
			int noFields = ws.curTab.getFieldCount();
			for (int i = 0; i < noFields; i++)
			{
				GridField field = ws.curTab.getField(i);
				String columnName = field.getColumnName();

				/**
				 *  Get Data and convert to String (singleRow)
				 */
				Object oData = ws.curTab.getValue(field);

				/**
				 *  Display field
				 */
				if (field.isDisplayed(true))
				{
					if (!field.isSameLine())
						line = new tr();
					//
					boolean hasDependents = ws.curTab.hasDependants(columnName);
					addField(wsc, line, field, oData, hasDependents);
					table.addElement(line);
					//  Additional Values
					String dispLogic = field.getDisplayLogic();
					if (dispLogic != null && dispLogic.length() > 0)
					{
						dispLogic = dispLogic.replace('\'', '"');   //  replace ' with "
						scriptSrc.append("document.").append(FORM_NAME)
							.append(".").append(columnName)
							.append(".displayLogic='").append(dispLogic).append("';\n");
					}
				}
			}	//	for all fields
		}	//	displayed
		if (scriptSrc.length() > 0)
			table.addElement(new script(scriptSrc.toString()));

		//  Status Line
		int rowNo = ws.curTab.getCurrentRow();
		String statusDB = String.valueOf(rowNo+1) + " # " + ws.curTab.getRowCount();
		//
		return createLayout (action, table, wsc, ws, "", statusDB);
	}	//	getSR_Form


	/**************************************************************************
	 *	Return MultiRow Form details
	 *  @param action action
	 *  @param wsc session context
	 *  @param ws window status
	 *  @return Form
	 */
	private WebDoc getMR_Form (String action, WebSessionCtx wsc, WWindowStatus ws)
	{
		log.fine("Tab=" + ws.curTab.getTabNo());

		int initRowNo = ws.curTab.getCurrentRow();

		/**
		 *  Table Header
		 */
		table table = new table().setAlign(AlignType.CENTER);
		table.setClass("MultiRow");
		table.setBorder(1);
		table.setCellSpacing(1);
		tr line = new tr();
		//  First Column
		line.addElement(new th().addElement(" "));
		//	Tab not displayed
		if (!ws.curTab.isDisplayed())
			return createLayout (action, table, wsc, ws, "", "-");

		int noFields = ws.curTab.getFieldCount();
		//  for all (header) columns
		for (int colNo = 0; colNo < noFields; colNo++)
		{
			GridField field = ws.curTab.getField(colNo);
			if (field.isDisplayed(false))
			{
				th th = new th();
				th.addElement(field.getHeader());           //  Name
				th.setAbbr(field.getDescription());         //  Description
				line.addElement(th);
			}
		}   //  for all columns
		table.addElement(new thead().addElement(line));

		/**
		 *  Table Lines
		 */
		int lastRow = initRowNo + MAX_LINES;
		lastRow = Math.min(lastRow, ws.curTab.getRowCount());
		for (int lineNo = initRowNo; lineNo < lastRow; lineNo++)
		{
			//  Row
			ws.curTab.navigate(lineNo);

			line = new tr();
			//  Selector
			button selector = new button();
			selector.addElement("&gt;");        //  displays ">"
			selector.setOnClick("document." + FORM_NAME + "." + P_MR_RowNo + ".value='" + lineNo + "'; submit();");
			line.addElement(new td().addElement(selector));

			//  for all columns
			for (int colNo = 0; colNo < noFields; colNo++)
			{
				td td = new td();
				//
				GridField field = ws.curTab.getField(colNo);
				if (!field.isDisplayed(false))
					continue;

				//  Get Data - turn to string
				Object data = ws.curTab.getValue(field.getColumnName());
				String info = null;
				//
				if (data == null)
					info = "";
				else
				{
					int dt = field.getDisplayType();
					switch (dt)
					{
						case DisplayType.Date:
							info = wsc.dateFormat.format(data);
							td.setAlign("right");
							break;
						case DisplayType.DateTime:
							info = wsc.dateTimeFormat.format(data);
							td.setAlign("right");
							break;
						case DisplayType.Amount:
							info = wsc.amountFormat.format(data);
							td.setAlign("right");
							break;
						case DisplayType.Number:
						case DisplayType.CostPrice:
							info = wsc.numberFormat.format(data);
							td.setAlign("right");
							break;
						case DisplayType.Quantity:
							info = wsc.quantityFormat.format(data);
							td.setAlign("right");
							break;
						case DisplayType.Integer:
							info = wsc.integerFormat.format(data);
							td.setAlign("right");
							break;
						case DisplayType.YesNo:
							info = Msg.getMsg(ws.ctx, data.toString());
							break;
						/** @todo output formatting 2 */
						default:
							if (DisplayType.isLookup(dt))
								info = field.getLookup().getDisplay(data);
							else
								info = data.toString();
					}
				}

				//  Empty info
				if (info == null || info.length() == 0)
					info = "&nbsp;";                //  Space
				//
				td.addElement(info);
				line.addElement(td);
			}   //  for all columns
			table.addElement(line);
		}   //  for all table lines

		//  Status Line
		String statusDB = String.valueOf(initRowNo+1) + "-" + String.valueOf(lastRow) + " # " + ws.curTab.getRowCount();
		
		return createLayout (action, table, wsc, ws, "", statusDB);
	}	//	getMR_Form

	/**
	 *  Create Window Layout.
	 *  @param action form action
	 *  @param contentTable content table
	 *  @param wsc web session context
	 *  @param ws window status
	 *  @param statusInfo status line info
	 *  @param statusDB status db info
	 *  @return Form
	 */
	private static WebDoc createLayout (String action, table contentTable, 
		WebSessionCtx wsc, WWindowStatus ws, String statusInfo, String statusDB)
	{
		form myForm = null;
		myForm = new form(action);
		myForm.setTarget(WebEnv.TARGET_WINDOW);
		myForm.setID("WWindow" + ws.mWindow.getAD_Window_ID());
		String AD_Language = Env.getAD_Language(ws.ctx);

		//	Window
		myForm.setName(FORM_NAME);
		myForm.addElement(new input("hidden", P_Command, ""));    //  button commands
		myForm.addElement(new input("hidden", P_MR_RowNo, ""));   //  RowNo
		myForm.addElement(new input("hidden", P_ChangedColumn, ""));    //  
		//  Set Title of main window
		String title = ws.mWindow.getName() + " - " + wsc.loginInfo;
		myForm.addElement(new script("top.document.title='" + title + "';"));

		//	Buttons
		td toolbar = new td(null, AlignType.LEFT, AlignType.MIDDLE, true);
		//	Toolbar
		toolbar.addElement(createImage(AD_Language, "Ignore", 
			"reset();", true, false));
		toolbar.addElement("&nbsp;");
		toolbar.addElement(createImage(AD_Language, "Help", 
			"startPopup('WHelp?AD_Window_ID=" + ws.mWindow.getAD_Window_ID() + "');", 
			true, false));
		toolbar.addElement(createImage(AD_Language, "New"));
		toolbar.addElement(createImage(AD_Language, "Delete", 
			"if (confirm(deleteText)) submit();", true, false));
		toolbar.addElement(createImage(AD_Language, "Save"));
		toolbar.addElement("&nbsp;");
		toolbar.addElement(createImage(AD_Language, "Find"));
		toolbar.addElement(createImage(AD_Language, "Refresh"));
		toolbar.addElement(createImage(AD_Language, "Attachment",
			"startPopup('WAttachment');", 
			ws.curTab.canHaveAttachment(), ws.curTab.hasAttachment()));
		toolbar.addElement(createImage(AD_Language, "Multi", null, true, !ws.curTab.isSingleRow()));
		toolbar.addElement("&nbsp;");
		toolbar.addElement(createImage(AD_Language, "History", 
			null, ws.mWindow.isTransaction()&&ws.curTab.getTabNo()==0, !ws.curTab.isOnlyCurrentRows()));
		toolbar.addElement("&nbsp;");
		boolean isFirst = ws.curTab.getCurrentRow() < 1;
		toolbar.addElement(createImage(AD_Language, "First", null, !isFirst, false));
		toolbar.addElement(createImage(AD_Language, "Previous", null, !isFirst, false));
		boolean isLast = ws.curTab.getCurrentRow()+1 == ws.curTab.getRowCount();
		toolbar.addElement(createImage(AD_Language, "Next", null, !isLast, false));
		toolbar.addElement(createImage(AD_Language, "Last", null, !isLast, false));
		toolbar.addElement("&nbsp;");
		toolbar.addElement(createImage(AD_Language, "Report"));
		toolbar.addElement(createImage(AD_Language, "Print"));
		toolbar.addElement("&nbsp;");
		toolbar.addElement(createImage(AD_Language, "Exit"));
		
		//  Tabs
		td tabbar = new td("windowCenter", AlignType.LEFT, AlignType.MIDDLE, false);
		tabbar.addElement(new input(input.TYPE_HIDDEN, P_Tab, ""));
		for (int i = 0; i < ws.mWindow.getTabCount(); i++)
		{
			GridTab tab = ws.mWindow.getTab(i);
			if (tab.isSortTab())
				continue;
			big big = new big(tab.getName());
			if (ws.curTab.getTabNo() == i)
				big.setID("tabSelected");           //  css
			else
			{
				big.setID("tab");                   //  css
				big.setOnClick("alert('" + tab.getName() + "');");
				big.setOnClick("document." + FORM_NAME + "." + P_Tab + ".value='" + i + "';submit();");
			}
			//  Status: Description
			if (tab.getDescription().length() > 0)
				big.setOnMouseOver("status='" + tab.getDescription() + "';return true;");
			tabbar.addElement(big);
		}

		//	Top Table
		table topTable = new table ("0", "0", "5", "100%", null);
		topTable.setID("WWindow.topTable");
		topTable.addElement(new tr(toolbar));
		topTable.addElement(new tr(tabbar));
		myForm.addElement(topTable);
		
		//  Fields
		myForm.addElement(contentTable);

		//  Status Line
		table statusTable = new table ("0", "0", "0", "100%", null);
		topTable.setID("WWindow.statusLine");
		tr statusLine = new tr();
		statusLine.addElement(new td().setWidth("85%").setAlign(AlignType.LEFT)
			.addElement(statusInfo));
		statusLine.addElement(new td().setWidth("10%").setAlign(AlignType.RIGHT)
			.addElement(new small(statusDB)));
		statusLine.addElement(new td().setWidth("5%").setAlign(AlignType.RIGHT)
			.addElement(createImage(AD_Language, "Save")));
		statusTable.addElement(statusLine).setClass("windowCenter");
		myForm.addElement(statusTable);

		//  fini
		/** @todo Dynamic Display */
	//	myForm.addElement(new script("dynDisplay(); createWCmd();"));   //  initial Display & set Cmd Window
		
		WebDoc doc = createPage(ws);
		//	Main Table
		doc.getTable().addElement(new tr()
			.addElement(
				new td(null, AlignType.CENTER, AlignType.MIDDLE, 
					true, myForm).setColSpan(2)));
		//
		return doc;
	}   //  createLayout

	/**
	 *  Create Page.
	 *  - Set Header
	 *  @param ws status
	 *  @return WDoc page
	 */
	private static WebDoc createPage (WWindowStatus ws)
	{
		WebDoc doc = WebDoc.createWindow (ws.mWindow.getName());
		//  Set Variables
		doc.getBody().addElement(
			new script("deleteText='" + Msg.getMsg(ws.ctx, "DeleteRecord?") + "';"));
		//
		return doc;
	}   //  createPage

	
	/**************************************************************************
	 *  Create Image with name, id of button_name and set P_Command onClick
	 *  @param  AD_Language
	 *  @param  name        Name of the Image used also for Name24.gif
	 *  @param  js_command  Java script command, null results in 'submit();', an empty string disables OnClick
	 *  @param  enabled     Enable the immage button, if not uses the "D" image
	 *  @param  pressed     If true, use the "X" image
	 *  @return Image
	 */
	private static img createImage (String AD_Language, String name, String js_command, boolean enabled, boolean pressed)
	{
		StringBuffer imgName = new StringBuffer(name);
		if (!enabled)
			imgName.append("D");
		else if (pressed)
			imgName.append("X");
		imgName.append("24.gif");
		//
		img img = new img (WebEnv.getImageDirectory(imgName.toString()), name);
		if (enabled)
			img.setAlt(Msg.getMsg(AD_Language, name));  //  Translate ToolTip
		//
		if (!pressed || !enabled)
			img.setID("imgButton");                     //  css
		else
			img.setID("imgButtonPressed");              //  css
		//
		if (js_command == null)
			js_command = "submit();";
		if (js_command.length() > 0 && enabled)
			img.setOnClick("document." + FORM_NAME + "." + P_Command + ".value='" + name + "';" + js_command);
		//
		return img;
	}   //  createImage

	/**
	 *  Create enabled Image with name, id of button_name and sumbit command
	 *  @param  AD_Language
	 *  @param  name        Name of the Image used also for Name24.gif
	 *  @return Image
	 */
	private static img createImage (String AD_Language, String name)
	{
		return createImage (AD_Language, name, null, true, false);
	}   //  createImage

	
	/**************************************************************************
	 *	Add Field to Line
	 *  @param wsc session context
	 *  @param line format element
	 *  @param field field
	 *  @param oData original data
	 *  @param hasDependents has Callout function(s)
	 */
	private void addField (WebSessionCtx wsc, tr line, GridField field, 
		Object oData, boolean hasDependents)
	{
		String columnName = field.getColumnName();
		//  Any Error?
		boolean error = field.isErrorValue();
		if (error)
			oData = field.getErrorValue();
		int dt = field.getDisplayType();
		boolean hasCallout = field.getCallout().length() > 0;
		
		/**
		 *  HTML Label Element
		 *      ID = ID_columnName
		 *
		 *  HTML Input Elements
		 *      NAME = columnName
		 *      ID = ID_columnName
		 */
		WebField wField = new WebField (wsc,
			columnName, field.getHeader(), field.getDescription(),
			dt, field.getFieldLength(), field.getDisplayLength(), field.isLongField(),
			// readOnly context check, mandatory no context check,  
			!field.isEditable(true), field.isMandatory(false), error, 
			hasDependents, hasCallout);
		
		line
			.addElement(wField.getLabel())
			.addElement(wField.getField(field.getLookup(), oData));
	}	//	addField

}   //  WWindow
