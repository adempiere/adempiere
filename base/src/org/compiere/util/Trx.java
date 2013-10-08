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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

import org.adempiere.ad.service.ITrxBL;
import org.adempiere.ad.service.ITrxListenerManager;
import org.adempiere.ad.service.impl.NullTrxListenerManager;
import org.adempiere.ad.service.impl.TrxListenerManager;
import org.adempiere.util.Services;
import org.adempiere.util.trxConstraints.api.IOpenTrxBL;

/**
 *	Transaction Management.
 *	- Create new Transaction by Trx.get(name);
 *	- ..transactions..
 *	- commit();
 *	----	start();
 *	----	commit();
 *	- close();
 *	
 *  @author Jorg Janke
 *  @author Low Heng Sin
 *  - added rollback(boolean) and commit(boolean) [20070105]
 *  - remove unnecessary use of savepoint
 *  - use UUID for safer transaction name generation
 *  @author Teo Sarca, http://www.arhipac.ro
 *  			<li>FR [ 2080217 ] Implement TrxRunnable
 *  			<li>BF [ 2876927 ] Oracle JDBC driver problem
 *  				https://sourceforge.net/tracker/?func=detail&atid=879332&aid=2876927&group_id=176962
 *  @author Teo Sarca, teo.sarca@gmail.com
 *  		<li>BF [ 2849122 ] PO.AfterSave is not rollback on error - add releaseSavepoint method
 *  			https://sourceforge.net/tracker/index.php?func=detail&aid=2849122&group_id=176962&atid=879332#
 */
public class Trx implements VetoableChangeListener
{
	/** trxName=null marker */
	// metas
	public static final String TRXNAME_None = null;
	/**
	 * 	Get Transaction
	 *	@param trxName trx name
	 *	@param createNew if false, null is returned if not found
	 *	@return Transaction or null
	 */
	public static synchronized Trx get (String trxName, boolean createNew)
	{
		if (trxName == null || trxName.length() == 0)
			throw new IllegalArgumentException ("No Transaction Name");

		if (s_trxMap == null)
		{
			s_trxMap = new HashMap<String,Trx>(10);	//	no expiration
		}
		
		Trx retValue = (Trx)s_trxMap.get(trxName);
		if (retValue == null && createNew)
		{
			retValue = new Trx (trxName);
			Services.get(IOpenTrxBL.class).onNewTrx(retValue); // metas me00_02367
			s_trxMap.put(trxName, retValue);
		}
		return retValue;
	}	//	get
	
	/**	Transaction Cache					*/
	// metas: R.Craciunescu@metas.ro : mo73_03339
	// in Trx name we will use Map instead of Cache, because it's not a cache at all:
	// private static CCache<String,Trx> 	s_cache = null;	//	create change listener
	
	private static Map<String, Trx> s_trxMap = new HashMap<String, Trx>();
	
	/**
	 * 	Create unique Transaction Name <b>and instantly create the new trx</b>.
	 *	@param prefix optional prefix
	 *	@return unique name
	 */
	public static String createTrxName (final String prefix)
	{
		// calling with createNew == true because that is the old (default) behavior of this method
		return createTrxName(prefix, true);
	}
	
	/**
	 * 	Create unique Transaction Name
	 *	@param prefix optional prefix
	 *
	 *	@return unique name
	 */
	public static String createTrxName (String prefix, final boolean createNew)
	{
		if (prefix == null || prefix.length() == 0)
			prefix = "Trx";
		prefix += "_" + UUID.randomUUID(); //System.currentTimeMillis();
		
		if(createNew)
		{
			//create transaction entry
			Trx.get(prefix, true);
		}
		return prefix;
	}	//	createTrxName

	/**
	 * 	Create unique Transaction Name
	 *	@return unique name
	 */
	public static String createTrxName ()
	{
		return createTrxName(null);
	}	//	createTrxName
	
	
	/**************************************************************************
	 * 	Transaction Constructor
	 * 	@param trxName unique name
	 */
	private Trx (String trxName)
	{
		this (trxName, null);
	}	//	Trx

	/**
	 * 	Transaction Constructor
	 * 	@param trxName unique name
	 *  @param con optional connection ( ignore for remote transaction )
	 * 	 */
	private Trx (String trxName, Connection con)
	{
	//	log.info (trxName);
		setTrxName (trxName);
		setConnection (con);
	}	//	Trx

	/** Logger					*/
	private CLogger 		log = CLogger.getCLogger(getClass());
	
	private	Connection 	m_connection = null;
	private	String 		m_trxName = null;
	private boolean		m_active = false;

	private long m_startTime;

	/**
	 * Get connection
	 * @return connection
	 */
	public Connection getConnection()
	{
		return getConnection(true);
	}

	/**
	 * 	Get Connection
	 *  @param createNew if true, create new connection if the trx does not have one created yet
	 *	@return connection
	 */
	public Connection getConnection(boolean createNew)
	{
		log.log(Level.ALL, "Active=" + isActive() + ", Connection=" + m_connection);
		
		// metas: tsa: begin: Handle the case when the connection was already closed
		// Example: one case when we can get this is when we start a process with a transaction
		// and that process is commiting the transaction somewhere
		if (m_connection != null)
		{
			boolean isClosed = false;
			try
			{
				isClosed = m_connection.isClosed();
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, "Error checking if the connection is closed. Assume closed - " + e.getLocalizedMessage(), e);
				isClosed = true;
			}
			if (isClosed)
			{
				log.info("Connection is closed. Trying to create another connection.");
				m_connection = null;
			}
		}
		// metas: tsa: end:
		
		if (m_connection == null)	//	get new Connection
		{
			if (createNew)
			{
				if (s_trxMap == null || !s_trxMap.containsKey(m_trxName))
				{
					new Exception("Illegal to getConnection for Trx that is not register.").printStackTrace();
					return null;
				}
				setConnection(DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED));
			}
			else
				return null;
		}
		if (!isActive())
			start();
		return m_connection;
	}	//	getConnection

	/**
	 * 	Set Connection
	 *	@param conn connection
	 */
	private void setConnection (Connection conn)
	{
		if (conn == null)
			return;
		m_connection = conn;
		log.finest("Connection=" + conn);
		try
		{
			m_connection.setAutoCommit(false);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "connection", e);
		}
	}	//	setConnection

	/**
	 * 	Set Trx Name
	 *	@param trxName transaction name
	 */
	private void setTrxName (String trxName)
	{
		if (trxName == null || trxName.length() == 0)
			throw new IllegalArgumentException ("No Transaction Name");
		m_trxName = trxName;
	}	//	setName

	/**
	 * 	Get Name
	 *	@return name
	 */
	public String getTrxName()
	{
		return m_trxName;
	}	//	getName

	/**
	 * 	Start Trx
	 *	@return true if trx started
	 */
	public boolean start()
	{
		if (m_active)
		{
			log.warning("Trx in progress " + m_trxName);
			return false;
		}
		m_active = true;
		m_startTime = System.currentTimeMillis();
		return true;
	}	//	startTrx

	/**
	 * @return The start time of this transaction
	 */
	public Date getStartTime()
	{
		return new Date(m_startTime);
	}
	
	/**
	 * 	Transaction is Active
	 *	@return true if transaction active  
	 */
	public boolean isActive()
	{
		return m_active;
	}	//	isActive

	/**
	 * 	Rollback
	 *  @param throwException if true, re-throws exception
	 *	@return true if success, false if failed or transaction already rollback
	 */
	public boolean rollback(boolean throwException) throws SQLException
	{
		//local
		try
		{
			if (m_connection != null)
			{
				m_connection.rollback();
				log.info ("**** " + m_trxName);
				m_active = false;
				return true;
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, m_trxName, e);
			if (throwException)
			{
				m_active = false;
				throw e;
			}
		}		
		m_active = false;
		return false;
	}	//	rollback
	
	/**
	 * Rollback 
	 * @return true if success, false if failed or transaction already rollback
	 */
	public boolean rollback()
	{
		try {
			return rollback(false);
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * 	Rollback
	 *  @param throwException if true, re-throws exception
	 *	@return true if success, false if failed or transaction already rollback
	 */
	public boolean rollback(Savepoint savepoint) throws SQLException
	{
		try
		{
			return rollback0(savepoint);
		}
		finally
		{
			Services.get(IOpenTrxBL.class).onRollback(this); // metas me00_02367
		}
	}
	
	private boolean rollback0(Savepoint savepoint) throws SQLException
	// metas: end: me00_02367
	{
		//local
		try
		{
			if (m_connection != null)
			{
				m_connection.rollback(savepoint);
				log.info ("**** " + m_trxName);
				return true;
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, m_trxName, e);
			throw e;
		}		
		return false;
	}	//	rollback
	
	/**
	 * Commit
	 * @param throwException if true, re-throws exception
	 * @return true if success
	 **/
	public boolean commit(boolean throwException) throws SQLException
	// metas: begin: me00_02367
	{
		boolean success = false;
		try
		{
			success = commit0(throwException);
			return success;
		}
		finally
		{
			Services.get(IOpenTrxBL.class).onCommit(this); // metas me00_02367

			// mo73_04265: If transaction was successfully committed fire listeners
			if (success)
	{
				getTrxListenerManager(false).fireAfterCommit(getTrxName());
			}
		}
	}
	private boolean commit0(boolean throwException) throws SQLException {
	// metas: end: me00_02367
		//local
		try
		{
			if (m_connection != null)
			{
				m_connection.commit();
				log.log(isLocalTrx(m_trxName) ? Level.FINE : Level.INFO, "**** " + m_trxName);
				m_active = false;
				return true;
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, m_trxName, e);
			if (throwException) 
			{
				m_active = false;
				throw e;
			}
		}
		m_active = false;
		return false;
	}	//	commit
	
	/**
	 * Commit
	 * @return true if success
	 */
	public boolean commit()
	{
		try 
		{
			return commit(false);
		} 
		catch(SQLException e) 
		{
			return false;
		}
	}
	

	/**
	 * 	End Transaction and Close Connection
	 *	@return true if success
	 */
	public synchronized boolean close()
	{
		// metas: begin: me00_02367
		try
		{
			return close0();
		}
		finally
		{
			Services.get(IOpenTrxBL.class).onClose(this); // metas me00_02367
		}
	}
		
	private synchronized boolean close0() 
	{
		// metas: end: me00_02367
		if (s_trxMap != null)
			s_trxMap.remove(getTrxName());
		
		//local
		if (m_connection == null)
			return true;
		
		if (isActive())
			commit();
			
		//	Close Connection
		try
		{
			m_connection.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, m_trxName, e);
		}
		m_connection = null;
		m_active = false;
		log.config(m_trxName);
		return true;
	}	//	close
	
	/**
	 * 
	 * @param name
	 * @return Savepoint
	 * @throws SQLException
	 */
	public Savepoint setSavepoint(String name) throws SQLException {
		if (m_connection == null) 
			getConnection();
		
		if(m_connection != null) {
			
			final Savepoint result;
			if (name != null)
				result = m_connection.setSavepoint(name);
			else
				result = m_connection.setSavepoint();
			
			Services.get(IOpenTrxBL.class).onSetSavepoint(this, result); // metas me00_02367
			return result;
			
		} else {
			return null;
		}
	}
	
	/**
	 * Release Savepoint
	 * @param savepoint
	 * @throws SQLException
	 * @see {@link Connection#releaseSavepoint(Savepoint)}
	 */
	public void releaseSavepoint(Savepoint savepoint) throws SQLException
	{
		if (DB.isOracle())
		{
			// Note: As of Oracle Database 10g, releaseSavepoint and
			// oracleReleaseSavepoint are not supported. If you call either
			// of the methods, then SQLException is thrown with the message
			// "Unsupported feature".
			// -- 4-4 Oracle Database JDBC Developer's Guide and Reference
			return;
		}
		if (m_connection == null) 
		{
			getConnection();
		}
		if(m_connection != null)
		{
			m_connection.releaseSavepoint(savepoint);
			Services.get(IOpenTrxBL.class).onReleaseSavepoint(this, savepoint); // metas me00_02367
		}
		
	}
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("Trx[");
		sb.append(getTrxName())
			.append(",Active=").append(isActive())
			.append("]");
		return sb.toString();
	}	//	toString

	/**
	 * 	Vetoable Change.
	 * 	Called from CCache to close connections
	 *	@param evt event
	 *	@throws PropertyVetoException
	 */
	public void vetoableChange (PropertyChangeEvent evt)
		throws PropertyVetoException
	{
		log.info(evt.toString());
		throw new PropertyVetoException("Skip reset for trx entries cache", evt);
	}	//	vetoableChange	
	
	/**
	 * @return Trx[]
	 */
	public static Trx[] getActiveTransactions()
	{
		Collection<Trx> collections = s_trxMap.values();
		Trx[] trxs = new Trx[collections.size()];
		collections.toArray(trxs);
		
		return trxs;
	}
	
	/**
	 * @see #run(String, TrxRunnable)
	 */
	public static void run(TrxRunnable r)
	{
		Services.get(ITrxBL.class).run(r);
	}
	
	/**
	 * Executes the runnable object.
	 * Same as calling {@link #run(String, boolean, TrxRunnable)} with manageTrx = false 
	 * @param trxName transaction name
	 * @param r runnable object
	 * @see #run(String, boolean, TrxRunnable)
	 */
	// metas: backward compatibility
	public static void run(String trxName, TrxRunnable r)
	{
		Services.get(ITrxBL.class).run(trxName, r);
	}
	
	/**
	 * Execute runnable object using provided transaction. If execution fails, database operations will be rolled back.
	 * <p>
	 * Example:
	 * 
	 * <pre>
	 * Trx.run(null, new {@link TrxRunnable}() {
	 *     public void run(String trxName) {
	 *         // do something using trxName
	 *     }
	 * )};
	 * </pre>
	 * 
	 * @param trxName
	 *            transaction name (if null, a new transaction will be created)
	 * @param manageTrx
	 *            if true, the transaction will be managed by this method (i.e. when runner finishes, transaction will
	 *            be commited). Also, in case transaction is managed, a trxName will be created using given "trxName" as
	 *            name prefix. If trxName is null a new transaction name will be created with prefix "TrxRun".
	 *            If trxName is null, the transaction will be automatically managed, even if the manageTrx parameter is false.
	 * @param r
	 *            runnable object
	 */
	// metas: added manageTrx parameter
	public static void run(String trxName, boolean manageTrx, TrxRunnable r)
	{
		Services.get(ITrxBL.class).run(trxName, manageTrx, r);
		}
			
	private boolean isLocalTrx(String trxName)
			{
		return trxName == null
			|| trxName.startsWith("POSave") // TODO: hardcoded
			;
			}

	/**
	 * Current {@link ITrxListenerManager}
	 * 
	 * @task mo73_04265
	 */
	private ITrxListenerManager trxListenerManager = null;

	/**
	 * Gets the {@link ITrxListenerManager} associated with this transaction.
	 * 
	 * If no {@link ITrxListenerManager} was already created and <code>create</code> is true, a new transaction listener manager will be created and returned
	 * 
	 * @param create
	 * @return
	 * 
	 * @task mo73_04265
	 */
	private ITrxListenerManager getTrxListenerManager(final boolean create)
			{
		if (trxListenerManager != null)
			{
			return trxListenerManager;
		}

		if (create)
			{
			trxListenerManager = new TrxListenerManager();
			return trxListenerManager;
		}

		return NullTrxListenerManager.instance;
	}

	/**
	 * Gets the {@link ITrxListenerManager} associated with this transaction
	 * 
	 * @return {@link ITrxListenerManager}; never returns null
	 * 
	 * @task mo73_04265
	 */
	public ITrxListenerManager getTrxListenerManager()
	{
		return getTrxListenerManager(true); // create=true
	}
}	//	Trx
