SET SQLBLANKLINES ON
-- Sep 8, 2008 10:12:30 PM CDT
-- DB Native Sequence
INSERT INTO AD_SysConfig (AD_Client_ID,AD_Org_ID,AD_SysConfig_ID,ConfigurationLevel,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50016,'S',TO_DATE('2008-09-08 22:12:21','YYYY-MM-DD HH24:MI:SS'),0,'Y - Yes Use Native Sequence , N - No Use Adempiere Sequence (AD_Sequence)','D','Y','SYSTEM_NATIVE_SEQUENCE',TO_DATE('2008-09-08 22:12:21','YYYY-MM-DD HH24:MI:SS'),0,'N')
;

-- Sep 8, 2008 10:22:39 PM CDT
-- DB Native Sequence
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES (0,0,53156,'4','org.eevolution.process.EnableNativeSequence',TO_DATE('2008-09-08 22:22:38','YYYY-MM-DD HH24:MI:SS'),0,'Enable Native Sequence','D','This process allows Enable the native sequences based in the current AD_Sequence.

This process should be implemented when the users are not connected.','Y','N','N','N','N','Enable Native Sequence','Y',5,181,TO_DATE('2008-09-08 22:22:38','YYYY-MM-DD HH24:MI:SS'),0,'AD_Native_Sequence_Enable')
;

-- Sep 8, 2008 10:22:39 PM CDT
-- DB Native Sequence
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53156 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Sep 8, 2008 10:22:39 PM CDT
-- DB Native Sequence
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53156,0,TO_DATE('2008-09-08 22:22:39','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-09-08 22:22:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 8, 2008 10:22:39 PM CDT
-- DB Native Sequence
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53156,102,TO_DATE('2008-09-08 22:22:39','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-09-08 22:22:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 8, 2008 10:22:39 PM CDT
-- DB Native Sequence
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53156,103,TO_DATE('2008-09-08 22:22:39','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-09-08 22:22:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 8, 2008 10:22:39 PM CDT
-- DB Native Sequence
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53156,50002,TO_DATE('2008-09-08 22:22:39','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-09-08 22:22:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 8, 2008 10:22:39 PM CDT
-- DB Native Sequence
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53156,50001,TO_DATE('2008-09-08 22:22:39','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-09-08 22:22:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 8, 2008 10:24:04 PM CDT
-- DB Native Sequence
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53193,0,53156,'P',TO_DATE('2008-09-08 22:24:03','YYYY-MM-DD HH24:MI:SS'),0,'Enable Native Sequence','D','Y','N','N','N','Enable Native Sequence',TO_DATE('2008-09-08 22:24:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 8, 2008 10:24:04 PM CDT
-- DB Native Sequence
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53193 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 8, 2008 10:24:04 PM CDT
-- DB Native Sequence
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 53193, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53193)
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=161
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=367
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=456
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=501
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=326
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=566
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=392
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=113
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=220
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=351
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=289
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=302
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=303
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=321
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=461
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53193
;

-- Sep 8, 2008 10:24:14 PM CDT
-- DB Native Sequence
UPDATE AD_TreeNodeMM SET Parent_ID=155, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=383
;

