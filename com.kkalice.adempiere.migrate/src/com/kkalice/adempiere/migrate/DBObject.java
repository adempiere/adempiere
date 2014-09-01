/*
 * Name:		DBObject.java
 * Description:	common class for database objects
 * Created:		Feb 13, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject.java
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

import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

/**
 * common class for database objects
 * @author Stefan Christians
 */
public class DBObject {
	
	// FIELDS
	// ======

	/** static logger */
	private static MigrateLogger s_logger = null;
	
	/** static parameters */
	private static Parameters s_parameters = null;
	
	/** static dbEngine */
	private static DBEngine s_dbEngine = null;

	
	/** parent connection */
	private DBConnection m_parent = null;
	
	/** interface to implemented object */
	private DBObjectInterface m_interface = null;

	
	/** name of this database object */
	private String m_name = null;
	
	/** header of this database object */
	private HashMap<Integer, DBObjectDefinition> m_headers = null;

	/** contents of this database object */
	private HashMap<Integer, DBObjectDefinition> m_contents = null;

	/** customization level */
	private Integer m_customizationLevel = null;

	
	// CONSTRUCTORS
	// ============

	/**
	 * default constructor
	 * for common class for database objects
	 */
	public DBObject() {
		super();
		s_parameters = Parameters.getParameters();
		s_logger = MigrateLogger.getLogger();
		s_dbEngine = DBEngine.getDBEngine();
	}
	
	/**
	 * constructor by interface
	 * for common class for database objects
	 * @param objectInterface interface to the database object
	 */
	public DBObject (DBObjectInterface objectInterface) {
		this();
		setInterface(objectInterface);
	}
	
	/**
	 * constructor by implementation class
	 * for common class for database objects
	 * @param objectClass implementation class of the database object
	 */
	public DBObject (Class<DBObjectInterface> objectClass) {
		this();

		DBObjectInterface objectInterface = null;
		try {
			objectInterface = (DBObjectInterface)objectClass.newInstance();
		} catch (InstantiationException e) {
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), "constructorByClass", "instantiationException", new Object[] {objectClass.getName(), e.getMessage()});
		} catch (IllegalAccessException e) {
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), "constructorByClass", "illegalAccessException", new Object[] {objectClass.getName(), e.getMessage()});
		}
		
		if (objectInterface != null ) {
			setInterface(objectInterface);
		}
	}

	/**
	 * constructor by connection and implementation class
	 * for common class for database objects
	 * @param parent calling class
	 * @param objectClass implementation class of the database object
	 */
	public DBObject (DBConnection parent, Class<DBObjectInterface> objectClass) {
		this(objectClass);
		setParent(parent);
	}

	/**
	 * constructor by connection, implementation class, and name
	 * for common class for database objects
	 * @param connection calling class
	 * @param objectClass implementation class of the database object
	 * @param name name of the database object
	 */
	public DBObject (DBConnection connection, Class<DBObjectInterface> objectClass, String name) {
		this(connection, objectClass);
		setName(name);
	}

	
	// METHODS
	// =======
	
	/**
	 * @return a string representation of this DB object
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(m_parent.getDirection()).append(" ").append(getObjectType()).append(" ").append(m_name); 
		return sb.toString();
	}
	
	/** 
	 * @return the type of object (singular form) 
	 */
	public String getObjectType() {
		return s_logger.localizeMessage(m_interface.getObjectType());
	}
	
	/**
	 * @return the type of object (plural form)
	 */
	public String getObjectTypes() {
		return s_logger.localizeMessage(m_interface.getObjectTypes());
	}
	
	/**
	 * @return the type of header (singular form)
	 */
	public String getHeaderType() {
		return s_logger.localizeMessage(m_interface.getHeaderType());
	}
	
	/**
	 * @return the type of header (plural form) 
	 */
	public String getHeaderTypes() {
		return s_logger.localizeMessage(m_interface.getHeaderTypes());
	}
	
	/** @return the type of contents (singular form) */
	public String getContentType() {
		return s_logger.localizeMessage(m_interface.getContentType());
	}
	
	/**
	 * @return the type of contents (plural form)
	 */
	public String getContentTypes() {
		return s_logger.localizeMessage(m_interface.getContentTypes());
	}

	/**
	 * @return the interface
	 */
	public DBObjectInterface getInterface() {
		return m_interface;
	}

	/**
	 * @param objectInterface the interface to set
	 */
	public void setInterface(DBObjectInterface objectInterface) {
		m_interface =objectInterface;
	}

	/**
	 * @return the parent connection
	 */
	public DBConnection getParent() {
		return m_parent;
	}

	/**
	 * @param parent the parent connection to set
	 */
	public void setParent(DBConnection parent) {
		m_parent = parent;
	}
	
	/**
	 * get sql command to load objects of this type
	 * @return sql command returning OBJECT_NAME
	 */
	public String getLoadObjectSQL() {
		return m_interface.getLoadObjectSQL(s_parameters, s_logger, s_dbEngine, m_parent.getVendor(), m_parent.getCatalog(), m_parent.getSchema());
	}

	/**
	 * get sql command to load headers for this object
	 * @return sql command returning object header information
	 */
	public String getLoadHeaderSQL() {
		return m_interface.getLoadHeaderSQL(s_parameters, s_logger, s_dbEngine, m_parent.getVendor(), m_parent.getCatalog(), m_parent.getSchema());
	}

	/**
	 * get sql command to load contents for this object
	 * @return sql command returning object content information
	 */
	public String getLoadContentSQL() {
		return m_interface.getLoadContentSQL(s_parameters, s_logger, s_dbEngine, m_parent.getVendor(), m_parent.getCatalog(), m_parent.getSchema());
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
	 * @return the headers
	 */
	public HashMap<Integer, DBObjectDefinition> getHeaders() {
		return m_headers;
	}

	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(HashMap<Integer, DBObjectDefinition> headers) {
		m_headers = headers;
	}

	/**
	 * @return the contents
	 */
	public HashMap<Integer, DBObjectDefinition> getContents() {
		return m_contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(HashMap<Integer, DBObjectDefinition> contents) {
		m_contents = contents;
	}
	
	/**
	 * checks whether a name matches the name of this object
	 * @param name the name to check
	 * @return whether or not the name matches
	 */
	public boolean isName (String name) {
		return getName().equalsIgnoreCase(name);
	}

	/**
	 * load details, headers, and contents for this object
	 * @param psHeader prepared statement for loading header information
	 * @param psContent prepared statement for loading content information
	 */
	public void populate (PreparedStatementWrapper psHeader, PreparedStatementWrapper psContent) {
		loadHeaders(psHeader);
		loadContents(psContent);
	}
	
	/**
	 * load headers for this object
	 * @param ps prepared statement for loading header information
	 */
	public void loadHeaders(PreparedStatementWrapper ps) {
		if (m_interface.getHeaderType()!=null && m_interface.getHeaderTypes()!=null) {
			s_logger.log(Level.FINE, "loadHeaders", new Object[] {getHeaderTypes(), getObjectType(), m_name, m_parent.getDirection()});
			
			m_headers = new HashMap<Integer, DBObjectDefinition>();
			m_interface.loadHeaders(m_headers, s_parameters, s_logger, s_dbEngine, m_parent, m_name, ps);
			
			int counter = m_headers.size();
			String logObject = getHeaderTypes();
			if (counter==1)
				logObject = getHeaderType();
			s_logger.log(Level.FINE, "headersLoaded", new Object[] {Integer.toString(counter), logObject});
		}
	}
	
	/**
	 * load contents for this object
	 * @param ps prepared statement for loading content information
	 */
	public void loadContents(PreparedStatementWrapper ps) {
		if (m_interface.getContentType()!=null && m_interface.getContentTypes()!=null) {
			s_logger.log(Level.FINE, "loadContents", new Object[] {getContentTypes(), getObjectType(), m_name, m_parent.getDirection()});
			
			m_contents = new HashMap<Integer, DBObjectDefinition>();
			m_interface.loadContents(m_contents, s_parameters, s_logger, s_dbEngine, m_parent, m_name, m_headers, ps);
			
			int counter = m_contents.size();
			String logObject = getContentTypes();
			if (counter==1)
				logObject = getContentType();
			s_logger.log(Level.FINE, "contentsLoaded", new Object[] {Integer.toString(counter), logObject});
		}
	}

	/**
	 * @return whether the contents of this object has been populated
	 */
	public boolean isPopulated () {
		boolean result = false;
		if (m_contents.size()>0)
			result = true;
		return result;
	}
	
	/**
	 * drop this object
	 * @return whether or not the object was successfully dropped
	 */
	public boolean drop () {
		boolean result=false;
		if (isPopulated()) {
			s_logger.log(Level.FINE, "dropThisObject", new Object[] {getObjectType(), m_name, m_parent.getDirection()});

			// remember savepoint for rollback
			Savepoint sp = m_parent.setSavepoint("drop_object");

			// drop the object
			result = m_interface.dropObject(s_parameters, s_logger, s_dbEngine, m_parent, m_name, m_headers);
			
			// release savepoint
			m_parent.releaseSavepoint(sp);
			
		}
		return result;
	}
	
	/** 
	 * create this object
	 * @param db database in which to create this object
	 * @return whether or not the object was successfully created 
	 */
	public boolean create (DBConnection db) {
		s_logger.log(Level.FINE, "createThisObject", new Object[] {getObjectType(), m_name, db.getDirection()});

		// remember savepoint for rollback
		Savepoint sp = db.setSavepoint("create object");
		
		// create the object
		boolean result = m_interface.createObject(s_parameters, s_logger, s_dbEngine, db, m_name, m_headers, m_contents);
		
		// release savepoint
		db.releaseSavepoint(sp);
		
		return result;
	}
	
	/**
	 * update this object
	 * @param sourceObj the object to use as template
	 * @return whether or not the object was successfully updated
	 */
	public boolean update (DBObject sourceObj) {
		s_logger.log(Level.FINE, "updateThisObject", new Object[] {getObjectType(), m_name, m_parent.getDirection()});

		// remember savepoint for rollback
		Savepoint sp = m_parent.setSavepoint("update object");
		
		// update the object
		boolean result = m_interface.updateObject(s_parameters, s_logger, s_dbEngine, m_parent, m_name, m_headers, m_contents, sourceObj);
		
		// release savepoint
		m_parent.releaseSavepoint(sp);
		
		return result;
	}
	
	/**
	 * get customization level of this object
	 * @return one of CUSTOMNONE, CUSTOMIMPLIED, CUSTOMMARKED, or CUSTOMPREFIXED
	 */
	@SuppressWarnings("static-access")
	public int getCustomizationLevel () {
		
		// it would be faster to first check whether we have a customized prefix,
		// but we need to initialize customization levels of all sub-objects as well,
		// that is why we start checking those
		
		if (m_customizationLevel==null) {
			m_customizationLevel = new Integer(s_parameters.CUSTOMNONE);
			if (isCustomImplied())
				m_customizationLevel = new Integer(s_parameters.CUSTOMIMPLIED);
			if (isCustomMarked())
				m_customizationLevel = new Integer(s_parameters.CUSTOMMARKED);
			if (isCustomPrefixed())
				m_customizationLevel = new Integer(s_parameters.CUSTOMPREFIXED);
		}
		
		return m_customizationLevel;
	}
	
	/**
	 * @return this object has a prefix registered for customizations
	 */
	private boolean isCustomPrefixed () {
		return m_parent.isCustomPrefix(m_name);
	}
	
	/**
	 * @return this object is marked as customization in the application dictionary
	 */
	private boolean isCustomMarked () {
		return m_interface.isCustomMarked(s_parameters, s_logger, s_dbEngine, m_parent, m_name, m_headers);
	}
	
	/**
	 * @return this object contains customized objects
	 */
	private boolean isCustomImplied () {
		return m_interface.isCustomImplied(s_parameters, s_logger, s_dbEngine, m_parent, m_name, m_headers, m_contents);
	}
	
	/**
	 * get list of objects this object depends on
	 * @return list of names of dependencies (including this object name itself) in order of appearance
	 */
	public ArrayList<String> getDependencies () {
		return m_interface.getDependencies(s_parameters, s_logger, s_dbEngine, m_parent, m_name, m_headers, m_contents);
	}

}
