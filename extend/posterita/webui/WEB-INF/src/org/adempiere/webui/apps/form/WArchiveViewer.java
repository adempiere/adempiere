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

/**
 * 2007, Modified by Posterita Ltd.
 */

package org.adempiere.webui.apps.form;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.session.SessionManager;
import org.compiere.model.MArchive;
import org.compiere.model.MBPartner;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MRole;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Separator;

/**
 * 	Archive Viewer
 * 
 * @author	Niraj Sohun
 * @date	September 28, 2007
*/

public class WArchiveViewer extends ADForm implements EventListener, ValueChangeListener
{
	private static final long serialVersionUID = 1L;
	
	/**	Window No			*/
	private int m_WindowNo = 0;
	
	/**	The Archives		*/
	private MArchive[] m_archives = new MArchive[0];
	
	/** Archive Index		*/
	private int	m_index = 0;
	
	/** Table direct		*/
	private int m_AD_Table_ID = 0;
	
	/** Record direct		*/
	private int m_Record_ID = 0;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WArchiveViewer.class);
	
	
	private VerticalBox queryPanel = new VerticalBox();
	private Checkbox reportField = new Checkbox();
	private Label processLabel = new Label(Msg.translate(Env.getCtx(), "AD_Process_ID"));
	private Listbox processField = new Listbox();
	private Label tableLabel = new Label(Msg.translate(Env.getCtx(), "AD_Table_ID"));
	private Listbox tableField = new Listbox();
	private Label bPartnerLabel = new Label(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
	private WEditor bPartnerField = null;
	private Label nameQLabel = new Label(Msg.translate(Env.getCtx(), "Name"));
	private Textbox nameQField = new Textbox();
	private Label descriptionQLabel = new Label(Msg.translate(Env.getCtx(), "Description"));
	private Textbox descriptionQField = new Textbox();
	private Label helpQLabel = new Label(Msg.translate(Env.getCtx(), "Help"));
	private Textbox helpQField = new Textbox();
	private Label createdByQLabel = new Label(Msg.translate(Env.getCtx(), "CreatedBy"));
	private Listbox createdByQField = new Listbox();
	private Label createdQLabel = new Label(Msg.translate(Env.getCtx(), "Created"));
	private Datebox createdQFrom = new Datebox();
	private Datebox createdQTo = new Datebox();
	
	private VerticalBox viewEnterPanel = new VerticalBox();
	private Button bBack = new Button();
	private Button bNext = new Button();
	private Label positionInfo = new Label(".");
	private Label createdByLabel = new Label(Msg.translate(Env.getCtx(), "CreatedBy"));
	private Textbox createdByField = new Textbox();
	private Datebox createdField = new Datebox();
	
	private Label nameLabel = new Label(Msg.translate(Env.getCtx(), "Name"));
	private Textbox nameField = new Textbox("Name");
	private Label descriptionLabel = new Label(Msg.translate(Env.getCtx(), "Description"));
	private Textbox descriptionField = new Textbox("Description");
	private Label helpLabel = new Label(Msg.translate(Env.getCtx(), "Help"));
	private Textbox helpField = new Textbox("Help");
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private Button updateArchive = new Button(); 
		
	private Tabbox tabbox = new Tabbox();
	private Tabs tabs = new Tabs();
	private Tabpanels tabpanels = new Tabpanels(); 
	
	private Iframe iframe = new Iframe();
	
	public WArchiveViewer()
	{
		init(super.m_windowNo);
	}
	
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 */
	
	public void init (int WindowNo)
	{
		log.info("");
		m_WindowNo = WindowNo;

		try
		{
			dynInit();
			jbInit();
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "init", e);
		}
	}
	
	/**
	 *  Dynamic Init
	 */
	
	private void dynInit()
	{
		int AD_Role_ID = Env.getAD_Role_ID(Env.getCtx());

		//Processes
		boolean trl = !Env.isBaseLanguage(Env.getCtx(), "AD_Process");
		String lang = Env.getAD_Language(Env.getCtx());
		String sql = "SELECT DISTINCT p.AD_Process_ID,"
				+ (trl ? "trl.Name" : "p.Name ")
			+ " FROM AD_Process p INNER JOIN AD_Process_Access pa ON (p.AD_Process_ID=pa.AD_Process_ID) "
			+ (trl ? "LEFT JOIN AD_Process_Trl trl on (trl.AD_Process_ID=p.AD_Process_ID and trl.AD_Language=" + DB.TO_STRING(lang) + ")" : "") 
			+ " WHERE pa.AD_Role_ID=" + AD_Role_ID
			+ " AND p.IsReport='Y' AND p.IsActive='Y' AND pa.IsActive='Y' "
			+ "ORDER BY 2"; 
		
		processField = new Listbox();
		
		KeyNamePair[] keyNamePair = DB.getKeyNamePairs(sql, true);
		
		for (int i = 0; i < keyNamePair.length; i++)
		{
			processField.appendItem(keyNamePair[i].getName(), keyNamePair[i]);
		}
		
		//	Tables
		trl = !Env.isBaseLanguage(Env.getCtx(), "AD_Table");
		sql = "SELECT DISTINCT t.AD_Table_ID,"
				+ (trl ? "trl.Name" : "t.Name")
			+ " FROM AD_Table t INNER JOIN AD_Tab tab ON (tab.AD_Table_ID=t.AD_Table_ID)"
			+ " INNER JOIN AD_Window_Access wa ON (tab.AD_Window_ID=wa.AD_Window_ID) "
			+ (trl ? "LEFT JOIN AD_Table_Trl trl on (trl.AD_Table_ID=t.AD_Table_ID and trl.AD_Language=" + DB.TO_STRING(lang) + ")" : "") 
			+ " WHERE wa.AD_Role_ID=" + AD_Role_ID
			+ " AND t.IsActive='Y' AND tab.IsActive='Y' "
			+ "ORDER BY 2";
		
		tableField = new Listbox();
		
		keyNamePair = DB.getKeyNamePairs(sql, true);
		
		for (int i = 0; i < keyNamePair.length; i++)
		{
			tableField.appendItem(keyNamePair[i].getName(), keyNamePair[i]);
		}
		
		//	Internal Users
		sql = "SELECT AD_User_ID, Name "
			+ "FROM AD_User u WHERE EXISTS "
				+"(SELECT * FROM AD_User_Roles ur WHERE u.AD_User_ID=ur.AD_User_ID) "
			+ "ORDER BY 2";
		
		createdByQField = new Listbox();
		
		keyNamePair = DB.getKeyNamePairs(sql, true);
		
		for (int i = 0; i < keyNamePair.length; i++)
		{
			createdByQField.appendItem(keyNamePair[i].getName(), keyNamePair[i]);
		}
		
		MLookup lookup = MLookupFactory.get(Env.getCtx(), m_WindowNo,
				0, 2762, DisplayType.Search);

		bPartnerField = new WTableDirEditor(lookup, Msg.translate(
				Env.getCtx(), "C_BPartner_ID"), "", true, false, true);

		bPartnerField.addValueChangeListner(this);
	}	//	dynInit

	private void reportViewer(byte[] data)
	{
		
		AMedia media = new AMedia("Archive Viewer", "pdf", "application/pdf", data);
		iframe.setContent(media);
	}
	
	/**
	 *  Static Init
	 *  @throws Exception
	 */
	
	private void jbInit() throws Exception
	{
		tabbox.setWidth("100%");
		tabbox.appendChild(tabs);
		tabbox.appendChild(tabpanels);
		
		processField.setMold("select");
		processField.setRows(1);
		
		tableField.setMold("select");
		tableField.setRows(1);
		
		createdByQField.setMold("select");
		createdByQField.setRows(1);
		
		nameField.addEventListener(Events.ON_CHANGE, this);
		descriptionField.addEventListener(Events.ON_SELECT, this);
		helpField.addEventListener(Events.ON_SELECT, this);
		updateArchive.addEventListener(Events.ON_CLICK, this);
		
		reportField.setLabel(Msg.translate(Env.getCtx(), "IsReport"));
		reportField.addEventListener(Events.ON_CLICK, this);
		
		Hbox boxProcess = new Hbox();
		boxProcess.setWidth("100%");
		boxProcess.setWidth("30%, 70%");
		boxProcess.appendChild(processLabel);
		boxProcess.appendChild(processField);

		Hbox boxBPartner = new Hbox();
		boxBPartner.setWidth("100%");
		boxBPartner.setWidths("30%, 70%");
		boxBPartner.appendChild(bPartnerLabel);
		boxBPartner.appendChild(bPartnerField.getComponent());

		Hbox boxTable = new Hbox();
		boxTable.setWidth("100%");
		boxTable.setWidths("30%, 70%");
		boxTable.appendChild(tableLabel);
		boxTable.appendChild(tableField);

		Hbox boxNameQ = new Hbox();
		boxNameQ.setWidth("100%");
		boxNameQ.setWidths("30%, 70%");
		boxNameQ.appendChild(nameQLabel);
		boxNameQ.appendChild(nameQField);
		
		Hbox boxDescritionQ = new Hbox();
		boxDescritionQ.setWidth("100%");
		boxDescritionQ.setWidths("30%, 70%");
		boxDescritionQ.appendChild(descriptionQLabel);
		boxDescritionQ.appendChild(descriptionQField);

		Hbox boxHelpQ = new Hbox();
		boxHelpQ.setWidth("100%");
		boxHelpQ.setWidths("30%, 70%");
		boxHelpQ.appendChild(helpQLabel);
		boxHelpQ.appendChild(helpQField);
		
		Hbox boxCreatedBy = new Hbox();
		boxCreatedBy.setWidth("100%");
		boxCreatedBy.setWidths("30%, 70%");
		boxCreatedBy.appendChild(createdByQLabel);
		boxCreatedBy.appendChild(createdByQField);
		
		Hbox boxCreatedQ = new Hbox();
		boxCreatedQ.setWidth("100%");
		boxCreatedQ.setWidths("30%, 35%, 35%");
		boxCreatedQ.appendChild(createdQLabel);
		boxCreatedQ.appendChild(createdQFrom);
		boxCreatedQ.appendChild(createdQTo);
		
		queryPanel.setWidth("50%");
		queryPanel.appendChild(reportField);
		queryPanel.appendChild(boxProcess);
		queryPanel.appendChild(boxBPartner);
		queryPanel.appendChild(boxTable);
		queryPanel.appendChild(boxNameQ);
		queryPanel.appendChild(boxDescritionQ);
		queryPanel.appendChild(boxHelpQ);
		queryPanel.appendChild(boxCreatedBy);
		queryPanel.appendChild(boxCreatedQ);
		
		Tabpanel tabQueryPanel = new Tabpanel();
		tabQueryPanel.appendChild(queryPanel);
		
		Tab tabQuery = new Tab("Query");

		tabpanels.appendChild(tabQueryPanel);
		tabs.appendChild(tabQuery);

		bBack.addEventListener(Events.ON_CLICK, this);
		bNext.addEventListener(Events.ON_CLICK, this);
		
		Hbox boxViewEnter = new Hbox();
		
		bBack.setImage("/images/Parent24.gif");
		bNext.setImage("/images/Detail24.gif");
		
		boxViewEnter.setWidth("100%");
		boxViewEnter.setWidths("10%, 80%, 10%");
		boxViewEnter.appendChild(bBack);
		boxViewEnter.appendChild(positionInfo);
		boxViewEnter.appendChild(bNext);

		Hbox boxCreatedByV = new Hbox();
		boxCreatedByV.setWidth("100%");
		boxCreatedByV.setWidths("30%, 50%, 20%");
		boxCreatedByV.appendChild(createdByLabel);
		boxCreatedByV.appendChild(createdByField);
		boxCreatedByV.appendChild(createdField);

		Hbox boxName = new Hbox();
		boxName.setWidth("100%");
		boxName.setWidths("40%, 60%");
		boxName.appendChild(nameLabel);
		boxName.appendChild(nameField);
		
		Hbox boxDescription = new Hbox();
		boxDescription.setWidth("100%");
		boxDescription.setWidths("40%, 60%");
		boxDescription.appendChild(descriptionLabel);
		boxDescription.appendChild(descriptionField);
		
		Hbox boxHelp = new Hbox();
		boxHelp.setWidth("100%");
		boxHelp.setWidths("40%, 60%");
		boxHelp.appendChild(helpLabel);
		boxHelp.appendChild(helpField);
		
		updateArchive.setImage("/images/Ok24.gif");
		
		viewEnterPanel.setWidth("100%");
		viewEnterPanel.appendChild(boxViewEnter);
		viewEnterPanel.appendChild(boxCreatedByV);
		viewEnterPanel.appendChild(boxName);
		viewEnterPanel.appendChild(boxDescription);
		viewEnterPanel.appendChild(boxHelp);
		viewEnterPanel.appendChild(updateArchive);
		
		createdByField.setEnabled(false);
		createdField.setEnabled(false);
		
		Tab tabView = new Tab("View");
		
		Tabpanel tabViewPanel = new Tabpanel();
		Hbox boxViewSeparator = new Hbox();
		boxViewSeparator.setWidth("100%");
		boxViewSeparator.setWidths("50%, 50%");
		boxViewSeparator.appendChild(iframe);
		boxViewSeparator.appendChild(viewEnterPanel);
		tabViewPanel.appendChild(boxViewSeparator);

		tabs.appendChild(tabView);
		tabpanels.appendChild(tabViewPanel);
		
		confirmPanel.addActionListener(Events.ON_CLICK, this);
		updateQDisplay();

		iframe.setId("reportFrame");
		iframe.setHeight("100%");
		iframe.setWidth("100%");
		
		this.setWidth("900px");
		this.appendChild(tabbox);
		this.appendChild(new Separator());
		this.appendChild(confirmPanel);
	}
	
	public void onEvent(Event e) throws Exception 
	{
		log.info(e.getName());
		
		if (e.getTarget() == updateArchive)
			cmd_updateArchive();
		else if (confirmPanel.getButton("Cancel").equals(e.getTarget()))
			SessionManager.getAppDesktop().removeWindow();
		else if (confirmPanel.getButton("Ok").equals(e.getTarget()))
		{
			if (tabbox.getSelectedIndex() == 1)
				SessionManager.getAppDesktop().removeWindow();
			else
				cmd_query();
		}
		else if (e.getTarget() == reportField)
			updateQDisplay();
		else if (e.getTarget() == bBack)
			updateVDisplay(false);
		else if (e.getTarget() == bNext)
			updateVDisplay(true);
	}
	
	public void valueChange(ValueChangeEvent evt) 
	{
		if (m_archives.length > 0)
			updateArchive.setEnabled(true);
	}
	
	/**
	 * 	Update Query Display
	 */
	
	private void updateQDisplay()
	{
		boolean reports = reportField.isChecked();
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
		
		log.info("Index=" + m_index + ", Length=" + m_archives.length);
		
		if (m_archives.length == 0)
		{
			positionInfo.setValue("No Record Found");
			createdByField.setText("");
			createdField.setValue(null);
			nameField.setText("");
			descriptionField.setText("");
			helpField.setText("");
			iframe.getChildren().clear();
			return;
		}
		
		positionInfo.setValue(m_index+1 + " of " + m_archives.length);
		MArchive ar = m_archives[m_index];
		createdByField.setText(ar.getCreatedByName());
		createdField.setValue(ar.getCreated());
		nameField.setText(ar.getName());
		descriptionField.setText(ar.getDescription());
		helpField.setText(ar.getHelp());
		
		try
		{
			InputStream in = ar.getInputStream();
			//pdfViewer.setScale(reportField.isSelected() ? 50 : 75);
			if (in != null)
				reportViewer(ar.getBinaryData());//pdfViewer.loadPDF(in);
			else
				iframe.getChildren().clear();//pdfViewer.clearDocument();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "pdf", e);
			iframe.getChildren().clear();//pdfViewer.clearDocument();
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
		reportField.setChecked(isReport);
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
		boolean reports = reportField.isChecked();
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
			ListItem listitem = processField.getSelectedItem();
			
			KeyNamePair nn = null;
			
			if (listitem != null)
				nn = (KeyNamePair)listitem.getValue();
			
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
			ListItem listitem = tableField.getSelectedItem();
			
			KeyNamePair nn = null;
			
			if (listitem != null)
				nn = (KeyNamePair)listitem.getValue();
			
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
			if (ss.indexOf('%') != -1 || ss.indexOf('_') != -1)
				sql.append(" AND Name LIKE ").append(DB.TO_STRING(ss));
			else
				sql.append(" AND Name=").append(DB.TO_STRING(ss));
		}
		
		//	Description
		ss = descriptionQField.getText();
		if (ss != null && ss.length() > 0)
		{
			if (ss.indexOf('%') != -1 || ss.indexOf('_') != -1)
				sql.append(" AND Description LIKE ").append(DB.TO_STRING(ss));
			else
				sql.append(" AND Description=").append(DB.TO_STRING(ss));
		}

		//	Help
		ss = helpQField.getText();
		if (ss != null && ss.length() > 0)
		{
			if (ss.indexOf('%') != -1 || ss.indexOf('_') != -1)
				sql.append(" AND Help LIKE ").append(DB.TO_STRING(ss));
			else
				sql.append(" AND Help=").append(DB.TO_STRING(ss));
		}

		//	CreatedBy
		ListItem listitem = createdByQField.getSelectedItem();
		
		KeyNamePair nn = null;
		
		if (listitem != null)
			nn = (KeyNamePair)listitem.getValue();
		
		if (nn != null && nn.getKey() > 0)
			sql.append(" AND CreatedBy=").append(nn.getKey());
		
		//	Created
		Date date = null;
		Timestamp tt  =null;
		
		if (createdQFrom.getValue() != null)
		{
			date = createdQFrom.getValue();
			tt = new Timestamp(date.getTime());
		}

		if (tt != null)
			sql.append(" AND Created>=").append(DB.TO_DATE(tt, true));
		
		if (createdQTo.getValue() != null)
		{
			date = createdQTo.getValue();
			tt = new Timestamp(date.getTime());
		}
		
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
		
		m_archives = MArchive.get(Env.getCtx(), sql.toString());
		log.info("Length=" + m_archives.length);
		
		//	Display
		//this.setSelectedIndex(1);
		m_index = 1;
		updateVDisplay(false);
	}	//	cmd_query	
}