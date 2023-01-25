/****************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                                 *
 * This program is free software; you can redistribute it and/or modify it              *
 * under the terms version 2 or later of the GNU General Public License as published    *
 * by the Free Software Foundation. This program is distributed in the hope             *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied           *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                     *
 * See the GNU General Public License for more details.                                 *
 * You should have received a copy of the GNU General Public License along              *
 * with this program; if not, write to the Free Software Foundation, Inc.,              *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                               *
 * For the text or an alternative of this public license, you may reach us              *
 * Copyright (C) 2003-Present E.R.P. Consultores y Asociados, C.A.                      *
 * All Rights Reserved.                                                                 *
 * Contributor(s): Yamel Senih www.erpcya.com                                           *
 ****************************************************************************************/
package org.compiere.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import org.adempiere.core.domains.models.I_AD_Column;
import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.model.Query;

/**
 * Add for get tables of accesses
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *
 */
public class DependentRecordAccess {
	
	/**
	 * default constructor
	 * @param context
	 */
	public DependentRecordAccess(Properties context) {
		this.context = context;
	}
	private Properties context;
	/**	Map wit tables	*/
	private Map<String, List<KeyNamePair>> referencedColumnsMap = new HashMap<>();
	
	/**
	 * Load tables, this apply when a record access is configured as dependent entities
	 */
	private void loadReferencedTables(String tableName) {
		if(referencedColumnsMap.containsKey(tableName)) {
			return;
		}
		List<KeyNamePair> referencedColumns = new ArrayList<KeyNamePair>();
		String whereClause = "AD_Column.ColumnSQL IS NULL "
				+ "AND EXISTS(SELECT 1 FROM AD_Column c "
				+ "						LEFT JOIN AD_Ref_Table rt ON(rt.AD_Reference_ID = c.AD_Reference_Value_ID) "
				+ "						LEFT JOIN AD_Column rc ON(rc.AD_Column_ID = rt.AD_Key) "
				+ "						LEFT JOIN AD_Table rrt ON(rrt.TableName || '_ID' = c.ColumnName) "
				+ "						LEFT JOIN AD_Column dtc ON(dtc.AD_Table_ID = COALESCE(rrt.AD_Table_ID, rc.AD_Table_ID) AND dtc.ColumnSQL IS NULL) "
				+ "						LEFT JOIN AD_Ref_Table rrrt ON(rrrt.AD_Reference_ID = dtc.AD_Reference_Value_ID) "
				+ "						LEFT JOIN AD_Column rrc ON(rrc.AD_Column_ID = rrrt.AD_Key) "
				+ "						LEFT JOIN AD_Table errt ON(errt.TableName || '_ID' = dtc.ColumnName) "
				+ "						WHERE c.AD_Column_ID = AD_Column.AD_Column_ID "
				+ "						AND (errt.TableName = ? OR errt.AD_Table_ID = ?)"
				+ ") "
				+ "AND EXISTS(SELECT 1 FROM AD_Table t "
				+ "						WHERE t.AD_Table_ID = AD_Column.AD_Table_ID"
				+ "						AND t.TableName || '_ID' <> AD_Column.ColumnName"
				+ "						AND ? || '_ID' <> AD_Column.ColumnName)";
		new Query(context, I_AD_Column.Table_Name, whereClause, null)
		.setParameters(tableName, MTable.getTable_ID(tableName), tableName)
			.getIDsAsList().forEach(columnId -> {
				Optional.ofNullable(MColumn.get(context, columnId)).ifPresent(column -> {
					referencedColumns.add(new KeyNamePair(column.getAD_Column_ID(), MTable.getTableName(context, column.getAD_Table_ID())));
				});
			});
		//	Put values
		referencedColumnsMap.put(tableName, referencedColumns);
	}
	
	/**
	 * Validate if a table match with dependents
	 * @param accessTableName
	 * @param tableName
	 * @return
	 */
	public boolean isMatchedForTable(String accessTableName, String tableName) {
		loadReferencedTables(accessTableName);
		if(!referencedColumnsMap.containsKey(accessTableName)) {
			return false;
		}
		return referencedColumnsMap.get(accessTableName)
				.stream()
				.filter(column -> column.getName().equals(tableName))
				.findFirst()
				.isPresent();
	}
	
	/**
	 * Get Column Ids
	 * @param accessTableName
	 * @param tableName
	 * @return
	 */
	public List<Integer> getColumnIds(String accessTableName, String tableName) {
		loadReferencedTables(accessTableName);
		if(!referencedColumnsMap.containsKey(accessTableName)) {
			return null;
		}
		return referencedColumnsMap.get(accessTableName)
				.stream()
				.filter(column -> column.getName().equals(tableName))
				.map(column -> column.getKey())
				.collect(Collectors.toList());
	}
}
