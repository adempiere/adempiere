/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.adempiere.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.util.ListElement;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Language;

/**
 * The SortTabController provides a MVC used for 
 * get data for sort tab
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/990">
 * 		@see FR [ 990 ] Sort Tab is not MVC</a>
 */
public abstract class SortTabController {
	
	/**
	 * Overload Constructor
	 * @param windowNo
	 * @param tableId
	 * @param columnSortOrderId
	 * @param columnSortYesNoId
	 */
	public SortTabController(int windowNo, int tableId, int columnSortOrderId, int columnSortYesNoId) {
		this(windowNo, tableId, columnSortOrderId, columnSortYesNoId, 0);
	}
	/**
	 * Standard constructor
	 * @param windowNo
	 * @param tableId
	 * @param columnSortOrderId
	 * @param columnSortYesNoId
	 */
	public SortTabController(int windowNo, int tableId, int columnSortOrderId, int columnSortYesNoId, int parentColumn_ID) {
		this.m_WindowNo = windowNo;
		this.tableId = tableId;
		this.columnSortOrderId = columnSortOrderId;
		this.columnSortYesNoId = columnSortYesNoId;
		this.m_ParentColumn_ID = parentColumn_ID;
		//	
		dynInit();
	}

	/**	Logger			*/
	private CLogger		log = CLogger.getCLogger(getClass());
	private int			tableId;
	private int			columnSortOrderId;
	private int			columnSortYesNoId;
	private String		tableName = null;
	private String		columnSortName= null;
	private String		columnYesNoName = null;
	private String		keyColumnName = null;
	private String		m_IdentifierSql = null;
	private boolean		m_IdentifierTranslated = false;
	private int			m_WindowNo;
	private String		m_ParentColumnName = null;
	private boolean 	isReadWrite = true;
	private int			m_ParentColumn_ID = 0;
	
	
	
	/**
	 * 	Dyanamic Init
	 *  @param AD_Table_ID Table No
	 *  @param AD_ColumnSortOrder_ID Sort Column
	 *  @param AD_ColumnSortYesNo_ID YesNo Column
	 */
	private void dynInit () {
		int identifiersCount = 0;
		StringBuffer identifierSql = new StringBuffer();
		String sql = "SELECT t.TableName, c.AD_Column_ID, c.ColumnName, e.Name,"	//	1..4
			+ "c.IsParent, c.IsKey, c.IsIdentifier, c.IsTranslated "				//	4..8
			+ "FROM AD_Table t, AD_Column c, AD_Element e "
			+ "WHERE t.AD_Table_ID=?"						//	#1
			+ " AND t.AD_Table_ID=c.AD_Table_ID"
			+ " AND (c.AD_Column_ID=? OR AD_Column_ID=?"	//	#2..3
			+ " OR c.IsParent='Y' OR c.IsKey='Y' OR c.IsIdentifier='Y')"
			+ " AND c.AD_Element_ID=e.AD_Element_ID";
		boolean trl = !Env.isBaseLanguage(Env.getCtx(), "AD_Element");
		if (trl)
			sql = "SELECT t.TableName, c.AD_Column_ID, c.ColumnName, et.Name,"	//	1..4
				+ "c.IsParent, c.IsKey, c.IsIdentifier, c.IsTranslated "		//	4..8
				+ "FROM AD_Table t, AD_Column c, AD_Element_Trl et "
				+ "WHERE t.AD_Table_ID=?"						//	#1
				+ " AND t.AD_Table_ID=c.AD_Table_ID"
				+ " AND (c.AD_Column_ID=? OR AD_Column_ID=?"	//	#2..3
				+ "	OR c.IsParent='Y' OR c.IsKey='Y' OR c.IsIdentifier='Y')"
				+ " AND c.AD_Element_ID=et.AD_Element_ID"
				+ " AND et.AD_Language=?";						//	#4
		sql += " ORDER BY c.SeqNo";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, tableId);
			pstmt.setInt(2, columnSortOrderId);
			pstmt.setInt(3, columnSortYesNoId);
			if (trl)
				pstmt.setString(4, Env.getAD_Language(Env.getCtx()));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tableName = rs.getString(1);
				//	Sort Column
				if (columnSortOrderId == rs.getInt(2)) {
					log.fine("Sort=" + rs.getString(1) + "." + rs.getString(3));
					columnSortName = rs.getString(3);
				}
				//	Optional YesNo
				else if (columnSortYesNoId == rs.getInt(2)) {
					log.fine("YesNo=" + rs.getString(1) + "." + rs.getString(3));
					columnYesNoName = rs.getString(3);
				}
				//	Parent2
				else if (rs.getString(5).equals("Y")) {
					log.fine("Parent=" + rs.getString(1) + "." + rs.getString(3));
					m_ParentColumnName = rs.getString(3);
				}
				//	KeyColumn
				else if (rs.getString(6).equals("Y")) {
					log.fine("Key=" + rs.getString(1) + "." + rs.getString(3));
					keyColumnName = rs.getString(3);
				}
				//	Identifier
				else if (rs.getString(7).equals("Y")) {
					log.fine("Identifier=" + rs.getString(1) + "." + rs.getString(3));
					boolean isTranslated = trl && "Y".equals(rs.getString(8));
					if (identifierSql.length() > 0)
						identifierSql.append(",");
					identifierSql.append(getIdentifier(rs.getString(1), rs.getString(3), rs.getInt(2),isTranslated));
					identifiersCount++;
					if (isTranslated)
						m_IdentifierTranslated = true;
				} else {
					log.fine("??NotUsed??=" + rs.getString(1) + "." + rs.getString(3));
				}
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		if (identifiersCount == 0)
			m_IdentifierSql = "NULL";
		else if (identifiersCount == 1)
			m_IdentifierSql = identifierSql.toString();
		else 
			m_IdentifierSql = identifierSql.insert(0, "COALESCE(").append(")").toString();
		
		log.fine(columnSortName);
	}	//	dynInit
	
	/**
	 * Load Data from result set
	 */
	public void loadData() {
		StringBuffer sql = new StringBuffer();
		//	Columns
		sql.append("SELECT t.").append(keyColumnName)				//	1
		.append(",").append(m_IdentifierSql)						//	2
		.append(",t.").append(columnSortName)				//	3
		.append(", t.AD_Client_ID, t.AD_Org_ID");		// 4, 5
		if (columnYesNoName != null)
			sql.append(",t.").append(columnYesNoName);			//	6
		//	Tables
		sql.append(" FROM ").append(tableName).append( " t");
		if (m_IdentifierTranslated)
			sql.append(", ").append(tableName).append("_Trl tt");
		//	Where
		if (m_ParentColumnName ==null 
				&& m_ParentColumn_ID!=0)
			m_ParentColumnName = MColumn.getColumnName(Env.getCtx(), m_ParentColumn_ID);
		
		//FR [ 2826406 ]
		if(m_ParentColumnName != null)
		{
			sql.append(" WHERE t.").append(m_ParentColumnName).append("=?");
		}
		else
		{
			sql.append(" WHERE 1=?");
		}
			
		if (m_IdentifierTranslated)
			sql.append(" AND t.").append(keyColumnName).append("=tt.").append(keyColumnName)
			.append(" AND tt.AD_Language=?");
		//	Order
		sql.append(" ORDER BY ");
		if (columnYesNoName != null)
			sql.append("6 DESC,");		//	t.IsDisplayed DESC
		sql.append("3,2");				//	t.SeqNo, tt.Name 
		//FR [ 2826406 ]
		int ID = 0;		
		if(m_ParentColumnName != null)
		{	
			ID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, m_ParentColumnName);
			log.fine(sql.toString() + " - ID=" + ID);
		} else {
			ID = 1;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, ID);
			
			if (m_IdentifierTranslated)
				pstmt.setString(2, Env.getAD_Language(Env.getCtx()));
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int key = rs.getInt(1);
				String name = rs.getString(2);
				int sequence = rs.getInt(3);
				boolean isYes = sequence != 0;
				int clientId = rs.getInt(4);
				int orgId = rs.getInt(5);
				if (columnYesNoName != null)
					isYes = rs.getString(6).equals("Y");
				//
				ListElement item = new ListElement(key, name, sequence, isYes, clientId, orgId, tableId);
				addItem(item);
				// If the one item from "Yes" list is readonly make entire tab readonly
				if (isYes && !item.isUpdateable()) {
					isReadWrite = false;
				}
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
	}
	
	/**
	 * Save Data from Selection
	 * @return null if is ok
	 */
	public String saveData(List<ListElement> noModel, List<ListElement> yesModel) {
		//	
		log.fine("");
		StringBuffer info = new StringBuffer();
		MTable table = MTable.get(Env.getCtx(), tableId);
		//	noList - Set SortColumn to null and optional YesNo Column to 'N'
		for (ListElement item : noModel) {
			if (!item.isUpdateable())
				continue;
			if(item.getSortNo() == 0 && (columnYesNoName == null || !item.isYes()))
				continue; // no changes
			//
			PO po = table.getPO(item.getKey(), null);
			po.set_ValueOfColumn(columnSortName, 0);
			po.set_ValueOfColumn(columnYesNoName, false);
			
			if (po.save()) {
				item.setSortNo(0);
				item.setIsYes(false);
			} else {
				if (info.length() > 0)
					info.append(", ");
				info.append(item.getName());
				log.log(Level.SEVERE, "NoModel - Not updated: " + keyColumnName + "=" + item.getKey());
			}
		}
		//	yesList - Set SortColumn to value and optional YesNo Column to 'Y'
		int index = 0;
		for (ListElement item : yesModel) {
			if (!item.isUpdateable())
				continue;
			index += 10;
			if(item.getSortNo() == index && (columnYesNoName == null || item.isYes()))
				continue; // no changes
			//

			PO po = table.getPO(item.getKey(), null);
			po.set_ValueOfColumn(columnSortName, index);
			po.set_ValueOfColumn(columnYesNoName, true);
			
			if (po.save()) {
				item.setSortNo(index);
				item.setIsYes(true);
			} else {
				if (info.length() > 0)
					info.append(", ");
				info.append(item.getName());
				log.log(Level.SEVERE, "YesModel - Not updated: " + keyColumnName + "=" + item.getKey());
			}
		}
		//	For error
		if(info.length() > 0) {
			return info.toString();
		}
		//	
		return null;
	}	//	saveData
	
	/**
	 * get Identifier
	 * @param tableName
	 * @param columnName
	 * @param AD_Column_ID
	 * @param isTranslated
	 * @return Sql
	 */
	private String getIdentifier (String tableName, String columnName,Integer AD_Column_ID, boolean isTranslated) {
		Language language = Language.getLanguage(Env
				.getAD_Language(Env.getCtx()));
		StringBuilder sql = new StringBuilder("");
		MColumn column = MColumn.get(Env.getCtx(), AD_Column_ID);
		if(DisplayType.TableDir == column.getAD_Reference_ID() || DisplayType.Search == column.getAD_Reference_ID())
			sql.append("(").append(MLookupFactory.getLookup_TableDirEmbed(language, columnName, "t")).append(")");
		else if (DisplayType.Table == column.getAD_Reference_ID())
			sql.append("(").append(MLookupFactory.getLookup_TableEmbed(language, column.getColumnName(), "t", column.getAD_Reference_Value_ID())).append(")");
		else if(DisplayType.List == column.getAD_Reference_ID())
			sql.append("(").append(MLookupFactory.getLookup_ListEmbed(language, column.getAD_Reference_Value_ID(), columnName)).append(")");
		else 
			sql.append(isTranslated ? "tt." : "t.").append(columnName);
		
		return sql.toString();
	}
	
	/**
	 * Is Read Write
	 * @return
	 */
	public boolean isReadWrite() {
		return isReadWrite;
	}
	
	/**
	 * Add Item to list
	 * @param item
	 */
	public abstract void addItem(ListElement item);
}
