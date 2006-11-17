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
package org.compiere.www;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class WError extends HttpServlet
{
	static final private String CONTENT_TYPE = "text/html";
	static final private String DOC_TYPE = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
	  "  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">";
	/**Initialize global variables*/
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}
	/**Process the HTTP Get request*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.println("<?xml version=\"1.0\"?>");
		out.println(DOC_TYPE);
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">");
		out.println("<head><title>WError</title></head>");
		out.println("<body>");
		out.println("<p>The servlet has received a GET. This is the reply.</p>");
		out.println("</body></html>");
	}
	/**Process the HTTP Post request*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.println("<?xml version=\"1.0\"?>");
		out.println(DOC_TYPE);
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">");
		out.println("<head><title>WError</title></head>");
		out.println("<body>");
		out.println("<p>The servlet has received a POST. This is the reply.</p>");
		out.println("</body></html>");
	}
	/**Clean up resources*/
	public void destroy()
	{
	}
}
