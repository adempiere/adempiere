-- 15-Jun-2009 18:17:07 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_ReportView_ID,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('3',0,0,53176,100,TO_TIMESTAMP('2009-06-15 18:17:06','YYYY-MM-DD HH24:MI:SS'),100,'Sales Order Transaction Report','D','Y','N','N','Y','N','Order Transactions','Y',0,0,TO_TIMESTAMP('2009-06-15 18:17:06','YYYY-MM-DD HH24:MI:SS'),100,'RV_Order_Trx')
;

-- 15-Jun-2009 18:17:07 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53176 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;
-- 15-Jun-2009 18:34:14 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,DefaultValue2,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53176,53319,15,'DateOrdered',TO_TIMESTAMP('2009-06-15 18:34:13','YYYY-MM-DD HH24:MI:SS'),100,'@#Date@','@#Date@','Date of the Order','D',11,'Indicates the Date the item was ordered.','Y','Y','Y','Y','Date Ordered',10,TO_TIMESTAMP('2009-06-15 18:34:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 15-Jun-2009 18:34:14 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53319 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 15-Jun-2009 18:35:32 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53176,53320,20,'IsSOTrx',TO_TIMESTAMP('2009-06-15 18:35:31','YYYY-MM-DD HH24:MI:SS'),100,'Y','This is a Sales Transaction','D',1,'The Sales Transaction checkbox indicates if this item is a Sales Transaction.','Y','Y','N','N','Sales Transaction',20,TO_TIMESTAMP('2009-06-15 18:35:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 15-Jun-2009 18:35:32 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53320 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 15-Jun-2009 18:37:25 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
INSERT INTO AD_Menu ("action",AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('R',0,53223,0,53176,TO_TIMESTAMP('2009-06-15 18:37:24','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','Order Transactions',TO_TIMESTAMP('2009-06-15 18:37:24','YYYY-MM-DD HH24:MI:SS'),100)
;
-- 15-Jun-2009 18:37:25 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53223 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- 15-Jun-2009 18:37:25 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 53223, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53223)
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=166
;
-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=1000000
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=13, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=1000007
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53223
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=241
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=288
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=432
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=243
;
-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=413
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=538
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=462
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=505
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=235
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=511
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=245
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=251
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=13, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=246
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=14, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=509
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=15, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=510
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=16, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=496
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=17, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=497
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=18, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=304
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=19, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=255
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=20, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=286
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=21, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=287
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=22, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=438
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=23, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=234
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=24, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=244
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=25, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53190
;

-- 15-Jun-2009 18:37:44 BST
-- BF 2806709 - Reinstate Lost Report: Sales Tranactions
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=26, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53187
;

