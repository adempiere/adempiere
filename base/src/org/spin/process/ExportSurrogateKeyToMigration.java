/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.spin.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.adempiere.core.domains.models.I_AD_MigrationStep;
import org.adempiere.core.domains.models.I_AD_Table;
import org.compiere.model.MColumn;
import org.compiere.model.MMigration;
import org.compiere.model.MMigrationStep;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;

/** Generated Process for (Export Surrogate Key)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public class ExportSurrogateKeyToMigration extends ExportSurrogateKeyToMigrationAbstract {

	/**	Update List	*/
	private List<String> updateList = new ArrayList<>();
	/**	Roll back List	*/
	private List<String> rollbackList = new ArrayList<>();
	/**	Current Migration */
	private MMigration migration = null;
	/**	Sequence	*/
	private int sequence = 10;
	/**	Generated	*/
	private long generated = 0;
	/**	Entity Type Where clause	*/
	private String entityTypeWhereClause = "";
	
	@Override
	protected String doIt() throws Exception {
		List<MTable> tableList = getTableList(get_TrxName());
		//	Add columns
		tableList.stream().filter(table -> table != null)
			.filter(table -> !table.getTableName().endsWith("_Trl"))
			.filter(table -> table.getColumn(I_AD_Table.COLUMNNAME_UUID) != null)
			.filter(table -> table.getColumn(I_AD_Table.COLUMNNAME_AD_Client_ID) != null)
			.filter(table -> !table.isIgnoreMigration())
			.forEach(table -> {
			addTableToUpdate(table.getTableName());
		});
		if(migration != null) {
			return "@AD_Migration_ID@: " + migration.getSeqNo() + " - " + migration.getName() + " (" + generated + ")";
		}
		return "@Created@: " + generated;
	}
	
	private void addMigration() {
		StringBuffer updateBuffer = new StringBuffer();
		StringBuffer rollbackBuffer = new StringBuffer();
		if(updateList.size() == 0) {
			return;
		}
		//	Create new Migration
		createMigration();
		//	Add Steps
		MColumn sQLColumn = MTable.get(getCtx(), I_AD_MigrationStep.Table_Name)
				.getColumn(I_AD_MigrationStep.COLUMNNAME_SQLStatement);
		//	Get length
		int length = sQLColumn.getFieldLength();
		for(int i = 0; i < updateList.size(); i++) {
			if(updateBuffer.length() >= (length - 200)) {
				addStep(updateBuffer.toString(), rollbackBuffer.toString());
				updateBuffer = new StringBuffer();
				rollbackBuffer = new StringBuffer();
			}
			//	
			if(updateBuffer.length() > 0) {
				updateBuffer.append(Env.NL);
				rollbackBuffer.append(Env.NL);
			}
			//	
			updateBuffer.append(updateList.get(i));
			rollbackBuffer.append(rollbackList.get(i));
		}
		//	For Last
		if(updateBuffer.length() > 0) {
			addStep(updateBuffer.toString(), rollbackBuffer.toString());
		}
		//	Clear list
		generated += updateList.size();
		updateList = new ArrayList<>();
		rollbackList = new ArrayList<>();
	}
	
	/**
	 * Add Step
	 * @param updateStatement
	 * @param rollbackStatement
	 */
	private void addStep(String updateStatement, String rollbackStatement) {
		MMigrationStep step = new MMigrationStep(migration);
		step.set_TrxName(get_TrxName());
		step.setStepType(MMigrationStep.STEPTYPE_SQLStatement);
		step.setDBType(MMigrationStep.DBTYPE_AllDatabaseTypes);
		step.setStatusCode(MMigrationStep.STATUSCODE_Applied);
		step.setApply(MMigrationStep.APPLY_Rollback);
		step.setSeqNo(sequence);
		//	Add statements
		step.setSQLStatement(updateStatement);
		step.setRollbackStatement(rollbackStatement);
		step.saveEx();
		sequence += 10;
	}
	
	/**
	 * Create Migration header
	 */
	private void createMigration() {
		if(migration != null) {
			return;
		}
		migration = new MMigration(getCtx(), 0, null);
		migration.setName(MSysConfig.getValue("DICTIONARY_ID_COMMENTS"));
		boolean dict = Ini.isPropertyBool(Ini.P_ADEMPIERESYS);
		migration.setEntityType( dict ? "D" : "U");
		String sql = "SELECT max(SeqNo)+10 FROM AD_Migration";
		int seqNo = DB.getSQLValue(null, sql);
		migration.setSeqNo(seqNo);
		migration.setStatusCode(MMigration.STATUSCODE_Applied);
		migration.setApply(MMigration.APPLY_Rollback);
		migration.saveEx();
		addLog(migration.getAD_Migration_ID(), null, null, "Ok");
	}
	
	/**
	 * Add table to update
	 * @param tableName
	 */
	private void addTableToUpdate(String tableName) {
		StringBuffer keyColumnNames = new StringBuffer();
		String uuidKey = I_AD_Table.COLUMNNAME_UUID;
		String uuidWhereClause = uuidKey + " IS NOT NULL";
		if(isOnlyUUIDMissing()) {
			uuidKey = "getUUID()";
			uuidWhereClause = I_AD_Table.COLUMNNAME_UUID + " IS NULL";
		}
		MTable table = MTable.get(getCtx(), tableName);
		//	Validate multiple key
		String keyColumn[] = table.getKeyColumns();
		if(keyColumn != null) {
			Arrays.asList(keyColumn)
				.forEach(columnName -> {
					if(keyColumnNames.length() > 0) {
						keyColumnNames.append(" || ' AND ' || ");
					}
					keyColumnNames.append("'").append(columnName).append(" = ' || ").append(columnName);
				});
		}
		//	Only for key columns
		if(keyColumnNames.length() > 0) {
			List<Object> parameters = new ArrayList<>();
			parameters.add(getAD_Client_ID());
			//	Get
			ValueNamePair[] uuidValuesPair = DB.getValueNamePairs("SELECT " + keyColumnNames + ", " + uuidKey + " FROM " + tableName + " WHERE AD_Client_ID = ? AND " + uuidWhereClause + entityTypeWhereClause, false, parameters);
			//	Get all UUID
			for(ValueNamePair value : uuidValuesPair) {
				updateList.add("UPDATE " + tableName + " SET " + I_AD_Table.COLUMNNAME_UUID + " = '" + value.getName() + "' WHERE " + value.getValue() + ";");
				rollbackList.add("UPDATE " + tableName + " SET " + I_AD_Table.COLUMNNAME_UUID + " = NULL WHERE " + value.getValue() + ";");
			}
			//	Add
			addMigration();
		}
	}
	
	/**
	 * Get Table List
	 * @param trxName
	 * @return
	 */
	private List<MTable> getTableList(String trxName) {
		List<Object> parameters = new ArrayList<>();
		StringBuilder whereClause = new StringBuilder(MTable.COLUMNNAME_IsView + "=?");
		parameters.add("N");
		if (getTableId() > 0) {
			whereClause.append(" AND ").append(MTable.COLUMNNAME_AD_Table_ID).append(" = ? ");
			parameters.add(getTableId());
		}
		//	Support to Entity Type
		if(!Util.isEmpty(getEntityType())) {
			whereClause.append(" AND EXISTS(SELECT 1 FROM AD_Column c WHERE c.AD_Table_ID = AD_Table.AD_Table_ID AND c.ColumnName = ?)");
			parameters.add(ENTITYTYPE);
			entityTypeWhereClause = " AND EntityType = '" + getEntityType() + "'";
		}

		return new Query(Env.getCtx(), MTable.Table_Name, whereClause.toString(), trxName)
				.setOnlyActiveRecords(true)
				.setParameters(parameters)
				.setOrderBy(MTable.COLUMNNAME_TableName)
				.list();
	}
}