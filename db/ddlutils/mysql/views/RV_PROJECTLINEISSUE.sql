CREATE OR REPLACE VIEW RV_ProjectLineIssue
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, C_PROJECT_ID, M_PRODUCT_ID, C_PROJECTLINE_ID, 
 LINE, DESCRIPTION, PLANNEDQTY, PLANNEDPRICE, PLANNEDAMT, 
 PLANNEDMARGINAMT, COMMITTEDQTY, C_PROJECTISSUE_ID, M_LOCATOR_ID, MOVEMENTQTY, 
 MOVEMENTDATE, ISSUELINE, ISSUEDESCRIPTION, M_INOUTLINE_ID, S_TIMEEXPENSELINE_ID, 
 C_ACCTSCHEMA_ID, ACCOUNT_ID, AMTSOURCEDR, AMTSOURCECR, AMTACCTDR, 
 AMTACCTCR, LINEMARGIN)
AS 
SELECT COALESCE(l.AD_Client_ID,i.AD_Client_ID) AS AD_Client_ID, COALESCE(l.AD_Org_ID,i.AD_Org_ID) AS AD_Org_ID,
    COALESCE(l.IsActive,i.IsActive) AS IsActive,
    COALESCE(l.Created,i.Created) AS Created, COALESCE(l.CreatedBy,i.CreatedBy) AS CreatedBy,
    COALESCE(l.Updated,i.Updated) AS Updated, COALESCE(l.UpdatedBy,i.UpdatedBy) AS UpdatedBy,
    COALESCE(l.C_Project_ID,i.C_Project_ID) AS C_Project_ID,
    COALESCE(l.M_Product_ID,i.M_Product_ID) AS M_Product_ID,
    --
    l.C_ProjectLine_ID, l.Line, l.Description, l.PlannedQty, l.PlannedPrice, l.PlannedAmt, 
    l.PlannedMarginAmt, l.CommittedQty,
    --
    i.C_ProjectIssue_ID, i.M_Locator_ID, i.MovementQty, i.MovementDate,
    i.Line AS IssueLine, i.Description AS IssueDescription,
    i.M_InOutLine_ID, i.S_TimeExpenseLine_ID,
    --
    fa.C_AcctSchema_ID, fa.Account_ID, 
    fa.AmtSourceDr, fa.AmtSourceCr, fa.AmtAcctDr, fa.AmtAcctCr,
    --
    l.PlannedAmt-fa.AmtSourceDr+fa.AmtSourceCr AS LineMargin
  FROM C_ProjectLine l
  LEFT OUTER JOIN C_ProjectIssue i ON (l.C_Project_ID=i.C_Project_ID AND l.C_ProjectIssue_ID=i.C_ProjectIssue_ID)  
  LEFT OUTER JOIN Fact_Acct fa ON (fa.AD_Table_ID=623 AND fa.Record_ID=i.C_ProjectIssue_ID AND fa.M_Locator_ID IS NULL)
UNION ALL
  SELECT COALESCE(l.AD_Client_ID,i.AD_Client_ID) AS AD_Client_ID, COALESCE(l.AD_Org_ID,i.AD_Org_ID) AS AD_Org_ID,
    COALESCE(l.IsActive,i.IsActive) AS IsActive,
    COALESCE(l.Created,i.Created) AS Created, COALESCE(l.CreatedBy,i.CreatedBy) AS CreatedBy,
    COALESCE(l.Updated,i.Updated) AS Updated, COALESCE(l.UpdatedBy,i.UpdatedBy) AS UpdatedBy,
    COALESCE(l.C_Project_ID,i.C_Project_ID) AS C_Project_ID,
    COALESCE(l.M_Product_ID,i.M_Product_ID) AS M_Product_ID,
    --
    l.C_ProjectLine_ID, l.Line, l.Description, l.PlannedQty, l.PlannedPrice, l.PlannedAmt, 
    l.PlannedMarginAmt, l.CommittedQty,
    --
    i.C_ProjectIssue_ID, i.M_Locator_ID, i.MovementQty, i.MovementDate,
    i.Line AS IssueLine, i.Description AS IssueDescription,
    i.M_InOutLine_ID, i.S_TimeExpenseLine_ID,
    --
    fa.C_AcctSchema_ID, fa.Account_ID, 
    fa.AmtSourceDr, fa.AmtSourceCr, fa.AmtAcctDr, fa.AmtAcctCr,
    --
    l.PlannedAmt-fa.AmtSourceDr+fa.AmtSourceCr AS LineMargin
  FROM C_ProjectLine l
  RIGHT OUTER JOIN C_ProjectIssue i ON (l.C_Project_ID=i.C_Project_ID AND l.C_ProjectIssue_ID=i.C_ProjectIssue_ID)  
  LEFT OUTER JOIN Fact_Acct fa ON (fa.AD_Table_ID=623 AND fa.Record_ID=i.C_ProjectIssue_ID AND fa.M_Locator_ID IS NULL)
WHERE l.C_Project_ID IS NULL
 AND l.C_ProjectIssue_ID IS NULL
;