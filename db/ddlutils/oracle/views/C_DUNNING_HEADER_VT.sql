CREATE OR REPLACE VIEW C_DUNNING_HEADER_VT
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_DUNNINGRUN_ID, C_DUNNINGRUNENTRY_ID, 
 DUNNINGDATE, PRINTNAME, DOCUMENTNOTE, C_BPARTNER_ID, BPVALUE, 
 BPTAXID, NAICS, DUNS, ORG_LOCATION_ID, TAXID, 
 SALESREP_ID, SALESREP_NAME, BPGREETING, NAME, NAME2, 
 BPCONTACTGREETING, TITLE, PHONE, CONTACTNAME, C_LOCATION_ID, 
 REFERENCENO, POSTAL, AMT, QTY, NOTE, LOGO_ID)
AS 
SELECT dr.AD_Client_ID, dr.AD_Org_ID, dr.IsActive, dr.Created, dr.CreatedBy, dr.Updated, dr.UpdatedBy, 
	dlt.AD_Language, dr.C_DunningRun_ID, C_DunningRunEntry_ID,
    dr.DunningDate, dlt.PrintName, dlt.Note AS DocumentNote,
    dre.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	dre.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, bp.ReferenceNo, l.Postal || l.Postal_Add AS Postal,
    dre.Amt, dre.Qty, dre.Note, COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM C_DunningRun dr
    INNER JOIN C_DunningRunEntry dre ON (dr.C_DunningRun_ID=dre.C_DunningRun_ID)
    INNER JOIN C_DunningLevel dl ON (dre.C_DunningLevel_ID=dl.C_DunningLevel_ID)
    INNER JOIN C_DunningLevel_Trl dlt ON (dl.C_DunningLevel_ID=dlt.C_DunningLevel_ID)
	INNER JOIN C_BPartner bp ON (dre.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID
        AND dlt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (dre.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (dre.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID
        AND dlt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (dr.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (dr.AD_Client_ID=ci.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (dre.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID);



