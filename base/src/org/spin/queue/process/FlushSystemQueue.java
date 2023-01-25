/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.spin.queue.process;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import org.adempiere.core.domains.models.I_AD_Queue;
import org.compiere.model.Query;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.spin.queue.model.MADQueue;
import org.spin.queue.model.MADQueueType;
import org.spin.queue.util.QueueLoader;
import org.spin.queue.util.QueueManager;

/** Generated Process for (Flush System Queue)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public class FlushSystemQueue extends FlushSystemQueueAbstract {
	
	/**	Counter	*/
	private AtomicInteger counter = new AtomicInteger();
	/**	Errors	*/
	private AtomicInteger errors = new AtomicInteger();
	
	@Override
	protected String doIt() throws Exception {
		if(getBatchsToProcess() > 0
				&& getRecordsByBatch() > 0) {
			StringBuffer whereClause = new StringBuffer(I_AD_Queue.COLUMNNAME_Processed).append(" = 'N'");
			List<Object> parameters = new ArrayList<Object>();
			if(getQueueTypeId() > 0) {
				whereClause.append(" AND ").append(I_AD_Queue.COLUMNNAME_AD_QueueType_ID).append(" = ?");
				parameters.add(getQueueTypeId());
			}
			Timestamp now = new Timestamp(System.currentTimeMillis());
			now = TimeUtil.getDayTime(now, now);
			whereClause.append(" AND ").append(I_AD_Queue.COLUMNNAME_Updated).append(" < ?");
			parameters.add(now);
			AtomicReference<Timestamp> referenceTime = new AtomicReference<Timestamp>(now);
			//	For batch
			Trx.run(transactionName -> {
				IntStream.range(0, getBatchsToProcess()).forEach(page -> {
					new Query(getCtx(), I_AD_Queue.Table_Name, whereClause.toString(), transactionName)
						.setParameters(parameters)
						.setClient_ID()
						.setLimit(getRecordsByBatch())
						.setOrderBy(I_AD_Queue.COLUMNNAME_Created)
						.getIDsAsList()
						.forEach(queueId -> {
							MADQueue queueToProcess = new MADQueue(getCtx(), queueId, transactionName);
							if(isValidToProcess(queueToProcess, referenceTime.get())) {
								QueueManager queueManager = QueueLoader.getInstance()
										.getQueueManager(queueToProcess.getAD_QueueType_ID())
										.withContext(getCtx())
										.withTransactionName(transactionName);
								try {
									queueManager.process(queueToProcess, isDeleteAfterProcess());
									counter.incrementAndGet();
								} catch (Exception e) {
									errors.incrementAndGet();
									addLog("@AD_Queue_ID@: [" + queueToProcess + "]: " + e.getLocalizedMessage());
									log.severe(queueToProcess + ": " + e.getLocalizedMessage());
								}
							}
						});
				});
			});
		}
		return "@Processed@: " + counter + " @Errors@: " + errors;
	}
	
	/**
	 * Is valid to process a queue
	 * @param queueToProcess
	 * @param now
	 * @return
	 */
	private boolean isValidToProcess(MADQueue queueToProcess, Timestamp now) {
		return addWaitingTime(queueToProcess).before(now);
	}
	
	/**
	 * Add Waiting Time
	 * @param queueToProcess
	 * @return
	 */
	private Timestamp addWaitingTime(MADQueue queueToProcess) {
		MADQueueType queueType = MADQueueType.getById(getCtx(), queueToProcess.getAD_QueueType_ID(), queueToProcess.get_TrxName());
		if(queueType.getWaitingTime() > 0
				&& !Util.isEmpty(queueType.getTimeUnit())) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(queueToProcess.getUpdated());
			if(queueType.getTimeUnit().equals(MADQueueType.TIMEUNIT_Year)) {
				calendar.add(Calendar.YEAR, queueType.getWaitingTime());
			} else if(queueType.getTimeUnit().equals(MADQueueType.TIMEUNIT_Month)) {
				calendar.add(Calendar.MONTH, queueType.getWaitingTime());
			} else if(queueType.getTimeUnit().equals(MADQueueType.TIMEUNIT_Week)) {
				calendar.add(Calendar.WEEK_OF_MONTH, queueType.getWaitingTime());
			} else if(queueType.getTimeUnit().equals(MADQueueType.TIMEUNIT_Day)) {
				calendar.add(Calendar.DAY_OF_YEAR, queueType.getWaitingTime());
			} else if(queueType.getTimeUnit().equals(MADQueueType.TIMEUNIT_Hour)) {
				calendar.add(Calendar.HOUR_OF_DAY, queueType.getWaitingTime());
			} else if(queueType.getTimeUnit().equals(MADQueueType.TIMEUNIT_Minute)) {
				calendar.add(Calendar.MINUTE, queueType.getWaitingTime());
			}
			return new Timestamp(calendar.getTimeInMillis());
		}
		return queueToProcess.getUpdated();
	}
}