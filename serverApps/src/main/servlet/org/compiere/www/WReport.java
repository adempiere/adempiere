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
package org.compiere.www;

import java.io.File;
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

import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.PrintInfo;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Trx;
import org.compiere.util.WebDoc;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUtil;


/**
*	Web (window) Report
*	
*  @author Jorg Janke
*  @version $Id: WReport.java,v 1.1 2009/04/15 11:27:15 vinhpt Exp $
*/
public class WReport extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6281182537210181407L;
	
	/**	Logger			*/
	protected CLogger	log = CLogger.getCLogger(getClass());
	/** Current MTab                                    */
	private GridTab			m_curTab;
	/**	The Option List					*/
	private ArrayList<KeyNamePair>	m_list = new ArrayList<KeyNamePair>();
	
	/**
	 * Initialize global variables
	 */
	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("WReport.init");
	}   //  init

	/**
	 * Process the HTTP Get request
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.fine("doGet");
		log.info(response.toString());
		WebSessionCtx wsc = WebSessionCtx.get(request);
		WWindowStatus ws = WWindowStatus.get(request);
		m_curTab = ws.curTab;
		//
		WebDoc doc = null;
		File file = null;
		if (ws == null)
		{
			doc = WebDoc.createPopup("No Context");
			doc.addPopupClose(wsc.ctx);
		}
		/**else if (fileName!=null)
		{			
			int AD_PInstance_ID = WebUtil.getParameterAsInt(request, "AD_PInstance_ID");
			File file = new File (fileName);
			String error = WebUtil.streamFile(response, file);
			if (error == null)
				return;
			doc = WebDoc.createWindow(error);
		}**/
		else{
			log.info("");
			if (!MRole.getDefault().isCanReport(ws.curTab.getAD_Table_ID()))
			{				
				doc = WebDoc.createPopup("Access Cannot Report");
				doc.addPopupClose(wsc.ctx);				
			}			
			

			//	Query
			MQuery query = new MQuery(m_curTab.getTableName());
			//	Link for detail records
			String queryColumn = m_curTab.getLinkColumnName();
			//	Current row otherwise
			if (queryColumn.length() == 0)
				queryColumn = m_curTab.getKeyColumnName();
			//	Find display
			String infoName = null;
			String infoDisplay = null;
			for (int i = 0; i < m_curTab.getFieldCount(); i++)
			{
				GridField field = m_curTab.getField(i);
				if (field.isKey())
					infoName = field.getHeader();
				if ((field.getColumnName().equals("Name") || field.getColumnName().equals("DocumentNo") )
					&& field.getValue() != null)
					infoDisplay = field.getValue().toString();
				if (infoName != null && infoDisplay != null)
					break;
			}
			if (queryColumn.length() != 0)
			{
				if (queryColumn.endsWith("_ID"))
					query.addRestriction(queryColumn, MQuery.EQUAL,
						new Integer(Env.getContextAsInt(wsc.ctx, m_curTab.getWindowNo(), queryColumn)),
						infoName, infoDisplay);
				else
					query.addRestriction(queryColumn, MQuery.EQUAL,
						Env.getContext(wsc.ctx, m_curTab.getWindowNo(), queryColumn),
						infoName, infoDisplay);
			}
			
			file = getPrintFormats (m_curTab.getAD_Table_ID(), request,  m_curTab, query);			
			String error = WebUtil.streamFile(response, file);
			if (error == null)
				return;
			doc = WebDoc.createWindow(error);			
		}
			
		//
		WebUtil.createResponse(request, response, this, null, doc, false);
	}   //  doGet


	/**
	 *  Process the HTTP Post request
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		WebDoc doc = WebDoc.createPopup ("Report - Post - Not Implemented");
		WebUtil.createResponse(request, response, this, null, doc, false);
	}   //  doPost

	/**
	 * 	Get the Print Formats for the table.
	 *  Fill the list and the popup menu
	 * 	@param AD_Table_ID table
	 *  @param invoker component to display popup (optional)
	 */

	private File getPrintFormats (int AD_Table_ID,HttpServletRequest request, GridTab	m_curTab, MQuery m_query )
	{
		WebSessionCtx wsc = WebSessionCtx.get(request);
		WWindowStatus ws = WWindowStatus.get(request);
		int AD_Client_ID = Env.getAD_Client_ID(wsc.ctx);
		File file = null;
		m_list =  new ArrayList<KeyNamePair>();
		//
		String sql = MRole.getDefault().addAccessSQL (
			"SELECT AD_PrintFormat_ID, Name, AD_Client_ID "
				+ "FROM AD_PrintFormat "
				+ "WHERE AD_Table_ID=? AND IsTableBased='Y' "
				+ "ORDER BY AD_Client_ID DESC, IsDefault DESC, Name",		//	Own First
			"AD_PrintFormat", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		KeyNamePair pp = null;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Table_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				pp = new KeyNamePair (rs.getInt(1), rs.getString(2));
				if (rs.getInt(3) == AD_Client_ID)
				{
					m_list.add(pp);
					//m_popup.add(pp.toString()).addActionListener(this);
				}
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}

		//	No Format exists - create it
		if (m_list.size() == 0)
		{
			if (pp == null)
				 file = createNewFormat (AD_Table_ID,request, m_curTab, m_query);		//	calls launch
			else
				file = copyFormat(pp.getKey(), AD_Client_ID,request, m_curTab, m_query);
		}
		//	One Format exists or no invoker - show it
		//else if (m_list.size() == 1 || invoker == null)
		else
			file = launchReport ((KeyNamePair)m_list.get(0),request, m_curTab, m_query);
		//TODO Display Multiple Report List
		//	Multiple Formats exist - show selection
		//else if (invoker.isShowing())
			//m_popup.show(invoker, 0, invoker.getHeight());	//	below button
		return file;
	}	//	getPrintFormats

	/**
	 * 	Create and Launch new Format for table
	 * 	@param AD_Table_ID table
	 */
	private File createNewFormat (int AD_Table_ID, HttpServletRequest request,  GridTab m_curTab, MQuery m_query)
	{
		WebSessionCtx wsc = WebSessionCtx.get(request);
		MPrintFormat pf = MPrintFormat.createFromTable(wsc.ctx, AD_Table_ID);
		File file = launchReport (pf,request, m_curTab, m_query);
		return file;
	}	//	createNewFormat

	/**
	 * 	Copy existing Format
	 * 	@param AD_PrintFormat_ID print format
	 * 	@param To_Client_ID to client
	 */
	private File copyFormat (int AD_PrintFormat_ID, int To_Client_ID, HttpServletRequest request,  GridTab	m_curTab, MQuery m_query)
	{
		WebSessionCtx wsc = WebSessionCtx.get(request);
		MPrintFormat pf = MPrintFormat.copyToClient(wsc.ctx, AD_PrintFormat_ID, To_Client_ID);
		File file = launchReport (pf,request, m_curTab, m_query);
		return file;
	}	//	copyFormatFromClient

	/**
	 * 	Launch Report
	 * 	@param pp Key=AD_PrintFormat_ID
	 */
	private File launchReport (KeyNamePair pp, HttpServletRequest request,  GridTab	m_curTab,MQuery m_query)
	{
		WebSessionCtx wsc = WebSessionCtx.get(request);		
		MPrintFormat pf = MPrintFormat.get(wsc.ctx, pp.getKey(), false);
		File file = launchReport (pf, request, m_curTab, m_query);
		return file;
	}	//	launchReport

	/**
	 * 	Launch Report
	 * 	@param pf print format
	 */
	private File launchReport (MPrintFormat pf, HttpServletRequest request,  GridTab	m_curTab, MQuery m_query)
	{
		int Record_ID = 0;
		
		WebSessionCtx wsc = WebSessionCtx.get(request);
		WWindowStatus ws = WWindowStatus.get(request);
		//Instance pInstance = new MPInstance (wsc.ctx, 0, 0);
		
		File fileName = null;
		
				
		if (m_query.getRestrictionCount()==1 && m_query.getCode(0) instanceof Integer){		
			Record_ID = ((Integer)m_query.getCode(0)).intValue();
			}
		PrintInfo info = new PrintInfo(
			pf.getName(),
			pf.getAD_Table_ID(),
			Record_ID);
		info.setDescription(m_query.getInfo());
		if(pf != null && pf.getJasperProcess_ID() > 0)
		{
			// It's a report using the JasperReports engine
			ProcessInfo pi = new ProcessInfo ("", pf.getJasperProcess_ID());
			Trx trx = Trx.get(Trx.createTrxName("WebPrc"), true);
			//	Execute Process
			WProcessCtl.process(this, m_curTab.getAD_Window_ID(), pi, trx, request);		
		}
		else
		{
			// It's a default report using the standard printing engine
			ReportEngine re = new ReportEngine (wsc.ctx, pf, m_query, info);			
			if (re == null)
			{
				log.info("Could not start ReportEngine");				
			}
			else
			{
				try
				{
					File file = File.createTempFile("WProcess", ".pdf");
					boolean ok = re.createPDF(file);
					if (ok)
					{						
						fileName =  file;
					}
					else{		
						log.info("Could not create Report");
					}
				}
				catch (Exception e)
				{
					log.info(e.toString());					
				}
			}
		}		
		//doc.addPopupClose(wsc.ctx);		
		
	//	if (m_popup.isVisible())
	//		m_popup.setVisible(false);
		
		return fileName;
	}	//	launchReport

	/**************************************************************************
	 * 	Get AD_Table_ID for Table Name
	 * 	@param TableName table name
	 * 	@return AD_Table_ID or 0
	 */
	static public int getAD_Table_ID (String TableName)
	{
		int AD_Table_ID = 0;
		String sql = "SELECT AD_Table_ID FROM AD_Table WHERE TableName=?";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, TableName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				AD_Table_ID = rs.getInt(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			//log.log(Level.SEVERE, sql, e);
		}
		return AD_Table_ID;
	}	//	getAD_Table_ID

}	//	WReport
