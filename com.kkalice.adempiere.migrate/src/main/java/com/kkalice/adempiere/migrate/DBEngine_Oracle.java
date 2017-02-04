/*
 * Name:		DBEngine_Oracle.java
 * Description:	Oracle compatibility class
 * Created:		Nov 29, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 *
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBEngine_Oracle.java
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Oracle compatibility class
 * @author Stefan Christians
 */
public class DBEngine_Oracle implements DBEngineInterface {

	// database identification

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getVendorNames()
	 */
	public List<String> getVendorNames() {
		return Arrays.asList("Oracle", "OracleXE");
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
		return Arrays.asList("anonymous", "ctxsys", "dbsnmp", "dip", "dmsys", "exfsys",
				"lbacsys", "mddata", "mdsys", "mgmt_view", "ordplugins", "ordsys", "outln",
				"si_informtn_schema", "sys", "sysman", "system", "tsmsys", "wmsys", "xdb");
	}


	// database access

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDriver(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine)
	 */
	public String getDBDriver() {
		return "oracle.jdbc.OracleDriver";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDBPort(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String)
	 */
	public String getDBPort() {
		return "1521";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDBName(java.lang.String)
	 */
	public String getDBName(String vendorName) {
		// in oracle, the usual database name depends on the product
		String dbName = "orcl";

		if (vendorName!=null && vendorName.length()>0) {
			if (vendorName.toUpperCase().contains("XE")) {
				dbName = "xe";
			}
		}

		return dbName;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#isSourceAndTargetSameDBName()
	 */
	public boolean isSourceAndTargetSameDBName() {
		// in oracle, reference and target are in the same databases
		return true;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDBUrl(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getDBUrl(String host, String port, String name) {

		// oracle needs the database name to connect
		if (name==null || name.length()==0)
			return null;

		if (host==null || host.length()==0)
			host="localhost";

		if (port==null || port.length()==0)
			port="1521";

		// check if this is an sid (dbname.domain.com)
		// or a service name (dbname) -- the format of the URL differs
		StringBuffer url = new StringBuffer();
		if (name.contains(".")) {
			// this is an SID
			url.append("jdbc:oracle:thin:").append("@//").append(host).append(":").append(port).append("/").append(name);
		} else {
			// this is a service name
			url.append("jdbc:oracle:thin:").append("@").append(host).append(":").append(port).append(":").append(name);
		}

		return url.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDBDefaultSystemUser()
	 */
	public String getDBSystemUser() {
		return "system";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDBDefaultSystemPassword()
	 */
	public String getDBSystemPassword() {
		return "manager";
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDBSchemaOrUser(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String getDBSchemaOrUser(String schemaName, String user) {
		// in oracle, schema should be same as user name
		return user;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getSystemOrNormalUser(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String getDBSystemOrNormalUser(String normalUser, String systemUser) {
		// in oracle, the system user is required for privileged operations
		return systemUser;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getSystemOrNormalUser(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String getDBSystemOrNormalPassword(String normalPasswd, String systemPasswd) {
		// in oracle, the system user is required for privileged operations
		return systemPasswd;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDBMaxIdentifierLength()
	 */
	public int getDBMaxIdentifierLength() {
		// maximum length of identifiers is 30 characters
		return 30;
	}


	// data type translation

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#disambiguateDataType(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String)
	 */
	public String disambiguateDataType(String dataType) {
		String dt = dataType;
		if (dt.equalsIgnoreCase("RECORD")) {
			// RECORD was already in use by postgres with a different meaning,
			// so rename it to imaginary type COMPOSITE in Oracle
			dt = "COMPOSITE";
		}
		return dt;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#getDataType(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, int, int, int)
	 */
	public String getDataType(int dataTypeID, int size, int scale) {
		String result = null;

		switch (dataTypeID) {
		case DBEngine.SMALLINT:
		case DBEngine.INT2:
			result = "NUMBER (4)";
			break;
		case DBEngine.INTEGER:
		case DBEngine.INT4:
		case DBEngine.INT:
		case DBEngine.SERIAL:
		case DBEngine.SERIAL4:
			result = "NUMBER (10)";
			break;
		case DBEngine.PLS_INTEGER:
			result = "PLS_INTEGER";
			break;
		case DBEngine.BINARY_INTEGER:
			result = "BINARY_INTEGER";
			break;
		case DBEngine.NATURAL:
			result = "NATURAL";
			break;
		case DBEngine.POSITIVE:
			result = "POSITIVE";
			break;
		case DBEngine.SIMPLE_INTEGER:
			result = "SIMPLE_INTEGER";
			break;
		case DBEngine.NATURALN:
			result = "NATURALN";
			break;
		case DBEngine.POSITIVEN:
			result = "POSITIVEN";
			break;
		case DBEngine.MONEY:
		case DBEngine.BIGINT:
		case DBEngine.INT8:
		case DBEngine.BIGSERIAL:
		case DBEngine.SERIAL8:
			result = "NUMBER (19)";
			break;
		case DBEngine.NUMERIC:
		case DBEngine.DECIMAL:
		case DBEngine.DEC:
		case DBEngine.NUMBER:
			result = "NUMBER";
			if (size!=0) {
				result = new StringBuffer("NUMBER (").append(size).append(")").toString();
				if (scale != -1 && scale !=0)
					result = new StringBuffer("NUMBER (").append(size).append(",").append(scale).append(")").toString();
				if (scale == -1)
					result = "NUMBER";
			}
			break;
		case DBEngine.REAL:
		case DBEngine.FLOAT4:
			result = "real";
			break;
		case DBEngine.BINARY_FLOAT:
			result = "BINARY_FLOAT";
			break;
		case DBEngine.DOUBLE_PRECISION:
		case DBEngine.FLOAT8:
		case DBEngine.FLOAT:
			result = "FLOAT";
			break;
		case DBEngine.BINARY_DOUBLE:
			result = "BINARY_DOUBLE";
			break;
		case DBEngine.CHAR:
		case DBEngine.CHARACTER:
			if (size!=0)
				result = new StringBuffer("CHAR (").append(size).append(")").toString();
			else
				result = "CHAR";
			break;
		case DBEngine.NCHAR:
			if (size!=0)
				result = new StringBuffer("NCHAR (").append(size).append(")").toString();
			else
				result = "NCHAR";
			break;
		case DBEngine.NAME:
			result = "NCHAR (64)";
			break;
		case DBEngine.VARCHAR:
		case DBEngine.CHARACTER_VARYING:
		case DBEngine.CHAR_VARYING:
		case DBEngine.VARCHAR2:
		case DBEngine.STRING:
		case DBEngine.CIDR:
		case DBEngine.INET:
		case DBEngine.MACADDR:
			if (size!=0)
				result = new StringBuffer("VARCHAR2 (").append(size).append(")").toString();
			else
				result = "VARCHAR2";
			break;
		case DBEngine.NVARCHAR:
		case DBEngine.NCHAR_VARYING:
		case DBEngine.NATIONAL_CHAR_VARYING:
		case DBEngine.NATIONAL_CHARACTER_VARYING:
		case DBEngine.NVARCHAR2:
			if (size!=0)
				result = new StringBuffer("NVARCHAR2 (").append(size).append(")").toString();
			else
				result = "NVARCHAR2";
			break;
		case DBEngine.LONG:
		case DBEngine.TEXT:
		case DBEngine.CLOB:
		case DBEngine.NCLOB:
			result = "NCLOB";
			break;
		case DBEngine.MLSLABEL:
			result = "MLSLABEL";
			break;
		case DBEngine.RAW:
		case DBEngine.LONG_RAW:
		case DBEngine.BYTEA:
		case DBEngine.BLOB:
			result = "BLOB";
			break;
		case DBEngine.BFILE:
			result = "BFILE";
			break;
		case DBEngine.DATE:
		case DBEngine.TIME:
		case DBEngine.TIME_WITHOUT_TIME_ZONE:
			result = "DATE";
			break;
		case DBEngine.TIMESTAMP:
		case DBEngine.TIMESTAMP_WITHOUT_TIME_ZONE:
			result = "TIMESTAMP";
			break;
		case DBEngine.TIMESTAMPTZ:
		case DBEngine.TIMESTAMP_WITH_TIME_ZONE:
			result = "TIMESTAMP WITH TIME ZONE";
			break;
		case DBEngine.TIMESTAMP_WITH_LOCAL_TIME_ZONE:
			result = "TIMESTAMP WITH LOCAL TIME ZONE";
			break;
		case DBEngine.INTERVAL:
		case DBEngine.INTERVAL_DAY_TO_SECOND:
			result = "INTERVAL DAY TO SECOND";
			break;
		case DBEngine.INTERVAL_YEAR_TO_MONTH:
			result = "INTERVAL YEAR TO MONTH";
			break;
		case DBEngine.BOOLEAN:
		case DBEngine.BOOL:
			result = "BOOLEAN";
			break;
		case DBEngine.SIGNTYPE:
			result = "SIGNTYPE";
			break;
		case DBEngine.SDO_GEOMETRY:
			result = "SDO_GEOMETRY";
			break;
		case DBEngine.SDO_RASTER:
			result = "SDO_RASTER";
			break;
		case DBEngine.URIType:
			result = "URITYPE";
			break;
		case DBEngine.DBURIType:
			result = "DBURITYPE";
			break;
		case DBEngine.HTTPURIType:
			result = "HTTPURITYPE";
			break;
		case DBEngine.XDBURIType:
			result = "XDBURITYPE";
			break;
		case DBEngine.TABLE:
			result = "TABLE";
			break;
		case DBEngine.VARRAY:
			result = "VARRAY";
			break;
		case DBEngine.COMPOSITE:
			result = "RECORD";
			break;
		case DBEngine.ROWID:
			result = "ROWID";
			break;
		case DBEngine.UROWID:
			result = "UROWID";
			break;
		case DBEngine.REF_CURSOR:
			result = "REF CURSOR";
			break;
		case DBEngine.REF:
			result = "REF";
			break;
		case DBEngine.ANY:
		case DBEngine.SYS_ANYDATA:
			result = "SYS.ANYDATA";
			break;
		case DBEngine.ANYELEMENT:
		case DBEngine.SYS_ANYTYPE:
			result = "SYS.ANYTYPE";
			break;
		case DBEngine.ANYENUM:
		case DBEngine.SYS_ANYDATASET:
			result = "SYS.ANYDATASET";
			break;
		case DBEngine.ORDAUDIO:
			result = "ORDAUDIO";
			break;
		case DBEngine.ORDDOC:
			result = "ORDDOC";
			break;
		case DBEngine.ORDIMAGE:
			result = "ORDIMAGE";
			break;
		case DBEngine.ORDIMAGESIGNATURE:
			result = "ORDIMAGESIGNATURE";
			break;
		case DBEngine.SI_AVERAGECOLOR:
			result = "SI_AVERAGECOLOR";
			break;
		case DBEngine.SI_COLOR:
			result = "SI_COLOR";
			break;
		case DBEngine.SI_COLORHISTOGRAM:
			result = "SI_COLORHISTOGRAM";
			break;
		case DBEngine.SI_FEATURELIST:
			result = "SI_FATURELIST";
			break;
		case DBEngine.SI_POSITIONALCOLOR:
			result = "SI_POSITIONALCOLOR";
			break;
		case DBEngine.SI_STILLIMAGE:
			result = "SI_STILLIMAGE";
			break;
		case DBEngine.SI_TEXTURE:
			result = "SI_TEXTURE";
			break;
		case DBEngine.ORDVIDEO:
			result = "ORDVIDEO";
			break;
		default:
			result = null;
			break;
		}

		return result;
	}


	// expression translation

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateExpression(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String translateExpression(String sourceVendorName, String expression) {
		String result = expression;

		if (sourceVendorName.contains("POSTGRES")) {

			// translate expressions from postgresql

			// now() becomes sysdate
			result = result.replaceAll("(?i)now\\s*\\(\\)", "SYSDATE");

			// omit ::typecasts
			result = result.replaceAll("(?i)::\\w*(\\s+(varying|precision|(with|without) time zone))?", "");

			// =ANY(ARRAY[]) becomes IN()
			result = result.replaceAll("(?i)=\\s*ANY\\s*\\(\\s*ARRAY\\s*\\[(.*?)\\]\\s*\\)", "IN ($1)");
			result = result.replaceAll("(?i)<>\\s*ALL\\s*\\(\\s*ARRAY\\s*\\[(.*?)\\]\\s*\\)", "NOT IN ($1)");

		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateUnnamedParameter(int)
	 */
	public String translateUnnamedParameter(int paramNum) {
		// oracle requires parameters to be named
		return new StringBuffer("v").append(paramNum).toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#normalizeColumnName(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String)
	 */
	public String normalizeColumnName(String columnName) {
		// oracle understands when key words are used as column names
		return columnName;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#normalizeColumnValue(int)
	 */
	public String normalizeColumnValue(int dataTypeID) {
		// use zero
		String result = "0";
		// space for char types (oracle treats empty string like null)
		if (dataTypeID >= DBEngine.CHARTYPE_START && dataTypeID <= DBEngine.CHARTYPE_END)
			result = "' '";
		// or epoch for dates
		else if (dataTypeID >= DBEngine.DATETYPE_START && dataTypeID <= DBEngine.DATETYPE_END)
			result = "TO_DATE('1970-01-01','YYYY-MM-DD')";
		// or all zero for times
		else if (dataTypeID >= DBEngine.TIMETYPE_START && dataTypeID <= DBEngine.TIMETYPE_END)
			result = "TO_DATE('00:00:00','HH24:MI:SS')";
		// or epoch for timestamps
		else if (dataTypeID >= DBEngine.TIMESTAMPTYPE_START && dataTypeID <= DBEngine.TIMESTAMPTYPE_END)
			result = "TO_DATE('1970-01-01 00:00:00','YYYY-MM-DD HH24:MI:SS')";
		return result;
	}


	// function translation

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateFunctionLanguage(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String translateFunctionLanguage(String sourceVendorName, String functionLanguage) {
		String result = functionLanguage;

		if (sourceVendorName.contains("POSTGRES")) {

			// translate function languages from postgresql

			// replace plpgsql with PL/SQL
			if (functionLanguage.equalsIgnoreCase("plpgsql"))
				result = "PL/SQL";

			// replace PL/Java with Java
			else if (functionLanguage.equalsIgnoreCase("java"))
				result = "Java";

		}

		return result;
	}


	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateFunctionType(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String translateFunctionType(String sourceVendorName, String functionType, String functionReturnType) {
		String result = functionType;

		if (sourceVendorName.contains("ORACLE"))  {

			// adjust function type within oracle
			// (keep previous type if it is not null)
			if (functionType==null || functionType.length()==0 || functionType.equalsIgnoreCase("null")) {
				if (functionReturnType==null
						|| functionReturnType.length()==0
						|| functionReturnType.equalsIgnoreCase("null")
						|| functionReturnType.equalsIgnoreCase("void")
						|| functionReturnType.equalsIgnoreCase("trigger"))
					result = "PROCEDURE";
				else
					result = "FUNCTION";
			}

		} else {

			// translate function type from other databases

			// whether FUNCTION or PROCEDURE depends on return type
			if (functionReturnType==null
					|| functionReturnType.length()==0
					|| functionReturnType.equalsIgnoreCase("null")
					|| functionReturnType.equalsIgnoreCase("void")
					|| functionReturnType.equalsIgnoreCase("trigger"))
				result = "PROCEDURE";
			else
				result = "FUNCTION";
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateFunctionReturnType(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String translateFunctionReturnType(DBEngine dbEngine, String sourceVendorName, String functionReturnType) {
		String result = functionReturnType;

		if (sourceVendorName.contains("POSTGRES")) {

			// translate function return types from postgresql

			// returntype only if not null or void
			if (functionReturnType==null
					|| functionReturnType.length()==0
					|| functionReturnType.equalsIgnoreCase("null")
					|| functionReturnType.equalsIgnoreCase("void")
					|| functionReturnType.equalsIgnoreCase("trigger"))
				result = null;
			else
				result = dbEngine.translateDataType(sourceVendorName, getVendorNames().get(0), functionReturnType, 0, 0);
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateFunctionBodyFull(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String translateFunctionBodyFull(DBEngine dbEngine, String sourceVendorName, String sourceSchemaName, String functionLanguage, String functionReturnType, String functionBodyText) {


		if (sourceVendorName.contains("POSTGRES")) {

			// translate function from postgresql to oracle

			// TODO full translation of functions is not implemented yet
			// call stub translation instead
			functionBodyText = translateFunctionBodyStub(dbEngine, sourceVendorName, functionLanguage, functionReturnType, functionBodyText);

		} else if (sourceVendorName.contains("ORACLE")) {

			// translate function from oracle to oracle

			// nothing to do
		}

		return functionBodyText;

	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateFunctionBodyStub(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String translateFunctionBodyStub(DBEngine dbEngine, String sourceVendorName, String functionLanguage, String functionReturnType, String functionBodyText) {

		// comment out the text
		if (functionBodyText!=null)
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
				retVal = "      RETURN TO_DATE('1970-01-01','YYYY-MM-DD');";
			// return all zero for times
			else if (dataType >= DBEngine.TIMETYPE_START && dataType <= DBEngine.TIMETYPE_END)
				retVal = "      RETURN TO_DATE('00:00:00','HH24:MI:SS');";
			// return epoch for timestamps
			else if (dataType >= DBEngine.TIMESTAMPTYPE_START && dataType <= DBEngine.TIMESTAMPTYPE_END)
				retVal = "      RETURN TO_DATE('1970-01-01 00:00:00','YYYY-MM-DD HH24:MI:SS');";
			// return nothing for triggers
			else if (dataType == DBEngine.TRIGGER)
				retVal = "      RETURN;";
			// otherwise return number zero
			else
				retVal = "      RETURN 0;";
		}

		stub.append(System.getProperty("line.separator"))
		.append("-- Migration Stub Start --").append(System.getProperty("line.separator"))
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
	public String translateOperator(String sourceVendorName, String operatorName) {
		String result = operatorName;

		if (sourceVendorName.contains("POSTGRES")) {
			// translate operator names from postgresql
			result = result.replaceAll("\\+", "PLUS");
			result = result.replaceAll("-", "SUBTRACT");
			result = result.replaceAll("\\*", "TIMES");
			result = result.replaceAll("\\/", "DIVIDED");
			result = result.replaceAll("<>", "NOTEQUAL");
			result = result.replaceAll("><", "NOTEQUAL");
			result = result.replaceAll("<", "LESS");
			result = result.replaceAll(">", "GREATER");
			result = result.replaceAll("=", "EQUAL");
			result = result.replaceAll("~", "LIKE");
			result = result.replaceAll("!", "NOT");
			result = result.replaceAll("@", "AT");
			result = result.replaceAll("#", "POUND");
			result = result.replaceAll("%", "MOD");
			result = result.replaceAll("\\^", "XOR");
			result = result.replaceAll("&", "AND");
			result = result.replaceAll("\\|", "OR");
			result = result.replaceAll("`", "BACKTICK");
			result = result.replaceAll("\\?", "QUESTION");
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#isTriggerContainsInlineCode(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine)
	 */
	public boolean isTriggerContainsInlineCode() {
		// oracle triggers can contain inline code
		return true;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateTriggerType(java.lang.String, java.lang.String)
	 */
	public String translateTriggerType(String sourceVendorName, String triggerType) {

		if (sourceVendorName.contains("ORACLE")) {
			// translate trigger types from oracle to oracle
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
			actionOrientation = "";

		return actionOrientation;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateTriggerFunction(java.lang.String, java.lang.String)
	 */
	public String translateTriggerFunction(String sourceVendorName, String triggerFunction) {

		if (sourceVendorName.contains("POSTGRES")) {
			// translate trigger function from postgresql to oracle
			triggerFunction = triggerFunction.replaceAll("^EXECUTE PROCEDURE ", "");
		}

		return triggerFunction;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateTriggerCode(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String translateTriggerCode(String sourceVendorName, String actionType, String triggerName, String triggerFunction) {
		StringBuffer codeExpr = new StringBuffer();

		if (sourceVendorName.contains("ORACLE")) {
			// translate trigger code from oracle to oracle
			if (actionType.equalsIgnoreCase("CALL"))
				codeExpr.append("CALL ").append(triggerFunction);
			else
				codeExpr.append(triggerFunction);
		} else if (sourceVendorName.contains("POSTGRES")) {
			// translate trigger code from postgresql to oracle
			codeExpr.append("CALL ").append(triggerFunction);
		}

		return codeExpr.toString();
	}



	// view translation

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateViewDefinitionFull(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String translateViewDefinitionFull(DBEngine dbEngine, String sourceVendorName, String sourceSchemaName, String targetSchemaName, String viewDefinition) {

		if (sourceVendorName.contains("POSTGRES")) {

			// translate view from postgresql to oracle

			// sorry, but we need to remove comments to keep parsing manageable
			viewDefinition = viewDefinition.replaceAll("(?m)--.*$", "");

			// 'LIMIT X' becomes 'AND ROWNUM<=X'
			viewDefinition = viewDefinition.replaceAll("(?i)\\bLIMIT\\s+(\\d+)", "AND ROWNUM<=$1");

			// generate_series gets replaced with ROWNUM iterator
			StringBuffer sb = new StringBuffer();
			ArrayList<String> tempRowTables = new ArrayList<String> ();
			Pattern regex = Pattern.compile("\\(\\s*SELECT\\s+s.a\\s+AS\\s+(\\w+)\\s+FROM\\s+generate_series\\(1,\\s*(\\d+)\\)\\s+(AS\\s+)?s\\(a\\)\\)\\s+(AS\\s+)?(temp\\w+)", Pattern.CASE_INSENSITIVE);
			Matcher matcher = regex.matcher(viewDefinition);
			while (matcher.find()) {
				String tempRowTable = matcher.group(5);
				if (! tempRowTables.contains(tempRowTable))
					tempRowTables.add(tempRowTable);
				matcher.appendReplacement(sb, "(SELECT ROWNUM $1 FROM ALL_OBJECTS WHERE ROWNUM <= $2 ORDER BY ROWNUM)");
			}
			matcher.appendTail(sb);
			viewDefinition = sb.toString();
			// remove series table names
			for (String tempRowTable : tempRowTables) {
				String pattern = new StringBuffer(tempRowTable).append("\\.").toString();
				viewDefinition = viewDefinition.replaceAll(pattern, "");
			}

			// now() becomes sysdate
			viewDefinition = viewDefinition.replaceAll("(?i)now\\s*\\(\\)", "SYSDATE");

			// =ANY(ARRAY[]) becomes IN()
			viewDefinition = viewDefinition.replaceAll("(?i)=\\s*ANY\\s*\\(\\s*ARRAY\\s*\\[(.*?)\\]\\s*\\)", "IN ($1)");
			viewDefinition = viewDefinition.replaceAll("(?i)<>\\s*ALL\\s*\\(\\s*ARRAY\\s*\\[(.*?)\\]\\s*\\)", "NOT IN ($1)");

			// ~~ becomes LIKE
			viewDefinition = viewDefinition.replaceAll("\\s!~~\\s", " NOT LIKE ");
			viewDefinition = viewDefinition.replaceAll("\\s~~\\s", " LIKE ");

			// omit ::typecasts
			//viewDefinition = viewDefinition.replaceAll("(?i)::\\w*(\\s+(varying|precision|(with|without) time zone))?", "");
			viewDefinition = viewDefinition.replaceAll("(?i)::\\w*(\\s+(varying|precision|(with|without) time zone))?(\\(\\d*?\\,?\\d*?\\))?", "");

			// typecast strings in CASE statements
			viewDefinition = viewDefinition.replaceAll("(?i)WHEN\\s+'", "WHEN N'");

			//  numeric in postgres is number in oracle
			viewDefinition = viewDefinition.replaceAll("(?i)AS NUMERIC\\b", "AS NUMBER");

			// remove unnecessary parentheses
			viewDefinition = viewDefinition.replaceAll("\\(([\\.\\w]+)\\)", "$1");

			// oracle has add_months function instead of + operator
			// (first remove parentheses enclosing factor and '1 month' group)
			viewDefinition = viewDefinition.replaceAll("\\(([\\(\\)\\.\\w]+\\s*\\*\\s*'1 mon(th)?'\\s*)\\)", "$1");
			viewDefinition = viewDefinition.replaceAll("(?i)\\(?([\\(\\)\\.\\w]+)\\s*\\+\\s*([\\(\\)\\.\\w]+)\\s*\\*\\s*'1 mon'\\)?", "ADD_MONTHS($1, $2)");

			// oracle has one TRUNC function for both numbers and dates
			viewDefinition = viewDefinition.replaceAll("(?i)\\bDATE_TRUNC\\s?\\('day',", "TRUNC (");
			viewDefinition = viewDefinition.replaceAll("(?i)\\bDATE_TRUNC\\s?\\('month',\\s*(.*?(\\(.*?\\))*?)\\)", "TRUNC ($1, 'MM')");

			// user-defined operators in postgres
			// are probably standard symbols in oracle
			viewDefinition = viewDefinition.replaceAll("(?i)\\bOPERATOR\\s*\\((.*?)\\)", "$1");

			// remove schema qualifiers
			viewDefinition = viewDefinition.replaceAll(new StringBuffer("(?i)").append(sourceSchemaName).append("\\.").toString(), "");

			// remove final semicolon
			viewDefinition = viewDefinition.trim().replaceAll(";$", "");

			// oracle needs FROM keyword
			if (! viewDefinition.toUpperCase().contains(" FROM "))
				viewDefinition = new StringBuffer(viewDefinition).append(" FROM dual").toString();

		} else if (sourceVendorName.contains("ORACLE")) {

			// translate view from oracle to oracle

			// nothing to do
		}

		return viewDefinition;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#translateViewDefinitionStub(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String translateViewDefinitionStub(String sourceVendorName, String viewDefinition) {

//		// comment out actual view definition
//		viewDefinition = viewDefinition.replaceAll("(?m)^", "-- migrate:  ");
//
//		StringBuffer stub = new StringBuffer(viewDefinition);
//
//		// add stub
//		stub.append(System.getProperty("line.separator")).append("-- Migration Stub Start --").append(System.getProperty("line.separator"));
//		stub.append("SELECT 1 AS stub FROM dual");
//		stub.append(System.getProperty("line.separator")).append("-- Migration Stub End --").append(System.getProperty("line.separator"));

		// oracle does not store view definitions as original source code, so comments get lost.
		// to remember the original source code, we use it as return value by the stub.
		
		// escape single quotes in original view definition
		viewDefinition = viewDefinition.replaceAll("'", "''");

		// create stub
		StringBuffer stub = new StringBuffer("SELECT '")
			.append(System.getProperty("line.separator"))
			.append(viewDefinition)
			.append(System.getProperty("line.separator"))
			.append("' AS stub FROM dual");

		return stub.toString();
	}


	// SQL compatibility

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlCreateSchema(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlAdmin_createSchema(int step, String catalogName, String schemaName, String passwd) {

		StringBuffer sql = new StringBuffer();

		switch (step) {
		case 0:
			sql.append("CREATE USER ").append(schemaName).append(" IDENTIFIED BY ").append(passwd).append(" DEFAULT TABLESPACE USERS TEMPORARY TABLESPACE TEMP PROFILE DEFAULT ACCOUNT UNLOCK");
			break;
		case 1:
			sql.append("GRANT CONNECT TO ").append(schemaName);
			break;
		case 2:
			sql.append("GRANT DBA TO ").append(schemaName);
			break;
		case 3:
			sql.append("GRANT RESOURCE TO ").append(schemaName);
			break;
		case 4:
			sql.append("GRANT UNLIMITED TABLESPACE TO ").append(schemaName);
			break;
		case 5:
			sql.append("ALTER USER ").append(schemaName).append(" DEFAULT ROLE CONNECT, RESOURCE, DBA");
			break;
		case 6:
			sql.append("GRANT CREATE TABLE TO ").append(schemaName);
			break;
		}

		if (sql.length()>0)
			return sql.toString();
		else
			return null;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlDropSchema(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, int, java.lang.String, java.lang.String)
	 */
	public String sqlAdmin_dropSchema(int step, String catalogName, String schemaName) {
		if (step>0)
			return null;
		return new StringBuffer().append("DROP USER ").append(schemaName).append(" CASCADE").toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlAdmin_connectSchema(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, int, java.lang.String, java.lang.String)
	 */
	public String sqlAdmin_connectSchema(int step, String catalogName, String schemaName) {
		// oracle user only has his own schema, so nothing needs to be done
		return null;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlAdmin_optimizeDatabase(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, int, java.lang.String, java.lang.String)
	 */
	public String sqlAdmin_optimizeDatabase(int step, String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		switch (step) {
		case 0:
			// recompile invalid objects
			sql.append("BEGIN DBMS_UTILITY.compile_schema(schema => '").append(schemaName.toUpperCase()).append("'); END; ");
			break;
		case 1:
			// correct data file sizing (from AfterImport.sql script)
			sql.append("DECLARE "
					+ "CURSOR Cur_TS IS "
					+ "SELECT FILE_NAME, Tablespace_Name, Bytes/1024/1024 as MB "
					+ "FROM DBA_DATA_FILES "
					+ "WHERE (TABLESPACE_NAME='USERS' AND BYTES < 100*1024*1024) "
					+ "OR (TABLESPACE_NAME='INDX' AND BYTES < 100*1024*1024) "
					+ "OR (TABLESPACE_NAME='TEMP' AND BYTES < 100*1024*1024); "
					+ "v_CMD VARCHAR2(300); "
					+ "BEGIN "
					+ "FOR ts IN Cur_TS LOOP "
					+ "v_CMD := 'ALTER DATABASE DATAFILE ''' || ts.FILE_NAME || ''' RESIZE 100M'; "
					+ "EXECUTE IMMEDIATE v_CMD; "
					+ "v_CMD := 'ALTER DATABASE DATAFILE ''' || ts.FILE_NAME || ''' AUTOEXTEND ON NEXT 10M MAXSIZE UNLIMITED'; "
					+ "EXECUTE IMMEDIATE v_CMD; "
					+ "END LOOP; "
					+ "END; ");
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

		// no special preparation steps required for ORACLE
		switch (step) {
		case 0:
			sql.append("");
			break;
		case 1:
			sql.append("");
			break;
		}

		if (sql.length()>0)
			return sql.toString();
		else
			return null;
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_availableDatabases()
	 */
	public String sqlMetadata_availableDatabases() {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "name AS DATABASE_NAME "
				+ "FROM v$database "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_openCharSetTest(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_openCharSetTest(String catalogName, String schemaName, String tableName) {
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE ").append(schemaName).append(".").append(tableName).append(" ( VC2 VARCHAR2(100), NVC2 NVARCHAR2(100) )");
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_closeCharSetTest(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_closeCharSetTest(String catalogName, String schemaName, String tableName) {
		StringBuffer sql = new StringBuffer();
		sql.append("DROP TABLE ").append(schemaName).append(".").append(tableName).append(" CASCADE CONSTRAINTS PURGE");
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_tableNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_tableNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "table_name AS OBJECT_NAME "
				+ "FROM user_tables "
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
				+ "column_id AS COLUMN_SEQUENCE, "
				+ "column_name AS COLUMN_NAME, "
				+ "data_type AS COLUMN_TYPE, "
				+ "COALESCE(data_precision, data_length) AS COLUMN_SIZE, "
				+ "data_scale AS COLUMN_PRECISION, "
				+ "data_default AS COLUMN_DEFAULT, "
				+ "nullable AS COLUMN_NULLABLE "
				+ "FROM user_tab_columns "
				+ "WHERE table_name = ? "
				+ "ORDER BY 2 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetaData_viewNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_viewNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "view_name AS OBJECT_NAME "
				+ "FROM user_views "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_viewDefinitions(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_viewDefinitions(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "view_name AS VIEW_NAME, "
				+ "text AS VIEW_DEFINITION "
				+ "FROM user_views "
				+ "WHERE view_name = ? "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_functionNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_functionNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		// oracle can only use overloaded functions in packages
		// and none seem to be implemented for compiere or adempiere,
		// so for the time being we just assume unique names
		sql.append("SELECT DISTINCT "
				+ "name AS OBJECT_NAME "
				+ "FROM user_source "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_functionArguments(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_functionArguments(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "o.object_name AS FUNC_NAME, "
				+ "o.object_type AS FUNC_TYPE, "
				+ "COALESCE(MAX(SUBSTR(REGEXP_SUBSTR(s.text,'LANGUAGE .*'),10)), "
				+ 	"'PL/SQL') AS FUNC_LANG, "
				+ "aa.data_type AS RET_TYPE, "
				+ "a.position AS SEQ_NUM, "
				+ "a.in_out AS ARG_DIR, "
				+ "a.argument_name AS ARG_NAME, "
				+ "a.data_type AS ARG_TYPE "
				+ "FROM user_objects o "
				+ "LEFT OUTER JOIN user_arguments a ON (o.object_id=a.object_id) "
				+ "INNER JOIN user_source s ON (s.name=o.object_name) "
				+ "LEFT OUTER JOIN user_arguments aa ON (o.object_id=aa.object_id AND aa.position=0) "
				+ "WHERE o.object_name = ? "
				+ "GROUP BY o.object_name, o.object_type, aa.data_type, a.position, "
				+ 	"a.in_out, a.argument_name, a.data_type "
				+ "ORDER BY 1,5 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_functionBodies(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_functionBodies(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		// we need to extract only the actual script, not the header, and take out new lines
		sql.append("SELECT "
			+ "name AS FUNC_NAME, "
			+ "line - "
			+ "(SELECT MIN(line) "
			+ "FROM user_source "
			+ "WHERE name LIKE ? "
			+ "AND REGEXP_LIKE (text, '(^[[:space:]]*(AS|IS)[[:space:]]*|[[:space:]]?(AS|IS)[[:space:]]*$)') "
			+ ")-1 AS SEQ_NUM, "
			+ "SUBSTR(text, 1, LENGTH(text)-1) AS FUNC_DEF "
			+ "FROM user_source "
			+ "WHERE line > "
			+ "(SELECT MIN(line) "
			+ "FROM user_source "
			+ "WHERE name LIKE ? "
			+ "AND REGEXP_LIKE (text, '(^[[:space:]]*(AS|IS)[[:space:]]*|[[:space:]]?(AS|IS)[[:space:]]*$)') "
			+ ")"
			+ "AND name LIKE ? "
			+ "ORDER BY 2 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_operatorNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_operatorNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "ob.operator_name || ' (' || "
				+ "DECODE (oal.argument_type, null, "
				+ "DECODE( oar.argument_type, null, '', oar.argument_type), oal.argument_type || "
				+ "DECODE (oar.argument_type, null, '', ', ' || oar.argument_type)) "
				+ "|| ')' AS OBJECT_NAME "
				+ "FROM user_opbindings ob "
				+ "LEFT JOIN user_oparguments oal ON (ob.operator_name = oal.operator_name AND ob.binding# = oal.binding# AND oal.position=1) "
				+ "LEFT JOIN user_oparguments oar ON (ob.operator_name = oar.operator_name AND ob.binding# = oar.binding# AND oar.position=2) "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_operatorSignatures(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_operatorSignatures(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "ob.operator_name AS OPERATOR_NAME, "
				+ "DECODE (oar.argument_type, null, null, oal.argument_type) AS LEFT_ARG, "
				+ "DECODE (oar.argument_type, null, oal.argument_type, oar.argument_type) AS RIGHT_ARG, "
				+ "ob.return_type AS RETURN_TYPE "
				+ "FROM user_opbindings ob "
				+ "LEFT JOIN user_oparguments oal ON (ob.operator_name = oal.operator_name AND ob.binding# = oal.binding# AND oal.position=1) "
				+ "LEFT JOIN user_oparguments oar ON (ob.operator_name = oar.operator_name AND ob.binding# = oar.binding# AND oar.position=2) "
				+ "WHERE "
				+ "UPPER((ob.operator_name || ' (' || "
				+ "DECODE (oal.argument_type, null, "
				+ "DECODE( oar.argument_type, null, '', oar.argument_type), oal.argument_type || "
				+ "DECODE (oar.argument_type, null, '', ', ' || oar.argument_type)) "
				+ "|| ')')) LIKE UPPER(?) "
				+ "ORDER BY 1,2,3 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_operatorDefinitions(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_operatorDefinitions(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "ob.operator_name AS OPERATOR_NAME, "
				+ "ob.function_name AS FUNCTION_NAME, "
				+ "NULL AS OP_COMMUTATOR, "
				+ "NULL AS OP_NEGATOR, "
				+ "NULL AS OP_RESTRICT, "
				+ "NULL AS OP_JOIN, "
				+ "0 AS OP_HASHABLE, "
				+ "0 AS OP_MERGEABLE "
				+ "FROM user_opbindings ob "
				+ "LEFT JOIN user_oparguments oal ON (ob.operator_name = oal.operator_name AND ob.binding# = oal.binding# AND oal.position=1) "
				+ "LEFT JOIN user_oparguments oar ON (ob.operator_name = oar.operator_name AND ob.binding# = oar.binding# AND oar.position=2) "
				+ "WHERE "
				+ "UPPER((ob.operator_name || ' (' || "
				+ "DECODE (oal.argument_type, null, "
				+ "DECODE( oar.argument_type, null, '', oar.argument_type), oal.argument_type || "
				+ "DECODE (oar.argument_type, null, '', ', ' || oar.argument_type)) "
				+ "|| ')')) LIKE UPPER(?) "
				+ "ORDER BY 1,2,3 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_triggerNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_triggerNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "trigger_name AS OBJECT_NAME "
				+ "FROM user_triggers "
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
				+ "trigger_type AS TRIG_TYPE, "
				+ "triggering_event AS TRIG_EVENT, "
				+ "table_name AS TABLE_NAME, "
				+ "action_type AS ACTION_TYPE, "
				+ "trigger_type AS ACTION_ORIENTATION "
				+ "FROM user_triggers "
				+ "WHERE trigger_name LIKE ? "
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
				+ "trigger_BODY AS TRIG_BODY "
				+ "FROM user_triggers "
				+ "WHERE trigger_name LIKE ? "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_sequenceNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_sequenceNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT sequence_name AS OBJECT_NAME "
				+ "FROM user_sequences "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_sequenceDefinitions(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_sequenceDefinitions(String catalogName, String schemaName, String sequenceName) {
		StringBuffer sql = new StringBuffer();

		// need to convert MAX value to biggest possible number for long int type
		sql.append("SELECT "
			+ "min_value AS MIN_VALUE, "
			+ "LEAST(max_value, 9223372036854775807) AS MAX_VALUE, "
			+ "increment_by AS INCREMENT_BY, "
			+ "cycle_flag AS IS_CYCLED, "
			+ "cache_size AS CACHE_SIZE, "
			+ "last_number AS LAST_VALUE "
			+ "FROM user_sequences "
			+ "WHERE sequence_name LIKE '").append(sequenceName).append("' ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetdata_primaryKeyNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_primaryKeyNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "constraint_name AS OBJECT_NAME "
				+ "FROM user_constraints "
				+ "WHERE constraint_type = 'P' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_primaryKeyTables(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_primaryKeyTables(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "uc.constraint_name AS PK_NAME, "
				+ "uc.table_name AS TABLE_NAME, "
				+ "CASE uc.deferrable WHEN 'NOT DEFERRABLE' THEN 0 ELSE 1 END AS IS_DEFERRABLE, "
				+ "CASE uc.deferred WHEN 'IMMEDIATE' THEN 0 ELSE 1 END AS INITIALLY_DEFERRED "
				+ "FROM user_constraints uc "
				+ "WHERE uc.constraint_type = 'P' "
				+ "AND uc.constraint_name = ? "
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
				+ "position AS PK_SEQ, "
				+ "table_name AS TABLE_NAME, "
				+ "column_name AS COLUMN_NAME "
				+ "FROM user_cons_columns "
				+ "WHERE constraint_name = ? "
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
				+ "FROM user_constraints "
				+ "WHERE constraint_type = 'R' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_foreignKeyTables(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_foreignKeyTables(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "uc.constraint_name AS FK_NAME, "
				+ "uc.table_name AS TABLE_NAME, "
				+ "r_uc.table_name AS FTABLE_NAME, "
				+ "CASE uc.deferrable WHEN 'NOT DEFERRABLE' THEN 0 ELSE 1 END AS IS_DEFERRABLE, "
				+ "CASE uc.deferred WHEN 'IMMEDIATE' THEN 0 ELSE 1 END AS INITIALLY_DEFERRED, "
				+ "'SIMPLE' AS MATCH_TYPE, "
				+ "'RESTRICT' AS ON_UPDATE, "
				+ "uc.delete_rule AS ON_DELETE "
				+ "FROM user_constraints uc "
				+ "LEFT JOIN user_constraints r_uc "
				+	"ON uc.r_constraint_name = r_uc.constraint_name "
				+ "WHERE uc.constraint_type = 'R' "
				+ "AND uc.constraint_name = ? "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_foreignKeyColumns(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_foreignKeyColumns(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "ucc.constraint_name AS FK_NAME, "
				+ "ucc.position AS FK_SEQ, "
				+ "ucc.table_name AS TABLE_NAME, "
				+ "ucc.column_name AS COLUMN_NAME, "
				+ "r_uc.table_name AS FTABLE_NAME, "
				+ "r_ucc.column_name AS FCOLUMN_NAME "
				+ "FROM user_cons_columns ucc "
				+ "LEFT JOIN user_constraints uc "
				+	"ON uc.constraint_name = ucc.constraint_name "
				+	"AND uc.owner = ucc.owner "
				+ "LEFT JOIN user_constraints r_uc "
				+	"ON uc.r_constraint_name = r_uc.constraint_name "
				+	"AND uc.r_owner = r_uc.owner "
				+ "LEFT JOIN user_cons_columns r_ucc "
				+	"ON r_uc.constraint_name = r_ucc.constraint_name "
				+	"AND r_uc.owner = r_ucc.owner "
				+	"AND ucc.position = r_ucc.position "
				+ "WHERE ucc.constraint_name = ? "
				+ "ORDER BY 1,2 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_checkNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_checkNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		// this will include NOT NULL constraints, which are redundant and must be filtered out
		// when loading the actual constraint rules
		sql.append("SELECT uc.constraint_name AS OBJECT_NAME "
				+ "FROM user_constraints uc "
				+ "WHERE uc.constraint_type LIKE 'C' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_checkTables(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_checkTables(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "uc.constraint_name AS CHECK_NAME, "
				+ "uc.table_name AS TABLE_NAME, "
				+ "CASE uc.deferrable WHEN 'NOT DEFERRABLE' THEN 0 ELSE 1 END AS IS_DEFERRABLE, "
				+ "CASE uc.deferred WHEN 'IMMEDIATE' THEN 0 ELSE 1 END AS INITIALLY_DEFERRED "
				+ "FROM user_constraints uc "
				+ "WHERE uc.constraint_type LIKE 'C' "
				+ "AND uc.constraint_name = ? "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_checkRules(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_checkRules(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "uc.constraint_name AS CHECK_NAME, "
				+ "uc.table_name AS TABLE_NAME, "
				+ "uc.search_condition AS CHECK_CLAUSE "
				+ "FROM user_constraints uc "
				+ "WHERE uc.constraint_type LIKE 'C' "
				+ "AND uc.constraint_name = ? "
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
				+ "FROM user_constraints "
				+ "WHERE constraint_type = 'U' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_uniqueTables(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_uniqueTables(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "uc.constraint_name AS UNIQUE_NAME, "
				+ "uc.table_name AS TABLE_NAME, "
				+ "CASE uc.deferrable WHEN 'NOT DEFERRABLE' THEN 0 ELSE 1 END AS IS_DEFERRABLE, "
				+ "CASE uc.deferred WHEN 'IMMEDIATE' THEN 0 ELSE 1 END AS INITIALLY_DEFERRED "
				+ "FROM user_constraints uc "
				+ "WHERE uc.constraint_type = 'U' "
				+ "AND uc.constraint_name = ? "
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
				+ "position AS UNIQUE_SEQ, "
				+ "table_name AS TABLE_NAME, "
				+ "column_name AS COLUMN_NAME "
				+ "FROM user_cons_columns "
				+ "WHERE constraint_name = ? "
				+ "ORDER BY 2 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_indexNames(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_indexNames(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "ui.index_name AS OBJECT_NAME "
				+ "FROM user_indexes ui "
				+ "WHERE ui.index_name NOT IN "
				+ 	"(SELECT uc.constraint_name FROM user_constraints uc WHERE uc.constraint_type = 'P') "
				+ "AND ui.generated = 'N' "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_indexTables(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_indexTables(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "
				+ "ui.index_name AS INDEX_NAME, "
				+ "ui.table_name AS TABLE_NAME, "
				+ "CASE ui.uniqueness WHEN 'UNIQUE' THEN 1 ELSE 0 END AS IS_UNIQUE "
				+ "FROM user_indexes ui "
				+ "WHERE ui.index_name = ? "
				+ "ORDER BY 1 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlMetadata_indexColumns(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String)
	 */
	public String sqlMetadata_indexColumns(String catalogName, String schemaName) {
		StringBuffer sql = new StringBuffer();

		// Oracle keeps any expression in a separate view and uses a dummy column name,
		// so we need to supply the expression if a function is used.
		// To make things worse, the expression is a LONG while the column name is a VARCHAR,
		// so we need to supply two separate columns and can not use any logic
		// to supply one or the other.
		// And it gets even worse than that: Oracle erroneously surrounds column names
		// passed to the index function as arguments with quotes. Again, because
		// it is a LONG field, we can not correct it within this query but have to
		// parse it later.

		sql.append("SELECT "
				+ "uic.index_name AS INDEX_NAME, "
				+ "uic.column_position AS INDEX_SEQ, "
				+ "uic.table_name AS TABLE_NAME, "
				+ "uic.column_name AS COLUMN_NAME, "
				+ "uie.column_expression AS EXPRESSION, "
				+ "uic.descend AS SORT_ORDER, "
				+ "CASE (uic.descend) "
				+	"WHEN 'ASC' THEN 'LAST' "
				+	"ELSE 'FIRST' END "
				+	"AS SORT_NULLS "
				+ "FROM user_ind_columns uic "
				+ "LEFT OUTER JOIN user_ind_expressions uie ON "
				+	"(uic.index_name = uie.index_name AND uic.column_position = uie.column_position) "
				+ "WHERE uic.index_name = ? "
				+ "ORDER BY 1,2 ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#correctIndexFunction(java.lang.String)
	 */
	public String correctQuotedFieldNames(String expression) {
		// Oracle sometimes quotes field names in expressions, which is wrong
		// (quotes should only be used to force upper or lower case, but adempiere is case-insensitive anyway)
		return expression.replaceAll("\"", "");
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createTable(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)
	 */
	public String sqlObject_createTable(String catalogName, String schemaName, String tableName, ArrayList<String> columnNames, ArrayList<String> columnTypes, ArrayList<Boolean> columnNullables, ArrayList<String> columnDefaults) {
		StringBuffer sql = new StringBuffer();

		// prefix
		sql.append("CREATE TABLE ").append(schemaName).append(".").append(tableName).append(" (");

		// column list
		// column list
		for (int i = 0; i < columnNames.size(); i++) {
			String columnName = columnNames.get(i);
			String dataType = columnTypes.get(i);
			boolean isNullable = columnNullables.get(i);
			String defaultValue = columnDefaults.get(i);
			String sqlDefault = "";
			if (defaultValue!=null && !defaultValue.equalsIgnoreCase("null") && defaultValue.length()>0)
				sqlDefault = " DEFAULT " + defaultValue;
			String sqlNullable = "";
			if (! isNullable)
				sqlNullable = " NOT NULL";
			if (i>0)
				sql.append(", ");
			sql.append(columnName).append(" ").append(dataType).append(sqlDefault).append(sqlNullable);
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

		sql.append("DROP TABLE ").append(schemaName).append(".").append(tableName).append(" PURGE");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createView(com.kkalice.adempiere.migrate.Parameters, com.kkalice.adempiere.migrate.Logger, com.kkalice.adempiere.migrate.DBEngine, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObject_createView(String catalogName, String schemaName, String viewName, String viewDefinition) {
		StringBuffer sql = new StringBuffer();

		sql.append("CREATE OR REPLACE FORCE VIEW ")
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

		sql.append("DROP VIEW ").append(schemaName).append(".").append(viewName);

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
				.append(schemaName).append(".").append(functionName).append(" ");

		// header
		for (int i=0; i<argNames.size(); i++) {
			String argName = argNames.get(i);
			String argType = argTypes.get(i);
			String argDir = argDirs.get(i);
			if (i==0)
				sql.append("(");
			else
				sql.append(", ");
			sql.append(argName).append(" ").append(argDir).append(" ").append(argType);
		}
		if (argNames.size()>0)
			sql.append(") ");

		// middle
		StringBuffer returnExpr = new StringBuffer();
		StringBuffer langExpr = new StringBuffer();
		if (functionReturnType!=null && ! functionReturnType.equalsIgnoreCase("null"))
			returnExpr.append("RETURN ").append(functionReturnType).append(" ");
		if (functionLanguage!=null && ! functionLanguage.equalsIgnoreCase("PL/SQL"))
			langExpr.append("LANGUAGE ").append(functionLanguage).append(" ");
		sql.append(returnExpr)
				.append("AS ")
				.append(langExpr)
				.append(System.getProperty("line.separator"));

		// body
		sql.append(bodyText);

		// suffix
		sql.append(System.getProperty("line.separator"));

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_dropFunction(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObject_dropFunction(String catalogName, String schemaName, String functionType, String functionName, String functionSignature) {
		StringBuffer sql = new StringBuffer();

		// oracle can only use overloaded functions in packages
		// and none seem to be implemented for compiere or adempiere,
		// so for the time being we just assume unique names
		sql.append("DROP ").append(functionType).append(" ").append(schemaName).append(".").append(functionName).append(" ");

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

		if (functionName!=null)
			function.append(schemaName).append(".").append(functionName);
		if (leftArg!=null)
			left.append(leftArg);
		if (rightArg!=null) {
			if (left.length()>0)
				right.append(", ").append(rightArg);
			else
				right.append(rightArg);
		}
		sql.append("CREATE OR REPLACE OPERATOR ").append(schemaName).append(".").append(operatorName).append(" "
			+ "BINDING (").append(left).append(right).append(") "
			+ "RETURN ").append(returnType).append(" "
			+ "USING ").append(function);

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_dropOperator(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObject_dropOperator(String catalogName, String schemaName, String operatorName, String leftArg, String rightArg) {
		StringBuffer sql = new StringBuffer();

		sql.append("DROP OPERATOR ").append(schemaName).append(".").append(operatorName).append(" FORCE ");

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

		sql.append("DROP TRIGGER ").append(schemaName).append(".").append(triggerName).append(" ");

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
			strCycle = "NOCYCLE";

		StringBuffer strCache = new StringBuffer();
		if (cache==1)
			strCache.append("NOCACHE");
		else
			strCache.append("CACHE ").append(cache);

		sql.append("CREATE SEQUENCE ").append(schemaName).append(".").append(sequenceName).append(" "
				+ "INCREMENT BY ").append(incr).append(" "
				+ "START WITH ").append(start).append(" "
				+ "MAXVALUE ").append(max).append(" "
				+ "MINVALUE ").append(min).append(" ")
				.append(strCycle).append(" ")
				.append(strCache).append(" ");

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

		// key name
		StringBuffer constraintPhrase = new StringBuffer("CONSTRAINT ").append(keyName);
		if (keyName.toUpperCase().startsWith("SYS_C"))
			constraintPhrase = new StringBuffer("");

		// prefix
		sql.append("ALTER TABLE ")
				.append(schemaName).append(".").append(tableName).append(" "
				+ "ADD ").append(constraintPhrase).append( " "
				+ "PRIMARY KEY (");

		// columns
		for (int i = 0; i < keyColumns.size() ;  i++) {
			String columnName = keyColumns.get(i);
			if (i>0)
				sql.append(", ");
			sql.append(columnName);
		}

		// suffix
		String deferrable = "";
		String deferred = "";
		if (isDeferrable) {
			deferrable = "DEFERRABLE ";
			if (isDeferred)
				deferred = "INITIALLY DEFERRED ";
			else
				deferred = "INITIALLY IMMEDIATE ";
		} else {
			deferrable = "NOT DEFERRABLE ";
		}
		sql.append(") ")
			.append(deferrable)
			.append(deferred);

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createForeignKey(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String, boolean, boolean)
	 */
	public String sqlObject_createForeignKey(String catalogName, String schemaName, String keyName, String localTable, ArrayList<String> localColumns, String foreignTable, ArrayList<String> foreignColumns, String matchType, String onDelete, String onUpdate, boolean isDeferrable, boolean isDeferred) {
		StringBuffer sql = new StringBuffer();

		// key name
		StringBuffer constraintPhrase = new StringBuffer("CONSTRAINT ").append(keyName);
		if (keyName.toUpperCase().startsWith("SYS_C"))
			constraintPhrase = new StringBuffer("");

		// prefix
		sql.append("ALTER TABLE ")
				.append(schemaName).append(".").append(localTable).append(" "
				+ "ADD ").append(constraintPhrase).append(" "
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
		StringBuffer deleteClause = new StringBuffer();
		String deferrable = "";
		String deferred = "";
		if (onDelete!=null && !onDelete.equalsIgnoreCase("NO ACTION"))
			deleteClause.append("ON DELETE ").append(onDelete).append(" ");
		if (isDeferrable) {
			deferrable = "DEFERRABLE ";
			if (isDeferred)
				deferred="INITIALLY DEFERRED ";
			else
				deferred="INITIALLY IMMEDIATE ";
		} else {
			deferrable = "NOT DEFERRABLE ";
		}
		sql.append(") ").append(deleteClause).append(deferrable).append(deferred);

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createCheck(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, boolean, boolean)
	 */
	public String sqlObject_createCheck(String catalogName, String schemaName, String tableName, String constraintName, ArrayList<String> expressions, boolean isDeferrable, boolean isDeferred) {
		StringBuffer sql = new StringBuffer();

		// constraint name
		StringBuffer constraintPhrase = new StringBuffer("CONSTRAINT ").append(constraintName);
		if (constraintName.toUpperCase().startsWith("SYS_C"))
			constraintPhrase = new StringBuffer("");


		// prefix
		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" ADD ").append(constraintPhrase).append(" CHECK (");

		// expressions
		for (int i = 0; i < expressions.size(); i++) {
			String expression = expressions.get(i);
			if (i>0)
				sql.append(", ");
			sql.append(expression);
		}

		// suffix
		String deferrable = "";
		String deferred = "";
		if (isDeferrable) {
			deferrable = "DEFERRABLE ";
			if (isDeferred)
				deferred = "INITIALLY DEFERRED ";
			else
				deferred = "INITIALLY IMMEDIATE ";
		} else {
			deferrable = "NOT DEFERRABLE ";
		}
		sql.append(") ").append(deferrable).append(deferred);

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_createUnique(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList, boolean, boolean)
	 */
	public String sqlObject_createUnique(String catalogName, String schemaName, String tableName, String constraintName, ArrayList<String> columns, boolean isDeferrable, boolean isDeferred) {
		StringBuffer sql = new StringBuffer();

		// constraint name
		StringBuffer constraintPhrase = new StringBuffer("CONSTRAINT ").append(constraintName);
		if (constraintName.toUpperCase().startsWith("SYS_C"))
			constraintPhrase = new StringBuffer("");

		// prefix
		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" ADD ").append(constraintPhrase). append(" UNIQUE (");

		// column list
		for (int i = 0; i < columns.size(); i++) {
			String column = columns.get(i);
			if (i>0)
				sql.append(", ");
			sql.append(column);
		}

		// suffix
		String deferrable = "";
		String deferred = "";
		if (isDeferrable) {
			deferrable = "DEFERRABLE ";
			if (isDeferred)
				deferred = "INITIALLY DEFERRED ";
			else
				deferred = "INITIALLY IMMEDIATE ";
		} else {
			deferrable = "NOT DEFERRABLE ";
		}
		sql.append(") ").append(deferrable).append(deferred);

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_dropConstraint(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObject_dropConstraint(String catalogName, String schemaName, String constraintName, String tableName) {
		StringBuffer sql = new StringBuffer();

		// if the constraint name has lower case letters, we probably need to quote it.
		if (constraintName!=null && constraintName.length()>0) {
			Pattern lcTest = Pattern.compile("\\p{Lower}");
			Matcher lcResult = lcTest.matcher(constraintName);
			if (lcResult.find())
				constraintName = new StringBuffer("\"").append(constraintName).append("\"").toString();
		}

		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName)
				.append(" DROP CONSTRAINT ").append(constraintName).append(" DROP INDEX ");

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
			if (i>0)
				sql.append(", ");
			sql.append(columnName).append(" ").append(direction).append(" ");
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
				+ "ADD (").append(columnName).append(" ")
				.append(dataType).append(sqlDefault).append(sqlNullable).append(") ");

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
				+ "MODIFY ").append(columnName).append(" "
				+ "DEFAULT ").append(defaultValue).append(" ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_dropTableColumnDefault(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_dropColumnDefault(String catalogName, String schemaName, String tableName, String columnName) {
		StringBuffer sql = new StringBuffer();

		// oracle can not drop default value, only set to NULL
		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" "
			+ "MODIFY ").append(columnName).append(" "
			+ "DEFAULT NULL ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_setTableColumnNullable(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_setColumnNullable(String catalogName, String schemaName, String tableName, String columnName) {
		StringBuffer sql = new StringBuffer();

		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" "
				+ "MODIFY ").append(columnName).append(" "
				+ "NULL ");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlObject_setTableColumnNullable(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlObjectDetail_dropColumnNullable(String catalogName, String schemaName, String tableName, String columnName) {
		StringBuffer sql = new StringBuffer();

		sql.append("ALTER TABLE ").append(schemaName).append(".").append(tableName).append(" "
				+ "MODIFY ").append(columnName).append(" "
				+ "NOT NULL ");

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
				+ "MODIFY ").append(columnName).append(" ")
				.append(dataType).append(" ");

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
				+ "ADD ").append(columnName).append(" ")
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
			sql.append("TRUNC(updated) < TRUNC(sysdate - ").append(daysOld.toString()).append(") ");
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
		StringBuffer fromClause = new StringBuffer();
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
					sql.append("SET (");
				else
					sql.append(", ");
				sql.append(updateColumns.get(i));
			}
			sql.append(") = (SELECT DISTINCT ");
			for (int i=0; i<updateValues.size(); i++) {
				if (i>0)
					sql.append(", ");
				sql.append(updateValues.get(i));
			}
			sql.append(" ");
		}

		// FROM clause
		if (targetTranslationName==null) {
			// base synchronization
			fromClause.append("FROM ").append(schemaName).append(".").append(sourceTableName).append(" ts");
		} else {
			// translation synchronization
			fromClause.append("FROM ").append(schemaName).append(".").append(targetTableName).append(" tt, ")
			.append(schemaName).append(".").append(sourceTableName).append(" ts, ")
			.append(schemaName).append(".").append(sourceTranslationName).append(" tsl");
		}

		// JOIN tables
		if (joinTableNames!=null && joinTableNames.size()>0) {
			for (int index=0; index<joinTableNames.size(); index++) {
				fromClause.append(", ").append(schemaName).append(".").append(joinTableNames.get(index)).append(" tj").append(index);
			}
		}

		// EXTRA tables
		if (extraTableNames!=null && extraTableNames.size()>0) {
			for (int index=0; index<extraTableNames.size(); index++) {
				fromClause.append(", ").append(schemaName).append(".").append(extraTableNames.get(index)).append(" tx").append(index);
			}
		}
		fromClause.append(" ");

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

		// append from and where clauses to SQL command
		sql.append(fromClause);
		if (whereClause.length()>0)
			sql.append("WHERE ").append(whereClause);
		sql.append(") ");

		// append WHERE EXISTS clause (simulating postgres's UPDATE FROM command)
		sql.append("WHERE EXISTS (SELECT 1 ").append(fromClause);
		if (whereClause.length()>0)
			sql.append("WHERE ").append(whereClause);
		sql.append(") ");

		// the synchronization statement uses ANSI-compatible COALESCE function calls,
		// but oracle does not implicitly typecast COALESCE arguments, so we use
		// NVL function calls instead, where implicit typecasting is done
		sql = new StringBuffer(sql.toString().replaceAll("coalesce", "nvl"));

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
			.append(" t1 WHERE t1.rowid > ANY (SELECT t2.rowid FROM ")
			.append(schemaName).append(".").append(tableName)
			.append(" t2 WHERE ");

		for (int i = 0; i < keyColumnNames.size(); i++) {
			String columnName = keyColumnNames.get(i);
			if (i>0)
				sql.append("AND ");
			sql.append("t2.").append(columnName).append(" = t1.").append(columnName).append(" ");
		}

		sql.append(")");

		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.kkalice.adempiere.migrate.DBEngineInterface#sqlAction_enforceCheckConstraints(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String sqlAction_enforceCheckConstraints(String catalogName, String schemaName, String tableName, String checkExpression) {
		StringBuffer sql = new StringBuffer();

		// extract column name, operator, and value
		String pattern = "(?i).*?(\\b\\w*\\b).*?([<=>!]+|(\\bNOT\\s+)?LIKE\\b|\\bIN\\b).*?('\\w*'|\\d+|\\bNULL\\b).*";

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
			// we can only determine default values for equality operators =, LIKE, or IN
			// (< or > are permissible only as <= or >=)
			if (operator.contains("=") || operator.contains("LIKE") || operator.contains("IN")) {
				// we can not dtermine default values for NOT operators
				// (!= or NOT LIKE are not permissable)
				if (! (operator.contains("!") || operator.contains("NOT"))) {

					// remove wrong quotes arround column names
					checkExpression = checkExpression.replaceAll("\"", " ");

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
