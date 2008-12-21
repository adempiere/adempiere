DROP VIEW C_INVOICELINE_V;
DROP VIEW RV_BPARTNEROPEN;
DROP VIEW C_INVOICE_V;

CREATE OR REPLACE VIEW C_INVOICE_V
(C_INVOICE_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, 
 CREATEDBY, UPDATED, UPDATEDBY, ISSOTRX, DOCUMENTNO, 
 DOCSTATUS, DOCACTION, PROCESSING, PROCESSED, C_DOCTYPE_ID, 
 C_DOCTYPETARGET_ID, C_ORDER_ID, DESCRIPTION, ISAPPROVED, ISTRANSFERRED, 
 SALESREP_ID, DATEINVOICED, DATEPRINTED, DATEACCT, C_BPARTNER_ID, 
 C_BPARTNER_LOCATION_ID, AD_USER_ID, POREFERENCE, DATEORDERED, C_CURRENCY_ID, 
 C_CONVERSIONTYPE_ID, PAYMENTRULE, C_PAYMENTTERM_ID, C_CHARGE_ID, M_PRICELIST_ID, 
 C_CAMPAIGN_ID, C_PROJECT_ID, C_ACTIVITY_ID, ISPRINTED, ISDISCOUNTPRINTED, 
 ISPAID, ISINDISPUTE, ISPAYSCHEDULEVALID, C_INVOICEPAYSCHEDULE_ID, INVOICECOLLECTIONTYPE,DUNNINGGRACE, 
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
    i.IsPayScheduleValid, cast(null as numeric) AS C_InvoicePaySchedule_ID, i.InvoiceCollectionType,i.DunningGrace,
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.ChargeAmt*-1 ELSE i.ChargeAmt END AS ChargeAmt,
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.TotalLines*-1 ELSE i.TotalLines END AS TotalLines,
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN i.GrandTotal*-1 ELSE i.GrandTotal END AS GrandTotal,
    CASE WHEN charAt(d.DocBaseType,3)='C' THEN -1.0 ELSE 1.0 END AS Multiplier,
    CASE WHEN charAt(d.DocBaseType,2)='P' THEN -1.0 ELSE 1.0 END AS MultiplierAP,
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
    i.IsPayScheduleValid, ips.C_InvoicePaySchedule_ID, i.InvoiceCollectionType, i.DunningGrace,
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

CREATE OR REPLACE VIEW C_INVOICELINE_V
(AD_CLIENT_ID, AD_ORG_ID, C_INVOICELINE_ID, C_INVOICE_ID, SALESREP_ID, 
 C_BPARTNER_ID, M_PRODUCT_ID, DOCUMENTNO, DATEINVOICED, DATEACCT, 
 ISSOTRX, DOCSTATUS, LINENETAMT, LINELISTAMT, LINELIMITAMT, 
 LINEDISCOUNTAMT, LINEOVERLIMITAMT, QTYINVOICED, QTYENTERED, LINE, 
 C_ORDERLINE_ID, C_UOM_ID, C_CAMPAIGN_ID, C_PROJECT_ID, C_ACTIVITY_ID, 
 C_PROJECTPHASE_ID, C_PROJECTTASK_ID)
AS 
SELECT il.AD_Client_ID, il.AD_Org_ID, 
	il.C_InvoiceLine_ID, i.C_Invoice_ID, i.SalesRep_ID, 
	i.C_BPartner_ID, il.M_Product_ID,  
	i.DocumentNo, i.DateInvoiced, i.DateAcct,
	i.IsSOTrx, i.DocStatus,
	ROUND(i.Multiplier*LineNetAmt, 2) AS LineNetAmt,
	ROUND(i.Multiplier*PriceList*QtyInvoiced, 2) AS LineListAmt,
	CASE WHEN COALESCE(il.PriceLimit, 0)=0 THEN ROUND(i.Multiplier*LineNetAmt,2) ELSE ROUND(i.Multiplier*il.PriceLimit*il.QtyInvoiced,2) END AS LineLimitAmt,
	ROUND(i.Multiplier*il.PriceList*il.QtyInvoiced-il.LineNetAmt,2) AS LineDiscountAmt,
	CASE WHEN COALESCE(il.PriceLimit,0)=0 THEN 0 ELSE ROUND(i.Multiplier*il.LineNetAmt-il.PriceLimit*il.QtyInvoiced,2) END AS LineOverLimitAmt,
	il.QtyInvoiced, il.QtyEntered,
	il.Line, il.C_OrderLine_ID, il.C_UOM_ID,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID
FROM C_Invoice_v i, C_InvoiceLine il
WHERE i.C_Invoice_ID=il.C_Invoice_ID;

CREATE OR REPLACE VIEW RV_BPARTNEROPEN
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, C_BPARTNER_ID, C_CURRENCY_ID, AMT, 
 OPENAMT, DATEDOC, DAYSDUE, C_CAMPAIGN_ID, C_PROJECT_ID, 
 C_ACTIVITY_ID)
AS 
SELECT i.AD_Client_ID,i.AD_Org_ID, i.IsActive, i.Created,i.CreatedBy,i.Updated,i.UpdatedBy,
    i.C_BPartner_ID, i.C_Currency_ID,
    i.GrandTotal*i.MultiplierAP AS Amt,
    invoiceOpen (i.C_Invoice_ID, i.C_InvoicePaySchedule_ID)*i.MultiplierAP AS OpenAmt,
    i.DateInvoiced AS DateDoc, 
    COALESCE(daysBetween(getdate(),ips.DueDate), paymentTermDueDays(C_PaymentTerm_ID,DateInvoiced,getdate())) AS DaysDue,
    i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID
FROM C_Invoice_v i 
  LEFT OUTER JOIN C_InvoicePaySchedule ips ON (i.C_InvoicePaySchedule_ID=ips.C_InvoicePaySchedule_ID)
WHERE i.IsPaid='N'
 AND i.DocStatus IN ('CO','CL')
UNION
SELECT p.AD_Client_ID,p.AD_Org_ID, p.IsActive, p.Created,p.CreatedBy,p.Updated,p.UpdatedBy,
    p.C_BPartner_ID, p.C_Currency_ID,
    p.PayAmt*MultiplierAP*-1 AS Amt,
    paymentAvailable(p.C_Payment_ID)*p.MultiplierAP*-1 AS OpenAmt,
    p.DateTrx AS DateDoc,
    null,
    p.C_Campaign_ID, p.C_Project_ID, p.C_Activity_ID
FROM C_Payment_v p 
WHERE p.IsAllocated='N' AND p.C_BPartner_ID IS NOT NULL
 AND p.DocStatus IN ('CO','CL');
