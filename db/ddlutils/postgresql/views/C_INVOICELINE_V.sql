CREATE OR REPLACE VIEW C_INVOICELINE_V
(AD_CLIENT_ID, AD_ORG_ID, C_INVOICELINE_ID, C_INVOICE_ID, SALESREP_ID, 
 C_BPARTNER_ID, M_PRODUCT_ID, DOCUMENTNO, DATEINVOICED, DATEACCT, 
 ISSOTRX, DOCSTATUS, LINENETAMT, LINELISTAMT, LINELIMITAMT, 
 LINEDISCOUNTAMT, LINEOVERLIMITAMT, QTYINVOICED, QTYENTERED, LINE, 
 C_ORDERLINE_ID, C_UOM_ID, C_CAMPAIGN_ID, C_PROJECT_ID, C_ACTIVITY_ID, 
 C_PROJECTPHASE_ID, C_PROJECTTASK_ID)
AS 
SELECT il.AD_Client_ID, il.AD_Org_ID, 
	il.C_InvoiceLine_ID, i.C_Invoice_ID, i.SalesRep_ID, 
	i.C_BPartner_ID, il.M_Product_ID,  
	i.DocumentNo, i.DateInvoiced, i.DateAcct,
	i.IsSOTrx, i.DocStatus,
	ROUND(i.Multiplier*il.LineNetAmt, 2) AS LineNetAmt,
	ROUND(i.Multiplier*il.PriceList*il.QtyInvoiced, 2) AS LineListAmt,
	CASE WHEN COALESCE(il.PriceLimit, 0::numeric)=0::numeric THEN ROUND(i.Multiplier*il.LineNetAmt,2) ELSE ROUND(i.Multiplier*il.PriceLimit*il.QtyInvoiced,2) END AS LineLimitAmt,
	ROUND(i.Multiplier*il.PriceList*il.QtyInvoiced-il.LineNetAmt,2) AS LineDiscountAmt,
	CASE WHEN COALESCE(il.PriceLimit,0::numeric)=0::numeric THEN 0::numeric ELSE ROUND(i.Multiplier*il.LineNetAmt-il.PriceLimit*il.QtyInvoiced,2) END AS LineOverLimitAmt,
	il.QtyInvoiced, il.QtyEntered,
	il.Line, il.C_OrderLine_ID, il.C_UOM_ID,
    il.C_Campaign_ID, il.C_Project_ID, il.C_Activity_ID, il.C_ProjectPhase_ID, il.C_ProjectTask_ID
FROM C_Invoice_v i, C_InvoiceLine il
WHERE i.C_Invoice_ID=il.C_Invoice_ID;

--COMMENT ON TABLE C_INVOICELINE_V IS 'Invoice Line Summary for Reporting Views - Corrected for Credit Memos';



