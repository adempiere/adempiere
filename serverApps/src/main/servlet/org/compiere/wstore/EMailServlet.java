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
package org.compiere.wstore;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.util.CLogger;
import org.compiere.util.WebEnv;
import org.compiere.util.WebLogin;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUtil;

/**
 * 	EMail Servlet
 *  @author Jorg Janke
 *  @version $Id: EMailServlet.java,v 1.4 2006/09/24 12:11:16 comdivision Exp $
 */
public class EMailServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6237307729376702569L;

	/**	Logging						*/
	private static CLogger			log = CLogger.getCLogger(EMailServlet.class);

	/** Name						*/
	static public final String		NAME = "emailServlet";

	/**
	 * Initialize global variables
	 *
	 * @param config servlet config
	 * @throws ServletException
	 */
	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("EMailServlet.init");
	}   //  init

	/**
	 * Get Servlet information
	 * @return Info
	 */
	public String getServletInfo()
	{
		return "Adempiere EMail";
	}	//	getServletInfo

	/**
	 * Clean up resources
	 */
	public void destroy()
	{
		log.info("");
	}   //  destroy

	
	/**************************************************************************
	 *  Process the HTTP Get request
	 *
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doGet (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("Get from " + request.getRemoteHost() + " - " + request.getRemoteAddr());
		doPost (request, response);
	}   //  doGet

	/**
	 *  Process the HTTP Post request
	 *
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doPost (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("Post from " + request.getRemoteHost() + " - " + request.getRemoteAddr());
		Properties ctx = JSPEnv.getCtx(request);
		HttpSession session = request.getSession(true);
		session.removeAttribute(WebSessionCtx.HDR_MESSAGE);

		String url = WebUtil.getParameter(request, "ForwardTo");
		if (url == null || url.length() == 0)
			url = "emailVerify.jsp";
		
		WebLogin thisLogin = new WebLogin(request, response, ctx);
		thisLogin.init ();
		thisLogin.setMode("EMailVerify");
		if (!thisLogin.action ()) 
		{
			if (!response.isCommitted ())
			response.sendRedirect("loginServlet?ForwardTo=" + url);
			return;
		}
		
		url = "/" + url;
		log.info ("Forward to " + url);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher (url);
		dispatcher.forward (request, response);
	}   //  doPost
}	//	EMailServlet
