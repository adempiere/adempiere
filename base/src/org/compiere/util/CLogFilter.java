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
package org.compiere.util;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;


/**
 *	Adempiere Log Filter
 *	
 *  @author Jorg Janke
 *  @version $Id: CLogFilter.java,v 1.2 2006/07/30 00:54:36 jjanke Exp $
 */
public class CLogFilter implements Filter
{
	/**
	 * 	Get Filter
	 *	@return singleton
	 */
	public static synchronized CLogFilter get()
	{
		if (s_filter == null)
			s_filter = new CLogFilter();
		return s_filter;
	}
	
	/**	Singleton			*/
	private static CLogFilter	s_filter = null;
	
	/**************************************************************************
	 * 	Constructor
	 */
	public CLogFilter ()
	{
	}	//	CLogFilter

	/**
	 * 	Loggable - Don't log core java classes
	 *	@param record log record
	 *	@return true
	 */
	public boolean isLoggable (LogRecord record)
	{
		if (record.getLevel() == Level.SEVERE
			|| record.getLevel() == Level.WARNING)
			return true;
		//
		String loggerName = record.getLoggerName();
		if (loggerName != null)
		{
		//	if (loggerName.toLowerCase().indexOf("focus") != -1)
		//		return true;
			if (loggerName.startsWith("sun.")
				|| loggerName.startsWith("java.awt.")
				|| loggerName.startsWith("javax.")
				)
				return false;
		}
		String className = record.getSourceClassName();
		if (className != null)
		{
			if (className.startsWith("sun.")
				|| className.startsWith("java.awt.")
				|| className.startsWith("javax.")
				)
				return false;
		}
		return true;
	}	//	isLoggable
	
}	//	CLogFilter
