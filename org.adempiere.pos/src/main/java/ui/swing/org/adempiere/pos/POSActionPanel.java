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

package org.adempiere.pos;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.pos.search.POSQuery;
import org.adempiere.pos.search.QueryBPartner;
import org.adempiere.pos.search.QueryProduct;
import org.adempiere.pos.search.QueryTicket;
import org.adempiere.pos.service.I_POSPanel;
import org.adempiere.pos.service.I_POSQuery;
import org.adempiere.pos.service.POSQueryListener;
import org.compiere.apps.ADialog;
import org.compiere.model.I_M_Product;
import org.compiere.model.MPOSKey;
import org.compiere.model.MWarehousePrice;
import org.compiere.print.ReportCtl;
import org.compiere.swing.CButton;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;


/**
 *	Customer Sub Panel
 *	
 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright � Jorg Janke
 *         *Copyright � ConSerTi
 *  @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  <li> Implement best practices
 *  @version $Id: SubOrder.java,v 1.1 2004/07/12 04:10:04 jjanke Exp $
 *  @version $Id: SubOrder.java,v 2.0 2015/09/01 00:00:00 mar_cal_westf
 *  @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public class POSActionPanel extends POSSubPanel 
	implements ActionListener, I_POSPanel, POSQueryListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5895558315889871887L;

	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public POSActionPanel (VPOS posPanel) {
		super (posPanel);
	}	//	PosSubCustomer
	
	/**	Buttons Command		*/
	private CButton 			f_bNew;
	private CButton 			f_bBPartner;
	private CButton 			f_bHistory;
	private CButton 			f_bBack;
	private CButton 			f_bNext;
	private CButton 			f_bCollect;
	private CButton 			f_bCancel;
	private CButton 			f_bLogout;
	/**	Button Panel		*/
	private CPanel				v_ButtonPanel;
	/**	Info Product Panel	*/
	private POSInfoProduct		v_InfoProductPanel;
	/**	For Show BPartner	*/
	private	POSTextField		f_ProductName;
	/**	Padding				*/
	private int 				m_TopP;
	private int 				m_LeftP;
	private int 				m_BottonP;
	private int 				m_RightP;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(POSActionPanel.class);	

	private final String ACTION_NEW         = "New";
	private final String ACTION_BPARTNER    = "BPartner";
	private final String ACTION_HISTORY     = "History";
	private final String ACTION_BACK       	= "Previous";
	private final String ACTION_NEXT  		= "Next";
	private final String ACTION_PAYMENT     = "Payment";
	private final String ACTION_CANCEL      = "Cancel";
	private final String ACTION_LOGOUT      = "End";
	
	/**
	 * 	Initialize
	 */
	public void init() {
		//	Content
		setLayout(new GridBagLayout());
		//	Button Panel
		v_ButtonPanel = new CPanel(new GridBagLayout());
		v_InfoProductPanel = new POSInfoProduct(v_POSPanel);
		//	
		m_TopP = 0;
		m_LeftP = 1;
		m_BottonP = 0;
		m_RightP = 1;
		// NEW
		f_bNew = createButtonAction(ACTION_NEW, KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.F2));
		f_bNew.setPreferredSize(new Dimension(v_POSPanel.getButtonSize(), v_POSPanel.getButtonSize()));
		v_ButtonPanel.add(f_bNew, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
		// BPARTNER
		f_bBPartner = createButtonAction (ACTION_BPARTNER, KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.SHIFT_MASK+Event.CTRL_MASK));
		f_bBPartner.setPreferredSize(new Dimension(v_POSPanel.getButtonSize(), v_POSPanel.getButtonSize()));
		v_ButtonPanel.add(f_bBPartner, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
		// HISTORY
		f_bHistory = createButtonAction(ACTION_HISTORY, null);
		f_bHistory.setPreferredSize(new Dimension(v_POSPanel.getButtonSize(), v_POSPanel.getButtonSize()));
		v_ButtonPanel.add(f_bHistory, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
 		// 	BACK
 		f_bBack = createButtonAction(ACTION_BACK, null);
 		f_bBack.setPreferredSize(new Dimension(v_POSPanel.getButtonSize(), v_POSPanel.getButtonSize()));
 		v_ButtonPanel.add(f_bBack, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
 		f_bBack.setEnabled(true);
		
 		//	NEXT
 		f_bNext = createButtonAction(ACTION_NEXT, null);
 		f_bNext.setPreferredSize(new Dimension(v_POSPanel.getButtonSize(), v_POSPanel.getButtonSize()));
 		v_ButtonPanel.add(f_bNext, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
		f_bNext.setEnabled(true);
 		
 		// PAYMENT
 		f_bCollect = createButtonAction(ACTION_PAYMENT, null);
 		f_bCollect.setPreferredSize(new Dimension(v_POSPanel.getButtonSize(), v_POSPanel.getButtonSize()));
 		v_ButtonPanel.add(f_bCollect, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
		f_bCollect.setEnabled(false);
 		
 		// CANCEL
		f_bCancel = createButtonAction(ACTION_CANCEL, null);
		f_bCancel.setPreferredSize(new Dimension(v_POSPanel.getButtonSize(), v_POSPanel.getButtonSize()));
		v_ButtonPanel.add(f_bCancel, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
 		
		// Logout
		f_bLogout = createButtonAction (ACTION_LOGOUT, null);
		f_bLogout.setPreferredSize(new Dimension(v_POSPanel.getButtonSize(), v_POSPanel.getButtonSize()));
		v_ButtonPanel.add(f_bLogout, new GridBagConstraints(7, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
		// BP
		String labelName = Msg.translate(Env.getCtx(), I_M_Product.COLUMNNAME_M_Product_ID); 
		f_ProductName = new POSTextField(labelName, v_POSPanel.getKeyboard());
		f_ProductName.setPlaceholder(labelName);
		f_ProductName.addActionListener(this);
		f_ProductName.setFont(v_POSPanel.getFont());
		f_ProductName.setPreferredSize(new Dimension(250, v_POSPanel.getFieldLenght()));
		f_ProductName.setMinimumSize(new Dimension(250, v_POSPanel.getFieldLenght()));
		//	Add Button Panel
		add(v_ButtonPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1
				,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));
		add(f_ProductName, new GridBagConstraints(0, 1, 1, 1, 1, 1
				,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));
		add(v_InfoProductPanel, new GridBagConstraints(0, 2, 1, 2, 1, 2
				,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));
		//	List Orders
		v_POSPanel.listOrder();
	}	//	init

	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose()
	{
		f_ProductName = null;
		removeAll();
		super.dispose();
	}	//	dispose

	
	/**
	 * 	Distribute actions
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e) {
		String action = e.getActionCommand();
		if (action == null || action.length() == 0)
			return;
		log.info( "PosSubCustomer - actionPerformed: " + action);
		try {
				//	New
				if (e.getSource().equals(f_bNew)) {
					v_POSPanel.newOrder();
				} else if (e.getSource().equals(f_bBPartner)) {
					QueryBPartner qt = new QueryBPartner(v_POSPanel);
					qt.addOptionListener(this);
					qt.setResults(null);
					qt.showView();
				} else if (e.getSource().equals(f_bHistory)) {
					// For already created, but either not completed or not yet paid POS Orders
					POSQuery qt = new QueryTicket(v_POSPanel);
					qt.addOptionListener(this);
					qt.showView();
					return;
				} else if (e.getSource().equals(f_bBack)){
					previousRecord();
				} else if (e.getSource().equals(f_bNext)){
					nextRecord();
				} else if (e.getSource().equals(f_bCollect)) {
					payOrder();
				} else if (e.getSource().equals(f_bCancel)) {
					deleteOrder();
				} else if (e.getSource().equals(f_bLogout)) {	//	Logout
					v_POSPanel.dispose();
					return;
				} else if (e.getSource().equals(f_ProductName)) {
					findProduct();
					return;
				}
				//	Refresh
				v_POSPanel.refreshPanel();
		} catch (AdempiereException exception) {
			ADialog.error(v_POSPanel.getWindowNo(), this, exception.getLocalizedMessage());
		}
	}	//	actionPerformed
	
	/**************************************************************************
	 * 	Find/Set Product & Price
	 */
	private void findProduct() {
		String query = f_ProductName.getText();
		if (query == null || query.length() == 0)
			return;
		query = query.toUpperCase();
		//	Test Number
		boolean allNumber = true;
		try {
			Integer.getInteger(query);
		} catch (Exception e) {
			allNumber = false;
		}
		String Value = query;
		String Name = query;
		String UPC = (allNumber ? query : null);
		String SKU = (allNumber ? query : null);
		//	
		MWarehousePrice[] results = MWarehousePrice.find (m_ctx,
				v_POSPanel.getM_PriceList_Version_ID(), v_POSPanel.getM_Warehouse_ID(), 
				Value, Name, UPC, SKU, null);
		
		//	Set Result
		if (results.length == 1) {	//	one
			v_POSPanel.addLine(results[0].getM_Product_ID(), Env.ONE);
			f_ProductName.setText(results[0].getName());
		} else {	//	more than one
			v_POSPanel.getFrame().getContentPane().invalidate();
			QueryProduct qt = new QueryProduct(v_POSPanel);
			qt.addOptionListener(this);
			qt.setResults(results);
			qt.setQueryData(v_POSPanel.getM_PriceList_Version_ID(), v_POSPanel.getM_Warehouse_ID());
			qt.showView();
		}
	}	//	findProduct

	/**
	 * 	Execute printing an order
	 */
//	private void printOrder() {
//		{
//			if (isOrderFullyPaid())
//			{
//				changeViewPanel();
//				printTicket();
//				openCashDrawer();
//			}
//		}
//	}
	
	/**
	 * Previous Record Order
	 */
	public void previousRecord() {
		v_POSPanel.previousRecord();
		//	Refresh
		v_POSPanel.refreshPanel();
	}

	/**
	 * Next Record Order
	 */
	public void nextRecord() {
		v_POSPanel.nextRecord();
		//	Refresh
		v_POSPanel.refreshPanel();
	}
	
	/**
	 * Execute order payment
	 * If order is not processed, process it first.
	 * If it is successful, proceed to pay and print ticket
	 */
	private void payOrder() {
		//Check if order is completed, if so, print and open drawer, create an empty order and set cashGiven to zero
		if(!v_POSPanel.hasOrder()) {		
			ADialog.warn(v_POSPanel.getWindowNo(), this,  Msg.getMsg(m_ctx, "POS.MustCreateOrder"));
		} else {
			VCollect collect = new VCollect(v_POSPanel);
			if (collect.showCollect()) {
				//	Print Ticket just when is completed
				if(v_POSPanel.isToPrint()) {
					printTicket();
				}
				//	
				v_POSPanel.setOrder(0);
				v_POSPanel.refreshPanel();
			}
		}	
	}  // payOrder

	/**
	 * Execute deleting an order
	 * If the order is in drafted status -> ask to delete it
	 * If the order is in completed status -> ask to void it it
	 * Otherwise, it must be done outside this class.
	 */
	private void deleteOrder() {
		String errorMsg = null;
		String askMsg = "POS.DeleteOrder";	//	TODO Translate it: Do you want to delete Order?
		if (v_POSPanel.isCompleted()) {	
			askMsg = "POS.OrderIsAlreadyCompleted";	//	TODO Translate it: The order is already completed. Do you want to void it?
		}
		//	Show Ask
		if (ADialog.ask(0, this, Msg.getMsg(m_ctx, Msg.getMsg(m_ctx, askMsg)))) {
			v_POSPanel.getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			//	Cancel Order
			errorMsg = v_POSPanel.cancelOrder();
			//	Set Cursor to default
			v_POSPanel.getFrame().setCursor(Cursor.getDefaultCursor());
		}
		//	show if exists error
		if(errorMsg != null) {
			ADialog.error(v_POSPanel.getWindowNo(), 
					this, Msg.parseTranslation(m_ctx, errorMsg));
		}
		//	Update
		v_POSPanel.refreshPanel();
	} // deleteOrder
	
	/**
	 * 	Print Ticket
	 * 
	 */
	public void printTicket() {
		if (!v_POSPanel.hasOrder())
			return;
		//	
		
		//int windowNo = p_posPanel.getWindowNo();
		//Properties m_ctx = p_posPanel.getPropiedades();
			try 
			{
				//TODO: to incorporate work from Posterita
				/*
				if (p_pos.getAD_PrintLabel_ID() != 0)
					PrintLabel.printLabelTicket(order.getC_Order_ID(), p_pos.getAD_PrintLabel_ID());
				*/ 
				//print standard document
//				Boolean print = true;
				Trx.run(new TrxRunnable() {
					public void run(String trxName) {
						if (m_pos.getAD_Sequence_ID()!= 0) {
							
							String docno = v_POSPanel.getSequenceDoc(trxName);
							String q = "Confirmar el número consecutivo "  + docno;
							if (ADialog.ask(v_POSPanel.getWindowNo(), v_POSPanel.getFrame(), q)) {
								v_POSPanel.setPOReference(docno);
								v_POSPanel.saveNextSeq(trxName);
							}
						}
					}
				});
				ReportCtl.startDocumentPrint(0, v_POSPanel.getC_Order_ID(), false);
			}
			catch (Exception e) {
				log.severe("PrintTicket - Error Printing Ticket");
			}
			
			  
	}
	
	/**
	 * Refresh Product Info from Key
	 * @param key
	 * @return void
	 */
	public void refreshProductInfo(MPOSKey key) {
		v_InfoProductPanel.refreshProduct(key);
	}
	
	/**
	 * Refresh Product Info from Product
	 * @param p_M_Product_ID
	 * @return void
	 */
	public void refreshProductInfo(int p_M_Product_ID) {
		v_InfoProductPanel.refreshProduct(p_M_Product_ID);
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
	 * 	Open cash drawer
	 * 
	 */
//	public void openCashDrawer()
//	{
//		String port = "/dev/lp";
//		
//		byte data[] = new byte[] {0x1B, 0x40, 0x1C};
//		try {  
//            FileOutputStream m_out = null;
//			if (m_out == null) {
//                m_out = new FileOutputStream(port);  // No poner append = true.
//            }
//            m_out.write(data);
//        } catch (IOException e) {
//        }  
//	}

	@Override
	public void refreshPanel() {
		//	
	}

	@Override
	public String validatePanel() {
		return null;
	}

	@Override
	public void changeViewPanel() {
		if(v_POSPanel.hasOrder()) {
			//	For Next
			f_bNext.setEnabled(!v_POSPanel.isLastRecord() && v_POSPanel.hasRecord());
			//	For Back
			f_bBack.setEnabled(!v_POSPanel.isFirstRecord() && v_POSPanel.hasRecord());
			//	For Collect
			if(v_POSPanel.hasLines()
					&& !v_POSPanel.isPaid()
					&& !v_POSPanel.isVoided()) {
				//	For Credit Order
				f_bCollect.setEnabled(true);
			} else {
				f_bCollect.setEnabled(false);
			}
			//	For Cancel Action
			f_bCancel.setEnabled(!v_POSPanel.isVoided());
		} else {
			f_bNew.setEnabled(true);
			f_bHistory.setEnabled(true);
			//	For Next
			f_bNext.setEnabled(!v_POSPanel.isLastRecord() && v_POSPanel.hasRecord());
			//	For Back
			f_bBack.setEnabled(!v_POSPanel.isFirstRecord() && v_POSPanel.hasRecord());
			f_bCollect.setEnabled(false);
			//	For Cancel Action
			f_bCancel.setEnabled(false);
		}
	}

	@Override
	public void okAction(I_POSQuery query) {
		if (query.getRecord_ID() <= 0)
			return;
		//	For Ticket
		if(query instanceof QueryTicket) {
			v_POSPanel.setOrder(query.getRecord_ID());
			v_POSPanel.reloadIndex(query.getRecord_ID()); 
		} else if(query instanceof QueryBPartner) {
			if(!v_POSPanel.hasOrder()) {
				v_POSPanel.newOrder(query.getRecord_ID());
			} else {
				v_POSPanel.setC_BPartner_ID(query.getRecord_ID());
			}
			//	
			log.fine("C_BPartner_ID=" + query.getRecord_ID());
		} else if(query instanceof QueryProduct) {
			if (query.getRecord_ID() > 0) {
				v_POSPanel.addLine(query.getRecord_ID(), Env.ONE);
			}
		}
		//	Refresh
		v_POSPanel.refreshPanel();
	}

	@Override
	public void cancelAction(I_POSQuery query) {
		//	Nothing
	}	
}//	POSActionPanel