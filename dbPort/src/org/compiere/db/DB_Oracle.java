/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.sql.*;
import oracle.jdbc.*;
import oracle.jdbc.pool.*;
import org.compiere.*;
import org.compiere.dbPort.Convert;
import org.compiere.dbPort.Convert_Oracle;
import org.compiere.util.*;

/**
 *  Oracle Database Port
 *
 *  @author     Jorg Janke
 *  @version    $Id: DB_Oracle.java,v 1.7 2006/09/22 23:35:19 jjanke Exp $
 */
public class DB_Oracle implements AdempiereDatabase, OracleConnectionCacheCallback
{
	/**
	 *  Oracle Database
	 */
	public DB_Oracle()
	{
		/**	Causes VPN problems ???
		try
		{
			getDriver();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, e.getMessage());
		}
		**/
	}   //  DB_Oracle

	/** Static Driver           	*/
	private static OracleDriver	   	s_driver = null;
	/** Driver Class Name			*/
	public static final String		DRIVER = "oracle.jdbc.OracleDriver";

	/** Default Port            	*/
	public static final int 		DEFAULT_PORT = 1521;
	/** Default Connection Manager Port */
	public static final int 		DEFAULT_CM_PORT = 1630;
	
	/** Connection String       	*/
	private String          		m_connectionURL;

	/** Statement Cache	(50)		*/
	private static final int		MAX_STATEMENTS = 50;
	/** Data Source					*/
	private OracleDataSource		m_ds = null;
	
    /** Use Connection Cache (false)*/
	private static final boolean	USE_CACHE = false;
	/** Connection Cache			*/
	private OracleConnectionCacheManager	m_cacheMgr = null;
	/** Connection Cache Name		*/
    private static final String 	CACHE_NAME = "AdempiereCCache";
    /** Cached User Name			*/
    private String					m_userName = null;
    
    private Convert m_convert = new Convert_Oracle();

    /**	Logger			*/
	private static CLogger			log	= CLogger.getCLogger (DB_Oracle.class);
    
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
	 *	@throws SQLException
	 */
	public Driver getDriver() throws SQLException
	{
		if (s_driver == null)
		{
			//	Speed up transfer rate
			System.setProperty("oracle.jdbc.TcpNoDelay", "true");
			//	Oracle Multi - Language
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
		//	sb.append(connection.getDbName());
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
				//	new: jdbc:oracle:thin:@//dev2:1521/serviceName
				sb.append("//")
					.append(connection.getDbHost())
					.append(":").append(connection.getDbPort())
					.append("/").append(connection.getDbName());
			}
		}
		m_connectionURL = sb.toString();
	//	log.config(m_connectionURL);
		//
		m_userName = connection.getDbUid();
		return m_connectionURL;
	}   //  getConnectionURL

	/**
	 * 	Get Connection URL.
	 * 	http://download-east.oracle.com/docs/cd/B14117_01/java.101/b10979/urls.htm#BEIDBFDF
	 *	@param dbHost db Host
	 *	@param dbPort db Port
	 *	@param dbName db Name
	 *	@param userName user name
	 *	@return connection
	 */
	public String getConnectionURL (String dbHost, int dbPort, String dbName, 
		String userName)
	{
		m_userName = userName;
		m_connectionURL = "jdbc:oracle:thin:@//" 
			+ dbHost + ":" + dbPort + "/" + dbName;
		return m_connectionURL;
	}	//	getConnectionURL
		
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
	}	//	getConnectionURL

	/**
	 * 	Get JDBC Catalog
	 *	@return null - not used
	 */
	public String getCatalog()
	{
		return null;
	}	//	getCatalog
	
	/**
	 * 	Get JDBC Schema
	 *	@return user name
	 */
	public String getSchema()
	{
		if (m_userName != null)
			return m_userName.toUpperCase();
		log.severe("User Name not set (yet) - call getConnectionURL first");
		return null;
	}	//	getSchema

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
			if (m_ds != null)
				sb.append("-").append(m_ds.getDataSourceName())
				//	.append(",ExplCache=").append(m_ds.getExplicitCachingEnabled())
					.append(",ImplCache=").append(m_ds.getImplicitCachingEnabled())
					.append(",MaxStmts=").append(m_ds.getMaxStatements());
				//	.append(",Ref=").append(m_ds.getReference());
			if (m_cacheMgr != null && m_cacheMgr.existsCache(CACHE_NAME))
				sb.append(";ConnectionActive=").append(m_cacheMgr.getNumberOfActiveConnections(CACHE_NAME))
					.append(",CacheAvailable=").append(m_cacheMgr.getNumberOfAvailableConnections(CACHE_NAME));
		}
		catch (Exception e)
		{
			sb.append("=").append(e.getLocalizedMessage());
		}
		sb.append("]");
		return sb.toString();
	}   //  toString

	/**
	 * 	Get Status
	 * 	@return status info
	 */
	public String getStatus()
	{
		StringBuffer sb = new StringBuffer();
		try
		{
			if (m_cacheMgr != null && m_cacheMgr.existsCache(CACHE_NAME))
				sb.append("-Connections=").append(m_cacheMgr.getNumberOfActiveConnections(CACHE_NAME))
					.append(",Cache=").append(m_cacheMgr.getNumberOfAvailableConnections(CACHE_NAME));
		}
		catch (Exception e)
		{}
		return sb.toString();
	}	//	getStatus

	
	/**************************************************************************
	 *  Convert an individual Oracle Style statements to target database statement syntax.
	 *  @param oraStatement oracle statement
	 *  @return converted Statement oracle statement
	 */
	public String convertStatement (String oraStatement)
	{
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
	 *  		String[1] - String[n] = Constraint Name
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
	}	//	getSystemUser
	
	/**
	 *  Get Name of System Database
	 *  @param databaseName database Name
	 *  @return e.g. master or database Name
	 */
	public String getSystemDatabase(String databaseName)
	{
		return databaseName;
	}	//	getSystemDatabase


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
			dateString.append(myDate.substring(0, myDate.indexOf(".")));	//	cut off miliseconds
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
	 *  @return TRIM(TO_CHAR(columnName,'9G999G990D00','NLS_NUMERIC_CHARACTERS='',.'''))
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
				retValue.append(",'9G999G990D00'");
			else
				retValue.append(",'TM9'");
			//  TO_CHAR(GrandTotal,'9G999G990D00','NLS_NUMERIC_CHARACTERS='',.''')
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
	 * 	Return number as string for INSERT statements with correct precision
	 *	@param number number
	 *	@param displayType display Type
	 *	@return number as string
	 */
	public String TO_NUMBER (BigDecimal number, int displayType)
	{
		if (number == null)
			return "NULL";
		return number.toString();
	}	//	TO_NUMBER

	
	/**
	 * 	Get SQL Commands.
	 * 	The following variables are resolved:
	 * 	@SystemPassword@, @AdempiereUser@, @AdempierePassword@
	 * 	@SystemPassword@, @DatabaseName@, @DatabaseDevice@
	 *	@param cmdType CMD_*
	 *	@return array of commands to be executed
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
	}	//	getCommands

	/**
	 * 	Create DataSource
	 *	@param connection connection
	 *	@return data dource
	 */
	public DataSource getDataSource(CConnection connection)
	{
		if (m_ds != null)
			return m_ds;
		try
		{
			m_ds = new OracleDataSource();
			m_ds.setDriverType("thin");
			m_ds.setNetworkProtocol("tcp");
			m_ds.setServerName(connection.getDbHost());
			m_ds.setServiceName(connection.getDbName());
			m_ds.setPortNumber(connection.getDbPort());
			m_ds.setUser(connection.getDbUid());
			m_ds.setPassword(connection.getDbPwd());
			//
			m_ds.setDataSourceName("AdempiereDS");
			m_ds.setDescription("Adempiere Oracle Data Source");
			m_ds.setImplicitCachingEnabled(true);
			m_ds.setExplicitCachingEnabled(true);
			m_ds.setMaxStatements(MAX_STATEMENTS);
			//	http://download-east.oracle.com/docs/cd/B14117_01/java.101/b10979/oralob.htm#sthref1258
			Properties connProperties = new Properties(); 
			connProperties.setProperty("SetBigStringUseClob", "true");
			m_ds.setConnectionProperties(connProperties);
			//
			Properties cacheProperties = new Properties();
		//	cacheProperties.setProperty("InitialLimit", "3"); // at startup
		//	cacheProperties.setProperty("MaxStatementsLimit", "10");
			cacheProperties.setProperty("ClosestConnectionMatch", "true");
			cacheProperties.setProperty("ValidateConnection", "true");
			if (Ini.isClient())
			{
				cacheProperties.setProperty("MinLimit", "0");
			//	cacheProperties.setProperty("MaxLimit", "5");
				cacheProperties.setProperty("InactivityTimeout", "300");    //  5 Min
				cacheProperties.setProperty("AbandonedConnectionTimeout", "300");  //  5 Min
			}
			else	//	Server Settings
			{
				cacheProperties.setProperty("MinLimit", "3");
			//	cacheProperties.setProperty("MaxLimit", "5");
				cacheProperties.setProperty("InactivityTimeout", "600");    //  10 Min
				cacheProperties.setProperty("AbandonedConnectionTimeout", "600");  //  10 Min
			}
			cacheProperties.setProperty("PropertyCheckInterval", "120"); // 2 Min
			//
			if (USE_CACHE)
			{
				m_ds.setConnectionCachingEnabled(true);
				m_ds.setConnectionCacheName(CACHE_NAME);
				m_ds.setFastConnectionFailoverEnabled(true);
			}
			//
			if (m_cacheMgr == null && USE_CACHE)
			{
				m_cacheMgr = OracleConnectionCacheManager.getConnectionCacheManagerInstance();
				if (!m_cacheMgr.existsCache(CACHE_NAME))
					m_cacheMgr.createCache(CACHE_NAME, m_ds, cacheProperties);
			}
			//	test
//			OracleConnection con = m_ds.getConnection();
//			con.close();
			//
			log.config(toString());
			//
			return m_ds;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, toString(), e);
		}
		return null;	
	}	//	getDataSource


	/**
	 * 	Get Cached Connection
	 *	@param connection info
	 *  @param autoCommit true if autocommit connection
	 *  @param transactionIsolation Connection transaction level
	 *	@return connection or null
	 *	@throws Exception
	 */
	public Connection getCachedConnection (CConnection connection, 
		boolean autoCommit, int transactionIsolation)
		throws Exception
	{
		OracleConnection conn = null;
		Exception exception = null;
		try
		{
			if (USE_CACHE && m_cacheMgr == null)
				getDataSource(connection);
			if (m_ds == null)
				getDataSource(connection);
			
		//	Properties connAttr = new Properties();
		//	connAttr.setProperty("TRANSACTION_ISOLATION", CConnection.getTransactionIsolationInfo(transactionIsolation));
		//	OracleConnection conn = (OracleConnection)m_ds.getConnection(connAttr);
			//
			//	Try 5 times max
			for (int i = 0; i < 5; i++)
			{
				try
				{
					conn = (OracleConnection)m_ds.getConnection();
					if (conn != null)
					{
						if (conn.getTransactionIsolation() != transactionIsolation)
							conn.setTransactionIsolation(transactionIsolation);
						if (conn.getAutoCommit() != autoCommit)
							conn.setAutoCommit(autoCommit);
						conn.setDefaultRowPrefetch(20);		//	10 default - reduces round trips
					}
				}
				catch (Exception e)
				{
					exception = e;
					conn = null;
					if (e instanceof SQLException
						&& ((SQLException)e).getErrorCode() == 1017)	//	invalid username/password
					{
						log.severe("Cannot connect to database: " 
							+ getConnectionURL(connection)
							+ " - UserID=" + connection.getDbUid());
						break;
					}
				}
				try
				{
					if (conn != null && conn.isClosed())
						conn = null;
					//	OK
					if (conn != null && !conn.isClosed())
						break;
					if (i == 0)
						Thread.yield();		//	give some time
					else
						Thread.sleep(100);
				}
				catch (Exception e)
				{
					exception = e;
					conn = null;
				}
			}	//	5 tries
			
			if (conn == null && exception != null)
			{
				log.log(Level.SEVERE, exception.toString());
				log.fine(toString());
				log.finest("Reference=" + m_ds.getReference());
			}
		//	else
		//	{
			//	System.out.println(conn + " " + getStatus());
			//	conn.registerConnectionCacheCallback(this, "test", OracleConnection.ALL_CONNECTION_CALLBACKS);
		//	}
		}
		catch (Exception e)
		{
		//	System.err.println ("DB_Oracle.getCachedConnection");
		//	if (!(e instanceof SQLException))
		//		e.printStackTrace();
			exception = e;
		}	
		if (exception != null)
			throw exception;
		return conn;
	}	//	getCachedConnection

	/**
	 * 	Get Connection from Driver
	 *	@param connection info
	 *	@return connection or null
	 *	@throws SQLException
	 */
	public Connection getDriverConnection (CConnection connection) throws SQLException
	{
		getDriver();
		return DriverManager.getConnection (getConnectionURL (connection), 
			connection.getDbUid(), connection.getDbPwd());
	}	//	getDriverConnection

	/**
	 * 	Get Driver Connection
	 *	@param dbUrl URL
	 *	@param dbUid user
	 *	@param dbPwd password
	 *	@return connection
	 *	@throws SQLException
	 */
	public Connection getDriverConnection (String dbUrl, String dbUid, String dbPwd) 
		throws SQLException
	{
		getDriver();
		return DriverManager.getConnection (dbUrl, dbUid, dbPwd);
	}	//	getDriverConnection

	/**
	 * 	Close
	 */
	public void close()
	{
		log.config(toString());
		if (m_ds != null)
		{
			try
			{
				m_ds.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if (m_cacheMgr != null)
		{
			try
			{
				if (m_cacheMgr.existsCache(CACHE_NAME))
					m_cacheMgr.purgeCache(CACHE_NAME, false);	// not active
			//	m_cache.disableCache(CACHE_NAME);
			//	m_cache.removeCache(CACHE_NAME, 0);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		m_cacheMgr = null;
		m_ds = null;
	}	//	close

	/**
	 * 	Clean up
	 */
	public void cleanup()
	{
		if (!USE_CACHE)
			return;
		
		log.config("");
		try
		{
			if (m_cacheMgr == null)
				m_cacheMgr = OracleConnectionCacheManager.getConnectionCacheManagerInstance();
			String[] cacheNames = m_cacheMgr.getCacheNameList();
			for (int i = 0; i < cacheNames.length; i++)
			{
				String name = cacheNames[i];
				System.out.println("  cleanup: " + name);
				System.out.println("    Before = Active=" + m_cacheMgr.getNumberOfActiveConnections(name)
					+ ", Available=" + m_cacheMgr.getNumberOfAvailableConnections(name));
				m_cacheMgr.purgeCache(name, false);
				System.out.println("    Cached = Active=" + m_cacheMgr.getNumberOfActiveConnections(name)
					+ ", Available=" + m_cacheMgr.getNumberOfAvailableConnections(name));
				m_cacheMgr.purgeCache(name, true);
				System.out.println("    All    = Active=" + m_cacheMgr.getNumberOfActiveConnections(name)
					+ ", Available=" + m_cacheMgr.getNumberOfAvailableConnections(name));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}	//	cleanup

	/**************************************************************************
	 * 	Handle Abandoned Connection
	 *	@param conn connection
	 *	@param userObject 
	 *	@return true if close - false for keeping it
	 */
	public boolean handleAbandonedConnection (OracleConnection conn, Object userObject)
	{
		System.out.println("--------------------handleAbandonedConnection " + conn + " - " + userObject);
		return true;	//	reclaim it
	}	//	handleAbandonedConnection

	/**
	 * 	Release Connection
	 *	@param conn connection
	 *	@param userObject 
	 */
	public void releaseConnection (OracleConnection conn, Object userObject)
	{
		System.out.println("----------------------releaseConnection " + conn + " - " + userObject);
	}	//	releaseConnection

	
	/**
	 * 	Get Data Type
	 *	@param displayType display type
	 *	@param precision precision
	 *	@param defaultValue if true adds default value
	 *	@return data type
	 */
	public String getDataType (int displayType, int precision,
		boolean defaultValue)
	{
		String retValue = null;
		switch (displayType)
		{
			//	IDs
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
				
			//	Date	
			case DisplayType.Date:
			case DisplayType.DateTime:
			case DisplayType.Time:
				retValue = "DATE";
				if (defaultValue)
					retValue += " DEFAULT SYSDATE";
				break;
				
			// 	Number(10)
			case DisplayType.Integer:
				retValue = "NUMBER(10)";
				break;
				
			case DisplayType.List:
				retValue = "CHAR(" + precision + ")";
				break;

			//	NVARCHAR
			case DisplayType.Memo:
			case DisplayType.String:
			case DisplayType.Text:
				retValue = "NVARCHAR(" + precision + ")";
				break;

			case DisplayType.TextLong:
				retValue = "CLOB";
				break;

			//	Dyn Prec
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
		return retValue;
	}	//	getDataType


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
	 * 	Testing
	 * 	@param args ignored
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
		//	System.out.println("Driver=" + db.getDriverConnection(cc));
			DataSource ds = db.getDataSource(cc);
			System.out.println("DS=" + ds.getConnection());
			conn = db.getCachedConnection(cc, true, Connection.TRANSACTION_READ_COMMITTED);
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
			Connection conn2 = db.getCachedConnection(cc, true, Connection.TRANSACTION_READ_COMMITTED);
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
			Connection conn1 = db.getCachedConnection(cc, false, Connection.TRANSACTION_READ_COMMITTED);
			Connection conn2 = db.getCachedConnection(cc, true, Connection.TRANSACTION_READ_COMMITTED);
			Connection conn3 = db.getCachedConnection(cc, false, Connection.TRANSACTION_READ_COMMITTED);
			System.out.println("3 -> " + db);
			conn1.close();
			conn2.close();
			conn1 = db.getCachedConnection(cc, true, Connection.TRANSACTION_READ_COMMITTED);
			conn2 = db.getCachedConnection(cc, true, Connection.TRANSACTION_READ_COMMITTED);
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
		
	//	System.exit(0);
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
		//	Connection option 1
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
	}	//	main

	public Convert getConvert() {
		return m_convert;
	}
        
	
}   //  DB_Oracle
