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
package org.spin.queue.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.X_AD_Queue;

public class MADQueue extends X_AD_Queue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3416589401841367255L;
	
	
	public MADQueue(Properties ctx, int queueId, String trxName) {
		super(ctx, queueId, trxName);
	}

	public MADQueue(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	public String toString() {
		return "MQueue [getAD_Queue_ID()=" + getAD_Queue_ID() + ", getAD_QueueType_ID()=" + getAD_QueueType_ID()
				+ ", getAD_Table_ID()=" + getAD_Table_ID() + ", getDescription()=" + getDescription()
				+ ", isProcessed()=" + isProcessed() + ", getProcessedOn()=" + getProcessedOn() + ", getRecord_ID()="
				+ getRecord_ID() + ", getUUID()=" + getUUID() + "]";
	}
}
