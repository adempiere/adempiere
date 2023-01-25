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
package org.spin.queue.notification.telegram.util;

import org.spin.queue.notification.model.MADNotificationQueue;
import org.spin.queue.notification.model.MADNotificationRecipient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 * Contract for notification using
 */
public interface ITelegramMessage {
	
	/**
	 * Create and get message for recipient
	 * @param notification Notification
	 * @param recipient Recipient
	 * @return
	 */
	public SendMessage createAndGetMessage(MADNotificationQueue notification, MADNotificationRecipient recipient);
}
