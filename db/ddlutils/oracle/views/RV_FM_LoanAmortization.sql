-- View: RV_FM_LoanAmortization
-- DROP VIEW RV_FM_LoanAmortization;
/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************
 ***
 * Title:	Loan Amortization from Agreement
 * Description:
 *	Show Loan Amortization and amount for payment for a quote
 *
 * Test:
 * 	SELECT * FROM RV_FM_LoanAmortization WHERE FM_Agreement_ID = 1000000;
 ************************************************************************/
CREATE OR REPLACE VIEW RV_FM_LoanAmortization AS
SELECT ag.AD_Client_ID, ag.AD_Org_ID, ag.IsActive, ag.Created, ag.CreatedBy, ag.Updated, ag.UpdatedBy, 
ag.FM_Agreement_ID, ag.FM_AgreementType_ID, ag.C_DocType_ID, ag.DocumentNo, ag.DateDoc, 
ag.DocStatus, ag.FM_Product_ID, ag.C_BPartner_ID, ag.IsSOTrx, ag.Status, 
ac.FM_Account_ID, ac.C_Currency_ID, ac.AccountNo, 
ac.FeesQty, ac.PaymentFrequency, ac.PayDate, am.FM_Amortization_ID, am.PeriodNo,
am.CapitalAmt, am.InterestAmt, am.TaxAmt, (COALESCE(am.CapitalAmt, 0) + COALESCE(am.InterestAmt, 0) + COALESCE(am.TaxAmt, 0)) AS FeeAmt,
am.StartDate, am.EndDate, am.DueDate, am.IsPaid,
ca.CapitalAmt AS CurrentCapitalAmt, ca.InterestAmt AS CurrentInterestAmt, ca.TaxAmt AS CurrentTaxAmt, ca.DunningAmt AS CurrentDunningAmt, ca.DunningTaxAmt AS CurrentDunningTaxAmt, 
(COALESCE(am.CapitalAmt, 0) + COALESCE(am.InterestAmt, 0) + COALESCE(am.TaxAmt, 0) + COALESCE(ca.DunningTaxAmt, 0) + COALESCE(ca.DunningAmt, 0)) AS CurrentFeeAmt,
(CASE WHEN am.DueDate <= getdate() THEN 'Y' ELSE 'N' END) AS IsDue, am.IsInvoiced,
i.C_Invoice_ID, i.DateInvoiced
FROM FM_Agreement ag
INNER JOIN FM_Account ac ON(ac.FM_Agreement_ID = ag.FM_Agreement_ID)
INNER JOIN FM_Amortization am ON(am.FM_Account_ID = ac.FM_Account_ID)
LEFT JOIN (SELECT t.FM_Amortization_ID, SUM(CASE WHEN tt.Type = 'LCC' THEN t.Amount ELSE 0 END) AS CapitalAmt,
          SUM(CASE WHEN tt.Type = 'LIN' THEN t.Amount ELSE 0 END) AS InterestAmt, 
          SUM(CASE WHEN tt.Type = 'LIT' THEN t.Amount ELSE 0 END) AS TaxAmt,
          SUM(CASE WHEN tt.Type = 'LDD' THEN t.Amount ELSE 0 END) AS DunningAmt,
          SUM(CASE WHEN tt.Type = 'LDT' THEN t.Amount ELSE 0 END) AS DunningTaxAmt
          FROM FM_Transaction t 
          INNER JOIN FM_TransactionType tt ON(tt.FM_TransactionType_ID = t.FM_TransactionType_ID)
          WHERE tt.Type IN('LCC', 'LIN', 'LIT', 'LDD', 'LDT')
          AND EXISTS(SELECT 1 FROM FM_Batch b 
                     WHERE b.FM_Batch_ID = t.FM_Batch_ID
                     AND b.DocStatus IN('CO', 'CL'))
          GROUP BY t.FM_Amortization_ID) ca ON(ca.FM_Amortization_ID = am.FM_Amortization_ID)
LEFT JOIN (SELECT il.FM_Amortization_ID, MAX(i.C_Invoice_ID) AS C_Invoice_ID, MAX(i.DateInvoiced) AS DateInvoiced
	FROM C_Invoice i
	INNER JOIN C_InvoiceLine il ON(il.C_Invoice_ID = i.C_Invoice_ID)
	WHERE i.DocStatus IN('CO', 'CL')
	GROUP BY il.FM_Amortization_ID) i ON(i.FM_Amortization_ID = am.FM_Amortization_ID)
/