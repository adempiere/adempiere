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

package org.eevolution.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Send Payroll Receipt by Email)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class SendPayrollReceiptByEmailAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "HR_Send Payroll Receipt by Email";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Send Payroll Receipt by Email";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54143;
	/**	Parameter Name for Process	*/
	public static final String AD_PROCESS_ID = "AD_Process_ID";
	/**	Parameter Name for Payroll Process	*/
	public static final String HR_PROCESS_ID = "HR_Process_ID";
	/**	Parameter Name for Mail Template	*/
	public static final String R_MAILTEXT_ID = "R_MailText_ID";
	/**	Parameter Name for Business Partner Group	*/
	public static final String C_BP_GROUP_ID = "C_BP_Group_ID";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Payroll Department	*/
	public static final String HR_DEPARTMENT_ID = "HR_Department_ID";
	/**	Parameter Name for Payroll Job	*/
	public static final String HR_JOB_ID = "HR_Job_ID";
	/**	Parameter Name for Activity	*/
	public static final String C_ACTIVITY_ID = "C_Activity_ID";
	/**	Parameter Value for Process	*/
	private int aDProcessId;
	/**	Parameter Value for Payroll Process	*/
	private int hRProcessId;
	/**	Parameter Value for Mail Template	*/
	private int mailTextId;
	/**	Parameter Value for Business Partner Group	*/
	private int bPGroupId;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Payroll Department	*/
	private int departmentId;
	/**	Parameter Value for Payroll Job	*/
	private int jobId;
	/**	Parameter Value for Activity	*/
	private int activityId;

	@Override
	protected void prepare() {
		aDProcessId = getParameterAsInt(AD_PROCESS_ID);
		hRProcessId = getParameterAsInt(HR_PROCESS_ID);
		mailTextId = getParameterAsInt(R_MAILTEXT_ID);
		bPGroupId = getParameterAsInt(C_BP_GROUP_ID);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		departmentId = getParameterAsInt(HR_DEPARTMENT_ID);
		jobId = getParameterAsInt(HR_JOB_ID);
		activityId = getParameterAsInt(C_ACTIVITY_ID);
	}

	/**	 Getter Parameter Value for Process	*/
	protected int getADProcessId() {
		return aDProcessId;
	}

	/**	 Setter Parameter Value for Process	*/
	protected void setADProcessId(int aDProcessId) {
		this.aDProcessId = aDProcessId;
	}

	/**	 Getter Parameter Value for Payroll Process	*/
	protected int getHRProcessId() {
		return hRProcessId;
	}

	/**	 Setter Parameter Value for Payroll Process	*/
	protected void setHRProcessId(int hRProcessId) {
		this.hRProcessId = hRProcessId;
	}

	/**	 Getter Parameter Value for Mail Template	*/
	protected int getMailTextId() {
		return mailTextId;
	}

	/**	 Setter Parameter Value for Mail Template	*/
	protected void setMailTextId(int mailTextId) {
		this.mailTextId = mailTextId;
	}

	/**	 Getter Parameter Value for Business Partner Group	*/
	protected int getBPGroupId() {
		return bPGroupId;
	}

	/**	 Setter Parameter Value for Business Partner Group	*/
	protected void setBPGroupId(int bPGroupId) {
		this.bPGroupId = bPGroupId;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Payroll Department	*/
	protected int getDepartmentId() {
		return departmentId;
	}

	/**	 Setter Parameter Value for Payroll Department	*/
	protected void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	/**	 Getter Parameter Value for Payroll Job	*/
	protected int getJobId() {
		return jobId;
	}

	/**	 Setter Parameter Value for Payroll Job	*/
	protected void setJobId(int jobId) {
		this.jobId = jobId;
	}

	/**	 Getter Parameter Value for Activity	*/
	protected int getActivityId() {
		return activityId;
	}

	/**	 Setter Parameter Value for Activity	*/
	protected void setActivityId(int activityId) {
		this.activityId = activityId;
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