package org.compiere.print;

import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.model.MProcess;
import org.compiere.model.MQuery;
import org.compiere.model.MTable;
import org.compiere.model.PrintInfo;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.ServerProcessCtl;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;


public class ServerReportCtl {

	/**
	 * Constants used to pass process parameters to Jasper Process
	 */
	public static final String PARAM_PRINTER_NAME = "PRINTER_NAME";
	public static final String PARAM_PRINT_FORMAT = "PRINT_FORMAT";
	public static final String PARAM_PRINT_INFO = "PRINT_INFO";
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (ServerReportCtl.class);

	/**
	 * Start Document Print for Type with specified printer.
	 * @param type
	 * @param customPrintFormat
	 * @param recordId
	 * @param printerName
	 * @param processInfo
     * @return
     */
	public static boolean startDocumentPrint (int type, MPrintFormat customPrintFormat, int recordId, String printerName , ProcessInfo processInfo)
	{
		String trxName;
		if (processInfo != null)
			trxName = processInfo.getTransactionName();
		else
			trxName = null;

		ReportEngine reportEngine = ReportEngine.get (Env.getCtx(), type, recordId , trxName);
		if (reportEngine == null)
		{
			CLogger log = CLogger.getCLogger(ServerReportCtl.class);
			log.warning("NoDocPrintFormat");
			return false;
		}
		if (customPrintFormat!=null) {
			// Use custom print format if available
			reportEngine.setPrintFormat(customPrintFormat);
		}
		
		if (reportEngine.getPrintFormat()!=null)
		{
			MPrintFormat format = reportEngine.getPrintFormat();
			
			// We have a Jasper Print Format
			// ==============================
			if(format.getJasperProcess_ID() > 0)	
			{
				boolean result = runJasperProcess(recordId, reportEngine, true, printerName , processInfo);
				return(result);
			}
			else
			// Standard Print Format (Non-Jasper)
			// ==================================
			{
				// set generated PDF
				if (processInfo != null)
					processInfo.setPDFReport(reportEngine.getPDF());

				createOutput(reportEngine, printerName);
				ReportEngine.printConfirm (type, recordId, trxName);
			}
		}
		return true;
	}	//	StartDocumentPrint
	
	/**
	 * Runs a Jasper process that prints the record
	 *
	 * @param recordId
	 * @param reportEngine
	 * @param isDirectPrint
	 * @param printerName
	 * @param processInfo
     * @return
     */
	public static boolean runJasperProcess(int recordId, ReportEngine reportEngine, boolean isDirectPrint, String printerName, ProcessInfo processInfo) {
		Trx trx;
		if (processInfo != null && processInfo.getTransactionName() != null)
			trx = Trx.get(processInfo.getTransactionName() , false);
		else
			trx = null;
		MPrintFormat format = reportEngine.getPrintFormat();
		ProcessInfo jasperProcessInfo = new ProcessInfo ("", format.getJasperProcess_ID());
		jasperProcessInfo.setPrintPreview( !isDirectPrint );
		MQuery query = reportEngine.getQuery();
		if (query != null )
			recordId = (Integer) query.getCode(0);

		jasperProcessInfo.setRecord_ID(recordId);
		Vector<ProcessInfoParameter> jasperPrintParams = new Vector<ProcessInfoParameter>();
		ProcessInfoParameter pip;
		if (printerName!=null && printerName.trim().length()>0) {
			// Override printer name
			pip = new ProcessInfoParameter(PARAM_PRINTER_NAME, printerName, null, null, null);
			jasperPrintParams.add(pip);
		}
		pip = new ProcessInfoParameter(PARAM_PRINT_FORMAT, format, null, null, null);
		jasperPrintParams.add(pip);
		pip = new ProcessInfoParameter(PARAM_PRINT_INFO, reportEngine.getPrintInfo(), null, null, null);
		jasperPrintParams.add(pip);

		jasperProcessInfo.setParameter(jasperPrintParams.toArray(new ProcessInfoParameter[]{}));
		
		ServerProcessCtl.process(null,		// Parent set to null for synchronous processing, see bugtracker 3010932  
				   jasperProcessInfo,
				trx);

		if (processInfo != null)
			processInfo.setPDFReport(jasperProcessInfo.getPDFReport());

		boolean result = true;
		return(result);
	}
	
	/**
	 * Create output (server only)
	 * 
	 * @param reportEngine
	 * @param printerName
	 */
	private static void createOutput(ReportEngine reportEngine, String printerName)
	{
		if (printerName!=null) {
			reportEngine.getPrintInfo().setPrinterName(printerName);
		}
		reportEngine.print();
	}
	
	
	/**
	 *	Create Report.
	 *	Called from ProcessCtl.
	 *	- Check special reports first, if not, create standard Report
	 * @param parent
	 * @param processInfo
     * @return true if created
     */
	static public boolean start (ASyncProcess parent, ProcessInfo processInfo)
	{

		/**
		 *	Order Print
		 */
		if (processInfo.getAD_Process_ID() == 110)			//	C_Order
			return startDocumentPrint(ReportEngine.ORDER, null, processInfo.getRecord_ID(), null , processInfo);
		if (processInfo.getAD_Process_ID() ==  MProcess.getProcess_ID("Rpt PP_Order", null))			//	C_Order
			return startDocumentPrint(ReportEngine.MANUFACTURING_ORDER, null, processInfo.getRecord_ID(), null,  processInfo);
		if (processInfo.getAD_Process_ID() ==  MProcess.getProcess_ID("Rpt DD_Order", null))			//	C_Order
			return startDocumentPrint(ReportEngine.DISTRIBUTION_ORDER, null, processInfo.getRecord_ID(), null , processInfo);
		else if (processInfo.getAD_Process_ID() == 116)		//	C_Invoice
			return startDocumentPrint(ReportEngine.INVOICE, null, processInfo.getRecord_ID(), null, processInfo);
		else if (processInfo.getAD_Process_ID() == 117)		//	M_InOut
			return startDocumentPrint(ReportEngine.SHIPMENT, null, processInfo.getRecord_ID(), null, processInfo);
		else if (processInfo.getAD_Process_ID() == 217)		//	C_Project
			return startDocumentPrint(ReportEngine.PROJECT, null, processInfo.getRecord_ID(), null, processInfo);
		else if (processInfo.getAD_Process_ID() == 276)		//	C_RfQResponse
			return startDocumentPrint(ReportEngine.RFQ, null, processInfo.getRecord_ID(), null, processInfo);
		else if (processInfo.getAD_Process_ID() == 159)		//	Dunning
			return startDocumentPrint(ReportEngine.DUNNING, null, processInfo.getRecord_ID(), null, processInfo);
 	    else if (processInfo.getAD_Process_ID() == 202			//	Financial Report
			|| processInfo.getAD_Process_ID() == 204)			//	Financial Statement
		   return startFinReport (processInfo);
		/********************
		 *	Standard Report
		 *******************/
		return startStandardReport (processInfo);
	}	//	create

	/**************************************************************************
	 *	Start Standard Report.
	 *  - Get Table Info & submit
	 *  @param processInfo Process Info
	 *  @param isDirectPrint if true, prints directly - otherwise View
	 *  @return true if OK
	 */
	static public boolean startStandardReport (ProcessInfo processInfo, boolean isDirectPrint)
	{
		processInfo.setPrintPreview(!isDirectPrint);
		return startStandardReport(processInfo);
	}
	
	/**************************************************************************
	 *	Start Standard Report.
	 *  - Get Table Info & submit.<br>
	 *  A report can be created from:
	 *  <ol>
	 *  <li>attached MPrintFormat, if any (see {@link ProcessInfo#setTransientObject(Object)}, {@link ProcessInfo#setSerializableObject(java.io.Serializable)}
	 *  <li>process information (AD_Process.AD_PrintFormat_ID, AD_Process.AD_ReportView_ID)
	 *  </ol>
	 * @param processInfo
	 * @return true if OK
     */
	static public boolean startStandardReport (ProcessInfo processInfo)
	{
		ReportEngine re = null;
		//
		// Create Report Engine by using attached MPrintFormat (if any)
		Object o = processInfo.getTransientObject();
		if (o == null)
			o = processInfo.getSerializableObject();
		if (o != null && o instanceof MPrintFormat) {
			Properties ctx = Env.getCtx();
			MPrintFormat format = (MPrintFormat)o;
			String TableName = MTable.getTableName(ctx, format.getAD_Table_ID());
			MQuery query = MQuery.get (ctx, processInfo.getAD_PInstance_ID(), TableName);
			PrintInfo info = new PrintInfo(processInfo);
			re = new ReportEngine(ctx, format, query, info);
			createOutput(re, null);
			return true;
		}
		//
		// Create Report Engine normally
		else {
			re = ReportEngine.get(Env.getCtx(), processInfo);
			if (re == null)
			{
				processInfo.setSummary("No ReportEngine");
				return false;
			}
		}
		
		createOutput(re, null);
		return true;
	}	//	startStandardReport

	/**
	 *	Start Financial Report.
	 *  @param pi Process Info
	 *  @return true if OK
	 */
	static public boolean startFinReport (ProcessInfo pi)
	{
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());

		//  Create Query from Parameters
		String TableName = pi.getAD_Process_ID() == 202 ? "T_Report" : "T_ReportStatement";
		MQuery query = MQuery.get (Env.getCtx(), pi.getAD_PInstance_ID(), TableName);

		//	Get PrintFormat
		MPrintFormat format = (MPrintFormat)pi.getTransientObject();
		if (format == null)
			format = (MPrintFormat)pi.getSerializableObject();
		if (format == null)
		{
			s_log.log(Level.SEVERE, "startFinReport - No PrintFormat");
			return false;
		}
		PrintInfo printInfo = new PrintInfo(pi);

		ReportEngine reportEngine = new ReportEngine(Env.getCtx(), format, query, printInfo);
		createOutput(reportEngine, null);
		return true;
	}	//	startFinReport
	
	
}
