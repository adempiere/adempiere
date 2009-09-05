DROP VIEW C_PROJECT_HEADER_VT;

CREATE OR REPLACE VIEW C_PROJECT_HEADER_VT
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_PROJECT_ID, VALUE, 
 PROJECTNAME, DESCRIPTION, NOTE, ISSUMMARY, PROJECTCATEGORY, 
 ORG_LOCATION_ID, TAXID, C_PROJECTTYPE_ID, PROJECTTYPENAME, C_PHASE_ID, 
 PROJECTPHASENAME, SALESREP_ID, SALESREP_NAME, C_BPARTNER_ID, BPVALUE, 
 BPTAXID, NAICS, DUNS, BPGREETING, NAME, 
 NAME2, BPCONTACTGREETING, TITLE, PHONE, CONTACTNAME, 
 C_LOCATION_ID, REFERENCENO, PAYMENTTERM, PAYMENTTERMNOTE, POREFERENCE, 
 C_CURRENCY_ID, M_PRICELIST_VERSION_ID, C_CAMPAIGN_ID, PLANNEDAMT, PLANNEDQTY, 
 PLANNEDMARGINAMT, INVOICEDAMT, INVOICEDQTY, PROJECTBALANCEAMT, ISCOMMITMENT, 
 COMMITTEDAMT, COMMITTEDQTY, DATECONTRACT, DATEFINISH, ISCOMMITCEILING, 
 M_WAREHOUSE_ID, LOGO_ID)
AS 
SELECT p.AD_Client_ID, p.AD_Org_ID, p.IsActive, p.Created, p.CreatedBy, p.Updated, p.UpdatedBy, 
	pt.AD_Language, p.C_Project_ID,
    p.Value, p.Name AS ProjectName, p.Description, p.Note, p.IsSummary, p.ProjectCategory,
    oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
    p.C_ProjectType_ID, pjt.Name AS ProjectTypeName, p.C_Phase_ID, pjp.Name AS ProjectPhaseName,
	p.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	p.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID,
	bp.ReferenceNo,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
    p.POReference,
    p.C_Currency_ID, p.M_PriceList_Version_ID,
    p.C_Campaign_ID,
    p.PlannedAmt, p.PlannedQty, p.PlannedMarginAmt, p.InvoicedAmt, p.InvoicedQty, p.ProjectBalanceAmt,
    p.IsCommitment, p.CommittedAmt, p.CommittedQty, p.DateContract, p.DateFinish, p.IsCommitCeiling,
    p.M_Warehouse_ID, COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM C_Project p
	LEFT OUTER JOIN C_BPartner bp ON (p.C_BPartner_ID=bp.C_BPartner_ID)
	INNER JOIN AD_OrgInfo oi ON (p.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (p.AD_Client_ID=oi.AD_Client_ID)
	LEFT OUTER JOIN C_PaymentTerm_Trl pt ON (p.C_PaymentTerm_ID=pt.C_PaymentTerm_ID)
    LEFT OUTER JOIN C_ProjectType pjt ON (p.C_ProjectType_ID=pjt.C_ProjectType_ID)
    LEFT OUTER JOIN C_Phase pjp ON (p.C_Phase_ID=pjp.C_Phase_ID)
	LEFT OUTER JOIN AD_User u ON (p.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	LEFT OUTER JOIN AD_User bpc ON (p.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	LEFT OUTER JOIN C_BPartner_Location bpl ON (p.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID);



