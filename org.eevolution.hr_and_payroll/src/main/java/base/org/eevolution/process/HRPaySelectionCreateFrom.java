/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Contributor(s):Victor Perez www.e-evolution.com 				              *
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 *****************************************************************************/
package org.eevolution.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.eevolution.model.I_HR_Movement;
import org.eevolution.model.MHREmployee;
import org.eevolution.model.MHRMovement;
import org.eevolution.model.MHRPaySelection;
import org.eevolution.model.MHRPaySelectionLine;
import org.eevolution.model.MHRPayroll;
import org.eevolution.model.MHRProcess;

/**
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 *
 */
public class HRPaySelectionCreateFrom extends SvrProcess
{

	/**	Payroll Process				*/
	private int processId = 0;
	/** Payroll						*/
	private int payrollId = 0;
	/** BPartner 					*/
	private int partnerId = 0;
	/** BPartner Group				*/
	private int	employeeTypeId = 0;
	/** Employee Type			*/
	private int partnerGroupId = 0;
	/** Payment Rule				*/
	private String paymentRule = null;
	/** Payroll Concept				*/
	private int payrollConceptId = 0;
	/** Payroll Department			*/
	private int departmentId = 0;
	/** Payroll Job					*/
	private int jobId = 0;
	/**	Payroll Payment Selection	*/
	private int paySelectionId = 0;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		for (ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (I_HR_Movement.COLUMNNAME_HR_Process_ID.equals(name))
				processId = para.getParameterAsInt();
			else if (I_HR_Movement.COLUMNNAME_HR_Payroll_ID.equals(name))
				payrollId = para.getParameterAsInt();
			else if (I_HR_Movement.COLUMNNAME_C_BPartner_ID.equals(name))
				partnerId = para.getParameterAsInt();
			else if (I_HR_Movement.COLUMNNAME_C_BP_Group_ID.equals(name))
				partnerGroupId = para.getParameterAsInt();
			else if (I_HR_Movement.COLUMNNAME_HR_EmployeeType_ID.equals(name))
				employeeTypeId = para.getParameterAsInt();
			else if (I_HR_Movement.COLUMNNAME_PaymentRule.equals(name))
				paymentRule = (String)para.getParameter();
			else if (I_HR_Movement.COLUMNNAME_HR_Concept_ID.equals(name))
				payrollConceptId = para.getParameterAsInt();
			else if (I_HR_Movement.COLUMNNAME_HR_Department_ID.equals(name))
				departmentId = para.getParameterAsInt();
			else if (I_HR_Movement.COLUMNNAME_HR_Job_ID.equals(name))
				jobId = para.getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		paySelectionId = getRecord_ID();
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message 
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		log.info ("C_PaySelection_ID=" + paySelectionId
			+ ", Process=" + processId
			+ ", Payroll=" + payrollId
			+ ", BP Group=" + partnerGroupId
			+ ", PaymentRule=" + paymentRule
			+ ", Concept=" + payrollConceptId
			+ ", Depatment="+ departmentId
			+ ", Job=" + jobId);
		
		MHRPaySelection psel = new MHRPaySelection (getCtx(), paySelectionId, get_TrxName());
		psel.setHR_Process_ID(processId);
		psel.saveEx();
		
		MHRProcess process = new MHRProcess(getCtx(), processId,get_TrxName());
		MHRPayroll payroll = new MHRPayroll(getCtx(),process.getHR_Payroll_ID(),get_TrxName()); 
		
		ArrayList<Object> parameters = new ArrayList<Object>();

		if (psel.get_ID() == 0)
			throw new IllegalArgumentException("Not found HR_PaySelection_ID=" + paySelectionId);
		if (psel.isProcessed())
			throw new IllegalArgumentException("@Processed@");

		parameters.add(processId);
		parameters.add(true);
		parameters.add(paySelectionId);

		StringBuffer where = new StringBuffer("HR_Process_ID=?");
			where.append(" AND HR_Concept_ID IN(SELECT HR_Concept_ID FROM HR_Concept WHERE IsPaid=?)"); 	// Only Concept isPaid
			where.append(" AND HR_Movement_ID NOT IN(SELECT HR_Movement_ID " + // Not Exist in PaySelection Process or PaySelection Actual
								" FROM HR_PaySelectionLine " + 	
								" WHERE HR_PaySelectionCheck_ID > 0 OR HR_PaySelection_ID=?)");
		if(partnerGroupId > 0)
		{	
			where.append(" AND C_BP_Group_ID=?");
			parameters.add(partnerGroupId);
		}
		if(employeeTypeId > 0)
		{
			where.append(" AND HR_EmployeeType_ID=?");
			parameters.add(employeeTypeId);
		}
		if(partnerId > 0)
		{	
			where.append(" AND C_BPartner_ID=?");
			parameters.add(partnerId);
		}	
		
		if(paymentRule != null)
		{	
			where.append(" AND PaymentRule=?");
			parameters.add(paymentRule);
		}
		if(payrollConceptId > 0)
		{	
			where.append(" AND HR_Concept_ID=?");
			parameters.add(payrollConceptId);
		}	
		if(departmentId > 0)
		{	
			where.append(" AND HR_Department_ID=?");
			parameters.add(departmentId);
		}	
		if(jobId > 0)
		{	
			where.append(" AND HR_Job_ID=?");
			parameters.add(jobId);
		}	
				
		int lines = 0;
		List <MHRMovement> movements = new Query(getCtx(),I_HR_Movement.Table_Name,where.toString(),get_TrxName())
		.setClient_ID()
		.setParameters(parameters)
		.list();
		
		for(MHRMovement movement : movements)
		{
			MBPartner partner = new MBPartner(getCtx(),movement.getC_BPartner_ID(),get_TrxName());
			String paymentRule = null;
            MHREmployee employee = MHREmployee.getActiveEmployee(partner.getCtx(), partner.getC_BPartner_ID(), movement.get_TrxName());

			if (employee != null)
				paymentRule = employee.getPaymentRule();

            if (paymentRule == null) {
                if (partner.getPaymentRule() != null)
                    paymentRule = partner.getPaymentRule();
                else
                    paymentRule = payroll.getPaymentRule();
            }

			if(paymentRule == null)
				paymentRule = "T";

			MHRPaySelectionLine paySelectionLine = new MHRPaySelectionLine(getCtx(),0,get_TrxName());
			paySelectionLine.setHR_PaySelection_ID(paySelectionId);
			paySelectionLine.setHR_Movement_ID(movement.getHR_Movement_ID());
			paySelectionLine.setPaymentRule(paymentRule);
			paySelectionLine.setAD_Org_ID(psel.getAD_Org_ID());
			paySelectionLine.setLine((lines + 1) * 10);
			paySelectionLine.setOpenAmt(movement.getAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN));
			paySelectionLine.setPayAmt(movement.getAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN));
			paySelectionLine.setDescription(partner.getName() + " " + partner.getName2());
			paySelectionLine.setDifferenceAmt(Env.ZERO);
			paySelectionLine.setDiscountAmt(Env.ZERO);
			paySelectionLine.setIsManual(false);
			paySelectionLine.setIsSOTrx(false);
			paySelectionLine.setIsActive(true);
			paySelectionLine.saveEx();
			lines++;
		}
		return "@C_PaySelectionLine_ID@  - #" + lines;
	}	//	doIt

}