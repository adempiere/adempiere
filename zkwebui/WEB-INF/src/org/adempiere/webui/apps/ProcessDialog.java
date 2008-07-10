package org.adempiere.webui.apps;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.session.SessionManager;
import org.compiere.apps.ProcessCtl;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
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
public class ProcessDialog extends Window implements EventListener
{
	
	/**
	 * Dialog to start a process/report
	 * @param ctx
	 * @param parent
	 * @param title
	 * @param aProcess
	 * @param WindowNo
	 * @param AD_Process_ID
	 * @param tableId
	 * @param recordId
	 * @param autoStart
	 */
	public ProcessDialog (int AD_Process_ID, boolean isSOTrx)
	{
		
		log.info("Process=" + AD_Process_ID );
		m_ctx = Env.getCtx();;
		m_WindowNo = SessionManager.getAppDesktop().registerWindow(this);
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
		VerticalBox vbox = new VerticalBox();
		vbox.setWidth("100%");
		vbox.setSpacing("10px");
		Div div = new Div();
		message = new Html();
		div.appendChild(message);
		vbox.appendChild(div);
		centerPanel = new Panel();
		vbox.appendChild(centerPanel);
		div = new Div();
		div.setAlign("right");
		Hbox hbox = new Hbox();
		String label = Msg.getMsg(Env.getCtx(), "Ok");
		bOK = new Button(label.replaceAll("&", ""));
		bOK.setImage("/images/Ok16.gif");
		bOK.setName("ok");
		bOK.addEventListener(Events.ON_CLICK, this);
		hbox.appendChild(bOK);
		
		label = Msg.getMsg(Env.getCtx(), "Cancel");
		Button btn = new Button(label.replaceAll("&", ""));
		btn.setImage("/images/Cancel16.gif");
		btn.setName("cancel");
		btn.addEventListener(Events.ON_CLICK, this);
		
		hbox.appendChild(btn);
		div.appendChild(hbox);
		vbox.appendChild(div);		
		this.appendChild(vbox);
		
		this.setBorder("normal");
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
	
	private boolean valid = true;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessDialog.class);
	//
	private ProcessParameterPanel parameterPanel = null;
	
	private ProcessInfo m_pi = null;

	
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
		Env.clearWinContext(m_WindowNo);
		SessionManager.getAppDesktop().unregisterWindow(m_WindowNo);
		valid = false;
		this.detach();
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
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_AD_Process_ID);
			if (trl)
				pstmt.setString(2, Env.getAD_Language(m_ctx));
			ResultSet rs = pstmt.executeQuery();
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

			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			return false;
		}

		if (m_Name == null)
			return false;
		//
		this.setTitle(m_Name);
		message.setContent(m_messageText.toString());
		bOK.setLabel(Msg.getMsg(Env.getCtx(), "Start"));

		//	Move from APanel.actionButton
		m_pi = new ProcessInfo(m_Name, m_AD_Process_ID);
		m_pi.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
		m_pi.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
		parameterPanel = new ProcessParameterPanel(m_WindowNo, m_pi);
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
		return true;
	}	//	init

	public void startProcess()
	{
		m_pi.setPrintPreview(true);
		//can't use asyncprocess, zk ui threading issue
		this.lockUI(m_pi);
		try {
			ProcessCtl.process(null, m_WindowNo, parameterPanel, m_pi, null);
		} finally {
			this.unlockUI(m_pi);
		}
	}

	public boolean isAsap() {
		return true;
	}

	public void onEvent(Event event) {
		Component component = event.getTarget(); 
		if (component instanceof Button) {
			Button element = (Button)component;
			if ("ok".equalsIgnoreCase(element.getName())) {
				if (element.getLabel().length() > 0)
					this.startProcess();
				else
					this.dispose();
			} else if ("cancel".equalsIgnoreCase(element.getName())) {
				this.dispose();
			}
		}
		
	}

	public void lockUI(ProcessInfo pi) {
		bOK.setLabel("");
		bOK.setEnabled(false);
	}

	public void unlockUI(ProcessInfo pi) {
		ProcessInfoUtil.setLogFromDB(pi);
		m_messageText.append("<p><font color=\"").append(pi.isError() ? "#FF0000" : "#0000FF").append("\">** ")
			.append(pi.getSummary())
			.append("</font></p>");
		m_messageText.append(pi.getLogInfo(true));
		message.setContent(m_messageText.toString());
		//message.setCaretPosition(message.getDocument().getLength());	//	scroll down
		m_ids = pi.getIDs();
		//
		bOK.setEnabled(true);
		
		//no longer needed, hide to give more space to display log
		centerPanel.detach();
		invalidate();
		
		//
		afterProcessTask();
		//	Close automatically
		if (m_IsReport && !pi.isError())
			this.dispose();
		
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
		/*
		if (m_ids == null)
			return;
		if (!ADialog.ask(m_WindowNo, this, "PrintShipments"))
			return;
		m_messageText.append("<p>").append(Msg.getMsg(Env.getCtx(), "PrintShipments")).append("</p>");
		message.setText(m_messageText.toString());
		int retValue = ADialogDialog.A_CANCEL;
		do
		{
			//	Loop through all items
			for (int i = 0; i < m_ids.length; i++)
			{
				int M_InOut_ID = m_ids[i];
				ReportCtl.startDocumentPrint(ReportEngine.SHIPMENT, M_InOut_ID, this, Env.getWindowNo(this), true);
			}
			ADialogDialog d = new ADialogDialog (this,
				Env.getHeader(Env.getCtx(), m_WindowNo),
				Msg.getMsg(Env.getCtx(), "PrintoutOK?"),
				JOptionPane.QUESTION_MESSAGE);
			retValue = d.getReturnCode();
		}
		while (retValue == ADialogDialog.A_CANCEL);
		*/
	}	//	printInvoices

	/**
	 *	Print Invoices
	 */
	private void printInvoices()
	{
		/*
		if (m_ids == null)
			return;
		if (!ADialog.ask(m_WindowNo, this, "PrintInvoices"))
			return;
		m_messageText.append("<p>").append(Msg.getMsg(Env.getCtx(), "PrintInvoices")).append("</p>");
		message.setText(m_messageText.toString());
		int retValue = ADialogDialog.A_CANCEL;
		do
		{
			//	Loop through all items
			for (int i = 0; i < m_ids.length; i++)
			{
				int AD_Invoice_ID = m_ids[i];
				ReportCtl.startDocumentPrint(ReportEngine.INVOICE, AD_Invoice_ID, this, Env.getWindowNo(this), true);
			}
			ADialogDialog d = new ADialogDialog (this,
				Env.getHeader(Env.getCtx(), m_WindowNo),
				Msg.getMsg(Env.getCtx(), "PrintoutOK?"),
				JOptionPane.QUESTION_MESSAGE);
			retValue = d.getReturnCode();
		}
		while (retValue == ADialogDialog.A_CANCEL);
		*/
	}	//	printInvoices

	public boolean isValid() {
		return valid;
	}
}	//	ProcessDialog
