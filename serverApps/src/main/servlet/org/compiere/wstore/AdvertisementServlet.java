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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.model.MAdvertisement;
import org.compiere.util.CLogger;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUser;
import org.compiere.util.WebUtil;

/**
 *  Web Request.
 *
 *  @author     Jorg Janke
 *  @version    $Id: AdvertisementServlet.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class AdvertisementServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1795015577057042600L;
	/**	Logging						*/
	private static CLogger			log = CLogger.getCLogger(AdvertisementServlet.class);
	/** Name						*/
	static public final String		NAME = "AdvertisementServlet";

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
			throw new ServletException("AdvertisementServlet.init");
	}   //  init

	/**
	 * Get Servlet information
	 * @return Info
	 */
	public String getServletInfo()
	{
		return "Adempiere Web Avertisement Servlet";
	}	//	getServletInfo

	/**
	 * Clean up resources
	 */
	public void destroy()
	{
		log.fine("");
	}   //  destroy

	/*************************************************************************/

	public static final String  P_ADVERTISEMENT_ID	= "W_Advertisement_ID";

	/**
	 *  Process the HTTP Get request
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
		log.info("Get from " + request.getRemoteHost() + " - " + request.getRemoteAddr() + " - forward to request.jsp");
		if (!response.isCommitted ())
		response.sendRedirect("advertisements.jsp");
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
		log.info("Post from " + request.getRemoteHost() + " - " + request.getRemoteAddr());

		//  Get Session attributes
		HttpSession session = request.getSession(true);
		session.removeAttribute(WebSessionCtx.HDR_MESSAGE);
		//
		Properties ctx = JSPEnv.getCtx(request);
		WebUser wu = (WebUser)session.getAttribute(WebUser.NAME);
		if (wu == null)
		{
			log.warning("No web user");
			if (!response.isCommitted ())
			response.sendRedirect("loginServlet?ForwardTo=advertisement.jsp");	//	entry
			return;
		}
		int W_Advertisement_ID = WebUtil.getParameterAsInt(request, P_ADVERTISEMENT_ID);
		MAdvertisement ad = new MAdvertisement (ctx, W_Advertisement_ID, null);
		if (ad.get_ID() == 0)
		{
			WebUtil.createForwardPage(response, "Web Advertisement Not Found", "advertisements.jsp", 0);
			return;
		}
		StringBuffer info = new StringBuffer();
		//
		String Name = WebUtil.getParameter (request, "Name");
		if (Name != null && Name.length() > 0 && !Name.equals(ad.getName()))
		{
			ad.setName(Name);
			info.append("Name - ");
		}
		String Description = WebUtil.getParameter (request, "Description");
		if (Description != null && Description.length() > 0 && !Description.equals(ad.getDescription()))
		{
			ad.setDescription(Description);
			info.append("Description - ");
		}
		String ImageURL = null;
		String AdText = WebUtil.getParameter (request, "AdText");
		if (AdText != null && AdText.length() > 0 && !AdText.equals(ad.getAdText()))
		{
			ad.setAdText(AdText);
			info.append("AdText - ");
		}
		String ClickTargetURL = WebUtil.getParameter (request, "ClickTargetURL");
		if (ClickTargetURL != null && ClickTargetURL.length() > 0 && !ClickTargetURL.equals(ad.getClickTargetURL()))
		{
			ad.setClickTargetURL(ClickTargetURL);
			info.append("ClickTargetURL - ");
		}
		if (info.length() > 0)
		{
			if (ad.save())
				WebUtil.createForwardPage(response, "Web Advertisement Updated: " + info.toString(), "advertisements.jsp", 0);
			else
				WebUtil.createForwardPage(response, "Web Advertisement Update Error", "advertisements.jsp", 0);
		}
		else
			WebUtil.createForwardPage(response, "Web Advertisement not changed", "advertisements.jsp", 0);
	}   //  doPost

}   //  AdvertisementSerlet
