/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Trifon Trifonov.                                     * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Trifon Trifonov (trifonnt@users.sourceforge.net)                *
 *  - Antonio Cañaveral (antonio.canaveral@e-evolution.com)			  *
 *                                                                    *
 * Sponsors:                                                          *
 *  - E-evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/

package org.adempiere.server.rpl.imp;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.adempiere.process.rpl.imp.ImportHelper;
import org.adempiere.process.rpl.XMLHelper;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.compiere.server.ReplicationProcessor;
import org.compiere.util.CLogger;
import org.compiere.model.MIMPProcessorLog;
import org.w3c.dom.Document;

/**
 * Listen for JMS Messages
 * @author Trifon N. Trifonov
 * @author Antonio Cañaveral, e-Evolution
 * 				<li>[ 2194986 ] Already connected ClientID issue.
 * 				<li>http://sourceforge.net/tracker/index.php?func=detail&aid=2194986&group_id=176962&atid=879332
 */
public class TopicListener implements MessageListener {
	
	/**
	 * Connection to JMS Server
	 */
	private Connection conn;
	
	/**
	 * JMS Session
	 */
	private Session session;
	
	/**
	 * JMS Topic
	 */
	private Topic topic;
	
//	private String url="tcp://localhost:61616?jms.dispatchAsync=true&jms.useAsyncSend=true&jms.optimizeAcknowledge=true&jms.disableTimeStampsByDefault=true&jms.optimizedMessageDispatch=true&wireFormat.cacheEnabled=false&wireFormat.tightEncodingEnabled=false";
	private String url="tcp://localhost:61616";
	
	/**
	 * host where JMS server is running
	 */
	private String host = "localhost";
	
	/**
	 * port of JMS Server
	 */
	private int port = 61616;
	
	/**
	 * Network protocol
	 */
	private String protocol = "tcp";
	
	/**
	 * Context
	 */
	private Properties ctx = null;
	
	/**
	 * Transaction name
	 */
	private String trxName = null;
	
	/**
	 * Topic Name
	 */
	private String topicName = null;
	
	/**
	 * Replication processor
	 */
	private ReplicationProcessor replicationProcessor = null;
	
	/**	Logger	*/
	protected CLogger	log = CLogger.getCLogger (TopicListener.class);
	
	/** 
	 * Is Durable Subscription
	 */
	private boolean isDurableSubscription = false;
	
	/**
	 * Subscription Name
	 */
	private String subscriptionName = null;
	
	/**
	 * JMS Connection ClientID
	 */
	private String clientID = null;
	
	/**
	 * String User Name
	 */
	private String userName = null;
	
	/**
	 * Password
	 */
	private String password = null;
	
	
	/**
	 * 
	 */
	public TopicListener(Properties ctx, ReplicationProcessor replicationProcessor, String protocol, String host, int port
			, boolean isDurableSubscription, String subscriptionName, String topicName
			, String clientID, String userName, String password
			, String options, String trxName) {
		if ( host != null && !host.equals("") ) {
			this.host = host;
		}
		
		if ( port > 0 ) {
			this.port = port;
		}

		if ( protocol != null && !protocol.equals("") ) {
			this.protocol = protocol;
		}
		
		this.topicName = topicName;
		
		String uri=this.protocol + "://" + this.host + ":" + this.port;
		
		if(options!=null && options.length()>0)
		{
			if(!options.contains("?"))
				uri+="?"+options;
		}
		this.setUrl(uri);
		
		this.ctx = ctx;
		
		this.trxName = trxName;
		
		this.replicationProcessor = replicationProcessor;
		
		this.isDurableSubscription = isDurableSubscription;
		
		this.subscriptionName = subscriptionName;
		
		this.clientID = clientID;
		
		this.userName = userName;
		
		this.password = password;
		
	}
	
	public void run() throws JMSException {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory( url );
		log.finest("ActiveMQConnectionFactory = " + factory);
		
		if (userName !=null && password !=null) {
			conn = factory.createConnection(userName, password);
		} else {
			conn = factory.createConnection();
		}
		log.finest("conn = " + conn );
		
		if (conn.getClientID()==null)
		{
			try
			{
				conn.setClientID( clientID );
			}
			catch (Exception e)
			{
				//log.info("Connection with clientID '" + clientID +"' already exists" + e.toString());
				conn.close();
				return;
			}
		} else {
			if (conn.getClientID().equals(clientID))
			{
				log.warning("Connection with clientID '" + clientID + "' already exists");
				conn.close();
				return;
			} else {
				try
				{
					conn.setClientID( clientID );
				}
				catch (Exception e)
				{
					log.info("Error while invoking setClientID(" + clientID +")! " + e.getMessage());
					conn.close();
					return;
				}
			}
		}
		
		
		session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE); // TODO - could be parameter
		log.finest("session = " + session );
		
		log.finest("topicName = " + topicName );
		log.finest("subscriptionName = " + subscriptionName);
		
		topic = session.createTopic( topicName );
		log.finest("topic = " + topic );
		
		MessageConsumer consumer = null;
		if (isDurableSubscription) {
			// http://publib.boulder.ibm.com/infocenter/wasinfo/v6r0/index.jsp?topic=/com.ibm.websphere.pmc.express.doc/tasks/tjn0012_.html
			// The subscriptionName assigned to a durable subscription must be unique within a given client ID.
			consumer = session.createDurableSubscriber( topic, subscriptionName );	
		} else {
			consumer = session.createConsumer( topic );	
		}
		
		log.finest("consumer = " + consumer );
		
		consumer.setMessageListener( this );

		conn.start();
		log.finest("Waiting for JMS messages...");
		if(replicationProcessor !=null)
		{	
			MIMPProcessorLog pLog = new MIMPProcessorLog(replicationProcessor.getMImportProcessor(), "Connected to JMS Server. Waiting for messages!");
			StringBuffer logReference = new StringBuffer("topicName = ").append(topicName)
				.append(", subscriptionName = ").append( subscriptionName )
			;
			pLog.setReference( logReference.toString() );
			boolean resultSave = pLog.save();
			log.finest("Result Save = " + resultSave);
		}
		
	}
	
	/**
	 * 
	 */
	public void onMessage(Message message) {
		if ( message instanceof TextMessage ) {
			
			try {
				TextMessage txtMessage = (TextMessage) message;
				
				String text = txtMessage.getText();
				log.finest("Received message: \n" + text );

				Document documentToBeImported = XMLHelper.createDocumentFromString( text );
				StringBuffer result = new StringBuffer();
				
				ImportHelper impHelper = new ImportHelper( ctx );

				impHelper.importXMLDocument(result, documentToBeImported, trxName );
				
				log.finest("Message processed ...");
				
				if(replicationProcessor != null)
				{	
					MIMPProcessorLog pLog = new MIMPProcessorLog(replicationProcessor.getMImportProcessor(), "Imported Document!");
					//pLog.setReference("topicName = " + topicName );
					if (text.length() > 2000 ) {
						pLog.setTextMsg( text.substring(0, 1999) );
					} else {
						pLog.setTextMsg( text);
					}
					
					pLog.saveEx();
				}
				
				session.commit();
								
			} 
			catch (Exception e) 
			{
				log.finest("Rollback = " + e.toString());
				try 
				{
					session.rollback();
					stop();
					//replicationProcessor.interrupt();
					//replicationProcessor.join();	
					replicationProcessor.setProcessRunning(false);
				}
				/*catch (InterruptedException e1) {
				    // TODO Auto-generated catch block
				    e1.printStackTrace();
				}*/
				catch (JMSException e2) 
				{
					e2.printStackTrace();
				}
				e.printStackTrace();
				
			}

		} else {
			log.finest("Received NO TEXT Message: " );
		}
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void stop() throws JMSException {
		// Close JMS Connection
		log.finest("Closing JMS Connection!");
		conn.close();
	}
}
