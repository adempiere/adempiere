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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compiere.util.WebEnv;
import org.compiere.util.WebUtil;

/**
 *  Application Start Page
 *  <pre>
 *  Creates a Frame with
 *  - Command   - cmd.html  (invisible)
 *  - Menu      - menu.html
 *  - Window    = window.html
 *  </pre>
 *  framesetOuter
 *  +- WCmd
 *  +- framesetMenuWindow
 *     +- WMenu
 *     +- framesetWindow
 *        +- WPopUp
 *        +- WWindow
 *  see webapps/adempiere/index.html
 *  @author Jorg Janke
 *  @version $Id: WStart.java,v 1.1 2009/04/15 11:27:15 vinhpt Exp $
 */
public class WStart extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3520310916249390852L;


	/**
	 *  Set UI directory to Servlet init param
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("WStart.init");
	}   //  init

	/**
	 * Process the HTTP Get request
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		WebUtil.createLoginPage (request, response, this, null, null);
	}   //  doGet


	/**
	 *  Process the HTTP Post request
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		doGet (request, response);
	}   //  doPost

}   //  WStart
