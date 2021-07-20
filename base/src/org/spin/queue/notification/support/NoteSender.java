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
import java.util.Optional;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttachment;
import org.compiere.model.MNote;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.spin.queue.model.MADQueue;
import org.spin.queue.notification.model.MADNotificationQueue;

/**
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Implementation of notes sender for backward compatibility  
 */
public class NoteSender implements INotification {

	/**	Registration Id	*/
	private int registrationId = 0;
	/** Static Logger					*/
	private CLogger log = CLogger.getCLogger (NoteSender.class);
	
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
			try {
				MNote note = new MNote(notification.getCtx(), 0, notification.get_TrxName());
				if(recipient.getAD_User_ID() > 0) {
					note.setAD_User_ID(recipient.getAD_User_ID());
				}
				MADQueue queue = new MADQueue(notification.getCtx(), notification.getAD_Queue_ID(), notification.get_TrxName());
				if(queue.getAD_Table_ID() > 0) {
					note.setRecord(queue.getAD_Table_ID(), queue.getRecord_ID());
				}
				//	Description
				if(!Util.isEmpty(notification.getDescription())) {
					note.setDescription(notification.getDescription());
				}
				//	Text
				if(!Util.isEmpty(notification.getText())) {
					note.setTextMsg(notification.getText());
				}
				/* TODO - Hardcoded message=notes */
				note.setAD_Message_ID(52244);
				//	
				note.saveEx();
				//	Attachment
				MAttachment attachment = notification.getAttachment();
				if(attachment != null
						&& attachment.getAD_Attachment_ID() > 0) {
					MAttachment noteAttchment = note.createAttachment();
					Arrays.asList(attachment.getEntries()).forEach(entry -> {
						noteAttchment.addEntry(entry);
					});
					Optional.ofNullable(attachment.getTextMsg()).ifPresent(comment -> noteAttchment.addTextMsg(comment));
					noteAttchment.saveEx(note.get_TrxName());
				}
				recipient.setProcessed(true);
				recipient.saveEx();
				log.fine("Note sent: " + note);	
			} catch (Exception e) {
				log.severe(e.getLocalizedMessage());
				recipient.setErrorMsg(e.getLocalizedMessage());
				recipient.saveEx();
				if(errorMessage.length() > 0) {
					errorMessage.append(Env.NL);
				}
	        	errorMessage.append("Error: Sending to: " + recipient.getAccountName() + ": " + e.getLocalizedMessage());
			}
		});
		if(errorMessage.length() > 0) {
			throw new AdempiereException(errorMessage.toString());
		}
	}

}
