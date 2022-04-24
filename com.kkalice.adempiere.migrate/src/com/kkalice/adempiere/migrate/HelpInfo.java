/*
 * Name:		HelpInfo.java
 * Description:	Help file viewer
 * Created:		May 16, 2010
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/HelpInfo.java
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

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.text.html.HTMLDocument;


/**
 * Help file viewer
 * @author Stefan Christians
 */
public class HelpInfo extends JDialog implements ActionListener {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 7798295720976428064L;

	
	// MEMBERS
	// =======
	
	/** logger */
	private static MigrateLogger s_logger = null;
	/** parameters */
	private static Parameters s_parameters = null;
	/** database engine */
	private static DBEngine s_dbEngine = null;
	
	
    /** close button */
    private JButton m_buttonClose = new JButton ();
    

	// CONSTRUCTORS
	// ============
	
    /**
     * Default Constructor
     */
	public HelpInfo() {
		// initialize logger for access to resource localizer
		s_logger = MigrateLogger.getLogger();
		// initialze parameters for access to default settings
		s_parameters = Parameters.getParameters();
		// initialize DB Engine for access to database vendors
		s_dbEngine = DBEngine.getDBEngine();
	}

	
	// METHODS
	// =======

	/**
	 * Create the GUI and show it
	 * @param component the calling component
	 */
	public void createAndShowGui (Component component) {
	
        // title
        setTitle(s_logger.localizeMessage("guiHelpTitle"));
        
        // load icons
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(getImage("AD16.png"));
        images.add(getImage("AD32.png"));
        setIconImages(images);        

        // content pane
        Container pane = getContentPane();
		Locale locale = Locale.getDefault();
		pane.setComponentOrientation(ComponentOrientation.getOrientation(locale));
		pane.setLayout(new GridBagLayout());
		
		// help text
		JEditorPane info = new JEditorPane();
		info.setEditable (false);
		info.setContentType ("text/html");
		HTMLDocument htmlDoc = (HTMLDocument)info.getEditorKit().createDefaultDocument();
		info.setDocument(htmlDoc);
		info.setText(getHelpText());
		info.setCaretPosition(0);
		
		// scrollable pane for help text
		JScrollPane infoPane = new JScrollPane();
		infoPane.setBorder(BorderFactory.createLoweredBevelBorder());
		infoPane.setPreferredSize(new Dimension(500, 400));
		infoPane.getViewport().add(info, null);
		pane.add(infoPane, getInfoPaneConstraints());

        // close button
        m_buttonClose = new JButton(s_logger.localizeMessage("guiButtonClose"));
        m_buttonClose.setMnemonic(Integer.valueOf(s_logger.localizeMessage("guiButtonCloseMnemonic")));
        m_buttonClose.setIcon(new ImageIcon(getImage("Cancel16.png")));
        m_buttonClose.addActionListener(this);
        pane.add(m_buttonClose, getCloseConstraints());
        
        // show dialog
        pack();
        validate();
        m_buttonClose.requestFocusInWindow();
        setLocationRelativeTo(component);
        setVisible(true);
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
	 * gets constraints used by info pane
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getInfoPaneConstraints() {
	    return getGBC(0, 0, 1, 1, GridBagConstraints.NONE, 0, 0, 10, 10, 0, 10, GridBagConstraints.CENTER, 0, 0);
	}
	
	/**
	 * gets constraints used by close button
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getCloseConstraints() {
	    return getGBC(0, 1, 1, 1, GridBagConstraints.NONE, 0, 0, 10, 10, 10, 10, GridBagConstraints.CENTER, 0, 0);
	}
	
    /**
     * load image from file as resource
     * @param imageName name of the image
     * @return small Adempiere image
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
     * get the help text to display
     * @return help text in HTML format
     */
    @SuppressWarnings("static-access")
	private String getHelpText() {
    	StringBuffer doc = new StringBuffer();

    	// prolog
    	doc.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">");

    	// start of document
    	doc.append("<html>");
    	
    	// document header
    	doc.append("<head>");
    	doc.append("<title>").append(s_logger.localizeMessage("guiWindowTitle")).append("</title>");
    	doc.append("<meta name=\"generator\" content=\"Adempiere Migration Tool\" />");
    	doc.append("<meta name=\"author\" content=\"Stefan Christians\" />");
    	doc.append("<meta name=\"description\" content=\"static help file for Adempiere Migration Tool\" />");
    	doc.append("</head>");
    	
    	// start of body
    	doc.append("<body>");
    	
    	// title
    	doc.append("<h1><font color=\"red\">").append(getHtmlMessage("guiWindowTitle")).append("</font></h1>");
    	doc.append("<b><i>").append(getHtmlMessage("guiWindowDescription")).append("</i></b>");
    	
    	// migration mode
    	doc.append("<div id=\"mode\"><p>");
    	doc.append("<h2>").append(getHtmlMessage("guiModeTitle")).append("</h2>");
    	doc.append("<b>").append(getHtmlMessage("guiModeTip")).append("</b>");
    	doc.append("<p>").append(getHtmlMessage("guiModeHelp")).append("</p>");
    	doc.append("<dl>");
    	doc.append("<dt><b>").append(getHtmlMessage("guiModeUpgrade")).append("</b></dt>");
    		doc.append("<dd>");
    		doc.append("<b>").append(getHtmlMessage("guiModeUpgradeTip")).append("</b>");
        	if (s_parameters.isDefaultMigrationIsUpgrade())
        		doc.append(getDefaultMarker());
    		doc.append("<p>").append(getHtmlMessage("guiModeUpgradeHelp")).append("</p>");
    		doc.append("</dd>");
    	doc.append("<dt><b>").append(getHtmlMessage("guiModeTransfer")).append("</b></dt>");
    		doc.append("<dd>");
    		doc.append("<b>").append(getHtmlMessage("guiModeTransferTip")).append("</b>");
    		if (!s_parameters.isDefaultMigrationIsUpgrade())
    			doc.append(getDefaultMarker());
    		doc.append("<p>").append(getHtmlMessage("guiModeTransferHelp")).append("</p>");
    		doc.append("</dd>");
    	doc.append("</dl>");
    	doc.append("</p></div>");
    	
    	// options
    	doc.append("<div id=\"options\"><p>");
    	doc.append("<h2>").append(getHtmlMessage("guiOptionsTitle")).append("</h2>");
    	doc.append("<b>").append(getHtmlMessage("guiOptionsTip")).append("</b>");
    	doc.append("<p>").append(getHtmlMessage("guiOptionsHelp")).append("</p>");
    	doc.append("<dl>");
    	// log level
    	doc.append("<dt><b>").append(getHtmlMessage("guiOptionLogLevel")).append("</b></dt>");
    		doc.append("<dd>");
    		doc.append("<b>").append(getHtmlMessage("guiOptionLogLevelTip")).append("</b>");
    		doc.append("<p>").append(getHtmlMessage("guiOptionLogLevelHelp"));
    			doc.append("<ul>");
    				doc.append("<li>").append(logLevelToDescription(Level.OFF)).append("</li>");
    				doc.append("<li>").append(logLevelToDescription(Level.SEVERE)).append("</li>");
    				doc.append("<li>").append(logLevelToDescription(Level.WARNING)).append("</li>");
    				doc.append("<li>").append(logLevelToDescription(Level.INFO)).append("</li>");
    				doc.append("<li>").append(logLevelToDescription(Level.CONFIG)).append("</li>");
    				doc.append("<li>").append(logLevelToDescription(Level.FINE)).append("</li>");
    				doc.append("<li>").append(logLevelToDescription(Level.FINER)).append("</li>");
    				doc.append("<li>").append(logLevelToDescription(Level.FINEST)).append("</li>");
    				doc.append("<li>").append(logLevelToDescription(Level.ALL)).append("</li>");
    			doc.append("</ul></p>");
    		doc.append("</dd>");
    	// attempt translations
    	doc.append("<dt><b>").append(getHtmlMessage("guiOptionAttemptTranslations")).append("</b></dt>");
   			doc.append("<dd>");
    		doc.append("<b>").append(getHtmlMessage("guiOptionAttemptTranslationsTip")).append("</b>");
    		doc.append(getLogicalDefaultStatement(s_parameters.isDefaultAttemptTranslation()));
    		doc.append("<p>").append(getHtmlMessage("guiOptionAttemptTranslationsHelp")).append("</p>");
    		doc.append("</dd>");
		// preserve Garden World
		doc.append("<dt><b>").append(getHtmlMessage("guiOptionPreserveGardenWord")).append("</b></dt>");
			doc.append("<dd>");
			doc.append("<b>").append(getHtmlMessage("guiOptionPreserveGardenWorldTip")).append("</b>");
			doc.append(getLogicalDefaultStatement(s_parameters.isDefaultPreserveGardenWorld()));
			doc.append("<p>").append(getHtmlMessage("guiOptionPreserveGardenWorldHelp")).append("</p>");
			doc.append("</dd>");
		// truncate temporary tables
		doc.append("<dt><b>").append(getHtmlMessage("guiOptionTruncateTemporaryTables")).append("</b></dt>");
			doc.append("<dd>");
			doc.append("<b>").append(getHtmlMessage("guiOptionTruncateTemporaryTablesTip")).append("</b>");
			doc.append(getLogicalDefaultStatement(s_parameters.isTruncateTemporaryTables()));
			doc.append("<p>").append(getHtmlMessage("guiOptionTruncateTemporaryTablesHelp")).append("</p>");
			doc.append("</dd>");
		// preserve Log data
		doc.append("<dt><b>").append(getHtmlMessage("guiOptionPreserveLogs")).append("</b></dt>");
			doc.append("<dd>");
			doc.append("<b>").append(getHtmlMessage("guiOptionPreserveLogsTip")).append("</b>");
			doc.append(getLogicalDefaultStatement(s_parameters.isDefaultPreserveLogs()));
			doc.append("<p>").append(getHtmlMessage("guiOptionPreserveLogsHelp")).append("</p>");
			doc.append("</dd>");			
		// preserve unreferenced Elements
		doc.append("<dt><b>").append(getHtmlMessage("guiOptionPreserveUnreferencedElements")).append("</b></dt>");
			doc.append("<dd>");
			doc.append("<b>").append(getHtmlMessage("guiOptionPreserveUnreferencedElementsTip")).append("</b>");
			doc.append(getLogicalDefaultStatement(s_parameters.isDefaultPreserveUnreferencedElements()));
			doc.append("<p>").append(getHtmlMessage("guiOptionPreserveUnreferencedElementsHelp")).append("</p>");
			doc.append("</dd>");			
		// preserve days
		doc.append("<dt><b>").append(getHtmlMessage("guiOptionPreserveDays")).append("</b></dt>");
			doc.append("<dd>");
			doc.append("<b>").append(getHtmlMessage("guiOptionPreserveDaysTip")).append("</b>");
			doc.append(getHtmlMessage(String.valueOf(s_parameters.getDefaultPreserveDays())));
			doc.append("<p>").append(getHtmlMessage("guiOptionPreserveDaysHelp")).append("</p>");
			doc.append("</dd>");
		// preserve table IDs
    	doc.append("<dt><b>").append(getHtmlMessage("guiOptionPreserveTableIDs")).append("</b></dt>");
   			doc.append("<dd>");
    		doc.append("<b>").append(getHtmlMessage("guiOptionPreserveTableIDsTip")).append("</b>");
    		doc.append(getLogicalDefaultStatement(s_parameters.isDefaultPreserveTableID()));
    		doc.append("<p>").append(getHtmlMessage("guiOptionPreserveTableIDsHelp")).append("</p>");
    		doc.append("</dd>");
    	// drop source
    	doc.append("<dt><b>").append(getHtmlMessage("guiOptionDropSource")).append("</b></dt>");
   			doc.append("<dd>");
    		doc.append("<b>").append(getHtmlMessage("guiOptionDropSourceTip")).append("</b>");
    		doc.append(getLogicalDefaultStatement(s_parameters.isDefaultDropSource()));
    		doc.append("<p>").append(getHtmlMessage("guiOptionDropSourceHelp")).append("</p>");
    		doc.append("</dd>");
    	// optimize database
    	doc.append("<dt><b>").append(getHtmlMessage("guiOptionOptimizeDatabase")).append("</b></dt>");
       		doc.append("<dd>");
    		doc.append("<b>").append(getHtmlMessage("guiOptionOptimizeDatabaseTip")).append("</b>");
    		doc.append(getLogicalDefaultStatement(s_parameters.isDefaultOptimizeDatabase()));
    		doc.append("<p>").append(getHtmlMessage("guiOptionOptimizeDatabaseHelp")).append("</p>");
    		doc.append("</dd>");
    	doc.append("</dl>");
    	doc.append("</p></div>");
    	
    	// parameters
    	doc.append("<div id=\"parameters\"><p>");
    	doc.append("<h2>").append(getHtmlMessage("guiParametersTitle")).append("</h2>");
    	doc.append("<b>").append(getHtmlMessage("guiParametersTip")).append("</b>");
    	doc.append("<p>").append(getHtmlMessage("guiParametersHelp")).append("</p>");
    	doc.append("<dl>");
    	// version
    	doc.append("<dt><b>").append(getHtmlMessage("guiVersion")).append("</b></dt>");
    		doc.append("<dd>");
    		doc.append("<b>").append(getHtmlMessage("guiVersionTip")).append("</b>");
   			doc.append("<p>").append(getHtmlMessage("guiVersionHelp")).append("</p>");
   			doc.append("</dd>");
   		// vendor
   		doc.append("<dt><b>").append(getHtmlMessage("guiVendor")).append("</b></dt>");
    		doc.append("<dd>");
    		doc.append("<b>").append(getHtmlMessage("guiVendorTip")).append("</b>");
   			doc.append("<p>").append(getHtmlMessage("guiVendorHelp"));
   			doc.append(getVendorList()).append("</p>");
   			doc.append("</dd>");
   		// host
   		doc.append("<dt><b>").append(getHtmlMessage("guiHost")).append("</b></dt>");
    		doc.append("<dd>");
    		doc.append("<b>").append(getHtmlMessage("guiHostTip")).append("</b>");
    		doc.append(getStringDefaultStatement(s_parameters.getDefaultHost()));
   			doc.append("<p>").append(getHtmlMessage("guiHostHelp")).append("</p>");
   			doc.append("</dd>");
   		// port
   		doc.append("<dt><b>").append(getHtmlMessage("guiPort")).append("</b></dt>");
    		doc.append("<dd>");
    		doc.append("<b>").append(getHtmlMessage("guiPortTip")).append("</b>");
    		doc.append(getNumberDefaultStatement(s_parameters.getDefaultPort()));
   			doc.append("<p>").append(getHtmlMessage("guiPortHelp")).append("</p>");
   			doc.append("</dd>");
   		// user
   		doc.append("<dt><b>").append(getHtmlMessage("guiUser")).append("</b></dt>");
    		doc.append("<dd>");
    		doc.append("<b>").append(getHtmlMessage("guiUserTip")).append("</b>");
    		doc.append(getStringDefaultStatement(s_parameters.getDefaultSourceUser(), s_parameters.getDefaultTargetUser()));
   			doc.append("<p>").append(getHtmlMessage("guiUserHelp")).append("</p>");
   			doc.append("</dd>");
   		// password
   		doc.append("<dt><b>").append(getHtmlMessage("guiPassword")).append("</b></dt>");
    		doc.append("<dd>");
    		doc.append("<b>").append(getHtmlMessage("guiPasswordTip")).append("</b>");
    		doc.append(getStringDefaultStatement(s_parameters.getDefaultSourcePasswd(), s_parameters.getDefaultTargetPasswd()));
   			doc.append("<p>").append(getHtmlMessage("guiPasswordHelp")).append("</p>");
   			doc.append("</dd>");
   		// system user
   	   	doc.append("<dt><b>").append(getHtmlMessage("guiSystemUser")).append("</b></dt>");
   	    	doc.append("<dd>");
   	    	doc.append("<b>").append(getHtmlMessage("guiSystemUserTip")).append("</b>");
   	    	doc.append(getStringDefaultStatement(s_parameters.getDefaultSystemUser()));
   	   		doc.append("<p>").append(getHtmlMessage("guiSystemUserHelp")).append("</p>");
   	   		doc.append("</dd>");
   	   	// system password
   	   	doc.append("<dt><b>").append(getHtmlMessage("guiSystemPassword")).append("</b></dt>");
   	    	doc.append("<dd>");
   	    	doc.append("<b>").append(getHtmlMessage("guiSystemPasswordTip")).append("</b>");
   	    	doc.append(getStringDefaultStatement(s_parameters.getDefaultSystemPasswd()));
   	   		doc.append("<p>").append(getHtmlMessage("guiSystemPasswordHelp")).append("</p>");
   	   		doc.append("</dd>");
   	   	// database name
   	   	doc.append("<dt><b>").append(getHtmlMessage("guiName")).append("</b></dt>");
   	   		doc.append("<dd>");
   	   		doc.append("<b>").append(getHtmlMessage("guiNameTip")).append("</b>");
   	   		doc.append(getStringDefaultStatement(s_parameters.getDefaultSourceName(), s_parameters.getDefaultTargetName()));
   			doc.append("<p>").append(getHtmlMessage("guiNameHelp")).append("</p>");
   			doc.append("</dd>");
   	   	// url
   		doc.append("<dt><b>").append(getHtmlMessage("guiUrl")).append("</b></dt>");
   	    	doc.append("<dd>");
   	    	doc.append("<b>").append(getHtmlMessage("guiUrlTip")).append("</b>");
   	   		doc.append("<p>").append(getHtmlMessage("guiUrlHelp")).append("</p>");
   	   		doc.append("</dd>");
   	   	// catalog
   	   	doc.append("<dt><b>").append(getHtmlMessage("guiCatalog")).append("</b></dt>");
   	    	doc.append("<dd>");
   	    	doc.append("<b>").append(getHtmlMessage("guiCatalogTip")).append("</b>");
   	   		doc.append("<p>").append(getHtmlMessage("guiCatalogHelp")).append("</p>");
   	   		doc.append("</dd>");
   	   	// schema
   	   	doc.append("<dt><b>").append(getHtmlMessage("guiSchema")).append("</b></dt>");
   	    	doc.append("<dd>");
   	    	doc.append("<b>").append(getHtmlMessage("guiSchemaTip")).append("</b>");
   	   		doc.append("<p>").append(getHtmlMessage("guiSchemaHelp")).append("</p>");
   	   		doc.append("</dd>");
   	   	// reset
   	   	doc.append("<dt><b>").append(getHtmlMessage("guiReset")).append("</b></dt>");
   	   	   	doc.append("<dd>");
   	   	   	doc.append("<b>").append(getHtmlMessage("guiResetTip")).append("</b>");
   	   	   	doc.append("<p>").append(getHtmlMessage("guiResetHelp")).append("</p>");
   	   	  	doc.append("</dd>");
    	doc.append("</dl>");
    	doc.append("</p></div>");
    	
    	// status
    	doc.append("<div id=\"status\"><p>");
    	doc.append("<h2>").append(getHtmlMessage("guiStatusTitle")).append("</h2>");
    	doc.append("<b>").append(getHtmlMessage("guiStatusTip")).append("</b>");
    	doc.append("<p>").append(getHtmlMessage("guiStatusHelp")).append("</p>");
    	doc.append("<dl>");
    	// step
   	   	doc.append("<dt><b>").append(getHtmlMessage("guiStep")).append("</b></dt>");
	   	   	doc.append("<dd>");
	   	   	doc.append("<b>").append(getHtmlMessage("guiStepTip")).append("</b>");
	   	   	doc.append("<p>").append(getHtmlMessage("guiStepHelp"));
	   	   		doc.append("<ul>");
					doc.append("<li>").append(getHtmlMessage("migrateConnectDatabases")).append("</li>");
					doc.append("<li>").append(getHtmlMessage("migrateLoadMetaData")).append("</li>");
					doc.append("<li>").append(getHtmlMessage("migrateSynchronize")).append("</li>");
					doc.append("<li>").append(getHtmlMessage("migrateCloseDatabases")).append("</li>");
				doc.append("</ul></p>");
	   	  	doc.append("</dd>");
    	// action
   	   	doc.append("<dt><b>").append(getHtmlMessage("guiAction")).append("</b></dt>");
	   	   	doc.append("<dd>");
	   	   	doc.append("<b>").append(getHtmlMessage("guiActionTip")).append("</b>");
	   	   	doc.append("<p>").append(getHtmlMessage("guiActionHelp")).append("</p>");
	   	  	doc.append("</dd>");
    	// detail
   	   	doc.append("<dt><b>").append(getHtmlMessage("guiDetail")).append("</b></dt>");
	   	   	doc.append("<dd>");
	   	   	doc.append("<b>").append(getHtmlMessage("guiDetailTip")).append("</b>");
	   	   	doc.append("<p>").append(getHtmlMessage("guiDetailHelp")).append("</p>");
	   	  	doc.append("</dd>");
    	doc.append("</dl>");
    	doc.append("</p></div>");

    	// buttons
    	doc.append("<div id=\"buttons\"><p>");
    	doc.append("<h2>").append(getHtmlMessage("guiButtonsTitle")).append("</h2>");
    	doc.append("<b>").append(getHtmlMessage("guiButtonsTip")).append("</b>");
    	doc.append("<p>").append(getHtmlMessage("guiButtonsHelp")).append("</p>");
    	doc.append("<dl>");
    	// start
   	   	doc.append("<dt><b>").append(getHtmlMessage("guiButtonStart")).append("</b></dt>");
   	   		doc.append("<dd>");
   	   		doc.append("<b>").append(getHtmlMessage("guiButtonStartTip")).append("</b>");
   	   		doc.append("<p>").append(getHtmlMessage("guiButtonStartHelp")).append("</p>");
   	   		doc.append("</dd>");
    	// cancel
   	   	doc.append("<dt><b>").append(getHtmlMessage("guiButtonCancel")).append("</b></dt>");
   	   		doc.append("<dd>");
   	   		doc.append("<b>").append(getHtmlMessage("guiButtonCancelTip")).append("</b>");
   	   		doc.append("<p>").append(getHtmlMessage("guiButtonCancelHelp")).append("</p>");
   	   		doc.append("</dd>");
    	// close
   	   	doc.append("<dt><b>").append(getHtmlMessage("guiButtonClose")).append("</b></dt>");
   	   		doc.append("<dd>");
   	   		doc.append("<b>").append(getHtmlMessage("guiButtonCloseTip")).append("</b>");
   	   		doc.append("<p>").append(getHtmlMessage("guiButtonCloseHelp")).append("</p>");
   	   		doc.append("</dd>");
    	// trace
   	   	doc.append("<dt><b>").append(getHtmlMessage("guiButtonViewTrace")).append("</b></dt>");
   	   		doc.append("<dd>");
   	   		doc.append("<b>").append(getHtmlMessage("guiButtonViewTraceTip")).append("</b>");
   	   		String message = getHtmlMessage("guiButtonViewTraceHelp");
   	   		message = message.replaceAll("\\{0\\}", Integer.valueOf(s_parameters.MAXLOGLINES).toString());
   	   		doc.append("<p>").append(message).append("</p>");
   	   		doc.append("</dd>");
    	// warnings
   	   	doc.append("<dt><b>").append(getHtmlMessage("guiButtonViewWarnings")).append("</b></dt>");
   	   		doc.append("<dd>");
   	   		doc.append("<b>").append(getHtmlMessage("guiButtonViewWarningsTip")).append("</b>");
   	   		message = getHtmlMessage("guiButtonViewWarningsHelp");
   	   		message = message.replaceAll("\\{0\\}", Integer.valueOf(s_parameters.MAXLOGLINES).toString());
   	   		doc.append("<p>").append(message).append("</p>");
   	   		doc.append("</dd>");
    	// errors
   	   	doc.append("<dt><b>").append(getHtmlMessage("guiButtonViewErrors")).append("</b></dt>");
   	   		doc.append("<dd>");
   	   		doc.append("<b>").append(getHtmlMessage("guiButtonViewErrorsTip")).append("</b>");
   	   		message = getHtmlMessage("guiButtonViewErrorsHelp");
   	   		message = message.replaceAll("\\{0\\}", Integer.valueOf(s_parameters.MAXLOGLINES).toString());
   	   		doc.append("<p>").append(message).append("</p>");
   	   		doc.append("</dd>");
    	doc.append("</dl>");
    	doc.append("</p></div>");
    	
    	// end of body
    	doc.append("</body>");
    	
    	// end of document
    	doc.append("</html>");
    	
    	return doc.toString();
    }
    
    /**
     * get a localized message formatted for HTML output
     * @param message the messge to be localized and formatted
     * @return localized message text formatted for HTML output
     */
    private String getHtmlMessage(String message) {
    	String html = s_logger.localizeMessage(message);
    	
    	// tabs
    	if (html.contains("\t")) {

    		// first newline before first tab marks start of table
    		int firstTab = html.indexOf("\t");
    		String preText = html.substring(0, firstTab);
    		int firstNL = preText.lastIndexOf("\n");
    		preText = preText.substring(0, firstNL);

    		// first newline before last tab marks end of table
    		int lastTab = html.lastIndexOf("\t");
    		int lastNL = html.indexOf("\n", lastTab);
    		String postText = html.substring(lastNL+1);
    		String tabText = html.substring(firstNL+1, lastNL);
    		
    		// table columns
    		tabText = tabText.replaceAll("\t", "</td><td valign=\"top\">");
    		// table rows
    		tabText = tabText.replaceAll("\n", "</td></tr><tr><td valign=\"top\">");
    		// surrounding table
    		tabText = new StringBuffer("<table><tr><td valign=\"top\">").append(tabText).append("</td></tr></table>").toString();
    		
    		html = new StringBuffer(preText).append(tabText).append(postText).toString();
    	}
    	
    	// newline
    	html = html.replaceAll("\n", "<br />");
    	
    	
    	return html;
    }
    
    /**
     * get a standard default marker to be used in HTML output
     * @return a default marker in HTML format
     */
    private String getDefaultMarker() {
    	return new StringBuffer(" <font color=\"blue\"><i>(").append(s_logger.localizeMessage("guiDefault")).append(")</i></font>").toString();    	
    }
    
    /**
     * get a statement showing yes or no as default value to be used in HTML output
     * @param value boolean value (true or false)
     * @return a default statement in HTML format
     */
    private String getLogicalDefaultStatement(boolean value) {
    	String yesNo = s_logger.localizeMessage("guiNo");
    	if (value)
    		yesNo = s_logger.localizeMessage("guiYes");
    	return new StringBuffer(" <font color=\"blue\"><i>(").append(s_logger.localizeMessage("guiDefault")).append(" = ").append(yesNo).append(")</i></font>").toString();
    }
    
    /**
     * get a statement showing a string as default value to be used in HTML output
     * @param value String to use as default
     * @return a default statement in HTML format
     */
    private String getStringDefaultStatement(String value) {
    	if (value==null)
    		value="";
    	return new StringBuffer(" <font color=\"blue\"><i>(").append(s_logger.localizeMessage("guiDefault")).append(" = \"").append(value).append("\")</i></font>").toString();
    }

    /**
     * get a statement showing two strings as default values for source and target to be used in HTML output
     * @param sourceValue String to use as default for source
     * @param targetValue String to use as default for target
     * @return a default statement in HTML format
     */
    private String getStringDefaultStatement(String sourceValue, String targetValue) {
    	if (sourceValue==null)
    		sourceValue="";
    	if (targetValue==null)
    		targetValue="";
    	String source = s_logger.localizeMessage("source");
    	String target = s_logger.localizeMessage("target");
    	return new StringBuffer("<br /><font color=\"blue\"><i>(")
    		.append(source).append(" ").append(s_logger.localizeMessage("guiDefault"))
    		.append(" = \"").append(sourceValue).append("\")<br />(")
    		.append(target).append(" ").append(s_logger.localizeMessage("guiDefault"))
    		.append(" = \"").append(targetValue).append("\")")
    		.append("</i></font>").toString();
    }

    /**
     * get a statement showing a number as default value to be used in HTML output
     * @param value Number to use as default
     * @return a default statement in HTML format
     */
    private String getNumberDefaultStatement(String value) {
    	if (value==null)
    		value="";
    	return new StringBuffer(" <font color=\"blue\"><i>(").append(s_logger.localizeMessage("guiDefault")).append(" = ").append(value).append(")</i></font>").toString();
    }

    /**
	 * convert log level to descriptive localized text
	 * @param logLevel the log level
	 * @return String representation of log level
	 */
	@SuppressWarnings("static-access")
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

		if (logLevel.equals(s_parameters.getDefaultLogLevel())) {
			result = new StringBuffer(result).append(getDefaultMarker()).toString();
		}
		
		return result;
	}
	
	/**
	 * create list of database vendors for output in HTML
	 * @return HTML List containing supported database vendors
	 */
	@SuppressWarnings("static-access")
	private String getVendorList() {
		StringBuffer vendors = new StringBuffer("<ul>");

		for (String vendorName : s_dbEngine.getVendorList()) {
			vendors.append("<li>").append(vendorName);
			if (vendorName.equalsIgnoreCase(s_parameters.getDefaultVendor()))
				vendors.append(getDefaultMarker());
			vendors.append("</li>");
		}
		
		vendors.append("</ul>");
		
		return vendors.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		
		Object eventObj = e.getSource();
		
		// only process if a worthy action triggered this event
		if (eventObj == m_buttonClose) {
			dispose();
		}
		
	}
	
}
