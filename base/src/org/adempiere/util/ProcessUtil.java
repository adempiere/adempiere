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
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL - BF [ 1757523 ]
 */
public final class ProcessUtil {

	private static final String JASPER_STARTER_CLASS = "org.compiere.report.ReportStarter";

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
		Class processClass = null;
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
			process.startProcess(ctx, pi, trx);
			if (trx != null)
			{
				trx.commit(true);
				trx.close();
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

			// Window context are    _
			// Login context  are    __
			// Parameter context are ___
			MRule.setContext(engine, ctx, 0);  // no window
			// now add the process parameters to the engine 
			// Parameter context are ___
			engine.put("___Ctx", ctx);
			if (trx == null)
				trx = Trx.get(pi.getTitle()+"_"+pi.getAD_PInstance_ID(), true);
			engine.put("___Trx", trx);
			engine.put("___TrxName", trx.getTrxName());
			engine.put("___Record_ID", pi.getRecord_ID());
			engine.put("___AD_Client_ID", pi.getAD_Client_ID());
			engine.put("___AD_User_ID", pi.getAD_User_ID());
			engine.put("___AD_PInstance_ID", pi.getAD_PInstance_ID());
			engine.put("___Table_ID", pi.getTable_ID());
			// Add process parameters
			ProcessInfoParameter[] para = pi.getParameter();
			if (para == null) {
				ProcessInfoUtil.setParameterFromDB(pi);
				para = pi.getParameter();
			}
			if (para != null) {
				engine.put("___Parameter", pi.getParameter());
				for (int i = 0; i < para.length; i++)
				{
					String name = para[i].getParameterName();
					if (para[i].getParameter_To() == null) {
						Object value = para[i].getParameter();
						if (name.endsWith("_ID") && (value instanceof BigDecimal))
							engine.put("___" + name, ((BigDecimal)value).intValue());
						else
							engine.put("___" + name, value);
					} else {
						Object value1 = para[i].getParameter();
						Object value2 = para[i].getParameter_To();
						if (name.endsWith("_ID") && (value1 instanceof BigDecimal))
							engine.put("___" + name + "1", ((BigDecimal)value1).intValue());
						else
							engine.put("___" + name + "1", value1);
						if (name.endsWith("_ID") && (value2 instanceof BigDecimal))
							engine.put("___" + name + "2", ((BigDecimal)value2).intValue());
						else
							engine.put("___" + name + "2", value2);
					}
				}
			}
			engine.put("___ProcessInfo", pi);
		
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
			wfProcess = wf.start(pi);		//	may return null
		else
			wfProcess = wf.startWait(pi);	//	may return null
		log.fine(pi.toString());
		return wfProcess;
	}
}