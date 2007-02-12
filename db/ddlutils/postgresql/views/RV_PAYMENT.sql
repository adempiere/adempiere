CREATE OR REPLACE VIEW RV_PAYMENT
(C_PAYMENT_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, 
 CREATEDBY, UPDATED, UPDATEDBY, DOCUMENTNO, DATETRX, 
 ISRECEIPT, C_DOCTYPE_ID, TRXTYPE, C_BANKACCOUNT_ID, C_BPARTNER_ID, 
 C_INVOICE_ID, C_BP_BANKACCOUNT_ID, C_PAYMENTBATCH_ID, TENDERTYPE, CREDITCARDTYPE, 
 CREDITCARDNUMBER, CREDITCARDVV, CREDITCARDEXPMM, CREDITCARDEXPYY, MICR, 
 ROUTINGNO, ACCOUNTNO, CHECKNO, A_NAME, A_STREET, 
 A_CITY, A_STATE, A_ZIP, A_IDENT_DL, A_IDENT_SSN, 
 A_EMAIL, VOICEAUTHCODE, ORIG_TRXID, PONUM, C_CURRENCY_ID, 
 C_CONVERSIONTYPE_ID, PAYAMT, DISCOUNTAMT, WRITEOFFAMT, TAXAMT, 
 OVERUNDERAMT, MULTIPLIERAP, ALLOCATEDAMT, AVAILABLEAMT, ISOVERUNDERPAYMENT, 
 ISAPPROVED, R_PNREF, R_RESULT, R_RESPMSG, R_AUTHCODE, 
 R_AVSADDR, R_AVSZIP, R_INFO, PROCESSING, OPROCESSING, 
 DOCSTATUS, DOCACTION, ISPREPAYMENT, C_CHARGE_ID, ISRECONCILED, 
 ISALLOCATED, ISONLINE, PROCESSED, POSTED, C_CAMPAIGN_ID, 
 C_PROJECT_ID, C_ACTIVITY_ID)
AS 
SELECT C_Payment_ID, AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
    DocumentNo, DateTrx, IsReceipt, C_DocType_ID, TrxType,
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
    paymentAllocated(C_Payment_ID, C_Currency_ID) AS AllocatedAmt,
    paymentAvailable(C_Payment_ID) AS AvailableAmt,
    IsOverUnderPayment, IsApproved,
    R_PnRef, R_Result, R_RespMsg, R_AuthCode, R_AvsAddr, R_AvsZip, R_Info,
    Processing, OProcessing, DocStatus, DocAction,
    IsPrepayment, C_Charge_ID,
    IsReconciled, IsAllocated, IsOnline, Processed, Posted,
    C_Campaign_ID, C_Project_ID, C_Activity_ID
FROM C_Payment;

--COMMENT ON TABLE RV_PAYMENT IS 'Payment Information corrected for AP/AR';



