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
package org.compiere.www;

import java.io.IOException;
import java.security.Principal;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ecs.AlignType;
import org.apache.ecs.HtmlColor;
import org.apache.ecs.xhtml.b;
import org.apache.ecs.xhtml.font;
import org.apache.ecs.xhtml.form;
import org.apache.ecs.xhtml.img;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.label;
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
import org.compiere.util.WebDoc;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
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
		if (!WebEnv.initWeb(config))
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
		sess.setMaxInactiveInterval(WebEnv.TIMEOUT);

		//  Get Cookie Properties
		Properties cProp = WebUtil.getCookieProprties(request);

		//  Create Context
		WebSessionCtx wsc = WebSessionCtx.get (request);
		
		//  Page
		WebDoc doc = null;

		//  Check DB connection
		if (!DB.isConnected())
		{
			String msg = Msg.getMsg(wsc.ctx, "WLoginNoDB");
			if (msg.equals("WLoginNoDB"))
				msg = "No Database Connection";
			doc = WebDoc.createWindow (msg);
		}

		//  Login Info from request?
		else
		{
			//  Get Parameters:     UserName/Password
			String usr = WebUtil.getParameter (request, P_USERNAME);
			String pwd = WebUtil.getParameter (request, P_PASSWORD);
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
					doc = createSecondPage(request, WebUtil.convertToOption(roles, null), "");
					//	Create adempiere Session - user id in ctx
					MSession.get (wsc.ctx, request.getRemoteAddr(), 
						request.getRemoteHost(), sess.getId() );
				}
				//  Can we save Cookie ?
				if (WebUtil.getParameter (request, P_STORE) == null)
				{
					cProp.clear();                          //  erase all
				}
				else    //  Save Cookie Parameter
				{
					cProp.setProperty(P_USERNAME, usr);
					cProp.setProperty(P_STORE, "Y");
					cProp.setProperty(P_PASSWORD, pwd);     //  For test only
				}
			}
		}
		WebUtil.createResponse (request, response, this, cProp, doc, false);
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

	
	/**************************************************************************
	 *  First Login Page
	 *  @param cProp Login Cookie information for defaults
	 *  @param request request
	 *  @param errorMessage error message
	 *  @return WDoc page
	 */
	private WebDoc createFirstPage(Properties cProp, HttpServletRequest request,
		String errorMessage)
	{
		log.info (" - " + errorMessage);
		String AD_Language = (cProp.getProperty(Env.LANGUAGE, Language.getAD_Language(request.getLocale())));
		//
		String windowTitle = Msg.getMsg(AD_Language, "Login");
		String usrText = Msg.getMsg(AD_Language, "User");
		String pwdText = Msg.getMsg(AD_Language, "Password");
		String lngText = Msg.translate(AD_Language, "AD_Language");
		String okText = Msg.getMsg(AD_Language, "OK");
		String cancelText = Msg.getMsg(AD_Language, "Cancel");
		String storeTxt = Msg.getMsg(AD_Language, "SaveCookie");

		//	Form - post to same URL
		String action = request.getRequestURI();
		form myForm = null;
		myForm = new form(action).setName("Login1");
		table table = new table().setAlign(AlignType.CENTER).setWidth("25%");				
		//Modified by Rob Klein 4/29/07
		//	Blank Line
		tr line = new tr();
		line.addElement(new td().addElement(" "));
		
		//	Username
		String userData = cProp.getProperty(P_USERNAME, "");
		line = new tr();
		label usrLabel = new label().setFor(P_USERNAME + "F").addElement(usrText);
		usrLabel.setID(P_USERNAME + "L");
		line.addElement(new td().addElement(usrLabel).setAlign(AlignType.RIGHT));
		input usr = new input(input.TYPE_TEXT, P_USERNAME, userData).setSize(20).setMaxlength(30);
		usr.setID(P_USERNAME + "F");
		line.addElement(new td().addElement(usr).setAlign(AlignType.LEFT));
		table.addElement(line);

		//  Password
		String pwdData = cProp.getProperty(P_PASSWORD, "");
		line = new tr();
		label pwdLabel = new label().setFor(P_PASSWORD + "F").addElement(pwdText);
		pwdLabel.setID(P_PASSWORD + "L");
		line.addElement(new td().addElement(pwdLabel).setAlign(AlignType.RIGHT));
		input pwd = new input(input.TYPE_PASSWORD, P_PASSWORD, pwdData).setSize(20).setMaxlength(30);
		pwd.setID(P_PASSWORD + "F");
		line.addElement(new td().addElement(pwd).setAlign(AlignType.LEFT));
		table.addElement(line);

		//	Language Pick
		String langData = cProp.getProperty(AD_Language);
		line = new tr();
		label langLabel = new label().setFor(Env.LANGUAGE + "F").addElement(lngText);
		langLabel.setID(Env.LANGUAGE + "L");
		line.addElement(new td().addElement(langLabel).setAlign(AlignType.RIGHT));
		option options[] = new option[Language.getLanguageCount()];
		for (int i = 0; i < Language.getLanguageCount(); i++)
		{
			Language language = Language.getLanguage(i);
			options[i] = new option(language.getAD_Language())
				.addElement(Util.maskHTML(language.getName()));
			if (language.getAD_Language().equals(langData))
				options[i].setSelected(true);
			else
				options[i].setSelected(false);
		}
		line.addElement(new td().addElement(new select(Env.LANGUAGE, options)
			.setID(Env.LANGUAGE + "F")));
		table.addElement(line);

		//  Store Cookie
		String storeData = cProp.getProperty(P_STORE, "N");
		line = new tr();
		line.addElement(new td());
		input store = new input(input.TYPE_CHECKBOX, P_STORE, "Y").addElement(storeTxt).setChecked(storeData.equals("Y"));
		store.setID(P_STORE + "F");
		line.addElement(new td().addElement(store).setAlign(AlignType.LEFT));
		table.addElement(line);

		//  ErrorMessage
		if (errorMessage != null && errorMessage.length() > 0)
		{
			line = new tr();
		//	line.addElement(new td());
			line.addElement(new td().setColSpan(2)
				.addElement(new font(HtmlColor.red, 4).addElement(new b(errorMessage))));   //  color, size
			table.addElement(line);
		}

		//  Finish
		line = new tr();
		//Modified by Rob Klein 4/29/07
		table tablebutton = new table().setAlign(AlignType.CENTER).setWidth("25%");
		input cancel = new input(input.TYPE_RESET, "Reset", "  "+"Cancel");
		cancel.setClass("cancelbtn");
		line.addElement(new td().addElement(cancel).setWidth("50%")).setAlign(AlignType.CENTER);
		input submit = new input(input.TYPE_SUBMIT, P_SUBMIT, "  "+"OK");
		submit.setClass("loginbtn");
		line.addElement(new td().addElement(submit).setWidth("50%").setAlign(AlignType.CENTER));		
		tablebutton.addElement(line);
		table.addElement(tablebutton);
		//
		myForm.addElement(table);
		
		//  Document
		WebDoc doc = WebDoc.createWindow (windowTitle);
		//Modified by Rob Klein 4/29/07
		img img = new img (WebEnv.getImageDirectory("Logo.gif"), "logo");
		doc.addWindowCenter(true)			
			.addElement(img)
			.addElement(new p())
			.addElement(new p())
			//.addElement(new h3("The HTML UI is Beta Functionality!"))
			//.addElement(myForm)
			.addElement(myForm)
			.addElement(new p())
			.addElement(new p());
		//  Clear Menu Frame
		doc.getBody()
			.addElement(WebUtil.getClearFrame(WebEnv.TARGET_MENU))
			.setTitle(windowTitle);

		return doc;
	}   //  createFirstPage


	/**
	 *  Create Second Page
	 *  @param request request
	 *  @param roleOptions role options
	 *  @param errorMessage error message
	 *  @return WDoc page
	 */
	private WebDoc createSecondPage(HttpServletRequest request,
		option[] roleOptions, String errorMessage)
	{
		log.info(" - " + errorMessage);
		WebSessionCtx wsc = WebSessionCtx.get(request);
		String windowTitle = Msg.getMsg(wsc.language, "LoginSuccess");

		//	Form - Get Menu
		String action = WebEnv.getBaseDirectory("WMenu");
		form myForm = null;
		myForm = new form(action).setName("Login2");
		myForm.setTarget(WebEnv.TARGET_MENU);
		table table = new table().setAlign(AlignType.CENTER);

		//	Role Pick
		tr line = new tr();
		//Modified by Rob Klein 4/29/07
		line.addElement(new td().addElement(" "));
		table.addElement(line);
		line = new tr();
		label roleLabel = new label().setFor(P_ROLE + "F").addElement(Msg.translate(wsc.language, "AD_Role_ID"));
		roleLabel.setID(P_ROLE + "L");
		line.addElement(new td().addElement(roleLabel).setAlign(AlignType.RIGHT));
		select role = new select(P_ROLE, roleOptions);
		role.setID(P_ROLE + "F");
		role.setOnClick("fieldUpdate(this);");        		//  WFieldUpdate sets Client & Org
		line.addElement(new td().addElement(role));
		table.addElement(line);

		//	Client Pick
		line = new tr();
		label clientLabel = new label().setFor(P_CLIENT + "F").addElement(Msg.translate(wsc.language, "AD_Client_ID"));
		clientLabel.setID(P_CLIENT + "L");
		line.addElement(new td().addElement(clientLabel).setAlign(AlignType.RIGHT));
		select client = new select(P_CLIENT);
		client.setID(P_CLIENT + "F");
		client.setOnClick("fieldUpdate(this);");          	//  WFieldUpdate sets Org
		line.addElement(new td().addElement(client));
		table.addElement(line);

		//	Org Pick
		line = new tr();
		label orgLabel = new label().setFor(P_ORG + "F").addElement(Msg.translate(wsc.language, "AD_Org_ID"));
		orgLabel.setID(P_ORG + "L");
		line.addElement(new td().addElement(orgLabel).setAlign(AlignType.RIGHT));
		select org = new select(P_ORG);
		org.setID(P_ORG + "F");
		org.setOnClick("fieldUpdate(this);");          	//  WFieldUpdate sets Org
		line.addElement(new td().addElement(org));
		table.addElement(line);

		//  Warehouse
		line = new tr();
		label whLabel = new label().setFor(P_WAREHOUSE + "F").addElement(Msg.translate(wsc.language, "M_Warehouse_ID"));
		whLabel.setID(P_WAREHOUSE + "L");
		line.addElement(new td().addElement(whLabel).setAlign(AlignType.RIGHT));
		select wh = new select(P_WAREHOUSE);
		wh.setID(P_WAREHOUSE + "F");
		line.addElement(new td().addElement(wh ));
		table.addElement(line);

		//  Date
		String dateData = wsc.dateFormat.format(new java.util.Date());
		line = new tr();
		label dateLabel = new label().setFor(P_DATE + "F").addElement(Msg.getMsg(wsc.language, "Date"));
		dateLabel.setID(P_DATE + "L");
		line.addElement(new td().addElement(dateLabel).setAlign(AlignType.RIGHT));
		input date = new input(input.TYPE_TEXT, P_DATE, dateData).setSize(10).setMaxlength(10);
		date.setID(P_DATE + "F");
		line.addElement(new td().addElement(date).setAlign(AlignType.LEFT));
		table.addElement(line);

		//  ErrorMessage
		if (errorMessage != null && errorMessage.length() > 0)
		{
			line = new tr();
			line.addElement(new td().addElement(new strong(errorMessage)).setColSpan(2).setAlign(AlignType.CENTER));
			table.addElement(line);
		}
		//Modified by Rob Klein 4/29/07
		//  Finish		
		table tablebutton = new table().setAlign(AlignType.CENTER).setWidth("25%");
		line = new tr();
		//Modified by Rob Klein 4/29/07
		//input cancel = new input(input.TYPE_RESET, "Reset", Msg.getMsg(wsc.language, "Cancel"));
		input cancel = new input(input.TYPE_RESET, "Reset", "  "+ "Cancel");
		cancel.setOnClick("window.top.location.replace('/adempiere/index.html');");
		cancel.setClass("cancelbtn");
		line.addElement(new td().addElement(cancel).setWidth("50%").setAlign(AlignType.CENTER));
		input submit = new input(input.TYPE_SUBMIT, "Submit", "  "+ "OK");
		submit.setOnClick("showLoadingMenu('" + WebEnv.getBaseDirectory("") + "');");
		//Modified by Rob Klein 4/29/07
		submit.setClass("loginbtn");
		line.addElement(new td().addElement(submit).setWidth("50%").setAlign(AlignType.CENTER));
		tablebutton.addElement(line);
		table.addElement(tablebutton);
		//
		myForm.addElement(table);
		
		//  Create Document
		WebDoc doc = WebDoc.createWindow (windowTitle);
		//Modified by Rob Klein 4/29/07
		img img = new img (WebEnv.getImageDirectory("Logo.gif"), "logo");
		doc.addWindowCenter(true)
			.addElement(img)
			.addElement(new p())
			//.addElement(new h3("The HTML UI is Beta Functionality!"))
			.addElement(myForm)
			.addElement(new p())
			.addElement(new p());
		//
		String script = "fieldUpdate(document.Login2." + P_ROLE + ");";	//  init dependency updates
		doc.getBody()
			.addElement(new script(script));
		//  Note
		doc.addWindowFooter()
			.addElement(new p(Msg.getMsg(wsc.language, "WLoginBrowserNote"), AlignType.CENTER));

		return doc;
	}   //  createSecondPage

}	//	WLogin
