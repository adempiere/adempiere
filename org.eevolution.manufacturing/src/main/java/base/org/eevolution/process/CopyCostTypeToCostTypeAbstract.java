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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.process;

import org.compiere.process.SvrProcess;
/** Generated Process for (Copy Cost Type to other Cost Type)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class CopyCostTypeToCostTypeAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "PP_Copy Cost Type to Cost Type";
	/** Process Name 	*/
	private static final String NAME = "Copy Cost Type to other Cost Type";
	/** Process Id 	*/
	private static final int ID = 53887;
 
	/**	Parameter Name for C_AcctSchema_ID	*/
	public static final String C_AcctSchema_ID = "C_AcctSchema_ID";
	/**	Parameter Name for AD_Org_ID	*/
	public static final String AD_Org_ID = "AD_Org_ID";
	/**	Parameter Name for M_Warehouse_ID	*/
	public static final String M_Warehouse_ID = "M_Warehouse_ID";
	/**	Parameter Name for M_CostType_ID	*/
	public static final String M_CostType_ID = "M_CostType_ID";
	/**	Parameter Name for M_CostElement_ID	*/
	public static final String M_CostElement_ID = "M_CostElement_ID";
	/**	Parameter Name for M_Product_ID	*/
	public static final String M_Product_ID = "M_Product_ID";
	/**	Parameter Name for M_Product_Category_ID	*/
	public static final String M_Product_Category_ID = "M_Product_Category_ID";
	/**	Parameter Name for M_Product_Group_ID	*/
	public static final String M_Product_Group_ID = "M_Product_Group_ID";
	/**	Parameter Name for M_Product_Class_ID	*/
	public static final String M_Product_Class_ID = "M_Product_Class_ID";
	/**	Parameter Name for M_Product_Classification_ID	*/
	public static final String M_Product_Classification_ID = "M_Product_Classification_ID";
	/**	Parameter Name for IsUpdateCosting	*/
	public static final String IsUpdateCosting = "IsUpdateCosting";

	/**	Parameter Value for accountingSchemaId	*/
	private int accountingSchemaId;
	/**	Parameter Value for organizationId	*/
	private int organizationId;
	/**	Parameter Value for warehouseId	*/
	private int warehouseId;
	/**	Parameter Value for costTypeId	*/
	private int costTypeId;
	/**	Parameter Value for costTypeIdTo	*/
	private int costTypeIdTo;
	/**	Parameter Value for costElementId	*/
	private int costElementId;
	/**	Parameter Value for costElementIdTo	*/
	private int costElementIdTo;
	/**	Parameter Value for productId	*/
	private int productId;
	/**	Parameter Value for productCategoryId	*/
	private int productCategoryId;
	/**	Parameter Value for productGroupId	*/
	private int productGroupId;
	/**	Parameter Value for productClassId	*/
	private int productClassId;
	/**	Parameter Value for productClassificationId	*/
	private int productClassificationId;
	/**	Parameter Value for isdateCosting	*/
	private boolean isUpdateCosting;
 

	@Override
	protected void prepare()
	{
		accountingSchemaId = getParameterAsInt(C_AcctSchema_ID);
		organizationId = getParameterAsInt(AD_Org_ID);
		warehouseId = getParameterAsInt(M_Warehouse_ID);
		costTypeId = getParameterAsInt(M_CostType_ID);
		costTypeIdTo = getParameterToAsInt(M_CostType_ID);
		costElementId = getParameterAsInt(M_CostElement_ID);
		costElementIdTo = getParameterToAsInt(M_CostElement_ID);
		productId = getParameterAsInt(M_Product_ID);
		productCategoryId = getParameterAsInt(M_Product_Category_ID);
		productGroupId = getParameterAsInt(M_Product_Group_ID);
		productClassId = getParameterAsInt(M_Product_Class_ID);
		productClassificationId = getParameterAsInt(M_Product_Classification_ID);
		isUpdateCosting = getParameterAsBoolean(IsUpdateCosting);
	}

	/**	 Getter Parameter Value for accountingSchemaId	*/
	protected int getAccountingSchemaId() {
		return accountingSchemaId;
	}

	/**	 Getter Parameter Value for organizationId	*/
	protected int getOrganizationId() {
		return organizationId;
	}

	/**	 Getter Parameter Value for warehouseId	*/
	protected int getWarehouseId() {
		return warehouseId;
	}

	/**	 Getter Parameter Value for costTypeId	*/
	protected int getCostTypeId() {
		return costTypeId;
	}

	/**	 Getter Parameter Value for costTypeIdTo	*/
	protected int getCostTypeIdTo() {
		return costTypeIdTo;
	}

	/**	 Getter Parameter Value for costElementId	*/
	protected int getCostElementId() {
		return costElementId;
	}

	/**	 Getter Parameter Value for costElementIdTo	*/
	protected int getCostElementIdTo() {
		return costElementIdTo;
	}

	/**	 Getter Parameter Value for productId	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Getter Parameter Value for productCategoryId	*/
	protected int getProductCategoryId() {
		return productCategoryId;
	}

	/**	 Getter Parameter Value for productGroupId	*/
	protected int getProductGroupId() {
		return productGroupId;
	}

	/**	 Getter Parameter Value for productClassId	*/
	protected int getProductClassId() {
		return productClassId;
	}

	/**	 Getter Parameter Value for productClassificationId	*/
	protected int getProductClassificationId() {
		return productClassificationId;
	}

	/**	 Getter Parameter Value for isdateCosting	*/
	protected boolean isUpdateCosting() {
		return isUpdateCosting;
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