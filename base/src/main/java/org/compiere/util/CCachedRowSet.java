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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;

import oracle.jdbc.rowset.OracleCachedRowSet;

import org.compiere.db.AdempiereDatabase;
import org.compiere.db.Database;

import com.sun.rowset.CachedRowSetImpl;


/**
 *	Adempiere Cached Row Set Implementation
 *	
 *  @author Jorg Janke
 *  @version $Id: CCachedRowSet.java,v 1.6 2006/07/30 00:54:36 jjanke Exp $
 */
public class CCachedRowSet extends CachedRowSetImpl implements CachedRowSet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -233983261449861555L;


	/**
	 * 	Get Cached Row Set.
	 * 	Required due to Java Sun bug 393865
	 *	@return Cached Row Set
	 *	@throws SQLException
	 */
	public static CCachedRowSet get() throws SQLException
	{
		CCachedRowSet crs = null;
		//	only first time call
		if (s_loc == null)
		{
			s_loc = Locale.getDefault();
			Locale.setDefault(Locale.US);
			crs = new CCachedRowSet();
			Locale.setDefault(s_loc);
		}
		else
			crs = new CCachedRowSet();
		//
		return crs;
	}	//	get
	
	/**
	 * 	Get Row Set.
	 * 	Read-Only, Scroll Insensitive
	 * 	Need to set parameters and call  execute(Commection)
	 *	@param sql sql
	 *	@return row set
	 *	@throws SQLException
	 */
	public static RowSet getRowSet (String sql) throws SQLException
	{
		CachedRowSet crs = get();
		crs.setConcurrency(ResultSet.CONCUR_READ_ONLY);
		crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
		crs.setCommand(sql);
		//	Set Parameters
	//	crs.execute(conn);
		return crs;
	}	//	get

	/**
	 * 	Get and Execute Row Set.
	 * 	No parameters, Read-Only, Scroll Insensitive
	 *	@param sql sql
	 *	@param conn connection
	 *	@param db database
	 *	@return row set
	 *	@throws SQLException
	 */
	public static RowSet getRowSet (String sql, Connection conn, AdempiereDatabase db) throws SQLException
	{
		if (db.getName().equals(Database.DB_ORACLE))
		{
			Statement stmt = conn.createStatement
				(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sql);
			OracleCachedRowSet crs = new OracleCachedRowSet();
			crs.populate(rs);
			rs.close();
			stmt.close();
			return crs;
		}
		CachedRowSet crs = get();
		crs.setConcurrency(ResultSet.CONCUR_READ_ONLY);
		crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
		crs.setCommand(sql);
		crs.execute(conn);
		return crs;
	}	//	get
	
	/**
	 * 	Get Cached Row Set.
	 * 	Required due to Java Sun bug 393865. 
	 * 	Also, Oracle NUMBER returns scale -127 
	 * 	@param rs result set
	 * 	@param db database 
	 *	@return Cached Row Set
	 *	@throws SQLException
	 */
	public static RowSet getRowSet (ResultSet rs, AdempiereDatabase db) throws SQLException
	{
		if (db.getName().equals(Database.DB_ORACLE))
		{
			OracleCachedRowSet crs = new OracleCachedRowSet();
			crs.populate(rs);
			return crs;
		}
		CachedRowSet crs = get();
		crs.populate(rs);
		return crs;
	}	//	getRowSet
	
	/**
	 * 	Get Cached Row Set.
	 * 	Gets Database from DB
	 * 	Required due to Java Sun bug 393865. 
	 * 	Also, Oracle NUMBER returns scale -127 
	 * 	@param rs result set
	 *	@return Cached Row Set
	 *	@throws SQLException
	 */
	public static RowSet getRowSet (ResultSet rs) throws SQLException
	{
		return getRowSet(rs, DB.getDatabase());
	}	//	getRowSet

	
	/**	Private Locale Marker	*/
	private static Locale s_loc = null;
	
	
	/**************************************************************************
	 * 	Adempiere Cached RowSet
	 *	@throws java.sql.SQLException
	 */
	private CCachedRowSet() throws SQLException
	{
		super ();
		setSyncProvider("com.sun.rowset.providers.RIOptimisticProvider");
	}	//	CCachedRowSet

	
	/**************************************************************************
	 * 	Test
	 *	@param args ignored
	 */
	public static void main (String[] args)
	{
		try
		{
			Locale.setDefault(Locale.CANADA);
			get();
			System.out.println("OK 1");
			get();
			System.out.println("OK 1a");
			new CachedRowSetImpl();
			System.out.println("OK 2");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}	//	main
	
	/**
	 *	To Collection
	 *	@return a <code>Collection</code> object that contains the values in 
	 * 		each row in this <code>CachedRowSet</code> object
	 *	@throws SQLException
	 */
	public Collection<?> toCollection () throws SQLException
	{
		return super.toCollection ();
	}
	/**
	 *	To Collection
	 *	@param column an <code>int</code> indicating the column whose values
	 *	are to be represented in a <code>Collection</code> object
	 *	@return a <code>Collection</code> object that contains the values
	 *	stored in the specified column of this <code>CachedRowSet</code> object
	 *	@throws SQLException
	 */
	public Collection<?> toCollection (int column) throws SQLException
	{
		return super.toCollection (column);
	}
	/**
	 *	To Collection
	 * @param column a <code>String</code> object giving the name of the 
	 *        column whose values are to be represented in a collection
	 * @return a <code>Collection</code> object that contains the values
	 * 		stored in the specified column of this <code>CachedRowSet</code> object
	 * @throws SQLException if an error occurs generating the collection or
	 * 	an invalid column id is provided
	 */
	public Collection<?> toCollection (String column) throws SQLException
	{
		return super.toCollection (column);
	}

	@Override
	public int getInt(int idx) throws SQLException {
		//CachedRowSetImpl throw null pointer exception converting 
		//decimal value to integer
		try {
			return super.getInt(idx);
		} catch (NullPointerException e) {
			String s = getString(idx);
			if ( s == null ) return 0;
			return Double.valueOf(s).intValue();
		}
	}

	@Override
	public int getInt(String column) throws SQLException {
		//CachedRowSetImpl throw null pointer exception converting 
		//decimal value to integer
		try {
			return super.getInt(column);
		} catch (NullPointerException e) {
			String s = getString(column);
			if ( s == null ) return 0;
			return Double.valueOf(s).intValue();
		}	
	}

    @Override
    public void setTypeMap(Map map) {
        super.setTypeMap(map);
    }
}	//	CCachedRowSet
