/*
 * Name:		DBObject_Trigger_Definition.java
 * Description:	definition for a trigger
 * Created:		Feb 9, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Trigger_Definition.java
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
 * definition for a trigger 
 * @author Stefan Christians
 */
public class DBObject_Trigger_Definition extends DBObjectDefinition {
	
	/** code to execute */
	private String m_body;
	

	/**
	 * constructor for trigger definition
	 * @param parent the calling connection
	 * @param name the name of this definition
	 * @param sequence the ordinal sequence of this definition
	 */
	public DBObject_Trigger_Definition(DBConnection parent, String name, int sequence) {
		super(parent, name, sequence);
	}

	/**
	 * initializes this trigger
	 * @param triggerBody code to execute when trigger is fired
	 */
	public void initializeDefinition(String triggerBody) {
	
		m_body = triggerBody;

		s_logger.log(Level.FINEST, toString());
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectDefinition#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(m_name).append(": ").append(m_body);
		return sb.toString();
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return m_body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		m_body = body;
	}

}
