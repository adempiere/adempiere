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
package org.spin.queue.notification.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_AD_NotificationRecipient;
import org.adempiere.core.domains.models.I_AD_NotificationUpdates;
import org.adempiere.core.domains.models.X_AD_NotificationQueue;
import org.compiere.model.Query;
import org.spin.queue.model.MADQueue;

/**
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Model class
 */
public class MADNotificationQueue extends X_AD_NotificationQueue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3416589401841367255L;
	
	
	public MADNotificationQueue(Properties ctx, int notificationQueueId, String trxName) {
		super(ctx, notificationQueueId, trxName);
	}

	public MADNotificationQueue(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Constructor based on queue
	 * @param queue
	 */
	public MADNotificationQueue(MADQueue queue) {
		super(queue.getCtx(), 0, queue.get_TrxName());
		setAD_Queue_ID(queue.getAD_Queue_ID());
		setAD_Org_ID(queue.getAD_Org_ID());
	}
	
	/**
	 * Get recipients of notification
	 * @return
	 */
	public List<MADNotificationRecipient> getRecipients() {
		return new Query(getCtx(), I_AD_NotificationRecipient.Table_Name,
				I_AD_NotificationRecipient.COLUMNNAME_AD_NotificationQueue_ID + " = ?", get_TrxName())
				.setParameters(getAD_NotificationQueue_ID())
				.setOnlyActiveRecords(true)
				.list();
	}
	
	/**
	 * Get updates of notification
	 * @return
	 */
	public List<MADNotificationUpdates> getUpdates() {
		return new Query(getCtx(), I_AD_NotificationUpdates.Table_Name,
				I_AD_NotificationUpdates.COLUMNNAME_AD_NotificationQueue_ID + " = ?", get_TrxName())
				.setParameters(getAD_NotificationQueue_ID())
				.setOnlyActiveRecords(true)
				.list();
	}
	
	/**
	 * Get Notification from queue
	 * @param context
	 * @param queueId
	 * @param transactionName
	 * @return
	 */
	public static List<MADNotificationQueue> getNotificationsFromQueue(
			Properties context,
			int queueId,
			String transactionName) {
		return new Query(context, Table_Name, COLUMNNAME_AD_Queue_ID + " = ?", transactionName)
				.setParameters(queueId)
				.list();
	}

	@Override
	public String toString() {
		return "MADNotificationQueue [getAD_AppRegistration_ID()=" + getAD_AppRegistration_ID()
				+ ", getAD_AppSupport_ID()=" + getAD_AppSupport_ID() + ", getAD_NotificationQueue_ID()="
				+ getAD_NotificationQueue_ID() + ", getAD_Queue_ID()=" + getAD_Queue_ID() + ", getApplicationType()="
				+ getApplicationType() + ", getDescription()=" + getDescription() + "]";
	}
}
