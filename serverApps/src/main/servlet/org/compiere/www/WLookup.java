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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.ecs.AlignType;
import org.apache.ecs.xhtml.*;
import org.compiere.grid.ed.VLookup;
import org.compiere.model.*;
import org.compiere.util.*;

/**
 *  WLookup Servlet.
 *  <p>
 *  The servlet is invoked by a parent window via
 *  <code>
 *  WLookup?FormName=formName%ColumnName=columnName
 *  </code>
 *  and assumes that in the opening window/form there are two fields
 *  <code>
 *  opener.document.formName.columnName - The (hidden) field for the ID
 *  opener.document.formName.columnName_D - The display field for the value
 *  </code>
 *  When selecting an entry, the window is closed and the value of the two fields set.
 *
 *  @author Jorg Janke
 *  @version  $Id: WLookup.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class WLookup extends HttpServlet
{
	/**	Logger			*/
	protected static CLogger	log = CLogger.getCLogger(WLookup.class);
	
	/** The Data List           */
	protected volatile ArrayList<Object>   p_data = new ArrayList<Object>();
	
	
	private StringBuffer HeaderSelect = null;
	
	/**
	 * Initialize global variables
	 *
	 * @param config
	 * @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
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
		WebEnv.dump(request);
		WebEnv.dump(request.getSession());
		//Modified by Rob Klein 4/29/07
		//
		WebSessionCtx wsc = WebSessionCtx.get(request);
		WWindowStatus ws = WWindowStatus.get(request);		
		
		if (wsc == null)
		{
			WebUtil.createTimeoutPage(request, response, this, null);
			return;
		}
		
		//  Get Mandatory Parameters
		String columnName = WebUtil.getParameter (request, "ColumnName");
		
		//Lookup called from a process
		//Modified by Rob Klein 4/29/07
		int AD_Process_ID = WebUtil.getParameterAsInt(request, "AD_Process_ID");		
		
		if (AD_Process_ID > 0)
		{		
			
			if (AD_Process_ID < 1 || columnName == null 
					|| columnName.equals(""))
				{
					WebUtil.createErrorPage(request, response, this, 
						Msg.getMsg(wsc.ctx, "ParameterMissing"));
					return;
				}
			String targetBase = "'" + columnName;
									
			MProcess process = MProcess.get(wsc.ctx, AD_Process_ID);
			
			MProcessPara para = null;
			
			MProcessPara[] parameter = process.getParameters();
			
			for (int i = 0; i < parameter.length; i++)
			{						
				para = parameter[i];
			
				if (para.getColumnName().equals(columnName))
					i=parameter.length;
			}
			
			//  Create Document
			WebDoc doc = WebDoc.createPopup (para.getColumnName());			
			
			//  Reset
			String text = "Reset";
			if (wsc.ctx != null)
				text = Msg.getMsg (wsc.ctx, "Reset");
			
			input button = new input("button", text, "  "+text);		
			button.setID(text);
			button.setClass("resetbtn");			
			String script = targetBase + "F.value='';" + targetBase + "D.value='';closePopup();";			
			button.setOnClick(script);
			//
			
			doc.getTable().addElement(new tr()
				.addElement(fillTable(wsc, para.getColumnName(), para.getAD_Reference_Value_ID(), request.getRequestURI(),targetBase,false))
				.addElement(button));
			//
			doc.addPopupClose(wsc.ctx);
		//	log.trace(log.l6_Database, doc.toString());
			WebUtil.createResponse (request, response, this, null, doc, false);			
		}
		//Lookup called from a window
		else{
			//	Modified by Rob Klein 7/01/07
			if (ws == null)
			{
				WebUtil.createTimeoutPage(request, response, this, null);
				return;
			}			
			GridField mField = ws.curTab.getField(columnName);
			
			log.config("ColumnName=" + columnName 
				+ ", MField=" + mField);
			if (mField == null || columnName == null 
				|| columnName.equals(""))
			{
				WebUtil.createErrorPage(request, response, this, 
					Msg.getMsg(ws.ctx, "ParameterMissing"));
				return;
			}
			//	parent = framesetWindow
			//	Label - Dtata - Field - Button
			String targetBase = "'" + columnName;
	
			//  Create Document
			WebDoc doc = WebDoc.createPopup (mField.getHeader());
	
			boolean hasDependents = ws.curTab.hasDependants(columnName);
			boolean hasCallout = mField.getCallout().length() > 0;
			
			//  Reset
//			 Reset
			String text = "Reset";
			if (wsc.ctx != null)
				text = Msg.getMsg (wsc.ctx, "Reset");		
			input restbtn = new input(input.TYPE_RESET, text, "  "+text);		
			restbtn.setID(text);
			restbtn.setClass("resetbtn");			
			
			String script = targetBase + "F.value='';" + targetBase + "D.value='';self.close();";
			if (hasDependents || hasCallout)
				script += "startUpdate(" + targetBase + "F);";
			restbtn.setOnClick(script);
			//
			
			doc.getTable().addElement(new tr()
				.addElement(fillTable(wsc, mField.getColumnName(), mField.getAD_Reference_Value_ID(), request.getRequestURI(),targetBase, hasDependents || hasCallout))
				.addElement(restbtn));
			//
			doc.addPopupClose(ws.ctx);
		//	log.trace(log.l6_Database, doc.toString());
			WebUtil.createResponse (request, response, this, null, doc, false);
		}
	}   //  doGet


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
	 *	@return  Table with selection
	 */
	private table fillTable (WebSessionCtx wsc, String columnName, int fieldRefId, 
			String action,String targetBase, boolean addStart)
	{
		/**
		if (mField.getColumnName().equals("C_BPartner_ID"))
			return fillTable_BPartner (ws, mField, target);
		else if (mField.getColumnName().equals("M_Product_ID"))
			return fillTable_Product (ws, mField, target);
		**/

		table table = new table("1");	//	Border 1
		table.setID("WLookup");		
		table.setClass("MultiRow table-autofilter table-filterable table-autosort table-autostripe table-stripeclass:alternate");
		table.addElement("<thead>");
		tr line = new tr();	
				
		
		//  Set Headers
		line.addElement(new th("&nbsp")).
			addElement(new th(Msg.translate(wsc.ctx, "Key Name")).setClass("table-filterable table-filtered table-sortable:default"));																		
		line = fillTable_Lookup_Headers(columnName, fieldRefId, line, targetBase, true, true, true, false, true);		
		table.addElement(line);
		tr line2 = new tr();
		line2.addElement(new th("&nbsp")).addElement(new th("&nbsp"));
		line2 = fillTable_Lookup_Headers( columnName, fieldRefId, line2, targetBase, true, true, true, false, false);		
		table.addElement(line2);
		table.addElement("</thead>");
		table.addElement("<tbody>");
		
		//  Fillout rows
		table = fillTable_Lookup_Rows(wsc, columnName, fieldRefId, table, targetBase, true, true, true, false);		
		
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
	private table fillTable_Lookup_Rows (WebSessionCtx wsc, String columnName, int fieldRefId,
			table table1, String targetBase, boolean mandatory, boolean onlyValidated, boolean onlyActive,
			boolean temporary)
	{		
		ArrayList<Object> list = new ArrayList<Object>();		
		StringBuffer sqlSelect = null;
		StringBuffer sqlSelectDetail = null;
		
		int size = 0;
		int colCount = 0;
		
		if (!mandatory)
			list.add(new KeyNamePair (-1, ""));		
		
		StringBuffer sql = null;	
		
		if (fieldRefId > 0){
			sql = new StringBuffer ("SELECT AD_Display, AD_Key, AD_Table_ID, WhereClause, OrderByClause " 
					+ "FROM AD_Ref_Table WHERE AD_Reference_ID = "+fieldRefId);
			
			int nameID = 0;
			int keyID = 0;
			int tableID = 0;
			String whereClause = null;
			String orderBy = null;	
			try
			{			
				PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);			
				ResultSet rs = pstmt.executeQuery();			
				size=2;
						
				while (rs.next()){					
					nameID = rs.getInt(1);
					keyID = rs.getInt(2);
					tableID = rs.getInt(3);					
					whereClause = rs.getString(4);
					orderBy = rs.getString(5);
				}
		
				String sql1 = "Select ColumnName FROM AD_Column Where AD_Column_ID = ? AND AD_Table_ID = "+tableID;				
				sql = new StringBuffer ("SELECT count(Name) FROM AD_Column WHERE AD_TABLE_ID="+tableID);
				colCount = DB.getSQLValue(null, sql.toString());
		
				String name = DB.getSQLValueString(null, sql1 , nameID);				
				String key = DB.getSQLValueString(null, sql1 , keyID);				
				sqlSelect = new StringBuffer ("SELECT "+key+", "+name);
		
				rs.close();
				pstmt.close();
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, sqlSelect.toString(), e);
			}
		
			String sql1 = "Select TableName FROM AD_Table Where AD_Table_ID = ?";
			String tableName = DB.getSQLValueString(null, sql1 , tableID);		
			sqlSelect.append(" FROM " + tableName + " WHERE AD_Client_ID=?");			
			sqlSelectDetail = HeaderSelect.append(" FROM " + tableName + " WHERE "+columnName+" = ?");
					
			if (whereClause != null){
				sqlSelect.append(" AND " + whereClause);
				sqlSelectDetail.append(" AND " + whereClause);
			}
			
			if (orderBy != null){
				sqlSelect.append(" ORDER BY " + orderBy);
				sqlSelectDetail.append(" ORDER BY " + orderBy);
			}
		}
		else{
		
			//direct select as indicated below
			sql = new StringBuffer ("SELECT AD_Table_ID " 
					+ "FROM AD_Table WHERE TableName = '"+columnName.replace("_ID", "")+"'");
			int tableID = DB.getSQLValue(null, sql.toString());
		
			sql = new StringBuffer ("SELECT count(Name) FROM AD_Column WHERE AD_TABLE_ID="+tableID);
			colCount = DB.getSQLValue(null, sql.toString());
		
			sql = new StringBuffer ("SELECT ColumnName "
					+ "FROM AD_Column WHERE IsIdentifier = 'Y' AND " 
					+ "AD_Table_ID= "+tableID
					+ " ORDER BY SeqNo");
		
			sqlSelect = new StringBuffer ("SELECT "+columnName+", ");
			size=1;
			
			try
			{		
				PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);			
				ResultSet rs = pstmt.executeQuery();
		
				while (rs.next()){				
					if (size>1)
						sqlSelect.append(", ");
					sqlSelect.append(rs.getObject(1));
					size++;				
				}			
				rs.close();
				pstmt.close();
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, sqlSelect.toString(), e);
			}
		
			sqlSelect.append(" FROM " + columnName.replace("_ID", "") + " WHERE AD_Client_ID=?");
			sqlSelectDetail  = HeaderSelect.append(" FROM " + columnName.replace("_ID", "") + " WHERE "+columnName+" = ?");
		
			}		
		try
		{		
			PreparedStatement pstmt = DB.prepareStatement(sqlSelect.toString(), null);			
			pstmt.setInt(1, Env.getAD_Client_ID(wsc.ctx));
			ResultSet rs = pstmt.executeQuery();
		
			while (rs.next()){
				StringBuffer name = new StringBuffer ("");
				for (int i = 2; i <= size; i++)
				{
					if (i>2)
						name.append("_");
					name.append(rs.getObject(i));
				}				
				button button = new button();
				button.addElement("&gt;");
				StringBuffer script = new StringBuffer();
				script
					.append("startLookUpdate(").append(targetBase).append("F',")
					.append(targetBase).append("D','").append(rs.getObject(1).toString()).append("',")
					.append(targetBase).append("F','").append(name.toString())
					.append("');startUpdate(").append(targetBase).append("');return false;");
				button.setOnClick(script.toString());
				//
				tr line = new tr();
				line.addElement(new td(button));				
				line.addElement(new td(name.toString()));
				try
				{						
					PreparedStatement pstmt1 = DB.prepareStatement(sqlSelectDetail.toString(), null);			
					pstmt1.setInt(1, rs.getInt(1));		
					ResultSet rs1 = pstmt1.executeQuery();		
					while (rs1.next()){						
						for (int i = 1; i <= colCount; i++)
						{
							Object fieldRS = rs1.getObject(i);
							if (fieldRS == null)
								line.addElement(new td("&nbsp;"));
							else
								line.addElement(new td(rs1.getObject(i).toString()));					
						}
					}
					rs1.close();
					pstmt1.close();					
						
				}			
				
			catch (SQLException e)
				{
					log.log(Level.SEVERE, sql.toString(), e);
				}			
			table1.addElement(line);			
			}
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
		
		StringBuffer sqlSelect = null;
		int size = 0;		
		StringBuffer sql = null;
		int colCount = 0;
		if (fieldRefId > 0){
			
			sql = new StringBuffer ("SELECT AD_Table_ID " 
					+ "FROM AD_Ref_Table WHERE AD_Reference_ID = "+fieldRefId);
			int tableID = DB.getSQLValue(null, sql.toString());
							
			sql = new StringBuffer ("SELECT count(Name) FROM AD_Column WHERE AD_TABLE_ID="+tableID);
			colCount = DB.getSQLValue(null, sql.toString());
			sqlSelect = new StringBuffer ("SELECT ColumnName, Name FROM AD_Column WHERE AD_Table_ID="+tableID+" ORDER BY AD_Column_ID");			
			
		}
		else{	
		//direct select as indicated below
			
			sql = new StringBuffer ("SELECT AD_Table_ID " 
					+ "FROM AD_Table WHERE TableName = '"+columnName.replace("_ID", "")+"'");
			int tableID = DB.getSQLValue(null, sql.toString());			
			sql = new StringBuffer ("SELECT count(Name) FROM AD_Column WHERE AD_TABLE_ID="+tableID);
			colCount = DB.getSQLValue(null, sql.toString());			
			sqlSelect = new StringBuffer ("SELECT ColumnName, Name FROM AD_Column WHERE AD_TABLE_ID="+tableID+" ORDER BY AD_Column_ID");
			
		}
		
		if(firstHeaderLine)
			HeaderSelect = new StringBuffer ("Select ");		
		
		try
		{			
			PreparedStatement pstmt = DB.prepareStatement(sqlSelect.toString(), null);			
			ResultSet rs = pstmt.executeQuery();
						
			input filter = null;
			while (rs.next()){
					if(firstHeaderLine){						
						line.addElement(new th(rs.getString(2)).setClass("table-filterable table-filtered table-sortable:default"));
						HeaderSelect.append(rs.getString(1)+",");
					}
					else{						
						th th = new th();
						filter = new input (input.TYPE_TEXT, rs.getString(2)+"filter", "");
						filter.setOnKeyUp("Table.filter(this,this)");
						th.addElement(filter);				
						line.addElement(th);
					}
			}
			
			if(firstHeaderLine)
				HeaderSelect.setLength(HeaderSelect.length()-1);
			
			rs.close();
			pstmt.close();
			
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		return line;

	}   //  fillTable_Product

}   //  WLookup
