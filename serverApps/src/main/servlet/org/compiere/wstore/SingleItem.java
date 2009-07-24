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
package org.compiere.wstore;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compiere.util.CLogger;
import org.compiere.util.WebEnv;

/**
 *	Single Item Purchase
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: SingleItem.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class SingleItem extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6314271823384026972L;
	/**	Logging						*/
	private CLogger			log = CLogger.getCLogger(getClass());

	/**
	 * 	Initialize global variables
	 *  @param config servlet configuration
	 *  @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("SingleItem");
	}	//	init

	/**
	 * Clean up resources
	 */
	public void destroy()
	{
		log.info("destroy");
	}   //  destroy

	//  Parameter Names
	private static final String P_ITEM_NAME =			"item_name";
	private static final String P_ITEM_NUMBER =			"item_number";
	private static final String P_AMOUNT =				"amount";
	private static final String P_QUANTITY =			"quantity";
	private static final String P_UNDEFINED_QUANTITY =	"undefined_quantity";
	private static final String P_RETURN_URL =			"return";
	private static final String P_CANCEL_URL =			"cancel_return";
	//
	private static final String P_SUBMIT =				"SUBMIT";

	/*************************************************************************/

	/**
	 *  Process the initial HTTP Get request
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("From " + request.getRemoteHost() + " - " + request.getRemoteAddr());
	}   //  doGet


	/**
	 *  Process the HTTP Post request
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("From " + request.getRemoteHost() + " - " + request.getRemoteAddr());
	}   //  doPost

}	//	SingleItem
