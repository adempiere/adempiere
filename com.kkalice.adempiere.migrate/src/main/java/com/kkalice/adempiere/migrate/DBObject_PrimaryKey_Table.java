/*
 * Name:		DBObject_PrimaryKey_Table.java
 * Description:	detailed definition for primary key
 * Created:		Feb 14, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_PrimaryKey_Table.java
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
 * detailed definition for primary key
 * @author Stefan Christians
 */
public class DBObject_PrimaryKey_Table extends DBObjectDefinition {
	
	/** table on which primary key is defined */
	private String m_table = null;
	/** whether this primary key is deferrable */
	private boolean m_isDeferrable = false;
	/** whether this primary key is initially deferred */
	private boolean m_isDeferred = false;

	/**
	 * detailed definition for primary key
	 * @param parent
	 * @param name
	 * @param sequence
	 */
	public DBObject_PrimaryKey_Table(DBConnection parent, String name, int sequence) {
		super(parent, name, sequence);
	}
	
	/**
	 * initialize detailed definitions for primary key
	 * @param tableName the table on which the primary key is defined
	 * @param isDeferrable whether or not the constraint is deferrable
	 * @param isDeferred whether or not the constraint is initially deferred
	 */
	public void initializeDefinition(String tableName, boolean isDeferrable, boolean isDeferred) {

		m_table = tableName;
		m_isDeferrable = isDeferrable;
		m_isDeferred = isDeferred;

		s_logger.log(Level.FINEST, toString());
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectDefinition#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(m_name).append(": ").append(m_table)
			.append(" (deferrable:").append(m_isDeferrable)
			.append(", initially deferred:").append(m_isDeferred).append(")");
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
	 * @return the isDeferrable
	 */
	public boolean isDeferrable() {
		return m_isDeferrable;
	}

	/**
	 * @param isDeferrable the isDeferrable to set
	 */
	public void setDeferrable(boolean isDeferrable) {
		m_isDeferrable = isDeferrable;
	}

	/**
	 * @return the isDeferred
	 */
	public boolean isDeferred() {
		return m_isDeferred;
	}

	/**
	 * @param isDeferred the isDeferred to set
	 */
	public void setDeferred(boolean isDeferred) {
		m_isDeferred = isDeferred;
	}
	
	
}
