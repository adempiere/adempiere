/*
 * Name:		DBObject_Unique_Column.java
 * Description:	column of a unique constraint
 * Created:		Feb 28, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Unique_Column.java
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
 * column of a unique constraint
 * @author Stefan Christians
 */
public class DBObject_Unique_Column extends DBObjectDefinition {

	/** name of the table */
	String m_table = null;
	/** name of the column */
	String m_column = null;

	/**
	 * column of a unique constraint
	 * @param parent
	 * @param name
	 * @param sequence
	 */
	public DBObject_Unique_Column(DBConnection parent, String name, int sequence) {
		super(parent, name, sequence);
	}

	/**
	 * initialize this unique constraint
	 * @param tableName name of the table
	 * @param columnName name of the column
	 */
	public void initializeDefinition (String tableName, String columnName) {
		
		m_table = tableName;
		m_column = columnName;
		
		s_logger.log(Level.FINEST, toString());
	}


	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectDefinition#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(m_name).append("[").append(m_sequence).append("] = ").append(m_table).append(".").append(m_column); 
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
	 * @return the column
	 */
	public String getColumn() {
		return m_column;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn(String column) {
		m_column = column;
	}

}
