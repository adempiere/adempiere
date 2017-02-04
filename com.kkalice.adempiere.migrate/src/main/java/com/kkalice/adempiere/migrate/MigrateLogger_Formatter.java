/*
 * Name:		MigrateLogger_Formatter.java
 * Description:	custom format for log messages
 * Created:		Nov 28, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/MigrateLogger_Formatter.java
 * FileOwner:	spc.dvp
 * FilePerms:	0644
 *
 */

/**
 * This file is part of Adempiere ERP Bazaar
 * http://www.adempiere.org
 * 
 * Copyright (C) Stefan Christians
 * Copyright (C) Contributors
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * Contributors:
 * - Stefan Christians
 * 
 * Sponsors:
 * - K.K. Alice
 * 
 */

package com.kkalice.adempiere.migrate;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * custom format for log messages
 * @author Stefan Christians
 */
public class MigrateLogger_Formatter extends Formatter {

	/** whether or not to display log level, source class, and source method */
	private boolean m_isDisplaySource = true;
	
	/**
	 * constructor setting whether or not to display log level, source class, and source method 
	 * @param isDisplaySource log level, source class, and source method are displayed
	 */
	public MigrateLogger_Formatter(boolean isDisplaySource) {
		super();
		m_isDisplaySource = isDisplaySource;
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
	 */
	@Override
	public String format(LogRecord record) {
		
		// create a string buffer to contain the formatted message
		StringBuffer result = new StringBuffer();

		// prepare level, source class, and source method information
		String severityLevel = record.getLevel().getLocalizedName();
		String className = record.getSourceClassName();
		String methodName = record.getSourceMethodName();
		// we are only interested if a class or method was passed to
		// the logger, otherwise this is merely an informational
		// message tracing migration progress
		if (className!=null || methodName!=null) {
			if (m_isDisplaySource) {
				// the severity level
				result.append(severityLevel);
				
				// the calling class
				if (className != null)
					result.append(" ").append(className);

				// the calling method
				if (methodName != null)
					result.append(".").append(methodName);

				result.append(": ");
			}
		}
		
		// format the message (localization + parameter substitution)
		result.append(formatMessage(record));
		
		// add line break at end of line
		result.append(System.getProperty("line.separator"));
		
		return result.toString();
	}

}
