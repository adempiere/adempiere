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
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.compiere.cm.utils.RequestAnalyzer;
import org.compiere.cm.xml.Generator;

/**
 * @author YS
 * @version $Id$
 */
public class XMLBroadcast extends HttpServletCM
{
	/**
	 * 	Get
	 *	@param request
	 *	@param response
	 *	@throws ServletException
	 *	@throws IOException
	 */
	public void doGet (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		RequestAnalyzer thisRequest = new RequestAnalyzer (this, request,
			false, "/xml");
		// Even if we will only display the XML tree we are forced to build the Media URLs
		if (getInternalMediaURL () == null)
			setInternalMediaURL (request);
		if (externalMediaURL == null) {
			if (thisRequest.getWebProject()!=null)
				externalMediaURL = getExternalMediaURL (thisRequest
					.getWebProject ().get_ID ());
			else 
				externalMediaURL = getInternalMediaURL();
			
		}
		// Generate the needed XMLCode
		Generator thisXMLGen = new Generator (this, request, thisRequest, new StringBuffer(""));
		String xmlCode = thisXMLGen.get ();
		response.setContentType ("text/xml; charset=UTF8");
		PrintWriter out;
		out = response.getWriter ();
		out.print (xmlCode);
		out.close ();
	}	//	doGet
	
}	//	HttpServletCM
