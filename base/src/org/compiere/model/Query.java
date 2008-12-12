/*******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it     *
 * under the terms version 2 of the GNU General Public License as published    *
 * by the Free Software Foundation. This program is distributed in the hope    *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied  *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.            *
 * See the GNU General Public License for more details.                        *
 * You should have received a copy of the GNU General Public License along     *
 * with this program; if not, write to the Free Software Foundation, Inc.,     *
 * 59 Temple Place, Suite 330, Boston, MA                                      *
 * 02111-1307 USA.                                                             *
 *                                                                             *
 * Copyright (C) 2007 Low Heng Sin hengsin@avantz.com                          *
 * Contributor(s):                                                             *
 *                 Teo Sarca, www.arhipac.ro                                   *
 * __________________________________________                                  *
 ******************************************************************************/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Util;

/**
 * 
 * @author Low Heng Sin
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>FR [ 1981760 ] Improve Query class
 * 			<li>BF [ 2030280 ] org.compiere.model.Query apply access filter issue
 * 			<li>FR [ 2041894 ] Add Query.match() method
 * 			<li>FR [ 2107068 ] Query.setOrderBy should be more error tolerant
 * 			<li>FR [ 2107109 ] Add method Query.setOnlyActiveRecords
 * 			<li>FR [ 2421313 ] Introduce Query.firstOnly convenient method
 */
public class Query
{
	private static CLogger log	= CLogger.getCLogger (Query.class);
	
	private Properties ctx = null;
	private MTable table = null;
	private String whereClause = null;
	private String orderBy = null;
	private String trxName = null;
	private Object[] parameters = null;
	private boolean applyAccessFilter = false;
	private boolean onlyActiveRecords = false;
	
	/**
	 * 
	 * @param table
	 * @param whereClause
	 * @param trxName
	 * @deprecated Use {@link #Query(Properties, MTable, String, String)} instead because this method is security error prone
	 */
	public Query(MTable table, String whereClause, String trxName)
	{
		this.ctx = table.getCtx();
		this.table = table;
		this.whereClause = whereClause;
		this.trxName = trxName;
	}
	
	/**
	 * @param ctx context 
	 * @param table
	 * @param whereClause
	 * @param trxName
	 */
	public Query(Properties ctx, MTable table, String whereClause, String trxName)
	{
		this.ctx = ctx;
		this.table = table;
		this.whereClause = whereClause;
		this.trxName = trxName;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param tableName
	 * @param whereClause
	 * @param trxName
	 */
	public Query(Properties ctx, String tableName, String whereClause, String trxName)
	{
		this(ctx, MTable.get(ctx, tableName), whereClause, trxName);
		if (this.table == null)
			throw new IllegalArgumentException("Table Name Not Found - "+tableName);
	}
	
	/**
	 * Set query parameters
	 * @param parameters
	 */
	public Query setParameters(Object[] parameters)
	{
		this.parameters = parameters;
		return this;
	}
	
	/**
	 * Set query parameters
	 * @param parameters collection of parameters
	 */
	public Query setParameters(Collection<Object> parameters)
	{
		if (parameters == null) {
			this.parameters = null;
			return this;
		}
		this.parameters = new Object[parameters.size()];
		parameters.toArray(this.parameters);
		return this;
	}
	
	/**
	 * Set order by clause.
	 * If the string starts with "ORDER BY" then "ORDER BY" keywords will be discarded. 
	 * @param orderBy SQL ORDER BY clause
	 */
	public Query setOrderBy(String orderBy)
	{
		this.orderBy = orderBy != null ? orderBy.trim() : null;
		if (this.orderBy != null && this.orderBy.toUpperCase().startsWith("ORDER BY"))
		{
			this.orderBy = this.orderBy.substring(8);
		}
		return this;
	}
	
	/**
	 * Turn on/off the addition of data access filter
	 * @param flag
	 */
	public Query setApplyAccessFilter(boolean flag)
	{
		this.applyAccessFilter = flag;
		return this;
	}
	
	/**
	 * Select only active records (i.e. IsActive='Y')
	 * @param onlyActiveRecords
	 */
	public Query setOnlyActiveRecords(boolean onlyActiveRecords)
	{
		this.onlyActiveRecords = onlyActiveRecords;
		return this;
	}
	
	/**
	 * Return a list of all po that match the query criteria.
	 * @return List
	 * @throws DBException 
	 */
	@SuppressWarnings("unchecked")
	public <T extends PO> List<T> list() throws DBException
	{
		List<T> list = new ArrayList<T>();
		String sql = buildSQL(null, true);
		
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
	 * @return first PO
	 * @throws DBException
	 */
	@SuppressWarnings("unchecked")
	public <T extends PO> T first() throws DBException
	{
		T po = null;
		String sql = buildSQL(null, true);
		
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
	 * Return first PO that match query criteria.
	 * If there are more records that match criteria an exception will be throwed 
	 * @return first PO
	 * @throws DBException
	 * @see {@link #first()}
	 */
	@SuppressWarnings("unchecked")
	public <T extends PO> T firstOnly() throws DBException
	{
		T po = null;
		String sql = buildSQL(null, true);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			rs = createResultSet(pstmt);
			if (rs.next())
			{
				po = (T)table.getPO(rs, trxName);
			}
			if (rs.next())
			{
				throw new DBException("QueryMoreThanOneRecordsFound"); // TODO : translate
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			throw new DBException(e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return po;
	}

	
	/**
	 * red1 - returns full SQL string - for caller needs
	 * @return buildSQL(null,true)
	 * 
	 */
	public String getSQL() throws DBException
	{
 		return buildSQL(null, true);
	}
	
	/**
	 * Count items that match query criteria
	 * @return count
	 * @throws DBException
	 */
	public int count() throws DBException
	{
		int count = -1;
		String sql = buildSQL(new StringBuffer("SELECT COUNT(*) FROM ").append(table.getTableName()), false);
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
	 * Check if there items for query criteria
	 * @return true if exists, false otherwise
	 * @throws DBException
	 */
	public boolean match() throws DBException
	{
		String sql = buildSQL(new StringBuffer("SELECT 1 FROM ").append(table.getTableName()), false);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, this.trxName);
			rs = createResultSet(pstmt);
			if (rs.next())
				return true;
		}
		catch (SQLException e) {
			throw new DBException(e);
		}
		finally {
			DB.close(rs, pstmt);
		}
		return false;
	}
	
	/**
	 * Return an Iterator implementation to fetch one PO at a time. The implementation first retrieve
	 * all IDS that match the query criteria and issue sql query to fetch the PO when caller want to
	 * fetch the next PO. This minimize memory usage but it is slower than the list method.
	 * @return Iterator
	 * @throws DBException 
	 */
	public <T extends PO> Iterator<T> iterate() throws DBException
	{
		String[] keys = table.getKeyColumns();
		StringBuffer sqlBuffer = new StringBuffer(" SELECT ");
		for (int i = 0; i < keys.length; i++) {
			if (i > 0)
				sqlBuffer.append(", ");
			sqlBuffer.append(keys[i]);
		}
		sqlBuffer.append(" FROM ").append(table.getTableName());
		String sql = buildSQL(sqlBuffer, true);
		
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
	public <T extends PO> POResultSet<T> scroll() throws DBException
	{
		String sql = buildSQL(null, true);
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
	private final String buildSQL(StringBuffer selectClause, boolean useOrderByClause)
	{
		if (selectClause == null)
		{
			POInfo info = POInfo.getPOInfo(this.ctx, table.getAD_Table_ID(), trxName);
			if (info == null)
			{
				throw new IllegalStateException("No POInfo found for AD_Table_ID="+table.getAD_Table_ID());
			}
			selectClause = info.buildSelect();
		}
		StringBuffer sqlBuffer = new StringBuffer(selectClause);
		if (!Util.isEmpty(this.whereClause, true))
		{
			sqlBuffer.append(" WHERE ").append(whereClause);
		}
		if (this.onlyActiveRecords)
		{
			if (Util.isEmpty(this.whereClause, true))
			{
				sqlBuffer.append(" WHERE IsActive=?");
			}
			else
			{
				sqlBuffer.append(" AND IsActive=?");
			}
			
		}
		if (useOrderByClause && orderBy != null && orderBy.trim().length() > 0)
		{
			sqlBuffer.append(" ORDER BY ").append(orderBy);
		}
		String sql = sqlBuffer.toString();
		if (applyAccessFilter)
		{
			MRole role = MRole.getDefault(this.ctx, false);
			sql = role.addAccessSQL(sql, table.getTableName(), true, false);
		}
		return sql;
	}
	
	private final ResultSet createResultSet (PreparedStatement pstmt) throws SQLException
	{
		DB.setParameters(pstmt, parameters);
		if (this.onlyActiveRecords)
		{
			int i = 1 + (parameters != null ? parameters.length : 0);
			DB.setParameter(pstmt, i, true);
		}
		return pstmt.executeQuery();
	}

}
