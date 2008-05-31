-- Apr 7, 2008 11:10:44 PM CDT
-- [ 1933937 ] Is necessary a new Aging with effective date.
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,263,0,238,53144,20,'DateAcct',TO_TIMESTAMP('2008-04-07 23:10:39','YYYY-MM-DD HH24:MI:SS'),0,'N','D',1,'Y','Y','N','N','DateAcct',15,TO_TIMESTAMP('2008-04-07 23:10:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 7, 2008 11:10:44 PM CDT
-- [ 1933937 ] Is necessary a new Aging with effective date.
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53144 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Apr 7, 2008 11:11:17 PM CDT
-- [ 1933937 ] Is necessary a new Aging with effective date.
UPDATE AD_Process_Para SET Description='Accounting Date', Help='The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.',Updated=TO_TIMESTAMP('2008-04-07 23:11:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53144
;

-- Apr 7, 2008 11:11:17 PM CDT
-- [ 1933937 ] Is necessary a new Aging with effective date.
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53144
;


-- Apr 7, 2008 11:11:53 PM CDT
-- [ 1933937 ] Is necessary a new Aging with effective date.
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=238
;

-- Apr 7, 2008 11:12:00 PM CDT
-- [ 1933937 ] Is necessary a new Aging with effective date.
UPDATE AD_Process_Para SET Name='Account Date',Updated=TO_TIMESTAMP('2008-04-07 23:12:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53144
;

-- Apr 7, 2008 11:12:00 PM CDT
-- [ 1933937 ] Is necessary a new Aging with effective date.
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53144
;

-- Apr 7, 2008 11:47:58 PM CDT
-- [ 1933937 ] Is necessary a new Aging with effective date.
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=238
;

-- Apr 8, 2008 12:05:33 AM CDT
-- [ 1933937 ] Is necessary a new Aging with effective date.
UPDATE AD_Process SET Help='The aging report allows you to report on Open Items (Invoices). Select the aging buckets, you want to have in your report. If you select a currency, you get only invoices of that currency, otherwise the amounts are converted to your primary accounting currency. If you do not select a Statement Date, the system date is used to calculate the buckets. If you do not list the individual invoices, the Due Date is the earliest due date for the business partner and the Due Days are the average due days of all invoices.<br>
<br>
If you select an Account Date the report will generate the Aging as of that date. The report will exclude documents after the selected date.<br>
<br>
For example: A customer has one invoice for $100 with the Account Date of 03/31/08 and one payment for $100 with the Account Date of 05/01/08. The report will show the following balances based on the Account Date selected: 03/15/08= 0; 04/15/08=100; 05/15/08 = 0.
',Updated=TO_TIMESTAMP('2008-04-08 00:05:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=238
;

-- Apr 8, 2008 12:05:33 AM CDT
-- [ 1933937 ] Is necessary a new Aging with effective date.
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=238
;

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
    AND i.DocStatus<>'DR'
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
    AND i.DocStatus<>'DR'
    AND ips.IsValid='Y';

-- Apr 8, 2008 12:21:30 AM CDT
-- [ 1933937 ] Is necessary a new Aging with effective date.
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55245,263,0,20,631,'DateAcct',TO_TIMESTAMP('2008-04-08 00:21:28','YYYY-MM-DD HH24:MI:SS'),0,'N','Accounting Date','D',1,'The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.','Y','N','N','N','N','N','N','N','N','N','Y','Account Date',0,TO_TIMESTAMP('2008-04-08 00:21:28','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Apr 8, 2008 12:21:30 AM CDT
-- [ 1933937 ] Is necessary a new Aging with effective date.
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55245 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Apr 8, 2008 12:21:35 AM CDT
-- [ 1933937 ] Is necessary a new Aging with effective date.
ALTER TABLE T_Aging ADD COLUMN DateAcct CHAR(1) DEFAULT 'N' CHECK (DateAcct IN ('Y','N'))
;

