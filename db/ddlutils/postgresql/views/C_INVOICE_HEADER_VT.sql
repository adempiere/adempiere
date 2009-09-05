DROP VIEW C_INVOICE_HEADER_VT;

CREATE OR REPLACE VIEW C_INVOICE_HEADER_VT
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_INVOICE_ID, ISSOTRX, 
 DOCUMENTNO, DOCSTATUS, C_DOCTYPE_ID, C_BPARTNER_ID, BPVALUE, 
 BPTAXID, NAICS, DUNS, ORG_LOCATION_ID, TAXID, 
 DOCUMENTTYPE, DOCUMENTTYPENOTE, C_ORDER_ID, SALESREP_ID, SALESREP_NAME, 
 DATEINVOICED, BPGREETING, NAME, NAME2, BPCONTACTGREETING, 
 TITLE, PHONE, CONTACTNAME, C_LOCATION_ID, REFERENCENO, 
 POSTAL, DESCRIPTION, POREFERENCE, DATEORDERED, C_CURRENCY_ID, 
 PAYMENTTERM, PAYMENTTERMNOTE, C_CHARGE_ID, CHARGEAMT, TOTALLINES, 
 GRANDTOTAL, AMTINWORDS, M_PRICELIST_ID, ISTAXINCLUDED, C_CAMPAIGN_ID, 
 C_PROJECT_ID, C_ACTIVITY_ID, ISPAID, LOGO_ID)
AS 
SELECT i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy, 
	dt.AD_Language,
	i.C_Invoice_ID, i.IsSOTrx, i.DocumentNo, i.DocStatus,  i.C_DocType_ID,
	i.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	i.C_Order_ID, i.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	i.DateInvoiced,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, bp.ReferenceNo, l.Postal || l.Postal_Add AS Postal,
	i.Description,
	i.POReference,
	i.DateOrdered,
	i.C_Currency_ID,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
	i.C_Charge_ID, i.ChargeAmt,
	i.TotalLines,
	i.GrandTotal, i.GrandTotal AS AmtInWords,
	i.M_PriceList_ID,
	i.IsTaxIncluded,
	i.C_Campaign_ID,
	i.C_Project_ID,
	i.C_Activity_ID,
	i.IsPaid, COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM C_Invoice i
	INNER JOIN C_DocType_Trl dt ON (i.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN C_PaymentTerm_Trl pt ON (i.C_PaymentTerm_ID=pt.C_PaymentTerm_ID AND dt.AD_Language=pt.AD_Language)
	INNER JOIN C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID AND dt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (i.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (i.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID AND dt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (i.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (i.AD_Client_ID=oi.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (i.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID);



