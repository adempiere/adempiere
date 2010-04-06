-- Mar 24, 2010 9:47:27 AM EST
-- Java POS improvements
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,CopyFromProcess,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('1',0,0,53202,'org.compiere.process.POSKeyGenerate','N',TO_TIMESTAMP('2010-03-24 09:47:24','YYYY-MM-DD HH24:MI:SS'),100,'Generate POS Keys from products.','D','Y','N','N','N','N','POS Key Generate','Y',0,0,TO_TIMESTAMP('2010-03-24 09:47:24','YYYY-MM-DD HH24:MI:SS'),100,'C_POSKeyGenerate')
;

-- Mar 24, 2010 9:47:27 AM EST
-- Java POS improvements
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53202 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Mar 24, 2010 9:48:46 AM EST
-- Java POS improvements
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2583,0,53202,53405,19,'C_POSKeyLayout_ID',TO_TIMESTAMP('2010-03-24 09:48:45','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','POS Key Layout',10,TO_TIMESTAMP('2010-03-24 09:48:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 9:48:46 AM EST
-- Java POS improvements
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53405 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Mar 24, 2010 9:49:40 AM EST
-- Java POS improvements
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,453,0,53202,53406,19,'M_Product_Category_ID',TO_TIMESTAMP('2010-03-24 09:49:37','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','Product Category',20,TO_TIMESTAMP('2010-03-24 09:49:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 9:49:40 AM EST
-- Java POS improvements
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53406 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Mar 24, 2010 9:49:43 AM EST
-- Java POS improvements
UPDATE AD_Process_Para SET IsMandatory='Y',Updated=TO_TIMESTAMP('2010-03-24 09:49:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53405
;

-- Mar 24, 2010 9:56:32 AM EST
-- Java POS improvements
INSERT INTO AD_Menu ("action",AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('P',0,53269,0,53202,TO_TIMESTAMP('2010-03-24 09:56:31','YYYY-MM-DD HH24:MI:SS'),100,NULL,'D','Y','N','N','N','POS Key Generate',TO_TIMESTAMP('2010-03-24 09:56:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 9:56:32 AM EST
-- Java POS improvements
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53269 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Mar 24, 2010 9:56:32 AM EST
-- Java POS improvements
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID, 0, 'Y', CURRENT_TIMESTAMP, 100, CURRENT_TIMESTAMP, 100,t.AD_Tree_ID, 53269, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53269)
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=52001
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=460
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=301
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53249
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=129
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=543
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=195
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53223
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=407
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=406
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=335
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=436
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=507
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=13, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=448
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=14, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=449
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=15, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=492
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=16, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53269
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=17, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=491
;

-- Mar 24, 2010 9:56:36 AM EST
-- Java POS improvements
UPDATE AD_TreeNodeMM SET Parent_ID=457, SeqNo=18, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=419
;

-- Mar 24, 2010 10:02:43 AM EST
-- Java POS improvements
UPDATE AD_Process SET Classname='org.compiere.process.PosKeyGenerate',Updated=TO_TIMESTAMP('2010-03-24 10:02:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53202
;

-- Mar 24, 2010 11:02:09 AM EST
-- Java POS improvements
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,52072,0,TO_TIMESTAMP('2008-03-26 13:20:02','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Subtotal',NULL,'I',TO_TIMESTAMP('2008-03-26 13:20:02','YYYY-MM-DD HH24:MI:SS'),100,'SubTotal')
;

-- Mar 24, 2010 11:03:05 AM EST
-- Java POS improvements
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53096,0,TO_TIMESTAMP('2010-03-24 11:02:58','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Subtotal','I',TO_TIMESTAMP('2010-03-24 11:02:58','YYYY-MM-DD HH24:MI:SS'),100,'SubTotal')
;

-- Mar 24, 2010 11:03:05 AM EST
-- Java POS improvements
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53096 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Mar 24, 2010 11:04:48 AM EST
-- Java POS improvements
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53097,0,TO_TIMESTAMP('2010-03-24 11:04:47','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Tender Amount','I',TO_TIMESTAMP('2010-03-24 11:04:47','YYYY-MM-DD HH24:MI:SS'),100,'AmountTendered')
;

-- Mar 24, 2010 11:04:48 AM EST
-- Java POS improvements
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53097 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Mar 24, 2010 11:05:04 AM EST
-- Java POS improvements
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53098,0,TO_TIMESTAMP('2010-03-24 11:05:03','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Change','I',TO_TIMESTAMP('2010-03-24 11:05:03','YYYY-MM-DD HH24:MI:SS'),100,'AmountReturned')
;

-- Mar 24, 2010 11:05:04 AM EST
-- Java POS improvements
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53098 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Mar 24, 2010 10:01:46 AM EST
-- Java POS improvements
INSERT INTO C_POSKeyLayout (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,Columns,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,Name,POSKeyLayoutType,Updated,UpdatedBy) VALUES (11,0,104,129,4,50003,TO_TIMESTAMP('2010-03-24 10:01:41','YYYY-MM-DD HH24:MI:SS'),100,'Y','All Products','P',TO_TIMESTAMP('2010-03-24 10:01:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:07:53 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50092,50003,TO_TIMESTAMP('2010-03-24 10:07:50','YYYY-MM-DD HH24:MI:SS'),100,'Y',50019,'Assembly Area',TO_TIMESTAMP('2010-03-24 10:07:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:07:54 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50093,50003,TO_TIMESTAMP('2010-03-24 10:07:53','YYYY-MM-DD HH24:MI:SS'),100,'Y',128,'Azalea Bush',TO_TIMESTAMP('2010-03-24 10:07:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:07:55 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50094,50003,TO_TIMESTAMP('2010-03-24 10:07:54','YYYY-MM-DD HH24:MI:SS'),100,'Y',50016,'Back Leg',TO_TIMESTAMP('2010-03-24 10:07:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:07:56 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50095,50003,TO_TIMESTAMP('2010-03-24 10:07:55','YYYY-MM-DD HH24:MI:SS'),100,'Y',50005,'Back Support',TO_TIMESTAMP('2010-03-24 10:07:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:07:57 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50096,50003,TO_TIMESTAMP('2010-03-24 10:07:56','YYYY-MM-DD HH24:MI:SS'),100,'Y',50013,'Bag 50 Kg',TO_TIMESTAMP('2010-03-24 10:07:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:07:57 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50097,50003,TO_TIMESTAMP('2010-03-24 10:07:57','YYYY-MM-DD HH24:MI:SS'),100,'Y',50014,'Bag 70 Kg',TO_TIMESTAMP('2010-03-24 10:07:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:07:59 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50098,50003,TO_TIMESTAMP('2010-03-24 10:07:57','YYYY-MM-DD HH24:MI:SS'),100,'Y',50021,'Chrome Subcontract Area',TO_TIMESTAMP('2010-03-24 10:07:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:07:59 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50099,50003,TO_TIMESTAMP('2010-03-24 10:07:59','YYYY-MM-DD HH24:MI:SS'),100,'Y',146,'How To Plant',TO_TIMESTAMP('2010-03-24 10:07:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:02 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50100,50003,TO_TIMESTAMP('2010-03-24 10:07:59','YYYY-MM-DD HH24:MI:SS'),100,'Y',50026,'Dry Area',TO_TIMESTAMP('2010-03-24 10:07:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:03 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50101,50003,TO_TIMESTAMP('2010-03-24 10:08:02','YYYY-MM-DD HH24:MI:SS'),100,'Y',124,'Elm Tree',TO_TIMESTAMP('2010-03-24 10:08:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:04 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50102,50003,TO_TIMESTAMP('2010-03-24 10:08:03','YYYY-MM-DD HH24:MI:SS'),100,'Y',50008,'Lawn Fertilizer',TO_TIMESTAMP('2010-03-24 10:08:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:05 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50103,50003,TO_TIMESTAMP('2010-03-24 10:08:04','YYYY-MM-DD HH24:MI:SS'),100,'Y',136,'Fertilizer #50',TO_TIMESTAMP('2010-03-24 10:08:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:06 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50104,50003,TO_TIMESTAMP('2010-03-24 10:08:05','YYYY-MM-DD HH24:MI:SS'),100,'Y',50007,'Fertilizer #70',TO_TIMESTAMP('2010-03-24 10:08:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:07 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50105,50003,TO_TIMESTAMP('2010-03-24 10:08:06','YYYY-MM-DD HH24:MI:SS'),100,'Y',50027,'Fertilizer Inspection Area',TO_TIMESTAMP('2010-03-24 10:08:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:08 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50106,50003,TO_TIMESTAMP('2010-03-24 10:08:07','YYYY-MM-DD HH24:MI:SS'),100,'Y',50018,'Fertilizer Plant',TO_TIMESTAMP('2010-03-24 10:08:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:09 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50107,50003,TO_TIMESTAMP('2010-03-24 10:08:08','YYYY-MM-DD HH24:MI:SS'),100,'Y',50015,'Front Leg',TO_TIMESTAMP('2010-03-24 10:08:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:10 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50108,50003,TO_TIMESTAMP('2010-03-24 10:08:09','YYYY-MM-DD HH24:MI:SS'),100,'Y',50023,'Furniture Plant',TO_TIMESTAMP('2010-03-24 10:08:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:11 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50109,50003,TO_TIMESTAMP('2010-03-24 10:08:10','YYYY-MM-DD HH24:MI:SS'),100,'Y',125,'Grass Seed Container',TO_TIMESTAMP('2010-03-24 10:08:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:12 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50110,50003,TO_TIMESTAMP('2010-03-24 10:08:11','YYYY-MM-DD HH24:MI:SS'),100,'Y',138,'Hoe 4 ft',TO_TIMESTAMP('2010-03-24 10:08:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:13 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50111,50003,TO_TIMESTAMP('2010-03-24 10:08:12','YYYY-MM-DD HH24:MI:SS'),100,'Y',129,'Holly Bush',TO_TIMESTAMP('2010-03-24 10:08:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:14 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50112,50003,TO_TIMESTAMP('2010-03-24 10:08:13','YYYY-MM-DD HH24:MI:SS'),100,'Y',50022,'Inspection Area',TO_TIMESTAMP('2010-03-24 10:08:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:15 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50113,50003,TO_TIMESTAMP('2010-03-24 10:08:14','YYYY-MM-DD HH24:MI:SS'),100,'Y',132,'Mary Consultant',TO_TIMESTAMP('2010-03-24 10:08:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:16 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50114,50003,TO_TIMESTAMP('2010-03-24 10:08:15','YYYY-MM-DD HH24:MI:SS'),100,'Y',50025,'Mixed Area',TO_TIMESTAMP('2010-03-24 10:08:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:17 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50115,50003,TO_TIMESTAMP('2010-03-24 10:08:16','YYYY-MM-DD HH24:MI:SS'),100,'Y',137,'Mulch 10#',TO_TIMESTAMP('2010-03-24 10:08:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:18 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50116,50003,TO_TIMESTAMP('2010-03-24 10:08:17','YYYY-MM-DD HH24:MI:SS'),100,'Y',50009,'Nitrogen',TO_TIMESTAMP('2010-03-24 10:08:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:18 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50117,50003,TO_TIMESTAMP('2010-03-24 10:08:18','YYYY-MM-DD HH24:MI:SS'),100,'Y',123,'Oak Tree',TO_TIMESTAMP('2010-03-24 10:08:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:19 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50118,50003,TO_TIMESTAMP('2010-03-24 10:08:18','YYYY-MM-DD HH24:MI:SS'),100,'Y',50024,'Packing Production Line',TO_TIMESTAMP('2010-03-24 10:08:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:22 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50119,50003,TO_TIMESTAMP('2010-03-24 10:08:19','YYYY-MM-DD HH24:MI:SS'),100,'Y',50020,'Paint Area',TO_TIMESTAMP('2010-03-24 10:08:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:23 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50120,50003,TO_TIMESTAMP('2010-03-24 10:08:22','YYYY-MM-DD HH24:MI:SS'),100,'Y',145,'Patio Furniture Set',TO_TIMESTAMP('2010-03-24 10:08:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:24 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50121,50003,TO_TIMESTAMP('2010-03-24 10:08:23','YYYY-MM-DD HH24:MI:SS'),100,'Y',50000,'Assembly Back Leg',TO_TIMESTAMP('2010-03-24 10:08:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:25 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50122,50003,TO_TIMESTAMP('2010-03-24 10:08:24','YYYY-MM-DD HH24:MI:SS'),100,'Y',133,'Patio Chair',TO_TIMESTAMP('2010-03-24 10:08:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:25 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50123,50003,TO_TIMESTAMP('2010-03-24 10:08:25','YYYY-MM-DD HH24:MI:SS'),100,'Y',50001,'Assembly Front Leg',TO_TIMESTAMP('2010-03-24 10:08:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:26 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50124,50003,TO_TIMESTAMP('2010-03-24 10:08:25','YYYY-MM-DD HH24:MI:SS'),100,'Y',50010,'Phosphorus',TO_TIMESTAMP('2010-03-24 10:08:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:27 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50125,50003,TO_TIMESTAMP('2010-03-24 10:08:26','YYYY-MM-DD HH24:MI:SS'),100,'Y',126,'Planting Service',TO_TIMESTAMP('2010-03-24 10:08:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:28 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50126,50003,TO_TIMESTAMP('2010-03-24 10:08:27','YYYY-MM-DD HH24:MI:SS'),100,'Y',130,'Plum Tree',TO_TIMESTAMP('2010-03-24 10:08:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:29 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50127,50003,TO_TIMESTAMP('2010-03-24 10:08:28','YYYY-MM-DD HH24:MI:SS'),100,'Y',50012,'Potassium',TO_TIMESTAMP('2010-03-24 10:08:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:30 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50128,50003,TO_TIMESTAMP('2010-03-24 10:08:29','YYYY-MM-DD HH24:MI:SS'),100,'Y',134,'Patio Table',TO_TIMESTAMP('2010-03-24 10:08:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:31 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50129,50003,TO_TIMESTAMP('2010-03-24 10:08:30','YYYY-MM-DD HH24:MI:SS'),100,'Y',139,'Rake Bamboo',TO_TIMESTAMP('2010-03-24 10:08:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:32 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50130,50003,TO_TIMESTAMP('2010-03-24 10:08:31','YYYY-MM-DD HH24:MI:SS'),100,'Y',140,'Rake Metal',TO_TIMESTAMP('2010-03-24 10:08:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:33 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50131,50003,TO_TIMESTAMP('2010-03-24 10:08:32','YYYY-MM-DD HH24:MI:SS'),100,'Y',127,'Rose Bush',TO_TIMESTAMP('2010-03-24 10:08:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:34 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50132,50003,TO_TIMESTAMP('2010-03-24 10:08:33','YYYY-MM-DD HH24:MI:SS'),100,'Y',135,'Patio Sun Screen',TO_TIMESTAMP('2010-03-24 10:08:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:35 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50133,50003,TO_TIMESTAMP('2010-03-24 10:08:34','YYYY-MM-DD HH24:MI:SS'),100,'Y',50002,'#6-32 x 3/8 Socket Head Cap Screw',TO_TIMESTAMP('2010-03-24 10:08:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:36 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50134,50003,TO_TIMESTAMP('2010-03-24 10:08:35','YYYY-MM-DD HH24:MI:SS'),100,'Y',50004,'Seat',TO_TIMESTAMP('2010-03-24 10:08:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:37 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50135,50003,TO_TIMESTAMP('2010-03-24 10:08:36','YYYY-MM-DD HH24:MI:SS'),100,'Y',143,'Grass Seeder',TO_TIMESTAMP('2010-03-24 10:08:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:38 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50136,50003,TO_TIMESTAMP('2010-03-24 10:08:37','YYYY-MM-DD HH24:MI:SS'),100,'Y',122,'Standard',TO_TIMESTAMP('2010-03-24 10:08:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:39 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50137,50003,TO_TIMESTAMP('2010-03-24 10:08:38','YYYY-MM-DD HH24:MI:SS'),100,'Y',144,'Lawn Tiller',TO_TIMESTAMP('2010-03-24 10:08:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:40 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50138,50003,TO_TIMESTAMP('2010-03-24 10:08:39','YYYY-MM-DD HH24:MI:SS'),100,'Y',142,'Transplanter',TO_TIMESTAMP('2010-03-24 10:08:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:41 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50139,50003,TO_TIMESTAMP('2010-03-24 10:08:40','YYYY-MM-DD HH24:MI:SS'),100,'Y',131,'Travel cost',TO_TIMESTAMP('2010-03-24 10:08:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:42 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50140,50003,TO_TIMESTAMP('2010-03-24 10:08:41','YYYY-MM-DD HH24:MI:SS'),100,'Y',148,'TShirt - Green Large',TO_TIMESTAMP('2010-03-24 10:08:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:43 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50141,50003,TO_TIMESTAMP('2010-03-24 10:08:42','YYYY-MM-DD HH24:MI:SS'),100,'Y',147,'TShirt - Red Large',TO_TIMESTAMP('2010-03-24 10:08:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:44 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50142,50003,TO_TIMESTAMP('2010-03-24 10:08:43','YYYY-MM-DD HH24:MI:SS'),100,'Y',50003,'Ultra Glue',TO_TIMESTAMP('2010-03-24 10:08:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:45 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50143,50003,TO_TIMESTAMP('2010-03-24 10:08:44','YYYY-MM-DD HH24:MI:SS'),100,'Y',141,'Weeder',TO_TIMESTAMP('2010-03-24 10:08:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:08:46 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Updated,UpdatedBy) VALUES (11,11,50144,50003,TO_TIMESTAMP('2010-03-24 10:08:45','YYYY-MM-DD HH24:MI:SS'),100,'Y',50017,'Whater',TO_TIMESTAMP('2010-03-24 10:08:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50133
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50092
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50121
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50123
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50094
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50095
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50098
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50100
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50105
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50106
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50108
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50112
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50114
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50118
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50119
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50134
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50136
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=0,IsActive='N' WHERE C_POSKey_ID=50144
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=10,IsActive='Y' WHERE C_POSKey_ID=50093
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=20,IsActive='Y' WHERE C_POSKey_ID=50096
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=30,IsActive='Y' WHERE C_POSKey_ID=50097
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=40,IsActive='Y' WHERE C_POSKey_ID=50101
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=50,IsActive='Y' WHERE C_POSKey_ID=50103
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=60,IsActive='Y' WHERE C_POSKey_ID=50104
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=70,IsActive='Y' WHERE C_POSKey_ID=50107
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=80,IsActive='Y' WHERE C_POSKey_ID=50109
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=90,IsActive='Y' WHERE C_POSKey_ID=50135
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=100,IsActive='Y' WHERE C_POSKey_ID=50110
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=110,IsActive='Y' WHERE C_POSKey_ID=50111
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=120,IsActive='Y' WHERE C_POSKey_ID=50099
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=130,IsActive='Y' WHERE C_POSKey_ID=50102
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=140,IsActive='Y' WHERE C_POSKey_ID=50137
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=150,IsActive='Y' WHERE C_POSKey_ID=50113
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=160,IsActive='Y' WHERE C_POSKey_ID=50115
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=170,IsActive='Y' WHERE C_POSKey_ID=50116
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=180,IsActive='Y' WHERE C_POSKey_ID=50117
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=190,IsActive='Y' WHERE C_POSKey_ID=50122
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=200,IsActive='Y' WHERE C_POSKey_ID=50120
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=210,IsActive='Y' WHERE C_POSKey_ID=50132
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=220,IsActive='Y' WHERE C_POSKey_ID=50128
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=230,IsActive='Y' WHERE C_POSKey_ID=50124
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=240,IsActive='Y' WHERE C_POSKey_ID=50125
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=250,IsActive='Y' WHERE C_POSKey_ID=50126
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=260,IsActive='Y' WHERE C_POSKey_ID=50127
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=270,IsActive='Y' WHERE C_POSKey_ID=50129
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=280,IsActive='Y' WHERE C_POSKey_ID=50130
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=290,IsActive='Y' WHERE C_POSKey_ID=50131
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=300,IsActive='Y' WHERE C_POSKey_ID=50138
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=310,IsActive='Y' WHERE C_POSKey_ID=50139
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=320,IsActive='Y' WHERE C_POSKey_ID=50140
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=330,IsActive='Y' WHERE C_POSKey_ID=50141
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=340,IsActive='Y' WHERE C_POSKey_ID=50142
;

-- Mar 24, 2010 10:18:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=350,IsActive='Y' WHERE C_POSKey_ID=50143
;

-- Mar 24, 2010 10:18:49 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:18:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50093
;

-- Mar 24, 2010 10:18:51 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:18:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50096
;

-- Mar 24, 2010 10:18:52 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:18:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50097
;

-- Mar 24, 2010 10:18:52 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:18:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50101
;

-- Mar 24, 2010 10:18:53 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:18:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50103
;

-- Mar 24, 2010 10:18:54 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:18:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50104
;

-- Mar 24, 2010 10:18:55 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:18:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50107
;

-- Mar 24, 2010 10:18:56 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:18:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50109
;

-- Mar 24, 2010 10:18:56 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:18:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50135
;

-- Mar 24, 2010 10:18:57 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:18:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50110
;

-- Mar 24, 2010 10:18:58 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:18:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50111
;

-- Mar 24, 2010 10:18:59 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:18:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50099
;

-- Mar 24, 2010 10:19:00 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50102
;

-- Mar 24, 2010 10:19:01 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50137
;

-- Mar 24, 2010 10:19:02 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50113
;

-- Mar 24, 2010 10:19:03 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50115
;

-- Mar 24, 2010 10:19:04 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50116
;

-- Mar 24, 2010 10:19:04 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50117
;

-- Mar 24, 2010 10:19:05 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50122
;

-- Mar 24, 2010 10:19:06 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50120
;

-- Mar 24, 2010 10:19:07 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50132
;

-- Mar 24, 2010 10:19:08 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50128
;

-- Mar 24, 2010 10:19:10 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50124
;

-- Mar 24, 2010 10:19:12 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50125
;

-- Mar 24, 2010 10:19:12 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50126
;

-- Mar 24, 2010 10:19:13 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50127
;

-- Mar 24, 2010 10:19:14 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50129
;

-- Mar 24, 2010 10:19:16 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50130
;

-- Mar 24, 2010 10:19:18 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50131
;

-- Mar 24, 2010 10:19:20 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50138
;

-- Mar 24, 2010 10:19:21 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50139
;

-- Mar 24, 2010 10:19:22 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50140
;

-- Mar 24, 2010 10:19:22 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50141
;

-- Mar 24, 2010 10:19:23 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50142
;

-- Mar 24, 2010 10:19:27 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:19:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50143
;

-- Mar 24, 2010 10:20:45 AM EST
-- Java POS improvements
UPDATE C_POSKey SET Qty=1.000000000000,Updated=TO_TIMESTAMP('2010-03-24 10:20:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50088
;

-- Mar 24, 2010 10:21:14 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,Name,Qty,SeqNo,SpanX,SpanY,SubKeyLayout_ID,Updated,UpdatedBy) VALUES (11,11,50145,100,TO_TIMESTAMP('2010-03-24 10:21:13','YYYY-MM-DD HH24:MI:SS'),100,'Y','All Products',0,23,4,0,50003,TO_TIMESTAMP('2010-03-24 10:21:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:22:17 AM EST
-- Java POS improvements
UPDATE C_POSKeyLayout SET AD_PrintColor_ID=111,Updated=TO_TIMESTAMP('2010-03-24 10:22:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKeyLayout_ID=50003
;

-- Mar 24, 2010 10:22:57 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,Name,Qty,SeqNo,SpanX,SpanY,SubKeyLayout_ID,Updated,UpdatedBy) VALUES (11,11,111,50146,50003,TO_TIMESTAMP('2010-03-24 10:22:56','YYYY-MM-DD HH24:MI:SS'),100,'Y','Popular Items',0,360,0,0,100,TO_TIMESTAMP('2010-03-24 10:22:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:23:03 AM EST
-- Java POS improvements
UPDATE C_POSKeyLayout SET AD_PrintColor_ID=103,Updated=TO_TIMESTAMP('2010-03-24 10:23:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKeyLayout_ID=50003
;

-- Mar 24, 2010 10:23:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET AD_PrintColor_ID=103,Updated=TO_TIMESTAMP('2010-03-24 10:23:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50145
;

-- Mar 24, 2010 10:34:12 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Qty,SeqNo,SpanX,SpanY,Updated,UpdatedBy) VALUES (11,11,50147,100,TO_TIMESTAMP('2010-03-24 10:34:09','YYYY-MM-DD HH24:MI:SS'),100,'Y',124,'Elm Tree',1.000000000000,33,0,0,TO_TIMESTAMP('2010-03-24 10:34:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:34:28 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Qty,SeqNo,SpanX,SpanY,Updated,UpdatedBy) VALUES (11,11,50148,100,TO_TIMESTAMP('2010-03-24 10:34:27','YYYY-MM-DD HH24:MI:SS'),100,'Y',130,'Plum Tree',1.000000000000,43,0,0,TO_TIMESTAMP('2010-03-24 10:34:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:34:45 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Qty,SeqNo,SpanX,SpanY,Updated,UpdatedBy) VALUES (11,11,50149,100,TO_TIMESTAMP('2010-03-24 10:34:44','YYYY-MM-DD HH24:MI:SS'),100,'Y',129,'Holly Bush',1.000000000000,53,0,0,TO_TIMESTAMP('2010-03-24 10:34:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:34:59 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Qty,SeqNo,SpanX,SpanY,Updated,UpdatedBy) VALUES (11,11,50150,100,TO_TIMESTAMP('2010-03-24 10:34:58','YYYY-MM-DD HH24:MI:SS'),100,'Y',127,'Rose Bush',1.000000000000,63,0,0,TO_TIMESTAMP('2010-03-24 10:34:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:35:47 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Qty,SeqNo,SpanX,SpanY,Updated,UpdatedBy) VALUES (11,11,50151,100,TO_TIMESTAMP('2010-03-24 10:35:46','YYYY-MM-DD HH24:MI:SS'),100,'Y',143,'Grass seeder',1.000000000000,73,0,0,TO_TIMESTAMP('2010-03-24 10:35:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:36:07 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Qty,SeqNo,SpanX,SpanY,Updated,UpdatedBy) VALUES (11,11,50152,100,TO_TIMESTAMP('2010-03-24 10:36:06','YYYY-MM-DD HH24:MI:SS'),100,'Y',144,'Lawn Tiller',1.000000000000,83,0,0,TO_TIMESTAMP('2010-03-24 10:36:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:36:22 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Qty,SeqNo,SpanX,SpanY,Updated,UpdatedBy) VALUES (11,11,50153,100,TO_TIMESTAMP('2010-03-24 10:36:21','YYYY-MM-DD HH24:MI:SS'),100,'Y',139,'Rake Bamboo',1.000000000000,93,0,0,TO_TIMESTAMP('2010-03-24 10:36:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:36:34 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Qty,SeqNo,SpanX,SpanY,Updated,UpdatedBy) VALUES (11,11,50154,100,TO_TIMESTAMP('2010-03-24 10:36:33','YYYY-MM-DD HH24:MI:SS'),100,'Y',140,'Rake Metal',1.000000000000,103,0,0,TO_TIMESTAMP('2010-03-24 10:36:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:36:50 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Qty,SeqNo,SpanX,SpanY,Updated,UpdatedBy) VALUES (11,11,50155,100,TO_TIMESTAMP('2010-03-24 10:36:49','YYYY-MM-DD HH24:MI:SS'),100,'Y',141,'Weeder',1.000000000000,113,0,0,TO_TIMESTAMP('2010-03-24 10:36:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:37:14 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Qty,SeqNo,SpanX,SpanY,Updated,UpdatedBy) VALUES (11,11,50156,100,TO_TIMESTAMP('2010-03-24 10:37:13','YYYY-MM-DD HH24:MI:SS'),100,'Y',148,'Tshirt GL',1.000000000000,123,0,0,TO_TIMESTAMP('2010-03-24 10:37:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:37:33 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Qty,SeqNo,SpanX,SpanY,Updated,UpdatedBy) VALUES (11,11,50157,100,TO_TIMESTAMP('2010-03-24 10:37:32','YYYY-MM-DD HH24:MI:SS'),100,'Y',147,'Tshirt RL',1.000000000000,133,0,0,TO_TIMESTAMP('2010-03-24 10:37:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:37:52 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Qty,SeqNo,SpanX,SpanY,Updated,UpdatedBy) VALUES (11,11,50158,100,TO_TIMESTAMP('2010-03-24 10:37:51','YYYY-MM-DD HH24:MI:SS'),100,'Y',145,'Patio Set',1.000000000000,143,0,0,TO_TIMESTAMP('2010-03-24 10:37:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:38:13 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,M_Product_ID,Name,Qty,SeqNo,SpanX,SpanY,Updated,UpdatedBy) VALUES (11,11,50159,100,TO_TIMESTAMP('2010-03-24 10:38:12','YYYY-MM-DD HH24:MI:SS'),100,'Y',146,'How to Plant',1.000000000000,153,0,0,TO_TIMESTAMP('2010-03-24 10:38:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=10,IsActive='Y' WHERE C_POSKey_ID=50145
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=20,IsActive='Y' WHERE C_POSKey_ID=100
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=30,IsActive='Y' WHERE C_POSKey_ID=50149
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=40,IsActive='Y' WHERE C_POSKey_ID=50150
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=50,IsActive='Y' WHERE C_POSKey_ID=102
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=60,IsActive='Y' WHERE C_POSKey_ID=50088
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=70,IsActive='Y' WHERE C_POSKey_ID=101
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=80,IsActive='Y' WHERE C_POSKey_ID=50147
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=90,IsActive='Y' WHERE C_POSKey_ID=50148
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=100,IsActive='Y' WHERE C_POSKey_ID=50157
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=110,IsActive='Y' WHERE C_POSKey_ID=50151
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=120,IsActive='Y' WHERE C_POSKey_ID=50152
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=130,IsActive='Y' WHERE C_POSKey_ID=50153
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=140,IsActive='Y' WHERE C_POSKey_ID=50154
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=150,IsActive='Y' WHERE C_POSKey_ID=50155
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=160,IsActive='Y' WHERE C_POSKey_ID=50156
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=170,IsActive='Y' WHERE C_POSKey_ID=50158
;

-- Mar 24, 2010 10:39:17 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=180,IsActive='Y' WHERE C_POSKey_ID=50159
;

-- Mar 24, 2010 10:39:24 AM EST
-- Java POS improvements
DELETE FROM C_POSKey WHERE C_POSKey_ID=50156
;

-- Mar 24, 2010 10:44:25 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=50,IsActive='Y' WHERE C_POSKey_ID=50088
;

-- Mar 24, 2010 10:44:25 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=60,IsActive='Y' WHERE C_POSKey_ID=101
;

-- Mar 24, 2010 10:44:25 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=70,IsActive='Y' WHERE C_POSKey_ID=50147
;

-- Mar 24, 2010 10:44:25 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=80,IsActive='Y' WHERE C_POSKey_ID=50148
;

-- Mar 24, 2010 10:44:25 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=90,IsActive='Y' WHERE C_POSKey_ID=50157
;

-- Mar 24, 2010 10:44:25 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=100,IsActive='Y' WHERE C_POSKey_ID=50151
;

-- Mar 24, 2010 10:44:25 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=110,IsActive='Y' WHERE C_POSKey_ID=50152
;

-- Mar 24, 2010 10:44:25 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=120,IsActive='Y' WHERE C_POSKey_ID=50153
;

-- Mar 24, 2010 10:44:25 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=130,IsActive='Y' WHERE C_POSKey_ID=50154
;

-- Mar 24, 2010 10:44:25 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=140,IsActive='Y' WHERE C_POSKey_ID=50155
;

-- Mar 24, 2010 10:44:25 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=150,IsActive='Y' WHERE C_POSKey_ID=50158
;

-- Mar 24, 2010 10:44:25 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=160,IsActive='Y' WHERE C_POSKey_ID=50159
;

-- Mar 24, 2010 10:44:25 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=170,IsActive='Y' WHERE C_POSKey_ID=102
;

-- Mar 24, 2010 10:45:09 AM EST
-- Java POS improvements
UPDATE C_POSKey SET AD_PrintColor_ID=102,Updated=TO_TIMESTAMP('2010-03-24 10:45:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50149
;

-- Mar 24, 2010 10:45:13 AM EST
-- Java POS improvements
UPDATE C_POSKey SET AD_PrintColor_ID=102,Updated=TO_TIMESTAMP('2010-03-24 10:45:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50150
;

-- Mar 24, 2010 10:45:29 AM EST
-- Java POS improvements
UPDATE C_POSKey SET AD_PrintColor_ID=113,Updated=TO_TIMESTAMP('2010-03-24 10:45:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=101
;

-- Mar 24, 2010 10:45:33 AM EST
-- Java POS improvements
UPDATE C_POSKey SET AD_PrintColor_ID=113,Updated=TO_TIMESTAMP('2010-03-24 10:45:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50147
;

-- Mar 24, 2010 10:45:39 AM EST
-- Java POS improvements
UPDATE C_POSKey SET AD_PrintColor_ID=113,Updated=TO_TIMESTAMP('2010-03-24 10:45:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKey_ID=50148
;

-- Mar 24, 2010 10:51:53 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,Name,Qty,SeqNo,SpanX,SpanY,Text,Updated,UpdatedBy) VALUES (11,11,102,50160,50002,TO_TIMESTAMP('2010-03-24 10:51:50','YYYY-MM-DD HH24:MI:SS'),100,'Y','10',0,130,0,0,'10',TO_TIMESTAMP('2010-03-24 10:51:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:52:06 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,Name,Qty,SeqNo,SpanX,SpanY,Text,Updated,UpdatedBy) VALUES (11,11,102,50161,50002,TO_TIMESTAMP('2010-03-24 10:52:05','YYYY-MM-DD HH24:MI:SS'),100,'Y','20',0,140,0,0,'20',TO_TIMESTAMP('2010-03-24 10:52:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:52:22 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,Name,Qty,SeqNo,SpanX,SpanY,Text,Updated,UpdatedBy) VALUES (11,11,102,50162,50002,TO_TIMESTAMP('2010-03-24 10:52:20','YYYY-MM-DD HH24:MI:SS'),100,'Y','50',0,150,0,0,'50',TO_TIMESTAMP('2010-03-24 10:52:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:52:34 AM EST
-- Java POS improvements
INSERT INTO C_POSKey (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,C_POSKey_ID,C_POSKeyLayout_ID,Created,CreatedBy,IsActive,Name,Qty,SeqNo,SpanX,SpanY,Text,Updated,UpdatedBy) VALUES (11,11,102,50163,50002,TO_TIMESTAMP('2010-03-24 10:52:33','YYYY-MM-DD HH24:MI:SS'),100,'Y','100',0,160,0,0,'100',TO_TIMESTAMP('2010-03-24 10:52:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 24, 2010 10:52:53 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=40,IsActive='Y' WHERE C_POSKey_ID=50160
;

-- Mar 24, 2010 10:52:53 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=50,IsActive='Y' WHERE C_POSKey_ID=50069
;

-- Mar 24, 2010 10:52:53 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=60,IsActive='Y' WHERE C_POSKey_ID=50070
;

-- Mar 24, 2010 10:52:53 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=70,IsActive='Y' WHERE C_POSKey_ID=50071
;

-- Mar 24, 2010 10:52:53 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=80,IsActive='Y' WHERE C_POSKey_ID=50161
;

-- Mar 24, 2010 10:52:53 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=90,IsActive='Y' WHERE C_POSKey_ID=50072
;

-- Mar 24, 2010 10:52:53 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=100,IsActive='Y' WHERE C_POSKey_ID=50073
;

-- Mar 24, 2010 10:52:53 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=110,IsActive='Y' WHERE C_POSKey_ID=50074
;

-- Mar 24, 2010 10:52:53 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=120,IsActive='Y' WHERE C_POSKey_ID=50162
;

-- Mar 24, 2010 10:52:53 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=130,IsActive='Y' WHERE C_POSKey_ID=50075
;

-- Mar 24, 2010 10:52:53 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=140,IsActive='Y' WHERE C_POSKey_ID=50076
;

-- Mar 24, 2010 10:52:53 AM EST
-- Java POS improvements
UPDATE C_POSKey SET SeqNo=150,IsActive='Y' WHERE C_POSKey_ID=50077
;

-- Mar 24, 2010 10:53:01 AM EST
-- Java POS improvements
UPDATE C_POSKeyLayout SET Columns=4,Updated=TO_TIMESTAMP('2010-03-24 10:53:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_POSKeyLayout_ID=50002
;

-- Mar 24, 2010 4:08:39 PM EST
-- Java POS improvements
UPDATE AD_Column SET AD_Reference_ID=32,Updated=TO_TIMESTAMP('2010-03-24 16:08:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59097
;

