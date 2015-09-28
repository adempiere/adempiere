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
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import org.adempiere.pipo.exception.POSaveFailedException;
import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.pos.service.Collect;
import org.adempiere.pos.service.I_POSPanel;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.AppsAction;
import org.compiere.apps.ConfirmPanel;
import org.compiere.model.MOrder;
import org.compiere.model.X_C_Payment;
import org.compiere.swing.CButton;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CDialog;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class VCollect extends Collect
		implements ActionListener, I_POSPanel {
	
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
		m_Balance = Env.ZERO;
		m_Format = DisplayType.getNumberFormat(DisplayType.Amount);
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
	private CLabel 			lReturnAmt;
	private CLabel 			fReturnAmt;
	private CLabel 			fLine;
	private CLabel 			lPayAmt;
	private CLabel 			fPayAmt;
	private CCheckBox 		fIsPrePayment;
	private CCheckBox 		fIsCreditOrder;
	
	/**	Action				*/
	private CButton 		bPlus;
	private JButton 		bCancel;
	private JButton 		bOk;
	
	/**	Generic Values		*/
	private boolean 		isPaid;
	private Properties 		m_ctx;
	private int 			collectRowNo;
	private BigDecimal 		m_Balance;
	private DecimalFormat	m_Format;
	
	/**	Log					*/
	private CLogger 		log = CLogger.getCLogger(VCollect.class);
	/**	Default Font		*/
	private Font 			font = AdempierePLAF.getFont_Field().deriveFont(Font.BOLD, 18);
	/**	Default Width		*/
	private final int		SUMMARY_FIELD_WIDTH 	= 200;
	/**	Default Height		*/
	private final int		SUMMARY_FIELD_HEIGHT 	= 30;
	/**	Plus Button Size	*/
	private final int		BUTTON_SIZE				= 50;

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
		lGrandTotal.setFont(font);
		//	
		fGrandTotal = new CLabel();
		fGrandTotal.setFont(font);
		//	
		fGrandTotal.setPreferredSize(new Dimension(SUMMARY_FIELD_WIDTH, SUMMARY_FIELD_HEIGHT));
		
		//	Add Payment Amount
		lPayAmt = new CLabel(Msg.translate(m_ctx, "PayAmt") + ":");
		lPayAmt.setFont(font);
		//	
		fPayAmt = new CLabel();
		fPayAmt.setFont(font);
		fPayAmt.setPreferredSize(new Dimension(SUMMARY_FIELD_WIDTH, SUMMARY_FIELD_HEIGHT));
		//	Add Line
		fLine = new CLabel("________________");
		fLine.setFont(font);
		//	For Returned Amount
		lReturnAmt = new CLabel(Msg.translate(m_ctx, "AmountReturned") + ":");
		lReturnAmt.setFont(font);
		//	
		fReturnAmt = new CLabel();
		fReturnAmt.setFont(font);
		fReturnAmt.setPreferredSize(new Dimension(SUMMARY_FIELD_WIDTH, SUMMARY_FIELD_HEIGHT));
		
		//	Add Is Pre-Payment
		fIsPrePayment = new CCheckBox(Msg.translate(m_ctx, "IsPrePayment"));
		fIsPrePayment.setFont(font);
		
		//	Add Is Credit Order
		fIsCreditOrder = new CCheckBox(Msg.translate(m_ctx, "CreditSale"));
		fIsCreditOrder.setFont(font);
		
		// Pre-Payment, Standard Order: enable only if the order is completed and there are lines 
		if(v_POSPanel.getTotalLines().compareTo(Env.ZERO)==1 && 
		   v_POSPanel.isCompleted() &&
		   v_POSPanel.getM_Order().getC_DocType().getDocSubTypeSO().equalsIgnoreCase(MOrder.DocSubTypeSO_Standard)) {	
			fIsPrePayment.setEnabled(false);	
			fIsCreditOrder.setEnabled(false);
			fIsPrePayment.setSelected(true);
		}
		// Pre-Payment, Credit Order: enable only if the order is drafted and there are lines 
		else if(v_POSPanel.getTotalLines().compareTo(Env.ZERO)==1 && 
				!v_POSPanel.isCompleted()) {		
			fIsPrePayment.setEnabled(true);	
			fIsCreditOrder.setEnabled(true);
		}
		else {
			fIsPrePayment.setEnabled(false);	
			fIsCreditOrder.setEnabled(false);
			if(v_POSPanel.isCompleted() && 
				v_POSPanel.getM_Order().isInvoiced()  && 
				v_POSPanel.getOpenAmt().compareTo(Env.ZERO)==1) {
				fIsCreditOrder.setSelected(true);
			}
		}
		
		//	Add Plus Button
		AppsAction act = new AppsAction("Plus", KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.F2), false);
		act.setDelegate(this);
		bPlus = (CButton)act.getButton();
		bPlus.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
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

		v_ParameterPanel.add(fLine, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		v_ParameterPanel.add(lReturnAmt, new GridBagConstraints(1, 3, 1, 1, 0.0,0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(0, 0, 0, 0), 0, 0));
		
		v_ParameterPanel.add(fReturnAmt, new GridBagConstraints(2, 3, 1, 1, 0.0,0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(0, 0, 0, 0), 0, 0));

		v_ParameterPanel.add(fIsPrePayment, new GridBagConstraints(1, 4, 1, 1, 0.0,0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		v_ParameterPanel.add(fIsCreditOrder, new GridBagConstraints(2, 4, 1, 1, 0.0,0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		v_ParameterPanel.add(bPlus, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 0), 0, 0));
		
		//	Add Fields to Main Panel
		v_MainPanel.add(v_ParameterPanel, BorderLayout.NORTH);
		// comandPanel
		commandLayout.setAlignment(FlowLayout.RIGHT);
		commandLayout.setHgap(10);
		v_CommandPanel.add(bCancel, null);
		v_CommandPanel.add(bOk, null);
		
		
		//	Add Listeners
		fIsPrePayment.addActionListener(this);
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
		//	
		VCollectDetail collectDetail = new VCollectDetail(this, tenderType, Env.ZERO);
		//	Add Collect controller
		addCollect(collectDetail);
		// add parameter panel
		v_CenterPanel.add(collectDetail.getPanel(), new GridBagConstraints(0, collectRowNo, 1, 1, 0.0, 0.0,
						GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 5, 5, 5), 0, 0));
		//	Repaint
		v_ScrollPanel.validate();
		v_ScrollPanel.repaint();
		//	Add Count
		collectRowNo++;
	}

	/**
	 * Process Window
	 * @return
	 * @return String
	 */
	public String saveData() {
		try {
			v_Dialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			v_POSPanel.setPrepayment(fIsPrePayment.isSelected());
			setCreditOrder(fIsCreditOrder.isSelected());
			setReturnAmt(new BigDecimal(fReturnAmt.getText()));
			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					if(v_POSPanel.processOrder(trxName)) {
						processPayment(trxName);
					} else {
						throw new POSaveFailedException(v_POSPanel.getProcessMsg());
					}
				}
			});
		} catch (Exception e) {
			return e.getMessage();
		} finally {
			v_Dialog.setCursor(Cursor.getDefaultCursor());
		}
		//	Default
		return null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//	Validate Event
		if (e.getSource().equals(bPlus)) {
			addCollectType();
		} else if (e.getSource().equals(bOk)) {
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
			//	
			v_Dialog.dispose();
			return;
		} else if (e.getSource().equals(bCancel)) {	//	Nothing
			isPaid = false;
			v_Dialog.dispose();
			return;
		} else if(e.getSource().equals(fIsCreditOrder)) {	//	For Credit Order Checked
			fIsPrePayment.setSelected(false);
			if(fIsCreditOrder.isSelected()) {				
				bPlus.setVisible(false);  // TODO setEnable(false) doesn't work!!
				bOk.setEnabled(true);
			}
			else 
				bPlus.setVisible(true);
		} else if(e.getSource().equals(fIsPrePayment)) {	//	For Pre-Payment Order Checked
			fIsCreditOrder.setSelected(false);
			bPlus.setVisible(true);   // TODO setEnable(true) doesn't work!!
		}
		//	Valid Panel
		changeViewPanel();
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
		v_Dialog.setMinimumSize(new Dimension(500, 580));
		v_Dialog.pack();
		//	
		AEnv.positionCenterScreen(v_Dialog);
		v_Dialog.setVisible(true);
		return isPaid;
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
		//	Get from controller
		BigDecimal m_PayAmt = getPayAmt();
		//	
		m_Balance = v_POSPanel.getOpenAmt().subtract(m_PayAmt);
		m_Balance = m_Balance.setScale(2, BigDecimal.ROUND_HALF_UP);
		//	Change View
		fGrandTotal.setText(m_Format.format(v_POSPanel.getOpenAmt()));
		fPayAmt.setText(m_Format.format(m_PayAmt));
		fReturnAmt.setText(m_Format.format(m_Balance));
		//	
		changeViewPanel();
	}

	@Override
	public String validatePanel() {
		if(!v_POSPanel.hasOrder()) {	//	When is not created order
			return "VCollect.MustCreateOrder";
		} else if(fIsCreditOrder.isSelected()) {	//	For Credit Order
			return null;
		} else if(!fIsPrePayment.isSelected() 
				&& m_Balance.doubleValue() > 0) {	//	For Pre-Payment Order
			return "VCollect.OrderPayNotCompleted";
		} else if(m_Balance.doubleValue() > 0) {
			return "VCollect.InsufficientOrderPaymentAmt";
		}
		//	
		return null;
	}

	@Override
	public void changeViewPanel() {
		if(fIsCreditOrder.isSelected()) {
			fIsPrePayment.setSelected(false);
			bPlus.setEnabled(true);  // TODO setEnable(true) doesn't work!!
			
			if((!v_POSPanel.isCompleted() && m_Balance.doubleValue() > 0) ||
			   (v_POSPanel.isCompleted() && getPayAmt().compareTo(Env.ZERO)==1) ) 
				bOk.setEnabled(true);
			else
				bOk.setEnabled(false);
			
		} else if(fIsPrePayment.isSelected()) {
			if(getPayAmt().doubleValue() > 0) {
				bOk.setEnabled(true);
			} else {
				bOk.setEnabled(false);
			}
		} else if(m_Balance.doubleValue() <= 0) {
			bOk.setEnabled(true);
		} else {
			bOk.setEnabled(false);
		}
	}
}