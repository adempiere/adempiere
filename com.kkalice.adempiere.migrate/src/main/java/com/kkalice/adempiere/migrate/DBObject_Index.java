/*
 * Name:		DBObject_Index.java
 * Description:	index object in a database
 * Created:		Feb 22, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Index.java
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
 * index object in a database
 * @author Stefan Christians
 */
public class DBObject_Index implements DBObjectInterface {

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getObjectType()
	 */
	public String getObjectType() {
		return "index";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getObjectTypes()
	 */
	public String getObjectTypes() {
		return "indexes";
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
		return dbEngine.sqlMetadata_indexNames(vendor, catalog, schema);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#loadHeaders(java.util.HashMap, com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String)
	 */
	public void loadHeaders(HashMap<Integer, DBObjectDefinition> headerMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, PreparedStatementWrapper statement) {

		parent.setPreparedStatementString(statement, 1, name);
		ResultSet rs = parent.executeQuery(statement);
		int counter = 0;
		while (parent.getResultSetNext(rs)) {
			String indexName = parent.getResultSetString(rs, "INDEX_NAME");
			String tableName = parent.getResultSetString(rs, "TABLE_NAME");
			boolean isUnique = dbEngine.isTrue(parent.getResultSetString(rs, "IS_UNIQUE"));
			DBObject_Index_Table obj = new DBObject_Index_Table(parent, indexName, counter);
			obj.initializeDefinition(tableName, isUnique);
			headerMap.put(new Integer(counter), obj);
			counter ++;
		}
		parent.releaseResultSet(rs);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#loadContents(java.util.HashMap, com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public void loadContents(HashMap<Integer, DBObjectDefinition> contentMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, PreparedStatementWrapper statement) {

		// only add columns if no unique constraint by the same name already exists
		DBObject_Index_Table header = (DBObject_Index_Table) headerMap.get(0);
		boolean isUnique = header.isUnique();
		String headerName = header.getName();
		if (! (isUnique && parent.isObjectExists(headerName, parent.getUniques()))) {

			// in some datatbases we need the index name more than once in the query
			int nParam = parent.getPreparedStatementParameterCount(statement);
			for (int i=1; i<=nParam; i++) {
				parent.setPreparedStatementString(statement, i, name);
			}

			ResultSet rs = parent.executeQuery(statement);
			while (parent.getResultSetNext(rs)) {
				String indexName = parent.getResultSetString(rs, "INDEX_NAME");
				int indexSeq = parent.getResultSetInt(rs, "INDEX_SEQ");
				String tableName = parent.getResultSetString(rs, "TABLE_NAME");
				String columnName = parent.getResultSetString(rs, "COLUMN_NAME");
				String expression = parent.getResultSetString(rs, "EXPRESSION");
				String sortOrder = parent.getResultSetString(rs, "SORT_ORDER");
				String sortNulls = parent.getResultSetString(rs, "SORT_NULLS");
				
				// replace any column name with expression, if given
				if (expression!=null && expression.length()>0) {
					// expression needs to be corrected in some databases
					expression = dbEngine.correctQuotedFieldNames(parent.getVendor(), expression);
					columnName = expression;
				}

				DBObject_Index_Column obj = new DBObject_Index_Column(parent, indexName, indexSeq);
				obj.initializeDefinition(tableName, columnName, sortOrder, sortNulls);
				contentMap.put(new Integer(indexSeq), obj);

			}
			parent.releaseResultSet(rs);
		}

	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#dropObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public boolean dropObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap) {
		
		boolean success = true;

		DBObject_Index_Table header = (DBObject_Index_Table)headerMap.get(0);
		String indexName = header.getName();
		String sql = dbEngine.sqlObject_dropIndex(parent.getVendor(), parent.getCatalog(), parent.getSchema(), indexName);

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
		DBObject_Index_Table header = (DBObject_Index_Table)headerMap.get(0);
		String vendor = db.getVendor();
		String catalog = db.getCatalog();
		String schema = db.getSchema();
		String tableName = header.getTable();
		String keyName = header.getName();
		boolean isUnique = header.isUnique();

		// column list
		ArrayList<String> columnNames = new ArrayList<String> ();
		ArrayList<String> directions = new ArrayList<String> ();
		ArrayList<String> nullTreatments = new ArrayList<String> ();
	    Vector<Integer> v = new Vector<Integer>(contentMap.keySet());
	    java.util.Collections.sort(v);
		for (Iterator<Integer> it = v.iterator(); it.hasNext();) {
			int key = it.next();
			DBObject_Index_Column iCol = (DBObject_Index_Column)contentMap.get(key);
			columnNames.add(iCol.getColumn());
			directions.add(iCol.getSortOrder());
			nullTreatments.add(iCol.getSortNulls());
		}

		String sql = dbEngine.sqlObject_createIndex(vendor, catalog, schema, tableName, isUnique, keyName, columnNames, directions, nullTreatments);
		
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
		// indexes only depend on tables, which should have already been installed
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
			DBObject_Index_Column iCol = (DBObject_Index_Column)contentMap.get(key);
			String tableName = iCol.getTable();
			String columnName = iCol.getColumn();
			
			// if a referenced table is customized, then this index is customized
			DBObject table = parent.getObjectByName(tableName, parent.getTables());
			if (table!=null) {
				if (table.getCustomizationLevel()>=parameters.CUSTOMMARKED) {
					result = true;
				} else {
					// if a referenced column is customized, then this index is customized
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
		// indexes are not managed in application dictionary
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#updateObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap, com.kkalice.adempiere.migrate.DBObject)
	 */
	public boolean updateObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap, DBObject sourceObj) {
		// indexes are dropped and re-created, not updated
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadContentSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadContentSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_indexColumns(vendor, catalog, schema);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadHeaderSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadHeaderSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_indexTables(vendor, catalog, schema);
	}

}
