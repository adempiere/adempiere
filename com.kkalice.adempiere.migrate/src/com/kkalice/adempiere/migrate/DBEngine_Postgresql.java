/*
 * Name:		DBEngineInterface_Postgresql.java
 * Description:	PostgreSQL compatibility class
 * Created:		Nov 29, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBEngineInterface_Postgresql.java
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PostgreSQL compatibility class
 * @author Stefan Christians
 */
public class DBEngine_Postgresql implements DBEngineInterface {

	// database identification
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getVendorNames()
	 */
	public List<String> getVendorNames () {
		return Arrays.asList("PostgreSQL", "Postgres", "pgsql", "pg");
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getSystemCatalogs()
	 */
	public List<String> getSystemCatalogs() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getSystemSchemas()
	 */
	public List<String> getSystemSchemas() {
		return Arrays.asList("information_schema", "pg_catalog", "pg_toast_temp_1");
	}


	// database access
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDriver(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine)
	 */
	public String getDBDriver () {
		return "org.postgresql.Driver";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDBPort(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String)
	 */
	public String getDBPort () {
		return "5432";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDBName(java.lang.String)
	 */
	public String getDBName(String vendorName) {
		// there is usually no default name for postgres
		return null;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#isSourceAndTargetSameDBName()
	 */
	public boolean isSourceAndTargetSameDBName() {
		// in postgres, reference and target are in separate databases
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDBUrl(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getDBUrl (String host, String port, String name) {
		StringBuffer url = new StringBuffer();
		if (host==null || host.length()==0)
			host="";
		if (port==null || port.length()==0 || port.equalsIgnoreCase("5432"))
			port="";
		url.append("jdbc:postgresql:");
		if (host.length()>0) {
			url.append("//").append(host);
			if (port.length()>0)
				url.append(":").append(port);
			url.append("/");
		}
		if (name!=null)
			url.append(name);
		return url.toString();
	}
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDBDefaultSystemUser()
	 */
	public String getDBSystemUser() {
		return "postgres";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDBDefaultSystemPassword()
	 */
	public String getDBSystemPassword() {
		return "postgres";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDBSchemaOrUser(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String getDBSchemaOrUser (String schemaName, String user) {
		// in postgresql, schema can be any name
		return schemaName;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getSystemOrNormalUser(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String getDBSystemOrNormalUser (String normalUser, String systemUser) {
		// in postgresql, normal user has sufficient control for privileged operations
		return normalUser;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getSystemOrNormalUser(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String getDBSystemOrNormalPassword (String normalPasswd, String systemPasswd) {
		// in postgresql, normal user has sufficient control for privileged operations
		return normalPasswd;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDBMaxIdentifierLength()
	 */
	public int getDBMaxIdentifierLength() {
		// maximum length of identifiers is 63 characters
		return 63;
	}


	// data type translation
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#disambiguateDataType(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String)
	 */
	public String disambiguateDataType (String dataType) {
		// no disambigutaion necessary in postgresql
		return dataType;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDataType(int, int, int)
	 */
	public String getDataType (int dataTypeID, int size, int scale) {
		String result = null;

		switch (dataTypeID) {
		case DBEngine.SMALLINT:
		case DBEngine.INT2:
			result = "SMALLINT";
			break;
		case DBEngine.INTEGER:
		case DBEngine.INT4:
		case DBEngine.INT:
		case DBEngine.PLS_INTEGER:
		case DBEngine.BINARY_INTEGER:
		case DBEngine.NATURAL:
		case DBEngine.POSITIVE:
		case DBEngine.SIMPLE_INTEGER:
		case DBEngine.NATURALN:
		case DBEngine.POSITIVEN:
		case DBEngine.SIGNTYPE:
			result = "INTEGER";
			break;
		case DBEngine.BIGINT:
		case DBEngine.INT8:
			result = "BIGINT";
			break;
		case DBEngine.NUMERIC:
		case DBEngine.DECIMAL:
		case DBEngine.DEC:
		case DBEngine.NUMBER:
			result = "NUMERIC";
			if (size!=0) {
				result = new StringBuffer("NUMERIC (").append(size).append(")").toString();
				if (scale != -1 && scale !=0)
					result = new StringBuffer("NUMERIC (").append(size).append(",").append(scale).append(")").toString();
				if (scale == -1)
					result = "NUMERIC";
			}
			break;
		case DBEngine.REAL:
		case DBEngine.FLOAT4:
		case DBEngine.BINARY_FLOAT:
			result = "FLOAT4";
			break;
		case DBEngine.DOUBLE_PRECISION:
		case DBEngine.FLOAT8:
		case DBEngine.BINARY_DOUBLE:
		case DBEngine.FLOAT:
			result = "FLOAT8";
			break;
		case DBEngine.SERIAL:
		case DBEngine.SERIAL4:
			result = "SERIAL";
			break;
		case DBEngine.BIGSERIAL:
		case DBEngine.SERIAL8:
			result = "BIGSERIAL";
			break;
		case DBEngine.MONEY:
			result = "MONEY";
			break;
		case DBEngine.CHAR:
		case DBEngine.CHARACTER:
		case DBEngine.NCHAR:
			if (size!=0)
				result = new StringBuffer("CHAR (").append(size).append(")").toString();
			else
				result = "CHAR";
			break;
		case DBEngine.NAME:
			result = "NAME";
			break;
		case DBEngine.VARCHAR:
		case DBEngine.CHARACTER_VARYING:
		case DBEngine.CHAR_VARYING:
		case DBEngine.VARCHAR2:
		case DBEngine.NVARCHAR:
		case DBEngine.NCHAR_VARYING:
		case DBEngine.NATIONAL_CHAR_VARYING:
		case DBEngine.NATIONAL_CHARACTER_VARYING:
		case DBEngine.NVARCHAR2:
		case DBEngine.STRING:
			if (size!=0)
				result = new StringBuffer("VARCHAR (").append(size).append(")").toString();
			else
				result = "VARCHAR";
			break;
		case DBEngine.LONG:
		case DBEngine.TEXT:
		case DBEngine.CLOB:
		case DBEngine.NCLOB:
			result = "TEXT";
			break;
		case DBEngine.MLSLABEL:
		case DBEngine.RAW:
		case DBEngine.LONG_RAW:
		case DBEngine.BYTEA:
		case DBEngine.BLOB:
		case DBEngine.BFILE:
			result = "BYTEA";
			break;
		case DBEngine.TIME:
		case DBEngine.TIME_WITHOUT_TIME_ZONE:
			result = "TIME";
			break;
		case DBEngine.TIMETZ:
		case DBEngine.TIME_WITH_TIME_ZONE:
			result = "TIMETZ";
			break;
		case DBEngine.DATE:
		case DBEngine.TIMESTAMP:
		case DBEngine.TIMESTAMP_WITHOUT_TIME_ZONE:
			result = "TIMESTAMP";
			break;
		case DBEngine.TIMESTAMPTZ:
		case DBEngine.TIMESTAMP_WITH_TIME_ZONE:
		case DBEngine.TIMESTAMP_WITH_LOCAL_TIME_ZONE:
			result = "TIMESTAMPTZ";
			break;
		case DBEngine.INTERVAL:
			result = "INTERVAL";
			break;
		case DBEngine.BOOLEAN:
		case DBEngine.BOOL:
			result = "BOOLEAN";
			break;
		case DBEngine.ENUM:
			result = "ENUM";
			break;
		case DBEngine.POINT:
			result = "POINT";
			break;
		case DBEngine.LINE:
			result = "LINE";
			break;
		case DBEngine.LSEG:
			result = "LSEG";
			break;
		case DBEngine.BOX:
			result = "BOX";
			break;
		case DBEngine.PATH:
			result = "PATH";
			break;
		case DBEngine.POLYGON:
			result = "POLYGON";
			break;
		case DBEngine.CIRCLE:
			result = "CIRCLE";
			break;
		case DBEngine.CIDR:
			result = "CIDR";
			break;
		case DBEngine.INET:
			result = "INET";
			break;
		case DBEngine.MACADDR:
			result = "MACADDR";
			break;
		case DBEngine.BIT:
			if (size!=0)
				result = new StringBuffer("BIT (").append(size).append(")").toString();
			else
				result = "BIT";
			break;
		case DBEngine.VARBIT:
		case DBEngine.BIT_VARYING:
			if (size!=0)
				result = new StringBuffer("VARBIT (").append(size).append(")").toString();
			else
				result = "VARBIT";
			break;
		case DBEngine.TSVECTOR:
			result = "TSVECTOR";
			break;
		case DBEngine.TSQUERY:
			result = "TSQUERY";
			break;
		case DBEngine.UUID:
			result = "UUID";
			break;
		case DBEngine.XML:
			result = "XML";
			break;
		case DBEngine.OID:
			result = "OID";
			break;
		case DBEngine.REGPROC:
			result = "REGPROC";
			break;
		case DBEngine.REGPROCEDURE:
			result = "REGPROCEDURE";
			break;
		case DBEngine.REGOPER:
			result = "REGOPER";
			break;
		case DBEngine.REGOPERATOR:
			result = "REGOPERATOR";
			break;
		case DBEngine.REGCLASS:
			result = "REGCLASS";
			break;
		case DBEngine.REGTYPE:
			result = "REGTYPE";
			break;
		case DBEngine.REGCONFIG:
			result = "REGCONFIG";
			break;
		case DBEngine.REGDICTIONARY:
			result = "REGDICTIONARY";
			break;
		case DBEngine.ANY:
		case DBEngine.SYS_ANYDATA:
			result = "ANY";
			break;
		case DBEngine.ANYARRAY:
			result = "ANYARRAY";
			break;
		case DBEngine.ANYELEMENT:
		case DBEngine.SYS_ANYTYPE:
			result = "ANYELEMENT";
			break;
		case DBEngine.ANYENUM:
		case DBEngine.SYS_ANYDATASET:
			result = "ANYENUM";
			break;
		case DBEngine.ANYNONARRAY:
			result = "ANYNONARRAY";
			break;
		case DBEngine.CSTRING:
			result = "CSTRING";
			break;
		case DBEngine.INTERNAL:
			result = "INTERNAL";
			break;
		case DBEngine.LANGUAGE_HANDLER:
			result = "LANGUAGE_HANDLER";
			break;
		case DBEngine.RECORD:
			result = "RECORD";
			break;
		case DBEngine.TRIGGER:
			result = "TRIGGER";
			break;
		case DBEngine.VOID:
			result = "VOID";
			break;
		case DBEngine.OPAQUE:
			result = "OPAQUE";
			break;
		case DBEngine.TXID_SNAPSHOT:
			result = "TXID_SNAPSHOT";
			break;
		default:
			result = null;
			break;
		}

		return result;
	}


	// expresion translation

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateExpression(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String translateExpression (String sourceVendorName, String expression) {
		String result = expression;

		if (sourceVendorName.contains("ORACLE")) {

			// translate expressions from oracle
			
			// replace SYSDATE with now()
			if (expression.equalsIgnoreCase("SYSDATE"))
				result = "now()";

		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateUnnamedParameter(int)
	 */
	public String translateUnnamedParameter(int paramNum) {
		// postgresql supports unnamed parameters
		return "";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#normalizeColumnName(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String)
	 */
	public String normalizeColumnName (String columnName) {
		return new StringBuffer("\"").append(columnName.toLowerCase()).append("\"").toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#normalizeColumnValue(int)
	 */
	public String normalizeColumnValue(int dataTypeID) {
		// use zero
		String result = "0";
		// or empty string for char types
		if (dataTypeID >= DBEngine.CHARTYPE_START && dataTypeID <= DBEngine.CHARTYPE_END)
			result = "''";
		// or epoch for dates
		else if (dataTypeID >= DBEngine.DATETYPE_START && dataTypeID <= DBEngine.DATETYPE_END)
			result = "'epoch'";
		// or all zero for times
		else if (dataTypeID >= DBEngine.TIMETYPE_START && dataTypeID <= DBEngine.TIMETYPE_END)
			result = "'allballs'";
		// or epoch for timestamps
		else if (dataTypeID >= DBEngine.TIMESTAMPTYPE_START && dataTypeID <= DBEngine.TIMESTAMPTYPE_END)
			result = "'epoch'";
		return result;
	}


	// function translation

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateFunctionLanguage(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String translateFunctionLanguage (String sourceVendorName, String functionLanguage) {
		String result = functionLanguage;

		if (sourceVendorName.contains("ORACLE")) {

			// translate function languages from oracle
			
			// replace PL/SQL with plpgsql
			if (functionLanguage.equalsIgnoreCase("PL/SQL"))
				result = "plpgsql";

			// replace Java with PL/Java
			else if (functionLanguage.equalsIgnoreCase("Java"))
				result = "java";

		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateFunctionType(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String translateFunctionType (String sourceVendorName, String functionType, String functionReturnType) {
		// in postgres, all functions are of type FUNCTION
		return "FUNCTION";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateFunctionReturnType(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String translateFunctionReturnType (DBEngine dbEngine, String sourceVendorName, String functionReturnType) {
		String result = functionReturnType;

		if (sourceVendorName.contains("ORACLE")) {

			// translate funtion return types from oracle
			
			// replace null with void
			if (functionReturnType==null || functionReturnType.length()==0 || functionReturnType.equalsIgnoreCase("null") || functionReturnType.equalsIgnoreCase("void"))
				result = "void";
			else
				result = dbEngine.translateDataType(sourceVendorName, getVendorNames().get(0), functionReturnType, 0, 0);
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateFunctionBodyFull(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String translateFunctionBodyFull (DBEngine dbEngine, String sourceVendorName, String sourceSchemaName, String functionLanguage, String functionReturnType, String functionBodyText) {

		if (sourceVendorName.contains("POSTGRES")) {
			
			// translate function from postgresql to postgresql
			
			// remove schema qualifiers
			functionBodyText = functionBodyText.replaceAll(new StringBuffer("(?i)").append(sourceSchemaName).append("\\.").toString(), "");

		} else if (sourceVendorName.contains("ORACLE")) {

			// translate function from oracle to postgresql

			// TODO full translation of functions is not implemented yet.
			// call stub translation instead
			functionBodyText = translateFunctionBodyStub(dbEngine, sourceVendorName, functionLanguage, functionReturnType, functionBodyText);
			
		}
		
		return functionBodyText;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateFunctionBodyStub(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String translateFunctionBodyStub(DBEngine dbEngine, String sourceVendorName, String functionLanguage, String functionReturnType, String functionBodyText) {

		// comment out the text
		if (functionBodyText!=null && functionBodyText.length()>0)
			functionBodyText = functionBodyText.replaceAll("(?m)^", "-- migrate:  ");

		// need return type for returning correct return value
		int dataType=0;
		if (functionReturnType!=null 
				&& functionReturnType.length()>0
				&& ! functionReturnType.equalsIgnoreCase("null") 
				&& ! functionReturnType.equalsIgnoreCase("void")) 
			dataType = dbEngine.getDataTypeID(sourceVendorName, functionReturnType);
		// default: return nothing
		String retVal = "      RETURN;";		

		StringBuffer stub = new StringBuffer();

		if (dataType > 0) {
			// return empty string for char types
			if (dataType >= DBEngine.CHARTYPE_START && dataType <= DBEngine.CHARTYPE_END)
				retVal = "      RETURN '';";
			// return epoch for dates
			else if (dataType >= DBEngine.DATETYPE_START && dataType <= DBEngine.DATETYPE_END)
				retVal = "      RETURN '1970-01-01';";
			// return all zero for times
			else if (dataType >= DBEngine.TIMETYPE_START && dataType <= DBEngine.TIMETYPE_END)
				retVal = "      RETURN '00:00:00';";
			// return epoch for timestamps
			else if (dataType >= DBEngine.TIMESTAMPTYPE_START && dataType <= DBEngine.TIMESTAMPTYPE_END)
				retVal = "      RETURN '1970-01-01 00:00:00';";
			// return NEW recordset for triggers
			else if (dataType == DBEngine.TRIGGER)
				retVal = "      RETURN NEW;";
			// otherwise return number zero
			else
				retVal = "      RETURN 0;";	
		}

		stub.append("-- Migration Stub Start --").append(System.getProperty("line.separator"))
		.append("BEGIN ").append(System.getProperty("line.separator"))
		.append(retVal).append(System.getProperty("line.separator"))
		.append("END;").append(System.getProperty("line.separator"))
		.append("-- Migration Stub End --").append(System.getProperty("line.separator"))
		;

		functionBodyText = new StringBuffer(functionBodyText).append(stub).toString();

		return functionBodyText;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateOperator(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String translateOperator (String sourceVendorName, String operatorName) {
		String result = operatorName;
		
		if (sourceVendorName.contains("ORACLE")) {
			// translate operator names from oracle
			result = result.replaceAll("(?i)PLUS", "+");
			result = result.replaceAll("(?i)ADD", "+");
			result = result.replaceAll("(?i)MINUS", "-");
			result = result.replaceAll("(?i)HYPHEN", "-");
			result = result.replaceAll("(?i)SUBTRACT", "-");
			result = result.replaceAll("(?i)ASTERISK", "*");
			result = result.replaceAll("(?i)START", "*");
			result = result.replaceAll("(?i)TIMES", "*");
			result = result.replaceAll("(?i)MULTIPLIEDBY", "*");
			result = result.replaceAll("(?i)MULTIPLIED", "*");
			result = result.replaceAll("(?i)SLASH", "/");
			result = result.replaceAll("(?i)DIVIDEDBY", "/");
			result = result.replaceAll("(?i)DIVIDED", "/");
			result = result.replaceAll("(?i)LESSTHAN", "<");
			result = result.replaceAll("(?i)LESS", "<");
			result = result.replaceAll("(?i)SMALLERTHAN", "<");
			result = result.replaceAll("(?i)SMALLER", "<");
			result = result.replaceAll("(?i)GREATERTHAN", ">");
			result = result.replaceAll("(?i)GREATER", ">");
			result = result.replaceAll("(?i)BIGGERTHAN", ">");
			result = result.replaceAll("(?i)BIGGER", ">");
			result = result.replaceAll("(?i)LARGERTHAN", ">");
			result = result.replaceAll("(?i)LARGER", ">");
			result = result.replaceAll("(?i)MORETHAN", ">");
			result = result.replaceAll("(?i)MORE", ">");
			result = result.replaceAll("(?i)EQUAL", "=");
			result = result.replaceAll("(?i)ABOUT", "~");
			result = result.replaceAll("(?i)LIKE", "~");
			result = result.replaceAll("(?i)TILDE", "~");
			result = result.replaceAll("(?i)EXCLAMATION", "!");
			result = result.replaceAll("(?i)NOT", "!");
			result = result.replaceAll("(?i)AT", "@");
			result = result.replaceAll("(?i)POUND", "#");
			result = result.replaceAll("(?i)PERCENT", "%");
			result = result.replaceAll("(?i)MOD", "%");
			result = result.replaceAll("(?i)XOR", "^");
			result = result.replaceAll("(?i)AND", "&");
			result = result.replaceAll("(?i)AMPERSAND", "&");
			result = result.replaceAll("(?i)OR", "|");
			result = result.replaceAll("(?i)BACKTICK", "`");
			result = result.replaceAll("(?i)QUESTION", "?");
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#isTriggerContainsInlineCode(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine)
	 */
	public boolean isTriggerContainsInlineCode () {
		// postgresql triggers can not contain inline code
		return false;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateTriggerType(java.lang.String, java.lang.String)
	 */
	public String translateTriggerType(String sourceVendorName, String triggerType) {
		
		if (sourceVendorName.contains("ORACLE")) {
			// translate trigger types from oracle to postgresql
			triggerType = triggerType.replaceAll(" EVENT", "");
			triggerType = triggerType.replaceAll(" STATEMENT", "");
			triggerType = triggerType.replaceAll(" EACH ROW", "");
		}
		
		return triggerType;
	}
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateTriggerActionOrientation(java.lang.String, java.lang.String)
	 */
	public String translateTriggerActionOrientation(String sourceVendorName, String actionOrientation) {

		if (actionOrientation.contains("ROW"))
			actionOrientation = "FOR EACH ROW";
		else
			actionOrientation = "FOR EACH STATEMENT";

		return actionOrientation;
	}
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateTriggerFunction(java.lang.String, java.lang.String)
	 */
	public String translateTriggerFunction(String sourceVendorName, String triggerFunction) {

		if (sourceVendorName.contains("POSTGRES")) {
			// translate trigger function from postgresql to postgresql
			triggerFunction = triggerFunction.replaceAll("^EXECUTE PROCEDURE ", "");
		}

		return triggerFunction;
	}
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateTriggerCode(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String translateTriggerCode(String sourceVendorName, String actionType, String triggerName, String triggerFunction) {
		StringBuffer codeExpr = new StringBuffer();
		
		if (sourceVendorName.contains("POSTGRES")) {
			// translate trigger code from postgresql to postgresql
			codeExpr.append("EXECUTE PROCEDURE ").append(triggerFunction);
		} else if (sourceVendorName.contains("ORACLE")) {
			// translate trigger code from oracle to postgresql
			if (actionType.equalsIgnoreCase("CALL"))
				codeExpr.append("EXECUTE PROCEDURE ").append(triggerFunction);
			else
				codeExpr.append("EXECUTE PROCEDURE ").append(triggerName.toLowerCase()).append("_trigger()");
		}
		
		return codeExpr.toString();
	}


	// view translation
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateViewDefinitionFull(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String translateViewDefinitionFull(DBEngine dbEngine, String sourceVendorName, String sourceSchemaName, String targetSchemaName, String viewDefinition) {

		if (sourceVendorName.contains("POSTGRES")) {
			
			// translate view from postgresql to postgresql
			
			// remove schema qualifiers
			viewDefinition = viewDefinition.replaceAll(new StringBuffer("(?i)").append(sourceSchemaName).append("\\.").toString(), "");

		} else if (sourceVendorName.contains("ORACLE")) {

			// translate view from oracle to postgresql

			// sorry, but we need to remove comments to keep parsing manageable
			viewDefinition = viewDefinition.replaceAll("(?m)--.*$", "");

			// ROWNUM iterator gets replaced with generate_series 
			viewDefinition = viewDefinition.replaceAll("\\(SELECT ROWNUM (AS )?(\\w+) FROM ALL_OBJECTS WHERE ROWNUM\\s?<=\\s?(\\d+) ORDER BY ROWNUM\\)", 
			"(SELECT s.a AS $2 FROM generate_series(1,$3) AS s(a)) AS temp$2");

			// 'WHERE ROWNUM<=X' becomes 'LIMIT X' 
			viewDefinition = viewDefinition.replaceAll("(?i)WHERE ROWNUM\\s?<?=\\s?(\\d+)", "LIMIT $1");
			viewDefinition = viewDefinition.replaceAll("(?i)AND ROWNUM\\s?<?=\\s?(\\d+)", "LIMIT $1");

			// sysdate becomes now()
			viewDefinition = viewDefinition.replaceAll("(?i)sysdate", "now()");

			// decode function becomes case structure
			// (some decodes are nested, so we need a while loop!)
			if (viewDefinition!=null) {
				while(viewDefinition.toLowerCase().contains("decode")) {

					// everything before the decode clause
					String prefix = viewDefinition.substring(0, viewDefinition.toLowerCase().indexOf("decode"));

					// first opening braces after keyword "decode"
					int beginIndex = viewDefinition.indexOf("(", viewDefinition.toLowerCase().indexOf("decode"));
					// find closing braces
					int endIndex = viewDefinition.length();
					Stack<String> stack = new Stack<String>();
					for (int i= beginIndex; i < endIndex; i++) {
						char charValue = viewDefinition.charAt(i);
						switch (charValue) {
						case '(': 
							stack.push(Character.toString(charValue)); 
							break;
						case ')': 
							stack.pop();
						}
						if (stack.isEmpty())
							endIndex = i;
					}
					// the decode clause
					String decode = viewDefinition.substring(beginIndex, endIndex);

					// everything after the decode clause
					if (endIndex >= viewDefinition.length())
						endIndex = viewDefinition.length()-2;
					String suffix = viewDefinition.substring(endIndex+1);

					// rewrite the decode clause as case structure

					// remove opening and closing braces
					decode = decode.substring(1, decode.length());

					String testValue = "";
					ArrayList<String> whenCondition = new ArrayList<String>();
					ArrayList<String> thenResult = new ArrayList<String>();
					String defaultResult = "ELSE NULL";
					int lastIndex=0;
					stack = new Stack<String>();

					// look for commata outside single quotes, double quotes, braces, curly braces, and square braces 
					for (int i= 0; i < decode.length(); i++) {
						char charValue = decode.charAt(i);
						String stringValue = Character.toString(charValue);
						String lastOpener = "";
						if (! stack.isEmpty())
							lastOpener = stack.peek();
						switch (charValue) {
						case '\'':
							if (lastOpener.equals(stringValue))
								stack.pop();
							else
								stack.push(stringValue);
							break;
						case '"':
							if (lastOpener.equals(stringValue))
								stack.pop();
							else
								stack.push(stringValue);
							break;
						case '(':
						case '{':
						case '[':
							stack.push(stringValue);
							break;
						case ')':
						case '}':
						case ']':
							stack.pop();
							break;
						case ',':
							// if stack is empty, we are outside of any quotes
							if (stack.isEmpty()) {
								String subClause = decode.substring(lastIndex, i);
								lastIndex = i+1;
								// if no test value has been defined yet, this is the test value
								if (testValue.length()==0) {
									testValue = subClause;
								} else {
									// otherwise it is a condition or a result
									int whenSize = whenCondition.size();
									int thenSize = thenResult.size();
									// if we have as many conditions as results, this is a new condition
									if (whenSize == thenSize) {
										whenCondition.add(subClause);
									} else {
										// otherwise this is a new result
										thenResult.add(subClause);
									}
								}
							}
						}
					}
					// one subclause (the last one, not terminating by comma) is still left
					// if we have same number of conditions and results, it is the default clause,
					// otherwise it is the last result
					if (whenCondition.size() == thenResult.size()) {
						defaultResult = new StringBuffer("ELSE ").append(decode.substring(lastIndex)).toString();
					} else {
						thenResult.add(decode.substring(lastIndex));
					}

					// put together the case structure
					// (enclosed in braces for easier human reading)
					StringBuffer sb = new StringBuffer();
					sb.append("(CASE ").append(testValue).append(" ");
					for (int i=0; i<whenCondition.size(); i++) {
						sb.append("WHEN ").append(whenCondition.get(i)).append(" THEN ").append(thenResult.get(i)).append(" ");
					}
					sb.append(defaultResult).append(" END)");
					decode = sb.toString();

					// remove double spaces
					decode = decode.replaceAll("\\s+", " ");

					// put everything back together
					viewDefinition = new StringBuffer(prefix).append(decode).append(suffix).toString();

				}
			}

			// remove doublequotes from field names
			if (viewDefinition!=null) {
				Pattern pattern = Pattern.compile("(.*SELECT\\s)((\"[^,\\s]*?\"[,\\s]+)+)(FROM.*)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
				Matcher matcher = pattern.matcher(viewDefinition);
				if (matcher.find()) {
					String prefix = matcher.group(1);
					String suffix = matcher.group(4);
					String phrase = matcher.group(2);
					phrase = phrase.replaceAll("(?m)\"", "");
					viewDefinition = new StringBuffer(prefix).append(phrase).append(suffix).toString();
				}
			}

			// qualify null as numeric for ID fields
			viewDefinition = viewDefinition.replaceAll("(?i)NULL\\s+(AS\\s+\\w+ID\\b)", "NULL::numeric $1");

			// column headers need AS keyword in postgresql
			// (seems to be sometimes missing after a construct that ends on parenthesis)
			if (viewDefinition!=null) {
				// if it ends with a comma we know it is a column header
				viewDefinition = viewDefinition.replaceAll("\\)\\s(\\w+),", ") AS $1,");
				// if  it does not end with comma but as end-of-line, it is a bit more
				// complicated because we can not be sure the last word is really a
				// column header.
				// So we check for key-words. If it is a keyword, we assume it is
				// not a column header
				Pattern pattern = Pattern.compile("\\)\\s(\\w+)$", Pattern.MULTILINE);
				Matcher matcher = pattern.matcher(viewDefinition);
				StringBuffer sb = new StringBuffer();
				while (matcher.find()) {
					String lastWord = matcher.group(1);
					if (! DBEngine.keyWords.contains(lastWord.toUpperCase()))
						matcher.appendReplacement(sb, ") AS " + lastWord);
				}
				matcher.appendTail(sb);
				viewDefinition = sb.toString();
			}

			// number in oracle is numeric in postgres
			viewDefinition = viewDefinition.replaceAll("(?i)AS NUMBER\\b", "AS NUMERIC");

			// postgresql has trunc for numbers and date_trunc for dates
			viewDefinition = viewDefinition.replaceAll("(?i)\\bTRUNC\\s?\\(([^<=>]*?),\\s?'MM'\\)", "DATE_TRUNC ('month', $1)::date");
			viewDefinition = viewDefinition.replaceAll("(?i)\\bTRUNC\\s?\\(([^<=>]*?)(,\\s?'DD')?\\)", "DATE_TRUNC ('day', $1)::date");

			// postgresql does not have an add_months function
			viewDefinition = viewDefinition.replaceAll("(?i)ADD_MONTHS\\s?\\((.*?),(.*?)\\)", "$1 + ($2 * interval '1 months')");

			// postgresql can not add integers to timestamps
			viewDefinition = viewDefinition.replaceAll("\\b(.*?date[^\\(]*?)\\s*?\\+\\s*?([^\\(]*?day.*?)\\b", "$1::date + $2");
			viewDefinition = viewDefinition.replaceAll("(?i)(firstof\\s?\\([^,]*?,\\s?'.*?'\\))", "$1::date");
			
			// different syntax of type casts
			// CAST (xxx AS yyy) becomes xxx::yyy
			StringBuffer buffer = new StringBuffer();
			Pattern pattern = Pattern.compile("\\bCAST\\s?\\((.*?)\\s+AS\\s+(.*?)\\b\\(?(\\d*?),?(\\d*)\\)?\\)", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(viewDefinition);
			while (matcher.find()) {
				String item = matcher.group(1);
				String type = matcher.group(2);
				String size = matcher.group(3);
				String scale = matcher.group(4);
				// if we have only a scale it was supposed to be the size
				if (size==null || size.length()==0) {
					if (scale!=null) {
						size = scale;
						scale = "";
					}
				}
				if (size==null || size.length()==0)
					size = "0";
				if (scale==null || scale.length()==0)
					scale="0";
				String translatedType = dbEngine.translateDataType(sourceVendorName, getVendorNames().get(0), type, new Integer(size).intValue(), new Integer(scale).intValue());
				matcher.appendReplacement(buffer, new StringBuffer(item).append("::").append(translatedType).toString());
			}
			matcher.appendTail(buffer);
			viewDefinition = buffer.toString();
			
		} 

		return viewDefinition;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateViewDefinitionStub(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String translateViewDefinitionStub (String sourceVendorName, String viewDefinition) {

//		// comment out actual view definition
//		viewDefinition = viewDefinition.replaceAll("(?m)^", "-- migrate:  ");
//		
//		StringBuffer stub = new StringBuffer(viewDefinition);
//		
//		// add stub
//		stub.append(System.getProperty("line.separator")).append("-- Migration Stub Start --").append(System.getProperty("line.separator"));
//		stub.append("SELECT 1");
//		stub.append(System.getProperty("line.separator")).append("-- Migration Stub End --").append(System.getProperty("line.separator"));
		
		// postgres does not store view definitions as original source code, so comments get lost.
		// to remember the original source code, we use it as return value by the stub.
		
		// escape single quotes in original view definition
		viewDefinition = viewDefinition.replaceAll("'", "''");

		// create stub
		StringBuffer stub = new StringBuffer("SELECT '")
			.append(System.getProperty("line.separator"))
			.append(viewDefinition)
			.append(System.getProperty("line.separator"))
			.append("'");

		return stub.toString();
	}

	
	// sql compatibility

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlCreateSchema(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlAdmin_createSchema(int step, String catalogName, String schemaName, String passwd) {
		if (step>0)
			return null;
		return new StringBuffer().append("CREATE SCHEMA ").append(schemaName).toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlDropSchema(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, int, java.lang.String, java.lang.String)
	 */
	public String sqlAdmin_dropSchema(int step, String catalogName, String schemaName) {
		if (step>0)
			return null;
		return new StringBuffer().append("DROP SCHEMA ").append(schemaName).append(" CASCADE").toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlAdmin_connectSchema(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, int, java.lang.String, java.lang.String)
	 */
	public String sqlAdmin_connectSchema(int step, String catalogName, String schemaName) {
		if (step>0)
			return null;
		return new StringBuffer("SET search_path TO ").append(schemaName).toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlAdmin_optimizeDatabase(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, int, java.lang.String, java.lang.String)
	 */
	public String sqlAdmin_optimizeDatabase(int step, String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		switch (step) {
		case 0:
			sql.append("VACUUM ANALYZE");
			break;
		}

		if (sql.length()>0)
			return sql.toString();
		else
			return null;
		
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlAdmin_prepareDatabaseForTransfer(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, int, java.lang.String, java.lang.String)
	 */
	public String sqlAdmin_prepareDatabaseForTransfer(int step,	String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		// When transferring to PostgreSQL, the source may contain some
		// functions or operators which are not native to PostgreSQL.
		// Customized objects referencing those functions or operators
		// available in source would therefore not compile in PostgreSQL.
		// Such functions and operators are temporarily added here for the
		// transfer migration
		
		switch (step) {
		case 0:
			// add_months(timestamp, numeric) from adempiere/db/ddlutils/postgresql/functions/Add_Months.sql
			sql.append("CREATE OR REPLACE FUNCTION ").append(schemaName).append(".add_months (in datetime timestamptz, in months numeric) RETURNS date AS "
					+ "$BODY$ "
					+ "declare duration varchar; "
					+ "BEGIN "
					+ "if datetime is null or months is null then "
					+ "return null; "
					+ "end if; "
					+ "duration = months || ' month'; "
					+ "return cast(datetime + cast(duration as interval) as date); "
					+ "END; "
					+ "$BODY$ "
					+ "LANGUAGE 'plpgsql' "
					+ "; ");
			break;
		case 1:
			// addDays(timestamp, numeric) from adempiere/db/ddlutils/postgresql/functions/addDays.sql
			sql.append("CREATE OR REPLACE FUNCTION ").append(schemaName).append(".addDays(datetime TIMESTAMP WITH TIME ZONE, days Numeric) "
					+ "RETURNS DATE AS $$ "
					+ "declare duration varchar; "
					+ "BEGIN "
					+ "if datetime is null or days is null then "
					+ "return null; "
					+ "end if; "
					+ "duration = days || ' day'; "
					+ "return cast(date_trunc('day',datetime) + cast(duration as interval) as date); "
					+ "END; "
					+ "$$ LANGUAGE plpgsql; ");
			break;
		case 2:
			// subtractDays(timestamp, numeric) from adempiere/db/ddlutils/postgresql/functions/addDays.sql
			sql.append("CREATE OR REPLACE FUNCTION ").append(schemaName).append(".subtractdays (day TIMESTAMP WITH TIME ZONE, days NUMERIC) "
					+ "RETURNS DATE AS $$ "
					+ "BEGIN "
					+ "RETURN ").append(schemaName).append(".addDays(day,(days * -1)); "
					+ "END; "
					+ "$$ LANGUAGE plpgsql; ");
			break;
		case 3:
			// addDays(interval, numeric) from adempiere/db/ddlutils/postgresql/functions/addDays.sql
			sql.append("CREATE OR REPLACE FUNCTION ").append(schemaName).append(".adddays(inter interval, days numeric) "
					+ "RETURNS integer AS "
					+ "$BODY$ "
					+ "BEGIN "
					+ "RETURN ( EXTRACT( EPOCH FROM ( inter ) ) / 86400 ) + days; "
					+ "END; "
					+ "$BODY$ "
					+ "LANGUAGE plpgsql VOLATILE "
					+ "COST 100;");
			break;
		case 4:
			// subtractDays(interval, numeric) from adempiere/db/ddlutils/postgresql/functions/addDays.sql
			sql.append("CREATE OR REPLACE FUNCTION ").append(schemaName).append(".subtractdays(inter interval, days numeric) "
					+ "RETURNS integer AS "
					+ "$BODY$ "
					+ "BEGIN "
					+ "RETURN ( EXTRACT( EPOCH FROM ( inter ) ) / 86400 ) - days; "
					+ "END; "
					+ "$BODY$ "
					+ "LANGUAGE plpgsql VOLATILE "
					+ "COST 100; ");
			break;
		case 5:
			// charAt(string, position) from adempiere/db/ddlutils/postgresql/functions/charAt.sql
			sql.append("CREATE OR REPLACE FUNCTION ").append(schemaName).append(".charAt ( "
					+ "IN VARCHAR, "
					+ "IN INTEGER "
					+ ") RETURNS VARCHAR AS "
					+ "$$ "
					+ "BEGIN "
					+ "RETURN SUBSTR($1, $2, 1); "
					+ "END; "
					+ "$$ LANGUAGE plpgsql; ");
			break;
		case 6:
			// daysBetween(timestamp, timestamp) from adempiere/db/ddlutils/postgresql/functions/daysBetween.sql
			sql.append("CREATE OR REPLACE FUNCTION ").append(schemaName).append(".daysBetween(p_date1 TIMESTAMP WITH TIME ZONE, p_date2 TIMESTAMP WITH TIME ZONE) "
					+ "RETURNS INTEGER AS $$ "
					+ "BEGIN "
					+ "RETURN CAST(p_date1 AS DATE) - CAST(p_date2 as DATE); "
					+ "END; "
					+ "$$ LANGUAGE plpgsql; ");
			break;
		case 7:
			// firstof(timestamp, string) from adempiere/db/ddlutils/postgresql/functions/firstOf.sql
			sql.append("CREATE OR REPLACE FUNCTION ").append(schemaName).append(".firstOf ( "
					+ "IN TIMESTAMP WITH TIME ZONE, "
					+ "IN VARCHAR "
					+ ") RETURNS DATE AS "
					+ "$$ "
					+ "DECLARE "
					+ "datepart VARCHAR; "
					+ "datetime TIMESTAMP WITH TIME ZONE; "
					+ "offsetdays INTEGER; "
					+ "BEGIN "
					+ "datepart = $2; "
					+ "offsetdays = 0; "
					+ "IF $2 IN ('') THEN "
					+ "datepart = 'millennium'; "
					+ "ELSEIF $2 IN ('') THEN "
					+ "datepart = 'century'; "
					+ "ELSEIF $2 IN ('') THEN "
					+ "datepart = 'decade'; "
					+ "ELSEIF $2 IN ('IYYY','IY','I') THEN "
					+ "datepart = 'year'; "
					+ "ELSEIF $2 IN ('SYYYY','YYYY','YEAR','SYEAR','YYY','YY','Y') THEN "
					+ "datepart = 'year'; "
					+ "ELSEIF $2 IN ('Q') THEN "
					+ "datepart = 'quarter'; "
					+ "ELSEIF $2 IN ('MONTH','MON','MM','RM') THEN "
					+ "datepart = 'month'; "
					+ "ELSEIF $2 IN ('IW') THEN "
					+ "datepart = 'week'; "
					+ "ELSEIF $2 IN ('W') THEN "
					+ "datepart = 'week'; "
					+ "ELSEIF $2 IN ('DDD','DD','J') THEN "
					+ "datepart = 'day'; "
					+ "ELSEIF $2 IN ('DAY','DY','D') THEN "
					+ "datepart = 'week'; "
					+ "offsetdays = -1; "
					+ "ELSEIF $2 IN ('HH','HH12','HH24') THEN "
					+ "datepart = 'hour'; "
					+ "ELSEIF $2 IN ('MI') THEN "
					+ "datepart = 'minute'; "
					+ "ELSEIF $2 IN ('') THEN "
					+ "datepart = 'second'; "
					+ "ELSEIF $2 IN ('') THEN "
					+ "datepart = 'milliseconds'; "
					+ "ELSEIF $2 IN ('') THEN "
					+ "datepart = 'microseconds'; "
					+ "END IF; "
					+ "datetime = date_trunc(datepart, $1); "
					+ "RETURN cast(datetime as date) + offsetdays; "
					+ "END; "
					+ "$$ LANGUAGE plpgsql; ");
			break;
		case 8:
			// instr(string, string, int) from adempiere/db/ddlutils/postgresql/functions/INSTR.sql
			sql.append("CREATE FUNCTION ").append(schemaName).append(".instr(string varchar, string_to_search varchar, beg_index integer) "
					+ "RETURNS integer AS $$ "
					+ "DECLARE "
					+ "pos integer NOT NULL DEFAULT 0; "
					+ "temp_str varchar; "
					+ "beg integer; "
					+ "length integer; "
					+ "ss_length integer; "
					+ "BEGIN "
					+ "IF beg_index > 0 THEN "
					+ "temp_str := substring(string FROM beg_index); "
					+ "pos := position(string_to_search IN temp_str); "
					+ "IF pos = 0 THEN "
					+ "RETURN 0; "
					+ "ELSE "
					+ "RETURN pos + beg_index - 1; "
					+ "END IF; "
					+ "ELSE "
					+ "ss_length := char_length(string_to_search); "
					+ "length := char_length(string); "
					+ "beg := length + beg_index - ss_length + 2; "
					+ "WHILE beg > 0 LOOP "
					+ "temp_str := substring(string FROM beg FOR ss_length); "
					+ "pos := position(string_to_search IN temp_str); "
					+ "IF pos > 0 THEN "
					+ "RETURN beg; "
					+ "END IF; "
					+ "beg := beg - 1; "
					+ "END LOOP; "
					+ "RETURN 0; "
					+ "END IF; "
					+ "END; "
					+ "$$ LANGUAGE plpgsql STRICT IMMUTABLE; ");
			break;
		case 9:
			// instr(string, string) from adempiere/db/ddlutils/postgresql/functions/INSTR.sql
			sql.append("CREATE FUNCTION ").append(schemaName).append(".instr(varchar, varchar) RETURNS integer AS $$ "
					+ "DECLARE "
					+ "pos integer; "
					+ "BEGIN "
					+ "pos:= ").append(schemaName).append(".instr($1, $2, 1); "
					+ "RETURN pos; "
					+ "END; "
					+ "$$ LANGUAGE plpgsql STRICT IMMUTABLE; ");
			break;
		case 10:
			// instr(string, string, int, int) from adempiere/db/ddlutils/postgresql/functions/INSTR.sql
			sql.append("CREATE FUNCTION ").append(schemaName).append(".instr(string varchar, string_to_search varchar, "
					+ "beg_index integer, occur_index integer) "
					+ "RETURNS integer AS $$ "
					+ "DECLARE "
					+ "pos integer NOT NULL DEFAULT 0; "
					+ "occur_number integer NOT NULL DEFAULT 0; "
					+ "temp_str varchar; "
					+ "beg integer; "
					+ "i integer; "
					+ "length integer; "
					+ "ss_length integer; "
					+ "BEGIN "
					+ "IF beg_index > 0 THEN "
					+ "beg := beg_index; "
					+ "temp_str := substring(string FROM beg_index); "
					+ "FOR i IN 1..occur_index LOOP "
					+ "pos := position(string_to_search IN temp_str); "
					+ "IF i = 1 THEN "
					+ "beg := beg + pos - 1; "
					+ "ELSE "
					+ "beg := beg + pos; "
					+ "END IF; "
					+ "temp_str := substring(string FROM beg + 1); "
					+ "END LOOP; "
					+ "IF pos = 0 THEN "
					+ "RETURN 0; "
					+ "ELSE "
					+ "RETURN beg; "
					+ "END IF; "
					+ "ELSE "
					+ "ss_length := char_length(string_to_search); "
					+ "length := char_length(string); "
					+ "beg := length + beg_index - ss_length + 2; "
					+ "WHILE beg > 0 LOOP "
					+ "temp_str := substring(string FROM beg FOR ss_length); "
					+ "pos := position(string_to_search IN temp_str); "
					+ "IF pos > 0 THEN "
					+ "occur_number := occur_number + 1; "
					+ "IF occur_number = occur_index THEN "
					+ "RETURN beg; "
					+ "END IF; "
					+ "END IF; "
					+ "beg := beg - 1; "
					+ "END LOOP; "
					+ "RETURN 0; "
					+ "END IF; "
					+ "END; "
					+ "$$ LANGUAGE plpgsql STRICT IMMUTABLE; ");
			break;
		case 11:
			// round(numeric, numeric) from adempiere/db/ddlutils/postgresql/functions/round.sql
			sql.append("CREATE OR REPLACE FUNCTION ").append(schemaName).append(".round ( "
					+ "IN NUMERIC, "
					+ "IN NUMERIC "
					+ ") RETURNS NUMERIC AS "
					+ "$$ "
					+ "BEGIN "
					+ "RETURN ROUND($1, cast($2 as integer)); "
					+ "END; "
					+ "$$ LANGUAGE plpgsql; ");
			break;
		case 12:
			// trunc(timestamp) from adempiere/db/ddlutils/postgresql/functions/trunc.sql
			sql.append("CREATE OR REPLACE FUNCTION ").append(schemaName).append(".trunc(datetime timestamp with time zone) "
					+ "RETURNS timestamp with time zone AS "
					+ "$BODY$ "
					+ "BEGIN "
					+ "RETURN CAST(datetime AS DATE); "
					+ "END; "
					+ "$BODY$ "
					+ "LANGUAGE plpgsql VOLATILE "
					+ "COST 100; ");
			break;
		case 13:
			// trunc(timestamp, string) from adempiere/db/ddlutils/postgresql/functions/trunc.sql
			sql.append("CREATE OR REPLACE FUNCTION ").append(schemaName).append(".trunc(datetime TIMESTAMP WITH TIME ZONE, format varchar) "
					+ "RETURNS DATE AS $$ "
					+ "BEGIN "
					+ "IF format = 'Q' THEN "
					+ "RETURN CAST(DATE_Trunc('quarter',datetime) as DATE); "
					+ "ELSIF format = 'Y' or format = 'YEAR' THEN "
					+ "RETURN CAST(DATE_Trunc('year',datetime) as DATE); "
					+ "ELSIF format = 'MM' or format = 'MONTH' THEN "
					+ "RETURN CAST(DATE_Trunc('month',datetime) as DATE); "
					+ "ELSIF format = 'DD' THEN "
					+ "RETURN CAST(DATE_Trunc('day',datetime) as DATE); "
					+ "ELSIF format = 'DY' THEN "
					+ "RETURN CAST(DATE_Trunc('day',datetime) as DATE); "
					+ "ELSE "
					+ "RETURN CAST(datetime AS DATE); "
					+ "END IF; "
					+ "END; "
					+ "$$ LANGUAGE plpgsql; ");
			break;
		case 14:
			// trunc(interval) from adempiere/db/ddlutils/postgresql/functions/trunc.sql
			sql.append("CREATE OR REPLACE FUNCTION ").append(schemaName).append(".trunc(i interval) "
					+ "RETURNS integer AS "
					+ "$BODY$ "
					+ "BEGIN "
					+ "RETURN EXTRACT(DAY FROM i); "
					+ "END; "
					+ "$BODY$ "
					+ "LANGUAGE plpgsql VOLATILE "
					+ "COST 100; ");
			break;
		case 15:
			// operator + (timestamp, numeric) from adempiere/db/ddlutils/postgresql/operators.sql
			sql.append("CREATE OPERATOR ").append(schemaName).append(".+ ( PROCEDURE = ").append(schemaName).append(".adddays, "
					+ "LEFTARG = TIMESTAMPTZ, RIGHTARG = NUMERIC, "
					+ "COMMUTATOR = +); ");
			break;
		case 16:
			// operator - (timestamp, numeric) from adempiere/db/ddlutils/postgresql/operators.sql
			sql.append("CREATE OPERATOR ").append(schemaName).append(".- ( PROCEDURE = ").append(schemaName).append(".subtractdays, "
					+ "LEFTARG = TIMESTAMPTZ, RIGHTARG = NUMERIC, "
					+ "COMMUTATOR = -); ");
			break;
		}

		if (sql.length()>0)
			return sql.toString();
		else
			return null;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_avilableDatabases()
	 */
	public String sqlMetadata_availableDatabases() {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "datname AS DATABASE_NAME "
				+ "FROM pg_database "
				+ "WHERE datname NOT LIKE 'template%' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_closeCharSetTest(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_closeCharSetTest(String catalogName, String schemaName, String tableName) {
		// no character set test needed in postgresql
		return null;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_openCharSetTest(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_openCharSetTest(String catalogName, String schemaName, String tableName) {
		// no character set test needed in postgresql
		return null;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_tableNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_tableNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "table_name AS OBJECT_NAME "
				+ "FROM information_schema.tables "
				+ "WHERE table_type = 'BASE TABLE' "
				+ "AND table_catalog = '").append(catalogName).append("' "
				+ "AND table_schema = '").append(schemaName).append("' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_tableColumns(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_tableColumns(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "table_name AS TABLE_NAME, "
				+ "ordinal_position AS COLUMN_SEQUENCE, "
				+ "column_name AS COLUMN_NAME, "
				+ "data_type AS COLUMN_TYPE, "
				+ "COALESCE (character_maximum_length, numeric_precision) AS COLUMN_SIZE, "
				+ "numeric_scale AS COLUMN_PRECISION, "
				+ "column_default AS COLUMN_DEFAULT, "
				+ "is_nullable AS COLUMN_NULLABLE "
				+ "FROM information_schema.columns "
				+ "WHERE table_name = ? "
				+ "AND table_catalog = '").append(catalogName).append("' "
				+ "AND table_schema = '").append(schemaName).append("' "
				+ "ORDER BY 2 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetaData_viewNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_viewNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "table_name AS OBJECT_NAME "
				+ "FROM information_schema.tables "
				+ "WHERE table_type = 'VIEW' "
				+ "AND table_catalog = '").append(catalogName).append("' "
				+ "AND table_schema = '").append(schemaName).append("' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_viewDefinitions(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_viewDefinitions(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "table_name AS VIEW_NAME, "
				+ "view_definition AS VIEW_DEFINITION "
				+ "FROM information_schema.views "
				+ "WHERE table_name = ? "
				+ "AND table_catalog = '").append(catalogName).append("' "
				+ "AND table_schema = '").append(schemaName).append("' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_functionNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_functionNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		// postgres has overloaded functions, and adempiere uses them, so we need unique signatures and use specific_name
		sql.append("SELECT "
				+ "specific_name AS OBJECT_NAME "
				+ "FROM information_schema.routines "
				+ "WHERE specific_catalog = '").append(catalogName).append("' "
				+ "AND specific_schema = '").append(schemaName).append("' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_functionArguments(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_functionArguments(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "rt.routine_name AS FUNC_NAME, "
				+ "rt.routine_type AS FUNC_TYPE, "
				+ "rt.external_language AS FUNC_LANG, "
				+ "rt.data_type AS RET_TYPE, "
				+ "pr.ordinal_position AS SEQ_NUM, "
				+ "pr.parameter_mode AS ARG_DIR, "
				+ "pr.parameter_name AS ARG_NAME, "
				+ "pr.data_type AS ARG_TYPE "
				+ "FROM information_schema.routines rt "
				+ "LEFT JOIN information_schema.parameters pr ON (rt.specific_name = pr.specific_name) "
				+ "WHERE rt.specific_name = ? "
				+ "AND rt.specific_catalog = '").append(catalogName).append("' "
				+ "AND rt.specific_schema = '").append(schemaName).append("' "
				+ "ORDER BY 1,5 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_functionBodies(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_functionBodies(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "rt.routine_name AS FUNC_NAME, "
				+ "0 AS SEQ_NUM, "
				+ "rt.routine_definition AS FUNC_DEF "
				+ "FROM information_schema.routines rt "
				+ "WHERE rt.specific_name = ? "
				+ "AND rt.specific_catalog = '").append(catalogName).append("' "
				+ "AND rt.specific_schema = '").append(schemaName).append("' "
				+ "ORDER BY 2 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_operatorNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_operatorNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "op.oprname || ' (' || "
				+ "CASE op.oprleft "
				+  "WHEN 0 THEN '' || "
				+   "CASE op.oprright "
				+    "WHEN 0 THEN '' "
				+    "ELSE rt.typname "
				+   "END "
				+  "ELSE lt.typname || "
				+   "CASE op.oprright "
				+    "WHEN 0 THEN '' "
				+    "ELSE ', ' || rt.typname "
				+   "END "
				+ "END "
				+ "|| ')' AS OBJECT_NAME, "
				+ "lt.typname, "
				+ "rt.typname "
				+ "FROM pg_operator op "
				+ "LEFT JOIN pg_type lt ON (op.oprleft = lt.oid) "
				+ "LEFT JOIN pg_type rt ON (op.oprright = rt.oid) "
				+ "WHERE op.oprnamespace IN "
				+ "(SELECT oid FROM pg_namespace WHERE nspname LIKE '").append(schemaName).append("') "
				+ "ORDER BY 1,2,3 ");
				
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_operatorSignatures(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_operatorSignatures(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "op.oprname AS OPERATOR_NAME, "
				+ "lt.typname AS LEFT_ARG, "
				+ "rt.typname AS RIGHT_ARG, "
				+ "tr.typname AS RETURN_TYPE "
				+ "FROM pg_operator op "
				+ "LEFT JOIN pg_type lt ON (op.oprleft = lt.oid) "
				+ "LEFT JOIN pg_type rt ON (op.oprright = rt.oid) "
				+ "LEFT JOIN pg_type tr ON (op.oprresult = tr.oid) "
				+ "WHERE op.oprnamespace IN "
				+ "(SELECT oid FROM pg_namespace WHERE nspname LIKE '").append(schemaName).append("')"
				+ "AND ("
				+ "op.oprname || ' (' || " 
				+ "CASE op.oprleft "
				+  "WHEN 0 THEN '' || "
				+   "CASE op.oprright "
				+    "WHEN 0 THEN '' "
				+    "ELSE rt.typname "
				+   "END "
				+  "ELSE lt.typname || "
				+   "CASE op.oprright "
				+    "WHEN 0 THEN '' "
				+    "ELSE ', ' || rt.typname "
				+   "END "
				+ "END "
				+ "|| ')') = ? "
				+ "ORDER BY 1,2,3 ");

		return sql.toString();
	}
	
	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_operatorDefinitions(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_operatorDefinitions(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "op.oprname AS OPERATOR_NAME, "
				+ "CASE op.oprcode "
				+  "WHEN 0 THEN null "
				+  "ELSE op.oprcode "
				+ "END AS FUNCTION_NAME, "
				+ "com.oprname AS OP_COMMUTATOR, "
				+ "neg.oprname AS OP_NEGATOR, "
				+ "CASE op.oprrest "
				+  "WHEN 0 THEN null "
				+  "ELSE op.oprrest "
				+ "END AS OP_RESTRICT, "
				+ "CASE op.oprjoin "
				+  "WHEN 0 THEN null "
				+  "ELSE op.oprjoin "
				+ "END AS OP_JOIN, "
				+ "op.oprcanhash AS OP_HASHABLE, "
				+ "op.oprcanmerge AS OP_MERGEABLE "
				+ "FROM pg_operator op "
				+ "LEFT JOIN pg_type lt ON (op.oprleft = lt.oid) "
				+ "LEFT JOIN pg_type rt ON (op.oprright = rt.oid) "
				+ "LEFT JOIN pg_operator com ON (op.oprcom = com.oid) "
				+ "LEFT JOIN pg_operator neg ON (op.oprnegate = neg.oid) "
				+ "WHERE op.oprnamespace IN "
				+ "(SELECT oid FROM pg_namespace WHERE nspname LIKE '").append(schemaName).append("')"
				+ "AND ("
				+ "op.oprname || ' (' || " 
				+ "CASE op.oprleft "
				+  "WHEN 0 THEN '' || "
				+   "CASE op.oprright "
				+    "WHEN 0 THEN '' "
				+    "ELSE rt.typname "
				+   "END "
				+  "ELSE lt.typname || "
				+   "CASE op.oprright "
				+    "WHEN 0 THEN '' "
				+    "ELSE ', ' || rt.typname "
				+   "END "
				+ "END "
				+ "|| ')') = ? "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_triggerNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_triggerNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "trigger_name AS OBJECT_NAME "
				+ "FROM information_schema.triggers "
				+ "WHERE trigger_catalog = '").append(catalogName).append("' " 
				+ "AND trigger_schema = '").append(schemaName).append("' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_triggerTables(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_triggerTables(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "trigger_name AS TRIG_NAME, "
				// TODO: ask PG version for correct column name 
				//+ "condition_timing AS TRIG_TYPE, "  // valid before PG 9.1
				+ "action_timing AS TRIG_TYPE, "  // valid after PG 9.1
				+ "event_manipulation AS TRIG_EVENT, "
				+ "event_object_table AS TABLE_NAME, "
				+ "'CALL' AS ACTION_TYPE, "
				+ "action_orientation AS ACTION_ORIENTATION "
				+ "FROM information_schema.triggers "
				+ "WHERE trigger_name = ? "
				+ "AND trigger_catalog = '").append(catalogName).append("' "
				+ "AND trigger_schema = '").append(schemaName).append("' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_triggerDefinitions(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_triggerDefinitions(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "trigger_name AS TRIG_NAME, "
				+ "action_statement AS TRIG_BODY "
				+ "FROM information_schema.triggers "
				+ "WHERE trigger_name = ? "
				+ "AND trigger_catalog = '").append(catalogName).append("' "
				+ "AND trigger_schema = '").append(schemaName).append("' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_sequenceNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_sequenceNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT relname AS OBJECT_NAME "
				+ "FROM pg_class "
				+ "WHERE relkind='S' "
				+ "AND relnamespace IN ("
				+ "SELECT oid FROM pg_namespace "
				+ "WHERE nspname LIKE '").append(schemaName).append("') "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_sequenceDefinitions(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_sequenceDefinitions(String catalogName, String schemaName, String sequenceName) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "
				+ "min_value AS MIN_VALUE, "
				+ "max_value AS MAX_VALUE, "
				+ "increment_by AS INCREMENT_BY, "
				+ "cycle AS IS_CYCLED ,"
				+ "cache_size AS CACHE_SIZE, "
				+ "last_value AS LAST_VALUE "
				+ "FROM ").append("pg_sequences WHERE sequencename='").append(sequenceName).append("' ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_sequenceDefinitions(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_sequenceDefinitions(String productVersion , String catalogName, String schemaName, String sequenceName) {
		int productVersionAsInt = 0;
		if(productVersion == null
				|| productVersion.length() == 0) {
			productVersion = "0";
		}
		//	Validate
		if(productVersion.indexOf(".") > 0) {
			productVersion = productVersion.substring(0, productVersion.indexOf("."));
		}
		productVersionAsInt = Integer.parseInt(productVersion);
		//	For older versions
		if(productVersionAsInt < 10) {	//	9.x
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT "
					+ "min_value AS MIN_VALUE, "
					+ "max_value AS MAX_VALUE, "
					+ "increment_by AS INCREMENT_BY, "
					+ "is_cycled AS IS_CYCLED, "
					+ "cache_value AS CACHE_SIZE, "
					+ "last_value AS LAST_VALUE "
					+ "FROM ").append(schemaName).append(".").append(sequenceName).append(" ");
			return sql.toString();
		} else {	//	Latest
			return sqlMetadata_sequenceDefinitions(catalogName, schemaName, sequenceName);
		}
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetdata_primaryKeyNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_primaryKeyNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "constraint_name AS OBJECT_NAME "
				+ "FROM information_schema.table_constraints tc "
				+ "WHERE constraint_type = 'PRIMARY KEY' "
				+ "AND constraint_catalog = '").append(catalogName).append("' "
				+ "AND constraint_schema = '").append(schemaName).append("' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_primaryKeyTables(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_primaryKeyTables(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "tc.constraint_name AS PK_NAME, "
				+ "tc.table_name AS TABLE_NAME, "
				+ "tc.is_deferrable AS IS_DEFERRABLE, "
				+ "tc.initially_deferred AS INITIALLY_DEFERRED "
				+ "FROM information_schema.table_constraints tc "
				+ "WHERE constraint_type = 'PRIMARY KEY' "
				+ "AND constraint_name = ? "
				+ "AND constraint_catalog = '").append(catalogName).append("' "
				+ "AND constraint_schema = '").append(schemaName).append("' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_primaryKeyColumns(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_primaryKeyColumns(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "constraint_name AS PK_NAME, "
				+ "ordinal_position AS PK_SEQ, "
				+ "table_name AS TABLE_NAME, "
				+ "column_name AS COLUMN_NAME "
				+ "FROM information_schema.key_column_usage "
				+ "WHERE constraint_name = ? "
				+ "AND constraint_catalog = '").append(catalogName).append("' "
				+ "AND constraint_schema = '").append(schemaName).append("' "
				+ "ORDER BY 2 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_foreignKeyNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_foreignKeyNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "constraint_name AS OBJECT_NAME "
				+ "FROM information_schema.table_constraints tc "
				+ "WHERE constraint_type = 'FOREIGN KEY' "
				+ "AND constraint_catalog = '").append(catalogName).append("' "
				+ "AND constraint_schema = '").append(schemaName).append("' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_foreignKeyTables(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_foreignKeyTables(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT DISTINCT "
				+ "tc.constraint_name AS FK_NAME, "
				+ "tc.table_name AS TABLE_NAME, "
				+ "ccu.table_name AS FTABLE_NAME, "
				+ "tc.is_deferrable AS IS_DEFERRABLE, "
				+ "tc.initially_deferred AS INITIALLY_DEFERRED, "
				+ "rc.match_option AS MATCH_TYPE, "
				+ "rc.update_rule AS ON_UPDATE, "
				+ "rc.delete_rule AS ON_DELETE "
				+ "FROM information_schema.table_constraints tc "
				+ "LEFT JOIN information_schema.referential_constraints rc "
				+	"ON tc.constraint_catalog = rc.constraint_catalog "
				+	"AND tc.constraint_schema = rc.constraint_schema "
				+ 	"AND tc.constraint_name = rc.constraint_name "
				+ "LEFT JOIN information_schema.constraint_column_usage ccu "
				+	"ON tc.constraint_catalog = ccu.constraint_catalog "
				+	"AND tc.constraint_schema = ccu.constraint_schema "
				+ 	"AND tc.constraint_name = ccu.constraint_name "
				+ "WHERE tc.constraint_type = 'FOREIGN KEY' "
				+ "AND tc.constraint_name = ? "
				+ "AND tc.constraint_catalog = '").append(catalogName).append("' "
				+ "AND tc.constraint_schema = '").append(schemaName).append("' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_foreignKeyColumns(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_foreignKeyColumns(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT DISTINCT "
				+ "kcu.constraint_name AS FK_NAME, "
				+ "kcu.ordinal_position AS FK_SEQ, "
				+ "kcu.table_name AS TABLE_NAME, "
				+ "kcu.column_name AS COLUMN_NAME, "
				+ "ft.relname AS FTABLE_NAME, "
				+ "attr.attname AS FCOLUMN_NAME "
				+ "FROM information_schema.key_column_usage kcu "
				+ "LEFT JOIN pg_constraint c "
				+ 	"ON kcu.constraint_name = c.conname "
				+ "LEFT JOIN pg_namespace nsp "
				+	"ON c.connamespace = nsp.oid "
				+	"AND kcu.constraint_schema = nsp.nspname "
				+ "LEFT JOIN pg_class ft "
				+	"ON c.confrelid = ft.oid "
				+ "LEFT JOIN pg_attribute attr "
				+	"ON c.confrelid = attr.attrelid "
				+	"AND c.confkey[kcu.ordinal_position]=attr.attnum "
				+ "WHERE kcu.constraint_name = ? "
				+ "AND kcu.constraint_catalog = '").append(catalogName).append("' "
				+ "AND kcu.constraint_schema = '").append(schemaName).append("' "
				+ "ORDER BY 1,2 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_checkNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_checkNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT cns.conname AS OBJECT_NAME "
				+ "FROM pg_constraint cns "
				+ "LEFT JOIN pg_namespace nmspc ON cns.connamespace = nmspc.oid "
				+ "WHERE cns.contype = 'c' "
				+ "AND nmspc.nspname = '").append(schemaName).append("' "
				+ "ORDER BY 1 ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_checkTables(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_checkTables(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "cns.conname AS CHECK_NAME, "
				+ "cls.relname AS TABLE_NAME, "
				+ "cns.condeferrable AS IS_DEFERRABLE, "
				+ "cns.condeferred AS INITIALLY_DEFERRED "
				+ "FROM pg_constraint cns "
				+ "LEFT JOIN pg_class cls "
				+	"ON (cns.conrelid = cls.oid) "
				+ "LEFT JOIN pg_namespace nmspc "
				+	"ON (cns.connamespace = nmspc.oid) "
				+ "WHERE cns.contype = 'c' "
				+ "AND nmspc.nspname = '").append(schemaName).append("' "
				+ "AND cns.conname = ? "
				+ "ORDER BY 1 ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_checkRules(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_checkRules(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "cns.conname AS CHECK_NAME, "
				+ "cls.relname AS TABLE_NAME, "
				+ "cns.consrc AS CHECK_CLAUSE "
				+ "FROM pg_constraint cns "
				+ "LEFT JOIN pg_class cls "
				+	"ON (cns.conrelid = cls.oid) "
				+ "LEFT JOIN pg_namespace nmspc "
				+	"ON (cns.connamespace = nmspc.oid) "
				+ "WHERE cns.contype = 'c' "
				+ "AND nmspc.nspname = '").append(schemaName).append("' "
				+ "AND cns.conname = ? "
				+ "ORDER BY 1 ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_uniqueNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_uniqueNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "constraint_name AS OBJECT_NAME "
				+ "FROM information_schema.table_constraints tc "
				+ "WHERE constraint_type = 'UNIQUE' "
				+ "AND constraint_catalog = '").append(catalogName).append("' "
				+ "AND constraint_schema = '").append(schemaName).append("' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_uniqueTables(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_uniqueTables(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "tc.constraint_name AS UNIQUE_NAME, "
				+ "tc.table_name AS TABLE_NAME, "
				+ "tc.is_deferrable AS IS_DEFERRABLE, "
				+ "tc.initially_deferred AS INITIALLY_DEFERRED "
				+ "FROM information_schema.table_constraints tc "
				+ "WHERE constraint_type = 'UNIQUE' "
				+ "AND constraint_name = ? "
				+ "AND constraint_catalog = '").append(catalogName).append("' "
				+ "AND constraint_schema = '").append(schemaName).append("' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_uniqueColumns(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_uniqueColumns(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "constraint_name AS UNIQUE_NAME, "
				+ "ordinal_position AS UNIQUE_SEQ, "
				+ "table_name AS TABLE_NAME, "
				+ "column_name AS COLUMN_NAME "
				+ "FROM information_schema.key_column_usage "
				+ "WHERE constraint_name = ? "
				+ "AND constraint_catalog = '").append(catalogName).append("' "
				+ "AND constraint_schema = '").append(schemaName).append("' "
				+ "ORDER BY 2 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_indexNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_indexNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "pgc.relname AS OBJECT_NAME "
				+ "FROM pg_class pgc "
				+ "LEFT JOIN pg_index pgi "
				+ 	"ON pgc.oid = pgi.indexrelid "
				+ "LEFT JOIN pg_namespace nmspc "
				+	"ON pgc.relnamespace = nmspc.oid "
				+ "WHERE pgi.indisprimary != 't' "
				+ "AND nmspc.nspname = '").append(schemaName).append("' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_indexTables(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_indexTables(String catalogName, String schemaName) { 
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "pgc.relname AS INDEX_NAME, "
				+ "pgctbl.relname AS TABLE_NAME, "
				+ "pgi.indisunique AS IS_UNIQUE "
				+ "FROM pg_class pgc "
				+ "LEFT JOIN pg_index pgi "
				+ 	"ON pgc.oid = pgi.indexrelid "
				+ "LEFT JOIN pg_namespace nmspc "
				+ 	"ON pgc.relnamespace = nmspc.oid "
				+ "LEFT JOIN pg_class pgctbl "
				+ 	"ON pgi.indrelid = pgctbl.oid "
				+ "WHERE nmspc.nspname = '").append(schemaName).append("' "
				+ "AND pgc.relname = ? "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_indexColumns(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_indexColumns(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		// postgresql returns any expression as column name, so we can just always return NULL
		// as expression value
		
		sql.append("SELECT "
				+ "(SELECT pgc.relname "
				+	"FROM pg_class pgc "
				+	"LEFT JOIN pg_index pgi ON pgc.oid = pgi.indexrelid "
				+	"LEFT JOIN pg_namespace nmspc ON pgc.relnamespace = nmspc.oid "
				+	"WHERE nmspc.nspname = '").append(schemaName).append("' "
				+	"AND pgc.relname = ? "
				+ ") AS INDEX_NAME, "
				+ "s.a AS INDEX_SEQ, "
				+ "(SELECT pgt.relname "
				+	"FROM pg_class pgt "
				+	"LEFT JOIN pg_index pgi ON pgt.oid = pgi.indrelid "
				+	"LEFT JOIN pg_class pgc ON pgi.indexrelid = pgc.oid "
				+	"LEFT JOIN pg_namespace nmspc ON pgc.relnamespace = nmspc.oid "
				+	"WHERE nmspc.nspname = '").append(schemaName).append("' "
				+	"AND pgc.relname = ? "
				+ ") AS TABLE_NAME, "
				+ "pg_get_indexdef((SELECT pgc.oid "
				+   "FROM pg_class pgc "
				+   "LEFT JOIN pg_namespace nmspc ON pgc.relnamespace = nmspc.oid "
				+   "WHERE nmspc.nspname = '").append(schemaName).append("' "
				+	"AND pgc.relname = ?), "
				+   "s.a, "
				+   "false"
				+ ") AS COLUMN_NAME, "
				+ "NULL AS EXPRESSION, "
				+ "CASE ( "
				+	"SELECT pgi.indoption[s.a-1] "
				+	"FROM pg_index pgi "
				+	"LEFT JOIN pg_class pgc ON pgi.indexrelid = pgc.oid "
				+	"LEFT JOIN pg_namespace nmspc ON pgc.relnamespace = nmspc.oid "
				+	"WHERE nmspc.nspname = '").append(schemaName).append("' "
				+	"AND pgc.relname = ? "
				+ ") "
				+	"WHEN 0 THEN 'ASC' "
				+	"WHEN 1 THEN 'DESC' "
				+	"WHEN 2 THEN 'ASC' "
				+	"WHEN 3 THEN 'DESC' "
				+ "END AS SORT_ORDER, "
				+ "CASE ( "
				+	"SELECT pgi.indoption[s.a-1] "
				+	"FROM pg_index pgi "
				+	"LEFT JOIN pg_class pgc ON pgi.indexrelid = pgc.oid "
				+	"LEFT JOIN pg_namespace nmspc ON pgc.relnamespace = nmspc.oid "
				+	"WHERE nmspc.nspname = '").append(schemaName).append("' "
				+	"AND pgc.relname = ? "
				+ ") "
				+	"WHEN 0 THEN 'LAST' "
				+	"WHEN 1 THEN 'LAST' "
				+	"WHEN 2 THEN 'FIRST' "
				+	"WHEN 3 THEN 'FIRST' "
				+ "END AS SORT_NULLS "
				+ "FROM generate_series(1, "
				+	"(SELECT indnatts FROM pg_index inline_pgi "
				+	"LEFT JOIN pg_class inline_pgc ON inline_pgi.indexrelid = inline_pgc.oid "
				+	"LEFT JOIN pg_namespace inline_nmspc ON inline_pgc.relnamespace = inline_nmspc.oid "
				+	"WHERE inline_nmspc.nspname = '").append(schemaName).append("' "
				+	"AND inline_pgc.relname = ? "
				+ 	") "
				+ ") as s(a) "
				+ "ORDER BY 1,2 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#correctIndexFunction(java.lang.String)
	 */
	public String correctQuotedFieldNames(String expression) {
		// nothing needs to be corrected in PostgreSQL
		return expression;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createTable(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)
	 */
	public String sqlObject_createTable(String catalogName, String schemaName, String tableName, ArrayList<String> columnNames, ArrayList<String> columnTypes, ArrayList<Boolean> columnNullables, ArrayList<String> columnDefaults) {
		StringBuffer sql = new StringBuffer();

		// prefix
		sql.append("CREATE TABLE ").append(schemaName).append(".").append(tableName).append(" (");

		// column list
		for (int i = 0; i < columnNames.size(); i++) {
			String columnName = columnNames.get(i);
			String dataType = columnTypes.get(i);
			boolean isNullable = columnNullables.get(i);
			String defaultValue = columnDefaults.get(i);
			String sqlNullable = "";
			if (! isNullable)
				sqlNullable = " NOT NULL";
			String sqlDefault = "";
			if (defaultValue!=null && !defaultValue.equalsIgnoreCase("null") && defaultValue.length()>0)
				sqlDefault = " DEFAULT " + defaultValue;
			if (i>0)
				sql.append(", ");
			sql.append(columnName).append(" ").append(dataType).append(sqlNullable).append(sqlDefault);
		}

		// suffix
		sql.append(")");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_dropTable(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObject_dropTable(String catalogName, String schemaName, String tableName) {
		StringBuffer sql = new StringBuffer();

		sql.append("DROP TABLE ").append(schemaName).append(".").append(tableName);

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createView(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObject_createView(String catalogName, String schemaName, String viewName, String viewDefinition) {
		StringBuffer sql = new StringBuffer();

		sql.append("CREATE OR REPLACE VIEW ") 
				.append(schemaName).append(".").append(viewName).append(" "
				+ "AS ").append(System.getProperty("line.separator")) 
				.append(viewDefinition).append(" ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getSQLDropView(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObject_dropView(String catalogName, String schemaName, String viewName) {
		StringBuffer sql = new StringBuffer();

		sql.append("DROP VIEW IF EXISTS ").append(schemaName).append(".").append(viewName).append(" CASCADE ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createFunction(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.lang.String)
	 */
	public String sqlObject_createFunction(String catalogName, String schemaName, String functionType, String functionName, String functionReturnType, boolean hasOutParameters, String functionLanguage, ArrayList<String> argDirs, ArrayList<String> argNames, ArrayList<String> argTypes, String bodyText) {
		StringBuffer sql = new StringBuffer();
		
		// prefix
		sql.append("CREATE OR REPLACE ") 
				.append(functionType).append(" ") 
				.append(schemaName).append(".").append(functionName).append(" (");
	
		// header
		for (int i=0; i<argNames.size(); i++) {
			String argName = argNames.get(i);
			String argType = argTypes.get(i);
			String argDir = argDirs.get(i);
			if (i>0)
				sql.append(", ");
			sql.append(argDir).append(" ").append(argName).append(" ").append(argType);
		}
		
		// middle
		StringBuffer returnExpr = new StringBuffer();
		if (!hasOutParameters)
			returnExpr.append("RETURNS ").append(functionReturnType).append(" ");
		sql.append(") ")
		.append(returnExpr)
		.append("LANGUAGE ").append(functionLanguage).append(" "
				+ "AS ").append(System.getProperty("line.separator"))
				.append("$BODY$ ").append(System.getProperty("line.separator"));

		// body
		sql.append(bodyText);
		
		// suffix
		sql.append(System.getProperty("line.separator")) 
				.append("$BODY$ ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_dropFunction(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObject_dropFunction(String catalogName, String schemaName, String functionType, String functionName, String functionSignature) {
		StringBuffer sql = new StringBuffer();

		// postgresql uses overloaded functions, so we need to identify the function to drop with its signature
		sql.append("DROP ").append(functionType).append(" IF EXISTS ").append(schemaName).append(".").append(functionSignature).append(" CASCADE ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createOperator(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, boolean)
	 */
	public String sqlObject_createOperator(String catalogName, String schemaName, String operatorName, String leftArg, String rightArg, String returnType, String functionName, String commutator, String negator, String restrictor, String joiner, boolean isHashable, boolean isMergeable) {
		StringBuffer sql = new StringBuffer();

		StringBuffer function = new StringBuffer();
		StringBuffer left = new StringBuffer();
		StringBuffer right = new StringBuffer();
		StringBuffer com = new StringBuffer();
		StringBuffer neg = new StringBuffer();
		StringBuffer res = new StringBuffer();
		StringBuffer join = new StringBuffer();
		StringBuffer hashes = new StringBuffer();
		StringBuffer merges = new StringBuffer();
		
		if (functionName!=null)
			function.append("PROCEDURE = ").append(schemaName).append(".").append(functionName);
		if (leftArg!=null)
			left.append(", LEFTARG = ").append(leftArg);
		if (rightArg!=null)
			right.append(", RIGHTARG = ").append(rightArg);
		if (commutator!=null)
			com.append(", COMMUTATOR = ").append(commutator);
		if (negator!=null)
			neg.append(", NEGATOR = ").append(negator);
		if (restrictor!=null)
			res.append(", RESTRICT = ").append(restrictor);
		if (joiner!=null)
			join.append(", JOIN = ").append(joiner);
		if (isHashable)
			hashes.append(", HASHES");
		if (isMergeable)
			merges.append(", MERGES");
		sql.append("CREATE OPERATOR ").append(schemaName).append(".").append(operatorName).append(" (")
			.append(function) 
			.append(left).append(right)
			.append(com).append(neg)
			.append(res).append(join)
			.append(hashes).append(merges)
			.append(") ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_dropOperator(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObject_dropOperator(String catalogName, String schemaName, String operatorName, String leftArg, String rightArg) {
		StringBuffer sql = new StringBuffer();

		String left = "NONE";
		if (leftArg!=null)
			left = leftArg;
		String right = "NONE";
		if (rightArg!=null)
			right = rightArg;
		sql.append("DROP OPERATOR IF EXISTS ").append(schemaName).append(".").append(operatorName).append(" "
				+ "(").append(left).append(", ").append(right).append(") "
				+ "CASCADE ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createTrigger(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObject_createTrigger(String catalogName, String schemaName, String triggerName, String tableName, String triggerType, String triggerEvent, String actionOrientation, String triggerCode) {
		StringBuffer sql = new StringBuffer();

		sql.append("CREATE TRIGGER ").append(triggerName).append(" ")
			.append(triggerType).append(" ")
			.append(triggerEvent).append(" "
			+ "ON ").append(schemaName).append(".").append(tableName).append(" ")
			.append(actionOrientation).append(" ")
			.append(triggerCode).append(" ");
	
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_dropTrigger(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObject_dropTrigger(String catalogName, String schemaName, String triggerName, String tableName) {
		StringBuffer sql = new StringBuffer();

		sql.append("DROP TRIGGER ").append(schemaName).append(".").append(triggerName)
				.append(" ON ").append(schemaName).append(".").append(tableName).append(" ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createSequence(java.lang.String, java.lang.String, java.lang.String, long, long, long, boolean, long, long)
	 */
	public String sqlObject_createSequence(String catalogName, String schemaName, String sequenceName, long min, long max, long incr, boolean isCycled, long cache, long start) {
		StringBuffer sql = new StringBuffer();
		
		String strCycle = null;
		if (isCycled)
			strCycle = "CYCLE";
		else
			strCycle = "NO CYCLE";

		sql.append("CREATE SEQUENCE ").append(schemaName).append(".").append(sequenceName).append(" "
				+ "INCREMENT BY ").append(incr).append(" "
				+ "MINVALUE ").append(min).append(" "
				+ "MAXVALUE ").append(max).append(" "
				+ "START WITH ").append(start).append(" "
				+ "CACHE ").append(cache).append(" ")
				.append(strCycle).append(" ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_dropSequence(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObject_dropSequence(String catalogName, String schemaName, String sequenceName) {
		StringBuffer sql = new StringBuffer();

		sql.append("DROP SEQUENCE ").append(schemaName).append(".").append(sequenceName).append(" ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createPrimaryKey(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, java.util.ArrayList)
	 */
	public String sqlObject_createPrimaryKey(String catalogName, String schemaName, String tableName, String keyName, boolean isDeferrable, boolean isDeferred, ArrayList<String> keyColumns) {
		StringBuffer sql = new StringBuffer();

		// prefix
		sql.append("ALTER TABLE ")
				.append(schemaName).append(".").append(tableName).append(" "
				+ "ADD CONSTRAINT ").append(keyName).append( " "
				+ "PRIMARY KEY (");

		// columns
		for (int i = 0; i < keyColumns.size() ;  i++) {
			String columnName = keyColumns.get(i);
			if (i>0)
				sql.append(", ");
			sql.append(columnName);
		}
		
		// suffix
		// (postgresql can not defer primary keys)
		sql.append(") ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createForeignKey(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String, boolean, boolean)
	 */
	public String sqlObject_createForeignKey(String catalogName, String schemaName, String keyName, String localTable, ArrayList<String> localColumns, String foreignTable, ArrayList<String> foreignColumns, String matchType, String onDelete, String onUpdate, boolean isDeferrable, boolean isDeferred) {
		StringBuffer sql = new StringBuffer();

		// prefix
		sql.append("ALTER TABLE ")
				.append(schemaName).append(".").append(localTable).append(" "
				+ "ADD CONSTRAINT ").append(keyName).append(" "
				+ "FOREIGN KEY (");

		// column lists
		StringBuffer localKeys = new StringBuffer();
		StringBuffer foreignKeys = new StringBuffer();
		for (int i = 0; i < localColumns.size(); i++) {
			String localKey = localColumns.get(i);
			String foreignKey = foreignColumns.get(i);
			if (i>0) {
				localKeys.append(", ");
				foreignKeys.append(", ");
			}
			localKeys.append(localKey);
			foreignKeys.append(foreignKey);
		}
		sql.append(localKeys).append(") REFERENCES ").append(foreignTable).append(" (").append(foreignKeys);
		
		// suffix
		StringBuffer matchClause = new StringBuffer();
		StringBuffer deleteClause = new StringBuffer();
		StringBuffer updateClause = new StringBuffer();
		String deferrable = "";
		String deferred = "";
		if (matchType!=null) {
			if (matchType.equalsIgnoreCase("NONE"))
				matchType = "SIMPLE";
			matchClause.append("MATCH ").append(matchType).append(" ");
		}
		if (onDelete!=null)
			deleteClause.append("ON DELETE ").append(onDelete).append(" ");
		if (onUpdate!=null)
			updateClause.append("ON UPDATE ").append(onUpdate).append(" ");
		if (isDeferrable) {
			deferrable = "DEFERRABLE ";
			if (isDeferred)
				deferred="INITIALLY DEFERRED ";
			else
				deferred="INITIALLY IMMEDIATE ";
		} else {
			deferrable = "NOT DEFERRABLE ";
		}
		sql.append(") ").append(matchClause).append(deleteClause).append(updateClause).append(deferrable).append(deferred);

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createCheck(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, boolean, boolean)
	 */
	public String sqlObject_createCheck(String catalogName, String schemaName, String tableName, String constraintName, ArrayList<String> expressions, boolean isDeferrable, boolean isDeferred) {
		StringBuffer sql = new StringBuffer();

		// prefix
		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" ADD CONSTRAINT ").append(constraintName).append(" CHECK (");
		
		// expressions
		for (int i = 0; i < expressions.size(); i++) {
			String expression = expressions.get(i);
			if (i>0)
				sql.append(", ");
			sql.append(expression);
		}
		
		// suffix
		// (postgresql can not defer check constraints)
		sql.append(") ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createUnique(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, boolean, boolean)
	 */
	public String sqlObject_createUnique(String catalogName, String schemaName, String tableName, String constraintName, ArrayList<String> columns, boolean isDeferrable, boolean isDeferred) {
		StringBuffer sql = new StringBuffer();

		// prefix
		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" ADD CONSTRAINT ").append(constraintName). append(" UNIQUE (");

		// column list
		for (int i = 0; i < columns.size(); i++) {
			String column = columns.get(i);
			if (i>0)
				sql.append(", ");
			sql.append(column);
		}

		// suffix
		// postgresql can not defer unique constraints
		sql.append(") ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_dropConstraint(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObject_dropConstraint(String catalogName, String schemaName, String constraintName, String tableName) {
		StringBuffer sql = new StringBuffer();

		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName)
				.append(" DROP CONSTRAINT ").append(constraintName).append(" ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createIndex(java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)
	 */
	public String sqlObject_createIndex(String catalogName, String schemaName, String tableName, boolean isUnique, String indexName, ArrayList<String> columnNames, ArrayList<String> directions, ArrayList<String> nullTreatments) {
		StringBuffer sql = new StringBuffer();
		
		// prefix
		String unique="";
		if (isUnique)
			unique = "UNIQUE";
		sql.append("CREATE ").append(unique).append(" INDEX ").append(indexName).append(" ON ").append(schemaName).append(".").append(tableName).append(" (");
	
		// columns
		for (int i = 0; i < columnNames.size(); i++) {
			String columnName = columnNames.get(i);
			String direction = directions.get(i);
			String nullTreatment = nullTreatments.get(i);
			StringBuffer nullExpression = new StringBuffer();
			if (i>0)
				sql.append(", ");
			if (nullTreatment!=null)
				nullExpression.append("NULLS ").append(nullTreatment).append(" ");
			sql.append(columnName).append(" ").append(direction).append(" ").append(nullExpression);
		}
		
		// suffix
		sql.append(") ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_dropIndex(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObject_dropIndex(String catalogName, String schemaName, String indexName) {
		StringBuffer sql = new StringBuffer();

		sql.append("DROP INDEX ").append(schemaName).append(".").append(indexName).append(" ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createTableColumn(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_createColumn(String catalogName, String schemaName, String tableName, String columnName, String dataType, boolean isNullable, String defaultValue) {
		StringBuffer sql = new StringBuffer();
		
		String sqlNullable = "";
		if (! isNullable)
			sqlNullable = " NOT NULL";
		
		StringBuffer sqlDefault = new StringBuffer();
		if (defaultValue!=null)
			sqlDefault.append(" DEFAULT ").append(defaultValue);

		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" "
				+ "ADD COLUMN ").append(columnName).append(" ") 
				.append(dataType).append(sqlDefault).append(sqlNullable).append(" ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_dropTableColumn(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_dropColumn(String catalogName, String schemaName, String tableName, String columnName) {
		StringBuffer sql = new StringBuffer();

		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" "
				+ "DROP COLUMN ").append(columnName).append(" ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_setTableColumnDefault(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_setColumnDefault(String catalogName, String schemaName, String tableName, String columnName, String defaultValue) {
		StringBuffer sql = new StringBuffer();

		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" "
				+ "ALTER COLUMN ").append(columnName).append(" "
				+ "SET DEFAULT ").append(defaultValue).append(" ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_dropTableColumnDefault(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_dropColumnDefault(String catalogName, String schemaName, String tableName, String columnName) {
		StringBuffer sql = new StringBuffer();

		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" "
				+ "ALTER COLUMN ").append(columnName).append(" "
				+ "DROP DEFAULT ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_setTableColumnNullable(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_setColumnNullable(String catalogName, String schemaName, String tableName, String columnName) {
		StringBuffer sql = new StringBuffer();

		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" "
				+ "ALTER COLUMN ").append(columnName).append(" "
				+ "DROP NOT NULL ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_setTableColumnNullable(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_dropColumnNullable(String catalogName, String schemaName, String tableName, String columnName) {
		StringBuffer sql = new StringBuffer();

		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" "
				+ "ALTER COLUMN ").append(columnName).append(" "
				+ "SET NOT NULL ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_prepareTableColumnNotNullable(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_prepareColumnNotNullable(String catalogName, String schemaName, String tableName, String columnName, String dataType, String defaultValue) {
		StringBuffer sql = new StringBuffer();

		sql.append("UPDATE ").append(schemaName).append(".").append(tableName).append(" "
				+ "SET ").append(columnName).append(" = ").append(defaultValue).append(" "
				+ "WHERE ").append(columnName).append(" IS NULL ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObjectDetail_modifyColumn(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_modifyColumnType(String catalogName, String schemaName, String tableName, String columnName, String dataType) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" "
				+ "ALTER COLUMN ").append(columnName).append(" "
				+ "TYPE ").append(dataType).append(" "
				+ "USING NULL ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObjectDetail_renameColumn(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_renameColumn(String catalogName, String schemaName, String tableName, String columnName, String newName) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" "
				+ "RENAME COLUMN ").append(columnName).append(" "
				+ "TO ").append(newName).append(" ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getSQLColumnTempCreate(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_createTemporaryColumn(String catalogName, String schemaName, String tableName, String columnName, String dataType) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" "
				+ "ADD COLUMN ").append(columnName).append(" ")
				.append(dataType).append(" ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObjectDetail_dropTemporaryColumn(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_dropTemporaryColumn(String catalogName, String schemaName, String tableName, String columnName) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" "
				+ "DROP COLUMN ").append(columnName).append(" ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObjectDetail_saveTemporaryColumn(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_saveTemporaryColumn(String catalogName, String schemaName, String tableName, String temporaryColumnName, String columnName, String dataType) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE ").append(schemaName).append(".").append(tableName).append(" "
				+ "SET ").append(temporaryColumnName).append(" = " 
				+ "CAST (").append(columnName).append(" AS ").append(dataType).append(") ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObjectDetail_restoreTemporaryColumn(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_restoreTemporaryColumn(String vendorName, String catalogName, String schemaName, String tableName, String temporaryColumnName, String columnName) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE ").append(schemaName).append(".").append(tableName).append(" "
				+ "SET ").append(columnName).append(" = ").append(temporaryColumnName).append(" ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObjectDetail_eraseColumn(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_eraseColumn(String catalogName, String schemaName, String tableName, String columnName) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE ").append(schemaName).append(".").append(tableName).append(" "
				+ "SET ").append(columnName).append(" = NULL ");
		
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sql_select(java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)
	 */
	public String sql_select(String catalogName, String schemaName, String tableName, String tableAlias, ArrayList<String> columnNames, ArrayList<String> aliasNames, ArrayList<String> joinTypes, ArrayList<String> joinTables, ArrayList<String> joinAliases, ArrayList<String> joinConditions, ArrayList<String> conditions, ArrayList<String> sortColumns, boolean isDistinct) {
		StringBuffer sql = new StringBuffer() ;
		
		// prefix
		sql.append("SELECT ");
		if (isDistinct)
			sql.append("DISTINCT ");
		
		// column list
		if (columnNames == null)
			sql.append("* ");
		else {
			for (int i = 0; i < columnNames.size(); i++) {
				if (i>0)
					sql.append(", ");
				sql.append(columnNames.get(i));
				if (aliasNames.get(i)!=null)
					sql.append(" AS ").append(aliasNames.get(i));
			}
			sql.append(" ");
		}
		
		// table
		sql.append("FROM ").append(schemaName).append(".").append(tableName).append(" ").append(tableAlias).append(" ");
		
		// JOIN clause
		if (joinTypes!=null && joinTypes.size()>0) {
			for (int i = 0; i < joinTypes.size(); i++) {
				String joinType = joinTypes.get(i);
				String joinTable = joinTables.get(i);
				String joinAlias = joinAliases.get(i);
				String joinCondition = joinConditions.get(i);
				if (sql!=null && sql.length()>0) {
					sql.append(joinType).append(" ").append(schemaName).append(".").append(joinTable).append(" ") 
						.append(joinAlias).append(" ON (").append(joinCondition).append(") ");
				}
			}
		}
		
		// conditions
		if (conditions!=null) {
			for (int i = 0; i < conditions.size(); i++) {
				if (i==0)
					sql.append("WHERE ");
				else
					sql.append("AND ");
				sql.append(conditions.get(i)).append(" ");
			}
		}
		
		// order
		if (sortColumns!=null) {
			for (int i = 0; i < sortColumns.size(); i++) {
				if (i==0)
					sql.append("ORDER BY ");
				else
					sql.append(", ");
				sql.append(sortColumns.get(i));
			}
			sql.append(" ");
		}
			
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sql_update(java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)
	 */
	public String sql_update(String catalogName, String schemaName, String tableName, String tableAlias, ArrayList<String> columnNames, ArrayList<String> values, ArrayList<String> conditions) {
		StringBuffer sql = new StringBuffer();

		for (int i = 0; i < columnNames.size(); i++) {
			if (i==0)
				sql.append("UPDATE ").append(schemaName).append(".").append(tableName).append(" ").append(tableAlias).append(" SET ");
			else
				sql.append(", ");
			sql.append(columnNames.get(i)).append(" = ").append(values.get(i));
		}
		sql.append(" ");

		StringBuffer condition = new StringBuffer();
		if (conditions!=null) {
			for (int i = 0; i < conditions.size(); i++) {
				if (i==0)
					condition.append("WHERE ");
				else
					condition.append("AND ");
				condition.append(conditions.get(i));
			}
		}

		if (sql!=null && sql.length()>0)
			sql.append(condition);

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sql_delete(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public String sql_delete(String catalogName, String schemaName, String tableName, String tableAlias, ArrayList<String> conditions, Integer daysOld) {
		StringBuffer sql = new StringBuffer();

		sql.append("DELETE FROM ").append(schemaName).append(".").append(tableName).append(" ").append(tableAlias).append(" ");
		
		// condition
		if (conditions!=null && conditions.size()>0) {
			for (int i = 0; i < conditions.size(); i++) {
				if (i==0)
					sql.append("WHERE ");
				else
					sql.append("AND ");
				sql.append(conditions.get(i)).append(" ");
			}
		}

		// age clause
		if (daysOld!=null) {
			if (conditions == null || conditions.size()==0)
				sql.append("WHERE ");
			else
				sql.append("AND ");
			sql.append("date_trunc('day', updated) < (date_trunc('day', now())::date - ").append(daysOld.toString()).append("::integer) ");
		}
			 
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sql_insert(java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, java.util.ArrayList)
	 */
	public String sql_insert(String catalogName, String schemaName, String tableName, ArrayList<String> columnNames, ArrayList<String> columnValues) {
		StringBuffer sql = new StringBuffer();
		StringBuffer sqlValues = new StringBuffer();

		for (int i = 0; i < columnNames.size(); i++) {
			if (i==0) {
				sql.append("INSERT INTO ").append(schemaName).append(".").append(tableName).append(" (");
				sqlValues = new StringBuffer();
			} else {
				sql.append(", ");
				sqlValues.append(", ");
			}
			sql.append(columnNames.get(i));
			sqlValues.append(columnValues.get(i));
		}

		if (sql!=null && sql.length()>0) {
			sql.append(") VALUES (").append(sqlValues).append(") ");
		}

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sql_insertFromTable(java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, java.util.ArrayList, java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.lang.String)
	 */
	public String sql_insertFromTable(String catalogName, String schemaName, String tableName, ArrayList<String> columnNames, ArrayList<String> columnValues, String sourceTableName, ArrayList<String> joinTypes, ArrayList<String> joinTables, ArrayList<String> joinConditions, String whereClause) {
		StringBuffer sql = new StringBuffer();
		
		// columns and values
		StringBuffer sqlColumns = new StringBuffer();
		StringBuffer sqlValues = new StringBuffer();
		if (columnNames!=null && columnNames.size()>0) {
			for (int i = 0; i < columnNames.size(); i++) {
				if (i>0) {
					sqlColumns.append(", ");
					sqlValues.append(", ");
				}
				sqlColumns.append(columnNames.get(i));
				sqlValues.append(columnValues.get(i));
			}
		}

		// INSERT clause
		sql.append("INSERT INTO ").append(schemaName).append(".").append(tableName).append(" (").append(sqlColumns).append(") ");

		
		// SELECT clause
		if (sql!=null && sql.length()>0) {
			sql.append("SELECT DISTINCT ").append(sqlValues).append(" "
					+ "FROM ").append(schemaName).append(".").append(sourceTableName).append(" t ");
		}
		
		// JOIN clause
		if (joinTypes!=null && joinTypes.size()>0) {
			for (int i = 0; i < joinTypes.size(); i++) {
				String joinType = joinTypes.get(i);
				String joinTable = joinTables.get(i);
				String joinCondition = joinConditions.get(i);
				if (sql!=null && sql.length()>0) {
					sql.append(joinType).append(" ").append(schemaName).append(".").append(joinTable).append(" " 
							+ "t").append(i).append(" ON (").append(joinCondition).append(") ");
				}
			}
		}
		
		// WHERE clause
		if (whereClause != null) { 
			if (sql!=null && sql.length()>0) {
				sql.append("WHERE ").append(whereClause).append(" ");
			}
		}
	
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlADAction_updateTerminology(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, boolean, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)
	 */
	public String sqlADAction_updateTerminology(
			String catalogName, String schemaName, String targetTableName,
			String sourceTableName, String targetTranslationName,
			String sourceTranslationName, ArrayList<String> joinTableNames,
			ArrayList<String> linkConditions,
			ArrayList<String> extraTableNames,
			ArrayList<String> extraConditions, boolean hasCentrallyMaintained,
			ArrayList<String> updateColumns, ArrayList<String> updateValues,
			ArrayList<String> updateConditions) {

		StringBuffer sql = new StringBuffer();
		StringBuffer whereClause = new StringBuffer();

		// UPDATE clause
		if (targetTranslationName==null) {
			// base synchronization
			sql.append("UPDATE ").append(schemaName).append(".").append(targetTableName).append(" tt ");
		} else {
			// translation synchronization
			sql.append("UPDATE ").append(schemaName).append(".").append(targetTranslationName).append(" ttl ");
		}

		// SET clause
		if ((updateColumns!=null && updateColumns.size()>0) && (updateValues!=null && updateValues.size()==updateColumns.size())) {
			for (int i=0; i<updateColumns.size(); i++) {
				if (i==0)
					sql.append("SET ");
				else
					sql.append(", ");
				sql.append(updateColumns.get(i)).append(" = ").append(updateValues.get(i));
			}
			sql.append(" ");
		}

		// FROM clause
		if (targetTranslationName==null) {
			// base synchronization
			sql.append("FROM ").append(schemaName).append(".").append(sourceTableName).append(" ts");
		} else {
			// translation synchronization
			sql.append("FROM ").append(schemaName).append(".").append(targetTableName).append(" tt, ")
			.append(schemaName).append(".").append(sourceTableName).append(" ts, ")
			.append(schemaName).append(".").append(sourceTranslationName).append(" tsl");
		}

		// JOIN tables
		if (joinTableNames!=null && joinTableNames.size()>0) {
			for (int index=0; index<joinTableNames.size(); index++) {
				sql.append(", ").append(schemaName).append(".").append(joinTableNames.get(index)).append(" tj").append(index);
			}
		}

		// EXTRA tables
		if (extraTableNames!=null && extraTableNames.size()>0) {
			for (int index=0; index<extraTableNames.size(); index++) {
				sql.append(", ").append(schemaName).append(".").append(extraTableNames.get(index)).append(" tx").append(index);
			}
		}
		sql.append(" ");


		// WHERE clause
		// JOIN conditions
		if (linkConditions!=null && linkConditions.size()>0) {
			for (int index = 0; index<linkConditions.size(); index++) {
				if (whereClause.length()>0)
					whereClause.append("AND ");
				whereClause.append(linkConditions.get(index));
			}
		}

		// EXTRA conditions
		if (extraConditions!=null && extraConditions.size()>0) {
			for (int index = 0; index<extraConditions.size(); index++) {
				if (whereClause.length()>0)
					whereClause.append("AND ");
				whereClause.append(extraConditions.get(index));
			}
		}

		// is centrally maintained?
		if (hasCentrallyMaintained) {
			if (whereClause.length()>0)
				whereClause.append("AND ");
			whereClause.append("tt.IsCentrallyMaintained = 'Y' ");
		}

		// update conditions
		if (updateConditions!=null && updateConditions.size()>0) {
			if (whereClause.length()>0)
				whereClause.append("AND (");
			else
				whereClause.append("(");	
			for (int i=0; i<updateConditions.size(); i++) {
				if (i>0)
					whereClause.append("OR ");
				whereClause.append(updateConditions.get(i));
			}
			whereClause.append(") ");
		}

		// append WHERE clause to SQL command
		if (whereClause.length()>0)
			sql.append("WHERE ").append(whereClause);

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlAction_purgeOrphans(java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)
	 */
	public String sqlAction_purgeOrphans(String catalogName, String schemaName,
			String localTableName, ArrayList<String> localColumnNames,
			ArrayList<String> foreignKeyNames,
			ArrayList<String> foreignTableNames,
			ArrayList<String> foreignColumnNames) {

		StringBuffer sql = new StringBuffer();

		String lastForeignKeyName = "";
		for (int i = 0; i < foreignColumnNames.size(); i++) {
			String foreignKeyName = foreignKeyNames.get(i);
			String foreignTableName = foreignTableNames.get(i);
			String foreignColName = foreignColumnNames.get(i);
			String localColName = localColumnNames.get(i);
			if (foreignKeyName.equalsIgnoreCase(lastForeignKeyName)) {
				sql.append(" AND ");
			} else {
				if (i==0) {
					sql.append("DELETE FROM ").append(schemaName).append(".").append(localTableName).append(" lcltbl WHERE "); 
				} else {
					sql.append(") OR ");
				}
				sql.append("NOT EXISTS (SELECT 1 FROM ").append(schemaName).append(".").append(foreignTableName).append( " frntbl WHERE ");
				lastForeignKeyName = foreignKeyName;
			}
			sql.append("lcltbl.").append(localColName).append(" = frntbl.").append(foreignColName);
		}

		if (sql!=null && sql.length()>0)
			sql.append(") ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlAction_dropDuplicates(java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList)
	 */
	public String sqlAction_dropDuplicates(String catalogName,
			String schemaName, String tableName,
			ArrayList<String> keyColumnNames) {
		
		StringBuffer sql = new StringBuffer();

		sql.append("DELETE FROM ").append(schemaName).append(".").append(tableName)
			.append(" t1 WHERE EXISTS (SELECT 1 FROM ")
			.append(schemaName).append(".").append(tableName)
			.append(" t2 WHERE ");
		
		for (int i = 0; i < keyColumnNames.size(); i++) {
			String columnName = keyColumnNames.get(i);
			if (i>0)
				sql.append("AND ");
			sql.append("t2.").append(columnName).append(" = t1.").append(columnName).append(" ");
		}
		
		sql.append(" AND t2.ctid < t1.ctid)");
		 
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlAction_enforceCheckConstraints(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlAction_enforceCheckConstraints(String catalogName, String schemaName, String tableName, String checkExpression) {
		StringBuffer sql = new StringBuffer();

		// extract column name, operator, and value
		String pattern = "(?i).*?(\\b\\w*\\b).*?([<=>!]+|(\\bNOT\\s+)?LIKE\\b).*?('\\w*'|\\d+|\\bNULL\\b).*";

		// extract the last word before first operator as column name
		String columnName = checkExpression.replaceAll(pattern, "$1");
		if (columnName.equalsIgnoreCase(checkExpression))
			columnName=null;

		// extract the first operator
		String operator = checkExpression.replaceAll(pattern, "$2").toUpperCase();
		if (operator.equalsIgnoreCase(checkExpression))
			operator=null;

		// extract the first string or number or NULL after first operator as value
		String value = checkExpression.replaceAll(pattern, "$4");
		if (value.equalsIgnoreCase(checkExpression))
			value=null;

		// only continue if extraction of column name, operator and value was successful
		if (columnName!=null && operator!=null && value!=null) {
			// we can only determine default values for equality operators
			// (< or > are permissible only as <= or >=)
			if (operator.contains("=") || operator.contains("LIKE")) {
				// we can not dtermine default values for NOT operators
				// (!= or NOT LIKE are not permissable)
				if (! (operator.contains("!") || operator.contains("NOT"))) {
					// build the sql string
					sql.append("UPDATE ").append(schemaName).append(".").append(tableName).append(" "
						+ "SET ").append(columnName).append(" = ").append(value).append(" "
						+ "WHERE NOT (").append(checkExpression).append(") ");
				}
			}
		}

		if (sql!=null && sql.length()>0)
			return sql.toString();
		else
			return null;
	}

}
