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
package org.compiere.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.adempiere.util.EMailOAuth2Provider;
import org.compiere.model.MClient;
import org.compiere.model.MEMailConfig;
import org.compiere.model.MSysConfig;

import com.sun.mail.smtp.SMTPMessage;
import com.sun.mail.smtp.SMTPTransport;

/**
 *	EMail Object.
 *	Resources:
 *	http://java.sun.com/products/javamail/index.html
 * 	http://java.sun.com/products/javamail/FAQ.html
 *
 *  <p>
 *  When I try to send a message, I get javax.mail.SendFailedException:
 * 		550 Unable to relay for my-address
 *  <br>
 *  This is an error reply from your SMTP mail server. It indicates that
 *  your mail server is not configured to allow you to send mail through it.
 *
 *  @author Jorg Janke
 *  @version  $Id: EMail.java,v 1.4 2006/07/30 00:54:35 jjanke Exp $
 *	@author	Michael Judd BF [ 2736995 ] - toURL() in java.io.File has been depreciated
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *			<li> FR [ 402 ] Mail setup is hardcoded
 *			@see https://github.com/adempiere/adempiere/issues/402
 *			<li> FR [ 423 ] Add support to oAuth EMail
 *			@see https://github.com/adempiere/adempiere/issues/423
 *			<li> FR [ 1308 ] Unable To send test mail
 *			@see https://github.com/adempiere/adempiere/issues/1308
 */
public final class EMail implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1408649015285763245L;
	//use in server bean
	public final static String HTML_MAIL_MARKER = "ContentType=text/html;";
	
	/**
	 * Constructor without custom mail config by user and no html
	 * @param client
	 * @param from
	 * @param to
	 * @param subject
	 * @param message
	 */
	public EMail (MClient client, String from, String to, 
			String subject, String message) {
		this(client, 0, from, to, subject, message, false);
	}
	
	/**
	 * Full Constructor for Client
	 * @param client Current Client
	 * @param eMailConfigId
	 * @param from Sender's EMail address
	 * @param to Recipient EMail address
	 * @param subject Subject of message
	 * @param message The message
	 * @param html html email
	 */
	public EMail (MClient client, int eMailConfigId, String from, String to, 
		String subject, String message, boolean html) {
		this.client = client;
		ctx = client.getCtx();
		isManual = false;
		//	FR [ 402 ]
		setEMailParameters(eMailConfigId);
		//	Configure EMail
		configureEMail(from, to, subject, message, html);
		valid = isValid (true);
	}	//	EMail
	
	/**
	 * Default instance not client configuration
	 * @param smtpHost
	 * @param from
	 * @param to
	 * @param subject
	 * @param message
	 * @param html
	 */
	@Deprecated
	public EMail(String smtpHost, String from, String to, 
			String subject, String message, boolean html) {
		this(smtpHost, MEMailConfig.DEFAULT_SMTP_PORT, MEMailConfig.PROTOCOL_SMTP, MEMailConfig.ENCRYPTIONTYPE_None, 
				MEMailConfig.AUTHMECHANISM_Login, from, to, subject, message, html);
	}
	
	/**
	 * Full Manual constructor
	 * @param smtpHost
	 * @param smtpPort
	 * @param protocol
	 * @param encryptionType
	 * @param authMechanism
	 * @param from
	 * @param to
	 * @param subject
	 * @param message
	 * @param html
	 */
	@Deprecated
	public EMail(String smtpHost, int smtpPort, String protocol, String encryptionType, String authMechanism, String from, String to, 
			String subject, String message, boolean html) {
		setSmtpHost(smtpHost);
		setPort(smtpPort);
		setProtocol(protocol);
		setEncryptionType(encryptionType);
		setAuthMechanism(authMechanism);
		isSmtpAuthorization = false;
		isManual = true;
		//	
		if(from == null
				&& to == null)
			return;
		//	For send
		configureEMail(from, to, subject, message, html);
		//	Is Valid?
		valid = isValid (true);
	}
	
	/**
	 * Standard Constructor, test receipt
	 * @param smtpHost
	 * @param smtpPort
	 * @param protocol
	 * @param encryptionType
	 * @param authMechanism
	 */
	@Deprecated
	public EMail(String smtpHost, int smtpPort, String protocol, String encryptionType, String authMechanism) {
		this(smtpHost, smtpPort, protocol, encryptionType, authMechanism, null, null, null, null, false);
	}	
	
	/**
	 * Constructor used by receipt server
	 * @param client
	 * @param eMailConfigId
	 */
	public EMail (MClient client, int eMailConfigId) {
		//	FR [ 402 ]
		this(client, eMailConfigId, null, null, null, null, false);
	}
	
	/**
	 * Constructor used by receipt server, get configuration from client
	 * @param client
	 */
	public EMail (MClient client) {
		this(client, client.getAD_EMailConfig_ID());
	}

	/**	From Address				*/
	private InternetAddress     from;
	/** To Address					*/
	private ArrayList<InternetAddress>	to;
	/** CC Addresses				*/
	private ArrayList<InternetAddress>	cc;
	/** BCC Addresses				*/
	private ArrayList<InternetAddress>	bcc;
	/**	Reply To Address			*/
	private InternetAddress		replyTo;
	/**	Mail Subject				*/
	private String  			subject;
	/** Mail Plain Message			*/
	private String  			messageText;
	/** Mail HTML Message			*/
	private String  			messageHTML;
	/**	Mail SMTP Server			*/
	private String  			host;
	/**	Mail SMTP Server Port		*/
	private int  				port = 0;
	/**	Protocol					*/
	private String 				protocol = null;
	/**	SMTP Encryption Type		*/
	private String 				encryptionType = null;
	/**	SMTP Authentication Mechanism*/
	private String 				authMechanism = null;
	/**	SMTP Authentication			*/
	private boolean 			isSmtpAuthorization = false;
	/**	Domain						*/
	private String 				domain = null;
	/**	Timeout						*/
	private long				timeout = 0;
	/**	Connection Timeout			*/
	private long				connectionTimeout = 0;
	/**	Custom EMail Configuration	*/
	private int					eMailConfigId = 0;
	/**	Attachments					*/
	private ArrayList<Object>	attachments;
	/**	UserName and Password		*/
	private EMailAuthenticator	auth = null;
	/**	Message						*/
	private SMTPMessage 		msg = null;
	/**	User						*/
	private String				user = null;
	/**	Token						*/
	private String				token = "";
	/** Context - may be null		*/
	private Properties			ctx;
	/** Client						*/
	private MClient				client;
	/**	Is Manual					*/
	private boolean				isManual = false;

	/**	Info Valid					*/
	private boolean 			valid = false;
	/** Send result Message			*/
	private String				sentMsg = null;
	/**	Store				*/
	private Store 				store = null;
	/**	Session				*/
	private Session 			session = null;
	/**	Transport			*/
	private Transport 			transport = null;

	/**	Mail Sent OK Status				*/
	public static final String      SENT_OK = "OK";

	/**	Logger							*/
	protected static CLogger		log = CLogger.getCLogger (EMail.class);

	/**
	 * Get Session from email config
	 * @return
	 */
	public Session getSession() {
		if (session != null)
			return session;
		//	
		if(getAuthMechanism().equals(MEMailConfig.AUTHMECHANISM_OAuth)) {
			//	Add initialization
			Security.addProvider(new EMailOAuth2Provider());
			session = Session.getInstance(getEMailProperties());
		} else {
			session = Session.getInstance(getEMailProperties(), auth);
		}
		session.setDebug(CLogMgt.isLevelFinest());
		//	
		return session;
	}
	
	/**
	 * Get Transport
	 * @param session
	 * @return
	 * @throws MessagingException
	 */
	public Transport getTransport(Session session) throws MessagingException {
		if (transport != null)
			return transport;
		if(getAuthMechanism().equals(MEMailConfig.AUTHMECHANISM_OAuth)) {
			transport = new SMTPTransport(session, null);
			transport.connect(getSmtpHost(), getPort(), user, token);
		} else {
			transport = session.getTransport(getStringProtocol());
			transport.connect();
		}
		//	
		this.session = session;
		//	Log
		log.fine("transport=" + transport);
		log.fine("transport connected");
		//	Default Return
		return transport;
	}
	
	/**
	 * Get transport without session
	 * @return
	 * @throws MessagingException
	 */
	public Transport getTransport() throws MessagingException {
		if (transport != null)
			return transport;
		//	
		return getTransport(getSession());
	}
	
	/**
	 * Get default folder
	 * @return
	 * @throws MessagingException
	 */
	public Folder getDefaultFolder() throws MessagingException {
		if(store != null) {
			return store.getDefaultFolder();
		}
		//	Get it
		getStore();
		return store.getDefaultFolder();
	}
	
	/**
	 * 	Get Store
	 *	@return Store
	 * @throws MessagingException 
	 * @throws Exception
	 */
	public Store getStore() throws MessagingException {
		if (store != null)
			return store;
		if (getSession() == null)
			throw new IllegalStateException("No Session");
		//	Get Store
		store = session.getStore(getStringProtocol());
		if(getAuthMechanism().equals(MEMailConfig.AUTHMECHANISM_OAuth)) {
			store.connect(getSmtpHost(), user, token);
		} else {
			store = session.getStore(getStringProtocol());
			store.connect();
		}
		//
		log.fine("getStore - " + store);
		return store;
	}	//	getStore
	
	/**
	 *	Send Mail direct
	 *	@return OK or error message
	 */
	public String send() {
		log.info("(" + host + ") " + from + " -> " + to);
		sentMsg = null;
		//
		if (!isValid(true))
		{
			sentMsg = "Invalid Data";
			return sentMsg;
		}
		//	FR [ 402 ]
		try {
			getSession();
		} catch (SecurityException se) {
			log.log(Level.WARNING, "Auth=" + auth + " - " + se.toString());
			sentMsg = se.toString();
			return se.toString();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Auth=" + auth, e);
			sentMsg = e.toString();
			return e.toString();
		}

		try
		{
			msg = new SMTPMessage(getSession());
			//	Addresses
			msg.setFrom(from);
			InternetAddress[] rec = getTos();
			if (rec.length == 1)
				msg.setRecipient (Message.RecipientType.TO, rec[0]);
			else
				msg.setRecipients (Message.RecipientType.TO, rec);
			rec = getCcs();
			if (rec != null && rec.length > 0)
				msg.setRecipients (Message.RecipientType.CC, rec);
			rec = getBccs();
			if (rec != null && rec.length > 0)
				msg.setRecipients (Message.RecipientType.BCC, rec);
			if (replyTo != null)
				msg.setReplyTo(new Address[] {replyTo});
			//
			msg.setSentDate(new java.util.Date());
			msg.setHeader("Comments", "AdempiereMail");
			//	SMTP specifics
			msg.setAllow8bitMIME(true);
			//	Send notification on Failure & Success - no way to set envid in Java yet
			//	Bounce only header
			msg.setReturnOption (SMTPMessage.RETURN_HDRS);
			//
			setContent();
			msg.saveChanges();
			log.fine("message =" + msg);
			//
			getTransport();
			Transport.send(msg);
			log.fine("Success - MessageID=" + msg.getMessageID());
		}
		catch (MessagingException me)
		{
			Exception ex = me;
			StringBuffer sb = new StringBuffer("(ME)");
			boolean printed = false;
			do
			{
				if (ex instanceof SendFailedException)
				{
					SendFailedException sfex = (SendFailedException)ex;
					Address[] invalid = sfex.getInvalidAddresses();
					if (!printed)
					{
						if (invalid != null && invalid.length > 0)
						{
							sb.append (" - Invalid:");
							for (int i = 0; i < invalid.length; i++)
								sb.append (" ").append (invalid[i]);

						}
						Address[] validUnsent = sfex.getValidUnsentAddresses ();
						if (validUnsent != null && validUnsent.length > 0)
						{
							sb.append (" - ValidUnsent:");
							for (int i = 0; i < validUnsent.length; i++)
								sb.append (" ").append (validUnsent[i]);
						}
						Address[] validSent = sfex.getValidSentAddresses ();
						if (validSent != null && validSent.length > 0)
						{
							sb.append (" - ValidSent:");
							for (int i = 0; i < validSent.length; i++)
								sb.append (" ").append (validSent[i]);
						}
						printed = true;
					}
					if (sfex.getNextException() == null)
						sb.append(" ").append(sfex.getLocalizedMessage());
				}
				else if (ex instanceof AuthenticationFailedException)
				{
					sb.append(" - Invalid Username/Password - " + auth);
				}
				else	//	other MessagingException 
				{
					String msg = ex.getLocalizedMessage();
					if (msg == null)
						sb.append(": ").append(ex.toString());
					else
					{
						if (msg.indexOf("Could not connect to SMTP host:") != -1)
						{
							int index = msg.indexOf('\n');
							if (index != -1)
								msg = msg.substring(0, index);
							String cc = "??";
							if (ctx != null)
								cc = ctx.getProperty("#AD_Client_ID");
							msg += " - AD_Client_ID=" + cc;
						}
						String className = ex.getClass().getName();
						if (className.indexOf("MessagingException") != -1)
							sb.append(": ").append(msg);
						else
							sb.append(" ").append(className).append(": ").append(msg);
					}
				}
				//	Next Exception
				if (ex instanceof MessagingException)
					ex = ((MessagingException)ex).getNextException();
				else
					ex = null;
			}	
			while (ex != null);	//	error loop
			//
			if (CLogMgt.isLevelFinest())
				log.log(Level.WARNING, sb.toString(), me);
			else
				log.log(Level.WARNING, sb.toString());
			sentMsg = sb.toString();
			return sb.toString();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
			sentMsg = e.getLocalizedMessage();
			return e.getLocalizedMessage();
		}
		//
		if (CLogMgt.isLevelFinest())
			dumpMessage();
		sentMsg = SENT_OK;
		return sentMsg;
	}	//	send
	
	/**
	 * Get EMail Properties from type
	 * @return
	 */
	public Properties getEMailProperties() {
		//
		Properties props = System.getProperties();
		//	For Debug
		if (CLogMgt.isLevelFinest()) {
			props.put("mail.debug", "true");
		}
		//	Set Host
		props.put("mail.host", host);
		props.put("mail.store.protocol", getStringProtocol());
		props.put("mail.transport.protocol", getStringProtocol());
		//	SMTP
		if(protocol.equals(MEMailConfig.PROTOCOL_SMTP)) {
			//	Timeout
			if(timeout > 0) {
				props.put("mail.smtp.timeout", String.valueOf(timeout));
			}
			//	Connection Timeout
			if(timeout > 0) {
				props.put("mail.smtp.connectiontimeout", String.valueOf(connectionTimeout));
			}
			//	Set Port
			props.put("mail.smtp.port", String.valueOf(getPort()));
			//	createAuthenticator was called
			if (isSmtpAuthorization && auth != null) {
				props.put("mail.smtp.auth", "true");
			}
			//	Encryption Type
			if(!getAuthMechanism().equals(MEMailConfig.AUTHMECHANISM_OAuth)) {
				if(getEncryptionType().equals(MEMailConfig.ENCRYPTIONTYPE_SSL)) {
					props.put("mail.smtp.ssl.enable", "true");
				} else if(getEncryptionType().equals(MEMailConfig.ENCRYPTIONTYPE_TLS) || getEncryptionType().equals(MEMailConfig.ENCRYPTIONTYPE_STARTTLS)) {
					props.put("mail.smtp.starttls.enable", "true");
				}
				//	
				if(getAuthMechanism().equals(MEMailConfig.AUTHMECHANISM_NTLM)) {
					props.put("mail.smtp.auth.mechanisms","NTLM");
					if(domain != null)
						props.put("mail.smtp.auth.ntlm.domain", domain);	
				}
			} else if(getAuthMechanism().equals(MEMailConfig.AUTHMECHANISM_OAuth)) {
				props.put("mail.smtp.starttls.enable", "true");
			    props.put("mail.smtp.sasl.enable", "true");
			    props.put("mail.smtp.sasl.mechanisms", "XOAUTH2");
			    props.put("mail.imaps.sasl.mechanisms.oauth2.oauthToken", token);
			}
		}
		if(protocol.equals(MEMailConfig.PROTOCOL_IMAP)) {
			//	Timeout
			if(timeout > 0) {
				props.put("mail.imap.timeout", String.valueOf(timeout));
			}
			//	Connection Timeout
			if(timeout > 0) {
				props.put("mail.imap.connectiontimeout", String.valueOf(connectionTimeout));
			}
			//	createAuthenticator was called
			if (auth != null) {
				props.put("mail.imap.auth", "true");
			}
			//	Set Port
			props.put("mail.imap.port", String.valueOf(getPort()));
			//	Encryption Type
			if(!getAuthMechanism().equals(MEMailConfig.AUTHMECHANISM_OAuth)) {
				if(getEncryptionType().equals(MEMailConfig.ENCRYPTIONTYPE_SSL)) {
					props.put("mail.imap.ssl.enable", "true");
				} else if(getEncryptionType().equals(MEMailConfig.ENCRYPTIONTYPE_TLS) || getEncryptionType().equals(MEMailConfig.ENCRYPTIONTYPE_STARTTLS)) {
					props.put("mail.imap.starttls.enable", "true");
				}
				//	
				if(getAuthMechanism().equals(MEMailConfig.AUTHMECHANISM_NTLM)) {
					props.put("mail.imap.auth.mechanisms","NTLM");
					if(domain != null)
						props.put("mail.imap.auth.ntlm.domain", domain);	
				}
			} else {
				props.put("mail.imap.starttls.enable", "true");
			    props.put("mail.imap.sasl.enable", "true");
			    props.put("mail.imap.sasl.mechanisms", "XOAUTH2");
			}
		}
		//	Unsupported
		//if(m_AuthMechanism == MEMailConfig.AUTHMECHANISM_Digest_MD5)
			
		//	Default return
		return props;
	}
	
	/**
	 *	Set EMail Parameters (Host / Port / Encryption)
	 */
	private void setEMailParameters(int eMailConfigId) {
		//	
		if(eMailConfigId != 0) {
			this.eMailConfigId = eMailConfigId;
		}
		//	Validate Config
		if(eMailConfigId == 0) {
			eMailConfigId = client.getAD_EMailConfig_ID();
		}
		MEMailConfig eMailConfig = MEMailConfig.get(ctx, eMailConfigId);
		//	Valid null
		if(eMailConfig != null) {
			host = eMailConfig.getSMTPHost();
			//	
			port = eMailConfig.getPort();
			timeout = eMailConfig.getTimeout();
			connectionTimeout = eMailConfig.getConnectionTimeout();
			protocol = eMailConfig.getProtocol();
			encryptionType = eMailConfig.getEncryptionType();
			authMechanism = eMailConfig.getAuthMechanism();
			isSmtpAuthorization = eMailConfig.isSmtpAuthorization();
			domain = eMailConfig.getLDAPDomain();
		} else {
			getPort();
			getProtocol();
			getEncryptionType();
			getAuthMechanism();
			isSmtpAuthorization = false;
		}
	}
	
	/**
	 * Configure Mail from values
	 * @param from
	 * @param to
	 * @param subject
	 * @param message
	 * @param html
	 */
	private void configureEMail(String from, String to, 
			String subject, String message, boolean html) {
		setFrom(from);
		addTo(to);
		if (subject == null || subject.length() == 0)
			setSubject(".");	//	pass validation
		else
			setSubject (subject);
		if (message != null && message.length() > 0) {
			if (html)
				setMessageHTML(subject, message);
			else
				setMessageText (message);
		}
	}
	
	/**
	 * Set Port
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	/**
	 * Get EMail Config
	 * @return
	 */
	public int gerEMailConfigId() {
		return eMailConfigId;
	}
	
	/**
	 * Get Port
	 * @return
	 */
	public int getPort() {
		//	Set Default
		if(port <= 0) {
			if(getProtocol().equals(MEMailConfig.PROTOCOL_SMTP)) {
				if(getEncryptionType().equals(MEMailConfig.ENCRYPTIONTYPE_None))
					port = MEMailConfig.DEFAULT_SMTP_PORT;
				else
					port = MEMailConfig.DEFAULT_SMTP_SSL_PORT;
			} else if(getProtocol().equals(MEMailConfig.PROTOCOL_POP3)) {
				if(getEncryptionType().equals(MEMailConfig.ENCRYPTIONTYPE_None))
					port = MEMailConfig.DEFAULT_POP3_PORT;
				else
					port = MEMailConfig.DEFAULT_POP3_SSL_PORT;
			} else if(getProtocol().equals(MEMailConfig.PROTOCOL_IMAP)) {
				if(getEncryptionType().equals(MEMailConfig.ENCRYPTIONTYPE_None))
					port = MEMailConfig.DEFAULT_IMAP_PORT;
				else
					port = MEMailConfig.DEFAULT_IMAP_SSL_PORT;
			}
		}
		//	Return
		return port;
	}
	
	/**
	 * Set Protocol
	 * @param protocol
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	/**
	 * Get Protocol
	 * @return
	 */
	public String getProtocol() {
		//	Set Default
		if(protocol == null)
			protocol = MEMailConfig.PROTOCOL_SMTP;
		//	Return
		return protocol;
	}
	
	/**
	 * Set Encryption Type
	 * @param encryptionType
	 */
	public void setEncryptionType(String encryptionType) {
		this.encryptionType = encryptionType;
	}
	
	/**
	 * Get Encryption Type
	 * @return
	 */
	public String getEncryptionType() {
		//	Valid default
		if(encryptionType == null)
			encryptionType = MEMailConfig.ENCRYPTIONTYPE_None;
		//	Return
		return encryptionType;
	}
	
	/**
	 * Set Authentication Mechanism
	 * @param authMechanism
	 */
	public void setAuthMechanism(String authMechanism) {
		this.authMechanism = authMechanism;
	}
	
	/**
	 * Get Authentication Mechanism
	 * @return
	 */
	public String getAuthMechanism() {
		//	Valid default
		if(authMechanism == null)
			authMechanism = MEMailConfig.AUTHMECHANISM_Login;
		//	Return
		return authMechanism;
	}

	/**
	 * 	Get Send Result Msg
	 *	@return msg
	 */
	public String getSentMsg()
	{
		return sentMsg;
	}	//	getSentMsg
	
	/**
	 * 	Was sending the Msg OK
	 *	@return msg == OK
	 */
	public boolean isSentOK()
	{
		return sentMsg != null && SENT_OK.equals(sentMsg);
	}	//	isSentOK
	
	/**
	 * 	Dump Message Info
	 */
	private void dumpMessage()
	{
		if (msg == null)
			return;
		try
		{
			Enumeration<?> e = msg.getAllHeaderLines ();
			while (e.hasMoreElements ())
				log.fine("- " + e.nextElement ());
		}
		catch (MessagingException ex)
		{
			log.log(Level.WARNING, msg.toString(), ex);
		}
	}	//	dumpMessage

	/**
	 * Get Is SMTP Authorization
	 * @return
	 */
	public boolean isSmtpAuthorization() {
		return isSmtpAuthorization;
	}
	
	/**
	 * 	Get the message directly
	 * 	@return mail message
	 */
	protected MimeMessage getMimeMessage()
	{
		return msg;
	}	//	getMessage

	/**
	 * 	Get Message ID or null
	 * 	@return Message ID e.g. <20030130004739.15377.qmail@web13506.mail.yahoo.com>
	 *  <25699763.1043887247538.JavaMail.jjanke@main>
	 */
	public String getMessageID()
	{
		try
		{
			if (msg != null)
				return msg.getMessageID ();
		}
		catch (MessagingException ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
		return null;
	}	//	getMessageID

	/**	Getter/Setter ********************************************************/

	/**
	 * 	Create Authenticator for User
	 * 	@param username user name
	 * 	@param password user password
	 */
	public void createAuthenticator (String username, String password) {
		isSmtpAuthorization = false;
		if(getAuthMechanism().equals(MEMailConfig.AUTHMECHANISM_OAuth)) {
			user = username;
			token = password;
			isSmtpAuthorization = true;
		} else {
			if (username == null || password == null) {
				log.warning("Ignored - " +  username + "/" + password);
				auth = null;
			} else {
			//	log.fine("setEMailUser: " + username + "/" + password);
				auth = new EMailAuthenticator (username, password);
				isSmtpAuthorization = true;
			}
		}
	}	//	createAuthenticator
	
	/**
	 * Get Store Protocol
	 * @return
	 */
	public String getStringProtocol() {
		if(getProtocol().equals(MEMailConfig.PROTOCOL_SMTP))
			return "smtp";
		else if(getProtocol().equals(MEMailConfig.PROTOCOL_IMAP))
			return "imap";
		else if(getProtocol().equals(MEMailConfig.PROTOCOL_POP3))
			return "pop3s";
		//	Default
		return "";
	}

	/**
	 *  Get Sender
	 *  @return Sender's internet address
	 */
	public InternetAddress getFrom() {
		return from;
	}   //  getFrom

	/**
	 *  Set Sender
	 *  @param newFrom Sender's email address
	 */
	public void setFrom(String newFrom) {
		if (newFrom == null) {
			valid = false;
			return;
		}
		try {
			from = new InternetAddress (newFrom, true);
			//	No Client
			if(isManual)
				return;
			//	
			if (MSysConfig.getBooleanValue("MAIL_SEND_BCC_TO_FROM", false, Env.getAD_Client_ID(Env.getCtx())));
				addBcc(newFrom);
			//	Add Copy
			String bccAddressForAllMails = MSysConfig.getValue("MAIL_SEND_BCC_TO_ADDRESS", Env.getAD_Client_ID(Env.getCtx()));
			if (bccAddressForAllMails != null && bccAddressForAllMails.length() > 0)
	 			addBcc(bccAddressForAllMails);
		} catch (Exception e) {
			log.log(Level.WARNING, newFrom + ": " + e.toString());
			valid = false;
		}
	}   //  setFrom

	/**
	 *  Add To Recipient
	 *  @param newTo Recipient's email address
	 * 	@return true if valid
	 */
	public boolean addTo(String newTo)
	{
		if (newTo == null 
				|| newTo.length() == 0) {
			valid = false;
			return false;
		}
		InternetAddress ia = null;
		try {
			ia = new InternetAddress (newTo, true);
		} catch (Exception e) {
			log.log(Level.WARNING, newTo + ": " + e.toString());
			valid = false;
			return false;
		}
		if (to == null)
			to = new ArrayList<InternetAddress>();
		to.add(ia);
		return true;
	}   //  addTo

	/**
	 *  Get Recipient
	 *  @return Recipient's internet address
	 */
	public InternetAddress getTo()
	{
		if (to == null || to.size() == 0)
			return null;
		InternetAddress ia = (InternetAddress)to.get(0);
		return ia;
	}   //  getTo

	/**
	 *  Get TO Recipients
	 *  @return Recipient's internet address
	 */
	public InternetAddress[] getTos()
	{
		if (to == null || to.size() == 0)
			return null;
		InternetAddress[] ias = new InternetAddress[to.size()];
		to.toArray(ias);
		return ias;
	}   //  getTos

	/**
	 * 	Add CC Recipient
	 * 	@param newCc EMail cc Recipient
	 * 	@return true if valid
	 */
	public boolean addCc (String newCc)
	{
		if (newCc == null || newCc.length() == 0)
			return false;
		InternetAddress ia = null;
		try
		{
			ia = new InternetAddress (newCc, true);
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, newCc + ": " + e.toString());
			return false;
		}
		if (cc == null)
			cc = new ArrayList<InternetAddress>();
		cc.add (ia);
		return true;
	}	//	addCc

	/**
	 *  Get CC Recipients
	 *  @return Recipient's internet address
	 */
	public InternetAddress[] getCcs()
	{
		if (cc == null || cc.size() == 0)
			return null;
		InternetAddress[] ias = new InternetAddress[cc.size()];
		cc.toArray(ias);
		return ias;
	}   //  getCcs

	/**
	 * 	Add BCC Recipient
	 * 	@param newBcc EMail cc Recipient
	 * 	@return true if valid
	 */
	public boolean addBcc (String newBcc)
	{
		if (newBcc == null || newBcc.length() == 0)
			return false;
		InternetAddress ia = null;
		try
		{
			ia = new InternetAddress (newBcc, true);
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, newBcc + ": " + e.getMessage());
			return false;
		}
		if (bcc == null)
			bcc = new ArrayList<InternetAddress>();
		bcc.add (ia);
		return true;
	}	//	addBcc

	/**
	 *  Get BCC Recipients
	 *  @return Recipient's internet address
	 */
	public InternetAddress[] getBccs()
	{
		if (bcc == null || bcc.size() == 0)
			return null;
		InternetAddress[] ias = new InternetAddress[bcc.size()];
		bcc.toArray(ias);
		return ias;
	}   //  getBccs

	/**
	 *  Set Reply to Address
	 *  @param newTo email address
	 * 	@return true if valid
	 */
	public boolean setReplyTo (String newTo)
	{
		if (newTo == null || newTo.length() == 0)
			return false;
		InternetAddress ia = null;
		try
		{
			ia = new InternetAddress (newTo, true);
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, newTo + ": " + e.toString());
			return false;
		}
		replyTo = ia;
		return true;
	}   //  setReplyTo

	/**
	 *  Get Reply To
	 *  @return Reply To internet address
	 */
	public InternetAddress getReplyTo()
	{
		return replyTo;
	}   //  getReplyTo

	
	/**************************************************************************
	 *  Set Subject
	 *  @param newSubject Subject
	 */
	public void setSubject(String newSubject)
	{
		if (newSubject == null || newSubject.length() == 0)
			valid = false;
		else
			subject = newSubject;
	}   //  setSubject

	/**
	 *  Get Subject
	 *  @return subject
	 */
	public String getSubject()
	{
		return subject;
	}   //  getSubject

	/**
	 *  Set Message
	 *  @param newMessage message
	 */
	public void setMessageText (String newMessage)
	{
		if (newMessage == null || newMessage.length() == 0)
			valid = false;
		else
		{
			messageText = newMessage;
			if (!messageText.endsWith("\n"))
				messageText += "\n";
		}
	}   //  setMessage

	/**
	 *  Get MIME String Message - line ending with CRLF.
	 *  @return message
	 */
	public String getMessageCRLF()
	{
		if (messageText == null)
			return "";
		char[] chars = messageText.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars.length; i++)
		{
			char c = chars[i];
			if (c == '\n')
			{
				int previous = i-1;
				if (previous >= 0 && chars[previous] == '\r')
					sb.append(c);
				else
					sb.append("\r\n");
			}
			else
				sb.append(c);
		}
	//	log.fine("IN  " + m_messageText);
	//	log.fine("OUT " + sb);

		return sb.toString();
	}   //  getMessageCRLF

	/**
	 *  Set HTML Message
	 *  @param html message
	 */
	public void setMessageHTML (String html)
	{
		if (html == null || html.length() == 0)
			valid = false;
		else
		{
			messageHTML = html;
			if (!messageHTML.endsWith("\n"))
				messageHTML += "\n";
		}
	}   //  setMessageHTML

	/**
	 *  Set HTML Message
	 *  @param subject subject repeated in message as H2
	 * 	@param message message
	 */
	public void setMessageHTML (String subject, String message) {
		this.subject = subject;
		StringBuffer sb = new StringBuffer("<HTML>\n")
			.append("<HEAD>\n")
			.append("<TITLE>\n")
			.append(subject + "\n")
			.append("</TITLE>\n")
			.append("</HEAD>\n");
		sb.append("<BODY>\n")
			.append("<H2>" + subject + "</H2>" + "\n")
			.append(message)
			.append("\n")
			.append("</BODY>\n");
		sb.append("</HTML>\n");
		messageHTML = sb.toString();
	}   //  setMessageHTML

	/**
	 *  Get HTML Message
	 *  @return message
	 */
	public String getMessageHTML()
	{
		return messageHTML;
	}   //  getMessageHTML

	/**
	 *	Add file Attachment
	 * 	@param file file to attach
	 */
	public void addAttachment (File file)
	{
		if (file == null)
			return;
		if (attachments == null)
			attachments = new ArrayList<Object>();
		attachments.add(file);
	}	//	addAttachment
	
	/**
	 * Add a collection of attachments
	 * @param files collection of files
	 */
	public void addAttachments(Collection<File> files)
	{
		if (files == null || files.size() == 0)
			return;
		for (File f : files) {
			addAttachment(f);
		}
	}

	/**
	 *	Add url based file Attachment
	 * 	@param uri url content to attach
	 */
	public void addAttachment (URI url)
	{
		if (url == null)
			return;
		if (attachments == null)
			attachments = new ArrayList<Object>();
		attachments.add(url);
	}	//	addAttachment

	/**
	 *	Add attachment.
	 *  (converted to ByteArrayDataSource)
	 * 	@param data data
	 * 	@param type MIME type
	 * 	@param name name of attachment
	 */
	public void addAttachment (byte[] data, String type, String name)
	{
		ByteArrayDataSource byteArray = new ByteArrayDataSource (data, type).setName(name);
		addAttachment (byteArray);
	}	//	addAttachment

	/**
	 *	Add arbitrary Attachment
	 * 	@param dataSource content to attach
	 */
	public void addAttachment (DataSource dataSource)
	{
		if (dataSource == null)
			return;
		if (attachments == null)
			attachments = new ArrayList<Object>();
		attachments.add(dataSource);
	}	//	addAttachment

	/**
	 *	Set the message content
	 * 	@throws MessagingException
	 * 	@throws IOException
	 */
	private void setContent ()
		throws MessagingException, IOException
	{
		//	Local Character Set
		String charSetName = Ini.getCharset().name();
		if (charSetName == null || charSetName.length() == 0)
			charSetName = "iso-8859-1";	// WebEnv.ENCODING - alternative iso-8859-1
		//
		msg.setSubject (getSubject(), charSetName);

		//	Simple Message
		if (attachments == null || attachments.size() == 0)
		{
			if (messageHTML == null || messageHTML.length () == 0)
				msg.setText (getMessageCRLF(), charSetName);
			else
				msg.setDataHandler (new DataHandler
					(new ByteArrayDataSource (messageHTML, charSetName, "text/html")));
			//
			log.fine("(simple) " + getSubject());
		}
		else	//	Multi part message	***************************************
		{
			//	First Part - Message
			MimeBodyPart mbp_1 = new MimeBodyPart();
			mbp_1.setText("");
			if (messageHTML == null || messageHTML.length () == 0)
				mbp_1.setText (getMessageCRLF(), charSetName);
			else
				mbp_1.setDataHandler (new DataHandler
					(new ByteArrayDataSource (messageHTML, charSetName, "text/html")));

			// Create Multipart and its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp_1);
			log.fine("(multi) " + getSubject() + " - " + mbp_1);

			//	for all attachments
			for (int i = 0; i < attachments.size(); i++)
			{
				Object attachment = attachments.get(i);
				DataSource ds = null;
				if (attachment instanceof File)
				{
					File file = (File)attachment;
					if (file.exists())
						ds = new FileDataSource (file);
					else
					{
						log.log(Level.WARNING, "File does not exist: " + file);
						continue;
					}
				}
				else if (attachment instanceof URI)
				{
					URI url = (URI)attachment;
					ds = new URLDataSource (url.toURL());
				}
				else if (attachment instanceof DataSource)
					ds = (DataSource)attachment;
				else
				{
					log.log(Level.WARNING, "Attachement type unknown: " + attachment);
					continue;
				}
				//	Attachment Part
				MimeBodyPart mbp_2 = new MimeBodyPart();
				mbp_2.setDataHandler(new DataHandler(ds));
				mbp_2.setFileName(ds.getName());
				log.fine("Added Attachment " + ds.getName() + " - " + mbp_2);
				mp.addBodyPart(mbp_2);
			}

			//	Add to Message
			msg.setContent(mp);
		}	//	multi=part
	}	//	setContent

	
	/**************************************************************************
	 *  Set SMTP Host or address
	 *  @param newSmtpHost Mail server
	 */
	public void setSmtpHost(String newSmtpHost)
	{
		if (newSmtpHost == null || newSmtpHost.length() == 0)
			valid = false;
		else
			host = newSmtpHost;
	}   //  setSMTPHost

	/**
	 *  Get Mail Server name or address
	 *  @return mail server
	 */
	public String getSmtpHost()
	{
		return host;
	}   //  getSmtpHosr

	/**
	 *  Is Info valid to send EMail
	 *  @return true if email is valid and can be sent
	 */
	public boolean isValid()
	{
		return valid;
	}   //  isValid

	/**
	 *  Re-Check Info if valid to send EMail
	 * 	@param recheck if true check main variables
	 *  @return true if email is valid and can be sent
	 */
	public boolean isValid (boolean recheck)
	{
		if (!recheck)
			return valid;
			
		//  From
		if (from == null 
			|| from.getAddress().length() == 0 
			|| from.getAddress().indexOf(' ') != -1)
		{
			log.warning("From is invalid=" + from);
			return false;
		}
		//	To
		InternetAddress[] ias = getTos();
		if (ias == null)
		{
			log.warning("No To");
			return false;
		}
		for (int i = 0; i < ias.length; i++)
		{
			if (ias[i] == null 
				|| ias[i].getAddress().length() == 0
				|| ias[i].getAddress().indexOf(' ') != -1)
			{
				log.warning("To(" + i + ") is invalid=" + ias[i]);
				return false;
			}
		}

		//	Host
		if (host == null || host.length() == 0)
		{
			log.warning("SMTP Host is invalid" + host);
			return false;
		}
		
		//	Subject
		if (subject == null || subject.length() == 0)
		{
			log.warning("Subject is invalid=" + subject);
			return false;
		}
		return true;
	}   //  isValid

	/**
	 * @return attachments array or empty array. This method will never return null.
	 */
	public Object[] getAttachments()
	{
		if (attachments == null)
			return new Object[]{};
		return attachments.toArray();
	}

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("EMail[");
		sb.append("From:").append(from)
			.append(",To:").append(getTo())
			.append(",Subject=").append(getSubject())
			.append ("]");
		return sb.toString ();
	}	//	toString

	/**************************************************************************
	 *  Test.
	 *  java -cp CTools.jar;CClient.jar org.compiere.util.EMail main info@adempiere.org jjanke@adempiere.org "My Subject"  "My Message"
	 * 	--
	 * 	If you get SendFailedException: 550 5.7.1 Unable to relay for ..
	 * 	Check:
	 * 	- Does the SMTP server allow you to relay
	 *    (Exchange: SMTP server - Access)
	 *  - Did you authenticate (setEmailUser)
	 *  @param args Array of arguments
	 */
	public static void main (String[] args)
	{
		org.compiere.Adempiere.startup(true);

		if (args.length != 5)
		{
			System.out.println("Parameters: smtpHost from to subject message");
			System.out.println("Example: java org.compiere.util.EMail mail.acme.com joe@acme.com sue@acme.com HiThere CheersJoe");
			System.exit(1);
		}
		//	FR [ 402 ]
		EMail email = new EMail(args[0], args[1], args[2], args[3], args[4], false);
		email.send();
	}   //  main

}	//	EMail
