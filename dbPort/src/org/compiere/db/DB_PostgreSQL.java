/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Portions created by Victor Perez are Copyright (C) 1999-2005 e-Evolution,S.C
 * Contributor(s): Victor Perez
 *****************************************************************************/
package org.compiere.db;

import java.math.*;
import java.sql.*;
import java.util.logging.Level;

import javax.sql.*;
import org.compiere.dbPort.*;
import org.compiere.util.*;

/**
 *  PostgreSQL Database Port
 *
 *  @author      @author     Jorg Janke, Victor P�rez 
 *  @version    $Id: DB_PostgreSQL.java,v 1.23 2005/03/11 20:29:01 jjanke Exp $
 */
public class DB_PostgreSQL implements AdempiereDatabase
{
	/**
	 *  PostgreSQL Database
	 */
	public DB_PostgreSQL()
	{
	}   //  DB_PostgreSQL

	/** Driver                  */
	private org.postgresql.Driver   s_driver = null;

	/** Default Port            */
	public static final int         DEFAULT_PORT = 5432;
	
	/** Data Source				*/
	private org.postgresql.ds.PGPoolingDataSource m_ds = null;

	/** Statement Converter     */
	private Convert         m_convert = new Convert(Database.DB_POSTGRESQL);
	/** Connection String       */
	private String          m_connection;
	/** Cached Database Name	*/
	private String			m_dbName = null;
        
        private String				m_userName = null;
        	/** Connection String       	*/
	private String          		m_connectionURL;
            
	/**	Logger			*/
	private static CLogger			log	= CLogger.getCLogger (DB_PostgreSQL.class);

	/**
	 *  Get Database Name
	 *  @return database short name
	 */
	public String getName()
	{
		return Database.DB_POSTGRESQL;
	}   //  getName

	/**
	 *  Get Database Description
	 *  @return database long name and version
	 */
	public String getDescription()
	{       //begin vpj-cd e-evolution 30.09.2005
		//return s_driver.toString();
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
                //end vpj-cd e-evolution 30.09.2005
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
	 */
	public java.sql.Driver getDriver() throws SQLException
	{
		if (s_driver == null)
		{
			s_driver = new org.postgresql.Driver();
			DriverManager.registerDriver (s_driver);
			DriverManager.setLoginTimeout (Database.CONNECTION_TIMEOUT);
		}
		return s_driver;
	}   //  getDriver

	/**
	 *  Get Database Connection String.
	 *  Requirements:
	 *      - createdb -E UNICODE compiere
	 *  @param connection Connection Descriptor
	 *  @return connection String
	 */
	public String getConnectionURL (CConnection connection)
	{
		//  jdbc:postgresql://hostname:portnumber/databasename?encoding=UNICODE
		StringBuffer sb = new StringBuffer("jdbc:postgresql:");
		sb.append("//").append(connection.getDbHost())
			.append(":").append(connection.getDbPort())
			.append("/").append(connection.getDbName())
			.append("?encoding=UNICODE");
		m_connection = sb.toString();
		return m_connection;
	}   //  getConnectionString

	/**
	 * 	Get Connection URL
	 *	@param dbHost db Host
	 *	@param dbPort db Port
	 *	@param dbName sb Name
	 *	@param userName user name
	 *	@return connection url
	 */
	public String getConnectionURL (String dbHost, int dbPort, String dbName,
		String userName)
	{
		return "jdbc:postgresql://" 
			+ dbHost + ":" + dbPort + "/" + dbName;
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
	 *	@return catalog (database name)
	 */
	public String getCatalog()
	{
		if (m_dbName != null)
			return m_dbName;
	//	log.severe("Database Name not set (yet) - call getConnectionURL first");
		return null;
	}	//	getCatalog
	
	/**
	 * 	Get JDBC Schema
	 *	@return schema (dbo)
	 */
	public String getSchema()
	{
		//begin vpj-cd e-evolution 03/04/2005
		return "adempiere";
		//end vpj-cd e-evolution 03/04/2005
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
		StringBuffer sb = new StringBuffer("DB_PostgreSQL[");
		sb.append(m_connection)
			.append("]");
		return sb.toString();
	}   //  toString

	/**
	 * 	Get Status
	 * 	@return status info
	 */
	public String getStatus()
	{
		return "";
	}	//	getStatus

	/*************************************************************************
	 *  Convert an individual Oracle Style statements to target database statement syntax
	 *
	 *  @param oraStatement
	 *  @return converted Statement
	 *  @throws Exception
	 */
	public String convertStatement (String oraStatement)
	{
		String retValue[] = m_convert.convert(oraStatement);
		
        //begin vpj-cd e-evolution 03/14/2005
		if (retValue.length == 0 )
			return  oraStatement;
        //end vpj-cd e-evolution 03/14/2005
		
		if (retValue == null)
        //begin vpj-cd 24/06/2005 e-evolution	
		{	
			log.log(Level.SEVERE,("DB_PostgreSQL.convertStatement - Not Converted (" + oraStatement + ") - "
					+ m_convert.getConversionError()));
			throw new IllegalArgumentException
				("DB_PostgreSQL.convertStatement - Not Converted (" + oraStatement + ") - "
					+ m_convert.getConversionError());
		}
		//		end vpj-cd 24/06/2005 e-evolution
		if (retValue.length != 1)
			//begin vpj-cd 24/06/2005 e-evolution
			{
			log.log(Level.SEVERE, ("DB_PostgreSQL.convertStatement - Convert Command Number=" + retValue.length
				+ " (" + oraStatement + ") - " + m_convert.getConversionError()));
			throw new IllegalArgumentException
				("DB_PostgreSQL.convertStatement - Convert Command Number=" + retValue.length
					+ " (" + oraStatement + ") - " + m_convert.getConversionError());
			}
			//end vpj-cd 24/06/2005 e-evolution
		//  Diagnostics (show changed, but not if AD_Error
		if (!oraStatement.equals(retValue[0]) && retValue[0].indexOf("AD_Error") == -1)
			//begin vpj-cd 24/06/2005 e-evolution
			//System.out.println("PostgreSQL =>" + retValue[0] + "<= <" + oraStatement + ">");
			log.log(Level.INFO, "PostgreSQL =>" + retValue[0] + "<= <" + oraStatement + ">");
		    //end vpj-cd 24/06/2005 e-evolution
		//
		return retValue[0];
	}   //  convertStatement

	
	/**
	 *  Get Name of System User
	 *  @return e.g. sa, system
	 */
	public String getSystemUser()
	{
		return "postgres";
	}	//	getSystemUser

	/**
	 *  Get Name of System Database
	 *  @param databaseName database Name
	 *  @return e.g. master or database Name
	 */
	public String getSystemDatabase(String databaseName)
	{
		return "template1";
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
				return "current_date()";
			return "current_date()";
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
	 **/
	public String TO_CHAR (String columnName, int displayType, String AD_Language)
	{
		StringBuffer retValue = new StringBuffer("CAST (");
		retValue.append(columnName);
		retValue.append(" AS Text)");

		//  Numbers
		/*
		if (DisplayType.isNumeric(displayType))
		{
			if (displayType == DisplayType.Amount)
				retValue.append(" AS TEXT");
			else
				retValue.append(" AS TEXT");			
			//if (!Language.isDecimalPoint(AD_Language))      //  reversed
			//retValue.append(",'NLS_NUMERIC_CHARACTERS='',.'''");
		}
		else if (DisplayType.isDate(displayType))
		{
			retValue.append(",'")
				.append(Language.getLanguage(AD_Language).getDBdatePattern())
				.append("'");
		}
		retValue.append(")");
		//*/
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
		BigDecimal result = number;
		int scale = DisplayType.getDefaultPrecision(displayType);
		if (scale > number.scale())
		{
			try
			{
				result = number.setScale(scale, BigDecimal.ROUND_HALF_UP);
			}
			catch (Exception e)
			{
			//	log.severe("Number=" + number + ", Scale=" + " - " + e.getMessage());
			}
		}
		return result.toString();
	}	//	TO_NUMBER

	
	/**
	 * 	Get SQL Commands
	 *	@param cmdType CMD_*
	 *	@return array of commands to be executed
	 */
	public String[] getCommands (int cmdType)
	{
		if (CMD_CREATE_USER == cmdType)
			return new String[]
			{
			"CREATE USER compiere;",			
			};
		//
		if (CMD_CREATE_DATABASE == cmdType)
			return new String[]
			{
		    "CREATE DATABASE compiere OWNER compiere;",
			"GRANT ALL PRIVILEGES ON compiere TO compiere;"	,
			"CREATE SCHEMA compiere;",
			"SET search_path TO compiere;"
			};
		//
		if (CMD_DROP_DATABASE == cmdType)
			return new String[]
			{
			"DROP DATABASE compiere;"
			};
		//
		return null;
	}	//	getCommands

	
	/**************************************************************************
	 *  Get RowSet
	 * 	@param rs ResultSet
	 *  @return RowSet
	 *  @throws SQLException
	 */
	public RowSet getRowSet (java.sql.ResultSet rs) throws SQLException
	{
		throw new UnsupportedOperationException("PostgreSQL does not support RowSets");
	}	//	getRowSet
	
	
	/**
	 * 	Get Cached Connection
	 *	@param connection connection
	 *	@param autoCommit auto commit
	 *	@param transactionIsolation trx isolation
	 *	@return Connection
	 *	@throws Exception
	 */
	public Connection getCachedConnection (CConnection connection,
		boolean autoCommit, int transactionIsolation)
		throws Exception
	{
		if (m_ds == null)
			getDataSource(connection);
		//
		Connection conn = m_ds.getConnection();
	//	Connection conn = getDriverConnection(connection);
		//
		conn.setAutoCommit(autoCommit);
		conn.setTransactionIsolation(transactionIsolation);
		return conn;
	}	//	getCachedConnection
	

	/**
	 * 	Create DataSource (Client)
	 *	@param connection connection
	 *	@return data dource
	 */
	public DataSource getDataSource(CConnection connection)
	{
		//throw new UnsupportedOperationException("Not supported/implemented");
		if (m_ds != null)
			return m_ds;
		
		//org.postgresql.ds.PGPoolingDataSource ds = new org.postgresql.ds.PGPoolingDataSource();
		org.postgresql.jdbc3.Jdbc3PoolingDataSource ds = new org.postgresql.jdbc3.Jdbc3PoolingDataSource();
		ds.setDataSourceName("CompiereDS");
		ds.setServerName(connection.getDbHost());
		ds.setDatabaseName(connection.getDbName());
		ds.setUser(connection.getDbUid());
		ds.setPassword(connection.getDbPwd());
		ds.setPortNumber(connection.getDbPort());
		ds.setMaxConnections(50);
		ds.setInitialConnections(20);
		
		//new InitialContext().rebind("DataSource", source);		
		m_ds = ds;
		
		return m_ds;
	}

	/**
	 * 	Create Pooled DataSource (Server)
	 *	@param connection connection
	 *	@return data dource
	 */
	public ConnectionPoolDataSource createPoolDataSource(CConnection connection)
	{
		throw new UnsupportedOperationException("Not supported/implemented");
	}
	
	/**
	 * 	Get Connection from Driver
	 *	@param connection info
	 *	@return connection or null
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
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}		
		m_ds = null;
	}	//	close
        
        
	/**
	 *  Check and generate an alternative SQL
	 *  @reExNo number of re-execution
	 *  @msg previous execution error message
	 *  @sql previous executed SQL
	 *  @return String, the alternative SQL, null if no alternative
	 */
	public String getAlternativeSQL(int reExNo, String msg, String sql)
	{
		return null; //do not do re-execution of alternative SQL
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
	 * 	Test
	 *	@param args ignored
	 */
	public static void main(String[] args)
	{
		DB_PostgreSQL postgresql = new DB_PostgreSQL();
		//
		String databaseName = "adempiere";
		String uid = "adempiere";
		String pwd = "adempiere";
		String jdbcURL = postgresql.getConnectionURL("vpj", DEFAULT_PORT, databaseName, uid);
		System.out.println(jdbcURL);
		try
		{
			postgresql.getDriver();
			Connection conn = DriverManager.getConnection (jdbcURL, uid, pwd);
			
			//CachedRowSetImpl crs = null;
			//crs = new CachedRowSetImpl();
			//crs.setSyncProvider("com.sun.rowset.providers.RIOptimisticProvider");
			//crs.setConcurrency(ResultSet.CONCUR_READ_ONLY);
			//crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
			//crs.setCommand("SELECT * FROM AD_Client");
			//
			//crs.execute(conn);
			//
			conn.close();
			conn = null;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}	//	main

}   //  DB_PostgreSQL
