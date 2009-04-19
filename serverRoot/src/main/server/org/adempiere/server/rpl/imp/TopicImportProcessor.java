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
 *                                                                    *
 * Sponsors:                                                          *
 *  - E-evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/
package org.adempiere.server.rpl.imp;

import java.util.Properties;

import org.adempiere.server.rpl.IImportProcessor;
import org.compiere.model.MIMPProcessor;
import org.compiere.model.X_IMP_ProcessorParameter;
import org.compiere.server.ReplicationProcessor;
import org.compiere.util.CLogger;

/**
 * Aim of this class is to import records from JMS Server.
 * 
 * @author Trifon N. Trifonov
 * @version $Id:$
 */
public class TopicImportProcessor implements IImportProcessor {
	
	/**	Logger	*/
	protected CLogger	log = CLogger.getCLogger (TopicImportProcessor.class);
	
	/**
	 * Topic Listener
	 */
	private TopicListener topicListener = null;
	
	
	public void process(Properties ctx, ReplicationProcessor replicationProcessor, String trxName)
			throws Exception {
		
		log.info("replicationProcessor = " + replicationProcessor);
		log.info("replicationProcessor.getMImportProcessor() = " + replicationProcessor.getMImportProcessor());
		
		MIMPProcessor impProcessor = replicationProcessor.getMImportProcessor();
		
		X_IMP_ProcessorParameter[] processorParameters = impProcessor.getIMP_ProcessorParameters(trxName);
		
		String host = impProcessor.getHost();
		int port = impProcessor.getPort();
		String account = impProcessor.getAccount();
		String password = impProcessor.getPasswordInfo();
		
		// mandatory parameters!
		String topicName = null;
		String protocol = null;
		boolean isDurableSubscription = true;
		String subscriptionName = null;
		String options = null;
		String clientID = null;
		
		if (processorParameters != null && processorParameters.length > 0) {
        	for (int i = 0; i < processorParameters.length; i++) {
        		log.info("ProcesParameter          Value = " + processorParameters[i].getValue());
        		log.info("ProcesParameter ParameterValue = " + processorParameters[i].getParameterValue());
        		if (processorParameters[i].getValue().equals("topicName")) {
        			topicName = processorParameters[i].getParameterValue();
        		} else if (processorParameters[i].getValue().equals("protocol")) {
        			protocol = processorParameters[i].getParameterValue();
        		} else if (processorParameters[i].getValue().equals("isDurableSubscription")) {
        			isDurableSubscription = Boolean.parseBoolean( processorParameters[i].getParameterValue() );
        		} else if (processorParameters[i].getValue().equals("subscriptionName")) {
        			subscriptionName = processorParameters[i].getParameterValue();
        		} else if (processorParameters[i].getValue().equals("clientID")) {
        			clientID = processorParameters[i].getParameterValue();
        		} else {
        			// Some other mandatory parameter here
        		}
        	}
        }
        
        if (topicName == null || topicName.length() == 0) {
        	throw new Exception("Missing "+X_IMP_ProcessorParameter.Table_Name+" with key 'topicName'!");
        }
        if (protocol == null || protocol.length() == 0) {
        	throw new Exception("Missing "+X_IMP_ProcessorParameter.Table_Name+" with key 'protocol'!");
        }
        if (isDurableSubscription && subscriptionName == null || subscriptionName.length() == 0) {
        	throw new Exception("Missing "+X_IMP_ProcessorParameter.Table_Name+" with key 'subscriptionName'!");
        }
        if (clientID == null || clientID.length() == 0) {
        	throw new Exception("Missing "+X_IMP_ProcessorParameter.Table_Name+" with key 'clientID'!");
        }
        
        topicListener = new TopicListener(ctx, replicationProcessor, protocol, host, port
        		, isDurableSubscription, subscriptionName, topicName, clientID
        		, account, password, options, trxName);
        
        topicListener.run();
        log.info("Started topicListener = " + topicListener);
   }

	public void stop() throws Exception {
		
		if ( topicListener != null ) {
			topicListener.stop();
			log.info("Stoped topicListener." );
		}
		
	}
	
	
}
