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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CCache;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Language;

/**
 *  Create MLookups
 *
 *  @author Jorg Janke
 *  @version  $Id: MLookupFactory.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *		<li>BF [ 1734394 ] MLookupFactory.getLookup_TableDirEmbed is not translated
 *		<li>BF [ 1714261 ] MLookupFactory: TableDirEmbed -> TableEmbed not supported
 *		<li>BF [ 1672820 ] Sorting should be language-sensitive
 *		<li>BF [ 1739530 ] getLookup_TableDirEmbed error when BaseColumn is sql query
 *		<li>BF [ 1739544 ] getLookup_TableEmbed error for self referencing references
 *		<li>BF [ 1817768 ] Isolate hardcoded table direct columns
 * @author Carlos Ruiz, GlobalQSS
 *		<li>BF [ 2561593 ] Multi-tenant problem with webui
 */
public class MLookupFactory
{
	/**	Logging								*/
	private static CLogger		s_log = CLogger.getCLogger(MLookupFactory.class);
	/** Table Reference Cache				*/
	private static CCache<String,MLookupInfo> s_cacheRefTable = new CCache<String,MLookupInfo>("AD_Ref_Table", 30, 60);	//	1h


	/**
	 *  Create MLookup
	 *
	 *  @param ctx context for access
	 *  @param WindowNo window no
	 * 	@param AD_Reference_ID display type
	 *  @param Column_ID AD_Column_ID or AD_Process_Para_ID
	 *  @param language report language
	 * 	@param ColumnName key column name
	 * 	@param AD_Reference_Value_ID AD_Reference (List, Table)
	 * 	@param IsParent parent (prevents query to directly access value)
	 * 	@param ValidationCode optional SQL validation
	 *  @throws Exception if Lookup could not be created
	 *  @return MLookup
	 */
	public static MLookup get (Properties ctx, int WindowNo, int Column_ID, int AD_Reference_ID,
			Language language, String ColumnName, int AD_Reference_Value_ID,
			boolean IsParent, String ValidationCode)
		throws Exception
	{
		MLookupInfo info = getLookupInfo (ctx, WindowNo, Column_ID, AD_Reference_ID,
			language, ColumnName, AD_Reference_Value_ID, IsParent, ValidationCode);
		if (info == null)
			throw new Exception ("MLookup.create - no LookupInfo");
		return new MLookup(info, 0);
	}   //  create

	public static MLookupInfo getLookupInfo(Properties ctx, int WindowNo, int Column_ID, int AD_Reference_ID)
	{
		String ColumnName = "";
		int AD_Reference_Value_ID = 0;
		boolean IsParent = false;
		String ValidationCode = "";
		//
		String sql = "SELECT c.ColumnName, c.AD_Reference_Value_ID, c.IsParent, vr.Code "
			+ "FROM AD_Column c"
			+ " LEFT OUTER JOIN AD_Val_Rule vr ON (c.AD_Val_Rule_ID=vr.AD_Val_Rule_ID) "
			+ "WHERE c.AD_Column_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Column_ID);
			//
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				ColumnName = rs.getString(1);
				AD_Reference_Value_ID = rs.getInt(2);
				IsParent = "Y".equals(rs.getString(3));
				ValidationCode = rs.getString(4);
			}
			else
				s_log.log(Level.SEVERE, "Column Not Found - AD_Column_ID=" + Column_ID);
		}
		catch (SQLException ex)
		{
			s_log.log(Level.SEVERE, "create", ex);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//
		MLookupInfo info = getLookupInfo (ctx, WindowNo, Column_ID, AD_Reference_ID,
			Env.getLanguage(ctx), ColumnName, AD_Reference_Value_ID, IsParent, ValidationCode);

		return info;
	}

	/**
	 *  Create MLookup
	 *
	 *  @param ctx context for access
	 *  @param WindowNo window no
	 * 	@param TabNo TabNo
	 *  @param Column_ID AD_Column_ID or AD_Process_Para_ID
	 * 	@param AD_Reference_ID display type
	 *  @return MLookup
	 */
	public static MLookup get (Properties ctx, int WindowNo, int TabNo, int Column_ID, int AD_Reference_ID)
	{
		//
		MLookupInfo info = getLookupInfo (ctx, WindowNo, Column_ID, AD_Reference_ID);
		return new MLookup(info, TabNo);
	}   //  get


	/**************************************************************************
	 *  Get Information for Lookups based on Column_ID for Table Columns or Process Parameters.
	 *
	 *	The SQL returns three columns:
	 *  <pre>
	 *		Key, Value, Name, IsActive	(where either key or value is null)
	 *  </pre>
	 *  @param ctx context for access
	 *  @param language report language
	 *  @param WindowNo window no
	 *  @param Column_ID AD_Column_ID or AD_Process_Para_ID
	 * 	@param ColumnName key column name
	 * 	@param AD_Reference_ID display type
	 * 	@param AD_Reference_Value_ID AD_Reference (List, Table)
	 * 	@param IsParent parent (prevents query to directly access value)
	 * 	@param ValidationCode optional SQL validation
	 *  @return lookup info structure
	 */
	static public MLookupInfo getLookupInfo (Properties ctx, int WindowNo,
		int Column_ID, int AD_Reference_ID,
		Language language, String ColumnName, int AD_Reference_Value_ID,
		boolean IsParent, String ValidationCode)
	{
		MLookupInfo info = null;
		boolean needToAddSecurity = true;
		//	List
		if (AD_Reference_ID == DisplayType.List)	//	17
		{
			info = getLookup_List(language, AD_Reference_Value_ID);
			needToAddSecurity = false;
		}
		//	Table or Search with Reference_Value
		else if ((AD_Reference_ID == DisplayType.Table || AD_Reference_ID == DisplayType.Search)
			&& AD_Reference_Value_ID != 0)
		{
			info = getLookup_Table (ctx, language, WindowNo, AD_Reference_Value_ID);
		}
		//	TableDir, Search, ID, ...
		else
		{
			info = getLookup_TableDir (ctx, language, WindowNo, ColumnName);
		}
		//  do we have basic info?
		if (info == null)
		{
			s_log.severe ("No SQL - " + ColumnName);
			return null;
		}
		//	remaining values
		info.ctx = ctx;
		info.WindowNo = WindowNo;
		info.Column_ID = Column_ID;
		info.DisplayType = AD_Reference_ID;
		info.AD_Reference_Value_ID = AD_Reference_Value_ID;
		info.IsParent = IsParent;
		info.ValidationCode = ValidationCode;
		if (info.ValidationCode == null)
			info.ValidationCode = "";

		//	Variables in SQL WHERE
		if (info.Query.indexOf('@') != -1)
		{
		//	String newSQL = Env.parseContext(ctx, WindowNo, info.Query, false);
			String newSQL = Env.parseContext(ctx, 0, info.Query, false);	//	only global
			if (newSQL.length() == 0)
			{
				s_log.severe ("SQL parse error: " + info.Query);
				return null;
			}
			info.Query = newSQL;
			s_log.fine("getLookupInfo, newSQL ="+newSQL); //jz
		}

		//	Direct Query - NO Validation/Security
		int posOrder = info.Query.lastIndexOf(" ORDER BY ");
		boolean hasWhere = info.Query.lastIndexOf(" WHERE ") != -1;
		if (hasWhere)	//	might be for a select sub-query
		{
			//	SELECT (SELECT .. FROM .. WHERE ..) FROM ..
			//	SELECT .. FROM .. WHERE EXISTS (SELECT .. FROM .. WHERE ..)
			AccessSqlParser asp = new AccessSqlParser(info.Query);
			String mainQuery = asp.getMainSql();
			hasWhere = mainQuery.indexOf(" WHERE ") != -1;
		}
		if (posOrder == -1)
			info.QueryDirect = info.Query
				+ (hasWhere ? " AND " : " WHERE ") + info.KeyColumn + "=?";
		else
			info.QueryDirect = info.Query.substring(0, posOrder)
				+ (hasWhere ? " AND " : " WHERE ") + info.KeyColumn + "=?";

		//	Validation
		//String local_validationCode = "";
		if (info.ValidationCode.length() == 0)
			info.IsValidated = true;

		else
		{
			/*
			local_validationCode = Env.parseContext (ctx, WindowNo, info.ValidationCode, true);
			if (local_validationCode.length() == 0)   //  returns "" if not all variables were parsed
				info.IsValidated = false;
			else
				info.IsValidated = true;
			*/
			info.IsValidated = false;
		}
		/*
		//	Add Local Validation
		if (local_validationCode.length() != 0)
		{
			//jz handle no posOrder case
			if (posOrder > 0)
				info.Query = info.Query.substring(0, posOrder)
					+ (hasWhere ? " AND " : " WHERE ") + local_validationCode
					+ info.Query.substring(posOrder);
			else
				info.Query = info.Query
				+ (hasWhere ? " AND " : " WHERE ") + local_validationCode;
		}*/

		//	Add Security
		if (needToAddSecurity)
			info.Query = MRole.getDefault(ctx, false).addAccessSQL(info.Query,
				info.TableName, MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		//
	//	s_log.finest("Query:  " + info.Query);
	//	s_log.finest("Direct: " + info.QueryDirect);
		return info;
	}	//	createLookupInfo


	/**************************************************************************
	 *	Get Lookup SQL for Lists
	 *  @param language report language
	 *  @param AD_Reference_Value_ID reference value
	 *	@return SELECT NULL, Value, Name, IsActive FROM AD_Ref_List
	 */
	static public MLookupInfo getLookup_List(Language language, int AD_Reference_Value_ID)
	{
		String byValue = DB.getSQLValueString(null, "SELECT IsOrderByValue FROM AD_Reference WHERE AD_Reference_ID = ? ", AD_Reference_Value_ID);
		StringBuffer realSQL = new StringBuffer ("SELECT NULL, AD_Ref_List.Value,");
		if (Env.isBaseLanguage(language, "AD_Ref_List"))
			realSQL.append("AD_Ref_List.Name,AD_Ref_List.IsActive FROM AD_Ref_List");
		else
			realSQL.append("trl.Name, AD_Ref_List.IsActive "
				+ "FROM AD_Ref_List INNER JOIN AD_Ref_List_Trl trl "
				+ " ON (AD_Ref_List.AD_Ref_List_ID=trl.AD_Ref_List_ID AND trl.AD_Language='")
					.append(language.getAD_Language()).append("')");
		realSQL.append(" WHERE AD_Ref_List.AD_Reference_ID=").append(AD_Reference_Value_ID);
		if ("Y".equals(byValue))
			realSQL.append(" ORDER BY 2");
		else
			realSQL.append(" ORDER BY 3"); // sort by name/translated name - teo_sarca, [ 1672820 ]
		//
		return new MLookupInfo(realSQL.toString(), "AD_Ref_List", "AD_Ref_List.Value",
			101,101, MQuery.getEqualQuery("AD_Reference_ID", AD_Reference_Value_ID));	//	Zoom Window+Query
	}	//	getLookup_List

	/**
	 * Get Lookup SQL for List
	 * @param language report Language
	 * @param AD_Reference_Value_ID reference value
	 * @param linkColumnName link column name
	 * @return SELECT Name FROM AD_Ref_List WHERE AD_Reference_ID=x AND Value=linkColumn
	 */
	static public String getLookup_ListEmbed(Language language,
		int AD_Reference_Value_ID, String linkColumnName)
	{
		StringBuffer realSQL = new StringBuffer ("SELECT ");
		if (Env.isBaseLanguage(language, "AD_Ref_List"))
			realSQL.append("AD_Ref_List.Name FROM AD_Ref_List");
		else
			realSQL.append("trl.Name "
				+ "FROM AD_Ref_List INNER JOIN AD_Ref_List_Trl trl "
				+ " ON (AD_Ref_List.AD_Ref_List_ID=trl.AD_Ref_List_ID AND trl.AD_Language='")
					.append(language.getAD_Language()).append("')");
		realSQL.append(" WHERE AD_Ref_List.AD_Reference_ID=").append(AD_Reference_Value_ID)
			.append(" AND AD_Ref_List.Value=").append(linkColumnName);

		//
		return realSQL.toString();
	}	//	getLookup_ListEmbed

	/***************************************************************************
	 *	Get Lookup SQL for Table Lookup
	 *  @param ctx context for access and dynamic access
	 *  @param language report language
	 *  @param WindowNo window no
	 *  @param AD_Reference_Value_ID reference value
	 *	@return	SELECT Key, NULL, Name, IsActive FROM Table - if KeyColumn end with _ID
	 *	  otherwise	SELECT NULL, Key, Name, IsActive FROM Table
	 */
	static private MLookupInfo getLookup_Table (Properties ctx, Language language,
		int WindowNo, int AD_Reference_Value_ID)
	{
		//	Try cache - assume no language change
		String key = Env.getAD_Client_ID(ctx) + "|" + String.valueOf(AD_Reference_Value_ID);
		MLookupInfo retValue = (MLookupInfo)s_cacheRefTable.get(key);
		if (retValue != null)
		{
			s_log.finest("Cache: " + retValue);
			return retValue.cloneIt();
		}
		//
		String sql0 = "SELECT t.TableName,ck.ColumnName AS KeyColumn,"				//	1..2
			+ "cd.ColumnName AS DisplayColumn,rt.IsValueDisplayed,cd.IsTranslated,"	//	3..5
			+ "rt.WhereClause,rt.OrderByClause,t.AD_Window_ID,t.PO_Window_ID, "		//	6..9
			+ "t.AD_Table_ID, cd.ColumnSQL as DisplayColumnSQL, "					//	10..11
			+ "rt.AD_Window_ID as RT_AD_Window_ID " // 12
			+ "FROM AD_Ref_Table rt"
			+ " INNER JOIN AD_Table t ON (rt.AD_Table_ID=t.AD_Table_ID)"
			+ " INNER JOIN AD_Column ck ON (rt.AD_Key=ck.AD_Column_ID)"
			+ " INNER JOIN AD_Column cd ON (rt.AD_Display=cd.AD_Column_ID) "
			+ "WHERE rt.AD_Reference_ID=?"
			+ " AND rt.IsActive='Y' AND t.IsActive='Y'";
		//
		String	KeyColumn = null, DisplayColumn = null, TableName = null, WhereClause = null, OrderByClause = null;
		String displayColumnSQL = null;
		boolean IsTranslated = false, isValueDisplayed = false;
		//boolean isSOTrx = !"N".equals(Env.getContext(ctx, WindowNo, "IsSOTrx"));
		int ZoomWindow = 0;
		int ZoomWindowPO = 0;
		int overrideZoomWindow = 0;
		//int AD_Table_ID = 0;
		boolean loaded = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql0, null);
			pstmt.setInt(1, AD_Reference_Value_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				TableName = rs.getString(1);
				KeyColumn = rs.getString(2);
				DisplayColumn = rs.getString(3);
				isValueDisplayed = "Y".equals(rs.getString(4));
				IsTranslated = "Y".equals(rs.getString(5));
				WhereClause = rs.getString(6);
				OrderByClause = rs.getString(7);
				ZoomWindow = rs.getInt(8);
				ZoomWindowPO = rs.getInt(9);
				//AD_Table_ID = rs.getInt(10);
				displayColumnSQL = rs.getString(11);
				overrideZoomWindow = rs.getInt(12);
				loaded = true;
			}
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql0, e);
			return null;
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		if (!loaded)
		{
			s_log.log(Level.SEVERE, "No Table Reference Table ID=" + AD_Reference_Value_ID);
			return null;
		}

		StringBuffer realSQL = new StringBuffer("SELECT ");
		if (!KeyColumn.endsWith("_ID"))
			realSQL.append("NULL,");

		//	Translated
		if (IsTranslated && !Env.isBaseLanguage(language, TableName))
		{
			realSQL.append(TableName).append(".").append(KeyColumn).append(",");
			if (KeyColumn.endsWith("_ID"))
				realSQL.append("NULL,");
			if (isValueDisplayed)
				realSQL.append("NVL(").append(TableName).append(".Value,'-1') || '-' || ");
			if (displayColumnSQL != null && displayColumnSQL.trim().length() > 0)
				realSQL.append("NVL(").append(displayColumnSQL).append(",'-1')");
			else
				realSQL.append("NVL(").append(TableName).append("_Trl.").append(DisplayColumn).append(",'-1')");
			realSQL.append(",").append(TableName).append(".IsActive");
			realSQL.append(" FROM ").append(TableName)
				.append(" INNER JOIN ").append(TableName).append("_TRL ON (")
				.append(TableName).append(".").append(KeyColumn)
				.append("=").append(TableName).append("_Trl.").append(KeyColumn)
				.append(" AND ").append(TableName).append("_Trl.AD_Language='")
				.append(language.getAD_Language()).append("')");
		}
		//	Not Translated
		else
		{
			realSQL.append(TableName).append(".").append(KeyColumn).append(",");
			if (KeyColumn.endsWith("_ID"))
				realSQL.append("NULL,");
			if (isValueDisplayed)
				realSQL.append("NVL(").append(TableName).append(".Value,'-1') || '-' || ");
			if (displayColumnSQL != null && displayColumnSQL.trim().length() > 0)
				realSQL.append("NVL(").append(displayColumnSQL).append(",'-1')");
			else
				realSQL.append("NVL(").append(TableName).append(".").append(DisplayColumn).append(",'-1')");
			realSQL.append(",").append(TableName).append(".IsActive");
			realSQL.append(" FROM ").append(TableName);
		}

		//	add WHERE clause
		MQuery zoomQuery = null;
		if (WhereClause != null && WhereClause.length() > 0)
		{
			String where = WhereClause;
			if (where.indexOf('@') != -1)
				where = Env.parseContext(ctx, WindowNo, where, false);
			if (where.length() == 0 && WhereClause.length() != 0)
				s_log.severe ("Could not resolve: " + WhereClause);

			//	We have no context
			if (where.length() != 0)
			{
				realSQL.append(" WHERE ").append(where);
				if (where.indexOf('.') == -1)
					s_log.log(Level.SEVERE, "getLookup_Table - " + TableName
						+ ": WHERE should be fully qualified: " + WhereClause);
				zoomQuery = new MQuery (TableName);
				zoomQuery.addRestriction(where);
			}
		}

		//	Order By qualified term or by Name
		if (OrderByClause != null && OrderByClause.length() > 0  )
		{
			realSQL.append(" ORDER BY ").append(OrderByClause);
			if (OrderByClause.indexOf('.') == -1)
				s_log.log(Level.SEVERE, "getLookup_Table - " + TableName
					+ ": ORDER BY must fully qualified: " + OrderByClause);
		}
		else
			realSQL.append(" ORDER BY 3");

		s_log.finest("AD_Reference_Value_ID=" + AD_Reference_Value_ID + " - " + realSQL);
		if (overrideZoomWindow > 0)
		{
			ZoomWindow = overrideZoomWindow;
			ZoomWindowPO = 0;
		}
		retValue = new MLookupInfo (realSQL.toString(), TableName,
			TableName + "." + KeyColumn, ZoomWindow, ZoomWindowPO, zoomQuery);
		s_cacheRefTable.put(key, retValue.cloneIt());
		return retValue;
	}	//	getLookup_Table

	/**
	 *	Get Embedded Lookup SQL for Table Lookup
	 *  @param language report language
	 * 	@param BaseColumn base column name
	 * 	@param BaseTable base table name
	 *  @param AD_Reference_Value_ID reference value
	 *	@return	SELECT Name FROM Table
	 */
	static public String getLookup_TableEmbed (Language language,
		String BaseColumn, String BaseTable, int AD_Reference_Value_ID)
	{
		String sql = "SELECT t.TableName,ck.ColumnName AS KeyColumn,"
			+ "cd.ColumnName AS DisplayColumn,rt.isValueDisplayed,cd.IsTranslated "
			+ "FROM AD_Ref_Table rt"
			+ " INNER JOIN AD_Table t ON (rt.AD_Table_ID=t.AD_Table_ID)"
			+ " INNER JOIN AD_Column ck ON (rt.AD_Key=ck.AD_Column_ID)"
			+ " INNER JOIN AD_Column cd ON (rt.AD_Display=cd.AD_Column_ID) "
			+ "WHERE rt.AD_Reference_ID=?"
			+ " AND rt.IsActive='Y' AND t.IsActive='Y'";
		//
		String	KeyColumn, DisplayColumn, TableName, TableNameAlias;
		boolean IsTranslated, isValueDisplayed;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Reference_Value_ID);
			rs = pstmt.executeQuery();
			if (!rs.next())
			{
				s_log.log(Level.SEVERE, "Cannot find Reference Table, ID=" + AD_Reference_Value_ID
					+ ", Base=" + BaseTable + "." + BaseColumn);
				return null;
			}

			TableName = rs.getString(1);
			KeyColumn = rs.getString(2);
			DisplayColumn = rs.getString(3);
			isValueDisplayed = rs.getString(4).equals("Y");
			IsTranslated = rs.getString(5).equals("Y");

		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
			return null;
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		// If it's self referencing then use other alias - teo_sarca [ 1739544 ]
		if (TableName.equals(BaseTable)) {
			TableNameAlias = TableName + "1";
		}
		else {
			TableNameAlias = TableName;
		}

		StringBuffer embedSQL = new StringBuffer("SELECT ");

		//	Translated
		if (IsTranslated && !Env.isBaseLanguage(language, TableName))
		{
			if (isValueDisplayed)
				embedSQL.append(TableNameAlias).append(".Value||'-'||");
			embedSQL.append(TableName).append("_Trl.").append(DisplayColumn);
			//
			embedSQL.append(" FROM ").append(TableName).append(" ").append(TableNameAlias)
				.append(" INNER JOIN ").append(TableName).append("_TRL ON (")
				.append(TableNameAlias).append(".").append(KeyColumn)
				.append("=").append(TableName).append("_Trl.").append(KeyColumn)
				.append(" AND ").append(TableName).append("_Trl.AD_Language='")
				.append(language.getAD_Language()).append("')");
		}
		//	Not Translated
		else
		{
			if (isValueDisplayed)
				embedSQL.append(TableNameAlias).append(".Value||'-'||");
			embedSQL.append(TableNameAlias).append(".").append(DisplayColumn);
			//
			embedSQL.append(" FROM ").append(TableName).append(" ").append(TableNameAlias);
		}

		embedSQL.append(" WHERE ").append(BaseTable).append(".").append(BaseColumn);
		embedSQL.append("=").append(TableNameAlias).append(".").append(KeyColumn);

		return embedSQL.toString();
	}	//	getLookup_TableEmbed


	/**************************************************************************
	 * Get Lookup SQL for direct Table Lookup
	 * @param ctx context for access
	 * @param language report language
	 * @param ColumnName column name
	 * @param WindowNo Window (for SOTrx)
	 * @return SELECT Key, NULL, Name, IsActive from Table (fully qualified)
	 */
	static private MLookupInfo getLookup_TableDir (Properties ctx, Language language,
		int WindowNo, String ColumnName)
	{
		if (!ColumnName.endsWith("_ID"))
		{
			s_log.log(Level.SEVERE, "Key does not end with '_ID': " + ColumnName);
			return null;
		}

		String KeyColumn = MQuery.getZoomColumnName(ColumnName);
		String TableName = MQuery.getZoomTableName(ColumnName);
		//boolean isSOTrx = !"N".equals(Env.getContext(ctx, WindowNo, "IsSOTrx"));
		int ZoomWindow = 0;
		int ZoomWindowPO = 0;

		//try cache
		String cacheKey = Env.getAD_Client_ID(ctx) + "|" + TableName + "." + KeyColumn;
		if (s_cacheRefTable.containsKey(cacheKey))
			return s_cacheRefTable.get(cacheKey).cloneIt();

		//	get display column names
		String sql0 = "SELECT c.ColumnName,c.IsTranslated,c.AD_Reference_ID,"
			+ "c.AD_Reference_Value_ID,t.AD_Window_ID,t.PO_Window_ID "
			+ "FROM AD_Table t"
			+ " INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID) "
			+ "WHERE TableName=?"
			+ " AND c.IsIdentifier='Y' "
			+ "ORDER BY c.SeqNo";
		//
		ArrayList<LookupDisplayColumn> list = new ArrayList<LookupDisplayColumn>();
		boolean isTranslated = false;
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql0, null);
			pstmt.setString(1, TableName);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				LookupDisplayColumn ldc = new LookupDisplayColumn (rs.getString(1),
					"Y".equals(rs.getString(2)), rs.getInt(3), rs.getInt(4));
				list.add (ldc);
			//	s_log.fine("getLookup_TableDir: " + ColumnName + " - " + ldc);
				//
				if (!isTranslated && ldc.IsTranslated)
					isTranslated = true;
				ZoomWindow = rs.getInt(5);
				ZoomWindowPO = rs.getInt(6);
			}
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql0, e);
			return null;
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		//  Do we have columns ?
		if (list.size() == 0)
		{
			s_log.log(Level.SEVERE, "No Identifier records found: " + ColumnName);
			return null;
		}

		StringBuffer realSQL = new StringBuffer("SELECT ");
		realSQL.append(TableName).append(".").append(KeyColumn).append(",NULL,");

		StringBuffer displayColumn = new StringBuffer();
		int size = list.size();
		//  Get Display Column
		for (int i = 0; i < size; i++)
		{
			if (i > 0)
				displayColumn.append(" ||'_'|| " );
			LookupDisplayColumn ldc = (LookupDisplayColumn)list.get(i);

			displayColumn.append("NVL(");

			//  translated
			if (ldc.IsTranslated && !Env.isBaseLanguage(language, TableName))
				displayColumn.append(TableName).append("_Trl.").append(ldc.ColumnName);
			//  date
			else if (DisplayType.isDate(ldc.DisplayType))
			{
				displayColumn.append(DB.TO_CHAR(TableName + "." + ldc.ColumnName, ldc.DisplayType, language.getAD_Language()));
			}
			//  TableDir
			else if ((ldc.DisplayType == DisplayType.TableDir || ldc.DisplayType == DisplayType.Search)
				&& ldc.ColumnName.endsWith("_ID"))
			{
				String embeddedSQL = getLookup_TableDirEmbed(language, ldc.ColumnName, TableName);
				if (embeddedSQL != null)
					displayColumn.append("(").append(embeddedSQL).append(")");
			}
			//	Table
			else if (ldc.DisplayType == DisplayType.Table && ldc.AD_Reference_ID != 0)
			{
				String embeddedSQL = getLookup_TableEmbed (language, ldc.ColumnName, TableName, ldc.AD_Reference_ID);
				if (embeddedSQL != null)
					displayColumn.append("(").append(embeddedSQL).append(")");
			}
			//  number
			else if (DisplayType.isNumeric(ldc.DisplayType))
			{
				displayColumn.append(DB.TO_CHAR(TableName + "." + ldc.ColumnName, ldc.DisplayType, language.getAD_Language()));
			}
			//  String
			else
				displayColumn.append(TableName).append(".").append(ldc.ColumnName);

			displayColumn.append(",'-1')");

		}
		realSQL.append(displayColumn.toString());
		realSQL.append(",").append(TableName).append(".IsActive");

		//  Translation
		if (isTranslated && !Env.isBaseLanguage(language, TableName))
		{
			realSQL.append(" FROM ").append(TableName)
				.append(" INNER JOIN ").append(TableName).append("_TRL ON (")
				.append(TableName).append(".").append(KeyColumn)
				.append("=").append(TableName).append("_Trl.").append(KeyColumn)
				.append(" AND ").append(TableName).append("_Trl.AD_Language='")
				.append(language.getAD_Language()).append("')");
		}
		else	//	no translation
		{
			realSQL.append(" FROM ").append(TableName);
		}

		//	Order by Display
		realSQL.append(" ORDER BY 3");
		MQuery zoomQuery = null;	//	corrected in VLookup

		if (CLogMgt.isLevelFinest())
			s_log.fine("ColumnName=" + ColumnName + " - " + realSQL);
		MLookupInfo lInfo = new MLookupInfo(realSQL.toString(), TableName,
			TableName + "." + KeyColumn, ZoomWindow, ZoomWindowPO, zoomQuery);
		s_cacheRefTable.put(cacheKey, lInfo.cloneIt());
		return lInfo;
	}	//	getLookup_TableDir


	/**
	 *  Get embedded SQL for TableDir Lookup
	 *
	 *  @param language report language
	 *  @param ColumnName column name
	 *  @param BaseTable base table
	 *  @return SELECT Column FROM TableName WHERE BaseTable.ColumnName=TableName.ColumnName
	 *  @see #getLookup_TableDirEmbed(Language, String, String, String)
	 */
	static public String getLookup_TableDirEmbed (Language language, String ColumnName, String BaseTable)
	{
		return getLookup_TableDirEmbed (language, ColumnName, BaseTable, ColumnName);
	}   //  getLookup_TableDirEmbed

	/**
	 *  Get embedded SQL for TableDir Lookup
	 *
	 *  @param language report language
	 *  @param ColumnName column name
	 *  @param BaseTable base table
	 *  @param BaseColumn base column
	 *  @return SELECT Column FROM TableName WHERE BaseTable.BaseColumn=TableName.ColumnName
	 */
	static public String getLookup_TableDirEmbed (Language language,
		String ColumnName, String BaseTable, String BaseColumn)
	{
		String KeyColumn = MQuery.getZoomColumnName(ColumnName);
		String TableName = MQuery.getZoomTableName(ColumnName);

		//	get display column name (first identifier column)
		String sql = "SELECT c.ColumnName,c.IsTranslated,c.AD_Reference_ID,c.AD_Reference_Value_ID "
			+ "FROM AD_Table t INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID) "
			+ "WHERE TableName=?"
			+ " AND c.IsIdentifier='Y' "
			+ "ORDER BY c.SeqNo";
		//
		ArrayList<LookupDisplayColumn> list = new ArrayList<LookupDisplayColumn>();
		boolean isTranslated = false;
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, TableName);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				LookupDisplayColumn ldc = new LookupDisplayColumn (rs.getString(1),
					"Y".equals(rs.getString(2)), rs.getInt(3), rs.getInt(4));
				list.add (ldc);
			//	s_log.fine("getLookup_TableDirEmbed: " + ColumnName + " - " + ldc);
				//
				if (!isTranslated && ldc.IsTranslated)
					isTranslated = true;
			}
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
			return "";
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		//  Do we have columns ?
		if (list.size() == 0)
		{
			s_log.log(Level.SEVERE, "No Identifier records found: " + ColumnName);
			return "";
		}

		//
		StringBuffer embedSQL = new StringBuffer("SELECT ");

		int size = list.size();
		for (int i = 0; i < size; i++)
		{
			if (i > 0)
				embedSQL.append("||' - '||" );
			LookupDisplayColumn ldc = (LookupDisplayColumn)list.get(i);

			//  translated
			if (ldc.IsTranslated && !Env.isBaseLanguage(language, TableName))
				embedSQL.append(TableName).append("_Trl.").append(ldc.ColumnName);
			//  date, number
			else if (DisplayType.isDate(ldc.DisplayType) || DisplayType.isNumeric(ldc.DisplayType))
			{
				embedSQL.append("COALESCE(" + DB.TO_CHAR(TableName + "." + ldc.ColumnName, ldc.DisplayType, language.getAD_Language()) + ",'')");
			}
			//  TableDir
			else if ((ldc.DisplayType == DisplayType.TableDir || ldc.DisplayType == DisplayType.Search)
			  && ldc.ColumnName.endsWith("_ID"))
			{
				String embeddedSQL = getLookup_TableDirEmbed(language, ldc.ColumnName, TableName);
				embedSQL.append("COALESCE((").append(embeddedSQL).append("),'')");
			}
			//	Table - teo_sarca [ 1714261 ]
			else if (ldc.DisplayType == DisplayType.Table && ldc.AD_Reference_ID != 0)
			{
				String embeddedSQL = getLookup_TableEmbed (language, ldc.ColumnName, TableName, ldc.AD_Reference_ID);
				embedSQL.append("COALESCE((").append(embeddedSQL).append("),'')");
			}
			//  String
			else
				embedSQL.append("COALESCE(").append(TableName).append(".").append(ldc.ColumnName).append(",'')");
		}

		embedSQL.append(" FROM ").append(TableName);
		//  Translation
		if (isTranslated && !Env.isBaseLanguage(language, TableName))
		{
			embedSQL.append(" INNER JOIN ").append(TableName).append("_TRL ON (")
				.append(TableName).append(".").append(KeyColumn)
				.append("=").append(TableName).append("_Trl.").append(KeyColumn)
				.append(" AND ").append(TableName).append("_Trl.AD_Language=")
				.append(DB.TO_STRING(language.getAD_Language())).append(")");
		}
		embedSQL.append(" WHERE ");
		// If is not virtual column - teo_sarca [ 1739530 ]
		if (! BaseColumn.trim().startsWith("(")) {
			embedSQL.append(BaseTable).append(".").append(BaseColumn);
		}
		else {
			embedSQL.append(BaseColumn);
		}
		embedSQL.append("=").append(TableName).append(".").append(ColumnName);
		//
		return embedSQL.toString();
	}	//  getLookup_TableDirEmbed

}   //  MLookupFactory

