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

	private CButton 			buttonHistory;
	/**	Buttons Command		*/
	private CButton 			buttonNew;
	private CButton 			buttonDocType;
	private CButton 			buttonBPartner;
	private CButton 			buttonBack;
	private CButton 			buttonNext;
	private CButton 			buttonCollect;
	private CButton 			buttonCancel;
	private CButton 			buttonLogout;
	/**	Button Panel		*/
	private CPanel 				buttonPanel;
	/**	For Show BPartner	*/
	private	POSTextField 		fieldProductName;
	/**	Padding				*/
	private int 				topPadding;
	private int 				bottonPadding;
	private int 				rightPadding;
	private int 				leftPadding;
	/**	Logger			*/
	private static CLogger logger = CLogger.getCLogger(POSActionPanel.class);

	private final String ACTION_NEW         = "New";
	private final String ACTION_DOCTYPE     = "Assignment";
	private final String ACTION_BPARTNER    = "BPartner";
	private final String ACTION_HISTORY     = "History";
	private final String ACTION_BACK       	= "Parent";
	private final String ACTION_NEXT  		= "Detail";
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

		topPadding = 0;
		leftPadding = 1;
		bottonPadding = 0;
		rightPadding = 1;
		// NEW
		buttonNew = createButtonAction(ACTION_NEW, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		buttonNew.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
		buttonPanel.add(buttonNew, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(topPadding, leftPadding, bottonPadding, rightPadding), 0, 0));
		// DOCTYPE
		buttonDocType = createButtonAction(ACTION_DOCTYPE,  KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));
		buttonDocType.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
		buttonDocType.setToolTipText(Msg.translate(ctx, "C_DocType_ID"));
		buttonPanel.add(buttonDocType, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(topPadding, leftPadding, bottonPadding, rightPadding), 0, 0));
		// BPARTNER
		buttonBPartner = createButtonAction (ACTION_BPARTNER, KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.SHIFT_MASK+Event.ALT_MASK));
		buttonBPartner.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
		buttonPanel.add(buttonBPartner, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(topPadding, leftPadding, bottonPadding, rightPadding), 0, 0));
		// HISTORY
		buttonHistory = createButtonAction(ACTION_HISTORY, KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0));
		buttonHistory.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
		buttonPanel.add(buttonHistory, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(topPadding, leftPadding, bottonPadding, rightPadding), 0, 0));
 		// 	BACK
 		buttonBack = createButtonAction(ACTION_BACK, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, Event.ALT_MASK));
 		buttonBack.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
 		buttonPanel.add(buttonBack, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(topPadding, leftPadding, bottonPadding, rightPadding), 0, 0));
 		buttonBack.setEnabled(true);
		
 		//	NEXT
 		buttonNext = createButtonAction(ACTION_NEXT, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,Event.ALT_MASK));
 		buttonNext.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
 		buttonPanel.add(buttonNext, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(topPadding, leftPadding, bottonPadding, rightPadding), 0, 0));
		buttonNext.setEnabled(true);
 		
 		// PAYMENT
 		buttonCollect = createButtonAction(ACTION_PAYMENT, KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
 		buttonCollect.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
 		buttonPanel.add(buttonCollect, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(topPadding, leftPadding, bottonPadding, rightPadding), 0, 0));
		buttonCollect.setEnabled(false);
 		
 		// CANCEL
		buttonCancel = createButtonAction(ACTION_CANCEL, KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		buttonCancel.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
		buttonPanel.add(buttonCancel, new GridBagConstraints(7, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(topPadding, leftPadding, bottonPadding, rightPadding), 0, 0));
 		
		// Logout
		buttonLogout = createButtonAction (ACTION_LOGOUT, KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.ALT_MASK+Event.SHIFT_MASK));
		buttonLogout.setPreferredSize(new Dimension(posPanel.getButtonSize(), posPanel.getButtonSize()));
		buttonPanel.add(buttonLogout, new GridBagConstraints(8, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(topPadding, leftPadding, bottonPadding, rightPadding), 0, 0));
		// BP
		String labelName = Msg.translate(Env.getCtx(), I_M_Product.COLUMNNAME_M_Product_ID); 
		fieldProductName = new POSTextField(labelName, posPanel.getKeyboard());
		fieldProductName.setPlaceholder(labelName);
		fieldProductName.addActionListener(this);
		fieldProductName.setFont(posPanel.getFont());
		fieldProductName.setPreferredSize(new Dimension(250, posPanel.getFieldLenght()));
		fieldProductName.setMinimumSize(new Dimension(250, posPanel.getFieldLenght()));
		fieldProductName.setFocusable(true);

		//	Add Button Panel
		add(buttonPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1
				,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));
		add(fieldProductName, new GridBagConstraints(0, 1, 1, 1, 1, 1
				,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(10, 0, 0, 0), 0, 0));

		posPanel.listOrder();
		getMainFocus();
	}	//	init

	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose()
	{
		fieldProductName = null;
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
		logger.info( "PosSubCustomer - actionPerformed: " + action);
		try {
				//	New
				if (e.getSource().equals(buttonNew)) {
					posPanel.newOrder();
				} if (e.getSource().equals(buttonDocType)) {
					QueryDocType queryDocType = new QueryDocType(posPanel);
					queryDocType.addOptionListener(this);
					queryDocType.loadData();
					queryDocType.showView();
				} else if (e.getSource().equals(buttonBPartner)) {
					QueryBPartner queryBPartner = new QueryBPartner(posPanel);
					queryBPartner.addOptionListener(this);
					if(posPanel.isBPartnerStandard())
						queryBPartner.setResults(null);
					else
						queryBPartner.loadData();
					queryBPartner.showView();
				} else if (e.getSource().equals(buttonHistory)) {
					// For already created, but either not completed or not yet paid POS Orders
					POSQuery queryTicket = new QueryTicket(posPanel);
					queryTicket.addOptionListener(this);
					queryTicket.showView();
					return;
				} else if (e.getSource().equals(buttonBack)){
					previousRecord();
				} else if (e.getSource().equals(buttonNext)){
					nextRecord();
				} else if (e.getSource().equals(buttonCollect)) {
					payOrder();
				} else if (e.getSource().equals(buttonCancel)) {
					deleteOrder();
				} else if (e.getSource().equals(buttonLogout)) {	//	Logout
					posPanel.dispose();
					return;
				} else if (e.getSource().equals(fieldProductName)) {
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
				fieldProductName.requestFocusInWindow();
			}
		});
	}

	/**************************************************************************
	 * 	Find/Set Product & Price
	 */
	private void findProduct() {
		String query = fieldProductName.getText();
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
		String value = query;
		String name = query;
		String upc = (allNumber ? query : null);
		String sku = (allNumber ? query : null);
		//	
		MWarehousePrice[] results = MWarehousePrice.find (ctx,
				posPanel.getM_PriceList_Version_ID(), posPanel.getM_Warehouse_ID(),
				value, name, upc, sku, null);
		
		//	Set Result
		if (results.length == 1) {	//	one
			posPanel.addLine(results[0].getM_Product_ID(), Env.ONE);
			fieldProductName.setText(results[0].getName());
		} else {	//	more than one
			posPanel.getFrame().getContentPane().invalidate();
			QueryProduct qt = new QueryProduct(posPanel);
			qt.addOptionListener(this);
			qt.setResults(results);
			qt.setQueryData(posPanel.getM_PriceList_Version_ID(), posPanel.getM_Warehouse_ID());
			qt.showView();
			fieldProductName.setValue(null);
			fieldProductName.setText("");
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
				logger.severe("PrintTicket - Error Printing Ticket");
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
			buttonNext.setEnabled(!posPanel.isLastRecord() && posPanel.hasRecord());
			//	For Back
			buttonBack.setEnabled(!posPanel.isFirstRecord() && posPanel.hasRecord());
			//	For Collect
			if(posPanel.hasLines()
					&& !posPanel.isPaid()
					&& !posPanel.isVoided()) {
				//	For Credit Order
				buttonCollect.setEnabled(true);
			} else {
				buttonCollect.setEnabled(false);
			}
			// For BusinessPartner and Document Type
			if(posPanel.isDrafted() || posPanel.isInProgress()) {
				buttonDocType.setEnabled(true);
				buttonBPartner.setEnabled(true);
			} else {
				buttonDocType.setEnabled(false);
				buttonBPartner.setEnabled(false);
			} 
			//	For Cancel Action
			buttonCancel.setEnabled(!posPanel.isVoided());
		} else {
			buttonNew.setEnabled(true);
			buttonHistory.setEnabled(true);
			//	For Next
			buttonNext.setEnabled(!posPanel.isLastRecord() && posPanel.hasRecord());
			//	For Back
			buttonBack.setEnabled(!posPanel.isFirstRecord() && posPanel.hasRecord());
			buttonCollect.setEnabled(false);
			//	For Cancel Action
			buttonCancel.setEnabled(false);
			// For BusinessPartner and Document Type
			buttonDocType.setEnabled(false);
			buttonBPartner.setEnabled(false);
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
			logger.fine("C_BPartner_ID=" + query.getRecord_ID());
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