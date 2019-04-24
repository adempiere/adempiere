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
package org.spin.util.support.mq;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.adempiere.exceptions.AdempiereException;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.spin.model.MADAppRegistration;

/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/2109">
 * 		@see FR [ 2109 ] Add App Registration ADempiere</a>
 */
public class ActiveMQ extends AbstractMessageQueue {
	
	public ActiveMQ() {
		super();
	}
	
	/** Static Logger					*/
	private CLogger log = CLogger.getCLogger (ActiveMQ.class);

	@Override
	public void publish(String channel, IMessageQueue payload) throws Exception {
		if(Util.isEmpty(userName)) {
			throw new AdempiereException("@SecretToken@ @NotFound@");
		}
		if(connection == null) {
			throw new AdempiereException("@Connection@ @NotFound@");
		}
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(channel);	
		MessageProducer producer = session.createProducer(destination);
		Message message = null;
		//	Validate Type
		if(payload.getType() == IMessageQueue.TEXT) {
			message = session.createTextMessage(payload.getMessageAsText());
			log.config("Message Type (Text)");
		} else if(payload.getType() == IMessageQueue.FILE) {
			InputStream inputStream = payload.getMessageAsInputStream();
			BytesMessage bytesMessage = session.createBytesMessage();
			byte[] array = new byte[inputStream.available()];
			inputStream.read(array);
			bytesMessage.writeBytes(array);
			if(!Util.isEmpty(payload.getFileName())) {
				bytesMessage.setStringProperty("FileName", payload.getFileName());
			}
			message = bytesMessage;
			log.config("Message Type (File)");
		}
		//	Validate
		if(message != null) {
			producer.send(message);
			log.config("Message sent");
		}
	}

	@Override
	public void subscribe(String channel) throws Exception {
		if(Util.isEmpty(userName)) {
			throw new AdempiereException("@AD_User_ID@ @NotFound@");
		}
		if(connection == null) {
			throw new AdempiereException("@Connection@ @NotFound@");
		}
	}
	
	/**	Registration Id	*/
	private int registrationId = 0;
	/**	Secret Token	*/
	private String userName = null;
	/**	Password	*/
	private String password = null;
	/**	URL	*/
	private String url = null;
	/**	Connection */
	private Connection connection;

	@Override
	public void setAppRegistrationId(int registrationId) {
		this.registrationId = registrationId;
		MADAppRegistration registration = MADAppRegistration.getById(Env.getCtx(), getAppRegistrationId(), null);
		String userName = registration.getParameterValue("UserName");
		String password = registration.getParameterValue("Password");
		String url = registration.getParameterValue("URL");
		if(Util.isEmpty(userName)) {
			throw new AdempiereException("@AD_User_ID@ @NotFound@");
		}
		if(Util.isEmpty(password)) {
			throw new AdempiereException("@Password@ @NotFound@");
		}
		if(Util.isEmpty(url)) {
			throw new AdempiereException("@URL@ @NotFound@");
		}
		//	Set values
		setUserName(userName);
		setPassword(password);
		setUrl(url);
	}

	@Override
	public int getAppRegistrationId() {
		return registrationId;
	}

	@Override
	public void connect() throws Exception {
		if(connection == null) {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, password, url);
			connection = connectionFactory.createConnection();
		}
		//	
		connection.start();
	}

	@Override
	public void disconnect() throws Exception {
		if(connection == null) {
			throw new AdempiereException("@Connection@ @NotFound@");
		}
		//	
		connection.close();
		connection = null;
	}
	
	@Override
	public String testConnection() {
		String message = null;
		try {
			MADAppRegistration registration = MADAppRegistration.getById(Env.getCtx(), getAppRegistrationId(), null);
			userName = registration.getParameterValue("UserName");
			password = registration.getParameterValue("Password");
			url = registration.getParameterValue("URL");
			String channel = registration.getParameterValue("TargetChannel");
			if(Util.isEmpty(userName)) {
				return "@AD_User_ID@ @NotFound@";
			}
			if(Util.isEmpty(password)) {
				return "@Password@ @NotFound@";
			}
			if(Util.isEmpty(url)) {
				return "@URL@ @NotFound@";
			}
			if(Util.isEmpty(channel)) {
				return "@Channel@ @NotFound@";
			}
			connect();
			publish(channel, new IMessageQueue() {
				
				@Override
				public int getType() {
					return IMessageQueue.FILE;
				}
				
				@Override
				public String getMessageAsText() {
					return "Hi";
				}
				
				@Override
				public InputStream getMessageAsInputStream() {
					try {
						InputStream inputStream = new FileInputStream("/tmp/Factura_Nacional_1000158.pdf");
						return inputStream;
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
				
				@Override
				public Object getMessage() {
					return getMessageAsText();
				}

				@Override
				public String getFileName() {
					return "Test.pdf";
				}
			});
			message = "Ok";
		} catch (Exception e) {
			message = e.getLocalizedMessage();
		}
		return message;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
