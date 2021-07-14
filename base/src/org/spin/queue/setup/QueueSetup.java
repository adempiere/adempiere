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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.queue.setup;

import java.util.Properties;

import org.compiere.model.I_AD_Scheduler;
import org.compiere.model.MProcess;
import org.compiere.model.MScheduler;
import org.compiere.model.MSchedulerPara;
import org.compiere.model.Query;
import org.compiere.util.TimeUtil;
import org.spin.queue.process.FlushSystemQueue;
import org.spin.util.ISetupDefinition;

/**
 * Testing class for setup
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class QueueSetup implements ISetupDefinition {

	private static final String SETUP_DESCRIPTION = "(*Created from Setup Automatically*)";
	
	@Override
	public String doIt(Properties context, String transactionName) {
		//	Scheduler
		createSchedule(context, transactionName);
		//	financial management
		return "@AD_SetupDefinition_ID@ @Ok@";
	}
	
	/**
	 * Create Schedule for setup
	 * @param context
	 * @param transactionName
	 */
	private void createSchedule(Properties context, String transactionName) {
		//	Delete before apply
		new Query(context, I_AD_Scheduler.Table_Name, I_AD_Scheduler.COLUMNNAME_AD_Process_ID + " = ?", transactionName)
			.setParameters(FlushSystemQueue.getProcessId())
			.setClient_ID()
			.list()
			.forEach(scheduler -> scheduler.delete(true));
		MScheduler scheduler = new MScheduler(context, 0, transactionName);
		scheduler.setAD_Org_ID(0);
		scheduler.setName(FlushSystemQueue.getProcessName());
		scheduler.setDescription(SETUP_DESCRIPTION);
		scheduler.setAD_Process_ID(FlushSystemQueue.getProcessId());
		scheduler.setSupervisor_ID(100);
		scheduler.setFrequencyType(MScheduler.FREQUENCYTYPE_Minute);
		scheduler.setFrequency(5);
		scheduler.setKeepLogDays(7);
		scheduler.setDateNextRun(TimeUtil.addMinutess(TimeUtil.getDay(System.currentTimeMillis()), scheduler.getFrequency()));
		scheduler.saveEx();
		//	Get Process
		MProcess process = MProcess.get(context, FlushSystemQueue.getProcessId());
		//	Batch Quantity
		process.getParametersAsList().stream().filter(parameter -> parameter.getColumnName().equals(FlushSystemQueue.BATCHSTOPROCESS)).findFirst().ifPresent(parameter -> {
			MSchedulerPara schedulerParameter = new MSchedulerPara(context, 0, transactionName);
			schedulerParameter.setAD_Org_ID(0);
			schedulerParameter.setAD_Scheduler_ID(scheduler.getAD_Scheduler_ID());
			schedulerParameter.setAD_Process_Para_ID(parameter.getAD_Process_Para_ID());
			schedulerParameter.setParameterDefault("10");
			schedulerParameter.saveEx();
		});
		//	Records Quantity
		process.getParametersAsList().stream().filter(parameter -> parameter.getColumnName().equals(FlushSystemQueue.RECORDSBYBATCH)).findFirst().ifPresent(parameter -> {
			MSchedulerPara schedulerParameter = new MSchedulerPara(context, 0, transactionName);
			schedulerParameter.setAD_Org_ID(0);
			schedulerParameter.setAD_Scheduler_ID(scheduler.getAD_Scheduler_ID());
			schedulerParameter.setAD_Process_Para_ID(parameter.getAD_Process_Para_ID());
			schedulerParameter.setParameterDefault("100");
			schedulerParameter.saveEx();
		});
		//	Delete records
		process.getParametersAsList().stream().filter(parameter -> parameter.getColumnName().equals(FlushSystemQueue.ISDELETEAFTERPROCESS)).findFirst().ifPresent(parameter -> {
			MSchedulerPara schedulerParameter = new MSchedulerPara(context, 0, transactionName);
			schedulerParameter.setAD_Org_ID(0);
			schedulerParameter.setAD_Scheduler_ID(scheduler.getAD_Scheduler_ID());
			schedulerParameter.setAD_Process_Para_ID(parameter.getAD_Process_Para_ID());
			schedulerParameter.setParameterDefault("N");
			schedulerParameter.saveEx();
		});
	}
}
