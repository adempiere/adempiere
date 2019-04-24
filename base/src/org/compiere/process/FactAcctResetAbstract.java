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

package org.compiere.process;

import java.sql.Timestamp;

/** Generated Process for (Reset Accounting)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.1
 */
public abstract class FactAcctResetAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "Fact_Acct_Reset DELETE";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Reset Accounting";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 176;
	/**	Parameter Name for Client	*/
	public static final String AD_CLIENT_ID = "AD_Client_ID";
	/**	Parameter Name for Table	*/
	public static final String AD_TABLE_ID = "AD_Table_ID";
	/**	Parameter Name for Delete existing Accounting Entries	*/
	public static final String DELETEPOSTING = "DeletePosting";
	/**	Parameter Name for Account Date	*/
	public static final String DATEACCT = "DateAcct";
	/**	Parameter Value for Client	*/
	private int clientId;
	/**	Parameter Value for Table	*/
	private int tableId;
	/**	Parameter Value for Delete existing Accounting Entries	*/
	private boolean isDeletePosting;
	/**	Parameter Value for Account Date	*/
	private Timestamp dateAcct;
	/**	Parameter Value for Account Date(To)	*/
	private Timestamp dateAcctTo;

	@Override
	protected void prepare() {
		clientId = getParameterAsInt(AD_CLIENT_ID);
		tableId = getParameterAsInt(AD_TABLE_ID);
		isDeletePosting = getParameterAsBoolean(DELETEPOSTING);
		dateAcct = getParameterAsTimestamp(DATEACCT);
		dateAcctTo = getParameterToAsTimestamp(DATEACCT);
	}

	/**	 Getter Parameter Value for Client	*/
	protected int getClientId() {
		return clientId;
	}

	/**	 Setter Parameter Value for Client	*/
	protected void setClientId(int clientId) {
		this.clientId = clientId;
	}

	/**	 Getter Parameter Value for Table	*/
	protected int getTableId() {
		return tableId;
	}

	/**	 Setter Parameter Value for Table	*/
	protected void setTableId(int tableId) {
		this.tableId = tableId;
	}

	/**	 Getter Parameter Value for Delete existing Accounting Entries	*/
	protected boolean isDeletePosting() {
		return isDeletePosting;
	}

	/**	 Setter Parameter Value for Delete existing Accounting Entries	*/
	protected void setDeletePosting(boolean isDeletePosting) {
		this.isDeletePosting = isDeletePosting;
	}

	/**	 Getter Parameter Value for Account Date	*/
	protected Timestamp getDateAcct() {
		return dateAcct;
	}

	/**	 Setter Parameter Value for Account Date	*/
	protected void setDateAcct(Timestamp dateAcct) {
		this.dateAcct = dateAcct;
	}

	/**	 Getter Parameter Value for Account Date(To)	*/
	protected Timestamp getDateAcctTo() {
		return dateAcctTo;
	}

	/**	 Setter Parameter Value for Account Date(To)	*/
	protected void setDateAcctTo(Timestamp dateAcctTo) {
		this.dateAcctTo = dateAcctTo;
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