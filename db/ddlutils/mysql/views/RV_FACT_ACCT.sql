CREATE OR REPLACE VIEW RV_FACT_ACCT
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, FACT_ACCT_ID, C_ACCTSCHEMA_ID, ACCOUNT_ID, 
 DATETRX, DATEACCT, C_PERIOD_ID, AD_TABLE_ID, RECORD_ID, 
 LINE_ID, GL_CATEGORY_ID, GL_BUDGET_ID, C_TAX_ID, M_LOCATOR_ID, 
 POSTINGTYPE, C_CURRENCY_ID, AMTSOURCEDR, AMTSOURCECR, AMTSOURCE, 
 AMTACCTDR, AMTACCTCR, AMTACCT, RATE, C_UOM_ID, 
 QTY, M_PRODUCT_ID, C_BPARTNER_ID, AD_ORGTRX_ID, C_LOCFROM_ID, 
 C_LOCTO_ID, C_SALESREGION_ID, C_PROJECT_ID, C_CAMPAIGN_ID, C_ACTIVITY_ID, 
 USER1_ID, USER2_ID, A_ASSET_ID, DESCRIPTION, ORGVALUE, 
 ORGNAME, ACCOUNTVALUE, NAME, ACCOUNTTYPE, BPARTNERVALUE, 
 BPNAME, C_BP_GROUP_ID, PRODUCTVALUE, PRODUCTNAME, UPC, 
 M_PRODUCT_CATEGORY_ID)
AS 
SELECT f.AD_Client_ID, f.AD_Org_ID, f.IsActive,f.Created,f.CreatedBy,f.Updated,f.UpdatedBy,
    f.Fact_Acct_ID,
    f.C_AcctSchema_ID, f.Account_ID, f.DateTrx, f.DateAcct, f.C_Period_ID, 
    f.AD_Table_ID, f.Record_ID, f.Line_ID,
    f.GL_Category_ID, f.GL_Budget_ID, f.C_Tax_ID, f.M_Locator_ID, 
    f.PostingType, f.C_Currency_ID,
    f.AmtSourceDr, f.AmtSourceCr, (f.AmtSourceDr - f.AmtSourceCr) AS AmtSource,
    f.AmtAcctDr, f.AmtAcctCr, (f.AmtAcctDr - f.AmtAcctCr) AS AmtAcct,
    CASE WHEN (f.AmtSourceDr - f.AmtSourceCr) = 0 THEN 0 ELSE
        (f.AmtAcctDr - f.AmtAcctCr) / (f.AmtSourceDr - f.AmtSourceCr) END AS Rate,
    f.C_UOM_ID, f.Qty,
    f.M_Product_ID, f.C_BPartner_ID, f.AD_OrgTrx_ID, 
    f.C_LocFrom_ID, f.C_LocTo_ID, f.C_SalesRegion_ID,
    f.C_Project_ID, f.C_Campaign_ID, f.C_Activity_ID, 
    f.User1_ID, f.User2_ID, f.A_Asset_ID,
    f.Description,
    o.Value AS OrgValue, o.Name AS OrgName,
    ev.Value AS AccountValue, ev.Name, ev.AccountType,
    bp.Value AS BPartnerValue, bp.Name AS BPName, bp.C_BP_Group_ID, 
    p.Value AS ProductValue, p.Name AS ProductName, p.UPC, p.M_Product_Category_ID
FROM Fact_Acct f
  INNER JOIN AD_Org o ON (f.AD_Org_ID=o.AD_Org_ID)
  INNER JOIN C_ElementValue ev ON (f.Account_ID=ev.C_ElementValue_ID)
  LEFT OUTER JOIN C_BPartner bp ON (f.C_BPartner_ID=bp.C_BPartner_ID)
  LEFT OUTER JOIN M_Product p ON (f.M_Product_ID=p.M_Product_ID);



