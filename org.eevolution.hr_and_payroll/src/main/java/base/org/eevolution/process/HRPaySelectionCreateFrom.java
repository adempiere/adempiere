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
	private int 	p_HR_Process_ID = 0;
	/** Payroll						*/
	private int		p_HR_Payroll_ID = 0;
	/** BPartner 					*/
	private int		p_C_BPartner_ID = 0;
	/** BPartner Group				*/
	private int		p_C_BP_Group_ID = 0;
	/** Payment Rule				*/
	private String  p_PaymentRule = null;
	/** Payroll Concept				*/
	private int		p_HR_Concept_ID = 0;
	/** Payroll Department			*/
	private int		p_HR_Department_ID = 0;
	/** Payroll Job					*/
	private int		p_HR_Job_ID = 0;
	/**	Payroll Payment Selection	*/
	private int		p_HR_PaySelection_ID = 0;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("HR_Process_ID"))
				p_HR_Process_ID = para[i].getParameterAsInt();
			else if (name.equals("HR_Payroll_ID"))
				p_HR_Payroll_ID = para[i].getParameterAsInt();
			else if (name.equals("C_BPartner_ID"))
				p_C_BPartner_ID = para[i].getParameterAsInt();
			else if (name.equals("C_BP_Group_ID"))
				p_C_BP_Group_ID = para[i].getParameterAsInt();
			else if (name.equals("PaymentRule"))
				p_PaymentRule = (String)para[i].getParameter();			
			else if (name.equals("HR_Concept_ID"))
				p_HR_Concept_ID = para[i].getParameterAsInt();
			else if (name.equals("HR_Department_ID"))
				p_HR_Department_ID = para[i].getParameterAsInt();
			else if (name.equals("HR_Job_ID"))
				p_HR_Job_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_HR_PaySelection_ID = getRecord_ID();
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message 
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		log.info ("C_PaySelection_ID=" + p_HR_PaySelection_ID
			+ ", Process=" + p_HR_Process_ID
			+ ", Payroll=" + p_HR_Payroll_ID
			+ ", BP Group=" + p_C_BP_Group_ID
			+ ", PaymentRule=" + p_PaymentRule
			+ ", Concept=" + p_HR_Concept_ID
			+ ", Depatment="+ p_HR_Department_ID
			+ ", Job=" + p_HR_Job_ID);
		
		MHRPaySelection psel = new MHRPaySelection (getCtx(), p_HR_PaySelection_ID, get_TrxName());
		MHRProcess process = new MHRProcess(getCtx(),p_HR_Process_ID,get_TrxName());
		MHRPayroll payroll = new MHRPayroll(getCtx(),process.getHR_Payroll_ID(),get_TrxName()); 
		
		ArrayList<Object> parameters = new ArrayList<Object>();

		if (psel.get_ID() == 0)
			throw new IllegalArgumentException("Not found HR_PaySelection_ID=" + p_HR_PaySelection_ID);
		if (psel.isProcessed())
			throw new IllegalArgumentException("@Processed@");

		parameters.add(p_HR_Process_ID);
		parameters.add(true);
		parameters.add(p_HR_PaySelection_ID);
		
		StringBuffer where = new StringBuffer("HR_Process_ID=?");
			where.append(" AND HR_Concept_ID IN(SELECT HR_Concept_ID FROM HR_Concept WHERE IsPaid=?)"); 	// Only Concept isPaid
			where.append(" AND HR_Movement_ID NOT IN(SELECT HR_Movement_ID " + // Not Exist in PaySelection Process or PaySelection Actual
								" FROM HR_PaySelectionLine " + 	
								" WHERE HR_PaySelectionCheck_ID > 0 OR HR_PaySelection_ID=?)");
		if(p_C_BP_Group_ID > 0)
		{	
			where.append(" AND C_BPartner_ID IN(SELECT C_BPartner_ID FROM C_BPartner WHERE C_BP_Group_ID=? )");
			parameters.add(p_C_BP_Group_ID);
		}	
		if(p_C_BPartner_ID > 0)
		{	
			where.append(" AND C_BPartner_ID=?");
			parameters.add(p_C_BPartner_ID);
		}	
		
		if(p_PaymentRule != null)
		{	
			where.append(" AND C_BPartner_ID IN(SELECT C_BPartner_ID FROM C_BPartner WHERE PaymentRulePO=? )");
			parameters.add(p_PaymentRule);
		}	
		if(p_HR_Concept_ID > 0)
		{	
			where.append(" AND HR_Concept_ID=? ");
			parameters.add(p_HR_Concept_ID);
		}	
		if(p_HR_Department_ID > 0)
		{	
			where.append(" AND HR_Department_ID=?");
			parameters.add(p_HR_Department_ID);
		}	
		if(p_HR_Job_ID > 0)
		{	
			where.append(" AND HR_Job_ID=?");
			parameters.add(p_HR_Job_ID);
		}	
				
		int lines = 0;
		List <MHRMovement> movements = new Query(getCtx(),I_HR_Movement.Table_Name,where.toString(),get_TrxName())
		.setClient_ID()
		.setParameters(parameters)
		.list();
		
		for(MHRMovement m : movements)
		{
			MBPartner bp = new MBPartner(getCtx(),m.getC_BPartner_ID(),get_TrxName());
			String PaymentRule = "";
			if(bp.getPaymentRulePO() != null)
			{
				PaymentRule = bp.getPaymentRule();
			}
			else
			{
				PaymentRule = payroll.getPaymentRule();
			}
			if(PaymentRule == null)
				PaymentRule = "D";
			
			MHRPaySelectionLine psl = new MHRPaySelectionLine(getCtx(),0,get_TrxName());
			psl.setHR_PaySelection_ID(p_HR_PaySelection_ID);
			psl.setHR_Movement_ID(m.getHR_Movement_ID());
			psl.setPaymentRule(PaymentRule);
			psl.setAD_Org_ID(psel.getAD_Org_ID());
			psl.setLine((lines+1)*10);
			psl.setOpenAmt(m.getAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN));
			psl.setPayAmt(m.getAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN));
			psl.setDescription(bp.getName() +" "+ bp.getName2());
			psl.setDifferenceAmt(Env.ZERO);
			psl.setDiscountAmt(Env.ZERO);
			psl.setIsManual(false);
			psl.setIsSOTrx(false);
			psl.setIsActive(true);
			psl.saveEx();
			lines++;
		}
		return "@C_PaySelectionLine_ID@  - #" + lines;
	}	//	doIt

}