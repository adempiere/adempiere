/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.apps.form;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import org.compiere.apps.ADialog;
import org.compiere.apps.ConfirmPanel;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MLookupInfo;
import org.compiere.model.MPaySelectionCheck;
import org.compiere.model.MPaymentBatch;
import org.compiere.plaf.CompiereColor;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.PaymentExport;
import org.compiere.util.PaymentExportList;
import org.compiere.util.ValueNamePair;

/**
 *  Payment Print & Export
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VPayPrint.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 
 *  Contributors:
 *    Carlos Ruiz - GlobalQSS - FR 3132033 - Make payment export class configurable per bank
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 297 ] Payment Selection must be like ADempiere Document, this process is changed to 
 *			document workflow of Payment Selection
 *		@see https://github.com/adempiere/adempiere/issues/297
 *	@author  victor.perez , victor.perez@e-evolution.com http://www.e-evolution.com
 * 		<li> FR [ 297 ] Apply ADempiere best Pratice
 *		@see https://github.com/adempiere/adempiere/issues/297
 */
@Deprecated
public class VPayPrint extends PayPrint implements FormPanel, ActionListener, VetoableChangeListener
{
	private CPanel panel = new CPanel();

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		log.info("");
		windowNo = WindowNo;
		m_frame = frame;
		try
		{
			dynInit();
			jbInit();
			frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
			frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	init

	
	/**	FormFrame			*/
	private FormFrame 		m_frame;
	
	//  Static Variables
	private CPanel centerPanel = new CPanel();
	private CPanel southPanel = new CPanel();
	private FlowLayout southLayout = new FlowLayout();
	private GridBagLayout centerLayout = new GridBagLayout();
	private JButton bPrint = ConfirmPanel.createPrintButton(true);
	private JButton bExport = ConfirmPanel.createExportButton(true);
	private JButton bCancel = ConfirmPanel.createCancelButton(true);
	private JButton bProcess = ConfirmPanel.createProcessButton(Msg.getMsg(Env.getCtx(), "VPayPrintProcess"));
	private CLabel lPaySelect = new CLabel();
	private VLookup paySelectSearch = null;
	private CLabel lBank = new CLabel();
	private CLabel fBank = new CLabel();
	private CLabel lPaymentRule = new CLabel();
	private CComboBox fPaymentRule = new CComboBox();
	private CLabel lDocumentNo = new CLabel();
	private VNumber fDocumentNo = new VNumber();
	private CLabel lNoPayments = new CLabel();
	private CLabel fNoPayments = new CLabel();
	private CLabel lBalance = new CLabel();
	private VNumber fBalance = new VNumber();
	private CLabel lCurrency = new CLabel();
	private CLabel fCurrency = new CLabel();

	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		CompiereColor.setBackground(panel);
		//
		southPanel.setLayout(southLayout);
		southLayout.setAlignment(FlowLayout.RIGHT);
		centerPanel.setLayout(centerLayout);
		//
		bPrint.addActionListener(this);
		bExport.addActionListener(this);
		bCancel.addActionListener(this);
		//
		bProcess.setText(Msg.getMsg(Env.getCtx(), "EFT"));
		bProcess.setEnabled(false);
		bProcess.addActionListener(this);
		//
		lPaySelect.setText(Msg.translate(Env.getCtx(), "C_PaySelection_ID"));
		//
		lBank.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
		//
		lPaymentRule.setText(Msg.translate(Env.getCtx(), "PaymentRule"));
		fPaymentRule.addActionListener(this);
		//
		lDocumentNo.setText(Msg.translate(Env.getCtx(), "DocumentNo"));
		fDocumentNo.setDisplayType(DisplayType.Integer);
		lNoPayments.setText(Msg.getMsg(Env.getCtx(), "NoOfPayments"));
		fNoPayments.setText("0");
		lBalance.setText(Msg.translate(Env.getCtx(), "CurrentBalance"));
		fBalance.setReadWrite(false);
		fBalance.setDisplayType(DisplayType.Amount);
		lCurrency.setText(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		//
		southPanel.add(bCancel, null);
		southPanel.add(bExport, null);
		southPanel.add(bPrint, null);
		southPanel.add(bProcess, null);
		//
		centerPanel.add(lPaySelect,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(12, 12, 5, 5), 0, 0));
		centerPanel.add(paySelectSearch, new GridBagConstraints(1, 0, 3, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(12, 0, 5, 12), 0, 0));
		centerPanel.add(lBank,   new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 12, 5, 5), 0, 0));
		centerPanel.add(fBank,    new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 12), 0, 0));
		centerPanel.add(lPaymentRule,   new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 12, 5, 5), 0, 0));
		centerPanel.add(fPaymentRule,    new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 12), 0, 0));
		centerPanel.add(lDocumentNo,   new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 12, 5, 5), 0, 0));
		centerPanel.add(fDocumentNo,    new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 12), 0, 0));
		centerPanel.add(lNoPayments,   new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 12, 5, 5), 0, 0));
		centerPanel.add(fNoPayments,    new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 12), 0, 0));
		centerPanel.add(lBalance,    new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 12, 5, 5), 0, 0));
		centerPanel.add(fBalance,    new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 12), 0, 0));
		centerPanel.add(lCurrency,  new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 12, 12, 5), 0, 0));
		centerPanel.add(fCurrency,   new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 12, 12), 0, 0));
	}   //  VPayPrint

	/**
	 *  Dynamic Init
	 */
	private void dynInit()
	{
		
		//  C_PaySelection_ID
		int AD_Column_ID = 7670;        //  C_PaySelectionCheck.C_PaySelection_ID
		//	FR [ 297 ]
		//	Add DocStatus for validation
		MLookupInfo info = MLookupFactory.getLookupInfo(Env.getCtx(), windowNo, AD_Column_ID, DisplayType.Search);
		info.ValidationCode = getValidationCode();
		MLookup lookupPS = new MLookup(info, 0);
		paySelectSearch = new VLookup("C_PaySelection_ID", true, false, true, lookupPS);
		paySelectSearch.addVetoableChangeListener(this);
		
	}   //  dynInit

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose

	/**
	 * 	Set Payment Selection
	 *	@param payselectionId id
	 */
	public void setPaySelection (int payselectionId)
	{
		if (payselectionId == 0)
			return;

		this.paySelectionId = payselectionId;
		paySelectSearch.setValue(new Integer(this.paySelectionId));
		loadPaySelectInfo();
	}	//	setsetPaySelection

	/**************************************************************************
	 *  Action Listener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		//	log.config( "VPayPrint.actionPerformed" + e.toString());
		if (e.getSource() == bCancel)
			dispose();
		else if (paySelectionId <= 0)
			return;
		else if (e.getSource() == fPaymentRule)
			loadPaymentRuleInfo();
		else if (e.getSource() == bExport)
			cmd_export();
		else if (e.getSource() == bProcess)
			cmd_EFT();
		else if (e.getSource() == bPrint)
			cmd_print();
	}   //  actionPerformed

	/**
	 *  PaySelect changed - load Bank
	 */
	private void loadPaySelectInfo()
	{
		log.info( "VPayPrint.loadPaySelectInfo");
		if (paySelectionId <= 0)
			return;
		
		loadPaySelectInfo(paySelectionId);
		
		fBank.setText(bank);
		fCurrency.setText(currency);
		fBalance.setValue(balance);
		
		m_frame.pack();
		
		loadPaymentRule();
	}   //  loadPaySelectInfo

	/**
	 *  Bank changed - load PaymentRule
	 */
	private void loadPaymentRule()
	{
		log.info("");
		if (bankAccountId == -1)
			return;
		
		fPaymentRule.removeAllItems();
		
		ArrayList<ValueNamePair> data = loadPaymentRule(paySelectionId);
		for(ValueNamePair pp : data)
			fPaymentRule.addItem(pp);
		
		if (fPaymentRule.getItemCount() > 0)
			fPaymentRule.setSelectedIndex(0);
		
		loadPaymentRuleInfo();
	}   //  loadPaymentRule

	/**
	 *  PaymentRule changed - load DocumentNo, NoPayments,
	 *  enable/disable EFT, Print
	 */
	private void loadPaymentRuleInfo()
	{
		ValueNamePair pp = (ValueNamePair)fPaymentRule.getSelectedItem();
		if (pp == null)
			return;
		String paymentRule = pp.getValue();

		log.info("PaymentRule=" + paymentRule);
		fNoPayments.setText(" ");
		
		String msg = loadPaymentRuleInfo(paySelectionId, paymentRule);
		
		if(noPayments != null)
			fNoPayments.setText(noPayments);
		
		bProcess.setEnabled(paymentRule.equals("T"));
		
		if(documentNo != null)
			fDocumentNo.setValue(documentNo);
		
		if(msg != null && msg.length() > 0)
			ADialog.error(windowNo, panel, msg);
	}   //  loadPaymentRuleInfo


	/**************************************************************************
	 *  Export payments to file
	 */
	private void cmd_export()
	{
		ValueNamePair pp = (ValueNamePair)fPaymentRule.getSelectedItem();
		if (pp == null)
			return;
		String paymentRule = pp.getValue();
		log.info(paymentRule);
		if (!getChecks(paymentRule))
			return;

		//  Get File Info
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle(Msg.getMsg(Env.getCtx(), "Export"));
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setMultiSelectionEnabled(false);
		fc.setSelectedFile(new java.io.File("paymentExport.txt"));
		if (fc.showSaveDialog(panel) != JFileChooser.APPROVE_OPTION)
			return;

		//  Create File
		int no = 0;
		StringBuffer error = new StringBuffer("");
		if (paymentExportClass == null || paymentExportClass.trim().length() == 0) {
			paymentExportClass = "org.compiere.util.GenericPaymentExport";
		}

		try
		{
			Class<?> clazz = Class.forName(paymentExportClass);
			if (PaymentExportList.class.isAssignableFrom(clazz))
			{
				PaymentExportList custom = (PaymentExportList)clazz.newInstance();
				no = custom.exportToFile(paySelectionChecks, fc.getSelectedFile(), error);
			}
			else if (PaymentExport.class.isAssignableFrom(clazz))
			{
				PaymentExport custom = (PaymentExport)clazz.newInstance();
				no = custom.exportToFile(paySelectionChecks.toArray(new MPaySelectionCheck[paySelectionChecks.size()]), fc.getSelectedFile(), error);
			}
		}
		catch (ClassNotFoundException e)
		{
			no = -1;
			error.append("No custom PaymentExport class " + paymentExportClass + " - " + e.toString());
			log.log(Level.SEVERE, error.toString(), e);
		}
		catch (Exception e)
		{
			no = -1;
			error.append("Error in " + paymentExportClass + " check log, " + e.toString());
			log.log(Level.SEVERE, error.toString(), e);
		}
		if (no >= 0) {
			ADialog.info(windowNo, panel, "Saved",
					fc.getSelectedFile().getAbsolutePath() + "\n"
					+ Msg.getMsg(Env.getCtx(), "NoOfLines") + "=" + no);

			if (ADialog.ask(windowNo, panel, "VPayPrintSuccess?"))
			{
				//	int lastDocumentNo = 
				int lastDocumentNo = MPaySelectionCheck.confirmPrint (paySelectionChecks, paymentBatch);
				if (lastDocumentNo != 0)
					setBankAccountNextSequence(paymentRule, ++lastDocumentNo);
				//	document No not updated
			}
		} else {
			ADialog.error(windowNo, panel, "Error", error.toString());
		}
		dispose();
	}   //  cmd_export

	/**
	 *  Create EFT payment
	 */
	private void cmd_EFT()
	{
		ValueNamePair pp = (ValueNamePair)fPaymentRule.getSelectedItem();
		if (pp == null)
			return;
		String PaymentRule = pp.getValue();
		log.info(PaymentRule);
		if (!getChecks(PaymentRule))
			return;
		dispose();
	}   //  cmd_EFT

	/**
	 *  Print Checks and/or Remittance
	 */
	private void cmd_print()
	{
		ValueNamePair valueNamePair = (ValueNamePair)fPaymentRule.getSelectedItem();
		if (valueNamePair == null)
			return;
		String paymentRule = valueNamePair.getValue();
		log.info(paymentRule);
		if (!getChecks(paymentRule))
			return;

		panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		AtomicBoolean somethingPrinted = new AtomicBoolean(false);
		boolean directPrint = !Ini.isPropertyBool(Ini.P_PRINTPREVIEW);
		//	for all checks
		paySelectionChecks.stream()
				.filter(paySelectionCheck -> paySelectionCheck != null)
				.forEach(paySelectionCheck -> {
			//	ReportCtrl will check BankAccountDoc for PrintFormat
			boolean ok = ReportCtl.startDocumentPrint(ReportEngine.CHECK, paySelectionCheck.get_ID(), null, Env.getWindowNo(panel), directPrint);
			if (!somethingPrinted.get() && ok)
				somethingPrinted.set(true);
		});

		//	Confirm Print and Update BankAccountDoc
		if (somethingPrinted.get() && ADialog.ask(windowNo, panel, "VPayPrintSuccess?"))
		{
			int lastDocumentNo = MPaySelectionCheck.confirmPrint (paySelectionChecks, paymentBatch);
			if (lastDocumentNo != 0)
				setBankAccountNextSequence(paymentRule, ++lastDocumentNo);
		}	//	confirm

		if (ADialog.ask(windowNo, panel, "VPayPrintPrintRemittance"))
		{
			paySelectionChecks.stream()
					.filter(paySelectionCheck -> paySelectionCheck != null)
					.forEach( paySelectionCheck -> {
				ReportCtl.startDocumentPrint(ReportEngine.REMITTANCE, paySelectionCheck.get_ID(), null, Env.getWindowNo(panel), directPrint);
			});
		}	//	remittance

		panel.setCursor(Cursor.getDefaultCursor());
		dispose();
	}   //  cmd_print

	
	/**************************************************************************
	 *  Get Checks
	 *  @param paymentRule Payment Rule
	 *  @return true if payments were created
	 */
	private boolean getChecks(String paymentRule)
	{
		//  do we have values
		if (paySelectionId <= 0 || bankAccountId == -1
			|| fPaymentRule.getSelectedIndex() == -1 || fDocumentNo.getValue() == null)
		{
			ADialog.error(windowNo, panel, "VPayPrintNoRecords",
				"(" + Msg.translate(Env.getCtx(), "C_PaySelectionLine_ID") + "=0)");
			return false;
		}

		//  get data
		int startDocumentNo = ((Number)fDocumentNo.getValue()).intValue();

		log.config("C_PaySelection_ID=" + paySelectionId + ", PaymentRule=" +  paymentRule + ", DocumentNo=" + startDocumentNo);
		//
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		//	get Selections
		paySelectionChecks = MPaySelectionCheck.get(paySelectionId, paymentRule, startDocumentNo, null);

		panel.setCursor(Cursor.getDefaultCursor());
		//
		if (paySelectionChecks == null || paySelectionChecks.size() == 0)
		{
			ADialog.error(windowNo, panel, "VPayPrintNoRecords",
				"(" + Msg.translate(Env.getCtx(), "C_PaySelectionLine_ID") + " #0");
			return false;
		}
		paymentBatch = MPaymentBatch.getForPaySelection (Env.getCtx(), paySelectionId, null);
		return true;
	}   //  getChecks

	/**
	 *  Vetoable Change Listener.
	 *  - Payment Selection
	 *  @param e event
	 */
	@Override
	public void vetoableChange(PropertyChangeEvent e)
			throws PropertyVetoException {
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		log.config(name + "=" + value);
		
		if (value == null)
			return;
		
		//  Payment Selection
		if (name.equals("C_PaySelection_ID"))
		{
			paySelectSearch.setValue(value);
			paySelectionId = ((Integer)value).intValue();
			loadPaySelectInfo();
		}
	}

}   //  PayPrint
