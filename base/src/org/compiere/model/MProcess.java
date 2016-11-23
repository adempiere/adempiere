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
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.util.ProcessUtil;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.wf.MWFNode;

/**
 *  Process Model
 *
 *  @author Jorg Janke
 *  @version $Id: MProcess.java,v 1.4 2006/07/30 00:58:04 jjanke Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>BF [ 1757523 ] Server Processes are using Server's context
 * 			<li>FR [ 2214883 ] Remove SQL code and Replace for Query
 * @contributor Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *    <a href="https://github.com/adempiere/adempiere/issues/566">
 *    @see FR [ 566 ] Process parameter don't have a parameter like only information</a>
 *  @contributor Raul Munoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
 *   <li> FR [ 566 ] Add validation parameter get Only Active Records
 *    
 */
public class MProcess extends X_AD_Process
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6665942554198058466L;

	/**
	 * Get based on java class
	 * @param processClass
	 * @return
     */
	public static MProcess getUsingJavaClass(final Class<?> processClass)
	{
		String className = processClass.getCanonicalName();
		if (className == null)
			return null;

		return new Query(Env.getCtx() , MProcess.Table_Name , MProcess.COLUMNNAME_Classname + "=?" , null)
						.setParameters(className)
						.first();
	}

	/**
	 * 	Get MProcess from Cache
	 *	@param ctx context
	 *	@param AD_Process_ID id
	 *	@return MProcess
	 */
	public static MProcess get (Properties ctx, int AD_Process_ID)
	{
		Integer key = new Integer (AD_Process_ID);
		MProcess retValue = (MProcess) s_cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MProcess (ctx, AD_Process_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get
	
	/**
	 * Get Process From Instance
	 * @param ctx
	 * @param processInstanceId
	 * @return
	 */
	public static MProcess getFromInstance(Properties ctx, int processInstanceId) {
		return new Query(Env.getCtx() , Table_Name , 
				"EXISTS(SELECT 1 FROM AD_PInstance pi "
				+ "WHERE pi.AD_Process_ID = AD_Process.AD_Process_ID "
				+ "AND pi.AD_PInstance_ID = ?)", null)
			.setParameters(processInstanceId)
			.first();
	}
	
	/**
	 * 	Get MProcess from Menu
	 *	@param ctx context
	 *	@param AD_Menu_ID id
	 *	@return MProcess or null
	 */
	public static MProcess getFromMenu (Properties ctx, int AD_Menu_ID)
	{
		final String whereClause = "EXISTS (SELECT 1 FROM AD_Menu m"
							+" WHERE m.AD_Process_ID=AD_Process.AD_Process_ID AND m.AD_Menu_ID=?)";
		MProcess p = new Query(ctx, I_AD_Process.Table_Name, whereClause, null)
			.setParameters(AD_Menu_ID)
			.firstOnly();
		if (p != null)
		{
			s_cache.put (p.get_ID(), p);
		}
		return p;
	}	//	getFromMenu


	/**	Cache						*/
	private static CCache<Integer,MProcess>	s_cache	= new CCache<Integer,MProcess>(Table_Name, 20);
	/**	Cache for parameters		*/
	private static CCache<String, MProcessPara[]>	s_cacheASPParameters = new CCache<String, MProcessPara[]>(I_AD_Process_Para.Table_Name, 20);
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Process_ID process
	 *	@param trxName transaction name
	 */
	public MProcess (Properties ctx, int AD_Process_ID, String trxName)
	{
		super (ctx, AD_Process_ID, trxName);
		if (AD_Process_ID == 0)
		{
		//	setValue (null);
		//	setName (null);
			setIsReport (false);
			setIsServerProcess(false);
			setAccessLevel (ACCESSLEVEL_All);
			setEntityType (ENTITYTYPE_UserMaintained);
			setIsBetaFunctionality(false);
		}
	}	//	MProcess

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction name
	 */
	public MProcess (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MProcess


	/**	Parameters					*/
	private MProcessPara[]		m_parameters = null;
	
	/**
	 * 	Get Parameters
	 *	@return parameters
	 */
	public MProcessPara[] getParameters() {
		if (m_parameters != null)
			return m_parameters;
		//
		m_parameters = getParameters(null);
		//
		return m_parameters;
	}	//	getParameters
	
	/**
	 * Get ASP Parameter (make a query if it is not exists in Cache)
	 * @return
	 */
	public MProcessPara[] getASPParameters() {
		MClient client = MClient.get(Env.getCtx());
		String key = getAD_Process_ID() + "|" + client.getAD_Client_ID();
		MProcessPara[] retValue = s_cacheASPParameters.get (key);
		if (retValue != null)
			return retValue;
		//	Get where clause
		String ASPFilter = null;
		if (client.isUseASP()) {
			ASPFilter = 
				//	Just ASP subscribed process parameters for client "
				"("
				+ "	EXISTS(SELECT 1 FROM ASP_Process p "
				+ "					INNER JOIN ASP_Process_Para pp ON(pp.ASP_Process_ID = p.ASP_Process_ID) "
				+ "					INNER JOIN ASP_Level l ON(l.ASP_Level_ID = p.ASP_Level_ID) "
				+ "					INNER JOIN ASP_ClientLevel cl ON(cl.ASP_Level_ID = l.ASP_Level_ID) "
				+ "				WHERE pp.AD_Process_Para_ID = AD_Process_Para.AD_Process_Para_ID "
				+ "				AND cl.AD_Client_ID = " + client.getAD_Client_ID()
				+ "				AND pp.IsActive = 'Y' "
				+ "				AND p.IsActive = 'Y' "
				+ "				AND l.IsActive = 'Y' "
				+ "				AND cl.IsActive = 'Y' "
				+ "				AND pp.ASP_Status = 'S') "	//	Show
				+ "OR "
				//	+ show ASP exceptions for client
				+ "	EXISTS(SELECT 1 FROM ASP_ClientException ce "
				+ "				WHERE ce.AD_Process_Para_ID = AD_Process_Para.AD_Process_Para_ID "
				+ "				AND ce.AD_Client_ID = " + client.getAD_Client_ID()
				+ "				AND ce.IsActive = 'Y' "
				+ "				AND ce.AD_Process_Para_ID IS NOT NULL "
				+ "				AND ce.AD_Tab_ID IS NULL "
				+ "				AND ce.AD_Field_ID IS NULL "
				+ "				AND ce.ASP_Status = 'S')"	//	Show
				+ ") "
				//	minus hide ASP exceptions for client
				+ "AND EXISTS(SELECT 1 FROM ASP_ClientException ce "
				+ "				WHERE ce.AD_Process_Para_ID = AD_Process_Para.AD_Process_Para_ID "
				+ "				AND ce.AD_Client_ID = " + client.getAD_Client_ID()
				+ "				AND ce.IsActive = 'Y' "
				+ "				AND ce.AD_Process_Para_ID IS NOT NULL "
				+ "				AND ce.AD_Tab_ID IS NULL "
				+ "				AND ce.AD_Field_ID IS NULL "
				+ "				AND ce.ASP_Status = 'H')";	//	Hide
		}
		retValue = getParameters(ASPFilter);
		if (retValue.length != 0)
			s_cacheASPParameters.put(key, retValue);
		//	Default Return
		return retValue;
	}
	
	/**
	 * Get Parameter with optional where clause
	 * @param optionalWhereClause
	 * @return
	 */
	public MProcessPara[] getParameters(String optionalWhereClause) {
		MProcessPara[] retValue = null;
		StringBuffer whereClause = new StringBuffer(MProcessPara.COLUMNNAME_AD_Process_ID + "=?");
		
		//	Validate where
		if(optionalWhereClause != null
				&& optionalWhereClause.trim().length() > 0)
			whereClause.append(" AND ").append(optionalWhereClause);
		//	
		List<MProcessPara> list = new Query(getCtx(), I_AD_Process_Para.Table_Name, whereClause.toString(), get_TrxName())
			.setParameters(get_ID())
			.setOnlyActiveRecords(true)
			.setOrderBy(MProcessPara.COLUMNNAME_SeqNo)
			.list();
		//
		retValue = new MProcessPara[list.size()];
		list.toArray(retValue);
		//	Default Return
		return retValue;
	}

	/**
	 * 	Get Parameter with ColumnName
	 *	@param name column name
	 *	@return parameter or null
	 */
	public MProcessPara getParameter(String name)
	{
		getParameters();
		for (int i = 0; i < m_parameters.length; i++)
		{
			if (m_parameters[i].getColumnName().equals(name))
				return m_parameters[i];
		}
		return null;
	}	//	getParameter
	
	
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MProcess[")
			.append (get_ID())
			.append("-").append(getName())
			.append ("]");
		return sb.toString ();
	}	//	toString

	
	/**************************************************************************
	 * 	Process w/o parameter
	 *	@param Record_ID record
	 *	@param trx transaction
	 *	@return Process Instance
	 */
	public MPInstance processIt (int Record_ID, Trx trx)
	{
		return processIt(Record_ID, trx, true);
	}

	/**************************************************************************
	 * 	Process w/o parameter
	 *	@param Record_ID record
	 *	@param trx transaction
	 *	@return Process Instance
	 */
	public MPInstance processIt (int Record_ID, Trx trx, boolean managedTrx)
	{
		MPInstance pInstance = new MPInstance (this, Record_ID);
		//	Lock
		pInstance.setIsProcessing(true);
		pInstance.saveEx();

		boolean ok = true;

		ProcessInfo processInfo = new ProcessInfo("", this.getAD_Process_ID());
		processInfo.setAD_PInstance_ID(pInstance.getAD_PInstance_ID());
		processInfo.setRecord_ID( Record_ID ); //@Trifon - pass Record_Id to ProcessInfo class
		ok = processIt(processInfo, trx, managedTrx);

		//	Unlock
		pInstance.setResult(ok ? MPInstance.RESULT_OK : MPInstance.RESULT_ERROR);
		pInstance.setErrorMsg(processInfo.getSummary());
		pInstance.setIsProcessing(false);
		pInstance.saveEx();
		//
		pInstance.log();
		return pInstance;
	}	//	process

	/**
	 * 	Process It (sync)
	 *	@param pi Process Info
	 *	@param trx transaction
	 *	@return true if OK
	 */
	public boolean processIt (ProcessInfo pi, Trx trx)
	{
		return processIt(pi, trx, true);
	}

	/**
	 * 	Process It (sync)
	 *	@param pi Process Info
	 *	@param trx transaction
	 *	@return true if OK
	 */
	public boolean processIt (ProcessInfo pi, Trx trx, boolean managedTrx)
	{
		if (pi.getAD_PInstance_ID() == 0)
		{
			MPInstance pInstance = new MPInstance (this, pi.getRecord_ID());
			//	Lock
			pInstance.setIsProcessing(true);
			pInstance.saveEx();
		}

		boolean ok = false;

		//	Java Class
		String Classname = getClassname();
		if (Classname != null && Classname.length() > 0)
		{
			pi.setClassName(Classname);
			ok = startClass(pi, trx, managedTrx);
		}
		else
		{
			//	PL/SQL Procedure
			String ProcedureName = getProcedureName();
			if (ProcedureName != null && ProcedureName.length() > 0)
			{
				ok = startProcess (ProcedureName, pi, trx, managedTrx);
			}
			else
			{
				// BF3038385, ADEMPIERE-50
				if (this.isReport()) {
					ok = true;
				}
				else {
					String msg = "No Classname or ProcedureName for " + getName();
					pi.setSummary(msg, ok);
					log.warning(msg);
				}
			}
		}
		return ok;
	}	//	process

	/**
	 * 	Is this a Java Process
	 *	@return true if java process
	 */
	public boolean isJavaProcess()
	{
		String Classname = getClassname();
		return (Classname != null && Classname.length() > 0);
	}	//	is JavaProcess
	
	/**
	 * Is a Jasper Process
	 * @return
	 */
	public boolean isJasper() {
		return getJasperReport() != null && getJasperReport().trim().length() > 0;
	}
	
	/**
	 *  Start Database Process
	 *  @param ProcedureName PL/SQL procedure name
	 *  @param pInstance process instance
	 *  @param managedTrx false if trx is managed by caller
	 *	see ProcessCtl.startProcess
	 *  @return true if success
	 */
	private boolean startProcess (String ProcedureName, ProcessInfo processInfo, Trx trx, boolean managedTrx)
	{
		int AD_PInstance_ID = processInfo.getAD_PInstance_ID();
		//  execute on this thread/connection
		log.info(ProcedureName + "(" + AD_PInstance_ID + ")");
		
		return ProcessUtil.startDatabaseProcedure(processInfo, ProcedureName, trx, managedTrx);
	}   //  startProcess


	/**
	 *  Start Java Class (sync).
	 *      instanciate the class implementing the interface ProcessCall.
	 *  The class can be a Server/Client class (when in Package
	 *  org adempiere.process or org.compiere.model) or a client only class
	 *  (e.g. in org.compiere.report)
	 *
	 *  @param Classname    name of the class to call
	 *  @param pi	process info
	 *  @param trx transaction
	 *  @param managedTrx false if trx is managed by caller
	 *  @return     true if success
	 *	see ProcessCtl.startClass
	 */
	private boolean startClass (ProcessInfo pi, Trx trx, boolean managedTrx)
	{
		log.info(pi.getClassName());
		
		return ProcessUtil.startJavaProcess(getCtx(), pi, trx, managedTrx);
	}   //  startClass

	
	/**
	 * 	Is it a Workflow
	 *	@return true if Workflow
	 */
	public boolean isWorkflow()
	{
		return getAD_Workflow_ID() > 0;
	}	//	isWorkflow
	
	
	/**
	 * 	Update Statistics
	 *	@param seconds sec
	 */
	public void addStatistics (int seconds)
	{
		setStatistic_Count(getStatistic_Count() + 1);
		setStatistic_Seconds(getStatistic_Seconds() + seconds);
	}	//	addStatistics
	
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord)	//	Add to all automatic roles
		{
			MRole[] roles = MRole.getOf(getCtx(), "IsManual='N'");
			for (int i = 0; i < roles.length; i++)
			{
				
				MProcessAccess pa = new MProcessAccess(this, roles[i].getAD_Role_ID());
				pa.saveEx();
			}
		}
		//	Menu/Workflow
		else if (is_ValueChanged("IsActive") || is_ValueChanged("Name") 
			|| is_ValueChanged("Description") || is_ValueChanged("Help"))
		{
			MMenu[] menues = MMenu.get(getCtx(), "AD_Process_ID=" + getAD_Process_ID(), get_TrxName());
			for (int i = 0; i < menues.length; i++)
			{
				menues[i].setIsActive(isActive());
				menues[i].setName(getName());
				menues[i].setDescription(getDescription());
				menues[i].saveEx();
			}
			MWFNode[] nodes = MWindow.getWFNodes(getCtx(), "AD_Process_ID=" + getAD_Process_ID(), get_TrxName());
			for (int i = 0; i < nodes.length; i++)
			{
				boolean changed = false;
				if (nodes[i].isActive() != isActive())
				{
					nodes[i].setIsActive(isActive());
					changed = true;
				}
				if (nodes[i].isCentrallyMaintained())
				{
					nodes[i].setName(getName());
					nodes[i].setDescription(getDescription());
					nodes[i].setHelp(getHelp());
					changed = true;
				}
				if (changed)
					nodes[i].saveEx();
			}
		}
		return success;
	}	//	afterSave
	
	/**
	 * Grant independence to GenerateModel from AD_Process_ID
	 * @param value
	 * @param trxName
	 * @return
	 */
	public static int getProcess_ID(String value, String trxName)
	{
		int retValue = DB.getSQLValueEx(trxName, "SELECT AD_Process_ID FROM AD_Process WHERE Value=?", value);
		return retValue;
	}
	
	/**
	 * Copy settings from another process
	 * overwrites existing data
	 * (including translations)
	 * and saves.
	 * Not overwritten: name, value, entitytype
	 * @param source 
	 */
	public void copyFrom (MProcess source)
	{

		log.log(Level.FINE, "Copying from:" + source + ", to: " + this);
		setAccessLevel(source.getAccessLevel());
		setAD_Form_ID(source.getAD_Form_ID());
		setAD_PrintFormat_ID(source.getAD_PrintFormat_ID());
		setAD_ReportView_ID(source.getAD_ReportView_ID());
		setAD_Workflow_ID(source.getAD_Workflow_ID());
		setClassname(source.getClassname());
		setDescription(source.getDescription());
		setHelp(source.getHelp());
		setIsBetaFunctionality(source.isBetaFunctionality());
		setIsDirectPrint(source.isDirectPrint());
		setIsReport(source.isReport());
		setIsServerProcess(source.isServerProcess());
		setJasperReport(source.getJasperReport());
		setProcedureName(source.getProcedureName());
		setShowHelp(source.getShowHelp());
		
		saveEx();
		
		// copy parameters 
		// TODO? Perhaps should delete existing first?
		MProcessPara[] parameters = source.getParameters();
		for ( MProcessPara sourcePara : parameters )
		{
			MProcessPara targetPara = new MProcessPara(this);
			targetPara.copyFrom (sourcePara);  // saves automatically
		}
	}

	/**
	 * 	Process It without closing the given transaction - used from workflow engine.
	 *	@param pi Process Info
	 *	@param trx transaction
	 *	@return true if OK
	 */
	public boolean processItWithoutTrxClose (ProcessInfo pi, Trx trx)
	{
		return processIt(pi, trx, false);
	}	//	processItWithoutTrxClose
	
	/**
	 * Get Estimated Seconds
	 * @return
	 */
	public int getEstimatedSeconds() {
		if(getStatistic_Count() == 0)
			return 0;
		//	Else
		return getStatistic_Seconds() / getStatistic_Count();
	}
	
}	//	MProcess
