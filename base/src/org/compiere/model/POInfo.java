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

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

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
		m_AD_Table_ID = AD_Table_ID;
		boolean baseLanguage = baseLanguageOnly ? true : Env.isBaseLanguage(m_ctx, "AD_Table");
		loadInfo (baseLanguage, trxName);
	}   //  PInfo

	/** Context             	*/
	private Properties  m_ctx = null;
	/** Table_ID            	*/
	private int         m_AD_Table_ID = 0;
	/** Table Name          	*/
	private String      m_TableName = null;
	/** Access Level			*/
	private String		m_AccessLevel = MTable.ACCESSLEVEL_Organization;
	/** Columns             	*/
	private POInfoColumn[]    m_columns = null;
	/** Table has Key Column	*/ 
	private boolean		m_hasKeyColumn = false;
	/**	Table needs keep log*/
	private boolean 	m_IsChangeLog = false;
	/**	Ignore Migration Log*/
	private boolean 	m_IsIgnoreMigration = false;

	/**
	 *  Load Table/Column Info
	 * 	@param baseLanguage in English
	 *  @param trxName
	 */
	private void loadInfo (boolean baseLanguage, String trxName)
	{
		ArrayList<POInfoColumn> list = new ArrayList<POInfoColumn>(15);
		StringBuffer sql = new StringBuffer();
		//	FR [ 390 ] Get Native Columns
		
		sql.append("SELECT "
				+ "t.TableName, "
				+ "c.ColumnName, "
				+ "c.AD_Reference_ID,"
				+ "c.IsMandatory, "
				+ "c.IsUpdateable, "
				+ "c.DefaultValue,"
				+ "e.Name, "
				+ "e.Description, "
				+ "c.AD_Column_ID, "
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
				+ "t.AD_Table_ID, "
				//	FR [ 390 ]
				//	Additional columns
				+ "t.*, c.* "
		);
		sql.append(" FROM AD_Table t"
			+ " INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID)"
			+ " LEFT OUTER JOIN AD_Val_Rule vr ON (c.AD_Val_Rule_ID=vr.AD_Val_Rule_ID)"
			+ " INNER JOIN AD_Element");
		if (!baseLanguage)
			sql.append("_Trl");
		sql.append(" e "
			+ " ON (c.AD_Element_ID=e.AD_Element_ID) "
			+ "WHERE t." + (m_AD_Table_ID <= 0 ? "TableName=?" : "AD_Table_ID=?")
			+ " AND c.IsActive='Y'");
		if (!baseLanguage)
			sql.append(" AND e.AD_Language='").append(Env.getAD_Language(m_ctx)).append("'");
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if (m_AD_Table_ID <= 0)
				pstmt.setString(1, m_TableName);
			else
				pstmt.setInt(1, m_AD_Table_ID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//	Get Table
				if (m_TableName == null)
					m_TableName = rs.getString(1);
				if (m_AD_Table_ID <= 0)
					m_AD_Table_ID = rs.getInt("AD_Table_ID");
				//	Load additional attributes
				loadAdditionalAttributes(rs);
				//	Get Standard Columns
				do {
					String ColumnName = rs.getString(2);
					int AD_Reference_ID = rs.getInt(3);
					boolean IsMandatory = "Y".equals(rs.getString(4));
					boolean IsUpdateable = "Y".equals(rs.getString(5));
					String DefaultLogic = rs.getString(6);
					String Name = rs.getString(7);
					String Description = rs.getString(8);
					int AD_Column_ID = rs.getInt(9);
					boolean IsKey = "Y".equals(rs.getString(10));
					// metas: begin
					if (IsKey)
					{
						if (m_hasKeyColumn)
						{
							// it already has a key column, which means that this table has multi-primary key
							// so we don't have a single key column
							m_keyColumnName = null;
						}
						else
						{
							m_keyColumnName = ColumnName;
						}
					}
					// metas: end
					if (IsKey)
						m_hasKeyColumn = true;
					boolean IsParent = "Y".equals(rs.getString(11));
					int AD_Reference_Value_ID = rs.getInt(12);
					String ValidationCode = rs.getString(13);
					int FieldLength = rs.getInt(14);
					String ValueMin = rs.getString(15);
					String ValueMax = rs.getString(16);
					boolean IsTranslated = "Y".equals(rs.getString(17));
					//
					m_AccessLevel = rs.getString(18);
					String ColumnSQL = rs.getString(19);
					boolean IsEncrypted = "Y".equals(rs.getString(20));
					boolean IsAllowLogging = "Y".equals(rs.getString(21));
					m_IsChangeLog="Y".equals(rs.getString(22));

					POInfoColumn col = new POInfoColumn (
						AD_Column_ID, ColumnName, ColumnSQL, AD_Reference_ID,
						IsMandatory, IsUpdateable,
						DefaultLogic, Name, Description,
						IsKey, IsParent,
						AD_Reference_Value_ID, ValidationCode,
						FieldLength, ValueMin, ValueMax,
						IsTranslated, IsEncrypted,
						IsAllowLogging);
					list.add(col);
				} while(rs.next());
			}
		}
		catch (SQLException e)
		{
			CLogger.get().log(Level.SEVERE, sql.toString(), e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//  convert to array
		m_columns = new POInfoColumn[list.size()];
		list.toArray(m_columns);
	}   //  loadInfo
	
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
		//	First verify if exists
		HashMap<String, Boolean> existColumn = new HashMap<String, Boolean>();
		//	Add new attributes here
		existColumn.put("IsIgnoreMigration", false);
		//	
		for(int i = 1; i < rsmd.getColumnCount(); i++) {
			String columnName = rsmd.getColumnName(i);
			if (columnName.equalsIgnoreCase("IsIgnoreMigration")) {
				existColumn.put("IsIgnoreMigration", true);
				String value = rs.getString(i);
				m_IsIgnoreMigration = (value != null && value.equals("Y"));
			}
		}
		//	Valid Additional Columns
		for(Entry<String, Boolean> entry : existColumn.entrySet()) {
			//	Validate for warning
			if(!entry.getValue()) {
				CLogger.get().log(Level.WARNING, "Table [" + m_TableName + "] - Attribute [" + entry.getKey() + "] Not found");
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
		if (index < 0 || index >= m_columns.length)
			return "POInfo[" + getTableName() + "-(InvalidColumnIndex=" + index + ")]";
		return "POInfo[" + getTableName() + "-" + m_columns[index].toString() + "]";
	}   //  toString

	/**
	 *  Get Table Name
	 *  @return Table Name
	 */
	public String getTableName()
	{
		return m_TableName;
	}   //  getTableName

	/**
	 *  Get AD_Table_ID
	 *  @return AD_Table_ID
	 */
	public int getAD_Table_ID()
	{
		return m_AD_Table_ID;
	}   //  getAD_Table_ID
	
	/**
	 * Get Ignore Migration Attribute
	 * @return
	 */
	public boolean isIgnoreMigration() {
		return m_IsIgnoreMigration;
	}

	/**
	 * 	Table has a Key Column
	 *	@return true if has a key column
	 */
	public boolean hasKeyColumn()
	{
		return m_hasKeyColumn;
	}	//	hasKeyColumn

	/**
	 * 	Get Table Access Level
	 *	@return Table.ACCESS..
	 */
	public String getAccessLevel()
	{
		return m_AccessLevel;
	}	//	getAccessLevel
	
	/**************************************************************************
	 *  Get ColumnCount
	 *  @return column count
	 */
	public int getColumnCount()
	{
		return m_columns.length;
	}   //  getColumnCount

	/**
	 *  Get Column Index
	 *  @param ColumnName column name
	 *  @return index of column with ColumnName or -1 if not found
	 */
	public int getColumnIndex (String ColumnName)
	{
		for (int i = 0; i < m_columns.length; i++)
		{
			if (ColumnName.equalsIgnoreCase(m_columns[i].ColumnName)) // teo_sarca : modified to compare ignoring case [ 1619179 ]
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
		for (int i = 0; i < m_columns.length; i++)
		{
			if (AD_Column_ID == m_columns[i].AD_Column_ID)
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
		for (int i = 0; i < m_columns.length; i++)
		{
			if (columnName.equalsIgnoreCase(m_columns[i].ColumnName)) // globalqss : modified to compare ignoring case [ 1619179 ]
				return m_columns[i].AD_Column_ID;
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
		if (index < 0 || index >= m_columns.length)
			return null;
		return m_columns[index];
	}   //  getColumn

	/**
	 *  Get Column Name
	 *  @param index index
	 *  @return ColumnName column name
	 */
	public String getColumnName (int index)
	{
		if (index < 0 || index >= m_columns.length)
			return null;
		return m_columns[index].ColumnName;
	}   //  getColumnName

	/**
	 *  Get Column SQL or Column Name
	 *  @param index index
	 *  @return ColumnSQL column sql or name
	 */
	public String getColumnSQL (int index)
	{
		if (index < 0 || index >= m_columns.length)
			return null;
		if (m_columns[index].ColumnSQL != null && m_columns[index].ColumnSQL.length() > 0)
			return m_columns[index].ColumnSQL + " AS " + m_columns[index].ColumnName;
		return m_columns[index].ColumnName;
	}   //  getColumnSQL

	/**
	 *  Is Column Virtual?
	 *  @param index index
	 *  @return true if column is virtual
	 */
	public boolean isVirtualColumn (int index)
	{
		if (index < 0 || index >= m_columns.length)
			return true;
		return m_columns[index].ColumnSQL != null 
			&& m_columns[index].ColumnSQL.length() > 0;
	}   //  isVirtualColumn

	/**
	 *  Get Column Label
	 *  @param index index
	 *  @return column label
	 */
	public String getColumnLabel (int index)
	{
		if (index < 0 || index >= m_columns.length)
			return null;
		return m_columns[index].ColumnLabel;
	}   //  getColumnLabel

	/**
	 *  Get Column Description
	 *  @param index index
	 *  @return column description
	 */
	public String getColumnDescription (int index)
	{
		if (index < 0 || index >= m_columns.length)
			return null;
		return m_columns[index].ColumnDescription;
	}   //  getColumnDescription

	/**
	 *  Get Column Class
	 *  @param index index
	 *  @return Class
	 */
	public Class<?> getColumnClass (int index)
	{
		if (index < 0 || index >= m_columns.length)
			return null;
		return m_columns[index].ColumnClass;
	}   //  getColumnClass

	/**
	 *  Get Column Display Type
	 *  @param index index
	 *  @return DisplayType
	 */
	public int getColumnDisplayType (int index)
	{
		if (index < 0 || index >= m_columns.length)
			return DisplayType.String;
		return m_columns[index].DisplayType;
	}   //  getColumnDisplayType

	/**
	 *  Get Column Default Logic
	 *  @param index index
	 *  @return Default Logic
	 */
	public String getDefaultLogic (int index)
	{
		if (index < 0 || index >= m_columns.length)
			return null;
		return m_columns[index].DefaultLogic;
	}   //  getDefaultLogic

	/**
	 *  Is Column Mandatory
	 *  @param index index
	 *  @return true if column mandatory
	 */
	public boolean isColumnMandatory (int index)
	{
		if (index < 0 || index >= m_columns.length)
			return false;
		return m_columns[index].IsMandatory;
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
		if (index < 0 || index >= m_columns.length)
			return -1;
		return m_columns[index].AD_Reference_Value_ID;
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
		if (index < 0 || index >= m_columns.length)
			return false;
		return m_columns[index].IsUpdateable;
	}   //  isUpdateable

	/**
	 *  Set Column Updateable
	 *  @param index index
	 *  @param updateable column updateable
	 */
	public void setColumnUpdateable (int index, boolean updateable)
	{
		if (index < 0 || index >= m_columns.length)
			return;
		m_columns[index].IsUpdateable = updateable;
	}	//	setColumnUpdateable

	/**
	 * 	Set all columns updateable
	 * 	@param updateable updateable
	 */
	public void setUpdateable (boolean updateable)
	{
		for (int i = 0; i < m_columns.length; i++)
			m_columns[i].IsUpdateable = updateable;
	}	//	setUpdateable

	/**
	 *  Is Lookup Column
	 *  @param index index
	 *  @return true if it is a lookup column
	 */
	public boolean isColumnLookup (int index)
	{
		if (index < 0 || index >= m_columns.length)
			return false;
		return DisplayType.isLookup(m_columns[index].DisplayType);
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
				m_columns[index].AD_Column_ID, m_columns[index].DisplayType,
				Env.getLanguage(m_ctx), m_columns[index].ColumnName,
				m_columns[index].AD_Reference_Value_ID,
				m_columns[index].IsParent, m_columns[index].ValidationCode);
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
		if (index < 0 || index >= m_columns.length)
			return false;
		return m_columns[index].IsKey;
	}   //  isKey

	/**
	 *  Is Column Parent
	 *  @param index index
	 *  @return true if column is a Parent
	 */
	public boolean isColumnParent (int index)
	{
		if (index < 0 || index >= m_columns.length)
			return false;
		return m_columns[index].IsParent;
	}   //  isColumnParent

	/**
	 *  Is Column Translated
	 *  @param index index
	 *  @return true if column is translated
	 */
	public boolean isColumnTranslated (int index)
	{
		if (index < 0 || index >= m_columns.length)
			return false;
		return m_columns[index].IsTranslated;
	}   //  isColumnTranslated

	/**
	 *  Is Table Translated
	 *  @return true if table is translated
	 */
	public boolean isTranslated ()
	{
		for (int i = 0; i < m_columns.length; i++)
		{
			if (m_columns[i].IsTranslated)
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
		if (index < 0 || index >= m_columns.length)
			return false;
		return m_columns[index].IsEncrypted;
	}   //  isEncrypted

	/**
	 * Is allowed logging on this column
	 * 
	 * @param index
	 *            index
	 * @return true if column is allowed to be logged
	 */
	public boolean isAllowLogging(int index) {
		if (index < 0 || index >= m_columns.length)
			return false;
		return m_columns[index].IsAllowLogging;
	} // isAllowLogging

	/**
	 *  Get Column FieldLength
	 *  @param index index
	 *  @return field length
	 */
	public int getFieldLength (int index)
	{
		if (index < 0 || index >= m_columns.length)
			return 0;
		return m_columns[index].FieldLength;
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
		if (index < 0 || index >= m_columns.length)
			return "RangeError";
		//	Mandatory (i.e. not null
		if (m_columns[index].IsMandatory && value == null)
		{
			return "IsMandatory";
		}
		if (value == null)
			return null;
		
		//	Length ignored

		//
		if (m_columns[index].ValueMin != null)
		{
			BigDecimal value_BD = null;
			try
			{
				if (m_columns[index].ValueMin_BD != null)
					value_BD = new BigDecimal(value.toString());
			}
			catch (Exception ex){}
			//	Both are Numeric
			if (m_columns[index].ValueMin_BD != null && value_BD != null)
			{	//	error: 1 - 0 => 1  -  OK: 1 - 1 => 0 & 1 - 10 => -1
				int comp = m_columns[index].ValueMin_BD.compareTo(value_BD);
				if (comp > 0)
				{
					return "MinValue=" + m_columns[index].ValueMin_BD 
						+ "(" + m_columns[index].ValueMin + ")"
						+ " - compared with Numeric Value=" + value_BD + "(" + value + ")"
						+ " - results in " + comp;
				}
			}
			else	//	String
			{
				int comp = m_columns[index].ValueMin.compareTo(value.toString());
				if (comp > 0)
				{
					return "MinValue=" + m_columns[index].ValueMin
					  + " - compared with String Value=" + value
					  + " - results in " + comp;
				}
			}
		}
		if (m_columns[index].ValueMax != null)
		{
			BigDecimal value_BD = null;
			try
			{
				if (m_columns[index].ValueMax_BD != null)
					value_BD = new BigDecimal(value.toString());
			}
			catch (Exception ex){}
			//	Both are Numeric
			if (m_columns[index].ValueMax_BD != null && value_BD != null)
			{	//	error 12 - 20 => -1  -  OK: 12 - 12 => 0 & 12 - 10 => 1
				int comp = m_columns[index].ValueMax_BD.compareTo(value_BD);
				if (comp < 0)
				{
					return "MaxValue=" + m_columns[index].ValueMax_BD + "(" + m_columns[index].ValueMax + ")"
					  + " - compared with Numeric Value=" + value_BD + "(" + value + ")"
					  + " - results in " + comp;
				}
			}
			else	//	String
			{
				int comp = m_columns[index].ValueMax.compareTo(value.toString());
				if (comp < 0)
				{
					return "MaxValue=" + m_columns[index].ValueMax
					  + " - compared with String Value=" + value
					  + " - results in " + comp;
				}
			}
		}
		return null;
	}   //  validate
	
	public boolean isLazyLoading(int index)
	{
		if (index < 0 || index >= m_columns.length)
			return true;
		return isVirtualColumn(index) && m_columns[index].IsLazyLoading; 
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
		return m_IsChangeLog;
	}

	// metas: pr50_us215
	public boolean isCalculated(int index)
	{
		if (index < 0 || index >= m_columns.length)
			return false;
		return m_columns[index].IsCalculated
				;
	}

	private String m_keyColumnName = null;
	public String getKeyColumnName()
	{
		return m_keyColumnName;
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
		return Integer.parseInt(m_AccessLevel);
	}
	
	private static final CCache<String,POInfo>  s_cacheByTableName = new CCache<String,POInfo>("POInfo_ByTableName", 200);
	private POInfo (Properties ctx, String tableName, boolean baseLanguageOnly, String trxName)
	{
		m_ctx = ctx;
		m_TableName = tableName;
		boolean baseLanguage = baseLanguageOnly ? true : Env.isBaseLanguage(m_ctx, "AD_Table");
		loadInfo (baseLanguage, trxName);
	}   //  PInfo

}   //  POInfo
