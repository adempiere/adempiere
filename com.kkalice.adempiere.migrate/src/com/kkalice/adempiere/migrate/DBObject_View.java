/*
 * Name:		DBObject_View.java
 * Description:	view object in a database
 * Created:		Feb 14, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_View.java
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
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * view object in a database
 * @author Stefan Christians
 */
public class DBObject_View implements DBObjectInterface {

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getObjectType()
	 */
	public String getObjectType() {
		return "view";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getObjectTypes()
	 */
	public String getObjectTypes() {
		return "views";
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
		return "definition";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getContentTypes()
	 */
	public String getContentTypes() {
		return "definitions";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadObjectSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadObjectSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_viewNames(vendor, catalog, schema);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#loadHeaders(java.util.HashMap, com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String)
	 */
	public void loadHeaders(HashMap<Integer, DBObjectDefinition> headerMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, PreparedStatementWrapper statement) {
		// view has no headers
		return;
	}
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#loadContents(java.util.HashMap, com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String)
	 */
	public void loadContents(HashMap<Integer, DBObjectDefinition> contentMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, PreparedStatementWrapper statement) {

		parent.setPreparedStatementString(statement, 1, name);
		ResultSet rs = parent.executeQuery(statement);
		int counter = 0;
		while (parent.getResultSetNext(rs)) {

			String viewName = parent.getResultSetString(rs, "VIEW_NAME");
			String viewDefinition = parent.getResultSetString(rs, "VIEW_DEFINITION");

			DBObject_View_Definition obj = new DBObject_View_Definition (parent, viewName, counter);
			obj.initializeDefinition(viewDefinition);
			contentMap.put(new Integer(counter), obj);

			counter++;
		}
		parent.releaseResultSet(rs);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#dropObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public boolean dropObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap) {

		boolean success = true;

		String viewName = name;
		String sql = dbEngine.sqlObject_dropView(parent.getVendor(), parent.getCatalog(), parent.getSchema(), viewName);

		Statement stmt = parent.setStatement();
		if (parent.executeUpdate(stmt, sql, false, false) == null)
			success = false;
		parent.releaseStatement(stmt);

		return success;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#createObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	@SuppressWarnings("static-access")
	public boolean createObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection db, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {
		boolean success = true;

		// find source db vendor from first entry in content map
		DBObject_View_Definition content = (DBObject_View_Definition)contentMap.get(0);
		String sourceVendor = content.getParent().getVendor();
		String sourceSchema = content.getParent().getSchema();

		// definition in contents
		// concatenate all lines into a single string
		Vector<Integer> v = new Vector<Integer>(contentMap.keySet());
		java.util.Collections.sort(v);
		StringBuffer sqlContent = new StringBuffer();
		for (Iterator<Integer> it = v.iterator(); it.hasNext();) {
			int key = it.next();
			content = (DBObject_View_Definition)contentMap.get(key);
			if (sqlContent.length()>0)
				sqlContent.append(System.getProperty("line.separator"));
			sqlContent.append(content.getDefinition());
		}

		String sql = dbEngine.sqlObject_createView(sourceVendor, sourceSchema, db.getVendor(), db.getCatalog(), db.getSchema(), name, sqlContent.toString(), false);

		// create the view
		Statement stmt = db.setStatement();
		if (db.executeUpdateSilent(stmt, sql, true) == null)
			success = false;

		// if creation failed, try again as commented-out stub to make fixing easier for the DBA
		if (! success) {
			String lastError = db.getLastSilentError();
			// try again to create the view
			if (parameters.isAttemptTranslation() || sourceVendor.equalsIgnoreCase(db.getVendor())) {
				success = true;
				logger.log(Level.FINER, "failedToCreateRetrying", new Object[] {getObjectType(), name});
				sql = dbEngine.sqlObject_createView(sourceVendor, null, db.getVendor(), db.getCatalog(), db.getSchema(), name, sqlContent.toString(), true);
				if (db.executeUpdate(stmt, sql, true, false) == null)
					success = false;
				logger.log(Level.WARNING, "mustRewrite", new Object[] {getObjectType(), name, lastError});
			} 
		}

		db.releaseStatement(stmt);
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#isCustomImplied(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	public boolean isCustomImplied(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {
		boolean result = false;
		
		if (parent.isObjectExists("AD_COLUMN", parent.getTables()) && parent.isObjectExists("AD_TABLE", parent.getTables())) {
				String sql = dbEngine.sqlAD_getViewColumnEntityType(parent.getVendor(), parent.getCatalog(), parent.getSchema(), name);
				Statement stmt = parent.setStatement();
				ResultSet rs = parent.executeQuery(stmt, sql);
				while (parent.getResultSetNext(rs)) {
					String s = parent.getResultSetString(rs, "ENTITY_TYPE");
					if (parent.isCustomEntityType(s))
						result = true;
				}
				parent.releaseResultSet(rs);
				parent.releaseStatement(stmt);
		}
		
		return result;
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
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#updateObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap, com.kkalice.adempiere.migrate.DBObject)
	 */
	public boolean updateObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap, DBObject sourceObj) {
		// views can be dropped or re-created, not updated
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getDependencies(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	public ArrayList<String> getDependencies(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {
		// views depend on subviews
		ArrayList<String> list = new ArrayList<String>();
		list.add(name.toUpperCase());

		DBObject_View_Definition definition = (DBObject_View_Definition)contentMap.get(0);
		String text = definition.getDefinition();

		// remove all parenthesis because they just disturb our parsing
		// (replace with space)
		text = text.replaceAll("\\(", " ").replaceAll("\\)", " ");
		
		// remove schema qualifiers as well
		String schemaName = parent.getSchema();
		text = text.replaceAll(new StringBuffer("(?i)").append(schemaName).append("\\.").toString(), "");
		
		// the word following FROM or JOIN is the underlying table or view name
		Pattern pattern = Pattern.compile("\\s(FROM|JOIN)\\s+?(\\w+)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			String subview = matcher.group(2);
			if (subview!=null) {
				subview = subview.toUpperCase();
				if (! list.contains(subview))
					list.add(subview);
			}
		}
		
		return list;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadContentSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadContentSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_viewDefinitions(vendor, catalog, schema);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadHeaderSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadHeaderSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		// view has no headers
		return null;
	}

}
