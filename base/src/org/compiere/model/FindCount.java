/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2026 e-Evolution Consultants. All Rights Reserved.      *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com            *
 *****************************************************************************/
package org.compiere.model;

import org.compiere.util.DB;
import org.compiere.util.Env;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * 	Record count of the Find dialogs (Swing and ZK) and link value of the zoom to a detail tab.
 * 	The restriction of the {@link MQuery} runs as bind parameters; the tab extended where clause
 * 	is a raw SQL fragment and passes through as text. Keeps the logic out of the dialogs so that
 * 	both user interfaces behave the same and it can be tested without a screen.
 */
public final class FindCount
{
	private FindCount ()
	{
	}

	/**
	 * 	Count statement with <code>?</code> placeholders and the values to set in order.
	 * 	Immutable.
	 */
	public static final class CountStatement
	{
		private final String sql;
		private final List<Object> parameters;

		CountStatement (String sql, List<Object> parameters)
		{
			this.sql = sql;
			this.parameters = Collections.unmodifiableList(new ArrayList<Object>(parameters));
		}

		/**
		 * 	Get the statement to execute
		 *	@return SELECT COUNT(*) with one <code>?</code> per parameter
		 */
		public String getSql ()
		{
			return sql;
		}	//	getSql

		/**
		 * 	Get the values of the placeholders, in order
		 *	@return unmodifiable list of parameters
		 */
		public List<Object> getParameters ()
		{
			return parameters;
		}	//	getParameters

		/**
		 * 	Get the statement with the values rendered, for the context
		 * 	(<code>_TabInfo_FindSQL</code>), the logs and code that expects the literal text.
		 * 	Do not execute it; execute {@link #getSql()} with {@link #getParameters()}.
		 *	@return statement without placeholders
		 */
		public String getLiteralSql ()
		{
			try
			{
				return DB.inlineQueryParameters(sql, parameters);
			}
			catch (RuntimeException e)
			{
				return sql;	//	the context parsing changed the text, the values stay in getParameters
			}
		}	//	getLiteralSql

		@Override
		public String toString ()
		{
			return sql + " " + parameters;
		}
	}	//	CountStatement

	/**
	 * 	Create the statement that counts the records of a table
	 * 	@param ctx context for the variables of the SQL, or null to leave the text as it is
	 * 	@param windowNo window of the variables
	 * 	@param role role for the access restriction of the table, or null for none
	 * 	@param tableName table to count
	 * 	@param whereExtended raw SQL restriction of the tab (may have <code>@variables@</code>), or null
	 * 	@param query restriction of the user, or null
	 *	@return statement and values
	 */
	public static CountStatement getStatement (Properties ctx, int windowNo, MRole role,
		String tableName, String whereExtended, MQuery query)
	{
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM ").append(tableName);
		List<Object> parameters = new ArrayList<Object>();
		boolean hasWhere = false;
		if (whereExtended != null && whereExtended.length() > 0)
		{
			sql.append(" WHERE ").append(whereExtended);
			hasWhere = true;
		}
		if (query != null && query.isActive())
		{
			MQuery.BoundWhereClause bound = query.getBoundWhereClause(false);
			sql.append(hasWhere ? " AND " : " WHERE ").append(bound.getSql());
			parameters.addAll(bound.getParameters());
		}
		String statement = sql.toString();
		if (role != null)
			statement = role.addAccessSQL(statement, tableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		if (ctx != null)
			statement = Env.parseContext(ctx, windowNo, statement, false);
		return new CountStatement(statement, parameters);
	}	//	getStatement

	/**
	 * 	Execute the count
	 * 	@param statement statement created by {@link #getStatement(Properties, int, MRole, String, String, MQuery)}
	 *	@return number of records
	 * 	@throws SQLException if the statement fails
	 */
	public static int count (CountStatement statement) throws SQLException
	{
		if (statement.getSql().length() == 0)
			throw new SQLException("No SQL");	//	a variable of the extended where clause is missing
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try
		{
			preparedStatement = DB.prepareStatement(statement.getSql(), null);
			DB.setQueryParameters(preparedStatement, statement.getParameters());
			resultSet = preparedStatement.executeQuery();
			return resultSet.next() ? resultSet.getInt(1) : 0;
		}
		finally
		{
			DB.close(resultSet, preparedStatement);
		}
	}	//	count

	/**
	 * 	Get the value of the link column of the record that a zoom query selects
	 * 	@param linkColumnName column of the link with the parent tab
	 * 	@param tableName table of the detail tab
	 * 	@param query zoom restriction
	 *	@return value of the first record, or -1 if there is none or the statement fails
	 */
	public static int getLinkValue (String linkColumnName, String tableName, MQuery query)
	{
		MQuery.BoundWhereClause bound = query.getBoundWhereClause(false);
		String sql = "SELECT " + linkColumnName + " FROM " + tableName + " WHERE " + bound.getSql();
		return DB.getSQLValueQuery(null, sql, bound.getParameters());
	}	//	getLinkValue
}	//	FindCount
