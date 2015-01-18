/*
 * Name:		DBObject_Check_Rule.java
 * Description:	rules for a check constraint
 * Created:		Feb 28, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Check_Rule.java
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

import java.util.logging.Level;

/**
 * rules for a check constraint
 * @author Stefan Christians
 */
public class DBObject_Check_Rule extends DBObjectDefinition {

	/** table on which check constraint is defined */
	private String m_table = null;
	/** check clause */
	private String m_checkClause = null;
	
	/**
	 * constructor for check constraint rules
	 * @param parent
	 * @param name
	 * @param sequence
	 */
	public DBObject_Check_Rule(DBConnection parent, String name, int sequence) {
		super(parent, name, sequence);
	}

	/**
	 * 
	 * @param tableName table on which check constraint is defined
	 * @param checkClause the check clause
	 */
	public void initializeDefinition(String tableName, String checkClause) {

		m_table = tableName;
		m_checkClause = checkClause;

		s_logger.log(Level.FINEST, toString());
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectDefinition#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(m_name).append(": ").append(m_table).append(" - ").append( m_checkClause);
		return sb.toString();
	}

	/**
	 * @return the table
	 */
	public String getTable() {
		return m_table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(String table) {
		m_table = table;
	}

	/**
	 * @return the checkClause
	 */
	public String getCheckClause() {
		return m_checkClause;
	}

	/**
	 * @param checkClause the checkClause to set
	 */
	public void setCheckClause(String checkClause) {
		m_checkClause = checkClause;
	}
	
}
