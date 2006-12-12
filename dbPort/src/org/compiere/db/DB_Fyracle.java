/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is                  Compiere  ERP & CRM  Business Solution
 * The Initial Developer of the Original Code is Jorg Janke  and ComPiere, Inc.
 * Portions created by Jorg Janke are Copyright (C) 1999-2001 Jorg Janke, parts
 * created by ComPiere are Copyright (C) ComPiere, Inc.;   All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.db;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import javax.sql.DataSource;

import org.compiere.dbPort.Convert;
import org.compiere.dbPort.Convert_Oracle;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Language;
import org.firebirdsql.jdbc.FBConnection;
import org.firebirdsql.pool.DriverConnectionPoolDataSource;
import org.firebirdsql.pool.FBWrappingDataSource;

/**
 * Firebird Database Port
 * 
 * @author Paul Ruizendaal
 * @author Marek Mosiewicz http://www.jotel.com.pl (port to Compiere 2.5.1g)
 * @version $Id: DB_Firebird.java,v 1.13 2003/08/16 19:09:21 pnr Exp $
 */
public class DB_Fyracle implements AdempiereDatabase {
	/** Statement Cache */
	private static final int MAX_STATEMENTS = 20;

	/**
	 * Firebird Database
	 */
	public DB_Fyracle() {
	} // DB_Firebird

	/** Driver */
	private org.firebirdsql.jdbc.FBDriver s_driver = null;

	/** Default Port */
	public static final int DEFAULT_PORT = 3050;

	/** Connection String */
	private String m_connection;

	private FBWrappingDataSource m_ds;

	private static CLogger log = CLogger.getCLogger(DB_Fyracle.class);

	private Convert m_convert = new Convert_Oracle();

	private String m_userName;

	private String m_connectionURL;

	/**
	 * Get Database Name
	 * 
	 * @return database short name
	 */
	public String getName() {
		return Database.DB_FYRACLE;
	} // getName

	/**
	 * Get Database Description
	 * 
	 * @return database long name and version
	 */
	public String getDescription() {
		return s_driver.toString();
	} // getDescription

	/**
	 * Get Standard JDBC Port
	 * 
	 * @return standard port
	 */
	public int getStandardPort() {
		return DEFAULT_PORT;
	} // getStandardPort

	/**
	 * Get Database Driver
	 * 
	 * @return Driver
	 */
	public java.sql.Driver getDriver() {
		if (s_driver == null)
			s_driver = new org.firebirdsql.jdbc.FBDriver();
		return s_driver;
	} // getDriver

	/**
	 * Get Database Connection String. Requirements: - createdb -E UNICODE
	 * compiere
	 * 
	 * @param connection
	 *            Connection Descriptor
	 * @return connection String
	 */
	public String getConnectionURL(CConnection connection) {
		StringBuffer sb = new StringBuffer("jdbc:firebirdsql:oracle:");
		sb.append(connection.getDbHost()).append("/").append(
				connection.getDbPort()).append(":").append(
				connection.getDbName());
		m_connection = sb.toString();
		m_userName = connection.getDbUid();
		return m_connection;
	} // getConnectionString

	/**
	 * Get Connection URL.
	 * http://download-east.oracle.com/docs/cd/B14117_01/java.101/b10979/urls.htm#BEIDBFDF
	 * 
	 * @param dbHost
	 *            db Host
	 * @param dbPort
	 *            db Port
	 * @param dbName
	 *            db Name
	 * @param userName
	 *            user name
	 * @return connection
	 */
	public String getConnectionURL(String dbHost, int dbPort, String dbName,
			String userName) {
		StringBuffer sb = new StringBuffer("jdbc:firebirdsql:oracle:");
		sb.append(dbHost).append("/").append(dbPort).append(":").append(dbName);
		m_connection = sb.toString();
		m_userName = userName;
		return m_connection;
	} // getConnectionURL

	/**
	 * Supports BLOB
	 * 
	 * @return true if BLOB is supported
	 */
	public boolean supportsBLOB() {
		return true;
	} // supportsBLOB

	/**
	 * String Representation
	 * 
	 * @return info
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("DB_Firebird[");
		sb.append(m_connection).append("]");
		return sb.toString();
	} // toString

	/** ********************************************************************** */

	/**
	 * Convert an individual Oracle Style statements to target database
	 * statement syntax
	 * 
	 * @param oraStatement
	 * @return converted Statement
	 * @throws Exception
	 */
	public String convertStatement(String oraStatement) {
		return oraStatement;
	} // convertStatement

	/** ********************************************************************** */

	/**
	 * Set the RowID
	 * 
	 * @param pstmt
	 * @param pos
	 * @param rowID
	 * @throws SQLException
	 */
	public void setRowID(PreparedStatement pstmt, int pos, Object rowID)
			throws SQLException {
		pstmt.setString(pos, (String) rowID);
	} // setRowID

	/**
	 * Get the RowID
	 * 
	 * @param rs
	 * @param pos
	 * @return rowID
	 * @throws SQLException
	 */
	public Object getRowID(java.sql.ResultSet rs, int pos) throws SQLException {
		return rs.getString(pos);
	} // getRowID

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.db.CompiereDatabase#getCachedConnection(org.compiere.db.CConnection,
	 *      boolean, int)
	 */
	public Connection getCachedConnection(CConnection connection,
			boolean autoCommit, int transactionIsolation) throws Exception {
		if (m_ds == null)
			getDataSource(connection);
		Connection conn = null;
		// @TODO FYRACLE PooledConnection ignore setAutoCommit
		// conn = m_ds.getConnection();
		conn = getDriverConnection(connection);
		if (conn != null) {
			if (conn.getTransactionIsolation() != transactionIsolation)
				conn.setTransactionIsolation(transactionIsolation);
			if (conn.getAutoCommit() != autoCommit)
				conn.setAutoCommit(autoCommit);
		} else {
			throw new RuntimeException("Cann't connect to database");
		}
		return conn;
	}

	/**
	 * Get JDBC Schema
	 * 
	 * @return user name
	 */
	public String getSchema() {
		if (m_userName != null)
			return m_userName.toUpperCase();
		log.severe("User Name not set (yet) - call getConnectionURL first");
		return null;
	} // getSchema

	/**
	 * Get Driver Connection
	 * 
	 * @param dbUrl
	 *            URL
	 * @param dbUid
	 *            user
	 * @param dbPwd
	 *            password
	 * @return connection
	 * @throws SQLException
	 */
	public Connection getDriverConnection(String dbUrl, String dbUid,
			String dbPwd) throws SQLException {
		getDriver();
		return DriverManager.getConnection(dbUrl, dbUid, dbPwd);
	} // getDriverConnection

	/**
	 * Get Connection from Driver
	 * 
	 * @param connection
	 *            info
	 * @return connection or null
	 */
	public Connection getDriverConnection(CConnection connection)
			throws SQLException {
		getDriver();
		return DriverManager.getConnection(getConnectionURL(connection),
				connection.getDbUid(), connection.getDbPwd());
	} // getDriverConnection

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.db.CompiereDatabase#getDataSource(org.compiere.db.CConnection)
	 */
	public DataSource getDataSource(CConnection connection) {
		if (m_ds != null)
			return m_ds;
		try {
			m_ds = new FBWrappingDataSource();
			StringBuffer db = new StringBuffer().append(connection.getDbHost())
					.append("/").append(connection.getDbPort()).append(":")
					.append(connection.getDbName());

			m_ds.setDatabase(db.toString());
			m_ds.setUserName(connection.getDbUid());
			m_ds.setPassword(connection.getDbPwd());
			m_ds.setDescription("Compiere DS");

			m_ds.setType("ORACLE_MODE");

			m_ds.setLoginTimeout(10);
			m_ds.setMaxStatements(MAX_STATEMENTS);
			m_ds.setLoginTimeout(10);
			m_ds.setMaxStatements(MAX_STATEMENTS);

			return m_ds;

		} catch (Exception e) {
			log.log(Level.SEVERE, toString(), e);
			// throw new RuntimeException(e);
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.db.CompiereDatabase#getStatus()
	 */
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.db.CompiereDatabase#close()
	 */
	public void close() {
		m_ds = null;
	}

	public static void main(String[] a) {
		try {
			FBWrappingDataSource m_ds = new FBWrappingDataSource();
			m_ds
					.setDatabase("localhost/3050:c:/devspace/apps/fyracle/data/compiere.fdb");

			m_ds.setUserName("sysdba");
			m_ds.setPassword("masterkey");
			m_ds.setDescription("Compiere DS");

			m_ds.setLoginTimeout(10);
			//
			m_ds.setMaxStatements(MAX_STATEMENTS);
			//
			m_ds.setType("ORACLE_MODE");
			Connection c = null;
			c = m_ds.getConnection();
			c
					.createStatement()
					.executeQuery(
							"SELECT * FROM ad_CLIENT WHERE created>TO_DATE('2001-01-01')");

			new DB_Fyracle().getDriver();
			c = DriverManager
					.getConnection(
							"jdbc:firebirdsql:oracle:localhost/3050:c:/devspace/apps/fyracle/data/compiere.fdb",
							"SYSDBA", "masterkey");

			c
					.createStatement()
					.executeQuery(
							"SELECT * FROM ad_CLIENT WHERE created>TO_DATE('2001-01-01')");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Get JDBC Catalog
	 * 
	 * @return null - not used
	 */
	public String getCatalog() {
		return null;
	} // getCatalog

	/**
	 * Get SQL Commands. The following variables are resolved:
	 * 
	 * @SystemPassword@,
	 * @CompiereUser@,
	 * @CompierePassword@
	 * @SystemPassword@,
	 * @DatabaseName@,
	 * @DatabaseDevice@
	 * @param cmdType
	 *            CMD_*
	 * @return array of commands to be executed
	 */
	public String[] getCommands(int cmdType) {
		if (CMD_CREATE_USER == cmdType)
			return new String[] {

			};
		//
		if (CMD_CREATE_DATABASE == cmdType)
			return new String[] {

			};
		//
		if (CMD_DROP_DATABASE == cmdType)
			return new String[] {

			};
		//
		return null;
	} // getCommands

	/**
	 * Create SQL TO Date String from Timestamp
	 * 
	 * @param time
	 *            Date to be converted
	 * @param dayOnly
	 *            true if time set to 00:00:00
	 * 
	 * @return TO_DATE('2001-01-30 18:10:20',''YYYY-MM-DD HH24:MI:SS') or
	 *         TO_DATE('2001-01-30',''YYYY-MM-DD')
	 */
	public String TO_DATE(Timestamp time, boolean dayOnly) {
		if (time == null) {
			if (dayOnly)
				return "TRUNC(SysDate)";
			return "SysDate";
		}

		StringBuffer dateString = new StringBuffer("TO_DATE('");
		// YYYY-MM-DD HH24:MI:SS.mmmm JDBC Timestamp format
		String myDate = time.toString();
		if (dayOnly) {
			dateString.append(myDate.substring(0, 10));
			dateString.append("','YYYY-MM-DD')");
		} else {
			dateString.append(myDate.substring(0, myDate.indexOf("."))); // cut
																			// off
																			// miliseconds
			dateString.append("','YYYY-MM-DD HH24:MI:SS')");
		}
		return dateString.toString();
	} // TO_DATE

	/**
	 * Create SQL for formatted Date, Number
	 * 
	 * @param columnName
	 *            the column name in the SQL
	 * @param displayType
	 *            Display Type
	 * @param AD_Language
	 *            6 character language setting (from Env.LANG_*)
	 * 
	 * @return TRIM(TO_CHAR(columnName,'9G999G990D00','NLS_NUMERIC_CHARACTERS='',.'''))
	 *         or TRIM(TO_CHAR(columnName,'TM9')) depending on DisplayType and
	 *         Language
	 * @see org.compiere.util.DisplayType
	 * @see org.compiere.util.Env
	 * 
	 */
	public String TO_CHAR(String columnName, int displayType, String AD_Language) {
		StringBuffer retValue = new StringBuffer("TRIM(TO_CHAR(");
		retValue.append(columnName);

		// Numbers
		if (DisplayType.isNumeric(displayType)) {
			if (displayType == DisplayType.Amount)
				retValue.append(",'9G999G990D00'");
			else
				retValue.append(",'TM9'");
			// TO_CHAR(GrandTotal,'9G999G990D00','NLS_NUMERIC_CHARACTERS='',.''')
			if (!Language.isDecimalPoint(AD_Language)) // reversed
				retValue.append(",'NLS_NUMERIC_CHARACTERS='',.'''");
		} else if (DisplayType.isDate(displayType)) {
			retValue.append(",'").append(
					Language.getLanguage(AD_Language).getDBdatePattern())
					.append("'");
		}
		retValue.append("))");
		//
		return retValue.toString();
	} // TO_CHAR

	/**
	 * Return number as string for INSERT statements with correct precision
	 * 
	 * @param number
	 *            number
	 * @param displayType
	 *            display Type
	 * @return number as string
	 */
	public String TO_NUMBER(BigDecimal number, int displayType) {
		if (number == null)
			return "NULL";
		return number.toString();
	} // TO_NUMBER

	/**
	 * Get Name of System User
	 * 
	 * @return system
	 */
	public String getSystemUser() {
		return "sysdba";
	} // getSystemUser

	/**
	 * Get Name of System Database
	 * 
	 * @param databaseName
	 *            database Name
	 * @return e.g. master or database Name
	 */
	public String getSystemDatabase(String databaseName) {
		return databaseName;
	} // getSystemDatabase

	public String getAlternativeSQL(int reExNo, String msg, String sql) {
		return null;
	}

	public String getConnectionURL(String connectionURL, String userName) {
		m_userName = userName;
		m_connectionURL = connectionURL;
		return m_connectionURL;
	}

	public String getConstraintType(Connection conn, String tableName,
			String IXName) {
		if (IXName == null || IXName.length() == 0)
			return "0";
		if (IXName.endsWith("_KEY"))
			return "1" + IXName;
		else
			return "0";
	}

	public Convert getConvert() {
		return m_convert;
	}

	public boolean isSupported(String sql) {
		return true;
	}

} // DB_Firebird

class DataSourceImpl implements DataSource {
	DriverConnectionPoolDataSource impl;

	public DataSourceImpl() {
		impl = new DriverConnectionPoolDataSource();
		impl.setPooling(true);
		impl.setStatementPooling(true);
		impl.setDriverClassName("org.firebirdsql.jdbc.FBDriver");
	}

	public Connection getConnection() throws SQLException {
		return impl.getPooledConnection("SYSDBA", "masterkey").getConnection();
	}

	public void setJdbcUrl(String url) throws SQLException {
		impl.setJdbcUrl(url);
	}

	public Connection getConnection(String username, String password)
			throws SQLException {
		return impl.getPooledConnection(username, password).getConnection();
	}

	public PrintWriter getLogWriter() throws SQLException {
		return impl.getLogWriter();
	}

	public int getLoginTimeout() throws SQLException {
		return impl.getLoginTimeout();
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		impl.setLogWriter(out);
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		impl.setLoginTimeout(seconds);
	}

	public boolean equals(Object obj) {
		return impl.equals(obj);
	}

	public void setMaxConnections(int arg0) {
		impl.setMaxPoolSize(arg0);
	}

	public void setMaxIdleTime(int arg0) {
		impl.setMaxIdleTime(arg0);
	}

	public void setMaxPoolSize(int arg0) {
		impl.setMaxPoolSize(arg0);
	}

	public void setMaxStatements(int arg0) {
		impl.setMaxStatements(arg0);
	}

	public void setProperty(String arg0, String arg1) {
		impl.setProperty(arg0, arg1);
	}

	public void setTransactionIsolationLevel(int arg0) {
		impl.setTransactionIsolationLevel(arg0);
	}

	public String toString() {
		return impl.toString();
	}

        public boolean isWrapperFor(java.lang.Class<?> iface) throws java.sql.SQLException
        {
            return false;
        }
        
        public <T> T unwrap(java.lang.Class<T> iface) throws java.sql.SQLException{return null;}
}