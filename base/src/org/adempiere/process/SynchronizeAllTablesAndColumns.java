/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2020 ADempiere Foundation, All Rights Reserved.         *
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

package org.adempiere.process;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

/** Generated Process to Synchronize All Tables and Columns.
 *  This process will synchronize all tables and columns with the database
 *  where the tables and/or columns are new or have changes that require
 *  updates in the database.  Tables and columns that require sync are
 *  flagged when saved using the field RequiresSync.  Changes in table or
 *  column name, if recorded in the field NameOldValue, will also be made.
 *  <p>Individual tables and columns can be selected to limit the process. 
 *  If not selected, every table and column will be tested.
 *  <p>The parameter isOnlyReport, if selected, will generate the SQL required
 *  to synchronize the tables and columns but will not execute these updates. This 
 *  provides a means to review the changes before they are performed.
 *  <p>If the parameter isSyncObviousChanges is not selected, every table
 *  and column will be checked against the database for consistency. Missing 
 *  tables and columns will be added and any tables or columns in the database
 *  that do not match the Application Dictionary will be modified.
 *  <p>The application dictionary takes precedence. Changes made to the database
 *  will be overwritten to match the application dictionary.
 *  <p>The sync operation is not perfect and some manual intervention may be
 *  required if there are multiple keys and complex constraints.
 *  
 *  @author Michael McKay, mckayERP@gmail.com 
 *  	<li>Added as part of <a href="https://github.com/adempiere/adempiere/issues/213">#213</a>
 *  		Support for application dictionary changes and configurable automatic synchronization
 *  
 *  @version Release 3.9.4
 */
public class SynchronizeAllTablesAndColumns extends SynchronizeAllTablesAndColumnsAbstract
{

	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{

		if (get_TrxName() == null || get_TrxName().isEmpty())
			throw new AdempiereException("No Transaction.  A transaction is required.");
		
		// Create a connection for this work
		Connection conn = Trx.get(get_TrxName(),true).getConnection();
		
		String syncMessage = "";
		int tableCount = 0;
		int tableChangedCount = 0;
		int columnCount = 0;
		int columnChangedCount = 0;
		Properties ctx = Env.getCtx();
		boolean hasErrors = false;
		
		int ad_table_id = getTableId();
		int ad_column_id = getColumnId();
		
		//  If a column is selected, make sure the table is correct
		if (ad_column_id > 0)
			ad_table_id = MColumn.getTable_ID(getCtx(), ad_column_id, null);
		
		String where = null;
		
		if (ad_table_id <= 0)
		{
			//  Can't change the content of AD_Table and AD_Column and
			//  also change their structure.  Change the structure first.
			MTable table = new MTable(ctx, MTable.Table_ID, null);
			syncMessage = table.syncDatabase(conn, isOnlyReport());
			if (!syncMessage.equals("") && !MColumn.MSG_NoSQLNOChangesMade.equals(syncMessage))
			{
				addLog(table.getTableName() + ": " + syncMessage);
				tableChangedCount++;
			}
			tableCount++;
	
			table = new MTable(ctx, MColumn.Table_ID, null);
			syncMessage = table.syncDatabase(conn, isOnlyReport());
			if (!syncMessage.equals("") && !MColumn.MSG_NoSQLNOChangesMade.equals(syncMessage))
			{
				if (syncMessage.contains("Error"))
				{
					hasErrors=true; 
				}
				addLog(table.getTableName() + ": " + syncMessage);
				tableChangedCount++;
			}
			tableCount++;
		
			// Sync the columns of AD_Table and AD_Column
			where = MColumn.COLUMNNAME_ColumnSQL + " is null"
				+ " AND (" + MColumn.COLUMNNAME_AD_Table_ID + "=" + MTable.Table_ID
				+ " OR " + MColumn.COLUMNNAME_AD_Table_ID + "=" + MColumn.Table_ID + ")";
			if (isSyncObviousChanges()) {
				where += " AND " + MColumn.COLUMNNAME_RequiresSync + "='Y'";
			}

			int[] ids = new Query(getCtx(), MColumn.Table_Name, where, null)
										.setOnlyActiveRecords(true)
										.getIDs();
			
			for (int i=0; i < ids.length; i++)
			{
				MColumn column = MColumn.get(getCtx(), ids[i]);
				syncMessage = column.syncDatabase(conn, isOnlyReport());
				if (!syncMessage.equals("") && !MColumn.MSG_NoSQLNOChangesMade.equals(syncMessage)) 
				{
					if (syncMessage.contains("Error"))
					{
						hasErrors=true; 
					}
					addLog(column.getAD_Table().getTableName() + "." + column.getColumnName() + ": " + syncMessage);
					columnChangedCount++;
				}
				columnCount++;
				if (columnCount%50 == 0)
					log.info("Column Sync. Changed/Examined/Total: " + columnChangedCount + "/" + columnCount + "/" + ids.length);
			}
		
		}
		
		where = MTable.COLUMNNAME_IsView + "='N'";
		// Tables first - table sync will update some if not all the columns
		if (ad_table_id > 0)
		{
			where += " AND " + MTable.COLUMNNAME_AD_Table_ID + "=?";			
		}
		else
		{
			where += " AND " + MTable.COLUMNNAME_AD_Table_ID + "!=" + MTable.Table_ID
					+ " AND " + MTable.COLUMNNAME_AD_Table_ID + "!=" + MColumn.Table_ID;
		}
		if (isSyncObviousChanges()) {
			where += " AND " + MTable.COLUMNNAME_RequiresSync + "='Y'";
		}
		
		Query query = new Query(getCtx(), MTable.Table_Name, where, get_TrxName());
		if (getTableId() > 0)
			query = query.setParameters(getTableId());
		
		List<MTable> tables = query.setOnlyActiveRecords(true)
						.list();
		
		for (MTable table : tables)
		{
			syncMessage = table.syncDatabase(conn, isOnlyReport());
			
			if (!syncMessage.equals("") && !MColumn.MSG_NoSQLNOChangesMade.equals(syncMessage))
			{
				if (syncMessage.contains("Error"))
				{
					hasErrors=true; 
				}
				addLog(table.getTableName() + ": " + syncMessage);
				tableChangedCount++;
			}
			tableCount++;
			if (tableCount%50 == 0)
				log.info("Table Sync. Changed/Examined/Total: " + tableChangedCount + "/" + tableCount + "/" + tables.size());
		}
		

		where = MColumn.COLUMNNAME_ColumnSQL + " is null";
		// Columns
		if (ad_column_id <= 0)
		{
			if (ad_table_id > 0)
			{
				where += " AND " + MColumn.COLUMNNAME_AD_Table_ID + "=?";						
			}
			else
			{
				where += " AND " + MColumn.COLUMNNAME_AD_Table_ID + "!=" + MTable.Table_ID
						+ " AND " + MColumn.COLUMNNAME_AD_Table_ID + "!=" + MColumn.Table_ID;
			}
		}
		else
		{
			where += " AND " + MColumn.COLUMNNAME_AD_Column_ID + "=?";			
		}
		
		if (isSyncObviousChanges()) {
			where += " AND " + MColumn.COLUMNNAME_RequiresSync + "='Y'";
		}

		query = new Query(getCtx(), MColumn.Table_Name, where, get_TrxName());
		if (ad_column_id <= 0 && ad_table_id > 0)
		{
			query = query.setParameters(ad_table_id);
		}
		else if (ad_column_id > 0)
		{
			query = query.setParameters(ad_column_id);			
		}
		
		int[] ids = query.setOnlyActiveRecords(true)
									.getIDs();
		
		for (int i = 0; i<ids.length; i++)
		{
			MColumn column = new MColumn(getCtx(), ids[i], null);
			//log.info("Column: " + column.getAD_Table().getTableName() + "." + column.getColumnName());
			if (column.getAD_Table_ID() == MTable.Table_ID || column.getAD_Table_ID() == MColumn.Table_ID)
			{
				// column.set_TrxName(null);
				column.set_TrxName(get_TrxName());
				//continue;
			}
			
			syncMessage = column.syncDatabase(conn, isOnlyReport());
			if (!syncMessage.equals("") && !MColumn.MSG_NoSQLNOChangesMade.equals(syncMessage) && !MColumn.MSG_CannotSyncView.equals(syncMessage))
			{
				if (syncMessage.contains("Error"))
				{
					hasErrors=true; 
				}
				addLog("<b>" + column.getAD_Table().getTableName() + "." + column.getColumnName() + "</b>: " + syncMessage);
				columnChangedCount++;
			}
			columnCount++;
			if (columnCount%50 == 0)
				log.info("Column Sync. Changed/Examined/Total: " + columnChangedCount + "/" + columnCount + "/" + ids.length);
		}
		
		String returnMessage = Msg.parseTranslation(getCtx(), "@AD_Table_ID@: " + tableChangedCount + "/" + tableCount 
				+ " @AD_Column_ID@: " + columnChangedCount + "/" + columnCount);
		
		if (tableChangedCount + columnChangedCount == 0)
		{
			if (isOnlyReport())
				returnMessage += Msg.parseTranslation(getCtx()," " + MSG_NothingToReport);
			else 
				returnMessage += Msg.parseTranslation(getCtx(), " " + MSG_NoChangesRequired);
		}
		else
		{
			if (isOnlyReport())
				returnMessage += Msg.parseTranslation(getCtx()," " + MSG_OnlyReport);
			else
				if (hasErrors)
					returnMessage += Msg.parseTranslation(getCtx(), " " + MSG_ChangesMadeWithErrors);
				else
					returnMessage += Msg.parseTranslation(getCtx(), " " + MSG_ChangesMade);
			
		}
		return returnMessage;
	}
}