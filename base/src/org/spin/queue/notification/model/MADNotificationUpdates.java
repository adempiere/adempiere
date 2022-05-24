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
import java.util.Properties;

import org.spin.queue.notification.util.UpdateHandlerUtil;

/**
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Updates of notification
 */
public class MADNotificationUpdates extends X_AD_NotificationUpdates {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3416589401841367255L;
	
	
	public MADNotificationUpdates(Properties ctx, int notificationUpdatesId, String trxName) {
		super(ctx, notificationUpdatesId, trxName);
	}

	public MADNotificationUpdates(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * COnstructor from parent
	 * @param queue
	 */
	public MADNotificationUpdates(MADNotificationQueue queue) {
		super(queue.getCtx(), 0, queue.get_TrxName());
		setAD_NotificationQueue_ID(queue.getAD_NotificationQueue_ID());
		setAD_Org_ID(queue.getAD_Org_ID());
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(newRecord && success) {
			UpdateHandlerUtil.getInstance().runHandler(this);
		}
		return super.afterSave(newRecord, success);
	}

	@Override
	public String toString() {
		return "MADNotificationUpdates [getAccountName()=" + getAccountName() + ", getAD_NotificationQueue_ID()="
				+ getAD_NotificationQueue_ID() + ", getAD_NotificationRecipient_ID()="
				+ getAD_NotificationRecipient_ID() + ", getNotificationResponseCode()=" + getNotificationResponseCode()
				+ "]";
	}
}
