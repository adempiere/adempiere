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
package org.compiere.cm;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.compiere.model.*;

/**
 * AdRedirector will forward the Ad Request to the destination URL and log the request
 * @author Yves Sandfort
 * @version  $Id$
 */
@SuppressWarnings("serial")
public class AdRedirector extends HttpServletCM
{
	/**
	 * Process the HTTP Get request
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		if (request.getParameter("CM_Ad_ID")!=null) {
			try {
				String CM_Ad_ID = request.getParameter("CM_Ad_ID");
				MAd thisAd = new MAd(ctx, Integer.parseInt(CM_Ad_ID), "webCM");
				thisAd.addClick(request);
				response.sendRedirect(thisAd.getTargetURL());
			} catch (Exception E) {
				response.sendError(500, "Internal Error while processing Ad Redirect...");
			}
		} else {
			response.sendError(500,"Unknown or illegal Ad set, can't handle request...");
		}
	}

	/**
	 *  Process the HTTP Post request
	 *  to simplify we reuse the doGet functionality
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		doGet(request, response);
	}   //  doPost
}
