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
package org.spin.queue.notification.telegram.support;

import org.adempiere.exceptions.AdempiereException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Implementation of Telegram based on bot Api  
 */
public class SenderBot extends TelegramLongPollingBot {

	public SenderBot(String botName, String botToken) {
		this.botName = botName;
		this.botToken = botToken;
	}
	
	private String botName;
	private String botToken;
	
	@Override
	public void onUpdateReceived(Update update) {
		
	}

	@Override
	public String getBotUsername() {
		return botName;
	}

	@Override
	public String getBotToken() {
		return botToken;
	}
	
	
	/**
	 * Send message based on SendMessage class
	 * @param message
	 */
	public void sendMessage(SendMessage message) {
		try {
			execute(message);
		} catch (Exception e) {
			throw new AdempiereException(e);
		}
	}
}
