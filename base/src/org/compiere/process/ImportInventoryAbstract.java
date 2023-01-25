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

/** Generated Process for (Import Inventory)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class ImportInventoryAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "Import_Inventory";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Import Inventory";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 219;
	/**	Parameter Name for Client	*/
	public static final String AD_CLIENT_ID = "AD_Client_ID";
	/**	Parameter Name for Organization	*/
	public static final String AD_ORG_ID = "AD_Org_ID";
	/**	Parameter Name for Locator	*/
	public static final String M_LOCATOR_ID = "M_Locator_ID";
	/**	Parameter Name for Movement Date	*/
	public static final String MOVEMENTDATE = "MovementDate";
	/**	Parameter Name for Delete old imported records	*/
	public static final String DELETEOLDIMPORTED = "DeleteOldImported";
	/**	Parameter Name for Update Costing	*/
	public static final String ISUPDATECOSTING = "IsUpdateCosting";
	/**	Parameter Name for Accounting Schema	*/
	public static final String C_ACCTSCHEMA_ID = "C_AcctSchema_ID";
	/**	Parameter Name for Cost Type	*/
	public static final String M_COSTTYPE_ID = "M_CostType_ID";
	/**	Parameter Name for Cost Element	*/
	public static final String M_COSTELEMENT_ID = "M_CostElement_ID";
	/**	Parameter Name for Trx Organization	*/
	public static final String AD_ORGTRX_ID = "AD_OrgTrx_ID";
	/**	Parameter Value for Client	*/
	private int clientId;
	/**	Parameter Value for Organization	*/
	private int orgId;
	/**	Parameter Value for Locator	*/
	private int locatorId;
	/**	Parameter Value for Movement Date	*/
	private Timestamp movementDate;
	/**	Parameter Value for Delete old imported records	*/
	private boolean isDeleteOldImported;
	/**	Parameter Value for Update Costing	*/
	private boolean isUpdateCosting;
	/**	Parameter Value for Accounting Schema	*/
	private int acctSchemaId;
	/**	Parameter Value for Cost Type	*/
	private int costTypeId;
	/**	Parameter Value for Cost Element	*/
	private int costElementId;
	/**	Parameter Value for Trx Organization	*/
	private int orgTrxId;

	@Override
	protected void prepare() {
		clientId = getParameterAsInt(AD_CLIENT_ID);
		orgId = getParameterAsInt(AD_ORG_ID);
		locatorId = getParameterAsInt(M_LOCATOR_ID);
		movementDate = getParameterAsTimestamp(MOVEMENTDATE);
		isDeleteOldImported = getParameterAsBoolean(DELETEOLDIMPORTED);
		isUpdateCosting = getParameterAsBoolean(ISUPDATECOSTING);
		acctSchemaId = getParameterAsInt(C_ACCTSCHEMA_ID);
		costTypeId = getParameterAsInt(M_COSTTYPE_ID);
		costElementId = getParameterAsInt(M_COSTELEMENT_ID);
		orgTrxId = getParameterAsInt(AD_ORGTRX_ID);
	}

	/**	 Getter Parameter Value for Client	*/
	protected int getClientId() {
		return clientId;
	}

	/**	 Setter Parameter Value for Client	*/
	protected void setClientId(int clientId) {
		this.clientId = clientId;
	}

	/**	 Getter Parameter Value for Organization	*/
	protected int getOrgId() {
		return orgId;
	}

	/**	 Setter Parameter Value for Organization	*/
	protected void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	/**	 Getter Parameter Value for Locator	*/
	protected int getLocatorId() {
		return locatorId;
	}

	/**	 Setter Parameter Value for Locator	*/
	protected void setLocatorId(int locatorId) {
		this.locatorId = locatorId;
	}

	/**	 Getter Parameter Value for Movement Date	*/
	protected Timestamp getMovementDate() {
		return movementDate;
	}

	/**	 Setter Parameter Value for Movement Date	*/
	protected void setMovementDate(Timestamp movementDate) {
		this.movementDate = movementDate;
	}

	/**	 Getter Parameter Value for Delete old imported records	*/
	protected boolean isDeleteOldImported() {
		return isDeleteOldImported;
	}

	/**	 Setter Parameter Value for Delete old imported records	*/
	protected void setDeleteOldImported(boolean isDeleteOldImported) {
		this.isDeleteOldImported = isDeleteOldImported;
	}

	/**	 Getter Parameter Value for Update Costing	*/
	protected boolean isUpdateCosting() {
		return isUpdateCosting;
	}

	/**	 Setter Parameter Value for Update Costing	*/
	protected void setIsUpdateCosting(boolean isUpdateCosting) {
		this.isUpdateCosting = isUpdateCosting;
	}

	/**	 Getter Parameter Value for Accounting Schema	*/
	protected int getAcctSchemaId() {
		return acctSchemaId;
	}

	/**	 Setter Parameter Value for Accounting Schema	*/
	protected void setAcctSchemaId(int acctSchemaId) {
		this.acctSchemaId = acctSchemaId;
	}

	/**	 Getter Parameter Value for Cost Type	*/
	protected int getCostTypeId() {
		return costTypeId;
	}

	/**	 Setter Parameter Value for Cost Type	*/
	protected void setCostTypeId(int costTypeId) {
		this.costTypeId = costTypeId;
	}

	/**	 Getter Parameter Value for Cost Element	*/
	protected int getCostElementId() {
		return costElementId;
	}

	/**	 Setter Parameter Value for Cost Element	*/
	protected void setCostElementId(int costElementId) {
		this.costElementId = costElementId;
	}

	/**	 Getter Parameter Value for Trx Organization	*/
	protected int getOrgTrxId() {
		return orgTrxId;
	}

	/**	 Setter Parameter Value for Trx Organization	*/
	protected void setOrgTrxId(int orgTrxId) {
		this.orgTrxId = orgTrxId;
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