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

package org.eevolution.report;

import org.compiere.process.SvrProcess;
/** Generated Process for (Cost BOM Multi Level Review)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class CostBillOfMaterialAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "PP_CostBillOfMaterial";
	/** Process Name 	*/
	private static final String NAME = "Cost BOM Multi Level Review";
	/** Process Id 	*/
	private static final int ID = 53159;
 
	/**	Parameter Name for AD_Org_ID	*/
	public static final String AD_Org_ID = "AD_Org_ID";
	/**	Parameter Name for C_AcctSchema_ID	*/
	public static final String C_AcctSchema_ID = "C_AcctSchema_ID";
	/**	Parameter Name for M_Warehouse_ID	*/
	public static final String M_Warehouse_ID = "M_Warehouse_ID";
	/**	Parameter Name for M_CostType_ID	*/
	public static final String M_CostType_ID = "M_CostType_ID";
	/**	Parameter Name for CostingMethod	*/
	public static final String CostingMethod = "CostingMethod";
	/**	Parameter Name for M_Product_ID	*/
	public static final String M_Product_ID = "M_Product_ID";

	/**	Parameter Value for organizationId	*/
	private int organizationId;
	/**	Parameter Value for accountingSchemaId	*/
	private int accountingSchemaId;
	/**	Parameter Value for warehouseId	*/
	private int warehouseId;
	/**	Parameter Value for costTypeId	*/
	private int costTypeId;
	/**	Parameter Value for costingMethod	*/
	private String costingMethod;
	/**	Parameter Value for productId	*/
	private int productId;
 

	@Override
	protected void prepare()
	{
		organizationId = getParameterAsInt(AD_Org_ID);
		accountingSchemaId = getParameterAsInt(C_AcctSchema_ID);
		warehouseId = getParameterAsInt(M_Warehouse_ID);
		costTypeId = getParameterAsInt(M_CostType_ID);
		costingMethod = getParameterAsString(CostingMethod);
		productId = getParameterAsInt(M_Product_ID);
	}

	/**	 Getter Parameter Value for organizationId	*/
	protected int getOrganizationId() {
		return organizationId;
	}

	/**	 Getter Parameter Value for accountingSchemaId	*/
	protected int getAccountingSchemaId() {
		return accountingSchemaId;
	}

	/**	 Getter Parameter Value for warehouseId	*/
	protected int getWarehouseId() {
		return warehouseId;
	}

	/**	 Getter Parameter Value for costTypeId	*/
	protected int getCostTypeId() {
		return costTypeId;
	}

	/**	 Getter Parameter Value for costingMethod	*/
	protected String getCostingMethod() {
		return costingMethod;
	}

	/**	 Getter Parameter Value for productId	*/
	protected int getProductId() {
		return productId;
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