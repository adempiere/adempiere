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
package org.compiere.server;

import java.sql.Timestamp;

import org.adempiere.server.rpl.IImportProcessor;
import org.compiere.model.AdempiereProcessor;
import org.compiere.model.MClient;
import org.compiere.model.X_IMP_Processor_Type;
import org.compiere.util.TimeUtil;
import org.compiere.server.AdempiereServer;
import org.compiere.model.MIMPProcessor;
import org.compiere.model.MIMPProcessorLog;

/**
 * 
 * @author Trifon N. Trifonov
 *
 */
public class ReplicationProcessor extends AdempiereServer {
	
	/**	Last Summary				*/
	private StringBuffer 		m_summary = new StringBuffer();
	
	/** Client info					*/
	private MClient 			m_client = null;
	
	private MIMPProcessor mImportProcessor = null;
	
	/**
	 * flag showing if process is working!
	 */
	private boolean isProcessRunning = false;
	
	
	/*protected ReplicationProcessor(MIMPProcessor model, int initialNap) {
		super (model, initialNap);
		mImportProcessor = model;
		m_client = MClient.get(mImportProcessor.getCtx(), mImportProcessor.getAD_Client_ID());
	}*/

	/*protected ReplicationProcessor(MIMPProcessor model) {
		super (model, 30);
		mImportProcessor = model;
		m_client = MClient.get(mImportProcessor.getCtx(), mImportProcessor.getAD_Client_ID());
	}*/
	
	protected ReplicationProcessor(AdempiereProcessor model, int initialNap) {
	super (model, initialNap);
	mImportProcessor = (MIMPProcessor)model;
	m_client = MClient.get(mImportProcessor.getCtx(), mImportProcessor.getAD_Client_ID());
}
	protected ReplicationProcessor(AdempiereProcessor model) {
		super (model, 30);
		mImportProcessor =(MIMPProcessor)model;
		m_client = MClient.get(mImportProcessor.getCtx(), mImportProcessor.getAD_Client_ID());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doWork() 
	{
		if (isProcessRunning) 
		{
			// process is already started successfully!
			
		} 
		else 
		{
		    // process is not started!
		    
		    m_summary = new StringBuffer();
		    String trxName = mImportProcessor.get_TrxName();
		    if ( trxName == null || "".equals(trxName) ) 
		    {
//				trxName = "ImportProcessor-" + System.currentTimeMillis();
		    }
		    log.fine("trxName = " + trxName);
		    log.fine("ImportProcessor = " + mImportProcessor);
		    
		    int IMP_ProcessorType_ID = 0;
		    IMP_ProcessorType_ID = mImportProcessor.getIMP_Processor_Type_ID();
		    X_IMP_Processor_Type impProcessor_Type = new X_IMP_Processor_Type(mImportProcessor.getCtx(), IMP_ProcessorType_ID, trxName );
		    log.fine("impProcessor_Type = " + impProcessor_Type); // TODO --- REMOVE
		    
		    String javaClass = impProcessor_Type.getJavaClass();
		    IImportProcessor importProcessor = null;
		    try 
		    {
			Class clazz = Class.forName(javaClass);
			importProcessor = (IImportProcessor)clazz.newInstance();				
			importProcessor.process(mImportProcessor.getCtx(), this, trxName );
		    } 
		    catch (Exception e) 
		    {
				isProcessRunning = false;
				log.fine("ReplicationProcessor caught an exception !!!" );
				e.printStackTrace();
				log.severe(e.getMessage());
				MIMPProcessorLog pLog = new MIMPProcessorLog(mImportProcessor, e.getMessage() );
				pLog.setReference("#" + String.valueOf(p_runCount) + " - " + TimeUtil.formatElapsed(new Timestamp(p_startWork)));
				pLog.saveEx();
				try 
				{
				    importProcessor.stop();
				} 
				catch (Exception e1) 
				{
				    e1.printStackTrace();
				    MIMPProcessorLog pLog2 = new MIMPProcessorLog(mImportProcessor, e1.getMessage() );					
				    pLog2.saveEx();
				} 
		    }
			//
			int no = mImportProcessor.deleteLog();
			m_summary.append("Logs Records deleted=").append(no).append("; ");
			//
			MIMPProcessorLog pLog = new MIMPProcessorLog(mImportProcessor, m_summary.toString());
			pLog.setReference("#" + String.valueOf(p_runCount) + " - " + TimeUtil.formatElapsed(new Timestamp(p_startWork)));
			pLog.saveEx();
		}
	}

	@Override
	public String getServerInfo()
	{
		return "#" + p_runCount + " - Last=" + m_summary.toString();
	}

	/**
	 * @return the isProcessRunning
	 */
	public boolean isProcessRunning() {
		return isProcessRunning;
	}

	/**
	 * @param isProcessRunning the isProcessRunning to set
	 */
	public void setProcessRunning(boolean isProcessRunning) {
		this.isProcessRunning = isProcessRunning;
	}

	/**
	 * @return the mImportProcessor
	 */
	public MIMPProcessor getMImportProcessor() {
		return mImportProcessor;
	}

	/**
	 * @param importProcessor the mImportProcessor to set
	 */
	public void setMImportProcessor(MIMPProcessor importProcessor) {
		mImportProcessor = importProcessor;
	}
	
}
