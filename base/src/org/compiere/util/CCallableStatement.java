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
import java.util.Calendar;
import java.util.Map;
import java.util.logging.Level;


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


    public Array getArray(int i) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getArray(i);
        }
        throw new java.lang.UnsupportedOperationException ("Method getArray() not yet implemented.");
    }


    public Array getArray(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getArray(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getArray() not yet implemented.");
    }


    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBigDecimal(parameterIndex);
        }
        throw new java.lang.UnsupportedOperationException ("Method getBigDecimal() not yet implemented.");
    }


    public BigDecimal getBigDecimal(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBigDecimal(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getBigDecimal() not yet implemented.");
    }


    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBigDecimal(parameterIndex, scale);
        }
        throw new java.lang.UnsupportedOperationException ("Method getBigDecimal() not yet implemented.");
    }


    public Blob getBlob(int i) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBlob(i);
        }
        throw new java.lang.UnsupportedOperationException ("Method getBlob() not yet implemented.");
    }


    public Blob getBlob(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBlob(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getBlob() not yet implemented.");
    }


    public boolean getBoolean(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBoolean(parameterIndex);
        }
        throw new java.lang.UnsupportedOperationException ("Method getBoolean() not yet implemented.");
    }


    public boolean getBoolean(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBoolean(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getBoolean() not yet implemented.");
    }


    public byte getByte(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getByte(parameterIndex);
        }
        throw new java.lang.UnsupportedOperationException ("Method getByte() not yet implemented.");
    }


    public byte getByte(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getByte(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getByte() not yet implemented.");
    }


    public byte[] getBytes(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBytes(parameterIndex);
        }
        throw new java.lang.UnsupportedOperationException ("Method getBytes() not yet implemented.");
    }


    public byte[] getBytes(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getBytes(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getBytes() not yet implemented.");
    }


    public Clob getClob(int i) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getClob(i);
        }
        throw new java.lang.UnsupportedOperationException ("Method getClob() not yet implemented.");
    }


    public Clob getClob(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getClob(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getClob() not yet implemented.");
    }


    public Date getDate(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getDate(parameterIndex);
        }
        throw new java.lang.UnsupportedOperationException ("Method getDate() not yet implemented.");
    }


    public Date getDate(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getDate(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getDate() not yet implemented.");
    }


    public Date getDate(int parameterIndex, Calendar cal) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getDate(parameterIndex, cal);
        }
        throw new java.lang.UnsupportedOperationException ("Method getDate() not yet implemented.");
    }


    public Date getDate(String parameterName, Calendar cal) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getDate(parameterName, cal);
        }
        throw new java.lang.UnsupportedOperationException ("Method getDate() not yet implemented.");
    }


    public double getDouble(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getDouble(parameterIndex);
        }
        throw new java.lang.UnsupportedOperationException ("Method getDouble() not yet implemented.");
    }


    public double getDouble(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getDouble(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getDouble() not yet implemented.");
    }


    public float getFloat(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getFloat(parameterIndex);
        }
        throw new java.lang.UnsupportedOperationException ("Method getFloat() not yet implemented.");
    }


    public float getFloat(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getFloat(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getFloat() not yet implemented.");
    }


    public int getInt(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getInt(parameterIndex);
        }
        throw new java.lang.UnsupportedOperationException ("Method getInt() not yet implemented.");
    }


    public int getInt(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getInt(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getInt() not yet implemented.");
    }


    public long getLong(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getLong(parameterIndex);
        }
        throw new java.lang.UnsupportedOperationException ("Method getLong() not yet implemented.");
    }


    public long getLong(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getLong(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getLong() not yet implemented.");
    }


    public Object getObject(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getObject(parameterIndex);
        }
        throw new java.lang.UnsupportedOperationException ("Method getObject() not yet implemented.");
    }


    public Object getObject(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getObject(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getObject() not yet implemented.");
    }


    public Object getObject(int i, Map<String, Class<?>> map) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getObject(i, map);
        }
        throw new java.lang.UnsupportedOperationException ("Method getObject() not yet implemented.");
    }


    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getObject(parameterName, map);
        }
        throw new java.lang.UnsupportedOperationException ("Method getObject() not yet implemented.");
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
        throw new java.lang.UnsupportedOperationException ("Method getShort() not yet implemented.");
    }


    public short getShort(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getShort(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getShort() not yet implemented.");
    }


    public String getString(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getString(parameterIndex);
        }
        throw new java.lang.UnsupportedOperationException ("Method getString() not yet implemented.");
    }


    public String getString(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getString(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getString() not yet implemented.");
    }


    public Time getTime(int parameterIndex) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getTime(parameterIndex);
        }
        throw new java.lang.UnsupportedOperationException ("Method getTime() not yet implemented.");
    }


    public Time getTime(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getTime(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getTime() not yet implemented.");
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
        throw new java.lang.UnsupportedOperationException ("Method getTimestamp() not yet implemented.");
    }


    public Timestamp getTimestamp(String parameterName) throws SQLException
    {
        if (p_stmt != null)
        {
            return ((CallableStatement)p_stmt).getTimestamp(parameterName);
        }
        throw new java.lang.UnsupportedOperationException ("Method getTimestamp() not yet implemented.");
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
            throw new java.lang.UnsupportedOperationException ("Method registerOutParameter() not yet implemented.");  
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
            throw new java.lang.UnsupportedOperationException ("Method registerOutParameter() not yet implemented.");  
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
            throw new java.lang.UnsupportedOperationException ("Method registerOutParameter() not yet implemented.");  
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
            throw new java.lang.UnsupportedOperationException ("Method registerOutParameter() not yet implemented.");  
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
            throw new java.lang.UnsupportedOperationException ("Method registerOutParameter() not yet implemented.");  
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
            throw new java.lang.UnsupportedOperationException ("Method registerOutParameter() not yet implemented.");  
        }
    }


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


    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException
    {        
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setBigDecimal(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setBigDecimal() not yet implemented.");  
        }
    }


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


    public void setBoolean(String parameterName, boolean x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setBoolean(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setBoolean() not yet implemented.");  
        }
    }


    public void setByte(String parameterName, byte x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setByte(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setByte() not yet implemented.");  
        }       
    }


    public void setBytes(String parameterName, byte[] x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setBytes(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setBytes() not yet implemented.");  
        }
    }


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


    public void setDate(String parameterName, Date x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setDate(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setDate() not yet implemented.");  
        }
    }


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


    public void setDouble(String parameterName, double x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setDouble(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setDouble() not yet implemented.");  
        }
    }


    public void setFloat(String parameterName, float x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setFloat(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setFloat() not yet implemented.");  
        }
    }


    public void setInt(String parameterName, int x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setInt(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setInt() not yet implemented.");  
        }
    }


    public void setLong(String parameterName, long x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setLong(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setLong() not yet implemented.");  
        }        
    }


    public void setNull(String parameterName, int sqlType) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setNull(parameterName, sqlType);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setNull() not yet implemented.");  
        } 
    }


    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setNull(parameterName, sqlType, typeName);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setNull() not yet implemented.");  
        } 
    }


    public void setObject(String parameterName, Object x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setObject(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setObject() not yet implemented.");  
        } 
    }


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


    public void setShort(String parameterName, short x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setShort(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setShort() not yet implemented.");  
        } 
    }


    public void setString(String parameterName, String x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setString(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setString() not yet implemented.");  
        } 
    }


    public void setTime(String parameterName, Time x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setTime(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setTime() not yet implemented.");  
        } 
    }


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


    public void setTimestamp(String parameterName, Timestamp x) throws SQLException
    {
        if (p_stmt != null)
        {
            ((CallableStatement)p_stmt).setTimestamp(parameterName, x);
        }
        else
        {
            throw new java.lang.UnsupportedOperationException ("Method setTimestamp() not yet implemented.");  
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
