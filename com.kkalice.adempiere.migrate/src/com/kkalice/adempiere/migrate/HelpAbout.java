/*
 * Name:		HelpAbout.java
 * Description:	About Box displaying program information
 * Created:		May 15, 2010
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/HelpAbout.java
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

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * About Box displaying program information
 * @author Stefan Christians
 */
public class HelpAbout extends JDialog implements ActionListener {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 7669585194300224053L;
	
	// MEMBERS
	// =======
	
	/** logger */
	private static MigrateLogger s_logger = null;
	
    /** close button */
    private JButton m_buttonClose = new JButton ();
    
    /** layout row counter for dialog layout */
    private int m_rowCounter = 0;
    

	// CONSTRUCTORS
	// ============
	
    /**
     * Default Constructor
     */
	public HelpAbout() {
		// initialize logger for access to resource localizer
		s_logger = MigrateLogger.getLogger();
	}

	// METHODS
	// =======
	
	/**
	 * Create the GUI and show it
	 * @param component the calling component
	 */
	@SuppressWarnings("unchecked")
	public void createAndShowGui (Component component) {
	
        // title
        setTitle(s_logger.localizeMessage("guiAboutTitle"));
        
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
		m_rowCounter = 0;
		
		// logo
		JLabel logo = new JLabel(new ImageIcon(getImage("ADempiere.png")));

        // heading
        JLabel label = new JLabel(s_logger.localizeMessage("guiAboutHeading"));
        label.setFont(new Font("Dialog", Font.BOLD, 14));
        pane.add(label, getHeadingConstraints());
        
        // display additional information if we are running in an Adempiere environment
        try {
			Class ademp = Class.forName("org.compiere.Adempiere", false, this.getClass().getClassLoader());
        	
			// Adempiere Logo image & subtitle
			logo.setIcon(new ImageIcon(getImage("AD10030.png")));
			Field subtitle = ademp.getField("SUB_TITLE");
			logo.setText(subtitle.get(null).toString());
			logo.setHorizontalTextPosition(SwingConstants.CENTER);
			logo.setVerticalTextPosition(SwingConstants.BOTTOM);
			logo.setFont(new Font("Serif", Font.ITALIC, 10));
			logo.setForeground(Color.BLUE);
			
			// Adempiere version
			Field mainVersion = ademp.getField("MAIN_VERSION");
			Field dateVersion = ademp.getField("DATE_VERSION");
			String versionString = new StringBuffer(mainVersion.get(null).toString()).append(" @ ").append(dateVersion.get(null).toString()).toString(); 
			label = new JLabel (versionString);
	        pane.add(label, getVersionConstraints());
			
	        // Adempiere Copyright
	        Field copyright = ademp.getField("COPYRIGHT");
	        label = new JLabel (copyright.get(null).toString());
	        pane.add(label, getAdempCprConstraints());
	        
	        // Adempiere URL
	        Field url = ademp.getField("URL");
	        label = new JLabel (url.get(null).toString());
	        label.setForeground(Color.BLUE);
	        pane.add(label, getUrlConstraints());
	        
		} catch (Exception e) {
			// do nothing if any error occurs trying to access Adempiere class
		}

		// add logo to pane
		// (constraints depend on how many rows were added by above Adempiere information)
        pane.add(logo, getLogoConstraints());

		// program title
		label = new JLabel(s_logger.localizeMessage("guiWindowTitle"));
        label.setFont(new Font("DIALOG", Font.BOLD, 18));
        label.setForeground(Color.RED);
        pane.add(label, getTitleConstraints());
        
        // program description
        label = new JLabel(s_logger.localizeMessage("guiWindowDescription"));
        label.setFont(new Font("Dialog", Font.ITALIC, 12));
        pane.add(label, getDescrConstraints());
        
        //copyright
        JTextArea text = new JTextArea (s_logger.localizeMessage("copyright"));
        text.setFont(new Font("Dialog", Font.PLAIN, 10));
        text.setBackground(label.getBackground());
        pane.add(text, getCopyrConstraints());
        
        // close button
        m_buttonClose = new JButton(s_logger.localizeMessage("guiButtonClose"));
        m_buttonClose.setMnemonic(new Integer(s_logger.localizeMessage("guiButtonCloseMnemonic")));
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
	 * gets constraints used by logo
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getLogoConstraints() {
		return getGBC(0, 0, 1, m_rowCounter, GridBagConstraints.NONE, 0, 0, 10, 10, 0, 10, GridBagConstraints.CENTER, 0, 0);
	}

	/**
	 * gets constraints used by heading label
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getHeadingConstraints() {
		return getGBC(1, m_rowCounter++, 1, 1, GridBagConstraints.NONE, 0, 0, 10, 10, 0, 10, GridBagConstraints.CENTER, 0, 0);
	}

	/**
	 * gets constraints used by version label
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getVersionConstraints() {
		return getGBC(1, m_rowCounter++, 1, 1, GridBagConstraints.NONE, 0, 0, 2, 10, 0, 10, GridBagConstraints.CENTER, 0, 0);
	}

	/**
	 * gets constraints used by adempiere copyright label
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getAdempCprConstraints() {
		return getGBC(1, m_rowCounter++, 1, 1, GridBagConstraints.NONE, 0, 0, 2, 10, 0, 10, GridBagConstraints.CENTER, 0, 0);
	}

	/**
	 * gets constraints used by url label
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getUrlConstraints() {
		return getGBC(1, m_rowCounter++, 1, 1, GridBagConstraints.NONE, 0, 0, 2, 10, 0, 10, GridBagConstraints.CENTER, 0, 0);
	}

	/**
	 * gets constraints used by title label
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getTitleConstraints() {
		return getGBC(0, m_rowCounter++, 2, 1, GridBagConstraints.NONE, 0, 0, 20, 10, 0, 10, GridBagConstraints.CENTER, 0, 0);
	}

	/**
	 * gets constraints used by description label
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getDescrConstraints() {
		return getGBC(0, m_rowCounter++, 2, 1, GridBagConstraints.NONE, 0, 0, 6, 10, 0, 10, GridBagConstraints.CENTER, 0, 0);
	}
	
	/**
	 * gets constraints used by copyright text area
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getCopyrConstraints() {
		return getGBC(0, m_rowCounter++, 2, 1, GridBagConstraints.NONE, 0, 0, 20, 10, 0, 10, GridBagConstraints.CENTER, 0, 0);
	}
	
	/**
	 * gets constraints used by close button
	 * @return GridBagConstraints object
	 */
	private GridBagConstraints getCloseConstraints() {
	    return getGBC(0, m_rowCounter++, 2, 1, GridBagConstraints.NONE, 0, 0, 10, 10, 10, 10, GridBagConstraints.CENTER, 0, 0);
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
