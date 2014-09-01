/*
 * Name:		DBObject_ForeignKey_Table.java
 * Description:	detailed definition for foreign key
 * Created:		Feb 17, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_ForeignKey_Table.java
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
 * detailed definition for foreign key
 * @author Stefan Christians
 */
public class DBObject_ForeignKey_Table extends DBObjectDefinition {
	
	/** table on which foreign key is defined */
	private String m_table = null;
	/** table on which is referenced by foreign key */
	private String m_fTable = null;
	/** whether this foreign key is deferrable */
	private boolean m_isDeferrable = false;
	/** whether this foreign key is initially deferred */
	private boolean m_isDeferred = false;
	/** match type (rule for null keys) */
	private String m_matchType = null;
	/** update action */
	private String m_onUpdate = null;
	/** delete action */
	private String m_onDelete = null;

	/**
	 * detailed definition for foreign key
	 * @param parent
	 * @param name
	 * @param sequence
	 */
	public DBObject_ForeignKey_Table(DBConnection parent, String name, int sequence) {
		super(parent, name, sequence);
	}

	/**
	 * initialize detailed definition for foreign key
	 * @param tableName name of the table
	 * @param fTableName name of the reference table
	 * @param isDeferrable whether or not the constraint is deferrable
	 * @param isDeferred whether or not the constraint is initially deferred
	 * @param matchType rule for null keys
	 * @param onUpdate rule for key updates
	 * @param onDelete rule for key deletes
	 */
	public void initializeDefinition(String tableName, String fTableName, boolean isDeferrable, boolean isDeferred, String matchType, String onUpdate, String onDelete) {

		m_table = tableName;
		m_fTable = fTableName;
		m_isDeferrable = isDeferrable;
		m_isDeferred = isDeferred;
		m_matchType = matchType;
		m_onUpdate = onUpdate;
		m_onDelete = onDelete;

		s_logger.log(Level.FINEST, toString());
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectDefinition#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(m_name).append(": ").append(m_table).append(" - ").append(m_fTable)
			.append(" (deferrable:").append(m_isDeferrable)
			.append(", initially deferred:").append(m_isDeferred)
			.append(", match type:").append(m_matchType )
			.append(", on update:").append(m_onUpdate)
			.append(", on delete:").append(m_onDelete).append(")");
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

	/**
	 * @return the matchType
	 */
	public String getMatchType() {
		return m_matchType;
	}

	/**
	 * @param matchType the matchType to set
	 */
	public void setMatchType(String matchType) {
		m_matchType = matchType;
	}

	/**
	 * @return the onUpdate
	 */
	public String getOnUpdate() {
		return m_onUpdate;
	}

	/**
	 * @param onUpdate the onUpdate to set
	 */
	public void setOnUpdate(String onUpdate) {
		m_onUpdate = onUpdate;
	}

	/**
	 * @return the onDelete
	 */
	public String getOnDelete() {
		return m_onDelete;
	}

	/**
	 * @param onDelete the onDelete to set
	 */
	public void setOnDelete(String onDelete) {
		m_onDelete = onDelete;
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

	
}
