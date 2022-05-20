/*
 * Name:		DBObject_Operator.java
 * Description:	operator object in a database
 * Created:		May 8, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Operator.java
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
 * operator object in a database 
 * @author Stefan Christians
 */
public class DBObject_Operator implements DBObjectInterface {

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#createObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	public boolean createObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection db, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {
		boolean success = true;

		DBObject_Operator_Signature header = (DBObject_Operator_Signature)headerMap.get(0);
		DBObject_Operator_Definition content = (DBObject_Operator_Definition)contentMap.get(0);
		
		String vendorFrom = header.getParent().getVendor();
		String vendorTo = db.getVendor();
		String catalog = db.getCatalog();
		String schema = db.getSchema();
		
		String operatorName = header.getName();
		String leftArg = header.getLeftArg();
		String rightArg = header.getRightArg();
		String returnType = header.getReturnType();
		
		String functionName = content.getFunctionName();
		String commutator = content.getCommutator();
		String negator = content.getNegator();
		String restrictor = content.getRestrictor();
		String joiner = content.getJoiner();
		boolean isHashable = content.isHashable();
		boolean isMergeable = content.isMergeable();
		
		String sql = dbEngine.sqlObject_createOperator(vendorFrom, vendorTo, catalog, schema, operatorName, leftArg, rightArg, returnType, functionName, commutator, negator, restrictor, joiner, isHashable, isMergeable);
		
		Statement stmt = db.setStatement();
		if (db.executeUpdate(stmt, sql, false, false) == null)
			success = false;
		db.releaseStatement(stmt);
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#dropObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public boolean dropObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap) {
		boolean success = true;
		
		DBObject_Operator_Signature header = (DBObject_Operator_Signature)headerMap.get(0);
		String vendor = parent.getVendor();
		String catalog = parent.getCatalog();
		String schema = parent.getSchema();
		String operatorName = header.getName();
		String leftArg = header.getLeftArg();
		String rightArg = header.getRightArg();
		
		String sql = dbEngine.sqlObject_dropOperator(vendor, catalog, schema, operatorName, leftArg, rightArg);

		Statement stmt = parent.setStatement();
		if (parent.executeUpdate(stmt, sql, false, false) == null)
			success = false;
		parent.releaseStatement(stmt);
		
		return success;
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
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getDependencies(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	public ArrayList<String> getDependencies(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {
		// operators only have functions as dependencies, which should have already been created
		ArrayList<String> list = new ArrayList<String>();
		list.add(name.toUpperCase());
		return list;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getHeaderType()
	 */
	public String getHeaderType() {
		return "signature";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getHeaderTypes()
	 */
	public String getHeaderTypes() {
		return "signatures";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadObjectSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadObjectSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_operatorNames(vendor, catalog, schema);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getObjectType()
	 */
	public String getObjectType() {
		return "operator";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getObjectTypes()
	 */
	public String getObjectTypes() {
		return "operators";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#isCustomImplied(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	@SuppressWarnings("static-access")
	public boolean isCustomImplied(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {
		// operators are customized if the function they call is customized
		boolean result = false;

		DBObject_Operator_Signature header = (DBObject_Operator_Signature)headerMap.get(0);
		DBObject_Operator_Definition content = (DBObject_Operator_Definition)contentMap.get(0);
		
		String functionName = new StringBuffer(content.getFunctionName()).append(" ").append(header.getSignature()).toString();
		
		DBObject func = parent.getObjectByName(functionName, parent.getFunctions()); 
		if (func!=null) {
			if(func.getCustomizationLevel()>parameters.CUSTOMNONE)
				result = true;
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#isCustomMarked(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public boolean isCustomMarked(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap) {
		// operators are not managed by application dictionary
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#loadContents(java.util.HashMap, com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public void loadContents(HashMap<Integer, DBObjectDefinition> contentMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, PreparedStatementWrapper statement) {
		
		parent.setPreparedStatementString(statement, 1, name);
		ResultSet rs = parent.executeQuery(statement);
		int counter = 0;
		while (parent.getResultSetNext(rs)) {

			String operatorName = parent.getResultSetString(rs, "OPERATOR_NAME");
			String functionName =parent.getResultSetString(rs, "FUNCTION_NAME");
			String commutator = parent.getResultSetString(rs, "OP_COMMUTATOR");
			String negator = parent.getResultSetString(rs, "OP_NEGATOR");
			String restrictor = parent.getResultSetString(rs, "OP_RESTRICT");
			String joiner = parent.getResultSetString(rs, "OP_JOIN");
			boolean hashable = parent.getResultSetBoolean(rs, "OP_HASHABLE");
			boolean mergeable = parent.getResultSetBoolean(rs, "OP_MERGEABLE");

			// only load operators which have a function name defined
			if (functionName!=null && functionName.length()>0) {

				// remove schema and quotes from function name
				if (functionName.contains("."))
					functionName = functionName.substring(functionName.lastIndexOf(".")+1);
				functionName = functionName.replaceAll("\"", "");

				DBObject_Operator_Definition obj = new DBObject_Operator_Definition (parent, operatorName, counter);
				obj.initializeDefinition(functionName, commutator, negator, restrictor, joiner, hashable, mergeable);
				contentMap.put(Integer.valueOf(counter), obj);

				counter++;
			}

		}
		parent.releaseResultSet(rs);
	}
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#loadHeaders(java.util.HashMap, com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String)
	 */
	public void loadHeaders(HashMap<Integer, DBObjectDefinition> headerMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, PreparedStatementWrapper statement) {
		
		parent.setPreparedStatementString(statement, 1, name);
		ResultSet rs = parent.executeQuery(statement);
		int counter = 0;
		while (parent.getResultSetNext(rs)) {

			String operatorName = parent.getResultSetString(rs, "OPERATOR_NAME");
			String leftArg = parent.getResultSetString(rs, "LEFT_ARG");
			String rightArg = parent.getResultSetString(rs, "RIGHT_ARG");
			String returnType = parent.getResultSetString(rs, "RETURN_TYPE");

			DBObject_Operator_Signature obj = new DBObject_Operator_Signature (parent, operatorName, counter);
			obj.initializeDefinition(leftArg, rightArg, returnType);
			headerMap.put(Integer.valueOf(counter), obj);

			counter++;
		}
		parent.releaseResultSet(rs);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#updateObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap, com.kkalice.adempiere.migrate.DBObject)
	 */
	public boolean updateObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap, DBObject sourceObj) {
		// operators can be dropped or re-created, not updated
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadContentSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadContentSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_operatorDefinitions(vendor, catalog, schema);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadHeaderSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadHeaderSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_operatorSignatures(vendor, catalog, schema);
	}

	
}
