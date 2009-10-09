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
package org.compiere.process;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 *  Process Information (Value Object)
 *
 *  @author     Jorg Janke
 *  @version    $Id: ProcessInfo.java,v 1.2 2006/07/30 00:54:44 jjanke Exp $
 *  @author victor.perez@e-evolution.com 
 *  @see FR 1906632 http://sourceforge.net/tracker/?func=detail&atid=879335&aid=1906632&group_id=176962
 */
public class ProcessInfo implements Serializable
{
	/**
	 *  Constructor
	 *  @param Title Title
	 *  @param AD_Process_ID AD_Process_ID
	 *  @param Table_ID AD_Table_ID
	 *  @param Record_ID Record_ID
	 */
	public ProcessInfo (String Title, int AD_Process_ID, int Table_ID, int Record_ID)
	{
		setTitle (Title);
		setAD_Process_ID(AD_Process_ID);
		setTable_ID (Table_ID);
		setRecord_ID (Record_ID);
		if (Ini.isPropertyBool(Ini.P_PRINTPREVIEW))
			m_printPreview = true;
		else
			m_printPreview = false;
	}   //  ProcessInfo

	/**
	 *  Constructor
	 *  @param Title Title
	 *  @param AD_Process_ID AD_Process_ID
	 *   */
	public ProcessInfo (String Title, int AD_Process_ID)
	{
		this (Title, AD_Process_ID, 0, 0);
	}   //  ProcessInfo

	/**	Serialization Info	**/
	static final long serialVersionUID = -1993220053515488725L;
	

	/** Title of the Process/Report */
	private String				m_Title;
	/** Process ID                  */
	private int					m_AD_Process_ID;
	/** Table ID if the Process	    */
	private int					m_Table_ID;
	/** Record ID if the Process    */
	private int					m_Record_ID;
	/** User_ID        					*/
	private Integer	 			m_AD_User_ID;
	/** Client_ID        				*/
	private Integer 			m_AD_Client_ID;
	/** Class Name 						*/
	private String				m_ClassName = null;

	//  -- Optional --

	/** Process Instance ID         */
	private int					m_AD_PInstance_ID = 0;

	/** Summary of Execution        */
	private String    			m_Summary = "";
	/** Execution had an error      */
	private boolean     		m_Error = false;


	/*	General Data Object			*/
	private Serializable		m_SerializableObject = null;
	/*	General Data Object			*/
	private transient Object	m_TransientObject = null;
	/** Estimated Runtime           */
	private int          		m_EstSeconds = 5;
	/** Batch						*/
	private boolean				m_batch = false;
	/** Process timed out				*/
	private boolean				m_timeout = false;

	/**	Log Info					*/
	private ArrayList<ProcessInfoLog> m_logs = null;

	/**	Log Info					*/
	private ProcessInfoParameter[]	m_parameter = null;
	
	/** Transaction Name 			*/
	private String				m_transactionName = null;
	
	private boolean				m_printPreview = false;

	private boolean				m_reportingProcess = false;
	//FR 1906632
	private File 			    m_pdf_report = null;

	/**
	 *  String representation
	 *  @return String representation
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("ProcessInfo[");
		sb.append(m_Title)
			.append(",Process_ID=").append(m_AD_Process_ID);
		if (m_AD_PInstance_ID != 0)
			sb.append(",AD_PInstance_ID=").append(m_AD_PInstance_ID);
		if (m_Record_ID != 0)
			sb.append(",Record_ID=").append(m_Record_ID);
		if (m_ClassName != null)
			sb.append(",ClassName=").append(m_ClassName);
		sb.append(",Error=").append(isError());
		if (m_TransientObject != null)
			sb.append(",Transient=").append(m_TransientObject);
		if (m_SerializableObject != null)
			sb.append(",Serializable=").append(m_SerializableObject);
		sb.append(",Summary=").append(getSummary())
			.append(",Log=").append(m_logs == null ? 0 : m_logs.size());
		//	.append(getLogInfo(false));
		sb.append("]");
		return sb.toString();
	}   //  toString

	
	/**************************************************************************
	 * 	Set Summary
	 * 	@param summary summary (will be translated)
	 */
	public void setSummary (String summary)
	{
		m_Summary = summary;
	}	//	setSummary
	/**
	 * Method getSummary
	 * @return String
	 */
	public String getSummary ()
	{
		return Util.cleanAmp(m_Summary);
	}	//	getSummary

	/**
	 * Method setSummary
	 * @param translatedSummary String
	 * @param error boolean
	 */
	public void setSummary (String translatedSummary, boolean error)
	{
		setSummary (translatedSummary);
		setError(error);
	}	//	setSummary
	/**
	 * Method addSummary
	 * @param additionalSummary String
	 */
	public void addSummary (String additionalSummary)
	{
		m_Summary += additionalSummary;
	}	//	addSummary

	/**
	 * Method setError
	 * @param error boolean
	 */
	public void setError (boolean error)
	{
		m_Error = error;
	}	//	setError
	/**
	 * Method isError
	 * @return boolean
	 */
	public boolean isError ()
	{
		return m_Error;
	}	//	isError

	/**
	 *	Batch
	 * 	@param batch true if batch processing
	 */
	public void setIsBatch (boolean batch)
	{
		m_batch = batch;
	}	//	setTimeout
	
	/**
	 *	Batch - i.e. UI not blocked
	 *	@return boolean
	 */
	public boolean isBatch()
	{
		return m_batch;
	}	//	isBatch

	/**
	 *	Timeout
	 * 	@param timeout true still running
	 */
	public void setIsTimeout (boolean timeout)
	{
		m_timeout = timeout;
	}	//	setTimeout
	
	/**
	 *	Timeout - i.e process did not complete
	 *	@return boolean
	 */
	public boolean isTimeout()
	{
		return m_timeout;
	}	//	isTimeout

	/**
	 *	Set Log of Process.
	 *  <pre>
	 *  - Translated Process Message
	 *  - List of log entries
	 *      Date - Number - Msg
	 *  </pre>
	 *	@param html if true with HTML markup
	 *	@return Log Info
	 */
	public String getLogInfo (boolean html)
	{
		if (m_logs == null)
			return "";
		//
		StringBuffer sb = new StringBuffer ();
		SimpleDateFormat dateFormat = DisplayType.getDateFormat(DisplayType.DateTime);
		if (html)
			sb.append("<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"2\">");
		//
		for (int i = 0; i < m_logs.size(); i++)
		{
			if (html)
				sb.append("<tr>");
			else if (i > 0)
				sb.append("\n");
			//
			ProcessInfoLog log = m_logs.get(i);
			/**
			if (log.getP_ID() != 0)
				sb.append(html ? "<td>" : "")
					.append(log.getP_ID())
					.append(html ? "</td>" : " \t");	**/
			//
			if (log.getP_Date() != null)
				sb.append(html ? "<td>" : "")
					.append(dateFormat.format(log.getP_Date()))
					.append(html ? "</td>" : " \t");
			//
			if (log.getP_Number() != null)
				sb.append(html ? "<td>" : "")
					.append(log.getP_Number())
					.append(html ? "</td>" : " \t");
			//
			if (log.getP_Msg() != null)
				sb.append(html ? "<td>" : "")
					.append(Msg.parseTranslation(Env.getCtx(), log.getP_Msg()))
					.append(html ? "</td>" : "");
			//
			if (html)
				sb.append("</tr>");
		}
		if (html)
			sb.append("</table>");
		return sb.toString();
	 }	//	getLogInfo

	/**
	 * 	Get ASCII Log Info
	 *	@return Log Info
	 */
	public String getLogInfo ()
	{
		return getLogInfo(false);
	}	//	getLogInfo

	/**
	 * Method getAD_PInstance_ID
	 * @return int
	 */
	public int getAD_PInstance_ID()
	{
		return m_AD_PInstance_ID;
	}
	/**
	 * Method setAD_PInstance_ID
	 * @param AD_PInstance_ID int
	 */
	public void setAD_PInstance_ID(int AD_PInstance_ID)
	{
		m_AD_PInstance_ID = AD_PInstance_ID;
	}

	/**
	 * Method getAD_Process_ID
	 * @return int
	 */
	public int getAD_Process_ID()
	{
		return m_AD_Process_ID;
	}
	/**
	 * Method setAD_Process_ID
	 * @param AD_Process_ID int
	 */
	public void setAD_Process_ID(int AD_Process_ID)
	{
		m_AD_Process_ID = AD_Process_ID;
	}

	/**
	 * Method getClassName
	 * @return String or null
	 */
	public String getClassName()
	{
		return m_ClassName;
	}
	
	/**
	 * Method setClassName
	 * @param ClassName String
	 */
	public void setClassName(String ClassName)
	{
		m_ClassName = ClassName;
		if (m_ClassName != null && m_ClassName.length() == 0)
			m_ClassName = null;
	}	//	setClassName

	/**
	 * Method getTransientObject
	 * @return Object
	 */
	public Object getTransientObject()
	{
		return m_TransientObject;
	}
	/**
	 * Method setTransientObject
	 * @param TransientObject Object
	 */
	public void setTransientObject (Object TransientObject)
	{
		m_TransientObject = TransientObject;
	}

	/**
	 * Method getSerializableObject
	 * @return Serializable
	 */
	public Serializable getSerializableObject()
	{
		return m_SerializableObject;
	}
	/**
	 * Method setSerializableObject
	 * @param SerializableObject Serializable
	 */
	public void setSerializableObject (Serializable SerializableObject)
	{
		m_SerializableObject = SerializableObject;
	}

	/**
	 * Method getEstSeconds
	 * @return int
	 */
	public int getEstSeconds()
	{
		return m_EstSeconds;
	}
	/**
	 * Method setEstSeconds
	 * @param EstSeconds int
	 */
	public void setEstSeconds (int EstSeconds)
	{
		m_EstSeconds = EstSeconds;
	}


	/**
	 * Method getTable_ID
	 * @return int
	 */
	public int getTable_ID()
	{
		return m_Table_ID;
	}
	/**
	 * Method setTable_ID
	 * @param AD_Table_ID int
	 */
	public void setTable_ID(int AD_Table_ID)
	{
		m_Table_ID = AD_Table_ID;
	}

	/**
	 * Method getRecord_ID
	 * @return int
	 */
	public int getRecord_ID()
	{
		return m_Record_ID;
	}
	/**
	 * Method setRecord_ID
	 * @param Record_ID int
	 */
	public void setRecord_ID(int Record_ID)
	{
		m_Record_ID = Record_ID;
	}

	/**
	 * Method getTitle
	 * @return String
	 */
	public String getTitle()
	{
		return m_Title;
	}
	/**
	 * Method setTitle
	 * @param Title String
	 */
	public void setTitle (String Title)
	{
		m_Title = Title;
	}	//	setTitle


	/**
	 * Method setAD_Client_ID
	 * @param AD_Client_ID int
	 */
	public void setAD_Client_ID (int AD_Client_ID)
	{
		m_AD_Client_ID = new Integer (AD_Client_ID);
	}
	/**
	 * Method getAD_Client_ID
	 * @return Integer
	 */
	public Integer getAD_Client_ID()
	{
		return m_AD_Client_ID;
	}

	/**
	 * Method setAD_User_ID
	 * @param AD_User_ID int
	 */
	public void setAD_User_ID (int AD_User_ID)
	{
		m_AD_User_ID = new Integer (AD_User_ID);
	}
	/**
	 * Method getAD_User_ID
	 * @return Integer
	 */
	public Integer getAD_User_ID()
	{
		return m_AD_User_ID;
	}

	
	/**************************************************************************
	 * 	Get Parameter
	 *	@return Parameter Array
	 */
	public ProcessInfoParameter[] getParameter()
	{
		return m_parameter;
	}	//	getParameter

	/**
	 * 	Set Parameter
	 *	@param parameter Parameter Array
	 */
	public void setParameter (ProcessInfoParameter[] parameter)
	{
		m_parameter = parameter;
	}	//	setParameter

	
	/**************************************************************************
	 * 	Add to Log
	 *	@param Log_ID Log ID
	 *	@param P_ID Process ID
	 *	@param P_Date Process Date
	 *	@param P_Number Process Number
	 *	@param P_Msg Process Message
	 */
	public void addLog (int Log_ID, int P_ID, Timestamp P_Date, BigDecimal P_Number, String P_Msg)
	{
		addLog (new ProcessInfoLog (Log_ID, P_ID, P_Date, P_Number, P_Msg));
	}	//	addLog

	/**
	 * 	Add to Log
	 *	@param P_ID Process ID
	 *	@param P_Date Process Date
	 *	@param P_Number Process Number
	 *	@param P_Msg Process Message
	 */
	public void addLog (int P_ID, Timestamp P_Date, BigDecimal P_Number, String P_Msg)
	{
		addLog (new ProcessInfoLog (P_ID, P_Date, P_Number, P_Msg));
	}	//	addLog

	/**
	 * 	Add to Log
	 *	@param logEntry log entry
	 */
	public void addLog (ProcessInfoLog logEntry)
	{
		if (logEntry == null)
			return;
		if (m_logs == null)
			m_logs = new ArrayList<ProcessInfoLog>();
		m_logs.add (logEntry);
	}	//	addLog


	/**
	 * Method getLogs
	 * @return ProcessInfoLog[]
	 */
	public ProcessInfoLog[] getLogs()
	{
		if (m_logs == null)
			return null;
		ProcessInfoLog[] logs = new ProcessInfoLog[m_logs.size()];
		m_logs.toArray (logs);
		return logs;
	}	//	getLogs

	/**
	 * Method getIDs
	 * @return int[]
	 */
	public int[] getIDs()
	{
		if (m_logs == null)
			return null;
		int[] ids = new int[m_logs.size()];
		for (int i = 0; i < m_logs.size(); i++)
			ids[i] = m_logs.get(i).getP_ID();
		return ids;
	}	//	getIDs

	/**
	 * Method getLogList
	 * @return ArrayList
	 */
	public ArrayList<ProcessInfoLog> getLogList()
	{
		return m_logs;
	}
	/**
	 * Method setLogList
	 * @param logs ArrayList
	 */
	public void setLogList (ArrayList<ProcessInfoLog> logs)
	{
		m_logs = logs;
	}
	
	/**
	 * Get transaction name for this process
	 * @return String
	 */
	public String getTransactionName()
	{
		return m_transactionName;
	}

	/**
	 * Set transaction name from this process
	 * @param trxName
	 */
	public void setTransactionName(String trxName)
	{
		m_transactionName = trxName;
	}
	
	/**
	 * Set print preview flag, only relevant if this is a reporting process
	 * @param b
	 */
	public void setPrintPreview(boolean b)
	{
		m_printPreview = b;
	}
	
	/**
	 * Is print preview instead of direct print ? Only relevant if this is a reporting process 
	 * @return boolean
	 */
	public boolean isPrintPreview()
	{
		return m_printPreview;
	}
	
	/**
	 * Is this a reporting process ?
	 * @return boolean
	 */
	public boolean isReportingProcess() 
	{
		return m_reportingProcess;
	}
	
	/**
	 * Set is this a reporting process
	 * @param f
	 */
	public void setReportingProcess(boolean f)
	{
		m_reportingProcess = f;
	}
	
	//FR 1906632
	/**
	 * Set PDF file generate to Jasper Report
	 * @param PDF File 
	 */
	public void setPDFReport(File f)
	{
		m_pdf_report = f;
	}	
	
	/**
	 * Get PDF file generate to Jasper Report
	 * @param f
	 */
	public File getPDFReport()
	{
		return m_pdf_report;
	}	
		
	
}   //  ProcessInfo
