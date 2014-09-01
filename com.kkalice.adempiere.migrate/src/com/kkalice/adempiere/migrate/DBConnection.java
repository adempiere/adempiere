/*
 * Name:		DBConnection.java
 * Description:	Connection to database through JDBC
 * Created:		Jan 31, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 *
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/DBConnection.java
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
import java.math.*;

/**
 * Connection to Database through JDBC
 * @author Stefan Christians
 */
@SuppressWarnings("static-access")
public class DBConnection {

	// MEMBERS
	// =======

	/** static parameters */
	private static Parameters s_parameters = null;

	/** static logger */
	private static MigrateLogger s_logger = null;

	/** static dbEngine */
	private static DBEngine s_dbEngine = null;


	/** this connection */
	private Connection m_connection = null;

	/** whether or not this is the target connection */
	private boolean m_isTarget = false;

	/** whether this connection has been temporarily disconnected */
	private boolean m_isTempDisconnected = false;

	/** database driver */
	private String m_driver = null;

	/** database url */
	private String m_url = null;

	/** character size devisor (because of buggy oracle jdbc driver) */
	private int m_charDevisor = 1;

	/** whether or not savepoints are releasable (because of buggy oracle jdbc driver) */
	private boolean m_isSavepointReleaseable = true;


	/** list of tables */
	private HashMap<String, DBObject> m_tables = null;
	/** list of views */
	private HashMap<String, DBObject> m_views = null;
	/** list of operators */
	private HashMap<String, DBObject> m_operators = null;
	/** list of functions */
	private HashMap<String, DBObject> m_functions = null;
	/** list of triggers */
	private HashMap<String, DBObject> m_triggers = null;
	/** list of sequences */
	private HashMap<String, DBObject> m_sequences = null;
	/** list of primary Keys */
	private HashMap<String, DBObject> m_primaryKeys = null;
	/** list of foreign Keys */
	private HashMap<String, DBObject> m_foreignKeys = null;
	/** list of check constraints */
	private HashMap<String, DBObject> m_checks = null;
	/** list of unique constraints */
	private HashMap<String, DBObject> m_uniques = null;
	/** list of indexes */
	private HashMap<String, DBObject> m_indexes = null;


	/** list of custom prefixes */
	private ArrayList<String> m_customPrefixes = null;
	/** list of custom entities */
	private ArrayList<String> m_customEntities = null;

	/** list of system-level clients */
	private HashMap<Integer, String> m_systemClients = null;
	/** list of system languages */
	private ArrayList<String> m_systemLanguages = null;
	/** list of system sequences */
	private HashMap<String, Long> m_sequenceSystem = null;
	/** list of user sequences */
	private HashMap<String, Long> m_sequenceUser = null;


	/** last error of silent sql command */
	private String m_lastSilentError = null;

	/** database structure has been changed and migration process must not be interrupted anymore*/
	private boolean m_isDoNotInterrupt = false;


	// CONSTRUCTORS
	// ============

	/**
	 * Default Constructor
	 * @param isTarget this is the target connection
	 */
	public DBConnection(boolean isTarget) {
		s_parameters = Parameters.getParameters();
		s_logger = MigrateLogger.getLogger();
		s_dbEngine = DBEngine.getDBEngine();

		m_isTarget = isTarget;

		reset();
	}


	// METHODS

	/**
	 * connect to the database
	 */
	public void connectDatabase() {

		s_logger.log(Level.CONFIG, "connectDatabase", getDirection());

		setDriver(s_dbEngine.getDBDriver(getVendor()));
		setUrl(s_dbEngine.getDBUrl(getVendor(), getHost(), getPort(), getName()));

		// Load the JDBC driver
		try {
			Class.forName(getDriver());
		} catch (ClassNotFoundException e) {
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "connectDatabaseNoDriver", new Object[] {getDriver(), e.getMessage()});
		}

		// connect to the database
		try {
			if (m_connection!=null)
				m_connection.close();
			m_connection = DriverManager.getConnection(getUrl(), getUser(), getPasswd());
			m_connection.setAutoCommit(false);

			s_logger.log(Level.CONFIG, "connectDatabaseEstablished", getUrl());

		} catch (SQLException e) {
			// if connection fails, try again as system user
			connectDatabaseSystem();
			if (m_connection==null)
				s_dbEngine.setDBError(true);
		}

		s_logger.flush();
	}

	/**
	 * connect to the database as system user
	 */
	private void connectDatabaseSystem() {

		s_logger.log(Level.CONFIG, "connectDatabaseSystem", getDirection());

		String user = s_dbEngine.getDBSystemOrNormalUser(getVendor(), getUser(), getSystemUser());
		String passwd = s_dbEngine.getDBSystemOrNormalPassword(getVendor(), getPasswd(), getSystemPasswd());

		// Load the JDBC driver
		try {
			Class.forName(getDriver());
		} catch (ClassNotFoundException e) {
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "connectDatabaseNoDriver", new Object[] {getDriver(), e.getMessage()});
		}

		// connect to the database
		try {
			if (m_connection!=null)
				m_connection.close();
			m_connection = DriverManager.getConnection(getUrl(), user, passwd);
			m_connection.setAutoCommit(true);

			s_logger.log(Level.CONFIG, "connectDatabaseEstablished", getUrl());

		} catch (SQLException e) {
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "connectDatabaseFailed", new Object[] {getUrl(), e.getMessage()});
		}

		s_logger.flush();
	}

	/**
	 * temporarily disconnect from source database to ease burden on server
	 */
	public void temporarilyDisconnectSource () {
		// only disconnect source
		if (isSource()) {

			s_logger.log(Level.CONFIG, "tempCloseConnection", getDirection());

			if (m_connection != null) {
				rollbackChanges();
				try {
					m_connection.close();
					m_connection = null;
					m_isTempDisconnected=true;
				} catch (SQLException e) {
					s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "closeConnectionError", new Object[] {getDirection(), e.getMessage()});
				}
			}
			s_logger.log(Level.CONFIG, "tempConnectionClosed");
		}
	}

	/**
	 * reconnect to source after temporary disconnection
	 */
	public void reconnectSource() {
		// only re-connect source if it has been temporarily disconnected
		if (isSource() && m_isTempDisconnected) {
			// re-establish the database connection
			if (m_connection==null) {
				connectDatabase();
			}
			// re-attach to schema
			connectDBSchema();
		}
	}

	/**
	 * find out the metadata for this connection
	 */
	public void loadMetaData() {
		loadMetaData(null, null);
	}


	/**
	 * find out the metadata for this connection
	 * @param sourceCustomPrefixes custom prefixes which have already been defined in source
	 * @param sourceCustomEntities custom entities which have already been defined in source
	 */
	public void loadMetaData(ArrayList<String> sourceCustomPrefixes, ArrayList<String> sourceCustomEntities) {

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "loadMetadata", getDirection());

		loadDBVendor();
		loadDBCatalog();
		loadDBSchema();

		if (s_parameters.isCopy() && isTarget())
			reCreateDBSchema();
		connectDBSchema();

		loadDBCharSize();
		loadDBisSavepointReleaseable();

		m_tables = new HashMap<String, DBObject>(loadDBObjects(DBObject_Table.class));
		m_views = new HashMap<String, DBObject>(loadDBObjects(DBObject_View.class));
		m_operators = new HashMap<String, DBObject>(loadDBObjects(DBObject_Operator.class));
		m_triggers = new HashMap<String, DBObject>(loadDBObjects(DBObject_Trigger.class));
		m_functions = new HashMap<String, DBObject>(loadDBObjects(DBObject_Function.class));
		m_sequences = new HashMap<String, DBObject>(loadDBObjects(DBObject_Sequence.class));
		m_primaryKeys = new HashMap<String, DBObject>(loadDBObjects(DBObject_PrimaryKey.class));
		m_foreignKeys = new HashMap<String, DBObject>(loadDBObjects(DBObject_ForeignKey.class));
		m_checks = new HashMap<String, DBObject>(loadDBObjects(DBObject_Check.class));
		m_uniques = new HashMap<String, DBObject>(loadDBObjects(DBObject_Unique.class));
		m_indexes = new HashMap<String, DBObject>(loadDBObjects(DBObject_Index.class));

		loadCustomPrefixes(sourceCustomPrefixes);
		loadCustomEntities(sourceCustomEntities);

		m_systemClients = new HashMap<Integer, String>(loadSystemClients());
		m_systemLanguages = new ArrayList<String>(loadSystemLanguages());

		if (isPreserveTableIDs())
			loadADSequences();

		loadCustomizationLevel(DBObject_Table.class);
		loadCustomizationLevel(DBObject_View.class);
		loadCustomizationLevel(DBObject_Operator.class);
		loadCustomizationLevel(DBObject_Trigger.class);
		loadCustomizationLevel(DBObject_Function.class);
		loadCustomizationLevel(DBObject_Sequence.class);
		loadCustomizationLevel(DBObject_PrimaryKey.class);
		loadCustomizationLevel(DBObject_ForeignKey.class);
		loadCustomizationLevel(DBObject_Check.class);
		loadCustomizationLevel(DBObject_Unique.class);
		loadCustomizationLevel(DBObject_Index.class);

		s_logger.log(Level.CONFIG, "metadataLoaded", getDirection());
		s_logger.flush();
	}

	/**
	 * commit changes
	 */
	public void commitChanges() {

		s_logger.log(Level.FINE, "commitChanges", getDirection());

		// commit
		try {
			if (m_connection!=null && !m_connection.getAutoCommit())
				m_connection.commit();
		} catch (SQLException e) {
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName() , "commitChangesError", new Object[] {getDirection(), e.getMessage()});
		}

		s_logger.log(Level.FINE, "changesComitted");
		s_logger.flush();
	}

	/**
	 * rollback changes
	 */
	private void rollbackChanges() {

		s_logger.log(Level.FINE, "rollbackChanges", getDirection());

		// roll back
		try {
			m_connection.rollback();
		} catch (SQLException e) {
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName() , "rollbackChangesError", new Object[] {getDirection(), e.getMessage()});
		}

		s_logger.log(Level.FINE, "changesRolledBack");
		s_logger.flush();
	}

	/**
	 * prepare database before transfer migration
	 */
	public void prepareDatabase() {

		s_logger.log(Level.FINE, "prepareDatabase", getDirection());

		String vendor = getVendor();
		String catalog = getCatalog();
		String schema = getSchema();

		// multiple commands may be run, so we keep fetching SQL statements
		// until null is returned
		int step = 0;
		int errors = 0;
		String sql = "";

		Statement stmt = setStatement();
		sql = s_dbEngine.sqlAdmin_prepareDatabaseForTransfer(step, vendor, catalog, schema);
		while (sql != null) {
			step ++;

			if (executeUpdate(stmt, sql, false, false) == null)
				errors ++;

			sql = s_dbEngine.sqlAdmin_prepareDatabaseForTransfer(step, vendor, catalog, schema);
		}
		releaseStatement(stmt);

		s_logger.log(Level.FINE, "databasePrepared", new Object[] {Integer.toString(step-errors), Integer.toString(step)});
		s_logger.flush();
	}

	/**
	 * optimize database after migration
	 */
	private void optimizeDatabase() {

		s_logger.log(Level.FINE, "optimizeDatabase", getDirection());

		String vendor = getVendor();
		String catalog = getCatalog();
		String schema = getSchema();

		// multiple commands may be run, so we keep fetching SQL statements
		// until null is returned
		int step = 0;
		int errors = 0;
		String sql = "";

		Statement stmt = setStatement();
		sql = s_dbEngine.sqlAdmin_optimizeDatabase(step, vendor, catalog, schema);
		while (sql != null) {
			step ++;

			if (executeUpdate(stmt, sql, false, false) == null)
				errors ++;

			sql = s_dbEngine.sqlAdmin_optimizeDatabase(step, vendor, catalog, schema);
		}
		releaseStatement(stmt);

		s_logger.log(Level.FINE, "databaseOptimized", new Object[] {Integer.toString(step-errors), Integer.toString(step)});
		s_logger.flush();
	}

	/**
	 * close this connection
	 */
	public void close() {

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "closeConnection", getDirection());

		if (m_connection != null) {

			if (isTarget()) {
				// commit target
				commitChanges();
				// optimize target if so required
				if (s_parameters.isOptimizeDatabase()) {
					// re-logon as system user
					connectDatabaseSystem();
					// optimiza database
					optimizeDatabase();
				}
			} else {
				// rollback source
				rollbackChanges();
				// drop source if so required and no errors occured
				if (s_parameters.isUpgrade() && s_parameters.isDropSource()) {
					if ((! s_dbEngine.isDBError()) && (! s_dbEngine.isSQLError())) {
						// re-logon as system user
						connectDatabaseSystem();
						// drop schema
						dropDBSchema();
					}
				}
			}

			try {

				m_connection.close();
				m_connection = null;

			} catch (SQLException e) {
				s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "closeConnectionError", new Object[] {getDirection(), e.getMessage()});
			}
		}

		s_logger.log(Level.CONFIG, "connectionClosed");
		s_logger.flush();
	}


	/**
	 * load the vendor of a database
	 * (overwrites the supplied migration parameter with actual value from the database)
	 */
	private void loadDBVendor() {

		s_logger.log(Level.FINE, "loadDBVendor", getDirection());

		String result = null;

		try {
			result = m_connection.getMetaData().getDatabaseProductName();
		} catch (SQLException e) {
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "loadDBVendorError", new Object[] {getDirection(), e.getMessage()});
		}

		setVendor(result);

		s_logger.log(Level.FINE, "dbVendorLoaded", new Object[] {getDirection(), result});
		s_logger.flush();
	}

	/**
	 * retrieves a useful catalog (as search parameter for jdbc functions)
	 */
	private void loadDBCatalog() {

		s_logger.log(Level.FINE, "loadDBCatalog", getDirection());

		if (getCatalog() == null) {
			try {
				DatabaseMetaData md = m_connection.getMetaData();
				String url = md.getURL();
				String user = md.getUserName();
				ResultSet rs = md.getCatalogs();
				while (getResultSetNext(rs)) {
					String s = getResultSetString(rs, "TABLE_CAT");
					if (user.equalsIgnoreCase(s)
							|| url.toUpperCase().contains(s.toUpperCase())) {
						setCatalog(s);
					}
				}
				releaseResultSet(rs);
			} catch (SQLException e) {
				s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "loadDBCatalogError", new Object[] {getDirection(), e.getMessage()});
			}
		}

		s_logger.log(Level.FINE, "dbCatalogLoaded", new Object[] {getDirection(), getCatalog()});
		s_logger.flush();
	}

	/**
	 * retrieves a useful schema (as search parameter for jdbc functions)
	 */
	private void loadDBSchema() {

		s_logger.log(Level.FINE, "loadDBSchema", getDirection());

		if (getSchema() == null) {
			try {
				DatabaseMetaData md = m_connection.getMetaData();
				String url = md.getURL();
				String user = md.getUserName();
				ResultSet rs  = md.getSchemas();
				while (getResultSetNext(rs)) {
					String s = rs.getString("TABLE_SCHEM");
					if (user.equalsIgnoreCase(s)
							|| url.toUpperCase().contains(s.toUpperCase())) {
						setSchema(s);
					}
				}
				releaseResultSet(rs);
			} catch (SQLException e) {
				s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "loadDBSchemaError", new Object[] {getDirection(), e.getMessage()});
			}
		} else {
			// in some databases, the schema must be same as user
			setSchema(s_dbEngine.getDBSchemaOrUser(getVendor(), getSchema(), getUser()));
		}

		s_logger.log(Level.FINE, "dbSchemaLoaded", new Object[] {getDirection(), getSchema()});
		s_logger.flush();
	}

	/**
	 * drop a schema in a database
	 */
	private void dropDBSchema() {

		setDoNotInterrupt(true);

		s_logger.log(Level.FINE, "dropDBSchema", new Object[] {getSchema(), getDirection()});

		// drop schema
		try {
			Statement stmt = setStatement();
			DatabaseMetaData md = m_connection.getMetaData();
			ResultSet rs = md.getSchemas();
			while (getResultSetNext(rs)) {
				if (getResultSetString(rs, "TABLE_SCHEM").equalsIgnoreCase(getSchema())) {
					int step = 0;
					String sql = s_dbEngine.sqlAdmin_dropSchema(step, getVendor(), getCatalog(), getSchema());
					while (sql != null) {
						executeUpdate(stmt, sql, false, false);
						step ++;
						sql = s_dbEngine.sqlAdmin_dropSchema(step, getVendor(), getCatalog(), getSchema());
					}
				}
			}
			releaseResultSet(rs);
			releaseStatement(stmt);
		} catch (SQLException e) {
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "dropDBSchemaError", new Object[] {getSchema(), e.getMessage()});
		}

		s_logger.log(Level.FINE, "dbSchemaDropped", getSchema());
		s_logger.flush();
	}

	/**
	 * create a schema in a database
	 */
	private void createDBSchema() {

		setDoNotInterrupt(true);

		s_logger.log(Level.FINE, "createDBSchema", new Object[] {getSchema(), getDirection()});

		// create schema
		// some databases need more than one sql call, so we loop until no more is coming
		Statement stmt = setStatement();
		int step = 0;
		String sql = s_dbEngine.sqlAdmin_createSchema(step, getVendor(), getCatalog(), getSchema(), getPasswd());
		while (sql!=null) {
			executeUpdate(stmt, sql, false, false);
			step++;
			sql = s_dbEngine.sqlAdmin_createSchema(step, getVendor(), getCatalog(), getSchema(), getPasswd());
		}
		releaseStatement(stmt);
		commitChanges();

		// re-logon as normal user
		connectDatabase();


		s_logger.log(Level.FINE, "dbSchemaCreated", getSchema());
		s_logger.flush();
	}

	/**
	 * drop and re-create a schema in a database
	 */
	private void reCreateDBSchema() {

		s_logger.log(Level.FINE, "recreateDBSchema", new Object[] {getSchema(), getDirection()});

		// re-logon as system user
		connectDatabaseSystem();

		// drop schema
		dropDBSchema();

		// (re-)create schema
		createDBSchema();

		// re-logon as normal user
		connectDatabase();

		s_logger.log(Level.FINE, "dbSchemaRecreated", getSchema());
		s_logger.flush();
	}

	/**
	 * connect to the schema
	 */
	private void connectDBSchema() {

		s_logger.log(Level.FINE, "connectDBSchema", new Object[] {getSchema(), getDirection()});

		Statement stmt = setStatement();
		int step = 0;
		String sql = s_dbEngine.sqlAdmin_connectSchema(step, getVendor(), getCatalog(), getSchema());
		while (sql != null) {
			executeUpdate(stmt, sql, false, true);
			step ++;
			sql = s_dbEngine.sqlAdmin_connectSchema(step, getVendor(), getCatalog(), getSchema());
		}
		releaseStatement(stmt);

		s_logger.log(Level.FINE, "dbSchemaConnected", getSchema());
		s_logger.flush();
	}

	/**
	 * loads the size of a character in a database's national language
	 * (needed because buggy ORACLE jdbc driver sometimes uses size of
	 * BYTE instead of size of CHAR)
	 */
	private void loadDBCharSize() {

		s_logger.log(Level.FINE, "loadDBCharSize", getDirection());

		String sql = null;
		m_charDevisor = 1;
		int vc2Size=1;
		int nvc2Size=1;

		sql = s_dbEngine.sqlMetadata_openCharSetTest(getVendor(), getCatalog(), getSchema());
		if (sql!=null && sql.length()>0) {
			try {
				Statement stmt = setStatement();
				executeUpdate(stmt, sql, false, true);
				DatabaseMetaData md = m_connection.getMetaData();
				ResultSet rs = md.getColumns(null, null, "CHARSETTEST", null);
				while (getResultSetNext(rs)) {
					String c = getResultSetString(rs, "COLUMN_NAME");
					int i = getResultSetInt(rs, "COLUMN_SIZE");
					if (c.equalsIgnoreCase("VC2"))
						vc2Size=i;
					else
						nvc2Size=i;
				}
				releaseResultSet(rs);
				releaseStatement(stmt);
			} catch (SQLException e) {
				s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "loadDBCharSizeError", new Object[] {getDirection(), e.getMessage()});
			}
		}

		sql = s_dbEngine.sqlMetadata_closeCharSetTest(getVendor(), getCatalog(), getSchema());
		if (sql!=null && sql.length()>0) {
			Statement stmt = setStatement();
			executeUpdate (stmt, sql, false, true);
			releaseStatement(stmt);
		}

		if (vc2Size != 0)
			m_charDevisor = nvc2Size / vc2Size;

		s_logger.log(Level.FINE, "dbCharSizeLoaded", Integer.toString(m_charDevisor));
		s_logger.flush();
	}

	/**
	 * checks whether savepoints are releasable
	 * (needed because buggy ORACLE jdbc driver does not allow to release savepoints)
	 */
	private void loadDBisSavepointReleaseable() {

		s_logger.log(Level.FINE, "loadDBisSavepointReleaseable", getDirection());

		// by default, savepoints should be releaseable
		m_isSavepointReleaseable = true;

		Savepoint sp = null;

		// set a savepoint for testing
		try {
			sp = getConnection().setSavepoint("releasetest");

			// and immediately release it again
			try {
				getConnection().releaseSavepoint(sp);
			} catch (SQLException e) {
				// if an exception is thrown, it means savepoints are not releaseable in this jdbc driver
				m_isSavepointReleaseable = false;
			} finally {
				sp = null;
			}

		} catch (SQLException e) {
			// if savepoint can not be created, we just assume the default which means savepoints are releaseable
			sp = null;
		}

		if (m_isSavepointReleaseable)
			s_logger.log(Level.FINE, "dbisSavepointReleaseableYes");
		else
			s_logger.log(Level.FINE, "dbisSavepointReleaseableNo");
		s_logger.flush();
	}


	/**
	 * load objects defined in the database
	 * @param objectClass class of objects to load
	 * @return map of objects
	 */
	@SuppressWarnings("unchecked")
	private HashMap<String, DBObject> loadDBObjects (Class objectClass) {

		// the return value - an empty hashmap if something goes wrong
		HashMap <String, DBObject> objectMap = new HashMap <String, DBObject>();

		// create an object of the class we want to load (just for access to its static members)
		DBObject dbObject = new DBObject(this, objectClass);

		String objectToLoad = s_logger.localizeMessage("object");
		String objectsToLoad = s_logger.localizeMessage("objects");
		String sqlLoadHeaders = null;
		String sqlLoadContents = null;
		if (dbObject != null ) {
			objectToLoad = dbObject.getObjectType();
			objectsToLoad = dbObject.getObjectTypes();
			sqlLoadHeaders = dbObject.getLoadHeaderSQL();
			sqlLoadContents = dbObject.getLoadContentSQL();
		}

		s_logger.log(Level.FINE, "loadDBObjects", new Object[] {objectsToLoad, getDirection()});

		// pre-compile sql statement for loading header information
		PreparedStatementWrapper stmtLoadHeaders = setPreparedStatement(sqlLoadHeaders);

		// pre-compile sql statement for loading content information
		PreparedStatementWrapper stmtLoadContents = setPreparedStatement(sqlLoadContents);

		// actual loading of database objects
		int counter = 0;
		if (dbObject != null ) {
			String sql = dbObject.getLoadObjectSQL();
			Statement stmt = setStatement();
			ResultSet rs = executeQuery(stmt, sql);
			while (getResultSetNext(rs)) {
				String s = getResultSetString(rs, "OBJECT_NAME");
				if (!objectMap.containsKey(s)) {
					DBObject obj = new DBObject(this, objectClass, s);
					obj.populate(stmtLoadHeaders, stmtLoadContents);
					// only add objects that have been populated
					if (obj.isPopulated()) {
						objectMap.put(s.toUpperCase(), obj);
						counter++;
					}
				}
			}
			releaseResultSet(rs);
			releaseStatement(stmt);
		}

		// release prepared statement for loading headers from database
		releasePreparedStatement(stmtLoadHeaders);

		// release prepared statement for loading contents from database
		releasePreparedStatement(stmtLoadContents);

		if (counter == 1)
			s_logger.log(Level.FINE, "dbObjectsLoaded", new Object[] {Integer.toString(counter), objectToLoad});
		else
			s_logger.log(Level.FINE, "dbObjectsLoaded", new Object[] {Integer.toString(counter), objectsToLoad});

		s_logger.flush();

		return objectMap;
	}

	/**
	 * load Custom Entity Type Prefixes defined in Application Dictionary
	 * @param sourcePrefixes prefixes which are already defined in source
	 */
	private void loadCustomPrefixes(ArrayList<String> sourcePrefixes) {

		s_logger.log(Level.FINE, "loadCustomPrefixes", getDirection());

		ArrayList<String> entityTypes = new ArrayList<String>();
		StringBuffer result = new StringBuffer();
		String sql = s_dbEngine.sqlAD_getCustomEntityPrefixes(getVendor(), getCatalog(), getSchema());
		ResultSet rs = null;
		java.sql.Statement stmt = null;

		if (isObjectExists("ad_entitytype", m_tables)) {
			stmt = setStatement();
			rs = executeQuery(stmt, sql);
			while (getResultSetNext(rs)) {
				String s = getResultSetString(rs, "EntityType").toUpperCase();
				if (!entityTypes.contains(s)) {
					if (isTarget()) {
						if (sourcePrefixes!=null) {
							// exclude prefixes which are already defined in source
							// (the source is the system reference database, so if the source
							// includes customizations, they should be regarded as system
							// entities and replace those in the target database)
							if (! sourcePrefixes.contains(s)) {
								entityTypes.add(s);
							}
						} else {
							entityTypes.add(s);
						}
					} else {
						entityTypes.add(s);
					}
				}
			}
			releaseResultSet(rs);
			releaseStatement(stmt);
		}

		// make sure the default types are included
		if (!entityTypes.contains("CUST"))
			entityTypes.add("CUST");
		if (!entityTypes.contains("EXT"))
			entityTypes.add("EXT");
		if (!entityTypes.contains("XX"))
			entityTypes.add("XX");

		// sort and save list
		java.util.Collections.sort(entityTypes);
		m_customPrefixes = new ArrayList<String>(entityTypes);

		// build formatted output string
		for (Iterator<String> it = entityTypes.iterator(); it.hasNext();) {
			String s = it.next();
			if (result.length()>0)
				result.append(", ");
			result.append(s).append("_");
		}

		s_logger.log(Level.FINE, "customPrefixesLoaded", new Object[] {getDirection(), result});
		s_logger.flush();
	}

	/**
	 * load Custom Entity Types defined in Application Dictionary
	 * @param sourceEntities entities which are already defined in source
	 */
	private void loadCustomEntities(ArrayList<String> sourceEntities) {

		s_logger.log(Level.FINE, "loadCustomEntities", getDirection());

		ArrayList<String> entityTypes = new ArrayList<String>();
		String sql = s_dbEngine.sqlAD_getCustomEntityTypes(getVendor(), getCatalog(), getSchema());
		ResultSet rs = null;
		java.sql.Statement stmt = null;

		if (isObjectExists("ad_entitytype", m_tables)) {
			stmt = setStatement();
			rs = executeQuery(stmt, sql);
			while (getResultSetNext(rs)) {
				String s = getResultSetString(rs, "EntityType").toUpperCase();
				if (!entityTypes.contains(s)) {
					if (isTarget()) {
						if (sourceEntities!=null) {
							// exclude entities which are already defined in source
							// (the source is the system reference database, so if the source
							// includes customizations, they should be regarded as system
							// entities and replace those in the target database)
							if (! sourceEntities.contains(s)) {
								entityTypes.add(s);
							}
						} else {
							entityTypes.add(s);
						}
					} else {
						entityTypes.add(s);
					}
				}
			}
			releaseResultSet(rs);
			releaseStatement(stmt);
		}

		// make sure the default types are included
		if (!entityTypes.contains("A"))
			entityTypes.add("A");
		if (!entityTypes.contains("CUST"))
			entityTypes.add("CUST");
		if (!entityTypes.contains("EXT"))
			entityTypes.add("EXT");
		if (!entityTypes.contains("U"))
			entityTypes.add("U");
		if (!entityTypes.contains("XX"))
			entityTypes.add("XX");

		// sort and save list
		java.util.Collections.sort(entityTypes);
		m_customEntities = new ArrayList<String>(entityTypes);

		// build formatted output string
		StringBuffer result = new StringBuffer();
		for (Iterator<String> it = m_customEntities.iterator(); it.hasNext();) {
			String s = it.next();
			if (result.length()>0)
				result.append(", ");
			result.append(s);
		}

		s_logger.log(Level.FINE, "customEntitiesLoaded", new Object[] {getDirection(), result});
		s_logger.flush();
	}

	/**
	 * load languages which are actively being used in the system
	 */
	private ArrayList<String> loadSystemLanguages() {

		s_logger.log(Level.FINE, "loadSystemLanguages", getDirection());

		ArrayList<String> systemClients = new ArrayList<String> ();
		String sql = s_dbEngine.sqlAD_getSystemLanguages(getVendor(), getCatalog(), getSchema());
		ResultSet rs = null;
		Statement stmt = null;

		if (isObjectExists("ad_language", m_tables)) {
			stmt = setStatement();
			rs = executeQuery(stmt, sql);
			while (getResultSetNext(rs)) {
				String s = getResultSetString(rs, "AD_Language");
				systemClients.add(s);
			}
			releaseResultSet(rs);
			releaseStatement(stmt);
		}


		// build formatted output string
		StringBuffer result = new StringBuffer();
		for (Iterator<String> it = systemClients.iterator(); it.hasNext();) {
			String s = it.next();
			if (result.length()>0)
				result.append(", ");
			result.append(s);
		}

		s_logger.log(Level.FINE, "systemLanguagesLoaded", new Object[] {getDirection(), result});
		s_logger.flush();

		return systemClients;
	}

	/**
	 * load system-level clients defined in Application Dictionary
	 */
	private HashMap<Integer, String> loadSystemClients() {

		s_logger.log(Level.FINE, "loadSystemClients", getDirection());

		HashMap<Integer, String> systemClients = new HashMap<Integer, String>();
		String sql = s_dbEngine.sqlAD_getSystemClients(getVendor(), getCatalog(), getSchema());
		ResultSet rs = null;
		Statement stmt = null;

		if (isObjectExists("ad_client", m_tables)) {
			stmt = setStatement();
			rs = executeQuery(stmt, sql);
			while (getResultSetNext(rs)) {
				int i = getResultSetInt(rs, "AD_CLIENT_ID");
				String s = getResultSetString(rs, "NAME");
				systemClients.put(i, s);
			}
			releaseResultSet(rs);
			releaseStatement(stmt);
		}

		// make sure the default system clients are included
		if (!systemClients.containsKey(0))
			systemClients.put(0, "SYSTEM");
		if (!systemClients.containsKey(11))
			systemClients.put(11, "GardenWorld");

		// sort hashmap list
		Vector<Integer> v = new Vector<Integer>(systemClients.keySet());
		java.util.Collections.sort(v);

		// build formatted output string
		StringBuffer result = new StringBuffer();
		for (Iterator<Integer> it = v.iterator(); it.hasNext();) {
			int key = it.next();
			String s = systemClients.get(key);
			if (result.length()>0)
				result.append(", ");
			result.append(s);
		}

		s_logger.log(Level.FINE, "systemClientsLoaded", new Object[] {getDirection(), result});
		s_logger.flush();

		return systemClients;
	}

	/**
	 * load sequence counters defined in Application Dictionary
	 */
	private void loadADSequences() {

		if (! isObjectExists("AD_Sequence", m_tables))
			return;

		s_logger.log(Level.FINE, "loadADSequences", getDirection());

		String sql = s_dbEngine.sqlAD_getSequences(getVendor(), getCatalog(), getSchema());
		ResultSet rs = null;
		Statement stmt = null;

		m_sequenceSystem = new HashMap<String, Long> ();
		m_sequenceUser = new HashMap<String, Long> ();

		stmt = setStatement();
		rs = executeQuery(stmt, sql);
		while (getResultSetNext(rs)) {
			String seqName = getResultSetString(rs, "SEQ_NAME");
			if (seqName!=null) {
				seqName = seqName.toUpperCase();
				long seqSystem = getResultSetLong(rs, "SEQ_SYS");
				long seqUser = getResultSetLong(rs, "SEQ_USER");
				m_sequenceSystem.put(seqName, seqSystem);
				m_sequenceUser.put(seqName, seqUser);
			}
		}
		releaseResultSet(rs);
		releaseStatement(stmt);

		s_logger.log(Level.FINE, "adSequencesLoaded", Integer.toString(m_sequenceSystem.size()));
		s_logger.flush();

		return;
	}

	/**
	 * @return whether or not this is the source connection
	 */
	private boolean isSource () {
		return !m_isTarget;
	}

	/**
	 * @return whether or not this is the target connection
	 */
	private boolean isTarget () {
		return m_isTarget;
	}

	/**
	 * gets the direction of this connection
	 * @return source or target
	 */
	public String getDirection() {
		if (isTarget())
			return s_logger.localizeMessage("target");
		else
			return s_logger.localizeMessage("source");
	}

	/**
	 * @return whether or not table IDs should be preserved through migration
	 */
	private boolean isPreserveTableIDs() {
		return s_parameters.isPreserveTableID();
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return m_connection;
	}

	/**
	 * @return the database vendor
	 */
	public String getVendor() {
		if (isSource())
			return s_parameters.getSourceVendor();
		else
			return s_parameters.getTargetVendor();
	}

	/**
	 * @param vendor the database vendor
	 */
	private void setVendor(String vendor) {
		if (isSource())
			s_parameters.setSourceVendor(vendor);
		else
			s_parameters.setTargetVendor(vendor);
	}

	/**
	 * @return the database server
	 */
	private String getHost() {
		if (isSource())
			return s_parameters.getSourceHost();
		else
			return s_parameters.getTargetHost();
	}

	/**
	 * @param host the database server
	 */
	private void setHost(String host) {
		if (isSource())
			s_parameters.setSourceHost(host);
		else
			s_parameters.setTargetHost(host);
	}

	/**
	 * @return the database port
	 */
	private String getPort() {
		if (isSource())
			return s_dbEngine.getDBPort(getVendor(), s_parameters.getSourcePort());
		else
			return s_dbEngine.getDBPort(getVendor(), s_parameters.getTargetPort());
	}

	/**
	 * @param port the database port
	 */
	private void setPort(String port) {
		if (isSource())
			s_parameters.setSourcePort(s_dbEngine.getDBPort(getVendor(), port));
		else
			s_parameters.setTargetPort(s_dbEngine.getDBPort(getVendor(), port));
	}


	/**
	 * @return the database name
	 */
	private String getName() {
		if (isSource())
			return s_parameters.getSourceName();
		else
			return s_parameters.getTargetName();
	}

	/**
	 * param name the database name
	 */
	private void setName(String name) {
		if (isSource())
			s_parameters.setSourceName(name);
		else
			s_parameters.setTargetName(name);
	}

	/**
	 * @return the database driver
	 */
	private String getDriver() {
		return m_driver;
	}

	/**
	 * @param driver the database driver
	 */
	private void setDriver(String driver) {
		m_driver = driver;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return m_url;
	}

	/**
	 * @param url the database url
	 */
	private void setUrl(String url) {
		m_url = url;
	}

	/**
	 * @return the database catalog
	 */
	public String getCatalog() {
		if (isSource())
			return s_parameters.getSourceCatalog();
		else
			return s_parameters.getTargetCatalog();
	}

	/**
	 * param catalog the database catalog
	 */
	private void setCatalog(String catalog) {
		if (isSource())
			s_parameters.setSourceCatalog(catalog);
		else
			s_parameters.setTargetCatalog(catalog);
	}

	/**
	 * @return the database schema
	 */
	public String getSchema() {
		if (isSource())
			return s_parameters.getSourceSchema();
		else
			return s_parameters.getTargetSchema();
	}

	/**
	 * param schema the database schema
	 */
	private void setSchema(String schema) {
		if (isSource())
			s_parameters.setSourceSchema(schema);
		else
			s_parameters.setTargetSchema(schema);
	}

	/**
	 * @return the database user
	 */
	private String getUser() {
		if (isSource())
			return s_parameters.getSourceUser();
		else
			return s_parameters.getTargetUser();
	}

	/**
	 * param user the database user
	 */
	private void setUser(String user) {
		if (isSource())
			s_parameters.setSourceUser(user);
		else
			s_parameters.setTargetUser(user);
	}

	/**
	 * @return the database user's password
	 */
	private String getPasswd() {
		if (isSource())
			return s_parameters.getSourcePasswd();
		else
			return s_parameters.getTargetPasswd();
	}

	/**
	 * param passwd the database user's password
	 */
	private void setPasswd(String passwd) {
		if (isSource())
			s_parameters.setSourcePasswd(passwd);
		else
			s_parameters.setTargetPasswd(passwd);
	}

	/**
	 * @return the system user
	 */
	private String getSystemUser() {
		if (isSource())
			return s_parameters.getSourceSystemUser();
		else
			return s_parameters.getTargetSystemUser();
	}

	/**
	 * param systemUser the system user
	 */
	private void setSystemUser(String systemUser) {
		if (isSource())
			s_parameters.setSourceSystemUser(systemUser);
		else
			s_parameters.setTargetSystemUser(systemUser);
	}

	/**
	 * @return the system user's password
	 */
	private String getSystemPasswd() {
		if (isSource())
			return s_parameters.getSourceSystemPasswd();
		else
			return s_parameters.getTargetSystemPasswd();
	}

	/**
	 * param passwd the system user's password
	 */
	private void setSystemPasswd(String systemPasswd) {
		if (isSource())
			s_parameters.setSourceSystemPasswd(systemPasswd);
		else
			s_parameters.setTargetSystemPasswd(systemPasswd);
	}

	/**
	 * @return the charDevisor to fix oracle jdbc bug
	 */
	public int getCharDevisor() {
		return m_charDevisor;
	}

	/**
	 * @return the tables
	 */
	public HashMap<String, DBObject> getTables() {
		return m_tables;
	}

	/**
	 * @return the views
	 */
	public HashMap<String, DBObject> getViews() {
		return m_views;
	}

	/**
	 * @return the functions
	 */
	public HashMap<String, DBObject> getFunctions() {
		return m_functions;
	}

	/**
	 * @return the triggers
	 */
	public HashMap<String, DBObject> getTriggers() {
		return m_triggers;
	}

	/**
	 * @return the sequences
	 */
	public HashMap<String, DBObject> getSequences() {
		return m_sequences;
	}

	/**
	 * get current sequence metadata
	 * @return current snapshot of sequences
	 */
	public HashMap<String, DBObject> reloadSequences() {
		return (loadDBObjects(DBObject_Sequence.class));
	}

	/**
	 * @return the primaryKeys
	 */
	public HashMap<String, DBObject> getPrimaryKeys() {
		return m_primaryKeys;
	}

	/**
	 * @return the foreignKeys
	 */
	public HashMap<String, DBObject> getForeignKeys() {
		return m_foreignKeys;
	}

	/**
	 * @return the checks
	 */
	public HashMap<String, DBObject> getChecks() {
		return m_checks;
	}

	/**
	 * @return the uniques
	 */
	public HashMap<String, DBObject> getUniques() {
		return m_uniques;
	}

	/**
	 * @return the indexes
	 */
	public HashMap<String, DBObject> getIndexes() {
		return m_indexes;
	}

	/**
	 * @return the customPrefixes
	 */
	public ArrayList<String> getCustomPrefixes() {
		return m_customPrefixes;
	}

	/**
	 * @return the customEntities
	 */
	public ArrayList<String> getCustomEntities() {
		return m_customEntities;
	}

	/**
	 * checks whether an object name already exists in the database
	 * @param objectName the name of the object to search for
	 * @param objectMap the map of objects to search in
	 * @return whether or not the named object exists
	 */
	public boolean isObjectExists(String objectName, HashMap<String, DBObject> objectMap) {

		boolean result = false;

		if (objectMap!=null && objectMap.size()>0) {
			for (Iterator<String> it = objectMap.keySet().iterator(); it.hasNext();) {
				String key = it.next();
				if (key.equalsIgnoreCase(objectName))
					result = true;
				else if (objectMap.get(key).isName(objectName))
					result = true;
			}
		}

		return result;
	}

	/**
	 * returns a database object which has the specified name
	 * @param objectName the name of the object to search for
	 * @param objectMap the map of objects to search in
	 * @return the named database object
	 */
	public DBObject getObjectByName(String objectName, HashMap<String, DBObject> objectMap) {

		DBObject result = null;

		if (objectMap!=null && objectMap.size()>0) {
			for (Iterator<String> it = objectMap.keySet().iterator(); it.hasNext();) {
				String key = it.next();
				if (key.equalsIgnoreCase(objectName) || objectMap.get(key).isName(objectName))
					result = objectMap.get(key);
			}
		}

		return result;
	}

	/**
	 * checks whether an entity is named with a custom prefix
	 * @param entity the entity to check
	 * @return true if entity name starts with custom prefix
	 */
	public boolean isCustomPrefix(String entity) {

		boolean result = false;

		int i = entity.indexOf("_");
		if (i != -1) {
			String prefix = entity.substring(0, entity.indexOf("_"));
			result = m_customPrefixes.contains(prefix.toUpperCase());
		}

		return result;

	}

	/**
	 * checks whether an Entity is a custom entity type
	 * @param entity the entity to check
	 * @return true if it is a custom entity type
	 */
	public boolean isCustomEntityType(String entity) {

		boolean result = false;

		result = m_customEntities.contains(entity.toUpperCase());

		return result;

	}

	/**
	 * @return the operators
	 */
	public HashMap<String, DBObject> getOperators() {
		return m_operators;
	}

	/**
	 * @return the systemClients
	 */
	public HashMap<Integer, String> getSystemClients() {
		return m_systemClients;
	}

	/**
	 * @return the systemLanguages
	 */
	public ArrayList<String> getSystemLanguages() {
		return m_systemLanguages;
	}

	/**
	 * pre-determines customization-level information into database objects
	 * @param objectClass class of objects to load
	 */
	@SuppressWarnings("unchecked")
	private void loadCustomizationLevel (Class objectClass) {

		// create an object of the class we want to load (just for access to its static members)
		DBObject dbObject = new DBObject(this, objectClass);
		String objectsToLoad = s_logger.localizeMessage("objects");

		HashMap<String, DBObject> map = null;
		if (dbObject != null ) {
			objectsToLoad = dbObject.getObjectTypes();
			if (objectClass.getSimpleName().equals("DBObject_Table")) {
				map = getTables();
			} else if (objectClass.getSimpleName().equals("DBObject_View")) {
				map = getViews();
			} else if (objectClass.getSimpleName().equals("DBObject_Operator")) {
				map = getOperators();
			} else if (objectClass.getSimpleName().equals("DBObject_Function")) {
				map = getFunctions();
			} else if (objectClass.getSimpleName().equals("DBObject_Trigger")) {
				map = getTriggers();
			} else if (objectClass.getSimpleName().equals("DBObject_Sequence")) {
				map = getSequences();
			} else if (objectClass.getSimpleName().equals("DBObject_PrimaryKey")) {
				map = getPrimaryKeys();
			} else if (objectClass.getSimpleName().equals("DBObject_ForeignKey")) {
				map = getForeignKeys();
			} else if (objectClass.getSimpleName().equals("DBObject_Check")) {
				map = getChecks();
			} else if (objectClass.getSimpleName().equals("DBObject_Unique")) {
				map = getUniques();
			} else if (objectClass.getSimpleName().equals("DBObject_Index")) {
				map = getIndexes();
			}
		}

		if (map!=null) {
			s_logger.log(Level.FINE, "loadCustomizationLevel", new Object[] {objectsToLoad, getDirection()});

			// actual loading of customization levels
			int counter = 0;
			for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
				String key = it.next();
				// just call the getter to initialize the customization level of object and its sub-objects
				map.get(key).getCustomizationLevel();
				counter ++;
			}

			String logObject = s_logger.localizeMessage("customization levels");
			if (counter == 1)
				logObject = s_logger.localizeMessage("customization level");
			s_logger.log(Level.FINE, "customizationLevelLoaded", new Object[] {Integer.toString(counter), logObject});
		}

		s_logger.flush();

		return;
	}

	/**
	 * set a savepoint which we can rollback to after unsuccessful DB operation
	 * @param savepointName identifier of savepoint
	 * @return the savepoint
	 */
	public Savepoint setSavepoint(String savepointName) {

		Savepoint sp = null;

		// do nothing if this is not the target
		// (no changes are allowed in source, anyway)
		if (isSource())
			return null;

		// do nothing if a previous database error exists
		if (s_dbEngine.isDBError())
			return null;

		// do nothing if an SQL error already exists in the parent transaction
		if (s_dbEngine.getTransactionErrors()>0)
			return null;

		// use default name if none was provided
		if (savepointName==null || savepointName.length()==0)
			savepointName = "savepoint";

		// eliminate illegal characters from name
		savepointName = savepointName.replaceAll("\\s", "_");
		savepointName = savepointName.replaceAll("\\$", "_");

		// set a new savepoint
		try {
			sp = getConnection().setSavepoint(savepointName);
		} catch (SQLException e) {
			// exception when creating savepoint only means something wrong with database, transaction is not affected
			// (without savepoint, there is no transaction yet)
			s_dbEngine.setDBError(true);
			sp = null;
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "setSavepointError", new Object[] {savepointName, e.getMessage()});
		}

		// initialize error count if we have a valid savepoint
		if (isValidSavepoint(sp)) {
			s_dbEngine.resetTransactionErrors();
		}

		return sp;
	}

	/**
	 * roll back if necessary and then release the savepoint
	 * @param sp the savepoint to release
	 */
	public void releaseSavepoint (Savepoint sp) {

		// do nothing if this is not the target
		// (no changes are allowed in source, anyway)
		if (isSource())
			return;

		// do nothing if this is not a valid savepoint
		if (! isValidSavepoint(sp))
			return;

		// fetch savepoint name
		String savepointName = null;
		try {
			savepointName = sp.getSavepointName();
		} catch (SQLException e) {
			// exception when getting savepoint name means something wrong with database but transaction may be valid
			s_dbEngine.setDBError(true);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "releaseSavepointNoName", new Object[] {e.getMessage()});
		}
		if (savepointName==null || savepointName.length()==0)
			savepointName="savepoint";

		// fetch errors for this savepoint
		int errors = s_dbEngine.getTransactionErrors();

		// rollback to savepoint if errors were encountered
		if (errors>0) {

			// set SQL error flag
			s_dbEngine.resetTransactionErrors();

			// log number of errors
			// (no logging if we are called from a silent command)
			if (! savepointName.toUpperCase().startsWith("SILENT")) {
				String logObject = s_logger.localizeMessage("errors");
				if (errors == 1)
					logObject = s_logger.localizeMessage("error");
				s_logger.log(Level.CONFIG, "errorRollback", new Object[] {Integer.toString(errors), logObject, savepointName});
			}

			// rollback
			try {
				getConnection().rollback(sp);
			} catch (SQLException e) {
				// // exception when rolling back means something wrong with database but transaction may be valid
				// s_dbEngine.setDBError(true);
				// s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "releaseSavepointNoRollback", new Object[] {savepointName, e.getMessage()});

				// Savepoints may be invalid:
				// For example in oracle, when exeuting a ddl statement, COMMIT is implied, which
				// invalidates the savepoint
				// Since jdbc does not allow checking whether a savepoint is valid we just ignore any errors
			}
		}

		// release savepoint
		try {
			if (m_isSavepointReleaseable) {
				getConnection().releaseSavepoint(sp);
			}
		} catch (SQLException e) {
			// exception when closing savepoint means something wrong with database but transaction may be valid
			s_dbEngine.setDBError(true);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "releaseSavepointError", new Object[] {savepointName, e.getMessage()});
		} finally {
			sp = null;
		}
	}

	/**
	 * checks whether a savepoint is valid
	 * @param sp the savepoint to check
	 * @return the savepoint is valid
	 */
	public boolean isValidSavepoint (Savepoint sp) {

		// savepoint can only be valid if this is the target
		// (changes are not allowed in source, anyway)
		if (isSource())
			return false;

		boolean result = false;

		if (sp != null)
			result = true;

		return result;
	}

	/**
	 * create a prepared statement for later execution
	 * @param sqlCommand the statement to prepare
	 * @return the prepared statement
	 */
	public PreparedStatementWrapper setPreparedStatement(String sqlCommand) {

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return null;

		// do nothing if SQL command is invalid
		if (sqlCommand==null || sqlCommand.length()==0)
			return null;

		// prepare the statement
		PreparedStatementWrapper stmt = null;
		try {
			stmt = new PreparedStatementWrapper(getConnection(), sqlCommand);
		} catch (SQLException e) {
			// exception when creating statement means something wrong with database and transaction is invalid
			s_dbEngine.setDBError(true);
			s_dbEngine.setTransactionErrors(1);
			stmt = null;
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "setPreparedStatementError", new Object[] {sqlCommand, e.getMessage()});
		}

		return stmt;
	}

	/**
	 * release a prepared statement
	 * @param stmt the prepared statement to release
	 */
	public void releasePreparedStatement(PreparedStatement stmt) {

		// do nothing if this is not a valid prepared statement
		if (stmt == null)
			return;

		// get a string representation of this prepared statement
		String sqlCommand = stmt.toString();

		// reset parameters
		try {
			stmt.clearParameters();
		} catch (SQLException e) {
			// exception when clearing statement parameters means something wrong with database but transaction may be valid
			s_dbEngine.setDBError(true);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "releasePreparedStatementNoReset", new Object[] {sqlCommand, e.getMessage()});
		}

		// close prepared statement
		try {
			stmt.close();
		} catch (SQLException e) {
			// exception when closing statement means something wrong with database but transaction may be valid
			s_dbEngine.setDBError(true);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "releasePreparedStatementError", new Object[] {sqlCommand, e.getMessage()});
		} finally {
			stmt = null;
		}
	}

	/**
	 * retrieve the number of parameters a prepared statement contains
	 * @param stmt the prepared statement for which to gather information
	 * @return the number of parameters
	 */
	public int getPreparedStatementParameterCount (PreparedStatement stmt) {

		int result = 0;

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return result;

		// do nothing if this is not a valid prepared statement
		if (stmt == null)
			return result;

		// get a string representation of this prepared statement
		String sqlCommand = stmt.toString();

		// find out number of parameters
		try {
			result = stmt.getParameterMetaData().getParameterCount();
		} catch (SQLException e) {
			// exception when counting parameters means something wrong with database but transaction may be valid
			s_dbEngine.setDBError(true);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "getPreparedStatementParameterCountError", new Object[] {sqlCommand, e.getMessage()});
		}

		return result;
	}

	/**
	 * set parameter in a prepared statement to given big decimal
	 * @param stmt the prepared statement in which to set the parameter
	 * @param parameterIndex index of the parameter to set (1-based)
	 * @param x the new parameter value
	 */
	public void setPreparedStatementBigDecimal (PreparedStatement stmt, int parameterIndex, BigDecimal x) {

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return;

		// do nothing if this is not a valid prepared statement
		if (stmt == null)
			return;

		// get a string representation of this prepared statement
		String sqlCommand = stmt.toString();

		// set the parameter
		try {
			stmt.setBigDecimal(parameterIndex, x);
		} catch (SQLException e) {
			// exception when setting parameter means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "setPreparedStatementParameterError", new Object[] {Integer.toString(parameterIndex), sqlCommand, e.getMessage()});
		}
	}

	/**
	 * set parameter in a prepared statement to given byte sequence
	 * @param stmt the prepared statement in which to set the parameter
	 * @param parameterIndex index of the parameter to set (1-based)
	 * @param x the new parameter value
	 */
	public void setPreparedStatementBytes (PreparedStatement stmt, int parameterIndex, byte[] x) {

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return;

		// do nothing if this is not a valid prepared statement
		if (stmt == null)
			return;

		// get a string representation of this prepared statement
		String sqlCommand = stmt.toString();

		// set the parameter
		try {
			stmt.setBytes(parameterIndex, x);
		} catch (SQLException e) {
			// exception when setting parameter means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "setPreparedStatementParameterError", new Object[] {Integer.toString(parameterIndex), sqlCommand, e.getMessage()});
		}
	}

	/**
	 * set parameter in a prepared statement to given clob
	 * @param stmt the prepared statement in which to set the parameter
	 * @param parameterIndex index of the parameter to set (1-based)
	 * @param x the new parameter value
	 */
	public void setPreparedStatementClob (PreparedStatement stmt, int parameterIndex, Clob x) {

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return;

		// do nothing if this is not a valid prepared statement
		if (stmt == null)
			return;

		// get a string representation of this prepared statement
		String sqlCommand = stmt.toString();

		// set the parameter
		try {
			stmt.setClob(parameterIndex, x);
		} catch (SQLException e) {
			// exception when setting parameter means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "setPreparedStatementParameterError", new Object[] {Integer.toString(parameterIndex), sqlCommand, e.getMessage()});
		}
	}

	/**
	 * set parameter in a prepared statement to given integer
	 * @param stmt the prepared statement in which to set the parameter
	 * @param parameterIndex index of the parameter to set (1-based)
	 * @param x the new parameter value
	 */
	public void setPreparedStatementInt (PreparedStatement stmt, int parameterIndex, int x) {

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return;

		// do nothing if this is not a valid prepared statement
		if (stmt == null)
			return;

		// get a string representation of this prepared statement
		String sqlCommand = stmt.toString();

		// set the parameter
		try {
			stmt.setInt(parameterIndex, x);
		} catch (SQLException e) {
			// exception when setting parameter means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "setPreparedStatementParameterError", new Object[] {Integer.toString(parameterIndex), sqlCommand, e.getMessage()});
		}
	}

	/**
	 * set parameter in a prepared statement to given long
	 * @param stmt the prepared statement in which to set the parameter
	 * @param parameterIndex index of the parameter to set (1-based)
	 * @param x the new parameter value
	 */
	public void setPreparedStatementLong (PreparedStatement stmt, int parameterIndex, long x) {

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return;

		// do nothing if this is not a valid prepared statement
		if (stmt == null)
			return;

		// get a string representation of this prepared statement
		String sqlCommand = stmt.toString();

		// set the parameter
		try {
			stmt.setLong(parameterIndex, x);
		} catch (SQLException e) {
			// exception when setting parameter means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "setPreparedStatementParameterError", new Object[] {Integer.toString(parameterIndex), sqlCommand, e.getMessage()});
		}
	}

	/**
	 * set parameter in a prepared statement to null
	 * @param stmt the prepared statement in which to set the parameter
	 * @param parameterIndex index of the parameter to set (1-based)
	 * @param sqlType the SQL type code defined in <code>java.sql.Types</code>
	 */
	public void setPreparedStatementNull (PreparedStatement stmt, int parameterIndex, int sqlType) {

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return;

		// do nothing if this is not a valid prepared statement
		if (stmt == null)
			return;

		// get a string representation of this prepared statement
		String sqlCommand = stmt.toString();

		// set the parameter
		try {
			stmt.setNull(parameterIndex, sqlType);
		} catch (SQLException e) {
			// exception when setting parameter means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "setPreparedStatementParameterError", new Object[] {Integer.toString(parameterIndex), sqlCommand, e.getMessage()});
		}
	}

	/**
	 * set parameter in a prepared statement to given object
	 * @param stmt the prepared statement in which to set the parameter
	 * @param parameterIndex index of the parameter to set (1-based)
	 * @param x the new parameter value
	 */
	public void setPreparedStatementObject (PreparedStatement stmt, int parameterIndex, Object x) {

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return;

		// do nothing if this is not a valid prepared statement
		if (stmt == null)
			return;

		// get a string representation of this prepared statement
		String sqlCommand = stmt.toString();

		// set the parameter
		try {
			stmt.setObject(parameterIndex, x);
		} catch (SQLException e) {
			// exception when setting parameter means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "setPreparedStatementParameterError", new Object[] {Integer.toString(parameterIndex), sqlCommand, e.getMessage()});
		}
	}

	/**
	 * set parameter in a prepared statement to given string
	 * @param stmt the prepared statement in which to set the parameter
	 * @param parameterIndex index of the parameter to set (1-based)
	 * @param x the new parameter value
	 */
	public void setPreparedStatementString (PreparedStatement stmt, int parameterIndex, String x) {

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return;

		// do nothing if this is not a valid prepared statement
		if (stmt == null)
			return;

		// get a string representation of this prepared statement
		String sqlCommand = stmt.toString();

		// set the parameter
		try {
			stmt.setString(parameterIndex, x);
		} catch (SQLException e) {
			// exception when setting parameter means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "setPreparedStatementParameterError", new Object[] {Integer.toString(parameterIndex), sqlCommand, e.getMessage()});
		}
	}

	/**
	 * set parameter in a prepared statement to given timestamp
	 * @param stmt the prepared statement in which to set the parameter
	 * @param parameterIndex index of the parameter to set (1-based)
	 * @param x the new parameter value
	 */
	public void setPreparedStatementTimestamp (PreparedStatement stmt, int parameterIndex, Timestamp x) {

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return;

		// do nothing if this is not a valid prepared statement
		if (stmt == null)
			return;

		// get a string representation of this prepared statement
		String sqlCommand = stmt.toString();

		// set the parameter
		try {
			stmt.setTimestamp(parameterIndex, x);
		} catch (SQLException e) {
			// exception when setting parameter means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "setPreparedStatementParameterError", new Object[] {Integer.toString(parameterIndex), sqlCommand, e.getMessage()});
		}
	}


	/**
	 * create a statement in which an sql command can later be executed
	 * @return the statement
	 */
	public Statement setStatement() {
		return setStatement(false);
	}

	/**
	 * create a statement in which an sql command can later be executed
	 * @param isMinimizeFootprint if true, limit the number of rows a resultset can contain to 1 to minimize the usage of memory
	 * @return the statement
	 */
	public Statement setStatement(boolean isMinimizeFootprint) {

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return null;

		// create the statement
		Statement stmt = null;
		try {
			stmt = getConnection().createStatement();

			if (isMinimizeFootprint) {
				stmt.setFetchSize(1);
			}

		} catch (SQLException e) {
			// exception when creating statement means something wrong with database and transaction is invalid
			s_dbEngine.setDBError(true);
			s_dbEngine.setTransactionErrors(1);
			stmt = null;
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "setStatementError", new Object[] {e.getMessage()});
		}

		return stmt;
	}

	/**
	 * release a statement
	 * @param stmt the statement to release
	 */
	public void releaseStatement(Statement stmt) {

		// do nothing if this is not a valid statement
		if (stmt == null)
			return;

		// close statement
		try {
			stmt.close();
		} catch (SQLException e) {
			// exception when closing statement means something wrong with database but transaction may be valid
			s_dbEngine.setDBError(true);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "releaseStatementError", new Object[] {e.getMessage()});
		} finally {
			stmt = null;
		}
	}

	/**
	 * run an SQL query from a prepared statement
	 * @param stmt prepared statement containing the SQL command to run
	 * @return result of the query
	 */
	public ResultSet executeQuery (PreparedStatement stmt) {

		ResultSet rs = null;

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return null;

		// do nothing if prepared statement is invalid
		if (stmt == null)
			return null;

		// get a String representation of the statement
		String sqlCommand = stmt.toString();

		// run SQL command
		try {
			s_logger.log(Level.FINEST, sqlCommand);

			rs = stmt.executeQuery();

		} catch (SQLException e) {
			// exception when running SQL command means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "executeQueryPreparedStatementError", new Object[] {sqlCommand, e.getMessage()});
			rs = null;
		}

		return rs;
	}

	/**
	 * run a new SQL query in a statement
	 * @param stmt statement in which the SQL command should be run
	 * @param sqlCommand the query to run
	 * @return result of the query
	 */
	public ResultSet executeQuery (Statement stmt, String sqlCommand) {

		ResultSet rs = null;

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return null;

		// do nothing if statement is invalid
		if (stmt == null)
			return null;

		// do nothing if SQL command is invalid
		if (sqlCommand==null || sqlCommand.length()==0)
			return null;

		// run SQL command
		try {
			s_logger.log(Level.FINEST, sqlCommand);

			rs = stmt.executeQuery(sqlCommand);

		} catch (SQLException e) {
			// exception when running SQL command means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "executeQuerySqlError", new Object[] {sqlCommand, e.getMessage()});
			rs = null;
		}

		return rs;
	}

	/**
	 * release a resultset
	 * @param rs the resultset to release
	 */
	public void releaseResultSet(ResultSet rs) {

		// do nothing if this is not a valid resultset
		if (rs == null)
			return;

		// get a string representation of this resultset
		String rsName = rs.toString();

		// close the resultset
		try {
			rs.close();
		} catch (SQLException e) {
			// exception when closing resultset means something wrong with database but transaction may be valid
			s_dbEngine.setDBError(true);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "releaseResultSetError", new Object[] {rsName, e.getMessage()});
		} finally {
			rs = null;
		}
	}

	/**
	 * get the next record from a resultset
	 * @param rs the resultset
	 * @return the resultset has a next value
	 */
	public boolean getResultSetNext(ResultSet rs) {

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return false;

		// do nothing if this is not a valid resultset
		if (rs == null)
			return false;

		// get string represenation of resultset
		String rsName = rs.toString();

		boolean result = false;

		try {
			if (rs.next() )
				result = true;
		} catch (SQLException e) {
			// exception when creating statement means something wrong with database and transaction is invalid
			s_dbEngine.setDBError(true);
			s_dbEngine.setTransactionErrors(1);
			result = false;
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "getResultSetNextError", new Object[] {rsName, e.getMessage()});
		}

		return result;
	}

	/**
	 * get value from named column in given result set as boolean
	 * @param rs the result set to read
	 * @param columnName name of the column to read
	 */
	public boolean getResultSetBoolean (ResultSet rs, String columnName) {

		boolean result = false;

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return result;

		// do nothing if this is not a valid result set
		if (rs == null)
			return result;

		// get a string representation of this resultset
		String rsName = rs.toString();

		// read the value
		try {
			result = rs.getBoolean(columnName);
		} catch (SQLException e) {
			// exception when reading values means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "getResultSetColumnError", new Object[] {columnName, rsName, e.getMessage()});
			result = false;
		}

		return result;
	}

	/**
	 * get value from named column in given result set as byte stream
	 * @param rs the result set to read
	 * @param columnName name of the column to read
	 */
	public byte[] getResultSetBytes (ResultSet rs, String columnName) {

		byte[] result = null;

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return result;

		// do nothing if this is not a valid result set
		if (rs == null)
			return result;

		// get a string representation of this resultset
		String rsName = rs.toString();

		// read the value
		try {
			result = rs.getBytes(columnName);
		} catch (SQLException e) {
			// exception when reading values means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "getResultSetColumnError", new Object[] {columnName, rsName, e.getMessage()});
			result = null;
		}

		return result;
	}

	/**
	 * get value from named column in given result set as clob
	 * @param rs the result set to read
	 * @param columnName name of the column to read
	 * @return value as clob
	 */
	public Clob getResultSetClob (ResultSet rs, String columnName) {

		Clob result = null;

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return result;

		// do nothing if this is not a valid result set
		if (rs == null)
			return result;

		// get a string representation of this resultset
		String rsName = rs.toString();

		// read the value
		try {
			result = rs.getClob(columnName);
		} catch (SQLException e) {
			// exception when reading values means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "getResultSetColumnError", new Object[] {columnName, rsName, e.getMessage()});
			result = null;
		}

		return result;
	}

	/**
	 * get value from named column in given result set as integer
	 * @param rs the result set to read
	 * @param columnName name of the column to read
	 */
	public int getResultSetInt (ResultSet rs, String columnName) {

		int result = 0;

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return result;

		// do nothing if this is not a valid result set
		if (rs == null)
			return result;

		// get a string representation of this resultset
		String rsName = rs.toString();

		// read the value
		try {
			result = rs.getInt(columnName);
		} catch (SQLException e) {
			// exception when reading values means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "getResultSetColumnError", new Object[] {columnName, rsName, e.getMessage()});
			result = 0;
		}

		return result;
	}

	/**
	 * get value from named column in given result set as long
	 * @param rs the result set to read
	 * @param columnName name of the column to read
	 */
	public long getResultSetLong (ResultSet rs, String columnName) {

		long result = 0;

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return result;

		// do nothing if this is not a valid result set
		if (rs == null)
			return result;

		// get a string representation of this resultset
		String rsName = rs.toString();

		// read the value
		try {
			result = rs.getLong(columnName);
		} catch (SQLException e) {
			// exception when reading values means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "getResultSetColumnError", new Object[] {columnName, rsName, e.getMessage()});
			result = 0;
		}

		return result;
	}

	/**
	 * get value from named column in given result set as object
	 * @param rs the result set to read
	 * @param columnName name of the column to read
	 */
	public Object getResultSetObject (ResultSet rs, String columnName) {

		Object result = null;

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return result;

		// do nothing if this is not a valid result set
		if (rs == null)
			return result;

		// get a string representation of this resultset
		String rsName = rs.toString();

		// read the value
		try {
			result = rs.getObject(columnName);
		} catch (SQLException e) {
			// exception when reading values means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "getResultSetColumnError", new Object[] {columnName, rsName, e.getMessage()});
			result = null;
		}

		return result;
	}

	/**
	 * get value from named column in given result set as string
	 * @param rs the result set to read
	 * @param columnName name of the column to read
	 */
	public String getResultSetString (ResultSet rs, String columnName) {

		String result = null;

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return result;

		// do nothing if this is not a valid result set
		if (rs == null)
			return result;

		// get a string representation of this resultset
		String rsName = rs.toString();

		// read the value
		try {
			result = rs.getString(columnName);
		} catch (SQLException e) {
			// exception when reading values means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "getResultSetColumnError", new Object[] {columnName, rsName, e.getMessage()});
			result = null;
		}

		return result;
	}

	/**
	 * reports whether the last column read had a value of NULL
	 * @param rs the result set to check
	 */
	public boolean getResultSetWasNull (ResultSet rs) {

		boolean result = false;

		// do nothing if a previous error exists
		if (s_dbEngine.isDBError())
			return result;

		// do nothing if this is not a valid result set
		if (rs == null)
			return result;

		// get a string representation of this resultset
		String rsName = rs.toString();

		// read the value
		try {
			result = rs.wasNull();
		} catch (SQLException e) {
			// exception when reading values means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "getResultSetWasNullError", new Object[] {rsName, e.getMessage()});
			result = false;
		}

		return result;
	}

	/**
	 * run an SQL update command from a prepared statement
	 * @param stmt prepared statement containg the SQL command to run
	 * @param useInlineSavepoint run the command in an isolated child transaction
	 * @return the number of records updated/inserted/deleted or NULL if invalid
	 */
	public Integer executeUpdate(PreparedStatement stmt, boolean useInlineSavepoint) {

		// do nothing if this is not target
		// (changes not allowed in source)
		if (isSource())
			return null;

		// do nothing if a previous database error exists
		if (s_dbEngine.isDBError())
			return null;

		// do nothing if prepared statement is invalid
		if (stmt == null)
			return null;

		// migration must not be interrupted anymore
		setDoNotInterrupt(true);

		Integer result = null;

		// get a String representation of the statement
		String sqlCommand = stmt.toString();

		// remember inline savepoint
		Savepoint spInline = null;
		if (useInlineSavepoint)
			spInline = setSavepoint("pstmtInline");

		// run SQL command
		try {
			s_logger.log(Level.FINER, sqlCommand);

			int recordsUpdated = stmt.executeUpdate();
			result = new Integer(recordsUpdated);

		} catch (SQLException e) {
			// exception when running SQL command means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "executeUpdatePreparedStatementError", new Object[] {sqlCommand, e.getMessage()});
			result = null;
		}

		// release inline savepoint
		releaseSavepoint(spInline);

		return result;
	}


	/**
	 * run an SQL update command
	 * @param stmt statement in which the SQL command should be run
	 * @param sqlCommand the SQL command to run
	 * @param useInlineSavepoint run the command in an isolated child transaction
	 * @param isAllowInterruption whether the migration process may still be interrupted after this update
	 * @return the number of records updated/inserted/deleted or NULL if invalid
	 */
	public Integer executeUpdate(Statement stmt, String sqlCommand, boolean useInlineSavepoint, boolean isAllowInterruption) {

		// do nothing if this is not target
		// (changes not allowed in source)
		if (isSource() && ! isAllowInterruption)
			return null;

		// do nothing if a previous database error exists
		if (s_dbEngine.isDBError())
			return null;

		// do nothing if statement is invalid
		if (stmt == null)
			return null;

		// do nothing if SQL command is invalid
		if (sqlCommand==null || sqlCommand.length()==0)
			return null;

		// migration must not be interrupted anymore
		if (! isAllowInterruption)
			setDoNotInterrupt(true);

		Integer result = null;

		// remember inline savepoint
		Savepoint spInline = null;
		if (useInlineSavepoint)
			spInline = setSavepoint("sqlInline");

		// run SQL command
		try {
			s_logger.log(Level.FINER, sqlCommand);

			int recordsUpdated = stmt.executeUpdate(sqlCommand);
			result = new Integer(recordsUpdated);

		} catch (SQLException e) {
			// exception when running SQL command means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2].getMethodName(), "executeUpdateSqlError", new Object[] {sqlCommand, e.getMessage()});
			result = null;
		}

		// release inline savepoint
		releaseSavepoint(spInline);

		return result;
	}

	/**
	 * run an SQL update command silently (without logging errors)
	 * <p>
	 * This can be used to try out whether an error would occur without annoying
	 * the user with error messages.
	 * <p>
	 * If the return value is <code>null</code>, it means an error occured
	 * @param stmt statement in which the SQL command should be run
	 * @param sqlCommand the SQL command to run
	 * @param useInlineSavepoint run the command in an isolated child transaction
	 * @return the number of records updated/inserted/deleted or NULL if invalid
	 */
	public Integer executeUpdateSilent (Statement stmt, String sqlCommand, boolean useInlineSavepoint) {

		// do nothing if this is not target
		// (changes not allowed in source)
		if (isSource())
			return null;

		// do nothing if a previous database error exists
		if (s_dbEngine.isDBError())
			return null;

		// do nothing if statement is invalid
		if (stmt == null)
			return null;

		// do nothing if SQL command is invalid
		if (sqlCommand==null || sqlCommand.length()==0)
			return null;

		// migration must not be interrupted anymore
		setDoNotInterrupt(true);

		Integer result = null;
		m_lastSilentError = null;

		// remember inline savepoint
		Savepoint spInline = null;
		if (useInlineSavepoint)
			spInline = setSavepoint("silentInline");

		// run SQL command
		try {
			s_logger.log(Level.FINER, sqlCommand);

			int recordsUpdated = stmt.executeUpdate(sqlCommand);
			result = new Integer(recordsUpdated);

		} catch (SQLException e) {
			// exception when running SQL command means database may be OK but transaction is invalid
			s_dbEngine.setTransactionErrors(1);
			result = null;
			m_lastSilentError = e.toString();
		}

		// release inline savepoint
		releaseSavepoint(spInline);

		return result;
	}

	/**
	 * @return the sequenceSystem
	 */
	public HashMap<String, Long> getSequenceSystem() {
		return m_sequenceSystem;
	}

	/**
	 * @return the sequenceUser
	 */
	public HashMap<String, Long> getSequenceUser() {
		return m_sequenceUser;
	}

	/**
	 * @return the lastSilentError
	 */
	public String getLastSilentError() {
		return m_lastSilentError;
	}


	/**
	 * Set to TRUE if the database structure has changed and the
	 * migration process must not be interrupted anymore.<br>
	 * Set to FALSE if it is safe to interrupt the migration process.<br>
	 * (Only used for target connection).
	 * @param isDoNotInterrupt migration must not be interrupted anymore
	 */
	public void setDoNotInterrupt(boolean isDoNotInterrupt) {

		if (isTarget()) {
			if (isDoNotInterrupt) {
				// migration must not be interrupted
				if (! m_isDoNotInterrupt) {
					m_isDoNotInterrupt = true;
					s_logger.log(Level.WARNING, "migrateDoNotInterrupt");
				}
			} else {
				// migration may be interrupted
				m_isDoNotInterrupt = false;
			}
		}
	}

	/**
	 * release all metadata and initialize parameters
	 */
	public void reset () {

		// reset connection
		m_connection = null;
		m_isTarget = isTarget();
		m_isTempDisconnected = false;
		m_driver = null;
		m_url = null;
		m_charDevisor = 1;
		m_isSavepointReleaseable = true;
		m_lastSilentError = null;
		m_isDoNotInterrupt = false;

		// reset objects
		m_tables = null;
		m_views = null;
		m_operators = null;
		m_functions = null;
		m_triggers = null;
		m_sequences = null;
		m_primaryKeys = null;
		m_foreignKeys = null;
		m_checks = null;
		m_uniques = null;
		m_indexes = null;

		// reset application dictionary
		m_customPrefixes = null;
		m_customEntities = null;
		m_systemClients = null;
		m_systemLanguages = null;
		m_sequenceSystem = null;
		m_sequenceUser = null;

		// initialize parameters
		setVendor(getVendor());
		setHost(getHost());
		setPort(getPort());
		setName(getName());
		setCatalog(getCatalog());
		setSchema(getSchema());
		setUser(getUser());
		setPasswd(getPasswd());
		setSystemUser(getSystemUser());
		setSystemPasswd(getSystemPasswd());
	}

}
