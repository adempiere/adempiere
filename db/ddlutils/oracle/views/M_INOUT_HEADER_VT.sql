CREATE OR REPLACE VIEW M_INOUT_HEADER_VT
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, M_INOUT_ID, ISSOTRX, 
 DOCUMENTNO, DOCSTATUS, C_DOCTYPE_ID, C_BPARTNER_ID, BPVALUE, 
 BPTAXID, NAICS, DUNS, ORG_LOCATION_ID, TAXID, 
 M_WAREHOUSE_ID, WAREHOUSE_LOCATION_ID, DOCUMENTTYPE, DOCUMENTTYPENOTE, C_ORDER_ID, 
 PHONE, MOVEMENTDATE, MOVEMENTTYPE, BPGREETING, NAME, 
 NAME2, BPCONTACTGREETING, TITLE, CONTACTNAME, C_LOCATION_ID, 
 POSTAL, REFERENCENO, DESCRIPTION, POREFERENCE, DATEORDERED, 
 VOLUME, WEIGHT, M_SHIPPER_ID, DELIVERYRULE, DELIVERYVIARULE, 
 PRIORITYRULE, LOGO_ID)
AS 
SELECT io.AD_Client_ID, io.AD_Org_ID, io.IsActive, io.Created, io.CreatedBy, io.Updated, io.UpdatedBy,
	dt.AD_Language,
	io.M_InOut_ID, io.IsSOTrx, io.DocumentNo, io.DocStatus,	 io.C_DocType_ID,
	io.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	io.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	io.C_Order_ID, bpc.Phone,
	io.MovementDate, io.MovementType,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, l.Postal || l.Postal_Add AS Postal,
	bp.ReferenceNo,
	io.Description,
	io.POReference,
	io.DateOrdered, io.Volume, io.Weight,
	io.M_Shipper_ID, io.DeliveryRule, io.DeliveryViaRule, io.PriorityRule, COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM M_InOut io
	INNER JOIN C_DocType_Trl dt ON (io.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN C_BPartner bp ON (io.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID AND dt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (io.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (io.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID AND dt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (io.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (io.AD_Client_ID=ci.AD_Client_ID)
	INNER JOIN M_Warehouse wh ON (io.M_Warehouse_ID=wh.M_Warehouse_ID);



