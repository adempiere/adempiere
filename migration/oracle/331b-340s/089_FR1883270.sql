
-- Feb 1, 2008 4:02:32 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,MandatoryLogic,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54272,1580,0,10,115,'DateColumn',TO_DATE('2008-02-01 16:02:30','YYYY-MM-DD HH24:MI:SS'),100,'Fully qualified date column','D',60,'The Date Column indicates the date to be used when calculating this measurement','Y','N','N','N','N','N','N','N','N','N','Y','@StartNewYear@=Y','Date Column',0,TO_DATE('2008-02-01 16:02:30','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 1, 2008 4:02:32 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54272 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 1, 2008 4:02:37 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
ALTER TABLE AD_Sequence ADD DateColumn NVARCHAR2(60)
;

-- Feb 1, 2008 4:05:04 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54272,54357,0,146,TO_DATE('2008-02-01 16:05:01','YYYY-MM-DD HH24:MI:SS'),100,'Fully qualified date column',0,'@IsAutoSequence@=Y & @IsTableID@=N & @StartNewYear@=Y','D','The Date Column indicates the date to be used when calculating this measurement','Y','Y','Y','N','N','N','N','N','Date Column',180,0,TO_DATE('2008-02-01 16:05:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 1, 2008 4:05:04 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54357 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 1, 2008 4:05:22 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=3055
;

-- Feb 1, 2008 4:05:22 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=322
;

-- Feb 1, 2008 4:05:22 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=323
;

-- Feb 1, 2008 4:05:22 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=2019
;

-- Feb 1, 2008 4:05:23 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=324
;

-- Feb 1, 2008 4:05:23 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=325
;

-- Feb 1, 2008 4:05:23 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=326
;

-- Feb 1, 2008 4:05:23 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=330
;

-- Feb 1, 2008 4:05:23 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=331
;

-- Feb 1, 2008 4:05:23 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=635
;

-- Feb 1, 2008 4:05:23 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=1554
;

-- Feb 1, 2008 4:05:23 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=329
;

-- Feb 1, 2008 4:05:23 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=335
;

-- Feb 1, 2008 4:05:23 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=1555
;

-- Feb 1, 2008 4:05:23 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=54357
;

-- Feb 1, 2008 4:05:23 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=332
;

-- Feb 1, 2008 4:05:23 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=333
;

-- Feb 1, 2008 4:05:23 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=334
;

-- Feb 1, 2008 4:06:20 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET DisplayLength=60,Updated=TO_DATE('2008-02-01 16:06:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=332
;

-- Feb 1, 2008 4:06:36 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET DisplayLength=60, IsSameLine='N',Updated=TO_DATE('2008-02-01 16:06:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=333
;

-- Feb 1, 2008 4:06:57 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Column SET FieldLength=255,Updated=TO_DATE('2008-02-01 16:06:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=223
;

-- Feb 1, 2008 4:06:57 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET Name='Prefix', Description='Prefix before the sequence number', Help='The Prefix indicates the characters to print in front of the document number.' WHERE AD_Column_ID=223 AND IsCentrallyMaintained='Y'
;

-- Feb 1, 2008 4:07:04 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
ALTER TABLE AD_Sequence MODIFY Prefix NVARCHAR2(255) DEFAULT  NULL 
;

-- Feb 1, 2008 4:07:16 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Column SET FieldLength=255,Updated=TO_DATE('2008-02-01 16:07:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=224
;

-- Feb 1, 2008 4:07:16 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET Name='Suffix', Description='Suffix after the number', Help='The Suffix indicates the characters to append to the document number.' WHERE AD_Column_ID=224 AND IsCentrallyMaintained='Y'
;

-- Feb 1, 2008 4:07:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
ALTER TABLE AD_Sequence MODIFY Suffix NVARCHAR2(255) DEFAULT  NULL 
;

-- Feb 2, 2008 12:41:35 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES (0,0,53068,'3','org.adempiere.process.UpdateSequenceNo',TO_DATE('2008-02-02 12:41:31','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','N','Update Sequence No','Y',0,0,TO_DATE('2008-02-02 12:41:31','YYYY-MM-DD HH24:MI:SS'),100,'Sequence_No_Update')
;

-- Feb 2, 2008 12:41:35 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53068 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Feb 2, 2008 12:41:35 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53068,0,TO_DATE('2008-02-02 12:41:35','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-02 12:41:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 2, 2008 12:41:35 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53068,102,TO_DATE('2008-02-02 12:41:35','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-02 12:41:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 2, 2008 12:41:35 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53068,103,TO_DATE('2008-02-02 12:41:35','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-02 12:41:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 2, 2008 12:41:35 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53068,50001,TO_DATE('2008-02-02 12:41:35','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-02-02 12:41:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 2, 2008 12:42:54 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,636,0,53068,53129,10,'CalendarYear',TO_DATE('2008-02-02 12:42:52','YYYY-MM-DD HH24:MI:SS'),100,'D',4,'Y','Y','Y','N','Year',10,TO_DATE('2008-02-02 12:42:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 2, 2008 12:42:54 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53129 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 2, 2008 12:43:57 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Action,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53087,0,53068,'P',TO_DATE('2008-02-02 12:43:56','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','Update Sequence No',TO_DATE('2008-02-02 12:43:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 2, 2008 12:43:57 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53087 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Feb 2, 2008 12:43:57 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 53087, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53087)
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=1000000
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=265
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=104
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=105
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=384
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=111
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=106
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=117
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=418
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=102
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=103
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=270
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=121
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=409
;

-- Feb 2, 2008 12:44:17 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=151
;

-- Feb 2, 2008 12:44:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53087
;

-- Feb 2, 2008 12:44:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=464
;

-- Feb 2, 2008 12:44:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=124
;

-- Feb 2, 2008 12:44:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=123
;

-- Feb 2, 2008 12:44:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=547
;

-- Feb 2, 2008 12:44:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=19, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=174
;

-- Feb 2, 2008 12:44:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=20, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=254
;

-- Feb 2, 2008 12:44:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=21, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=120
;

-- Feb 2, 2008 12:44:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=22, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=135
;

-- Feb 2, 2008 12:44:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=23, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=550
;

-- Feb 2, 2008 12:44:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=24, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=551
;

-- Feb 2, 2008 12:44:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=25, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=306
;

-- Feb 2, 2008 12:44:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=26, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=417
;

-- Feb 2, 2008 12:44:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=27, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=307
;

-- Feb 2, 2008 12:44:18 PM SGT
-- [ 1883270 ] Enhance Document No Formatting
UPDATE AD_TreeNodeMM SET Parent_ID=164, SeqNo=28, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=393
;

CREATE or REPLACE PROCEDURE NextIDByYear
(
    p_AD_Sequence_ID    	IN  NUMBER,
    p_IncrementNo           IN  NUMBER,
    p_CalendarYear          IN  CHAR,
    o_NextID                OUT NUMBER
)
AS
BEGIN
   SELECT CurrentNext
		INTO o_NextID
	FROM AD_Sequence_No
	WHERE AD_Sequence_ID=p_AD_Sequence_ID 
	AND CalendarYear = p_CalendarYear 
	FOR UPDATE OF CurrentNext;
	--
	UPDATE AD_Sequence_No
	  SET CurrentNext = CurrentNext + p_IncrementNo
	WHERE AD_Sequence_ID=p_AD_Sequence_ID
	AND CalendarYear = p_CalendarYear;
EXCEPTION
    WHEN  OTHERS THEN
    	DBMS_OUTPUT.PUT_LINE(SQLERRM);
END NextIDByYear;
/


