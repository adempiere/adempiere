/*
 * Name:		DBObject_Unique.java
 * Description:	unique constraint object in database
 * Created:		Feb 28, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Unique.java
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

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/**
 * unique constraint object in database
 * @author Stefan Christians
 */
public class DBObject_Unique implements DBObjectInterface {

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getObjectType()
	 */
	public String getObjectType() {
		return "unique constraint";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getObjectTypes()
	 */
	public String getObjectTypes() {
		return "unique constraints";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getHeaderType()
	 */
	public String getHeaderType() {
		return "table";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getHeaderTypes()
	 */
	public String getHeaderTypes() {
		return "tables";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getContentType()
	 */
	public String getContentType() {
		return "column";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getContentTypes()
	 */
	public String getContentTypes() {
		return "columns";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadObjectSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadObjectSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_uniqueNames(vendor, catalog, schema);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#loadHeaders(java.util.HashMap, com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String)
	 */
	public void loadHeaders(HashMap<Integer, DBObjectDefinition> headerMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, PreparedStatementWrapper statement) {

		parent.setPreparedStatementString(statement, 1, name);
		ResultSet rs = parent.executeQuery(statement);
		int counter = 0;
		while (parent.getResultSetNext(rs)) {
			String uniqueName = parent.getResultSetString(rs, "UNIQUE_NAME");
			String tableName = parent.getResultSetString(rs, "TABLE_NAME");
			boolean isDeferrable = dbEngine.isTrue(parent.getResultSetString(rs, "IS_DEFERRABLE"));
			boolean isDeferred = dbEngine.isTrue(parent.getResultSetString(rs, "INITIALLY_DEFERRED"));
			DBObject_Unique_Table obj = new DBObject_Unique_Table(parent, uniqueName, counter);
			obj.initializeDefinition(tableName, isDeferrable, isDeferred);
			headerMap.put(new Integer(counter), obj);
			counter ++;
		}
		parent.releaseResultSet(rs);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#loadContents(java.util.HashMap, com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public void loadContents(HashMap<Integer, DBObjectDefinition> contentMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, PreparedStatementWrapper statement) {

		parent.setPreparedStatementString(statement, 1, name);
		ResultSet rs = parent.executeQuery(statement);
		while (parent.getResultSetNext(rs)) {
			String uniqueName = parent.getResultSetString(rs, "UNIQUE_NAME");
			int uniqueSeq = parent.getResultSetInt(rs, "UNIQUE_SEQ");
			String tableName = parent.getResultSetString(rs, "TABLE_NAME");
			String columnName = parent.getResultSetString(rs, "COLUMN_NAME");
			DBObject_Unique_Column obj = new DBObject_Unique_Column(parent, uniqueName, uniqueSeq);
			obj.initializeDefinition(tableName, columnName);
			contentMap.put(new Integer(uniqueSeq), obj);
		}
		parent.releaseResultSet(rs);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#dropObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public boolean dropObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap) {

		boolean success = true;

		DBObject_Unique_Table header = (DBObject_Unique_Table)headerMap.get(0);
		String constraintName = header.getName();
		String tableName = header.getTable();
		String sql = dbEngine.sqlObject_dropConstraint(parent.getVendor(), parent.getCatalog(), parent.getSchema(), constraintName, tableName);

		Statement stmt = parent.setStatement();
		if (parent.executeUpdate(stmt, sql, false, false) == null)
			success = false;
		parent.releaseStatement(stmt);

		return success;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#createObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	public boolean createObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection db, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {
		
		boolean success=true;
		
		// prefix
		DBObject_Unique_Table header = (DBObject_Unique_Table)headerMap.get(0);
		String vendor = db.getVendor();
		String catalog = db.getCatalog();
		String schema = db.getSchema();
		String tableName = header.getTable();
		String keyName = header.getName();

		// column list
		ArrayList<String> columns = new ArrayList<String> ();
		Vector<Integer> v = new Vector<Integer>(contentMap.keySet());
		java.util.Collections.sort(v);
		for (Iterator<Integer> it = v.iterator(); it.hasNext();) {
			int key = it.next();
			DBObject_Unique_Column pkCol = (DBObject_Unique_Column)contentMap.get(key);
			columns.add(pkCol.getColumn());
		}

		// suffix
		boolean isDeferrable = header.isDeferrable();
		boolean isDeferred = header.isDeferred();

		String sql = dbEngine.sqlObject_createUnique(vendor, catalog, schema, tableName, keyName, columns, isDeferrable, isDeferred);

		Statement stmt = db.setStatement();
		if (db.executeUpdate(stmt, sql, false, false) == null)
			success = false;
		db.releaseStatement(stmt);
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getDependencies(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	public ArrayList<String> getDependencies(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {
		// unique constraints only have tables as dependencies, which should have already been installed
		ArrayList<String> list = new ArrayList<String>();
		list.add(name.toUpperCase());
		return list;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#isCustomImplied(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	@SuppressWarnings("static-access")
	public boolean isCustomImplied(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {
		boolean result = false;

		for (Iterator<Integer> it = contentMap.keySet().iterator(); it.hasNext() && result==false;) {
			int key = it.next();
			DBObject_Unique_Column uCol = (DBObject_Unique_Column)contentMap.get(key);
			String tableName = uCol.getTable();
			String columnName = uCol.getColumn();
			
			// if a referenced table is customized, then this unique constraint is customized
			DBObject table = parent.getObjectByName(tableName, parent.getTables());
			if (table!=null) {
				if (table.getCustomizationLevel()>=parameters.CUSTOMMARKED) {
					result = true;
				} else {
					// if a referenced column is customized, then this unique constraint is customized
					HashMap<Integer,DBObjectDefinition> tableColumns = table.getContents();
					for (Iterator<Integer> it2 = tableColumns.keySet().iterator(); it2.hasNext();) {
						int key2 = it2.next();
						DBObject_Table_Column tableColumn = (DBObject_Table_Column)tableColumns.get(key2);
						if (columnName.equalsIgnoreCase(tableColumn.getName())) {
							if (tableColumn.isCustomized())
								result = true;
						}
					}
				}
			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#isCustomMarked(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public boolean isCustomMarked(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap) {
		// unique constraints are not managed in application dictionary
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#updateObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap, com.kkalice.adempiere.migrate.DBObject)
	 */
	public boolean updateObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap, DBObject sourceObj) {
		// unique constraints are dropped and re-created, but not updated
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadContentSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadContentSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_uniqueColumns(vendor, catalog, schema);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadHeaderSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadHeaderSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_uniqueTables(vendor, catalog, schema);
	}

}
