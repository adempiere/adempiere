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
 *****************************************************************************/
package org.compiere.grid;

import java.beans.*;
import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import java.awt.event.ActionEvent;
import javax.swing.table.*;
import org.compiere.apps.*;
import org.compiere.grid.ed.*;
import org.compiere.model.*;
import org.compiere.util.*;

/**
 *  Create Transactions for Bank Statements
 *
 *  @author Jorg Janke
 *  @version  $Id: VCreateFromStatement.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 *  @author Victor Perez, e-Evolucion 
 *  <li> RF [1811114] http://sourceforge.net/tracker/index.php?func=detail&aid=1811114&group_id=176962&atid=879335
 */
public class VCreateFromStatement extends VCreateFrom implements VetoableChangeListener
{
	/**
	 *  Protected Constructor
	 *  @param mTab MTab
	 */
	VCreateFromStatement(GridTab mTab)
	{
		super (mTab);
		log.info("");
	}   //  VCreateFromStatement

	/**
	 *  Dynamic Init
	 *  @throws Exception if Lookups cannot be initialized
	 *  @return true if initialized
	 */
	protected boolean dynInit() throws Exception
	{
		if (p_mTab.getValue("C_BankStatement_ID") == null)
		{
			ADialog.error(0, this, "SaveErrorRowNotFound");
			return false;
		}
        // Do not display RMA selection
        rmaLabel.setVisible(false);
        rmaField.setVisible(false);

		setTitle(Msg.translate(Env.getCtx(), "C_BankStatement_ID") + " .. " + Msg.translate(Env.getCtx(), "CreateFrom"));
		parameterStdPanel.setVisible(false);

		int AD_Column_ID = 4917;        //  C_BankStatement.C_BankAccount_ID
		MLookup lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, AD_Column_ID, DisplayType.TableDir);
		bankAccountField = new VLookup ("C_BankAccount_ID", true, false, true, lookup);
		bankAccountField.addVetoableChangeListener(this);
		//  Set Default
		int C_BankAccount_ID = Env.getContextAsInt(Env.getCtx(), p_WindowNo, "C_BankAccount_ID");
		bankAccountField.setValue(new Integer(C_BankAccount_ID));
		//  initial Loading
		//RF [1811114]
		String R_AuthCode="";        
		authorizationField = new VString ("authorization", false, false, true, 10, 30, null, null);
		authorizationField.addActionListener(this);
		loadBankAccount(C_BankAccount_ID, R_AuthCode);

		return true;
	}   //  dynInit

	/**
	 *  Init Details (never called)
	 *  @param C_BPartner_ID BPartner
	 */
	protected void initBPDetails(int C_BPartner_ID)
	{
	}   //  initDetails

	/**
	 *  Change Listener
	 *  @param e event
	 */
	public void vetoableChange (PropertyChangeEvent e)
	{
		log.config(e.getPropertyName() + "=" + e.getNewValue());
		int C_BankAccount_ID=0;
		//RF [1811114]
		String R_AuthCode = (authorizationField.getValue().toString());

		//  BankAccount
		if (e.getPropertyName().equals("C_BankAccount_ID"))
		{
			//RF [1811114]
			C_BankAccount_ID = ((Integer)e.getNewValue()).intValue();
			if (authorizationField.getValue().toString().equals(""))
				loadBankAccount(C_BankAccount_ID, null);
			else
				loadBankAccount(C_BankAccount_ID, R_AuthCode);
		}
		tableChanged(null);
	}   //  vetoableChange

	/**
	 *  Load Data - Bank Account
	 *  @param C_BankAccount_ID Bank Account
	 *  @param Autorization Code
	 */
	//RF [1811114]
	private void loadBankAccount (int C_BankAccount_ID, String R_AuthCode)
	{
		log.config ("C_BankAccount_ID=" + C_BankAccount_ID);
		/**
		 *  Selected        - 0
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
			+ " AND p.C_BankAccount_ID=?";      //  #2
			//RF [1811114]
		    if (R_AuthCode!= "" && R_AuthCode!= null)
		    	sql = sql + " AND p.R_AuthCode LIKE ?";
		    
		    sql = sql + " AND NOT EXISTS (SELECT * FROM C_BankStatementLine l " 
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
			//RF [1811114]
			if (R_AuthCode!= "" && R_AuthCode!= null){
				pstmt.setString(3, R_AuthCode);}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector<Object>(6);
				line.add(new Boolean(false));       //  0-Selection
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
		//  Header Info
		Vector<String> columnNames = new Vector<String>(6);
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add(Msg.translate(Env.getCtx(), "Date"));
		columnNames.add(Msg.getElement(Env.getCtx(), "C_Payment_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "Amount"));
		columnNames.add(Msg.translate(Env.getCtx(), "ConvertedAmount"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_BPartner_ID"));

		//  Remove previous listeners
		dataTable.getModel().removeTableModelListener(this);
		//  Set Model
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		model.addTableModelListener(this);
		dataTable.setModel(model);
		//
		dataTable.setColumnClass(0, Boolean.class, false);      //  0-Selection
		dataTable.setColumnClass(1, Timestamp.class, true);     //  1-TrxDate
		dataTable.setColumnClass(2, String.class, true);        //  2-Payment
		dataTable.setColumnClass(3, String.class, true);        //  3-Currency
		dataTable.setColumnClass(4, BigDecimal.class, true);    //  4-Amount
		dataTable.setColumnClass(5, BigDecimal.class, true);    //  5-ConvAmount
		dataTable.setColumnClass(6, String.class, true);    	//  6-BPartner
		//  Table UI
		dataTable.autoSize();
	}   //  loadBankAccount

	/**
	 *  List total amount
	 */
	protected void info()
	{
		DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Amount);

		TableModel model = dataTable.getModel();
		BigDecimal total = new BigDecimal(0.0);
		int rows = model.getRowCount();
		int count = 0;
		for (int i = 0; i < rows; i++)
		{
			if (((Boolean)model.getValueAt(i, 0)).booleanValue())
			{
				total = total.add((BigDecimal)model.getValueAt(i, 4));
				count++;
			}
		}
		setStatusLine(count, Msg.getMsg(Env.getCtx(), "Sum") + "  " + format.format(total));
	}   //  infoStatement

	/**
	 *  Save Statement - Insert Data
	 *  @return true if saved
	 */
	protected boolean save()
	{
		log.config("");
		TableModel model = dataTable.getModel();
		int rows = model.getRowCount();
		if (rows == 0)
			return false;

		//  fixed values
		int C_BankStatement_ID = ((Integer)p_mTab.getValue("C_BankStatement_ID")).intValue();
		MBankStatement bs = new MBankStatement (Env.getCtx(), C_BankStatement_ID, null);
		log.config(bs.toString());

		//  Lines
		for (int i = 0; i < rows; i++)
		{
			if (((Boolean)model.getValueAt(i, 0)).booleanValue())
			{
				Timestamp trxDate = (Timestamp)model.getValueAt(i, 1);  //  1-DateTrx
				KeyNamePair pp = (KeyNamePair)model.getValueAt(i, 2);   //  2-C_Payment_ID
				int C_Payment_ID = pp.getKey();
				pp = (KeyNamePair)model.getValueAt(i, 3);               //  3-Currency
				int C_Currency_ID = pp.getKey();
				BigDecimal TrxAmt = (BigDecimal)model.getValueAt(i, 4); //  4-PayAmt
			//	BigDecimal StmtAmt = (BigDecimal)model.getValueAt(i, 5);//  5-Conv Amt
				//
				log.fine("Line Date=" + trxDate
					+ ", Payment=" + C_Payment_ID + ", Currency=" + C_Currency_ID + ", Amt=" + TrxAmt);
				//	
				MBankStatementLine bsl = new MBankStatementLine (bs);
				bsl.setStatementLineDate(trxDate);
				bsl.setPayment(new MPayment(Env.getCtx(), C_Payment_ID, null));
				if (!bsl.save())
					log.log(Level.SEVERE, "Line not created #" + i);
			}   //   if selected
		}   //  for all rows
		return true;
	}   //  save
	
	/*
	 *  Action Listener
	 *  @param e event*/
	//RF [1811114]
	public void  actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		log.config("Action=" + e.getActionCommand());
		int C_BankAccount_ID  = Env.getContextAsInt(Env.getCtx(), p_WindowNo, "C_BankAccount_ID");
		if (e.getSource().equals(authorizationField))
		{
			String R_AuthCode = (authorizationField.getValue().toString());
			if (authorizationField.getValue().toString().equals(""))
			{
				loadBankAccount(C_BankAccount_ID, null);
			}
			else
				loadBankAccount(C_BankAccount_ID, R_AuthCode);
	 		}
	 }

}   //  VCreateFromStatement
