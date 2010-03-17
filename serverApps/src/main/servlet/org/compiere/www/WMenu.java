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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ecs.Element;
import org.apache.ecs.xhtml.a;
import org.apache.ecs.xhtml.base;
import org.apache.ecs.xhtml.body;
import org.apache.ecs.xhtml.head;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.link;
import org.apache.ecs.xhtml.script;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.tr;
import org.compiere.model.MForm;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Login;
import org.compiere.util.Msg;
import org.compiere.util.WebDoc;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUtil;

/**
 * Web Menu
 * 
 * @author Jorg Janke
 * @version $Id: WMenu.java,v 1.1 2009/04/15 11:27:15 vinhpt Exp $
 */
public class WMenu extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6357775290362135602L;
	
	/** Logger */
	protected CLogger	log	= CLogger.getCLogger (getClass ());

	/**
	 * Initialize global variables
	 * 
	 * @param config
	 *            config
	 * @throws ServletException
	 */
	public void init (ServletConfig config)
		throws ServletException
	{
		super.init (config);
		if (!WebEnv.initWeb (config))
			throw new ServletException ("WMenu.init");
	} // init

	/**
	 * Get Servlet information
	 * 
	 * @return servlet info
	 */
	public String getServletInfo ()
	{
		return "adempiere Web Menu";
	} // getServletInfo

	/**
	 * Clean up resources
	 */
	public void destroy ()
	{
		log.fine("destroy");
		super.destroy ();
	} // destroy

	/** */
	private PreparedStatement	m_pstmt;

	/***************************************************************************
	 * Process the HTTP Get request. - Exit (Logout) - AD_Window_ID Forward to
	 * Window
	 * 
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.fine("Process Menu Request");
		//  Get Parameter: Exit
		if (WebUtil.getParameter (request, "Exit") != null)
		{
			WebUtil.createLoginPage (request, response, this, null, "Exit");
			return;
		}

		//  Get Session attributes
	  	WebSessionCtx wsc = WebSessionCtx.get(request);
		if (wsc.ctx == null)
		{
			WebUtil.createTimeoutPage(request, response, this, null);
			return;
		}

		//  Window
		int AD_Window_ID = WebUtil.getParameterAsInt(request, "AD_Window_ID");

		//  Forward to WWindow
		if (AD_Window_ID != 0)
		{
			log.fine("AD_Window_ID=" + AD_Window_ID);
			//
			String url = WebEnv.getBaseDirectory("WWindow?AD_Window_ID=" + AD_Window_ID);
			log.fine("Forward to=" + url);
			//
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
			return;
		}

		//  Request not serviceable
		WebUtil.createErrorPage(request, response, this, "NotImplemented");
	}   //  doGet


	/**************************************************************************
	 *  Process the HTTP Post request - Verify Input & Create Menu
	 *
	 * @param request request
	 * @param response response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.fine("doPost - Create Menu");
		
		//  Get Session attributes
	  	WebSessionCtx wsc = WebSessionCtx.get(request);
		if (wsc == null)
		{
			WebUtil.createTimeoutPage(request, response, this, null);
			return;
		}
		//Added by Rob Klein 4/29/07
	
		//Reset WWindow
		WebUtil.getClearFrame(WebEnv.TARGET_WINDOW);

		//  Get Parameters: Role, Client, Org, Warehouse, Date
		String role = WebUtil.getParameter (request, WLogin.P_ROLE);
		String client = WebUtil.getParameter (request, WLogin.P_CLIENT);
		String org = WebUtil.getParameter (request, WLogin.P_ORG);
		String wh = WebUtil.getParameter (request, WLogin.P_WAREHOUSE);
		if (wh == null)
			wh = "";

		//  Get context
		if (role == null || client == null || org == null)
		{
			WebUtil.createTimeoutPage(request, response, this, 
				Msg.getMsg(wsc.ctx, "ParameterMissing"));
			return;
		}

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
			if (wh.length() > 0)
			{
				M_Warehouse_ID = Integer.parseInt(wh);
				log.fine("M_Warehouse_ID = " + M_Warehouse_ID);
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Parameter", e);
			WebUtil.createTimeoutPage(request, response, this, 
				Msg.getMsg(wsc.ctx, "ParameterMissing"));
			return;
		}

		//  Check Login info and set environment
		wsc.loginInfo = checkLogin (wsc.ctx, AD_User_ID, AD_Role_ID, 
			AD_Client_ID, AD_Org_ID, M_Warehouse_ID);
		if (wsc.loginInfo == null)
		{
			WebUtil.createErrorPage (request, response, this,  
				Msg.getMsg(wsc.ctx, "RoleInconsistent"));
			return;
		}

		//  Set cookie for future defaults
		Properties cProp = WebUtil.getCookieProprties (request);
		cProp.setProperty(WLogin.P_ROLE, String.valueOf(AD_Role_ID));
		cProp.setProperty(WLogin.P_CLIENT, String.valueOf(AD_Client_ID));
		cProp.setProperty(WLogin.P_ORG, String.valueOf(AD_Org_ID));
		if (M_Warehouse_ID == -1)
			cProp.setProperty(WLogin.P_WAREHOUSE, "");
		else
			cProp.setProperty(WLogin.P_WAREHOUSE, String.valueOf(M_Warehouse_ID));

		//  Set Date
		Timestamp ts = WebUtil.getParameterAsDate (request, WLogin.P_DATE);
		if (ts == null)
			ts = new Timestamp(System.currentTimeMillis());
		Env.setContext(wsc.ctx, "#Date", ts);    //  JDBC format


	//	log.printProperties(System.getProperties(), "System");
	//	log.printProperties(cProp, "Cookie");
	//	log.printProperties(ctx, "Servlet Context");
	//	log.printProperties(Env.getCtx(), "Apps Env Context");


		//  Can we store Cookie ?
		if (!cProp.getProperty(WLogin.P_STORE, "N").equals("Y"))
			cProp.clear();

		WebDoc doc = createPage (request, wsc, AD_Role_ID, AD_User_ID, AD_Client_ID, AD_Org_ID);
		//Added by Rob Klein 4/29/07
		doc.getBody()
		.addElement(WebUtil.getClearFrame(WebEnv.TARGET_WINDOW));
		WebUtil.createResponse (request, response, this, cProp, doc, false);
		//WebUtil.createResponse (request, response, this, cProp, doc, true);
	}   //  doPost

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
		//Modified by Rob Klein to Show Accounting Tabs 04/29/2007
		//	Don't Show Acct/Trl Tabs on HTML UI
		//Env.setContext(ctx, "#ShowAcct", "N");
		//Env.setContext(ctx, "#ShowTrl", "N");		
		//Env.setContext(ctx, "#ShowAcct", "Y");
		//Env.setContext(ctx, "#ShowTrl", "Y");
		//
		return loginInfo;
	}   //  checkLogin

	/**
	 * 	Create Menu Page
	 *	@param request request 
	 *	@param wsc context
	 *	@param AD_Role_ID role
	 *	@return document
	 */
	private WebDoc createPage (HttpServletRequest request, 
		WebSessionCtx wsc, int AD_Role_ID, int AD_User_ID, int AD_Client_ID, int AD_Org_ID)
	{
		//	Document
		//Modified by Rob Klein 4/29/07
		//String windowTitle = Msg.getMsg(wsc.ctx, "Menu");
		String windowTitle = "Menu";
		WebDoc doc = WebDoc.create (windowTitle);
		head head = doc.getHead();
		//  Target
		head.addElement(new base().setTarget(WebEnv.TARGET_WINDOW));
		//  Specific Menu Script/Stylesheet
		head.addElement(new link(WebEnv.getBaseDirectory("/css/menu.css"), link.REL_STYLESHEET, link.TYPE_CSS));
		head.addElement(new script((Element)null, WebEnv.getBaseDirectory("/js/menu.js")));
		head.addElement(new script((Element)null, WebEnv.getBaseDirectory("/js/window.js")));
		//Modified by Rob Klein 4/29/07
		//head.addElement(new script((Element)null, WebEnv.getBaseDirectory("/js/mktree.js")));
		//head.addElement(new link(WebEnv.getBaseDirectory("/css/mktree.css"), link.REL_STYLESHEET, link.TYPE_CSS));
		//	Scripts
		String statusMessage = Msg.getMsg(wsc.ctx, "SelectMenuItem");
		String scriptTxt = "top.document.title='" + windowTitle + " - " + wsc.loginInfo + "'; "
			+ "var defaultStatus='" + statusMessage + "';";
		//Rob 12-16-2006 head.addElement(new script(scriptTxt));
		
		//	Body

		body body = doc.getBody();
		body.setTitle(statusMessage);
		//  Clear Window Frame
		//Rob 12-16-2006 body.addElement(WebUtil.getClearFrame(WebEnv.TARGET_WINDOW));

		//  Header
		table table = doc.getTable();
		
		doc.setClasses ("menuTable", "menuHeader");
		//Rob 12-16-2006 doc.getTopLeft().addElement(new cite(wsc.loginInfo));
		input txtSearch = new input(input.TYPE_TEXT, "txtSearch", "");
		txtSearch.setOnKeyDown("searchMenu('main',this.value,event,'" + WebEnv.TARGET_WINDOW + "')");
		doc.getTopLeft().addElement("Find: ");
		doc.getTopLeft().addElement(txtSearch);

		//  Load Menu Structure     ----------------------
		int AD_Tree_ID = DB.getSQLValue(null,
			"SELECT COALESCE(r.AD_Tree_Menu_ID, ci.AD_Tree_Menu_ID)" 
			+ "FROM AD_ClientInfo ci" 
			+ " INNER JOIN AD_Role r ON (ci.AD_Client_ID=r.AD_Client_ID) "
			+ "WHERE AD_Role_ID=?", AD_Role_ID);
		if (AD_Tree_ID <= 0)
			AD_Tree_ID = 10;	//	Menu
		log.fine("doPost - AD_Tree_ID=" + AD_Tree_ID + " - " + Env.getAD_Language(wsc.ctx));
		MTree tree = new MTree (wsc.ctx, AD_Tree_ID, false, false, null);	// Language set in WLogin
		//	Trim tree
		MTreeNode root = tree.getRoot();
		Enumeration en = root.preorderEnumeration();
		/*while (en.hasMoreElements())
		{
			MTreeNode nd = (MTreeNode)en.nextElement();
			if (nd.isTask() 
				|| nd.isWorkbench() 
				|| nd.isWorkFlow()
				|| nd.getNode_ID() == 383	//	Reset Cache - kills the server
			)
			{
				MTreeNode parent = (MTreeNode)nd.getParent();
				parent.remove(nd);
			}
		}*/
		tree.trimTree();
		
		//	Print tree
		StringBuffer buf = new StringBuffer();
		StringBuffer buffav = new StringBuffer();
		en = root.preorderEnumeration();
		int oldLevel = 0;
		while (en.hasMoreElements())
		{
			MTreeNode nd = (MTreeNode)en.nextElement();
			
			//  Level
			int level = nd.getLevel();	//	0 == root
			if (level == 0)
				continue;
			//
			while (oldLevel < level)
			{
				if (level == 1)
					buf.append("<ul class=\"mktree\"  id=\"main\">\n");			//  start first level
				else
					buf.append("<ul style=\"display:none\">\n");//  start next level
				oldLevel++;
			}
			while (oldLevel > level)
			{
				oldLevel--;
				if (oldLevel == 1)
					buf.append("</ul>\n");             			//  finish last level
				else
					buf.append("</ul></li>\n");                 //  finish next level
			}
				
			//	Print Node
			buf.append(printNode(nd, wsc.ctx));
			//Modified by Rob Klein 4/29/07
			if (nd.isOnBar())
				buffav.append(printNode(nd, wsc.ctx));
		}
		//	Final
		while (oldLevel >  0)
		{
			oldLevel--;
			if (oldLevel == 1)
				buf.append("</ul>\n");                         	//  finish last level
			else
				buf.append("</ul></li>\n");                    	//  finish next level
		}

		//Modified by Rob Klein 4/29/07
		//  Set Favorites		
		buf.append("<ul><li class=\"menuSummary\" id=\"218\" onClick=\"changeMenu(event);\">Favorites<ul style=\"display:none\">\n");
		buf.append(buffav);
		buf.append("</ul></li></ul>\n");
		
		
		
		td td = new td().setColSpan(2).setNoWrap(true);
		td.setClass("menuCenter");
		td.addElement(buf.toString());
		table.addElement(new tr().addElement(td));

		//	  Expand/Collapse Info
		td = new td().setColSpan(2);
		td.setClass("menuFooter");		
		//	Modified by Rob Klein 4/29/07
		/**td.addElement(new a("javascript:expandTree('main');", "Expand Menu"));
		td.addElement(" | ");		
		td.addElement( new a("javascript:collapseTree('main');", "Contract Menu"));
		table.addElement(new tr().addElement(td));
		**/
		
		//  Exit Info
		td = new td().setColSpan(2);
		td.setClass("menuFooter");
		String url = request.getRequestURI() + "?Exit=true";
		td.addElement(new a(url, Msg.getMsg(wsc.ctx, "Exit")));
		table.addElement(new tr().addElement(td));
		//
	//	System.out.println(doc);
		return doc;
	}	//	createPage
	
	
	/**
	 *  Print Menu Item
	 *  @param node node
	 *  @param ctx context
	 *  @return string with node
	 */
	private StringBuffer printNode (MTreeNode node, Properties ctx)
	{
		StringBuffer sb = new StringBuffer();
		
		//  Leaf
		if (!node.isSummary())
		{
			/**
			 *  <li id="menuXXXXX"><a href="...." onMouseOver="status='Menu Description';return true;">Menu Entry</a></li>
			 */
			String cssClassName = "";
			String servletName = "";
			if (node.isWindow())
			{
				cssClassName = "menuWindow";
				servletName = "WWindow";
			}
			else if (node.isForm())
			{
				cssClassName = "menuWindow";
				servletName = "WForm";
			}
			else if (node.isReport())
			{
				cssClassName = "menuReport";
				servletName = "WProcess";
			}
			else if (node.isProcess())
			{
				cssClassName = "menuProcess";
				servletName = "WProcess";
			}
			else if (node.isWorkFlow())
			{
				cssClassName = "menuWorkflow";
				servletName = "WWorkflow";
			}
			else if (node.isTask())
			{
				cssClassName = "menuProcess";
				servletName = "WTask";
			}
			else
				servletName = "WError";

			String name = node.getName().replace('\'',' ').replace('"',' ');
			String description = node.getDescription().replace('\'',' ').replace('"',' ');
			//
			sb.append("<li class=\"" + cssClassName
				+ "\" id=\"" + node.getNode_ID()			//	debug
				+ "\"><a href=\"");
			//	Get URL
			boolean standardURL = true;
			if (node.isForm())
			{
				MForm form = new MForm (ctx, node.getNode_ID(), null);
				if (form.getJSPURL() != null && form.getJSPURL().length() > 0)
				{
					sb.append(form.getJSPURL());
					standardURL = false;
				}
			}
			if (standardURL)	//	url = /appl/servletName?AD_Menu_ID=x
			{
				sb.append(WebEnv.getBaseDirectory(servletName))
					.append("?AD_Menu_ID=")
					.append(node.getNode_ID());
			}
			//	remaining a tag
			sb.append("\" title=\"" + description 
					+ "\" onClick=\"showLoadingWindow('" + WebEnv.getBaseDirectory("") + "')\">")
				.append(name)		//	language set in MTree.getNodeDetails based on ctx
				.append("</a></li>\n");
		}
		else
		{
			/**
			 *  <li class="foldHeader" onClick="changeMenu(event)">MenuEntry
			 *  <ul style="display:none">
			 *  ....
			 *  </ul></li>
			 */
			String name = node.getName().replace('\'',' ').replace('"',' ');
			sb.append("\n<li class=\"menuSummary\""
				+ " id=\"" + node.getNode_ID()			//	debug
				+ "\" onClick=\"changeMenu(event);\">")	//  summary node
				.append(name)
				.append("\n");
		}
		return sb;
	}	//  printNode


}   //  WMenu
