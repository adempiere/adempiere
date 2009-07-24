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

import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.util.Env;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUser;
import org.compiere.util.WebUtil;
 
/**
 *  JSP Environment Utilities
 *
 *  @author Jorg Janke
 *  @version $Id: JSPEnv.java,v 1.5 2006/09/21 20:45:30 jjanke Exp $
 */
public class JSPEnv
{
	/**
	 * 	Get Context from Session
	 *	@param request request
	 * 	@return properties
	 */
	public static Properties getCtx (HttpServletRequest request)
	{
		WebSessionCtx wsc = WebSessionCtx.get(request);
		HttpSession session = request.getSession(true);

		//	Add/set current user
		WebUser wu = WebUser.get(request);
		if (wu != null)
		{
			int AD_User_ID = wu.getAD_User_ID();
			Env.setContext(wsc.ctx, "#AD_User_ID", AD_User_ID);		//	security
		}

		//	Finish
		session.setMaxInactiveInterval(1800);	//	30 Min	HARDCODED
		String info = (String)wsc.ctx.get(WebSessionCtx.HDR_INFO);
		if (info != null)
			session.setAttribute(WebSessionCtx.HDR_INFO, info);
		return wsc.ctx;
	}	//	getCtx

	/*************************************************************************/

	private final static String		COOKIE_NAME = "AdempiereWebUser";

	/**
	 * 	Get Web User from Cookie
	 * 	@param request request with cookie
	 * 	@return web user or null
	 */
	public static String getCookieWebUser (HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return null;
		for (int i = 0; i < cookies.length; i++)
		{
			if (COOKIE_NAME.equals(cookies[i].getName()))
				return cookies[i].getValue();
		}
		return null;
	}	//	getCookieWebUser

	/**
	 * 	Add Cookie with web user
	 * 	@param request request (for context path)
	 * 	@param response response to add cookie
	 * 	@param webUser email address
	 */
	public static void addCookieWebUser (HttpServletRequest request, HttpServletResponse response, String webUser)
	{
		Cookie cookie = new Cookie(COOKIE_NAME, webUser);
		cookie.setComment("Adempiere Web User");
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(2592000);      //  30 days in seconds   60*60*24*30
		response.addCookie(cookie);
	}	//	setCookieWebUser

	/**
	 * 	Remove Cookie with web user by setting user to _
	 * 	@param request request (for context path)
	 * 	@param response response to add cookie
	 */
	public static void deleteCookieWebUser (HttpServletRequest request, HttpServletResponse response)
	{
		// Moved over to WebUtil as needed more general also for WebCM
		org.compiere.util.WebUtil.deleteCookieWebUser (request, response, COOKIE_NAME);
	}	//	deleteCookieWebUser

	/**
	 * 	Get Remote From info
	 * 	@param request request
	 * 	@return remore info
	 */
	public static String getFrom (HttpServletRequest request)
	{
		return WebUtil.getFrom (request);
	}	//	getFrom
	
	/**************************************************************************
	 * 	Send EMail
	 *	@param request request
	 *	@param to web user
	 *	@param msgType see MMailMsg.MAILMSGTYPE_*
	 *	@param parameter object array with parameters
	 * 	@return mail EMail.SENT_OK or error message 
	 */
	public static String sendEMail (HttpServletRequest request, WebUser to,
		String msgType, Object[] parameter)
	{
		return WebUtil.sendEMail(request, to, msgType, parameter);
	}	//	sendEMail

}	//	JSPEnv
