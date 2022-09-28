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

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Generate Sales History)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.2
 */
public abstract class GenerateSalesHistoryAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "C_SalesHistory_Generate";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Generate Sales History";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53302;
	/**	Parameter Name for Organization	*/
	public static final String AD_ORG_ID = "AD_Org_ID";
	/**	Parameter Name for Date Invoiced	*/
	public static final String DATEINVOICED = "DateInvoiced";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Business Partner Group	*/
	public static final String C_BP_GROUP_ID = "C_BP_Group_ID";
	/**	Parameter Name for Partner Location	*/
	public static final String C_BPARTNER_LOCATION_ID = "C_BPartner_Location_ID";
	/**	Parameter Name for Sales Representative	*/
	public static final String SALESREP_ID = "SalesRep_ID";
	/**	Parameter Name for Product	*/
	public static final String M_PRODUCT_ID = "M_Product_ID";
	/**	Parameter Name for Product Category	*/
	public static final String M_PRODUCT_CATEGORY_ID = "M_Product_Category_ID";
	/**	Parameter Name for Product Classification	*/
	public static final String M_PRODUCT_CLASSIFICATION_ID = "M_Product_Classification_ID";
	/**	Parameter Name for Product Class	*/
	public static final String M_PRODUCT_CLASS_ID = "M_Product_Class_ID";
	/**	Parameter Name for Product Group	*/
	public static final String M_PRODUCT_GROUP_ID = "M_Product_Group_ID";
	/**	Parameter Name for Warehouse	*/
	public static final String M_WAREHOUSE_ID = "M_Warehouse_ID";
	/**	Parameter Name for Campaign	*/
	public static final String C_CAMPAIGN_ID = "C_Campaign_ID";
	/**	Parameter Name for Sales Region	*/
	public static final String C_SALESREGION_ID = "C_SalesRegion_ID";
	/**	Parameter Name for Project	*/
	public static final String C_PROJECT_ID = "C_Project_ID";
	/**	Parameter Name for Activity	*/
	public static final String C_ACTIVITY_ID = "C_Activity_ID";
	/**	Parameter Name for User List 1	*/
	public static final String USER1_ID = "User1_ID";
	/**	Parameter Name for User List 2	*/
	public static final String USER2_ID = "User2_ID";
	/**	Parameter Name for User List 3	*/
	public static final String USER3_ID = "User3_ID";
	/**	Parameter Name for User List 4	*/
	public static final String USER4_ID = "User4_ID";
	/**	Parameter Value for Organization	*/
	private int orgId;
	/**	Parameter Value for Date Invoiced	*/
	private Timestamp dateInvoiced;
	/**	Parameter Value for Date Invoiced(To)	*/
	private Timestamp dateInvoicedTo;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Business Partner Group	*/
	private int bPGroupId;
	/**	Parameter Value for Partner Location	*/
	private int bPartnerLocationId;
	/**	Parameter Value for Sales Representative	*/
	private int salesRepId;
	/**	Parameter Value for Product	*/
	private int productId;
	/**	Parameter Value for Product Category	*/
	private int productCategoryId;
	/**	Parameter Value for Product Classification	*/
	private int productClassificationId;
	/**	Parameter Value for Product Class	*/
	private int productClassId;
	/**	Parameter Value for Product Group	*/
	private int productGroupId;
	/**	Parameter Value for Warehouse	*/
	private int warehouseId;
	/**	Parameter Value for Campaign	*/
	private int campaignId;
	/**	Parameter Value for Sales Region	*/
	private int salesRegionId;
	/**	Parameter Value for Project	*/
	private int projectId;
	/**	Parameter Value for Activity	*/
	private int activityId;
	/**	Parameter Value for User List 1	*/
	private int user1Id;
	/**	Parameter Value for User List 2	*/
	private int user2Id;
	/**	Parameter Value for User List 3	*/
	private int user3Id;
	/**	Parameter Value for User List 4	*/
	private int user4Id;

	@Override
	protected void prepare() {
		orgId = getParameterAsInt(AD_ORG_ID);
		dateInvoiced = getParameterAsTimestamp(DATEINVOICED);
		dateInvoicedTo = getParameterToAsTimestamp(DATEINVOICED);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		bPGroupId = getParameterAsInt(C_BP_GROUP_ID);
		bPartnerLocationId = getParameterAsInt(C_BPARTNER_LOCATION_ID);
		salesRepId = getParameterAsInt(SALESREP_ID);
		productId = getParameterAsInt(M_PRODUCT_ID);
		productCategoryId = getParameterAsInt(M_PRODUCT_CATEGORY_ID);
		productClassificationId = getParameterAsInt(M_PRODUCT_CLASSIFICATION_ID);
		productClassId = getParameterAsInt(M_PRODUCT_CLASS_ID);
		productGroupId = getParameterAsInt(M_PRODUCT_GROUP_ID);
		warehouseId = getParameterAsInt(M_WAREHOUSE_ID);
		campaignId = getParameterAsInt(C_CAMPAIGN_ID);
		salesRegionId = getParameterAsInt(C_SALESREGION_ID);
		projectId = getParameterAsInt(C_PROJECT_ID);
		activityId = getParameterAsInt(C_ACTIVITY_ID);
		user1Id = getParameterAsInt(USER1_ID);
		user2Id = getParameterAsInt(USER2_ID);
		user3Id = getParameterAsInt(USER3_ID);
		user4Id = getParameterAsInt(USER4_ID);
	}

	/**	 Getter Parameter Value for Organization	*/
	protected int getOrgId() {
		return orgId;
	}

	/**	 Setter Parameter Value for Organization	*/
	protected void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	/**	 Getter Parameter Value for Date Invoiced	*/
	protected Timestamp getDateInvoiced() {
		return dateInvoiced;
	}

	/**	 Setter Parameter Value for Date Invoiced	*/
	protected void setDateInvoiced(Timestamp dateInvoiced) {
		this.dateInvoiced = dateInvoiced;
	}

	/**	 Getter Parameter Value for Date Invoiced(To)	*/
	protected Timestamp getDateInvoicedTo() {
		return dateInvoicedTo;
	}

	/**	 Setter Parameter Value for Date Invoiced(To)	*/
	protected void setDateInvoicedTo(Timestamp dateInvoicedTo) {
		this.dateInvoicedTo = dateInvoicedTo;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Business Partner Group	*/
	protected int getBPGroupId() {
		return bPGroupId;
	}

	/**	 Setter Parameter Value for Business Partner Group	*/
	protected void setBPGroupId(int bPGroupId) {
		this.bPGroupId = bPGroupId;
	}

	/**	 Getter Parameter Value for Partner Location	*/
	protected int getBPartnerLocationId() {
		return bPartnerLocationId;
	}

	/**	 Setter Parameter Value for Partner Location	*/
	protected void setBPartnerLocationId(int bPartnerLocationId) {
		this.bPartnerLocationId = bPartnerLocationId;
	}

	/**	 Getter Parameter Value for Sales Representative	*/
	protected int getSalesRepId() {
		return salesRepId;
	}

	/**	 Setter Parameter Value for Sales Representative	*/
	protected void setSalesRepId(int salesRepId) {
		this.salesRepId = salesRepId;
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

	/**	 Getter Parameter Value for Product Classification	*/
	protected int getProductClassificationId() {
		return productClassificationId;
	}

	/**	 Setter Parameter Value for Product Classification	*/
	protected void setProductClassificationId(int productClassificationId) {
		this.productClassificationId = productClassificationId;
	}

	/**	 Getter Parameter Value for Product Class	*/
	protected int getProductClassId() {
		return productClassId;
	}

	/**	 Setter Parameter Value for Product Class	*/
	protected void setProductClassId(int productClassId) {
		this.productClassId = productClassId;
	}

	/**	 Getter Parameter Value for Product Group	*/
	protected int getProductGroupId() {
		return productGroupId;
	}

	/**	 Setter Parameter Value for Product Group	*/
	protected void setProductGroupId(int productGroupId) {
		this.productGroupId = productGroupId;
	}

	/**	 Getter Parameter Value for Warehouse	*/
	protected int getWarehouseId() {
		return warehouseId;
	}

	/**	 Setter Parameter Value for Warehouse	*/
	protected void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**	 Getter Parameter Value for Campaign	*/
	protected int getCampaignId() {
		return campaignId;
	}

	/**	 Setter Parameter Value for Campaign	*/
	protected void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	/**	 Getter Parameter Value for Sales Region	*/
	protected int getSalesRegionId() {
		return salesRegionId;
	}

	/**	 Setter Parameter Value for Sales Region	*/
	protected void setSalesRegionId(int salesRegionId) {
		this.salesRegionId = salesRegionId;
	}

	/**	 Getter Parameter Value for Project	*/
	protected int getProjectId() {
		return projectId;
	}

	/**	 Setter Parameter Value for Project	*/
	protected void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**	 Getter Parameter Value for Activity	*/
	protected int getActivityId() {
		return activityId;
	}

	/**	 Setter Parameter Value for Activity	*/
	protected void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	/**	 Getter Parameter Value for User List 1	*/
	protected int getUser1Id() {
		return user1Id;
	}

	/**	 Setter Parameter Value for User List 1	*/
	protected void setUser1Id(int user1Id) {
		this.user1Id = user1Id;
	}

	/**	 Getter Parameter Value for User List 2	*/
	protected int getUser2Id() {
		return user2Id;
	}

	/**	 Setter Parameter Value for User List 2	*/
	protected void setUser2Id(int user2Id) {
		this.user2Id = user2Id;
	}

	/**	 Getter Parameter Value for User List 3	*/
	protected int getUser3Id() {
		return user3Id;
	}

	/**	 Setter Parameter Value for User List 3	*/
	protected void setUser3Id(int user3Id) {
		this.user3Id = user3Id;
	}

	/**	 Getter Parameter Value for User List 4	*/
	protected int getUser4Id() {
		return user4Id;
	}

	/**	 Setter Parameter Value for User List 4	*/
	protected void setUser4Id(int user4Id) {
		this.user4Id = user4Id;
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