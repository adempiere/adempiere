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

import org.compiere.process.SvrProcess;

/** Generated Process for (Export Surrogate Key To Migration)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.1
 */
public abstract class ExportSurrogateKeyToMigrationAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "ExportSurrogateKeyToMigration";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Export Surrogate Key To Migration";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54170;
	/**	Parameter Name for Table	*/
	public static final String AD_TABLE_ID = "AD_Table_ID";
	/**	Parameter Name for Entity Type	*/
	public static final String ENTITYTYPE = "EntityType";
	/**	Parameter Value for Table	*/
	private int tableId;
	/**	Parameter Value for Entity Type	*/
	private String entityType;

	@Override
	protected void prepare() {
		tableId = getParameterAsInt(AD_TABLE_ID);
		entityType = getParameterAsString(ENTITYTYPE);
	}

	/**	 Getter Parameter Value for Table	*/
	protected int getTableId() {
		return tableId;
	}

	/**	 Setter Parameter Value for Table	*/
	protected void setTableId(int tableId) {
		this.tableId = tableId;
	}

	/**	 Getter Parameter Value for Entity Type	*/
	protected String getEntityType() {
		return entityType;
	}

	/**	 Setter Parameter Value for Entity Type	*/
	protected void setEntityType(String entityType) {
		this.entityType = entityType;
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