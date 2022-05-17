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
package org.spin.queue.notification.discord.util;

import java.util.Arrays;
import java.util.Optional;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttachment;
import org.compiere.util.Util;
import org.spin.queue.notification.model.MADNotificationQueue;
import org.spin.queue.notification.model.MADNotificationRecipient;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 * Used for create base message by telegram as message
 */
public class BaseMessage implements IDiscordMessage {
	
	/**
	 * Create Base Message
	 * @param connector
	 * @param message
	 * @param channelName
	 * @return
	 */
	public static MessageAction createBaseMessage(JDA connector, String message, String channelName) {
		if(connector == null) {
			throw new AdempiereException("@Connection@ @IsMandatory@");
		}
		if(Util.isEmpty(message)) {
			throw new AdempiereException("@Message@ @IsMandatory@");
		}
		if(Util.isEmpty(channelName)) {
			throw new AdempiereException("@Channel@ @IsMandatory@");
		}
		TextChannel channel = getChannel(connector, channelName);
		return channel.sendMessage(message);
	}
	
	/***
	 * Get channel by name or id
	 * @param connector
	 * @param channelName
	 * @return
	 */
	private static TextChannel getChannel(JDA connector, String channelName) {
		if(channelName.matches("[+-]?\\d*(\\.\\d+)?")) {
			return connector.getTextChannelById(channelName);
		}
		Optional<TextChannel> channel = connector.getTextChannelsByName(channelName, true)
				.stream()
				.findFirst();
		if(channel.isPresent()) {
			return channel.get();
		}
		throw new AdempiereException("@Channel@ @IsMandatory@");
	}
	
	
	@Override
	public MessageAction createAndGetMessage(
			JDA connector,
			MADNotificationQueue notification,
			MADNotificationRecipient recipient) {

		StringBuilder messageInfo = new StringBuilder();
		if(!Util.isEmpty(notification.getDescription())) {
			messageInfo.append("**").append(notification.getDescription().trim()).append("**").append("\n");
		}
		//	Add Text
		if(!Util.isEmpty(notification.getText())) {
			messageInfo.append(notification.getText());
		}
		MessageAction message = createBaseMessage(connector, messageInfo.toString(), recipient.getAccountName());
		//	Attachment
		//	Attachment
		MAttachment attachment = notification.getAttachment();
		if(attachment != null
				&& attachment.getAD_Attachment_ID() > 0) {
			Arrays.asList(attachment.getEntries()).forEach(entry -> {
				message.addFile(entry.getFile());
			});
		}
		return message;
	}

}
