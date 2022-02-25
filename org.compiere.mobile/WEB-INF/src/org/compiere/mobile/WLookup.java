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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ecs.xhtml.a;
import org.apache.ecs.xhtml.br;
import org.apache.ecs.xhtml.button;
import org.apache.ecs.xhtml.div;
import org.apache.ecs.xhtml.fieldset;
import org.apache.ecs.xhtml.form;
import org.apache.ecs.xhtml.h1;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.th;
import org.apache.ecs.xhtml.tr;
import org.compiere.mobile.MobileSessionCtx;
import org.compiere.model.GridField;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
/**
 *  WLookup Servlet.
 *  <p>
 *  The servlet is invoked by a parent window via
 *  <code>
 *  WLookup?FormName=formName%ColumnName=columnName
 *  </code>
 *  and assumes that in the opening window/form there are two fields
 *  <code>
 *  opener.document.formName.columnName - The (hidden) field for thcoe ID
 *  opener.document.formName.columnName_D - The display field for the value
 *  </code>
 *  When selecting an entry, the window is closed and the value of the two fields set.
 *
 *  @author Jorg Janke
 *  @version  $Id: WLookup.java,v 1.1 2009/04/15 11:27:15 vinhpt Exp $
 */
public class WLookup extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4835226093865297019L;

	/**	Logger			*/
	protected static CLogger	log = CLogger.getCLogger(WLookup.class);
	
	/** The Data List           */
	protected volatile ArrayList<Object>   p_data = new ArrayList<Object>();
	
	private static final int    MAX_LINES   = 99999;
	private int m_recordCount;
	private int m_colCount;
	private String[] m_searchFields;
	private String[] m_searchLabels;
	
	private StringBuffer m_HeaderSelect = null;
	
	/**
	 * Initialize global variables
	 *
	 * @param config
	 * @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		if (!MobileEnv.initWeb(config))
			throw new ServletException("WLookup.init");
	}   //  init

	/**
	 * Process the HTTP Get request - initial Start
	 * Needs to have parameters FormName and ColumnName
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		MobileEnv.dump(request);
		MobileEnv.dump(request.getSession());
		//Modified by Rob Klein 4/29/07
		//
		MobileSessionCtx wsc = MobileSessionCtx.get(request);
		WWindowStatus ws = WWindowStatus.get(request);		
		
		if (wsc == null)
		{
			MobileUtil.createTimeoutPage(request, response, this, null);
			return;
		}
		
		//  Get Mandatory Parameters
		String columnName = MobileUtil.getParameter (request, "ColumnName");		
		//Lookup called from a process
		//Modified by Rob Klein 4/29/07
		int AD_Process_ID = MobileUtil.getParameterAsInt(request, "AD_Process_ID");
		//Lookup modified to include paging
		//Modified by Rob Klein 07/07/07
		int page = MobileUtil.getParameterAsInt(request, "page");
		
		int refValueId = 0;
		boolean startUpdate = false;
		String targetBase = "'" + columnName;
		String header = null;
		
		if (AD_Process_ID > 0)
		{		
			
			if (AD_Process_ID < 1 || columnName == null 
					|| columnName.equals(""))
				{
					MobileUtil.createErrorPage(request, response, this, 
						Msg.getMsg(wsc.ctx, "ParameterMissing"));
					return;
				}
									
			MProcess process = MProcess.get(wsc.ctx, AD_Process_ID);
			
			MProcessPara para = null;
			
			MProcessPara[] parameters = process.getParameters();
			
			for (int i = 0; i < parameters.length; i++)
			{						
				if (para.getColumnName().equals(columnName))
					para = parameters[i];
			}
			
			if ( para !=null )
				refValueId= para.getAD_Reference_Value_ID();
			
			header = para.getColumnName();			
		}
		//Lookup called from a window
		else{
			//	Modified by Rob Klein 7/01/07
			if (ws == null)
			{
				MobileUtil.createTimeoutPage(request, response, this, null);
				return;
			}			
			GridField mField = ws.curTab.getField(columnName);
			
			log.config("ColumnName=" + columnName 
				+ ", MField=" + mField);
			if (mField == null || columnName == null 
				|| columnName.equals(""))
			{
				MobileUtil.createErrorPage(request, response, this, 
					Msg.getMsg(ws.ctx, "ParameterMissing"));
				return;
			}
	
			header = mField.getHeader();
	
			boolean hasDependents = ws.curTab.hasDependants(columnName);
			boolean hasCallout = mField.getCallout().length() > 0;
			
			startUpdate = hasDependents || hasCallout;
			
			refValueId = mField.getAD_Reference_Value_ID();
		}
		
		if ( m_searchFields == null || m_searchFields.length == 0)
		{
			getSearchFields(columnName, refValueId);
		}
		
		String search = MobileUtil.getParameter (request, "search") ;

		if ( Util.isEmpty(MobileUtil.getParameter (request, "search") ) )
		{
			//  Create Document
			MobileDoc doc =MobileDoc.createPopup (header);	

			form panel=new form();

			panel.setMethod("post");
			panel.setClass("dialog");
			panel.setID("WLookup1");
			panel.setAction("WLookup?ColumnName=" + columnName+"&AD_Process_ID="+AD_Process_ID);
			panel.addAttribute("selected", "true");
			fieldset set = new fieldset();
			h1 h = new h1(header);
			set.addElement(h);
			
			a a = new a("#", "Cancel");
			a.addAttribute("type", "cancel");
			a.setClass("button leftButton");
			set.addElement(a);
			
			a = new a("WLookup?ColumnName=" + columnName+"&AD_Process_ID="+AD_Process_ID, "Search");
			a.addAttribute("type", "submit");
			a.setClass("button");
			set.addElement(a);
			
			panel.addElement(set);

			for (int i = 0; i < m_searchFields.length; i++ )
			{
				/*
				label l = new label();
				l.addElement(m_searchLabels[i]);
				set.addElement(l)*/

				input f = new input(input.TYPE_TEXT, m_searchFields[i], "");
				f.setID(m_searchFields[i]);
				f.addAttribute("placeholder", m_searchLabels[i]);
				set.addElement(f).addElement(new br());
			}
			
			input hidden = new input(input.TYPE_HIDDEN, "search", "true");
			set.addElement(hidden);
			doc.getBody().addElement(panel);				

			MobileUtil.createResponseFragment(request, response, this, null, doc);
		}
		else
		{
			//  Create Document
			MobileDoc doc =MobileDoc.createPopup (header);	
			
			StringBuffer where = new StringBuffer();
			for (String column : m_searchFields)
			{
				String value = request.getParameter(column);
				if (!Util.isEmpty(value)) 
				{
					value = "%" + value + "%";
					where.append(" AND UPPER(")
					.append(column)
					.append(") LIKE UPPER(")
					.append(DB.TO_STRING(value))
					.append(") ");
				}
			}

			div panel=new div();
			panel.setClass("dialog");
			panel.addAttribute("selected", "true");
			panel.setID("WLookup2");
			fieldset set = new fieldset();
			panel.addElement(set);
			set.addElement(fillTable(wsc, columnName, refValueId, request.getRequestURI(),targetBase, startUpdate, page, where.toString()));

			//  Reset
			String text = "Reset";
			//if (wsc.ctx != null)
			//	text = Msg.getMsg (wsc.ctx, "Reset");		
			input resetbtn = new input(input.TYPE_RESET, text, "  "+text);		
			resetbtn.setID(text);
			resetbtn.setClass("resetbtn");			

			String script = targetBase + "F.value='';" + targetBase + "D.value='';self.close();";
			if ( startUpdate )
				script += "startUpdate(" + targetBase + "F);";
			resetbtn.setOnClick(script);

		

			doc.getBody().addElement(panel);				

			doc.getBody()
			.addElement(resetbtn);

			MobileUtil.createResponseFragment (request, response, this, null, doc);
		}
		

		
	}   //  doGet


	private void getSearchFields(String columnName, int refValueId) {
		String sqlSelect = null;
		
		if (refValueId > 0)
			sqlSelect = "SELECT ColumnName, Name FROM AD_Column" +
					" WHERE AD_Table_ID IN (SELECT AD_Table_ID FROM AD_Ref_Table WHERE AD_Reference_ID = " + refValueId + ")" +
					" AND (IsSelectionColumn = 'Y' OR ColumnName LIKE '%Name%') AND AD_Reference_ID IN " +
					" (" + DisplayType.String + "," + DisplayType.Text +")" +
					" ORDER BY SEQNO";			
		else	
			sqlSelect = "SELECT ColumnName, Name FROM AD_Column " +
					" WHERE AD_Table_ID IN (SELECT AD_Table_ID FROM AD_Table WHERE TableName = '" + columnName.replace("_ID", "") + "') " +
					" AND (IsSelectionColumn = 'Y' OR ColumnName LIKE '%Name%') AND AD_Reference_ID IN " +
					" (" + DisplayType.String + "," + DisplayType.Text +")" +
					" ORDER BY SEQNO";
		
		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<String> names = new ArrayList<String>();
		
		try
		{			
			PreparedStatement pstmt = DB.prepareStatement(sqlSelect.toString(), null);			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				columns.add(rs.getString(1));
				names.add(rs.getString(2));
			}
			
			rs.close();
			pstmt.close();
			
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sqlSelect.toString(), e);
		}
		
		m_searchFields = columns.toArray(new String[columns.size()]);
		m_searchLabels = names.toArray(new String[names.size()]);
	}

	/**
	 *  Process the HTTP Post request - perform Get
	 *  @param request
	 *  @param response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		log.config("");
		doGet(request, response);
	}   //  doPost

	//Modified by Rob Klein 4/29/07
	/**************************************************************************
	 *  Fill Table (Generic)
	 *
	 *	@param ws        WindowStatus
	 *	@param mField    the Field
	 *	@param targetBase target field string - add field Type
	 *	@param addStart add startUpdate
	 * @param where 
	 *	@return  Table with selection
	 */
	private table fillTable (MobileSessionCtx wsc, String columnName, int fieldRefId, 
			String action,String targetBase, boolean addStart, int page, String where)
	{
		/**
		if (mField.getColumnName().equals("C_BPartner_ID"))
			return fillTable_BPartner (ws, mField, target);
		else if (mField.getColumnName().equals("M_Product_ID"))
			return fillTable_Product (ws, mField, target);
		**/

		table table = new table("1");	//	Border 1
		table.setClass("itable");
		table.setID("WLookupT");
		table.addElement("<thead>");
		tr line = new tr();	
		line.setClass("header");
				
		
		//  Set Headers
		//line.addElement(new th("&nbsp")).
		//	addElement(new th(Msg.translate(wsc.ctx, "Key Name")).setClass("table-filterable table-filtered table-sortable:default"));																		
		line = fillTable_Lookup_Headers(columnName, fieldRefId, line, targetBase, true, true, true, false, true);		
		
		tr line2 = new tr();
		//line2.addElement(new th("&nbsp")).addElement(new th("&nbsp"));
		line2 = fillTable_Lookup_Headers( columnName, fieldRefId, line2, targetBase, true, true, true, false, false);		

		table.addElement(line);
		table.addElement("</thead>");
		table.addElement("<tbody>");
		
		//  Fillout rows
		table = fillTable_Lookup_Rows(wsc, columnName, fieldRefId, table, targetBase, true, true, true, false, page, where);		
		
		table.addElement("</tbody>");		
		//  Restore				
		return table;
	}   //  fillTable
	
	//Modified by Rob Klein 4/29/07
	/**************************************************************************
	 *  Fill Table Lookup
	 *
	 * @param ws        WindowStatus
	 * @param mField    the Field
	 * @param targetBase    target field string
	 * @return  Lookup Rows in List Format
	 */
	private table fillTable_Lookup_Rows (MobileSessionCtx wsc, String columnName, int fieldRefId,
			table table1, String targetBase, boolean mandatory, boolean onlyValidated, boolean onlyActive,
			boolean temporary, int page, String where)
	{		
		StringBuffer sqlSelect = null;
		StringBuffer sqlCount = null;
		String sql=null;
		String colKey=null;
		String colDisplay=null;
		
		if (fieldRefId > 0){
			sql = "SELECT AD_Table_ID, AD_Key, AD_Display, WhereClause, OrderByClause FROM AD_Ref_Table WHERE AD_Reference_ID = " + fieldRefId;
			
			int tableID = 0;
			
			String whereClause = null;
			String orderBy = null;
			
			try
			{			
				PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);			
				ResultSet rs = pstmt.executeQuery();			
						
				if (rs.next()){					
					tableID = rs.getInt(1);	
					
					whereClause = whereClause + rs.getString(4);
					orderBy = rs.getString(5);
					sql="Select ColumnName FROM AD_Column Where AD_Column_ID = ? AND AD_Table_ID = " + tableID;
					colKey = DB.getSQLValueString(null, sql, rs.getInt(2));
					colDisplay = DB.getSQLValueString(null, sql, rs.getInt(3));
				}
				rs.close();
				pstmt.close();
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, sql.toString(), e);
			}
		
			sql = "Select TableName FROM AD_Table Where AD_Table_ID = ?";
			String tableName = DB.getSQLValueString(null, sql , tableID);		
			sqlSelect=new StringBuffer ( "SELECT " + m_HeaderSelect + " FROM " + tableName + " WHERE AD_Client_ID=?");
			sqlCount=new StringBuffer ( "SELECT count(*) FROM " + tableName + " WHERE AD_Client_ID=?");
			
			if (whereClause != null){
				sqlSelect.append(" AND " + whereClause).append(where);
				sqlCount.append(" AND " + whereClause).append(where);
			}
			if (orderBy != null)
				sqlSelect.append(" ORDER BY " + orderBy);
				
		}
		else{
			sqlSelect=new StringBuffer("SELECT " + m_HeaderSelect + " FROM " + columnName.replace("_ID", "") + " WHERE AD_Client_ID=?");
			sqlCount=new StringBuffer("SELECT count(*) FROM " + columnName.replace("_ID", "") + " WHERE AD_Client_ID=?");

			sqlSelect.append(where);
			sqlCount.append(where);
			colKey=columnName;
			if (m_HeaderSelect.toString().contains("Name"))
				colDisplay="Name";
			else
				colDisplay="Description";
		}		
		try
		{	
			PreparedStatement pstmt = DB.prepareStatement(sqlSelect.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_READ_ONLY, null);
			
			pstmt.setInt(1, Env.getAD_Client_ID(wsc.ctx));						
			ResultSet rs = pstmt.executeQuery();
			log.info("This is the page number "+page);
			log.info("This is the MAX_LINES "+MAX_LINES);
			//rs.absolute(((page-1)*MAX_LINES)+1);			
			
			//for (int j= 1; j<= MAX_LINES; j++){
				while(rs.next()){
					button button = new button();
					button.addElement("&gt;");
					StringBuffer script = new StringBuffer();
					script
						.append("startLookUpdate(").append(targetBase).append("D',")
						.append(targetBase).append("F','").append(rs.getString(colKey)).append("',")
						.append(targetBase).append("D','").append(rs.getString(colDisplay))
						.append("');startUpdate(").append(targetBase).append("');return false;");
					button.setOnClick(script.toString());
				//
					tr line = new tr();
					line.addElement(new td(button));				
					//line.addElement(new td(rs.getString(i)));
					
					for (int i = 1; i <=m_colCount; i++)
					{								
						line.addElement(new td(rs.getString(i)));
					}
					
					table1.addElement(line);
				}
			//}
		
			
			//count
			m_recordCount = DB.getSQLValue(null, sqlCount.toString(),Env.getAD_Client_ID(wsc.ctx));
				
			
			rs.close();
			pstmt.close();
			
		}		
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}		
		return table1;
	}   //  fillTable_Lookup_Rows

	/**
	 *  Fill Table Product
	 *
	 * @param ws        WindowStatus
	 * @param mField    the Field
	 * @param targetBase    target field string
	 * @return  Table with selection
	 */
	private tr fillTable_Lookup_Headers ( String columnName, int fieldRefId,
			tr line, String targetBase, boolean mandatory, boolean onlyValidated, boolean onlyActive,
			boolean temporary, boolean firstHeaderLine)
	{
		
		String sqlSelect = null;	
		input filter = null;
		if (fieldRefId > 0)
			sqlSelect = "SELECT ColumnName, Name FROM AD_Column WHERE AD_Table_ID IN (SELECT AD_Table_ID FROM AD_Ref_Table WHERE AD_Reference_ID = " + fieldRefId + ") ORDER BY SEQNO";			
		else	
			sqlSelect = "SELECT ColumnName, Name FROM AD_Column WHERE AD_Table_ID IN (SELECT AD_Table_ID FROM AD_Table WHERE TableName = '" + columnName.replace("_ID", "") + "') ORDER BY SEQNO";
		
		
		
		if(firstHeaderLine) {
			line.addElement(new th());
			m_HeaderSelect = new StringBuffer(columnName);
			line.addElement(new th(columnName));
			m_colCount=1;
		} 
		
		try
		{			
			PreparedStatement pstmt = DB.prepareStatement(sqlSelect.toString(), null);			
			ResultSet rs = pstmt.executeQuery();
					
			
			String col;
			while (rs.next()){
				col=rs.getString(1);
				if(col.equals("Value")||col.equals("DocumentNo")||col.equals("Name")||col.equals("Description")){
					if(firstHeaderLine){						
						line.addElement(new th(rs.getString(2)));
						m_HeaderSelect.append(",").append(col);
						m_colCount++;
					}
				}
			}
			
			rs.close();
			pstmt.close();
			
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sqlSelect.toString(), e);
		}
		return line;

	}   //  fillTable_Product

}   //  WLookup
