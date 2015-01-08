/*
 * Name:		DBObject_Table.java
 * Description:	table object in a database
 * Created:		Feb 13, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Table.java
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

import java.sql.*;
import java.util.*;

/**
 * table object in a database
 * @author Stefan Christians
 */
public class DBObject_Table implements DBObjectInterface {

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBInterface#getObjectType()
	 */
	public String getObjectType() {
		return "table";
	}
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBInterface#getObjectTypes()
	 */
	public String getObjectTypes() {
		return "tables";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBInterface#getHeaderType()
	 */
	public String getHeaderType() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBInterface#getHeaderTypes()
	 */
	public String getHeaderTypes() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBInterface#getContentType()
	 */
	public String getContentType() {
		return "column";
	}
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBInterface#getContentTypes()
	 */
	public String getContentTypes() {
		return "columns";
	}
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBInterface#getLoadObjectSQL(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadObjectSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_tableNames(vendor, catalog, schema);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#loadHeaders(java.util.HashMap, com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String)
	 */
	public void loadHeaders(HashMap<Integer, DBObjectDefinition> headerMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, PreparedStatementWrapper statement) {
		// table has no headers
		return;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBInterface#loadContents(java.util.HashMap, com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void loadContents(HashMap<Integer, DBObjectDefinition> contentMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, PreparedStatementWrapper statement) {

		parent.setPreparedStatementString(statement, 1, name);
		ResultSet rs = parent.executeQuery(statement);
		@SuppressWarnings("unused")
		int counter = 0;
		while (parent.getResultSetNext(rs)) {

			String tableName = parent.getResultSetString(rs, "TABLE_NAME");
			int columnSequence = parent.getResultSetInt(rs, "COLUMN_SEQUENCE");
			String columnName = parent.getResultSetString(rs, "COLUMN_NAME");
			String columnType = parent.getResultSetString(rs, "COLUMN_TYPE");
			int columnSize = parent.getResultSetInt(rs, "COLUMN_SIZE");
			int columnPrecision = parent.getResultSetInt(rs, "COLUMN_PRECISION");
			if (parent.getResultSetWasNull(rs))
				columnPrecision = -1;
			String columnDefault = parent.getResultSetString(rs, "COLUMN_DEFAULT");
			boolean columnNullable = dbEngine.isTrue(parent.getResultSetString(rs, "COLUMN_NULLABLE"));

			// correct char width for oracle
			columnSize = dbEngine.correctOracleCharSize(columnType, columnSize, parent.getCharDevisor());

			// correct size and precision of ID fields
			columnSize = dbEngine.correctIDColumnSize(parent.getVendor(), columnName, columnType, columnSize);
			columnPrecision = dbEngine.correctIDColumnScale(parent.getVendor(), columnName, columnType, columnPrecision);


			DBObject_Table_Column obj = new DBObject_Table_Column (parent, columnName, columnSequence);
			obj.initializeDefinition(tableName, columnType, columnSize, columnPrecision, columnDefault, columnNullable);
			contentMap.put(new Integer(columnSequence), obj);

			counter++;
		}
		parent.releaseResultSet(rs);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#dropObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public boolean dropObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap) {
		
		boolean success = true;

		String sql = dbEngine.sqlObject_dropTable(parent.getVendor(), parent.getCatalog(), parent.getSchema(), name);

		Statement stmt =parent.setStatement();
		if (parent.executeUpdate(stmt, sql, false, false) == null)
			success = false;
		parent.releaseStatement(stmt);

		return success;
	}
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#createObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	public boolean createObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection db, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {

		boolean success = true;
		
		// sort columns in correct order for iteration
	    Vector<Integer> v = new Vector<Integer>(contentMap.keySet());
	    java.util.Collections.sort(v);

	    // build arrays of column information
	    ArrayList<String> columnNames = new ArrayList<String>();
	    ArrayList<String> columnTypes = new ArrayList<String>();
	    ArrayList<Integer> columnSizes = new ArrayList<Integer>();
		ArrayList<Integer> columnPrecisions = new ArrayList<Integer>();
		ArrayList<Boolean> columnNullables = new ArrayList<Boolean>();
		ArrayList<String> columnDefaults = new ArrayList<String>();
		String vendorFrom = null;
		for (Iterator<Integer> it = v.iterator(); it.hasNext();) {
			int key = it.next();
			DBObject_Table_Column col = (DBObject_Table_Column)contentMap.get(key);
			if (vendorFrom==null)
				vendorFrom = col.getParent().getVendor();
			columnNames.add(col.getName());
			columnTypes.add(col.getType());
			columnSizes.add(col.getSize());
			columnPrecisions.add(col.getPrecision());
			columnNullables.add(col.isNullable());
			columnDefaults.add(col.getDefault());
			// remember hat this is a new column which was added to target
			col.setNew(true);
		}

		// build SQL command
		String sql = dbEngine.sqlObject_createTable(vendorFrom, db.getVendor(), db.getCatalog(), db.getSchema(), name, columnNames, columnTypes, columnSizes, columnPrecisions, columnNullables, columnDefaults);
		
		// run SQL command
		Statement stmt = db.setStatement();
		if (db.executeUpdate(stmt, sql, false, false) == null)
			success = false;
		db.releaseStatement(stmt);

		return success;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#isCustomMarked(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public boolean isCustomMarked(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap) { 

		boolean result = false;

		if (parent.isObjectExists("AD_TABLE", parent.getTables())) {
			String sql = dbEngine.sqlAD_getTableEntityType(parent.getVendor(), parent.getCatalog(), parent.getSchema(), name);
			Statement stmt = parent.setStatement();
			ResultSet rs = parent.executeQuery(stmt, sql);
			if (parent.getResultSetNext(rs)) {
				String s = parent.getResultSetString(rs, "ENTITY_TYPE");
				result = parent.isCustomEntityType(s);
			}
			parent.releaseResultSet(rs);
			parent.releaseStatement(stmt);
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#isCustomImplied(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	public boolean isCustomImplied(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {
		
		boolean result = false;
		
		// iterate through columns to see if any of them are custom columns
		for (Iterator<Integer> it = contentMap.keySet().iterator(); it.hasNext();) {
			int key = it.next();
			DBObject_Table_Column col = (DBObject_Table_Column)contentMap.get(key);
			if (col.isCustomized())
				result = true;
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#updateObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap, com.kkalice.adempiere.migrate.DBObject)
	 */
	public boolean updateObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap, DBObject sourceObj) {
		boolean success = true;

		// source and target columns
		HashMap<Integer, DBObjectDefinition> targetMap = contentMap;
		HashMap<Integer, DBObjectDefinition> sourceMap = sourceObj.getContents();

		// create lists of column names for quick lookup
		ArrayList<String> targetList = new ArrayList<String>();
		for (Iterator<Integer> it = targetMap.keySet().iterator(); it.hasNext();) {
			Integer key = it.next();
			DBObject_Table_Column col = (DBObject_Table_Column) targetMap.get(key);
			targetList.add(col.getName().toUpperCase());
		}

		ArrayList<String> sourceList = new ArrayList<String>();
		for (Iterator<Integer> it = sourceMap.keySet().iterator(); it.hasNext();) {
			Integer key = it.next();
			DBObject_Table_Column col = (DBObject_Table_Column) sourceMap.get(key);
			sourceList.add(col.getName().toUpperCase());
		}


		// mark columns not existing in source object which are not customized in target for dropping
		ArrayList<DBObject_Table_Column> dropColumns = new ArrayList<DBObject_Table_Column> (); 
		for (Iterator<Integer> it = targetMap.keySet().iterator(); it.hasNext();) {
			Integer key = it.next();
			DBObject_Table_Column col = (DBObject_Table_Column) targetMap.get(key);
			if (! sourceList.contains(col.getName().toUpperCase()))
				if (! col.isCustomized())
					dropColumns.add(col);
		}

		// add columns only existing in source
		// rename columns with same AD_Element_ID
		// synchronize columns existing in both source and target
		// (we sort this to have added columns reflecting their original position in source as much as possible)
		Vector<Integer> v = new Vector<Integer>(sourceMap.keySet());
		java.util.Collections.sort(v);

		for (Iterator<Integer> it = v.iterator(); it.hasNext();) {
			Integer key = it.next();
			DBObject_Table_Column col = (DBObject_Table_Column) sourceMap.get(key);

			// check whether source column exists in target
			ArrayList<DBObject_Table_Column> renamedColumns = new ArrayList<DBObject_Table_Column> (); 
			if (! targetList.contains(col.getName().toUpperCase())) {
				// check whether a column marked to be dropped exists in target with the same element ID
				boolean renamed = false;
				int colAddID = col.getElementID();
				for (Iterator<DBObject_Table_Column> it2 = dropColumns.iterator(); it2.hasNext();) {
					DBObject_Table_Column col2 = it2.next();
					int colDropID = col2.getElementID();
					if (colDropID!=0) {
						if (colDropID == colAddID) {
							// rename (same as synchronize)
							if (! col2.synchronize(col))
								success = false;
							renamedColumns.add(col2);
							renamed = true;
						}
					}
				}

				// Check whether a column marked to be dropped exists in target which has a similar name
				// as the column to add (is the shorter name contained in the longer name?
				// for example ismandatory/ismandatoryui, inout_id/m_inout_id, percent/percentcost, ...)
				// For safety, we also check the data type of both columns
				if (! renamed) {
					for (Iterator<DBObject_Table_Column> it2 = dropColumns.iterator(); it2.hasNext();) {
						DBObject_Table_Column col2 = it2.next();
						String shortColName = col.getName().toUpperCase();
						String longColName = col2.getName().toUpperCase();
						if(col.getName().length() > col2.getName().length()) {
							shortColName = col2.getName().toUpperCase();
							longColName = col.getName().toUpperCase();
						}
						if (longColName.contains(shortColName)) {
							// maybe data type comparison should be more general like just checking numeric/numeric
							// instead of full size and scale?
							String addType = dbEngine.translateDataType(col.getParent().getVendor(), parent.getVendor(), col.getType(), col.getSize(), col.getPrecision());
							String dropType = dbEngine.translateDataType(col2.getParent().getVendor(), parent.getVendor(), col2.getType(), col2.getSize(), col2.getPrecision());
							if (addType!=null && dropType!=null && addType.equalsIgnoreCase(dropType)) {
								// rename (same as synchronize)
								if (! col2.synchronize(col))
									success = false;
								renamedColumns.add(col2);
								renamed = true;
							}
						}
					}
				}

				// remove any renamed columns from column list to drop
				for (Iterator<DBObject_Table_Column> it2 = renamedColumns.iterator(); it2.hasNext();) {
					DBObject_Table_Column col2 = it2.next();
					dropColumns.remove(col2);
				}

				if (!renamed) {
					// add column

					// to avoid being trapped by schizophrenic NOT NULL DEFAULT NULL definitions,
					// we first add the column being nullable. (isNullable=true)
					String sql = dbEngine.sqlObjectDetail_createColumn(col.getParent().getVendor(), 
							parent.getVendor(), parent.getCatalog(), parent.getSchema(), 
							col.getTable(), col.getName(), 
							col.getType(), col.getSize(), col.getPrecision(), true, col.getDefault());
					Statement stmt = parent.setStatement();
					if (parent.executeUpdate(stmt, sql, false, false) == null)
						success = false;
					
					// then, if the column is not allowed to be NULL, we prepare it by replacing NULL entries
					// with sensible default values and finally set the NOT NULL constraint
					if (col.isNotNullable()) {
						// ensure that there are no columns containing null
						String dataType = dbEngine.translateDataType(col.getParent().getVendor(), parent.getVendor(), col.getType(), col.getSize(), col.getPrecision());
						sql = dbEngine.sqlObjectDetail_prepareColumnNotNullable(parent.getVendor(), parent.getCatalog(), parent.getSchema(), col.getTable(), col.getName(), dataType, col.getDefault());
						if (parent.executeUpdate(stmt, sql, false, false) == null)
							success = false;
						// make column not nullable
						sql = dbEngine.sqlObjectDetail_dropColumnNullable(parent.getVendor(), parent.getCatalog(), parent.getSchema(), col.getTable(), col.getName());
						if (parent.executeUpdate(stmt, sql, false, false) == null)
							success = false;
					}
					parent.releaseStatement(stmt);
					// remember that this is a new column which was added to target
					col.setNew(true);
				}
			} else {
				// synchronize
				for (Iterator<Integer> it2 = targetMap.keySet().iterator(); it2.hasNext();) {
					Integer key2 = it2.next();
					DBObject_Table_Column col2 = (DBObject_Table_Column) targetMap.get(key2);
					if (col.getName().equalsIgnoreCase(col2.getName()) && ! col2.isCustomized())
						if (!col2.synchronize(col))
							success = false;
				}
			}
		}

		// drop columns
		for (Iterator<DBObject_Table_Column> it = dropColumns.iterator(); it.hasNext();) {
			DBObject_Table_Column col = it.next();
			if (! col.drop())
				success = false;
		}

		return success;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getDependencies(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	public ArrayList<String> getDependencies(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {
		// table dependencies have been invalidated by dropping all constraints
		ArrayList<String> list = new ArrayList<String>();
		list.add(name.toUpperCase());
		return list;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadContentSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadContentSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_tableColumns(vendor, catalog, schema);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadHeaderSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadHeaderSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		// table has no headers
		return null;
	}
	
}
