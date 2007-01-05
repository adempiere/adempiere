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
import java.sql.*;
import java.util.logging.*;

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
		prefix += "_" + System.currentTimeMillis();
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
	@param con optional connection
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
	private Savepoint	m_savepoint = null;
	private boolean		m_active = false;

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
	//	System.err.println ("Trx.getConnection - " + m_name + ": "+ m_connection); 
	//	Trace.printStack();
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
		if (m_savepoint != null || m_active)
		{
			log.warning("Trx in progress " + m_trxName + " - " + m_savepoint);
			return false;
		}
		m_active = true;
		try
		{
			if (m_connection != null)
			{
				m_savepoint = m_connection.setSavepoint(m_trxName);
				log.info("**** " + getTrxName());
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, m_trxName, e);
			m_savepoint = null;
			return false;
		}
		return true;
	}	//	startTrx

	/**
	 * 	Get Savepoint
	 *	@return savepoint or null
	 */
	public Savepoint getSavepoint()
	{
		return m_savepoint;
	}	//	getSavepoint
	
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
		try
		{
			if (m_connection != null)
			{
				if (m_savepoint == null)
					m_connection.rollback();
				else
					m_connection.rollback(m_savepoint);
				log.info ("**** " + m_trxName);
				m_savepoint = null;
				m_active = false;
				return true;
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, m_trxName, e);
			if (throwException)
			{
				m_savepoint = null;
				m_active = false;
				throw e;
			}
		}		
		m_savepoint = null;
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
	 * 	Release savepoint
	 *	@return true if released
	 *
	public boolean release()
	{
		if (m_connection == null)
			return false;
		m_active = false;
		if (m_savepoint == null)
			return true;
		try
		{
			getConnection().releaseSavepoint(m_savepoint);
			log.fine("release **** " + getName());
			m_savepoint = null;
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "release ****", e);
			m_savepoint = null;
			return false;
		}
		return true;
	}	//	release

	/**
	 * Commit
	 * @param throwException if true, re-throws exception
	 * @return true if success
	 **/
	public boolean commit(boolean throwException) throws SQLException
	{
		try
		{
			if (m_connection != null)
			{
				m_connection.commit();
				log.info ("**** " + m_trxName);
				m_savepoint = null;
				m_active = false;
				return true;
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, m_trxName, e);
			if (throwException) 
			{
				m_savepoint = null;
				m_active = false;
				throw e;
			}
		}
		m_savepoint = null;
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
		//
		if (m_connection == null)
			return true;
		
		if (m_savepoint != null || isActive())
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
		m_savepoint = null;
		m_connection = null;
		m_active = false;
		log.config(m_trxName);
		return true;
	}	//	close
	
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
	
	
}	//	Trx
