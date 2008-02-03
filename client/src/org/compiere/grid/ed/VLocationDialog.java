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
package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import org.compiere.apps.*;
import org.compiere.model.*;
import org.compiere.swing.*;
import org.compiere.util.*;

import org.adempiere.interfaces.*;
import org.adempiere.model.*;

/**
 *	Dialog to enter Location Info (Address)
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VLocationDialog.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>BF [ 1831060 ] Location dialog should use Address1, Address2 ... elements
 */
public class VLocationDialog extends CDialog 
	implements ActionListener
{
	
	/** Lookup result */
	//private Object[][] data = null;

	/** Lookup result header */
	private Object[] header = null;

	//private int m_WindowNo = 0;

	/**
	 *	Constructor
	 *
	 * @param frame parent
	 * @param title title (field name)
	 * @param location Model Location
	 */
	public VLocationDialog (Frame frame, String title, MLocation location)
	{
		super(frame, title, true);
		//m_WindowNo = WindowNo;
		try
		{
			jbInit();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, ex.getMessage());
		}
		m_location = location;
		if (m_location == null)
			m_location = new MLocation (Env.getCtx(), 0, null);
		//	Overwrite title	
		if (m_location.getC_Location_ID() == 0)
			setTitle(Msg.getMsg(Env.getCtx(), "LocationNew"));
		else
			setTitle(Msg.getMsg(Env.getCtx(), "LocationUpdate"));

		//	Current Country
		MCountry.setDisplayLanguage(Env.getAD_Language(Env.getCtx()));
		fCountry = new CComboBox(MCountry.getCountries(Env.getCtx()));
		fCountry.setSelectedItem(m_location.getCountry());
		m_origCountry_ID = m_location.getC_Country_ID();
		//	Current Region
		fRegion = new CComboBox(MRegion.getRegions(Env.getCtx(), m_origCountry_ID));
		if (m_location.getCountry().isHasRegion())
			lRegion.setText(m_location.getCountry().getRegionName());	//	name for region
		fRegion.setSelectedItem(m_location.getRegion());
		//
		initLocation();
		fCountry.addActionListener(this);
		fOnline.addActionListener(this);
		AEnv.positionCenterWindow(frame, this);
		
		
	}	//	VLocationDialog

	private boolean 	m_change = false;
	private MLocation	m_location;
	private int			m_origCountry_ID;
	private int			s_oldCountry_ID = 0;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VLocationDialog.class);

	private CPanel panel = new CPanel();
	private CPanel mainPanel = new CPanel();
	private CPanel southPanel = new CPanel();
	private BorderLayout panelLayout = new BorderLayout();
	private GridBagLayout gridBagLayout = new GridBagLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private BorderLayout southLayout = new BorderLayout();
	//
	private CLabel		lAddress1   = new CLabel(Msg.getElement(Env.getCtx(), "Address1"));
	private CLabel		lAddress2   = new CLabel(Msg.getElement(Env.getCtx(), "Address2"));
	private CLabel		lAddress3   = new CLabel(Msg.getElement(Env.getCtx(), "Address3"));
	private CLabel		lAddress4   = new CLabel(Msg.getElement(Env.getCtx(), "Address4"));
	private CLabel		lCity       = new CLabel(Msg.getMsg(Env.getCtx(), "City"));
	private CLabel		lCountry    = new CLabel(Msg.getMsg(Env.getCtx(), "Country"));
	private CLabel		lRegion     = new CLabel(Msg.getMsg(Env.getCtx(), "Region"));
	private CLabel		lPostal     = new CLabel(Msg.getMsg(Env.getCtx(), "Postal"));
	private CLabel		lPostalAdd  = new CLabel(Msg.getMsg(Env.getCtx(), "PostalAdd"));
	private CLabel		lOnline		= new CLabel("");		// dummy to use addLine without error....
	private CTextField	fAddress1 = new CTextField(20);		//	length=60
	private CTextField	fAddress2 = new CTextField(20);		//	length=60
	private CTextField	fAddress3 = new CTextField(20);		//	length=60
	private CTextField	fAddress4 = new CTextField(20);		//	length=60
	private CTextField	fCity  = new CTextField(15);		//	length=60
	private CComboBox	fCountry;
	private CComboBox	fRegion;
	private CTextField	fPostal = new CTextField(5);		//	length=10
	private CTextField	fPostalAdd = new CTextField(5);		//	length=10
	private CButton 	fOnline = new CButton();			
	//
	private GridBagConstraints gbc = new GridBagConstraints();
	private Insets labelInsets = new Insets(2,15,2,0);		// 	top,left,bottom,right
	private Insets fieldInsets = new Insets(2,5,2,10);

	/**
	 *	Static component init
	 *  @throws Exception
	 */
	void jbInit() throws Exception
	{
		panel.setLayout(panelLayout);
		southPanel.setLayout(southLayout);
		mainPanel.setLayout(gridBagLayout);
		panelLayout.setHgap(5);
		panelLayout.setVgap(10);
		getContentPane().add(panel);
		panel.add(mainPanel, BorderLayout.CENTER);
		panel.add(southPanel, BorderLayout.SOUTH);
		southPanel.add(confirmPanel, BorderLayout.NORTH);
		//
		confirmPanel.addActionListener(this);
	}	//	jbInit

	/**
	 *	Dynanmic Init & fill fields - Called when Country changes!
	 */
	private void initLocation()
	{
		MCountry country = m_location.getCountry();
		log.fine(country.getName() + ", Region=" + country.isHasRegion() + " " + country.getDisplaySequence()
			+ ", C_Location_ID=" + m_location.getC_Location_ID());
		//	new Region
		if (m_location.getC_Country_ID() != s_oldCountry_ID && country.isHasRegion())
		{
			fRegion = new CComboBox(MRegion.getRegions(Env.getCtx(), country.getC_Country_ID()));
			if (m_location.getRegion() != null)
				fRegion.setSelectedItem(m_location.getRegion());
			lRegion.setText(country.getRegionName());
			s_oldCountry_ID = m_location.getC_Country_ID();
		}

		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridy = 0;			//	line
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.insets = fieldInsets;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0;
		gbc.weighty = 0;

		mainPanel.add(Box.createVerticalStrut(5), gbc);    	//	top gap

		int line = 1;
		addLine(line++, lAddress1, fAddress1);
		addLine(line++, lAddress2, fAddress2);
		addLine(line++, lAddress3, fAddress3);
		addLine(line++, lAddress4, fAddress4);

		//  sequence of City Postal Region - @P@ @C@ - @C@, @R@ @P@
		String ds = country.getDisplaySequence();
		if (ds == null || ds.length() == 0)
		{
			log.log(Level.SEVERE, "DisplaySequence empty - " + country);
			ds = "";	//	@C@,  @P@
		}
		StringTokenizer st = new StringTokenizer(ds, "@", false);
		while (st.hasMoreTokens())
		{
			String s = st.nextToken();
			if (s.startsWith("C"))
				addLine(line++, lCity, fCity);
			else if (s.startsWith("P"))
				addLine(line++, lPostal, fPostal);
			else if (s.startsWith("A"))
				addLine(line++, lPostalAdd, fPostalAdd);
			else if (s.startsWith("R") && m_location.getCountry().isHasRegion())
				addLine(line++, lRegion, fRegion);
		}
		
		
		addLine(line++, lOnline, fOnline);
		
		//  Country Last
		addLine(line++, lCountry, fCountry);

		//	Fill it
		if (m_location.getC_Location_ID() != 0)
		{
			fAddress1.setText(m_location.getAddress1());
			fAddress2.setText(m_location.getAddress2());
			fAddress3.setText(m_location.getAddress3());
			fAddress4.setText(m_location.getAddress4());
			fCity.setText(m_location.getCity());
			fPostal.setText(m_location.getPostal());
			fPostalAdd.setText(m_location.getPostal_Add());
			fOnline.setText(Msg.getMsg(Env.getCtx(), "Online"));
			if (m_location.getCountry().isHasRegion())
			{
				lRegion.setText(m_location.getCountry().getRegionName());
				fRegion.setSelectedItem(m_location.getRegion());
			}
			
			// disable online if this country doesn't have post code lookup
			if (m_location.getCountry().isPostcodeLookup()) {
				fOnline.setEnabled(true);
				fOnline.setVisible(true);
			}
			else {
				fOnline.setEnabled(false);
				fOnline.setVisible(false);
			}
			
			fCountry.setSelectedItem(country);
		}
		//	Update UI
		pack();
	}	//	initLocation

	/**
	 *	Add Line to screen
	 *
	 *  @param line line number (zero based)
	 *  @param label label
	 *  @param field field
	 */
	private void addLine(int line, JLabel label, JComponent field)
	{
		gbc.gridy = line;
		//	label
		gbc.insets = labelInsets;
		gbc.gridx = 0;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		mainPanel.add(label, gbc);
		//	Field
		gbc.insets = fieldInsets;
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = fieldInsets;
		mainPanel.add(field, gbc);
		
	}	//	addLine


	/**
	 *	ActionListener
	 *  @param e ActionEvent
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			action_OK();
			m_change = true;
			dispose();
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
		{
			m_change = false;
			dispose();
		}

		//	Country Changed - display in new Format
		else if (e.getSource() == fCountry)
		{
			//	Modifier for Mouse selection is 16  - for any key selection 0
			MCountry c = (MCountry)fCountry.getSelectedItem();
			m_location.setCountry(c);
			
			// refresh online button for new country
			if (c.isPostcodeLookup()) {
				fOnline.setEnabled(true);
				fOnline.setVisible(true);
			}
			else {
				fOnline.setEnabled(false);
				fOnline.setVisible(false);
			}
			
			// update the region name if regions are enabled for this country
			if (c.isHasRegion())
			{
				lRegion.setText(c.getRegionName());
				fRegion.setSelectedItem(m_location.getRegion());
				
				// TODO: fix bug that occurs when the new region name is shorter than the old region name
			}
			
			//			refresh
			mainPanel.removeAll();
			
			initLocation();
			fCountry.requestFocus();	//	allows to use Keybord selection
		}
		else if (e.getSource() == fOnline)
		{
			
			// check to see if we have a postcode lookup plugin for this country
			MCountry c = (MCountry)fCountry.getSelectedItem();
			if (c.isPostcodeLookup())
			{
				lookupPostcode(c, fPostal.getText());
			}
		}
	}	//	actionPerformed

	/**
	 * 	OK - check for changes (save them) & Exit
	 */
	private void action_OK()
	{
		m_location.setAddress1(fAddress1.getText());
		m_location.setAddress2(fAddress2.getText());
		m_location.setAddress3(fAddress3.getText());
		m_location.setAddress4(fAddress4.getText());
		m_location.setCity(fCity.getText());
		m_location.setPostal(fPostal.getText());
		m_location.setPostal_Add(fPostalAdd.getText());
		//  Country/Region
		MCountry c = (MCountry)fCountry.getSelectedItem();
		m_location.setCountry(c);
		if (m_location.getCountry().isHasRegion())
		{
			MRegion r = (MRegion)fRegion.getSelectedItem();
			m_location.setRegion(r);
		}
		else
			m_location.setC_Region_ID(0);
		//	Save changes
		m_location.save();
	}	//	actionOK

	/**
	 *	Get result
	 *  @return true, if changed
	 */
	public boolean isChanged()
	{
		return m_change;
	}	//	getChange

	/**
	 * 	Get edited Value (MLocation)
	 *	@return location
	 */
	public MLocation getValue()
	{
		return m_location;
	}	//	getValue
	/**
	 * lookupPostcode
	 * 
	 * 
	 * @param country
	 * @param postcode
	 * @return
	 */
	private String lookupPostcode(MCountry country, String postcode)
	{
		// Initialise the lookup class.
		PostcodeLookupInterface pcLookup = null;
		try {
			PostcodeLookupInterface pcLookupTmp = (PostcodeLookupInterface) Class
					.forName(country.getLookupClassName()).newInstance();
			pcLookup = pcLookupTmp.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return "lookupAddress(): " + e.getMessage();
		}
		
		// remove any spaces from the postcode and convert to upper case
		postcode = postcode.replaceAll(" ", "").toUpperCase();
		log.fine("Looking up postcode: " + postcode);
		
		// Lookup postcode on server.
		pcLookup.setServerUrl(country.getLookupUrl());
		pcLookup.setClientID(country.getLookupClientID());
		pcLookup.setPassword(country.getLookupPassword());
		if (pcLookup.lookupPostcode(postcode)==1){
			// Success
			fillLocation(pcLookup.getPostCodeData(), country);
			fAddress1.requestFocusInWindow();
		} else
			return "Postcode Lookup Error";
		
		return "";
	}
		/**
		 * Fills the location field using the information retrieved from postcode
		 * servers.
		 * 
		 * @param ctx
		 *            Context
		 * @param pkeyData
		 *            Lookup results
		 * @param windowNo
		 *            Window No.
		 * @param tab
		 *            Tab
		 * @param field
		 *            Field
		 */
		private void fillLocation(HashMap<String, Object> postcodeData, MCountry country) {

			// If it's not empty warn the user.
			if (fAddress1 != null || fAddress2 != null
					|| fAddress3 != null
					|| fAddress4 != null || fCity != null) {
				String warningMsg = "Existing address information will be overwritten. Proceed?";
				String warningTitle = "Warning";
				int response = JOptionPane.showConfirmDialog(null, warningMsg,
						warningTitle, JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.NO_OPTION)
					return;
			}
			
			
			Set<String> pcodeKeys = postcodeData.keySet();
			Iterator<String> iterator = pcodeKeys.iterator();
			header = null;

			// Allocate the header array
			header = new Object[pcodeKeys.size()];

			String headerStr = null;
			
			// need to check how many records returned
			// TODO - check number of records returns - size() method is incorrect
			if (pcodeKeys.size() > 2)
			{
				// TODO: Implement ResultData Grid and get return (for premises level data)
				System.out.println("Too many postcodes returned from Postcode Lookup - need to Implement ResultData");
			} else
			{
				for (int i = 0; (headerStr = (iterator.hasNext() ? iterator.next() : null)) != null
						|| iterator.hasNext(); i++) {
					header[i] = headerStr;
					Postcode values =  (Postcode) postcodeData.get(headerStr);
				
					// Overwrite the values in location field.
					fAddress1.setText(values.getStreet1());
					fCity.setText(values.getCity());
					fPostal.setText(values.getPostcode());
					
					// Do region lookup
					if (country.isHasRegion())
					{
						// get all regions for this country
						MRegion[] regions = MRegion.getRegions(country.getCtx(), country.getC_Country_ID());
						
						// If regions were loaded
						if ( regions.length > 0)
						{
							// loop through regions array to attempt a region match - don't finish loop if region found
							boolean found = false;
							for (i = 0; i < regions.length && !found; i++)
							{
								
								if (regions[i].getName().equals(values.getRegion()) )
								{
									// found county
									fRegion.setSelectedItem(regions[i]);	
									log.fine("Found region: " + regions[i].getName());
									found = true;
								}
							}
							if (!found)
							{
								// add new region
								MRegion region = new MRegion(country, values.getRegion());
								if (region.save())
								{
									log.fine("Added new region from web service: " + values.getRegion());
									fRegion.setSelectedItem(values);
								} else
									log.severe("Error saving new region: " + region.getName());
								
							}
						} else
							log.severe("Region lookup failed for Country: " + country.getName());
						
					}		
				}
			}
			
		}
}	//	VLocationDialog
