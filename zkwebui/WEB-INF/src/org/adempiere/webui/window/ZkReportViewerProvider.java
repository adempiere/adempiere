/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *
 * Copyright (C) 2007 Low Heng Sin hengsin@avantz.com
 * _____________________________________________
 *****************************************************************************/
package org.adempiere.webui.window;

import java.io.FileInputStream;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.SMJReportViewer;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.session.SessionManager;
import org.compiere.model.MSysConfig;
import org.compiere.print.ReportEngine;
import org.compiere.print.ReportViewerProvider;
import org.compiere.util.CLogger;
import org.zkoss.zk.ui.Desktop;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.report.MReport;
import org.zkoss.zk.ui.Executions;

/**
 * 
 * @author Low Heng Sin
 *
 */
public class ZkReportViewerProvider implements ReportViewerProvider {

	protected static CLogger	log = CLogger.getCLogger (ZkReportViewerProvider.class);
	private Desktop m_desktop = null;



	public void openViewer(ReportEngine report) {
		
		// System Configuration Parameter "SMART_REPORT"
		// Value = 'Y' if want to generate SMJReport else Value = 'N' if we don't want to generate SMJreport

		if ( report.getPrintInfo().getAD_Process_ID() == 202 && MSysConfig.getBooleanValue("SMART_REPORT", Boolean.FALSE) ) { 
			ProcessInfo processInfo   = report.getProcessInfo() ;
			MReport	m_report          = new MReport ( report.getCtx(), processInfo.getRecord_ID(), processInfo.getTransactionName() );
			ProcessInfoParameter[] pi = processInfo.getParameter();
			// Goodwill BF: report title is taken wrongly from other report if report line set is shared 
			// - passing PA_Report_ID instead of PA_ReportLineSet_ID
			Window smjviewer = new SMJReportViewer(processInfo.getAD_PInstance_ID(), processInfo.getTransactionName(), 
													m_report.getPA_Report_ID(), pi[0].getParameterAsInt() , 
													report.getPrintFormat().getAD_PrintFont_ID(), m_report.getColumnSet().getColumns());
			smjviewer.setAttribute(Window.MODE_KEY, Window.MODE_EMBEDDED);
			smjviewer.setAttribute(Window.INSERT_POSITION_KEY, Window.INSERT_NEXT);
			SessionManager.getAppDesktop().showWindow(smjviewer);

		} else { 
			Window viewer = new ZkReportViewer(report, report.getName());

			viewer.setAttribute(Window.MODE_KEY, Window.MODE_EMBEDDED);
			viewer.setAttribute(Window.INSERT_POSITION_KEY, Window.INSERT_NEXT);
			SessionManager.getAppDesktop().showWindow(viewer);
		}
	}
	
	public void openViewer(ReportEngine report, Object objDesktop) {
		if (objDesktop == null) {
			log.severe("Report Viewer got a NULL desktop. This shouldn't happen.");
			return;
		}
		m_desktop = (Desktop) objDesktop;
		boolean wait = true;
		int n = 1;
		while (wait) {
			if (n > 1) {
				log.info("Activate Desktop. Attempt#" + n);
			}
			wait = !SessionManager.activateDesktop(m_desktop);
			n++;
		}
		openViewer(report);
		SessionManager.releaseDesktop(m_desktop);
	}

	@Override
	public void openViewer(String  title, FileInputStream input)
	{
		  boolean inUIThread = Executions.getCurrent() != null;
		  boolean desktopActivated = false;
		  
		  Desktop desktop = null;
		  try {
		      if (!inUIThread) {
		    	  desktop = AEnv.getDesktop();
		    	  
		       if (desktop == null)
		       {
		        log.warning("desktop is null");
		        return;
		       }
		       //1 second timeout
		       if (Executions.activate(desktop, 1000)) {
		        desktopActivated = true;
		       } else {
		        log.warning("could not activate desktop");
		        return;
		       }
		      }
		   Window win = new SimplePDFViewer(title, input);
		   SessionManager.getAppDesktop().showWindow(win, "center");
		  } catch (Exception e) {
		     } finally {
		      if (!inUIThread && desktopActivated) {
		       Executions.deactivate(desktop);
		      }
		     }		
	}
}