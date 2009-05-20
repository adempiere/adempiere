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
package org.adempiere.exceptions;

import java.sql.SQLException;

import org.compiere.util.CLogMgt;
import org.compiere.util.DB;


/**
 * This RuntimeException is used to pass SQLException up the chain of calling
 * methods to determine what to do where needed.
 *
 * @author Vincent Harcq
 * @version $Id: DBException.java,v 1.2 2006/07/30 00:54:35 jjanke Exp $
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * @author Armen Rizal, GOODWILL CONSULTING
 * 		FR [2789943] Better DBException handling for PostgreSQL
 * 		https://sourceforge.net/tracker/?func=detail&aid=2789943&group_id=176962&atid=879335
 */
public class DBException extends AdempiereException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4264201718343118625L;
	private String m_sql = null;

	/**
	 * Create a new DBException based on a SQLException
	 * @param e Specicy the Exception cause
	 */
	public DBException(Exception e)
	{
		super(e);
		if (CLogMgt.isLevelFinest())
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create a new DBException based on a SQLException and SQL Query
	 * @param e exception
	 * @param sql sql query
	 */
	public DBException(SQLException e, String sql)
	{
		this(e);
		m_sql = sql;
	}

	/**
	 * Create a new DBException
	 * @param msg Message
	 */
	public DBException(String msg)
	{
		super(msg);
	}

	/**
	 * @return SQL Query or null
	 */
	public String getSQL()
	{
		return m_sql;
	}

	/**
	 * @return Wrapped SQLException or null
	 */
	public SQLException getSQLException() {
		Throwable cause = getCause();
		if (cause instanceof SQLException)
			return (SQLException)cause;
		return null;
	}

	/**
	 * @see java.sql.SQLException#getErrorCode()
	 */
    public int getErrorCode() {
    	SQLException e = getSQLException();
    	return (e != null ? e.getErrorCode() : -1);
    }

    /**
     * @see java.sql.SQLException#getNextException()
     */
    public SQLException getNextException() {
    	SQLException e = getSQLException();
    	return (e != null ? e.getNextException() : null);
    }

    /**
     * @see java.sql.SQLException#getSQLState()
     */
    public String getSQLState() {
    	SQLException e = getSQLException();
    	return (e != null ? e.getSQLState() : null);
    }


    private static final boolean isErrorCode(Exception e, int errorCode) {
    	if (e == null) {
    		return false;
    	}
    	else if (e instanceof SQLException) {
    		return ((SQLException)e).getErrorCode() == errorCode;
    	}
    	else if (e instanceof DBException) {
    		SQLException sqlEx = ((DBException)e).getSQLException();
    		if (sqlEx != null)
    			return sqlEx.getErrorCode() == errorCode;
    		else
    			return false;
    	}
    	return false;
    }

    private static final boolean isSQLState(Exception e, String SQLState) {
    	if (e == null) {
    		return false;
    	}
    	else if (e instanceof SQLException) {
    		return ((SQLException)e).getSQLState().equals(SQLState);
    	}
    	else if (e instanceof DBException) {
    		SQLException sqlEx = ((DBException)e).getSQLException();
    		if (sqlEx != null)
    			return sqlEx.getSQLState().equals(SQLState);
    		else
    			return false;
    	}
    	return false;
    }

    /**
     * Check if Unique Constraint Exception (aka ORA-00001)
     * @param e exception
     */
    public static boolean isUniqueContraintError(Exception e) {
    	if (DB.isPostgreSQL())
    		return isSQLState(e, "23505");
    	//
    	return isErrorCode(e, 1);
    }

    /**
     * Check if "child record found" exception (aka ORA-02292)
     * @param e exception
     */
    public static boolean isChildRecordFoundError(Exception e) {
    	if (DB.isPostgreSQL())
    		return isSQLState(e, "23503");
    	return isErrorCode(e, 2292);
    }

    /**
     * Check if "invalid identifier" exception (aka ORA-00904)
     * @param e exception
     */
    public static boolean isInvalidIdentifierError(Exception e) {
    	return isErrorCode(e, 904);
    }

    /**
     * Check if "invalid username/password" exception (aka ORA-01017)
     * @param e exception
     */
    public static boolean isInvalidUserPassError(Exception e) {
    	return isErrorCode(e, 1017);
    }

    /**
     * Check if "time out" exception (aka ORA-01013)
     * @param e
     */
    public static boolean isTimeout(Exception e) {
    	if (DB.isPostgreSQL())
    		return isSQLState(e, "57014");
    	return isErrorCode(e, 1013);
    }
}	//	DBException
