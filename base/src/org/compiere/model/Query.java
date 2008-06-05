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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DBException;
import org.compiere.util.Env;

/**
 * 
 * @author Low Heng Sin
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>FR [ 1981760 ] Improve Query class
 */
public class Query {

	private static CLogger log	= CLogger.getCLogger (Query.class);
	
	private MTable table = null;
	private String whereClause = null;
	private String orderBy = null;
	private String trxName = null;
	private Object[] parameters = null;
	private boolean applyAccessFilter = false;
	
	/**
	 * 
	 * @param table
	 * @param whereClause
	 * @param trxName
	 */
	public Query(MTable table, String whereClause, String trxName) {
		this.table = table;
		this.whereClause = whereClause;
		this.trxName = trxName;
	}
	
	public Query(String tableName, String whereClause, String trxName) {
		this(MTable.get(Env.getCtx(), tableName), whereClause, trxName);
		if (this.table == null)
			throw new IllegalArgumentException("Table Name Not Found - "+tableName);
	}
	
	/**
	 * Set query parameters
	 * @param parameters
	 */
	public Query setParameters(Object[] parameters) {
		this.parameters = parameters;
		return this;
	}
	
	/**
	 * Set query parameters
	 * @param parameters collection of parameters
	 */
	public Query setParameters(Collection<Object> parameters) {
		if (parameters == null) {
			this.parameters = null;
			return this;
		}
		this.parameters = new Object[parameters.size()];
		parameters.toArray(this.parameters);
		return this;
	}
	
	/**
	 * Set order by clause ( without the order by sql keyword ).
	 * @param orderBy
	 */
	public Query setOrderBy(String orderBy) {
		this.orderBy = orderBy;
		return this;
	}
	
	/**
	 * Turn on/off the addition of data access filter
	 * @param flag
	 */
	public Query setApplyAccessFilter(boolean flag) {
		applyAccessFilter = flag;
		return this;
	}
	
	/**
	 * Return a list of all po that match the query criteria.
	 * @return List
	 * @throws DBException 
	 */
	public <T extends PO> List<T> list() throws DBException {
		List<T> list = new ArrayList<T>();
		String sql = buildSQL(null);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			rs = createResultSet(pstmt);
			while (rs.next ())
			{
				T po = (T)table.getPO(rs, trxName);
				list.add(po);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			throw new DBException(e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return list;
	}
	
	/**
	 * Return first PO that match query criteria
	 * @return PO
	 * @throws DBException
	 */
	public <T extends PO> T first() throws DBException {
		T po = null;
		String sql = buildSQL(null);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			rs = createResultSet(pstmt);
			if (rs.next ())
			{
				po = (T)table.getPO(rs, trxName);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			throw new DBException(e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return po;
	}
	
	/**
	 * Count items that match query criteria
	 * @return count
	 * @throws DBException
	 */
	public int count() throws DBException
	{
		int count = -1;
		String sql = buildSQL(new StringBuffer("SELECT COUNT(*) FROM ").append(table.getTableName()));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, this.trxName);
			rs = createResultSet(pstmt);
			rs.next();
			count = rs.getInt(1);
		}
		catch (SQLException e) {
			throw new DBException(e);
		}
		finally {
			DB.close(rs, pstmt);
		}
		return count;
	}
	
	/**
	 * Return an Iterator implementation to fetch one PO at a time. The implementation first retrieve
	 * all IDS that match the query criteria and issue sql query to fetch the PO when caller want to
	 * fetch the next PO. This minimize memory usage but it is slower than the list method.
	 * @return Iterator
	 * @throws DBException 
	 */
	public <T extends PO> Iterator<T> iterate() throws DBException {
		String[] keys = table.getKeyColumns();
		StringBuffer sqlBuffer = new StringBuffer(" SELECT ");
		for (int i = 0; i < keys.length; i++) {
			if (i > 0)
				sqlBuffer.append(", ");
			sqlBuffer.append(keys[i]);
		}
		sqlBuffer.append(" FROM ").append(table.getTableName());
		String sql = buildSQL(sqlBuffer);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object[]> idList = new ArrayList<Object[]>();
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			rs = createResultSet(pstmt);
			while (rs.next ())
			{
				Object[] ids = new Object[keys.length];
				for (int i = 0; i < ids.length; i++) {
					ids[i] = rs.getObject(i+1);
				}
				idList.add(ids);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			throw new DBException(e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return new POIterator<T>(table, idList, trxName);
	}
	
	/**
	 * Return a simple wrapper over a jdbc resultset. It is the caller responsibility to
	 * call the close method to release the underlying database resources.
	 * @return POResultSet
	 * @throws DBException 
	 */
	public <T extends PO> POResultSet<T> scroll() throws DBException {
		String sql = buildSQL(null);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		POResultSet<T> rsPO = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			rs = createResultSet(pstmt);
			rsPO = new POResultSet<T>(table, pstmt, rs, trxName);
			rsPO.setCloseOnError(true);
			return rsPO;
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			throw new DBException(e);
		}
		finally
		{
			// If there was an error, then close the statement and resultset
			if (rsPO == null) {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
	}
	
	/**
	 * Build SQL Clause
	 * @param selectClause optional; if null the select clause will be build according to POInfo
	 * @return final SQL
	 */
	private final String buildSQL(StringBuffer selectClause) {
		if (selectClause == null) {
			POInfo info = POInfo.getPOInfo(Env.getCtx(), table.getAD_Table_ID(), trxName);
			if (info == null)
				throw new IllegalStateException("No POInfo found for AD_Table_ID="+table.getAD_Table_ID());
			selectClause = info.buildSelect();
		}
		StringBuffer sqlBuffer = new StringBuffer(selectClause);
		if (whereClause != null && whereClause.trim().length() > 0)
			sqlBuffer.append(" WHERE ").append(whereClause);
		if (orderBy != null && orderBy.trim().length() > 0)
			sqlBuffer.append(" Order By ").append(orderBy);
		String sql = sqlBuffer.toString();
		if (applyAccessFilter) {
			MRole role = MRole.getDefault();
			sql = role.addAccessSQL(sql, table.getTableName(), true, false);
		}
		return sql;
	}
	
	private final ResultSet createResultSet (PreparedStatement pstmt) throws SQLException
	{
		if (parameters != null && parameters.length > 0) 
		{
			for (int i = 0; i < parameters.length; i++)
			{
				pstmt.setObject(i+1, parameters[i]);
			}
		}
		return pstmt.executeQuery();
	}
	
}
