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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import net.miginfocom.swing.MigLayout;

import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.window.FDialog;
import org.compiere.apps.ADialog;
import org.compiere.apps.AppsAction;
import org.compiere.model.MCurrency;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPOS;
import org.compiere.model.MPOSKey;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentValidate;
import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CDialog;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.ui.event.EventListener;

public class WPosPayment extends Window implements WPosKeyListener, VetoableChangeListener, ActionListener, EventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1961106531807910948L;

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if ( e.getSource().equals(fTenderAmt) || e.getSource().equals(fPayAmt) )
		{
			BigDecimal tender = new BigDecimal( fTenderAmt.getText() );
			BigDecimal pay = new BigDecimal( fPayAmt.getText() );
			if ( tender.compareTo(Env.ZERO) != 0 )
			{
				fReturnAmt.setValue(tender.subtract(pay).toString());
			}
			return;
		}

		if ( e.getSource().equals(f_bProcess))
		{
			processPayment();
		}
		if ( e.getSource().equals(f_bCancel))
		{
			dispose();
			return;
		}

		setTotals();

	}

	private void processPayment() {

		try {

			String tenderType = ((ValueNamePair) tenderTypePick.getValue()).getID();
			BigDecimal amt = new BigDecimal(fPayAmt.getText());

			if ( tenderType.equals(MPayment.TENDERTYPE_Cash) )
			{
				p_posPanel.m_order.payCash(amt);
			}
			else if ( tenderType.equals(MPayment.TENDERTYPE_Check) )
			{
				p_posPanel.m_order.payCheck(amt,fCheckAccountNo.getText(), fCheckRouteNo.getText(), fCheckNo.getText());
				p_posPanel.f_order.openCashDrawer();
			}
			else if ( tenderType.equals(MPayment.TENDERTYPE_CreditCard) )
			{
				String error = null;
				error = MPaymentValidate.validateCreditCardExp(fCCardMonth.getText());
				if ( error != null && !error.isEmpty() )
				{
					FDialog.warn(0, p_posPanel, error, "");
					return;
				}
				int month = MPaymentValidate.getCreditCardExpMM(fCCardMonth.getText());
				int year = MPaymentValidate.getCreditCardExpYY(fCCardMonth.getText());

				ValueNamePair pp = fCCardType.getSelectedItem().toValueNamePair();
				if (pp == null)
					return;
				String type = pp.getValue();
				error = MPaymentValidate.validateCreditCardNumber(fCCardNo.getText(), type);
				if ( error != null && !error.isEmpty() )
				{
					FDialog.warn(0, p_posPanel, error, "");
					return;
				}
				p_posPanel.m_order.payCreditCard(amt, fCCardName.getText(),
						month, year, fCCardNo.getText(), fCCardVC.getText(), type);
				p_posPanel.f_order.openCashDrawer();
			}
			else if ( tenderType.equals(MPayment.TENDERTYPE_Account) )
			{
				p_posPanel.m_order.payCash(amt);
				p_posPanel.f_order.openCashDrawer();
			}
			else
			{
				FDialog.warn(0, this, "Unsupported payment type", "");
			}


			p_posPanel.f_order.openCashDrawer();
			setTotals();
		}
		catch (Exception e )
		{
			FDialog.warn(0, this, "Payment processing failed: " + e.getMessage(),"");
		}
	}

	private WPosBasePanel p_posPanel;
	private MPOS p_pos;
	private Properties p_ctx;
	private PosOrderModel p_order;
	private Textbox fTotal = new Textbox();
	private Textbox fBalance = new Textbox();
	private Listbox tenderTypePick = ListboxFactory.newDropdownListbox();
	private WPosTextField fPayAmt;
	private CButton f_bProcess;
	private boolean paid = false;
	private BigDecimal balance = Env.ZERO;
	private WPosTextField fCheckAccountNo;
	private WPosTextField fCheckNo;
	private WPosTextField fCheckRouteNo;
	private WPosTextField fCCardNo;
	private WPosTextField fCCardName;
	private Listbox fCCardType= ListboxFactory.newDropdownListbox();
	private WPosTextField fCCardMonth;
	private WPosTextField fCCardVC;

	private Label lCheckNo;
	private Label lCheckAccountNo;
	private Label lCheckRouteNo;
	private Label lCCardNo;
	private Label lCCardName;
	private Label lCCardType;
	private Label lCCardMonth;
	private Label lCCardVC;
	private WPosTextField fTenderAmt;
	private Label lTenderAmt;
	private WPosTextField fReturnAmt;
	private Label lReturnAmt;
	private CButton f_bCancel;

	public WPosPayment(WPosBasePanel posPanel, WSubOrder subOrder) {
		super();
		p_posPanel = posPanel;
		p_pos = subOrder.p_pos;
		p_ctx = p_pos.getCtx();
		p_order = subOrder.m_order;
		
		if ( p_order == null )
			dispose();
		
		init();
//		pack();
//		setLocationByPlatform(true);
	}

	private void init() {
		
		Font font = AdempierePLAF.getFont_Field().deriveFont(18f);
		
		//	North
		Panel mainPanel = new Panel();
		appendChild(mainPanel);
		//Msg.translate(p_ctx, "Payment")
		Label gtLabel = new Label(Msg.translate(p_ctx, "GrandTotal"));
		mainPanel.appendChild(gtLabel);
		mainPanel.appendChild(fTotal);
		fTotal.setEnabled(false);
//		fTotal.setHorizontalAlignment(JTextField.TRAILING);
		
		mainPanel.appendChild(new Label(Msg.translate(p_ctx, "Balance")));
		mainPanel.appendChild(fBalance);
		fBalance.setEnabled(false);
//		fBalance.setHorizontalAlignment(JTextField.TRAILING);
		
		
		mainPanel.appendChild(new Label(Msg.translate(p_ctx, "TenderType")));
		// Payment type selection
		int AD_Column_ID = 8416; //C_Payment_v.TenderType
		MLookup lookup = MLookupFactory.get(Env.getCtx(), 0, 0, AD_Column_ID, DisplayType.List);
		ArrayList<Object> types = lookup.getData(true, false, true, true);
		
		// default to cash payment
		for (Object obj : types)
		{
			if ( obj instanceof ValueNamePair )
			{
				ValueNamePair key = (ValueNamePair) obj;
				tenderTypePick.appendItem(key.getName(), key);
				if ( key.getID().equals("X"))   // Cash
					tenderTypePick.setSelectedValueNamePair(key);
				
//				if ( ! "CKX".contains(key.getID() ) )
//					tenderTypePick.removeItemFromSelection(key);
			}
		}
		
//		tenderTypePick.setFont(font);
		tenderTypePick.addActionListener(this);
//		tenderTypePick.setRenderer(new ListCellRenderer() {
//			protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
//
//			public Component getListCellRendererComponent(JList list, Object value,
//					int index, boolean isSelected, boolean cellHasFocus) {
//				
//				JLabel renderer = (JLabel) defaultRenderer
//		        .getListCellRendererComponent(list, value, index, isSelected,
//		            cellHasFocus);
//				
//				renderer.setPreferredSize(new Dimension(50, 50));
//				renderer.setHorizontalAlignment(JLabel.CENTER);
//				
//				return renderer;
//
//			}
//		});
		
		mainPanel.appendChild(tenderTypePick);
		
		fPayAmt = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID());
		mainPanel.appendChild(new Label(Msg.translate(p_ctx, "PayAmt")));
//		fPayAmt.setHorizontalAlignment(JTextField.TRAILING);
//		fPayAmt.addActionListener(this);
		mainPanel.appendChild(fPayAmt);
		
		fTenderAmt = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID());
		lTenderAmt = new Label(Msg.translate(p_ctx, "AmountTendered"));
		mainPanel.appendChild(lTenderAmt);
//		fTenderAmt.addActionListener(this);
//		fTenderAmt.setHorizontalAlignment(JTextField.TRAILING);
		mainPanel.appendChild(fTenderAmt);
		
		fReturnAmt = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID());
		lReturnAmt = new Label(Msg.translate(p_ctx, "AmountReturned"));
		mainPanel.appendChild(lReturnAmt);
//		fReturnAmt.setHorizontalAlignment(JTextField.TRAILING);
		mainPanel.appendChild(fReturnAmt);
		fReturnAmt.setEnabled(false);
		
		fCheckRouteNo = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID());
		lCheckRouteNo = new Label(Msg.translate(p_ctx, "RoutingNo"));
		mainPanel.appendChild(lCheckRouteNo);
		mainPanel.appendChild(fCheckRouteNo);
//		fCheckRouteNo.setFont(font);
//		fCheckRouteNo.setHorizontalAlignment(JTextField.TRAILING);
			
		fCheckAccountNo = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID());
		lCheckAccountNo = new Label(Msg.translate(p_ctx, "AccountNo"));
		mainPanel.appendChild(lCheckAccountNo);
		mainPanel.appendChild(fCheckAccountNo);
//		fCheckAccountNo.setHorizontalAlignment(JTextField.TRAILING);
		
		fCheckNo = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID());
		lCheckNo = new Label(Msg.translate(p_ctx, "CheckNo"));
		mainPanel.appendChild(lCheckNo);
		mainPanel.appendChild(fCheckNo);
//		fCheckNo.setHorizontalAlignment(JTextField.TRAILING);
		
		/**
		 *	Load Credit Cards
		 */
//		ValueNamePair[] ccs = p_order.getCreditCards(new BigDecimal(fPayAmt.getValue().toString()));
		//	Set Selection
//		fCCardType.setRenderer(new ListCellRenderer() {
//			protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
//
//			public Component getListCellRendererComponent(JList list, Object value,
//					int index, boolean isSelected, boolean cellHasFocus) {
//				
//				JLabel renderer = (JLabel) defaultRenderer
//		        .getListCellRendererComponent(list, value, index, isSelected,
//		            cellHasFocus);
//				
//				renderer.setPreferredSize(new Dimension(50, 50));
//				renderer.setHorizontalAlignment(JLabel.CENTER);
//				
//				return renderer;
//
//			}
//		});
		lCCardType = new Label(Msg.translate(p_ctx, "CreditCardType"));
		mainPanel.appendChild(lCCardType);
		mainPanel.appendChild(fCCardType);
//		fCCardType.setFont(font);
			
		fCCardNo = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID(),  new DecimalFormat("#"));
		lCCardNo = new Label(Msg.translate(p_ctx, "CreditCardNumber"));
		mainPanel.appendChild(lCCardNo);
		mainPanel.appendChild(fCCardNo);
//		fCCardNo.setHorizontalAlignment(JTextField.TRAILING);
		
		fCCardName = new WPosTextField(p_posPanel, p_pos.getOSK_KeyLayout_ID());
		lCCardName = new Label(Msg.translate(p_ctx, "Name"));
		mainPanel.appendChild(lCCardName);
		mainPanel.appendChild(fCCardName);
//		fCCardName.setHorizontalAlignment(JTextField.TRAILING);
		
		fCCardMonth = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID(), new DecimalFormat("#"));
		lCCardMonth = new Label(Msg.translate(p_ctx, "Expires"));
		mainPanel.appendChild(lCCardMonth);
		mainPanel.appendChild(fCCardMonth);
//		fCCardMonth.setHorizontalAlignment(JTextField.TRAILING);
		
		fCCardVC = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID(),  new DecimalFormat("#"));
		lCCardVC = new Label(Msg.translate(p_ctx, "CVC"));
		mainPanel.appendChild(lCCardVC);
		mainPanel.appendChild(fCCardVC);
//		fCCardVC.setHorizontalAlignment(JTextField.TRAILING);

		ConfirmPanel confirm = new ConfirmPanel(true, false, true, false, false, false, false);
		confirm.addActionListener(this);

		mainPanel.appendChild(confirm);
		
//		pack();
		
		setTotals();
	}

	private void setTotals() {

		String tenderType = ((ValueNamePair) tenderTypePick.getValue()).getID();
		boolean cash = MPayment.TENDERTYPE_Cash.equals(tenderType);
		boolean check = MPayment.TENDERTYPE_Check.equals(tenderType);
		boolean creditcard = MPayment.TENDERTYPE_CreditCard.equals(tenderType);
		boolean account = MPayment.TENDERTYPE_Account.equals(tenderType);

		fTenderAmt.setVisible(cash);
		fReturnAmt.setVisible(cash);
		lTenderAmt.setVisible(cash);
		lReturnAmt.setVisible(cash);
		
		fCheckAccountNo.setVisible(check);
		fCheckNo.setVisible(check);
		fCheckRouteNo.setVisible(check);
		lCheckAccountNo.setVisible(check);
		lCheckNo.setVisible(check);
		lCheckRouteNo.setVisible(check);

		fCCardMonth.setVisible(creditcard);
		fCCardName.setVisible(creditcard);
		fCCardNo.setVisible(creditcard);
		fCCardType.setVisible(creditcard);
		fCCardVC.setVisible(creditcard);
		lCCardMonth.setVisible(creditcard);
		lCCardName.setVisible(creditcard);
		lCCardNo.setVisible(creditcard);
		lCCardType.setVisible(creditcard);
		lCCardVC.setVisible(creditcard);

		fTotal.setValue(p_order.getGrandTotal().toString());
		
		BigDecimal received = p_order.getPaidAmt();		
		balance  = p_order.getGrandTotal().subtract(received);
		balance = balance.setScale(MCurrency.getStdPrecision(p_ctx, p_order.getC_Currency_ID()));
		if ( balance.compareTo(Env.ZERO) <= 0 )
		{
			paid = true;
			
			if ( balance.compareTo(Env.ZERO) < 0 )
					FDialog.warn(0, this, Msg.getMsg(p_ctx, "Change") + ": " + balance,"");
			dispose();
		}
		
		fBalance.setValue(balance.toString());
		fPayAmt.setValue(balance.toString());
		if ( !MPayment.TENDERTYPE_Cash.equals(tenderType) )
		{
//			fPayAmt.requestFocusInWindow();
			SwingUtilities.invokeLater(new Runnable() {

				public void run() {
					fPayAmt.select();
				}
			});
		}
		else
		{
//			fTenderAmt.requestFocusInWindow();
		}
		
//		pack();
	}

	public void keyReturned(MPOSKey key) {
		
		String text = key.getText();
		String payAmt = fPayAmt.getText();
//		String selected = fPayAmt.getSelectedText();
//		if ( selected != null && !selected.isEmpty() )
//		{
//			payAmt = payAmt.replaceAll(selected, "");
//		}
		
		if ( text != null && !text.isEmpty() )
		{
			if ( text.equals(".") && payAmt.indexOf(".") == -1 )
			{
				fPayAmt.setText(payAmt + text);
				return;
			}
			try
			{
				Integer.parseInt(text);		// test if number
				fPayAmt.setText(payAmt + text);
			}
			catch (NumberFormatException e)
			{
				// ignore non-numbers
			}
		}
	}

	public static boolean pay(WPosBasePanel posPanel, WSubOrder subOrder) {
		
		WPosPayment pay = new WPosPayment(posPanel, subOrder);
		pay.setVisible(true);
		pay.setMinwidth(300);
		pay.setMinheight(300);
		
		return pay.isPaid();
	}

	private boolean isPaid() {
		return paid ;
	}

	public void vetoableChange(PropertyChangeEvent arg0)
			throws PropertyVetoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
