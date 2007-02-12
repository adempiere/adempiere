CREATE OR REPLACE VIEW RV_CASH_DETAIL
(C_CASH_ID, C_CASHLINE_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, 
 CREATED, CREATEDBY, UPDATED, UPDATEDBY, C_CASHBOOK_ID, 
 NAME, STATEMENTDATE, DATEACCT, PROCESSED, POSTED, 
 LINE, DESCRIPTION, CASHTYPE, C_CURRENCY_ID, AMOUNT, 
 CONVERTEDAMT, C_BANKACCOUNT_ID, C_INVOICE_ID, C_CHARGE_ID)
AS 
SELECT cl.C_Cash_ID, cl.C_CashLine_ID,
	c.AD_Client_ID, c.AD_Org_ID, cl.IsActive, cl.Created, cl.CreatedBy, cl.Updated, cl.UpdatedBy,
	c.C_CashBook_ID, c.Name, c.StatementDate, c.DateAcct, c.Processed, c.Posted,
	cl.Line, cl.Description, cl.CashType, cl.C_Currency_ID, cl.Amount, 
	currencyConvert(cl.Amount,cl.C_Currency_ID,cb.C_Currency_ID,c.StatementDate,0, c.AD_Client_ID, c.AD_Org_ID) AS ConvertedAmt,
	cl.C_BankAccount_ID, cl.C_Invoice_ID, cl.C_Charge_ID
FROM C_Cash c
 INNER JOIN C_CashLine cl ON (c.C_Cash_ID=cl.C_Cash_ID)
 INNER JOIN C_CashBook cb ON (c.C_CashBook_ID=cb.C_CashBook_ID);



