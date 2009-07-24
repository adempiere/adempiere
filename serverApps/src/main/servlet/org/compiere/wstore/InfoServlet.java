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
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.model.MContactInterest;
import org.compiere.model.MInterestArea;
import org.compiere.model.MMailMsg;
import org.compiere.util.CLogger;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUser;
import org.compiere.util.WebUtil;

/**
 *	Web Store Subscription Info.
 *	http://dev2/wstore/infoServlet?mode=subscribe&area=1000002&contact=1000000
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: InfoServlet.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class InfoServlet  extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7753318286119195317L;
	/**	Logging						*/
	private CLogger			log = CLogger.getCLogger(getClass());

	/**
	 * 	Initialize global variables
	 *  @param config servlet configuration
	 *  @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("InfoServlet.init");
	}	//	init

	/**
	 * Get Servlet information
	 * @return Info
	 */
	public String getServletInfo()
	{
		return "Adempiere Interest Area Servlet";
	}	//	getServletInfo

	/**
	 * Clean up resources
	 */
	public void destroy()
	{
		log.info("destroy");
	}   //  destroy

	/*************************************************************************/

	/**
	 *  Process the initial HTTP Get request.
	 *  Reads the Parameter Amt and optional C_Invoice_ID
	 *
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("From " + request.getRemoteHost() + " - " + request.getRemoteAddr());
		HttpSession session = request.getSession(true);
		session.removeAttribute(WebSessionCtx.HDR_MESSAGE);
	//	WEnv.dump(session);
	//	WEnv.dump(request);

		boolean success = processParameter(request);

		String url = "/info.jsp";
		log.info ("Forward to " + url);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher (url);
		dispatcher.forward (request, response);
	}   //  doGet

	/**
	 *  Process the HTTP Post request.
	 *
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("From " + request.getRemoteHost() + " - " + request.getRemoteAddr());
		doGet (request, response);
	}   //  doPost


	/**************************************************************************
	 * 	Process Parameter and check them
	 * 	@param request request
	 *	@return true if processed
	 */
	private boolean processParameter (HttpServletRequest request)
	{
		HttpSession session = request.getSession(true);
		session.removeAttribute(WebSessionCtx.HDR_MESSAGE);
		Properties ctx = JSPEnv.getCtx(request);

		//	mode = subscribe
		String mode = WebUtil.getParameter (request, "mode");
		if (mode == null)
			return false;
		boolean subscribe = !mode.startsWith("un");
		//	area = 101
		int R_InterestArea_ID = WebUtil.getParameterAsInt(request, "area");
		MInterestArea ia = MInterestArea.get(ctx, R_InterestArea_ID);
		//	contact = -1
		int AD_User_ID = WebUtil.getParameterAsInt(request, "contact");
		//
		log.fine("Subscribe=" + subscribe
			+ ",R_InterestArea_ID=" + R_InterestArea_ID
			+ ",AD_User_ID=" + AD_User_ID);
		if (R_InterestArea_ID == 0 || AD_User_ID == 0)
			return false;
		//
		MContactInterest ci = MContactInterest.get (ctx, R_InterestArea_ID, AD_User_ID, false, null);
		ci.subscribe(subscribe);
		boolean ok = ci.save();
		if (ok)
			log.fine("success");
		else
			log.log(Level.SEVERE, "subscribe failed");

		//	Lookup user if direct link
		WebUser wu = (WebUser)session.getAttribute(WebUser.NAME);
		if (wu == null)
		{
			wu = WebUser.get(ctx, AD_User_ID);
			session.setAttribute(WebUser.NAME, wu);
		}
		sendEMail (request, wu, ia.getName(), subscribe);

		return ok;
	}	//	processParameter

	/**
	 * 	Send Subscription EMail.
	 * 	@param request request
	 * 	@param wu web user
	 */
	private void sendEMail (HttpServletRequest request, WebUser wu, 
		String listName, boolean subscribe)
	{
		String msg = JSPEnv.sendEMail(request, wu, 
			subscribe ? MMailMsg.MAILMSGTYPE_Subscribe : MMailMsg.MAILMSGTYPE_UnSubscribe,
			new Object[]{listName, wu.getName(), listName});
	}	//	sendEMail


}	//	InfoServlet
