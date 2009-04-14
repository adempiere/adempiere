/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
 * Contributors:                                                              *
 * Colin Rooney (croo) Patch 1605368 Fixed Payment Terms & Only due           *
 *****************************************************************************/
package org.adempiere.webui.apps.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.webui.apps.ProcessModalDialog;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MLookupInfo;
import org.compiere.model.MPaySelection;
import org.compiere.model.MPaySelectionLine;
import org.compiere.model.MRole;
import org.compiere.model.X_C_Order;
import org.compiere.model.X_C_PaySelection;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.au.out.AuEcho;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Space;

/**
 *  Create Manual Payments From (AP) Invoices or (AR) Credit Memos.
 *  Allows user to select Invoices for payment.
 *  When Processed, PaySelection is created
 *  and optionally posted/generated and printed
 *
 *  @author Jorg Janke
 *  @version $Id: VPaySelect.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 */
public class WPaySelect extends ADForm
	implements EventListener, WTableModelListener, ASyncProcess
{
	/** @todo withholding */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6031404894392912610L;

	/**
	 *	Initialize Panel
	 */
	protected void initForm()
	{
		try
		{
			zkInit();
			dynInit();
			southPanel.appendChild(new Separator());
			southPanel.appendChild(commandPanel);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	init

	/** Format                  */
	private DecimalFormat   m_format = DisplayType.getNumberFormat(DisplayType.Amount);
	/** Bank Balance            */
	private BigDecimal      m_bankBalance = new BigDecimal(0.0);
	/** SQL for Query           */
	private String          m_sql;
	/** Number of selected rows */
	private int             m_noSelected = 0;
	/** Client ID               */
	private int             m_AD_Client_ID = 0;
	/** Payment Selection		*/
	private MPaySelection	m_ps = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WPaySelect.class);

	//
	private Panel mainPanel = new Panel();
	private Borderlayout mainLayout = new Borderlayout();
	private Panel parameterPanel = new Panel();
	private Label labelBankAccount = new Label();
	private Listbox fieldBankAccount = ListboxFactory.newDropdownListbox();
	private Grid parameterLayout = GridFactory.newGridLayout();
	private Label labelBankBalance = new Label();
	private Label labelCurrency = new Label();
	private Label labelBalance = new Label();
	private Checkbox onlyDue = new Checkbox();
	private Label labelBPartner = new Label();
	private Listbox fieldBPartner = ListboxFactory.newDropdownListbox();
	private Label dataStatus = new Label();
	private WListbox miniTable = ListboxFactory.newDataTable();
	private ConfirmPanel commandPanel = new ConfirmPanel(true, false, false, false, false, false, false);
	private Button bCancel = commandPanel.getButton(ConfirmPanel.A_CANCEL);
	private Button bGenerate = commandPanel.createButton(ConfirmPanel.A_PROCESS);
	private Button bRefresh = commandPanel.createButton(ConfirmPanel.A_REFRESH);
	private Label labelPayDate = new Label();
	private WDateEditor fieldPayDate = new WDateEditor();
	private Label labelPaymentRule = new Label();
	private Listbox fieldPaymentRule = ListboxFactory.newDropdownListbox();
	private Panel southPanel;
	private ProcessInfo m_pi;
	private boolean m_isLock;

	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void zkInit() throws Exception
	{
		//
		this.appendChild(mainPanel);
		mainPanel.appendChild(mainLayout);
		mainPanel.setStyle("width: 100%; height: 100%; padding: 0; margin: 0");
		mainLayout.setHeight("100%");
		mainLayout.setWidth("99%");
		parameterPanel.appendChild(parameterLayout);
		//
		labelBankAccount.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
		fieldBankAccount.addActionListener(this);
		labelBPartner.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		fieldBPartner.addActionListener(this);
		bRefresh.addActionListener(this);
		labelPayDate.setText(Msg.translate(Env.getCtx(), "PayDate"));
		labelPaymentRule.setText(Msg.translate(Env.getCtx(), "PaymentRule"));
		fieldPaymentRule.addActionListener(this);
		//
		labelBankBalance.setText(Msg.translate(Env.getCtx(), "CurrentBalance"));
		labelBalance.setText("0");
		onlyDue.setText(Msg.getMsg(Env.getCtx(), "OnlyDue"));
		dataStatus.setText(" ");
		dataStatus.setPre(true);
		//
		bGenerate.addActionListener(this);
		bCancel.addActionListener(this);
		//
		North north = new North();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(parameterPanel);
		
		Rows rows = parameterLayout.newRows();
		Row row = rows.newRow();
		row.appendChild(labelBankAccount.rightAlign());
		row.appendChild(fieldBankAccount);
		row.appendChild(labelBankBalance.rightAlign());
		Panel balancePanel = new Panel();
		balancePanel.appendChild(labelCurrency);
		balancePanel.appendChild(labelBalance);
		row.appendChild(balancePanel);
		row.appendChild(new Space());
		
		row = rows.newRow();
		row.appendChild(labelBPartner.rightAlign());
		row.appendChild(fieldBPartner);
		row.appendChild(new Space());
		row.appendChild(onlyDue);
		row.appendChild(new Space());
		
		row = rows.newRow();
		row.appendChild(labelPayDate.rightAlign());
		row.appendChild(fieldPayDate.getComponent());
		row.appendChild(labelPaymentRule.rightAlign());
		row.appendChild(fieldPaymentRule);
		row.appendChild(bRefresh);
		
		South south = new South();
		south.setStyle("border: none");
		mainLayout.appendChild(south);
		southPanel = new Panel();
		southPanel.appendChild(dataStatus);
		south.appendChild(southPanel);
		Center center = new Center();
		mainLayout.appendChild(center);
		center.appendChild(miniTable);
		//
		commandPanel.addButton(bGenerate);
	}   //  jbInit

	/**
	 *  Dynamic Init.
	 *  - Load Bank Info
	 *  - Load BPartner
	 *  - Init Table
	 */
	private void dynInit()
	{
		Properties ctx = Env.getCtx();

		//  Bank Account Info
		String sql = MRole.getDefault().addAccessSQL(
			"SELECT ba.C_BankAccount_ID,"                       //  1
			+ "b.Name || ' ' || ba.AccountNo AS Name,"          //  2
			+ "ba.C_Currency_ID, c.ISO_Code,"                   //  3..4
			+ "ba.CurrentBalance "                              //  5
			+ "FROM C_Bank b, C_BankAccount ba, C_Currency c "
			+ "WHERE b.C_Bank_ID=ba.C_Bank_ID"
			+ " AND ba.C_Currency_ID=c.C_Currency_ID "
			+ " AND EXISTS (SELECT * FROM C_BankAccountDoc d WHERE d.C_BankAccount_ID=ba.C_BankAccount_ID) "
			+ "ORDER BY 2",
			"b", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RW);
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				boolean transfers = false;
				BankInfo bi = new BankInfo (rs.getInt(1), rs.getInt(3),
					rs.getString(2), rs.getString(4),
					rs.getBigDecimal(5), transfers);
				fieldBankAccount.appendItem(bi.toString(), bi);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		if (fieldBankAccount.getItemCount() == 0)
			FDialog.error(m_WindowNo, this, "VPaySelectNoBank");
		else
			fieldBankAccount.setSelectedIndex(0);
		loadBankInfo();

		//  Optional BusinessPartner with unpaid AP Invoices
		KeyNamePair pp = new KeyNamePair(0, "");
		fieldBPartner.addItem(pp);
		sql = MRole.getDefault().addAccessSQL(
			"SELECT bp.C_BPartner_ID, bp.Name FROM C_BPartner bp", "bp", 
			MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO)
			+ " AND EXISTS (SELECT * FROM C_Invoice i WHERE bp.C_BPartner_ID=i.C_BPartner_ID"
			//	X_C_Order.PAYMENTRULE_DirectDebit
			  + " AND (i.IsSOTrx='N' OR (i.IsSOTrx='Y' AND i.PaymentRule='D'))"
			  + " AND i.IsPaid<>'Y') "
			+ "ORDER BY 2";

		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				fieldBPartner.addItem(pp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		fieldBPartner.setSelectedIndex(0);

		/**  prepare MiniTable
		 *
		SELECT i.C_Invoice_ID, i.DateInvoiced+p.NetDays AS DateDue,
		bp.Name, i.DocumentNo, c.ISO_Code, i.GrandTotal,
		paymentTermDiscount(i.GrandTotal, i.C_PaymentTerm_ID, i.DateInvoiced, SysDate) AS Discount,
		SysDate-paymentTermDueDays(i.C_PaymentTerm_ID,i.DateInvoiced) AS DiscountDate,
		i.GrandTotal-paymentTermDiscount(i.GrandTotal,i.C_PaymentTerm_ID,i.DateInvoiced,SysDate) AS DueAmount,
		currencyConvert(i.GrandTotal-paymentTermDiscount(i.GrandTotal,i.C_PaymentTerm_ID,i.DateInvoiced,SysDate,null),
			i.C_Currency_ID,xx100,SysDate) AS PayAmt
		FROM C_Invoice i, C_BPartner bp, C_Currency c, C_PaymentTerm p
		WHERE i.IsSOTrx='N'
		AND i.C_BPartner_ID=bp.C_BPartner_ID
		AND i.C_Currency_ID=c.C_Currency_ID
		AND i.C_PaymentTerm_ID=p.C_PaymentTerm_ID
		AND i.DocStatus IN ('CO','CL')
		ORDER BY 2,3
		 */

		m_sql = miniTable.prepareTable(new ColumnInfo[] {
			//  0..4
			new ColumnInfo(" ", "i.C_Invoice_ID", IDColumn.class, false, false, null),
			new ColumnInfo(Msg.translate(ctx, "DueDate"), "paymentTermDueDate(i.C_PaymentTerm_ID, i.DateInvoiced) AS DateDue", Timestamp.class, true, true, null),
			new ColumnInfo(Msg.translate(ctx, "C_BPartner_ID"), "bp.Name", KeyNamePair.class, true, false, "i.C_BPartner_ID"),
			new ColumnInfo(Msg.translate(ctx, "DocumentNo"), "i.DocumentNo", String.class),
			new ColumnInfo(Msg.translate(ctx, "C_Currency_ID"), "c.ISO_Code", KeyNamePair.class, true, false, "i.C_Currency_ID"),
			// 5..9
			new ColumnInfo(Msg.translate(ctx, "GrandTotal"), "i.GrandTotal", BigDecimal.class),
			new ColumnInfo(Msg.translate(ctx, "DiscountAmt"), "paymentTermDiscount(i.GrandTotal,i.C_Currency_ID,i.C_PaymentTerm_ID,i.DateInvoiced, ?)", BigDecimal.class),
			new ColumnInfo(Msg.getMsg(ctx, "DiscountDate"), "SysDate-paymentTermDueDays(i.C_PaymentTerm_ID,i.DateInvoiced,SysDate)", Timestamp.class),
			new ColumnInfo(Msg.getMsg(ctx, "AmountDue"), "currencyConvert(invoiceOpen(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID),i.C_Currency_ID, ?,?,i.C_ConversionType_ID, i.AD_Client_ID,i.AD_Org_ID)", BigDecimal.class),
			new ColumnInfo(Msg.getMsg(ctx, "AmountPay"), "currencyConvert(invoiceOpen(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID)-paymentTermDiscount(i.GrandTotal,i.C_Currency_ID,i.C_PaymentTerm_ID,i.DateInvoiced, ?),i.C_Currency_ID, ?,?,i.C_ConversionType_ID, i.AD_Client_ID,i.AD_Org_ID)", BigDecimal.class)
			},
			//	FROM
			"C_Invoice_v i"
			+ " INNER JOIN C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID)"
			+ " INNER JOIN C_Currency c ON (i.C_Currency_ID=c.C_Currency_ID)"
			+ " INNER JOIN C_PaymentTerm p ON (i.C_PaymentTerm_ID=p.C_PaymentTerm_ID)",
			//	WHERE
			"i.IsSOTrx=? AND IsPaid='N'"
			//	Different Payment Selection 
			+ " AND NOT EXISTS (SELECT * FROM C_PaySelectionLine psl"
				+ " WHERE i.C_Invoice_ID=psl.C_Invoice_ID AND psl.C_PaySelectionCheck_ID IS NOT NULL)"
			+ " AND i.DocStatus IN ('CO','CL')"
			+ " AND i.AD_Client_ID=?",	//	additional where & order in loadTableInfo() 
			true, "i");
		//
		miniTable.getModel().addTableModelListener(this);
		//
		fieldPayDate.setMandatory(true);
		fieldPayDate.setValue(new Timestamp(System.currentTimeMillis()));
		//
		m_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
	}   //  dynInit

	/**
	 *  Load Bank Info - Load Info from Bank Account and valid Documents (PaymentRule)
	 */
	private void loadBankInfo()
	{
		BankInfo bi = (BankInfo)fieldBankAccount.getSelectedItem().getValue();
		if (bi == null)
			return;
		labelCurrency.setText(bi.Currency);
		labelBalance.setText(m_format.format(bi.Balance));
		m_bankBalance = bi.Balance;

		//  PaymentRule
		fieldPaymentRule.removeAllItems();
		int AD_Reference_ID = 195;  //  MLookupInfo.getAD_Reference_ID("All_Payment Rule");
		Language language = Env.getLanguage(Env.getCtx());
		MLookupInfo info = MLookupFactory.getLookup_List(language, AD_Reference_ID);
		String sql = info.Query.substring(0, info.Query.indexOf(" ORDER BY"))
			+ " AND " + info.KeyColumn
			+ " IN (SELECT PaymentRule FROM C_BankAccountDoc WHERE C_BankAccount_ID=?) "
			+ info.Query.substring(info.Query.indexOf(" ORDER BY"));
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, bi.C_BankAccount_ID);
			ResultSet rs = pstmt.executeQuery();
			ValueNamePair vp = null;
			while (rs.next())
			{
				vp = new ValueNamePair(rs.getString(2), rs.getString(3));   //  returns also not active
				fieldPaymentRule.addItem(vp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		fieldPaymentRule.setSelectedIndex(0);

	}   //  loadBankInfo

	/**
	 *  Query and create TableInfo
	 */
	private void loadTableInfo()
	{
		log.config("");
		//  not yet initialized
		if (m_sql == null)
			return;

		String sql = m_sql;
		//  Parameters
		Timestamp payDate = (Timestamp)fieldPayDate.getValue();
		miniTable.setColorCompare(payDate);
		log.config("PayDate=" + payDate);
		BankInfo bi = (BankInfo)fieldBankAccount.getSelectedItem().getValue();
		//
		String isSOTrx = "N";
		ValueNamePair vp = (ValueNamePair)fieldPaymentRule.getSelectedItem().toValueNamePair();
		if (vp != null && X_C_Order.PAYMENTRULE_DirectDebit.equals(vp.getValue()))
		{
			isSOTrx = "Y";
			sql += " AND i.PaymentRule='" + X_C_Order.PAYMENTRULE_DirectDebit + "'";
		}
		//
		if (onlyDue.isSelected())
			sql += " AND paymentTermDueDate(i.C_PaymentTerm_ID, i.DateInvoiced) <= ?";
		//
		KeyNamePair pp = (KeyNamePair)fieldBPartner.getSelectedItem().toKeyNamePair();
		int C_BPartner_ID = pp.getKey();
		if (C_BPartner_ID != 0)
			sql += " AND i.C_BPartner_ID=?";
		sql += " ORDER BY 2,3";
		//
		log.finest(sql + " - C_Currecny_ID=" + bi.C_Currency_ID + ", C_BPartner_ID=" + C_BPartner_ID);
		
		//  Get Open Invoices
		try
		{
			int index = 1;
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setTimestamp(index++, payDate);		//	DiscountAmt
			pstmt.setInt(index++, bi.C_Currency_ID);	//	DueAmt
			pstmt.setTimestamp(index++, payDate);		
			pstmt.setTimestamp(index++, payDate);		//	PayAmt
			pstmt.setInt(index++, bi.C_Currency_ID);
			pstmt.setTimestamp(index++, payDate);
			pstmt.setString(index++, isSOTrx);			//	IsSOTrx	
			pstmt.setInt(index++, m_AD_Client_ID);		//	Client	
			if (onlyDue.isSelected())
				pstmt.setTimestamp(index++, payDate);
			if (C_BPartner_ID != 0)
				pstmt.setInt(index++, C_BPartner_ID);
			//
			ResultSet rs = pstmt.executeQuery();
			miniTable.loadTable(rs);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		calculateSelection();
	}   //  loadTableInfo

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		SessionManager.getAppDesktop().closeActiveWindow();
	}	//	dispose

	
	/**************************************************************************
	 *  ActionListener
	 *  @param e event
	 */
	public void onEvent (Event e)
	{
		//  Update Bank Info
		if (e.getTarget() == fieldBankAccount)
			loadBankInfo();

		//  Generate PaySelection
		else if (e.getTarget() == bGenerate)
		{
			generatePaySelect();
		}

		else if (e.getTarget() == bCancel)
			dispose();

		//  Update Open Invoices
		else if (e.getTarget() == fieldBPartner || e.getTarget() == bRefresh)
			loadTableInfo();

	}   //  actionPerformed

	/**
	 *  Table Model Listener
	 *  @param e event
	 */
	public void tableChanged(WTableModelEvent e)
	{
		if (e.getColumn() == 0)
			calculateSelection();
	}   //  valueChanged

	/**
	 *  Calculate selected rows.
	 *  - add up selected rows
	 */
	public void calculateSelection()
	{
		m_noSelected = 0;
		BigDecimal invoiceAmt = new BigDecimal(0.0);

		int rows = miniTable.getRowCount();
		for (int i = 0; i < rows; i++)
		{
			IDColumn id = (IDColumn)miniTable.getModel().getValueAt(i, 0);
			if (id.isSelected())
			{
				BigDecimal amt = (BigDecimal)miniTable.getModel().getValueAt(i, 9);
				if (amt != null)
					invoiceAmt = invoiceAmt.add(amt);
				m_noSelected++;
			}
		}

		//  Information
		BigDecimal remaining = m_bankBalance.subtract(invoiceAmt);
		StringBuffer info = new StringBuffer();
		info.append(m_noSelected).append(" ").append(Msg.getMsg(Env.getCtx(), "Selected")).append(" - ");
		info.append(m_format.format(invoiceAmt)).append(", ");
		info.append(Msg.getMsg(Env.getCtx(), "Remaining")).append(" ").append(m_format.format(remaining));
		dataStatus.setText(info.toString());
		//
		bGenerate.setEnabled(m_noSelected != 0);
	}   //  calculateSelection

	/**
	 *  Generate PaySelection
	 */
	private void generatePaySelect()
	{
		log.info("");
	//	String trxName Trx.createTrxName("PaySelect");
	//	Trx trx = Trx.get(trxName, true);	trx needs to be committed too
		String trxName = null;
		Trx trx = null;
		//
		if (miniTable.getRowCount() == 0)
			return;
		miniTable.setSelectedIndices(new int[]{0});
		calculateSelection();
		if (m_noSelected == 0)
			return;

		String PaymentRule = ((ValueNamePair)fieldPaymentRule.getSelectedItem().toValueNamePair()).getValue();

		//  Create Header
		m_ps = new MPaySelection(Env.getCtx(), 0, trxName);
		m_ps.setName (Msg.getMsg(Env.getCtx(), "VPaySelect")
				+ " - " + ((ValueNamePair)fieldPaymentRule.getSelectedItem().toValueNamePair()).getName()
				+ " - " + fieldPayDate.getValue());
		m_ps.setPayDate (new Timestamp(fieldPayDate.getComponent().getValue().getTime()));
		BankInfo bi = (BankInfo)fieldBankAccount.getSelectedItem().getValue();
		m_ps.setC_BankAccount_ID(bi.C_BankAccount_ID);
		m_ps.setIsApproved(true);
		if (!m_ps.save())
		{
			FDialog.error(m_WindowNo, this, "SaveError", Msg.translate(Env.getCtx(), "C_PaySelection_ID"));
			m_ps = null;
			return;
		}
		log.config(m_ps.toString());

		//  Create Lines
		int rows = miniTable.getRowCount();
		int line = 0;
		for (int i = 0; i < rows; i++)
		{
			IDColumn id = (IDColumn)miniTable.getModel().getValueAt(i, 0);
			if (id.isSelected())
			{
				line += 10;
				MPaySelectionLine psl = new MPaySelectionLine (m_ps, line, PaymentRule);
				int C_Invoice_ID = id.getRecord_ID().intValue();
				BigDecimal OpenAmt = (BigDecimal)miniTable.getModel().getValueAt(i, 8);
				BigDecimal PayAmt = (BigDecimal)miniTable.getModel().getValueAt(i, 9);
				boolean isSOTrx = false;
				//
				psl.setInvoice(C_Invoice_ID, isSOTrx, 
					OpenAmt, PayAmt, OpenAmt.subtract(PayAmt));
				if (!psl.save(trxName))
				{
					FDialog.error(m_WindowNo, this, "SaveError", Msg.translate(Env.getCtx(), "C_PaySelectionLine_ID"));
					return;
				}
				log.fine("C_Invoice_ID=" + C_Invoice_ID + ", PayAmt=" + PayAmt);
			}
		}   //  for all rows in table


		//  Ask to Post it
		if (!FDialog.ask(m_WindowNo, this, "VPaySelectGenerate?", "(" + m_ps.getName() + ")"))
			return;

		//  Prepare Process 
		int AD_Proces_ID = 155;	//	C_PaySelection_CreatePayment

		//	Execute Process
		ProcessModalDialog dialog = new ProcessModalDialog(null, getFormName(), this, m_WindowNo, 
				AD_Proces_ID, X_C_PaySelection.Table_ID, m_ps.getC_PaySelection_ID(), false);
		if (dialog.isValid()) {
			try {
				dialog.setWidth("500px");
				dialog.setVisible(true);
				dialog.setPage(this.getPage());
				dialog.doModal();
			} catch (SuspendNotAllowedException e) {
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			} catch (InterruptedException e) {
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}
		}
	}   //  generatePaySelect
	
	/**
	 *  Lock User Interface
	 *  Called from the Worker before processing
	 */
	public void lockUI (ProcessInfo pi)
	{
		if (m_isLock) return;
		m_isLock = true;
		Clients.showBusy(null, true);
	}   //  lockUI

	/**
	 *  Unlock User Interface.
	 *  Called from the Worker when processing is done
	 */
	public void unlockUI (ProcessInfo pi)
	{
		if (!m_isLock) return;
		m_isLock = false;
		m_pi = pi;
		Clients.showBusy(null, false);	
		Clients.response(new AuEcho(this, "onAfterProcess", null));
	}   //  unlockUI
	
	public void onAfterProcess()
	{
		if (!FDialog.ask(0, this, "VPaySelectPrint?", "(" + m_pi.getSummary() + ")"))
		{
			dispose();
			return;
		}

		this.dispose();
		
		//  Start PayPrint
		int AD_Form_ID = 106;	//	Payment Print/Export
		ADForm form = SessionManager.getAppDesktop().openForm(AD_Form_ID);
		if (m_ps != null)
		{
			WPayPrint pp = (WPayPrint)form;
			pp.setPaySelection(m_ps.getC_PaySelection_ID());
		}
	}

	/**************************************************************************
	 *  Bank Account Info
	 */
	public class BankInfo
	{
		/**
		 * 	BankInfo
		 *	@param newC_BankAccount_ID
		 *	@param newC_Currency_ID
		 *	@param newName
		 *	@param newCurrency
		 *	@param newBalance
		 *	@param newTransfers
		 */
		public BankInfo (int newC_BankAccount_ID, int newC_Currency_ID,
			String newName, String newCurrency, BigDecimal newBalance, boolean newTransfers)
		{
			C_BankAccount_ID = newC_BankAccount_ID;
			C_Currency_ID = newC_Currency_ID;
			Name = newName;
			Currency = newCurrency;
			Balance = newBalance;
		}
		int C_BankAccount_ID;
		int C_Currency_ID;
		String Name;
		String Currency;
		BigDecimal Balance;
		boolean Transfers;

		/**
		 * 	to String
		 *	@return info
		 */
		public String toString()
		{
			return Name;
		}
	}   //  BankInfo

	public void executeASync(ProcessInfo pi) {
	}

	public boolean isUILocked() {
		return m_isLock;
	}

}   //  VPaySelect
