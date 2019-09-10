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
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ecs.xhtml.body;
import org.apache.ecs.xhtml.p;
import org.apache.ecs.xhtml.script;
import org.compiere.util.CLogger;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Login;

/**
 *  Dynamic Field Updates.
 * 	
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: WFieldUpdate.java,v 1.1 2009/04/15 11:27:15 vinhpt Exp $
 */
public class LoginDynUpdate extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1576213475379404148L;
	
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (LoginDynUpdate.class);
	
	/**
	 *  Initialize global variables
	 *  @param config
	 *  @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		if (!MobileEnv.initWeb(config))
			throw new ServletException("WFieldUpdate.init");
	}   //  init

	/**
	 *  Clean up resources
	 */
	public void destroy()
	{
	}   //  destroy

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
		MobileSessionCtx wsc = MobileSessionCtx.get(request);
		WWindowStatus ws = WWindowStatus.get(request);

		if (wsc == null || ws == null)    //  ws can be null for Login
			;

		String role = request.getParameter("AD_Role_ID");
		int roleId;
		try {
			roleId = Integer.parseInt(role); 
		}
		catch (Exception e) {
			roleId = -1;
		}
		
		String client = request.getParameter("AD_Client_ID");
		int clientId;
		try {
			clientId = Integer.parseInt(client); 
		}
		catch (Exception e) {
			clientId = -1;
		}
		
		Login login = new Login(wsc.ctx);
		StringBuffer script = new StringBuffer ("{");
		
		boolean hasClients = false;
		
		
		if ( roleId >= 0 )
		{

			//  Get Data
			KeyNamePair[] clients = login.getClients ( 
					new KeyNamePair(roleId , role));

			//  Set Client ----
			script.append("\"clients\":[");
			for (int i = 0; i < clients.length; i++)
			{
				if ( i > 0 )
					script.append(",");
				KeyNamePair p = clients[i];
				script.append("{\"text\":\"");
				script.append(p.getName());     //  text
				script.append("\",\"value\":\"");
				script.append(p.getKey());      //  value
				script.append("\"}");
			}
			script.append("]");
			
			if ( clientId < 0 && clients.length > 0 )
			{
				clientId = clients[0].getKey();
			}
		}
		if (clientId >= 0 )
		{
			script.append(",\"orgs\":[");

			KeyNamePair[] orgs = login.getOrgs (new KeyNamePair(clientId, client));
			for (int i = 0; i < orgs.length; i++)
			{
				if ( i > 0 )
					script.append(",");
				KeyNamePair p = orgs[i];
				script.append("{\"text\":\"");
				script.append(p.getName());     //  text
				script.append("\",\"value\":\"");
				script.append(p.getKey());      //  value
				script.append("\"}");
			}
			script.append("]");
		}
		script.append("}");
	//  print document
		PrintWriter out = response.getWriter();     //  with character encoding support
		out.print(script);
		out.flush();
		if (out.checkError())
			log.log(Level.SEVERE, "error writing");
		//  binary output (is faster but does not do character set conversion)
	//	OutputStream out = response.getOutputStream();
	//	byte[] data = doc.toString().getBytes();
	//	response.setContentLength(data.length);
	//	out.write(doc.toString().getBytes());
		//
		out.close();
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
	private static MobileDoc createPage (MobileSessionCtx wsc, WWindowStatus ws, String roleId)
	{
		MobileDoc doc = MobileDoc.create (true);	// plain
		body body = doc.getBody();
		//  Info
		StringBuffer sb = new StringBuffer("FieldUpdate - ") ;
		body.addElement(new p()
			.addElement(sb.toString()));

		
		reply_Login2_Role (body, wsc, roleId);
		/*
		//
		else if (formName.equals("Login2") && fieldName.equals(WLogin.P_CLIENT))
			reply_Login2_Client (body, wsc, formName, fieldValue, locationValue);
		//
		else if (formName.equals("Login2") && fieldName.equals(WLogin.P_ORG))
			reply_Login2_Org (body, wsc, ws, formName, fieldValue, locationValue);
		//*/
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
	private static void reply_Login2_Role (body body, MobileSessionCtx wsc, String roleId)
	{
		//  Formname
		String form = null;
		Login login = new Login(wsc.ctx);
		//  Get Data
		KeyNamePair[] clients = login.getClients ( 
			new KeyNamePair(Integer.parseInt(roleId) , roleId));

		//  Set Client ----
		StringBuffer script = new StringBuffer ("{\"options\":[");
		for (int i = 0; i < clients.length; i++)
		{
			if ( i > 0 )
				script.append(",");
			KeyNamePair p = clients[i];
			script.append("{\"text\":\"");
			script.append(p.getName());     //  text
			script.append("\",\"value\":\"");
			script.append(p.getKey());      //  value
			script.append("\"}); ");
		}
		script.append("]}");

		/*
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
	 
		 */
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
	private static void reply_Login2_Client (body body, MobileSessionCtx wsc, 
		String formName, String fieldValue, String locationValue)
	{
		log.info("Location-Client: "+locationValue);
		//  Formname
		String form = null;
		//if (locationValue!=null)
			form = locationValue + MobileEnv.TARGET_WINDOW + ".document." + formName + ".";
		//else
			//form = "top." + WebEnv.TARGET_WINDOW + ".document." + formName + ".";		
		StringBuffer script = new StringBuffer ();
		//log.info("form_client->"+form);
		
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
	private static void reply_Login2_Org (body body, MobileSessionCtx wsc, WWindowStatus ws,
		String formName, String fieldValue, String locationValue)
	{
		//  Formname
		String form = null;
		log.info("Location-Org: "+locationValue);
		//if (locationValue!=null)
			form = locationValue + MobileEnv.TARGET_WINDOW + ".document." + formName + ".";
		//else
			//form = "top." + WebEnv.TARGET_WINDOW + ".document." + formName + ".";
		
		//log.info("form_org->"+form);
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
