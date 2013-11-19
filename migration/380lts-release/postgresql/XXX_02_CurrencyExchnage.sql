DROP VIEW RV_BPARTNEROPEN;

DROP VIEW C_PAYMENT_V;

CREATE OR REPLACE VIEW C_PAYMENT_V
(C_PAYMENT_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, 
 CREATEDBY, UPDATED, UPDATEDBY, DOCUMENTNO, DATETRX, DATEACCT, 
 ISRECEIPT, C_DOCTYPE_ID, TRXTYPE, C_BANKACCOUNT_ID, C_BPARTNER_ID, 
 C_INVOICE_ID, C_BP_BANKACCOUNT_ID, C_PAYMENTBATCH_ID, TENDERTYPE, CREDITCARDTYPE, 
 CREDITCARDNUMBER, CREDITCARDVV, CREDITCARDEXPMM, CREDITCARDEXPYY, MICR, 
 ROUTINGNO, ACCOUNTNO, CHECKNO, A_NAME, A_STREET, 
 A_CITY, A_STATE, A_ZIP, A_IDENT_DL, A_IDENT_SSN, 
 A_EMAIL, VOICEAUTHCODE, ORIG_TRXID, PONUM, C_CURRENCY_ID, 
 C_CONVERSIONTYPE_ID, PAYAMT, DISCOUNTAMT, WRITEOFFAMT, TAXAMT, 
 OVERUNDERAMT, MULTIPLIERAP, ISOVERUNDERPAYMENT, ISAPPROVED, R_PNREF, 
 R_RESULT, R_RESPMSG, R_AUTHCODE, R_AVSADDR, R_AVSZIP, 
 R_INFO, PROCESSING, OPROCESSING, DOCSTATUS, DOCACTION, 
 ISPREPAYMENT, C_CHARGE_ID, ISRECONCILED, ISALLOCATED, ISONLINE, 
 PROCESSED, POSTED, C_CAMPAIGN_ID, C_PROJECT_ID, C_ACTIVITY_ID)
AS 
SELECT C_Payment_ID, AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    DocumentNo, DateTrx, DateAcct, IsReceipt, C_DocType_ID, TrxType,
    C_BankAccount_ID, C_BPartner_ID, C_Invoice_ID, C_BP_BankAccount_ID, C_PaymentBatch_ID,
    TenderType, CreditCardType, CreditCardNumber, CreditCardVV, CreditCardExpMM, CreditCardExpYY,
    Micr, RoutingNo, AccountNo, CheckNo,
    A_Name, A_Street, A_City, A_State, A_Zip, A_Ident_DL, A_Ident_SSN, A_EMail,
    VoiceAuthCode, Orig_TrxID, PONum,
    C_Currency_ID, C_ConversionType_ID,
    CASE IsReceipt WHEN 'Y' THEN PayAmt ELSE PayAmt*-1 END AS PayAmt,
    CASE IsReceipt WHEN 'Y' THEN DiscountAmt ELSE DiscountAmt*-1 END AS DiscountAmt, 
    CASE IsReceipt WHEN 'Y' THEN WriteOffAmt ELSE WriteOffAmt*-1 END AS WriteOffAmt,
    CASE IsReceipt WHEN 'Y' THEN TaxAmt ELSE TaxAmt*-1 END AS TaxAmt, 
    CASE IsReceipt WHEN 'Y' THEN OverUnderAmt ELSE OverUnderAmt*-1 END AS OverUnderAmt,
    CASE IsReceipt WHEN 'Y' THEN 1 ELSE -1 END AS MultiplierAP,
    IsOverUnderPayment, IsApproved,
    R_PnRef, R_Result, R_RespMsg, R_AuthCode, R_AvsAddr, R_AvsZip, R_Info,
    Processing, OProcessing, DocStatus, DocAction,
    IsPrepayment, C_Charge_ID,
    IsReconciled, IsAllocated, IsOnline, Processed, Posted,
    C_Campaign_ID, C_Project_ID, C_Activity_ID
FROM C_Payment;

--COMMENT ON TABLE C_PAYMENT_V IS 'Payment Information corrected for AP/AR';


CREATE OR REPLACE VIEW RV_BPARTNEROPEN
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, C_BPARTNER_ID, C_CURRENCY_ID, AMT, 
 OPENAMT, DATEDOC, DAYSDUE, C_CAMPAIGN_ID, C_PROJECT_ID, 
 C_ACTIVITY_ID)
AS 
SELECT i.AD_Client_ID,i.AD_Org_ID, i.IsActive, i.Created,i.CreatedBy,i.Updated,i.UpdatedBy,
    i.C_BPartner_ID, i.C_Currency_ID,
    i.GrandTotal*i.MultiplierAP AS Amt,
    invoiceOpen (i.C_Invoice_ID, i.C_InvoicePaySchedule_ID)*MultiplierAP AS OpenAmt,
    i.DateInvoiced AS DateDoc, 
    COALESCE(daysBetween(getdate(),ips.DueDate), paymentTermDueDays(C_PaymentTerm_ID,DateInvoiced,getdate())) AS DaysDue,
    i.C_Campaign_ID, i.C_Project_ID, i.C_Activity_ID
FROM C_Invoice_v i 
  LEFT OUTER JOIN C_InvoicePaySchedule ips ON (i.C_InvoicePaySchedule_ID=ips.C_InvoicePaySchedule_ID)
WHERE IsPaid='N'
 AND DocStatus IN ('CO','CL')
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