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
package org.compiere.server;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

import org.compiere.model.MClient;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MRole;
import org.compiere.model.MScheduler;
import org.compiere.model.MSchedulerLog;
import org.compiere.model.MUser;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.eevolution.service.dsl.ProcessBuilder;
import org.spin.queue.notification.DefaultNotifier;
import org.spin.queue.util.QueueLoader;

import it.sauronsoftware.cron4j.Predictor;
import it.sauronsoftware.cron4j.SchedulingPattern;


/**
 *	Scheduler
 *
 *  @author Jorg Janke
 *  @version $Id: Scheduler.java,v 1.5 2006/07/30 00:53:33 jjanke Exp $
 *  
 *  Contributors:
 *    Carlos Ruiz - globalqss - FR [3135351] - Enable Scheduler for buttons
 *    Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com - Fixes default parameters
 */
public class Scheduler extends AdempiereServer
{
	/**
	 * 	Scheduler
	 *	@param model model
	 */
	public Scheduler (MScheduler model)
	{
		super (model, 240);		//	nap
		schedulerConfiguration = model;
	//	m_client = MClient.get(model.getCtx(), model.getAD_Client_ID());
	}	//	Scheduler

	/**	The Concrete Model			*/
	private MScheduler			schedulerConfiguration = null;
	/**	Last Summary				*/
	private StringBuffer 		summary = new StringBuffer();
	/** Transaction					*/
	private Trx 				transaction = null;

	private it.sauronsoftware.cron4j.Scheduler cronScheduler;
	private Predictor 			predictor;
	
	// ctx for the report/process
	Properties 					schedulerContext = new Properties();

	/**
	 * 	Work
	 */
	protected void doWork ()
	{
		summary = new StringBuffer(schedulerConfiguration.toString())
			.append(" - ");

		// Prepare a ctx for the report/process - BF [1966880]
		schedulerContext.clear();
		MClient schedclient = MClient.get(getCtx(), schedulerConfiguration.getAD_Client_ID());
		Env.setContext(schedulerContext, "#AD_Client_ID", schedclient.getAD_Client_ID());
		Env.setContext(schedulerContext, "#AD_Language", schedclient.getAD_Language());
		Env.setContext(schedulerContext, "#AD_Org_ID", schedulerConfiguration.getAD_Org_ID());
		if (schedulerConfiguration.getAD_Org_ID() != 0) {
			MOrgInfo schedorg = MOrgInfo.get(getCtx(), schedulerConfiguration.getAD_Org_ID(), null);
			if (schedorg.getM_Warehouse_ID() > 0)
				Env.setContext(schedulerContext, "#M_Warehouse_ID", schedorg.getM_Warehouse_ID());
		}
		Env.setContext(schedulerContext, "#AD_User_ID", getAD_User_ID());
		Env.setContext(schedulerContext, "#SalesRep_ID", getAD_User_ID());
		// TODO: It can be convenient to add  AD_Scheduler.AD_Role_ID
		MUser scheduser = MUser.get(getCtx(), getAD_User_ID());
		MRole[] schedroles = scheduser.getRoles(schedulerConfiguration.getAD_Org_ID());
		if (schedroles != null && schedroles.length > 0)
			Env.setContext(schedulerContext, "#AD_Role_ID", schedroles[0].getAD_Role_ID()); // first role, ordered by AD_Role_ID
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat dateFormat4Timestamp = new SimpleDateFormat("yyyy-MM-dd"); 
		Env.setContext(schedulerContext, "#Date", dateFormat4Timestamp.format(ts)+" 00:00:00" );    //  JDBC format
		Properties currentctx = Env.getCtx();
		Env.setCtx(schedulerContext);

		transaction = Trx.get(Trx.createTrxName("Scheduler"), true);
		MProcess process = new MProcess(schedulerContext, schedulerConfiguration.getAD_Process_ID(), transaction.getTrxName());
		try
		{
			process.set_TrxName(transaction.getTrxName());
			summary.append(runProcess(process));
			transaction.commit(true);
		}
		catch (Exception e)
		{
			if (transaction != null)
				transaction.rollback();
			log.log(Level.WARNING, process.toString(), e);
			summary.append(e.toString());
		}
		finally
		{
			if (transaction != null)
				transaction.close();
		}
		
		// Restore system context
		Env.setCtx(currentctx);

		//
		int no = schedulerConfiguration.deleteLog();
		summary.append(" Logs deleted=").append(no);
		if (schedulerConfiguration.get_TrxName() == null ) {
			Trx.run(this::addSchedulerLog);
		} else {
			addSchedulerLog(schedulerConfiguration.get_TrxName());
		}

	}	//	doWork

	/**
	 * Add Scheduler Log
	 * @param trxName
	 */
	private void addSchedulerLog(String trxName) {
		MSchedulerLog schedulerLog = new MSchedulerLog(schedulerConfiguration, summary.toString(), trxName);
		schedulerLog.setReference("#" + p_runCount + " - " + TimeUtil.formatElapsed(new Timestamp(p_startWork)));
		schedulerLog.saveEx();
	}
	/**
	 * 	Run Process or Report
	 *	@param process process
	 *	@return summary
	 *	@throws Exception
	 */
	private String runProcess(MProcess process) throws Exception {
		log.info(process.toString());
		
		boolean isReport = (process.isReport() || process.getAD_ReportView_ID() > 0);
		//
		ProcessBuilder builder = ProcessBuilder.create(getCtx())
			.process(process.getAD_Process_ID())
			.withClientId(schedulerConfiguration.getAD_Client_ID())
			.withUserId(getAD_User_ID())
			.withTitle(process.getName())
			.withRecordId(schedulerConfiguration.getAD_Table_ID(), schedulerConfiguration.getRecord_ID());
		//	Fill It
		fillParameter(builder);
		try {
			builder.execute();
		} catch (Exception e) {
			log.severe(e.getLocalizedMessage());
		}
		//	Get Process Info
		ProcessInfo info = builder.getProcessInfo();
		if(info.isError()) {
			// notify supervisor if error
			int supervisor = schedulerConfiguration.getSupervisor_ID();
			if (supervisor > 0) {
				ProcessInfoUtil.setLogFromDB(info);
				//	
				Trx.run(transactionName -> {
					//	Get instance for notifier
					DefaultNotifier notifier = (DefaultNotifier) QueueLoader.getInstance().getQueueManager(DefaultNotifier.QUEUETYPE_DefaultNotifier)
							.withContext(getCtx())
							.withTransactionName(transactionName);
					//	Send notification to queue
					notifier
						.clearMessage()
						.withApplicationType(DefaultNotifier.DefaultNotificationType_UserDefined)
						.withUserId(getAD_User_ID())
						.addRecipient(supervisor)
						.withText(info.getSummary() + " " + info.getLogInfo())
						.withDescription(process.getName())
						.withTableId(MPInstance.Table_ID)
						.withRecordId(info.getAD_PInstance_ID());
					//	Add to queue
					notifier.addToQueue();
				});
			}
		} else {
			// notify recipients on success
			Integer[] userIds = schedulerConfiguration.getRecipientByUserIds();
			StringBuffer errorsSending = new StringBuffer();
			if (userIds.length > 0)  {
				ProcessInfoUtil.setLogFromDB(info);
				Arrays.asList(userIds).forEach(userId -> {
					AtomicReference<File> report = new AtomicReference<File>();
					if (isReport) {
						//	Report
						ReportEngine re = ReportEngine.get(getCtx(), info);
						if (re != null) {
							report.set(re.getPDF());
						} else {
							if(errorsSending.length() > 0) {
								errorsSending.append(Env.NL);
							}
							errorsSending.append("@Error@ " + process.getAD_Process_ID() + " - " + process.getName());
						}
					}
					//	
					Trx.run(transactionName -> {
						//	Get instance for notifier
						DefaultNotifier notifier = (DefaultNotifier) QueueLoader.getInstance().getQueueManager(DefaultNotifier.QUEUETYPE_DefaultNotifier)
								.withContext(getCtx())
								.withTransactionName(transactionName);
						//	Send notification to queue
						notifier
							.clearMessage()
							.withApplicationType(DefaultNotifier.DefaultNotificationType_UserDefined)
							.withUserId(getAD_User_ID())
							.addRecipient(userId)
							.addAttachment(report.get())
							.withText(info.getSummary() + " " + info.getLogInfo())
							.withDescription(process.getName())
							.withTableId(schedulerConfiguration.getAD_Table_ID())
							.withRecordId(schedulerConfiguration.getRecord_ID());
						//	Change Subject and Text
						if (isReport) {
							notifier
								.withText(schedulerConfiguration.getDescription())
								.withDescription(schedulerConfiguration.getName());
						}
						//	Add to queue
						notifier.addToQueue();
					});
				});
				//	report all errors
				if(errorsSending.length() > 0) {
					info.setSummary(Optional.ofNullable(info.getSummary()).orElse("") + Env.NL + errorsSending.toString());
				}
			}
		}
		return info.getSummary();
	}	//	runProcess

	private int getAD_User_ID() {
		int AD_User_ID;
		if (schedulerConfiguration.getSupervisor_ID() > 0)
			AD_User_ID = schedulerConfiguration.getSupervisor_ID();
		else if (schedulerConfiguration.getCreatedBy() > 0)
			AD_User_ID = schedulerConfiguration.getCreatedBy();
		else if (schedulerConfiguration.getUpdatedBy() > 0)
			AD_User_ID = schedulerConfiguration.getUpdatedBy();
		else
			AD_User_ID = 100; //fall back to SuperUser
		return AD_User_ID;
	}
	
	/**
	 * 	Fill Parameter
	 *	@param pInstance process instance
	 */
	private void fillParameter(ProcessBuilder builder) {
		Arrays.asList(schedulerConfiguration.getParameters(false))
			.forEach(schedulerParameter -> {
				String variable = schedulerParameter.getParameterDefault();
				log.fine(schedulerParameter.getColumnName() + " = " + variable);
				//	Value - Constant/Variable
				Object value = variable;
				if (variable == null
					|| (variable != null && variable.length() == 0)) {
					value = null;
				} else if (  variable.indexOf('@') != -1
						&& variable.indexOf('@') != variable.lastIndexOf('@')) {
					//	Strip
					int index = variable.indexOf('@');
					String columnName = variable.substring(index+1);
					index = columnName.indexOf('@');
					if (index != -1) {
						columnName = columnName.substring(0, index);
						//	try Env
						String environment = Env.getContext(schedulerContext, columnName);
						if (environment == null || environment.length() == 0)
							environment = Env.getContext(getCtx(), columnName);
						if (environment.length() == 0) {
							log.warning(schedulerParameter.getColumnName()
								+ " - not in environment =" + columnName
								+ "(" + variable + ")");
						} else {
							value = environment;
						}
					}
				}	//	@variable@
				//	Exists a Value
				if (value != null) {
					//	Object Parameter
					Object parameterOfProcess = null;
					//	Convert to Type
					try {
						if (DisplayType.isNumeric(schedulerParameter.getDisplayType())
							|| DisplayType.isID(schedulerParameter.getDisplayType())) {
							BigDecimal decimalValue = null;
							if (value instanceof BigDecimal)
								decimalValue = (BigDecimal)value;
							else if (value instanceof Integer)
								decimalValue = new BigDecimal (((Integer)value).intValue());
							else
								decimalValue = new BigDecimal (value.toString());
							parameterOfProcess = decimalValue;
							log.fine(schedulerParameter.getColumnName()
								+ " = " + variable + " (=" + decimalValue + "=)");
						} else if (DisplayType.YesNo == schedulerParameter.getDisplayType()) {
							boolean booleanValue = false;
							if(value instanceof Boolean) {
								booleanValue = ((Boolean) value);
							} else {
								booleanValue = value.toString().replaceAll("'", "").equals("Y");
							}
							parameterOfProcess = booleanValue;
							log.fine(schedulerParameter.getColumnName()
									+ " = " + variable + " (=" + booleanValue + "=)");
						} else if (DisplayType.isDate(schedulerParameter.getDisplayType())) {
							Timestamp dateValue = null;
							if (value instanceof Timestamp)
								dateValue = (Timestamp)value;
							else
								dateValue = Timestamp.valueOf(value.toString());
							parameterOfProcess = dateValue;
							log.fine(schedulerParameter.getColumnName()
								+ " = " + variable + " (=" + dateValue + "=)");
						} else {
							parameterOfProcess = value.toString().replaceAll("'", "");
							log.fine(schedulerParameter.getColumnName()
								+ " = " + variable
								+ " (=" + value + "=) " + value.getClass().getName());
						}
						//	add to builder
						if (parameterOfProcess != null) {
							log.fine("ColumnName=" + schedulerParameter.getColumnName() + ", Value=" + parameterOfProcess);
							builder.withParameter(schedulerParameter.getColumnName(), parameterOfProcess);
						}
					}
					catch (Exception e) {
						log.warning(schedulerParameter.getColumnName()
							+ " = " + variable + " (" + value
							+ ") " + value.getClass().getName()
							+ " - " + e.getLocalizedMessage());
					}
				}
			});
	}
	/**
	 * 	Get Server Info
	 *	@return info
	 */
	public String getServerInfo()
	{
		return "#" + p_runCount + " - Last=" + summary.toString();
	}	//	getServerInfo

	@Override
	public void run() {
		String cronPattern = (String) schedulerConfiguration.getCronPattern();
		if (cronPattern != null && cronPattern.trim().length() > 0 && SchedulingPattern.validate(cronPattern)) {
			cronScheduler = new it.sauronsoftware.cron4j.Scheduler();
			cronScheduler.schedule(cronPattern, new Runnable() {
				public void run() {
					runNow();
					long next = predictor.nextMatchingTime();
					p_model.setDateNextRun(new Timestamp(next));
					p_model.saveEx();
				}
			});
			predictor = new Predictor(cronPattern);
			long next = predictor.nextMatchingTime();
			p_model.setDateNextRun(new Timestamp(next));
			p_model.saveEx();
			cronScheduler.start();
			while (true) {
				if (!sleep()) {
					cronScheduler.stop();
					break;
				} else if (!cronScheduler.isStarted()) {
					break;
				}
			}
		} else {
			super.run();
		}
	}
}	//	Scheduler
