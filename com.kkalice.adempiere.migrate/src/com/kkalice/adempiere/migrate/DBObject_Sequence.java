/*
 * Name:		DBObject_Sequence.java
 * Description:	sequence object in a database
 * Created:		Feb 14, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Sequence.java
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

/**
 * sequence object in a database
 * @author Stefan Christians
 */
public class DBObject_Sequence implements DBObjectInterface {

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getObjectType()
	 */
	public String getObjectType() {
		return "sequence";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getObjectTypes()
	 */
	public String getObjectTypes() {
		return "sequences";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getHeaderType()
	 */
	public String getHeaderType() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getHeaderTypes()
	 */
	public String getHeaderTypes() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getContentType()
	 */
	public String getContentType() {
		return "counter";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getContentTypes()
	 */
	public String getContentTypes() {
		return "counters";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadObjectSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadObjectSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_sequenceNames(vendor, catalog, schema);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#loadHeaders(java.util.HashMap, com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public void loadHeaders(HashMap<Integer, DBObjectDefinition> headerMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, PreparedStatementWrapper statement) {
		// sequences do not have headers
		return;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#loadContents(java.util.HashMap, com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String)
	 */
	public void loadContents(HashMap<Integer, DBObjectDefinition> contentMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, PreparedStatementWrapper statement) {

		String sql = dbEngine.sqlMetadata_sequenceDefinitions( parent.getVendor(), parent.getProductVersion() , parent.getCatalog(), parent.getSchema(), name);
		Statement stmt = parent.setStatement();
		ResultSet rs = parent.executeQuery(stmt, sql);
		int counter=0;
		while (parent.getResultSetNext(rs)) {

			String minValue = parent.getResultSetString(rs, "MIN_VALUE");
			String maxValue = parent.getResultSetString(rs, "MAX_VALUE");
			String increment = parent.getResultSetString(rs, "INCREMENT_BY");
			boolean isCycled = dbEngine.isTrue(parent.getResultSetString(rs, "IS_CYCLED"));
			String cacheSize = parent.getResultSetString(rs, "CACHE_SIZE");
			String lastValue = parent.getResultSetString(rs, "LAST_VALUE");
			DBObject_Sequence_Counter obj = new DBObject_Sequence_Counter(parent, name, counter);
			obj.initializeDefinition(minValue, maxValue, increment, isCycled, cacheSize, lastValue);
			contentMap.put(Integer.valueOf(counter), obj);

			counter++;
		}
		parent.releaseResultSet(rs);
		parent.releaseStatement(stmt);

	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#dropObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public boolean dropObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap) {

		boolean success = true;

		String sql = dbEngine.sqlObject_dropSequence(parent.getVendor(), parent.getCatalog(), parent.getSchema(), name);

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

		boolean success = true;

		DBObject_Sequence_Counter seq = (DBObject_Sequence_Counter)contentMap.get(0);  

		long min = seq.getMinimum();
		long max = seq.getMaximum();
		long incr = seq.getIncrement();
		boolean isCycled = seq.isCycled();
		long cache = seq.getCached();
		long start = seq.getCurrent();

		String sql = dbEngine.sqlObject_createSequence(db.getVendor(), db.getCatalog(), db.getSchema(), name, min, max, incr, isCycled, cache, start);

		Statement stmt = db.setStatement();
		if (db.executeUpdate(stmt, sql, false, false) == null)
			success = false;
		db.releaseStatement(stmt);

		return success;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#updateObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap, com.kkalice.adempiere.migrate.DBObject)
	 */
	public boolean updateObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap, DBObject sourceObj) {
		
		boolean success = true;

		// direction, cycle, and cache are determined by source
		// Note that unique sequence numbers are not guaranteed if source and target
		// have a different direction (positive or negative increment).
		// In that case, it is better to use a new sequence with a different name.
		DBObject_Sequence_Counter targetSeq = (DBObject_Sequence_Counter)contentMap.get(0);
		String targetName = name;
		DBObject_Sequence_Counter sourceSeq = (DBObject_Sequence_Counter)sourceObj.getContents().get(0);
		String sourceName = sourceSeq.getName();
		long incr = sourceSeq.getIncrement();
		boolean isCycled = sourceSeq.isCycled();
		long cache = sourceSeq.getCached();
		// retain sensible max and min values from either source or target
		long min = java.lang.Math.min(targetSeq.getMinimum(), sourceSeq.getMinimum());
		long max = java.lang.Math.max(targetSeq.getMaximum(), sourceSeq.getMaximum());
		long start = java.lang.Math.max(targetSeq.getCurrent(), sourceSeq.getCurrent());
		if (incr<0)
			start = java.lang.Math.min(targetSeq.getCurrent(), sourceSeq.getCurrent());
		
		// oracle does not allow to restart a sequence at a different number, so instead
		// of using "ALTER SEQUENCE...RESTART" we first drop and then re-create it.
		String sql = dbEngine.sqlObject_dropSequence(parent.getVendor(), parent.getCatalog(), parent.getSchema(), targetName);

		Statement stmt = parent.setStatement();
		if (parent.executeUpdate(stmt, sql, false, false) == null)
			success = false;
		else {
			sql = dbEngine.sqlObject_createSequence(parent.getVendor(), parent.getCatalog(), parent.getSchema(), sourceName, min, max, incr, isCycled, cache, start);

			if (parent.executeUpdate(stmt, sql, false, false) == null)
				success = false;
		}
		parent.releaseStatement(stmt);

		return success;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#isCustomImplied(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	public boolean isCustomImplied(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {
		// sequences can not contain customized objects
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#isCustomMarked(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public boolean isCustomMarked(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap) {
		// sequences are not maintained in application dictionary
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getDependencies(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	public ArrayList<String> getDependencies(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {
		// sequences have no dependencies
		ArrayList<String> list = new ArrayList<String>();
		list.add(name.toUpperCase());
		return list;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadContentSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadContentSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		// in some databases sequences are actually tables, so we can not use a prepared statement
		// (because table names can not be parameters for prepared statements)
		return null;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadHeaderSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadHeaderSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		// sequences do not have headers
		return null;
	}

}
