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

import org.adempiere.core.domains.models.I_AD_Scheduler;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProcess;
import org.compiere.model.MScheduler;
import org.compiere.model.MSchedulerPara;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.spin.model.MADAppRegistration;
import org.spin.model.MADAppSupport;
import org.spin.queue.notification.DefaultNotifier;
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
		//	App registration
		createDefaultNotifiers(context, transactionName);
		//	Scheduler
		createSchedule(context, transactionName);
		//	financial management
		return "@AD_SetupDefinition_ID@ @Ok@";
	}
	
	/**
	 * Create notifiers as app registration
	 * @param context
	 * @param transactionName
	 */
	private void createDefaultNotifiers(Properties context, String transactionName) {
		MADAppRegistration emailSender = MADAppRegistration.getByApplicationType(context, DefaultNotifier.DefaultNotificationType_EMail, transactionName);
		if(emailSender == null
				|| emailSender.getAD_AppRegistration_ID() <= 0) {
			MADAppSupport emailSupport = MADAppSupport.getByApplicationType(context, DefaultNotifier.DefaultNotificationType_EMail, transactionName);
			if(emailSupport == null
					|| emailSupport.getAD_AppSupport_ID() <= 0) {
				throw new AdempiereException("@AD_AppSupport_ID@ @EMail@ @NotFound@");
			}
			emailSender = new MADAppRegistration(context, 0, transactionName);
			emailSender.setValue("EMail");
			emailSender.setApplicationType(DefaultNotifier.DefaultNotificationType_EMail);
			emailSender.setAD_AppSupport_ID(emailSupport.getAD_AppSupport_ID());
			emailSender.setName("Default EMail Sender");
			emailSender.setVersionNo("1.0");
			emailSender.setHost("localhost");
			emailSender.setPort(0);
			emailSender.saveEx();
		}
		MADAppRegistration notesSender = MADAppRegistration.getByApplicationType(context, DefaultNotifier.DefaultNotificationType_Notes, transactionName);
		if(notesSender == null
				|| notesSender.getAD_AppRegistration_ID() <= 0) {
			MADAppSupport notesSupport = MADAppSupport.getByApplicationType(context, DefaultNotifier.DefaultNotificationType_Notes, transactionName);
			if(notesSupport == null
					|| notesSupport.getAD_AppSupport_ID() <= 0) {
				throw new AdempiereException("@AD_AppSupport_ID@ @Note@ @NotFound@");
			}
			notesSender = new MADAppRegistration(context, 0, transactionName);
			notesSender.setValue("Notes");
			notesSender.setApplicationType(DefaultNotifier.DefaultNotificationType_Notes);
			notesSender.setAD_AppSupport_ID(notesSupport.getAD_AppSupport_ID());
			notesSender.setName("Default Notes Sender");
			notesSender.setVersionNo("1.0");
			notesSender.setHost("localhost");
			notesSender.setPort(0);
			notesSender.saveEx();
		}
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
		scheduler.setSupervisor_ID(Env.getAD_User_ID(context));
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
