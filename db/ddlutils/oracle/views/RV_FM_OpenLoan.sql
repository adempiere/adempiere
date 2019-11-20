-- View: RV_FM_OpenLoan
-- DROP VIEW RV_FM_OpenLoan;
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
 * Title:	Loan Open
 * Description:
 *	Show Loan 
 *
 * Test:
 * 	SELECT * FROM RV_FM_OpenLoan WHERE FM_Agreement_ID = 1000000;
 ************************************************************************/
CREATE OR REPLACE VIEW RV_FM_OpenLoan AS
SELECT am.AD_Client_ID, am.AD_Org_ID, am.IsActive, am.Created, am.CreatedBy, am.Updated, am.UpdatedBy,
am.FM_Agreement_ID, am.FM_AgreementType_ID, am.C_DocType_ID, am.DocumentNo, am.DateDoc,
am.DocStatus, am.FM_Product_ID, am.C_BPartner_ID, am.IsSOTrx, am.Status,
am.FM_Account_ID, am.C_Currency_ID, am.AccountNo,
ac.FeesQty, ac.PaymentFrequency, ac.PayDate,
ac.CapitalAmt,
ac.InterestAmt,
ac.TaxAmt,
COALESCE(ac.CapitalAmt, 0) + COALESCE(ac.InterestAmt, 0) + COALESCE(ac.TaxAmt, 0) AS FeeAmt,
SUM(CASE WHEN am.IsInvoiced = 'N' OR am.IsPaid = 'N' THEN COALESCE(am.CapitalAmt, 0) ELSE 0 END) AS CurrentCapitalAmt,
SUM(CASE WHEN am.IsInvoiced = 'N' OR am.IsPaid = 'N' THEN COALESCE(am.InterestAmt, 0) ELSE 0 END) AS CurrentInterestAmt,
SUM(CASE WHEN am.IsInvoiced = 'N' OR am.IsPaid = 'N' THEN COALESCE(am.TaxAmt, 0) ELSE 0 END) AS CurrentTaxAmt,
SUM(CASE WHEN am.IsInvoiced = 'N' OR am.IsPaid = 'N' THEN COALESCE(am.CurrentDunningAmt, 0) ELSE 0 END) AS CurrentDunningAmt,
SUM(CASE WHEN am.IsInvoiced = 'N' OR am.IsPaid = 'N' THEN COALESCE(am.CurrentDunningTaxAmt, 0) ELSE 0 END) AS CurrentDunningTaxAmt,
SUM(CASE WHEN am.IsInvoiced = 'N' OR am.IsPaid = 'N' THEN COALESCE(am.CurrentFeeAmt, 0) ELSE 0 END) AS CurrentFeeAmt,
SUM(CASE WHEN am.IsInvoiced = 'N' OR am.IsPaid = 'N' THEN 1 ELSE 0 END) OpenFeesQty,
SUM(CASE WHEN am.IsPaid = 'Y' THEN 1 ELSE 0 END) PaidFeesQty,
SUM(CASE WHEN (am.IsInvoiced = 'N' OR am.IsPaid = 'N') AND am.IsDue = 'Y' THEN COALESCE(am.CurrentFeeAmt, 0) ELSE 0 END) AS CurrentDueFeeAmt,
SUM(CASE WHEN (am.IsInvoiced = 'N' OR am.IsPaid = 'N') AND am.IsDue = 'Y' THEN 1 ELSE 0 END) AS DueFeesQty,
SUM(CASE WHEN am.IsInvoiced = 'Y' AND am.IsPaid = 'Y' THEN COALESCE(am.CurrentFeeAmt, 0) ELSE 0 END) AS PayAmt,
lp.DueDate
FROM RV_FM_LoanAmortization am
INNER JOIN FM_Account ac ON(ac.FM_Account_ID = am.FM_Account_ID)
LEFT JOIN (SELECT lp.FM_Account_ID, lp.DueDate
		FROM RV_FM_LoanAmortization lp
		WHERE
        lp.FM_Amortization_ID =
        (SELECT FM_Amortization_ID FROM (SELECT lpp.FM_Amortization_ID
						FROM RV_FM_LoanAmortization lpp
						WHERE lpp.FM_Account_ID = lp.FM_Account_ID
						AND (lpp.IsInvoiced = 'N' OR lpp.IsPaid = 'N')
						AND lpp.IsDue = 'Y'
						AND ROWNUM = 1
						ORDER BY lpp.PeriodNo) WHERE ROWNUM = 1)
) lp ON(lp.FM_Account_ID = am.FM_Account_ID)
WHERE am.DocStatus = 'CO'
AND EXISTS(SELECT 1 FROM FM_Amortization la
	WHERE la.FM_Account_ID = am.FM_Account_ID
	AND (la.IsInvoiced = 'N' OR la.IsPaid = 'N'))
GROUP BY am.AD_Client_ID, am.AD_Org_ID, am.IsActive, am.Created, am.CreatedBy, am.Updated, am.UpdatedBy,
am.FM_Agreement_ID, am.FM_AgreementType_ID, am.C_DocType_ID, am.DocumentNo, am.DateDoc,
am.DocStatus, am.FM_Product_ID, am.C_BPartner_ID, am.IsSOTrx, am.Status,
am.FM_Account_ID, am.C_Currency_ID, am.AccountNo,
ac.FeesQty, ac.PaymentFrequency, ac.PayDate, ac.CapitalAmt, ac.InterestAmt, ac.TaxAmt,lp.DueDate
/

