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
package org.compiere.apps;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import org.adempiere.exceptions.DBException;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CButton;
import org.compiere.swing.CDialog;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * [ 1639242 ] Inconsistent appearance of Process/Report Dialog
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
 *		<li>BR [ 300 ] ZK Process action buttons don't have standard size and position
 *		@see https://github.com/adempiere/adempiere/issues/300
 *		<li>BR[ 323 ] Process parameter panel is showed without parameter
 *		@see https://github.com/adempiere/adempiere/issues/323
 */
public class ProcessModalDialog extends CDialog
	implements ActionListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6613814452809135635L;

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
	public ProcessModalDialog (Properties ctx, Frame parent, String title, 
			ASyncProcess aProcess, int WindowNo, int AD_Process_ID,
			int tableId, int recordId, boolean autoStart) {
		this(ctx, parent, title, aProcess, WindowNo, 
				AD_Process_ID, tableId, recordId, autoStart, null);
	}
	
	
	/**
	 * Private Constructor
	 * @param ctx
	 * @param parent
	 * @param title
	 * @param aProcess
	 * @param WindowNo
	 * @param AD_Process_ID
	 * @param tableId
	 * @param recordId
	 * @param autoStart
	 * @param pi
	 */
	private ProcessModalDialog (Properties ctx, Frame parent, String title, 
			ASyncProcess aProcess, int WindowNo, int AD_Process_ID,
			int tableId, int recordId, boolean autoStart, ProcessInfo pi)
	{
		super(parent, title, true);
		log.info("Process=" + AD_Process_ID );
		if(pi == null) {
			m_ctx = ctx;
			m_ASyncProcess = aProcess;
			m_AD_Process_ID = AD_Process_ID;
			m_tableId = tableId;
			m_recordId = recordId;
			m_autoStart = autoStart;
		} else {
			m_pi = pi;
			m_AD_Process_ID = pi.getAD_Process_ID();
			m_tableId = pi.getTable_ID();
			m_recordId = pi.getRecord_ID();
		}
		//	
		m_WindowNo = WindowNo;
		//	
		try
		{
			jbInit();
			init();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
	}	//	ProcessDialog
	
	/**
	 * Optional constructor, for launch from ProcessCtl
	 * @param frame
	 * @param WindowNo
	 * @param pi
	 */
	public ProcessModalDialog (Frame frame, int WindowNo, ProcessInfo pi) {
		this(Env.getCtx(), frame, pi.getTitle(), 
				null, WindowNo, pi.getAD_Process_ID(), 
				pi.getTable_ID(), pi.getRecord_ID(), false, pi);
		//	Set Process instance and flag
		m_pi = pi;
		m_OnlyPanel = true;
	}

	private ASyncProcess m_ASyncProcess;
	private int m_WindowNo;
	private Properties m_ctx;
	private int m_tableId;
	private int m_recordId;
	private boolean m_autoStart;
	private int 		    m_AD_Process_ID;
	private String		    m_Name = null;
	private StringBuffer	m_messageText = new StringBuffer();
	private String          m_ShowHelp = null; // Determine if a Help Process Window is shown
	private boolean 		m_valid = true;
	private boolean 		m_OnlyPanel = false;
	private boolean 		m_isOK = false;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessDialog.class);
	//

	private CPanel dialog = new CPanel()
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -8093428846912456722L;

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
	private ConfirmPanel southPanel = new ConfirmPanel(true);
	private CButton bOK = null;
	private JEditorPane message = new JEditorPane()
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1218214722657165651L;

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
		private static final long serialVersionUID = 8653555758412012675L;

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
	
	private CPanel centerPanel = null;
	private ProcessParameterPanel parameterPanel = null;
	private JSeparator separator = new JSeparator();
	private ProcessInfo m_pi;

	/**
	 *	Static Layout
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		dialog.setLayout(mainLayout);
		dialog.setMinimumSize(new Dimension(500, 200));
		southPanel.addActionListener(this);
		bOK = southPanel.getOKButton();
		//
		message.setContentType("text/html");
		message.setEditable(false);
		message.setBackground(Color.white);
		message.setFocusable(false);
		getContentPane().add(dialog);
		dialog.add(southPanel, BorderLayout.SOUTH);
		dialog.add(messagePane, BorderLayout.NORTH);
		messagePane.setBorder(null);
		messagePane.setMaximumSize(new Dimension(600, 300));
		centerPanel = new CPanel();
		centerPanel.setBorder(null);
		centerPanel.setLayout(new BorderLayout());
		dialog.add(centerPanel, BorderLayout.CENTER);
		//
		this.getRootPane().setDefaultButton(bOK);
	}	//	jbInit

	/**
	 * 	Set Visible 
	 * 	(set focus to OK if visible)
	 * 	@param visible true if visible
	 */
	public void setVisible(boolean visible) {
		if(m_OnlyPanel) {
			if(m_ShowHelp != null && m_ShowHelp.equals("N")) {
				// It is defined as a silent process
				if(parameterPanel.saveParameters() == null) {
					m_isOK = true;
					dispose();
				}
			} else {
				// Not a silent process
				super.setVisible(visible);
			}
		} else {
			super.setVisible(visible);
			if (visible) {
				bOK.requestFocus();
			}
		}
	}

	/**
	 *	Dispose
	 */
	public void dispose()
	{
		m_valid = false;
		parameterPanel.restoreContext(); // teo_sarca [ 1699826 ]
		super.dispose();
	}	//	dispose

	public boolean isValidDialog()
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
			pstmt.setInt(1, m_AD_Process_ID);
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
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		if (m_Name == null)
			return false;
		//
		this.setTitle(m_Name);
		message.setMargin(new Insets(10,10,10,10));
		message.setText(m_messageText.toString());

		//	Move from APanel.actionButton
		if(m_pi == null) {
			m_pi = new ProcessInfo(m_Name, m_AD_Process_ID, m_tableId, m_recordId);
		}
		m_pi.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
		m_pi.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
		parameterPanel = new ProcessParameterPanel(m_WindowNo, m_pi);
		centerPanel.removeAll();
		//	BR [ 265 ]
		m_valid = parameterPanel.init();
		if (m_valid) {
			// hasfields
			centerPanel.add(separator, BorderLayout.NORTH);
			//	Add Scroll FR [ 265 ]
			CScrollPane scrollPane = new CScrollPane(parameterPanel.getPanel());
			scrollPane.setAutoscrolls(true);
			scrollPane.createVerticalScrollBar();
			scrollPane.createHorizontalScrollBar();
			//	
			centerPanel.add(scrollPane, BorderLayout.CENTER);
		} else {
			if (m_ShowHelp != null && m_ShowHelp.equals("N")) {
				m_autoStart = true;
			}
			if (m_autoStart)
				bOK.doClick();    // don't ask first click
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
	public void actionPerformed (ActionEvent e) {
		m_isOK = false;
		if (e.getSource() == bOK) {
			if(!m_OnlyPanel) {
				ProcessCtl.process(m_ASyncProcess, m_WindowNo, parameterPanel, m_pi, null);
				dispose();
			} else {
				//	check if saving parameters is complete
				if (parameterPanel.validateParameters() == null) {
					//	Save Parameters
					parameterPanel.saveParameters();
					m_isOK = true;
					dispose();
				}
			}
		} else if (e.getSource() == southPanel.getCancelButton()) {
			dispose();
		}
	}	//	actionPerformed
	
	/**
	 *	Is everything OK?
	 *  @return true if parameters saved correctly
	 */
	public boolean isOK() {
		return m_isOK;
	}	//	isOK
}	//	ProcessDialog
