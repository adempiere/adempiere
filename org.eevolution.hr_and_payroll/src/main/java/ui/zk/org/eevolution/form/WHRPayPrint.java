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
package org.eevolution.form;

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
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.adempiere.webui.window.SimplePDFViewer;
import org.compiere.print.ReportEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.eevolution.util.HRPaymentExport;
import org.eevolution.model.MHRPaySelection;
import org.eevolution.model.MHRPaySelectionCheck;
import org.eevolution.service.HRPayPrint;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.South;
import org.zkoss.zkmax.zul.Filedownload;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *  Payment Print & Export
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VPayPrint.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 
 *  Contributors:
 *    Carlos Ruiz - GlobalQSS - FR 3132033 - Make payment export class configurable per bank
 */
public class WHRPayPrint extends HRPayPrint implements IFormController, EventListener
{
	private CustomForm form = new CustomForm();

	/**
	 *	Initialize Panel
	 */
	public WHRPayPrint()
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
	private Listbox fPaySelect = ListboxFactory.newDropdownListbox();
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
		lPaySelect.setText(Msg.translate(Env.getCtx(), "HR_PaySelection_ID"));
		fPaySelect.addActionListener(this);
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
		row.appendChild(fPaySelect);
		
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
		ArrayList<KeyNamePair> data = getPaySelectionData();
		for(KeyNamePair pp : data)
			fPaySelect.addItem(pp);
		
		if (fPaySelect.getItemCount() == 0)
			FDialog.info(m_WindowNo, form, "VPayPrintNoRecords");
		else
		{
			fPaySelect.setSelectedIndex(0);
			loadPaySelectInfo();
		}
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
	 *	@param HR_PaySelection_ID id
	 */
	public void setPaySelection (int HR_PaySelection_ID)
	{
		if (HR_PaySelection_ID == 0)
			return;
		//
		for (int i = 0; i < fPaySelect.getItemCount(); i++)
		{
			KeyNamePair pp = fPaySelect.getItemAtIndex(i).toKeyNamePair();
			if (pp.getKey() == HR_PaySelection_ID)
			{
				fPaySelect.setSelectedIndex(i);
				loadPaySelectInfo();
				return;
			}
		}
	}	//	setsetPaySelection

	
	/**************************************************************************
	 *  Action Listener
	 *  @param e event
	 */
	public void onEvent(Event e)
	{
	//	log.config( "HRVPayPrint.actionPerformed" + e.toString());
		if (e.getTarget() == fPaySelect)
			loadPaySelectInfo();
		else if (e.getTarget() == fPaymentRule)
			loadPaymentRuleInfo();
		//
		else if (e.getTarget() == bCancel)
			dispose();
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
		log.info( "HRPayPrint.loadPaySelectInfo");
		if (fPaySelect.getSelectedIndex() == -1)
			return;
		
		//  load Banks from PaySelectLine
		int HR_PaySelection_ID = fPaySelect.getSelectedItem().toKeyNamePair().getKey();
		loadPaySelectInfo(HR_PaySelection_ID);
		
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
		int HR_PaySelection_ID = fPaySelect.getSelectedItem().toKeyNamePair().getKey();
		ArrayList<ValueNamePair> data = loadPaymentRule(HR_PaySelection_ID);
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
		if(fPaymentRule.getSelectedItem()==null)
			return;
		
		ValueNamePair pp = fPaymentRule.getSelectedItem().toValueNamePair();
		if (pp == null)
			return;
		String PaymentRule = pp.getValue();

		log.info("PaymentRule=" + PaymentRule);
		fNoPayments.setText(" ");
		
		int HR_PaySelection_ID = fPaySelect.getSelectedItem().toKeyNamePair().getKey();
		String msg = loadPaymentRuleInfo(HR_PaySelection_ID, PaymentRule);
		
		if(noPayments != null)
			fNoPayments.setText(noPayments);
		
		bProcess.setEnabled(PaymentRule.equals("T"));

        if(documentNo != null)
            fDocumentNo.setValue(documentNo);

        if(documentNo != null)
        {
            MHRPaySelection hrps = new MHRPaySelection(Env.getCtx(), HR_PaySelection_ID, null);
            if( hrps.get_ValueAsInt("CheckNo") > 0 )
                fDocumentNo.setValue(hrps.get_ValueAsInt("CheckNo"));
            else
                fDocumentNo.setValue(documentNo);
        }

		
		
		if(msg != null && msg.length() > 0)
			FDialog.error(m_WindowNo, form, msg);
	}   //  loadPaymentRuleInfo


	/**************************************************************************
	 *  Export payments to file
	 */
	private void cmd_export()
	{
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
				m_PaymentExportClass = "org.eevolution.util.HRGenericPaymentExport";
			}
			//	Get Payment Export Class
			HRPaymentExport custom = null;
			try
			{
				Class<?> clazz = Class.forName(m_PaymentExportClass);
				custom = (HRPaymentExport)clazz.newInstance();
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
					MHRPaySelectionCheck.confirmPrint(m_checks, m_batch);
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
		for (MHRPaySelectionCheck check : m_checks)
		{
			//	ReportCtrl will check BankAccountDoc for PrintFormat
			ReportEngine re = ReportEngine.get(Env.getCtx(), ReportEngine.HR_CHECK, check.get_ID());
			try 
			{
				File file = File.createTempFile("WHRPayPrint", null);
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
			File outFile = File.createTempFile("WHRPayPrint", null);
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
		int lastDocumentNo = MHRPaySelectionCheck.confirmPrint(m_checks, m_batch);
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
			for (MHRPaySelectionCheck check : m_checks)
			{
				ReportEngine re = ReportEngine.get(Env.getCtx(), ReportEngine.HR_REMITTANCE, check.get_ID());
				try 
				{
					File file = File.createTempFile("WHRPayPrint", null);
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
				File outFile = File.createTempFile("WHRPayPrint", null);
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
		}

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
		if (fPaySelect.getSelectedIndex() == -1 || m_C_BankAccount_ID == -1
			|| fPaymentRule.getSelectedIndex() == -1 || fDocumentNo.getValue() == null)
		{
			FDialog.error(m_WindowNo, form, "VPayPrintNoRecords",
                    "(" + Msg.translate(Env.getCtx(), "HR_PaySelectionLine_ID") + "=0)");
			return false;
		}

		//  get data
		int HR_PaySelection_ID = fPaySelect.getSelectedItem().toKeyNamePair().getKey();
		int startDocumentNo = ((Number)fDocumentNo.getValue()).intValue();

		log.config("HR_PaySelection_ID=" + HR_PaySelection_ID + ", PaymentRule=" +  PaymentRule + ", DocumentNo=" + startDocumentNo);
		//
		//	get Slecetions
		m_checks = MHRPaySelectionCheck.get(Env.getCtx(), HR_PaySelection_ID, PaymentRule, startDocumentNo, null);

		//
		if (m_checks == null || m_checks.size() == 0)
		{
			FDialog.error(m_WindowNo, form, "VPayPrintNoRecords",
                    "(" + Msg.translate(Env.getCtx(), "C_PaySelectionLine_ID") + " #0");
			return false;
		}
		//m_batch = MHRPaymentBatch.getForPaySelection (Env.getCtx(), HR_PaySelection_ID, null);
		return true;
	}   //  getChecks
	
	public ADForm getForm() {
		return form;
	}

}   //  PayPrint
