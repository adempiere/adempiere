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
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.ADialog;
import org.compiere.apps.ProcessCtl;
import org.compiere.model.MPaySelectionCheck;
import org.compiere.model.MProcess;
import org.compiere.model.MQuery;
import org.compiere.model.MTable;
import org.compiere.model.PrintInfo;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
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
 */
public class ReportCtl
{
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
	 *  @param pi process info
	 *  @param IsDirectPrint if true, prints directly - otherwise View
	 *  @return true if created
	 */
	static public boolean start (ProcessInfo pi, boolean IsDirectPrint)
	{
		return start(null, -1, pi, IsDirectPrint);
	}
	
	/**
	 *	Create Report.
	 *	Called from ProcessCtl.
	 *	- Check special reports first, if not, create standard Report
	 *
	 *  @param parent The window which invoked the printing
	 *  @param WindowNo The windows number which invoked the printing
	 *  @param pi process info
	 *  @param IsDirectPrint if true, prints directly - otherwise View
	 *  @return true if created
	 */
	static public boolean start (ASyncProcess parent, int WindowNo, ProcessInfo pi, boolean IsDirectPrint)
	{
		pi.setPrintPreview(!IsDirectPrint);
		return start(parent, WindowNo, pi);
	}
	
	/**
	 *	Create Report.
	 *	Called from ProcessCtl.
	 *	- Check special reports first, if not, create standard Report
	 *
	 *  @param parent The window which invoked the printing
	 *  @param WindowNo The windows number which invoked the printing
	 *  @param pi process info
	 *  @param IsDirectPrint if true, prints directly - otherwise View
	 *  @return true if created
	 */
	static public boolean start (ASyncProcess parent, int WindowNo, ProcessInfo pi)
	{
		s_log.info("start - " + pi);

		/**
		 *	Order Print
		 */
		if (pi.getAD_Process_ID() == 110)			//	C_Order
			return startDocumentPrint(ReportEngine.ORDER, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview());
		if (pi.getAD_Process_ID() ==  MProcess.getProcess_ID("Rpt PP_Order", null))			//	C_Order
			return startDocumentPrint(ReportEngine.MANUFACTURING_ORDER, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview());
		if (pi.getAD_Process_ID() ==  MProcess.getProcess_ID("Rpt DD_Order", null))			//	C_Order
			return startDocumentPrint(ReportEngine.DISTRIBUTION_ORDER, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview());
		else if (pi.getAD_Process_ID() == 116)		//	C_Invoice
			return startDocumentPrint(ReportEngine.INVOICE, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview());
		else if (pi.getAD_Process_ID() == 117)		//	M_InOut
			return startDocumentPrint(ReportEngine.SHIPMENT, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview());
		else if (pi.getAD_Process_ID() == 217)		//	C_Project
			return startDocumentPrint(ReportEngine.PROJECT, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview());
		else if (pi.getAD_Process_ID() == 276)		//	C_RfQResponse
			return startDocumentPrint(ReportEngine.RFQ, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview());
		else if (pi.getAD_Process_ID() == 313)		//	C_Payment
			return startCheckPrint(pi.getRecord_ID(), !pi.isPrintPreview());
		/**
        else if (pi.getAD_Process_ID() == 290)      // Movement Submission by VHARCQ
            return startDocumentPrint(ReportEngine.MOVEMENT, pi.getRecord_ID(), parent, WindowNo, IsDirectPrint);
		else if (pi.AD_Process_ID == 9999999)	//	PaySelection
			return startDocumentPrint(CHECK, pi, IsDirectPrint);
		else if (pi.AD_Process_ID == 9999999)	//	PaySelection
			return startDocumentPrint(REMITTANCE, pi, IsDirectPrint);
		**/
		else if (pi.getAD_Process_ID() == 159)		//	Dunning
			return startDocumentPrint(ReportEngine.DUNNING, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview());
	   else if (pi.getAD_Process_ID() == 202			//	Financial Report
			|| pi.getAD_Process_ID() == 204)			//	Financial Statement
		   return startFinReport (pi);
		/********************
		 *	Standard Report
		 *******************/
		return startStandardReport (pi);
	}	//	create

	/**************************************************************************
	 *	Start Standard Report.
	 *  - Get Table Info & submit
	 *  @param pi Process Info
	 *  @param IsDirectPrint if true, prints directly - otherwise View
	 *  @return true if OK
	 */
	static public boolean startStandardReport (ProcessInfo pi, boolean IsDirectPrint)
	{
		pi.setPrintPreview(!IsDirectPrint);
		return startStandardReport(pi);
	}
	
	/**************************************************************************
	 *	Start Standard Report.
	 *  - Get Table Info & submit.<br>
	 *  A report can be created from:
	 *  <ol>
	 *  <li>attached MPrintFormat, if any (see {@link ProcessInfo#setTransientObject(Object)}, {@link ProcessInfo#setSerializableObject(java.io.Serializable)}
	 *  <li>process information (AD_Process.AD_PrintFormat_ID, AD_Process.AD_ReportView_ID)
	 *  </ol>
	 *  @param pi Process Info
	 *  @param IsDirectPrint if true, prints directly - otherwise View
	 *  @return true if OK
	 */
	static public boolean startStandardReport (ProcessInfo pi)
	{
		ReportEngine re = null;
		//
		// Create Report Engine by using attached MPrintFormat (if any)
		Object o = pi.getTransientObject();
		if (o == null)
			o = pi.getSerializableObject();
		if (o != null && o instanceof MPrintFormat) {
			Properties ctx = Env.getCtx();
			MPrintFormat format = (MPrintFormat)o;
			String TableName = MTable.getTableName(ctx, format.getAD_Table_ID());
			MQuery query = MQuery.get (ctx, pi.getAD_PInstance_ID(), TableName);
			PrintInfo info = new PrintInfo(pi);
			re = new ReportEngine(ctx, format, query, info);
			createOutput(re, pi.isPrintPreview());
			return true;
		}
		//
		// Create Report Engine normally
		else {
			re = ReportEngine.get(Env.getCtx(), pi);
			if (re == null)
			{
				pi.setSummary("No ReportEngine");
				return false;
			}
		}
		
		createOutput(re, pi.isPrintPreview());
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
		PrintInfo info = new PrintInfo(pi);

		ReportEngine re = new ReportEngine(Env.getCtx(), format, query, info);
		createOutput(re, pi.isPrintPreview());
		return true;
	}	//	startFinReport
	
	/**
	 * 	Start Document Print for Type.
	 *  	Called also directly from ProcessDialog, VInOutGen, VInvoiceGen, VPayPrint
	 * 	@param type document type in ReportEngine
	 * 	@param Record_ID id
	 * 	@param IsDirectPrint if true, prints directly - otherwise View
	 * 	@return true if success
	 */
	public static boolean startDocumentPrint (int type, int Record_ID, boolean IsDirectPrint)
	{
		return startDocumentPrint(type, Record_ID, null, -1, IsDirectPrint);
	}
	
	/**
	 * 	Start Document Print for Type.
	 *  	Called also directly from ProcessDialog, VInOutGen, VInvoiceGen, VPayPrint
	 * 	@param type document type in ReportEngine
	 * 	@param Record_ID id
	 *  @param parent The window which invoked the printing
	 *  @param WindowNo The windows number which invoked the printing
	 * 	@param IsDirectPrint if true, prints directly - otherwise View
	 * 	@return true if success
	 */
	public static boolean startDocumentPrint (int type, int Record_ID, ASyncProcess parent, int WindowNo, 
			boolean IsDirectPrint)
	{
		ReportEngine re = ReportEngine.get (Env.getCtx(), type, Record_ID);
		if (re == null)
		{
			if (Ini.isClient())
			{
				ADialog.error(0, null, "NoDocPrintFormat");
			}
			else
			{
				try 
				{
					ClassLoader loader = ReportCtl.class.getClassLoader();
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
		
		if(re.getPrintFormat() != null)
		{
			if(re.getPrintFormat().getJasperProcess_ID() > 0)	
			{
				ProcessInfo pi = new ProcessInfo ("", re.getPrintFormat().getJasperProcess_ID());
				pi.setPrintPreview( !IsDirectPrint );
				pi.setRecord_ID ( Record_ID );
				//	Execute Process
				ProcessCtl worker = ProcessCtl.process(parent, WindowNo, pi, null);
			}
			else
			{
				createOutput(re, !IsDirectPrint);
				if (IsDirectPrint)
				{
					ReportEngine.printConfirm (type, Record_ID);
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
		
		// afalcone - [ 1871567 ] Wrong value in Payment document
		boolean ok = MPaySelectionCheck.deleteGeneratedDraft(Env.getCtx(), C_Payment_ID, null);
		//
		
		int C_PaySelectionCheck_ID = 0;
		MPaySelectionCheck psc = MPaySelectionCheck.getOfPayment(Env.getCtx(), C_Payment_ID, null);
		
		if (psc != null)
			C_PaySelectionCheck_ID = psc.getC_PaySelectionCheck_ID();
		else
		{
			psc = MPaySelectionCheck.createForPayment(Env.getCtx(), C_Payment_ID, null);
			if (psc != null)
				C_PaySelectionCheck_ID = psc.getC_PaySelectionCheck_ID();
		}
		return startDocumentPrint (ReportEngine.CHECK, C_PaySelectionCheck_ID, null, -1, IsDirectPrint);
	}	//	startCheckPrint
	
	private static void createOutput(ReportEngine re, boolean printPreview)
	{
		if (printPreview)
			preview(re);
		else 
			re.print();
	}
	
	/**
	 * Launch viewer for report
	 * @param re
	 */
	public static void preview(ReportEngine re) 
	{
		ReportViewerProvider provider = getReportViewerProvider();
		provider.openViewer(re);
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
