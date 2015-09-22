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
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.KeyStroke;

import net.miginfocom.swing.MigLayout;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerInfo;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCurrency;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MSequence;
import org.compiere.model.MUser;
import org.compiere.print.ReportCtl;
import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
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
 *  @author Mario Calderon, Systemhaus Westfalia
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
	public SubOrder (VPOS posPanel)
	{
		super (posPanel);
	}	//	PosSubCustomer
	
	private CButton 		f_history;
	private	CTextField		f_name;
	private CButton 		f_bNew;
	private CButton 		f_bBPartner;
	private CComboBox		f_location;
	private CComboBox		f_user;
	private CButton 		f_cashPayment;
	private CButton 		f_Next;
	private CButton 		f_Back;
	private CButton 		f_Cancel;
	private CButton 		f_logout;
	
	/**	The Business Partner		*/
	private MBPartner	m_bpartner;
	/**	Price List Version to use	*/
	private int					m_M_PriceList_Version_ID = 0;
	private CTextField 			f_currency = new CTextField();
	private CButton 			f_bCreditSale;
	private int 				recordPosition;
	private ArrayList<Integer>	orderList;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(SubOrder.class);	

	private final String ACTION_BPARTNER    = "BPartner";
	private final String ACTION_CANCEL      = "Cancel";
	private final String ACTION_CREDITSALE  = "Credit Sale";
	private final String ACTION_HISTORY     = "History";
	private final String ACTION_LOGOUT      = "End";
	private final String ACTION_NEW         = "New";
	private final String ACTION_PAYMENT     = "Payment";
	private final String ACTION_NEXT  		= "Next";
	private final String ACTION_BACK       	= "Back";
	
	/**
	 * 	Initialize
	 */
	public void init()
	{
		//	Content
		MigLayout layout = new MigLayout("ins 0 0","[fill|fill|fill|fill]","[nogrid]unrel[||]");
		setLayout(layout);
		listOrder();
		recordPosition = orderList.size()-1;
		Font bigFont = AdempierePLAF.getFont_Field().deriveFont(16f);

		String buttonSize = "w 50!, h 50!,";
		// NEW
		f_bNew = createButtonAction(ACTION_NEW, KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.F2));
		add (f_bNew, buttonSize+",gapx 35");

		// BPARTNER
		f_bBPartner = createButtonAction (ACTION_BPARTNER, KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.SHIFT_MASK+Event.CTRL_MASK));
		add (f_bBPartner,buttonSize+",gapx 35" );
		
		// CREDIT SALE
		f_bCreditSale = createButtonAction(ACTION_CREDITSALE, null);
		add(f_bCreditSale, buttonSize+",gapx 35");
 		f_bCreditSale.setEnabled(false);
		
		// HISTORY
		f_history = createButtonAction(ACTION_HISTORY, null);
 		add (f_history, buttonSize+",gapx 35"); 
		
 		// 	BACK
 		f_Back = createButtonAction(ACTION_BACK, null);
 		add (f_Back, buttonSize+",gapx 35");
 		f_Back.setEnabled(true);
		
 		//	NEXT
 		f_Next = createButtonAction(ACTION_NEXT, null);
 		f_Next.setActionCommand(ACTION_NEXT);
		add (f_Next, buttonSize+",gapx 35"); 
		f_Next.setEnabled(true);
 		
 		// PAYMENT
 		f_cashPayment = createButtonAction(ACTION_PAYMENT, null);
		f_cashPayment.setActionCommand(ACTION_PAYMENT);
		add (f_cashPayment, buttonSize+",gapx 35"); 
		f_cashPayment.setEnabled(false);
 		
 		// CANCEL
		f_Cancel = createButtonAction(ACTION_CANCEL, null);
 		add (f_Cancel, buttonSize +",gapx 35");
 		
		// Logout
		f_logout = createButtonAction (ACTION_LOGOUT, null);
		add (f_logout, buttonSize + ", gapx 35, wrap");

		// BP
//		CLabel BPLabelLabel = new CLabel(Msg.translate(Env.getCtx(), MBPartner.COLUMNNAME_C_BPartner_ID)); 
//		add(BPLabelLabel, "");
		f_name = new CTextField();
		f_name.setEditable(false);
		f_name.setName(MBPartner.COLUMNNAME_Name);
//		add (f_name, "wrap,spanx 3, growx");

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
			PosQuery qt = new QueryTicket(p_posPanel);
			qt.setVisible(true);				
			p_posPanel.updateInfo();
		}
		else if (action.equals(ACTION_CANCEL))
			deleteOrder();
		else if (action.equals(ACTION_PAYMENT))
			payOrder();
		else if (action.equals(ACTION_NEXT)){
			nextRecord();
			p_posPanel.updateInfo();
		}
		else if (action.equals(ACTION_BACK)){
			previousRecord();
			p_posPanel.updateInfo();
		}
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
	 * Previous Record Order
	 */
	public void previousRecord() {
		if(recordPosition>0)
			p_posPanel.setOrder(orderList.get(recordPosition--));
	}

	/**
	 * Next Record Order
	 */
	public void nextRecord() {
		if(recordPosition < orderList.size()-1)
			p_posPanel.setOrder(orderList.get(recordPosition++));
		
	}
	/**
	 * Execute order payment
	 * If order is not processed, process it first.
	 * If it is successful, proceed to pay and print ticket
	 */
	private void payOrder() {
		//Check if order is completed, if so, print and open drawer, create an empty order and set cashGiven to zero
		if( p_posPanel.getM_Order() == null) {		
			ADialog.warn(0, p_posPanel.f_curLine,  Msg.getMsg(p_ctx, "You must create an Order first"));
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
//	private void prePayOrder() {
//		//Check if order is completed, if so, print and open drawer, create an empty order and set cashGiven to zero
//		if( p_posPanel.m_order == null) {		
//			ADialog.warn(0, p_posPanel,  Msg.getMsg(p_ctx, "You must create an Order first"));
//		}
//		else if(p_posPanel.m_order.getDocStatus().equals(MOrder.STATUS_Drafted) ) {
//			if(p_posPanel.m_order.getLines().length == 0) 
//				ADialog.warn(0, p_posPanel,  Msg.getMsg(p_ctx, "Order must have lines"));
//			else if ( PosPrePayment.pay(p_posPanel) ) {
//				p_posPanel.setOrder(0);
//			}
//		}
//		else if(p_posPanel.m_order.getDocStatus().equals(MOrder.DOCSTATUS_Completed)) {
//			if(!p_posPanel.m_order.getC_DocType().getDocSubTypeSO().equalsIgnoreCase(MOrder.DocSubTypeSO_Standard) ||
//				p_posPanel.m_order.getC_Invoice_ID()>0) {
//				ADialog.warn(0, p_posPanel,  Msg.getMsg(p_ctx, "It must be a not invoiced standard order"));
//			}
//			else { // OK -> proceed to prepayment
//				if ( PosPrePayment.pay(p_posPanel) ) {
//					p_posPanel.setOrder(0);
//				}
//			}	
//		}				    
//	}  // prePayOrder

	/**
	 * Execute deleting an order
	 * If the order is in drafted status -> ask to delete it
	 * If the order is in completed status -> ask to void it it
	 * Otherwise, it must be done outside this class.
	 */
	private void deleteOrder() {
		if (p_posPanel == null || p_posPanel.getM_Order() == null) {
			ADialog.warn(p_posPanel.getWindowNo(), p_posPanel.f_curLine.getParent(),  Msg.getMsg(p_ctx, "You must create an Order first"));
			return;			
		}
		else if (p_posPanel.getM_Order().getDocStatus().equals(MOrder.STATUS_Drafted) ) {
			if (ADialog.ask(p_posPanel.getWindowNo(), p_posPanel.f_curLine.getParent(), Msg.getMsg(p_ctx, "Do you want to delete the Order?"))) {
				if (!p_posPanel.deleteOrder())
//					p_posPanel.getM_Order() = null;	
//				else
					ADialog.warn(p_posPanel.getWindowNo(), p_posPanel.f_curLine.getParent(), Msg.getMsg(p_ctx, "Order could not be deleted"));
			}
		}
		else if (p_posPanel.getM_Order().getDocStatus().equals(MOrder.STATUS_Completed)) {	
			if (ADialog.ask(0, this, Msg.getMsg(p_ctx, Msg.getMsg(p_ctx, "The order is already completed. Do you want to void it?")))) {		
				if (!p_posPanel.cancelOrder())
					ADialog.warn(p_posPanel.getWindowNo(), p_posPanel.f_curLine.getParent(), Msg.getMsg(p_ctx, "Order could not be voided"));
			}
		}
		else {
			ADialog.warn(p_posPanel.getWindowNo(), p_posPanel.f_curLine.getParent(),  Msg.getMsg(p_ctx, "Order is not Drafted nor Completed. Try to delete it other way"));
			return;
		}
		
		updateOrder();

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
	private void onCreditSale() {
		if( p_posPanel.getM_Order() == null) {		
			ADialog.warn(p_posPanel.getWindowNo(), p_posPanel.f_curLine.getParent(),  Msg.getMsg(p_ctx, "You must create an Order first"));
		} else {
			if ( p_posPanel.getM_Order().getLines().length==0) {
				ADialog.warn(p_posPanel.getWindowNo(), null, Msg.getMsg(p_ctx, "The Order does not contain lines"));
			} else if ( !p_posPanel.getM_Order().isProcessed() 
					&& !p_posPanel.processOrder()) {		
				ADialog.warn(p_posPanel.getWindowNo(), p_posPanel.f_curLine.getParent(), Msg.getMsg(p_ctx, "Error processing Credit sale"));
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
		if ( p_posPanel.getM_Order() != null && m_bpartner != null )
			p_posPanel.getM_Order().setBPartner(m_bpartner);  //added by ConSerTi to update the client in the request
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
		if (p_posPanel.getM_Order() == null)
			return;
		
		MOrder order = p_posPanel.getM_Order();
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
					if (org.compiere.apps.ADialog.ask(0, p_posPanel.f_curLine.getParent(), q))						
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
	 * 	Update panel butttons
	 *  Enable or disable buttons according to the context
	 *  
	 */
	public void updateOrder()
	{
		if (p_posPanel != null )
		{
			MOrder order = p_posPanel.getM_Order();
			if (order != null)
			{
  				p_posPanel.f_curLine.f_DocumentNo.setText(order.getDocumentNo());
  				
  				// Button BPartner: enable when order drafted, and order has no lines
  				setC_BPartner_ID(order.getC_BPartner_ID());  				
  				if(order.getDocStatus().equals(MOrder.DOCSTATUS_Drafted) && 
  						order.getLines().length == 0 )
  					f_bBPartner.setEnabled(true);
  				else
  					f_bBPartner.setEnabled(false);

  				// Button New: enabled when lines existing or order is voided
  				f_bNew.setEnabled(order.getLines().length != 0 || order.getDocStatus().equals(MOrder.DOCSTATUS_Voided));
  				
  				// Button Credit Sale: enabled when drafted, with lines and not invoiced
  				if(order.getDocStatus().equals(MOrder.DOCSTATUS_Drafted) && 
  						order.getLines().length != 0 && 
  						order.getC_Invoice_ID()<=0)
  					f_bCreditSale.setEnabled(true);
  				else
  					f_bCreditSale.setEnabled(false);

  			    // History Button: enabled when lines existing or order is voided
  				if(order.getLines().length != 0 || order.getDocStatus().equals(MOrder.DOCSTATUS_Voided))
  	  				f_history.setEnabled(true);  	
  				else
  					f_history.setEnabled(false);

  				if(!order.getDocStatus().equals(MOrder.DOCSTATUS_Voided))			
  	  				f_Cancel.setEnabled(true);
  				else
  					f_Cancel.setEnabled(false);
 				
  				// Button Payment: enable when (drafted, with lines) or (completed, on credit, (not invoiced or not paid) ) 
  				if((order.getDocStatus().equals(MOrder.DOCSTATUS_Drafted) && order.getLines().length != 0) ||
  				   (order.getDocStatus().equals(MOrder.DOCSTATUS_Completed) && 
  				    order.getC_DocType().getDocSubTypeSO().equalsIgnoreCase(MOrder.DocSubTypeSO_OnCredit) &&
  				    	(order.getC_Invoice_ID()<=0  ||
  				    	 !MInvoice.get(p_ctx, order.getC_Invoice_ID()).isPaid()
  				    	 )
  				   )
  				  )
  					f_cashPayment.setEnabled(true);
  				else 
					f_cashPayment.setEnabled(false);	
  				
  			    // Next and Back Buttons:  enabled when lines existing or order is voided
  				if(order.getLines().length != 0 || order.getDocStatus().equals(MOrder.DOCSTATUS_Voided)) {

  					if(recordPosition==orderList.size()-1)
  					    f_Next.setEnabled(false); // End of order list
  					else
  	  					f_Next.setEnabled(true);

  					if(recordPosition==0)
  						f_Back.setEnabled(false); // Begin of order list
  					else
  						f_Back.setEnabled(true);
  				}
  				else{
  					f_Next.setEnabled(false);
  	  				f_Back.setEnabled(false);
  				}
 				
			}
			else
			{
				p_posPanel.f_curLine.f_DocumentNo.setText(null);
				setC_BPartner_ID(0);
				f_bBPartner.setEnabled(false);
				f_bNew.setEnabled(true);
				f_bCreditSale.setEnabled(false);
				f_history.setEnabled(true);
				f_Cancel.setEnabled(false);
				f_cashPayment.setEnabled(false);
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
	void setSums(MOrder order) {
//		int noLines = p_posPanel.f_curLine.m_table.getRowCount();
		if (order == null )
		{
			p_posPanel.f_curLine.f_net.setText(Env.ZERO.toString());
			p_posPanel.f_curLine.f_total.setText(Env.ZERO.toString());
			p_posPanel.f_curLine.f_tax.setText(Env.ZERO.toString());
		}
		else
		{
			// order.getMOrder().prepareIt();
			p_posPanel.f_curLine.f_net.setText(order.getTotalLines().toString());
			p_posPanel.f_curLine.f_total.setText(order.getGrandTotal().toString());
			BigDecimal total = new BigDecimal(p_posPanel.f_curLine.f_total.getText());
			BigDecimal totalNet = new BigDecimal(p_posPanel.f_curLine.f_net.getText());
			
			BigDecimal tax = total.subtract(totalNet);
			p_posPanel.f_curLine.f_tax.setText(tax.toString());
			p_posPanel.f_curLine.validate();
			p_posPanel.f_curLine.repaint();
		}
	}	//	setSums
	
	/**
	 * Get Data List Order
	 */
	public void listOrder() {
		String sql = "";
		PreparedStatement pstm;
		ResultSet rs;
		orderList = new ArrayList<Integer>();
		try 
		{
			sql=" SELECT o.C_Order_ID"
					+ " FROM C_Order o"
					+ " LEFT JOIN c_invoice i ON i.c_order_ID = o.c_order_ID"
					+ " WHERE"
					+ " (coalesce(invoiceopen(i.c_invoice_ID, 0), 0) > 0 OR o.docstatus IN ('DR', 'IP') ) AND "
					+ " o.issotrx='Y' AND "
					+ " o.ad_client_id=? "
					+ " ORDER BY o.dateordered ASC, o.datepromised ASC";
			
			pstm= DB.prepareStatement(sql, null);
			pstm.setInt (1, Env.getAD_Client_ID(Env.getCtx()));
			rs = pstm.executeQuery();
			int i = 0;
			while(rs.next()){
				orderList.add(rs.getInt(1));
				
			}
		}
		catch(Exception e)
		{
			log.severe("SubOrder.listOrder: " + e + " -> " + sql);
		}
	}
}//	PosSubCustomer
	
