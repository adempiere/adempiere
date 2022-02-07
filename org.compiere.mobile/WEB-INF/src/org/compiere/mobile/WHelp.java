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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compiere.util.CLogger;
import org.compiere.util.Env;


public class WHelp extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2586562865823315494L;
	
	protected CLogger	log = CLogger.getCLogger(getClass());

	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		if (!MobileEnv.initWeb(config))
			throw new ServletException("WHelp.init");
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.fine("doGet");
		WWindowStatus ws = WWindowStatus.get(request);
		MobileDoc doc = null;
		
		/*
		if (ws == null)	{
			doc = MobileDoc.createPopup("No Context");
			doc.addPopupClose(Env.getCtx());
		} else
			doc = ws.mWindow.getHelpDoc(false);
			
			*/
		MobileUtil.createResponse(request, response, this, null, doc, false);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		MobileDoc doc = MobileDoc.create ("Help - Post - Not Implemented");
		MobileUtil.createResponse(request, response, this, null, doc, false);
	}
}