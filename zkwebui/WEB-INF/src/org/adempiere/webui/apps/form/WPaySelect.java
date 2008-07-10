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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.webui.apps.ProcessModalDialog;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
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
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Separator;

/**
 * Pay Selection Manual Custom Form : Based on VPaySelect
 * 
 * @author  Niraj Sohun
 * @date    Jun 25, 2007
 */
public class WPaySelect extends ADForm implements EventListener, WTableModelListener
{
	private static final long serialVersionUID = 1L;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WPaySelect.class);
	
	// Input Part of Form
	
	private Grid parameters;
	private Rows rows;
	private Row row1;
	private Row row2;
	private Row row3;

	// Labels
	
	private Label lblBalanceAmt;
	private Label lblBottom;
	
	// Components
	
	private Listbox lstBankAccount;
	private Listbox lstBusinessPartner;
	private Checkbox dueInvoices;
	private Datebox date;
	private Listbox lstPaymentRule;
	private Button refresh;
	private Button btnProcess;
	
	// Data Grid
	
	private WListbox dataTable;
	
	private int m_AD_Client_ID = 0;
	private String m_sql = "";
	private DecimalFormat m_format = DisplayType.getNumberFormat(DisplayType.Amount);
	private Float currentBalance;
	
	/**
	 * 
	 *
	 */
	public WPaySelect()
	{
		init();
	}
	
	/**
	 * 
	 *
	 */
	private void init()
	{
		dataTable = new WListbox();
		dataTable.setWidth("700px");
		dataTable.setMultiple(true);

		// Input Part of Form
		
		parameters = new Grid();
		parameters.setWidth("700px");
		parameters.setAlign("center");
		
		rows = new Rows();
		row1 = new Row();
		row2 = new Row();
		row3 = new Row();
	
		// Components
		
		lblBalanceAmt = new Label();
		lblBottom = new Label();
		
		lstBankAccount = new Listbox();
		lstBankAccount.setWidth("150px");
		lstBankAccount.setRows(1);
        lstBankAccount.setMold("select");
        lstBankAccount.addEventListener(Events.ON_SELECT, this);
        		
		lstBusinessPartner = new Listbox();
		lstBusinessPartner.setWidth("150px");
		lstBusinessPartner.setRows(1);
        lstBusinessPartner.setMold("select");
        lstBusinessPartner.addEventListener(Events.ON_SELECT, this);
		
		dueInvoices = new Checkbox();
		dueInvoices.setLabel(Msg.getMsg(Env.getCtx(), "OnlyDue"));
		dueInvoices.addEventListener(Events.ON_SELECT, this);
		
		date = new Datebox();
		date.setWidth("150px");
		date.setValue(new Timestamp(System.currentTimeMillis()));
		date.addEventListener(Events.ON_SELECT, this);
		
		lstPaymentRule = new Listbox();
		lstPaymentRule.setWidth("150px");
		lstPaymentRule.setWidth("150px");
		lstPaymentRule.setRows(1);
        lstPaymentRule.setMold("select");
        lstPaymentRule.addEventListener(Events.ON_SELECT, this);
				
		refresh = new Button();
		refresh.setImage("/images/Refresh24.gif");
		refresh.addEventListener(Events.ON_CLICK, this);
        
        btnProcess = new Button();
        btnProcess.setImage("/images/Process24.gif");
        btnProcess.setEnabled(false);
        btnProcess.addEventListener(Events.ON_CLICK, this);
		
		display();
	}
	
	/**
	 * 
	 *
	 */

	private void display()
	{
		row1.appendChild(new Label("Bank Account"));
		row1.appendChild(lstBankAccount);
		
		row1.appendChild(new Label("Current Balance"));
		row1.appendChild(lblBalanceAmt);
		
		row2.setSpans(",2,");
		row2.appendChild(new Label("Business Partner"));
		row2.appendChild(lstBusinessPartner);

		row2.appendChild(dueInvoices);
		
		row3.appendChild(new Label("Payment Date"));
		row3.appendChild(date);
		row3.appendChild(new Label("Payment Rule"));
		row3.appendChild(lstPaymentRule);
		
		rows.appendChild(row1);
		rows.appendChild(row2);
		rows.appendChild(row3);
		parameters.appendChild(rows);
		
		Hbox mainBox = new Hbox();
		mainBox.setWidth("700px");
		
		Hbox hboxButtons = new Hbox();
		hboxButtons.setWidth("80px");
		hboxButtons.appendChild(btnProcess);
		hboxButtons.appendChild(refresh);
		
		Hbox hLbl = new Hbox();
		hLbl.appendChild(lblBottom);
				
		mainBox.appendChild(hboxButtons);
		mainBox.appendChild(hLbl);
		
		this.setHeight("710px");
		this.setWidth("100%");
		this.setBorder("normal");
		this.appendChild(parameters);
		this.appendChild(new Separator());
		this.appendChild(dataTable);
		this.appendChild(new Separator());
		this.appendChild(mainBox);
		
		populateBankAccount();
		populateBusinessPartner();
		populateGrid();
	}
	
	/**
	 * 
	 *
	 */
	private void process()
	{
		int adProcessId = 155;
		String trxName = null;
		
		calculateSelection();
		
		MPaySelection m_ps = new MPaySelection(Env.getCtx(), 0, trxName);
		
		ListItem payRule = lstPaymentRule.getSelectedItem();
		Timestamp payDate = (Timestamp)date.getValue();
		
		m_ps.setName (Msg.getMsg(Env.getCtx(), "WPaySelect")
				+ " - " + ((ValueNamePair)payRule.getValue()).getName()
				+ " - " + payDate);
		
		m_ps.setPayDate (payDate);
		
		BankInfo bi = getSelectedBankAccount();
		m_ps.setC_BankAccount_ID(bi.C_BankAccount_ID);
		m_ps.setIsApproved(true);
		
		if (!m_ps.save())
		{
			FDialog.error(super.m_windowNo, this, "SaveError", Msg.translate(Env.getCtx(), "C_PaySelection_ID"));
			m_ps = null;
			return;
		}

		//  Create Lines
		
		int rows = dataTable.getItemCount();
		int line = 0;

		ListItem pyRule = lstPaymentRule.getSelectedItem();
		String strPayRule = ((ValueNamePair)pyRule.getValue()).getValue();

		for (int i = 0; i < rows; i++)
		{
			if (dataTable.getItemAtIndex(i).isSelected())
			{
				line += 10;
				
				MPaySelectionLine psl = new MPaySelectionLine (m_ps, line, strPayRule);

//				List<ListCell> celllist = (List<ListCell>)(dataTable.getItemAtIndex(i).getChildren());
				
//				ListCell invID = celllist.get(0);
//				ListCell openAmt = celllist.get(8);
//				ListCell payAmt = celllist.get(9);
                IDColumn id = (IDColumn)dataTable.getValueAt(i, 0);                

                Integer C_Invoice_ID = id.getRecord_ID();
                BigDecimal OpenAmt = new BigDecimal(dataTable.getValueAt(i, 8).toString());
                BigDecimal PayAmt = new BigDecimal(dataTable.getValueAt(i, 9).toString());
				
				boolean isSOTrx = false;

				psl.setInvoice(C_Invoice_ID, isSOTrx, OpenAmt, PayAmt, OpenAmt.subtract(PayAmt));
				
				if (!psl.save(trxName))
				{
					FDialog.error(this.m_windowNo, this, "SaveError", Msg.translate(Env.getCtx(), "C_PaySelectionLine_ID"));
					return;
				}
				log.fine("C_Invoice_ID=" + C_Invoice_ID 
						+ ", PayAmt=" + PayAmt);
			}
		} 

		//  Ask to Post it

		if (!FDialog.ask(this.m_windowNo, this, "(" + m_ps.getName() + ")"))
		{
			return;
		}
		
		ProcessModalDialog msg = new ProcessModalDialog(
				this, "Payment Selection Manual", null, this.m_windowNo, adProcessId, 
				X_C_PaySelection.Table_ID, m_ps.getC_PaySelection_ID(), true);
  
		if (msg.isValid()) 
		{
			msg.setTitle("Payment Selection (Manual)");
			msg.setPage(this.getPage());
			msg.setClosable(true);
			msg.setWidth("500px");
						
			try 
			{
				msg.doModal();
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}		
	}

	private void calculateSelection()
	{
		int noSelected = 0;
		BigDecimal invoiceAmt = new BigDecimal(0.0);

		int rows = dataTable.getRowCount();
		
		for (int rowIndex = 0; rowIndex < rows; rowIndex++)
		{
			// TODO remove this magic number
			IDColumn id = (IDColumn)dataTable.getValueAt(rowIndex, 0);
			
			if (id.isSelected())
			{
				// TODO remove this magic number
				BigDecimal amt = (BigDecimal)dataTable.getValueAt(rowIndex, 9);
				invoiceAmt = invoiceAmt.add(amt);
				noSelected++;
			}
		}

		//  Information
		
		BankInfo bi = getSelectedBankAccount();
		BigDecimal remaining = bi.Balance.subtract(invoiceAmt);
		
		StringBuffer info = new StringBuffer();
		info.append(noSelected).append(" ").append(Msg.getMsg(Env.getCtx(), "Selected")).append(" - ");
		info.append(m_format.format(invoiceAmt)).append(", ");
		info.append(Msg.getMsg(Env.getCtx(), "Remaining")).append(" ").append(m_format.format(remaining));
		
		lblBottom.setValue(info.toString());
		
		if (noSelected == 0)
		{
			btnProcess.setEnabled(false);
		}
		else
		{
			btnProcess.setEnabled(true);
		}
		
		return;
	}
	
	/**
	 * Obtain details of the selected bank account
	 * 
	 * @return the BankInfo of the selected account
	 */
	private BankInfo getSelectedBankAccount()
	{
		ListItem bankAccountItem = lstBankAccount.getSelectedItem(); 
		BankInfo bi = (BankInfo)bankAccountItem.getValue();
		
		return bi;
	}

	private void populateBankAccount()
	{
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
		
		BankInfo bi = null;
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				boolean Transfers = false;
				bi = new BankInfo (rs.getInt(1), rs.getInt(3), rs.getString(2), rs.getString(4), rs.getBigDecimal(5), Transfers);
				lstBankAccount.appendItem(bi.Name, bi);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		
		if (lstBankAccount.getItemCount() == 0)
		{
			throw new IllegalStateException("No Bank Account has been found");
		}
		else
		{
			// Selecting the first item
			lstBankAccount.setSelectedIndex(0);
			populatePaymentRule();
			
			updateCurrentBalance();
		}
	}
	
	private void updateCurrentBalance()
	{
		BankInfo bnkInf = getSelectedBankAccount();
		currentBalance = bnkInf.Balance.floatValue();
		lblBalanceAmt.setValue(currentBalance.toString() + "  "  + bnkInf.Currency);
		
		lblBottom.setValue("");
	}
	
	/**
	 * Query the database for Business Partners and populate the Business 
	 * Partner combobox with the returned resultset
	 */
	private void populateBusinessPartner()
	{
		KeyNamePair pp = new KeyNamePair(0, "");
		lstBusinessPartner.appendItem(pp.getName(), pp);
		
		String sql = MRole.getDefault().addAccessSQL(
			"SELECT bp.C_BPartner_ID, bp.Name FROM C_BPartner bp", "bp", 
			MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO)
			+ " AND EXISTS (SELECT * FROM C_Invoice i WHERE bp.C_BPartner_ID=i.C_BPartner_ID"
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
				lstBusinessPartner.appendItem(pp.getName(), pp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			// TODO need to do something with this exception
			log.log(Level.SEVERE, sql, e);
		}
		
		lstBusinessPartner.setSelectedIndex(0);
	}


	/**
	 * Query the database for Payment Rules and populate the Payment
	 * Rules combobox with the returned resultset
	 */
	private void populatePaymentRule()
	{
		ListItem temp = lstBankAccount.getSelectedItem();
		BankInfo bankInfo = (BankInfo)temp.getValue();
		
		if (bankInfo == null)
		{
			return;
		}
		
		//  PaymentRule

		lstPaymentRule.getChildren().clear();
		
		int AD_Reference_ID = 195; //TODO: Find this reference in the models
		Language language = Env.getLanguage(Env.getCtx());
		MLookupInfo info = MLookupFactory.getLookup_List(language, AD_Reference_ID);
		String sql = info.Query.substring(0, info.Query.indexOf(" ORDER BY"))
			+ " AND " + info.KeyColumn
			+ " IN (SELECT PaymentRule FROM C_BankAccountDoc WHERE C_BankAccount_ID=?) "
			+ info.Query.substring(info.Query.indexOf(" ORDER BY"));
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, bankInfo.C_BankAccount_ID);
			ResultSet rs = pstmt.executeQuery();
			ValueNamePair vp = null;
			while (rs.next())
			{
				vp = new ValueNamePair(rs.getString(2), rs.getString(3));   //  returns also not active
				lstPaymentRule.appendItem(vp.getName(), vp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		
		lstPaymentRule.setSelectedIndex(0);		
	}
	
	/**
	 * Prepare the data table
	 *
	 */
	private void prepareGrid()
	{
		// FROM VPaySelect.dynInit 
		
		// MiniTable Parameters
		
		Properties ctx = Env.getCtx();
		
		ColumnInfo[] columnInfo = new ColumnInfo[10];
		columnInfo[0] = new ColumnInfo(" ", "i.C_Invoice_ID", IDColumn.class, false, false, null);
		columnInfo[1] = new ColumnInfo(Msg.translate(ctx, "DueDate"), "paymentTermDueDate(i.C_PaymentTerm_ID, i.DateInvoiced) AS DateDue", Timestamp.class, true, true, null);
		columnInfo[2] = new ColumnInfo(Msg.translate(ctx, "C_BPartner_ID"), "bp.Name", KeyNamePair.class, true, false, "i.C_BPartner_ID");
		columnInfo[3] = new ColumnInfo(Msg.translate(ctx, "DocumentNo"), "i.DocumentNo", String.class);
		columnInfo[4] = new ColumnInfo(Msg.translate(ctx, "C_Currency_ID"), "c.ISO_Code", KeyNamePair.class, true, false, "i.C_Currency_ID");
		columnInfo[5] = new ColumnInfo(Msg.translate(ctx, "GrandTotal"), "i.GrandTotal", BigDecimal.class);
		columnInfo[6] = new ColumnInfo(Msg.translate(ctx, "DiscountAmt"), "paymentTermDiscount(i.GrandTotal,i.C_Currency_ID,i.C_PaymentTerm_ID,i.DateInvoiced, ?)", BigDecimal.class);
		columnInfo[7] = new ColumnInfo(Msg.getMsg(ctx, "DiscountDate"), "SysDate-paymentTermDueDays(i.C_PaymentTerm_ID,i.DateInvoiced,SysDate)", Timestamp.class);
		columnInfo[8] = new ColumnInfo(Msg.getMsg(ctx, "AmountDue"), "currencyConvert(invoiceOpen(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID),i.C_Currency_ID, ?,?,i.C_ConversionType_ID, i.AD_Client_ID,i.AD_Org_ID)", BigDecimal.class);
		columnInfo[9] = new ColumnInfo(Msg.getMsg(ctx, "AmountPay"), "currencyConvert(invoiceOpen(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID)-paymentTermDiscount(i.GrandTotal,i.C_Currency_ID,i.C_PaymentTerm_ID,i.DateInvoiced, ?),i.C_Currency_ID, ?,?,i.C_ConversionType_ID, i.AD_Client_ID,i.AD_Org_ID)", BigDecimal.class);
		
		String fromClause = "C_Invoice_v i"
			+ " INNER JOIN C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID)"
			+ " INNER JOIN C_Currency c ON (i.C_Currency_ID=c.C_Currency_ID)"
			+ " INNER JOIN C_PaymentTerm p ON (i.C_PaymentTerm_ID=p.C_PaymentTerm_ID)";
		
		String whereClause = "i.IsSOTrx=? AND IsPaid='N'"
			+ " AND NOT EXISTS (SELECT * FROM C_PaySelectionLine psl"
							+ " WHERE i.C_Invoice_ID=psl.C_Invoice_ID AND psl.C_PaySelectionCheck_ID IS NOT NULL)"
			+ " AND i.DocStatus IN ('CO','CL')"
			+ " AND i.AD_Client_ID=?";
		
		boolean multiSelect = true;
		
		String tableName = "i";
		
		// Create MiniTable
		
		m_sql = dataTable.prepareTable(columnInfo, fromClause, whereClause, multiSelect,tableName);

		dataTable.getModel().addTableModelListener(this);
		
		//TODO
		//fieldPayDate.setMandatory(true);

		m_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
	}
	
	private void populateGrid()
	{
		prepareGrid();
		
		// FROM VPaySelect.loadTableInfo
		
		if (m_sql == null)
		{
			return;
		}

		String sql = m_sql;

		//  Parameters
		
		Timestamp payDate = (Timestamp)date.getValue();
		dataTable.setColorCompare(payDate);
		log.config("PayDate=" + payDate);

		// Bank Account
		BankInfo bi = getSelectedBankAccount();

		String isSOTrx = "N";
		
		// Payment Rule
		
		ListItem selectedRule = lstPaymentRule.getSelectedItem();
		ValueNamePair vp = (ValueNamePair)selectedRule.getValue();
		
		if (vp != null && X_C_Order.PAYMENTRULE_DirectDebit.equals(vp.getValue()))
		{
			isSOTrx = "Y";
			sql += " AND i.PaymentRule='" + X_C_Order.PAYMENTRULE_DirectDebit + "'";
		}
		
		if (dueInvoices.isChecked())
		{
			sql += " AND paymentTermDueDate(i.C_PaymentTerm_ID, i.DateInvoiced) <= ?";
		}
		
		// Business Partner
		
		ListItem selectedBPartner = lstBusinessPartner.getSelectedItem();
		KeyNamePair pp = (KeyNamePair)selectedBPartner.getValue();
		
		int C_BPartner_ID = pp.getKey();
		
		if (C_BPartner_ID != 0)
			sql += " AND i.C_BPartner_ID=?";
		
		sql += " ORDER BY 2,3";
		
		log.finest(sql + " - C_Currecny_ID=" + bi.C_Currency_ID + ", C_BPartner_ID=" + C_BPartner_ID);
		
		//int m_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		
		// Get Open Invoices
		
		try
		{
			int columnIndex = 1;
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setTimestamp(columnIndex++, payDate);		//	DiscountAmt
			pstmt.setInt(columnIndex++, bi.C_Currency_ID);	//	DueAmt
			pstmt.setTimestamp(columnIndex++, payDate);		
			pstmt.setTimestamp(columnIndex++, payDate);		//	PayAmt
			pstmt.setInt(columnIndex++, bi.C_Currency_ID);
			pstmt.setTimestamp(columnIndex++, payDate);
			pstmt.setString(columnIndex++, isSOTrx);			//	IsSOTrx	
			pstmt.setInt(columnIndex++, m_AD_Client_ID);		//	Client	
			
			if (dueInvoices.isChecked())
			{
				pstmt.setTimestamp(columnIndex++, payDate);
			}
			
			if (C_BPartner_ID != 0)
			{
				pstmt.setInt(columnIndex++, C_BPartner_ID);
			}
			
			ResultSet rs = pstmt.executeQuery();
			dataTable.loadTable(rs);
			rs.close();
			pstmt.close();
			
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
	}

	public void onEvent(Event evt) throws Exception 
	{
		if (evt != null)
		{
			if (evt.getTarget() == lstBankAccount)
			{
				populatePaymentRule();
				updateCurrentBalance();
				dataTable.getItems().clear();
				populateGrid();
			}
			
			if (evt.getTarget() == lstBusinessPartner)
			{
				dataTable.getItems().clear();
				populateGrid();
			}
			
			if (evt.getTarget() == lstPaymentRule)
			{
				dataTable.getItems().clear();
				populateGrid();
			}
			
			if (evt.getTarget() == dueInvoices)
			{
				dataTable.getItems().clear();
				populateGrid();
			}
			
			if (evt.getTarget() == date)
			{
				dataTable.getItems().clear();
				populateGrid();
			}
			
			if (evt.getTarget() == refresh)
			{
				dataTable.clear();
				populateGrid();
			}
			
			if (evt.getTarget() == btnProcess)
			{
				if (dataTable.getSelectedCount() <= 0)
				{
					btnProcess.setEnabled(false);
					throw new IllegalArgumentException("No records selected");
				}
				
				process();
			}
			
			if (evt.getTarget() instanceof ListItem)
			{
				btnProcess.setEnabled(true);
				
				ListItem lstitem = (ListItem)(evt.getTarget());
						
				if (lstitem.isSelected())
				{
					dataTable.addItemToSelection(lstitem);
				}	
				Integer size = dataTable.getSelectedCount(); 
					
				Float amt = calculateTotalAmount();
				Float remaining = currentBalance - amt; 
					
				lblBottom.setValue(size.toString() + " Selected :: " + amt.toString() + ", Remaining " + remaining.toString()); 
			}
		}
	}	
	
	private Float calculateTotalAmount()
	{
		Float amount = new Float(0);
				
		for (int i = 0; i < dataTable.getItemCount(); i++)
		{
			if (dataTable.getItemAtIndex(i).isSelected())
			{
				List<Listcell> celllist = (List<Listcell>)(dataTable.getItemAtIndex(i).getChildren());
				Listcell payAmt = celllist.get(9);
				amount += new Float(payAmt.getLabel());
			}
		}
		
		return amount;
	}
	
	/* (non-Javadoc)
	 * @see org.adempiere.webui.event.WTableModelListener#tableChanged(org.adempiere.webui.event.WTableModelEvent)
	 */
	public void tableChanged(WTableModelEvent event)
	{
		if (event.getColumn() == 0)
		{
			calculateSelection();
		}
	}
	
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
		public BankInfo (int newC_BankAccount_ID, int newC_Currency_ID, String newName, String newCurrency, BigDecimal newBalance, boolean newTransfers)
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
	}
}
