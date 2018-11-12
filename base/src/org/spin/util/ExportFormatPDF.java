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
import java.net.URI;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.pdf.ITextDocument;
import org.compiere.print.ArchiveEngine;
import org.compiere.print.ReportEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1400">
 * 		@see FR [ 1400 ] Dynamic report export</a>
 */
public class ExportFormatPDF extends AbstractExportFormat {
	
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (ExportFormatPDF.class);
	
	public ExportFormatPDF(Properties ctx, ReportEngine reportEngine) {
		setCtx(ctx);
		setReportEngine(reportEngine);
	}
	
	@Override
	public String getExtension() {
		return "pdf";
	}

	@Override
	public String getName() {
		return Msg.getMsg(Env.getCtx(), "FilePDF");
	}
	
	@Override
	public boolean exportToFile(File file) {
		if(getReportEngine() == null
				|| getCtx() == null) {
			return false;
		}
		//	
		return createPDF(file);
	}
	
	/**
	 * 	Create PDF File
	 * 	@param file file
	 * 	@return true if success
	 */
	public boolean createPDF (File file) {
		String fileName = null;
		URI uri = null;

		try {
			File fil = file; // to avoid reassigning parameters 
			if (file == null)
				fil = File.createTempFile ("ReportEngine", ".pdf");
			fileName = fil.getAbsolutePath();
			uri = fil.toURI();
			if (fil.exists())
				fil.delete();

		} catch (Exception e) {
			log.log(Level.SEVERE, "file", e);
			return false;
		}
			
		log.fine(uri.toString());

		try {
			getReportEngine().getView();
			ArchiveEngine.get().archive(getLayoutEngine(), getPrintInfo());
			new ITextDocument().getPDFAsFile(fileName, getReportEngine().getLayout().getPageable(false));
		} catch (Exception e) {
			log.log(Level.SEVERE, "PDF", e);
			return false;
		}

		File file2 = new File(fileName);
		log.info(file2.getAbsolutePath() + " - " + file2.length());
		return file2.exists();
	}	//	createPDF
	
}	//	AbstractBatchImport
