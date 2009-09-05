DROP VIEW C_DUNNING_HEADER_V;

CREATE OR REPLACE VIEW C_DUNNING_HEADER_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_DUNNINGRUN_ID, C_DUNNINGRUNENTRY_ID, 
 DUNNINGDATE, PRINTNAME, DOCUMENTNOTE, C_BPARTNER_ID, BPVALUE, 
 BPTAXID, NAICS, DUNS, ORG_LOCATION_ID, TAXID, 
 SALESREP_ID, SALESREP_NAME, BPGREETING, NAME, NAME2, 
 BPCONTACTGREETING, TITLE, PHONE, CONTACTNAME, C_LOCATION_ID, 
 REFERENCENO, POSTAL, AMT, QTY, NOTE, LOGO_ID)
AS 
SELECT dr.AD_Client_ID, dr.AD_Org_ID, dr.IsActive, dr.Created, dr.CreatedBy, dr.Updated, dr.UpdatedBy, 
	cast('en_US' as varchar) AS AD_Language, dr.C_DunningRun_ID, C_DunningRunEntry_ID,
    dr.DunningDate, dl.PrintName, dl.Note AS DocumentNote,
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
    INNER JOIN C_DunningLevel dl ON (dr.C_DunningLevel_ID=dl.C_DunningLevel_ID)
    INNER JOIN C_DunningRunEntry dre ON (dr.C_DunningRun_ID=dre.C_DunningRun_ID)
	INNER JOIN C_BPartner bp ON (dre.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (dre.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (dre.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (dr.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (dr.AD_Client_ID=oi.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (dre.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID);





DROP VIEW C_DUNNING_HEADER_VT;

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
    INNER JOIN C_DunningLevel dl ON (dr.C_DunningLevel_ID=dl.C_DunningLevel_ID)
    INNER JOIN C_DunningLevel_Trl dlt ON (dl.C_DunningLevel_ID=dlt.C_DunningLevel_ID)
    INNER JOIN C_DunningRunEntry dre ON (dr.C_DunningRun_ID=dre.C_DunningRun_ID)
	INNER JOIN C_BPartner bp ON (dre.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID
        AND dlt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (dre.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (dre.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID
        AND dlt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (dr.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (dr.AD_Client_ID=oi.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (dre.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID);





DROP VIEW C_INVOICE_HEADER_V;

CREATE OR REPLACE VIEW C_INVOICE_HEADER_V
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
	cast('en_US' as varchar) AS AD_Language,
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
	INNER JOIN C_DocType dt ON (i.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN C_PaymentTerm pt ON (i.C_PaymentTerm_ID=pt.C_PaymentTerm_ID)
	INNER JOIN C_BPartner bp ON (i.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (i.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (i.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (i.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (i.AD_Client_ID=oi.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (i.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID);





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





DROP VIEW C_ORDER_HEADER_V;

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
	cast('en_US' as varchar) AS AD_Language,
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
	INNER JOIN AD_ClientInfo ci ON (o.AD_Client_ID=oi.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (o.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
    INNER JOIN C_BPartner bbp ON (o.Bill_BPartner_ID=bbp.C_BPartner_ID)
	INNER JOIN C_BPartner_Location bbpl ON (o.Bill_Location_ID=bbpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bbpc ON (o.Bill_User_ID=bbpc.AD_User_ID);





DROP VIEW C_ORDER_HEADER_VT;

CREATE OR REPLACE VIEW C_ORDER_HEADER_VT
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
	dt.AD_Language,
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
	INNER JOIN C_DocType_Trl dt ON (o.C_DocType_ID=dt.C_DocType_ID)
    INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN C_PaymentTerm_Trl pt ON (o.C_PaymentTerm_ID=pt.C_PaymentTerm_ID AND dt.AD_Language=pt.AD_Language)
	INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID AND dt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (o.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (o.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID AND dt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (o.AD_Client_ID=oi.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (o.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
    INNER JOIN C_BPartner bbp ON (o.Bill_BPartner_ID=bbp.C_BPartner_ID)
	INNER JOIN C_BPartner_Location bbpl ON (o.Bill_Location_ID=bbpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bbpc ON (o.Bill_User_ID=bbpc.AD_User_ID);





DROP VIEW C_PROJECT_HEADER_V;

CREATE OR REPLACE VIEW C_PROJECT_HEADER_V
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
	cast('en_US' as varchar) AS AD_Language, p.C_Project_ID,
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
    LEFT OUTER JOIN C_ProjectType pjt ON (p.C_ProjectType_ID=pjt.C_ProjectType_ID)
    LEFT OUTER JOIN C_Phase pjp ON (p.C_Phase_ID=pjp.C_Phase_ID)
	LEFT OUTER JOIN AD_User u ON (p.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg on (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	LEFT OUTER JOIN AD_User bpc ON (p.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg on (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	LEFT OUTER JOIN C_BPartner_Location bpl ON (p.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN C_PaymentTerm pt ON (p.C_PaymentTerm_ID=pt.C_PaymentTerm_ID);





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





DROP VIEW DD_ORDER_HEADER_V;

CREATE OR REPLACE VIEW DD_ORDER_HEADER_V
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	cast('en_US' as varchar) AS AD_Language,
	o.DD_Order_ID,o.C_Order_ID, o.IsSOTrx, o.DocumentNo, o.DocStatus,	 o.C_DocType_ID,
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
	o.Description,
	o.POReference,
	o.C_Charge_ID, o.ChargeAmt,
	o.Volume, o.Weight,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	o.M_Shipper_ID, o.DeliveryRule, o.DeliveryViaRule, o.PriorityRule, COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM DD_Order o
	INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)
    INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (o.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (o.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (o.AD_Client_ID=oi.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (o.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID);






DROP VIEW DD_ORDER_HEADER_VT;

CREATE OR REPLACE VIEW DD_ORDER_HEADER_VT
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	dt.AD_Language,o.DD_Order_ID,
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
	o.Description,
	o.POReference,
	o.C_Charge_ID, o.ChargeAmt,
	o.Volume, o.Weight,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	o.M_Shipper_ID, o.DeliveryRule, o.DeliveryViaRule, o.PriorityRule, COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM DD_Order o
	INNER JOIN C_DocType_Trl dt ON (o.C_DocType_ID=dt.C_DocType_ID)
    INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID AND dt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (o.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (o.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID AND dt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (o.AD_Client_ID=oi.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (o.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID);






DROP VIEW M_INOUT_HEADER_V;

CREATE OR REPLACE VIEW M_INOUT_HEADER_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, M_INOUT_ID, ISSOTRX, 
 DOCUMENTNO, DOCSTATUS, C_DOCTYPE_ID, C_BPARTNER_ID, BPVALUE, 
 BPTAXID, NAICS, DUNS, ORG_LOCATION_ID, TAXID, 
 M_WAREHOUSE_ID, WAREHOUSE_LOCATION_ID, DOCUMENTTYPE, DOCUMENTTYPENOTE, C_ORDER_ID, 
 MOVEMENTDATE, MOVEMENTTYPE, BPGREETING, NAME, NAME2, 
 BPCONTACTGREETING, TITLE, PHONE, CONTACTNAME, C_LOCATION_ID, 
 POSTAL, REFERENCENO, DESCRIPTION, POREFERENCE, DATEORDERED, 
 VOLUME, WEIGHT, M_SHIPPER_ID, DELIVERYRULE, DELIVERYVIARULE, 
 PRIORITYRULE, LOGO_ID)
AS 
SELECT io.AD_Client_ID, io.AD_Org_ID, io.IsActive, io.Created, io.CreatedBy, io.Updated, io.UpdatedBy,
	cast('en_US' as varchar) AS AD_Language,
	io.M_InOut_ID, io.IsSOTrx, io.DocumentNo, io.DocStatus,	 io.C_DocType_ID,
	io.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	io.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	io.C_Order_ID, 
	io.MovementDate, io.MovementType,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, l.Postal || l.Postal_Add AS Postal,
	bp.ReferenceNo,
	io.Description,
	io.POReference,
	io.DateOrdered, io.Volume, io.Weight,
	io.M_Shipper_ID, io.DeliveryRule, io.DeliveryViaRule, io.PriorityRule, COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM M_InOut io
	INNER JOIN C_DocType dt ON (io.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN C_BPartner bp ON (io.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (io.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (io.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (io.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (io.AD_Client_ID=oi.AD_Client_ID)
	INNER JOIN M_Warehouse wh ON (io.M_Warehouse_ID=wh.M_Warehouse_ID);





DROP VIEW M_INOUT_HEADER_VT;

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
	INNER JOIN AD_ClientInfo ci ON (io.AD_Client_ID=oi.AD_Client_ID)
	INNER JOIN M_Warehouse wh ON (io.M_Warehouse_ID=wh.M_Warehouse_ID);





DROP VIEW PP_Order_BOM_Header_v;

CREATE OR REPLACE VIEW PP_Order_BOM_Header_v
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	cast('en_US' as varchar) AS AD_Language,
	o.PP_Order_ID, o.DocumentNo, o.DocStatus,o.C_DocType_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
	o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	d.PrintName AS DocumentType, d.DocumentNote AS DocumentTypeNote,
	o.Planner_ID, u.Name AS SalesRep_Name,o.DateStart, o.DateStartSchedule,o.FloatAfter, o.FloatBefored, o.Line, o.Lot, o.SerNo, 
	--o.M_Product_ID,
	--o.M_AttributeSetInstance_ID,
	o.C_UOM_ID,o.S_Resource_ID,o.PP_Product_BOM_ID,o.AD_Workflow_ID, o.Assay, o.C_OrderLine_ID, o.PriorityRule , 
	o.QtyBatchSize , o.QtyBatchs, o.QtyDelivered, o.QtyEntered, o.QtyOrdered, 
	o.DateConfirm,o.DateDelivered,o.DateFinish, o.DateFinishSchedule,o.DateOrdered, o.DatePromised,o.QtyReject, o.QtyReserved , o.QtyScrap , o.Yield ,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	--ob.PP_Product_BOM_ID,
	ob.BOMType,ob.BOMUse, ob.Description , ob.Help , ob.M_AttributeSetInstance_ID , ob.M_Product_ID, ob.Name , ob.Revision, ob.ValidFrom , ob.ValidTo,
	COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM PP_Order o
	INNER JOIN C_DocType d ON (o.C_DocType_ID=d.C_DocType_ID)
	INNER JOIN PP_Order_BOM ob ON (ob.PP_Order_ID=o.PP_Order_ID)
	INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (o.AD_Client_ID=oi.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (o.Planner_ID=u.AD_User_ID);


DROP VIEW PP_Order_BOM_Header_vt;

CREATE OR REPLACE VIEW PP_Order_BOM_Header_vt
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	obt.AD_Language,
	o.PP_Order_ID, o.DocumentNo, o.DocStatus,o.C_DocType_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
	o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.Planner_ID, u.Name AS SalesRep_Name,o.DateStart, o.DateStartSchedule,o.FloatAfter, o.FloatBefored, o.Line, o.Lot, o.SerNo, 
	--o.M_Product_ID,
	--o.M_AttributeSetInstance_ID,
	o.C_UOM_ID,o.S_Resource_ID,o.PP_Product_BOM_ID,o.AD_Workflow_ID, o.Assay, o.C_OrderLine_ID, o.PriorityRule , 
	o.QtyBatchSize , o.QtyBatchs, o.QtyDelivered, o.QtyEntered, o.QtyOrdered, 
	o.DateConfirm,o.DateDelivered,o.DateFinish, o.DateFinishSchedule,o.DateOrdered, o.DatePromised,o.QtyReject, o.QtyReserved , o.QtyScrap , o.Yield ,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	--ob.PP_Product_BOM_ID,
	ob.BOMType,ob.BOMUse, obt.Description , obt.Help , ob.M_AttributeSetInstance_ID , ob.M_Product_ID, obt.Name , ob.Revision, ob.ValidFrom , ob.ValidTo,
	COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM PP_Order o
	INNER JOIN C_DocType_Trl dt ON (o.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN PP_Order_BOM ob ON (ob.PP_Order_ID=o.PP_Order_ID)
	INNER JOIN PP_Order_BOM_Trl obt ON (obt.PP_Order_BOM_ID=ob.PP_Order_BOM_ID)
	INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (o.AD_Client_ID=oi.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (o.Planner_ID=u.AD_User_ID);


DROP VIEW PP_Order_Header_v;	

CREATE OR REPLACE VIEW PP_Order_Header_v
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	cast('en_US' as varchar) AS AD_Language,
	o.PP_Order_ID, o.DocumentNo, o.DocStatus,o.C_DocType_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
	o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.Planner_ID, u.Name AS SalesRep_Name,o.DateStart, o.DateStartSchedule,o.FloatAfter, o.FloatBefored, o.Line, o.Lot, o.SerNo, 
 	o.Description,
	o.M_Product_ID,o.M_AttributeSetInstance_ID,o.C_UOM_ID,o.S_Resource_ID,o.PP_Product_BOM_ID,o.AD_Workflow_ID, o.Assay, o.C_OrderLine_ID, o.PriorityRule , 
	o.QtyBatchSize , o.QtyBatchs, o.QtyDelivered, o.QtyEntered, o.QtyOrdered, 
	o.DateConfirm,o.DateDelivered,o.DateFinish, o.DateFinishSchedule,o.DateOrdered, o.DatePromised,o.QtyReject, o.QtyReserved , o.QtyScrap , o.Yield ,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID, o.User1_ID , o.User2_ID , o.AD_OrgTrx_ID ,o.C_DocTypeTarget_ID,o.ScheduleType , o.IsApproved , o.DocAction , o.Posted , o.IsPrinted, o.OrderType,
	COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM PP_Order o
	INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (o.AD_Client_ID=oi.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (o.Planner_ID=u.AD_User_ID);


		


DROP VIEW PP_Order_Header_vt;	

CREATE OR REPLACE VIEW PP_Order_Header_vt
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	dt.AD_Language,
	o.PP_Order_ID, o.DocumentNo, o.DocStatus,o.C_DocType_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
	o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.Planner_ID, u.Name AS SalesRep_Name,o.DateStart, o.DateStartSchedule,o.FloatAfter, o.FloatBefored, o.Line, o.Lot, o.SerNo, 
 	o.Description,
	o.M_Product_ID,o.M_AttributeSetInstance_ID,o.C_UOM_ID,o.S_Resource_ID,o.PP_Product_BOM_ID,o.AD_Workflow_ID, o.Assay, o.C_OrderLine_ID, o.PriorityRule , 
	o.QtyBatchSize , o.QtyBatchs, o.QtyDelivered, o.QtyEntered, o.QtyOrdered, 
	o.DateConfirm,o.DateDelivered,o.DateFinish, o.DateFinishSchedule,o.DateOrdered, o.DatePromised,o.QtyReject, o.QtyReserved , o.QtyScrap , o.Yield ,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID, o.User1_ID , o.User2_ID , o.AD_OrgTrx_ID ,o.C_DocTypeTarget_ID,o.ScheduleType , o.IsApproved , o.DocAction , o.Posted , o.IsPrinted, o.OrderType,
	COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM PP_Order o
	INNER JOIN C_DocType_trl dt ON (o.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (o.AD_Client_ID=oi.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (o.Planner_ID=u.AD_User_ID);


		


DROP VIEW PP_Order_Workflow_Header_v;

CREATE OR REPLACE VIEW PP_Order_Workflow_Header_v
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	cast('en_US' as varchar) AS AD_Language,
	o.PP_Order_ID, 
	--o.DocumentNo,
	o.DocStatus,
	o.C_DocType_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
	o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	d.PrintName AS DocumentType, d.DocumentNote AS DocumentTypeNote,
	o.Planner_ID, u.Name AS SalesRep_Name,o.DateStart, o.DateStartSchedule,o.FloatAfter, o.FloatBefored, o.Line, o.Lot, o.SerNo, 
	--o.M_Product_ID,
	--o.M_AttributeSetInstance_ID,
	o.C_UOM_ID,o.S_Resource_ID,o.PP_Product_BOM_ID,o.AD_Workflow_ID, o.Assay, o.C_OrderLine_ID, o.PriorityRule , 
	o.QtyBatchs, o.QtyDelivered, o.QtyEntered, o.QtyOrdered, 
	o.DateConfirm,o.DateDelivered,o.DateFinish, o.DateFinishSchedule,o.DateOrdered, o.DatePromised,o.QtyReject, o.QtyReserved , o.QtyScrap ,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	-- Order Workflow Field
	ow.Name ,ow.Description ,ow.Help,
	ow.Author, ow.Cost, ow.DocumentNo ,  ow.Duration, ow.DurationUnit , ow.Version, ow.ValidFrom , ow.ValidTo ,
	ow.MovingTime, ow.OverlapUnits , ow.PublishStatus , ow.QueuingTime , ow.SetupTime , ow.UnitsCycles,
	ow.WaitingTime , ow.WorkflowType, ow.WorkingTime , ow.Yield, COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM PP_Order o
	INNER JOIN PP_Order_Workflow ow ON (ow.PP_Order_ID=o.PP_Order_ID)
	INNER JOIN C_DocType d ON (o.C_DocType_ID=d.C_DocType_ID)
	INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (o.AD_Client_ID=oi.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (o.Planner_ID=u.AD_User_ID);


DROP VIEW PP_Order_Workflow_Header_vt;

CREATE OR REPLACE VIEW PP_Order_Workflow_Header_vt
AS 
SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	owt.AD_Language,
	o.PP_Order_ID, 
	--o.DocumentNo,
	o.DocStatus,
	o.C_DocType_ID,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
	o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.Planner_ID, u.Name AS SalesRep_Name,o.DateStart, o.DateStartSchedule,o.FloatAfter, o.FloatBefored, o.Line, o.Lot, o.SerNo, 
	o.C_UOM_ID,o.PP_Product_BOM_ID, o.Assay, o.C_OrderLine_ID, o.PriorityRule , 
	o.QtyBatchSize , o.QtyBatchs, o.QtyDelivered, o.QtyEntered, o.QtyOrdered, 
	o.DateConfirm,o.DateDelivered,o.DateFinish, o.DateFinishSchedule,o.DateOrdered, o.DatePromised,o.QtyReject, o.QtyReserved , o.QtyScrap , o.S_Resource_ID ,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,	
	--Trl Field 
	owt.Name , owt.Description, owt.Help,
	-- Order Workflow Field
	ow.Author, ow.Cost, ow.DocumentNo  , ow.Duration, ow.DurationUnit, ow.Version, ow.ValidFrom , ow.ValidTo ,
	ow.MovingTime, ow.OverlapUnits, ow.AD_Workflow_ID, ow.PublishStatus , ow.QueuingTime , ow.SetupTime , ow.UnitsCycles,
	ow.WaitingTime , ow.WorkflowType ,  ow.WorkingTime , ow.Yield, COALESCE(oi.Logo_ID, ci.Logo_ID) AS Logo_ID
FROM PP_Order o
	INNER JOIN PP_Order_Workflow ow ON (ow.PP_Order_ID=o.PP_Order_ID)
	INNER JOIN PP_Order_Workflow_Trl owt ON (owt.PP_Order_Workflow_ID=ow.PP_Order_Workflow_ID)
	INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN AD_ClientInfo ci ON (o.AD_Client_ID=oi.AD_Client_ID)
	LEFT OUTER JOIN AD_User u ON (o.Planner_ID=u.AD_User_ID);


