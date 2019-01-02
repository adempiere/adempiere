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

package org.compiere.report;

import org.compiere.process.SvrProcess;

/** Generated Process for (Create Report)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class FinReportAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "FinReport";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Create Report";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 202;
	/**	Parameter Name for Period	*/
	public static final String C_PERIOD_ID = "C_Period_ID";
	/**	Parameter Name for Organization	*/
	public static final String ORG_ID = "Org_ID";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Product	*/
	public static final String M_PRODUCT_ID = "M_Product_ID";
	/**	Parameter Name for Project	*/
	public static final String C_PROJECT_ID = "C_Project_ID";
	/**	Parameter Name for Activity	*/
	public static final String C_ACTIVITY_ID = "C_Activity_ID";
	/**	Parameter Name for Sales Region	*/
	public static final String C_SALESREGION_ID = "C_SalesRegion_ID";
	/**	Parameter Name for Campaign	*/
	public static final String C_CAMPAIGN_ID = "C_Campaign_ID";
	/**	Parameter Name for User List 1	*/
	public static final String USER1_ID = "User1_ID";
	/**	Parameter Name for User List 2	*/
	public static final String USER2_ID = "User2_ID";
	/**	Parameter Name for User List 3	*/
	public static final String USER3_ID = "User3_ID";
	/**	Parameter Name for User List 4	*/
	public static final String USER4_ID = "User4_ID";
	/**	Parameter Name for User Element 1	*/
	public static final String USERELEMENT1_ID = "UserElement1_ID";
	/**	Parameter Name for User Element 2	*/
	public static final String USERELEMENT2_ID = "UserElement2_ID";
	/**	Parameter Name for Posting Type	*/
	public static final String POSTINGTYPE = "PostingType";
	/**	Parameter Name for Budget	*/
	public static final String GL_BUDGET_ID = "GL_Budget_ID";
	/**	Parameter Name for List Sources	*/
	public static final String LISTSOURCES = "ListSources";
	/**	Parameter Name for List Transactions	*/
	public static final String LISTTRX = "ListTrx";
	/**	Parameter Name for Details/Source First	*/
	public static final String DETAILSSOURCEFIRST = "DetailsSourceFirst";
	/**	Parameter Name for Reporting Hierarchy	*/
	public static final String PA_HIERARCHY_ID = "PA_Hierarchy_ID";
	/**	Parameter Name for Report Cube	*/
	public static final String PA_REPORTCUBE_ID = "PA_ReportCube_ID";
	/**	Parameter Value for Period	*/
	private int periodId;
	/**	Parameter Value for Organization	*/
	private int orgId;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Product	*/
	private int productId;
	/**	Parameter Value for Project	*/
	private int projectId;
	/**	Parameter Value for Activity	*/
	private int activityId;
	/**	Parameter Value for Sales Region	*/
	private int salesRegionId;
	/**	Parameter Value for Campaign	*/
	private int campaignId;
	/**	Parameter Value for User List 1	*/
	private int user1Id;
	/**	Parameter Value for User List 2	*/
	private int user2Id;
	/**	Parameter Value for User List 3	*/
	private int user3Id;
	/**	Parameter Value for User List 4	*/
	private int user4Id;
	/**	Parameter Value for User Element 1	*/
	private int userElement1Id;
	/**	Parameter Value for User Element 2	*/
	private int userElement2Id;
	/**	Parameter Value for Posting Type	*/
	private String postingType;
	/**	Parameter Value for Budget	*/
	private int budgetId;
	/**	Parameter Value for List Sources	*/
	private String listSources;
	/**	Parameter Value for List Transactions	*/
	private String listTrx;
	/**	Parameter Value for Details/Source First	*/
	private boolean isDetailsSourceFirst;
	/**	Parameter Value for Reporting Hierarchy	*/
	private int hierarchyId;
	/**	Parameter Value for Report Cube	*/
	private int reportCubeId;

	@Override
	protected void prepare() {
		periodId = getParameterAsInt(C_PERIOD_ID);
		orgId = getParameterAsInt(ORG_ID);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		productId = getParameterAsInt(M_PRODUCT_ID);
		projectId = getParameterAsInt(C_PROJECT_ID);
		activityId = getParameterAsInt(C_ACTIVITY_ID);
		salesRegionId = getParameterAsInt(C_SALESREGION_ID);
		campaignId = getParameterAsInt(C_CAMPAIGN_ID);
		user1Id = getParameterAsInt(USER1_ID);
		user2Id = getParameterAsInt(USER2_ID);
		user3Id = getParameterAsInt(USER3_ID);
		user4Id = getParameterAsInt(USER4_ID);
		userElement1Id = getParameterAsInt(USERELEMENT1_ID);
		userElement2Id = getParameterAsInt(USERELEMENT2_ID);
		postingType = getParameterAsString(POSTINGTYPE);
		budgetId = getParameterAsInt(GL_BUDGET_ID);
		listSources = getParameterAsString(LISTSOURCES);
		listTrx = getParameterAsString(LISTTRX);
		isDetailsSourceFirst = getParameterAsBoolean(DETAILSSOURCEFIRST);
		hierarchyId = getParameterAsInt(PA_HIERARCHY_ID);
		reportCubeId = getParameterAsInt(PA_REPORTCUBE_ID);
	}

	/**	 Getter Parameter Value for Period	*/
	protected int getPeriodId() {
		return periodId;
	}

	/**	 Setter Parameter Value for Period	*/
	protected void setPeriodId(int periodId) {
		this.periodId = periodId;
	}

	/**	 Getter Parameter Value for Organization	*/
	protected int getOrgId() {
		return orgId;
	}

	/**	 Setter Parameter Value for Organization	*/
	protected void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Product	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Setter Parameter Value for Product	*/
	protected void setProductId(int productId) {
		this.productId = productId;
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

	/**	 Getter Parameter Value for Sales Region	*/
	protected int getSalesRegionId() {
		return salesRegionId;
	}

	/**	 Setter Parameter Value for Sales Region	*/
	protected void setSalesRegionId(int salesRegionId) {
		this.salesRegionId = salesRegionId;
	}

	/**	 Getter Parameter Value for Campaign	*/
	protected int getCampaignId() {
		return campaignId;
	}

	/**	 Setter Parameter Value for Campaign	*/
	protected void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
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

	/**	 Getter Parameter Value for User Element 1	*/
	protected int getUserElement1Id() {
		return userElement1Id;
	}

	/**	 Setter Parameter Value for User Element 1	*/
	protected void setUserElement1Id(int userElement1Id) {
		this.userElement1Id = userElement1Id;
	}

	/**	 Getter Parameter Value for User Element 2	*/
	protected int getUserElement2Id() {
		return userElement2Id;
	}

	/**	 Setter Parameter Value for User Element 2	*/
	protected void setUserElement2Id(int userElement2Id) {
		this.userElement2Id = userElement2Id;
	}

	/**	 Getter Parameter Value for Posting Type	*/
	protected String getPostingType() {
		return postingType;
	}

	/**	 Setter Parameter Value for Posting Type	*/
	protected void setPostingType(String postingType) {
		this.postingType = postingType;
	}

	/**	 Getter Parameter Value for Budget	*/
	protected int getBudgetId() {
		return budgetId;
	}

	/**	 Setter Parameter Value for Budget	*/
	protected void setBudgetId(int budgetId) {
		this.budgetId = budgetId;
	}

	/**	 Getter Parameter Value for List Sources	*/
	protected String getListSources() {
		return listSources;
	}

	/**	 Setter Parameter Value for List Sources	*/
	protected void setListSources(String listSources) {
		this.listSources = listSources;
	}

	/**	 Getter Parameter Value for List Transactions	*/
	protected String getListTrx() {
		return listTrx;
	}

	/**	 Setter Parameter Value for List Transactions	*/
	protected void setListTrx(String listTrx) {
		this.listTrx = listTrx;
	}

	/**	 Getter Parameter Value for Details/Source First	*/
	protected boolean isDetailsSourceFirst() {
		return isDetailsSourceFirst;
	}

	/**	 Setter Parameter Value for Details/Source First	*/
	protected void setDetailsSourceFirst(boolean isDetailsSourceFirst) {
		this.isDetailsSourceFirst = isDetailsSourceFirst;
	}

	/**	 Getter Parameter Value for Reporting Hierarchy	*/
	protected int getHierarchyId() {
		return hierarchyId;
	}

	/**	 Setter Parameter Value for Reporting Hierarchy	*/
	protected void setHierarchyId(int hierarchyId) {
		this.hierarchyId = hierarchyId;
	}

	/**	 Getter Parameter Value for Report Cube	*/
	protected int getReportCubeId() {
		return reportCubeId;
	}

	/**	 Setter Parameter Value for Report Cube	*/
	protected void setReportCubeId(int reportCubeId) {
		this.reportCubeId = reportCubeId;
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