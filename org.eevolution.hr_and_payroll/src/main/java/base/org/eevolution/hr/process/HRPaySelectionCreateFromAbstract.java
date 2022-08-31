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

package org.eevolution.hr.process;

import org.compiere.process.SvrProcess;

/** Generated Process for (Create From ...)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class HRPaySelectionCreateFromAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "HR_PaySelection_CreateFrom";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Create From ...";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 53190;
 
	/**	Parameter Name for Payroll	*/
	private static final String HR_PAYROLL_ID = "HR_Payroll_ID";
	/**	Parameter Name for Payroll Process	*/
	private static final String HR_PROCESS_ID = "HR_Process_ID";
	/**	Parameter Name for Business Partner Group	*/
	private static final String C_BP_GROUP_ID = "C_BP_Group_ID";
	/**	Parameter Name for Employee Type	*/
	private static final String HR_EMPLOYEETYPE_ID = "HR_EmployeeType_ID";
	/**	Parameter Name for Business Partner 	*/
	private static final String C_BPARTNER_ID = "C_BPartner_ID";
	/**	Parameter Name for Payment Rule	*/
	private static final String PAYMENTRULE = "PaymentRule";
	/**	Parameter Name for Global Payroll Concept	*/
	private static final String HR_CONCEPT_ID = "HR_Concept_ID";
	/**	Parameter Name for Payroll Department	*/
	private static final String HR_DEPARTMENT_ID = "HR_Department_ID";
	/**	Parameter Name for Payroll Job	*/
	private static final String HR_JOB_ID = "HR_Job_ID";

	/**	Parameter Value for Payroll	*/
	private int payrollId;
	/**	Parameter Value for Payroll Process	*/
	private int hRProcessId;
	/**	Parameter Value for Business Partner Group	*/
	private int bPGroupId;
	/**	Parameter Value for Employee Type	*/
	private int employeeTypeId;
	/**	Parameter Value for Business Partner 	*/
	private int bPartnerId;
	/**	Parameter Value for Payment Rule	*/
	private String paymentRule;
	/**	Parameter Value for Global Payroll Concept	*/
	private int conceptId;
	/**	Parameter Value for Payroll Department	*/
	private int departmentId;
	/**	Parameter Value for Payroll Job	*/
	private int jobId;
 

	@Override
	protected void prepare() {
		payrollId = getParameterAsInt(HR_PAYROLL_ID);
		hRProcessId = getParameterAsInt(HR_PROCESS_ID);
		bPGroupId = getParameterAsInt(C_BP_GROUP_ID);
		employeeTypeId = getParameterAsInt(HR_EMPLOYEETYPE_ID);
		bPartnerId = getParameterAsInt(C_BPARTNER_ID);
		paymentRule = getParameterAsString(PAYMENTRULE);
		conceptId = getParameterAsInt(HR_CONCEPT_ID);
		departmentId = getParameterAsInt(HR_DEPARTMENT_ID);
		jobId = getParameterAsInt(HR_JOB_ID);
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

	/**	 Getter Parameter Value for Business Partner Group	*/
	protected int getBPGroupId() {
		return bPGroupId;
	}

	/**	 Setter Parameter Value for Business Partner Group	*/
	protected void setBPGroupId(int bPGroupId) {
		this.bPGroupId = bPGroupId;
	}

	/**	 Getter Parameter Value for Employee Type	*/
	protected int getEmployeeTypeId() {
		return employeeTypeId;
	}

	/**	 Setter Parameter Value for Employee Type	*/
	protected void setEmployeeTypeId(int employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}

	/**	 Getter Parameter Value for Business Partner 	*/
	protected int getBPartnerId() {
		return bPartnerId;
	}

	/**	 Setter Parameter Value for Business Partner 	*/
	protected void setBPartnerId(int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	/**	 Getter Parameter Value for Payment Rule	*/
	protected String getPaymentRule() {
		return paymentRule;
	}

	/**	 Setter Parameter Value for Payment Rule	*/
	protected void setPaymentRule(String paymentRule) {
		this.paymentRule = paymentRule;
	}

	/**	 Getter Parameter Value for Global Payroll Concept	*/
	protected int getConceptId() {
		return conceptId;
	}

	/**	 Setter Parameter Value for Global Payroll Concept	*/
	protected void setConceptId(int conceptId) {
		this.conceptId = conceptId;
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