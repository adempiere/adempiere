/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.request.process;

import org.compiere.model.*;
import org.compiere.process.RequestEMailProcessorAbstract;
import org.compiere.util.DB;
import org.compiere.util.EMail;
import org.compiere.util.Util;

import javax.mail.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.logging.Level;

/**
 *	Request Email Processor
 *	
 *  @author Carlos Ruiz based on initial work by Jorg Janke - sponsored by DigitalArmour
 *  @version $Id: RequestEMailProcessor.java,v 1.2 2006/10/23 06:01:20 cruiz Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *			<li> FR [ 402 ] Mail setup is hardcoded
 *			@see https://github.com/adempiere/adempiere/issues/402
 *			<li> FR [ 1308 ] Unable To send test mail
 *			@see https://github.com/adempiere/adempiere/issues/1308
 */
public class RequestEMailProcessor extends RequestEMailProcessorAbstract {
	/**	Email Configuration		*/
	private EMail email = null;

	private int noProcessed = 0;
	private int noRequest = 0;
	private int noError = 0;
	/**	Process Error				*/
	private static final int		ERROR = 0;
	/**	Process Request				*/
	private static final int		REQUEST = 1;

	/**
	 *  Perform process.
	 *  @return Message (clear text)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception {
		log.info("doIt - IMAPHost=" + getRequestUser() +
				       " IMAPPwd=" + getRequestUserPW()  +
				       " RequestFolder=" + getRequestFolder() +
				       " InboxFolder=" + getInboxFolder() +
				       " ErrorFolder=" + getErrorFolder());
		
		try {
			processInBox();
		} catch (Exception e) {
			log.log(Level.SEVERE, "processInBox", e);
		}
		addLog("@Total@=" + noProcessed);
		addLog("@R_Request_ID@=" + noRequest);
		addLog("@Error@=" + noError);
		//	
		return "Ok";
	}	//	doIt
	
	/**
	 * 	Process InBox
	 *	@return number of processed
	 *	@throws Exception
	 */
	private void processInBox() throws Exception {
		//	
		email = new EMail(MClient.get(getCtx()), getEMailConfigId());
		email.createAuthenticator (getRequestUser(), getRequestUserPW());
		//	Folder
		Folder folder;
		folder = email.getDefaultFolder();
		if (folder == null)
			throw new IllegalStateException("@DefaultFolder@ @NotFound@");
		//	Open Inbox
		Folder inbox = folder.getFolder(getInboxFolder());
		if (!inbox.exists())
			throw new IllegalStateException("@InboxFolder@ @NotFound@");
		//	
		inbox.open(Folder.READ_WRITE);
		log.fine("processInBox - " + inbox.getName() 
			+ "; Messages Total=" + inbox.getMessageCount()
			+ "; New=" + inbox.getNewMessageCount());
		//	Validate IMAP
		if(!email.getProtocol().equals(MEMailConfig.PROTOCOL_IMAP))
			return;
		//	Open Request
		Folder requestFolder = folder.getFolder(getRequestFolder());
		if (!requestFolder.exists() && !requestFolder.create(Folder.HOLDS_MESSAGES))
			throw new IllegalStateException("Cannot create Request Folder");
		requestFolder.open(Folder.READ_WRITE);
		//	Open Error
		Folder errorFolder = folder.getFolder(getErrorFolder());
		if (!errorFolder.exists() && !errorFolder.create(Folder.HOLDS_MESSAGES))
			throw new IllegalStateException("Cannot create Error Folder");
		errorFolder.open(Folder.READ_WRITE);
		
		//	Messages
		Message[] messages = inbox.getMessages();
		//
		for (Message msg : messages) {
			int result = processMessage (msg);
			if (result == REQUEST) {
				String[] hdrs = msg.getHeader("Message-ID");
				//	Copy to processed
				try {
					if (createRequest(msg)) {
						msg.setFlag(Flags.Flag.SEEN, true);
						msg.setFlag(Flags.Flag.ANSWERED, true);
						requestFolder.appendMessages(new Message[]{msg});
						// log movement
						log.info("message " + hdrs[0] + " moved to " + getRequestFolder() + " folder");
						log.info("message info: Sent -> " + msg.getSentDate() + " From -> " + msg.getFrom()[0].toString());
						// Delete in InBox
						msg.setFlag(Flags.Flag.DELETED, true);
						inbox.expunge();
						noRequest++;
					}
				} catch (Exception e) {
					log.info("message " + hdrs[0] + " threw error");
				}
			} else {	//	error
				errorFolder.appendMessages(new Message[]{msg});
				String[] hdrs = msg.getHeader("Message-ID");
				log.warning("message " + hdrs[0] + " moved to " + getErrorFolder() + " folder");
				log.warning("message info: Sent -> " + msg.getSentDate() + " From -> " + msg.getFrom()[0].toString());
				noError++;
			}
			noProcessed++;
		}
		
		log.info("processInBox - Total=" + noProcessed + 
				" - Requests=" + noRequest + 
				" - Errors=" + noError);
		//	Fini
		errorFolder.close(false);
		requestFolder.close(false);
		// workflowFolder.close(false);
		//
		inbox.close(true);
	}	//	processInBox
	
	/**
	 * 	Create request
	 *	@param msg message
	 * @return 
	 *	@return Type of Message
	 * @throws MessagingException
	 */
	private boolean createRequest(Message msg) throws MessagingException, SQLException {
		// Assign from variable
		Address[] from = msg.getFrom();
		String fromAddress;
		if(from == null
				|| from.length == 0) {
			return false;
		}
		// Carlos Ruiz <c_ruiz@myrealbox.com>
		if (from[0].toString().indexOf('<')!= -1 && from[0].toString().indexOf('>')!= -1) {
			fromAddress = from[0].toString().substring(from[0].toString().indexOf('<')+1, from[0].toString().indexOf('>'));
			log.info("fromAddress stripped: "+fromAddress);
		} else {
			fromAddress = from[0].toString(); 
		}
		// Message-ID as documentNo
		String[] hdrs = msg.getHeader("Message-ID");
		String firstHeader = "";
		String documentNo = "";
		//	Get first
		if(hdrs != null
				&& hdrs.length > 0) {
			firstHeader = hdrs[0];
		}
		//	
		if(!Util.isEmpty(firstHeader)) {
			if(firstHeader.length() >= 30) {
				documentNo = firstHeader.substring(0, 30);
			} else {
				documentNo = firstHeader;
			}
		}
		// Review if the e-mail was already created, comparing Message-ID+From+body
		int retValuedup = 0;
		String sqldup = "SELECT R_Request_ID FROM R_Request "
			 + "where AD_Client_ID = ? "
			 + "AND DocumentNo = ? "
			 + "AND StartDate = ?";
		retValuedup = DB.getSQLValue(get_TrxName(), sqldup,
				getAD_Client_ID(), 
				documentNo, 
				new Timestamp(msg.getSentDate().getTime()));
		if (retValuedup > 0) {
			log.info("request already existed for msg -> " + firstHeader);
			return true;
		}
		// Analyze subject if Re: find the original request by subject + e-mail and add an action
		int request_upd = 0;
		String sqlupd = "SELECT R_Request_ID "
			 + "  FROM R_Request "
			 + " WHERE AD_Client_ID = ? "
			 + "   AND Summary LIKE 'FROM: ' || ? || '%' "
			 + "   AND (   DocumentNo = "
			 + "              SUBSTR "
			 + "                 (?, "
			 + "                  INSTR "
			 + "                      (?, "
			 + "                       '<' "
			 + "                      ) "
			 + "                 ) "
			 + "        OR (    ? LIKE 'Re: %' "
			 + "            AND Summary = "
			 + "                      'FROM: ' "
			 + "                   || ? "
			 + "                   || CHR (10) "
			 + "                   || SUBSTR (?, 5) "
			 + "           ) "
			 + "       ) ";
		request_upd = DB.getSQLValue(get_TrxName(), sqlupd,
				getAD_Client_ID(), 
				fromAddress, 
				msg.getSubject(), 
				msg.getSubject(), 
				msg.getSubject(), 
				fromAddress, 
				msg.getSubject());
			if (request_upd > 0) {
			log.info("msg -> " + firstHeader + " is an answer for req " + request_upd);
			return updateRequest(request_upd, msg);
		}
		
		MRequest request = new MRequest(getCtx(), 0, get_TrxName());
		// Subject as summary
		request.setSummary("FROM: " + fromAddress + "\n" + msg.getSubject());
		// Body as result
		request.setResult("FROM: " + from[0].toString() + "\n" + getMessage(msg));
		// Message-ID as documentNo
		if (documentNo != null) {
			request.setDocumentNo(documentNo);
		}
		// Default request type for this process
		if (getRequestTypeId() > 0) {
			request.setR_RequestType_ID(getRequestTypeId());
		} else {
			request.setR_RequestType_ID();
		}
		// set Default sales representative 
		if (getSalesRepId() > 0) {
			request.setSalesRep_ID(getSalesRepId());
		}
		// set Default role
		if (getRoleId() > 0) {
			request.setAD_Role_ID(getRoleId());
		}
		// Look for user via e-mail
		String sqlu = "SELECT AD_User_ID "
			+ "  FROM AD_User "
			+ " WHERE UPPER (EMail) = UPPER (?) "
			+ "   AND AD_Client_ID = ?";
		int retValueUserId = DB.getSQLValue(get_TrxName(), sqlu, fromAddress, getAD_Client_ID());
		if (retValueUserId > 0) {
			request.setAD_User_ID(retValueUserId);
		} else {
			// set default user
			if (getUserId() > 0) {
				request.setAD_User_ID(getUserId());
			}
		}
		// Look BP
		if (request.getAD_User_ID() > 0) {
			MUser us = new MUser(getCtx(), request.getAD_User_ID(), get_TrxName());
			if (us.getC_BPartner_ID() > 0) {
				request.setC_BPartner_ID(us.getC_BPartner_ID());
			}
		}
		if (request.getC_BPartner_ID() <= 0 && getBPartnerId() > 0) {
			// set default business partner
			request.setC_BPartner_ID(getBPartnerId());
		}
		
		// Set start date as sent date of e-mail
		request.setStartDate(new Timestamp(msg.getSentDate().getTime()));
		
		// defaults priority Medium, confidentiality partner
		if (getConfidentialType() != null) {
			request.setConfidentialType (getConfidentialType());
			request.setConfidentialTypeEntry (getConfidentialType());
		}
		if (getPriorityRule() != null) {
			request.setPriority(getPriorityRule());
			request.setPriorityUser(getPriorityRule());
		}
		
		if (request.save(get_TrxName())) {
			log.info("created request " + request.getR_Request_ID() + " from msg -> " + firstHeader);
			// get simple attachments and attach to request
			if (msg.isMimeType("multipart/*" )) {
				try {
					Multipart mp = (Multipart) msg.getContent();

					for (int i=0, n=mp.getCount(); i<n; i++) {
						Part part = mp.getBodyPart(i);

						String disposition = part.getDisposition();

						if ((disposition != null) && 
								((disposition.equals(Part.ATTACHMENT) ||
										(disposition.equals(Part.INLINE))))) {

							MAttachment attach = request.createAttachment();

							InputStream in = part.getInputStream();

							ByteArrayOutputStream out = new ByteArrayOutputStream();
							final int BUF_SIZE = 1 << 8; //1KiB buffer
							byte[] buffer = new byte[BUF_SIZE];
							int bytesRead = -1;
							while((bytesRead = in.read(buffer)) > -1) {
								out.write(buffer, 0, bytesRead);
							}
							in.close();

							byte[] bytes = out.toByteArray();

							attach.addEntry(part.getFileName(), bytes);
							attach.saveEx(get_TrxName());
						}
					}
				}
				catch (IOException e) {
					log.log(Level.FINE, "Error extracting attachments", e);
				}
			}
			return true;
		} else {
			return false;
		}
	}

	private boolean updateRequest(int request_upd, Message msg) throws MessagingException, SQLException {
		MRequest requp = new MRequest(getCtx(), request_upd, get_TrxName());
		// Body as result
		Address[] from = msg.getFrom();
		requp.setResult("FROM: " + from[0].toString() + "\n" + getMessage(msg));
		return requp.save();
	}

	/**
	 * 	Process Message
	 *	@param msg message
	 *	@return Type of Message
	 *	@throws Exception
	 */
	private int processMessage (Message msg) throws Exception
	{
		dumpEnvelope (msg);
		dumpBody (msg);
		log.finer(":::::::::::::::");
		log.finer(getSubject(msg));
		log.finer(":::::::::::::::");
		log.finer(getMessage(msg));
		log.finer(":::::::::::::::");
		String delivery = getDeliveryReport(msg);
		log.finer(delivery);
		log.finer(":::::::::::::::");
		Address[] from;
		// FROM
		if ((from = msg.getFrom()) != null)
		{
			// send to error messages from postmaster@CONSULTDESK
			// TODO: possible enhancement - put error from accounts in a table
			if (from[0].toString().equalsIgnoreCase("postmaster@CONSULTDESK"))
				return ERROR;
		} else {
			// there is no from
			return ERROR;
		}
		
		// By now this account is to process requests
		return REQUEST; 
		
	}	//	processMessage
	
	/**
	 * 	Get Subject
	 *	@param msg message
	 *	@return subject or ""
	 */
	private String getSubject (Message msg)
	{
		try
		{
			String str = msg.getSubject();
			if (str != null)
				return str.trim();
		}
		catch (MessagingException e)
		{
			log.log(Level.SEVERE, "getSubject", e);
		}
		return "";
	}	//	getSubject
	
	/**
	 * 	Get Message
	 *	@param msg Message
	 *	@return message or ""
	 */
	private String getMessage (Part msg)
	{
		StringBuffer sb = new StringBuffer();
		try
		{
			//	Text
			if (msg.isMimeType("text/plain"))
			{
				sb.append(msg.getContent());
			}
			//	Other Text (e.g. html/xml) 
			else if (msg.isMimeType("text/*"))
			{
				sb.append(msg.getContent());
			}
			//	Nested
			else if (msg.isMimeType("message/rfc822"))
			{
				sb.append(msg.getContent());
			}
			//	Multi Part Alternative
			else if (msg.isMimeType("multipart/alternative"))
			{
				String plainText = null;
				String otherStuff = null;
				//
				Multipart mp = (Multipart)msg.getContent();
				int count = mp.getCount();
				for (int i = 0; i < count; i++)
				{
					Part part = mp.getBodyPart(i);
					Object content = part.getContent();
					if (content == null || content.toString().trim().length() == 0)
						continue;
					if (part.isMimeType("text/plain"))
						plainText = content.toString();
					else
						otherStuff = content.toString();
				}
				if (plainText != null)
					sb.append(plainText);
				else if (otherStuff != null)
					sb.append(otherStuff);
			}
			//	Multi Part
			else if (msg.isMimeType("multipart/*"))
			{
				Multipart mp = (Multipart)msg.getContent();
				int count = mp.getCount();
				for (int i = 0; i < count; i++)
				{
					String str = getMessage(mp.getBodyPart(i));
					if (str.length() > 0)
					{
						if (sb.length() > 0)
							sb.append("\n-----\n");
						sb.append(str);
					}
				}
			}
			else
			{
				/*
				 * If we actually want to see the data, and it's not a
				 * MIME type we know, fetch it and check its Java type.
				 */
				Object o = msg.getContent();
				if (o instanceof String)
				{
					sb.append(o);
				}
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "getMessage", e);
		}
		return sb.toString().trim();
	}	//	getMessage

	/**
	 * 	Get Delivery Report
	 *	@param msg message
	 *	@return delivery info or null
	 */
	private String getDeliveryReport (Part msg)
	{
		try
		{
			if (msg.isMimeType("multipart/report"))
			{
				String deliveryMessage = null;
				String otherStuff = null;
				//
				Multipart mp = (Multipart)msg.getContent();
				int count = mp.getCount();
				for (int i = 0; i < count; i++)
				{
					Part part = mp.getBodyPart(i);
					Object content = part.getContent();
					if (content == null)
						continue;
					if (part.isMimeType("message/*"))
						deliveryMessage = getDeliveredReportDetail (part);
					else
						otherStuff = content.toString().trim();
				}
				if (deliveryMessage != null)
					return deliveryMessage;
				return otherStuff;
			}
			else if (msg.isMimeType("message/*"))
			{
				return getDeliveredReportDetail (msg);
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "getDeliveryReport", e);
		}
		//	Nothing
		return null;
	}	//	getDeliveryReport
	
	/**
	 * 	Get Delivered Report Detail
	 *	@param part Mime Type message/*
	 *	@return info or null
	 *	@throws Exception
	 */
	private String getDeliveredReportDetail (Part part) throws Exception
	{
		Object content = part.getContent();
		if (content == null)
			return null;
		
		String deliveryMessage = null;
		if (content instanceof InputStream)
		{
			StringBuffer sb = new StringBuffer();
			InputStream is = (InputStream)content;
			int c;
			while ((c = is.read()) != -1)
				sb.append((char)c);
			deliveryMessage = sb.toString().trim();
		}
		else
			deliveryMessage = content.toString().trim();
		//
		if (deliveryMessage == null)
			return null;
		
		//	Final-Recipient: RFC822; jjanke@compiere.org
		int index = deliveryMessage.indexOf("Final-Recipient:");
		if (index != -1)
		{
			String finalRecipient = deliveryMessage.substring(index);
			int atIndex = finalRecipient.indexOf("@");
			if (atIndex != -1)
			{
				index = finalRecipient.lastIndexOf(' ', atIndex);
				if (index != -1)
					finalRecipient = finalRecipient.substring(index+1);
				atIndex = finalRecipient.indexOf("@");
				if (atIndex != -1)
					index = finalRecipient.indexOf(' ', atIndex);
				if (index != -1)
					finalRecipient = finalRecipient.substring(0, index);
				index = finalRecipient.indexOf('\n');
				if (index != -1)
					finalRecipient = finalRecipient.substring(0, index);
				return finalRecipient.trim();
			}
		}
		return deliveryMessage;
	}	//	getDeliveredReportDetail
	
	
	/**************************************************************************
	 * 	Print Envelope
	 *	@param m message
	 *	@throws Exception
	 */
	private void dumpEnvelope(Message m) throws Exception
	{
		log.finer("-----------------------------------------------------------------");
		Address[] a;
		// FROM
		if ((a = m.getFrom()) != null)
		{ 
			for (int j = 0; j < a.length; j++)
				log.finer("FROM: " + a[j].toString());
		}

		// TO
		if ((a = m.getRecipients(Message.RecipientType.TO)) != null)
		{
			for (int j = 0; j < a.length; j++)
				log.finer("TO: " + a[j].toString());
		}

		// SUBJECT
		log.finer("SUBJECT: " + m.getSubject());

		// DATE
		java.util.Date d = m.getSentDate();
		log.finer("SendDate: " + (d != null ? d.toString() : "UNKNOWN"));

		// FLAGS
		Flags flags = m.getFlags();
		StringBuffer sb = new StringBuffer();
		Flags.Flag[] sf = flags.getSystemFlags(); // get the system flags

		boolean first = true;
		for (int i = 0; i < sf.length; i++)
		{
			String s;
			Flags.Flag f = sf[i];
			if (f == Flags.Flag.ANSWERED)
				s = "\\Answered";
			else if (f == Flags.Flag.DELETED)
				s = "\\Deleted";
			else if (f == Flags.Flag.DRAFT)
				s = "\\Draft";
			else if (f == Flags.Flag.FLAGGED)
				s = "\\Flagged";
			else if (f == Flags.Flag.RECENT)
				s = "\\Recent";
			else if (f == Flags.Flag.SEEN)
				s = "\\Seen";
			else
				continue;	// skip it
			if (first)
				first = false;
			else
				sb.append(' ');
			sb.append(s);
		}

		String[] uf = flags.getUserFlags(); // get the user flag strings
		for (int i = 0; i < uf.length; i++)
		{
			if (first)
				first = false;
			else
				sb.append(' ');
			sb.append(uf[i]);
		}
		log.finer("FLAGS: " + sb.toString());

		// X-MAILER
		String[] hdrs = m.getHeader("X-Mailer");
		if (hdrs != null)
		{
			StringBuffer sb1 = new StringBuffer("X-Mailer: ");
			for (int i = 0; i < hdrs.length; i++)
				sb1.append(hdrs[i]).append("  ");
			log.finer(sb1.toString());
		}
		else
			log.finer("X-Mailer NOT available");
		
		//	Message ID
		hdrs = m.getHeader("Message-ID");
		if (hdrs != null)
		{
			StringBuffer sb1 = new StringBuffer("Message-ID: ");
			for (int i = 0; i < hdrs.length; i++)
				sb1.append(hdrs[i]).append("  ");
			log.finer(sb1.toString());
		}
		else
			log.finer("Message-ID NOT available");
		
		//	All
		log.finer("ALL HEADERs:");
		Enumeration<Header> en = m.getAllHeaders();
		while (en.hasMoreElements())
		{
			Header hdr = (Header)en.nextElement();
			log.finer("  " + hdr.getName() + " = " + hdr.getValue());
		}
		
		
		log.finer("-----------------------------------------------------------------");
	}	//	printEnvelope

	/**
	 * 	Print Body
	 *	@param p
	 *	@throws Exception
	 */
	private void dumpBody (Part p) throws Exception
	{
		//	http://www.iana.org/assignments/media-types/
		log.finer("=================================================================");
		log.finer("CONTENT-TYPE: " + p.getContentType());
		/**
		Enumeration en = p.getAllHeaders();
		while (en.hasMoreElements())
		{
			Header hdr = (Header)en.nextElement();
			log.finer("  " + hdr.getName() + " = " + hdr.getValue());
		}
		log.finer("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
		/** **/
		
		/**
		 * Using isMimeType to determine the content type avoids
		 * fetching the actual content data until we need it.
		 */
		if (p.isMimeType("text/plain")) {
			log.finer("Plain text ---------------------------");
			log.finer((String)p.getContent());
		} else if (p.getContentType().toUpperCase().startsWith("TEXT")) {
			log.finer("Other text ---------------------------");
			log.finer((String)p.getContent());
		} else if (p.isMimeType("multipart/*")) {
			log.finer("Multipart ---------------------------");
			Multipart mp = (Multipart)p.getContent();
			int count = mp.getCount();
			for (int i = 0; i < count; i++)
				dumpBody(mp.getBodyPart(i));
		} else if (p.isMimeType("message/rfc822")) {
			log.finer("Nested ---------------------------");
			dumpBody((Part)p.getContent());
		} else {
			/*
			 * If we actually want to see the data, and it's not a
			 * MIME type we know, fetch it and check its Java type.
			 */
			Object o = p.getContent();
			if (o instanceof String) {
				log.finer("This is a string ---------------------------");
				log.finer((String)o);
			} else if (o instanceof InputStream) {
				log.finer("This is just an input stream ---------------------------");
				// TODO: process attachment
				// InputStream is = (InputStream)o;
				// int c;
				// while ((c = is.read()) != -1)
					// System.out.write(c);  // must be log. ??
			} else {
				log.finer("This is an unknown type ---------------------------");
				log.finer(o.toString());
			}
		}
		log.finer("=================================================================");
	}	//	printBody
}	//	RequestEMailProcessor
