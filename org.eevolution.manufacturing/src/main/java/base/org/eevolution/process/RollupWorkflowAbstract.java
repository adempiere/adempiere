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
/** Generated Process for (Workflow Cost Roll-Up)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class RollupWorkflowAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "PP_Workflow Cost Roll-Up";
	/** Process Name 	*/
	private static final String NAME = "Workflow Cost Roll-Up";
	/** Process Id 	*/
	private static final int ID = 53058;
 
	/**	Parameter Name for C_AcctSchema_ID	*/
	public static final String C_AcctSchema_ID = "C_AcctSchema_ID";
	/**	Parameter Name for M_CostType_ID	*/
	public static final String M_CostType_ID = "M_CostType_ID";
	/**	Parameter Name for M_CostElement_ID	*/
	public static final String M_CostElement_ID = "M_CostElement_ID";
	/**	Parameter Name for AD_Org_ID	*/
	public static final String AD_Org_ID = "AD_Org_ID";
	/**	Parameter Name for M_Warehouse_ID	*/
	public static final String M_Warehouse_ID = "M_Warehouse_ID";
	/**	Parameter Name for S_Resource_ID	*/
	public static final String S_Resource_ID = "S_Resource_ID";
	/**	Parameter Name for M_Product_ID	*/
	public static final String M_Product_ID = "M_Product_ID";
	/**	Parameter Name for M_Product_Category_ID	*/
	public static final String M_Product_Category_ID = "M_Product_Category_ID";
	/**	Parameter Name for M_Product_Class_ID	*/
	public static final String M_Product_Class_ID = "M_Product_Class_ID";
	/**	Parameter Name for M_Product_Group_ID	*/
	public static final String M_Product_Group_ID = "M_Product_Group_ID";
	/**	Parameter Name for M_Product_Classification_ID	*/
	public static final String M_Product_Classification_ID = "M_Product_Classification_ID";

	/**	Parameter Value for accountingSchemaId	*/
	private int accountingSchemaId;
	/**	Parameter Value for costTypeId	*/
	private int costTypeId;
	/**	Parameter Value for costElementId	*/
	private int costElementId;
	/**	Parameter Value for organizationId	*/
	private int organizationId;
	/**	Parameter Value for warehouseId	*/
	private int warehouseId;
	/**	Parameter Value for resourcePlantId	*/
	private int resourcePlantId;
	/**	Parameter Value for productId	*/
	private int productId;
	/**	Parameter Value for productCategoryId	*/
	private int productCategoryId;
	/**	Parameter Value for productClassId	*/
	private int productClassId;
	/**	Parameter Value for productGroupId	*/
	private int productGroupId;
	/**	Parameter Value for productClassificationId	*/
	private int productClassificationId;
 

	@Override
	protected void prepare()
	{
		accountingSchemaId = getParameterAsInt(C_AcctSchema_ID);
		costTypeId = getParameterAsInt(M_CostType_ID);
		costElementId = getParameterAsInt(M_CostElement_ID);
		organizationId = getParameterAsInt(AD_Org_ID);
		warehouseId = getParameterAsInt(M_Warehouse_ID);
		resourcePlantId = getParameterAsInt(S_Resource_ID);
		productId = getParameterAsInt(M_Product_ID);
		productCategoryId = getParameterAsInt(M_Product_Category_ID);
		productClassId = getParameterAsInt(M_Product_Class_ID);
		productGroupId = getParameterAsInt(M_Product_Group_ID);
		productClassificationId = getParameterAsInt(M_Product_Classification_ID);
	}

	/**	 Getter Parameter Value for accountingSchemaId	*/
	protected int getAccountingSchemaId() {
		return accountingSchemaId;
	}

	/**	 Getter Parameter Value for costTypeId	*/
	protected int getCostTypeId() {
		return costTypeId;
	}

	/**	 Getter Parameter Value for costElementId	*/
	protected int getCostElementId() {
		return costElementId;
	}

	/**	 Getter Parameter Value for organizationId	*/
	protected int getOrganizationId() {
		return organizationId;
	}

	/**	 Getter Parameter Value for warehouseId	*/
	protected int getWarehouseId() {
		return warehouseId;
	}

	/**	 Getter Parameter Value for resourcePlantId	*/
	protected int getResourcePlantId() {
		return resourcePlantId;
	}

	/**	 Getter Parameter Value for productId	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Getter Parameter Value for productCategoryId	*/
	protected int getProductCategoryId() {
		return productCategoryId;
	}

	/**	 Getter Parameter Value for productClassId	*/
	protected int getProductClassId() {
		return productClassId;
	}

	/**	 Getter Parameter Value for productGroupId	*/
	protected int getProductGroupId() {
		return productGroupId;
	}

	/**	 Getter Parameter Value for productClassificationId	*/
	protected int getProductClassificationId() {
		return productClassificationId;
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