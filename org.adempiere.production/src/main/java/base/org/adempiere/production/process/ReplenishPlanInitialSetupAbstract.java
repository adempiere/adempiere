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

package org.adempiere.production.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Replenish Plan Initial Setup)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class ReplenishPlanInitialSetupAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "ReplenishPlanInitialSetup";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Replenish Plan Initial Setup";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53870;
	/**	Parameter Name for Create Planned Order Document Type	*/
	public static final String ISCREATEDOCTYPEPLANNEDORDER = "isCreateDocTypePlannedOrder";
	/**	Parameter Name for Create Manufacturing Order Document Type	*/
	public static final String ISCREATEDOCTYPECONFIRMEDORDER = "isCreateDocTypeConfirmedOrder";
	/**	Parameter Name for Create Replenish Plan Requisition Document Type	*/
	public static final String ISCREATEDOCTYPEREQUISITION = "isCreateDocTypeRequisition";
	/**	Parameter Name for Create Purchase Order Document Type	*/
	public static final String ISCREATEDOCTYPEPURCHASEORDER = "isCreateDocTypePurchaseOrder";
	/**	Parameter Value for Create Planned Order Document Type	*/
	private boolean isisCreateDocTypePlannedOrder;
	/**	Parameter Value for Create Manufacturing Order Document Type	*/
	private boolean isisCreateDocTypeConfirmedOrder;
	/**	Parameter Value for Create Replenish Plan Requisition Document Type	*/
	private boolean isisCreateDocTypeRequisition;
	/**	Parameter Value for Create Purchase Order Document Type	*/
	private boolean isisCreateDocTypePurchaseOrder;

	@Override
	protected void prepare() {
		isisCreateDocTypePlannedOrder = getParameterAsBoolean(ISCREATEDOCTYPEPLANNEDORDER);
		isisCreateDocTypeConfirmedOrder = getParameterAsBoolean(ISCREATEDOCTYPECONFIRMEDORDER);
		isisCreateDocTypeRequisition = getParameterAsBoolean(ISCREATEDOCTYPEREQUISITION);
		isisCreateDocTypePurchaseOrder = getParameterAsBoolean(ISCREATEDOCTYPEPURCHASEORDER);
	}

	/**	 Getter Parameter Value for Create Planned Order Document Type	*/
	protected boolean isisCreateDocTypePlannedOrder() {
		return isisCreateDocTypePlannedOrder;
	}

	/**	 Setter Parameter Value for Create Planned Order Document Type	*/
	protected void setisCreateDocTypePlannedOrder(boolean isisCreateDocTypePlannedOrder) {
		this.isisCreateDocTypePlannedOrder = isisCreateDocTypePlannedOrder;
	}

	/**	 Getter Parameter Value for Create Manufacturing Order Document Type	*/
	protected boolean isisCreateDocTypeConfirmedOrder() {
		return isisCreateDocTypeConfirmedOrder;
	}

	/**	 Setter Parameter Value for Create Manufacturing Order Document Type	*/
	protected void setisCreateDocTypeConfirmedOrder(boolean isisCreateDocTypeConfirmedOrder) {
		this.isisCreateDocTypeConfirmedOrder = isisCreateDocTypeConfirmedOrder;
	}

	/**	 Getter Parameter Value for Create Replenish Plan Requisition Document Type	*/
	protected boolean isisCreateDocTypeRequisition() {
		return isisCreateDocTypeRequisition;
	}

	/**	 Setter Parameter Value for Create Replenish Plan Requisition Document Type	*/
	protected void setisCreateDocTypeRequisition(boolean isisCreateDocTypeRequisition) {
		this.isisCreateDocTypeRequisition = isisCreateDocTypeRequisition;
	}

	/**	 Getter Parameter Value for Create Purchase Order Document Type	*/
	protected boolean isisCreateDocTypePurchaseOrder() {
		return isisCreateDocTypePurchaseOrder;
	}

	/**	 Setter Parameter Value for Create Purchase Order Document Type	*/
	protected void setisCreateDocTypePurchaseOrder(boolean isisCreateDocTypePurchaseOrder) {
		this.isisCreateDocTypePurchaseOrder = isisCreateDocTypePurchaseOrder;
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