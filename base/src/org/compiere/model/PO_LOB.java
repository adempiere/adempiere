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
package org.compiere.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Trx;

/**
 * 	Persistent Object LOB.
 * 	Allows to store LOB remotely
 * 	Currently Oracle specific!
 *	
 *  @author Jorg Janke
 *  @version $Id: PO_LOB.java,v 1.2 2006/07/30 00:58:04 jjanke Exp $
 */
public class PO_LOB implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -325477490976139224L;

	/**
	 * 	Constructor
	 *	@param tableName table name
	 *	@param columnName column name
	 *	@param whereClause where 
	 *	@param displayType display type
	 *	@param value value
	 */
	public PO_LOB (String tableName, String columnName, String whereClause, 
		int displayType, Object value)
	{
		m_tableName = tableName;
		m_columnName = columnName;
		m_whereClause = whereClause;
		m_displayType = displayType;
		m_value = value;
	}	//	PO_LOB

	/**	Logger					*/
	protected CLogger	log = CLogger.getCLogger (getClass());
	/**	Table Name				*/
	private String 		m_tableName;
	/** Column Name				*/
	private String 		m_columnName;
	/** Where Clause			*/
	private String		m_whereClause;
	
	/** Display Type			*/
	private int			m_displayType;
	/** Data					*/
	private Object 		m_value;

	/**
	 * 	Save LOB
	 * 	@param whereClause clause
	 * 	@param trxName trx name
	 *	@return true if saved
	 */
	public boolean save (String whereClause, String trxName)
	{
		m_whereClause = whereClause;
		return save(trxName);
	}	//	save

	/**
	 * 	Save LOB.
	 * 	see also org.compiere.session.ServerBean#updateLOB
	 * 	@param trxName trx name
	 *	@return true if saved
	 */
	public boolean save (String trxName)
	{
		if (m_value == null
			|| (!(m_value instanceof String || m_value instanceof byte[])) 
			|| (m_value instanceof String && m_value.toString().length() == 0)
			|| (m_value instanceof byte[] && ((byte[])m_value).length == 0)
			)
		{
			StringBuffer sql = new StringBuffer ("UPDATE ")
				.append(m_tableName)
				.append(" SET ").append(m_columnName)
				.append("=null WHERE ").append(m_whereClause);
			int no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("save [" + trxName + "] #" + no + " - no data - set to null - " + m_value);
			if (no == 0)
				log.warning("[" + trxName + "] - not updated - " + sql);
			return true;
		}
		
		StringBuffer sql = new StringBuffer ("UPDATE ")
			.append(m_tableName)
			.append(" SET ").append(m_columnName)
			.append("=? WHERE ").append(m_whereClause);
		//
		
		log.fine("[" + trxName + "] - Local - " + m_value);
		//	Connection
		Trx trx = null;
		if (trxName != null)
			trx = Trx.get(trxName, false);
		Connection con = null;
		//	Create Connection
		if (trx != null)
			con = trx.getConnection();
		if (con == null)
			con = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
		if (con == null)
		{
			log.log(Level.SEVERE, "Could not get Connection");
			return false;
		}

		PreparedStatement pstmt = null;
		boolean success = true;
		try
		{
			pstmt = con.prepareStatement(sql.toString());
			if (m_displayType == DisplayType.TextLong)
				pstmt.setString(1, (String)m_value);
			else
				pstmt.setBytes(1, (byte[])m_value);
			int no = pstmt.executeUpdate();
			if (no != 1)
			{
				log.warning("[" + trxName + "] - Not updated #" + no + " - " + sql);
				success = false;
			}
		}
		catch (Throwable e)
		{
			log.log(Level.SEVERE, "[" + trxName + "] - " + sql, e);
			success = false;
		}
		finally
		{
			DB.close(pstmt);
			pstmt = null;
		}
		
		//	Success - commit local trx
		if (success)
		{
			if (trx != null)
			{
				trx = null;
				con = null;
			}
			else
			{
				try
				{
					con.commit();					
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, "[" + trxName + "] - commit " , e);
					success = false;
				}
				finally
				{
					try {
						con.close();
					} catch (SQLException e) {
					}
					con = null;
				}
			}
		}
		//	Error - roll back
		if (!success)
		{
			log.severe ("[" + trxName + "] - rollback");
			if (trx != null)
			{
				trx.rollback();
				trx = null;
				con = null;
			}
			else
			{
				try
				{
					con.rollback();
				}
				catch (Exception ee)
				{
					log.log(Level.SEVERE, "[" + trxName + "] - rollback" , ee);
				}
				finally
				{
					try {
						con.close();
					} catch (SQLException e) {
					}
					con = null;
				}
			}
		}
		
		return success;
	}	//	save

	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("PO_LOB[");
		sb.append(m_tableName).append(".").append(m_columnName)
			.append(",DisplayType=").append(m_displayType)
			.append("]");
		return sb.toString();
	}	//	toString

}	//	PO_LOB
