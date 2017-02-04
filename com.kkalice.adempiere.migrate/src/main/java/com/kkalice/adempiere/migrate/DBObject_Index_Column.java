/*
 * Name:		DBObject_Index_Column.java
 * Description:	column of an index
 * Created:		Feb 22, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Index_Column.java
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
 * column of an index
 * @author Stefan Christians
 */
public class DBObject_Index_Column extends DBObjectDefinition {

	/** name of the table */
	String m_table = null;
	/** name of the column */
	String m_column = null;
	/** sort order */
	String m_sortOrder = null;
	/** sort nulls */
	String m_sortNulls = null;

	/**
	 * column of an index
	 * @param parent
	 * @param name
	 * @param sequence
	 */
	public DBObject_Index_Column(DBConnection parent, String name, int sequence) {
		super(parent, name, sequence);
	}

	/**
	 * initialize this index
	 * @param tableName name of the table
	 * @param columnName name of the column
	 * @param sortOrder ASCending or DESCending
	 * @param sortNulls first or last
	 */
	public void initializeDefinition (String tableName, String columnName, String sortOrder, String sortNulls) {
		
		m_table = tableName;
		m_column = columnName;
		m_sortOrder = sortOrder;
		m_sortNulls = sortNulls;
		
		s_logger.log(Level.FINEST, toString());
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectDefinition#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(m_name).append("[")
			.append(m_sequence).append("] ")
			.append(m_table).append(".")
			.append(m_column).append(" (")
			.append(m_sortOrder).append(" NULLS ").append(m_sortNulls).append(")"); 
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
	 * @return the sortOrder
	 */
	public String getSortOrder() {
		return m_sortOrder;
	}

	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(String sortOrder) {
		m_sortOrder = sortOrder;
	}

	/**
	 * @return the sortNulls
	 */
	public String getSortNulls() {
		return m_sortNulls;
	}

	/**
	 * @param sortNulls the sortNulls to set
	 */
	public void setSortNulls(String sortNulls) {
		m_sortNulls = sortNulls;
	}

	
}
