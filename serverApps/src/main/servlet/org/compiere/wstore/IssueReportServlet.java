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
package org.compiere.wstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.model.MIssue;
import org.compiere.util.CLogger;
import org.compiere.util.WebEnv;
import org.compiere.util.WebUtil;

/**
 * 	Issue Reporting
 *	
 *  @author Jorg Janke
 *  @version $Id: IssueReportServlet.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class IssueReportServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5347264108766751365L;
	/**	Logging						*/
	private static CLogger			log = CLogger.getCLogger(IssueReportServlet.class);

	/**
	 * 	Initialize global variables
	 *  @param config servlet configuration
	 *  @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("IssueReportServlet.init");
	}	//	init

	/**
	 * Get Servlet information
	 * @return Info
	 */
	public String getServletInfo()
	{
		return "Adempiere Issue Reporting";
	}	//	getServletInfo

	/**
	 * Clean up resources
	 */
	public void destroy()
	{
		log.info("");
	}   //  destroy

	
	/**************************************************************************
	 *  Process the initial HTTP Get request.
	 *  Reads the Parameter Amt and optional C_Invoice_ID
	 *
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("From " + request.getRemoteHost() + " - " + request.getRemoteAddr());
		Properties ctx = JSPEnv.getCtx(request);
		HttpSession session = request.getSession(true);
	//	WEnv.dump(session);
	//	WEnv.dump(request);

		int AD_Issue_ID = WebUtil.getParameterAsInt(request, "RECORDID");
		String DBAddress = WebUtil.getParameter(request, "DBADDRESS");
		String Comments = WebUtil.getParameter(request, "COMMENTS");
		String IssueString = WebUtil.getParameter(request, "ISSUE");
		//
		StringBuffer responseText = new StringBuffer("Adempiere Support - ")
			.append(new Date().toString())
			.append("\n");
		MIssue issue = null;
		if (AD_Issue_ID != 0)
		{
			issue = new MIssue(ctx, AD_Issue_ID, null);
			if (issue.get_ID() != AD_Issue_ID)
				responseText.append("Issue Unknown - Request Ignored");
			else if (!issue.getDBAddress().equals(DBAddress))
				responseText.append("Not Issue Owner - Request Ignored");
			else
			{
				issue.addComments(Comments);
				responseText.append(issue.createAnswer());
			}
		}
		else if (IssueString == null || IssueString.length() == 0)
		{
			responseText.append("Unknown Request");
		}
		else
		{
			issue = MIssue.create(ctx, IssueString);
			if (issue == null || !issue.save())
				responseText.append("Could not save Issue");
			else
				responseText.append(issue.process());
		}
		
		//
		StringBuffer answer = new StringBuffer();
		if (issue != null && issue.get_ID() != 0)
		{
			answer.append("RECORDID=").append(issue.get_ID())
				.append(MIssue.DELIMITER);
		//	answer.append("DOCUMENTNO=").append(".")
		//		.append(MIssue.DELIMITER);
		}
		answer.append("RESPONSE=").append(responseText);
		//
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();     //  with character encoding support
		out.write(URLEncoder.encode(answer.toString(), "UTF-8"));
		out.flush();
		if (out.checkError())
			log.log(Level.SEVERE, "error writing");
		out.close();
	}   //  doGet

	/**
	 *  Process the HTTP Post request.
	 * 	The actual payment processing
	 *
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("Post from " + request.getRemoteHost() + " - " + request.getRemoteAddr());
		doGet(request, response);
	}   //  doPost

}	//	IssueReportServlet
