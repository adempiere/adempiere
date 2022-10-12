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
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import oracle.jdbc.OracleDriver;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.Adempiere;
import org.compiere.dbPort.Convert;
import org.compiere.dbPort.Convert_Oracle;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Ini;
import org.compiere.util.Language;
import org.compiere.util.Util;

/**
 *  Oracle Database Port
 *
 *  @author     Jorg Janke
 *  @version    $Id: DB_Oracle.java,v 1.7 2006/09/22 23:35:19 jjanke Exp $
 *  ---
 *  Modifications: Refactoring. Replaced Oracle Cache Manager with C3P0
 *  connection pooling framework for better and more efficient connnection handling
 *  
 *  @author Ashley Ramdass (Posterita)
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *			<li> FR [ 391 ] getSchema method in DB_PostgreSQL.java is better use the adempiere user
 *			@see https://github.com/adempiere/adempiere/issues/391
 */
public class DB_Oracle implements AdempiereDatabase
{
    /**
     *  Oracle Database
     */
    public DB_Oracle()
    {
        /** Causes VPN problems ???
        try
        {
            getDriver();
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, e.getMessage());
        }
        **/
        // teo [ bug 1638208 ]: oracle 10g DATETIME issue
        // http://www.oracle.com/technology/tech/java/sqlj_jdbc/htdocs/jdbc_faq.htm#08_01
        try
        {
            System.setProperty("oracle.jdbc.V8Compatible", "true");
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, e.getMessage());
        }
    }   //  DB_Oracle

    /** Static Driver               */
    private static OracleDriver     s_driver = null;
    /** Driver Class Name           */
    public static final String      DRIVER = "oracle.jdbc.OracleDriver";

    /** Default Port                */
    public static final int         DEFAULT_PORT = 1521;
    /** Default Connection Manager Port */
    public static final int         DEFAULT_CM_PORT = 1630;

    /** Connection String           */
    private String                  m_connectionURL;

    /** Statement Cache (50)        */
    public static final String     MAX_STATEMENTS = "200";

    /** Data Source	Long Running 	*/
    private DataSource datasourceLongRunning = null;
    /** Data Source	Short Running 	*/
    private DataSource datasourceShortRunning = null;
    /** Cached User Name            */
    private String                  m_userName = null;

    private Convert m_convert = new Convert_Oracle();

    /** Logger          */
    private static CLogger          log = CLogger.getCLogger (DB_Oracle.class);


    private static int              m_maxbusyconnections = 0;


    /**
     *  Get Database Name
     *  @return database short name
     */
    public String getName()
    {
        return Database.DB_ORACLE;
    }   //  getName

    /**
     *  Get Database Description
     *  @return database long name and version
     */
    public String getDescription()
    {
        try
        {
            if (s_driver == null)
                getDriver();
        }
        catch (Exception e)
        {
        }
        if (s_driver != null)
            return s_driver.toString();
        return "No Driver";
    }   //  getDescription

    /**
     *  Get Standard JDBC Port
     *  @return standard port
     */
    public int getStandardPort()
    {
        return DEFAULT_PORT;
    }   //  getStandardPort

    /**
     *  Get and register Database Driver
     *  @return Driver
     *  @throws SQLException
     */
    public Driver getDriver() throws SQLException
    {
        if (s_driver == null)
        {
            //  Speed up transfer rate
            System.setProperty("oracle.jdbc.TcpNoDelay", "true");
            //  Oracle Multi - Language
            System.setProperty("oracle.jdbc.defaultNChar", "true");
            //
            s_driver = new OracleDriver();
            DriverManager.registerDriver (s_driver);
            DriverManager.setLoginTimeout (Database.CONNECTION_TIMEOUT);
        }
        return s_driver;
    }   //  getDriver

    /**
     *  Get Database Connection String.
     *  <pre>
     *  Timing:
     *  - CM with source_route not in address_list  = 28.5 sec
     *  - CM with source_route in address_list      = 58.0 sec
     *  - direct    = 4.3-8 sec  (no real difference if on other box)
     *  - bequeath  = 3.4-8 sec
     *  </pre>
     *  @param connection Connection Descriptor
     *  @return connection String
     */
    public String getConnectionURL (CConnection connection)
    {
        StringBuffer sb = null;
        //  Server Connections (bequeath)
        if (connection.isBequeath())
        {
            sb = new StringBuffer ("jdbc:oracle:oci8:@");
            //  bug: does not work if there is more than one db instance - use Net8
        //  sb.append(connection.getDbName());
        }
        else        //  thin driver
        {
            sb = new StringBuffer ("jdbc:oracle:thin:@");
            //  direct connection
            if (connection.isViaFirewall())
            {
                //  (description=(address_list=
                //  ( (source_route=yes)
                //    (address=(protocol=TCP)(host=cmhost)(port=1630))
                //    (address=(protocol=TCP)(host=dev)(port=1521))
                //  (connect_data=(service_name=dev1.adempiere.org)))
                sb.append("(DESCRIPTION=(ADDRESS_LIST=")
                    .append("(SOURCE_ROUTE=YES)")
                    .append("(ADDRESS=(PROTOCOL=TCP)(HOST=").append(connection.getFwHost())
                        .append(")(PORT=").append(connection.getFwPort()).append("))")
                    .append("(ADDRESS=(PROTOCOL=TCP)(HOST=").append(connection.getDbHost())
                        .append(")(PORT=").append(connection.getDbPort()).append(")))")
                    .append("(CONNECT_DATA=(SERVICE_NAME=").append(connection.getDbName()).append(")))");
            }
            else
            {
                //  old: jdbc:oracle:thin:@dev2:1521:sid
                //  new: jdbc:oracle:thin:@//dev2:1521/serviceName
                sb.append("//")
                    .append(connection.getDbHost())
                    .append(":").append(connection.getDbPort())
                    .append("/").append(connection.getDbName());
            }
        }
        m_connectionURL = sb.toString();
    //  log.config(m_connectionURL);
        //
        m_userName = connection.getDbUid();
        return m_connectionURL;
    }   //  getConnectionURL

    /**
     *  Get Connection URL.
     *  http://download-east.oracle.com/docs/cd/B14117_01/java.101/b10979/urls.htm#BEIDBFDF
     *  @param dbHost db Host
     *  @param dbPort db Port
     *  @param dbName db Name
     *  @param userName user name
     *  @return connection
     */
    public String getConnectionURL (String dbHost, int dbPort, String dbName,
        String userName)
    {
        m_userName = userName;
        m_connectionURL = "jdbc:oracle:thin:@//"
            + dbHost + ":" + dbPort + "/" + dbName;
        return m_connectionURL;
    }   //  getConnectionURL

    /**
     *  Get Database Connection String
     *  @param connectionURL Connection URL
     *  @param userName user name
     *  @return connection String
     */
    public String getConnectionURL (String connectionURL, String userName)
    {
        m_userName = userName;
        m_connectionURL = connectionURL;
        return m_connectionURL;
    }   //  getConnectionURL

    /**
     *  Get JDBC Catalog
     *  @return null - not used
     */
    public String getCatalog()
    {
        return null;
    }   //  getCatalog

    /**
     *  Get JDBC Schema
     *  @return user name
     */
    public String getSchema() {
    	if (m_userName == null) {
	        CConnection cconn = CConnection.get(Adempiere.getCodeBaseHost());
	        m_userName = cconn.getDbUid();
	    }
    	//	Validate
        if (m_userName == null) {
        	log.severe("User Name not set (yet) - call getConnectionURL first");
        	return null;
        }
        //	
        return m_userName.toUpperCase();
    }   //  getSchema

    /**
     *  Supports BLOB
     *  @return true if BLOB is supported
     */
    public boolean supportsBLOB()
    {
        return true;
    }   //  supportsBLOB

    /**
     *  String Representation
     *  @return info
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer("DB_Oracle[");
        sb.append(m_connectionURL);
        try
        {
            StringBuffer logBuffer = new StringBuffer(50);
            logBuffer.append("# Connections: ").append(datasourceLongRunning.toString());
           /* logBuffer.append("# Connections: ").append(m_ds.getNumConnections());
            logBuffer.append(" , # Busy Connections: ").append(m_ds.getNumBusyConnections());
            logBuffer.append(" , # Idle Connections: ").append(m_ds.getNumIdleConnections());
            logBuffer.append(" , # Orphaned Connections: ").append(m_ds.getNumUnclosedOrphanedConnections());*/
        }
        catch (Exception e)
        {
            sb.append("=").append(e.getLocalizedMessage());
        }
        sb.append("]");
        return sb.toString();
    }   //  toString

    /**
     *  Get Status
     *  @return status info
     */
    public String getStatus()
    {
        if (datasourceLongRunning == null)
        {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        try
        {
            sb.append("# Connections: ").append(datasourceLongRunning.toString());
            /*sb.append("# Connections: ").append(m_ds.getNumConnections());
            sb.append(" , # Busy Connections: ").append(m_ds.getNumBusyConnections());
            sb.append(" , # Idle Connections: ").append(m_ds.getNumIdleConnections());
            sb.append(" , # Orphaned Connections: ").append(m_ds.getNumUnclosedOrphanedConnections());*/
        }
        catch (Exception e)
        {}
        return sb.toString();
    }   //  getStatus


    /**************************************************************************
     *  Convert an individual Oracle Style statements to target database statement syntax.
     *  @param oraStatement oracle statement
     *  @return converted Statement oracle statement
     */
    public String convertStatement (String oraStatement)
    {
    	Convert.logMigrationScript(oraStatement, null, null);
        return oraStatement;
    }   //  convertStatement



    /**
     *  Check if DBMS support the sql statement
     *  @sql SQL statement
     *  @return true: yes
     */
    public boolean isSupported(String sql)
    {
        return true;
        //jz temp, modify later
    }



    /**
     *  Get constraint type associated with the index
     *  @tableName table name
     *  @IXName Index name
     *  @return String[0] = 0: do not know, 1: Primary Key  2: Foreign Key
     *          String[1] - String[n] = Constraint Name
     */
    public String getConstraintType(Connection conn, String tableName, String IXName)
    {
        if (IXName == null || IXName.length()==0)
            return "0";
        if (IXName.endsWith("_KEY"))
            return "1"+IXName;
        else
            return "0";
        //jz temp, modify later from user.constraints
    }

    /**
     *  Get Name of System User
     *  @return system
     */
    public String getSystemUser()
    {
        return "system";
    }   //  getSystemUser

    /**
     *  Get Name of System Database
     *  @param databaseName database Name
     *  @return e.g. master or database Name
     */
    public String getSystemDatabase(String databaseName)
    {
        return databaseName;
    }   //  getSystemDatabase


    /**
     *  Create SQL TO Date String from Timestamp
     *
     *  @param  time Date to be converted
     *  @param  dayOnly true if time set to 00:00:00
     *
     *  @return TO_DATE('2001-01-30 18:10:20',''YYYY-MM-DD HH24:MI:SS')
     *      or  TO_DATE('2001-01-30',''YYYY-MM-DD')
     */
    public String TO_DATE (Timestamp time, boolean dayOnly)
    {
        if (time == null)
        {
            if (dayOnly)
                return "TRUNC(SysDate)";
            return "SysDate";
        }

        StringBuffer dateString = new StringBuffer("TO_DATE('");
        //  YYYY-MM-DD HH24:MI:SS.mmmm  JDBC Timestamp format
        String myDate = time.toString();
        if (dayOnly)
        {
            dateString.append(myDate.substring(0,10));
            dateString.append("','YYYY-MM-DD')");
        }
        else
        {
            dateString.append(myDate.substring(0, myDate.indexOf('.')));    //  cut off miliseconds
            dateString.append("','YYYY-MM-DD HH24:MI:SS')");
        }
        return dateString.toString();
    }   //  TO_DATE

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
     *   */
    public String TO_CHAR (String columnName, int displayType, String AD_Language)
    {
        StringBuffer retValue = new StringBuffer("TRIM(TO_CHAR(");
        retValue.append(columnName);

        //  Numbers
        if (DisplayType.isNumeric(displayType))
        {
            if (displayType == DisplayType.Amount)
                retValue.append(",'999G999G999G990D00'");
            else
                retValue.append(",'TM9'");
            //  TO_CHAR(GrandTotal,'999G999G999G990D00','NLS_NUMERIC_CHARACTERS='',.''')
            if (!Language.isDecimalPoint(AD_Language))      //  reversed
                retValue.append(",'NLS_NUMERIC_CHARACTERS='',.'''");
        }
        else if (DisplayType.isDate(displayType))
        {
            retValue.append(",'")
                .append(Language.getLanguage(AD_Language).getDBdatePattern())
                .append("'");
        }
        retValue.append("))");
        //
        return retValue.toString();
    }   //  TO_CHAR

    /**
     *  Return number as string for INSERT statements with correct precision
     *  @param number number
     *  @param displayType display Type
     *  @return number as string
     */
    public String TO_NUMBER (BigDecimal number, int displayType)
    {
        if (number == null)
            return "NULL";
        BigDecimal result = number;
        int scale = DisplayType.getDefaultPrecision(displayType);
        if (scale > number.scale())
        {
            try
            {
                result = number.setScale(scale, RoundingMode.HALF_UP);
            }
            catch (Exception e)
            {
            //  log.severe("Number=" + number + ", Scale=" + " - " + e.getMessage());
            }
        }
        return result.toString();
    }   //  TO_NUMBER


    /**
     *  Get SQL Commands.
     *  The following variables are resolved:
     *  @SystemPassword@, @AdempiereUser@, @AdempierePassword@
     *  @SystemPassword@, @DatabaseName@, @DatabaseDevice@
     *  @param cmdType CMD_*
     *  @return array of commands to be executed
     */
    public String[] getCommands (int cmdType)
    {
        if (CMD_CREATE_USER == cmdType)
            return new String[]
            {

            };
        //
        if (CMD_CREATE_DATABASE == cmdType)
            return new String[]
            {

            };
        //
        if (CMD_DROP_DATABASE == cmdType)
            return new String[]
            {

            };
        //
        return null;
    }   //  getCommands

    /**
     *  Create DataSource
     *  @param connection connection
     *  @return data dource
     */
    public DataSource getDataSource(CConnection connection) {
        if (datasourceLongRunning != null)
            return datasourceLongRunning;

        try
        {
            if (Ini.isClient()) {
                log.warning("Config Hikari Connection Pool Datasource");
                HikariConfig config = new HikariConfig();
                config.setDriverClassName(DRIVER);
                config.setJdbcUrl(getConnectionURL(connection));
                config.setUsername(connection.getDbUid());
                config.setPassword(connection.getDbPwd());
                config.setConnectionTestQuery(DEFAULT_CONN_TEST_SQL);
                config.setIdleTimeout(0);
                config.setMinimumIdle(15);
                config.setMaximumPoolSize(150);
                config.setPoolName("AdempiereDS");
                config.addDataSourceProperty( "cachePrepStmts" , "true" );
                config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
                config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
                datasourceLongRunning = new HikariDataSource(config);
                log.warning("Starting Client Hikari Connection Pool");
            } else {
                Optional<String> maybeApplicationType = Optional.ofNullable(System.getenv("ADEMPIERE_APPS_TYPE"));
                datasourceLongRunning = maybeApplicationType
                        .map(applicationType -> {
                            if ("wildfly".equals(applicationType)) {
                                try {
                                    Context initCtx = new InitialContext();
                                    DataSource dataSource = (DataSource) initCtx.lookup("java:/AdempiereDS");
                                    log.warning("Connection Lookup JNDI Datasource for java:/AdempiereDS Hikari Connection Pool");
                                    return dataSource;
                                } catch (Exception namingException) {
                                    datasourceLongRunning = null;
                                    log.log(Level.SEVERE, "Could not initialise Hikari Connection Pool", namingException);
                                    namingException.printStackTrace();
                                }
                            }
                            try {
                                DataSource dataSource = InitialContext.doLookup("java:comp/env/java/AdempiereDS");
                                log.warning("Connection Lookup JNDI Datasource for java:comp/env/java/AdempiereDS Hikari Connection Pool");
                                return dataSource;
                            } catch (Exception namingException) {
                                datasourceLongRunning = null;
                                log.log(Level.SEVERE, "Application Server does not exist Could not initialise Hikari Connection Pool", namingException);
                                namingException.printStackTrace();
                            }
                            log.warning("Connection successful using Standalone Hikari Config Connection Pool");
                            HikariConfig config = new HikariConfig();
                            config.setDriverClassName(DRIVER);
                            config.setJdbcUrl(getConnectionURL(connection));
                            config.setUsername(connection.getDbUid());
                            config.setPassword(connection.getDbPwd());
                            config.setConnectionTestQuery(DEFAULT_CONN_TEST_SQL);
                            config.setIdleTimeout(60000);
                            config.setKeepaliveTime(30000);
                            config.setMinimumIdle(15);
                            config.setMaximumPoolSize(150);
                            config.setPoolName("AdempiereDS");
                            config.addDataSourceProperty( "cachePrepStmts" , "true" );
                            config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
                            config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
                            return new HikariDataSource(config);
                        }).orElseThrow(() -> new AdempiereException("The ADEMPIERE_APPS_TYPE environment variable is not set, so it is not possible to initialize the Hikari Connection Pool"));
            }
        } catch (Exception exception) {
            datasourceLongRunning = null;
            log.log(Level.SEVERE, "Application Server does not exist, no is possible to initialize the initialise Hikari Connection Pool", exception);
            exception.printStackTrace();
        }
        return datasourceLongRunning;
    }

    /**
     * 	Create DataSource Short Running
     *	@param connection connection
     *	@return data source Short Running
     */
    public DataSource getDataSourceShortRunning(CConnection connection) {
        if (datasourceShortRunning != null)
            return datasourceShortRunning;

        try
        {
            if (Ini.isClient()) {
                log.warning("Config Hikari Connection Pool Short Running Datasource");
                HikariConfig config = new HikariConfig();
                config.setDriverClassName(DRIVER);
                config.setJdbcUrl(getConnectionURL(connection));
                config.setUsername(connection.getDbUid());
                config.setPassword(connection.getDbPwd());
                config.addDataSourceProperty( "poolName" , "AdempiereSRDS" );
                config.addDataSourceProperty( "cachePrepStmts" , "true" );
                config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
                config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
                config.addDataSourceProperty("connectionTestQuery", DEFAULT_CONN_TEST_SQL);
                config.addDataSourceProperty( "idleTimeout" , "1200" );
                config.addDataSourceProperty("maximumPoolSize", "10");
                HikariDataSource cpds = new HikariDataSource(config);
                datasourceShortRunning = cpds;
                log.warning("Starting Client Hikari Connection Pool");
            } else {
                Optional<String> maybeApplicationType = Optional.ofNullable(System.getenv("ADEMPIERE_APPS_TYPE"));
                datasourceShortRunning = maybeApplicationType
                        .map(applicationType -> {
                            if ("wildfly".equals(applicationType)) {
                                try {
                                    Context initCtx = new InitialContext();
                                    DataSource dataSource = (DataSource) initCtx.lookup("java:/AdempiereSRDS");
                                    log.warning("Connection Lookup JNDI Short Running Datasource for java:/AdempiereSRDS Hikari Connection Pool");
                                    return dataSource;
                                } catch (Exception namingException) {
                                    datasourceShortRunning = null;
                                    log.log(Level.SEVERE, "Could not initialise Short Running Hikari Connection Pool", namingException);
                                    namingException.printStackTrace();
                                }
                            }
                            try {
                                DataSource dataSource = InitialContext.doLookup("java:comp/env/java/AdempiereSRDS");
                                log.warning("Connection Lookup JNDI Short Running Datasource for java:comp/env/java/AdempiereSRDS Hikari Connection Pool");
                                return dataSource;
                            } catch (Exception namingException) {
                                datasourceShortRunning = null;
                                log.log(Level.SEVERE, "Application Server does not exist Could not initialise Short Running Hikari Connection Pool", namingException);
                                namingException.printStackTrace();
                            }
                            log.warning("Connection successful using Standalone Short Running Hikari Config Connection Pool");
                            HikariConfig config = new HikariConfig();
                            config.setDriverClassName(DRIVER);
                            config.setJdbcUrl(getConnectionURL(connection));
                            config.setUsername(connection.getDbUid());
                            config.setPassword(connection.getDbPwd());
                            config.addDataSourceProperty("poolName", "AdempiereSRDS");
                            config.addDataSourceProperty("cachePrepStmts", "true");
                            config.addDataSourceProperty("prepStmtCacheSize", "250");
                            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
                            config.addDataSourceProperty("connectionTestQuery", DEFAULT_CONN_TEST_SQL);
                            config.addDataSourceProperty("idleTimeout", "1200");
                            config.addDataSourceProperty("maximumPoolSize", "5");
                            return new HikariDataSource(config);
                        }).orElseThrow(() -> new AdempiereException("The ADEMPIERE_APPS_TYPE environment variable is not set, so it is not possible to initialize the Short Running Hikari Connection Pool"));
            }
        } catch (Exception exception) {
            datasourceShortRunning = null;
            log.log(Level.SEVERE, "Application Server does not exist, no is possible to initialize the Short Running initialise Short Running Hikari Connection Pool", exception);
            exception.printStackTrace();
        }
        return datasourceShortRunning;
    }

    /**
     *  Get Cached Connection
     *  @param connection info
     *  @param autoCommit true if autocommit connection
     *  @param transactionIsolation Connection transaction level
     *  @return connection or null
     *  @throws Exception
     */
    public Connection getFromConnectionPool(CConnection connection,
                                            boolean autoCommit, int transactionIsolation)
        throws Exception
    {
        Connection conn = null;
        Exception exception = null;
        try
        {
            if (datasourceLongRunning == null)
                getDataSource(connection);

            //
            try
            {
                conn = (Connection) datasourceLongRunning.getConnection();
                if (conn != null)
                {
                    if (conn.getTransactionIsolation() != transactionIsolation)
                        conn.setTransactionIsolation(transactionIsolation);
                    if (conn.getAutoCommit() != autoCommit)
                        conn.setAutoCommit(autoCommit);
                }
            }
            catch (Exception e)
            {
                exception = e;
                conn = null;
                if (DBException.isInvalidUserPassError(e))
                {
                	System.err.println("Cannot connect to database: "
                            + getConnectionURL(connection)
                            + " - UserID=" + connection.getDbUid());
                }
            }

            if (conn == null && exception != null)
            {
            	System.err.println(exception.toString());
            }
        }
        catch (Exception e)
        {
            exception = e;
        }

        if (exception != null)
            throw exception;
        return conn;
    }   //  getCachedConnection


    /**
     *  Get Cached Connection
     *  @param connection info
     *  @param autoCommit true if autocommit connection
     *  @param transactionIsolation Connection transaction level
     *  @return connection or null
     *  @throws Exception
     */
    @Override
    public Connection getFromConnectionPoolShortRunning(CConnection connection,
                                            boolean autoCommit, int transactionIsolation)
            throws Exception
    {
        Connection localConnection = null;
        Exception exception = null;
        try
        {
            if (datasourceShortRunning == null)
                getDataSource(connection);

            //
            try
            {
                localConnection = datasourceShortRunning.getConnection();
                if (localConnection != null)
                {
                    if (localConnection.getTransactionIsolation() != transactionIsolation)
                        localConnection.setTransactionIsolation(transactionIsolation);
                    if (localConnection.getAutoCommit() != autoCommit)
                        localConnection.setAutoCommit(autoCommit);
                }
            }
            catch (Exception e)
            {
                exception = e;
                localConnection = null;
                if (DBException.isInvalidUserPassError(e))
                {
                    System.err.println("Cannot connect to database: "
                            + getConnectionURL(connection)
                            + " - UserID=" + connection.getDbUid());
                }
            }

            if (localConnection == null && exception != null)
            {
                System.err.println(exception.toString());
            }
        }
        catch (Exception e)
        {
            exception = e;
        }

        if (exception != null)
            throw exception;
        return localConnection;
    }   //  getCachedConnection



    /**
     *  Get Connection from Driver
     *  @param connection info
     *  @return connection or null
     *  @throws SQLException
     */
    public Connection getDriverConnection (CConnection connection) throws SQLException
    {
        getDriver();
        return DriverManager.getConnection (getConnectionURL (connection),
            connection.getDbUid(), connection.getDbPwd());
    }   //  getDriverConnection

    /**
     *  Get Driver Connection
     *  @param dbUrl URL
     *  @param dbUid user
     *  @param dbPwd password
     *  @return connection
     *  @throws SQLException
     */
    public Connection getDriverConnection (String dbUrl, String dbUid, String dbPwd)
        throws SQLException
    {
        getDriver();
        return DriverManager.getConnection (dbUrl, dbUid, dbPwd);
    }   //  getDriverConnection

    /**
     *  Close
     */
    public void close()
    {
        log.config(toString());
        if (datasourceLongRunning != null)
        {
            try
            {
               datasourceLongRunning.getConnection().close(); //m_ds.close();
            }
            catch (Exception e)
            {
                log.log(Level.SEVERE, "Could not close Data Source");
            }
        }
        datasourceLongRunning = null;
    }   //  close

    /**
     *  Clean up
     */
    public void cleanup()
    {

    }   //  cleanup

    /**
     *  Get Data Type
     *  @param columnName
     *  @param displayType display type
     *  @param precision precision
     *  @param defaultValue if true adds default value
     *  @return data type
     */
    public String getDataType (String columnName, int displayType, int precision,
        boolean defaultValue)
    {
        String retValue = null;
        //handle special case, bug [ 1618423 ]
        if (columnName != null)
        {
            if (displayType == DisplayType.Button
                && columnName.endsWith("_ID"))
            {
                retValue = "NUMBER(10)";
            }
        }
        if (retValue == null)
        {
            switch (displayType)
            {
                //  IDs
                case DisplayType.Account:
                case DisplayType.Assignment:
                case DisplayType.Color:
                case DisplayType.ID:
                case DisplayType.Location:
                case DisplayType.Locator:
                case DisplayType.PAttribute:
                case DisplayType.Search:
                case DisplayType.Table:
                case DisplayType.TableDir:
                case DisplayType.Image:
                    retValue = "NUMBER(10)";
                    break;

                // Dynamic Precision
                case DisplayType.Amount:
                    retValue = "NUMBER";
                    if (defaultValue)
                        retValue += " DEFAULT 0";
                    break;

                case DisplayType.Binary:
                    retValue = "BLOB";
                    break;

                case DisplayType.Button:
                    retValue = "CHAR(1)";
                    break;

                // Number Dynamic Precision
                case DisplayType.CostPrice:
                    retValue = "NUMBER";
                    if (defaultValue)
                        retValue += " DEFAULT 0";
                    break;

                //  Date
                case DisplayType.Date:
                case DisplayType.DateTime:
                case DisplayType.Time:
                    retValue = "DATE";
                    if (defaultValue)
                        retValue += " DEFAULT SYSDATE";
                    break;

                //  Number(10)
                case DisplayType.Integer:
                    retValue = "NUMBER(10)";
                    break;

                case DisplayType.List:
                    retValue = "CHAR(" + precision + ")";
                    break;

                //  NVARCHAR
                case DisplayType.Memo:
                case DisplayType.String:
                case DisplayType.Text:
                    retValue = "NVARCHAR(" + precision + ")";
                    break;

                case DisplayType.TextLong:
                    retValue = "CLOB";
                    break;

                //  Dyn Prec
                case DisplayType.Quantity:
                    retValue = "NUMBER";
                    break;

                case DisplayType.YesNo:
                    retValue = "CHAR(1)";
                    break;

                default:
                    log.severe("Unknown: " + displayType);
                    break;
            }
        }
        return retValue;
    }   //  getDataType


    /**
     *  Check and generate an alternative SQL
     *  @reExNo number of re-execution
     *  @msg previous execution error message
     *  @sql previous executed SQL
     *  @return String, the alternative SQL, null if no alternative
     */
    public String getAlternativeSQL(int reExNo, String msg, String sql)
    {
        //check reExNo or based on reExNo to do a decision. Currently none

        return null; //do not do re-execution of alternative SQL
    }


    /**************************************************************************
     *  Testing
     *  @param args ignored
     */
    public static void main (String[] args)
    {
        Adempiere.startupEnvironment(true);
        CConnection cc = CConnection.get();
        DB_Oracle db = (DB_Oracle)cc.getDatabase();
        db.cleanup();

        try
        {
            Connection conn = null;
        //  System.out.println("Driver=" + db.getDriverConnection(cc));
            DataSource ds = db.getDataSource(cc);
            System.out.println("DS=" + ds.getConnection());
            conn = db.getFromConnectionPool(cc, true, Connection.TRANSACTION_READ_COMMITTED);
            System.out.println("Cached=" + conn);
            System.out.println(db);
            //////////////////////////
            System.out.println("JAVA classpath: [\n" +
                System.getProperty("java.class.path") + "\n]");
                DatabaseMetaData dmd = conn.getMetaData();
                System.out.println("DriverVersion: ["+
                dmd.getDriverVersion()+"]");
                System.out.println("DriverMajorVersion: ["+
                dmd.getDriverMajorVersion()+"]");
                System.out.println("DriverMinorVersion: ["+
                dmd.getDriverMinorVersion()+"]");
                System.out.println("DriverName: ["+
                dmd.getDriverName()+"]");
                System.out.println("ProductName: ["+
                dmd.getDatabaseProductName() +"]");
                System.out.println("ProductVersion: [\n"+
                dmd.getDatabaseProductVersion()+"\n]");
            //////////////////////////
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
        db.cleanup();

        System.out.println("--------------------------------------------------");
        /**
        DROP TABLE X_Test;
        CREATE TABLE X_Test
        (
            Text1   NVARCHAR2(2000) NULL,
            Text2   VARCHAR2(2000)  NULL
        );
        **/
        try
        {
            String myString1 = "123456789 12345678";
            String myString = "";
            for (int i = 0; i < 99; i++)
                myString += myString1 + (char)('a'+i) + "\n";
            System.out.println(myString.length());
            System.out.println(Util.size(myString));
            //
            myString = Util.trimSize(myString, 2000);
            System.out.println(myString.length());
            System.out.println(Util.size(myString));
            //
            Connection conn2 = db.getFromConnectionPool(cc, true, Connection.TRANSACTION_READ_COMMITTED);
            /** **/
            PreparedStatement pstmt = conn2.prepareStatement
                ("INSERT INTO X_Test(Text1, Text2) values(?,?)");
            pstmt.setString(1, myString); // NVARCHAR2 column
            pstmt.setString(2, myString); // VARCHAR2 column
            System.out.println(pstmt.executeUpdate());
            /** **/
            Statement stmt = conn2.createStatement();
            System.out.println(stmt.executeUpdate
                ("INSERT INTO X_Test(Text1, Text2) values('" + myString + "','" + myString + "')"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        db.cleanup();
        System.out.println("--------------------------------------------------");
        System.exit(0);


        System.out.println("--------------------------------------------------");
        try
        {
            Connection conn1 = db.getFromConnectionPool(cc, false, Connection.TRANSACTION_READ_COMMITTED);
            Connection conn2 = db.getFromConnectionPool(cc, true, Connection.TRANSACTION_READ_COMMITTED);
            Connection conn3 = db.getFromConnectionPool(cc, false, Connection.TRANSACTION_READ_COMMITTED);
            System.out.println("3 -> " + db);
            conn1.close();
            conn2.close();
            conn1 = db.getFromConnectionPool(cc, true, Connection.TRANSACTION_READ_COMMITTED);
            conn2 = db.getFromConnectionPool(cc, true, Connection.TRANSACTION_READ_COMMITTED);
            System.out.println("3 -> " + db);
            conn1.close();
            conn2.close();
            conn3.close();
            System.out.println("0 -> " + db);
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }

        db.cleanup();

    //  System.exit(0);
        System.out.println("--------------------------------------------------");

        System.out.println(DB.getConnectionRO());
        System.out.println(DB.getConnectionRW());
        System.out.println(DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED));

        System.out.println(DB.getConnectionRO());
        System.out.println(DB.getConnectionRW());
        System.out.println(DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED));

        System.out.println(DB.getConnectionRO());
        System.out.println(DB.getConnectionRW());
        System.out.println(DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED));

        System.out.println(DB.getConnectionRO());
        System.out.println(DB.getConnectionRW());
        System.out.println(DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED));

        System.out.println(DB.getConnectionRO());
        System.out.println(DB.getConnectionRW());
        System.out.println(DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED));

        System.out.println(DB.getConnectionRO());
        System.out.println(DB.getConnectionRW());
        System.out.println(DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED));

        System.out.println(DB.getConnectionRO());
        System.out.println(DB.getConnectionRW());
        System.out.println(DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED));

        System.out.println(DB.getConnectionRO());
        System.out.println(DB.getConnectionRW());
        System.out.println(DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED));

        System.out.println(DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED));
        System.out.println(DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED));
        System.out.println(DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED));
        System.out.println(DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED));
        System.out.println(DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED));

        System.out.println(db);


        try
        {
            System.out.println("-- Sleeping --");
            Thread.sleep(60000);
            System.out.println(db);
            db.close();
            db.cleanup();
            System.out.println(db);
        }
        catch (InterruptedException e)
        {
        }



        /**
        //  Connection option 1
        try
        {
            System.setProperty("oracle.jdbc.Trace", "true");
            DriverManager.registerDriver(new OracleDriver());
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//dev:1521/dev", "adempiere", "adempiere");
            System.out.println("Catalog=" + con.getCatalog());
            DatabaseMetaData md = con.getMetaData();
            System.out.println("URL=" + md.getURL());
            System.out.println("User=" + md.getUserName());
            //
            System.out.println("Catalog");
            ResultSet rs = md.getCatalogs();
            while (rs.next())
                System.out.println("- " + rs.getString(1));
            //
            System.out.println("Table");
            rs = md.getTables(null, "ADEMPIERE", null, new String[] {"TABLE"});
            while (rs.next())
                System.out.println("- User=" + rs.getString(2) + " | Table=" + rs.getString(3)
                    + " | Type=" + rs.getString(4) + " | " + rs.getString(5));
            //
            System.out.println("Column");
            rs = md.getColumns(null, "ADEMPIERE", "C_ORDER", null);
            while (rs.next())
                System.out.println("- Tab=" + rs.getString(3) + " | Col=" + rs.getString(4)
                    + " | Type=" + rs.getString(5) + ", " + rs.getString(6)
                    + " | Size=" + rs.getString(7) + " | " + rs.getString(8)
                    + " | Digits=" + rs.getString(9) + " | Radix=" + rs.getString(10)
                    + " | Null=" + rs.getString(11) + " | Rem=" + rs.getString(12)
                    + " | Def=" + rs.getString(13) + " | " + rs.getString(14)
                    + " | " + rs.getString(15) + " | " + rs.getString(16)
                    + " | Ord=" + rs.getString(17) + " | Null=" + rs.getString(18)
                    );

            con.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        **/
    }   //  main

    public Convert getConvert() {
        return m_convert;
    }

	public int getNextID(String Name) {
		int m_sequence_id = DB.getSQLValue(null, "SELECT "+Name.toUpperCase()+".nextval FROM DUAL");
		return m_sequence_id;
	}

	public boolean createSequence(String name , int increment , int minvalue , int maxvalue ,int  start , String trxName) 
	{
		int no = DB.executeUpdate("DROP SEQUENCE "+name.toUpperCase(), trxName);
		no = DB.executeUpdateEx("CREATE SEQUENCE "+name.toUpperCase()													
							+ " MINVALUE " + minvalue 
							+ " MAXVALUE " + maxvalue
							+ " START WITH " + start 
							+ " INCREMENT BY " + increment +" CACHE 20", trxName)
							;
		if(no == -1 )
			return false;
		else 
			return true;
	}

	public boolean isQueryTimeoutSupported() {
		return true;
	}
	
	public String addPagingSQL(String sql, int start, int end) {
		//not supported, too many corner case that doesn't work using rownum. to investigate later
		return sql;
	}

	public boolean isPagingSupported() {
		return false;
	}



    private int getIntProperty(Properties properties, String key, int defaultValue)
    {
        int i = defaultValue;
        try
        {
            String s = properties.getProperty(key);
            if (s != null && s.trim().length() > 0)
                i = Integer.parseInt(s);
        }
        catch (Exception e) {}
        return i;
    }

    private boolean getBooleanProperty(Properties properties, String key, boolean defaultValue)
    {
        boolean b = defaultValue;
        try
        {
            String s = properties.getProperty(key);
            if (s != null && s.trim().length() > 0)
                b = Boolean.valueOf(s);
        }
        catch (Exception e) {}
        return b;
    }

    private	String getStringProperty(Properties properties,	String key, String defaultValue)
    {
        String b = defaultValue;
        try
        {
            String s = properties.getProperty(key);
            if	(s != null && s.trim().length() > 0)
                b = s.trim();
        }
        catch(Exception e){}
        return b;
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
            sqlBuffer.append(" FOR UPDATE WAIT ").append((timeout > 0 ? timeout : LOCK_TIME_OUT));

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
                rs = null; stmt = null;
            }
        }
        return false;
    }

    @Override
    public String getNameOfUniqueConstraintError(Exception e) {
        String info = e.getMessage();
        int fromIndex = info.indexOf(".");
        if (fromIndex == -1)
            return info;
        int toIndex = info.indexOf(")", fromIndex + 1);
        if (toIndex == -1)
            return info;
        return info.substring(fromIndex + 1, toIndex);
    }

}   //  DB_Oracle
