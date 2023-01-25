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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;

import javax.print.DocFlavor;
import javax.print.StreamPrintService;
import javax.print.StreamPrintServiceFactory;
import javax.print.attribute.HashPrintRequestAttributeSet;

import org.compiere.print.ReportEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1400">
 * 		@see FR [ 1400 ] Dynamic report export</a>
 */
public class ExportFormatPS extends AbstractExportFormat {
	
	public ExportFormatPS(Properties ctx, ReportEngine reportEngine) {
		setCtx(ctx);
		setReportEngine(reportEngine);
	}
	
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (ExportFormatPS.class);
	
	@Override
	public String getExtension() {
		return "ps";
	}

	@Override
	public String getName() {
		return Msg.getMsg(Env.getCtx(), "FilePS");
	}
	
	@Override
	public boolean exportToFile(File file) {
		if(getReportEngine() == null
				|| getCtx() == null) {
			return false;
		}
		//	
		return createPS(file);
	}
	
	/**************************************************************************
	 * 	Create PostScript File
	 * 	@param file file
	 * 	@return true if success
	 */
	public boolean createPS(File file) {
		try {
			return createPS (new FileOutputStream(file));
		} catch (FileNotFoundException fnfe) {
			log.log(Level.SEVERE, "(f) - " + fnfe.toString());
		} catch (Exception e) {
			log.log(Level.SEVERE, "(f)", e);
		}
		return false;
	}	//	createPS

	/**
	 * 	Write PostScript to writer
	 * 	@param os output stream
	 * 	@return true if success
	 */
	public boolean createPS(OutputStream os) {
		try {
			String outputMimeType = DocFlavor.BYTE_ARRAY.POSTSCRIPT.getMimeType();
			DocFlavor docFlavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
			StreamPrintServiceFactory[] spsfactories =
				StreamPrintServiceFactory.lookupStreamPrintServiceFactories(docFlavor, outputMimeType);
			if (spsfactories.length == 0)
			{
				log.log(Level.SEVERE, "(fos) - No StreamPrintService");
				return false;
			}
			//	just use first one - sun.print.PSStreamPrinterFactory
			//	System.out.println("- " + spsfactories[0]);
			StreamPrintService sps = spsfactories[0].getPrintService(os);
			//	get format
			getReportEngine().showView();
			//	print it
			sps.createPrintJob().print(getLayoutEngine().getPageable(false), 
				new HashPrintRequestAttributeSet());
			//
			os.flush();
			//following 2 line for backward compatibility
			if (os instanceof FileOutputStream)
				((FileOutputStream)os).close();
		} catch (Exception e) {
			log.log(Level.SEVERE, "(fos)", e);
		}
		return false;
	}	//	createPS
	
}	//	AbstractBatchImport
