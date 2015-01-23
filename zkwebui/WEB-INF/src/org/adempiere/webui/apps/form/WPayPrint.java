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
package org.adempiere.webui.apps.form;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.editor.WNumberEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.adempiere.webui.window.SimplePDFViewer;
import org.compiere.apps.form.PayPrint;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPaySelectionCheck;
import org.compiere.model.MPaymentBatch;
import org.compiere.print.ReportEngine;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.PaymentExport;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Filedownload;

/**
 *  Payment Print & Export
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VPayPrint.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 
 *  Contributors:
 *    Carlos Ruiz - GlobalQSS - FR 3132033 - Make payment export class configurable per bank
 */
public class WPayPrint extends PayPrint implements IFormController, EventListener, ValueChangeListener
{
	private CustomForm form = new CustomForm();

	/**
	 *	Initialize Panel
	 */
	public WPayPrint()
	{
		try
		{
			dynInit();
			zkInit();
			Borderlayout contentLayout = new Borderlayout();
			contentLayout.setWidth("100%");
			contentLayout.setHeight("100%");
			form.appendChild(contentLayout);
			Center center = new Center();
			contentLayout.appendChild(center);
			center.appendChild(centerPanel);
			South south = new South();
			south.setStyle("border: none");
			contentLayout.appendChild(south);
			south.appendChild(southPanel);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	init
	
	//  Static Variables
	private Panel centerPanel = new Panel();
	private ConfirmPanel southPanel = new ConfirmPanel(true, false, false, false, false, false, false);
	private Grid centerLayout = GridFactory.newGridLayout();
	private Button bPrint = southPanel.createButton(ConfirmPanel.A_PRINT);
	private Button bExport = southPanel.createButton(ConfirmPanel.A_EXPORT);
	private Button bCancel = southPanel.getButton(ConfirmPanel.A_CANCEL);
	private Button bProcess = southPanel.createButton(ConfirmPanel.A_PROCESS);
	private Label lPaySelect = new Label();
	private WSearchEditor paySelectSearch = null;
	private Label lBank = new Label();
	private Label fBank = new Label();
	private Label lPaymentRule = new Label();
	private Listbox fPaymentRule = ListboxFactory.newDropdownListbox();
	private Label lDocumentNo = new Label();
	private WNumberEditor fDocumentNo = new WNumberEditor();
	private Label lNoPayments = new Label();
	private Label fNoPayments = new Label();
	private Label lBalance = new Label();
	private WNumberEditor fBalance = new WNumberEditor();
	private Label lCurrency = new Label();
	private Label fCurrency = new Label();

	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void zkInit() throws Exception
	{
		//
		centerPanel.appendChild(centerLayout);
		//
		bPrint.addActionListener(this);
		bExport.addActionListener(this);
		bCancel.addActionListener(this);
		//
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
		fDocumentNo.getComponent().setIntegral(true);
		lNoPayments.setText(Msg.getMsg(Env.getCtx(), "NoOfPayments"));
		fNoPayments.setText("0");
		lBalance.setText(Msg.translate(Env.getCtx(), "CurrentBalance"));
		fBalance.setReadWrite(false);
		fBalance.getComponent().setIntegral(false);
		lCurrency.setText(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		//
		southPanel.addButton(bExport);
		southPanel.addButton(bPrint);
		southPanel.addButton(bProcess);
		//
		Rows rows = centerLayout.newRows();
		Row row = rows.newRow();
		row.appendChild(lPaySelect.rightAlign());
		row.appendChild(paySelectSearch.getComponent());

		row = rows.newRow();
		row.appendChild(lBank.rightAlign());
		row.appendChild(fBank);
		row.appendChild(lBalance.rightAlign());
		row.appendChild(fBalance.getComponent());
			
		row = rows.newRow();
		row.appendChild(lPaymentRule.rightAlign());
		row.appendChild(fPaymentRule);
		row.appendChild(lCurrency.rightAlign());
		row.appendChild(fCurrency);
		
		row = rows.newRow();
		row.appendChild(lDocumentNo.rightAlign());
		row.appendChild(fDocumentNo.getComponent());
		row.appendChild(lNoPayments.rightAlign());
		row.appendChild(fNoPayments);
		
		southPanel.getButton(ConfirmPanel.A_OK).setVisible(false);
	}   //  VPayPrint

	/**
	 *  Dynamic Init
	 */
	private void dynInit()
	{
		//  C_PaySelection_ID
		int AD_Column_ID = 7670;        //  C_PaySelectionCheck.C_PaySelection_ID
		MLookup lookupPS = MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, AD_Column_ID, DisplayType.Search);
		paySelectSearch = new WSearchEditor("C_PaySelection_ID", true, false, true, lookupPS);
		paySelectSearch.addValueChangeListener(this);

	}   //  dynInit

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		SessionManager.getAppDesktop().closeActiveWindow();
	}	//	dispose

	/**
	 * 	Set Payment Selection
	 *	@param C_PaySelection_ID id
	 */
	public void setPaySelection (int C_PaySelection_ID)
	{
		if (C_PaySelection_ID == 0)
			return;
		//
		m_C_PaySelection_ID = C_PaySelection_ID;
		paySelectSearch.setValue(new Integer(m_C_PaySelection_ID));
		loadPaySelectInfo();
	}	//	setsetPaySelection

	
	/**************************************************************************
	 *  Action Listener
	 *  @param e event
	 */
	public void onEvent(Event e)
	{
	//	log.config( "VPayPrint.actionPerformed" + e.toString());
		if (e.getTarget() == bCancel)
			dispose();
		else if (m_C_PaySelection_ID <= 0)
			return;
		else if (e.getTarget() == fPaymentRule)
			loadPaymentRuleInfo();
		//
		else if (e.getTarget() == bExport)
			cmd_export();
		else if (e.getTarget() == bProcess)
			cmd_EFT();
		else if (e.getTarget() == bPrint)
			cmd_print();
	}   //  actionPerformed

	/**
	 *  PaySelect changed - load Bank
	 */
	private void loadPaySelectInfo()
	{
		log.info( "VPayPrint.loadPaySelectInfo");
		if (m_C_PaySelection_ID <= 0)
			return;
		
		//  load Banks from PaySelectLine
		loadPaySelectInfo(m_C_PaySelection_ID);
		
		fBank.setText(bank);
		fCurrency.setText(currency);
		fBalance.setValue(balance);
		
		loadPaymentRule();
	}   //  loadPaySelectInfo

	/**
	 *  Bank changed - load PaymentRule
	 */
	private void loadPaymentRule()
	{
		log.info("");
		if (m_C_BankAccount_ID == -1)
			return;
		
		fPaymentRule.removeAllItems();
		
		// load PaymentRule for Bank
		ArrayList<ValueNamePair> data = loadPaymentRule(m_C_PaySelection_ID);
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
		if (fPaymentRule.getSelectedItem() == null)
			return;
		ValueNamePair pp = fPaymentRule.getSelectedItem().toValueNamePair();
		if (pp == null)
			return;
		String PaymentRule = pp.getValue();

		log.info("PaymentRule=" + PaymentRule);
		fNoPayments.setText(" ");
		
		String msg = loadPaymentRuleInfo(m_C_PaySelection_ID, PaymentRule);
		
		if(noPayments != null)
			fNoPayments.setText(noPayments);
		
		bProcess.setEnabled(PaymentRule.equals("T"));
		
		if(documentNo != null)
			fDocumentNo.setValue(documentNo);
		
		if(msg != null && msg.length() > 0)
			FDialog.error(m_WindowNo, form, msg);
	}   //  loadPaymentRuleInfo


	/**************************************************************************
	 *  Export payments to file
	 */
	private void cmd_export()
	{
		if (fPaymentRule.getSelectedItem() == null)
			return;
		String PaymentRule = fPaymentRule.getSelectedItem().toValueNamePair().getValue();
		log.info(PaymentRule);
		if (!getChecks(PaymentRule))
			return;

		try 
		{
			//  Get File Info
			File tempFile = File.createTempFile("paymentExport", ".txt");
	
			//  Create File
			int no = 0;
			StringBuffer err = new StringBuffer("");
			if (m_PaymentExportClass == null || m_PaymentExportClass.trim().length() == 0) {
				m_PaymentExportClass = "org.compiere.util.GenericPaymentExport";
			}
			//	Get Payment Export Class
			PaymentExport custom = null;
			try
			{
				Class<?> clazz = Class.forName(m_PaymentExportClass);
				custom = (PaymentExport)clazz.newInstance();
				no = custom.exportToFile(m_checks, tempFile, err);
			}
			catch (ClassNotFoundException e)
			{
				no = -1;
				err.append("No custom PaymentExport class " + m_PaymentExportClass + " - " + e.toString());
				log.log(Level.SEVERE, err.toString(), e);
			}
			catch (Exception e)
			{
				no = -1;
				err.append("Error in " + m_PaymentExportClass + " check log, " + e.toString());
				log.log(Level.SEVERE, err.toString(), e);
			}
			if (no >= 0) {
				Filedownload.save(new FileInputStream(tempFile), "plain/text", "paymentExport.txt");
				FDialog.info(m_WindowNo, form, "Saved",
						Msg.getMsg(Env.getCtx(), "NoOfLines") + "=" + no);

				if (FDialog.ask(m_WindowNo, form, "VPayPrintSuccess?"))
				{
					//	int lastDocumentNo = 
					MPaySelectionCheck.confirmPrint (m_checks, m_batch);
					//	document No not updated
				}
			} else {
				FDialog.error(m_WindowNo, form, "Error", err.toString());
			}
			dispose();
		}
		catch (Exception e) 
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}   //  cmd_export

	/**
	 *  Create EFT payment
	 */
	private void cmd_EFT()
	{
		if (fPaymentRule.getSelectedItem() == null)
			return;
		String PaymentRule = fPaymentRule.getSelectedItem().toValueNamePair().getValue();
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
		if (fPaymentRule.getSelectedItem() == null)
			return;
		String PaymentRule = fPaymentRule.getSelectedItem().toValueNamePair().getValue();
		log.info(PaymentRule);
		if (!getChecks(PaymentRule))
			return;

		//	for all checks
		List<File> pdfList = new ArrayList<File>();
		for (int i = 0; i < m_checks.length; i++)
		{
			MPaySelectionCheck check = m_checks[i];
			//	ReportCtrl will check BankAccountDoc for PrintFormat
			ReportEngine re = ReportEngine.get(Env.getCtx(), ReportEngine.CHECK, check.get_ID());
			try 
			{
				File file = File.createTempFile("WPayPrint", null);
				re.getPDF(file);
				pdfList.add(file);
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
				return;
			}
		}
		
		SimplePDFViewer chequeViewer = null;
		try 
		{
			File outFile = File.createTempFile("WPayPrint", null);
			AEnv.mergePdf(pdfList, outFile);
			chequeViewer = new SimplePDFViewer(form.getFormName(), new FileInputStream(outFile));
			chequeViewer.setAttribute(Window.MODE_KEY, Window.MODE_EMBEDDED);
			chequeViewer.setWidth("100%");
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			return;
		}

		//	Update BankAccountDoc
		int lastDocumentNo = MPaySelectionCheck.confirmPrint (m_checks, m_batch);
		if (lastDocumentNo != 0)
		{
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE C_BankAccountDoc SET CurrentNext=").append(++lastDocumentNo)
				.append(" WHERE C_BankAccount_ID=").append(m_C_BankAccount_ID)
				.append(" AND PaymentRule='").append(PaymentRule).append("'");
			DB.executeUpdate(sb.toString(), null);
		}

		SimplePDFViewer remitViewer = null; 
		if (FDialog.ask(m_WindowNo, form, "VPayPrintPrintRemittance"))
		{
			pdfList = new ArrayList<File>();
			for (int i = 0; i < m_checks.length; i++)
			{
				MPaySelectionCheck check = m_checks[i];
				ReportEngine re = ReportEngine.get(Env.getCtx(), ReportEngine.REMITTANCE, check.get_ID());
				try 
				{
					File file = File.createTempFile("WPayPrint", null);
					re.getPDF(file);
					pdfList.add(file);
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, e.getLocalizedMessage(), e);
				}
			}
			
			try
			{
				File outFile = File.createTempFile("WPayPrint", null);
				AEnv.mergePdf(pdfList, outFile);
				String name = Msg.translate(Env.getCtx(), "Remittance");
				remitViewer = new SimplePDFViewer(form.getFormName() + " - " + name, new FileInputStream(outFile));
				remitViewer.setAttribute(Window.MODE_KEY, Window.MODE_EMBEDDED);
				remitViewer.setWidth("100%");
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}
		}	//	remittance

		dispose();
		
		if (chequeViewer != null)
			SessionManager.getAppDesktop().showWindow(chequeViewer);
		
		if (remitViewer != null)
			SessionManager.getAppDesktop().showWindow(remitViewer);
	}   //  cmd_print

	
	/**************************************************************************
	 *  Get Checks
	 *  @param PaymentRule Payment Rule
	 *  @return true if payments were created
	 */
	private boolean getChecks(String PaymentRule)
	{
		//  do we have values
		if (m_C_PaySelection_ID <= 0 || m_C_BankAccount_ID == -1
			|| fPaymentRule.getSelectedIndex() == -1 || fDocumentNo.getValue() == null)
		{
			FDialog.error(m_WindowNo, form, "VPayPrintNoRecords",
				"(" + Msg.translate(Env.getCtx(), "C_PaySelectionLine_ID") + "=0)");
			return false;
		}

		//  get data
		int startDocumentNo = ((Number)fDocumentNo.getValue()).intValue();

		log.config("C_PaySelection_ID=" + m_C_PaySelection_ID + ", PaymentRule=" +  PaymentRule + ", DocumentNo=" + startDocumentNo);
		//
		//	get Selections
		m_checks = MPaySelectionCheck.get(m_C_PaySelection_ID, PaymentRule, startDocumentNo, null);

		//
		if (m_checks == null || m_checks.length == 0)
		{
			FDialog.error(m_WindowNo, form, "VPayPrintNoRecords",
				"(" + Msg.translate(Env.getCtx(), "C_PaySelectionLine_ID") + " #0");
			return false;
		}
		m_batch = MPaymentBatch.getForPaySelection (Env.getCtx(), m_C_PaySelection_ID, null);
		return true;
	}   //  getChecks
	
	public ADForm getForm() {
		return form;
	}

	/**
	 *  Vetoable Change Listener.
	 *  - Payment Selection
	 *  @param e event
	 */
	@Override
	public void valueChange(ValueChangeEvent e) {
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		log.config(name + "=" + value);
		if (value == null)
			return;
		
		// Payment Selection
		if (name.equals("C_PaySelection_ID"))
		{
			paySelectSearch.setValue(value);
			m_C_PaySelection_ID = ((Integer)value).intValue();
			loadPaySelectInfo();
		}
	}

}   //  PayPrint
