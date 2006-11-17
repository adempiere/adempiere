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
package org.compiere.apps.form;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.sql.*;
import java.util.logging.*;
import org.compiere.apps.*;
import org.compiere.grid.ed.*;
import org.compiere.model.*;
import org.compiere.swing.*;
import org.compiere.util.*;
import com.qoppa.pdf.*;
import com.qoppa.pdfViewer.*;


/**
 *	Arvhive Viewer
 *	
 *  @author Jorg Janke
 *  @version $Id: ArchiveViewer.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class ArchiveViewer extends CTabbedPane
	implements FormPanel, ActionListener, VetoableChangeListener
{
	
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame parent frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		log.info("");
		m_WindowNo = WindowNo;
		m_frame = frame;
		try
		{
			dynInit();
			jbInit();
			frame.getContentPane().add(this, BorderLayout.CENTER);
			frame.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
			//
			m_frame.setIconImage(Env.getImage("Archive16.gif"));
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "init", e);
		}
	}	//	init


	/**	Window No			*/
	private int         m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 	m_frame;
	/**	The Archives		*/
	private MArchive[]	m_archives = new MArchive[0];
	/** Archive Index		*/
	private int			m_index = 0;
	/** Table direct		*/
	private int 		m_AD_Table_ID = 0;
	/** Record direct		*/
	private int 		m_Record_ID = 0;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ArchiveViewer.class);
	

	private CPanel queryPanel = new CPanel(new GridBagLayout());
	private CCheckBox reportField = new CCheckBox(Msg.translate(Env.getCtx(), "IsReport"));
	private CLabel processLabel = new CLabel(Msg.translate(Env.getCtx(), "AD_Process_ID"));
	private CComboBox processField = null;
	private CLabel tableLabel = new CLabel(Msg.translate(Env.getCtx(), "AD_Table_ID"));
	private CComboBox tableField = null;
	private CLabel bPartnerLabel = new CLabel(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
	private VLookup bPartnerField = null;
	private CLabel nameQLabel = new CLabel(Msg.translate(Env.getCtx(), "Name"));
	private CTextField nameQField = new CTextField(15);
	private CLabel descriptionQLabel = new CLabel(Msg.translate(Env.getCtx(), "Description"));
	private CTextField descriptionQField = new CTextField(15);
	private CLabel helpQLabel = new CLabel(Msg.translate(Env.getCtx(), "Help"));
	private CTextField helpQField = new CTextField(15);
	private CLabel createdByQLabel = new CLabel(Msg.translate(Env.getCtx(), "CreatedBy"));
	private CComboBox createdByQField = null;
	private CLabel createdQLabel = new CLabel(Msg.translate(Env.getCtx(), "Created"));
	private VDate createdQFrom = new VDate();
	private VDate createdQTo = new VDate();
	//
	private CPanel viewPanel = new CPanel(new BorderLayout(5,5));
	private PDFViewerBean pdfViewer = Document.getViewer();
	private CPanel viewEnterPanel = new CPanel(new GridBagLayout());
	private CButton bBack = new CButton(Env.getImageIcon("wfBack24.gif"));
	private CButton bNext = new CButton(Env.getImageIcon("wfNext24.gif"));
	private CLabel positionInfo = new CLabel(".");
	private CLabel createdByLabel = new CLabel(Msg.translate(Env.getCtx(), "CreatedBy"));
	private CTextField createdByField = new CTextField(20);
	private CLabel createdLabel = new CLabel(Msg.translate(Env.getCtx(), "Created"));
	private VDate createdField = new VDate();
	//
	private CLabel nameLabel = new CLabel(Msg.translate(Env.getCtx(), "Name"));
	private VString nameField = new VString("Name", true, false, true, 20, 60, null, null);
	private CLabel descriptionLabel = new CLabel(Msg.translate(Env.getCtx(), "Description"));
	private VText descriptionField = new VText("Description", false, false, true, 20, 255);
	private CLabel helpLabel = new CLabel(Msg.translate(Env.getCtx(), "Help"));
	private VText helpField = new VText("Help", false, false, true, 20, 2000);
	private CButton updateArchive = ConfirmPanel.createOKButton(Msg.getMsg(Env.getCtx(), "Update"));
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);

	
	/**
	 *  Dynamic Init
	 */
	private void dynInit()
	{
		int AD_Role_ID = Env.getAD_Role_ID(Env.getCtx());
		//	Processes
		String sql = "SELECT DISTINCT p.AD_Process_ID, p.Name "
			+ "FROM AD_Process p INNER JOIN AD_Process_Access pa ON (p.AD_Process_ID=pa.AD_Process_ID) "
			+ "WHERE pa.AD_Role_ID=" + AD_Role_ID
			+ " AND p.IsReport='Y' AND p.IsActive='Y' AND pa.IsActive='Y' "
			+ "ORDER BY 2"; 
		processField = new CComboBox(DB.getKeyNamePairs(sql, true));
		//	Tables
		sql = "SELECT DISTINCT t.AD_Table_ID, t.Name "
			+ "FROM AD_Table t INNER JOIN AD_Tab tab ON (tab.AD_Table_ID=t.AD_Table_ID)"
			+ " INNER JOIN AD_Window_Access wa ON (tab.AD_Window_ID=wa.AD_Window_ID) "
			+ "WHERE wa.AD_Role_ID=" + AD_Role_ID
			+ " AND t.IsActive='Y' AND tab.IsActive='Y' "
			+ "ORDER BY 2";
		tableField = new CComboBox(DB.getKeyNamePairs(sql, true));
		//	Internal Users
		sql = "SELECT AD_User_ID, Name "
			+ "FROM AD_User u WHERE EXISTS "
				+"(SELECT * FROM AD_User_Roles ur WHERE u.AD_User_ID=ur.AD_User_ID) "
			+ "ORDER BY 2";
		createdByQField = new CComboBox(DB.getKeyNamePairs(sql, true));
		//
		bPartnerField = VLookup.createBPartner(m_WindowNo);
	}	//	dynInit

	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		int line = 0;
		queryPanel.add(reportField, new GridBagConstraints(0, line, 
			1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
		reportField.addActionListener(this);
		//
		queryPanel.add(processLabel, new GridBagConstraints(0, ++line, 
			1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,0,0,5), 0, 0));
		queryPanel.add(processField, new GridBagConstraints(1, line, 
			1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5,0,0,0), 0, 0));
		queryPanel.add(bPartnerLabel, new GridBagConstraints(0, ++line, 
			1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,0,0,5), 0, 0));
		queryPanel.add(bPartnerField, new GridBagConstraints(1, line, 
			1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5,0,0,0), 0, 0));
		queryPanel.add(tableLabel, new GridBagConstraints(0, ++line, 
			1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,0,0,5), 0, 0));
		queryPanel.add(tableField,new GridBagConstraints(1, line, 
			1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5,0,0,0), 0, 0));
		//
		queryPanel.add(nameQLabel, new GridBagConstraints(0, ++line, 
			1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10,0,0,5), 0, 0));
		queryPanel.add(nameQField, new GridBagConstraints(1, line, 
			1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(10,0,0,0), 0, 0));
		queryPanel.add(descriptionQLabel, new GridBagConstraints(0, ++line, 
			1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,0,0,5), 0, 0));
		queryPanel.add(descriptionQField, new GridBagConstraints(1, line, 
			1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5,0,0,0), 0, 0));
		queryPanel.add(helpQLabel, new GridBagConstraints(0, ++line, 
			1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,0,0,5), 0, 0));
		queryPanel.add(helpQField, new GridBagConstraints(1, line, 
			1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5,0,0,0), 0, 0));
		//
		queryPanel.add(createdByQLabel, new GridBagConstraints(0, ++line, 
			1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10,0,0,5), 0, 0));
		queryPanel.add(createdByQField, new GridBagConstraints(1, line, 
			1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(10,0,0,0), 0, 0));
		queryPanel.add(createdQLabel, new GridBagConstraints(0, ++line, 
			1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,0,0,5), 0, 0));
		queryPanel.add(createdQFrom, new GridBagConstraints(1, line, 
			1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5,0,0,0), 0, 0));
		queryPanel.add(createdQTo, new GridBagConstraints(2, line, 
			1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5,0,0,0), 0, 0));
		this.add(queryPanel, "Query");
		//
		//
		line = 0;
		viewPanel.add(pdfViewer, BorderLayout.WEST);
		//
		bBack.addActionListener(this);
		bNext.addActionListener(this);
		positionInfo.setFontBold(true);
		positionInfo.setHorizontalAlignment(CLabel.CENTER);
		viewEnterPanel.add(bBack,  new GridBagConstraints(0, line, 
			1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
		viewEnterPanel.add(positionInfo,  new GridBagConstraints(1, line, 
			1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,5,0,5), 0, 0));
		viewEnterPanel.add(bNext,  new GridBagConstraints(2, line, 
			1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0,0,0,5), 0, 0));
		//
		createdByField.setReadWrite(false);
		createdField.setReadWrite(false);
		nameField.addVetoableChangeListener(this);
		descriptionField.addVetoableChangeListener(this);
		helpField.addVetoableChangeListener(this);
		viewEnterPanel.add(createdByLabel, new GridBagConstraints(0, ++line, 
			3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5,0,0,5), 0, 0));
		viewEnterPanel.add(createdByField,  new GridBagConstraints(0, ++line, 
			3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2,0,0,5), 0, 0));
	//	viewEnterPanel.add(createdLabel, new ALayoutConstraint(line++,0));
		viewEnterPanel.add(createdField,  new GridBagConstraints(0, ++line, 
			3, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2,0,0,5), 0, 0));
		//
		viewEnterPanel.add(nameLabel, new GridBagConstraints(0, ++line, 
			3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5,0,0,5), 0, 0));
		viewEnterPanel.add(nameField, new GridBagConstraints(0, ++line, 
			3, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2,0,0,5), 0, 0));
		//
		viewEnterPanel.add(descriptionLabel, new GridBagConstraints(0, ++line, 
			3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5,0,0,5), 0, 0));
		viewEnterPanel.add(descriptionField,new GridBagConstraints(0, ++line, 
			3, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2,0,0,5), 0, 0));
		//
		viewEnterPanel.add(helpLabel, new GridBagConstraints(0, ++line, 
			3, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5,0,0,5), 0, 0));
		viewEnterPanel.add(helpField, new GridBagConstraints(0, ++line, 
			3, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2,0,0,5), 0, 0));
		//
		viewEnterPanel.add(updateArchive, new GridBagConstraints(0, ++line, 
			3, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,0,0,5), 0, 0));
		//
		viewEnterPanel.setPreferredSize(new Dimension(220,500));
		updateArchive.addActionListener(this);
		viewPanel.add(viewEnterPanel, BorderLayout.CENTER);
		this.add(viewPanel, "View");
		//
		confirmPanel.addActionListener(this);
		updateQDisplay();
		//
		this.setPreferredSize(new Dimension (720,500));
	}	//	jbInit
	
	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose

	/**
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		log.info(e.getActionCommand());
		//
		if (e.getSource() == updateArchive)
			cmd_updateArchive();
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			dispose();
		else if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			if (getSelectedIndex() == 1)
				dispose();
			else
				cmd_query();
		}
		else if (e.getSource() == reportField)
			updateQDisplay();
		else if (e.getSource() == bBack)
			updateVDisplay(false);
		else if (e.getSource() == bNext)
			updateVDisplay(true);
	}	//	actionPerformed

	/**
	 * 	Field Listener
	 *	@param evt event
	 *	@throws PropertyVetoException
	 */
	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException
	{
		if (m_archives.length > 0)
			updateArchive.setEnabled(true);
	}	//	vetableChange
	
	/**
	 * 	Update Query Display
	 */
	private void updateQDisplay()
	{
		boolean reports = reportField.isSelected();
		log.config("Reports=" + reports);
		//	Show
		processLabel.setVisible(reports);
		processField.setVisible(reports);
		//	Hide
		bPartnerLabel.setVisible(!reports);
		bPartnerField.setVisible(!reports);
	}	//	updateQDisplay

	/**
	 * 	Update View Display
	 * 	@param next show next Archive
	 */
	private void updateVDisplay (boolean next)
	{
		if (m_archives == null)
			m_archives = new MArchive[0];
		if (next)
			m_index++;
		else
			m_index--;
		if (m_index >= m_archives.length-1)
			m_index = m_archives.length-1;
		if (m_index < 0)
			m_index = 0;
		bBack.setEnabled(m_index > 0);
		bNext.setEnabled(m_index < m_archives.length-1);
		updateArchive.setEnabled(false);
		//
		log.info("Index=" + m_index + ", Length=" + m_archives.length);
		if (m_archives.length == 0)
		{
			positionInfo.setText("No Record Found");
			createdByField.setText("");
			createdField.setValue(null);
			nameField.setText("");
			descriptionField.setText("");
			helpField.setText("");
			pdfViewer.clearDocument();
			return;
		}
		//
		positionInfo.setText(m_index+1 + " of " + m_archives.length);
		MArchive ar = m_archives[m_index];
		createdByField.setText(ar.getCreatedByName());
		createdField.setValue(ar.getCreated());
		nameField.setText(ar.getName());
		descriptionField.setText(ar.getDescription());
		helpField.setText(ar.getHelp());
		//
		try
		{
			InputStream in = ar.getInputStream();
			pdfViewer.setScale(reportField.isSelected() ? 50 : 75);
			if (in != null)
				pdfViewer.loadPDF(in);
			else
				pdfViewer.clearDocument();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "pdf", e);
			pdfViewer.clearDocument();
		}
	}	//	updateVDisplay

	/**
	 * 	Update Archive Info
	 */
	private void cmd_updateArchive()
	{
		MArchive ar = m_archives[m_index];
		boolean update = false;
		if (!isSame(nameField.getText(), ar.getName()))
		{
			String newText = nameField.getText();
			if (newText != null && newText.length() > 0)
			{
				ar.setName(newText);
				update = true;
			}
		}
		if (!isSame(descriptionField.getText(), ar.getDescription()))
		{
			ar.setDescription(descriptionField.getText());
			update = true;
		}
		if (!isSame(helpField.getText(), ar.getHelp()))
		{
			ar.setHelp(helpField.getText());
			update = true;
		}
		log.info("Update=" + update);
		if (update)
			ar.save();
		//
		m_index++;
		updateVDisplay(false);
	}	//	cmd_updateArchive
	
	/**
	 * 	Is it the same
	 *	@param s1 s1
	 *	@param s2 s1
	 *	@return true if the same
	 */
	private boolean isSame (String s1, String s2)
	{
		if (s1 == null)
			return s2 == null;
		else if (s2 == null)
			return false;
		else
			return s1.equals(s2);
	}	//	isSame
	
	/**
	 * 	Query Directly
	 *	@param isReport report
	 *	@param AD_Table_ID table
	 *	@param Record_ID tecord
	 */
	public void query (boolean isReport, int AD_Table_ID, int Record_ID)
	{
		log.config("Report=" + isReport + ", AD_Table_ID=" + AD_Table_ID + ",Record_ID=" + Record_ID);
		reportField.setSelected(isReport);
		m_AD_Table_ID = AD_Table_ID;
		m_Record_ID = Record_ID;
		cmd_query();
	}	//	query	
	
	
	/**************************************************************************
	 * 	Create Query
	 */
	private void cmd_query()
	{
		StringBuffer sql = new StringBuffer();
		boolean reports = reportField.isSelected();
		MRole role = MRole.getDefault();
		if (!role.isCanReport())
		{
			log.warning("User/Role cannot Report AD_User_ID=" + Env.getAD_User_ID(Env.getCtx()));
			return;
		}
		sql.append(" AND IsReport=").append(reports ? "'Y'" : "'N'");
		
		//	Process
		if (reports)
		{
			KeyNamePair nn = (KeyNamePair)processField.getSelectedItem();
			if (nn != null && nn.getKey() > 0)
				sql.append(" AND AD_Process_ID=").append(nn.getKey());
		}
		
		//	Table
		if (m_AD_Table_ID > 0)
		{
			sql.append(" AND ((AD_Table_ID=").append(m_AD_Table_ID);
			if (m_Record_ID > 0)
				sql.append(" AND Record_ID=").append(m_Record_ID);
			sql.append(")");
			if (m_AD_Table_ID == MBPartner.Table_ID && m_Record_ID > 0)
				sql.append(" OR C_BPartner_ID=").append(m_Record_ID);
			sql.append(")");
			//	Reset for query
			m_AD_Table_ID = 0;
			m_Record_ID = 0;
		}
		else
		{
			KeyNamePair nn = (KeyNamePair)tableField.getSelectedItem();
			if (nn != null && nn.getKey() > 0)
				sql.append(" AND AD_Table_ID=").append(nn.getKey());
		}
		
		//	Business Partner
		if (!reports)
		{
			Integer ii = (Integer)bPartnerField.getValue();
			if (ii != null)
				sql.append(" AND C_BPartner_ID=").append(ii);
			else
				sql.append(" AND C_BPartner_ID IS NOT NULL");
		}
		
		//	Name
		String ss = nameQField.getText();
		if (ss != null && ss.length() > 0)
		{
			if (ss.indexOf("%") != -1 || ss.indexOf("_") != -1)
				sql.append(" AND Name LIKE ").append(DB.TO_STRING(ss));
			else
				sql.append(" AND Name=").append(DB.TO_STRING(ss));
		}
		
		//	Description
		ss = descriptionQField.getText();
		if (ss != null && ss.length() > 0)
		{
			if (ss.indexOf("%") != -1 || ss.indexOf("_") != -1)
				sql.append(" AND Description LIKE ").append(DB.TO_STRING(ss));
			else
				sql.append(" AND Description=").append(DB.TO_STRING(ss));
		}

		//	Help
		ss = helpQField.getText();
		if (ss != null && ss.length() > 0)
		{
			if (ss.indexOf("%") != -1 || ss.indexOf("_") != -1)
				sql.append(" AND Help LIKE ").append(DB.TO_STRING(ss));
			else
				sql.append(" AND Help=").append(DB.TO_STRING(ss));
		}

		//	CreatedBy
		KeyNamePair nn = (KeyNamePair)createdByQField.getSelectedItem();
		if (nn != null && nn.getKey() > 0)
			sql.append(" AND CreatedBy=").append(nn.getKey());
		
		//	Created
		Timestamp tt = createdQFrom.getTimestamp();
		if (tt != null)
			sql.append(" AND Created>=").append(DB.TO_DATE(tt, true));
		tt = createdQTo.getTimestamp();
		if (tt != null)
			sql.append(" AND Created<").append(DB.TO_DATE(TimeUtil.addDays(tt,1), true));
		
		log.fine(sql.toString());
		
		//	Process Access
		sql.append(" AND (AD_Process_ID IS NULL OR AD_Process_ID IN "
			+ "(SELECT AD_Process_ID FROM AD_Process_Access WHERE AD_Role_ID=")
			.append(role.getAD_Role_ID()).append("))");
		//	Table Access
		sql.append(" AND (AD_Table_ID IS NULL "
			+ "OR (AD_Table_ID IS NOT NULL AND AD_Process_ID IS NOT NULL) "	//	Menu Reports 
			+ "OR AD_Table_ID IN "
			+ "(SELECT t.AD_Table_ID FROM AD_Tab t"
			+ " INNER JOIN AD_Window_Access wa ON (t.AD_Window_ID=wa.AD_Window_ID) "
			+ "WHERE wa.AD_Role_ID=").append(role.getAD_Role_ID()).append("))");
		log.finest(sql.toString());
		//
		m_archives = MArchive.get(Env.getCtx(), sql.toString());
		log.info("Length=" + m_archives.length);
		//	Display
		this.setSelectedIndex(1);
		m_index = 1;
		updateVDisplay(false);
	}	//	cmd_query

	
}	//	ArchiveViewer
