CREATE OR REPLACE VIEW C_ORDER_HEADER_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_ORDER_ID, ISSOTRX, 
 DOCUMENTNO, DOCSTATUS, C_DOCTYPE_ID, C_BPARTNER_ID, BPVALUE, 
 BPTAXID, NAICS, DUNS, ORG_LOCATION_ID, TAXID, 
 M_WAREHOUSE_ID, WAREHOUSE_LOCATION_ID, DOCUMENTTYPE, DOCUMENTTYPENOTE, SALESREP_ID, 
 SALESREP_NAME, DATEORDERED, DATEPROMISED, BPGREETING, NAME, 
 NAME2, BPCONTACTGREETING, TITLE, PHONE, CONTACTNAME, 
 C_LOCATION_ID, POSTAL, REFERENCENO, BILL_BPARTNER_ID, BILL_LOCATION_ID, 
 BILL_USER_ID, BILL_BPVALUE, BILL_BPTAXID, BILL_NAME, BILL_NAME2, 
 BILL_TITLE, BILL_PHONE, BILL_CONTACTNAME, BILL_C_LOCATION_ID, DESCRIPTION, 
 POREFERENCE, C_CURRENCY_ID, PAYMENTTERM, PAYMENTTERMNOTE, C_CHARGE_ID, 
 CHARGEAMT, TOTALLINES, GRANDTOTAL, AMTINWORDS, M_PRICELIST_ID, 
 ISTAXINCLUDED, VOLUME, WEIGHT, C_CAMPAIGN_ID, C_PROJECT_ID, 
 C_ACTIVITY_ID, M_SHIPPER_ID, DELIVERYRULE, DELIVERYVIARULE, PRIORITYRULE, 
 INVOICERULE, LOGO_ID)
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	cast('en_US' as varchar2(6)) AS AD_Language,
	o.C_Order_ID, o.IsSOTrx, o.DocumentNo, o.DocStatus,	 o.C_DocType_ID,
	o.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
    o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	o.DateOrdered, o.DatePromised,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, l.Postal || l.Postal_Add AS Postal,
	bp.ReferenceNo,
    --  Bill to
    o.Bill_BPartner_ID, o.Bill_Location_ID, o.Bill_User_ID,
    bbp.Value AS Bill_BPValue, bbp.TaxID AS Bill_BPTaxID,
    bbp.Name AS Bill_Name, bbp.Name2 AS Bill_Name2,
    bbpc.Title AS Bill_Title, bbpc.Phone AS Bill_Phone,
    NULLIF (bbpc.Name, bbp.Name) AS Bill_ContactName,
    bbpl.C_Location_ID AS Bill_C_Location_ID,
	o.Description,
	o.POReference,
	o.C_Currency_ID,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
	o.C_Charge_ID, o.ChargeAmt,
	o.TotalLines,
	o.GrandTotal, o.GrandTotal AS AmtInWords,
	o.M_PriceList_ID,
	o.IsTaxIncluded, o.Volume, o.Weight,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	o.M_Shipper_ID, o.DeliveryRule, o.DeliveryViaRule, o.PriorityRule, o.InvoiceRule, COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM C_Order o
	INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)
    INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN C_PaymentTerm pt ON (o.C_PaymentTerm_ID=pt.C_PaymentTerm_ID)
	INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (o.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (o.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (o.AD_Client_ID=ci.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (o.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
    INNER JOIN C_BPartner bbp ON (o.Bill_BPartner_ID=bbp.C_BPartner_ID)
	INNER JOIN C_BPartner_Location bbpl ON (o.Bill_Location_ID=bbpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bbpc ON (o.Bill_User_ID=bbpc.AD_User_ID);



