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
import org.apache.ecs.xhtml.div;
import org.apache.ecs.xhtml.form;
import org.apache.ecs.xhtml.h1;
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
		if (!MobileEnv.initWeb (config))
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
		if (MobileUtil.getParameter (request, "Exit") != null)
		{
			MobileUtil.createLoginPage (request, response, this, null, "Exit");
			return;
		}

		//  Get Session attributes
	  	MobileSessionCtx wsc = MobileSessionCtx.get(request);
		if (wsc.ctx == null)
		{
			MobileUtil.createTimeoutPage(request, response, this, null);
			return;
		}

		//  Window
		int AD_Window_ID = MobileUtil.getParameterAsInt(request, "AD_Window_ID");

		//  Forward to WWindow
		if (AD_Window_ID != 0)
		{
			log.fine("AD_Window_ID=" + AD_Window_ID);
			//
			String url = MobileEnv.getBaseDirectory("WWindow?AD_Window_ID=" + AD_Window_ID);
			log.fine("Forward to=" + url);
			//
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
			return;
		}
		
		int AD_User_ID = Env.getAD_User_ID(wsc.ctx);
		int AD_Role_ID = Env.getAD_Role_ID(wsc.ctx);
		int AD_Client_ID = Env.getAD_Client_ID(wsc.ctx);
		int AD_Org_ID = Env.getAD_Client_ID(wsc.ctx);
		String AD_Language = Env.getAD_Language(wsc.ctx);

		if ( wsc.loginInfo != null && wsc.loginInfo.length() > 0 )
		{
			MobileDoc doc = createPage (request, wsc, AD_Role_ID, AD_User_ID, AD_Client_ID, AD_Org_ID);
			MobileUtil.createResponse (request, response, this, null, doc, false);
		}
		
		//  Request not serviceable
		MobileUtil.createErrorPage(request, response, this, "NotImplemented");
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
		doGet(request, response);
	}   //  doPost

	/**
	 * 	Create Menu Page
	 *	@param request request 
	 *	@param wsc context
	 *	@param AD_Role_ID role
	 *	@return document
	 */
	private MobileDoc createPage (HttpServletRequest request, 
		MobileSessionCtx wsc, int AD_Role_ID, int AD_User_ID, int AD_Client_ID, int AD_Org_ID)
	{
		String windowTitle = Msg.getMsg(wsc.ctx, "Menu");
		MobileDoc doc = MobileDoc.createWindow (windowTitle);
		head head = doc.getHead();
		//  Specific Menu Script/Stylesheet
		head.addElement(new link(MobileEnv.getBaseDirectory("/css/menu.css"), link.REL_STYLESHEET, link.TYPE_CSS));
		
		//	Body

		body body = doc.getBody();
		
		div div = new div();
		div.setClass("toolbar");
		h1 header = new h1();
		header.setID("pageTitle");
		div.addElement(header);
		a anchor = new a();
		anchor.setID("backButton");
		anchor.setClass("button");
		div.addElement(anchor);
		
		anchor = new a();
		anchor.setClass("button");
		anchor.setHref(request.getRequestURI()+"?Exit=true");
		anchor.setTarget("_self");
		anchor.addElement("Logout");
		div.addElement(anchor);
		
		
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

		tree.trimTree();
		
		//	Print tree
		StringBuffer buf = new StringBuffer();
		StringBuffer buffav = new StringBuffer();
		en = root.breadthFirstEnumeration();
		int lastNodeId = 0;
		
		buf.append("<ul id=\"main\" selected=\"true\" title=\"Menu\">\n");			//  start first level
		while (en.hasMoreElements())
		{
			MTreeNode nd = (MTreeNode)en.nextElement();
			
			//  Level
			int level = nd.getLevel();	//	0 == root
			if (level == 0)
				continue;
			//
			
			MTreeNode parent = (MTreeNode) nd.getParent();
			if ( parent != null && parent.getNode_ID() != lastNodeId )
			{
				buf.append("</ul>\n<ul id=\"" + parent.getNode_ID() + "\" title=\"" + parent.getName() + "\">\n");
				lastNodeId = parent.getNode_ID();
			}
			
			//	Print Node
			buf.append(printNode(nd, wsc.ctx));
			//Modified by Rob Klein 4/29/07
			if (nd.isOnBar())
				buffav.append(printNode(nd, wsc.ctx));
		}
		buf.append("</ul>\n");                    	//  finish
		

		//Modified by Rob Klein 4/29/07
		//  Set Favorites		
		buf.append("<ul><li class=\"menuSummary\" id=\"218\" onClick=\"changeMenu(event);\">Favorites<ul style=\"display:none\">\n");
		buf.append(buffav);
		buf.append("</ul></li></ul>\n");
		
		
		
		body.addElement(buf.toString());

		body.addElement(div);
		
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
				/*
				cssClassName = "menuWindow";
				servletName = "WForm";
				*/
				return sb;
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
				/*cssClassName = "menuWorkflow";
				servletName = "WWorkflow";*/
				return sb;
			}
			else if (node.isTask())
			{
				/*cssClassName = "menuProcess";
				servletName = "WTask";*/
				return sb;
			}
			else
				servletName = "WError";

			//TODO getTranslation
			String name = Msg.getMsg(ctx, node.getName().replace('\'',' ').replace('"',' '));
			String description = Msg.getMsg(ctx, node.getDescription().replace('\'',' ').replace('"',' '));
			//
			sb.append("<li class=\"" + cssClassName
				+ "\" id=\"" + node.getNode_ID()			//	debug
				+ "\"><a href=\"");
			//	Get URL
			boolean standardURL = true;
			/* form not supported
			if (node.isForm())
			{
				MForm form = new MForm (ctx, node.getNode_ID(), null);
				if (form.getJSPURL() != null && form.getJSPURL().length() > 0)
				{
					sb.append(form.getJSPURL());
					standardURL = false;
				}
			}*/
			if (standardURL)	//	url = /appl/servletName?AD_Menu_ID=x
			{
				sb.append(MobileEnv.getBaseDirectory(servletName))
					.append("?AD_Menu_ID=")
					.append(node.getNode_ID());
			}
			//	remaining a tag
			sb.append("\" class=\"whiteButton\" target=\"_self\" title=\"" + description 
					+ "\">")
				.append(name)		//	language set in MTree.getNodeDetails based on ctx
				.append("</a></li>\n");
		}
		else
		{
			String name = node.getName().replace('\'',' ').replace('"',' ');
			sb.append("\n<li><a href=\"#" + node.getNode_ID() + "\">")
				.append(name)
				.append("</a></li>\n");
		}
		return sb;
	}	//  printNode


}   //  WMenu
