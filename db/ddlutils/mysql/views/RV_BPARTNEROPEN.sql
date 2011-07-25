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



