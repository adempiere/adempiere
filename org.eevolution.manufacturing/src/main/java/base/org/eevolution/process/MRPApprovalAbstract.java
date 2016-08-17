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

import org.compiere.process.SvrProcess;
/** Generated Process for (Requisition Approval)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class MRPApprovalAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "MRP_Requisition_Approval";
	/** Process Name 	*/
	private static final String NAME = "Requisition Approval";
	/** Process Id 	*/
	private static final int ID = 53321;
 
	/**	Parameter Name for OrderType	*/
	public static final String OrderType = "OrderType";
	/**	Parameter Name for PriorityRule	*/
	public static final String PriorityRule = "PriorityRule";
	/**	Parameter Name for C_BPartner_ID	*/
	public static final String C_BPartner_ID = "C_BPartner_ID";
	/**	Parameter Name for ReferenceNo	*/
	public static final String ReferenceNo = "ReferenceNo";
	/**	Parameter Name for M_Warehouse_ID	*/
	public static final String M_Warehouse_ID = "M_Warehouse_ID";
	/**	Parameter Name for M_Shipper_ID	*/
	public static final String M_Shipper_ID = "M_Shipper_ID";
	/**	Parameter Name for M_Locator_ID	*/
	public static final String M_Locator_ID = "M_Locator_ID";
	/**	Parameter Name for M_LocatorTo_ID	*/
	public static final String M_LocatorTo_ID = "M_LocatorTo_ID";
	/**	Parameter Name for S_Resource_ID	*/
	public static final String S_Resource_ID = "S_Resource_ID";
	/**	Parameter Name for PP_Product_BOM_ID	*/
	public static final String PP_Product_BOM_ID = "PP_Product_BOM_ID";
	/**	Parameter Name for AD_Workflow_ID	*/
	public static final String AD_Workflow_ID = "AD_Workflow_ID";

	/**	Parameter Value for orderType	*/
	private String orderType;
	/**	Parameter Value for priority	*/
	private String priority;
	/**	Parameter Value for businessPartnerId	*/
	private int businessPartnerId;
	/**	Parameter Value for referenceNo	*/
	private String referenceNo;
	/**	Parameter Value for warehouseinTransitId	*/
	private int warehouseinTransitId;
	/**	Parameter Value for shipperId	*/
	private int shipperId;
	/**	Parameter Value for locatorId	*/
	private int locatorId;
	/**	Parameter Value for locatorToId	*/
	private int locatorToId;
	/**	Parameter Value for resource(Plant)Id	*/
	private int resourcePlantId;
	/**	Parameter Value for bOM&FormulaId	*/
	private int bOMFormulaId;
	/**	Parameter Value for workflowId	*/
	private int workflowId;
 

	@Override
	protected void prepare()
	{
		orderType = getParameterAsString(OrderType);
		priority = getParameterAsString(PriorityRule);
		businessPartnerId = getParameterAsInt(C_BPartner_ID);
		referenceNo = getParameterAsString(ReferenceNo);
		warehouseinTransitId = getParameterAsInt(M_Warehouse_ID);
		shipperId = getParameterAsInt(M_Shipper_ID);
		locatorId = getParameterAsInt(M_Locator_ID);
		locatorToId = getParameterAsInt(M_LocatorTo_ID);
		resourcePlantId = getParameterAsInt(S_Resource_ID);
		bOMFormulaId = getParameterAsInt(PP_Product_BOM_ID);
		workflowId = getParameterAsInt(AD_Workflow_ID);
	}

	/**	 Getter Parameter Value for orderType	*/
	protected String getOrderType() {
		return orderType;
	}

	/**	 Getter Parameter Value for priority	*/
	protected String getPriority() {
		return priority;
	}

	/**	 Getter Parameter Value for businessPartnerId	*/
	protected int getBusinessPartnerId() {
		return businessPartnerId;
	}

	/**	 Getter Parameter Value for referenceNo	*/
	protected String getReferenceNo() {
		return referenceNo;
	}

	/**	 Getter Parameter Value for warehouseinTransitId	*/
	protected int getWarehouseinTransitId() {
		return warehouseinTransitId;
	}

	/**	 Getter Parameter Value for shipperId	*/
	protected int getShipperId() {
		return shipperId;
	}

	/**	 Getter Parameter Value for locatorId	*/
	protected int getLocatorId() {
		return locatorId;
	}

	/**	 Getter Parameter Value for locatorToId	*/
	protected int getLocatorToId() {
		return locatorToId;
	}

	/**	 Getter Parameter Value for resource(Plant)Id	*/
	protected int getResourcePlantId() {
		return resourcePlantId;
	}

	/**	 Getter Parameter Value for bOM&FormulaId	*/
	protected int getBOMFormulaId() {
		return bOMFormulaId;
	}

	/**	 Getter Parameter Value for workflowId	*/
	protected int getWorkflowId() {
		return workflowId;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME;
	}
}