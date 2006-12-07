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
package org.compiere.apps;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.plaf.*;
import org.compiere.print.*;
import org.compiere.process.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *	Dialog to Start process.
 *	Displays information about the process
 *		and lets the user decide to start it
 *  	and displays results (optionally print them).
 *  Calls ProcessCtl to execute.
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: ProcessDialog.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public class ProcessDialog extends CFrame
	implements ActionListener, ASyncProcess
{
	/**
	 *	Dialog to start Process
	 *
	 * @param AD_Process_ID process
	 * @param isSOTrx is sales trx
	 */
	public ProcessDialog (int AD_Process_ID, boolean isSOTrx)
	{
		super();
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
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessDialog.class);
	//

	private CPanel dialog = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel southPanel = new CPanel();
	private CButton bOK = ConfirmPanel.createOKButton(true);
	private FlowLayout southLayout = new FlowLayout();
	private JEditorPane message = new JEditorPane();
	private JScrollPane messagePane = new JScrollPane(message);
	private CButton bPrint = ConfirmPanel.createPrintButton(true);

	/**
	 *	Static Layout
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		setIconImage(Env.getImage("mProcess.gif"));
		//
		dialog.setLayout(mainLayout);
		bOK.addActionListener(this);
		bPrint.addActionListener(this);
		//
		southPanel.setLayout(southLayout);
		southLayout.setAlignment(FlowLayout.RIGHT);
		dialog.setPreferredSize(new Dimension(500, 150));
		message.setContentType("text/html");
		message.setEditable(false);
		message.setBackground(AdempierePLAF.getFieldBackground_Inactive());
		message.setFocusable(false);
		getContentPane().add(dialog);
		dialog.add(southPanel, BorderLayout.SOUTH);
		southPanel.add(bPrint, null);
		southPanel.add(bOK, null);
		dialog.add(messagePane, BorderLayout.CENTER);
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
		if (visible)
			bOK.requestFocus();
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
		String sql = "SELECT Name, Description, Help, IsReport "
				+ "FROM AD_Process "
				+ "WHERE AD_Process_ID=?";
		if (trl)
			sql = "SELECT t.Name, t.Description, t.Help, p.IsReport "
				+ "FROM AD_Process p, AD_Process_Trl t "
				+ "WHERE p.AD_Process_ID=t.AD_Process_ID"
				+ " AND p.AD_Process_ID=? AND t.AD_Language=?";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_AD_Process_ID);
			if (trl)
				pstmt.setString(2, Env.getAD_Language(Env.getCtx()));
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				m_Name = rs.getString(1);
				m_IsReport = rs.getString(4).equals("Y");
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
		message.setText(m_messageText.toString());
		bOK.setText(Msg.getMsg(Env.getCtx(), "Start"));

		/**	Start Reports w/o asking
		if (m_IsReport)
		{
			bOK.doClick();
			return false;		//	don't show
		}
		**/
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
				//	Similar to APanel.actionButton
				ProcessInfo pi = new ProcessInfo(m_Name, m_AD_Process_ID);
				pi.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
				pi.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
				m_messageText.append("<p>** ").append(m_Name).append("</p>");
				message.setText(m_messageText.toString());
			//	Trx trx = Trx.get(Trx.createTrxName("ProcessDialog"), true);
				ProcessCtl.process(this, m_WindowNo, pi, null);
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
		//
		afterProcessTask();
		//	Close automatically
		if (m_IsReport && !pi.isError())
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
				ReportCtl.startDocumentPrint(ReportEngine.SHIPMENT, M_InOut_ID, true);
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
				ReportCtl.startDocumentPrint(ReportEngine.INVOICE, AD_Invoice_ID, true);
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
