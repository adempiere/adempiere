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
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.model.MAttachment;
import org.compiere.model.MMailMsg;
import org.compiere.model.MRequest;
import org.compiere.model.MUser;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.FileUpload;
import org.compiere.util.Msg;
import org.compiere.util.WebEnv;
import org.compiere.util.WebInfo;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUser;
import org.compiere.util.WebUtil;

/**
 *  Web Request.
 *
 *  @author     Jorg Janke
 *  @version    $Id: RequestServlet.java,v 1.3 2006/09/16 08:32:34 comdivision Exp $
 */
public class RequestServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3070465594678429980L;
	/**	Logging						*/
	private static CLogger			log = CLogger.getCLogger(RequestServlet.class);
	/** Name						*/
	static public final String		NAME = "requestServlet";

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
			throw new ServletException("RequestServlet.init");
	}   //  init

	/**
	 * Get Servlet information
	 * @return Info
	 */
	public String getServletInfo()
	{
		return "Adempiere Web Request Servlet";
	}	//	getServletInfo

	/**
	 * Clean up resources
	 */
	public void destroy()
	{
		log.fine("destroy");
	}   //  destroy

	/*************************************************************************/

	public static final String  P_FORWARDTO     = "ForwardTo";
	public static final String  P_SOURCE        = "Source";
	public static final String  P_INFO          = "Info";

	public static final String  P_SALESREP_ID	= "SalesRep_ID";
	public static final String  P_REQUESTTYPE_ID= "RequestType_ID";
	public static final String  P_REF_ORDER_ID	= "RefOrder_ID";
	public static final String	P_CLOSE			= "Close";
	public static final String	P_ESCALATE		= "Escalate";
	public static final String  P_SUMMARY       = "Summary";
	public static final String  P_CONFIDENTIAL  = "Confidential";
	
	
	public static final String  P_REQUEST_ID	= "R_Request_ID";
	public static final String	P_ATTACHMENT_INDEX = "AttachmentIndex";

	/**
	 *  Process the HTTP Get request.
	 *  Sends Attachment
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
		String url = "/requestDetails.jsp";
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

			//	Parameter = Note_ID - if is valid and belongs to wu then create PDF & stream it
			String msg = streamAttachment (request, response);
			if (msg == null || msg.length() == 0)
				return;
			if (info != null)
				info.setMessage(msg);
		}

		log.info ("Forward to " + url);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher (url);
		dispatcher.forward (request, response);
	}   //  doGet

	/**
	 * 	Stream Attachment
	 * 	@param request request
	 * 	@param response response
	 * 	@return null or error message
	 */
	private String streamAttachment (HttpServletRequest request, HttpServletResponse response)
	{
		//	Get Request ID
		int R_Request_ID = WebUtil.getParameterAsInt(request, P_REQUEST_ID);
		if (R_Request_ID == 0)
		{
			log.fine("No R_Request_ID)");
			return "No Request ID";
		}
		int attachmentIndex = WebUtil.getParameterAsInt(request, P_ATTACHMENT_INDEX);
		if (attachmentIndex == 0)
		{
			log.fine("No index)");
			return "No Request Attachment index";
		}
		log.info("R_Request_ID=" + R_Request_ID + " / " + attachmentIndex);

		//	Get Request
		Properties ctx = JSPEnv.getCtx(request);
		MRequest doc = new MRequest (ctx, R_Request_ID, null);
		if (doc.getR_Request_ID() != R_Request_ID)
		{
			log.fine("Request not found - R_Request_ID=" + R_Request_ID);
			return "Request not found";
		}
		
		MAttachment attachment = doc.getAttachment(false);
		if (attachment == null)
		{
			log.fine("No Attachment for R_Request_ID=" + R_Request_ID);
			return "Request Attachment not found";
		}
		
		//	Get WebUser & Compare with invoice
		HttpSession session = request.getSession(true);
		WebUser wu = (WebUser)session.getAttribute(WebUser.NAME);
		if (wu.getAD_User_ID() == doc.getAD_User_ID()
			|| wu.getAD_User_ID() == doc.getSalesRep_ID())
			;
		else
		{
			log.warning ("R_Request_ID="
				+ R_Request_ID 
				+ " Web_User=" + wu.getAD_User_ID()
				+ " <> AD_User_ID=" + doc.getAD_User_ID()
				+ " | SalesRep_ID=" + doc.getSalesRep_ID());
			return "Your Request not found";
		}
		//	Stream it
		return WebUtil.streamAttachment (response, attachment, attachmentIndex);
	}	//	streamAttachment

	
	/**************************************************************************
	 *  Process the HTTP Post request
	 *
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doPost (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		String contentType = request.getContentType();
		log.info("From " + request.getRemoteHost() + " - " + request.getRemoteAddr()
			+ " - " + contentType);

		//  Get Session attributes
		HttpSession session = request.getSession(true);
		session.removeAttribute(WebSessionCtx.HDR_MESSAGE);
		//
		Properties ctx = JSPEnv.getCtx(request);
		WebUser wu = (WebUser)session.getAttribute(WebUser.NAME);
		if (wu == null)
		{
			log.warning("No web user");
			if (!response.isCommitted ())
				response.sendRedirect("loginServlet?ForwardTo=request.jsp");	//	entry
			return;
		}
		if (contentType != null && contentType.indexOf("multipart/form-data") != -1)
		{
			uploadFile(request, response);
			return;
		}
		
		//	Addl Info
		String requestURL = request.getRequestURL().toString();
		String requestRef = request.getHeader("referer");
		String source = WebUtil.getParameter (request, P_SOURCE);
		String info = WebUtil.getParameter (request, P_INFO);
		String forwardTo = WebUtil.getParameter (request, P_FORWARDTO);
		log.fine("Referer=" + requestRef + ", Source=" + source + ", ForwardTo=" + forwardTo);
		if (requestURL == null)
			requestURL = "";
		if (requestURL.equals(requestRef))      //  if URL and Referrer are the same, get source
		{
			requestRef = source;
			source = null;
		}

		int AD_Client_ID = Env.getContextAsInt(ctx, "AD_Client_ID");
		int R_RequestType_ID = WebUtil.getParameterAsInt(request, P_REQUESTTYPE_ID);
		int C_Order_ID = WebUtil.getParameterAsInt(request, P_REF_ORDER_ID);
		int R_Request_ID = WebUtil.getParameterAsInt(request, P_REQUEST_ID);
		int SalesRep_ID = WebUtil.getParameterAsInt(request, P_SALESREP_ID);
		if (SalesRep_ID != 0 && !MUser.isSalesRep(SalesRep_ID))
		{
			log.warning("Invalid (set to 0) SalesRep_ID=" + SalesRep_ID);
			SalesRep_ID = 0;
		}
		//	The text
		String Summary = WebUtil.getParameter (request, P_SUMMARY);
		if (Summary == null || Summary.length() == 0)
		{
			WebUtil.createErrorPage(request, response, this, "No Data Received");
			return;
		}
		boolean IsConfidential = WebUtil.getParameterAsBoolean(request, P_CONFIDENTIAL);
		boolean IsClose = WebUtil.getParameterAsBoolean(request, P_CLOSE);
		boolean IsEscalate = WebUtil.getParameterAsBoolean(request, P_ESCALATE);
		MRequest req = null;
		//	New SelfService Request
		if (R_Request_ID == 0)
		{
			req = new MRequest(ctx, SalesRep_ID, R_RequestType_ID, Summary, true, null);
			req.setC_BPartner_ID(wu.getC_BPartner_ID());
			req.setAD_User_ID(wu.getAD_User_ID());
			if (C_Order_ID > 0)
				req.setC_Order_ID(C_Order_ID);
			if (IsConfidential)
				req.setConfidentialType(MRequest.CONFIDENTIALTYPE_PartnerConfidential);
			//
			StringBuffer sb = new StringBuffer();
			sb.append("From:").append(request.getRemoteHost())
				.append("-").append(request.getRemoteAddr());
			sb.append(", Request:").append(requestURL).append("-").append(requestRef);
			if (source != null)
				sb.append("-").append(source);
			sb.append("-").append(info);
			sb.append(", User=").append(request.getHeader("accept-language"))
				.append("-").append(request.getHeader("user-agent"));
			req.setLastResult(sb.toString());
			//
			if (!req.save())
			{
				log.log(Level.SEVERE, "New Request NOT saved");
				WebUtil.createErrorPage (request, response, this, 
					"Request Save Error. Shorten Text!");
				return;
			}
		}
		else	//	existing Request
		{
			req = new MRequest (ctx, R_Request_ID, null);
			if (req.get_ID() == 0)
			{
				log.log(Level.SEVERE, "Request NOT found - R_Request_ID=" + R_Request_ID);
				WebUtil.createErrorPage(request, response, this,
					"Request Not found.");
				return;
			}
			//	Update Info
			if (C_Order_ID != 0 && req.getC_Order_ID() == 0)
				req.setC_Order_ID(C_Order_ID);
			if (!req.webUpdate(Summary))
			{
				WebUtil.createErrorPage(request, response, this,
					"Request Cannot be updated.");
				return;
			}
			//	Flags
			if (IsConfidential)
				req.setConfidentialTypeEntry(MRequest.CONFIDENTIALTYPEENTRY_PartnerConfidential);
			if (IsClose)
				req.doClose();
			else if (IsEscalate)
			{
				boolean isUser = req.getC_BPartner_ID() == wu.getC_BPartner_ID();
				if (isUser || wu.isSalesRep())	//	allow only user or sales rep to escalate
					req.doEscalate(isUser);
			}
			if (!req.save())
			{
				log.log(Level.SEVERE, "Request Action Error");
				WebUtil.createErrorPage(request, response, this,
					"Request Process Error.");
				return;
			}
		}	//	Requests

		//	Send EMail to Customer
		StringBuffer message = new StringBuffer(req.getSummary());
		String result = req.getResult();
		if (result != null && result.length() > 0)
			message.append("\n----------\n")
				.append(req.getResult());
		JSPEnv.sendEMail(request, wu, MMailMsg.MAILMSGTYPE_Request, 
			new Object[]{
				req.getDocumentNo(),
				wu.getName(),
				message,
				"\n---------- " + req.getMailTag()});

		//  --  Fini
		String webStoreURL = "http://" + request.getServerName() + request.getContextPath() + "/";
		if (forwardTo == null || forwardTo.length() == 0)
			forwardTo = requestRef;
		if (forwardTo != null && 
			(forwardTo.indexOf("request.jsp") != -1 || forwardTo.indexOf("requestDetails.jsp") != -1))
			forwardTo = "requests.jsp";		//	list of requests
		if (forwardTo == null || forwardTo.length() == 0)
			forwardTo = webStoreURL;
		if (forwardTo.indexOf("Servlet") != -1)
			forwardTo = webStoreURL;
		WebUtil.createForwardPage(response, "Web Request Received - Thanks", forwardTo, 10);
	}   //  doPost

	/**
	 * 	Upload File
	 *	@param request request
	 *	@param response response
	 *	@throws ServletException
	 *	@throws IOException
	 */
	private void uploadFile (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		FileUpload upload = new FileUpload(request);
		String error = upload.getError();
		if (error != null)
		{
			WebUtil.createForwardPage(response, error, 
				"requests.jsp", 10);
			return;
		}
		
		Properties ctx = JSPEnv.getCtx(request);
		//	Get Request:
		int R_Request_ID = upload.getParameterAsInt("R_Request_ID");
		MRequest req = null;
		if (R_Request_ID != 0)
			req = new MRequest (ctx, R_Request_ID, null);
		if (R_Request_ID == 0 || req == null || req.get_ID() != R_Request_ID)
		{
			WebUtil.createForwardPage(response, "Request not found", "requests.jsp", 10);
			return;
		}
		if (!req.isWebCanUpdate())
		{
			WebUtil.createForwardPage(response, "Request cannot be updated", "requests.jsp", 10);
			return;
		}
		String fileName = upload.getFileName();
		log.fine("R_Request_ID=" + R_Request_ID + " - " + fileName); 
		
		//	Add Attachment
		MAttachment attachment = req.createAttachment();
		attachment.addEntry(fileName, upload.getData());
		if (attachment.save())
		{
			String msg = Msg.parseTranslation(ctx, "@Added@: @AD_Attachment_ID@ " + fileName);
			req.webUpdate(msg);
			req.save();
			WebUtil.createForwardPage(response, msg, "requests.jsp", 10);
		}
		else
			WebUtil.createForwardPage(response, "File Upload Error - Please try again", "requests.jsp", 10);
		log.fine(attachment.toString());
	}	//	uploadFile

	
}   //  RequestServlet
