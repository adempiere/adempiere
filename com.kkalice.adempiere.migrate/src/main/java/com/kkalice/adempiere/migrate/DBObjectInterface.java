/*
 * Name:		DBObjectInterface.java
 * Description:	interface to database objects
 * Created:		Feb 13, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObjectInterface.java
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

import java.util.*;

/**
 * interface to database objects
 * @author Stefan Christians
 */
public interface DBObjectInterface {

	/** 
	 * @return the type of object (singular form)
	 */
	public String getObjectType();
	
	/**
	 * @return the type of object (plural form)
	 */
	public String getObjectTypes();

	/**
	 * @return the type of header (singular form)
	 */
	public String getHeaderType();
	
	/**
	 * @return the type of header (plural form)
	 */
	public String getHeaderTypes();
	
	/**
	 * @return the type of contents (singular form)
	 */
	public String getContentType();
	
	/**
	 * @return the type of contents (plural form)
	 */
	public String getContentTypes();
	
	/**
	 * get sql command to load objects of this type
	 * @param parameters static parameters
	 * @param logger static logger
	 * @param dbEngine static dbEngine
	 * @param vendor database vendor
	 * @param catalog database catalog
	 * @param schema database schema
	 * @return sql command returning OBJECT_NAME
	 */
	public String getLoadObjectSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema);

	/**
	 * get sql command to load headers for this object
	 * @param parameters static parameters
	 * @param logger static logger
	 * @param dbEngine static dbEngine
	 * @param vendor database vendor
	 * @param catalog database catalog
	 * @param schema database schema
	 * @return sql command returning object header information
	 */
	public String getLoadHeaderSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema);

	/**
	 * get sql command to load contents for this object
	 * @param parameters static parameters
	 * @param logger static logger
	 * @param dbEngine static dbEngine
	 * @param vendor database vendor
	 * @param catalog database catalog
	 * @param schema database schema
	 * @return sql command returning object content information
	 */
	public String getLoadContentSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema);

	/**
	 * load headers for this object
	 * @param headerMap map containing all the headers for this object
	 * @param parameters static parameters
	 * @param logger static logger
	 * @param dbEngine static dbEngine
	 * @param parent the calling database connection
	 * @param name the name of this object
	 * @param statement the prepared sql statement
	 */
	public void loadHeaders(HashMap<Integer, DBObjectDefinition> headerMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, PreparedStatementWrapper statement);

	/**
	 * load contents for this object
	 * @param contentMap map containing all the contents for this object
	 * @param parameters static parameters
	 * @param logger static logger
	 * @param dbEngine static dbEngine
	 * @param parent the calling database connection
	 * @param name the name of this object
	 * @param headerMap map containing headers for this object
	 * @param statement the prepared sql statement
	 */
	public void loadContents(HashMap<Integer, DBObjectDefinition> contentMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, PreparedStatementWrapper statement);
	
	/**
	 * drop this object
	 * @param parameters static parameters
	 * @param logger static logger
	 * @param dbEngine static dbEngine
	 * @param parent the calling database connection
	 * @param name the name of this object
	 * @param headerMap map containing headers for this object
	 * @return whether or not the object was successfully dropped
	 */
	public boolean dropObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap);

	/**
	 * create this object in specified database
	 * @param parameters static parameters
	 * @param logger static logger
	 * @param dbEngine static dbEngine
	 * @param db the database connection in which to create this object
	 * @param name the name of this object
	 * @param headerMap map containing headers for this object
	 * @param contentMap map containing contents for this object
	 * @return whether or not the object was successfully created
	 */
	public boolean createObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection db,String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap);

	/**
	 * update this object
	 * @param parameters static parameters
	 * @param logger static logger
	 * @param dbEngine static dbEngine
	 * @param parent the calling database connection
	 * @param name the name of this object
	 * @param headerMap map containing headers for this object
	 * @param contentMap map containing contents for this object
	 * @param sourceObj the source object to use as template
	 * @return whether or not the object was successfully updated
	 */
	public boolean updateObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap, DBObject sourceObj);

	/**
	 * Whether or not this object is marked as customization in the application dictionary
	 * @param parameters static parameters
	 * @param logger static logger
	 * @param dbEngine static dbEngine
	 * @param parent the calling database connection
	 * @param name the name of this object
	 * @param headerMap map containing headers for this object
	 * @return this object is marked as customization in the application dictionary
	 */
	public boolean isCustomMarked(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap);

	/**
	 * Whether or not this object contains customized elements
	 * @param parameters static parameters
	 * @param logger static logger
	 * @param dbEngine static dbEngine
	 * @param parent the calling database connection
	 * @param name the name of this object
	 * @param headerMap map containing headers for this object
	 * @param contentMap map containing contents for this object
	 * @return this object contains customized elements
	 */
	public boolean isCustomImplied(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap);
	
	/**
	 * get list of objects this object depends on 
	 * @param parameters static parameters
	 * @param logger static logger
	 * @param dbEngine static dbEngine
	 * @param parent the calling database connection
	 * @param name the name of this object
	 * @param headerMap map containing headers for this object
	 * @param contentMap map containing contents for this object
	 * @return list of names of dependencies (including this object name itself) in order of appearance
	 */
	public ArrayList<String> getDependencies (Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap);
	
}
