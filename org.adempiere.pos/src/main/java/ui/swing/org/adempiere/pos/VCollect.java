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

import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.pipo.exception.POSaveFailedException;
import org.adempiere.pos.service.Collect;
import org.adempiere.pos.service.POSPanelInterface;
import org.compiere.apps.ADialog;
import org.compiere.apps.AppsAction;
import org.compiere.apps.ConfirmPanel;
import org.compiere.model.X_C_Payment;
import org.compiere.swing.CButton;
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
 * @author Victor Perez <victor.perez@e-evolution.com>,  eEvolution http://www.e-evolution.com
 */
public class VCollect extends Collect
		implements ActionListener, POSPanelInterface, VetoableChangeListener {
	
	/**
	 * From POS
	 * *** Constructor ***
	 * @param posPanel
	 */
	public VCollect(VPOS posPanel) {
		super(posPanel.getCtx(), posPanel.getOrder(), posPanel.getM_POS());
		pos = posPanel;
		ctx = pos.getCtx();
		collectRowNo = 0;
		init();
	}

	public VCollect load (VPOS posPanel)
	{
		//	Instance Collects
		load(posPanel.getCtx() , posPanel.getOrder() , posPanel.getM_POS());
		centerPanel.removeAll();
		collectRowNo = 0;
		calculatePanelData();
		refreshPanel();
		addCollectType();
		pos.disablePOSButtons();
		return this;
	}

	/**	View Panel			*/
	private VPOS 			pos;
	private CPanel			dialog;
	private BorderLayout 	mainLayout;
	private GridBagLayout 	parameterLayout;
	private CPanel 			mainPanel;
	private CPanel 			parameterPanel;
	private JScrollPane 	scrollPane;
	private CPanel 			centerPanel;
	
	/**	Fields Summary		*/
	private CLabel 			labelPaidAmt;
	private CLabel 			fieldPaidAmt;
	private CLabel 			labelPayAmt;
	private CLabel 			fieldPayAmt;
	private CLabel 			labelOpenAmt;
	private CLabel 			fieldOpenAmt;
	private CLabel 			labelReturnAmt;
	private CLabel 			fieldReturnAmt;
	
	/**	Action				*/
	private CButton 		buttonPlus;
	private CButton 		buttonCancel;
	private CButton 		buttonOk;
	
	/**	Generic Values		*/
	private boolean 		isProcessed;
	private Properties 		ctx;
	private int 			collectRowNo;
	
	/**	Log					*/
	private CLogger 		log = CLogger.getCLogger(VCollect.class);

	private Dimension screenSize;
	private int widthSize;
	private int heightSize;

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
	/**
	 * Instance Frame and fill fields
	 * @throws Exception
	 * @return void
	 */
	private void jbInit() throws Exception {
		screenSize = pos.getSize();
		widthSize = (screenSize.width * 40) / 100 ;
		heightSize = (screenSize.height * 50) / 100 ;

		//	Instance Dialog
		//dialog = new CDialog(Env.getWindow(pos.getWindowNo()), Msg.translate(ctx, "Payment"), true);
		dialog = new CPanel();
		dialog.setName(Msg.translate(ctx, "Payment"));
		//
		mainLayout = new BorderLayout();
		parameterLayout = new GridBagLayout();
		mainPanel = new CPanel();
		parameterPanel = new CPanel();

		centerPanel = new CPanel();
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(widthSize, heightSize));

		mainPanel.setLayout(mainLayout);
		parameterPanel.setLayout(parameterLayout);
		centerPanel.setLayout(parameterLayout);
		mainPanel.add(scrollPane);
		scrollPane.getViewport().add(centerPanel);

		//	Add Payment Amount
		labelPayAmt = new CLabel(Msg.translate(ctx, "PayAmt") + ":");
		labelPayAmt.setFont(pos.getBigFont());
		//
		fieldPayAmt = new CLabel();
		fieldPayAmt.setFont(pos.getBigFont()	);
		
		//	Add Payment Amount
		labelOpenAmt = new CLabel(Msg.translate(ctx, "OpenAmt") + ":");
		labelOpenAmt.setFont(pos.getBigFont());
		//	
		fieldOpenAmt = new CLabel();
		fieldOpenAmt.setFont(pos.getBigFont());
		
		//	For Returned Amount
		labelReturnAmt = new CLabel(Msg.translate(ctx, "AmountReturned") + ":");
		labelReturnAmt.setFont(pos.getBigFont());
		//	
		fieldReturnAmt = new CLabel();
		fieldReturnAmt.setFont(pos.getBigFont());

		labelPaidAmt = new CLabel(Msg.translate(ctx, "PaidAmt") + ":");
		labelPaidAmt.setFont(pos.getBigFont());
		labelPaidAmt.setVisible(false);
		fieldPaidAmt = new CLabel();
		fieldPaidAmt.setFont(pos.getBigFont());
		fieldPaidAmt.setVisible(false);

		//	Add Plus Button
		AppsAction action = new AppsAction("Plus", KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.F2), false);
		action.setDelegate(this);
		buttonPlus = (CButton)action.getButton();
		buttonPlus.setPreferredSize(new Dimension(pos.getButtonSize(), pos.getButtonSize()));
		buttonPlus.setFocusable(false);
		//	For Confirm Panel Button
		buttonCancel = ConfirmPanel.createCancelButton(true);
		buttonCancel.setPreferredSize(new Dimension(pos.getButtonSize(), pos.getButtonSize()));
		buttonOk = ConfirmPanel.createOKButton(true);
		buttonOk.setPreferredSize(new Dimension(pos.getButtonSize(), pos.getButtonSize()));

		parameterPanel.add(labelPayAmt, new GridBagConstraints(1, 0, 1, 1, 0.0,	0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(2, 2, 2, 0), 0, 0));
		
		parameterPanel.add(fieldPayAmt, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(2, 2, 2, 0), 0, 0));
		
		parameterPanel.add(labelOpenAmt, new GridBagConstraints(1, 1, 1, 1, 0.0,	0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(2, 2, 2, 0), 0, 0));
		
		parameterPanel.add(fieldOpenAmt, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(2, 2, 2, 0), 0, 0));
		
		parameterPanel.add(labelReturnAmt, new GridBagConstraints(1, 2, 1, 1, 0.0,0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(2, 2, 2, 0), 0, 0));
		
		parameterPanel.add(fieldReturnAmt, new GridBagConstraints(2, 2, 1, 1, 0.0,0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(2, 2, 2, 0), 0, 0));

		parameterPanel.add(labelPaidAmt, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 2, 2, 0), 0, 0));

		parameterPanel.add(fieldPaidAmt, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 2, 2, 0), 0, 0));

		parameterPanel.add(buttonPlus, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 2, 2, 0), 0, 0));

		parameterPanel.add(buttonCancel, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 2, 2, 0), 0, 0));

		parameterPanel.add(buttonOk, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 2, 2, 0), 0, 0));

		//	Add Fields to Main Panel
		mainPanel.add(parameterPanel, BorderLayout.NORTH);

		buttonOk.addActionListener(this);
		buttonCancel.addActionListener(this);
		
		//	Add to Dialog
		dialog.add(mainPanel, BorderLayout.CENTER);
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
		BigDecimal balance = getBalance(pos.getOpenAmt());
		if(balance.doubleValue() < 0)
			balance = Env.ZERO;
		//	
		VCollectDetail collectDetail = new VCollectDetail(this, tenderType, balance);
		//	Add Collect controller
		addCollect(collectDetail);
		// add parameter panel
		centerPanel.add(collectDetail.getPanel(), new GridBagConstraints(0, collectRowNo, 1, 1, 0.0, 0.0,
						GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(1, 1, 1, 1), 0, 0));
		//	Repaint
		scrollPane.validate();
		scrollPane.repaint();
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
	public void executePayment(String trxName) {
		if(pos.processOrder(trxName, isAllowsPartialPayment(), getBalance(pos.getOpenAmt()).signum() <= 0)) {
			processTenderTypes(trxName, pos.getOpenAmt());
			String error = getErrorMsg();
			if(error != null && error.length() > 0)
				throw new POSaveFailedException(Msg.parseTranslation(ctx, "@order.no@ " + pos.getDocumentNo() + ": " + getErrorMsg()));
		} else {
			throw new POSaveFailedException(Msg.parseTranslation(ctx, "@order.no@ " + pos.getDocumentNo() + ": "  +
		                 "@ProcessRunError@" + " (" +  pos.getProcessMsg() + ")"));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		isProcessed = false;
		//	Validate Event
		if (actionEvent.getSource().equals(buttonPlus)) {
			addCollectType();
		} else if (actionEvent.getSource().equals(buttonOk)) {	//	Process if is ok validation
			//	Validate before process
			try {
				pos.getFrame().getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Trx.run(new TrxRunnable() {
					public void run(String trxName) {
						String validResult = validatePayment();
						if(validResult == null) {
							executePayment(trxName);
						} else {
							throw new AdempiereException(validResult);
						}
						//	Print Ticket
						if (pos.isToPrint()) {
							pos.printTicket();
						}
					}
				});
			} catch(Exception e) {
				ADialog.warn(pos.getWindowNo(), dialog, Msg.parseTranslation(ctx, e.getLocalizedMessage()));
				pos.reloadOrder();
				return;
			} finally {
				pos.getFrame().getContentPane().setCursor(Cursor.getDefaultCursor());
			}
			//	Process printing
			isProcessed = true;
			hidePanel();
			pos.showKeyboard();
			pos.refreshPanel();
			pos.restoreTable();
			return;
		} else if (actionEvent.getSource().equals(buttonCancel)) {	//	Nothing
			hidePanel();
			pos.showKeyboard();
			pos.refreshPanel();
			pos.restoreTable();
			//if(pos.getM_Order().getDocStatus().equalsIgnoreCase(MOrder.DOCSTATUS_Drafted) ||
			//		pos.getM_Order().getDocStatus().equalsIgnoreCase(MOrder.DOCSTATUS_Invalid))
			//	setIsPrePayOrder(false);
			return;
		}
		//	Valid Panel
		changeViewPanel();
	}

	/**
	 * Remove All Collect Detail
	 * @return void
	 */
	public void removeAllCollectDetail() {
		centerPanel.removeAll();
		super.removeAllCollectDetail();
		//	Refresh View
		scrollPane.validate();
		scrollPane.repaint();
	}
	
	/**
	 * Remove Collect Detal From Child
	 * @param child
	 * @return void
	 */
	public void removeCollectDetail(VCollectDetail child) {
		Component comp = child.getPanel();
		removeCollect(child);
		centerPanel.remove(comp);
		scrollPane.validate();
		scrollPane.repaint();
	}
	
	/**
	 * Show Collect
	 * @return boolean
	 */
	public boolean showPanel() {
		//	Resize to Height
		//	Set static width
		dialog.setMinimumSize(new Dimension(widthSize , heightSize));
		dialog.setVisible(true);
		return isProcessed;
	}
	
	/**
	 * Get Keyboard
	 * @return POSKeyboard
	 */
	public POSKeyboard getKeyboard() {
		return pos.getKeyboard();
	}

	@Override
	public void refreshPanel() {
		calculatePanelData();
		changeViewPanel();
	}

	@Override
	public String validatePayment() {
		String errorMsg = null;
		if(!pos.hasOrder()) {	//	When is not created order
			errorMsg = "@POS.MustCreateOrder@";
		} else {
			if(!(pos.isStandardOrder()))
				// No Check if Order is not Standard Order
				// TODO: Review why nor Warehouse Order
				errorMsg = validateTenderTypes(pos.getOpenAmt());
		}
		//	
		return errorMsg;
	}

	@Override
	public void changeViewPanel() {
		//	Set Credit for Complete Documents
		boolean isCreditOpen = (pos.isCompleted()
				&& pos.getOpenAmt().doubleValue() > 0);
		//	Is Standard Order
		boolean isStandardOrder = pos.isStandardOrder();
		//	Set Credit Order
		setIsCreditOrder(isCreditOrder() 
				|| (isCreditOpen && !isStandardOrder));
		//	Verify complete order
		if(pos.isCompleted()) {
			buttonPlus.setEnabled(isCreditOpen);
			buttonOk.setEnabled(true);
		} else if(pos.isVoided()){
			buttonPlus.setEnabled(false);
			buttonOk.setEnabled(false);
		} else if(pos.isStandardOrder() /*|| pos.isWarehouseOrder()*/) {
			// Standard Order or Warehouse Order: no Credit Order, no prepayment
			buttonPlus.setEnabled(false);
		}
		else {
			buttonPlus.setEnabled(!isCreditOrder()
					|| isCreditOpen);
			buttonOk.setEnabled(true);
		}
	}
	

	
	/**
	 * Calculate and change data in panel
	 * @return void
	 */
	private void calculatePanelData() {
		//	Get from controller
		BigDecimal collectDetail  = getCollectDetailAmt();
		BigDecimal balance = getBalance(pos.getOpenAmt());
		//	Change View
		String currencyISOCode = pos.getCurSymbol();
		//fieldGrandTotal.setText(currencyISOCode + " "
		//		+ pos.getNumberFormat().format(pos.getGrandTotal()));
		if(isAllowsPartialPayment()) {
			labelPaidAmt.setVisible(true);
			fieldPaidAmt.setVisible(true);
			fieldPaidAmt.setText(currencyISOCode + " "
					+ pos.getNumberFormat().format(pos.getPaidAmt()));
		}
		else
		{
			labelPaidAmt.setVisible(false);
			fieldPaidAmt.setVisible(false);
		}

		fieldPayAmt.setText(currencyISOCode + " "
				+ pos.getNumberFormat().format(collectDetail));
		//	Show pretty Return Amount
		BigDecimal returnAmt = Env.ZERO;
		BigDecimal openAmt = Env.ZERO;
		if(balance.doubleValue() < 0) {
			returnAmt = balance.abs();
		} else if(balance.doubleValue() > 0){
			openAmt = balance;
		}
		//	Set Open Amount
		fieldOpenAmt.setText(currencyISOCode + " "
				+ pos.getNumberFormat().format(openAmt));
		//	Set Return Amount
		fieldReturnAmt.setText(currencyISOCode + " "
				+ pos.getNumberFormat().format(returnAmt));
		
//		fPaymentTerm.setValue(getC_PaymentTerm_ID());
	}

	@Override
	public void vetoableChange(PropertyChangeEvent e)
			throws PropertyVetoException {
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		log.config(name + " = " + value);
	}

	@Override
	public void moveUp() {
	}

	@Override
	public void moveDown() {
	}

	public void hidePanel()
	{
		dialog.setVisible(false);
	}

	public CPanel getPanel()
	{
		return dialog;
	}

	public VPOS getPOS()
	{
		return pos;
	}

} // VCollect