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
import java.util.logging.*;
import javax.sql.*;

import org.compiere.dbPort.Convert;
import org.compiere.util.*;

import com.ibm.db2.jcc.*;

/**
 * 	DB2 Database Driver
 *	
 *  @author Jorg Janke
 *  @version $Id: DB_DB2.java,v 1.5 2006/09/22 23:35:19 jjanke Exp $
 */
public class DB_DB2
	implements AdempiereDatabase
{
	/**
	 * 	Database DB2
	 */
	public DB_DB2()
	{
		
	}	//	DB_DB2
	
	/** Static Driver           	*/
	private static DB2Driver	   	s_driver = null;
	/** Driver Class Name			*/
	public static final String		DRIVER = "com.ibm.db2.jcc.DB2Driver";
	/** Type 2 Driver				*/
	public static final String		DRIVER2 = "COM.ibm.db2.jdbc.app.DB2Driver";
	
	/** Default Port 446           	*/
	public static final int 		DEFAULT_PORT = 446;
	/** Default Port 50000         	*/
	public static final int 		DEFAULT_PORT_0 = 50000;
	
    /** Cached User Name			*/
    private String					m_userName = null;
	
	/** Connection String       	*/
	private String          		m_connectionURL;
	private DB2SimpleDataSource		m_ds = null;

	/**	Logger	*/
	private static CLogger log = CLogger.getCLogger (DB_DB2.class);
	
	/**
	 *  Get Database Name
	 *  @return database short name
	 */
	public String getName()
	{
		return Database.DB_DB2;
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
		return DEFAULT_PORT_0;
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
			s_driver = new DB2Driver();
			DriverManager.registerDriver (s_driver);
			DriverManager.setLoginTimeout (Database.CONNECTION_TIMEOUT);
		}
		return s_driver;
	}   //  getDriver

	/**
	 *  Get Database Connection String.
	 *  <pre>
	 *  Timing:
	 *  </pre>
	 *  @param connection Connection Descriptor
	 *  @return connection String
	 */
	public String getConnectionURL (CConnection connection)
	{
		StringBuffer sb = null;
		//	connection//server:port/database
		sb = new StringBuffer ("jdbc:db2:");
		//	Cloudscape = jdbc:db2j:net: 
		sb.append("//")
			.append(connection.getDbHost())
			.append(":").append(connection.getDbPort())
			.append("/").append(connection.getDbName());
		m_connectionURL = sb.toString();
	//	log.config(m_connectionURL);
		//
		m_userName = connection.getDbUid();
		return m_connectionURL;
	}   //  getConnectionURL

	/**
	 * 	Get Connection URL.
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
		m_connectionURL = "jdbc:db2://" 
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
		StringBuffer sb = new StringBuffer("DB_DB2[");
		sb.append(m_connectionURL);
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

		return "0";
		//jz temp, modify later
	}

	/**
	 *  Get Name of System User
	 *  @return system
	 */
	public String getSystemUser()
	{
		return "db2adm";
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
	 *  @return TO_DATE('1999-12-31 23:59:59', 'YYYY-MM-DD HH24:MI:SS')
	 *  or TIMESTAMP('2000-01-10-00.00.00.000000')
	 */
	public String TO_DATE (Timestamp time, boolean dayOnly)
	{
		
		if (time == null)
		{
			if (dayOnly)
				return "trunc(CURRENT TIMESTAMP)";
			return "CURRENT TIMESTAMP";
		}

		//	TIMESTAMP('2000-01-10-00.00.00.000000')
		StringBuffer dateString = new StringBuffer("TIMESTAMP('");
		//  YYYY-MM-DD HH24:MI:SS.mmmm  JDBC Timestamp format
		String myDate = time.toString();
		if (dayOnly)
		{
			dateString.append(myDate.substring(0,10));
			dateString.append("-00.00.00.000000')");
		}
		else
		{
			myDate = myDate.replace('-', ' ');
			myDate = myDate.replace(':', '.');
			dateString.append(myDate);
			dateString.append("00')");
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
		return columnName;
		/**
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
		**/
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
		if (m_ds == null)
		{
			m_ds = new DB2SimpleDataSource();
			m_ds.setServerName(connection.getDbHost());
			m_ds.setPortNumber(connection.getDbPort());
			m_ds.setDatabaseName(connection.getDbName()); 
			m_ds.setDescription("Adempiere DataSource");
			m_ds.setUser(connection.getDbUid());
			m_ds.setPassword(connection.getDbPwd());
			m_ds.setLoginTimeout(5);	//	seconds
		//	m_ds.setUseCachedCursor(true);
		}
		return m_ds;
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
		Connection conn = getDataSource(connection).getConnection();
		conn.setAutoCommit(autoCommit);
		conn.setTransactionIsolation(transactionIsolation);
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
		m_ds = null;
	}	//	close

	/**
	 * 	Clean up
	 */
	public void cleanup()
	{
		log.config("");
	}	//	cleanup

	
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
				retValue = "INTEGER";
				break;
				
			// Dynamic Precision
			case DisplayType.Amount:
				retValue = "DECIMAL(18,2)";
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
				retValue = "DECIMAL(22,6)";
				if (defaultValue)
					retValue += " DEFAULT 0";
				break;
				
			//	Date	
			case DisplayType.Date:
			case DisplayType.DateTime:
			case DisplayType.Time:
				retValue = "Timestamp";
				if (defaultValue)
					retValue += " DEFAULT 0";
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
		return null; //do not do re-execution of alternative SQL
	}
	
	
	/**************************************************************************
	 * 	Testing
	 * 	@param args ignored
	 */
	public static void main (String[] args)
	{
		/**
		Adempiere.startupEnvironment(true);
		CConnection cc = CConnection.get();
		DB_Oracle db = (DB_Oracle)cc.getDatabase();
		db.cleanup();
		
		try
		{
			Connection conn = ;
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
		/** **/
		
		
		/**	**/
		//	Connection option 1
		try
		{
			DB2Driver driver = new DB2Driver();
			DriverManager.registerDriver(driver);
			
			Connection con = DriverManager.getConnection("jdbc:db2://dev1:50000/sample",
				"db2admin", "db2admin");
//				"adempiere", "adempiere");
//				"db2inst1", "daDm7rfr");
			System.out.println("Connection Catalog = " + con.getCatalog());
			//
			DatabaseMetaData md = con.getMetaData();
			System.out.println(md.getDatabaseProductName() + " - " + md.getDatabaseProductVersion());
		//	System.out.println(md.getDatabaseMajorVersion() + " - " + md.getDatabaseMinorVersion());
			System.out.println(md.getDriverName() + " - " + md.getDriverVersion());
		//	System.out.println(md.getDriverMajorVersion() + " - " + md.getDriverMinorVersion());
			System.out.println("URL=" + md.getURL());
			System.out.println("User=" + md.getUserName());
			//
			System.out.println(md.getNumericFunctions());
			System.out.println(md.getStringFunctions());
			System.out.println(md.getTimeDateFunctions());
			System.out.println(md.getSystemFunctions());
			//
			System.out.println("Catalogs - " + md.getCatalogTerm());
			ResultSet rs = md.getCatalogs();
			while (rs.next())
				System.out.println("- " + rs.getString(1));
			//
			System.out.println("Schemas - " + md.getSchemaTerm());
			rs = md.getSchemas();
			while (rs.next())
				System.out.println("- " + rs.getString(1));

			
			String sql = "SELECT GRANTOR,GRANTEE,DBADMAUTH FROM SYSCAT.DBAUTH";
			PreparedStatement pstmt = null;
			try
			{
				pstmt = con.prepareStatement (sql);
				rs = pstmt.executeQuery ();
				while (rs.next ())
				{
					String GRANTOR = rs.getString(1);
					String GRANTEE = rs.getString(2);
					String DBADMAUTH = rs.getString(3);
					System.out.println(GRANTOR + " -> " + GRANTEE + " = " + DBADMAUTH);
				}
				rs.close ();
				pstmt.close ();
				pstmt = null;
			}
			catch (Exception e)
			{
				log.log (Level.SEVERE, sql, e);
			}
			try
			{
				if (pstmt != null)
					pstmt.close ();
				pstmt = null;
			}
			catch (Exception e)
			{
				pstmt = null;
			}
			
			
			System.out.println("SysCat Table");
			rs = md.getTables(null, "SYSCAT", null, new String[] {"TABLE", "VIEW"});
			while (rs.next())
				System.out.println("- User=" + rs.getString(2) + " | Table=" + rs.getString(3)
					+ " | Type=" + rs.getString(4) + " | " + rs.getString(5));
			//
			System.out.println("Column");
			rs = md.getColumns(null, "SYSCAT", "DBAUTH", null);
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
		/** **/
	}	//	main

	public Convert getConvert() {
		throw new UnsupportedOperationException("Not implemented.");
	}
	
}	//	DB_DB2
