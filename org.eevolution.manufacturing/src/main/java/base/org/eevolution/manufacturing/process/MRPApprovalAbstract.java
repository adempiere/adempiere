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

package org.eevolution.manufacturing.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Requisition Approval)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class MRPApprovalAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "MRP_Requisition_Approval";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Requisition Approval";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53321;
 
	/**	Parameter Name for Order Type	*/
	private static final String ORDERTYPE = "OrderType";
	/**	Parameter Name for Priority	*/
	private static final String PRIORITYRULE = "PriorityRule";
	/**	Parameter Name for Business Partner 	*/
	private static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Reference No	*/
	private static final String REFERENCENO = "ReferenceNo";
	/**	Parameter Name for Warehouse in Transit	*/
	private static final String M_WAREHOUSE_ID = "M_Warehouse_ID";
	/**	Parameter Name for Shipper	*/
	private static final String M_SHIPPER_ID = "M_Shipper_ID";
	/**	Parameter Name for Locator	*/
	private static final String M_LOCATOR_ID = "M_Locator_ID";
	/**	Parameter Name for Locator To	*/
	private static final String M_LOCATORTO_ID = "M_LocatorTo_ID";
	/**	Parameter Name for Resource (Plant)	*/
	private static final String S_RESOURCE_ID = "S_Resource_ID";
	/**	Parameter Name for BOM & Formula	*/
	private static final String PP_PRODUCT_BOM_ID = "PP_Product_BOM_ID";
	/**	Parameter Name for Workflow	*/
	private static final String AD_WORKFLOW_ID = "AD_Workflow_ID";

	/**	Parameter Value for Order Type	*/
	private String orderType;
	/**	Parameter Value for Priority	*/
	private String priorityRule;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Reference No	*/
	private String referenceNo;
	/**	Parameter Value for Warehouse in Transit	*/
	private int warehouseId;
	/**	Parameter Value for Shipper	*/
	private int shipperId;
	/**	Parameter Value for Locator	*/
	private int locatorId;
	/**	Parameter Value for Locator To	*/
	private int locatorToId;
	/**	Parameter Value for Resource (Plant)	*/
	private int resourceId;
	/**	Parameter Value for BOM & Formula	*/
	private int productBOMId;
	/**	Parameter Value for Workflow	*/
	private int workflowId;
 

	@Override
	protected void prepare() {
		orderType = getParameterAsString(ORDERTYPE);
		priorityRule = getParameterAsString(PRIORITYRULE);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		referenceNo = getParameterAsString(REFERENCENO);
		warehouseId = getParameterAsInt(M_WAREHOUSE_ID);
		shipperId = getParameterAsInt(M_SHIPPER_ID);
		locatorId = getParameterAsInt(M_LOCATOR_ID);
		locatorToId = getParameterAsInt(M_LOCATORTO_ID);
		resourceId = getParameterAsInt(S_RESOURCE_ID);
		productBOMId = getParameterAsInt(PP_PRODUCT_BOM_ID);
		workflowId = getParameterAsInt(AD_WORKFLOW_ID);
	}

	/**	 Getter Parameter Value for Order Type	*/
	protected String getOrderType() {
		return orderType;
	}

	/**	 Setter Parameter Value for Order Type	*/
	protected void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**	 Getter Parameter Value for Priority	*/
	protected String getPriorityRule() {
		return priorityRule;
	}

	/**	 Setter Parameter Value for Priority	*/
	protected void setPriorityRule(String priorityRule) {
		this.priorityRule = priorityRule;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Reference No	*/
	protected String getReferenceNo() {
		return referenceNo;
	}

	/**	 Setter Parameter Value for Reference No	*/
	protected void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	/**	 Getter Parameter Value for Warehouse in Transit	*/
	protected int getWarehouseId() {
		return warehouseId;
	}

	/**	 Setter Parameter Value for Warehouse in Transit	*/
	protected void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**	 Getter Parameter Value for Shipper	*/
	protected int getShipperId() {
		return shipperId;
	}

	/**	 Setter Parameter Value for Shipper	*/
	protected void setShipperId(int shipperId) {
		this.shipperId = shipperId;
	}

	/**	 Getter Parameter Value for Locator	*/
	protected int getLocatorId() {
		return locatorId;
	}

	/**	 Setter Parameter Value for Locator	*/
	protected void setLocatorId(int locatorId) {
		this.locatorId = locatorId;
	}

	/**	 Getter Parameter Value for Locator To	*/
	protected int getLocatorToId() {
		return locatorToId;
	}

	/**	 Setter Parameter Value for Locator To	*/
	protected void setLocatorToId(int locatorToId) {
		this.locatorToId = locatorToId;
	}

	/**	 Getter Parameter Value for Resource (Plant)	*/
	protected int getResourceId() {
		return resourceId;
	}

	/**	 Setter Parameter Value for Resource (Plant)	*/
	protected void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	/**	 Getter Parameter Value for BOM & Formula	*/
	protected int getProductBOMId() {
		return productBOMId;
	}

	/**	 Setter Parameter Value for BOM & Formula	*/
	protected void setProductBOMId(int productBOMId) {
		this.productBOMId = productBOMId;
	}

	/**	 Getter Parameter Value for Workflow	*/
	protected int getWorkflowId() {
		return workflowId;
	}

	/**	 Setter Parameter Value for Workflow	*/
	protected void setWorkflowId(int workflowId) {
		this.workflowId = workflowId;
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