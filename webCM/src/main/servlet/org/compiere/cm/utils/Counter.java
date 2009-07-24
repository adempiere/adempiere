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
package org.compiere.cm.utils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author YS
 * @version $Id$
 */
public class Counter
	implements HttpSessionListener
{

	private static int m_activeSessions = 0;

	/**
	 * 	SessionCreated
	 *	@param se
	 */
	public void sessionCreated (HttpSessionEvent se)
	{
		m_activeSessions++;
	}

	/**
	 * 	Session Destroyed
	 *	@param se
	 */
	public void sessionDestroyed (HttpSessionEvent se)
	{
		if (m_activeSessions > 0)
			m_activeSessions--;
	}

	/**
	 * 	Get Active Sessions
	 *	@return no of sessions
	 */
	public static int getActiveSessions ()
	{
		return m_activeSessions;
	}
}	//	
