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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import net.miginfocom.swing.MigLayout;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerInfo;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCurrency;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MSequence;
import org.compiere.model.MSysConfig;
import org.compiere.model.MUser;
import org.compiere.print.ReportCtl;
import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CLabel;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;


/**
 *	Customer Sub Panel
 *	
 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright � Jorg Janke
 *         *Copyright � ConSerTi
 *  @author Mario Calderón, Systemhaus Westfalia
 *  @version $Id: SubOrder.java,v 1.1 2004/07/12 04:10:04 jjanke Exp $
 *  @version $Id: SubOrder.java,v 2.0 2015/09/01 00:00:00 mar_cal_westf
 */
public class SubOrder extends PosSubPanel 
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
	public SubOrder (PosBasePanel posPanel)
	{
		super (posPanel);
	}	//	PosSubCustomer
	
	private CButton 		f_history;
	private	CTextField		f_name;
	private CButton 		f_bNew;
	private CButton 		f_bSearch;
	private CComboBox		f_location;
	private CComboBox		f_user;
	private CButton 		f_cashPayment;
	private CButton 		f_cashPrePayment;
	private CButton 		f_process;
	private CButton 		f_print;
	private CTextField 		f_DocumentNo;
	private CButton 		f_logout;
	private JFormattedTextField f_net;
	private JFormattedTextField f_tax;
	private JFormattedTextField f_total;
	private CTextField f_RepName;
	
	/**	The Business Partner		*/
	private MBPartner	m_bpartner;
	/**	Price List Version to use	*/
	private int			m_M_PriceList_Version_ID = 0;
	private CTextField f_currency = new CTextField();
	private CButton f_bEdit;
	private CButton f_bSettings;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(SubOrder.class);

	private final String POS_ALTERNATIVE_DOCTYPE_ENABLED = "POS_ALTERNATIVE_DOCTYPE_ENABLED";  // System configurator entry
	private final String NO_ALTERNATIVE_POS_DOCTYPE      = "N";
	private final boolean isAlternativeDocTypeEnabled    = MSysConfig.getValue(POS_ALTERNATIVE_DOCTYPE_ENABLED, 
			NO_ALTERNATIVE_POS_DOCTYPE, Env.getAD_Client_ID(p_ctx)).compareToIgnoreCase(NO_ALTERNATIVE_POS_DOCTYPE)==0?false:true;
	

	private final String ACTION_BPARTNER    = "BPartner";
	private final String ACTION_CANCEL      = "Cancel";
	private final String ACTION_CREDITSALE  = "Credit Sale";
	private final String ACTION_HISTORY     = "History";
	private final String ACTION_LOGOUT      = "Logout";
	private final String ACTION_NEW         = "New";
	private final String ACTION_PAYMENT     = "Payment";
	private final String ACTION_PREPAYMENT  = "Prepayment";
	private final String ACTION_PREFERENCES = "Preference";
	private final String ACTION_PRINT       = "Print";
	
	/**
	 * 	Initialize
	 */
	public void init()
	{
		//	Content
		MigLayout layout = new MigLayout("ins 0 0","[fill|fill|fill|fill]","[nogrid]unrel[||]");
		setLayout(layout);
		
		Font bigFont = AdempierePLAF.getFont_Field().deriveFont(16f);

		String buttonSize = "w 50!, h 50!,";
		// NEW
		f_bNew = createButtonAction(ACTION_NEW, KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.F2));
		add (f_bNew, buttonSize);

		// BPARTNER
		f_bSearch = createButtonAction (ACTION_BPARTNER, KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.SHIFT_MASK+Event.CTRL_MASK));
		add (f_bSearch,buttonSize );
		
		// CREDIT SALE
		f_bEdit = createButtonAction(ACTION_CREDITSALE, null);
		add(f_bEdit, buttonSize);
 		f_bEdit.setEnabled(false);
		
		// HISTORY
		f_history = createButtonAction(ACTION_HISTORY, null);
 		add (f_history, buttonSize); 
		
		// CANCEL
		f_process = createButtonAction(ACTION_CANCEL, null);
 		add (f_process, buttonSize);
 		f_process.setEnabled(false);
 		
 		// PAYMENT
 		f_cashPayment = createButtonAction(ACTION_PAYMENT, null);
		f_cashPayment.setActionCommand(ACTION_PAYMENT);
		add (f_cashPayment, buttonSize); 
		f_cashPayment.setEnabled(false);
 		
 		// PREPAYMENT
 		f_cashPrePayment = createButtonAction(ACTION_PREPAYMENT, null);
 		f_cashPrePayment.setActionCommand(ACTION_PREPAYMENT);
		add (f_cashPrePayment, buttonSize); 
		f_cashPrePayment.setEnabled(false);
		
 		//PRINT
		f_print = createButtonAction(ACTION_PRINT, null);
 		add (f_print, buttonSize);
 		f_print.setEnabled(false);
 		
 		// Settings
		f_bSettings = createButtonAction(ACTION_PREFERENCES, null);
 		add (f_bSettings, buttonSize);
 		
		// Logout
		f_logout = createButtonAction (ACTION_LOGOUT, null);
		add (f_logout, buttonSize + ", gapx 25, wrap");

		// BP
		CLabel BPLabelLabel = new CLabel(Msg.translate(Env.getCtx(), MBPartner.COLUMNNAME_C_BPartner_ID)); 
		//add(BPLabelLabel, "split 2, spanx 4, flowy, h 15");
		add(BPLabelLabel, "");
		//add(new CLabel(Msg.translate(Env.getCtx(), "C_BPartner_ID")), "");
		f_name = new CTextField();
		f_name.setEditable(false);
		f_name.setName(MBPartner.COLUMNNAME_Name);
		add (f_name, "wrap,spanx 3, growx");
		
 		// DOC NO
		add (new CLabel(Msg.getMsg(Env.getCtx(),MOrder.COLUMNNAME_DocumentNo)), ""); 
		
		f_DocumentNo = new CTextField("");
		f_DocumentNo.setName(MOrder.COLUMNNAME_DocumentNo);
		f_DocumentNo.setEditable(false);
		add (f_DocumentNo, "growx, pushx");
		
		CLabel lNet = new CLabel (Msg.translate(Env.getCtx(), MOrder.COLUMNNAME_TotalLines));
		add(lNet, "");
		f_net = new JFormattedTextField(DisplayType.getNumberFormat(DisplayType.Amount));
		f_net.setHorizontalAlignment(JTextField.TRAILING);
		f_net.setEditable(false);
		f_net.setFocusable(false);
		lNet.setLabelFor(f_net);
		add(f_net, "wrap, growx, pushx");
		f_net.setValue (Env.ZERO);
		//
	
		
		
		//f_name.setName("Name");
		//f_name.addActionListener(this);
		//f_name.addFocusListener(this);
		//add (f_name, "wrap");
		

		// SALES REP
		add(new CLabel(Msg.translate(Env.getCtx(), MOrder.COLUMNNAME_SalesRep_ID)), ""); 
		MUser salesrep = new MUser(p_ctx, Env.getAD_User_ID(p_ctx), null);
		f_RepName = new CTextField(salesrep.getName());
		f_RepName.setName("SalesRep");
		f_RepName.setEditable(false);
		add (f_RepName, "growx, pushx");

		CLabel lTax = new CLabel (Msg.translate(Env.getCtx(), MInvoiceLine.COLUMNNAME_TaxAmt));
		add(lTax);
		f_tax = new JFormattedTextField(DisplayType.getNumberFormat(DisplayType.Amount));
		f_tax.setHorizontalAlignment(JTextField.TRAILING);
		f_tax.setEditable(false);
		f_tax.setFocusable(false);
		lTax.setLabelFor(f_tax);
		add(f_tax, "wrap, growx, pushx");
		f_tax.setValue (Env.ZERO);
		//
		
		/*
		f_location = new CComboBox();
		add (f_location, " wrap");
	*/
		

		//
		CLabel lTotal = new CLabel (Msg.translate(Env.getCtx(), MOrder.COLUMNNAME_GrandTotal));
		//lTotal.setFont(bigFont);
		add(lTotal, "cell 2 4");
		f_total = new JFormattedTextField(DisplayType.getNumberFormat(DisplayType.Amount));
		f_total.setHorizontalAlignment(JTextField.TRAILING);
		//f_total.setFont(bigFont);
		f_total.setEditable(false);
		f_total.setFocusable(false);
		lTotal.setLabelFor(f_total);
		add(f_total, "cell 3 4");
		f_total.setValue (Env.ZERO);
		/*
		//
		f_user = new CComboBox();
		add (f_user, "skip 1");
		*/
	}	//	init

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

	
	/**
	 * 	Distribute actions
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		String action = e.getActionCommand();
		if (action == null || action.length() == 0)
			return;
		log.info( "PosSubCustomer - actionPerformed: " + action);
		//	New
		if (action.equals(ACTION_NEW))
		{
			p_posPanel.newOrder(); //red1 New POS Order instead - B_Partner already has direct field
			return;
		}
		else if (action.equals(ACTION_HISTORY))
		{
			// For already created, but either not completed or not yet paid POS Orders
			Boolean alternativeDocType = false;
			if(isAlternativeDocTypeEnabled) {
				if (org.compiere.apps.ADialog.ask(0, null, Msg.getMsg(p_ctx, "Do you want to use the alternate Document type?")))						
				{
					alternativeDocType = true;
				}			
			}
			PosQuery qt = new QueryTicket(p_posPanel);
			qt.setVisible(true);				
			p_posPanel.updateInfo();
			
			//  if (alternativeDocType)
			//	p_posPanel.m_order.setC_DocTypeTarget_ID(0);
		}
		else if (action.equals(ACTION_CANCEL))
			deleteOrder();
		else if (action.equals(ACTION_PAYMENT))
			payOrder();
		else if (action.equals(ACTION_PREPAYMENT))
			prePayOrder();
		else if (action.equals(ACTION_PRINT))
			printOrder();
		else if (action.equals(ACTION_BPARTNER))
		{	// Change to another BPartner
			PosQuery qt = new QueryBPartner(p_posPanel);
			qt.setVisible(true);
			findBPartner();
		}
		// Logout
		else if (action.equals(ACTION_LOGOUT))
		{
			p_posPanel.dispose();
			return;
		}
		//	Name
		else if (e.getSource() == f_name)
			findBPartner();
		
		else if (action.equals(ACTION_CREDITSALE))
			onCreditSale();
		
		p_posPanel.updateInfo();
	}	//	actionPerformed

	/**
	 * 	Execute printing an order
	 */
	private void printOrder() {
		{
			if (isOrderFullyPaid())
			{
				updateOrder();
				printTicket();
				openCashDrawer();
			}
		}
	}

	/**
	 * Execute order payment
	 * If order is not processed, process it first.
	 * If it is successful, proceed to pay and print ticket
	 */
	private void payOrder() {
		//Check if order is completed, if so, print and open drawer, create an empty order and set cashGiven to zero
		if( p_posPanel.m_order == null) {		
			ADialog.warn(0, p_posPanel,  Msg.getMsg(p_ctx, "You must create an Order first"));
		}
		else
		{
			if ( PosPayment.pay(p_posPanel) )
			{
				printTicket();
				p_posPanel.setOrder(0);
			}
		}	
	}  // payOrder

	/**
	 * Execute order prepayment
	 * If order is not processed, process it first.
	 * If it is successful, proceed to pay and print ticket
	 */
	private void prePayOrder() {
		//Check if order is completed, if so, print and open drawer, create an empty order and set cashGiven to zero
		if( p_posPanel.m_order == null) {		
			ADialog.warn(0, p_posPanel,  Msg.getMsg(p_ctx, "You must create an Order first"));
		}
		else
		{
			if ( PosPrePayment.pay(p_posPanel) )
			{
				p_posPanel.setOrder(0);
			}
		}	
	}  // prePayOrder

	/**
	 * Execute deleting an order
	 * If the order is in drafted status -> ask to delete it
	 * If the order is in completed status -> ask to void it it
	 * Otherwise, it must be done outside this class.
	 */
	private void deleteOrder() {
		if (p_posPanel == null || p_posPanel.m_order == null) {
			ADialog.warn(0, p_posPanel,  Msg.getMsg(p_ctx, "You must create an Order first"));
			return;			
		}
		else if ( p_posPanel.m_order.getDocStatus().equals(MOrder.STATUS_Drafted) ) {
			if (ADialog.ask(0, this, Msg.getMsg(p_ctx, "Do you want to delete the Order?"))) {
				if (p_posPanel.m_order.deleteOrder())
					p_posPanel.m_order = null;	
				else
					ADialog.warn(0, p_posPanel,  Msg.getMsg(p_ctx, "Order could not be deleted"));
			}
		}
		else if (p_posPanel.m_order.getDocStatus().equals(MOrder.STATUS_Completed)) {	
			if (ADialog.ask(0, this, Msg.getMsg(p_ctx, Msg.getMsg(p_ctx, "The order is already completed. Do you want to void it?")))) {		
				if (!p_posPanel.m_order.cancelOrder())
					ADialog.warn(0, p_posPanel,  Msg.getMsg(p_ctx, "Order could not be voided"));
			}
		}
		else {
			ADialog.warn(0, p_posPanel,  Msg.getMsg(p_ctx, "Order is not Drafted nor Completed. Try to delete it other way"));
			return;
		}
		
		updateOrder();
		// p_posPanel.newOrder();

	} // deleteOrder

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
		getBPartner();
		
		if (query == null || query.length() == 0)
			return;
		
		// unchanged
		if ( m_bpartner != null && m_bpartner.getName().equals(query))
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
			QueryBPartner qt = new QueryBPartner(p_posPanel);
			qt.setResults (results);
			qt.setVisible(true);
		}
	}	//	findBPartner

	/**
	 * 	Process the order.
	 * Usually, the action should be "complete".
	 */
	private void onCreditSale()
	{
		if( p_posPanel.m_order == null) {		
			ADialog.warn(0, p_posPanel,  Msg.getMsg(p_ctx, "You must create an Order first"));
		}
		else {
			if ( p_posPanel.m_order.getLines().length==0) {
				ADialog.warn(0, p_posPanel, Msg.getMsg(p_ctx, "The Order does not contain lines"));
			}
			else if ( !p_posPanel.m_order.isProcessed() && !p_posPanel.m_order.processOrder() ) {		
				ADialog.warn(0, p_posPanel, Msg.getMsg(p_ctx, "Error processing Credit sale"));
			}
		}
		return;
	} // onCreditSale
	
	
	/**
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
		}
		else
		{
			f_name.setText(null);
		}
		//	Sets Currency
		m_M_PriceList_Version_ID = 0;
		getM_PriceList_Version_ID();
		//fillCombos();
		if ( p_posPanel.m_order != null && m_bpartner != null )
			p_posPanel.m_order.setBPartner(m_bpartner);  //added by ConSerTi to update the client in the request
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
			setCurrency(MCurrency.getISO_Code(p_ctx, pl.getC_Currency_ID()));
			f_name.setToolTipText(pl.getName());
			//
			MPriceListVersion plv = pl.getPriceListVersion (p_posPanel.getToday());
			if (plv != null && plv.getM_PriceList_Version_ID() != 0)
				m_M_PriceList_Version_ID = plv.getM_PriceList_Version_ID();
		}
		return m_M_PriceList_Version_ID;
	}	//	getM_PriceList_Version_ID
	

	/***************************************************************************
	 * Set Currency
	 * 
	 * @param currency
	 * 
	 */
	public void setCurrency(String currency) {
		if (currency == null)
			f_currency.setText("---");
		else
			f_currency.setText(currency);
	} //	setCurrency
	
	/**
	 * 	Print Ticket
	 * 
	 */
	public void printTicket()
	{
		if ( p_posPanel.m_order == null )
			return;
		
		MOrder order = p_posPanel.m_order;
		//int windowNo = p_posPanel.getWindowNo();
		//Properties m_ctx = p_posPanel.getPropiedades();
		
		if (order != null)
		{
			try 
			{
				//TODO: to incorporate work from Posterita
				/*
				if (p_pos.getAD_PrintLabel_ID() != 0)
					PrintLabel.printLabelTicket(order.getC_Order_ID(), p_pos.getAD_PrintLabel_ID());
				*/ 
				//print standard document
				Boolean print = true;
				if (p_pos.getAD_Sequence_ID()!= 0)
				{
					MSequence seq = new MSequence(Env.getCtx(), p_pos.getAD_Sequence_ID(), order.get_TrxName());
					String docno = seq.getPrefix() + seq.getCurrentNext();
					String q = "Confirmar el número consecutivo "  + docno;
					if (org.compiere.apps.ADialog.ask(0, null, q))						
					{
						order.setPOReference(docno);
						order.saveEx();
						ReportCtl.startDocumentPrint(0, order.getC_Order_ID(), false);
						int next = seq.getCurrentNext() + seq.getIncrementNo();
						seq.setCurrentNext(next);
						seq.saveEx();
					}
				}
				else
					ReportCtl.startDocumentPrint(0, order.getC_Order_ID(), false);				
			}
			catch (Exception e) 
			{
				log.severe("PrintTicket - Error Printing Ticket");
			}
		}	  
	}	
	
	/**
	 * Is order fully pay ?
	 * Calculates if the given money is sufficient to pay the order
	 * 
	 */
	public boolean isOrderFullyPaid()
	{
		/*TODO
		BigDecimal given = new BigDecimal(f_cashGiven.getValue().toString());
		boolean paid = false;
		if (p_posPanel != null && p_posPanel.f_curLine != null)
		{
			MOrder order = p_posPanel.f_curLine.getOrder();
			BigDecimal total = new BigDecimal(0);
			if (order != null)
				total = order.getGrandTotal();
			paid = given.doubleValue() >= total.doubleValue();
		}
		return paid;
		*/
		return true;
	}
	
	/**
	 * 	Display cash return
	 *  Display the difference between tender amount and bill amount
	 *  
	 */
	public void updateOrder()
	{
		if (p_posPanel != null )
		{
			MOrder order = p_posPanel.m_order;
			if (order != null)
			{
  				f_DocumentNo.setText(order.getDocumentNo());
  				setC_BPartner_ID(order.getC_BPartner_ID());
  				f_bNew.setEnabled(order.getLines().length != 0);
  				f_bEdit.setEnabled(true);
  				f_history.setEnabled(order.getLines().length != 0);
  				f_process.setEnabled(true);
  				f_print.setEnabled(order.isProcessed());
  				f_cashPayment.setEnabled(order.getLines().length != 0);
  				f_cashPrePayment.setEnabled(order.getLines().length != 0);
			}
			else
			{
				f_DocumentNo.setText(null);
				setC_BPartner_ID(0);
				f_bNew.setEnabled(true);
				f_bEdit.setEnabled(false);
				f_history.setEnabled(true);
				f_process.setEnabled(false);
				f_print.setEnabled(false);
				f_cashPayment.setEnabled(false);
				f_cashPrePayment.setEnabled(false);
			}
			
		}
	}	

	/**
	 * 	Open cash drawer
	 * 
	 */
	public void openCashDrawer()
	{
		String port = "/dev/lp";
		
		byte data[] = new byte[] {0x1B, 0x40, 0x1C};
		try {  
            FileOutputStream m_out = null;
			if (m_out == null) {
                m_out = new FileOutputStream(port);  // No poner append = true.
            }
            m_out.write(data);
        } catch (IOException e) {
        }  
	}	

	/**
	 * 	Set Sums from Table
	 */
	void setSums(PosOrderModel order)
	{
		int noLines = p_posPanel.f_curLine.m_table.getRowCount();
		if (order == null || noLines == 0)
		{
			f_net.setValue(Env.ZERO);
			f_total.setValue(Env.ZERO);
			f_tax.setValue(Env.ZERO);
		}
		else
		{
			// order.getMOrder().prepareIt();
			f_net.setValue(order.getSubtotal());
			f_total.setValue(order.getGrandTotal());
			f_tax.setValue(order.getTaxAmt());

		}
	}	//	setSums
}//	PosSubCustomer
	
