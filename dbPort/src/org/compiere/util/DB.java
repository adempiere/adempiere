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
package org.compiere.util;

import java.io.*;
import java.math.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import javax.sql.*;
import javax.swing.*;
import oracle.jdbc.*;
//
import org.compiere.*;
import org.compiere.db.*;
import org.compiere.interfaces.*;
import org.compiere.model.*;
import org.compiere.process.*;


/**
 *  General Database Interface
 *
 *  @author     Jorg Janke
 *  @version    $Id: DB.java,v 1.8 2006/10/09 00:22:29 jjanke Exp $
 */
public final class DB
{
	/** Connection Descriptor           */
	private static CConnection      s_cc = null;
	/** Connection Cache r/o            */
	private static Connection[]		s_connections = null;
	/** Connection Cache Size           */
	private static int              s_conCacheSize = Ini.isClient() ? 1 : 3;
	/** Connection counter              */
	private static int              s_conCount = 0;
	/** Connection r/w                  */
	private static Connection		s_connectionRW = null;
	/** Connection r/w for ID           */
	private static Connection		s_connectionID = null;
	/**	Logger							*/
	private static CLogger			log = CLogger.getCLogger (DB.class);

	/** SQL Statement Separator "; "	*/
	public static final String SQLSTATEMENT_SEPARATOR = "; ";
	
	
	/**************************************************************************
	 * 	Check need for post Upgrade
	 * 	@param ctx context
	 *	@return true if post upgrade ran - false if there was no need
	 */
	public static boolean afterMigration (Properties ctx)
	{
		//	UPDATE AD_System SET IsJustMigrated='Y'
		MSystem system = MSystem.get(ctx); 
		if (!system.isJustMigrated())
			return false;
		
		//	Role update
		log.info("Role");
		String sql = "SELECT * FROM AD_Role";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MRole role = new MRole (ctx, rs, null);
				role.updateAccessRecords();
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "(1)", e);
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
		
		//	Release Specif stuff & Print Format
		try
		{
			Class clazz = Class.forName("org.compiere.MigrateData");
			clazz.newInstance();
		}
		catch (Exception e)
		{
			log.log (Level.SEVERE, "Data", e);
		}

		//	Language check
		log.info("Language");
		MLanguage.maintain(ctx);
		
		//	Sequence check
		log.info("Sequence");
		SequenceCheck.validate(ctx);
		
		//	Costing Setup
		log.info("Costing");
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(ctx, 0);
		for (int i = 0; i < ass.length; i++)
		{
			ass[i].checkCosting();
			ass[i].save();
		}
		
		//	Reset Flag
		system.setIsJustMigrated(false);
		return system.save();
	}	//	afterMigration
	
	/**
	 * 	Update Mail Settings for System Client and System User
	 */
	public static void updateMail()
	{
		//	Get Property File
		String envName = Ini.getAdempiereHome();
		if (envName == null)
			return;
		envName += File.separator + "AdempiereEnv.properties";
		File envFile = new File(envName);
		if (!envFile.exists())
			return;
		
		Properties env = new Properties();
		try
		{
			FileInputStream in = new FileInputStream(envFile);
			env.load(in);
			in.close();
		}
		catch (Exception e)
		{
			return;
		}
		String updated = env.getProperty("ADEMPIERE_MAIL_UPDATED");
		if (updated != null || updated.equals("Y"))
			return;
		
		//	See org.compiere.install.ConfigurationData
		String server = env.getProperty("ADEMPIERE_MAIL_SERVER");
		if (server == null || server.length() == 0)
			return;
		String adminEMail = env.getProperty("ADEMPIERE_ADMIN_EMAIL");
		if (adminEMail == null || adminEMail.length() == 0)
			return;
		String mailUser = env.getProperty("ADEMPIERE_MAIL_USER");
		if (mailUser == null || mailUser.length() == 0)
			return;
		String mailPassword = env.getProperty("ADEMPIERE_MAIL_PASSWORD");
	//	if (mailPassword == null || mailPassword.length() == 0)
	//		return;
		//
		StringBuffer sql = new StringBuffer("UPDATE AD_Client SET")
			.append(" SMTPHost=").append(DB.TO_STRING(server))
			.append(", RequestEMail=").append(DB.TO_STRING(adminEMail))
			.append(", RequestUser=").append(DB.TO_STRING(mailUser))
			.append(", RequestUserPW=").append(DB.TO_STRING(mailPassword))
			.append(", IsSMTPAuthorization='Y' WHERE AD_Client_ID=0");
		int no = DB.executeUpdate(sql.toString(), null);
		//
		sql = new StringBuffer("UPDATE AD_User SET ")
			.append(" EMail=").append(DB.TO_STRING(adminEMail))
			.append(", EMailUser=").append(DB.TO_STRING(mailUser))
			.append(", EMailUserPW=").append(DB.TO_STRING(mailUser))
			.append(" WHERE AD_User_ID IN (0,100)");
		no = DB.executeUpdate(sql.toString(), null);
		//
		try
		{
			env.setProperty("ADEMPIERE_MAIL_UPDATED", "Y");
			FileOutputStream out = new FileOutputStream(envFile);
			env.store(out, "");
			out.flush();
			out.close();
		}
		catch (Exception e)
		{
		}

	}	//	updateMail
	
	/**************************************************************************
	 *  Set connection
	 *  @param cc connection
	 */
	public static void setDBTarget (CConnection cc)
	{
		if (cc == null)
			throw new IllegalArgumentException("Connection is NULL");

		if (s_cc != null && s_cc.equals(cc))
			return;
		
		DB.closeTarget();
		//
		if (s_cc == null)
			s_cc = cc;
		synchronized (s_cc)    //  use as mutex
		{
			s_cc = cc;
			s_connections = null;
			s_connectionRW = null;
			s_connectionID = null;
		}
		if ( isRemoteObjects() == false)
			s_cc.setDataSource();
		log.config(s_cc + " - DS=" + s_cc.isDataSource());
	//	Trace.printStack();
	}   //  setDBTarget

	/**
	 * Connect to database and initialise all connections.
	 * @return True if success, false otherwise
	 */
	public static boolean connect() {
		//wan profile
		if (DB.isRemoteObjects()) return true;
		
		boolean success =false;
		try 
		{
			success = getConnectionRW() != null;
			if (success) success = getConnectionRO() != null;
			if (success) success = getConnectionID() != null;
			s_cc.readInfo(getConnectionRW());
		} catch (Exception e)
		{
			success = false;
		}
		return success;
	}
	
	/**
	 * @return true, if connected to database
	 */
	public static boolean isConnected()
	{
		return isConnected(true);
	}
	
	/**
	 *  Is there a connection to the database ?
	 *  @param createNew If true, try to connect it not already connected
	 *  @return true, if connected to database
	 */
	public static boolean isConnected(boolean createNew)
	{
		//bug [1637432]
		if (s_cc == null) return false;
		
		//wan profile
		if (DB.isRemoteObjects()) return true;
		
		boolean success = false;
		CLogErrorBuffer eb = CLogErrorBuffer.get(false);
		if (eb != null && eb.isIssueError())
			eb.setIssueError(false);
		else
			eb = null;	//	don't reset
		try
		{
			success = getConnectionRW(createNew) != null;	//	try to get a connection
		}
		catch (Exception e)
		{
			success = false;
		}
		if (eb != null)
			eb.setIssueError(true);
		return success;
	}   //  isConnected

	/**
	 * @return Connection (r/w)
	 */
	public static Connection getConnectionRW()
	{
		return getConnectionRW(true);
	}
	
	/**
	 *	Return (pooled) r/w AutoCommit, Serializable connection.
	 *	For Transaction control use Trx.getConnection()
	 *  @param createNew If true, try to create new connection if no existing connection
	 *  @return Connection (r/w)
	 */
	public static Connection getConnectionRW (boolean createNew)
	{
		//wan profile
		if (DB.isRemoteObjects()) return null;
		
		//	check health of connection
		try
		{
			if (s_connectionRW == null)
				;
			else if (s_connectionRW.isClosed())
			{
				log.finest("Closed");
				s_connectionRW = null;
			}
			else if (s_connectionRW instanceof OracleConnection && ((OracleConnection)s_connectionRW).pingDatabase(1) < 0)
			{
				log.warning("No ping");
				s_connectionRW = null;
			}
			else
			{
				if (s_connectionRW.getTransactionIsolation() != Connection.TRANSACTION_READ_COMMITTED)
					s_connectionRW.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			}
		}
		catch (Exception e)
		{
			s_connectionRW = null;
		}
		//	Get new
		if (s_connectionRW == null)
		{
			if (createNew)
			{
				s_connectionRW = s_cc.getConnection (true, Connection.TRANSACTION_READ_COMMITTED);
				log.finest("Con=" + s_connectionRW);
			}
		}
		if (s_connectionRW == null && createNew)
			throw new UnsupportedOperationException("No DBConnection");
		//
	//	System.err.println ("DB.getConnectionRW - " + s_connectionRW); 
	//	Trace.printStack();
		return s_connectionRW;
	}   //  getConnectionRW

	/**
	 *	Return everytime a new r/w no AutoCommit, Serializable connection.
	 *	To be used to ID
	 *  @return Connection (r/w)
	 */
	public static Connection getConnectionID ()
	{
		//wan profile
		if (DB.isRemoteObjects()) return null;
		
		if (s_connectionID != null)
		{
			try
			{
				if (s_connectionID.isClosed())
					s_connectionID = null;
			}
			catch (Exception e)
			{
				s_connectionID = null;
			}
		}
		if (s_connectionID == null)
		{
			s_connectionID = s_cc.getConnection (false, Connection.TRANSACTION_READ_COMMITTED);
		}
		if (s_connectionID == null)
			throw new UnsupportedOperationException("No DBConnection");
		log.log(Level.ALL, s_connectionID.toString());
		return s_connectionID;
	}   //  getConnectionID

	/**
	 *	Return read committed, read/only from pool.
	 *  @return Connection (r/o)
	 */
	public static Connection getConnectionRO ()
	{
		//wan profile
		if (DB.isRemoteObjects()) return null;
		
		try
		{
			synchronized (s_cc)    //  use as mutex as s_connection is null the first time
			{
				if (s_connections == null)
					s_connections = createConnections (Connection.TRANSACTION_READ_COMMITTED);     //  see below
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "RO", e);
		}

		//  check health of connection
		int pos = s_conCount++;
		int connectionNo = pos % s_conCacheSize;
		Connection connection = s_connections[connectionNo];
		try
		{
			if (connection == null)
				;
			else if (connection.isClosed())
			{
			//	RowSet.close also closes connection!
			//	System.out.println("DB.getConnectionRO - closed #" + connectionNo);
				connection = null;
			}
			else if (connection instanceof OracleConnection && ((OracleConnection)connection).pingDatabase(1) < 0)
			{
				log.warning("No ping #" + connectionNo);
				connection = null;
			}
			else
			{
				if (!connection.isReadOnly())
					connection.setReadOnly(true);
				if (connection.getTransactionIsolation() != Connection.TRANSACTION_READ_COMMITTED)
					connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			}
		}
		catch (Exception e)
		{
			log.severe("#" + connectionNo + " - " + e.toString());
			connection = null;
		}
		//	Get new
		if (connection == null)
		{
			log.finest("Replacing connection #" + connectionNo);
			connection = s_cc.getConnection (true, Connection.TRANSACTION_READ_COMMITTED); //  see above
			try
			{
				if (connection != null)
					connection.setReadOnly(true);
			}
			catch (Exception e)
			{
				log.severe("Cannot set to R/O - " + e);
			} 
			s_connections[connectionNo] = connection;
		}
		if (connection == null)
			throw new UnsupportedOperationException("DB.getConnectionRO - @NoDBConnection@");
		log.log(Level.ALL, "#" + connectionNo + " - " + connection);
	//	System.err.println ("DB.getConnectionRO - " + connection); 
		return connection;
	}	//	getConnectionRO

	/**
	 *	Create new Connection.
	 *  The connection must be closed explicitly by the application
	 *
	 *  @param autoCommit auto commit
	 *  @param trxLevel - Connection.TRANSACTION_READ_UNCOMMITTED, Connection.TRANSACTION_READ_COMMITTED, Connection.TRANSACTION_REPEATABLE_READ, or Connection.TRANSACTION_READ_COMMITTED.
	 *  @return Connection connection
	 */
	public static Connection createConnection (boolean autoCommit, int trxLevel)
	{
		//wan and vpn
		if (isRemoteObjects()) return null;
		
		Connection conn = s_cc.getConnection (autoCommit, trxLevel);
		if (CLogMgt.isLevelFinest())
		{
			/**
			try
			{
				log.finest(s_cc.getConnectionURL()
					+ ", UserID=" + s_cc.getDbUid() 
					+ ", AutoCommit=" + conn.getAutoCommit() + " (" + autoCommit + ")"
					+ ", TrxIso=" + conn.getTransactionIsolation() + "( " + trxLevel + ")");
			}
			catch (Exception e)
			{
			}
			**/
		}
		return conn;
	}	//	createConnection

	/**
	 *	Create new set of r/o Connections.
	 *  R/O connection might not be supported by DB
	 *
	 *  @param trxLevel - Connection.TRANSACTION_READ_UNCOMMITTED, Connection.TRANSACTION_READ_COMMITTED, Connection.TRANSACTION_REPEATABLE_READ, or Connection.TRANSACTION_READ_COMMITTED.
	 *  @return Array of Connections (size based on s_conCacheSize)
	 */
	private static Connection[] createConnections (int trxLevel)
	{
		log.finest("(" + s_conCacheSize + ") " + s_cc.getConnectionURL()
			+ ", UserID=" + s_cc.getDbUid() 
			+ ", TrxLevel=" + CConnection.getTransactionIsolationInfo(trxLevel));
		Connection cons[] = new Connection[s_conCacheSize];
		try
		{
			for (int i = 0; i < s_conCacheSize; i++)
			{
				cons[i] = s_cc.getConnection (true, trxLevel);  //  auto commit
				if (cons[i] == null)
					log.warning("Connection is NULL");	//	don't use log
			}
		}
		catch (Exception e)
		{
			log.severe(e.getMessage());
		}
		return cons;
	}	//	createConnections

	/**
	 *  Get Database Driver.
	 *  Access to database specific functionality.
	 *  @return Adempiere Database Driver
	 */
	public static AdempiereDatabase getDatabase()
	{
		if (s_cc != null)
			return s_cc.getDatabase();
		log.severe("No Database Connection");
		return null;
	}   //  getDatabase
	
	/**
	 *  Get Database Driver.
	 *  Access to database specific functionality.
	 *  @param URL JDBC connection url
	 *  @return Adempiere Database Driver
	 */
	public static AdempiereDatabase getDatabase(String URL)
	{
		return Database.getDatabaseFromURL(URL);
	}   //  getDatabase

	/**
	 * 	Do we have an Oracle DB ?
	 *	@return true if connected to Oracle
	 */
	public static boolean isOracle()
	{
		if (s_cc != null)
			return s_cc.isOracle();
		log.severe("No Database Connection");
		return false;
	}	//	isOracle

	/**
	 * 	Do we have a Derby DB ?
	 *	@return true if connected to Derby
	 */
	
	public static boolean isDerby()
	{
		if (s_cc != null)
			return s_cc.isDerby();
		log.severe("No Database Connection");
		return false;
	}	//	isDerby

            //begin vpj-cd e-evolution 02/07/2005 PostgreSQL
	/**
	 * 	Do we have a Postgre DB ?
	 *	@return true if connected to PostgreSQL
	 */
	public static boolean isPostgreSQL()
	{
		if (s_cc != null)
			return s_cc.isPostgreSQL();
                log.severe("No Database");
		return false;
	}	//	isPostgreSQL
            //begin vpj-cd e-evolution 02/07/2005 PostgreSQL
	
	public static boolean isFyracle()
	{
		if (s_cc != null)
			return s_cc.isFyracle();
                log.severe("No Database");
		return false;
	}	//	isFyracle
 	
	/**
	 * 	Do we have a Postgre DB ?
	 *	@return true if connected to PostgreSQL
	 */
//	public static boolean isEDB()
//	{
//		if (s_cc != null)
//			return s_cc.isEDB();
//                log.severe("No Database");
//		return false;
//	}	//	isPostgreSQL
        //end vpj-cd e-evolution 02/07/2005 PostgreSQL
        
	/**
	 * 	Get Database Info
	 *	@return info
	 */
	public static String getDatabaseInfo()
	{
		if (s_cc != null)
			return s_cc.getDBInfo();
		return "No Database";
	}	//	getDatabaseInfo

	
	/**************************************************************************
	 *  Check database Version with Code version
	 *  @param ctx context
	 *  @return true if Database version (date) is the same
	 */
	public static boolean isDatabaseOK (Properties ctx)
	{
		//  Check Version
		String version = "?";
		String sql = "SELECT Version FROM AD_System";
		try
		{
			PreparedStatement pstmt = prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				version = rs.getString(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "Problem with AD_System Table - Run system.sql script - " + e.toString());
			return false;
		}
		log.info("DB_Version=" + version);
		//  Identical DB version
		if (Adempiere.DB_VERSION.equals(version))
			return true;

		String AD_Message = "DatabaseVersionError";
		String title = org.compiere.Adempiere.getName() + " " +  Msg.getMsg(ctx, AD_Message, true);
		//	Code assumes Database version {0}, but Database has Version {1}.
		String msg = Msg.getMsg(ctx, AD_Message);	//	complete message
		msg = MessageFormat.format(msg, new Object[] {Adempiere.DB_VERSION, version});
		Object[] options = { UIManager.get("OptionPane.noButtonText"), "Migrate" };
		int no = JOptionPane.showOptionDialog (null, msg,
			title, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
			UIManager.getIcon("OptionPane.errorIcon"), options, options[0]);
		if (no == 1)
		{
			JOptionPane.showMessageDialog (null,
				"Start RUN_Migrate (in utils)\nSee: http://www.adempiere.com/maintain",
				title, JOptionPane.INFORMATION_MESSAGE);
			Env.exitEnv(1);
		}
		return false;
	}   //  isDatabaseOK

	
	/**************************************************************************
	 *	Close Target
	 */
	public static void closeTarget()
	{
		boolean closed = false;
		//	RO connection
		if (s_connections != null)
		{
			for (int i = 0; i < s_conCacheSize; i++)
			{
				try
				{
					if (s_connections[i] != null)
					{
						closed = true;
						s_connections[i].close();
					}
				}
				catch (SQLException e)
				{
					log.warning("#" + i + " - " + e.getMessage());
				}
				s_connections[i] = null;
			}
		}
		s_connections = null;
		//	RW connection
		try
		{
			if (s_connectionRW != null)
			{
				closed = true;
				s_connectionRW.close();
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "R/W", e);
		}
		s_connectionRW = null;
		
		//ID Connection
		try 
		{
			if (s_connectionID != null)
			{
				s_connectionID.close();
			}
		} catch (SQLException e)
		{
			log.log(Level.SEVERE, "Id", e);
		}
		s_connectionID = null;
		
		//	CConnection
		if (s_cc != null)
		{
			closed = true;
			s_cc.setDataSource(null);
		}
		s_cc = null;
		if (closed)
			log.fine("closed");
	}	//	closeTarget

	/**************************************************************************
	 *	Prepare Forward Read Only Call
	 *  @param SQL sql
	 *  @return Callable Statement
	 */
	public static CallableStatement prepareCall(String sql) 
	{
		return prepareCall(sql, ResultSet.CONCUR_UPDATABLE, null);
	}
	
	/**************************************************************************
	 *	Prepare Call
	 *  @param SQL sql
	 *  @param readOnly
	 *  @param trxName
	 *  @return Callable Statement
	 */
	public static CallableStatement prepareCall(String SQL, int resultSetConcurrency, String trxName)
	{
		if (SQL == null || SQL.length() == 0)
			throw new IllegalArgumentException("Required parameter missing - " + SQL);
		//
		String sql = getDatabase().convertStatement(SQL);
		try
		{
			Connection conn = null;
			Trx trx = trxName == null ? null : Trx.get(trxName, true);
			if (trx != null)
				conn = trx.getConnection();
			else
			{
				if (resultSetConcurrency == ResultSet.CONCUR_UPDATABLE)
					conn = DB.getConnectionRW ();
				else
					conn = DB.getConnectionRO();
			}
			if (conn == null)
				throw new DBException("No Connection");
			
			return conn.prepareCall
				(sql, ResultSet.TYPE_FORWARD_ONLY, resultSetConcurrency);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		//	throw new DBException(e);
		}
		return null;
	}	//	prepareCall

	
	/**************************************************************************
	 *	Prepare Read Only Statement
	 *  @param RO_SQL sql (RO)
	 *  @return Prepared Statement
	 *  @deprecated
	 */
	public static CPreparedStatement prepareStatement (String RO_SQL)
	{
		return prepareStatement(RO_SQL, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, null);
	}	//	prepareStatement

	/**
	 *	Prepare Read Only Statement
	 *  @param RO_SQL sql (RO)
	 * 	@param trxName transaction
	 *  @return Prepared Statement
	 */
	public static CPreparedStatement prepareStatement (String RO_SQL, String trxName)
	{
		return prepareStatement(RO_SQL, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, trxName);
	}	//	prepareStatement
	
	/**
	 *	Prepare Statement.
	 *  @param sql sql statement
	 *  @param resultSetType - ResultSet.TYPE_FORWARD_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.TYPE_SCROLL_SENSITIVE
	 *  @param resultSetConcurrency - ResultSet.CONCUR_READ_ONLY or ResultSet.CONCUR_UPDATABLE
	 *  @return Prepared Statement r/o or r/w depending on concur
	 *  @deprecated
	 */
	public static CPreparedStatement prepareStatement (String sql, 
		int resultSetType, int resultSetConcurrency)
	{
		return prepareStatement(sql, resultSetType, resultSetConcurrency, null);
	}	//	prepareStatement

	/**
	 *	Prepare Statement.
	 *  @param sql sql statement
	 *  @param resultSetType - ResultSet.TYPE_FORWARD_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.TYPE_SCROLL_SENSITIVE
	 *  @param resultSetConcurrency - ResultSet.CONCUR_READ_ONLY or ResultSet.CONCUR_UPDATABLE
	 * 	@param trxName transaction name
	 *  @return Prepared Statement r/o or r/w depending on concur
	 */
	public static CPreparedStatement prepareStatement(String sql, 
		int resultSetType, int resultSetConcurrency, String trxName)
	{
		if (sql == null || sql.length() == 0)
			throw new IllegalArgumentException("No SQL");
		//
		return new CPreparedStatement(resultSetType, resultSetConcurrency, sql, trxName);
	}	//	prepareStatement

	/**
	 *	Create Read Only Statement
	 *  @return Statement
	 */
	public static Statement createStatement()
	{
		return createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, null);
	}	//	createStatement

	/**
	 *	Create Statement.
	 *  @param resultSetType - ResultSet.TYPE_FORWARD_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.TYPE_SCROLL_SENSITIVE
	 *  @param resultSetConcurrency - ResultSet.CONCUR_READ_ONLY or ResultSet.CONCUR_UPDATABLE
	 * 	@param trxName transaction name
	 *  @return Statement - either r/w ir r/o depending on concur
	 */
	public static Statement createStatement(int resultSetType, int resultSetConcurrency, String trxName)
	{
		return new CStatement(resultSetType, resultSetConcurrency, trxName);
	}	//	createStatement

	/**
	 *	Execute Update.
	 *  saves "DBExecuteError" in Log
	 *  @param sql sql
	 *  @return number of rows updated or -1 if error
	 *  @deprecated
	 */
	public static int executeUpdate (String sql)
	{
		return executeUpdate(sql, null, false, null);
	}	//	executeUpdate

	/**
	 *	Execute Update.
	 *  saves "DBExecuteError" in Log
	 *  @param sql sql
	 * 	@param trxName optional transaction name
	 *  @return number of rows updated or -1 if error
	 */
	public static int executeUpdate (String sql, String trxName)
	{
		return executeUpdate(sql, null, false, trxName);
	}	//	executeUpdate

	/**
	 *	Execute Update.
	 *  saves "DBExecuteError" in Log
	 *  @param sql sql
	 * 	@param ignoreError if true, no execution error is reported
	 *  @return number of rows updated or -1 if error
	 *  @deprecated
	 */
	public static int executeUpdate (String sql, boolean ignoreError)
	{
		return executeUpdate (sql, null, ignoreError, null);
	}	//	executeUpdate

	/**
	 *	Execute Update.
	 *  saves "DBExecuteError" in Log
	 *  @param sql sql
	 * 	@param ignoreError if true, no execution error is reported
	 * 	@param trxName transaction
	 *  @return number of rows updated or -1 if error
	 */
	public static int executeUpdate (String sql, boolean ignoreError, String trxName)
	{
		return executeUpdate (sql, null, ignoreError, trxName);
	}	//	executeUpdate

	/**
	 *	Execute Update.
	 *  saves "DBExecuteError" in Log
	 *  @param sql sql
	 *  @param param int param
	 * 	@param trxName transaction
	 *  @return number of rows updated or -1 if error
	 */
	public static int executeUpdate (String sql, int param, String trxName)
	{
		return executeUpdate (sql, new Object[]{new Integer(param)}, false, trxName);
	}	//	executeUpdate

	/**
	 *	Execute Update.
	 *  saves "DBExecuteError" in Log
	 *  @param sql sql
	 *  @param param int parameter
	 * 	@param ignoreError if true, no execution error is reported
	 * 	@param trxName transaction
	 *  @return number of rows updated or -1 if error
	 */
	public static int executeUpdate (String sql, int param, boolean ignoreError, String trxName)
	{
		return executeUpdate (sql, new Object[]{new Integer(param)}, ignoreError, trxName);
	}	//	executeUpdate

	/**
	 *	Execute Update.
	 *  saves "DBExecuteError" in Log
	 *  @param sql sql
	 *  @param params array of parameters
	 * 	@param ignoreError if true, no execution error is reported
	 * 	@param trxName optional transaction name
	 *  @return number of rows updated or -1 if error
	 */
	public static int executeUpdate (String sql, Object[] params, boolean ignoreError, String trxName)
	{
		if (sql == null || sql.length() == 0)
			throw new IllegalArgumentException("Required parameter missing - " + sql);
		//
		int no = -1;
		CPreparedStatement cs = new CPreparedStatement(ResultSet.TYPE_FORWARD_ONLY, 
			ResultSet.CONCUR_UPDATABLE, sql, trxName);	//	converted in call
		
		try
		{
			//	Set Parameter
			if (params != null)
			{
				for (int i = 0; i < params.length; i++)
				{
					Object param = params[i];
					if (param instanceof String)
						cs.setString(i+1, (String)param);
					else if (param instanceof Integer)
						cs.setInt(i+1, ((Integer)param).intValue());
					else if (param instanceof BigDecimal)
						cs.setBigDecimal(i+1, (BigDecimal)param);
					else if (param instanceof Timestamp)
						cs.setTimestamp(i+1, (Timestamp)param);
				}
			}
			//
			no = cs.executeUpdate();
			//	No Transaction - Commit
			if (trxName == null)
			{
				cs.commit();	//	Local commit
			//	Connection conn = cs.getConnection();
			//	if (conn != null && !conn.getAutoCommit())	//	is null for remote
			//		conn.commit();
			}
		}
		catch (SQLException e)
		{
			if (ignoreError)
				log.log(Level.SEVERE, cs.getSql() + " [" + trxName + "] - " +  e.getMessage());
			else
			{
				log.log(Level.SEVERE, cs.getSql() + " [" + trxName + "]", e);
				log.saveError ("DBExecuteError", e);
			}
		//	throw new DBException(e);
		}
		finally
		{
			//  Always close cursor
			try
			{
				cs.close();
			}
			catch (SQLException e2)
			{
				log.log(Level.SEVERE, "Cannot close statement");
			}
		}
		return no;
	}	//	executeUpdate

	/**
	 *	Execute multiple Update statements.
	 *  saves (last) "DBExecuteError" in Log
	 *  @param sql multiple sql statements separated by "; " SQLSTATEMENT_SEPARATOR
	 * 	@param ignoreError if true, no execution error is reported
	 * 	@param trxName optional transaction name
	 *  @return number of rows updated or -1 if error
	 */
	public static int executeUpdateMultiple (String sql, boolean ignoreError, String trxName)
	{
		if (sql == null || sql.length() == 0)
			throw new IllegalArgumentException("Required parameter missing - " + sql);
		int index = sql.indexOf(SQLSTATEMENT_SEPARATOR);
		if (index == -1)
			return executeUpdate(sql, null, ignoreError, trxName);
		int no = 0;
		//
		String statements[] = sql.split(SQLSTATEMENT_SEPARATOR);
		for (int i = 0; i < statements.length; i++)
		{
			log.fine(statements[i]);
			no += executeUpdate(statements[i], null, ignoreError, trxName);
		}
		
		return no;
	}	//	executeUpdareMultiple

	/**
	 *	Execute Update and throw exception.
	 *  @param sql
	 *  @return number of rows updated or -1 if error
	 * 	@param trxName transaction
	 * 	@throws SQLException
	 */
	public static int executeUpdateEx (String sql, String trxName) throws SQLException
	{
		if (sql == null || sql.length() == 0)
			throw new IllegalArgumentException("Required parameter missing - " + sql);
		//
		int no = -1;
		CPreparedStatement cs = new CPreparedStatement(ResultSet.TYPE_FORWARD_ONLY, 
			ResultSet.CONCUR_UPDATABLE, sql, trxName);	//	converted in call
		
		SQLException ex = null;
		try
		{
			no = cs.executeUpdate();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql + " [" + trxName + "]", e);
			ex = e;
		}
		finally
		{
			//  Always close cursor
			try
			{
				cs.close();
			}
			catch (SQLException e2)
			{
				log.log(Level.SEVERE, "Cannot close statement");
			}
		}
		if (ex != null)
			throw ex;
		return no;
	}	//	execute Update

	/**
	 *	Commit - commit on RW connection.
	 *  Is not required as RW connection is AutoCommit (exception: with transaction)
	 *  @param throwException if true, re-throws exception
	 * 	@param trxName transaction name
	 *  @return true if not needed or success
	 *  @throws SQLException
	 */
	public static boolean commit (boolean throwException, String trxName) throws SQLException
	{
		try
		{
			Connection conn = null;
			Trx trx = trxName == null ? null : Trx.get(trxName, true);
			if (trx != null)
				return trx.commit(true);
			else
				conn = DB.getConnectionRW ();
			if (conn != null && !conn.getAutoCommit())
				conn.commit();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "[" + trxName + "]", e);
			if (throwException)
				throw e;
			return false;
		}
		return true;
	}	//	commit

	/**
	 *	Rollback - rollback on RW connection.
	 *  Is has no effect as RW connection is AutoCommit (exception: with transaction)
	 *  @param throwException if true, re-throws exception
	 * 	@param trxName transaction name
	 *  @return true if not needed or success
	 *  @throws SQLException
	 */
	public static boolean rollback (boolean throwException, String trxName) throws SQLException
	{
		try
		{
			Connection conn = null;
			Trx trx = trxName == null ? null : Trx.get(trxName, true);
			if (trx != null)
				return trx.rollback(true);
			else
				conn = DB.getConnectionRW ();
			if (conn != null && !conn.getAutoCommit())
				conn.rollback();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "[" + trxName + "]", e);
			if (throwException)
				throw e;
			return false;
		}
		return true;
	}	//	commit

	/**
	 * 	Get Row Set.
	 * 	When a Rowset is closed, it also closes the underlying connection.
	 * 	If the created RowSet is transfered by RMI, closing it makes no difference 
	 *	@param sql sql
	 *	@param local local RowSet (own connection)
	 *	@return row set or null
	 */
	public static RowSet getRowSet (String sql)
	{
		RowSet retValue = null;
		              // Bugfix Gunther Hoppe, 02.09.2005 add vpj-cd e-evolution
                // Begin		
		//		CStatementVO info = new CStatementVO ( 
		//	RowSet.TYPE_SCROLL_INSENSITIVE, RowSet.CONCUR_READ_ONLY, sql);
		CStatementVO info = new CStatementVO (RowSet.TYPE_SCROLL_INSENSITIVE, RowSet.CONCUR_READ_ONLY, DB.getDatabase().convertStatement(sql));
                // End add vpj-cd e-evolution
		CPreparedStatement stmt = new CPreparedStatement(info);
		retValue = stmt.getRowSet();
		return retValue;
	}	//	getRowSet

	/**
	 * 	Get Value from sql
	 * 	@param trxName trx
	 * 	@param sql sql
	 * 	@return first value or -1
	 */
	public static int getSQLValue (String trxName, String sql)
	{
		int retValue = -1;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = prepareStatement(sql, trxName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				retValue = rs.getInt(1);
			else
				log.fine("No Value " + sql);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}
		return retValue;
	}	//	getSQLValue

	/**
	 * 	Get Value from sql
	 * 	@param trxName trx
	 * 	@param sql sql
	 * 	@param int_param1 parameter 1
	 * 	@return first value or -1
	 */
	public static int getSQLValue (String trxName, String sql, int int_param1)
	{
		int retValue = -1;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = prepareStatement(sql, trxName);
			pstmt.setInt(1, int_param1);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				retValue = rs.getInt(1);
			else
				log.config("No Value " + sql + " - Param1=" + int_param1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql + " - Param1=" + int_param1 + " [" + trxName + "]", e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}
		return retValue;
	}	//	getSQLValue

	/**
	 * 	Get Value from sql
	 * 	@param trxName trx
	 * 	@param sql sql
	 * 	@param int_param1 parameter 1
	 * 	@param int_param2 parameter 2
	 * 	@return first value or -1
	 */
	public static int getSQLValue (String trxName, String sql, int int_param1, int int_param2)
	{
		int retValue = -1;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = prepareStatement(sql, trxName);
			pstmt.setInt(1, int_param1);
			pstmt.setInt(2, int_param2);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				retValue = rs.getInt(1);
			else
				log.info("No Value " + sql 
					+ " - Param1=" + int_param1 + ",Param2=" + int_param2);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql + " - Param1=" + int_param1 + ",Param2=" + int_param2 
				+ " [" + trxName + "]", e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}
		return retValue;
	}	//	getSQLValue

	/**
	 * 	Get Value from sql
	 * 	@param trxName trx
	 * 	@param sql sql
	 * 	@param str_param1 parameter 1
	 * 	@return first value or -1
	 */
	public static int getSQLValue (String trxName, String sql, String str_param1)
	{
		int retValue = -1;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = prepareStatement(sql, trxName);
			pstmt.setString(1, str_param1);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				retValue = rs.getInt(1);
			else
				log.info("No Value " + sql + " - Param1=" + str_param1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql + " - Param1=" + str_param1, e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}
		return retValue;
	}	//	getSQLValue

	/**
	 * 	Get Value from sql
	 * 	@param trxName trx
	 * 	@param sql sql
	 * 	@param int_param1 parameter 1
	 * 	@param s_param2 parameter 2
	 * 	@return first value or -1
	 */
	public static int getSQLValue (String trxName, String sql, int int_param1, String s_param2)
	{
		int retValue = -1;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = prepareStatement(sql, trxName);
			pstmt.setInt(1, int_param1);
			pstmt.setString(2, s_param2);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				retValue = rs.getInt(1);
			else
				log.info("No Value: " + sql + " - Param1=" + int_param1 + ",Param2=" + s_param2);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql + " - Param1=" + int_param1 + ",Param2=" + s_param2, e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}
		return retValue;
	}	//	getSQLValue

	/**
	 * 	Get String Value from sql
	 * 	@param trxName trx
	 * 	@param sql sql
	 * 	@param int_param1 parameter 1
	 * 	@return first value or null
	 */
	public static String getSQLValueString (String trxName, String sql, int int_param1)
	{
		String retValue = null;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = prepareStatement(sql, trxName);
			pstmt.setInt(1, int_param1);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				retValue = rs.getString(1);
			else
				log.info("No Value " + sql + " - Param1=" + int_param1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql + " - Param1=" + int_param1, e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}
		return retValue;
	}	//	getSQLValueString

	/**
	 * 	Get BigDecimal Value from sql
	 * 	@param trxName trx
	 * 	@param sql sql
	 * 	@param int_param1 parameter 1
	 * 	@return first value or null
	 */
	public static BigDecimal getSQLValueBD (String trxName, String sql, int int_param1)
	{
		BigDecimal retValue = null;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = prepareStatement(sql, trxName);
			pstmt.setInt(1, int_param1);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				retValue = rs.getBigDecimal(1);
			else
				log.info("No Value " + sql + " - Param1=" + int_param1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql + " - Param1=" + int_param1 + " [" + trxName + "]", e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}
		return retValue;
	}	//	getSQLValueBD

	
	/**
	 * 	Get Array of Key Name Pairs
	 *	@param sql select with id / name as first / second column
	 *	@param optional if true (-1,"") is added 
	 *	@return array of key name pairs
	 */
	public static KeyNamePair[] getKeyNamePairs(String sql, boolean optional)
	{
		PreparedStatement pstmt = null;
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		if (optional)
			list.add (new KeyNamePair(-1, ""));
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		KeyNamePair[] retValue = new KeyNamePair[list.size()];
		list.toArray(retValue);
	//	s_log.fine("getKeyNamePairs #" + retValue.length);
		return retValue;		
	}	//	getKeyNamePairs
	
	/**
	 * 	Is Sales Order Trx.
	 * 	Assumes Sales Order. Queries IsSOTrx of table with where clause
	 *	@param TableName table
	 *	@param whereClause where clause
	 *	@return true (default) or false if tested that not SO
	 */
	public static boolean isSOTrx (String TableName, String whereClause)
	{
		if (TableName == null || TableName.length() == 0)
		{
			log.severe("No TableName");
			return true;
		}
		if (whereClause == null || whereClause.length() == 0)
		{
			log.severe("No Where Clause");
			return true;
		}
		//
		boolean isSOTrx = true;
		String sql = "SELECT IsSOTrx FROM " + TableName 
			+ " WHERE " + whereClause;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
				isSOTrx = "Y".equals(rs.getString(1));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			if (TableName.endsWith("Line"))
			{
				String hdr = TableName.substring(0, TableName.indexOf("Line"));
				sql = "SELECT IsSOTrx FROM " + hdr 
					+ " h WHERE EXISTS (SELECT * FROM " + TableName 
					+ " l WHERE h." + hdr + "_ID=l." + hdr + "_ID AND "
					+ whereClause + ")";
				PreparedStatement pstmt2 = null;
				try
				{
					pstmt2 = DB.prepareStatement (sql, null);
					ResultSet rs2 = pstmt2.executeQuery ();
					if (rs2.next ())
						isSOTrx = "Y".equals(rs2.getString(1));
					rs2.close ();
					pstmt2.close ();
					pstmt2 = null;
				}
				catch (Exception ee)
				{
					log.finest(sql + " - " + e.getMessage());
				}
				try
				{
					if (pstmt2 != null)
						pstmt2.close ();
					pstmt2 = null;
				}
				catch (Exception ee)
				{
					pstmt2 = null;
				}
			}
			else
			{
				log.finest(TableName + " - No SOTrx");
			//	log.finest(sql + " - " + e.getMessage());
			}
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
		return isSOTrx;
	}	//	isSOTrx
	
	
	/**************************************************************************
	 *	Get next number for Key column = 0 is Error.
	 *   * @param ctx client
	@param TableName table name
	 * 	@param trxName optionl transaction name
	 *  @return next no
	 */
	public static int getNextID (Properties ctx, String TableName, String trxName)
	{
		if (ctx == null)
			throw new IllegalArgumentException("Context missing");
		if (TableName == null || TableName.length() == 0)
			throw new IllegalArgumentException("TableName missing");
		return getNextID(Env.getAD_Client_ID(ctx), TableName, trxName);
	}	//	getNextID

	/**
	 *	Get next number for Key column = 0 is Error.
	 *  @param AD_Client_ID client
	 *  @param TableName table name
	 * 	@param trxName optional Transaction Name
	 *  @return next no
	 */
	public static int getNextID (int AD_Client_ID, String TableName, String trxName)
	{
		if (isRemoteObjects())
		{
			Server server = CConnection.get().getServer();
			try
			{
				if (server != null)
				{	//	See ServerBean
					int id = server.getNextID(AD_Client_ID, TableName, trxName);
					log.finest("server => " + id);
					if (id < 0)
						throw new DBException("No NextID");
					return id;
				}
				log.log(Level.SEVERE, "AppsServer not found - " + TableName); 
			}
			catch (RemoteException ex)
			{
				log.log(Level.SEVERE, "AppsServer error", ex);
			}
			//	Try locally
		}
		int id = MSequence.getNextID (AD_Client_ID, TableName, trxName);	//	tries 3 times
	//	if (id <= 0)
	//		throw new DBException("No NextID (" + id + ")");
		return id;
	}	//	getNextID
		
	/**
	 * 	Get Document No based on Document Type
	 *	@param C_DocType_ID document type
	 * 	@param trxName optional Transaction Name
	 *	@return document no or null
	 */
	public static String getDocumentNo(int C_DocType_ID, String trxName)
	{
		if (isRemoteObjects())
		{
			Server server = CConnection.get().getServer();
			try
			{
				if (server != null)
				{	//	See ServerBean
					String dn = server.getDocumentNo (C_DocType_ID, trxName);
					log.finest("Server => " + dn);
					if (dn != null)
						return dn;
				}
				log.log(Level.SEVERE, "AppsServer not found - " + C_DocType_ID); 
			}
			catch (RemoteException ex)
			{
				log.log(Level.SEVERE, "AppsServer error", ex);
			}
		}
		//	fallback
		String dn = MSequence.getDocumentNo (C_DocType_ID, trxName);
		if (dn == null)		//	try again
			dn = MSequence.getDocumentNo (C_DocType_ID, trxName);
	//	if (dn == null)
	//		throw new DBException ("No DocumentNo");
		return dn;
	}	//	getDocumentNo


	/**
	 * 	Get Document No from table
	 *	@param AD_Client_ID client
	 *	@param TableName table name
	 * 	@param trxName optional Transaction Name
	 *	@return document no or null
	 */
	public static String getDocumentNo (int AD_Client_ID, String TableName, String trxName)
	{
		if (isRemoteObjects())
		{
			Server server = CConnection.get().getServer();
			try
			{
				if (server != null)
				{	//	See ServerBean
					String dn = server.getDocumentNo (AD_Client_ID, TableName, trxName);
					log.finest("Server => " + dn);
					if (dn != null)
						return dn;
				}
				log.log(Level.SEVERE, "AppsServer not found - " + TableName); 
			}
			catch (RemoteException ex)
			{
				log.log(Level.SEVERE, "AppsServer error", ex);
			}
		}
		//	fallback
		String dn = MSequence.getDocumentNo (AD_Client_ID, TableName, trxName);
		if (dn == null)		//	try again
			dn = MSequence.getDocumentNo (AD_Client_ID, TableName, trxName);
		if (dn == null)
			throw new DBException ("No DocumentNo");
		return dn;
	}	//	getDocumentNo

	/**
	 *	Get Document Number for current document.
	 *  <br>
	 *  - first search for DocType based Document No
	 *  - then Search for DocumentNo based on TableName
	 *  @param ctx context
	 *  @param WindowNo window
	 *  @param TableName table
	 *  @param onlyDocType Do not search for document no based on TableName
	 * 	@param trxName optional Transaction Name
	 *	@return DocumentNo or null, if no doc number defined
	 */
	public static String getDocumentNo (Properties ctx, int WindowNo, 
		String TableName, boolean onlyDocType, String trxName)
	{
		if (ctx == null || TableName == null || TableName.length() == 0)
			throw new IllegalArgumentException("Required parameter missing");
		int AD_Client_ID = Env.getContextAsInt(ctx, WindowNo, "AD_Client_ID");

		//	Get C_DocType_ID from context - NO Defaults -
		int C_DocType_ID = Env.getContextAsInt(ctx, WindowNo + "|C_DocTypeTarget_ID");
		if (C_DocType_ID == 0)
			C_DocType_ID = Env.getContextAsInt(ctx, WindowNo + "|C_DocType_ID");
		if (C_DocType_ID == 0)
		{
			log.fine("Window=" + WindowNo
				+ " - Target=" + Env.getContextAsInt(ctx, WindowNo + "|C_DocTypeTarget_ID") + "/" + Env.getContextAsInt(ctx, WindowNo, "C_DocTypeTarget_ID")
				+ " - Actual=" + Env.getContextAsInt(ctx, WindowNo + "|C_DocType_ID") + "/" + Env.getContextAsInt(ctx, WindowNo, "C_DocType_ID"));
			return getDocumentNo (AD_Client_ID, TableName, trxName);
		}

		String retValue = getDocumentNo (C_DocType_ID, trxName);
		if (!onlyDocType && retValue == null)
			return getDocumentNo (AD_Client_ID, TableName, trxName);
		return retValue;
	}	//	getDocumentNo

	/**
	 * 	Is this a remote client connection
	 *	@return true if client and RMI or Objects on Server
	 */
	public static boolean isRemoteObjects()
	{
		return CConnection.get().isServerObjects()
			&& CConnection.get().isAppsServerOK(false);
	}	//	isRemoteObjects
	
	/**
	 * 	Is this a remote client connection
	 *	@return true if client and RMI or Process on Server
	 */
	public static boolean isRemoteProcess()
	{
		return CConnection.get().isServerProcess() 
			&& CConnection.get().isAppsServerOK(false);
	}	//	isRemoteProcess
	
	
	/**************************************************************************
	 *	Print SQL Warnings.
	 *  <br>
	 *		Usage: DB.printWarning("comment", rs.getWarnings());
	 *  @param comment comment
	 *  @param warning warning
	 */
	public static void printWarning (String comment, SQLWarning warning)
	{
		if (comment == null || warning == null || comment.length() == 0)
			throw new IllegalArgumentException("Required parameter missing");
		log.warning(comment);
		if (warning == null)
			return;
		//
		SQLWarning warn = warning;
		while (warn != null)
		{
			StringBuffer buffer = new StringBuffer();
			buffer.append(warn.getMessage())
				.append("; State=").append(warn.getSQLState())
				.append("; ErrorCode=").append(warn.getErrorCode());
			log.warning(buffer.toString());
			warn = warn.getNextWarning();
		}
	}	//	printWarning

	/**
	 *  Create SQL TO Date String from Timestamp
	 *
	 *  @param  time Date to be converted
	 *  @param  dayOnly true if time set to 00:00:00
	 *
	 *  @return TO_DATE('2001-01-30 18:10:20',''YYYY-MM-DD HH24:MI:SS')
	 *      or  TO_DATE('2001-01-30',''YYYY-MM-DD')
	 */
	public static String TO_DATE (Timestamp time, boolean dayOnly)
	{
		return s_cc.getDatabase().TO_DATE(time, dayOnly);
	}   //  TO_DATE

	/**
	 *  Create SQL TO Date String from Timestamp
	 *  @param day day time
	 *  @return TO_DATE String (day only)
	 */
	public static String TO_DATE (Timestamp day)
	{
		return TO_DATE(day, true);
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
	public static String TO_CHAR (String columnName, int displayType, String AD_Language)
	{
		if (columnName == null || AD_Language == null || columnName.length() == 0)
			throw new IllegalArgumentException("Required parameter missing");
		return s_cc.getDatabase().TO_CHAR(columnName, displayType, AD_Language);
	}   //  TO_CHAR

	/**
	 * 	Return number as string for INSERT statements with correct precision
	 *	@param number number
	 *	@param displayType display Type
	 *	@return number as string
	 */
	public String TO_NUMBER (BigDecimal number, int displayType)
	{
		return s_cc.getDatabase().TO_NUMBER(number, displayType);
	}	//	TO_NUMBER

	/**
	 *  Package Strings for SQL command in quotes
	 *  @param txt  String with text
	 *  @return escaped string for insert statement (NULL if null)
	 */
	public static String TO_STRING (String txt)
	{
		return TO_STRING (txt, 0);
	}   //  TO_STRING

	/**
	 *	Package Strings for SQL command in quotes.
	 *  <pre>
	 *		-	include in ' (single quotes)
	 *		-	replace ' with ''
	 *  </pre>
	 *  @param txt  String with text
	 *  @param maxLength    Maximum Length of content or 0 to ignore
	 *  @return escaped string for insert statement (NULL if null)
	 */
	public static String TO_STRING (String txt, int maxLength)
	{
		if (txt == null || txt.length() == 0)
			return "NULL";

		//  Length
		String text = txt;
		if (maxLength != 0 && text.length() > maxLength)
			text = txt.substring(0, maxLength);

		//  copy characters		(we need to look through anyway)
		StringBuffer out = new StringBuffer();
		out.append(QUOTE);		//	'
		for (int i = 0; i < text.length(); i++)
		{
			char c = text.charAt(i);
			if (c == QUOTE)
				out.append("''");
			else
				out.append(c);
		}
		out.append(QUOTE);		//	'
		//
		return out.toString();
	}	//	TO_STRING

	/** Quote			*/
	private static final char QUOTE = '\'';
	
	/**
	 * 	Run Post Migration manually
	 *	@param args ignored
	 */
	public static void main (String[] args)
	{
		Adempiere.startup(true);
		MSystem system = MSystem.get(Env.getCtx());
		system.setIsJustMigrated(true);
		afterMigration(Env.getCtx());
	}	//	main
}	//	DB

