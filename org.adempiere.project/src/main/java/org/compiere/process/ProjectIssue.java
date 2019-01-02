/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.process;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MProject;
import org.compiere.model.MProjectIssue;
import org.compiere.model.MProjectLine;
import org.compiere.model.MStorage;
import org.compiere.model.MTimeExpense;
import org.compiere.model.MTimeExpenseLine;
import org.compiere.util.Env;

/**
 *  Issue to Project.
 *
 *	@author Jorg Janke
 *	@version $Id: ProjectIssue.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class ProjectIssue extends ProjectIssueAbstract
{
	private List<MProjectIssue>	projectIssues = null;


	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message (clear text)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		//	Check Parameter
		MProject project = new MProject (getCtx(), getProjectId(), get_TrxName());
		if (!(MProject.PROJECTCATEGORY_WorkOrderJob.equals(project.getProjectCategory())
			|| MProject.PROJECTCATEGORY_AssetProject.equals(project.getProjectCategory())))
			throw new IllegalArgumentException("Project not Work Order or Asset =" + project.getProjectCategory());
		log.info(project.toString());
		//
		if (getInOutId() != 0)
			return issueReceipt(project);
		if (getTimeExpenseId() != 0)
			return issueExpense(project);
		if (getLocatorId() == 0)
			throw new IllegalArgumentException("Locator missing");
		if (getProjectLineId() != 0)
			return issueProjectLine(project);
		return issueInventory(project);
	}	//	doIt

	/**
	 *	Issue Receipt
	 *	@param project Project
	 *	@return Message (clear text)
	 */
	private String issueReceipt(MProject project)
	{
		MInOut inOut = new MInOut (getCtx(), getInOutId(), null);
		if (inOut.isSOTrx() || !inOut.isProcessed()
			|| !(MInOut.DOCSTATUS_Completed.equals(inOut.getDocStatus()) || MInOut.DOCSTATUS_Closed.equals(inOut.getDocStatus())))
			throw new IllegalArgumentException ("Receipt not valid - " + inOut);
		log.info(inOut.toString());
		//	Set Project of Receipt
		if (inOut.getC_Project_ID() == 0)
		{
			inOut.setC_Project_ID(project.getC_Project_ID());
			inOut.saveEx();
		}
		else if (inOut.getC_Project_ID() != project.getC_Project_ID())
			throw new IllegalArgumentException ("Receipt for other Project (" 
				+ inOut.getC_Project_ID() + ")");

		MInOutLine[] inOutLines = inOut.getLines(false);
		AtomicInteger counter = new AtomicInteger(0);
		Arrays.stream(inOut.getLines(false))
				.filter(inOutLine ->
						inOutLine.getM_Product_ID() != 0  // Need to have a Product
					&& (inOutLine.getMovementQty() != null || inOutLine.getMovementQty().signum() != 0) // Need to have Quantity
					&& (!projectIssueHasReceipt(project, inOutLine.getM_InOutLine_ID()))) // not issued yet
				.forEach(inOutLine -> {
					//	Create Issue
					MProjectIssue projectIssue = new MProjectIssue(project);
					projectIssue.setMandatory(inOutLine.getM_Locator_ID(), inOutLine.getM_Product_ID(), inOutLine.getMovementQty());
					if (getMovementDate() != null)        //	default today
						projectIssue.setMovementDate(getMovementDate());
					if (getDescription() != null && getDescription().length() > 0)
						projectIssue.setDescription(getDescription());
					else if (inOutLine.getDescription() != null)
						projectIssue.setDescription(inOutLine.getDescription());
					else if (inOut.getDescription() != null)
						projectIssue.setDescription(inOut.getDescription());
					projectIssue.setM_InOutLine_ID(inOutLine.getM_InOutLine_ID());
					projectIssue.process();

					//	Find/Create Project Line
					MProjectLine firstProjectLine = project.getLines().stream()
							.filter(projectLine -> projectLine.getC_OrderPO_ID() == inOut.getC_Order_ID()
								 && projectLine.getM_Product_ID() == inOutLine.getM_Product_ID()
								 && projectLine.getC_ProjectIssue_ID() == 0)
							.findFirst().get();
					if (firstProjectLine == null)
						firstProjectLine = new MProjectLine(project);
					firstProjectLine.setMProjectIssue(projectIssue);        //	setIssue
					firstProjectLine.saveEx();
					addLog(projectIssue.getLine(), projectIssue.getMovementDate(), projectIssue.getMovementQty(), null);
					counter.getAndUpdate(no -> no + 1);
				});
		return "@Created@ " + counter.get();
	}	//	issueReceipt


	/**
	 *	Issue Expense Report
	 *  @param project
	 *	@return Message (clear text)
	 */
	private String issueExpense(MProject project)
	{
		//	Get Expense Report
		MTimeExpense expense = new MTimeExpense (getCtx(), getTimeExpenseId(), get_TrxName());
		if (!expense.isProcessed())
		  throw new IllegalArgumentException ("Time+Expense not processed - " + expense);

		//	for all expense lines
		MTimeExpenseLine[] expenseLines = expense.getLines(false);
		AtomicInteger counter = new AtomicInteger(0);
		//for (int i = 0; i < expenseLines.length; i++)
		Arrays.stream(expenseLines)
				.filter(expenseLine ->
				expenseLine.getM_Product_ID() != 0 // Need to have a Product
			 && (expenseLine.getQty() != null || expenseLine.getQty().signum() != 0) //	Need to have Quantity
			 &&	(expenseLine.getC_Project_ID() == project.getC_Project_ID()) // Need to the same project
			 &&	(!projectIssueHasExpense(project, expenseLine.getS_TimeExpenseLine_ID()))) // not issued yet
				.forEach(expenseLine -> {
			//	Find Location
			int locatorId = 0;
		//	MProduct product = new MProduct (getCtx(), expenseLines[i].getM_Product_ID());
		//	if (product.isStocked())
				locatorId = MStorage.getM_Locator_ID(expense.getM_Warehouse_ID(),
					expenseLine.getM_Product_ID(), 0, 	//	no ASI
					expenseLine.getQty(), null);
			if (locatorId == 0)	//	Service/Expense - get default (and fallback)
				locatorId = expense.getM_Locator_ID();

			//	Create Issue
			MProjectIssue projectIssue = new MProjectIssue (project);
			projectIssue.setMandatory (locatorId, expenseLine.getM_Product_ID(), expenseLine.getQty());
			if (getMovementDate() != null)		//	default today
				projectIssue.setMovementDate(getMovementDate());
			if (getDescription() != null && getDescription().length() > 0)
				projectIssue.setDescription(getDescription());
			else if (expenseLine.getDescription() != null)
				projectIssue.setDescription(expenseLine.getDescription());
			projectIssue.setS_TimeExpenseLine_ID(expenseLine.getS_TimeExpenseLine_ID());
			projectIssue.process();
			//	Find/Create Project Line
			MProjectLine projectLine = new MProjectLine(project);
			projectLine.setMProjectIssue(projectIssue);		//	setIssue
			projectLine.saveEx();
			addLog(projectIssue.getLine(), projectIssue.getMovementDate(), projectIssue.getMovementQty(), null);
			counter.getAndUpdate(no -> no + 1);
		});	//	allExpenseLines
		return "@Created@ " + counter.get();
	}	//	issueExpense


	/**
	 *	Issue Project Line
	 *	@return Message (clear text)
	 */
	private String issueProjectLine(MProject project)
	{
		MProjectLine projectLine = new MProjectLine(getCtx(), getProjectLineId(), get_TrxName());
		if (projectLine.getM_Product_ID() == 0)
			throw new IllegalArgumentException("Projet Line has no Product");
		if (projectLine.getC_ProjectIssue_ID() != 0)
			throw new IllegalArgumentException("Projet Line already been issued");
		if (getLocatorId() == 0)
			throw new IllegalArgumentException("No Locator");
		//	Set to Qty 1
		if (projectLine.getPlannedQty() == null || projectLine.getPlannedQty().signum() == 0)
			projectLine.setPlannedQty(Env.ONE);
		//
		MProjectIssue projectIssue = new MProjectIssue (project);
		projectIssue.setMandatory (getLocatorId(), projectLine.getM_Product_ID(), projectLine.getPlannedQty());
		if (getMovementDate() != null)		//	default today
			projectIssue.setMovementDate(getMovementDate());
		if (getDescription() != null && getDescription().length() > 0)
			projectIssue.setDescription(getDescription());
		else if (projectLine.getDescription() != null)
			projectIssue.setDescription(projectLine.getDescription());
		projectIssue.process();

		//	Update Line
		projectLine.setMProjectIssue(projectIssue);
		projectLine.saveEx();
		addLog(projectIssue.getLine(), projectIssue.getMovementDate(), projectIssue.getMovementQty(), null);
		return "@Created@ 1";
	}	//	issueProjectLine


	/**
	 *	Issue from Inventory
	 *	@return Message (clear text)
	 */
	private String issueInventory(MProject project)
	{
		if (getLocatorId() == 0)
			throw new IllegalArgumentException("No Locator");
		if (getProductId() == 0)
			throw new IllegalArgumentException("No Product");
		//
		MProjectIssue projectIssue = new MProjectIssue (project);
		projectIssue.setMandatory (getLocatorId(), getProductId(), getMovementQty());
		if (getMovementDate() != null)		//	default today
			projectIssue.setMovementDate(getMovementDate());
		if (getDescription() != null && getDescription().length() > 0)
			projectIssue.setDescription(getDescription());
		projectIssue.process();

		//	Create Project Line
		MProjectLine projectLine = new MProjectLine(project);
		projectLine.setMProjectIssue(projectIssue);
		projectLine.saveEx();
		addLog(projectIssue.getLine(), projectIssue.getMovementDate(), projectIssue.getMovementQty(), null);
		return "@Created@ 1";
	}	//	issueInventory

	/**
	 * 	Check if Project Issue already has Expense
	 * 	@param project Project
	 *	@param timeExpenseLineId line
	 *	@return true if exists
	 */
	private boolean projectIssueHasExpense (MProject project , int timeExpenseLineId)
	{
		if (projectIssues == null)
			projectIssues = project.getIssues();
		if (projectIssues.isEmpty())
			return false;
		Boolean exists = projectIssues.stream().allMatch(projectIssue -> projectIssue.getS_TimeExpenseLine_ID() == timeExpenseLineId);
		if (exists)
				return true;
		return false;
	}	//	projectIssueHasExpense

	/**
	 * 	Check if Project Issue already has Receipt
	 * 	@param project Project
	 *	@param inOutLineId line
	 *	@return true if exists
	 */
	private boolean projectIssueHasReceipt (MProject project , int inOutLineId)
	{
		if (projectIssues == null)
			projectIssues = project.getIssues();
		Boolean exists = projectIssues.stream().allMatch(projectIssue -> projectIssue.getM_InOutLine_ID() == inOutLineId);
		if (exists)
			return true;
		else
			return false;
	}	//	projectIssueHasReceipt
}	//	ProjectIssue
