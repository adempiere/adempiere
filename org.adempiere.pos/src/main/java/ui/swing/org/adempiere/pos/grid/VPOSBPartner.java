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
package org.adempiere.pos.grid;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.Box;
import javax.swing.JComponent;

import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.pos.POSTextField;
import org.adempiere.pos.VPOS;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.ConfirmPanel;
import org.compiere.grid.ed.VLocation;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MLocation;
import org.compiere.model.MLocationLookup;
import org.compiere.model.MRole;
import org.compiere.model.MUser;
import org.compiere.swing.CDialog;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Business Partner Editor.
 *	Set BPartner: loadBPartner
 *	Get result: getC_BPartner_ID
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VBPartner.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public final class VPOSBPartner extends CDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4130643780412193122L;


	/**
	 *	Constructor.
	 *	Requires call loadBPartner
	 * 	@param frame	parent
	 * 	@param WindowNo	Window No
	 */
	public VPOSBPartner(Frame frame, int WindowNo, VPOS p_POSPanel)
	{
		super(frame, Msg.translate(Env.getCtx(), "C_BPartner_ID"), true);
		v_POSPanel = p_POSPanel;
		m_WindowNo = WindowNo;
		m_readOnly = !MRole.getDefault().canUpdate(
			Env.getAD_Client_ID(Env.getCtx()), Env.getAD_Org_ID(Env.getCtx()), 
			MBPartner.Table_ID, 0, false);
		log.info("R/O=" + m_readOnly);
		try
		{
			jbInit();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, ex.getMessage());
		}
		initBPartner();
		//
		AEnv.positionCenterWindow(frame, this);
	}	//	VBPartner

	private VPOS 			v_POSPanel; 
	private int 			m_WindowNo;
	/** The Partner				*/
	private MBPartner		m_partner = null;
	/** The Location			*/
	private MBPartnerLocation	m_pLocation = null;
	/** The User				*/
	private MUser			m_user = null;
	/** Read Only				*/
	private boolean			m_readOnly = false;

	
	private Insets			m_labelInsets = new Insets(2,15,2,0);		// 	top,left,bottom,right
	private Insets			m_fieldInsets = new Insets(2,5,2,10);		// 	top,left,bottom,right
	private GridBagConstraints m_gbc = new GridBagConstraints();
	private int				m_line;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VPOSBPartner.class);
	//
	private POSTextField 	fValue, fName, fName2, fContact, fPhone, fPhone2, fEMail;
	private VLocation 		fAddress;

	//
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel centerPanel = new CPanel();
	private CPanel southPanel = new CPanel();
	private GridBagLayout centerLayout = new GridBagLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private BorderLayout southLayout = new BorderLayout();

	
	/**
	 *	Static Init
	 * 	@throws Exception
	 */
	void jbInit() throws Exception
	{
		mainPanel.setLayout(mainLayout);
		southPanel.setLayout(southLayout);
		centerPanel.setLayout(centerLayout);
		mainLayout.setVgap(5);
		getContentPane().add(mainPanel);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		southPanel.add(confirmPanel, BorderLayout.CENTER);
		setMinimumSize(new Dimension(400, 300));
		//
		confirmPanel.addActionListener(this);
	}	//	jbInit

	/**
	 *	Dynamic Init
	 */
	private void initBPartner()	{

		//	Display
		m_gbc.anchor = GridBagConstraints.NORTHWEST;
		m_gbc.gridx = 0;
		m_gbc.gridy = 0;
		m_gbc.gridwidth = 1;
		m_gbc.weightx = 0;
		m_gbc.weighty = 0;
		m_gbc.fill = GridBagConstraints.HORIZONTAL;
		m_gbc.ipadx = 0;
		m_gbc.ipady = 0;
		m_line = 0;

		//	Value
		fValue = new POSTextField("Value", v_POSPanel.getKeyboard());
		fValue.addActionListener(this);
		fValue.setPreferredSize(new Dimension(300, 25));
		createLine (fValue, "Value", true);
		//	Name
		fName = new POSTextField("Name", v_POSPanel.getKeyboard());
		fName.addActionListener(this);
		createLine (fName, "Name", false).setFontBold(true);
		//	Name2
		fName2 = new POSTextField("Name2", v_POSPanel.getKeyboard());
		createLine (fName2, "Name2", false);
		
		//	Contact
		fContact = new POSTextField("Contact", v_POSPanel.getKeyboard());
		createLine (fContact, "Contact", true).setFontBold(true);
		//	Email
		fEMail = new POSTextField("EMail", v_POSPanel.getKeyboard());
		createLine (fEMail, "EMail", false);
		
		//	Location
		boolean ro = m_readOnly;
		if (!ro)
			ro = !MRole.getDefault().canUpdate(
				Env.getAD_Client_ID(Env.getCtx()), Env.getAD_Org_ID(Env.getCtx()), 
				MBPartnerLocation.Table_ID, 0, false);
		if (!ro)
			ro = !MRole.getDefault().canUpdate(
				Env.getAD_Client_ID(Env.getCtx()), Env.getAD_Org_ID(Env.getCtx()), 
				MLocation.Table_ID, 0, false);
		fAddress = new VLocation ("C_Location_ID", false, ro, true, new MLocationLookup (Env.getCtx(), m_WindowNo));
		fAddress.setValue (null);
		createLine (fAddress, "C_Location_ID", true).setFontBold(true);
		//	Phone
		fPhone = new POSTextField("Phone", v_POSPanel.getKeyboard());
		createLine (fPhone, "Phone", true);
		//	Phone2
		fPhone2 = new POSTextField("Phone2", v_POSPanel.getKeyboard());
		createLine (fPhone2, "Phone2", false);
		//
		fName.setBackground(AdempierePLAF.getFieldBackground_Mandatory());
		fValue.setBackground(AdempierePLAF.getFieldBackground_Mandatory());
		fAddress.setBackground(AdempierePLAF.getFieldBackground_Mandatory());
	}	//	initBPartner

	/**
	 * 	Create Line
	 * 	@param field 	field
	 * 	@param title	label value
	 * 	@param addSpace	add more space
	 * 	@return label
	 */
	private CLabel createLine (JComponent field, String title, boolean addSpace)
	{
		if (addSpace)
		{
			m_gbc.gridy = m_line++;
			m_gbc.gridx = 1;
			m_gbc.insets = m_fieldInsets;
			centerPanel.add (Box.createHorizontalStrut(6), m_gbc);
		}

		//	Line
		m_gbc.gridy = m_line++;

		//	Label
		m_gbc.gridx = 0;
		m_gbc.insets = m_labelInsets;
		m_gbc.fill = GridBagConstraints.HORIZONTAL;
		CLabel label = new CLabel(Msg.translate(Env.getCtx(), title));
		centerPanel.add(label, m_gbc);

		//	Field
		m_gbc.gridx = 1;
		m_gbc.insets = m_fieldInsets;
		m_gbc.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(field, m_gbc);
		if (m_readOnly)
			field.setEnabled(false);
		return label;
	}	//	createLine

	/**
	 *	Load BPartner
	 *  @param C_BPartner_ID - existing BPartner or 0 for new
	 * 	@return true if loaded
	 */
	public boolean loadBPartner (int C_BPartner_ID)
	{
		log.config("C_BPartner_ID=" + C_BPartner_ID);
		//  New bpartner
		if (C_BPartner_ID == 0)
		{
			m_partner = null;
			m_pLocation = null;
			m_user = null;
			return true;
		}

		m_partner = new MBPartner (Env.getCtx(), C_BPartner_ID, null);
		if (m_partner.get_ID() == 0)
		{
			ADialog.error(m_WindowNo, this, "BPartnerNotFound");
			return false;
		}

		//	BPartner - Load values
		fValue.setText(m_partner.getValue());
		fName.setText(m_partner.getName());
		fName2.setText(m_partner.getName2());

		//	Contact - Load values
		m_pLocation = m_partner.getLocation(
			Env.getContextAsInt(Env.getCtx(), m_WindowNo, "C_BPartner_Location_ID"));
		if (m_pLocation != null)
		{
			int location = m_pLocation.getC_Location_ID();
			fAddress.setValue (new Integer(location));
			//
			fPhone.setText(m_pLocation.getPhone());
			fPhone2.setText(m_pLocation.getPhone2());
		}
		//	User - Load values
		m_user = m_partner.getContact(
			Env.getContextAsInt(Env.getCtx(), m_WindowNo, "AD_User_ID"));
		if (m_user != null)
		{
			fContact.setText(m_user.getName());
			fEMail.setText(m_user.getEMail());
			//
			fPhone.setText(m_user.getPhone());
			fPhone2.setText(m_user.getPhone2());
		}
		return true;
	}	//	loadBPartner


	/**
	 *	Action Listener
	 * 	@param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (m_readOnly)
			dispose();
		//	copy value
		else if (e.getSource() == fValue)
		{
			if (fName.getText() == null || fName.getText().length() == 0)
				fName.setText(fValue.getText());
		}
		else if (e.getSource() == fName)
		{
			if (fContact.getText() == null || fContact.getText().length() == 0)
				fContact.setText(fName.getText());
		}
		//	OK pressed
		else if (e.getActionCommand().equals(ConfirmPanel.A_OK) 
			&& actionSave())
			dispose();
		//	Cancel pressed
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			dispose();
	}	//	actionPerformed

	/**
	 *	Save.
	 *	Checks mandatory fields and saves Partner, Contact and Location
	 * 	@return true if saved
	 */
	private boolean actionSave()
	{
		log.config("");

		//	Check Mandatory fields
		if (fValue.getText().equals(""))
		{
			fValue.setBackground(AdempierePLAF.getFieldBackground_Error());
			return false;
		}
		else {
			fValue.setBackground(AdempierePLAF.getFieldBackground_Mandatory());
		}
		if (fName.getText().equals(""))
		{
			fName.setBackground(AdempierePLAF.getFieldBackground_Error());
			return false;
		}
		
		else
			fName.setBackground(AdempierePLAF.getFieldBackground_Mandatory());
		if (fAddress.getC_Location_ID() == 0)
		{
			fAddress.setBackground(AdempierePLAF.getFieldBackground_Error());
			return false;
		}
		else
			fAddress.setBackground(AdempierePLAF.getFieldBackground_Mandatory());

		//	***** Business Partner *****
		if (m_partner == null)
		{
			int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
			m_partner = MBPartner.getTemplate(Env.getCtx(), AD_Client_ID);
			m_partner.setAD_Org_ID(Env.getAD_Org_ID(Env.getCtx())); // Elaine 2009/07/03
			boolean isSOTrx = !"N".equals(Env.getContext(Env.getCtx(), m_WindowNo, "IsSOTrx"));
			m_partner.setIsCustomer (isSOTrx);
			m_partner.setIsVendor (!isSOTrx);
		}
		//	Check Value
		String value = fValue.getText();
		if (value == null || value.length() == 0)
		{
			//	get Table Documet No
			value = DB.getDocumentNo (Env.getAD_Client_ID(Env.getCtx()), "C_BPartner", null, m_partner);
			fValue.setText(value);
		}
		m_partner.setValue(fValue.getText());
		//
		m_partner.setName(fName.getText());
		m_partner.setName2(fName2.getText());

		if (m_partner.save())
			log.fine("C_BPartner_ID=" + m_partner.getC_BPartner_ID());
		else
			ADialog.error(m_WindowNo, this, "BPartnerNotSaved");
		
		//	***** Business Partner - Location *****
		if (m_pLocation == null)
			m_pLocation = new MBPartnerLocation(m_partner);
		m_pLocation.setC_Location_ID(fAddress.getC_Location_ID());
		//
		m_pLocation.setPhone(fPhone.getText());
		m_pLocation.setPhone2(fPhone2.getText());
		if (m_pLocation.save())
			log.fine("C_BPartner_Location_ID=" + m_pLocation.getC_BPartner_Location_ID());
		else
			ADialog.error(m_WindowNo, this, "BPartnerNotSaved", Msg.translate(Env.getCtx(), "C_BPartner_Location_ID"));
			
		//	***** Business Partner - User *****
		String contact = fContact.getText();
		String email = fEMail.getText();
		if (m_user == null && (contact.length() > 0 || email.length() > 0))
			m_user = new MUser (m_partner);
		if (m_user != null)
		{
			if (contact.length() == 0)
				contact = fName.getText();
			m_user.setName(contact);
			m_user.setEMail(email);
			//
			m_user.setPhone(fPhone.getText());
			m_user.setPhone2(fPhone2.getText());
			if (m_user.save())
				log.fine("AD_User_ID=" + m_user.getAD_User_ID());
			else
				ADialog.error(m_WindowNo, this, "BPartnerNotSaved", Msg.translate(Env.getCtx(), "AD_User_ID"));
		}
		return true;
	}	//	actionSave


	/**
	 *	Returns BPartner ID
	 *	@return C_BPartner_ID (0 = not saved)
	 */
	public int getC_BPartner_ID()
	{
		if (m_partner == null)
			return 0;
		return m_partner.getC_BPartner_ID();
	}	//	getBPartner_ID

}	//	VPOSBPartner
