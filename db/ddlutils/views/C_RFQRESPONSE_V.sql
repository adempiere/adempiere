CREATE OR REPLACE VIEW C_RFQRESPONSE_V
(C_RFQRESPONSE_ID, C_RFQ_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, 
 CREATED, CREATEDBY, UPDATED, UPDATEDBY, AD_LANGUAGE, 
 ORG_LOCATION_ID, TAXID, NAME, DESCRIPTION, HELP, 
 C_CURRENCY_ID, ISO_CODE, DATERESPONSE, DATEWORKSTART, DELIVERYDAYS, 
 C_BPARTNER_ID, BPNAME, BPNAME2, C_BPARTNER_LOCATION_ID, C_LOCATION_ID, 
 AD_USER_ID, TITLE, PHONE, CONTACTNAME)
AS 
SELECT rr.C_RfQResponse_ID, rr.C_RfQ_ID, 
    rr.AD_Client_ID, rr.AD_Org_ID, rr.IsActive, rr.Created, rr.CreatedBy, rr.Updated, rr.UpdatedBy,
	cast('en_US' as varchar) AS AD_Language,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
    r.Name, r.Description, r.Help,
    r.C_Currency_ID, c.ISO_Code,
    r.DateResponse, r.DateWorkStart, r.DeliveryDays,
    rr.C_BPartner_ID, bp.Name AS BPName, bp.Name2 AS BPName2,
    rr.C_BPartner_Location_ID, bpl.C_Location_ID,
    rr.AD_User_ID, bpc.Title, bpc.Phone,
    NULLIF (bpc.Name, bp.Name) AS ContactName
FROM C_RfQResponse rr
  INNER JOIN C_RfQ r ON (rr.C_RfQ_ID=r.C_RfQ_ID)
  INNER JOIN AD_OrgInfo oi ON (rr.AD_Org_ID=oi.AD_Org_ID)
  INNER JOIN C_Currency c ON (r.C_Currency_ID=c.C_Currency_ID)
  INNER JOIN C_BPartner bp ON (rr.C_BPartner_ID=bp.C_BPartner_ID)
  INNER JOIN C_BPartner_Location bpl ON (rr.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
  LEFT OUTER JOIN AD_User bpc ON (rr.AD_User_ID=bpc.AD_User_ID);



