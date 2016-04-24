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
package org.adempiere.webui.apps;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.component.WAppsAction;
import org.adempiere.webui.component.Window;
import org.compiere.apps.ProcessCtl;
import org.compiere.apps.ProcessDialog;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.au.out.AuEcho;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Html;

/**
 * 
 *	Modal Dialog to Start process.
 *	Displays information about the process
 *		and lets the user decide to start it
 *  	and displays results (optionally print them).
 *  Calls ProcessCtl to execute.
 *  @author 	Low Heng Sin
 *  @author     arboleda - globalqss
 *  - Implement ShowHelp option on processes and reports
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li>FR [ 265 ] ProcessParameterPanel is not MVC
 *		@see https://github.com/adempiere/adempiere/issues/265
 */
public class ProcessModalDialog extends Window implements EventListener
{
	/**
	 * generated serial version ID
	 */
	private static final long serialVersionUID = -7109707014309321369L;
	private boolean m_autoStart;
	private VerticalBox dialogBody;
	private Button bOK = null;
	private Button bCancel = null;

	/**
	 * @param aProcess
	 * @param WindowNo
	 * @param pi
	 * @param autoStart
	 */
	public ProcessModalDialog(ASyncProcess aProcess, int WindowNo, ProcessInfo pi, boolean autoStart)
	{
		m_ctx = Env.getCtx();
		m_ASyncProcess = aProcess;
		m_WindowNo = WindowNo;
		m_pi = pi;
		m_autoStart = autoStart;
		
		log.info("Process=" + pi.getAD_Process_ID());		
		try
		{
			initComponents();
			init();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
	}
	
	/**
	 * Dialog to start a process/report
	 * @param ctx
	 * @param aProcess
	 * @param WindowNo
	 * @param AD_Process_ID
	 * @param tableId
	 * @param recordId
	 * @param autoStart
	 */
	public ProcessModalDialog (  ASyncProcess aProcess, int WindowNo, int AD_Process_ID, int tableId, int recordId, boolean autoStart)
	{						
		this(aProcess, WindowNo, new ProcessInfo("", AD_Process_ID, tableId, recordId), autoStart);		
	}
	
	/**
	 * Optional constructor, for launch from ProcessCtl
	 * @param frame
	 * @param WindowNo
	 * @param pi
	 */
	public ProcessModalDialog (ASyncProcess aProcess, int WindowNo, ProcessInfo pi) {
		this(aProcess, WindowNo, pi, false);
		//	Set Process instance and flag
		m_OnlyPanel = true;
	}
	
	/**
	 * Dialog to start a process/report
	 * @param ctx
	 * @param parent not used
	 * @param title not used
	 * @param aProcess
	 * @param WindowNo
	 * @param AD_Process_ID
	 * @param tableId
	 * @param recordId
	 * @param autoStart
	 * @deprecated
	 */
	public ProcessModalDialog (Window parent, String title, 
			ASyncProcess aProcess, int WindowNo, int AD_Process_ID,
			int tableId, int recordId, boolean autoStart)
	{
		this(aProcess, WindowNo, AD_Process_ID, tableId, recordId, autoStart);
	}	//	ProcessDialog

	private void initComponents() {
		this.setBorder("normal");
		dialogBody = new VerticalBox();
		Div div = new Div();
		message = new Html();
		div.appendChild(message);
		div.setStyle("max-height: 150pt; overflow: auto;");
		dialogBody.appendChild(div);
		centerPanel = new Panel();
		dialogBody.appendChild(centerPanel);
		div = new Div();
		div.setAlign("right");
		Hbox hbox = new Hbox();
		//	BR [ 300 ]
		try{
			//	Set Ok
			WAppsAction action = new WAppsAction(ConfirmPanel.A_OK, null, ConfirmPanel.A_OK);
			bOK = action.getButton();
			//	Set to Cancel
			action = new WAppsAction(ConfirmPanel.A_CANCEL, null, ConfirmPanel.A_CANCEL);
			bCancel = action.getButton();
			//	Add Listener
			bOK.addEventListener(Events.ON_CLICK, this);
			bCancel.addEventListener(Events.ON_CLICK, this);
			//	Add to Panel
			hbox.appendChild(bCancel);
			hbox.appendChild(bOK);
		} catch(Exception e) {
			log.severe("Error loading Buttons " + e.getLocalizedMessage());
		}
		//	
		div.appendChild(hbox);
		dialogBody.appendChild(div);
		this.appendChild(dialogBody);
		
	}

	private ASyncProcess m_ASyncProcess;
	private int m_WindowNo;
	private Properties m_ctx;
	private String		    m_Name = null;
	private StringBuffer	m_messageText = new StringBuffer();
	private String          m_ShowHelp = null; // Determine if a Help Process Window is shown
	private boolean 		m_valid = true;
	private boolean 		m_OnlyPanel = false;
	private boolean 		m_isOK = false;
	
	private Panel centerPanel = null;
	private Html message = null;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessDialog.class);
	//
	private ProcessParameterPanel parameterPanel = null;
	
	private ProcessInfo m_pi = null;
	private BusyDialog progressWindow;
	
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
		parameterPanel.restoreContext();
		m_valid = false;
		this.detach();
	}	//	dispose

	/**
	 * is dialog still valid
	 * @return boolean
	 */
	public boolean isValid()
	{
		return m_valid;
	}

	/**
	 *	Dynamic Init
	 *  @return true, if there is something to process (start from menu)
	 */
	private boolean init()
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
			pstmt.setInt(1, m_pi.getAD_Process_ID());
			if (trl)
				pstmt.setString(2, Env.getAD_Language(m_ctx));
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				m_Name = rs.getString(1);
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
		message.setContent(m_messageText.toString());
		
		//	Move from APanel.actionButton
		m_pi.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
		m_pi.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
		m_pi.setTitle(m_Name);
		parameterPanel = new ProcessParameterPanel(m_WindowNo, m_pi);
		centerPanel.getChildren().clear();
		//	FR [ 265 ]
		if (parameterPanel.init()) {
			centerPanel.appendChild(parameterPanel.getPanel());
		} else {
			if (m_ShowHelp != null && m_ShowHelp.equals("N")) {
				m_autoStart = true;
			}
			if (m_autoStart) {
				this.getFirstChild().setVisible(false);
				startProcess();
				return true;
			}
		}
		
		// Check if the process is a silent one
		if(isValid() && m_ShowHelp != null && m_ShowHelp.equals("S"))
		{
			this.getFirstChild().setVisible(false);
			startProcess();			
		}
		return true;
	}	//	init

	/**
	 * launch process
	 */
	private void startProcess()
	{			
		m_pi.setPrintPreview(true);
		
		if (m_ASyncProcess != null) {
			m_ASyncProcess.lockUI(m_pi);
			Clients.showBusy(null, false);
		}
		
		showBusyDialog();
		
		//use echo, otherwise lock ui wouldn't work
		Clients.response(new AuEcho(this, "runProcess", null));
	}

	private void showBusyDialog() {
		this.setBorder("none");
		this.setTitle(null);
		dialogBody.setVisible(false);

		progressWindow = new BusyDialog();
		this.appendChild(progressWindow);
	}
	
	/**
	 * internal use, don't call this directly
	 */
	public void runProcess() {
		try {
			ProcessCtl.process(null, m_WindowNo, parameterPanel, m_pi, null);					
		} finally {
			dispose();
			if (m_ASyncProcess != null) {
				m_ASyncProcess.unlockUI(m_pi);
			} 
			hideBusyDialog();
		}
	}

	private void hideBusyDialog() {
		if (progressWindow != null) {
			progressWindow.dispose();
			progressWindow = null;
		}
	}
	
	/**
	 * handle events
	 */
	public void onEvent(Event event) {
		m_isOK = false;
		Component component = event.getTarget(); 
		if (component instanceof Button) {
			if (event.getTarget().equals(bOK)) {
				if(!m_OnlyPanel) {
					this.startProcess();
				} else {
					//	check if saving parameters is complete
					if (parameterPanel.validateParameters() == null) {
						//	Save Parameters
						parameterPanel.saveParameters();
						m_isOK = true;
						dispose();
					}
				}
			} else if (event.getTarget().equals(bCancel)) {
				this.dispose();
			}
		}		
	}
	
	/**
	 *	Is everything OK?
	 *  @return true if parameters saved correctly
	 */
	public boolean isOK() {
		return m_isOK;
	}	//	isOK
}	//	ProcessDialog
