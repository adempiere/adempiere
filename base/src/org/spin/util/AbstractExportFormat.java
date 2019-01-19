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
import java.io.Writer;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MQuery;
import org.compiere.model.PrintInfo;
import org.compiere.print.MPrintFormat;
import org.compiere.print.MPrintFormatItem;
import org.compiere.print.PrintData;
import org.compiere.print.PrintDataElement;
import org.compiere.print.ReportEngine;
import org.compiere.print.layout.LayoutEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Language;
import org.compiere.util.Util;

/**
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1400">
 * 		@see FR [ 1400 ] Dynamic report export</a>
 */
public abstract class AbstractExportFormat {
	
	/**	Report Engine	*/
	private ReportEngine reportEngine;
	/**	Context			*/
	private Properties ctx;
	/**	Message	*/
	private String message;
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (AbstractExportFormat.class);
	/**	Supported Actions	*/
	public static final String EXPORT_FILE = "EF";
	public static final String SEND_FILE = "SE";
	
	/**
	 * Set report engine
	 * @param reportEngine
	 */
	public void setReportEngine(ReportEngine reportEngine) {
		this.reportEngine = reportEngine;
	}
	
	/**
	 * Set Context
	 * @param ctx
	 */
	public void setCtx(Properties ctx) {
		this.ctx = ctx;
	}
	
	/**
	 * Get Report Engine
	 * @return
	 */
	public ReportEngine getReportEngine() {
		return reportEngine;
	}
	
	/**
	 * Get Context
	 * @return
	 */
	public Properties getCtx() {
		return ctx;
	}
	
	/**
	 * Get Print Info
	 * @return
	 */
	public PrintInfo getPrintInfo() {
		if(reportEngine == null) {
			return null;
		}
		//	
		return reportEngine.getPrintInfo();
	}	//	getPrintInfo
	
	/**
	 * Get Print format
	 * @return
	 */
	public MPrintFormat getPrintFormat() {
		if(reportEngine == null) {
			return null;
		}
		//	
		return reportEngine.getPrintFormat();
	}
	
	/**
	 * Get print data
	 * @return
	 */
	public PrintData getPrintData() {
		if(reportEngine == null) {
			return null;
		}
		//	
		return reportEngine.getPrintData();
	}
	
	/**
	 * Get Layout Engine
	 * @return
	 */
	public LayoutEngine getLayoutEngine() {
		if(reportEngine == null) {
			return null;
		}
		//	
		return reportEngine.getLayout();
	}
	
	/**
	 * Get Process Info
	 * @return
	 */
	public ProcessInfo getProcessInfo() {
		if(reportEngine == null) {
			return null;
		}
		//	
		return reportEngine.getProcessInfo();
	}
	
	/**
	 * Get Language
	 * @return
	 */
	public Language getLanguage() {
		if(reportEngine == null) {
			return Env.getLanguage(getCtx());
		}
		//	
		return reportEngine.getPrintFormat().getLanguage();
	}
	
	/**
	 * Get a list of extension supported
	 * @return
	 */
	public abstract String getExtension();
	
	
	/**
	 * Get a list of extension Description supported
	 * @return
	 */
	public abstract String getName();
	
	/**
	 * Export to file
	 * @param file
	 * @return
	 */
	public boolean exportToFile(File file) {
		//	
		return false;
	}
	
	/**
	 * Send file to custom service
	 * @param file
	 * @return
	 */
	public boolean sendTo(File file) {
		return false;
	}
	
	/**
	 * Get Supported Action
	 * @return
	 */
	public String getAction() {
		return EXPORT_FILE;
	}
	
	/**
	 * Generic Export to
	 * @param file
	 * @param action
	 * @return
	 */
	public boolean exportTo(File file) {
		//	Have it Action?
		if(Util.isEmpty(getAction())) {
			return false;
		}
		//	Supported Actions
		if(getAction().equals(EXPORT_FILE)) {
			return exportToFile(file);
		} else if(getAction().equals(SEND_FILE)) {
			return sendTo(file);
		}
		//	Default
		return false;
	}
	
	/**
	 * 	Write delimited file to writer
	 * 	@param writer writer
	 *  @param delimiter delimiter, e.g. comma, tab
	 *  @param language translation language
	 *  @param printHeader if you want a row with column names included
	 *  try
	 * 	@return true if success
	 */
	public boolean createDelimitedFile (Writer writer, char delimiter, Language language, boolean printHeader) {
		return createDelimitedFile(writer, delimiter, language, printHeader, null);
	}
	
	/**
	 * 	Write delimited file to writer
	 * 	@param writer writer
	 *  @param delimiter delimiter, e.g. comma, tab
	 *  @param language translation language
	 *  @param printHeader if you want a row with column names included
	 *  @param engine
	 *  try
	 * 	@return true if success
	 */
	public boolean createDelimitedFile (Writer writer, char delimiter, Language language, boolean printHeader, ReportEngine engine) {
		
		if (delimiter == 0)
			delimiter = '\t';
		try {
			int startAt = printHeader ? -1 : 0;
			PrintData printData = (engine != null? engine.getPrintData(): getPrintData());
			MPrintFormat printFormat = (engine != null? engine.getPrintFormat(): getPrintFormat());
			if(language == null) {
				language = getLanguage();
			}
			//	for all rows (-1 = header row)
			for (int row = startAt; row < printData.getRowCount(); row++)
			{
				if ( row != startAt )
					writer.write(Env.NL);
				StringBuffer sb = new StringBuffer();
				if (row != -1)
					printData.setRowIndex(row);

				//	for all columns
				boolean first = true;	//	first column to print
				for (int col = 0; col < printFormat.getItemCount(); col++)
				{
					MPrintFormatItem item = printFormat.getItem(col);
					if (item.isPrinted())
					{
						//	column delimiter (comma or tab)
						if (first)
							first = false;
						else
							sb.append(delimiter);
						//	header row
						if (row == -1) {
							createCSVvalue (sb, delimiter,
									printFormat.getItem(col).getPrintName(language));
						} else {
							Object obj = printData.getNode(new Integer(item.getAD_Column_ID()));
							if (!item.isDisplayed(printData))
								obj = null;

							String data = "";
							if (obj == null)
								;
							else if (obj instanceof PrintDataElement) {
								PrintDataElement pde = (PrintDataElement)obj;
								if (item.isTypePrintFormat()) {
									writer.write(sb.toString());
									sb = new StringBuffer();
									writer.write(Env.NL);
									MPrintFormat format = MPrintFormat.get (getCtx(), item.getAD_PrintFormatChild_ID(), false);
									format.setLanguage(language);
									int AD_Column_ID = item.getAD_Column_ID();
									log.info(format + " - Item=" + item.getName() + " (" + AD_Column_ID + ")");
									//
									String recordString = pde.getValueKey();
									int Record_ID = 0;
									try {
										Record_ID = Integer.parseInt(recordString);
									} catch (Exception e) {
										log.log(Level.SEVERE, "Invalid Record Key - " + recordString
												+ " (" + e.getMessage()
												+ ") - AD_Column_ID=" + AD_Column_ID + " - " + item);
									}
									MQuery query = new MQuery (format.getAD_Table_ID());
									query.addRestriction(item.getColumnName(), MQuery.EQUAL, new Integer(Record_ID));
									format.setTranslationViewQuery(query);
									log.fine(query.toString());
									//
									MPrintFormat pf = new MPrintFormat(getCtx(), item.getAD_PrintFormatChild_ID(), null);
									ReportEngine childReport = new ReportEngine(getCtx(), pf, query, getPrintInfo());

									createDelimitedFile(writer, delimiter, language, printHeader, childReport);

								}
								else if (pde.isPKey())
									data = pde.getValueAsString();
								else
									data = pde.getValueDisplay(language);	//	formatted
							} else {
								log.log(Level.SEVERE, "Element not PrintData(Element) " + obj.getClass());
							}
							createCSVvalue (sb, delimiter, data);
						}
					}	//	printed
				}	//	for all columns
				writer.write(sb.toString());
				writer.write(Env.NL);
			}	//	for all rows
			//
			writer.flush();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "(w)", e);
		}
		return true;
	}	//	createDelimitedFile

	/**
	 * 	Add Content to CSV string.
	 *  Encapsulate/mask content in " if required
	 * 	@param sb StringBuffer to add to
	 * 	@param delimiter delimiter
	 * 	@param content column value
	 */
	private void createCSVvalue (StringBuffer sb, char delimiter, String content)
	{
		//	nothing to add
		if (content == null || content.length() == 0)
			return;

		// don't quote tab-delimited file
		if ( delimiter == '\t' )
		{
			sb.append(content);
			return;
		}		
		//
		boolean needMask = false;
		StringBuffer buff = new StringBuffer();
		char chars[] = content.toCharArray();
		for (int i = 0; i < chars.length; i++)
		{
			char c = chars[i];
			if (c == '"')
			{
				needMask = true;
				buff.append(c);		//	repeat twice
			}	//	mask if any control character
			else if (!needMask && (c == delimiter || !Character.isLetterOrDigit(c)))
				needMask = true;
			buff.append(c);
		}

		//	Optionally mask value
		if (needMask)
			sb.append('"').append(buff).append('"');
		else
			sb.append(buff);
	}	//	addCSVColumnValue
	
	/**
	 * Convert File
	 * @param file
	 * @return
	 */
	public BufferedWriter convertFile(File file) {
		Writer fileWriter = null;
		try {
			fileWriter = new OutputStreamWriter(new FileOutputStream(file, false), Ini.getCharset());
		} catch (FileNotFoundException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		}
		//	Validate null
		if(fileWriter == null) { 
			return null;
		}
		return new BufferedWriter(fileWriter);
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}	//	AbstractBatchImport
