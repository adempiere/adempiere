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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.compiere.model.Query;
import org.compiere.util.Trx;
import org.spin.queue.model.I_AD_Queue;
import org.spin.queue.model.MADQueue;
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
			List<Object> parameters = new ArrayList<>();
			if(getQueueTypeId() > 0) {
				whereClause.append(" AND ").append(I_AD_Queue.COLUMNNAME_AD_QueueType_ID).append(" = ?");
				parameters.add(getQueueTypeId());
			}
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
						});
				});
			});
		}
		return "@Processed@: " + counter + " @Errors@: " + errors;
	}
}