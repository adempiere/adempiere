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

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.JobPriority;
import javax.print.event.PrintServiceAttributeEvent;
import javax.print.event.PrintServiceAttributeListener;

import org.adempiere.pdf.ITextDocument;
import org.compiere.model.MClient;
import org.compiere.model.MDunningRunEntry;
import org.compiere.model.MInOut;
import org.compiere.model.MInvoice;
import org.compiere.model.MMovement;
import org.compiere.model.MOrder;
import org.compiere.model.MPaySelectionCheck;
import org.compiere.model.MProject;
import org.compiere.model.MQuery;
import org.compiere.model.MRfQResponse;
import org.compiere.model.MTable;
import org.compiere.model.PrintInfo;
import org.compiere.print.layout.LayoutEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Language;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.X_HR_PaySelectionCheck;
import org.eevolution.model.X_PP_Order;  // to be changed by MPPOrder
import org.spin.util.ExportFormatCSV;
import org.spin.util.ExportFormatHTML;
import org.spin.util.ExportFormatPDF;
import org.spin.util.ExportFormatPS;
import org.spin.util.ExportFormatXLS;
import org.spin.util.ExportFormatXLSX;
import org.spin.util.ExportFormatXML;

/**
 *	Report Engine.
 *  For a given PrintFormat,
 *  create a Report
 *  <p>
 *  Change log:
 *  <ul>
 *  <li>2007-02-12 - teo_sarca - [ 1658127 ] Select charset encoding on import
 *  <li>2007-02-10 - teo_sarca - [ 1652660 ] Save XML,HTML,CSV should have utf8 charset
 *  <li>2009-02-06 - globalqss - [ 2574162 ] Priority to choose invoice print format not working
 *  <li>2009-07-10 - trifonnt - [ 2819637 ] Wrong print format on non completed order
 *  </ul>
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ReportEngine.java,v 1.4 2006/10/08 06:52:51 comdivision Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>BF [ 2828300 ] Error when printformat table differs from DOC_TABLES
 * 				https://sourceforge.net/tracker/?func=detail&aid=2828300&group_id=176962&atid=879332
 * 			<li>BF [ 2828886 ] Problem with reports from temporary tables
 * 				https://sourceforge.net/tracker/?func=detail&atid=879332&aid=2828886&group_id=176962
 * 
 *  FR 2872010 - Dunning Run for a complete Dunning (not just level) - Developer: Carlos Ruiz - globalqss - Sponsor: Metas
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>BR [ 237 ] Same Print format but distinct report view
 * 		@see https://github.com/adempiere/adempiere/issues/237
 * 		<li>FR [ 295 ] Report viewer re-query
 * 		@see https://github.com/adempiere/adempiere/issues/295
 * 		<li>FR [ 238 ] Is Summary property by default in Print Format (Set default summary property from print format)
 * 		@see https://github.com/adempiere/adempiere/issues/238
 * 		<a href="https://github.com/adempiere/adempiere/issues/1400">
 * 		@see FR [ 1400 ] Dynamic report export</a>
 * 	@author Raul Capecce
 * 	    <li>FR [ 1305 ] Setting zoom glass to a dark theme resource</li>
 * 	    @see https://github.com/adempiere/adempiere/issues/1305
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
		this(ctx, pf, query, info, null);
	}	//	ReportEngine


	/**
	 * Constructor
	 * @param ctx
	 * @param pf
	 * @param query
	 * @param pInfo
	 * @param info
	 */
	public ReportEngine ( Properties ctx,MPrintFormat pf,MQuery query,ProcessInfo pInfo, PrintInfo info ){
		this(ctx, pf, query, info, null);
		this.setProcessInfo(pInfo);
	}


	/**
	 *	Constructor
	 * 	@param ctx context
	 *  @param pf Print Format
	 *  @param query Optional Query
	 *  @param info print info
	 *  @param trxName
	 */
	public ReportEngine (Properties ctx, MPrintFormat pf, MQuery query, PrintInfo info, String trxName)
	{
		if (pf == null)
			throw new IllegalArgumentException("ReportEngine - no PrintFormat");
		log.info(pf + " -- " + query);
		m_ctx = ctx;
		//
		m_printFormat = pf;
		m_info = info;
		m_trxName = trxName;
		//	FR [ 238 ] Set default summary from print format
		setSummary(m_printFormat.isSummary());
		setQuery(query);		//	loads Data		
	}	//	ReportEngine

	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (ReportEngine.class);

	/**	Context					*/
	protected Properties		m_ctx;

	/**	Print Format			*/
	protected MPrintFormat	m_printFormat;
	/** Print Info				*/
	private PrintInfo		m_info;
	/**	Query					*/
	protected MQuery			m_query;
	/**	Query Data				*/
	private PrintData		m_printData;
	/** Layout					*/
	private LayoutEngine 	m_layout = null;
	/**	Printer					*/
	private String			m_printerName = Ini.getProperty(Ini.P_PRINTER);
	/**	View					*/
	private View			m_view = null;
	/** Transaction Name 		*/
	protected String 			m_trxName = null;
	/** Where filter */
	private String 			m_whereExtended = null;
	/** Window */
	private int m_windowNo = -1;

	private ProcessInfo processInfo = null ;
	
	private boolean m_summary = false;
	//	FR [ 237 ]
	private int 			m_AD_ReportView_ID = 0;
	
	/**
	 * Set Optional Report View
	 * @param p_AD_ReportView_ID
	 */
	public void setAD_ReportView_ID(int p_AD_ReportView_ID) {
		m_AD_ReportView_ID = p_AD_ReportView_ID;
	}
	
	/**
	 * Get Optional Report View
	 * @return
	 */
	public int getAD_ReportView_ID() {
		return m_AD_ReportView_ID;
	}

	/* Explicit printer job priority	*/
	private int m_priority = 0;
	
	public void setPriority(int priority) {
		if ( priority > 0 && priority <= 100 )
			m_priority = priority;
	}

	/**
	 * 	Set PrintFormat.
	 *  If Layout was created, re-create layout
	 * 	@param pf print format
	 */
	public void setPrintFormat (MPrintFormat pf)
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
	public void setQuery (MQuery query)
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
	protected void setPrintData()
	{
		if (m_query == null)
			return;
		
		DataEngine de = new DataEngine(m_printFormat.getLanguage(),m_trxName);
		//	FR [ 237 ]
		setPrintData(de.getPrintData (m_ctx, m_printFormat, m_query, m_summary, getAD_ReportView_ID()));
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
		m_layout = new LayoutEngine (m_printFormat, m_printData, m_query, m_info, m_trxName);
	}	//	layout

	/**
	 * 	Get Layout
	 *  @return Layout
	 */
	public LayoutEngine getLayout()
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
		return getLayout().getCtx();
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
		return m_printData.getColumnInfo().length;
		/*
		if (m_layout != null)
			return m_layout.getColumnCount();
		return 0;
		*/
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
		JobPriority priority = PrintUtil.getJobPriority(m_layout.getNumberOfPages(), m_info.getCopies(), true);
		// check for directly set priority
		if (m_priority  > 0 && m_priority <= 100)
			priority = new JobPriority(m_priority);
		prats.add(priority);

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
			PrintUtil.print(job, prats, false, !m_info.isAsync() || printCopy);

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
	@Deprecated
	public boolean createHTML (File file, boolean onlyTable) {
		return new ExportFormatHTML(getCtx(), this).createHTML(file, onlyTable, null);
	}
	
	/**************************************************************************
	 * 	Create HTML File
	 * 	@param file file
	 *  @param onlyTable if false create complete HTML document
	 *  @param language optional language - if null the default language is used to format nubers/dates
	 *  @param extension optional extension for html output
	 * 	@return true if success
	 */
	@Deprecated
	public boolean createHTML (File file, boolean onlyTable, IHTMLExtension extension) {
		return new ExportFormatHTML(m_ctx, this).createHTML(file, onlyTable, extension);
	}	//	createHTML

	/**************************************************************************
	 * 	Create CSV File
	 * 	@param file file
	 *  @param delimiter delimiter, e.g. comma, tab
	 *  @param language translation language
	 * 	@return true if success
	 */
	@Deprecated
	public boolean createCSV (File file, char delimiter) {
		return new ExportFormatCSV(getCtx(), this).createCSV(file, delimiter, null);
	}	//	createCSV

	/**
	 * 	Write CSV to writer
	 * 	@param writer writer
	 *  @param delimiter delimiter, e.g. comma, tab
	 *  @param language translation language
	 * 	@return true if success
	 */
	@Deprecated
	public boolean createCSV (Writer writer, char delimiter, Language language) {
		return  createCSV(writer, delimiter, language, true);
	}	//	createCSV
	
	/**
	 * 	Write CSV to writer
	 * 	@param writer writer
	 *  @param delimiter delimiter, e.g. comma, tab
	 *  @param language translation language
	 *  @param printHeader include column headers
	 * 	@return true if success
	 */
	@Deprecated
	public boolean createCSV (Writer writer, char delimiter, Language language, boolean printHeader) {
		return new ExportFormatCSV(getCtx(), this).createCSV(writer, delimiter, language, printHeader);
	}	//	createCSV
	
	/**
	 * Create XML from File
	 */
	@Deprecated
	public boolean createXML(File file) {
		return new ExportFormatXML(getCtx(), this).exportToFile(file);
	}
	
	/**
	 * Create XML from Writer
	 */
	@Deprecated
	public boolean createXML(Writer writer) {
		return new ExportFormatXML(getCtx(), this).createXML(writer);
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
	@Deprecated
	public boolean createDelimitedFile (Writer writer, char delimiter, Language language, boolean printHeader) {
		return new ExportFormatXML(getCtx(), this).createDelimitedFile(writer, delimiter, language, printHeader);
	}
	
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
	public File getPDF (File file) {
		try {
			if (file == null) {
				file = File.createTempFile ("ReportEngine", ".pdf");
			}
			//ordinary temp file - rename it
			if (!(file.getName().contains("_") || file.getName().contains("-"))) {
				file = rename(file);
			}
		} catch (IOException e) {
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
	public boolean createPDF (File file) {
		URI uri = null;
		try {
			if (file == null) {
				file = File.createTempFile ("ReportEngine", ".pdf");
			}
			uri = file.toURI();
			if (file.exists()) {
				file.delete();
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "file", e);
			return false;
		}
		log.fine(uri.toString());
		return new ExportFormatPDF(m_ctx, this).createPDF(file);
	}	//	createPDF

	/**
	 * 	Create PDF as Data array
	 *	@return pdf data
	 */
	public byte[] createPDFData () {
		try {
			if (m_layout == null)
				layout ();
			return new ITextDocument().getPDFAsArray(m_layout.getPageable(false));
		} catch (Exception e) {
			log.log(Level.SEVERE, "PDF", e);
		}
		return null;
	}	//	createPDFData
	
	/**************************************************************************
	 * 	Create PostScript File
	 * 	@param file file
	 * 	@return true if success
	 */
	public boolean createPS (File file) {
		return new ExportFormatPS(m_ctx, this).exportToFile(file);
	}	//	createPS
	
	/**
	 * Create Excel file
	 * @param outFile output file
	 * @param language
	 * @throws Exception if error
	 */
	public void createXLS(File outFile) {
		new ExportFormatXLS(m_ctx, this).exportToFile(outFile);
	}
	
	/**
	 * Create Excel file
	 * @param outFile
	 */
	public void createXLSX(File outFile) {
		new ExportFormatXLSX(m_ctx, this).exportToFile(outFile);
	}
	
	/**
	 * Create Excel file
	 * @param outFile output file
	 * @param language
	 * @throws Exception if error
	 */
	@Deprecated
	public void createXLS(File outFile, Language language) throws Exception {
		new ExportFormatXLS(m_ctx, this).exportToFile(outFile, language);
	}
	
	/**
	 * 
	 * @param outFile
	 * @param language
	 * @throws Exception
	 */
	@Deprecated
	public void createXLSX(File outFile, Language language) throws Exception {
		new ExportFormatXLSX(m_ctx, this).exportToFile(outFile, language);
	}
	
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
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, pi.getAD_PInstance_ID());
			rs = pstmt.executeQuery();
			//	Just get first
			if (rs.next())
			{
				AD_ReportView_ID = rs.getInt(1);		//	required
				whereClause = rs.getString(2);
				if (rs.wasNull())
					whereClause = "";

				whereClause = Env.parseContext(ctx, 0, whereClause, false);
				//
				AD_Table_ID = rs.getInt(3);
				TableName = rs.getString(4);			//	required for query
				AD_PrintFormat_ID = rs.getInt(5);		//	required
				IsForm = "Y".equals(rs.getString(6));	//	required
				Client_ID = rs.getInt(7);
			}
		}
		catch (SQLException e1)
		{
			log.log(Level.SEVERE, "(1) - " + sql, e1);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
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
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, pi.getAD_PInstance_ID());
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					whereClause = "";
					AD_Table_ID = rs.getInt(1);
					TableName = rs.getString(2);			//	required for query
					AD_PrintFormat_ID = rs.getInt(3);		//	required
					IsForm = "Y".equals(rs.getString(4));	//	required
					Client_ID = AD_Client_ID;
				}
			}
			catch (SQLException e1)
			{
				log.log(Level.SEVERE, "(2) - " + sql, e1);
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
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
		if (IsForm && pi.getRecord_ID() != 0		//	Form = one record
				&& !TableName.startsWith("T_") )	//	Not temporary table - teo_sarca, BF [ 2828886 ]
		{
			MTable table = MTable.get(ctx, AD_Table_ID);
			String columnKey = null;
			if(table.isSingleKey())
				columnKey = table.getKeyColumns()[0];
			else 
				columnKey = TableName + "_ID";

			query = MQuery.getEqualQuery(columnKey, pi.getRecord_ID());
		}
		else
		{
			query = MQuery.get (ctx, pi.getAD_PInstance_ID(), TableName);
		}
		
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
			
		format.setTranslationLanguage(format.getLanguage());	
		//
		PrintInfo info = new PrintInfo (pi);
		info.setAD_Table_ID(AD_Table_ID);
		
		query.setWindowNo(pi.getWindowNo());

		//	FR [ 295 ]
		ReportEngine re = new ReportEngine(ctx, format, query, info, pi.getTransactionName());
		//	Set Process Information
		re.setProcessInfo(pi);
		return re;
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
	/** Manufacturing Order = 8  */
	public static final int		MANUFACTURING_ORDER = 8;
	/** Distribution Order = 9  */
	public static final int		DISTRIBUTION_ORDER = 9;
	/** Payroll Check = 10  */
	public static final int		HR_CHECK = 10;	

    public static final int     HR_REMITTANCE = 11;

	public static final int     MOVEMENT = 12;
	

//	private static final String[]	DOC_TABLES = new String[] {
//		"C_Order_Header_v", "M_InOut_Header_v", "C_Invoice_Header_v", "C_Project_Header_v",
//		"C_RfQResponse_v",
//		"C_PaySelection_Check_v", "C_PaySelection_Check_v",  
//		"C_DunningRunEntry_v","PP_Order_Header_v","DD_Order_Header_v" };
	private static final String[]	DOC_BASETABLES = new String[] {
		"C_Order", "M_InOut", "C_Invoice", "C_Project",
		"C_RfQResponse",
		"C_PaySelectionCheck", "C_PaySelectionCheck", 
		"C_DunningRunEntry","PP_Order", "DD_Order", "HR_PaySelectionCheck","HR_PaySelectionCheck", "M_Movement"};
	private static final String[]	DOC_IDS = new String[] {
		"C_Order_ID", "M_InOut_ID", "C_Invoice_ID", "C_Project_ID",
		"C_RfQResponse_ID",
		"C_PaySelectionCheck_ID", "C_PaySelectionCheck_ID", 
		"C_DunningRunEntry_ID" , "PP_Order_ID" , "DD_Order_ID" , "HR_PaySelectionCheck_ID", "HR_PaySelectionCheck_ID" , "M_Movement_ID"};
	private static final int[]	DOC_TABLE_ID = new int[] {
		MOrder.Table_ID, MInOut.Table_ID, MInvoice.Table_ID, MProject.Table_ID,
		MRfQResponse.Table_ID,
		MPaySelectionCheck.Table_ID, MPaySelectionCheck.Table_ID, 
		MDunningRunEntry.Table_ID, X_PP_Order.Table_ID, MDDOrder.Table_ID , X_HR_PaySelectionCheck.Table_ID ,  X_HR_PaySelectionCheck.Table_ID , MMovement.Table_ID};

	/**************************************************************************
	 * 	Get Document Print Engine for Document Type.
	 * 	@param ctx context
	 * 	@param type document type
	 * 	@param Record_ID id
	 * 	@return Report Engine or null
	 */
	public static ReportEngine get (Properties ctx, int type, int Record_ID)
	{
		return get(ctx, type, Record_ID, null);
	}
	
	/**************************************************************************
	 * 	Get Document Print Engine for Document Type.
	 * 	@param ctx context
	 * 	@param type document type
	 * 	@param Record_ID id
	 *  @param trxName
	 * 	@return Report Engine or null
	 */
	public static ReportEngine get (Properties ctx, int type, int Record_ID, String trxName)
	{
		if (Record_ID < 1)
		{
			log.log(Level.WARNING, "No PrintFormat for Record_ID=" + Record_ID 
					+ ", Type=" + type);
			return null;
		}
		//	Order - Print Shipment or Invoice
		if (type == ORDER || type == SHIPMENT)
		{
			int[] what = getDocumentWhat (type, Record_ID);
			if (what != null)
			{
				type = what[0];
				Record_ID = what[1];
			}
		}	//	Order

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
        else if (type == HR_CHECK)
            sql = "SELECT bad.Check_PrintFormat_ID,"								//	1
                    + "	c.IsMultiLingualDocument,bp.AD_Language,bp.C_BPartner_ID,d.DocumentNo "		//	2..5
                    + "FROM HR_PaySelectionCheck d"
                    + " INNER JOIN HR_PaySelection ps ON (d.HR_PaySelection_ID=ps.HR_PaySelection_ID)"
                    + " INNER JOIN C_BankAccountDoc bad ON (ps.C_BankAccount_ID=bad.C_BankAccount_ID AND d.PaymentRule=bad.PaymentRule)"
                    + " INNER JOIN AD_Client c ON (d.AD_Client_ID=c.AD_Client_ID)"
                    + " INNER JOIN C_BPartner bp ON (d.C_BPartner_ID=bp.C_BPartner_ID) "
                    + "WHERE d.HR_PaySelectionCheck_ID=?";		//	info from BankAccount
		else if (type == DUNNING)
			sql = "SELECT dl.Dunning_PrintFormat_ID,"
				+ " c.IsMultiLingualDocument,bp.AD_Language,bp.C_BPartner_ID,dr.DunningDate "
				+ "FROM C_DunningRunEntry d"
				+ " INNER JOIN AD_Client c ON (d.AD_Client_ID=c.AD_Client_ID)"
				+ " INNER JOIN C_BPartner bp ON (d.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN C_DunningRun dr ON (d.C_DunningRun_ID=dr.C_DunningRun_ID)"
				+ " INNER JOIN C_DunningLevel dl ON (dl.C_DunningLevel_ID=d.C_DunningLevel_ID) "
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
        else if (type == HR_REMITTANCE)
            sql = "SELECT pf.Remittance_PrintFormat_ID,"
                    + " c.IsMultiLingualDocument,bp.AD_Language,bp.C_BPartner_ID,d.DocumentNo "
                    + "FROM HR_PaySelectionCheck d"
                    + " INNER JOIN AD_Client c ON (d.AD_Client_ID=c.AD_Client_ID)"
                    + " INNER JOIN AD_PrintForm pf ON (c.AD_Client_ID=pf.AD_Client_ID)"
                    + " INNER JOIN C_BPartner bp ON (d.C_BPartner_ID=bp.C_BPartner_ID) "
                    + "WHERE d.HR_PaySelectionCheck_ID=?"		//	info from PrintForm
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
		else if (type == MANUFACTURING_ORDER)
			sql = "SELECT pf.Manuf_Order_PrintFormat_ID,"
				+ " c.IsMultiLingualDocument,bp.AD_Language, 0 , d.DocumentNo "
				+ "FROM PP_Order d"
				+ " INNER JOIN AD_Client c ON (d.AD_Client_ID=c.AD_Client_ID)"
				+ " LEFT OUTER JOIN AD_User u ON (u.AD_User_ID=d.Planner_ID)"
				+ " LEFT OUTER JOIN C_BPartner bp ON (u.C_BPartner_ID=bp.C_BPartner_ID) "
				+ " INNER JOIN AD_PrintForm pf ON (c.AD_Client_ID=pf.AD_Client_ID)"
				+ "WHERE d.PP_Order_ID=?"					//	info from PrintForm
				+ " AND pf.AD_Org_ID IN (0,d.AD_Org_ID) ORDER BY pf.AD_Org_ID DESC";
		else if (type == DISTRIBUTION_ORDER)
			sql = "SELECT pf.Distrib_Order_PrintFormat_ID,"
				+ " c.IsMultiLingualDocument,bp.AD_Language, bp.C_BPartner_ID , d.DocumentNo "
				+ "FROM DD_Order d"
				+ " INNER JOIN AD_Client c ON (d.AD_Client_ID=c.AD_Client_ID)"
				+ " INNER JOIN AD_PrintForm pf ON (c.AD_Client_ID=pf.AD_Client_ID)"
				+ " LEFT OUTER JOIN C_BPartner bp ON (d.C_BPartner_ID=bp.C_BPartner_ID) "
				+ "WHERE d.DD_Order_ID=?"					//	info from PrintForm
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
		// Fix [2574162] Priority to choose invoice print format not working
		else if (type == ORDER || type == INVOICE)
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
				+ " LEFT OUTER JOIN C_DocType dt ON ((d.C_DocType_ID>0 AND d.C_DocType_ID=dt.C_DocType_ID) OR (d.C_DocType_ID=0 AND d.C_DocTypeTarget_ID=dt.C_DocType_ID)) "
				+ "WHERE d." + DOC_IDS[type] + "=?"			//	info from PrintForm
				+ " AND pf.AD_Org_ID IN (0,d.AD_Org_ID) "
				+ "ORDER BY pf.AD_Org_ID DESC";
		else	//	Get PrintFormat from Org or 0 of document client
			sql = "SELECT pf.Order_PrintFormat_ID,pf.Shipment_PrintFormat_ID,"		//	1..2
				//	Prio: 1. BPartner 2. DocType, 3. PrintFormat (Org)	//	see InvoicePrint
				+ " COALESCE (bp.Invoice_PrintFormat_ID,dt.AD_PrintFormat_ID,pf.Invoice_PrintFormat_ID)," // 3
				+ " pf.Project_PrintFormat_ID, pf.Remittance_PrintFormat_ID,"		//	4..5
				+ " c.IsMultiLingualDocument, bp.AD_Language,"						//	6..7
				+ " COALESCE(dt.DocumentCopies,0)+COALESCE(bp.DocumentCopies,1), " 	// 	8
				+ " dt.AD_PrintFormat_ID,bp.C_BPartner_ID,d.DocumentNo, "			//  9..11 
				+ " pf.Manuf_Order_PrintFormat_ID, pf.Distrib_Order_PrintFormat_ID "	//	12..13
				+ "FROM " + DOC_BASETABLES[type] + " d"
				+ " INNER JOIN AD_Client c ON (d.AD_Client_ID=c.AD_Client_ID)"
				+ " INNER JOIN AD_PrintForm pf ON (c.AD_Client_ID=pf.AD_Client_ID)"
				+ " INNER JOIN C_BPartner bp ON (d.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " LEFT OUTER JOIN C_DocType dt ON (d.C_DocType_ID=dt.C_DocType_ID) "
				+ "WHERE d." + DOC_IDS[type] + "=?"			//	info from PrintForm
				+ " AND pf.AD_Org_ID IN (0,d.AD_Org_ID) "
				+ "ORDER BY pf.AD_Org_ID DESC";
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, Record_ID);
			rs = pstmt.executeQuery();
			if (rs.next())	//	first record only
			{
				if (type == CHECK || type == HR_CHECK|| type == DUNNING || type == REMITTANCE || type == HR_REMITTANCE
				||  type == PROJECT || type == RFQ || type == MANUFACTURING_ORDER || type == DISTRIBUTION_ORDER)
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
				else
				{
					//	Set PrintFormat
					AD_PrintFormat_ID = rs.getInt(type+1);
					if (type != INVOICE && rs.getInt(9) != 0)		//	C_DocType.AD_PrintFormat_ID
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
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Record_ID=" + Record_ID + ", SQL=" + sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
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
		MQuery query = new MQuery(format.getAD_Table_ID());
		query.addRestriction(DOC_IDS[type], MQuery.EQUAL, Record_ID);
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
		ReportEngine re = new ReportEngine(ctx, format, query, info, trxName);
		return re;
	}	//	get

	/**
	 *	Determine what Order document to print.
	 *  @param Record_ID id
	 *	@return int Array with [printWhat, ID]
	 */
	private static int[] getDocumentWhat (int type, int Record_ID)
	{

		int[] what = new int[2];
		if (type == ORDER)
		{
			what[0] = ORDER;
			what[1] = Record_ID;
			//
			String sql = "SELECT dt.DocSubTypeSO, dt.PrintDocument "
				+ "FROM C_DocType dt, C_Order o "
				+ "WHERE o.C_DocType_ID=dt.C_DocType_ID"
				+ " AND o.C_Order_ID=?";
			String DocSubTypeSO = null;
			String printDocument = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, Record_ID);
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					DocSubTypeSO = rs.getString(1);
					printDocument = rs.getString(2);
				}

				// @Trifon - Order is not completed(C_DoctType_ID=0) then try with C_DocTypeTarget_ID
				// [ 2819637 ] Wrong print format on non completed order - https://sourceforge.net/tracker/?func=detail&aid=2819637&group_id=176962&atid=879332
				if (DocSubTypeSO == null || "".equals(DocSubTypeSO)) {
					sql = "SELECT dt.DocSubTypeSO, dt.PrintDocument "
						+ "FROM C_DocType dt, C_Order o "
						+ "WHERE o.C_DocTypeTarget_ID=dt.C_DocType_ID"
						+ " AND o.C_Order_ID=?";
					pstmt = DB.prepareStatement(sql, null);
					pstmt.setInt(1, Record_ID);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						DocSubTypeSO = rs.getString(1);
						printDocument = rs.getString(2);
					}
				}
			}
			catch (SQLException e1)
			{
				log.log(Level.SEVERE, "(1) - " + sql, e1);
				return null;		//	error
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}

			if (DocSubTypeSO == null)
				DocSubTypeSO = "";
			
			if ("0".equals(printDocument))
				what[0] = ORDER;
			else if ("1".equals(printDocument))
				what[0] = SHIPMENT;
			else if ("2".equals(printDocument))
				what[0] = INVOICE;
			//	WalkIn Receipt, WalkIn Invoice,
			else if (DocSubTypeSO.equals("WR") || DocSubTypeSO.equals("WI"))
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
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, Record_ID);
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					//	if (i == 1 && ADialog.ask(0, null, what[0] == INVOICE ? "PrintOnlyRecentInvoice?" : "PrintOnlyRecentShipment?")) break;
					what[1] = rs.getInt(1);
				}
				else	//	No Document Found
					what[0] = ORDER;
			}
			catch (SQLException e2)
			{
				log.log(Level.SEVERE, "(2) - " + sql, e2);
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
			log.fine("Order => " + what[0] + " ID=" + what[1]);
		}
		else if (type == SHIPMENT)
		{
			what[0] = SHIPMENT;
			what[1] = Record_ID;
			//
			String sql = "SELECT dt.PrintDocument "
				+ "FROM C_DocType dt, M_InOut io "
				+ "WHERE io.C_DocType_ID=dt.C_DocType_ID"
				+ " AND io.M_InOut_ID=?";
			String DocSubTypeSO = null;
			String printDocument = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, Record_ID);
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					printDocument = rs.getString(1);
				}

			}
			catch (SQLException e1)
			{
				log.log(Level.SEVERE, "(1) - " + sql, e1);
				return null;		//	error
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
			
			if (DocSubTypeSO == null)
				DocSubTypeSO = "";
			
			if ("0".equals(printDocument))
				what[0] = ORDER;
			else if ("2".equals(printDocument))
				what[0] = INVOICE;
			else
				return what;

			//	Get Record_ID of Invoice/Receipt
			if (what[0] == ORDER)
				sql = "SELECT C_Order_ID REC FROM M_InOut WHERE M_InOut_ID=?"	//	1
					+ " ORDER BY C_Order_ID DESC";
			else
				sql = "SELECT C_Invoice_ID REC FROM M_InOut WHERE M_InOut_ID=?" 	//	1
					+ " ORDER BY M_InOut_ID DESC";
			try
			{
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, Record_ID);
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					//	if (i == 1 && ADialog.ask(0, null, what[0] == INVOICE ? "PrintOnlyRecentInvoice?" : "PrintOnlyRecentShipment?")) break;
					what[1] = rs.getInt(1);
					
					if ( rs.wasNull())
					{
						what[0] = SHIPMENT;
						what[1] = Record_ID;
					}
				}
				
			}
			catch (SQLException e2)
			{
				log.log(Level.SEVERE, "(2) - " + sql, e2);
				return null;
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
			log.fine("InOut => " + what[0] + " ID=" + what[1]);
		}
		return what;
	}	//	getDocumentWhat

	/**
	 * 	Print Confirm.
	 *  Update Date Printed
	 * 	@param type document type
	 * 	@param Record_ID record id
	 */
	public static void printConfirm (int type, int Record_ID, String trxName)
	{
		StringBuffer sql = new StringBuffer();
		if (type == ORDER || type == SHIPMENT || type == INVOICE)
			sql.append("UPDATE ").append(DOC_BASETABLES[type])
				.append(" SET DatePrinted=SysDate, IsPrinted='Y' WHERE ")
				.append(DOC_IDS[type]).append("=").append(Record_ID);
		//
		if (sql.length() > 0)
		{
			int no = DB.executeUpdate(sql.toString(), trxName);
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

	public void setWhereExtended(String whereExtended) {
		m_whereExtended = whereExtended;
	}

	public String getWhereExtended() {
		return m_whereExtended;
	}

	/* Save windowNo of the report to parse the context */
	public void setWindowNo(int windowNo) {
		m_windowNo = windowNo;
		if (m_layout != null)
			m_layout.setWindowNo(windowNo);
		
	}
	
	public int getWindowNo() {
		return m_windowNo;
	}

	public void setSummary(boolean summary)
	{
		m_summary = summary;
	}
	private String reportType;
	public void setReportType(String type)
	{
		reportType=type;
	}
	public String getReportType()
	{
		return reportType;
	}

	private File rename(File f) throws IOException 
	{
		File d = File.createTempFile("tempdir", "");
		String path = d.getAbsolutePath();
		d.delete();
		d.mkdir();
		//yyyyMMdd_HHmmss
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(Calendar.getInstance().getTime());
		String filename = timeStamp;
		if (m_printFormat != null) {
			filename = filename + "_" + m_printFormat.getName();
		}
		File doc = new File(path + File.separator + filename + ".pdf");
		f.renameTo(doc);
		return doc;
	}

	public void setProcessInfo(ProcessInfo processInfo) {
		this.processInfo = processInfo;
	}

	public ProcessInfo getProcessInfo() {
		return processInfo;
	}
}	//	ReportEngine
