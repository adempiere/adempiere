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
package org.compiere.mobile;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.compiere.util.CLogger;

/**
 *  HTML UI Filter to do timing and list parameters
 *  @author Jorg Janke
 *  @version $Id: WFilter.java,v 1.1 2009/04/15 11:27:15 vinhpt Exp $
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
		MobileEnv.initWeb(filterConfig.getServletContext());
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
		MobileSessionCtx wctx = MobileSessionCtx.get((HttpServletRequest)request);
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
					|| uri.endsWith("login.js")
					|| uri.endsWith("toolbar.png")
					|| uri.endsWith("WLogin"))
					;
				else if ( uri.contains("/iui/"))
					;
				else
					pass = false;
		else if (!uri.startsWith(MobileEnv.DIR_BASE)      //  not requesting /mobile/...
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
					request.setCharacterEncoding(MobileEnv.ENCODING);
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "Set CharacterEndocung=" + MobileEnv.ENCODING, e);
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

			RequestDispatcher rd=request.getRequestDispatcher("/WLogin");
			rd.forward(request, response);

		}

		//  Post
		if (check && pass)
		{
			if (m_timing)
				myTime = System.currentTimeMillis() - myTime;
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
