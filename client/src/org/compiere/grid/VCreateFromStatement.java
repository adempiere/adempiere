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
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.table.*;
import org.compiere.apps.*;
import org.compiere.grid.ed.*;
import org.compiere.model.*;
import org.compiere.swing.CButton;
import org.compiere.swing.CTextField;
import org.compiere.util.*;
/**
 *  Create Transactions for Bank Statements
 *
 *  @author Jorg Janke
 *  @version  $Id: VCreateFromStatement.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 *  @author Victor Perez, e-Evolucion 
 *  <li> RF [1811114] http://sourceforge.net/tracker/index.php?func=detail&aid=1811114&group_id=176962&atid=879335
 */
public class VCreateFromStatement	extends VCreateFrom	implements ActionListener
{
	private MBankAccount bankAccount;

	/**
	 *  Protected Constructor
	 *  @param mTab MTab
	 */
	public VCreateFromStatement(GridTab mTab)
	{
		super (mTab);

		//Refresh button
		CButton refreshButton = ConfirmPanel.createRefreshButton(false);
		refreshButton.setMargin(new Insets (1, 10, 0, 10));
		refreshButton.setDefaultCapable(true);
		refreshButton.addActionListener(this);
		confirmPanel.addButton(refreshButton);
		this.getRootPane().setDefaultButton(refreshButton);
		
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
		
		//
		
        // Do not display RMA selection
       /* rmaLabel.setVisible(false);
        rmaField.setVisible(false);
        
        sameWarehouseCb.setVisible(false);
*/
		setTitle(Msg.translate(Env.getCtx(), "C_BankStatement_ID") + " .. " + Msg.translate(Env.getCtx(), "CreateFrom"));
		parameterStdPanel.setVisible(false);

		int AD_Column_ID = 4917;        //  C_BankStatement.C_BankAccount_ID
		MLookup lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, AD_Column_ID, DisplayType.TableDir);
		bankAccountField = new VLookup ("C_BankAccount_ID", true, true, true, lookup);
		//  Set Default
		int C_BankAccount_ID = Env.getContextAsInt(Env.getCtx(), p_WindowNo, "C_BankAccount_ID");
		bankAccountField.setValue(new Integer(C_BankAccount_ID));
		//  initial Loading
		authorizationField = new VString ("authorization", false, false, true, 10, 30, null, null);
		authorizationField.addActionListener(this);

		MLookup lookupDocument = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, MColumn.getColumn_ID(MPayment.Table_Name, MPayment.COLUMNNAME_C_DocType_ID), DisplayType.TableDir);
		documentTypeField = new VLookup (MPayment.COLUMNNAME_C_DocType_ID,false,false,true,lookupDocument);
		documentTypeField.addActionListener(this);
		
		MLookup lookupTender = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, MColumn.getColumn_ID(MPayment.Table_Name, MPayment.COLUMNNAME_TenderType), DisplayType.List);
		tenderTypeField = new VLookup (MPayment.COLUMNNAME_TenderType,false,false,true,lookupTender);
		tenderTypeField.addActionListener(this);
		
		bPartnerLookup = new VLookup("C_BPartner_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 3499, DisplayType.Search));
		BPartner_idLabel.setLabelFor(bPartnerLookup);
		
		Timestamp date = Env.getContextAsDate(Env.getCtx(), p_WindowNo, MBankStatement.COLUMNNAME_StatementDate);
		dateToField.setValue(date);
	
		bankAccount = new MBankAccount(Env.getCtx(), C_BankAccount_ID, null);
		
		loadBankAccount();

		return true;
	}   //  dynInit

	/**************************************************************************
	 *	Construct SQL Where Clause and define parameters
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return sql where clause
	 */
	String getSQLWhere()
	{
		StringBuffer sql = new StringBuffer("WHERE p.Processed='Y' AND p.IsReconciled='N'"
		+ " AND p.DocStatus IN ('CO','CL','RE','VO') AND p.PayAmt<>0" 
		+ " AND p.C_BankAccount_ID = ?");
		
		if (documentNoField.getText().length() > 0)
			sql.append(" AND UPPER(p.DocumentNo) LIKE ?");
		//
		if (bPartnerLookup.getValue() != null)
			sql.append(" AND p.C_BPartner_ID=?");
		//
		if (dateFromField.getValue() != null || dateToField.getValue() != null)
		{
			Timestamp from = (Timestamp)dateFromField.getValue();
			Timestamp to = (Timestamp)dateToField.getValue();
			if (from == null && to != null)
				sql.append(" AND TRUNC(p.DateTrx) <= ?");
			else if (from != null && to == null)
				sql.append(" AND TRUNC(p.DateTrx) >= ?");
			else if (from != null && to != null)
				sql.append(" AND TRUNC(p.DateTrx) BETWEEN ? AND ?");
		}
		//
		if (amtFromField.getValue() != null || amtToField.getValue() != null)
		{
			BigDecimal from = (BigDecimal)amtFromField.getValue();
			BigDecimal to = (BigDecimal)amtToField.getValue();
			if (from == null && to != null)
				sql.append(" AND p.PayAmt <= ?");
			else if (from != null && to == null)
				sql.append(" AND p.PayAmt >= ?");
			else if (from != null && to != null)
				sql.append(" AND p.PayAmt BETWEEN ? AND ?");
		}
		
		if(documentTypeField.getValue()!=null)
			sql.append(" AND p.C_DocType_ID=?");
		if(tenderTypeField.getValue() != null && tenderTypeField.getValue().toString().length() > 0)
			sql.append(" AND p.TenderType=?");
		if(authorizationField.getText().length() > 0 )
			sql.append(" AND p.R_AuthCode LIKE ?");

		log.fine(sql.toString());
		return sql.toString();
	}	//	getSQLWhere

	/**
	 *  Set Parameters for Query.
	 *  (as defined in getSQLWhere)
	 *  @param pstmt statement
	 *  @param forCount for counting records
	 *  @throws SQLException
	 */
	void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;
		
		pstmt.setInt(index++, bankAccount.getC_BankAccount_ID());
		
		if (documentNoField.getText().length() > 0)
			pstmt.setString(index++, getSQLText(documentNoField));
		//
		if (bPartnerLookup.getValue() != null)
		{
			Integer bp = (Integer)bPartnerLookup.getValue();
			pstmt.setInt(index++, bp.intValue());
			log.fine("BPartner=" + bp);
		}
		//
		if (dateFromField.getValue() != null || dateToField.getValue() != null)
		{
			Timestamp from = (Timestamp)dateFromField.getValue();
			Timestamp to = (Timestamp)dateToField.getValue();
			log.fine("Date From=" + from + ", To=" + to);
			if (from == null && to != null)
				pstmt.setTimestamp(index++, to);
			else if (from != null && to == null)
				pstmt.setTimestamp(index++, from);
			else if (from != null && to != null)
			{
				pstmt.setTimestamp(index++, from);
				pstmt.setTimestamp(index++, to);
			}
		}
		//
		if (amtFromField.getValue() != null || amtToField.getValue() != null)
		{
			BigDecimal from = (BigDecimal)amtFromField.getValue();
			BigDecimal to = (BigDecimal)amtToField.getValue();
			log.fine("Amt From=" + from + ", To=" + to);
			if (from == null && to != null)
				pstmt.setBigDecimal(index++, to);
			else if (from != null && to == null)
				pstmt.setBigDecimal(index++, from);
			else if (from != null && to != null)
			{
				pstmt.setBigDecimal(index++, from);
				pstmt.setBigDecimal(index++, to);
			}
		}
		if(documentTypeField.getValue()!=null)
			pstmt.setInt(index++, (Integer) documentTypeField.getValue());
		if(tenderTypeField.getValue()!=null  && tenderTypeField.getValue().toString().length() > 0 )
			pstmt.setString(index++, (String) tenderTypeField.getValue());
		if(authorizationField.getText().length() > 0 )
			pstmt.setString(index++, getSQLText(authorizationField));

	}   //  setParameters
	/**
	 *  Get SQL WHERE parameter
	 *  @param f field
	 *  @return Upper case text with % at the end
	 */
	private String getSQLText (CTextField f)
	{
		String s = f.getText().toUpperCase();
		if (!s.endsWith("%"))
			s += "%";
		log.fine( "String=" + s);
		return s;
	}   //  getSQLText

	/**
	 *  Init Details (never called)
	 *  @param C_BPartner_ID BPartner
	 */
	protected void initBPDetails(int C_BPartner_ID)
	{
	}   //  initDetails
 

	/**
	 *  Load Data - Bank Account
	 */
	private void loadBankAccount ()
	{
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		String sql = "SELECT p.DateTrx,p.C_Payment_ID,p.DocumentNo, p.C_Currency_ID,c.ISO_Code, p.PayAmt,"
			+ "currencyConvert(p.PayAmt,p.C_Currency_ID,ba.C_Currency_ID,pay.DateAcct,p.C_ConversionType_ID,p.AD_Client_ID,p.AD_Org_ID),"
			+ " bp.Name "
			+ "FROM C_BankAccount ba"
			+ " INNER JOIN C_Payment_v p ON (p.C_BankAccount_ID=ba.C_BankAccount_ID)"
			+ " INNER JOIN C_Payment pay ON (p.C_Payment_ID=pay.C_Payment_ID)"
			+ " INNER JOIN C_Currency c ON (p.C_Currency_ID=c.C_Currency_ID)"
			+ " LEFT OUTER JOIN C_BPartner bp ON (p.C_BPartner_ID=bp.C_BPartner_ID) ";

		sql = sql + getSQLWhere() + " ORDER BY p.DateTrx";

		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			
			setParameters( pstmt, false);
			
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
				BigDecimal TrxAmt = (BigDecimal)model.getValueAt(i, 5); //  5- Conv Amt

				log.fine("Line Date=" + trxDate
					+ ", Payment=" + C_Payment_ID + ", Currency=" + C_Currency_ID + ", Amt=" + TrxAmt);
				//	
				MBankStatementLine bsl = new MBankStatementLine (bs);
				bsl.setStatementLineDate(trxDate);
				bsl.setPayment(new MPayment(Env.getCtx(), C_Payment_ID, null));
				
				bsl.setTrxAmt(TrxAmt);
				bsl.setStmtAmt(TrxAmt);
				bsl.setC_Currency_ID(bankAccount.getC_Currency_ID()); 
				
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
		log.config("Action=" + e.getActionCommand());
		Object source = e.getSource();
		if ( e.getActionCommand().equals(confirmPanel.A_REFRESH) )	{
			Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
			loadBankAccount();
			tableChanged(null);
			Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		}
		else
			super.actionPerformed(e);
	}

}   //  VCreateFromStatement
