package org.adempiere.util;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.ProcessCall;
import org.compiere.process.ProcessInfo;
import org.compiere.process.SvrProcess;
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
