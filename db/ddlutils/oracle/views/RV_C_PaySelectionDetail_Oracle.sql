CREATE OR REPLACE VIEW RV_C_PaySelectionDetail (AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive, Processed, IsApproved, 
DocAction, DocStatus, DocumentNo, C_PaySelection_ID, Description, 
PayDate, TotalAmt, DateDoc, Name, C_DocType_ID, C_BankAccount_ID, C_Currency_ID, IsManual, C_BankAccountTo_ID, 
HR_Movement_ID, C_Charge_ID, LineDescription, DiscountAmt, PayAmt, C_PaySelectionLine_Parent_ID, 
IsPrepayment, AmtSource, DifferenceAmt, IsSOTrx, OpenAmt, C_InvoicePaySchedule_ID, PaymentRule, C_PaySelectionLine_ID, 
Line, C_PaySelectionCheck_ID, C_ConversionType_ID, C_Conversion_Rate_ID, C_Invoice_ID, C_BP_BankAccount_ID, C_BPartner_ID, C_Order_ID, UUID, C_Payment_ID, Qty) AS
SELECT ps.AD_Client_ID, ps.AD_Org_ID, ps.Created, ps.CreatedBy, ps.Updated, ps.UpdatedBy, ps.IsActive, ps.Processed, ps.IsApproved, 
ps.DocAction, ps.DocStatus, ps.DocumentNo, ps.C_PaySelection_ID, ps.Description, 
ps.PayDate, ps.TotalAmt, ps.DateDoc, ps.Name, ps.C_DocType_ID, ps.C_BankAccount_ID, ps.C_Currency_ID, psl.IsManual, psl.C_BankAccountTo_ID, 
psl.HR_Movement_ID, psl.C_Charge_ID, psl.Description AS LineDescription, psl.DiscountAmt, psl.PayAmt, psl.C_PaySelectionLine_Parent_ID, 
psl.IsPrepayment, psl.AmtSource, psl.DifferenceAmt, psl.IsSOTrx, psl.OpenAmt, psl.C_InvoicePaySchedule_ID, psl.PaymentRule, psl.C_PaySelectionLine_ID, 
psl.Line, psl.C_PaySelectionCheck_ID, psl.C_ConversionType_ID, psl.C_Conversion_Rate_ID, psl.C_Invoice_ID, psl.C_BP_BankAccount_ID, psl.C_BPartner_ID, psl.C_Order_ID, psl.UUID,
psc.C_Payment_ID, psc.Qty
FROM C_PaySelection ps
INNER JOIN C_PaySelectionLine psl ON(psl.C_PaySelection_ID = ps.C_PaySelection_ID)
LEFT JOIN C_PaySelectionCheck psc ON(psc.C_PaySelectionCheck_ID = psl.C_PaySelectionCheck_ID)
