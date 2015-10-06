package org.adempiere.webui.apps;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import org.adempiere.webui.component.*;
import org.adempiere.webui.desktop.IDesktop;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.IFormLauncher;
import org.adempiere.webui.process.WProcessInfo;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.theme.ThemeUtils;
import org.adempiere.webui.window.FDialog;
import org.adempiere.webui.window.SimplePDFViewer;
import org.compiere.apps.ProcessCtl;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.util.*;
import org.zkoss.web.fn.ServletFns;
import org.zkoss.zk.au.out.AuEcho;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.North;
import org.zkoss.zul.South;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Html;

/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 Low Heng Sin											  *
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
 *****************************************************************************/



/**
 *	Dialog to Start process or report.
 *	Displays information about the process
 *		and lets the user decide to start it
 *  	and displays results (optionally print them).
 *  Calls ProcessCtl to execute.
 *  @author 	Low Heng Sin
 *  @author     arboleda - globalqss
 *  - Implement ShowHelp option on processes and reports
 */
public class ProcessDialog extends Window implements EventListener//, ASyncProcess
{
	/**
	 * generate serial version ID
	 */

	private static final long serialVersionUID = 5545731871518761455L;
	private Div messageDiv;
	private Center center;
	private North north;
	private Grid southRowPanel = GridFactory.newGridLayout();

    /**
     * Dialog to start a process/report
     * @param AD_Process_ID
     * @param isSOTrx
     */
	public ProcessDialog (int AD_Process_ID, boolean isSOTrx)
	{
		ThemeUtils.addSclass("ad-processdialog", this);
		log.info("Process=" + AD_Process_ID );
		m_ctx = Env.getCtx();
		m_WindowNo = SessionManager.getAppDesktop().registerWindow(this);
		this.setAttribute(IDesktop.WINDOWNO_ATTRIBUTE, m_WindowNo);
		m_AD_Process_ID = AD_Process_ID;
		Env.setContext(Env.getCtx(), m_WindowNo, "IsSOTrx", isSOTrx ? "Y" : "N");
		try
		{
			initComponents();
			init();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
	}	//	ProcessDialog

	private void initComponents() {
		Borderlayout layout = new Borderlayout();
		messageDiv = new Div();
		message = new Html();
		messageDiv.appendChild(message);
		
		north = new North();
		north.appendChild(messageDiv);
		layout.appendChild(north);
		north.setAutoscroll(true);
		
		centerPanel = new Panel();
		center = new Center();
		layout.appendChild(center);
		center.appendChild(centerPanel);
		center.setHflex("true");
		center.setVflex("true");
		center.setAutoscroll(true);
		
		Rows rows = southRowPanel.newRows();
		Row row = rows.newRow();

		Hbox hBox = new Hbox();

		//Panel saveParaPanel =new Panel();
		//saveParaPanel.setAlign("left");

		hBox.appendChild(lSaved);
		fSavedName.addEventListener(Events.ON_CHANGE, this);
		hBox.appendChild(fSavedName);

		bSave.setEnabled(false);
		bSave.setImage(ServletFns.resolveThemeURL("~./images/Save24.png"));
		bSave.setSclass("action-button");
		bSave.addActionListener(this);
		hBox.appendChild(bSave);

		bDelete.setEnabled(false);
		bDelete.setImage(ServletFns.resolveThemeURL("~./images/Delete24.png"));
		bDelete.setSclass("action-button");
		bDelete.addActionListener(this);
		hBox.appendChild(bDelete);

		row.appendChild(hBox);


		Panel confParaPanel =new Panel();
		confParaPanel.setAlign("right");

		String label = Msg.getMsg(Env.getCtx(), "Start");
		bOK = new Button(label.replaceAll("&", ""));
		bOK.setImage(ServletFns.resolveThemeURL("~./images/Ok16.png"));
		bOK.setId("Ok");
		bOK.addEventListener(Events.ON_CLICK, this);
		bOK.setSclass("action-button");
		confParaPanel.appendChild(bOK);
		
		label = Msg.getMsg(Env.getCtx(), "Cancel");
		bCancle = new Button(label.replaceAll("&", ""));
		bCancle.setImage(ServletFns.resolveThemeURL("~./images/Cancel16.png"));
		bCancle.addEventListener(Events.ON_CLICK, this);
		bCancle.setSclass("action-button");
		confParaPanel.appendChild(bCancle);
		row.appendChild(confParaPanel);
		
		South south = new South();
		layout.appendChild(south);
		south.appendChild(southRowPanel);
		this.appendChild(layout);
	}

	private int m_WindowNo;
	private Properties m_ctx;
	private int 		    m_AD_Process_ID;
	private String		    m_Name = null;
	
	private boolean		    m_IsReport = false;
	private int[]		    m_ids = null;
	private StringBuffer	m_messageText = new StringBuffer();
	private String          m_ShowHelp = null; // Determine if a Help Process Window is shown
	
	private Panel centerPanel = null;
	private Html message = null;
	private Button bOK = null;
	private Button bCancle = null;

	private boolean valid = true;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessDialog.class);
	//
	private ProcessParameterPanel parameterPanel = null;
	
	private ProcessInfo m_pi = null;
	private boolean m_isLocked = false;
	private String initialMessage;
	private BusyDialog progressWindow;

	//saved paramaters

	private Combobox fSavedName=new Combobox();
	private Button bSave=new Button("Save");
	private Button bDelete=new Button("Delete");
	private List<MPInstance> savedParams;
	private Label lSaved=new Label(Msg.getMsg(Env.getCtx(), "SavedParameter"));


	/**
	 * 	Set Visible 
	 * 	(set focus to OK if visible)
	 * 	@param visible true if visible
	 */
	public boolean setVisible (boolean visible)
	{
		return super.setVisible(visible);
	}	//	setVisible

	/**
	 *	Dispose
	 */
	public void dispose()
	{
		SessionManager.getAppDesktop().closeWindow(m_WindowNo);
		valid = false;
	}//	dispose

	/**
	 *	Dynamic Init
	 *  @return true, if there is something to process (start from menu)
	 */
	public boolean init()
	{
		log.config("");
		//
		boolean trl = !Env.isBaseLanguage(m_ctx, "AD_Process");
		String sql = "SELECT Name, Description, Help, IsReport, ShowHelp "
				+ "FROM AD_Process "
				+ "WHERE AD_Process_ID=?";
		if (trl)
			sql = "SELECT t.Name, t.Description, t.Help, p.IsReport, p.ShowHelp "
				+ "FROM AD_Process p, AD_Process_Trl t "
				+ "WHERE p.AD_Process_ID=t.AD_Process_ID"
				+ " AND p.AD_Process_ID=? AND t.AD_Language=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_AD_Process_ID);
			if (trl)
				pstmt.setString(2, Env.getAD_Language(m_ctx));
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				m_Name = rs.getString(1);
				m_IsReport = rs.getString(4).equals("Y");
				m_ShowHelp = rs.getString(5);
				//
				m_messageText.append("<b>");
				String s = rs.getString(2);		//	Description
				if (rs.wasNull())
					m_messageText.append(Msg.getMsg(m_ctx, "StartProcess?"));
				else
					m_messageText.append(s);
				m_messageText.append("</b>");
				
				s = rs.getString(3);			//	Help
				if (!rs.wasNull())
					m_messageText.append("<p>").append(s).append("</p>");
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			return false;
		}
		finally
		{
			DB.close(rs, pstmt);
		}

		if (m_Name == null)
			return false;
		//
		this.setTitle(m_Name);
		initialMessage = m_messageText.toString();
		message.setContent(initialMessage);
		bOK.setLabel(Msg.getMsg(Env.getCtx(), "Start"));

		//	Move from APanel.actionButton
		m_pi = new WProcessInfo(m_Name, m_AD_Process_ID);
		m_pi.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
		m_pi.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
		parameterPanel = new ProcessParameterPanel(m_WindowNo, m_pi, "70%");
		centerPanel.getChildren().clear();
		if ( parameterPanel.init() ) {
			centerPanel.appendChild(parameterPanel);
		} else {
			if (m_ShowHelp != null && m_ShowHelp.equals("N")) {
				startProcess();
			}
		}
		
		// Check if the process is a silent one
		if(m_ShowHelp != null && m_ShowHelp.equals("S"))
		{
			startProcess();
		}

		querySaved();

		return true;
	}	//	init

	private void querySaved()
	{
		//user query
		savedParams = MPInstance.get(Env.getCtx(), m_AD_Process_ID, Env.getContextAsInt(Env.getCtx(), "#AD_User_ID"));
		fSavedName.removeAllItems();
		for (MPInstance instance : savedParams)
		{
			String queries = instance.getName();
			fSavedName.appendItem(queries);
		}

		fSavedName.setValue("");
	}

	public void startProcess()
	{
		m_pi.setPrintPreview(true);

		this.lockUI(m_pi);
		Clients.response(new AuEcho(this, "runProcess", null));
	}

	public void runProcess() {
		try {
			ProcessCtl.process(null, m_WindowNo, parameterPanel, m_pi, null);

			org.compiere.model.MProcess process = new org.compiere.model.MProcess(m_ctx, m_AD_Process_ID, null);
			
			int adFormID = process.getAD_Form_ID();
			if (adFormID != 0 )
			{
				ADForm form = ADForm.openForm(adFormID);
				form.setProcessInfo(m_pi);
				if (form.getICustomForm() instanceof IFormLauncher)
					((IFormLauncher)form.getICustomForm()).init();				
				form.setAttribute(Window.MODE_KEY, Window.MODE_EMBEDDED);
				form.setAttribute(Window.INSERT_POSITION_KEY, Window.INSERT_NEXT);
				SessionManager.getAppDesktop().showWindow(form);
			}
			
		} finally {
			unlockUI(m_pi);
		}
	}
	
	public void onEvent(Event event) {
		String saveName = null;
		boolean lastRun = false;
		if(fSavedName.getRawText() != null )
		{
			saveName = fSavedName.getRawText();
			lastRun = ("** " + Msg.getMsg(Env.getCtx(), "LastRun") + " **").equals(saveName);
		}
		Component component = event.getTarget();
		if (event.getTarget().equals(bOK))
		{
			Button element = (Button)component;
				if (!parameterPanel.validateParameters())
					return;
				if (element.getLabel().length() > 0)
					this.startProcess();
				else
					this.dispose();
		}
		else if (event.getTarget().equals(bCancle))
		{
				this.dispose();
			}

		//saved paramater
		else if(event.getTarget().equals(bSave) && fSavedName != null && !lastRun)
		{
			// Update existing
			if (fSavedName.getSelectedIndex() > -1 && savedParams != null)
			{
				for (int i = 0; i < savedParams.size(); i++)
				{
					if (savedParams.get(i).getName().equals(saveName))
					{
						m_pi.setAD_PInstance_ID(savedParams.get(i).getAD_PInstance_ID());
						for (MPInstancePara para : savedParams.get(i).getParameters())
						{
							para.deleteEx(true);
		}
						parameterPanel.saveParameters();
					}
				}
			}
			// create new
			else
			{
				MPInstance instance = null;
				try
				{
					instance = new MPInstance(Env.getCtx(), m_pi.getAD_Process_ID(), m_pi.getRecord_ID());
					instance.setName(saveName);
					instance.saveEx();
					m_pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
					//				Get Parameters
					if (parameterPanel != null)
					{
						if (!parameterPanel.saveParameters())
						{
							throw new AdempiereSystemError(Msg.getMsg(Env.getCtx(), "SaveParameterError"));
						}
					}
				}
				catch (Exception ex)
				{
					log.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
				}
			}

			querySaved();
			fSavedName.setSelectedItem(getComboItem(saveName));

	}

		else if(event.getTarget().equals(bDelete)   && fSavedName != null && !lastRun )
		{
			Object o = fSavedName.getSelectedItem();
			if (savedParams != null && o != null)
			{
				String selected = fSavedName.getSelectedItem().getLabel();
				for (int i = 0; i < savedParams.size(); i++)
				{
					if (savedParams.get(i).getName().equals(selected))
					{
						savedParams.get(i).deleteEx(true);
					}
				}
			}

			querySaved();
		}

		else if(event.getTarget().equals(fSavedName) )
		{
			if (savedParams != null && saveName != null)
			{
				for (int i = 0; i < savedParams.size(); i++)
				{
					if (savedParams.get(i).getName().equals(saveName))
					{
						loadSavedParams(savedParams.get(i));
					}
				}
			}
			boolean enabled = !Util.isEmpty(saveName);
			bSave.setEnabled(enabled && !lastRun);
			bDelete.setEnabled(enabled && fSavedName.getSelectedIndex() > -1 && !lastRun);
		}
	}

	public  Comboitem getComboItem( String value){

		Comboitem item = null;
		for(int i = 0; i < fSavedName.getItems().size(); i++){
			if(fSavedName.getItems().get(i) != null){
				item = (Comboitem)fSavedName.getItems().get(i);
				if(value.equals(item.getLabel().toString())){
					break;
				}
			}
		}

		return item;
	}

	private void loadSavedParams(MPInstance instance) {
		parameterPanel.loadParameters(instance);
	}

	public void lockUI(ProcessInfo pi) {
		if (m_isLocked) return;
		
		m_isLocked = true;
		
		showBusyDialog();
	}

	private void showBusyDialog() {
		progressWindow = new BusyDialog();
		progressWindow.setPage(this.getPage());
		progressWindow.doHighlighted();
	}

	public void unlockUI(ProcessInfo pi) {
		if (!m_isLocked) return;
		
		m_isLocked = false;
		hideBusyDialog();
		updateUI(pi);
	}

	private void hideBusyDialog() {
		if (progressWindow != null) {
			progressWindow.dispose();
			progressWindow = null;
		}
	}

	private void updateUI(ProcessInfo pi) {
		ProcessInfoUtil.setLogFromDB(pi);
        // avoid close dialog when an report is executed
		if( (m_IsReport && pi.isError()) || !m_IsReport)
		{
			m_messageText.append("<p><font color=\"").append(pi.isError() ? "#FF0000" : "#0000FF").append("\">** ")
				.append(pi.getSummary())
				.append("</font></p>");
			m_messageText.append(pi.getLogInfo(true));
			message.setContent(m_messageText.toString());
			
			bOK.setLabel("");		
			m_ids = pi.getIDs();
			
			//move message div to center to give more space to display potentially very long log info
			centerPanel.detach();
			messageDiv.detach();
			messageDiv.setStyle("");
			north.setVisible(false);
			center.appendChild(messageDiv);
			invalidate();
		}
		
		Clients.response(new AuEcho(this, "onAfterProcess", null));
	}
	
	public void onAfterProcess() 
	{
		//
		afterProcessTask();

		// Close automatically 
		if (m_IsReport && !m_pi.isError())
		{
			m_pi = new WProcessInfo(m_Name, m_AD_Process_ID);
			m_pi.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
			m_pi.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
			parameterPanel.setProcessInfo(m_pi);
		}

		// If the process is a silent one and no errors occured, close the dialog
		if(m_ShowHelp != null && m_ShowHelp.equals("S"))
			this.dispose();	
	}
	
	/**************************************************************************
	 *	Optional Processing Task
	 */
	private void afterProcessTask()
	{
		//  something to do?
		if (m_ids != null && m_ids.length > 0)
		{
			log.config("");
			//	Print invoices
			if (m_AD_Process_ID == 119)
				printInvoices();
			else if (m_AD_Process_ID == 118)
				printShipments();
		}

	}	//	afterProcessTask
	
	/**************************************************************************
	 *	Print Shipments
	 */
	private void printShipments()
	{		
		if (m_ids == null)
			return;
		if (!FDialog.ask(m_WindowNo, this, "PrintShipments"))
			return;
				
		m_messageText.append("<p>").append(Msg.getMsg(Env.getCtx(), "PrintShipments")).append("</p>");
		message.setContent(m_messageText.toString());
		showBusyDialog();
		Clients.response(new AuEcho(this, "onPrintShipments", null));
	}	//	printInvoices
	
	public void onPrintShipments() 
	{		
		//	Loop through all items
		List<File> pdfList = new ArrayList<File>();
		for (int i = 0; i < m_ids.length; i++)
		{
			int M_InOut_ID = m_ids[i];
			ReportEngine re = ReportEngine.get (Env.getCtx(), ReportEngine.SHIPMENT, M_InOut_ID);
			pdfList.add(re.getPDF());				
		}
		
		if (pdfList.size() > 1) {
			try {
				File outFile = File.createTempFile("PrintShipments", ".pdf");					
				Document document = null;
				PdfWriter copy = null;					
				for (File f : pdfList) 
				{
					String fileName = f.getAbsolutePath();
					PdfReader reader = new PdfReader(fileName);
					reader.consolidateNamedDestinations();
					if (document == null)
					{
						document = new Document(reader.getPageSizeWithRotation(1));
						copy = PdfWriter.getInstance(document, new FileOutputStream(outFile));
						document.open();
					}
					int pages = reader.getNumberOfPages();
					PdfContentByte cb = copy.getDirectContent();
					for (int i = 1; i <= pages; i++) {
						document.newPage();
						PdfImportedPage page = copy.getImportedPage(reader, i);
						cb.addTemplate(page, 0, 0);
					}
				}
				document.close();

				hideBusyDialog();
				Window win = new SimplePDFViewer(this.getTitle(), new FileInputStream(outFile));
				SessionManager.getAppDesktop().showWindow(win, "center");
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}
		} else if (pdfList.size() > 0) {
			hideBusyDialog();
			try {
				Window win = new SimplePDFViewer(this.getTitle(), new FileInputStream(pdfList.get(0)));
				SessionManager.getAppDesktop().showWindow(win, "center");
			} catch (Exception e)
			{
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}
		}
	}

	/**
	 *	Print Invoices
	 */
	private void printInvoices()
	{
		if (m_ids == null)
			return;
		if (!FDialog.ask(m_WindowNo, this, "PrintInvoices"))
			return;
		m_messageText.append("<p>").append(Msg.getMsg(Env.getCtx(), "PrintInvoices")).append("</p>");
		message.setContent(m_messageText.toString());
		showBusyDialog();
		Clients.response(new AuEcho(this, "onPrintInvoices", null));				
	}	//	printInvoices
	
	public void onPrintInvoices()
	{
		//	Loop through all items
		List<File> pdfList = new ArrayList<File>();
		for (int i = 0; i < m_ids.length; i++)
		{
			int C_Invoice_ID = m_ids[i];
			ReportEngine re = ReportEngine.get (Env.getCtx(), ReportEngine.INVOICE, C_Invoice_ID);
			pdfList.add(re.getPDF());				
		}
		
		if (pdfList.size() > 1) {
			try {
				File outFile = File.createTempFile("PrintInvoices", ".pdf");					
				Document document = null;
				PdfWriter copy = null;					
				for (File f : pdfList) 
				{
					PdfReader reader = new PdfReader(f.getAbsolutePath());
					if (document == null)
					{
						document = new Document(reader.getPageSizeWithRotation(1));
						copy = PdfWriter.getInstance(document, new FileOutputStream(outFile));
						document.open();						
					}
					PdfContentByte cb = copy.getDirectContent(); // Holds the PDF
					int pages = reader.getNumberOfPages();
					for (int i = 1; i <= pages; i++) {
						document.newPage();
						PdfImportedPage page = copy.getImportedPage(reader, i);
						cb.addTemplate(page, 0, 0);
					}
				}
				document.close();

				hideBusyDialog();
				Window win = new SimplePDFViewer(this.getTitle(), new FileInputStream(outFile));
				SessionManager.getAppDesktop().showWindow(win, "center");
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}
		} else if (pdfList.size() > 0) {
			try {
				Window win = new SimplePDFViewer(this.getTitle(), new FileInputStream(pdfList.get(0)));
				SessionManager.getAppDesktop().showWindow(win, "center");
			} catch (Exception e)
			{
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}
		}
	}

	public boolean isValid() {
		return valid;
	}

	public void executeASync(ProcessInfo pi) {
	}

	public boolean isUILocked() {
		return m_isLocked;
	}
}	//	ProcessDialog
