CREATE OR REPLACE VIEW RV_C_INVOICE_WEEK
(AD_CLIENT_ID, AD_ORG_ID, SALESREP_ID, DATEINVOICED, LINENETAMT, 
 LINELISTAMT, LINELIMITAMT, LINEDISCOUNTAMT, LINEDISCOUNT, LINEOVERLIMITAMT, 
 LINEOVERLIMIT, ISSOTRX)
AS 
SELECT AD_Client_ID, AD_Org_ID, SalesRep_ID,
	firstOf(DateInvoiced, 'DY') AS DateInvoiced,	--	DD Day, DY Week, MM Month
	SUM(LineNetAmt) AS LineNetAmt,
	SUM(LineListAmt) AS LineListAmt,
	SUM(LineLimitAmt) AS LineLimitAmt,
	SUM(LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
    IsSOTrx
FROM RV_C_InvoiceLine
GROUP BY AD_Client_ID, AD_Org_ID, SalesRep_ID,
	firstOf(DateInvoiced, 'DY'), IsSOTrx;



