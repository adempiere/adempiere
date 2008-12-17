-- Mar 24, 2008 9:08:43 PM CST
-- Replication Functionality
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=55187
;

-- Mar 24, 2008 9:08:43 PM CST
-- Replication Functionality
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=54574
;

-- Mar 24, 2008 9:08:43 PM CST
-- Replication Functionality
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=54575
;

-- Mar 24, 2008 9:08:43 PM CST
-- Replication Functionality
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=54576
;

-- Mar 24, 2008 9:08:43 PM CST
-- Replication Functionality
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=54577
;

-- Mar 24, 2008 9:08:43 PM CST
-- Replication Functionality
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=54578
;

-- Mar 24, 2008 9:08:43 PM CST
-- Replication Functionality
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=54579
;

-- Mar 24, 2008 9:08:43 PM CST
-- Replication Functionality
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=54580
;

-- Mar 24, 2008 9:08:43 PM CST
-- Replication Functionality
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=54581
;

-- Mar 24, 2008 9:10:26 PM CST
-- Replication Functionality
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,"action",Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53125,0,53085,'P',TO_TIMESTAMP('2008-03-24 21:09:42','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','N','N','Create Export Format from Window',TO_TIMESTAMP('2008-03-24 21:09:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 24, 2008 9:10:26 PM CST
-- Replication Functionality
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53125 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Mar 24, 2008 9:10:26 PM CST
-- Replication Functionality
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 53125, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53125)
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53105
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=13, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53099
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=385
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=386
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53100
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53125
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53102
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53101
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53104
;

-- Mar 24, 2008 9:10:51 PM CST
-- Replication Functionality
UPDATE AD_TreeNodeMM SET Parent_ID=53098, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53103
;

-- Mar 24, 2008 9:11:15 PM CST
-- Replication Functionality
UPDATE AD_Menu SET Name='Create Export Format from a Window',Updated=TO_TIMESTAMP('2008-03-24 21:11:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53125
;

-- Mar 24, 2008 9:11:15 PM CST
-- Replication Functionality
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53125
;

-- Mar 24, 2008 9:16:33 PM CST
-- Replication Functionality
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2792,0,53085,53142,20,'IsInsertRecord',TO_TIMESTAMP('2008-03-24 21:16:32','YYYY-MM-DD HH24:MI:SS'),0,'Generated Export Format for the Tabs with Insert Record is Yes','EE05',1,'Y','N','N','N','IsInsertRecord',20,TO_TIMESTAMP('2008-03-24 21:16:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 24, 2008 9:16:33 PM CST
-- Replication Functionality
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53142 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Mar 24, 2008 9:23:04 PM CST
-- Replication Functionality
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,392,0,53085,53143,20,'IsMandatory',TO_TIMESTAMP('2008-03-24 21:23:03','YYYY-MM-DD HH24:MI:SS'),0,'Generated an Export Format Line  if the column is mandatory ','EE05',1,'Y','N','N','N','IsMandatory',30,TO_TIMESTAMP('2008-03-24 21:23:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 24, 2008 9:23:04 PM CST
-- Replication Functionality
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53143 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Mar 24, 2008 9:25:13 PM CST
-- Replication Functionality
UPDATE AD_Process_Para SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-03-24 21:25:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53141
;

-- Mar 24, 2008 9:30:09 PM CST
-- Replication Functionality
UPDATE AD_Process SET Description='Create multiple Export Format based in a Window',Updated=TO_TIMESTAMP('2008-03-24 21:30:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53085
;

-- Mar 24, 2008 9:30:09 PM CST
-- Replication Functionality
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53085
;

-- Mar 24, 2008 9:30:09 PM CST
-- Replication Functionality
UPDATE AD_Menu SET Description='Create multiple Export Format based in a Window', IsActive='Y', Name='EXP_Format Generator',Updated=TO_TIMESTAMP('2008-03-24 21:30:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53125
;

-- Mar 24, 2008 9:30:09 PM CST
-- Replication Functionality
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53125
;

-- Mar 24, 2008 9:31:18 PM CST
-- Replication Functionality
UPDATE AD_Process_Para SET Name='Insert Record',Updated=TO_TIMESTAMP('2008-03-24 21:31:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53142
;

-- Mar 24, 2008 9:31:18 PM CST
-- Replication Functionality
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53142
;

-- Mar 24, 2008 9:34:56 PM CST
-- Replication Functionality
UPDATE AD_Process_Para SET Name='Includes only the Tabs that Insert records',Updated=TO_TIMESTAMP('2008-03-24 21:34:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53142
;

-- Mar 24, 2008 9:34:56 PM CST
-- Replication Functionality
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53142
;

-- Mar 24, 2008 9:35:52 PM CST
-- Replication Functionality
UPDATE AD_Process_Para SET Name='Includes only the mandatory columns',Updated=TO_TIMESTAMP('2008-03-24 21:35:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53143
;

-- Mar 24, 2008 9:35:52 PM CST
-- Replication Functionality
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53143
;

