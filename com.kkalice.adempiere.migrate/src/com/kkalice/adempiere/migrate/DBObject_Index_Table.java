/*
 * Name:		DBObject_Index_Table.java
 * Description:	detailed definition for index
 * Created:		Feb 22, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Index_Table.java
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
 * detailed definition for index
 * @author Stefan Christians
 */
public class DBObject_Index_Table extends DBObjectDefinition {

	/** table on which index is defined */
	private String m_table = null;
	/** whether or not this index is unique */
	private boolean m_isUnique = false;

	/**
	 * detailed definition for index
	 * @param parent
	 * @param name
	 * @param sequence
	 */
	public DBObject_Index_Table(DBConnection parent, String name, int sequence) {
		super(parent, name, sequence);
	}

	/**
	 * initialize detailed definitions forindex
	 * @param tableName the table on which the index is defined
	 * @param isUnique whether or not this index is unique
	 */
	public void initializeDefinition(String tableName, boolean isUnique) {

		m_table = tableName;
		m_isUnique = isUnique;

		s_logger.log(Level.FINEST, toString());
	}


	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectDefinition#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(m_name).append(" (unique=").append(m_isUnique).append(", table=").append(m_table).append(")");
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
	 * @return the isUnique
	 */
	public boolean isUnique() {
		return m_isUnique;
	}

	/**
	 * @param isUnique the isUnique to set
	 */
	public void setUnique(boolean isUnique) {
		m_isUnique = isUnique;
	}

}
