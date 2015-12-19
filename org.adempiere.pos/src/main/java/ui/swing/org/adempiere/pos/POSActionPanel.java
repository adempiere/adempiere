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
import javax.swing.SwingUtilities;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.pos.search.POSQuery;
import org.adempiere.pos.search.QueryBPartner;
import org.adempiere.pos.search.QueryDocType;
import org.adempiere.pos.search.QueryProduct;
import org.adempiere.pos.search.QueryTicket;
import org.adempiere.pos.service.I_POSPanel;
import org.adempiere.pos.service.I_POSQuery;
import org.adempiere.pos.service.POSQueryListener;
import org.compiere.apps.ADialog;
import org.compiere.model.I_M_Product;
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
 *         *Copyright � ConSerTi
 *  @author Comunidad de Desarrollo OpenXpertya
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright � Jorg Janke
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

	private CButton 			f_bHistory;
	/**	Buttons Command		*/
	private CButton 			f_bNew;
	private CButton 			f_bDocType;
	private CButton 			f_bBPartner;
	private CButton 			f_bBack;
	private CButton 			f_bNext;
	private CButton 			f_bCollect;
	private CButton 			f_bCancel;
	private CButton 			f_bLogout;
	/**	Button Panel		*/
	private CPanel 				buttonPanel;
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
	private final String ACTION_DOCTYPE     = "Assignment";
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
		buttonPanel = new CPanel(new GridBagLayout());

		m_TopP = 0;
		m_LeftP = 1;
		m_BottonP = 0;
		m_RightP = 1;
		// NEW
		f_bNew = createButtonAction(ACTION_NEW, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		f_bNew.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
		buttonPanel.add(f_bNew, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
		// DOCTYPE
		f_bDocType = createButtonAction(ACTION_DOCTYPE,  KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));
		f_bDocType.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
		f_bDocType.setToolTipText(Msg.translate(ctx, "C_DocType_ID"));
		buttonPanel.add(f_bDocType, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
		// BPARTNER
		f_bBPartner = createButtonAction (ACTION_BPARTNER, KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.SHIFT_MASK+Event.ALT_MASK));
		f_bBPartner.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
		buttonPanel.add(f_bBPartner, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
		// HISTORY
		f_bHistory = createButtonAction(ACTION_HISTORY, KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0));
		f_bHistory.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
		buttonPanel.add(f_bHistory, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
 		// 	BACK
 		f_bBack = createButtonAction(ACTION_BACK, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, Event.ALT_MASK));
 		f_bBack.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
 		buttonPanel.add(f_bBack, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
 		f_bBack.setEnabled(true);
		
 		//	NEXT
 		f_bNext = createButtonAction(ACTION_NEXT, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,Event.ALT_MASK));
 		f_bNext.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
 		buttonPanel.add(f_bNext, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
		f_bNext.setEnabled(true);
 		
 		// PAYMENT
 		f_bCollect = createButtonAction(ACTION_PAYMENT, null);
 		f_bCollect.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
 		buttonPanel.add(f_bCollect, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
		f_bCollect.setEnabled(false);
 		
 		// CANCEL
		f_bCancel = createButtonAction(ACTION_CANCEL, null);
		f_bCancel.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
		buttonPanel.add(f_bCancel, new GridBagConstraints(7, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
 		
		// Logout
		f_bLogout = createButtonAction (ACTION_LOGOUT, KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.ALT_MASK+Event.SHIFT_MASK));
		f_bLogout.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
		buttonPanel.add(f_bLogout, new GridBagConstraints(8, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(m_TopP, m_LeftP, m_BottonP, m_RightP), 0, 0));
		// BP
		String labelName = Msg.translate(Env.getCtx(), I_M_Product.COLUMNNAME_M_Product_ID); 
		f_ProductName = new POSTextField(labelName, posPanel.getKeyboard());
		f_ProductName.setPlaceholder(labelName);
		f_ProductName.addActionListener(this);
		f_ProductName.setFont(posPanel.getFont());
		f_ProductName.setPreferredSize(new Dimension(250, posPanel.getFieldLenght()));
		f_ProductName.setMinimumSize(new Dimension(250, posPanel.getFieldLenght()));
		f_ProductName.setFocusable(true);

		//	Add Button Panel
		add(buttonPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1
				,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));
		add(f_ProductName     , new GridBagConstraints(0, 1, 1, 1, 1, 1
				,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));

		posPanel.listOrder();
		getMainFocus();
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
					posPanel.newOrder();
				} if (e.getSource().equals(f_bDocType)) {
					QueryDocType qt = new QueryDocType(posPanel);
					qt.addOptionListener(this);
					qt.loadData();
					qt.showView();
				} else if (e.getSource().equals(f_bBPartner)) {
					QueryBPartner qt = new QueryBPartner(posPanel);
					qt.addOptionListener(this);
					if(posPanel.isBPartnerStandard())
						qt.setResults(null);
					else
						qt.loadData();
					qt.showView();
				} else if (e.getSource().equals(f_bHistory)) {
					// For already created, but either not completed or not yet paid POS Orders
					POSQuery qt = new QueryTicket(posPanel);
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
					posPanel.dispose();
					return;
				} else if (e.getSource().equals(f_ProductName)) {
					findProduct();
					getMainFocus();
					return;
				}
				getMainFocus();
				//	Refresh
				posPanel.refreshPanel();
		} catch (AdempiereException exception) {
			ADialog.error(posPanel.getWindowNo(), this, exception.getLocalizedMessage());
		}
	}	//	actionPerformed

	private void getMainFocus() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				f_ProductName.requestFocusInWindow();
			}
		});
	}

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
		MWarehousePrice[] results = MWarehousePrice.find (ctx,
				posPanel.getM_PriceList_Version_ID(), posPanel.getM_Warehouse_ID(),
				Value, Name, UPC, SKU, null);
		
		//	Set Result
		if (results.length == 1) {	//	one
			posPanel.addLine(results[0].getM_Product_ID(), Env.ONE);
			f_ProductName.setText(results[0].getName());
		} else {	//	more than one
			posPanel.getFrame().getContentPane().invalidate();
			QueryProduct qt = new QueryProduct(posPanel);
			qt.addOptionListener(this);
			qt.setResults(results);
			qt.setQueryData(posPanel.getM_PriceList_Version_ID(), posPanel.getM_Warehouse_ID());
			qt.showView();
			f_ProductName.setValue(null);
			f_ProductName.setText("");
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
		posPanel.previousRecord();
		//quantityPanel.changeViewPanel();
		//	Refresh
		posPanel.refreshPanel();
	}

	/**
	 * Next Record Order
	 */
	public void nextRecord() {
		posPanel.nextRecord();
		posPanel.refreshPanel();
	}
	
	/**
	 * Execute order payment
	 * If order is not processed, process it first.
	 * If it is successful, proceed to pay and print ticket
	 */
	private void payOrder() {
		//Check if order is completed, if so, print and open drawer, create an empty order and set cashGiven to zero
		if(!posPanel.hasOrder()) {
			ADialog.warn(posPanel.getWindowNo(), this,  Msg.getMsg(ctx, "POS.MustCreateOrder"));
		} else {
			VCollect collect = new VCollect(posPanel);
			if (collect.showCollect()) {
				//	Print Ticket just when is completed and it is not a Standard Order nor a Warehouse Order
				if(!posPanel.isStandardOrder() && !posPanel.isWarehouseOrder() && posPanel.isToPrint()) {
					printTicket();
				}
				//	
				posPanel.setOrder(0);
				posPanel.refreshPanel();
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
		String askMsg = "POS.DeleteOrder";
		if (posPanel.isCompleted()) {
			askMsg = "POS.OrderIsAlreadyCompleted";
		}
		//	Show Ask
		if (ADialog.ask(0, this, Msg.getMsg(ctx, Msg.getMsg(ctx, askMsg)))) {
			posPanel.getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			//	Cancel Order
			errorMsg = posPanel.cancelOrder();
			//	Set Cursor to default
			posPanel.getFrame().setCursor(Cursor.getDefaultCursor());
		}
		//	show if exists error
		if(errorMsg != null) {
			ADialog.error(posPanel.getWindowNo(),
					this, Msg.parseTranslation(ctx, errorMsg));
		}
		//	Update
		posPanel.refreshPanel();
	} // deleteOrder
	
	/**
	 * 	Print Ticket
	 * 
	 */
	public void printTicket() {
		if (!posPanel.hasOrder())
			return;
		//	
		
		//int windowNo = p_posPanel.getWindowNo();
		//Properties ctx = p_posPanel.getPropiedades();
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
						if (posPanel.getAD_Sequence_ID()!= 0) {
							
							String docno = posPanel.getSequenceDoc(trxName);
							String q = "Confirmar el número consecutivo "  + docno;
							if (ADialog.ask(posPanel.getWindowNo(), posPanel.getFrame(), q)) {
								posPanel.setPOReference(docno);
								posPanel.saveNextSeq(trxName);
							}
						}
					}
				});
				ReportCtl.startDocumentPrint(0, posPanel.getC_Order_ID(), false);
			}
			catch (Exception e) {
				log.severe("PrintTicket - Error Printing Ticket");
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
		if(posPanel.hasOrder()) {
			//	For Next
			f_bNext.setEnabled(!posPanel.isLastRecord() && posPanel.hasRecord());
			//	For Back
			f_bBack.setEnabled(!posPanel.isFirstRecord() && posPanel.hasRecord());
			//	For Collect
			if(posPanel.hasLines()
					&& !posPanel.isPaid()
					&& !posPanel.isVoided()) {
				//	For Credit Order
				f_bCollect.setEnabled(true);
			} else {
				f_bCollect.setEnabled(false);
			}
			//	For Cancel Action
			f_bCancel.setEnabled(!posPanel.isVoided());
		} else {
			f_bNew.setEnabled(true);
			f_bHistory.setEnabled(true);
			//	For Next
			f_bNext.setEnabled(!posPanel.isLastRecord() && posPanel.hasRecord());
			//	For Back
			f_bBack.setEnabled(!posPanel.isFirstRecord() && posPanel.hasRecord());
			f_bCollect.setEnabled(false);
			//	For Cancel Action
			f_bCancel.setEnabled(false);
		}
		posPanel.changeViewQuantityPanel();
	}

	@Override
	public void okAction(I_POSQuery query) {
		if (query.getRecord_ID() <= 0)
			return;
		//	For Ticket
		if(query instanceof QueryTicket) {
			posPanel.setOrder(query.getRecord_ID());
			posPanel.reloadIndex(query.getRecord_ID());
		} else if(query instanceof QueryBPartner) {
			if(!posPanel.hasOrder()) {
				posPanel.newOrder(query.getRecord_ID());
			} else {
				posPanel.setC_BPartner_ID(query.getRecord_ID());
			}
			//	
			log.fine("C_BPartner_ID=" + query.getRecord_ID());
		} else if(query instanceof QueryProduct) {
			if (query.getRecord_ID() > 0) {
				posPanel.addLine(query.getRecord_ID(), Env.ONE);
			}
		} else if(query instanceof QueryDocType) {
			if (query.getRecord_ID() > 0) {
				posPanel.setC_DocType_ID(query.getRecord_ID());
			}
		}
		//	Refresh
		posPanel.refreshPanel();
	}

	@Override
	public void cancelAction(I_POSQuery query) {
		//	Nothing
	}

	@Override
	public void moveUp() {
	}

	@Override
	public void moveDown() {
	}	
}//	POSActionPanel