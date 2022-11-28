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
 * Copyright (C) 2003-2013 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.spin.queue.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Logger;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.util.Util;
import org.spin.queue.model.MADQueue;

/**
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Queue manager allows manage all system queue with util method for add 
 */
public abstract class QueueManager {
	
	protected static final Logger logger = Logger.getLogger(QueueManager.class.getName());
	
	/**
	 * Default constructor
	 */
	public QueueManager() {
		clear();
	}
	
	/**	Queue Type	*/
	private int queueTypeId;
	/**	Description	*/
	private String description;
	/**	Table Id	*/
	private int tableId;
	/**	Record Id	*/
	private int recordId;
	/**	Organization	*/
	private int organizationId;
	/**	Context	*/
	private Properties context;
	/**	Transaction Name	*/
	private String transactionName;
	/**	Persistence Object	*/
	private PO entity;
	
	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}
	
	/**
	 * @return the context
	 */
	public final Properties getContext() {
		return context;
	}
	
	/**
	 * @return the transaction name
	 */
	public final String getTransactionName() {
		return transactionName;
	}
	
	/**
	 * @param description the description to set
	 */
	public final QueueManager withDescription(String description) {
		this.description = description;
		return this;
	}
	
	/**
	 * @return the tableId
	 */
	public final int getTableId() {
		return tableId;
	}
	
	/**
	 * @param tableId the tableId to set
	 */
	private final QueueManager withTableId(int tableId) {
		this.tableId = tableId;
		return this;
	}
	
	/**
	 * @return the recordId
	 */
	public final int getRecordId() {
		return recordId;
	}
	
	/**
	 * @param recordId the recordId to set
	 */
	private final QueueManager withRecordId(int recordId) {
		this.recordId = recordId;
		return this;
	}
	
	/**
	 * @param transactionName the transactionName to set
	 */
	public final QueueManager withTransactionName(String transactionName) {
		this.transactionName = transactionName;
		return this;
	}
	
	/**
	 * @param context the context to set
	 */
	public final QueueManager withContext(Properties context) {
		this.context = context;
		return this;
	}
	
	/**
	 * @param organizationId the tableId to set
	 */
	public final QueueManager withOrganizationId(int organizationId) {
		if (getRecordId() <= 0) {
			this.organizationId = organizationId;
		}
		return this;
	}
	
	/**
	 * @return the entity
	 */
	public final PO getEntity() {
		return entity;
	}
	
	/**
	 * @return the queueTypeId
	 */
	public final int getQueueTypeId() {
		return queueTypeId;
	}
	
	/**
	 * @param queueTypeId the queueTypeId to set
	 */
	public final QueueManager withQueueTypeId(int queueTypeId) {
		this.queueTypeId = queueTypeId;
		return this;
	}
	
	/**
	 * @param entity the entity to set
	 */
	public QueueManager withEntity(PO entity) {
		this.entity = entity;
		if(entity != null) {
			withContext(entity.getCtx())
				.withTransactionName(entity.get_TrxName())
				.withOrganizationId(entity.getAD_Org_ID())
				.withTableId(entity.get_Table_ID())
				.withRecordId(entity.get_ID());
		} else {
			logger.config("Entity is null");
		}
		return this;
	}

	public QueueManager withEntity(int tableId, int recordId) {
		if (getContext() == null || getTransactionName() == null) {
			throw new AdempiereException("@NotFound@");
		}
		MTable tableEntity = MTable.get(getContext(), tableId);

		return withEntity(tableEntity.getPO(recordId, getTransactionName()));
	}
	/**
	 * Clear Object
	 */
	public QueueManager clear() {
		queueTypeId = 0;
		description = null;
		tableId = 0;
		recordId = 0;
		organizationId = 0;
		context = null;
		transactionName = null;
		entity = null;
		return this;
	}
	
	/**
	 * Add value to queue
	 * @return
	 */
	public MADQueue addToQueue() {
		if(context == null) {
			throw new AdempiereException("Context is mandatory");
		}
		if(queueTypeId <= 0) {
			throw new AdempiereException("Queue Type is mandatory");
		}
		MADQueue queue = new MADQueue(context, 0, transactionName);
		queue.setAD_Org_ID(organizationId);
		queue.setAD_QueueType_ID(queueTypeId);
		if(!Util.isEmpty(getDescription())) {
			queue.setDescription(getDescription());
		}
		if(getTableId() > 0) {
			queue.setAD_Table_ID(getTableId());
		}
		if(getRecordId() > 0) {
			queue.setRecord_ID(getRecordId());
		}
		//	Save
		queue.saveEx();
		//	Call implementation for add to queue
		add(queue.getAD_Queue_ID());
		return queue;
	}
	
	/**
	 * Process a queue record
	 * @param queueId
	 * @param deleteAfterProcess
	 * @return
	 */
	public void process(MADQueue queueToProcess, boolean deleteAfterProcess) {
		if(queueToProcess == null) {
			throw new AdempiereException("Queue is mandatory");
		}
		if(context == null) {
			throw new AdempiereException("Context is mandatory");
		}
		if(transactionName == null) {
			throw new AdempiereException("Transaction is mandatory");
		}
		//	Call abstract implementation
		boolean processed = true;
		String error = null;
		try {
			process(queueToProcess.getAD_Queue_ID());
		} catch (Exception e) {
			logger.severe(e.getLocalizedMessage());
			processed = false;
			error = e.getLocalizedMessage();
		}
		//	
		if(deleteAfterProcess) {
			queueToProcess.delete(true);
		} else {
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			long mili = ts.getTime();
			int nano = ts.getNanos();
			double doublets = Double.parseDouble(Long.toString(mili) + "." + Integer.toString(nano));
			BigDecimal bdtimestamp = new BigDecimal(doublets);
			queueToProcess.setProcessedOn(bdtimestamp);
			queueToProcess.setProcessed(processed);
			queueToProcess.saveEx();
		}
		if(!processed) {
			throw new AdempiereException(error);
		}
	}

	/**
	 * This method is called after created a generic queue record
	 * @param context
	 * @param queueId
	 * @param transactionName
	 */
	public abstract void add(int queueId);
	
	/**
	 * Method used for flush a record of queue, please implement it in child class
	 * @param queueId
	 * @throws Exception
	 */
	public abstract void process(int queueId);
}
