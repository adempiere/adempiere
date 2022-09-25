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

import org.adempiere.core.domains.models.I_C_ProjectLine;
import org.adempiere.core.domains.models.X_C_ProjectTask;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

/**
 * 	Project Phase Task Model
 *
 *	@author Jorg Janke
 *  @author Víctor Pérez Juárez , victor.perez@e-evolution.com , http://www.e-evolution.com
 *  <a href="https://github.com/adempiere/adempiere/issues/1478">
 *  <li>Add support to create request based on Standard Request Type setting on Project Type #1478
 */
public class MProjectTask extends X_C_ProjectTask
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6714520156233475723L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_ProjectTask_ID id
	 *	@param trxName transaction
	 */
	public MProjectTask (Properties ctx, int C_ProjectTask_ID, String trxName)
	{
		super (ctx, C_ProjectTask_ID, trxName);
		if (C_ProjectTask_ID == 0)
		{
		//	setC_ProjectTask_ID (0);	//	PK
		//	setC_ProjectPhase_ID (0);	//	Parent
		//	setC_Task_ID (0);			//	FK
			setSeqNo (0);
		//	setName (null);
			setQty (Env.ZERO);
		}
	}	//	MProjectTask

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MProjectTask (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MProjectTask

	/**
	 * 	Parent Constructor
	 * 	@param phase parent
	 */
	public MProjectTask (MProjectPhase phase)
	{
		this (phase.getCtx(), 0, phase.get_TrxName());
		setClientOrg(phase);
		setC_ProjectPhase_ID(phase.getC_ProjectPhase_ID());
	}	//	MProjectTask

	/**
	 * 	Copy Constructor
	 *	@param phase parent
	 *	@param task type copy
	 */
	public MProjectTask (MProjectPhase phase, MProjectTypeTask task)
	{
		this (phase);
		//
		setC_Task_ID (task.getC_Task_ID());			//	FK
		setSeqNo (task.getSeqNo());
		setName (task.getName());
		setDescription(task.getDescription());
		setHelp(task.getHelp());
		if (task.getM_Product_ID() > 0)
			setM_Product_ID(task.getM_Product_ID());
		if (task.getPP_Product_BOM_ID() > 0 )
			setPP_Product_BOM_ID(task.getPP_Product_BOM_ID());
		if (task.getAD_Workflow_ID() > 0)
			setAD_Workflow_ID(task.getAD_Workflow_ID());
		if (phase.getC_Campaign_ID() > 0)
			setC_Campaign_ID(phase.getC_Campaign_ID());
		if (phase.getC_Activity_ID() > 0)
			setC_Activity_ID(phase.getC_Activity_ID());
		if (phase.getC_SalesRegion_ID() > 0)
			setC_SalesRegion_ID(phase.getC_SalesRegion_ID());
		if (phase.getAD_OrgTrx_ID() > 0)
			setAD_OrgTrx_ID(phase.getAD_OrgTrx_ID());
		if (phase.getUser1_ID() > 0)
			setUser1_ID(phase.getUser1_ID());
		if (phase.getUser2_ID() > 0)
			setUser2_ID(phase.getUser2_ID());
		if (phase.getUser3_ID() > 0)
			setUser3_ID(phase.getUser3_ID());
		if (phase.getUser4_ID() > 0)
			setUser4_ID(phase.getUser4_ID());


		setPriorityRule(task.getPriorityRule());
		setIsMilestone(task.isMilestone());
		setIsIndefinite(task.isIndefinite());
		setIsRecurrent(task.isRecurrent());
		setFrequencyType(task.getFrequencyType());
		setFrequency(task.getFrequency());
		setRunsMax(task.getRunsMax());
		setDurationUnit(task.getDurationUnit());
		setDurationEstimated(task.getDurationEstimated());
		setQty(task.getStandardQty());
	}	//	MProjectTask

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (getDateDeadline() == null)
		{
			Timestamp phaseStartDate = getC_ProjectPhase().getStartDate();
			if (phaseStartDate != null &&  getDurationUnit() != null)
			{
				Timestamp deadLine = TimeUtil.addDuration(phaseStartDate, getDurationUnit() , getDurationEstimated());
				setDateDeadline(deadLine);
			}
		}
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
		if (getC_Task_ID() > 0 && getC_Task().getR_StandardRequestType_ID() > 0) {
			MStandardRequestType standardRequestType = (MStandardRequestType)getC_Task().getR_StandardRequestType();
			List<MRequest> requests =  standardRequestType.createStandardRequest(this);
			requests.stream().forEach(request -> {
				request.setC_Project_ID(getC_Project_ID(false));
				request.setC_ProjectPhase_ID(getC_ProjectPhase_ID());
				request.setC_ProjectTask_ID(getC_ProjectTask_ID());
				request.setDateStartPlan(getDateStartSchedule());
				request.setDateCompletePlan(getDateFinishSchedule());
				request.saveEx();
			});
		}
	}

	/**************************************************************************
	 * 	Get Project Lines
	 * 	BF 3067850 - monhate
	 *	@return Array of lines
	 */	public MProjectLine[] getLines()
	{
		final String whereClause = "C_ProjectPhase_ID=? and C_ProjectTask_ID=? ";
		List <MProjectLine> list = new Query(getCtx(), I_C_ProjectLine.Table_Name, whereClause, get_TrxName())
			.setParameters(getC_ProjectPhase_ID(), getC_ProjectTask_ID())
			.setOrderBy("Line")
			.list();		
		//
		MProjectLine[] retValue = new MProjectLine[list.size()];
		list.toArray(retValue);
		return retValue;
	}
	 
	/**
	 * 	Copy Lines from other Task
	 * 	BF 3067850 - monhate
	 *	@param fromTask from Task
	 *	@return number of lines copied
	 */
	public int copyLinesFrom (MProjectTask fromTask)
	{
		if (fromTask == null)
			return 0;
		int count = 0;
		//
		MProjectLine[] fromLines = fromTask.getLines();
		//	Copy Project Lines
		for (int i = 0; i < fromLines.length; i++)
		{
				MProjectLine toLine = new MProjectLine(getCtx (), 0, get_TrxName());
				PO.copyValues (fromLines[i], toLine, getAD_Client_ID (), getAD_Org_ID ());
				toLine.setC_Project_ID(getC_Project_ID(false));
				toLine.setC_ProjectPhase_ID (getC_ProjectPhase_ID ());
				toLine.setC_ProjectTask_ID(getC_ProjectTask_ID ());
				if (toLine.save ())
					count++;
		}
		if (fromLines.length != count)
			log.warning("Count difference - ProjectLine=" + fromLines.length + " <> Saved=" + count);

		return count;		
	}

	private int C_Project_ID = 0;

	private int getC_Project_ID(boolean reQuery) {
			if (C_Project_ID==0 || reQuery)
				C_Project_ID = getC_ProjectPhase().getC_Project_ID();
			return C_Project_ID;
	}

	/**
	 * Get Order based on this project Task
	 * @return
	 */
	public List<MOrder> getOrders()
	{
		StringBuilder whereClause = new StringBuilder();
		whereClause.append("EXISTS (SELECT 1 FROM C_OrderLine ol WHERE ol.C_Order_ID = C_Order.C_Order_ID AND ol.C_ProjectTask_ID=?)");
		return new Query(getCtx(), MOrder.Table_Name, whereClause.toString(), get_TrxName())
				.setClient_ID()
				.setParameters(getC_ProjectTask_ID())
				.list();
	}


	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MProjectTask[");
		sb.append (get_ID())
			.append ("-").append (getSeqNo())
			.append ("-").append (getName())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
}	//	MProjectTask
