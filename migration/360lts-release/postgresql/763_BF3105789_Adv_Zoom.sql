-- 05-nov-2010 11:46:26 COT
-- Fix for 2897194 - Advanced Zoom and RelationTypes
UPDATE AD_Ref_Table SET WhereClause='C_Order_ID=@C_Order_ID@ OR
C_Invoice_ID IN (
  SELECT i.C_Invoice_ID from /* intentionally lowercase */ C_Invoice i
    LEFT JOIN C_InvoiceLine il ON il.C_Invoice_ID = i.C_Invoice_ID
    LEFT JOIN C_OrderLine ol ON ol.C_OrderLine_ID = il.C_OrderLine_ID
  where ol.C_Order_ID=@C_Order_ID@ AND i.isSOTrx=''Y''
)',Updated=TO_TIMESTAMP('2010-11-05 11:46:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53334
;

-- 05-nov-2010 11:48:13 COT
UPDATE AD_Ref_Table SET WhereClause='C_Order_ID=@C_Order_ID@ OR
M_InOut_ID IN (
  SELECT i.M_InOut_ID from /* intentionally lowercase */ M_InOut i
    LEFT JOIN M_InOutline il ON il.M_InOut_ID = i.M_InOut_ID
    LEFT JOIN C_OrderLine ol ON ol.C_OrderLine_ID = il.C_OrderLine_ID
  where ol.C_Order_ID=@C_Order_ID@ AND i.MovementType IN (''C-'')
)',Updated=TO_TIMESTAMP('2010-11-05 11:48:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53347
;

-- 05-nov-2010 11:49:39 COT
UPDATE AD_Ref_Table SET WhereClause='C_Invoice_ID=@C_Invoice_ID@ OR
M_InOut_ID IN (
  SELECT m.M_InOut_ID from /* intentionally lowercase */ M_InOut m
    LEFT JOIN M_InOutline ml ON ml.M_InOut_ID = m.M_InOut_ID
    LEFT JOIN c_invoiceline il ON il.M_InOutline_ID = ml.M_InOutline_ID
  where il.C_Invoice_ID=@C_Invoice_ID@ AND m.MovementType IN (''C-'')
)',Updated=TO_TIMESTAMP('2010-11-05 11:49:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53345
;

