-- Jul 26, 2008 5:50:38 PM COT
-- [ 1783032 ] Add DocAction parameter to Generate Invoices (manual) form
UPDATE AD_Val_Rule SET Code='DocStatus NOT IN (''DR'',''IP'')  AND EXISTS (SELECT * FROM C_OrderLine WHERE C_Order.C_Order_ID=C_OrderLine.C_Order_ID AND QtyOrdered <> QtyInvoiced) AND IsSOTrx=''Y'' AND NOT EXISTS (SELECT * FROM C_Invoice i WHERE i.C_Order_ID=C_Order.C_Order_ID AND i.DocStatus IN (''IP'', ''CO'', ''CL''))',Updated=TO_TIMESTAMP('2008-07-26 17:50:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=134
;

