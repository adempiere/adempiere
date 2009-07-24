/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
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

package org.compiere.pos;

import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerInfo;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCurrency;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MUser;
import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;


/**
 *	Customer Sub Panel
 *	
 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright � Jorg Janke
 *  @version $Id: SubBPartner.java,v 1.1 2004/07/12 04:10:04 jjanke Exp $
 */
public class SubBPartner extends PosSubPanel 
	implements ActionListener, FocusListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5895558315889871887L;

	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public SubBPartner (PosPanel posPanel)
	{
		super (posPanel);
	}	//	PosSubCustomer
	
	private	CTextField		f_name;
	private CButton 		f_bNew;
	private CButton 		f_bEdit;
	private CButton 		f_bSearch;
	private CComboBox		f_location;
	private CComboBox		f_user;
	
	/**	The Business Partner		*/
	private MBPartner	m_bpartner;
	/**	Price List Version to use	*/
	private int			m_M_PriceList_Version_ID = 0;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(SubBPartner.class);
	
	/**
	 * 	Initialize
	 */
	public void init()
	{
		//	Title
		TitledBorder border = new TitledBorder(Msg.translate(p_ctx, "C_BPartner_ID"));
		setBorder(border);
		
		//	Content
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = INSETS2;
		//	--
		f_bNew = createButtonAction("New", null);
		gbc.gridx = 0;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.WEST;
		add (f_bNew, gbc);
		//
		f_bEdit = createButtonAction ("Edit", null);
		gbc.gridx = 1;
		add (f_bEdit, gbc);
		//
		f_name = new CTextField("");
		f_name.setName("Name");
		f_name.addActionListener(this);
		f_name.addFocusListener(this);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add (f_name, gbc);
		//
		f_location = new CComboBox();
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		add (f_location, gbc);
		//
		f_user = new CComboBox();
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.EAST;
		add (f_user, gbc);
		//
		f_bSearch = createButtonAction ("BPartner", KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.SHIFT_MASK+Event.CTRL_MASK));
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.NONE;
		add (f_bSearch, gbc);
	}	//	init

	/**
	 * 	Get Panel Position
	 */
	public GridBagConstraints getGridBagConstraints()
	{
		GridBagConstraints gbc = super.getGridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		return gbc;
	}	//	getGridBagConstraints
	
	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose()
	{
		if (f_name != null)
			f_name.removeFocusListener(this);
		f_name = null;
		removeAll();
		super.dispose();
	}	//	dispose

	
	/**************************************************************************
	 * 	Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		String action = e.getActionCommand();
		if (action == null || action.length() == 0)
			return;
		log.info( "PosSubCustomer - actionPerformed: " + action);
		//	New
		if (action.equals("New"))
				p_posPanel.newOrder(); //red1 New POS Order instead - B_Partner already has direct field
		//	Edit
		else if (action.equals("Edit"))
		{
			f_bEdit.setReadWrite(false);
		}
		//	BPartner
		else if (action.equals("BPartner"))
		{
			p_posPanel.openQuery(p_posPanel.f_queryBPartner);
		}
		//	Name
		else if (e.getSource() == f_name)
			findBPartner();
		
		p_posPanel.updateInfo();
	}	//	actionPerformed

	/**
	 * 	Focus Gained
	 *	@param e
	 */
	public void focusGained (FocusEvent e)
	{
	}	//	focusGained

	/**
	 * 	Focus Lost
	 *	@param e
	 */
	public void focusLost (FocusEvent e)
	{
		if (e.isTemporary())
			return;
		log.info(e.toString());
		findBPartner();
	}	//	focusLost

	
	/**
	 * 	Find/Set BPartner
	 */
	private void findBPartner()
	{
		String query = f_name.getText();
		if (query == null || query.length() == 0)
			return;
		query = query.toUpperCase();
		//	Test Number
		boolean allNumber = true;
		boolean noNumber = true;
		char[] qq = query.toCharArray();
		for (int i = 0; i < qq.length; i++)
		{
			if (Character.isDigit(qq[i]))
			{
				noNumber = false;
				break;
			}
		}
		try
		{
			Integer.parseInt(query);
		}
		catch (Exception e)
		{
			allNumber = false;
		}
		String Value = query;
		String Name = (allNumber ? null : query);
		String EMail = (query.indexOf('@') != -1 ? query : null); 
		String Phone = (noNumber ? null : query);
		String City = null;
		//
		//TODO: contact have been remove from rv_bpartner
		MBPartnerInfo[] results = MBPartnerInfo.find(p_ctx, Value, Name, 
			/*Contact, */null, EMail, Phone, City);
		
		//	Set Result
		if (results.length == 0)
		{
			setC_BPartner_ID(0);
		}
		else if (results.length == 1)
		{
			setC_BPartner_ID(results[0].getC_BPartner_ID());
			f_name.setText(results[0].getName());
		}
		else	//	more than one
		{
			p_posPanel.f_queryBPartner.setResults (results);
			p_posPanel.openQuery(p_posPanel.f_queryBPartner);
		}
	}	//	findBPartner
	
	
	/**************************************************************************
	 * 	Set BPartner
	 *	@param C_BPartner_ID id
	 */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		log.fine( "PosSubCustomer.setC_BPartner_ID=" + C_BPartner_ID);
		if (C_BPartner_ID == 0)
			m_bpartner = null;
		else
		{
			m_bpartner = new MBPartner(p_ctx, C_BPartner_ID, null);
			if (m_bpartner.get_ID() == 0)
				m_bpartner = null;
		}
		
		//	Set Info
		if (m_bpartner != null)
		{
			f_name.setText(m_bpartner.getName());
			f_bEdit.setReadWrite(false);
		}
		else
		{
			f_name.setText(null);
			f_bEdit.setReadWrite(false);
		}
		//	Sets Currency
		m_M_PriceList_Version_ID = 0;
		getM_PriceList_Version_ID();
		fillCombos();
		p_posPanel.f_curLine.setBPartner();  //added by ConSerTi to update the client in the request
	}	//	setC_BPartner_ID

	/**
	 * 	Fill Combos (Location, User)
	 */
	private void fillCombos()
	{
		Vector<KeyNamePair> locationVector = new Vector<KeyNamePair>();
		if (m_bpartner != null)
		{
			MBPartnerLocation[] locations = m_bpartner.getLocations(false);
			for (int i = 0; i < locations.length; i++)
				locationVector.add(locations[i].getKeyNamePair());
		}
		DefaultComboBoxModel locationModel = new DefaultComboBoxModel(locationVector); 
		f_location.setModel(locationModel);
		//
		Vector<KeyNamePair> userVector = new Vector<KeyNamePair>();
		if (m_bpartner != null)
		{
			MUser[] users = m_bpartner.getContacts(false);
			for (int i = 0; i < users.length; i++)
				userVector.add(users[i].getKeyNamePair());
		}
		DefaultComboBoxModel userModel = new DefaultComboBoxModel(userVector); 
		f_user.setModel(userModel);
	}	//	fillCombos
	
	
	/**
	 * 	Get BPartner
	 *	@return C_BPartner_ID
	 */
	public int getC_BPartner_ID ()
	{
		if (m_bpartner != null)
			return m_bpartner.getC_BPartner_ID();
		return 0;
	}	//	getC_BPartner_ID

	/**
	 * 	Get BPartner
	 *	@return BPartner
	 */
	public MBPartner getBPartner ()
	{
		return m_bpartner;
	}	//	getBPartner
	
	/**
	 * 	Get BPartner Location
	 *	@return C_BPartner_Location_ID
	 */
	public int getC_BPartner_Location_ID ()
	{
		if (m_bpartner != null)
		{
			KeyNamePair pp = (KeyNamePair)f_location.getSelectedItem();
			if (pp != null)
				return pp.getKey();
		}
		return 0;
	}	//	getC_BPartner_Location_ID
	
	/**
	 * 	Get BPartner Contact
	 *	@return AD_User_ID
	 */
	public int getAD_User_ID ()
	{
		if (m_bpartner != null)
		{
			KeyNamePair pp = (KeyNamePair)f_user.getSelectedItem();
			if (pp != null)
				return pp.getKey();
		}
		return 0;
	}	//	getC_BPartner_Location_ID

	/**
	 * 	Get M_PriceList_Version_ID.
	 * 	Set Currency
	 *	@return plv
	 */
	public int getM_PriceList_Version_ID()
	{
		if (m_M_PriceList_Version_ID == 0)
		{
			int M_PriceList_ID = p_pos.getM_PriceList_ID();
			if (m_bpartner != null && m_bpartner.getM_PriceList_ID() != 0)
				M_PriceList_ID = m_bpartner.getM_PriceList_ID();
			//
			MPriceList pl = MPriceList.get(p_ctx, M_PriceList_ID, null);
			p_posPanel.f_curLine.setCurrency(MCurrency.getISO_Code(p_ctx, pl.getC_Currency_ID()));
			f_name.setToolTipText(pl.getName());
			//
			MPriceListVersion plv = pl.getPriceListVersion (p_posPanel.getToday());
			if (plv != null && plv.getM_PriceList_Version_ID() != 0)
				m_M_PriceList_Version_ID = plv.getM_PriceList_Version_ID();
		}
		return m_M_PriceList_Version_ID;
	}	//	getM_PriceList_Version_ID
	
}	//	PosSubCustomer
