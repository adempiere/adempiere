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
 * Copyright (C) 2007 Low Heng Sin hengsin@avantz.com                         *
 * Contributor(s):                                                            *
 * Teo Sarca, SC ARHIPAC SERVICE SRL                                          *
 * __________________________________________                                 *
 *****************************************************************************/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.adempiere.exceptions.DBException;
import org.compiere.util.DB;

/**
 * Simple wrapper over jdbc resultset
 * @author Low Heng Sin
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>FR [ 1984834 ] Add POResultSet.hasNext convenient method
 * 			<li>FR [ 1985134 ] POResultSet improvements
 */
public class POResultSet<T extends PO> {

	private String trxName;
	private ResultSet resultSet;
	private MTable table;
	private PreparedStatement statement;
	/** Current fetched PO */
	private T currentPO = null;
	/** Should we close the statement and resultSet on any exception that occur ? */
	private boolean closeOnError = true;

	/**
	 * Constructs the POResultSet.
	 * By default, closeOnError option is false. You need to set it explicitly.
	 * @param table
	 * @param ps
	 * @param rs
	 * @param trxName
	 */
	public POResultSet(MTable table, PreparedStatement ps, ResultSet rs, String trxName) {
		this.table = table;
		this.statement = ps;
		this.resultSet = rs;
		this.trxName = trxName;
		this.closeOnError = false;
	}
	
	/**
	 * 
	 * @return true if it has next, false otherwise
	 * @throws DBException
	 */
	public boolean hasNext() throws DBException {
		if (currentPO != null)
			return true;
		currentPO = next();
		return currentPO != null;
	}
	
	/**
	 * 
	 * @return PO or null if reach the end of resultset
	 * @throws DBException
	 */
	public T next() throws DBException {
		if (currentPO != null) {
			T po = currentPO;
			currentPO = null;
			return po;
		}
		try {
			if ( resultSet.next() ) {
				return (T) table.getPO(resultSet, trxName);
			} else {
				this.close(); // close it if there is no more data to read
				return null;
			}
		}
		catch (SQLException e) {
			if (this.closeOnError) {
				this.close();
			}
			throw new DBException(e);
		}
		// Catching any RuntimeException, and close the resultset (if closeOnError is set)
		catch (RuntimeException e) {
			if (this.closeOnError) {
				this.close();
			}
			throw e;
		}
	}
	
	/**
	 * Should we automatically close the {@link PreparedStatement} and {@link ResultSet} in case
	 * we get an error.
	 * @param closeOnError
	 */
	public void setCloseOnError(boolean closeOnError) {
		this.closeOnError = closeOnError;
	}

	/**
	 * Will be the {@link PreparedStatement} and {@link ResultSet} closed on any database exception
	 * @return true if yes, false otherwise
	 */
	public boolean isCloseOnError() {
		return this.closeOnError;
	}
	
	/**
	 * Release database resources.
	 */
	public void close() {
		DB.close(this.resultSet, this.statement);
		this.resultSet = null;
		this.statement = null;
		currentPO = null;
	}
}
