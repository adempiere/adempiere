/*
 * Name:		DBObject_ForeignKey_Column.java
 * Description:	column of a foreign key
 * Created:		Feb 21, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_ForeignKey_Column.java
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
 * column of a foreign key
 * @author Stefan Christians
 */
public class DBObject_ForeignKey_Column extends DBObjectDefinition {

	/** name of the table this foreign key is defined on */
	private String m_table = null;
	/** name of the column this foreign key is defined for */
	private String m_column = null;
	/** name of the foreign table this key references */
	private String m_fTable = null;
	/** name of the foreign column this key references */
	private String m_fColumn = null;

	/**
	 * constructor for FK column
	 * @param parent
	 * @param name
	 * @param sequence
	 */
	public DBObject_ForeignKey_Column(DBConnection parent, String name, int sequence) {
		super(parent, name, sequence);
	}

	
	/**
	 * initialize this foreign key
	 * @param tableName name of the table this foreign key is defined on
	 * @param columnName name of the column this foreign key is defined for
	 * @param fTableName name of the foreign table this key references
	 * @param fColumnName name of the foreign column this key references
	 */
	public void initializeDefinition (String tableName, String columnName, String fTableName, String fColumnName) {
		
		m_table = tableName;
		m_column = columnName;
		m_fTable = fTableName;
		m_fColumn = fColumnName;
		
		s_logger.log(Level.FINEST, toString());
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectDefinition#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(m_name).append("[").append(m_sequence).append("] = ").append(m_table).append(".").append(m_column).append(" - ").append(m_fTable).append(".").append(m_fColumn); 
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


	/**
	 * @return the fTable
	 */
	public String getFTable() {
		return m_fTable;
	}


	/**
	 * @param table the fTable to set
	 */
	public void setFTable(String table) {
		m_fTable = table;
	}


	/**
	 * @return the fColumn
	 */
	public String getFColumn() {
		return m_fColumn;
	}


	/**
	 * @param column the fColumn to set
	 */
	public void setFColumn(String column) {
		m_fColumn = column;
	}

}
