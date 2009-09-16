CREATE OR REPLACE VIEW RV_OPENITEM
(AD_ORG_ID, AD_CLIENT_ID, DOCUMENTNO, C_INVOICE_ID, C_ORDER_ID, 
 C_BPARTNER_ID, ISSOTRX, DATEINVOICED, DATEACCT, NETDAYS, 
 DUEDATE, DAYSDUE, DISCOUNTDATE, DISCOUNTAMT, GRANDTOTAL, 
 PAIDAMT, OPENAMT, 
 C_CURRENCY_ID, C_CONVERSIONTYPE_ID, C_PAYMENTTERM_ID, 
 ISPAYSCHEDULEVALID, C_INVOICEPAYSCHEDULE_ID, INVOICECOLLECTIONTYPE, C_CAMPAIGN_ID, C_PROJECT_ID, 
 C_ACTIVITY_ID)
AS 
SELECT i.AD_Org_ID, i.AD_Client_ID,
	i.DocumentNo, i.C_Invoice_ID, i.C_Order_ID, i.C_BPartner_ID, i.IsSOTrx,
	i.DateInvoiced, i.DateAcct,
    p.NetDays,
	paymentTermDueDate(i.C_PaymentTerm_ID, i.DateInvoiced) AS DueDate,
	paymentTermDueDays(i.C_PaymentTerm_ID, i.DateInvoiced, getdate()) AS DaysDue,
    addDays(i.DateInvoiced,p.DiscountDays) AS DiscountDate,
    ROUND(i.GrandTotal*p.Discount/100,2) AS DiscountAmt,
	i.GrandTotal,
	invoicePaid(i.C_Invoice_ID, i.C_Currency_ID, 1) AS PaidAmt,
	invoiceOpen(i.C_Invoice_ID,0) AS OpenAmt,
    i.C_Currency_ID, i.C_ConversionType_ID,
    i.C_PaymentTerm_ID,
    i.IsPayScheduleValid, cast(null as numeric) AS C_InvoicePaySchedule_ID, i.InvoiceCollectionType,
    i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID
FROM RV_C_Invoice i
    INNER JOIN C_PaymentTerm p ON (i.C_PaymentTerm_ID=p.C_PaymentTerm_ID)
WHERE --    i.IsPaid='N'
    invoiceOpen(i.C_Invoice_ID,0) <> 0 AND 
    i.IsPayScheduleValid<>'Y'
    AND i.DocStatus IN ('CO','CL')
UNION
SELECT i.AD_Org_ID, i.AD_Client_ID,
    i.DocumentNo, i.C_Invoice_ID, i.C_Order_ID, i.C_BPartner_ID, i.IsSOTrx,
	i.DateInvoiced, i.DateAcct,
    daysBetween(ips.DueDate,i.DateInvoiced) AS NetDays,
    ips.DueDate,
    daysBetween(getdate(),ips.DueDate) AS DaysDue,
    ips.DiscountDate,
    ips.DiscountAmt,
	ips.DueAmt AS GrandTotal,
	invoicePaid(i.C_Invoice_ID, i.C_Currency_ID, 1) AS PaidAmt,
	invoiceOpen(i.C_Invoice_ID, ips.C_InvoicePaySchedule_ID) AS OpenAmt,
    i.C_Currency_ID, i.C_ConversionType_ID,
    i.C_PaymentTerm_ID,
    i.IsPayScheduleValid, ips.C_InvoicePaySchedule_ID, i.InvoiceCollectionType,
    i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID
FROM RV_C_Invoice i
    INNER JOIN C_InvoicePaySchedule ips ON (i.C_Invoice_ID=ips.C_Invoice_ID)
WHERE  --   i.IsPaid='N'
    invoiceOpen(i.C_Invoice_ID,ips.C_InvoicePaySchedule_ID) <> 0 AND 
    i.IsPayScheduleValid='Y'
    AND i.DocStatus IN ('CO','CL')
    AND ips.IsValid='Y';



CREATE OR REPLACE VIEW RV_OPENITEMTODATE
(AD_ORG_ID, AD_CLIENT_ID, DOCUMENTNO, C_INVOICE_ID, C_ORDER_ID, 
 C_BPARTNER_ID, ISSOTRX, DATEINVOICED, DATEACCT, NETDAYS, 
 DUEDATE, DAYSDUE, DISCOUNTDATE, DISCOUNTAMT, GRANDTOTAL, 
 --PAIDAMT, OPENAMT, 
 C_CURRENCY_ID, C_CONVERSIONTYPE_ID, C_PAYMENTTERM_ID, 
 ISPAYSCHEDULEVALID, C_INVOICEPAYSCHEDULE_ID, INVOICECOLLECTIONTYPE, C_CAMPAIGN_ID, C_PROJECT_ID, 
 C_ACTIVITY_ID)
AS 
SELECT i.AD_Org_ID, i.AD_Client_ID,
	i.DocumentNo, i.C_Invoice_ID, i.C_Order_ID, i.C_BPartner_ID, i.IsSOTrx,
	i.DateInvoiced, i.DateAcct,
    p.NetDays,
	paymentTermDueDate(i.C_PaymentTerm_ID, i.DateInvoiced) AS DueDate,
	paymentTermDueDays(i.C_PaymentTerm_ID, i.DateInvoiced, getdate()) AS DaysDue,
    addDays(i.DateInvoiced,p.DiscountDays) AS DiscountDate,
    ROUND(i.GrandTotal*p.Discount/100,2) AS DiscountAmt,
	i.GrandTotal,
	--invoicePaid(i.C_Invoice_ID, i.C_Currency_ID, 1) AS PaidAmt,
	--invoiceOpen(i.C_Invoice_ID,0) AS OpenAmt,
    i.C_Currency_ID, i.C_ConversionType_ID,
    i.C_PaymentTerm_ID,
    i.IsPayScheduleValid, cast(null as numeric) AS C_InvoicePaySchedule_ID, i.InvoiceCollectionType,
    i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID
FROM RV_C_Invoice i
    INNER JOIN C_PaymentTerm p ON (i.C_PaymentTerm_ID=p.C_PaymentTerm_ID)
WHERE --    i.IsPaid='N'
    --invoiceOpen(i.C_Invoice_ID,0) <> 0 AND 
    i.IsPayScheduleValid<>'Y'
    AND i.DocStatus IN ('CO','CL')
UNION
SELECT i.AD_Org_ID, i.AD_Client_ID,
    i.DocumentNo, i.C_Invoice_ID, i.C_Order_ID, i.C_BPartner_ID, i.IsSOTrx,
	i.DateInvoiced, i.DateAcct,
    daysBetween(ips.DueDate,i.DateInvoiced) AS NetDays,
    ips.DueDate,
    daysBetween(getdate(),ips.DueDate) AS DaysDue,
    ips.DiscountDate,
    ips.DiscountAmt,
	ips.DueAmt AS GrandTotal,
	--invoicePaid(i.C_Invoice_ID, i.C_Currency_ID, 1) AS PaidAmt,
	--invoiceOpen(i.C_Invoice_ID, ips.C_InvoicePaySchedule_ID) AS OpenAmt,
    i.C_Currency_ID, i.C_ConversionType_ID,
    i.C_PaymentTerm_ID,
    i.IsPayScheduleValid, ips.C_InvoicePaySchedule_ID, i.InvoiceCollectionType,
    i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID
FROM RV_C_Invoice i
    INNER JOIN C_InvoicePaySchedule ips ON (i.C_Invoice_ID=ips.C_Invoice_ID)
WHERE  --   i.IsPaid='N'
    --invoiceOpen(i.C_Invoice_ID,ips.C_InvoicePaySchedule_ID) <> 0 AND 
    i.IsPayScheduleValid='Y'
    AND i.DocStatus IN ('CO','CL')
    AND ips.IsValid='Y';



