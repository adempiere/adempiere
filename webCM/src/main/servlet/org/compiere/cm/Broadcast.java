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
import javax.servlet.*;
import javax.servlet.http.*;
import org.compiere.util.*;
import org.compiere.cm.utils.*;
import org.compiere.cm.xml.*;

/**
 * Broadcast Servlet This servlet normally does Page processing for all pages,
 * so it creates context etc.
 * 
 * @author Yves Sandfort
 * @version $Id$
 */
@SuppressWarnings("serial")
public class Broadcast extends HttpServletCM
{

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
		// Create New Session
		HttpSession sess = request.getSession (true);
		sess.setMaxInactiveInterval (WebEnv.TIMEOUT);
		StringBuffer output = new StringBuffer ();
		// We will reset the Media URL for each request. Should be moved in the session.
		resetInternalMediaURL (request);
		if (configLoaded && !fatalError)
		{
			String acceptLanguage = request.getHeader ("Accept-Language");
			String acceptCharset = request.getHeader ("Accept-Charset");
			LocaleHandler lhandler = new LocaleHandler (acceptLanguage,
				acceptCharset);
			RequestAnalyzer thisRequest = new RequestAnalyzer (this, request,
				false, "");
			StringBuffer xmlCode = new StringBuffer();
			StringBuffer xmlAppend = new StringBuffer();
			
			// Fill up ExternalMediaURL
			//if (externalMediaURL == null) {
				if (thisRequest.getWebProject()!=null)
					externalMediaURL = getExternalMediaURL (thisRequest
						.getWebProject ().get_ID ());
				else 
					externalMediaURL = getInternalMediaURL();
			//}
			sess.setAttribute ("ctx", getCtx());
			// This Request has a Processor Class Name, so we should process it!
			if (thisRequest.getProcClassName ()!=null) {
				try {
					org.compiere.cm.Extend thisProcessor = thisRequest.getProcClass();
					thisProcessor.doIt ();
					xmlAppend.append(thisProcessor.getXML());
					if (thisProcessor.getRedirectURL()!=null)
						thisRequest.setRedirectURL(thisProcessor.getRedirectURL());
				}
				catch (Exception ex) 
				{
					ex.printStackTrace ();
				}
				
			}
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
					thisRequest, xmlAppend);
				xmlCode.append(thisXMLGen.get ());
				// Get or generate Template needed
				String xslCode = templateCache.getCM_Template (
					thisRequest.getCM_Container ().getCM_Template_ID (),
					thisRequest.getWebProject ().get_ID ())
					.getPreBuildTemplate ().toString ();
				// Put it all together
				try
				{
					output.append (XSLTProcessor.run (request, xslCode, xmlCode.toString()));
				}
				catch (Exception E)
				{
					response.sendError (500);
				}
				// response.setContentType("text/html; charset=" +
                // lhandler.getCharset());
				response.setContentType ("text/html; charset=UTF-8");
				response.setHeader ("CMBuild", buildDate);
				PrintWriter out;
				out = response.getWriter ();
				out.print (output.toString ());
				out.close ();

				if (thisRequest.getWebProject()!=null) {
					// We will log the request in a seperate task to speed up page display
					AccessLogger thisAccessLogger = new AccessLogger(request, this, thisRequest);
					thisAccessLogger.setFileSize(new java.math.BigDecimal(output.length()));
					thisAccessLogger.start();
				}
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
} // Broadcast
