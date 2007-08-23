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
import java.util.List;
import java.util.Iterator;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 
 * @author Low Heng Sin
 *
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
	
	/**
	 * Set query parameters
	 * @param parameters
	 */
	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
	
	/**
	 * Set order by clause ( without the order by sql keyword ).
	 * @param orderBy
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	/**
	 * Turn on/off the addition of data access filter
	 * @param flag
	 */
	public void setApplyAccessFilter(boolean flag) {
		applyAccessFilter = flag;
	}
	
	/**
	 * Return a list of all po that match the query criteria.
	 * @return List
	 * @throws SQLException 
	 */
	public List<PO> list() throws SQLException {
		List<PO> list = new ArrayList<PO>();
		
		POInfo info = POInfo.getPOInfo(Env.getCtx(), table.getAD_Table_ID());
		if (info == null) return null;
		StringBuffer sqlBuffer = info.buildSelect();
		if (whereClause != null && whereClause.trim().length() > 0)
			sqlBuffer.append(" WHERE ").append(whereClause);
		if (orderBy != null && orderBy.trim().length() > 0)
			sqlBuffer.append(" Order By ").append(orderBy);
		String sql = sqlBuffer.toString();
		if (applyAccessFilter) {
			MRole role = MRole.getDefault();
			sql = role.addAccessSQL(sql, table.get_TableName(), true, false);
		}
		
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			if (parameters != null && parameters.length > 0) 
			{
				for (int i = 0; i < parameters.length; i++)
				{
					pstmt.setObject(i+1, parameters[i]);
				}
			}
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				PO po = table.getPO(rs, trxName);
				list.add(po);
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close ();				
			}
			catch (Exception e){}
			pstmt = null;
		}
		return list;
	}
	
	/**
	 * Return an Iterator implementation to fetch one PO at a time. The implementation first retrieve
	 * all IDS that match the query criteria and issue sql query to fetch the PO when caller want to
	 * fetch the next PO. This minimize memory usage but it is slower than the list method.
	 * @return Iterator
	 * @throws SQLException 
	 */
	public Iterator iterate() throws SQLException {
		String[] keys = table.get_KeyColumns();
		StringBuffer sqlBuffer = new StringBuffer(" SELECT ");
		for (int i = 0; i < keys.length; i++) {
			if (i > 0)
				sqlBuffer.append(", ");
			sqlBuffer.append(keys[i]);
		}
		sqlBuffer.append(" FROM ").append(table.get_TableName());
		if (whereClause != null && whereClause.trim().length() > 0)
			sqlBuffer.append(" WHERE ").append(whereClause);
		if (orderBy != null && orderBy.trim().length() > 0)
			sqlBuffer.append(" Order By ").append(orderBy);
		String sql = sqlBuffer.toString();
		if (applyAccessFilter) {
			MRole role = MRole.getDefault();
			sql = role.addAccessSQL(sql, table.get_TableName(), true, false);
		}
		PreparedStatement pstmt = null;
		List<Object[]> idList = new ArrayList<Object[]>();
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			if (parameters != null && parameters.length > 0) 
			{
				for (int i = 0; i < parameters.length; i++)
				{
					pstmt.setObject(i+1, parameters[i]);
				}
			}
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				Object[] ids = new Object[keys.length];
				for (int i = 0; i < ids.length; i++) {
					ids[i] = rs.getObject(i+1);
				}
				idList.add(ids);
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e) {}
			pstmt = null;
		}
		return new POIterator(table, idList, trxName);
	}
	
	/**
	 * Return a simple wrapper over a jdbc resultset. It is the caller responsibility to
	 * call the close method to release the underlying database resources.
	 * @return POResultSet
	 * @throws SQLException 
	 */
	public POResultSet scroll() throws SQLException {
		POInfo info = POInfo.getPOInfo(Env.getCtx(), table.getAD_Table_ID());
		if (info == null) return null;
		StringBuffer sqlBuffer = info.buildSelect();
		if (whereClause != null && whereClause.trim().length() > 0)
			sqlBuffer.append(" WHERE ").append(whereClause);
		if (orderBy != null && orderBy.trim().length() > 0)
			sqlBuffer.append(" Order By ").append(orderBy);
		String sql = sqlBuffer.toString();
		if (applyAccessFilter) {
			MRole role = MRole.getDefault();
			sql = role.addAccessSQL(sql, table.get_TableName(), true, false);
		}
		
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			if (parameters != null && parameters.length > 0) 
			{
				for (int i = 0; i < parameters.length; i++)
				{
					pstmt.setObject(i+1, parameters[i]);
				}
			}
			ResultSet rs = pstmt.executeQuery ();
			return new POResultSet(table, pstmt, rs, trxName);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			throw e;
		}
	}
}
