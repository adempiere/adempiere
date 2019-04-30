DROP VIEW T_INVOICEGL_VT;
CREATE OR REPLACE VIEW T_INVOICEGL_VT
(T_InvoiceGL_ID , AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY,
 UPDATED, UPDATEDBY, C_INVOICE_ID, ISSOTRX, DOCUMENTNO, 
 DOCSTATUS, C_DOCTYPE_ID, C_ORDER_ID, DESCRIPTION, SALESREP_ID, 
 DATEINVOICED, DATEACCT, C_PAYMENTTERM_ID, C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, 
 AD_USER_ID, ISSELFSERVICE, C_CURRENCY_ID, C_CONVERSIONTYPE_ID, GRANDTOTAL, 
 ISTAXINCLUDED, C_CAMPAIGN_ID, C_PROJECT_ID, C_ACTIVITY_ID, AD_ORGTRX_ID, 
 USER1_ID, USER2_ID, USER3_ID, USER4_ID, C_LOCFROM_ID, C_LOCTO_ID, C_SALESREGION_ID,
 FACT_ACCT_ID, C_ACCTSCHEMA_ID, ACCOUNT_ID, C_PERIOD_ID, GL_CATEGORY_ID, 
 GL_BUDGET_ID, C_TAX_ID, M_LOCATOR_ID, POSTINGTYPE, AMTSOURCEDR, 
 AMTSOURCECR, AMTACCTDR, AMTACCTCR, C_UOM_ID, QTY, 
 AD_PINSTANCE_ID, APAR, OPENAMT, PERCENT, AMTREVALDR, 
 AMTREVALCR, DATEREVAL, C_CONVERSIONTYPEREVAL_ID, AMTSOURCEBALANCE, AMTACCTBALANCE, 
 C_DOCTYPEREVAL_ID, AMTREVALDRDIFF, AMTREVALCRDIFF, ISALLCURRENCIES, AMTACCTOPENDR,
 AMTACCTOPENCR, AMTACCTOPENBALANCE, AD_Table_ID , Record_ID , AD_Language)
AS 
SELECT
    gl.T_InvoiceGL_ID,
    COALESCE(i.AD_Client_ID,fa.AD_Client_ID) AS AD_Client_ID ,
    COALESCE(i.AD_Org_ID, fa.AD_Org_ID) AS AD_Org_ID,
    COALESCE(i.IsActive, fa.IsActive) AS IsActive,
    COALESCE(i.Created, fa.Created) AS Created,
    COALESCE(i.CreatedBy, fa.CreatedBy) AS CreatedBy,
    COALESCE(i.Updated,fa.Updated) AS Updated,
    COALESCE(i.UpdatedBy,fa.UpdatedBy) AS UpdatedBy,
    i.C_Invoice_ID, i.IsSOTrx, i.DocumentNo, i.DocStatus, i.C_DocType_ID, i.C_Order_ID,
    i.Description, i.SalesRep_ID, i.DateInvoiced,
    COALESCE(i.DateAcct, fa.DateAcct) AS DateAcct,
    i.C_PaymentTerm_ID,
    COALESCE(i.C_BPartner_ID,fa.C_BPartner_ID) AS C_BPartner_ID,
    i.C_BPartner_Location_ID, i.AD_User_ID, i.IsSelfService,
     COALESCE(i.C_Currency_ID, gl.C_Currency_ID) AS C_Currency_ID,
    i.C_ConversionType_ID, i.GrandTotal, i.IsTaxIncluded,
--  References
    COALESCE(i.C_Campaign_ID, fa.C_Campaign_ID) AS C_Campaign_ID,
    COALESCE(i.C_Project_ID, fa.C_Project_ID) AS C_Project_ID,
    COALESCE(i.C_Activity_ID, fa.C_Activity_ID) AS C_Activity_ID,
    COALESCE(i.AD_OrgTrx_ID, fa.AD_OrgTrx_ID) AS AD_OrgTrx_ID,
    COALESCE(i.User1_ID, fa.User1_ID) AS User1_ID,
    COALESCE(i.User2_ID, fa.User2_ID) AS User2_ID,
    COALESCE(i.User3_ID, fa.User3_ID) AS User3_ID,
    COALESCE(i.User4_ID, fa.User4_ID) AS User4_ID,
    fa.C_LocFrom_ID, fa.C_LocTo_ID, fa.C_SalesRegion_ID,
--  Accounting
    gl.Fact_Acct_ID,  COALESCE(gl.C_AcctSchema_ID, fa.C_AcctSchema_ID) AS C_AcctSchema_ID, fa.Account_ID, fa.C_Period_ID, fa.GL_Category_ID, fa.GL_Budget_ID,
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
    ((fa.AmtAcctDr-fa.AmtAcctCr)*gl.Percent/100) AS AmtAcctOpenBalance,
     gl.AD_Table_ID , gl.Record_ID , l.AD_Language
FROM T_InvoiceGL gl
  LEFT JOIN C_Invoice i ON (gl.C_Invoice_ID=i.C_Invoice_ID)
  LEFT JOIN Fact_Acct fa ON (gl.Fact_Acct_ID=fa.Fact_Acct_ID)
  LEFT JOIN AD_Language l ON (l.IsSystemLanguage='Y' AND l.IsActive = 'Y')
  LEFT JOIN C_DocType_Trl dttrl ON (gl.C_DocTypeReval_ID=dttrl.C_DocType_ID AND l.AD_Language=dttrl.AD_Language);