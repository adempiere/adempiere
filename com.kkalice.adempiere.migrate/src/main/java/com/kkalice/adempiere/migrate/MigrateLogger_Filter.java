/*
 * Name:		MigrateLogger_Filter.java
 * Description:	filter to limit log messages to only one specific level
 * Created:		Nov 28, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/MigrateLogger_Filter.java
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

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * filter to limit log messages to only one specific level
 * @author Stefan Christians
 */
public class MigrateLogger_Filter implements Filter {

	/** the level to which to limit log messages */
	private Level limitLevel = null;

	/** 
	 * constructor setting level limitation
	 * @param level the log level 
	 */
	public MigrateLogger_Filter (Level level) {
		super();
		limitLevel = level;
	}
	
	/* (non-Javadoc)
	 * @see java.util.logging.Filter#isLoggable(java.util.logging.LogRecord)
	 */
	public boolean isLoggable(LogRecord record) {
		return record.getLevel().equals(limitLevel);
	}

}
