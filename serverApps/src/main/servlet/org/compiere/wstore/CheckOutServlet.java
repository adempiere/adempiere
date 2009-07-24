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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.util.CLogger;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUser;


/**
 *  Check Out.
 *
 *  @author Jorg Janke
 *  @version $Id: CheckOutServlet.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class CheckOutServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8593119091754611172L;
	/**	Logging						*/
	private CLogger			log = CLogger.getCLogger(getClass());
	/** Name						*/
	static public final String		NAME = "checkOutServlet";
	/** Attribute					*/
	static public final String		ATTR_CHECKOUT = "CheckOut";

	/**
	 *	Initialize global variables
	 *
	 *  @param config Configuration
	 *  @throws ServletException
	 */
	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("CheckOutServlet.init");
	}   //  init

	/**
	 * Get Servlet information
	 * @return Info
	 */
	public String getServletInfo()
	{
		return "Adempiere Web CheckOut Servlet";
	}	//	getServletInfo

	/**
	 * Clean up resources
	 */
	public void destroy()
	{
		log.fine("destroy");
	}   //  destroy


	/**
	 *  Process the HTTP Get request.
	 * 	(logout, deleteCookie)
	 *  Sends Web Request Page
	 *
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("Get from " + request.getRemoteHost() + " - " + request.getRemoteAddr());
		HttpSession session = request.getSession(true);
		session.removeAttribute(WebSessionCtx.HDR_MESSAGE);

		//	Web User/Basket
		WebUser wu = (WebUser)session.getAttribute(WebUser.NAME);
		WebBasket wb = (WebBasket)session.getAttribute(WebBasket.NAME);

		String url = "/login.jsp";
		//	Nothing in basket
		if (wb == null || wb.getLineCount() == 0)
			url = "/basket.jsp";
		else
		{
			session.setAttribute(ATTR_CHECKOUT, "Y");	//	indicate checkout
			if (wu != null && wu.isLoggedIn ())
				url = "/addressInfo.jsp";
		}

	//	if (request.isSecure())
	//	{
			log.info ("Forward to " + url);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher (url);
			dispatcher.forward (request, response);
	//	}
	//	else
		//	Switch to secure
	//	{
	//		url = "https://" + request.getServerName() + request.getContextPath() + "/" + url;
	//		log.info ("doGet - Secure Forward to " + url);
	//		WUtil.createForwardPage(response, "Secure Access", url);
	//	}
	}	//	doGet

	/**
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
		log.info("Post from " + request.getRemoteHost() + " - " + request.getRemoteAddr());
		HttpSession session = request.getSession(false);
	}	//	doPost

}	//	CheckOutServlet
