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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.pdf.ITextDocument;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.ProcessModalDialog;
import org.adempiere.webui.apps.WReport;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.event.DrillEvent;
import org.adempiere.webui.event.ZoomEvent;
import org.adempiere.webui.panel.StatusBarPanel;
import org.adempiere.webui.report.HTMLExtension;
import org.adempiere.webui.session.SessionManager;
import org.compiere.model.GridField;
import org.compiere.model.MArchive;
import org.compiere.model.MClient;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.MSysConfig;
import org.compiere.model.MUser;
import org.compiere.print.AReport;
import org.compiere.print.ArchiveEngine;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ImpExpUtil;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.spin.util.AbstractExportFormat;
import org.spin.util.ReportExportHandler;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;


/**
 *	Print View Frame
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: Viewer.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * globalqss: integrate phib contribution from 
 *   http://sourceforge.net/tracker/index.php?func=detail&aid=1566335&group_id=176962&atid=879334
 * globalqss: integrate Teo Sarca bug fixing
 * Colin Rooney 2007/03/20 RFE#1670185 & BUG#1684142
 *                         Extend security to Info queries
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>FR [ 1762466 ] Add "Window" menu to report viewer.
 * 				<li>FR [ 1894640 ] Report Engine: Excel Export support
 * 
 * @author Low Heng Sin
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>BR [ 236 ] Report View does not refresh when print format is changed
 * 			@see https://github.com/adempiere/adempiere/issues/236
 * 		<li>BR [ 237 ] Same Print format but distinct report view
 * 			@see https://github.com/adempiere/adempiere/issues/237
 * 		<li>BR [ 237 ] Same Print format but distinct report view
 * 			@see https://github.com/adempiere/adempiere/issues/237
 *	@author Dixon Martinez, dmartinez@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li>BR [1019] New Icon to export report definition is show only swing but not ZK https://github.com/adempiere/adempiere/issues/1019
 */
public class ZkReportViewer extends Window implements EventListener {
	
	private static final long serialVersionUID = 4640088641140012438L;
	/** Window No					*/
	private int                 m_WindowNo;
	/**	Print Context				*/
	private Properties			m_ctx;
	/**	Setting Values				*/
	private boolean				m_setting = false;
	/**	Report Engine				*/
	private ReportEngine 		m_reportEngine;
	/** Table ID					*/
	private int					m_AD_Table_ID = 0;
	private boolean				m_isCanExport;
	private boolean 			m_isAllowHTMLView;
	private boolean 			m_isAllowXLSView;
	/**	Export Handler			*/
	private ReportExportHandler	exportHandler = null;
	
	private MQuery 		m_ddQ = null;
	private MQuery 		m_daQ = null;
	private Menuitem 	m_ddM = null;
	private Menuitem 	m_daM = null;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ZkReportViewer.class);

	//
	private StatusBarPanel statusBar = new StatusBarPanel();
	private Toolbar toolBar = new Toolbar();
	private Toolbarbutton bSendMail = new Toolbarbutton();
	private Toolbarbutton bArchive = new Toolbarbutton();
	private Toolbarbutton bCustomize = new Toolbarbutton();
	private Toolbarbutton bFind = new Toolbarbutton();
	private Toolbarbutton bExport = new Toolbarbutton();
	private Listbox comboReport = new Listbox();
	private Label labelDrill = new Label();
	private Listbox comboDrill = new Listbox();
	private Listbox previewType = new Listbox();
	
	private Toolbarbutton bRefresh = new Toolbarbutton();
	private Iframe iframe;
	
	private Window winExportFile = null;
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private Listbox cboType = new Listbox();
	private Checkbox summary = new Checkbox();
	//	FR [ 237 ]
	private Listbox comboReportView = new Listbox();
	//	BR [1019]
	/**	Is Can Load							*/
	private boolean		m_IsCanLoad 		= false;
	/**	Load Toll Bar Button				*/
	private Toolbarbutton 	bLoad 			= new Toolbarbutton();
	
	/**
	 * 	Static Layout
	 * 	@throws Exception
	 */
	public ZkReportViewer(ReportEngine re, String title) {		
		super();
		
		log.info("");
		m_WindowNo = SessionManager.getAppDesktop().registerWindow(this);
		Env.setContext(re.getCtx(), m_WindowNo, "_WinInfo_IsReportViewer", "Y");
		m_reportEngine = re;
		m_AD_Table_ID = re.getPrintFormat().getAD_Table_ID();
		if (!MRole.getDefault().isCanReport(m_AD_Table_ID))
		{
			FDialog.error(m_WindowNo, this, "AccessCannotReport", m_reportEngine.getName());
			this.onClose();
		}
		m_isCanExport = MRole.getDefault().isCanExport(m_AD_Table_ID);
		//	BR [1019]
		m_IsCanLoad = MRole.getDefault().isCanLoad(m_AD_Table_ID);
		MRole roleCurrent = MRole.get(Env.getCtx(), Env.getAD_Role_ID(Env.getCtx()));
		m_isAllowHTMLView = roleCurrent.isAllow_HTML_View();	
		m_isAllowXLSView = roleCurrent.isAllow_XLS_View();			
		try
		{
			m_ctx = m_reportEngine.getCtx();
			//	Load export
			exportHandler = new ReportExportHandler(m_ctx, m_reportEngine);
			init();
			dynInit();
			
			if (!ArchiveEngine.isValid(m_reportEngine.getLayout()))
				log.warning("Cannot archive Document");
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
			FDialog.error(m_WindowNo, this, "LoadError", e.getLocalizedMessage());
			this.onClose();
		}
	}

	private void init() {
		Borderlayout layout = new Borderlayout();
		layout.setStyle("position: absolute; height: 99%; width: 99%");
		this.appendChild(layout);
		this.setStyle("width: 100%; height: 100%; position: absolute");

		toolBar.setHeight("100%");
		
		previewType.setMold("select");
		previewType.appendItem("PDF", "PDF");
		previewType.appendItem("CSV", "CSV");
		if (m_isAllowHTMLView) {
			previewType.appendItem("HTML", "HTML");
		}
		if (m_isAllowXLSView) {
			previewType.appendItem("Excel", "XLS");
			previewType.appendItem("XLSX", "XLSX");
		}
		toolBar.appendChild(previewType);		
		previewType.addEventListener(Events.ON_SELECT, this);
		toolBar.appendChild(new Separator("vertical"));
		
		//set default type
		String type = m_reportEngine.getReportType();
		if (type == null) {
			type = m_reportEngine.getPrintFormat().isForm()
					? MSysConfig.getValue("ZK_REPORT_FORM_OUTPUT_TYPE", Env.getAD_Client_ID(Env.getCtx()))
					: MSysConfig.getValue("ZK_REPORT_TABLE_OUTPUT_TYPE" , Env.getAD_Client_ID(Env.getCtx()));
		}

		if ("H".equals(type)  && !m_isAllowHTMLView) {
			type = "PDF";
		}

		if (("X".equals(type)  && !m_isAllowXLSView) || ("XX".equals(type)  && !m_isAllowXLSView)) {
			type = "PDF";
		}

		if ("C".equals(type))
			type = "CSV";
		if ("H".equals(type))
			type = "HTML";
		if ("X".equals(type))
			type = "XLS";
		if ("XX".equals(type))
			type = "XLSX";
		if ("P".equals(type))
			type = "PDF";

		if ("PDF".equals(type))
			previewType.setSelectedIndex(0);
		else if ("HTML".equals(type))
			previewType.setSelectedIndex(1);
		else if ("XLS".equals(type))
			previewType.setSelectedIndex(2);
		else if ("XLSX".equals(type))
			previewType.setSelectedIndex(3);
		else
			// XXX - provide hint if unexpected value
			previewType.setSelectedIndex(0); //fallback to PDF
			
		if(m_reportEngine.getReportType() != null)
		{
			if(m_reportEngine.getReportType().equals("P")) {
				previewType.setSelectedIndex(0);
			}
			else if (m_reportEngine.getReportType().equals("C")) {
				previewType.setSelectedIndex(1);
			}
			else if(m_reportEngine.getReportType().equals("H")) {
				previewType.setSelectedIndex(2);
			}
			else if(m_reportEngine.getReportType().equals("X")) {
				previewType.setSelectedIndex(3);
			}
			else if(m_reportEngine.getReportType().equals("XX")) {
				previewType.setSelectedIndex(4);
			}
		}

		labelDrill.setValue(Msg.getMsg(Env.getCtx(), "Drill") + ": ");
		toolBar.appendChild(labelDrill);
		
		comboDrill.setMold("select");
		comboDrill.setTooltiptext(Msg.getMsg(Env.getCtx(), "Drill"));
		toolBar.appendChild(comboDrill);
		
		toolBar.appendChild(new Separator("vertical"));
		
		comboReport.setMold("select");
		comboReport.setTooltiptext(Msg.translate(Env.getCtx(), "AD_PrintFormat_ID"));
		toolBar.appendChild(comboReport);
		//	FR [ 237 ]
		toolBar.appendChild(new Separator("vertical"));
		comboReportView.setMold("select");
		comboReportView.setTooltiptext(Msg.translate(Env.getCtx(), "AD_ReportView_ID"));
		toolBar.appendChild(comboReportView);

		toolBar.appendChild(new Separator("vertical"));
		bCustomize.setImage("/images/dark/Preference16.png");
		bCustomize.setTooltiptext(Msg.getMsg(Env.getCtx(), "PrintCustomize"));
		toolBar.appendChild(bCustomize);
		bCustomize.addEventListener(Events.ON_CLICK, this);

		toolBar.appendChild(new Separator("vertical"));
		bFind.setImage("/images/dark/Find16.png");
		bFind.setTooltiptext(Msg.getMsg(Env.getCtx(), "Find"));
		toolBar.appendChild(bFind);
		bFind.addEventListener(Events.ON_CLICK, this);
		
		toolBar.appendChild(new Separator("vertical"));
		bSendMail.setImage("/images/dark/SendMail16.png");
		bSendMail.setTooltiptext(Msg.getMsg(Env.getCtx(), "SendMail"));
		toolBar.appendChild(bSendMail);
		bSendMail.addEventListener(Events.ON_CLICK, this);

		toolBar.appendChild(new Separator("vertical"));
		bArchive.setImage("/images/dark/Archive16.png");
		bArchive.setTooltiptext(Msg.getMsg(Env.getCtx(), "Archive"));
		toolBar.appendChild(bArchive);
		bArchive.addEventListener(Events.ON_CLICK, this);

		if (m_isCanExport)
		{
			toolBar.appendChild(new Separator("vertical"));
			bExport.setImage("/images/dark/ExportX16.png");
			bExport.setTooltiptext(Msg.getMsg(Env.getCtx(), "Export"));
			toolBar.appendChild(bExport);
			bExport.addEventListener(Events.ON_CLICK, this);
		}
		
		//	BR [1019]
		if (m_IsCanLoad) {
			toolBar.appendChild(new Separator("vertical"));
			bLoad.setImage("/images/dark/Import16.png");
			bLoad.setTooltiptext(Msg.getMsg(Env.getCtx(), "Load New Report Definition"));
			toolBar.appendChild(bLoad);
			bLoad.addEventListener(Events.ON_CLICK, this);
		}
		
		toolBar.appendChild(new Separator("vertical"));
		bRefresh.setImage("/images/dark/Refresh16.png");
		bRefresh.setTooltiptext(Msg.getMsg(Env.getCtx(), "Refresh"));
		toolBar.appendChild(bRefresh);
		bRefresh.addEventListener(Events.ON_CLICK, this);

		toolBar.appendChild(new Separator("vertical"));
		summary.setText(Msg.getMsg(Env.getCtx(), "Summary"));
		toolBar.appendChild(summary);

		North north = new North();
		north.setParent(layout);
		north.setCollapsible(false);
		north.setFlex(true);

		Vbox box = new Vbox();
		box.setWidth("100%");
		toolBar.setParent(box);
		box.setParent(north);

		Center center = new Center();
		center.setFlex(true);
		layout.appendChild(center);
		iframe = new Iframe();
		iframe.setId("reportFrame");
		iframe.setHeight("100%");
		iframe.setWidth("100%");
		iframe.addEventListener(Events.ON_CLICK, this);
		iframe.addEventListener(Events.ON_RIGHT_CLICK, this);
		center.appendChild(iframe);

		try {
			renderReport();
		} catch (Exception e) {
			throw new AdempiereException("Failed to render report", e);
		}
				
		iframe.setAutohide(true);

		this.setBorder("normal");
		
		this.addEventListener("onZoom", new EventListener() {
			
			public void onEvent(Event event) throws Exception {
				if (event instanceof ZoomEvent) {
					ZoomEvent ze = (ZoomEvent) event;
					if (ze.getData() != null && ze.getData() instanceof MQuery) {
						AEnv.zoom((MQuery) ze.getData());
					}
				}
				
			}
		});
		
		this.addEventListener(DrillEvent.ON_DRILL_ACROSS, new EventListener() {
			
			public void onEvent(Event event) throws Exception {
				if (event instanceof DrillEvent) {
					DrillEvent de = (DrillEvent) event;
					if (de.getData() != null && de.getData() instanceof MQuery) {
						MQuery query = (MQuery) de.getData();
						Listitem item = comboDrill.getSelectedItem();
						if (item != null && item.getValue() != null && item.toString().trim().length() > 0)
						{
							query.setTableName(item.getValue().toString());
							executeDrill(query, event.getTarget());
						}
					}
				}
				
			}
		});
		
		this.addEventListener(DrillEvent.ON_DRILL_DOWN, new EventListener() {
			
			public void onEvent(Event event) throws Exception {
				if (event instanceof DrillEvent) {
					DrillEvent de = (DrillEvent) event;
					if (de.getData() != null && de.getData() instanceof MQuery) {
						MQuery query = (MQuery) de.getData();
						executeDrill(query, event.getTarget());
					}
				}
				
			}
		});
	}

	private void renderReport() throws Exception {
		AMedia media = null;
		Listitem selected = previewType.getSelectedItem();
		if (selected == null || "PDF".equals(selected.getValue())) {
			String path = System.getProperty("java.io.tmpdir");
			String prefix = makePrefix(m_reportEngine.getName());
			if (log.isLoggable(Level.FINE))
			{
				log.log(Level.FINE, "Path="+path + " Prefix="+prefix);
			}
			File file = File.createTempFile(prefix, ".pdf", new File(path));
			m_reportEngine.createPDF(file);
			media = new AMedia(getTitle(), "pdf", "application/pdf", file, true);
		} else if ("HTML".equals(previewType.getSelectedItem().getValue())) {
			String path = System.getProperty("java.io.tmpdir");
			String prefix = makePrefix(m_reportEngine.getName());
			if (log.isLoggable(Level.FINE))
			{
				log.log(Level.FINE, "Path="+path + " Prefix="+prefix);
			}
			File file = File.createTempFile(prefix, ".html", new File(path));
			m_reportEngine.createHTML(file, false, new HTMLExtension(Executions.getCurrent().getContextPath(), "rp", this.getUuid()));
			media = new AMedia(getTitle(), "html", "text/html", file, false);
		} else if ("XLS".equals(previewType.getSelectedItem().getValue())) {
			String path = System.getProperty("java.io.tmpdir");
			String prefix = makePrefix(m_reportEngine.getName());
			if (log.isLoggable(Level.FINE))
			{
				log.log(Level.FINE, "Path="+path + " Prefix="+prefix);
			}
			File file = File.createTempFile(prefix, ".xls", new File(path));
			m_reportEngine.createXLS(file);
			media = new AMedia(getTitle(), "xls", "application/vnd.ms-excel", file, true);
		} else if ("XLSX".equals(previewType.getSelectedItem().getValue())) {
			String path = System.getProperty("java.io.tmpdir");
			String prefix = makePrefix(m_reportEngine.getName());
			if (log.isLoggable(Level.FINE))
			{
				log.log(Level.FINE, "Path="+path + " Prefix="+prefix);
			}
			File file = File.createTempFile(prefix, ".xls", new File(path));
			m_reportEngine.createXLSX(file);
			media = new AMedia(prefix, "xlsx", "application/vnd.ms-excel", file, true);
		}
		else if ("CSV".equals(previewType.getSelectedItem().getValue())) {
			String path = System.getProperty("java.io.tmpdir");
			String prefix = makePrefix(m_reportEngine.getName());
			if (log.isLoggable(Level.FINE))
			{
				log.log(Level.FINE, "Path=" + path + " Prefix=" + prefix);
			}
			File file = File.createTempFile(prefix, ".csv", new File(path));
			m_reportEngine.createCSV(file, ',');
			media = new AMedia(prefix, "csv", "text/csv", file, true);
		}
		iframe.setContent(media);
	}

	private String makePrefix(String name) {
		StringBuffer prefix = new StringBuffer();
		char[] nameArray = name.toCharArray();
		for (char ch : nameArray) {
			if (Character.isLetterOrDigit(ch)) {
				prefix.append(ch);
			} else {
				prefix.append("_");
			}
		}
		return prefix.toString();
	}
	
	/**
	 * 	Dynamic Init
	 */
	private void dynInit()
	{
		//	Default summary from print format
		summary.setSelected(m_reportEngine.getPrintFormat().isSummary());
		summary.addActionListener(this);
		
		fillComboReport(m_reportEngine.getPrintFormat().get_ID());

		//	fill Drill Options (Name, TableName)
		comboDrill.appendItem("", null);
		String sql = "SELECT t.AD_Table_ID, t.TableName, e.PrintName, NULLIF(e.PO_PrintName,e.PrintName) "
			+ "FROM AD_Column c "
			+ " INNER JOIN AD_Column used ON (c.ColumnName=used.ColumnName)"
			+ " INNER JOIN AD_Table t ON (used.AD_Table_ID=t.AD_Table_ID AND t.IsView='N' AND t.AD_Table_ID <> c.AD_Table_ID)"
			+ " INNER JOIN AD_Column cKey ON (t.AD_Table_ID=cKey.AD_Table_ID AND cKey.IsKey='Y')"
			+ " INNER JOIN AD_Element e ON (cKey.ColumnName=e.ColumnName) "
			+ "WHERE c.AD_Table_ID=? AND c.IsKey='Y' "
			+ "ORDER BY 3";
		boolean trl = !Env.isBaseLanguage(Env.getCtx(), "AD_Element");
		if (trl)
			sql = "SELECT t.AD_Table_ID, t.TableName, et.PrintName, NULLIF(et.PO_PrintName,et.PrintName) "
				+ "FROM AD_Column c"
				+ " INNER JOIN AD_Column used ON (c.ColumnName=used.ColumnName)"
				+ " INNER JOIN AD_Table t ON (used.AD_Table_ID=t.AD_Table_ID AND t.IsView='N' AND t.AD_Table_ID <> c.AD_Table_ID)"
				+ " INNER JOIN AD_Column cKey ON (t.AD_Table_ID=cKey.AD_Table_ID AND cKey.IsKey='Y')"
				+ " INNER JOIN AD_Element e ON (cKey.ColumnName=e.ColumnName)"
				+ " INNER JOIN AD_Element_Trl et ON (e.AD_Element_ID=et.AD_Element_ID) "
				+ "WHERE c.AD_Table_ID=? AND c.IsKey='Y'"
				+ " AND et.AD_Language=? "
				+ "ORDER BY 3";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_reportEngine.getPrintFormat().getAD_Table_ID());
			if (trl)
				pstmt.setString(2, Env.getAD_Language(Env.getCtx()));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				String tableName = rs.getString(2);
				String name = rs.getString(3);
				String poName = rs.getString(4);
				if (poName != null)
					name += "/" + poName;
				comboDrill.appendItem(name, tableName);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		if (comboDrill.getItemCount() == 1)
		{
			labelDrill.setVisible(false);
			comboDrill.setVisible(false);
		}
		else
			comboDrill.addEventListener(Events.ON_SELECT, this);

		revalidate();
	}	//	dynInit
	
	/**
	 * 	Fill ComboBox comboReport (report options)
	 *  @param AD_PrintFormat_ID item to be selected
	 */
	private void fillComboReport(int AD_PrintFormat_ID)
	{
		comboReport.removeEventListener(Events.ON_SELECT, this);
		comboReport.getItems().clear();
		Listitem selectValue = null;
		//	fill Report Options
		String sql = MRole.getDefault().addAccessSQL(
			"SELECT AD_PrintFormat_ID, Name, Description "
				+ "FROM AD_PrintFormat "
				+ "WHERE AD_Table_ID=? "
				//Added Lines by Armen
				+ "AND IsActive='Y' "
				//End of Added Lines
				+ "ORDER BY Name",
			"AD_PrintFormat", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		int AD_Table_ID = m_reportEngine.getPrintFormat().getAD_Table_ID();
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Table_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				KeyNamePair pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				Listitem li = comboReport.appendItem(pp.getName(), pp.getKey());
				if (rs.getInt(1) == AD_PrintFormat_ID)
				{
					selectValue = li;
					comboReport.setSelectedItem(li);
				}
			}
			//	Select Default
		    if (selectValue != null) {
		    	comboReport.setSelectedItem(selectValue);
		    }
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		StringBuffer sb = new StringBuffer("** ").append(Msg.getMsg(Env.getCtx(), "NewReport")).append(" **");
		KeyNamePair pp = new KeyNamePair(-1, sb.toString());
		comboReport.appendItem(pp.getName(), pp.getKey());
		
		sb = new StringBuffer("** ").append(Msg.getMsg(m_ctx, "CopyReport")).append(" **");
	   	pp = new KeyNamePair(-2, sb.toString());
	    comboReport.addItem(pp);
	    
		comboReport.addEventListener(Events.ON_SELECT, this);
		//	FR [ 237 ]
		fillComboReportView();
	}	//	fillComboReport

	/**
	 * 	Fill ComboBox comboReportView (report view options)
	 */
	private void fillComboReportView() {
		comboReportView.removeEventListener(Events.ON_SELECT, this);
		comboReportView.getItems().clear();
		Listitem selectValue = null;
		//	fill Report View Options
		String sql = "";
		//	For base language
		if (Env.isBaseLanguage(Env.getCtx(), "AD_ReportView")) {
			sql = MRole.getDefault().addAccessSQL(
					"SELECT AD_ReportView_ID, COALESCE(PrintName, Name) AS Name "
						+ "FROM AD_ReportView "
						+ "WHERE AD_Table_ID = ? "
						+ "AND IsActive='Y' "
						+ "ORDER BY Name",
					"AD_ReportView", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		} else {
			sql = MRole.getDefault().addAccessSQL(
					"SELECT rv.AD_ReportView_ID, COALESCE(rvt.PrintName, rv.PrintName, rvt.Name, rv.Name) AS Name "
						+ "FROM AD_ReportView rv "
						+ "LEFT JOIN AD_ReportView_Trl rvt ON(rvt.AD_ReportView_ID = rv.AD_ReportView_ID "
						+ "										AND rvt.AD_Language = '" + Env.getAD_Language(Env.getCtx()) + "') "
						+ "WHERE rv.AD_Table_ID = ? "
						+ "AND rv.IsActive='Y' "
						+ "ORDER BY rvt.Name",
					"rv", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		}
		//	Get Table
		int AD_Table_ID = m_reportEngine.getPrintFormat().getAD_Table_ID();
		//	Get Default Report View
		int AD_ReportView_ID = m_reportEngine.getPrintFormat().getAD_ReportView_ID();
		//	Get from DB
		KeyNamePair [] pairs = DB.getKeyNamePairs(sql, true, AD_Table_ID);
		//	fill combo
		for(KeyNamePair view : pairs) {
			Listitem li = comboReportView.appendItem(view.getName(), view.getKey());
			if (view.getKey() == AD_ReportView_ID) {
				selectValue = li;
			}
		}
		//	Select Default
	    if (selectValue != null) {
	    	comboReportView.setSelectedItem(selectValue);
	    }
	    //	Add Listener
	    comboReportView.addEventListener(Events.ON_SELECT, this);
	}	//	fillComboReport
	
	/**
	 * Select a Report view from print format
	 * @param p_AD_ReportView_ID
	 */
	private void selectReportView(int p_AD_ReportView_ID) {
		comboReportView.removeEventListener(Events.ON_SELECT, this);
		//	Select
		Listitem selectValue = null;
		for(int i = 0; i < comboReportView.getItemCount(); i++) {
			Listitem pp = (Listitem) comboReportView.getItemAtIndex(i);
			if ((int)pp.getValue() == p_AD_ReportView_ID) {
				selectValue = pp;
				break;
			}
		}
		//	Select Default
	    if (selectValue != null) {
	    	comboReportView.setSelectedItem(selectValue);
	    }
	    //	Add Listener
	    comboReportView.addEventListener(Events.ON_SELECT, this);
	}
	
	/**
	 * 	Revalidate settings after change of environment
	 */
	private void revalidate()
	{
		//	Report Info
		setTitle(Msg.getMsg(Env.getCtx(), "Report") + ": " + m_reportEngine.getName() + "  " + Env.getHeader(Env.getCtx(), 0));
		StringBuffer sb = new StringBuffer ();
		sb.append(Msg.getMsg(Env.getCtx(), "DataCols")).append("=")
			.append(m_reportEngine.getColumnCount())
			.append(", ").append(Msg.getMsg(Env.getCtx(), "DataRows")).append("=")
			.append(m_reportEngine.getRowCount());
		statusBar.setStatusLine(sb.toString());
		//
	}	//	revalidate

	/**
	 * 	Dispose
	 */
	public void onClose()
	{
		SessionManager.getAppDesktop().unregisterWindow(m_WindowNo);
		m_reportEngine = null;
		m_ctx = null;
		super.onClose();
	}	//	dispose

	public void onEvent(Event event) throws Exception {
		
		if(event.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
			winExportFile.onClose();
		else if(event.getTarget().getId().equals(ConfirmPanel.A_OK))			
			exportFile();
		else if(event.getName().equals(Events.ON_CLICK) || event.getName().equals(Events.ON_SELECT)) 
			actionPerformed(event);
		else if (event.getTarget() == summary) 
		{
			//	FR [ 238 ]
			//m_reportEngine.setSummary(summary.isSelected());
			cmd_isSummary();
		}
	}

	/**************************************************************************
	 * 	Action Listener
	 * 	@param e event
	 */
	public void actionPerformed (Event e)
	{
		if (m_setting)
			return;
		if (e.getTarget() == comboReport)
			cmd_report();
		else if(e.getTarget() == comboReportView)
			cmd_reportView();
		else if (e.getTarget() == bFind)
			cmd_find();
		else if (e.getTarget() == bExport)
			cmd_export();
		//	BR [1019]
		else if (e.getTarget() == bLoad){
			cmd_load();
		}
		else if (e.getTarget() == previewType)
			cmd_render();
		else if (e.getTarget() == bSendMail)
			cmd_sendMail();
		else if (e.getTarget() == bArchive)
			cmd_archive();
		else if (e.getTarget() == bCustomize)
			cmd_customize();
		else if (e.getTarget() == bRefresh)
			cmd_report();
		//
		else if (e.getTarget() == m_ddM)
			cmd_window(m_ddQ);
		else if (e.getTarget() == m_daM)
			cmd_window(m_daQ);
	}	//	actionPerformed
	
	private void cmd_render() {
		try { 
			renderReport();
		} catch (Exception e) {
			throw new AdempiereException("Failed to render report", e);
		}
	}

	/**
	 * 	Execute Drill to Query
	 * 	@param query query
	 *  @param component
	 */
	private void executeDrill (MQuery query, Component component)
	{
		int AD_Table_ID = AReport.getAD_Table_ID(query.getTableName());
		if (!MRole.getDefault().isCanReport(AD_Table_ID))
		{
			FDialog.error(m_WindowNo, this, "AccessCannotReport", query.getTableName());
			return;
		}
		if (AD_Table_ID != 0)
			new WReport (AD_Table_ID, query, component, 0);
		else
			log.warning("No Table found for " + query.getWhereClause(true));
	}	//	executeDrill
	
	/**
	 * 	Open Window
	 *	@param query query
	 */
	private void cmd_window (MQuery query)
	{
		if (query == null)
			return;
		AEnv.zoom(query);
	}	//	cmd_window
	
	/**
	 * 	Send Mail
	 */
	private void cmd_sendMail()
	{
		String to = "";
		MUser from = MUser.get(Env.getCtx(), Env.getAD_User_ID(Env.getCtx()));
		String subject = m_reportEngine.getName();
		String message = "";
		File attachment = null;
		try {
			attachment = m_reportEngine.getPDF(null);
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}

		new WEMailDialog (this,
			Msg.getMsg(Env.getCtx(), "SendMail"),
			from, to, subject, message, attachment);		
	}	//	cmd_sendMail

	/**
	 * 	Archive Report directly
	 */
	private void cmd_archive ()
	{
		boolean success = false;
		byte[] data = new ITextDocument().getPDFAsArray(m_reportEngine.getLayout().getPageable(false));	//	No Copy
		if (data != null)
		{
			MArchive archive = new MArchive (Env.getCtx(), m_reportEngine.getPrintInfo(), null);
			archive.setBinaryData(data);
			success = archive.save();
		}
		if (success)
			FDialog.info(m_WindowNo, this, "Archived");
		else
			FDialog.error(m_WindowNo, this, "ArchiveError");
	}	//	cmd_archive

	/**
	 * 	Export
	 */
	private void cmd_export()
	{		
		log.config("");
		if (!m_isCanExport)
		{
			FDialog.error(m_WindowNo, this, "AccessCannotExport", getTitle());
			return;
		}
		
		if(winExportFile == null)
		{
			winExportFile = new Window();
			winExportFile.setTitle(Msg.getMsg(Env.getCtx(), "Export") + ": " + getTitle());
			winExportFile.setWidth("450px");
			winExportFile.setClosable(true);
			winExportFile.setBorder("normal");
			winExportFile.setStyle("position:absolute");

			cboType.setMold("select");
			
			cboType.getItems().clear();
			//	
			int defaultItem = 0;
			if(exportHandler.getExportFormatList() != null) {
				for(AbstractExportFormat exportFormat : exportHandler.getExportFormatList()) {
					if(exportFormat.getExtension().equals("arxml")
							&& !m_IsCanLoad) {
						continue;
					}
					//	For all
					cboType.appendItem(exportFormat.getName(), exportFormat.getExtension());
					//	For default
					if(exportFormat.getExtension().equals("pdf")) {
						defaultItem = cboType.getItemCount() - 1;
					}
				}
			}
			//	Set Default
			cboType.setSelectedIndex(defaultItem);
			Hbox hb = new Hbox();
			Div div = new Div();
			div.setAlign("right");
			div.appendChild(new Label(Msg.getMsg(Env.getCtx(), "FilesOfType")));
			hb.appendChild(div);
			hb.appendChild(cboType);
			cboType.setWidth("100%");
			Vbox vb = new Vbox();
			vb.setWidth("390px");
			winExportFile.appendChild(vb);
			vb.appendChild(hb);
			vb.appendChild(confirmPanel);	
			confirmPanel.addActionListener(this);
		}
		
		AEnv.showCenterScreen(winExportFile);
		
	}	//	cmd_export
		
	/**
	 * Load Data Command
	 */
	private void cmd_load() {
		log.config("");
		if (!m_IsCanLoad) {
			FDialog.error(m_WindowNo, this, "AccessCannotLoad", getTitle());
			return;
		}
		//  Show File Open Dialog
		Media file = null;

		try {
			file = Fileupload.get(true);
			if (file == null)
				return;
		}
		catch (InterruptedException e)
		{
			log.warning(e.getLocalizedMessage());
			return;
		}

		FileOutputStream fos = null;
		try {

			File tempFile = File.createTempFile("adempiere_", "_"+file.getName());

			fos = new FileOutputStream(tempFile);
			byte[] bytes = null;
			if (file.inMemory()) {
				bytes = file.getByteData();
			} else {
				InputStream is = file.getStreamData();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buf = new byte[ 1000 ];
				int byteread = 0;
				while (( byteread=is.read(buf) )!=-1)
					baos.write(buf,0,byteread);
				bytes = baos.toByteArray();
			}

			fos.write(bytes);
			fos.flush();
			fos.close();
			if (ImpExpUtil.importPrintFormat(tempFile)) {
				FDialog.info(m_WindowNo, this, "Report Definition Loaded", getTitle());
				fillComboReport(m_reportEngine.getPrintFormat().get_ID());
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			return;
		} finally {
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {}
		}
		
	}
	
	/**
	 * Export to file
	 */
	private void exportFile() {
		try {
			ListItem li = cboType.getSelectedItem();
			if(li == null || li.getValue() == null) {
				FDialog.error(m_WindowNo, winExportFile, "FileInvalidExtension");
				return;
			}
			//	
			String ext = li.getValue().toString();
			String exportName = li.getLabel();
			File inputFile = File.createTempFile("Export", "." + ext);
			AbstractExportFormat exporter = exportHandler.getExporter(exportName);
			if(exporter == null) {
				winExportFile.onClose();
				return;
			}
			boolean isOk = exporter.exportTo(inputFile);
			winExportFile.onClose();
			if(isOk) {
				if(exporter.getAction().equals(AbstractExportFormat.EXPORT_FILE)) {
					AMedia media = null;
					media = new AMedia(m_reportEngine.getPrintFormat().getName() + "." + ext, null, "application/octet-stream", inputFile, true);
					Filedownload.save(media, m_reportEngine.getPrintFormat().getName() + "." + ext);
				} else if(exporter.getAction().equals(AbstractExportFormat.SEND_FILE)) {
					if(!Util.isEmpty(exporter.getMessage())) {
						FDialog.info(m_WindowNo, this, "user.info", Msg.parseTranslation(m_ctx, exporter.getMessage()));
					}
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "Failed to export content.", e);
			FDialog.error(m_WindowNo, this, "ExportError", e.getLocalizedMessage());
		}
	}
	
	/**
	 * 	Report Combo - Start other Report or create new one
	 */
	private void cmd_report() {
		Listitem pp = (Listitem) comboReport.getSelectedItem();
		if (pp != null
				&& (int) pp.getValue() >= 0) {
			//	Set Default Report View
			MPrintFormat pf = MPrintFormat.get (Env.getCtx(), (int) pp.getValue(), true);
			selectReportView(pf.getAD_ReportView_ID());
		}
		//	Call Report
		cmd_report(false);
	}
	
	/**
	 * Call report with change in property is Summary
	 */
	private void cmd_isSummary() {
		//	FR [ 238 ]
		cmd_report(true);
	}
	
	/**
	 * Report View Combo - Start the same report with other where clause
	 */
	private void cmd_reportView() {
		//	FR [ 237 ]
		cmd_report(false);
	}
	
	/**
	 * 	Report Combo - Start other Report or create new one
	 * 	@param isSummaryChanged for when the check Is Summary is changed
	 */
	private void cmd_report(boolean isSummaryChanged)
	{
		ListItem li = comboReport.getSelectedItem();
		if(li == null || li.getValue() == null) return;
		
		Object pp = li.getValue();
		if (pp == null)
			return;
		//
		MPrintFormat pf = null;
		int AD_PrintFormat_ID = Integer.valueOf(pp.toString());

		//	create new
		if (AD_PrintFormat_ID == -1)
		{
			int AD_ReportView_ID = m_reportEngine.getPrintFormat().getAD_ReportView_ID();
			if (AD_ReportView_ID != 0)
			{
				String name = m_reportEngine.getName();
				int index = name.lastIndexOf('_');
				if (index != -1)
					name = name.substring(0,index);
				pf = MPrintFormat.createFromReportView(m_ctx, AD_ReportView_ID, name);
			}
			else
			{
				int AD_Table_ID = m_reportEngine.getPrintFormat().getAD_Table_ID();
				pf = MPrintFormat.createFromTable(m_ctx, AD_Table_ID);
			}
			if (pf != null)
				fillComboReport(pf.get_ID());
			else
				return;
		}
		else if (AD_PrintFormat_ID == -2) {
			MPrintFormat current = m_reportEngine.getPrintFormat();
			if (current != null) {
				pf = MPrintFormat.copyToClient(m_ctx,
						current.getAD_PrintFormat_ID(),
						Env.getAD_Client_ID(m_ctx));

				if (pf != null)
					fillComboReport(pf.get_ID());
				else
					return;
			} else
				return;
		}		
		else
			pf = MPrintFormat.get (Env.getCtx(), AD_PrintFormat_ID, true);
		//	FR [ 238 ]
		if(pf == null)
			return;
		//	
		if(!isSummaryChanged) {
			summary.setSelected(pf.isSummary());
		}
		// Set Summary for report
		m_reportEngine.setSummary(summary.isSelected());
		//	Get Language from previous - thanks Gunther Hoppe 
		if (m_reportEngine.getPrintFormat() != null)
		{
			pf.setLanguage(m_reportEngine.getPrintFormat().getLanguage());		//	needs to be re-set - otherwise viewer will be blank
			pf.setTranslationLanguage(m_reportEngine.getPrintFormat().getLanguage());
		}
		//	FR [ 237 ]
		Listitem reportView = (Listitem)comboReportView.getSelectedItem();
		if (reportView != null) {
			m_reportEngine.setAD_ReportView_ID((int) reportView.getValue());
		}
		m_reportEngine.setPrintFormat(pf);
		//	
		try {
			renderReport();
		} catch (Exception e) {
			throw new AdempiereException("Failed to render report", e);
		}

		revalidate();

	}	//	cmd_report

	/**
	 * 	Query Report
	 */
	private void cmd_find()
	{
		int AD_Table_ID = m_reportEngine.getPrintFormat().getAD_Table_ID();
		
		String title = null; 
		String tableName = null;

		//	Get Find Tab Info
		String sql = "SELECT t.AD_Tab_ID "
			//	,w.Name, t.Name, w.IsDefault, t.SeqNo, ABS (tt.AD_Window_ID-t.AD_Window_ID)
			+ "FROM AD_Tab t"
			+ " INNER JOIN AD_Window w ON (t.AD_Window_ID=w.AD_Window_ID)"
			+ " INNER JOIN AD_Table tt ON (t.AD_Table_ID=tt.AD_Table_ID) "
			+ "WHERE tt.AD_Table_ID=? "
			+ "ORDER BY w.IsDefault DESC, t.SeqNo, ABS (tt.AD_Window_ID-t.AD_Window_ID)";
		int AD_Tab_ID = DB.getSQLValue(null, sql, AD_Table_ID);
		// ASP
		MClient client = MClient.get(Env.getCtx());
		String ASPFilter = "";
		if (client.isUseASP())
			ASPFilter =
				"     AND (   AD_Tab_ID IN ( "
				// Just ASP subscribed tabs for client "
				+ "              SELECT t.AD_Tab_ID "
				+ "                FROM ASP_Tab t, ASP_Window w, ASP_Level l, ASP_ClientLevel cl "
				+ "               WHERE w.ASP_Level_ID = l.ASP_Level_ID "
				+ "                 AND cl.AD_Client_ID = " + client.getAD_Client_ID()
				+ "                 AND cl.ASP_Level_ID = l.ASP_Level_ID "
				+ "                 AND t.ASP_Window_ID = w.ASP_Window_ID "
				+ "                 AND t.IsActive = 'Y' "
				+ "                 AND w.IsActive = 'Y' "
				+ "                 AND l.IsActive = 'Y' "
				+ "                 AND cl.IsActive = 'Y' "
				+ "                 AND t.ASP_Status = 'S') " // Show
				+ "        OR AD_Tab_ID IN ( "
				// + show ASP exceptions for client
				+ "              SELECT AD_Tab_ID "
				+ "                FROM ASP_ClientException ce "
				+ "               WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
				+ "                 AND ce.IsActive = 'Y' "
				+ "                 AND ce.AD_Tab_ID IS NOT NULL "
				+ "                 AND ce.AD_Field_ID IS NULL "
				+ "                 AND ce.ASP_Status = 'S') " // Show
				+ "       ) "
				+ "   AND AD_Tab_ID NOT IN ( "
				// minus hide ASP exceptions for client
				+ "          SELECT AD_Tab_ID "
				+ "            FROM ASP_ClientException ce "
				+ "           WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
				+ "             AND ce.IsActive = 'Y' "
				+ "             AND ce.AD_Tab_ID IS NOT NULL "
				+ "             AND ce.AD_Field_ID IS NULL "
				+ "             AND ce.ASP_Status = 'H')"; // Hide
		//
		sql = "SELECT Name, TableName FROM AD_Tab_v WHERE AD_Tab_ID=? " + ASPFilter;
		if (!Env.isBaseLanguage(Env.getCtx(), "AD_Tab"))
			sql = "SELECT Name, TableName FROM AD_Tab_vt WHERE AD_Tab_ID=?"
				+ " AND AD_Language='" + Env.getAD_Language(Env.getCtx()) + "' " + ASPFilter;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Tab_ID);
			ResultSet rs = pstmt.executeQuery();
			//
			if (rs.next())
			{
				title = rs.getString(1);				
				tableName = rs.getString(2);
			}
			//
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}

		GridField[] findFields = null;
		if (tableName != null)
			findFields = GridField.createFields(m_ctx, m_WindowNo, 0, AD_Tab_ID);
		
		//	FR [ 295 ]
		if (findFields == null)	{	//	No Tab for Table exists
			if(launchProcessPara()) {
				try {
					renderReport();
				} catch (Exception e) {
					throw new AdempiereException("Failed to render report", e);
				}
				revalidate();
			} else {
				return;
			}
		} else {
			String whereExtended = "";
			whereExtended = m_reportEngine.getWhereExtended();
            FindWindow find = new FindWindow(m_WindowNo, title, AD_Table_ID, tableName,whereExtended, findFields, 1, AD_Tab_ID);
            if (!find.isCancel())
            {
            	m_reportEngine.setQuery(find.getQuery());
            	try {
            		renderReport();
            	} catch (Exception e) {
        			throw new AdempiereException("Failed to render report", e);
        		}
            	revalidate();
            }
            find = null;
		}
	}	//	cmd_find
	
	/**
	 * FR [ 295 ]
	 * Launch Parameters for re-query
	 * @return isOk
	 */
	private boolean launchProcessPara() {
		//	Create new Instance
		ProcessInfo pi = new ProcessInfo(m_reportEngine.getProcessInfo().getTitle(), 
				m_reportEngine.getProcessInfo().getAD_Process_ID(), 
				m_reportEngine.getProcessInfo().getTable_ID(), 
				m_reportEngine.getProcessInfo().getRecord_ID());
		//	Launch dialog
		ProcessModalDialog processModalDialog = new ProcessModalDialog(null, m_WindowNo, pi);
		if (processModalDialog.isValidDialog()) {
			try {
				processModalDialog.setPage(this.getPage());
				processModalDialog.doModal();
				//	Valid
				if(processModalDialog.isOK()) {
					//	execute
					//ProcessCtl worker = new ProcessCtl(null, m_WindowNo, pi, true, null);
					//synchrous
					//worker.run();
					processModalDialog.runProcess();
					//	
					ReportEngine re = ReportEngine.get(Env.getCtx(), pi);
					//	
					if(re != null) {
						m_reportEngine.setQuery(re.getQuery());
					}
					//	
					return true;
				}
			} catch (InterruptedException e) {
				log.severe(e.getLocalizedMessage());
			}
		}
		else
			processModalDialog.runProcess();
		//	Default
		return false;
	}

	/**
	 * 	Call Customize
	 */
	private void cmd_customize()
	{
		int AD_Window_ID = 240;		//	hardcoded
		int AD_PrintFormat_ID = m_reportEngine.getPrintFormat().get_ID();
		AEnv.zoom(AD_Window_ID, MQuery.getEqualQuery("AD_PrintFormat_ID", AD_PrintFormat_ID));
	}	//	cmd_customize
}
