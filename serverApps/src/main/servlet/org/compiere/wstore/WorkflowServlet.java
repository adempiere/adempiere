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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.model.MAttachment;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.WebEnv;
import org.compiere.util.WebInfo;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUser;
import org.compiere.util.WebUtil;
import org.compiere.wf.MWFActivity;

/**
 *  Web Workflow.
 *
 *  @author     Jorg Janke
 *  @version    $Id: WorkflowServlet.java,v 1.3 2006/09/16 08:32:34 comdivision Exp $
 */
public class WorkflowServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5194115425645974027L;
	/**	Logging						*/
	private CLogger					log = CLogger.getCLogger(getClass());
	/** Name						*/
	static public final String		NAME = "WorkflowServlet";

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
			throw new ServletException("WorkflowServlet.init");
	}   //  init

	/**
	 * Get Servlet information
	 * @return Info
	 */
	public String getServletInfo()
	{
		return "Adempiere Web Workflow Servlet";
	}	//	getServletInfo

	/**
	 * Clean up resources
	 */
	public void destroy()
	{
		log.fine("destroy");
	}   //  destroy

	/*************************************************************************/

	public static final String  P_WF_Activity_ID	= "AD_WF_Activity_ID";
	public static final String	P_ATTACHMENT_INDEX 	= "AttachmentIndex";

	/**
	 *  Process the HTTP Get request.
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
		log.info("doGet from " + request.getRemoteHost() + " - " + request.getRemoteAddr() + " - forward to notes.jsp");
		String url = "/notes.jsp";
		//
		HttpSession session = request.getSession(false);
		if (session == null 
			|| session.getAttribute(WebInfo.NAME) == null)
			url = "/login.jsp";
		else
		{
			session.removeAttribute(WebSessionCtx.HDR_MESSAGE);
			WebInfo info = (WebInfo)session.getAttribute(WebInfo.NAME);
			if (info != null)
				info.setMessage("");

			//	Parameter = Activity_ID - if valid and belongs to wu then create PDF & stream it
			String msg = streamAttachment (request, response);
			if (msg == null || msg.length() == 0)
				return;
			if (info != null)
				info.setMessage(msg);
		}

		log.info ("doGet - Forward to " + url);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher (url);
		dispatcher.forward (request, response);
	}   //  doGet

	/**
	 * 	Stream Attachment
	 * 	@param request request
	 * 	@param response response
	 * 	@return "" or error message
	 */
	private String streamAttachment (HttpServletRequest request, HttpServletResponse response)
	{
		//	Get Activity ID
		int AD_WF_Activity_ID = WebUtil.getParameterAsInt(request, P_WF_Activity_ID);
		if (AD_WF_Activity_ID == 0)
		{
			log.fine("streamAttachment - no AD_WF_Activity_ID)");
			return "No Activity ID";
		}
		int attachmentIndex = WebUtil.getParameterAsInt(request, P_ATTACHMENT_INDEX);
		if (attachmentIndex == 0)
		{
			log.fine("streamAttachment - no index)");
			return "No Request Attachment index";
		}
		log.info("streamAttachment - AD_WF_Activity_ID=" + AD_WF_Activity_ID + " / " + attachmentIndex);

		//	Get Note
		Properties ctx = JSPEnv.getCtx(request);
		MWFActivity doc = new MWFActivity (ctx, AD_WF_Activity_ID, null);
		if (doc.get_ID() != AD_WF_Activity_ID)
		{
			log.fine("streamAttachment - Activity not found - ID=" + AD_WF_Activity_ID);
			return "Activity not found";
		}
		
		MAttachment attachment = doc.getAttachment(false);
		if (attachment == null)
		{
			log.fine("streamAttachment - No Attachment for AD_WF_Activity_ID=" + AD_WF_Activity_ID);
			return "Notice Attachment not found";
		}

		//	Get WebUser & Compare with invoice
		HttpSession session = request.getSession(true);
		WebUser wu = (WebUser)session.getAttribute(WebUser.NAME);
		if (wu.getAD_User_ID() != doc.getAD_User_ID())
		{
			log.warning ("streamAttachment - AD_WF_Activity_ID="
				+ AD_WF_Activity_ID + " - User_Activity=" + doc.getAD_User_ID()
				+ " = Web_User=" + wu.getAD_User_ID());
			return "Your Activity not found";
		}

		//	Stream it
		return WebUtil.streamAttachment (response, attachment, attachmentIndex);
	}	//	streamAttachment


	/**************************************************************************
	 *  Process the HTTP Post request.
	 *
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("doPost from " + request.getRemoteHost() + " - " + request.getRemoteAddr());
		String url = "/notes.jsp";
		//
	//	Log.setTraceLevel(9);
	//	WebEnv.dump(request);
		//
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute(WebInfo.NAME) == null)
			url = "/login.jsp";
		else
		{
			session.removeAttribute(WebSessionCtx.HDR_MESSAGE);
			Properties ctx = JSPEnv.getCtx(request);
			WebUser wu = (WebUser)session.getAttribute(WebUser.NAME);
			if (wu == null)
			{
				log.warning("doPost - no web user");
				if (!response.isCommitted ())
					response.sendRedirect("loginServlet?ForwardTo=note.jsp");	//	entry
				return;
			}
			//	Get Feedback
			int AD_WF_Activity_ID = WebUtil.getParameterAsInt(request, P_WF_Activity_ID);
			boolean isConfirmed = WebUtil.getParameterAsBoolean (request, "IsConfirmed");
			boolean isApproved = WebUtil.getParameterAsBoolean (request, "IsApproved");
			boolean isRejected = WebUtil.getParameterAsBoolean (request, "IsApproved");
			String textMsg = WebUtil.getParameter (request, "textMsg");
			log.fine("doPost - TextMsg=" + textMsg);
			//
			MWFActivity act = new MWFActivity (ctx, AD_WF_Activity_ID, null);
			log.fine("doPost - " + act);
			
			if (AD_WF_Activity_ID == 0 || act == null || act.getAD_WF_Activity_ID() != AD_WF_Activity_ID)
				session.setAttribute(WebSessionCtx.HDR_MESSAGE, "Activity not found");
			else
			{
				if (act.isUserApproval() && (isApproved || isRejected))
				{
					try
					{
						act.setUserChoice(wu.getAD_User_ID(), isApproved ? "Y" : "N", 
							DisplayType.YesNo, textMsg);
						act.save();
					}
					catch (Exception e)
					{
					}
				}	//	approval
				else if (act.isUserManual() && isConfirmed)
				{
					act.setUserConfirmation(wu.getAD_User_ID(), textMsg);
					act.save();
				}
				else if (textMsg != null && textMsg.length() > 0)
				{
					act.setTextMsg (textMsg);
					act.save();
				}
			}
		}
		
		log.info ("doGet - Forward to " + url);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher (url);
		dispatcher.forward (request, response);
	}   //  doPost

}   //  WorkflowServlet
