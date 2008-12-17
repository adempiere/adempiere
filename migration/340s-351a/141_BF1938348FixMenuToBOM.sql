SET define OFF

-- Apr 9, 2008 1:22:02 AM CDT
-- e-Evolution Libero Manufacturing Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Form_ID,AD_Menu_ID,AD_Org_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,NAME,Updated,UpdatedBy) VALUES (0,116,53128,0,'X',TO_DATE('2008-04-09 01:22:01','YYYY-MM-DD HH24:MI:SS'),0,'Edit Workflows','EE01','Y','N','N','N','Manufacturing Workflow Editor',TO_DATE('2008-04-09 01:22:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 9, 2008 1:22:02 AM CDT
-- e-Evolution Libero Manufacturing Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53128 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Apr 9, 2008 1:22:02 AM CDT
-- e-Evolution Libero Manufacturing Management
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SYSDATE, 0, SYSDATE, 0,t.AD_Tree_ID, 53128, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53128)
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53019, SeqNo=0, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53021
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53019, SeqNo=1, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53020
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53019, SeqNo=2, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=469
;

-- Apr 9, 2008 1:22:09 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53019, SeqNo=3, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53128
;

-- Apr 9, 2008 1:22:39 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53019, SeqNo=0, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53021
;

-- Apr 9, 2008 1:22:39 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53019, SeqNo=1, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53020
;

-- Apr 9, 2008 1:22:39 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53019, SeqNo=2, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53128
;

-- Apr 9, 2008 1:22:39 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=501, SeqNo=0, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=427
;

-- Apr 9, 2008 1:22:39 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=501, SeqNo=1, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=152
;

-- Apr 9, 2008 1:22:39 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=501, SeqNo=2, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=469
;

-- Apr 9, 2008 1:22:39 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=501, SeqNo=3, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=500
;

-- Apr 9, 2008 1:22:39 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=501, SeqNo=4, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=429
;

-- Apr 9, 2008 1:22:39 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=501, SeqNo=5, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=470
;

-- Apr 9, 2008 1:22:39 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=501, SeqNo=6, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=428
;

-- Apr 9, 2008 1:23:57 AM CDT
-- e-Evolution Libero Manufacturing Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,NAME,Updated,UpdatedBy) VALUES (0,53129,0,53006,'W',TO_DATE('2008-04-09 01:23:56','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Product Bill of Materials & Formula ','EE01','Y','N','N','N','Quality Bill of Materials & Formula ',TO_DATE('2008-04-09 01:23:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 9, 2008 1:23:57 AM CDT
-- e-Evolution Libero Manufacturing Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53129 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Apr 9, 2008 1:23:57 AM CDT
-- e-Evolution Libero Manufacturing Management
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SYSDATE, 0, SYSDATE, 0,t.AD_Tree_ID, 53129, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53129)
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53071, SeqNo=0, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53072
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53071, SeqNo=1, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53129
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53071, SeqNo=2, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53024
;

-- Apr 9, 2008 1:24:02 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53071, SeqNo=3, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53073
;

-- Apr 9, 2008 1:24:21 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53071, SeqNo=0, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53072
;

-- Apr 9, 2008 1:24:21 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53071, SeqNo=1, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53129
;

-- Apr 9, 2008 1:24:21 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53071, SeqNo=2, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53073
;

-- Apr 9, 2008 1:24:21 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53022, SeqNo=0, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53023
;

-- Apr 9, 2008 1:24:21 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53022, SeqNo=1, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53024
;

-- Apr 9, 2008 1:24:21 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53022, SeqNo=2, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53025
;

-- Apr 9, 2008 1:24:21 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53022, SeqNo=3, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53026
;

-- Apr 9, 2008 1:24:21 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53022, SeqNo=4, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53027
;

-- Apr 9, 2008 1:24:21 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53022, SeqNo=5, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=53028
;

-- Apr 9, 2008 1:24:21 AM CDT
-- e-Evolution Libero Manufacturing Management
UPDATE AD_TreeNodeMM SET Parent_ID=53022, SeqNo=6, Updated=SYSDATE WHERE AD_Tree_ID=10 AND Node_ID=426
;

