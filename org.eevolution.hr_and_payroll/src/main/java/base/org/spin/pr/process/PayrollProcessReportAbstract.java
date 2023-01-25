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

package org.spin.pr.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Payroll Process Report)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class PayrollProcessReportAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "PayrollProcessReport";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Payroll Process Report";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54035;
	/**	Parameter Name for Organization	*/
	public static final String AD_ORG_ID = "AD_Org_ID";
	/**	Parameter Name for Payroll Contract	*/
	public static final String HR_CONTRACT_ID = "HR_Contract_ID";
	/**	Parameter Name for Payroll	*/
	public static final String HR_PAYROLL_ID = "HR_Payroll_ID";
	/**	Parameter Name for Payroll Process	*/
	public static final String HR_PROCESS_ID = "HR_Process_ID";
	/**	Parameter Name for Payroll Department	*/
	public static final String HR_DEPARTMENT_ID = "HR_Department_ID";
	/**	Parameter Name for Payroll Job	*/
	public static final String HR_JOB_ID = "HR_Job_ID";
	/**	Parameter Name for Employee Status	*/
	public static final String EMPLOYEESTATUS = "EmployeeStatus";
	/**	Parameter Name for Business Partner 	*/
	public static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Account Date	*/
	public static final String DATEACCT = "DateAcct";
	/**	Parameter Name for Document Status	*/
	public static final String DOCSTATUS = "DocStatus";
	/**	Parameter Name for Payroll Process Report	*/
	public static final String HR_PROCESSREPORT_ID = "HR_ProcessReport_ID";
	/**	Parameter Name for Payroll Process Report Template ID	*/
	public static final String HR_PROCESSREPORTTEMPLATE_ID = "HR_ProcessReportTemplate_ID";
	/**	Parameter Value for Organization	*/
	private int orgId;
	/**	Parameter Value for Payroll Contract	*/
	private int contractId;
	/**	Parameter Value for Payroll	*/
	private int payrollId;
	/**	Parameter Value for Payroll Process	*/
	private int hRProcessId;
	/**	Parameter Value for Payroll Department	*/
	private int departmentId;
	/**	Parameter Value for Payroll Job	*/
	private int jobId;
	/**	Parameter Value for Employee Status	*/
	private String employeeStatus;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Account Date	*/
	private Timestamp dateAcct;
	/**	Parameter Value for Account Date(To)	*/
	private Timestamp dateAcctTo;
	/**	Parameter Value for Document Status	*/
	private String docStatus;
	/**	Parameter Value for Payroll Process Report	*/
	private int processReportId;
	/**	Parameter Value for Payroll Process Report Template ID	*/
	private int processReportTemplateId;

	@Override
	protected void prepare() {
		orgId = getParameterAsInt(AD_ORG_ID);
		contractId = getParameterAsInt(HR_CONTRACT_ID);
		payrollId = getParameterAsInt(HR_PAYROLL_ID);
		hRProcessId = getParameterAsInt(HR_PROCESS_ID);
		departmentId = getParameterAsInt(HR_DEPARTMENT_ID);
		jobId = getParameterAsInt(HR_JOB_ID);
		employeeStatus = getParameterAsString(EMPLOYEESTATUS);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		dateAcct = getParameterAsTimestamp(DATEACCT);
		dateAcctTo = getParameterToAsTimestamp(DATEACCT);
		docStatus = getParameterAsString(DOCSTATUS);
		processReportId = getParameterAsInt(HR_PROCESSREPORT_ID);
		processReportTemplateId = getParameterAsInt(HR_PROCESSREPORTTEMPLATE_ID);
	}

	/**	 Getter Parameter Value for Organization	*/
	protected int getOrgId() {
		return orgId;
	}

	/**	 Setter Parameter Value for Organization	*/
	protected void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	/**	 Getter Parameter Value for Payroll Contract	*/
	protected int getContractId() {
		return contractId;
	}

	/**	 Setter Parameter Value for Payroll Contract	*/
	protected void setContractId(int contractId) {
		this.contractId = contractId;
	}

	/**	 Getter Parameter Value for Payroll	*/
	protected int getPayrollId() {
		return payrollId;
	}

	/**	 Setter Parameter Value for Payroll	*/
	protected void setPayrollId(int payrollId) {
		this.payrollId = payrollId;
	}

	/**	 Getter Parameter Value for Payroll Process	*/
	protected int getHRProcessId() {
		return hRProcessId;
	}

	/**	 Setter Parameter Value for Payroll Process	*/
	protected void setHRProcessId(int hRProcessId) {
		this.hRProcessId = hRProcessId;
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

	/**	 Getter Parameter Value for Employee Status	*/
	protected String getEmployeeStatus() {
		return employeeStatus;
	}

	/**	 Setter Parameter Value for Employee Status	*/
	protected void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Account Date	*/
	protected Timestamp getDateAcct() {
		return dateAcct;
	}

	/**	 Setter Parameter Value for Account Date	*/
	protected void setDateAcct(Timestamp dateAcct) {
		this.dateAcct = dateAcct;
	}

	/**	 Getter Parameter Value for Account Date(To)	*/
	protected Timestamp getDateAcctTo() {
		return dateAcctTo;
	}

	/**	 Setter Parameter Value for Account Date(To)	*/
	protected void setDateAcctTo(Timestamp dateAcctTo) {
		this.dateAcctTo = dateAcctTo;
	}

	/**	 Getter Parameter Value for Document Status	*/
	protected String getDocStatus() {
		return docStatus;
	}

	/**	 Setter Parameter Value for Document Status	*/
	protected void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	/**	 Getter Parameter Value for Payroll Process Report	*/
	protected int getProcessReportId() {
		return processReportId;
	}

	/**	 Setter Parameter Value for Payroll Process Report	*/
	protected void setProcessReportId(int processReportId) {
		this.processReportId = processReportId;
	}

	/**	 Getter Parameter Value for Payroll Process Report Template ID	*/
	protected int getProcessReportTemplateId() {
		return processReportTemplateId;
	}

	/**	 Setter Parameter Value for Payroll Process Report Template ID	*/
	protected void setProcessReportTemplateId(int processReportTemplateId) {
		this.processReportTemplateId = processReportTemplateId;
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