/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.
 * This program is free software; you can redistribute it and/or modify it
 * under the terms version 2 of the GNU General Public License as published
 * by the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 * You may reach us at: ComPiere, Inc. - http://www.adempiere.org/license.html
 * 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA or info@adempiere.org 
 *****************************************************************************/
package org.compiere.util;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import org.compiere.model.*;
import java.util.logging.*;


/**
 *	WebLogin provides a standard interface to login from Webapps like WStore or M
 *	
 *  @author Yves Sandfort
 *  @version $Id$
 */
public class WebLogin
{
	/**	Logging								*/
	private CLogger				log = CLogger.getCLogger(getClass());
	private final static String		COOKIE_NAME = "adempiereWebUser";
	/** Forward Parameter					*/
	private String		P_ForwardTo = "ForwardTo";
	/** SalesRep Parameter					*/
	private String		P_SalesRep_ID = "SalesRep_ID";
	/** EMail Parameter					*/
	private String		P_EMail = "EMail";
	/** Password Parameter					*/
	private String		P_Password = "Password";
	/** Mode/Action Parameter				*/
	private String		P_Action = "Mode";
	/** Login Page							*/
	private String		LOGIN_RelURL = "/login.jsp";
    /** Update Page                         */
    private String      update_page = "/update.jsp";
    /** Message								*/
    private String      message = null;
	/** Context								*/
	private Properties 			ctx;
	/** HttpServletRequest					*/
	private HttpServletRequest	request;
	/** HttpServletResponse					*/
	private HttpServletResponse	response;
	/** HttpSession						*/
	private HttpSession			session;
	/** adressConfirm						*/
	private boolean addressConfirm;
	/** forward								*/
	private String forward;
	/** SalesRep							*/
	private String salesRep;
	/** EMail								*/
	private String email;
	/** Password							*/
	private String password;
	/** WebUser								*/
	private WebUser wu;
	
	/**
	 * 	WebLogin
	 *	@param t_request
	 *	@param t_response
	 *	@param t_ctx
	 */
	public WebLogin (HttpServletRequest t_request, HttpServletResponse t_response, Properties t_ctx)
	{
		request = t_request;
		response = t_response;
		ctx = t_ctx;
		// We will check the Request to see whether Parameters are overwritten
		if (request.getParameter ("P_ForwardTo")!=null) 
			setP_ForwardTo (request.getParameter("P_ForwardTo"));
		if (request.getParameter ("SalesRep_ID")!=null)
			setP_SalesRep_ID (request.getParameter ("SalesRep_ID"));
		if (request.getParameter ("P_EMail")!=null)
			setP_EMail (request.getParameter ("P_EMail"));
		if (request.getParameter ("P_Password")!=null)
			setP_Password (request.getParameter ("P_Password"));
		if (request.getParameter ("P_Action")!=null)
			setP_Action (request.getParameter ("P_Action"));
		if (request.getParameter ("LOGIN_RelURL")!=null)
			setLogin_RelURL (request.getParameter ("LOGIN_RelURL"));
		if (request.getParameter ("update_page")!=null)
			setLogin_RelURL (request.getParameter ("update_page"));
	}
	
	/**
	 * 	init will initialize the WebLogin Object for further use
	 *	@return true if init was successfull
	 */
	public boolean init()
	{
		session = request.getSession(true);	//	create new
		forward = WebUtil.getParameter (request, P_ForwardTo);			//	get forward from request
		if (forward != null)
			session.setAttribute(P_ForwardTo, forward);
		salesRep = WebUtil.getParameter (request, P_SalesRep_ID);		//	get SalesRep from request
		if (salesRep != null)
			session.setAttribute(P_SalesRep_ID, salesRep);

		//	Get Base Info
		email = WebUtil.getParameter (request, P_EMail);
		if (email == null)
			email = "";
		email = email.trim();
		if (email != null)
			session.setAttribute (P_EMail, email);
		password = WebUtil.getParameter (request, P_Password);
		if (password == null)
			password = "";	//	null loads w/o check
		password = password.trim();
		if (session.getAttribute (WebInfo.NAME)!=null) 
		{
			WebInfo wi = (WebInfo)session.getAttribute(WebInfo.NAME);
			wu = wi.getWebUser ();
		}
		return true;
	}
	
	/**
	 * 	action run functions against the Login process
	 *	@return true if successfull
	 *	@throws IOException
	 *	@throws ServletException
	 */
	public boolean action() throws IOException, ServletException
	{
		//	Mode
		String mode = WebUtil.getParameter (request, P_Action);
		boolean deleteCookie = "deleteCookie".equals(mode);
		if (deleteCookie)
		{
			log.fine("** deleteCookie");
			WebUtil.deleteCookieWebUser (request, response, COOKIE_NAME);
		}
		boolean logout = "logout".equals(mode);
		if (logout || deleteCookie)
		{
			log.fine("** logout");
			if (session != null)
			{
				MSession cSession = MSession.get (ctx, false);
				if (cSession != null)
					cSession.logout();
				//
				wu = (WebUser)session.getAttribute(WebUser.NAME);
				if (wu != null)
					wu.logout();
	
	            session.removeAttribute(WebUser.NAME);
	            session.setMaxInactiveInterval(1);
				session.invalidate ();
			}
			//	Forward to unsecure /
			WebUtil.createForwardPage(response, "Logout", "http://" + request.getServerName() + "/", 2);
		}
		//	Send EMail				***	Send Password EMail Request
		else if ("SendEMail".equals(mode))
		{
			log.info("** send mail");
			wu = WebUser.get (ctx, email);			//	find it
			if (!wu.isEMailValid())
				wu.setPasswordMessage("EMail not found in system");
			else
			{
				wu.setPassword();		//	set password to current
				//
				String msg = WebUtil.sendEMail (request, wu,
					MMailMsg.MAILMSGTYPE_UserPassword, new Object[]{
						request.getServerName(),
						wu.getName(),
						WebUtil.getFrom(request),
						wu.getPassword()});
				if (EMail.SENT_OK.equals(msg))
					wu.setPasswordMessage ("EMail sent");
				else
					wu.setPasswordMessage ("Problem sending EMail: " + msg);
			}
			forward = getLogin_RelURL ();
		}	//	SendEMail
		//	Login
		else if ("Login".equals(mode))
		{
			log.info("** login " + email + "/" + password);
			//	add Cookie
			WebUtil.addCookieWebUser(request, response, email, COOKIE_NAME);

			//	Always re-query
			wu = WebUser.get (ctx, email, password, false);
			wu.login(password);
			//	Password valid
			if (wu.isLoggedIn())
			{
				if (forward==null || forward.equals(getLogin_RelURL ()))
					forward = "/index.jsp";
				//	Create Session with User ID
				MSession cSession = MSession.get (ctx, request.getRemoteAddr(), 
					request.getRemoteHost(), session.getId());
				if (cSession != null)
					cSession.setWebStoreSession(true);
			}
			else
			{
				forward = getLogin_RelURL ();
				log.fine("- PasswordMessage=" + wu.getPasswordMessage());
			}
			// If no session exists or is not loaded, load or create it
			if (session==null) 
				session = request.getSession (true);
			
			session.setAttribute (WebInfo.NAME, new WebInfo (ctx, wu));
		}	//	Login

		//	Login New
		else if ("LoginNew".equals(mode))
		{
			log.info("** loginNew");
			WebUtil.addCookieWebUser(request, response, "", COOKIE_NAME);
			wu =  WebUser.get (ctx, "");
			forward = getLogin_RelURL ();
		}

		//	Submit - update/new Contact
		else if ("Submit".equals(mode))
		{
			log.info("** submit " + email + "/" + password + " - AddrConf=" + addressConfirm);
			//	we have a record for address update
			if (wu != null && wu.isLoggedIn() && addressConfirm)	//	address update
				;
			else	//	Submit - always re-load user record
				wu = WebUser.get (ctx, email, null, false); //	load w/o password check direct
			//
			if (wu.getAD_User_ID() != 0)		//	existing BPC
			{
				String passwordNew = WebUtil.getParameter (request, "PasswordNew");
				if (passwordNew == null)
					passwordNew = "";
				boolean passwordChange = passwordNew.length() > 0 && !passwordNew.equals(password);
				if (addressConfirm || wu.login (password))
				{
					//	Create / set session
					if (wu.isLoggedIn())
					{
						MSession cSession = MSession.get (ctx, request.getRemoteAddr(), 
							request.getRemoteHost(), session.getId());
						if (cSession != null)
							cSession.setWebStoreSession(true);
					}
					//
					if (passwordChange)
						log.fine("- update Pwd " + email + ", Old=" + password + ", DB=" + wu.getPassword() + ", New=" + passwordNew);
					if (WebUtil.updateFields(request, wu, passwordChange))
					{
						if (passwordChange)
							session.setAttribute(WebSessionCtx.HDR_MESSAGE, "Password changed");
					}
					else
					{
						forward = getLogin_RelURL ();
						log.warning(" - update not done");
					}
				}
				else
				{
					forward = getLogin_RelURL ();
					session.setAttribute(WebSessionCtx.HDR_MESSAGE, "Email/Password not correct");
					log.warning(" - update not confirmed");
				}
			}
			else	//	new
			{
				log.fine("** new " + email + "/" + password);
				wu.setEmail (email);
				wu.setPassword (password);
				if (WebUtil.updateFields (request, wu, true))
				{
					if (wu.login(password))
					{
						session.setAttribute (WebInfo.NAME, new WebInfo (ctx, wu));
						//	Create / set session
						MSession cSession = MSession.get (ctx, request.getRemoteAddr(), 
							request.getRemoteHost(), session.getId());
						if (cSession != null)
							cSession.setWebStoreSession(true);
					}
					else
						forward = getLogin_RelURL ();
				}
				else
				{
					log.fine("- failed - " + wu.getSaveErrorMessage() + " - " + wu.getPasswordMessage());
					forward = getLogin_RelURL ();
				}
			}	//	new
			if (wu!=null)
				session.setAttribute (WebInfo.NAME, new WebInfo (ctx, wu));
		}	//	Submit

		else if("email".equals(mode))
        {
            String email = WebUtil.getParameter (request, "EMail");
            if (email == null)
                email = "";
            email = email.trim();

            String emailNew = WebUtil.getParameter (request, "EMailNew");
            if (emailNew == null)
                emailNew = "";

            email = email.trim();
            if((emailNew.length() == 0)||(emailNew.equals(email)))
            {
                setMessage("New EMail invalid.");
                return false;
            }

            if(!WebUtil.isEmailValid(emailNew))
            {
                setMessage("New EMail invalid.");
                return false;
            }

            wu.setEmail(emailNew);
            wu.save();
            session.setAttribute(WebSessionCtx.HDR_MESSAGE, "EMail Address Changed");
            session.setAttribute(WebInfo.NAME, new WebInfo(ctx, wu));
        }

        else if("password".equals(mode))
        {
    		if (wu == null)
    		{
    			log.warning("No web user");
    			return false;
    		}

            String password = WebUtil.getParameter (request, "Password");
            if (password == null)
                password = "";	//	null loads w/o check
            password = password.trim();

            if(!wu.login(password))
            {
                setMessage("Email/Password not correct");
                return false;
            }

            MSession cSession = MSession.get (ctx, request.getRemoteAddr(), request.getRemoteHost(), session.getId());
            if (cSession != null)
                cSession.setWebStoreSession(true);

            String passwordNew = WebUtil.getParameter (request, "PasswordNew");
            if (passwordNew == null)
                passwordNew = "";

            password = password.trim();
            if( (passwordNew.length() == 0) || (passwordNew.equals(password)))
            {
                setMessage("New Password invalid.");
                return false;
            }

            wu.setPasswordMessage(null);
            wu.setPassword(passwordNew);
            if(wu.getPasswordMessage() != null)
            {
                setMessage("New Password invalid.");
                return false;
            }
            wu.save();
            session.setAttribute(WebSessionCtx.HDR_MESSAGE, "Password Changed");
            session.setAttribute(WebInfo.NAME, new WebInfo(ctx, wu));
        }

        else if("address".equals(mode))
        {
            wu.setC_Country_ID(WebUtil.getParamOrNull(request, "C_Country_ID"));
            wu.setC_Region_ID(WebUtil.getParamOrNull(request, "C_Region_ID"));
            wu.setRegionName(WebUtil.getParamOrNull(request, "RegionName"));
            wu.setName(WebUtil.getParamOrNull(request, "Name"));
            wu.setCompany(WebUtil.getParamOrNull(request, "Company"));
            wu.setTitle(WebUtil.getParamOrNull(request, "Title"));
            wu.setAddress(WebUtil.getParamOrNull(request, "Address"));
            wu.setAddress2(WebUtil.getParamOrNull(request, "Address2"));
            wu.setCity(WebUtil.getParamOrNull(request, "City"));
            wu.setPostal(WebUtil.getParamOrNull(request, "Postal"));
            wu.setPhone(WebUtil.getParamOrNull(request, "Phone"));
            wu.setFax(WebUtil.getParamOrNull(request, "Fax"));
            wu.save();
            session.setAttribute(WebSessionCtx.HDR_MESSAGE, "Contact Information Changed");
            session.setAttribute(WebInfo.NAME, new WebInfo(ctx, wu));
        }
		
        else if ("EMailVerify".equals(mode))
        {
    		if (wu == null)
    		{
    			log.warning("No web user");
    			return false;
    		}

    		log.info(forward + " - " + wu.toString());

    		String cmd = WebUtil.getParameter(request, "ReSend");
    		if (cmd != null && cmd.length() > 1)
    			WebUtil.resendCode(request, wu);
    		else
    			wu.setEMailVerifyCode(WebUtil.getParameter(request, "VerifyCode"), request.getRemoteAddr());

        }
		
        else if ("bankaccountach".equals(mode))
        {
    		if (wu == null)
    		{
    			log.warning("No web user");
    			return false;
    		}

    		log.info(forward + " - " + wu.toString());

    		MBPBankAccount thisBPBankAccount = wu.getBankAccount (true);
    		// As this sets bankaccountach 
    		thisBPBankAccount.setIsACH (true);
    		thisBPBankAccount.setA_City (WebUtil.getParamOrNull (request, "A_City"));
    		thisBPBankAccount.setA_Name (WebUtil.getParamOrNull (request, "A_Name"));
    		thisBPBankAccount.setAccountNo (WebUtil.getParamOrNull (request, "AccountNo"));
    		thisBPBankAccount.setRoutingNo (WebUtil.getParamOrNull (request, "RoutingNo"));
    		thisBPBankAccount.save ();
        }
		else
			log.log(Level.WARNING, "Unknown request='" + mode + "'");

		return true;
	}
	
	/** 
	 * setMessage to set a Message
	 * @param newVal 
	 */
	public void setMessage(String newVal)
	{
		if (newVal!=null)
			message = newVal;
	}
	
	/**
	 * getMessage back
	 * @return Message
	 */
	public String getMessage()
	{
		return message;
	}
	
	/**
	 * 	setP_ForwardTo to overwrite default "ForwardTo" Parameter
	 *	@param newVal new value to look for
	 */
	public void setP_ForwardTo(String newVal) 
	{
		if (newVal!=null) 
			P_ForwardTo = newVal;
	}
	
	/**
	 * 	getP_ForwardTo
	 *	@return ForwardTo request parameter
	 */
	public String getP_ForwardTo() {
		return P_ForwardTo;
	}

	/**
	 * 	setP_EMail to overwrite default "EMail" Parameter
	 *	@param newVal new value to look for
	 */
	public void setP_EMail(String newVal) 
	{
		if (newVal!=null) 
			P_EMail = newVal;
	}
	
	/**
	 * 	getP_EMail
	 *	@return EMail request parameter
	 */
	public String getP_EMail() {
		return P_EMail;
	}

	/**
	 * 	setP_Password to overwrite default "Password" Parameter
	 *	@param newVal new value to look for
	 */
	public void setP_Password(String newVal) 
	{
		if (newVal!=null) 
			P_Password = newVal;
	}
	
	/**
	 * 	getP_Password
	 *	@return Password request parameter
	 */
	public String getP_Password() {
		return P_Password;
	}

	/**
	 * 	setP_SalesRep_ID to overwrite default "SalesRep_ID" Parameter
	 *	@param newVal new value to look for
	 */
	public void setP_SalesRep_ID(String newVal) 
	{
		if (newVal!=null) 
			P_SalesRep_ID = newVal;
	}
	
	/**
	 * 	getP_SalesRep_ID
	 *	@return SalesRep_ID request parameter
	 */
	public String getP_SalesRep_ID() {
		return P_SalesRep_ID;
	}

	/**
	 * 	setP_Action to overwrite default "Action/Mode" Parameter
	 *	@param newVal new value to look for
	 */
	public void setP_Action(String newVal) 
	{
		if (newVal!=null) 
			P_Action = newVal;
	}
	
	/**
	 * 	getP_SalesRep_ID
	 *	@return SalesRep_ID request parameter
	 */
	public String getP_Action() {
		return P_Action;
	}

	/**
	 * 	setLogin_RelURL to overwrite default Login Relative URL
	 *	@param newVal new relative URL inside Domain to goto
	 */
	public void setLogin_RelURL(String newVal) 
	{
		if (newVal!=null) 
			LOGIN_RelURL = newVal;
	}
	
	/**
	 * 	getLogin_RelURL
	 *	@return Login_RelURL request parameter
	 */
	public String getLogin_RelURL() {
		return LOGIN_RelURL;
	}

	/**
	 * 	setLogin_RelURL to overwrite default Login Relative URL
	 *	@param newVal new relative URL inside Domain to goto
	 */
	public void setUpdate_page(String newVal) 
	{
		if (newVal!=null) 
			update_page = newVal;
	}
	
	/**
	 * 	getLogin_RelURL
	 *	@return Login_RelURL request parameter
	 */
	public String getUpdate_page() {
		return update_page;
	}
	
	/**
	 * 	setForward updates Forward URL
	 *	@param newVal
	 */
	public void setForward(String newVal)
	{
		if (newVal!=null)
			forward = newVal;
	}

	/**
	 * 	getForward
	 *	@return URL to forward request on to
	 */
	public String getForward() {
		return forward;
	}

	/**
	 * 	getSalesRep_ID
	 *	@return SalesRep_ID of the SalesRep_ID in the Request
	 */
	public String getSalesRep_ID() {
		return salesRep;
	}
	
	/**
	 * 	setAddressConfirm 
	 *	@param newVal new addressConfirm
	 */
	public void setAddressConfirm(boolean newVal) 
	{
			addressConfirm = newVal;
	}
	
	/**
	 * 	getAdressConfirm
	 *	@return boolean addressConfirm
	 */
	public boolean getAddressConfirm() {
		return addressConfirm;
	}
	
	public WebUser getWebUser() {
		return wu;
	}
}