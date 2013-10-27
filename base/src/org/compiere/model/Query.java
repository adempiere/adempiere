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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryOrderBy;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.adempiere.exceptions.DBMoreThenOneRecordsFoundException;
import org.adempiere.model.POWrapper;
import org.adempiere.util.Services;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * 
 * @author Low Heng Sin
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>FR [ 1981760 ] Improve Query class
 * 			<li>BF [ 2030280 ] org.compiere.model.Query apply access filter issue
 * 			<li>FR [ 2041894 ] Add Query.match() method
 * 			<li>FR [ 2107068 ] Query.setOrderBy should be more error tolerant
 * 			<li>FR [ 2107109 ] Add method Query.setOnlyActiveRecords
 * 			<li>FR [ 2421313 ] Introduce Query.firstOnly convenient method
 * 			<li>FR [ 2546052 ] Introduce Query aggregate methods
 * 			<li>FR [ 2726447 ] Query aggregate methods for all return types
 * 			<li>FR [ 2818547 ] Implement Query.setOnlySelection
 * 				https://sourceforge.net/tracker/?func=detail&aid=2818547&group_id=176962&atid=879335
 * 			<li>FR [ 2818646 ] Implement Query.firstId/firstIdOnly
 * 				https://sourceforge.net/tracker/?func=detail&aid=2818646&group_id=176962&atid=879335
 * @author Redhuan D. Oon
 * 			<li>FR: [ 2214883 ] Remove SQL code and Replace for Query // introducing SQL String prompt in log.info 
 *			<li>FR: [ 2214883 ] - to introduce .setClient_ID
 */
public class Query implements IQuery
{
	public static final String AGGREGATE_COUNT		= "COUNT";
	public static final String AGGREGATE_SUM		= "SUM";
	public static final String AGGREGATE_AVG		= "AVG";
	public static final String AGGREGATE_MIN		= "MIN";
	public static final String AGGREGATE_MAX		= "MAX";
	public static final String AGGREGATE_DISTINCT	= "DISTINCT";
	
	private static CLogger log	= CLogger.getCLogger (Query.class);
	
	private Properties ctx = null;
	private MTable table = null;
	private String whereClause = null;
	private IQueryOrderBy queryOrderBy = null;
	private String trxName = null;
	private List<Object> parameters = null;
	private boolean applyAccessFilter = false;
	private boolean applyAccessFilterRW = false;
	// private boolean applyAccessFilterFullyQualified = true; // metas: shall not be used
	private boolean onlyActiveRecords = false;
	private boolean onlyClient_ID = false;
	private int onlySelection_ID = -1;
	
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
		Util.assume(ctx != null, "ctx != null");
		Util.assume(table != null, "table != null");
		
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
	public Query setParameters(Object ...parameters)
	{
		this.parameters = Arrays.asList(parameters);
		return this;
	}
	
	/**
	 * Set query parameters
	 * @param parameters collection of parameters
	 */
	public Query setParameters(List<Object> parameters)
	{
		if (parameters == null) {
			this.parameters = null;
			return this;
		}
		this.parameters = new ArrayList<Object>(parameters);
		return this;
	}
	
	/**
	 * Set order by clause.
	 * If the string starts with "ORDER BY" then "ORDER BY" keywords will be discarded. 
	 * @param orderBy SQL ORDER BY clause
	 */
	public Query setOrderBy(String orderBy)
	{
		this.queryOrderBy = Services.get(IQueryBL.class).createSqlQueryOrderBy(orderBy);
		return this;
	}
	
	@Override
	public Query setOrderBy(IQueryOrderBy orderBy)
	{
		this.queryOrderBy = orderBy;
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
	 * Turn on data access filter with controls
	 * @param flag
	 * 
	 * @deprecated Please use {@link #setApplyAccessFilterRW(boolean)}
	 */
	@Deprecated
	public Query setApplyAccessFilter(boolean fullyQualified, boolean RW)
	{
		// metas: begin: fullyQualified parameter shall not exist at all because it's related on how the query is internally built.
		// For backward-compatibility we are keeping it but we enforce developer to always set it to true
		if (!fullyQualified)
		{
			log.log(Level.WARNING, "fullyQualified shall be always true, else it could be a developer issue. Changing to true."
					+ " Query: " + toString(), new AdempiereException());
		}
		
		return setApplyAccessFilterRW(RW);
		// metas: end
	}

	/**
	 * Apply read-write access filter to all resulting records.
	 * 
	 * Please note that this method will turn on security filter anyway. If you want to turn this off again, use {@link #setApplyAccessFilter(boolean)}.
	 * 
	 * @param RW true if read-write access is required, false if read-only access is sufficient
	 */
	public Query setApplyAccessFilterRW(boolean RW)
	{
		this.applyAccessFilter = true;
		this.applyAccessFilterRW = RW;
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
	 * Set Client_ID true for WhereClause routine to include AD_Client_ID
	 */
	public Query setClient_ID()
	{
		this.onlyClient_ID = true;
		return this;
	}
	
	/**
	 * Only records that are in T_Selection with AD_PInstance_ID.
	 * @param AD_PInstance_ID
	 */
	public Query setOnlySelection(int AD_PInstance_ID)
	{
		this.onlySelection_ID = AD_PInstance_ID;
		return this;
	}
	
	/**
	 * Return a list of all po that match the query criteria.
	 * @return List
	 * @throws DBException 
	 */
	public <T extends PO> List<T> list() throws DBException
	{
		return list(null);
	}
	/**
	 * Return a list of all po that match the query criteria.
	 * @param clazz all resulting POs will be converted to this interface
	 * @return List
	 * @throws DBException 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> list(Class<T> clazz) throws DBException
	{
		final List<T> list;
		if (limit > 0)
		{
			list = new ArrayList<T>(limit);
		}
		else
		{
			list = new ArrayList<T>();
		}
		
		String sql = buildSQL(null, true);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			rs = createResultSet(pstmt);
			while (rs.next ())
			{
				PO o = table.getPO(rs, trxName);
				T po;
				if (clazz != null && !o.getClass().isAssignableFrom(clazz))
					po = POWrapper.create(o, clazz);
				else
					po = (T)o;
					
				list.add(po);
				
				if (limit > 0 && list.size() >= limit)
				{
					log.fine("Limit of "+limit+" reached. Stop.");
					break;
				}
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			throw new DBException(e, sql);
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
		return (T)first(null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T first(Class<T> clazz) throws DBException
	{
		T po = null;
		
		// metas: begin: not using ORDER BY clause can be a developer error
		final String orderBy = getOrderBy();
		if (Util.isEmpty(orderBy, true))
		{
			log.log(Level.WARNING, "Using first() without an ORDER BY clause can be a developer error."
					+ " Please specify ORDER BY clause or in case you know that only one result shall be returned then use firstOnly()."
					+ " Query: " + toString(), new AdempiereException());
		}
		// metas: end
		
		String sql = buildSQL(null, true);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			rs = createResultSet(pstmt);
			if (rs.next ())
			{
				PO o = table.getPO(rs, trxName);
				if (clazz != null && !o.getClass().isAssignableFrom(clazz))
					po = POWrapper.create(o, clazz);
				else
					po = (T)o;
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			throw new DBException(e, sql);
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
		return (T)firstOnly(null);
	}
	/**
	 * Return first PO that match query criteria.
	 * If there are more records that match criteria an exception will be throwed 
	 * @return first PO
	 * @throws DBException
	 * @see {@link #first()}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T firstOnly(Class<T> clazz) throws DBException
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
				PO o = table.getPO(rs, trxName);
				if (clazz != null && !o.getClass().isAssignableFrom(clazz))
					po = POWrapper.create(o, clazz);
				else
					po = (T)o;
			}
			if (rs.next())
			{
				throw new DBMoreThenOneRecordsFoundException(this.toString());
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return po;
	}
	
	/**
	 * Return first ID
	 * @return first ID or -1 if not found
	 * @throws DBException
	 */
	public int firstId() throws DBException
	{
		return firstId(false);
	}
	
	/**
	 * Return first ID.
	 * If there are more results and exception is thrown.
	 * @return first ID or -1 if not found
	 * @throws DBException
	 */
	public int firstIdOnly() throws DBException
	{
		return firstId(true);
	}
	
	private int firstId(boolean assumeOnlyOneResult) throws DBException
	{
		final String keyColumnName = getKeyColumnName();
		if (keyColumnName == null)
		{
			throw new DBException("Table "+table+" has 0 or more than 1 key columns");
		}

		StringBuilder selectClause = new StringBuilder("SELECT ");
		selectClause.append(keyColumnName);
		selectClause.append(" FROM ").append(table.getTableName());
		String sql = buildSQL(selectClause, true);

		int id = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			rs = createResultSet(pstmt);
			if (rs.next())
			{
				id = rs.getInt(1);
			}
			if (assumeOnlyOneResult && rs.next())
			{
				throw new DBException("QueryMoreThanOneRecordsFound"); // TODO : translate
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		return id;
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
	 * Aggregate given expression on this criteria
	 * @param sqlExpression
	 * @param sqlFunction 
	 * @return aggregated value
	 * @throws DBException
	 */
	public BigDecimal aggregate(String sqlExpression, String sqlFunction) throws DBException
	{
		return aggregate(sqlExpression, sqlFunction, BigDecimal.class);
	}

	/**
	 * Aggregate given expression on this criteria
	 * @param <T>
	 * @param sqlExpression
	 * @param sqlFunction
	 * @param returnType
	 * @return aggregated value
	 * @throws DBException
	 */
	public <T> T aggregate(String sqlExpression, String sqlFunction, Class<T> returnType) throws DBException
	{
		final List<T> list = aggregateList(sqlExpression, sqlFunction, returnType);
		
		if (list.isEmpty())
		{
			return null;
		}
		else if (list.size() > 1)
		{
			throw new DBException("QueryMoreThanOneRecordsFound"); // TODO : translate
		}
		
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> aggregateList(String sqlExpression, String sqlFunction, Class<T> returnType) throws DBException
	{
		if (Util.isEmpty(sqlFunction, true))
		{
			throw new DBException("No Aggregate Function defined");
		}
		
		// metas-tsa: If the sqlExpression is a virtual column, then replace it with it's ColumnSQL
		final MColumn column = table.getColumn(sqlExpression);
		if (column != null && column.isVirtualColumn())
		{
			sqlExpression = column.getColumnSQL();
		}

		if (Util.isEmpty(sqlExpression, true))
		{
			if (AGGREGATE_COUNT == sqlFunction)
			{
				sqlExpression = "*";
			}
			else
			{
				throw new DBException("No Expression defined");
			}
		}
		
		final List<T> result = new ArrayList<T>();

		StringBuilder sqlSelect = new StringBuilder("SELECT ").append(sqlFunction).append("(")
					.append(sqlExpression).append(")")
					.append(" FROM ").append(table.getTableName());
		
		String sql = buildSQL(sqlSelect, false);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, this.trxName);
			rs = createResultSet(pstmt);
			while(rs.next())
			{
				T value = null;
				T defaultValue = null;
				if (returnType.isAssignableFrom(BigDecimal.class))
				{
					value = (T)rs.getBigDecimal(1);
					defaultValue = (T)Env.ZERO;
				}
				else if (returnType.isAssignableFrom(Double.class))
				{
					value = (T)Double.valueOf(rs.getDouble(1));
					defaultValue = (T)Double.valueOf(0.00);
				}
				else if (returnType.isAssignableFrom(Integer.class))
				{
					value = (T)Integer.valueOf(rs.getInt(1));
					defaultValue = (T)Integer.valueOf(0);
				}
				else if (returnType.isAssignableFrom(Timestamp.class))
				{
					value = (T)rs.getTimestamp(1);
				}
				else if (returnType.isAssignableFrom(Boolean.class))
				{
					value = (T) Boolean.valueOf("Y".equals(rs.getString(1)));
					defaultValue = (T) Boolean.FALSE;
				}
				else
				{
					value = (T)rs.getObject(1);
				}
				
				//
				// Add value to result
				if (value == null)
				{
					value = defaultValue;
			}
				if (value != null)
			{
					result.add(value);
			}
		}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		return result;
	}
	
	/**
	 * Count items that match query criteria
	 * @return count
	 * @throws DBException
	 */
	@Override
	public int count() throws DBException
	{
		return aggregate("*", AGGREGATE_COUNT).intValue();
	}
	
	/**
	 * SUM sqlExpression for items that match query criteria
	 * @param sqlExpression
	 * @return sum
	 */
	public BigDecimal sum(String sqlExpression)
	{
		return aggregate(sqlExpression, AGGREGATE_SUM);
	}
	/**
	 * Check if there items for query criteria
	 * @return true if exists, false otherwise
	 * @throws DBException
	 */
	public boolean match() throws DBException
	{
		String sql = buildSQL(new StringBuilder("SELECT 1 FROM ").append(table.getTableName()), false);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, this.trxName);
			rs = createResultSet(pstmt);
			if (rs.next())
				return true;
		}
		catch (SQLException e) {
			throw new DBException(e, sql);
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
		return (Iterator<T>)iterate((Class<T>)null);
	}
	
	public <T> Iterator<T> iterate(Class<T> clazz) throws DBException
	{
		// Because most of the business logic relies on guaranteed iterator, we will use it as implicit. 
		final boolean guaranteed = true;
		return iterate(clazz, guaranteed);
	}
	
	public <T> Iterator<T> iterate(Class<T> clazz, boolean guaranteed) throws DBException
	{
		final Integer iteratorBufferSize = getOption(OPTION_IteratorBufferSize);
		
		if (guaranteed)
		{
			final GuaranteedPOBufferedIterator<T> it = new GuaranteedPOBufferedIterator<T>(this, clazz);
			if (iteratorBufferSize != null)
			{
				it.setBufferSize(iteratorBufferSize);
			}
			return it;
		}
		
		// metas: mo73_03658: use POBufferedIterator instead of old POIterator, if database paging is supported
		else if (DB.getDatabase().isPagingSupported())
		{
			final POBufferedIterator<T> poBufferedIterator = new POBufferedIterator<T>(this, clazz);
			if (iteratorBufferSize != null)
			{
				poBufferedIterator.setBufferSize(iteratorBufferSize);
			}
			else if (limit != NO_LIMIT)
			{   // use the set limit as our buffer size, if a limit has been set
				poBufferedIterator.setBufferSize(limit);
			}
			return poBufferedIterator;
		}
		else
		{
			final List<Object[]> idList = getComposedIDs();
			return new POIterator<T>(table, clazz, idList, trxName);
		}
	}

	/**
	 * Get a List of composed IDs for this Query.
	 * @return List of composed IDs
	 */
	public List<Object[]> getComposedIDs()
	{
		String[] keys = table.getKeyColumns();
		StringBuilder sqlBuffer = new StringBuilder(" SELECT ");
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
			throw new DBException(e, sql);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		return idList;
	}
	
	/**
	 * Return a simple wrapper over a JDBC {@link ResultSet}. It is the caller responsibility to
	 * call the close method to release the underlying database resources.
	 * @return POResultSet
	 * @throws DBException 
	 */
	public <T extends PO> POResultSet<T> scroll() throws DBException
	{
		return (POResultSet<T>)scroll((Class<T>)null);
	}
	public <T> POResultSet<T> scroll(Class<T> clazz) throws DBException
	{
		String sql = buildSQL(null, true);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		POResultSet<T> rsPO = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			rs = createResultSet(pstmt);
			rsPO = new POResultSet<T>(table, clazz, pstmt, rs, trxName);
			rsPO.setCloseOnError(true);
			return rsPO;
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			throw new DBException(e, sql);
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
	 * Create a new {@link Query} object and set it's whereClause 
	 * @param whereClause
	 * @return
	 */
	public Query setWhereClause(final String whereClause)
	{
		final Query query = copy();
		query.whereClause = whereClause;
		return query;
	}
	
	public Query addWhereClause(final boolean joinByAnd, final String whereClause)
		{
		if (Util.isEmpty(whereClause, true))
			{
			return this;
			}

		final String whereClauseFinal;
		if (!Util.isEmpty(getWhereClause(), true))
		{
			whereClauseFinal = new StringBuilder()
					.append("(").append(getWhereClause()).append(")")
					.append(joinByAnd ? " AND " : " OR ")
					.append("(").append(whereClause).append(")")
					.toString();
		}
		else
		{
			whereClauseFinal = whereClause;
		}
		
		return setWhereClause(whereClauseFinal);
	}
	
	public String getWhereClause()
	{
		return whereClause;
	}
	
	protected final String getWhereClauseEffective()
	{
		StringBuffer whereBuffer = new StringBuffer(); 
		if (!Util.isEmpty(this.whereClause, true))
		{
			if (whereBuffer.length() > 0)
				whereBuffer.append(" AND ");
			whereBuffer.append("(").append(this.whereClause).append(")");
		}
		if (this.onlyActiveRecords)
		{
			if (whereBuffer.length() > 0)
				whereBuffer.append(" AND ");
			whereBuffer.append("IsActive=?");
		}
		if (this.onlyClient_ID) //red1
		{
			if (whereBuffer.length() > 0)
				whereBuffer.append(" AND ");
			whereBuffer.append("AD_Client_ID=?");
		}
		if (this.onlySelection_ID > 0)
		{
			final String keyColumnName = getKeyColumnName();
			if (keyColumnName == null)
			{
				throw new DBException("Table "+table+" has 0 or more than 1 key columns");
			}
			//
			if (whereBuffer.length() > 0)
				whereBuffer.append(" AND ");
			whereBuffer.append(" EXISTS (SELECT 1 FROM T_Selection s WHERE s.AD_PInstance_ID=?"
					+" AND s.T_Selection_ID="+table.getTableName()+"."+keyColumnName+")");
		}
		
		return whereBuffer.toString();
	}
	
	protected final List<Object> getParametersEffective()
		{
		final List<Object> parametersEffective = new ArrayList<Object>();
		
		if (parameters != null && !parameters.isEmpty())
		{
			parametersEffective.addAll(parameters);
		}
		
		if (this.onlyActiveRecords)
		{
			parametersEffective.add(true);
			log.finest("Parameter IsActive = Y");
		}
		if (this.onlyClient_ID)
		{
			int AD_Client_ID = Env.getAD_Client_ID(ctx);
			parametersEffective.add(AD_Client_ID);
			log.finest("Parameter AD_Client_ID = "+AD_Client_ID);
		}
		if (this.onlySelection_ID > 0)
		{
			parametersEffective.add(this.onlySelection_ID);
			log.finest("Parameter Selection AD_PInstance_ID = "+this.onlySelection_ID);
		}

		return parametersEffective;
	}

	
	/**
	 * Build SQL Clause
	 * @param selectClause optional; if null the select clause will be build according to POInfo
	 * @return final SQL
	 */
	protected final String buildSQL(StringBuilder selectClause, boolean useOrderByClause)
	{
		if (selectClause == null)
		{
			POInfo info = POInfo.getPOInfo(this.ctx, table.getAD_Table_ID(), trxName);
			if (info == null)
			{
				throw new IllegalStateException("No POInfo found for AD_Table_ID="+table.getAD_Table_ID());
			}
			selectClause = new StringBuilder(info.buildSelect().toString());
		}
		
		
		StringBuilder sqlBuffer = new StringBuilder(selectClause);
		
		final String whereClauseEffective = getWhereClauseEffective();
		if (whereClauseEffective != null && !whereClauseEffective.isEmpty())
		{
			sqlBuffer.append(" WHERE ").append(whereClauseEffective);
		}
		
		final String orderBy = getOrderBy();
		if (useOrderByClause && !Util.isEmpty(orderBy, true))
		{
			sqlBuffer.append(" ORDER BY ").append(orderBy);
		}
		String sql = sqlBuffer.toString();
		if (applyAccessFilter)
		{
			MRole role = MRole.getDefault(this.ctx, false);
			final boolean applyAccessFilterFullyQualified = true; // metas: shall always be true
			sql = role.addAccessSQL(sql, table.getTableName(), applyAccessFilterFullyQualified, applyAccessFilterRW);
		}
		
		// metas: begin
		if (this.limit > 0 || this.offset >= 0)
		{
			if (DB.getDatabase().isPagingSupported())
			{
				final int offsetFixed = offset > 0 ? offset : 0;
				final int start = offsetFixed + 1;
				final int end = limit + offsetFixed;
				sql = DB.getDatabase().addPagingSQL(sql, start, end);
			}
			else
			{
				log.log(Level.SEVERE, "Paging is not supported. Ignored", new Exception());
			}
		}
		// metas: end

		if (CLogMgt.isLevelFinest()) log.finest("TableName = "+table.getTableName()+"... SQL = " +sql); //red1  - to assist in debugging SQL
		return sql;
	}
	
	private final ResultSet createResultSet (PreparedStatement pstmt) throws SQLException
	{
		final List<Object> parametersEffective = getParametersEffective();
		DB.setParameters(pstmt, parametersEffective);
		
		final long ts = System.currentTimeMillis();
		final ResultSet rs = pstmt.executeQuery();
		
		final long durationMillis = System.currentTimeMillis() - ts;
		final long durationMaxMillis = 1000 * 60 * 5; // 5min 
		if (durationMaxMillis > 0 && durationMillis > durationMaxMillis)
		{
			log.log(Level.WARNING, "Query " + this + " took longer then " + durationMaxMillis + "millis to create the ResultSet", new Exception("trace"));
		}
		
		return rs;
	}
	
	/**
	 * Get a Array with the IDs for this Query
	 * @return Get a Array with the IDs
	 */
	public int[] getIDs ()
	{
		final String keyColumnName = getKeyColumnName();
		if (keyColumnName == null)
		{
			throw new DBException("Table "+table+" has 0 or more than 1 key columns");
		}

		StringBuilder selectClause = new StringBuilder("SELECT ");
		selectClause.append(keyColumnName);
		selectClause.append(" FROM ").append(table.getTableName());
		String sql = buildSQL(selectClause, true);
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			rs = createResultSet(pstmt);
			while (rs.next())
			{
				list.add(rs.getInt(1));
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	Convert to array
		int[] retValue = new int[list.size()];
		for (int i = 0; i < retValue.length; i++)
		{
			retValue[i] = list.get(i);
		}
		return retValue;
	}	//	get_IDs

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("Query[");
		sb.append(table.getTableName());
		sb.append(", Where=").append(whereClause);
		if (parameters != null && !parameters.isEmpty())
			sb.append(", Params=").append(parameters.toString());
		if (queryOrderBy != null)
			sb.append(", OrderBy=").append(queryOrderBy);
		//
		// metas: limit & offset
		if (limit > 0)
		{
			sb.append(", Limit=").append(limit);
		}
		if (offset >= 0)
		{
			sb.append(", Offset=").append(offset);
		}
		
		sb.append(", trxName=").append(trxName);
		sb.append(", Options=");
		if (applyAccessFilter)
			sb.append("ApplyAccessFilter;");
		// metas: commented out because we don't use it anymore (considering always true)
//		if (applyAccessFilterFullyQualified)
//			sb.append("ApplyAccessFilterFQ;");
		if (applyAccessFilterRW)
			sb.append("ApplyAccessFilterRW;");
		if (onlyActiveRecords)
			sb.append("OnlyActive;");
		if (onlySelection_ID > 0)
			sb.append("OnlySelection=").append(onlySelection_ID).append(";");
		if (options != null && !options.isEmpty())
			sb.append(options.toString()).append(";");
		sb.append("]");
		return sb.toString();
	}

	// metas
	public static final int NO_LIMIT = -1;
	private int limit = NO_LIMIT;
	private int offset = NO_LIMIT;

	/**
	 * Sets query LIMIT to be used.
	 * 
	 * For a detailed description about LIMIT and OFFSET concepts, please take a look <a
	 * href="http://www.postgresql.org/docs/9.1/static/queries-limit.html">here</a>.
	 * 
	 * @param limit
	 *            integer greater than zero or {@link #NO_LIMIT}. Note: if the {@link #iterate()} method is used and the
	 *            underlying database supports paging, then the limit value (if set) is used as page size.
	 * @return this
	 */
	public Query setLimit(int limit)
	{
		this.limit = limit;
		return this;
	}
	
	/**
	 * Sets query LIMIT and OFFSET to be used.
	 * 
	 * For a detailed description about LIMIT and OFFSET concepts, please take a look <a
	 * href="http://www.postgresql.org/docs/9.1/static/queries-limit.html">here</a>.
	 * 
	 * @param limit
	 *            integer greater than zero or {@link #NO_LIMIT}. Note: if the {@link #iterate()} method is used and the
	 *            underlying database supports paging, then the limit value (if set) is used as page size.
	 * @param offset
	 *            integer greater than zero or {@link #NO_LIMIT}
	 * @return this
	 */
	public Query setLimit(int limit, int offset)
	{
		this.limit = limit;
		this.offset = offset;
		return this;
	}
	
	public Properties getCtx()
	{
		return ctx;
	}
	
	public String getTrxName()
	{
		return trxName;
	}
	
	public String getOrderBy()
	{
		if (queryOrderBy == null)
		{
			return null;
		}
		return queryOrderBy.getSql();
	}
	
	@Override
	public String getTableName()
	{
		return table.getTableName();
	}
	
	protected MTable getAD_Table()
	{
		return table;
	}
	
	/**
	 * Gets single key column name.
	 * @return key column name.
	 * @throws DBException 
	 */
	public String getKeyColumnName()
	{
		final String[] keys = table.getKeyColumns();
		if (keys == null || keys.length != 1)
		{
			return null;
		}

		return keys[0];
	}
	
	private Map<String, Object> options = null;
	public static final String OPTION_IteratorBufferSize = "IteratorBufferSize";
	
	public Query setOption(String name, Object value)
	{
		if (options == null)
		{
			options = new HashMap<String, Object>();
		}
		options.put(name, value);
		
		return this;
	}
	
	public <T> T getOption(String name)
	{
		if (options == null)
		{
			return null;
		}
		
		@SuppressWarnings("unchecked")
		final T value = (T)options.get(name);
		
		return value;
	}
	
	/**
	 * 
	 * @return a copy of this object
	 */
	@Override
	public Query copy()
	{
		final Query queryTo = new Query(ctx, table, whereClause, trxName);
		queryTo.ctx = ctx;
		queryTo.table = table;
		queryTo.whereClause = whereClause;
		queryTo.trxName = trxName;
		//
		queryTo.queryOrderBy = queryOrderBy;
		queryTo.applyAccessFilter = applyAccessFilter;
		queryTo.applyAccessFilterRW = applyAccessFilterRW;
		queryTo.onlyActiveRecords = onlyActiveRecords;
		queryTo.onlyClient_ID = onlyClient_ID;
		queryTo.onlySelection_ID = onlySelection_ID;
		queryTo.limit = limit;
		queryTo.offset = offset;
		
		if (parameters == null)
		{
			queryTo.parameters = null;
		}
		else
		{
			queryTo.parameters = new ArrayList<Object>(parameters);
		}
		
		queryTo.options = options == null ? null : new HashMap<String, Object>(options);

		return queryTo;
	}

	@Override
	public Object clone()
	{
		return copy();
	}
	
	/**
	 * Inserts the query result into a <code>T_Selection</code> for the given AD_PInstance_ID
	 * 
	 * @param AD_PInstance_ID
	 * @return number of records inserted in selection
	 */
	public int createSelection(final int AD_PInstance_ID)
	{
		final String keyColumnName = getKeyColumnName();
		if (keyColumnName == null)
		{
			throw new DBException("Table "+table+" has 0 or more than 1 key columns");
		}

		final StringBuilder selectClause = new StringBuilder(80)
				.append("INSERT INTO T_SELECTION(AD_PINSTANCE_ID, T_SELECTION_ID) ")
				.append(" SELECT ")
				.append(AD_PInstance_ID)
				.append(", ").append(keyColumnName)
				.append(" FROM ").append(table.getTableName());

		final String sql = buildSQL(selectClause, false);
		final Object[] params = getParametersEffective().toArray();
		
		final int no = DB.executeUpdateEx(sql, params, trxName);
		return no;
	}
}
