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
 *                                                                            *
 *  Contributors:                                                             *
 *    Carlos Ruiz - GlobalQSS:                                                *
 *      FR 3132033 - Make payment export class configurable per bank          *
 *****************************************************************************/
package org.compiere.apps.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.core.domains.models.X_C_BankAccountDoc;
import org.compiere.model.MBankAccount;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MLookupInfo;
import org.compiere.model.MPaySelectionCheck;
import org.compiere.model.MPaymentBatch;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 297 ] Payment Selection must be like ADempiere Document, this process is changed to 
 *			document workflow of Payment Selection
 *		@see https://github.com/adempiere/adempiere/issues/297
 * @author  victor.perez , victor.perez@e-evolution.com http://www.e-evolution.com
 * 		<li> FR [ 297 ] Apply ADempiere best Pratice
 *		@see https://github.com/adempiere/adempiere/issues/297
 */
@Deprecated
public class PayPrint {

	/**	Window No			*/
	public int windowNo = 0;
	/**	Used Bank Account	*/
	public int bankAccountId = -1;
	/**	Export Class for Bank Account	*/
	public String paymentExportClass = null;
	/**	Payment Selection	*/
	public int paySelectionId = 0;

	/** Payment Information */
	public List<MPaySelectionCheck> paySelectionChecks = new ArrayList<>();
	/** Payment Batch		*/
	public MPaymentBatch	paymentBatch = null;
	/**	Logger			*/
	public static CLogger log = CLogger.getCLogger(PayPrint.class);
	public String bank;
	public String currency;
	public BigDecimal balance;
	
	/**
	 *  PaySelect changed - load Bank
	 *  FR [ 297 ] Change by Document Type
	 */
	public void loadPaySelectInfo(int paySelectionId)
	{
		//  load Banks from PaySelectLine
		bankAccountId = -1;
		String sql = "SELECT ps.C_BankAccount_ID, (b.Name || ' ' || ba.AccountNo) AS BankName,"	//	1..2
			+ " c.ISO_Code, CurrentBalance, dt.IsPayrollPayment, ba.PaymentExportClass, ba.PayrollPaymentExportClass "					//	3..5
			+ "FROM C_PaySelection ps"
			+ " INNER JOIN C_BankAccount ba ON (ps.C_BankAccount_ID=ba.C_BankAccount_ID)"
			+ " INNER JOIN C_Bank b ON (ba.C_Bank_ID=b.C_Bank_ID)"
			+ " INNER JOIN C_Currency c ON (ba.C_Currency_ID=c.C_Currency_ID) "
			+ " INNER JOIN C_DocType dt ON(dt.C_DocType_ID = ps.C_DocType_ID) "
			+ "WHERE ps.C_PaySelection_ID=? "
			+ "AND ps.DocStatus IN('CO', 'CL') "
			+ "AND ba.IsActive='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, paySelectionId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bankAccountId = rs.getInt("C_BankAccount_ID");
				bank = rs.getString("BankName");
				currency = rs.getString("ISO_Code");
				balance = rs.getBigDecimal("CurrentBalance");
				String isPayrollPayment = rs.getString("IsPayrollPayment");
				String payrollExportClass = rs.getString("PayrollPaymentExportClass");
				paymentExportClass = rs.getString("PaymentExportClass");
				if(!Util.isEmpty(isPayrollPayment)
						&& isPayrollPayment.equals("Y")
						&& !Util.isEmpty(payrollExportClass)) {
					paymentExportClass = payrollExportClass; 
				}
			} else {
				bankAccountId = -1;
				bank = "";
				currency = "";
				balance = Env.ZERO;
				paymentExportClass = null;
				log.log(Level.SEVERE, "No active BankAccount for C_PaySelection_ID=" + paySelectionId);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
	}   //  loadPaySelectInfo

	/**
	 *  Bank changed - load PaymentRule
	 */
	public ArrayList<ValueNamePair> loadPaymentRule(int paySelectionId)
	{
		ArrayList<ValueNamePair> data = new ArrayList<ValueNamePair>();

		// load PaymentRule for Bank
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
			pstmt.setInt(1, paySelectionId);
			ResultSet rs = pstmt.executeQuery();
			//
			while (rs.next())
			{
				ValueNamePair pp = new ValueNamePair(rs.getString(2), rs.getString(3));
				data.add(pp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		
		if (data.size() == 0)
			log.config("PaySel=" + paySelectionId + ", BAcct=" + bankAccountId + " - " + sql);
		
		return data;
	}   //  loadPaymentRule
	
	public String noPayments;
	public Integer documentNo;
	

	/**
	 *  PaymentRule changed - load DocumentNo, NoPayments,
	 *  enable/disable EFT, Print
	 */
	public String loadPaymentRuleInfo(int paySelectionId, String paymentRule)
	{
		Integer paymentCount  = new Query(Env.getCtx(), MPaySelectionCheck.Table_Name , MPaySelectionCheck.COLUMNNAME_C_PaySelection_ID + "=?" , null)
				.setClient_ID()
				.setParameters(paySelectionId)
				.count();

		noPayments = String.valueOf(paymentCount);
		String msg = null;
		//"SELECT CurrentNext FROM C_BankAccountDoc WHERE  C_BankAccount_ID=? AND PaymentRule=? AND IsActive=?
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ")
				.append(X_C_BankAccountDoc.COLUMNNAME_CurrentNext)
				.append(" FROM ")
				.append(X_C_BankAccountDoc.Table_Name)
				.append(" WHERE ")
				.append(X_C_BankAccountDoc.COLUMNNAME_C_BankAccount_ID).append("=? AND ")
				.append(X_C_BankAccountDoc.COLUMNNAME_PaymentRule).append("=? AND ")
				.append(X_C_BankAccountDoc.COLUMNNAME_IsActive).append("=?");

		List<Object> parameters =  new ArrayList<>();
		parameters.add(bankAccountId);
		parameters.add(paymentRule);
		parameters.add(true);
		//  DocumentNo
		documentNo = DB.getSQLValueEx(null , sql.toString() , parameters.toArray());
		if (documentNo == -1)
		{
			log.log(Level.SEVERE, "VPayPrint.loadPaymentRuleInfo - No active BankAccountDoc for C_BankAccount_ID="
					+ bankAccountId + " AND PaymentRule=" + paymentRule);
			msg = "VPayPrintNoDoc";
		}
		return msg;
	}   //  loadPaymentRuleInfo

	/**
	 *
	 * @return
	 */
	protected String getValidationCode()
	{
		return "C_PaySelection.DocStatus = 'CO' "
				+ "AND C_PaySelection.C_BankAccount_ID IS NOT NULL "
				+ "AND EXISTS(SELECT 1 FROM C_PaySelectionCheck psc "
				+ "				LEFT JOIN C_Payment p ON(p.C_Payment_ID = psc.C_Payment_ID) "
				+ "				WHERE psc.C_PaySelection_ID = C_PaySelection.C_PaySelection_ID "
				+ "				AND (psc.C_Payment_ID IS NULL OR p.DocStatus NOT IN('CO', 'CL')))";
	}
	
	/**
	 * Set Next Sequence for Bank Account Document 
	 * @param paymentRule
	 * @param lastDocumentNo
	 */
	protected void setBankAccountNextSequence(String paymentRule, int lastDocumentNo) {
		if (bankAccountId > 0) { 
			MBankAccount bankAccount = MBankAccount.get(Env.getCtx(), bankAccountId);
			bankAccount.setDocumentCurrentNext(paymentRule, lastDocumentNo);
		}
	}
}
