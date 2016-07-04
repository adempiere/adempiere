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
 *****************************************************************************/

package org.eevolution.process;

import org.compiere.process.SvrProcess;
/** Generated Process for (Create From ...)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class HRPaySelectionCreateFromAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "HR_PaySelection_CreateFrom";
	/** Process Name 	*/
	private static final String NAME = "Create From ...";
	/** Process Id 	*/
	private static final int ID = 53190;
 
	/**	Parameter Name for HR_Payroll_ID	*/
	public static final String HR_Payroll_ID = "HR_Payroll_ID";
	/**	Parameter Name for HR_Process_ID	*/
	public static final String HR_Process_ID = "HR_Process_ID";
	/**	Parameter Name for C_BP_Group_ID	*/
	public static final String C_BP_Group_ID = "C_BP_Group_ID";
	/**	Parameter Name for HR_EmployeeType_ID	*/
	public static final String HR_EmployeeType_ID = "HR_EmployeeType_ID";
	/**	Parameter Name for C_BPartner_ID	*/
	public static final String C_BPartner_ID = "C_BPartner_ID";
	/**	Parameter Name for PaymentRule	*/
	public static final String PaymentRule = "PaymentRule";
	/**	Parameter Name for HR_Concept_ID	*/
	public static final String HR_Concept_ID = "HR_Concept_ID";
	/**	Parameter Name for HR_Department_ID	*/
	public static final String HR_Department_ID = "HR_Department_ID";
	/**	Parameter Name for HR_Job_ID	*/
	public static final String HR_Job_ID = "HR_Job_ID";

	/**	Parameter Value for payrollId	*/
	private int payrollId;
	/**	Parameter Value for payrollProcessId	*/
	private int payrollProcessId;
	/**	Parameter Value for businessPartnerGroupId	*/
	private int businessPartnerGroupId;
	/**	Parameter Value for employeeTypeId	*/
	private int employeeTypeId;
	/**	Parameter Value for businessPartnerId	*/
	private int businessPartnerId;
	/**	Parameter Value for paymentRule	*/
	private String paymentRule;
	/**	Parameter Value for globalPayrollConceptId	*/
	private int globalPayrollConceptId;
	/**	Parameter Value for departmentId	*/
	private int departmentId;
	/**	Parameter Value for payrollJobId	*/
	private int payrollJobId;
 

	@Override
	protected void prepare()
	{
		payrollId = getParameterAsInt(HR_Payroll_ID);
		payrollProcessId = getParameterAsInt(HR_Process_ID);
		businessPartnerGroupId = getParameterAsInt(C_BP_Group_ID);
		employeeTypeId = getParameterAsInt(HR_EmployeeType_ID);
		businessPartnerId = getParameterAsInt(C_BPartner_ID);
		paymentRule = getParameterAsString(PaymentRule);
		globalPayrollConceptId = getParameterAsInt(HR_Concept_ID);
		departmentId = getParameterAsInt(HR_Department_ID);
		payrollJobId = getParameterAsInt(HR_Job_ID);
	}

	/**	 Getter Parameter Value for payrollId	*/
	protected int getPayrollId() {
		return payrollId;
	}

	/**	 Getter Parameter Value for payrollProcessId	*/
	protected int getPayrollProcessId() {
		return payrollProcessId;
	}

	/**	 Getter Parameter Value for businessPartnerGroupId	*/
	protected int getBusinessPartnerGroupId() {
		return businessPartnerGroupId;
	}

	/**	 Getter Parameter Value for employeeTypeId	*/
	protected int getEmployeeTypeId() {
		return employeeTypeId;
	}

	/**	 Getter Parameter Value for businessPartnerId	*/
	protected int getBusinessPartnerId() {
		return businessPartnerId;
	}

	/**	 Getter Parameter Value for paymentRule	*/
	protected String getPaymentRule() {
		return paymentRule;
	}

	/**	 Getter Parameter Value for globalPayrollConceptId	*/
	protected int getGlobalPayrollConceptId() {
		return globalPayrollConceptId;
	}

	/**	 Getter Parameter Value for departmentId	*/
	protected int getDepartmentId() {
		return departmentId;
	}

	/**	 Getter Parameter Value for payrollJobId	*/
	protected int getPayrollJobId() {
		return payrollJobId;
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