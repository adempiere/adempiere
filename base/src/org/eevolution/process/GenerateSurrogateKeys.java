/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
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

package org.eevolution.process;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.compiere.model.I_AD_Element;
import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.model.M_Element;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.jfree.util.Log;

/** Generated Process for (Generate Surrogate Key UUID for all tables)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public class GenerateSurrogateKeys extends GenerateSurrogateKeysAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		List<MTable> tableList = getTableList(get_TrxName());
		//	Add columns
		tableList.stream().filter(table -> table != null).forEach(table -> {
			addColumn(table.getAD_Table_ID());
		});
		//	Generate Surrogate Keys
		if (isGenerateUUIDforallrecords()) {
			tableList.stream().filter(table -> table != null).forEach(table -> {
				generateUUIDByTable(table.getTableName());
			});
		}
		return "@Ok@";
	}

	private void addColumn(int tableId) {
		try {
			//System.out.println("Processing Add Column UUID in tables");
			Log.info("Procesing Add Column UUID in tables");
			AtomicInteger elementId = new AtomicInteger();
			AtomicInteger columnId = new AtomicInteger();
			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					M_Element element = getElement("UUID", trxName);
					if (element == null) {
						element = new M_Element(Env.getCtx(), 0, trxName);
						element.setColumnName("UUID");
						element.setPrintName("UUID");
						element.setName("Immutable Universally Unique Identifier");
						element.setDescription("Immutable Universally Unique Identifier");
						element.setHelp("\"A surrogate key in a database is a unique identifier for either an entity in the modeled world or an object in the database. The surrogate key is not derived from application data, unlike a natural (or business) key which is derived from application data. \" , According to Wikipedia http://en.wikipedia.org/wiki/Surrogate_key");
						element.setEntityType("D");
						element.saveEx();
						elementId.set(element.get_ID());
					}
					else {
						elementId.set(element.getAD_Element_ID());
					}
				}
			});

			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					MTable table = MTable.get(Env.getCtx() , tableId);
					log.info("Table Name: " + table.getTableName() + " ...");
					M_Element element = new M_Element(Env.getCtx() , elementId.get() , trxName);
					MColumn column = table.getColumn("UUID");
					if (column == null) {
						column = new MColumn(Env.getCtx(), 0, trxName);
						column.setName(element.getName());
						column.setAD_Table_ID(table.getAD_Table_ID());
						column.setAD_Element_ID(element.getAD_Element_ID());
						column.setName(element.getName());
						column.setColumnName(element.getColumnName());
						column.setEntityType(element.getEntityType());
						column.setIsActive(true);
						column.setAD_Reference_ID(DisplayType.String);
						column.setFieldLength(36);
						column.setEntityType("D");
						column.saveEx();
						columnId.set(column.getAD_Column_ID());
					} else {
						column.setAD_Element_ID(element.getAD_Element_ID());
						column.setFieldLength(36);
						column.saveEx();
						columnId.set(column.getAD_Column_ID());
					}
				}
			});

			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					MColumn column = new MColumn(Env.getCtx() , columnId.get() , trxName);
					if (column != null && column.getAD_Column_ID() > 0)
						column.syncDatabase();
				}
			});
		} catch (Exception e) {
			addLog(e.getMessage());
		}
	}

	/**
	 * Generate UUID for Table
	 * @param tableName
	 */
	private void generateUUIDByTable(String tableName) {
		int updated = DB.executeUpdate("UPDATE " + tableName + " SET UUID = getUUID() WHERE UUID IS NULL", get_TrxName());
		addLog(tableName + " @Updated@: " + updated);
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

		return new Query(Env.getCtx(), MTable.Table_Name, whereClause.toString(), trxName)
				.setOnlyActiveRecords(true)
				.setParameters(parameters)
				.setOrderBy(MTable.COLUMNNAME_TableName)
				.list();
	}

	public static M_Element getElement(String name, String trxName) {
		final String whereClause = I_AD_Element.COLUMNNAME_ColumnName + "=?";
		return new Query(Env.getCtx(), I_AD_Element.Table_Name, whereClause, trxName)
				.setParameters(name)
				.firstOnly();
	}
}