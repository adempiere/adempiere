/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the                                  *
 * GNU General Public License as published                                    *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2017 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/

package org.spin.investment.report;

import java.sql.PreparedStatement;

import org.compiere.util.DB;

/** 
 * 	Open Item to date for loans
 *  @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com 
 *  @version Release 3.9.0
 */
public class OpenILoanToDate extends OpenILoanToDateAbstract {
	/**	Start Time				*/
	private long 				m_start = System.currentTimeMillis();
	private StringBuffer 		sql = null;
	
	@Override
	protected void prepare() {
		super.prepare();
		StringBuffer whereClause = new StringBuffer(" WHERE am.DocStatus = 'CO'");
		whereClause.append(" AND am.DateDoc <= ?");
		whereClause.append(" AND (am.DateInvoiced > ? OR am.DateInvoiced IS NULL)");
		//	Add parameters
		if(getBPartnerId() > 0) {
			whereClause.append(" AND am.C_BPartner_ID = ?");
		}
		//	Currency
		if(getCurrencyId() > 0) {
			whereClause.append(" AND am.C_Currency_ID = ?");
		}
		//	Document Type
		if(getDocTypeId() > 0) {
			whereClause.append(" AND am.C_DocType_ID = ?");
		}		
		//	Document Date
		if(getDateDoc() != null) {
			whereClause.append(" AND am.DateDoc >= ?");
		}
		//	Date Invoiced To
		if(getDateDocTo() != null) {
			whereClause.append(" AND am.DateDoc <= ?");
		}
		//	Sales Transaction
		whereClause.append(" AND am.IsSOTrx = ").append(isSOTrx()? "'Y'": "'N'");
		//	Financial Product
		if(getProductId() > 0) {
			whereClause.append(" AND am.FM_Product_ID = ?");
		}
		//	Agreement Type
		if(getAgreementTypeId() > 0) {
			whereClause.append(" AND am.FM_AgreementType_ID = ?");
		}
		//	Agreement
		if(getAgreementId() > 0) {
			whereClause.append(" AND am.FM_Agreement_ID = ?");
		}
		//	Prepare SQL
		sql = new StringBuffer(
				//	Target
				"INSERT INTO T_FM_OpenLoanToDate(AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, FM_Agreement_ID, FM_AgreementType_ID, "
						+ "C_DocType_ID, DocumentNo, DateDoc, FM_Product_ID, C_BPartner_ID, IsSOTrx, Status, FM_Account_ID, "
						+ "C_Currency_ID, AccountNo, FeesQty, PaymentFrequency, PayDate, CapitalAmt, InterestAmt, TaxAmt, "
						+ "FeeAmt, CurrentCapitalAmt, CurrentInterestAmt, CurrentTaxAmt, CurrentDunningAmt, CurrentDunningTaxAmt, CurrentFeeAmt, "
						+ "OpenFeesQty, PaidFeesQty, CurrentDueFeeAmt, DueFeesQty, PayAmt, C_Invoice_ID, DateInvoiced, DueDate, NextFeeAmt, DateTo, AD_PInstance_ID) "
				//	Source
				+ "SELECT am.AD_Client_ID, am.AD_Org_ID, am.IsActive, am.Created, am.CreatedBy, am.Updated, am.UpdatedBy, am.FM_Agreement_ID, am.FM_AgreementType_ID, "
				+ "am.C_DocType_ID, am.DocumentNo, am.DateDoc, am.FM_Product_ID, am.C_BPartner_ID, am.IsSOTrx, am.Status, am.FM_Account_ID, "
				+ "am.C_Currency_ID, am.AccountNo, ac.FeesQty, ac.PaymentFrequency, ac.PayDate, ac.CapitalAmt, ac.InterestAmt, ac.TaxAmt, "
				+ "COALESCE(ac.CapitalAmt, 0) + COALESCE(ac.InterestAmt, 0) + COALESCE(ac.TaxAmt, 0) AS FeeAmt, "
				+ "SUM(CASE WHEN (am.DateInvoiced IS NULL OR am.DateInvoiced > ?) THEN COALESCE(am.CapitalAmt, 0) ELSE 0 END) AS CurrentCapitalAmt, "
				+ "SUM(CASE WHEN (am.DateInvoiced IS NULL OR am.DateInvoiced > ?) THEN COALESCE(am.InterestAmt, 0) ELSE 0 END) AS CurrentInterestAmt, "
				+ "SUM(CASE WHEN (am.DateInvoiced IS NULL OR am.DateInvoiced > ?) THEN COALESCE(am.TaxAmt, 0) ELSE 0 END) AS CurrentTaxAmt, "
				+ "SUM(CASE WHEN (am.DateInvoiced IS NULL OR am.DateInvoiced > ?) THEN COALESCE(dt.Amount, 0) ELSE 0 END) AS CurrentDunningAmt, "
				+ "SUM(CASE WHEN (am.DateInvoiced IS NULL OR am.DateInvoiced > ?) THEN COALESCE(dtt.Amount, 0) ELSE 0 END) AS CurrentDunningTaxAmt, "
				+ "SUM(CASE WHEN (am.DateInvoiced IS NULL OR am.DateInvoiced > ?) THEN "
				+ "		COALESCE(am.CapitalAmt, 0) + COALESCE(am.InterestAmt, 0) + COALESCE(am.TaxAmt, 0) + COALESCE(dt.Amount, 0) + COALESCE(dtt.Amount, 0) ELSE 0 END) AS CurrentFeeAmt, "
				+ "SUM(CASE WHEN (am.DateInvoiced IS NULL OR am.DateInvoiced > ?) THEN 1 ELSE 0 END) OpenFeesQty, "
				+ "SUM(CASE WHEN am.DateInvoiced <= ? THEN 1 ELSE 0 END) PaidFeesQty, "
				+ "SUM(CASE WHEN (am.DateInvoiced IS NULL OR am.DateInvoiced > ?) AND am.DueDate <= ? THEN COALESCE(am.CurrentFeeAmt, 0) ELSE 0 END) AS CurrentDueFeeAmt, "
				+ "SUM(CASE WHEN (am.DateInvoiced IS NULL OR am.DateInvoiced > ?) AND am.DueDate <= ? THEN 1 ELSE 0 END) AS DueFeesQty, "
				+ "SUM(CASE WHEN am.DateInvoiced <= ? THEN COALESCE(am.CurrentFeeAmt, 0) ELSE 0 END) AS PayAmt, i.C_Invoice_ID, i.DateInvoiced, ar.DueDate, ar.CurrentFeeAmt, ?, " + getAD_PInstance_ID() + " "
				+ "FROM RV_FM_LoanAmortization am "
				+ "INNER JOIN FM_Account ac ON(ac.FM_Account_ID = am.FM_Account_ID) "
				+ "LEFT JOIN (SELECT t.FM_Amortization_ID, "
				+ "		MAX(CASE WHEN tt.Type = 'LDD' THEN t.FM_Transaction_ID ELSE 0 END) AS DunningTransaction_ID, "
				+ "		MAX(CASE WHEN tt.Type = 'LDT' THEN t.FM_Transaction_ID ELSE 0 END) AS DunningTaxTransaction_ID "
				+ "	FROM FM_Transaction t "
				+ "	INNER JOIN FM_TransactionType tt ON(tt.FM_TransactionType_ID = t.FM_TransactionType_ID) "
				+ "	WHERE tt.Type IN('LDD', 'LDT') "
				+ "	AND EXISTS(SELECT 1 FROM FM_Batch b WHERE b.FM_Batch_ID = t.FM_Batch_ID AND b.DocStatus NOT IN('DR', 'IP', 'IN') AND b.DateDoc <= ? AND b.TotalAmt >= 0) "
				+ "	GROUP BY t.FM_Amortization_ID) AS ca ON(ca.FM_Amortization_ID = am.FM_Amortization_ID) "
				+ "LEFT JOIN FM_Transaction dt ON(dt.FM_Transaction_ID = ca.DunningTransaction_ID) "
				+ "LEFT JOIN FM_Transaction dtt ON(dtt.FM_Transaction_ID = ca.DunningTaxTransaction_ID) "
				+ "LEFT JOIN (SELECT am.FM_Account_ID, MAX(i.C_Invoice_ID) AS C_Invoice_ID, MAX(i.DateInvoiced) AS DateInvoiced "
				+ "	FROM C_Invoice i "
				+ "	INNER JOIN C_InvoiceLine il ON(il.C_Invoice_ID = i.C_Invoice_ID) "
				+ "	INNER JOIN FM_Amortization am ON(am.FM_Amortization_ID = il.FM_Amortization_ID) "
				+ "	WHERE i.DocStatus IN('CO', 'CL') "
				+ "	AND i.DateInvoiced <= ? "
				+ "GROUP BY am.FM_Account_ID) i ON(i.FM_Account_ID = am.FM_Account_ID) "
				+ "LEFT JOIN (SELECT am.FM_Account_ID, MIN(am.FM_Amortization_ID) AS FM_Amortization_ID "
				+ "	FROM FM_Amortization am "
				+ "	WHERE am.StartDate <= ? "
				+ "	AND NOT EXISTS(SELECT 1 "
				+ "				FROM C_Invoice i "
				+ "				INNER JOIN C_InvoiceLine il ON(il.C_Invoice_ID = i.C_Invoice_ID) "
				+ "				WHERE i.DocStatus IN('CO', 'CL') "
				+ "				AND il.FM_Amortization_ID = am.FM_Amortization_ID) "
				+ "GROUP BY am.FM_Account_ID) dd ON(dd.FM_Account_ID = am.FM_Account_ID) "
				+ "LEFT JOIN RV_FM_LoanAmortization ar ON(ar.FM_Amortization_ID = dd.FM_Amortization_ID) ");
		//	Add Where Clause
		sql.append(whereClause);
		sql.append(
				"GROUP BY am.AD_Client_ID, am.AD_Org_ID, am.IsActive, am.Created, am.CreatedBy, am.Updated, am.UpdatedBy, am.FM_Agreement_ID, "
				+ "am.FM_AgreementType_ID, am.C_DocType_ID, am.DocumentNo, am.DateDoc, am.DocStatus, am.FM_Product_ID, am.C_BPartner_ID, am.IsSOTrx, "
				+ "am.Status, am.FM_Account_ID, am.C_Currency_ID, am.AccountNo, ac.FeesQty, ac.PaymentFrequency, ac.PayDate, "
				+ "ac.CapitalAmt, ac.InterestAmt, ac.TaxAmt, i.C_Invoice_ID, i.DateInvoiced, ar.DueDate, ar.CurrentFeeAmt");
	}

	@Override
	protected String doIt() throws Exception {
		//	
		log.fine("SQL = " + sql.toString());
		//	Prepare statement
		PreparedStatement pstmtInsert = DB.prepareStatement (sql.toString(), get_TrxName());
		int i = 1;
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		pstmtInsert.setTimestamp(i++, getDateTo());
		//	Add parameters
		if(getBPartnerId() > 0) {
			pstmtInsert.setInt(i++, getBPartnerId());
		}
		//	Currency
		if(getCurrencyId() > 0) {
			pstmtInsert.setInt(i++, getCurrencyId());
		}
		//	Document Type
		if(getDocTypeId() > 0) {
			pstmtInsert.setInt(i++, getDocTypeId());
		}		
		//	Document Date
		if(getDateDoc() != null) {
			pstmtInsert.setTimestamp(i++, getDateDoc());
		}
		//	Date Invoiced To
		if(getDateDocTo() != null) {
			pstmtInsert.setTimestamp(i++, getDateDocTo());
		}
		//	Financial Product
		if(getProductId() > 0) {
			pstmtInsert.setInt(i++, getProductId());
		}
		//	Agreement Type
		if(getAgreementTypeId() > 0) {
			pstmtInsert.setInt(i++, getAgreementTypeId());
		}
		//	Agreement
		if(getAgreementId() > 0) {
			pstmtInsert.setInt(i++, getAgreementId());
		}
		
		//	Execute Query for insert
		int noInserts = pstmtInsert.executeUpdate();
		//	
		log.fine((System.currentTimeMillis() - m_start) + " ms");
		//	
		return "@Created@ = " + noInserts;
	}
}