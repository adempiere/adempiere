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

import java.util.HashMap;
import java.util.Map;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Util;
import org.spin.queue.notification.model.MADNotificationQueue;

/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 * Factory for create messages based on the type
 */
public class MessageFactory {
	/**	Default instances	*/
	private Map<String, ITelegramMessage> messagesHandler = new HashMap<String, ITelegramMessage>();
	/**	Singleton	*/
	private static MessageFactory instance = new MessageFactory();
	
	/**
	 * Get default instance
	 * @return
	 */
	public static MessageFactory getInstance() {
		return instance;
	}
	
	/**
	 * Get a simple handler for message type
	 * @param messageType
	 * @return
	 */
	public ITelegramMessage getHandler(String messageType) {
		if(Util.isEmpty(messageType)) {
			throw new AdempiereException("@MessageType@ @IsMandatory@");
		}
		ITelegramMessage messageHandler = messagesHandler.get(messageType);
		if(messageHandler == null) {
			messageHandler = getStaticHandler(messageType);
			messagesHandler.put(messageType, messageHandler);
		}
		return messageHandler;
	}
	
	/**
	 * Get Handler based on supported classes
	 * @param messageType
	 * @return
	 */
	private ITelegramMessage getStaticHandler(String messageType) {
		ITelegramMessage handler = null;
		switch (messageType) {
			case MADNotificationQueue.MESSAGETYPE_Confirmation:
				handler = new ConfirmationMessage();
				break;
			case MADNotificationQueue.MESSAGETYPE_Promotional:
				handler = new BaseMessage();
				break;
			case MADNotificationQueue.MESSAGETYPE_Survey:
				handler = new BaseMessage();
				break;
			default:
				handler = new BaseMessage();
				break;
		}
		return handler;
	}
}
