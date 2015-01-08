/*
 * Name:		DBObject_ForeignKey.java
 * Description:	
 * Created:		Feb 17, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_ForeignKey.java
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
 * foreign key object in a database
 * @author Stefan Christians
 */
public class DBObject_ForeignKey implements DBObjectInterface {

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getObjectType()
	 */
	public String getObjectType() {
		return "foreign key";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getObjectTypes()
	 */
	public String getObjectTypes() {
		return "foreign keys";
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
		return dbEngine.sqlMetadata_foreignKeyNames(vendor, catalog, schema);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#loadHeaders(java.util.HashMap, com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String)
	 */
	public void loadHeaders(HashMap<Integer, DBObjectDefinition> headerMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, PreparedStatementWrapper statement) {

		parent.setPreparedStatementString(statement, 1, name);
		ResultSet rs = parent.executeQuery(statement);
		int counter = 0;
		while (parent.getResultSetNext(rs)) {
			String fkName = parent.getResultSetString(rs, "FK_NAME");
			String tableName =parent.getResultSetString(rs, "TABLE_NAME");
			String fTableName = parent.getResultSetString(rs, "FTABLE_NAME");
			boolean isDeferrable = dbEngine.isTrue(parent.getResultSetString(rs, "IS_DEFERRABLE"));
			boolean isDeferred = dbEngine.isTrue(parent.getResultSetString(rs, "INITIALLY_DEFERRED"));
			String matchType = parent.getResultSetString(rs, "MATCH_TYPE");
			String onUpdate = parent.getResultSetString(rs, "ON_UPDATE");
			String onDelete = parent.getResultSetString(rs, "ON_DELETE");
			DBObject_ForeignKey_Table obj = new DBObject_ForeignKey_Table(parent, fkName, counter);
			obj.initializeDefinition(tableName, fTableName, isDeferrable, isDeferred, matchType, onUpdate, onDelete);
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
			String fkName = parent.getResultSetString(rs, "FK_NAME");
			int fkSeq = parent.getResultSetInt(rs, "FK_SEQ");
			String tableName = parent.getResultSetString(rs, "TABLE_NAME");
			String columnName = parent.getResultSetString(rs, "COLUMN_NAME");
			String fTableName = parent.getResultSetString(rs, "FTABLE_NAME");
			String fColumnName = parent.getResultSetString(rs, "FCOLUMN_NAME");
			DBObject_ForeignKey_Column obj = new DBObject_ForeignKey_Column(parent, fkName, fkSeq);
			obj.initializeDefinition(tableName, columnName, fTableName, fColumnName);
			contentMap.put(new Integer(fkSeq), obj);
		}
		parent.releaseResultSet(rs);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#dropObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public boolean dropObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap) {
		boolean success = true;
		
		DBObject_ForeignKey_Table header = (DBObject_ForeignKey_Table)headerMap.get(0);
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
		DBObject_ForeignKey_Table header = (DBObject_ForeignKey_Table)headerMap.get(0);
		String vendor = db.getVendor();
		String catalog = db.getCatalog();
		String schema = db.getSchema();
		String keyName = header.getName();
		String localTable = header.getTable();
		String foreignTable = header.getFTable();

		// column lists
		ArrayList<String> localColumns = new ArrayList<String>();
		ArrayList<String> foreignColumns = new ArrayList<String>();
	    Vector<Integer> v = new Vector<Integer>(contentMap.keySet());
	    java.util.Collections.sort(v);
		for (Iterator<Integer> it = v.iterator(); it.hasNext();) {
			int key = it.next();
			DBObject_ForeignKey_Column fkCol = (DBObject_ForeignKey_Column)contentMap.get(key);
			localColumns.add(fkCol.getColumn());
			foreignColumns.add(fkCol.getFColumn());
		}
		
		// suffix
		boolean isDeferrable = header.isDeferrable();
		boolean isDeferred = header.isDeferred();
		String matchType = header.getMatchType();
		String onDelete = header.getOnDelete();
		String onUpdate = header.getOnUpdate();

		String sql = dbEngine.sqlObject_createForeignKey(vendor, catalog, schema, keyName, localTable, localColumns, foreignTable, foreignColumns, matchType, onDelete, onUpdate, isDeferrable, isDeferred);
		
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
		// foreign keys have no dependencies
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
			DBObject_ForeignKey_Column fkCol = (DBObject_ForeignKey_Column)contentMap.get(key);
			String tableName = fkCol.getTable();
			String columnName = fkCol.getColumn();
			String ftableName = fkCol.getFTable();
			String fcolumnName = fkCol.getFColumn();

			
			// if a referenced table is customized, then this foreign key is customized
			DBObject table = parent.getObjectByName(tableName, parent.getTables());
			DBObject ftable = parent.getObjectByName(ftableName, parent.getTables());
			if (table!=null && ftable!=null) {
				if (table.getCustomizationLevel()>=parameters.CUSTOMMARKED) {
					// own table
					result = true;
				} else if (ftable.getCustomizationLevel()>=parameters.CUSTOMMARKED) {
					// foreign table
					result = true;
				}
				// if a referenced column is customized, then this foreign key is customized
				if (! result) {
					// own columns
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
				if (! result) {
					// foreign columns
					HashMap<Integer,DBObjectDefinition> tableColumns = ftable.getContents();
					for (Iterator<Integer> it2 = tableColumns.keySet().iterator(); it2.hasNext();) {
						int key2 = it2.next();
						DBObject_Table_Column tableColumn = (DBObject_Table_Column)tableColumns.get(key2);
						if (fcolumnName.equalsIgnoreCase(tableColumn.getName())) {
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
		// foreign keys are not managed in application dictionary
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#updateObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap, com.kkalice.adempiere.migrate.DBObject)
	 */
	public boolean updateObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap, DBObject sourceObj) {
		// foreign keys are dropped and created, but not updated
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadContentSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadContentSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_foreignKeyColumns(vendor, catalog, schema);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadHeaderSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadHeaderSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_foreignKeyTables(vendor, catalog, schema);
	}

}
