/*
 * Name:		PreparedStatementWrapper.java
 * Description:	Wrapper around PreparedStatement implementing toString method useful for logging
 * Created:		Feb 2, 2010
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/PreparedStatementWrapper.java
 * FileOwner:	spc.dvp
 * FilePerms:	0644
 *
 */

/**
 * This file is part of Adempiere ERP Bazaar
 * http://www.adempiere.org
 * 
 * Copyright (C) Stefan Christians
 * Copyright (C) Contributors
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * Contributors:
 * - Stefan Christians
 * 
 * Sponsors:
 * - K.K. Alice
 * 
 */

package com.kkalice.adempiere.migrate;

import java.io.*;
import java.math.*;
import java.net.*;
import java.sql.*;
import java.util.*;


/**
 * Wrapper around PreparedStatement implementing toString method useful for logging
 * @author Stefan Christians
 */
public class PreparedStatementWrapper implements PreparedStatement {

	// MEMBERS
	// =======
	
	/** the actual prepared statement */
	private PreparedStatement m_preparedStatement;
	
	/** original sql string */
	private String m_sql;
	
	/** list of variables used for parameter substitution */
	private HashMap<Integer, String> m_variables = new HashMap<Integer, String> ();
	
	
	// CONSTRUCTORS
	// ============
	
	public PreparedStatementWrapper (Connection dbcon, String sql) throws SQLException {
		
		// must have valid connection
		if (dbcon == null)
			throw new SQLException("connection is null");
		
		// remember the sql string
		m_sql = sql;

		// create the actual prepared statement
		m_preparedStatement = dbcon.prepareStatement(sql);
		
		// initialize list of variables
		m_variables = new HashMap<Integer, String> ();
		
	}
	
	// METHODS
	// =======

	/**
	 * String representation of the SQL statement in the database.<p>
	 * Some jdbc drivers do not return a readable sql statement but only
	 * an object reference when the toString() method is called on a 
	 * prepared statement.<p>
	 * This wrapper replaces the parameters in the prepared statement
	 * with the actual values which were set to produce human readable
	 * output in the log.<p>
	 * Note that the resulting SQL string may still not be executable
	 * in the target database, as java's toString() method is used to
	 * represent each object in the SQL string, disregarding syntax 
	 * conventions required by the database.
	 * @return a string representation of this prepared statement
	 */
	public String toString () {
		StringBuffer result = new StringBuffer();
		
		// iterate through SQL string and replace ? with variables
		// (ignore escaped ? or literal ? within strings)
		boolean isEscaped = false;
		boolean isString = false;
		int parameterIndex = 0;
		for (int i = 0; i < m_sql.length(); i++) {
			char charValue = m_sql.charAt(i);
			switch (charValue) {
			case '\\': // escapes next character
				isEscaped = ! isEscaped;
				result.append(charValue);
				break;
			case '\'': // string delimiter
				if (!isEscaped)
					isString = ! isString;
				result.append(charValue);
				isEscaped = false;
				break;
			case '?': // parameter token
				if (!isEscaped && !isString) {
					parameterIndex++;
					if (m_variables.containsKey(parameterIndex)) {
						result.append(m_variables.get(parameterIndex));
					} else {
						result.append(charValue);
					}
				} else {
					result.append(charValue);
					isEscaped = false;
				}
				break;
			default:
				result.append(charValue);
				isEscaped = false;
				break;
			}
		}
		
		return result.toString();
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setArray(int, java.sql.Array)
	 */
	public void setArray(int i, Array x) throws SQLException {
		m_preparedStatement.setArray(i, x);

		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(i, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setAsciiStream(int, java.io.InputStream)
	 */
	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		m_preparedStatement.setAsciiStream(parameterIndex, x);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setAsciiStream(int, java.io.InputStream, int)
	 */
	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
		m_preparedStatement.setAsciiStream(parameterIndex, x, length);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setAsciiStream(int, java.io.InputStream, long)
	 */
	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		m_preparedStatement.setAsciiStream(parameterIndex, x, length);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setBigDecimal(int, java.math.BigDecimal)
	 */
	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		m_preparedStatement.setBigDecimal(parameterIndex, x);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setBinaryStream(int, java.io.InputStream)
	 */
	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		m_preparedStatement.setBinaryStream(parameterIndex, x);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setBinaryStream(int, java.io.InputStream, int)
	 */
	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
		m_preparedStatement.setBinaryStream(parameterIndex, x, length);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setBinaryStream(int, java.io.InputStream, long)
	 */
	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		m_preparedStatement.setBinaryStream(parameterIndex, x, length);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setBlob(int, java.sql.Blob)
	 */
	public void setBlob(int i, Blob x) throws SQLException {
		m_preparedStatement.setBlob(i, x);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(i, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setBlob(int, java.io.InputStream)
	 */
	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		m_preparedStatement.setBlob(parameterIndex, inputStream);
		
		String str = "null";
		if (inputStream!=null)
			str = inputStream.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setBlob(int, java.io.InputStream, long)
	 */
	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		m_preparedStatement.setBlob(parameterIndex, inputStream, length);
		
		String str = "null";
		if (inputStream!=null)
			str = inputStream.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setBoolean(int, boolean)
	 */
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		m_preparedStatement.setBoolean(parameterIndex, x);
		m_variables.put(parameterIndex, Boolean.valueOf(x).toString());
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setByte(int, byte)
	 */
	public void setByte(int parameterIndex, byte x) throws SQLException {
		m_preparedStatement.setByte(parameterIndex, x);
		m_variables.put(parameterIndex, Byte.valueOf(x).toString());
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setBytes(int, byte[])
	 */
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		m_preparedStatement.setBytes(parameterIndex, x);

		if (x==null) {
			m_variables.put(parameterIndex, "null");
		} else {
			ArrayList<Byte> obj = new ArrayList<Byte>();
			for(byte b : x) {
				obj.add(Byte.valueOf(b));
			}
			m_variables.put(parameterIndex, obj.toString());
		}
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setCharacterStream(int, java.io.Reader)
	 */
	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		m_preparedStatement.setCharacterStream(parameterIndex, reader);
		
		String str = "null";
		if (reader!=null)
			str = reader.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setCharacterStream(int, java.io.Reader, int)
	 */
	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
		m_preparedStatement.setCharacterStream(parameterIndex, reader, length);
		
		String str = "null";
		if (reader!=null)
			str = reader.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setCharacterStream(int, java.io.Reader, long)
	 */
	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		m_preparedStatement.setCharacterStream(parameterIndex, reader, length);
		
		String str = "null";
		if (reader!=null)
			str = reader.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setNCharacterStream(int, java.io.Reader)
	 */
	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		m_preparedStatement.setNCharacterStream(parameterIndex, value);
		
		String str = "null";
		if (value!=null)
			str = value.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setNCharacterStream(int, java.io.Reader, long)
	 */
	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		m_preparedStatement.setNCharacterStream(parameterIndex, value, length);
		
		String str = "null";
		if (value!=null)
			str = value.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setClob(int, java.sql.Clob)
	 */
	public void setClob(int i, Clob x) throws SQLException {
		m_preparedStatement.setClob(i, x);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(i, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setClob(int, java.io.Reader)
	 */
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		m_preparedStatement.setClob(parameterIndex, reader);
		
		String str = "null";
		if (reader!=null)
			str = reader.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setClob(int, java.io.Reader, long)
	 */
	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		m_preparedStatement.setClob(parameterIndex, reader, length);
		
		String str = "null";
		if (reader!=null)
			str = reader.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setNClob(int, java.sql.NClob)
	 */
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		m_preparedStatement.setNClob(parameterIndex, value);
		
		String str = "null";
		if (value!=null)
			str = value.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setNClob(int, java.io.Reader)
	 */
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		m_preparedStatement.setNClob(parameterIndex, reader);
		
		String str = "null";
		if (reader!=null)
			str = reader.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setNClob(int, java.io.Reader, long)
	 */
	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		m_preparedStatement.setNClob(parameterIndex, reader, length);
		
		String str = "null";
		if (reader!=null)
			str = reader.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setDate(int, java.sql.Date)
	 */
	public void setDate(int parameterIndex, java.sql.Date x) throws SQLException {
		m_preparedStatement.setDate(parameterIndex, x);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setDate(int, java.sql.Date, java.util.Calendar)
	 */
	public void setDate(int parameterIndex, java.sql.Date x, Calendar cal) throws SQLException {
		m_preparedStatement.setDate(parameterIndex, x, cal);
        
		if (x==null || cal==null) {
			m_variables.put(parameterIndex, "null");
		} else {
			cal.setTime(new java.util.Date(x.getTime()));
			m_variables.put(parameterIndex, cal.toString());
		}
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setDouble(int, double)
	 */
	public void setDouble(int parameterIndex, double x) throws SQLException {
		m_preparedStatement.setDouble(parameterIndex, x);
		m_variables.put(parameterIndex, Double.valueOf(x).toString());
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setFloat(int, float)
	 */
	public void setFloat(int parameterIndex, float x) throws SQLException {
		m_preparedStatement.setFloat(parameterIndex, x);
		m_variables.put(parameterIndex, Float.valueOf(x).toString());
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setInt(int, int)
	 */
	public void setInt(int parameterIndex, int x) throws SQLException {
		m_preparedStatement.setInt(parameterIndex, x);
		m_variables.put(parameterIndex, Integer.valueOf(x).toString());
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setLong(int, long)
	 */
	public void setLong(int parameterIndex, long x) throws SQLException {
		m_preparedStatement.setLong(parameterIndex, x);
		m_variables.put(parameterIndex, Long.valueOf(x).toString());
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setNull(int, int)
	 */
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		m_preparedStatement.setNull(parameterIndex, sqlType);
		m_variables.put(parameterIndex, "null");
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setNull(int, int, java.lang.String)
	 */
	public void setNull(int paramIndex, int sqlType, String typeName) throws SQLException {
		m_preparedStatement.setNull(paramIndex, sqlType, typeName);
		m_variables.put(paramIndex, "null");
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setObject(int, java.lang.Object)
	 */
	public void setObject(int parameterIndex, Object x) throws SQLException {
		m_preparedStatement.setObject(parameterIndex, x);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setObject(int, java.lang.Object, int)
	 */
	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
		m_preparedStatement.setObject(parameterIndex, x, targetSqlType);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setObject(int, java.lang.Object, int, int)
	 */
	public void setObject(int parameterIndex, Object x, int targetSqlType, int scale) throws SQLException {
		m_preparedStatement.setObject(parameterIndex, x, targetSqlType, scale);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setRef(int, java.sql.Ref)
	 */
	public void setRef(int i, Ref x) throws SQLException {
		m_preparedStatement.setRef(i, x);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(i, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setRowId(int, java.sql.RowId)
	 */
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		m_preparedStatement.setRowId(parameterIndex, x);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setShort(int, short)
	 */
	public void setShort(int parameterIndex, short x) throws SQLException {
		m_preparedStatement.setShort(parameterIndex, x);
		m_variables.put(parameterIndex, Short.valueOf(x).toString());
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setSQLXML(int, java.sql.SQLXML)
	 */
	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		m_preparedStatement.setSQLXML(parameterIndex, xmlObject);
		
		String str = "null";
		if (xmlObject!=null)
			str = xmlObject.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setString(int, java.lang.String)
	 */
	public void setString(int parameterIndex, String x) throws SQLException {
		m_preparedStatement.setString(parameterIndex, x);

		if (x==null) {
			m_variables.put(parameterIndex, "null");
		} else {
			m_variables.put(parameterIndex, new StringBuffer("'").append(x).append("'").toString());
		}
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setNString(int, java.lang.String)
	 */
	public void setNString(int parameterIndex, String value) throws SQLException {
		m_preparedStatement.setNString(parameterIndex, value);

		if (value==null) {
			m_variables.put(parameterIndex, "null");
		} else {
			m_variables.put(parameterIndex, new StringBuffer("'").append(value).append("'").toString());
		}
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setTime(int, java.sql.Time)
	 */
	public void setTime(int parameterIndex, Time x) throws SQLException {
		m_preparedStatement.setTime(parameterIndex, x);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setTime(int, java.sql.Time, java.util.Calendar)
	 */
	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
		m_preparedStatement.setTime(parameterIndex, x, cal);

		if (x==null || cal==null) {
			m_variables.put(parameterIndex, "null");
		} else {
			cal.setTime(new java.util.Date(x.getTime()));
			m_variables.put(parameterIndex, cal.toString());
		}
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setTimestamp(int, java.sql.Timestamp)
	 */
	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
		m_preparedStatement.setTimestamp(parameterIndex, x);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setTimestamp(int, java.sql.Timestamp, java.util.Calendar)
	 */
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
		m_preparedStatement.setTimestamp(parameterIndex, x, cal);

		if (x==null || cal==null) {
			m_variables.put(parameterIndex, "null");
		} else {
			cal.setTime(new java.util.Date(x.getTime()));
			m_variables.put(parameterIndex, cal.toString());
		}
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setURL(int, java.net.URL)
	 */
	public void setURL(int parameterIndex, URL x) throws SQLException {
		m_preparedStatement.setURL(parameterIndex, x);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setUnicodeStream(int, java.io.InputStream, int)
	 */
	@SuppressWarnings("deprecation")
	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
		m_preparedStatement.setUnicodeStream(parameterIndex, x, length);
		
		String str = "null";
		if (x!=null)
			str = x.toString();
		m_variables.put(parameterIndex, str);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#addBatch()
	 */
	public void addBatch() throws SQLException {
		m_preparedStatement.addBatch();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#addBatch(java.lang.String)
	 */
	public void addBatch(String sql) throws SQLException {
		m_preparedStatement.addBatch(sql);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#cancel()
	 */
	public void cancel() throws SQLException {
		m_preparedStatement.cancel();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#clearBatch()
	 */
	public void clearBatch() throws SQLException {
		m_preparedStatement.clearBatch();
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#clearParameters()
	 */
	public void clearParameters() throws SQLException {
		m_preparedStatement.clearParameters();
		m_variables = new HashMap<Integer, String> ();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#clearWarnings()
	 */
	public void clearWarnings() throws SQLException {
		m_preparedStatement.clearWarnings();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#close()
	 */
	public void close() throws SQLException {
		m_preparedStatement.close();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#isClosed()
	 */
	public boolean isClosed() throws SQLException {
		return m_preparedStatement.isClosed();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#execute(java.lang.String)
	 */
	public boolean execute(String sql) throws SQLException {
		return m_preparedStatement.execute(sql);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#execute(java.lang.String, int)
	 */
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		return m_preparedStatement.execute(sql, autoGeneratedKeys);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#execute(java.lang.String, int[])
	 */
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		return m_preparedStatement.execute(sql, columnIndexes);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#execute(java.lang.String, java.lang.String[])
	 */
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		return m_preparedStatement.execute(sql, columnNames);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#executeBatch()
	 */
	public int[] executeBatch() throws SQLException {
		return m_preparedStatement.executeBatch();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#executeQuery(java.lang.String)
	 */
	public ResultSet executeQuery(String sql) throws SQLException {
		return m_preparedStatement.executeQuery(sql);
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#execute()
	 */
	public boolean execute() throws SQLException {
		return m_preparedStatement.execute();
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#executeQuery()
	 */
	public ResultSet executeQuery() throws SQLException {
		return m_preparedStatement.executeQuery();
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#executeUpdate()
	 */
	public int executeUpdate() throws SQLException {
		return m_preparedStatement.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#getMetaData()
	 */
	public ResultSetMetaData getMetaData() throws SQLException {
		return m_preparedStatement.getMetaData();
	}

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#getParameterMetaData()
	 */
	public ParameterMetaData getParameterMetaData() throws SQLException {
		return m_preparedStatement.getParameterMetaData();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#executeUpdate(java.lang.String)
	 */
	public int executeUpdate(String sql) throws SQLException {
		return m_preparedStatement.executeUpdate(sql);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#executeUpdate(java.lang.String, int)
	 */
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		return m_preparedStatement.executeUpdate(sql, autoGeneratedKeys);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#executeUpdate(java.lang.String, int[])
	 */
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		return m_preparedStatement.executeUpdate(sql, columnIndexes);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#executeUpdate(java.lang.String, java.lang.String[])
	 */
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		return m_preparedStatement.executeUpdate(sql, columnNames);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getConnection()
	 */
	public Connection getConnection() throws SQLException {
		return m_preparedStatement.getConnection();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getFetchDirection()
	 */
	public int getFetchDirection() throws SQLException {
		return m_preparedStatement.getFetchDirection();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getFetchSize()
	 */
	public int getFetchSize() throws SQLException {
		return m_preparedStatement.getFetchSize();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getGeneratedKeys()
	 */
	public ResultSet getGeneratedKeys() throws SQLException {
		return m_preparedStatement.getGeneratedKeys();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getMaxFieldSize()
	 */
	public int getMaxFieldSize() throws SQLException {
		return m_preparedStatement.getMaxFieldSize();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getMaxRows()
	 */
	public int getMaxRows() throws SQLException {
		return m_preparedStatement.getMaxRows();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getMoreResults()
	 */
	public boolean getMoreResults() throws SQLException {
		return m_preparedStatement.getMoreResults();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getMoreResults(int)
	 */
	public boolean getMoreResults(int current) throws SQLException {
		return m_preparedStatement.getMoreResults(current);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getQueryTimeout()
	 */
	public int getQueryTimeout() throws SQLException {
		return m_preparedStatement.getQueryTimeout();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getResultSet()
	 */
	public ResultSet getResultSet() throws SQLException {
		return m_preparedStatement.getResultSet();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getResultSetConcurrency()
	 */
	public int getResultSetConcurrency() throws SQLException {
		return m_preparedStatement.getResultSetConcurrency();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getResultSetHoldability()
	 */
	public int getResultSetHoldability() throws SQLException {
		return m_preparedStatement.getResultSetHoldability();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getResultSetType()
	 */
	public int getResultSetType() throws SQLException {
		return m_preparedStatement.getResultSetType();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getUpdateCount()
	 */
	public int getUpdateCount() throws SQLException {
		return m_preparedStatement.getUpdateCount();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#getWarnings()
	 */
	public SQLWarning getWarnings() throws SQLException {
		return m_preparedStatement.getWarnings();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setCursorName(java.lang.String)
	 */
	public void setCursorName(String name) throws SQLException {
		m_preparedStatement.setCursorName(name);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setEscapeProcessing(boolean)
	 */
	public void setEscapeProcessing(boolean enable) throws SQLException {
		m_preparedStatement.setEscapeProcessing(enable);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setFetchDirection(int)
	 */
	public void setFetchDirection(int direction) throws SQLException {
		m_preparedStatement.setFetchDirection(direction);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setFetchSize(int)
	 */
	public void setFetchSize(int rows) throws SQLException {
		m_preparedStatement.setFetchSize(rows);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setMaxFieldSize(int)
	 */
	public void setMaxFieldSize(int max) throws SQLException {
		m_preparedStatement.setMaxFieldSize(max);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setMaxRows(int)
	 */
	public void setMaxRows(int max) throws SQLException {
		m_preparedStatement.setMaxRows(max);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setQueryTimeout(int)
	 */
	public void setQueryTimeout(int seconds) throws SQLException {
		m_preparedStatement.setQueryTimeout(seconds);
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#isPoolable()
	 */
	public boolean isPoolable() throws SQLException {
		return m_preparedStatement.isPoolable();
	}

	/* (non-Javadoc)
	 * @see java.sql.Statement#setPoolable(boolean)
	 */
	public void setPoolable(boolean poolable) throws SQLException {
		m_preparedStatement.setPoolable(poolable);
	}

	/* (non-Javadoc)
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return m_preparedStatement.isWrapperFor(iface);
	}

	/* (non-Javadoc)
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return m_preparedStatement.unwrap(iface);
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		m_preparedStatement.closeOnCompletion();
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		return m_preparedStatement.isCloseOnCompletion();
	}
	
}
