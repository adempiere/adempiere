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
 * Copyright (C) 2003-Present E.R.P. Consultores y Asociados, C.A.            *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.spin.queue.notification;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttachment;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CCache;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.eevolution.service.dsl.ProcessBuilder;
import org.spin.model.I_AD_AppRegistration;
import org.spin.model.MADAppRegistration;
import org.spin.model.MADAppSupport;
import org.spin.model.MADUserSocialMedia;
import org.spin.queue.model.MADQueue;
import org.spin.queue.model.MADQueueType;
import org.spin.queue.notification.model.MADNotificationQueue;
import org.spin.queue.notification.model.MADNotificationRecipient;
import org.spin.queue.notification.support.INotification;
import org.spin.queue.process.FlushSystemQueue;
import org.spin.queue.util.QueueLoader;
import org.spin.queue.util.QueueManager;
import org.spin.util.support.AppSupportHandler;
import org.spin.util.support.IAppSupport;

/**
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Notification queue manager  
 */
public class DefaultNotifier extends QueueManager {

	/**	User reference	*/
	private int userId;
	/**	Text	*/
	private String text;
	/**	Application Type	*/
	private String applicationType;
	/**	Application Support	*/
	private int applicationSupportId;
	/**	Application Registration	*/
	private int applicationRegistrationId;
	/**	Recipients	*/
	private List<KeyNamePair> recipients;
	/**	Attachments	*/
	private List<AttachmentStub> attachments;
	/**	Cache with notifiers	*/
	private static CCache<String, MADAppRegistration> notifierCache = new CCache<String, MADAppRegistration>(MADAppRegistration.Table_Name, 30, 0);
	
	/** Default Notifier = NTF */
	public static final String QUEUETYPE_DefaultNotifier = MADQueueType.QUEUETYPE_SystemNotification;
	/**	Email Service	*/
	public static final String DefaultNotificationType_Notes = MADAppSupport.APPLICATIONTYPE_Notes;
	/**	Internal Notes	*/
	public static final String DefaultNotificationType_EMail = MADAppSupport.APPLICATIONTYPE_EMail;
	/**	Twitter	*/
	public static final String DefaultNotificationType_Twitter = MADAppSupport.APPLICATIONTYPE_Twitter;
	/**	Facebook	*/
	public static final String DefaultNotificationType_Facebook = MADAppSupport.APPLICATIONTYPE_Facebook;
	/**	YouTube	*/
	public static final String DefaultNotificationType_YouTube = MADAppSupport.APPLICATIONTYPE_YouTube;
	/**	Instagram	*/
	public static final String DefaultNotificationType_Instagram = MADAppSupport.APPLICATIONTYPE_Instagram;
	/**	Skype	*/
	public static final String DefaultNotificationType_Skype = MADAppSupport.APPLICATIONTYPE_Skype;
	/**	LinkedIn	*/
	public static final String DefaultNotificationType_LinkedIn = MADAppSupport.APPLICATIONTYPE_LinkedIn;
	/**	SnapChat	*/
	public static final String DefaultNotificationType_SnapChat = MADAppSupport.APPLICATIONTYPE_SnapChat;
	/**	Telegram	*/
	public static final String DefaultNotificationType_Telegram = MADAppSupport.APPLICATIONTYPE_Telegram;
	/**	WhatsApp	*/
	public static final String DefaultNotificationType_WhatsApp = MADAppSupport.APPLICATIONTYPE_WhatsApp;
	/**	User Defined	*/
	public static final String DefaultNotificationType_UserDefined = "UDP";
	
	@Override
	public QueueManager clear() {
		super.clear();
		//	Local clear
		clearMessage();
		return this;
	}
	
	/**
	 * Clear message
	 * @return
	 */
	public DefaultNotifier clearMessage() {
		userId = 0;
		text = null;
		applicationType = null;
		applicationSupportId = 0;
		applicationRegistrationId = 0;
		recipients = new ArrayList<KeyNamePair>();
		attachments = new ArrayList<AttachmentStub>();
		return this;
	}
	
	/**
	 * @return the userId
	 */
	public final int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public final DefaultNotifier withUserId(int userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * @return the text
	 */
	public final String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public final DefaultNotifier withText(String text) {
		this.text = text;
		return this;
	}

	/**
	 * @return the applicationType
	 */
	public final String getApplicationType() {
		return applicationType;
	}

	/**
	 * @param applicationType the applicationType to set
	 */
	public final DefaultNotifier withApplicationType(String applicationType) {
		this.applicationType = applicationType;
		return this;
	}

	/**
	 * @return the applicationSupportId
	 */
	public final int getApplicationSupportId() {
		return applicationSupportId;
	}

	/**
	 * @param applicationSupportId the applicationSupportId to set
	 */
	public final DefaultNotifier withApplicationSupportId(int applicationSupportId) {
		this.applicationSupportId = applicationSupportId;
		return this;
	}
	
	/**
	 * @param applicationSupportCode the applicationSupportCode to set
	 */
	public final DefaultNotifier withApplicationSupportCode(String applicationSupportCode) {
		if(Util.isEmpty(applicationSupportCode)) {
			this.applicationRegistrationId = 0;
			return this;
		}
		//	Default
		
		this.applicationSupportId = getApplicationSupportFromValue(applicationSupportCode);
		return this;
	}
	
	/**
	 * Get Application Supported id from Value
	 * @param applicationTypeCode
	 * @return
	 */
	private int getApplicationSupportFromValue(String applicationTypeCode) {
		List<MADAppSupport> supportedApplications = MADAppSupport.getAll(getContext(), false, getTransactionName());
		if(supportedApplications != null
				&& supportedApplications.size() > 0) {
			Optional<MADAppSupport> maybeApplication = supportedApplications.stream().filter(application -> application.getValue().equals(applicationTypeCode)).findFirst();
			if(maybeApplication.isPresent()) {
				return maybeApplication.get().getAD_AppSupport_ID();
			}
		}
		return 0;
	}

	/**
	 * @return the applicationRegistrationId
	 */
	public final int getApplicationRegistrationId() {
		return applicationRegistrationId;
	}

	/**
	 * @param applicationRegistrationId the applicationRegistrationId to set
	 */
	public final DefaultNotifier withApplicationRegistrationId(int applicationRegistrationId) {
		this.applicationRegistrationId = applicationRegistrationId;
		return this;
	}

	/**
	 * @return the recipients
	 */
	public final List<KeyNamePair> getRecipients() {
		return recipients;
	}

	/**
	 * @param userId the userId to set
	 */
	public final DefaultNotifier addRecipient(int userId) {
		if(userId > 0) {
			this.recipients.add(new KeyNamePair(userId, null));
		}
		return this;
	}
	
	/**
	 * @param recipient the recipient to set
	 */
	public final DefaultNotifier addRecipient(String recipient) {
		if(!Util.isEmpty(recipient)) {
			this.recipients.add(new KeyNamePair(0, recipient));
		}
		return this;
	}
	
	/**
	 * @param recipient the recipient to set
	 */
	public final DefaultNotifier addRecipient(int userId, String recipient) {
		if(!Util.isEmpty(recipient)) {
			this.recipients.add(new KeyNamePair(userId, recipient));
		}
		return this;
	}

	/**
	 * @return the attachments
	 */
	public final List<AttachmentStub> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachment the attachment to set
	 * @param comment the comments embedded of attachment
	 */
	public final DefaultNotifier addAttachment(File attachment, String comment) {
		Optional.ofNullable(attachment).ifPresent(attachmentToSet -> {
			this.attachments.add(new AttachmentStub(attachment, comment));
		});
		return this;
	}
	
	/**
	 * Add attachment without comments
	 * @param attachment
	 * @return
	 */
	public final DefaultNotifier addAttachment(File attachment) {
		return addAttachment(attachment, null);
	}

	@Override
	public void add(int queueId) {
		if(Util.isEmpty(getApplicationType())) {
			throw new AdempiereException("@ApplicationType@ @IsMandatory@");
		}
		if(getRecipients().size() == 0) {
			throw new AdempiereException("@AD_NotificationRecipient_ID@ @IsMandatory@");
		}
		if(Util.isEmpty(text) && Util.isEmpty(getDescription())) {
			throw new AdempiereException("@Text@ @IsMandatory@");
		}
		if(getApplicationType().equals(DefaultNotificationType_UserDefined)
				&& !getRecipients().stream().filter(recipient -> recipient.getKey() > 0).findFirst().isPresent()) {
			throw new AdempiereException("@AD_User_ID@ @IsMandatory@");
		}
		//	for default
		if(!getApplicationType().equals(DefaultNotificationType_UserDefined)) {
			addToQueueBasedOnApplicationType(queueId);
		} else {
			getRecipients().stream().filter(recipient -> recipient.getKey() > 0)
				.forEach(recipient -> {
				MUser userRecipient = MUser.get(getContext(), recipient.getKey());
				//	For EMail
				if(userRecipient.isNotificationEMail()) {
					int applicationSupportId = getApplicationSupportFromValue(DefaultNotificationType_EMail);
					addToQueueBasedOnUserDefinition(DefaultNotificationType_EMail, applicationSupportId, queueId, new KeyNamePair(userRecipient.getAD_User_ID(), userRecipient.getEMail()));
				}
				//	For Note
				if(userRecipient.isNotificationNote()) {
					int applicationSupportId = getApplicationSupportFromValue(DefaultNotificationType_Notes);
					addToQueueBasedOnUserDefinition(DefaultNotificationType_Notes, applicationSupportId, queueId, recipient);
				}
				//	For Social Media
				if(userRecipient.isNotificationSocialMedia()) {
					MADUserSocialMedia.getSocialMedias(getContext(), recipient.getKey(), getTransactionName())
						.stream()
						.filter(socialMedia -> socialMedia.isReceiveNotifications())
						.forEach(socialMedia -> {
							int applicationSupportId = socialMedia.getAD_AppSupport_ID();
							if(applicationSupportId <= 0) {
								applicationSupportId = getApplicationSupportFromValue(socialMedia.getApplicationType());
							}
							addToQueueBasedOnUserDefinition(socialMedia.getApplicationType(), applicationSupportId, queueId, new KeyNamePair(userRecipient.getAD_User_ID(), socialMedia.getAccountName()));
					});
				}
			});
		}
	}

	/**
	 * Add to queue 
	 * @param applicationType
	 * @param applicationSupportId
	 * @param queueId
	 * @param recipient
	 */
	private void addToQueueBasedOnUserDefinition(String applicationType, int applicationSupportId, int queueId, KeyNamePair recipient) {
		MADQueue queue = new MADQueue(getContext(), queueId, getTransactionName());
		MADNotificationQueue notification = new MADNotificationQueue(queue);
		notification.setApplicationType(applicationType);
		if(getUserId() > 0) {
			notification.setAD_User_ID(getUserId());
		}
		if(getApplicationSupportId() > 0) {
			notification.setAD_AppSupport_ID(applicationSupportId);
		}
		if(!Util.isEmpty(getText())) {
			notification.setText(getText());
		}
		if(!Util.isEmpty(getDescription())) {
			notification.setDescription(getDescription());
		}
		notification.saveEx();
		//	Add recipient
		MADNotificationRecipient notificationRecipient = new MADNotificationRecipient(notification);
		notificationRecipient.setAccountName(Optional.ofNullable(recipient.getName()).orElse("").trim());
		if(recipient.getKey() > 0) {
			notificationRecipient.setAD_User_ID(recipient.getKey());
		}
		notificationRecipient.saveEx();
		//	Add Attachments
		if(getAttachments().size() > 0) {
			getAttachments().forEach(attachment -> {
				MAttachment attachmentReference = notification.createAttachment();
				attachmentReference.addEntry(attachment.getAttachment());
				Optional.ofNullable(attachment.getComment()).ifPresent(comment -> attachmentReference.addTextMsg(comment));
				attachmentReference.saveEx(getTransactionName());
			});
		}
		logger.fine("Queue Added: " + notification);
	}
	
	/**
	 * Add to queue using the application supported defined
	 * @param queueId
	 */
	private void addToQueueBasedOnApplicationType(int queueId) {
		MADQueue queue = new MADQueue(getContext(), queueId, getTransactionName());
		MADNotificationQueue notification = new MADNotificationQueue(queue);
		notification.setApplicationType(getApplicationType());
		if(getUserId() > 0) {
			notification.setAD_User_ID(getUserId());
		}
		if(getApplicationSupportId() > 0) {
			notification.setAD_AppSupport_ID(getApplicationSupportId());
		}
		if(getApplicationRegistrationId() > 0) {
			notification.setAD_AppRegistration_ID(getApplicationRegistrationId());
		}
		if(!Util.isEmpty(getText())) {
			notification.setText(getText());
		}
		if(!Util.isEmpty(getDescription())) {
			notification.setDescription(getDescription());
		}
		notification.saveEx();
		//	Add recipients
		getRecipients().forEach(recipient -> {
			MADNotificationRecipient notificationRecipient = new MADNotificationRecipient(notification);
			notificationRecipient.setAccountName(Optional.ofNullable(recipient.getName()).orElse("").trim());
			if(recipient.getKey() > 0) {
				notificationRecipient.setAD_User_ID(recipient.getKey());
			}
			notificationRecipient.saveEx();
		});
		//	Add Attachments
		if(getAttachments().size() > 0) {
			getAttachments().forEach(attachment -> {
				MAttachment attachmentReference = notification.createAttachment();
				attachmentReference.addEntry(attachment.getAttachment());
				Optional.ofNullable(attachment.getComment()).ifPresent(comment -> attachmentReference.addTextMsg(comment));
				attachmentReference.saveEx(getTransactionName());
			});
		}
		logger.fine("Queue Added: " + notification);
	}
	
	@Override
	public void process(int queueId) {
		MADNotificationQueue.getNotificationsFromQueue(getContext(), queueId, getTransactionName()).forEach(notification -> {
			try {
				getNotifier(notification).sendNotification(notification);
				notification.setProcessed(true);
				notification.saveEx();
			} catch (Exception e) {
				notification.setProcessed(false);
				notification.saveEx();
				throw new AdempiereException(e);
			}
		});
	}
	
	/**
	 * Get notifier from queue definition
	 * @param queue
	 * @return
	 */
	protected INotification getNotifier(MADNotificationQueue queue) {
		try {
			MADAppRegistration registeredApplication = getRegistration(queue);
			if(registeredApplication == null) {
				throw new AdempiereException("@AD_AppRegistration_ID@ @NotFound@");
			}
			//	Load support
			IAppSupport supportedApplication = AppSupportHandler.getInstance().getAppSupport(registeredApplication);
			//	Exists a Application available for it?
			if(supportedApplication != null
					&& INotification.class.isAssignableFrom(supportedApplication.getClass())) {
				//	Instance of fiscal printer
				return (INotification) supportedApplication;
			}
		} catch (Exception e) {
			logger.severe(e.getLocalizedMessage());
			throw new AdempiereException(e);
		}
		//	default
		return null;
	}
	
	/**
	 * Get registration from cache or search it
	 * @param queue
	 * @return
	 */
	private MADAppRegistration getRegistration(MADNotificationQueue queue) {
		String key = queue.getApplicationType() + "|" + queue.getAD_AppSupport_ID() + "|" + queue.getAD_AppRegistration_ID();
		MADAppRegistration registration = notifierCache.get(key);
		//	Query for it
		if(registration == null) {
			if(queue.getAD_AppRegistration_ID() > 0) {
				registration = MADAppRegistration.getById(getContext(), queue.getAD_AppRegistration_ID(), getTransactionName());
			} else if(queue.getAD_AppSupport_ID() > 0) {
				registration = new Query(getContext(), I_AD_AppRegistration.Table_Name, I_AD_AppRegistration.COLUMNNAME_AD_AppSupport_ID + " = ?", getTransactionName())
						.setParameters(queue.getAD_AppSupport_ID())
						.setOnlyActiveRecords(true)
						.setOrderBy(I_AD_AppRegistration.COLUMNNAME_AD_Client_ID + " DESC")
						.first();
			} else if(!Util.isEmpty(queue.getApplicationType())) {
				registration = MADAppRegistration.getByApplicationType(getContext(), queue.getApplicationType(), getTransactionName());
			}
			//	Set to cache
			Optional.ofNullable(registration).ifPresent(registrationToPush -> notifierCache.put(key, registrationToPush));
		}
		//	return
		return registration;
	}
	
	/**
	 * Write a file from download
	 * @param url
	 * @param fileName
	 */
	private static void writeTempFile(String url, String fileName) {
		try {
			BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
			//	
			fileOutputStream.close();
		} catch (IOException e) {
		    // handle exception
		}
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
		    emailAddr.validate();
		} catch (AddressException ex) {
			ex.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public static void main(String[] args) {
		if(args == null
				|| args.length == 0) {
			throw new AdempiereException("Email is Mandatory");
		}
		//			
		if(!isValidEmailAddress(args[0])) {
			throw new AdempiereException("Please provide a valid email");
		}
		org.compiere.Adempiere.startup(true);
		Env.setContext(Env.getCtx(), "#AD_Client_ID", 11);
		writeTempFile("https://user-images.githubusercontent.com/2333092/125704926-691a2f7f-4533-4654-a037-8fcc30494b5e.png", "/tmp/ADempiereQueue.png");
		writeTempFile("https://github.com/adempiere/adempiere/files/6819389/ADempiereQueue.pdf", "/tmp/ADempiereQueue.pdf");
		File file1 = new File("/tmp/ADempiereQueue.png");
		File file2 = new File("/tmp/ADempiereQueue.pdf");
		Trx.run(transactionName -> {
			DefaultNotifier notifier = (DefaultNotifier) QueueLoader.getInstance().getQueueManager(QUEUETYPE_DefaultNotifier)
					.withContext(Env.getCtx())
					.withTransactionName(transactionName);
			//	EMail
			notifier
				.clearMessage()
				.withApplicationType(DefaultNotificationType_UserDefined)
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
				.addRecipient(104, args[0])
				.addAttachment(file1, "Just a Test")
				.addAttachment(file2)
				.withDescription("Hello by EMAil")
				.addToQueue();
			//	Notes
			notifier
				.clearMessage()
				.withApplicationType(DefaultNotificationType_Notes)
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
				.addRecipient("YamelSenih")
				.addAttachment(file1, "Just a test")
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
			logger.fine(result.getSummary());
		});
	}
}
