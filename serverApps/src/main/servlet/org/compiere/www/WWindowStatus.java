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

import java.util.*;
import javax.servlet.http.*;
import org.compiere.model.*;


/**
 *  WWindow Status Information (Value Object)
 *
 *  @author Jorg Janke
 *  @version  $Id: WWindowStatus.java,v 1.3 2006/07/30 00:53:21 jjanke Exp $
 */
public class WWindowStatus
{
	/**
	 * 	Get Web Window Status.
	 	WWindowStatus ws = WWindowStatus.get(ctx);
	 *	@param request request
	 *	@return ctx or null
	 */
	public static WWindowStatus get (HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if (session == null)
			return null;
		return (WWindowStatus)session.getAttribute(NAME);
	}	//	get
	
	/**************************************************************************
	 *  Constructor - First Tab - First Row - Single Row.
	 *  <br>
	 *  Initialize Formats
	 *  @param mWindowVO window VO
	 */
	public WWindowStatus (GridWindowVO mWindowVO)
	{
		mWindow = new GridWindow(mWindowVO);
		curTab = mWindow.getTab(0);
		curTab.setSingleRow(true);
		//
		ctx = mWindowVO.ctx;
	}   //  WWindowStatus

	/**	Session Attribute Name			*/
	public static final String NAME	= "WWindowStatus"; 
	
	/** The MWindow                 */
	protected GridWindow       mWindow;
	/** The current MTab            */
	protected GridTab          curTab;

	/** Window Context 				*/
	public Properties    ctx = null;

	/**
	 *  String representation
	 *  @return String representation
	 */
	public String toString()
	{
		return "WWindowStatus[" + mWindow
			+ " - " + curTab + "]";
	}   //  toString

}   //  WWindowStatus
