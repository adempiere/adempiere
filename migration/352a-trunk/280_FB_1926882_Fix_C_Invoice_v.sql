CREATE OR REPLACE VIEW C_INVOICE_V
(C_INVOICE_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, 
 CREATEDBY, UPDATED, UPDATEDBY, ISSOTRX, DOCUMENTNO, 
 DOCSTATUS, DOCACTION, PROCESSING, PROCESSED, C_DOCTYPE_ID, 
 C_DOCTYPETARGET_ID, C_ORDER_ID, DESCRIPTION, ISAPPROVED, ISTRANSFERRED, 
 SALESREP_ID, DATEINVOICED, DATEPRINTED, DATEACCT, C_BPARTNER_ID, 
 C_BPARTNER_LOCATION_ID, AD_USER_ID, POREFERENCE, DATEORDERED, C_CURRENCY_ID, 
 C_CONVERSIONTYPE_ID, PAYMENTRULE, C_PAYMENTTERM_ID, C_CHARGE_ID, M_PRICELIST_ID, 
 C_CAMPAIGN_ID, C_PROJECT_ID, C_ACTIVITY_ID, ISPRINTED, ISDISCOUNTPRINTED, 
 ISPAID, ISINDISPUTE, ISPAYSCHEDULEVALID, C_INVOICEPAYSCHEDULE_ID, INVOICECOLLECTIONTYPE, 
 CHARGEAMT, TOTALLINES, GRANDTOTAL, MULTIPLIER, MULTIPLIERAP, 
 DOCBASETYPE, DUEDATE
 )
AS 
SELECT i.C_Invoice_ID, i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy,
    i.IsSOTrx, i.DocumentNo, i.DocStatus, i.DocAction, i.Processing, i.Processed, i.C_DocType_ID,
    i.C_DocTypeTarget_ID, i.C_Order_ID, i.Description, i.IsApproved, i.IsTransferred,
    i.SalesRep_ID, i.DateInvoiced, i.DatePrinted, i.DateAcct, i.C_BPartner_ID, i.C_BPartner_Location_ID,
    i.AD_User_ID, i.POReference, i.DateOrdered, i.C_Currency_ID, i.C_ConversionType_ID, i.PaymentRule,
    i.C_PaymentTerm_ID, i.C_Charge_ID, i.M_PriceList_ID, i.C_Campaign_ID, i.C_Project_ID,
    i.C_Activity_ID, i.IsPrinted, i.IsDiscountPrinted, i.IsPaid, i.IsInDispute,
    i.IsPayScheduleValid, cast(null as number) AS C_InvoicePaySchedule_ID, i.InvoiceCollectionType,
    (CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.ChargeAmt*-1 ELSE i.ChargeAmt END)  AS ChargeAmt,
    (CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.TotalLines*-1 ELSE i.TotalLines END) AS TotalLines,
    (CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.GrandTotal*-1 ELSE i.GrandTotal END) AS GrandTotal,
    (CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END) AS Multiplier,
    (CASE WHEN charAt(d.DocBaseType,2)='P' THEN -1 ELSE 1 END) AS MultiplierAP,
    d.DocBaseType
    , paymentTermDueDate(i.C_PaymentTerm_ID, i.DateInvoiced) as DueDate
FROM C_Invoice i
    INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
WHERE i.IsPayScheduleValid<>'Y'
UNION
SELECT i.C_Invoice_ID, i.AD_Client_ID, i.AD_Org_ID, i.IsActive, i.Created, i.CreatedBy, i.Updated, i.UpdatedBy,
    i.IsSOTrx, i.DocumentNo, i.DocStatus, i.DocAction, i.Processing, i.Processed, i.C_DocType_ID,
    i.C_DocTypeTarget_ID, i.C_Order_ID, i.Description, i.IsApproved, i.IsTransferred,
    i.SalesRep_ID, i.DateInvoiced, i.DatePrinted, i.DateAcct, i.C_BPartner_ID, i.C_BPartner_Location_ID,
    i.AD_User_ID, i.POReference, i.DateOrdered, i.C_Currency_ID, i.C_ConversionType_ID, i.PaymentRule,
    i.C_PaymentTerm_ID, i.C_Charge_ID, i.M_PriceList_ID, i.C_Campaign_ID, i.C_Project_ID,
    i.C_Activity_ID, i.IsPrinted, i.IsDiscountPrinted, i.IsPaid, i.IsInDispute,
    i.IsPayScheduleValid, ips.C_InvoicePaySchedule_ID, i.InvoiceCollectionType,
    null AS ChargeAmt,
    null AS TotalLines,
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN ips.DueAmt*-1 ELSE ips.DueAmt END AS GrandTotal,
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1 ELSE 1 END AS Multiplier,
    CASE WHEN charAt(d.DocBaseType,2)='P' THEN -1 ELSE 1 END AS MultiplierAP,
    d.DocBaseType
    , ips.DueDate
FROM C_Invoice i
    INNER JOIN C_DocType d ON (i.C_DocType_ID=d.C_DocType_ID)
    INNER JOIN C_InvoicePaySchedule ips ON (i.C_Invoice_ID=ips.C_Invoice_ID)
WHERE i.IsPayScheduleValid='Y'
    AND ips.IsValid='Y';
/
