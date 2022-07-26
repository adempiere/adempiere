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
package org.spin.queue.notification.discord.support;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.MClient;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.eevolution.service.dsl.ProcessBuilder;
import org.spin.model.MADAppRegistration;
import org.spin.queue.notification.DefaultNotifier;
import org.spin.queue.notification.discord.util.BaseMessage;
import org.spin.queue.notification.discord.util.MessageFactory;
import org.spin.queue.notification.model.MADNotificationQueue;
import org.spin.queue.notification.model.MADNotificationRecipient;
import org.spin.queue.notification.support.INotification;
import org.spin.queue.process.FlushSystemQueue;
import org.spin.queue.util.QueueLoader;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

/**
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Implementation of Discord based on bot Api  
 */
public class Discord implements INotification {

	/**	Registration Id	*/
	private int registrationId = 0;
	/**	Bot Token	*/
	private String botToken = null;
	/**	Chat ID	*/
	private String channelName = null;
	/**	Bot Token		*/
	private final String BOT_TOKEN = "bot_token";
	/**	Chat ID for Testing	*/
	private final String CHANNEL_NAME = "channel_name";
	/** Static Logger					*/
	private CLogger log = CLogger.getCLogger (Discord.class);
	
	@Override
	public int getAppRegistrationId() {
		return registrationId;
	}

	@Override
	public void setAppRegistrationId(int registrationId) {
		this.registrationId = registrationId;
		MADAppRegistration registration = MADAppRegistration.getById(Env.getCtx(), getAppRegistrationId(), null);
		assert registration != null;
		botToken = registration.getParameterValue(BOT_TOKEN);
		channelName = registration.getParameterValue(CHANNEL_NAME);
		log.fine("set Registration: " + registrationId);
	}

	@Override
	public String testConnection() {
		log.fine("Test connection");
		try {
			//	TODO: Add from Queue
			if(Util.isEmpty(channelName)) {
				throw new AdempiereException("chat id not found");
			}
			MADAppRegistration registration = MADAppRegistration.getById(Env.getCtx(), getAppRegistrationId(), null);
			assert registration != null;
			StringBuilder testMessage = new StringBuilder("**").append(registration.getName()).append("**");
			testMessage.append(Env.NL).append("**@Value@**: ").append(MClient.get(Env.getCtx()).getValue())
			.append(Env.NL).append("**@Name@**: ").append(MClient.get(Env.getCtx()).getName())
			.append(Env.NL).append("**@Version@**: ").append(Adempiere.getVersion());
			JDA connector = JDABuilder.createDefault(botToken).build();
			connector.awaitReady();
			BaseMessage.createBaseMessage(
					connector,
					Msg.parseTranslation(Env.getCtx(), testMessage.toString()),
					channelName
			).queue();
			connector.shutdown();
		} catch (Exception e) {
			throw new AdempiereException(e);
		}
		return "Ok";
	}

	@Override
	public void sendNotification(MADNotificationQueue notification) {
		StringBuffer errorMessage = new StringBuffer();
		notification.getRecipients().forEach(recipient -> {
			try {
				if(Util.isEmpty(notification.getText())) {
					throw new AdempiereException("@Text@ @IsMandatory@");
				}
				JDA connector = JDABuilder.createDefault(botToken).build();
				connector.awaitReady();
			    MessageFactory.getInstance()
						.getHandler(recipient.getMessageType())
						.createAndGetMessage(connector, notification, recipient)
						.queue();
			    connector.shutdown();
				recipient.setProcessed(true);
				recipient.saveEx();
				log.fine("Telegram sent");	
			} catch (Exception exception) {
				log.severe(exception.getLocalizedMessage());
				recipient.setErrorMsg(exception.getLocalizedMessage());
				recipient.saveEx();
				if(errorMessage.length() > 0) {
					errorMessage.append(Env.NL);
				}
	        	errorMessage.append("Error: Sending to: ").append(recipient.getAccountName())
						.append(": ").append(exception.getLocalizedMessage());
			}
		});
		if(errorMessage.length() > 0) {
			throw new AdempiereException(errorMessage.toString());
		}
	}
	
	public static void main(String[] args) {
		org.compiere.Adempiere.startup(true);
		Env.setContext(Env.getCtx(), "#AD_Client_ID", 1000000);
		Trx.run(transactionName -> {
			DefaultNotifier notifier = (DefaultNotifier) QueueLoader.getInstance()
					.getQueueManager(DefaultNotifier.QUEUETYPE_DefaultNotifier)
					.withContext(Env.getCtx())
					.withTransactionName(transactionName);
			//	Telegram
			notifier
				.clearMessage()
				.withApplicationType(DefaultNotifier.DefaultNotificationType_Discord)
				.withUserId(100)
				.withText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec fringilla faucibus enim quis aliquam. Integer tincidunt et dui vitae egestas. Suspendisse felis est, commodo at ex eu, pellentesque varius leo. Pellentesque tempor quis felis et rutrum. Curabitur imperdiet euismod leo, in pretium ante convallis eu. Nam non odio vulputate, luctus est sed, semper dolor. Vivamus auctor, odio vitae sodales vestibulum, lacus metus auctor ex, nec accumsan est nibh a erat. Ut suscipit velit a imperdiet vestibulum.\n" + 
						"\n" + 
						"Maecenas vel felis ac nulla eleifend volutpat in ac magna. Duis dui est, facilisis at posuere eu, faucibus at nisl. Duis efficitur porttitor lorem. Curabitur tincidunt lorem massa, in sollicitudin ante fringilla sit amet. Nunc nec neque pharetra, tempor mi ac, pharetra risus. Donec tristique laoreet dolor tincidunt venenatis. Sed et leo eget diam molestie venenatis in a eros. Cras nibh arcu, laoreet et suscipit sed, ornare eu mauris. Nulla egestas lacus efficitur, faucibus odio sed, elementum velit. Proin fringilla bibendum lacinia. Vivamus maximus vulputate hendrerit. Fusce mauris ex, finibus eget augue at, volutpat viverra ipsum. Nullam justo nibh, maximus vel nisl tempus, sollicitudin tempor magna.\n" + 
						"\n" + 
						"Sed odio dui, tristique sit amet pretium vitae, vehicula vitae enim. In et interdum quam. Etiam id metus a quam interdum tempor. Sed luctus, tellus et mollis porttitor, dolor lectus dignissim eros, in commodo mauris ligula quis eros. Suspendisse scelerisque ipsum at turpis suscipit interdum at ac velit. Aenean suscipit ipsum ut velit cursus sodales. Nulla viverra mi quis commodo aliquam. Ut auctor condimentum urna sed mollis. Fusce neque neque, interdum eget purus ut, efficitur vestibulum lectus. Praesent sagittis nunc ut tincidunt posuere. In faucibus, elit vel vehicula porta, felis ligula finibus arcu, in suscipit odio risus pellentesque lorem. Etiam viverra lacus a eros placerat, eget tempus mi eleifend. Donec ac arcu vel lacus tristique imperdiet. Quisque venenatis arcu egestas ex viverra accumsan. Aenean vitae massa sed elit hendrerit faucibus.\n" + 
						"\n" + 
						"In hac habitasse platea dictumst. Sed tellus ante, pretium ut viverra feugiat, consectetur et velit. Nulla quis nibh est. Quisque pharetra sem eget ultrices efficitur. Phasellus maximus posuere metus, facilisis dictum justo bibendum ut. Sed aliquet scelerisque risus vel luctus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Suspendisse eget diam vestibulum risus venenatis pellentesque. Suspendisse tincidunt orci et nisl malesuada, ut porta neque dictum. Quisque at mollis arcu. Nulla sodales diam id lacus efficitur aliquet. In dui leo, pharetra vulputate purus quis, tincidunt ultricies enim. Nulla facilisi. Donec sagittis pharetra ante. Aenean et condimentum ligula, sed volutpat lorem.\n" + 
						"\n" + 
						"Nulla laoreet faucibus odio, in rutrum diam semper ut. Praesent at purus id massa hendrerit ultricies. Nam sodales sapien id diam finibus viverra. Duis efficitur hendrerit vulputate. Cras urna enim, vestibulum sit amet ex nec, lobortis viverra massa. Morbi accumsan vel magna in dictum. Cras sed mollis libero. Etiam egestas, orci sed dapibus tempus, ligula ex vulputate lacus, sit amet volutpat urna nulla ac massa. Phasellus ac sollicitudin purus. Ut sit amet ligula eget justo imperdiet rhoncus non nec lectus. Mauris euismod ornare felis et dictum. Maecenas vestibulum dictum dui, ut elementum tellus convallis ac. In congue nunc vel felis elementum, at ultrices velit pharetra.\n")
				.addRecipient("880454443445923891", MADNotificationRecipient.MESSAGETYPE_Standard)
				.withEntity(MClient.Table_ID, 1000000)
				.withDescription("Hello by Notes")
				.addToQueue();
		});
		Trx.run(transactionName -> {
			//	add to queue
			ProcessInfo result = ProcessBuilder.create(Env.getCtx())
				.process(FlushSystemQueue.getProcessId())
				.withParameter(FlushSystemQueue.BATCHSTOPROCESS, 1)
				.withParameter(FlushSystemQueue.RECORDSBYBATCH, 100)
				.withParameter(FlushSystemQueue.ISDELETEAFTERPROCESS, false)
				.execute();
			//	
			System.err.println(result.getSummary());
		});
	}
}
