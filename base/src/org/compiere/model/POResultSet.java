/*******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution * Copyright (C)
 * 1999-2006 Adempiere, Inc. All Rights Reserved. * This program is free
 * software; you can redistribute it and/or modify it * under the terms version
 * 2 of the GNU General Public License as published * by the Free Software
 * Foundation. This program is distributed in the hope * that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied * warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. * See the GNU General
 * Public License for more details. * You should have received a copy of the GNU
 * General Public License along * with this program; if not, write to the Free
 * Software Foundation, Inc., * 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307 USA. *
 * 
 * Copyright (C) 2007 Low Heng Sin hengsin@avantz.com 
 * Contributor(s): 
 * __________________________________________
 ******************************************************************************/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Simple wrapper over jdbc resultset
 * @author Low Heng Sin
 *
 */
public class POResultSet<T extends PO> {

	private String trxName;
	private ResultSet resultSet;
	private MTable table;
	private PreparedStatement statement;

	/**
	 * 
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
	}
	
	/**
	 * 
	 * @return PO or null if reach the end of resultset
	 * @throws SQLException
	 */
	public T next() throws SQLException {
		if ( resultSet.next() ) {
			return (T) table.getPO(resultSet, trxName);
		} else {
			return null;
		}
	}
	
	/**
	 * Release database resources.
	 */
	public void close() {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
			}
		}
		try {
			resultSet.close();
		} catch (SQLException e) {
		}
	}
}
