/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.logging.Level;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.RowSet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.Adempiere;
import org.compiere.dbPort.Convert;
import org.compiere.dbPort.Convert_MariaDB;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Ini;

/**
 * Connection class for MariaDB Database
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 391 ] Add connection support to MariaDB
 *		@see https://github.com/adempiere/adempiere/issues/464
 */
public class DB_MariaDB implements AdempiereDatabase {

	public Convert getConvert() {
		return m_convert;
	}

	/**
	 * MariaDB Database
	 */
	public DB_MariaDB() {
		super();
	}

	/** Driver */
	private org.mariadb.jdbc.Driver s_driver = null;

	/** Driver class */
	public static final String DRIVER = "org.mariadb.jdbc.Driver";

	/** Default Port */
	public static final int DEFAULT_PORT = 3306;

	/** Data Source */
	private HikariDataSource m_ds = null;

	/** Statement Converter */
	private Convert_MariaDB m_convert = new Convert_MariaDB();
	
	/** Connection String */
	private String m_connection;
	
	/** Cached Database Name */
	private String m_dbName = null;

	private String m_userName = null;
	
	/** Connection String */
	private String m_connectionURL;
	
	/** Logger */
	private static CLogger log = CLogger.getCLogger(DB_MariaDB.class);

	private static int m_maxbusyconnections = 0;

	/**
	 * Get Database Name
	 * 
	 * @return database short name
	 */
	public String getName() {
		return Database.DB_MARIADB;
	}

	/**
	 * Get Database Description
	 * 
	 * @return database long name and version
	 */
	public String getDescription() {
		try {
			if (s_driver == null)
				getDriver();
		} catch (Exception e) {
		}
		if (s_driver != null)
			return s_driver.toString();
		return "No Driver for MariaDB";
	}

	/**
	 * Get Standard JDBC Port
	 * 
	 * @return standard port
	 */
	public int getStandardPort() {
		return DEFAULT_PORT;
	}

	/**
	 * Get and register Database Driver
	 * 
	 * @return Driver
	 */
	public java.sql.Driver getDriver() throws SQLException {
		if (s_driver == null) {
			s_driver = new org.mariadb.jdbc.Driver();
			DriverManager.registerDriver(s_driver);
			DriverManager.setLoginTimeout(Database.CONNECTION_TIMEOUT);
		}
		return s_driver;
	}

	/**
	 * Get Database Connection String. Requirements: - createdb -E UNICODE
	 * compiere
	 * 
	 * @param connection
	 *            Connection Descriptor
	 * @return connection String
	 */
	public String getConnectionURL(CConnection connection) {
		// jdbc:mariadb://localhost:3306/DB?user=root&password=myPassword
		StringBuffer sb = new StringBuffer("jdbc:mariadb:");
		sb.append("//").append(connection.getDbHost()).append(":").append(
				connection.getDbPort()).append("/").append(
				connection.getDbName()).append("?encoding=UNICODE");
		m_connection = sb.toString();
		return m_connection;
	}

	/**
	 * Get Connection URL
	 * It can be jdbc:mariadb://localhost:3306/DB?user=root&password=myPassword
	 * copied from https://mariadb.com/kb/en/mariadb/about-mariadb-connector-j/
	 * @param dbHost db Host
	 * @param dbPort db Port
	 * @param dbName sb Name
	 * @param userName user name
	 * @return connection url
	 */
	public String getConnectionURL(String dbHost, int dbPort, String dbName,
			String userName) {
		return "jdbc:mariadb://" + dbHost + ":" + dbPort + "/" + dbName;
	}

	/**
	 * Get Database Connection String
	 * 
	 * @param connectionURL Connection URL
	 * @param userName user name
	 * @return connection String
	 */
	public String getConnectionURL(String connectionURL, String userName) {
		m_userName = userName;
		m_connectionURL = connectionURL;
		return m_connectionURL;
	}

	/**
	 * Get JDBC Catalog
	 * @return catalog (database name)
	 */
	public String getCatalog() {
		if (m_dbName != null)
			return m_dbName;
		 log.severe("Database Name not set (yet) - call getConnectionURL first");
		return null;
	}

	/**
	 * Get JDBC Schema
	 * @return schema (dbo)
	 */
	public String getSchema() {
		//	BR [ 391 ]
		if (m_userName == null) {
	        CConnection cconn = CConnection.get(Adempiere.getCodeBaseHost());
	        m_userName = cconn.getDbUid();
	    }
    	//	Validate
        if (m_userName == null) {
        	log.severe("User Name not set (yet) - call getConnectionURL first");
        	return null;
        }
	    return m_userName;
	}

	/**
	 * Supports BLOB
	 * @return true if BLOB is supported
	 */
	public boolean supportsBLOB() {
		return true;
	}

	/**
	 * String Representation
	 * @return info
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("DB_MariaDB[");
		sb.append(m_connectionURL);
		try {
			StringBuffer logBuffer = new StringBuffer(50);
			/*logBuffer.append("# Connections: ").append(m_ds.getNumConnections());
			logBuffer.append(" , # Busy Connections: ").append(m_ds.getNumBusyConnections());
			logBuffer.append(" , # Idle Connections: ").append(m_ds.getNumIdleConnections());
			logBuffer.append(" , # Orphaned Connections: ").append(m_ds.getNumUnclosedOrphanedConnections());*/
		} catch (Exception e) {
			sb.append("=").append(e.getLocalizedMessage());
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Get Status
	 * 
	 * @return status info
	 */
	public String getStatus() {
		if (m_ds == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		try {
			/*sb.append("# Connections: ").append(m_ds.getNumConnections());
			sb.append(" , # Busy Connections: ").append(m_ds.getNumBusyConnections());
			sb.append(" , # Idle Connections: ").append(m_ds.getNumIdleConnections());
			sb.append(" , # Orphaned Connections: ").append(m_ds.getNumUnclosedOrphanedConnections());
			*/
		} catch (Exception e) {
		}
		return sb.toString();
	}

	/*************************************************************************
	 * Convert an individual Oracle Style statements to target database
	 * statement syntax
	 * 
	 * @param oraStatement
	 * @return converted Statement
	 * @throws Exception
	 */
	public String convertStatement(String oraStatement) {
		String retValue[] = m_convert.convert(oraStatement);
		//	Valid null
		if (retValue == null) {
			log.log(Level.SEVERE,
					("DB_MariaDB.convertStatement - Not Converted ("
							+ oraStatement + ") - " + m_convert.getConversionError()));
			throw new IllegalArgumentException(
					"DB_MariaDB.convertStatement - Not Converted ("
							+ oraStatement + ") - "
							+ m_convert.getConversionError());
		}
		if (retValue.length == 0)
			return oraStatement;
		//	
		if (retValue.length != 1)
		{
			log.log(Level.SEVERE,
						("DB_MariaDB.convertStatement - Convert Command Number="
							+ retValue.length
							+ " ("
							+ oraStatement
							+ ") - " + m_convert.getConversionError()));
			throw new IllegalArgumentException(
					"DB_MariaDB.convertStatement - Convert Command Number="
							+ retValue.length + " (" + oraStatement + ") - "
							+ m_convert.getConversionError());
		}
		// Diagnostics (show changed, but not if AD_Error
		if (!oraStatement.equals(retValue[0]) && retValue[0].indexOf("AD_Error") == -1)
			// System.out.println("PostgreSQL =>" + retValue[0] + "<= <" + oraStatement + ">");
			log.log(Level.ALL, "MariaDB =>" + retValue[0] + "<= <" + oraStatement	+ ">");
		//
		Convert.logMigrationScript(oraStatement, null, retValue[0]);
		return retValue[0];
	}

	/**
	 * Get Name of System User
	 * @return e.g. sa, system
	 */
	public String getSystemUser() {
		return "root";
	}

	/**
	 * Get Name of System Database
	 * @param databaseName database Name
	 * @return e.g. master or database Name
	 */
	public String getSystemDatabase(String databaseName) {
		return "adempiere";
	}

	/**
	 * Create SQL TO Date String from Timestamp
	 * 
	 * @param time Date to be converted
	 * @param dayOnly true if time set to 00:00:00
	 * 
	 * @return STR_TO_DATE('2001-01-30 18:10:20', '%Y-%m-%d %H:%i:%s') or
	 *         STR_TO_DATE('2001-01-30', '%Y-%m-%d')
	 */
	public String TO_DATE(Timestamp time, boolean dayOnly) {
		if (time == null) {
			if (dayOnly)
				return "current_date()";
			return "now()";
		}
/* MaruaDB has no to_date function.
 * Trifon: created 'to_date' function in order to be able to create proper .sql migration files.
 *
 * select STR_TO_DATE('01,5,2013','%d,%m,%Y'); --> '2013-05-01'
 * select STR_TO_DATE('2010-07-30 19:38:51','%Y-%m-%d %H:%i:%s');  --> '2010-07-30 19:38:51'
 */
		StringBuffer dateString = new StringBuffer("TO_DATE('"); // "STR_TO_DATE" -> MariaDB function. But if we use MariaDB function then Oracle and PostgreSQL migration files get wrong.
		// YYYY-MM-DD HH24:MI:SS.mmmm JDBC Timestamp format
		String myDate = time.toString();
		if (dayOnly) {
			dateString.append(myDate.substring(0, 10));
			//dateString.append("','%Y-%m-%d')"); // MariaDB
			dateString.append("','YYYY-MM-DD')"); // Oracle & PostgreSQL strings: 'YYYY-MM-DD'
		} else {
			dateString.append(myDate.substring(0, myDate.indexOf('.'))); // cut off milliseconds
			//dateString.append("','%Y-%m-%d %H:%i:%s')");   // MariaDB
			dateString.append("','YYYY-MM-DD HH24:MI:SS')"); //Oracle & PostgreSQL strings:  'YYYY-MM-DD HH24:MI:SS'
		}
		return dateString.toString();
	}

	/**
	 * Create SQL for formatted Date, Number
	 * 
	 * @param columnName the column name in the SQL
	 * @param displayType Display Type
	 * @param AD_Language 6 character language setting (from Env.LANG_*)
	 * 
	 * @return TRIM(TO_CHAR(columnName,
	 *         '999G999G999G990D00','NLS_NUMERIC_CHARACTERS='',.''')) or
	 *         TRIM(TO_CHAR(columnName,'TM9')) depending on DisplayType and
	 *         Language
	 * @see org.compiere.util.DisplayType
	 * @see org.compiere.util.Env
	 * 
	 **/
	public String TO_CHAR(String columnName, int displayType, String AD_Language) {
		StringBuffer retValue = new StringBuffer("CAST (");
		retValue.append(columnName);
		retValue.append(" AS Char)");
		//	
		return retValue.toString();
	}

	/**
	 * Return number as string for INSERT statements with correct precision
	 * 
	 * @param number number
	 * @param displayType display Type
	 * @return number as string
	 */
	public String TO_NUMBER(BigDecimal number, int displayType) {
		if (number == null)
			return "NULL";
		BigDecimal result = number;
		int scale = DisplayType.getDefaultPrecision(displayType);
		if (scale > number.scale()) {
			try {
				result = number.setScale(scale, BigDecimal.ROUND_HALF_UP);
			} catch (Exception e) {
				// log.severe("Number=" + number + ", Scale=" + " - " +
				// e.getMessage());
			}
		}
		return result.toString();
	}

	/**
	 * Get SQL Commands
	 * @param cmdType CMD_*
	 * @return array of commands to be executed
	 */
	public String[] getCommands(int cmdType) {
		if (CMD_CREATE_USER == cmdType)
			return new String[] { "CREATE USER adempiere;", };
		//
		if (CMD_CREATE_DATABASE == cmdType)
			return new String[] { "CREATE DATABASE adempiere OWNER adempiere;",
					"GRANT ALL PRIVILEGES ON adempiere TO adempiere;",
					"CREATE SCHEMA adempiere;", "SET search_path TO adempiere;" };
		//
		if (CMD_DROP_DATABASE == cmdType)
			return new String[] { "DROP DATABASE adempiere;" };
		//
		return null;
	}

	/**************************************************************************
	 * Get RowSet
	 * @param rs ResultSet
	 * @return RowSet
	 * @throws SQLException
	 */
	public RowSet getRowSet(java.sql.ResultSet rs) throws SQLException {
		throw new UnsupportedOperationException("MariaDB does not support RowSets");
	}

	/**
	 * Get Cached Connection
	 * 
	 * @param connection connection
	 * @param autoCommit auto commit
	 * @param transactionIsolation trx isolation
	 * @return Connection
	 * @throws Exception
	 */
	public Connection getCachedConnection(CConnection connection,
			boolean autoCommit, int transactionIsolation) throws Exception {
		if (m_ds == null)
			getDataSource(connection);
		//
		Connection conn = m_ds.getConnection();
		if (conn != null) {
			//
			conn.setAutoCommit(autoCommit);
			conn.setTransactionIsolation(transactionIsolation);
		}
		return conn;
	}

	/**
	 * Create DataSource (Client)
	 *
	 * @param connection connection
	 * @return data source
	 */
	public DataSource getDataSource(CConnection connection) {
		if (m_ds != null)
			return m_ds;

		try
		{
			if (Ini.isClient()) {
				log.warning("Config Hikari Connection Pool Datasource");
				HikariConfig config = new HikariConfig();
				config.setDriverClassName(DRIVER);
				config.setJdbcUrl(getConnectionURL(connection));
				config.setUsername(connection.getDbUid());
				config.setPassword(connection.getDbPwd());
				config.addDataSourceProperty( "poolName" , "AdempiereDS" );
				config.addDataSourceProperty( "cachePrepStmts" , "true" );
				config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
				config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
				config.addDataSourceProperty("connectionTestQuery", DEFAULT_CONN_TEST_SQL);
				config.addDataSourceProperty( "connectionInitSql" , "1" );
				config.addDataSourceProperty( "idleTimeout" , "1200" );
				config.addDataSourceProperty("maximumPoolSize", "15");
				HikariDataSource cpds = new HikariDataSource(config);
				m_ds = cpds;
				log.warning("Starting Client Hikari Connection Pool");
			} else {
				Optional<String> maybeApplicationType = Optional.ofNullable(System.getenv("ADEMPIERE_APPS_TYPE"));
				m_ds = maybeApplicationType
						.map(applicationType -> {
							if ("wildfly".equals(applicationType)) {
								try {
									Context initCtx = new InitialContext();
									DataSource dataSource = (DataSource) initCtx.lookup("java:/AdempiereDS");
									log.warning("Connection Lookup JNDI Datasource for java:/AdempiereDS Hikari Connection Pool");
									HikariConfig config = new HikariConfig();
									config.setDataSource(dataSource);
									return new HikariDataSource(config);
								} catch (Exception namingException) {
									m_ds = null;
									log.log(Level.SEVERE, "Could not initialise Hikari Connection Pool", namingException);
									namingException.printStackTrace();
								}
							}
							try {
								DataSource dataSource = InitialContext.doLookup("java:comp/env/java/AdempiereDS");
								log.warning("Connection Lookup JNDI Datasource for java:comp/env/java/AdempiereDS Hikari Connection Pool");
								HikariConfig config = new HikariConfig();
								config.setDataSource(dataSource);
								return new HikariDataSource(config);
							} catch (Exception namingException) {
								m_ds = null;
								log.log(Level.SEVERE, "Application Server does not exist Could not initialise Hikari Connection Pool", namingException);
								namingException.printStackTrace();
							}
							log.warning("Connection successful using Standalone Hikari Config Connection Pool");
							HikariConfig config = new HikariConfig();
							config.setDriverClassName(DRIVER);
							config.setJdbcUrl(getConnectionURL(connection));
							config.setUsername(connection.getDbUid());
							config.setPassword(connection.getDbPwd());
							config.addDataSourceProperty("poolName", "AdempiereDS");
							config.addDataSourceProperty("cachePrepStmts", "true");
							config.addDataSourceProperty("prepStmtCacheSize", "250");
							config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
							config.addDataSourceProperty("connectionTestQuery", DEFAULT_CONN_TEST_SQL);
							config.addDataSourceProperty("connectionInitSql", "1");
							config.addDataSourceProperty("idleTimeout", "1200");
							config.addDataSourceProperty("maximumPoolSize", "150");
							return new HikariDataSource(config);
						}).orElseThrow(() -> new AdempiereException("The ADEMPIERE_APPS_TYPE environment variable is not set, so it is not possible to initialize the Hikari Connection Pool"));
			}
		} catch (Exception exception) {
			m_ds = null;
			log.log(Level.SEVERE, "Application Server does not exist, no is possible to initialize the initialise Hikari Connection Pool", exception);
			exception.printStackTrace();
		}
		return m_ds;
	}

	/**
	 * Create Pooled DataSource (Server)
	 * @param connection connection
	 * @return data source
	 */
	public ConnectionPoolDataSource createPoolDataSource(CConnection connection) {
		throw new UnsupportedOperationException("Not supported/implemented");
	}

	/**
	 * Get Connection from Driver
	 * @param connection info
	 * @return connection or null
	 */
	public Connection getDriverConnection(CConnection connection)
			throws SQLException {
		getDriver();
		return DriverManager.getConnection(getConnectionURL(connection),
				connection.getDbUid(), connection.getDbPwd());
	}

	/**
	 * Get Driver Connection
	 * 
	 * @param dbUrl URL
	 * @param dbUid user
	 * @param dbPwd password
	 * @return connection
	 * @throws SQLException
	 */
	public Connection getDriverConnection(String dbUrl, String dbUid,
			String dbPwd) throws SQLException {
		getDriver();
		return DriverManager.getConnection(dbUrl, dbUid, dbPwd);
	}

	/**
	 * Close
	 */
	public void close() {

		log.config(toString());

		if (m_ds != null) {
			try {
				m_ds.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		m_ds = null;
	}

	/**
	 * Check and generate an alternative SQL
	 * 
	 * @reExNo number of re-execution
	 * @msg previous execution error message
	 * @sql previous executed SQL
	 * @return String, the alternative SQL, null if no alternative
	 */
	public String getAlternativeSQL(int reExNo, String msg, String sql) {
		return null; // do not do re-execution of alternative SQL
	}

	/**
	 * Get constraint type associated with the index
	 * 
	 * @tableName table name
	 * @IXName Index name
	 * @return String[0] = 0: do not know, 1: Primary Key 2: Foreign Key
	 *         String[1] - String[n] = Constraint Name
	 */
	public String getConstraintType(Connection conn, String tableName,
			String IXName) {
		if (IXName == null || IXName.length() == 0)
			return "0";
		if (IXName.toUpperCase().endsWith("_KEY"))
			return "1" + IXName;
		else
			return "0";
		// jz temp, modify later from user.constraints
	}

	/**
	 * Check if DBMS support the sql statement
	 * 
	 * @sql SQL statement
	 * @return true: yes
	 */
	public boolean isSupported(String sql) {
		return true;
		// jz temp, modify later
	}

	/**
	 * Dump table lock info to console for current transaction
	 * 
	 * @param conn
	 */
	public static void dumpLocks(Connection conn) {
		Statement stmt = null;
		try {
			String sql = "select pg_class.relname,pg_locks.* from pg_class,pg_locks where pg_class.relfilenode=pg_locks.relation order by 1";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int cnt = rs.getMetaData().getColumnCount();
			System.out.println();
			while (rs.next()) {
				for (int i = 0; i < cnt; i++) {
					Object value = rs.getObject(i + 1);
					if (i > 0)
						System.out.print(", ");
					System.out.print(value != null ? value.toString() : "");
				}
				System.out.println();
			}
			System.out.println();
		} catch (Exception e) {

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Test
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {
		DB_MariaDB mariaDB = new DB_MariaDB();
		//
		String databaseName = "adempiere";
		String uid = "adempiere";
		String pwd = "adempiere";
		String jdbcURL = mariaDB.getConnectionURL("localhost", DEFAULT_PORT, databaseName, uid);
		System.out.println(jdbcURL);
		try {
			mariaDB.getDriver();
			System.out.println(mariaDB.getDriver());
			Connection conn = DriverManager.getConnection(jdbcURL, uid, pwd);
			//
			conn.close();
			conn = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public int getNextID(String name) {
		int m_sequence_id = DB.getSQLValue(null, "SELECT nextval('" + name.toLowerCase() + "')");
		return m_sequence_id;
	}

	public boolean createSequence(String name, int increment, int minvalue,
			int maxvalue, int start, String trxName) {

		int no = DB.executeUpdate("CREATE SEQUENCE " + name.toUpperCase()
				+ " INCREMENT " + increment + " MINVALUE " + minvalue
				+ " MAXVALUE " + maxvalue + " START " + start, trxName);
		if (no == -1)
			return false;
		else
			return true;
	}

	@Override
	public String addPagingSQL(String sql, int start, int end) {
		return null;
	}

	@Override
	public boolean isPagingSupported() {
		return false;
	}

	@Override
	public boolean isQueryTimeoutSupported() {
		return false;
	}

	@Override
	public boolean forUpdate(PO po, int timeout) {
		//only can lock for update if using trx
		if (po.get_TrxName() == null)
			return false;

		String[] keyColumns = po.get_KeyColumns();
		if (keyColumns != null && keyColumns.length > 0 && !po.is_new()) {
			StringBuilder sqlBuffer = new StringBuilder(" SELECT ");
			sqlBuffer.append(keyColumns[0])
					.append(" FROM ")
					.append(po.get_TableName())
					.append(" WHERE ");
			for(int i = 0; i < keyColumns.length; i++) {
				if (i > 0)
					sqlBuffer.append(" AND ");
				sqlBuffer.append(keyColumns[i]).append("=?");
			}
			sqlBuffer.append(" FOR UPDATE ");

			Object[] parameters = new Object[keyColumns.length];
			for(int i = 0; i < keyColumns.length; i++) {
				Object parameter = po.get_Value(keyColumns[i]);
				if (parameter != null && parameter instanceof Boolean) {
					if ((Boolean) parameter)
						parameter = "Y";
					else
						parameter = "N";
				}
				parameters[i] = parameter;
			}

			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = DB.prepareStatement(sqlBuffer.toString(),
						ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE, po.get_TrxName());
				for(int i = 0; i < keyColumns.length; i++) {
					stmt.setObject(i+1, parameters[i]);
				}
				stmt.setQueryTimeout(timeout > 0 ? timeout : LOCK_TIME_OUT);

				rs = stmt.executeQuery();
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				if (log.isLoggable(Level.INFO))log.log(Level.INFO, e.getLocalizedMessage(), e);
				throw new DBException("Could not lock record for " + po.toString() + " caused by " + e.getLocalizedMessage());
			} finally {
				DB.close(rs, stmt);
				rs = null;stmt = null;
			}
		}
		return false;
	}

	@Override
	public String getNameOfUniqueConstraintError(Exception e) {
		String info = e.getMessage();
		int fromIndex = info.indexOf("\"");
		if (fromIndex == -1)
			fromIndex = info.indexOf("\u00ab"); // quote for spanish postgresql message
		if (fromIndex == -1)
			return info;
		int toIndex = info.indexOf("\"", fromIndex + 1);
		if (toIndex == -1)
			toIndex = info.indexOf("\u00bb", fromIndex + 1);
		if (toIndex == -1)
			return info;
		return info.substring(fromIndex + 1, toIndex);
	}
}
