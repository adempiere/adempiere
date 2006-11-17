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
package org.compiere.cm.cache;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compiere.cm.*;

public class Service extends HttpServletCM {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		PrintWriter out;
		out = response.getWriter();
		out.print("<H1>This servlet only handles service requests!</H1>");
		if (request.getParameter("Cache")!=null) {
			if (request.getParameter("Cache").equals("Container")) {
				if (request.getParameter("ID")!=null) {
					containerCache.remove(request.getParameter("ID"));
				}
				if (request.getParameter("SHOW")!=null) {
					out.print("<container>" + containerCache.show() + "</container>");
				}
			}
			if (request.getParameter("Cache").equals("ContainerElement")) {
				if (request.getParameter("ID")!=null) {
					containerElementCache.remove(request.getParameter("ID"));
				}
				if (request.getParameter("SHOW")!=null) {
					out.print("<containerElement>" + containerElementCache.show() + "</containerElement>");
				}
			}
			if (request.getParameter("Cache").equals("ContainerTree")) {
				if (request.getParameter("ID")!=null) {
					containerTreeCache.remove(request.getParameter("ID"));
				}
				if (request.getParameter("SHOW")!=null) {
					out.print("<containerTree>" + containerTreeCache.show() + "</containerTree>");
				}
			}
			if (request.getParameter("Cache").equals("Template")) {
				if (request.getParameter("ID")!=null) {
					if (request.getParameter("ID").equals("0")) {
						templateCache.empty();
					} else {
						templateCache.remove(request.getParameter("ID"));
					}
				}
				if (request.getParameter("SHOW")!=null) {
					out.print("<template>" + templateCache.show() + "</template>");
				}
			}
			if (request.getParameter("Cache").equals("all")) {
				chatCache.empty ();
				containerCache.empty ();
				containerElementCache.empty ();
				containerTreeCache.empty ();
				domainCache.empty ();
				mediaServerCache.empty ();
				templateCache.empty ();
				webProjectCache.empty ();
				xmlCache.empty ();
			}
		}
		out.close();
	}

}
