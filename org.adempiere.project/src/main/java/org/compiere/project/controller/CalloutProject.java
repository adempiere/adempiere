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
package org.compiere.project.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.Properties;

import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.I_C_ProjectPhase;
import org.compiere.model.I_C_ProjectTask;
import org.compiere.model.MProjectTypePhase;
import org.compiere.model.MProjectTypeTask;
import org.compiere.util.Env;


/**
 *	Project Callouts
 *	
 *  @author Jorg Janke
 *  @version $Id: CalloutProject.java,v 1.3 2006/07/30 00:51:04 jjanke Exp $
 */
public class CalloutProject extends CalloutEngine
{
	/**
	 *	Project Planned - Price + Qty.
	 *		- called from PlannedPrice, PlannedQty
	 *		- calculates PlannedAmt (same as Trigger)
	 *  @param ctx context
	 *  @param WindowNo current Window No
	 *  @param mTab Grid Tab
	 *  @param mField Grid Field
	 *  @param value New Value
	 *  @return null or error message
	 */
	public  String planned (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";

		BigDecimal PlannedQty, PlannedPrice;
		int StdPrecision = Env.getContextAsInt(ctx, WindowNo, "StdPrecision");


		//	get values
		PlannedQty = (BigDecimal)mTab.getValue("PlannedQty");
		if (PlannedQty == null)
			PlannedQty = Env.ONE;
		PlannedPrice = ((BigDecimal)mTab.getValue("PlannedPrice"));
		if (PlannedPrice == null)
			PlannedPrice = Env.ZERO;
		//
		BigDecimal PlannedAmt = PlannedQty.multiply(PlannedPrice);
		if (PlannedAmt.scale() > StdPrecision)
			PlannedAmt = PlannedAmt.setScale(StdPrecision, RoundingMode.HALF_UP);
		//
		log.fine("PlannedQty=" + PlannedQty + " * PlannedPrice=" + PlannedPrice + " -> PlannedAmt=" + PlannedAmt + " (Precision=" + StdPrecision+ ")");
		mTab.setValue("PlannedAmt", PlannedAmt);
		return "";
	}	//	planned

	/**
	 * Fill Project Standard Phase
	 * @param ctx
	 * @param windowNo
	 * @param gridTab
	 * @param gridField
	 * @param value
	 * @return
	 */
	public String projectPhase(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		Optional<I_C_ProjectPhase> projectPhaseOptional = Optional.of(GridTabWrapper.create(gridTab, I_C_ProjectPhase.class));
		projectPhaseOptional.ifPresent(projectPhase -> {
			MProjectTypePhase projectTypePhase = (MProjectTypePhase) projectPhase.getC_Phase();
			if (projectPhase.getC_Phase_ID() > 0) {
				if (projectPhase.getName() == null || projectPhase.getName().isEmpty())
					projectPhase.setName(projectTypePhase.getName());
				if (projectPhase.getDescription() == null || projectPhase.getDescription().isEmpty())
					projectPhase.setDescription(projectTypePhase.getDescription());
				if (projectPhase.getHelp() == null || projectPhase.getHelp().isEmpty())
					projectPhase.setHelp(projectTypePhase.getHelp());
				if (projectPhase.getPriorityRule() == null || projectPhase.getPriorityRule().isEmpty())
					projectPhase.setPriorityRule(projectTypePhase.getPriorityRule());

				projectPhase.setIsMilestone(projectTypePhase.isMilestone());

				if(projectPhase.getDurationUnit() == null || projectPhase.getDurationUnit().isEmpty())
					projectPhase.setDurationUnit(projectTypePhase.getDurationUnit());
				if (projectPhase.getDurationEstimated().signum() == 0)
					projectPhase.setDurationEstimated(projectTypePhase.getDurationEstimated());

				if (projectPhase.getM_Product_ID() <=0 )
					projectPhase.setM_Product_ID(projectTypePhase.getM_Product_ID());

				if (projectPhase.getPP_Product_BOM_ID() <= 0)
					projectPhase.setPP_Product_BOM_ID(projectTypePhase.getPP_Product_BOM_ID());

				if (projectPhase.getAD_Workflow_ID() <= 0)
					projectPhase.setAD_Workflow_ID(projectTypePhase.getAD_Workflow_ID());

				if (projectPhase.getQty().signum() == 0)
					projectPhase.setQty(projectTypePhase.getStandardQty());

				projectPhase.setIsIndefinite(projectTypePhase.isIndefinite());
				projectPhase.setIsRecurrent(projectTypePhase.isRecurrent());
				projectPhase.setFrequencyType(projectTypePhase.getFrequencyType());
				projectPhase.setFrequency(projectTypePhase.getFrequency());
				projectPhase.setRunsMax(projectTypePhase.getRunsMax());
			}
		});
		return "";
	}

	/**
	 * Fill Project Task from Project Standard Task
	 * @param ctx
	 * @param windowNo
	 * @param gridTab
	 * @param gridField
	 * @param value
	 * @return
	 */
	public String projectTask(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		Optional<I_C_ProjectTask> projectTaskOptional = Optional.of(GridTabWrapper.create(gridTab, I_C_ProjectTask.class));
		projectTaskOptional.ifPresent(projectTask -> {
			MProjectTypeTask projectTypeTask = (MProjectTypeTask) projectTask.getC_Task();
			if (projectTask.getC_Task_ID() > 0) {
				if (projectTask.getName() == null || projectTask.getName().isEmpty())
					projectTask.setName(projectTypeTask.getName());
				if (projectTask.getDescription() == null || projectTask.getDescription().isEmpty())
					projectTask.setDescription(projectTypeTask.getDescription());
				if (projectTask.getHelp() == null || projectTask.getHelp().isEmpty())
					projectTask.setHelp(projectTypeTask.getHelp());
				if (projectTask.getPriorityRule() == null || projectTask.getPriorityRule().isEmpty())
					projectTask.setPriorityRule(projectTypeTask.getPriorityRule());

				projectTask.setIsMilestone(projectTypeTask.isMilestone());

				if (projectTask.getDurationUnit() == null || projectTask.getDurationUnit().isEmpty())
					projectTask.setDurationUnit(projectTypeTask.getDurationUnit());
				if (projectTask.getDurationEstimated().signum() == 0 )
					projectTask.setDurationEstimated(projectTypeTask.getDurationEstimated());
				if (projectTask.getM_Product_ID() <= 0)
					projectTask.setM_Product_ID(projectTypeTask.getM_Product_ID());

				if (projectTask.getPP_Product_BOM_ID() <= 0)
					projectTask.setPP_Product_BOM_ID(projectTypeTask.getPP_Product_BOM_ID());

				if (projectTask.getAD_Workflow_ID() <= 0)
					projectTask.setAD_Workflow_ID(projectTypeTask.getAD_Workflow_ID());

				projectTask.setIsIndefinite(projectTypeTask.isIndefinite());
				projectTask.setIsRecurrent(projectTypeTask.isRecurrent());
				projectTask.setFrequencyType(projectTypeTask.getFrequencyType());
				projectTask.setFrequency(projectTypeTask.getFrequency());
				projectTask.setRunsMax(projectTypeTask.getRunsMax());
			}
		});
		return "";
	}

	/**
	 * Complete percentage or complete flag
	 * @param ctx
	 * @param windowNo
	 * @param gridTab
	 * @param gridField
	 * @param value
	 * @return
	 */
	public String completeTask (Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		Optional<I_C_ProjectTask> projectTaskOptional = Optional.of(GridTabWrapper.create(gridTab, I_C_ProjectTask.class));
		projectTaskOptional.ifPresent(projectTask -> {
			if (projectTask.getPercentageCompleted().compareTo( new BigDecimal(100)) == 0)
			{
				if (!projectTask.isComplete())
					projectTask.setIsComplete(true);
			}
			if (projectTask.isComplete())
			{
				if (projectTask.getPercentageCompleted().compareTo(new BigDecimal(100)) <= 0)
					projectTask.setPercentageCompleted(new BigDecimal(100));
			}

		});
		return null;
	}
}	//	CalloutProject
