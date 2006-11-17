/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.
 * This program is free software; you can redistribute it and/or modify it
 * under the terms version 2 of the GNU General Public License as published
 * by the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 * You may reach us at: ComPiere, Inc. - http://www.adempiere.org/license.html
 * 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA or info@adempiere.org 
 *****************************************************************************/
package org.compiere.cm;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.cm.request.Request;
import org.compiere.util.CLogger;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUtil;

/**
 *	Request Servlet to handle Request create & Update etc.
 *	
 *  @author Kai Viiksaar
 *  @version $Id: RequestServlet.java,v 1.1 2006/10/11 06:30:11 comdivision Exp $
 */
public class RequestServlet extends HttpServletCM {
	/**	Logging				  				*/
	private CLogger				log = CLogger.getCLogger(getClass());

	/**
	 * 	Process Get Request
	 *	@param request
	 *	@param response
	 *	@throws ServletException
	 *	@throws IOException
	 */
	public void doGet (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		String l_szRequestID = null;
		HttpSession sess = request.getSession (true);
		sess.setMaxInactiveInterval (WebEnv.TIMEOUT);
		
		Properties ctx = getCtx();
		
		if (sess.getAttribute ("ctx")!=null)
			ctx = (Properties) sess.getAttribute ("ctx");

		WebSessionCtx wsc = (WebSessionCtx)sess.getAttribute(WebSessionCtx.NAME);
		
		//	Create New Request
		if (wsc != null) {
			String mode = WebUtil.getParameter(request, "Mode");
			if (mode != null && mode.equals("RequestNew")) {
				l_szRequestID = Request.createRequest(request, ctx);
			} else if (mode != null && mode.equals("RequestChange")) {
				l_szRequestID = Request.changeRequest(request, ctx);
			}
		}
		String url = request.getParameter("ForwardTo") + l_szRequestID;
		if (!url.startsWith("/"))
			url = "/" + url;
		response.sendRedirect(url);
	}

	/**
     * Process Post Request (handled by get)
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	public void doPost (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		doGet (request, response);
	} // doPost

}
