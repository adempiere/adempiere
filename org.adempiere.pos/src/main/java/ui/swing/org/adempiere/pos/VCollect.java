/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2013 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor: Yamel Senih www.erpcya.com                                    *
 * Contributor: Mario Calderon www.westfalia-it.com                           *
 *****************************************************************************/
package org.adempiere.pos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import org.adempiere.pipo.exception.POSaveFailedException;
import org.adempiere.pos.service.Collect;
import org.adempiere.pos.service.I_POSPanel;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.AppsAction;
import org.compiere.apps.ConfirmPanel;
import org.compiere.model.X_C_Payment;
import org.compiere.swing.CButton;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CDialog;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class VCollect extends Collect
		implements ActionListener, I_POSPanel, VetoableChangeListener {
	
	/**
	 * From POS
	 * *** Constructor ***
	 * @param posPanel
	 */
	public VCollect(VPOS posPanel) {
		super(posPanel.getCtx(), posPanel.getM_Order(), posPanel.getM_POS());
		v_POSPanel = posPanel;
		m_ctx = v_POSPanel.getCtx();
		collectRowNo = 0;
		init();
	}

	/**
	 * Init Dialog
	 * @return void
	 */
	public void init() {
		log.info("");
		try {
			jbInit();
			refreshPanel();
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}
	} // init

	/**	View Panel			*/
	private VPOS 			v_POSPanel;
	private CDialog 		v_Dialog;
	private BorderLayout 	mainLayout;
	private GridBagLayout 	parameterLayout;
	private FlowLayout 		commandLayout;
	private CPanel 			v_MainPanel;
	private CPanel 			v_ParameterPanel;
	private JScrollPane 	v_ScrollPanel;
	private CPanel 			v_CenterPanel;
	private CPanel 			v_CommandPanel;
	
	/**	Fields Summary		*/
	private CLabel 			lGrandTotal;
	private CLabel 			fGrandTotal;
	private CLabel 			lPayAmt;
	private CLabel 			fPayAmt;
	private CLabel 			lOpenAmt;
	private CLabel 			fOpenAmt;
	private CLabel 			lReturnAmt;
	private CLabel 			fReturnAmt;
	private CCheckBox 		fIsPrePayOrder;
	private CCheckBox 		fIsCreditOrder;
//	private VLookup 		fPaymentTerm;
	
	/**	Action				*/
	private CButton 		bPlus;
	private JButton 		bCancel;
	private JButton 		bOk;
	
	/**	Generic Values		*/
	private boolean 		isProcessed;
	private Properties 		m_ctx;
	private int 			collectRowNo;
	
	/**	Log					*/
	private CLogger 		log = CLogger.getCLogger(VCollect.class);
	/**	Default Width		*/
	private final int		SUMMARY_FIELD_WIDTH 	= 200;
	/**	Default Height		*/
	private final int		SUMMARY_FIELD_HEIGHT 	= 30;

	/**
	 * Instance Frame and fill fields
	 * @throws Exception
	 * @return void
	 */
	private void jbInit() throws Exception {
		//	Instance Dialog
		v_Dialog = new CDialog(Env.getWindow(v_POSPanel.getWindowNo()), Msg.translate(m_ctx, "Payment"), true);
		//
		mainLayout = new BorderLayout();
		parameterLayout = new GridBagLayout();
		commandLayout = new FlowLayout();
		v_MainPanel = new CPanel();
		v_ParameterPanel = new CPanel();
		v_ScrollPanel = new JScrollPane();
		v_CenterPanel = new CPanel();
		v_CommandPanel = new CPanel();
		//	
		v_MainPanel.setLayout(mainLayout);
		v_ParameterPanel.setLayout(parameterLayout);
		v_CenterPanel.setLayout(parameterLayout);
		v_MainPanel.add(v_ScrollPanel);
		v_ScrollPanel.getViewport().add(v_CenterPanel);
		v_CommandPanel.setLayout(commandLayout);
		
		// Add Grand Total
		lGrandTotal = new CLabel(Msg.translate(m_ctx, "GrandTotal") + ":");
		lGrandTotal.setFont(v_POSPanel.getPlainFont());
		//	
		fGrandTotal = new CLabel();
		fGrandTotal.setFont(v_POSPanel.getBigFont());
		//	
		fGrandTotal.setPreferredSize(new Dimension(SUMMARY_FIELD_WIDTH, SUMMARY_FIELD_HEIGHT));
		
		//	Add Payment Amount
		lPayAmt = new CLabel(Msg.translate(m_ctx, "PayAmt") + ":");
		lPayAmt.setFont(v_POSPanel.getPlainFont());
		//	
		fPayAmt = new CLabel();
		fPayAmt.setFont(v_POSPanel.getFont());
		fPayAmt.setPreferredSize(new Dimension(SUMMARY_FIELD_WIDTH, SUMMARY_FIELD_HEIGHT));
		
		//	Add Payment Amount
		lOpenAmt = new CLabel(Msg.translate(m_ctx, "OpenAmt") + ":");
		lOpenAmt.setFont(v_POSPanel.getPlainFont());
		//	
		fOpenAmt = new CLabel();
		fOpenAmt.setFont(v_POSPanel.getFont());
		fOpenAmt.setPreferredSize(new Dimension(SUMMARY_FIELD_WIDTH, SUMMARY_FIELD_HEIGHT));
		
		//	For Returned Amount
		lReturnAmt = new CLabel(Msg.translate(m_ctx, "AmountReturned") + ":");
		lReturnAmt.setFont(v_POSPanel.getPlainFont());
		//	
		fReturnAmt = new CLabel();
		fReturnAmt.setFont(v_POSPanel.getFont());
		fReturnAmt.setPreferredSize(new Dimension(SUMMARY_FIELD_WIDTH, SUMMARY_FIELD_HEIGHT));
		
		//	Add Is Pre-Payment
		fIsPrePayOrder = new CCheckBox(Msg.translate(m_ctx, "IsPrePayment"));
		fIsPrePayOrder.setFont(v_POSPanel.getPlainFont());
		
		//	Add Is Credit Order
		fIsCreditOrder = new CCheckBox(Msg.translate(m_ctx, "IsCreditSale"));
		fIsCreditOrder.setFont(v_POSPanel.getPlainFont());
		
		// Completed Standard Order: only prepayment possible 
		if(v_POSPanel.getTotalLines().compareTo(Env.ZERO)==1 && 
		   v_POSPanel.isCompleted() &&
		   v_POSPanel.isStandardOrder()) {	
			fIsPrePayOrder.setEnabled(false);	
			fIsCreditOrder.setEnabled(false);
			fIsPrePayOrder.setSelected(true);
		}
		// Not completed Order 
		else if(v_POSPanel.getTotalLines().compareTo(Env.ZERO)==1 && 
				!v_POSPanel.isCompleted()) {	
			if(v_POSPanel.isStandardOrder() || v_POSPanel.isWarehouseOrder()) {
				 // Standard Order or Warehouse Order: no Credit Order, no prepayment
				fIsPrePayOrder.setEnabled(false);	
				fIsPrePayOrder.setSelected(false);	
				fIsCreditOrder.setEnabled(false);
				fIsCreditOrder.setSelected(false);
			}
			else {		
				fIsPrePayOrder.setEnabled(true);	
				fIsCreditOrder.setEnabled(true);
			}
		}
		else {
			fIsPrePayOrder.setEnabled(false);	
			fIsCreditOrder.setEnabled(false);
			if(v_POSPanel.isCompleted() && 
				v_POSPanel.getM_Order().isInvoiced()  && 
				v_POSPanel.getOpenAmt().compareTo(Env.ZERO)==1) {
				fIsCreditOrder.setSelected(true);
			}
		}
//		int AD_Column_ID = 2187;        //  C_Order.C_PaymentTerm_ID
//		MLookup lookup = MLookupFactory.get(Env.getCtx(), 0, 0, AD_Column_ID, DisplayType.TableDir);
//		fPaymentTerm = new VLookup("C_PaymentTerm_ID", true, false, true, lookup);
//		((VComboBox)fPaymentTerm.getCombo()).setRenderer(new POSLookupTableDirCellRenderer(posPanel.getFont()));
//		fPaymentTerm.setPreferredSize(new Dimension(200, posPanel.getFieldLenght()));
//		((VComboBox)fPaymentTerm.getCombo()).setFont(posPanel.getFont());
//		fPaymentTerm.addVetoableChangeListener(this);
		//	Add Plus Button
		AppsAction act = new AppsAction("Plus", KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.F2), false);
		act.setDelegate(this);
		bPlus = (CButton)act.getButton();
		bPlus.setPreferredSize(new Dimension(v_POSPanel.getButtonSize(), v_POSPanel.getButtonSize()));
		bPlus.setFocusable(false);
		//	For Confirm Panel Button
		bCancel = ConfirmPanel.createCancelButton(true);
		bOk = ConfirmPanel.createOKButton(true);
		//	
		//	
		v_ParameterPanel.add(lGrandTotal, new GridBagConstraints(1, 0, 1, 1, 0.0,0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		v_ParameterPanel.add(fGrandTotal, new GridBagConstraints(2, 0, 1, 1, 0.0,0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(0, 0, 0, 0), 0, 0));
		
		v_ParameterPanel.add(lPayAmt, new GridBagConstraints(1, 1, 1, 1, 0.0,	0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(0, 0, 0, 0), 0, 0));
		
		v_ParameterPanel.add(fPayAmt, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(0, 0, 0, 0), 0, 0));
		
		v_ParameterPanel.add(lOpenAmt, new GridBagConstraints(1, 2, 1, 1, 0.0,	0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(0, 0, 0, 0), 0, 0));
		
		v_ParameterPanel.add(fOpenAmt, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(0, 0, 0, 0), 0, 0));
		
		v_ParameterPanel.add(lReturnAmt, new GridBagConstraints(1, 3, 1, 1, 0.0,0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(0, 0, 0, 0), 0, 0));
		
		v_ParameterPanel.add(fReturnAmt, new GridBagConstraints(2, 3, 1, 1, 0.0,0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(0, 0, 0, 0), 0, 0));

		v_ParameterPanel.add(fIsPrePayOrder, new GridBagConstraints(1, 4, 1, 1, 0.0,0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		v_ParameterPanel.add(fIsCreditOrder, new GridBagConstraints(2, 4, 1, 1, 0.0,0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		v_ParameterPanel.add(bPlus, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 0), 0, 0));
		
//		parameterPanel.add(fPaymentTerm, new GridBagConstraints(2, 5, 1, 1, 0.0,0.0,
//				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		//	Add Fields to Main Panel
		v_MainPanel.add(v_ParameterPanel, BorderLayout.NORTH);
		// comandPanel
		commandLayout.setAlignment(FlowLayout.RIGHT);
		commandLayout.setHgap(10);
		v_CommandPanel.add(bCancel, null);
		v_CommandPanel.add(bOk, null);
		
		
		//	Add Listeners
		fIsPrePayOrder.addActionListener(this);
		fIsCreditOrder.addActionListener(this);
		bOk.addActionListener(this);
		bCancel.addActionListener(this);
		
		//	Add to Dialog
		v_Dialog.getContentPane().add(v_CommandPanel, BorderLayout.SOUTH);
		v_Dialog.getContentPane().add(v_MainPanel, BorderLayout.CENTER);
	}

	/**
	 * Add new Collect
	 * @return void
	 */
	private void addCollectType() {
		//	
		String tenderType = X_C_Payment.TENDERTYPE_Cash;
		if(collectRowNo > 0) {
			tenderType = X_C_Payment.TENDERTYPE_CreditCard;
		}
		//	FR https://github.com/erpcya/AD-POS-WebUI/issues/7
		BigDecimal m_Balance = getBalance();
		if(m_Balance.doubleValue() < 0)
			m_Balance = Env.ZERO;
		//	
		VCollectDetail collectDetail = new VCollectDetail(this, tenderType, getBalance());
		//	Add Collect controller
		addCollect(collectDetail);
		// add parameter panel
		v_CenterPanel.add(collectDetail.getPanel(), new GridBagConstraints(0, collectRowNo, 1, 1, 0.0, 0.0,
						GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 5, 5, 5), 0, 0));
		//	Repaint
		v_ScrollPanel.validate();
		v_ScrollPanel.repaint();
		//	Request Focus
		collectDetail.requestFocusInPayAmt();
		//	Add Count
		collectRowNo++;
		//	Calculate Data
		calculatePanelData();
	}

	/**
	 * Process Window
	 * @return
	 * @return String
	 */
	public String saveData() {
		String errorMsg = null;
		try {
			v_Dialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					if(v_POSPanel.processOrder(trxName, isPrePayOrder(), getBalance().doubleValue() <= 0)) {
						processPayment(trxName, v_POSPanel.getOpenAmt());
					} else {
						throw new POSaveFailedException(v_POSPanel.getProcessMsg());
					}
				}
			});
		} catch (Exception e) {
			errorMsg = e.getLocalizedMessage();
		} finally {
			v_Dialog.setCursor(Cursor.getDefaultCursor());
		}
		//	Default
		return errorMsg;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		isProcessed = false;
		//	Validate Event
		if (e.getSource().equals(bPlus)) {
			addCollectType();
		} else if (e.getSource().equals(bOk)) {	//	Process if is ok validation
			//	Validate before process
			String validResult = validatePanel();
			if(validResult == null) {
				validResult = saveData();
			}
			//	Show Dialog
			if(validResult != null) {
				ADialog.warn(v_POSPanel.getWindowNo(), v_Dialog, Msg.parseTranslation(m_ctx, validResult));
				return;
			}
			//	Set Processed
			isProcessed = true;
			//	
			v_Dialog.dispose();
			return;
		} else if (e.getSource().equals(bCancel)) {	//	Nothing
			v_Dialog.dispose();
			return;
		} else if(e.getSource().equals(fIsCreditOrder)) {	//	For Credit Order Checked
			//	Set to Controller
			setIsCreditOrder(fIsCreditOrder.isSelected());
			if(fIsCreditOrder.isSelected()) {
				removeAllCollectDetail();
			}
		} else if(e.getSource().equals(fIsPrePayOrder)) {	//	For Pre-Payment Order Checked
			//	Set to Controller
			setIsPrePayOrder(fIsPrePayOrder.isSelected());
		}
		//	Valid Panel
		changeViewPanel();
	}

	/**
	 * Remove All Collect Detail
	 * @return void
	 */
	public void removeAllCollectDetail() {
		v_CenterPanel.removeAll();
		super.removeAllCollectDetail();
		//	Refresh View
		v_ScrollPanel.validate();
		v_ScrollPanel.repaint();
	}
	
	/**
	 * Remove Collect Detal From Child
	 * @param child
	 * @return void
	 */
	public void removeCollectDetail(VCollectDetail child) {
		Component comp = child.getPanel();
		removeCollect(child);
		v_CenterPanel.remove(comp);
		v_ScrollPanel.validate();
		v_ScrollPanel.repaint();
	}
	
	/**
	 * Show Collect
	 * @return boolean
	 */
	public boolean showCollect() {
		//	Resize to Heigth
		Dimension screenSize = Env.getWindow(v_POSPanel.getWindowNo()).getSize();
		//	Set static width
		screenSize.width = 500;
		v_Dialog.setMinimumSize(screenSize);
		v_Dialog.pack();
		//	
		AEnv.positionCenterScreen(v_Dialog);
		v_Dialog.setVisible(true);
		return isProcessed;
	}
	
	/**
	 * Get Keyboard
	 * @return POSKeyboard
	 */
	public POSKeyboard getKeyboard() {
		return v_POSPanel.getKeyboard();
	}

	@Override
	public void refreshPanel() {
		calculatePanelData();
		changeViewPanel();
	}

	@Override
	public String validatePanel() {
		String errorMsg = null;
		if(!v_POSPanel.hasOrder()) {	//	When is not created order
			errorMsg = "@POS.MustCreateOrder@";
		} else {
			if(!(v_POSPanel.isStandardOrder() /*|| v_POSPanel.isWarehouseOrder()*/))
				// No Check if Order is not Standard Order
				// TODO: Review why nor Warehouse Order
				errorMsg = validatePayment(v_POSPanel.getOpenAmt());
		}
		//	
		return errorMsg;
	}

	@Override
	public void changeViewPanel() {
		//	Set Credit for Complete Documents
		boolean isCreditOpen = (v_POSPanel.isCompleted() 
				&& v_POSPanel.getOpenAmt().doubleValue() > 0);
		//	Is Standard Order
		boolean isStandardOrder = v_POSPanel.isStandardOrder();
		//	Set Credit Order
		setIsCreditOrder(isCreditOrder() 
				|| (isCreditOpen && !isStandardOrder));
		//	
		setIsPrePayOrder(isPrePayOrder()
				|| (isCreditOpen && isStandardOrder));
		//	Set Credit and Pre-Pay Order
		fIsCreditOrder.setSelected(isCreditOrder());
		fIsPrePayOrder.setSelected(isPrePayOrder());
//		fPaymentTerm.setVisible(isCreditOrder());
		//	Verify complete order
		if(v_POSPanel.isCompleted()) {
			fIsCreditOrder.setEnabled(false);
			fIsPrePayOrder.setEnabled(false);
//			fPaymentTerm.setEnabled(false);
			bPlus.setEnabled(isCreditOpen);
			bOk.setEnabled(true);
		} else if(v_POSPanel.isVoided()){
			fIsCreditOrder.setEnabled(false);
			fIsPrePayOrder.setEnabled(false);
//			fPaymentTerm.setEnabled(false);
			bPlus.setEnabled(false);
			bOk.setEnabled(false);
		} else if(v_POSPanel.isStandardOrder() || v_POSPanel.isWarehouseOrder()) { 
			// Standard Order or Warehouse Order: no Credit Order, no prepayment
			fIsPrePayOrder.setEnabled(false);	
			fIsCreditOrder.setEnabled(false);
			bPlus.setEnabled(false);
		}
		else {
			fIsCreditOrder.setEnabled(true);
			fIsPrePayOrder.setEnabled(true);
//			fPaymentTerm.setEnabled(true);
			bPlus.setEnabled(!isCreditOrder()
					|| isCreditOpen);
			bOk.setEnabled(true);
		}
	}
	
	/**
	 * Get Balance
	 * @return
	 * @return BigDecimal
	 */
	private BigDecimal getBalance() {
		BigDecimal m_PayAmt = getPayAmt();
		return v_POSPanel.getOpenAmt().subtract(m_PayAmt);
	}
	
	/**
	 * Calculate and change data in panel
	 * @return void
	 */
	private void calculatePanelData() {
		//	Get from controller
		BigDecimal m_PayAmt = getPayAmt();
		BigDecimal m_Balance = getBalance();
		//	Change View
		String currencyISO_Code = v_POSPanel.getCurSymbol();
		fGrandTotal.setText(currencyISO_Code + " " 
				+ v_POSPanel.getNumberFormat().format(v_POSPanel.getGrandTotal()));
		fPayAmt.setText(currencyISO_Code + " " 
				+ v_POSPanel.getNumberFormat().format(m_PayAmt.add(v_POSPanel.getPaidAmt())));
		//	BR https://github.com/erpcya/AD-POS-WebUI/issues/6
		//	Show pretty Return Amount
		BigDecimal m_ReturnAmt = Env.ZERO;
		BigDecimal m_OpenAmt = Env.ZERO;
		if(m_Balance.doubleValue() < 0) {
			m_ReturnAmt = m_Balance.abs();
		} else if(m_Balance.doubleValue() > 0){
			m_OpenAmt = m_Balance;
		}
		//	Set Open Amount
		fOpenAmt.setText(currencyISO_Code + " " 
				+ v_POSPanel.getNumberFormat().format(m_OpenAmt));
		//	Set Return Amount
		fReturnAmt.setText(currencyISO_Code + " " 
				+ v_POSPanel.getNumberFormat().format(m_ReturnAmt));
		
//		fPaymentTerm.setValue(getC_PaymentTerm_ID());
	}

	@Override
	public void vetoableChange(PropertyChangeEvent e)
			throws PropertyVetoException {
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		log.config(name + " = " + value);
		//	Verify Event
//		if(name.equals("C_PaymentTerm_ID")) {
//			int m_C_PaymentTerm_ID = ((Integer)(value != null? value: 0)).intValue();
//			setC_PaymentTerm_ID(m_C_PaymentTerm_ID);
//		}
	}

	@Override
	public void moveUp() {
	}

	@Override
	public void moveDown() {
	}
} // VCollect