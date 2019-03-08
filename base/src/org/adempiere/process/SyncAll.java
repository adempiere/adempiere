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

package org.adempiere.process;

import java.util.List;

import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.Msg;

/** Generated Process to Sync All Tables and Columns.
 *  This process will synchronize all tables and columns with the database
 *  where the tables and/or columns are new or have changes that require
 *  updates in the database.  Tables and columns that require sync are
 *  flagged when saved using the field RequiresSync.  Changes in table or
 *  column name, if recorded in the field NameOldValue, will also be made.
 *  <p>The sync operation is not perfect and some manual intervention may be
 *  required if there are multiple keys and complex constraints.
 *  
 *  @author Michael McKay, mckayERP@gmail.com 
 *  @version Release 3.9.2
 */
public class SyncAll extends SyncAllAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
	
		String where = MTable.COLUMNNAME_RequiresSync + "='Y'";
		// Tables first
		List<MTable> tables = new Query(getCtx(), MTable.Table_Name, where, get_TrxName())
									.setOnlyActiveRecords(true)
									.list();
		String syncMessage = "";
		int tableCount = 0;
		for (MTable table : tables)
		{
			syncMessage = table.syncDatabase();
			addLog(table.getTableName() + ": " + syncMessage);
			tableCount++;
		}
		
		where = MColumn.COLUMNNAME_RequiresSync + "='Y'";
		// Tables first
		List<MColumn> columns = new Query(getCtx(), MColumn.Table_Name, where, get_TrxName())
									.setOnlyActiveRecords(true)
									.list();
		
		int columnCount = 0;
		for (MColumn column : columns)
		{
			syncMessage = column.syncDatabase();
			addLog(column.getAD_Table().getTableName() + "." + column.getColumnName() + ": " + syncMessage);
			columnCount++;
		}
		
		return Msg.parseTranslation(getCtx(), "@AD_Table_ID@: " + tableCount + " @AD_Column_ID@: " + columnCount);
	}
}