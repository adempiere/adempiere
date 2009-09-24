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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.ecs.xhtml.a;
import org.apache.ecs.xhtml.input;
import org.compiere.util.CLogger;
import org.compiere.util.HtmlCode;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUser;

/**
 *  Login Link.
 * 	Creates Login/Logout Link
 *  <pre>
 *  <cws:loginLink />
 *  Variable used - "webUser"
 *	</pre>
 *
 *  @author Jorg Janke
 *  @version $Id: LoginLinkTag.java,v 1.3 2006/07/30 00:53:21 jjanke Exp $
 */
public class LoginLinkTag extends TagSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3808804739017792308L;
	/**	Logger							*/
	protected static CLogger	log = CLogger.getCLogger (LoginLinkTag.class);

	/**
	 *  Start Tag
	 *  @return SKIP_BODY
	 * 	@throws JspException
	 */
	public int doStartTag() throws JspException
	{
		Properties ctx = JSPEnv.getCtx((HttpServletRequest)pageContext.getRequest());
		//
		WebUser wu = getWebUser(ctx);
		if (wu == null)
			pageContext.getSession().removeAttribute(WebUser.NAME);
		else
			pageContext.getSession().setAttribute (WebUser.NAME, wu);
		//
		String serverContext = ctx.getProperty(WebSessionCtx.CTX_SERVER_CONTEXT);
	//	log.fine("doStartTag - ServerContext=" + serverContext);
		HtmlCode html = null;
		if (wu != null && wu.isValid())
			html = getWelcomeLink (serverContext, wu);
		else
			html = getLoginLink (serverContext);
		//
		JspWriter out = pageContext.getOut();
		/**
		//	Delete Cookie Call
		if (cookieUser != null && !cookieUser.equals(" "))
		{
			log.fine("- Cookie=" + cookieUser);
			html.addElement(" ");
			a a = new a("loginServlet?mode=deleteCookie");
			a.setClass("menuDetail");
			a.addElement("(Delete&nbsp;Cookie)");
			html.addElement(a);
		}
		**/
		html.output(out);
		//
		//
		return (SKIP_BODY);
	}   //  doStartTag

	/**
	 * 	End Tag
	 * 	@return EVAL_PAGE
	 * 	@throws JspException
	 */
	public int doEndTag() throws JspException
	{
		return EVAL_PAGE;
	}	//	doEndTag


	/**
	 *	Get WebUser.
	 * 	@param ctx context
	 * 	@return Web User or null
	 */
	private WebUser getWebUser (Properties ctx)
	{
		String address = pageContext.getRequest().getRemoteAddr();
		//	Get stored User
		WebUser wu = (WebUser)pageContext.getSession().getAttribute (WebUser.NAME);
		if (wu != null)
		{
			log.finest("(" + address + ") - SessionContext: " + wu);
		}
		else
		{
			wu = (WebUser)pageContext.getAttribute(WebUser.NAME);
			if (wu != null)
				log.finest ("(" + address + ") - Context: " + wu);
		}
		if (wu != null)
			return wu;

		//	Check Cookie
		String cookieUser = JSPEnv.getCookieWebUser ((HttpServletRequest)pageContext.getRequest());
		if (cookieUser == null || cookieUser.trim().length() == 0)
			log.finer ("(" + address + ") - no cookie");
		else
		{
			//	Try to Load
			wu = WebUser.get (ctx, cookieUser);
			log.finer ("(" + address + ") - Cookie: " + wu);
		}
		if (wu != null)
			return wu;
		//
		return null;
	}	//	getWebUser

	
	/**************************************************************************
	 * 	Get Login Link
	 * 	@param	serverContext server context
	 * 	@return link
	 */
	private HtmlCode getLoginLink(String serverContext)
	{
		HtmlCode retValue = new HtmlCode();
		//	Login button
		input button = new input(input.TYPE_BUTTON, "Login", "Login");
		button.setOnClick("window.top.location.replace('https://" + serverContext + "/loginServlet');");
		retValue.addElement(button);

		/**	Link
		a a = new a("https://" + serverContext + "/login.jsp");
		a.setClass("menuMain");
		a.addElement("Login");
		retValue.addElement(a);
		**/

		retValue.addElement(" ");
		return retValue;
	}	//	getLoginLink

	/**
	 * 	Get Welcome Link
	 * 	@param	serverContext server Context
	 * 	@param wu web user
	 * 	@return link
	 */
	private HtmlCode getWelcomeLink(String serverContext, WebUser wu)
	{
		HtmlCode retValue = new HtmlCode();
		//
		a a = new a("https://" + serverContext + "/login.jsp");
		a.setClass("menuMain");
		String msg = "Welcome " + wu.getName();
		a.addElement(msg);
		retValue.addElement(a);
		//
		retValue.addElement(" &nbsp; ");
		if (wu.isLoggedIn())
		{
			//	Verify
			if (!wu.isEMailVerified())
			{
				input button = new input(input.TYPE_BUTTON, "Verify", "Verify EMail");
				button.setOnClick("window.top.location.replace('emailVerify.jsp');");
				retValue.addElement(button);
				retValue.addElement(" ");
			}
			
			//	Update
			input button = new input(input.TYPE_BUTTON, "Update", "Update User Info");
			button.setOnClick("window.top.location.replace('update.jsp');");
			retValue.addElement(button);
			retValue.addElement(" ");
			
			//	Logout
			button = new input(input.TYPE_BUTTON, "Logout", "Logout");
			button.setOnClick("window.top.location.replace('loginServlet?mode=logout');");
			retValue.addElement(button);

			/** Link
			a = new a ("loginServlet?mode=logout");
			a.setClass ("menuMain");
			a.addElement ("Logout");
			retValue.addElement (a);
			**/
		}
		else
		{
			input button = new input (input.TYPE_BUTTON, "Login", "Login");
			button.setOnClick ("window.top.location.replace('https://" + serverContext + "/login.jsp');");
			retValue.addElement (button);
		}
		retValue.addElement (" ");
		//
		return retValue;
	}	//	getWelcomeLink

}	//	LoginLinkTag
