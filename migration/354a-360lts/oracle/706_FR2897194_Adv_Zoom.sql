-- Apr 8, 2010 6:02:59 PM COT
-- FR 2897194 Advanced Zoom and RelationTypes
UPDATE AD_Reference SET Name='RelType M_InOut (Shipment) <=  C_Order_ID',Updated=TO_DATE('2010-04-08 18:02:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53347
;

-- Apr 8, 2010 6:02:59 PM COT
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53347
;

-- Apr 8, 2010 6:03:11 PM COT
UPDATE AD_RelationType SET Name='Order<->InOut (Shipment)',Updated=TO_DATE('2010-04-08 18:03:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_RelationType_ID=50002
;

-- Apr 8, 2010 6:10:36 PM COT
UPDATE AD_Ref_Table SET WhereClause='M_InOut_ID IN (
  select i.M_InOut_ID from M_InOut i
    left join M_InOutline il on il.M_InOut_ID = i.M_InOut_ID
    left join C_OrderLine ol on ol.C_OrderLine_ID = il.C_OrderLine_ID
  where ol.C_Order_ID=@C_Order_ID@ AND M_InOut.MovementType IN (''C-'')
)',Updated=TO_DATE('2010-04-08 18:10:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53347
;

-- Apr 8, 2010 6:12:31 PM COT
UPDATE AD_Reference SET Name='RelType C_Order <=  M_InOut_ID (Shipment)',Updated=TO_DATE('2010-04-08 18:12:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53349
;

-- Apr 8, 2010 6:12:31 PM COT
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53349
;

-- Apr 8, 2010 6:17:51 PM COT
UPDATE AD_Ref_Table SET WhereClause='C_Order_ID IN (
  select o.c_order_id from c_order o
    left join c_orderline ol on ol.c_order_id = o.c_order_id
    left join M_InOutline il on il.c_orderline_id = ol.c_orderline_id
  where il.M_InOut_ID=@M_InOut_ID@ AND o.isSOTrx=''Y''
)',Updated=TO_DATE('2010-04-08 18:17:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53349
;

-- Apr 8, 2010 6:17:58 PM COT
UPDATE AD_Reference SET Name='RelType C_Order (Sales) <=  M_InOut_ID (Shipment)',Updated=TO_DATE('2010-04-08 18:17:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53349
;

-- Apr 8, 2010 6:17:58 PM COT
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53349
;

-- Apr 8, 2010 6:18:08 PM COT
UPDATE AD_RelationType SET Name='Order (Sales) <->InOut (Shipment)',Updated=TO_DATE('2010-04-08 18:18:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_RelationType_ID=50002
;

-- Apr 8, 2010 6:19:22 PM COT
UPDATE AD_Reference SET Name='RelType C_Order (Sales) <= C_Invoice_ID (Customer)',Updated=TO_DATE('2010-04-08 18:19:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53333
;

-- Apr 8, 2010 6:19:22 PM COT
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53333
;

-- Apr 8, 2010 6:19:45 PM COT
UPDATE AD_Ref_Table SET WhereClause='C_Order_ID IN (
  select o.c_order_id from c_order o
    left join c_orderline ol on o.c_order_id = ol.c_order_id
    left join c_invoiceline il on ol.c_orderline_id = il.c_orderline_id
  where il.C_Invoice_ID=@C_Invoice_ID@ AND o.isSOTrx=''Y''
)',Updated=TO_DATE('2010-04-08 18:19:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53333
;

-- Apr 8, 2010 6:20:00 PM COT
UPDATE AD_Reference SET Name='RelType C_Invoice (Customer) <= C_Order_ID (Sales)',Updated=TO_DATE('2010-04-08 18:20:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53334
;

-- Apr 8, 2010 6:20:00 PM COT
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53334
;

-- Apr 8, 2010 6:20:13 PM COT
UPDATE AD_Ref_Table SET WhereClause='C_Invoice_ID IN (
  select i.C_Invoice_ID from C_Invoice i
    left join C_InvoiceLine il on il.C_Invoice_ID = i.C_Invoice_ID
    left join C_OrderLine ol on ol.C_OrderLine_ID = il.C_OrderLine_ID
  where ol.C_Order_ID=@C_Order_ID@ AND i.isSOTrx=''Y''
)',Updated=TO_DATE('2010-04-08 18:20:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53334
;

-- Apr 8, 2010 6:20:24 PM COT
UPDATE AD_RelationType SET Name='Order (Sales) <->Invoice (Customer)',Updated=TO_DATE('2010-04-08 18:20:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_RelationType_ID=50001
;

-- Apr 8, 2010 6:21:09 PM COT
UPDATE AD_RelationType SET Name='Invoice (Customer) <->InOut (Shipment)',Updated=TO_DATE('2010-04-08 18:21:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_RelationType_ID=50003
;

-- Apr 8, 2010 6:21:31 PM COT
UPDATE AD_Reference SET Name='RelType C_Invoice (Customer) <= M_InOut_ID (Shipment)',Updated=TO_DATE('2010-04-08 18:21:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53348
;

-- Apr 8, 2010 6:21:31 PM COT
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53348
;

-- Apr 8, 2010 6:21:44 PM COT
UPDATE AD_Ref_Table SET WhereClause='C_Invoice_ID IN (
  select i.C_Invoice_ID from C_Invoice i
    left join c_invoiceline il on il.C_Invoice_ID = i.C_Invoice_ID
    left join M_InOutline ml on ml.M_InOutline_ID = il.M_InOutline_ID
  where ml.M_InOut_ID=@M_InOut_ID@ AND i.isSOTrx=''Y''
)',Updated=TO_DATE('2010-04-08 18:21:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53348
;

-- Apr 8, 2010 6:21:57 PM COT
UPDATE AD_Reference SET Name='RelType M_InOut (Shipment) <= C_Invoice_ID (Customer)',Updated=TO_DATE('2010-04-08 18:21:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53345
;

-- Apr 8, 2010 6:21:57 PM COT
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53345
;

-- Apr 8, 2010 6:22:17 PM COT
UPDATE AD_Ref_Table SET WhereClause='M_InOut_ID IN (
  select m.M_InOut_ID from M_InOut m
    left join M_InOutline ml on ml.M_InOut_ID = m.M_InOut_ID
    left join c_invoiceline il on il.M_InOutline_ID = ml.M_InOutline_ID
  where il.C_Invoice_ID=@C_Invoice_ID@ AND m.MovementType IN (''C-'')
)',Updated=TO_DATE('2010-04-08 18:22:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53345
;

