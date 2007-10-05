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
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import javax.sql.*;

import org.compiere.db.*;
import org.compiere.interfaces.*;

/**
 *  Adempiere Prepared Statement
 *
 *  @author Jorg Janke
 *  @version $Id: CPreparedStatement.java,v 1.3 2006/07/30 00:54:36 jjanke Exp $
 *  ---
 *   Modifications: Handle connections properly
 *   Reason       : Due to changes brought in the connection pooling whereby the system 
 *                 no more relies upon abandoned connections.
 *  @author Ashley Ramdass (Posterita)
 */
public class CPreparedStatement extends CStatement implements PreparedStatement
{
	/**
	 *	Prepared Statement Constructor
	 *
	 *  @param resultSetType - ResultSet.TYPE_FORWARD_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.TYPE_SCROLL_SENSITIVE
	 *  @param resultSetConcurrency - ResultSet.CONCUR_READ_ONLY or ResultSet.CONCUR_UPDATABLE
	 * 	@param sql0 unconverted sql statement
	 *  @param trxName transaction name or null
	 */
	public CPreparedStatement (int resultSetType, int resultSetConcurrency,
		String sql0, String trxName)
	{
		if (sql0 == null || sql0.length() == 0)
			throw new IllegalArgumentException ("sql required");
		
		p_vo = new CStatementVO (resultSetType, resultSetConcurrency,
			DB.getDatabase().convertStatement(sql0));

		p_vo.setTrxName(trxName);
		
		init();
	}	//	CPreparedStatement

	/**
	 * Initialise the prepared statement wrapper object 
	 */
	protected void init()
	{
		//Local access
		if (!DB.isRemoteObjects())
		{
			try
			{
				Connection conn = null;
				Trx trx = p_vo.getTrxName() == null ? null : Trx.get(p_vo.getTrxName(), true);
				if (trx != null)
					conn = trx.getConnection();
				else
				{
					if (p_vo.getResultSetConcurrency() == ResultSet.CONCUR_UPDATABLE)
						conn = DB.getConnectionRW ();
					else
						conn = DB.getConnectionRO();
				}
				if (conn == null)
					throw new DBException("No Connection");
				p_stmt = conn.prepareStatement (p_vo.getSql(), p_vo.getResultSetType(), p_vo.getResultSetConcurrency());
				return;
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, p_vo.getSql(), e);
			}
		}
	}
	
	/**
	 * 	Remote Constructor
	 *	@param vo value object
	 */
	public CPreparedStatement (CStatementVO vo)
	{
		super(vo);
	}	//	CPreparedStatement


	/**
	 * 	Execute Query
	 * 	@return ResultSet or RowSet
	 * 	@throws SQLException
	 * @see java.sql.PreparedStatement#executeQuery()
	 */
	public ResultSet executeQuery () throws SQLException
	{
		if (p_stmt != null)	//	local
			return ((PreparedStatement)p_stmt).executeQuery();
		//
		//	Client -> remote sever
		log.finest("server => " + p_vo + ", Remote=" + DB.isRemoteObjects());
		try
		{
			boolean remote = DB.isRemoteObjects() && CConnection.get().isAppsServerOK(false);
			if (remote && p_remoteErrors > 1)
				remote = CConnection.get().isAppsServerOK(true);
			if (remote)
			{
				Server server = CConnection.get().getServer();
				if (server != null)
				{
					ResultSet rs = server.pstmt_getRowSet (p_vo, SecurityToken.getInstance());
					p_vo.clearParameters();		//	re-use of result set
					if (rs == null)
						log.warning("ResultSet is null - " + p_vo);
					else
						p_remoteErrors = 0;
					return rs;
				}
				log.log(Level.SEVERE, "AppsServer not found");
				p_remoteErrors++;
			}
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "AppsServer error", ex);
			p_remoteErrors++;
			if (ex instanceof SQLException)
				throw (SQLException)ex;
			else if (ex instanceof RuntimeException)
				throw (RuntimeException)ex;
			else
				throw new RuntimeException(ex);
		}
		//	Try locally
		if (!CConnection.get().isRMIoverHTTP() && CConnection.get().getDatabase().getStatus() != null)
		{
			log.warning("Execute locally");
			p_stmt = local_getPreparedStatement (false, null);	// shared connection
			p_vo.clearParameters();		//	re-use of result set
			ResultSet rs = ((PreparedStatement)p_stmt).executeQuery();
			return rs;
		}
		else
		{
			throw new IllegalStateException("WAN - Application server not available");
		}
	}	//	executeQuery

	/**
	 * 	Execute Query
	 * 	@param sql0 unconverted SQL to execute
	 * 	@return ResultSet or RowSet
	 * 	@throws SQLException
	 * @see java.sql.Statement#executeQuery(String)
	 */
	public ResultSet executeQuery (String sql0) throws SQLException
	{
		//	Convert SQL
		p_vo.setSql(DB.getDatabase().convertStatement(sql0));
		if (p_stmt != null)	//	local
			return p_stmt.executeQuery(p_vo.getSql());
		//
		return executeQuery();
	}	//	executeQuery

	
	/**************************************************************************
	 * 	Execute Update
	 *	@return no of updated rows
	 *	@throws SQLException
	 * @see java.sql.PreparedStatement#executeUpdate()
	 */
	public int executeUpdate () throws SQLException
	{
		if (p_stmt != null)
			return ((PreparedStatement)p_stmt).executeUpdate();
		//
		//	Client -> remote sever
		log.finest("server => " + p_vo + ", Remote=" + DB.isRemoteObjects());
		try
		{
			if (DB.isRemoteObjects() && CConnection.get().isAppsServerOK(false))
			{
				Server server = CConnection.get().getServer();
				if (server != null)
				{
					int result = server.stmt_executeUpdate (p_vo, SecurityToken.getInstance());
					p_vo.clearParameters();		//	re-use of result set
					return result;
				}
				log.log(Level.SEVERE, "AppsServer not found");
			}
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "AppsServer error", ex);
			if (ex instanceof SQLException)
				throw (SQLException)ex;
			else if (ex instanceof RuntimeException)
				throw (RuntimeException)ex;
			else
				throw new RuntimeException(ex);
		}
		//	Try locally
		if (!CConnection.get().isRMIoverHTTP() && CConnection.get().getDatabase().getStatus() != null)
		{
			log.warning("execute locally");
			p_stmt = local_getPreparedStatement (false, null);	//	shared connection
			p_vo.clearParameters();		//	re-use of result set
			return ((PreparedStatement)p_stmt).executeUpdate();
		}
		else
		{
			throw new IllegalStateException("WAN - Application server not available");
		}
	}	//	executeUpdate

	/**
	 * 	Execute Update
	 *	@param sql0 unconverted sql
	 *	@return no of updated rows
	 *	@throws SQLException
	 * @see java.sql.Statement#executeUpdate(String)
	 */
	public int executeUpdate (String sql0) throws SQLException
	{
		//	Convert SQL
		p_vo.setSql(DB.getDatabase().convertStatement(sql0));
		if (p_stmt != null)	//	local
			return p_stmt.executeUpdate (p_vo.getSql());
		return executeUpdate();
	}	//	executeUpdate


	/**
	 * Method execute
	 * @return boolean
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#execute()
	 */
	public boolean execute () throws SQLException
	{
		if (p_stmt != null)
			return ((PreparedStatement)p_stmt).execute();
		throw new java.lang.UnsupportedOperationException ("Method execute() not yet implemented.");
	}


	/**
	 * Method getMetaData
	 * @return ResultSetMetaData
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#getMetaData()
	 */
	public ResultSetMetaData getMetaData () throws SQLException
	{
		if (p_stmt != null)
			return ((PreparedStatement)p_stmt).getMetaData ();
		else
			throw new java.lang.UnsupportedOperationException ("Method getMetaData() not yet implemented.");
	}

	/**
	 * Method getParameterMetaData
	 * @return ParameterMetaData
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#getParameterMetaData()
	 */
	public ParameterMetaData getParameterMetaData () throws SQLException
	{
		if (p_stmt != null)
			return ((PreparedStatement)p_stmt).getParameterMetaData();
		throw new java.lang.UnsupportedOperationException ("Method getParameterMetaData() not yet implemented.");
	}

	/**
	 * Method addBatch
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#addBatch()
	 */
	public void addBatch () throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).addBatch ();
		else
			throw new java.lang.UnsupportedOperationException ("Method addBatch() not yet implemented.");
	}

	/**************************************************************************
	 * 	Set Null
	 *	@param parameterIndex index
	 *	@param sqlType type
	 *	@throws SQLException
	 */
	public void setNull (int parameterIndex, int sqlType) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setNull (parameterIndex, sqlType);
		else
			p_vo.setParameter(parameterIndex, new NullParameter(sqlType));
	}	//	setNull

	/**
	 * Method setNull
	 * @param parameterIndex int
	 * @param sqlType int
	 * @param typeName String
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setNull(int, int, String)
	 */
	public void setNull (int parameterIndex, int sqlType, String typeName) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setNull (parameterIndex, sqlType);
		else
			p_vo.setParameter(parameterIndex, new NullParameter(sqlType));
	}

	/**
	 * Method setBoolean
	 * @param parameterIndex int
	 * @param x boolean
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setBoolean(int, boolean)
	 */
	public void setBoolean (int parameterIndex, boolean x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setBoolean (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, new Boolean(x));
	}

	/**
	 * Method setByte
	 * @param parameterIndex int
	 * @param x byte
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setByte(int, byte)
	 */
	public void setByte (int parameterIndex, byte x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setByte (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, new Byte(x));
	}

	/**
	 * Method setShort
	 * @param parameterIndex int
	 * @param x short
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setShort(int, short)
	 */
	public void setShort (int parameterIndex, short x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setShort (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, new Short(x));
	}

	/**
	 * Method setInt
	 * @param parameterIndex int
	 * @param x int
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setInt(int, int)
	 */
	public void setInt (int parameterIndex, int x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setInt (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, new Integer(x));
	}

	/**
	 * Method setLong
	 * @param parameterIndex int
	 * @param x long
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setLong(int, long)
	 */
	public void setLong (int parameterIndex, long x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setLong (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, new Long(x));
	}

	/**
	 * Method setFloat
	 * @param parameterIndex int
	 * @param x float
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setFloat(int, float)
	 */
	public void setFloat (int parameterIndex, float x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setFloat (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, new Float(x));
	}

	/**
	 * Method setDouble
	 * @param parameterIndex int
	 * @param x double
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setDouble(int, double)
	 */
	public void setDouble (int parameterIndex, double x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setDouble (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, new Double(x));
	}

	/**
	 * Method setBigDecimal
	 * @param parameterIndex int
	 * @param x BigDecimal
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setBigDecimal(int, BigDecimal)
	 */
	public void setBigDecimal (int parameterIndex, BigDecimal x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setBigDecimal (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, x);
	}

	/**
	 * Method setString
	 * @param parameterIndex int
	 * @param x String
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setString(int, String)
	 */
	public void setString (int parameterIndex, String x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setString (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, x);
	}

	/**
	 * Method setBytes
	 * @param parameterIndex int
	 * @param x byte[]
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setBytes(int, byte[])
	 */
	public void setBytes (int parameterIndex, byte[] x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setBytes (parameterIndex, x);
		else
			p_vo.setParameter (parameterIndex, x);
	}

	/**
	 * Method setDate
	 * @param parameterIndex int
	 * @param x java.sql.Date
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setDate(int, java.sql.Date)
	 */
	public void setDate (int parameterIndex, java.sql.Date x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setDate (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, x);
	}

	/**
	 * Method setTime
	 * @param parameterIndex int
	 * @param x Time
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setTime(int, Time)
	 */
	public void setTime (int parameterIndex, Time x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setTime (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, x);
	}

	/**
	 * Method setTimestamp
	 * @param parameterIndex int
	 * @param x Timestamp
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setTimestamp(int, Timestamp)
	 */
	public void setTimestamp (int parameterIndex, Timestamp x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setTimestamp (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, x);
	}

	/**
	 * Method setAsciiStream
	 * @param parameterIndex int
	 * @param x InputStream
	 * @param length int
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setAsciiStream(int, InputStream, int)
	 */
	public void setAsciiStream (int parameterIndex, InputStream x, int length) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setAsciiStream (parameterIndex, x, length);
		else
			throw new java.lang.UnsupportedOperationException ("Method setAsciiStream() not yet implemented.");
	}

	/**
	 * @param parameterIndex the first parameter is 1, the second is 2, ...
	 * @param x a <code>java.io.InputStream</code> object that contains the
	 *        Unicode parameter value as two-byte Unicode characters
	 * @param length the number of bytes in the stream
	 * @exception SQLException if a database access error occurs
	 * see java.sql.PreparedStatement#setUnicodeStream(int, InputStream, int)
	 * @deprecated
	 */
	public void setUnicodeStream (int parameterIndex, InputStream x, int length) throws SQLException
	{
		throw new UnsupportedOperationException ("Method setUnicodeStream() not yet implemented.");
	}

	/**
	 * Method setBinaryStream
	 * @param parameterIndex int
	 * @param x InputStream
	 * @param length int
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setBinaryStream(int, InputStream, int)
	 */
	public void setBinaryStream (int parameterIndex, InputStream x, int length) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setBinaryStream (parameterIndex, x, length);
		else
			throw new java.lang.UnsupportedOperationException ("Method setBinaryStream() not yet implemented.");
	}

	/**
	 * Method clearParameters
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#clearParameters()
	 */
	public void clearParameters () throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).clearParameters ();
		else
			p_vo.clearParameters();
	}

	/**
	 * Method setObject
	 * @param parameterIndex int
	 * @param x Object
	 * @param targetSqlType int
	 * @param scale int
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setObject(int, Object, int, int)
	 */
	public void setObject (int parameterIndex, Object x, int targetSqlType, int scale) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setObject (parameterIndex, x, targetSqlType, scale);
		else
			throw new java.lang.UnsupportedOperationException ("Method setObject() not yet implemented.");
	}

	/**
	 * Method setObject
	 * @param parameterIndex int
	 * @param x Object
	 * @param targetSqlType int
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setObject(int, Object, int)
	 */
	public void setObject (int parameterIndex, Object x, int targetSqlType) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setObject (parameterIndex, x);
		else
			throw new java.lang.UnsupportedOperationException ("Method setObject() not yet implemented.");
	}

	/**
	 * Method setObject
	 * @param parameterIndex int
	 * @param x Object
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setObject(int, Object)
	 */
	public void setObject (int parameterIndex, Object x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setObject (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, x);
	}

	/**
	 * Method setCharacterStream
	 * @param parameterIndex int
	 * @param reader Reader
	 * @param length int
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setCharacterStream(int, Reader, int)
	 */
	public void setCharacterStream (int parameterIndex, Reader reader, int length) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setCharacterStream (parameterIndex, reader, length);
		else
			throw new java.lang.UnsupportedOperationException ("Method setCharacterStream() not yet implemented.");
	}

	/**
	 * Method setRef
	 * @param parameterIndex int
	 * @param x Ref
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setRef(int, Ref)
	 */
	public void setRef (int parameterIndex, Ref x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setRef (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, x);
	}

	/**
	 * Method setBlob
	 * @param parameterIndex int
	 * @param x Blob
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setBlob(int, Blob)
	 */
	public void setBlob (int parameterIndex, Blob x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setObject (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, x);
	}

	/**
	 * Method setClob
	 * @param parameterIndex int
	 * @param x Clob
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setClob(int, Clob)
	 */
	public void setClob (int parameterIndex, Clob x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setObject (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, x);
	}

	/**
	 * Method setArray
	 * @param parameterIndex int
	 * @param x Array
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setArray(int, Array)
	 */
	public void setArray (int parameterIndex, Array x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setObject (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, x);
	}

	/**
	 * Method setDate
	 * @param parameterIndex int
	 * @param x java.sql.Date
	 * @param cal Calendar
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setDate(int, java.sql.Date, Calendar)
	 */
	public void setDate (int parameterIndex, java.sql.Date x, Calendar cal) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setDate (parameterIndex, x, cal);
		else
			throw new java.lang.UnsupportedOperationException ("Method setDate() not yet implemented.");
	}

	/**
	 * Method setTime
	 * @param parameterIndex int
	 * @param x Time
	 * @param cal Calendar
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setTime(int, Time, Calendar)
	 */
	public void setTime (int parameterIndex, Time x, Calendar cal) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setTime (parameterIndex, x, cal);
		else
			throw new java.lang.UnsupportedOperationException ("Method setTime() not yet implemented.");
	}

	/**
	 * Method setTimestamp
	 * @param parameterIndex int
	 * @param x Timestamp
	 * @param cal Calendar
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setTimestamp(int, Timestamp, Calendar)
	 */
	public void setTimestamp (int parameterIndex, Timestamp x, Calendar cal) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setTimestamp (parameterIndex, x, cal);
		else
			throw new java.lang.UnsupportedOperationException ("Method setTimestamp() not yet implemented.");
	}

	/**
	 * Method setURL
	 * @param parameterIndex int
	 * @param x URL
	 * @throws SQLException
	 * @see java.sql.PreparedStatement#setURL(int, URL)
	 */
	public void setURL (int parameterIndex, URL x) throws SQLException
	{
		if (p_stmt != null)
			((PreparedStatement)p_stmt).setObject (parameterIndex, x);
		else
			p_vo.setParameter(parameterIndex, x);
	}

	/**
	 * 	String representation
	 * 	@return info
	 */
	public String toString()
	{
		if (p_stmt != null)
			return "CPreparedStatement[Local=" + p_stmt + "]";
		return "CPreparedStatement[" + p_vo + "]";
	}	//	toString

	/**************************************************************************
	 * 	Get Prepared Statement to create RowSet and set parameters.
	 * 	Method called on Remote to execute locally.
	 * 	@param dedicatedConnection if true gets new connection - if false gets anormal RO/RW connection
	 * 	@param trxName transaction
	 * 	@return Prepared Statement
	 */
	private PreparedStatement local_getPreparedStatement (boolean dedicatedConnection, String trxName)
	{
		log.finest(p_vo.getSql());
		Connection conn = null;
		Trx trx = trxName == null ? null : Trx.get(trxName, true);
		if (trx != null)
			conn = trx.getConnection();
		else
		{
			if (dedicatedConnection)
				conn = DB.createConnection (false, Connection.TRANSACTION_READ_COMMITTED);
			else
				conn = local_getConnection (trxName);
		}
		if (conn == null)
			throw new IllegalStateException("Local - No Connection");
		PreparedStatement pstmt = null;
		try
		{
			pstmt = conn.prepareStatement(p_vo.getSql(), p_vo.getResultSetType(), p_vo.getResultSetConcurrency());
			//	Set Parameters
			ArrayList parameters = p_vo.getParameters();
			for (int i = 0; i < parameters.size(); i++)
			{
				Object o = parameters.get(i);
				if (o == null)
					throw new IllegalArgumentException ("Local - Null Parameter #" + i);
				else if (o instanceof NullParameter)
				{
					int type = ((NullParameter)o).getType();
					pstmt.setNull(i+1, type);
					log.finest("#" + (i+1) + " - Null");
				}
				else if (o instanceof Integer)
				{
					pstmt.setInt(i+1, ((Integer)o).intValue());
					log.finest("#" + (i+1) + " - int=" + o);
				}
				else if (o instanceof String)
				{
					pstmt.setString(i+1, (String)o);
					log.finest("#" + (i+1) + " - String=" + o);
				}
				else if (o instanceof Timestamp)
				{
					pstmt.setTimestamp(i+1, (Timestamp)o);
					log.finest("#" + (i+1) + " - Timestamp=" + o);
				}
				else if (o instanceof BigDecimal)
				{
					pstmt.setBigDecimal(i+1, (BigDecimal)o);
					log.finest("#" + (i+1) + " - BigDecimal=" + o);
				}
                else if (o instanceof java.util.Date)
                {
                    pstmt.setTimestamp(i+1, new Timestamp(((java.util.Date)o).getTime()));
                    log.finest("#" + (i+1) + " - Date=" + o);
                }
                else if (o instanceof java.sql.Date)
                {
                    pstmt.setTimestamp(i+1, new Timestamp(((java.sql.Date)o).getTime()));
                    log.finest("#" + (i+1) + " - Date=" + o);
                }
				else
					throw new java.lang.UnsupportedOperationException ("Unknown Parameter Class=" + o.getClass());
			}
		}
		catch (SQLException ex)
		{
            log.log(Level.SEVERE, "local", ex);
            if (pstmt != null)
            {
                try
                {
                    pstmt.close();
                }
                catch (Exception e)
                {
                    log.log(Level.SEVERE, "Could not close prepared statement", e);
                }
            }
            
            if (conn != null && p_vo.getTrxName() == null)
            {
                try
                {
                    conn.close();
                }
                catch (Exception e)
                {
                    log.log(Level.SEVERE, "Could not close connection", e);
                }
            }
            
            pstmt = null;
		}
		return pstmt;
	}	//	local_getPreparedStatement


	/**
	 * 	Execute Query
	 * 	@return ResultSet or RowSet
	 * 	@throws SQLException
	 * @see java.sql.PreparedStatement#executeQuery()
	 */
	public RowSet getRowSet()
	{
		if (p_stmt != null)	//	local
			return local_getRowSet();
		//
		//	Client -> remote sever
		log.finest("server => " + p_vo + ", Remote=" + DB.isRemoteObjects());
		try
		{
			boolean remote = DB.isRemoteObjects() && CConnection.get().isAppsServerOK(false);
			if (remote && p_remoteErrors > 1)
				remote = CConnection.get().isAppsServerOK(true);
			if (remote)
			{
				Server server = CConnection.get().getServer();
				if (server != null)
				{
					RowSet rs = server.pstmt_getRowSet (p_vo, SecurityToken.getInstance());
					p_vo.clearParameters();		//	re-use of result set
					if (rs == null)
						log.warning("RowSet is null - " + p_vo);
					else
						p_remoteErrors = 0;
					return rs;
				}
				log.log(Level.SEVERE, "AppsServer not found");
				p_remoteErrors++;
			}
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "AppsServer error", ex);
			p_remoteErrors++;
			if (ex instanceof RuntimeException)
				throw (RuntimeException)ex;
			else
				throw new RuntimeException(ex);
		}
		//	Try locally
		if (!CConnection.get().isRMIoverHTTP() && CConnection.get().getDatabase().getStatus() != null)
		{
			log.warning("Execute locally");
			p_stmt = local_getPreparedStatement (false, null);	// shared connection
			p_vo.clearParameters();		//	re-use of result set
			return local_getRowSet();
		}
		else
		{
			throw new IllegalStateException("WAN - Application server not available");
		}
	}
	
	/**
	 * 	Get Result as RowSet for local system.
	 * 	Note that connection is closed when closing Oracle CachedRowSet!
	 *	@return result as RowSet
	 */
	protected RowSet local_getRowSet()
	{
		log.finest("local");
		
		RowSet rowSet = null;
		PreparedStatement pstmt = (PreparedStatement)p_stmt;
		try
		{
			//	Set Parameters
			ArrayList parameters = p_vo.getParameters();
			for (int i = 0; i < parameters.size(); i++)
			{
				Object o = parameters.get(i);
				if (o == null)
					throw new IllegalArgumentException ("Null Parameter #" + i);
				else if (o instanceof NullParameter)
				{
					int type = ((NullParameter)o).getType();
					pstmt.setNull(i+1, type);
					log.finest("#" + (i+1) + " - Null");
				}
				else if (o instanceof Integer)
				{
					pstmt.setInt(i+1, ((Integer)o).intValue());
					log.finest("#" + (i+1) + " - int=" + o);
				}
				else if (o instanceof String)
				{
					pstmt.setString(i+1, (String)o);
					log.finest("#" + (i+1) + " - String=" + o);
				}
				else if (o instanceof Timestamp)
				{
					pstmt.setTimestamp(i+1, (Timestamp)o);
					log.finest("#" + (i+1) + " - Timestamp=" + o);
				}
				else if (o instanceof BigDecimal)
				{
					pstmt.setBigDecimal(i+1, (BigDecimal)o);
					log.finest("#" + (i+1) + " - BigDecimal=" + o);
				}
                else if (o instanceof java.util.Date)
                {
                    pstmt.setTimestamp(i+1, new Timestamp(((java.util.Date)o).getTime()));
                    log.finest("#" + (i+1) + " - Date=" + o);
                }
                else if (o instanceof java.sql.Date)
                {
                    pstmt.setTimestamp(i+1, new Timestamp(((java.sql.Date)o).getTime()));
                    log.finest("#" + (i+1) + " - Date=" + o);
                }
				else
					throw new java.lang.UnsupportedOperationException ("Unknown Parameter Class=" + o.getClass());
			}
			//
			ResultSet rs = pstmt.executeQuery();
			rowSet = CCachedRowSet.getRowSet(rs);
			rs.close();			
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, p_vo.toString(), ex);
			throw new RuntimeException (ex);
		}		
		return rowSet;
	}	//	local_getRowSet

	/*************************************************************************
	 * 	Execute Update.
	 *	@return row count
	 */
	public int remote_executeUpdate()
	{
		log.finest("Update");
		PreparedStatement pstmt = null;
		try
		{
			AdempiereDatabase db = CConnection.get().getDatabase();
			if (db == null)
				throw new NullPointerException("Remote - No Database");
			//
			pstmt = local_getPreparedStatement (false, p_vo.getTrxName());	
			int result = pstmt.executeUpdate();
			//
			return result;
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, p_vo.toString(), ex);
			throw new RuntimeException (ex);
		}
		finally 
        {
            if (pstmt != null) 
            {
                try 
                {
                    Connection conn = pstmt.getConnection();
                    pstmt.close();
                    if (p_vo.getTrxName() == null && !conn.isClosed())
                    {
                        conn.close();
                    }
                } 
                catch (SQLException e) 
                {
                    log.log(Level.SEVERE, e.getMessage(), e);
                }
                pstmt = null;
            }
		}
	}	//	remote_executeUpdate
        
	   //remove this commnet if you want use JAVA 6
        	
        public void setAsciiStream(int parameterIndex, java.io.InputStream x, long length)
	    throws SQLException
        {
            
        }
            
        //vpj-cd add support java 6    
        /*
        public void setBinaryStream(int parameterIndex, java.io.InputStream x, 
			 long length) throws SQLException
        {        
            
        }
        
        public void setCharacterStream(int parameterIndex,
       			  java.io.Reader reader,
			  long length) throws SQLException
        {
            
        }
            
        public void setAsciiStream(int parameterIndex, java.io.InputStream x)
	    throws SQLException
        {
            
        }
           
        
       public void setBinaryStream(int parameterIndex, java.io.InputStream x)
       throws SQLException
       {
                
       }
            
       public void setCharacterStream(int parameterIndex,
       			  java.io.Reader reader) throws SQLException
       {
           
       }
        
       public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException
       {
           
       }        
       
       public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException
       {
               
       }
        
       public void setClob(int parameterIndex, Reader reader)
       throws SQLException
       {
           
       }   
       
        public void setClob(int parameterIndex, Reader reader, long length)
       throws SQLException
        {
            
        }
        
        public void setNClob(int parameterIndex, Reader reader, long length)
        throws SQLException
        {
            
        }
        
        public void setNClob(int parameterIndex, Reader reader)
       throws SQLException
        {
            
        }
        
        public void setNClob(int i,java.sql.NClob c) 
       throws SQLException
        {
            
        }
        
        public void setBlob(int parameterIndex, InputStream inputStream, long length)
        throws SQLException
        {
            
        }

           
       public void setBlob(int parameterIndex, InputStream inputStream)
       throws SQLException
       {
           
       }        
       
       public void setNString(int parameterIndex, String value) throws SQLException
       {
           
       }
       
       //In order to compile in Java 6 you must add these methods
     
       
       public void setSQLXML(int parameterIndex, java.sql.SQLXML xmlObject) throws SQLException
       {
           
       }

       public void setRowId(int parameterIndex, java.sql.RowId x) throws SQLException{}
       */

}	//	CPreparedStatement

