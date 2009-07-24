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
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.util.CLogger;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUser;
import org.compiere.util.WebUtil;

/**
 *  Web Expense Report.
 *
 *  @author     Jorg Janke
 *  @version    $Id: ExpenseServlet.java,v 1.3 2006/07/30 00:53:21 jjanke Exp $
 */
public class ExpenseServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3162975286315508379L;
	/**	Logging						*/
	private static CLogger			log = CLogger.getCLogger(ExpenseServlet.class);
	/** Name						*/
	static public final String		NAME = "ExpenseServlet";
	/** Submit type					*/
	public static String			P_SubmitType_Name = "Submit";
	
	/**
	 *	Initialize global variables
	 *
	 *  @param config Configuration
	 *  @throws ServletException
	 */
	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("ExpenseServlet.init");
	}   //  init

	/**
	 * Get Servlet information
	 * @return Info
	 */
	public String getServletInfo()
	{
		return "Adempiere Web Expense Servlet";
	}	//	getServletInfo

	/**
	 * Clean up resources
	 */
	public void destroy()
	{
		log.fine("");
	}   //  destroy

	/*************************************************************************/

	public static final String  P_Expense_ID	= "W_Expense_ID";

	/**
	 *  Process the HTTP Get request
	 *  Sends Web Request Page
	 *
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("Get from " + request.getRemoteHost() + " - " + request.getRemoteAddr() + " - forward to request.jsp");
		if (!response.isCommitted ())
			response.sendRedirect("expenses.jsp");
	}   //  doGet


	/*************************************************************************/

	/**
	 *  Process the HTTP Post request
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

		//  Get Session attributes
		HttpSession session = request.getSession(true);
		session.removeAttribute(WebSessionCtx.HDR_MESSAGE);
		//
		Properties ctx = JSPEnv.getCtx(request);
		WebUser wu = (WebUser)session.getAttribute(WebUser.NAME);
		if (wu == null)
		{
			log.warning("No web user");
			response.sendRedirect("loginServlet?ForwardTo=expense.jsp");	//	entry
			return;
		}
		int W_Expense_ID = WebUtil.getParameterAsInt(request, P_Expense_ID);
		String method = WebUtil.getParameter(request, P_SubmitType_Name);
		if(method.startsWith("Save"))
		{
			// save BUT DON'T submit for approval
		}else if(method.startsWith("Submit")){
			// save AND submit for approval
		}else{
			// invalid
		}
		WebUtil.createForwardPage(response, "Web Expense Under Construction", "expenses.jsp", 0);
	}   //  doPost

}   //  ExpenseSerlet
