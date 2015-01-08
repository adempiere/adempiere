/*
 * Name:		DBObject_Trigger_Table.java
 * Description:	detailed definition for trigger
 * Created:		Mar 1, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Trigger_Table.java
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
 * detailed definition for trigger
 * @author Stefan Christians
 */
public class DBObject_Trigger_Table extends DBObjectDefinition {

	/** type of trigger */
	private String m_type = null;
	
	/** trigerring event */
	private String m_event = null;
	
	/** table this trigger is for */
	private String m_table = null;

	/** action type (call or inline function) */
	private String m_actionType = null;

	/** action Orientation (per row or statement)*/
	private String m_actionOrientation = null;

	/**
	 * detailed definition for trigger
	 * @param parent
	 * @param name
	 * @param sequence
	 */
	public DBObject_Trigger_Table(DBConnection parent, String name, int sequence) {
		super(parent, name, sequence);
	}
	
	/**
	 * initializes this trigger
	 * @param triggerType type of trigger
	 * @param triggerEvent event that fires this trigger
	 * @param tableName the table this trigger is for
	 * @param actionType call or inline function
	 * @param actionOrientation per row or per statement
	 */
	public void initializeDefinition(String triggerType, String triggerEvent, String tableName, String actionType, String actionOrientation) {
	
		m_type = triggerType;
		m_event = triggerEvent;
		m_table = tableName;
		m_actionType = actionType;
		m_actionOrientation = actionOrientation;

		s_logger.log(Level.FINEST, toString());
	}


	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectDefinition#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(m_name).append(": ").append(m_type).append(" (").append(m_event).append(") on ").append(m_table);
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
	 * @return the event
	 */
	public String getEvent() {
		return m_event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		m_event = event;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return m_type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		m_type = type;
	}

	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return m_actionType;
	}

	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
		m_actionType = actionType;
	}

	/**
	 * @return the actionOrientation
	 */
	public String getActionOrientation() {
		return m_actionOrientation;
	}

	/**
	 * @param actionOrientation the actionOrientation to set
	 */
	public void setActionOrientation(String actionOrientation) {
		m_actionOrientation = actionOrientation;
	}

}
