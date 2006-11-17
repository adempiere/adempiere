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
import java.util.Enumeration;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.compiere.util.*;
import org.compiere.model.*;
import org.compiere.cm.utils.*;

/**
 *	Community Servlet to handle login, BPartner create & Update etc.
 *	
 *  @author Yves Sandfort
 *  @version $Id$
 */
public class Community extends HttpServletCM
{
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
		HttpSession sess = request.getSession (true);
		sess.setMaxInactiveInterval (WebEnv.TIMEOUT);
		
		Properties ctx = getCtx();
		
		if (sess.getAttribute ("ctx")!=null)
			ctx = (Properties) sess.getAttribute ("ctx");

		if (ctx.get ("#AD_Client_ID")!=null) {
			RequestAnalyzer thisRequest = new RequestAnalyzer(this, request, false, null);
		}

		
		WebSessionCtx wsc = (WebSessionCtx)sess.getAttribute(WebSessionCtx.NAME);
		//	Create New
		if (wsc == null)
		{
			int [] allIDs = X_W_Store.getAllIDs ("W_Store", "AD_Client_ID=" + ctx.get ("#AD_Client_ID"), "");
			if (allIDs!=null && allIDs.length>0) 
			{
				wsc = WebSessionCtx.get(request, allIDs[0]);
				wsc.setWStore (allIDs[0]);
				sess.setAttribute(WebSessionCtx.NAME, wsc);
			}
		}
		
		WebLogin thisLogin = new WebLogin(request, response, ctx);
		thisLogin.init ();
		if (!thisLogin.action ())
		{
			WebUtil.reload(thisLogin.getMessage(), thisLogin.getUpdate_page (), sess, request, response, getServletContext());
			return;
		}
		String url = thisLogin.getForward ();
		if (!url.startsWith("/"))
			url = "/" + url;
		log.info("doPost - Forward to " + url);
		response.sendRedirect (url);
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
