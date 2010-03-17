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

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.model.GridWindowVO;
import org.compiere.model.MQuery;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.WebDoc;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUtil;


/**
 *	HTML Zoom Window
 *	
 *  @author Rob Klein
 *  @version $Id: WZoom.java,v 1.1 2009/04/15 11:27:15 vinhpt Exp $
 */
public class WZoom extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7075820778158654964L;
	
	/**	Logger			*/
	private static CLogger	log = CLogger.getCLogger(WAttachment.class);

	/**
	 * Initialize global variables
	 */
	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("WZoom.init");
	}   //  init

	public static final String  P_Record_ID	= "AD_Record_ID";
	public static final String  P_Table_ID	= "AD_Table_ID";
	private static int          s_WindowNo  = 3;
	
	/**
	 * 	Process the HTTP Get request.
	 * 	Initial display and streaming 
	 *  @param request request
	 *  @param response response
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("From " + request.getRemoteHost() + " - " + request.getRemoteAddr());
		HttpSession session = request.getSession(false);
		WWindowStatus ws = WWindowStatus.get(request);
		WebDoc doc = null;
		if (session == null || ws == null)
		{
			doc = WebDoc.createPopup ("No Context");
			doc.addPopupClose(ws.ctx);
		}
		else
		{
			String error = null;
			int AD_Record_ID = WebUtil.getParameterAsInt(request, P_Record_ID);
			int AD_Table_ID = WebUtil.getParameterAsInt(request, P_Table_ID);
			if (AD_Record_ID == 0 || AD_Table_ID == 0)
			{
				doc = WebDoc.createPopup ("Invalid Record ID or Table ID");
				doc.addPopupClose(ws.ctx);
			}
			else
			{
				
			
			doc = createPage (ws.ctx, request,AD_Record_ID,
					AD_Table_ID);
			}
		}
		//
		WebUtil.createResponse(request, response, this, null, doc, false);
	}   //  doGet


	/**
	 *  Process the HTTP Post request.
	 *  Update Attachment
	 *  @param request request
	 *  @parem response response
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		
		HttpSession sess = request.getSession(false);
		WWindowStatus ws = WWindowStatus.get(request);
		WebDoc doc = null;
		//		
			doc = WebDoc.create ("Help - Post Not Implemented");
				//
		WebUtil.createResponse(request, response, this, null, doc, false);
	}   //  doPost

	/**
	 * 	Create Attachment Page
	 * 	@param ctx context
	 *	@param AD_Attachment_ID id for existing attachment
	 *	@param AD_Table_ID table for new attachment
	 *	@param Record_ID record for new attachment
	 *	@param error optional error message
	 *	@return WebDoc
	 */
	public static WebDoc createPage (Properties ctx, HttpServletRequest request, int AD_Record_ID,
		int AD_Table_ID)
	{
		
		//WebDoc doc = WebDoc.createPopup (Msg.translate(ctx, "AD_Attachment_ID"));		
		WebDoc doc = null;
		String TableName = null;
		int AD_Window_ID = 0;
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
			doc = WebDoc.createPopup ("No Context");
			doc.addPopupClose(ctx);			
			return doc;
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
		
		/**
		 *  New Window data
		 */
		WWindowStatus ws = WWindowStatus.get(request);
		HttpSession sess = request.getSession();
		WebSessionCtx wsc = WebSessionCtx.get(request);
		
		if (ws != null)
		{		
			int WindowNo = ws.mWindow.getWindowNo();
			log.fine("Disposing - WindowNo=" + WindowNo + ", ID=" + ws.mWindow.getAD_Window_ID());
			ws.mWindow.dispose();
			Env.clearWinContext(wsc.ctx, WindowNo);		
		}

		GridWindowVO mWindowVO = GridWindowVO.create (ctx, s_WindowNo++, AD_Window_ID, 0);
		
		if (mWindowVO == null)
		{
			
			String msg = Msg.translate(ctx, "AD_Window_ID") + " "
				+ Msg.getMsg(ctx, "NotFound") + ", ID=" + AD_Window_ID + "/" + 0;
			doc = WebDoc.createPopup (msg);
			doc.addPopupClose(ctx);			
			return doc;			
		}
		
		//  Create New Window
		ws = new WWindowStatus(mWindowVO);
		sess.setAttribute(WWindowStatus.NAME, ws);
		
		//  Query
		
		ws.mWindow.initTab(ws.curTab.getTabNo());
		ws.curTab.setQuery(MQuery.getEqualQuery(TableName + "_ID", AD_Record_ID));
		ws.curTab.query(false);
		
		//ws.curTab.navigate(0);

		/**
		 *  Build Page
		 */
		
		//doc = WWindow.getSR_Form (request.getRequestURI(), wsc, ws);
		

		return doc;
	}	//	createPage
	
	
}	//	WZoom
