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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;
import javax.swing.table.DefaultTableModel;

import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.plaf.PLAFEditorPanel;
import org.compiere.db.CConnection;
import org.compiere.grid.ed.VDate;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MRole;
import org.compiere.model.MSystem;
import org.compiere.model.MUser;
import org.compiere.print.CPrinter;
import org.compiere.swing.CButton;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CDialog;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTabbedPane;
import org.compiere.swing.CTextArea;
import org.compiere.swing.CToggleButton;
import org.compiere.util.CLogErrorBuffer;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

/**
 *	Customize settings like L&F, AutoCommit, etc. & Diagnostics
 * <p>
 * Change log:
 * <ul>
 * <li>2007-02-12 - teo_sarca - [ 1658127 ] Select charset encoding on import
 * </ul>
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: Preference.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *  
 *  @author Low Heng Sin
 *  @version 2006-11-27
 */
public final class Preference extends CDialog
	implements ActionListener, ListSelectionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8923143271736597338L;

	/**
	 *	Standard Constructor
	 *  @param frame frame
	 *  @param WindowNo window
	 */
	public Preference(Frame frame, int WindowNo)
	{
		super(frame, Msg.getMsg(Env.getCtx(), "Preference"), true);
		log.config("Preference");
		try
		{
			jbInit();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, ex.getMessage());
		}
		load();
		//
		StringBuffer sta = new StringBuffer("#");
		sta.append(Env.getCtx().size()).append(" - ")
			.append(Msg.translate(Env.getCtx(), "AD_Window_ID"))
			.append("=").append(WindowNo);
		statusBar.setStatusLine(sta.toString());
		statusBar.setStatusDB("");
		AEnv.positionCenterWindow(frame, this);
	}	//	Preference

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(Preference.class);

	private CPanel panel = new CPanel();
	private BorderLayout panelLayout = new BorderLayout();
	private CTabbedPane tabPane = new CTabbedPane();
	private CPanel customizePane = new CPanel();
	private CPanel contextPane = new CPanel();
	private GridBagLayout customizeLayout = new GridBagLayout();
	private CCheckBox autoCommit = new CCheckBox();
	private CCheckBox autoNew = new CCheckBox();
	private CCheckBox printPreview = new CCheckBox();
	private CCheckBox validateConnectionOnStartup = new CCheckBox();
	private CCheckBox singleInstancePerWindow = new CCheckBox();
	private CCheckBox openWindowMaximized = new CCheckBox();
	private CPanel southPanel = new CPanel();
	private BorderLayout southLayout = new BorderLayout();
	private BorderLayout icontextLayout = new BorderLayout();
	private JList infoList = new JList();
	private JScrollPane contextListScrollPane = new JScrollPane(infoList);
	private CPanel contextSouthPanel = new CPanel();
	private CTextArea contextHeader = new CTextArea(4,15);
	private CTextArea contextDetail = new CTextArea(4,35);
	private CTextArea infoArea = new CTextArea(5, 30);
	private BorderLayout contextSouthLayout = new BorderLayout();
	private StatusBar statusBar = new StatusBar();
	private ConfirmPanel confirm = new ConfirmPanel(true);
	private CComboBox traceLevel = new CComboBox(CLogMgt.LEVELS);
	private CLabel traceLabel = new CLabel();
	private CCheckBox traceFile = new CCheckBox();
	private CCheckBox autoLogin = new CCheckBox();
	private CCheckBox adempiereSys = new CCheckBox();
	private CCheckBox logMigrationScript = new CCheckBox();
	private CCheckBox storePassword = new CCheckBox();
	private CCheckBox showTrl = new CCheckBox();
	private CCheckBox showAcct = new CCheckBox();
	private CCheckBox showAdvanced = new CCheckBox();
	private CCheckBox cacheWindow = new CCheckBox();
	private CLabel lPrinter = new CLabel();
	private CPrinter fPrinter = new CPrinter();
	private CLabel lDate = new CLabel();
	private VDate fDate = new VDate();
	private CComboBox connectionProfile = new CComboBox(CConnection.CONNECTIONProfiles);
	private CLabel connectionProfileLabel = new CLabel();
	private CPanel errorPane = new CPanel();
	private BorderLayout errorLayout = new BorderLayout();
	private JScrollPane errorScrollPane = new JScrollPane();
	private MiniTable errorTable = new MiniTable();
	private CPanel errorPanel = new CPanel(new FlowLayout(FlowLayout.TRAILING));
	private CToggleButton bErrorsOnly = new CToggleButton(Msg.getMsg(Env.getCtx(), "ErrorsOnly"));
	private CButton bErrorReset = new CButton(Msg.getMsg(Env.getCtx(), "Reset"));
	private CButton bErrorEMail = new CButton(Msg.getMsg(Env.getCtx(), "SendEMail"));
	private CButton bErrorSave = new CButton(Msg.getMsg(Env.getCtx(), "SaveFile"));
	private CButton bRoleInfo = new CButton(Msg.translate(Env.getCtx(), "AD_Role_ID"));
	// Charset:
	private CLabel lCharset = new CLabel();
	private CComboBox fCharset = new CComboBox(Ini.getAvailableCharsets());

	private CPanel configPanel = new CPanel();

	private PLAFEditorPanel plafEditor = new PLAFEditorPanel();
	
	/**
	 *	Static Init.
	 *  <pre>
	 *  - panel
	 *      - tabPane
	 *          - customizePane
	 *              - infoArea
	 *              - fields ...
	 *          - contextPane
	 *              - contextList
	 *              - contextSouthPanel
	 *                  - contextHeader
	 *                  - contextDetail
	 * 			- errorPane
	 * 				- errorScollPane
	 * 					- errorTable
	 *      - southPanel
	 *  </pre>
	 *  @throws Exception
	 */
	void jbInit() throws Exception
	{
		traceLabel.setRequestFocusEnabled(false);
		traceLabel.setText(Msg.getMsg(Env.getCtx(), "TraceLevel", true));
		traceLabel.setToolTipText(Msg.getMsg(Env.getCtx(), "TraceLevel", false));
		traceFile.setText(Msg.getMsg(Env.getCtx(), "TraceFile", true));
		traceFile.setToolTipText(Msg.getMsg(Env.getCtx(), "TraceFile", false));

		autoCommit.setText(Msg.getMsg(Env.getCtx(), "AutoCommit", true));
		autoCommit.setToolTipText(Msg.getMsg(Env.getCtx(), "AutoCommit", false));
		autoNew.setText(Msg.getMsg(Env.getCtx(), "AutoNew", true));
		autoNew.setToolTipText(Msg.getMsg(Env.getCtx(), "AutoNew", false));
		adempiereSys.setText(Msg.getMsg(Env.getCtx(), "AdempiereSys", true));
		adempiereSys.setToolTipText(Msg.getMsg(Env.getCtx(), "AdempiereSys", false));
		logMigrationScript.setText(Msg.getMsg(Env.getCtx(), "LogMigrationScript", true));
		logMigrationScript.setToolTipText(Msg.getMsg(Env.getCtx(), "LogMigrationScript", false));
		printPreview.setText(Msg.getMsg(Env.getCtx(), "AlwaysPrintPreview", true));
		printPreview.setToolTipText(Msg.getMsg(Env.getCtx(), "AlwaysPrintPreview", false));
		validateConnectionOnStartup.setText(Msg.getMsg(Env.getCtx(), "ValidateConnectionOnStartup", true));
		validateConnectionOnStartup.setToolTipText(Msg.getMsg(Env.getCtx(), "ValidateConnectionOnStartup", false));
		singleInstancePerWindow.setText(Msg.getMsg(Env.getCtx(), "SingleInstancePerWindow", true));
		singleInstancePerWindow.setToolTipText(Msg.getMsg(Env.getCtx(), "SingleInstancePerWindow", false));
		openWindowMaximized.setText(Msg.getMsg(Env.getCtx(), "OpenWindowMaximized", true));
		openWindowMaximized.setToolTipText(Msg.getMsg(Env.getCtx(), "OpenWindowMaximized", false));
		autoLogin.setText(Msg.getMsg(Env.getCtx(), "AutoLogin", true));
		autoLogin.setToolTipText(Msg.getMsg(Env.getCtx(), "AutoLogin", false));
		storePassword.setText(Msg.getMsg(Env.getCtx(), "StorePassword", true));
		storePassword.setToolTipText(Msg.getMsg(Env.getCtx(), "StorePassword", false));
		showTrl.setText(Msg.getMsg(Env.getCtx(), "ShowTrlTab", true));
		showTrl.setToolTipText(Msg.getMsg(Env.getCtx(), "ShowTrlTab", false));
		showAcct.setText(Msg.getMsg(Env.getCtx(), "ShowAcctTab", true));
		showAcct.setToolTipText(Msg.getMsg(Env.getCtx(), "ShowAcctTab", false));
		showAdvanced.setText(Msg.getMsg(Env.getCtx(), "ShowAdvancedTab", true));
		showAdvanced.setToolTipText(Msg.getMsg(Env.getCtx(), "ShowAdvancedTab", false));
		connectionProfileLabel.setText(Msg.getElement(Env.getCtx(), "ConnectionProfile"));
		cacheWindow.setText(Msg.getMsg(Env.getCtx(), "CacheWindow", true));
		cacheWindow.setToolTipText(Msg.getMsg(Env.getCtx(), "CacheWindow", false));
		lPrinter.setText(Msg.getMsg(Env.getCtx(), "Printer"));
		lDate.setText(Msg.getMsg(Env.getCtx(), "Date"));
		infoArea.setReadWrite(false);
		// Charset:
		lCharset.setText(Msg.getMsg(Env.getCtx(), "Charset", true));
		lCharset.setToolTipText(Msg.getMsg(Env.getCtx(), "Charset", false));
		
		getContentPane().add(panel);
		panel.setLayout(panelLayout);
		panel.add(tabPane, BorderLayout.CENTER);
		//	Customize
		tabPane.add(customizePane,  Msg.getMsg(Env.getCtx(), "Preference"));
		customizePane.setLayout(customizeLayout);
		customizePane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		Border insetBorder = BorderFactory.createEmptyBorder(2, 2, 2, 0); 
		if (MSystem.isSwingRememberPasswordAllowed()) {
			CPanel loginPanel = new CPanel();
			loginPanel.setBorder(BorderFactory.createTitledBorder(Msg.getMsg(Env.getCtx(), "Login")));
			loginPanel.setLayout(new GridLayout(1, 2));
			autoLogin.setBorder(insetBorder);
			storePassword.setBorder(insetBorder);
			loginPanel.add(autoLogin);
			loginPanel.add(storePassword);

			customizePane.add(loginPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
					,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 0, 2, 0), 0, 0));
		}
		
		CPanel windowPanel = new CPanel();
		windowPanel.setBorder(BorderFactory.createTitledBorder(Msg.getMsg(Env.getCtx(), "Window")));
		windowPanel.setLayout(new GridLayout(4, 2));
		windowPanel.add(showAcct);showAcct.setBorder(insetBorder);
		windowPanel.add(showTrl);showTrl.setBorder(insetBorder);
		windowPanel.add(showAdvanced);showAdvanced.setBorder(insetBorder);
		windowPanel.add(autoCommit);autoCommit.setBorder(insetBorder);
		windowPanel.add(autoNew);autoNew.setBorder(insetBorder);
		windowPanel.add(cacheWindow);cacheWindow.setBorder(insetBorder);
		windowPanel.add(openWindowMaximized);openWindowMaximized.setBorder(insetBorder);
		windowPanel.add(singleInstancePerWindow);singleInstancePerWindow.setBorder(insetBorder);
		customizePane.add(windowPanel, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 0, 2, 0), 0, 0));
		
		CPanel connPanel = new CPanel();
		connPanel.setBorder(BorderFactory.createTitledBorder(Msg.getMsg(Env.getCtx(), "Connection")));
		connPanel.setLayout(new GridBagLayout());
		connPanel.add(connectionProfileLabel,    new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 2, 2, 0), 0, 0));
		connPanel.add(connectionProfile,    new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 2, 2, 0), 0, 0));
		connPanel.add(validateConnectionOnStartup,    new GridBagConstraints(0, 1, 2, 1, 1.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 2, 2, 0), 0, 0));
		customizePane.add(connPanel, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 0, 2, 0), 0, 0));
		
		CPanel tracePanel = new CPanel();
		tracePanel.setBorder(BorderFactory.createTitledBorder(Msg.getMsg(Env.getCtx(), "TraceInfo")));
		tracePanel.setLayout(new GridBagLayout());
		tracePanel.add(traceLabel,    new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 2, 2, 0), 0, 0));
		tracePanel.add(traceLevel,       new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 2, 2, 0), 0, 0));
		tracePanel.add(traceFile,       new GridBagConstraints(0, 1, 2, 1, 1.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 2, 2, 0), 0, 0));
		customizePane.add(tracePanel, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 0, 2, 0), 0, 0));
		
		CPanel printPanel = new CPanel();
		printPanel.setBorder(BorderFactory.createTitledBorder(Msg.getMsg(Env.getCtx(), "Printing")));
		printPanel.setLayout(new GridBagLayout());
		printPanel.add(lPrinter,     new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 2, 2, 0), 0, 0));
		printPanel.add(fPrinter,         new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 2, 2, 0), 0, 0));
		printPanel.add(printPreview,    new GridBagConstraints(0, 1, 2, 1, 1.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 2, 2, 0), 0, 0));
		customizePane.add(printPanel, new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 0, 2, 0), 0, 0));
		
		CPanel otherPanel = new CPanel();
		otherPanel.setBorder(BorderFactory.createEmptyBorder());
		otherPanel.setLayout(new GridLayout());
		CPanel datePanel = new CPanel();
		datePanel.setLayout(new FlowLayout());
		((FlowLayout)datePanel.getLayout()).setAlignment(FlowLayout.LEFT);
		datePanel.add(lDate);
		datePanel.add(fDate);
		otherPanel.add(datePanel);datePanel.setBorder(insetBorder);
		otherPanel.add(adempiereSys);adempiereSys.setBorder(insetBorder);
		otherPanel.add(logMigrationScript);logMigrationScript.setBorder(insetBorder);
		customizePane.add(otherPanel, new GridBagConstraints(0, 5, 1, 1, 1.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 0, 2, 0), 0, 0));
		
		// Charset:
		CPanel charsetPanel = new CPanel();
		charsetPanel.setBorder(BorderFactory.createEmptyBorder());
		charsetPanel.setLayout(new FlowLayout());
		((FlowLayout)charsetPanel.getLayout()).setAlignment(FlowLayout.LEFT);
		charsetPanel.add(lCharset);
		charsetPanel.add(fCharset);
		customizePane.add(charsetPanel, new GridBagConstraints(0, 6, 1, 1, 1.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 0, 2, 0), 0, 0));
		
		CPanel themePanel = new CPanel();
		themePanel.setLayout(new GridLayout(1, 1));
		
		themePanel.add(plafEditor);
		tabPane.add(themePanel, Msg.getMsg(Env.getCtx(), "UITheme", true));
		
		configPanel.setLayout(new BorderLayout());
		configPanel.add(infoArea, BorderLayout.CENTER);
		CPanel configSouth = new CPanel();
		configSouth.setLayout(new FlowLayout());
		((FlowLayout)configSouth.getLayout()).setAlignment(FlowLayout.RIGHT);
		configSouth.add(bRoleInfo);
		configPanel.add(configSouth, BorderLayout.SOUTH);
		tabPane.add(configPanel, Msg.getMsg(Env.getCtx(), "Info"));
		
		//	Info
		tabPane.add(contextPane,  Msg.getMsg(Env.getCtx(), "Context"));
		contextPane.setLayout(icontextLayout);
		contextPane.add(contextListScrollPane, BorderLayout.CENTER);
		contextListScrollPane.setPreferredSize(new Dimension(200, 300));
		infoList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		infoList.setBackground(AdempierePLAF.getFieldBackground_Inactive());
		infoList.addListSelectionListener(this);
		infoList.setFixedCellWidth(30);
		contextPane.add(contextSouthPanel, BorderLayout.SOUTH);
		contextSouthPanel.setLayout(contextSouthLayout);
		contextSouthPanel.add(contextHeader, BorderLayout.WEST);
		contextHeader.setBackground(SystemColor.info);
		contextHeader.setReadWrite(false);
		contextHeader.setLineWrap(true);
		contextHeader.setWrapStyleWord(true);
		contextHeader.setBorder(BorderFactory.createLoweredBevelBorder());
		contextSouthPanel.add(contextDetail, BorderLayout.CENTER);
		contextDetail.setBackground(SystemColor.info);
		contextDetail.setReadWrite(false);
		contextDetail.setLineWrap(true);
		contextDetail.setWrapStyleWord(true);
		contextDetail.setBorder(BorderFactory.createLoweredBevelBorder());
		//	Error Pane
		errorPane.setLayout(errorLayout);
//		tabPane.add(errorPane,  Msg.getMsg(Env.getCtx(), "Errors"));
		tabPane.add(errorPane,  "Errors");
		errorPane.add(errorScrollPane, BorderLayout.CENTER);
		errorScrollPane.getViewport().add(errorTable, null);
		//
		errorPanel.add(bErrorsOnly);
		errorPanel.add(bErrorReset);
		errorPanel.add(bErrorEMail);
		errorPanel.add(bErrorSave);
		errorPane.add(errorPanel, BorderLayout.SOUTH);
		//	South
		panel.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(southLayout);
		southPanel.add(statusBar, BorderLayout.SOUTH);
		southPanel.add(confirm, BorderLayout.CENTER);
		//
		bRoleInfo.addActionListener(this);
		confirm.addActionListener(this);
	}	//	jbInit


	/**
	 *	List Selection Listener - show info in header/detail fields
	 *  @param e evant
	 */
	public void valueChanged(ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting())
			return;

		String value = (String)infoList.getSelectedValue();
		if (value == null)
			return;
		int pos = value.indexOf("==");
		if (pos == -1)
		{
			contextHeader.setText("");
			contextDetail.setText(value);
		}
		else
		{
			contextHeader.setText(value.substring(0, pos).replace('|','\n'));
			contextDetail.setText(value.substring(pos+3));
		}
	}	//	valueChanged


	/**
	 *	ActionListener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			dispose();
		else if (e.getActionCommand().equals(ConfirmPanel.A_OK))
			cmd_save();
		//
		else if (e.getSource() == bErrorsOnly)
			cmd_displayErrors();
		else if (e.getSource() == bErrorReset)
			cmd_errorReset();
		else if (e.getSource() == bErrorEMail)
			cmd_errorEMail();
		else if (e.getSource() == bErrorSave)
			cmd_errorSave();
		//
		else if (e.getSource() == bRoleInfo)
			ADialog.info(0, this, "RoleInfo", MRole.getDefault().toStringX(Env.getCtx()));
	}	//	actionPerformed


	/**
	 *	Load Settings - and Context
	 */
	private void load()
	{
		log.config("");
		infoArea.setText(CLogMgt.getInfo(null).toString());
		infoArea.setCaretPosition(0);

		//	--	Load Settings	--
		//	AutoCommit
		autoCommit.setSelected(Env.isAutoCommit(Env.getCtx()));
		autoNew.setSelected(Env.isAutoNew(Env.getCtx()));
		//	AdempiereSys
		adempiereSys.setSelected(Ini.isPropertyBool(Ini.P_ADEMPIERESYS));
		//	LogMigrationScript
		logMigrationScript.setSelected(Ini.isPropertyBool(Ini.P_LOGMIGRATIONSCRIPT));
		if (Env.getAD_Client_ID(Env.getCtx()) > 20)
		{
			adempiereSys.setSelected(false);
			adempiereSys.setEnabled(false);
			if (Env.getAD_User_ID(Env.getCtx()) > 100) {
				// disable log migration scripts on clients non-GW for non-SuperUser 
				logMigrationScript.setSelected(false);
				logMigrationScript.setEnabled(false);
			}
		}
		if (MSystem.isSwingRememberPasswordAllowed()) {
			//	AutoLogin
			autoLogin.setSelected(Ini.isPropertyBool(Ini.P_A_LOGIN));
			//	Save Password
			storePassword.setSelected(Ini.isPropertyBool(Ini.P_STORE_PWD));
		} else {
			autoLogin.setSelected(false);
			storePassword.setSelected(false);
		}
		//	Show Acct Tab
		if (MRole.getDefault().isShowAcct())
			showAcct.setSelected(Ini.isPropertyBool(Ini.P_SHOW_ACCT));
		else
		{
			showAcct.setSelected(false);
			showAcct.setReadWrite(false);
		}
		//	Show Trl/Advanced Tab
		showTrl.setSelected(Ini.isPropertyBool(Ini.P_SHOW_TRL));
		showAdvanced.setSelected(Ini.isPropertyBool(Ini.P_SHOW_ADVANCED));
		
		//  Connection Profile
		MUser user = MUser.get(Env.getCtx());
		String cp = user.getConnectionProfile();
		if (cp == null)
			cp = MRole.getDefault().getConnectionProfile();
		if (cp != null)
		{
			CConnection.get().setConnectionProfile(cp);
			connectionProfile.setReadWrite(false);
		}
		connectionProfile.setSelectedItem(CConnection.get().getConnectionProfilePair());
		cacheWindow.setSelected(Ini.isCacheWindow());
		
		//  Print Preview
		printPreview.setSelected(Ini.isPropertyBool(Ini.P_PRINTPREVIEW));

		//  Validate Connection on Startup
		validateConnectionOnStartup.setSelected(Ini.isPropertyBool(Ini.P_VALIDATE_CONNECTION_ON_STARTUP));

		//  Single Instance per Window
		singleInstancePerWindow.setSelected(Ini.isPropertyBool(Ini.P_SINGLE_INSTANCE_PER_WINDOW));

		//  Open Window Maximized
		openWindowMaximized.setSelected(Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED));

		//	TraceLevel
		traceLevel.setSelectedItem(CLogMgt.getLevel());
		traceFile.setSelected(Ini.isPropertyBool(Ini.P_TRACEFILE));
		//  Printer
		fPrinter.setValue(Env.getContext(Env.getCtx(), "#Printer"));
		//  Date
		fDate.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		// Charset
		fCharset.setSelectedItem(Ini.getCharset());

		//	--	Load and sort Context	--
		String[] context = Env.getEntireContext(Env.getCtx());
		Arrays.sort(context);
		infoList.setListData(context);

		//	Load Errors
	//	CLogMgt mgt = new CLogMgt();		//	creates test trace
		bErrorsOnly.setSelected(true);
		errorTable.setCellSelectionEnabled(true);
		cmd_displayErrors();
	//	for (int i = 2; i < 6; i++)
	//		errorTable.setColumnReadOnly(i, false);
		//
		bErrorsOnly.addActionListener(this);
		bErrorReset.addActionListener(this);
		bErrorSave.addActionListener(this);
		bErrorEMail.addActionListener(this);
	}	//	load

	/**
	 *	Save Settings
	 */
	private void cmd_save()
	{
		log.config("");
		//  UI
		//	AutoCommit
		Ini.setProperty(Ini.P_A_COMMIT, (autoCommit.isSelected()));
		Env.setAutoCommit(Env.getCtx(), autoCommit.isSelected());
		Ini.setProperty(Ini.P_A_NEW, (autoNew.isSelected()));
		Env.setAutoNew(Env.getCtx(), autoNew.isSelected());
		//	AdempiereSys
		Ini.setProperty(Ini.P_ADEMPIERESYS, adempiereSys.isSelected());
		//	LogMigrationScript
		Ini.setProperty(Ini.P_LOGMIGRATIONSCRIPT, logMigrationScript.isSelected());
		if (MSystem.isSwingRememberPasswordAllowed()) {
			//	AutoLogin
			Ini.setProperty(Ini.P_A_LOGIN, (autoLogin.isSelected()));
			//	Save Password
			Ini.setProperty(Ini.P_STORE_PWD, (storePassword.isSelected()));
		} else {
			Ini.setProperty(Ini.P_A_LOGIN, false);
			Ini.setProperty(Ini.P_STORE_PWD, false);
		}
		//	Show Acct Tab
		Ini.setProperty(Ini.P_SHOW_ACCT, (showAcct.isSelected()));
		Env.setContext(Env.getCtx(), "#ShowAcct", (showAcct.isSelected()));
		//	Show Trl Tab
		Ini.setProperty(Ini.P_SHOW_TRL, (showTrl.isSelected()));
		Env.setContext(Env.getCtx(), "#ShowTrl", (showTrl.isSelected()));
		//	Show Advanced Tab
		Ini.setProperty(Ini.P_SHOW_ADVANCED, (showAdvanced.isSelected()));
		Env.setContext(Env.getCtx(), "#ShowAdvanced", (showAdvanced.isSelected()));
		
		//  ConnectionProfile
		ValueNamePair ppNew = (ValueNamePair)connectionProfile.getSelectedItem();
		String cpNew = ppNew.getValue();
		String cpOld = CConnection.get().getConnectionProfile(); 
		CConnection.get().setConnectionProfile(cpNew);
		if (!cpNew.equals(cpOld)
			&& (cpNew.equals(CConnection.PROFILE_WAN) || cpOld.equals(CConnection.PROFILE_WAN))) 
			ADialog.info(0, this, "ConnectionProfileChange");
		Ini.setProperty(Ini.P_CACHE_WINDOW, cacheWindow.isSelected());
		
		//  Print Preview
		Ini.setProperty(Ini.P_PRINTPREVIEW, (printPreview.isSelected()));
		//  Validate Connection on Startup
		Ini.setProperty(Ini.P_VALIDATE_CONNECTION_ON_STARTUP, (validateConnectionOnStartup.isSelected()));
		//  Single Instance per Window
		Ini.setProperty(Ini.P_SINGLE_INSTANCE_PER_WINDOW, (singleInstancePerWindow.isSelected()));
		//  Open Window Maximized
		Ini.setProperty(Ini.P_OPEN_WINDOW_MAXIMIZED, (openWindowMaximized.isSelected()));
		//	TraceLevel/File
		Level level = (Level)traceLevel.getSelectedItem();
		CLogMgt.setLevel(level);
		Ini.setProperty(Ini.P_TRACELEVEL, level.getName());
		Ini.setProperty(Ini.P_TRACEFILE, traceFile.isSelected());
		//  Printer
		String printer = (String)fPrinter.getSelectedItem();
		Env.setContext(Env.getCtx(), "#Printer", printer);
		Ini.setProperty(Ini.P_PRINTER, printer);
		//  Date (remove seconds)
		java.sql.Timestamp ts = (java.sql.Timestamp)fDate.getValue();
		if (ts != null)
			Env.setContext(Env.getCtx(), "#Date", ts);
		// Charset
		Charset charset = (Charset)fCharset.getSelectedItem();
		Ini.setProperty(Ini.P_CHARSET, charset.name());

		//UI
		ValueNamePair laf = plafEditor.getSelectedLook();
		ValueNamePair theme = plafEditor.getSelectedTheme();
		if ( laf != null ) {
			String clazz = laf.getValue();
			String currentLaf = UIManager.getLookAndFeel().getClass().getName();
			if (clazz != null && clazz.length() > 0 && !currentLaf.equals(clazz))
			{
				//laf changed
				AdempierePLAF.setPLAF(laf, theme, true);
				AEnv.updateUI();
			}
			else
			{
				if (UIManager.getLookAndFeel() instanceof MetalLookAndFeel)
				{
					MetalTheme currentTheme = MetalLookAndFeel.getCurrentTheme();
					String themeClass = currentTheme.getClass().getName();
					String sTheme = theme.getValue();
					if (sTheme != null && sTheme.length() > 0 && !sTheme.equals(themeClass))
					{
						ValueNamePair plaf = new ValueNamePair(
								UIManager.getLookAndFeel().getClass().getName(),
								UIManager.getLookAndFeel().getName());
						AdempierePLAF.setPLAF(plaf, theme, true);
						AEnv.updateUI();
					}
				}
			}
		}
		
		Ini.saveProperties(Ini.isClient());
		dispose();
	}	//	cmd_save
	
	/**
	 * 	(Re)Display Errors
	 */
	private void cmd_displayErrors()
	{
		Vector data = CLogErrorBuffer.get(true).getLogData(bErrorsOnly.isSelected());
		Vector columnNames = CLogErrorBuffer.get(true).getColumnNames(Env.getCtx());
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		errorTable.setModel(model);
		//
		if (bErrorsOnly.isSelected())
			tabPane.setTitleAt(4, Msg.getMsg(Env.getCtx(), "Errors") + " (" + data.size() + ")");
		else
			tabPane.setTitleAt(4, Msg.getMsg(Env.getCtx(), "TraceInfo") + " (" + data.size() + ")");
		errorTable.autoSize();
	}	//	cmd_errorsOnly
	
	/**
	 * 	Reset Errors
	 */
	private void cmd_errorReset()
	{
		CLogErrorBuffer.get(true).resetBuffer(bErrorsOnly.isSelected());
		cmd_displayErrors();
	}	//	cmd_errorReset

	/**
	 * 	EMail Errors
	 */
	private void cmd_errorEMail()
	{
		EMailDialog emd = new EMailDialog(this, 
			"EMail Trace", 
			MUser.get(Env.getCtx()), 
			"",			//	to 
			"Adempiere Trace Info", 
			CLogErrorBuffer.get(true).getErrorInfo(Env.getCtx(), bErrorsOnly.isSelected()), 
			null);
		
	}	//	cmd_errorEMail
	
	/**
	 * 	Save Error to File
	 */
	private void cmd_errorSave()
	{
	    JFileChooser chooser = new JFileChooser();
	    chooser.setDialogType(JFileChooser.SAVE_DIALOG);
	    chooser.setDialogTitle("Adempiere Trace File");
	    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    chooser.setSelectedFile(new File ("traceInfo.log"));
	    int returnVal = chooser.showSaveDialog(this);
	    if(returnVal != JFileChooser.APPROVE_OPTION)
	    	return;
	    try
	    {
	    	File file = chooser.getSelectedFile();
	    	FileWriter writer = new FileWriter(file);
	    	writer.write(CLogErrorBuffer.get(true).getErrorInfo(Env.getCtx(), bErrorsOnly.isSelected()));
	    	writer.flush();
	    	writer.close();
	    }
	    catch (Exception e)
	    {
	    	log.log(Level.SEVERE, "", e);
	    }
	}	//	cmd_errorSave
		
}	//	Preference
