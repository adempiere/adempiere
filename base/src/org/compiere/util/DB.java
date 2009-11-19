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
package org.compiere.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.sql.RowSet;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.adempiere.exceptions.DBException;
import org.compiere.Adempiere;
import org.compiere.db.AdempiereDatabase;
import org.compiere.db.CConnection;
import org.compiere.db.Database;
import org.compiere.db.ProxyFactory;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MLanguage;
import org.compiere.model.MRole;
import org.compiere.model.MSequence;
import org.compiere.model.MSysConfig;
import org.compiere.model.MSystem;
import org.compiere.model.PO;
import org.compiere.model.POResultSet;
import org.compiere.process.SequenceCheck;


/**
 *  General Database Interface
 *
 *  @author     Jorg Janke
 *  @version    $Id: DB.java,v 1.8 2006/10/09 00:22:29 jjanke Exp $
 *  ---
 * @author Ashley Ramdass (Posterita)
 *		<li>Modifications: removed static references to database connection and instead always
 *			get a new connection from database pool manager which manages all connections
 *			set rw/ro properties for the connection accordingly.
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 		<li>BF [ 1647864 ] WAN: delete record error
 * 		<li>FR [ 1884435 ] Add more DB.getSQLValue helper methods
 * 		<li>FR [ 1904460 ] DB.executeUpdate should handle Boolean params
 * 		<li>BF [ 1962568 ] DB.executeUpdate should handle null params
 * 		<li>FR [ 1984268 ] DB.executeUpdateEx should throw DBException
 * 		<li>FR [ 1986583 ] Add DB.executeUpdateEx(String, Object[], String)
 * 		<li>BF [ 2030233 ] Remove duplicate code from DB class
 * 		<li>FR [ 2107062 ] Add more DB.getKeyNamePairs methods
 *		<li>FR [ 2448461 ] Introduce DB.getSQLValue*Ex methods
 *		<li>FR [ 2781053 ] Introduce DB.getValueNamePairs
 *		<li>FR [ 2818480 ] Introduce DB.createT_Selection helper method
 *			https://sourceforge.net/tracker/?func=detail&aid=2818480&group_id=176962&atid=879335
 * @author Teo Sarca, teo.sarca@gmail.com
 * 		<li>BF [ 2873324 ] DB.TO_NUMBER should be a static method
 * 			https://sourceforge.net/tracker/?func=detail&aid=2873324&group_id=176962&atid=879332
 * 		<li>FR [ 2873891 ] DB.getKeyNamePairs should use trxName
 * 			https://sourceforge.net/tracker/?func=detail&aid=2873891&group_id=176962&atid=879335
 *  @author Paul Bowden, phib BF 2900767 Zoom to child tab - inefficient queries
 *  @see https://sourceforge.net/tracker/?func=detail&aid=2900767&group_id=176962&atid=879332
 */
public final class DB
{
	/** Connection Descriptor           */
	private static CConnection      s_cc = null;
	/**	Logger							*/
	private static CLogger			log = CLogger.getCLogger (DB.class);

	private static Object			s_ccLock = new Object();

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
        ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MRole role = new MRole (ctx, rs, null);
				role.updateAccessRecords();
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "(1)", e);
		}
        finally
        {
            close(rs);
            close(pstmt);
            rs= null;
            pstmt = null;
        }
		//	Release Specif stuff & Print Format
		try
		{
			Class<?> clazz = Class.forName("org.compiere.MigrateData");
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
		if (updated != null && updated.equals("Y"))
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
		log.fine("Client #"+no);
		//
		sql = new StringBuffer("UPDATE AD_User SET ")
			.append(" EMail=").append(DB.TO_STRING(adminEMail))
			.append(", EMailUser=").append(DB.TO_STRING(mailUser))
			.append(", EMailUserPW=").append(DB.TO_STRING(mailPassword))
			.append(" WHERE AD_User_ID IN (0,100)");
		no = DB.executeUpdate(sql.toString(), null);
		log.fine("User #"+no);
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
		synchronized(s_ccLock)
		{
			s_cc = cc;
		}

		s_cc.setDataSource();

		log.config(s_cc + " - DS=" + s_cc.isDataSource());
	//	Trace.printStack();
	}   //  setDBTarget

	/**
	 * Connect to database and initialise all connections.
	 * @return True if success, false otherwise
	 */
	public static boolean connect() {
		//direct connection
		boolean success =false;
		try
		{
            Connection connRW = getConnectionRW();
            if (connRW != null)
            {
                s_cc.readInfo(connRW);
                connRW.close();
            }

            Connection connRO = getConnectionRO();
            if (connRO != null)
            {
                connRO.close();
            }

            Connection connID = getConnectionID();
            if (connID != null)
            {
                connID.close();
            }
            success = ((connRW != null) && (connRO != null) && (connID != null));
		}
        catch (Exception e)
		{
        	//logging here could cause infinite loop
            //log.log(Level.SEVERE, "Could not connect to DB", e);
        	System.err.println("Could not connect to DB - " + e.getLocalizedMessage());
        	e.printStackTrace();
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

		//direct connection
		boolean success = false;
		CLogErrorBuffer eb = CLogErrorBuffer.get(false);
		if (eb != null && eb.isIssueError())
			eb.setIssueError(false);
		else
			eb = null;	//	don't reset
		try
		{
            Connection conn = getConnectionRW(createNew);   //  try to get a connection
            if (conn != null)
            {
                conn.close();
            }
            success = (conn != null);
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
        return createConnection(true, false, Connection.TRANSACTION_READ_COMMITTED);
	}   //  getConnectionRW

	/**
	 *	Return everytime a new r/w no AutoCommit, Serializable connection.
	 *	To be used to ID
	 *  @return Connection (r/w)
	 */
	public static Connection getConnectionID ()
	{
        return createConnection(false, false, Connection.TRANSACTION_READ_COMMITTED);
	}   //  getConnectionID

	/**
	 *	Return read committed, read/only from pool.
	 *  @return Connection (r/o)
	 */
	public static Connection getConnectionRO ()
	{
        return createConnection(true, true, Connection.TRANSACTION_READ_COMMITTED);     //  see below
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

		//hengsin: failed to set autocommit can lead to severe lock up of the system
        try {
	        if (conn != null && conn.getAutoCommit() != autoCommit)
	        {
	        	throw new IllegalStateException("Failed to set the requested auto commit mode on connection. [autoCommit=" + autoCommit +"]");
	        }
        } catch (SQLException e) {}

		return conn;
	}	//	createConnection

    /**
     *  Create new Connection.
     *  The connection must be closed explicitly by the application
     *
     *  @param autoCommit auto commit
     *  @param trxLevel - Connection.TRANSACTION_READ_UNCOMMITTED, Connection.TRANSACTION_READ_COMMITTED, Connection.TRANSACTION_REPEATABLE_READ, or Connection.TRANSACTION_READ_COMMITTED.
     *  @return Connection connection
     */
    public static Connection createConnection (boolean autoCommit, boolean readOnly, int trxLevel)
    {
        Connection conn = s_cc.getConnection (autoCommit, trxLevel);

        //hengsin: this could be problematic as it can be reuse for readwrite activites after return to pool
        /*
        if (conn != null)
        {
            try
            {
                conn.setReadOnly(readOnly);
            }
            catch (SQLException ex)
            {
                conn = null;
                log.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }*/

        if (conn == null)
        {
            throw new IllegalStateException("DB.getConnectionRO - @NoDBConnection@");
        }

        //hengsin: failed to set autocommit can lead to severe lock up of the system
        try {
	        if (conn.getAutoCommit() != autoCommit)
	        {
	        	throw new IllegalStateException("Failed to set the requested auto commit mode on connection. [autocommit=" + autoCommit +"]");
	        }
        } catch (SQLException e) {}

        return conn;
    }   //  createConnection

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
//    Check Version
        String version = "?";
        String sql = "SELECT Version FROM AD_System";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            pstmt = prepareStatement(sql, null);
            rs = pstmt.executeQuery();
            if (rs.next())
                version = rs.getString(1);
        }
        catch (SQLException e)
        {
            log.log(Level.SEVERE, "Problem with AD_System Table - Run system.sql script - " + e.toString());
            return false;
        }
        finally
        {
            close(rs);
            close(pstmt);
            rs= null;
            pstmt = null;
        }
        log.info("DB_Version=" + version);
        //  Identical DB version
        if (Adempiere.DB_VERSION.equals(version))
            return true;

        String AD_Message = "DatabaseVersionError";
        String title = org.compiere.Adempiere.getName() + " " +  Msg.getMsg(ctx, AD_Message, true);
        //  Code assumes Database version {0}, but Database has Version {1}.
        String msg = Msg.getMsg(ctx, AD_Message);   //  complete message
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
	 *  Check Build Version of Database against running client
	 *  @param ctx context
	 *  @return true if Database version (date) is the same
	 */
	public static boolean isBuildOK (Properties ctx)
	{
//    Check Build
        String buildClient = Adempiere.getImplementationVersion();
        String buildDatabase = "";
        boolean failOnBuild = false;
        String sql = "SELECT LastBuildInfo, IsFailOnBuildDiffer FROM AD_System";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            pstmt = prepareStatement(sql, null);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                buildDatabase = rs.getString(1);
                failOnBuild = rs.getString(2).equals("Y");
            }
        }
        catch (SQLException e)
        {
            log.log(Level.SEVERE, "Problem with AD_System Table - Run system.sql script - " + e.toString());
            return false;
        }
        finally
        {
            close(rs);
            close(pstmt);
            rs= null;
            pstmt = null;
        }
        log.info("Build DB=" + buildDatabase);
        log.info("Build Cl=" + buildClient);
        //  Identical DB version
        if (buildClient.equals(buildDatabase))
            return true;

        String AD_Message = "BuildVersionError";
        String title = org.compiere.Adempiere.getName() + " " +  Msg.getMsg(ctx, AD_Message, true);
        // The program assumes build version {0}, but database has build Version {1}.
        String msg = Msg.getMsg(ctx, AD_Message);   //  complete message
        msg = MessageFormat.format(msg, new Object[] {buildClient, buildDatabase});
        if (! failOnBuild) {
        	log.warning(msg);
        	return true;
        }
    	JOptionPane.showMessageDialog (null,
    			msg,
    			title, JOptionPane.ERROR_MESSAGE);
    	Env.exitEnv(1);
    	return false;
	}   //  isDatabaseOK


	/**************************************************************************
	 *	Close Target
	 */
	public static void closeTarget()
	{

        boolean closed = false;

        //  CConnection
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
		return ProxyFactory.newCCallableStatement(ResultSet.TYPE_FORWARD_ONLY, resultSetConcurrency, SQL,
				trxName);
	}	//	prepareCall


	/**************************************************************************
	 *	Prepare Statement
	 *  @param sql
	 *  @return Prepared Statement
	 *  @deprecated
	 */
	public static CPreparedStatement prepareStatement (String sql)
	{
		int concurrency = ResultSet.CONCUR_READ_ONLY;
		String upper = sql.toUpperCase();
		if (upper.startsWith("UPDATE ") || upper.startsWith("DELETE "))
			concurrency = ResultSet.CONCUR_UPDATABLE;
		return prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, concurrency, null);
	}	//	prepareStatement

	/**
	 *	Prepare Statement
	 *  @param sql
	 * 	@param trxName transaction
	 *  @return Prepared Statement
	 */
	public static CPreparedStatement prepareStatement (String sql, String trxName)
	{
		int concurrency = ResultSet.CONCUR_READ_ONLY;
		String upper = sql.toUpperCase();
		if (upper.startsWith("UPDATE ") || upper.startsWith("DELETE "))
			concurrency = ResultSet.CONCUR_UPDATABLE;
		return prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, concurrency, trxName);
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
		return ProxyFactory.newCPreparedStatement(resultSetType, resultSetConcurrency, sql, trxName);
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
		return ProxyFactory.newCStatement(resultSetType, resultSetConcurrency, trxName);
	}	//	createStatement

	/**
	 * Set parameters for given statement
	 * @param stmt statements
	 * @param params parameters array; if null or empty array, no parameters are set
	 */
	public static void setParameters(PreparedStatement stmt, Object[] params)
	throws SQLException
	{
		if (params == null || params.length == 0) {
			return;
		}
		//
		for (int i = 0; i < params.length; i++)
		{
			setParameter(stmt, i+1, params[i]);
		}
	}

	/**
	 * Set parameters for given statement
	 * @param stmt statements
	 * @param params parameters list; if null or empty list, no parameters are set
	 */
	public static void setParameters(PreparedStatement stmt, List<?> params)
	throws SQLException
	{
		if (params == null || params.size() == 0)
		{
			return;
		}
		for (int i = 0; i < params.size(); i++)
		{
			setParameter(stmt, i+1, params.get(i));
		}
	}


	/**
	 * Set PreparedStatement's parameter.
	 * Similar with calling <code>pstmt.setObject(index, param)</code>
	 * @param pstmt
	 * @param index
	 * @param param
	 * @throws SQLException
	 */
	public static void setParameter(PreparedStatement pstmt, int index, Object param)
	throws SQLException
	{
		if (param == null)
			pstmt.setObject(index, null);
		else if (param instanceof String)
			pstmt.setString(index, (String)param);
		else if (param instanceof Integer)
			pstmt.setInt(index, ((Integer)param).intValue());
		else if (param instanceof BigDecimal)
			pstmt.setBigDecimal(index, (BigDecimal)param);
		else if (param instanceof Timestamp)
			pstmt.setTimestamp(index, (Timestamp)param);
		else if (param instanceof Boolean)
			pstmt.setString(index, ((Boolean)param).booleanValue() ? "Y" : "N");
		else
			throw new DBException("Unknown parameter type "+index+" - "+param);
	}

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
		return executeUpdate(sql, trxName, 0);
	}	//	executeUpdate

	/**
	 *	Execute Update.
	 *  saves "DBExecuteError" in Log
	 *  @param sql sql
	 * 	@param trxName optional transaction name
	 *  @param timeOut optional timeout parameter
	 *  @return number of rows updated or -1 if error
	 */
	public static int executeUpdate (String sql, String trxName, int timeOut)
	{
		return executeUpdate(sql, null, false, trxName, timeOut);
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
		return executeUpdate (sql, ignoreError, trxName, 0);
	}	//	executeUpdate

	/**
	 *	Execute Update.
	 *  saves "DBExecuteError" in Log
	 *  @param sql sql
	 * 	@param ignoreError if true, no execution error is reported
	 * 	@param trxName transaction
	 *  @param timeOut optional timeOut parameter
	 *  @return number of rows updated or -1 if error
	 */
	public static int executeUpdate (String sql, boolean ignoreError, String trxName, int timeOut)
	{
		return executeUpdate (sql, null, ignoreError, trxName, timeOut);
	}

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
		return executeUpdate (sql, param, trxName, 0);
	}	//	executeUpdate

	/**
	 *	Execute Update.
	 *  saves "DBExecuteError" in Log
	 *  @param sql sql
	 *  @param param int param
	 * 	@param trxName transaction
	 *  @param timeOut optional timeOut parameter
	 *  @return number of rows updated or -1 if error
	 */
	public static int executeUpdate (String sql, int param, String trxName, int timeOut)
	{
		return executeUpdate (sql, new Object[]{new Integer(param)}, false, trxName, timeOut);
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
		return executeUpdate (sql, param, ignoreError, trxName, 0);
	}	//	executeUpdate

	/**
	 *	Execute Update.
	 *  saves "DBExecuteError" in Log
	 *  @param sql sql
	 *  @param param int parameter
	 * 	@param ignoreError if true, no execution error is reported
	 * 	@param trxName transaction
	 *  @param timeOut optional timeOut parameter
	 *  @return number of rows updated or -1 if error
	 */
	public static int executeUpdate (String sql, int param, boolean ignoreError, String trxName, int timeOut)
	{
		return executeUpdate (sql, new Object[]{new Integer(param)}, ignoreError, trxName, timeOut);
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
		return executeUpdate(sql, params, ignoreError, trxName, 0);
	}

	/**
	 *	Execute Update.
	 *  saves "DBExecuteError" in Log
	 *  @param sql sql
	 *  @param params array of parameters
	 * 	@param ignoreError if true, no execution error is reported
	 * 	@param trxName optional transaction name
	 *  @param timeOut optional timeOut parameter
	 *  @return number of rows updated or -1 if error
	 */
	public static int executeUpdate (String sql, Object[] params, boolean ignoreError, String trxName, int timeOut)
	{
		if (sql == null || sql.length() == 0)
			throw new IllegalArgumentException("Required parameter missing - " + sql);
		//
		int no = -1;
		CPreparedStatement cs = ProxyFactory.newCPreparedStatement(ResultSet.TYPE_FORWARD_ONLY,
			ResultSet.CONCUR_UPDATABLE, sql, trxName);	//	converted in call

		try
		{
			setParameters(cs, params);
			if (timeOut > 0)
				cs.setQueryTimeout(timeOut);
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
		catch (Exception e)
		{
			e = getSQLException(e);
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
	 * Execute Update and throw exception.
	 * @param sql
	 * @param params statement parameters
	 * @param trxName transaction
	 * @return number of rows updated
	 * @throws SQLException
	 */
	public static int executeUpdateEx (String sql, Object[] params, String trxName) throws DBException
	{
		return executeUpdateEx(sql, params, trxName, 0);
	}

	/**
	 * Execute Update and throw exception.
	 * @param sql
	 * @param params statement parameters
	 * @param trxName transaction
	 * @param timeOut optional timeOut parameter
	 * @return number of rows updated
	 * @throws SQLException
	 */
	public static int executeUpdateEx (String sql, Object[] params, String trxName, int timeOut) throws DBException
	{
		if (sql == null || sql.length() == 0)
			throw new IllegalArgumentException("Required parameter missing - " + sql);
		//
		int no = -1;
		CPreparedStatement cs = ProxyFactory.newCPreparedStatement(ResultSet.TYPE_FORWARD_ONLY,
			ResultSet.CONCUR_UPDATABLE, sql, trxName);	//	converted in call

		try
		{
			setParameters(cs, params);
			if (timeOut > 0)
				cs.setQueryTimeout(timeOut);
			no = cs.executeUpdate();
			//	No Transaction - Commit
			if (trxName == null)
			{
				cs.commit();	//	Local commit
			}
		}
		catch (Exception e)
		{
			throw new DBException(e);
		}
		finally
		{
			DB.close(cs);
		}
		return no;
	}

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
	 * Execute Update and throw exception.
	 * @see {@link #executeUpdateEx(String, Object[], String)}
	 */
	public static int executeUpdateEx (String sql, String trxName) throws DBException
	{
		return executeUpdateEx(sql, trxName, 0);
	}	//	executeUpdateEx

	/**
	 * Execute Update and throw exception.
	 * @see {@link #executeUpdateEx(String, Object[], String)}
	 */
	public static int executeUpdateEx (String sql, String trxName, int timeOut) throws DBException
	{
		return executeUpdateEx(sql, null, trxName, timeOut);
	}	//	executeUpdateEx

	/**
	 *	Commit - commit on RW connection.
	 *  Is not required as RW connection is AutoCommit (exception: with transaction)
	 *  @param throwException if true, re-throws exception
	 * 	@param trxName transaction name
	 *  @return true if not needed or success
	 *  @throws SQLException
	 */
	public static boolean commit (boolean throwException, String trxName) throws SQLException,IllegalStateException
	{
        // Not on transaction scope, Connection are thus auto commit
        if (trxName == null)
        {
            return true;
        }

		try
		{
			Trx trx = Trx.get(trxName, false);
			if (trx != null)
				return trx.commit(true);

            if (throwException)
            {
                throw new IllegalStateException("Could not load transation with identifier: " + trxName);
            }
            else
            {
                return false;
            }
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "[" + trxName + "]", e);
			if (throwException)
				throw e;
			return false;
		}
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
		// Bugfix Gunther Hoppe, 02.09.2005, vpj-cd e-evolution
		CStatementVO info = new CStatementVO (RowSet.TYPE_SCROLL_INSENSITIVE, RowSet.CONCUR_READ_ONLY, DB.getDatabase().convertStatement(sql));
		CPreparedStatement stmt = ProxyFactory.newCPreparedStatement(info);
		RowSet retValue = stmt.getRowSet();
		close(stmt);
		return retValue;
	}	//	getRowSet

    /**
     * Get int Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params array of parameters
     * @return first value or -1 if not found
     * @throws DBException if there is any SQLException
     */
    public static int getSQLValueEx (String trxName, String sql, Object... params) throws DBException
    {
    	int retValue = -1;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try
    	{
    		pstmt = prepareStatement(sql, trxName);
    		setParameters(pstmt, params);
    		rs = pstmt.executeQuery();
    		if (rs.next())
    			retValue = rs.getInt(1);
    		else
    			log.info("No Value " + sql);
    	}
    	catch (SQLException e)
    	{
    		throw new DBException(e, sql);
    	}
    	finally
    	{
    		close(rs, pstmt);
    		rs = null; pstmt = null;
    	}
    	return retValue;
    }

    /**
     * Get String Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params collection of parameters
     * @return first value or -1
     * @throws DBException if there is any SQLException
     */
    public static int getSQLValueEx (String trxName, String sql, Collection<Object> params)
    {
		return getSQLValueEx(trxName, sql, params.toArray(new Object[params.size()]));
    }

    /**
     * Get int Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params array of parameters
     * @return first value or -1 if not found or error
     */
    public static int getSQLValue (String trxName, String sql, Object... params)
    {
    	int retValue = -1;
    	try
    	{
    		retValue = getSQLValueEx(trxName, sql, params);
    	}
    	catch (Exception e)
    	{
    		log.log(Level.SEVERE, sql, getSQLException(e));
    	}
    	return retValue;
    }

    /**
     * Get int Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params collection of parameters
     * @return first value or null
     */
    public static int getSQLValue (String trxName, String sql, Collection<Object> params)
    {
		return getSQLValue(trxName, sql, params.toArray(new Object[params.size()]));
    }

    /**
     * Get String Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params array of parameters
     * @return first value or null
     * @throws DBException if there is any SQLException
     */
    public static String getSQLValueStringEx (String trxName, String sql, Object... params)
    {
    	String retValue = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try
    	{
    		pstmt = prepareStatement(sql, trxName);
    		setParameters(pstmt, params);
    		rs = pstmt.executeQuery();
    		if (rs.next())
    			retValue = rs.getString(1);
    		else
    			log.info("No Value " + sql);
    	}
    	catch (SQLException e)
    	{
    		throw new DBException(e, sql);
    	}
    	finally
    	{
    		close(rs, pstmt);
    		rs = null; pstmt = null;
    	}
    	return retValue;
    }

    /**
     * Get String Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params collection of parameters
     * @return first value or null
     * @throws DBException if there is any SQLException
     */
    public static String getSQLValueStringEx (String trxName, String sql, Collection<Object> params)
    {
		return getSQLValueStringEx(trxName, sql, params.toArray(new Object[params.size()]));
    }

    /**
     * Get String Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params array of parameters
     * @return first value or null
     */
    public static String getSQLValueString (String trxName, String sql, Object... params)
    {
    	String retValue = null;
    	try
    	{
    		retValue = getSQLValueStringEx(trxName, sql, params);
    	}
    	catch (Exception e)
    	{
    		log.log(Level.SEVERE, sql, getSQLException(e));
    	}
    	return retValue;
    }

    /**
     * Get String Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params collection of parameters
     * @return first value or null
     */
    public static String getSQLValueString (String trxName, String sql, Collection<Object> params)
    {
		return getSQLValueString(trxName, sql, params.toArray(new Object[params.size()]));
    }

    /**
     * Get BigDecimal Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params array of parameters
     * @return first value or null if not found
     * @throws DBException if there is any SQLException
     */
    public static BigDecimal getSQLValueBDEx (String trxName, String sql, Object... params) throws DBException
    {
    	BigDecimal retValue = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try
    	{
    		pstmt = prepareStatement(sql, trxName);
    		setParameters(pstmt, params);
    		rs = pstmt.executeQuery();
    		if (rs.next())
    			retValue = rs.getBigDecimal(1);
    		else
    			log.info("No Value " + sql);
    	}
    	catch (SQLException e)
    	{
    		//log.log(Level.SEVERE, sql, getSQLException(e));
    		throw new DBException(e, sql);
    	}
    	finally
    	{
    		close(rs, pstmt);
    		rs = null; pstmt = null;
    	}
    	return retValue;
    }

    /**
     * Get BigDecimal Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params collection of parameters
     * @return first value or null if not found
     * @throws DBException if there is any SQLException
     */
    public static BigDecimal getSQLValueBDEx (String trxName, String sql, Collection<Object> params) throws DBException
    {
		return getSQLValueBDEx(trxName, sql, params.toArray(new Object[params.size()]));
    }


    /**
     * Get BigDecimal Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params array of parameters
     * @return first value or null
     */
    public static BigDecimal getSQLValueBD (String trxName, String sql, Object... params)
    {
    	try
    	{
    		return getSQLValueBDEx(trxName, sql, params);
    	}
    	catch (Exception e)
    	{
    		log.log(Level.SEVERE, sql, getSQLException(e));
    	}
    	return null;
    }


    /**
     * Get BigDecimal Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params collection of parameters
     * @return first value or null
     */
    public static BigDecimal getSQLValueBD (String trxName, String sql, Collection<Object> params)
    {
		return getSQLValueBD(trxName, sql, params.toArray(new Object[params.size()]));
    }

    /**
     * Get Timestamp Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params array of parameters
     * @return first value or null
     * @throws DBException if there is any SQLException
     */
    public static Timestamp getSQLValueTSEx (String trxName, String sql, Object... params)
    {
    	Timestamp retValue = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try
    	{
    		pstmt = prepareStatement(sql, trxName);
    		setParameters(pstmt, params);
    		rs = pstmt.executeQuery();
    		if (rs.next())
    			retValue = rs.getTimestamp(1);
    		else
    			log.info("No Value " + sql);
    	}
    	catch (SQLException e)
    	{
    		throw new DBException(e, sql);
    	}
    	finally
    	{
    		close(rs, pstmt);
    		rs = null; pstmt = null;
    	}
    	return retValue;
    }

    /**
     * Get BigDecimal Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params collection of parameters
     * @return first value or null if not found
     * @throws DBException if there is any SQLException
     */
    public static Timestamp getSQLValueTSEx (String trxName, String sql, Collection<Object> params) throws DBException
    {
		return getSQLValueTSEx(trxName, sql, params.toArray(new Object[params.size()]));
    }

    /**
     * Get Timestamp Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params array of parameters
     * @return first value or null
     */
    public static Timestamp getSQLValueTS (String trxName, String sql, Object... params)
    {
    	try
    	{
    		return getSQLValueTSEx(trxName, sql, params);
    	}
    	catch (Exception e)
    	{
    		log.log(Level.SEVERE, sql, getSQLException(e));
    	}
    	return null;
    }

    /**
     * Get Timestamp Value from sql
     * @param trxName trx
     * @param sql sql
     * @param params collection of parameters
     * @return first value or null
     */
    public static Timestamp getSQLValueTS (String trxName, String sql, Collection<Object> params)
    {
		Object[] arr = new Object[params.size()];
		params.toArray(arr);
		return getSQLValueTS(trxName, sql, arr);
    }

	/**
	 * Get Array of Key Name Pairs
	 * @param sql select with id / name as first / second column
	 * @param optional if true (-1,"") is added
	 * @return array of {@link KeyNamePair}
	 * @see #getKeyNamePairs(String, boolean, Object...)
	 */
	public static KeyNamePair[] getKeyNamePairs(String sql, boolean optional)
	{
		return getKeyNamePairs(sql, optional, (Object[])null);
	}

	/**
	 * Get Array of Key Name Pairs
	 * @param sql select with id / name as first / second column
	 * @param optional if true (-1,"") is added
	 * @param params query parameters
	 * @return array of {@link KeyNamePair}
	 * @see #getKeyNamePairs(String, boolean, Object...)
	 */
	public static KeyNamePair[] getKeyNamePairs(String sql, boolean optional, Collection<Object> params)
	{
		return getKeyNamePairs(sql, optional, params.toArray(new Object[params.size()]));
	}

	/**
	 * Get Array of Key Name Pairs
	 * @param sql select with id / name as first / second column
	 * @param optional if true (-1,"") is added
	 * @param params query parameters
	 */
	public static KeyNamePair[] getKeyNamePairs(String sql, boolean optional, Object ... params)
	{
		return getKeyNamePairs(null, sql, optional, params);
	}
	
	/**
	 * Get Array of Key Name Pairs
	 * @param trxName
	 * @param sql select with id / name as first / second column
	 * @param optional if true (-1,"") is added
	 * @param params query parameters
	 */
	public static KeyNamePair[] getKeyNamePairs(String trxName, String sql, boolean optional, Object ... params)
	{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
        if (optional)
        {
            list.add (new KeyNamePair(-1, ""));
        }
        try
        {
            pstmt = DB.prepareStatement(sql, trxName);
            setParameters(pstmt, params);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                list.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
            }
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, sql, getSQLException(e));
        }
        finally
        {
            close(rs, pstmt);
            rs= null;
            pstmt = null;
        }
        KeyNamePair[] retValue = new KeyNamePair[list.size()];
        list.toArray(retValue);
    //  s_log.fine("getKeyNamePairs #" + retValue.length);
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
        ResultSet rs = null;
        try
        {
            pstmt = DB.prepareStatement (sql, null);
            rs = pstmt.executeQuery ();
            if (rs.next ())
                isSOTrx = "Y".equals(rs.getString(1));
        }
        catch (Exception e)
        {
            if (TableName.endsWith("Line"))
            {
                String hdr = TableName.substring(0, TableName.indexOf("Line"));
                // use IN instead of EXISTS as the subquery should be highly selective
                sql = "SELECT IsSOTrx FROM " + hdr
                    + " h WHERE h." + hdr + "_ID IN (SELECT l." + hdr + "_ID FROM " + TableName
                    + " l WHERE " + whereClause + ")";
                PreparedStatement pstmt2 = null;
                ResultSet rs2 = null;
                try
                {
                    pstmt2 = DB.prepareStatement (sql, null);
                    rs2 = pstmt2.executeQuery ();
                    if (rs2.next ())
                        isSOTrx = "Y".equals(rs2.getString(1));
                }
                catch (Exception ee)
                {
                	ee = getSQLException(ee);
                    log.log(Level.FINEST, sql + " - " + e.getMessage(), ee);
                }
                finally
                {
                    close(rs2, pstmt2);
                    rs= null;
                    pstmt = null;
                }
            }
            else
            {
                log.log(Level.FINEST, TableName + " - No SOTrx", e);
            }
        }
        finally
        {
            close(rs);
            close(pstmt);
            rs= null;
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
		boolean SYSTEM_NATIVE_SEQUENCE = MSysConfig.getBooleanValue("SYSTEM_NATIVE_SEQUENCE",false);
		boolean adempiereSys = Ini.isPropertyBool(Ini.P_ADEMPIERESYS);

		if(SYSTEM_NATIVE_SEQUENCE && !adempiereSys)
		{
			int m_sequence_id = CConnection.get().getDatabase().getNextID(TableName+"_SEQ");
			return m_sequence_id;
		}

		return MSequence.getNextID (AD_Client_ID, TableName, trxName);
	}	//	getNextID

	/**
	 * 	Get Document No based on Document Type (backward compatibility)
	 *	@param C_DocType_ID document type
	 * 	@param trxName optional Transaction Name
	 *	@return document no or null
	 *  @deprecated
	 */
	public static String getDocumentNo(int C_DocType_ID, String trxName)
	{
		return MSequence.getDocumentNo (C_DocType_ID, trxName, false);
	}	//	getDocumentNo

	/**
	 * 	Get Document No based on Document Type
	 *	@param C_DocType_ID document type
	 * 	@param trxName optional Transaction Name
	 *  @param definite asking for a definitive or temporary sequence
	 *	@return document no or null
	 */
	public static String getDocumentNo(int C_DocType_ID, String trxName, boolean definite) {
		return getDocumentNo(C_DocType_ID, trxName, definite, null);
	}

	/**
	 * 	Get Document No based on Document Type
	 *	@param C_DocType_ID document type
	 * 	@param trxName optional Transaction Name
	 *  @param definite asking for a definitive or temporary sequence
	 *  @param PO
	 *	@return document no or null
	 */
	public static String getDocumentNo(int C_DocType_ID, String trxName, boolean definite, PO po)
	{
		return MSequence.getDocumentNo (C_DocType_ID, trxName, definite, po);
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
		return getDocumentNo(AD_Client_ID, TableName, trxName, null);
	}

	/**
	 * 	Get Document No from table
	 *	@param AD_Client_ID client
	 *	@param TableName table name
	 * 	@param trxName optional Transaction Name
	 *  @param po
	 *	@return document no or null
	 */
	public static String getDocumentNo (int AD_Client_ID, String TableName, String trxName, PO po)
	{
		String dn = MSequence.getDocumentNo (AD_Client_ID, TableName, trxName, po);
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

		String retValue = getDocumentNo (C_DocType_ID, trxName, false);
		if (!onlyDocType && retValue == null)
			return getDocumentNo (AD_Client_ID, TableName, trxName);
		return retValue;
	}	//	getDocumentNo

	/**
	 * 	Is this a remote client connection.
	 *
	 *  Deprecated, always return false.
	 *	@return true if client and RMI or Objects on Server
	 *  @deprecated
	 */
	public static boolean isRemoteObjects()
	{
		return false;
	}	//	isRemoteObjects

	/**
	 * 	Is this a remote client connection
	 *
	 *  Deprecated, always return false.
	 *	@return true if client and RMI or Process on Server
	 *  @deprecated
	 */
	public static boolean isRemoteProcess()
	{
		return false;
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
	 *  @return TRIM(TO_CHAR(columnName,'999G999G999G990D00','NLS_NUMERIC_CHARACTERS='',.'''))
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
	public static String TO_NUMBER (BigDecimal number, int displayType)
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

	/**
	 * convenient method to close result set
	 * @param rs
	 */
	public static void close( ResultSet rs) {
        try {
            if (rs!=null) rs.close();
        } catch (SQLException e) {
            ;
        }
    }

	/**
	 * convenient method to close statement
	 * @param st
	 */
    public static void close( Statement st) {
        try {
            if (st!=null) st.close();
        } catch (SQLException e) {
            ;
        }
    }

    /**
     * convenient method to close result set and statement
     * @param rs result set
     * @param st statement
     * @see #close(ResultSet)
     * @see #close(Statement)
     */
    public static void close(ResultSet rs, Statement st) {
    	close(rs);
    	close(st);
    }

    /**
     * convenient method to close a {@link POResultSet}
     * @param rs result set
     * @see POResultSet#close()
     */
    public static void close(POResultSet<?> rs) {
    	if (rs != null)
    		rs.close();
    }

	/**
	 * Try to get the SQLException from Exception
	 * @param e Exception
	 * @return SQLException if found or provided exception elsewhere
	 */
    public static Exception getSQLException(Exception e)
    {
    	Throwable e1 = e;
    	while (e1 != null)
    	{
	    	if (e1 instanceof SQLException)
	    		return (SQLException)e1;
	    	e1 = e1.getCause();
    	}
    	return e;
    }

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


    // Following methods are kept for BeanShell compatibility.
	// See BF [ 2030233 ] Remove duplicate code from DB class
    // TODO: remove this when BeanShell will support varargs methods
    public static int getSQLValue (String trxName, String sql)
    {
    	return getSQLValue(trxName, sql, new Object[]{});
    }
    public static int getSQLValue (String trxName, String sql, int int_param1)
    {
    	return getSQLValue(trxName, sql, new Object[]{int_param1});
    }
    public static int getSQLValue (String trxName, String sql, int int_param1, int int_param2)
    {
    	return getSQLValue(trxName, sql, new Object[]{int_param1, int_param2});
    }
    public static int getSQLValue (String trxName, String sql, String str_param1)
    {
    	return getSQLValue(trxName, sql, new Object[]{str_param1});
    }
    public static int getSQLValue (String trxName, String sql, int int_param1, String str_param2)
    {
    	return getSQLValue(trxName, sql, new Object[]{int_param1, str_param2});
    }
    public static String getSQLValueString (String trxName, String sql, int int_param1)
    {
    	return getSQLValueString(trxName, sql, new Object[]{int_param1});
    }
    public static BigDecimal getSQLValueBD (String trxName, String sql, int int_param1)
    {
    	return getSQLValueBD(trxName, sql, new Object[]{int_param1});
    }

	/**
	 * Get Array of ValueNamePair items.
	 * <pre> Example:
	 * String sql = "SELECT Name, Description FROM AD_Ref_List WHERE AD_Reference_ID=?";
	 * ValueNamePair[] list = DB.getValueNamePairs(sql, false, params);
	 * </pre>
	 * @param sql SELECT Value_Column, Name_Column FROM ...
	 * @param optional if {@link ValueNamePair#EMPTY} is added
	 * @param params query parameters
	 * @return array of {@link ValueNamePair} or empty array
     * @throws DBException if there is any SQLException
	 */
	public static ValueNamePair[] getValueNamePairs(String sql, boolean optional, List<Object> params)
	{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ValueNamePair> list = new ArrayList<ValueNamePair>();
        if (optional)
        {
            list.add (ValueNamePair.EMPTY);
        }
        try
        {
            pstmt = DB.prepareStatement(sql, null);
            setParameters(pstmt, params);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                list.add(new ValueNamePair(rs.getString(1), rs.getString(2)));
            }
        }
        catch (SQLException e)
        {
            throw new DBException(e, sql);
        }
        finally
        {
            close(rs, pstmt);
            rs = null; pstmt = null;
        }
		return list.toArray(new ValueNamePair[list.size()]);
	}

	/**
	 * Get Array of KeyNamePair items.
	 * <pre> Example:
	 * String sql = "SELECT C_City_ID, Name FROM C_City WHERE C_City_ID=?";
	 * KeyNamePair[] list = DB.getKeyNamePairs(sql, false, params);
	 * </pre>
	 * @param sql SELECT ID_Column, Name_Column FROM ...
	 * @param optional if {@link ValueNamePair#EMPTY} is added
	 * @param params query parameters
	 * @return array of {@link KeyNamePair} or empty array
     * @throws DBException if there is any SQLException
	 */
	public static KeyNamePair[] getKeyNamePairs(String sql, boolean optional, List<Object> params)
	{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
        if (optional)
        {
            list.add (KeyNamePair.EMPTY);
        }
        try
        {
            pstmt = DB.prepareStatement(sql, null);
            setParameters(pstmt, params);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                list.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
            }
        }
        catch (SQLException e)
        {
            throw new DBException(e, sql);
        }
        finally
        {
            close(rs, pstmt);
            rs = null; pstmt = null;
        }
		return list.toArray(new KeyNamePair[list.size()]);
	}
	
	/**
	 * Create persistent selection in T_Selection table
	 * @param AD_PInstance_ID
	 * @param selection
	 * @param trxName
	 */
	public static void createT_Selection(int AD_PInstance_ID, Collection<Integer> selection, String trxName)
	{
		StringBuffer insert = new StringBuffer();
		insert.append("INSERT INTO T_SELECTION(AD_PINSTANCE_ID, T_SELECTION_ID) ");
		int counter = 0;
		for(Integer selectedId : selection)
		{
			counter++;
			if (counter > 1)
				insert.append(" UNION ");
			insert.append("SELECT ");
			insert.append(AD_PInstance_ID);
			insert.append(", ");
			insert.append(selectedId);
			insert.append(" FROM DUAL ");
			
			if (counter >= 1000) 
			{
				DB.executeUpdateEx(insert.toString(), trxName);
				insert = new StringBuffer();
				insert.append("INSERT INTO T_SELECTION(AD_PINSTANCE_ID, T_SELECTION_ID) ");
				counter = 0;
			}
		}
		if (counter > 0)
		{
			DB.executeUpdateEx(insert.toString(), trxName);
		}
	}
}	//	DB

