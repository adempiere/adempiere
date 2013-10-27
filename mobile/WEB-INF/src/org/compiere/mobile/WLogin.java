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

import java.io.IOException;
import java.security.Principal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ecs.AlignType;
import org.apache.ecs.Element;
import org.apache.ecs.HtmlColor;
import org.apache.ecs.xhtml.a;
import org.apache.ecs.xhtml.b;
import org.apache.ecs.xhtml.div;
import org.apache.ecs.xhtml.fieldset;
import org.apache.ecs.xhtml.font;
import org.apache.ecs.xhtml.form;
import org.apache.ecs.xhtml.h1;
import org.apache.ecs.xhtml.img;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.label;
import org.apache.ecs.xhtml.link;
import org.apache.ecs.xhtml.meta;
import org.apache.ecs.xhtml.option;
import org.apache.ecs.xhtml.p;
import org.apache.ecs.xhtml.script;
import org.apache.ecs.xhtml.select;
import org.apache.ecs.xhtml.strong;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.tr;
import org.compiere.model.MSession;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Login;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;
import org.compiere.util.WebUtil;

/**
 *  Web Login Page.
 *  <p>
 *  Page request:
 *  <pre>
 *  - Check database connection
 *  - LoginInfo from request?
 *      - Yes: DoLogin success ?
 *          - Yes: return (second) preferences page
 *          - No: return (first) user/password page
 *      - No: User Principal ?
 *          - Yes: DoLogin success ?
 *              - Yes: return (second) preferences page
 *              - No: return (first) user/password page
 *          - No: return (first) user/password page
 *  </pre>
 *
 *  @author Jorg Janke
 *  @version  $Id: WLogin.java,v 1.1 2009/04/15 11:27:15 vinhpt Exp $
 */
public class WLogin extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -407753489095758837L;
	
	/**	Logger			*/
	protected CLogger	log = CLogger.getCLogger(getClass());
	
	/**
	 *	Initialize
	 *  @param config confif
	 *  @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		if (!MobileEnv.initWeb(config))
			throw new ServletException("WLogin.init");
	}	//	init

	/**
	 * Get Servlet information
	 * @return servlet info
	 */
	public String getServletInfo()
	{
		return "adempiere Web Login";
	}	//	getServletInfo

	/**
	 *	Clean up
	 */
	public void destroy()
	{
		log.info("destroy");
		super.destroy();
	}	//	destroy


	/**
	 *	Process the HTTP Get request - forward to Post
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		log.info("");
		doPost (request, response);
	}	//	doGet


	/**
	 *	Process the HTTP Post request.
	 *  <pre>
	 *  - Optionally create Session
	 *  - Check database connection
	 *  - LoginInfo from request?
	 *      - Yes: DoLogin success ?
	 *          - Yes: return (second) preferences page
	 *          - No: return (first) user/password page
	 *      - No: User Principal ?
	 *          - Yes: DoLogin success ?
	 *              - Yes: return (second) preferences page
	 *              - No: return (first) user/password page
	 *          - No: return (first) user/password page
	 *  </pre>
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		log.info("");
		//  Create New Session
		HttpSession sess = request.getSession(true);
		sess.setMaxInactiveInterval(MobileEnv.TIMEOUT);

		//  Get Cookie Properties
		Properties cProp = MobileUtil.getCookieProprties(request);

		//  Create Context
		MobileSessionCtx wsc = MobileSessionCtx.get (request);
		
		//  Page
		MobileDoc doc = null;

		//  Check DB connection
		if (!DB.isConnected())
		{
			String msg = Msg.getMsg(wsc.ctx, "WLoginNoDB");
			if (msg.equals("WLoginNoDB"))
				msg = "No Database Connection";
			doc = MobileDoc.createWindow (msg);
		}
		
		//  Get Parameters: Role, Client, Org, Warehouse, Date
		String role = MobileUtil.getParameter (request, WLogin.P_ROLE);
		String client = MobileUtil.getParameter (request, WLogin.P_CLIENT);
		String org = MobileUtil.getParameter (request, WLogin.P_ORG);
		String language = MobileUtil.getParameter(request, Env.LANGUAGE);
		if ( role != null && client != null && org != null )
		{
			//  Get Info from Context - User, Role, Client
			int AD_User_ID = Env.getAD_User_ID(wsc.ctx);
			int AD_Role_ID = Env.getAD_Role_ID(wsc.ctx);
			int AD_Client_ID = Env.getAD_Client_ID(wsc.ctx);
			//  Not available in context yet - Org, Warehouse
			int AD_Org_ID = -1;
			int M_Warehouse_ID = -1;

			//  Get latest info from context
			try
			{
				int req_role = Integer.parseInt(role);
				if (req_role != AD_Role_ID)
				{
					log.fine("AD_Role_ID - changed from " + AD_Role_ID);
					AD_Role_ID = req_role;
					Env.setContext(wsc.ctx, "#AD_Role_ID", AD_Role_ID);
				}
				log.fine("AD_Role_ID = " + AD_Role_ID);
				//
				int req_client = Integer.parseInt(client);
				if (req_client != AD_Client_ID)
				{
					log.fine("AD_Client_ID - changed from " + AD_Client_ID);
					AD_Client_ID = req_client;
					Env.setContext(wsc.ctx, "#AD_Client_ID", AD_Client_ID);
				}
				log.fine("AD_Client_ID = " + AD_Client_ID);
				//
				AD_Org_ID = Integer.parseInt(org);
				log.fine("AD_Org_ID = " + AD_Org_ID);
				//
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "Parameter", e);
				MobileUtil.createTimeoutPage(request, response, this, 
					Msg.getMsg(wsc.ctx, "ParameterMissing"));
				return;
			}

			//  Check Login info and set environment
			wsc.loginInfo = checkLogin (wsc.ctx, AD_User_ID, AD_Role_ID, 
				AD_Client_ID, AD_Org_ID, M_Warehouse_ID);
			if (wsc.loginInfo == null)
			{
				MobileUtil.createErrorPage (request, response, this,  
					Msg.getMsg(wsc.ctx, "RoleInconsistent"));
				return;
			}

			//  Set Date
			Timestamp ts = MobileUtil.getParameterAsDate (request, WLogin.P_DATE);
			if (ts == null)
				ts = new Timestamp(System.currentTimeMillis());
			Env.setContext(wsc.ctx, "#Date", ts);    //  JDBC format


			cProp.setProperty(P_ROLE, Integer.toString(AD_Role_ID));
			cProp.setProperty(P_ORG, Integer.toString(AD_Org_ID));
			/*
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WMenu");
			rd.forward(request, response);
			*/

			//
			//  Update Cookie - overwrite
			if (cProp != null)
			{
				Cookie cookie = new Cookie (MobileEnv.COOKIE_INFO, MobileUtil.propertiesEncode(cProp));
				cookie.setComment("(c) adempiere, Inc - Jorg Janke");
				cookie.setSecure(false);
				cookie.setPath("/");
				if (cProp.size() == 0)
					cookie.setMaxAge(0);            //  delete cookie
				else
					cookie.setMaxAge(2592000);      //  30 days in seconds   60*60*24*30
				response.addCookie(cookie);
			}
			
			response.sendRedirect(MobileEnv.getBaseDirectory("/WMenu"));
			
			return;

		}
		//  Login Info from request?
		else
		{
			//  Get Parameters:     UserName/Password
			String usr = MobileUtil.getParameter (request, P_USERNAME);
			String pwd = MobileUtil.getParameter (request, P_PASSWORD);
			//  Get Principle
			Principal userPrincipal = request.getUserPrincipal();
			log.info("Principal=" + userPrincipal + "; User=" + usr);

			//  Login info not from request and not pre-authorized
			if (userPrincipal == null && (usr == null || pwd == null))
				doc = createFirstPage (cProp, request, "");
			//  Login info from request or authorized
			else
			{
				KeyNamePair[] roles = null;
				Login login = new Login(wsc.ctx);
				//  Pre-authorized
				if (userPrincipal != null)
				{
					roles = login.getRoles(userPrincipal);
					usr = userPrincipal.getName();
				}
				else
					roles = login.getRoles(usr, pwd);
				//
				if (roles == null)
					doc = createFirstPage(cProp, request, Msg.getMsg(wsc.ctx, "UserPwdError"));
				else
				{
					
					String sql = "SELECT AD_Role_ID, Name FROM AD_Role WHERE IsMobileAccessible='Y'";
					
					ArrayList<KeyNamePair> validRoles= new ArrayList();
					try {

						ValueNamePair[] mobileRoles = DB.getValueNamePairs(sql, false, null);

						for ( KeyNamePair role1 : roles)
						{
							for (ValueNamePair mobileRole : mobileRoles )
							{
								if ( role1.getKey() == Integer.parseInt(mobileRole.getValue()))
								{
									validRoles.add(role1);
									break;
								}
							}
						}
						roles = new KeyNamePair[validRoles.size()];
						roles = validRoles.toArray(roles);
					}
					catch (Exception e) {
						// IsMobileAccessible not supported, allow any role
					}
					
					cProp.setProperty(P_USERNAME, usr);
					cProp.setProperty(Env.LANGUAGE, language);
				
					
					if (roles.length == 0)
						doc = createFirstPage(cProp, request, Msg.getMsg(wsc.ctx, "UserPwdError"));
					else 
					{
						String roleData=(cProp.getProperty(P_ROLE, null));
						doc = createSecondPage(cProp, request, MobileUtil.convertToOption(roles, roleData), "");
						//	Create adempiere Session - user id in ctx
						MSession.get (wsc.ctx, request.getRemoteAddr(), 
								request.getRemoteHost(), sess.getId() );
						MobileUtil.createResponseFragment (request, response, this, cProp, doc);
						return;
					}
				}
				
			}
		}
		MobileUtil.createResponse (request, response, this, cProp, doc, false);
	}	//	doPost

	//  Variable Names
	private static final String		P_USERNAME      = "User";
	private static final String		P_PASSWORD      = "Password";
	private static final String		P_SUBMIT        = "Submit";
	//  WMenu picks it up
	protected static final String   P_ROLE          = "AD_Role_ID";
	protected static final String   P_CLIENT        = "AD_Client_ID";
	protected static final String   P_ORG           = "AD_Org_ID";
	protected static final String   P_DATE          = "Date";
	protected static final String   P_WAREHOUSE     = "M_Warehouse_ID";
	protected static final String   P_ERRORMSG      = "ErrorMessage";
	protected static final String   P_STORE         = "SaveCookie";
	protected static final String	P_LANGUAGE			= "Language";

	/**
	 *  Check Login information and set context.
	 *  @return    true if login info are OK
	 *  @param ctx context
	 *  @param AD_User_ID user
	 *  @param AD_Role_ID role
	 *  @param AD_Client_ID client
	 *  @param AD_Org_ID org
	 *  @param M_Warehouse_ID warehouse
	 */
	private String checkLogin (Properties ctx, int AD_User_ID, int AD_Role_ID, int AD_Client_ID, int AD_Org_ID, int M_Warehouse_ID)
	{
		//  Get Login Info
		String loginInfo = null;
		//  Verify existance of User/Client/Org/Role and User's acces to Client & Org
		String sql = "SELECT u.Name || '@' || c.Name || '.' || o.Name || ' [' || INITCAP(USER) || ']' AS Text "
			+ "FROM AD_User u, AD_Client c, AD_Org o, AD_User_Roles ur "
			+ "WHERE u.AD_User_ID=?"    //  #1
			+ " AND c.AD_Client_ID=?"   //  #2
			+ " AND o.AD_Org_ID=?"      //  #3
			+ " AND ur.AD_Role_ID=?"    //  #4
			+ " AND ur.AD_User_ID=u.AD_User_ID"
			+ " AND (o.AD_Client_ID = 0 OR o.AD_Client_ID=c.AD_Client_ID)"
			+ " AND c.AD_Client_ID IN (SELECT AD_Client_ID FROM AD_Role_OrgAccess ca WHERE ca.AD_Role_ID=ur.AD_Role_ID)"
			+ " AND o.AD_Org_ID IN (SELECT AD_Org_ID FROM AD_Role_OrgAccess ca WHERE ca.AD_Role_ID=ur.AD_Role_ID)";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_User_ID);
			pstmt.setInt(2, AD_Client_ID);
			pstmt.setInt(3, AD_Org_ID);
			pstmt.setInt(4, AD_Role_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				loginInfo = rs.getString(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}

		//  not verified
		if (loginInfo == null)
			return null;

		//  Set Preferences
		KeyNamePair org = new KeyNamePair(AD_Org_ID, String.valueOf(AD_Org_ID));
		KeyNamePair wh = null;
		if (M_Warehouse_ID > 0)
			wh = new KeyNamePair(M_Warehouse_ID, String.valueOf(M_Warehouse_ID));
		//
		Timestamp date = null;
		String printer = null;
		Login login = new Login(ctx);
		login.loadPreferences(org, wh, date, printer);
		//	Don't Show Acct/Trl Tabs on HTML UI
		Env.setContext(ctx, "#ShowAcct", "N");
		Env.setContext(ctx, "#ShowTrl", "N");	
		//
		return loginInfo;
	}   //  checkLogin

	
	/**************************************************************************
	 *  First Login Page
	 *  @param cProp Login Cookie information for defaults
	 *  @param request request
	 *  @param errorMessage error message
	 *  @return WDoc page
	 */
	private MobileDoc createFirstPage(Properties cProp, HttpServletRequest request,
		String errorMessage)
	{
		log.info (" - " + errorMessage);
		String AD_Language = (cProp.getProperty(Env.LANGUAGE, Language.getAD_Language(request.getLocale())));
		//
		String windowTitle = Msg.getMsg(AD_Language, "Login");
		String usrText = Msg.getMsg(AD_Language, "User");
		String pwdText = Msg.getMsg(AD_Language, "Password");
		String lngText = Msg.translate(AD_Language, "AD_Language");

		//	Form - post to same URL
		String action = request.getRequestURI();
		form myForm = null;
		myForm = new form(action).setName("Login1");
		myForm.setID(windowTitle);
		myForm.setTitle(windowTitle);
		myForm.addAttribute("selected", "true");
		myForm.setClass("panel");
		myForm.setMethod("post");
		myForm.addAttribute("autocomplete", "off");
		
		fieldset fs = new fieldset();
		div div1 = new div();
		div1.setClass("row");
		
		//	Username
		String userData = cProp.getProperty(P_USERNAME, "");
		label usrLabel = new label().setFor(P_USERNAME + "F").addElement(usrText);
		usrLabel.setID(P_USERNAME + "L");
		div1.addElement(usrLabel);
		input usr = new input(input.TYPE_TEXT, P_USERNAME, userData).setSize(20).setMaxlength(30);
		usr.setID(P_USERNAME + "F");
		div1.addElement(usr);
		fs.addElement(div1);

		div1 = new div();
		div1.setClass("row");
		
		//  Password
		String pwdData = cProp.getProperty(P_PASSWORD, "");
		label pwdLabel = new label().setFor(P_PASSWORD + "F").addElement(pwdText);
		pwdLabel.setID(P_PASSWORD + "L");
		div1.addElement(pwdLabel);
		input pwd = new input(input.TYPE_PASSWORD, P_PASSWORD, pwdData).setSize(20).setMaxlength(30);
		pwd.setID(P_PASSWORD + "F");
		div1.addElement(pwd);
		fs.addElement(div1);
		
		div1 = new div();
		div1.setClass("row");

		//	Language Pick
		label langLabel = new label().setFor(Env.LANGUAGE + "F").addElement(lngText);
		langLabel.setID(Env.LANGUAGE + "L");
		div1.addElement(langLabel);
		option options[] = new option[Language.getLanguageCount()];
		for (int i = 0; i < Language.getLanguageCount(); i++)
		{
			Language language = Language.getLanguage(i);
			options[i] = new option(language.getAD_Language())
				.addElement(Util.maskHTML(language.getName()));
			if (language.getAD_Language().equals(AD_Language))
				options[i].setSelected(true);
			else
				options[i].setSelected(false);
		}
		div1.addElement(new select(Env.LANGUAGE, options)
			.setID(Env.LANGUAGE + "F"));
		fs.addElement(div1);

		div1 = new div();
		div1.setClass("row");
		/*  Store Cookie
		label cookieLabel = new label().setFor(P_STORE + "F").addElement(storeTxt);
		cookieLabel.setID(P_STORE + "L");
		div1.addElement(cookieLabel);
		String storeData = cProp.getProperty(P_STORE, "N");
		input store = new input(input.TYPE_CHECKBOX, P_STORE, "Y").setChecked(storeData.equals("Y"));
		store.setID(P_STORE + "F");
		div1.addElement(store);
		fs.addElement(div1);*/

		
		//  ErrorMessage
		if (errorMessage != null && errorMessage.length() > 0)
		{
			div1 = new div();
			div1.setClass("row");
			div1.addElement(new font(HtmlColor.red, 4).addElement(new b(errorMessage)));   //  color, size
			fs.addElement(div1);
		}

		myForm.addElement(fs);
		
		//<a class="whiteButton" type="submit" href="#">Login</a>
		//  Finish
		a button = new a("#", windowTitle);
		button.addAttribute("type", "submit");
		button.setClass("whiteButton");
		
		//
		myForm.addElement(button);
		
		//  Document
		MobileDoc doc = MobileDoc.createWindow(windowTitle);
		
		div div = new div();
		div.setClass("toolbar");
		h1 header = new h1();
		header.setID("pageTitle");
		div.addElement(header);
		a anchor = new a();
		anchor.setID("backButton");
		anchor.setClass("button");
		div.addElement(anchor);

		doc.getBody()
		.addElement(myForm)
		.addElement(div)
			.setTitle(windowTitle);


		doc.getHead().addElement(new script((Element)null, MobileEnv.getBaseDirectory("/js/login.js")));

		return doc;
	}   //  createFirstPage


	/**
	 *  Create Second Page
	 *  @param request request
	 *  @param roleOptions role options
	 *  @param errorMessage error message
	 *  @return WDoc page
	 */
	private MobileDoc createSecondPage(Properties cProp, HttpServletRequest request,
		option[] roleOptions, String errorMessage)
	{
		log.info(" - " + errorMessage);
		MobileSessionCtx wsc = MobileSessionCtx.get(request);
		String windowTitle = Msg.getMsg(wsc.language, "Login");

		//	Form - Get Menu
		String action = MobileEnv.getBaseDirectory("WLogin");
		form myForm = new form(action).setName("Login2");
		myForm.setID(windowTitle);
		myForm.setTitle(windowTitle);
		myForm.addAttribute("selected", "true");
		myForm.setClass("panel");
		myForm.setMethod("post");
		myForm.setTarget("_self");
		
		//	Role Pick
		fieldset fs = new fieldset();
		div div1 = new div();
		div1.setClass("row");
		//Modified by Rob Klein 4/29/07
		label roleLabel = new label().setFor(P_ROLE + "F").addElement(Msg.translate(wsc.language, "AD_Role_ID"));
		roleLabel.setID(P_ROLE + "L");
		div1.addElement(roleLabel);
		select role = new select(P_ROLE, roleOptions);
		role.setID(P_ROLE + "F");
		role.setOnChange("loginDynUpdate(this);");        		//  sets Client & Org
		div1.addElement(role);
		fs.addElement(div1);
		
		Login login = new Login(wsc.ctx);
		//  Get Data
		KeyNamePair[] clients = null;
		if (roleOptions.length > 0)
			clients = login.getClients ( 
			new KeyNamePair(Integer.parseInt(roleOptions[0].getAttribute("value")) , roleOptions[0].getAttribute("value")));

		//	Client Pick
		div1 = new div();
		div1.setClass("row");
		label clientLabel = new label().setFor(P_CLIENT + "F").addElement(Msg.translate(wsc.language, "AD_Client_ID"));
		clientLabel.setID(P_CLIENT + "L");
		div1.addElement(clientLabel);
		select client = new select(P_CLIENT, MobileUtil.convertToOption(clients, null));
		client.setID(P_CLIENT + "F");
		div1.addElement(new td().addElement(client));
		fs.addElement(div1);

		KeyNamePair[] orgs = null;
		if ( clients.length > 0 )
			orgs = login.getOrgs (clients[0]);
		
		//	Org Pick
		div1 = new div();
		div1.setClass("row");
		label orgLabel = new label().setFor(P_ORG + "F").addElement(Msg.translate(wsc.language, "AD_Org_ID"));
		orgLabel.setID(P_ORG + "L");
		div1.addElement(orgLabel);
		String orgData = cProp.getProperty(P_ORG, null);
		select org = new select(P_ORG, MobileUtil.convertToOption(orgs, orgData));
		org.setID(P_ORG + "F");
		div1.addElement(org);
		fs.addElement(div1);

		//  ErrorMessage
		if (errorMessage != null && errorMessage.length() > 0)
		{
			div1 = new div();
			div1.setClass("row");
			div1.addElement(new strong(errorMessage));
			fs.addElement(div1);
		}
		
		myForm.addElement(fs);
		
		//  Finish
		a button = new a("#", "OK");
		button.addAttribute("type", "submit");
		button.setClass("whiteButton");
		
		myForm.addElement(button);
		
	//  Document
		MobileDoc doc = MobileDoc.createWindow(windowTitle);
		
		doc.getBody()
		.addElement(myForm)
			.setTitle("Login");

		return doc;
	}   //  createSecondPage

}	//	WLogin
