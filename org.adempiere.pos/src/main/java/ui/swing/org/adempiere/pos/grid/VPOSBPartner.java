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
 *  @author victor.perez@e-evolution.com , http://www.e-evolution.com
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
	public VPOSBPartner(Frame frame, int WindowNo, VPOS pos)
	{
		super(frame, Msg.translate(Env.getCtx(), "C_BPartner_ID"), true);
		this.pos = pos;
		windowNo = WindowNo;
		isReadOnly = !MRole.getDefault().canUpdate(
			Env.getAD_Client_ID(Env.getCtx()), Env.getAD_Org_ID(Env.getCtx()),
			MBPartner.Table_ID, 0, false);
		log.info("R/O=" + isReadOnly);
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

	private VPOS 			pos;
	private int 			windowNo;
	/** The Partner				*/
	private MBPartner 		partner = null;
	/** The Location			*/
	private MBPartnerLocation partnerLocation = null;
	/** The User				*/
	private MUser 			contact = null;
	/** Read Only				*/
	private boolean 		isReadOnly = false;

	
	private Insets 			labelInsets = new Insets(2,15,2,0);		// 	top,left,bottom,right
	private Insets 			fieldInsets = new Insets(2,5,2,10);		// 	top,left,bottom,right
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
		fValue = new POSTextField("Value", pos.getKeyboard());
		fValue.addActionListener(this);
		fValue.setPreferredSize(new Dimension(300, 25));
		createLine (fValue, "Value", true);
		//	Name
		fName = new POSTextField("Name", pos.getKeyboard());
		fName.addActionListener(this);
		createLine (fName, "Name", false).setFontBold(true);
		//	Name2
		fName2 = new POSTextField("Name2", pos.getKeyboard());
		createLine (fName2, "Name2", false);
		
		//	Contact
		fContact = new POSTextField("Contact", pos.getKeyboard());
		createLine (fContact, "Contact", true).setFontBold(true);
		//	Email
		fEMail = new POSTextField("EMail", pos.getKeyboard());
		createLine (fEMail, "EMail", false);
		
		//	Location
		boolean ro = isReadOnly;
		if (!ro)
			ro = !MRole.getDefault().canUpdate(
				Env.getAD_Client_ID(Env.getCtx()), Env.getAD_Org_ID(Env.getCtx()), 
				MBPartnerLocation.Table_ID, 0, false);
		if (!ro)
			ro = !MRole.getDefault().canUpdate(
				Env.getAD_Client_ID(Env.getCtx()), Env.getAD_Org_ID(Env.getCtx()), 
				MLocation.Table_ID, 0, false);
		fAddress = new VLocation ("C_Location_ID", false, ro, true, new MLocationLookup (Env.getCtx(), windowNo));
		fAddress.setValue (null);
		createLine (fAddress, "C_Location_ID", true).setFontBold(true);
		//	Phone
		fPhone = new POSTextField("Phone", pos.getKeyboard());
		createLine (fPhone, "Phone", true);
		//	Phone2
		fPhone2 = new POSTextField("Phone2", pos.getKeyboard());
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
			m_gbc.insets = fieldInsets;
			centerPanel.add (Box.createHorizontalStrut(6), m_gbc);
		}

		//	Line
		m_gbc.gridy = m_line++;

		//	Label
		m_gbc.gridx = 0;
		m_gbc.insets = labelInsets;
		m_gbc.fill = GridBagConstraints.HORIZONTAL;
		CLabel label = new CLabel(Msg.translate(Env.getCtx(), title));
		centerPanel.add(label, m_gbc);

		//	Field
		m_gbc.gridx = 1;
		m_gbc.insets = fieldInsets;
		m_gbc.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(field, m_gbc);
		if (isReadOnly)
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
			partner = null;
			partnerLocation = null;
			contact = null;
			return true;
		}

		partner = new MBPartner (Env.getCtx(), C_BPartner_ID, null);
		if (partner.get_ID() == 0)
		{
			ADialog.error(windowNo, this, "BPartnerNotFound");
			return false;
		}

		//	BPartner - Load values
		fValue.setText(partner.getValue());
		fName.setText(partner.getName());
		fName2.setText(partner.getName2());

		//	Contact - Load values
		partnerLocation = partner.getLocation(
			Env.getContextAsInt(Env.getCtx(), windowNo, "C_BPartner_Location_ID"));
		if (partnerLocation != null)
		{
			int location = partnerLocation.getC_Location_ID();
			fAddress.setValue (new Integer(location));
			//
			fPhone.setText(partnerLocation.getPhone());
			fPhone2.setText(partnerLocation.getPhone2());
		}
		//	User - Load values
		contact = partner.getContact(
			Env.getContextAsInt(Env.getCtx(), windowNo, "AD_User_ID"));
		if (contact != null)
		{
			fContact.setText(contact.getName());
			fEMail.setText(contact.getEMail());
			//
			fPhone.setText(contact.getPhone());
			fPhone2.setText(contact.getPhone2());
		}
		return true;
	}	//	loadBPartner


	/**
	 *	Action Listener
	 * 	@param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (isReadOnly)
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
		if (partner == null) {
			int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
			partner = MBPartner.getTemplate(Env.getCtx(), AD_Client_ID, pos.getC_POS_ID());
			partner.setAD_Org_ID(Env.getAD_Org_ID(Env.getCtx())); // Elaine 2009/07/03
			boolean isSOTrx = !"N".equals(Env.getContext(Env.getCtx(), windowNo, "IsSOTrx"));
			partner.setIsCustomer (isSOTrx);
			partner.setIsVendor (!isSOTrx);
		}
		//	Check Value
		String value = fValue.getText();
		if (value == null || value.length() == 0)
		{
			//	get Table Documet No
			value = DB.getDocumentNo (Env.getAD_Client_ID(Env.getCtx()), "C_BPartner", null, partner);
			fValue.setText(value);
		}
		partner.setValue(fValue.getText());
		//
		partner.setName(fName.getText());
		partner.setName2(fName2.getText());

		if (partner.save())
			log.fine("C_BPartner_ID=" + partner.getC_BPartner_ID());
		else
			ADialog.error(windowNo, this, "BPartnerNotSaved");
		
		//	***** Business Partner - Location *****
		if (partnerLocation == null)
			partnerLocation = new MBPartnerLocation(partner);
		partnerLocation.setC_Location_ID(fAddress.getC_Location_ID());
		//
		partnerLocation.setPhone(fPhone.getText());
		partnerLocation.setPhone2(fPhone2.getText());
		if (partnerLocation.save())
			log.fine("C_BPartner_Location_ID=" + partnerLocation.getC_BPartner_Location_ID());
		else
			ADialog.error(windowNo, this, "BPartnerNotSaved", Msg.translate(Env.getCtx(), "C_BPartner_Location_ID"));
			
		//	***** Business Partner - User *****
		String contact = fContact.getText();
		String email = fEMail.getText();
		if (this.contact == null && (contact.length() > 0 || email.length() > 0))
			this.contact = new MUser (partner);
		if (this.contact != null)
		{
			if (contact.length() == 0)
				contact = fName.getText();
			this.contact.setName(contact);
			this.contact.setEMail(email);
			//
			this.contact.setPhone(fPhone.getText());
			this.contact.setPhone2(fPhone2.getText());
			if (this.contact.save())
				log.fine("AD_User_ID=" + this.contact.getAD_User_ID());
			else
				ADialog.error(windowNo, this, "BPartnerNotSaved", Msg.translate(Env.getCtx(), "AD_User_ID"));
		}
		return true;
	}	//	actionSave


	/**
	 *	Returns BPartner ID
	 *	@return C_BPartner_ID (0 = not saved)
	 */
	public int getC_BPartner_ID()
	{
		if (partner == null)
			return 0;
		return partner.getC_BPartner_ID();
	}	//	getBPartner_ID

}	//	VPOSBPartner
