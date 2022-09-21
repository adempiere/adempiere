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

/** Generated Process for (Copy Cost Type to other Cost Type)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class CopyCostTypeToCostTypeAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "PP_Copy Cost Type to Cost Type";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Copy Cost Type to other Cost Type";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53887;
 
	/**	Parameter Name for Accounting Schema	*/
	private static final String C_ACCTSCHEMA_ID = "C_AcctSchema_ID";
	/**	Parameter Name for Organization	*/
	private static final String AD_ORG_ID = "AD_Org_ID";
	/**	Parameter Name for Warehouse	*/
	private static final String M_WAREHOUSE_ID = "M_Warehouse_ID";
	/**	Parameter Name for Cost Type	*/
	private static final String M_COSTTYPE_ID = "M_CostType_ID";
	/**	Parameter Name for Cost Element	*/
	private static final String M_COSTELEMENT_ID = "M_CostElement_ID";
	/**	Parameter Name for Product	*/
	private static final String M_PRODUCT_ID = "M_Product_ID";
	/**	Parameter Name for Product Category	*/
	private static final String M_PRODUCT_CATEGORY_ID = "M_Product_Category_ID";
	/**	Parameter Name for Product Group	*/
	private static final String M_PRODUCT_GROUP_ID = "M_Product_Group_ID";
	/**	Parameter Name for Product Class	*/
	private static final String M_PRODUCT_CLASS_ID = "M_Product_Class_ID";
	/**	Parameter Name for Product Classification	*/
	private static final String M_PRODUCT_CLASSIFICATION_ID = "M_Product_Classification_ID";
	/**	Parameter Name for Update Costing	*/
	private static final String ISUPDATECOSTING = "IsUpdateCosting";

	/**	Parameter Value for Accounting Schema	*/
	private int acctSchemaId;
	/**	Parameter Value for Organization	*/
	private int orgId;
	/**	Parameter Value for Warehouse	*/
	private int warehouseId;
	/**	Parameter Value for Cost Type	*/
	private int costTypeId;
	/**	Parameter Value for Cost Type(To)	*/
	private int costTypeIdTo;
	/**	Parameter Value for Cost Element	*/
	private int costElementId;
	/**	Parameter Value for Cost Element(To)	*/
	private int costElementIdTo;
	/**	Parameter Value for Product	*/
	private int productId;
	/**	Parameter Value for Product Category	*/
	private int productCategoryId;
	/**	Parameter Value for Product Group	*/
	private int productGroupId;
	/**	Parameter Value for Product Class	*/
	private int productClassId;
	/**	Parameter Value for Product Classification	*/
	private int productClassificationId;
	/**	Parameter Value for Update Costing	*/
	private boolean isUpdateCosting;
 

	@Override
	protected void prepare() {
		acctSchemaId = getParameterAsInt(C_ACCTSCHEMA_ID);
		orgId = getParameterAsInt(AD_ORG_ID);
		warehouseId = getParameterAsInt(M_WAREHOUSE_ID);
		costTypeId = getParameterAsInt(M_COSTTYPE_ID);
		costTypeIdTo = getParameterToAsInt(M_COSTTYPE_ID);
		costElementId = getParameterAsInt(M_COSTELEMENT_ID);
		costElementIdTo = getParameterToAsInt(M_COSTELEMENT_ID);
		productId = getParameterAsInt(M_PRODUCT_ID);
		productCategoryId = getParameterAsInt(M_PRODUCT_CATEGORY_ID);
		productGroupId = getParameterAsInt(M_PRODUCT_GROUP_ID);
		productClassId = getParameterAsInt(M_PRODUCT_CLASS_ID);
		productClassificationId = getParameterAsInt(M_PRODUCT_CLASSIFICATION_ID);
		isUpdateCosting = getParameterAsBoolean(ISUPDATECOSTING);
	}

	/**	 Getter Parameter Value for Accounting Schema	*/
	protected int getAcctSchemaId() {
		return acctSchemaId;
	}

	/**	 Setter Parameter Value for Accounting Schema	*/
	protected void setAcctSchemaId(int acctSchemaId) {
		this.acctSchemaId = acctSchemaId;
	}

	/**	 Getter Parameter Value for Organization	*/
	protected int getOrgId() {
		return orgId;
	}

	/**	 Setter Parameter Value for Organization	*/
	protected void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	/**	 Getter Parameter Value for Warehouse	*/
	protected int getWarehouseId() {
		return warehouseId;
	}

	/**	 Setter Parameter Value for Warehouse	*/
	protected void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**	 Getter Parameter Value for Cost Type	*/
	protected int getCostTypeId() {
		return costTypeId;
	}

	/**	 Setter Parameter Value for Cost Type	*/
	protected void setCostTypeId(int costTypeId) {
		this.costTypeId = costTypeId;
	}

	/**	 Getter Parameter Value for Cost Type(To)	*/
	protected int getCostTypeIdTo() {
		return costTypeIdTo;
	}

	/**	 Setter Parameter Value for Cost Type(To)	*/
	protected void setCostTypeIdTo(int costTypeIdTo) {
		this.costTypeIdTo = costTypeIdTo;
	}

	/**	 Getter Parameter Value for Cost Element	*/
	protected int getCostElementId() {
		return costElementId;
	}

	/**	 Setter Parameter Value for Cost Element	*/
	protected void setCostElementId(int costElementId) {
		this.costElementId = costElementId;
	}

	/**	 Getter Parameter Value for Cost Element(To)	*/
	protected int getCostElementIdTo() {
		return costElementIdTo;
	}

	/**	 Setter Parameter Value for Cost Element(To)	*/
	protected void setCostElementIdTo(int costElementIdTo) {
		this.costElementIdTo = costElementIdTo;
	}

	/**	 Getter Parameter Value for Product	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Setter Parameter Value for Product	*/
	protected void setProductId(int productId) {
		this.productId = productId;
	}

	/**	 Getter Parameter Value for Product Category	*/
	protected int getProductCategoryId() {
		return productCategoryId;
	}

	/**	 Setter Parameter Value for Product Category	*/
	protected void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	/**	 Getter Parameter Value for Product Group	*/
	protected int getProductGroupId() {
		return productGroupId;
	}

	/**	 Setter Parameter Value for Product Group	*/
	protected void setProductGroupId(int productGroupId) {
		this.productGroupId = productGroupId;
	}

	/**	 Getter Parameter Value for Product Class	*/
	protected int getProductClassId() {
		return productClassId;
	}

	/**	 Setter Parameter Value for Product Class	*/
	protected void setProductClassId(int productClassId) {
		this.productClassId = productClassId;
	}

	/**	 Getter Parameter Value for Product Classification	*/
	protected int getProductClassificationId() {
		return productClassificationId;
	}

	/**	 Setter Parameter Value for Product Classification	*/
	protected void setProductClassificationId(int productClassificationId) {
		this.productClassificationId = productClassificationId;
	}

	/**	 Getter Parameter Value for Update Costing	*/
	protected boolean isUpdateCosting() {
		return isUpdateCosting;
	}

	/**	 Setter Parameter Value for Update Costing	*/
	protected void setIsUpdateCosting(boolean isUpdateCosting) {
		this.isUpdateCosting = isUpdateCosting;
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