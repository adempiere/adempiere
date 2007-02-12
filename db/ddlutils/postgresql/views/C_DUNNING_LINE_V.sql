CREATE OR REPLACE VIEW C_DUNNING_LINE_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, C_DUNNINGRUNLINE_ID, C_DUNNINGRUNENTRY_ID, 
 AMT, CONVERTEDAMT, DAYSDUE, TIMESDUNNED, INTERESTAMT, 
 FEEAMT, TOTALAMT, C_INVOICE_ID, ISSOTRX, DOCUMENTNO, 
 DOCSTATUS, DATETRX, C_DOCTYPE_ID, DOCUMENTTYPE, DESCRIPTION, 
 C_CURRENCY_ID, C_CAMPAIGN_ID, C_PROJECT_ID, C_ACTIVITY_ID, USER1_ID, 
 USER2_ID, DATEACCT, C_CONVERSIONTYPE_ID, AD_ORGTRX_ID, POREFERENCE, 
 DATEORDERED, DATEINVOICED, ISINDISPUTE, PAYMENTTERM, C_CHARGE_ID, 
 CHARGEAMT, TOTALLINES, GRANDTOTAL, AMTINWORDS, M_PRICELIST_ID, 
 ISPAID, ISALLOCATED, TENDERTYPE, DISCOUNTAMT)
AS 
SELECT drl.AD_Client_ID, drl.AD_Org_ID, drl.IsActive, drl.Created, drl.CreatedBy, drl.Updated, drl.UpdatedBy, 
	cast('en_US' as varchar) AS AD_Language,
    drl.C_DunningRunLine_ID, drl.C_DunningRunEntry_ID,
    drl.Amt, drl.ConvertedAmt, drl.DaysDue, drl.TimesDunned, 
    drl.InterestAmt, drl.FeeAmt, drl.TotalAmt,
	drl.C_Invoice_ID, 
    COALESCE(i.IsSOTrx,p.IsReceipt) AS IsSOTrx,
    COALESCE(i.DocumentNo,p.DocumentNo) AS DocumentNo,
    COALESCE(i.DocStatus,p.DocStatus) AS DocStatus, 
	COALESCE(i.DateInvoiced, p.DateTrx) AS DateTrx,
    COALESCE(i.C_DocType_ID,p.C_DocType_ID) AS C_DocType_ID,
    COALESCE(dt.PrintName,dtp.PrintName) AS DocumentType, 
    COALESCE(i.Description,p.Description) AS Description, 
	COALESCE(i.C_Currency_ID,p.C_Currency_ID) AS C_Currency_ID, 
    COALESCE(i.C_Campaign_ID,p.C_Campaign_ID) AS C_Campaign_ID, 
    COALESCE(i.C_Project_ID,p.C_Project_ID) AS C_Project_ID,
    COALESCE(i.C_Activity_ID,p.C_Activity_ID) AS C_Activity_ID,
    COALESCE(i.User1_ID,p.User1_ID) AS User1_ID,
    COALESCE(i.User2_ID,p.User2_ID) AS User2_ID,
    COALESCE(i.DateAcct,p.DateAcct) AS DateAcct,
    COALESCE(i.C_ConversionType_ID,i.C_ConversionType_ID) AS C_ConversionType_ID,
    COALESCE(i.AD_OrgTrx_ID,p.AD_OrgTrx_ID) AS AD_OrgTrx_ID,
    i.POReference, i.DateOrdered,
	i.DateInvoiced, i.IsInDispute,
	pt.Name AS PaymentTerm,
    i.C_Charge_ID, i.ChargeAmt,
	i.TotalLines, i.GrandTotal, i.GrandTotal AS AmtInWords,
	i.M_PriceList_ID, i.IsPaid,
    p.IsAllocated, p.TenderType, p.DiscountAmt
FROM C_DunningRunLine drl
    LEFT OUTER JOIN C_Invoice i ON (drl.C_Invoice_ID=i.C_Invoice_ID)
	LEFT OUTER JOIN C_DocType dt ON (i.C_DocType_ID=dt.C_DocType_ID)
	LEFT OUTER JOIN C_PaymentTerm pt ON (i.C_PaymentTerm_ID=pt.C_PaymentTerm_ID)
    LEFT OUTER JOIN C_Payment p ON (drl.C_Payment_ID=p.C_Payment_ID)
	LEFT OUTER JOIN C_DocType dtp ON (p.C_DocType_ID=dtp.C_DocType_ID);



