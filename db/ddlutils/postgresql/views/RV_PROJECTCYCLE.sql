CREATE OR REPLACE VIEW RV_PROJECTCYCLE
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, C_CYCLE_ID, CYCLENAME, C_CURRENCY_ID, 
 C_CYCLESTEP_ID, CYCLESTEPNAME, SEQNO, RELATIVEWEIGHT, C_PHASE_ID, 
 PROJECTPHASENAME, C_PROJECTTYPE_ID, PROJECTTYPENAME, PROJECTVALUE, PROJECTNAME, 
 DESCRIPTION, NOTE, C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, AD_USER_ID, 
 POREFERENCE, SALESREP_ID, M_WAREHOUSE_ID, PROJECTCATEGORY, DATECONTRACT, 
 DATEFINISH, ISCOMMITMENT, ISCOMMITCEILING, COMMITTEDQTY, COMMITTEDAMT, 
 PLANNEDQTY, PLANNEDAMT, PLANNEDMARGINAMT, INVOICEDAMT, INVOICEDQTY, 
 PROJECTBALANCEAMT)
AS 
SELECT p.AD_Client_ID, p.AD_Org_ID, p.IsActive, p.Created,p.CreatedBy, p.Updated,p.UpdatedBy,
    c.C_Cycle_ID, c.Name AS CycleName, c.C_Currency_ID,
    cs.C_CycleStep_ID, cs.Name AS CycleStepName, cs.SeqNo, cs.RelativeWeight,
    pp.C_Phase_ID, pp.Name AS ProjectPhaseName, 
    pt.C_ProjectType_ID, pt.Name AS ProjectTypeName,
    p.Value AS ProjectValue, p.Name AS ProjectName, p.Description, p.Note, 
    p.C_BPartner_ID, p.C_BPartner_Location_ID, p.AD_User_ID, p.POReference,
    p.SalesRep_ID, p.M_Warehouse_ID, p.ProjectCategory,
    p.DateContract, p.DateFinish,
    p.IsCommitment, p.IsCommitCeiling, 
    p.CommittedQty*cs.RelativeWeight AS CommittedQty,
    currencyConvert (p.CommittedAmt, p.C_Currency_ID, c.C_Currency_ID, getdate(),0, p.AD_Client_ID, p.AD_Org_ID)*cs.RelativeWeight AS CommittedAmt,
    p.PlannedQty*cs.RelativeWeight AS PlannedQty, 
    currencyConvert (p.PlannedAmt, p.C_Currency_ID, c.C_Currency_ID, getdate(),0, p.AD_Client_ID, p.AD_Org_ID)*cs.RelativeWeight AS PlannedAmt, 
    currencyConvert (p.PlannedMarginAmt, p.C_Currency_ID, c.C_Currency_ID, getdate(),0, p.AD_Client_ID, p.AD_Org_ID)*cs.RelativeWeight AS PlannedMarginAmt,
    currencyConvert (p.InvoicedAmt, p.C_Currency_ID, c.C_Currency_ID, getdate(),0, p.AD_Client_ID, p.AD_Org_ID)*cs.RelativeWeight AS InvoicedAmt, 
    p.InvoicedQty*cs.RelativeWeight AS InvoicedQty,
    currencyConvert (p.ProjectBalanceAmt, p.C_Currency_ID, c.C_Currency_ID, getdate(),0, p.AD_Client_ID, p.AD_Org_ID)*cs.RelativeWeight AS ProjectBalanceAmt
FROM C_Cycle c
  INNER JOIN C_CycleStep cs ON (c.C_Cycle_ID=cs.C_Cycle_ID)
  INNER JOIN C_CyclePhase cp ON (cs.C_CycleStep_ID=cp.C_CycleStep_ID)
  INNER JOIN C_Phase pp ON (cp.C_Phase_ID=pp.C_Phase_ID)
  INNER JOIN C_Project p ON (cp.C_Phase_ID=p.C_Phase_ID)
  INNER JOIN C_ProjectType pt ON (p.C_ProjectType_ID=pt.C_ProjectType_ID);



