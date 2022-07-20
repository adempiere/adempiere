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
package org.spin.queue.notification.support;

import java.util.Arrays;

import org.adempiere.exceptions.AdempiereException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.MAttachment;
import org.compiere.model.MClient;
import org.compiere.model.MEMailConfig;
import org.compiere.model.MUser;
import org.compiere.model.X_AD_UserMail;
import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.util.CLogger;
import org.compiere.util.EMail;
import org.compiere.util.Env;
import org.spin.queue.notification.model.MADNotificationQueue;

/**
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Implementation of email sender for backward compatibility  
 */
public class EMailSender implements INotification {

	/**	Registration Id	*/
	private int registrationId = 0;
	/** Static Logger					*/
	private CLogger log = CLogger.getCLogger (EMailSender.class);
	
	@Override
	public int getAppRegistrationId() {
		return registrationId;
	}

	@Override
	public void setAppRegistrationId(int registrationId) {
		this.registrationId = registrationId;
		log.fine("set Registration: " + registrationId);
	}

	@Override
	public String testConnection() {
		log.fine("Test connection");
		return "Ok";
	}

	@Override
	public void sendNotification(MADNotificationQueue notification) {
		StringBuffer errorMessage = new StringBuffer();
		notification.getRecipients().forEach(recipient -> {

			int eMailConfigurationId = 0;
			String eMailUser = null;
			String eMailPassword = null;

			MUser notificationUser = new MUser(notification.getCtx(), notification.getCreatedBy(), notification.get_TrxName());
			MClient client = MClient.get(notification.getCtx(), notification.getAD_Client_ID());
			eMailConfigurationId = notificationUser.getAD_EMailConfig_ID();
			eMailUser = notificationUser.getEMailUser();
			eMailPassword = notificationUser.getEMailUserPW();

			if (eMailConfigurationId <= 0 || StringUtils.isEmpty(eMailUser) || StringUtils.isEmpty(eMailPassword)) {
				if (notification.getAD_Org_ID() > 0) {
					MOrg organization = MOrg.get(notification.getCtx(), notification.getAD_Org_ID());
					MOrgInfo organizationInformation = organization.getInfo();
					if (organizationInformation != null && organizationInformation.get_ID() > 0) {
						eMailConfigurationId = organizationInformation.getAD_EMailConfig_ID();
						eMailUser = organizationInformation.getEMail();
						eMailPassword = organizationInformation.getRequestUserPW();
					}
				}
			}

			if (eMailConfigurationId <= 0 || StringUtils.isEmpty(eMailUser) || StringUtils.isEmpty(eMailPassword)) {
				eMailUser = client.getRequestEMail();
				eMailPassword = client.getRequestUserPW();
				eMailConfigurationId = client.getAD_EMailConfig_ID();
			}

			if (eMailConfigurationId <= 0 || StringUtils.isEmpty(eMailUser) || StringUtils.isEmpty(eMailPassword)) {
				log.warning("@AD_EMailConfig_ID@ @NotFound@");
				if (errorMessage.length() > 0) {
					errorMessage.append(Env.NL);
				}
				errorMessage.append("@AD_EMailConfig_ID@ @NotFound@");
			} else {
				//	Create instance
				EMail email = new EMail(client,
						eMailConfigurationId,
						eMailUser,
						recipient.getAccountName(),
						notification.getDescription(),
						notification.getText(),
						false);
				if (!email.isValid() && !email.isValid(true)) {
					log.warning("NOT VALID - " + email);
					if (errorMessage.length() > 0) {
						errorMessage.append(Env.NL);
					}
					errorMessage.append("NOT VALID - ").append(email);
				} else {
					MEMailConfig eMailConfig = MEMailConfig.get(notification.getCtx(), eMailConfigurationId);
					if (eMailConfig.isSmtpAuthorization()) {
						email.createAuthenticator(eMailUser, eMailPassword);
					}
					//	Subject
					email.setSubject(notification.getDescription());
					email.setMessageHTML(notification.getText());
					//	Attachment
					MAttachment attachment = notification.getAttachment();
					if (attachment != null
							&& attachment.getAD_Attachment_ID() > 0) {
						Arrays.asList(attachment.getEntries()).forEach(entry -> {
							email.addAttachment(entry.getFile());
						});
					}
					boolean isSent = EMail.SENT_OK.equals(email.send());
					if (isSent) {
						log.fine("EMail Sent: " + recipient.getAccountName());
						recipient.setProcessed(true);
					} else {
						if (errorMessage.length() > 0) {
							errorMessage.append(Env.NL);
						}
						errorMessage.append("Error: Sending to: ").append(recipient.getAccountName());
						recipient.setErrorMsg("Error: Sending to: " + recipient.getAccountName());
					}
					recipient.saveEx();
					//	Backward compatibility
					if (recipient.getAD_User_ID() > 0) {
						X_AD_UserMail userMail = new X_AD_UserMail(
								notification.getCtx(),
								0,
								notification.get_TrxName()
						);
						userMail.setAD_Org_ID(notification.getAD_Org_ID());
						userMail.setAD_User_ID(recipient.getAD_User_ID());
						userMail.setSubject(email.getSubject());
						userMail.setMailText(email.getMessageCRLF());
						if (email.isSentOK()) {
							userMail.setMessageID(email.getMessageID());
						} else {
							userMail.setMessageID(email.getSentMsg());
							userMail.setIsDelivered(X_AD_UserMail.ISDELIVERED_No);
						}
						userMail.saveEx();
					}
				}
			}
		});
		if(errorMessage.length() > 0) {
			throw new AdempiereException(errorMessage.toString());
		}
	}

}
