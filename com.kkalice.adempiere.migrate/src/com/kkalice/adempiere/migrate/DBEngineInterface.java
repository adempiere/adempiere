/*
 * Name:		DBEngineInterface.java
 * Description:	interface to database compatibility engines
 * Created:		Nov 29, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBEngineInterface.java
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
import java.util.List;

/**
 * interface to database compatibility engines
 * @author Stefan Christians
 */
public interface DBEngineInterface {

	// database identification
	
	/**
	 * list of possible vendor names
	 * <p>
	 * A list of case-insensitive names that is used for 
	 * selecting this interface to access a database.<br>
	 * When the vendor name reported by the database's metadata matches
	 * one of those in this list, this interface is selected.
	 * <p>
	 * The first name in the list should be the primary name.
	 * @return list of possible vendor names
	 */
	public List<String> getVendorNames ();
	
	/**
	 * list of system catalogs
	 * <p>
	 * A list of case-insensitive names which are used as
	 * system catalogs by the database.<br>
	 * If a catalog is contained in this list, the user will not
	 * be able to select it for migration. 
	 * @return list of system catalog names
	 */
	public List<String> getSystemCatalogs ();

	/**
	 * list of system schemas
	 * <p>
	 * A list of case-insensitive names which are used as
	 * system schemas by the database.<br>
	 * If a schema is contained in this list, the user will not
	 * be able to select it for migration. 
	 * @return list of system schema names
	 */
	public List<String> getSystemSchemas ();

	
	// database access
	
	/**
	 * Get a jdbc driver for this database
	 * @return name of this database's jdbc driver
	 */
	public String getDBDriver ();

	/**
	 * get a valid port number
	 * @return default port number for this database
	 */
	public String getDBPort ();

	/**
	 * get the default database name<p>
	 * Some database names depend on the variant of database vendor,
	 * for example<br>
	 * vendor name "oracle" -> database name "orcl"<br>
	 * vendor name "oracleXE" -> database name "xe"
	 * <p>
	 * <code>null</code> if the database does not have any usual default name
	 * @param vendorName name of the database vendor
	 * @return default database name depending on the variant of database vendor
	 */
	public String getDBName (String vendorName);
	
	/**
	 * whether the source and target usually share the same
	 * database name
	 * @return the same database name is shared by source and target
	 */
	public boolean isSourceAndTargetSameDBName ();
	
	/**
	 * get a valid URL to connect to the datatabase 
	 * @param host server on whcih the database runs
	 * @param port port on which to connect to the database
	 * @param name name of the database
	 * @return connection url to this database
	 */
	public String getDBUrl (String host, String port, String name);

	/**
	 * get a valid system user name
	 * @return default system user name for this database
	 */
	public String getDBSystemUser ();
	
	/**
	 * get a valid system user password
	 * @return default system user password for this database
	 */
	public String getDBSystemPassword ();
	
	/**
	 * get a valid schema name
	 * <p>
	 * In some databases, the schema name must be same as the user name
	 * @param schemaName name of schema to check
	 * @param user actual name of user
	 * @return name of schema to use
	 */
	public String getDBSchemaOrUser (String schemaName, String user);
	
	/**
	 * select system or standard user
	 * <p>
	 * In some databases, a system user is required for certain operations (for example to drop a schema).<br>
	 * @param normalUser name of normal database user
	 * @param systemUser name of system user
	 * @return name of user to use for privileged operations
	 */
	public String getDBSystemOrNormalUser (String normalUser, String systemUser);
	
	/**
	 * select password for system or standard user
	 * <p>
	 * In some databases, a system user is required for certain operations (for example to drop a schema).<br>
	 * @param normalPasswd password for normal database user
	 * @param systemPasswd password for system user
	 * @return password of user to use for privileged operations
	 */
	public String getDBSystemOrNormalPassword (String normalPasswd, String systemPasswd);
	
	/**
	 * the maximum permissable length of identifiers
	 * <p>
	 * Different databases allow different maximum lengths for identifiers such as
	 * table names, column names, constraint names, etc.
	 * @return maximum identifier length
	 */
	public int getDBMaxIdentifierLength ();

	
	// data type translation
	
	/**
	 * disambiguate data type names for compatibility between databases 
	 * <p>
	 * The same name may be used for different data types in different databases.<br>
	 * This program is based on PostgreSQL. If another database uses a data type name 
	 * differently from PostgreSQL, a new name must be "invented" for disambiguation
	 * and added to DBEngine.getDataTypeID.
	 * <p>
	 * This function should convert the database's native name of the data type
	 * to the newly invented name.
	 * @param dataType name of the data type to disambiguate
	 * @return unambiguous name of data type
	 */
	public String disambiguateDataType (String dataType);
	
	/**
	 * convert data type ID to database specific implementation
	 * @param dataTypeID data type to convert
	 * @param size size/length/precision of the field
	 * @param scale scale/decimal digits of the field
	 * @return corresponding database specific data type as String (null if no direct conversion is possible)
	 */
	public String getDataType (int dataTypeID, int size, int scale);
	

	// expression translation

	/**
	 * translate expression from source database to target database
	 * @param sourceVendorName the source database vendor (in UPPERCASE)
	 * @param expression expression to translate
	 * @return valid expression
	 */
	public String translateExpression (String sourceVendorName, String expression);

	/**
	 * translate unnamed parameter<p>
	 * Some databases can use unnamed parameters in a functions argument list.<br>
	 * If unnamed parameters are supported, the valid unnamed parameter name is an empty string.<br>
	 * Otherwise it should be something like v1, v2, v... .
	 * @param paramNum the parameter number
	 * @return valid unnamed parameter name
	 */
	public String translateUnnamedParameter (int paramNum);

	/**
	 * normalize key word so that it can be used as column name
	 * @param columnName key word used as name of column
	 * @return valid column name
	 */
	public String normalizeColumnName (String columnName);
	
	/**
	 * normalize null value replacement so that it can be used in column of specified type 
	 * @param dataTypeID the data type
	 * @return valid null value replacement
	 */
	public String normalizeColumnValue (int dataTypeID);
	
	/**
	 * correct expressions using quoted field names<p>
	 * some databases erroneously surround any column names used in expressions
	 * with quotes, which must be removed
	 * @param expression the expression including quoted field names
	 * @return valid expression without quoted field names
	 */
	public String correctQuotedFieldNames (String expression);
	
	// function translation
	
	/**
	 * translate function language from source database to target database
	 * @param sourceVendorName the source database vendor (in UPPERCASE)
	 * @param functionLanguage language of function
	 * @return valid function language
	 */
	public String translateFunctionLanguage (String sourceVendorName, String functionLanguage);

	/**
	 * translate function type from source database to target database
	 * @param sourceVendorName the source database vendor (in UPPERCASE)
	 * @param functionType type of function (in UPPERCASE)
	 * @param functionReturnType return type of function
	 * @return valid function type
	 */
	public String translateFunctionType (String sourceVendorName, String functionType, String functionReturnType);
	
	/**
	 * translate function's return type from source database to target database
	 * @param dbEngine static dbEngine (for callback to translation functions in DBEngine)
	 * @param sourceVendorName the source database vendor (in UPPERCASE)
	 * @param functionReturnType return type of function
	 * @return vali function return type
	 */
	public String translateFunctionReturnType (DBEngine dbEngine, String sourceVendorName, String functionReturnType);

	/**
	 * fully translate function body from source database to target database
	 * @param dbEngine static dbEngine (for callback to translation functions in DBEngine)
	 * @param sourceVendorName the source database vendor (in UPPERCASE)
	 * @param sourceSchemaName schema used in source database
	 * @param functionLanguage language of function
	 * @param functionReturnType return type of function
	 * @param functionBodyText lines of code
	 * @return fully translated function body
	 */
	public String translateFunctionBodyFull (DBEngine dbEngine, String sourceVendorName, String sourceSchemaName, String functionLanguage, String functionReturnType, String functionBodyText);

	/**
	 * comment out function body and replace with compilable stub
	 * @param dbEngine static dbEngine (for callback to translation functions in DBEngine)
	 * @param sourceVendorName the source database vendor (in UPPERCASE)
	 * @param functionLanguage language of function
	 * @param functionReturnType return type of function
	 * @param functionBodyText lines of code
	 * @return function body replaced with compilable stub
	 */
	public String translateFunctionBodyStub (DBEngine dbEngine, String sourceVendorName, String functionLanguage, String functionReturnType, String functionBodyText);

	/**
	 * translate operator name from source database to target database
	 * @param sourceVendorName the source database vendor (in UPPERCASE)
	 * @param operatorName name of operator
	 * @return valid name of operator
	 */
	public String translateOperator (String sourceVendorName, String operatorName);

	/**
	 * Whether or not triggers can contain inline code
	 * @return triggers can contain inline code
	 */
	public boolean isTriggerContainsInlineCode ();
	
	/**
	 * translate trigger type from source database to target database
	 * @param sourceVendorName the source database vendor (in UPPERCASE)
	 * @param triggerType type of trigger (before/after)
	 * @return valid trigger type
	 */
	public String translateTriggerType(String sourceVendorName, String triggerType);

	/**
	 * translate trigger action orientation from source database to target database
	 * @param sourceVendorName the source database vendor (in UPPERCASE)
	 * @param actionOrientation fire on row or statement (in UPPERCASE)
	 * @return valid trigger action orientation
	 */
	public String translateTriggerActionOrientation(String sourceVendorName, String actionOrientation);

	/**
	 * translate trigger function from source database to target database
	 * @param sourceVendorName the source database vendor (inUPPERCASE)
	 * @param triggerFunction code block or procedure name
	 * @return valid trigger function
	 */
	public String translateTriggerFunction(String sourceVendorName, String triggerFunction);

	/**
	 * translate trigger code from source database to target database
	 * @param sourceVendorName the source database vendor (inUPPERCASE)
	 * @param actionType inline code or call procedure
	 * @param triggerName the trigger to use
	 * @param triggerFunction code block or procedure name
	 * @return valid trigger code
	 */
	public String translateTriggerCode(String sourceVendorName, String actionType, String triggerName, String triggerFunction);
	
	
	// view translation

	/**
	 * fully translate view definition from source database to target database
	 * @param dbEngine static dbEngine (for callback to translation functions in DBEngine)
	 * @param sourceVendorName the source database vendor (in UPPERCASE)
	 * @param sourceSchemaName schema used in source database
	 * @param targetSchemaName schema used in target database
	 * @param viewDefinition  text defining the view
	 * @return fully translated view definition
	 */
	public String translateViewDefinitionFull (DBEngine dbEngine, String sourceVendorName, String sourceSchemaName, String targetSchemaName, String viewDefinition);

	/**
	 * comment out view definition and replace with compilable stub
	 * @param sourceVendorName the source database vendor (in UPPERCASE)
	 * @param viewDefinition  text defining the view
	 * @return view definition replaced with compilable stub
	 */
	public String translateViewDefinitionStub (String sourceVendorName, String viewDefinition);


	// sql compatibility
	
	/**
	 * SQL command(s) to create a schema
	 * <p>
	 * This function is called multiple times with increasing step numbers and may return 
	 * a different SQL command for each step. In this way, complex multi-step commands can be 
	 * implemented.<br>
	 * The first step number is 0, the second is 1, ...<br>
	 * When the step number is higher than the number of commands needed to execute, 
	 * this function should return <code>null</code> to inform the caller that 
	 * no further commands will follow.
	 * @param step the step number to execute in a multi-step complex function (the first step is 0)
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param passwd the user's password
	 * @return SQL command(s) to create schema (null if no further commands available)
	 */
	public String sqlAdmin_createSchema (int step, String catalogName, String schemaName, String passwd);

	/**
	 * SQL command(s) to drop a schema
	 * <p>
	 * This function is called multiple times with increasing step numbers and may return 
	 * a different SQL command for each step. In this way, complex multi-step commands can be 
	 * implemented.<br>
	 * The first step number is 0, the second is 1, ...<br>
	 * When the step number is higher than the number of commands needed to execute, 
	 * this function should return <code>null</code> to inform the caller that 
	 * no further commands will follow.
	 * @param step the step number to execute in a multi-step complex function (the first step is 0)
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command(s) to drop schema (null if no further commands available)
	 */
	public String sqlAdmin_dropSchema (int step, String catalogName, String schemaName);

	/**
	 * SQL command(s) to connect to a schema
	 * <p>
	 * This function is called multiple times with increasing step numbers and may return 
	 * a different SQL command for each step. In this way, complex multi-step commands can be 
	 * implemented.<br>
	 * The first step number is 0, the second is 1, ...<br>
	 * When the step number is higher than the number of commands needed to execute, 
	 * this function should return <code>null</code> to inform the caller that 
	 * no further commands will follow.
	 * @param step the step number to execute in a multi-step complex function (the first step is 0)
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command to connect to schema (null if no further commands available)
	 */
	public String sqlAdmin_connectSchema (int step, String catalogName, String schemaName);

	/**
	 * SQL command(s) to optimize a database
	 * <p>
	 * This function is called multiple times with increasing step numbers and may return 
	 * a different SQL command for each step. In this way, complex multi-step commands can be 
	 * implemented.<br>
	 * The first step number is 0, the second is 1, ...<br>
	 * When the step number is higher than the number of commands needed to execute, 
	 * this function should return <code>null</code> to inform the caller that 
	 * no further commands will follow.
	 * @param step the step number to execute in a multi-step complex function (the first step is 0)
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command to optimize database (null if no further commands available)
	 */
	public String sqlAdmin_optimizeDatabase (int step, String catalogName, String schemaName);
	
	/**
	 * SQL command(s) to prepare a database before transfer migration starts
	 * <p>
	 * For example, this can be used to ensure that certain functions or operators exist
	 * in the target database.<br>
	 * Note that anything added here is only temporary because it will be overwritten by subsequent
	 * upgrade migrations
	 * <p>
	 * This function is called multiple times with increasing step numbers and may return 
	 * a different SQL command for each step. In this way, complex multi-step commands can be 
	 * implemented.<br>
	 * The first step number is 0, the second is 1, ...<br>
	 * When the step number is higher than the number of commands needed to execute, 
	 * this function should return <code>null</code> to inform the caller that 
	 * no further commands will follow.
	 * @param step the step number to execute in a multi-step complex function (the first step is 0)
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return SQL command to prepare database (null if no further commands available)
	 */
	public String sqlAdmin_prepareDatabaseForTransfer (int step, String catalogName, String schemaName);
	
	/**
	 * SQL command to list available databases
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>DATABASE_NAME</code></td><td>name of database</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>DATABASE_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_availableDatabases ();

	/**
	 * SQL command to create a table for testing the character set of a database<br>
	 * (needed because buggy ORACLE jdbc driver sometimes uses size of BYTE instead of size of CHAR)
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @return SQL command creating table tableName with columns 'VC2' (VARCHAR2(100)) and 'NVC2' (NVARCHAR2(100))
	 */
	public String sqlMetadata_openCharSetTest (String catalogName, String schemaName, String tableName);	

	/**
	 * SQL command to drop a table for testing the character set of a database<br>
	 * (needed because buggy ORACLE jdbc driver sometimes uses size of BYTE instead of size of CHAR)
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @return SQL command dropping table tableName
	 */
	public String sqlMetadata_closeCharSetTest (String catalogName, String schemaName, String tableName);
	
	/**
	 * SQL command to find table names
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>table name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_tableNames (String catalogName, String schemaName);	
	
	/**
	 * SQL command to find columns in a table
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return 
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>COLUMN_SEQUENCE</code></td><td>sequence number of column</td></tr>
	 * <tr><td><code>COLUMN_NAME</code></td><td>column name</td></tr>
	 * <tr><td><code>COLUMN_TYPE</code></td><td>column data type</td></tr>
	 * <tr><td><code>COLUMN_SIZE</code></td><td>column size</td></tr>
	 * <tr><td><code>COLUMN_PRECISION</code></td><td>column scale</td></tr>
	 * <tr><td><code>COLUMN_DEFAULT</code></td><td>column's default value</td></tr>
	 * <tr><td><code>COLUMN_NULLABLE</code></td><td>column is nullable</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>TABLE_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>COLUMN_SEQUENCE</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_tableColumns (String catalogName, String schemaName);
	
	/**
	 * SQL command to find view names
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>view name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_viewNames (String catalogName, String schemaName);
	
	/**
	 * SQL command to find view definitions
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>VIEW_NAME</code></td><td>view name</td></tr>
	 * <tr><td><code>VIEW_DEFINITION</code></td><td>view definition</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>VIEW_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>VIEW_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_viewDefinitions (String catalogName, String schemaName);
	
	/**
	 * SQL command to find function names
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>function name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_functionNames (String catalogName, String schemaName);

	/**
	 * SQL command to find function arguments
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>FUNC_NAME</code></td><td>function name</td></tr>
	 * <tr><td><code>FUNC_TYPE</code></td><td>function type</td></tr>
	 * <tr><td><code>FUNC_LANG</code></td><td>function language</td></tr>
	 * <tr><td><code>RET_TYPE</code></td><td>function data type</td></tr>
	 * <tr><td><code>SEQ_NUM</code></td><td>sequence number of argument</td></tr>
	 * <tr><td><code>ARG_DIR</code></td><td>argument direction</td></tr>
	 * <tr><td><code>ARG_NAME</code></td><td>argument name</td></tr>
	 * <tr><td><code>ARG_TYPE</code></td><td>argument data type</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>FUNC_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>FUNC_NAME</code></td></tr>
	 * <tr><td>2</td><td><code>SEQ_NUM</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_functionArguments (String catalogName, String schemaName);
	
	/**
	 * SQL command to find function bodies
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>FUNC_NAME</code></td><td>function name</td></tr>
	 * <tr><td><code>SEQ_NUM</code></td><td>sequence number of code line in function body</td></tr>
	 * <tr><td><code>FUNC_DEF</code></td><td>function body or code line in body</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1..n</td><td><code>FUNC_NAME</code> (can be used multiple times)</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>SEQ_NUM</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_functionBodies (String catalogName, String schemaName);	
	
	/**
	 * SQL command to find operator names
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>operator name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_operatorNames (String catalogName, String schemaName);
	
	/**
	 * SQL command to find operator signatures
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>OPERATOR_NAME</code></td><td>operator name</td></tr>
	 * <tr><td><code>LEFT_ARG</code></td><td>data type of left argument</td></tr>
	 * <tr><td><code>RIGHT_ARG</code></td><td>data type of right argument</td></tr>
	 * <tr><td><code>RETURN_TYPE</code></td><td>data type of return value</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>"OPERATOR_NAME (LEFT_ARG, RIGHT_ARG)"</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OPERATOR_NAME</code></td></tr>
	 * <tr><td>2</td><td><code>LEFT_ARG</code></td></tr>
	 * <tr><td>3</td><td><code>RIGHT_ARG</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_operatorSignatures (String catalogName, String schemaName);
	
	/**
	 * SQL command to find operator definitions
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>OPERATOR_NAME</code></td><td>operator name</td></tr>
	 * <tr><td><code>FUNCTION_NAME</code></td><td>name of function to call</td></tr>
	 * <tr><td><code>OP_COMMUTATOR</code></td><td>commutator</td></tr>
	 * <tr><td><code>OP_NEGATOR</code></td><td>negator</td></tr>
	 * <tr><td><code>OP_RESTRICT</code></td><td>restriction selectivity estimator function</td></tr>
	 * <tr><td><code>OP_JOIN</code></td><td>join selectivity estimator function</td></tr>
	 * <tr><td><code>OP_HASHABLE</code></td><td>this operator supports hash joins</td></tr>
	 * <tr><td><code>OP_MERGEABLE</code></td><td>this operator supports merge joins</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>"OPERATOR_NAME (LEFT_ARG, RIGHT_ARG)"</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OPERATOR_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_operatorDefinitions (String catalogName, String schemaName);
	
	/**
	 * SQL command to find trigger names
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>trigger name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_triggerNames (String catalogName, String schemaName);
	
	/**
	 * SQL command to find triggered tables
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>TRIG_NAME</code></td><td>trigger name</td></tr>
	 * <tr><td><code>TRIG_TYPE</code></td><td>type of trigger (before/after)</td></tr>
	 * <tr><td><code>TRIG_EVENT</code></td><td>triggering event</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>name of triggered table</td></tr>
	 * <tr><td><code>ACTION_TYPE</code></td><td>inline code or call procedure</td></tr>
	 * <tr><td><code>ACTION_ORIENTATION</code></td><td>fire on row or statement</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>TRIG_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>TRIG_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_triggerTables (String catalogName, String schemaName);
	
	/**
	 * SQL command to find trigger definitions
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>TRIG_NAME</code></td><td>trigger name</td></tr>
	 * <tr><td><code>TRIG_BODY</code></td><td>code block or procedure name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>TRIG_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>TRIG_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_triggerDefinitions (String catalogName, String schemaName);
	
	/**
	 * SQL command to find sequence names
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>sequence name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_sequenceNames (String catalogName, String schemaName);
	
	/**
	 * SQL command to find sequence definitions
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param sequenceName the sequence to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>MIN_VALUE</code></td><td>minimum value</td></tr>
	 * <tr><td><code>MAX_VALUE</code></td><td>maximum value</td></tr>
	 * <tr><td><code>INCREMENT_BY</code></td><td>increment amount</td></tr>
	 * <tr><td><code>IS_CYCLED</code></td><td>sequence is cycled</td></tr>
	 * <tr><td><code>CACHE_SIZE</code></td><td>cache size</td></tr>
	 * <tr><td><code>LAST_VALUE</code></td><td>last used value</td></tr>
	 * </table>
	 */
	public String sqlMetadata_sequenceDefinitions (String catalogName, String schemaName, String sequenceName);
	
	/**
	 * gets the database specific SQL command to find primary key names
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>primary key name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_primaryKeyNames (String catalogName, String schemaName);
	
	/**
	 * SQL command to find primary key tables
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>PK_NAME</code></td><td>primary key name</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>IS_DEFERRABLE</code></td><td>primary key is deferrable</td></tr>
	 * <tr><td><code>INITIALLY_DEFERRED</code></td><td>primary key is initially deferred</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>PK_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>PK_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_primaryKeyTables (String catalogName, String schemaName);	
	
	/**
	 * SQL command to find primary key columns
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>PK_NAME</code></td><td>primary key name</td></tr>
	 * <tr><td><code>PK_SEQ</code></td><td>sequence number of column</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>COLUMN_NAME</code></td><td>column name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>PK_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>PK_SEQ</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_primaryKeyColumns (String catalogName, String schemaName);
	
	/**
	 * SQL command to find foreign key names
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>foreign key name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_foreignKeyNames (String catalogName, String schemaName);	
	
	/**
	 * SQL command to find foreign key tables
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>FK_NAME</code></td><td>foreign key name</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>local table name</td></tr>
	 * <tr><td><code>FTABLE_NAME</code></td><td>foreign table name</td></tr>
	 * <tr><td><code>IS_DEFERRABLE</code></td><td>foreign key is deferrable</td></tr>
	 * <tr><td><code>INITIALLY_DEFERRED</code></td><td>foreign key is initially deferred</td></tr>
	 * <tr><td><code>MATCH_TYPE</code></td><td>match type</td></tr>
	 * <tr><td><code>ON_UPDATE</code></td><td>update action</td></tr>
	 * <tr><td><code>ON_DELETE</code></td><td>delete action</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>FK_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>FK_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_foreignKeyTables (String catalogName, String schemaName);
	
	/**
	 * SQL command to find foreign key columns
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>FK_NAME</code></td><td>foreign key name</td></tr>
	 * <tr><td><code>FK_SEQ</code></td><td>sequence number of column</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>local table name</td></tr>
	 * <tr><td><code>COLUMN_NAME</code></td><td>local column name</td></tr>
	 * <tr><td><code>FTABLE_NAME</code></td><td>foreign table name</td></tr>
	 * <tr><td><code>FCOLUMN_NAME</code></td><td>foreign column name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>FK_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>FK_NAME</code></td></tr>
	 * <tr><td>2</td><td><code>FK_SEQ</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_foreignKeyColumns (String catalogName, String schemaName);	

	/**
	 * SQL command to find check constraint names
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>check constraint name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_checkNames (String catalogName, String schemaName);
	
	/**
	 * SQL command to find check constraint tables
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>CHECK_NAME</code></td><td>check constraint name</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>IS_DEFERRABLE</code></td><td>constraint is deferrable</td></tr>
	 * <tr><td><code>INITIALLY_DEFERRED</code></td><td>constraint is initially deferred</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>CHECK_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>CHECK_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_checkTables (String catalogName, String schemaName);	
	
	/**
	 * SQL command to find check constraint rules
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>CHECK_NAME</code></td><td>check constraint name</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>CHECK_CLAUSE</code></td><td>check constraint clause</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>CHECK_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>CHECK_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_checkRules (String catalogName, String schemaName);	
	
	/**
	 * SQL command to find unique constraint names
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>unique constraint name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_uniqueNames (String catalogName, String schemaName);	
	
	/**
	 * SQL command to find unique constraint tables
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>UNIQUE_NAME</code></td><td>unique constraint name</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>IS_DEFERRABLE</code></td><td>constraint is deferrable</td></tr>
	 * <tr><td><code>INITIALLY_DEFERRED</code></td><td>constraint is initially deferred</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>UNIQUE_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>UNIQUE_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_uniqueTables (String catalogName, String schemaName);

	/**
	 * SQL command to find unique constraint columns
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>UNIQUE_NAME</code></td><td>check constraint name</td></tr>
	 * <tr><td><code>UNIQUE_SEQ</code></td><td>sequence of column</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>COLUMN_NAME</code></td><td>column name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>UNIQUE_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>UNIQUE_SEQ</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_uniqueColumns (String catalogName, String schemaName);
	
	/**
	 * SQL command to find index names
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">SQL command returning</th></tr>
	 * <tr><td><code>OBJECT_NAME</code></td><td>index name</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>OBJECT_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_indexNames (String catalogName, String schemaName);	
	
	/**
	 * SQL command to find indexed tables
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>INDEX_NAME</code></td><td>index name</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>IS_UNIQUE</code></td><td>index is unique</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>INDEX_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>INDEX_NAME</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_indexTables (String catalogName, String schemaName);
	
	/**
	 * SQL command to find indexed columns
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @return
	 * <table>
	 * <tr><th colspan="2" align="left">Prepared statement returning</th></tr>
	 * <tr><td><code>INDEX_NAME</code></td><td>index name</td></tr>
	 * <tr><td><code>INDEX_SEQ</code></td><td>sequence number of column</td></tr>
	 * <tr><td><code>TABLE_NAME</code></td><td>table name</td></tr>
	 * <tr><td><code>COLUMN_NAME</code></td><td>column name</td></tr>
	 * <tr><td><code>EXPRESSION</code></td><td>optional expression which will be used instead of COLUMN_NAME if not null</td></tr>
	 * <tr><td><code>SORT_ORDER</code></td><td>ascending or descending</td></tr>
	 * <tr><td><code>SORT_NULLS</code></td><td>first or last</td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">using parameters</th></tr>
	 * <tr><td>1</td><td><code>INDEX_NAME</code></td></tr>
	 * </table>
	 * <table>
	 * <tr><th colspan="2" align="left">sorted by</th></tr>
	 * <tr><td>1</td><td><code>INDEX_NAME</code></td></tr>
	 * <tr><td>2</td><td><code>INDEX_SEQ</code></td></tr>
	 * </table>
	 */
	public String sqlMetadata_indexColumns (String catalogName, String schemaName);
	
	/**
	 * SQL command to create tables
	 * @param catalogName th ecatalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnNames array of column names
	 * @param columnTypes array of column types as <code>"type (size, scale)"</code>
	 * @param columnNullables array of boolean values whether column is nullable
	 * @param columnDefaults array of column defaults
	 * @return SQL command to create table
	 */
	public String sqlObject_createTable (String catalogName, String schemaName, String tableName, ArrayList<String> columnNames, ArrayList<String> columnTypes, ArrayList<Boolean> columnNullables, ArrayList<String> columnDefaults);
	
	/**
	 * SQL command to drop tables
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @return SQL command to drop table
	 */
	public String sqlObject_dropTable (String catalogName, String schemaName, String tableName);

	/**
	 * SQL command to create views
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param viewName the view to use
	 * @param viewDefinition text defining the view
	 * @return SQL command to create view
	 */
	public String sqlObject_createView (String catalogName, String schemaName, String viewName, String viewDefinition);	
	
	/**
	 * SQL command to drop views
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param viewName view to use
	 * @return SQL command to drop view
	 */
	public String sqlObject_dropView (String catalogName, String schemaName, String viewName);
	
	/**
	 * SQL command to create functions
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param functionType type of function
	 * @param functionName the function to use
	 * @param functionReturnType return type of function
	 * @param hasOutParameters the function uses OUT parameters
	 * @param functionLanguage language of function
	 * @param argDirs array of directions of arguments
	 * @param argNames array of argument names
	 * @param argTypes array of argument types
	 * @param bodyText code of function
	 * @return SQL command to create function
	 */
	public String sqlObject_createFunction (String catalogName, String schemaName, String functionType, String functionName, String functionReturnType, boolean hasOutParameters, String functionLanguage, ArrayList<String> argDirs, ArrayList<String> argNames, ArrayList<String> argTypes, String bodyText);
	
	/**
	 * SQL command to drop functions
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param functionType type of function
	 * @param functionName the function to use
	 * @param functionSignature fully qualified name of function to use
	 * @return SQL command to drop function
	 */
	public String sqlObject_dropFunction (String catalogName, String schemaName, String functionType, String functionName, String functionSignature);
	
	/**
	 * SQL command to create operators
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param operatorName the operator to use
	 * @param leftArg data type of left argument
	 * @param rightArg data type of right argument
	 * @param returnType data type of return value
	 * @param functionName name of function to call
	 * @param commutator commutator
	 * @param negator negator
	 * @param restrictor restriction selectivity estimator function
	 * @param joiner join selectivity estimator function
	 * @param isHashable this operator supports hash joins
	 * @param isMergeable this operator supports merge joins
	 * @return SQL command to create operator
	 */
	public String sqlObject_createOperator (String catalogName, String schemaName, String operatorName, String leftArg, String rightArg, String returnType, String functionName, String commutator, String negator, String restrictor, String joiner, boolean isHashable, boolean isMergeable);	
	
	/**
	 * SQL command to drop operators
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param operatorName the operator to use
	 * @param leftArg type of left argument
	 * @param rightArg type of right argument
	 * @return SQL command to drop operator
	 */
	public String sqlObject_dropOperator (String catalogName, String schemaName, String operatorName, String leftArg, String rightArg);	

	/**
	 * SQL command to create triggers
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param triggerName the trigger to use
	 * @param tableName name of triggered table
	 * @param triggerType type of trigger (before/after)
	 * @param triggerEvent triggering event
	 * @param actionOrientation fire on row or statement
	 * @param triggerCode code block or procedure name
	 * @return SQL command to create trigger
	 */
	public String sqlObject_createTrigger (String catalogName, String schemaName, String triggerName, String tableName, String triggerType, String triggerEvent, String actionOrientation, String triggerCode);

	/**
	 * SQL command to drop triggers
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param triggerName the trigger to use
	 * @param tableName name of triggered table
	 * @return SQL command to drop trigger
	 */
	public String sqlObject_dropTrigger (String catalogName, String schemaName, String triggerName, String tableName);
	
	/**
	 * SQL command for creating sequences
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param sequenceName the sequence to use
	 * @param min minimum value
	 * @param max maximum value
	 * @param incr sequence increment
	 * @param isCycled wrap around when maximum reached
	 * @param cache cache size
	 * @param start first sequence number
	 * @return SQL command to create sequence
	 */
	public String sqlObject_createSequence (String catalogName, String schemaName, String sequenceName, long min, long max, long incr, boolean isCycled, long cache, long start);

	/**
	 * SQL command to drop sequences
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param sequenceName the sequence to use
	 * @return SQL command to drop sequence
	 */
	public String sqlObject_dropSequence (String catalogName, String schemaName, String sequenceName);
	
	/**
	 * SQL command to create primary keys
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param keyName name of the primary key
	 * @param isDeferrable the constraint is deferrable
	 * @param isDeferred the constraint is initially deferred
	 * @param keyColumns array of columns that constitute the primary key
	 * @return SQL command to create the primary key
	 */
	public String sqlObject_createPrimaryKey (String catalogName, String schemaName, String tableName, String keyName, boolean isDeferrable, boolean isDeferred, ArrayList<String> keyColumns);
	
	/**
	 * SQL command to create foreign keys
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param keyName the foreign key to use
	 * @param localTable table in which to create the foreign key
	 * @param localColumns array of bound columns in the local table
	 * @param foreignTable foreign table to which the foreign key points
	 * @param foreignColumns array of referenced columns in the foreign table
	 * @param matchType the match type
	 * @param onDelete the delete action
	 * @param onUpdate the update action
	 * @param isDeferrable the constraint is deferrable
	 * @param isDeferred the constraint is initially deferred
	 * @return SQL command to create foreign keys
	 */
	public String sqlObject_createForeignKey (String catalogName, String schemaName, String keyName, String localTable, ArrayList<String> localColumns, String foreignTable, ArrayList<String> foreignColumns, String matchType, String onDelete, String onUpdate, boolean isDeferrable, boolean isDeferred);	
	
	/**
	 * gets the database specific SQL command to create check constraints
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param constraintName the constraint to use
	 * @param expressions array of expressions to check
	 * @param isDeferrable the constraint is deferrable
	 * @param isDeferred the constraint is initially deferred
	 * @return SQL command to create check constraint
	 */
	public String sqlObject_createCheck (String catalogName, String schemaName, String tableName, String constraintName, ArrayList<String> expressions, boolean isDeferrable, boolean isDeferred);
		
	/**
	 * SQL command to create unique constraints
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param constraintName the constraint to use
	 * @param columns array of columns to be unique
	 * @param isDeferrable the constraint is deferrable
	 * @param isDeferred the constraint is initially deferred
	 * @return SQL command to create unique constraint
	 */
	public String sqlObject_createUnique (String catalogName, String schemaName, String tableName, String constraintName, ArrayList<String> columns, boolean isDeferrable, boolean isDeferred);
	
	/**
	 * SQL command to drop constraints
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param constraintName the constraint to use
	 * @param tableName table from which to drop the constraint
	 * @return SQL command to drop constraint
	 */
	public String sqlObject_dropConstraint (String catalogName, String schemaName, String constraintName, String tableName);
	
	/**
	 * SQL command to create indexes
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName table on which to create the index
	 * @param isUnique index is unique
	 * @param indexName the index to use
	 * @param columnNames array of columns to index
	 * @param directions array of ascending or descending directions
	 * @param nullTreatments array of nulls first or last sorting
	 * @return SQL command to create index
	 */
	public String sqlObject_createIndex (String catalogName, String schemaName, String tableName, boolean isUnique, String indexName, ArrayList<String> columnNames, ArrayList<String> directions, ArrayList<String> nullTreatments);

	/**
	 * SQL command to drop indexes
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param indexName the index to use
	 * @return SQL command to drop index
	 */
	public String sqlObject_dropIndex (String catalogName, String schemaName, String indexName);

	/**
	 * SQL command to create columns 
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @param dataType fully qualified data type of column
	 * @param isNullable column is nullable
	 * @param defaultValue default value of column
	 * @return SQL command to create column
	 */
	public String sqlObjectDetail_createColumn(String catalogName, String schemaName, String tableName, String columnName, String dataType, boolean isNullable, String defaultValue);	
	
	/**
	 * SQL command to drop columns
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @return SQL command to drop column
	 */
	public String sqlObjectDetail_dropColumn (String catalogName, String schemaName, String tableName, String columnName);
	
	/**
	 * gets the database specific SQL command to set default column values  
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @param defaultValue default value of column
	 * @return SQL command to set default column value
	 */
	public String sqlObjectDetail_setColumnDefault (String catalogName, String schemaName, String tableName, String columnName, String defaultValue);
	
	/**
	 * gets the database specific SQL command to drop default column values  
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @return SQL command to drop default column value
	 */
	public String sqlObjectDetail_dropColumnDefault (String catalogName, String schemaName, String tableName, String columnName);	

	/**
	 * SQL command to make columns nullable  
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @return SQL command to make column nullable
	 */
	public String sqlObjectDetail_setColumnNullable (String catalogName, String schemaName, String tableName, String columnName);
	
	/**
	 * SQL command to make columns not nullable  
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @return SQL command to make column not nullable
	 */
	public String sqlObjectDetail_dropColumnNullable (String catalogName, String schemaName, String tableName, String columnName);

	/**
	 * SQL command to prepare columns for being not nullable 
	 * <p>
	 * When the column of an existing table is set to be not nullable, care must be taken
	 * that no null values pre-exist  
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @param dataType data type of column
	 * @param defaultValue default value of column
	 * @return SQL command to prepare column for being not nullable 
	 */
	public String sqlObjectDetail_prepareColumnNotNullable (String catalogName, String schemaName, String tableName, String columnName, String dataType, String defaultValue);	
	
	/**
	 * SQL command to modify column data types
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @param dataType fully qualified data type of column
	 * @return SQL command to modify column data type
	 */
	public String sqlObjectDetail_modifyColumnType (String catalogName, String schemaName, String tableName, String columnName, String dataType);
	
	/**
	 * SQL command to rename columns
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @param newName the new column name
	 * @return SQL command to rename column
	 */
	public String sqlObjectDetail_renameColumn (String catalogName, String schemaName, String tableName, String columnName, String newName);	
	
	/**
	 * SQL command to create temporary columns
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @param dataType fully qualified type of column
	 * @return SQL command to create temporary column
	 */
	public String sqlObjectDetail_createTemporaryColumn (String catalogName, String schemaName, String tableName, String columnName, String dataType);
	
	/**
	 * SQL command to drop temporary columns
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @return SQL command to drop temporary column
	 */
	public String sqlObjectDetail_dropTemporaryColumn (String catalogName, String schemaName, String tableName, String columnName);

	/**
	 * SQL command to save data in temporary columns
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param temporaryColumnName name of the temporary column
	 * @param columnName name of the original column
	 * @param dataType type of column
	 * @return SQL command save data in temporary column
	 */
	public String sqlObjectDetail_saveTemporaryColumn (String catalogName, String schemaName, String tableName, String temporaryColumnName, String columnName, String dataType);
	
	/**
	 * SQL command to restore data from temporary columns
	 * @param vendorName the database vendor
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param temporaryColumnName name of the temporary column
	 * @param columnName name of the original column
	 * @return SQL command restore data from temporary column
	 */
	public String sqlObjectDetail_restoreTemporaryColumn (String vendorName, String catalogName, String schemaName, String tableName, String temporaryColumnName, String columnName);

	/**
	 * SQL command to erase data from columns
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnName the column to use
	 * @return SQL command to erase data from column
	 */
	public String sqlObjectDetail_eraseColumn (String catalogName, String schemaName, String tableName, String columnName);
	
	/**
	 * SQL command to select records from a table
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param tableAlias alias to use for the table
	 * @param columnNames array of columns to query (null if all columns should be returned)
	 * @param aliasNames array of aliases to use as column names in the query
	 * @param joinTypes array of join types
	 * @param joinTables array of tables to join
	 * @param joinAliases aliases to use for joined tables
	 * @param joinConditions array of join conditions
	 * @param conditions array of where clauses (to be ANDed)
	 * @param sortColumns array of columns by which to sort the query
	 * @param isDistinct only return distinct rows
	 * @return SQL command to select records from a table<p>
	 * <b>example:</b><br>
	 * <code>SELECT (DISTINCT) columnName0 AS aliasName0, columnName1 AS aliasName1, columnName2 AS aliasName 2 ...<br>
	 * FROM schemaName.tableName tableAlias<br>
	 * joinType0 schemaName.joinTable0 joinAlias0 ON (joinCondition0)<br>
	 * joinType1 schemaName.joinTable1 joinAlias1 (joinCondition1)<br>
	 * joinType2 schemaName.joinTable2 joinAlias2 ON (joinCondition2)<br>
	 * ...<br>
	 * WHERE condition0 AND condition1 AND condition2 ...<br>
	 * ORDER BY sortColumn0, sortColumn1, sortColumn2 ...</code> 
	 */
	public String sql_select (String catalogName, String schemaName, String tableName, String tableAlias, ArrayList<String> columnNames, ArrayList<String> aliasNames, ArrayList<String> joinTypes, ArrayList<String> joinTables, ArrayList<String> joinAliases, ArrayList<String> joinConditions, ArrayList<String> conditions, ArrayList<String> sortColumns, boolean isDistinct);

	/**
	 * SQL command to update records in a table
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param tableAlias alias to use for the table
	 * @param columnNames array of column names to set
	 * @param values array of values to set
	 * @param conditions array of where clauses (to be ANDed)
	 * @return SQL command to update records in a table<p>
	 * <b>example:</b><br>
	 * <code> UPDATE schemaName.tableName tableAlias<br>
	 * SET columnName0 = value0, columnName1 = value1, columnName2 = value2 ...<br>
	 * WHERE condition0 AND condition1 AND condition2 ...</code>
	 */
	public String sql_update (String catalogName, String schemaName, String tableName, String tableAlias, ArrayList<String> columnNames, ArrayList<String> values, ArrayList<String> conditions);
	
	/**
	 * SQL command to delete records fulfilling condition and of a specified age
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param tableAlias alias to use for the table
	 * @param conditions array of WHERE clauses (or NULL if no condition) (to be ANDed)
	 * @param daysOld minimum age of records to delete (or NULL if no age limit)
	 * @return SQL command to delete records fulfilling condition and of a specified age<p>
	 * <b>example:</b><br>
	 * <code>DELETE FROM schemaName.tableName tableAlias<br>
	 * WHERE condition0 AND condition1 AND condition2 ...<br>
	 * AND date_trunc('day', updated) < (date_trunc('day', now())::date - daysOld::integer)</code>
	 */
	public String sql_delete (String catalogName, String schemaName, String tableName, String tableAlias, ArrayList<String> conditions, Integer daysOld);

	/**
	 * SQL command to insert records
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnNames array of column names to use
	 * @param columnValues array of values to use
	 * @return SQL command to insert record<p>
	 * <b>example:</b><br>
	 * <code>INSERT INTO schemaName.tableName <br>
	 * (column0, column1, column2 ...) VALUES <br>
	 * (value0, value1, value2 ...)</code>
	 */
	public String sql_insert (String catalogName, String schemaName, String tableName, ArrayList<String> columnNames, ArrayList<String> columnValues);
	
	/**
	 * SQL command to insert records from another table
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param columnNames array of column names to use
	 * @param columnValues array of values to use
	 * @param sourceTableName table from which to fetch records (aliased as t)
	 * @param joinTypes array of join types
	 * @param joinTables array of tables to join (aliased as t0, t1, t2 ...)
	 * @param joinConditions array of join conditions
	 * @param whereClause optional SQL WHERE clause
	 * @return SQL command to insert records from another table<p>
	 * <b>example</b><br>
	 * <code>INSERT INTO schemaName.tableName (column0, column1, column2 ...) <br>
	 * SELECT DISTINCT value0, value1, value2 ... FROM schemaName.sourceTableName t <br>
	 * jointType0 schemaName.joinTable0 t0 ON (joinCondition0) <br>
	 * jointType1 schemaName.joinTable1 t1 ON (joinCondition1) <br>
	 * jointType2 schemaName.joinTable2 t2 ON (joinCondition2) <br>
	 * ... <br>
	 * WHERE whereClause</code>
	 */
	public String sql_insertFromTable (String catalogName, String schemaName, String tableName, ArrayList<String> columnNames, ArrayList<String> columnValues, String sourceTableName, ArrayList<String> joinTypes, ArrayList<String> joinTables, ArrayList<String> joinConditions, String whereClause);
		
	/**
	 * SQL command to synchronize terminology in application dictionary
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param targetTableName name of the table which holds data to update (aliased as tt)
	 * @param sourceTableName name of the table which holds data to deploy (aliased as ts)
 	 * @param targetTranslationName name of the table which holds translated data to update (aliased as ttl)
	 * @param sourceTranslationName name of the table which holds translated data to deploy (aliased as tsl)
	 * @param joinTableNames array of tables joining the target table with the source table (aliased as tj0, tj1, ..., tjn)
	 * @param linkConditions array of conditions to link the target, joined, and source tables
	 * @param extraTableNames array of tables providing additional information for the target table (aliased as tx0, tx1, ..., txn)
	 * @param extraConditions array of conditions to link the extra tables
	 * @param hasCentrallyMaintained the target table has a column to indicate whether it is centrally maintained
	 * @param updateColumns array of columns to be updated
	 * @param updateValues array of values with which to update the columns
	 * @param updateConditions array of conditions (ORed) under which to update the columns
	 * @return SQL command to synchronize terminology<p>
	 * <b>example</b><br>
	 * <code>UPDATE schemaName.targetTranslationName ttl<br>
	 * SET updateColumn0 = updateValue0, updateColumn1 = updateValue1, updateColumn2 = updateValue2 ...<br>
	 * FROM schemaName.targetTableName tt, schemaName.sourceTableName ts, schemaName.sourceTranslationName tsl,<br>
	 * schemaName.joinTable0 tj0, schemaName.joinTable1 tj1, schemaName.joinTable2 tj2 ...<br>
	 * schemaName.extraTable0 tx0, schemaName.extraTable1 tx1, schemaName.extraTable2 tx2 ...<br>
	 * WHERE linkCondition0 AND linkCondition1 AND linkCondition2 ...<br>
	 * AND extraCondition0 AND extraCondition1 AND extraCondition2 ...<br>
	 * AND tt.IsCentrallyMaintained = 'Y'<br>
	 * AND (updateCondition0 OR updateCondition1 OR updateCondition2 ...)</code>
	 */
	public String sqlADAction_updateTerminology (String catalogName, String schemaName, 
			String targetTableName, String sourceTableName, 
			String targetTranslationName, String sourceTranslationName, 
			ArrayList<String> joinTableNames, ArrayList<String> linkConditions, 
			ArrayList<String> extraTableNames, ArrayList<String> extraConditions,
			boolean hasCentrallyMaintained,
			ArrayList<String> updateColumns, ArrayList<String> updateValues, ArrayList<String> updateConditions);

	/**
	 * SQL command to purge records with unsatisfied foreign keys
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param localTableName local table in which to search
	 * @param localColumnNames array of columns which reference a foreign table
	 * @param foreignKeyNames array of names of foreign keys
	 * @param foreignTableNames array of foreign tables being referenced
	 * @param foreignColumnNames array of foreign columns being referenced
	 * @return SQL command to purge records with unsatisfied foreign keys<p>
	 * <b>example</b><br>
	 * <code>
	 * DELETE FROM schemaName.localTableName lcltbl<br>
	 * WHERE NOT EXISTS (SELECT 1 FROM schemaName.foreignTableName0 frntbl WHERE lcltbl.localColName0 = frntbl.foreignColName0 AND lcltbl.localColName1 = frntbl.foreignColName1 AND lcltbl.localColName2 = frntbl.foreignColName2 ...)<br>
	 * OR NOT EXISTS (SELECT 1 FROM schemaName.foreignTableName1 frntbl WHERE lcltbl.localColName0 = frntbl.foreignColName0 AND lcltbl.localColName1 = frntbl.foreignColName1 AND lcltbl.localColName2 = frntbl.foreignColName2 ...)<br>
	 * OR NOT EXISTS (SELECT 1 FROM schemaName.foreignTableName2 frntbl WHERE lcltbl.localColName0 = frntbl.foreignColName0 AND lcltbl.localColName1 = frntbl.foreignColName1 AND lcltbl.localColName2 = frntbl.foreignColName2 ...)<br>
	 * ... 
	 * </code>
	 */
	public String sqlAction_purgeOrphans (String catalogName, String schemaName, 
			String localTableName, ArrayList<String> localColumnNames, 
			ArrayList<String> foreignKeyNames, ArrayList<String> foreignTableNames, ArrayList<String> foreignColumnNames);

	/**
	 * SQL command to delete records with duplicate primary keys
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table in which to search
	 * @param keyColumnNames array of columns used by primary key
	 * @return SQL command to delete records with duplicate primary keys<p>
	 * <b>example</b><br>
	 * <code>
	 * DELETE FROM schemaName.tableName t1<br>
	 * WHERE EXISTS (<br>
	 * 	SELECT 1 FROM schemaName.tableName t2<br>
	 * 	WHERE t2.keyColumn1 = t1.keyColumn1<br>
	 * 	AND t2.keyColumn2 = t1.keyColumn2<br>
	 * 	...<br>
	 * 	AND t2.ctid < t1.ctid<br>
	 * )
	 * </code>
	 */
	public String sqlAction_dropDuplicates (String catalogName, String schemaName, String tableName, ArrayList<String> keyColumnNames);

	/**
	 * gets the database-specific SQL command to enforce check constraints
	 * <p>
	 * set rows to first value allowed by check constraint if the constraint
	 * would otherwise be violated by the current value.
	 * @param catalogName the catalog to use
	 * @param schemaName the schema to use
	 * @param tableName the table to use
	 * @param checkExpression the check constraint
	 * @return SQL command to enforce check constraints
	 * <p>
	 * <b>example</b><br>
	 * a check expression of "columName = ANY(ARRAY['Y'::bpchar, 'N'::bpchar])" would result in:<p>
	 * <code>
	 * UPDATE schemaName.tableName <br>
	 * SET columnName = 'Y' <br>
	 * WHERE NOT (columName = ANY(ARRAY['Y'::bpchar, 'N'::bpchar]));
	 * </code>
	 */
	public String sqlAction_enforceCheckConstraints (String catalogName, String schemaName, String tableName, String checkExpression);
	
}
