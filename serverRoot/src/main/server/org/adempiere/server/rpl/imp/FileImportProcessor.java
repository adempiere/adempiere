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
import org.adempiere.server.rpl.XMLHelper;
import org.compiere.model.MIMPProcessor;
import org.compiere.model.X_IMP_ProcessorParameter;
import org.compiere.server.ReplicationProcessor;
import org.compiere.util.CLogger;
import org.w3c.dom.Document;

/**
 * 
 * @author Trifon N. Trifonov
 * @version $Id:$
 */
public class FileImportProcessor implements IImportProcessor {
	
	/**	Logger	*/
	protected CLogger	log = CLogger.getCLogger (FileImportProcessor.class);
	
	public void process(Properties ctx, ReplicationProcessor replicationProcessor, String trxName)
			throws Exception {
		
		MIMPProcessor impProcessor = replicationProcessor.getMImportProcessor();
		
		X_IMP_ProcessorParameter[] processorParameters = impProcessor.getIMP_ProcessorParameters(trxName);
		
		String fileName = null;
		String folder  = "";
		if (processorParameters != null && processorParameters.length > 0) {
        	for (int i = 0; i < processorParameters.length; i++) {
        		log.info("ProcesParameter          Value = " + processorParameters[i].getValue());
        		log.info("ProcesParameter ParameterValue = " + processorParameters[i].getParameterValue());
        		if (processorParameters[i].getValue().equals("fileName")) {
        			fileName = processorParameters[i].getParameterValue();
        		} else if ( processorParameters[i].getValue().equals("folder") ) {
        			folder = processorParameters[i].getParameterValue();
        		}
        	}
        }
        
        if (fileName == null || fileName.length() == 0) {
        	throw new Exception("Missing IMP_ProcessorParameter with key 'fileName'!");
        }
        
		Document documentToBeImported = XMLHelper.createDocumentFromFile(folder + fileName);
		StringBuffer result = new StringBuffer();
		
		ImportHelper impHelper = new ImportHelper( ctx );
		impHelper.importXMLDocument(result, documentToBeImported, trxName );

//		addLog(0, null, null, Msg.getMsg(ctx, "ImportModelProcessResult") + "\n" + result.toString());
	}

	public void stop() throws Exception {
		// do nothing!
	}

}
