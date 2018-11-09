/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.spin.util;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MReportView;
import org.compiere.print.ReportEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Util;

/**
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1400">
 * 		@see FR [ 1400 ] Dynamic report export</a>
 */
public class ReportExportHandler {
	
	public ReportExportHandler(Properties ctx, ReportEngine reportEngine) {
		this.ctx = ctx;
		this.reportEngine = reportEngine;
		reportView = MReportView.get(ctx, reportEngine.getPrintFormat().getAD_ReportView_ID());
		//	Load class
		load();
	}
	
	/**	Report view		*/
	private MReportView reportView;
	/**	Report Engine	*/
	private ReportEngine reportEngine;
	/**	Context			*/
	private Properties ctx;
	/**	Report Export	*/
	private ReportExport reportExport;
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (ReportExportHandler.class);
	/**	Last Message	*/
	private String lastMessage;
	
	/**
	 * Get Class from device type, used for handler
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return Class<?>
	 */
	private Class<?> getHandlerClass() {
		if(reportView == null 
				|| reportView.getAD_ReportView_ID() <= 0) {
			return null;
		}
		String className = reportView.getClassname();
		//	Validate null values
		if(Util.isEmpty(className)) {
			return null;
		}
		try {
			Class<?> clazz = Class.forName(className);
			//	Make sure that it is a PO class
			Class<?> superClazz = clazz.getSuperclass();
			//	Validate super class
			while (superClazz != null) {
				if (superClazz == ReportExport.class) {
					log.fine("Use: " + className);
					return clazz;
				}
				//	Get Supert Class
				superClazz = superClazz.getSuperclass();
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
		//	
		log.finest("Not found: " + className);
		return null;
	}	//	getHandlerClass
	
	/**
	 * Load Default class
	 */
	private void load() {
		//	Get class from parent
		Class<?> clazz = getHandlerClass();
		//	Not yet implemented
		if (clazz == null) {
			log.log(Level.INFO, "Using Standard Report Export");
			reportExport = new ReportExport(ctx, reportEngine);
			return;
		}
		//	
		Constructor<?> constructor = null;
		try {
			constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, ReportEngine.class});
			//	new instance
			reportExport = (ReportExport) constructor.newInstance(new Object[] {ctx, reportEngine});
		} catch (Exception e) {
			String msg = e.getMessage();
			if (msg == null)
				msg = e.toString();
			log.warning("No transaction Constructor for " + clazz + " (" + msg + ")");
		}
	}
	
	/**
	 * Get Export Format List
	 * @return
	 */
	public List<AbstractExportFormat> getExportFormatList() {
		if(reportExport == null) {
			return null;
		}
		//	
		return reportExport.getExportFormatList();
	}
	
	/**
	 * Export to file
	 * @param exportName
	 * @param file
	 * @return
	 */
	@Deprecated
	public boolean exportToFile(String exportName, File file) {
		return exportTo(exportName, file);
	}
	
	/**
	 * Export to service or file based on default action
	 * @param exportName
	 * @param file
	 * @param defaultAction
	 * @return
	 */
	public boolean exportTo(String exportName, File file) {
		if(reportExport == null) {
			return false;
		}
		boolean result = false;
		//	
		for(AbstractExportFormat exporter : getExportFormatList()) {
			if(exporter.getName().equals(exportName)) {
				result = exporter.exportTo(file);
				lastMessage = exporter.getMessage();
			}
		}
		//	Default
		return result;
	}
	
	/**
	 * Get exporter from name
	 * @param exportName
	 * @return
	 */
	public AbstractExportFormat getExporter(String exportName) {
		for(AbstractExportFormat exporter : getExportFormatList()) {
			if(exporter.getName().equals(exportName)) {
				return exporter;
			}
		}
		//	Default
		return null;
	}
	
	/**
	 * Get Last Message
	 * @return
	 */
	public String getLastMessage() {
		return lastMessage;
	}
	
}	//	ReportExportHandler
