/******************************************************************************
 * Copyright (C) 2009 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
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
package org.compiere.apps.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.I_C_BankStatement;
import org.compiere.model.MBankAccount;
import org.compiere.model.MBankStatement;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.MPayment;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;

/**
 *  Create Transactions for Bank Statements
 *
 *  @author Jorg Janke
 *  @version  $Id: VCreateFromStatement.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 *  @author Victor Perez, e-Evolucion 
 *  <li> RF [1811114] http://sourceforge.net/tracker/index.php?func=detail&aid=1811114&group_id=176962&atid=879335
 *  @author Teo Sarca, www.arhipac.ro
 * 			<li>BF [ 2007837 ] VCreateFrom.save() should run in trx
 *  @author Michael McKay (mjmckay)
 * 			<li>BF3439685 Create from for Statement Line picks wrong date
 * 			See https://sourceforge.net/tracker/?func=detail&aid=3439695&group_id=176962&atid=879332
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 114 ] Change "Create From" UI for Form like Dialog in window without "hardcode"
 *		@see https://github.com/adempiere/adempiere/issues/114
 *		<li> FR [ 441 ] Create From in C_BankStatement change to Smart Browse
 *		@see https://github.com/adempiere/adempiere/issues/441
 */
@Deprecated
public class CreateFromStatement {
	/**	Logger				*/
	protected CLogger 		log = CLogger.getCLogger(getClass());
	/**	Model Statement		*/
	private MBankStatement 	m_BankStatement;
	/**	Bank Account		*/
	private MBankAccount	m_BankAccount;
	/**	Record Identifier	*/
	private int 			m_Record_ID = 0;
	
	/**
	 * Valid Table and Record Identifier
	 * @param p_AD_Table_ID
	 * @param p_Record_ID
	 */
	protected void validTable(int p_AD_Table_ID, int p_Record_ID) {
		//	Valid Table
		if(p_AD_Table_ID != I_C_BankStatement.Table_ID) {
			throw new AdempiereException("@AD_Table_ID@ @C_BankStatement_ID@ @NotFound@");
		}
		//	Valid Record Identifier
		if(p_Record_ID <= 0) {
			throw new AdempiereException("@SaveErrorRowNotFound@");
		}
		//	Default
		m_Record_ID = p_Record_ID;
		//	Instance Bank Statement
		m_BankStatement = new MBankStatement(Env.getCtx(), m_Record_ID, null);
		m_BankAccount = m_BankStatement.getBankAccount();
	}
	
	/**
	 * Get Account
	 * @return
	 */
	protected int getC_BankAccount_ID() {
		//	Valid null
		if(m_BankStatement != null) {
			return m_BankStatement.getC_BankAccount_ID();
		}
		//	Default
		return 0;
	}
	
	/**************************************************************************
	 *	Construct SQL Where Clause and define parameters
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return sql where clause
	 */
	public String getSQLWhere(String DocumentNo, Object BPartner, Object DateFrom, Object DateTo, 
			Object AmtFrom, Object AmtTo, Object DocType, Object TenderType, String AuthCode)
	{
		StringBuffer sql = new StringBuffer("WHERE p.Processed='Y' AND p.IsReconciled='N'"
		+ " AND p.DocStatus IN ('CO','CL','RE','VO') AND p.PayAmt<>0" 
		+ " AND p.C_BankAccount_ID = ?");
		
	    sql.append( " AND NOT EXISTS (SELECT * FROM C_BankStatementLine l " 
		//	Voided Bank Statements have 0 StmtAmt
			+ "WHERE p.C_Payment_ID=l.C_Payment_ID AND l.StmtAmt <> 0)");
		
		if (DocumentNo.length() > 0)
			sql.append(" AND UPPER(p.DocumentNo) LIKE ?");
		//
		if (BPartner != null)
			sql.append(" AND p.C_BPartner_ID=?");
		//
		if (DateFrom != null || DateTo != null)
		{
			Timestamp from = (Timestamp) DateFrom;
			Timestamp to = (Timestamp) DateTo;
			if (from == null && to != null)
				sql.append(" AND TRUNC(p.DateTrx, 'DD') <= ?");
			else if (from != null && to == null)
				sql.append(" AND TRUNC(p.DateTrx, 'DD') >= ?");
			else if (from != null && to != null)
				sql.append(" AND TRUNC(p.DateTrx, 'DD') BETWEEN ? AND ?");
		}
		//
		if (AmtFrom != null || AmtTo != null)
		{
			BigDecimal from = (BigDecimal) AmtFrom;
			BigDecimal to = (BigDecimal) AmtTo;
			if (from == null && to != null)
				sql.append(" AND p.PayAmt <= ?");
			else if (from != null && to == null)
				sql.append(" AND p.PayAmt >= ?");
			else if (from != null && to != null)
				sql.append(" AND p.PayAmt BETWEEN ? AND ?");
		}
		
		if(DocType!=null)
			sql.append(" AND p.C_DocType_ID=?");
		if(TenderType != null && TenderType.toString().length() > 0)
			sql.append(" AND p.TenderType=?");
		if(AuthCode.length() > 0 )
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
	private void setParameters(PreparedStatement pstmt, boolean forCount, 
			String DocumentNo, Object BPartner, Object DateFrom, Object DateTo, 
			Object AmtFrom, Object AmtTo, Object DocType, Object TenderType, String AuthCode) 
					throws SQLException {
		int index = 1;
		
		pstmt.setInt(index++, m_BankStatement.getC_BankAccount_ID());
		
		if (DocumentNo.length() > 0)
			pstmt.setString(index++, getSQLText(DocumentNo));
		//
		if (BPartner != null)
		{
			Integer bp = (Integer) BPartner;
			pstmt.setInt(index++, bp.intValue());
			log.fine("BPartner=" + bp);
		}
		//
		if (DateFrom != null || DateTo != null)
		{
			Timestamp from = (Timestamp) DateFrom;
			Timestamp to = (Timestamp) DateTo;
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
		if (AmtFrom != null || AmtTo != null)
		{
			BigDecimal from = (BigDecimal) AmtFrom;
			BigDecimal to = (BigDecimal) AmtTo;
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
		if(DocType!=null)
			pstmt.setInt(index++, (Integer) DocType);
		if(TenderType!=null  && TenderType.toString().length() > 0 )
			pstmt.setString(index++, (String) TenderType);
		if(AuthCode.length() > 0 )
			pstmt.setString(index++, getSQLText(AuthCode));

	}   //  setParameters
	
	/**
	 *  Get SQL WHERE parameter
	 *  @param f field
	 *  @return Upper case text with % at the end
	 */
	private String getSQLText (String text)
	{
		String s = text.toUpperCase();
		if (!s.endsWith("%"))
			s += "%";
		log.fine( "String=" + s);
		return s;
	}   //  getSQLText
	
	protected Vector<Vector<Object>> getBankData(String DocumentNo, Object BPartner, Object DateFrom, Object DateTo, 
			Object AmtFrom, Object AmtTo, Object DocType, Object TenderType, String AuthCode)
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

		sql = sql + getSQLWhere(DocumentNo, BPartner, DateFrom, DateTo, AmtFrom, AmtTo, DocType, TenderType, AuthCode) + " ORDER BY p.DateTrx";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			setParameters(pstmt, false, DocumentNo, BPartner, DateFrom, DateTo, AmtFrom, AmtTo, DocType, TenderType, AuthCode);
			rs = pstmt.executeQuery();
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
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		return data;
	}
	
	/**
	 * Configure Mini Table Column
	 * @param miniTable
	 */
	protected void configureMiniTable (IMiniTable miniTable) {
		miniTable.setColumnClass(0, Boolean.class, false);      //  0-Selection
		miniTable.setColumnClass(1, Timestamp.class, true);     //  1-TrxDate
		miniTable.setColumnClass(2, String.class, true);        //  2-Payment
		miniTable.setColumnClass(3, String.class, true);        //  3-Currency
		miniTable.setColumnClass(4, BigDecimal.class, true);    //  4-Amount
		miniTable.setColumnClass(5, BigDecimal.class, true);    //  5-ConvAmount
		miniTable.setColumnClass(6, String.class, true);    	//  6-BPartner
		//  Table UI
		miniTable.autoSize();
	}

	/**
	 *  Save Statement - Insert Data
	 *  @return true if saved
	 */
	public boolean save(IMiniTable miniTable, String trxName) {
		//  fixed values
		MBankStatement bs = new MBankStatement (Env.getCtx(), m_Record_ID, trxName);
		log.config(bs.toString());
		
		//  Lines
		for (int i = 0; i < miniTable.getRowCount(); i++) {
			if (((Boolean)miniTable.getValueAt(i, 0)).booleanValue()) {
				Timestamp trxDate = (Timestamp)miniTable.getValueAt(i, 1);  //  1-DateTrx
				KeyNamePair pp = (KeyNamePair)miniTable.getValueAt(i, 2);   //  2-C_Payment_ID
				int C_Payment_ID = pp.getKey();
				pp = (KeyNamePair)miniTable.getValueAt(i, 3);               //  3-Currency
				int C_Currency_ID = pp.getKey();
				BigDecimal TrxAmt = (BigDecimal)miniTable.getValueAt(i, 5); //  5- Conv Amt

				log.fine("Line Date=" + trxDate
					+ ", Payment=" + C_Payment_ID + ", Currency=" + C_Currency_ID + ", Amt=" + TrxAmt);
				//	
				MBankStatementLine bsl = new MBankStatementLine (bs);
				
				// BF3439695 - Create from for Statement Line picks wrong date
				bsl.setDateAcct(bs.getStatementDate());
				bsl.setStatementLineDate(bs.getStatementDate());
				bsl.setValutaDate(trxDate);
				
				bsl.setPayment(new MPayment(Env.getCtx(), C_Payment_ID, trxName));
				
				bsl.setTrxAmt(TrxAmt);
				bsl.setStmtAmt(TrxAmt);
				bsl.setC_Currency_ID(m_BankAccount.getC_Currency_ID()); 
				
				if (!bsl.save())
					log.log(Level.SEVERE, "Line not created #" + i);
			}   //   if selected
		}   //  for all rows
		return true;
	}   //  save
	
	/**
	 * Get Column Names from Table
	 * @return
	 */
	protected Vector<String> getOISColumnNames() {
		//  Header Info
		Vector<String> columnNames = new Vector<String>(6);
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add(Msg.translate(Env.getCtx(), "Date"));
		columnNames.add(Msg.getElement(Env.getCtx(), "C_Payment_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "Amount"));
		columnNames.add(Msg.translate(Env.getCtx(), "ConvertedAmount"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
	    
	    return columnNames;
	}
}