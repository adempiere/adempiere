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
package org.compiere.apps;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.swing.CButton;
import org.compiere.swing.CFrame;
import org.compiere.swing.CPanel;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Dialog to Start process.
 *	Displays information about the process
 *		and lets the user decide to start it
 *  	and displays results (optionally print them).
 *  Calls ProcessCtl to execute.
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: ProcessDialog.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *  @author		Low Heng Sin
 *  - Merge process parameter dialog into process dialog.
 *  @author     arboleda - globalqss
 *  - Implement ShowHelp option on processes and reports
 *  @author		Teo Sarca, SC ARHIPAC SERVICE SRL
 *  				<li>BF [ 1893525 ] ProcessDialog: Cannot select the text from text field
 *  				<li>BF [ 1963128 ] Running a process w/o trl should display an error
 */
public class ProcessDialog extends CFrame
	implements ActionListener, ASyncProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 790447068287846414L;

	/**
	 * 	@deprecated
	 *	Dialog to start Process
	 *
	 * @param AD_Process_ID process
	 * @param isSOTrx is sales trx
	 */
	public ProcessDialog (int AD_Process_ID, boolean isSOTrx)
	{
		this(null, AD_Process_ID, isSOTrx);

	}	//	ProcessDialog
	
	/**
	 *	Dialog to start Process
	 *
	 * @param gc
	 * @param AD_Process_ID process
	 * @param isSOTrx is sales trx
	 */
	public ProcessDialog (GraphicsConfiguration gc, int AD_Process_ID, boolean isSOTrx)
	{
		super(gc);
		log.info("Process=" + AD_Process_ID + "; SOTrx=" + isSOTrx);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		m_AD_Process_ID = AD_Process_ID;
		m_WindowNo = Env.createWindowNo (this);
		Env.setContext(Env.getCtx(), m_WindowNo, "IsSOTrx", isSOTrx ? "Y" : "N");
		try
		{
			jbInit();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
	}	//	ProcessDialog

	private int 		    m_AD_Process_ID;
	private int			    m_WindowNo;
	private String		    m_Name = null;
	private boolean		    m_IsReport = false;
	private int[]		    m_ids = null;
	private boolean	        m_isLocked = false;
	private StringBuffer	m_messageText = new StringBuffer();
	private String          m_ShowHelp = null; // Determine if a Help Process Window is shown
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessDialog.class);
	//

	private CPanel dialog = new CPanel()
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 428410337428677817L;

		public Dimension getPreferredSize() {
			Dimension d = super.getPreferredSize();
			Dimension m = getMinimumSize();
			if ( d.height < m.height || d.width < m.width ) {
				Dimension d1 = new Dimension();
				d1.height = Math.max(d.height, m.height);
				d1.width = Math.max(d.width, m.width);
				return d1;
			} else
				return d;
		}
	};
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel southPanel = new CPanel();
	private CButton bOK = ConfirmPanel.createOKButton(true);
	private FlowLayout southLayout = new FlowLayout();
	private JEditorPane message = new JEditorPane()
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -2271852928089812014L;

		public Dimension getPreferredSize() {
			Dimension d = super.getPreferredSize();
			Dimension m = getMaximumSize();
			if ( d.height > m.height || d.width > m.width ) {
				Dimension d1 = new Dimension();
				d1.height = Math.min(d.height, m.height);
				d1.width = Math.min(d.width, m.width);
				return d1;
			} else
				return d;
		}
	};
	private JScrollPane messagePane = new JScrollPane(message)
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 3605316311642118445L;

		public Dimension getPreferredSize() {
			Dimension d = super.getPreferredSize();
			Dimension m = getMaximumSize();
			if ( d.height > m.height || d.width > m.width ) {
				Dimension d1 = new Dimension();
				d1.height = Math.min(d.height, m.height);
				d1.width = Math.min(d.width, m.width);
				return d1;
			} else
				return d;
		}
	};
	private CButton bPrint = ConfirmPanel.createPrintButton(true);
	
	private CPanel centerPanel = null;
	private ProcessParameterPanel parameterPanel = null;
	private JSeparator separator = new JSeparator();
	private ProcessInfo m_pi = null;

	/**
	 *	Static Layout
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		setIconImage(Env.getImage("mProcess.gif"));
		//
		dialog.setLayout(mainLayout);
		dialog.setMinimumSize(new Dimension(500, 200));
		bOK.addActionListener(this);
		bPrint.addActionListener(this);
		//
		southPanel.setLayout(southLayout);
		southLayout.setAlignment(FlowLayout.RIGHT);
		message.setContentType("text/html");
		message.setEditable(false);
		message.setBackground(Color.white);
		message.setFocusable(true);
		getContentPane().add(dialog);
		dialog.add(southPanel, BorderLayout.SOUTH);
		southPanel.add(bPrint, null);
		southPanel.add(bOK, null);
		dialog.add(messagePane, BorderLayout.NORTH);
		messagePane.setBorder(null);
		messagePane.setMaximumSize(new Dimension(600, 300));
		centerPanel = new CPanel();
		centerPanel.setBorder(null);
		centerPanel.setLayout(new BorderLayout());
		dialog.add(centerPanel, BorderLayout.CENTER);
		mainLayout.setVgap(2);
		//
		this.getRootPane().setDefaultButton(bOK);
	}	//	jbInit

	/**
	 * 	Set Visible 
	 * 	(set focus to OK if visible)
	 * 	@param visible true if visible
	 */
	public void setVisible (boolean visible)
	{
		super.setVisible(visible);
		if (visible) {
			bOK.requestFocus();
		}
	}	//	setVisible

	/**
	 *	Dispose
	 */
	public void dispose()
	{
		Env.clearWinContext(m_WindowNo);
		super.dispose();
	}	//	dispose


	/**
	 *	Dynamic Init
	 *  @return true, if there is something to process (start from menu)
	 */
	public boolean init()
	{
		log.config("");
		//
		boolean trl = !Env.isBaseLanguage(Env.getCtx(), "AD_Process");
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
				pstmt.setString(2, Env.getAD_Language(Env.getCtx()));
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
					m_messageText.append(Msg.getMsg(Env.getCtx(), "StartProcess?"));
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
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		if (m_Name == null) {
			throw new AdempiereException("@NotFound@ @AD_Process_ID@="+m_AD_Process_ID+". @CheckMissingTrl@");
		}
		//
		this.setTitle(m_Name);
		message.setText(m_messageText.toString());
		bOK.setText(Msg.getMsg(Env.getCtx(), "Start"));

		//	Similar to APanel.actionButton
		m_pi = new ProcessInfo(m_Name, m_AD_Process_ID);
		m_pi.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
		m_pi.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
		parameterPanel = new ProcessParameterPanel(m_WindowNo, m_pi);
		centerPanel.removeAll();
		if (parameterPanel.init()) {
			// hasfields
			centerPanel.add(separator, BorderLayout.NORTH);
			centerPanel.add(parameterPanel, BorderLayout.CENTER);
		} else {
			if (m_ShowHelp != null && m_ShowHelp.equals("N")) {
				bOK.doClick();    // don't ask first click
				// anyway show resulting window
			}
		}
		
		// Check if the process is a silent one
		if(m_ShowHelp != null && m_ShowHelp.equals("S"))
			bOK.doClick();
		
		dialog.revalidate();
		return true;
	}	//	init

	/**
	 *	ActionListener (Start)
	 *  @param e ActionEvent
	 */
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == bOK)
		{
			if (bOK.getText().length() == 0)
				dispose();
			else
			{
			//	Trx trx = Trx.get(Trx.createTrxName("ProcessDialog"), true);
				ProcessCtl.process(this, m_WindowNo, parameterPanel, m_pi, null);
			}
		}

		else if (e.getSource() == bPrint)
			printScreen();
	}	//	actionPerformed


	/**
	 *  Lock User Interface
	 *  Called from the Worker before processing
	 *  @param pi process info
	 */
	public void lockUI (ProcessInfo pi)
	{
		bOK.setText("");
		bOK.setEnabled(false);
		this.setEnabled(false);
		m_isLocked = true;
	}   //  lockUI

	/**
	 *  Unlock User Interface.
	 *  Called from the Worker when processing is done
	 *  @param pi process info
	 */
	public void unlockUI (ProcessInfo pi)
	{
		ProcessInfoUtil.setLogFromDB(pi);
		m_messageText.append("<p><font color=\"").append(pi.isError() ? "#FF0000" : "#0000FF").append("\">** ")
			.append(pi.getSummary())
			.append("</font></p>");
		m_messageText.append(pi.getLogInfo(true));
		message.setText(m_messageText.toString());
		message.setCaretPosition(message.getDocument().getLength());	//	scroll down
		m_ids = pi.getIDs();
		//
		bOK.setEnabled(true);
		this.setEnabled(true);
		m_isLocked = false;
		
		//no longer needed, hide to give more space to display log
		dialog.remove(centerPanel);
		messagePane.setMaximumSize(null);
		dialog.remove(messagePane);
		dialog.add(messagePane, BorderLayout.CENTER);
		
		this.validate();
		AEnv.showCenterScreen(this);
		//
		afterProcessTask();
		//	Close automatically
		if (m_IsReport && !pi.isError())
			bOK.doClick();
		
		// If the process is a silent one and no errors occured, close the dialog
		if(m_ShowHelp != null && m_ShowHelp.equals("S"))
			bOK.doClick();
	}   //  unlockUI

	/**
	 *  Is the UI locked (Internal method)
	 *  @return true, if UI is locked
	 */
	public boolean isUILocked()
	{
		return m_isLocked;
	}   //  isLoacked

	/**
	 *  Method to be executed async.
	 *  Called from the ASyncProcess worker
	 *  @param pi process info
	 */
	public void executeASync (ProcessInfo pi)
	{
		log.config("-");
	}   //  executeASync

	
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
	}	//	printInvoices

	/**
	 *	Print Invoices
	 */
	private void printInvoices()
	{
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
	}	//	printInvoices

	/**
	 *	Print Screen
	 */
	private void printScreen()
	{
		PrintScreenPainter.printScreen (this);
	}	//	printScreen

}	//	ProcessDialog
