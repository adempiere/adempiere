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
import java.util.UUID;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;

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
 *  @author Teo Sarca, teo.sarca@gmail.com
 *  		<li>BF [ 2849122 ] PO.AfterSave is not rollback on error - add releaseSavepoint method
 *  			https://sourceforge.net/tracker/index.php?func=detail&aid=2849122&group_id=176962&atid=879332#
 */
public class Trx implements VetoableChangeListener
{
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

		if (s_cache == null)
		{
			s_cache = new CCache<String,Trx>("Trx", 10, -1);	//	no expiration
			s_cache.addVetoableChangeListener(new Trx("controller"));
		}
		
		Trx retValue = (Trx)s_cache.get(trxName);
		if (retValue == null && createNew)
		{
			retValue = new Trx (trxName);
			s_cache.put(trxName, retValue);
		}
		return retValue;
	}	//	get
	
	/**	Transaction Cache					*/
	private static CCache<String,Trx> 	s_cache = null;	//	create change listener
	
	/**
	 * 	Create unique Transaction Name
	 *	@param prefix optional prefix
	 *	@return unique name
	 */
	public static String createTrxName (String prefix)
	{
		if (prefix == null || prefix.length() == 0)
			prefix = "Trx";
		prefix += "_" + UUID.randomUUID(); //System.currentTimeMillis();
		//create transaction entry
		Trx.get(prefix, true);
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
	 * 	Get Connection
	 *	@return connection
	 */
	public Connection getConnection()
	{
		log.log(Level.ALL, "Active=" + isActive() + ", Connection=" + m_connection);
		
		if (m_connection == null)	//	get new Connection
			setConnection(DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED));
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
	{
		//local
		try
		{
			if (m_connection != null)
			{
				m_connection.commit();
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
		if (s_cache != null)
			s_cache.remove(getTrxName());
		
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
			if (name != null)
				return m_connection.setSavepoint(name);
			else
				return m_connection.setSavepoint();
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
		if (m_connection == null) 
		{
			getConnection();
		}
		if(m_connection != null)
		{
			m_connection.releaseSavepoint(savepoint);
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
	}	//	vetoableChange	
	
	/**
	 * @return Trx[]
	 */
	public static Trx[] getActiveTransactions()
	{
		Collection<Trx> collections = s_cache.values();
		Trx[] trxs = new Trx[collections.size()];
		collections.toArray(trxs);
		
		return trxs;
	}
	
	/**
	 * @see #run(String, TrxRunnable)
	 */
	public static void run(TrxRunnable r)
	{
		run(null, r);
	}
	
	/**
	 * Execute runnable object using provided transaction.
	 * If execution fails, database operations will be rolled back.
	 * <p>
	 * Example: <pre>
	 * Trx.run(null, new {@link TrxRunnable}() {
	 *     public void run(String trxName) {
	 *         // do something using trxName
	 *     }
	 * )};
	 * </pre>
	 * 
	 * @param trxName transaction name (if null, a new transaction will be created)
	 * @param r runnable object
	 */
	public static void run(String trxName, TrxRunnable r)
	{
		boolean localTrx = false;
		if (trxName == null) {
			trxName = Trx.createTrxName("TrxRun");
			localTrx = true;
		}
		Trx trx = Trx.get(trxName, true);
		Savepoint savepoint = null;
		try
		{
			if (!localTrx)
				savepoint = trx.setSavepoint(null);
				
			r.run(trxName);
			
			if (localTrx)
				trx.commit(true);
		}
		catch (Throwable e)
		{
			// Rollback transaction
			if (localTrx)
			{
				trx.rollback();
			}
			else if (savepoint != null)
			{
				try {
					trx.rollback(savepoint);
				}
				catch (SQLException e2) {;}
			}
			trx = null;
			// Throw exception
			if (e instanceof RuntimeException)
			{
				throw (RuntimeException)e;
			}
			else
			{
				throw new AdempiereException(e);
			}
		}
		finally {
			if (localTrx && trx != null)
			{
				trx.close();
				trx = null;
			}
		}
	}
	
}	//	Trx
