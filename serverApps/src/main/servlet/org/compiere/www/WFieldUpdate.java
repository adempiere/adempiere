/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.ecs.xhtml.*;
import org.compiere.util.*;

/**
 *  Dynamic Field Updates.
 * 	
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: WFieldUpdate.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class WFieldUpdate extends HttpServlet
{
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (WFieldUpdate.class);
	
	/**
	 *  Initialize global variables
	 *  @param config
	 *  @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("WFieldUpdate.init");
	}   //  init

	/**
	 *  Clean up resources
	 */
	public void destroy()
	{
	}   //  destroy

	private static final String FORM_NAME   = "fieldUpdate";
	//
	private static final String FIELD_FORM  = "formName";
	private static final String FIELD_NAME  = "fieldName";
	private static final String FIELD_VALUE = "fieldValue";

	/**
	 *  Process the HTTP Get request
	 *  @param request
	 *  @param response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}   //  doPost


	/**
	 *  Process the HTTP Post request
	 *  @param request
	 *  @param response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		//  Get Session Info
		WebSessionCtx wsc = WebSessionCtx.get(request);
		WWindowStatus ws = WWindowStatus.get(request);

		if (wsc == null || ws == null)    //  ws can be null for Login
			;

		//  Get Parameter
		String formName = WebUtil.getParameter (request, FIELD_FORM);
		String fieldName = WebUtil.getParameter (request, FIELD_NAME);
		String fieldValue = WebUtil.getParameter (request, FIELD_VALUE);

		log.info("doPost - Form=" + formName + " - Field=" + fieldName + " - Value=" + fieldValue);

		//  Document
		WebDoc doc = createPage (wsc, ws, formName, fieldName, fieldValue);

		//  The Form
		form fu = new form(request.getRequestURI());
		fu.setName(FORM_NAME);
		fu.addElement(new input(input.TYPE_HIDDEN, FIELD_FORM, "y"));
		fu.addElement(new input(input.TYPE_HIDDEN, FIELD_NAME, "y"));
		fu.addElement(new input(input.TYPE_HIDDEN, FIELD_VALUE, "y"));
		doc.getBody().addElement(fu);

	//	log.trace(log.l1_User, "WFieldUpdate=" + doc.toString());

		//  Answer
		WebUtil.createResponse (request, response, this, null, doc, false);
	}   //  doPost

	/**
	 *	Create Reply.
	 *  <p>
	 *  Including special handling of login pages
	 *  @param wsc web session context
	 *  @param ws window status
	 *  @param formName
	 *  @param fieldName
	 *  @param fieldValue
	 */
	private static WebDoc createPage (WebSessionCtx wsc, WWindowStatus ws,
		String formName, String fieldName, String fieldValue)
	{
		WebDoc doc = WebDoc.create (true);	// plain
		body body = doc.getBody();
		
		//  Info
		StringBuffer sb = new StringBuffer("FieldUpdate - ") 
			.append(FIELD_FORM).append("=").append(formName).append(", ")
			.append(FIELD_NAME).append("=").append(fieldName).append(", ")
			.append(FIELD_VALUE).append("=").append(fieldValue);
		body.addElement(new p()
			.addElement(sb.toString()));

		//  Called manually - do nothing
		if (formName == null || fieldName == null)
			;
		//
		else if (formName.equals("Login2") && fieldName.equals(WLogin.P_ROLE))
			reply_Login2_Role (body, wsc, formName, fieldValue);
		//
		else if (formName.equals("Login2") && fieldName.equals(WLogin.P_CLIENT))
			reply_Login2_Client (body, wsc, formName, fieldValue);
		//
		else if (formName.equals("Login2") && fieldName.equals(WLogin.P_ORG))
			reply_Login2_Org (body, wsc, ws, formName, fieldValue);
		//
		return doc;
	}   //  getReply

	/**
	 *  Login 2nd page Response - Field Role.
	 *  <p>
	 *  fill Client, Org, Warehouse
	 *  @param body document body
	 *  @param wsc web session context
	 *  @param formName
	 *  @param fieldValue
	 */
	private static void reply_Login2_Role (body body, WebSessionCtx wsc, 
		String formName, String fieldValue)
	{
		//  Formname
		String form = "top." + WebEnv.TARGET_WINDOW + ".document.forms." + formName + ".";
		Login login = new Login(wsc.ctx);
		//  Get Data
		KeyNamePair[] clients = login.getClients ( 
			new KeyNamePair(Integer.parseInt(fieldValue) , fieldValue));

		//  Set Client ----
		StringBuffer script = new StringBuffer ();
		//  var A=top.WWindow.document.formName.selectName.options;
		script.append("var A=").append(form).append(WLogin.P_CLIENT).append(".options; ");
		//  A.length=0;                         //  resets options
		script.append("A.length=0; ");
		//  A[0]=new Option('text','value');    //  add new oprtion

		for (int i = 0; i < clients.length; i++)
		{
			KeyNamePair p = clients[i];
			script.append("A[").append(i).append("]=new Option('");
			script.append(p.getName());     //  text
			script.append("','");
			script.append(p.getKey());      //  value
			script.append("'); ");
		}
		script.append("\n");

		//  Set Organization ----

		if (clients.length > 0)
		{
			//  var A=top.WWindow.document.formName.selectName.options;
			script.append("var B=").append(form).append(WLogin.P_ORG).append(".options; ");
			//  A.length=0;                         //  resets options
			script.append("B.length=0; ");
			//  A[0]=new Option('text','value');    //  add new oprtion

			KeyNamePair[] orgs = login.getOrgs (clients[0]);
			for (int i = 0; i < orgs.length; i++)
			{
				KeyNamePair p = orgs[i];
				script.append("B[").append(i).append("]=new Option('");
				script.append(p.getName());     //  text
				script.append("','");
				script.append(p.getKey());      //  value
				script.append("'); ");
			}
			script.append("\n");

			//  Set Warehouse ----

			if (orgs.length > 0)
			{
				//  var A=top.WWindow.document.formName.selectName.options;
				script.append("var C=").append(form).append(WLogin.P_WAREHOUSE).append(".options; ");
				//  A.length=0;                         //  resets options
				script.append("C.length=0; ");
				//  A[0]=new Option('text','value');    //  add new oprtion

				KeyNamePair[] whs = login.getWarehouses (orgs[0]);
				if (whs != null)
				{	
					for (int i = 0; i < whs.length; i++)
					{
						KeyNamePair p = whs[i];
						script.append("C[").append(i).append("]=new Option('");
						script.append(p.getName());     //  text
						script.append("','");
						script.append(p.getKey());      //  value
						script.append("'); ");
					}
				}
			}	//	we have a org
		}	//	we have a client

		//  add script
		body.addElement(new p().addElement(WLogin.P_CLIENT + "="));
		body.addElement(new script(script.toString()));
	//	log.trace(log.l6_Database, "reply_Login2_Role - Script=" + script.toString());
	}   //  reply_Login2_Role

	/**
	 *  Login 2nd page Response - Field Client.
	 *  <p>
	 *  fill Org & Warehouse -
	 *  @param body body
	 *  @param wsc context
	 *  @param formName form name
	 *  @param fieldValue value
	 */
	private static void reply_Login2_Client (body body, WebSessionCtx wsc, 
		String formName, String fieldValue)
	{
		//  Formname
		String form = "top." + WebEnv.TARGET_WINDOW + ".document." + formName + ".";
		StringBuffer script = new StringBuffer ();

		//  Set Organization ----

		//  var A=top.WWindow.document.formName.selectName.options;
		script.append("var B=").append(form).append(WLogin.P_ORG).append(".options; ");
		//  A.length=0;                         //  resets options
		script.append("B.length=0; ");
		//  A[0]=new Option('text','value');    //  add new oprtion

		KeyNamePair client = new KeyNamePair(Integer.parseInt(fieldValue), fieldValue);
		Login login = new Login(wsc.ctx);
		KeyNamePair[] orgs = login.getOrgs (client);
		for (int i = 0; i < orgs.length; i++)
		{
			KeyNamePair p = orgs[i];
			script.append("B[").append(i).append("]=new Option('");
			script.append(p.getName());     //  text
			script.append("','");
			script.append(p.getKey());      //  value
			script.append("'); ");
		}
		script.append("\n");

		//  Set Warehouse ----

		//  var A=top.WWindow.document.formName.selectName.options;
		script.append("var C=").append(form).append(WLogin.P_WAREHOUSE).append(".options; ");
		//  A.length=0;                         //  resets options
		script.append("C.length=0; ");
		//  A[0]=new Option('text','value');    //  add new oprtion

		KeyNamePair[] whs = login.getWarehouses (orgs[0]);
		if (whs != null)
		{
			for (int i = 0; i < whs.length; i++)
			{
				KeyNamePair p = whs[i];
				script.append("C[").append(i).append("]=new Option('");
				script.append(p.getName());     //  text
				script.append("','");
				script.append(p.getKey());      //  value
				script.append("'); ");
			}
		}

		//  add script
		body.addElement(new p().addElement(WLogin.P_WAREHOUSE + "="));
		body.addElement(new script(script.toString()));
	//	log.trace(log.l6_Database, "Login2-Client - Script=" + script.toString());
	}   //  reply_Login2_Client

	/**
	 *  Login 2nd page Response - Field Org.
	 *  <p>
	 *  fill Warehouse
	 *  @param body document body
	 *  @param wsc web session context
	 *  @param formName
	 *  @param fieldValue
	 */
	private static void reply_Login2_Org (body body, WebSessionCtx wsc, WWindowStatus ws,
		String formName, String fieldValue)
	{
		//  Formname
		String form = "top." + WebEnv.TARGET_WINDOW + ".document." + formName + ".";
		StringBuffer script = new StringBuffer ();

		//  Set Warehouse ----

		//  var A=top.WWindow.document.formName.selectName.options;
		script.append("var C=").append(form).append(WLogin.P_WAREHOUSE).append(".options; ");
		//  A.length=0;                         //  resets options
		script.append("C.length=0; ");
		//  A[0]=new Option('text','value');    //  add new oprtion

		KeyNamePair org = new KeyNamePair(Integer.parseInt(fieldValue), fieldValue);
		Login login = new Login(wsc.ctx);
		String error = login.validateLogin(org);
		if (error != null && error.length() > 0)
		{
			log.severe(error); //todo graceful dead
			ws.mWindow = null;
			wsc.ctx = new Properties();
			return;
		}
		KeyNamePair[] whs = login.getWarehouses (org);
		if (whs != null)
		{
			for (int i = 0; i < whs.length; i++)
			{
				KeyNamePair p = whs[i];
				script.append("C[").append(i).append("]=new Option('");
				script.append(p.getName());     //  text
				script.append("','");
				script.append(p.getKey());      //  value
				script.append("'); ");
			}
		}

		//  add script
		body.addElement(new p().addElement(WLogin.P_WAREHOUSE + "="));
		body.addElement(new script(script.toString()));
	//	log.trace(log.l6_Database, "Login2-Org - Script=" + script.toString());
	}   //  reply_Login2_Org

}   //  WFieldUpdate
