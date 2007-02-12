CREATE OR REPLACE VIEW T_INVOICEGL_VT
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, C_INVOICE_ID, ISSOTRX, DOCUMENTNO, 
 DOCSTATUS, C_DOCTYPE_ID, C_ORDER_ID, DESCRIPTION, SALESREP_ID, 
 DATEINVOICED, DATEACCT, C_PAYMENTTERM_ID, C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, 
 AD_USER_ID, ISSELFSERVICE, C_CURRENCY_ID, C_CONVERSIONTYPE_ID, GRANDTOTAL, 
 ISTAXINCLUDED, C_CAMPAIGN_ID, C_PROJECT_ID, C_ACTIVITY_ID, AD_ORGTRX_ID, 
 USER1_ID, USER2_ID, C_LOCFROM_ID, C_LOCTO_ID, C_SALESREGION_ID, 
 FACT_ACCT_ID, C_ACCTSCHEMA_ID, ACCOUNT_ID, C_PERIOD_ID, GL_CATEGORY_ID, 
 GL_BUDGET_ID, C_TAX_ID, M_LOCATOR_ID, POSTINGTYPE, AMTSOURCEDR, 
 AMTSOURCECR, AMTACCTDR, AMTACCTCR, C_UOM_ID, QTY, 
 AD_PINSTANCE_ID, APAR, OPENAMT, PERCENT, AMTREVALDR, 
 AMTREVALCR, DATEREVAL, C_CONVERSIONTYPEREVAL_ID, AMTSOURCEBALANCE, AMTACCTBALANCE, 
 C_DOCTYPEREVAL_ID, AMTREVALDRDIFF, AMTREVALCRDIFF, ISALLCURRENCIES, AMTACCTOPENDR, 
 AMTACCTOPENCR, AMTACCTOPENBALANCE)
AS 
SELECT i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created,i.CreatedBy, i.Updated,i.UpdatedBy,
    i.C_Invoice_ID, i.IsSOTrx, i.DocumentNo, i.DocStatus, i.C_DocType_ID, i.C_Order_ID,
    i.Description, i.SalesRep_ID, i.DateInvoiced, i.DateAcct, i.C_PaymentTerm_ID,
    i.C_BPartner_ID, i.C_BPartner_Location_ID, i.AD_User_ID, i.IsSelfService,
    i.C_Currency_ID, i.C_ConversionType_ID, i.GrandTotal, i.IsTaxIncluded,
--  References
    i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID,
    i.AD_OrgTrx_ID, i.User1_ID, i.User2_ID,
    fa.C_LocFrom_ID, fa.C_LocTo_ID, fa.C_SalesRegion_ID,
--  Accounting
    fa.Fact_Acct_ID, fa.C_AcctSchema_ID, fa.Account_ID, fa.C_Period_ID, fa.GL_Category_ID, fa.GL_Budget_ID,
    fa.C_Tax_ID, fa.M_Locator_ID,
    fa.PostingType, fa.AmtSourceDr, fa.AmtSourceCr,
    fa.AmtAcctDr, fa.AmtAcctCr,
    fa.C_UOM_ID, fa.Qty,
--  Gain/Loss
    gl.AD_PInstance_ID, gl.APAR, gl.OpenAmt, gl.Percent,
    gl.AmtRevalDr, gl.AmtRevalCr, gl.DateReval, gl.C_ConversionTypeReval_ID,
    gl.AmtSourceBalance, gl.AmtAcctBalance,
    gl.C_DocTypeReval_ID,
    gl.AmtRevalDrDiff, gl.AmtRevalCrDiff, gl.IsAllCurrencies,
    (fa.AmtAcctDr*gl.Percent/100) AS AmtAcctOpenDr, (fa.AmtAcctCr*gl.Percent/100) AS AmtAcctOpenCr,
    ((fa.AmtAcctDr-fa.AmtAcctCr)*gl.Percent/100) AS AmtAcctOpenBalance
FROM T_InvoiceGL gl
  INNER JOIN C_Invoice i ON (gl.C_Invoice_ID=i.C_Invoice_ID)
  INNER JOIN Fact_Acct fa ON (gl.Fact_Acct_ID=fa.Fact_Acct_ID);



