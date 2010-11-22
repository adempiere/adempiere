CREATE OR REPLACE VIEW RV_C_INVOICE_PRODUCTQTR
(AD_CLIENT_ID, AD_ORG_ID, M_PRODUCT_ID, DATEINVOICED, LINENETAMT, 
 LINELISTAMT, LINELIMITAMT, LINEDISCOUNTAMT, LINEDISCOUNT, LINEOVERLIMITAMT, 
 LINEOVERLIMIT, QTYINVOICED, ISSOTRX)
AS 
SELECT il.AD_Client_ID, il.AD_Org_ID, il.M_Product_ID,
	firstOf(il.DateInvoiced, 'Q') AS DateInvoiced,
	SUM(il.LineNetAmt) AS LineNetAmt,
	SUM(il.LineListAmt) AS LineListAmt,
	SUM(il.LineLimitAmt) AS LineLimitAmt,
	SUM(il.LineDiscountAmt) AS LineDiscountAmt,
	CASE WHEN SUM(LineListAmt)=0 THEN 0 ELSE
	  ROUND((SUM(LineListAmt)-SUM(LineNetAmt))/SUM(LineListAmt)*100,2) END AS LineDiscount,
	SUM(LineOverLimitAmt) AS LineOverLimitAmt,
	CASE WHEN SUM(LineNetAmt)=0 THEN 0 ELSE
	  100-ROUND((SUM(LineNetAmt)-SUM(LineOverLimitAmt))/SUM(LineNetAmt)*100,2) END AS LineOverLimit,
	SUM(QtyInvoiced) AS QtyInvoiced, IsSOTrx
FROM RV_C_InvoiceLine il
GROUP BY il.AD_Client_ID, il.AD_Org_ID, il.M_Product_ID,
	firstOf(il.DateInvoiced, 'Q'), IsSOTrx;



