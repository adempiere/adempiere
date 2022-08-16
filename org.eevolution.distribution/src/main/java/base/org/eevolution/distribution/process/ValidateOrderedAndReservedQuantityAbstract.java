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

package org.eevolution.distribution.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Validate Ordered and Reserved Quantity)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class ValidateOrderedAndReservedQuantityAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "M_Storage Validate Reserved Quantity";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Validate Ordered and Reserved Quantity";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54470;
	/**	Parameter Name for Organization	*/
	public static final String AD_ORG_ID = "AD_Org_ID";
	/**	Parameter Name for Warehouse	*/
	public static final String M_WAREHOUSE_ID = "M_Warehouse_ID";
	/**	Parameter Name for Product Category	*/
	public static final String M_PRODUCT_CATEGORY_ID = "M_Product_Category_ID";
	/**	Parameter Name for Product Group	*/
	public static final String M_PRODUCT_GROUP_ID = "M_Product_Group_ID";
	/**	Parameter Name for Product Class	*/
	public static final String M_PRODUCT_CLASS_ID = "M_Product_Class_ID";
	/**	Parameter Name for Product Classification	*/
	public static final String M_PRODUCT_CLASSIFICATION_ID = "M_Product_Classification_ID";
	/**	Parameter Name for Product	*/
	public static final String M_PRODUCT_ID = "M_Product_ID";
	/**	Parameter Value for Organization	*/
	private int orgId;
	/**	Parameter Value for Warehouse	*/
	private int warehouseId;
	/**	Parameter Value for Product Category	*/
	private int productCategoryId;
	/**	Parameter Value for Product Group	*/
	private int productGroupId;
	/**	Parameter Value for Product Class	*/
	private int productClassId;
	/**	Parameter Value for Product Classification	*/
	private int productClassificationId;
	/**	Parameter Value for Product	*/
	private int productId;

	@Override
	protected void prepare() {
		orgId = getParameterAsInt(AD_ORG_ID);
		warehouseId = getParameterAsInt(M_WAREHOUSE_ID);
		productCategoryId = getParameterAsInt(M_PRODUCT_CATEGORY_ID);
		productGroupId = getParameterAsInt(M_PRODUCT_GROUP_ID);
		productClassId = getParameterAsInt(M_PRODUCT_CLASS_ID);
		productClassificationId = getParameterAsInt(M_PRODUCT_CLASSIFICATION_ID);
		productId = getParameterAsInt(M_PRODUCT_ID);
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

	/**	 Getter Parameter Value for Product	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Setter Parameter Value for Product	*/
	protected void setProductId(int productId) {
		this.productId = productId;
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