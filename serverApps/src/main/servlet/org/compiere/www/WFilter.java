/********************************************************************** 
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) 1999 - 2006 Compiere Inc.                            * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Bahman Movaqar (bmovaqar@users.sf.net)                          * 
 **********************************************************************/
package org.compiere.www;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.compiere.util.*;

/**
 *  HTML UI Filter to do timing and list parameters
 *  @author Jorg Janke
 *  @version $Id: WFilter.java,v 1.3 2006/07/30 00:53:21 jjanke Exp $
 */

public final class WFilter implements javax.servlet.Filter
{
	/**  The filter configuration object we are associated with.
	 *   If this value is null, this filter instance is not currently configured    */
	private FilterConfig 		m_filterConfig = null;

	/** Timing indicator                    */
	private boolean             m_timing = false;
	/**	Logging								*/
	private static CLogger		log = null;

	/**
	 *  Place this filter into service.
	 *
	 *  @param filterConfig The filter configuration object
	 *  @throws ServletException
	 */
	public void init (FilterConfig filterConfig) throws ServletException
	{
		m_filterConfig = filterConfig;
		WebEnv.initWeb(filterConfig.getServletContext());
		if (log == null)
			log = CLogger.getCLogger(WFilter.class);

		//  List all Parameters
		log.info(filterConfig.getFilterName());
		Enumeration en = filterConfig.getInitParameterNames();
		while (en.hasMoreElements())
		{
			String name = en.nextElement().toString();
			String value = filterConfig.getInitParameter(name);
			log.config(name + "=" + value);
			if (name.equals("Timing") && value.equals("Y"))
				m_timing = true;
		}
	}   //  init

	/**
	 *  Take this filter out of service.
	 */
	public void destroy()
	{
		m_filterConfig = null;
	}   //  destroy

	/**
	 *  Time the processing that is performed by all subsequent filters in the
	 *  current filter stack, including the ultimately invoked servlet.
	 *
	 * @param request The servlet request we are processing
	 * @param response response
	 * @param chain The filter chain we are processing
	 *
	 * @exception IOException if an input/output error occurs
	 * @exception ServletException if a servlet error occurs
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException
	{
		WebSessionCtx wctx = WebSessionCtx.get((HttpServletRequest)request);
		/**if (wctx == null) {
			if (m_filterConfig != null) {				
		        String login_page = m_filterConfig.getInitParameter("LoginServlet");		        
		        if (login_page != null && !"".equals(login_page)) {
		            m_filterConfig.getServletContext().getRequestDispatcher("/WLogin").forward(request, response);
		            return;
		        }
			}
			throw new ServletException("Unauthorized access, unable to forward to login page");
		}
			
		String sessionID = wctx.ctx.getProperty("#AD_Session_ID");
		if (sessionID == null) {
			if (m_filterConfig != null) {				
		        String login_page = m_filterConfig.getInitParameter("LoginServlet");
		        if (login_page != null && !"".equals(login_page)) {
		        	RequestDispatcher rd=request.getRequestDispatcher("/WLogin");
		        	rd.forward(request, response);		        	
		           return;
		        }
			}
			throw new ServletException("Unauthorized access, unable to forward to login page");
		}	**/	
		
		String sessionID = wctx.ctx.getProperty("#AD_Session_ID");
		
		if (sessionID == null) {
			//log.info("Still no session id");
		}
		
		//  Get URI
		String uri = "";
		if (request instanceof HttpServletRequest)
		{
			HttpServletRequest req = (HttpServletRequest)request;
			uri = req.getRequestURI();
		}
		
		
		boolean pass = true;
		//  Ignore static content
		boolean check = true;
		if (sessionID == null)
				if(uri.endsWith("index.html")
					|| uri.endsWith("cmd.html")
					|| uri.endsWith("menu.html")
					|| uri.endsWith("menu.js")
					|| uri.endsWith("window.html")
					|| uri.endsWith("Logo.gif")
					|| uri.endsWith("standard.js")
					|| uri.endsWith("standard.css")					
					|| uri.endsWith("calendar-blue.css")
					|| uri.endsWith("table.css")
					|| uri.endsWith("table.js")
					|| uri.endsWith("calendar.js")
					//|| uri.endsWith("calendar-setup.js")
					|| uri.endsWith("calendar-en.js")
					|| uri.endsWith("window.css")
					|| uri.endsWith("window.js")					
					|| uri.endsWith("WLogin"))
					;
				else
					pass = false;
		else if (!uri.startsWith(WebEnv.DIR_BASE)      //  not requesting /adempiere/...
			|| uri.endsWith(".gif") || uri.endsWith(".jpg") || uri.endsWith(".png") 
			|| uri.endsWith(".html") || uri.endsWith(".css")
			|| uri.endsWith(".js"))
			check = false;		
		else
			;

		// We need to check
		StringBuffer sb = new StringBuffer ("| Parameters");
		if (check)
		{
			try
			{
				String enc = request.getCharacterEncoding();
				if (enc == null)
					request.setCharacterEncoding(WebEnv.ENCODING);
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "Set CharacterEndocung=" + WebEnv.ENCODING, e);
			}
			//  print parameter
			Enumeration en = request.getParameterNames();
			while (en.hasMoreElements())
			{
				String name = (String)en.nextElement();
				sb.append(" - ").append(name).append("=").append(request.getParameter(name));
			}
			if (uri.endsWith("WWindowStatus"))
				pass = false;
		}
		if (pass && check)
			log.info("Start " + uri + sb.toString());

		//  Timing
		long myTime = 0l;
		if (pass && check && m_timing)
			myTime = System.currentTimeMillis();

		//  **  Start   **
		if (pass)
			chain.doFilter(request, response);
		else
		{
			//log.warning("Rejected " + uri);
			//String msg = "Error: Access Rejected";
			//WebDoc doc = WebDoc.create (msg);
			//	Body
			//body b = doc.getBody();
			//b.addElement(new p(uri, AlignType.CENTER));
			//	fini
			//response.setContentType("text/html");
			//PrintWriter out = new PrintWriter (response.getOutputStream());
			//doc.output(out);
			//out.close();
			RequestDispatcher rd=request.getRequestDispatcher("/index.html");
        	rd.forward(request, response);
		}

		//  Post
		if (check && pass)
		{
			if (m_timing)
				myTime = System.currentTimeMillis() - myTime;
			log.info("End   " + uri + "| " + (m_timing ? String.valueOf(myTime) : null));
		}
		
	}   //  doFilter


	/**
	 *  Return a String representation of this object.
	 *  @return String info
	 */
	public String toString()
	{
		if (m_filterConfig == null)
			return ("WFilter[]");
		StringBuffer sb = new StringBuffer("WFilter[");
		sb.append(m_filterConfig);
		sb.append("]");
		return (sb.toString());
	}   //  toString

}   //  Filter
