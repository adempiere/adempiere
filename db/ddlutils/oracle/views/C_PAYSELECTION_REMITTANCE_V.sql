CREATE OR REPLACE VIEW C_PAYSELECTION_REMITTANCE_V
(AD_CLIENT_ID, AD_ORG_ID, AD_LANGUAGE, C_PAYSELECTION_ID, C_PAYSELECTIONLINE_ID, 
 C_PAYSELECTIONCHECK_ID, PAYMENTRULE, LINE, OPENAMT, PAYAMT, 
 DISCOUNTAMT, DIFFERENCEAMT, C_BPARTNER_ID, DOCUMENTNO, DATEINVOICED, 
 GRANDTOTAL, AMTINWORDS)
AS 
SELECT psl.AD_Client_ID, psl.AD_Org_ID, 
	cast('en_US' as varchar) AS AD_Language,
	psl.C_PaySelection_ID, psl.C_PaySelectionLine_ID, 
	psl.C_PaySelectionCheck_ID,
	psl.PaymentRule, psl.Line, psl.OpenAmt, psl.PayAmt, psl.DiscountAmt, psl.DifferenceAmt, 
	i.C_BPartner_ID, i.DocumentNo, i.DateInvoiced, i.GrandTotal, i.GrandTotal AS AmtInWords
FROM C_PaySelectionLine psl
  INNER JOIN C_Invoice i ON (psl.C_Invoice_ID=i.C_Invoice_ID);



