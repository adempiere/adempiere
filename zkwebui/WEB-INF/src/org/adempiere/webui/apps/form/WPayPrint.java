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

/**
 * 2007, Modified by Posterita Ltd.
 */

package org.adempiere.webui.apps.form;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MLookupInfo;
import org.compiere.model.MPaySelectionCheck;
import org.compiere.model.MPaymentBatch;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Hbox;

public class WPayPrint extends ADForm implements EventListener
{
	/**	Window No			*/
	private int m_WindowNo = 0;

	/**	Used Bank Account	*/
	private int	m_C_BankAccount_ID = -1;

	/** Payment Information */
	private MPaySelectionCheck[]     m_checks = null;
	
	/** Payment Batch		*/
	private MPaymentBatch	m_batch = null; 
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WPayPrint.class);
	
	//  Static Variables
	private Hbox centerPanel = new Hbox();
	private Hbox southPanel = new Hbox();

	private Button bPrint = new Button();
	private Button bExport = new Button();
	private Button bCancel = new Button();
	private Button bProcess = new Button();//(Msg.getMsg(Env.getCtx(), "VPayPrintProcess"));
	private Label lPaySelect = new Label();
	private Listbox fPaySelect = new Listbox();
	private Label lBank = new Label();
	private Label fBank = new Label();
	private Label lPaymentRule = new Label();
	private Listbox fPaymentRule = new Listbox();
	private Label lDocumentNo = new Label();
	private Textbox fDocumentNo = new Textbox();
	private Label lNoPayments = new Label();
	private Label fNoPayments = new Label();
	private Label lBalance = new Label();
	private Textbox fBalance = new Textbox();
	private Label lCurrency = new Label();
	private Label fCurrency = new Label();
	
	public WPayPrint()
	{
		init(super.m_windowNo);
	}
	
	public void init (int WindowNo)
	{
		log.info("");
		m_WindowNo = WindowNo;

		try
		{
			jbInit();
			dynInit();
			
			this.appendChild(centerPanel);
			this.appendChild(southPanel);
			
			fPaySelect.setSelectedIndex(0);
			loadPaySelectInfo();
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	init

	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		fPaySelect.setRows(1);
		fPaySelect.setMold("select");
		
		fPaymentRule.setRows(1);
		fPaymentRule.setMold("select");
		
		bCancel.setImage("/images/Cancel24.gif");
		bPrint.setImage("/images/Print24.gif");
		bExport.setImage("/images/ExportX24.gif");
		
		bPrint.addEventListener(Events.ON_CLICK, this);
		bExport.addEventListener(Events.ON_CLICK, this);
		bCancel.addEventListener(Events.ON_CLICK, this);
		
		bProcess.setLabel(Msg.getMsg(Env.getCtx(), "EFT"));
		bProcess.setEnabled(false);
		bProcess.addEventListener(Events.ON_CLICK, this);
		
		lPaySelect.setValue(Msg.translate(Env.getCtx(), "C_PaySelection_ID"));
		fPaySelect.addEventListener(Events.ON_SELECT, this);
		
		lBank.setValue(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
		
		lPaymentRule.setValue(Msg.translate(Env.getCtx(), "PaymentRule"));
		fPaymentRule.addEventListener(Events.ON_SELECT, this);
		
		lDocumentNo.setValue(Msg.translate(Env.getCtx(), "DocumentNo"));
		lNoPayments.setValue(Msg.getMsg(Env.getCtx(), "NoOfPayments"));
		fNoPayments.setValue("0");
		lBalance.setValue(Msg.translate(Env.getCtx(), "CurrentBalance"));
		fBalance.setEnabled(false);
		lCurrency.setValue(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		
		Hbox boxPaySelect = new Hbox();
		
		fPaySelect.setWidth("100%");
		
		boxPaySelect.setWidth("100%");
		boxPaySelect.setWidths("40%, 60%");
		boxPaySelect.appendChild(lPaySelect);
		boxPaySelect.appendChild(fPaySelect);
		
		Hbox boxBank = new Hbox();
		boxBank.setWidth("100%");
		boxBank.setWidths("40%, 60%");
		boxBank.appendChild(lBank);
		boxBank.appendChild(fBank);
		
		Hbox boxPaymentRule = new Hbox();
		boxPaymentRule.setWidth("100%");
		boxPaymentRule.setWidths("40%, 60%");
		boxPaymentRule.appendChild(lPaymentRule);
		boxPaymentRule.appendChild(fPaymentRule);
		
		Hbox boxDocNo = new Hbox();
		boxDocNo.setWidth("100%");
		boxDocNo.setWidths("40%, 60%");
		boxDocNo.appendChild(lDocumentNo);
		boxDocNo.appendChild(fDocumentNo);
		
		Hbox boxNoPayments = new Hbox();
		boxNoPayments.setWidth("100%");
		boxNoPayments.setWidths("50%, 50%");
		boxNoPayments.appendChild(lNoPayments);
		boxNoPayments.appendChild(fNoPayments);
		
		Hbox boxBalance = new Hbox();
		boxBalance.setWidth("100%");
		boxBalance.setWidths("50%, 50%");
		boxBalance.appendChild(lBalance);
		boxBalance.appendChild(fBalance);

		Hbox boxCurrency = new Hbox();
		boxCurrency.setWidth("100%");
		boxCurrency.setWidths("50%, 50%");
		boxCurrency.appendChild(lCurrency);
		boxCurrency.appendChild(fCurrency);
		
		centerPanel.setWidth("100%");
		centerPanel.setWidths("65%, 35%");
		
		VerticalBox vBox1 = new VerticalBox();
		vBox1.setWidth("100%");
		vBox1.appendChild(boxPaySelect);
		vBox1.appendChild(boxBank);
		vBox1.appendChild(boxPaymentRule);
		vBox1.appendChild(boxDocNo);
		
		VerticalBox vBox2 = new VerticalBox();
		vBox2.setWidth("100%");
		vBox2.appendChild(new Label(""));
		vBox2.appendChild(boxBalance);
		vBox2.appendChild(boxCurrency);
		vBox2.appendChild(boxNoPayments);
		
		centerPanel.appendChild(vBox1);
		centerPanel.appendChild(vBox2);
		
		southPanel.appendChild(bCancel);
		southPanel.appendChild(bExport);
		southPanel.appendChild(bPrint);
		southPanel.appendChild(bProcess);
	}   //  WPayPrint

	/**
	 *  Dynamic Init
	 */
	
	private void dynInit()
	{
		log.config("");
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());

		//  Load PaySelect
		String sql = "SELECT C_PaySelection_ID, Name || ' - ' || TotalAmt FROM C_PaySelection "
			+ "WHERE AD_Client_ID=? AND Processed='Y' AND IsActive='Y'"
			+ "ORDER BY PayDate DESC";
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Client_ID);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				KeyNamePair pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				fPaySelect.appendItem(pp.getName(), pp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		
		if (fPaySelect.getItemCount() == 0)
			FDialog.info(m_WindowNo, this, "VPayPrintNoRecords");
	}   //  dynInit

	/**
	 * 	Set Payment Selection
	 *	@param C_PaySelection_ID id
	 */
	public void setPaySelection (int C_PaySelection_ID)
	{
		if (C_PaySelection_ID == 0)
			return;
		//
		for (int i = 0; i < fPaySelect.getItemCount(); i++)
		{
			ListItem listitem = fPaySelect.getItemAtIndex(i);
			
			KeyNamePair pp = null;
			
			if (listitem != null)
				pp = (KeyNamePair)listitem.getValue();
			
			if (pp.getKey() == C_PaySelection_ID)
			{
				fPaySelect.setSelectedIndex(i);
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
		if (e.getTarget() == fPaySelect)
			loadPaySelectInfo();
		else if (e.getTarget() == fPaymentRule)
			loadPaymentRuleInfo();
		else if (e.getTarget() == bCancel)
			SessionManager.getAppDesktop().removeWindow();
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
		if (fPaySelect.getSelectedIndex() == -1)
			return;

		//  load Banks from PaySelectLine
		
		ListItem listitem = fPaySelect.getSelectedItem();
		
		KeyNamePair key = null;
		
		if (listitem != null)
			key = (KeyNamePair)listitem.getValue();
				
		int C_PaySelection_ID = key.getKey();
		m_C_BankAccount_ID = -1;
		String sql = "SELECT ps.C_BankAccount_ID, b.Name || ' ' || ba.AccountNo,"	//	1..2
			+ " c.ISO_Code, CurrentBalance "										//	3..4
			+ "FROM C_PaySelection ps"
			+ " INNER JOIN C_BankAccount ba ON (ps.C_BankAccount_ID=ba.C_BankAccount_ID)"
			+ " INNER JOIN C_Bank b ON (ba.C_Bank_ID=b.C_Bank_ID)"
			+ " INNER JOIN C_Currency c ON (ba.C_Currency_ID=c.C_Currency_ID) "
			+ "WHERE ps.C_PaySelection_ID=? AND ps.Processed='Y' AND ba.IsActive='Y'";
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_PaySelection_ID);
			ResultSet rs = pstmt.executeQuery();
		
			if (rs.next())
			{
				m_C_BankAccount_ID = rs.getInt(1);
				fBank.setValue(rs.getString(2));
				fCurrency.setValue(rs.getString(3));
				fBalance.setValue(rs.getBigDecimal(4).toString());
			}
			else
			{
				m_C_BankAccount_ID = -1;
				fBank.setValue("");
				fCurrency.setValue("");
				fBalance.setValue("0");
				log.log(Level.SEVERE, "No active BankAccount for C_PaySelection_ID=" + C_PaySelection_ID);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		
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

		// load PaymentRule for Bank
		
		ListItem listitem = fPaySelect.getSelectedItem();
		
		KeyNamePair kp = null;
		
		if (listitem != null)
			kp = (KeyNamePair)listitem.getValue();
		else
			return;
		
		int C_PaySelection_ID = kp.getKey();
		fPaymentRule.getChildren().clear();
		int AD_Reference_ID = 195;  //  MLookupInfo.getAD_Reference_ID("All_Payment Rule");
		Language language = Language.getLanguage(Env.getAD_Language(Env.getCtx()));
		MLookupInfo info = MLookupFactory.getLookup_List(language, AD_Reference_ID);
		String sql = info.Query.substring(0, info.Query.indexOf(" ORDER BY"))
			+ " AND " + info.KeyColumn
			+ " IN (SELECT PaymentRule FROM C_PaySelectionCheck WHERE C_PaySelection_ID=?) "
			+ info.Query.substring(info.Query.indexOf(" ORDER BY"));
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_PaySelection_ID);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				ValueNamePair pp = new ValueNamePair(rs.getString(2), rs.getString(3));
				fPaymentRule.appendItem(pp.getName(), pp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		
		if (fPaymentRule.getItemCount() == 0)
			log.config("PaySel=" + C_PaySelection_ID + ", BAcct=" + m_C_BankAccount_ID + " - " + sql);
		
		fPaymentRule.setSelectedIndex(0);
		
		loadPaymentRuleInfo();
	}   //  loadPaymentRule

	/**
	 *  PaymentRule changed - load DocumentNo, NoPayments,
	 *  enable/disable EFT, Print
	 */
	
	private void loadPaymentRuleInfo()
	{
		ListItem listitem = fPaymentRule.getSelectedItem();
		
		ValueNamePair pp = null;
		
		if (listitem != null)
			pp = (ValueNamePair)listitem.getValue();
		
		if (pp == null)
			return;

		String PaymentRule = pp.getValue();

		log.info("PaymentRule=" + PaymentRule);
		fNoPayments.setValue(" ");

		listitem = fPaySelect.getSelectedItem();
		
		KeyNamePair kp = null;
		
		if (listitem != null)
			kp = (KeyNamePair)listitem.getValue();
		
		int C_PaySelection_ID = kp.getKey();
		
		String sql = "SELECT COUNT(*) "
			+ "FROM C_PaySelectionCheck "
			+ "WHERE C_PaySelection_ID=?";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_PaySelection_ID);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
				fNoPayments.setValue(String.valueOf(rs.getInt(1)));
			
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		
		bProcess.setEnabled(PaymentRule.equals("T"));

		//  DocumentNo
		sql = "SELECT CurrentNext "
			+ "FROM C_BankAccountDoc "
			+ "WHERE C_BankAccount_ID=? AND PaymentRule=? AND IsActive='Y'";
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_C_BankAccount_ID);
			pstmt.setString(2, PaymentRule);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
				fDocumentNo.setValue(new Integer(rs.getInt(1)).toString());
			else
			{
				log.log(Level.SEVERE, "VPayPrint.loadPaymentRuleInfo - No active BankAccountDoc for C_BankAccount_ID="
					+ m_C_BankAccount_ID + " AND PaymentRule=" + PaymentRule);
				FDialog.error(m_WindowNo, this, "VPayPrintNoDoc");
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
	}   //  loadPaymentRuleInfo

	/**************************************************************************
	 *  Export payments to file
	 */
	
	private void cmd_export()
	{
		ListItem listitem = fPaymentRule.getSelectedItem();
		
		ValueNamePair vp = null;
		
		if (listitem != null)
			vp = (ValueNamePair)listitem.getValue();
		
		String PaymentRule = null;
		
		if (vp != null)
			PaymentRule = vp.getValue();
			
		log.info(PaymentRule);
		
		if (!getChecks(PaymentRule))
			return;

		File file = null;
		FileInputStream fstream = null;
		int no = -1;
		
		try
		{
			file = File.createTempFile("temp", "txt");
			no = MPaySelectionCheck.exportToFile(m_checks, file);
			fstream = new FileInputStream(file);
		}
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}

		Filedownload.save(fstream, "", file.getAbsolutePath());
	
		//  Create File
		
		FDialog.info(m_WindowNo, this, "Saved",
			file.getAbsolutePath() + "\n"
			+ Msg.getMsg(Env.getCtx(), "NoOfLines") + "=" + no);

		if (FDialog.ask(m_WindowNo, this, "VPayPrintSuccess?"))
		{
			//	int lastDocumentNo = 
			MPaySelectionCheck.confirmPrint (m_checks, m_batch);
			//	document No not updated
		}
		
		SessionManager.getAppDesktop().removeWindow();
	}   //  cmd_export

	/**
	 *  Create EFT payment
	 */
	
	private void cmd_EFT()
	{
		ListItem listitem = fPaymentRule.getSelectedItem();
		
		ValueNamePair vp = null;
		
		if (listitem != null)
			vp = (ValueNamePair)listitem.getValue();
		
		String PaymentRule = vp.getValue();
		log.info(PaymentRule);
		
		if (!getChecks(PaymentRule))
			return;
		
		SessionManager.getAppDesktop().removeWindow();
	}   //  cmd_EFT

	/**
	 *  Print Checks and/or Remittance
	 */
	
	private void cmd_print()
	{
		ListItem listitem = fPaymentRule.getSelectedItem();
		
		ValueNamePair vp = null;
		
		if (listitem != null)
			vp = (ValueNamePair)listitem.getValue();
		
		String PaymentRule = vp.getValue();
		log.info(PaymentRule);
		
		if (!getChecks(PaymentRule))
			return;

		boolean somethingPrinted = false;
		boolean directPrint = !Ini.isPropertyBool(Ini.P_PRINTPREVIEW);

		//	for all checks
		for (int i = 0; i < m_checks.length; i++)
		{
			MPaySelectionCheck check = m_checks[i];
			//	ReportCtrl will check BankAccountDoc for PrintFormat
			boolean ok = ReportCtl.startDocumentPrint(ReportEngine.CHECK, check.get_ID(), null, super.m_windowNo, directPrint);
			if (!somethingPrinted && ok)
				somethingPrinted = true;
		}

		//	Confirm Print and Update BankAccountDoc
		if (somethingPrinted && FDialog.ask(m_WindowNo, this, "VPayPrintSuccess?"))
		{
			int lastDocumentNo = MPaySelectionCheck.confirmPrint (m_checks, m_batch);
			if (lastDocumentNo != 0)
			{
				StringBuffer sb = new StringBuffer();
				sb.append("UPDATE C_BankAccountDoc SET CurrentNext=").append(++lastDocumentNo)
					.append(" WHERE C_BankAccount_ID=").append(m_C_BankAccount_ID)
					.append(" AND PaymentRule='").append(PaymentRule).append("'");
				DB.executeUpdate(sb.toString(), null);
			}
		}	//	confirm

		if (FDialog.ask(m_WindowNo, this, "VPayPrintPrintRemittance"))
		{
			for (int i = 0; i < m_checks.length; i++)
			{
				MPaySelectionCheck check = m_checks[i];
				ReportCtl.startDocumentPrint(ReportEngine.REMITTANCE, check.get_ID(), null, super.m_windowNo, directPrint);
			}
		}	//	remittance

		SessionManager.getAppDesktop().removeWindow();
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
			FDialog.error(m_WindowNo, this, "VPayPrintNoRecords",
				"(" + Msg.translate(Env.getCtx(), "C_PaySelectionLine_ID") + "=0)");
			
			return false;
		}

		//  get data
		
		ListItem listitem = fPaySelect.getSelectedItem();
		
		KeyNamePair kp = null;
		
		if (listitem != null)
			kp = (KeyNamePair)listitem.getValue();
		
		int C_PaySelection_ID = kp.getKey();
		
		int startDocumentNo = new Integer(fDocumentNo.getValue());

		log.config("C_PaySelection_ID=" + C_PaySelection_ID + ", PaymentRule=" +  PaymentRule + ", DocumentNo=" + startDocumentNo);
		
		//	get Selections
		m_checks = MPaySelectionCheck.get(C_PaySelection_ID, PaymentRule, startDocumentNo, null);

		if (m_checks == null || m_checks.length == 0)
		{
			FDialog.error(m_WindowNo, this, "VPayPrintNoRecords",
				"(" + Msg.translate(Env.getCtx(), "C_PaySelectionLine_ID") + " #0");
			return false;
		}
		m_batch = MPaymentBatch.getForPaySelection (Env.getCtx(), C_PaySelection_ID, null);
		
		return true;
	}   //  getChecks
}
