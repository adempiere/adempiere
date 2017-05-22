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
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
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
	 * @param p_AD_EMailConfig_ID
	 * @param from Sender's EMail address
	 * @param to Recipient EMail address
	 * @param subject Subject of message
	 * @param message The message
	 * @param html html email
	 */
	public EMail (MClient client, int p_AD_EMailConfig_ID, String from, String to, 
		String subject, String message, boolean html) {
		m_ctx = client.getCtx();
		m_IsManual = false;
		//	FR [ 402 ]
		setEMailParameters(p_AD_EMailConfig_ID);
		//	Configure EMail
		configureEMail(from, to, subject, message, html);
		m_valid = isValid (true);
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
	public EMail(String smtpHost, int smtpPort, String protocol, String encryptionType, String authMechanism, String from, String to, 
			String subject, String message, boolean html) {
		setSmtpHost(smtpHost);
		setPort(smtpPort);
		setProtocol(protocol);
		setEncryptionType(encryptionType);
		setAuthMechanism(authMechanism);
		m_IsSmtpAuthorization = false;
		m_IsManual = true;
		//	
		if(from == null
				&& to == null)
			return;
		//	For send
		configureEMail(from, to, subject, message, html);
		//	Is Valid?
		m_valid = isValid (true);
	}
	
	/**
	 * Standard Constructor, test receipt
	 * @param smtpHost
	 * @param smtpPort
	 * @param protocol
	 * @param encryptionType
	 * @param authMechanism
	 */
	public EMail(String smtpHost, int smtpPort, String protocol, String encryptionType, String authMechanism) {
		this(smtpHost, smtpPort, protocol, encryptionType, authMechanism, null, null, null, null, false);
	}	
	
	/**
	 * Constructor used by receipt server
	 * @param client
	 * @param p_AD_EMailConfig_ID
	 */
	public EMail (MClient client, int p_AD_EMailConfig_ID) {
		//	FR [ 402 ]
		setEMailParameters(p_AD_EMailConfig_ID);
	}
	
	/**
	 * Constructor used by receipt server, get configuration from client
	 * @param client
	 */
	public EMail (MClient client) {
		this(client, client.getAD_EMailConfig_ID());
	}

	/**	From Address				*/
	private InternetAddress     m_from;
	/** To Address					*/
	private ArrayList<InternetAddress>	m_to;
	/** CC Addresses				*/
	private ArrayList<InternetAddress>	m_cc;
	/** BCC Addresses				*/
	private ArrayList<InternetAddress>	m_bcc;
	/**	Reply To Address			*/
	private InternetAddress		m_replyTo;
	/**	Mail Subject				*/
	private String  			m_subject;
	/** Mail Plain Message			*/
	private String  			m_messageText;
	/** Mail HTML Message			*/
	private String  			m_messageHTML;
	/**	Mail SMTP Server			*/
	private String  			m_Host;
	/**	Mail SMTP Server Port		*/
	private int  				m_Port = 0;
	/**	Protocol					*/
	private String 				m_Protocol = null;
	/**	SMTP Encryption Type		*/
	private String 				m_EncryptionType = null;
	/**	SMTP Authentication Mechanism*/
	private String 				m_AuthMechanism = null;
	/**	SMTP Authentication			*/
	private boolean 			m_IsSmtpAuthorization = false;
	/**	Domain						*/
	private String 				m_Domain = null;
	/**	Timeout						*/
	private long				m_Timeout = 0;
	/**	Connection Timeout			*/
	private long				m_ConnectionTimeout = 0;
	/**	Custom EMail Configuration	*/
	private int					m_AD_EMailConfig_ID = 0;
	/**	Attachments					*/
	private ArrayList<Object>	m_attachments;
	/**	UserName and Password		*/
	private EMailAuthenticator	m_auth = null;
	/**	Message						*/
	private SMTPMessage 		m_msg = null;
	/**	User						*/
	private String				m_user = null;
	/**	Token						*/
	private String				m_token = "";
	/** Context - may be null		*/
	private Properties			m_ctx;
	/**	Is Manual					*/
	private boolean				m_IsManual = false;

	/**	Info Valid					*/
	private boolean 			m_valid = false;
	/** Send result Message			*/
	private String				m_sentMsg = null;

	/**	Mail Sent OK Status				*/
	public static final String      SENT_OK = "OK";

	/**	Logger							*/
	protected static CLogger		log = CLogger.getCLogger (EMail.class);

	/**
	 * Get Session from email config
	 * @return
	 */
	public Session getSession() {
		//	FR [ 402 ]
		Session session = null;
		if(getAuthMechanism().equals(MEMailConfig.AUTHMECHANISM_OAuth)) {
			//	Add initialization
			Security.addProvider(new EMailOAuth2Provider());
			session = Session.getInstance(getEMailProperties());
		} else {
			session = Session.getInstance(getEMailProperties(), m_auth);
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
		Transport transport = null;
		if(getAuthMechanism().equals(MEMailConfig.AUTHMECHANISM_OAuth)) {
			transport = new SMTPTransport(session, null);
			transport.connect(getSmtpHost(), getPort(), m_user, "");
		} else {
			transport = session.getTransport("smtp");
			transport.connect();
		}
		//	Log
		log.fine("transport=" + transport);
		log.fine("transport connected");
		//	Default Return
		return transport;
	}
	
	/**
	 *	Send Mail direct
	 *	@return OK or error message
	 */
	public String send() {
		log.info("(" + m_Host + ") " + m_from + " -> " + m_to);
		m_sentMsg = null;
		//
		if (!isValid(true))
		{
			m_sentMsg = "Invalid Data";
			return m_sentMsg;
		}
		//	FR [ 402 ]
		Session session = null;
		try {
			session = getSession();
		} catch (SecurityException se) {
			log.log(Level.WARNING, "Auth=" + m_auth + " - " + se.toString());
			m_sentMsg = se.toString();
			return se.toString();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Auth=" + m_auth, e);
			m_sentMsg = e.toString();
			return e.toString();
		}

		try
		{
			m_msg = new SMTPMessage(session);
			//	Addresses
			m_msg.setFrom(m_from);
			InternetAddress[] rec = getTos();
			if (rec.length == 1)
				m_msg.setRecipient (Message.RecipientType.TO, rec[0]);
			else
				m_msg.setRecipients (Message.RecipientType.TO, rec);
			rec = getCcs();
			if (rec != null && rec.length > 0)
				m_msg.setRecipients (Message.RecipientType.CC, rec);
			rec = getBccs();
			if (rec != null && rec.length > 0)
				m_msg.setRecipients (Message.RecipientType.BCC, rec);
			if (m_replyTo != null)
				m_msg.setReplyTo(new Address[] {m_replyTo});
			//
			m_msg.setSentDate(new java.util.Date());
			m_msg.setHeader("Comments", "AdempiereMail");
			//	SMTP specifics
			m_msg.setAllow8bitMIME(true);
			//	Send notification on Failure & Success - no way to set envid in Java yet
			//	Bounce only header
			m_msg.setReturnOption (SMTPMessage.RETURN_HDRS);
			//
			setContent();
			m_msg.saveChanges();
			log.fine("message =" + m_msg);
			//
			getTransport(session);
			Transport.send(m_msg);
			log.fine("Success - MessageID=" + m_msg.getMessageID());
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
					sb.append(" - Invalid Username/Password - " + m_auth);
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
							if (m_ctx != null)
								cc = m_ctx.getProperty("#AD_Client_ID");
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
			m_sentMsg = sb.toString();
			return sb.toString();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
			m_sentMsg = e.getLocalizedMessage();
			return e.getLocalizedMessage();
		}
		//
		if (CLogMgt.isLevelFinest())
			dumpMessage();
		m_sentMsg = SENT_OK;
		return m_sentMsg;
	}	//	send
	
	/**
	 * Get EMail Properties from type
	 * @return
	 */
	public Properties getEMailProperties() {
		//
		Properties props = System.getProperties();
		//	For Debug
		if (CLogMgt.isLevelFinest())
			props.put("mail.debug", "true");
		//	Timeout
		if(m_Timeout > 0)
			props.put("mail.smtp.timeout", String.valueOf(m_Timeout));
		//	Connection Timeout
		if(m_Timeout > 0)
			props.put("mail.smtp.connectiontimeout", String.valueOf(m_ConnectionTimeout));
		//	Protocol
		if(m_Protocol.equals(MEMailConfig.PROTOCOL_SMTP)) {
			//	
			if (m_IsSmtpAuthorization && m_auth != null)		//	createAuthenticator was called
				props.put("mail.smtp.auth", "true");
			props.put("mail.store.protocol", "smtp");
			props.put("mail.transport.protocol", "smtp");
			//	Encryption Type
			if(!getAuthMechanism().equals(MEMailConfig.AUTHMECHANISM_OAuth)) {
				if(getEncryptionType().equals(MEMailConfig.ENCRYPTIONTYPE_SSL))
					props.put("mail.smtp.ssl.enable", "true");
				else if(getEncryptionType().equals(MEMailConfig.ENCRYPTIONTYPE_TLS))
					props.put("mail.smtp.starttls.enable", "true");
			}
		}
		//	For oAuth2
		if(getAuthMechanism().equals(MEMailConfig.AUTHMECHANISM_OAuth)) {
			props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.sasl.enable", "true");
		    props.put("mail.smtp.sasl.mechanisms", "XOAUTH2");;
		    props.put("mail.imaps.sasl.mechanisms.oauth2.oauthToken", m_token);
		} else {
			//	Set Host
			props.put("mail.host", m_Host);
			//	Set Port
			props.put("mail.smtp.port", String.valueOf(getPort()));
			//	Add Authentication Mechanism
			if(getAuthMechanism().equals(MEMailConfig.AUTHMECHANISM_NTLM)) {
				props.put("mail.smtp.auth.mechanisms","NTLM");
				if(m_Domain != null)
					props.put("mail.smtp.auth.ntlm.domain", m_Domain);	
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
	private void setEMailParameters(int p_AD_EMailConfig_ID) {
		//	
		if(p_AD_EMailConfig_ID != 0)
			m_AD_EMailConfig_ID = p_AD_EMailConfig_ID;
		//	Validate Config
		if(m_AD_EMailConfig_ID == 0) {
			MClient client = MClient.get(m_ctx);
			m_AD_EMailConfig_ID = client.getAD_EMailConfig_ID();
		}
		MEMailConfig eMailConfig = MEMailConfig.get(m_ctx, m_AD_EMailConfig_ID);
		//	Valid null
		if(eMailConfig != null) {
			m_Host = eMailConfig.getSMTPHost();
			//	
			m_Port = eMailConfig.getPort();
			m_Timeout = eMailConfig.getTimeout();
			m_ConnectionTimeout = eMailConfig.getConnectionTimeout();
			m_Protocol = eMailConfig.getProtocol();
			m_EncryptionType = eMailConfig.getEncryptionType();
			m_AuthMechanism = eMailConfig.getAuthMechanism();
			m_IsSmtpAuthorization = eMailConfig.isSmtpAuthorization();
			m_Domain = eMailConfig.getLDAPDomain();
		} else {
			getPort();
			getProtocol();
			getEncryptionType();
			getAuthMechanism();
			m_IsSmtpAuthorization = false;
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
		m_Port = port;
	}
	
	/**
	 * Get Port
	 * @return
	 */
	public int getPort() {
		//	Set Default
		if(m_Port <= 0) {
			if(getProtocol().equals(MEMailConfig.PROTOCOL_SMTP)) {
				if(getEncryptionType().equals(MEMailConfig.ENCRYPTIONTYPE_None))
					m_Port = MEMailConfig.DEFAULT_SMTP_PORT;
				else
					m_Port = MEMailConfig.DEFAULT_SMTP_SSL_PORT;
			} else if(getProtocol().equals(MEMailConfig.PROTOCOL_POP3)) {
				if(getEncryptionType().equals(MEMailConfig.ENCRYPTIONTYPE_None))
					m_Port = MEMailConfig.DEFAULT_POP3_PORT;
				else
					m_Port = MEMailConfig.DEFAULT_POP3_SSL_PORT;
			} else if(getProtocol().equals(MEMailConfig.PROTOCOL_IMAP)) {
				if(getEncryptionType().equals(MEMailConfig.ENCRYPTIONTYPE_None))
					m_Port = MEMailConfig.DEFAULT_IMAP_PORT;
				else
					m_Port = MEMailConfig.DEFAULT_IMAP_SSL_PORT;
			}
		}
		//	Return
		return m_Port;
	}
	
	/**
	 * Set Protocol
	 * @param protocol
	 */
	public void setProtocol(String protocol) {
		m_Protocol = protocol;
	}
	
	/**
	 * Get Protocol
	 * @return
	 */
	public String getProtocol() {
		//	Set Default
		if(m_Protocol == null)
			m_Protocol = MEMailConfig.PROTOCOL_SMTP;
		//	Return
		return m_Protocol;
	}
	
	/**
	 * Set Encryption Type
	 * @param encryptionType
	 */
	public void setEncryptionType(String encryptionType) {
		m_EncryptionType = encryptionType;
	}
	
	/**
	 * Get Encryption Type
	 * @return
	 */
	public String getEncryptionType() {
		//	Valid default
		if(m_EncryptionType == null)
			m_EncryptionType = MEMailConfig.ENCRYPTIONTYPE_None;
		//	Return
		return m_EncryptionType;
	}
	
	/**
	 * Set Authentication Mechanism
	 * @param authMechanism
	 */
	public void setAuthMechanism(String authMechanism) {
		m_AuthMechanism = authMechanism;
	}
	
	/**
	 * Get Authentication Mechanism
	 * @return
	 */
	public String getAuthMechanism() {
		//	Valid default
		if(m_AuthMechanism == null)
			m_AuthMechanism = MEMailConfig.AUTHMECHANISM_Login;
		//	Return
		return m_AuthMechanism;
	}

	/**
	 * 	Get Send Result Msg
	 *	@return msg
	 */
	public String getSentMsg()
	{
		return m_sentMsg;
	}	//	getSentMsg
	
	/**
	 * 	Was sending the Msg OK
	 *	@return msg == OK
	 */
	public boolean isSentOK()
	{
		return m_sentMsg != null && SENT_OK.equals(m_sentMsg);
	}	//	isSentOK
	
	/**
	 * 	Dump Message Info
	 */
	private void dumpMessage()
	{
		if (m_msg == null)
			return;
		try
		{
			Enumeration<?> e = m_msg.getAllHeaderLines ();
			while (e.hasMoreElements ())
				log.fine("- " + e.nextElement ());
		}
		catch (MessagingException ex)
		{
			log.log(Level.WARNING, m_msg.toString(), ex);
		}
	}	//	dumpMessage

	/**
	 * 	Get the message directly
	 * 	@return mail message
	 */
	protected MimeMessage getMimeMessage()
	{
		return m_msg;
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
			if (m_msg != null)
				return m_msg.getMessageID ();
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
		m_IsSmtpAuthorization = false;
		if(getAuthMechanism().equals(MEMailConfig.AUTHMECHANISM_OAuth)) {
			m_user = username;
			m_token = password;
			m_IsSmtpAuthorization = true;
		} else {
			if (username == null || password == null) {
				log.warning("Ignored - " +  username + "/" + password);
				m_auth = null;
			} else {
			//	log.fine("setEMailUser: " + username + "/" + password);
				m_auth = new EMailAuthenticator (username, password);
				m_IsSmtpAuthorization = true;
			}
		}
	}	//	createAuthenticator
	
	/**
	 * Get Store Protocol
	 * @return
	 */
	public String getStoreProtocol() {
		if(getProtocol().equals(MEMailConfig.PROTOCOL_IMAP))
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
	public InternetAddress getFrom()
	{
		return m_from;
	}   //  getFrom

	/**
	 *  Set Sender
	 *  @param newFrom Sender's email address
	 */
	public void setFrom(String newFrom) {
		if (newFrom == null) {
			m_valid = false;
			return;
		}
		try {
			m_from = new InternetAddress (newFrom, true);
			//	No Client
			if(m_IsManual)
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
			m_valid = false;
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
			m_valid = false;
			return false;
		}
		InternetAddress ia = null;
		try {
			ia = new InternetAddress (newTo, true);
		} catch (Exception e) {
			log.log(Level.WARNING, newTo + ": " + e.toString());
			m_valid = false;
			return false;
		}
		if (m_to == null)
			m_to = new ArrayList<InternetAddress>();
		m_to.add(ia);
		return true;
	}   //  addTo

	/**
	 *  Get Recipient
	 *  @return Recipient's internet address
	 */
	public InternetAddress getTo()
	{
		if (m_to == null || m_to.size() == 0)
			return null;
		InternetAddress ia = (InternetAddress)m_to.get(0);
		return ia;
	}   //  getTo

	/**
	 *  Get TO Recipients
	 *  @return Recipient's internet address
	 */
	public InternetAddress[] getTos()
	{
		if (m_to == null || m_to.size() == 0)
			return null;
		InternetAddress[] ias = new InternetAddress[m_to.size()];
		m_to.toArray(ias);
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
		if (m_cc == null)
			m_cc = new ArrayList<InternetAddress>();
		m_cc.add (ia);
		return true;
	}	//	addCc

	/**
	 *  Get CC Recipients
	 *  @return Recipient's internet address
	 */
	public InternetAddress[] getCcs()
	{
		if (m_cc == null || m_cc.size() == 0)
			return null;
		InternetAddress[] ias = new InternetAddress[m_cc.size()];
		m_cc.toArray(ias);
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
		if (m_bcc == null)
			m_bcc = new ArrayList<InternetAddress>();
		m_bcc.add (ia);
		return true;
	}	//	addBcc

	/**
	 *  Get BCC Recipients
	 *  @return Recipient's internet address
	 */
	public InternetAddress[] getBccs()
	{
		if (m_bcc == null || m_bcc.size() == 0)
			return null;
		InternetAddress[] ias = new InternetAddress[m_bcc.size()];
		m_bcc.toArray(ias);
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
		m_replyTo = ia;
		return true;
	}   //  setReplyTo

	/**
	 *  Get Reply To
	 *  @return Reply To internet address
	 */
	public InternetAddress getReplyTo()
	{
		return m_replyTo;
	}   //  getReplyTo

	
	/**************************************************************************
	 *  Set Subject
	 *  @param newSubject Subject
	 */
	public void setSubject(String newSubject)
	{
		if (newSubject == null || newSubject.length() == 0)
			m_valid = false;
		else
			m_subject = newSubject;
	}   //  setSubject

	/**
	 *  Get Subject
	 *  @return subject
	 */
	public String getSubject()
	{
		return m_subject;
	}   //  getSubject

	/**
	 *  Set Message
	 *  @param newMessage message
	 */
	public void setMessageText (String newMessage)
	{
		if (newMessage == null || newMessage.length() == 0)
			m_valid = false;
		else
		{
			m_messageText = newMessage;
			if (!m_messageText.endsWith("\n"))
				m_messageText += "\n";
		}
	}   //  setMessage

	/**
	 *  Get MIME String Message - line ending with CRLF.
	 *  @return message
	 */
	public String getMessageCRLF()
	{
		if (m_messageText == null)
			return "";
		char[] chars = m_messageText.toCharArray();
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
			m_valid = false;
		else
		{
			m_messageHTML = html;
			if (!m_messageHTML.endsWith("\n"))
				m_messageHTML += "\n";
		}
	}   //  setMessageHTML

	/**
	 *  Set HTML Message
	 *  @param subject subject repeated in message as H2
	 * 	@param message message
	 */
	public void setMessageHTML (String subject, String message)
	{
		m_subject = subject;
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
		m_messageHTML = sb.toString();
	}   //  setMessageHTML

	/**
	 *  Get HTML Message
	 *  @return message
	 */
	public String getMessageHTML()
	{
		return m_messageHTML;
	}   //  getMessageHTML

	/**
	 *	Add file Attachment
	 * 	@param file file to attach
	 */
	public void addAttachment (File file)
	{
		if (file == null)
			return;
		if (m_attachments == null)
			m_attachments = new ArrayList<Object>();
		m_attachments.add(file);
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
		if (m_attachments == null)
			m_attachments = new ArrayList<Object>();
		m_attachments.add(url);
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
		if (m_attachments == null)
			m_attachments = new ArrayList<Object>();
		m_attachments.add(dataSource);
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
		m_msg.setSubject (getSubject(), charSetName);

		//	Simple Message
		if (m_attachments == null || m_attachments.size() == 0)
		{
			if (m_messageHTML == null || m_messageHTML.length () == 0)
				m_msg.setText (getMessageCRLF(), charSetName);
			else
				m_msg.setDataHandler (new DataHandler
					(new ByteArrayDataSource (m_messageHTML, charSetName, "text/html")));
			//
			log.fine("(simple) " + getSubject());
		}
		else	//	Multi part message	***************************************
		{
			//	First Part - Message
			MimeBodyPart mbp_1 = new MimeBodyPart();
			mbp_1.setText("");
			if (m_messageHTML == null || m_messageHTML.length () == 0)
				mbp_1.setText (getMessageCRLF(), charSetName);
			else
				mbp_1.setDataHandler (new DataHandler
					(new ByteArrayDataSource (m_messageHTML, charSetName, "text/html")));

			// Create Multipart and its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp_1);
			log.fine("(multi) " + getSubject() + " - " + mbp_1);

			//	for all attachments
			for (int i = 0; i < m_attachments.size(); i++)
			{
				Object attachment = m_attachments.get(i);
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
			m_msg.setContent(mp);
		}	//	multi=part
	}	//	setContent

	
	/**************************************************************************
	 *  Set SMTP Host or address
	 *  @param newSmtpHost Mail server
	 */
	public void setSmtpHost(String newSmtpHost)
	{
		if (newSmtpHost == null || newSmtpHost.length() == 0)
			m_valid = false;
		else
			m_Host = newSmtpHost;
	}   //  setSMTPHost

	/**
	 *  Get Mail Server name or address
	 *  @return mail server
	 */
	public String getSmtpHost()
	{
		return m_Host;
	}   //  getSmtpHosr

	/**
	 *  Is Info valid to send EMail
	 *  @return true if email is valid and can be sent
	 */
	public boolean isValid()
	{
		return m_valid;
	}   //  isValid

	/**
	 *  Re-Check Info if valid to send EMail
	 * 	@param recheck if true check main variables
	 *  @return true if email is valid and can be sent
	 */
	public boolean isValid (boolean recheck)
	{
		if (!recheck)
			return m_valid;
			
		//  From
		if (m_from == null 
			|| m_from.getAddress().length() == 0 
			|| m_from.getAddress().indexOf(' ') != -1)
		{
			log.warning("From is invalid=" + m_from);
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
		if (m_Host == null || m_Host.length() == 0)
		{
			log.warning("SMTP Host is invalid" + m_Host);
			return false;
		}
		
		//	Subject
		if (m_subject == null || m_subject.length() == 0)
		{
			log.warning("Subject is invalid=" + m_subject);
			return false;
		}
		return true;
	}   //  isValid

	/**
	 * @return attachments array or empty array. This method will never return null.
	 */
	public Object[] getAttachments()
	{
		if (m_attachments == null)
			return new Object[]{};
		return m_attachments.toArray();
	}

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("EMail[");
		sb.append("From:").append(m_from)
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
