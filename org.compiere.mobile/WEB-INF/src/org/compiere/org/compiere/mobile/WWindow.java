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
package org.compiere.mobile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.logging.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ecs.xhtml.a;
import org.apache.ecs.xhtml.div;
import org.apache.ecs.xhtml.fieldset;
import org.apache.ecs.xhtml.form;
import org.apache.ecs.xhtml.h1;
import org.apache.ecs.xhtml.h2;
import org.apache.ecs.xhtml.img;
import org.apache.ecs.xhtml.li;
import org.apache.ecs.xhtml.ul;
// todo: chart support import org.compiere.grid.ed.ChartBuilder;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.GridTab;
import org.compiere.model.GridWindowVO;
import org.compiere.model.Lookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;
import org.compiere.util.WebDoc;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUtil;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;

/**
 *  Web Window Servlet
 *
 *  @author Jorg Janke
 *  @version  $Id: WWindow.java,v 1.1 2009/04/15 11:27:15 vinhpt Exp $
 */
public class WWindow extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2565659091166594270L;
	
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
		if (!MobileEnv.initWeb(config))
			throw new ServletException("WWindow.init");
	}   //  init
	
	/**
	 * Get Servlet information
	 * @return info
	 */
	public String getServletInfo()
	{
		return "Adempiere Web Window";
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
		//Modified by Rob Klein 4/29/07

	
	protected static String  sectionNameOld = null;

		private static fieldset fs;
	
	/**  Hidden Parameter   Command - Button    */
	private static final String P_Command   = "PCommand";
	/** Hidden Parameter - Tab No               */
	private static final String P_Tab       = "PTab";
	/** Hidden Parameter - MultiRow Row No      */
	private static final String P_MR_RowNo  = "PMRRowNo";
	/** Hidden Parameter - Changed Field for Callout/etc.	*/
	private static final String P_ChangedColumn = "ChangedColumn";

	/** Multi Row Lines per Screen          */
	// Modified by Rob Klein 4/29/2007
	//private static final int    MAX_LINES   = 12;
	private static final int    MAX_LINES   = 999999999;
	/** Indicator for last line             */
	private static final int    LAST_LINE   = 999999999;

	/** Error Indicator                     */
	private static final String ERROR       = " ERROR! ";

	private String m_searchField;
	private String m_targetWindow;

	private HttpSession sess;
	
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
		MobileDoc doc = null;
		sess = request.getSession();
		MobileSessionCtx wsc = MobileSessionCtx.get(request);
		WWindowStatus ws = WWindowStatus.get(request);
		if (wsc == null)
		{
			MobileUtil.createTimeoutPage(request, response, this, null);
			return;
		}

		String line = request.getParameter("record");
		int lineNo = -1;
		if ( !Util.isEmpty(line))
			lineNo = Integer.parseInt(line);
		if ( lineNo != -1 )
		{
			ws.curTab.navigate(lineNo);
			ws.curTab.setSingleRow(true);
			doc = getSR_Form (request.getRequestURI(), wsc, ws);
			//
			log.fine("Fini");
		//	log.trace(log.l6_Database, doc.toString());
			MobileUtil.createResponse (request, response, this, null, doc, false);
			return;
		}
		

		String action = MobileUtil.getParameter(request, "action");
		String strSQL=MobileUtil.getParameter(request, "txtSQL");
		if ( "edit".equals(action) )
		{
			ws.setRO(false);
			doc = getSR_Form (request.getRequestURI(), wsc, ws);
			//
			log.fine("Fini");
		//	log.trace(log.l6_Database, doc.toString());
			MobileUtil.createResponse (request, response, this, null, doc, false);
			return;
		}
		else if ("insert".equals(action )) 
		{

			if (!ws.curTab.dataNew(false))
				ws.curTab.dataIgnore();
			ws.setRO(false);
			doc = getSR_Form (request.getRequestURI(), wsc, ws);
			//
			log.fine("Fini");
		//	log.trace(log.l6_Database, doc.toString());
			MobileUtil.createResponse (request, response, this, null, doc, false);
			return;
			
		}
		else if ("list".equals(action))
		{
			ws.setRO(true);
			ws.curTab.setSingleRow(false);
			ws.curTab.query(false);
			ws.curTab.navigate(0);
			doc = getMR_Form(request.getRequestURI(), wsc, ws, ws.curTab.getTabLevel() == 0);
			MobileUtil.createResponse (request, response, this, null, doc, false);
			return;
		}
		else if ( !Util.isEmpty(strSQL))
		{
			
			MQuery query=new MQuery();
			if ("FIND".equals(strSQL)) {
					String value=MobileUtil.getParameter(request, "txtValue");
					String docno=MobileUtil.getParameter(request, "txtDocumentNo");
					String name=MobileUtil.getParameter(request, "txtName");
					String desc=MobileUtil.getParameter(request, "txtDescription");

					if (value!=null && value.length()!=0) query.addRestriction("UPPER(Value)", MQuery.LIKE, "%"+value.toUpperCase()+"%");
					if (docno!=null && docno.length()!=0) query.addRestriction("UPPER(DocumentNo)", MQuery.LIKE, "%"+docno.toUpperCase()+"%");
					if (name!=null && name.length()!=0) query.addRestriction("UPPER(Name)", MQuery.LIKE, "%"+name.toUpperCase()+"%");
					if (desc!=null && desc.length()!=0) query.addRestriction("(UPPER(Description", MQuery.LIKE, "%"+desc.toUpperCase()+"%");
			} else {
				query.addRestriction(strSQL);
			}

				ws.setRO(true);
				ws.curTab.setSingleRow(false);
				ws.curTab.setQuery(query);
				ws.curTab.query(false);
				ws.curTab.navigate(0);
				doc = getMR_Form(request.getRequestURI(), wsc, ws, ws.curTab.getTabLevel() == 0);
				MobileUtil.createResponse (request, response, this, null, doc, false);
				return;
			
		}
		else if ("previous".equals(action))
		{
			int curTabLevel = ws.curTab.getTabLevel();
			ws.setRO(true);
			ws.curTab.setSingleRow(true);
			while ( curTabLevel <= ws.curTab.getTabLevel() )
			{
				ws.curTab = ws.mWindow.getTab(ws.curTab.getTabNo()-1);
			}

			ws.curTab.dataRefresh();

			doc = getSR_Form(request.getRequestURI(), wsc, ws);
			MobileUtil.createResponse (request, response, this, null, doc, false);
			return;
		}

		String tab = request.getParameter("tab");
		int tabNo = -1;
		if ( !Util.isEmpty(tab))
			tabNo = Integer.parseInt(tab);
		if ( tabNo != -1)
		{
			ws.mWindow.initTab(tabNo);
			ws.curTab = ws.mWindow.getTab(tabNo);				
			ws.curTab.query(false);
			ws.curTab.navigate(0);
			ws.curTab.setSingleRow(false);
			ws.setRO(true);
			
			doc = getMR_Form (request.getRequestURI(), wsc, ws, false);
			MobileUtil.createResponse(request, response, this, null, doc, false);
			return;
		}

		//  Parameter: AD_Window_ID
		int AD_Window_ID = MobileUtil.getParameterAsInt(request, "AD_Window_ID");
		//  Get Parameter: Menu_ID
		int AD_Menu_ID = MobileUtil.getParameterAsInt(request, "AD_Menu_ID");
		
		
		log.info("AD_Window_ID=" + AD_Window_ID
			+ "; AD_Menu_ID=" + AD_Menu_ID);
		
		String TableName = null;
		//Check to see if Zoom
		int AD_Record_ID = MobileUtil.getParameterAsInt(request, "AD_Record_ID");
		int AD_Table_ID = MobileUtil.getParameterAsInt(request, "AD_Table_ID");
		if (AD_Record_ID != 0 || AD_Table_ID != 0){		
			
			AD_Window_ID = 0;
			int PO_Window_ID = 0;
			String sql = "SELECT TableName, AD_Window_ID, PO_Window_ID FROM AD_Table WHERE AD_Table_ID=?";
			try
			{
				PreparedStatement pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, AD_Table_ID);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
				{
					TableName = rs.getString(1);
					AD_Window_ID = rs.getInt(2);
					PO_Window_ID = rs.getInt(3);
				}
				rs.close();
				pstmt.close();
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			
			if (TableName == null || AD_Window_ID == 0){
				doc = MobileDoc.createPopup ("No Context");			
			}
			
			//	PO Zoom ?
			boolean isSOTrx = true;
			if (PO_Window_ID != 0)
			{
				String whereClause = TableName + "_ID=" + AD_Record_ID;
				isSOTrx = DB.isSOTrx(TableName, whereClause);
				if (!isSOTrx)
					AD_Window_ID = PO_Window_ID;
			}
		}	
		

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
			MobileUtil.createErrorPage(request, response, this, msg);
			sess.setAttribute(WWindowStatus.NAME, null);
			return;
		}
		//  Create New Window
		ws = new WWindowStatus(mWindowVO);
		sess.setAttribute(WWindowStatus.NAME, ws);

		//  Query
		if (AD_Record_ID != 0 || AD_Table_ID != 0){ //If Zoom
			ws.mWindow.initTab(ws.curTab.getTabNo());
			ws.curTab.setQuery(MQuery.getEqualQuery(TableName + "_ID", AD_Record_ID));
			ws.curTab.query(false);
		} else {
			ws.mWindow.initTab(ws.curTab.getTabNo());
			ws.curTab.query(ws.mWindow.isTransaction());
			ws.curTab.navigate(0);
			ws.curTab.setSingleRow(false);
		}

		/**
		 *  Build Page
		 */
		//  Create Single/Multi Row
		if (ws.curTab.isSingleRow())
			doc = getSR_Form (request.getRequestURI(), wsc, ws);
		else
			doc = getMR_Form (request.getRequestURI(), wsc, ws, true);

		ws.setRO(true);
		MobileUtil.createResponse(request, response, this, null, doc, false);
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
		MobileEnv.dump(request);
		//  Get Session Info
	  	MobileSessionCtx wsc = MobileSessionCtx.get(request);
		WWindowStatus ws = WWindowStatus.get(request);
		if (wsc == null || ws == null)
		{
			if (wsc == null)
				MobileUtil.createTimeoutPage(request, response, this, "No Context");
			else
				doGet(request, response);
			return;
		}
		
		//  Get Parameter: Command
		String p_cmd = MobileUtil.getParameter (request, P_Command);
		String column = MobileUtil.getParameter (request, P_ChangedColumn);
		log.info("Cmd=" + p_cmd + " - ChangedColumn=" + column);

		/*
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
		*/
		
		executeSave(request, wsc, ws);

		MobileDoc doc = getSR_Form (request.getRequestURI(), wsc, ws);
		
		MobileUtil.createResponseFragment(request, response, this, null, doc);
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
		String p_cmd, MobileSessionCtx wsc, WWindowStatus ws)
	{
		//  Get Parameter: Command and Tab changes
		String p_tab = MobileUtil.getParameter (request, P_Tab);
		String p_row = MobileUtil.getParameter (request, P_MR_RowNo);    //  MR Row Command
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
				ws.mWindow.initTab(newTabNo);
				ws.curTab = ws.mWindow.getTab(newTabNo);				
				ws.curTab.query(false);
				ws.curTab.navigate(0);
				
				//Modified by Rob Klein 6/01/07 create new record if no record exists
				if (ws.curTab.getRowCount() < 1 ){					
					if (!ws.curTab.dataNew(false))
						ws.curTab.dataIgnore();					
				}
				
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
		
		else if (p_cmd.equals("Find"))
		{
			String strSearch=MobileUtil.getParameter(request, "txtSearch");
			if (strSearch!=null) {
				MQuery query=new MQuery();
				if (strSearch.length()!=0)
					query.addRestriction(m_searchField, MQuery.LIKE, strSearch);
				ws.curTab.setQuery(query);
				ws.curTab.query(false);
				ws.curTab.navigate(0);
			}
		}
		
		else if (p_cmd.equals("FindAdv"))
		{
			
		}
	}   //  executeCommand

	/**
	 *  Execute Save
	 *  @param request request
	 *  @param wsc web session
	 *  @param ws
	 */
	private void executeSave (HttpServletRequest request, MobileSessionCtx wsc, WWindowStatus ws)
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
		else
			ws.setRO(true);
		log.fine("done");
	}   //  executeSave

	/**
	 * 	Update Field Values from Parameter
	 *	@param request request
	 *	@param wsc session context
	 *	@param ws window status
	 *	@return true if error
	 */
	private boolean updateFields(HttpServletRequest request, MobileSessionCtx wsc, WWindowStatus ws)
	{
		boolean error = false;
		try
		{
			String enc = request.getCharacterEncoding();
			if (enc == null)
				request.setCharacterEncoding(MobileEnv.ENCODING);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Set CharacterEncoding=" + MobileEnv.ENCODING, e);
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
				String oldValue = MobileUtil.getParameter(request, key);
				String newValue = MobileUtil.getParameter(request, key + "F");
				String value=null;
				if (newValue!=null) {
					Object val=lookupValue(newValue, mField.getLookup());
					if (val!=null) value=val.toString();
				}
				if (value==null)value=oldValue;
				
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
		}   //  for all parameters
		
		//	Re-Do Changed Column to overwrite
		/*String columnName = WebUtil.getParameter (request, P_ChangedColumn);
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
		}*/
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
	private boolean setFieldValue (MobileSessionCtx wsc, WWindowStatus ws, 
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
	private Object getFieldValue (MobileSessionCtx wsc, GridField mField, String value)
	{
		//Modified by Rob Klein 4/29/07
		//if (value == null || value.length() == 0)
			//return null;
		
		Object defaultObject = null;
		
		int dt = mField.getDisplayType();
		String columnName = mField.getColumnName();
		
		if (value == null || value.length() == 0){			
			defaultObject = mField.getDefault();			
			mField.setValue (defaultObject, true);			
			if (value == null || value.length() == 0 || mField.getValue() == null){
				return null;}
			else
				value = mField.getValue().toString();
		}		
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
			if (ii != null && ii.intValue() == -1)
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
	public MobileDoc getSR_Form (String action, MobileSessionCtx wsc, WWindowStatus ws)
	{
		log.fine("Tab=" + ws.curTab.getTabNo());
		

		form line = new form("WWindow");
		line.addAttribute("selected", "true");
		line.setClass("panel");
		line.setMethod("post");
		line.setTitle(ws.curTab.getName());
		// line.setTarget("_self");
		
		fs = new fieldset();
		line.addElement(fs);

		/**********************
		 *  For all Fields
		 */
		StringBuffer scriptSrc = new StringBuffer();
		MRole role = MRole.getDefault(wsc.ctx, false);
		//
		//Modified by Rob Klein 4/29/07
		m_searchField=null;
		boolean isTabRO = ws.isReadOnlyView();
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
				//Modified by Rob Klein 4/29/07
				/**
				 *  Get Record ID and Table ID for Processes
				 */
				int recordID = ws.curTab.getRecord_ID();
				int tableID = ws.curTab.getAD_Table_ID();
				


				/**
				 *  Display field
				 */
				if (field.isDisplayed(true) && field.getDisplayType() != DisplayType.Button )
				{
					/* todo chart support
					if ( ws.isReadOnlyView() && field.getDisplayType()== 53370 )
					{
						img chart = getChart(field.getAD_Chart_ID(), sess);
						div div = new div();
						div.setClass("row");
						if ( chart != null )
						{
							div.addElement(chart);
							fs.addElement(div);
						}
					}
					else */ {
						//
						boolean hasDependents = ws.curTab.hasDependants(columnName);
						
						//Modified by Rob Klein 4/29/07
						addField(wsc, line, field, oData, hasDependents, recordID, tableID, isTabRO, i, ws.curTab,role);
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
				}
			}	//	for all fields			
			
		}	//	displayed
		
		/*if (scriptSrc.length() > 0)
			table.addElement(new script(scriptSrc.toString()));*/

		//  Status Line
		int rowNo = ws.curTab.getCurrentRow();
		String statusDB = String.valueOf(rowNo+1) + " / " + ws.curTab.getRowCount();
		//
		// return createLayout (action, new ul(), wsc, ws, ws.curTab.getDescription(), statusDB);
		if (  ws.isReadOnlyView() )
		{
			for (int i = ws.curTab.getTabNo()+1; i < ws.mWindow.getTabCount(); i++)
			{
				GridTab tab = ws.mWindow.getTab(i);

				if ( tab.getTabNo() >= ws.curTab.getTabNo()
						&& tab.getTabLevel() <= ws.curTab.getTabLevel() )
					break;  // past all children of curTab

				if ( tab.getTabLevel() != ws.curTab.getTabLevel()+1 )
					continue;   // not direct child

				if (tab.isSortTab())
					continue;

				//Modified by Rob Klein 4/29/07
				a big = new a("WWindow?tab="+i,tab.getName());
				big.setClass("whiteButton");
				big.setTarget("_self");
				line.addElement(big);
			}
		}
		
		if ( !ws.isReadOnlyView() )
		{
			a button = new a("#", "Save");
			button.addAttribute("type", "submit");
			button.setClass("redButton");
			// a.setTarget("_self");
			line.addElement(button);
		}

		MobileDoc doc = createPage(ws);
		

		doc.getBody().addElement(line);
		
		div div = new div();
		div.setClass("toolbar");
		h1 header = new h1();
		header.setID("pageTitle");
		div.addElement(header);
		
		a anchor = new a();
		anchor.setClass("button");
		anchor.setHref(MobileEnv.getBaseDirectory("WMenu"));
		anchor.setTarget("_self");
		anchor.addElement("Menu");
		div.addElement(anchor);

		anchor = new a("WWindow?action=list", "Back");
		anchor.setID("previousButton");
		anchor.setClass("button");
		anchor.setTarget("_self");
		div.addElement(anchor);
		doc.getBody().addElement(div);
		

		if ( !ws.curTab.isReadOnly() && ws.isReadOnlyView() )
		{

			div = new div();
			div.setClass("footer");
			a a = new a("WWindow?action=edit", "Edit");
			a.setClass("redButton");
			a.setTarget("_self");
			div.addElement(a);
			doc.getBody().addElement(div);
		}

		
		return doc;
	}	//	getSR_Form


	/**************************************************************************
	 *	Return MultiRow Form details
	 *  @param action action
	 *  @param wsc session context
	 *  @param ws window status
	 *  @return Form
	 */
	public MobileDoc getMR_Form (String action, MobileSessionCtx wsc, WWindowStatus ws, boolean firstPage)
	{
		log.fine("Tab=" + ws.curTab.getTabNo());

		int initRowNo = ws.curTab.getCurrentRow();
		
		String name = ws.curTab.getName();
		
		int noFields = ws.curTab.getFieldCount();
		
		ul list = new ul();
		list.addAttribute("selected", "true");
		list.setTitle(name);
			
		String idSQL = "SELECT ColumnName, AD_Column_ID from AD_Column" +
				" WHERE AD_Table_ID = " + ws.curTab.getAD_Table_ID() +
				" AND IsIdentifier ='Y' ORDER BY SeqNo";
		ValueNamePair[] idColumns = DB.getValueNamePairs(idSQL, false,null);
		
		String primary = null;
		String secondary = null;
		
		for ( ValueNamePair pair : idColumns )
		{
			if ( primary == null)
			{
				primary = pair.getValue();
			}
			else if ( secondary == null )
			{
				secondary = pair.getValue();
			}
		}
		
		/**
		 * Lines
		 */
		int lastRow = initRowNo + MAX_LINES;
		lastRow = Math.min(lastRow, ws.curTab.getRowCount());
		for (int lineNo = initRowNo; lineNo >= 0 && lineNo < lastRow; lineNo++)
		{
			//  Row
			ws.curTab.navigate(lineNo);

			a anchor = new a();
			anchor.setHref("WWindow?record=" + lineNo );
			anchor.setTarget("_self");
				
			for (int i = 1; i < 3; i++)
			{
				GridField field = null;
				if ( i == 1 && primary != null && primary.length() >  0 )
					field = ws.curTab.getField(primary);
				else if ( secondary != null && secondary.length() > 0 )
					field = ws.curTab.getField(secondary);
				
				if (field == null)
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
							break;
						case DisplayType.DateTime:
							info = wsc.dateTimeFormat.format(data);
							break;
						case DisplayType.Amount:
							info = wsc.amountFormat.format(data);
							break;
						case DisplayType.Number:
						case DisplayType.CostPrice:
							info = wsc.numberFormat.format(data);
							break;
						case DisplayType.Quantity:
							info = wsc.quantityFormat.format(data);
							break;
						case DisplayType.Integer:
							info = wsc.integerFormat.format(data);
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

				if ( i == 1 )
				{
					//  Empty info
					if (info == null || info.length() == 0)
						info = "No Identifier";
					anchor.addElement(info);
				}
				else if ( info != null && info.length() > 0 )
				{
					div d = new div();
					d.setClass("secondary");
					d.addElement(info);
					anchor.addElement(d);
				}
					
				
			}   
			
			li item = new li();
			item.addElement(anchor);
			
			list.addElement(item);
			
		}   
		
		//return createLayout (action, list, wsc, ws, ws.curTab.getDescription(), statusDB);
		MobileDoc doc = createPage(ws);
		//	Main Table
		doc.getBody().addElement(list);

		div div = new div();
		div.setClass("toolbar");
		h1 header = new h1();
		header.setID("pageTitle");
		div.addElement(header);
		
		a anchor = new a();
		anchor.setClass("button");
		anchor.setHref(MobileEnv.getBaseDirectory("WMenu"));
		anchor.setTarget("_self");
		anchor.addElement("Menu");
		div.addElement(anchor);

		if ( !firstPage )
		{
			anchor = new a("WWindow?action=previous", "Back");
			anchor.setID("previousButton");
			anchor.setClass("button");
			anchor.setTarget("_self");
			div.addElement(anchor);
		}
		
		div div2 = new div();
		div2.setClass("footer");
		anchor = new a("WFindAdv", "Find");
		anchor.setID("findButton");
		anchor.setClass("blueButton");
		div2.addElement(anchor);
		
		if ( !ws.curTab.isReadOnly() &&  ws.curTab.isInsertRecord() && ws.isReadOnlyView() )
		{
			a a = new a("WWindow?action=insert", "New record");
			a.setClass("redButton");
			a.setTarget("_self");
			div2.addElement(a);
		}
		
		doc.getBody().addElement(div);
		doc.getBody().addElement(div2);
		return doc;
	}	//	getMR_Form

	/**
	 *  Create Page.
	 *  - Set Header
	 *  @param ws status
	 *  @return WDoc page
	 */
	private static MobileDoc createPage (WWindowStatus ws)
	{
		MobileDoc doc = MobileDoc.createWindow (ws.mWindow.getName());
		return doc;
	}   //  createPage

	
	/**************************************************************************
	 *	Add Field to Line
	 *  @param wsc session context
	 *  @param line format element
	 *  @param field field
	 *  @param oData original data
	 *  @param hasDependents has Callout function(s)
	 */
	public static void addField (MobileSessionCtx wsc, form line, GridField field, 
		Object oData, boolean hasDependents, int recordID, int tableID, boolean tabRO, int fieldNumber,
		GridTab mTab, MRole role)
	{
		String columnName = field.getColumnName();
		//  Any Error?
		boolean error = field.isErrorValue();
		if (error)
			oData = field.getErrorValue();
		int dt = field.getDisplayType();
		boolean hasCallout = field.getCallout().length() > 0;
		//Modified by Rob Klein 4/29/07
		String fieldgroup = field.getFieldGroup();
		if( fieldgroup != null && !fieldgroup.equals(sectionNameOld) && !fieldgroup.equals("") )
		{
			fs = new fieldset();
			line.addElement(new h2(fieldgroup));
			line.addElement(fs);
			sectionNameOld = field.getFieldGroup();
		}
		
		/** Set read only value
		 * 
		 */
		boolean fieldRO = true;
		if (tabRO==true)
			fieldRO = true;
		else
			fieldRO = !field.isEditable(true);
		
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
			//Modified by Rob Klein 4/29/07
			//!field.isEditable(true), field.isMandatory(false), error,
			fieldRO, field.isMandatory(false), error,
			hasDependents, hasCallout,field.getAD_Process_ID(),field.getAD_Window_ID(),
			recordID, tableID, fieldNumber, field.getDefault(), field.getCallout(),
			mTab, field, role);		
		div div = new div();
		div.setClass("row");
		div.addElement(wField.getLabel(!tabRO))			
			.addElement(wField.getField(field.getLookup(), oData));	
		fs.addElement(div);
		
	}	//	addField
 
	private Object lookupValue(String key, Lookup lookup) {
		if ( lookup == null )
			return null;
		
		if (lookup.containsKey(key))
			return lookup.get(key);
		
		return DB.getSQLValueString(null, "SELECT " + lookup.getColumnName() + " FROM " + lookup.getZoomQuery().getTableName() + " WHERE " + lookup.getZoomQuery().getWhereClause() + " AND Value LIKE ?", key);
	}
	

	private img getChart(int chartID, HttpSession session) {
		/* todo chart support
		ChartBuilder chartBuilder = new ChartBuilder( chartID, 0);
		chartBuilder.getChartModel().loadData();
		JFreeChart chart = chartBuilder.createChart();
		


		String filename = null;
		try {
			filename = ServletUtilities.saveChartAsPNG(chart,480, 320, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ( Util.isEmpty(filename))
		{
			return null;
		}

		img image = new img(MobileEnv.getBaseDirectory("/DisplayChart?filename=") + filename, chartBuilder.getChartModel().getName());
		
		return image;
		
		*/
		return null;
	}
	
}   //  WWindow
