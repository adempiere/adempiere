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

/** Generated Process for (Create PO from Requisition)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class RequisitionPOCreateAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "M_Requisition_POCreate";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Create PO from Requisition";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 337;
	/**	Parameter Name for Requisition	*/
	public static final String M_REQUISITION_ID = "M_Requisition_ID";
	/**	Parameter Name for Organization	*/
	public static final String AD_ORG_ID = "AD_Org_ID";
	/**	Parameter Name for Warehouse	*/
	public static final String M_WAREHOUSE_ID = "M_Warehouse_ID";
	/**	Parameter Name for Document Date	*/
	public static final String DATEDOC = "DateDoc";
	/**	Parameter Name for Date Required	*/
	public static final String DATEREQUIRED = "DateRequired";
	/**	Parameter Name for Priority	*/
	public static final String PRIORITYRULE = "PriorityRule";
	/**	Parameter Name for User/Contact	*/
	public static final String AD_USER_ID = "AD_User_ID";
	/**	Parameter Name for Product	*/
	public static final String M_PRODUCT_ID = "M_Product_ID";
	/**	Parameter Name for Product Category	*/
	public static final String M_PRODUCT_CATEGORY_ID = "M_Product_Category_ID";
	/**	Parameter Name for Business Partner Group	*/
	public static final String C_BP_GROUP_ID = "C_BP_Group_ID";
	/**	Parameter Name for Consolidate to one Document	*/
	public static final String CONSOLIDATEDOCUMENT = "ConsolidateDocument";
	/**	Parameter Value for Requisition	*/
	private int requisitionId;
	/**	Parameter Value for Organization	*/
	private int orgId;
	/**	Parameter Value for Warehouse	*/
	private int warehouseId;
	/**	Parameter Value for Document Date	*/
	private Timestamp dateDoc;
	/**	Parameter Value for Document Date(To)	*/
	private Timestamp dateDocTo;
	/**	Parameter Value for Date Required	*/
	private Timestamp dateRequired;
	/**	Parameter Value for Date Required(To)	*/
	private Timestamp dateRequiredTo;
	/**	Parameter Value for Priority	*/
	private String priorityRule;
	/**	Parameter Value for User/Contact	*/
	private int userId;
	/**	Parameter Value for Product	*/
	private int productId;
	/**	Parameter Value for Product Category	*/
	private int productCategoryId;
	/**	Parameter Value for Business Partner Group	*/
	private int bPGroupId;
	/**	Parameter Value for Consolidate to one Document	*/
	private boolean isConsolidateDocument;

	@Override
	protected void prepare() {
		requisitionId = getParameterAsInt(M_REQUISITION_ID);
		orgId = getParameterAsInt(AD_ORG_ID);
		warehouseId = getParameterAsInt(M_WAREHOUSE_ID);
		dateDoc = getParameterAsTimestamp(DATEDOC);
		dateDocTo = getParameterToAsTimestamp(DATEDOC);
		dateRequired = getParameterAsTimestamp(DATEREQUIRED);
		dateRequiredTo = getParameterToAsTimestamp(DATEREQUIRED);
		priorityRule = getParameterAsString(PRIORITYRULE);
		userId = getParameterAsInt(AD_USER_ID);
		productId = getParameterAsInt(M_PRODUCT_ID);
		productCategoryId = getParameterAsInt(M_PRODUCT_CATEGORY_ID);
		bPGroupId = getParameterAsInt(C_BP_GROUP_ID);
		isConsolidateDocument = getParameterAsBoolean(CONSOLIDATEDOCUMENT);
	}

	/**	 Getter Parameter Value for Requisition	*/
	protected int getRequisitionId() {
		return requisitionId;
	}

	/**	 Setter Parameter Value for Requisition	*/
	protected void setRequisitionId(int requisitionId) {
		this.requisitionId = requisitionId;
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

	/**	 Getter Parameter Value for Document Date	*/
	protected Timestamp getDateDoc() {
		return dateDoc;
	}

	/**	 Setter Parameter Value for Document Date	*/
	protected void setDateDoc(Timestamp dateDoc) {
		this.dateDoc = dateDoc;
	}

	/**	 Getter Parameter Value for Document Date(To)	*/
	protected Timestamp getDateDocTo() {
		return dateDocTo;
	}

	/**	 Setter Parameter Value for Document Date(To)	*/
	protected void setDateDocTo(Timestamp dateDocTo) {
		this.dateDocTo = dateDocTo;
	}

	/**	 Getter Parameter Value for Date Required	*/
	protected Timestamp getDateRequired() {
		return dateRequired;
	}

	/**	 Setter Parameter Value for Date Required	*/
	protected void setDateRequired(Timestamp dateRequired) {
		this.dateRequired = dateRequired;
	}

	/**	 Getter Parameter Value for Date Required(To)	*/
	protected Timestamp getDateRequiredTo() {
		return dateRequiredTo;
	}

	/**	 Setter Parameter Value for Date Required(To)	*/
	protected void setDateRequiredTo(Timestamp dateRequiredTo) {
		this.dateRequiredTo = dateRequiredTo;
	}

	/**	 Getter Parameter Value for Priority	*/
	protected String getPriorityRule() {
		return priorityRule;
	}

	/**	 Setter Parameter Value for Priority	*/
	protected void setPriorityRule(String priorityRule) {
		this.priorityRule = priorityRule;
	}

	/**	 Getter Parameter Value for User/Contact	*/
	protected int getUserId() {
		return userId;
	}

	/**	 Setter Parameter Value for User/Contact	*/
	protected void setUserId(int userId) {
		this.userId = userId;
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

	/**	 Getter Parameter Value for Business Partner Group	*/
	protected int getBPGroupId() {
		return bPGroupId;
	}

	/**	 Setter Parameter Value for Business Partner Group	*/
	protected void setBPGroupId(int bPGroupId) {
		this.bPGroupId = bPGroupId;
	}

	/**	 Getter Parameter Value for Consolidate to one Document	*/
	protected boolean isConsolidateDocument() {
		return isConsolidateDocument;
	}

	/**	 Setter Parameter Value for Consolidate to one Document	*/
	protected void setConsolidateDocument(boolean isConsolidateDocument) {
		this.isConsolidateDocument = isConsolidateDocument;
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