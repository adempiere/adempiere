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
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.GridTab;
import org.compiere.model.MBankStatement;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPayment;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.EventListener;

/**
 * Create From Statement : Based on VCreateFromStatement
 * 
 * @author  Niraj Sohun
 * @date    Jul 20, 2007
 */

public class WCreateFromStatement extends WCreateFrom implements EventListener, ValueChangeListener, WTableModelListener
{
	private static final long serialVersionUID = 1L;

	/**
	 *  Protected Constructor
	 *  @param mTab MTab
	 */
	
	WCreateFromStatement(GridTab mTab)
	{
		super(mTab);
		//log.info("");
	}   
	
	/**
	 *  Dynamic Init
	 *  @throws Exception if Lookups cannot be initialized
	 *  @return true if initialized
	 */
	
	protected boolean dynInit() throws Exception
	{
		if (p_mTab.getValue("C_BankStatement_ID") == null)
		{
			FDialog.error(0, this, "SaveErrorRowNotFound");
			return false;
		}

		setTitle(Msg.translate(Env.getCtx(), "C_BankStatement_ID") + " .. " + Msg.translate(Env.getCtx(), "CreateFrom"));
	
		parameterShipmentPanel.setVisible(false);
		parameterInvoicePanel.setVisible(false);
		hboxCommon.setVisible(false);
		
		int AD_Column_ID = 4917;        //  C_BankStatement.C_BankAccount_ID
		MLookup lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, AD_Column_ID, DisplayType.TableDir);
		bankAccountField = new WSearchEditor(lookup, "label","desc", true, false, true);
		bankAccountField.addValueChangeListner(this);
		
		//  Set Default
		
		int C_BankAccount_ID = Env.getContextAsInt(Env.getCtx(), p_WindowNo, "C_BankAccount_ID");
		bankAccountField.setValue(new Integer(C_BankAccount_ID));
	
		// Initial Loading
		loadBankAccount(C_BankAccount_ID);

		return true;
	}
	
	/**
	 *  Init Details (never called)
	 *  @param C_BPartner_ID BPartner
	 */
	
	protected void initBPDetails(int C_BPartner_ID)
	{
	}
	
	public void valueChange(ValueChangeEvent evt) 
	{
		log.config(evt.getPropertyName() + "=" + evt.getNewValue());
		
		if (evt == null)
			return;

		if (evt.getSource() instanceof WEditor)
		{
			//  BankAccount
			
			if (evt.getPropertyName().equals("C_BankAccount_ID"))
			{
				int C_BankAccount_ID = ((Integer)evt.getNewValue()).intValue();
				loadBankAccount(C_BankAccount_ID);
			}
			tableChanged(null);
		}
	}
	
	/**
	 *  Load Data - Bank Account
	 *  @param C_BankAccount_ID Bank Account
	 */
	
	private void loadBankAccount (int C_BankAccount_ID)
	{
		log.config ("C_BankAccount_ID=" + C_BankAccount_ID);
		/**
		 *  Selected        - -
		 *  Date            - 1
		 *  C_Payment_ID    - 2
		 *  C_Currenncy     - 3
		 *  Amt             - 4
		 */
	
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		String sql = "SELECT p.DateTrx,p.C_Payment_ID,p.DocumentNo, p.C_Currency_ID,c.ISO_Code, p.PayAmt,"
			+ "currencyConvert(p.PayAmt,p.C_Currency_ID,ba.C_Currency_ID,?,null,p.AD_Client_ID,p.AD_Org_ID),"   //  #1
			+ " bp.Name "
			+ "FROM C_BankAccount ba"
			+ " INNER JOIN C_Payment_v p ON (p.C_BankAccount_ID=ba.C_BankAccount_ID)"
			+ " INNER JOIN C_Currency c ON (p.C_Currency_ID=c.C_Currency_ID)"
			+ " LEFT OUTER JOIN C_BPartner bp ON (p.C_BPartner_ID=bp.C_BPartner_ID) "
			+ "WHERE p.Processed='Y' AND p.IsReconciled='N'"
			+ " AND p.DocStatus IN ('CO','CL','RE','VO') AND p.PayAmt<>0" // Bug 1564453 Added Voided payment to bank statement payement selection
			+ " AND p.C_BankAccount_ID=?"                              	//  #2
			+ " AND NOT EXISTS (SELECT * FROM C_BankStatementLine l " 
			//	Voided Bank Statements have 0 StmtAmt
				+ "WHERE p.C_Payment_ID=l.C_Payment_ID AND l.StmtAmt <> 0)";

		//  Get StatementDate
		
		Timestamp ts = (Timestamp)p_mTab.getValue("StatementDate");
		
		if (ts == null)
			ts = new Timestamp(System.currentTimeMillis());

		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setTimestamp(1, ts);
			pstmt.setInt(2, C_BankAccount_ID);
			ResultSet rs = pstmt.executeQuery();
		
			while (rs.next())
			{
				Vector<Object> line = new Vector<Object>(6);
				//line.add(new Boolean(false));       //  0-Selection
				line.add(rs.getTimestamp(1));       //  1-DateTrx
			
				KeyNamePair pp = new KeyNamePair(rs.getInt(2), rs.getString(3));
				line.add(pp);                       //  2-C_Payment_ID
				
				pp = new KeyNamePair(rs.getInt(4), rs.getString(5));
				line.add(pp);                       //  3-Currency
				line.add(rs.getBigDecimal(6));      //  4-PayAmt
				line.add(rs.getBigDecimal(7));      //  5-Conv Amt
				line.add(rs.getString(8));      	//  6-BParner
				data.add(line);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		
		if (data.size() == 0)
			return;
		
		//  Header Info
		
		Vector<String> columnNames = new Vector<String>(6);
		//columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add(Msg.translate(Env.getCtx(), "Date"));
		columnNames.add(Msg.getElement(Env.getCtx(), "C_Payment_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "Amount"));
		columnNames.add(Msg.translate(Env.getCtx(), "ConvertedAmount"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_BPartner_ID"));

		//  Remove previous listeners
		//dataTable.getModel().removeListDataListener(this);

		// Set Model
		ListModelTable model = new ListModelTable(data);
		model.addTableModelListener(this);
		dataTable.setData(model, columnNames);
		
		//dataTable.setColumnClass(0, Boolean.class, false);      //  0-Selection
		dataTable.setColumnClass(0, Timestamp.class, true);     //  1-TrxDate
		dataTable.setColumnClass(1, String.class, true);        //  2-Payment
		dataTable.setColumnClass(2, String.class, true);        //  3-Currency
		dataTable.setColumnClass(3, BigDecimal.class, true);    //  4-Amount
		dataTable.setColumnClass(4, BigDecimal.class, true);    //  5-ConvAmount
		dataTable.setColumnClass(5, String.class, true);    	//  6-BPartner
		
		//  Table UI
		//dataTable.autoSize();
	} // loadBankAccount
	
	/**
	 *  List total amount
	 */
	
	protected void info()
	{
		DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Amount);

		ListModelTable model = dataTable.getModel();
		BigDecimal total = new BigDecimal(0.0);
		int rows = model.size();
		int count = 0;
		
		for (int i = 0; i < rows; i++)
		{
			if (dataTable.getItemAtIndex(i).isSelected())//(((Boolean)model.getDataAt(i, 0)).booleanValue())
			{
				total = total.add((BigDecimal)model.getDataAt(i, 4));
				count++;
			}
		}
		lblStatus.setValue(String.valueOf(count) + " - " + Msg.getMsg(Env.getCtx(), "Sum") + "  " + format.format(total));
	} // infoStatement
	
	/**
	 *  Save Statement - Insert Data
	 *  @return true if saved
	 */
	protected boolean save()
	{
		log.config("");
		
		ListModelTable model = dataTable.getModel();
		int rows = model.size();
		
		if (rows == 0)
			return false;

		// Fixed values
		
		int C_BankStatement_ID = ((Integer)p_mTab.getValue("C_BankStatement_ID")).intValue();
		MBankStatement bs = new MBankStatement (Env.getCtx(), C_BankStatement_ID, null);
		log.config(bs.toString());

		//  Lines
		
		for (int i = 0; i < rows; i++)
		{
			if (dataTable.getItemAtIndex(i).isSelected())//(((Boolean)model.getDataAt(i, 0)).booleanValue())
			{
				Timestamp trxDate = (Timestamp)model.getDataAt(i, 0);  //  1-DateTrx
				KeyNamePair pp = (KeyNamePair)model.getDataAt(i, 1);   //  2-C_Payment_ID
				int C_Payment_ID = pp.getKey();
				pp = (KeyNamePair)model.getDataAt(i, 2);               //  3-Currency
				int C_Currency_ID = pp.getKey();
				BigDecimal TrxAmt = (BigDecimal)model.getDataAt(i, 3); //  4-PayAmt
				//	BigDecimal StmtAmt = (BigDecimal)model.getValueAt(i, 5);//  5-Conv Amt

				log.fine("Line Date=" + trxDate
					+ ", Payment=" + C_Payment_ID + ", Currency=" + C_Currency_ID + ", Amt=" + TrxAmt);

				MBankStatementLine bsl = new MBankStatementLine (bs);
				bsl.setStatementLineDate(trxDate);
				bsl.setPayment(new MPayment(Env.getCtx(), C_Payment_ID, null));
				
				if (!bsl.save())
					log.log(Level.SEVERE, "Line not created #" + i);
			} // if selected
		} // for all rows
		return true;
	} // save
}
