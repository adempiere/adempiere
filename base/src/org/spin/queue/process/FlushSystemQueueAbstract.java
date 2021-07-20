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

import org.compiere.process.SvrProcess;

/** Generated Process for (Flush System Queue)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public abstract class FlushSystemQueueAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "FlushSystemQueue";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Flush System Queue";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54539;
	/**	Parameter Name for Batchs to Process	*/
	public static final String BATCHSTOPROCESS = "BatchsToProcess";
	/**	Parameter Name for Records by Batch	*/
	public static final String RECORDSBYBATCH = "RecordsByBatch";
	/**	Parameter Name for Delete Records After Process	*/
	public static final String ISDELETEAFTERPROCESS = "IsDeleteAfterProcess";
	/**	Parameter Name for Queue Type	*/
	public static final String AD_QUEUETYPE_ID = "AD_QueueType_ID";
	/**	Parameter Value for Batchs to Process	*/
	private int batchsToProcess;
	/**	Parameter Value for Records by Batch	*/
	private int recordsByBatch;
	/**	Parameter Value for Delete Records After Process	*/
	private boolean isDeleteAfterProcess;
	/**	Parameter Value for Queue Type	*/
	private int queueTypeId;

	@Override
	protected void prepare() {
		batchsToProcess = getParameterAsInt(BATCHSTOPROCESS);
		recordsByBatch = getParameterAsInt(RECORDSBYBATCH);
		isDeleteAfterProcess = getParameterAsBoolean(ISDELETEAFTERPROCESS);
		queueTypeId = getParameterAsInt(AD_QUEUETYPE_ID);
	}

	/**	 Getter Parameter Value for Batchs to Process	*/
	protected int getBatchsToProcess() {
		return batchsToProcess;
	}

	/**	 Setter Parameter Value for Batchs to Process	*/
	protected void setBatchsToProcess(int batchsToProcess) {
		this.batchsToProcess = batchsToProcess;
	}

	/**	 Getter Parameter Value for Records by Batch	*/
	protected int getRecordsByBatch() {
		return recordsByBatch;
	}

	/**	 Setter Parameter Value for Records by Batch	*/
	protected void setRecordsByBatch(int recordsByBatch) {
		this.recordsByBatch = recordsByBatch;
	}

	/**	 Getter Parameter Value for Delete Records After Process	*/
	protected boolean isDeleteAfterProcess() {
		return isDeleteAfterProcess;
	}

	/**	 Setter Parameter Value for Delete Records After Process	*/
	protected void setIsDeleteAfterProcess(boolean isDeleteAfterProcess) {
		this.isDeleteAfterProcess = isDeleteAfterProcess;
	}

	/**	 Getter Parameter Value for Queue Type	*/
	protected int getQueueTypeId() {
		return queueTypeId;
	}

	/**	 Setter Parameter Value for Queue Type	*/
	protected void setQueueTypeId(int queueTypeId) {
		this.queueTypeId = queueTypeId;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME_FOR_PROCESS;
	}
}