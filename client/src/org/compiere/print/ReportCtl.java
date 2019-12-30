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
package org.compiere.print;

import java.lang.reflect.Method;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.ADialog;
import org.compiere.model.MPInstance;
import org.compiere.model.MPaySelectionCheck;
import org.compiere.model.MProcess;
import org.compiere.model.MQuery;
import org.compiere.model.MTable;
import org.compiere.model.PrintInfo;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;

/**
 *	Report Controller.
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ReportCtl.java,v 1.3 2006/10/08 07:05:08 comdivision Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>FR [ 1866739 ] ReportCtl: use printformat from the transient/serializable
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> BR [ 92 ] 
 *		@see https://github.com/adempiere/adempiere/issues/92
 */
public class ReportCtl
{
	/**
	 * @deprecated Please use {@link ServerReportCtl#PARAM_PRINTER_NAME}
	 */
	public static final String PARAM_PRINTER_NAME = ServerReportCtl.PARAM_PRINTER_NAME;
	/**
	 * @deprecated Please use {@link ServerReportCtl#PARAM_PRINT_FORMAT}
	 */
	public static final String PARAM_PRINT_FORMAT = ServerReportCtl.PARAM_PRINT_FORMAT;
	/**
	 * @deprecated Please use {@link ServerReportCtl#PARAM_PRINT_INFO}
	 */
	public static final String PARAM_PRINT_INFO = ServerReportCtl.PARAM_PRINT_INFO;
	
	/**
	 *	Constructor - prevent instance
	 */
	private ReportCtl()
	{
	}	//	ReportCtrl

	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (ReportCtl.class);
	
	private static ReportViewerProvider viewerProvider = new SwingViewerProvider(); 
	
	/**
	 *	Create Report.
	 *	Called from ProcessCtl.
	 *	- Check special reports first, if not, create standard Report
	 *
	 *  @param processInfo process info
	 *  @param isDirectPrint if true, prints directly - otherwise View
	 *  @return true if created
	 */
	static public boolean start (ProcessInfo processInfo, boolean isDirectPrint)
	{
		return start(null, -1, processInfo, isDirectPrint);
	}
	
	/**
	 *	Create Report.
	 *	Called from ProcessCtl.
	 *	- Check special reports first, if not, create standard Report
	 *
	 *  @param parent The window which invoked the printing
	 *  @param windowNo The windows number which invoked the printing
	 *  @param processInfo process info
	 *  @param isDirectPrint if true, prints directly - otherwise View
	 *  @return true if created
	 */
	static public boolean start (ASyncProcess parent, int windowNo, ProcessInfo processInfo, boolean isDirectPrint)
	{
		processInfo.setPrintPreview(!isDirectPrint);
		return start(parent, windowNo, processInfo);
	}
	
	/**
	 *	Create Report.
	 *	Called from ProcessCtl.
	 *	- Check special reports first, if not, create standard Report
	 * @param parent
	 * @param windowNo
	 * @param processInfo
     * @return true if created
     */
	static public boolean start (ASyncProcess parent, int windowNo, ProcessInfo processInfo)
	{
		s_log.info("start - " + processInfo);

		MPInstance instance = new MPInstance(Env.getCtx(), processInfo.getAD_PInstance_ID(), null);
		
		if (processInfo.getReportType() != null)
			instance.setReportType(processInfo.getReportType());
		
		if (processInfo.getTransientObject() != null)
			instance.setAD_PrintFormat_ID(((MPrintFormat) processInfo.getTransientObject()).getAD_PrintFormat_ID());
		else if (processInfo.getSerializableObject() != null)
			instance.setAD_PrintFormat_ID(((MPrintFormat) processInfo.getSerializableObject()).getAD_PrintFormat_ID());
		
		instance.saveEx();
		
		
		/**
		 *	Order Print
		 */
		if (processInfo.getAD_Process_ID() == 110)			//	C_Order
			return startDocumentPrint(ReportEngine.ORDER, null ,processInfo.getRecord_ID(), parent, windowNo, !processInfo.isPrintPreview() , null ,  processInfo);
		if (processInfo.getAD_Process_ID() ==  MProcess.getProcess_ID("Rpt PP_Order", null))			//	C_Order
			return startDocumentPrint(ReportEngine.MANUFACTURING_ORDER, null ,  processInfo.getRecord_ID(), parent, windowNo, !processInfo.isPrintPreview(), null , processInfo);
		if (processInfo.getAD_Process_ID() ==  MProcess.getProcess_ID("Rpt DD_Order", null))			//	C_Order
			return startDocumentPrint(ReportEngine.DISTRIBUTION_ORDER, null, processInfo.getRecord_ID() , parent, windowNo, !processInfo.isPrintPreview(),null , processInfo);
		else if (processInfo.getAD_Process_ID() == 116)		//	C_Invoice
			return startDocumentPrint(ReportEngine.INVOICE, null , processInfo.getRecord_ID(), parent, windowNo, !processInfo.isPrintPreview(), null , processInfo);
		else if (processInfo.getAD_Process_ID() == 117)		//	M_InOut
			return startDocumentPrint(ReportEngine.SHIPMENT,null, processInfo.getRecord_ID(), parent, windowNo, !processInfo.isPrintPreview(), null , processInfo);
		else if (processInfo.getAD_Process_ID() == 217)		//	C_Project
			return startDocumentPrint(ReportEngine.PROJECT,null, processInfo.getRecord_ID(), parent, windowNo, !processInfo.isPrintPreview(), null , processInfo);
		else if (processInfo.getAD_Process_ID() == 276)		//	C_RfQResponse
			return startDocumentPrint(ReportEngine.RFQ, null ,processInfo.getRecord_ID(), parent, windowNo, !processInfo.isPrintPreview(), null , processInfo);
		else if (processInfo.getAD_Process_ID() == 313)		//	C_Payment
			return startCheckPrint(processInfo.getRecord_ID(), !processInfo.isPrintPreview());
        else if (processInfo.getAD_Process_ID() == 290)     // M_Movement
            return startDocumentPrint(ReportEngine.MOVEMENT , null, processInfo.getRecord_ID(), parent, windowNo, !processInfo.isPrintPreview(), null , processInfo);
		/*else if (pi.AD_Process_ID == 9999999)	//	PaySelection
			return startDocumentPrint(CHECK, pi, IsDirectPrint);
		else if (pi.AD_Process_ID == 9999999)	//	PaySelection
			return startDocumentPrint(REMITTANCE, pi, IsDirectPrint);
		**/
		else if (processInfo.getAD_Process_ID() == 159)		//	Dunning
			return startDocumentPrint(ReportEngine.DUNNING,null , processInfo.getRecord_ID(), parent, windowNo, !processInfo.isPrintPreview(), null , processInfo);
		//	Yamel Senih, 2015-11-13
		//	Delete Hardcode
//	   else if (pi.getAD_Process_ID() == 202			//	Financial Report
//			|| pi.getAD_Process_ID() == 204)			//	Financial Statement
//		   return startFinReport (pi);
		//	End Yamel Senih
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
	 *  @param processInfo Process Info
	 *  @param IsDirectPrint if true, prints directly - otherwise View
	 *  @return true if OK
	 */
	static public boolean startStandardReport (ProcessInfo processInfo)
	{
		ReportEngine reportEngine = null;
		//
		// Create Report Engine by using attached MPrintFormat (if any)
		Object transientObject = processInfo.getTransientObject();
		if (transientObject == null)
			transientObject = processInfo.getSerializableObject();
		if (transientObject != null && transientObject instanceof MPrintFormat) {
			Properties ctx = Env.getCtx();
			MPrintFormat format = (MPrintFormat)transientObject;
			String tableName = MTable.getTableName(ctx, format.getAD_Table_ID());
			if ( processInfo.getAD_Process_ID() == 202	)
				tableName = "T_Report";
			MQuery query = MQuery.get (ctx, processInfo.getAD_PInstance_ID(), tableName);
			PrintInfo info = new PrintInfo(processInfo);
			reportEngine = new ReportEngine(ctx, format, query, info);
			{
				reportEngine.setReportType(processInfo.getReportType());
			}
			createOutput(reportEngine, processInfo.isPrintPreview(), null);
			return true;
		}
		//
		// Create Report Engine normally
		else {
			reportEngine = ReportEngine.get(Env.getCtx(), processInfo);
			if (reportEngine == null)
			{
				processInfo.setSummary("No ReportEngine");
				return false;
			}
		}
		if(processInfo.getReportType() != null)
		{
			reportEngine.setReportType(processInfo.getReportType());
		}
		createOutput(reportEngine, processInfo.isPrintPreview(), null);
		return true;
	}	//	startStandardReport

	/**
	 *	Start Financial Report.
	 *  @param pi Process Info
	 *  @return true if OK
	 */
	//	Yamel Senih, 2015-11-13
	//	Delete Hardcode
//	static public boolean startFinReport (ProcessInfo pi)
//	{
//		//  Create Query from Parameters
//		String TableName = pi.getAD_Process_ID() == 202 ? "T_Report" : "T_ReportStatement";
//		MQuery query = MQuery.get (Env.getCtx(), pi.getAD_PInstance_ID(), TableName);
//
//		//	Get PrintFormat
//		MPrintFormat format = (MPrintFormat)pi.getTransientObject();
//		if (format == null)
//			format = (MPrintFormat)pi.getSerializableObject();
//		if (format == null)
//		{
//			s_log.log(Level.SEVERE, "startFinReport - No PrintFormat");
//			return false;
//		}
//		PrintInfo info = new PrintInfo(pi);
//
//		ReportEngine re = new ReportEngine(Env.getCtx(), format, query, pi,info);
//		createOutput(re, pi.isPrintPreview(), null);
//		return true;
//	}	//	startFinReport
	//	End Yamel Senih
	
	/**
	 * 	Start Document Print for Type.
	 *  	Called also directly from ProcessDialog, VInOutGen, VInvoiceGen, VPayPrint
	 * 	@param type document type in ReportEngine
	 * 	@param recordId id
	 * 	@param isDirectPrint if true, prints directly - otherwise View
	 * 	@return true if success
	 */
	public static boolean startDocumentPrint (int type, int recordId, boolean isDirectPrint)
	{
		return startDocumentPrint(type, recordId, null, -1, isDirectPrint);
	}
	
	/**
	 * 	Start Document Print for Type with specified printer. Always direct print.
	 * 	@param type document type in ReportEngine
	 *  @param customPrintFormat	Custom print format. Can be null.
	 * 	@param recordId id
	 *  @param parent The window which invoked the printing
	 *  @param windowNo The windows number which invoked the printing
	 * 	@param printerName 	Specified printer name
	 * 	@return true if success
	 */
	public static boolean startDocumentPrint(int type, MPrintFormat customPrintFormat, int recordId, ASyncProcess parent, int windowNo, String printerName)
	{
		return(startDocumentPrint(type, customPrintFormat, recordId, parent, windowNo, true, printerName , null));
	}

	/**
	 * 	Start Document Print for Type.
	 *  	Called also directly from ProcessDialog, VInOutGen, VInvoiceGen, VPayPrint
	 * 	@param type document type in ReportEngine
	 * 	@param recordId id
	 *  @param parent The window which invoked the printing
	 *  @param windowNo The windows number which invoked the printing
	 * 	@param isDirectPrint if true, prints directly - otherwise View
	 * 	@return true if success
	 */
	public static boolean startDocumentPrint(int type, int recordId, ASyncProcess parent, int windowNo,
			boolean isDirectPrint)
	{
		return(startDocumentPrint(type, null, recordId, parent, windowNo, isDirectPrint, null , null));
	}

	/**
	 * 	Start Document Print for Type with specified printer.
	 * 	@param type document type in ReportEngine
	 * 	@param recordId id
	 *  @param parent The window which invoked the printing
	 *  @param windowNo The windows number which invoked the printing
	 * 	@param printerName 	Specified printer name
	 * 	@param processInfo Process Info
	 * 	@return true if success
	 */
	public static boolean startDocumentPrint (int type, MPrintFormat customPrintFormat, int recordId, ASyncProcess parent, int windowNo,
			boolean isDirectPrint, String printerName , ProcessInfo processInfo)
	{
		String trxName;
		if (processInfo != null)
			trxName = processInfo.getTransactionName();
		else
			trxName = null;

		ReportEngine reportEngine = ReportEngine.get (Env.getCtx(), type, recordId , trxName);
		if (reportEngine == null)
		{
			if (Ini.isClient())
			{
				ADialog.error(0, null, "NoDocPrintFormat");
			}
			else
			{
				try 
				{
					ClassLoader loader = Thread.currentThread().getContextClassLoader();
					if (loader == null)
						loader = ReportCtl.class.getClassLoader();
					Class<?> clazz = loader.loadClass("org.adempiere.webui.window.FDialog");
					Method m = clazz.getMethod("error", Integer.TYPE, String.class);
					m.invoke(null, 0, "NoDocPrintFormat");
				} catch (Exception e) 
				{
					throw new AdempiereException(e);
				}
			}
			return false;
		}
		if (customPrintFormat!=null) {
			// Use custom print format if available
			reportEngine.setPrintFormat(customPrintFormat);
		}
		
		if(reportEngine.getPrintFormat()!=null)
		{
			MPrintFormat format = reportEngine.getPrintFormat();
			
			// We have a Jasper Print Format
			// ==============================
			if(format.getJasperProcess_ID() > 0)	
			{
				ServerReportCtl.runJasperProcess(recordId, reportEngine, isDirectPrint, printerName, processInfo);
			}
			else
			// Standard Print Format (Non-Jasper)
			// ==================================
			{
				// set generated PDF
				if (processInfo != null) {
					processInfo.setPDFReport(reportEngine.getPDF());
					if (processInfo.isBatch())
						return true;
				}

				createOutput(reportEngine, !isDirectPrint, printerName);
				if (isDirectPrint)
				{
					ReportEngine.printConfirm (type, recordId , trxName);
				}
			}
		}
		return true;
	}	//	StartDocumentPrint
	
	/**
	 * 	Start Check Print.
	 * 	Find/Create
	 *	@param C_Payment_ID Payment
	 * 	@param IsDirectPrint if true, prints directly - otherwise View
	 * 	@return true if success
	 */
	public static boolean startCheckPrint (int C_Payment_ID, boolean IsDirectPrint)
	{
		int paySelectionCheckById = getHRPaySelectionCheckById(Env.getCtx(), C_Payment_ID, null);
		if (paySelectionCheckById > 0)
		{	
			return startDocumentPrint (ReportEngine.HR_CHECK, paySelectionCheckById, null, -1, IsDirectPrint);
		}	
		 		
		// afalcone - [ 1871567 ] Wrong value in Payment document
		boolean ok = MPaySelectionCheck.deleteGeneratedDraft(Env.getCtx(), C_Payment_ID, null);
		//
		
		int PaySelectionCheckId = 0;
		MPaySelectionCheck paySelectionCheck = MPaySelectionCheck.getOfPayment(Env.getCtx(), C_Payment_ID, null);
		
		if (paySelectionCheck != null)
			PaySelectionCheckId = paySelectionCheck.getC_PaySelectionCheck_ID();
		else
		{
			paySelectionCheck = MPaySelectionCheck.createForPayment(Env.getCtx(), C_Payment_ID, null);
			if (paySelectionCheck != null)
				PaySelectionCheckId = paySelectionCheck.getC_PaySelectionCheck_ID();
		}
		return startDocumentPrint (ReportEngine.CHECK, PaySelectionCheckId, null, -1, IsDirectPrint);
	}	//	startCheckPrint
	
	private static int getHRPaySelectionCheckById(Properties ctx, int paymentId,
			String trxName) {
		final String sql = "SELECT MAX(HR_PaySelectionCheck_ID) FROM HR_PaySelectionCheck psc WHERE psc.C_Payment_ID = ? AND Processed=?";
		return DB.getSQLValue(trxName,sql, paymentId , true);
	}

	private static void createOutput(ReportEngine reportEngine, boolean printPreview, String printerName)
	{
		if (printPreview)
			preview(reportEngine);
		else {
				if (printerName!=null) {
					reportEngine.getPrintInfo().setPrinterName(printerName);
				}
				reportEngine.print();
		}
	}
	
	/**
	 * Launch viewer for report
	 * @param reportEngine
	 */
	public static void preview(ReportEngine reportEngine)
	{
		ReportViewerProvider provider = getReportViewerProvider();
		provider.openViewer(reportEngine);
	}
	
	public static void setReportViewerProvider(ReportViewerProvider provider)
	{
		if (provider == null)
			throw new IllegalArgumentException("Cannot set report viewer provider to null");
		viewerProvider = provider;
	}
	
	public static ReportViewerProvider getReportViewerProvider()
	{
		return viewerProvider;
	}
}	//	ReportCtl
