/*
 * Name:		DBObjectDefinition.java
 * Description:	abstract class to organize common features of all database object definitions
 * Created:		Feb 7, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObjectDefinition.java
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


/**
 * abstract class to organize common features of all database object definitions
 * @author Stefan Christians
 */
public abstract class DBObjectDefinition {
	
	// MEMBERS
	// =======

	/** static parameters */
	protected static Parameters s_parameters = null;
	/** static logger */
	protected static MigrateLogger s_logger = null;
	/** static dbEngine */
	protected static DBEngine s_dbEngine = null;
	
	
	/** parent connection */
	protected DBConnection m_parent = null;

	
	/** name of this definition */
	protected String m_name = null;
	
	/** ordinal sequence of this definition */
	protected int m_sequence = 0;
	

	// CONSTRUCTORS
	// ============

	/**
	 * constructor for DB object definitions
	 * @param parent the calling connection
	 * @param name the name of this definition
	 * @param sequence the ordinal sequence of this definition
	 */
	public DBObjectDefinition(DBConnection parent, String name, int sequence) {
		s_parameters = Parameters.getParameters();
		s_logger = MigrateLogger.getLogger();
		s_dbEngine = DBEngine.getDBEngine();

		
		setParent(parent);
		setName(name);
		setSequence(sequence);
		
	}
	
	
	// METHODS
	// =======

	/**
	 * @return a string representation of this definition
	 */
	abstract public String toString();


	/**
	 * @return the parent connection
	 */
	public DBConnection getParent() {
		return m_parent;
	}


	/**
	 * @param parent the parent to set
	 */
	public void setParent(DBConnection parent) {
		m_parent = parent;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return m_name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		m_name = name;
	}


	/**
	 * @return the sequence
	 */
	public int getSequence() {
		return m_sequence;
	}


	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(int sequence) {
		m_sequence = sequence;
	}
	
	

}
