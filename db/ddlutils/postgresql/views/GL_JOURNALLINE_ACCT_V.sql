CREATE OR REPLACE VIEW GL_JOURNALLINE_ACCT_V
(GL_JOURNALLINE_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, 
 CREATEDBY, UPDATED, UPDATEDBY, GL_JOURNAL_ID, LINE, 
 ISGENERATED, DESCRIPTION, AMTSOURCEDR, AMTSOURCECR, C_CURRENCY_ID, 
 C_CONVERSIONTYPE_ID, CURRENCYRATE, DATEACCT, AMTACCTDR, AMTACCTCR, 
 C_UOM_ID, QTY, C_VALIDCOMBINATION_ID, C_ACCTSCHEMA_ID, ACCOUNT_ID, 
 M_PRODUCT_ID, C_BPARTNER_ID, AD_ORGTRX_ID, C_LOCFROM_ID, C_LOCTO_ID, 
 C_SALESREGION_ID, C_PROJECT_ID, C_CAMPAIGN_ID, USER1_ID, USER2_ID, 
 ISFULLYQUALIFIED, C_ACTIVITY_ID)
AS 
SELECT 
	gl.GL_JournalLine_ID, gl.AD_Client_ID, gl.AD_Org_ID, gl.IsActive, 
	gl.Created, gl.CreatedBy, gl.Updated, gl.UpdatedBy, gl.GL_Journal_ID, 
	gl.Line, gl.IsGenerated, gl.Description, 
	gl.AmtSourceDr, gl.AmtSourceCr, gl.C_Currency_ID, 
	gl.C_ConversionType_ID, gl.CurrencyRate, gl.DateAcct, 
	gl.AmtAcctDr, gl.AmtAcctCr, gl.C_UOM_ID, gl.Qty, gl.C_ValidCombination_ID, 
	vc.C_AcctSchema_ID, vc.Account_ID, vc.M_Product_ID, vc.C_BPartner_ID,
	vc.AD_OrgTrx_ID, vc.C_LocFrom_ID, vc.C_LocTo_ID, vc.C_SalesRegion_ID, 
	vc.C_Project_ID, vc.C_Campaign_ID, vc.User1_ID, vc.User2_ID, 
	vc.IsFullyQualified, vc.C_Activity_ID
FROM GL_JournalLine gl, C_ValidCombination vc
WHERE gl.C_ValidCombination_ID = vc.C_ValidCombination_ID;



