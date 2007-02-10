/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.awt.print.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import javax.print.event.*;
import javax.xml.transform.stream.*;
import org.apache.ecs.*;
import org.apache.ecs.xhtml.*;
import org.compiere.model.*;
import org.compiere.print.layout.*;
import org.compiere.process.*;
import org.compiere.util.*;
import com.qoppa.pdf.*;

/**
 *	Report Engine.
 *  For a given PrintFormat,
 *  create a Report
 *  <p>
 *  Change log:
 *  <ul>
 *  <li>2007-02-10 - teo_sarca - [ 1652660 ] Save XML,HTML,CSV should have utf8 charset
 *  </ul>
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ReportEngine.java,v 1.4 2006/10/08 06:52:51 comdivision Exp $
 */
public class ReportEngine implements PrintServiceAttributeListener
{
	/**
	 *	Constructor
	 * 	@param ctx context
	 *  @param pf Print Format
	 *  @param query Optional Query
	 *  @param info print info
	 */
	public ReportEngine (Properties ctx, MPrintFormat pf, MQuery query, PrintInfo info)
	{
		if (pf == null)
			throw new IllegalArgumentException("ReportEngine - no PrintFormat");
		log.info(pf + " -- " + query);
		m_ctx = ctx;
		//
		m_printFormat = pf;
		m_info = info;
		setQuery(query);		//	loads Data
	}	//	ReportEngine

	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (ReportEngine.class);

	/**	Context					*/
	private Properties		m_ctx;

	/**	Print Format			*/
	private MPrintFormat	m_printFormat;
	/** Print Info				*/
	private PrintInfo		m_info;
	/**	Query					*/
	private MQuery			m_query;
	/**	Query Data				*/
	private PrintData		m_printData;
	/** Layout					*/
	private LayoutEngine 	m_layout = null;
	/**	Printer					*/
	private String			m_printerName = Ini.getProperty(Ini.P_PRINTER);
	/**	View					*/
	private View			m_view = null;

	/**
	 * 	Set PrintFormat.
	 *  If Layout was created, re-create layout
	 * 	@param pf print format
	 */
	protected void setPrintFormat (MPrintFormat pf)
	{
		m_printFormat = pf;
		if (m_layout != null)
		{
			setPrintData();
			m_layout.setPrintFormat(pf, false);
			m_layout.setPrintData(m_printData, m_query, true);	//	format changes data
		}
		if (m_view != null)
			m_view.revalidate();
	}	//	setPrintFormat

	/**
	 * 	Set Query and generate PrintData.
	 *  If Layout was created, re-create layout
	 * 	@param query query
	 */
	protected void setQuery (MQuery query)
	{
		m_query = query;
		if (query == null)
			return;
		//
		setPrintData();
		if (m_layout != null)
			m_layout.setPrintData(m_printData, m_query, true);
		if (m_view != null)
			m_view.revalidate();
	}	//	setQuery

	/**
	 * 	Get Query
	 * 	@return query
	 */
	public MQuery getQuery()
	{
		return m_query;
	}	//	getQuery

	/**
	 * 	Set PrintData for Format restricted by Query.
	 * 	Nothing set if there is no query
	 *  Sets m_printData
	 */
	private void setPrintData()
	{
		if (m_query == null)
			return;
		DataEngine de = new DataEngine(m_printFormat.getLanguage());
		setPrintData(de.getPrintData (m_ctx, m_printFormat, m_query));
	//	m_printData.dump();
	}	//	setPrintData

	/**
	 * 	Get PrintData
	 * 	@return print data
	 */
	public PrintData getPrintData()
	{
		return m_printData;
	}	//	getPrintData

	/**
	 * 	Set PrintData
	 * 	@param printData printData
	 */
	public void setPrintData (PrintData printData)
	{
		if (printData == null)
			return;
		m_printData = printData;
	}	//	setPrintData

	
	/**************************************************************************
	 * 	Layout
	 */
	private void layout()
	{
		if (m_printFormat == null)
			throw new IllegalStateException ("No print format");
		if (m_printData == null)
			throw new IllegalStateException ("No print data (Delete Print Format and restart)");
		m_layout = new LayoutEngine (m_printFormat, m_printData, m_query);
	}	//	layout

	/**
	 * 	Get Layout
	 *  @return Layout
	 */
	protected LayoutEngine getLayout()
	{
		if (m_layout == null)
			layout();
		return m_layout;
	}	//	getLayout

	/**
	 * 	Get PrintFormat (Report) Name
	 * 	@return name
	 */
	public String getName()
	{
		return m_printFormat.getName();
	}	//	getName

	/**
	 * 	Get PrintFormat
	 * 	@return print format
	 */
	public MPrintFormat getPrintFormat()
	{
		return m_printFormat;
	}	//	getPrintFormat

	/**
	 * 	Get Print Info
	 *	@return info
	 */
	public PrintInfo getPrintInfo()
	{
		return m_info;
	}	//	getPrintInfo
	
	/**
	 * 	Get PrintLayout (Report) Context
	 * 	@return context
	 */
	public Properties getCtx()
	{
		return m_layout.getCtx();
	}	//	getCtx

	/**
	 * 	Get Row Count
	 * 	@return row count
	 */
	public int getRowCount()
	{
		return m_printData.getRowCount();
	}	//	getRowCount

	/**
	 * 	Get Column Count
	 * 	@return column count
	 */
	public int getColumnCount()
	{
		if (m_layout != null)
			return m_layout.getColumnCount();
		return 0;
	}	//	getColumnCount

	
	/**************************************************************************
	 * 	Get View Panel
	 * 	@return view panel
	 */
	public View getView()
	{
		if (m_layout == null)
			layout();
		if (m_view == null)
			m_view = new View (m_layout);
		return m_view;
	}	//	getView

	
	/**************************************************************************
	 * 	Print Report
	 */
	public void print ()
	{
		log.info(m_info.toString());
		if (m_layout == null)
			layout();
		
		//	Paper Attributes: 	media-printable-area, orientation-requested, media
		PrintRequestAttributeSet prats = m_layout.getPaper().getPrintRequestAttributeSet();
		//	add:				copies, job-name, priority
		if (m_info.isDocumentCopy() || m_info.getCopies() < 1)
			prats.add (new Copies(1));
		else
			prats.add (new Copies(m_info.getCopies()));
		Locale locale = Language.getLoginLanguage().getLocale();
		prats.add(new JobName(m_printFormat.getName(), locale));
		prats.add(PrintUtil.getJobPriority(m_layout.getNumberOfPages(), m_info.getCopies(), true));

		try
		{
			//	PrinterJob
			PrinterJob job = getPrinterJob(m_info.getPrinterName());
		//	job.getPrintService().addPrintServiceAttributeListener(this);
			job.setPageable(m_layout.getPageable(false));	//	no copy
		//	Dialog
			try
			{
				if (m_info.isWithDialog() && !job.printDialog(prats))
					return;
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, "Operating System Print Issue, check & try again", e);
				return;
			}

		//	submit
			boolean printCopy = m_info.isDocumentCopy() && m_info.getCopies() > 1;
			ArchiveEngine.get().archive(m_layout, m_info);
			PrintUtil.print(job, prats, false, printCopy);

			//	Document: Print Copies
			if (printCopy)
			{
				log.info("Copy " + (m_info.getCopies()-1));
				prats.add(new Copies(m_info.getCopies()-1));
				job = getPrinterJob(m_info.getPrinterName());
			//	job.getPrintService().addPrintServiceAttributeListener(this);
				job.setPageable (m_layout.getPageable(true));		//	Copy
				PrintUtil.print(job, prats, false, false);
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	print

	/**
	 * 	Print Service Attribute Listener.
	 * 	@param psae event
	 */
	public void attributeUpdate(PrintServiceAttributeEvent psae)
	{
		/**
PrintEvent on Win32 Printer : \\MAIN\HP LaserJet 5L
PrintServiceAttributeSet - length=2
queued-job-count = 0  (class javax.print.attribute.standard.QueuedJobCount)
printer-is-accepting-jobs = accepting-jobs  (class javax.print.attribute.standard.PrinterIsAcceptingJobs)
PrintEvent on Win32 Printer : \\MAIN\HP LaserJet 5L
PrintServiceAttributeSet - length=1
queued-job-count = 1  (class javax.print.attribute.standard.QueuedJobCount)
PrintEvent on Win32 Printer : \\MAIN\HP LaserJet 5L
PrintServiceAttributeSet - length=1
queued-job-count = 0  (class javax.print.attribute.standard.QueuedJobCount)
		**/
		log.fine("attributeUpdate - " + psae);
	//	PrintUtil.dump (psae.getAttributes());
	}	//	attributeUpdate


	/**
	 * 	Get PrinterJob based on PrinterName
	 * 	@param printerName optional Printer Name
	 * 	@return PrinterJob
	 */
	private PrinterJob getPrinterJob (String printerName)
	{
		if (printerName != null && printerName.length() > 0)
			return CPrinter.getPrinterJob(printerName);
		return CPrinter.getPrinterJob(m_printerName);
	}	//	getPrinterJob

	/**
	 * 	Show Dialog and Set Paper
	 *  Optionally re-calculate layout
	 */
	public void pageSetupDialog ()
	{
		if (m_layout == null)
			layout();
		m_layout.pageSetupDialog(getPrinterJob(m_printerName));
		if (m_view != null)
			m_view.revalidate();
	}	//	pageSetupDialog

	/**
	 * 	Set Printer (name)
	 * 	@param printerName valid printer name
	 */
	public void setPrinterName(String printerName)
	{
		if (printerName == null)
			m_printerName = Ini.getProperty(Ini.P_PRINTER);
		else
			m_printerName = printerName;
	}	//	setPrinterName

	/**
	 * 	Get Printer (name)
	 * 	@return printer name
	 */
	public String getPrinterName()
	{
		return m_printerName;
	}	//	getPrinterName

	
	/**************************************************************************
	 * 	Create HTML File
	 * 	@param file file
	 *  @param onlyTable if false create complete HTML document
	 *  @param language optional language - if null the default language is used to format nubers/dates
	 * 	@return true if success
	 */
	public boolean createHTML (File file, boolean onlyTable, Language language)
	{
		try
		{
			Language lang = language;
			if (lang == null)
				lang = Language.getLoginLanguage();
			Writer fw = new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"); // teo_sarca: modified to save using UTF8 [ 1652660 ]
			return createHTML (new BufferedWriter(fw), onlyTable, lang);
		}
		catch (FileNotFoundException fnfe)
		{
			log.log(Level.SEVERE, "(f) - " + fnfe.toString());
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "(f)", e);
		}
		return false;
	}	//	createHTML

	/**
	 * 	Write HTML to writer
	 * 	@param writer writer
	 *  @param onlyTable if false create complete HTML document
	 *  @param language optional language - if null nubers/dates are not formatted
	 * 	@return true if success
	 */
	public boolean createHTML (Writer writer, boolean onlyTable, Language language)
	{
		try
		{
			table table = new table();
			//
			//	for all rows (-1 = header row)
			for (int row = -1; row < m_printData.getRowCount(); row++)
			{
				tr tr = new tr();
				table.addElement(tr);
				if (row != -1)
					m_printData.setRowIndex(row);
				//	for all columns
				for (int col = 0; col < m_printFormat.getItemCount(); col++)
				{
					MPrintFormatItem item = m_printFormat.getItem(col);
					if (item.isPrinted())
					{
						//	header row
						if (row == -1)
						{
							th th = new th();
							tr.addElement(th);
							th.addElement(Util.maskHTML(item.getPrintName(language)));
						}
						else
						{
							td td = new td();
							tr.addElement(td);
							Object obj = m_printData.getNode(new Integer(item.getAD_Column_ID()));
							if (obj == null)
								td.addElement("&nbsp;");
							else if (obj instanceof PrintDataElement)
							{
								String value = ((PrintDataElement)obj).getValueDisplay(language);	//	formatted
								td.addElement(Util.maskHTML(value));
							}
							else if (obj instanceof PrintData)
							{
								//	ignore contained Data
							}
							else
								log.log(Level.SEVERE, "Element not PrintData(Element) " + obj.getClass());
						}
					}	//	printed
				}	//	for all columns
			}	//	for all rows

			//
			PrintWriter w = new PrintWriter(writer);
			if (onlyTable)
				table.output(w);
			else
			{
				XhtmlDocument doc = new XhtmlDocument();
				doc.appendBody(table);
				doc.output(w);
			}
			w.flush();
			w.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "(w)", e);
		}
		return false;
	}	//	createHTML


	/**************************************************************************
	 * 	Create CSV File
	 * 	@param file file
	 *  @param delimiter delimiter, e.g. comma, tab
	 *  @param language translation language
	 * 	@return true if success
	 */
	public boolean createCSV (File file, char delimiter, Language language)
	{
		try
		{
			Writer fw = new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"); // teo_sarca: modified to save using UTF8 [ 1652660 ]
			return createCSV (new BufferedWriter(fw), delimiter, language);
		}
		catch (FileNotFoundException fnfe)
		{
			log.log(Level.SEVERE, "(f) - " + fnfe.toString());
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "(f)", e);
		}
		return false;
	}	//	createCSV

	/**
	 * 	Write CSV to writer
	 * 	@param writer writer
	 *  @param delimiter delimiter, e.g. comma, tab
	 *  @param language translation language
	 * 	@return true if success
	 */
	public boolean createCSV (Writer writer, char delimiter, Language language)
	{
		if (delimiter == 0)
			delimiter = '\t';
		try
		{
			//	for all rows (-1 = header row)
			for (int row = -1; row < m_printData.getRowCount(); row++)
			{
				StringBuffer sb = new StringBuffer();
				if (row != -1)
					m_printData.setRowIndex(row);

				//	for all columns
				boolean first = true;	//	first column to print
				for (int col = 0; col < m_printFormat.getItemCount(); col++)
				{
					MPrintFormatItem item = m_printFormat.getItem(col);
					if (item.isPrinted())
					{
						//	column delimiter (comma or tab)
						if (first)
							first = false;
						else
							sb.append(delimiter);
						//	header row
						if (row == -1)
							createCSVvalue (sb, delimiter,
								m_printFormat.getItem(col).getPrintName(language));
						else
						{
							Object obj = m_printData.getNode(new Integer(item.getAD_Column_ID()));
							String data = "";
							if (obj == null)
								;
							else if (obj instanceof PrintDataElement)
							{
								PrintDataElement pde = (PrintDataElement)obj;
								if (pde.isPKey())
									data = pde.getValueAsString();
								else
									data = pde.getValueDisplay(language);	//	formatted
							}
							else if (obj instanceof PrintData)
							{
							}
							else
								log.log(Level.SEVERE, "Element not PrintData(Element) " + obj.getClass());
							createCSVvalue (sb, delimiter, data);
						}
					}	//	printed
				}	//	for all columns
				writer.write(sb.toString());
				writer.write(Env.NL);
			}	//	for all rows
			//
			writer.flush();
			writer.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "(w)", e);
		}
		return false;
	}	//	createCSV

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

	
	/**************************************************************************
	 * 	Create XML File
	 * 	@param file file
	 * 	@return true if success
	 */
	public boolean createXML (File file)
	{
		try
		{
			Writer fw = new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"); // teo_sarca: modified to save using UTF8 [ 1652660 ]
			return createXML (new BufferedWriter(fw));
		}
		catch (FileNotFoundException fnfe)
		{
			log.log(Level.SEVERE, "(f) - " + fnfe.toString());
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "(f)", e);
		}
		return false;
	}	//	createXML

	/**
	 * 	Write XML to writer
	 * 	@param writer writer
	 * 	@return true if success
	 */
	public boolean createXML (Writer writer)
	{
		try
		{
			m_printData.createXML(new StreamResult(writer));
			writer.flush();
			writer.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "(w)", e);
		}
		return false;
	}	//	createXML

	
	/**************************************************************************
	 * 	Create PDF file.
	 * 	(created in temporary storage)
	 *	@return PDF file
	 */
	public File getPDF()
	{
		return getPDF (null);
	}	//	getPDF

	/**
	 * 	Create PDF file.
	 * 	@param file file
	 *	@return PDF file
	 */
	public File getPDF (File file)
	{
		try
		{
			if (file == null)
				file = File.createTempFile ("ReportEngine", ".pdf");
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, "", e);
		}
		if (createPDF (file))
			return file;
		return null;
	}	//	getPDF

	/**
	 * 	Create PDF File
	 * 	@param file file
	 * 	@return true if success
	 */
	public boolean createPDF (File file)
	{
		String fileName = null;
		URI uri = null;

		try
		{
			if (file == null)
				file = File.createTempFile ("ReportEngine", ".pdf");
			fileName = file.getAbsolutePath();
			uri = file.toURI();
			if (file.exists())
				file.delete();

		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "file", e);
			return false;
		}
			
		log.fine(uri.toString());

		try
		{
			if (m_layout == null)
				layout ();
			ArchiveEngine.get().archive(m_layout, m_info);
			Document.getPDFAsFile(fileName, m_layout.getPageable(false));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "PDF", e);
			return false;
		}

		File file2 = new File(fileName);
		log.info(file2.getAbsolutePath() + " - " + file2.length());
		return file2.exists();
	}	//	createPDF

	/**
	 * 	Create PDF as Data array
	 *	@return pdf data
	 */
	public byte[] createPDFData ()
	{
		try
		{
			if (m_layout == null)
				layout ();
			return Document.getPDFAsArray(m_layout.getPageable(false));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "PDF", e);
		}
		return null;
	}	//	createPDFData
	
	/**************************************************************************
	 * 	Create PostScript File
	 * 	@param file file
	 * 	@return true if success
	 */
	public boolean createPS (File file)
	{
		try
		{
			return createPS (new FileOutputStream(file));
		}
		catch (FileNotFoundException fnfe)
		{
			log.log(Level.SEVERE, "(f) - " + fnfe.toString());
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "(f)", e);
		}
		return false;
	}	//	createPS

	/**
	 * 	Write PostScript to writer
	 * 	@param fos file output stream
	 * 	@return true if success
	 */
	public boolean createPS (FileOutputStream fos)
	{
		try
		{
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
			StreamPrintService sps = spsfactories[0].getPrintService(fos);
			//	get format
			if (m_layout == null)
				layout();
			//	print it
			sps.createPrintJob().print(m_layout.getPageable(false), 
				new HashPrintRequestAttributeSet());
			//
			fos.flush();
			fos.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "(fos)", e);
		}
		return false;
	}	//	createPS


	/**************************************************************************
	 * 	Get Report Engine for process info 
	 *	@param ctx context
	 *	@param pi process info with AD_PInstance_ID
	 *	@return report engine or null
	 */
	static public ReportEngine get (Properties ctx, ProcessInfo pi)
	{
		int AD_Client_ID = pi.getAD_Client_ID();
		//
		int AD_Table_ID = 0;
		int AD_ReportView_ID = 0;
		String TableName = null;
		String whereClause = "";
		int AD_PrintFormat_ID = 0;
		boolean IsForm = false;
		int Client_ID = -1;

		//	Get AD_Table_ID and TableName
		String sql = "SELECT rv.AD_ReportView_ID,rv.WhereClause,"
			+ " t.AD_Table_ID,t.TableName, pf.AD_PrintFormat_ID, pf.IsForm, pf.AD_Client_ID "
			+ "FROM AD_PInstance pi"
			+ " INNER JOIN AD_Process p ON (pi.AD_Process_ID=p.AD_Process_ID)"
			+ " INNER JOIN AD_ReportView rv ON (p.AD_ReportView_ID=rv.AD_ReportView_ID)"
			+ " INNER JOIN AD_Table t ON (rv.AD_Table_ID=t.AD_Table_ID)"
			+ " LEFT OUTER JOIN AD_PrintFormat pf ON (p.AD_ReportView_ID=pf.AD_ReportView_ID AND pf.AD_Client_ID IN (0,?)) "
			+ "WHERE pi.AD_PInstance_ID=? "		//	#2
			+ "ORDER BY pf.AD_Client_ID DESC, pf.IsDefault DESC";	//	own first
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, pi.getAD_PInstance_ID());
			ResultSet rs = pstmt.executeQuery();
			//	Just get first
			if (rs.next())
			{
				AD_ReportView_ID = rs.getInt(1);		//	required
				whereClause = rs.getString(2);
				if (rs.wasNull())
					whereClause = "";
				//
				AD_Table_ID = rs.getInt(3);
				TableName = rs.getString(4);			//	required for query
				AD_PrintFormat_ID = rs.getInt(5);		//	required
				IsForm = "Y".equals(rs.getString(6));	//	required
				Client_ID = rs.getInt(7);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e1)
		{
			log.log(Level.SEVERE, "(1) - " + sql, e1);
		}
		//	Nothing found
		if (AD_ReportView_ID == 0)
		{
			//	Check Print format in Report Directly
			sql = "SELECT t.AD_Table_ID,t.TableName, pf.AD_PrintFormat_ID, pf.IsForm "
				+ "FROM AD_PInstance pi"
				+ " INNER JOIN AD_Process p ON (pi.AD_Process_ID=p.AD_Process_ID)"
				+ " INNER JOIN AD_PrintFormat pf ON (p.AD_PrintFormat_ID=pf.AD_PrintFormat_ID)"
				+ " INNER JOIN AD_Table t ON (pf.AD_Table_ID=t.AD_Table_ID) "
				+ "WHERE pi.AD_PInstance_ID=?";
			try
			{
				PreparedStatement pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, pi.getAD_PInstance_ID());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
				{
					whereClause = "";
					AD_Table_ID = rs.getInt(1);
					TableName = rs.getString(2);			//	required for query
					AD_PrintFormat_ID = rs.getInt(3);		//	required
					IsForm = "Y".equals(rs.getString(4));	//	required
					Client_ID = AD_Client_ID;
				}
				rs.close();
				pstmt.close();
			}
			catch (SQLException e1)
			{
				log.log(Level.SEVERE, "(2) - " + sql, e1);
			}
			if (AD_PrintFormat_ID == 0)
			{
				log.log(Level.SEVERE, "Report Info NOT found AD_PInstance_ID=" + pi.getAD_PInstance_ID() 
					+ ",AD_Client_ID=" + AD_Client_ID);
				return null;
			}
		}

		//  Create Query from Parameters
		MQuery query = null;
		if (IsForm && pi.getRecord_ID() != 0)	//	Form = one record
			query = MQuery.getEqualQuery(TableName + "_ID", pi.getRecord_ID());
		else
			query = MQuery.get (ctx, pi.getAD_PInstance_ID(), TableName);
		
		//  Add to static where clause from ReportView
		if (whereClause.length() != 0)
			query.addRestriction(whereClause);

		//	Get Print Format
		MPrintFormat format = null;
		Object so = pi.getSerializableObject();
		if (so instanceof MPrintFormat)
			format = (MPrintFormat)so;
		if (format == null && AD_PrintFormat_ID != 0)
		{
			//	We have a PrintFormat with the correct Client
			if (Client_ID == AD_Client_ID)
				format = MPrintFormat.get (ctx, AD_PrintFormat_ID, false);
			else
				format = MPrintFormat.copyToClient (ctx, AD_PrintFormat_ID, AD_Client_ID);
		}
		if (format != null && format.getItemCount() == 0)
		{
			log.info("No Items - recreating:  " + format);
			format.delete(true);
			format = null;
		}
		//	Create Format
		if (format == null && AD_ReportView_ID != 0)
			format = MPrintFormat.createFromReportView(ctx, AD_ReportView_ID, pi.getTitle());
		if (format == null)
			return null;
		//
		PrintInfo info = new PrintInfo (pi);
		info.setAD_Table_ID(AD_Table_ID);
		
		return new ReportEngine(ctx, format, query, info);
	}	//	get

	/*************************************************************************/

	/** Order = 0				*/
	public static final int		ORDER = 0;
	/** Shipment = 1				*/
	public static final int		SHIPMENT = 1;
	/** Invoice = 2				*/
	public static final int		INVOICE = 2;
	/** Project = 3				*/
	public static final int		PROJECT = 3;
	/** RfQ = 4					*/
	public static final int		RFQ = 4;
	/** Remittance = 5			*/
	public static final int		REMITTANCE = 5;
	/** Check = 6				*/
	public static final int		CHECK = 6;
	/** Dunning = 7				*/
	public static final int		DUNNING = 7;
	/** Movement = 8            */
	public static final int     MOVEMENT = 8;

	private static final String[]	DOC_TABLES = new String[] {
		"C_Order_Header_v", "M_InOut_Header_v", "C_Invoice_Header_v", "C_Project_Header_v",
		"C_RfQResponse_v",
		"C_PaySelection_Check_v", "C_PaySelection_Check_v",  
		"C_DunningRunEntry_v", "M_Movement_Header_V" };
	private static final String[]	DOC_BASETABLES = new String[] {
		"C_Order", "M_InOut", "C_Invoice", "C_Project",
		"C_RfQResponse",
		"C_PaySelectionCheck", "C_PaySelectionCheck", 
		"C_DunningRunEntry", "M_Movement" };
	private static final String[]	DOC_IDS = new String[] {
		"C_Order_ID", "M_InOut_ID", "C_Invoice_ID", "C_Project_ID",
		"C_RfQResponse_ID",
		"C_PaySelectionCheck_ID", "C_PaySelectionCheck_ID", 
		"C_DunningRunEntry_ID", "M_Movement_ID"  };
	private static final int[]	DOC_TABLE_ID = new int[] {
		X_C_Order.Table_ID, X_M_InOut.Table_ID, X_C_Invoice.Table_ID, X_C_Project.Table_ID,
		X_C_RfQResponse.Table_ID,
		X_C_PaySelectionCheck.Table_ID, X_C_PaySelectionCheck.Table_ID, 
		X_C_DunningRunEntry.Table_ID, X_M_Movement.Table_ID  };

	
	/**************************************************************************
	 * 	Get Document Print Engine for Document Type.
	 * 	@param ctx context
	 * 	@param type document type
	 * 	@param Record_ID id
	 * 	@return Report Engine or null
	 */
	public static ReportEngine get (Properties ctx, int type, int Record_ID)
	{
		//	Order - Print Shipment or Invoice
		if (type == ORDER)
		{
			int[] what = getDocumentWhat (Record_ID);
			if (what != null)
			{
				type = what[0];
				Record_ID = what[1];
			}
		}	//	Order
		//
		String JobName = DOC_BASETABLES[type] + "_Print";
		int AD_PrintFormat_ID = 0;
		int C_BPartner_ID = 0;
		String DocumentNo = null;
		int copies = 1;

		//	Language
		MClient client = MClient.get(ctx);
		Language language = client.getLanguage();	
		//	Get Document Info
		String sql = null;
		if (type == CHECK)
			sql = "SELECT bad.Check_PrintFormat_ID,"								//	1
				+ "	c.IsMultiLingualDocument,bp.AD_Language,bp.C_BPartner_ID,d.DocumentNo "		//	2..5
				+ "FROM C_PaySelectionCheck d"
				+ " INNER JOIN C_PaySelection ps ON (d.C_PaySelection_ID=ps.C_PaySelection_ID)"
				+ " INNER JOIN C_BankAccountDoc bad ON (ps.C_BankAccount_ID=bad.C_BankAccount_ID AND d.PaymentRule=bad.PaymentRule)"
				+ " INNER JOIN AD_Client c ON (d.AD_Client_ID=c.AD_Client_ID)"
				+ " INNER JOIN C_BPartner bp ON (d.C_BPartner_ID=bp.C_BPartner_ID) "
				+ "WHERE d.C_PaySelectionCheck_ID=?";		//	info from BankAccount
		else if (type == DUNNING)
			sql = "SELECT dl.Dunning_PrintFormat_ID,"
				+ " c.IsMultiLingualDocument,bp.AD_Language,bp.C_BPartner_ID,dr.DunningDate "
				+ "FROM C_DunningRunEntry d"
				+ " INNER JOIN AD_Client c ON (d.AD_Client_ID=c.AD_Client_ID)"
				+ " INNER JOIN C_BPartner bp ON (d.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN C_DunningRun dr ON (d.C_DunningRun_ID=dr.C_DunningRun_ID)"
				+ " INNER JOIN C_DunningLevel dl ON (dl.C_DunningLevel_ID=dr.C_DunningLevel_ID) "
				+ "WHERE d.C_DunningRunEntry_ID=?";			//	info from Dunning
		else if (type == REMITTANCE)
			sql = "SELECT pf.Remittance_PrintFormat_ID,"
				+ " c.IsMultiLingualDocument,bp.AD_Language,bp.C_BPartner_ID,d.DocumentNo "
				+ "FROM C_PaySelectionCheck d"
				+ " INNER JOIN AD_Client c ON (d.AD_Client_ID=c.AD_Client_ID)"
				+ " INNER JOIN AD_PrintForm pf ON (c.AD_Client_ID=pf.AD_Client_ID)"
				+ " INNER JOIN C_BPartner bp ON (d.C_BPartner_ID=bp.C_BPartner_ID) "
				+ "WHERE d.C_PaySelectionCheck_ID=?"		//	info from PrintForm
				+ " AND pf.AD_Org_ID IN (0,d.AD_Org_ID) ORDER BY pf.AD_Org_ID DESC";
		else if (type == PROJECT)
			sql = "SELECT pf.Project_PrintFormat_ID,"
				+ " c.IsMultiLingualDocument,bp.AD_Language,bp.C_BPartner_ID,d.Value "
				+ "FROM C_Project d"
				+ " INNER JOIN AD_Client c ON (d.AD_Client_ID=c.AD_Client_ID)"
				+ " INNER JOIN AD_PrintForm pf ON (c.AD_Client_ID=pf.AD_Client_ID)"
				+ " LEFT OUTER JOIN C_BPartner bp ON (d.C_BPartner_ID=bp.C_BPartner_ID) "
				+ "WHERE d.C_Project_ID=?"					//	info from PrintForm
				+ " AND pf.AD_Org_ID IN (0,d.AD_Org_ID) ORDER BY pf.AD_Org_ID DESC";
		else if (type == RFQ)
			sql = "SELECT COALESCE(t.AD_PrintFormat_ID, pf.AD_PrintFormat_ID),"
				+ " c.IsMultiLingualDocument,bp.AD_Language,bp.C_BPartner_ID,rr.Name "
				+ "FROM C_RfQResponse rr"
				+ " INNER JOIN C_RfQ r ON (rr.C_RfQ_ID=r.C_RfQ_ID)"
				+ " INNER JOIN C_RfQ_Topic t ON (r.C_RfQ_Topic_ID=t.C_RfQ_Topic_ID)"
				+ " INNER JOIN AD_Client c ON (rr.AD_Client_ID=c.AD_Client_ID)"
				+ " INNER JOIN C_BPartner bp ON (rr.C_BPartner_ID=bp.C_BPartner_ID),"
				+ " AD_PrintFormat pf "
				+ "WHERE pf.AD_Client_ID IN (0,rr.AD_Client_ID)"
				+ " AND pf.AD_Table_ID=725 AND pf.IsTableBased='N'"	//	from RfQ PrintFormat
				+ " AND rr.C_RfQResponse_ID=? "				//	Info from RfQTopic
				+ "ORDER BY t.AD_PrintFormat_ID, pf.AD_Client_ID DESC, pf.AD_Org_ID DESC";
        else if (type == MOVEMENT)
            sql = "SELECT pf.Movement_PrintFormat_ID,"
                + " c.IsMultiLingualDocument, COALESCE(dt.DocumentCopies,0) "
                + "FROM M_Movement d"
                + " INNER JOIN AD_Client c ON (d.AD_Client_ID=c.AD_Client_ID)"
                + " INNER JOIN AD_PrintForm pf ON (c.AD_Client_ID=pf.AD_Client_ID)"
                + " LEFT OUTER JOIN C_DocType dt ON (d.C_DocType_ID=dt.C_DocType_ID) "
                + "WHERE d.M_Movement_ID=?"                 //  info from PrintForm
                + " AND pf.AD_Org_ID IN (0,d.AD_Org_ID) ORDER BY pf.AD_Org_ID DESC";
		else	//	Get PrintFormat from Org or 0 of document client
			sql = "SELECT pf.Order_PrintFormat_ID,pf.Shipment_PrintFormat_ID,"		//	1..2
				//	Prio: 1. BPartner 2. DocType, 3. PrintFormat (Org)	//	see InvoicePrint
				+ " COALESCE (bp.Invoice_PrintFormat_ID,dt.AD_PrintFormat_ID,pf.Invoice_PrintFormat_ID)," // 3
				+ " pf.Project_PrintFormat_ID, pf.Remittance_PrintFormat_ID,"		//	4..5
				+ " c.IsMultiLingualDocument, bp.AD_Language,"						//	6..7
				+ " COALESCE(dt.DocumentCopies,0)+COALESCE(bp.DocumentCopies,1), " 	// 	8
				+ " dt.AD_PrintFormat_ID,bp.C_BPartner_ID,d.DocumentNo "			//	9..11
				+ "FROM " + DOC_BASETABLES[type] + " d"
				+ " INNER JOIN AD_Client c ON (d.AD_Client_ID=c.AD_Client_ID)"
				+ " INNER JOIN AD_PrintForm pf ON (c.AD_Client_ID=pf.AD_Client_ID)"
				+ " INNER JOIN C_BPartner bp ON (d.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " LEFT OUTER JOIN C_DocType dt ON (d.C_DocType_ID=dt.C_DocType_ID) "
				+ "WHERE d." + DOC_IDS[type] + "=?"			//	info from PrintForm
				+ " AND pf.AD_Org_ID IN (0,d.AD_Org_ID) "
				+ "ORDER BY pf.AD_Org_ID DESC";
		//
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Record_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())	//	first record only
			{
				if (type == CHECK || type == DUNNING || type == REMITTANCE 
					|| type == PROJECT || type == RFQ)
				{
					AD_PrintFormat_ID = rs.getInt(1);
					copies = 1;
					//	Set Language when enabled
					String AD_Language = rs.getString(3);
					if (AD_Language != null)// && "Y".equals(rs.getString(2)))	//	IsMultiLingualDocument
						language = Language.getLanguage(AD_Language);
					C_BPartner_ID = rs.getInt(4);
					if (type == DUNNING)
					{
						Timestamp ts = rs.getTimestamp(5);
						DocumentNo = ts.toString();
					}
					else
						DocumentNo = rs.getString(5);
				}
                else if (type == MOVEMENT) {
                    AD_PrintFormat_ID = rs.getInt(1);
                    log.fine("PF 2 ="+AD_PrintFormat_ID);
                    //TODO VHARCQ SQL needs change for copies  VHARCQ= rs.getInt(8);
                }
				else
				{
					//	Set PrintFormat
					AD_PrintFormat_ID = rs.getInt(type+1);
					if (rs.getInt(9) != 0)		//	C_DocType.AD_PrintFormat_ID
						AD_PrintFormat_ID = rs.getInt(9);
					copies = rs.getInt(8);
					//	Set Language when enabled
					String AD_Language = rs.getString(7);
					if (AD_Language != null) // && "Y".equals(rs.getString(6)))	//	IsMultiLingualDocument
						language = Language.getLanguage(AD_Language);
					C_BPartner_ID = rs.getInt(10);
					DocumentNo = rs.getString(11);
				}
			}
			rs.close();
			pstmt.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Record_ID=" + Record_ID + ", SQL=" + sql, e);
		}
		if (AD_PrintFormat_ID == 0)
		{
			log.log(Level.SEVERE, "No PrintFormat found for Type=" + type + ", Record_ID=" + Record_ID);
			return null;
		}

		//	Get Format & Data
		MPrintFormat format = MPrintFormat.get (ctx, AD_PrintFormat_ID, false);
		format.setLanguage(language);		//	BP Language if Multi-Lingual
	//	if (!Env.isBaseLanguage(language, DOC_TABLES[type]))
			format.setTranslationLanguage(language);
		//	query
		MQuery query = new MQuery(DOC_TABLES[type]);
		query.addRestriction(DOC_IDS[type], MQuery.EQUAL, new Integer(Record_ID));
	//	log.config( "ReportCtrl.startDocumentPrint - " + format, query + " - " + language.getAD_Language());
		//
		if (DocumentNo == null || DocumentNo.length() == 0)
			DocumentNo = "DocPrint";
		PrintInfo info = new PrintInfo(
			DocumentNo,
			DOC_TABLE_ID[type],
			Record_ID,
			C_BPartner_ID);
		info.setCopies(copies);
		info.setDocumentCopy(false);		//	true prints "Copy" on second
		info.setPrinterName(format.getPrinterName());
		
		//	Engine
		ReportEngine re = new ReportEngine(ctx, format, query, info);
		return re;
	}	//	get

	/**
	 *	Determine what Order document to print.
	 *  @param C_Order_ID id
	 *	@return int Array with [printWhat, ID]
	 */
	private static int[] getDocumentWhat (int C_Order_ID)
	{
		int[] what = new int[2];
		what[0] = ORDER;
		what[1] = C_Order_ID;
		//
		String sql = "SELECT dt.DocSubTypeSO "
			+ "FROM C_DocType dt, C_Order o "
			+ "WHERE o.C_DocType_ID=dt.C_DocType_ID"
			+ " AND o.C_Order_ID=?";
		String DocSubTypeSO = null;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_Order_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				DocSubTypeSO = rs.getString(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e1)
		{
			log.log(Level.SEVERE, "(1) - " + sql, e1);
			return null;		//	error
		}
		if (DocSubTypeSO == null)
			DocSubTypeSO = "";
		//	WalkIn Receipt, WalkIn Invoice,
		if (DocSubTypeSO.equals("WR") || DocSubTypeSO.equals("WI"))
			what[0] = INVOICE;
		//	WalkIn Pickup,
		else if (DocSubTypeSO.equals("WP"))
			what[0] = SHIPMENT;
		//	Offer Binding, Offer Nonbinding, Standard Order
		else
			return what;

		//	Get Record_ID of Invoice/Receipt
		if (what[0] == INVOICE)
			sql = "SELECT C_Invoice_ID REC FROM C_Invoice WHERE C_Order_ID=?"	//	1
				+ " ORDER BY C_Invoice_ID DESC";
		else
			sql = "SELECT M_InOut_ID REC FROM M_InOut WHERE C_Order_ID=?" 	//	1
				+ " ORDER BY M_InOut_ID DESC";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_Order_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
			//	if (i == 1 && ADialog.ask(0, null, what[0] == INVOICE ? "PrintOnlyRecentInvoice?" : "PrintOnlyRecentShipment?")) break;
				what[1] = rs.getInt(1);
			}
			else	//	No Document Found
				what[0] = ORDER;

			rs.close();
			pstmt.close();
		}
		catch (SQLException e2)
		{
			log.log(Level.SEVERE, "(2) - " + sql, e2);
			return null;
		}
		log.fine("Order => " + what[0] + " ID=" + what[1]);
		return what;
	}	//	getDocumentWhat

	/**
	 * 	Print Confirm.
	 *  Update Date Printed
	 * 	@param type document type
	 * 	@param Record_ID record id
	 */
	public static void printConfirm (int type, int Record_ID)
	{
		StringBuffer sql = new StringBuffer();
		if (type == ORDER || type == SHIPMENT || type == INVOICE)
			sql.append("UPDATE ").append(DOC_BASETABLES[type])
				.append(" SET DatePrinted=SysDate, IsPrinted='Y' WHERE ")
				.append(DOC_IDS[type]).append("=").append(Record_ID);
		//
		if (sql.length() > 0)
		{
			int no = DB.executeUpdate(sql.toString(), null);
			if (no != 1)
				log.log(Level.SEVERE, "Updated records=" + no + " - should be just one");
		}
	}	//	printConfirm
	
	
	/*************************************************************************
	 * 	Test
	 * 	@param args args
	 */
	public static void main(String[] args)
	{
		org.compiere.Adempiere.startupEnvironment(true);
		//
		int AD_Table_ID = 100;
		MQuery q = new MQuery("AD_Table");
		q.addRestriction("AD_Table_ID", "<", 108);
		//
		MPrintFormat f = MPrintFormat.createFromTable(Env.getCtx(), AD_Table_ID);
		PrintInfo i = new PrintInfo("test", AD_Table_ID, 108, 0);
		i.setAD_Table_ID(AD_Table_ID);
		ReportEngine re = new ReportEngine(Env.getCtx(), f, q, i);
		re.layout();
		/**
		re.createCSV(new File("C:\\Temp\\test.csv"), ',', Language.getLanguage());
		re.createHTML(new File("C:\\Temp\\test.html"), false, Language.getLanguage());
		re.createXML(new File("C:\\Temp\\test.xml"));
		re.createPS(new File ("C:\\Temp\\test.ps"));
		re.createPDF(new File("C:\\Temp\\test.pdf"));
		/****/
		re.print();
	//	re.print(true, 1, false, "Epson Stylus COLOR 900 ESC/P 2");		//	Dialog
	//	re.print(true, 1, false, "HP LaserJet 3300 Series PCL 6");		//	Dialog
	//	re.print(false, 1, false, "Epson Stylus COLOR 900 ESC/P 2");	//	Dialog
		System.exit(0);
	}	//	main
	
}	//	ReportEngine
