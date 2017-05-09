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
public abstract class FinReportAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "FinReport";
	/** Process Name 	*/
	private static final String NAME = "Create Report";
	/** Process Id 	*/
	private static final int ID = 202;
 
	/**	Parameter Name for C_Period_ID	*/
	public static final String C_Period_ID = "C_Period_ID";
	/**	Parameter Name for Org_ID	*/
	public static final String Org_ID = "Org_ID";
	/**	Parameter Name for C_BPartner_ID	*/
	public static final String C_BPartner_ID = "C_BPartner_ID";
	/**	Parameter Name for M_Product_ID	*/
	public static final String M_Product_ID = "M_Product_ID";
	/**	Parameter Name for C_Project_ID	*/
	public static final String C_Project_ID = "C_Project_ID";
	/**	Parameter Name for C_Activity_ID	*/
	public static final String C_Activity_ID = "C_Activity_ID";
	/**	Parameter Name for C_SalesRegion_ID	*/
	public static final String C_SalesRegion_ID = "C_SalesRegion_ID";
	/**	Parameter Name for C_Campaign_ID	*/
	public static final String C_Campaign_ID = "C_Campaign_ID";
	/**	Parameter Name for User1_ID	*/
	public static final String User1_ID = "User1_ID";
	/**	Parameter Name for User2_ID	*/
	public static final String User2_ID = "User2_ID";
	/**	Parameter Name for User3_ID	*/
	public static final String User3_ID = "User3_ID";
	/**	Parameter Name for User4_ID	*/
	public static final String User4_ID = "User4_ID";
	/**	Parameter Name for UserElement1_ID	*/
	public static final String UserElement1_ID = "UserElement1_ID";
	/**	Parameter Name for UserElement2_ID	*/
	public static final String UserElement2_ID = "UserElement2_ID";
	/**	Parameter Name for DetailsSourceFirst	*/
	public static final String DetailsSourceFirst = "DetailsSourceFirst";
	/**	Parameter Name for PA_Hierarchy_ID	*/
	public static final String PA_Hierarchy_ID = "PA_Hierarchy_ID";
	/**	Parameter Name for PA_ReportCube_ID	*/
	public static final String PA_ReportCube_ID = "PA_ReportCube_ID";

	/**	Parameter Value for periodId	*/
	private int periodId;
	/**	Parameter Value for organizationId	*/
	private int organizationId;
	/**	Parameter Value for businessPartnerId	*/
	private int businessPartnerId;
	/**	Parameter Value for productId	*/
	private int productId;
	/**	Parameter Value for projectId	*/
	private int projectId;
	/**	Parameter Value for activityId	*/
	private int activityId;
	/**	Parameter Value for salesRegionId	*/
	private int salesRegionId;
	/**	Parameter Value for campaignId	*/
	private int campaignId;
	/**	Parameter Value for userList1Id	*/
	private int userList1Id;
	/**	Parameter Value for userList2Id	*/
	private int userList2Id;
	/**	Parameter Value for userList3Id	*/
	private int userList3Id;
	/**	Parameter Value for userList4Id	*/
	private int userList4Id;
	/**	Parameter Value for userElement1Id	*/
	private int userElement1Id;
	/**	Parameter Value for userElement2Id	*/
	private int userElement2Id;
	/**	Parameter Value for isDetailsSourceFirst	*/
	private boolean isDetailsSourceFirst;
	/**	Parameter Value for reportingHierarchyId	*/
	private int reportingHierarchyId;
	/**	Parameter Value for reportCubeId	*/
	private int reportCubeId;
 

	@Override
	protected void prepare()
	{
		periodId = getParameterAsInt(C_Period_ID);
		organizationId = getParameterAsInt(Org_ID);
		businessPartnerId = getParameterAsInt(C_BPartner_ID);
		productId = getParameterAsInt(M_Product_ID);
		projectId = getParameterAsInt(C_Project_ID);
		activityId = getParameterAsInt(C_Activity_ID);
		salesRegionId = getParameterAsInt(C_SalesRegion_ID);
		campaignId = getParameterAsInt(C_Campaign_ID);
		userList1Id = getParameterAsInt(User1_ID);
		userList2Id = getParameterAsInt(User2_ID);
		userList3Id = getParameterAsInt(User3_ID);
		userList4Id = getParameterAsInt(User4_ID);
		userElement1Id = getParameterAsInt(UserElement1_ID);
		userElement2Id = getParameterAsInt(UserElement2_ID);
		isDetailsSourceFirst = getParameterAsBoolean(DetailsSourceFirst);
		reportingHierarchyId = getParameterAsInt(PA_Hierarchy_ID);
		reportCubeId = getParameterAsInt(PA_ReportCube_ID);
	}

	/**	 Getter Parameter Value for periodId	*/
	protected int getPeriodId() {
		return periodId;
	}

	/**	 Set Parameter Value for periodId	*/
	protected void setPeriodId(int periodId ) {
		this.periodId = periodId;
	}

	/**	 Getter Parameter Value for organizationId	*/
	protected int getOrganizationId() {
		return organizationId;
	}

	/**	 Getter Parameter Value for businessPartnerId	*/
	protected int getBusinessPartnerId() {
		return businessPartnerId;
	}

	/**	 Getter Parameter Value for productId	*/
	protected int getProductId() {
		return productId;
	}

	/**	 Getter Parameter Value for projectId	*/
	protected int getProjectId() {
		return projectId;
	}

	/**	 Getter Parameter Value for activityId	*/
	protected int getActivityId() {
		return activityId;
	}

	/**	 Getter Parameter Value for salesRegionId	*/
	protected int getSalesRegionId() {
		return salesRegionId;
	}

	/**	 Getter Parameter Value for campaignId	*/
	protected int getCampaignId() {
		return campaignId;
	}

	/**	 Getter Parameter Value for userList1Id	*/
	protected int getUserList1Id() {
		return userList1Id;
	}

	/**	 Getter Parameter Value for userList2Id	*/
	protected int getUserList2Id() {
		return userList2Id;
	}

	/**	 Getter Parameter Value for userList3Id	*/
	protected int getUserList3Id() {
		return userList3Id;
	}

	/**	 Getter Parameter Value for userList4Id	*/
	protected int getUserList4Id() {
		return userList4Id;
	}

	/**	 Getter Parameter Value for userElement1Id	*/
	protected int getUserElement1Id() {
		return userElement1Id;
	}

	/**	 Getter Parameter Value for userElement2Id	*/
	protected int getUserElement2Id() {
		return userElement2Id;
	}

	/**	 Getter Parameter Value for isDetailsSourceFirst	*/
	protected boolean isDetailsSourceFirst() {
		return isDetailsSourceFirst;
	}

	/**	 Getter Parameter Value for reportingHierarchyId	*/
	protected int getReportingHierarchyId() {
		return reportingHierarchyId;
	}

	/**	 Getter Parameter Value for reportCubeId	*/
	protected int getReportCubeId() {
		return reportCubeId;
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