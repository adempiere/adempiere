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

import java.beans.*;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.Collection;
import java.util.UUID;
import java.util.logging.*;

import org.compiere.db.CConnection;
import org.compiere.db.ServerConnection;
import org.compiere.interfaces.Server;

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
 *  @version $Id$
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
		//create remote transaction immediately
		if (DB.isRemoteObjects())
			this.start();
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
		//wan profile
		if (DB.isRemoteObjects()) 
			return new ServerConnection(getTrxName());
		
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
		if (DB.isRemoteObjects()) {
			startRemoteTransaction();
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
	
	private void startRemoteTransaction() {
		Server server = CConnection.get().getServer();
		try
		{
			if (server != null)
			{	//	See ServerBean
				server.startTransaction(getTrxName());
			}
			else
			{
				log.log(Level.WARNING, "AppsServer not found");
			}
		}
		catch (RemoteException ex)
		{
			log.log(Level.SEVERE, "AppsServer error", ex);
		}
		
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
		//remote
		if (DB.isRemoteObjects())
		{
			return remote_rollback(throwException);
		}
		
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
		//remote
		if (DB.isRemoteObjects())
		{
			return remote_rollback(savepoint);
		}
		
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
	 * Rollback a remote transaction
	 * @param throwException
	 * @return true if success, false otherwise
	 * @throws SQLException
	 */
	private boolean remote_rollback(boolean throwException) throws SQLException
	{
		Server server = CConnection.get().getServer();
		try
		{
			if (server != null)
			{	//	See ServerBean
				return server.rollback(m_trxName);
			}
			else
			{
				log.log(Level.SEVERE, "AppsServer not found");
				if (throwException)
					throw new SQLException("AppsServer not found");
				return false;
			}
		}
		catch (RemoteException ex)
		{
			log.log(Level.SEVERE, "AppsServer error", ex);
			if (throwException)
			{
				if (ex.getCause() instanceof RuntimeException)
				{
					RuntimeException r = (RuntimeException)ex.getCause();
					if (r.getCause() instanceof SQLException)
						throw (SQLException)r.getCause();
					else if ( r.getCause() != null )
						throw new SQLException("Application server exception - " + r.getCause().getMessage());
					else
						throw new SQLException("Application server exception - " + r.getMessage());
				}
				else
					throw new SQLException("Application server exception - " + ex.getMessage());
			}
			
			return false;
		}
	}
	
	/**
	 * Rollback a remote transaction
	 * @param throwException
	 * @return true if success, false otherwise
	 * @throws SQLException
	 */
	private boolean remote_rollback(Savepoint savepoint) throws SQLException
	{
		Server server = CConnection.get().getServer();
		try
		{
			if (server != null)
			{	
				SavepointVO sp = null;
				if (savepoint instanceof SavepointVO)
					sp = (SavepointVO)savepoint;
				else
					sp = new SavepointVO(savepoint);
				return server.rollback(m_trxName, sp);
			}
			else
			{
				log.log(Level.SEVERE, "AppsServer not found");
				throw new SQLException("AppsServer not found");
			}
		}
		catch (RemoteException ex)
		{
			log.log(Level.SEVERE, "AppsServer error", ex);
			if (ex.getCause() instanceof RuntimeException)
			{
				RuntimeException r = (RuntimeException)ex.getCause();
				if (r.getCause() instanceof SQLException)
					throw (SQLException)r.getCause();
				else if ( r.getCause() != null )
					throw new SQLException("Application server exception - " + r.getCause().getMessage());
				else
					throw new SQLException("Application server exception - " + r.getMessage());
			}
			else
			{
					throw new SQLException("Application server exception - " + ex.getMessage());
			}
		}
	}
	/**
	 * Commit
	 * @param throwException if true, re-throws exception
	 * @return true if success
	 **/
	public boolean commit(boolean throwException) throws SQLException
	{
		//remote
		if (DB.isRemoteObjects())
		{
			return remote_commit(throwException);
		}
		
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
	 * Commit a remote transaction
	 * @param throwException
	 * @return true if success, false otherwise
	 * @throws SQLException
	 */
	private boolean remote_commit(boolean throwException) throws SQLException
	{
		Server server = CConnection.get().getServer();
		try
		{
			if (server != null)
			{	//	See ServerBean
				return server.commit(m_trxName);
			}
			else
			{
				log.log(Level.SEVERE, "AppsServer not found");
				if (throwException)
					throw new SQLException("AppsServer not found");
				return false;
			}
		}
		catch (RemoteException ex)
		{
			log.log(Level.SEVERE, "AppsServer error", ex);
			if (throwException)
			{
				if (ex.getCause() instanceof RuntimeException)
				{
					RuntimeException r = (RuntimeException)ex.getCause();
					if (r.getCause() instanceof SQLException)
						throw (SQLException)r.getCause();
					else if ( r.getCause() != null )
						throw new SQLException("Application server exception - " + r.getCause().getMessage());
					else
						throw new SQLException("Application server exception - " + r.getMessage());
				}
				else
					throw new SQLException("Application server exception - " + ex.getMessage());
			}
				
			return false;
		}
	}
	
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
		
		//remote
		if (DB.isRemoteObjects()) {
			closeRemoteTransaction();
			m_active = false;
			return true;
		}
		
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
	
	private void closeRemoteTransaction() {
		Server server = CConnection.get().getServer();
		try
		{
			if (server != null)
			{	//	See ServerBean
				server.closeTransaction(getTrxName());
			}
			else 
			{
				log.log(Level.WARNING, "AppsServer not found");
			}
		}
		catch (RemoteException ex)
		{
			log.log(Level.SEVERE, "AppsServer error", ex);
		}
		
	}

	/**
	 * 
	 * @param name
	 * @return Savepoint
	 * @throws SQLException
	 */
	public Savepoint setSavepoint(String name) throws SQLException {
		//remote
		if (DB.isRemoteObjects())
		{
			return setRemoteSavepoint(name);
		}
		
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
	
	private Savepoint setRemoteSavepoint(String name) throws SQLException {
		Server server = CConnection.get().getServer();
		try
		{
			if (server != null)
			{	//	See ServerBean
				return server.setSavepoint(m_trxName, name);
			}
			else
			{
				log.log(Level.SEVERE, "AppsServer not found");
				throw new SQLException("AppsServer not found");
			}
		}
		catch (RemoteException ex)
		{
			log.log(Level.SEVERE, "AppsServer error", ex);
			if (ex.getCause() instanceof RuntimeException)
			{
				RuntimeException r = (RuntimeException)ex.getCause();
				if (r.getCause() instanceof SQLException)
					throw (SQLException)r.getCause();
				else if ( r.getCause() != null )
					throw new SQLException("Application server exception - " + r.getCause().getMessage());
				else
					throw new SQLException("Application server exception - " + r.getMessage());
			}
			else
				throw new SQLException("Application server exception - " + ex.getMessage());
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
	
}	//	Trx
