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
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_C_ProjectLine;
import org.adempiere.core.domains.models.X_C_ProjectPhase;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

/**
 * 	Project Phase Model
 *
 *	@author Jorg Janke
 *  @author Víctor Pérez Juárez , victor.perez@e-evolution.com , http://www.e-evolution.com
 *  <a href="https://github.com/adempiere/adempiere/issues/1478">
 *  <li>Add support to create request based on Standard Request Type setting on Project Type #1478
 *	@
 */
public class MProjectPhase extends X_C_ProjectPhase
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5824045445920353065L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_ProjectPhase_ID id
	 *	@param trxName transaction
	 */
	public MProjectPhase (Properties ctx, int C_ProjectPhase_ID, String trxName)
	{
		super (ctx, C_ProjectPhase_ID, trxName);
		if (C_ProjectPhase_ID == 0)
		{
		//	setC_ProjectPhase_ID (0);	//	PK
		//	setC_Project_ID (0);		//	Parent
		//	setC_Phase_ID (0);			//	FK
			setCommittedAmt (Env.ZERO);
			setIsCommitCeiling (false);
			setIsComplete (false);
			setSeqNo (0);
		//	setName (null);
			setQty (Env.ZERO);
		}
	}	//	MProjectPhase

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MProjectPhase (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MProjectPhase

	/**
	 * 	Parent Constructor
	 *	@param project parent
	 */
	public MProjectPhase (MProject project)
	{
		this (project.getCtx(), 0, project.get_TrxName());
		setClientOrg(project);
		setC_Project_ID(project.getC_Project_ID());
	}	//	MProjectPhase

	/**
	 * 	Copy Constructor
	 *	@param project parent
	 *	@param phase copy
	 */
	public MProjectPhase (MProject project, MProjectTypePhase phase)
	{
		this (project);
		//
		setC_Phase_ID (phase.getC_Phase_ID());			//	FK
		setName (phase.getName());
		setSeqNo (phase.getSeqNo());
		setDescription(phase.getDescription());
		setHelp(phase.getHelp());
		setPriorityRule(phase.getPriorityRule());
		setIsMilestone(phase.isMilestone());
		setDurationUnit(phase.getDurationUnit());
		setDurationEstimated(phase.getDurationEstimated());
		if (phase.getM_Product_ID() > 0)
			setM_Product_ID(phase.getM_Product_ID());
		if (phase.getPP_Product_BOM_ID() > 0)
			setPP_Product_BOM_ID(phase.getPP_Product_BOM_ID());
		if (phase.getAD_Workflow_ID() > 0)
			setAD_Workflow_ID(phase.getAD_Workflow_ID());
		if (project.getC_Campaign_ID() > 0)
			setC_Campaign_ID(project.getC_Campaign_ID());
		if (project.getC_Activity_ID() > 0)
			setC_Activity_ID(project.getC_Activity_ID());
		if (project.getC_SalesRegion_ID() > 0)
			setC_SalesRegion_ID(project.getC_SalesRegion_ID());
		if (project.getAD_OrgTrx_ID() > 0)
			setAD_OrgTrx_ID(project.getAD_OrgTrx_ID());
		if (project.getUser1_ID() > 0)
			setUser1_ID(project.getUser1_ID());
		if (project.getUser2_ID() > 0)
			setUser2_ID(project.getUser2_ID());
		if (project.getUser3_ID() > 0)
			setUser3_ID(project.getUser3_ID());
		if (project.getUser4_ID() > 0)
			setUser4_ID(project.getUser4_ID());

		setQty(phase.getStandardQty());
	}	//	MProjectPhase


	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		// set date dead line if is empty
		if (getDateDeadline() == null)
		{
			Timestamp projectStartDate = getC_Project().getDateStart();
			if (projectStartDate != null &&  getDurationUnit() != null)
			{
				Timestamp deadLine = TimeUtil.addDuration(projectStartDate, getDurationUnit() , getDurationEstimated());
				setDateDeadline(deadLine);
			}
		}
		// Set Date finish Schedule if is empty using date dead line
		if (getDateFinishSchedule() == null && getDateDeadline() != null)
			setDateFinishSchedule(getDateDeadline());

		return true;
	}	//	beforeSave

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		//Create Request
		if (newRecord)
			createRequest();

		return true;
	}

	/**
	 * create Request Project
	 */
	public void createRequest()
	{
		if (getC_Phase_ID() > 0 && getC_Phase().getR_StandardRequestType_ID() > 0)
		{
			MStandardRequestType standardRequestType = (MStandardRequestType) getC_Phase().getR_StandardRequestType();
			List<MRequest> requests =  standardRequestType.createStandardRequest(this);
			requests.stream().forEach(request -> {
				request.setC_Project_ID(getC_Project_ID());
				request.setC_ProjectPhase_ID(getC_ProjectPhase_ID());
				request.setDateStartPlan(getDateStartSchedule());
				request.setDateCompletePlan(getDateFinishSchedule());
				request.saveEx();
			});
		}
	}

	/**
	 * 	Get Project Phase Tasks.
	 *	@return Array of tasks
	 */
	public List<MProjectTask> getTasks()
	{
		return  new Query(getCtx(), MProjectTask.Table_Name , COLUMNNAME_C_ProjectPhase_ID + "=?", get_TrxName())
				.setClient_ID()
				.setParameters(getC_ProjectPhase_ID())
				.setOrderBy(COLUMNNAME_SeqNo)
				.list();
	}	//	getTasks

	/**
	 * 	Copy Lines from other Phase
	 * 	BF 3067850 - monhate
	 *	@param fromPhase from phase
	 *	@return number of tasks copied
	 */
	public int copyLinesFrom (MProjectPhase fromPhase)
	{
		if (fromPhase == null)
			return 0;
		AtomicInteger count = new AtomicInteger(0);
		List<MProjectLine> fromProjectLines = fromPhase.getLines();
		fromProjectLines.stream()
				.filter(fromProjectLine -> fromProjectLine.getC_ProjectTask_ID() <= 0)
				.forEach(fromProjectLine -> {
					MProjectLine toProjectline = new MProjectLine(getCtx(), 0, get_TrxName());
					PO.copyValues(fromProjectLine, toProjectline, getAD_Client_ID(), getAD_Org_ID());
					toProjectline.setC_Project_ID(getC_Project_ID());
					toProjectline.setC_ProjectPhase_ID(getC_ProjectPhase_ID());
					toProjectline.saveEx();
					count.getAndUpdate(no -> no + 1);
				});

		if (fromProjectLines.size() != count.get())
			log.warning("Count difference - ProjectLine=" + fromProjectLines.size() + " <> Saved=" + count);

		return count.get();
	}
	
	
	/**
	 * 	Copy Tasks from other Phase
	 *  BF 3067850 - monhate
	 *	@param fromProjectPhase from phase
	 *	@return number of tasks copied
	 */
	public int copyTasksFrom (MProjectPhase fromProjectPhase)
	{
		if (fromProjectPhase == null)
			return 0;
		AtomicInteger count = new AtomicInteger(0);
		AtomicInteger countLine = new AtomicInteger(0);
		List<MProjectTask> toProjectTasks = getTasks();
		List<MProjectTask> fromProjectTasks = fromProjectPhase.getTasks();
		fromProjectTasks.stream().forEach(fromProjectTask -> {
			Boolean exists = toProjectTasks.stream().anyMatch(taskTo -> taskTo.getC_Task_ID() == fromProjectTask.getC_Task_ID());
			if (exists) {
				log.info("Task already exists here, ignored - " + fromProjectTask);
			} else {
				MProjectTask toProjectTask = new MProjectTask(getCtx(), 0, get_TrxName());
				PO.copyValues(fromProjectTask, toProjectTask, getAD_Client_ID(), getAD_Org_ID());
				toProjectTask.setC_ProjectPhase_ID(getC_ProjectPhase_ID());
				toProjectTask.setC_Task_ID(fromProjectTask.getC_Task_ID());
				toProjectTask.setProjInvoiceRule(getProjInvoiceRule());
				toProjectTask.saveEx();
				count.getAndUpdate(no -> no + 1);
				countLine.getAndUpdate(no -> no + toProjectTask.copyLinesFrom(fromProjectTask));
			}
		});

		if (fromProjectTasks.size() != count.get())
			log.warning("Count difference - ProjectPhase=" + fromProjectTasks.size() + " <> Saved=" + count.get());

		return count.get() + countLine.get();
	}	//	copyTasksFrom

	/**
	 * 	Copy Tasks from other Phase
	 *	@param fromProjectPhase from phase
	 *	@return number of tasks copied
	 */
	public int copyTasksFrom (MProjectTypePhase fromProjectPhase)
	{
		if (fromProjectPhase == null)
			return 0;
		AtomicInteger count = new AtomicInteger(0);
		//	Copy Type Tasks
		List<MProjectTypeTask> fromProjectTasks = fromProjectPhase.getTasks();
		fromProjectTasks.stream()
				.forEach(fromProjectTask -> {
					MProjectTask toProjectTask = new MProjectTask (this, fromProjectTask);
					toProjectTask.setC_ProjectPhase_ID(getC_ProjectPhase_ID());
					toProjectTask.setProjInvoiceRule(getProjInvoiceRule());
					toProjectTask.saveEx();
					count.getAndUpdate(no -> no + 1);
				});
		log.fine("#" + count.get() + " - " + fromProjectPhase);
		if (fromProjectTasks.size() != count.get())
			log.log(Level.SEVERE, "Count difference - TypePhase=" + fromProjectTasks.size() + " <> Saved=" + count.get());

		return count.get();
	}	//	copyTasksFrom
	
	/**************************************************************************
	 * 	Get Project Lines
	 * 	BF 3067850 - monhate
	 *	@return Array of lines
	 */	public List<MProjectLine> getLines()
	{
		final String whereClause = "C_Project_ID=? and C_ProjectPhase_ID=?";
		return new Query(getCtx(), I_C_ProjectLine.Table_Name, whereClause, get_TrxName())
			.setClient_ID()
			.setParameters(getC_Project_ID(), getC_ProjectPhase_ID())
			.setOrderBy("Line")
			.list();
	}

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MProjectPhase[");
		sb.append (get_ID())
			.append ("-").append (getSeqNo())
			.append ("-").append (getName())
			.append ("]");
		return sb.toString ();
	}	//	toString


	/**
	 * Get Order based on Project Phase
	 * @return
	 */
	public List<MOrder> getOrders()
	{
		StringBuilder whereClause = new StringBuilder();
		whereClause.append("EXISTS (SELECT 1 FROM C_OrderLine ol WHERE ol.C_Order_ID = C_Order.C_Order_ID AND ol.C_ProjectPhase_ID=?)");
		return new Query(getCtx(), MOrder.Table_Name, whereClause.toString(), get_TrxName())
				.setClient_ID()
				.setParameters(getC_ProjectPhase_ID())
				.list();
	}

	public Integer getBPartnerId()
	{
		return getC_Project().getC_BPartner_ID();
	}

}	//	MProjectPhase
