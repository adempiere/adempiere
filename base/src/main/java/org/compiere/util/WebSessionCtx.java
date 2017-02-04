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
package org.compiere.util;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.compiere.model.MClient;
import org.compiere.model.MStore;


/**
 *	Web Session Context Value Object
 *	
 *  @author Jorg Janke
 *  @version $Id: WebSessionCtx.java,v 1.6 2006/09/23 11:04:19 comdivision Exp $
 */
public class WebSessionCtx implements Serializable
{
	/**	SV */
	private static final long serialVersionUID = -5069858671373130221L;

	/**
	 * 	Get/create Web Session Context
	 *	@param request request
	 *	@return ctx or null
	 */
	public static WebSessionCtx get (HttpServletRequest request, int W_Store_ID)
	{
		HttpSession session = request.getSession(false);
		if (session == null)
			session = request.getSession(true);
		if (session == null)
			return null;
		WebSessionCtx wsc = (WebSessionCtx)session.getAttribute(NAME);
		//	Create New
		if (wsc == null)
		{
			wsc = new WebSessionCtx(request, W_Store_ID);
			session.setAttribute(NAME, wsc);
		}
		return wsc;
	}	//	get

	/**
	 * 	Get/create Web Session Context
	 *	@param request request
	 *	@return ctx or null
	 */
	public static WebSessionCtx get (HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if (session == null)
			session = request.getSession(true);
		if (session == null)
			return null;
		WebSessionCtx wsc = (WebSessionCtx)session.getAttribute(NAME);
		//	Create New
		if (wsc == null)
		{
			wsc = new WebSessionCtx (request);
			session.setAttribute(NAME, wsc);
		}
		wsc.setLanguage(request);
		return wsc;
	}	//	get
	
	/** Context				*/
	public final static String		CTX_SERVER_CONTEXT = "context";
	/** Dodument Directory	*/
	public final static String		CTX_DOCUMENT_DIR = "documentDir";
	/** Header (Error) Message			*/
	public final static String		HDR_MESSAGE = "hdrMessage";
	/** Header Info Message				*/
	public final static String		HDR_INFO = "hdrInfo";

	/**	Logger							*/
	static private CLogger			log = CLogger.getCLogger (WebSessionCtx.class);
	/** Cache 60 minutes				*/
	static private CCache<Integer,Properties> s_cacheCtx = new CCache<Integer,Properties>("WebSessionCtx", 30, 60);
	
	/**************************************************************************
	 * 	Web Session Context
	 * 	@param request request
	 */
	private WebSessionCtx (HttpServletRequest request)
	{
		log.info (request.getContextPath() + " (" + request.getRemoteAddr() 
			+ " - " + request.getLocale() + ") #" + counter);
		ctx = new Properties();
		setLanguage(request);
		
		HttpSession session = request.getSession(false);
		
		//	Add Servlet Init Parameters (webStore/src/web/WEB-INF/web.xml)
		ServletContext sc = session.getServletContext();
		Enumeration en = sc.getInitParameterNames();
		while (en.hasMoreElements())
		{
			String key = (String)en.nextElement();
			String value = sc.getInitParameter(key);
			ctx.setProperty(key, value);
			log.config (key + "=" + value); 
		}

		setWStore (request.getContextPath());
		ctx = getDefaults ();
		
		//	ServerContext	- dev2/wstore
		ctx.put(CTX_SERVER_CONTEXT, request.getServerName() + request.getContextPath());
		//	Make Context directly availabe to jsp's
		session.setAttribute("ctx", ctx);
		//
		log.fine("#" + ctx.size());
	}	//	WebSessionCtx

	/**************************************************************************
	 * 	Web Session Context
	 * 	@param request request
	 */
	private WebSessionCtx (HttpServletRequest request, int W_Store_ID)
	{
		log.info (request.getContextPath() + " (" + request.getRemoteAddr() 
			+ " - " + request.getLocale() + ") #" + counter);
		ctx = new Properties();
		setLanguage(request);
		
		HttpSession session = request.getSession(false);
		
		//	Add Servlet Init Parameters (webStore/src/web/WEB-INF/web.xml)
		ServletContext sc = session.getServletContext();
		Enumeration en = sc.getInitParameterNames();
		while (en.hasMoreElements())
		{
			String key = (String)en.nextElement();
			String value = sc.getInitParameter(key);
			ctx.setProperty(key, value);
			log.config (key + "=" + value); 
		}

		setWStore (W_Store_ID);
		ctx = getDefaults ();
		
		//	ServerContext	- dev2/wstore
		ctx.put(CTX_SERVER_CONTEXT, request.getServerName() + request.getContextPath());
		//	Make Context directly availabe to jsp's
		session.setAttribute("ctx", ctx);
		//
		log.fine("#" + ctx.size());
	}	//	WebSessionCtx

	/**	Sessition Attribute Name			*/
	public static final String	NAME = "WebSessionCtx";
	
	/**	Static counter				*/
	public static int 		s_counter = 0;
	/** Instance Counter			*/
	public int				counter = ++s_counter;
	
	
	/** Session Context 			*/
	public Properties		ctx = null;
	/**	Session Language			*/
	public Language			language = null;
	
	/** Localized Date format       */
	public SimpleDateFormat dateFormat = null;
	/** Localized Timestamp format  */
	public SimpleDateFormat dateTimeFormat = null;

	/** Localized Amount format    */
	public DecimalFormat 	amountFormat = null;
	/** Localized Integer format    */
	public DecimalFormat 	integerFormat = null;
	/** Localized Number format     */
	public DecimalFormat 	numberFormat = null;
	/** Localized Quantity format   */
	public DecimalFormat 	quantityFormat = null;

	/** Login Info					*/
	public String			loginInfo = "";
	/** Web Store					*/
	public MStore			wstore = null;
	
	/**
	 * 	Set Web Store
	 *	@param contextPath web server context path
	 */
	private void setWStore (String contextPath)
	{
		//	get from context
		int W_Store_ID = Env.getContextAsInt(ctx, "W_Store_ID");
		if (W_Store_ID != 0)
		{
			wstore = MStore.get(ctx, W_Store_ID);
			if (wstore.getW_Store_ID() != 0)
			{
				log.info("From web.xml - " + wstore);
				return;
			}
		}
		if ("/adempiere".equals(contextPath))	//	HTML UI
			return;
		
		// Modifications for POSterita
		if(wstore == null)
		{
			wstore = MStore.get(ctx, contextPath);
		}
		// End Modifications for Posterita 
		
		if (wstore == null)
			throw new IllegalStateException("No Web Store found - " + contextPath);
	}	//	setWStore
	
	
	/**
	 * 	Set Web Store
	 *	@param W_Store_ID Web Store ID
	 */
	public void setWStore (int W_Store_ID)
	{
		//	get from context
		if (W_Store_ID != 0)
		{
			wstore = MStore.get(ctx, W_Store_ID);
			if (wstore.getW_Store_ID() != 0)
			{
				log.info("From web.xml - " + wstore);
				return;
			}
		}
		if (wstore == null)
			throw new IllegalStateException("No Web Store found - ID: " + W_Store_ID);
	}	//	setWStore
 	
	/**
	 * 	Get Web Store Defaults
	 * 	@return context
	 */
	private Properties getDefaults ()
	{
		//	No Web Store
		if (wstore == null)
			return new Properties();
		//
		Integer key = new Integer (wstore.getW_Store_ID());
		Properties newCtx = (Properties)s_cacheCtx.get(key);
		
		/**	Create New Context		*/
		if (newCtx == null)
		{
			log.info(wstore.getWebContext());
			newCtx = new Properties();
			//	copy explicitly
			Enumeration e = ctx.keys();
			while (e.hasMoreElements())
			{
				String pKey = (String)e.nextElement();
				newCtx.setProperty(pKey, ctx.getProperty(pKey));
			}
			
			Env.setContext(newCtx, "#AD_Client_ID", wstore.getAD_Client_ID());
			Env.setContext(newCtx, "#AD_Org_ID", wstore.getAD_Org_ID());
			//
			Env.setContext(newCtx, "#SalesRep_ID", wstore.getSalesRep_ID());
			Env.setContext(newCtx, "#M_PriceList_ID", wstore.getM_PriceList_ID());
			Env.setContext(newCtx, "#M_Warehouse_ID", wstore.getM_Warehouse_ID());
			//
			String s = wstore.getWebParam1();
			Env.setContext(newCtx, "webParam1", s == null ? "" : s);
			s = wstore.getWebParam2();
			Env.setContext(newCtx, "webParam2", s == null ? "" : s);
			s = wstore.getWebParam3();
			Env.setContext(newCtx, "webParam3", s == null ? "" : s);
			s = wstore.getWebParam4();
			Env.setContext(newCtx, "webParam4", s == null ? "" : s);
			s = wstore.getWebParam5();
			Env.setContext(newCtx, "webParam5", s == null ? "" : s);
			s = wstore.getWebParam6();
			Env.setContext(newCtx, "webParam6", s == null ? "" : s);
			s = wstore.getStylesheet();
			if (s == null)
				s = "standard";
			else
			{
				int index = s.lastIndexOf('.');
				if (index != -1)
					s = s.substring(0, index);
			}
			Env.setContext(newCtx, "Stylesheet", s);
			//
			s = wstore.getWebInfo();
			if (s != null && s.length() > 0)
				Env.setContext(newCtx, HDR_INFO, s);
			
			//	Payment Term
			Env.setContext(newCtx, "#M_PriceList_ID", wstore.getM_PriceList_ID());

			//	Default User - SalesRep
			if (Env.getContextAsInt(newCtx, "#AD_User_ID") == 0)
				Env.setContext(newCtx, "#AD_User_ID", wstore.getSalesRep_ID());
			
			//	Default Role for access
			if (Env.getContextAsInt(newCtx, "#AD_Role_ID") == 0)
			{
				int AD_Role_ID = 0;		//	HARDCODED - System
				Env.setContext(newCtx, "#AD_Role_ID", AD_Role_ID);
			}

			//	Client
			MClient client = MClient.get (newCtx, wstore.getAD_Client_ID());
			//	Name,Description, SMTPHost,RequestEMail,RequestUser, RequestUserPw
			Env.setContext(newCtx, "name", client.getName());
			Env.setContext(newCtx, "description", client.getDescription());
			
			//	AD_Language
			if (newCtx.getProperty("#AD_Language") == null && client.getAD_Language() != null)
				Env.setContext(newCtx, "#AD_Language", client.getAD_Language());
			//	DocumentDir
			String docDir = client.getDocumentDir();
			Env.setContext(newCtx, CTX_DOCUMENT_DIR, docDir == null ? "" : docDir);

			//	Default Language
			if (newCtx.getProperty("#AD_Language") == null)
				Env.setContext(newCtx, "#AD_Language", "en_US");

			//	Save Context - Key is AD_Client_ID
			s_cacheCtx.put(key, newCtx);
		}
		//	return new Properties (pp);	seems not to work with JSP
		Enumeration e = newCtx.keys();
		while (e.hasMoreElements())
		{
			String pKey = (String)e.nextElement();
			ctx.setProperty(pKey, newCtx.getProperty(pKey));
		}
		return ctx;
	}	//	getDefaults

	
	/**************************************************************************
	 *  Set Language from request or session in.
	 *  - Properties
	 *  - Cookie
	 *  - Session
	 *  @param request request
	 */
	private void setLanguage (HttpServletRequest request)
	{
		//  Get Cookie
		Properties cProp = WebUtil.getCookieProprties(request);
		
		//  Get/set Parameter:      Language
		String AD_Language = WebUtil.getParameter (request, Env.LANGUAGE);
		if (AD_Language == null)
		{
			//  Check Cookie
			AD_Language = cProp.getProperty(Env.LANGUAGE);
			if (AD_Language == null)
			{
				//  Check Request Locale
				Locale locale = request.getLocale();
				AD_Language = Language.getAD_Language (locale);
			}
		}
		if (AD_Language != null)
		{
			Language lang = Language.getLanguage(AD_Language);
			Env.verifyLanguage (ctx, lang);
			Env.setContext(ctx, Env.LANGUAGE, lang.getAD_Language());
			Msg.getMsg(ctx, "0");
			cProp.setProperty(Env.LANGUAGE, lang.getAD_Language());
			setLanguage(lang);
		}
		else if (language == null)	//	set base language
			setLanguage (Language.getBaseLanguage());
	}	//	setLanguage

	/**
	 * 	Set Language and init formats
	 *	@param lang language
	 */
	private void setLanguage (Language lang)
	{
		language = lang;
		//
		dateFormat = DisplayType.getDateFormat(DisplayType.Date, language);
		dateTimeFormat = DisplayType.getDateFormat(DisplayType.DateTime, language);
		//
		amountFormat = DisplayType.getNumberFormat(DisplayType.Amount, language);
		integerFormat = DisplayType.getNumberFormat(DisplayType.Integer, language);
		numberFormat = DisplayType.getNumberFormat(DisplayType.Number, language);
		quantityFormat = DisplayType.getNumberFormat(DisplayType.Quantity, language);
	}	//	setLanguage
	
	/**
	 * 	String representation
	 *	@return Session + count
	 */
	public String toString ()
	{
		return "WSessionCtx#" + counter;
	}	//	toString
	
}	//	WSessionCtx