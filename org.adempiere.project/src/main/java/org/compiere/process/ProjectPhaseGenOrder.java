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


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProject;
import org.compiere.model.MProjectLine;
import org.compiere.model.MProjectPhase;
import org.compiere.model.MProjectTask;
import org.compiere.util.Env;


/**
 *  Generate Order from Project Phase
 *
 *	@author Jorg Janke
 *	@version $Id: ProjectPhaseGenOrder.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class ProjectPhaseGenOrder  extends ProjectPhaseGenOrderAbstract
{
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
		log.info("doIt - C_ProjectPhase_ID=" + getRecord_ID());
		if (getRecord_ID() == 0)
			throw new IllegalArgumentException("C_ProjectPhase_ID == 0");
		MProjectPhase fromPhase = new MProjectPhase (getCtx(), getRecord_ID(), get_TrxName());
		MProject fromProject = ProjectGenOrder.getProject (getCtx(), fromPhase.getC_Project_ID(), get_TrxName());
		if (fromProject.getC_PaymentTerm_ID() <= 0)
			throw new AdempiereException("@C_PaymentTerm_ID@ @NotFound@");

		MOrder order = new MOrder (fromProject, true, MOrder.DocSubTypeSO_OnCredit);
		order.setDescription(order.getDescription() + " - " + fromPhase.getName());
		order.saveEx();

		//	Create an order on Phase Level
		if (fromPhase.getM_Product_ID() != 0) {
			MOrderLine orderLine = new MOrderLine(order);
			orderLine.setLine(fromPhase.getSeqNo());
			StringBuilder stringBuilder = new StringBuilder(fromPhase.getName());
			if (fromPhase.getDescription() != null && fromPhase.getDescription().length() > 0)
				stringBuilder.append(" - ").append(fromPhase.getDescription());
			orderLine.setDescription(stringBuilder.toString());
			//
			orderLine.setM_Product_ID(fromPhase.getM_Product_ID(), true);
			orderLine.setQty(fromPhase.getQty());
			orderLine.setC_Project_ID(fromProject.getC_Project_ID());
			orderLine.setC_ProjectPhase_ID(fromPhase.getC_ProjectPhase_ID());
			orderLine.setPrice();
			if (fromPhase.getPriceActual() != null && fromPhase.getPriceActual().compareTo(Env.ZERO) != 0)
				orderLine.setPrice(fromPhase.getPriceActual());
			orderLine.setTax();
			if (!orderLine.save())
				log.log(Level.SEVERE, "doIt - Lines not generated");
			return "@C_Order_ID@ " + order.getDocumentNo() + " (1)";
		}

		//	Project Phase Lines
		AtomicInteger count = new AtomicInteger(0);
		List<MProjectLine> projectLines = fromPhase.getLines();
		projectLines.stream()
				.forEach(projectLine -> {
					MOrderLine orderLine = new MOrderLine(order);
					orderLine.setLine(projectLine.getLine());
					orderLine.setDescription(projectLine.getDescription());
					//
					orderLine.setM_Product_ID(projectLine.getM_Product_ID(), true);
					orderLine.setQty(projectLine.getPlannedQty().subtract(projectLine.getInvoicedQty()));
					orderLine.setPrice();
					if (projectLine.getPlannedPrice() != null && projectLine.getPlannedPrice().compareTo(Env.ZERO) != 0)
						orderLine.setPrice(projectLine.getPlannedPrice());
					orderLine.setDiscount();
					orderLine.setTax();
					orderLine.setC_Project_ID(fromProject.getC_Project_ID());
					orderLine.setC_ProjectPhase_ID(projectLine.getC_ProjectPhase_ID());
					orderLine.saveEx();
					count.getAndUpdate(no -> no + 1);
				});    //	for all lines
		if (projectLines.size() != count.get())
			log.log(Level.SEVERE, "Lines difference - ProjectLines=" + projectLines.size() + " <> Saved=" + count.get());

		//	Project Tasks
		List<MProjectTask> tasks = fromPhase.getTasks();
		tasks.stream().filter(task -> task.getM_Product_ID() != 0).forEach(fromTask -> {
			{
				MOrderLine orderLine = new MOrderLine(order);
				orderLine.setLine(fromTask.getSeqNo());
				StringBuilder stringBuilder = new StringBuilder(fromTask.getName());
				if (fromTask.getDescription() != null && fromTask.getDescription().length() > 0)
					stringBuilder.append(" - ").append(fromTask.getDescription());
				orderLine.setDescription(stringBuilder.toString());
				orderLine.setM_Product_ID(fromTask.getM_Product_ID(), true);
				orderLine.setQty(fromTask.getQty());
				orderLine.setPrice();
				orderLine.setC_Project_ID(fromProject.getC_Project_ID());
				orderLine.setC_ProjectPhase_ID(fromTask.getC_ProjectPhase_ID());
				orderLine.setC_ProjectTask_ID(fromTask.getC_ProjectTask_ID());
				orderLine.setTax();
				orderLine.saveEx();
				count.getAndUpdate(no -> no + 1);
			}
				});    //	for all lines
		if (tasks.size() != count.get() - projectLines.size())
			log.log(Level.SEVERE, "doIt - Lines difference - ProjectTasks=" + tasks.size() + " <> Saved=" + count.get());

		return "@C_Order_ID@ " + order.getDocumentNo() + " (" + count + ")";
	}	//	doIt

}	//	ProjectPhaseGenOrder
