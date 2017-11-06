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
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.print.export.PrintDataExcelExporter;
import org.compiere.print.ReportEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;

/**
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1400">
 * 		@see FR [ 1400 ] Dynamic report export</a>
 */
public class ExportFormatXLS extends AbstractExportFormat {
	
	public ExportFormatXLS(Properties ctx, ReportEngine reportEngine) {
		setCtx(ctx);
		setReportEngine(reportEngine);
	}
	
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (ExportFormatXLS.class);
	
	@Override
	public String getExtension() {
		return "xls";
	}

	@Override
	public String getName() {
		return Msg.getMsg(Env.getCtx(), "FileXLS");
	}
	
	@Override
	public boolean exportToFile(File file) {
		return exportToFile(file, null);
	}
	
	/**
	 * For old compatibility
	 * @param file
	 * @param languageInfo
	 * @return
	 */
	@Deprecated
	public boolean exportToFile(File file, Language language) {
		if(getReportEngine() == null
				|| getCtx() == null) {
			return false;
		}
		//	
		try {
			PrintDataExcelExporter exp = new PrintDataExcelExporter(getPrintData(), getPrintFormat());
			if(language == null) {
				language = getLanguage();
			}
			exp.export(file, language);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
			return false;
		}
		//	
		return true;
	}
}	//	AbstractBatchImport
