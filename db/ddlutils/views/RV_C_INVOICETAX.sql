CREATE OR REPLACE VIEW RV_C_INVOICETAX
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, C_TAX_ID, C_INVOICE_ID, C_DOCTYPE_ID, 
 C_BPARTNER_ID, TAXID, ISTAXEXEMPT, DATEACCT, DATEINVOICED, 
 ISSOTRX, DOCUMENTNO, ISPAID, C_CURRENCY_ID, TAXBASEAMT, 
 TAXAMT, TAXLINETOTAL, MULTIPLIER)
AS 
SELECT 
    i.AD_Client_ID, i.AD_Org_ID, i.IsActive, t.Created, t.CreatedBy, t.Updated, t.UpdatedBy,
    t.C_Tax_ID, i.C_Invoice_ID, i.C_DocType_ID,
    i.C_BPartner_ID, bp.TaxID, bp.IsTaxExempt, 
    i.DateAcct, i.DateInvoiced, i.IsSOTrx, i.DocumentNo, i.IsPaid, i.C_Currency_ID,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN t.TaxBaseAmt*-1 ELSE t.TaxBaseAmt END AS TaxBaseAmt,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN t.TaxAmt*-1 ELSE t.TaxAmt END AS TaxAmt,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN (t.TaxBaseAmt + t.TaxAmt)*-1 ELSE (t.TaxBaseAmt + t.TaxAmt) END AS TaxLineTotal,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END AS Multiplier
FROM C_InvoiceTax t
  INNER JOIN C_Invoice i ON (t.C_Invoice_ID=i.C_Invoice_ID)
  INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
  INNER JOIN C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID);



