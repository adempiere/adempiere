CREATE OR REPLACE VIEW RV_ALLOCATION
(C_ALLOCATIONHDR_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, 
 CREATEDBY, UPDATED, UPDATEDBY, DOCUMENTNO, DESCRIPTION, 
 DATETRX, DATEACCT, C_CURRENCY_ID, APPROVALAMT, ISMANUAL, 
 DOCSTATUS, DOCACTION, PROCESSED, C_ALLOCATIONLINE_ID, C_INVOICE_ID, 
 C_BPARTNER_ID, C_ORDER_ID, C_PAYMENT_ID, C_CASHLINE_ID, AMOUNT, 
 DISCOUNTAMT, WRITEOFFAMT, OVERUNDERAMT)
AS 
SELECT h.C_AllocationHdr_ID, h.AD_Client_ID, h.AD_Org_ID, 
  h.IsActive, h.Created, h.CreatedBy, h.Updated, h.UpdatedBy,
  h.DocumentNo, h.Description, h.DateTrx, h.DateAcct,
  h.C_Currency_ID, h.ApprovalAmt, h.IsManual, h.DocStatus, h.DocAction, h.Processed,
  l.C_AllocationLine_ID,
  l.C_Invoice_ID, l.C_BPartner_ID, l.C_Order_ID, l.C_Payment_ID, l.C_CashLine_ID,
  l.Amount, l.DiscountAmt, l.WriteOffAmt, l.OverUnderAmt
FROM C_AllocationHdr h
  INNER JOIN C_AllocationLine l ON (h.C_AllocationHdr_ID=l.C_AllocationHdr_ID);
