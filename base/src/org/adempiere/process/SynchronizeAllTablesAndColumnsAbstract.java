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

import org.compiere.process.SvrProcess;

/** Generated Process for (Sync All Tables and Columns)
 *  @author ADempiere (generated) 
 *  @author Michael McKay, mckayERP@gmail.com 
 *  	<li>Added as part of <a href="https://github.com/adempiere/adempiere/issues/213">#213</a>
 *  		Support for application dictionary changes and configurable automatic synchronization
 *  @version Release 3.9.4
 */
public abstract class SynchronizeAllTablesAndColumnsAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "SyncAllTablesAndColumns";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Synchronize All Tables and Columns";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54204;
	/**	Parameter Name for Table	*/
	public static final String AD_TABLE_ID = "AD_Table_ID";
	/**	Parameter Name for Column	*/
	public static final String AD_COLUMN_ID = "AD_Column_ID";
	/**	Parameter Name for Only known changes	*/
	public static final String SYNCOBVIOUSCHANGES = "SyncObviousChanges";
	/**	Parameter Name for Only report	*/
	public static final String ONLYREPORT = "OnlyReport";
	
	/** Message Text for translation */
	public static final String MSG_OnlyReport = "@SynchronizeAllTablesAndColumnsAbstract_OnlyReport@";
	public static final String MSG_NoChangesRequired = "@SynchronizeAllTablesAndColumnsAbstract_NoChangesRequired@";
	public static final String MSG_ChangesMade = "@SynchronizeAllTablesAndColumnsAbstract_ChangeMade@";
	public static final String MSG_NothingToReport = "@SynchronizeAllTablesAndColumnsAbstract_NothingToReport@";
	public static final String MSG_ChangesMadeWithErrors = "@SynchronizeAllTablesAndColumnsAbstract_ChangeMadeWithError@";

	/**	Parameter Value for Table	*/
	private int tableId;
	/**	Parameter Value for Column	*/
	private int columnId;
	/**	Parameter Value for Only known changes	*/
	private boolean isSyncObviousChanges;
	/**	Parameter Value for Only report	*/
	private boolean isOnlyReport;

	@Override
	protected void prepare() {
		tableId = getParameterAsInt(AD_TABLE_ID);
		columnId = getParameterAsInt(AD_COLUMN_ID);
		isSyncObviousChanges = getParameterAsBoolean(SYNCOBVIOUSCHANGES);
		isOnlyReport = getParameterAsBoolean(ONLYREPORT);
	}

	/**	 Getter Parameter Value for Table	*/
	protected int getTableId() {
		return tableId;
	}

	/**	 Setter Parameter Value for Table	*/
	protected void setTableId(int tableId) {
		this.tableId = tableId;
	}

	/**	 Getter Parameter Value for Column	*/
	protected int getColumnId() {
		return columnId;
	}

	/**	 Setter Parameter Value for Column	*/
	protected void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	/**	 Getter Parameter Value for Only known changes	*/
	protected boolean isSyncObviousChanges() {
		return isSyncObviousChanges;
	}

	/**	 Setter Parameter Value for Only known changes	*/
	protected void setSyncObviousChanges(boolean isSyncObviousChanges) {
		this.isSyncObviousChanges = isSyncObviousChanges;
	}

	/**	 Getter Parameter Value for Only report	*/
	protected boolean isOnlyReport() {
		return isOnlyReport;
	}

	/**	 Setter Parameter Value for Only report	*/
	protected void setOnlyReport(boolean isOnlyReport) {
		this.isOnlyReport = isOnlyReport;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME_FOR_PROCESS;
	}
}