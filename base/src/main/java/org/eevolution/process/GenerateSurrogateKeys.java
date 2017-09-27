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

import org.compiere.model.I_AD_Element;
import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.model.M_Element;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.jfree.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

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
		Arrays.stream(getTableIds(null)).filter(tableId -> tableId > 0).forEach(tableId -> {
			addColumn(tableId);
			if ( isGenerateUUIDforallrecords())
				generateUUIDByTable(tableId);
		});
		return "@Ok@";
	}

	private void addColumn(int tableId) {
		try {
			//System.out.println("Procesing Add Column UUID in tables");
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

			MColumn column = null;
			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					MTable table = MTable.get(Env.getCtx() , tableId);
					System.out.println("Table Name: " + table.getTableName() + " ...");
					Log.info("Table Name: " + table.getTableName() + " ...");
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

	private void generateUUIDByTable(int tableId) {
		try {
			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					MTable table = MTable.get(Env.getCtx() , tableId);
					//System.out.println("Procesing generating UUIDs for Table " + table.getTableName() +  " Name : " + table.getName() );
					Log.info("Procesing generating UUIDs for Table " + table.getTableName() +  " Name : " + table.getName());
					List<PO> records = new Query(Env.getCtx(),table.getTableName(),"UUID IS NULL",trxName).list();
					records.stream()
							.filter(    po -> po != null && po.get_ColumnIndex("UUID") > 0 && po.get_Value("UUID") == null)
							.forEach(   po -> {
								String keys[] = po.get_KeyColumns();
								String sqlKeys = "";
								List<Object> parameters = new ArrayList<Object>();
								for( int i=0 ; i < keys.length ; i++){
									sqlKeys+=keys[i]+"=? ";
									parameters.add(po.get_Value(keys[i]));

									if(i<keys.length-1)
										sqlKeys+=" AND ";
								}

								if(parameters.size() > 1)
									po = new Query(Env.getCtx(),table.getTableName(),sqlKeys.toString(),trxName)
											.setParameters(parameters)
											.first();

								String UUIDString= UUID.randomUUID().toString();
								//System.out.println("Table: "+table.getTableName()+" Record Id : "+po.get_ID()+" UUID: "+UUIDString+" Object :"+po.toString());
								Log.info("Table: "+table.getTableName()+" Record Id : "+po.get_ID()+" UUID: "+UUIDString+" Object :"+po.toString());
								if(po.get_ID() == 0 && parameters.size()==1 && po != null)
									System.out.println("Table : " + po.get_TableName() + " Object Without Id : " + po.toString());
								else{
									po.set_CustomColumn("UUID",UUIDString);
									po.saveEx();
								}
							});
				}
			});
		} catch (Exception e) {
			addLog(e.getMessage());
		}
	}

	private int[] getTableIds(String trxName) {
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
				.getIDs();
	}

	public static M_Element getElement(String name, String trxName) {
		final String whereClause = I_AD_Element.COLUMNNAME_ColumnName + "=?";
		return new Query(Env.getCtx(), I_AD_Element.Table_Name, whereClause, trxName)
				.setParameters(name)
				.firstOnly();
	}
}