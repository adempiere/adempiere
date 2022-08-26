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
package org.compiere.model;

import io.vavr.collection.List;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.ResultSetIterable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

/**
 *  Persistent Object Info.
 *  Provides structural information
 *
 *  @author Jorg Janke
 *  @version $Id: POInfo.java,v 1.2 2006/07/30 00:58:37 jjanke Exp $
 *  @author Victor Perez, e-Evolution SC
 *			<li>[ 2195894 ] Improve performance in PO engine
 *			<li>http://sourceforge.net/tracker/index.php?func=detail&aid=2195894&group_id=176962&atid=879335
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *			<li> FR [ 390 ] Special Tables are with hardcode instead use attribute
 *			@see https://github.com/adempiere/adempiere/issues/390
 *			<a href="https://github.com/adempiere/adempiere/issues/922">
 * 			@see FR [ 922 ] Is Allow Copy in model</a>
 *  @author Michael McKay, mckayERP@gmail.com
 *  		<li><A href="https://github.com/adempiere/adempiere/issues/2428">#2428 Allow changes to AD_Column and AD_Table</a>
 *  		Uses explicit references to column names rather than '*'
 *  @author Raul Capecce, Openup Solutions http://openupsolutions.com/
 *  		<li><a href="https://github.com/adempiere/adempiere/issues/3968">#3968 [Bug Report] POInfo cached sql sintax error</a></li>
 */
public class POInfo implements Serializable
{
	/** Used by Remote FinReport			*/
	static final long serialVersionUID = -5976719579744948419L;

	/**
	 *  POInfo Factory
	 *  @param ctx context
	 *  @param AD_Table_ID AD_Table_ID
	 *  @return POInfo
	 */
	public static POInfo getPOInfo (Properties ctx, int AD_Table_ID)
	{
		return getPOInfo(ctx, AD_Table_ID, null);
	}
	
	/**
	 *  POInfo Factory
	 *  @param ctx context
	 *  @param AD_Table_ID AD_Table_ID
	 *  @param trxName Transaction name
	 *  @return POInfo
	 */
	public static POInfo getPOInfo (Properties ctx, int AD_Table_ID, String trxName)
	{
		Integer key = AD_Table_ID;
		POInfo retValue = (POInfo)s_cache.get(key);
		if (retValue == null)
		{
			retValue = new POInfo(ctx, AD_Table_ID, false, trxName);
			if (retValue.getColumnCount() == 0)
			{
				//	May be run before Language verification
				retValue = new POInfo(ctx, AD_Table_ID, true, trxName);
			}
			else
			{
				s_cache.put(key, retValue);
				s_cacheByTableName.put(retValue.getTableName(), retValue); // metas
			}
		}
		return retValue;
	}   //  getPOInfo

	/** Cache of POInfo     */
	private static CCache<Integer,POInfo>  s_cache = new CCache<Integer,POInfo>("POInfo", 200);

	// #2428
	/** Cache of POInfo Columns     */
	private static StringBuffer  columnListCache = null;
	/** Cached of POInfo Translated Columns     */
	private static StringBuffer translatedColumnListCache = null;

	/**************************************************************************
	 *  Create Persistent Info
	 *  @param ctx context
	 *  @param AD_Table_ID AD_ Table_ID
	 * 	@param baseLanguageOnly get in base language
	 */
	private POInfo (Properties ctx, int AD_Table_ID, boolean baseLanguageOnly)
	{
		this(ctx, AD_Table_ID, baseLanguageOnly, null);
	}
	
	/**************************************************************************
	 *  Create Persistent Info
	 *  @param ctx context
	 *  @param AD_Table_ID AD_ Table_ID
	 * 	@param baseLanguageOnly get in base language
	 *  @param trxName transaction name
	 */
	private POInfo (Properties ctx, int AD_Table_ID, boolean baseLanguageOnly, String trxName)
	{
		m_ctx = ctx;
		tableId = AD_Table_ID;
		boolean baseLanguage = baseLanguageOnly ? true : Env.isBaseLanguage(m_ctx, "AD_Table");
		loadInfo (baseLanguage, trxName);
	}   //  PInfo

	/** Context             	*/
	private Properties  m_ctx = null;
	/** Table_ID            	*/
	private int         tableId = 0;
	/** Table Name          	*/
	private String      tableName = null;
	/** Access Level			*/
	private String		accessLevel = MTable.ACCESSLEVEL_Organization;
	/** Columns             	*/
	private POInfoColumn[]    columns = null;
	/** Table has Key Column	*/ 
	private boolean		hasKeyColumn = false;
	/**	Table needs keep log	*/
	private boolean 	isChangeLog = false;
	/**	Ignore Migration Log	*/
	private boolean 	isIgnoreMigration = false;
	/**	Ignore Allow Copy		*/
	private boolean 	isAllowCopy = false;
	/**	Key Column Name			*/
	private String keyColumnName = null;
	/**	Cache					*/
	private static final CCache<String,POInfo>  s_cacheByTableName = new CCache<String,POInfo>("POInfo_ByTableName", 200);
	/**
	 *  Load Table/Column Info
	 * 	@param baseLanguage in English
	 *  @param trxName
	 */
	private void loadInfo (boolean baseLanguage, String trxName)
	{
		ArrayList<POInfoColumn> list = new ArrayList<POInfoColumn>(15);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ")
				.append(getPOInfoColumnList(baseLanguage));
		sql.append(" FROM AD_Table t"
				+ " INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID)"
				+ " LEFT OUTER JOIN AD_Val_Rule vr ON (c.AD_Val_Rule_ID=vr.AD_Val_Rule_ID)"
				+ " INNER JOIN AD_Element e ON (c.AD_Element_ID=e.AD_Element_ID) ");
		if (!baseLanguage) {
			sql.append("LEFT JOIN AD_Element_Trl etrl ON (e.AD_Element_ID = etrl.AD_Element_ID)");
		}
		sql.append(" WHERE t.").append(tableId <= 0 ? "TableName = ?" : "AD_Table_ID = ?")
				.append(" AND c.IsActive=?");
		if (!baseLanguage) {
			sql.append(" AND etrl.AD_Language = ?");
		}
		List<Object> paramenters = List.of(tableId <= 0 ? tableName : tableId, "Y" );
		DB.runResultSetFunction.apply(
				trxName,
				sql.toString() ,
				baseLanguage ? paramenters : paramenters.append(Env.getAD_Language(m_ctx)),
				resultSet -> {
					//	Get Standard Columns
					List<POInfoColumn> infoColumns = new ResultSetIterable<>(resultSet, row -> {
						//  For ResultSet order, see getPOInfoColumnList()
						//	Get Table
						if (tableName == null)
							tableName = resultSet.getString(1);
						if (tableId <= 0)
							tableId = resultSet.getInt("AD_Table_ID");

						String ColumnName = row.getString(2);
						int AD_Reference_ID = row.getInt(3);
						boolean IsMandatory = "Y".equals(row.getString(4));
						boolean IsUpdateable = "Y".equals(row.getString(5));
						String DefaultLogic = row.getString(6);
						String Name = row.getString(7);
						String Description = row.getString(8);
						int AD_Column_ID = row.getInt(9);
						boolean IsKey = "Y".equals(row.getString(10));
						// metas: begin
						if (IsKey)
						{
							if (hasKeyColumn)
							{
								// it already has a key column, which means that this table has multi-primary key
								// so we don't have a single key column
								keyColumnName = null;
							}
							else
							{
								keyColumnName = ColumnName;
							}
						}
						// metas: end
						if (IsKey)
							hasKeyColumn = true;

						boolean IsParent = "Y".equals(row.getString(11));
						int AD_Reference_Value_ID = row.getInt(12);
						String ValidationCode = row.getString(13);
						int FieldLength = row.getInt(14);
						String ValueMin = row.getString(15);
						String ValueMax = row.getString(16);
						boolean IsTranslated = "Y".equals(row.getString(17));
						accessLevel = row.getString(18);
						String ColumnSQL = row.getString(19);
						boolean IsEncrypted = "Y".equals(row.getString(20));
						boolean IsAllowLogging = "Y".equals(row.getString(21));
						isChangeLog="Y".equals(row.getString(22));
						//	Load additional attributes
						loadAdditionalAttributes(row);
						return new POInfoColumn (
								AD_Column_ID, ColumnName, ColumnSQL, AD_Reference_ID,
								IsMandatory, IsUpdateable,
								DefaultLogic, Name, Description,
								IsKey, IsParent,
								AD_Reference_Value_ID, ValidationCode,
								FieldLength, ValueMin, ValueMax,
								IsTranslated, IsEncrypted,
								IsAllowLogging,
								isAllowCopy,
								trxName);
					}).toList();
					list.addAll(infoColumns.asJava());
				}).onFailure(throwable -> {
					throw new AdempiereException(throwable);
				});
		//  convert to array
		columns = new POInfoColumn[list.size()];
		list.toArray(columns);
	}   //  loadInfo
	
	/**
	 * Get the POInfo column list from cache or generate it.  The list of columns is
	 * drawn from AD_Table and AD_Column and needs to be explicit (i.e. no '*').  The 
	 * use of an asterisk can cause a "Cache plan must not change result type" error if
	 * a column is added to either table. See issue <a href="https://github.com/adempiere/adempiere/issues/2428">#2428</a>.
	 * <p>The column list is intended to be appended to a select statement as 
	 * <pre>
	 *	sql.append("SELECT ")
	 *		.append(getPOInfoColumnList());
	 *	sql.append(" FROM AD_Table t"
	 *		+ " INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID)"
	 *		+ " LEFT OUTER JOIN AD_Val_Rule vr ON (c.AD_Val_Rule_ID=vr.AD_Val_Rule_ID)"
	 *		+ " INNER JOIN AD_Element");
	 * </pre> 
	 * <p>The order of the columns in the list is
	 * <ol>
	 * 	<li>t.TableName
	 * 	<li>c.ColumnName
	 * 	<li>c.AD_Reference_ID
	 * 	<li>c.IsMandatory
	 * 	<li>c.IsUpdateable
	 * 	<li>c.DefaultValue
	 * 	<li>e.Name when baseLanguage else etrl.Name or eName
	 * 	<li>e.Description when baseLanguage else etrl.Description or e.Description
	 * 	<li>c.AD_Column_ID
	 * 	<li>c.IsKey
	 * 	<li>c.IsParent
	 * 	<li>c.AD_Reference_Value_ID
	 * 	<li>vr.Code
	 * 	<li>c.FieldLength
	 * 	<li>c.ValueMin
	 * 	<li>c.ValueMax
	 * 	<li>c.IsTranslated
	 * 	<li>t.AccessLevel
	 * 	<li>c.ColumnSQL
	 * 	<li>c.IsEncrypted
	 * 	<li>c.IsAllowLogging
	 * 	<li>t.IsChangeLog
	 * 	<li>t.AD_Table_ID
	 * </ol>
	 * <p>Any additional columns will follow this list in undefined order.  See {@link #loadAdditionalAttributes(ResultSet)}
	 * for examples on how to access these columns.
	 * @param baseLanguage baseLanguage
	 * @return
	 */
	private StringBuffer getPOInfoColumnList(boolean baseLanguage) {

		if (baseLanguage) {
			if (columnListCache != null && columnListCache.length() > 0) {
				return columnListCache;
			}
		} else {
			if (translatedColumnListCache != null && translatedColumnListCache.length() > 0) {
				return translatedColumnListCache;
			}
		}

		StringBuilder columnList = new StringBuilder("t.TableName, "
				+ "c.ColumnName, "
				+ "c.AD_Reference_ID,"
				+ "c.IsMandatory, "
				+ "c.IsUpdateable, "
				+ "c.DefaultValue, ");

		if (baseLanguage)
			columnList.append("e.Name, e.Description, ");
		 else
			columnList.append("COALESCE(etrl.Name, e.Name) AS Name, COALESCE(etrl.Description, e.Description) AS Description, ");

		columnList.append("c.AD_Column_ID, "
				+ "c.IsKey, "
				+ "c.IsParent, "
				+ "c.AD_Reference_Value_ID, "
				+ "vr.Code, "
				+ "c.FieldLength, "
				+ "c.ValueMin, "
				+ "c.ValueMax, "
				+ "c.IsTranslated, "
				+ "t.AccessLevel, "
				+ "c.ColumnSQL, "
				+ "c.IsEncrypted, "
				+ "c.IsAllowLogging, "
				+ "t.IsChangeLog, "
				+ "t.AD_Table_ID");

		String columnListLC = columnList.toString().toLowerCase();
		StringBuilder extraColumns = new StringBuilder();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try
		{
		
			// Get the list of columns for AD_Table and AD_Column
			conn = DB.getConnectionRO();
			DatabaseMetaData md = conn.getMetaData();
			String catalog = DB.getDatabase().getCatalog();
			String schema = DB.getDatabase().getSchema();
			String tableName = I_AD_Table.Table_Name;
			if (md.storesUpperCaseIdentifiers())
			{
				tableName = tableName.toUpperCase();
			}
			else if (md.storesLowerCaseIdentifiers())
			{
				tableName = tableName.toLowerCase();
			}
			
			rs = md.getColumns(catalog, schema, tableName, null);
			
			while (rs.next())
			{
				String columnName = rs.getString ("COLUMN_NAME");
				if (!columnListLC.contains("t." + columnName.toLowerCase()))
				{
					extraColumns.append(", t." + columnName);
				}
			}
			rs.close();
			rs = null;
	
			tableName = I_AD_Column.Table_Name;
			if (md.storesUpperCaseIdentifiers())
			{
				tableName = tableName.toUpperCase();
			}
			else if (md.storesLowerCaseIdentifiers())
			{
				tableName = tableName.toLowerCase();
			}
			
			rs = md.getColumns(catalog, schema, tableName, null);
			
			while (rs.next())
			{
				String columnName = rs.getString ("COLUMN_NAME").toLowerCase();
				if (!columnListLC.contains("c." + columnName.toLowerCase()))
				{
					extraColumns.append(", c." + columnName);
				}
			}
	
		}
		catch (SQLException e) {
			CLogger.get().log(Level.SEVERE, "Exception when trying to access metadata", e);
		}
		finally {
			DB.close(rs, pstmt);
			try {
				conn.close();
			} catch (SQLException e) {
				CLogger.get().log(Level.SEVERE, "Unable to close connection.", e);
			}
			rs = null; pstmt = null;
			conn = null;
		}

		if (baseLanguage) {
			columnListCache = new StringBuffer().append(columnList).append(extraColumns);
			return columnListCache;
		} else {
			translatedColumnListCache = new StringBuffer().append(columnList).append(extraColumns);
			return translatedColumnListCache;
		}
	}
	
	/**
	 * Reset the POInfo column list.  Call this method if any changes to the columns in
	 * AD_Table or AD_Column have been made. See <A href="https://github.com/adempiere/adempiere/issues/2428">issue #2428</a>.
	 * After making this call, the next call to getPOInfo() will reload the columnListCache.
	 */
	public static void resetPOInfoColumnList() {
		columnListCache = null;
		translatedColumnListCache = null;
	}

	/**
	 * Get Additional attributes
	 * @param rs
	 * @throws SQLException 
	 */
	private void loadAdditionalAttributes(ResultSet rs) throws SQLException {
		//	Get Meta-Data
		ResultSetMetaData rsmd = rs.getMetaData();
		//	FR [ 390 ]
		//	Additional columns
		//	
		for(int i = 1; i <= rsmd.getColumnCount(); i++) {
			String columnName = rsmd.getColumnName(i);
			if (columnName.equalsIgnoreCase(I_AD_Table.COLUMNNAME_IsIgnoreMigration)) {
				String value = rs.getString(i);
				isIgnoreMigration = (value != null && value.equals("Y"));
			} else if(columnName.equalsIgnoreCase(I_AD_Column.COLUMNNAME_IsAllowCopy)) {
				String value = rs.getString(i);
				isAllowCopy = (value != null && value.equals("Y"));
			}
		}
	}

	/**
	 *  String representation
	 *  @return String Representation
	 */
	@Override
	public String toString()
	{
		return "POInfo[" + getTableName() + ",AD_Table_ID=" + getAD_Table_ID() + "]";
	}   //  toString

	/**
	 *  String representation for index
	 * 	@param index column index
	 *  @return String Representation
	 */
	public String toString (int index)
	{
		if (index < 0 || index >= columns.length)
			return "POInfo[" + getTableName() + "-(InvalidColumnIndex=" + index + ")]";
		return "POInfo[" + getTableName() + "-" + columns[index].toString() + "]";
	}   //  toString

	/**
	 *  Get Table Name
	 *  @return Table Name
	 */
	public String getTableName()
	{
		return tableName;
	}   //  getTableName

	/**
	 *  Get AD_Table_ID
	 *  @return AD_Table_ID
	 */
	public int getAD_Table_ID()
	{
		return tableId;
	}   //  getAD_Table_ID
	
	/**
	 * Get Ignore Migration Attribute
	 * @return
	 */
	public boolean isIgnoreMigration() {
		return isIgnoreMigration;
	}

	/**
	 * 	Table has a Key Column
	 *	@return true if has a key column
	 */
	public boolean hasKeyColumn()
	{
		return hasKeyColumn;
	}	//	hasKeyColumn

	/**
	 * 	Get Table Access Level
	 *	@return Table.ACCESS..
	 */
	public String getAccessLevel()
	{
		return accessLevel;
	}	//	getAccessLevel
	
	/**************************************************************************
	 *  Get ColumnCount
	 *  @return column count
	 */
	public int getColumnCount()
	{
		return columns.length;
	}   //  getColumnCount

	/**
	 *  Get Column Index
	 *  @param ColumnName column name
	 *  @return index of column with ColumnName or -1 if not found
	 */
	public int getColumnIndex (String ColumnName)
	{
		for (int i = 0; i < columns.length; i++)
		{
			if (ColumnName.equalsIgnoreCase(columns[i].ColumnName)) // teo_sarca : modified to compare ignoring case [ 1619179 ]
				return i;
		}
		return -1;
	}   //  getColumnIndex

	/**
	 *  Get Column Index
	 *  @param AD_Column_ID column
	 *  @return index of column with ColumnName or -1 if not found
	 */
	public int getColumnIndex (int AD_Column_ID)
	{
		for (int i = 0; i < columns.length; i++)
		{
			if (AD_Column_ID == columns[i].AD_Column_ID)
				return i;
		}
		return -1;
	}   //  getColumnIndex
	
	/**
	 * @param columnName
	 * @return AD_Column_ID if found, -1 if not found
	 */
	public int getAD_Column_ID(String columnName) 
	{
		for (int i = 0; i < columns.length; i++)
		{
			if (columnName.equalsIgnoreCase(columns[i].ColumnName)) // globalqss : modified to compare ignoring case [ 1619179 ]
				return columns[i].AD_Column_ID;
		}
		return -1;
	}

	/**
	 *  Get Column
	 *  @param index index
	 *  @return column
	 */
	// metas: making getColumn public to enable easier testing (was protected)
	public POInfoColumn getColumn (int index)
	{
		if (index < 0 || index >= columns.length)
			return null;
		return columns[index];
	}   //  getColumn

	/**
	 *  Get Column Name
	 *  @param index index
	 *  @return ColumnName column name
	 */
	public String getColumnName (int index)
	{
		if (index < 0 || index >= columns.length)
			return null;
		return columns[index].ColumnName;
	}   //  getColumnName

	/**
	 *  Get Column SQL or Column Name
	 *  @param index index
	 *  @return ColumnSQL column sql or name
	 */
	public String getColumnSQL (int index)
	{
		if (index < 0 || index >= columns.length)
			return null;
		if (columns[index].ColumnSQL != null && columns[index].ColumnSQL.length() > 0)
			return columns[index].ColumnSQL + " AS " + columns[index].ColumnName;
		return columns[index].ColumnName;
	}   //  getColumnSQL

	/**
	 *  Is Column Virtual?
	 *  @param index index
	 *  @return true if column is virtual
	 */
	public boolean isVirtualColumn (int index)
	{
		if (index < 0 || index >= columns.length)
			return true;
		return columns[index].ColumnSQL != null 
			&& columns[index].ColumnSQL.length() > 0;
	}   //  isVirtualColumn

	/**
	 *  Get Column Label
	 *  @param index index
	 *  @return column label
	 */
	public String getColumnLabel (int index)
	{
		if (index < 0 || index >= columns.length)
			return null;
		return columns[index].ColumnLabel;
	}   //  getColumnLabel

	/**
	 *  Get Column Description
	 *  @param index index
	 *  @return column description
	 */
	public String getColumnDescription (int index)
	{
		if (index < 0 || index >= columns.length)
			return null;
		return columns[index].ColumnDescription;
	}   //  getColumnDescription

	/**
	 *  Get Column Class
	 *  @param index index
	 *  @return Class
	 */
	public Class<?> getColumnClass (int index)
	{
		if (index < 0 || index >= columns.length)
			return null;
		return columns[index].ColumnClass;
	}   //  getColumnClass

	/**
	 *  Get Column Display Type
	 *  @param index index
	 *  @return DisplayType
	 */
	public int getColumnDisplayType (int index)
	{
		if (index < 0 || index >= columns.length)
			return DisplayType.String;
		return columns[index].DisplayType;
	}   //  getColumnDisplayType

	/**
	 *  Get Column Default Logic
	 *  @param index index
	 *  @return Default Logic
	 */
	public String getDefaultLogic (int index)
	{
		if (index < 0 || index >= columns.length)
			return null;
		return columns[index].DefaultLogic;
	}   //  getDefaultLogic

	/**
	 *  Is Column Mandatory
	 *  @param index index
	 *  @return true if column mandatory
	 */
	public boolean isColumnMandatory (int index)
	{
		if (index < 0 || index >= columns.length)
			return false;
		return columns[index].IsMandatory;
	}   //  isMandatory

	// metas-mo73_03035 begin
	// method has been added to find out which table a given column references
	//
	/**
	 *  Returns the columns <code>AD_Reference_Value_ID</code>.
	 *  @param index index
	 *  @return the column's AD_Reference_Value_ID or -1 if the given index is not valid
	 */
	public int getColumnReferenceValueId (int index)
	{
		if (index < 0 || index >= columns.length)
			return -1;
		return columns[index].AD_Reference_Value_ID;
	}   
	//
	// metas-mo73_03035 end
	
	/**
	 *  Is Column Updateable
	 *  @param index index
	 *  @return true if column updateable
	 */
	public boolean isColumnUpdateable (int index)
	{
		if (index < 0 || index >= columns.length)
			return false;
		return columns[index].IsUpdateable;
	}   //  isUpdateable

	/**
	 *  Set Column Updateable
	 *  @param index index
	 *  @param updateable column updateable
	 */
	public void setColumnUpdateable (int index, boolean updateable)
	{
		if (index < 0 || index >= columns.length)
			return;
		columns[index].IsUpdateable = updateable;
	}	//	setColumnUpdateable

	/**
	 * 	Set all columns updateable
	 * 	@param updateable updateable
	 */
	public void setUpdateable (boolean updateable)
	{
		for (int i = 0; i < columns.length; i++)
			columns[i].IsUpdateable = updateable;
	}	//	setUpdateable

	/**
	 *  Is Lookup Column
	 *  @param index index
	 *  @return true if it is a lookup column
	 */
	public boolean isColumnLookup (int index)
	{
		if (index < 0 || index >= columns.length)
			return false;
		return DisplayType.isLookup(columns[index].DisplayType);
	}   //  isColumnLookup

	/**
	 *  Get Lookup
	 *  @param index index
	 *  @return Lookup
	 */
	public Lookup getColumnLookup (int index)
	{
		if (!isColumnLookup(index))
			return null;
		//
		int WindowNo = 0;
		//  List, Table, TableDir
		Lookup lookup = null;
		try
		{
			lookup = MLookupFactory.get (m_ctx, WindowNo,
				columns[index].AD_Column_ID, columns[index].DisplayType,
				Env.getLanguage(m_ctx), columns[index].ColumnName,
				columns[index].AD_Reference_Value_ID,
				columns[index].IsParent, columns[index].ValidationCode);
		}
		catch (Exception e)
		{
			lookup = null;          //  cannot create Lookup
		}
		return lookup;
		/** @todo other lookup types */
	}   //  getColumnLookup

	/**
	 *  Is Column Key
	 *  @param index index
	 *  @return true if column is the key
	 */
	public boolean isKey (int index)
	{
		if (index < 0 || index >= columns.length)
			return false;
		return columns[index].IsKey;
	}   //  isKey

	/**
	 *  Is Column Parent
	 *  @param index index
	 *  @return true if column is a Parent
	 */
	public boolean isColumnParent (int index)
	{
		if (index < 0 || index >= columns.length)
			return false;
		return columns[index].IsParent;
	}   //  isColumnParent

	/**
	 *  Is Column Translated
	 *  @param index index
	 *  @return true if column is translated
	 */
	public boolean isColumnTranslated (int index)
	{
		if (index < 0 || index >= columns.length)
			return false;
		return columns[index].IsTranslated;
	}   //  isColumnTranslated

	/**
	 *  Is Table Translated
	 *  @return true if table is translated
	 */
	public boolean isTranslated ()
	{
		for (int i = 0; i < columns.length; i++)
		{
			if (columns[i].IsTranslated)
				return true;
		}
		return false;
	}   //  isTranslated

	/**
	 *  Is Column (data) Encrypted
	 *  @param index index
	 *  @return true if column is encrypted
	 */
	public boolean isEncrypted (int index)
	{
		if (index < 0 || index >= columns.length)
			return false;
		return columns[index].IsEncrypted;
	}   //  isEncrypted

	/**
	 * Is allowed logging on this column
	 * 
	 * @param index
	 *            index
	 * @return true if column is allowed to be logged
	 */
	public boolean isAllowLogging(int index) {
		if (index < 0 || index >= columns.length)
			return false;
		return columns[index].IsAllowLogging;
	} // isAllowLogging
	
	/**
	 * Is allowed a copy on this column
	 * 
	 * @param index
	 *            index
	 * @return true if column is allowed to be logged
	 */
	public boolean isAllowCopy(int index) {
		if (index < 0 || index >= columns.length)
			return false;
		return columns[index].IsAllowCopy;
	} // isAllowCopy

	/**
	 *  Get Column FieldLength
	 *  @param index index
	 *  @return field length
	 */
	public int getFieldLength (int index)
	{
		if (index < 0 || index >= columns.length)
			return 0;
		return columns[index].FieldLength;
	}   //  getFieldLength

	/**
	 *  Get Column FieldLength
	 *  @param columnName Column Name
	 *  @return field length or 0
	 */
	public int getFieldLength (String columnName)
	{
		int index = getColumnIndex( columnName );
		if (index >= 0) {
			return getFieldLength( index );
		}
		return 0;
	}
	
	/**
	 *  Validate Content
	 *  @param index index
	 * 	@param value new Value
	 *  @return null if all valid otherwise error message
	 */
	public String validate (int index, Object value)
	{
		if (index < 0 || index >= columns.length)
			return "RangeError";
		//	Mandatory (i.e. not null
		if (columns[index].IsMandatory && value == null)
		{
			return "IsMandatory";
		}
		if (value == null)
			return null;
		
		//	Length ignored

		//
		if (columns[index].ValueMin != null)
		{
			BigDecimal value_BD = null;
			try
			{
				if (columns[index].ValueMin_BD != null)
					value_BD = new BigDecimal(value.toString());
			}
			catch (Exception ex){}
			//	Both are Numeric
			if (columns[index].ValueMin_BD != null && value_BD != null)
			{	//	error: 1 - 0 => 1  -  OK: 1 - 1 => 0 & 1 - 10 => -1
				int comp = columns[index].ValueMin_BD.compareTo(value_BD);
				if (comp > 0)
				{
					return "MinValue=" + columns[index].ValueMin_BD 
						+ "(" + columns[index].ValueMin + ")"
						+ " - compared with Numeric Value=" + value_BD + "(" + value + ")"
						+ " - results in " + comp;
				}
			}
			else	//	String
			{
				int comp = columns[index].ValueMin.compareTo(value.toString());
				if (comp > 0)
				{
					return "MinValue=" + columns[index].ValueMin
					  + " - compared with String Value=" + value
					  + " - results in " + comp;
				}
			}
		}
		if (columns[index].ValueMax != null)
		{
			BigDecimal value_BD = null;
			try
			{
				if (columns[index].ValueMax_BD != null)
					value_BD = new BigDecimal(value.toString());
			}
			catch (Exception ex){}
			//	Both are Numeric
			if (columns[index].ValueMax_BD != null && value_BD != null)
			{	//	error 12 - 20 => -1  -  OK: 12 - 12 => 0 & 12 - 10 => 1
				int comp = columns[index].ValueMax_BD.compareTo(value_BD);
				if (comp < 0)
				{
					return "MaxValue=" + columns[index].ValueMax_BD + "(" + columns[index].ValueMax + ")"
					  + " - compared with Numeric Value=" + value_BD + "(" + value + ")"
					  + " - results in " + comp;
				}
			}
			else	//	String
			{
				int comp = columns[index].ValueMax.compareTo(value.toString());
				if (comp < 0)
				{
					return "MaxValue=" + columns[index].ValueMax
					  + " - compared with String Value=" + value
					  + " - results in " + comp;
				}
			}
		}
		return null;
	}   //  validate
	
	public boolean isLazyLoading(int index)
	{
		if (index < 0 || index >= columns.length)
			return true;
		return isVirtualColumn(index) && columns[index].IsLazyLoading; 
	}
	
	/**
	 * Build select clause
	 * @return stringbuffer
	 */
	public StringBuffer buildSelect()
	{
		StringBuffer sql = new StringBuffer();
		int size = getColumnCount();
		for (int i = 0; i < size; i++)
		{
			if (isLazyLoading(i))
				continue;
			if (sql.length() > 0)
				sql.append(",");
			sql.append(getColumnSQL(i));	//	Normal and Virtual Column
		}
		sql.insert(0, "SELECT ");
		sql.append(" FROM ").append(getTableName());
		return sql;
	}

	/**
	 * 
	 * @return if table save log
	 */
	public boolean isChangeLog()
	{
		return isChangeLog;
	}

	// metas: pr50_us215
	public boolean isCalculated(int index)
	{
		if (index < 0 || index >= columns.length)
			return false;
		return columns[index].IsCalculated
				;
	}
	
	/**
	 * Get Key Column Name
	 * @return
	 */
	public String getKeyColumnName()
	{
		return keyColumnName;
	}
	
	// metas-mo73_03035 begin
	//
	/**
	 * Makes sure that a POInfo with the given <code>tableName</code> is not cached (anymore). This method can be used
	 * to make sure that a subsequent call of one of any <code>getPOInfo(...)</code> method causes a reload from DB.
	 * 
	 * @param tableName
	 *           if there is a cached POInfo instance whose {@link #getTableName()} method returns this string, it is removed from cache
	 */
	public static void removeFromCache(final String tableName)
	{
		final POInfo cachedValue = s_cacheByTableName.get(tableName);
		if(cachedValue == null)
		{
			return; // nothing to do
		}
		s_cacheByTableName.remove(tableName);
		s_cache.remove(cachedValue.getAD_Table_ID());
	}
	
	/**
	 * Makes sure that a POInfo with the given <code>AD_Table_ID</code> is not cached (anymore). This method can be used
	 * to make sure that a subsequent call of one of any <code>getPOInfo(...)</code> method causes a reload from DB.
	 * 
	 * @param AD_Table_ID
	 *            if there is a cached POInfo instance whose {@link #getAD_Table_ID()} method returns this int, it is removed from cache
	 */
	public static void removeFromCache(final int AD_Table_ID)
	{
		final POInfo cachedValue = s_cache.get(AD_Table_ID);
		if(cachedValue == null)
		{
			return; // nothing to do
		}
		s_cache.remove(AD_Table_ID);
		s_cacheByTableName.remove(cachedValue.getTableName());
	}
	//
	// metas-mo73_03035 end
	
	public static POInfo getPOInfo (Properties ctx, String tableName)
	{
		return getPOInfo(ctx, tableName, null);
	}
	
	public static POInfo getPOInfo (Properties ctx, String tableName, String trxName)
	{
		POInfo retValue = s_cacheByTableName.get(tableName);
		if (retValue == null)
		{
			retValue = new POInfo(ctx, tableName, false, trxName);
			if (retValue.getColumnCount() == 0)
			{
				//	May be run before Language verification
				retValue = new POInfo(ctx, tableName, true, trxName);
			}
			else
			{
				s_cache.put(retValue.getAD_Table_ID(), retValue);
				s_cacheByTableName.put(retValue.getTableName(), retValue); // metas
			}
		}
		return retValue;
	}   //  getPOInfo
	
	public int getAccessLevelInt()
	{
		return Integer.parseInt(accessLevel);
	}
	
	/**
	 * Get PO Info
	 * @param ctx
	 * @param tableName
	 * @param baseLanguageOnly
	 * @param trxName
	 */
	private POInfo (Properties ctx, String tableName, boolean baseLanguageOnly, String trxName)
	{
		m_ctx = ctx;
		this.tableName = tableName;
		boolean baseLanguage = baseLanguageOnly ? true : Env.isBaseLanguage(m_ctx, "AD_Table");
		loadInfo (baseLanguage, trxName);
	}   //  PInfo

}   //  POInfo
