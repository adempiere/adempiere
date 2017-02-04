/*
 * Name:		DBObject_Function.java
 * Description:	function object in a database
 * Created:		Feb 14, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBObject_Function.java
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
import java.util.logging.Level;

/**
 * function object in a database
 * @author Stefan Christians
 */
public class DBObject_Function implements DBObjectInterface {

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getObjectType()
	 */
	public String getObjectType() {
		return "function";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getObjectTypes()
	 */
	public String getObjectTypes() {
		return "functions";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getHeaderType()
	 */
	public String getHeaderType() {
		return "argument";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getHeaderTypes()
	 */
	public String getHeaderTypes() {
		return "arguments";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getContentType()
	 */
	public String getContentType() {
		return "body";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getContentTypes()
	 */
	public String getContentTypes() {
		return "lines";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadObjectSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadObjectSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_functionNames(vendor, catalog, schema);
	}
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#loadHeaders(java.util.HashMap, com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String)
	 */
	public void loadHeaders(HashMap<Integer, DBObjectDefinition> headerMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, PreparedStatementWrapper statement) {

		parent.setPreparedStatementString(statement, 1, name);
		ResultSet rs = parent.executeQuery(statement);
		while (parent.getResultSetNext(rs)) {

			String funcName = parent.getResultSetString(rs, "FUNC_NAME");
			String funcType = parent.getResultSetString(rs, "FUNC_TYPE");
			String funcLang = parent.getResultSetString(rs, "FUNC_LANG");
			String retType = parent.getResultSetString(rs, "RET_TYPE");
			int seqNum = parent.getResultSetInt(rs, "SEQ_NUM");
			String argDir = parent.getResultSetString(rs, "ARG_DIR");
			String argName = parent.getResultSetString(rs, "ARG_NAME");
			String argType = parent.getResultSetString(rs, "ARG_TYPE");

			DBObject_Function_Argument obj = new DBObject_Function_Argument(parent, argName, seqNum);
			obj.initializeDefinition(funcName, funcLang, funcType, retType, argDir, argType);
			headerMap.put(new Integer(seqNum), obj);

		}
		parent.releaseResultSet(rs);
		
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#loadContents(java.util.HashMap, com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String)
	 */
	public void loadContents(HashMap<Integer, DBObjectDefinition> contentMap, Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, PreparedStatementWrapper statement) {

		// in some datatbases we need the function name more than once in the query
		int nParam = parent.getPreparedStatementParameterCount(statement);
		for (int i=1; i<=nParam; i++) {
			parent.setPreparedStatementString(statement, i, name);
		}

		ResultSet rs = parent.executeQuery(statement);
		while (parent.getResultSetNext(rs)) {

			String funcName = parent.getResultSetString(rs, "FUNC_NAME");
			int seqNum = parent.getResultSetInt(rs, "SEQ_NUM");
			String funcDef = parent.getResultSetString(rs, "FUNC_DEF");

			if (funcDef==null)
				funcDef="";

			DBObject_Function_Body obj = new DBObject_Function_Body(parent, funcName, seqNum);
			obj.initializeDefinition (funcDef);
			contentMap.put(new Integer(seqNum), obj);

		}
		parent.releaseResultSet(rs);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#dropObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public boolean dropObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap) {

		boolean success = true;
		
		// get function signature to distinguish overloaded functions
		String functionName = getFunctionName(headerMap);
		String functionSignature = getFunctionSignature(headerMap);
		
		// get function type and return type to distinguish between FUNCTION and PROCEDURE
		String functionType = getFunctionType(headerMap);
		String functionReturnType = getFunctionReturnType(headerMap);

		// drop function
		String sql = dbEngine.sqlObject_dropFunction(parent.getVendor(), parent.getCatalog(), parent.getSchema(), functionType, functionName, functionReturnType, functionSignature);

		Statement stmt = parent.setStatement();
		if (parent.executeUpdate(stmt, sql, false, false) == null)
			success = false;
		parent.releaseStatement(stmt);

		return success;
	}
	
	/**
	 * get the function's header information (first record of header map)
	 * @param headerMap map containing headers for this object
	 * @return header information for this function
	 */
	private DBObject_Function_Argument getFunctionHeader (HashMap<Integer, DBObjectDefinition> headerMap) {

		// sort function arguments by sequence number
		Vector<Integer> v = new Vector<Integer>(headerMap.keySet());
	    java.util.Collections.sort(v);

	    Integer key = v.firstElement();
	    return (DBObject_Function_Argument) headerMap.get(key);
	}

	/**
	 * get the function type
	 * @param headerMap map containing headers for this object
	 * @return type of the function
	 */
	private String getFunctionType (HashMap<Integer, DBObjectDefinition> headerMap) {
	    return getFunctionHeader(headerMap).getFunctionType();
	}

	/**
	 * get the function name
	 * @param headerMap map containing headers for this object
	 * @return name of the function
	 */
	private String getFunctionName (HashMap<Integer, DBObjectDefinition> headerMap) {
	    return getFunctionHeader(headerMap).getFunctionName();
	}
	
	/**
	 * get the function signature (name with arguments)
	 * @param headerMap map containing headers for this object
	 * @return the function signature
	 */
	private String getFunctionSignature (HashMap<Integer, DBObjectDefinition> headerMap) {

		String functionName = getFunctionName(headerMap);
		StringBuffer functionSignature = new StringBuffer(functionName).append("(");

		// sort function arguments by sequence number
		Vector<Integer> v = new Vector<Integer>(headerMap.keySet());
	    java.util.Collections.sort(v);

	    int argCounter=0;
		for (Iterator<Integer> it = v.iterator(); it.hasNext();) {
			int key = it.next();
			DBObject_Function_Argument header = (DBObject_Function_Argument)headerMap.get(key);
			StringBuffer argument = new StringBuffer();
			String argDir = header.getArgumentDirection();
			if (argDir!=null && argDir.length()>0)
				argument.append(argDir).append(" ");
			String argName = header.getName();
			if (argName!=null && argName.length()>0)
				argument.append(argName).append(" ");
			String argType =  header.getArgumentType();
			if (argType!=null && argType.length()>0)
				argument.append(argType);
			if (argCounter==0)
				functionSignature.append(argument);
			else
				functionSignature.append(", ").append(argument);
			argCounter++;
		}
		functionSignature.append(")");
		
		return functionSignature.toString();
	}
	
	/**
	 * get the function's return type
	 * @param headerMap map containing headers for this object
	 * @return return type of the function
	 */
	private String getFunctionReturnType (HashMap<Integer, DBObjectDefinition> headerMap) {
	    return getFunctionHeader(headerMap).getReturnType();
	}
	
	/**
	 * get the function's language
	 * @param headerMap map containing headers for this object
	 * @return language of the function
	 */
	private String getFunctionLanguage (HashMap<Integer, DBObjectDefinition> headerMap) {
	    return getFunctionHeader(headerMap).getFunctionLanguage();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#createObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	@SuppressWarnings("static-access")
	public boolean createObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection db, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {

		boolean success = true;

		// prefix
		String vendorFrom = getFunctionHeader(headerMap).getParent().getVendor();
		String schemaFrom = getFunctionHeader(headerMap).getParent().getSchema();
		String vendorTo = db.getVendor();
		String catalog = db.getCatalog();
		String schema = db.getSchema();
		String functionType = getFunctionType(headerMap);
		String functionName = getFunctionName(headerMap);
		String functionReturnType =getFunctionReturnType(headerMap);
		String functionLanguage = getFunctionLanguage(headerMap);
		
		// argument list in header
		ArrayList<String> argDirs = new ArrayList<String>();
		ArrayList<String> argNames = new ArrayList<String>();
		ArrayList<String> argTypes = new ArrayList<String>();
		boolean hasOutParameters = false;

		Vector<Integer> v = new Vector<Integer>(headerMap.keySet());
		java.util.Collections.sort(v);
		int counter = 0;
		for (Iterator<Integer> it = v.iterator(); it.hasNext();) {
			int key = it.next();
			DBObject_Function_Argument header = (DBObject_Function_Argument)headerMap.get(key);
			String argDir = header.getArgumentDirection();
			String argName = header.getName();
			String argType = header.getArgumentType();

			// argName, argType, and argDir null means function has no parameters
			if (! (argName==null && argType==null && argDir==null)) {

				// argDir OUT and argName null means this is the return type
				if (! (argDir.equalsIgnoreCase("OUT") && argName==null)) {

					counter++;
					
					// otherwise argName null means parameter is unnamed
					if (argName==null)
						argName= dbEngine.translateUnnamedParameter(vendorTo , counter);

					argDirs.add(argDir);
					argNames.add(argName);
					argTypes.add(argType);

					// remember whether this function uses output parameters
					if (argDir.toUpperCase().contains("OUT"))
						hasOutParameters = true;
				}
			}
		}

		// lines of code as body in contents
		// (concatenate all lines into a single string)
		v = new Vector<Integer>(contentMap.keySet());
		java.util.Collections.sort(v);
		StringBuffer sb = new StringBuffer();
		for (Iterator<Integer> it = v.iterator(); it.hasNext();) {
			if (sb!=null && sb.length()>0)
				sb.append(System.getProperty("line.separator"));
			int key = it.next();
			DBObject_Function_Body func = (DBObject_Function_Body)contentMap.get(key);
			sb.append(func.getDefinition());
		}
		String bodyText = sb.toString();

		String sql = dbEngine.sqlObject_createFunction(vendorFrom, schemaFrom, vendorTo, catalog, schema, functionType, functionName, functionReturnType, hasOutParameters, functionLanguage, argDirs, argNames, argTypes, bodyText, false);

		// create the function
		Statement stmt = db.setStatement();
		if (db.executeUpdateSilent(stmt, sql, true) == null)
			success = false;

		// if creation failed, try again as commented-out stub to make fixing easier for the DBA
		if (! success) {
			String lastError = db.getLastSilentError();
			// try again to create the function 
			if (parameters.isAttemptTranslation() || vendorFrom.equalsIgnoreCase(vendorTo)) {
				success = true;
				logger.log(Level.FINER, "failedToCreateRetrying", new Object[] {getObjectType(), name});
				sql = dbEngine.sqlObject_createFunction(vendorFrom, null, vendorTo, catalog, schema, functionType, functionName, functionReturnType, hasOutParameters, functionLanguage, argDirs, argNames, argTypes, bodyText, true);
				if (db.executeUpdate(stmt, sql, true, false) == null)
					success = false;
				logger.log(Level.WARNING, "mustRewrite", new Object[] {getObjectType(), name, lastError});
			}
		}
		
		db.releaseStatement(stmt);

		return success;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#updateObject(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap, com.kkalice.adempiere.migrate.DBObject)
	 */
	public boolean updateObject(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap, DBObject sourceObj) {
		// functions can be either dropped or created, not updated
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#isCustomImplied(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	public boolean isCustomImplied(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {
		// functions can not contain customized objects
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#isCustomMarked(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap)
	 */
	public boolean isCustomMarked(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap) {
		// functions are not maintained in application dictionary
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getDependencies(com.kkalice.adempiere.migrate.MigrateLogger, com.kkalice.adempiere.migrate.MigrateDBEngine, com.kkalice.adempiere.migrate.DBConnection, java.lang.String, java.util.HashMap, java.util.HashMap)
	 */
	public ArrayList<String> getDependencies(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, DBConnection parent, String name, HashMap<Integer, DBObjectDefinition> headerMap, HashMap<Integer, DBObjectDefinition> contentMap) {
		// currently we have no way of determining what dependencies functions have
		// up to now it worked to install functions without first looking for dependencies
		ArrayList<String> list = new ArrayList<String>();
		list.add(name.toUpperCase());
		return list;	
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadContentSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadContentSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_functionBodies(vendor, catalog, schema);
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBObjectInterface#getLoadHeaderSQL(com.kkalice.adempiere.migrate.MigrateDBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getLoadHeaderSQL(Parameters parameters, MigrateLogger logger, DBEngine dbEngine, String vendor, String catalog, String schema) {
		return dbEngine.sqlMetadata_functionArguments(vendor, catalog, schema);
	}

}
