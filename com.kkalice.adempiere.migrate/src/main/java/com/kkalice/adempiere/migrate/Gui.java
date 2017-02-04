/*
 * Name:		Gui.java
 * Description:	graphical user interface for migration tool
 * Created:		Dec 19, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/Gui.java
 * FileOwner:	spc.dvp
 * FilePerms:	0644
 *
 */

/**
 * This file is part of Adempiere ERP Bazaar
 * http://www.adempiere.org
 * 
 * Copyright (C) Stefan Christians
 * Copyright (C) Contributors
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * Contributors:
 * - Stefan Christians
 * 
 * Sponsors:
 * - K.K. Alice
 * 
 */

package com.kkalice.adempiere.migrate;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;


/**
 * graphical user interface for migration tool
 * @author Stefan Christians
 */
public class Gui implements ActionListener, FocusListener {
	
	// MEMBERS
	// =======

	/** parameters */
	private static Parameters s_parameters = null;
	/** logger */
	private static MigrateLogger s_logger = null;
	/** dbEngine */
	private static DBEngine s_dbEngine = null;

	/** the migrator */
	private static Migrate s_migrator = null;
	
	/** graphics or text mode */
	private static boolean s_isTextMode = false; 
	
	/** identifies a source connection */
	private static final boolean s_isSource = true;
	/** identifies a target connection */
	private static final boolean s_isTarget = false;

	
	// gui components
	
	/** this gui has been initialized */
	private boolean m_isInitialized = false;
	/** the event handler is busy */
	private boolean m_isBusy = false;
	/** the migration process is running */
	private boolean m_isMigrationStarted = false;
	
	// the main window
	/** root frame */
	JFrame m_frame = null;
	
	// main menu
	/** the menu bar */
	private JMenuBar m_menuBar = new JMenuBar();
	/** the file menue */
	private JMenu m_menuFile = new JMenu();
	/** file menu save */
	private JMenuItem m_menuFileSave = new JMenuItem();
	/** file menu close */
	private JMenuItem m_menuFileClose = new JMenuItem();
	/** file menu exit */
	private JMenuItem m_menuFileExit = new JMenuItem();
	/** the help menu */
	private JMenu m_menuHelp = new JMenu();
	/** help menu info */
	private JMenuItem m_menuHelpInfo = new JMenuItem();
	/** help menu about */
	private JMenuItem m_menuHelpAbout = new JMenuItem();
	
	// source parameters
	/** adempiere version of source database */
	private JTextField m_sourceVersion = new JTextField();
	/** source database vendor */
	private JComboBox m_sourceVendor = new JComboBox();
	/** source host */
	private JTextField m_sourceHost = new JTextField();
	/** source port */
	private JTextField m_sourcePort = new JTextField();
	/** source database name */
	private JComboBox m_sourceName = new JComboBox();
	/** source URL */
	private JTextField m_sourceURL = new JTextField();
	/** source catalog */
	private JComboBox m_sourceCatalog = new JComboBox();
	/** source schema */
	private JComboBox m_sourceSchema = new JComboBox();
	/** source user */
	private JTextField m_sourceUser = new JTextField();
	/** source password */
	private JTextField m_sourcePassword = new JTextField();
	/** source system user */
	private JTextField m_sourceSystemUser = new JTextField();
	/** source system password */
	private JTextField m_sourceSystemPassword = new JTextField();
	/** source reset button */
	private JButton m_sourceReset = new JButton();
	
	// target parameters
	/** adempiere version of target database */
	private JTextField m_targetVersion = new JTextField();
	/** target database vendor */
	private JComboBox m_targetVendor = new JComboBox();
	/** target host */
	private JTextField m_targetHost = new JTextField();
	/** target port */
	private JTextField m_targetPort = new JTextField();
	/** target database name */
	private JComboBox m_targetName = new JComboBox();
	/** target URL */
	private JTextField m_targetURL = new JTextField();
	/** target catalog */
	private JComboBox m_targetCatalog = new JComboBox();
	/** target schema */
	private JComboBox m_targetSchema = new JComboBox();
	/** target user */
	private JTextField m_targetUser = new JTextField();
	/** target password */
	private JTextField m_targetPassword = new JTextField();
	/** target system user */
	private JTextField m_targetSystemUser = new JTextField();
	/** target system password */
	private JTextField m_targetSystemPassword = new JTextField();
	/** target reset button */
	private JButton m_targetReset = new JButton();
	
	// mode
	/** upgrade mode */
	private JRadioButton m_modeUpgrade = new JRadioButton();
	/** transfer (copy/overwrite) mode */
	private JRadioButton m_modeTransfer = new JRadioButton();
	
	// options
	/** log level label */
	private JLabel m_labelLogLevel = new JLabel();
	/** log level */
	private JComboBox m_optionLogLevel = new JComboBox();
	/** attempt translations */
	private JCheckBox m_optionAttemptTranslations = new JCheckBox();
	/** preserve table IDs */
	private JCheckBox m_optionPreserveTableIDs = new JCheckBox();
	/** drop source */
	private JCheckBox m_optionDropSource = new JCheckBox();
	/** optimize database */
	private JCheckBox m_optionOptimizeDatabase = new JCheckBox();
	
	// do not interrupt warning
	/** do not interrupt warning label */
	private JLabel m_labelDoNotInterrupt = new JLabel();
	
	// buttons
	/** start button */
	private JButton m_buttonStart = new JButton();
	/** cancel button */
	private JButton m_buttonCancel = new JButton();
	/** close button */
	private JButton m_buttonClose = new JButton();
	/** trace button */
	private JButton m_trace = new JButton();
	/** warnings button */
	private JButton m_warnings = new JButton();
	/** errors button */
	private JButton m_errors = new JButton();
	
	// status
	/** current step */
	private JTextField m_step = new JTextField();
	/** current action */
	private JTextField m_action = new JTextField();
	/** current detail */
	private JTextField m_detail = new JTextField();

	// previous source parameters (for tracking changes)
	/** previous value of source database vendor */
	private String m_sourceVendorOld = new String();
	/** previous value of source host */
	private String m_sourceHostOld = new String();
	/** previous value of source port */
	private String m_sourcePortOld = new String();
	/** previous value of source database name */
	private String m_sourceNameOld = new String();
	/** previous value of source catalog */
	private String m_sourceCatalogOld = new String();
	/** previous value of source schema */
	private String m_sourceSchemaOld = new String();
	/** previous value of source user */
	private String m_sourceUserOld = new String();
	/** previous value of source password */
	private String m_sourcePasswordOld = new String();
	/** previous value of source system user */
	private String m_sourceSystemUserOld = new String();
	/** previous value of source system password */
	private String m_sourceSystemPasswordOld = new String();
	
	// previous target parameters (for tracking changes)
	/** previous value of target database vendor */
	private String m_targetVendorOld = new String();
	/** previous value of target host */
	private String m_targetHostOld = new String();
	/** previous value of target port */
	private String m_targetPortOld = new String();
	/** previous value of target database name */
	private String m_targetNameOld = new String();
	/** previous value of target catalog */
	private String m_targetCatalogOld = new String();
	/** previous value of target schema */
	private String m_targetSchemaOld = new String();
	/** previous value of target user */
	private String m_targetUserOld = new String();
	/** previous value of target password */
	private String m_targetPasswordOld = new String();
	/** previous value of target system user */
	private String m_targetSystemUserOld = new String();
	/** previous value of target system password */
	private String m_targetSystemPasswordOld = new String();

	// previous mode (for tracking changes)
	/** previous upgrade mode */
	private boolean m_upgradeModeOld = false;
	
	/** map to remember enabled/disabled status of gui components before migration process is started */
	private static HashMap<JComponent, Boolean> m_componentStatus = null;

	/** focus traversal policy for custom tab order */
	static CustomTabOrder m_tabOrder;

	// log snapshots for display
	/** trace log */
	private ArrayList<String> m_traceLog = null;
	/** warnings log */
	private ArrayList<String> m_warningLog = null;
	/** warnings counter */
	private long m_warningCounter = 0;
	/** errors log */
	private ArrayList<String> m_errorLog = null;
	/** errors counter */
	private long m_errorCounter = 0;


	// CONSTRUCTORS
	// ============

	/**
	 * graphical user interface for migration tool
	 */
	@SuppressWarnings("static-access")
	public Gui() {
		super();
		s_parameters = Parameters.getParameters();
		s_logger = MigrateLogger.getLogger();
		s_dbEngine = DBEngine.getDBEngine();
		
		// select text or graphics mode,
		// depending on command line argument and graphics environment
		setTextMode(true);
		if (s_parameters.isGuiMode())
			setTextMode(java.awt.GraphicsEnvironment.isHeadless());
	}

	
	// METHODS
	// =======

	/**
	 * thread-safe start of GUI
	 * @param migrator the calling migration process
	 */
    @SuppressWarnings("static-access")
	public void startGui(Migrate migrator) {
    	if (!isTextMode()) {
            //Schedule a job for the event-dispatching thread
            //creating and showing this application's GUI.
    		s_migrator = migrator;
    		s_logger.setGui(this);
    		javax.swing.SwingUtilities.invokeLater(new Runnable() {
    			public void run() {
    				createAndShowGUI();
    			}
    		});
    	} else {
    		// save parameters
    		// (because the config file may need to be updated with command line arguments)
    		s_parameters.saveToFile();
    		// run migrator in batch mode without GUI
    		migrator.startMigration();
    	}
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
	 */
	@SuppressWarnings("static-access")
	private void createAndShowGUI() {
		
		// do nothing if we are in text mode
		if (isTextMode())
			return;
		
		// un-initialize this gui
		m_isInitialized = false;
		
        //Create and set up the window.
        m_frame = new JFrame(s_logger.localizeMessage("guiWindowTitle"));
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // load icons
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(getImage("AD16.png"));
        images.add(getImage("AD32.png"));
        m_frame.setIconImages(images);        
        
        // create the menu
        m_menuFile.setText(s_logger.localizeMessage("guiMenuFile"));
        m_menuFile.setMnemonic(new Integer(s_logger.localizeMessage("guiMenuFileMnemonic")));
        m_menuFileSave.setText(s_logger.localizeMessage("guiMenuFileSave"));
        m_menuFileSave.setToolTipText(s_logger.localizeMessage("guiMenuFileSaveTip"));
        m_menuFileSave.setMnemonic(new Integer(s_logger.localizeMessage("guiMenuFileSaveMnemonic")));
        m_menuFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        m_menuFileSave.setIcon(new ImageIcon(getImage("Save16.png")));
        m_menuFileSave.addActionListener(this);
        m_menuFileClose.setText(s_logger.localizeMessage("guiMenuFileClose"));
        m_menuFileClose.setToolTipText(s_logger.localizeMessage("guiMenuFileCloseTip"));
        m_menuFileClose.setMnemonic(new Integer(s_logger.localizeMessage("guiMenuFileCloseMnemonic")));
        m_menuFileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        m_menuFileClose.setIcon(new ImageIcon(getImage("Logout16.png")));
        m_menuFileClose.addActionListener(this);
        m_menuFileExit.setText(s_logger.localizeMessage("guiMenuFileExit"));
        m_menuFileExit.setToolTipText(s_logger.localizeMessage("guiMenuFileExitTip"));
        m_menuFileExit.setMnemonic(new Integer(s_logger.localizeMessage("guiMenuFileExitMnemonic")));
        m_menuFileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        m_menuFileExit.setIcon(new ImageIcon(getImage("Cancel16.png")));
        m_menuFileExit.addActionListener(this);
        m_menuFile.add(m_menuFileSave);
        m_menuFile.addSeparator();
        m_menuFile.add(m_menuFileClose);
        m_menuFile.add(m_menuFileExit);
        m_menuHelp.setText(s_logger.localizeMessage("guiMenuHelp"));
        m_menuHelp.setMnemonic(new Integer(s_logger.localizeMessage("guiMenuHelpMnemonic")));
        m_menuHelpInfo.setText(s_logger.localizeMessage("guiMenuHelpInfo"));
        m_menuHelpInfo.setMnemonic(new Integer(s_logger.localizeMessage("guiMenuHelpInfoMnemonic")));
        m_menuHelpInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        m_menuHelpInfo.setIcon(new ImageIcon(getImage("Help16.png")));
        m_menuHelpInfo.addActionListener(this);
        m_menuHelpAbout.setText(s_logger.localizeMessage("guiMenuHelpAbout"));
        m_menuHelpAbout.setMnemonic(new Integer(s_logger.localizeMessage("guiMenuHelpAboutMnemonic")));
        m_menuHelpAbout.setIcon(new ImageIcon(getImage("About16.png")));
        m_menuHelpAbout.addActionListener(this);
        m_menuHelp.add(m_menuHelpInfo);
        m_menuHelp.addSeparator();
        m_menuHelp.add(m_menuHelpAbout);
        m_menuBar.add(m_menuFile);
        m_menuBar.add(m_menuHelp);
        m_frame.setJMenuBar(m_menuBar);
        
        // Set up the content pane.
        addComponentsToPane(m_frame.getContentPane());
        
        // fill objects with parameter values
		ArrayList<String> vendors = s_dbEngine.getVendorList();
		for (String vendorName : vendors) {
			m_sourceVendor.addItem(vendorName.toLowerCase());
			m_targetVendor.addItem(vendorName.toLowerCase());
		}
		resetSourceParameters();
		resetTargetParameters();
		resetMigrationMode();
		resetOptions();
		
		// mark this gui as initialized
		m_isInitialized = true;
		
        //Display the window.
        m_frame.pack();
        m_buttonStart.requestFocusInWindow();
        m_frame.setLocationRelativeTo(null);
        m_frame.setVisible(true);
    }
	
	/**
	 * add components and widgets to pane
	 * @param pane the frame's pane
	 */
	private void addComponentsToPane (Container pane) {
		Locale locale = Locale.getDefault();
		pane.setComponentOrientation(ComponentOrientation.getOrientation(locale));
		pane.setLayout(new GridBagLayout());

    	// panel for parameters
		JPanel panelParameters = new JPanel();
		panelParameters.setComponentOrientation(ComponentOrientation.getOrientation(locale));
		panelParameters.setLayout(new GridBagLayout());
		panelParameters.setBorder(BorderFactory.createTitledBorder(s_logger.localizeMessage("guiParametersTitle")));
		panelParameters.setToolTipText(s_logger.localizeMessage("guiParametersTip"));
		pane.add(panelParameters, getParametersGroupConstraints(0,0));
		
    	JLabel label = new JLabel(s_logger.localizeMessage("guiSourceTitle"));
    	label.setToolTipText(s_logger.localizeMessage("guiSourceTip"));
    	panelParameters.add(label, getTitle1Constraints(1,0));

    	label = new JLabel(s_logger.localizeMessage("guiTargetTitle"));
    	label.setToolTipText(s_logger.localizeMessage("guiTargetTip"));
    	panelParameters.add(label, getTitle1Constraints(2,0));
    	
    	label = new JLabel(s_logger.localizeMessage("guiSourceDescription"));
    	label.setToolTipText(s_logger.localizeMessage("guiSourceTip"));
    	panelParameters.add(label, getTitle2Constraints(1,1));

    	label = new JLabel(s_logger.localizeMessage("guiTargetDescription"));
    	label.setToolTipText(s_logger.localizeMessage("guiTargetTip"));
    	panelParameters.add(label, getTitle2Constraints(2,1));

    	label = new JLabel(s_logger.localizeMessage("guiVersion"));
    	label.setToolTipText(s_logger.localizeMessage("guiVersionTip"));
    	panelParameters.add(label, getLabelConstraints(0,2));
    	m_sourceVersion.setEnabled(false);
    	m_sourceVersion.setEditable(false);
    	m_sourceVersion.setDisabledTextColor(m_sourceHost.getForeground());
    	panelParameters.add(m_sourceVersion, getFieldConstraints(1,2));
    	m_targetVersion.setEnabled(false);
    	m_targetVersion.setEditable(false);
    	m_targetVersion.setDisabledTextColor(m_targetHost.getForeground());
    	panelParameters.add(m_targetVersion, getFieldConstraints(2,2));
    	
    	label = new JLabel(s_logger.localizeMessage("guiVendor"));
    	label.setToolTipText(s_logger.localizeMessage("guiVendorTip"));
    	panelParameters.add(label, getLabelConstraints(0,3));
    	m_sourceVendor.addActionListener(this);
    	panelParameters.add(m_sourceVendor, getFieldConstraints(1,3));
    	m_targetVendor.addActionListener(this);
    	panelParameters.add(m_targetVendor, getFieldConstraints(2,3));

    	label = new JLabel(s_logger.localizeMessage("guiHost"));
    	label.setToolTipText(s_logger.localizeMessage("guiHostTip"));
    	panelParameters.add(label, getLabelConstraints(0,4));
    	m_sourceHost.addActionListener(this);
    	m_sourceHost.addFocusListener(this);
    	panelParameters.add(m_sourceHost, getFieldConstraints(1,4));
    	m_targetHost.addActionListener(this);
    	m_targetHost.addFocusListener(this);
    	panelParameters.add(m_targetHost, getFieldConstraints(2,4));

    	label = new JLabel(s_logger.localizeMessage("guiPort"));
    	label.setToolTipText(s_logger.localizeMessage("guiPortTip"));
    	panelParameters.add(label, getLabelConstraints(0,5));
    	m_sourcePort.addActionListener(this);
    	m_sourcePort.addFocusListener(this);
    	panelParameters.add(m_sourcePort, getFieldConstraints(1,5));
    	m_targetPort.addActionListener(this);
    	m_targetPort.addFocusListener(this);
    	panelParameters.add(m_targetPort, getFieldConstraints(2,5));

    	label = new JLabel(s_logger.localizeMessage("guiUser"));
    	label.setToolTipText(s_logger.localizeMessage("guiUserTip"));
    	panelParameters.add(label, getLabelConstraints(0,6));
    	m_sourceUser.addActionListener(this);
    	m_sourceUser.addFocusListener(this);
    	panelParameters.add(m_sourceUser, getFieldConstraints(1,6));
    	m_targetUser.addActionListener(this);
    	m_targetUser.addFocusListener(this);
    	panelParameters.add(m_targetUser, getFieldConstraints(2,6));

    	label = new JLabel(s_logger.localizeMessage("guiPassword"));
    	label.setToolTipText(s_logger.localizeMessage("guiPasswordTip"));
    	panelParameters.add(label, getLabelConstraints(0,7));
    	m_sourcePassword.addActionListener(this);
    	m_sourcePassword.addFocusListener(this);
    	panelParameters.add(m_sourcePassword, getFieldConstraints(1,7));
    	m_targetPassword.addActionListener(this);
    	m_targetPassword.addFocusListener(this);
    	panelParameters.add(m_targetPassword, getFieldConstraints(2,7));

    	label = new JLabel(s_logger.localizeMessage("guiSystemUser"));
    	label.setToolTipText(s_logger.localizeMessage("guiSystemUserTip"));
    	panelParameters.add(label, getLabelConstraints(0,8));
    	m_sourceSystemUser.addActionListener(this);
    	m_sourceSystemUser.addFocusListener(this);
    	panelParameters.add(m_sourceSystemUser, getFieldConstraints(1,8));
    	m_targetSystemUser.addActionListener(this);
    	m_targetSystemUser.addFocusListener(this);
    	panelParameters.add(m_targetSystemUser, getFieldConstraints(2,8));

    	label = new JLabel(s_logger.localizeMessage("guiSystemPassword"));
    	label.setToolTipText(s_logger.localizeMessage("guiSystemPasswordTip"));
    	panelParameters.add(label, getLabelConstraints(0,9));
    	m_sourceSystemPassword.addActionListener(this);
    	m_sourceSystemPassword.addFocusListener(this);
    	panelParameters.add(m_sourceSystemPassword, getFieldConstraints(1,9));
    	m_targetSystemPassword.addActionListener(this);
    	m_targetSystemPassword.addFocusListener(this);
    	panelParameters.add(m_targetSystemPassword, getFieldConstraints(2,9));

    	label = new JLabel(s_logger.localizeMessage("guiName"));
    	label.setToolTipText(s_logger.localizeMessage("guiNameTip"));
    	panelParameters.add(label, getLabelConstraints(0,10));
    	m_sourceName.setEditable(true);
    	m_sourceName.addActionListener(this);
    	panelParameters.add(m_sourceName, getFieldConstraints(1,10));
    	m_targetName.setEditable(true);
    	m_targetName.addActionListener(this);
    	panelParameters.add(m_targetName, getFieldConstraints(2,10));

    	label = new JLabel(s_logger.localizeMessage("guiUrl"));
    	label.setToolTipText(s_logger.localizeMessage("guiUrlTip"));
    	panelParameters.add(label, getLabelConstraints(0,11));
    	m_sourceURL.setEnabled(false);
    	m_sourceURL.setEditable(false);
    	m_sourceURL.setDisabledTextColor(m_sourceHost.getForeground());
    	panelParameters.add(m_sourceURL, getFieldConstraints(1,11));
    	m_targetURL.setEnabled(false);
    	m_targetURL.setEditable(false);
    	m_targetURL.setDisabledTextColor(m_targetHost.getForeground());
    	panelParameters.add(m_targetURL, getFieldConstraints(2,11));

    	label = new JLabel(s_logger.localizeMessage("guiCatalog"));
    	label.setToolTipText(s_logger.localizeMessage("guiCatalogTip"));
    	panelParameters.add(label, getLabelConstraints(0,12));
    	m_sourceCatalog.addActionListener(this);
    	panelParameters.add(m_sourceCatalog, getFieldConstraints(1,12));
    	m_targetCatalog.setEditable(true);
    	m_targetCatalog.addActionListener(this);
    	panelParameters.add(m_targetCatalog, getFieldConstraints(2,12));

    	label = new JLabel(s_logger.localizeMessage("guiSchema"));
    	label.setToolTipText(s_logger.localizeMessage("guiSchemaTip"));
    	panelParameters.add(label, getLabelConstraints(0,13));
    	m_sourceSchema.addActionListener(this);
    	panelParameters.add(m_sourceSchema, getFieldConstraints(1,13));
    	m_targetSchema.setEditable(true);
    	m_targetSchema.addActionListener(this);
    	panelParameters.add(m_targetSchema, getFieldConstraints(2,13));
    	
    	m_sourceReset = new JButton(s_logger.localizeMessage("guiReset"));
    	m_sourceReset.setMnemonic(new Integer(s_logger.localizeMessage("guiSourceResetMnemonic")));
    	m_sourceReset.addActionListener(this);
    	m_sourceReset.setToolTipText(s_logger.localizeMessage("guiResetTip"));
    	m_sourceReset.setIcon(new ImageIcon(getImage("Reset16.png")));
    	panelParameters.add(m_sourceReset, getFieldConstraints(1,14));
    	m_targetReset = new JButton(s_logger.localizeMessage("guiReset"));
    	m_targetReset.setMnemonic(new Integer(s_logger.localizeMessage("guiTargetResetMnemonic")));
    	m_targetReset.addActionListener(this);
    	m_targetReset.setToolTipText(s_logger.localizeMessage("guiResetTip"));
    	m_targetReset.setIcon(new ImageIcon(getImage("Reset16.png")));
    	panelParameters.add(m_targetReset, getFieldConstraints(2,14));

    	
    	// panel for mode
		JPanel panelMode = new JPanel();
		panelMode.setComponentOrientation(ComponentOrientation.getOrientation(locale));
		panelMode.setLayout(new GridBagLayout());
		panelMode.setBorder(BorderFactory.createTitledBorder(s_logger.localizeMessage("guiModeTitle")));
		panelMode.setToolTipText(s_logger.localizeMessage("guiModeTip"));
		pane.add(panelMode, getOptionGroupConstraints(1,0));
		
		m_modeUpgrade = new JRadioButton(s_logger.localizeMessage("guiModeUpgrade"));
		m_modeUpgrade.setMnemonic(new Integer(s_logger.localizeMessage("guiModeUpgradeMnemonic")));
		m_modeUpgrade.setToolTipText(s_logger.localizeMessage("guiModeUpgradeTip"));
		m_modeUpgrade.addActionListener(this);
		m_modeTransfer = new JRadioButton(s_logger.localizeMessage("guiModeTransfer"));
		m_modeTransfer.setMnemonic(new Integer(s_logger.localizeMessage("guiModeTransferMnemonic")));
		m_modeTransfer.setToolTipText(s_logger.localizeMessage("guiModeTransferTip"));
		m_modeTransfer.addActionListener(this);
		ButtonGroup modeGroup = new ButtonGroup();
		modeGroup.add(m_modeUpgrade);
		modeGroup.add(m_modeTransfer);
		panelMode.add(m_modeUpgrade, getFieldConstraints(0, 0));
		panelMode.add(m_modeTransfer, getFieldConstraints(1, 0));
		
		
		// panel for options
		JPanel panelOptions = new JPanel();
		panelOptions.setComponentOrientation(ComponentOrientation.getOrientation(locale));
		panelOptions.setLayout(new GridBagLayout());
		panelOptions.setBorder(BorderFactory.createTitledBorder(s_logger.localizeMessage("guiOptionsTitle")));
		panelOptions.setToolTipText(s_logger.localizeMessage("guiOptionsTip"));
		pane.add(panelOptions, getOptionGroupConstraints(1,1));
		
		JPanel panelLogLevel = new JPanel();
		panelLogLevel.setComponentOrientation(ComponentOrientation.getOrientation(locale));
		panelLogLevel.setLayout(new GridBagLayout());
		m_optionLogLevel = new JComboBox();
		m_optionLogLevel.setToolTipText(s_logger.localizeMessage("guiOptionLogLevelTip"));
		m_labelLogLevel = new JLabel(s_logger.localizeMessage("guiOptionLogLevel"));
		m_labelLogLevel.setLabelFor(m_optionLogLevel);
		m_labelLogLevel.setToolTipText(s_logger.localizeMessage("guiOptionLogLevelTip"));
		m_labelLogLevel.setDisplayedMnemonic(new Integer(s_logger.localizeMessage("guiOptionLogLevelMnemonic")));
		panelLogLevel.add(m_labelLogLevel, getFieldConstraints(0, 0));
		panelLogLevel.add(m_optionLogLevel, getFieldConstraints(1, 0));
		
		m_optionAttemptTranslations = new JCheckBox(s_logger.localizeMessage("guiOptionAttemptTranslations"));
		m_optionAttemptTranslations.setMnemonic(new Integer(s_logger.localizeMessage("guiOptionAttemptTranslationsMnemonic")));
		m_optionAttemptTranslations.setToolTipText(s_logger.localizeMessage("guiOptionAttemptTranslationsTip"));
		
		m_optionPreserveTableIDs = new JCheckBox(s_logger.localizeMessage("guiOptionPreserveTableIDs"));
		m_optionPreserveTableIDs.setMnemonic(new Integer(s_logger.localizeMessage("guiOptionPreserveTableIDsMnemonic")));
		m_optionPreserveTableIDs.setToolTipText(s_logger.localizeMessage("guiOptionPreserveTableIDsTip"));
		
		m_optionDropSource = new JCheckBox(s_logger.localizeMessage("guiOptionDropSource"));
		m_optionDropSource.setMnemonic(new Integer(s_logger.localizeMessage("guiOptionDropSourceMnemonic")));
		m_optionDropSource.setToolTipText(s_logger.localizeMessage("guiOptionDropSourceTip"));
		
		m_optionOptimizeDatabase = new JCheckBox(s_logger.localizeMessage("guiOptionOptimizeDatabase"));
		m_optionOptimizeDatabase.setMnemonic(new Integer(s_logger.localizeMessage("guiOptionOptimizeDatabaseMnemonic")));
		m_optionOptimizeDatabase.setToolTipText(s_logger.localizeMessage("guiOptionOptimizeDatabaseTip"));
		
		panelOptions.add(panelLogLevel, getFieldConstraints(0, 0));
		panelOptions.add(m_optionAttemptTranslations, getFieldConstraints(0, 1));
		panelOptions.add(m_optionPreserveTableIDs, getFieldConstraints(0, 2));
		panelOptions.add(m_optionDropSource, getFieldConstraints(0, 3));
		panelOptions.add(m_optionOptimizeDatabase, getFieldConstraints(0, 4));
		
		
		// label DO NOT INTERRUPT warning
		JPanel panelDoNotInterrupt = new JPanel();
		panelDoNotInterrupt.setComponentOrientation(ComponentOrientation.getOrientation(locale));
		panelDoNotInterrupt.setLayout(new GridBagLayout());
		pane.add(panelDoNotInterrupt, getDoNotInterruptConstraints(1,2));
		m_labelDoNotInterrupt = new JLabel(s_logger.localizeMessage("migrateDoNotInterrupt"));
		m_labelDoNotInterrupt.setForeground(Color.RED);
		m_labelDoNotInterrupt.setVisible(false);
		panelDoNotInterrupt.add(m_labelDoNotInterrupt, getFieldConstraints(0,0));
		
		
		// panel for start button 
		JPanel panelStartButton = new JPanel();
		panelStartButton.setComponentOrientation(ComponentOrientation.getOrientation(locale));
		panelStartButton.setLayout(new GridBagLayout());
		pane.add(panelStartButton, getButtonGroupConstraints(1,3));
		m_buttonStart = new JButton(s_logger.localizeMessage("guiButtonStart"));
		m_buttonStart.setMnemonic(new Integer(s_logger.localizeMessage("guiButtonStartMnemonic")));
		m_buttonStart.setToolTipText(s_logger.localizeMessage("guiButtonStartTip"));
		m_buttonStart.setIcon(new ImageIcon(getImage("Process16.png")));
		m_buttonStart.addActionListener(this);
		panelStartButton.add(m_buttonStart, getFieldConstraints(0,0));
		
		
		// panel for status indication
		JPanel panelStatus = new JPanel();
		panelStatus.setComponentOrientation(ComponentOrientation.getOrientation(locale));
		panelStatus.setLayout(new GridBagLayout());
		panelStatus.setBorder(BorderFactory.createTitledBorder(s_logger.localizeMessage("guiStatusTitle")));
		panelStatus.setToolTipText(s_logger.localizeMessage("guiStatusTip"));
		pane.add(panelStatus, getLogGroupConstraints(0,4));
		
    	label = new JLabel(s_logger.localizeMessage("guiStep"));
    	panelStatus.add(label, getLabelConstraints(0,0));
    	m_step.setEditable(false);
    	m_step.setEnabled(false);
    	m_step.setDisabledTextColor(m_sourceHost.getForeground());
    	panelStatus.add(m_step, getStatusFieldConstraints(1,0));

    	label = new JLabel(s_logger.localizeMessage("guiAction"));
    	panelStatus.add(label, getLabelConstraints(0,1));
    	m_action.setEditable(false);
    	m_action.setEnabled(false);
    	m_action.setDisabledTextColor(m_sourceHost.getForeground());
    	panelStatus.add(m_action, getStatusFieldConstraints(1,1));

    	label = new JLabel(s_logger.localizeMessage("guiDetail"));
    	panelStatus.add(label, getLabelConstraints(0,2));
    	m_detail.setEditable(false);
    	m_detail.setEnabled(false);
    	m_detail.setDisabledTextColor(m_sourceHost.getForeground());
    	panelStatus.add(m_detail, getStatusFieldConstraints(1,2));

		
		// panel for view buttons
		JPanel panelViewButtons = new JPanel();
		panelViewButtons.setComponentOrientation(ComponentOrientation.getOrientation(locale));
		panelViewButtons.setLayout(new GridBagLayout());
		pane.add(panelViewButtons, getViewGroupConstraints(0,5));
		m_trace = new JButton(s_logger.localizeMessage("guiButtonViewTrace"));
		m_trace.setMnemonic(new Integer(s_logger.localizeMessage("guiButtonViewTraceMnemonic")));
		m_trace.setToolTipText(s_logger.localizeMessage("guiButtonViewTraceTip"));
		m_trace.setIcon(new ImageIcon(getImage("Zoom16.png")));
		m_trace.setEnabled(false);
		m_trace.addActionListener(this);
		m_warnings = new JButton(s_logger.localizeMessage("guiButtonViewWarnings"));
		m_warnings.setMnemonic(new Integer(s_logger.localizeMessage("guiButtonViewWarningsMnemonic")));
		m_warnings.setToolTipText(s_logger.localizeMessage("guiButtonViewWarningsTip"));
		m_warnings.setIcon(new ImageIcon(getImage("Zoom16.png")));
		m_warnings.setEnabled(false);
		m_warnings.addActionListener(this);
		m_errors = new JButton(s_logger.localizeMessage("guiButtonViewErrors"));
		m_errors.setMnemonic(new Integer(s_logger.localizeMessage("guiButtonViewErrorsMnemonic")));
		m_errors.setToolTipText(s_logger.localizeMessage("guiButtonViewErrorsTip"));
		m_errors.setIcon(new ImageIcon(getImage("Zoom16.png")));
		m_errors.setEnabled(false);
		m_errors.addActionListener(this);
		panelViewButtons.add(m_trace, getFieldConstraints(0,0));
		panelViewButtons.add(m_warnings, getFieldConstraints(1,0));
		panelViewButtons.add(m_errors, getFieldConstraints(2,0));
		
		// panel for close buttons
		JPanel panelCloseButtons = new JPanel();
		panelCloseButtons.setComponentOrientation(ComponentOrientation.getOrientation(locale));
		panelCloseButtons.setLayout(new GridBagLayout());
		pane.add(panelCloseButtons, getButtonGroupConstraints(1,5));
		m_buttonCancel = new JButton(s_logger.localizeMessage("guiButtonCancel"));
		m_buttonCancel.setMnemonic(new Integer(s_logger.localizeMessage("guiButtonCancelMnemonic")));
		m_buttonCancel.setToolTipText(s_logger.localizeMessage("guiButtonCancelTip"));
		m_buttonCancel.setIcon(new ImageIcon(getImage("Cancel16.png")));
		m_buttonCancel.addActionListener(this);
		m_buttonClose = new JButton(s_logger.localizeMessage("guiButtonClose"));
		m_buttonClose.setMnemonic(new Integer(s_logger.localizeMessage("guiButtonCloseMnemonic")));
		m_buttonClose.setToolTipText(s_logger.localizeMessage("guiButtonCloseTip"));
		m_buttonClose.setIcon(new ImageIcon(getImage("Logout16.png")));
		m_buttonClose.addActionListener(this);
		panelCloseButtons.add(m_buttonCancel, getFieldConstraints(0,0));
		panelCloseButtons.add(m_buttonClose, getFieldConstraints(1,0));
		
		
		// customize tab order
		Vector<Component> order = new Vector<Component>();
		order.add(m_sourceVersion);
		order.add(m_sourceVendor);
		order.add(m_sourceHost);
		order.add(m_sourcePort);
		order.add(m_sourceUser);
		order.add(m_sourcePassword);
		order.add(m_sourceSystemUser);
		order.add(m_sourceSystemPassword);
		order.add(m_sourceName);
		order.add(m_sourceURL);
		order.add(m_sourceCatalog);
		order.add(m_sourceSchema);
		order.add(m_sourceReset);
		order.add(m_targetVersion);
		order.add(m_targetVendor);
		order.add(m_targetHost);
		order.add(m_targetPort);
		order.add(m_targetUser);
		order.add(m_targetPassword);
		order.add(m_targetSystemUser);
		order.add(m_targetSystemPassword);
		order.add(m_targetName);
		order.add(m_targetURL);
		order.add(m_targetCatalog);
		order.add(m_targetSchema);
		order.add(m_targetReset);
		order.add(m_modeUpgrade);
		order.add(m_modeTransfer);
		order.add(m_optionLogLevel);
		order.add(m_optionAttemptTranslations);
		order.add(m_optionPreserveTableIDs);
		order.add(m_optionDropSource);
		order.add(m_optionOptimizeDatabase);
		order.add(m_buttonStart);
		order.add(m_step);
		order.add(m_action);
		order.add(m_detail);
		order.add(m_trace);
		order.add(m_warnings);
		order.add(m_errors);
		order.add(m_buttonCancel);
		order.add(m_buttonClose);
		m_tabOrder = new CustomTabOrder(order);
		pane.setFocusCycleRoot(true);
		pane.setFocusTraversalPolicy(m_tabOrder);
	}
	
	/**
	 * create a GridBagConstraint object from parameters
	 * @param gridx column of component (first column is 0)
	 * @param gridy row of component (first row is 0)
	 * @param gridwidth how many columns the component spans
	 * @param gridheight how many rows the component spans
	 * @param fill how to resize component to fill display area
	 * @param ipadx pixels to pad left and right inside component
	 * @param ipady pixels to add top and bottom inside component
	 * @param insettop space between top edge of component and display area
	 * @param insetleft space between left edge of component and display area
	 * @param insetbottom space between bottom edge of component and display area
	 * @param insetright space between right edge of component and display area
	 * @param anchor where to align component within the display area
	 * @param weightx weight for determining how to distribute extra horizontal space
	 * @param weighty weight for determining how to distribute extra vertical space
	 * @return GridBagConstraint object
	 */
	private GridBagConstraints getGBC(int gridx, int gridy, int gridwidth, int gridheight, int fill, int ipadx, int ipady, int insettop, int insetleft, int insetbottom, int insetright, int anchor, double weightx, double weighty) {
    	GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.fill = fill;
		gbc.ipadx = ipadx;
		gbc.ipady = ipady;
		gbc.insets = new Insets(insettop, insetleft, insetbottom, insetright);
		gbc.anchor = anchor;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		return gbc;
	}
	
	/**
	 * gets standard constraints used by the parameters group
	 * @param gridx column of component (first column is 0)
	 * @param gridy row of component (first row is 0)
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getParametersGroupConstraints(int gridx, int gridy) {
		return getGBC(gridx, gridy, 1, 4, GridBagConstraints.BOTH, 0, 0, 2, 2, 2, 2, GridBagConstraints.CENTER, 0, 0);
	}

	/**
	 * gets standard constraints used by the log group
	 * @param gridx column of component (first column is 0)
	 * @param gridy row of component (first row is 0)
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getLogGroupConstraints(int gridx, int gridy) {
		return getGBC(gridx, gridy, 2, 1, GridBagConstraints.HORIZONTAL, 0, 0, 2, 2, 2, 2, GridBagConstraints.LINE_START, 0, 0);
	}

	/**
	 * gets standard constraints used by option groups
	 * @param gridx column of component (first column is 0)
	 * @param gridy row of component (first row is 0)
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getOptionGroupConstraints(int gridx, int gridy) {
		return getGBC(gridx, gridy, 1, 1, GridBagConstraints.HORIZONTAL, 0, 0, 2, 2, 2, 2, GridBagConstraints.PAGE_START, 0, 0);
	}

	/**
	 * gets standard constraints used by the button groups
	 * @param gridx column of component (first column is 0)
	 * @param gridy row of component (first row is 0)
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getButtonGroupConstraints(int gridx, int gridy) {
		return getGBC(gridx, gridy, 1, 1, GridBagConstraints.NONE, 0, 0, 2, 2, 2, 2, GridBagConstraints.LAST_LINE_END, 0, 0);
	}

	/**
	 * gets standard constraints used by the view button group
	 * @param gridx column of component (first column is 0)
	 * @param gridy row of component (first row is 0)
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getViewGroupConstraints(int gridx, int gridy) {
		return getGBC(gridx, gridy, 1, 1, GridBagConstraints.NONE, 0, 0, 2, 2, 2, 2, GridBagConstraints.LAST_LINE_START, 0, 0);
	}

	/**
	 * gets standard constraints used by title labels in first line
	 * @param gridx column of component (first column is 0)
	 * @param gridy row of component (first row is 0)
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getTitle1Constraints(int gridx, int gridy) {
		return getGBC(gridx, gridy, 1, 1, GridBagConstraints.NONE, 0, 0, 2, 2, 0, 2, GridBagConstraints.CENTER, 0, 0);
	}

	/**
	 * gets standard constraints used by title labels in second line
	 * @param gridx column of component (first column is 0)
	 * @param gridy row of component (first row is 0)
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getTitle2Constraints(int gridx, int gridy) {
		return getGBC(gridx, gridy, 1, 1, GridBagConstraints.NONE, 0, 0, 0, 2, 2, 2, GridBagConstraints.CENTER, 0, 0);
	}

	/**
	 * gets standard constraints used by labels
	 * @param gridx column of component (first column is 0)
	 * @param gridy row of component (first row is 0)
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getLabelConstraints(int gridx, int gridy) {
		return getGBC(gridx, gridy, 1, 1, GridBagConstraints.NONE, 0, 0, 2, 2, 2, 2, GridBagConstraints.LINE_END, 0, 0);
	}

	/**
	 * gets standard constraints used by fields
	 * @param gridx column of component (first column is 0)
	 * @param gridy row of component (first row is 0)
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getFieldConstraints(int gridx, int gridy) {
		return getGBC(gridx, gridy, 1, 1, GridBagConstraints.HORIZONTAL, 0, 0, 2, 2, 2, 2, GridBagConstraints.LINE_START, 0, 0);
	}

	/**
	 * gets standard constraints used by status display fields
	 * @param gridx column of component (first column is 0)
	 * @param gridy row of component (first row is 0)
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getStatusFieldConstraints(int gridx, int gridy) {
		return getGBC(gridx, gridy, 1, 1, GridBagConstraints.HORIZONTAL, 0, 0, 2, 2, 2, 2, GridBagConstraints.LINE_START, 1, 0);
	}

	/**
	 * gets standard constraints used by DO NOT INTERRUPT warning
	 * @param gridx column of component (first column is 0)
	 * @param gridy row of component (first row is 0)
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getDoNotInterruptConstraints(int gridx, int gridy) {
		return getGBC(gridx, gridy, 1, 1, GridBagConstraints.BOTH, 0, 0, 2, 2, 2, 2, GridBagConstraints.CENTER, 0.5, 0.5);
	}

    /**
     * load image from file as resource
     * @param imageName name of the image
     * @return the image
     */
    private Image getImage(String imageName) {
    	
    	if (imageName==null || imageName.length()==0)
    		return null;
    	
        java.net.URL imgURL = getClass().getResource(new StringBuffer("images/").append(imageName).toString());
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            return null;
        }
        
    }

	/**
	 * inline class for focus traversal policy
	 * for customized tab order
	 * @author Stefan Christians
	 */
	public static class CustomTabOrder extends FocusTraversalPolicy {
		Vector<Component> order;

		public CustomTabOrder(Vector<Component> order) {
			this.order = new Vector<Component>(order.size());
			this.order.addAll(order);
		}
		
		public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
			// get index of component losing focus
			int idx = order.indexOf(aComponent);
			while (idx == -1) {
				// if it is not a registered component, it might be the (child) textbox of a
				// (parent) combobox or something like that, so try to get the parent
				aComponent = aComponent.getParent();
				idx = order.indexOf(aComponent);
			}
			// increment to next component
			idx++;
			// if we are past the last component, start again with the first
			if (idx >= order.size())
				idx = 0;
			// skip any disabled components
			while (!order.get(idx).isEnabled()) {
				idx++;
				if (idx >= order.size())
					idx = 0;
			}
			return order.get(idx);
		}

		public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
			// get index of component losing focus
			int idx = order.indexOf(aComponent);
			while (idx == -1) {
				// if it is not a registered component, it might be the (child) textbox of a
				// (parent) combobox or something like that, so try to get the parent
				aComponent = aComponent.getParent();
				idx = order.indexOf(aComponent);
			}
			// decrement to previous component
			idx--;
			// if we are past the first component, start again with the last
			if (idx < 0)
				idx = order.size()-1;
			// skip any disabled components
			while (!order.get(idx).isEnabled()) {
				idx--;
				if (idx < 0)
					idx = order.size()-1;
			}
			return order.get(idx);
		}

		public Component getDefaultComponent(Container focusCycleRoot) {
			return order.get(33); // start button
		}

		public Component getLastComponent(Container focusCycleRoot) {
			return order.lastElement();
		}

		public Component getFirstComponent(Container focusCycleRoot) {
			return order.get(0);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		
		// no need to do anything if we are not initialized yet
		if (!m_isInitialized)
			return;
		
		// no need to do anything if we are alrady busy
		if (m_isBusy)
			return;
		
		m_isBusy = true;
		
		Object eventObj = e.getSource();
		
		// only process if a worthy action triggered this event
		if (valueChangeTrigger(e)) {
			if (eventObj == m_sourceVendor) {
				if (valueHasChanged(e, m_sourceVendorOld)) {
					String newValue = getNewValue(e);
					
					// source vendor
					// -------------
					resetVendor(s_isSource, newValue);
					resetPort(s_isSource, "");
					resetSystemUserStatus(s_isSource);
					resetName(s_isSource, "");
					resetURL(s_isSource, "");
					resetCatalog(s_isSource, "");
					resetSchema(s_isSource, "");
					resetVersion(s_isSource);
					
				}
			} else if (eventObj == m_sourceHost) {
				if (valueHasChanged(e, m_sourceHostOld)) {
					String newValue = getNewValue(e);
					
					// source host
					// -----------
					resetHost(s_isSource, newValue);
					resetName(s_isSource, "");
					resetURL(s_isSource, "");
					resetCatalog(s_isSource, "");
					resetSchema(s_isSource, "");
					resetVersion(s_isSource);
					
				}
			} else if (eventObj == m_sourcePort) {
				if (valueHasChanged(e, m_sourcePortOld)) {
					String newValue = getNewValue(e);
					
					// source port
					// -----------
					resetPort(s_isSource, newValue);
					resetName(s_isSource, "");
					resetURL(s_isSource, "");
					resetCatalog(s_isSource, "");
					resetSchema(s_isSource, "");
					resetVersion(s_isSource);
					
				}
			} else if (eventObj == m_sourceUser) {
				if (valueHasChanged(e, m_sourceUserOld)) {
					String newValue = getNewValue(e);
					
					// source user
					// -----------
					resetUser(s_isSource, newValue);
					resetName(s_isSource, "");
					resetURL(s_isSource, "");
					resetCatalog(s_isSource, "");
					resetSchema(s_isSource, "");
					resetVersion(s_isSource);
					
				}
			} else if (eventObj == m_sourcePassword) {
				if (valueHasChanged(e, m_sourcePasswordOld)) {
					String newValue = getNewValue(e);
					
					// source password
					// ---------------
					resetPassword(s_isSource, newValue);
					resetName(s_isSource, "");
					resetURL(s_isSource, "");
					resetCatalog(s_isSource, "");
					resetSchema(s_isSource, "");
					resetVersion(s_isSource);
					
				}
			} else if (eventObj == m_sourceSystemUser) {
				if (valueHasChanged(e, m_sourceSystemUserOld)) {
					String newValue = getNewValue(e);
					
					// source systemUser
					// -----------------
					resetSystemUser(s_isSource, newValue);
					
				}
			} else if (eventObj == m_sourceSystemPassword) {
				if (valueHasChanged(e, m_sourceSystemPasswordOld)) {
					String newValue = getNewValue(e);
					
					// source SystemPassword
					// ---------------------
					resetSystemPassword(s_isSource, newValue);
					
				}
			} else if (eventObj == m_sourceName) {
				if (valueHasChanged(e, m_sourceNameOld)) {
					String newValue = getNewValue(e);

					// source name
					// -----------
					resetName(s_isSource, newValue);
					resetURL(s_isSource, "");
					resetCatalog(s_isSource, "");
					resetSchema(s_isSource, "");
					resetVersion(s_isSource);
					
				}
			} else if (eventObj == m_sourceCatalog) {
				if (valueHasChanged(e, m_sourceCatalogOld)) {
					String newValue = getNewValue(e);
					
					// source catalog
					// --------------
					resetCatalog(s_isSource, newValue);
					resetSchema(s_isSource, "");
					resetVersion(s_isSource);
					
				}
			} else if (eventObj == m_sourceSchema) {
				if (valueHasChanged(e, m_sourceSchemaOld)) {
					String newValue = getNewValue(e);
					
					// source schema
					// -------------
					resetSchema(s_isSource, newValue);
					resetVersion(s_isSource);
					
				}
			} else if (eventObj == m_sourceReset) {
				
				// reset source
				// ------------
				m_isInitialized = false;
				resetSourceParameters();
				m_isInitialized = true;
				
			} else if (eventObj == m_targetVendor) {
				if (valueHasChanged(e, m_targetVendorOld)) {
					String newValue = getNewValue(e);
					
					// target vendor
					// -------------
					resetVendor(s_isTarget, newValue);
					resetPort(s_isTarget, "");
					resetSystemUserStatus(s_isTarget);
					resetName(s_isTarget, "");
					resetURL(s_isTarget, "");
					resetCatalog(s_isTarget, "");
					resetSchema(s_isTarget, "");
					resetVersion(s_isTarget);
					
				}
			} else if (eventObj == m_targetHost) {
				if (valueHasChanged(e, m_targetHostOld)) {
					String newValue = getNewValue(e);
					
					// target host
					// -----------
					resetHost(s_isTarget, newValue);
					resetName(s_isTarget, "");
					resetURL(s_isTarget, "");
					resetCatalog(s_isTarget, "");
					resetSchema(s_isTarget, "");
					resetVersion(s_isTarget);
					
				}
			} else if (eventObj == m_targetPort) {
				if (valueHasChanged(e, m_targetPortOld)) {
					String newValue = getNewValue(e);
					
					// target port
					// -----------
					resetPort(s_isTarget, newValue);
					resetName(s_isTarget, "");
					resetURL(s_isTarget, "");
					resetCatalog(s_isTarget, "");
					resetSchema(s_isTarget, "");
					resetVersion(s_isTarget);
					
				}
			} else if (eventObj == m_targetUser) {
				if (valueHasChanged(e, m_targetUserOld)) {
					String newValue = getNewValue(e);
					
					// target user
					// -----------
					resetUser(s_isTarget, newValue);
					resetName(s_isTarget, "");
					resetURL(s_isTarget, "");
					resetCatalog(s_isTarget, "");
					resetSchema(s_isTarget, "");
					resetVersion(s_isTarget);
					
				}
			} else if (eventObj == m_targetPassword) {
				if (valueHasChanged(e, m_targetPasswordOld)) {
					String newValue = getNewValue(e);
					
					// target password
					// ---------------
					resetPassword(s_isTarget, newValue);
					resetName(s_isTarget, "");
					resetURL(s_isTarget, "");
					resetCatalog(s_isTarget, "");
					resetSchema(s_isTarget, "");
					resetVersion(s_isTarget);
					
				}
			} else if (eventObj == m_targetSystemUser) {
				if (valueHasChanged(e, m_targetSystemUserOld)) {
					String newValue = getNewValue(e);
					
					// target systemUser
					// -----------------
					resetSystemUser(s_isTarget, newValue);
					resetName(s_isTarget, "");
					resetURL(s_isTarget, "");
					resetCatalog(s_isTarget, "");
					resetSchema(s_isTarget, "");
					resetVersion(s_isTarget);
					
				}
			} else if (eventObj == m_targetSystemPassword) {
				if (valueHasChanged(e, m_targetSystemPasswordOld)) {
					String newValue = getNewValue(e);
					
					// target systemPassword
					// ---------------------
					resetSystemPassword(s_isTarget, newValue);
					resetName(s_isTarget, "");
					resetURL(s_isTarget, "");
					resetCatalog(s_isTarget, "");
					resetSchema(s_isTarget, "");
					resetVersion(s_isTarget);
					
				}
			} else if (eventObj == m_targetName) {
				if (valueHasChanged(e, m_targetNameOld)) {
					String newValue = getNewValue(e);

					// target name
					// -----------
					resetName(s_isTarget, newValue);
					resetURL(s_isTarget, "");
					resetCatalog(s_isTarget, "");
					resetSchema(s_isTarget, "");
					resetVersion(s_isTarget);
					
				}
			} else if (eventObj == m_targetCatalog) {
				if (valueHasChanged(e, m_targetCatalogOld)) {
					String newValue = getNewValue(e);
					
					// target catalog
					// --------------
					resetCatalog(s_isTarget, newValue);
					resetSchema(s_isTarget, "");
					resetVersion(s_isTarget);
					
				}
			} else if (eventObj == m_targetSchema) {
				if (valueHasChanged(e, m_targetSchemaOld)) {
					String newValue = getNewValue(e);
					
					// target schema
					// -------------
					resetSchema(s_isTarget, newValue);
					resetVersion(s_isTarget);
					
				}
			} else if (eventObj == m_targetReset) {
				
				// reset target
				// ------------
				m_isInitialized = false;
				resetTargetParameters();
				m_isInitialized = true;

			} else if (eventObj == m_modeUpgrade) {
				if (!m_upgradeModeOld) {

					// upgrade mode
					// ------------
					resetOptionStatus();

				}
			} else if (eventObj == m_modeTransfer) {
				if (m_upgradeModeOld) {

					// transfer mode
					// -------------
					resetOptionStatus();

				}
			} else if (eventObj == m_trace) {
				
				// view trace button
				// -----------------
				viewTrace();
				
			} else if (eventObj == m_warnings) {
				
				// view warnings button
				// --------------------
				viewWarnings();
				
			} else if (eventObj == m_errors) {
				
				// view errors button
				// ------------------
				viewErrors();
				
			} else if (eventObj == m_buttonStart) {
				
				// start button
				// ------------
				if (! m_isMigrationStarted) {
					startMigration();
				}
				
			} else if (eventObj == m_buttonCancel) {
				
				// cancel button
				// -------------
				cancel();
				
			} else if (eventObj == m_buttonClose) {
				
				// close button
				// ------------
				close();
				
			} else if (eventObj == m_menuFileSave) {
				
				// file save menu
				// --------------
				storeParameters();
				s_parameters.saveToFile();
				
			} else if (eventObj == m_menuFileClose) {
				
				// file close menu
				// ---------------
				close();
				
			} else if (eventObj == m_menuFileExit) {
				
				// file exit menu
				// --------------
				cancel();
				
			} else if (eventObj == m_menuHelpInfo) {
				
				// help info menu
				// --------------
				viewHelp();
				
			} else if (eventObj == m_menuHelpAbout) {
				
				// help about menu
				// ---------------
				viewAbout();
				
			}
		}
		
		m_isBusy = false;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	public void focusGained(FocusEvent e) {
		// we are not interested in any new focus events
		return;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	public void focusLost(FocusEvent e) {
		
		// if a text field lost focus, fire an action event
		if (e.getSource().getClass().getSimpleName().equalsIgnoreCase("JTextField")) {
			String s = ((JTextField) e.getSource()).getText();
			ActionEvent ae = new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, s);
			actionPerformed (ae);
		}
		
		return;
	}

	/**
	 * checks whether the event action was triggered by changing a value
	 * @param e the action event
	 * @return the action was triggered by changing a value
	 */
	private boolean valueChangeTrigger (ActionEvent e) {
		boolean result = false;

		Object obj = e.getSource();
		String command = e.getActionCommand();

		if (obj.getClass().getSimpleName().equalsIgnoreCase("JComboBox")) {
			// for combo boxes, we are only interested in comboBoxChanged
			if (command.equalsIgnoreCase("comboBoxChanged"))
				result = true;
		} else {
			// for other widgets, any action is OK
			result = true;
		}
		
		return result;
	}
	
	/**
	 * checks whether the contents of a widget has changed
	 * @param e the action event
	 * @param oldValue the old value previously stored
	 * @return the value has changed
	 */
	private boolean valueHasChanged (ActionEvent e, String oldValue) {
		boolean result = true;
		
		String n = getNewValue(e);
		
		String o = "";
		if (oldValue!=null && oldValue.length()>0)
			o = oldValue;
		
		if (n.equalsIgnoreCase(o))
			result = false;
		
		return result;
	}
	
	/**
	 * gets the new value from a widget as String
	 * @param e the action event
	 * @return the new value as String
	 */
	private String getNewValue (ActionEvent e) {

		String newValue = "";
		
		Object obj = e.getSource();

		if (obj.getClass().getSimpleName().equalsIgnoreCase("JComboBox")) {
			// for combo boxes, we need deeper null-checking
			if (((JComboBox) obj).getSelectedItem()!=null)
				newValue = ((JComboBox) obj).getSelectedItem().toString();
		} else {
			// for other widgets, just take the string
			newValue = ((JTextField) obj).getText();
		}
		
		if (newValue==null || newValue.length()==0)
			newValue = "";

		return newValue;
	}
	
	/**
	 * connect to the database
	 * @param isSource use source connection (if false, use target connection)
	 * @param isSystem log in as system (privileged) user rather than normal user
	 * @return connection to the database
	 */
	private Connection getDatabaseConnection(boolean isSource, boolean isSystem) {
		
		Connection connection = null;

		String vendor = null;
		String host = null;
		String port = null;
		String name = null;
		String user = null;
		String passwd = null;
		if (isSource) {
			// use connection to source
			if (m_sourceVendor.getSelectedItem()!=null)
				vendor = m_sourceVendor.getSelectedItem().toString();
			host = m_sourceHost.getText();
			port = m_sourcePort.getText();
			if (m_sourceName.getSelectedItem()!=null)
				name = m_sourceName.getSelectedItem().toString();
			if (isSystem) {
				user = s_dbEngine.getDBSystemOrNormalUser(vendor, m_sourceUser.getText(), m_sourceSystemUser.getText());
				passwd = s_dbEngine.getDBSystemOrNormalPassword(vendor, m_sourcePassword.getText(), m_sourceSystemPassword.getText());
			} else {
				user = m_sourceUser.getText();
				passwd = m_sourcePassword.getText();
			}
		} else {
			// use connection to target
			if (m_targetVendor.getSelectedItem()!=null)
				vendor = m_targetVendor.getSelectedItem().toString();
			host = m_targetHost.getText();
			port = m_targetPort.getText();
			if (m_targetName.getSelectedItem()!=null)
				name = m_targetName.getSelectedItem().toString();
			if (isSystem) {
				user = s_dbEngine.getDBSystemOrNormalUser(vendor, m_targetUser.getText(), m_targetSystemUser.getText());
				passwd = s_dbEngine.getDBSystemOrNormalPassword(vendor, m_targetPassword.getText(), m_targetSystemPassword.getText());
			} else {
				user = m_targetUser.getText();
				passwd = m_targetPassword.getText();
			}
		}
		String driver = s_dbEngine.getDBDriver(vendor);
		String url = s_dbEngine.getDBUrl(vendor, host, port, name);
		
		// Load the JDBC driver
		if (vendor!=null && vendor.length()>0) {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// nothing needs to be done in case of failure, just trying to access metadata for user's convenience
				// (failure is expected as long as parameters are wrong or incomplete)
			}
		}

		// connect to the database
		if (url!=null && url.length()>0) {
			try {
				connection = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				// nothing needs to be done in case of failure, just trying to access metadata for user's convenience
				// (failure is expected as long as parameters are wrong or incomplete)
			}
		}
		
		return connection;
	}

	/**
	 * Close a database connection
	 * @param connection the connection to close
	 */
	private void closeDatabaseConnection (Connection connection) {
		if (connection == null)
			return;
		
		try {
			connection.close();
		} catch (SQLException e) {
			// nothing needs to be done in case of failure, just trying to access metadata for user's convenience
			// (failure is expected as long as parameters are wrong or incomplete)
		}
		
		connection = null;
		
		return;
	}
	
	/**
	 * test whether a valid connection can be used
	 * @param isSource use source connection (if false, use target connection)
	 * @param isSystem log in as system (privileged) user rather than normal user
	 * @return the connection is valid
	 */
	private boolean testConnection (boolean isSource, boolean isSystem) {
		
		Connection connection = getDatabaseConnection(isSource, isSystem);

		boolean result = false;
		if (connection != null)
			result = true;
		
		closeDatabaseConnection(connection);
	
		return result;
	}
	
	/**
	 * make sure the source and target databases are different
	 * @return source and target databases are different
	 */
	private boolean testSourceTargetDifferent () {
		boolean result = false;

		String sourceVendor = "";
		String targetVendor = "";
		String sourceHost = "";
		String targetHost = "";
		String sourcePort = "";
		String targetPort = "";
		String sourceName = "";
		String targetName = "";
		String sourceCatalog = "";
		String targetCatalog = "";
		String sourceSchema = "";
		String targetSchema = "";
		
		if (m_sourceVendor.getSelectedItem()!=null)
			sourceVendor = m_sourceVendor.getSelectedItem().toString().trim();
		if (m_targetVendor.getSelectedItem()!=null)
			targetVendor = m_targetVendor.getSelectedItem().toString().trim();
		
		if (m_sourceHost.getText()!=null)
			sourceHost = m_sourceHost.getText().trim();
		if (m_targetHost.getText()!=null)
			targetHost = m_targetHost.getText().trim();
		
		if (m_sourcePort.getText()!=null)
			sourcePort = m_sourcePort.getText().trim();
		if (m_targetPort.getText()!=null)
			targetPort = m_targetPort.getText().trim();
		
		if (m_sourceName.getSelectedItem()!=null)
			sourceName = m_sourceName.getSelectedItem().toString().trim();
		if (m_targetName.getSelectedItem()!=null)
			targetName = m_targetName.getSelectedItem().toString().trim();

		if (m_sourceCatalog.getSelectedItem()!=null)
			sourceCatalog = m_sourceCatalog.getSelectedItem().toString().trim();
		if (m_targetCatalog.getSelectedItem()!=null)
			targetCatalog = m_targetCatalog.getSelectedItem().toString().trim();

		if (m_sourceSchema.getSelectedItem()!=null)
			sourceSchema = m_sourceSchema.getSelectedItem().toString().trim();
		if (m_targetSchema.getSelectedItem()!=null)
			targetSchema = m_targetSchema.getSelectedItem().toString().trim();

		if (! sourceVendor.equalsIgnoreCase(targetVendor))
			result = true;
		else if (! sourceHost.equalsIgnoreCase(targetHost))
			result = true;
		else if (! sourcePort.equalsIgnoreCase(targetPort))
			result = true;
		else if (! sourceName.equalsIgnoreCase(targetName))
			result = true;
		else if (! sourceCatalog.equalsIgnoreCase(targetCatalog))
			result = true;
		else if (! sourceSchema.equalsIgnoreCase(targetSchema))
			result = true;
		
		return result;
	}
	
	/**
	 * make sure vendors are same for upgrade mode
	 * @return migration mode can be performed for selected vendors
	 */
	private boolean testModeBasedOnVendors() {
		boolean result = true;
		
		String sourceVendor = "";
		String targetVendor = "";
		if (m_sourceVendor.getSelectedItem()!=null)
			sourceVendor = m_sourceVendor.getSelectedItem().toString().trim();
		if (m_targetVendor.getSelectedItem()!=null)
			targetVendor = m_targetVendor.getSelectedItem().toString().trim();
	
		if (m_modeUpgrade.isSelected()) {
			if (! sourceVendor.equalsIgnoreCase(targetVendor))
				result = false;
		}
		
		return result;
	}
	
	/**
	 * load the names of databases available
	 * @param isSource use source connection (if false, use target connection)
	 * @return list of database names
	 */
	private ArrayList<String> getDatabaseNames (boolean isSource) {

		
		String vendor = null;
		if (isSource) {
			vendor = m_sourceVendor.getSelectedItem().toString();
		} else {
			vendor = m_targetVendor.getSelectedItem().toString();
		}

		// load database names
		ArrayList<String> databaseNames = new ArrayList<String> ();
		Connection connection = getDatabaseConnection (isSource, false);
		if (connection!=null && vendor!=null) {
			try {
				String sql = s_dbEngine.sqlMetadata_availableDatabases(vendor);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					databaseNames.add(rs.getString("DATABASE_NAME"));
				}
				rs.close();
			} catch (SQLException e) {
				// nothing needs to be done in case of failure, just trying to access metadata for user's convenience
				// (failure is expected as long as parameters are wrong or incomplete)
			}
		}
		closeDatabaseConnection(connection);
		
		if (databaseNames.size()>0)
			Collections.sort(databaseNames);

		return databaseNames;
	}

	/**
	 * load the names of catalogs available
	 * @param isSource use source connection (if false, use target connection)
	 * @return list of catalog names
	 */
	private ArrayList<String> getCatalogNames (boolean isSource) {

		String vendor = null;
		if (isSource) {
			if (m_sourceVendor.getSelectedItem()!=null)
				vendor = m_sourceVendor.getSelectedItem().toString();
		} else {
			if (m_targetVendor.getSelectedItem()!=null)
				vendor = m_targetVendor.getSelectedItem().toString();
		}
		
		ArrayList<String> catalogNames = new ArrayList<String> ();
		
		Connection connection = getDatabaseConnection(isSource, false);
		
		if (connection!=null) {
			try {
				DatabaseMetaData md = connection.getMetaData();
				ResultSet rs = md.getCatalogs();
				while (rs.next()) {
					String s = rs.getString("TABLE_CAT");
					if (! s_dbEngine.isSystemCatalog(vendor, s))
						catalogNames.add(s);
				}
				rs.close();
			} catch (SQLException e) {
				// nothing needs to be done in case of failure, just trying to access metadata for user's convenience
				// (failure is expected as long as parameters are wrong or incomplete)
			}
		}

		closeDatabaseConnection(connection);
		
		if (catalogNames.size()>0)
			Collections.sort(catalogNames);

		return catalogNames;
	}

	/**
	 * retrieves a useful catalog (as search parameter for jdbc functions)
	 * @param isSource use source connection (if false, use target connection)
	 * @param newCatalog the new catalog name to use (null if settings from paramaters should be used,
	 * empty if default value for current database should be used)
	 * @return catalog name
	 */
	@SuppressWarnings("static-access")
	private String getCatalogName(boolean isSource, String newCatalog) {
		
		String catalogName = null;
		
		if (isSource) {
			if (newCatalog==null) {
				// use parameters
				catalogName = s_parameters.getSourceCatalog();
			} else if (newCatalog.length()==0) {
				// use default value
				catalogName = null;
			} else {
				// use local variable
				catalogName = newCatalog;
			}
			
		} else {
			if (newCatalog==null) {
				// use parameters
				catalogName = s_parameters.getTargetCatalog();
			} else if (newCatalog.length()==0) {
				// use default value
				catalogName = null;
			} else {
				// use local variable
				catalogName = newCatalog;
			}
		}
		
		Connection connection = getDatabaseConnection(isSource, false);

		// find useful catalog name from list of all catalogs
		if (catalogName == null || catalogName.length() == 0) {
			if (connection!=null) {
				try {
					DatabaseMetaData md = connection.getMetaData();
					String url = md.getURL();
					String user = md.getUserName();
					ResultSet rs = md.getCatalogs();
					while (rs.next()) {
						String s = rs.getString("TABLE_CAT");
						if (user.equalsIgnoreCase(s)
								|| url.toUpperCase().contains(s.toUpperCase())) {
							catalogName = s;
						}
					}
					rs.close();
				} catch (SQLException e) {
					// nothing needs to be done in case of failure, just trying to access metadata for user's convenience
					// (failure is expected as long as parameters are wrong or incomplete)
				}
			}
		}
		closeDatabaseConnection(connection);

		return catalogName;
	}

	/**
	 * load the names of schemas available
	 * @param isSource use source connection (if false, use target connection)
	 * @return list of schema names
	 */
	private ArrayList<String> getSchemaNames (boolean isSource) {

		String vendor = null;
		if (isSource) {
			if (m_sourceVendor.getSelectedItem()!=null)
				vendor = m_sourceVendor.getSelectedItem().toString();
		} else {
			if (m_targetVendor.getSelectedItem()!=null)
				vendor = m_targetVendor.getSelectedItem().toString();
		}
		
		ArrayList<String> schemaNames = new ArrayList<String> ();

		Connection connection = getDatabaseConnection(isSource, false);
		
		if (connection!=null) {
			try {
				DatabaseMetaData md = connection.getMetaData();
				ResultSet rs  = md.getSchemas();
				while (rs.next()) {
					String s = rs.getString("TABLE_SCHEM");
					if (! s_dbEngine.isSystemSchema(vendor, s))
						schemaNames.add(s);
				}
				rs.close();
			} catch (SQLException e) {
				// nothing needs to be done in case of failure, just trying to access metadata for user's convenience
				// (failure is expected as long as parameters are wrong or incomplete)
			}
		}
		
		closeDatabaseConnection(connection);
		
		if (schemaNames.size()>0)
			Collections.sort(schemaNames);

		return schemaNames;
	}

	/**
	 * retrieves a useful schema (as search parameter for jdbc functions)
	 * @param isSource use source connection (if false, use target connection)
	 * @param newSchema the new schema name to use (null if settings from paramaters should be used,
	 * empty if default value for current database should be used)
	 * @return schema name
	 */
	@SuppressWarnings("static-access")
	private String getSchemaName(boolean isSource, String newSchema) {
		
		String schemaName = null;
		String vendorName = null;
		String userName = null;
		
		if (isSource) {
			vendorName = m_sourceVendor.getSelectedItem().toString();
			userName = m_sourceUser.getText();
			if (newSchema==null) {
				// use parameters
				schemaName = s_parameters.getSourceSchema();
			} else if (newSchema.length()==0) {
				// use default
				schemaName = null;
			} else {
				// use local variable
				schemaName = newSchema;
			}
		} else {
			vendorName = m_targetVendor.getSelectedItem().toString();
			userName = m_targetUser.getText();
			if (newSchema==null) {
				// use parameters
				schemaName = s_parameters.getTargetSchema();
			} else if (newSchema.length()==0) {
				// use default
				schemaName = null;
			} else {
				// use local variable
				schemaName = newSchema;
			}
		}
		
		Connection connection = getDatabaseConnection(isSource, false);

		// find useful schema name from list of all schemas
		if (schemaName==null || schemaName.length()==0) {
			if (connection!=null) {
				try {
					DatabaseMetaData md = connection.getMetaData();
					String url = md.getURL();
					String user = md.getUserName();
					ResultSet rs  = md.getSchemas();
					while (rs.next()) {
						String s = rs.getString("TABLE_SCHEM");
						if (user.equalsIgnoreCase(s)
								|| url.toUpperCase().contains(s.toUpperCase())) {
							schemaName = s;
						}
					}
					rs.close();
				} catch (SQLException e) {
					// nothing needs to be done in case of failure, just trying to access metadata for user's convenience
					// (failure is expected as long as parameters are wrong or incomplete)
				}
			}
		}

		// in some databases, the schema must be same as user
		schemaName = s_dbEngine.getDBSchemaOrUser(vendorName, schemaName, userName);
		
		return schemaName;
	}

	/**
	 * load the adempiere version
	 * @param isSource use source connection (if false, use target connection)
	 * @return list of schema names
	 */
	private String getAdempiereVersion (boolean isSource) {

		String vendor = null;
		String catalog = null;
		String schema = null;
		if (isSource) {
			if (m_sourceVendor.getSelectedItem()!=null)
				vendor = m_sourceVendor.getSelectedItem().toString();
			if (m_sourceCatalog.getSelectedItem()!=null)
				catalog = m_sourceCatalog.getSelectedItem().toString();
			if (m_sourceSchema.getSelectedItem()!=null)
				schema = m_sourceSchema.getSelectedItem().toString();
		} else {
			if (m_targetVendor.getSelectedItem()!=null)
				vendor = m_targetVendor.getSelectedItem().toString();
			if (m_targetCatalog.getSelectedItem()!=null)
				catalog = m_targetCatalog.getSelectedItem().toString();
			if (m_targetSchema.getSelectedItem()!=null)
				schema = m_targetSchema.getSelectedItem().toString();
		}
		
		// load version
		String versionInfo = s_logger.localizeMessage("guiNoVersionInfo");
		Connection connection = getDatabaseConnection (isSource, false);
		if (connection!=null) {
			try {
				String sql = s_dbEngine.sqlAD_getAdempiereVersion(vendor, catalog, schema);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					if (rs.getString("ReleaseNo")!=null && rs.getString("Version")!=null) 
						versionInfo =  new StringBuffer(rs.getString("ReleaseNo")).append(" (").append(rs.getString("Version")).append(")").toString();
				}
				rs.close();
			} catch (SQLException e) {
				// do nothing if fails, we just want simple metada access
			}
		}
		closeDatabaseConnection(connection);
		
		return versionInfo;
	}
	
	/**
	 * reload database name combobox 
	 * @param isSource use source connection (if false, use target connection)
	 * @param newName the new database name to use (null if settings from paramaters should be used,
	 * empty if default value for current database should be used)
	 */
	@SuppressWarnings("static-access")
	private void resetName (boolean isSource, String newName) {

		String name = "";

		if (isSource) {
			// set source name
			if (newName==null) {
				// use parameter values
				if (s_parameters.getSourceName()!=null)
					name = s_parameters.getSourceName();
			} else if (newName.length()==0) {
				// use default values
				if (m_sourceName.getSelectedItem()!=null)
					name = m_sourceName.getSelectedItem().toString();
			} else {
				// use local variables
				name = newName;
			}
		} else {
			// set target name
			if (newName==null) {
				// use parameter values
				if (s_parameters.getTargetName()!=null)
					name = s_parameters.getTargetName();
			} else if (newName.length()==0) {
				// use default values
				if (m_targetName.getSelectedItem()!=null)
					name = m_targetName.getSelectedItem().toString();
			} else {
				// use local variables
				name = newName;
			}
		}

		
		ArrayList<String> databaseNames = getDatabaseNames(isSource);
		
		if (isSource) {
			m_sourceName.removeAllItems();
		} else {
			m_targetName.removeAllItems();
		}
		
        if (databaseNames != null && databaseNames.size()>0) {
			for (String databaseName : databaseNames) {
				if (isSource) {
					m_sourceName.addItem(databaseName);
				} else {
					m_targetName.addItem(databaseName);
				}
			}
		} else {
			if (isSource) {
				m_sourceName.addItem(name);
			} else {
				m_targetName.addItem(name);
			}
		}
        
        if (isSource) {
        	m_sourceName.setSelectedItem(name);
        	m_sourceNameOld = name;
        } else {
        	m_targetName.setSelectedItem(name);
        	m_targetNameOld = name;
        }

	}
	
	/**
	 * reload catalog combo box
	 * @param isSource use source connection (if false, use target connection)
	 * @param newCatalog the new catalog to use (null if settings from paramaters should be used,
	 * empty if default value for current database should be used)
	 */
	private void resetCatalog (boolean isSource, String newCatalog) {

		String name = getCatalogName(isSource, newCatalog);

		ArrayList<String> databaseCatalogs = getCatalogNames(isSource);
		
		if (isSource) {
			m_sourceCatalog.removeAllItems();
		} else {
			m_targetCatalog.removeAllItems();
		}
		
		if (databaseCatalogs != null && databaseCatalogs.size()>0) {
			for (String databaseCatalog : databaseCatalogs) {
				if (isSource) {
					m_sourceCatalog.addItem(databaseCatalog.toLowerCase());
				} else {
					m_targetCatalog.addItem(databaseCatalog.toLowerCase());
				}
			}
		} 
		
		if (isSource) {
			m_sourceCatalog.setSelectedItem(name);
			m_sourceCatalogOld = name;
		} else {
			m_targetCatalog.setSelectedItem(name);
			m_targetCatalogOld = name;
		}
		
	}

	/**
	 * reload schema combo box
	 * @param isSource use source connection (if false, use target connection)
	 * @param newSchema the new schema to use (null if settings from paramaters should be used,
	 * empty if default value for current database should be used)
	 */
	private void resetSchema (boolean isSource, String newSchema) {

        String name = getSchemaName(isSource, newSchema);

        if (isSource) {
        	m_sourceSchema.removeAllItems();
        } else {
        	m_targetSchema.removeAllItems();
        }
        
		ArrayList<String> databaseSchemas = getSchemaNames(isSource);
		
		if (databaseSchemas != null && databaseSchemas.size()>0) {
			for (String databaseSchema : databaseSchemas) {
				if (isSource) {
					m_sourceSchema.addItem(databaseSchema.toLowerCase());
				} else {
					m_targetSchema.addItem(databaseSchema.toLowerCase());
				}
			}
		} 
		
		if (isSource) {
			m_sourceSchema.setSelectedItem(name);
			m_sourceSchemaOld = name;
		} else {
			m_targetSchema.setSelectedItem(name);
			m_targetSchemaOld = name;
		}
		
	}
	
	/**
	 * enables or disables system user and password fields depending on database vendor
	 * @param isSource use source connection (if false, use target connection)
	 */
	private void resetSystemUserStatus(boolean isSource) {

		String vendorName = null;
		
		if (isSource) {
			// set status of source system user and password fields
			if (m_sourceVendor.getSelectedItem()!=null)
				vendorName = m_sourceVendor.getSelectedItem().toString();

			if (s_dbEngine.getDBSystemOrNormalUser(vendorName, "no", "yes").equalsIgnoreCase("yes")) {
				m_sourceSystemUser.setEnabled(true);
				m_sourceSystemUser.setEditable(true);
				m_sourceSystemPassword.setEnabled(true);
				m_sourceSystemPassword.setEditable(true);
			} else {
				m_sourceSystemUser.setEnabled(false);
				m_sourceSystemUser.setEditable(false);
				m_sourceSystemPassword.setEnabled(false);
				m_sourceSystemPassword.setEditable(false);
			}
		} else {
			// set status of target system user and password fields
			if (m_targetVendor.getSelectedItem()!=null)
				vendorName = m_targetVendor.getSelectedItem().toString();

			if (s_dbEngine.getDBSystemOrNormalUser(vendorName, "no", "yes").equalsIgnoreCase("yes")) {
				m_targetSystemUser.setEnabled(true);
				m_targetSystemUser.setEditable(true);
				m_targetSystemPassword.setEnabled(true);
				m_targetSystemPassword.setEditable(true);
			} else {
				m_targetSystemUser.setEnabled(false);
				m_targetSystemUser.setEditable(false);
				m_targetSystemPassword.setEnabled(false);
				m_targetSystemPassword.setEditable(false);
			}
		}

	}

	/**
	 * resets the port
	 * @param isSource use source connection (if false, use target connection)
	 * @param newPort the new port to use (null if settings from paramaters should be used, 
	 * empty if default value for current database should be used)
	 */
	@SuppressWarnings("static-access")
	private void resetPort (boolean isSource, String newPort) {
		
		String vendor = null;
		String port = null;
		
		if (isSource) {
			// set source port
			if (newPort==null) {
				// use parameter values
				vendor = s_parameters.getSourceVendor();
				port = s_parameters.getSourcePort();
			} else if (newPort.length()==0) {
				// use default values
				if (m_sourceVendor.getSelectedItem()!=null)
					vendor = m_sourceVendor.getSelectedItem().toString();
				port = null;
			} else {
				// use local variables
				if (m_sourceVendor.getSelectedItem()!=null)
					vendor = m_sourceVendor.getSelectedItem().toString();
				port = newPort;
			}
			
			String result = s_dbEngine.getDBPort(vendor, port);
			m_sourcePort.setText(result);
			m_sourcePortOld = result;
			
		} else {
			// set target port
			if (newPort==null) {
				// use parameter values
				vendor = s_parameters.getTargetVendor();
				port = s_parameters.getTargetPort();
			} else if (newPort.length()==0) {
				// use default values
				if (m_targetVendor.getSelectedItem()!=null)
					vendor = m_targetVendor.getSelectedItem().toString();
				port = null;
			} else {
				// use local variables
				if (m_targetVendor.getSelectedItem()!=null)
					vendor = m_targetVendor.getSelectedItem().toString();
				port = newPort;
			}
			
			String result  = s_dbEngine.getDBPort(vendor, port);
			m_targetPort.setText(result);
			m_targetPortOld = result;
		}
		
	}
	
	/**
	 * resets the host
	 * @param isSource use source connection (if false, use target connection)
	 * @param newHost the new host to use (null if settings from paramaters should be used, 
	 * empty if default value for current database should be used)
	 */
	@SuppressWarnings("static-access")
	private void resetHost (boolean isSource, String newHost) {
		
		String host = null;
		
		if (isSource) {
			// set source host
			if (newHost==null) {
				// use parameter values
				host = s_parameters.getSourceHost();
			} else if (newHost.length()==0) {
				// use default values
				host = m_sourceHost.getText();;
			} else {
				// use local variables
				host = newHost;
			}
			
			m_sourceHost.setText(host);
			m_sourceHostOld = host;
			
		} else {
			// set target host
			if (newHost==null) {
				// use parameter values
				host = s_parameters.getTargetHost();
			} else if (newHost.length()==0) {
				// use default values
				host = m_targetHost.getText();
			} else {
				// use local variables
				host = newHost;
			}
			
			m_targetHost.setText(host);
			m_targetHostOld = host;
		}
		
	}

	/**
	 * resets the user
	 * @param isSource use source connection (if false, use target connection)
	 * @param newUser the new user to use (null if settings from paramaters should be used, 
	 * empty if default value for current database should be used)
	 */
	@SuppressWarnings("static-access")
	private void resetUser (boolean isSource, String newUser) {
		
		String user = null;
		
		if (isSource) {
			// set source user
			if (newUser==null) {
				// use parameter values
				user = s_parameters.getSourceUser();
			} else if (newUser.length()==0) {
				// use default values
				user = m_sourceUser.getText();;
			} else {
				// use local variables
				user = newUser;
			}
			
			m_sourceUser.setText(user);
			m_sourceUserOld = user;
			
		} else {
			// set target user
			if (newUser==null) {
				// use parameter values
				user = s_parameters.getTargetUser();
			} else if (newUser.length()==0) {
				// use default values
				user = m_targetUser.getText();
			} else {
				// use local variables
				user = newUser;
			}
			
			m_targetUser.setText(user);
			m_targetUserOld = user;
		}
		
	}

	/**
	 * resets the password
	 * @param isSource use source connection (if false, use target connection)
	 * @param newPassword the new password to use (null if settings from paramaters should be used, 
	 * empty if default value for current database should be used)
	 */
	@SuppressWarnings("static-access")
	private void resetPassword (boolean isSource, String newPassword) {
		
		String password = null;
		
		if (isSource) {
			// set source password
			if (newPassword==null) {
				// use parameter values
				password = s_parameters.getSourcePasswd();
			} else if (newPassword.length()==0) {
				// use default values
				password = m_sourcePassword.getText();;
			} else {
				// use local variables
				password = newPassword;
			}
			
			m_sourcePassword.setText(password);
			m_sourcePasswordOld = password;
			
		} else {
			// set target password
			if (newPassword==null) {
				// use parameter values
				password = s_parameters.getTargetPasswd();
			} else if (newPassword.length()==0) {
				// use default values
				password = m_targetPassword.getText();
			} else {
				// use local variables
				password = newPassword;
			}
			
			m_targetPassword.setText(password);
			m_targetPasswordOld = password;
		}
		
	}

	/**
	 * resets the systemUser
	 * @param isSource use source connection (if false, use target connection)
	 * @param newSystemUser the new systemUser to use (null if settings from paramaters should be used, 
	 * empty if default value for current database should be used)
	 */
	@SuppressWarnings("static-access")
	private void resetSystemUser (boolean isSource, String newSystemUser) {
		
		String systemUser = null;
		
		if (isSource) {
			// set source systemUser
			if (newSystemUser==null) {
				// use parameter values
				systemUser = s_parameters.getSourceSystemUser();
			} else if (newSystemUser.length()==0) {
				// use default values
				systemUser = m_sourceSystemUser.getText();;
			} else {
				// use local variables
				systemUser = newSystemUser;
			}
			
			m_sourceSystemUser.setText(systemUser);
			m_sourceSystemUserOld = systemUser;
			
		} else {
			// set target systemUser
			if (newSystemUser==null) {
				// use parameter values
				systemUser = s_parameters.getTargetSystemUser();
			} else if (newSystemUser.length()==0) {
				// use default values
				systemUser = m_targetSystemUser.getText();
			} else {
				// use local variables
				systemUser = newSystemUser;
			}
			
			m_targetSystemUser.setText(systemUser);
			m_targetSystemUserOld = systemUser;
		}
		
	}

	/**
	 * resets the systemPassword
	 * @param isSource use source connection (if false, use target connection)
	 * @param newSystemPassword the new systemPassword to use (null if settings from paramaters should be used, 
	 * empty if default value for current database should be used)
	 */
	@SuppressWarnings("static-access")
	private void resetSystemPassword (boolean isSource, String newSystemPassword) {
		
		String systemPassword = null;
		
		if (isSource) {
			// set source systemPassword
			if (newSystemPassword==null) {
				// use parameter values
				systemPassword = s_parameters.getSourceSystemPasswd();
			} else if (newSystemPassword.length()==0) {
				// use default values
				systemPassword = m_sourceSystemPassword.getText();;
			} else {
				// use local variables
				systemPassword = newSystemPassword;
			}
			
			m_sourceSystemPassword.setText(systemPassword);
			m_sourceSystemPasswordOld = systemPassword;
			
		} else {
			// set target systemPassword
			if (newSystemPassword==null) {
				// use parameter values
				systemPassword = s_parameters.getTargetSystemPasswd();
			} else if (newSystemPassword.length()==0) {
				// use default values
				systemPassword = m_targetSystemPassword.getText();
			} else {
				// use local variables
				systemPassword = newSystemPassword;
			}
			
			m_targetSystemPassword.setText(systemPassword);
			m_targetSystemPasswordOld = systemPassword;
		}
		
	}

	/**
	 * resets the URL
	 * @param isSource use source connection (if false, use target connection)
	 * @param newURL the new URL to use (null if settings from paramaters should be used, 
	 * empty if default value for current database should be used)
	 */
	@SuppressWarnings("static-access")
	private void resetURL (boolean isSource, String newURL) {
		
		String vendor = null;
		String host = null;
		String port = null;
		String name = null;
		String result = null;

		if (isSource) {
			// set source url
			if (newURL==null) {
				// use parameter values
				vendor = s_parameters.getSourceVendor();
				host = s_parameters.getSourceHost();
				port = s_parameters.getSourcePort();
				name = s_parameters.getSourceName();
				result = s_dbEngine.getDBUrl(vendor, host, port, name);
			} else if (newURL.length()==0) {
				// use default values
				if (m_sourceVendor.getSelectedItem()!=null)
					vendor = m_sourceVendor.getSelectedItem().toString();
				host = m_sourceHost.getText();
				port = m_sourcePort.getText();
				if (m_sourceName.getSelectedItem()!=null)
					name = m_sourceName.getSelectedItem().toString();
				result = s_dbEngine.getDBUrl(vendor, host, port, name);
			} else {
				// use local variables
				result = newURL;
			}
			
			m_sourceURL.setText(result);
			
		} else {
			// set target url
			if (newURL==null) {
				// use parameter values
				vendor = s_parameters.getTargetVendor();
				host = s_parameters.getTargetHost();
				port = s_parameters.getTargetPort();
				name = s_parameters.getTargetName();
				result = s_dbEngine.getDBUrl(vendor, host, port, name);
			} else if (newURL.length()==0) {
				// use default values
				if (m_targetVendor.getSelectedItem()!=null)
					vendor = m_targetVendor.getSelectedItem().toString();
				host = m_targetHost.getText();
				port = m_targetPort.getText();
				if (m_targetName.getSelectedItem()!=null)
					name = m_targetName.getSelectedItem().toString();
				result = s_dbEngine.getDBUrl(vendor, host, port, name);
			} else {
				// use local variables
				result = newURL;
			}
			
			m_targetURL.setText(result);
		}
		
	}

	/**
	 * resets the vendor
	 * @param isSource use source connection (if false, use target connection)
	 * @param newVendor the new vendor to use (null if settings from paramaters should be used, 
	 * empty if default value for current database should be used)
	 */
	@SuppressWarnings("static-access")
	private void resetVendor (boolean isSource, String newName) {
		
		String vendor = null;
		
		if (isSource) {
			// set source vendor
			if (newName==null) {
				// use parameter values
				vendor = s_parameters.getSourceVendor().toLowerCase();
			} else if (newName.length()==0) {
				// use default values
				if (m_sourceVendor.getSelectedItem()!=null)
					vendor = m_sourceVendor.getSelectedItem().toString();
			} else {
				// use local variables
				vendor = newName;
			}
			
			m_sourceVendor.setSelectedItem(vendor);
			m_sourceVendorOld = vendor;
			
		} else {
			// set target vendor
			if (newName==null) {
				// use parameter values
				vendor = s_parameters.getTargetVendor().toLowerCase();
			} else if (newName.length()==0) {
				// use default values
				if (m_targetVendor.getSelectedItem()!=null)
					vendor = m_targetVendor.getSelectedItem().toString();
			} else {
				// use local variables
				vendor = newName;
			}
			
			m_targetVendor.setSelectedItem(vendor);
			m_targetVendorOld = vendor;
		}
		
		resetModeStatus();
	}

	/**
	 * reload adempiere version text field
	 * @param isSource use source connection (if false, use target connection)
	 */
	private void resetVersion (boolean isSource) {
		
		String version = getAdempiereVersion(isSource);
		if (isSource) {
			m_sourceVersion.setText(version);
		} else {
			m_targetVersion.setText(version);
		}
	}
	
	/**
	 * reset source parameter fields from application parameters 
	 */
	private void resetSourceParameters () {
		resetVendor(s_isSource, null);
		resetHost(s_isSource, null);
        resetPort(s_isSource, null);
        resetUser(s_isSource, null);
        resetPassword(s_isSource, null);
        resetSystemUser(s_isSource, null);
        resetSystemPassword(s_isSource, null);
		resetSystemUserStatus(s_isSource);
        resetName(s_isSource, null);
        resetURL(s_isSource, null);
        resetCatalog(s_isSource, null);
        resetSchema(s_isSource, null);
		resetVersion(s_isSource);
	}
	
	/**
	 * reset target parameter fields from application parameters 
	 */
	private void resetTargetParameters () {
		resetVendor(s_isTarget, null);
		resetHost(s_isTarget, null);
        resetPort(s_isTarget, null);
        resetUser(s_isTarget, null);
        resetUser(s_isTarget, null);
        resetPassword(s_isTarget, null);
        resetSystemUser(s_isTarget, null);
        resetSystemPassword(s_isTarget, null);
        resetSystemUserStatus(s_isTarget);
        resetName(s_isTarget, null);
        resetURL(s_isTarget, null);
        resetCatalog(s_isTarget, null);
        resetSchema(s_isTarget, null);
		resetVersion(s_isTarget);
	}

	/**
	 * resets the migration mode
	 */
	@SuppressWarnings("static-access")
	private void resetMigrationMode () {
		if (s_parameters.isUpgrade()) {
			m_modeUpgrade.setSelected(true);
			m_modeTransfer.setSelected(false);
		} else {
			m_modeUpgrade.setSelected(false);
			m_modeTransfer.setSelected(true);
		}
	}
	
	/**
	 * convert log level to descriptive localized text
	 * @param logLevel the log level
	 * @return String representation of log level
	 */
	private String logLevelToDescription (Level logLevel) {
		String result = s_logger.localizeMessage("guiLogLevelOff");
		
		if (logLevel.equals(Level.ALL)) {
			result = s_logger.localizeMessage("guiLogLevelAll");
		} else if (logLevel.equals(Level.FINEST)) {
			result = s_logger.localizeMessage("guiLogLevelFinest");
		} else if (logLevel.equals(Level.FINER)) {
			result = s_logger.localizeMessage("guiLogLevelFiner");
		} else if (logLevel.equals(Level.FINE)) {
			result = s_logger.localizeMessage("guiLogLevelFine");
		} else if (logLevel.equals(Level.CONFIG)) {
			result = s_logger.localizeMessage("guiLogLevelConfig");
		} else if (logLevel.equals(Level.INFO)) {
			result = s_logger.localizeMessage("guiLogLevelInfo");
		} else if (logLevel.equals(Level.WARNING)) {
			result = s_logger.localizeMessage("guiLogLevelWarning");
		} else if (logLevel.equals(Level.SEVERE)) {
			result = s_logger.localizeMessage("guiLogLevelSevere");
		} else if (logLevel.equals(Level.OFF)) {
			result = s_logger.localizeMessage("guiLogLevelOff");
		}
		
		return result;
	}
	
	/**
	 * convert localized descriptive text to log level
	 * @param description String representation of log level
	 * @return the log level
	 */
	private Level descriptionToLogLevel (String description) {
		Level result = Level.OFF;
		
		if (description.equals(s_logger.localizeMessage("guiLogLevelOff"))) {
			result = Level.OFF;
		} else if (description.equals(s_logger.localizeMessage("guiLogLevelSevere"))) {
			result = Level.SEVERE;
		} else if (description.equals(s_logger.localizeMessage("guiLogLevelWarning"))) {
			result = Level.WARNING;
		} else if (description.equals(s_logger.localizeMessage("guiLogLevelInfo"))) {
			result = Level.INFO;
		} else if (description.equals(s_logger.localizeMessage("guiLogLevelConfig"))) {
			result = Level.CONFIG;
		} else if (description.equals(s_logger.localizeMessage("guiLogLevelFine"))) {
			result = Level.FINE;
		} else if (description.equals(s_logger.localizeMessage("guiLogLevelFiner"))) {
			result = Level.FINER;
		} else if (description.equals(s_logger.localizeMessage("guiLogLevelFinest"))) {
			result = Level.FINEST;
		} else if (description.equals(s_logger.localizeMessage("guiLogLevelAll"))) {
			result = Level.ALL;
		}
		
		return result;
	}

	/**
	 * resets the log level combo box
	 */
	@SuppressWarnings("static-access")
	private void resetOptionLogLevel () {
		m_optionLogLevel.removeAllItems();
		
		m_optionLogLevel.addItem(logLevelToDescription(Level.OFF));
		m_optionLogLevel.addItem(logLevelToDescription(Level.SEVERE));
		m_optionLogLevel.addItem(logLevelToDescription(Level.WARNING));
		m_optionLogLevel.addItem(logLevelToDescription(Level.INFO));
		m_optionLogLevel.addItem(logLevelToDescription(Level.CONFIG));
		m_optionLogLevel.addItem(logLevelToDescription(Level.FINE));
		m_optionLogLevel.addItem(logLevelToDescription(Level.FINER));
		m_optionLogLevel.addItem(logLevelToDescription(Level.FINEST));
		m_optionLogLevel.addItem(logLevelToDescription(Level.ALL));
		
		m_optionLogLevel.setSelectedItem(logLevelToDescription(s_parameters.getLogLevel()));
	}
	
	/**
	 * enables or disables option fields depending on migration mode
	 */
	private void resetOptionStatus() {
		
		// get migration mode
		boolean isUpgrade = true;
		if (m_modeTransfer.isSelected())
			isUpgrade = false;
		
		// remember migration mode
		m_upgradeModeOld = isUpgrade;
		
		// enable or disable options
		if (isUpgrade) {
			m_optionAttemptTranslations.setEnabled(false);
			m_optionPreserveTableIDs.setEnabled(true);
			m_optionDropSource.setEnabled(true);
		} else {
			m_optionAttemptTranslations.setEnabled(true);
			m_optionPreserveTableIDs.setEnabled(false);
			m_optionDropSource.setEnabled(false);
		}
	}

	/**
	 * resets the attempt translations checkbox
	 */
	@SuppressWarnings("static-access")
	private void resetOptionAttemptTranslations () {
		m_optionAttemptTranslations.setSelected(s_parameters.isAttemptTranslation());
	}
	
	/**
	 * resets the preserve table IDs checkbox
	 */
	@SuppressWarnings("static-access")
	private void resetOptionPreserveTableIDs () {
		m_optionPreserveTableIDs.setSelected(s_parameters.isPreserveTableID());
	}
	
	/**
	 * resets the drop source checkbox
	 */
	@SuppressWarnings("static-access")
	private void resetOptionDropSource() {
		m_optionDropSource.setSelected(s_parameters.isDropSource());
	}

	/**
	 * resets the drop source checkbox
	 */
	@SuppressWarnings("static-access")
	private void resetOptionOptimizeDatabase() {
		m_optionOptimizeDatabase.setSelected(s_parameters.isOptimizeDatabase());
	}

	/**
	 * resets all options
	 */
	private void resetOptions () {
		resetOptionLogLevel();
		resetOptionAttemptTranslations();
		resetOptionPreserveTableIDs();
		resetOptionDropSource();
		resetOptionOptimizeDatabase();
		resetOptionStatus();
	}
	
	/**
	 * resets the status of migration mode selection depending on source and target vendor
	 */
	private void resetModeStatus () {
		
		// get source and target vendor ID
		int sourceID = 0;
		int targetID = 0;
		if (m_sourceVendor.getSelectedItem() != null)
			sourceID = s_dbEngine.getDBVendorID(m_sourceVendor.getSelectedItem().toString());
		if (m_targetVendor.getSelectedItem() != null)
			targetID = s_dbEngine.getDBVendorID(m_targetVendor.getSelectedItem().toString());
		
		if (sourceID == targetID) {
			// source and target are same vendor, upgrade mode is possible
			// (and likely to be what user wants)
			if (!m_modeUpgrade.isEnabled()) {
				m_modeUpgrade.setEnabled(true);
				m_modeUpgrade.setSelected(true);
				m_modeTransfer.setSelected(false);
				resetOptionStatus();
			}
		} else {
			// source and target are diffenrent vendor, only transfer mode is possible
			if (m_modeUpgrade.isEnabled()) {
				m_modeUpgrade.setEnabled(false);
				m_modeUpgrade.setSelected(false);
				m_modeTransfer.setSelected(true);
				resetOptionStatus();
			}
		}
	}
	
	/**
	 * create and show a log view window
	 * @param title key for window title
	 * @param logMessages the messages to display
	 * @param color foreground text color
	 */
	private void createLogWindow(String title, ArrayList<String> logMessages, Color color) {
		
		// do nothing if no log messages exist
		if (logMessages==null || logMessages.size()==0)
			return;
		ArrayList<String> displayLogMessages = new ArrayList<String>(logMessages);

		// create window
		JFrame frame = new JFrame(s_logger.localizeMessage(title));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container pane = frame.getContentPane();
		Locale locale = Locale.getDefault();
		pane.setComponentOrientation(ComponentOrientation.getOrientation(locale));

		JTextArea textArea = new JTextArea(25,80);
		textArea.setForeground(color);
		textArea.setEditable(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane(panel);

		pane.add(scrollPane);
		
		// size window
		frame.pack();
        frame.setLocationRelativeTo(null);
		
		// load data
		for (String line : displayLogMessages) {
			textArea.append(line);
		}
		
		// show window
		frame.setVisible(true);

	}

	/**
	 * view a snapshot of the trace log
	 */
	private void viewTrace() {
		createLogWindow("guiButtonViewTrace", m_traceLog, Color.BLACK);
	}
	
	/**
	 * view a snapshot of the warning log
	 */
	private void viewWarnings() {
		createLogWindow("guiButtonViewWarnings", m_warningLog, Color.BLUE);
	}
	
	/**
	 * view a snapshot of the error log
	 */
	private void viewErrors() {
		createLogWindow("guiButtonViewErrors", m_errorLog, Color.RED);
	}
	
	/**
	 * view the Help About dialog
	 */
	private void viewAbout() {
		HelpAbout about = new HelpAbout();
		about.createAndShowGui(m_frame);
	}
	
	/**
	 * view the Help Info dialog
	 */
	private void viewHelp() {
		HelpInfo info = new HelpInfo();
		info.createAndShowGui(m_frame);
	}
	
	/**
	 * start the migration process
	 */
	private void startMigration() {
		
		// test if source and target are different
		if (!testSourceTargetDifferent()) {
			JOptionPane.showMessageDialog(m_frame, 
					s_logger.localizeMessage("guiNoSourceTargetDifferent"), 
					s_logger.localizeMessage("guiNoSourceTargetDifferentTitle"), 
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// test upgrade or transfer mode based on vendors
		if (!testModeBasedOnVendors()) {
			JOptionPane.showMessageDialog(m_frame, 
					s_logger.localizeMessage("guiNoUpgradeForDifferentVendors"), 
					s_logger.localizeMessage("guiNoUpgradeForDifferentVendorsTitle"), 
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// test normal source connection
		if (!testConnection(s_isSource, false)) {
			JOptionPane.showMessageDialog(m_frame, 
					s_logger.localizeMessage("guiNoConnectSource"), 
					s_logger.localizeMessage("guiNoConnectTitle"), 
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// test system source connection
		if (!testConnection(s_isSource, true)) {
			JOptionPane.showMessageDialog(m_frame, 
					s_logger.localizeMessage("guiNoConnectSystemSource"), 
					s_logger.localizeMessage("guiNoConnectTitle"), 
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// test normal target connection
		// (only needed for upgrademode, transfer can be done to new (not yet existing) database)
		if (m_modeUpgrade.isSelected()) {
			if (!testConnection(s_isTarget, false)) {
				JOptionPane.showMessageDialog(m_frame, 
						s_logger.localizeMessage("guiNoConnectTarget"), 
						s_logger.localizeMessage("guiNoConnectTitle"), 
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		
		// test system target connection
		if (!testConnection(s_isTarget, true)) {
			JOptionPane.showMessageDialog(m_frame, 
					s_logger.localizeMessage("guiNoConnectSystemTarget"), 
					s_logger.localizeMessage("guiNoConnectTitle"), 
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// store selected values in Parameters class
		storeParameters();
		
		// reset logs
		resetLogViews();
		
		// do not allow interruption of migration process
		setMigrationRunning(true);

		// run migration as background process
		Thread worker = new Thread() {
			public void run() {

				// start actual migration
				s_migrator.startMigration();

				// Report the result using invokeLater().
				SwingUtilities.invokeLater(new Runnable() {public void run() {
					// update version information of target after migration
					resetVersion(false); //(false = target)
					// release lock on gui components after migration process finished
					setMigrationRunning(false); //(false=migration finished)
				}});
				
			}
		};

		worker.start(); // So we don't hold up the dispatch thread.
		
	}
	
	/**
	 * close without saving parameters
	 */
	private void cancel() {
		m_frame.setVisible(false);
		m_frame.dispose();
		System.exit(0);
	}
	
	/**
	 * save parameters and close
	 */
	private void close() {
		storeParameters();
		s_parameters.saveToFile();
		
		m_frame.setVisible(false);
		m_frame.dispose();
		System.exit(0);
	}

	/**
	 * toggle status of gui fields and start/close buttons
	 * @param isRunning migration process is running
	 */
	private void setMigrationRunning (boolean isRunning) {

		if (isRunning) {
			// migration will start
			// remember enabled/disabled status of all components for restoring when migration finished
			m_componentStatus = new HashMap<JComponent, Boolean> ();
			m_componentStatus.put(m_sourceVendor, m_sourceVendor.isEnabled());
			m_componentStatus.put(m_sourceHost, m_sourceHost.isEnabled());
			m_componentStatus.put(m_sourcePort, m_sourcePort.isEnabled());
			m_componentStatus.put(m_sourceUser, m_sourceUser.isEnabled());
			m_componentStatus.put(m_sourcePassword, m_sourcePassword.isEnabled());
			m_componentStatus.put(m_sourceSystemUser, m_sourceSystemUser.isEnabled());
			m_componentStatus.put(m_sourceSystemPassword, m_sourceSystemPassword.isEnabled());
			m_componentStatus.put(m_sourceName, m_sourceName.isEnabled());
			m_componentStatus.put(m_sourceCatalog, m_sourceCatalog.isEnabled());
			m_componentStatus.put(m_sourceSchema, m_sourceSchema.isEnabled());
			m_componentStatus.put(m_sourceReset, m_sourceReset.isEnabled());
			m_componentStatus.put(m_targetVendor, m_targetVendor.isEnabled());
			m_componentStatus.put(m_targetHost, m_targetHost.isEnabled());
			m_componentStatus.put(m_targetPort, m_targetPort.isEnabled());
			m_componentStatus.put(m_targetUser, m_targetUser.isEnabled());
			m_componentStatus.put(m_targetPassword, m_targetPassword.isEnabled());
			m_componentStatus.put(m_targetSystemUser, m_targetSystemUser.isEnabled());
			m_componentStatus.put(m_targetSystemPassword, m_targetSystemPassword.isEnabled());
			m_componentStatus.put(m_targetName, m_targetName.isEnabled());
			m_componentStatus.put(m_targetCatalog, m_targetCatalog.isEnabled());
			m_componentStatus.put(m_targetSchema, m_targetSchema.isEnabled());
			m_componentStatus.put(m_targetReset, m_targetReset.isEnabled());
			m_componentStatus.put(m_modeUpgrade, m_modeUpgrade.isEnabled());
			m_componentStatus.put(m_modeTransfer, m_modeTransfer.isEnabled());
			m_componentStatus.put(m_labelLogLevel, m_labelLogLevel.isEnabled());
			m_componentStatus.put(m_optionLogLevel, m_optionLogLevel.isEnabled());
			m_componentStatus.put(m_optionAttemptTranslations, m_optionAttemptTranslations.isEnabled());
			m_componentStatus.put(m_optionPreserveTableIDs, m_optionPreserveTableIDs.isEnabled());
			m_componentStatus.put(m_optionDropSource, m_optionDropSource.isEnabled());
			m_componentStatus.put(m_optionOptimizeDatabase, m_optionOptimizeDatabase.isEnabled());
			m_componentStatus.put(m_buttonStart, m_buttonStart.isEnabled());
			// disable all components - no change or interruption allowed while migration is running
			m_sourceVendor.setEnabled(false);
			m_sourceHost.setEnabled(false);
			m_sourcePort.setEnabled(false);
			m_sourceUser.setEnabled(false);
			m_sourcePassword.setEnabled(false);
			m_sourceSystemUser.setEnabled(false);
			m_sourceSystemPassword.setEnabled(false);
			m_sourceName.setEnabled(false);
			m_sourceCatalog.setEnabled(false);
			m_sourceSchema.setEnabled(false);
			m_sourceReset.setEnabled(false);
			m_targetVendor.setEnabled(false);
			m_targetHost.setEnabled(false);
			m_targetPort.setEnabled(false);
			m_targetUser.setEnabled(false);
			m_targetPassword.setEnabled(false);
			m_targetSystemUser.setEnabled(false);
			m_targetSystemPassword.setEnabled(false);
			m_targetName.setEnabled(false);
			m_targetCatalog.setEnabled(false);
			m_targetSchema.setEnabled(false);
			m_targetReset.setEnabled(false);
			m_modeUpgrade.setEnabled(false);
			m_modeTransfer.setEnabled(false);
			m_labelLogLevel.setEnabled(false);
			m_optionLogLevel.setEnabled(false);
			m_optionAttemptTranslations.setEnabled(false);
			m_optionPreserveTableIDs.setEnabled(false);
			m_optionDropSource.setEnabled(false);
			m_optionOptimizeDatabase.setEnabled(false);
			m_buttonStart.setEnabled(false);

		} else {
			
			// migration has finished
			// restore enabled/disabled status of components to state before migration started
			m_sourceVendor.setEnabled(m_componentStatus.get(m_sourceVendor));
			m_sourceHost.setEnabled(m_componentStatus.get(m_sourceHost));
			m_sourcePort.setEnabled(m_componentStatus.get(m_sourcePort));
			m_sourceUser.setEnabled(m_componentStatus.get(m_sourceUser));
			m_sourcePassword.setEnabled(m_componentStatus.get(m_sourcePassword));
			m_sourceSystemUser.setEnabled(m_componentStatus.get(m_sourceSystemUser));
			m_sourceSystemPassword.setEnabled(m_componentStatus.get(m_sourceSystemPassword));
			m_sourceName.setEnabled(m_componentStatus.get(m_sourceName));
			m_sourceCatalog.setEnabled(m_componentStatus.get(m_sourceCatalog));
			m_sourceSchema.setEnabled(m_componentStatus.get(m_sourceSchema));
			m_sourceReset.setEnabled(m_componentStatus.get(m_sourceReset));
			m_targetVendor.setEnabled(m_componentStatus.get(m_targetVendor));
			m_targetHost.setEnabled(m_componentStatus.get(m_targetHost));
			m_targetPort.setEnabled(m_componentStatus.get(m_targetPort));
			m_targetUser.setEnabled(m_componentStatus.get(m_targetUser));
			m_targetPassword.setEnabled(m_componentStatus.get(m_targetPassword));
			m_targetSystemUser.setEnabled(m_componentStatus.get(m_targetSystemUser));
			m_targetSystemPassword.setEnabled(m_componentStatus.get(m_targetSystemPassword));
			m_targetName.setEnabled(m_componentStatus.get(m_targetName));
			m_targetCatalog.setEnabled(m_componentStatus.get(m_targetCatalog));
			m_targetSchema.setEnabled(m_componentStatus.get(m_targetSchema));
			m_targetReset.setEnabled(m_componentStatus.get(m_targetReset));
			m_modeUpgrade.setEnabled(m_componentStatus.get(m_modeUpgrade));
			m_modeTransfer.setEnabled(m_componentStatus.get(m_modeTransfer));
			m_labelLogLevel.setEnabled(m_componentStatus.get(m_labelLogLevel));
			m_optionLogLevel.setEnabled(m_componentStatus.get(m_optionLogLevel));
			m_optionAttemptTranslations.setEnabled(m_componentStatus.get(m_optionAttemptTranslations));
			m_optionPreserveTableIDs.setEnabled(m_componentStatus.get(m_optionPreserveTableIDs));
			m_optionDropSource.setEnabled(m_componentStatus.get(m_optionDropSource));
			m_optionOptimizeDatabase.setEnabled(m_componentStatus.get(m_optionOptimizeDatabase));
			m_buttonStart.setEnabled(m_componentStatus.get(m_buttonStart));
			// allow window to be closed
			m_labelDoNotInterrupt.setVisible(false);
	        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			m_buttonCancel.setEnabled(true);
			m_buttonClose.setEnabled(true);
			m_menuFileClose.setEnabled(true);
			m_menuFileExit.setEnabled(true);
			// set focus on close button
			m_buttonClose.requestFocusInWindow();
		}

		// track running migration process
		m_isMigrationStarted = isRunning;
	}

	/**
	 * store selected values in Parameters Class
	 */
	@SuppressWarnings("static-access")
	private void storeParameters() {

		 // store migration mode
		boolean isUpgrade = true;
		 if (m_modeTransfer.isSelected())
			 isUpgrade = false;
		 s_parameters.setIsUpgrade(isUpgrade);
		 
		 // store log level
		 if (m_optionLogLevel.getSelectedItem()!=null)
			 s_parameters.setLogLevel(descriptionToLogLevel(m_optionLogLevel.getSelectedItem().toString()));

		 // store options
		 s_parameters.setAttemptTranslation(m_optionAttemptTranslations.isSelected());
		 s_parameters.setPreserveTableID(m_optionPreserveTableIDs.isSelected());
		 s_parameters.setDropSource(m_optionDropSource.isSelected());
		 s_parameters.setOptimizeDatabase(m_optionOptimizeDatabase.isSelected());
		 
		 // store source settings
		 String vendor = null;
		 String host = null;
		 String port = null;
		 String name = null;
		 String catalog = null;
		 String schema = null;
		 String user = null;
		 String passwd = null;
		 String systemUser = null;
		 String systemPasswd = null;
		 
		 if (m_sourceVendor.getSelectedItem()!=null && m_sourceVendor.getSelectedItem().toString().length()>0)
			 vendor = m_sourceVendor.getSelectedItem().toString();
		 if (m_sourceHost.getText()!=null && m_sourceHost.getText().length()>0)
			 host = m_sourceHost.getText();
		 if (m_sourcePort.getText()!=null && m_sourcePort.getText().length()>0)
			 port = m_sourcePort.getText();
		 if (m_sourceName.getSelectedItem()!=null && m_sourceName.getSelectedItem().toString().length()>0)
			 name = m_sourceName.getSelectedItem().toString();
		 if (m_sourceCatalog.getSelectedItem()!=null && m_sourceCatalog.getSelectedItem().toString().length()>0)
			 catalog = m_sourceCatalog.getSelectedItem().toString();
		 if (m_sourceSchema.getSelectedItem()!=null && m_sourceSchema.getSelectedItem().toString().length()>0)
			 schema = m_sourceSchema.getSelectedItem().toString();
		 if (m_sourceUser.getText()!=null && m_sourceUser.getText().length()>0)
			 user = m_sourceUser.getText();
		 if (m_sourcePassword.getText()!=null && m_sourcePassword.getText().length()>0)
			 passwd = m_sourcePassword.getText();
		 if (m_sourceSystemUser.getText()!=null && m_sourceSystemUser.getText().length()>0)
			 systemUser = m_sourceSystemUser.getText();
		 if (m_sourceSystemPassword.getText()!=null && m_sourceSystemPassword.getText().length()>0)
			 systemPasswd = m_sourceSystemPassword.getText();
		 
		 s_parameters.setSourceVendor(vendor);
		 s_parameters.setSourceHost(host);
		 s_parameters.setSourcePort(port);
		 s_parameters.setSourceName(name);
		 s_parameters.setSourceCatalog(catalog);
		 s_parameters.setSourceSchema(schema);
		 s_parameters.setSourceUser(user);
		 s_parameters.setSourcePasswd(passwd);
		 s_parameters.setSourceSystemUser(systemUser);
		 s_parameters.setSourceSystemPasswd(systemPasswd);
		 
		 // store target settings
		 vendor = null;
		 host = null;
		 port = null;
		 name = null;
		 catalog = null;
		 schema = null;
		 user = null;
		 passwd = null;
		 systemUser = null;
		 systemPasswd = null;
		 
		 if (m_targetVendor.getSelectedItem()!=null && m_targetVendor.getSelectedItem().toString().length()>0)
			 vendor = m_targetVendor.getSelectedItem().toString();
		 if (m_targetHost.getText()!=null && m_targetHost.getText().length()>0)
			 host = m_targetHost.getText();
		 if (m_targetPort.getText()!=null && m_targetPort.getText().length()>0)
			 port = m_targetPort.getText();
		 if (m_targetName.getSelectedItem()!=null && m_targetName.getSelectedItem().toString().length()>0)
			 name = m_targetName.getSelectedItem().toString();
		 if (m_targetCatalog.getSelectedItem()!=null && m_targetCatalog.getSelectedItem().toString().length()>0)
			 catalog = m_targetCatalog.getSelectedItem().toString();
		 if (m_targetSchema.getSelectedItem()!=null && m_targetSchema.getSelectedItem().toString().length()>0)
			 schema = m_targetSchema.getSelectedItem().toString();
		 if (m_targetUser.getText()!=null && m_targetUser.getText().length()>0)
			 user = m_targetUser.getText();
		 if (m_targetPassword.getText()!=null && m_targetPassword.getText().length()>0)
			 passwd = m_targetPassword.getText();
		 if (m_targetSystemUser.getText()!=null && m_targetSystemUser.getText().length()>0)
			 systemUser = m_targetSystemUser.getText();
		 if (m_targetSystemPassword.getText()!=null && m_targetSystemPassword.getText().length()>0)
			 systemPasswd = m_targetSystemPassword.getText();
		 
		 s_parameters.setTargetVendor(vendor);
		 s_parameters.setTargetHost(host);
		 s_parameters.setTargetPort(port);
		 s_parameters.setTargetName(name);
		 s_parameters.setTargetCatalog(catalog);
		 s_parameters.setTargetSchema(schema);
		 s_parameters.setTargetUser(user);
		 s_parameters.setTargetPasswd(passwd);
		 s_parameters.setTargetSystemUser(systemUser);
		 s_parameters.setTargetSystemPasswd(systemPasswd);
		 
	}


	/**
	 * @param isTextMode the isTextMode to set
	 */
	private static void setTextMode(boolean isTextMode) {
		s_isTextMode = isTextMode;
	}

	/**
	 * @return the isTextMode
	 */
	public static boolean isTextMode() {
		return s_isTextMode;
	}


	/**
	 * @return the step
	 */
	public JTextField getStep() {
		return m_step;
	}


	/**
	 * @return the action
	 */
	public JTextField getAction() {
		return m_action;
	}


	/**
	 * @return the detail
	 */
	public JTextField getDetail() {
		return m_detail;
	}

	/**
	 * reset log view contents
	 */
	private void resetLogViews() {
		resetTraceLog();
		resetWarningLog();
		resetErrorLog();
	}
	
	/**
	 * reset trace log contents and disable button
	 */
	private void resetTraceLog() {
		m_traceLog = null;
		m_trace.setEnabled(false);
	}
	
	/**
	 * reset warning log content and disable button
	 */
	private void resetWarningLog() {
		m_warningLog = null;
		m_warningCounter = 0;
		m_warnings.setText(s_logger.localizeMessage("guiButtonViewWarnings"));
		m_warnings.setEnabled(false);
	}
	
	/**
	 * reset error log content and disable button
	 */
	private void resetErrorLog() {
		m_errorLog = null;
		m_errorCounter = 0;
		m_errors.setText(s_logger.localizeMessage("guiButtonViewErrors"));
		m_errors.setEnabled(false);
	}

	/**
	 * log a trace message
	 * @param message message to log
	 */
	@SuppressWarnings("static-access")
	public void logTrace (String message) {
		
		// initialize button and log if necessary
		if (m_traceLog==null) {
			m_traceLog = new ArrayList<String> (s_parameters.MAXLOGLINES+10);
			SwingUtilities.invokeLater (new Runnable() {public void run() { 
		    	m_trace.setEnabled(true); 
			}});
		}

		// add message to log
		m_traceLog.add(message);
		
		// do not exceed maximum number of lines
		while (m_traceLog.size()>s_parameters.MAXLOGLINES) {
			m_traceLog.remove(0);
		}
		
	}
	
	/**
	 * log a warning message
	 * @param message message to log
	 */
	@SuppressWarnings("static-access")
	public void logWarning(String message) {
		
		// initialize button and log if necessary
		if (m_warningLog==null) {
			m_warningLog = new ArrayList<String> (s_parameters.MAXLOGLINES+10);
			SwingUtilities.invokeLater (new Runnable() {public void run() { 
		    	m_warnings.setEnabled(true); 
			}});
		}

		// increment counter
		m_warningCounter++;
		SwingUtilities.invokeLater (new Runnable() {public void run() { 
			m_warnings.setText(new StringBuffer(s_logger.localizeMessage("guiButtonViewWarnings")).append(" (").append(m_warningCounter).append(")").toString()); 
		}});
		
		// add message to log
		m_warningLog.add(message);
		
		// do not exceed maximum number of lines
		while (m_warningLog.size()>s_parameters.MAXLOGLINES) {
			m_warningLog.remove(0);
		}
		
	}
	
	/**
	 * log an error message
	 * @param message message to log
	 */
	@SuppressWarnings("static-access")
	public void logError(String message) {
		
		// initialize button and log if necessary
		if (m_errorLog==null) {
			m_errorLog = new ArrayList<String> (s_parameters.MAXLOGLINES+10);
			SwingUtilities.invokeLater (new Runnable() {public void run() { 
		    	m_errors.setEnabled(true); 
			}});
		}

		// increment counter
		m_errorCounter++;
		SwingUtilities.invokeLater (new Runnable() {public void run() { 
			m_errors.setText(new StringBuffer(s_logger.localizeMessage("guiButtonViewErrors")).append(" (").append(m_errorCounter).append(")").toString()); 
		}});
		
		// add message to log
		m_errorLog.add(message);
		
		// do not exceed maximum number of lines
		while (m_errorLog.size()>s_parameters.MAXLOGLINES) {
			m_errorLog.remove(0);
		}
		
	}

	/**
	 * Disallow the window from being closed
	 */
	public void doNotInterrupt () {
		SwingUtilities.invokeLater (new Runnable() {public void run() { 
			m_labelDoNotInterrupt.setVisible(true);
	        m_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			m_buttonCancel.setEnabled(false);
			m_buttonClose.setEnabled(false);
			m_menuFileClose.setEnabled(false);
			m_menuFileExit.setEnabled(false);
		}});
	}
	
	
}
