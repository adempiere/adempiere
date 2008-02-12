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

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.logging.Level;

import org.compiere.db.CConnection;
import org.compiere.interfaces.Server;


/**
 *  Adempiere Callable Statement
 *
 *  @author Ashley Ramdass
 *  @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *  			<li>BF [ 1806700 ] Compile error on JAVA 6
 */
public class CCallableStatement extends CPreparedStatement implements CallableStatement
{
    /**
     *  Callable Statement Constructor
     *
     *  @param resultSetType - ResultSet.TYPE_FORWARD_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.TYPE_SCROLL_SENSITIVE
     *  @param resultSetConcurrency - ResultSet.CONCUR_READ_ONLY or ResultSet.CONCUR_UPDATABLE
     *  @param sql unconverted sql statement
     *  @param trxName transaction name or null
     */
    public CCallableStatement(int resultSetType, int resultSetConcurrency, String sql, String trxName)
    {
        super(resultSetType, resultSetConcurrency, sql, trxName);
    }
    
    public CCallableStatement(CStatementVO vo) {
		super(vo);
	}

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
                {
                    conn = trx.getConnection();
                    useTransactionConnection = true;
                }
                else
                {
                    if (p_vo.getResultSetConcurrency() == ResultSet.CONCUR_UPDATABLE)
                        conn = DB.getConnectionRW ();
                    else
                        conn = DB.getConnectionRO();                    
                }
                if (conn == null)
                    throw new DBException("No Connection");
                p_stmt = conn.prepareCall(p_vo.getSql(), p_vo.getResultSetType(), p_vo.getResultSetConcurrency());
                return;
            }
            catch (Exception e)
            {
                log.log(Level.SEVERE, p_vo.getSql(), e);
            }
        }
    }

    /**
     * @param i
     * @return Array
     * @see java.sql.CallableStatement#getArray(int)
     */
    public Array getArray(int i) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getArray(i);
        }
        throw new java.lang.UnsupportedOperationException ("Method getArray() not yet implemented.");
    }

    /**
     * @param parameterName
     * @return Array
     * @see java.sql.CallableStatement#getArray(String)
     */
    public Array getArray(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getArray(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getArray() not yet implemented.");
    }

    /**
     * @param parameterIndex
     * @return BigDecimal
     * @see java.sql.CallableStatement#getBigDecimal(int)
     */
    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBigDecimal(parameterIndex);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(parameterIndex);
        }
        return o != null ? (BigDecimal)o.getValue() : null;
    }

    /**
     * @param parameterName
     * @return BigDecimal
     * @see java.sql.CallableStatement#getBigDecimal(String)
     */
    public BigDecimal getBigDecimal(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBigDecimal(parameterName);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getNamedOutput().get(parameterName);
        }
        return o != null ? (BigDecimal)o.getValue() : null;
    }

    /**
     * @param parameterIndex
     * @param scale
     * @return BigDecimal
     * @see java.sql.CallableStatement#getBigDecimal(int, int)
     */
    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBigDecimal(parameterIndex, scale);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(parameterIndex);
        }
        return o != null ? (BigDecimal)o.getValue() : null;
    }

    /**
     * @param i
     * @return Blob
     * @see java.sql.CallableStatement#getBlob(int)
     */
    public Blob getBlob(int i) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBlob(i);
        }
        throw new java.lang.UnsupportedOperationException ("Method getBlob() not yet implemented.");
    }

    /**
     * @param parameterName
     * @return Blob
     * @see java.sql.CallableStatement#getBlob(String)
     */
    public Blob getBlob(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBlob(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getBlob() not yet implemented.");
    }

    /**
     * @param parameterIndex
     * @return boolean
     * @see java.sql.CallableStatement#getBoolean(int)
     */
    public boolean getBoolean(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBoolean(parameterIndex);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(parameterIndex);
        }
        return o != null ? (Boolean)o.getValue() : null;
    }

    /**
     * @param parameterName
     * @return boolean
     * @see java.sql.CallableStatement#getBoolean(String)
     */
    public boolean getBoolean(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBoolean(parameterName);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getNamedOutput().get(parameterName);
        }
        return o != null ? (Boolean)o.getValue() : null;
    }

    /**
     * @param parameterIndex
     * @see java.sql.CallableStatement#getByte(int)
     */
    public byte getByte(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getByte(parameterIndex);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(parameterIndex);
        }
        return o != null ? (Byte)o.getValue() : null;
    }

    /**
     * @param parameterName
     * @return byte
     * @see java.sql.CallableStatement#getByte(String)
     */
    public byte getByte(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getByte(parameterName);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getNamedOutput().get(parameterName);
        }
        return o != null ? (Byte)o.getValue() : null;
    }

    /**
     * @param parameterIndex
     * @return byte[]
     * @see java.sql.CallableStatement#getBytes(int)
     */
    public byte[] getBytes(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBytes(parameterIndex);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(parameterIndex);
        }
        return o != null ? (byte[])o.getValue() : null;
    }

    /**
     * @param parameterName
     * @return byte[]
     * @see java.sql.CallableStatement#getBytes(String)
     */
    public byte[] getBytes(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBytes(parameterName);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getNamedOutput().get(parameterName);
        }
        return o != null ? (byte[])o.getValue() : null;
    }

    /**
     * @param i
     * @return Clob
     * @see java.sql.CallableStatement#getClob(int)
     */
    public Clob getClob(int i) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getClob(i);
        }
        throw new java.lang.UnsupportedOperationException ("Method getClob() not yet implemented.");
    }

    /**
     * @param parameterName
     * @return @Clob
     * @see java.sql.CallableStatement#getClob(String)
     */
    public Clob getClob(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getClob(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getClob() not yet implemented.");
    }

    /**
     * @param parameterIndex
     * @return Date
     * @see java.sql.CallableStatement#getDate(int)
     */
    public Date getDate(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getDate(parameterIndex);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(parameterIndex);
        }
        return o != null ? (Date)o.getValue() : null;
    }


    public Date getDate(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getDate(parameterName);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getNamedOutput().get(parameterName);
        }
        return o != null ? (Date)o.getValue() : null;
    }


    public Date getDate(int parameterIndex, Calendar cal) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getDate(parameterIndex, cal);
        }
        throw new java.lang.UnsupportedOperationException ("Method getDate(parameterIndex, calendar) not yet implemented.");
    }


    public Date getDate(String parameterName, Calendar cal) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getDate(parameterName, cal);
        }
        throw new java.lang.UnsupportedOperationException ("Method getDate(parameterName, calendar) not yet implemented.");
    }


    public double getDouble(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getDouble(parameterIndex);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(parameterIndex);
        }
        return o != null ? ((Number)o.getValue()).doubleValue() : 0;
    }


    public double getDouble(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getDouble(parameterName);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getNamedOutput().get(parameterName);
        }
        return o != null ? ((Number)o.getValue()).doubleValue() : 0;
    }


    public float getFloat(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getFloat(parameterIndex);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(parameterIndex);
        }
        return o != null ? ((Number)o.getValue()).floatValue() : 0;
    }


    public float getFloat(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getFloat(parameterName);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getNamedOutput().get(parameterName);
        }
        return o != null ? ((Number)o.getValue()).floatValue() : 0;
    }


    public int getInt(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getInt(parameterIndex);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(parameterIndex);
        }
        return o != null ? ((Number)o.getValue()).intValue() : 0;
    }


    public int getInt(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getInt(parameterName);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getNamedOutput().get(parameterName);
        }
        return o != null ? ((Number)o.getValue()).intValue() : 0;
    }


    public long getLong(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getLong(parameterIndex);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(parameterIndex);
        }
        return o != null ? ((Number)o.getValue()).longValue() : 0;
    }


    public long getLong(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getLong(parameterName);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getNamedOutput().get(parameterName);
        }
        return o != null ? ((Number)o.getValue()).longValue() : 0;
    }


    public Object getObject(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getObject(parameterIndex);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(parameterIndex);
        }
        return o != null ? o.getValue() : null;
    }


    public Object getObject(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getObject(parameterName);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getNamedOutput().get(parameterName);
        }
        return o != null ? o.getValue() : null;
    }


    public Object getObject(int i, Map<String, Class<?>> map) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getObject(i, map);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(i);
        }
        return o != null ? o.getValue() : null;
    }


    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getObject(parameterName, map);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getNamedOutput().get(parameterName);
        }
        return o != null ? o.getValue() : null;
    }


    public Ref getRef(int i) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getRef(i);
        }
        throw new java.lang.UnsupportedOperationException ("Method getRef() not yet implemented.");
    }


    public Ref getRef(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getRef(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getRef() not yet implemented.");
    }


    public short getShort(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getShort(parameterIndex);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(parameterIndex);
        }
        return o != null ? ((Number)o.getValue()).shortValue() : 0;
    }


    public short getShort(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getShort(parameterName);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getNamedOutput().get(parameterName);
        }
        return o != null ? ((Number)o.getValue()).shortValue() : 0;
    }


    public String getString(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getString(parameterIndex);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(parameterIndex);
        }
        return o != null ? (o.getValue() != null ? o.getValue().toString() : null) : null;
    }


    public String getString(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getString(parameterName);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getNamedOutput().get(parameterName);
        }
        return o != null ? (o.getValue() != null ? o.getValue().toString() : null) : null;
    }


    public Time getTime(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getTime(parameterIndex);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(parameterIndex);
        }
        return o != null ? (Time)o.getValue() : null;
    }


    public Time getTime(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getTime(parameterName);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getNamedOutput().get(parameterName);
        }
        return o != null ? (Time)o.getValue() : null;
    }


    public Time getTime(int parameterIndex, Calendar cal) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getTime(parameterIndex, cal);
        }
        throw new java.lang.UnsupportedOperationException ("Method getTime() not yet implemented.");
    }


    public Time getTime(String parameterName, Calendar cal) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getTime(parameterName, cal);
        }
        throw new java.lang.UnsupportedOperationException ("Method getTime() not yet implemented.");
    }


    public Timestamp getTimestamp(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getTimestamp(parameterIndex);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getOrdinalOutput().get(parameterIndex);
        }
        return o != null ? (Timestamp)o.getValue() : null;
    }


    public Timestamp getTimestamp(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getTimestamp(parameterName);
        }
        OutputParameter o = null;
        if (executeResult != null)
        {
        	CallableResult cr = (CallableResult)executeResult;
        	o = cr.getNamedOutput().get(parameterName);
        }
        return o != null ? (Timestamp)o.getValue() : null;
    }


    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getTimestamp(parameterIndex, cal);
        }
        throw new java.lang.UnsupportedOperationException ("Method getTimestamp() not yet implemented.");
    }


    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getTimestamp(parameterName, cal);
        }
        throw new java.lang.UnsupportedOperationException ("Method getTimestamp() not yet implemented.");
    }


    public URL getURL(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getURL(parameterIndex);
        }
        throw new java.lang.UnsupportedOperationException ("Method getURL() not yet implemented.");
    }


    public URL getURL(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getURL(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getURL() not yet implemented.");
    }


    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).registerOutParameter(parameterIndex, sqlType);
        }
        else
        {
            p_vo.registerOutParameter(parameterIndex, sqlType);  
        }
    }


    public void registerOutParameter(String parameterName, int sqlType) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).registerOutParameter(parameterName, sqlType);
        }
        else
        {
            p_vo.registerOutParameter(parameterName, sqlType);  
        }
        
    }


    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).registerOutParameter(parameterIndex, sqlType, scale);
        }
        else
        {
            p_vo.registerOutParameter(parameterIndex, sqlType, scale);  
        }        
    }


    public void registerOutParameter(int paramIndex, int sqlType, String typeName) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).registerOutParameter(paramIndex, sqlType, typeName);
        }
        else
        {
            p_vo.registerOutParameter(paramIndex, sqlType, typeName);  
        }
    }


    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).registerOutParameter(parameterName, sqlType, scale);
        }
        else
        {
            p_vo.registerOutParameter(parameterName, sqlType, scale);  
        }
    }


    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException
    {      
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).registerOutParameter(parameterName, sqlType, typeName);
        }
        else
        {
            p_vo.registerOutParameter(parameterName, sqlType, typeName);  
        }
    }
    
    @Override
    protected boolean remote_execute() throws SQLException {
		//	Client -> remote sever
		log.finest("server => " + p_vo + ", Remote=" + DB.isRemoteObjects());
		try
		{
			if (DB.isRemoteObjects() && CConnection.get().isAppsServerOK(false))
			{
				Server server = CConnection.get().getServer();
				if (server != null)
				{
					executeResult = server.callable_execute(p_vo, SecurityToken.getInstance());
					p_vo.clearParameters();		//	re-use of result set
					return executeResult.isFirstResult();
				}
				log.log(Level.SEVERE, "AppsServer not found");
			}
			throw new IllegalStateException("Remote Connection - Application server not available");
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
	}

    /**
     * @see java.sql.CallableStatement#setAsciiStream(String, InputStream, int)
     */
	public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setAsciiStream(parameterName, x, length);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setAsciiStream() not yet implemented.");  
        }
    }

	/**
	 * @see java.sql.CallableStatement#setBigDecimal(String, BigDecimal)
	 */
    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException
    {        
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setBigDecimal(parameterName, x);
        }
        else
        {
            p_vo.setParameter(parameterName, x);  
        }
    }

    /**
     * @see java.sql.CallableStatement#setBinaryStream(String, InputStream, int)
     */
    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setBinaryStream(parameterName, x, length);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setBinaryStream() not yet implemented.");  
        }
        
    }

    /**
     * @see java.sql.CallableStatement#setBoolean(String, boolean)
     */
    public void setBoolean(String parameterName, boolean x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setBoolean(parameterName, x);
        }
        else
        {
            p_vo.setParameter(parameterName, x);  
        }
    }

    /**
     * @see java.sql.CallableStatement#setByte(String, byte)
     */
    public void setByte(String parameterName, byte x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setByte(parameterName, x);
        }
        else
        {
            p_vo.setParameter(parameterName, x);  
        }       
    }

    /**
     * @see java.sql.CallableStatement#setBytes(String, byte[])
     */
    public void setBytes(String parameterName, byte[] x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setBytes(parameterName, x);
        }
        else
        {
            p_vo.setParameter(parameterName, x);  
        }
    }

    /**
     * @see java.sql.CallableStatement#setCharacterStream(String, Reader, int)
     */
    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setCharacterStream(parameterName, reader, length);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setCharacterStream() not yet implemented.");  
        }
    }

    /**
     * @see java.sql.CallableStatement#setDate(String, Date)
     */
    public void setDate(String parameterName, Date x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setDate(parameterName, x);
        }
        else
        {
            p_vo.setParameter(parameterName, x);  
        }
    }

    /**
     * @see java.sql.CallableStatement#setDate(String, Date, Calendar)
     */
    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setDate(parameterName, x, cal);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setDate() not yet implemented.");  
        }
    }

    /**
     * @see java.sql.CallableStatement#setDouble(String, double)
     */
    public void setDouble(String parameterName, double x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setDouble(parameterName, x);
        }
        else
        {
            p_vo.setParameter(parameterName, x);  
        }
    }

    /**
     * @see java.sql.CallableStatement#setFloat(String, float)
     */
    public void setFloat(String parameterName, float x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setFloat(parameterName, x);
        }
        else
        {
            p_vo.setParameter(parameterName, x);  
        }
    }

    /**
     * @see java.sql.CallableStatement#setLong(String, long)
     */
    public void setInt(String parameterName, int x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setInt(parameterName, x);
        }
        else
        {
            p_vo.setParameter(parameterName, x);  
        }
    }

    /**
     * @see java.sql.CallableStatement#setLong(String, long)
     */
    public void setLong(String parameterName, long x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setLong(parameterName, x);
        }
        else
        {
            p_vo.setParameter(parameterName, x);  
        }        
    }

    /**
     * @see java.sql.CallableStatement#setNull(String, int)
     */
    public void setNull(String parameterName, int sqlType) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setNull(parameterName, sqlType);
        }
        else
        {
            p_vo.setParameter(parameterName, new NullParameter(sqlType));  
        } 
    }

    /**
     * @see java.sql.CallableStatement#setNull(String, int, String)
     */
    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setNull(parameterName, sqlType, typeName);
        }
        else
        {
            p_vo.setParameter(parameterName, new NullParameter(sqlType));  
        } 
    }

    /**
     * @see java.sql.CallableStatement#setObject(String, Object)
     */
    public void setObject(String parameterName, Object x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setObject(parameterName, x);
        }
        else
        {
            p_vo.setParameter(parameterName, x);  
        } 
    }

    /**
     * @see java.sql.CallableStatement#setObject(String, Object, int)
     */
    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setObject(parameterName, x, targetSqlType);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setObject() not yet implemented.");  
        } 
    }

    /**
     * @see java.sql.CallableStatement#setObject(String, Object, int, int)
     */
    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setObject(parameterName, x, targetSqlType, scale);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setObject() not yet implemented.");  
        }         
    }

    /**
     * @see java.sql.CallableStatement#setShort(String, short)
     */
    public void setShort(String parameterName, short x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setShort(parameterName, x);
        }
        else
        {
            p_vo.setParameter(parameterName, x);  
        } 
    }

    /**
     * @see java.sql.CallableStatement#setString(String, String)
     */
    public void setString(String parameterName, String x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setString(parameterName, x);
        }
        else
        {
            p_vo.setParameter(parameterName, x);  
        } 
    }

    /**
     * @see java.sql.CallableStatement#setTime(String, Time)
     */
    public void setTime(String parameterName, Time x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setTime(parameterName, x);
        }
        else
        {
            p_vo.setParameter(parameterName, x);  
        } 
    }


    /**
     * @see java.sql.CallableStatement#setTime(String, Time, Calendar)
     */
    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setTime(parameterName, x, cal);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setTime() not yet implemented.");  
        } 
    }

    /**
     * @see java.sql.CallableStatement#setTime(String, Time)
     */
    public void setTimestamp(String parameterName, Timestamp x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setTimestamp(parameterName, x);
        }
        else
        {
            p_vo.setParameter(parameterName, x);  
        } 
    }


    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setTimestamp(parameterName, x, cal);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setTimestamp() not yet implemented.");  
        } 
    }


    public void setURL(String parameterName, URL val) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setURL(parameterName, val);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setURL() not yet implemented.");  
        } 
    }


    public boolean wasNull() throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).wasNull();
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method wasNull() not yet implemented.");  
        } 
    }

	@Override
	public void fillParametersFromVO() {
		try
		{
			//ordinal input parameters
			ArrayList paramList = p_vo.getParameters();
			CallableStatement pstmt = (CallableStatement)p_stmt;
			for (int i = 0; i < paramList.size(); i++)
			{
				Object o = paramList.get(i);
				if (o == null)
				{
					throw new IllegalArgumentException ("Local - Null Parameter #" + i);
				}
				else if ( o instanceof OutputParameter)
				{
					OutputParameter op = (OutputParameter)o;
					if (op.getScale() != -1 )
						pstmt.registerOutParameter(i+1, op.getSqlType(), op.getScale());
					else if (op.getTypeName() != null)
						pstmt.registerOutParameter(i+1, op.getSqlType(), op.getTypeName());
					else
						pstmt.registerOutParameter(i+1, op.getSqlType());
				}
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
				else if (o instanceof java.sql.Date)
                {
                    pstmt.setDate(i+1, (java.sql.Date)o);
                    log.finest("#" + (i+1) + " - Date=" + o);
                }
	            else if (o instanceof java.util.Date)
	            {
	                pstmt.setTimestamp(i+1, new Timestamp(((java.util.Date)o).getTime()));
	                log.finest("#" + (i+1) + " - Date=" + o);
	            }
	            else if (o instanceof Double)
                {
                	pstmt.setDouble(i+1, (Double)o);
                    log.finest("#" + (i+1) + " - Double=" + o);
                }
                else if (o instanceof Float)
                {
                	pstmt.setFloat(i+1, (Float)o);
                    log.finest("#" + (i+1) + " - Double=" + o);
                }
				else
					throw new java.lang.UnsupportedOperationException ("Unknown Parameter Class=" + o.getClass());
			}
					
			//named input parameters					
			Map<String, Object> parameters = p_vo.getNamedParameters();
			for (Map.Entry<String, Object> e : parameters.entrySet())
			{
				Object o = e.getValue();
				if (o == null)
					throw new IllegalArgumentException ("Local - Null Parameter for " + e.getKey());
				else if (o instanceof NullParameter)
				{
					int type = ((NullParameter)o).getType();
					pstmt.setNull(e.getKey(), type);
					log.finest("#" + e.getKey() + " - Null");
				}
				else if (o instanceof Integer)
				{
					pstmt.setInt(e.getKey(), ((Integer)o).intValue());
					log.finest("#" + e.getKey() + " - int=" + o);
				}
				else if (o instanceof String)
				{
					pstmt.setString(e.getKey(), (String)o);
					log.finest("#" + e.getKey() + " - String=" + o);
				}
				else if (o instanceof Timestamp)
				{
					pstmt.setTimestamp(e.getKey(), (Timestamp)o);
					log.finest("#" + e.getKey() + " - Timestamp=" + o);
				}
				else if (o instanceof BigDecimal)
				{
					pstmt.setBigDecimal(e.getKey(), (BigDecimal)o);
					log.finest("#" + e.getKey() + " - BigDecimal=" + o);
				}
                else if (o instanceof java.util.Date)
                {
                    pstmt.setTimestamp(e.getKey(), new Timestamp(((java.util.Date)o).getTime()));
                    log.finest("#" + e.getKey() + " - Date=" + o);
                }
                else if (o instanceof java.sql.Date)
                {
                    pstmt.setTimestamp(e.getKey(), new Timestamp(((java.sql.Date)o).getTime()));
                    log.finest("#" + e.getKey() + " - Date=" + o);
                }
				else
					throw new java.lang.UnsupportedOperationException ("Unknown Parameter Class=" + o.getClass());
			}
			
			//named output parameters
			Map<String, OutputParameter>named = p_vo.getNamedOutput();
			for (Map.Entry<String, OutputParameter> e : named.entrySet())
			{
				String oi = e.getKey();
				OutputParameter op = e.getValue();
				if (op.getScale() != -1 )
					pstmt.registerOutParameter(oi, op.getSqlType(), op.getScale());
				else if (op.getTypeName() != null)
					pstmt.registerOutParameter(oi, op.getSqlType(), op.getTypeName());
				else
					pstmt.registerOutParameter(oi, op.getSqlType());
			}
		}
		catch (SQLException ex)
		{
            log.log(Level.SEVERE, "fillParametersFromVO", ex);
		}
	}

    

	/* Java 6 support - teo_sarca BF [ 1806700 ] *
	public Reader getCharacterStream(int parameterIndex) throws SQLException {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getCharacterStream(parameterIndex);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public Reader getCharacterStream(String parameterName) throws SQLException {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getCharacterStream(parameterName);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getNCharacterStream(parameterIndex);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public Reader getNCharacterStream(String parameterName) throws SQLException {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getNCharacterStream(parameterName);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public java.sql.NClob getNClob(int parameterIndex) throws SQLException {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getNClob(parameterIndex);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public java.sql.NClob getNClob(String parameterName) throws SQLException {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getNClob(parameterName);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public String getNString(int parameterIndex) throws SQLException {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getNString(parameterIndex);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public String getNString(String parameterName) throws SQLException {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getNString(parameterName);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public java.sql.RowId getRowId(int parameterIndex) throws SQLException {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getRowId(parameterIndex);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public java.sql.RowId getRowId(String parameterName) throws SQLException {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getRowId(parameterName);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public java.sql.SQLXML getSQLXML(int parameterIndex) throws SQLException {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getSQLXML(parameterIndex);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public java.sql.SQLXML getSQLXML(String parameterName) throws SQLException {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getSQLXML(parameterName);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setAsciiStream(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setAsciiStream(parameterName, x, length);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setBinaryStream(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setBinaryStream(parameterName, x, length);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setBlob(String parameterName, Blob x) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setBlob(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setBlob(parameterName, inputStream);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setBlob(parameterName, inputStream, length);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setCharacterStream(parameterName, reader);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setCharacterStream(parameterName, reader, length);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setClob(String parameterName, Clob x) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setClob(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setClob(String parameterName, Reader reader) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setClob(parameterName, reader);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setClob(String parameterName, Reader reader, long length) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setClob(parameterName, reader, length);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setNCharacterStream(parameterName, value);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setNCharacterStream(parameterName, value, length);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setNClob(String parameterName, java.sql.NClob value) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setNClob(parameterName, value);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setNClob(String parameterName, Reader reader) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setNCharacterStream(parameterName, reader);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setNClob(parameterName, reader, length);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setNString(String parameterName, String value) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setNString(parameterName, value);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setRowId(String parameterName, java.sql.RowId x) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setRowId(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public void setSQLXML(String parameterName, java.sql.SQLXML xmlObject) throws SQLException {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setSQLXML(parameterName, xmlObject);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}


	public boolean isWrapperFor(Class iface) throws SQLException {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).isWrapperFor(iface);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method not yet implemented.");  
        } 
	}
	/* Java 6 support - teo_sarca BF [ 1806700 ] */
}
