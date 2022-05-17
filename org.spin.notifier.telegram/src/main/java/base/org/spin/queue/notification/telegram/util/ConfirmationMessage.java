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

import java.util.ArrayList;
import java.util.List;

import org.adempiere.pipo.IDFinder;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.spin.queue.model.MADQueue;
import org.spin.queue.notification.model.MADNotificationQueue;
import org.spin.queue.notification.model.MADNotificationRecipient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 * Used for create a confirmation message from ADempiere info and the user should choice yes or not
 */
public class ConfirmationMessage extends BaseMessage {

	/**	Message Type	*/
	public static final String MESSAGE_TYPE = MADNotificationQueue.MESSAGETYPE_Confirmation;
	/**	Add Comments	*/
	public static final String YES = "y";
	/**	Assign To	*/
	public static final String NO = "n";
	/**	Constant for client Host	*/
	public static final String CLIENT_CODE = "CLIENT_CODE";
	
	/**
	 * Get available option
	 * @param clientCode
	 * @param action
	 * @param tableName
	 * @param uuid
	 * @return
	 */
	private String getInLineOption(String clientCode, int notificationId, String action) {
		return "c=" + clientCode + "|" + "t=" + MESSAGE_TYPE + "|" + "n=" + notificationId + "|" + "a=" + action;
	}
	
	@Override
	public SendMessage createAndGetMessage(MADNotificationQueue notification, MADNotificationRecipient recipient) {
		SendMessage message = createBaseMessage();
		StringBuilder messageInfo = new StringBuilder();
		if(!Util.isEmpty(notification.getDescription())) {
			messageInfo.append("*").append(notification.getDescription().trim()).append("*").append("\n");
		}
		//	Add Text
		if(!Util.isEmpty(notification.getText())) {
			messageInfo.append(notification.getText());
		}
		message.setText(messageInfo.toString());
		message.setChatId(recipient.getAccountName());
		//	For document
		MADQueue queue = new MADQueue(notification.getCtx(), notification.getAD_Queue_ID(), notification.get_TrxName());
		//	Actions
		if(queue.getAD_Table_ID() > 0 && queue.getRecord_ID() > 0) {
	    	String tableName = MTable.getTableName(queue.getCtx(), queue.getAD_Table_ID());
	    	String uuid = IDFinder.getUUIDFromId(
					tableName, queue.getRecord_ID(),
					notification.getAD_Client_ID(),
					notification.get_TrxName()
			);
	    	if(!Util.isEmpty(uuid)) {
	    		InlineKeyboardButton addColumn = new InlineKeyboardButton();
				InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		    	List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		    	List<InlineKeyboardButton> columnsInline = new ArrayList<>();
		    	//	Reject
		    	String host = MSysConfig.getValue(CLIENT_CODE, "none", queue.getAD_Client_ID());
		    	addColumn.setCallbackData(getInLineOption(host, notification.getAD_NotificationQueue_ID(), NO));
		    	addColumn.setText(Msg.getMsg(queue.getCtx(), "no"));
		    	columnsInline.add(addColumn);
		    	//	Approve
		    	addColumn = new InlineKeyboardButton();
		    	addColumn.setCallbackData(getInLineOption(host, notification.getAD_NotificationQueue_ID(), YES));
		    	addColumn.setText(Msg.getMsg(queue.getCtx(), "yes"));
		    	columnsInline.add(addColumn);
		    	rowsInline.add(columnsInline);
		    	// Add it to the message
		    	markupInline.setKeyboard(rowsInline);
		    	message.setReplyMarkup(markupInline);
	    	}
		}
		return message;
	}

}
