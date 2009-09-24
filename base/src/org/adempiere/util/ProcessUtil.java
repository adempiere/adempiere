package org.adempiere.util;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import javax.script.ScriptEngine;

import org.compiere.model.MProcess;
import org.compiere.model.MRule;
import org.compiere.process.ProcessCall;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.wf.MWFProcess;
import org.compiere.wf.MWorkflow;

/**
 * 
 * @author Low Heng Sin
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>BF [ 1757523 ] Server Processes are using Server's context
 * 				<li>BF [ 2528297 ] Poor error message on jasper fail
 * 				<li>BF [ 2530847 ] Report is displayed even if java process fails 
 */
public final class ProcessUtil {

	public static final String JASPER_STARTER_CLASS = "org.compiere.report.ReportStarter";

	/**	Logger				*/
	private static CLogger log = CLogger.getCLogger(ProcessUtil.class);
	
	private ProcessUtil() {}
	
	public static boolean startDatabaseProcedure (ProcessInfo processInfo, String ProcedureName, Trx trx) {
		String sql = "{call " + ProcedureName + "(?)}";
		String trxName = trx != null ? trx.getTrxName() : null;
		try
		{
			//hengsin, add trx support, updateable support.
			CallableStatement cstmt = DB.prepareCall(sql, ResultSet.CONCUR_UPDATABLE, trxName);	
			cstmt.setInt(1, processInfo.getAD_PInstance_ID());
			cstmt.executeUpdate();
			cstmt.close();
			if (trx != null && trx.isActive())
			{
				trx.commit(true);
				trx.close();
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
			if (trx != null && trx.isActive())
			{
				trx.rollback();
				trx.close();
			}
			processInfo.setSummary (Msg.getMsg(Env.getCtx(), "ProcessRunError") + " " + e.getLocalizedMessage());
			processInfo.setError (true);
			return false;
		}
		return true;
	}
	
	@Deprecated
	public static boolean startJavaProcess(ProcessInfo pi, Trx trx) {
		return startJavaProcess(Env.getCtx(), pi, trx);
	}
	
	public static boolean startJavaProcess(Properties ctx, ProcessInfo pi, Trx trx) {
		String className = pi.getClassName();
		if (className == null) {
			MProcess proc = new MProcess(ctx, pi.getAD_Process_ID(), trx.getTrxName());
			if (proc.getJasperReport() != null)
				className = JASPER_STARTER_CLASS;
		}
		//Get Class
		Class<?> processClass = null;
		//use context classloader if available
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null)
			classLoader = ProcessUtil.class.getClassLoader();
		try
		{
			processClass = classLoader.loadClass(className);
		}
		catch (ClassNotFoundException ex)
		{
			log.log(Level.WARNING, className, ex);
			pi.setSummary ("ClassNotFound", true);
			return false;
		}
		
		//Get Process
		ProcessCall process = null;
		try
		{
			process = (ProcessCall)processClass.newInstance();
		}
		catch (Exception ex)
		{
			log.log(Level.WARNING, "Instance for " + className, ex);
			pi.setSummary ("InstanceError", true);
			return false;
		}
		
		if (processClass == null) {
			pi.setSummary("No Instance for " + pi.getClassName(), true);
			return false;
		}
		
		boolean success = false;
		try
		{
			success = process.startProcess(ctx, pi, trx);
			if (success && trx != null)
			{
				trx.commit(true);
				trx.close();
				trx = null;
			}
		}
		catch (Exception e)
		{
			pi.setSummary (Msg.getMsg(Env.getCtx(), "ProcessError") + " " + e.getLocalizedMessage(), true);
			log.log(Level.SEVERE, pi.getClassName(), e);
			return false;
		}
		finally
		{
			if (trx != null)
			{
				trx.rollback();
				trx.close();
				trx = null;
			}
		}
		return success;
	}
	
	public static boolean startScriptProcess(Properties ctx, ProcessInfo pi, Trx trx) {
		String msg = null;
		boolean success = true;
		try 
		{
			String cmd = pi.getClassName();
			MRule rule = MRule.get(ctx, cmd.substring(MRule.SCRIPT_PREFIX.length()));
			if (rule == null) {
				log.log(Level.WARNING, cmd + " not found");
				pi.setSummary ("ScriptNotFound", true);
				return false;
			}
			if ( !  (rule.getEventType().equals(MRule.EVENTTYPE_Process) 
				  && rule.getRuleType().equals(MRule.RULETYPE_JSR223ScriptingAPIs))) {
				log.log(Level.WARNING, cmd + " must be of type JSR 223 and event Process");
				pi.setSummary ("ScriptNotFound", true);
				return false;
			}

			ScriptEngine engine = rule.getScriptEngine();

			// Window context are    W_
			// Login context  are    G_
			// Method arguments context are A_
			// Parameter context are P_
			MRule.setContext(engine, ctx, 0);  // no window
			// now add the method arguments to the engine 
			engine.put(MRule.ARGUMENTS_PREFIX + "Ctx", ctx);
			if (trx == null)
				trx = Trx.get(pi.getTitle()+"_"+pi.getAD_PInstance_ID(), true);
			engine.put(MRule.ARGUMENTS_PREFIX + "Trx", trx);
			engine.put(MRule.ARGUMENTS_PREFIX + "TrxName", trx.getTrxName());
			engine.put(MRule.ARGUMENTS_PREFIX + "Record_ID", pi.getRecord_ID());
			engine.put(MRule.ARGUMENTS_PREFIX + "AD_Client_ID", pi.getAD_Client_ID());
			engine.put(MRule.ARGUMENTS_PREFIX + "AD_User_ID", pi.getAD_User_ID());
			engine.put(MRule.ARGUMENTS_PREFIX + "AD_PInstance_ID", pi.getAD_PInstance_ID());
			engine.put(MRule.ARGUMENTS_PREFIX + "Table_ID", pi.getTable_ID());
			// Add process parameters
			ProcessInfoParameter[] para = pi.getParameter();
			if (para == null) {
				ProcessInfoUtil.setParameterFromDB(pi);
				para = pi.getParameter();
			}
			if (para != null) {
				engine.put(MRule.ARGUMENTS_PREFIX + "Parameter", pi.getParameter());
				for (int i = 0; i < para.length; i++)
				{
					String name = para[i].getParameterName();
					if (para[i].getParameter_To() == null) {
						Object value = para[i].getParameter();
						if (name.endsWith("_ID") && (value instanceof BigDecimal))
							engine.put(MRule.PARAMETERS_PREFIX + name, ((BigDecimal)value).intValue());
						else
							engine.put(MRule.PARAMETERS_PREFIX + name, value);
					} else {
						Object value1 = para[i].getParameter();
						Object value2 = para[i].getParameter_To();
						if (name.endsWith("_ID") && (value1 instanceof BigDecimal))
							engine.put(MRule.PARAMETERS_PREFIX + name + "1", ((BigDecimal)value1).intValue());
						else
							engine.put(MRule.PARAMETERS_PREFIX + name + "1", value1);
						if (name.endsWith("_ID") && (value2 instanceof BigDecimal))
							engine.put(MRule.PARAMETERS_PREFIX + name + "2", ((BigDecimal)value2).intValue());
						else
							engine.put(MRule.PARAMETERS_PREFIX + name + "2", value2);
					}
				}
			}
			engine.put(MRule.ARGUMENTS_PREFIX + "ProcessInfo", pi);
		
			msg = engine.eval(rule.getScript()).toString();
			//transaction should rollback if there are error in process
			if ("@Error@".equals(msg))
				success = false;
			
			//	Parse Variables
			msg = Msg.parseTranslation(ctx, msg);
			pi.setSummary (msg, !success);
			
		}
		catch (Exception e)
		{
			pi.setSummary("ScriptError", true);
			log.log(Level.SEVERE, pi.getClassName(), e);
			success = false;
		}
		if (success) {
			if (trx != null)
			{
				try 
				{
					trx.commit(true);
				} catch (Exception e)
				{
					log.log(Level.SEVERE, "Commit failed.", e);
					pi.addSummary("Commit Failed.");
					pi.setError(true);
					success = false;
				}
				trx.close();
			}
		} else {
			if (trx != null)
			{
				trx.rollback();
				trx.close();
			}
		}
		return success;
	}
	
	public static MWFProcess startWorkFlow(Properties ctx, ProcessInfo pi, int AD_Workflow_ID) {
		MWorkflow wf = MWorkflow.get (ctx, AD_Workflow_ID);
		MWFProcess wfProcess = null;
		if (pi.isBatch())
			wfProcess = wf.start(pi, pi.getTransactionName());		//	may return null
		else {
			wfProcess = wf.startWait(pi);	//	may return null
		}
		log.fine(pi.toString());
		return wfProcess;
	}

	/**
	 * Start a java process without closing the given transaction. Is used from the workflow engine.
	 * @param ctx
	 * @param pi
	 * @param trx
	 * @return
	 */
	public static boolean startJavaProcessWithoutTrxClose(Properties ctx, ProcessInfo pi, Trx trx) {
		String className = pi.getClassName();
		if (className == null) {
			MProcess proc = new MProcess(ctx, pi.getAD_Process_ID(), trx.getTrxName());
			if (proc.getJasperReport() != null)
				className = JASPER_STARTER_CLASS;
		}
		//Get Class
		Class<?> processClass = null;
		try
		{
			processClass = Class.forName (className);
		}
		catch (ClassNotFoundException ex)
		{
			log.log(Level.WARNING, className, ex);
			pi.setSummary ("ClassNotFound", true);
			return false;
		}
		
		//Get Process
		ProcessCall process = null;
		try
		{
			process = (ProcessCall)processClass.newInstance();
		}
		catch (Exception ex)
		{
			log.log(Level.WARNING, "Instance for " + className, ex);
			pi.setSummary ("InstanceError", true);
			return false;
		}
		
		if (processClass == null) {
			pi.setSummary("No Instance for " + pi.getClassName(), true);
			return false;
		}
		
		try
		{
			boolean success = process.startProcess(ctx, pi, trx);
			if (trx != null)
			{
				if(success){
//
				} else {
					trx.rollback();
					trx.close();
					return false;
				}	
			}
		}
		catch (Exception e)
		{
			if (trx != null)
			{
				trx.rollback();
				trx.close();
			}
			pi.setSummary("ProcessError", true);
			log.log(Level.SEVERE, pi.getClassName(), e);
			return false;
		}
		return true;
	}
	
	
}