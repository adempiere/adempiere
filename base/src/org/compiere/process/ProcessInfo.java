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
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MTable;
import org.compiere.model.PO;
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
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 244 ] Is Selection flag
 *		@see https://github.com/adempiere/adempiere/issues/244
 *		<li> FR [ 325 ] SvrProcess must handle mandatory error on Process Parameters
 *		@see https://github.com/adempiere/adempiere/issues/325
 *		<li>FR [ 352 ] T_Selection is better send to process like a HashMap instead read from disk
 *		@see https://github.com/adempiere/adempiere/issues/352
 *	@author Victor Perez , victor.perez@e-evolution.com, http://e-evolution.com
 */
public class ProcessInfo implements Serializable
{
	/**
	 *  Constructor
	 *  @param title Title
	 *  @param processId Process Id
	 *  @param tableId Table Id
	 *  @param recordId Record Id
	 */
	public ProcessInfo (String title, int processId, int tableId, int recordId)
	{
		setTitle (title);
		setAD_Process_ID(processId);
		setTable_ID (tableId);
		setRecord_ID (recordId);
		if (Ini.isPropertyBool(Ini.P_PRINTPREVIEW))
			printPreview = true;
		else
			printPreview = false;
	}   //  ProcessInfo

	public ProcessInfo (String title, int processId, int tableId, int recordId, boolean managedTransaction)
	{
		this(title, processId , tableId , recordId);
		this.managedTransaction = managedTransaction;
	}

	/**
	 *  Constructor
	 *  @param title Title
	 *  @param processId Process Id
	 *   */
	public ProcessInfo (String title, int processId)
	{
		this (title, processId, 0, 0);
	}   //  ProcessInfo

	public ProcessInfo (String title, int processId, boolean managedTransaction)
	{
		this (title , processId);
		this.managedTransaction = managedTransaction;
	}

	/**	Serialization Info	**/
	static final long serialVersionUID = -1993220053515488725L;
	

	/** Title of the Process/Report */
	private String 				title;
	/** Process ID                  */
	private int 				processId;
	/** Table ID if the Process	    */
	private int 				tableId;
	/** Record ID if the Process    */
	private int 				recordId;
	/* Table ID */
	private int tableSelectionId;
	/* Alias table selection */
	private String aliasTableSelection;
	/** User_ID        					*/
	private Integer 			userId;
	/** Client_ID        				*/
	private Integer 			clientId;
	/** Class Name 						*/
	private String 				className = null;

	//  -- Optional --

	/** Process Instance ID         */
	private int 				instanceId = 0;

	/** Summary of Execution        */
	private String 				summary = "";
	/** Execution had an error      */
	private boolean 			hadError = false;


	/*	General Data Object			*/
	private Serializable 		serializableObject = null;
	/*	General Data Object			*/
	private transient Object 	transientObject = null;
	/** Estimated Runtime           */
	private int 				estimatedSeconds = 5;
	/** Batch						*/
	private boolean 			batch = false;
	/** Process timed out				*/
	private boolean 			timeout = false;

	/**	Log Info					*/
	private ArrayList<ProcessInfoLog> logs = null;

	/**	Log Info					*/
	private Hashtable<String, ProcessInfoParameter> parameters = null;
	//	FR [ 352 ]
	/**	Multi-Selection Parameters	*/
	private LinkedHashMap<Integer, LinkedHashMap<String, Object>> selection = null;
	/**	Multi-Selection Keys		*/
	private List<Integer>		keySelection = null;
	
	/** Transaction Name 			*/
	private String 				transactionName = null;
	
	private boolean 			printPreview = false;

	private boolean 			reportingProcess = false;
	//FR 1906632
	private File 				pdfReportFile = null;

	private boolean 			managedTransaction = true;
	
	//	FR [ 244 ]
	private boolean 			isSelection = false;
	
	/**
	 * If the process fails with an Throwable, the Throwable is caught and stored here
	 */
	// 03152: motivation to add this is that now in ait we can assert that a certain exception was thrown.
	private Throwable 			throwable = null;
	
	/**
	 *  String representation
	 *  @return String representation
	 */
	public String toString()
	{
		StringBuffer stringBuffer = new StringBuffer("ProcessInfo[");
		stringBuffer.append(title)
			.append(",Process_ID=").append(processId);
		if (instanceId != 0)
			stringBuffer.append(",AD_PInstance_ID=").append(instanceId);
		if (recordId != 0)
			stringBuffer.append(",Record_ID=").append(recordId);
		if (className != null)
			stringBuffer.append(",ClassName=").append(className);
		stringBuffer.append(",Error=").append(isError());
		if (transientObject != null)
			stringBuffer.append(",Transient=").append(transientObject);
		if (serializableObject != null)
			stringBuffer.append(",Serializable=").append(serializableObject);
		stringBuffer.append(",Summary=").append(getSummary())
			.append(",Log=").append(logs == null ? 0 : logs.size());
		//	.append(getLogInfo(false));
		stringBuffer.append("]");
		return stringBuffer.toString();
	}   //  toString

	
	/**
	 * FR [ 244 ]
	 * Set the flag for know if is from SB or not
	 * @param isSelection
	 */
	public void setIsSelection(boolean isSelection) {
		this.isSelection = isSelection;
	}
	
	/**
	 * FR [ 244 ]
	 * Return flag is selection
	 * @return
	 */
	public boolean isSelection() {
		return isSelection;
	}
	
	
	/**************************************************************************
	 * 	Set Summary
	 * 	@param summary summary (will be translated)
	 */
	public void setSummary (String summary)
	{
		this.summary = summary;
	}	//	setSummary
	/**
	 * Method getSummary
	 * @return String
	 */
	public String getSummary ()
	{
		return Util.cleanAmp(summary);
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
		summary += additionalSummary;
	}	//	addSummary

	/**
	 * Method setError
	 * @param error boolean
	 */
	public void setError (boolean error)
	{
		hadError = error;
	}	//	setError
	/**
	 * Method isError
	 * @return boolean
	 */
	public boolean isError ()
	{
		return hadError;
	}	//	isError

	/**
	 *	Batch
	 * 	@param batch true if batch processing
	 */
	public void setIsBatch (boolean batch)
	{
		this.batch = batch;
	}	//	setTimeout
	
	/**
	 *	Batch - i.e. UI not blocked
	 *	@return boolean
	 */
	public boolean isBatch()
	{
		return batch;
	}	//	isBatch

	/**
	 *	Timeout
	 * 	@param timeout true still running
	 */
	public void setIsTimeout (boolean timeout)
	{
		this.timeout = timeout;
	}	//	setTimeout
	
	/**
	 *	Timeout - i.e process did not complete
	 *	@return boolean
	 */
	public boolean isTimeout()
	{
		return timeout;
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
		if (logs == null)
			return "";
		//
		StringBuffer stringBuffer = new StringBuffer ();
		SimpleDateFormat dateFormat = DisplayType.getDateFormat(DisplayType.DateTime);
		if (html)
			stringBuffer.append("<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"2\">");
		//
		for (int i = 0; i < logs.size(); i++)
		{
			if (html)
				stringBuffer.append("<tr>");
			else if (i > 0)
				stringBuffer.append("\n");
			//
			ProcessInfoLog log = logs.get(i);
			/**
			if (log.getP_ID() != 0)
				sb.append(html ? "<td>" : "")
					.append(log.getP_ID())
					.append(html ? "</td>" : " \t");	**/
			//
			if (log.getP_Date() != null)
				stringBuffer.append(html ? "<td>" : "")
					.append(dateFormat.format(log.getP_Date()))
					.append(html ? "</td>" : " \t");
			//
			if (log.getP_Number() != null)
				stringBuffer.append(html ? "<td>" : "")
					.append(log.getP_Number())
					.append(html ? "</td>" : " \t");
			//
			if (log.getP_Msg() != null)
				stringBuffer.append(html ? "<td>" : "")
					.append(Msg.parseTranslation(Env.getCtx(), log.getP_Msg()))
					.append(html ? "</td>" : "");
			//
			if (html)
				stringBuffer.append("</tr>");
		}
		if (html)
			stringBuffer.append("</table>");
		return stringBuffer.toString();
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
		return instanceId;
	}
	/**
	 * Method setAD_PInstance_ID
	 * @param instanceId int
	 */
	public void setAD_PInstance_ID(int instanceId)
	{
		this.instanceId = instanceId;
	}

	/**
	 * Method getAD_Process_ID
	 * @return int
	 */
	public int getAD_Process_ID()
	{
		return processId;
	}
	/**
	 * Method setAD_Process_ID
	 * @param processId int
	 */
	public void setAD_Process_ID(int processId)
	{
		this.processId = processId;
	}

	/**
	 * Method getClassName
	 * @return String or null
	 */
	public String getClassName()
	{
		return className;
	}
	
	/**
	 * Method setClassName
	 * @param ClassName String
	 */
	public void setClassName(String ClassName)
	{
		className = ClassName;
		if (className != null && className.length() == 0)
			className = null;
	}	//	setClassName

	/**
	 * Method getTransientObject
	 * @return Object
	 */
	public Object getTransientObject()
	{
		return transientObject;
	}
	/**
	 * Method setTransientObject
	 * @param transientObject Object
	 */
	public void setTransientObject (Object transientObject)
	{
		this.transientObject = transientObject;
	}

	/**
	 * Method getSerializableObject
	 * @return Serializable
	 */
	public Serializable getSerializableObject()
	{
		return serializableObject;
	}
	/**
	 * Method setSerializableObject
	 * @param serializableObject Serializable
	 */
	public void setSerializableObject (Serializable serializableObject)
	{
		this.serializableObject = serializableObject;
	}

	/**
	 * Method getEstSeconds
	 * @return int
	 */
	public int getEstSeconds()
	{
		return estimatedSeconds;
	}
	/**
	 * Method setEstSeconds
	 * @param estimatedSeconds int
	 */
	public void setEstSeconds (int estimatedSeconds)
	{
		this.estimatedSeconds = estimatedSeconds;
	}


	/**
	 * Method getTable_ID
	 * @return int
	 */
	public int getTable_ID()
	{
		return tableId;
	}
	/**
	 * Method setTable_ID
	 * @param tableId int
	 */
	public void setTable_ID(int tableId)
	{
		this.tableId = tableId;
	}

	/**
	 * set table alias for  selection
	 * @param aliasTableSelection
	 */
	public void setAliasForTableSelection(String aliasTableSelection)
	{
		this.aliasTableSelection = aliasTableSelection;
	}

	/**
	 * Get Selection Table Alias
	 * @return
	 */
	public String getAliasForTableSelection()
	{
		return aliasTableSelection;
	}

	/**
	 * get prefix alias for a table selection
	 * @return
	 */
	public String getPrefixAliasForTableSelection()
	{
		return aliasTableSelection.toUpperCase() + "_";
	}

	/**
	 * Method setTable_ID
	 * @param tableSelectionId
	 */
	public void setTableSelectionId(int tableSelectionId)
	{
		this.tableSelectionId = tableSelectionId;
	}

	/**
	 * Method tableSelectionId
	 * @return int
	 */
	public int getTableSelectionId()
	{
		return tableSelectionId;
	}

	/**
	 * Method getRecord_ID
	 * @return int
	 */
	public int getRecord_ID()
	{
		return recordId;
	}
	/**
	 * Method setRecord_ID
	 * @param recordId int
	 */
	public void setRecord_ID(int recordId)
	{
		this.recordId = recordId;
	}

	/**
	 * Method getTitle
	 * @return String
	 */
	public String getTitle()
	{
		return title;
	}
	/**
	 * Method setTitle
	 * @param title String
	 */
	public void setTitle (String title)
	{
		this.title = title;
	}	//	setTitle


	/**
	 * Method setAD_Client_ID
	 * @param clientId int
	 */
	public void setAD_Client_ID (int clientId)
	{
		this.clientId = new Integer (clientId);
	}
	/**
	 * Method getAD_Client_ID
	 * @return Integer
	 */
	public Integer getAD_Client_ID()
	{
		return clientId;
	}

	/**
	 * Method setAD_User_ID
	 * @param userId int
	 */
	public void setAD_User_ID (int userId)
	{
		this.userId = new Integer (userId);
	}
	/**
	 * Method getAD_User_ID
	 * @return Integer
	 */
	public Integer getAD_User_ID()
	{
		return userId;
	}

	
	/**************************************************************************
	 * 	Get Parameter
	 *  FR [ 325 ] Is preferable use any of getParameter(String) method
	 *	@return Parameter Array
	 */
	public ProcessInfoParameter[] getParameter()
	{
		if (parameters == null)
			return null;
		
		ProcessInfoParameter[] processInfoParameters = new ProcessInfoParameter[parameters.size()];
		//	FR [ 325 ] add support to get parameter like array
		parameters.values().toArray(processInfoParameters);
		return processInfoParameters;

	}	//	getParameter

	/**
	 * 	Set Parameter
	 *	@param parameters Parameter Array
	 */
	public void setParameter (ProcessInfoParameter[] parameters)
	{
		//	Set to null if parameter is null
		//	BR [ 380 ]
		if(parameters == null) {
			this.parameters = null;
			return;
		}
		//	
		this.parameters = new Hashtable<String, ProcessInfoParameter>();
		//	FR [ 325 ] Populate Hash
		for(ProcessInfoParameter parameter : parameters) {
			if(parameter.getParameterName() == null)
				continue;
			//	
			this.parameters.put(parameter.getParameterName(), parameter);
		}
	}	//	setParameter

	/**
	 * Set Selection keys
	 * @param selection
	 */
	public void setSelectionKeys(List<Integer> selection) {
		keySelection = selection;
		setIsSelection(selection != null && selection.size() > 0);
	}
	
	/**
	 * Get Selection keys (used just for key without values)
	 * @return
	 */
	public List<Integer> getSelectionKeys() {
		return keySelection;
	}
	
	/**
	 * Set Selection Parameters
	 * @param selection
	 */
	public void setSelectionValues(LinkedHashMap<Integer, LinkedHashMap<String, Object>> selection) {
		this.selection = selection;
		setIsSelection(selection != null && selection.size() > 0);
		//	fill key
		if(selection != null) {
			keySelection = new ArrayList<Integer>();
			for(Entry<Integer,LinkedHashMap<String, Object>> records : selection.entrySet()) {
				keySelection.add(records.getKey());
			}
		}
	}

	/**
	 * get instances for selection
	 * @param trxName
	 * @return
	 * @throws AdempiereException
     */
	public List<?> getInstancesForSelection(String trxName) throws AdempiereException
	{
		return PO.getInstances( getTableSelectionId() , getSelectionKeys(), trxName);
	}

	/**
	 * get intance from table id of process info
	 * @param trxName
	 * @return
	 * @throws AdempiereException
	 */
	public PO getInstance(String trxName) throws AdempiereException
	{
		if (tableId <= 0)
			throw new AdempiereException("@AD_Table_ID@  @NotFound@");
		if (getRecord_ID() <= 0)
			throw new AdempiereException("@NoRecordID@");

		MTable table =  MTable.get(Env.getCtx() , tableId);
		return table.getPO(getRecord_ID(), trxName);
	}

	/**
	 * Get Selection
	 * @return
	 */
	public LinkedHashMap<Integer, LinkedHashMap<String, Object>> getSelectionValues() {
		return selection;
	}
	
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
		if (logs == null)
			logs = new ArrayList<ProcessInfoLog>();
		logs.add (logEntry);
	}	//	addLog


	/**
	 * Method getLogs
	 * @return ProcessInfoLog[]
	 */
	public ProcessInfoLog[] getLogs()
	{
		if (logs == null)
			return null;
		ProcessInfoLog[] processInfoLogs = new ProcessInfoLog[this.logs.size()];
		this.logs.toArray (processInfoLogs);
		return processInfoLogs;
	}	//	getLogs

	/**
	 * Method getIDs
	 * @return int[]
	 */
	public int[] getIDs()
	{
		if (logs == null)
			return null;
		int[] ids = new int[logs.size()];
		for (int i = 0; i < logs.size(); i++)
			ids[i] = logs.get(i).getP_ID();
		return ids;
	}	//	getIDs


	/**
	 * Method getLogList
	 * @return ArrayList
	 */
	public ArrayList<ProcessInfoLog> getLogList()
	{
		return logs;
	}
	/**
	 * Method setLogList
	 * @param logs ArrayList
	 */
	public void setLogList (ArrayList<ProcessInfoLog> logs)
	{
		this.logs = logs;
	}
	
	/**
	 * Get transaction name for this process
	 * @return String
	 */
	public String getTransactionName()
	{
		return transactionName;
	}

	/**
	 * Set transaction name from this process
	 * @param trxName
	 */
	public void setTransactionName(String trxName)
	{
		transactionName = trxName;
	}
	
	/**
	 * Set print preview flag, only relevant if this is a reporting process
	 * @param printPreview
	 */
	public void setPrintPreview(boolean printPreview)
	{
		this.printPreview = printPreview;
	}
	
	/**
	 * Is print preview instead of direct print ? Only relevant if this is a reporting process 
	 * @return boolean
	 */
	public boolean isPrintPreview()
	{
		return printPreview;
	}
	
	/**
	 * Is this a reporting process ?
	 * @return boolean
	 */
	public boolean isReportingProcess() 
	{
		return reportingProcess;
	}
	
	/**
	 * Set is this a reporting process
	 * @param reportingProcess
	 */
	public void setReportingProcess(boolean reportingProcess)
	{
		this.reportingProcess = reportingProcess;
	}
	
	//FR 1906632
	/**
	 * Set PDF file generate to Jasper Report
	 * @param pdfFile
	 */
	public void setPDFReport(File pdfFile)
	{
		pdfReportFile = pdfFile;
	}	
	
	/**
	 * Get PDF file generate to Jasper Report
	 */
	public File getPDFReport()
	{
		return pdfReportFile;
	}	
	
	/**
	 * Add parameter
	 * @param name
	 * @param value
	 * @param info
	 */
	public void addParameter(String name, Object value, String info)
	{
		if (value == null)
			return;
		if (value instanceof String && Util.isEmpty((String) value))
			return;
		//	FR [ 325 ] Add support to HashMap
		if (parameters == null)
			parameters = new Hashtable<String, ProcessInfoParameter>();
		ProcessInfoParameter parameter = new ProcessInfoParameter(name, value, null, info, null);
		parameters.put(name, parameter);
	}

	// metas: begin
	/** Org_ID        				*/
	private Integer 		orgId = -1; //metas: c.ghita@metas.ro
	/**
	 * Method getAD_Org_ID
	 * @return Integer
	 */
	//metas: c.ghita@metas.ro
	public Integer getAD_Org_ID()
	{
		if (orgId == -1)
			return Env.getAD_Org_ID(Env.getCtx());
		return orgId;
	}

	/**
	 * Method setAD_Org_ID
	 * @param orgId int
	 */
	//metas: c.ghita@metas.ro
	public void setAD_Org_ID (int orgId)
	{
		this.orgId = new Integer (orgId);
	}
// metas: end

	//metas: t.schoeneberg@metas.de
	//03152
	/**
	 * If the process has failed with a Throwable, that Throwable can be retrieved using this getter.
	 * 
	 * @return
	 */
	public Throwable getThrowable()
	{
		return throwable;
	}

	public void setThrowable(Throwable t)
	{
		this.throwable = t;
	}
	// metas: end
	
	//metas: cg
	//03040
	/**
	 * @return the window No
	 */
	public int getWindowNo()
	{
		return windowNo;
	}

	/**
	 * @param window No the window No to set
	 */
	public void setWindowNo(int windowNo)
	{
		this.windowNo = windowNo;
	}

	private int          		windowNo = 0;
	// metas end
	
	
	// metas: cg
	// mo73_03685
	/**
	 * @return the m_whereClause
	 */
	public String getWhereClause()
	{
		return whereClause;
	}

	/**
	 * @param whereClause
	 *            the whereClause to set
	 */
	public void setWhereClause(String whereClause)
	{
		this.whereClause = whereClause;
	}

	private String whereClause = "";
	// metas end

	public void setManagedTransaction(boolean managedTransaction)
	{
		this.managedTransaction = managedTransaction;
	}

	public boolean isManagedTransaction()
	{
		return managedTransaction;
	}
	
	/**
	 * Get Parameter from Name
	 * @param parameterName
	 * @return ProcessInfoParameter
	 * FR [ 325 ]
	 */
	public ProcessInfoParameter getInfoParameter(String parameterName) {
		//	Valid null
		if(parameters == null)
			return null;
		//	Default
		return parameters.get(parameterName);
	}
	
	/**
	 * Get a Parameter from Name
	 * @param parameterName
	 * @return Object with parameter value
	 * FR [ 325 ]
	 */
	public Object getParameter(String parameterName) {
		ProcessInfoParameter parameter = getInfoParameter(parameterName);
		//	For null
		if(parameter == null)
			return null;
		//	Default
		return parameter.getParameter();
	}
	
	/**
	 * Get a parameter like BigDecimal from Name
	 * @param parameterName
	 * @return BigDecimal with value
	 * FR [ 325 ]
	 */
	public BigDecimal getParameterAsBigDecimal(String parameterName) {
		ProcessInfoParameter parameter = getInfoParameter(parameterName);
		//	For null
		if(parameter == null)
			return null;
		//	Default
		return parameter.getParameterAsBigDecimal();
	}
	
	/**
	 * Get a parameter like boolean from Name
	 * @param parameterName
	 * @return boolean with value
	 * FR [ 325 ]
	 */
	public boolean getParameterAsBoolean(String parameterName) {
		ProcessInfoParameter parameter = getInfoParameter(parameterName);
		//	For null
		if(parameter == null)
			return false;
		//	Default
		return parameter.getParameterAsBoolean();
	}
	
	/**
	 * Get a parameter like int from Name
	 * @param parameterName
	 * @return int with value
	 * FR [ 325 ]
	 */
	public int getParameterAsInt(String parameterName) {
		ProcessInfoParameter parameter = getInfoParameter(parameterName);
		//	For null
		if(parameter == null)
			return 0;
		//	Default
		return parameter.getParameterAsInt();
	}
	
	/**
	 * Get a parameter like String from Name
	 * @param parameterName
	 * @return String with value
	 * FR [ 325 ]
	 */
	public String getParameterAsString(String parameterName) {
		ProcessInfoParameter parameter = getInfoParameter(parameterName);
		//	For null
		if(parameter == null)
			return null;
		//	Default
		return parameter.getParameterAsString();
	}
	
	/**
	 * Get a parameter like Timestamp from Name
	 * @param parameterName
	 * @return Timestamp with value
	 * FR [ 325 ]
	 */
	public Timestamp getParameterAsTimestamp(String parameterName) {
		ProcessInfoParameter parameter = getInfoParameter(parameterName);
		//	For null
		if(parameter == null)
			return null;
		//	Default
		return parameter.getParameterAsTimestamp();
	}
	
	/**
	 * Get a Parameter To from Name
	 * @param parameterName
	 * @return Object with parameter value
	 * FR [ 325 ]
	 */
	public Object getParameterTo(String parameterName) {
		ProcessInfoParameter parameter = getInfoParameter(parameterName);
		//	For null
		if(parameter == null)
			return null;
		//	Default
		return parameter.getParameter_To();
	}
	
	/**
	 * Get a parameter to like BigDecimal from Name
	 * @param parameterName
	 * @return BigDecimal with value
	 * FR [ 325 ]
	 */
	public BigDecimal getParameterToAsBigDecimal(String parameterName) {
		ProcessInfoParameter parameter = getInfoParameter(parameterName);
		//	For null
		if(parameter == null)
			return null;
		//	Default
		return parameter.getParameterToAsBigDecimal();
	}
	
	/**
	 * Get a parameter to like boolean from Name
	 * @param parameterName
	 * @return boolean with value
	 * FR [ 325 ]
	 */
	public boolean getParameterToAsBoolean(String parameterName) {
		ProcessInfoParameter parameter = getInfoParameter(parameterName);
		//	For null
		if(parameter == null)
			return false;
		//	Default
		return parameter.getParameter_ToAsBoolean();
	}
	
	/**
	 * Get a parameter to like int from Name
	 * @param parameterName
	 * @return int with value
	 * FR [ 325 ]
	 */
	public int getParameterToAsInt(String parameterName) {
		ProcessInfoParameter parameter = getInfoParameter(parameterName);
		//	For null
		if(parameter == null)
			return 0;
		//	Default
		return parameter.getParameter_ToAsInt();
	}
	
	/**
	 * Get a parameter to like String from Name
	 * @param parameterName
	 * @return String with value
	 * FR [ 325 ]
	 */
	public String getParameterToAsString(String parameterName) {
		ProcessInfoParameter parameter = getInfoParameter(parameterName);
		//	For null
		if(parameter == null)
			return null;
		//	Default
		return parameter.getParameterToAsString();
	}
	
	/**
	 * Get a parameter like Timestamp from Name
	 * @param parameterName
	 * @return Timestamp with value
	 * FR [ 325 ]
	 */
	public Timestamp getParameterToAsTimestamp(String parameterName) {
		ProcessInfoParameter parameter = getInfoParameter(parameterName);
		//	For null
		if(parameter == null)
			return null;
		//	Default
		return parameter.getParameterToAsTimestamp();
	}
	
	/***************************************************
	 * Get Selection Values                            *                            
	 * FR [ 352 ]                                      *
	 ***************************************************/
	
	/**
	 * Get a value of selection from a key
	 * @param key
	 * @param columnName
	 * @return
	 */
	public Object getSelection(int key, String columnName) {
		if(selection != null) {
			LinkedHashMap<String, Object> record = selection.get(key);
			if(record != null) {
				return record.get(columnName);
			}
		}
		//	Default
		return null;
	}
	
	/**
	 * Get a selection value like BigDecimal from key and column name
	 * @param key
	 * @param columnName
	 * @return BigDecimal with value
	 * FR [ 352 ]
	 */
	public BigDecimal getSelectionAsBigDecimal(int key, String columnName) {
		Object retValue = getSelection(key, columnName);
		//	For null
		if(retValue == null)
			return null;
		if(retValue instanceof BigDecimal)
			return (BigDecimal) retValue;
		//	Default
		return null;
	}
	
	/**
	 * Get a selection value like boolean from key and column name
	 * @param key
	 * @param columnName
	 * @return boolean with value
	 * FR [ 352 ]
	 */
	public boolean getSelectionAsBoolean(int key, String columnName) {
		Object retValue = getSelection(key, columnName);
		//	For null
		if(retValue == null)
			return false;
		if(retValue instanceof Boolean)
			return (Boolean) retValue;
		//	Default
		return false;
	}
	
	/**
	 * Get a selection value like int from key and column name
	 * @param key
	 * @param columnName
	 * @return int with value
	 * FR [ 352 ]
	 */
	public int getSelectionAsInt(int key, String columnName) {
		Object retValue = getSelection(key, columnName);
		//	For null
		if(retValue == null)
			return 0;
		if(retValue instanceof Number)
			return ((Number) retValue).intValue();
		//	Default
		return 0;
	}
	
	/**
	 * Get a selection value like String from key and column name
	 * @param key
	 * @param columnName
	 * @return String with value
	 * FR [ 352 ]
	 */
	public String getSelectionAsString(int key, String columnName) {
		Object retValue = getSelection(key, columnName);
		//	For null
		if(retValue == null)
			return null;
		if(retValue instanceof String)
			return (String) retValue;
		//	Default
		return null;
	}
	
	/**
	 * Get a selection value like Timestamp from key and column name
	 * @param key
	 * @param columnName
	 * @return Timestamp with value
	 * FR [ 352 ]
	 */
	public Timestamp getSelectionAsTimestamp(int key, String columnName) {
		Object retValue = getSelection(key, columnName);
		//	For null
		if(retValue == null)
			return null;
		if(retValue instanceof Timestamp)
			return (Timestamp) retValue;
		//	Default
		return null;
	}

	public String getTableName()
	{
		return MTable.getTableName(Env.getCtx(), getTable_ID());
	}

	public String getTableNameSelection()
	{
		return MTable.getTableName(Env.getCtx(), getTableSelectionId());
	}
	
}   //  ProcessInfo
