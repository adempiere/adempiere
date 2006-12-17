CREATE OR REPLACE VIEW RV_FACT_ACCT_PERIOD
(AD_CLIENT_ID, AD_ORG_ID, C_ACCTSCHEMA_ID, ACCOUNT_ID, C_PERIOD_ID, 
 GL_CATEGORY_ID, GL_BUDGET_ID, C_TAX_ID, M_LOCATOR_ID, POSTINGTYPE, 
 C_CURRENCY_ID, AMTSOURCEDR, AMTSOURCECR, AMTSOURCE, AMTACCTDR, 
 AMTACCTCR, AMTACCT, RATE, M_PRODUCT_ID, C_BPARTNER_ID, 
 AD_ORGTRX_ID, C_LOCFROM_ID, C_LOCTO_ID, C_SALESREGION_ID, C_PROJECT_ID, 
 C_CAMPAIGN_ID, C_ACTIVITY_ID, USER1_ID, USER2_ID, A_ASSET_ID)
AS 
SELECT AD_Client_ID, AD_Org_ID,
    C_AcctSchema_ID, Account_ID, C_Period_ID, 
    GL_Category_ID, GL_Budget_ID, C_Tax_ID, M_Locator_ID, 
    PostingType, C_Currency_ID,
    SUM(AmtSourceDr) AS AmtSourceDr, SUM(AmtSourceCr) AS AmtSourceCr, SUM(AmtSourceDr - AmtSourceCr) AS AmtSource,
    SUM(AmtAcctDr) AS AmtAcctDr, SUM(AmtAcctCr) AS AmtAcctCr, SUM(AmtAcctDr - AmtAcctCr) AS AmtAcct,
    CASE WHEN SUM(AmtSourceDr - AmtSourceCr) = 0 THEN 0 ELSE
        SUM(AmtAcctDr - AmtAcctCr) / SUM(AmtSourceDr - AmtSourceCr) END AS Rate,
    M_Product_ID, C_BPartner_ID, AD_OrgTrx_ID, C_LocFrom_ID, C_LocTo_ID, C_SalesRegion_ID,
    C_Project_ID, C_Campaign_ID, C_Activity_ID, User1_ID, User2_ID, A_Asset_ID
FROM Fact_Acct
GROUP BY AD_Client_ID, AD_Org_ID,
    C_AcctSchema_ID, Account_ID, C_Period_ID, 
    GL_Category_ID, GL_Budget_ID, C_Tax_ID, M_Locator_ID, 
    PostingType, C_Currency_ID,
    M_Product_ID, C_BPartner_ID, AD_OrgTrx_ID, C_LocFrom_ID, C_LocTo_ID, C_SalesRegion_ID,
    C_Project_ID, C_Campaign_ID, C_Activity_ID, User1_ID, User2_ID, A_Asset_ID;



