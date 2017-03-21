-- DROP VIEW C_Payment_v;
CREATE OR REPLACE VIEW C_Payment_v AS
 SELECT p.C_Payment_ID,
    p.AD_Client_ID,
    p.AD_Org_ID,
    p.IsActive,
    p.Created,
    p.CreatedBy,
    p.Updated,
    p.UpdatedBy,
    p.DocumentNo,
    p.DateTrx,
    p.DateAcct,
    p.IsReceipt,
    p.C_Doctype_ID,
    p.TrxType,
    p.C_BankAccount_ID,
    p.C_BPartner_ID,
    p.C_Invoice_ID,
    p.C_BP_BankAccount_ID,
    p.C_PaymentBatch_ID,
    p.TenderType,
    p.CreditCardType,
    p.CreditCardNumber,
    p.CreditCardVV,
    p.CreditCardExpMM,
    p.CreditCardExpYY,
    p.Micr,
    p.RoutingNo,
    p.AccountNo,
    p.CheckNo,
    p.A_Name,
    p.A_Street,
    p.A_City,
    p.A_State,
    p.A_Zip,
    p.A_Ident_DL,
    p.A_Ident_SSN,
    p.A_Email,
    p.VoiceAuthCode,
    p.Orig_TrxID,
    p.PONum,
    p.C_Currency_ID,
    p.C_ConversionType_ID,
        CASE p.IsReceipt
            WHEN 'Y' THEN p.PayAmt
            ELSE p.PayAmt * (-1)
        END AS PayAmt,
        CASE p.IsReceipt
            WHEN 'Y' THEN p.DiscountAmt
            ELSE p.DiscountAmt * (-1)
        END AS DiscountAmt,
        CASE p.IsReceipt
            WHEN 'Y' THEN p.WriteOffAmt
            ELSE p.WriteOffAmt * (-1)
        END AS WriteOffAmt,
        CASE p.IsReceipt
            WHEN 'Y' THEN p.TaxAmt
            ELSE p.TaxAmt * (-1)
        END AS TaxAmt,
        CASE p.IsReceipt
            WHEN 'Y' THEN p.OverUnderAmt
            ELSE p.OverUnderAmt * (-1)
        END AS OverUnderAmt,
        CASE p.IsReceipt
            WHEN 'Y' THEN 1
            ELSE (-1)
        END AS MultiplierAP,
    p.IsOverUnderPayment,
    p.IsApproved,
    p.R_PNRef,
    p.R_Result,
    p.R_RespMsg,
    p.R_AuthCode,
    p.R_AvsAddr,
    p.r_AvsZip,
    p.r_Info,
    p.Processing,
    p.Oprocessing,
    p.DocStatus,
    p.DocAction,
    p.IsPrepayment,
    p.C_Charge_ID,
    p.IsReconciled,
    p.IsAllocated,
    p.IsOnline,
    p.Processed,
    p.Posted,
    p.C_Campaign_ID,
    p.C_Project_ID,
    p.C_Activity_ID,
    p.C_Order_ID,
    p.User1_ID,
    p.User2_ID
   FROM C_Payment p ;