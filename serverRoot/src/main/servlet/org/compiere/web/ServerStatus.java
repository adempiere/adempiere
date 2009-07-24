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
package org.compiere.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *	
 *	
 *  @author Jorg Janke
 *  @version $Id: ServerStatus.java,v 1.2 2006/07/30 00:53:33 jjanke Exp $
 */
public class ServerStatus extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 153792058066637363L;

	/**
	 * 	doGet
	 *	@see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 *	@param arg0
	 *	@param arg1
	 *	@throws javax.servlet.ServletException
	 *	@throws java.io.IOException
	 */
	protected void doGet (HttpServletRequest arg0, HttpServletResponse arg1)
		throws ServletException, IOException
	{
		super.doGet (arg0, arg1);
	}

	/**
	 * 	doPost
	 *	@see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 *	@param arg0
	 *	@param arg1
	 *	@throws javax.servlet.ServletException
	 *	@throws java.io.IOException
	 */
	protected void doPost (HttpServletRequest arg0, HttpServletResponse arg1)
		throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		super.doPost (arg0, arg1);
	}

	/**
	 * 	getServletInfo
	 *	@see javax.servlet.Servlet#getServletInfo()
	 *	@return servlet info
	 */
	public String getServletInfo ()
	{
		return super.getServletInfo ();
	}

	/**
	 * 	init
	 *	@see javax.servlet.GenericServlet#init()
	 *	@throws javax.servlet.ServletException
	 */
	public void init ()
		throws ServletException
	{
		super.init ();
	}

	/**
	 * 	init
	 *	@see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
	 *	@param arg0
	 *	@throws javax.servlet.ServletException
	 */
	public void init (ServletConfig arg0)
		throws ServletException
	{
		// TODO Auto-generated method stub
		super.init (arg0);
	}

}	//	ServerStatus
