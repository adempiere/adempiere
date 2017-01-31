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

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

/**
 *  Server Process Template
 *
 *  @author     Jorg Janke
 *  @version    $Id: SvrProcess.java,v 1.4 2006/08/10 01:00:44 jjanke Exp $
 *  
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>FR [ 1646891 ] SvrProcess - post process support
 * 			<li>BF [ 1877935 ] SvrProcess.process should catch all throwables
 * 			<li>FR [ 1877937 ] SvrProcess: added commitEx method
 * 			<li>BF [ 1878743 ] SvrProcess.getAD_User_ID
 *			<li>BF [ 1935093 ] SvrProcess.unlock() is setting invalid result
 *			<li>FR [ 2788006 ] SvrProcess: change access to some methods
 *				https://sourceforge.net/tracker/?func=detail&aid=2788006&group_id=176962&atid=879335
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 244 ] Is Selection flag
 *		@see https://github.com/adempiere/adempiere/issues/244
 *		<li> FR [ 325 ] SvrProcess must handle mandatory error on Process Parameters
 *		@see https://github.com/adempiere/adempiere/issues/325
 *		<li> FR [ 326 ] Process source code generated automatically (Add validation of mandatory parameter)
 *		@see https://github.com/adempiere/adempiere/issues/326
 *		<li>FR [ 352 ] T_Selection is better send to process like a HashMap instead read from disk
 *		@see https://github.com/adempiere/adempiere/issues/352
 * @author Victor Perez , victor.perez@e-evolution.com, http://e-evolution.com
 *
 * @author mckayERP www.mckayERP.com
 * 			<li> #285 Message in SvrProcess can cause null pointer exception. 
 */
public abstract class SvrProcess implements ProcessCall
{
	/**
	 *  Server Process.
	 * 	Note that the class is initiated by startProcess.
	 */
	public SvrProcess()
	{
	//	Env.ZERO.divide(Env.ZERO);
	}   //  SvrProcess

	private Properties 			ctx;
	private ProcessInfo 		processInfo;

	/**	Logger							*/
	protected CLogger			log = CLogger.getCLogger (getClass());

	/**	Is the Object locked			*/
	private boolean 			locked = false;
	/**	Loacked Object					*/
	private PO 					lockedObject = null;
	/** Process Main transaction 		*/
	private Trx 				transaction;

	/**	Common Error Message			*/
	protected static String 	MESSAGE_SaveErrorRowNotFound = "@SaveErrorRowNotFound@";
	protected static String 	MESSAGE_InvalidArguments = "@InvalidArguments@";
	protected static String 	MESSAGE_FillMandatory = "@FillMandatory@";


	/**
	 *  Start the process.
	 *  Calls the abstract methods <code>process</code>.
	 *  It should only return false, if the function could not be performed
	 *  as this causes the process to abort.
	 *
	 *  @param ctx      Context
	 *  @param pi		Process Info
	 *  @return true if the next process should be performed
	 * 	@see org.compiere.process.ProcessCall#startProcess(Properties, ProcessInfo, Trx)
	 */
	public final boolean startProcess (Properties ctx, ProcessInfo pi, Trx trx)
	{
		//  Preparation
		this.ctx = ctx == null ? Env.getCtx() : ctx;
		processInfo = pi;
		transaction = trx;
		//***	Trx
		boolean localTrx = transaction == null;
		if (localTrx)
			transaction = Trx.get(Trx.createTrxName("SvrProcess"), true);
		//
		lock();
		
		boolean success = process();
		//
		if (localTrx)
		{
			if (success)
			{
				try 
				{
					transaction.commit(true);
				} catch (Exception e)
				{
					log.log(Level.SEVERE, "Commit failed.", e);
					processInfo.addSummary("Commit Failed.");
					processInfo.setError(true);
				}
			}
			else
				transaction.rollback();
			transaction.close();
			transaction = null;
		}
		
		unlock();
		
		// outside transaction processing [ teo_sarca, 1646891 ]
		postProcess(!processInfo.isError());
		
		return !processInfo.isError();
	}   //  startProcess
	
	/**************************************************************************
	 *  Process
	 *  @return true if success
	 */
	private boolean process()
	{
		String msg = "";  //#285
		boolean success = true;
		try
		{
			//	FR [ 325 ]
			//	Load Parameters
			getParameter();
			//	FR [ 326 ]
			validateParameter();
			//	Prepare
			prepare();
			msg = doIt();
		}
		catch (Throwable e)
		{
			msg = e.getLocalizedMessage();
			if (msg == null)
				msg = e.toString();
			if (e.getCause() != null)
				log.log(Level.SEVERE, msg, e.getCause());
			else 
				log.log(Level.SEVERE, msg, e);
			success = false;
		//	throw new RuntimeException(e);
		}
		
		//transaction should rollback if there are error in process
		if (msg != null && msg.contains("@Error@")) // #285 msg could be null
			success = false;
		
		//	Parse Variables
		msg = Msg.parseTranslation(ctx, msg);
		processInfo.setSummary (msg, !success);
		
		return success;
	}   //  process
	
	/**
	 * Validate Parameters
	 */
	private void validateParameter() {
		MProcess process = MProcess.get(getCtx(), processInfo.getAD_Process_ID());
		//	No have parameter
		if(process == null)
			return;
		//	
		MProcessPara [] parameters = process.getParameters();
		StringBuffer errorMsg = new StringBuffer();
		//	Loop over parameter, find a mandatory parameter
		for(MProcessPara parameter : parameters) {
			if(parameter.isMandatory() && parameter.isActive()) {
				ProcessInfoParameter infoParameter = getInfoParameter(parameter.getColumnName());
				if(infoParameter == null
						|| infoParameter.getParameter() == null
						|| (DisplayType.isID(parameter.getAD_Reference_ID()) 
								&& (
									(infoParameter.getParameter() instanceof String 
											&& infoParameter.getParameterAsString() == null)
									||
									(infoParameter.getParameter() instanceof Number 
											&& infoParameter.getParameterAsInt() < 0)
								)
							)
						|| (DisplayType.isText(parameter.getAD_Reference_ID()) 
								&& (infoParameter.getParameterAsString() == null 
										|| infoParameter.getParameterAsString().length() == 0))
				) {
					if(errorMsg.length() > 0) {
						errorMsg.append(", ");
					}
					//	
					errorMsg.append("@").append(parameter.getColumnName()).append("@");
				}
			}
		}
		//	throw exception
		if(errorMsg.length() > 0) {
			throw new AdempiereException(MESSAGE_FillMandatory + errorMsg.toString());
		}
	}

	/**
	 *  Prepare - e.g., get Parameters.
	 *  FR [ 325 ]
	 *  Use custom method like
	 *  <pre>
	 *  getParameter(String parameterName);
	 *  getParameterAsBigDecimal(String parameterName);
	 *  getParameterAsBoolean(String parameterName);
	 *  getParameterAsInt(String parameterName);
	 *  getParameterAsString(String parameterName);
	 *  getParameterAsTimestamp(String parameterName);
	 *  getParameterTo(String parameterName);
	 *  getParameterToAsBigDecimal(String parameterName);
	 *  getParameterToAsBoolean(String parameterName);
	 *  getParameterToAsInt(String parameterName);
	 *  getParameterToAsString(String parameterName);
	 *  getParameterToAsTimestamp(String parameterName);
	 *  </pre>
	 *  For a simple Selection based in keys
	 *  <pre>
	 *  getSelectionKeys();
	 *  </pre>
	 *  For Smart Browser
	 *  <pre>
	 *  getSelection(int key, String columnName);
	 *  getSelectionAsBigDecimal(int key, String columnName);
	 *  getSelectionAsBoolean(int key, String columnName);
	 *  getSelectionAsInt(int key, String columnName);
	 *  getSelectionAsString(int key, String columnName);
	 *  getSelectionAsTimestamp(int key, String columnName);
	 *  </pre>
	 *  The old implementation
	 *  <pre>
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("A_Asset_Group_ID"))
				p_A_Asset_Group_ID = para[i].getParameterAsInt();
			else if (name.equals("GuaranteeDate"))
				p_GuaranteeDate = (Timestamp)para[i].getParameter();
			else if (name.equals("AttachAsset"))
				p_AttachAsset = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	 *  </pre>
	 */
	abstract protected void prepare();

	/**
	 *  Perform process.
	 *  @return Message (variables are parsed)
	 *  @throws Exception if not successful e.g.
	 *  throw new AdempiereUserError ("@FillMandatory@  @C_BankAccount_ID@");
	 */
	abstract protected String doIt() throws Exception;

	/**
	 * Post process actions (outside trx).
	 * Please note that at this point the transaction is committed so
	 * you can't rollback.
	 * This method is useful if you need to do some custom work when 
	 * the process complete the work (e.g. open some windows).
	 *  
	 * @param success true if the process was success
	 * @since 3.1.4
	 */
	protected void postProcess(boolean success) {
	}
	
	/**
	 * 	Commit
	 *  @deprecated suggested to use commitEx instead
	 */
	protected void commit()
	{
		if (transaction != null)
			transaction.commit();
	}	//	commit
	
	/**
	 * Commit and throw exception if error
	 * @throws SQLException on commit error
	 */
	protected void commitEx() throws SQLException
	{
		if (transaction != null)
			transaction.commit(true);
	}
	
	/**
	 * 	Rollback
	 */
	protected void rollback()
	{
		if (transaction != null)
			transaction.rollback();
	}	//	rollback
	
	
	/**************************************************************************
	 * 	Lock Object.
	 * 	Needs to be explicitly called. Unlock is automatic.
	 *	@param po object
	 *	@return true if locked
	 */
	protected boolean lockObject (PO po)
	{
		//	Unlock existing
		if (locked || lockedObject != null)
			unlockObject();
		//	Nothing to lock			
		if (po == null)
			return false;
		lockedObject = po;
		locked = lockedObject.lock();
		return locked;
	}	//	lockObject

	/**
	 * 	Is an object Locked?
	 *	@return true if object locked
	 */
	protected boolean isLocked()
	{
		return locked;
	}	//	isLocked

	/**
	 * 	Unlock Object.
	 * 	Is automatically called at the end of process.
	 *	@return true if unlocked or if there was nothing to unlock
	 */
	protected boolean unlockObject()
	{
		boolean success = true;
		if (locked || lockedObject != null)
		{
			success = lockedObject.unlock(null);
		}
		locked = false;
		lockedObject = null;
		return success;
	}	//	unlock


	/**************************************************************************
	 *  Get Process Info
	 *  @return Process Info
	 */
	public ProcessInfo getProcessInfo()
	{
		return processInfo;
	}   //  getProcessInfo

	/**
	 *  Get Properties
	 *  @return Properties
	 */
	public Properties getCtx()
	{
		return ctx;
	}   //  getCtx

	/**
	 *  Get Name/Title
	 *  @return Name
	 */
	protected String getName()
	{
		return processInfo.getTitle();
	}   //  getName

	/**
	 *  Get Process Instance
	 *  @return Process Instance
	 */
	protected int getAD_PInstance_ID()
	{
		return processInfo.getAD_PInstance_ID();
	}   //  getAD_PInstance_ID

	/**
	 *  Get Table_ID
	 *  @return AD_Table_ID
	 */
	protected int getTable_ID()
	{
		return processInfo.getTable_ID();
	}   //  getRecord_ID

	/**
	 *  Get Record_ID
	 *  @return Record_ID
	 */
	protected int getRecord_ID()
	{
		return processInfo.getRecord_ID();
	}   //  getRecord_ID

	/**
	 *  Get AD_User_ID
	 *  @return AD_User_ID of Process owner or -1 if not found
	 */
	protected int getAD_User_ID()
	{
		if (processInfo.getAD_User_ID() == null || processInfo.getAD_Client_ID() == null)
		{
			String sql = "SELECT AD_User_ID, AD_Client_ID FROM AD_PInstance WHERE AD_PInstance_ID=?";
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try
			{
				preparedStatement = DB.prepareStatement(sql, get_TrxName());
				preparedStatement.setInt(1, processInfo.getAD_PInstance_ID());
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next())
				{
					processInfo.setAD_User_ID (resultSet.getInt (1));
					processInfo.setAD_Client_ID (resultSet.getInt(2));
				}
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally {
				DB.close(resultSet, preparedStatement);
				resultSet = null; preparedStatement = null;
			}
		}
		if (processInfo.getAD_User_ID() == null)
			return -1;
		return processInfo.getAD_User_ID().intValue();
	}   //  getAD_User_ID

	/**
	 *  Get AD_User_ID
	 *  @return AD_User_ID of Process owner
	 */
	protected int getAD_Client_ID()
	{
		if (processInfo.getAD_Client_ID() == null)
		{
			getAD_User_ID();	//	sets also Client
			if (processInfo.getAD_Client_ID() == null)
				return 0;
		}
		return processInfo.getAD_Client_ID().intValue();
	}	//	getAD_Client_ID
	
	/**
	 * FR [ 244 ]
	 * Is Selection or is standard process
	 * @return
	 */
	protected boolean isSelection() {
		return processInfo.isSelection();
	}

	/**
	 * Get Selection keys (used just for key without values)
	 * @return
	 */
	public List<Integer> getSelectionKeys() {
		return processInfo.getSelectionKeys();
	}

	/**
	 * Get Selection values from process info
	 * @return
	 */
	public LinkedHashMap<Integer, LinkedHashMap<String, Object>> getSelectionValues() {
		return processInfo.getSelectionValues();
	}

	/**
	 * get instances from selection
	 * @param trxName
	 * @return
	 * @throws AdempiereException
	 */
	public List<?> getInstancesForSelection(String trxName) throws AdempiereException
	{
		return processInfo.getInstancesForSelection(trxName);
	}

	/**
	 * get intance from table id of process info
	 * @param trxName
	 * @return
	 * @throws AdempiereException
	 */
	public PO getInstance(String trxName) throws AdempiereException
	{
		return  processInfo.getInstance(trxName);
	}

	/**************************************************************************
	 * 	Get Parameter
	 *	@return parameter
	 *	FR [ 325 ] Is preferable use any of getParameter(String) method
	 */
	protected ProcessInfoParameter[] getParameter()
	{
		ProcessInfoParameter[] parameter = processInfo.getParameter();
		if (parameter == null)
		{
			ProcessInfoUtil.setParameterFromDB(processInfo);
			parameter = processInfo.getParameter();
		}
		return parameter;
	}	//	getParameter
	
	/**
	 * Get Parameter from Name
	 * @param parameterName
	 * @return ProcessInfoParameter
	 * FR [ 325 ]
	 */
	public ProcessInfoParameter getInfoParameter(String parameterName) {
		return processInfo.getInfoParameter(parameterName);
	}
	
	/**
	 * Get a Parameter from Name
	 * @param parameterName
	 * @return Object with parameter value
	 * FR [ 325 ]
	 */
	public Object getParameter(String parameterName) {
		return processInfo.getParameter(parameterName);
	}
	
	/**
	 * Get a parameter like BigDecimal from Name
	 * @param parameterName
	 * @return BigDecimal with value
	 * FR [ 325 ]
	 */
	public BigDecimal getParameterAsBigDecimal(String parameterName) {
		return processInfo.getParameterAsBigDecimal(parameterName);
	}
	
	/**
	 * Get a parameter like boolean from Name
	 * @param parameterName
	 * @return boolean with value
	 * FR [ 325 ]
	 */
	public boolean getParameterAsBoolean(String parameterName) {
		return processInfo.getParameterAsBoolean(parameterName);
	}
	
	/**
	 * Get a parameter like int from Name
	 * @param parameterName
	 * @return int with value
	 * FR [ 325 ]
	 */
	public int getParameterAsInt(String parameterName) {
		return processInfo.getParameterAsInt(parameterName);
	}
	
	/**
	 * Get a parameter like String from Name
	 * @param parameterName
	 * @return String with value
	 * FR [ 325 ]
	 */
	public String getParameterAsString(String parameterName) {
		return processInfo.getParameterAsString(parameterName);
	}
	
	/**
	 * Get a parameter like Timestamp from Name
	 * @param parameterName
	 * @return Timestamp with value
	 * FR [ 325 ]
	 */
	public Timestamp getParameterAsTimestamp(String parameterName) {
		return processInfo.getParameterAsTimestamp(parameterName);
	}
	
	/**
	 * Get a Parameter To from Name
	 * @param parameterName
	 * @return Object with parameter value
	 * FR [ 325 ]
	 */
	public Object getParameterTo(String parameterName) {
		return processInfo.getParameterTo(parameterName);
	}
	
	/**
	 * Get a parameter to like BigDecimal from Name
	 * @param parameterName
	 * @return BigDecimal with value
	 * FR [ 325 ]
	 */
	public BigDecimal getParameterToAsBigDecimal(String parameterName) {
		return processInfo.getParameterToAsBigDecimal(parameterName);
	}
	
	/**
	 * Get a parameter to like boolean from Name
	 * @param parameterName
	 * @return boolean with value
	 * FR [ 325 ]
	 */
	public boolean getParameterToAsBoolean(String parameterName) {
		return processInfo.getParameterToAsBoolean(parameterName);
	}
	
	/**
	 * Get a parameter to like int from Name
	 * @param parameterName
	 * @return int with value
	 * FR [ 325 ]
	 */
	public int getParameterToAsInt(String parameterName) {
		return processInfo.getParameterToAsInt(parameterName);
	}
	
	/**
	 * Get a parameter to like String from Name
	 * @param parameterName
	 * @return String with value
	 * FR [ 325 ]
	 */
	public String getParameterToAsString(String parameterName) {
		return processInfo.getParameterToAsString(parameterName);
	}
	
	/**
	 * Get a parameter like Timestamp from Name
	 * @param parameterName
	 * @return Timestamp with value
	 * FR [ 325 ]
	 */
	public Timestamp getParameterToAsTimestamp(String parameterName) {
		return processInfo.getParameterToAsTimestamp(parameterName);
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
		return processInfo.getSelection(key, columnName);
	}
	
	/**
	 * Get a selection value like BigDecimal from key and column name
	 * @param key
	 * @param columnName
	 * @return BigDecimal with value
	 * FR [ 352 ]
	 */
	public BigDecimal getSelectionAsBigDecimal(int key, String columnName) {
		return processInfo.getSelectionAsBigDecimal(key, columnName);
	}
	
	/**
	 * Get a selection value like boolean from key and column name
	 * @param key
	 * @param columnName
	 * @return boolean with value
	 * FR [ 352 ]
	 */
	public boolean getSelectionAsBoolean(int key, String columnName) {
		return processInfo.getSelectionAsBoolean(key, columnName);
	}
	
	/**
	 * Get a selection value like int from key and column name
	 * @param key
	 * @param columnName
	 * @return int with value
	 * FR [ 352 ]
	 */
	public int getSelectionAsInt(int key, String columnName) {
		return processInfo.getSelectionAsInt(key, columnName);
	}
	
	/**
	 * Get a selection value like String from key and column name
	 * @param key
	 * @param columnName
	 * @return String with value
	 * FR [ 352 ]
	 */
	public String getSelectionAsString(int key, String columnName) {
		return processInfo.getSelectionAsString(key, columnName);
	}
	
	/**
	 * Get a selection value like Timestamp from key and column name
	 * @param key
	 * @param columnName
	 * @return Timestamp with value
	 * FR [ 352 ]
	 */
	public Timestamp getSelectionAsTimestamp(int key, String columnName) {
		return processInfo.getSelectionAsTimestamp(key, columnName);
	}


	/**************************************************************************
	 *  Add Log Entry
	 *  @param date date or null
	 *  @param id record id or 0
	 *  @param number number or null
	 *  @param msg message or null
	 */
	public void addLog (int id, Timestamp date, BigDecimal number, String msg)
	{
		if (processInfo != null)
			processInfo.addLog(id, date, number, msg);
		log.info(id + " - " + date + " - " + number + " - " + msg);
	}	//	addLog

	/**
	 * 	Add Log
	 *	@param msg message
	 */
	public void addLog (String msg)
	{
		if (msg != null)
			addLog (0, null, null, msg);
	}	//	addLog

	/**************************************************************************
	 * 	Execute function
	 * 	@param className class
	 * 	@param methodName method
	 * 	@param args arguments
	 * 	@return result
	 */
	public Object doIt (String className, String methodName, Object args[])
	{
		try
		{
			Class<?> clazz = Class.forName(className);
			Object object = clazz.newInstance();
			Method[] methods = clazz.getMethods();
			for (int i = 0; i < methods.length; i++)
			{
				if (methods[i].getName().equals(methodName))
					return methods[i].invoke(object, args);
			}
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "doIt", ex);
			throw new RuntimeException(ex);
		}
		return null;
	}	//	doIt

	
	/**************************************************************************
	 *  Lock Process Instance
	 */
	private void lock()
	{
		log.fine("AD_PInstance_ID=" + processInfo.getAD_PInstance_ID());
		try 
		{
			DB.executeUpdate("UPDATE AD_PInstance SET IsProcessing='Y' WHERE AD_PInstance_ID=" 
				+ processInfo.getAD_PInstance_ID(), null);		//	outside trx
		} catch (Exception e)
		{
			log.severe("lock() - " + e.getLocalizedMessage());
		}
	}   //  lock

	/**
	 *  Unlock Process Instance.
	 *  Update Process Instance DB and write option return message
	 */
	private void unlock ()
	{
		try 
		{
			MPInstance instance = new MPInstance (getCtx(), processInfo.getAD_PInstance_ID(), null);
			if (instance.get_ID() == 0)
			{
				log.log(Level.SEVERE, "Did not find PInstance " + processInfo.getAD_PInstance_ID());
				return;
			}
			instance.setIsProcessing(false);
			instance.setResult(!processInfo.isError());
			instance.setErrorMsg(processInfo.getSummary());
			instance.saveEx();
			log.fine(instance.toString());
			
			ProcessInfoUtil.saveLogToDB(processInfo);
		} 
		catch (Exception e)
		{
			log.severe("unlock() - " + e.getLocalizedMessage());
		}
	}   //  unlock

	/**
	 * Return the main transaction of the current process.
	 * @return the transaction name
	 */
	public String get_TrxName()
	{
		if (transaction != null)
			return transaction.getTrxName();
		return null;
	}	//	get_TrxName

	public String getTableName()
	{
		return processInfo.getTableName();
	}

	public String getTableNameSelection()
	{
		return processInfo.getTableNameSelection();
	}

	/**
	 * Get Table selection Id
	 * @return
	 */
	public int getTableSelectionId()
	{
		return processInfo.getTableSelectionId();
	}

	/**
	 * get Alias for Table Selection
	 * @return
	 */
	public String getAliasForTableSelection()
	{
		return processInfo.getAliasForTableSelection();
	}
	/**
	 *
	 * @return
	 */
	public String getPrefixAliasForTableSelection()
	{
		return processInfo.getPrefixAliasForTableSelection();
	}

}   //  SvrProcess
