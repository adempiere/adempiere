CREATE OR REPLACE VIEW RV_C_INVOICE
(C_INVOICE_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, 
 CREATEDBY, UPDATED, UPDATEDBY, ISSOTRX, DOCUMENTNO, 
 DOCSTATUS, DOCACTION, ISPRINTED, ISDISCOUNTPRINTED, PROCESSING, 
 PROCESSED, ISTRANSFERRED, ISPAID, C_DOCTYPE_ID, C_DOCTYPETARGET_ID, 
 C_ORDER_ID, DESCRIPTION, ISAPPROVED, SALESREP_ID, DATEINVOICED, 
 DATEPRINTED, DATEACCT, C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, AD_USER_ID, 
 C_BP_GROUP_ID, POREFERENCE, DATEORDERED, C_CURRENCY_ID, C_CONVERSIONTYPE_ID, 
 PAYMENTRULE, C_PAYMENTTERM_ID, M_PRICELIST_ID, C_CAMPAIGN_ID, C_PROJECT_ID, 
 C_ACTIVITY_ID, ISPAYSCHEDULEVALID, INVOICECOLLECTIONTYPE, C_COUNTRY_ID, C_REGION_ID, 
 POSTAL, CITY, C_CHARGE_ID, CHARGEAMT, TOTALLINES, 
 GRANDTOTAL, MULTIPLIER)
AS 
SELECT i.C_Invoice_ID,
	i.AD_Client_ID,i.AD_Org_ID,i.IsActive,i.Created,i.CreatedBy,i.Updated,i.UpdatedBy,
	i.IsSOTrx, i.DocumentNo, i.DocStatus, i.DocAction,
	i.IsPrinted, i.IsDiscountPrinted, i.Processing, i.Processed, i.IsTransferred, i.IsPaid,
	i.C_DocType_ID, i.C_DocTypeTarget_ID, i.C_Order_ID, i.Description, i.IsApproved,
	i.SalesRep_ID, i.DateInvoiced, i.DatePrinted, i.DateAcct,
	i.C_BPartner_ID, i.C_BPartner_Location_ID, i.AD_User_ID, b.C_BP_Group_ID,
	i.POReference, i.DateOrdered, i.C_Currency_ID, C_ConversionType_ID, i.PaymentRule, i.C_PaymentTerm_ID,
	i.M_PriceList_ID, i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID,
    i.IsPayScheduleValid, i.InvoiceCollectionType,
    loc.C_Country_ID, loc.C_Region_ID, loc.Postal, loc.City,
	--	Amounts
	i.C_Charge_ID,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.ChargeAmt*-1 ELSE i.ChargeAmt END AS ChargeAmt,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.TotalLines*-1 ELSE i.TotalLines END AS TotalLines,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.GrandTotal*-1 ELSE i.GrandTotal END AS GrandTotal,
	CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END AS Multiplier
FROM  C_Invoice i
 INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
 INNER JOIN C_BPartner b ON (i.C_BPartner_ID=b.C_BPartner_ID)
 INNER JOIN C_BPartner_Location bpl ON (i.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
 INNER JOIN C_Location loc ON (bpl.C_Location_ID=loc.C_Location_ID);



