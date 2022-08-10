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
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.model.MRegistration;
import org.compiere.model.MUser;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUser;
import org.compiere.util.WebUtil;

/**
 *  Registration Servlet.
 *
 *  @author     Jorg Janke
 *  @version    $Id: RegistrationServlet.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class RegistrationServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6448126312542845680L;
	/**	Logging						*/
	private CLogger					log = CLogger.getCLogger(getClass());
	/** Name						*/
	static public final String		NAME = "RegistrationServlet";

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
			throw new ServletException("RegistrationServlet.init");
	}   //  init

	/**
	 * Get Servlet information
	 * @return Info
	 */
	public String getServletInfo()
	{
		return "Adempiere Web Registration Servlet";
	}	//	getServletInfo

	/**
	 * Clean up resources
	 */
	public void destroy()
	{
		log.fine("");
	}   //  destroy

	/*************************************************************************/

	public static final String  P_REGISTRATION_ID	= "A_Registration_ID";

	/**	Thanks	Msg					*/
	private String 		THANKS = "Thank you for your registration!";
	/**	Problem	Msg					*/
	private String 		PROBLEM = "Thank you for your registration - We experienced a problem - please let us know!";
	
	/**************************************************************************
	 *  Process the HTTP Get request.
	 * 	If not System registration - forward to registration.jsp
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
		if (!processSystemRegistration(request, response))
		{
			log.info("Forward to registration.jsp");
			if (!response.isCommitted ())
				response.sendRedirect("registration.jsp");
		}
	}   //  doGet

	/**
	 * 	Process System Registration
	 *	@param request request
	 *	@param response response
	 *	@return true if System Registration
	 */
	private boolean processSystemRegistration (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		//	System Info
		String name = WebUtil.getParameter (request, "Name");
		String userName = WebUtil.getParameter (request, "UserName");
		String password = WebUtil.getParameter (request, "Password");
		//	Not a System registration
		if ((name == null || name.length() == 0)&& (userName == null || userName.length() == 0) && (password == null || password.length() == 0))
			return false;
		log.info ("Name=" + name + ", User=" + userName);
		//	Registration Info
		String description = WebUtil.getParameter (request, "Description");
		boolean inProduction = WebUtil.getParameterAsBoolean(request, "IsInProduction", "Y");
		Timestamp startDate = WebUtil.getParameterAsDate (request, "StartProductionDate");
		if (startDate == null)
			startDate = new Timestamp(System.currentTimeMillis());
		boolean allowPublish = WebUtil.getParameterAsBoolean (request, "IsAllowPublish", "Y");
		boolean registered = WebUtil.getParameterAsBoolean (request, "IsRegistered", "Y");
		int Record_ID = WebUtil.getParameterAsInt(request, "Record_ID");
		
		//	Find User
		Properties ctx = JSPEnv.getCtx(request);
		MUser user = null;
		int AD_User_ID = DB.getSQLValue(null,
			"SELECT AD_User_ID FROM AD_User WHERE EMail=?", userName);
		if (AD_User_ID > 0)
			user = MUser.get(ctx, AD_User_ID);
		else
			log.warning("User Not found=" + userName);
		
		//	Registration
		MRegistration reg = null;
		if (Record_ID > 0)
		{
			reg = new MRegistration (ctx, Record_ID, null);
			if (reg.get_ID() != Record_ID)
			{
				log.warning("Registration Not found=" + Record_ID);
				reg = null;
			}
			else if (user != null)
			{
				if (reg.getC_BPartner_ID() != user.getC_BPartner_ID())
				{
					log.warning("Registration for different BP - AD_User_ID=" 
						+ AD_User_ID + "(" + user.getEMail()
						+ "), BP RegistrationBP=" + reg.getC_BPartner_ID() 
						+ "<>UserBP=" + user.getC_BPartner_ID());
					reg = null;
				}
				if (!password.equals(user.getPassword()))
				{
					log.warning("Password does not match - AD_User_ID=" 
						+ AD_User_ID + "(" + user.getEMail() + ")");
					//	??
				}
			}
		}
		if (reg == null)
		{
			log.fine("New Registration");
			reg = new MRegistration (ctx, name, allowPublish, inProduction, startDate, null);
			Record_ID = 0;
		}
		//	Common Update
		reg.setDescription(description);
		reg.setRemote_Addr(request.getRemoteAddr());
		reg.setRemote_Host(request.getRemoteHost());
		//	User
		if (user != null)
		{
			reg.setAD_User_ID(user.getAD_User_ID());
			reg.setC_BPartner_ID(user.getC_BPartner_ID());
		}
		if (reg.save())
		{
			if (Record_ID == 0)
				reg.loadAttributeValues(request);	//	new
			else
				reg.updateAttributeValues(request);	//	existing
			sendAnswer (response, THANKS + " Record_ID=" + reg.getA_Registration_ID());
		}
		else
		{
			log.log(Level.SEVERE, "Registration not saved");
			sendAnswer (response, PROBLEM + " Record_ID=0");
		}
		return true;
	}	//	processSystemRegistration
	
	/**
	 * 	Send Answer
	 * 	@param response response
	 * 	@param answer answer
	 * 	@throws IOException
	 */
	private void sendAnswer (HttpServletResponse response, String answer)
		throws IOException
	{
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();     //  with character encoding support
		out.print(answer);
		out.flush();
	}	//	sendAnswer
	
	/**************************************************************************
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
		log.info("From " + request.getRemoteHost() + " - " + request.getRemoteAddr());

		//  Get Session attributes
		HttpSession session = request.getSession(true);
		session.removeAttribute(WebSessionCtx.HDR_MESSAGE);
		//
		Properties ctx = JSPEnv.getCtx(request);
		WebUser wu = (WebUser)session.getAttribute(WebUser.NAME);
		if (wu == null)
		{
			log.warning("No web user");
			response.sendRedirect("loginServlet?ForwardTo=registration.jsp");	//	entry
			return;
		}
		int A_Registration_ID = WebUtil.getParameterAsInt(request, P_REGISTRATION_ID);
		MRegistration reg = null;
		if (A_Registration_ID > 0)
			reg = new MRegistration (ctx, A_Registration_ID, null);
		if (reg == null)
		{
			reg = new MRegistration (ctx, 0, null);
			A_Registration_ID = 0;
		}
		//
		String name = WebUtil.getParameter (request, "Name");
		if (name == null || name.length() == 0)
		{
			WebUtil.createForwardPage(response, "Name is Mandatory", "registrations.jsp", 4);
			return;
		}
		reg.setC_BPartner_ID(wu.getBpartnerID());
		reg.setName(name);
		String description = WebUtil.getParameter (request, "Description");
		if (description != null && description.length() > 0)
			reg.setDescription(description);
		boolean isInProduction = WebUtil.getParameterAsBoolean (request, "IsInProduction");
		reg.setIsInProduction(isInProduction);
		Timestamp assetServiceDate = WebUtil.getParameterAsDate (request, "AssetServiceDate");
		if (assetServiceDate == null)
			assetServiceDate = new Timestamp(System.currentTimeMillis());
		reg.setAssetServiceDate(assetServiceDate);
		boolean isAllowPublish = WebUtil.getParameterAsBoolean (request, "IsAllowPublish");
		reg.setIsAllowPublish(isAllowPublish);
		if (reg.save())
		{
			if (A_Registration_ID == 0)
				reg.loadAttributeValues(request);	//	new
			else
				reg.updateAttributeValues(request);	//	existing
			WebUtil.createForwardPage(response, THANKS, "registrations.jsp", 3);
		}
		else
		{
			log.log(Level.SEVERE, "Registration not saved");
			WebUtil.createForwardPage(response, PROBLEM, "registrations.jsp", 3);
		}
	}   //  doPost

}   //  RegistrationSerlet
