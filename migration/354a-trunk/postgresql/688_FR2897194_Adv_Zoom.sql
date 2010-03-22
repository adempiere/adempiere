-- 20.03.2010 16:57:19 MEZ
-- FR 2897194 Advanced Zoom and RelationTypes
INSERT INTO AD_Reference (AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType,AD_Client_ID) VALUES (0,53345,TO_TIMESTAMP('2010-03-20 16:57:18','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','RelType C_Invoice_ID->M_InOut',TO_TIMESTAMP('2010-03-20 16:57:18','YYYY-MM-DD HH24:MI:SS'),100,'T',0)
;

-- 20.03.2010 16:57:19 MEZ
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53345 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- 20.03.2010 16:57:55 MEZ
INSERT INTO AD_Ref_Table (AD_Display,AD_Key,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy,AD_Client_ID,WhereClause,AD_Reference_ID,AD_Table_ID) VALUES (3791,3521,0,TO_TIMESTAMP('2010-03-20 16:57:55','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N',TO_TIMESTAMP('2010-03-20 16:57:55','YYYY-MM-DD HH24:MI:SS'),100,0,'M_InOut_ID IN (
  select m.M_InOut_ID from M_InOut m
    left join M_InOutline ml on ml.M_InOut_ID = m.M_InOut_ID
    left join c_invoiceline il on il.M_InOutline_ID = ml.M_InOutline_ID
  where il.C_Invoice_ID=@C_Invoice_ID@
)',53345,319)
;

-- 20.03.2010 16:58:04 MEZ
UPDATE AD_Ref_Table SET OrderByClause=NULL,Updated=TO_TIMESTAMP('2010-03-20 16:58:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53345
;

-- 20.03.2010 16:58:43 MEZ
INSERT INTO AD_Reference (AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType,AD_Client_ID) VALUES (0,53346,TO_TIMESTAMP('2010-03-20 16:58:43','YYYY-MM-DD HH24:MI:SS'),100,'Finds C_Invoice_IDs for a given C_C_Order_ID','D','Y','N','RelType C_Order->C_Invoice',TO_TIMESTAMP('2010-03-20 16:58:43','YYYY-MM-DD HH24:MI:SS'),100,'T',0)
;

-- 20.03.2010 16:58:43 MEZ
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53346 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- 20.03.2010 16:59:18 MEZ
INSERT INTO AD_Ref_Table (AD_Display,AD_Key,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,OrderByClause,Updated,UpdatedBy,AD_Client_ID,WhereClause,AD_Reference_ID,AD_Table_ID) VALUES (3492,3484,0,TO_TIMESTAMP('2010-03-20 16:59:18','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','DocumentNo',TO_TIMESTAMP('2010-03-20 16:59:18','YYYY-MM-DD HH24:MI:SS'),100,0,'C_Invoice_ID IN (
  select i.C_Invoice_ID from C_Invoice i
    left join C_InvoiceLine il on il.C_Invoice_ID = i.C_Invoice_ID
    left join C_OrderLine ol on ol.C_OrderLine_ID = il.C_OrderLine_ID
  where ol.C_Order_ID=@C_Order_ID@
)',53346,318)
;

-- 20.03.2010 17:00:00 MEZ
INSERT INTO AD_Reference (AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType,AD_Client_ID) VALUES (0,53347,TO_TIMESTAMP('2010-03-20 16:59:59','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','RelType C_Order->M_InOut',TO_TIMESTAMP('2010-03-20 16:59:59','YYYY-MM-DD HH24:MI:SS'),100,'T',0)
;

-- 20.03.2010 17:00:00 MEZ
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53347 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- 20.03.2010 17:00:49 MEZ
INSERT INTO AD_Ref_Table (AD_Display,AD_Key,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,OrderByClause,Updated,UpdatedBy,AD_Client_ID,WhereClause,AD_Reference_ID,AD_Table_ID) VALUES (3791,3521,0,TO_TIMESTAMP('2010-03-20 17:00:49','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','DocumentNo',TO_TIMESTAMP('2010-03-20 17:00:49','YYYY-MM-DD HH24:MI:SS'),100,0,'M_InOut_ID IN (
  select i.M_InOut_ID from M_InOut i
    left join M_InOutline il on il.M_InOut_ID = i.M_InOut_ID
    left join C_OrderLine ol on ol.C_OrderLine_ID = il.C_OrderLine_ID
  where ol.C_Order_ID=@C_Order_ID@
)',53347,319)
;

-- 20.03.2010 17:01:14 MEZ
INSERT INTO AD_Reference (AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType,AD_Client_ID) VALUES (0,53348,TO_TIMESTAMP('2010-03-20 17:01:13','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','RelType M_InOut_ID->C_Invoice',TO_TIMESTAMP('2010-03-20 17:01:13','YYYY-MM-DD HH24:MI:SS'),100,'T',0)
;

-- 20.03.2010 17:01:14 MEZ
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53348 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- 20.03.2010 17:01:48 MEZ
INSERT INTO AD_Ref_Table (AD_Display,AD_Key,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,OrderByClause,Updated,UpdatedBy,AD_Client_ID,WhereClause,AD_Reference_ID,AD_Table_ID) VALUES (3492,3484,0,TO_TIMESTAMP('2010-03-20 17:01:48','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','DocumentNo',TO_TIMESTAMP('2010-03-20 17:01:48','YYYY-MM-DD HH24:MI:SS'),100,0,'C_Invoice_ID IN (
  select i.C_Invoice_ID from C_Invoice i
    left join c_invoiceline il on il.C_Invoice_ID = i.C_Invoice_ID
    left join M_InOutline ml on ml.M_InOutline_ID = il.M_InOutline_ID
  where ml.M_InOut_ID=@M_InOut_ID@
)',53348,318)
;

-- 20.03.2010 17:02:14 MEZ
INSERT INTO AD_Reference (AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType,AD_Client_ID) VALUES (0,53349,TO_TIMESTAMP('2010-03-20 17:02:08','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','RelType M_InOut_ID->C_Order',TO_TIMESTAMP('2010-03-20 17:02:08','YYYY-MM-DD HH24:MI:SS'),100,'T',0)
;

-- 20.03.2010 17:02:14 MEZ
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53349 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- 20.03.2010 17:02:48 MEZ
INSERT INTO AD_Ref_Table (AD_Display,AD_Key,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,OrderByClause,Updated,UpdatedBy,AD_Client_ID,WhereClause,AD_Reference_ID,AD_Table_ID) VALUES (2169,2161,0,TO_TIMESTAMP('2010-03-20 17:02:48','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','DocumentNo',TO_TIMESTAMP('2010-03-20 17:02:48','YYYY-MM-DD HH24:MI:SS'),100,0,'C_Order_ID IN (
  select o.c_order_id from c_order o
    left join c_orderline ol on ol.c_order_id = o.c_order_id
    left join M_InOutline il on il.c_orderline_id = ol.c_orderline_id
  where il.M_InOut_ID=@M_InOut_ID@
)',53349,259)
;

-- 20.03.2010 17:18:22 MEZ
INSERT INTO AD_RelationType (AD_Org_ID,AD_Client_ID,AD_Reference_Target_ID,AD_RelationType_ID,Created,CreatedBy,IsActive,IsDirected,Name,Type,Updated,UpdatedBy,AD_Reference_Source_ID) VALUES (0,0,53347,50002,TO_TIMESTAMP('2010-03-20 17:18:21','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','Order<->InOut','I',TO_TIMESTAMP('2010-03-20 17:18:21','YYYY-MM-DD HH24:MI:SS'),100,53349)
;

-- 20.03.2010 17:19:18 MEZ
INSERT INTO AD_RelationType (AD_Org_ID,AD_Client_ID,AD_Reference_Target_ID,AD_RelationType_ID,Created,CreatedBy,IsActive,IsDirected,Name,Type,Updated,UpdatedBy,AD_Reference_Source_ID) VALUES (0,0,53345,50003,TO_TIMESTAMP('2010-03-20 17:19:17','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','Invoice<->InOut','I',TO_TIMESTAMP('2010-03-20 17:19:17','YYYY-MM-DD HH24:MI:SS'),100,53348)
;

-- 22.03.2010 07:40:40 MEZ
INSERT INTO AD_Reference (AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType,AD_Client_ID) VALUES (0,53352,TO_TIMESTAMP('2010-03-22 07:40:39','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','RelType C_BPartner_ID->Customer_RMA',TO_TIMESTAMP('2010-03-22 07:40:39','YYYY-MM-DD HH24:MI:SS'),100,'T',0)
;

-- 22.03.2010 07:40:40 MEZ
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53352 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- 22.03.2010 07:44:03 MEZ
INSERT INTO AD_Ref_Table (AD_Display,AD_Key,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy,AD_Client_ID,WhereClause,AD_Reference_ID,AD_Table_ID) VALUES (2901,2893,0,TO_TIMESTAMP('2010-03-22 07:44:03','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N',TO_TIMESTAMP('2010-03-22 07:44:03','YYYY-MM-DD HH24:MI:SS'),100,0,'M_RMA.IsSOTrx=''Y'' AND M_RMA.C_BPartner_ID=@C_BPartner_ID@',53352,291)
;

-- 22.03.2010 07:44:30 MEZ
INSERT INTO AD_Reference (AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType,AD_Client_ID) VALUES (0,53353,TO_TIMESTAMP('2010-03-22 07:44:29','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','RelType Customer_RMA->C_BPartner_ID',TO_TIMESTAMP('2010-03-22 07:44:29','YYYY-MM-DD HH24:MI:SS'),100,'T',0)
;

-- 22.03.2010 07:44:30 MEZ
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53353 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- 22.03.2010 07:46:44 MEZ
INSERT INTO AD_Ref_Table (AD_Display,AD_Key,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,OrderByClause,Updated,UpdatedBy,AD_Client_ID,WhereClause,AD_Reference_ID,AD_Table_ID) VALUES (10841,10847,0,TO_TIMESTAMP('2010-03-22 07:46:44','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','Value',TO_TIMESTAMP('2010-03-22 07:46:44','YYYY-MM-DD HH24:MI:SS'),100,0,'C_BPartner_ID=@C_BPartner_ID@',53353,661)
;

-- 22.03.2010 07:49:29 MEZ
UPDATE AD_Ref_Table SET WhereClause='M_RMA.IsSOTrx=''Y'' AND M_RMA.C_BPartner_ID=@C_BPartner_ID@',Updated=TO_TIMESTAMP('2010-03-22 07:49:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53353
;

-- 22.03.2010 07:49:38 MEZ
UPDATE AD_Reference SET Name='RelType Customer_RMA<=C_BPartner_ID',Updated=TO_TIMESTAMP('2010-03-22 07:49:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53353
;

-- 22.03.2010 07:49:38 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53353
;

-- 22.03.2010 07:50:20 MEZ
UPDATE AD_Reference SET Name='RelType C_BPartner_ID<=Customer_RMA',Updated=TO_TIMESTAMP('2010-03-22 07:50:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53352
;

-- 22.03.2010 07:50:20 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53352
;

-- 22.03.2010 07:50:48 MEZ
UPDATE AD_Ref_Table SET WhereClause='C_BPartner.C_BPartner_ID=@C_BPartner_ID@',Updated=TO_TIMESTAMP('2010-03-22 07:50:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53352
;

-- 22.03.2010 07:51:07 MEZ
UPDATE AD_Reference SET Name='RelType C_BPartner_ID <= Customer_RMA',Updated=TO_TIMESTAMP('2010-03-22 07:51:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53352
;

-- 22.03.2010 07:51:07 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53352
;

-- 22.03.2010 07:51:13 MEZ
UPDATE AD_Reference SET Name='RelType C_Invoice_ID <= C_Order',Updated=TO_TIMESTAMP('2010-03-22 07:51:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53333
;

-- 22.03.2010 07:51:13 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53333
;

-- 22.03.2010 07:51:19 MEZ
UPDATE AD_Reference SET Name='RelType C_Invoice_ID <= M_InOut',Updated=TO_TIMESTAMP('2010-03-22 07:51:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53345
;

-- 22.03.2010 07:51:19 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53345
;

-- 22.03.2010 07:51:26 MEZ
UPDATE AD_Reference SET Name='RelType C_Order <= C_Invoice',Updated=TO_TIMESTAMP('2010-03-22 07:51:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53346
;

-- 22.03.2010 07:51:26 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53346
;

-- 22.03.2010 07:51:29 MEZ
UPDATE AD_Reference SET Name='RelType C_Order_ID <= C_Invoice',Updated=TO_TIMESTAMP('2010-03-22 07:51:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53334
;

-- 22.03.2010 07:51:29 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53334
;

-- 22.03.2010 07:51:32 MEZ
UPDATE AD_Reference SET Name='RelType C_Order <= M_InOut',Updated=TO_TIMESTAMP('2010-03-22 07:51:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53347
;

-- 22.03.2010 07:51:32 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53347
;

-- 22.03.2010 07:51:35 MEZ
UPDATE AD_Reference SET Name='RelType Customer_RMA <= C_BPartner_ID',Updated=TO_TIMESTAMP('2010-03-22 07:51:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53353
;

-- 22.03.2010 07:51:35 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53353
;

-- 22.03.2010 07:51:38 MEZ
UPDATE AD_Reference SET Name='RelType M_InOut_ID <= C_Invoice',Updated=TO_TIMESTAMP('2010-03-22 07:51:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53348
;

-- 22.03.2010 07:51:38 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53348
;

-- 22.03.2010 07:51:43 MEZ
UPDATE AD_Reference SET Name='RelType M_InOut_ID <= C_Order',Updated=TO_TIMESTAMP('2010-03-22 07:51:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53349
;

-- 22.03.2010 07:51:43 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53349
;

-- 22.03.2010 07:52:11 MEZ
UPDATE AD_Reference SET Name='RelType C_Order <=  M_InOut_ID',Updated=TO_TIMESTAMP('2010-03-22 07:52:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53349
;

-- 22.03.2010 07:52:11 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53349
;

-- 22.03.2010 07:52:22 MEZ
UPDATE AD_Reference SET Name='RelType C_Invoice <= M_InOut_ID',Updated=TO_TIMESTAMP('2010-03-22 07:52:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53348
;

-- 22.03.2010 07:52:22 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53348
;

-- 22.03.2010 07:52:58 MEZ
UPDATE AD_Reference SET Name='RelType M_InOut <=  C_Order',Updated=TO_TIMESTAMP('2010-03-22 07:52:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53347
;

-- 22.03.2010 07:52:58 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53347
;

-- 22.03.2010 07:53:10 MEZ
UPDATE AD_Reference SET Name='RelType C_Invoice <= C_Order_ID',Updated=TO_TIMESTAMP('2010-03-22 07:53:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53334
;

-- 22.03.2010 07:53:10 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53334
;

-- 22.03.2010 07:53:48 MEZ
DELETE FROM AD_Reference_Trl WHERE AD_Reference_ID=53346
;

-- 22.03.2010 07:53:48 MEZ
DELETE FROM AD_Reference WHERE AD_Reference_ID=53346
;

-- 22.03.2010 07:53:59 MEZ
UPDATE AD_Reference SET Name='RelType M_InOut <= C_Invoice_ID',Updated=TO_TIMESTAMP('2010-03-22 07:53:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53345
;

-- 22.03.2010 07:53:59 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53345
;

-- 22.03.2010 07:55:18 MEZ
UPDATE AD_Reference SET Name='RelType C_Order <= C_Invoice_ID',Updated=TO_TIMESTAMP('2010-03-22 07:55:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53333
;

-- 22.03.2010 07:55:18 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53333
;

-- 22.03.2010 07:55:23 MEZ
UPDATE AD_Reference SET Name='RelType M_InOut <=  C_Order_ID',Updated=TO_TIMESTAMP('2010-03-22 07:55:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53347
;

-- 22.03.2010 07:55:23 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53347
;

-- 22.03.2010 07:55:52 MEZ
UPDATE AD_Reference SET Name='RelType C_BPartner <= Customer_RMA_ID',Updated=TO_TIMESTAMP('2010-03-22 07:55:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53352
;

-- 22.03.2010 07:55:52 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53352
;

-- 22.03.2010 07:56:13 MEZ
UPDATE AD_Reference SET Name='RelType C_BPartner <= RMA_ID',Updated=TO_TIMESTAMP('2010-03-22 07:56:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53352
;

-- 22.03.2010 07:56:13 MEZ
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53352
;

-- 22.03.2010 07:56:33 MEZ
INSERT INTO AD_Reference (AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType,AD_Client_ID) VALUES (0,53354,TO_TIMESTAMP('2010-03-22 07:56:32','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','RelType Vendor_RMA <= C_BPartner_ID',TO_TIMESTAMP('2010-03-22 07:56:32','YYYY-MM-DD HH24:MI:SS'),100,'T',0)
;

-- 22.03.2010 07:56:33 MEZ
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53354 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- 22.03.2010 07:57:23 MEZ
INSERT INTO AD_Ref_Table (AD_Display,AD_Key,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,OrderByClause,Updated,UpdatedBy,AD_Client_ID,WhereClause,AD_Reference_ID,AD_Table_ID) VALUES (10841,10847,0,TO_TIMESTAMP('2010-03-22 07:57:23','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','DocumentNo',TO_TIMESTAMP('2010-03-22 07:57:23','YYYY-MM-DD HH24:MI:SS'),100,0,'M_RMA.IsSOTrx=''N'' AND M_RMA.C_BPartner_ID=@C_BPartner_ID@',53354,661)
;

-- 22.03.2010 08:00:23 MEZ
INSERT INTO AD_RelationType (AD_Org_ID,AD_Client_ID,AD_Reference_Target_ID,AD_RelationType_ID,Created,CreatedBy,IsActive,IsDirected,Name,Type,Updated,UpdatedBy,AD_Reference_Source_ID) VALUES (0,0,53354,50004,TO_TIMESTAMP('2010-03-22 08:00:22','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','Customer<->VendorRMA','I',TO_TIMESTAMP('2010-03-22 08:00:22','YYYY-MM-DD HH24:MI:SS'),100,53352)
;

-- 22.03.2010 08:00:51 MEZ
INSERT INTO AD_RelationType (AD_Org_ID,AD_Client_ID,AD_RelationType_ID,Created,CreatedBy,IsActive,IsDirected,Name,Type,Updated,UpdatedBy) VALUES (0,0,50005,TO_TIMESTAMP('2010-03-22 08:00:50','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','BPartner<->CustomerRMA','I',TO_TIMESTAMP('2010-03-22 08:00:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 22.03.2010 08:00:57 MEZ
UPDATE AD_RelationType SET Name='BPartner<->VendorRMA',Updated=TO_TIMESTAMP('2010-03-22 08:00:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_RelationType_ID=50004
;

-- 22.03.2010 08:01:16 MEZ
UPDATE AD_RelationType SET AD_Reference_Target_ID=53353, AD_Reference_Source_ID=53352,Updated=TO_TIMESTAMP('2010-03-22 08:01:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_RelationType_ID=50005
;

-- Mar 22, 2010 1:36:03 PM COT
UPDATE AD_RelationType SET Name='BPartner<->VendorReturn',Updated=TO_TIMESTAMP('2010-03-22 13:36:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_RelationType_ID=50004
;

-- Mar 22, 2010 1:37:03 PM COT
UPDATE AD_Reference SET EntityType='D', Name='RelType Vendor Return <= C_BPartner_ID',Updated=TO_TIMESTAMP('2010-03-22 13:37:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53354
;

-- Mar 22, 2010 1:37:03 PM COT
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53354
;

-- Mar 22, 2010 1:37:08 PM COT
UPDATE AD_Reference SET Name='RelType C_BPartner <= Vendor Return',Updated=TO_TIMESTAMP('2010-03-22 13:37:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53352
;

-- Mar 22, 2010 1:37:08 PM COT
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53352
;

-- Mar 22, 2010 1:39:08 PM COT
UPDATE AD_Ref_Table SET AD_Display=3791, AD_Key=3521, AD_Table_ID=319, WhereClause='M_InOut.MovementType IN (''V+'') AND M_InOut.C_BPartner_ID=@C_BPartner_ID@',Updated=TO_TIMESTAMP('2010-03-22 13:39:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53354
;

-- Mar 22, 2010 1:39:54 PM COT
UPDATE AD_RelationType SET Name='BPartner<->Customer Return',Updated=TO_TIMESTAMP('2010-03-22 13:39:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_RelationType_ID=50005
;

-- Mar 22, 2010 1:40:28 PM COT
UPDATE AD_Reference SET Name='RelType C_BPartner <= Vendor/Customer Return',Updated=TO_TIMESTAMP('2010-03-22 13:40:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53352
;

-- Mar 22, 2010 1:40:28 PM COT
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53352
;

-- Mar 22, 2010 1:40:39 PM COT
UPDATE AD_Reference SET Name='RelType Customer Return <= C_BPartner_ID',Updated=TO_TIMESTAMP('2010-03-22 13:40:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53353
;

-- Mar 22, 2010 1:40:39 PM COT
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53353
;

-- Mar 22, 2010 1:41:09 PM COT
UPDATE AD_Ref_Table SET AD_Display=3791, AD_Key=3521, AD_Table_ID=319, WhereClause='M_InOut.MovementType IN (''C-'') AND M_InOut.C_BPartner_ID=@C_BPartner_ID@',Updated=TO_TIMESTAMP('2010-03-22 13:41:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53353
;

-- Mar 22, 2010 1:48:48 PM COT
UPDATE AD_Ref_Table SET AD_Window_ID=53097,Updated=TO_TIMESTAMP('2010-03-22 13:48:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53354
;

-- Mar 22, 2010 1:49:28 PM COT
UPDATE AD_Ref_Table SET AD_Window_ID=53098,Updated=TO_TIMESTAMP('2010-03-22 13:49:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53354
;

-- Mar 22, 2010 1:49:55 PM COT
UPDATE AD_Ref_Table SET WhereClause='M_InOut.MovementType IN (''V-'') AND M_InOut.C_BPartner_ID=@C_BPartner_ID@',Updated=TO_TIMESTAMP('2010-03-22 13:49:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53354
;

-- Mar 22, 2010 1:51:01 PM COT
UPDATE AD_Ref_Table SET AD_Window_ID=53097, WhereClause='M_InOut.MovementType IN (''C+'') AND M_InOut.C_BPartner_ID=@C_BPartner_ID@',Updated=TO_TIMESTAMP('2010-03-22 13:51:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53353
;

