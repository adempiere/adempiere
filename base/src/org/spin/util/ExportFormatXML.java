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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.transform.stream.StreamResult;

import org.compiere.print.ReportEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1400">
 * 		@see FR [ 1400 ] Dynamic report export</a>
 */
public class ExportFormatXML extends AbstractExportFormat {
	
	public ExportFormatXML(Properties ctx, ReportEngine reportEngine) {
		setCtx(ctx);
		setReportEngine(reportEngine);
	}
	
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (ExportFormatXML.class);
	
	@Override
	public String getExtension() {
		return "xml";
	}

	@Override
	public String getName() {
		return Msg.getMsg(Env.getCtx(), "FileXML");
	}
	
	@Override
	public boolean exportToFile(File file) {
		if(getReportEngine() == null
				|| getCtx() == null) {
			return false;
		}
		//	
		return createXML(convertFile(file));
	}
	
	@Override // with UTF-8 charset , @see https://github.com/adempiere/adempiere/issues/2701
	public BufferedWriter convertFile(File file) {
		Writer fileWriter = null;
		FileOutputStream osf;
		try {
			fileWriter = new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		}
		//	Validate null
		if(fileWriter == null) { 
			return null;
		}
		return new BufferedWriter(fileWriter);
	}

	/**
	 * 	Write XML to writer
	 * 	@param writer writer
	 * 	@return true if success
	 */
	public boolean createXML(Writer writer) {
		if(writer == null) {
			return false;
		}
		try {
			getPrintData().createXML(new StreamResult(writer));
			writer.flush();
			writer.close();
			return true;
		} catch (Exception e) {
			log.log(Level.SEVERE, "(w)", e);
		}
		return false;
	}	//	createXML
	
}	//	AbstractBatchImport
