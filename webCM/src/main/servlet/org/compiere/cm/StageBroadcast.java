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

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.compiere.Adempiere;
import org.compiere.util.*;
import org.compiere.model.*;
import org.compiere.cm.utils.*;
import org.compiere.cm.xml.*;

/**
 * Broadcast Servlet
 * 
 * @author $Author$
 * @version $Id$
 */
public class StageBroadcast extends HttpServletCM
{

	/**
     * Handle Get Request
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	public void doGet (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		// Create New Session
		HttpSession sess = request.getSession (true);
		sess.setMaxInactiveInterval (WebEnv.TIMEOUT);
		StringBuffer output = new StringBuffer ();
		if (configLoaded && !fatalError)
		{
			String acceptLanguage = request.getHeader ("Accept-Language");
			String acceptCharset = request.getHeader ("Accept-Charset");
			LocaleHandler lhandler = new LocaleHandler (acceptLanguage,
				acceptCharset);
			RequestAnalyzer thisRequest = new RequestAnalyzer (this, request,
				true, "");
			if (thisRequest.getIsRedirect ())
			{
				// If we need to redirect as the URL is different do it beofre
                // we transmit to client
				response.sendRedirect (thisRequest.getRedirectURL ());
			}
			else
			{
				// No need to redirect so we will display the content
				// Generate the needed XMLCode
				Generator thisXMLGen = new Generator (this, request,
					thisRequest, new StringBuffer(""));
				// thisXMLGen.addPObject(thisRequest.getCM_Container());
				String xmlCode = thisXMLGen.get ();
				// Get or generate Template needed
				String xslCode = templateCache.getCM_Template (
					thisRequest.getCM_Container ().getCM_Template_ID (),
					thisRequest.getWebProject ().get_ID ())
					.getTemplateXST ();
				// Put it all together
				try
				{
					output.append (XSLTProcessor.run (request, xslCode, xmlCode));
				}
				catch (Exception E)
				{
					response.sendError (500);
				}
				response.setContentType ("text/html; charset="
					+ lhandler.getCharset ());
				response.setHeader ("CMBuild", buildDate);
				PrintWriter out;
				out = response.getWriter ();
				out.print (output.toString ());
				out.close ();
			}
		}
		else if (fatalError)
		{
			PrintWriter out;
			out = response.getWriter ();
			out.print ("<H1>Fatal Error:" + ErrorMessage + "</H1>");
			out.close ();
		}
	} // doGet

	/**
     * Process Post Request - handled by Get
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
} // Broadcast
