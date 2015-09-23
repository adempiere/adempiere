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

import java.awt.BorderLayout;
import java.awt.Component;
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

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.ConfirmPanel;
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

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com Aug 24, 2015, 10:17:04 PM
 *
 */
public class VCollect extends Collect implements ActionListener {
	
	/**
	 * From POS
	 * *** Constructor ***
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param posPanel
	 */
	public VCollect(VPOS posPanel) {
		super(posPanel.getCtx(), posPanel.getM_Order(), posPanel.getM_POS());
		m_POSPanel = posPanel;
		m_ctx = m_POSPanel.m_ctx;
		collectRowNo = 0;
		m_Balance = Env.ZERO;
		m_Format = DisplayType.getNumberFormat(DisplayType.Amount);
		init();
	}

	/**
	 * Init Dialog
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return void
	 */
	public void init() {
		log.info("");
		try {
			jbInit();
			refreshSummary();
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}
	} // init

	/**	View Panel			*/
	private VPOS 			m_POSPanel;
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
	private JButton 		bCancel;
	private JButton 		bOk;
	private CButton 		bPlus;
	
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

	/**
	 * Instance Frame and fill fields
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> Sep 20, 2015, 11:22:46 PM
	 * @throws Exception
	 * @return void
	 */
	private void jbInit() throws Exception {
		//	Instance Dialog
		v_Dialog = new CDialog(Env.getWindow(m_POSPanel.getWindowNo()), Msg.translate(m_ctx, "Payment"), true);
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

		// sizeFrame
		v_Dialog.setPreferredSize(new Dimension(270, 400));
		
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
		
		//	Add Plus Button
		bPlus = m_POSPanel.f_order.createButtonAction("Plus",
				KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.F2));
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
							GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 0), 0, 0));
		
		//	Add Fields to Main Panel
		v_MainPanel.add(v_ParameterPanel, BorderLayout.NORTH);
		// comandPanel
		commandLayout.setAlignment(FlowLayout.RIGHT);
		commandLayout.setHgap(10);
		v_CommandPanel.add(bCancel, null);
		v_CommandPanel.add(bOk, null);
		
		
		//	Add Listeners
		bPlus.addActionListener(this);
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
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return void
	 */
	public void addCollectType() {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(bPlus)) {
			addCollectType();
		} else if (e.getSource().equals(bOk)) {
			if(fIsCreditOrder.isSelected()) {	//	For Credit Order
				onCreditSale();
			} else if(!fIsPrePayment.isSelected() && m_Balance.compareTo(Env.ZERO) > 0) {	//	For Pre-Payment Order
				ADialog.warn(0, v_Dialog, Msg.getMsg(m_ctx, "POS.OrderPayNotCompleted"));
				return;
			} else if(m_Balance.compareTo(Env.ZERO) < 0) {
				ADialog.warn(0, v_Dialog, Msg.getMsg(m_ctx, "POS.OrderPayNotCompletedAmtExceeded"));
				return;
			} if (!m_POSPanel.processOrder()) {
				ADialog.warn(0, v_Dialog, Msg.parseTranslation(m_ctx, "@POS.OrderProcessFailed@ " + m_POSPanel.getM_Order().getProcessMsg()));
				return;
			}
			//	
			v_Dialog.dispose();
			return;
		} else if (e.getSource().equals(bCancel)) {	//	Nothing
			isPaid = false;
			v_Dialog.dispose();
			return;
		} if(e.getSource().equals(fIsCreditOrder)) {	//	For Credit Order Checked
			boolean isChecked = fIsCreditOrder.isSelected();
			if(fIsPrePayment.isSelected()) {
				fIsPrePayment.setSelected(false);
			}
			bPlus.setEnabled(!isChecked);
			if(isChecked) {
				bOk.setEnabled(isChecked);
			} else {
				bPlus.setEnabled(m_Balance.compareTo(Env.ZERO) == 0);
			}
		} else if(e.getSource().equals(fIsPrePayment)) {	//	For Pre-Payment Order Checked
			if(fIsCreditOrder.isSelected()) {
				fIsCreditOrder.setSelected(false);
			}
			if(m_Balance.compareTo(Env.ZERO) > 0) {
				bPlus.setEnabled(true);
			} else {
				bPlus.setEnabled(false);
			}
		}
	}

	/**
	 * Remove Collect Detal From Child
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
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
	 * 	Process the order.
	 * Usually, the action should be "complete".
	 */
	private void onCreditSale() {
		if( m_POSPanel.getM_Order() == null) {		
			ADialog.warn(0, v_Dialog,  Msg.getMsg(m_ctx, "You must create an Order first"));	//	TODO translate it
		}
		return;
	} // onCreditSale
	
	/**
	 * Show Collect
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return boolean
	 */
	public boolean showCollect() {
		v_Dialog.setMinimumSize(new Dimension(445, 580));
		v_Dialog.pack();
		//	
		AEnv.positionCenterScreen(v_Dialog);
		v_Dialog.setVisible(true);
		return isPaid;
	}

	/**
	 * Refresh Summary
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return void
	 */
	protected void refreshSummary() {
		//	Get from controller
		BigDecimal m_PayAmt = getPayAmt();
		//	
		m_Balance = m_POSPanel.getGrandTotal().subtract(m_PayAmt);
		//	Change View
		fGrandTotal.setText(m_Format.format(m_POSPanel.getGrandTotal()));
		fPayAmt.setText(m_Format.format(m_PayAmt));
		fReturnAmt.setText(m_Format.format(m_Balance));
	}
	
	/**
	 * Get Keyboard
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return POSKeyboard
	 */
	public POSKeyboard getKeyboard() {
		return m_POSPanel.getKeyboard();
	}
}