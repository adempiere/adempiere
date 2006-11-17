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
import javax.servlet.*;
import javax.servlet.http.*;
import org.compiere.util.*;


/**
 *	Web (window) Help
 *	
 *  @author Jorg Janke
 *  @version $Id: WHelp.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class WHelp extends HttpServlet
{
	/**	Logger			*/
	protected CLogger	log = CLogger.getCLogger(getClass());

	/**
	 * Initialize global variables
	 */
	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("WHelp.init");
	}   //  init

	/**
	 * Process the HTTP Get request
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.fine("doGet");
		WWindowStatus ws = WWindowStatus.get(request);
		//
		WebDoc doc = null;
		if (ws == null)
		{
			doc = WebDoc.createPopup("No Context");
			doc.addPopupClose();
		}
		else
			doc = ws.mWindow.getHelpDoc(false);
		//
		WebUtil.createResponse(request, response, this, null, doc, false);
	}   //  doGet


	/**
	 *  Process the HTTP Post request
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		WebDoc doc = WebDoc.create ("Help - Post - Not Implemented");
		WebUtil.createResponse(request, response, this, null, doc, false);
	}   //  doPost

}	//	WHelp
