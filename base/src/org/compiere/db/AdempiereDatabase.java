/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.compiere.dbPort.Convert;
import org.compiere.model.PO;
import org.compiere.util.CLogger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//import org.compiere.util.CPreparedStatement;

/**
 *  Interface for Adempiere Databases
 *
 *  @author     Jorg Janke
 *  @version    $Id: AdempiereDatabase.java,v 1.5 2006/09/22 23:35:19 jjanke Exp $
 */
public interface AdempiereDatabase
{

	/** default lock timeout, 60 seconds **/
	static final int LOCK_TIME_OUT = 60;

	/**
	 *  Get Database Name
	 *  @return database short name
	 */
	public String getName();

	/**
	 *  Get Database Description
	 *  @return database long name and version
	 */
	public String getDescription();

	/**
	 *  Get and register Database Driver
	 *  @return Driver
	 *  @throws SQLException
	 */
	public Driver getDriver() throws SQLException;


	/**
	 *  Get Standard JDBC Port
	 *  @return standard port
	 */
	public int getStandardPort();

	/**
	 *  Get Database Connection String
	 *  @param connection Connection Descriptor
	 *  @return connection String
	 */
	public String getConnectionURL (CConnection connection);
	
	/**
	 * 	Get Connection URL
	 *	@param dbHost db Host
	 *	@param dbPort db Port
	 *	@param dbName db Name
	 *	@param userName user name
	 *	@return url
	 */
	public String getConnectionURL (String dbHost, int dbPort, String dbName,
		String userName);

	/**
	 *  Get Database Connection String
	 *  @param connectionURL Connection URL
	 *  @param userName user name
	 *  @return connection String
	 */
	public String getConnectionURL (String connectionURL, String userName);

	/**
	 * 	Get JDBC Catalog
	 *	@return catalog
	 */
	public String getCatalog();
	
	/**
	 * 	Get JDBC Schema
	 *	@return schema
	 */
	public String getSchema();

	/**
	 *  Supports BLOB
	 *  @return true if BLOB is supported
	 */
	public boolean supportsBLOB();

	/**
	 *  String Representation
	 *  @return info
	 */
	public String toString();

	
	/**************************************************************************
	 *  Convert an individual Oracle Style statements to target database statement syntax
	 *
	 *  @param oraStatement oracle statement
	 *  @return converted Statement
	 */
	public String convertStatement (String oraStatement);

	

	/**
	 *  Check if DBMS support the sql statement
	 *  @sql SQL statement
	 *  @return true: yes
	 */
	public boolean isSupported(String sql);

	
	

	/**
	 *  Get constraint type associated with the index
	 *  @conn connection
	 *  @tableName table name
	 *  @IXName Index name
	 *  @return String[0] = 0: do not know, 1: Primary Key  2: Foreign Key
	 *  		String[1] - String[n] = Constraint Name
	 */
	public String getConstraintType(Connection conn, String tableName, String IXName);
	

	/**
	 *  Check and generate an alternative SQL
	 *  @reExNo number of re-execution
	 *  @msg previous execution error message
	 *  @sql previous executed SQL
	 *  @return String, the alternative SQL, null if no alternative
	 */
	public String getAlternativeSQL(int reExNo, String msg, String sql);

	/**
	 *  Get Name of System User
	 *  @return e.g. sa, system
	 */
	public String getSystemUser();
	
	/**
	 *  Get Name of System Database
	 *  @param databaseName database Name
	 *  @return e.g. master or database Name
	 */
	public String getSystemDatabase(String databaseName);
	

	/**
	 *  Create SQL TO Date String from Timestamp
	 *
	 *  @param  time Date to be converted
	 *  @param  dayOnly true if time set to 00:00:00
	 *  @return date function
	 */
	public String TO_DATE (Timestamp time, boolean dayOnly);

	/**
	 *  Create SQL for formatted Date, Number
	 *
	 *  @param  columnName  the column name in the SQL
	 *  @param  displayType Display Type
	 *  @param  AD_Language 6 character language setting (from Env.LANG_*)
	 *
	 *  @return TRIM(TO_CHAR(columnName,'999G999G999G990D00','NLS_NUMERIC_CHARACTERS='',.'''))
	 *      or TRIM(TO_CHAR(columnName,'TM9')) depending on DisplayType and Language
	 *  @see org.compiere.util.DisplayType
	 *  @see org.compiere.util.Env
	 *
	 **/
	public String TO_CHAR (String columnName, int displayType, String AD_Language);

	
	/**
	 * 	Return number as string for INSERT statements with correct precision
	 *	@param number number
	 *	@param displayType display Type
	 *	@return number as string
	 */
	public String TO_NUMBER (BigDecimal number, int displayType);
	
	
	/**
	 * 	Return next sequence this Sequence
	 *	@param Sequence Name
	 */
	public int getNextID(String Name);
	
	/*
	 * Create Native Sequence
	 * @param Sequence Name
	 */
	public boolean createSequence(String name , int increment , int minvalue , int maxvalue ,int  start, String trxName);
	
	
	/** Create User commands					*/
	public static final int		CMD_CREATE_USER = 0;
	/** Create Database/Schema Commands			*/
	public static final int		CMD_CREATE_DATABASE = 1;
	/** Drop Database/Schema Commands			*/
	public static final int		CMD_DROP_DATABASE = 2;
	
	/**
	 * 	Get SQL Commands.
	 *  <code>
	 * 	The following variables are resolved:
	 * 	@SystemPassword@, @AdempiereUser@, @AdempierePassword@
	 * 	@SystemPassword@, @DatabaseName@, @DatabaseDevice@
	 *  </code>
	 *	@param cmdType CMD_*
	 *	@return array of commands to be executed
	 */
	public String[] getCommands (int cmdType);

	
	/**
	 * 	Get connection from Connection Pool.
	 * 	The connection is returned with the requested autoCommit and transaction isolation:
	 * 	CConnection does not verify them again (see {@link #applyConnectionState}).
	 *	@param connection info
	 *  @param autoCommit true if autocommit connection
	 *  @param transactionIsolation Connection transaction level
	 *	@return connection or null
	 *  @throws Exception
	 */
	public Connection getFromConnectionPool(CConnection connection,
											boolean autoCommit, int transactionIsolation) throws Exception;


	/**
	 * 	Get connection from Connection Pool Short Running
	 *	@param connection info
	 *  @param autoCommit true if autocommit connection
	 *  @param transactionIsolation Connection transaction level
	 *	@return connection or null
	 *  @throws Exception
	 */
	public Connection getFromConnectionPoolShortRunning(CConnection connection,
											boolean autoCommit, int transactionIsolation) throws Exception;

	/**
	 * 	Get Connection from Driver
	 *	@param connection info
	 *	@return connection or null
	 *  @throws SQLException
	 */
	public Connection getDriverConnection (CConnection connection) throws SQLException;

	/**
	 * 	Get Driver Connection
	 *	@param dbUrl URL
	 *	@param dbUid user
	 *	@param dbPwd password
	 *	@return connection
	 *	@throws SQLException
	 */
	public Connection getDriverConnection (String dbUrl, String dbUid, String dbPwd) 
		throws SQLException;

	/**
	 * 	Create DataSource
	 *	@param connection connection
	 *	@return data dource
	 */
	public DataSource getDataSource(CConnection connection);

	/**
	 * 	Get Status
	 * 	@return status info or null if no local datasource available
	 */
	public String getStatus();

	/**
	 * 	Close
	 */
	public void close();
	
	public Convert getConvert();

	/**
	 * @return true if jdbc driver support statement timeout
	 */
	public boolean isQueryTimeoutSupported();

	/**
	 * 	Get Data Type
	 *	@param DisplayType display type
	 *	@return data type
	 */
//	public String getDataType (int displayType, int precision,
//		boolean defaultValue)
      
	/**
	 * Default sql use to test whether a connection is still valid.
	 * Only the pools of MySQL and MariaDB use it; the PostgreSQL and Oracle pools leave
	 * connectionTestQuery unset, so Hikari validates with the JDBC4 Connection.isValid.
	 */
	public final static String DEFAULT_CONN_TEST_SQL = "SELECT Version FROM AD_System";

	/**
	 * autoCommit of a connection when it leaves the pool without being asked for anything else.
	 * The Hikari pools are configured with it, and Hikari restores it when a connection returns to the pool.
	 */
	public final static boolean POOL_DEFAULT_AUTO_COMMIT = true;

	/**
	 * Transaction isolation of a connection when it leaves the pool without being asked for anything else
	 * (the one that DB.getConnectionRO, getConnectionRW, getConnectionID and Trx ask for).
	 * The Hikari pools are configured with it, and Hikari restores it when a connection returns to the pool.
	 */
	public final static int POOL_DEFAULT_TRANSACTION_ISOLATION = Connection.TRANSACTION_READ_COMMITTED;

	/** Transaction isolation of a pool that is not known (not a Hikari pool, or one that was not configured with it) */
	public final static int UNKNOWN_TRANSACTION_ISOLATION = -1;

	/**
	 * Prefix of the JVM system properties that override the settings of the pool AdempiereDS, for example
	 * <code>-Dorg.adempiere.db.pool.maximumPoolSize=50</code>. The names are those of HikariConfig; the value is a number
	 * (milliseconds for the times). A property that is not set, or does not hold a valid value, leaves the default:
	 * <ul>
	 * <li>maximumPoolSize (150), minimumIdle (15), idleTimeout (client 0, standalone 60000), keepaliveTime (standalone 30000)</li>
	 * <li>connectionTimeout (Hikari default 30000), maxLifetime (Hikari default 1800000)</li>
	 * <li>leakDetectionThreshold (0, off)</li>
	 * </ul>
	 * A datasource that comes from JNDI (Jetty, Tomcat, WildFly) is configured by its server, not by these properties.
	 */
	public final static String POOL_PROPERTY_PREFIX = "org.adempiere.db.pool.";

	/** Same as {@link #POOL_PROPERTY_PREFIX} for the short running pool AdempiereSRDS */
	public final static String POOL_PROPERTY_PREFIX_SHORT_RUNNING = "org.adempiere.db.poolShortRunning.";

	/**
	 * Configure the connection state defaults of a Hikari pool and apply the overrides of the JVM system properties.
	 * The pool registers its MBean (<code>com.zaxxer.hikari:type=Pool (poolName)</code>) so that waits and usage can be monitored through JMX.
	 * Call it after the settings of the database, so that a system property wins.
	 * @param config pool configuration
	 * @param propertyPrefix {@link #POOL_PROPERTY_PREFIX} or {@link #POOL_PROPERTY_PREFIX_SHORT_RUNNING}
	 */
	public static void configurePool(HikariConfig config, String propertyPrefix)
	{
		config.setAutoCommit(POOL_DEFAULT_AUTO_COMMIT);
		config.setTransactionIsolation("TRANSACTION_READ_COMMITTED");
		config.setRegisterMbeans(true);
		override(propertyPrefix, "maximumPoolSize", value -> config.setMaximumPoolSize(Math.toIntExact(value)));
		override(propertyPrefix, "minimumIdle", value -> config.setMinimumIdle(Math.toIntExact(value)));
		override(propertyPrefix, "idleTimeout", config::setIdleTimeout);
		override(propertyPrefix, "keepaliveTime", config::setKeepaliveTime);
		override(propertyPrefix, "connectionTimeout", config::setConnectionTimeout);
		override(propertyPrefix, "leakDetectionThreshold", config::setLeakDetectionThreshold);
		override(propertyPrefix, "maxLifetime", config::setMaxLifetime);
	}

	/** Idle connections that the short running pool keeps: it is created on the first report and a client may never use it again */
	public final static int SHORT_RUNNING_MINIMUM_IDLE = 1;

	/**
	 * Configure the pool AdempiereSRDS that a database builds when the application server does not give it (JNDI):
	 * the state defaults of {@link #configurePool}, read only (it serves the report engine), and few idle connections.
	 * Hikari sets the read only flag once per physical connection, not on every checkout. Call it after the size
	 * settings of the database; the {@link #POOL_PROPERTY_PREFIX_SHORT_RUNNING} system properties still win.
	 * keepaliveTime is not set: Hikari leaves it disabled (a value below 30 s is ignored) and validates a connection that
	 * stayed idle when it is borrowed.
	 * @param config pool configuration
	 */
	public static void configureShortRunningPool(HikariConfig config)
	{
		config.setReadOnly(true);
		config.setMinimumIdle(SHORT_RUNNING_MINIMUM_IDLE);
		configurePool(config, POOL_PROPERTY_PREFIX_SHORT_RUNNING);
	}

	/**
	 * Apply the value of a pool system property, when it is set.
	 * A value that is not a number, or that Hikari refuses, is logged and the default stays.
	 */
	private static void override(String prefix, String name, java.util.function.LongConsumer setter)
	{
		String value = System.getProperty(prefix + name);
		if (value == null || value.trim().isEmpty())
			return;
		try
		{
			setter.accept(Long.parseLong(value.trim()));
		}
		catch (RuntimeException exception)
		{
			CLogger.getCLogger(AdempiereDatabase.class).warning(prefix + name + "=" + value + " is not valid (" + exception.getMessage() + "), the default stays");
		}
	}

	/**
	 * Transaction isolation that the connections of a datasource have when they leave the pool.
	 * @param dataSource the pool of the database
	 * @return a Connection.TRANSACTION_* constant, or {@link #UNKNOWN_TRANSACTION_ISOLATION} when the datasource is not a
	 * Hikari pool or was not configured with transactionIsolation (a JNDI datasource of an older installation)
	 */
	public static int getPoolTransactionIsolation(DataSource dataSource)
	{
		if (!(dataSource instanceof HikariDataSource))
			return UNKNOWN_TRANSACTION_ISOLATION;
		String isolation = ((HikariDataSource) dataSource).getTransactionIsolation();
		if (isolation == null)
			return UNKNOWN_TRANSACTION_ISOLATION;
		switch (isolation.trim())
		{
			case "TRANSACTION_READ_UNCOMMITTED":
				return Connection.TRANSACTION_READ_UNCOMMITTED;
			case "TRANSACTION_READ_COMMITTED":
				return Connection.TRANSACTION_READ_COMMITTED;
			case "TRANSACTION_REPEATABLE_READ":
				return Connection.TRANSACTION_REPEATABLE_READ;
			case "TRANSACTION_SERIALIZABLE":
				return Connection.TRANSACTION_SERIALIZABLE;
			default:
				return UNKNOWN_TRANSACTION_ISOLATION;
		}
	}

	/**
	 * Give a connection that comes from the pool the autoCommit and the transaction isolation that were asked for.
	 * <p>
	 * Both calls cost a round trip to the database (<code>SET SESSION CHARACTERISTICS</code> in PostgreSQL),
	 * so they are made only when the connection does not have the value already: autoCommit is read from the driver
	 * (local), and the isolation is compared with the one that the pool gives to every connection
	 * (<code>poolIsolation</code>). When the pool default is not known, the isolation is always set.
	 * Hikari restores both values when a connection returns to the pool, so a connection that was handed out with
	 * another value does not pass it on to the next checkout.
	 * @param connection connection just taken from the pool
	 * @param autoCommit autoCommit asked for
	 * @param transactionIsolation transaction isolation asked for
	 * @param poolIsolation {@link #getPoolTransactionIsolation(DataSource)} of the pool of the connection
	 * @throws SQLException
	 */
	public static void applyConnectionState(Connection connection, boolean autoCommit,
											int transactionIsolation, int poolIsolation) throws SQLException
	{
		if (connection.getAutoCommit() != autoCommit)
			connection.setAutoCommit(autoCommit);
		if (poolIsolation == UNKNOWN_TRANSACTION_ISOLATION || poolIsolation != transactionIsolation)
			connection.setTransactionIsolation(transactionIsolation);
	}

	/**
	 * Is the database have sql extension that return a subset of the query result
	 * @return boolean
	 */
	public boolean isPagingSupported();

	/**
	 * modify sql to return a subset of the query result
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 */
	public String addPagingSQL(String sql, int start, int end);

	/**
	 * Lock PO for update
	 * @param po
	 * @param timeout
	 * @return true if lock is granted
	 */
	public boolean forUpdate(PO po, int timeout);

	public String getNameOfUniqueConstraintError(Exception e);

}   //  AdempiereDatabase

