

-- Sep 22, 2008 1:13:24 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Org (AD_Client_ID,AD_Org_ID,Created,CreatedBy,Description,IsActive,IsSummary,Name,Updated,UpdatedBy,Value) VALUES (11,50000,TO_TIMESTAMP('2008-09-22 13:13:23','YYYY-MM-DD HH24:MI:SS'),100,'Furniture','Y','N','Furniture',TO_TIMESTAMP('2008-09-22 13:13:23','YYYY-MM-DD HH24:MI:SS'),100,'Furniture')
;

-- Sep 22, 2008 1:13:25 PM CDT
-- Manufacturing Demo
INSERT INTO AD_OrgInfo (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DUNS,IsActive,TaxID,Updated,UpdatedBy) VALUES (11,50000,TO_TIMESTAMP('2008-09-22 13:13:25','YYYY-MM-DD HH24:MI:SS'),100,'?','Y','?',TO_TIMESTAMP('2008-09-22 13:13:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:13:25 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,50000,102,TO_TIMESTAMP('2008-09-22 13:13:25','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_TIMESTAMP('2008-09-22 13:13:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:13:25 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,50000,103,TO_TIMESTAMP('2008-09-22 13:13:25','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_TIMESTAMP('2008-09-22 13:13:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:13:25 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50000, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='OO' AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50000)
;

-- Sep 22, 2008 1:13:50 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Org (AD_Client_ID,AD_Org_ID,Created,CreatedBy,Description,IsActive,IsSummary,Name,Updated,UpdatedBy,Value) VALUES (11,50001,TO_TIMESTAMP('2008-09-22 13:13:49','YYYY-MM-DD HH24:MI:SS'),100,'Fertilizer','Y','N','Fertilizer',TO_TIMESTAMP('2008-09-22 13:13:49','YYYY-MM-DD HH24:MI:SS'),100,'Fertilizer')
;

-- Sep 22, 2008 1:13:50 PM CDT
-- Manufacturing Demo
INSERT INTO AD_OrgInfo (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DUNS,IsActive,TaxID,Updated,UpdatedBy) VALUES (11,50001,TO_TIMESTAMP('2008-09-22 13:13:50','YYYY-MM-DD HH24:MI:SS'),100,'?','Y','?',TO_TIMESTAMP('2008-09-22 13:13:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:13:50 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,50001,102,TO_TIMESTAMP('2008-09-22 13:13:50','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_TIMESTAMP('2008-09-22 13:13:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:13:50 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,50001,103,TO_TIMESTAMP('2008-09-22 13:13:50','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_TIMESTAMP('2008-09-22 13:13:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:13:50 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50001, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='OO' AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50001)
;
-- Sep 22, 2008 1:14:37 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Org (AD_Client_ID,AD_Org_ID,Created,CreatedBy,Description,IsActive,IsSummary,Name,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-22 13:14:31','YYYY-MM-DD HH24:MI:SS'),100,'Store North','Y','N','Store North',TO_TIMESTAMP('2008-09-22 13:14:31','YYYY-MM-DD HH24:MI:SS'),100,'Store North')
;

-- Sep 22, 2008 1:14:37 PM CDT
-- Manufacturing Demo
INSERT INTO AD_OrgInfo (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DUNS,IsActive,TaxID,Updated,UpdatedBy) VALUES (11,50002,TO_TIMESTAMP('2008-09-22 13:14:37','YYYY-MM-DD HH24:MI:SS'),100,'?','Y','?',TO_TIMESTAMP('2008-09-22 13:14:37','YYYY-MM-DD HH24:MI:SS'),100)
;
-- Sep 22, 2008 1:14:37 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,50002,102,TO_TIMESTAMP('2008-09-22 13:14:37','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_TIMESTAMP('2008-09-22 13:14:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:14:37 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,50002,103,TO_TIMESTAMP('2008-09-22 13:14:37','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_TIMESTAMP('2008-09-22 13:14:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:14:37 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50002, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='OO' AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50002)
;

-- Sep 22, 2008 1:15:19 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Org (AD_Client_ID,AD_Org_ID,Created,CreatedBy,Description,IsActive,IsSummary,Name,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-22 13:15:12','YYYY-MM-DD HH24:MI:SS'),100,'Store South','Y','N','Store South',TO_TIMESTAMP('2008-09-22 13:15:12','YYYY-MM-DD HH24:MI:SS'),100,'Store South')
;


-- Sep 22, 2008 1:15:19 PM CDT
-- Manufacturing Demo
INSERT INTO AD_OrgInfo (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DUNS,IsActive,TaxID,Updated,UpdatedBy) VALUES (11,50004,TO_TIMESTAMP('2008-09-22 13:15:19','YYYY-MM-DD HH24:MI:SS'),100,'?','Y','?',TO_TIMESTAMP('2008-09-22 13:15:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:15:19 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,50004,102,TO_TIMESTAMP('2008-09-22 13:15:19','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_TIMESTAMP('2008-09-22 13:15:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:15:19 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,50004,103,TO_TIMESTAMP('2008-09-22 13:15:19','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_TIMESTAMP('2008-09-22 13:15:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:15:19 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50004, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='OO' AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50004)
;

-- Sep 22, 2008 1:15:44 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Org (AD_Client_ID,AD_Org_ID,Created,CreatedBy,Description,IsActive,IsSummary,Name,Updated,UpdatedBy,Value) VALUES (11,50005,TO_TIMESTAMP('2008-09-22 13:15:40','YYYY-MM-DD HH24:MI:SS'),100,'Store East','Y','N','Store East',TO_TIMESTAMP('2008-09-22 13:15:40','YYYY-MM-DD HH24:MI:SS'),100,'Store East')
;

-- Sep 22, 2008 1:15:44 PM CDT
-- Manufacturing Demo
INSERT INTO AD_OrgInfo (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DUNS,IsActive,TaxID,Updated,UpdatedBy) VALUES (11,50005,TO_TIMESTAMP('2008-09-22 13:15:44','YYYY-MM-DD HH24:MI:SS'),100,'?','Y','?',TO_TIMESTAMP('2008-09-22 13:15:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:15:44 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,50005,102,TO_TIMESTAMP('2008-09-22 13:15:44','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_TIMESTAMP('2008-09-22 13:15:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:15:44 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,50005,103,TO_TIMESTAMP('2008-09-22 13:15:44','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_TIMESTAMP('2008-09-22 13:15:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:15:44 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50005, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='OO' AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50005)
;

-- Sep 22, 2008 1:16:09 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Org (AD_Client_ID,AD_Org_ID,Created,CreatedBy,Description,IsActive,IsSummary,Name,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-22 13:16:08','YYYY-MM-DD HH24:MI:SS'),100,'Store West','Y','N','Store West',TO_TIMESTAMP('2008-09-22 13:16:08','YYYY-MM-DD HH24:MI:SS'),100,'Store West')
;

-- Sep 22, 2008 1:16:09 PM CDT
-- Manufacturing Demo
INSERT INTO AD_OrgInfo (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DUNS,IsActive,TaxID,Updated,UpdatedBy) VALUES (11,50006,TO_TIMESTAMP('2008-09-22 13:16:09','YYYY-MM-DD HH24:MI:SS'),100,'?','Y','?',TO_TIMESTAMP('2008-09-22 13:16:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:16:09 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,50006,102,TO_TIMESTAMP('2008-09-22 13:16:09','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_TIMESTAMP('2008-09-22 13:16:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:16:09 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,50006,103,TO_TIMESTAMP('2008-09-22 13:16:09','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_TIMESTAMP('2008-09-22 13:16:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:16:09 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50006, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='OO' AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50006)
;

-- Sep 22, 2008 1:16:22 PM CDT
-- Manufacturing Demo
UPDATE AD_Org SET Name='Store Central', Value='Store Central',Updated=TO_TIMESTAMP('2008-09-22 13:16:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Org_ID=12
;

-- Sep 22, 2008 1:16:23 PM CDT
-- Manufacturing Demo
UPDATE C_ValidCombination SET Combination='Store Central-11100-_-_-_-_', Description='Store Central-Checking Account-_-_-_-_',Updated=TO_TIMESTAMP('2008-09-22 13:16:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ValidCombination_ID=277
;

-- Sep 22, 2008 1:16:23 PM CDT
-- Manufacturing Demo
UPDATE C_ValidCombination SET Combination='Store Central-11900-_-_-_-_', Description='Store Central-Petty Cash-_-_-_-_',Updated=TO_TIMESTAMP('2008-09-22 13:16:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ValidCombination_ID=292
;

-- Sep 22, 2008 1:16:23 PM CDT
-- Manufacturing Demo
UPDATE C_ValidCombination SET Combination='Store Central-11910-_-_-_-_', Description='Store Central-Petty Cash In-Transfer-_-_-_-_',Updated=TO_TIMESTAMP('2008-09-22 13:16:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ValidCombination_ID=293
;

-- Sep 22, 2008 1:16:23 PM CDT
-- Manufacturing Demo
UPDATE C_ValidCombination SET Combination='Store Central-79400-_-_-_-_', Description='Store Central-Cash book expense-_-_-_-_',Updated=TO_TIMESTAMP('2008-09-22 13:16:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ValidCombination_ID=294
;

-- Sep 22, 2008 1:16:23 PM CDT
-- Manufacturing Demo
UPDATE C_ValidCombination SET Combination='Store Central-78300-_-_-_-_', Description='Store Central-Petty Cash Over/Short-_-_-_-_',Updated=TO_TIMESTAMP('2008-09-22 13:16:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ValidCombination_ID=295
;

-- Sep 22, 2008 1:16:23 PM CDT
-- Manufacturing Demo
UPDATE C_ValidCombination SET Combination='Store Central-49500-_-_-_-_', Description='Store Central-Cash book receipts-_-_-_-_',Updated=TO_TIMESTAMP('2008-09-22 13:16:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ValidCombination_ID=296
;

-- Sep 22, 2008 1:16:39 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Org (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,IsSummary,Name,Updated,UpdatedBy,Value) VALUES (11,50007,TO_TIMESTAMP('2008-09-22 13:16:35','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y','Stores',TO_TIMESTAMP('2008-09-22 13:16:35','YYYY-MM-DD HH24:MI:SS'),100,'Stores')
;

-- Sep 22, 2008 1:16:40 PM CDT
-- Manufacturing Demo
INSERT INTO AD_OrgInfo (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DUNS,IsActive,TaxID,Updated,UpdatedBy) VALUES (11,50007,TO_TIMESTAMP('2008-09-22 13:16:40','YYYY-MM-DD HH24:MI:SS'),100,'?','Y','?',TO_TIMESTAMP('2008-09-22 13:16:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:16:40 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,50007,102,TO_TIMESTAMP('2008-09-22 13:16:40','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_TIMESTAMP('2008-09-22 13:16:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:16:40 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Role_OrgAccess (AD_Client_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,IsReadOnly,Updated,UpdatedBy) VALUES (11,50007,103,TO_TIMESTAMP('2008-09-22 13:16:40','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',TO_TIMESTAMP('2008-09-22 13:16:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:16:40 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50007, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='OO' AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50007)
;

-- Sep 22, 2008 1:16:44 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=11
;

-- Sep 22, 2008 1:16:44 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50000
;

-- Sep 22, 2008 1:16:44 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50001
;

-- Sep 22, 2008 1:16:44 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50002
;

-- Sep 22, 2008 1:16:44 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50004
;

-- Sep 22, 2008 1:16:44 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50005
;

-- Sep 22, 2008 1:16:44 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50006
;

-- Sep 22, 2008 1:16:44 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50007
;

-- Sep 22, 2008 1:16:44 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=50007, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=12
;

-- Sep 22, 2008 1:16:46 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=11
;

-- Sep 22, 2008 1:16:46 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50000
;

-- Sep 22, 2008 1:16:46 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50001
;

-- Sep 22, 2008 1:16:46 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50004
;

-- Sep 22, 2008 1:16:46 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50005
;

-- Sep 22, 2008 1:16:46 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50006
;

-- Sep 22, 2008 1:16:46 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50007
;

-- Sep 22, 2008 1:16:46 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=50007, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50002
;

-- Sep 22, 2008 1:16:46 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=50007, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=12
;

-- Sep 22, 2008 1:16:47 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=11
;

-- Sep 22, 2008 1:16:47 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50000
;

-- Sep 22, 2008 1:16:47 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50001
;

-- Sep 22, 2008 1:16:47 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50005
;

-- Sep 22, 2008 1:16:47 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50006
;

-- Sep 22, 2008 1:16:47 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50007
;

-- Sep 22, 2008 1:16:47 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=50007, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50004
;

-- Sep 22, 2008 1:16:47 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=50007, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50002
;

-- Sep 22, 2008 1:16:47 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=50007, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=12
;

-- Sep 22, 2008 1:16:49 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=11
;

-- Sep 22, 2008 1:16:49 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50000
;

-- Sep 22, 2008 1:16:49 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50001
;

-- Sep 22, 2008 1:16:49 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50006
;

-- Sep 22, 2008 1:16:49 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50007
;

-- Sep 22, 2008 1:16:49 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=50007, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50005
;

-- Sep 22, 2008 1:16:49 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=50007, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50004
;

-- Sep 22, 2008 1:16:49 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=50007, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50002
;

-- Sep 22, 2008 1:16:49 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=50007, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=12
;

-- Sep 22, 2008 1:16:51 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=11
;

-- Sep 22, 2008 1:16:51 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50000
;

-- Sep 22, 2008 1:16:51 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50001
;

-- Sep 22, 2008 1:16:51 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50007
;

-- Sep 22, 2008 1:16:51 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=50007, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50006
;

-- Sep 22, 2008 1:16:51 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=50007, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50005
;

-- Sep 22, 2008 1:16:51 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=50007, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50004
;

-- Sep 22, 2008 1:16:51 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=50007, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=50002
;

-- Sep 22, 2008 1:16:51 PM CDT
-- Manufacturing Demo
UPDATE AD_TreeNode SET Parent_ID=50007, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=104 AND Node_ID=12
;

-- Sep 22, 2008 1:18:20 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,C_Region_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'2828 SW Corbett Ave',NULL,NULL,NULL,100,50000,142,NULL,TO_TIMESTAMP('2008-09-22 13:17:38','YYYY-MM-DD HH24:MI:SS'),100,'Y',NULL,NULL,TO_TIMESTAMP('2008-09-22 13:17:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:19:21 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,C_Region_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'2828 SW Corbett Ave',NULL,NULL,NULL,100,50001,142,NULL,TO_TIMESTAMP('2008-09-22 13:18:50','YYYY-MM-DD HH24:MI:SS'),100,'Y',NULL,NULL,TO_TIMESTAMP('2008-09-22 13:18:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:19:47 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,C_Region_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'2828 SW Corbett Ave',NULL,NULL,NULL,100,50002,142,NULL,TO_TIMESTAMP('2008-09-22 13:19:44','YYYY-MM-DD HH24:MI:SS'),100,'Y',NULL,NULL,TO_TIMESTAMP('2008-09-22 13:19:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:20:12 PM CDT
-- Manufacturing Demo
UPDATE C_Location SET Address1='2828 SW Corbett Ave', Address2='Suite 130', Address3=NULL, Address4=NULL, C_Country_ID=100, C_Region_ID=142, City=NULL, Postal=NULL, Postal_Add=NULL,Updated=TO_TIMESTAMP('2008-09-22 13:20:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_Location_ID=50002
;

-- Sep 22, 2008 1:20:22 PM CDT
-- Manufacturing Demo
UPDATE C_Location SET Address1='2828 SW Corbett Ave', Address2='Suite 130', Address3=NULL, Address4=NULL, C_Country_ID=100, C_Region_ID=142, City='Portland', Postal='97201', Postal_Add=NULL,Updated=TO_TIMESTAMP('2008-09-22 13:20:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_Location_ID=114
;

-- Sep 22, 2008 1:20:34 PM CDT
-- Manufacturing Demo
UPDATE C_Location SET Address1='2828 SW Corbett Ave', Address2='Suite 130', Address3=NULL, Address4=NULL, C_Country_ID=100, C_Region_ID=142, City='Portland', Postal='97201', Postal_Add=NULL,Updated=TO_TIMESTAMP('2008-09-22 13:20:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_Location_ID=50002
;


-- Sep 22, 2008 1:20:36 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse (AD_Client_ID,AD_Org_ID,C_Location_ID,Created,CreatedBy,IsActive,IsInTransit,M_Warehouse_ID,Name,Separator,Updated,UpdatedBy,Value) VALUES (11,11,50002,TO_TIMESTAMP('2008-09-22 13:20:36','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y',50000,'HQ Transit','*',TO_TIMESTAMP('2008-09-22 13:20:36','YYYY-MM-DD HH24:MI:SS'),100,'HQT')
;

-- Sep 22, 2008 1:20:36 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse_Acct (M_Warehouse_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,W_Differences_Acct,W_InvActualAdjust_Acct,W_Inventory_Acct,W_Revaluation_Acct) SELECT 50000, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.W_Differences_Acct,p.W_InvActualAdjust_Acct,p.W_Inventory_Acct,p.W_Revaluation_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM M_Warehouse_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Warehouse_ID=50000)
;

-- Sep 22, 2008 1:20:54 PM CDT
-- Manufacturing Demo
INSERT INTO M_Locator (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,IsDefault,M_Locator_ID,M_Warehouse_ID,PriorityNo,Updated,UpdatedBy,Value,X,Y,Z) VALUES (11,11,TO_TIMESTAMP('2008-09-22 13:20:54','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y',50000,50000,50,TO_TIMESTAMP('2008-09-22 13:20:54','YYYY-MM-DD HH24:MI:SS'),100,'HQ Transit','HQ Transit','HQ Transit','HQ Transit')
;

-- Sep 22, 2008 1:23:02 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,C_Region_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'Carretera Mexico Pachuca Km 80',NULL,NULL,NULL,247,50003,188,'Pachuca',TO_TIMESTAMP('2008-09-22 13:21:48','YYYY-MM-DD HH24:MI:SS'),100,'Y','43000',NULL,TO_TIMESTAMP('2008-09-22 13:21:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:23:11 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse (AD_Client_ID,AD_Org_ID,C_Location_ID,Created,CreatedBy,Description,IsActive,IsInTransit,M_Warehouse_ID,Name,Separator,Updated,UpdatedBy,Value) VALUES (11,50000,50003,TO_TIMESTAMP('2008-09-22 13:23:09','YYYY-MM-DD HH24:MI:SS'),100,'Furniture','Y','N',50001,'Furniture','*',TO_TIMESTAMP('2008-09-22 13:23:09','YYYY-MM-DD HH24:MI:SS'),100,'Furniture')
;

-- Sep 22, 2008 1:23:11 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse_Acct (M_Warehouse_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,W_Differences_Acct,W_InvActualAdjust_Acct,W_Inventory_Acct,W_Revaluation_Acct) SELECT 50001, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.W_Differences_Acct,p.W_InvActualAdjust_Acct,p.W_Inventory_Acct,p.W_Revaluation_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM M_Warehouse_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Warehouse_ID=50001)
;

-- Sep 22, 2008 1:30:24 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'Schillerstrabe 18d',NULL,NULL,NULL,101,50004,'Munich',TO_TIMESTAMP('2008-09-22 13:23:56','YYYY-MM-DD HH24:MI:SS'),100,'Y','47445',NULL,TO_TIMESTAMP('2008-09-22 13:23:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:30:33 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse (AD_Client_ID,AD_Org_ID,C_Location_ID,Created,CreatedBy,Description,IsActive,IsInTransit,M_Warehouse_ID,Name,Separator,Updated,UpdatedBy,Value) VALUES (11,50001,50004,TO_TIMESTAMP('2008-09-22 13:30:32','YYYY-MM-DD HH24:MI:SS'),100,'Fertilizer','Y','N',50002,'Fertilizer','*',TO_TIMESTAMP('2008-09-22 13:30:32','YYYY-MM-DD HH24:MI:SS'),100,'Fertilizer')
;

-- Sep 22, 2008 1:30:33 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse_Acct (M_Warehouse_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,W_Differences_Acct,W_InvActualAdjust_Acct,W_Inventory_Acct,W_Revaluation_Acct) SELECT 50002, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.W_Differences_Acct,p.W_InvActualAdjust_Acct,p.W_Inventory_Acct,p.W_Revaluation_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM M_Warehouse_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Warehouse_ID=50002)
;

-- Sep 22, 2008 1:30:47 PM CDT
-- Manufacturing Demo
INSERT INTO M_Locator (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,IsDefault,M_Locator_ID,M_Warehouse_ID,PriorityNo,Updated,UpdatedBy,Value,X,Y,Z) VALUES (11,50001,TO_TIMESTAMP('2008-09-22 13:30:47','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',50001,50002,50,TO_TIMESTAMP('2008-09-22 13:30:47','YYYY-MM-DD HH24:MI:SS'),100,'10000000','Fertilizer','Fertilizer','Fertilizer')
;

-- Sep 22, 2008 1:30:53 PM CDT
-- Manufacturing Demo
UPDATE M_Locator SET Value='Fertilizer',Updated=TO_TIMESTAMP('2008-09-22 13:30:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Locator_ID=50001
;

-- Sep 22, 2008 1:36:46 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,C_Region_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'Store North',NULL,NULL,NULL,100,50005,142,NULL,TO_TIMESTAMP('2008-09-22 13:36:11','YYYY-MM-DD HH24:MI:SS'),100,'Y',NULL,NULL,TO_TIMESTAMP('2008-09-22 13:36:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:36:50 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse (AD_Client_ID,AD_Org_ID,C_Location_ID,Created,CreatedBy,IsActive,IsInTransit,M_Warehouse_ID,Name,Separator,Updated,UpdatedBy,Value) VALUES (11,50002,50005,TO_TIMESTAMP('2008-09-22 13:36:50','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',50003,'Store North','*',TO_TIMESTAMP('2008-09-22 13:36:50','YYYY-MM-DD HH24:MI:SS'),100,'Store North')
;

-- Sep 22, 2008 1:36:50 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse_Acct (M_Warehouse_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,W_Differences_Acct,W_InvActualAdjust_Acct,W_Inventory_Acct,W_Revaluation_Acct) SELECT 50003, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.W_Differences_Acct,p.W_InvActualAdjust_Acct,p.W_Inventory_Acct,p.W_Revaluation_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM M_Warehouse_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Warehouse_ID=50003)
;

-- Sep 22, 2008 1:37:12 PM CDT
-- Manufacturing Demo
INSERT INTO M_Locator (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,IsDefault,M_Locator_ID,M_Warehouse_ID,PriorityNo,Updated,UpdatedBy,Value,X,Y,Z) VALUES (11,50002,TO_TIMESTAMP('2008-09-22 13:37:09','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',50002,50003,50,TO_TIMESTAMP('2008-09-22 13:37:09','YYYY-MM-DD HH24:MI:SS'),100,'Store North','Store North','Store North','Store North')
;

-- Sep 22, 2008 1:37:31 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,C_Region_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'Store South',NULL,NULL,NULL,100,50006,142,NULL,TO_TIMESTAMP('2008-09-22 13:37:27','YYYY-MM-DD HH24:MI:SS'),100,'Y',NULL,NULL,TO_TIMESTAMP('2008-09-22 13:37:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:37:34 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse (AD_Client_ID,AD_Org_ID,C_Location_ID,Created,CreatedBy,IsActive,IsInTransit,M_Warehouse_ID,Name,Separator,Updated,UpdatedBy,Value) VALUES (11,50004,50006,TO_TIMESTAMP('2008-09-22 13:37:33','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',50004,'Store South','*',TO_TIMESTAMP('2008-09-22 13:37:33','YYYY-MM-DD HH24:MI:SS'),100,'Store South')
;

-- Sep 22, 2008 1:37:34 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse_Acct (M_Warehouse_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,W_Differences_Acct,W_InvActualAdjust_Acct,W_Inventory_Acct,W_Revaluation_Acct) SELECT 50004, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.W_Differences_Acct,p.W_InvActualAdjust_Acct,p.W_Inventory_Acct,p.W_Revaluation_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM M_Warehouse_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Warehouse_ID=50004)
;

-- Sep 22, 2008 1:37:45 PM CDT
-- Manufacturing Demo
INSERT INTO M_Locator (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,IsDefault,M_Locator_ID,M_Warehouse_ID,PriorityNo,Updated,UpdatedBy,Value,X,Y,Z) VALUES (11,50004,TO_TIMESTAMP('2008-09-22 13:37:43','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',50003,50004,50,TO_TIMESTAMP('2008-09-22 13:37:43','YYYY-MM-DD HH24:MI:SS'),100,'Store South','Store South','Store South','Store South')
;

-- Sep 22, 2008 1:37:48 PM CDT
-- Manufacturing Demo
UPDATE M_Locator SET IsDefault='Y',Updated=TO_TIMESTAMP('2008-09-22 13:37:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Locator_ID=50003
;

-- Sep 22, 2008 1:37:55 PM CDT
-- Manufacturing Demo
UPDATE M_Locator SET IsDefault='Y',Updated=TO_TIMESTAMP('2008-09-22 13:37:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Locator_ID=50002
;

-- Sep 22, 2008 1:38:01 PM CDT
-- Manufacturing Demo
UPDATE M_Locator SET IsDefault='Y',Updated=TO_TIMESTAMP('2008-09-22 13:38:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Locator_ID=50001
;

-- Sep 22, 2008 1:38:17 PM CDT
-- Manufacturing Demo
INSERT INTO M_Locator (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,IsDefault,M_Locator_ID,M_Warehouse_ID,PriorityNo,Updated,UpdatedBy,Value,X,Y,Z) VALUES (11,50000,TO_TIMESTAMP('2008-09-22 13:38:17','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y',50004,50001,50,TO_TIMESTAMP('2008-09-22 13:38:17','YYYY-MM-DD HH24:MI:SS'),100,'10000001','Furniture','Furniture','Furniture')
;

-- Sep 22, 2008 1:38:20 PM CDT
-- Manufacturing Demo
UPDATE M_Locator SET Value='Furniture',Updated=TO_TIMESTAMP('2008-09-22 13:38:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Locator_ID=50004
;

-- Sep 22, 2008 1:38:45 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,C_Region_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'Store East',NULL,NULL,NULL,100,50007,142,NULL,TO_TIMESTAMP('2008-09-22 13:38:41','YYYY-MM-DD HH24:MI:SS'),100,'Y',NULL,NULL,TO_TIMESTAMP('2008-09-22 13:38:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:38:50 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse (AD_Client_ID,AD_Org_ID,C_Location_ID,Created,CreatedBy,IsActive,IsInTransit,M_Warehouse_ID,Name,Separator,Updated,UpdatedBy,Value) VALUES (11,50005,50007,TO_TIMESTAMP('2008-09-22 13:38:48','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',50005,'Store East','*',TO_TIMESTAMP('2008-09-22 13:38:48','YYYY-MM-DD HH24:MI:SS'),100,'Store East')
;

-- Sep 22, 2008 1:38:50 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse_Acct (M_Warehouse_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,W_Differences_Acct,W_InvActualAdjust_Acct,W_Inventory_Acct,W_Revaluation_Acct) SELECT 50005, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.W_Differences_Acct,p.W_InvActualAdjust_Acct,p.W_Inventory_Acct,p.W_Revaluation_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM M_Warehouse_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Warehouse_ID=50005)
;

-- Sep 22, 2008 1:38:58 PM CDT
-- Manufacturing Demo
INSERT INTO M_Locator (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,IsDefault,M_Locator_ID,M_Warehouse_ID,PriorityNo,Updated,UpdatedBy,Value,X,Y,Z) VALUES (11,50005,TO_TIMESTAMP('2008-09-22 13:38:56','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y',50005,50005,50,TO_TIMESTAMP('2008-09-22 13:38:56','YYYY-MM-DD HH24:MI:SS'),100,'10000002','Store East','Store East','Store East')
;

-- Sep 22, 2008 1:39:01 PM CDT
-- Manufacturing Demo
UPDATE M_Locator SET Value='Store East',Updated=TO_TIMESTAMP('2008-09-22 13:39:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Locator_ID=50005
;

-- Sep 22, 2008 1:39:29 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,C_Region_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'Store West',NULL,NULL,NULL,100,50008,142,NULL,TO_TIMESTAMP('2008-09-22 13:39:26','YYYY-MM-DD HH24:MI:SS'),100,'Y',NULL,NULL,TO_TIMESTAMP('2008-09-22 13:39:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 1:39:39 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse (AD_Client_ID,AD_Org_ID,C_Location_ID,Created,CreatedBy,IsActive,IsInTransit,M_Warehouse_ID,Name,Separator,Updated,UpdatedBy,Value) VALUES (11,50006,50008,TO_TIMESTAMP('2008-09-22 13:39:35','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',50006,'Store West','*',TO_TIMESTAMP('2008-09-22 13:39:35','YYYY-MM-DD HH24:MI:SS'),100,'Store West')
;

-- Sep 22, 2008 1:39:39 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse_Acct (M_Warehouse_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,W_Differences_Acct,W_InvActualAdjust_Acct,W_Inventory_Acct,W_Revaluation_Acct) SELECT 50006, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.W_Differences_Acct,p.W_InvActualAdjust_Acct,p.W_Inventory_Acct,p.W_Revaluation_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM M_Warehouse_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Warehouse_ID=50006)
;

-- Sep 22, 2008 1:39:49 PM CDT
-- Manufacturing Demo
INSERT INTO M_Locator (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,IsDefault,M_Locator_ID,M_Warehouse_ID,PriorityNo,Updated,UpdatedBy,Value,X,Y,Z) VALUES (11,50006,TO_TIMESTAMP('2008-09-22 13:39:46','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',50006,50006,50,TO_TIMESTAMP('2008-09-22 13:39:46','YYYY-MM-DD HH24:MI:SS'),100,'Store West','Store West','Store West','Store West')
;

-- Sep 22, 2008 1:39:54 PM CDT
-- Manufacturing Demo
UPDATE M_Locator SET IsDefault='Y',Updated=TO_TIMESTAMP('2008-09-22 13:39:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Locator_ID=50006
;

-- Sep 22, 2008 1:40:41 PM CDT
-- Manufacturing Demo
UPDATE M_Warehouse SET Name='Store Central', Value='Store Central',Updated=TO_TIMESTAMP('2008-09-22 13:40:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Warehouse_ID=104
;

-- Sep 22, 2008 1:40:49 PM CDT
-- Manufacturing Demo
UPDATE M_Locator SET IsDefault='Y',Updated=TO_TIMESTAMP('2008-09-22 13:40:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Locator_ID=102
;

-- Sep 22, 2008 1:43:24 PM CDT
-- Manufacturing Demo
INSERT INTO C_BPartner (AD_Client_ID,AD_Language,AD_Org_ID,AcqusitionCost,ActualLifeTimeValue,C_BP_Group_ID,C_BPartner_ID,C_Dunning_ID,C_PaymentTerm_ID,Created,CreatedBy,DocumentCopies,FlatDiscount,IsActive,IsCustomer,IsDiscountPrinted,IsEmployee,IsOneTime,IsProspect,IsSalesRep,IsSummary,IsTaxExempt,IsVendor,M_PriceList_ID,Name,NumberEmployees,PotentialLifeTimeValue,SOCreditStatus,SO_CreditLimit,SO_CreditUsed,SalesRep_ID,SalesVolume,SendEMail,ShareOfCustomer,ShelfLifeMinPct,TotalOpenBalance,Updated,UpdatedBy,Value) VALUES (11,'en_US',0,0,0,104,50000,100,105,TO_TIMESTAMP('2008-09-22 13:43:15','YYYY-MM-DD HH24:MI:SS'),100,0,0,'Y','N','N','N','N','N','N','N','N','N',101,'Wood, Inc',0,0,'O',0,0,100,0,'N',0,0,0,TO_TIMESTAMP('2008-09-22 13:43:15','YYYY-MM-DD HH24:MI:SS'),100,'Wood, Inc')
;

-- Sep 22, 2008 1:43:24 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodeBP (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50000, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='BP' AND NOT EXISTS (SELECT * FROM AD_TreeNodeBP e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50000)
;

-- Sep 22, 2008 1:43:24 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Customer_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,C_Prepayment_Acct,C_Receivable_Acct,C_Receivable_Services_Acct) SELECT 50000, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.C_Prepayment_Acct,p.C_Receivable_Acct,p.C_Receivable_Services_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=104 AND NOT EXISTS (SELECT * FROM C_BP_Customer_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50000)
;

-- Sep 22, 2008 1:43:24 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Vendor_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,V_Liability_Acct,V_Liability_Services_Acct,V_Prepayment_Acct) SELECT 50000, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.V_Liability_Acct,p.V_Liability_Services_Acct,p.V_Prepayment_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=104 AND NOT EXISTS (SELECT * FROM C_BP_Vendor_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50000)
;

-- Sep 22, 2008 1:43:24 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Employee_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,E_Expense_Acct,E_Prepayment_Acct) SELECT 50000, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.E_Expense_Acct,p.E_Prepayment_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM C_BP_Employee_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50000)
;

-- Sep 22, 2008 1:43:29 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Process (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,11,291,100,50000,101,131,TO_TIMESTAMP('2008-09-22 13:43:24','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50000,TO_TIMESTAMP('2008-09-22 13:43:24','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 22, 2008 1:43:29 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-22 13:43:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50000
;

-- Sep 22, 2008 1:43:31 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Activity (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Activity_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,11,291,100,50000,244,50000,100,131,TO_TIMESTAMP('2008-09-22 13:43:29','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50000,TO_TIMESTAMP('2008-09-22 13:43:29','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 22, 2008 1:43:35 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_EventAudit (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_EventAudit_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,Created,CreatedBy,ElapsedTimeMS,EventType,IsActive,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,11,291,100,50000,244,50000,100,TO_TIMESTAMP('2008-09-22 13:43:32','YYYY-MM-DD HH24:MI:SS'),100,0,'PC','Y',50000,TO_TIMESTAMP('2008-09-22 13:43:32','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 22, 2008 1:43:35 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-22 13:43:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50000
;

-- Sep 22, 2008 1:43:35 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OR',Updated=TO_TIMESTAMP('2008-09-22 13:43:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50000
;

-- Sep 22, 2008 1:43:35 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-22 13:43:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50000
;

-- Sep 22, 2008 1:43:35 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OS',Updated=TO_TIMESTAMP('2008-09-22 13:43:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50000
;

-- Sep 22, 2008 1:43:35 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-22 13:43:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50000
;

-- Sep 22, 2008 1:43:59 PM CDT
-- Manufacturing Demo
UPDATE C_BPartner SET IsVendor='Y', PO_DiscountSchema_ID=102, PO_PaymentTerm_ID=107, PO_PriceList_ID=102, PaymentRulePO='T',Updated=TO_TIMESTAMP('2008-09-22 13:43:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_BPartner_ID=50000
;

-- Sep 22, 2008 1:44:48 PM CDT
-- Manufacturing Demo
INSERT INTO C_BPartner (AD_Client_ID,AD_Language,AD_Org_ID,AcqusitionCost,ActualLifeTimeValue,C_BP_Group_ID,C_BPartner_ID,C_Dunning_ID,C_PaymentTerm_ID,Created,CreatedBy,DocumentCopies,FlatDiscount,IsActive,IsCustomer,IsDiscountPrinted,IsEmployee,IsOneTime,IsProspect,IsSalesRep,IsSummary,IsTaxExempt,IsVendor,M_PriceList_ID,Name,NumberEmployees,PotentialLifeTimeValue,SOCreditStatus,SO_CreditLimit,SO_CreditUsed,SalesRep_ID,SalesVolume,SendEMail,ShareOfCustomer,ShelfLifeMinPct,TotalOpenBalance,Updated,UpdatedBy,Value) VALUES (11,'en_US',11,0,0,104,50001,100,105,TO_TIMESTAMP('2008-09-22 13:44:45','YYYY-MM-DD HH24:MI:SS'),100,0,0,'Y','N','N','N','N','N','N','N','N','N',101,'Color, Inc',0,0,'O',0,0,100,0,'N',0,0,0,TO_TIMESTAMP('2008-09-22 13:44:45','YYYY-MM-DD HH24:MI:SS'),100,'Color, Inc')
;

-- Sep 22, 2008 1:44:48 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodeBP (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50001, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='BP' AND NOT EXISTS (SELECT * FROM AD_TreeNodeBP e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50001)
;

-- Sep 22, 2008 1:44:48 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Customer_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,C_Prepayment_Acct,C_Receivable_Acct,C_Receivable_Services_Acct) SELECT 50001, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.C_Prepayment_Acct,p.C_Receivable_Acct,p.C_Receivable_Services_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=104 AND NOT EXISTS (SELECT * FROM C_BP_Customer_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50001)
;

-- Sep 22, 2008 1:44:48 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Vendor_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,V_Liability_Acct,V_Liability_Services_Acct,V_Prepayment_Acct) SELECT 50001, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.V_Liability_Acct,p.V_Liability_Services_Acct,p.V_Prepayment_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=104 AND NOT EXISTS (SELECT * FROM C_BP_Vendor_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50001)
;

-- Sep 22, 2008 1:44:48 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Employee_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,E_Expense_Acct,E_Prepayment_Acct) SELECT 50001, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.E_Expense_Acct,p.E_Prepayment_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM C_BP_Employee_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50001)
;

-- Sep 22, 2008 1:44:58 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Process (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,11,291,100,50001,101,131,TO_TIMESTAMP('2008-09-22 13:44:48','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50001,TO_TIMESTAMP('2008-09-22 13:44:48','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 22, 2008 1:44:58 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-22 13:44:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50001
;

-- Sep 22, 2008 1:45:00 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Activity (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Activity_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,11,291,100,50001,244,50001,100,131,TO_TIMESTAMP('2008-09-22 13:44:58','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50001,TO_TIMESTAMP('2008-09-22 13:44:58','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 22, 2008 1:45:03 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_EventAudit (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_EventAudit_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,Created,CreatedBy,ElapsedTimeMS,EventType,IsActive,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,11,291,100,50001,244,50001,100,TO_TIMESTAMP('2008-09-22 13:45:00','YYYY-MM-DD HH24:MI:SS'),100,0,'PC','Y',50001,TO_TIMESTAMP('2008-09-22 13:45:00','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 22, 2008 1:45:03 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-22 13:45:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50001
;

-- Sep 22, 2008 1:45:03 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OR',Updated=TO_TIMESTAMP('2008-09-22 13:45:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50001
;

-- Sep 22, 2008 1:45:03 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-22 13:45:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50001
;

-- Sep 22, 2008 1:45:03 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OS',Updated=TO_TIMESTAMP('2008-09-22 13:45:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50001
;

-- Sep 22, 2008 1:45:03 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-22 13:45:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50001
;

-- Sep 22, 2008 1:45:37 PM CDT
-- Manufacturing Demo
UPDATE C_BPartner SET IsVendor='Y', PO_PaymentTerm_ID=108, PO_PriceList_ID=102, PaymentRulePO='T',Updated=TO_TIMESTAMP('2008-09-22 13:45:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_BPartner_ID=50001
;

-- Sep 22, 2008 1:46:00 PM CDT
-- Manufacturing Demo
INSERT INTO C_BPartner (AD_Client_ID,AD_Language,AD_Org_ID,AcqusitionCost,ActualLifeTimeValue,C_BP_Group_ID,C_BPartner_ID,C_Dunning_ID,C_PaymentTerm_ID,Created,CreatedBy,DocumentCopies,FlatDiscount,IsActive,IsCustomer,IsDiscountPrinted,IsEmployee,IsOneTime,IsProspect,IsSalesRep,IsSummary,IsTaxExempt,IsVendor,M_PriceList_ID,Name,NumberEmployees,PotentialLifeTimeValue,SOCreditStatus,SO_CreditLimit,SO_CreditUsed,SalesRep_ID,SalesVolume,SendEMail,ShareOfCustomer,ShelfLifeMinPct,TotalOpenBalance,Updated,UpdatedBy,Value) VALUES (11,'en_US',0,0,0,104,50002,100,105,TO_TIMESTAMP('2008-09-22 13:45:58','YYYY-MM-DD HH24:MI:SS'),100,0,0,'Y','N','N','N','N','N','N','N','N','N',101,'Chrome, Inc',0,0,'X',0,0,100,0,'N',0,0,0,TO_TIMESTAMP('2008-09-22 13:45:58','YYYY-MM-DD HH24:MI:SS'),100,'Chrome, Inc')
;

-- Sep 22, 2008 1:46:00 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodeBP (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50002, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='BP' AND NOT EXISTS (SELECT * FROM AD_TreeNodeBP e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50002)
;

-- Sep 22, 2008 1:46:00 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Customer_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,C_Prepayment_Acct,C_Receivable_Acct,C_Receivable_Services_Acct) SELECT 50002, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.C_Prepayment_Acct,p.C_Receivable_Acct,p.C_Receivable_Services_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=104 AND NOT EXISTS (SELECT * FROM C_BP_Customer_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50002)
;

-- Sep 22, 2008 1:46:00 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Vendor_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,V_Liability_Acct,V_Liability_Services_Acct,V_Prepayment_Acct) SELECT 50002, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.V_Liability_Acct,p.V_Liability_Services_Acct,p.V_Prepayment_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=104 AND NOT EXISTS (SELECT * FROM C_BP_Vendor_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50002)
;

-- Sep 22, 2008 1:46:00 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Employee_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,E_Expense_Acct,E_Prepayment_Acct) SELECT 50002, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.E_Expense_Acct,p.E_Prepayment_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM C_BP_Employee_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50002)
;

-- Sep 22, 2008 1:46:05 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Process (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,11,291,100,50002,101,131,TO_TIMESTAMP('2008-09-22 13:46:00','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50002,TO_TIMESTAMP('2008-09-22 13:46:00','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 22, 2008 1:46:05 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-22 13:46:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50002
;

-- Sep 22, 2008 1:46:08 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Activity (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Activity_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,11,291,100,50002,244,50002,100,131,TO_TIMESTAMP('2008-09-22 13:46:05','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50002,TO_TIMESTAMP('2008-09-22 13:46:05','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 22, 2008 1:46:10 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_EventAudit (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_EventAudit_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,Created,CreatedBy,ElapsedTimeMS,EventType,IsActive,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,11,291,100,50002,244,50002,100,TO_TIMESTAMP('2008-09-22 13:46:08','YYYY-MM-DD HH24:MI:SS'),100,0,'PC','Y',50002,TO_TIMESTAMP('2008-09-22 13:46:08','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 22, 2008 1:46:10 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-22 13:46:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50002
;

-- Sep 22, 2008 1:46:10 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OR',Updated=TO_TIMESTAMP('2008-09-22 13:46:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50002
;

-- Sep 22, 2008 1:46:10 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-22 13:46:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50002
;

-- Sep 22, 2008 1:46:10 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OS',Updated=TO_TIMESTAMP('2008-09-22 13:46:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50002
;

-- Sep 22, 2008 1:46:10 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-22 13:46:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50002
;

-- Sep 22, 2008 1:46:30 PM CDT
-- Manufacturing Demo
UPDATE C_BPartner SET IsVendor='Y', PO_PaymentTerm_ID=106, PO_PriceList_ID=102, PaymentRulePO='T',Updated=TO_TIMESTAMP('2008-09-22 13:46:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_BPartner_ID=50002
;

-- Sep 22, 2008 1:47:29 PM CDT
-- Manufacturing Demo
INSERT INTO C_BPartner (AD_Client_ID,AD_Language,AD_Org_ID,AcqusitionCost,ActualLifeTimeValue,C_BP_Group_ID,C_BPartner_ID,C_Dunning_ID,C_PaymentTerm_ID,Created,CreatedBy,DocumentCopies,FlatDiscount,IsActive,IsCustomer,IsDiscountPrinted,IsEmployee,IsOneTime,IsProspect,IsSalesRep,IsSummary,IsTaxExempt,IsVendor,M_PriceList_ID,Name,NumberEmployees,PotentialLifeTimeValue,SOCreditStatus,SO_CreditLimit,SO_CreditUsed,SalesRep_ID,SalesVolume,SendEMail,ShareOfCustomer,ShelfLifeMinPct,TotalOpenBalance,Updated,UpdatedBy,Value) VALUES (11,'en_US',0,0,0,104,50003,100,105,TO_TIMESTAMP('2008-09-22 13:47:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,'Y','N','N','N','N','N','N','N','N','N',101,'Chemical Product, inc',0,0,'X',0,0,100,0,'N',0,0,0,TO_TIMESTAMP('2008-09-22 13:47:27','YYYY-MM-DD HH24:MI:SS'),100,'Chemical Product, inc')
;

-- Sep 22, 2008 1:47:29 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodeBP (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50003, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='BP' AND NOT EXISTS (SELECT * FROM AD_TreeNodeBP e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50003)
;

-- Sep 22, 2008 1:47:29 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Customer_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,C_Prepayment_Acct,C_Receivable_Acct,C_Receivable_Services_Acct) SELECT 50003, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.C_Prepayment_Acct,p.C_Receivable_Acct,p.C_Receivable_Services_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=104 AND NOT EXISTS (SELECT * FROM C_BP_Customer_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50003)
;

-- Sep 22, 2008 1:47:29 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Vendor_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,V_Liability_Acct,V_Liability_Services_Acct,V_Prepayment_Acct) SELECT 50003, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.V_Liability_Acct,p.V_Liability_Services_Acct,p.V_Prepayment_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=104 AND NOT EXISTS (SELECT * FROM C_BP_Vendor_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50003)
;

-- Sep 22, 2008 1:47:29 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Employee_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,E_Expense_Acct,E_Prepayment_Acct) SELECT 50003, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.E_Expense_Acct,p.E_Prepayment_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM C_BP_Employee_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50003)
;

-- Sep 22, 2008 1:47:30 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Process (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,11,291,100,50003,101,131,TO_TIMESTAMP('2008-09-22 13:47:29','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50003,TO_TIMESTAMP('2008-09-22 13:47:29','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 22, 2008 1:47:30 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-22 13:47:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50003
;

-- Sep 22, 2008 1:47:34 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Activity (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Activity_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,11,291,100,50003,244,50003,100,131,TO_TIMESTAMP('2008-09-22 13:47:30','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50003,TO_TIMESTAMP('2008-09-22 13:47:30','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 22, 2008 1:47:35 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_EventAudit (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_EventAudit_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,Created,CreatedBy,ElapsedTimeMS,EventType,IsActive,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,11,291,100,50003,244,50003,100,TO_TIMESTAMP('2008-09-22 13:47:34','YYYY-MM-DD HH24:MI:SS'),100,0,'PC','Y',50003,TO_TIMESTAMP('2008-09-22 13:47:34','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 22, 2008 1:47:35 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-22 13:47:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50003
;

-- Sep 22, 2008 1:47:35 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OR',Updated=TO_TIMESTAMP('2008-09-22 13:47:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50003
;

-- Sep 22, 2008 1:47:35 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-22 13:47:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50003
;

-- Sep 22, 2008 1:47:35 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OS',Updated=TO_TIMESTAMP('2008-09-22 13:47:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50003
;

-- Sep 22, 2008 1:47:35 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-22 13:47:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50003
;

-- Sep 22, 2008 1:47:52 PM CDT
-- Manufacturing Demo
UPDATE C_BPartner SET IsVendor='Y', PO_PaymentTerm_ID=107, PO_PriceList_ID=102, PaymentRulePO='T',Updated=TO_TIMESTAMP('2008-09-22 13:47:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_BPartner_ID=50003
;

-- Sep 22, 2008 1:49:51 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Category (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,Created,CreatedBy,IsActive,IsDefault,IsSelfService,MMPolicy,M_Product_Category_ID,Name,PlannedMargin,Updated,UpdatedBy,Value) VALUES (11,0,111,TO_TIMESTAMP('2008-09-22 13:49:50','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','F',50000,'Assembly',0,TO_TIMESTAMP('2008-09-22 13:49:50','YYYY-MM-DD HH24:MI:SS'),100,'Assembly')
;

-- Sep 22, 2008 1:49:51 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Category_Acct (M_Product_Category_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50000, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM M_Product_Category_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_Category_ID=50000)
;

-- Sep 22, 2008 1:50:56 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,100,TO_TIMESTAMP('2008-09-22 13:50:55','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','N','Y','N','N','N','N','N',101,50000,50000,'Assembly Back Leg','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 13:50:55','YYYY-MM-DD HH24:MI:SS'),100,'PBackLeg',0,0)
;

-- Sep 22, 2008 1:50:56 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50000 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 1:50:56 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50000, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50000 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50000)
;

-- Sep 22, 2008 1:50:56 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50000, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50000)
;

-- Sep 22, 2008 1:51:18 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_AttributeSetInstance_ID,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,100,TO_TIMESTAMP('2008-09-22 13:51:17','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','N','Y','N','N','N','N','N',0,101,50000,50001,'Assembly Fron Leg','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 13:51:17','YYYY-MM-DD HH24:MI:SS'),100,'PFronLeg',0,0)
;

-- Sep 22, 2008 1:51:18 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50001 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 1:51:18 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50001, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50000 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50001)
;

-- Sep 22, 2008 1:51:18 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50001, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50001)
;

-- Sep 22, 2008 1:54:55 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,100,TO_TIMESTAMP('2008-09-22 13:54:51','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','Y','Y','Y','Y','N','N','N',50004,105,50002,'Screw 8 x 1','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 13:54:51','YYYY-MM-DD HH24:MI:SS'),100,'Screw',0,0)
;

-- Sep 22, 2008 1:54:55 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50002 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 1:54:55 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50002, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=105 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50002)
;

-- Sep 22, 2008 1:54:55 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50002, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50002)
;

-- Sep 22, 2008 1:58:16 PM CDT
-- Manufacturing Demo
INSERT INTO C_UOM (AD_Client_ID,AD_Org_ID,C_UOM_ID,CostingPrecision,Created,CreatedBy,IsActive,IsDefault,Name,StdPrecision,UOMSymbol,Updated,UpdatedBy,X12DE355) VALUES (11,0,50000,4,TO_TIMESTAMP('2008-09-22 13:58:14','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','Mililiter',4,'ml',TO_TIMESTAMP('2008-09-22 13:58:14','YYYY-MM-DD HH24:MI:SS'),100,'ml')
;

-- Sep 22, 2008 1:58:16 PM CDT
-- Manufacturing Demo
INSERT INTO C_UOM_Trl (AD_Language,C_UOM_ID, Description,Name,UOMSymbol, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_UOM_ID, t.Description,t.Name,t.UOMSymbol, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_UOM t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_UOM_ID=50000 AND EXISTS (SELECT * FROM C_UOM_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_UOM_ID!=t.C_UOM_ID)
;

-- Sep 22, 2008 1:59:23 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,50000,TO_TIMESTAMP('2008-09-22 13:59:22','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','Y','Y','Y','Y','N','N','N',50004,105,50003,'Ultra Glue','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 13:59:22','YYYY-MM-DD HH24:MI:SS'),100,'UltraGlue',0,0)
;

-- Sep 22, 2008 1:59:23 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50003 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 1:59:23 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50003, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=105 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50003)
;

-- Sep 22, 2008 1:59:23 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50003, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50003)
;

-- Sep 22, 2008 1:59:32 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsSold='N',Updated=TO_TIMESTAMP('2008-09-22 13:59:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50003
;

-- Sep 22, 2008 1:59:37 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsSold='N',Updated=TO_TIMESTAMP('2008-09-22 13:59:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50002
;

-- Sep 22, 2008 2:00:40 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,100,TO_TIMESTAMP('2008-09-22 14:00:34','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','N','N','Y','Y','N','N','N',50004,105,50004,'Seat','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 14:00:34','YYYY-MM-DD HH24:MI:SS'),100,'Seat',0,0)
;

-- Sep 22, 2008 2:00:40 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50004 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 2:00:40 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50004, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=105 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50004)
;

-- Sep 22, 2008 2:00:40 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50004, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50004)
;

-- Sep 22, 2008 2:02:57 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET Name='#6-32 x 3/8 Socket Head Cap Screw',Updated=TO_TIMESTAMP('2008-09-22 14:02:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50002
;

-- Sep 22, 2008 2:02:57 PM CDT
-- Manufacturing Demo
UPDATE M_Product_Trl SET Description=NULL,DocumentNote=NULL,Name='#6-32 x 3/8 Socket Head Cap Screw',IsTranslated='Y' WHERE M_Product_ID=50002
;

-- Sep 22, 2008 2:02:57 PM CDT
-- Manufacturing Demo
UPDATE A_Asset SET Name=SUBSTR((SELECT bp.Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=A_Asset.C_BPartner_ID) || ' - ' || p.Name,1,60),Description=p.Description FROM M_Product p WHERE p.M_Product_ID=A_Asset.M_Product_ID AND A_Asset.IsActive='Y' AND A_Asset.M_Product_ID=50002
;

-- Sep 22, 2008 2:04:56 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,100,TO_TIMESTAMP('2008-09-22 14:04:55','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','Y','Y','N','Y','N','N','N',50004,105,50005,'Back Support','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 14:04:55','YYYY-MM-DD HH24:MI:SS'),100,'PBackSupport',0,0)
;

-- Sep 22, 2008 2:04:56 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50005 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 2:04:56 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50005, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=105 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50005)
;

-- Sep 22, 2008 2:04:56 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50005, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50005)
;

-- Sep 22, 2008 2:05:03 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET Value='PSeat',Updated=TO_TIMESTAMP('2008-09-22 14:05:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50004
;

-- Sep 22, 2008 2:05:09 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET Value='PScrew',Updated=TO_TIMESTAMP('2008-09-22 14:05:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50002
;

-- Sep 22, 2008 2:08:19 PM CDT
-- Manufacturing Demo
INSERT INTO C_UOM (AD_Client_ID,AD_Org_ID,C_UOM_ID,CostingPrecision,Created,CreatedBy,IsActive,IsDefault,Name,StdPrecision,UOMSymbol,Updated,UpdatedBy,X12DE355) VALUES (11,0,50001,0,TO_TIMESTAMP('2008-09-22 14:08:18','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','kilogram',2,'Kg',TO_TIMESTAMP('2008-09-22 14:08:18','YYYY-MM-DD HH24:MI:SS'),100,'Kg')
;

-- Sep 22, 2008 2:08:19 PM CDT
-- Manufacturing Demo
INSERT INTO C_UOM_Trl (AD_Language,C_UOM_ID, Description,Name,UOMSymbol, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_UOM_ID, t.Description,t.Name,t.UOMSymbol, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_UOM t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_UOM_ID=50001 AND EXISTS (SELECT * FROM C_UOM_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_UOM_ID!=t.C_UOM_ID)
;

-- Sep 22, 2008 2:10:56 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,100,TO_TIMESTAMP('2008-09-22 14:10:54','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','Y','Y','Y','Y','N','N','N',50001,105,50007,'Fertilizer #70','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 14:10:54','YYYY-MM-DD HH24:MI:SS'),100,'Fertilizer #70',0,0)
;

-- Sep 22, 2008 2:10:56 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50007 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 2:10:56 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50007, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=105 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50007)
;

-- Sep 22, 2008 2:10:56 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50007, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50007)
;

-- Sep 22, 2008 2:11:36 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET Value='Fertilizer#50',Updated=TO_TIMESTAMP('2008-09-22 14:11:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=136
;

-- Sep 22, 2008 2:11:59 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET Description='70 # Bag of Lawn Fertilizer', Value='Fertilizer#70',Updated=TO_TIMESTAMP('2008-09-22 14:11:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50007
;

-- Sep 22, 2008 2:11:59 PM CDT
-- Manufacturing Demo
UPDATE M_Product_Trl SET Description='70 # Bag of Lawn Fertilizer',DocumentNote=NULL,Name='Fertilizer #70',IsTranslated='Y' WHERE M_Product_ID=50007
;

-- Sep 22, 2008 2:11:59 PM CDT
-- Manufacturing Demo
UPDATE A_Asset SET Name=SUBSTR((SELECT bp.Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=A_Asset.C_BPartner_ID) || ' - ' || p.Name,1,60),Description=p.Description FROM M_Product p WHERE p.M_Product_ID=A_Asset.M_Product_ID AND A_Asset.IsActive='Y' AND A_Asset.M_Product_ID=50007
;

-- Sep 22, 2008 2:12:10 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Product_Category_ID=109,Updated=TO_TIMESTAMP('2008-09-22 14:12:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50007
;

-- Sep 22, 2008 2:12:28 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Locator_ID=50001,Updated=TO_TIMESTAMP('2008-09-22 14:12:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=136
;

-- Sep 22, 2008 2:13:17 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,50001,TO_TIMESTAMP('2008-09-22 14:13:14','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','N','Y','Y','Y','N','N','N',50001,109,50008,'Lawn Fertilizer','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 14:13:14','YYYY-MM-DD HH24:MI:SS'),100,'Fertilizer',0,0)
;

-- Sep 22, 2008 2:13:17 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50008 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 2:13:17 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50008, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=109 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50008)
;

-- Sep 22, 2008 2:13:17 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50008, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50008)
;

-- Sep 22, 2008 2:14:44 PM CDT
-- Manufacturing Demo
INSERT INTO C_UOM (AD_Client_ID,AD_Org_ID,C_UOM_ID,CostingPrecision,Created,CreatedBy,IsActive,IsDefault,Name,StdPrecision,UOMSymbol,Updated,UpdatedBy,X12DE355) VALUES (11,0,50002,6,TO_TIMESTAMP('2008-09-22 14:14:39','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','milligram',6,'mg',TO_TIMESTAMP('2008-09-22 14:14:39','YYYY-MM-DD HH24:MI:SS'),100,'mg')
;

-- Sep 22, 2008 2:14:44 PM CDT
-- Manufacturing Demo
INSERT INTO C_UOM_Trl (AD_Language,C_UOM_ID, Description,Name,UOMSymbol, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_UOM_ID, t.Description,t.Name,t.UOMSymbol, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_UOM t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_UOM_ID=50002 AND EXISTS (SELECT * FROM C_UOM_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_UOM_ID!=t.C_UOM_ID)
;

-- Sep 22, 2008 2:15:20 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,50002,TO_TIMESTAMP('2008-09-22 14:15:17','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','Y','N','N','Y','N','N','N',50001,109,50009,'Nitrogen','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 14:15:17','YYYY-MM-DD HH24:MI:SS'),100,'Nitrogen',0,0)
;

-- Sep 22, 2008 2:15:20 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50009 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 2:15:20 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50009, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=109 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50009)
;

-- Sep 22, 2008 2:15:20 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50009, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50009)
;

-- Sep 22, 2008 2:16:25 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,50002,TO_TIMESTAMP('2008-09-22 14:16:24','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','Y','Y','N','Y','N','N','N',50001,109,50010,'Phosphorus','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 14:16:24','YYYY-MM-DD HH24:MI:SS'),100,'Phosphorus',0,0)
;

-- Sep 22, 2008 2:16:25 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50010 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 2:16:25 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50010, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=109 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50010)
;

-- Sep 22, 2008 2:16:25 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50010, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50010)
;

-- Sep 22, 2008 2:17:32 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,50002,TO_TIMESTAMP('2008-09-22 14:17:31','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','Y','Y','Y','Y','N','N','N',50001,109,50012,'Potassium','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 14:17:31','YYYY-MM-DD HH24:MI:SS'),100,'Potassium',0,0)
;

-- Sep 22, 2008 2:17:32 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50012 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 2:17:32 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50012, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=109 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50012)
;

-- Sep 22, 2008 2:17:32 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50012, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50012)
;

-- Sep 22, 2008 2:19:07 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Category (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,Created,CreatedBy,IsActive,IsDefault,IsSelfService,MMPolicy,M_Product_Category_ID,Name,PlannedMargin,Updated,UpdatedBy,Value) VALUES (11,0,100,TO_TIMESTAMP('2008-09-22 14:19:06','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','Y','F',50001,'Raw Material',0,TO_TIMESTAMP('2008-09-22 14:19:06','YYYY-MM-DD HH24:MI:SS'),100,'Raw Material')
;

-- Sep 22, 2008 2:19:07 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Category_Acct (M_Product_Category_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50001, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM M_Product_Category_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_Category_ID=50001)
;

-- Sep 22, 2008 2:21:20 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Category (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,Created,CreatedBy,IsActive,IsDefault,IsSelfService,MMPolicy,M_Product_Category_ID,Name,PlannedMargin,Updated,UpdatedBy,Value) VALUES (11,0,100,TO_TIMESTAMP('2008-09-22 14:21:17','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','F',50002,'Packing',0,TO_TIMESTAMP('2008-09-22 14:21:17','YYYY-MM-DD HH24:MI:SS'),100,'Packing')
;

-- Sep 22, 2008 2:21:20 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Category_Acct (M_Product_Category_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50002, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM M_Product_Category_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_Category_ID=50002)
;

-- Sep 22, 2008 2:21:57 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,100,TO_TIMESTAMP('2008-09-22 14:21:56','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','Y','Y','N','Y','N','N','N',50001,50002,50013,'Bag 50 Kg','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 14:21:56','YYYY-MM-DD HH24:MI:SS'),100,'Bag50',0,0)
;

-- Sep 22, 2008 2:21:57 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50013 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 2:21:57 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50013, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50002 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50013)
;

-- Sep 22, 2008 2:21:57 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50013, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50013)
;

-- Sep 22, 2008 2:22:13 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_AttributeSetInstance_ID,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,100,TO_TIMESTAMP('2008-09-22 14:22:12','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','Y','N','N','Y','N','N','N',0,50001,50002,50014,'Bag 70 Kg','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 14:22:12','YYYY-MM-DD HH24:MI:SS'),100,'Bag70',0,0)
;

-- Sep 22, 2008 2:22:13 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50014 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 2:22:13 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50014, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50002 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50014)
;

-- Sep 22, 2008 2:22:13 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50014, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50014)
;

-- Sep 22, 2008 2:22:38 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Product_Category_ID=50001,Updated=TO_TIMESTAMP('2008-09-22 14:22:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50003
;

-- Sep 22, 2008 2:23:02 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Product_Category_ID=50001,Updated=TO_TIMESTAMP('2008-09-22 14:23:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50004
;

-- Sep 22, 2008 2:23:08 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Product_Category_ID=50001,Updated=TO_TIMESTAMP('2008-09-22 14:23:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50002
;

-- Sep 22, 2008 2:23:24 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Product_Category_ID=50001,Updated=TO_TIMESTAMP('2008-09-22 14:23:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=135
;

-- Sep 22, 2008 2:23:35 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Product_Category_ID=50001,Updated=TO_TIMESTAMP('2008-09-22 14:23:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50012
;

-- Sep 22, 2008 2:23:51 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Product_Category_ID=50001,Updated=TO_TIMESTAMP('2008-09-22 14:23:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50010
;

-- Sep 22, 2008 2:24:11 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Product_Category_ID=50001,Updated=TO_TIMESTAMP('2008-09-22 14:24:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50005
;

-- Sep 22, 2008 2:24:25 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Product_Category_ID=50001,Updated=TO_TIMESTAMP('2008-09-22 14:24:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50009
;

-- Sep 22, 2008 2:24:44 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Product_Category_ID=50000,Updated=TO_TIMESTAMP('2008-09-22 14:24:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50008
;

-- Sep 22, 2008 2:25:57 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_PO (AD_Client_ID,AD_Org_ID,C_BPartner_ID,C_Currency_ID,C_UOM_ID,CostPerOrder,Created,CreatedBy,DeliveryTime_Actual,DeliveryTime_Promised,Discontinued,IsActive,IsCurrentVendor,M_Product_ID,Order_Min,Order_Pack,PriceLastInv,PriceLastPO,PriceList,PricePO,QualityRating,RoyaltyAmt,Updated,UpdatedBy,VendorProductNo) VALUES (11,0,50000,100,100,0,TO_TIMESTAMP('2008-09-22 14:25:57','YYYY-MM-DD HH24:MI:SS'),100,0,15,'N','Y','Y',50004,200.000000000000,50.000000000000,0,0,4.000000000000,4.000000000000,0,0,TO_TIMESTAMP('2008-09-22 14:25:57','YYYY-MM-DD HH24:MI:SS'),100,'PSeat')
;

-- Sep 22, 2008 2:27:04 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_PO (AD_Client_ID,AD_Org_ID,C_BPartner_ID,C_Currency_ID,C_UOM_ID,CostPerOrder,Created,CreatedBy,DeliveryTime_Actual,DeliveryTime_Promised,Discontinued,IsActive,IsCurrentVendor,M_Product_ID,Order_Min,Order_Pack,PriceLastInv,PriceLastPO,PriceList,PricePO,QualityRating,RoyaltyAmt,Updated,UpdatedBy,VendorProductNo) VALUES (11,0,50000,100,100,0,TO_TIMESTAMP('2008-09-22 14:27:04','YYYY-MM-DD HH24:MI:SS'),100,0,20,'N','Y','Y',50005,60.000000000000,20.000000000000,0,0,0,0,0,0,TO_TIMESTAMP('2008-09-22 14:27:04','YYYY-MM-DD HH24:MI:SS'),100,'PBackSupport')
;

-- Sep 22, 2008 2:27:46 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,100,TO_TIMESTAMP('2008-09-22 14:27:45','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','Y','Y','Y','Y','N','N','N',50004,50001,50015,'Front Leg','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 14:27:45','YYYY-MM-DD HH24:MI:SS'),100,'PFront Leg',0,0)
;

-- Sep 22, 2008 2:27:46 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50015 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 2:27:46 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50015, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50001 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50015)
;

-- Sep 22, 2008 2:27:46 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50015, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50015)
;

-- Sep 22, 2008 2:29:40 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_PO (AD_Client_ID,AD_Org_ID,C_BPartner_ID,C_Currency_ID,C_UOM_ID,CostPerOrder,Created,CreatedBy,DeliveryTime_Actual,DeliveryTime_Promised,Discontinued,IsActive,IsCurrentVendor,M_Product_ID,Order_Min,Order_Pack,PriceLastInv,PriceLastPO,PriceList,PricePO,QualityRating,RoyaltyAmt,Updated,UpdatedBy,VendorProductNo) VALUES (11,0,50000,130,100,0,TO_TIMESTAMP('2008-09-22 14:29:40','YYYY-MM-DD HH24:MI:SS'),100,0,15,'N','Y','Y',50015,8.000000000000,2.000000000000,0,0,25.000000000000,25.000000000000,0,0,TO_TIMESTAMP('2008-09-22 14:29:40','YYYY-MM-DD HH24:MI:SS'),100,'PFront Leg')
;

-- Sep 22, 2008 2:30:19 PM CDT
-- Manufacturing Demo
UPDATE M_Product_PO SET C_Currency_ID=130, PriceList=70.000000000000, PricePO=70.000000000000,Updated=TO_TIMESTAMP('2008-09-22 14:30:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_BPartner_ID=50000 AND M_Product_ID=50005
;

-- Sep 22, 2008 2:30:39 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET Value='Front Leg',Updated=TO_TIMESTAMP('2008-09-22 14:30:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50015
;

-- Sep 22, 2008 2:31:05 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_AttributeSetInstance_ID,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,100,TO_TIMESTAMP('2008-09-22 14:31:03','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','Y','Y','Y','Y','N','N','N',0,50004,50001,50016,'Back Leg','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 14:31:03','YYYY-MM-DD HH24:MI:SS'),100,'BackLeg',0,0)
;

-- Sep 22, 2008 2:31:05 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50016 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 2:31:05 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50016, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50001 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50016)
;

-- Sep 22, 2008 2:31:05 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50016, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50016)
;

-- Sep 22, 2008 2:31:48 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_PO (AD_Client_ID,AD_Org_ID,C_BPartner_ID,C_Currency_ID,C_UOM_ID,CostPerOrder,Created,CreatedBy,DeliveryTime_Actual,DeliveryTime_Promised,Discontinued,IsActive,IsCurrentVendor,M_Product_ID,Order_Min,Order_Pack,PriceLastInv,PriceLastPO,PriceList,PricePO,QualityRating,RoyaltyAmt,Updated,UpdatedBy,VendorProductNo) VALUES (11,0,50000,130,100,0,TO_TIMESTAMP('2008-09-22 14:31:48','YYYY-MM-DD HH24:MI:SS'),100,0,20,'N','Y','Y',50016,8.000000000000,2.000000000000,0,0,35.000000000000,35.000000000000,0,0,TO_TIMESTAMP('2008-09-22 14:31:48','YYYY-MM-DD HH24:MI:SS'),100,'BackLeg')
;

-- Sep 22, 2008 2:32:38 PM CDT
-- Manufacturing Demo
UPDATE M_Product_PO SET C_Currency_ID=130, PriceList=40.000000000000, PricePO=40.000000000000,Updated=TO_TIMESTAMP('2008-09-22 14:32:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_BPartner_ID=50000 AND M_Product_ID=50004
;

-- Sep 22, 2008 2:32:50 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET Value='Seat',Updated=TO_TIMESTAMP('2008-09-22 14:32:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50004
;

-- Sep 22, 2008 2:33:02 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET Value='BackSupport',Updated=TO_TIMESTAMP('2008-09-22 14:33:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50005
;

-- Sep 22, 2008 2:33:11 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET Value='FrontLeg',Updated=TO_TIMESTAMP('2008-09-22 14:33:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50015
;

-- Sep 22, 2008 2:34:23 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_PO (AD_Client_ID,AD_Org_ID,C_BPartner_ID,C_Currency_ID,C_UOM_ID,CostPerOrder,Created,CreatedBy,DeliveryTime_Actual,DeliveryTime_Promised,Discontinued,IsActive,IsCurrentVendor,M_Product_ID,Order_Min,Order_Pack,PriceLastInv,PriceLastPO,PriceList,PricePO,QualityRating,RoyaltyAmt,Updated,UpdatedBy,VendorProductNo) VALUES (11,0,50003,102,50002,0,TO_TIMESTAMP('2008-09-22 14:34:23','YYYY-MM-DD HH24:MI:SS'),100,0,30,'N','Y','Y',50009,10000.000000000000,1000.000000000000,0,0,1.000000000000,0,0,0,TO_TIMESTAMP('2008-09-22 14:34:23','YYYY-MM-DD HH24:MI:SS'),100,'Nitrogen')
;

-- Sep 22, 2008 2:37:02 PM CDT
-- Manufacturing Demo
INSERT INTO C_UOM (AD_Client_ID,AD_Org_ID,C_UOM_ID,CostingPrecision,Created,CreatedBy,IsActive,IsDefault,Name,StdPrecision,UOMSymbol,Updated,UpdatedBy,X12DE355) VALUES (11,0,50003,0,TO_TIMESTAMP('2008-09-22 14:37:01','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','litre',2,'L',TO_TIMESTAMP('2008-09-22 14:37:01','YYYY-MM-DD HH24:MI:SS'),100,'L')
;

-- Sep 22, 2008 2:37:02 PM CDT
-- Manufacturing Demo
INSERT INTO C_UOM_Trl (AD_Language,C_UOM_ID, Description,Name,UOMSymbol, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_UOM_ID, t.Description,t.Name,t.UOMSymbol, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_UOM t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_UOM_ID=50003 AND EXISTS (SELECT * FROM C_UOM_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_UOM_ID!=t.C_UOM_ID)
;

-- Sep 22, 2008 2:37:11 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_PO (AD_Client_ID,AD_Org_ID,C_BPartner_ID,C_Currency_ID,C_UOM_ID,CostPerOrder,Created,CreatedBy,DeliveryTime_Actual,DeliveryTime_Promised,Discontinued,IsActive,IsCurrentVendor,M_Product_ID,Order_Min,Order_Pack,PriceLastInv,PriceLastPO,PriceList,PricePO,QualityRating,RoyaltyAmt,Updated,UpdatedBy,VendorProductNo) VALUES (11,0,50003,102,50003,0,TO_TIMESTAMP('2008-09-22 14:37:11','YYYY-MM-DD HH24:MI:SS'),100,0,0,'N','Y','Y',50003,0,0,0,0,0,0,0,0,TO_TIMESTAMP('2008-09-22 14:37:11','YYYY-MM-DD HH24:MI:SS'),100,'UltraGlue')
;

-- Sep 22, 2008 2:38:49 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_PO (AD_Client_ID,AD_Org_ID,C_BPartner_ID,C_Currency_ID,C_UOM_ID,CostPerOrder,Created,CreatedBy,DeliveryTime_Actual,DeliveryTime_Promised,Discontinued,IsActive,IsCurrentVendor,M_Product_ID,Order_Min,Order_Pack,PriceLastInv,PriceLastPO,PriceList,PricePO,QualityRating,RoyaltyAmt,Updated,UpdatedBy,VendorProductNo) VALUES (11,0,50003,102,50001,0,TO_TIMESTAMP('2008-09-22 14:38:49','YYYY-MM-DD HH24:MI:SS'),100,0,30,'N','Y','Y',50012,1000.000000000000,100.000000000000,0,0,2.000000000000,2.000000000000,0,0,TO_TIMESTAMP('2008-09-22 14:38:49','YYYY-MM-DD HH24:MI:SS'),100,'Potassium')
;

-- Sep 22, 2008 2:39:42 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_PO (AD_Client_ID,AD_Org_ID,C_BPartner_ID,C_Currency_ID,C_UOM_ID,CostPerOrder,Created,CreatedBy,DeliveryTime_Actual,DeliveryTime_Promised,Discontinued,IsActive,IsCurrentVendor,M_Product_ID,Order_Min,Order_Pack,PriceLastInv,PriceLastPO,PriceList,PricePO,QualityRating,RoyaltyAmt,Updated,UpdatedBy,VendorProductNo) VALUES (11,0,50003,102,50002,0,TO_TIMESTAMP('2008-09-22 14:39:42','YYYY-MM-DD HH24:MI:SS'),100,0,20,'N','Y','Y',50010,10000.000000000000,1000.000000000000,0,0,0.500000000000,0.500000000000,0,0,TO_TIMESTAMP('2008-09-22 14:39:42','YYYY-MM-DD HH24:MI:SS'),100,'Phosphorus')
;

-- Sep 22, 2008 2:40:36 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET Value='Screw',Updated=TO_TIMESTAMP('2008-09-22 14:40:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50002
;

-- Sep 22, 2008 2:40:42 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Product_Category_ID=110, Value='Screen',Updated=TO_TIMESTAMP('2008-09-22 14:40:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=135
;

-- Sep 22, 2008 2:44:23 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOM (AD_Client_ID,AD_Org_ID,BOMType,BOMUse,C_UOM_ID,CopyFrom,Created,CreatedBy,Description,DocumentNo,IsActive,M_Product_ID,Name,PP_Product_BOM_ID,Processing,Updated,UpdatedBy,ValidFrom,Value) VALUES (11,0,'A','M',100,'N',TO_TIMESTAMP('2008-09-22 14:44:17','YYYY-MM-DD HH24:MI:SS'),100,'Nice Chair for outdoors','50000','Y',133,'Patio Chair',50000,'N',TO_TIMESTAMP('2008-09-22 14:44:17','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-22','YYYY-MM-DD'),'PChair')
;

-- Sep 22, 2008 2:44:23 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsBOM='Y',Updated=TO_TIMESTAMP('2008-09-22 14:44:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=133
;

-- Sep 22, 2008 2:44:28 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_BOM SET ValidFrom=TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'),Updated=TO_TIMESTAMP('2008-09-22 14:44:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_BOM_ID=50000
;

-- Sep 22, 2008 2:45:26 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,100,'CO',TO_TIMESTAMP('2008-09-22 14:45:22','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','0',0,10,50004,50000,50000,1.000000000000,0,0,TO_TIMESTAMP('2008-09-22 14:45:22','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 2:45:26 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=2,Updated=TO_TIMESTAMP('2008-09-22 14:45:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50004
;

-- Sep 22, 2008 2:46:03 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,100,'CO',TO_TIMESTAMP('2008-09-22 14:46:01','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','0',0,20,50005,50001,50000,1.000000000000,0,0,TO_TIMESTAMP('2008-09-22 14:46:01','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 2:46:03 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=2,Updated=TO_TIMESTAMP('2008-09-22 14:46:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50005
;

-- Sep 22, 2008 2:49:25 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,100,'CO',TO_TIMESTAMP('2008-09-22 14:49:24','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','0',0,30,50000,50002,50000,2.000000000000,0,0,TO_TIMESTAMP('2008-09-22 14:49:24','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 2:49:25 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=2,Updated=TO_TIMESTAMP('2008-09-22 14:49:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50000
;

-- Sep 22, 2008 2:50:12 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,100,'PH',TO_TIMESTAMP('2008-09-22 14:50:11','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','1',0,40,50001,50003,50000,2.000000000000,0,0,TO_TIMESTAMP('2008-09-22 14:50:11','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 2:50:12 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=2,Updated=TO_TIMESTAMP('2008-09-22 14:50:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50001
;

-- Sep 22, 2008 2:50:34 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_BOMLine SET ComponentType='PH',Updated=TO_TIMESTAMP('2008-09-22 14:50:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_BOMLine_ID=50002
;

-- Sep 22, 2008 2:55:06 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOM (AD_Client_ID,AD_Org_ID,BOMType,BOMUse,C_UOM_ID,CopyFrom,Created,CreatedBy,DocumentNo,IsActive,M_Product_ID,Name,PP_Product_BOM_ID,Processing,Updated,UpdatedBy,ValidFrom,Value) VALUES (11,0,'A','M',100,'N',TO_TIMESTAMP('2008-09-22 14:55:05','YYYY-MM-DD HH24:MI:SS'),100,'50001','Y',50000,'Assembly Back Leg',50001,'N',TO_TIMESTAMP('2008-09-22 14:55:05','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'),'PBackLeg')
;

-- Sep 22, 2008 2:55:06 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsBOM='Y',Updated=TO_TIMESTAMP('2008-09-22 14:55:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50000
;

-- Sep 22, 2008 2:55:47 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,100,'CO',TO_TIMESTAMP('2008-09-22 14:55:46','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','0',0,10,50016,50004,50001,2.000000000000,0,0,TO_TIMESTAMP('2008-09-22 14:55:46','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 2:55:47 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=3,Updated=TO_TIMESTAMP('2008-09-22 14:55:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50016
;

-- Sep 22, 2008 2:56:14 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,100,'CO',TO_TIMESTAMP('2008-09-22 14:56:13','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','0',0,20,50015,50005,50001,2.000000000000,0,0,TO_TIMESTAMP('2008-09-22 14:56:13','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 2:56:14 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=3,Updated=TO_TIMESTAMP('2008-09-22 14:56:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50015
;

-- Sep 22, 2008 2:57:09 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_BOMLine SET M_Product_ID=50002, QtyBOM=8.000000000000,Updated=TO_TIMESTAMP('2008-09-22 14:57:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_BOMLine_ID=50005
;

-- Sep 22, 2008 2:57:09 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=3,Updated=TO_TIMESTAMP('2008-09-22 14:57:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50002
;

-- Sep 22, 2008 3:00:49 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,50000,'CO',TO_TIMESTAMP('2008-09-22 15:00:48','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','1',0,30,50003,50006,50001,500.000000000000,0,0,TO_TIMESTAMP('2008-09-22 15:00:48','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 3:00:49 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=3,Updated=TO_TIMESTAMP('2008-09-22 15:00:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50003
;

-- Sep 22, 2008 3:01:06 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_BOMLine SET IssueMethod='0',Updated=TO_TIMESTAMP('2008-09-22 15:01:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_BOMLine_ID=50006
;

-- Sep 22, 2008 3:05:35 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOM (AD_Client_ID,AD_Org_ID,BOMType,BOMUse,C_UOM_ID,CopyFrom,Created,CreatedBy,DocumentNo,IsActive,M_Product_ID,Name,PP_Product_BOM_ID,Processing,Updated,UpdatedBy,ValidFrom,Value) VALUES (11,0,'A','M',100,'N',TO_TIMESTAMP('2008-09-22 15:05:34','YYYY-MM-DD HH24:MI:SS'),100,'50002','Y',50001,'Assembly Fron Leg',50003,'N',TO_TIMESTAMP('2008-09-22 15:05:34','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'),'PFronLeg')
;

-- Sep 22, 2008 3:05:35 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsBOM='Y',Updated=TO_TIMESTAMP('2008-09-22 15:05:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50001
;

-- Sep 22, 2008 3:16:16 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,100,'CO',TO_TIMESTAMP('2008-09-22 15:16:14','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','0',0,10,50015,50007,50003,2.000000000000,0,0,TO_TIMESTAMP('2008-09-22 15:16:14','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 3:20:02 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,100,'CO',TO_TIMESTAMP('2008-09-22 15:20:01','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','0',0,20,50002,50008,50003,8.000000000000,0,0,TO_TIMESTAMP('2008-09-22 15:20:01','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 3:21:10 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,50000,'CO',TO_TIMESTAMP('2008-09-22 15:21:09','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','1',0,30,50003,50009,50003,650.000000000000,0,0,TO_TIMESTAMP('2008-09-22 15:21:09','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 3:21:30 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_BOMLine SET IssueMethod='1',Updated=TO_TIMESTAMP('2008-09-22 15:21:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_BOMLine_ID=50008
;

-- Sep 22, 2008 3:21:33 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_BOMLine SET IssueMethod='1',Updated=TO_TIMESTAMP('2008-09-22 15:21:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_BOMLine_ID=50007
;

-- Sep 22, 2008 3:21:48 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_BOMLine SET IssueMethod='1',Updated=TO_TIMESTAMP('2008-09-22 15:21:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_BOMLine_ID=50004
;

-- Sep 22, 2008 3:21:51 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_BOMLine SET IssueMethod='1',Updated=TO_TIMESTAMP('2008-09-22 15:21:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_BOMLine_ID=50005
;

-- Sep 22, 2008 3:22:00 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_BOMLine SET IssueMethod='1',Updated=TO_TIMESTAMP('2008-09-22 15:22:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_BOMLine_ID=50006
;

-- Sep 22, 2008 3:23:19 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50000,TO_TIMESTAMP('2008-09-22 15:23:17','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',0,'0',145,145,0,50000,TO_TIMESTAMP('2008-09-22 15:23:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 3:23:20 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50000,TO_TIMESTAMP('2008-09-22 15:23:19','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',1,'.1',134,100,145,145,1,50001,TO_TIMESTAMP('2008-09-22 15:23:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 3:23:22 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50000,TO_TIMESTAMP('2008-09-22 15:23:20','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',1,'.1',133,101,145,145,2,50002,TO_TIMESTAMP('2008-09-22 15:23:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 3:23:23 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50000,TO_TIMESTAMP('2008-09-22 15:23:22','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',2,'..2',50004,50000,50000,145,3,50003,TO_TIMESTAMP('2008-09-22 15:23:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 3:23:24 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50000,TO_TIMESTAMP('2008-09-22 15:23:23','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',2,'..2',50005,50001,50000,145,4,50004,TO_TIMESTAMP('2008-09-22 15:23:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 3:23:25 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50000,TO_TIMESTAMP('2008-09-22 15:23:24','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',2,'..2',50000,50002,50000,145,5,50005,TO_TIMESTAMP('2008-09-22 15:23:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 3:23:28 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50000,TO_TIMESTAMP('2008-09-22 15:23:25','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',3,'...3',50016,50004,50001,145,6,50006,TO_TIMESTAMP('2008-09-22 15:23:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 3:23:29 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50000,TO_TIMESTAMP('2008-09-22 15:23:28','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',3,'...3',50002,50005,50001,145,7,50007,TO_TIMESTAMP('2008-09-22 15:23:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 3:23:30 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50000,TO_TIMESTAMP('2008-09-22 15:23:29','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',3,'...3',50003,50006,50001,145,8,50008,TO_TIMESTAMP('2008-09-22 15:23:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 3:23:32 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50000,TO_TIMESTAMP('2008-09-22 15:23:30','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',2,'..2',50001,50003,50000,145,9,50009,TO_TIMESTAMP('2008-09-22 15:23:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 3:23:34 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50000,TO_TIMESTAMP('2008-09-22 15:23:32','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',3,'...3',50015,50007,50003,145,10,50010,TO_TIMESTAMP('2008-09-22 15:23:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 3:23:38 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50000,TO_TIMESTAMP('2008-09-22 15:23:34','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',3,'...3',50002,50008,50003,145,11,50011,TO_TIMESTAMP('2008-09-22 15:23:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 3:23:39 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50000,TO_TIMESTAMP('2008-09-22 15:23:38','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',3,'...3',50003,50009,50003,145,12,50012,TO_TIMESTAMP('2008-09-22 15:23:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 3:23:40 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50000,TO_TIMESTAMP('2008-09-22 15:23:39','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',1,'.1',135,102,145,145,13,50013,TO_TIMESTAMP('2008-09-22 15:23:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 3:23:43 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormat (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormat_ID,AD_PrintPaper_ID,AD_PrintTableFormat_ID,AD_ReportView_ID,AD_Table_ID,CreateCopy,Created,CreatedBy,Description,FooterMargin,HeaderMargin,IsActive,IsDefault,IsForm,IsStandardHeaderFooter,IsTableBased,Name,Updated,UpdatedBy) VALUES (11,0,100,130,50035,100,100,53007,53063,'N',TO_TIMESTAMP('2008-09-22 15:23:41','YYYY-MM-DD HH24:MI:SS'),100,'Multi Level BOM & Formula Detail',0,0,'Y','N','N','Y','Y','Multi Level BOM & Formula Detail &Copy Record 1746519845',TO_TIMESTAMP('2008-09-22 15:23:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 3:23:45 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54339,0,50917,50035,0,0,TO_TIMESTAMP('2008-09-22 15:23:43','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'BOM Line','C','F','BOM Line',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:23:43','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:23:45 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50917 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:23:45 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54339) WHERE AD_PrintFormatItem_ID = 50917 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54339 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50917) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:23:48 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54330,0,50918,50035,0,0,TO_TIMESTAMP('2008-09-22 15:23:46','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Active','C','F','Active',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:23:46','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:23:48 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50918 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:23:48 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54330) WHERE AD_PrintFormatItem_ID = 50918 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54330 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50918) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:23:49 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54340,0,50919,50035,0,0,TO_TIMESTAMP('2008-09-22 15:23:48','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'BOM & Formula','C','F','BOM & Formula',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:23:48','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:23:49 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50919 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:23:49 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54340) WHERE AD_PrintFormatItem_ID = 50919 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54340 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50919) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:23:50 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54326,0,50920,50035,0,0,TO_TIMESTAMP('2008-09-22 15:23:49','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Component Type','C','F','Component Type',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:23:49','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:23:50 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50920 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:23:50 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54326) WHERE AD_PrintFormatItem_ID = 50920 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54326 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50920) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:23:51 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54331,0,50921,50035,0,0,TO_TIMESTAMP('2008-09-22 15:23:50','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Is Critical Component','C','F','Is Critical Component',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:23:50','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:23:51 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50921 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:23:51 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54331) WHERE AD_PrintFormatItem_ID = 50921 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54331 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50921) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:23:53 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54328,0,50922,50035,0,0,TO_TIMESTAMP('2008-09-22 15:23:51','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Created By','C','F','Created By',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:23:51','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:23:53 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50922 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:23:53 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54328) WHERE AD_PrintFormatItem_ID = 50922 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54328 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50922) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:23:55 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54329,0,50923,50035,0,0,TO_TIMESTAMP('2008-09-22 15:23:53','YYYY-MM-DD HH24:MI:SS'),100,'B','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Description','C','F','Description',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:23:53','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:23:55 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50923 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:23:55 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54329) WHERE AD_PrintFormatItem_ID = 50923 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54329 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50923) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:23:56 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54323,0,50924,50035,0,0,TO_TIMESTAMP('2008-09-22 15:23:55','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Organization','C','F','Organization',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:23:55','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:23:56 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50924 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:23:56 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54323) WHERE AD_PrintFormatItem_ID = 50924 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54323 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50924) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:23:58 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54334,0,50925,50035,0,0,TO_TIMESTAMP('2008-09-22 15:23:56','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Level no','C','F','Level no',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:23:56','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:23:58 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50925 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:23:58 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54334) WHERE AD_PrintFormatItem_ID = 50925 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54334 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50925) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:01 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54337,0,50926,50035,0,0,TO_TIMESTAMP('2008-09-22 15:23:58','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Attribute Set Instance','C','F','Attribute Set Instance',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:23:58','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:01 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50926 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:01 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54337) WHERE AD_PrintFormatItem_ID = 50926 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54337 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50926) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:05 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54322,0,50927,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:01','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Client','C','F','Client',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:24:01','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:05 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50927 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:05 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54322) WHERE AD_PrintFormatItem_ID = 50927 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54322 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50927) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:05 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54348,0,50928,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:05','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Valid to','C','F','Valid to',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:24:05','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:05 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50928 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:05 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54348) WHERE AD_PrintFormatItem_ID = 50928 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54348 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50928) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:06 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54336,0,50929,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:05','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Line No','C','F','Line No',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:24:05','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:06 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50929 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:06 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54336) WHERE AD_PrintFormatItem_ID = 50929 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54336 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50929) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:08 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54327,0,50930,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:06','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Created','C','F','Created',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:24:06','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:08 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50930 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:08 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54327) WHERE AD_PrintFormatItem_ID = 50930 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54327 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50930) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:09 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54345,0,50931,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:08','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Updated','C','F','Updated',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:24:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:09 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50931 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:09 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54345) WHERE AD_PrintFormatItem_ID = 50931 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54345 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50931) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:10 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54346,0,50932,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:09','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Updated By','C','F','Updated By',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:24:09','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:10 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50932 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:10 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54346) WHERE AD_PrintFormatItem_ID = 50932 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54346 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50932) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:11 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54347,0,50933,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:10','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Valid from','C','F','Valid from',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:24:10','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:11 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50933 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:11 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54347) WHERE AD_PrintFormatItem_ID = 50933 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54347 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50933) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:12 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54324,0,50934,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:11','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Process Instance','C','F','Process Instance',0,0,'N',0,TO_TIMESTAMP('2008-09-22 15:24:11','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:12 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50934 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:12 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54324) WHERE AD_PrintFormatItem_ID = 50934 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54324 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50934) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:13 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54344,0,50935,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:12','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','Y','N','N','N','N','N','X',1,0,0,'Sequence','C','F','Sequence',0,0,'N',10,TO_TIMESTAMP('2008-09-22 15:24:12','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:13 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50935 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54344) WHERE AD_PrintFormatItem_ID = 50935 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54344 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50935) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:14 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54335,0,50936,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:13','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Levels','C','F','Levels',0,10,'N',0,TO_TIMESTAMP('2008-09-22 15:24:13','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:14 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50936 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:14 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54335) WHERE AD_PrintFormatItem_ID = 50936 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54335 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50936) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:16 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,55326,0,50937,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:14','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Product','C','F','Product',0,20,'N',0,TO_TIMESTAMP('2008-09-22 15:24:14','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:16 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50937 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:16 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=55326) WHERE AD_PrintFormatItem_ID = 50937 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=55326 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50937) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:17 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54332,0,50938,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:16','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Is Qty Percentage','C','F','Is Qty Percentage',0,30,'N',0,TO_TIMESTAMP('2008-09-22 15:24:16','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:17 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50938 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:17 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54332) WHERE AD_PrintFormatItem_ID = 50938 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54332 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50938) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:18 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54333,0,50939,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:17','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Issue Method','C','F','Issue Method',0,40,'N',0,TO_TIMESTAMP('2008-09-22 15:24:17','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:18 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50939 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:18 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54333) WHERE AD_PrintFormatItem_ID = 50939 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54333 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50939) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:20 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54341,0,50940,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:18','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Quantity','C','F','Quantity',0,50,'N',0,TO_TIMESTAMP('2008-09-22 15:24:18','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:20 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50940 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:20 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54341) WHERE AD_PrintFormatItem_ID = 50940 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54341 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50940) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:21 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54342,0,100,130,50941,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:20','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','Y','N','N','N','N','N','Y','N','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Quantity in %','C','F','Quantity in %',20,60,'N',0,TO_TIMESTAMP('2008-09-22 15:24:20','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:21 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50941 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:21 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54342) WHERE AD_PrintFormatItem_ID = 50941 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54342 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50941) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:22 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54325,0,50942,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:21','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'UOM','C','F','UOM',0,70,'N',0,TO_TIMESTAMP('2008-09-22 15:24:21','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:22 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50942 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:22 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54325) WHERE AD_PrintFormatItem_ID = 50942 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54325 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50942) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,54343,0,50943,50035,0,0,TO_TIMESTAMP('2008-09-22 15:24:22','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Scrap','C','F','Scrap',0,80,'N',0,TO_TIMESTAMP('2008-09-22 15:24:22','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50943 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54343) WHERE AD_PrintFormatItem_ID = 50943 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=54343 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50943) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50459 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50917 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50459)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50468 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50918 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50468)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50450 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50919 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50450)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50460 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50920 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50460)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50461 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50921 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50461)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50451 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50922 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50451)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50452 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50923 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50452)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50453 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50924 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50453)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50454 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50925 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50454)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50455 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50926 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50455)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50456 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50927 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50456)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50457 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50928 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50457)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50458 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50929 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50458)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50462 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50930 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50462)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50463 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50931 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50463)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50464 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50932 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50464)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50465 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50933 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50465)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50466 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50934 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50466)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50467 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50935 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50467)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50469 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50936 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50469)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50470 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50937 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50470)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50471 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50938 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50471)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50472 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50939 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50472)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50473 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50940 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50473)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50474 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50941 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50474)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50475 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50942 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50475)
;

-- Sep 22, 2008 3:24:23 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50476 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50943 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50476)
;

-- Sep 22, 2008 3:27:59 PM CDT
-- Manufacturing Demo
DELETE FROM T_BomLine WHERE AD_PInstance_ID = 50000
;

-- Sep 22, 2008 3:58:47 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOM (AD_Client_ID,AD_Org_ID,BOMType,BOMUse,C_UOM_ID,CopyFrom,Created,CreatedBy,Description,DocumentNo,IsActive,M_Product_ID,Name,PP_Product_BOM_ID,Processing,Updated,UpdatedBy,ValidFrom,Value) VALUES (11,0,'A','M',100,'N',TO_TIMESTAMP('2008-09-22 15:58:46','YYYY-MM-DD HH24:MI:SS'),100,'50 # Bag of Lawn Fertilizer','50003','Y',136,'Fertilizer #50',50004,'N',TO_TIMESTAMP('2008-09-22 15:58:46','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'),'Fertilizer#50')
;

-- Sep 22, 2008 3:58:47 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsBOM='Y',Updated=TO_TIMESTAMP('2008-09-22 15:58:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=136
;

-- Sep 22, 2008 4:05:36 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,50001,'CO',TO_TIMESTAMP('2008-09-22 16:05:35','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','1',0,10,50008,50010,50004,50.000000000000,0,2.000000000000,TO_TIMESTAMP('2008-09-22 16:05:35','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 4:05:37 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=1,Updated=TO_TIMESTAMP('2008-09-22 16:05:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50008
;

-- Sep 22, 2008 4:07:01 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,100,'PK',TO_TIMESTAMP('2008-09-22 16:06:55','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','1',0,20,50013,50011,50004,1.000000000000,0,0,TO_TIMESTAMP('2008-09-22 16:06:55','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 4:07:01 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=1,Updated=TO_TIMESTAMP('2008-09-22 16:07:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50013
;

-- Sep 22, 2008 4:08:19 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_BOMLine SET Scrap=1.000000000000,Updated=TO_TIMESTAMP('2008-09-22 16:08:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_BOMLine_ID=50010
;

-- Sep 22, 2008 4:08:57 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOM (AD_Client_ID,AD_Org_ID,BOMType,BOMUse,C_UOM_ID,CopyFrom,Created,CreatedBy,Description,DocumentNo,IsActive,M_Product_ID,Name,PP_Product_BOM_ID,Processing,Updated,UpdatedBy,ValidFrom,Value) VALUES (11,0,'A','M',100,'N',TO_TIMESTAMP('2008-09-22 16:08:56','YYYY-MM-DD HH24:MI:SS'),100,'70 # Bag of Lawn Fertilizer','50004','Y',50007,'Fertilizer #70',50005,'N',TO_TIMESTAMP('2008-09-22 16:08:56','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'),'Fertilizer#70')
;

-- Sep 22, 2008 4:08:57 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsBOM='Y',Updated=TO_TIMESTAMP('2008-09-22 16:08:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50007
;

-- Sep 22, 2008 4:09:46 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,50001,'CO',TO_TIMESTAMP('2008-09-22 16:09:35','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','1',0,10,50008,50012,50005,70.000000000000,0,1.000000000000,TO_TIMESTAMP('2008-09-22 16:09:35','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 4:10:49 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,100,'PK',TO_TIMESTAMP('2008-09-22 16:10:47','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','1',0,20,50014,50013,50005,1.000000000000,0,0,TO_TIMESTAMP('2008-09-22 16:10:47','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 4:10:49 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=1,Updated=TO_TIMESTAMP('2008-09-22 16:10:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50014
;

-- Sep 22, 2008 4:56:12 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOM (AD_Client_ID,AD_Org_ID,BOMType,BOMUse,C_UOM_ID,CopyFrom,Created,CreatedBy,DocumentNo,IsActive,M_Product_ID,Name,PP_Product_BOM_ID,Processing,Updated,UpdatedBy,ValidFrom,Value) VALUES (11,0,'A','M',50001,'N',TO_TIMESTAMP('2008-09-22 16:56:10','YYYY-MM-DD HH24:MI:SS'),100,'50005','Y',50008,'Lawn Fertilizer',50006,'N',TO_TIMESTAMP('2008-09-22 16:56:10','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'),'Fertilizer')
;

-- Sep 22, 2008 4:56:12 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsBOM='Y',Updated=TO_TIMESTAMP('2008-09-22 16:56:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50008
;

-- Sep 22, 2008 4:58:23 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,50001,'CO',TO_TIMESTAMP('2008-09-22 16:58:21','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','Y','1',0,10,50010,50014,50006,0,17.000000000000,2.000000000000,TO_TIMESTAMP('2008-09-22 16:58:21','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 4:58:24 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=2,Updated=TO_TIMESTAMP('2008-09-22 16:58:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50010
;

-- Sep 22, 2008 4:59:06 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,50001,'CO',TO_TIMESTAMP('2008-09-22 16:59:05','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','Y','1',0,20,50009,50015,50006,0,17.000000000000,2.000000000000,TO_TIMESTAMP('2008-09-22 16:59:05','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 4:59:06 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=2,Updated=TO_TIMESTAMP('2008-09-22 16:59:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50009
;

-- Sep 22, 2008 5:00:00 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,100,'CO',TO_TIMESTAMP('2008-09-22 16:59:58','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','Y','1',0,30,50012,50016,50006,0,17.000000000000,2.000000000000,TO_TIMESTAMP('2008-09-22 16:59:58','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 5:00:00 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=2,Updated=TO_TIMESTAMP('2008-09-22 17:00:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50012
;

-- Sep 22, 2008 5:05:22 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,Discontinued,GuaranteeDays,GuaranteeDaysMin,IsActive,IsBOM,IsDropShip,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Locator_ID,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,ShelfDepth,ShelfHeight,ShelfWidth,UnitsPerPallet,Updated,UpdatedBy,Value,Volume,Weight) VALUES (11,0,107,50003,TO_TIMESTAMP('2008-09-22 17:05:21','YYYY-MM-DD HH24:MI:SS'),100,'N',0,0,'Y','N','N','N','N','N','N','Y','N','N','N','N','N',101,50001,50017,'Whater','N','I',0,0,0,0,TO_TIMESTAMP('2008-09-22 17:05:21','YYYY-MM-DD HH24:MI:SS'),100,'Whater',0,0)
;

-- Sep 22, 2008 5:05:22 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50017 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 5:05:22 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50017, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50001 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50017)
;

-- Sep 22, 2008 5:05:22 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50017, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50017)
;

-- Sep 22, 2008 5:06:47 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,QtyBOM,QtyBatch,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,11,0,100,'CO',TO_TIMESTAMP('2008-09-22 17:06:47','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','Y','1',0,40,50017,50017,50006,0,49.000000000000,0,TO_TIMESTAMP('2008-09-22 17:06:47','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22','YYYY-MM-DD'))
;

-- Sep 22, 2008 5:06:47 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=2,Updated=TO_TIMESTAMP('2008-09-22 17:06:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50017
;

-- Sep 22, 2008 5:10:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_BOMLine SET IsCritical='Y',Updated=TO_TIMESTAMP('2008-09-22 17:10:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_BOMLine_ID=50017
;

-- Sep 22, 2008 5:23:09 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50001,TO_TIMESTAMP('2008-09-22 17:23:06','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',0,'0',136,136,0,50014,TO_TIMESTAMP('2008-09-22 17:23:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 5:23:10 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50001,TO_TIMESTAMP('2008-09-22 17:23:09','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',1,'.1',50008,50010,50004,136,1,50015,TO_TIMESTAMP('2008-09-22 17:23:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 5:23:10 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50001,TO_TIMESTAMP('2008-09-22 17:23:10','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',2,'..2',50010,50014,50006,136,2,50016,TO_TIMESTAMP('2008-09-22 17:23:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 5:23:11 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50001,TO_TIMESTAMP('2008-09-22 17:23:10','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',2,'..2',50009,50015,50006,136,3,50017,TO_TIMESTAMP('2008-09-22 17:23:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 5:23:12 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50001,TO_TIMESTAMP('2008-09-22 17:23:11','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',2,'..2',50012,50016,50006,136,4,50018,TO_TIMESTAMP('2008-09-22 17:23:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 5:23:13 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50001,TO_TIMESTAMP('2008-09-22 17:23:12','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',2,'..2',50017,50017,50006,136,5,50019,TO_TIMESTAMP('2008-09-22 17:23:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 5:23:13 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50001,TO_TIMESTAMP('2008-09-22 17:23:13','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',1,'.1',50013,50011,50004,136,6,50020,TO_TIMESTAMP('2008-09-22 17:23:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 5:27:42 PM CDT
-- Manufacturing Demo
DELETE FROM T_BomLine WHERE AD_PInstance_ID = 50001
;

-- Sep 22, 2008 5:28:14 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_BOMLine SET C_UOM_ID=50001,Updated=TO_TIMESTAMP('2008-09-22 17:28:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_BOMLine_ID=50016
;

-- Sep 22, 2008 5:28:19 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_BOMLine SET C_UOM_ID=50003,Updated=TO_TIMESTAMP('2008-09-22 17:28:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_BOMLine_ID=50017
;

-- Sep 22, 2008 5:28:38 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50002,TO_TIMESTAMP('2008-09-22 17:28:35','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',0,'0',136,136,0,50021,TO_TIMESTAMP('2008-09-22 17:28:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 5:28:39 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50002,TO_TIMESTAMP('2008-09-22 17:28:38','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',1,'.1',50008,50010,50004,136,1,50022,TO_TIMESTAMP('2008-09-22 17:28:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 5:28:40 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50002,TO_TIMESTAMP('2008-09-22 17:28:39','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',2,'..2',50010,50014,50006,136,2,50023,TO_TIMESTAMP('2008-09-22 17:28:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 5:28:41 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50002,TO_TIMESTAMP('2008-09-22 17:28:40','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',2,'..2',50009,50015,50006,136,3,50024,TO_TIMESTAMP('2008-09-22 17:28:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 5:28:42 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50002,TO_TIMESTAMP('2008-09-22 17:28:41','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',2,'..2',50012,50016,50006,136,4,50025,TO_TIMESTAMP('2008-09-22 17:28:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 5:28:43 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50002,TO_TIMESTAMP('2008-09-22 17:28:42','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',2,'..2',50017,50017,50006,136,5,50026,TO_TIMESTAMP('2008-09-22 17:28:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 5:28:45 PM CDT
-- Manufacturing Demo
INSERT INTO T_BOMLine (AD_Client_ID,AD_Org_ID,AD_PInstance_ID,Created,CreatedBy,Implosion,IsActive,LevelNo,Levels,M_Product_ID,PP_Product_BOMLine_ID,PP_Product_BOM_ID,Sel_Product_ID,SeqNo,T_BOMLine_ID,Updated,UpdatedBy) VALUES (11,11,50002,TO_TIMESTAMP('2008-09-22 17:28:43','YYYY-MM-DD HH24:MI:SS'),100,'N','Y',1,'.1',50013,50011,50004,136,6,50027,TO_TIMESTAMP('2008-09-22 17:28:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 22, 2008 5:32:08 PM CDT
-- Manufacturing Demo
DELETE FROM T_BomLine WHERE AD_PInstance_ID = 50002
;

-- Sep 22, 2008 7:18:11 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Category (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,IsDefault,IsSelfService,MMPolicy,M_Product_Category_ID,Name,PlannedMargin,Updated,UpdatedBy,Value) VALUES (11,0,TO_TIMESTAMP('2008-09-22 19:18:00','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','F',50003,'Resource',0,TO_TIMESTAMP('2008-09-22 19:18:00','YYYY-MM-DD HH24:MI:SS'),100,'Resource')
;

-- Sep 22, 2008 7:18:11 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Category_Acct (M_Product_Category_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50003, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM M_Product_Category_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_Category_ID=50003)
;

-- Sep 22, 2008 7:19:10 PM CDT
-- Manufacturing Demo
INSERT INTO S_ResourceType (AD_Client_ID,AD_Org_ID,AllowUoMFractions,C_TaxCategory_ID,C_UOM_ID,ChargeableQty,Created,CreatedBy,IsActive,IsDateSlot,IsSingleAssignment,IsTimeSlot,M_Product_Category_ID,Name,OnFriday,OnMonday,OnSaturday,OnSunday,OnThursday,OnTuesday,OnWednesday,S_ResourceType_ID,Updated,UpdatedBy,Value) VALUES (11,0,'N',107,101,0,TO_TIMESTAMP('2008-09-22 19:19:08','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y','N','N',50003,'Plants','Y','Y','N','Y','Y','Y','Y',50000,TO_TIMESTAMP('2008-09-22 19:19:08','YYYY-MM-DD HH24:MI:SS'),100,'Plants')
;

-- Sep 22, 2008 7:20:22 PM CDT
-- Manufacturing Demo
UPDATE M_Product_Category SET Name='Resources', Value='Resources',Updated=TO_TIMESTAMP('2008-09-22 19:20:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_Category_ID=50003
;

-- Sep 22, 2008 7:20:35 PM CDT
-- Manufacturing Demo
INSERT INTO S_ResourceType (AD_Client_ID,AD_Org_ID,AllowUoMFractions,C_TaxCategory_ID,C_UOM_ID,ChargeableQty,Created,CreatedBy,IsActive,IsDateSlot,IsSingleAssignment,IsTimeSlot,M_Product_Category_ID,Name,OnFriday,OnMonday,OnSaturday,OnSunday,OnThursday,OnTuesday,OnWednesday,S_ResourceType_ID,Updated,UpdatedBy,Value) VALUES (11,0,'N',107,101,0,TO_TIMESTAMP('2008-09-22 19:20:34','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y','N','N',50003,'Work Center','Y','Y','Y','N','Y','Y','Y',50001,TO_TIMESTAMP('2008-09-22 19:20:34','YYYY-MM-DD HH24:MI:SS'),100,'WC')
;

-- Sep 22, 2008 7:21:31 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,IsActive,IsBOM,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,Updated,UpdatedBy,Value) VALUES (11,11,107,101,TO_TIMESTAMP('2008-09-22 19:21:29','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','N','N','Y','Y','Y','N','N','N','N',50003,50018,'Fertilizer Plant','N','R',TO_TIMESTAMP('2008-09-22 19:21:29','YYYY-MM-DD HH24:MI:SS'),100,'Fertilizer Plant')
;

-- Sep 22, 2008 7:21:31 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50018 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 7:21:31 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50018, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50003 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50018)
;

-- Sep 22, 2008 7:21:31 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50018, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50018)
;

-- Sep 22, 2008 7:21:33 PM CDT
-- Manufacturing Demo
INSERT INTO S_Resource (AD_Client_ID,AD_Org_ID,ChargeableQty,Created,CreatedBy,DailyCapacity,IsActive,IsAvailable,IsManufacturingResource,M_Warehouse_ID,ManufacturingResourceType,Name,PercentUtilization,PlanningHorizon,QueuingTime,S_ResourceType_ID,S_Resource_ID,Updated,UpdatedBy,Value,WaitingTime) VALUES (11,0,0,TO_TIMESTAMP('2008-09-22 19:21:29','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y',50002,'PT','Fertilizer Plant',100,90,0,50000,50000,TO_TIMESTAMP('2008-09-22 19:21:29','YYYY-MM-DD HH24:MI:SS'),100,'Fertilizer Plant',0)
;

-- Sep 22, 2008 7:21:33 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', S_Resource_ID=50000,Updated=TO_TIMESTAMP('2008-09-22 19:21:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50018
;

-- Sep 22, 2008 7:26:02 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,IsActive,IsBOM,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,Updated,UpdatedBy,Value) VALUES (11,11,107,101,TO_TIMESTAMP('2008-09-22 19:26:01','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','N','N','Y','Y','Y','N','N','N','N',50003,50019,'Assembly Area','N','R',TO_TIMESTAMP('2008-09-22 19:26:01','YYYY-MM-DD HH24:MI:SS'),100,'Assembly Area')
;

-- Sep 22, 2008 7:26:03 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50019 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 7:26:03 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50019, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50003 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50019)
;

-- Sep 22, 2008 7:26:03 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50019, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50019)
;

-- Sep 22, 2008 7:26:12 PM CDT
-- Manufacturing Demo
INSERT INTO S_Resource (AD_Client_ID,AD_Org_ID,ChargeableQty,Created,CreatedBy,DailyCapacity,IsActive,IsAvailable,IsManufacturingResource,M_Warehouse_ID,ManufacturingResourceType,Name,PercentUtilization,PlanningHorizon,QueuingTime,S_ResourceType_ID,S_Resource_ID,Updated,UpdatedBy,Value,WaitingTime) VALUES (11,50000,0,TO_TIMESTAMP('2008-09-22 19:26:01','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y',50001,'WC','Assembly Area',100,0,0,50001,50001,TO_TIMESTAMP('2008-09-22 19:26:01','YYYY-MM-DD HH24:MI:SS'),100,'Assembly Area',0)
;

-- Sep 22, 2008 7:26:12 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', S_Resource_ID=50001,Updated=TO_TIMESTAMP('2008-09-22 19:26:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50019
;

-- Sep 22, 2008 7:27:12 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,IsActive,IsBOM,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,Updated,UpdatedBy,Value) VALUES (11,11,107,101,TO_TIMESTAMP('2008-09-22 19:27:08','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','N','N','Y','Y','Y','N','N','N','N',50003,50020,'Paint Area','N','R',TO_TIMESTAMP('2008-09-22 19:27:08','YYYY-MM-DD HH24:MI:SS'),100,'Paint Area')
;

-- Sep 22, 2008 7:27:12 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50020 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 7:27:12 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50020, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50003 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50020)
;

-- Sep 22, 2008 7:27:12 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50020, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50020)
;

-- Sep 22, 2008 7:27:23 PM CDT
-- Manufacturing Demo
INSERT INTO S_Resource (AD_Client_ID,AD_Org_ID,ChargeableQty,Created,CreatedBy,DailyCapacity,IsActive,IsAvailable,IsManufacturingResource,M_Warehouse_ID,ManufacturingResourceType,Name,PercentUtilization,PlanningHorizon,QueuingTime,S_ResourceType_ID,S_Resource_ID,Updated,UpdatedBy,Value,WaitingTime) VALUES (11,50000,0,TO_TIMESTAMP('2008-09-22 19:27:08','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y',50001,'WC','Paint Area',100,0,0,50001,50002,TO_TIMESTAMP('2008-09-22 19:27:08','YYYY-MM-DD HH24:MI:SS'),100,'Paint Area',0)
;

-- Sep 22, 2008 7:27:23 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', S_Resource_ID=50002,Updated=TO_TIMESTAMP('2008-09-22 19:27:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50020
;

-- Sep 22, 2008 7:29:10 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,IsActive,IsBOM,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,Updated,UpdatedBy,Value) VALUES (11,11,107,101,TO_TIMESTAMP('2008-09-22 19:29:08','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','N','N','Y','Y','Y','N','N','N','N',50003,50021,'Chrome Subcontract Area','N','R',TO_TIMESTAMP('2008-09-22 19:29:08','YYYY-MM-DD HH24:MI:SS'),100,'Chrome Subcontract Area')
;

-- Sep 22, 2008 7:29:10 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50021 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 7:29:10 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50021, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50003 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50021)
;

-- Sep 22, 2008 7:29:10 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50021, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50021)
;

-- Sep 22, 2008 7:29:12 PM CDT
-- Manufacturing Demo
INSERT INTO S_Resource (AD_Client_ID,AD_Org_ID,ChargeableQty,Created,CreatedBy,DailyCapacity,IsActive,IsAvailable,IsManufacturingResource,M_Warehouse_ID,ManufacturingResourceType,Name,PercentUtilization,PlanningHorizon,QueuingTime,S_ResourceType_ID,S_Resource_ID,Updated,UpdatedBy,Value,WaitingTime) VALUES (11,50000,0,TO_TIMESTAMP('2008-09-22 19:29:08','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y',50001,'WC','Chrome Subcontract Area',100,0,0,50001,50003,TO_TIMESTAMP('2008-09-22 19:29:08','YYYY-MM-DD HH24:MI:SS'),100,'Chrome Subcontract Area',0)
;

-- Sep 22, 2008 7:29:12 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', S_Resource_ID=50003,Updated=TO_TIMESTAMP('2008-09-22 19:29:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50021
;

-- Sep 22, 2008 7:30:03 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,IsActive,IsBOM,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,Updated,UpdatedBy,Value) VALUES (11,11,107,101,TO_TIMESTAMP('2008-09-22 19:30:01','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','N','N','Y','Y','Y','N','N','N','N',50003,50022,'Inspection Area','N','R',TO_TIMESTAMP('2008-09-22 19:30:01','YYYY-MM-DD HH24:MI:SS'),100,'Inspection Area')
;

-- Sep 22, 2008 7:30:03 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50022 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 7:30:03 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50022, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50003 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50022)
;

-- Sep 22, 2008 7:30:03 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50022, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50022)
;

-- Sep 22, 2008 7:30:13 PM CDT
-- Manufacturing Demo
INSERT INTO S_Resource (AD_Client_ID,AD_Org_ID,ChargeableQty,Created,CreatedBy,DailyCapacity,IsActive,IsAvailable,IsManufacturingResource,M_Warehouse_ID,ManufacturingResourceType,Name,PercentUtilization,PlanningHorizon,QueuingTime,S_ResourceType_ID,S_Resource_ID,Updated,UpdatedBy,Value,WaitingTime) VALUES (11,50000,0,TO_TIMESTAMP('2008-09-22 19:30:01','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y',50001,'WC','Inspection Area',100,0,0,50001,50004,TO_TIMESTAMP('2008-09-22 19:30:01','YYYY-MM-DD HH24:MI:SS'),100,'Inspection Area',0)
;

-- Sep 22, 2008 7:30:13 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', S_Resource_ID=50004,Updated=TO_TIMESTAMP('2008-09-22 19:30:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50022
;

-- Sep 22, 2008 7:32:00 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,IsActive,IsBOM,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,Updated,UpdatedBy,Value) VALUES (11,11,107,101,TO_TIMESTAMP('2008-09-22 19:31:59','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','N','N','Y','Y','Y','N','N','N','N',50003,50023,'Furniture Plant','N','R',TO_TIMESTAMP('2008-09-22 19:31:59','YYYY-MM-DD HH24:MI:SS'),100,'Furniture Plant')
;

-- Sep 22, 2008 7:32:00 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50023 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 7:32:00 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50023, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50003 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50023)
;

-- Sep 22, 2008 7:32:00 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50023, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50023)
;

-- Sep 22, 2008 7:32:01 PM CDT
-- Manufacturing Demo
INSERT INTO S_Resource (AD_Client_ID,AD_Org_ID,ChargeableQty,Created,CreatedBy,DailyCapacity,IsActive,IsAvailable,IsManufacturingResource,M_Warehouse_ID,ManufacturingResourceType,Name,PercentUtilization,PlanningHorizon,QueuingTime,S_ResourceType_ID,S_Resource_ID,Updated,UpdatedBy,Value,WaitingTime) VALUES (11,50001,0,TO_TIMESTAMP('2008-09-22 19:31:59','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y',50001,'PT','Furniture Plant',100,0,0,50000,50005,TO_TIMESTAMP('2008-09-22 19:31:59','YYYY-MM-DD HH24:MI:SS'),100,'Furniture Plant',0)
;

-- Sep 22, 2008 7:32:01 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', S_Resource_ID=50005,Updated=TO_TIMESTAMP('2008-09-22 19:32:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50023
;

-- Sep 22, 2008 7:32:44 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,IsActive,IsBOM,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,Updated,UpdatedBy,Value) VALUES (11,11,107,101,TO_TIMESTAMP('2008-09-22 19:32:43','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','N','N','Y','Y','Y','N','N','N','N',50003,50024,'Packing Production Line','N','R',TO_TIMESTAMP('2008-09-22 19:32:43','YYYY-MM-DD HH24:MI:SS'),100,'Packing Production Line')
;

-- Sep 22, 2008 7:32:44 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50024 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 7:32:44 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50024, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50003 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50024)
;

-- Sep 22, 2008 7:32:44 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50024, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50024)
;

-- Sep 22, 2008 7:32:45 PM CDT
-- Manufacturing Demo
INSERT INTO S_Resource (AD_Client_ID,AD_Org_ID,ChargeableQty,Created,CreatedBy,DailyCapacity,IsActive,IsAvailable,IsManufacturingResource,M_Warehouse_ID,ManufacturingResourceType,Name,PercentUtilization,PlanningHorizon,QueuingTime,S_ResourceType_ID,S_Resource_ID,Updated,UpdatedBy,Value,WaitingTime) VALUES (11,50001,0,TO_TIMESTAMP('2008-09-22 19:32:43','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y',50002,'PL','Packing Production Line',100,0,0,50001,50006,TO_TIMESTAMP('2008-09-22 19:32:43','YYYY-MM-DD HH24:MI:SS'),100,'Packing Production Line',0)
;

-- Sep 22, 2008 7:32:45 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', S_Resource_ID=50006,Updated=TO_TIMESTAMP('2008-09-22 19:32:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50024
;

-- Sep 22, 2008 7:33:31 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,IsActive,IsBOM,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,Updated,UpdatedBy,Value) VALUES (11,11,107,101,TO_TIMESTAMP('2008-09-22 19:33:31','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','N','N','Y','Y','Y','N','N','N','N',50003,50025,'Mixed Area','N','R',TO_TIMESTAMP('2008-09-22 19:33:31','YYYY-MM-DD HH24:MI:SS'),100,'Mixed Area')
;

-- Sep 22, 2008 7:33:31 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50025 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 7:33:32 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50025, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50003 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50025)
;

-- Sep 22, 2008 7:33:32 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50025, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50025)
;

-- Sep 22, 2008 7:33:32 PM CDT
-- Manufacturing Demo
INSERT INTO S_Resource (AD_Client_ID,AD_Org_ID,ChargeableQty,Created,CreatedBy,DailyCapacity,IsActive,IsAvailable,IsManufacturingResource,M_Warehouse_ID,ManufacturingResourceType,Name,PercentUtilization,PlanningHorizon,QueuingTime,S_ResourceType_ID,S_Resource_ID,Updated,UpdatedBy,Value,WaitingTime) VALUES (11,50001,0,TO_TIMESTAMP('2008-09-22 19:33:31','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y',50002,'WC','Mixed Area',100,0,0,50001,50007,TO_TIMESTAMP('2008-09-22 19:33:31','YYYY-MM-DD HH24:MI:SS'),100,'Mixed Area',0)
;

-- Sep 22, 2008 7:33:32 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', S_Resource_ID=50007,Updated=TO_TIMESTAMP('2008-09-22 19:33:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50025
;

-- Sep 22, 2008 7:34:27 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,IsActive,IsBOM,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,Updated,UpdatedBy,Value) VALUES (11,11,107,101,TO_TIMESTAMP('2008-09-22 19:34:25','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','N','N','Y','Y','Y','N','N','N','N',50003,50026,'Dry Area','N','R',TO_TIMESTAMP('2008-09-22 19:34:25','YYYY-MM-DD HH24:MI:SS'),100,'Dry Area')
;

-- Sep 22, 2008 7:34:27 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50026 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 7:34:27 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50026, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50003 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50026)
;

-- Sep 22, 2008 7:34:27 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50026, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50026)
;

-- Sep 22, 2008 7:34:28 PM CDT
-- Manufacturing Demo
INSERT INTO S_Resource (AD_Client_ID,AD_Org_ID,ChargeableQty,Created,CreatedBy,DailyCapacity,IsActive,IsAvailable,IsManufacturingResource,M_Warehouse_ID,ManufacturingResourceType,Name,PercentUtilization,PlanningHorizon,QueuingTime,S_ResourceType_ID,S_Resource_ID,Updated,UpdatedBy,Value,WaitingTime) VALUES (11,50001,0,TO_TIMESTAMP('2008-09-22 19:34:25','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y',50002,'WC','Dry Area',100,0,0,50001,50008,TO_TIMESTAMP('2008-09-22 19:34:25','YYYY-MM-DD HH24:MI:SS'),100,'Dry Area',0)
;

-- Sep 22, 2008 7:34:28 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', S_Resource_ID=50008,Updated=TO_TIMESTAMP('2008-09-22 19:34:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50026
;

-- Sep 22, 2008 7:35:33 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product (AD_Client_ID,AD_Org_ID,C_TaxCategory_ID,C_UOM_ID,Created,CreatedBy,IsActive,IsBOM,IsExcludeAutoDelivery,IsInvoicePrintDetails,IsPickListPrintDetails,IsPurchased,IsSelfService,IsSold,IsStocked,IsSummary,IsVerified,IsWebStoreFeatured,M_Product_Category_ID,M_Product_ID,Name,Processing,ProductType,Updated,UpdatedBy,Value) VALUES (11,11,107,101,TO_TIMESTAMP('2008-09-22 19:35:32','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','N','N','Y','Y','Y','N','N','N','N',50003,50027,'Fertilizer Inspection Area','N','R',TO_TIMESTAMP('2008-09-22 19:35:32','YYYY-MM-DD HH24:MI:SS'),100,'Fertilizer Inspection Area')
;

-- Sep 22, 2008 7:35:33 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Trl (AD_Language,M_Product_ID, Description,DocumentNote,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.M_Product_ID, t.Description,t.DocumentNote,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, M_Product t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.M_Product_ID=50027 AND EXISTS (SELECT * FROM M_Product_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.M_Product_ID!=t.M_Product_ID)
;

-- Sep 22, 2008 7:35:33 PM CDT
-- Manufacturing Demo
INSERT INTO M_Product_Acct (M_Product_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,P_Asset_Acct,P_COGS_Acct,P_CostAdjustment_Acct,P_Expense_Acct,P_InventoryClearing_Acct,P_InvoicePriceVariance_Acct,P_PurchasePriceVariance_Acct,P_Revenue_Acct,P_TradeDiscountGrant_Acct,P_TradeDiscountRec_Acct) SELECT 50027, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.P_Asset_Acct,p.P_COGS_Acct,p.P_CostAdjustment_Acct,p.P_Expense_Acct,p.P_InventoryClearing_Acct,p.P_InvoicePriceVariance_Acct,p.P_PurchasePriceVariance_Acct,p.P_Revenue_Acct,p.P_TradeDiscountGrant_Acct,p.P_TradeDiscountRec_Acct FROM M_Product_Category_Acct p WHERE p.AD_Client_ID=11 AND p.M_Product_Category_ID=50003 AND NOT EXISTS (SELECT * FROM M_Product_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Product_ID=50027)
;

-- Sep 22, 2008 7:35:33 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodePR (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50027, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='PR' AND NOT EXISTS (SELECT * FROM AD_TreeNodePR e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50027)
;

-- Sep 22, 2008 7:35:34 PM CDT
-- Manufacturing Demo
INSERT INTO S_Resource (AD_Client_ID,AD_Org_ID,ChargeableQty,Created,CreatedBy,DailyCapacity,IsActive,IsAvailable,IsManufacturingResource,M_Warehouse_ID,ManufacturingResourceType,Name,PercentUtilization,PlanningHorizon,QueuingTime,S_ResourceType_ID,S_Resource_ID,Updated,UpdatedBy,Value,WaitingTime) VALUES (11,50001,0,TO_TIMESTAMP('2008-09-22 19:35:32','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y',50002,'WC','Fertilizer Inspection Area',100,0,0,50001,50009,TO_TIMESTAMP('2008-09-22 19:35:32','YYYY-MM-DD HH24:MI:SS'),100,'Fertilizer Inspection Area',0)
;

-- Sep 22, 2008 7:35:34 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', S_Resource_ID=50009,Updated=TO_TIMESTAMP('2008-09-22 19:35:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50027
;

-- Sep 23, 2008 1:47:31 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Workflow (AD_Client_ID,AD_Org_ID,AD_WF_Responsible_ID,AD_Workflow_ID,AccessLevel,Author,Cost,Created,CreatedBy,DocumentNo,Duration,DurationUnit,EntityType,IsActive,IsDefault,IsValid,"limit",MovingTime,Name,Priority,ProcessType,PublishStatus,QtyBatchSize,QueuingTime,SetupTime,Updated,UpdatedBy,ValidateWorkflow,Value,Version,WaitingTime,WorkflowType,WorkingTime) VALUES (11,0,100,50015,'7','Victor Perez',0,TO_TIMESTAMP('2008-09-23 13:47:18','YYYY-MM-DD HH24:MI:SS'),100,'10000000',0,'m','EE01','Y','N','N',0,0,'Patio Chair',0,'BF','R',1.000000000000,0,0,TO_TIMESTAMP('2008-09-23 13:47:18','YYYY-MM-DD HH24:MI:SS'),100,'N','PChair',0,0,'M',0)
;

-- Sep 23, 2008 1:47:32 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Workflow_Trl (AD_Language,AD_Workflow_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Workflow_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Workflow t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Workflow_ID=50015 AND EXISTS (SELECT * FROM AD_Workflow_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Workflow_ID!=t.AD_Workflow_ID)
;

-- Sep 23, 2008 1:47:37 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Workflow_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (11,0,102,50015,TO_TIMESTAMP('2008-09-23 13:47:37','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-09-23 13:47:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 2:05:08 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,"action",Cost,Created,CreatedBy,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,IsMilestone,IsSubcontracting,JoinElement,"limit",MovingTime,Name,OverlapUnits,Priority,QueuingTime,S_Resource_ID,SetupTime,SplitElement,UnitsCycles,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (11,0,50085,50015,'D',0,TO_TIMESTAMP('2008-09-23 14:05:07','YYYY-MM-DD HH24:MI:SS'),100,'CO',40,0,'EE01','Y','Y','N','N','X',0,0,'Assembly',0,0,0,50001,20,'X',0,TO_TIMESTAMP('2008-09-23 14:05:07','YYYY-MM-DD HH24:MI:SS'),100,'10',0,0,0,0,0)
;

-- Sep 23, 2008 2:05:08 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50085 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Sep 23, 2008 2:07:01 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,"action",Cost,Created,CreatedBy,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,IsMilestone,IsSubcontracting,JoinElement,"limit",MovingTime,Name,OverlapUnits,Priority,QueuingTime,S_Resource_ID,SetupTime,SplitElement,UnitsCycles,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (11,0,50086,50015,'D',0,TO_TIMESTAMP('2008-09-23 14:06:55','YYYY-MM-DD HH24:MI:SS'),100,'CO',15,0,'EE01','Y','Y','N','N','X',0,0,'Paint',0,0,0,50002,10,'X',0,TO_TIMESTAMP('2008-09-23 14:06:55','YYYY-MM-DD HH24:MI:SS'),100,'20',0,0,0,0,0)
;

-- Sep 23, 2008 2:07:01 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50086 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Sep 23, 2008 2:08:14 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,"action",Cost,Created,CreatedBy,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,IsMilestone,IsSubcontracting,JoinElement,"limit",MovingTime,Name,OverlapUnits,Priority,QueuingTime,S_Resource_ID,SetupTime,SplitElement,UnitsCycles,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (11,0,50087,50015,'D',0,TO_TIMESTAMP('2008-09-23 14:08:14','YYYY-MM-DD HH24:MI:SS'),100,'CO',0,0,'EE01','Y','Y','N','N','X',0,0,'Dry',0,0,0,50008,0,'X',0,TO_TIMESTAMP('2008-09-23 14:08:14','YYYY-MM-DD HH24:MI:SS'),100,'30',0,60,0,0,0)
;

-- Sep 23, 2008 2:08:14 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50087 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Sep 23, 2008 2:10:44 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,"action",Cost,Created,CreatedBy,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,IsMilestone,IsSubcontracting,JoinElement,"limit",MovingTime,Name,OverlapUnits,Priority,QueuingTime,S_Resource_ID,SetupTime,SplitElement,UnitsCycles,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (11,0,50088,50015,'D',0,TO_TIMESTAMP('2008-09-23 14:10:38','YYYY-MM-DD HH24:MI:SS'),100,'CO',0,0,'EE01','Y','Y','N','Y','X',0,0,'Chrome',0,0,0,50003,0,'X',0,TO_TIMESTAMP('2008-09-23 14:10:38','YYYY-MM-DD HH24:MI:SS'),100,'40',0,24,0,0,0)
;

-- Sep 23, 2008 2:10:44 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50088 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Sep 23, 2008 2:12:40 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,"action",Cost,Created,CreatedBy,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,IsMilestone,IsSubcontracting,JoinElement,"limit",MovingTime,Name,OverlapUnits,Priority,QueuingTime,S_Resource_ID,SetupTime,SplitElement,UnitsCycles,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (11,0,50089,50015,'D',0,TO_TIMESTAMP('2008-09-23 14:12:32','YYYY-MM-DD HH24:MI:SS'),100,'CO',10,0,'EE01','Y','Y','N','Y','X',0,0,'Inspection',0,0,0,50004,0,'X',0,TO_TIMESTAMP('2008-09-23 14:12:32','YYYY-MM-DD HH24:MI:SS'),100,'50',0,0,0,0,0)
;

-- Sep 23, 2008 2:12:40 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50089 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Sep 23, 2008 2:13:17 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (11,0,50086,50067,50085,TO_TIMESTAMP('2008-09-23 14:13:09','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','N',10,TO_TIMESTAMP('2008-09-23 14:13:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 2:13:47 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (11,0,50087,50068,50086,TO_TIMESTAMP('2008-09-23 14:13:37','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','N',10,TO_TIMESTAMP('2008-09-23 14:13:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 2:14:06 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (11,0,50088,50069,50087,TO_TIMESTAMP('2008-09-23 14:14:05','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','N',10,TO_TIMESTAMP('2008-09-23 14:14:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 2:14:50 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (11,0,50089,50070,50088,TO_TIMESTAMP('2008-09-23 14:14:46','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','N',10,TO_TIMESTAMP('2008-09-23 14:14:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 2:15:03 PM CDT
-- Manufacturing Demo
UPDATE AD_Workflow SET AD_WF_Node_ID=50085, IsValid='Y',Updated=TO_TIMESTAMP('2008-09-23 14:15:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Workflow_ID=50015
;

-- Sep 23, 2008 2:31:29 PM CDT
-- Manufacturing Demo
UPDATE AD_Workflow SET Description='Routing for Assembly the Patio Chair', IsValid='Y',Updated=TO_TIMESTAMP('2008-09-23 14:31:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Workflow_ID=50015
;

-- Sep 23, 2008 2:31:29 PM CDT
-- Manufacturing Demo
UPDATE AD_Workflow_Trl SET Description='Routing for Assembly the Patio Chair',Help=NULL,Name='Patio Chair',IsTranslated='Y' WHERE AD_Workflow_ID=50015
;

-- Sep 23, 2008 2:31:30 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node SET Description='Routing for Assembly the Patio Chair', Help=NULL, Name='Patio Chair',Updated=TO_TIMESTAMP('2008-09-23 14:31:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=50085
;

-- Sep 23, 2008 2:31:30 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node_Trl SET Description='Routing for Assembly the Patio Chair',Help=NULL,Name='Patio Chair',IsTranslated='Y' WHERE AD_WF_Node_ID=50085
;

-- Sep 23, 2008 2:31:30 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node SET Description='Routing for Assembly the Patio Chair', Help=NULL, Name='Patio Chair',Updated=TO_TIMESTAMP('2008-09-23 14:31:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=50086
;

-- Sep 23, 2008 2:31:30 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node_Trl SET Description='Routing for Assembly the Patio Chair',Help=NULL,Name='Patio Chair',IsTranslated='Y' WHERE AD_WF_Node_ID=50086
;

-- Sep 23, 2008 2:31:30 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node SET Description='Routing for Assembly the Patio Chair', Help=NULL, Name='Patio Chair',Updated=TO_TIMESTAMP('2008-09-23 14:31:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=50087
;

-- Sep 23, 2008 2:31:30 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node_Trl SET Description='Routing for Assembly the Patio Chair',Help=NULL,Name='Patio Chair',IsTranslated='Y' WHERE AD_WF_Node_ID=50087
;

-- Sep 23, 2008 2:31:30 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node SET Description='Routing for Assembly the Patio Chair', Help=NULL, Name='Patio Chair',Updated=TO_TIMESTAMP('2008-09-23 14:31:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=50088
;

-- Sep 23, 2008 2:31:30 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node_Trl SET Description='Routing for Assembly the Patio Chair',Help=NULL,Name='Patio Chair',IsTranslated='Y' WHERE AD_WF_Node_ID=50088
;

-- Sep 23, 2008 2:31:30 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node SET Description='Routing for Assembly the Patio Chair', Help=NULL, Name='Patio Chair',Updated=TO_TIMESTAMP('2008-09-23 14:31:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=50089
;

-- Sep 23, 2008 2:31:30 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node_Trl SET Description='Routing for Assembly the Patio Chair',Help=NULL,Name='Patio Chair',IsTranslated='Y' WHERE AD_WF_Node_ID=50089
;

-- Sep 23, 2008 2:32:49 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node SET Description='Assembly the Patio Chair',Updated=TO_TIMESTAMP('2008-09-23 14:32:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=50085
;

-- Sep 23, 2008 2:32:49 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node_Trl SET Description='Assembly the Patio Chair',Help=NULL,Name='Patio Chair',IsTranslated='Y' WHERE AD_WF_Node_ID=50085
;

-- Sep 23, 2008 2:40:42 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node SET Description='Assembly the Chiar ', Help='1.- Apply Glue and Join the kit Leg back 
2.- Apply Glue and Join the kit Leg front
3.- Apply Glue and Join the Seat
4.- Apply Glue and Join the Back Support
5.- Fixing screws
', Name='Assembly',Updated=TO_TIMESTAMP('2008-09-23 14:40:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=50085
;

-- Sep 23, 2008 2:40:42 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node_Trl SET Description='Assembly the Chiar ',Help='1.- Apply Glue and Join the kit Leg back 
2.- Apply Glue and Join the kit Leg front
3.- Apply Glue and Join the Seat
4.- Apply Glue and Join the Back Support
5.- Fixing screws
',Name='Assembly',IsTranslated='Y' WHERE AD_WF_Node_ID=50085
;

-- Sep 23, 2008 2:41:33 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node SET Description='Painting Chair', Help='1.- Setup paint color
2.- Painting', Name='Paint',Updated=TO_TIMESTAMP('2008-09-23 14:41:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=50086
;

-- Sep 23, 2008 2:41:33 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node_Trl SET Description='Painting Chair',Help='1.- Setup paint color
2.- Painting',Name='Paint',IsTranslated='Y' WHERE AD_WF_Node_ID=50086
;

-- Sep 23, 2008 2:44:40 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node SET Description='Drying', Help='1.- Wait to Drying for 60 minutes', Name='Drying',Updated=TO_TIMESTAMP('2008-09-23 14:44:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=50087
;

-- Sep 23, 2008 2:44:40 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node_Trl SET Description='Drying',Help='1.- Wait to Drying for 60 minutes',Name='Drying',IsTranslated='Y' WHERE AD_WF_Node_ID=50087
;

-- Sep 23, 2008 2:46:25 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node SET Description='This activity is external process', Help='Please you need create the Subcontract Purchase Order', Name='Chrome Process',Updated=TO_TIMESTAMP('2008-09-23 14:46:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=50088
;

-- Sep 23, 2008 2:46:25 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node_Trl SET Description='This activity is external process',Help='Please you need create the Subcontract Purchase Order',Name='Chrome Process',IsTranslated='Y' WHERE AD_WF_Node_ID=50088
;

-- Sep 23, 2008 2:49:13 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node SET Description=NULL, Help='The inspection need get 99% the quality in next process:

Assembly
Paint
Chrome
', Name='Inspection Process',Updated=TO_TIMESTAMP('2008-09-23 14:49:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=50089
;

-- Sep 23, 2008 2:49:13 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node_Trl SET Description=NULL,Help='The inspection need get 99% the quality in next process:

Assembly
Paint
Chrome
',Name='Inspection Process',IsTranslated='Y' WHERE AD_WF_Node_ID=50089
;

-- Sep 23, 2008 2:57:09 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Workflow (AD_Client_ID,AD_Org_ID,AD_Workflow_ID,AccessLevel,Author,Cost,Created,CreatedBy,DocumentNo,Duration,DurationUnit,EntityType,IsActive,IsDefault,IsValid,"limit",MovingTime,Name,Priority,ProcessType,PublishStatus,QtyBatchSize,QueuingTime,SetupTime,Updated,UpdatedBy,ValidateWorkflow,Value,Version,WaitingTime,WorkflowType,WorkingTime) VALUES (11,0,50016,'7','Victor Perez',0,TO_TIMESTAMP('2008-09-23 14:57:00','YYYY-MM-DD HH24:MI:SS'),100,'10000001',0,'D','U','Y','N','N',0,0,'Assembly Back Leg',0,'BF','R',1,0,0,TO_TIMESTAMP('2008-09-23 14:57:00','YYYY-MM-DD HH24:MI:SS'),100,'N','PBackLeg',0,0,'M',0)
;

-- Sep 23, 2008 2:57:09 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Workflow_Trl (AD_Language,AD_Workflow_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Workflow_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Workflow t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Workflow_ID=50016 AND EXISTS (SELECT * FROM AD_Workflow_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Workflow_ID!=t.AD_Workflow_ID)
;

-- Sep 23, 2008 2:57:09 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Workflow_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (11,0,102,50016,TO_TIMESTAMP('2008-09-23 14:57:09','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-09-23 14:57:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 2:58:55 PM CDT
-- Manufacturing Demo
UPDATE AD_Workflow SET IsValid='N', Name='Lawn Fertilizer', Value='Fertilizer',Updated=TO_TIMESTAMP('2008-09-23 14:58:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Workflow_ID=50016
;

-- Sep 23, 2008 2:58:55 PM CDT
-- Manufacturing Demo
UPDATE AD_Workflow_Trl SET Description=NULL,Help=NULL,Name='Lawn Fertilizer',IsTranslated='Y' WHERE AD_Workflow_ID=50016
;

-- Sep 23, 2008 3:03:18 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,"action",Cost,Created,CreatedBy,DocAction,Duration,DynPriorityChange,EntityType,Help,IsActive,IsCentrallyMaintained,IsMilestone,IsSubcontracting,JoinElement,"limit",MovingTime,Name,OverlapUnits,Priority,QueuingTime,S_Resource_ID,SetupTime,SplitElement,UnitsCycles,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (11,0,50090,50016,'D',0,TO_TIMESTAMP('2008-09-23 15:03:17','YYYY-MM-DD HH24:MI:SS'),100,'CO',30,0,'EE01','Mixed the 17% of Nitrogen, 17% of Phosphorus , 17% of Potassium and Whater','Y','Y','N','N','X',0,0,'Mixed',0,0,0,50007,15,'X',0,TO_TIMESTAMP('2008-09-23 15:03:17','YYYY-MM-DD HH24:MI:SS'),100,'10',0,15,0,0,0)
;

-- Sep 23, 2008 3:03:18 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50090 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Sep 23, 2008 3:03:36 PM CDT
-- Manufacturing Demo
UPDATE AD_Workflow SET DurationUnit='m', EntityType='EE01', IsValid='N', QtyBatchSize=500.000000000000,Updated=TO_TIMESTAMP('2008-09-23 15:03:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Workflow_ID=50016
;

-- Sep 23, 2008 3:04:39 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,"action",Cost,Created,CreatedBy,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,IsMilestone,IsSubcontracting,JoinElement,"limit",MovingTime,Name,OverlapUnits,Priority,QueuingTime,S_Resource_ID,SetupTime,SplitElement,UnitsCycles,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (11,0,50091,50016,'D',0,TO_TIMESTAMP('2008-09-23 15:04:36','YYYY-MM-DD HH24:MI:SS'),100,'CO',10,0,'EE01','Y','Y','N','N','X',0,0,'Inspection the Mixed',0,0,0,50009,0,'X',0,TO_TIMESTAMP('2008-09-23 15:04:36','YYYY-MM-DD HH24:MI:SS'),100,'20',0,0,0,0,0)
;

-- Sep 23, 2008 3:04:39 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50091 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Sep 23, 2008 3:04:57 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (11,0,50091,50071,50090,TO_TIMESTAMP('2008-09-23 15:04:56','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','N',10,TO_TIMESTAMP('2008-09-23 15:04:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:05:06 PM CDT
-- Manufacturing Demo
UPDATE AD_Workflow SET AD_WF_Node_ID=50090, IsValid='Y',Updated=TO_TIMESTAMP('2008-09-23 15:05:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Workflow_ID=50016
;

-- Sep 23, 2008 3:10:47 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Workflow (AD_Client_ID,AD_Org_ID,AD_Workflow_ID,AccessLevel,Author,Cost,Created,CreatedBy,Description,DocumentNo,Duration,DurationUnit,EntityType,IsActive,IsDefault,IsValid,"limit",MovingTime,Name,Priority,ProcessType,PublishStatus,QtyBatchSize,QueuingTime,S_Resource_ID,SetupTime,Updated,UpdatedBy,ValidateWorkflow,Value,Version,WaitingTime,WorkflowType,WorkingTime) VALUES (11,0,50017,'7','Victor Perez',0,TO_TIMESTAMP('2008-09-23 15:10:45','YYYY-MM-DD HH24:MI:SS'),100,'Production Line to Packing the Fertilizer Bag 50 and 70 kg ','10000002',0,'m','EE01','Y','N','N',0,0,'Fertilizer Packing Process',0,'DR','R',200.000000000000,0,50006,0,TO_TIMESTAMP('2008-09-23 15:10:45','YYYY-MM-DD HH24:MI:SS'),100,'N','Fertilizer Packing ',0,0,'M',0)
;

-- Sep 23, 2008 3:10:47 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Workflow_Trl (AD_Language,AD_Workflow_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Workflow_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Workflow t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Workflow_ID=50017 AND EXISTS (SELECT * FROM AD_Workflow_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Workflow_ID!=t.AD_Workflow_ID)
;

-- Sep 23, 2008 3:10:47 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Workflow_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (11,0,102,50017,TO_TIMESTAMP('2008-09-23 15:10:47','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-09-23 15:10:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:11:35 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,"action",Cost,Created,CreatedBy,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,IsMilestone,IsSubcontracting,JoinElement,"limit",MovingTime,Name,OverlapUnits,Priority,QueuingTime,SetupTime,SplitElement,UnitsCycles,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (11,0,50092,50017,'D',0,TO_TIMESTAMP('2008-09-23 15:11:31','YYYY-MM-DD HH24:MI:SS'),100,'CO',0,0,'EE01','Y','Y','N','N','X',0,0,'Packing Process',0,0,0,0,'X',200.000000000000,TO_TIMESTAMP('2008-09-23 15:11:31','YYYY-MM-DD HH24:MI:SS'),100,'10',0,0,0,0,0)
;

-- Sep 23, 2008 3:11:35 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50092 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Sep 23, 2008 3:11:44 PM CDT
-- Manufacturing Demo
UPDATE AD_Workflow SET DurationUnit='s', IsValid='N',Updated=TO_TIMESTAMP('2008-09-23 15:11:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Workflow_ID=50017
;

-- Sep 23, 2008 3:12:16 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node SET Duration=3, MovingTime=2, WaitingTime=1,Updated=TO_TIMESTAMP('2008-09-23 15:12:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=50092
;

-- Sep 23, 2008 3:13:25 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node SET Description='Filling and Sealing bag', S_Resource_ID=50006,Updated=TO_TIMESTAMP('2008-09-23 15:13:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Node_ID=50092
;

-- Sep 23, 2008 3:13:25 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Node_Trl SET Description='Filling and Sealing bag',Help=NULL,Name='Packing Process',IsTranslated='Y' WHERE AD_WF_Node_ID=50092
;

-- Sep 23, 2008 3:13:30 PM CDT
-- Manufacturing Demo
UPDATE AD_Workflow SET AD_WF_Node_ID=50092, IsValid='Y',Updated=TO_TIMESTAMP('2008-09-23 15:13:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Workflow_ID=50017
;

-- Sep 23, 2008 3:19:01 PM CDT
-- Manufacturing Demo
INSERT INTO GL_Category (AD_Client_ID,AD_Org_ID,CategoryType,Created,CreatedBy,GL_Category_ID,IsActive,IsDefault,Name,Updated,UpdatedBy) VALUES (11,11,'D',TO_TIMESTAMP('2008-09-23 15:19:01','YYYY-MM-DD HH24:MI:SS'),100,50001,'Y','N','Manufactuing',TO_TIMESTAMP('2008-09-23 15:19:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:02 PM CDT
-- Manufacturing Demo
INSERT INTO GL_Category (AD_Client_ID,AD_Org_ID,CategoryType,Created,CreatedBy,GL_Category_ID,IsActive,IsDefault,Name,Updated,UpdatedBy) VALUES (11,11,'D',TO_TIMESTAMP('2008-09-23 15:19:02','YYYY-MM-DD HH24:MI:SS'),100,50002,'Y','N','Distribution',TO_TIMESTAMP('2008-09-23 15:19:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:02 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53169,TO_TIMESTAMP('2008-09-23 15:19:02','YYYY-MM-DD HH24:MI:SS'),100,80000,8000,'Manufacturing Order',1,'Y','N','Y','N','Manufacturing Order','N',80000,TO_TIMESTAMP('2008-09-23 15:19:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:03 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType (AD_Client_ID,AD_Org_ID,C_DocType_ID,Created,CreatedBy,DocBaseType,DocNoSequence_ID,DocumentCopies,GL_Category_ID,HasCharges,IsActive,IsCreateCounter,IsDefault,IsDefaultCounterDoc,IsDocNoControlled,IsIndexed,IsPickQAConfirm,IsSOTrx,IsShipConfirm,IsSplitWhenDifference,Name,PrintName,Updated,UpdatedBy) VALUES (11,0,50002,TO_TIMESTAMP('2008-09-23 15:19:03','YYYY-MM-DD HH24:MI:SS'),100,'MOP',53169,0,50001,'N','Y','Y','N','N','Y','Y','N','N','N','N','Manufacturing Order','Manufacturing Order',TO_TIMESTAMP('2008-09-23 15:19:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:03 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType_Trl (AD_Language,C_DocType_ID, DocumentNote,Name,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_DocType_ID, t.DocumentNote,t.Name,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_DocType t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_DocType_ID=50002 AND EXISTS (SELECT * FROM C_DocType_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_DocType_ID!=t.C_DocType_ID)
;

-- Sep 23, 2008 3:19:03 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) WHERE client.AD_Client_ID=11 AND doctype.C_DocType_ID=50002 AND rol.IsManual='N')
;

-- Sep 23, 2008 3:19:04 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53170,TO_TIMESTAMP('2008-09-23 15:19:03','YYYY-MM-DD HH24:MI:SS'),100,81000,8100,'Manufacturing Order Planning',1,'Y','N','Y','N','Manufacturing Order Planning','N',81000,TO_TIMESTAMP('2008-09-23 15:19:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:05 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType (AD_Client_ID,AD_Org_ID,C_DocType_ID,Created,CreatedBy,DocBaseType,DocNoSequence_ID,DocumentCopies,GL_Category_ID,HasCharges,IsActive,IsCreateCounter,IsDefault,IsDefaultCounterDoc,IsDocNoControlled,IsIndexed,IsPickQAConfirm,IsSOTrx,IsShipConfirm,IsSplitWhenDifference,Name,PrintName,Updated,UpdatedBy) VALUES (11,0,50003,TO_TIMESTAMP('2008-09-23 15:19:04','YYYY-MM-DD HH24:MI:SS'),100,'MOP',53170,0,50001,'N','Y','Y','N','N','Y','Y','N','N','N','N','Manufacturing Order Planning','Manufacturing Order Planning',TO_TIMESTAMP('2008-09-23 15:19:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:05 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType_Trl (AD_Language,C_DocType_ID, DocumentNote,Name,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_DocType_ID, t.DocumentNote,t.Name,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_DocType t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_DocType_ID=50003 AND EXISTS (SELECT * FROM C_DocType_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_DocType_ID!=t.C_DocType_ID)
;

-- Sep 23, 2008 3:19:05 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) WHERE client.AD_Client_ID=11 AND doctype.C_DocType_ID=50003 AND rol.IsManual='N')
;

-- Sep 23, 2008 3:19:05 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53171,TO_TIMESTAMP('2008-09-23 15:19:05','YYYY-MM-DD HH24:MI:SS'),100,82000,8200,'Manufacturing Order Receipt',1,'Y','N','Y','N','Manufacturing Order Receipt','N',82000,TO_TIMESTAMP('2008-09-23 15:19:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:05 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType (AD_Client_ID,AD_Org_ID,C_DocType_ID,Created,CreatedBy,DocBaseType,DocNoSequence_ID,DocumentCopies,GL_Category_ID,HasCharges,IsActive,IsCreateCounter,IsDefault,IsDefaultCounterDoc,IsDocNoControlled,IsIndexed,IsPickQAConfirm,IsSOTrx,IsShipConfirm,IsSplitWhenDifference,Name,PrintName,Updated,UpdatedBy) VALUES (11,0,50004,TO_TIMESTAMP('2008-09-23 15:19:05','YYYY-MM-DD HH24:MI:SS'),100,'MOR',53171,0,50001,'N','Y','Y','N','N','Y','Y','N','N','N','N','Manufacturing Order Receipt','Manufacturing Order Receipt',TO_TIMESTAMP('2008-09-23 15:19:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:06 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType_Trl (AD_Language,C_DocType_ID, DocumentNote,Name,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_DocType_ID, t.DocumentNote,t.Name,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_DocType t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_DocType_ID=50004 AND EXISTS (SELECT * FROM C_DocType_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_DocType_ID!=t.C_DocType_ID)
;

-- Sep 23, 2008 3:19:06 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) WHERE client.AD_Client_ID=11 AND doctype.C_DocType_ID=50004 AND rol.IsManual='N')
;

-- Sep 23, 2008 3:19:06 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53172,TO_TIMESTAMP('2008-09-23 15:19:06','YYYY-MM-DD HH24:MI:SS'),100,83000,8300,'Manufacturing Order Issue',1,'Y','N','Y','N','Manufacturing Order Issue','N',83000,TO_TIMESTAMP('2008-09-23 15:19:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:10 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType (AD_Client_ID,AD_Org_ID,C_DocType_ID,Created,CreatedBy,DocBaseType,DocNoSequence_ID,DocumentCopies,GL_Category_ID,HasCharges,IsActive,IsCreateCounter,IsDefault,IsDefaultCounterDoc,IsDocNoControlled,IsIndexed,IsPickQAConfirm,IsSOTrx,IsShipConfirm,IsSplitWhenDifference,Name,PrintName,Updated,UpdatedBy) VALUES (11,0,50005,TO_TIMESTAMP('2008-09-23 15:19:06','YYYY-MM-DD HH24:MI:SS'),100,'MOI',53172,0,50001,'N','Y','Y','N','N','Y','Y','N','N','N','N','Manufacturing Order Issue','Manufacturing Order Issue',TO_TIMESTAMP('2008-09-23 15:19:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:10 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType_Trl (AD_Language,C_DocType_ID, DocumentNote,Name,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_DocType_ID, t.DocumentNote,t.Name,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_DocType t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_DocType_ID=50005 AND EXISTS (SELECT * FROM C_DocType_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_DocType_ID!=t.C_DocType_ID)
;

-- Sep 23, 2008 3:19:10 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) WHERE client.AD_Client_ID=11 AND doctype.C_DocType_ID=50005 AND rol.IsManual='N')
;

-- Sep 23, 2008 3:19:11 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53173,TO_TIMESTAMP('2008-09-23 15:19:10','YYYY-MM-DD HH24:MI:SS'),100,84000,8400,'Manufacturing Order Method Variance',1,'Y','N','Y','N','Manufacturing Order Method Variance','N',84000,TO_TIMESTAMP('2008-09-23 15:19:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:12 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType (AD_Client_ID,AD_Org_ID,C_DocType_ID,Created,CreatedBy,DocBaseType,DocNoSequence_ID,DocumentCopies,GL_Category_ID,HasCharges,IsActive,IsCreateCounter,IsDefault,IsDefaultCounterDoc,IsDocNoControlled,IsIndexed,IsPickQAConfirm,IsSOTrx,IsShipConfirm,IsSplitWhenDifference,Name,PrintName,Updated,UpdatedBy) VALUES (11,0,50006,TO_TIMESTAMP('2008-09-23 15:19:11','YYYY-MM-DD HH24:MI:SS'),100,'MOM',53173,0,50001,'N','Y','Y','N','N','Y','Y','N','N','N','N','Manufacturing Order Method Variance','Manufacturing Order Method Variance',TO_TIMESTAMP('2008-09-23 15:19:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:12 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType_Trl (AD_Language,C_DocType_ID, DocumentNote,Name,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_DocType_ID, t.DocumentNote,t.Name,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_DocType t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_DocType_ID=50006 AND EXISTS (SELECT * FROM C_DocType_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_DocType_ID!=t.C_DocType_ID)
;

-- Sep 23, 2008 3:19:12 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) WHERE client.AD_Client_ID=11 AND doctype.C_DocType_ID=50006 AND rol.IsManual='N')
;

-- Sep 23, 2008 3:19:12 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53174,TO_TIMESTAMP('2008-09-23 15:19:12','YYYY-MM-DD HH24:MI:SS'),100,84100,8410,'Manufacturing Order Use Variance',1,'Y','N','Y','N','Manufacturing Order Use Variance','N',84100,TO_TIMESTAMP('2008-09-23 15:19:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:13 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType (AD_Client_ID,AD_Org_ID,C_DocType_ID,Created,CreatedBy,DocBaseType,DocNoSequence_ID,DocumentCopies,GL_Category_ID,HasCharges,IsActive,IsCreateCounter,IsDefault,IsDefaultCounterDoc,IsDocNoControlled,IsIndexed,IsPickQAConfirm,IsSOTrx,IsShipConfirm,IsSplitWhenDifference,Name,PrintName,Updated,UpdatedBy) VALUES (11,0,50007,TO_TIMESTAMP('2008-09-23 15:19:12','YYYY-MM-DD HH24:MI:SS'),100,'MOU',53174,0,50001,'N','Y','Y','N','N','Y','Y','N','N','N','N','Manufacturing Order Use Variance','Manufacturing Order Use Variance',TO_TIMESTAMP('2008-09-23 15:19:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:13 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType_Trl (AD_Language,C_DocType_ID, DocumentNote,Name,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_DocType_ID, t.DocumentNote,t.Name,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_DocType t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_DocType_ID=50007 AND EXISTS (SELECT * FROM C_DocType_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_DocType_ID!=t.C_DocType_ID)
;

-- Sep 23, 2008 3:19:13 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) WHERE client.AD_Client_ID=11 AND doctype.C_DocType_ID=50007 AND rol.IsManual='N')
;

-- Sep 23, 2008 3:19:14 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53175,TO_TIMESTAMP('2008-09-23 15:19:13','YYYY-MM-DD HH24:MI:SS'),100,84200,8420,'Manufacturing Order Rate Variance',1,'Y','N','Y','N','Manufacturing Order Rate Variance','N',84200,TO_TIMESTAMP('2008-09-23 15:19:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:14 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType (AD_Client_ID,AD_Org_ID,C_DocType_ID,Created,CreatedBy,DocBaseType,DocNoSequence_ID,DocumentCopies,GL_Category_ID,HasCharges,IsActive,IsCreateCounter,IsDefault,IsDefaultCounterDoc,IsDocNoControlled,IsIndexed,IsPickQAConfirm,IsSOTrx,IsShipConfirm,IsSplitWhenDifference,Name,PrintName,Updated,UpdatedBy) VALUES (11,0,50008,TO_TIMESTAMP('2008-09-23 15:19:14','YYYY-MM-DD HH24:MI:SS'),100,'MOV',53175,0,50001,'N','Y','Y','N','N','Y','Y','N','N','N','N','Manufacturing Order Rate Variance','Manufacturing Order Rate Variance',TO_TIMESTAMP('2008-09-23 15:19:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:14 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType_Trl (AD_Language,C_DocType_ID, DocumentNote,Name,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_DocType_ID, t.DocumentNote,t.Name,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_DocType t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_DocType_ID=50008 AND EXISTS (SELECT * FROM C_DocType_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_DocType_ID!=t.C_DocType_ID)
;

-- Sep 23, 2008 3:19:14 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) WHERE client.AD_Client_ID=11 AND doctype.C_DocType_ID=50008 AND rol.IsManual='N')
;

-- Sep 23, 2008 3:19:15 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53176,TO_TIMESTAMP('2008-09-23 15:19:14','YYYY-MM-DD HH24:MI:SS'),100,85000,8500,'Manufacturing Operation Activity',1,'Y','N','Y','N','Manufacturing Operation Activity','N',85000,TO_TIMESTAMP('2008-09-23 15:19:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:15 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType (AD_Client_ID,AD_Org_ID,C_DocType_ID,Created,CreatedBy,DocBaseType,DocNoSequence_ID,DocumentCopies,GL_Category_ID,HasCharges,IsActive,IsCreateCounter,IsDefault,IsDefaultCounterDoc,IsDocNoControlled,IsIndexed,IsPickQAConfirm,IsSOTrx,IsShipConfirm,IsSplitWhenDifference,Name,PrintName,Updated,UpdatedBy) VALUES (11,0,50009,TO_TIMESTAMP('2008-09-23 15:19:15','YYYY-MM-DD HH24:MI:SS'),100,'MOA',53176,0,50001,'N','Y','Y','N','N','Y','Y','N','N','N','N','Manufacturing Operation Activity','Manufacturing Operation Activity',TO_TIMESTAMP('2008-09-23 15:19:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:15 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType_Trl (AD_Language,C_DocType_ID, DocumentNote,Name,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_DocType_ID, t.DocumentNote,t.Name,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_DocType t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_DocType_ID=50009 AND EXISTS (SELECT * FROM C_DocType_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_DocType_ID!=t.C_DocType_ID)
;

-- Sep 23, 2008 3:19:15 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) WHERE client.AD_Client_ID=11 AND doctype.C_DocType_ID=50009 AND rol.IsManual='N')
;

-- Sep 23, 2008 3:19:16 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53177,TO_TIMESTAMP('2008-09-23 15:19:15','YYYY-MM-DD HH24:MI:SS'),100,86000,8600,'Maintenance Order',1,'Y','N','Y','N','Maintenance Order','N',86000,TO_TIMESTAMP('2008-09-23 15:19:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:16 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType (AD_Client_ID,AD_Org_ID,C_DocType_ID,Created,CreatedBy,DocBaseType,DocNoSequence_ID,DocumentCopies,GL_Category_ID,HasCharges,IsActive,IsCreateCounter,IsDefault,IsDefaultCounterDoc,IsDocNoControlled,IsIndexed,IsPickQAConfirm,IsSOTrx,IsShipConfirm,IsSplitWhenDifference,Name,PrintName,Updated,UpdatedBy) VALUES (11,0,50010,TO_TIMESTAMP('2008-09-23 15:19:16','YYYY-MM-DD HH24:MI:SS'),100,'MOF',53177,0,50001,'N','Y','Y','N','N','Y','Y','N','N','N','N','Maintenance Order','Maintenance Order',TO_TIMESTAMP('2008-09-23 15:19:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:16 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType_Trl (AD_Language,C_DocType_ID, DocumentNote,Name,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_DocType_ID, t.DocumentNote,t.Name,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_DocType t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_DocType_ID=50010 AND EXISTS (SELECT * FROM C_DocType_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_DocType_ID!=t.C_DocType_ID)
;

-- Sep 23, 2008 3:19:16 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) WHERE client.AD_Client_ID=11 AND doctype.C_DocType_ID=50010 AND rol.IsManual='N')
;

-- Sep 23, 2008 3:19:17 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53178,TO_TIMESTAMP('2008-09-23 15:19:16','YYYY-MM-DD HH24:MI:SS'),100,87000,8700,'Quality Order',1,'Y','N','Y','N','Quality Order','N',87000,TO_TIMESTAMP('2008-09-23 15:19:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:18 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType (AD_Client_ID,AD_Org_ID,C_DocType_ID,Created,CreatedBy,DocBaseType,DocNoSequence_ID,DocumentCopies,GL_Category_ID,HasCharges,IsActive,IsCreateCounter,IsDefault,IsDefaultCounterDoc,IsDocNoControlled,IsIndexed,IsPickQAConfirm,IsSOTrx,IsShipConfirm,IsSplitWhenDifference,Name,PrintName,Updated,UpdatedBy) VALUES (11,0,50011,TO_TIMESTAMP('2008-09-23 15:19:17','YYYY-MM-DD HH24:MI:SS'),100,'MQO',53178,0,50001,'N','Y','Y','N','N','Y','Y','N','N','N','N','Quality Order','Quality Order',TO_TIMESTAMP('2008-09-23 15:19:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:18 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType_Trl (AD_Language,C_DocType_ID, DocumentNote,Name,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_DocType_ID, t.DocumentNote,t.Name,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_DocType t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_DocType_ID=50011 AND EXISTS (SELECT * FROM C_DocType_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_DocType_ID!=t.C_DocType_ID)
;

-- Sep 23, 2008 3:19:18 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) WHERE client.AD_Client_ID=11 AND doctype.C_DocType_ID=50011 AND rol.IsManual='N')
;

-- Sep 23, 2008 3:19:18 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53179,TO_TIMESTAMP('2008-09-23 15:19:18','YYYY-MM-DD HH24:MI:SS'),100,88000,8800,'Distribution Order',1,'Y','N','Y','N','Distribution Order','N',88000,TO_TIMESTAMP('2008-09-23 15:19:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:19 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType (AD_Client_ID,AD_Org_ID,C_DocType_ID,Created,CreatedBy,DocBaseType,DocNoSequence_ID,DocumentCopies,GL_Category_ID,HasCharges,IsActive,IsCreateCounter,IsDefault,IsDefaultCounterDoc,IsDocNoControlled,IsIndexed,IsPickQAConfirm,IsSOTrx,IsShipConfirm,IsSplitWhenDifference,Name,PrintName,Updated,UpdatedBy) VALUES (11,0,50012,TO_TIMESTAMP('2008-09-23 15:19:18','YYYY-MM-DD HH24:MI:SS'),100,'DOO',53179,0,50002,'N','Y','Y','N','N','Y','Y','N','N','N','N','Distribution Order','Distribution Order',TO_TIMESTAMP('2008-09-23 15:19:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 23, 2008 3:19:19 PM CDT
-- Manufacturing Demo
INSERT INTO C_DocType_Trl (AD_Language,C_DocType_ID, DocumentNote,Name,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_DocType_ID, t.DocumentNote,t.Name,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_DocType t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_DocType_ID=50012 AND EXISTS (SELECT * FROM C_DocType_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_DocType_ID!=t.C_DocType_ID)
;

-- Sep 23, 2008 3:19:19 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) WHERE client.AD_Client_ID=11 AND doctype.C_DocType_ID=50012 AND rol.IsManual='N')
;

-- Sep 23, 2008 3:21:33 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:32','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',134,50001,0,0,0,5.000000000000,'POQ',0,50000,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:32','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:33 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:33','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',137,50001,0,0,0,5.000000000000,'POQ',0,50001,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:33','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:34 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:33','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',138,50001,0,0,0,5.000000000000,'POQ',0,50002,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:33','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:34 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:34','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',139,50001,0,0,0,5.000000000000,'POQ',0,50003,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:34','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:34 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:34','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',140,50001,0,0,0,5.000000000000,'POQ',0,50004,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:34','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:35 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:34','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',141,50001,0,0,0,5.000000000000,'POQ',0,50005,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:34','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:35 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:35','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',142,50001,0,0,0,5.000000000000,'POQ',0,50006,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:35','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:36 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:35','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',143,50001,0,0,0,5.000000000000,'POQ',0,50007,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:35','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:36 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:36','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',144,50001,0,0,0,5.000000000000,'POQ',0,50008,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:36','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:36 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:36','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',145,50001,0,0,0,5.000000000000,'POQ',0,50009,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:36','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:37 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:36','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',146,50001,0,0,0,5.000000000000,'POQ',0,50010,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:36','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:38 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:37','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',127,50001,0,0,0,5.000000000000,'POQ',0,50011,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:37','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:38 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:38','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',128,50001,0,0,0,5.000000000000,'POQ',0,50012,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:38','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:39 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:38','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',122,50001,0,0,0,5.000000000000,'POQ',0,50013,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:38','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:42 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:39','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',123,50001,0,0,0,5.000000000000,'POQ',0,50014,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:39','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:43 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:42','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',124,50001,0,0,0,5.000000000000,'POQ',0,50015,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:42','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:46 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:43','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',125,50001,0,0,0,5.000000000000,'POQ',0,50016,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:43','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:47 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:46','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',126,50001,0,0,0,5.000000000000,'POQ',0,50017,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:46','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:53 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:47','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',129,50001,0,0,0,5.000000000000,'POQ',0,50018,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:47','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:54 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:53','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',130,50001,0,0,0,5.000000000000,'POQ',0,50019,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:53','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:56 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:54','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',131,50001,0,0,0,5.000000000000,'POQ',0,50020,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:54','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:56 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:56','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',132,50001,0,0,0,5.000000000000,'POQ',0,50021,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:56','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:56 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:56','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',147,50001,0,0,0,5.000000000000,'POQ',0,50022,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:56','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:57 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:56','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',148,50001,0,0,0,5.000000000000,'POQ',0,50023,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:56','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:57 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:57','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',135,50001,0,0,0,5.000000000000,'POQ',0,50024,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:57','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:58 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:57','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',133,50001,0,0,0,5.000000000000,'POQ',0,50025,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:57','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:58 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:58','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50004,50001,0,0,0,5.000000000000,'POQ',0,50026,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:58','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:59 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:58','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50005,50001,0,0,0,5.000000000000,'POQ',0,50027,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:58','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:21:59 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:59','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50000,50001,0,0,0,5.000000000000,'POQ',0,50028,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:59','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:00 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:21:59','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50016,50001,0,0,0,5.000000000000,'POQ',0,50029,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:21:59','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:00 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:00','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50015,50001,0,0,0,5.000000000000,'POQ',0,50030,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:00','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:01 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:00','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50002,50001,0,0,0,5.000000000000,'POQ',0,50031,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:00','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:01 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:01','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50003,50001,0,0,0,5.000000000000,'POQ',0,50032,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:01','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:02 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:01','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50001,50001,0,0,0,5.000000000000,'POQ',0,50033,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:01','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:02 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:02','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',136,50001,0,0,0,5.000000000000,'POQ',0,50034,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:02','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:03 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:02','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50013,50001,0,0,0,5.000000000000,'POQ',0,50035,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:02','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:03 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:03','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50007,50001,0,0,0,5.000000000000,'POQ',0,50036,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:03','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:04 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:03','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50014,50001,0,0,0,5.000000000000,'POQ',0,50037,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:03','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:04 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:04','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50008,50001,0,0,0,5.000000000000,'POQ',0,50038,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:04','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:04 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:04','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50010,50001,0,0,0,5.000000000000,'POQ',0,50039,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:04','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:05 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:04','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50009,50001,0,0,0,5.000000000000,'POQ',0,50040,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:04','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:05 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:05','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50012,50001,0,0,0,5.000000000000,'POQ',0,50041,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:05','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:06 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:05','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50017,50001,0,0,0,5.000000000000,'POQ',0,50042,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:05','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:06 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:06','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50018,50001,0,0,0,5.000000000000,'POQ',0,50043,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:06','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:07 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:06','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50019,50001,0,0,0,5.000000000000,'POQ',0,50044,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:06','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:07 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:07','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50020,50001,0,0,0,5.000000000000,'POQ',0,50045,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:07','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:08 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:08','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50021,50001,0,0,0,5.000000000000,'POQ',0,50046,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:08','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:09 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:08','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50022,50001,0,0,0,5.000000000000,'POQ',0,50047,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:08','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:09 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:09','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50023,50001,0,0,0,5.000000000000,'POQ',0,50048,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:09','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:10 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:09','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50024,50001,0,0,0,5.000000000000,'POQ',0,50049,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:09','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:10 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:10','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50025,50001,0,0,0,5.000000000000,'POQ',0,50050,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:10','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:11 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:10','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50026,50001,0,0,0,5.000000000000,'POQ',0,50051,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:10','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:22:11 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50000,TO_TIMESTAMP('2008-09-23 15:22:11','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50027,50001,0,0,0,5.000000000000,'POQ',0,50052,101,50005,0,7.000000000000,TO_TIMESTAMP('2008-09-23 15:22:11','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50000
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50001
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50002
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50003
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50004
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50005
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50006
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50007
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50008
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50009
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50010
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50011
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50012
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50013
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50014
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50015
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50016
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50017
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50018
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50019
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50020
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50021
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50022
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50023
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50024
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50025
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50026
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50027
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50028
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50029
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50030
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50031
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50032
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50033
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50034
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50035
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50036
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50037
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50038
;

-- Sep 23, 2008 3:25:16 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50039
;

-- Sep 23, 2008 3:25:17 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50040
;

-- Sep 23, 2008 3:25:17 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50041
;

-- Sep 23, 2008 3:25:17 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50042
;

-- Sep 23, 2008 3:25:17 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50043
;

-- Sep 23, 2008 3:25:17 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50044
;

-- Sep 23, 2008 3:25:17 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50045
;

-- Sep 23, 2008 3:25:17 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50046
;

-- Sep 23, 2008 3:25:17 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50047
;

-- Sep 23, 2008 3:25:17 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50048
;

-- Sep 23, 2008 3:25:17 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50049
;

-- Sep 23, 2008 3:25:17 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50050
;

-- Sep 23, 2008 3:25:17 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50051
;

-- Sep 23, 2008 3:25:17 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=0, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=7.000000000000, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50052
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50000
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50001
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50002
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50003
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50004
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50005
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50006
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50007
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50008
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50009
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50010
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50011
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50012
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50013
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50014
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50015
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50016
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50017
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50018
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50019
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50020
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50021
;

-- Sep 23, 2008 3:25:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50022
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50023
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50024
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50025
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50026
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50027
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50028
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50029
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50030
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50031
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50032
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50033
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50034
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50035
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50036
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50037
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50038
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50039
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50040
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50041
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50042
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50043
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50044
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50045
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50046
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50047
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50048
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50049
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50050
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50051
;

-- Sep 23, 2008 3:25:59 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-23 15:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50052
;

-- Sep 23, 2008 3:39:05 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:04','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',134,50002,0,0,0,5.000000000000,'POQ',0,50053,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:04','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:06 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:05','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',137,50002,0,0,0,5.000000000000,'POQ',0,50054,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:05','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:07 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:06','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',138,50002,0,0,0,5.000000000000,'POQ',0,50055,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:06','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:07 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:07','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',139,50002,0,0,0,5.000000000000,'POQ',0,50056,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:07','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:08 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:07','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',140,50002,0,0,0,5.000000000000,'POQ',0,50057,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:07','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:09 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:08','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',141,50002,0,0,0,5.000000000000,'POQ',0,50058,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:08','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:10 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:09','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',142,50002,0,0,0,5.000000000000,'POQ',0,50059,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:09','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:11 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:10','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',143,50002,0,0,0,5.000000000000,'POQ',0,50060,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:10','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:13 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:12','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',144,50002,0,0,0,5.000000000000,'POQ',0,50061,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:12','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:13 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:13','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',145,50002,0,0,0,5.000000000000,'POQ',0,50062,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:13','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:14 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:13','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',146,50002,0,0,0,5.000000000000,'POQ',0,50063,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:13','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:15 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:14','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',127,50002,0,0,0,5.000000000000,'POQ',0,50064,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:14','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:16 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:15','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',128,50002,0,0,0,5.000000000000,'POQ',0,50065,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:15','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:16 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:16','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',122,50002,0,0,0,5.000000000000,'POQ',0,50066,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:16','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:17 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:16','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',123,50002,0,0,0,5.000000000000,'POQ',0,50067,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:16','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:18 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:17','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',124,50002,0,0,0,5.000000000000,'POQ',0,50068,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:17','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:19 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:18','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',125,50002,0,0,0,5.000000000000,'POQ',0,50069,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:18','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:21 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:20','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',126,50002,0,0,0,5.000000000000,'POQ',0,50070,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:20','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:21 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:21','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',129,50002,0,0,0,5.000000000000,'POQ',0,50071,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:21','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:22 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:22','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',130,50002,0,0,0,5.000000000000,'POQ',0,50072,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:22','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:24 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:22','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',131,50002,0,0,0,5.000000000000,'POQ',0,50073,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:22','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:25 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:24','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',132,50002,0,0,0,5.000000000000,'POQ',0,50074,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:24','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:26 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:25','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',147,50002,0,0,0,5.000000000000,'POQ',0,50075,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:25','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:27 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:27','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',148,50002,0,0,0,5.000000000000,'POQ',0,50076,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:27','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:28 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:28','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',135,50002,0,0,0,5.000000000000,'POQ',0,50077,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:28','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:29 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:29','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',133,50002,0,0,0,5.000000000000,'POQ',0,50078,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:29','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:31 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:30','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50004,50002,0,0,0,5.000000000000,'POQ',0,50079,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:30','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:31 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:31','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50005,50002,0,0,0,5.000000000000,'POQ',0,50080,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:31','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:32 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:31','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50000,50002,0,0,0,5.000000000000,'POQ',0,50081,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:31','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:33 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:32','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50016,50002,0,0,0,5.000000000000,'POQ',0,50082,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:32','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:34 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:34','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50015,50002,0,0,0,5.000000000000,'POQ',0,50083,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:34','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:35 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:34','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50002,50002,0,0,0,5.000000000000,'POQ',0,50084,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:34','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:36 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:35','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50003,50002,0,0,0,5.000000000000,'POQ',0,50085,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:35','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:37 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:36','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50001,50002,0,0,0,5.000000000000,'POQ',0,50086,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:36','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:38 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:37','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',136,50002,0,0,0,5.000000000000,'POQ',0,50087,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:37','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:39 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:38','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50013,50002,0,0,0,5.000000000000,'POQ',0,50088,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:38','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:39 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:39','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50007,50002,0,0,0,5.000000000000,'POQ',0,50089,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:39','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:40 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:39','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50014,50002,0,0,0,5.000000000000,'POQ',0,50090,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:39','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:40 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:40','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50008,50002,0,0,0,5.000000000000,'POQ',0,50091,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:40','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:41 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:40','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50010,50002,0,0,0,5.000000000000,'POQ',0,50092,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:40','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:42 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:41','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50009,50002,0,0,0,5.000000000000,'POQ',0,50093,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:41','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:42 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:42','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50012,50002,0,0,0,5.000000000000,'POQ',0,50094,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:42','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:43 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:42','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50017,50002,0,0,0,5.000000000000,'POQ',0,50095,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:42','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:44 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:43','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50018,50002,0,0,0,5.000000000000,'POQ',0,50096,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:43','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:45 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:44','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50019,50002,0,0,0,5.000000000000,'POQ',0,50097,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:44','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:49 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:45','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50020,50002,0,0,0,5.000000000000,'POQ',0,50098,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:45','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:49 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:49','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50021,50002,0,0,0,5.000000000000,'POQ',0,50099,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:49','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:50 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:49','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50022,50002,0,0,0,5.000000000000,'POQ',0,50100,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:49','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:50 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:50','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50023,50002,0,0,0,5.000000000000,'POQ',0,50101,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:50','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:51 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:50','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50024,50002,0,0,0,5.000000000000,'POQ',0,50102,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:50','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:52 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:51','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50025,50002,0,0,0,5.000000000000,'POQ',0,50103,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:51','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:52 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:52','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50026,50002,0,0,0,5.000000000000,'POQ',0,50104,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:52','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:39:53 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50001,TO_TIMESTAMP('2008-09-23 15:39:52','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','Y','Y','N','Y','Y',50027,50002,0,0,0,5.000000000000,'POQ',0,50105,101,50000,0,0,TO_TIMESTAMP('2008-09-23 15:39:52','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 23, 2008 3:43:58 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=50016, DeliveryTime_Promised=1.000000000000, IsPhantom='Y', Order_Min=1000.000000000000, Order_Pack=200.000000000000, Order_Qty=1000.000000000000, PP_Product_BOM_ID=50006, TimeFence=7.000000000000,Updated=TO_TIMESTAMP('2008-09-23 15:43:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50091
;

-- Sep 23, 2008 3:46:02 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=50017, DeliveryTime_Promised=1.000000000000, Order_Min=100.000000000000, PP_Product_BOM_ID=50004,Updated=TO_TIMESTAMP('2008-09-23 15:46:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50087
;

-- Sep 23, 2008 3:49:36 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=50017, DeliveryTime_Promised=1.000000000000, Order_Min=100.000000000000, PP_Product_BOM_ID=50005, TimeFence=7.000000000000,Updated=TO_TIMESTAMP('2008-09-23 15:49:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50089
;

-- Sep 23, 2008 3:50:28 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Locator_ID=NULL,Updated=TO_TIMESTAMP('2008-09-23 15:50:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50015
;

-- Sep 23, 2008 3:52:20 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=50015, DeliveryTime_Promised=3.000000000000, Order_Min=400.000000000000, Order_Pack=4.000000000000, PP_Product_BOM_ID=50000, SafetyStock=40.000000000000, TimeFence=7.000000000000,Updated=TO_TIMESTAMP('2008-09-23 15:52:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50025
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=122
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=123
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=124
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=125
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=126
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=127
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=128
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=129
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=130
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=131
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=132
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=1,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=133
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=1,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=134
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=1,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=135
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=136
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=137
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=138
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=139
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=140
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=141
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=142
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=143
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=144
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=145
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=146
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=147
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=148
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50007
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50018
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50019
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50020
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50021
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50022
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50023
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50024
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50025
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50026
;

-- Sep 23, 2008 3:59:04 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsStocked='N', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-23 15:59:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50027
;

-- Sep 24, 2008 11:05:07 AM CDT
-- Forecast Default Fix
UPDATE M_ForecastLine SET SalesRep_ID=101,Updated=TO_TIMESTAMP('2008-09-24 11:05:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_ForecastLine_ID=50002
;

-- Sep 24, 2008 11:08:09 AM CDT
-- Forecast Default Fix
UPDATE M_ForecastLine SET SalesRep_ID=102,Updated=TO_TIMESTAMP('2008-09-24 11:08:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_ForecastLine_ID=50003
;

-- Sep 24, 2008 11:08:13 AM CDT
-- Forecast Default Fix
UPDATE M_ForecastLine SET SalesRep_ID=102,Updated=TO_TIMESTAMP('2008-09-24 11:08:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_ForecastLine_ID=50004
;

-- Sep 24, 2008 11:08:16 AM CDT
-- Forecast Default Fix
UPDATE M_ForecastLine SET SalesRep_ID=102,Updated=TO_TIMESTAMP('2008-09-24 11:08:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_ForecastLine_ID=50005
;

-- Sep 24, 2008 11:08:23 AM CDT
-- Forecast Default Fix
UPDATE M_ForecastLine SET SalesRep_ID=102,Updated=TO_TIMESTAMP('2008-09-24 11:08:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_ForecastLine_ID=50006
;

-- Sep 24, 2008 11:08:36 AM CDT
-- Forecast Default Fix
UPDATE M_ForecastLine SET SalesRep_ID=101,Updated=TO_TIMESTAMP('2008-09-24 11:08:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_ForecastLine_ID=50005
;

-- Sep 24, 2008 11:13:32 AM CDT
-- Forecast Default Fix
INSERT INTO M_Forecast (AD_Client_ID,AD_Org_ID,C_Calendar_ID,C_Year_ID,Created,CreatedBy,Description,IsActive,IsDefault,M_Forecast_ID,M_PriceList_ID,Name,Processing,Updated,UpdatedBy) VALUES (11,50002,102,50001,TO_TIMESTAMP('2008-09-24 11:13:30','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Store North','Y','N',50000,101,'Store North 2008','N',TO_TIMESTAMP('2008-09-24 11:13:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:13:51 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50020,TO_TIMESTAMP('2008-09-24 11:13:45','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50011,50000,145,50003,0,0,101,TO_TIMESTAMP('2008-09-24 11:13:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:13:57 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:13:51','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:13:51','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50011,50000,145,50003,'MRP','FCT',50011,0,'D',TO_TIMESTAMP('2008-09-24 11:13:51','YYYY-MM-DD HH24:MI:SS'),100,'50011')
;

-- Sep 24, 2008 11:14:21 AM CDT
-- Forecast Default Fix
UPDATE M_ForecastLine SET Qty=10.000000000000,Updated=TO_TIMESTAMP('2008-09-24 11:14:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_ForecastLine_ID=50011
;

-- Sep 24, 2008 11:14:21 AM CDT
-- Forecast Default Fix
UPDATE PP_MRP SET AD_Org_ID=50002, DateFinishSchedule=TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), DateOrdered=TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), DatePromised=TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), DateStartSchedule=TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), Description='Forecast Store North', DocStatus='IP', M_Product_ID=145, M_Warehouse_ID=50003, Name='MRP', Qty=10.000000000000,Updated=TO_TIMESTAMP('2008-09-24 11:14:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_MRP_ID=50011
;

-- Sep 24, 2008 11:14:46 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50020,TO_TIMESTAMP('2008-09-24 11:14:40','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50012,50000,136,50003,8.000000000000,0,101,TO_TIMESTAMP('2008-09-24 11:14:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:14:50 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:14:46','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:14:46','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50012,50000,136,50003,'MRP','FCT',50012,8.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:14:46','YYYY-MM-DD HH24:MI:SS'),100,'50012')
;

-- Sep 24, 2008 11:14:54 AM CDT
-- Forecast Default Fix
UPDATE M_ForecastLine SET SalesRep_ID=102,Updated=TO_TIMESTAMP('2008-09-24 11:14:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_ForecastLine_ID=50012
;

-- Sep 24, 2008 11:15:12 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50020,TO_TIMESTAMP('2008-09-24 11:15:11','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50013,50000,50007,50003,4.000000000000,0,102,TO_TIMESTAMP('2008-09-24 11:15:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:15:14 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:15:12','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:15:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50013,50000,50007,50003,'MRP','FCT',50013,4.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:15:12','YYYY-MM-DD HH24:MI:SS'),100,'50013')
;

-- Sep 24, 2008 11:15:45 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50021,TO_TIMESTAMP('2008-09-24 11:15:43','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01','YYYY-MM-DD'),'Y',50014,50000,145,50003,8.000000000000,0,101,TO_TIMESTAMP('2008-09-24 11:15:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:15:48 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:15:45','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:15:45','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50014,50000,145,50003,'MRP','FCT',50014,8.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:15:45','YYYY-MM-DD HH24:MI:SS'),100,'50014')
;

-- Sep 24, 2008 11:16:10 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50021,TO_TIMESTAMP('2008-09-24 11:16:09','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01','YYYY-MM-DD'),'Y',50015,50000,136,50003,10.000000000000,0,102,TO_TIMESTAMP('2008-09-24 11:16:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:16:11 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:16:10','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:16:10','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50015,50000,136,50003,'MRP','FCT',50015,10.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:16:10','YYYY-MM-DD HH24:MI:SS'),100,'50015')
;

-- Sep 24, 2008 11:16:37 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50021,TO_TIMESTAMP('2008-09-24 11:16:31','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01','YYYY-MM-DD'),'Y',50016,50000,50007,50003,5.000000000000,0,102,TO_TIMESTAMP('2008-09-24 11:16:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:16:38 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:16:37','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:16:37','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50016,50000,50007,50003,'MRP','FCT',50016,5.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:16:37','YYYY-MM-DD HH24:MI:SS'),100,'50016')
;

-- Sep 24, 2008 11:17:05 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50022,TO_TIMESTAMP('2008-09-24 11:17:03','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01','YYYY-MM-DD'),'Y',50017,50000,145,50003,10.000000000000,0,101,TO_TIMESTAMP('2008-09-24 11:17:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:17:06 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:17:05','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:17:05','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50017,50000,145,50003,'MRP','FCT',50017,10.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:17:05','YYYY-MM-DD HH24:MI:SS'),100,'50017')
;

-- Sep 24, 2008 11:17:46 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50022,TO_TIMESTAMP('2008-09-24 11:17:45','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01','YYYY-MM-DD'),'Y',50018,50000,136,50003,8.000000000000,0,101,TO_TIMESTAMP('2008-09-24 11:17:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:17:47 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:17:46','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:17:46','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50018,50000,136,50003,'MRP','FCT',50018,8.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:17:46','YYYY-MM-DD HH24:MI:SS'),100,'50018')
;

-- Sep 24, 2008 11:18:18 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50022,TO_TIMESTAMP('2008-09-24 11:18:16','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01','YYYY-MM-DD'),'Y',50019,50000,50007,50003,4.000000000000,0,101,TO_TIMESTAMP('2008-09-24 11:18:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:18:19 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:18:18','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:18:18','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50019,50000,50007,50003,'MRP','FCT',50019,4.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:18:18','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 11:18:40 AM CDT
-- Forecast Default Fix
UPDATE M_ForecastLine SET SalesRep_ID=102,Updated=TO_TIMESTAMP('2008-09-24 11:18:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_ForecastLine_ID=50018
;

-- Sep 24, 2008 11:19:23 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50022,TO_TIMESTAMP('2008-09-24 11:19:21','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01','YYYY-MM-DD'),'Y',50020,50000,50007,50003,4.000000000000,0,102,TO_TIMESTAMP('2008-09-24 11:19:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:19:25 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:19:23','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:19:23','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50020,50000,50007,50003,'MRP','FCT',50020,4.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:19:23','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 11:20:38 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50022,TO_TIMESTAMP('2008-09-24 11:20:36','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01','YYYY-MM-DD'),'Y',50021,50000,50007,50003,5.000000000000,0,102,TO_TIMESTAMP('2008-09-24 11:20:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:20:39 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:20:38','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:20:38','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50021,50000,50007,50003,'MRP','FCT',50021,5.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:20:38','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 11:21:29 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50022,TO_TIMESTAMP('2008-09-24 11:21:28','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01','YYYY-MM-DD'),'Y',50022,50000,50007,50003,5.000000000000,0,102,TO_TIMESTAMP('2008-09-24 11:21:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:21:30 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:21:29','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:21:29','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50022,50000,50007,50003,'MRP','FCT',50022,5.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:21:29','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 11:23:08 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50022,TO_TIMESTAMP('2008-09-24 11:23:08','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01','YYYY-MM-DD'),'Y',50023,50000,50007,50003,5.000000000000,0,102,TO_TIMESTAMP('2008-09-24 11:23:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:23:10 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:23:08','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:23:08','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50023,50000,50007,50003,'MRP','FCT',50023,5.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:23:08','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 11:24:20 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50023,TO_TIMESTAMP('2008-09-24 11:24:19','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01','YYYY-MM-DD'),'Y',50024,50000,145,50003,0,0,101,TO_TIMESTAMP('2008-09-24 11:24:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:24:21 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:24:20','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:24:20','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50024,50000,145,50003,'MRP','FCT',50024,0,'D',TO_TIMESTAMP('2008-09-24 11:24:20','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 11:27:29 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50022,TO_TIMESTAMP('2008-09-24 11:27:28','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01','YYYY-MM-DD'),'Y',50025,50000,50007,50003,4.000000000000,0,102,TO_TIMESTAMP('2008-09-24 11:27:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:27:29 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:27:29','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:27:29','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50025,50000,50007,50003,'MRP','FCT',50025,4.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:27:29','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 11:28:27 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50002,50023,TO_TIMESTAMP('2008-09-24 11:28:26','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01','YYYY-MM-DD'),'Y',50026,50000,145,50003,8.000000000000,0,101,TO_TIMESTAMP('2008-09-24 11:28:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:28:28 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 11:28:27','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:28:27','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store North','IP','Y',50026,50000,145,50003,'MRP','FCT',50026,8.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:28:27','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 11:33:56 AM CDT
-- Forecast Default Fix
INSERT INTO M_Forecast (AD_Client_ID,AD_Org_ID,C_Calendar_ID,C_Year_ID,Created,CreatedBy,Description,IsActive,IsDefault,M_Forecast_ID,M_PriceList_ID,Name,Processing,Updated,UpdatedBy) VALUES (11,50004,102,50001,TO_TIMESTAMP('2008-09-24 11:33:55','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Store South','Y','N',50001,101,'Store South 2008','N',TO_TIMESTAMP('2008-09-24 11:33:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:34:08 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50020,TO_TIMESTAMP('2008-09-24 11:34:07','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50027,50001,145,50004,2.000000000000,0,101,TO_TIMESTAMP('2008-09-24 11:34:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:34:09 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 11:34:08','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:34:08','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50027,50001,145,50004,'MRP','FCT',50027,2.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:34:08','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 11:38:49 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50020,TO_TIMESTAMP('2008-09-24 11:38:48','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50028,50001,145,50004,10.000000000000,0,101,TO_TIMESTAMP('2008-09-24 11:38:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:38:49 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 11:38:49','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:38:49','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50028,50001,145,50004,'MRP','FCT',50028,10.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:38:49','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 11:39:42 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50020,TO_TIMESTAMP('2008-09-24 11:39:41','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50029,50001,145,50004,10.000000000000,0,101,TO_TIMESTAMP('2008-09-24 11:39:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:39:42 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 11:39:42','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:39:42','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50029,50001,145,50004,'MRP','FCT',50029,10.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:39:42','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 11:41:06 AM CDT
-- Forecast Default Fix
INSERT INTO M_Forecast (AD_Client_ID,AD_Org_ID,C_Calendar_ID,C_Year_ID,Created,CreatedBy,Description,IsActive,IsDefault,M_Forecast_ID,M_PriceList_ID,Name,Processing,Updated,UpdatedBy) VALUES (11,50004,102,50001,TO_TIMESTAMP('2008-09-24 11:41:04','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Store South','Y','N',50002,101,'Store South 2008','N',TO_TIMESTAMP('2008-09-24 11:41:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:41:24 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50020,TO_TIMESTAMP('2008-09-24 11:41:22','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50030,50002,145,50004,10.000000000000,0,101,TO_TIMESTAMP('2008-09-24 11:41:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:41:26 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 11:41:24','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:41:24','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50030,50002,145,50004,'MRP','FCT',50030,10.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:41:24','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 11:42:44 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50020,TO_TIMESTAMP('2008-09-24 11:42:43','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50031,50002,145,50004,10.000000000000,0,101,TO_TIMESTAMP('2008-09-24 11:42:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:42:45 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 11:42:44','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:42:44','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50031,50002,145,50004,'MRP','FCT',50031,10.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:42:44','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 11:43:29 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50020,TO_TIMESTAMP('2008-09-24 11:43:27','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50032,50002,136,50004,10.000000000000,0,101,TO_TIMESTAMP('2008-09-24 11:43:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:43:30 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 11:43:29','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:43:29','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50032,50002,136,50004,'MRP','FCT',50032,10.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:43:29','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 11:44:58 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50020,TO_TIMESTAMP('2008-09-24 11:44:57','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50033,50002,145,50004,10.000000000000,0,102,TO_TIMESTAMP('2008-09-24 11:44:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:44:59 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 11:44:58','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:44:58','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50033,50002,145,50004,'MRP','FCT',50033,10.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:44:58','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 11:47:52 AM CDT
-- Forecast Default Fix
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50020,TO_TIMESTAMP('2008-09-24 11:47:51','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50034,50002,50007,50004,5.000000000000,0,102,TO_TIMESTAMP('2008-09-24 11:47:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 11:47:52 AM CDT
-- Forecast Default Fix
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 11:47:52','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 11:47:52','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50034,50002,50007,50004,'MRP','FCT',50034,5.000000000000,'D',TO_TIMESTAMP('2008-09-24 11:47:52','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 12:05:04 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50020,TO_TIMESTAMP('2008-09-24 12:04:56','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50035,50002,145,50004,10.000000000000,0,101,TO_TIMESTAMP('2008-09-24 12:04:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:05:07 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 12:05:04','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:05:04','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50035,50002,145,50004,'MRP','FCT',50035,10.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:05:04','YYYY-MM-DD HH24:MI:SS'),100,'50019')
;

-- Sep 24, 2008 12:07:38 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50020,TO_TIMESTAMP('2008-09-24 12:07:31','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50036,50002,136,50004,8.000000000000,0,102,TO_TIMESTAMP('2008-09-24 12:07:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:07:44 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 12:07:38','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:07:38','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50036,50002,136,50004,'MRP','FCT',50036,8.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:07:38','YYYY-MM-DD HH24:MI:SS'),100,'50020')
;

-- Sep 24, 2008 12:08:15 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50020,TO_TIMESTAMP('2008-09-24 12:08:10','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50037,50002,50007,50004,3.000000000000,0,102,TO_TIMESTAMP('2008-09-24 12:08:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:08:17 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 12:08:15','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:08:15','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50037,50002,50007,50004,'MRP','FCT',50037,3.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:08:15','YYYY-MM-DD HH24:MI:SS'),100,'50021')
;

-- Sep 24, 2008 12:08:50 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50021,TO_TIMESTAMP('2008-09-24 12:08:49','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01','YYYY-MM-DD'),'Y',50038,50002,145,50004,8.000000000000,0,101,TO_TIMESTAMP('2008-09-24 12:08:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:08:50 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 12:08:50','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:08:50','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50038,50002,145,50004,'MRP','FCT',50038,8.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:08:50','YYYY-MM-DD HH24:MI:SS'),100,'50022')
;

-- Sep 24, 2008 12:09:21 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50021,TO_TIMESTAMP('2008-09-24 12:09:20','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01','YYYY-MM-DD'),'Y',50039,50002,136,50004,0,0,102,TO_TIMESTAMP('2008-09-24 12:09:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:09:22 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 12:09:21','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:09:21','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50039,50002,136,50004,'MRP','FCT',50039,0,'D',TO_TIMESTAMP('2008-09-24 12:09:21','YYYY-MM-DD HH24:MI:SS'),100,'50023')
;

-- Sep 24, 2008 12:09:27 PM CDT
-- Manufacturing Demo
UPDATE M_ForecastLine SET Qty=10.000000000000,Updated=TO_TIMESTAMP('2008-09-24 12:09:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_ForecastLine_ID=50039
;

-- Sep 24, 2008 12:09:27 PM CDT
-- Manufacturing Demo
UPDATE PP_MRP SET AD_Org_ID=50004, DateFinishSchedule=TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), DateOrdered=TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), DatePromised=TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), DateStartSchedule=TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), Description='Forecast Store South', DocStatus='IP', M_Product_ID=136, M_Warehouse_ID=50004, Name='MRP', Qty=10.000000000000,Updated=TO_TIMESTAMP('2008-09-24 12:09:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_MRP_ID=50039
;

-- Sep 24, 2008 12:09:46 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50021,TO_TIMESTAMP('2008-09-24 12:09:45','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01','YYYY-MM-DD'),'Y',50040,50002,50007,50004,4.000000000000,0,102,TO_TIMESTAMP('2008-09-24 12:09:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:09:46 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 12:09:46','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:09:46','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50040,50002,50007,50004,'MRP','FCT',50040,4.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:09:46','YYYY-MM-DD HH24:MI:SS'),100,'50024')
;

-- Sep 24, 2008 12:14:18 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50021,TO_TIMESTAMP('2008-09-24 12:14:16','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01','YYYY-MM-DD'),'Y',50041,50002,145,50004,6.000000000000,0,101,TO_TIMESTAMP('2008-09-24 12:14:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:14:20 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 12:14:18','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:14:18','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50041,50002,145,50004,'MRP','FCT',50041,6.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:14:18','YYYY-MM-DD HH24:MI:SS'),100,'50025')
;

-- Sep 24, 2008 12:14:57 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50022,TO_TIMESTAMP('2008-09-24 12:14:55','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01','YYYY-MM-DD'),'Y',50042,50002,145,50004,6.000000000000,0,102,TO_TIMESTAMP('2008-09-24 12:14:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:14:58 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 12:14:57','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:14:57','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50042,50002,145,50004,'MRP','FCT',50042,6.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:14:57','YYYY-MM-DD HH24:MI:SS'),100,'50026')
;

-- Sep 24, 2008 12:22:48 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50022,TO_TIMESTAMP('2008-09-24 12:22:47','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01','YYYY-MM-DD'),'Y',50043,50002,136,50004,8.000000000000,0,102,TO_TIMESTAMP('2008-09-24 12:22:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:22:50 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 12:22:48','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:22:48','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50043,50002,136,50004,'MRP','FCT',50043,8.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:22:48','YYYY-MM-DD HH24:MI:SS'),100,'50027')
;

-- Sep 24, 2008 12:23:24 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50022,TO_TIMESTAMP('2008-09-24 12:23:22','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01','YYYY-MM-DD'),'Y',50044,50002,50007,50004,6.000000000000,0,102,TO_TIMESTAMP('2008-09-24 12:23:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:23:26 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 12:23:24','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:23:24','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50044,50002,50007,50004,'MRP','FCT',50044,6.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:23:24','YYYY-MM-DD HH24:MI:SS'),100,'50028')
;

-- Sep 24, 2008 12:23:31 PM CDT
-- Manufacturing Demo
UPDATE M_ForecastLine SET SalesRep_ID=101,Updated=TO_TIMESTAMP('2008-09-24 12:23:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_ForecastLine_ID=50042
;

-- Sep 24, 2008 12:24:04 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50023,TO_TIMESTAMP('2008-09-24 12:24:03','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01','YYYY-MM-DD'),'Y',50045,50002,145,50004,4.000000000000,0,101,TO_TIMESTAMP('2008-09-24 12:24:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:24:05 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 12:24:04','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:24:04','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50045,50002,145,50004,'MRP','FCT',50045,4.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:24:04','YYYY-MM-DD HH24:MI:SS'),100,'50029')
;

-- Sep 24, 2008 12:24:16 PM CDT
-- Manufacturing Demo
UPDATE M_ForecastLine SET SalesRep_ID=102,Updated=TO_TIMESTAMP('2008-09-24 12:24:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_ForecastLine_ID=50045
;

-- Sep 24, 2008 12:24:52 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50023,TO_TIMESTAMP('2008-09-24 12:24:51','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01','YYYY-MM-DD'),'Y',50046,50002,136,50004,6.000000000000,0,102,TO_TIMESTAMP('2008-09-24 12:24:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:24:54 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 12:24:52','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:24:52','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50046,50002,136,50004,'MRP','FCT',50046,6.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:24:52','YYYY-MM-DD HH24:MI:SS'),100,'50030')
;

-- Sep 24, 2008 12:25:13 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50004,50023,TO_TIMESTAMP('2008-09-24 12:25:12','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01','YYYY-MM-DD'),'Y',50047,50002,50007,50004,3.000000000000,0,102,TO_TIMESTAMP('2008-09-24 12:25:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:25:13 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 12:25:13','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:25:13','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store South','IP','Y',50047,50002,50007,50004,'MRP','FCT',50047,3.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:25:13','YYYY-MM-DD HH24:MI:SS'),100,'50031')
;

-- Sep 24, 2008 12:28:15 PM CDT
-- Manufacturing Demo
INSERT INTO M_Forecast (AD_Client_ID,AD_Org_ID,C_Calendar_ID,C_Year_ID,Created,CreatedBy,Description,IsActive,IsDefault,M_Forecast_ID,M_PriceList_ID,Name,Processing,Updated,UpdatedBy) VALUES (11,50006,102,50001,TO_TIMESTAMP('2008-09-24 12:28:14','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Store West','Y','N',50003,101,'Store West 2008','N',TO_TIMESTAMP('2008-09-24 12:28:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:28:30 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50006,50020,TO_TIMESTAMP('2008-09-24 12:28:30','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50048,50003,145,50006,5.000000000000,0,101,TO_TIMESTAMP('2008-09-24 12:28:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:28:31 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 12:28:31','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:28:31','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store West','IP','Y',50048,50003,145,50006,'MRP','FCT',50048,5.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:28:31','YYYY-MM-DD HH24:MI:SS'),100,'50032')
;

-- Sep 24, 2008 12:28:52 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50006,50020,TO_TIMESTAMP('2008-09-24 12:28:52','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50049,50003,136,50006,10.000000000000,0,102,TO_TIMESTAMP('2008-09-24 12:28:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:28:53 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 12:28:52','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:28:52','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store West','IP','Y',50049,50003,136,50006,'MRP','FCT',50049,10.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:28:52','YYYY-MM-DD HH24:MI:SS'),100,'50033')
;

-- Sep 24, 2008 12:29:04 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50006,50020,TO_TIMESTAMP('2008-09-24 12:29:03','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01','YYYY-MM-DD'),'Y',50050,50003,50007,50006,0,0,102,TO_TIMESTAMP('2008-09-24 12:29:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:29:05 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 12:29:04','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:29:04','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store West','IP','Y',50050,50003,50007,50006,'MRP','FCT',50050,0,'D',TO_TIMESTAMP('2008-09-24 12:29:04','YYYY-MM-DD HH24:MI:SS'),100,'50034')
;

-- Sep 24, 2008 12:29:07 PM CDT
-- Manufacturing Demo
UPDATE M_ForecastLine SET Qty=3.000000000000,Updated=TO_TIMESTAMP('2008-09-24 12:29:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_ForecastLine_ID=50050
;

-- Sep 24, 2008 12:29:07 PM CDT
-- Manufacturing Demo
UPDATE PP_MRP SET AD_Org_ID=50006, DateFinishSchedule=TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), DateOrdered=TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), DatePromised=TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), DateStartSchedule=TO_TIMESTAMP('2008-09-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), Description='Forecast Store West', DocStatus='IP', M_Product_ID=50007, M_Warehouse_ID=50006, Name='MRP', Qty=3.000000000000,Updated=TO_TIMESTAMP('2008-09-24 12:29:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_MRP_ID=50050
;

-- Sep 24, 2008 12:29:30 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50006,50021,TO_TIMESTAMP('2008-09-24 12:29:29','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01','YYYY-MM-DD'),'Y',50051,50003,145,50006,6.000000000000,0,101,TO_TIMESTAMP('2008-09-24 12:29:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:29:30 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 12:29:30','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:29:30','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store West','IP','Y',50051,50003,145,50006,'MRP','FCT',50051,6.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:29:30','YYYY-MM-DD HH24:MI:SS'),100,'50035')
;

-- Sep 24, 2008 12:29:51 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50006,50021,TO_TIMESTAMP('2008-09-24 12:29:50','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01','YYYY-MM-DD'),'Y',50052,50003,136,50006,8.000000000000,0,101,TO_TIMESTAMP('2008-09-24 12:29:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:29:51 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 12:29:51','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:29:51','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store West','IP','Y',50052,50003,136,50006,'MRP','FCT',50052,8.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:29:51','YYYY-MM-DD HH24:MI:SS'),100,'50036')
;

-- Sep 24, 2008 12:30:12 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50006,50021,TO_TIMESTAMP('2008-09-24 12:30:10','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01','YYYY-MM-DD'),'Y',50053,50003,50007,50006,10.000000000000,0,101,TO_TIMESTAMP('2008-09-24 12:30:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 12:30:14 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 12:30:12','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 12:30:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store West','IP','Y',50053,50003,50007,50006,'MRP','FCT',50053,10.000000000000,'D',TO_TIMESTAMP('2008-09-24 12:30:12','YYYY-MM-DD HH24:MI:SS'),100,'50037')
;

-- Sep 24, 2008 1:14:28 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50006,50021,TO_TIMESTAMP('2008-09-24 13:14:19','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01','YYYY-MM-DD'),'Y',50054,50003,145,50006,8.000000000000,0,101,TO_TIMESTAMP('2008-09-24 13:14:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 1:14:30 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 13:14:28','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 13:14:28','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-10-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store West','IP','Y',50054,50003,145,50006,'MRP','FCT',50054,8.000000000000,'D',TO_TIMESTAMP('2008-09-24 13:14:28','YYYY-MM-DD HH24:MI:SS'),100,'50038')
;

-- Sep 24, 2008 1:15:06 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50006,50022,TO_TIMESTAMP('2008-09-24 13:15:05','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01','YYYY-MM-DD'),'Y',50055,50003,145,50006,6.000000000000,0,101,TO_TIMESTAMP('2008-09-24 13:15:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 1:15:08 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 13:15:06','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 13:15:06','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store West','IP','Y',50055,50003,145,50006,'MRP','FCT',50055,6.000000000000,'D',TO_TIMESTAMP('2008-09-24 13:15:06','YYYY-MM-DD HH24:MI:SS'),100,'50039')
;

-- Sep 24, 2008 1:15:43 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50006,50022,TO_TIMESTAMP('2008-09-24 13:15:39','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01','YYYY-MM-DD'),'Y',50056,50003,136,50006,8.000000000000,0,102,TO_TIMESTAMP('2008-09-24 13:15:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 1:15:44 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 13:15:43','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 13:15:43','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store West','IP','Y',50056,50003,136,50006,'MRP','FCT',50056,8.000000000000,'D',TO_TIMESTAMP('2008-09-24 13:15:43','YYYY-MM-DD HH24:MI:SS'),100,'50040')
;

-- Sep 24, 2008 1:16:09 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50006,50022,TO_TIMESTAMP('2008-09-24 13:16:08','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01','YYYY-MM-DD'),'Y',50057,50003,50007,50006,3.000000000000,0,102,TO_TIMESTAMP('2008-09-24 13:16:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 1:16:10 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 13:16:09','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 13:16:09','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-11-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store West','IP','Y',50057,50003,50007,50006,'MRP','FCT',50057,3.000000000000,'D',TO_TIMESTAMP('2008-09-24 13:16:09','YYYY-MM-DD HH24:MI:SS'),100,'50041')
;

-- Sep 24, 2008 1:16:31 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50006,50023,TO_TIMESTAMP('2008-09-24 13:16:29','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01','YYYY-MM-DD'),'Y',50058,50003,145,50006,3.000000000000,0,101,TO_TIMESTAMP('2008-09-24 13:16:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 1:16:32 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 13:16:31','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 13:16:31','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store West','IP','Y',50058,50003,145,50006,'MRP','FCT',50058,3.000000000000,'D',TO_TIMESTAMP('2008-09-24 13:16:31','YYYY-MM-DD HH24:MI:SS'),100,'50042')
;

-- Sep 24, 2008 1:16:57 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50006,50023,TO_TIMESTAMP('2008-09-24 13:16:56','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01','YYYY-MM-DD'),'Y',50059,50003,136,50006,0,0,102,TO_TIMESTAMP('2008-09-24 13:16:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 1:16:59 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 13:16:57','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 13:16:57','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store West','IP','Y',50059,50003,136,50006,'MRP','FCT',50059,0,'D',TO_TIMESTAMP('2008-09-24 13:16:57','YYYY-MM-DD HH24:MI:SS'),100,'50043')
;

-- Sep 24, 2008 1:17:12 PM CDT
-- Manufacturing Demo
INSERT INTO M_ForecastLine (AD_Client_ID,AD_Org_ID,C_Period_ID,Created,CreatedBy,DatePromised,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Qty,QtyCalculated,SalesRep_ID,Updated,UpdatedBy) VALUES (11,50006,50023,TO_TIMESTAMP('2008-09-24 13:17:11','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01','YYYY-MM-DD'),'Y',50060,50003,50007,50006,0,0,102,TO_TIMESTAMP('2008-09-24 13:17:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 1:17:14 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,Description,DocStatus,IsActive,M_ForecastLine_ID,M_Forecast_ID,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,Qty,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 13:17:12','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-24 13:17:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Forecast Store West','IP','Y',50060,50003,50007,50006,'MRP','FCT',50060,0,'D',TO_TIMESTAMP('2008-09-24 13:17:12','YYYY-MM-DD HH24:MI:SS'),100,'50044')
;

-- Sep 24, 2008 1:17:19 PM CDT
-- Manufacturing Demo
UPDATE M_ForecastLine SET Qty=2.000000000000,Updated=TO_TIMESTAMP('2008-09-24 13:17:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_ForecastLine_ID=50060
;

-- Sep 24, 2008 1:17:19 PM CDT
-- Manufacturing Demo
UPDATE PP_MRP SET AD_Org_ID=50006, DateFinishSchedule=TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), DateOrdered=TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), DatePromised=TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), DateStartSchedule=TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), Description='Forecast Store West', DocStatus='IP', M_Product_ID=50007, M_Warehouse_ID=50006, Name='MRP', Qty=2.000000000000,Updated=TO_TIMESTAMP('2008-09-24 13:17:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_MRP_ID=50060
;

-- Sep 24, 2008 1:17:24 PM CDT
-- Manufacturing Demo
UPDATE M_ForecastLine SET Qty=5.000000000000,Updated=TO_TIMESTAMP('2008-09-24 13:17:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_ForecastLine_ID=50059
;

-- Sep 24, 2008 1:17:24 PM CDT
-- Manufacturing Demo
UPDATE PP_MRP SET AD_Org_ID=50006, DateFinishSchedule=TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), DateOrdered=TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), DatePromised=TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), DateStartSchedule=TO_TIMESTAMP('2008-12-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), Description='Forecast Store West', DocStatus='IP', M_Product_ID=136, M_Warehouse_ID=50006, Name='MRP', Qty=5.000000000000,Updated=TO_TIMESTAMP('2008-09-24 13:17:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_MRP_ID=50059
;

-- Sep 24, 2008 1:29:42 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormat (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormat_ID,AD_PrintPaper_ID,AD_ReportView_ID,AD_Table_ID,Created,CreatedBy,FooterMargin,HeaderMargin,IsActive,IsDefault,IsForm,IsStandardHeaderFooter,IsTableBased,Name,Updated,UpdatedBy) VALUES (11,0,100,130,50036,100,53021,53142,TO_TIMESTAMP('2008-09-24 13:29:39','YYYY-MM-DD HH24:MI:SS'),100,0,0,'Y','N','N','Y','Y','Forecast Report &Copy Record 1630346308',TO_TIMESTAMP('2008-09-24 13:29:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 1:29:51 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56133,0,50944,50036,0,0,TO_TIMESTAMP('2008-09-24 13:29:43','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Client','C','F','Client',0,0,'N',0,TO_TIMESTAMP('2008-09-24 13:29:43','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:29:51 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50944 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:29:51 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56133) WHERE AD_PrintFormatItem_ID = 50944 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56133 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50944) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:29:54 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56115,0,50945,50036,0,0,TO_TIMESTAMP('2008-09-24 13:29:51','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Organization','C','F','Organization',0,0,'N',0,TO_TIMESTAMP('2008-09-24 13:29:51','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:29:54 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50945 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:29:54 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56115) WHERE AD_PrintFormatItem_ID = 50945 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56115 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50945) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:29:55 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56114,0,50946,50036,0,0,TO_TIMESTAMP('2008-09-24 13:29:54','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','Y','N','Y','Y','N','N','N','N','N','X',1,0,0,'Name','C','F','Name',0,1,'N',1,TO_TIMESTAMP('2008-09-24 13:29:54','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:29:55 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50946 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:29:55 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56114) WHERE AD_PrintFormatItem_ID = 50946 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56114 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50946) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:29:56 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56131,0,50947,50036,0,0,TO_TIMESTAMP('2008-09-24 13:29:55','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Calculated Quantity','C','F','Calculated Qty',0,2,'N',0,TO_TIMESTAMP('2008-09-24 13:29:55','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:29:56 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50947 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:29:56 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56131) WHERE AD_PrintFormatItem_ID = 50947 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56131 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50947) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:29:57 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56116,0,50948,50036,0,0,TO_TIMESTAMP('2008-09-24 13:29:56','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Calendar','C','F','Calendar',0,3,'N',0,TO_TIMESTAMP('2008-09-24 13:29:56','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:29:57 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50948 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:29:57 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56116) WHERE AD_PrintFormatItem_ID = 50948 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56116 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50948) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:29:58 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56120,0,50949,50036,0,0,TO_TIMESTAMP('2008-09-24 13:29:57','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Classification','C','F','Classification',0,4,'N',0,TO_TIMESTAMP('2008-09-24 13:29:57','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:29:58 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50949 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:29:58 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56120) WHERE AD_PrintFormatItem_ID = 50949 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56120 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50949) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:29:59 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56121,0,50950,50036,0,0,TO_TIMESTAMP('2008-09-24 13:29:58','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Date Promised','C','F','Date Promised',0,6,'N',0,TO_TIMESTAMP('2008-09-24 13:29:58','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:29:59 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50950 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:29:59 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56121) WHERE AD_PrintFormatItem_ID = 50950 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56121 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50950) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:30:07 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56124,0,50951,50036,0,0,TO_TIMESTAMP('2008-09-24 13:29:59','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Forecast','C','F','Forecast',0,7,'N',0,TO_TIMESTAMP('2008-09-24 13:29:59','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:30:07 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50951 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:30:07 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56124) WHERE AD_PrintFormatItem_ID = 50951 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56124 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50951) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:30:08 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56122,0,50952,50036,0,0,TO_TIMESTAMP('2008-09-24 13:30:07','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Group1','C','F','Group1',0,8,'N',0,TO_TIMESTAMP('2008-09-24 13:30:07','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:30:08 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50952 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:30:08 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56122) WHERE AD_PrintFormatItem_ID = 50952 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56122 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50952) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:30:10 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56123,0,50953,50036,0,0,TO_TIMESTAMP('2008-09-24 13:30:08','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Group2','C','F','Group2',0,9,'N',0,TO_TIMESTAMP('2008-09-24 13:30:08','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:30:10 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50953 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:30:10 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56123) WHERE AD_PrintFormatItem_ID = 50953 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56123 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50953) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:30:15 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56127,0,50954,50036,0,0,TO_TIMESTAMP('2008-09-24 13:30:10','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Limit Price','C','F','Limit Price',0,10,'N',0,TO_TIMESTAMP('2008-09-24 13:30:10','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:30:15 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50954 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:30:15 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56127) WHERE AD_PrintFormatItem_ID = 50954 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56127 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50954) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:30:24 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56128,0,50955,50036,0,0,TO_TIMESTAMP('2008-09-24 13:30:15','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'List Price','C','F','List Price',0,11,'N',0,TO_TIMESTAMP('2008-09-24 13:30:15','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:30:24 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50955 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:30:24 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56128) WHERE AD_PrintFormatItem_ID = 50955 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56128 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50955) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:30:25 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56117,0,50956,50036,0,0,TO_TIMESTAMP('2008-09-24 13:30:24','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Period','C','F','Period',0,13,'N',0,TO_TIMESTAMP('2008-09-24 13:30:24','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:30:25 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50956 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:30:25 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56117) WHERE AD_PrintFormatItem_ID = 50956 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56117 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50956) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:30:27 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56126,0,50957,50036,0,0,TO_TIMESTAMP('2008-09-24 13:30:25','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Product','C','F','Product',0,14,'N',0,TO_TIMESTAMP('2008-09-24 13:30:25','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:30:27 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50957 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:30:27 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56126) WHERE AD_PrintFormatItem_ID = 50957 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56126 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50957) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:30:29 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56125,0,50958,50036,0,0,TO_TIMESTAMP('2008-09-24 13:30:27','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Product Category','C','F','Product Category',0,15,'N',0,TO_TIMESTAMP('2008-09-24 13:30:27','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:30:29 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50958 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:30:29 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56125) WHERE AD_PrintFormatItem_ID = 50958 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56125 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50958) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:30:30 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56130,0,50959,50036,0,0,TO_TIMESTAMP('2008-09-24 13:30:29','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Quantity','C','F','Qty',0,16,'N',0,TO_TIMESTAMP('2008-09-24 13:30:29','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:30:30 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50959 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:30:30 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56130) WHERE AD_PrintFormatItem_ID = 50959 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56130 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50959) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:30:32 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56129,0,50960,50036,0,0,TO_TIMESTAMP('2008-09-24 13:30:30','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Standard Price','C','F','Std Price',0,17,'N',0,TO_TIMESTAMP('2008-09-24 13:30:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:30:32 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50960 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:30:32 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56129) WHERE AD_PrintFormatItem_ID = 50960 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56129 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50960) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:30:33 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56118,0,50961,50036,0,0,TO_TIMESTAMP('2008-09-24 13:30:32','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'UOM','C','F','UOM',0,18,'N',0,TO_TIMESTAMP('2008-09-24 13:30:32','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:30:33 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50961 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:30:33 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56118) WHERE AD_PrintFormatItem_ID = 50961 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56118 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50961) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:30:37 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56119,0,50962,50036,0,0,TO_TIMESTAMP('2008-09-24 13:30:33','YYYY-MM-DD HH24:MI:SS'),100,'L','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Year','C','F','Year',0,19,'N',0,TO_TIMESTAMP('2008-09-24 13:30:33','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:30:37 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50962 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:30:37 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56119) WHERE AD_PrintFormatItem_ID = 50962 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56119 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50962) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCentrallyMaintained,IsCounted,IsDeviationCalc,IsFilledRectangle,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsImageField,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (11,56134,0,50963,50036,0,0,TO_TIMESTAMP('2008-09-24 13:30:37','YYYY-MM-DD HH24:MI:SS'),100,'T','N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Total Amount','C','F','Total Amt',0,20,'N',0,TO_TIMESTAMP('2008-09-24 13:30:37','YYYY-MM-DD HH24:MI:SS'),100,0,0,0,0)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50963 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName = (SELECT e.PrintName FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56134) WHERE AD_PrintFormatItem_ID = 50963 AND EXISTS (SELECT * FROM AD_Element_Trl e, AD_Column c WHERE e.AD_Language=AD_PrintFormatItem_Trl.AD_Language AND e.AD_Element_ID=c.AD_Element_ID AND c.AD_Column_ID=56134 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID = 50963) AND EXISTS (SELECT * FROM AD_Client WHERE AD_Client_ID=AD_PrintFormatItem_Trl.AD_Client_ID AND IsMultiLingualDocument='Y')
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50788 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50944 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50788)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50789 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50945 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50789)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50790 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50946 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50790)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50791 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50947 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50791)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50792 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50948 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50792)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50793 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50949 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50793)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50794 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50950 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50794)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50795 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50951 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50795)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50796 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50952 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50796)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50797 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50953 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50797)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50798 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50954 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50798)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50799 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50955 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50799)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50800 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50956 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50800)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50801 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50957 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50801)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50802 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50958 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50802)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50803 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50959 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50803)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50804 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50960 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50804)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50805 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50961 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50805)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50806 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50962 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50806)
;

-- Sep 24, 2008 1:30:40 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem_Trl SET PrintName="old".PrintName,PrintNameSuffix="old".PrintNameSuffix,IsTranslated="old".IsTranslated FROM AD_PrintFormatItem_Trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND "old".AD_PrintFormatItem_ID =50807 AND AD_PrintFormatItem_Trl.AD_PrintFormatItem_ID=50963 AND EXISTS (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem_trl "old" WHERE "old".AD_Language=AD_PrintFormatItem_Trl.AD_Language AND AD_PrintFormatItem_ID =50807)
;

-- Sep 24, 2008 1:33:49 PM CDT
-- Manufacturing Demo
UPDATE M_Product_PO SET PriceList = 0 WHERE PriceList IS NULL
;

-- Sep 24, 2008 1:33:49 PM CDT
-- Manufacturing Demo
UPDATE M_Product_PO SET PriceLastPO = 0 WHERE PriceLastPO IS NULL
;

-- Sep 24, 2008 1:33:49 PM CDT
-- Manufacturing Demo
UPDATE M_Product_PO SET PricePO = PriceLastPO WHERE (PricePO IS NULL OR PricePO = 0) AND PriceLastPO <> 0
;

-- Sep 24, 2008 1:33:49 PM CDT
-- Manufacturing Demo
UPDATE M_Product_PO SET PricePO = 0 WHERE PricePO IS NULL
;

-- Sep 24, 2008 1:33:49 PM CDT
-- Manufacturing Demo
UPDATE M_Product_PO SET IsCurrentVendor = 'Y' WHERE IsCurrentVendor = 'N' AND NOT EXISTS (SELECT pp.M_Product_ID FROM M_Product_PO pp WHERE pp.M_Product_ID = M_Product_PO.M_Product_ID GROUP BY pp.M_Product_ID HAVING COUNT(*) > 1)
;

-- Sep 24, 2008 1:33:49 PM CDT
-- Manufacturing Demo
DELETE FROM M_ProductPrice WHERE M_ProductPrice.M_PriceList_Version_ID = 103 AND EXISTS (SELECT t_selection_id FROM T_Selection s WHERE M_ProductPrice.M_Product_ID=s.T_Selection_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'C'='N' THEN PriceList WHEN 'C'='0' THEN ROUND(PriceList, 0) WHEN 'C'='D' THEN ROUND(PriceList, 1) WHEN 'C'='T' THEN ROUND(PriceList, -1) WHEN 'C'='5' THEN ROUND(PriceList*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceList*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceList),10)<=5 THEN ROUND(PriceList)+(5-MOD(ROUND(PriceList),10)) WHEN MOD(ROUND(PriceList),10)>5 THEN ROUND(PriceList)+(9-MOD(ROUND(PriceList),10)) END ELSE ROUND(PriceList, 2) END, PriceStd = CASE WHEN 'C'='N' THEN PriceStd WHEN 'C'='0' THEN ROUND(PriceStd, 0) WHEN 'C'='D' THEN ROUND(PriceStd, 1) WHEN 'C'='T' THEN ROUND(PriceStd, -1) WHEN 'C'='5' THEN ROUND(PriceStd*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceStd*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceStd),10)<=5 THEN ROUND(PriceStd)+(5-MOD(ROUND(PriceStd),10)) WHEN MOD(ROUND(PriceStd),10)>5 THEN ROUND(PriceStd)+(9-MOD(ROUND(PriceStd),10)) END ELSE ROUND(PriceStd, 2) END,PriceLimit = CASE WHEN 'C'='N' THEN PriceLimit WHEN 'C'='0' THEN ROUND(PriceLimit, 0) WHEN 'C'='D' THEN ROUND(PriceLimit, 1) WHEN 'C'='T' THEN ROUND(PriceLimit, -1) WHEN 'C'='5' THEN ROUND(PriceLimit*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceLimit*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceLimit),10)<=5 THEN ROUND(PriceLimit)+(5-MOD(ROUND(PriceLimit),10)) WHEN MOD(ROUND(PriceLimit),10)>5 THEN ROUND(PriceLimit)+(9-MOD(ROUND(PriceLimit),10)) END ELSE ROUND(PriceLimit, 2) END WHERE M_PriceList_Version_ID=103 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceList END, PriceStd = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceStd END, PriceLimit = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceLimit END WHERE M_PriceList_Version_ID=103 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
DELETE FROM M_ProductPrice WHERE M_ProductPrice.M_PriceList_Version_ID = 103 AND EXISTS (SELECT t_selection_id FROM T_Selection s WHERE M_ProductPrice.M_Product_ID=s.T_Selection_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'C'='N' THEN PriceList WHEN 'C'='0' THEN ROUND(PriceList, 0) WHEN 'C'='D' THEN ROUND(PriceList, 1) WHEN 'C'='T' THEN ROUND(PriceList, -1) WHEN 'C'='5' THEN ROUND(PriceList*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceList*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceList),10)<=5 THEN ROUND(PriceList)+(5-MOD(ROUND(PriceList),10)) WHEN MOD(ROUND(PriceList),10)>5 THEN ROUND(PriceList)+(9-MOD(ROUND(PriceList),10)) END ELSE ROUND(PriceList, 2) END, PriceStd = CASE WHEN 'C'='N' THEN PriceStd WHEN 'C'='0' THEN ROUND(PriceStd, 0) WHEN 'C'='D' THEN ROUND(PriceStd, 1) WHEN 'C'='T' THEN ROUND(PriceStd, -1) WHEN 'C'='5' THEN ROUND(PriceStd*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceStd*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceStd),10)<=5 THEN ROUND(PriceStd)+(5-MOD(ROUND(PriceStd),10)) WHEN MOD(ROUND(PriceStd),10)>5 THEN ROUND(PriceStd)+(9-MOD(ROUND(PriceStd),10)) END ELSE ROUND(PriceStd, 2) END,PriceLimit = CASE WHEN 'C'='N' THEN PriceLimit WHEN 'C'='0' THEN ROUND(PriceLimit, 0) WHEN 'C'='D' THEN ROUND(PriceLimit, 1) WHEN 'C'='T' THEN ROUND(PriceLimit, -1) WHEN 'C'='5' THEN ROUND(PriceLimit*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceLimit*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceLimit),10)<=5 THEN ROUND(PriceLimit)+(5-MOD(ROUND(PriceLimit),10)) WHEN MOD(ROUND(PriceLimit),10)>5 THEN ROUND(PriceLimit)+(9-MOD(ROUND(PriceLimit),10)) END ELSE ROUND(PriceLimit, 2) END WHERE M_PriceList_Version_ID=103 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceList END, PriceStd = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceStd END, PriceLimit = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceLimit END WHERE M_PriceList_Version_ID=103 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
DELETE FROM M_ProductPrice WHERE M_ProductPrice.M_PriceList_Version_ID = 103 AND EXISTS (SELECT t_selection_id FROM T_Selection s WHERE M_ProductPrice.M_Product_ID=s.T_Selection_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'C'='N' THEN PriceList WHEN 'C'='0' THEN ROUND(PriceList, 0) WHEN 'C'='D' THEN ROUND(PriceList, 1) WHEN 'C'='T' THEN ROUND(PriceList, -1) WHEN 'C'='5' THEN ROUND(PriceList*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceList*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceList),10)<=5 THEN ROUND(PriceList)+(5-MOD(ROUND(PriceList),10)) WHEN MOD(ROUND(PriceList),10)>5 THEN ROUND(PriceList)+(9-MOD(ROUND(PriceList),10)) END ELSE ROUND(PriceList, 2) END, PriceStd = CASE WHEN 'C'='N' THEN PriceStd WHEN 'C'='0' THEN ROUND(PriceStd, 0) WHEN 'C'='D' THEN ROUND(PriceStd, 1) WHEN 'C'='T' THEN ROUND(PriceStd, -1) WHEN 'C'='5' THEN ROUND(PriceStd*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceStd*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceStd),10)<=5 THEN ROUND(PriceStd)+(5-MOD(ROUND(PriceStd),10)) WHEN MOD(ROUND(PriceStd),10)>5 THEN ROUND(PriceStd)+(9-MOD(ROUND(PriceStd),10)) END ELSE ROUND(PriceStd, 2) END,PriceLimit = CASE WHEN 'C'='N' THEN PriceLimit WHEN 'C'='0' THEN ROUND(PriceLimit, 0) WHEN 'C'='D' THEN ROUND(PriceLimit, 1) WHEN 'C'='T' THEN ROUND(PriceLimit, -1) WHEN 'C'='5' THEN ROUND(PriceLimit*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceLimit*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceLimit),10)<=5 THEN ROUND(PriceLimit)+(5-MOD(ROUND(PriceLimit),10)) WHEN MOD(ROUND(PriceLimit),10)>5 THEN ROUND(PriceLimit)+(9-MOD(ROUND(PriceLimit),10)) END ELSE ROUND(PriceLimit, 2) END WHERE M_PriceList_Version_ID=103 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceList END, PriceStd = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceStd END, PriceLimit = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceLimit END WHERE M_PriceList_Version_ID=103 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
DELETE FROM M_ProductPrice WHERE M_ProductPrice.M_PriceList_Version_ID = 103 AND EXISTS (SELECT t_selection_id FROM T_Selection s WHERE M_ProductPrice.M_Product_ID=s.T_Selection_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'C'='N' THEN PriceList WHEN 'C'='0' THEN ROUND(PriceList, 0) WHEN 'C'='D' THEN ROUND(PriceList, 1) WHEN 'C'='T' THEN ROUND(PriceList, -1) WHEN 'C'='5' THEN ROUND(PriceList*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceList*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceList),10)<=5 THEN ROUND(PriceList)+(5-MOD(ROUND(PriceList),10)) WHEN MOD(ROUND(PriceList),10)>5 THEN ROUND(PriceList)+(9-MOD(ROUND(PriceList),10)) END ELSE ROUND(PriceList, 2) END, PriceStd = CASE WHEN 'C'='N' THEN PriceStd WHEN 'C'='0' THEN ROUND(PriceStd, 0) WHEN 'C'='D' THEN ROUND(PriceStd, 1) WHEN 'C'='T' THEN ROUND(PriceStd, -1) WHEN 'C'='5' THEN ROUND(PriceStd*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceStd*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceStd),10)<=5 THEN ROUND(PriceStd)+(5-MOD(ROUND(PriceStd),10)) WHEN MOD(ROUND(PriceStd),10)>5 THEN ROUND(PriceStd)+(9-MOD(ROUND(PriceStd),10)) END ELSE ROUND(PriceStd, 2) END,PriceLimit = CASE WHEN 'C'='N' THEN PriceLimit WHEN 'C'='0' THEN ROUND(PriceLimit, 0) WHEN 'C'='D' THEN ROUND(PriceLimit, 1) WHEN 'C'='T' THEN ROUND(PriceLimit, -1) WHEN 'C'='5' THEN ROUND(PriceLimit*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceLimit*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceLimit),10)<=5 THEN ROUND(PriceLimit)+(5-MOD(ROUND(PriceLimit),10)) WHEN MOD(ROUND(PriceLimit),10)>5 THEN ROUND(PriceLimit)+(9-MOD(ROUND(PriceLimit),10)) END ELSE ROUND(PriceLimit, 2) END WHERE M_PriceList_Version_ID=103 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceList END, PriceStd = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceStd END, PriceLimit = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceLimit END WHERE M_PriceList_Version_ID=103 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
DELETE FROM M_ProductPrice WHERE M_ProductPrice.M_PriceList_Version_ID = 103 AND EXISTS (SELECT t_selection_id FROM T_Selection s WHERE M_ProductPrice.M_Product_ID=s.T_Selection_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'C'='N' THEN PriceList WHEN 'C'='0' THEN ROUND(PriceList, 0) WHEN 'C'='D' THEN ROUND(PriceList, 1) WHEN 'C'='T' THEN ROUND(PriceList, -1) WHEN 'C'='5' THEN ROUND(PriceList*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceList*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceList),10)<=5 THEN ROUND(PriceList)+(5-MOD(ROUND(PriceList),10)) WHEN MOD(ROUND(PriceList),10)>5 THEN ROUND(PriceList)+(9-MOD(ROUND(PriceList),10)) END ELSE ROUND(PriceList, 2) END, PriceStd = CASE WHEN 'C'='N' THEN PriceStd WHEN 'C'='0' THEN ROUND(PriceStd, 0) WHEN 'C'='D' THEN ROUND(PriceStd, 1) WHEN 'C'='T' THEN ROUND(PriceStd, -1) WHEN 'C'='5' THEN ROUND(PriceStd*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceStd*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceStd),10)<=5 THEN ROUND(PriceStd)+(5-MOD(ROUND(PriceStd),10)) WHEN MOD(ROUND(PriceStd),10)>5 THEN ROUND(PriceStd)+(9-MOD(ROUND(PriceStd),10)) END ELSE ROUND(PriceStd, 2) END,PriceLimit = CASE WHEN 'C'='N' THEN PriceLimit WHEN 'C'='0' THEN ROUND(PriceLimit, 0) WHEN 'C'='D' THEN ROUND(PriceLimit, 1) WHEN 'C'='T' THEN ROUND(PriceLimit, -1) WHEN 'C'='5' THEN ROUND(PriceLimit*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceLimit*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceLimit),10)<=5 THEN ROUND(PriceLimit)+(5-MOD(ROUND(PriceLimit),10)) WHEN MOD(ROUND(PriceLimit),10)>5 THEN ROUND(PriceLimit)+(9-MOD(ROUND(PriceLimit),10)) END ELSE ROUND(PriceLimit, 2) END WHERE M_PriceList_Version_ID=103 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceList END, PriceStd = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceStd END, PriceLimit = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceLimit END WHERE M_PriceList_Version_ID=103 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
DELETE FROM M_ProductPrice WHERE M_ProductPrice.M_PriceList_Version_ID = 103 AND EXISTS (SELECT t_selection_id FROM T_Selection s WHERE M_ProductPrice.M_Product_ID=s.T_Selection_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'C'='N' THEN PriceList WHEN 'C'='0' THEN ROUND(PriceList, 0) WHEN 'C'='D' THEN ROUND(PriceList, 1) WHEN 'C'='T' THEN ROUND(PriceList, -1) WHEN 'C'='5' THEN ROUND(PriceList*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceList*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceList),10)<=5 THEN ROUND(PriceList)+(5-MOD(ROUND(PriceList),10)) WHEN MOD(ROUND(PriceList),10)>5 THEN ROUND(PriceList)+(9-MOD(ROUND(PriceList),10)) END ELSE ROUND(PriceList, 2) END, PriceStd = CASE WHEN 'C'='N' THEN PriceStd WHEN 'C'='0' THEN ROUND(PriceStd, 0) WHEN 'C'='D' THEN ROUND(PriceStd, 1) WHEN 'C'='T' THEN ROUND(PriceStd, -1) WHEN 'C'='5' THEN ROUND(PriceStd*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceStd*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceStd),10)<=5 THEN ROUND(PriceStd)+(5-MOD(ROUND(PriceStd),10)) WHEN MOD(ROUND(PriceStd),10)>5 THEN ROUND(PriceStd)+(9-MOD(ROUND(PriceStd),10)) END ELSE ROUND(PriceStd, 2) END,PriceLimit = CASE WHEN 'C'='N' THEN PriceLimit WHEN 'C'='0' THEN ROUND(PriceLimit, 0) WHEN 'C'='D' THEN ROUND(PriceLimit, 1) WHEN 'C'='T' THEN ROUND(PriceLimit, -1) WHEN 'C'='5' THEN ROUND(PriceLimit*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceLimit*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceLimit),10)<=5 THEN ROUND(PriceLimit)+(5-MOD(ROUND(PriceLimit),10)) WHEN MOD(ROUND(PriceLimit),10)>5 THEN ROUND(PriceLimit)+(9-MOD(ROUND(PriceLimit),10)) END ELSE ROUND(PriceLimit, 2) END WHERE M_PriceList_Version_ID=103 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceList END, PriceStd = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceStd END, PriceLimit = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceLimit END WHERE M_PriceList_Version_ID=103 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50020)
;

-- Sep 24, 2008 1:33:50 PM CDT
-- Manufacturing Demo
DELETE FROM T_Selection
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
UPDATE M_Product_PO SET PriceList = 0 WHERE PriceList IS NULL
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
UPDATE M_Product_PO SET PriceLastPO = 0 WHERE PriceLastPO IS NULL
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
UPDATE M_Product_PO SET PricePO = PriceLastPO WHERE (PricePO IS NULL OR PricePO = 0) AND PriceLastPO <> 0
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
UPDATE M_Product_PO SET PricePO = 0 WHERE PricePO IS NULL
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
UPDATE M_Product_PO SET IsCurrentVendor = 'Y' WHERE IsCurrentVendor = 'N' AND NOT EXISTS (SELECT pp.M_Product_ID FROM M_Product_PO pp WHERE pp.M_Product_ID = M_Product_PO.M_Product_ID GROUP BY pp.M_Product_ID HAVING COUNT(*) > 1)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
DELETE FROM M_ProductPrice WHERE M_ProductPrice.M_PriceList_Version_ID = 104 AND EXISTS (SELECT t_selection_id FROM T_Selection s WHERE M_ProductPrice.M_Product_ID=s.T_Selection_ID AND s.AD_PInstance_ID=50021)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'C'='N' THEN PriceList WHEN 'C'='0' THEN ROUND(PriceList, 0) WHEN 'C'='D' THEN ROUND(PriceList, 1) WHEN 'C'='T' THEN ROUND(PriceList, -1) WHEN 'C'='5' THEN ROUND(PriceList*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceList*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceList),10)<=5 THEN ROUND(PriceList)+(5-MOD(ROUND(PriceList),10)) WHEN MOD(ROUND(PriceList),10)>5 THEN ROUND(PriceList)+(9-MOD(ROUND(PriceList),10)) END ELSE ROUND(PriceList, 2) END, PriceStd = CASE WHEN 'C'='N' THEN PriceStd WHEN 'C'='0' THEN ROUND(PriceStd, 0) WHEN 'C'='D' THEN ROUND(PriceStd, 1) WHEN 'C'='T' THEN ROUND(PriceStd, -1) WHEN 'C'='5' THEN ROUND(PriceStd*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceStd*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceStd),10)<=5 THEN ROUND(PriceStd)+(5-MOD(ROUND(PriceStd),10)) WHEN MOD(ROUND(PriceStd),10)>5 THEN ROUND(PriceStd)+(9-MOD(ROUND(PriceStd),10)) END ELSE ROUND(PriceStd, 2) END,PriceLimit = CASE WHEN 'C'='N' THEN PriceLimit WHEN 'C'='0' THEN ROUND(PriceLimit, 0) WHEN 'C'='D' THEN ROUND(PriceLimit, 1) WHEN 'C'='T' THEN ROUND(PriceLimit, -1) WHEN 'C'='5' THEN ROUND(PriceLimit*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceLimit*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceLimit),10)<=5 THEN ROUND(PriceLimit)+(5-MOD(ROUND(PriceLimit),10)) WHEN MOD(ROUND(PriceLimit),10)>5 THEN ROUND(PriceLimit)+(9-MOD(ROUND(PriceLimit),10)) END ELSE ROUND(PriceLimit, 2) END WHERE M_PriceList_Version_ID=104 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50021)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceList END, PriceStd = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceStd END, PriceLimit = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceLimit END WHERE M_PriceList_Version_ID=104 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50021)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
DELETE FROM M_ProductPrice WHERE M_ProductPrice.M_PriceList_Version_ID = 104 AND EXISTS (SELECT t_selection_id FROM T_Selection s WHERE M_ProductPrice.M_Product_ID=s.T_Selection_ID AND s.AD_PInstance_ID=50021)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'C'='N' THEN PriceList WHEN 'C'='0' THEN ROUND(PriceList, 0) WHEN 'C'='D' THEN ROUND(PriceList, 1) WHEN 'C'='T' THEN ROUND(PriceList, -1) WHEN 'C'='5' THEN ROUND(PriceList*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceList*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceList),10)<=5 THEN ROUND(PriceList)+(5-MOD(ROUND(PriceList),10)) WHEN MOD(ROUND(PriceList),10)>5 THEN ROUND(PriceList)+(9-MOD(ROUND(PriceList),10)) END ELSE ROUND(PriceList, 2) END, PriceStd = CASE WHEN 'C'='N' THEN PriceStd WHEN 'C'='0' THEN ROUND(PriceStd, 0) WHEN 'C'='D' THEN ROUND(PriceStd, 1) WHEN 'C'='T' THEN ROUND(PriceStd, -1) WHEN 'C'='5' THEN ROUND(PriceStd*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceStd*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceStd),10)<=5 THEN ROUND(PriceStd)+(5-MOD(ROUND(PriceStd),10)) WHEN MOD(ROUND(PriceStd),10)>5 THEN ROUND(PriceStd)+(9-MOD(ROUND(PriceStd),10)) END ELSE ROUND(PriceStd, 2) END,PriceLimit = CASE WHEN 'C'='N' THEN PriceLimit WHEN 'C'='0' THEN ROUND(PriceLimit, 0) WHEN 'C'='D' THEN ROUND(PriceLimit, 1) WHEN 'C'='T' THEN ROUND(PriceLimit, -1) WHEN 'C'='5' THEN ROUND(PriceLimit*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceLimit*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceLimit),10)<=5 THEN ROUND(PriceLimit)+(5-MOD(ROUND(PriceLimit),10)) WHEN MOD(ROUND(PriceLimit),10)>5 THEN ROUND(PriceLimit)+(9-MOD(ROUND(PriceLimit),10)) END ELSE ROUND(PriceLimit, 2) END WHERE M_PriceList_Version_ID=104 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50021)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceList END, PriceStd = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceStd END, PriceLimit = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceLimit END WHERE M_PriceList_Version_ID=104 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50021)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
DELETE FROM M_ProductPrice WHERE M_ProductPrice.M_PriceList_Version_ID = 104 AND EXISTS (SELECT t_selection_id FROM T_Selection s WHERE M_ProductPrice.M_Product_ID=s.T_Selection_ID AND s.AD_PInstance_ID=50021)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'C'='N' THEN PriceList WHEN 'C'='0' THEN ROUND(PriceList, 0) WHEN 'C'='D' THEN ROUND(PriceList, 1) WHEN 'C'='T' THEN ROUND(PriceList, -1) WHEN 'C'='5' THEN ROUND(PriceList*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceList*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceList),10)<=5 THEN ROUND(PriceList)+(5-MOD(ROUND(PriceList),10)) WHEN MOD(ROUND(PriceList),10)>5 THEN ROUND(PriceList)+(9-MOD(ROUND(PriceList),10)) END ELSE ROUND(PriceList, 2) END, PriceStd = CASE WHEN 'C'='N' THEN PriceStd WHEN 'C'='0' THEN ROUND(PriceStd, 0) WHEN 'C'='D' THEN ROUND(PriceStd, 1) WHEN 'C'='T' THEN ROUND(PriceStd, -1) WHEN 'C'='5' THEN ROUND(PriceStd*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceStd*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceStd),10)<=5 THEN ROUND(PriceStd)+(5-MOD(ROUND(PriceStd),10)) WHEN MOD(ROUND(PriceStd),10)>5 THEN ROUND(PriceStd)+(9-MOD(ROUND(PriceStd),10)) END ELSE ROUND(PriceStd, 2) END,PriceLimit = CASE WHEN 'C'='N' THEN PriceLimit WHEN 'C'='0' THEN ROUND(PriceLimit, 0) WHEN 'C'='D' THEN ROUND(PriceLimit, 1) WHEN 'C'='T' THEN ROUND(PriceLimit, -1) WHEN 'C'='5' THEN ROUND(PriceLimit*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceLimit*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceLimit),10)<=5 THEN ROUND(PriceLimit)+(5-MOD(ROUND(PriceLimit),10)) WHEN MOD(ROUND(PriceLimit),10)>5 THEN ROUND(PriceLimit)+(9-MOD(ROUND(PriceLimit),10)) END ELSE ROUND(PriceLimit, 2) END WHERE M_PriceList_Version_ID=104 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50021)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceList END, PriceStd = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceStd END, PriceLimit = CASE WHEN 'S'='F' THEN 0.0 ELSE PriceLimit END WHERE M_PriceList_Version_ID=104 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50021)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
DELETE FROM M_ProductPrice WHERE M_ProductPrice.M_PriceList_Version_ID = 104 AND EXISTS (SELECT t_selection_id FROM T_Selection s WHERE M_ProductPrice.M_Product_ID=s.T_Selection_ID AND s.AD_PInstance_ID=50021)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'C'='N' THEN PriceList WHEN 'C'='0' THEN ROUND(PriceList, 0) WHEN 'C'='D' THEN ROUND(PriceList, 1) WHEN 'C'='T' THEN ROUND(PriceList, -1) WHEN 'C'='5' THEN ROUND(PriceList*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceList*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceList),10)<=5 THEN ROUND(PriceList)+(5-MOD(ROUND(PriceList),10)) WHEN MOD(ROUND(PriceList),10)>5 THEN ROUND(PriceList)+(9-MOD(ROUND(PriceList),10)) END ELSE ROUND(PriceList, 2) END, PriceStd = CASE WHEN 'C'='N' THEN PriceStd WHEN 'C'='0' THEN ROUND(PriceStd, 0) WHEN 'C'='D' THEN ROUND(PriceStd, 1) WHEN 'C'='T' THEN ROUND(PriceStd, -1) WHEN 'C'='5' THEN ROUND(PriceStd*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceStd*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceStd),10)<=5 THEN ROUND(PriceStd)+(5-MOD(ROUND(PriceStd),10)) WHEN MOD(ROUND(PriceStd),10)>5 THEN ROUND(PriceStd)+(9-MOD(ROUND(PriceStd),10)) END ELSE ROUND(PriceStd, 2) END,PriceLimit = CASE WHEN 'C'='N' THEN PriceLimit WHEN 'C'='0' THEN ROUND(PriceLimit, 0) WHEN 'C'='D' THEN ROUND(PriceLimit, 1) WHEN 'C'='T' THEN ROUND(PriceLimit, -1) WHEN 'C'='5' THEN ROUND(PriceLimit*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceLimit*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceLimit),10)<=5 THEN ROUND(PriceLimit)+(5-MOD(ROUND(PriceLimit),10)) WHEN MOD(ROUND(PriceLimit),10)>5 THEN ROUND(PriceLimit)+(9-MOD(ROUND(PriceLimit),10)) END ELSE ROUND(PriceLimit, 2) END WHERE M_PriceList_Version_ID=104 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50021)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceList END, PriceStd = CASE WHEN 'S'='F' THEN 0.0 ELSE PriceStd END, PriceLimit = CASE WHEN 'S'='F' THEN 0.0 ELSE PriceLimit END WHERE M_PriceList_Version_ID=104 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50021)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
DELETE FROM M_ProductPrice WHERE M_ProductPrice.M_PriceList_Version_ID = 104 AND EXISTS (SELECT t_selection_id FROM T_Selection s WHERE M_ProductPrice.M_Product_ID=s.T_Selection_ID AND s.AD_PInstance_ID=50021)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'C'='N' THEN PriceList WHEN 'C'='0' THEN ROUND(PriceList, 0) WHEN 'C'='D' THEN ROUND(PriceList, 1) WHEN 'C'='T' THEN ROUND(PriceList, -1) WHEN 'C'='5' THEN ROUND(PriceList*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceList*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceList),10)<=5 THEN ROUND(PriceList)+(5-MOD(ROUND(PriceList),10)) WHEN MOD(ROUND(PriceList),10)>5 THEN ROUND(PriceList)+(9-MOD(ROUND(PriceList),10)) END ELSE ROUND(PriceList, 2) END, PriceStd = CASE WHEN 'C'='N' THEN PriceStd WHEN 'C'='0' THEN ROUND(PriceStd, 0) WHEN 'C'='D' THEN ROUND(PriceStd, 1) WHEN 'C'='T' THEN ROUND(PriceStd, -1) WHEN 'C'='5' THEN ROUND(PriceStd*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceStd*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceStd),10)<=5 THEN ROUND(PriceStd)+(5-MOD(ROUND(PriceStd),10)) WHEN MOD(ROUND(PriceStd),10)>5 THEN ROUND(PriceStd)+(9-MOD(ROUND(PriceStd),10)) END ELSE ROUND(PriceStd, 2) END,PriceLimit = CASE WHEN 'C'='N' THEN PriceLimit WHEN 'C'='0' THEN ROUND(PriceLimit, 0) WHEN 'C'='D' THEN ROUND(PriceLimit, 1) WHEN 'C'='T' THEN ROUND(PriceLimit, -1) WHEN 'C'='5' THEN ROUND(PriceLimit*20,0)/20 WHEN 'C'='Q' THEN ROUND(PriceLimit*4,0)/4 WHEN 'C'='9' THEN CASE WHEN MOD(ROUND(PriceLimit),10)<=5 THEN ROUND(PriceLimit)+(5-MOD(ROUND(PriceLimit),10)) WHEN MOD(ROUND(PriceLimit),10)>5 THEN ROUND(PriceLimit)+(9-MOD(ROUND(PriceLimit),10)) END ELSE ROUND(PriceLimit, 2) END WHERE M_PriceList_Version_ID=104 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50021)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceList = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceList END, PriceStd = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceStd END, PriceLimit = CASE WHEN 'L'='F' THEN 0.0 ELSE PriceLimit END WHERE M_PriceList_Version_ID=104 AND EXISTS (SELECT * FROM T_Selection s WHERE s.T_Selection_ID=M_ProductPrice.M_Product_ID AND s.AD_PInstance_ID=50021)
;

-- Sep 24, 2008 1:34:00 PM CDT
-- Manufacturing Demo
DELETE FROM T_Selection
;

-- Sep 24, 2008 1:35:34 PM CDT
-- Manufacturing Demo
INSERT INTO M_ProductPrice (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,M_PriceList_Version_ID,M_Product_ID,PriceLimit,PriceList,PriceStd,Updated,UpdatedBy) VALUES (11,0,TO_TIMESTAMP('2008-09-24 13:35:34','YYYY-MM-DD HH24:MI:SS'),100,'Y',104,50007,30.000000000000,35.000000000000,33.000000000000,TO_TIMESTAMP('2008-09-24 13:35:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50947
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50948
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50949
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50951
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50952
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50953
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50954
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50955
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50946
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50958
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=0,IsPrinted='N' WHERE AD_PrintFormatItem_ID=50962
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=10,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50944
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=20,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50945
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=30,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50956
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=40,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50950
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=50,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50957
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=60,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50961
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=70,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50959
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=80,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50960
;

-- Sep 24, 2008 1:37:13 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SeqNo=90,IsPrinted='Y' WHERE AD_PrintFormatItem_ID=50963
;

-- Sep 24, 2008 1:45:59 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET IsFixedWidth='Y', IsGroupBy='N', IsPageBreak='N', MaxWidth=100, SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-09-24 13:45:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=50950
;

-- Sep 24, 2008 1:46:09 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsPageBreak='N', IsSummarized='Y', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-09-24 13:46:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=50963
;

-- Sep 24, 2008 1:47:08 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET IsGroupBy='N', IsHeightOneLine='Y', IsPageBreak='N', SortNo=0, XPosition=0, YPosition=0,Updated=TO_TIMESTAMP('2008-09-24 13:47:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_PrintFormatItem_ID=50950
;

-- Sep 24, 2008 1:48:15 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceLimit=496.000000000000, PriceList=520.000000000000, PriceStd=500.000000000000,Updated=TO_TIMESTAMP('2008-09-24 13:48:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_PriceList_Version_ID=103 AND M_Product_ID=145
;

-- Sep 24, 2008 1:48:54 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SortNo=0,IsOrderBy='N' WHERE AD_PrintFormatItem_ID=50946
;

-- Sep 24, 2008 1:48:54 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SortNo=10,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=50956
;

-- Sep 24, 2008 1:48:54 PM CDT
-- Manufacturing Demo
UPDATE AD_PrintFormatItem SET SortNo=20,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=50957
;

-- Sep 24, 2008 1:49:27 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceLimit=0, PriceList=0, PriceStd=0,Updated=TO_TIMESTAMP('2008-09-24 13:49:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_PriceList_Version_ID=103 AND M_Product_ID=145
;

-- Sep 24, 2008 1:49:44 PM CDT
-- Manufacturing Demo
UPDATE M_ProductPrice SET PriceLimit=496.000000000000, PriceList=520.000000000000, PriceStd=500.000000000000,Updated=TO_TIMESTAMP('2008-09-24 13:49:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_PriceList_Version_ID=104 AND M_Product_ID=145
;

-- Sep 24, 2008 2:44:15 PM CDT
-- Manufacturing Demo
INSERT INTO C_BPartner (AD_Client_ID,AD_Language,AD_Org_ID,AcqusitionCost,ActualLifeTimeValue,C_BP_Group_ID,C_BPartner_ID,C_Dunning_ID,C_PaymentTerm_ID,Created,CreatedBy,DocumentCopies,FlatDiscount,IsActive,IsCustomer,IsDiscountPrinted,IsEmployee,IsOneTime,IsProspect,IsSalesRep,IsSummary,IsTaxExempt,IsVendor,M_PriceList_ID,Name,NumberEmployees,PotentialLifeTimeValue,SOCreditStatus,SO_CreditLimit,SO_CreditUsed,SalesRep_ID,SalesVolume,SendEMail,ShareOfCustomer,ShelfLifeMinPct,TotalOpenBalance,Updated,UpdatedBy,Value) VALUES (11,'en_US',0,0,0,105,50004,100,105,TO_TIMESTAMP('2008-09-24 14:44:04','YYYY-MM-DD HH24:MI:SS'),100,0,0,'Y','N','N','N','N','N','N','N','N','N',101,'Store Central',0,0,'X',0,0,100,0,'N',0,0,0,TO_TIMESTAMP('2008-09-24 14:44:04','YYYY-MM-DD HH24:MI:SS'),100,'10000000')
;

-- Sep 24, 2008 2:44:15 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodeBP (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50004, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='BP' AND NOT EXISTS (SELECT * FROM AD_TreeNodeBP e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50004)
;

-- Sep 24, 2008 2:44:15 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Customer_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,C_Prepayment_Acct,C_Receivable_Acct,C_Receivable_Services_Acct) SELECT 50004, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.C_Prepayment_Acct,p.C_Receivable_Acct,p.C_Receivable_Services_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=105 AND NOT EXISTS (SELECT * FROM C_BP_Customer_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50004)
;

-- Sep 24, 2008 2:44:15 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Vendor_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,V_Liability_Acct,V_Liability_Services_Acct,V_Prepayment_Acct) SELECT 50004, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.V_Liability_Acct,p.V_Liability_Services_Acct,p.V_Prepayment_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=105 AND NOT EXISTS (SELECT * FROM C_BP_Vendor_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50004)
;

-- Sep 24, 2008 2:44:15 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Employee_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,E_Expense_Acct,E_Prepayment_Acct) SELECT 50004, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.E_Expense_Acct,p.E_Prepayment_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM C_BP_Employee_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50004)
;

-- Sep 24, 2008 2:44:16 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Process (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50004,101,131,TO_TIMESTAMP('2008-09-24 14:44:15','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50004,TO_TIMESTAMP('2008-09-24 14:44:15','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:44:16 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:44:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50004
;

-- Sep 24, 2008 2:44:17 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Activity (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Activity_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50004,244,50004,100,131,TO_TIMESTAMP('2008-09-24 14:44:16','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50004,TO_TIMESTAMP('2008-09-24 14:44:16','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:44:19 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_EventAudit (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_EventAudit_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,Created,CreatedBy,ElapsedTimeMS,EventType,IsActive,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50004,244,50004,100,TO_TIMESTAMP('2008-09-24 14:44:18','YYYY-MM-DD HH24:MI:SS'),100,0,'PC','Y',50004,TO_TIMESTAMP('2008-09-24 14:44:18','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:44:19 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:44:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50004
;

-- Sep 24, 2008 2:44:19 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:44:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50004
;

-- Sep 24, 2008 2:44:19 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:44:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50004
;

-- Sep 24, 2008 2:44:19 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:44:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50004
;

-- Sep 24, 2008 2:44:19 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:44:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50004
;

-- Sep 24, 2008 2:45:29 PM CDT
-- Manufacturing Demo
UPDATE C_BPartner SET Value='Store Central',Updated=TO_TIMESTAMP('2008-09-24 14:45:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_BPartner_ID=50004
;

-- Sep 24, 2008 2:45:47 PM CDT
-- Manufacturing Demo
INSERT INTO C_BPartner (AD_Client_ID,AD_Language,AD_Org_ID,AcqusitionCost,ActualLifeTimeValue,C_BP_Group_ID,C_BPartner_ID,C_Dunning_ID,C_PaymentTerm_ID,Created,CreatedBy,DocumentCopies,FlatDiscount,IsActive,IsCustomer,IsDiscountPrinted,IsEmployee,IsOneTime,IsProspect,IsSalesRep,IsSummary,IsTaxExempt,IsVendor,M_PriceList_ID,Name,NumberEmployees,PotentialLifeTimeValue,SOCreditStatus,SO_CreditLimit,SO_CreditUsed,SalesRep_ID,SalesVolume,SendEMail,ShareOfCustomer,ShelfLifeMinPct,TotalOpenBalance,Updated,UpdatedBy,Value) VALUES (11,'en_US',0,0,0,105,50005,100,105,TO_TIMESTAMP('2008-09-24 14:45:47','YYYY-MM-DD HH24:MI:SS'),100,0,0,'Y','N','N','N','N','N','N','N','N','N',101,'Store East',0,0,'X',0,0,100,0,'N',0,0,0,TO_TIMESTAMP('2008-09-24 14:45:47','YYYY-MM-DD HH24:MI:SS'),100,'Store East')
;

-- Sep 24, 2008 2:45:47 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodeBP (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50005, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='BP' AND NOT EXISTS (SELECT * FROM AD_TreeNodeBP e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50005)
;

-- Sep 24, 2008 2:45:47 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Customer_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,C_Prepayment_Acct,C_Receivable_Acct,C_Receivable_Services_Acct) SELECT 50005, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.C_Prepayment_Acct,p.C_Receivable_Acct,p.C_Receivable_Services_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=105 AND NOT EXISTS (SELECT * FROM C_BP_Customer_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50005)
;

-- Sep 24, 2008 2:45:47 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Vendor_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,V_Liability_Acct,V_Liability_Services_Acct,V_Prepayment_Acct) SELECT 50005, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.V_Liability_Acct,p.V_Liability_Services_Acct,p.V_Prepayment_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=105 AND NOT EXISTS (SELECT * FROM C_BP_Vendor_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50005)
;

-- Sep 24, 2008 2:45:47 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Employee_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,E_Expense_Acct,E_Prepayment_Acct) SELECT 50005, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.E_Expense_Acct,p.E_Prepayment_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM C_BP_Employee_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50005)
;

-- Sep 24, 2008 2:45:48 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Process (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50005,101,131,TO_TIMESTAMP('2008-09-24 14:45:47','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50005,TO_TIMESTAMP('2008-09-24 14:45:47','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:45:48 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:45:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50005
;

-- Sep 24, 2008 2:45:49 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Activity (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Activity_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50005,244,50005,100,131,TO_TIMESTAMP('2008-09-24 14:45:48','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50005,TO_TIMESTAMP('2008-09-24 14:45:48','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:45:49 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_EventAudit (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_EventAudit_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,Created,CreatedBy,ElapsedTimeMS,EventType,IsActive,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50005,244,50005,100,TO_TIMESTAMP('2008-09-24 14:45:49','YYYY-MM-DD HH24:MI:SS'),100,0,'PC','Y',50005,TO_TIMESTAMP('2008-09-24 14:45:49','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:45:49 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:45:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50005
;

-- Sep 24, 2008 2:45:49 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:45:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50005
;

-- Sep 24, 2008 2:45:49 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:45:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50005
;

-- Sep 24, 2008 2:45:49 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:45:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50005
;

-- Sep 24, 2008 2:45:49 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:45:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50005
;

-- Sep 24, 2008 2:46:33 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,C_Region_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'Store East',NULL,NULL,NULL,100,50009,142,NULL,TO_TIMESTAMP('2008-09-24 14:46:10','YYYY-MM-DD HH24:MI:SS'),100,'Y',NULL,NULL,TO_TIMESTAMP('2008-09-24 14:46:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:46:36 PM CDT
-- Manufacturing Demo
INSERT INTO C_BPartner_Location (AD_Client_ID,AD_Org_ID,C_BPartner_ID,C_BPartner_Location_ID,C_Location_ID,Created,CreatedBy,IsActive,IsBillTo,IsPayFrom,IsRemitTo,IsShipTo,Name,Updated,UpdatedBy) VALUES (11,0,50005,50000,50009,TO_TIMESTAMP('2008-09-24 14:46:36','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y','Y','Y','Y','Store East',TO_TIMESTAMP('2008-09-24 14:46:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:46:48 PM CDT
-- Manufacturing Demo
UPDATE AD_OrgInfo SET AD_OrgType_ID=101, M_Warehouse_ID=50005,Updated=TO_TIMESTAMP('2008-09-24 14:46:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Org_ID=50005
;

-- Sep 24, 2008 2:46:49 PM CDT
-- Manufacturing Demo
UPDATE C_BPartner SET AD_OrgBP_ID=50005,Updated=TO_TIMESTAMP('2008-09-24 14:46:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_BPartner_ID=50005
;

-- Sep 24, 2008 2:46:49 PM CDT
-- Manufacturing Demo
DELETE FROM AD_Role_OrgAccess WHERE AD_Org_ID=50005 AND AD_Role_ID=103
;

-- Sep 24, 2008 2:47:11 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,C_Region_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'Store Central',NULL,NULL,NULL,100,50010,142,NULL,TO_TIMESTAMP('2008-09-24 14:47:01','YYYY-MM-DD HH24:MI:SS'),100,'Y',NULL,NULL,TO_TIMESTAMP('2008-09-24 14:47:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:47:14 PM CDT
-- Manufacturing Demo
INSERT INTO C_BPartner_Location (AD_Client_ID,AD_Org_ID,C_BPartner_ID,C_BPartner_Location_ID,C_Location_ID,Created,CreatedBy,IsActive,IsBillTo,IsPayFrom,IsRemitTo,IsShipTo,Name,Updated,UpdatedBy) VALUES (11,0,50004,50001,50010,TO_TIMESTAMP('2008-09-24 14:47:12','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y','Y','Y','Y','Store Central',TO_TIMESTAMP('2008-09-24 14:47:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:47:27 PM CDT
-- Manufacturing Demo
UPDATE AD_OrgInfo SET AD_OrgType_ID=101, M_Warehouse_ID=104,Updated=TO_TIMESTAMP('2008-09-24 14:47:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Org_ID=12
;

-- Sep 24, 2008 2:47:27 PM CDT
-- Manufacturing Demo
UPDATE C_BPartner SET AD_OrgBP_ID=12,Updated=TO_TIMESTAMP('2008-09-24 14:47:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_BPartner_ID=50004
;

-- Sep 24, 2008 2:47:27 PM CDT
-- Manufacturing Demo
DELETE FROM AD_Role_OrgAccess WHERE AD_Org_ID=12 AND AD_Role_ID=103
;

-- Sep 24, 2008 2:47:59 PM CDT
-- Manufacturing Demo
INSERT INTO C_BPartner (AD_Client_ID,AD_Language,AD_OrgBP_ID,AD_Org_ID,AcqusitionCost,ActualLifeTimeValue,C_BP_Group_ID,C_BPartner_ID,C_Dunning_ID,C_PaymentTerm_ID,Created,CreatedBy,DocumentCopies,FlatDiscount,IsActive,IsCustomer,IsDiscountPrinted,IsEmployee,IsOneTime,IsProspect,IsSalesRep,IsSummary,IsTaxExempt,IsVendor,M_PriceList_ID,Name,NumberEmployees,PotentialLifeTimeValue,SOCreditStatus,SO_CreditLimit,SO_CreditUsed,SalesRep_ID,SalesVolume,SendEMail,ShareOfCustomer,ShelfLifeMinPct,TotalOpenBalance,Updated,UpdatedBy,Value) VALUES (11,'en_US','12',0,0,0,105,50006,100,105,TO_TIMESTAMP('2008-09-24 14:47:58','YYYY-MM-DD HH24:MI:SS'),100,0,0,'Y','N','N','N','N','N','N','N','N','N',101,'Store North',0,0,'X',0,0,100,0,'N',0,0,0,TO_TIMESTAMP('2008-09-24 14:47:58','YYYY-MM-DD HH24:MI:SS'),100,'Store North')
;

-- Sep 24, 2008 2:47:59 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodeBP (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50006, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='BP' AND NOT EXISTS (SELECT * FROM AD_TreeNodeBP e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50006)
;

-- Sep 24, 2008 2:47:59 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Customer_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,C_Prepayment_Acct,C_Receivable_Acct,C_Receivable_Services_Acct) SELECT 50006, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.C_Prepayment_Acct,p.C_Receivable_Acct,p.C_Receivable_Services_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=105 AND NOT EXISTS (SELECT * FROM C_BP_Customer_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50006)
;

-- Sep 24, 2008 2:47:59 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Vendor_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,V_Liability_Acct,V_Liability_Services_Acct,V_Prepayment_Acct) SELECT 50006, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.V_Liability_Acct,p.V_Liability_Services_Acct,p.V_Prepayment_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=105 AND NOT EXISTS (SELECT * FROM C_BP_Vendor_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50006)
;

-- Sep 24, 2008 2:47:59 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Employee_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,E_Expense_Acct,E_Prepayment_Acct) SELECT 50006, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.E_Expense_Acct,p.E_Prepayment_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM C_BP_Employee_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50006)
;

-- Sep 24, 2008 2:47:59 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Process (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50006,101,131,TO_TIMESTAMP('2008-09-24 14:47:59','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50006,TO_TIMESTAMP('2008-09-24 14:47:59','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:47:59 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:47:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50006
;

-- Sep 24, 2008 2:48:00 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Activity (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Activity_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50006,244,50006,100,131,TO_TIMESTAMP('2008-09-24 14:47:59','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50006,TO_TIMESTAMP('2008-09-24 14:47:59','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:48:01 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_EventAudit (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_EventAudit_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,Created,CreatedBy,ElapsedTimeMS,EventType,IsActive,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50006,244,50006,100,TO_TIMESTAMP('2008-09-24 14:48:00','YYYY-MM-DD HH24:MI:SS'),100,0,'PC','Y',50006,TO_TIMESTAMP('2008-09-24 14:48:00','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:48:01 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:48:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50006
;

-- Sep 24, 2008 2:48:01 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:48:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50006
;

-- Sep 24, 2008 2:48:01 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:48:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50006
;

-- Sep 24, 2008 2:48:01 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:48:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50006
;

-- Sep 24, 2008 2:48:01 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:48:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50006
;

-- Sep 24, 2008 2:48:15 PM CDT
-- Manufacturing Demo
DELETE FROM C_BPartner WHERE C_BPartner_ID=50006
;

-- Sep 24, 2008 2:48:15 PM CDT
-- Manufacturing Demo
DELETE FROM AD_TreeNodeBP WHERE Node_ID=50006 AND EXISTS (SELECT * FROM AD_Tree t WHERE t.AD_Tree_ID=AD_TreeNodeBP.AD_Tree_ID AND t.TreeType='BP')
;

-- Sep 24, 2008 2:48:34 PM CDT
-- Manufacturing Demo
INSERT INTO C_BPartner (AD_Client_ID,AD_Language,AD_Org_ID,AcqusitionCost,ActualLifeTimeValue,C_BP_Group_ID,C_BPartner_ID,C_Dunning_ID,C_PaymentTerm_ID,Created,CreatedBy,DocumentCopies,FlatDiscount,IsActive,IsCustomer,IsDiscountPrinted,IsEmployee,IsOneTime,IsProspect,IsSalesRep,IsSummary,IsTaxExempt,IsVendor,M_PriceList_ID,Name,NumberEmployees,PotentialLifeTimeValue,SOCreditStatus,SO_CreditLimit,SO_CreditUsed,SalesRep_ID,SalesVolume,SendEMail,ShareOfCustomer,ShelfLifeMinPct,TotalOpenBalance,Updated,UpdatedBy,Value) VALUES (11,'en_US',0,0,0,105,50007,100,105,TO_TIMESTAMP('2008-09-24 14:48:33','YYYY-MM-DD HH24:MI:SS'),100,0,0,'Y','N','N','N','N','N','N','N','N','N',101,'Store North',0,0,'X',0,0,100,0,'N',0,0,0,TO_TIMESTAMP('2008-09-24 14:48:33','YYYY-MM-DD HH24:MI:SS'),100,'Store North')
;

-- Sep 24, 2008 2:48:34 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodeBP (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50007, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='BP' AND NOT EXISTS (SELECT * FROM AD_TreeNodeBP e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50007)
;

-- Sep 24, 2008 2:48:34 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Customer_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,C_Prepayment_Acct,C_Receivable_Acct,C_Receivable_Services_Acct) SELECT 50007, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.C_Prepayment_Acct,p.C_Receivable_Acct,p.C_Receivable_Services_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=105 AND NOT EXISTS (SELECT * FROM C_BP_Customer_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50007)
;

-- Sep 24, 2008 2:48:34 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Vendor_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,V_Liability_Acct,V_Liability_Services_Acct,V_Prepayment_Acct) SELECT 50007, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.V_Liability_Acct,p.V_Liability_Services_Acct,p.V_Prepayment_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=105 AND NOT EXISTS (SELECT * FROM C_BP_Vendor_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50007)
;

-- Sep 24, 2008 2:48:34 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Employee_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,E_Expense_Acct,E_Prepayment_Acct) SELECT 50007, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.E_Expense_Acct,p.E_Prepayment_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM C_BP_Employee_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50007)
;

-- Sep 24, 2008 2:48:34 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Process (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50007,101,131,TO_TIMESTAMP('2008-09-24 14:48:34','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50007,TO_TIMESTAMP('2008-09-24 14:48:34','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:48:34 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:48:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50007
;

-- Sep 24, 2008 2:48:35 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Activity (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Activity_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50007,244,50007,100,131,TO_TIMESTAMP('2008-09-24 14:48:34','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50007,TO_TIMESTAMP('2008-09-24 14:48:34','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:48:36 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_EventAudit (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_EventAudit_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,Created,CreatedBy,ElapsedTimeMS,EventType,IsActive,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50007,244,50007,100,TO_TIMESTAMP('2008-09-24 14:48:35','YYYY-MM-DD HH24:MI:SS'),100,0,'PC','Y',50007,TO_TIMESTAMP('2008-09-24 14:48:35','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:48:36 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:48:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50007
;

-- Sep 24, 2008 2:48:36 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:48:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50007
;

-- Sep 24, 2008 2:48:36 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:48:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50007
;

-- Sep 24, 2008 2:48:36 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:48:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50007
;

-- Sep 24, 2008 2:48:36 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:48:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50007
;

-- Sep 24, 2008 2:48:51 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,C_Region_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'Store North',NULL,NULL,NULL,100,50011,142,NULL,TO_TIMESTAMP('2008-09-24 14:48:43','YYYY-MM-DD HH24:MI:SS'),100,'Y',NULL,NULL,TO_TIMESTAMP('2008-09-24 14:48:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:48:54 PM CDT
-- Manufacturing Demo
INSERT INTO C_BPartner_Location (AD_Client_ID,AD_Org_ID,C_BPartner_ID,C_BPartner_Location_ID,C_Location_ID,Created,CreatedBy,IsActive,IsBillTo,IsPayFrom,IsRemitTo,IsShipTo,Name,Updated,UpdatedBy) VALUES (11,0,50007,50002,50011,TO_TIMESTAMP('2008-09-24 14:48:53','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y','Y','Y','Y','Store North',TO_TIMESTAMP('2008-09-24 14:48:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:49:06 PM CDT
-- Manufacturing Demo
UPDATE AD_OrgInfo SET AD_OrgType_ID=101, M_Warehouse_ID=50003,Updated=TO_TIMESTAMP('2008-09-24 14:49:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Org_ID=50002
;

-- Sep 24, 2008 2:49:06 PM CDT
-- Manufacturing Demo
UPDATE C_BPartner SET AD_OrgBP_ID=50002,Updated=TO_TIMESTAMP('2008-09-24 14:49:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_BPartner_ID=50007
;

-- Sep 24, 2008 2:49:06 PM CDT
-- Manufacturing Demo
DELETE FROM AD_Role_OrgAccess WHERE AD_Org_ID=50002 AND AD_Role_ID=103
;

-- Sep 24, 2008 2:49:39 PM CDT
-- Manufacturing Demo
INSERT INTO C_BPartner (AD_Client_ID,AD_Language,AD_Org_ID,AcqusitionCost,ActualLifeTimeValue,C_BP_Group_ID,C_BPartner_ID,C_Dunning_ID,C_PaymentTerm_ID,Created,CreatedBy,DocumentCopies,FlatDiscount,IsActive,IsCustomer,IsDiscountPrinted,IsEmployee,IsOneTime,IsProspect,IsSalesRep,IsSummary,IsTaxExempt,IsVendor,M_PriceList_ID,Name,NumberEmployees,PotentialLifeTimeValue,SOCreditStatus,SO_CreditLimit,SO_CreditUsed,SalesRep_ID,SalesVolume,SendEMail,ShareOfCustomer,ShelfLifeMinPct,TotalOpenBalance,Updated,UpdatedBy,Value) VALUES (11,'en_US',0,0,0,105,50008,100,105,TO_TIMESTAMP('2008-09-24 14:49:39','YYYY-MM-DD HH24:MI:SS'),100,0,0,'Y','N','N','N','N','N','N','N','N','N',101,'Store South',0,0,'X',0,0,100,0,'N',0,0,0,TO_TIMESTAMP('2008-09-24 14:49:39','YYYY-MM-DD HH24:MI:SS'),100,'Store South')
;

-- Sep 24, 2008 2:49:39 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodeBP (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50008, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='BP' AND NOT EXISTS (SELECT * FROM AD_TreeNodeBP e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50008)
;

-- Sep 24, 2008 2:49:39 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Customer_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,C_Prepayment_Acct,C_Receivable_Acct,C_Receivable_Services_Acct) SELECT 50008, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.C_Prepayment_Acct,p.C_Receivable_Acct,p.C_Receivable_Services_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=105 AND NOT EXISTS (SELECT * FROM C_BP_Customer_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50008)
;

-- Sep 24, 2008 2:49:39 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Vendor_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,V_Liability_Acct,V_Liability_Services_Acct,V_Prepayment_Acct) SELECT 50008, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.V_Liability_Acct,p.V_Liability_Services_Acct,p.V_Prepayment_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=105 AND NOT EXISTS (SELECT * FROM C_BP_Vendor_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50008)
;

-- Sep 24, 2008 2:49:39 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Employee_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,E_Expense_Acct,E_Prepayment_Acct) SELECT 50008, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.E_Expense_Acct,p.E_Prepayment_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM C_BP_Employee_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50008)
;

-- Sep 24, 2008 2:49:41 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Process (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50008,101,131,TO_TIMESTAMP('2008-09-24 14:49:39','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50008,TO_TIMESTAMP('2008-09-24 14:49:39','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:49:41 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:49:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50008
;

-- Sep 24, 2008 2:49:42 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Activity (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Activity_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50008,244,50008,100,131,TO_TIMESTAMP('2008-09-24 14:49:41','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50008,TO_TIMESTAMP('2008-09-24 14:49:41','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:49:43 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_EventAudit (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_EventAudit_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,Created,CreatedBy,ElapsedTimeMS,EventType,IsActive,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50008,244,50008,100,TO_TIMESTAMP('2008-09-24 14:49:42','YYYY-MM-DD HH24:MI:SS'),100,0,'PC','Y',50008,TO_TIMESTAMP('2008-09-24 14:49:42','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:49:43 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:49:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50008
;

-- Sep 24, 2008 2:49:43 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:49:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50008
;

-- Sep 24, 2008 2:49:43 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:49:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50008
;

-- Sep 24, 2008 2:49:43 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:49:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50008
;

-- Sep 24, 2008 2:49:43 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:49:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50008
;

-- Sep 24, 2008 2:49:56 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,C_Region_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'Store South',NULL,NULL,NULL,100,50012,142,NULL,TO_TIMESTAMP('2008-09-24 14:49:49','YYYY-MM-DD HH24:MI:SS'),100,'Y',NULL,NULL,TO_TIMESTAMP('2008-09-24 14:49:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:49:58 PM CDT
-- Manufacturing Demo
INSERT INTO C_BPartner_Location (AD_Client_ID,AD_Org_ID,C_BPartner_ID,C_BPartner_Location_ID,C_Location_ID,Created,CreatedBy,IsActive,IsBillTo,IsPayFrom,IsRemitTo,IsShipTo,Name,Updated,UpdatedBy) VALUES (11,0,50008,50003,50012,TO_TIMESTAMP('2008-09-24 14:49:57','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y','Y','Y','Y','Store South',TO_TIMESTAMP('2008-09-24 14:49:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:50:10 PM CDT
-- Manufacturing Demo
UPDATE AD_OrgInfo SET AD_OrgType_ID=101, M_Warehouse_ID=50004,Updated=TO_TIMESTAMP('2008-09-24 14:50:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Org_ID=50004
;

-- Sep 24, 2008 2:50:10 PM CDT
-- Manufacturing Demo
UPDATE C_BPartner SET AD_OrgBP_ID=50004,Updated=TO_TIMESTAMP('2008-09-24 14:50:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_BPartner_ID=50008
;

-- Sep 24, 2008 2:50:10 PM CDT
-- Manufacturing Demo
DELETE FROM AD_Role_OrgAccess WHERE AD_Org_ID=50004 AND AD_Role_ID=103
;

-- Sep 24, 2008 2:50:36 PM CDT
-- Manufacturing Demo
INSERT INTO C_BPartner (AD_Client_ID,AD_Language,AD_Org_ID,AcqusitionCost,ActualLifeTimeValue,C_BP_Group_ID,C_BPartner_ID,C_Dunning_ID,C_PaymentTerm_ID,Created,CreatedBy,DocumentCopies,FlatDiscount,IsActive,IsCustomer,IsDiscountPrinted,IsEmployee,IsOneTime,IsProspect,IsSalesRep,IsSummary,IsTaxExempt,IsVendor,M_PriceList_ID,Name,NumberEmployees,PotentialLifeTimeValue,SOCreditStatus,SO_CreditLimit,SO_CreditUsed,SalesRep_ID,SalesVolume,SendEMail,ShareOfCustomer,ShelfLifeMinPct,TotalOpenBalance,Updated,UpdatedBy,Value) VALUES (11,'en_US',0,0,0,105,50009,100,105,TO_TIMESTAMP('2008-09-24 14:50:35','YYYY-MM-DD HH24:MI:SS'),100,0,0,'Y','N','N','N','N','N','N','N','N','N',101,'Store West',0,0,'X',0,0,100,0,'N',0,0,0,TO_TIMESTAMP('2008-09-24 14:50:35','YYYY-MM-DD HH24:MI:SS'),100,'Store West')
;

-- Sep 24, 2008 2:50:36 PM CDT
-- Manufacturing Demo
INSERT INTO AD_TreeNodeBP (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 50009, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='BP' AND NOT EXISTS (SELECT * FROM AD_TreeNodeBP e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50009)
;

-- Sep 24, 2008 2:50:36 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Customer_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,C_Prepayment_Acct,C_Receivable_Acct,C_Receivable_Services_Acct) SELECT 50009, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.C_Prepayment_Acct,p.C_Receivable_Acct,p.C_Receivable_Services_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=105 AND NOT EXISTS (SELECT * FROM C_BP_Customer_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50009)
;

-- Sep 24, 2008 2:50:36 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Vendor_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,V_Liability_Acct,V_Liability_Services_Acct,V_Prepayment_Acct) SELECT 50009, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.V_Liability_Acct,p.V_Liability_Services_Acct,p.V_Prepayment_Acct FROM C_BP_Group_Acct p WHERE p.AD_Client_ID=11 AND p.C_BP_Group_ID=105 AND NOT EXISTS (SELECT * FROM C_BP_Vendor_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50009)
;

-- Sep 24, 2008 2:50:36 PM CDT
-- Manufacturing Demo
INSERT INTO C_BP_Employee_Acct (C_BPartner_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,E_Expense_Acct,E_Prepayment_Acct) SELECT 50009, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.E_Expense_Acct,p.E_Prepayment_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM C_BP_Employee_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.C_BPartner_ID=50009)
;

-- Sep 24, 2008 2:50:37 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Process (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50009,101,131,TO_TIMESTAMP('2008-09-24 14:50:36','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50009,TO_TIMESTAMP('2008-09-24 14:50:36','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:50:37 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:50:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50009
;

-- Sep 24, 2008 2:50:37 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Activity (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_Activity_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Priority,Processed,Processing,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50009,244,50009,100,131,TO_TIMESTAMP('2008-09-24 14:50:37','YYYY-MM-DD HH24:MI:SS'),100,'Y',0,'N','N',50009,TO_TIMESTAMP('2008-09-24 14:50:37','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:50:38 PM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_EventAudit (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_User_ID,AD_WF_EventAudit_ID,AD_WF_Node_ID,AD_WF_Process_ID,AD_WF_Responsible_ID,Created,CreatedBy,ElapsedTimeMS,EventType,IsActive,Record_ID,Updated,UpdatedBy,WFState) VALUES (11,50006,291,100,50009,244,50009,100,TO_TIMESTAMP('2008-09-24 14:50:37','YYYY-MM-DD HH24:MI:SS'),100,0,'PC','Y',50009,TO_TIMESTAMP('2008-09-24 14:50:37','YYYY-MM-DD HH24:MI:SS'),100,'ON')
;

-- Sep 24, 2008 2:50:38 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:50:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50009
;

-- Sep 24, 2008 2:50:38 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OR',Updated=TO_TIMESTAMP('2008-09-24 14:50:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50009
;

-- Sep 24, 2008 2:50:38 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Activity SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:50:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Activity_ID=50009
;

-- Sep 24, 2008 2:50:38 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_EventAudit SET EventType='SC', TextMsg=NULL, WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:50:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_EventAudit_ID=50009
;

-- Sep 24, 2008 2:50:38 PM CDT
-- Manufacturing Demo
UPDATE AD_WF_Process SET WFState='OS',Updated=TO_TIMESTAMP('2008-09-24 14:50:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_WF_Process_ID=50009
;

-- Sep 24, 2008 2:50:57 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,C_Region_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'Store West',NULL,NULL,NULL,100,50013,142,NULL,TO_TIMESTAMP('2008-09-24 14:50:50','YYYY-MM-DD HH24:MI:SS'),100,'Y',NULL,NULL,TO_TIMESTAMP('2008-09-24 14:50:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:50:59 PM CDT
-- Manufacturing Demo
INSERT INTO C_BPartner_Location (AD_Client_ID,AD_Org_ID,C_BPartner_ID,C_BPartner_Location_ID,C_Location_ID,Created,CreatedBy,IsActive,IsBillTo,IsPayFrom,IsRemitTo,IsShipTo,Name,Updated,UpdatedBy) VALUES (11,0,50009,50004,50013,TO_TIMESTAMP('2008-09-24 14:50:58','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y','Y','Y','Y','Store West',TO_TIMESTAMP('2008-09-24 14:50:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:51:12 PM CDT
-- Manufacturing Demo
UPDATE AD_OrgInfo SET AD_OrgType_ID=101, M_Warehouse_ID=50006,Updated=TO_TIMESTAMP('2008-09-24 14:51:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Org_ID=50006
;

-- Sep 24, 2008 2:51:12 PM CDT
-- Manufacturing Demo
UPDATE C_BPartner SET AD_OrgBP_ID=50006,Updated=TO_TIMESTAMP('2008-09-24 14:51:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_BPartner_ID=50009
;

-- Sep 24, 2008 2:51:12 PM CDT
-- Manufacturing Demo
DELETE FROM AD_Role_OrgAccess WHERE AD_Org_ID=50006 AND AD_Role_ID=103
;

-- Sep 24, 2008 2:52:08 PM CDT
-- Manufacturing Demo
INSERT INTO M_Shipper (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,M_Shipper_ID,Name,Updated,UpdatedBy) VALUES (11,0,TO_TIMESTAMP('2008-09-24 14:52:08','YYYY-MM-DD HH24:MI:SS'),100,'Y',50001,'Firtilizer Internal Shipper',TO_TIMESTAMP('2008-09-24 14:52:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:52:25 PM CDT
-- Manufacturing Demo
INSERT INTO M_Shipper (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,M_Shipper_ID,Name,Updated,UpdatedBy) VALUES (11,0,TO_TIMESTAMP('2008-09-24 14:52:24','YYYY-MM-DD HH24:MI:SS'),100,'Y',50002,'Furniture Internal Shipper',TO_TIMESTAMP('2008-09-24 14:52:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:55:24 PM CDT
-- Manufacturing Demo
INSERT INTO DD_NetworkDistribution (AD_Client_ID,AD_Org_ID,CopyFrom,Created,CreatedBy,DD_NetworkDistribution_ID,DocumentNo,IsActive,Name,Processing,Updated,UpdatedBy,Value) VALUES (11,0,'N',TO_TIMESTAMP('2008-09-24 14:55:17','YYYY-MM-DD HH24:MI:SS'),100,50000,'50000','Y','Patio Set Network Distribution','N',TO_TIMESTAMP('2008-09-24 14:55:17','YYYY-MM-DD HH24:MI:SS'),100,'PatioSet')
;

-- Sep 24, 2008 2:56:22 PM CDT
-- Manufacturing Demo
INSERT INTO DD_NetworkDistributionLine (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistributionLine_ID,DD_NetworkDistribution_ID,IsActive,M_Shipper_ID,M_WarehouseSource_ID,M_Warehouse_ID,Percent,PriorityNo,TransfertTime,Updated,UpdatedBy) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 14:56:21','YYYY-MM-DD HH24:MI:SS'),100,50000,50000,'Y',50002,50001,104,100.000000000000,0,30.000000000000,TO_TIMESTAMP('2008-09-24 14:56:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:56:50 PM CDT
-- Manufacturing Demo
INSERT INTO DD_NetworkDistributionLine (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistributionLine_ID,DD_NetworkDistribution_ID,IsActive,M_Shipper_ID,M_WarehouseSource_ID,M_Warehouse_ID,Percent,PriorityNo,TransfertTime,Updated,UpdatedBy) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 14:56:50','YYYY-MM-DD HH24:MI:SS'),100,50001,50000,'Y',50002,50001,50005,100.000000000000,0,30.000000000000,TO_TIMESTAMP('2008-09-24 14:56:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:57:15 PM CDT
-- Manufacturing Demo
INSERT INTO DD_NetworkDistributionLine (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistributionLine_ID,DD_NetworkDistribution_ID,IsActive,M_Shipper_ID,M_WarehouseSource_ID,M_Warehouse_ID,Percent,PriorityNo,TransfertTime,Updated,UpdatedBy) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 14:57:14','YYYY-MM-DD HH24:MI:SS'),100,50002,50000,'Y',50002,50001,50003,100.000000000000,0,45.000000000000,TO_TIMESTAMP('2008-09-24 14:57:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:57:28 PM CDT
-- Manufacturing Demo
INSERT INTO DD_NetworkDistributionLine (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistributionLine_ID,DD_NetworkDistribution_ID,IsActive,M_Shipper_ID,M_WarehouseSource_ID,M_Warehouse_ID,Percent,PriorityNo,TransfertTime,Updated,UpdatedBy) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 14:57:27','YYYY-MM-DD HH24:MI:SS'),100,50003,50000,'Y',50002,50001,50004,0,0,0,TO_TIMESTAMP('2008-09-24 14:57:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:57:40 PM CDT
-- Manufacturing Demo
UPDATE DD_NetworkDistributionLine SET Percent=100.000000000000, TransfertTime=20.000000000000,Updated=TO_TIMESTAMP('2008-09-24 14:57:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE DD_NetworkDistributionLine_ID=50003
;

-- Sep 24, 2008 2:58:01 PM CDT
-- Manufacturing Demo
INSERT INTO DD_NetworkDistributionLine (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistributionLine_ID,DD_NetworkDistribution_ID,IsActive,M_Shipper_ID,M_WarehouseSource_ID,M_Warehouse_ID,Percent,PriorityNo,TransfertTime,Updated,UpdatedBy) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 14:58:00','YYYY-MM-DD HH24:MI:SS'),100,50004,50000,'Y',50002,50001,50004,100.000000000000,0,30.000000000000,TO_TIMESTAMP('2008-09-24 14:58:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 2:58:08 PM CDT
-- Manufacturing Demo
UPDATE DD_NetworkDistributionLine SET M_Warehouse_ID=50006,Updated=TO_TIMESTAMP('2008-09-24 14:58:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE DD_NetworkDistributionLine_ID=50004
;

-- Sep 24, 2008 3:00:27 PM CDT
-- Manufacturing Demo
INSERT INTO DD_NetworkDistribution (AD_Client_ID,AD_Org_ID,CopyFrom,Created,CreatedBy,DD_NetworkDistribution_ID,DocumentNo,IsActive,Name,Processing,Updated,UpdatedBy,Value) VALUES (11,0,'N',TO_TIMESTAMP('2008-09-24 15:00:26','YYYY-MM-DD HH24:MI:SS'),100,50001,'50001','Y','Fertilizer Network Distribution','N',TO_TIMESTAMP('2008-09-24 15:00:26','YYYY-MM-DD HH24:MI:SS'),100,'Fertilizer')
;

-- Sep 24, 2008 3:00:54 PM CDT
-- Manufacturing Demo
INSERT INTO DD_NetworkDistributionLine (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistributionLine_ID,DD_NetworkDistribution_ID,IsActive,M_Shipper_ID,M_WarehouseSource_ID,M_Warehouse_ID,Percent,PriorityNo,TransfertTime,Updated,UpdatedBy) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 15:00:53','YYYY-MM-DD HH24:MI:SS'),100,50005,50001,'Y',50001,50002,104,100.000000000000,0,45.000000000000,TO_TIMESTAMP('2008-09-24 15:00:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 3:01:13 PM CDT
-- Manufacturing Demo
UPDATE M_Shipper SET Name='Fertilizer Internal Shipper',Updated=TO_TIMESTAMP('2008-09-24 15:01:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Shipper_ID=50001
;

-- Sep 24, 2008 3:01:27 PM CDT
-- Manufacturing Demo
INSERT INTO DD_NetworkDistributionLine (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistributionLine_ID,DD_NetworkDistribution_ID,IsActive,M_Shipper_ID,M_WarehouseSource_ID,M_Warehouse_ID,Percent,PriorityNo,TransfertTime,Updated,UpdatedBy) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 15:01:26','YYYY-MM-DD HH24:MI:SS'),100,50006,50001,'Y',50001,50002,50005,100.000000000000,0,45.000000000000,TO_TIMESTAMP('2008-09-24 15:01:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 3:01:49 PM CDT
-- Manufacturing Demo
INSERT INTO DD_NetworkDistributionLine (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistributionLine_ID,DD_NetworkDistribution_ID,IsActive,M_Shipper_ID,M_WarehouseSource_ID,M_Warehouse_ID,Percent,PriorityNo,TransfertTime,Updated,UpdatedBy) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 15:01:49','YYYY-MM-DD HH24:MI:SS'),100,50007,50001,'Y',50001,50002,50003,100.000000000000,0,50.000000000000,TO_TIMESTAMP('2008-09-24 15:01:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 3:02:13 PM CDT
-- Manufacturing Demo
INSERT INTO DD_NetworkDistributionLine (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistributionLine_ID,DD_NetworkDistribution_ID,IsActive,M_Shipper_ID,M_WarehouseSource_ID,M_Warehouse_ID,Percent,PriorityNo,TransfertTime,Updated,UpdatedBy) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 15:02:12','YYYY-MM-DD HH24:MI:SS'),100,50008,50001,'Y',50001,50002,50004,100.000000000000,0,50.000000000000,TO_TIMESTAMP('2008-09-24 15:02:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 3:02:34 PM CDT
-- Manufacturing Demo
INSERT INTO DD_NetworkDistributionLine (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistributionLine_ID,DD_NetworkDistribution_ID,IsActive,M_Shipper_ID,M_WarehouseSource_ID,M_Warehouse_ID,Percent,PriorityNo,TransfertTime,Updated,UpdatedBy) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 15:02:32','YYYY-MM-DD HH24:MI:SS'),100,50009,50001,'Y',50001,50002,50006,100.000000000000,0,45.000000000000,TO_TIMESTAMP('2008-09-24 15:02:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 3:07:34 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,C_Region_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'Fertilizer Transit',NULL,NULL,NULL,100,50014,142,NULL,TO_TIMESTAMP('2008-09-24 15:07:16','YYYY-MM-DD HH24:MI:SS'),100,'Y',NULL,NULL,TO_TIMESTAMP('2008-09-24 15:07:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 3:07:43 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse (AD_Client_ID,AD_Org_ID,C_Location_ID,Created,CreatedBy,Description,IsActive,IsInTransit,M_Warehouse_ID,Name,Separator,Updated,UpdatedBy,Value) VALUES (11,50001,50014,TO_TIMESTAMP('2008-09-24 15:07:37','YYYY-MM-DD HH24:MI:SS'),100,'Fertilizer Transit','Y','Y',50007,'Fertilizer Transit','*',TO_TIMESTAMP('2008-09-24 15:07:37','YYYY-MM-DD HH24:MI:SS'),100,'FertilizerT')
;

-- Sep 24, 2008 3:07:43 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse_Acct (M_Warehouse_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,W_Differences_Acct,W_InvActualAdjust_Acct,W_Inventory_Acct,W_Revaluation_Acct) SELECT 50007, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.W_Differences_Acct,p.W_InvActualAdjust_Acct,p.W_Inventory_Acct,p.W_Revaluation_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM M_Warehouse_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Warehouse_ID=50007)
;

-- Sep 24, 2008 3:08:03 PM CDT
-- Manufacturing Demo
INSERT INTO M_Locator (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,IsDefault,M_Locator_ID,M_Warehouse_ID,PriorityNo,Updated,UpdatedBy,Value,X,Y,Z) VALUES (11,50001,TO_TIMESTAMP('2008-09-24 15:07:52','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y',50007,50007,50,TO_TIMESTAMP('2008-09-24 15:07:52','YYYY-MM-DD HH24:MI:SS'),100,'10000003','Fertilizer Transit','Fertilizer Transit','Fertilizer Transit')
;

-- Sep 24, 2008 3:09:47 PM CDT
-- Manufacturing Demo
UPDATE M_Locator SET Value='Fertilizer Transit',Updated=TO_TIMESTAMP('2008-09-24 15:09:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Locator_ID=50007
;

-- Sep 24, 2008 3:10:18 PM CDT
-- Manufacturing Demo
INSERT INTO C_Location (AD_Client_ID,AD_Org_ID,Address1,Address2,Address3,Address4,C_Country_ID,C_Location_ID,C_Region_ID,City,Created,CreatedBy,IsActive,Postal,Postal_Add,Updated,UpdatedBy) VALUES (11,0,'Furniture Transit',NULL,NULL,NULL,100,50015,142,NULL,TO_TIMESTAMP('2008-09-24 15:10:12','YYYY-MM-DD HH24:MI:SS'),100,'Y',NULL,NULL,TO_TIMESTAMP('2008-09-24 15:10:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 24, 2008 3:10:27 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse (AD_Client_ID,AD_Org_ID,C_Location_ID,Created,CreatedBy,Description,IsActive,IsInTransit,M_Warehouse_ID,Name,Separator,Updated,UpdatedBy,Value) VALUES (11,50000,50015,TO_TIMESTAMP('2008-09-24 15:10:24','YYYY-MM-DD HH24:MI:SS'),100,'Furniture Transit','Y','Y',50008,'Furniture Transit','*',TO_TIMESTAMP('2008-09-24 15:10:24','YYYY-MM-DD HH24:MI:SS'),100,'FurnitureT')
;

-- Sep 24, 2008 3:10:27 PM CDT
-- Manufacturing Demo
INSERT INTO M_Warehouse_Acct (M_Warehouse_ID, C_AcctSchema_ID, AD_Client_ID,AD_Org_ID,IsActive, Created,CreatedBy,Updated,UpdatedBy ,W_Differences_Acct,W_InvActualAdjust_Acct,W_Inventory_Acct,W_Revaluation_Acct) SELECT 50008, p.C_AcctSchema_ID, p.AD_Client_ID,0,'Y', CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,p.W_Differences_Acct,p.W_InvActualAdjust_Acct,p.W_Inventory_Acct,p.W_Revaluation_Acct FROM C_AcctSchema_Default p WHERE p.AD_Client_ID=11 AND NOT EXISTS (SELECT * FROM M_Warehouse_Acct e WHERE e.C_AcctSchema_ID=p.C_AcctSchema_ID AND e.M_Warehouse_ID=50008)
;

-- Sep 24, 2008 3:10:42 PM CDT
-- Manufacturing Demo
INSERT INTO M_Locator (AD_Client_ID,AD_Org_ID,Created,CreatedBy,IsActive,IsDefault,M_Locator_ID,M_Warehouse_ID,PriorityNo,Updated,UpdatedBy,Value,X,Y,Z) VALUES (11,50000,TO_TIMESTAMP('2008-09-24 15:10:38','YYYY-MM-DD HH24:MI:SS'),100,'Y','Y',50008,50008,50,TO_TIMESTAMP('2008-09-24 15:10:38','YYYY-MM-DD HH24:MI:SS'),100,'Furniture Transit','Furniture Transit','Furniture Transit','Furniture Transit')
;

-- Sep 24, 2008 3:12:38 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:12:29','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',126,104,0,0,0,5.000000000000,'POQ',0,50106,50000,0,0,TO_TIMESTAMP('2008-09-24 15:12:29','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:12:38 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:12:38','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',127,104,0,0,0,5.000000000000,'POQ',0,50107,50000,0,0,TO_TIMESTAMP('2008-09-24 15:12:38','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:12:39 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:12:38','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',128,104,0,0,0,5.000000000000,'POQ',0,50108,50000,0,0,TO_TIMESTAMP('2008-09-24 15:12:38','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:12:40 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:12:39','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',129,104,0,0,0,5.000000000000,'POQ',0,50109,50000,0,0,TO_TIMESTAMP('2008-09-24 15:12:39','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:12:41 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:12:40','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',130,104,0,0,0,5.000000000000,'POQ',0,50110,50000,0,0,TO_TIMESTAMP('2008-09-24 15:12:40','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:12:42 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:12:41','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',131,104,0,0,0,5.000000000000,'POQ',0,50111,50000,0,0,TO_TIMESTAMP('2008-09-24 15:12:41','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:12:43 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:12:42','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',132,104,0,0,0,5.000000000000,'POQ',0,50112,50000,0,0,TO_TIMESTAMP('2008-09-24 15:12:42','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:12:45 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:12:43','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',133,104,0,0,0,5.000000000000,'POQ',0,50113,50000,0,0,TO_TIMESTAMP('2008-09-24 15:12:43','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:12:48 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:12:45','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',134,104,0,0,0,5.000000000000,'POQ',0,50114,50000,0,0,TO_TIMESTAMP('2008-09-24 15:12:45','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:12:50 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:12:48','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',135,104,0,0,0,5.000000000000,'POQ',0,50115,50000,0,0,TO_TIMESTAMP('2008-09-24 15:12:48','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:12:50 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:12:50','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',137,104,0,0,0,5.000000000000,'POQ',0,50116,50000,0,0,TO_TIMESTAMP('2008-09-24 15:12:50','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:12:51 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:12:50','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',138,104,0,0,0,5.000000000000,'POQ',0,50117,50000,0,0,TO_TIMESTAMP('2008-09-24 15:12:50','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:12:52 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:12:51','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',139,104,0,0,0,5.000000000000,'POQ',0,50118,50000,0,0,TO_TIMESTAMP('2008-09-24 15:12:51','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:12:56 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:12:52','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',140,104,0,0,0,5.000000000000,'POQ',0,50119,50000,0,0,TO_TIMESTAMP('2008-09-24 15:12:52','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:00 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:12:56','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',141,104,0,0,0,5.000000000000,'POQ',0,50120,50000,0,0,TO_TIMESTAMP('2008-09-24 15:12:56','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:01 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:01','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',142,104,0,0,0,5.000000000000,'POQ',0,50121,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:01','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:05 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:01','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',143,104,0,0,0,5.000000000000,'POQ',0,50122,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:01','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:06 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:05','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',144,104,0,0,0,5.000000000000,'POQ',0,50123,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:05','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:08 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:06','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',145,104,0,0,0,5.000000000000,'POQ',0,50124,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:06','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:09 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:08','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',146,104,0,0,0,5.000000000000,'POQ',0,50125,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:08','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:10 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:09','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',147,104,0,0,0,5.000000000000,'POQ',0,50126,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:09','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:13 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:10','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',148,104,0,0,0,5.000000000000,'POQ',0,50127,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:10','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:20 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:13','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',122,104,0,0,0,5.000000000000,'POQ',0,50128,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:13','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:21 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:20','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',123,104,0,0,0,5.000000000000,'POQ',0,50129,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:20','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:21 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:21','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',124,104,0,0,0,5.000000000000,'POQ',0,50130,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:21','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:23 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:21','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',125,104,0,0,0,5.000000000000,'POQ',0,50131,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:21','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:24 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:23','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50004,104,0,0,0,5.000000000000,'POQ',0,50132,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:23','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:27 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:24','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50005,104,0,0,0,5.000000000000,'POQ',0,50133,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:24','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:31 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:27','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50000,104,0,0,0,5.000000000000,'POQ',0,50134,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:27','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:33 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:31','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50016,104,0,0,0,5.000000000000,'POQ',0,50135,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:31','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:35 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:33','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50002,104,0,0,0,5.000000000000,'POQ',0,50136,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:33','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:35 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:35','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50003,104,0,0,0,5.000000000000,'POQ',0,50137,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:35','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:36 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:35','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50001,104,0,0,0,5.000000000000,'POQ',0,50138,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:35','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:38 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:36','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50013,104,0,0,0,5.000000000000,'POQ',0,50139,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:36','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:39 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:38','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50014,104,0,0,0,5.000000000000,'POQ',0,50140,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:38','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:40 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:39','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50008,104,0,0,0,5.000000000000,'POQ',0,50141,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:39','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:41 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:40','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50010,104,0,0,0,5.000000000000,'POQ',0,50142,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:40','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:41 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:41','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50009,104,0,0,0,5.000000000000,'POQ',0,50143,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:41','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:42 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:41','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50012,104,0,0,0,5.000000000000,'POQ',0,50144,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:41','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:42 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:42','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50017,104,0,0,0,5.000000000000,'POQ',0,50145,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:42','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:43 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:42','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50015,104,0,0,0,5.000000000000,'POQ',0,50146,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:42','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:43 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:43','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',136,104,0,0,0,5.000000000000,'POQ',0,50147,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:43','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:44 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:43','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50007,104,0,0,0,5.000000000000,'POQ',0,50148,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:43','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:45 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:44','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50018,104,0,0,0,5.000000000000,'POQ',0,50149,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:44','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:45 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:45','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50019,104,0,0,0,5.000000000000,'POQ',0,50150,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:45','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:46 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:45','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50020,104,0,0,0,5.000000000000,'POQ',0,50151,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:45','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:48 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:46','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50021,104,0,0,0,5.000000000000,'POQ',0,50152,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:46','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:48 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:48','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50022,104,0,0,0,5.000000000000,'POQ',0,50153,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:48','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:49 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:48','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50023,104,0,0,0,5.000000000000,'POQ',0,50154,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:48','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:49 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:49','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50024,104,0,0,0,5.000000000000,'POQ',0,50155,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:49','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:50 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:49','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50025,104,0,0,0,5.000000000000,'POQ',0,50156,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:49','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:50 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:50','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50026,104,0,0,0,5.000000000000,'POQ',0,50157,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:50','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:13:51 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 15:13:50','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','N','Y','Y',50027,104,0,0,0,5.000000000000,'POQ',0,50158,50000,0,0,TO_TIMESTAMP('2008-09-24 15:13:50','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:15:57 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:15:52','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',126,50005,0,0,0,5.000000000000,'POQ',0,50159,50005,0,0,TO_TIMESTAMP('2008-09-24 15:15:52','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:15:57 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:15:57','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',127,50005,0,0,0,5.000000000000,'POQ',0,50160,50005,0,0,TO_TIMESTAMP('2008-09-24 15:15:57','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:15:58 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:15:57','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',128,50005,0,0,0,5.000000000000,'POQ',0,50161,50005,0,0,TO_TIMESTAMP('2008-09-24 15:15:57','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:15:58 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:15:58','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',129,50005,0,0,0,5.000000000000,'POQ',0,50162,50005,0,0,TO_TIMESTAMP('2008-09-24 15:15:58','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:15:59 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:15:58','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',130,50005,0,0,0,5.000000000000,'POQ',0,50163,50005,0,0,TO_TIMESTAMP('2008-09-24 15:15:58','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:15:59 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:15:59','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',131,50005,0,0,0,5.000000000000,'POQ',0,50164,50005,0,0,TO_TIMESTAMP('2008-09-24 15:15:59','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:00 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:15:59','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',132,50005,0,0,0,5.000000000000,'POQ',0,50165,50005,0,0,TO_TIMESTAMP('2008-09-24 15:15:59','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:08 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:00','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',133,50005,0,0,0,5.000000000000,'POQ',0,50166,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:00','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:10 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:08','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',134,50005,0,0,0,5.000000000000,'POQ',0,50167,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:08','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:11 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:10','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',135,50005,0,0,0,5.000000000000,'POQ',0,50168,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:10','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:11 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:11','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',137,50005,0,0,0,5.000000000000,'POQ',0,50169,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:11','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:12 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:11','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',138,50005,0,0,0,5.000000000000,'POQ',0,50170,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:11','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:12 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:12','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',139,50005,0,0,0,5.000000000000,'POQ',0,50171,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:12','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:12 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:12','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',140,50005,0,0,0,5.000000000000,'POQ',0,50172,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:12','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:13 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:12','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',141,50005,0,0,0,5.000000000000,'POQ',0,50173,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:12','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:14 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:13','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',142,50005,0,0,0,5.000000000000,'POQ',0,50174,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:13','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:14 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:14','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',143,50005,0,0,0,5.000000000000,'POQ',0,50175,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:14','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:16 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:14','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',144,50005,0,0,0,5.000000000000,'POQ',0,50176,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:14','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:16 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:16','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',145,50005,0,0,0,5.000000000000,'POQ',0,50177,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:16','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:17 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:16','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',146,50005,0,0,0,5.000000000000,'POQ',0,50178,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:16','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:18 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:17','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',147,50005,0,0,0,5.000000000000,'POQ',0,50179,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:17','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:18 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:18','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',148,50005,0,0,0,5.000000000000,'POQ',0,50180,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:18','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:19 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:18','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',122,50005,0,0,0,5.000000000000,'POQ',0,50181,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:18','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:19 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:19','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',123,50005,0,0,0,5.000000000000,'POQ',0,50182,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:19','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:20 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:19','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',124,50005,0,0,0,5.000000000000,'POQ',0,50183,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:19','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:20 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:20','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',125,50005,0,0,0,5.000000000000,'POQ',0,50184,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:20','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:20 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:20','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50004,50005,0,0,0,5.000000000000,'POQ',0,50185,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:20','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:21 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:20','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50005,50005,0,0,0,5.000000000000,'POQ',0,50186,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:20','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:22 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:21','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50000,50005,0,0,0,5.000000000000,'POQ',0,50187,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:21','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:23 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:22','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50016,50005,0,0,0,5.000000000000,'POQ',0,50188,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:22','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:23 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:23','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50002,50005,0,0,0,5.000000000000,'POQ',0,50189,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:23','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:24 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:23','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50003,50005,0,0,0,5.000000000000,'POQ',0,50190,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:23','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:24 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:24','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50001,50005,0,0,0,5.000000000000,'POQ',0,50191,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:24','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:25 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:24','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50013,50005,0,0,0,5.000000000000,'POQ',0,50192,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:24','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:26 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:25','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50014,50005,0,0,0,5.000000000000,'POQ',0,50193,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:25','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:26 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:26','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50008,50005,0,0,0,5.000000000000,'POQ',0,50194,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:26','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:27 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:26','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50010,50005,0,0,0,5.000000000000,'POQ',0,50195,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:26','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:27 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:27','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50009,50005,0,0,0,5.000000000000,'POQ',0,50196,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:27','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:28 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:27','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50012,50005,0,0,0,5.000000000000,'POQ',0,50197,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:27','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:29 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:28','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50017,50005,0,0,0,5.000000000000,'POQ',0,50198,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:28','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:29 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:29','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50015,50005,0,0,0,5.000000000000,'POQ',0,50199,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:29','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:30 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:29','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',136,50005,0,0,0,5.000000000000,'POQ',0,50200,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:29','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:31 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:30','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50007,50005,0,0,0,5.000000000000,'POQ',0,50201,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:30','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:31 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:31','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50018,50005,0,0,0,5.000000000000,'POQ',0,50202,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:31','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:31 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:31','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50019,50005,0,0,0,5.000000000000,'POQ',0,50203,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:31','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:32 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:31','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50020,50005,0,0,0,5.000000000000,'POQ',0,50204,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:31','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:32 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:32','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50021,50005,0,0,0,5.000000000000,'POQ',0,50205,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:32','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:33 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:32','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50022,50005,0,0,0,5.000000000000,'POQ',0,50206,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:32','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:34 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:33','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50023,50005,0,0,0,5.000000000000,'POQ',0,50207,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:33','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:34 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:34','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50024,50005,0,0,0,5.000000000000,'POQ',0,50208,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:34','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:34 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:34','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50025,50005,0,0,0,5.000000000000,'POQ',0,50209,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:34','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:35 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:34','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50026,50005,0,0,0,5.000000000000,'POQ',0,50210,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:34','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:16:35 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:16:35','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','N','N','N','Y','Y',50027,50005,0,0,0,5.000000000000,'POQ',0,50211,50005,0,0,TO_TIMESTAMP('2008-09-24 15:16:35','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:17:10 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50159
;

-- Sep 24, 2008 3:17:10 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50160
;

-- Sep 24, 2008 3:17:10 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50161
;

-- Sep 24, 2008 3:17:10 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50162
;

-- Sep 24, 2008 3:17:10 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50163
;

-- Sep 24, 2008 3:17:10 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50164
;

-- Sep 24, 2008 3:17:10 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50165
;

-- Sep 24, 2008 3:17:10 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50166
;

-- Sep 24, 2008 3:17:10 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50167
;

-- Sep 24, 2008 3:17:10 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50168
;

-- Sep 24, 2008 3:17:10 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50169
;

-- Sep 24, 2008 3:17:10 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50170
;

-- Sep 24, 2008 3:17:10 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50171
;

-- Sep 24, 2008 3:17:10 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50172
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50173
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50174
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50175
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50176
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50177
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50178
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50179
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50180
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50181
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50182
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50183
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50184
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50185
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50186
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50187
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50188
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50189
;

-- Sep 24, 2008 3:17:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50190
;

-- Sep 24, 2008 3:17:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50191
;

-- Sep 24, 2008 3:17:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50192
;

-- Sep 24, 2008 3:17:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50193
;

-- Sep 24, 2008 3:17:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50194
;

-- Sep 24, 2008 3:17:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50195
;

-- Sep 24, 2008 3:17:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50196
;

-- Sep 24, 2008 3:17:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50197
;

-- Sep 24, 2008 3:17:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50198
;

-- Sep 24, 2008 3:17:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50199
;

-- Sep 24, 2008 3:17:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50200
;

-- Sep 24, 2008 3:17:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50201
;

-- Sep 24, 2008 3:17:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50202
;

-- Sep 24, 2008 3:17:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50203
;

-- Sep 24, 2008 3:17:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50204
;

-- Sep 24, 2008 3:17:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50205
;

-- Sep 24, 2008 3:17:19 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50206
;

-- Sep 24, 2008 3:17:19 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50207
;

-- Sep 24, 2008 3:17:19 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50208
;

-- Sep 24, 2008 3:17:19 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50209
;

-- Sep 24, 2008 3:17:27 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50210
;

-- Sep 24, 2008 3:17:27 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 15:17:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50211
;

-- Sep 24, 2008 3:18:09 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET DD_NetworkDistribution_ID=50000,Updated=TO_TIMESTAMP('2008-09-24 15:18:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50177
;

-- Sep 24, 2008 3:18:35 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsIssue,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,TransfertTime,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 15:18:35','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','Y','N','Y','Y',145,50003,0,0,0,5.000000000000,'POQ',0,50212,50005,0,0,0,TO_TIMESTAMP('2008-09-24 15:18:35','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:18:53 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsIssue,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,TransfertTime,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 15:18:52','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','Y','N','Y','Y',145,50004,0,0,0,5.000000000000,'POQ',0,50213,50005,0,0,0,TO_TIMESTAMP('2008-09-24 15:18:52','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:19:04 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsIssue,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,TransfertTime,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 15:19:03','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','Y','N','Y','Y',145,50006,0,0,0,5.000000000000,'POQ',0,50214,50005,0,0,0,TO_TIMESTAMP('2008-09-24 15:19:03','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:19:24 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Locator_ID=NULL,Updated=TO_TIMESTAMP('2008-09-24 15:19:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50008
;

-- Sep 24, 2008 3:19:26 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Locator_ID=NULL,Updated=TO_TIMESTAMP('2008-09-24 15:19:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=136
;

-- Sep 24, 2008 3:19:45 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Product_Planning WHERE PP_Product_Planning_ID=50034
;

-- Sep 24, 2008 3:20:01 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Product_Planning WHERE PP_Product_Planning_ID=50200
;

-- Sep 24, 2008 3:20:14 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsIssue,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 15:20:13','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','Y','N','Y','Y',136,50005,0,0,0,5.000000000000,'POQ',0,50215,50000,0,0,TO_TIMESTAMP('2008-09-24 15:20:13','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:20:23 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsIssue,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 15:20:22','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','Y','N','Y','Y',136,50003,0,0,0,5.000000000000,'POQ',0,50216,50000,0,0,TO_TIMESTAMP('2008-09-24 15:20:22','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:20:31 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsIssue,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 15:20:31','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','Y','N','Y','Y',136,50004,0,0,0,5.000000000000,'POQ',0,50217,50000,0,0,TO_TIMESTAMP('2008-09-24 15:20:31','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:21:15 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsIssue,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 15:21:14','YYYY-MM-DD HH24:MI:SS'),100,50001,0,'Y','Y','Y','Y','N','Y','Y',136,50006,0,0,0,5.000000000000,'POQ',0,50218,50000,0,0,TO_TIMESTAMP('2008-09-24 15:21:14','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:21:21 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET DD_NetworkDistribution_ID=50001,Updated=TO_TIMESTAMP('2008-09-24 15:21:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50217
;

-- Sep 24, 2008 3:21:26 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET DD_NetworkDistribution_ID=50001,Updated=TO_TIMESTAMP('2008-09-24 15:21:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50216
;

-- Sep 24, 2008 3:21:30 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET DD_NetworkDistribution_ID=50001,Updated=TO_TIMESTAMP('2008-09-24 15:21:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50215
;

-- Sep 24, 2008 3:21:36 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET DD_NetworkDistribution_ID=50001,Updated=TO_TIMESTAMP('2008-09-24 15:21:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50147
;

-- Sep 24, 2008 3:22:38 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Product_Planning WHERE PP_Product_Planning_ID=50036
;

-- Sep 24, 2008 3:22:51 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET DD_NetworkDistribution_ID=50001,Updated=TO_TIMESTAMP('2008-09-24 15:22:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50148
;

-- Sep 24, 2008 3:23:02 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET DD_NetworkDistribution_ID=50001,Updated=TO_TIMESTAMP('2008-09-24 15:23:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50201
;

-- Sep 24, 2008 3:23:13 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsIssue,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,TransfertTime,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 15:23:13','YYYY-MM-DD HH24:MI:SS'),100,50001,0,'Y','Y','Y','Y','N','Y','Y',50007,50003,0,0,0,5.000000000000,'POQ',0,50219,50005,0,0,0,TO_TIMESTAMP('2008-09-24 15:23:13','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:23:30 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsIssue,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,TransfertTime,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 15:23:29','YYYY-MM-DD HH24:MI:SS'),100,50001,0,'Y','Y','Y','Y','N','Y','Y',50007,50004,0,0,0,5.000000000000,'POQ',0,50220,50005,0,0,0,TO_TIMESTAMP('2008-09-24 15:23:29','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:23:44 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsIssue,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,S_Resource_ID,SafetyStock,TimeFence,TransfertTime,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 15:23:44','YYYY-MM-DD HH24:MI:SS'),100,50001,0,'Y','Y','Y','Y','N','Y','Y',50007,50006,0,0,0,5.000000000000,'POQ',0,50221,50005,0,0,0,TO_TIMESTAMP('2008-09-24 15:23:44','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 3:24:00 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET Planner_ID=102,Updated=TO_TIMESTAMP('2008-09-24 15:24:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50148
;

-- Sep 24, 2008 3:24:02 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET Planner_ID=102,Updated=TO_TIMESTAMP('2008-09-24 15:24:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50201
;

-- Sep 24, 2008 3:24:05 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET Planner_ID=102,Updated=TO_TIMESTAMP('2008-09-24 15:24:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50219
;

-- Sep 24, 2008 3:24:07 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET Planner_ID=102,Updated=TO_TIMESTAMP('2008-09-24 15:24:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50220
;

-- Sep 24, 2008 3:24:11 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET Planner_ID=102,Updated=TO_TIMESTAMP('2008-09-24 15:24:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50221
;

-- Sep 24, 2008 3:24:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET Planner_ID=102,Updated=TO_TIMESTAMP('2008-09-24 15:24:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50147
;

-- Sep 24, 2008 3:24:20 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET Planner_ID=102,Updated=TO_TIMESTAMP('2008-09-24 15:24:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50215
;

-- Sep 24, 2008 3:24:22 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET Planner_ID=102,Updated=TO_TIMESTAMP('2008-09-24 15:24:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50216
;

-- Sep 24, 2008 3:24:24 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET Planner_ID=102,Updated=TO_TIMESTAMP('2008-09-24 15:24:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50217
;

-- Sep 24, 2008 3:24:29 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET Planner_ID=102,Updated=TO_TIMESTAMP('2008-09-24 15:24:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50218
;

-- Sep 24, 2008 3:24:46 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET Planner_ID=101,Updated=TO_TIMESTAMP('2008-09-24 15:24:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50124
;

-- Sep 24, 2008 3:24:48 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET Planner_ID=101,Updated=TO_TIMESTAMP('2008-09-24 15:24:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50177
;

-- Sep 24, 2008 3:24:50 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET Planner_ID=101,Updated=TO_TIMESTAMP('2008-09-24 15:24:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50212
;

-- Sep 24, 2008 3:24:51 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET Planner_ID=101,Updated=TO_TIMESTAMP('2008-09-24 15:24:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50213
;

-- Sep 24, 2008 3:24:54 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET Planner_ID=101,Updated=TO_TIMESTAMP('2008-09-24 15:24:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50214
;

-- Sep 24, 2008 4:48:41 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:48:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50079
;

-- Sep 24, 2008 4:48:41 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:48:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50080
;

-- Sep 24, 2008 4:48:41 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:48:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50082
;

-- Sep 24, 2008 4:48:41 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:48:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50084
;

-- Sep 24, 2008 4:48:41 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:48:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50085
;

-- Sep 24, 2008 4:48:41 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:48:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50092
;

-- Sep 24, 2008 4:48:41 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:48:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50093
;

-- Sep 24, 2008 4:48:41 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:48:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50094
;

-- Sep 24, 2008 4:48:41 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:48:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50095
;

-- Sep 24, 2008 4:48:41 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=NULL, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:48:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50083
;

-- Sep 24, 2008 4:49:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:49:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50026
;

-- Sep 24, 2008 4:49:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:49:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50027
;

-- Sep 24, 2008 4:49:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:49:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50029
;

-- Sep 24, 2008 4:49:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:49:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50031
;

-- Sep 24, 2008 4:49:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:49:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50032
;

-- Sep 24, 2008 4:49:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:49:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50039
;

-- Sep 24, 2008 4:49:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:49:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50040
;

-- Sep 24, 2008 4:49:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:49:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50041
;

-- Sep 24, 2008 4:49:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:49:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50042
;

-- Sep 24, 2008 4:49:18 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=NULL, DD_NetworkDistribution_ID=NULL, DeliveryTime_Promised=0, IsCreatePlan='Y', IsMPS='Y', IsRequiredDRP='Y', IsRequiredMRP='Y', Order_Max=0, Order_Min=0, Order_Pack=0, Order_Period=5.000000000000, Order_Policy='POQ', Order_Qty=0, Planner_ID=101, SafetyStock=0, TimeFence=0, TransfertTime=0, WorkingTime=0, Yield=0,Updated=TO_TIMESTAMP('2008-09-24 16:49:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50030
;

-- Sep 24, 2008 5:31:55 PM CDT
-- Manufacturing Demo
UPDATE M_Product SET M_Locator_ID=NULL,Updated=TO_TIMESTAMP('2008-09-24 17:31:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50007
;

-- Sep 24, 2008 5:33:12 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsIssue,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,TransfertTime,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50006,TO_TIMESTAMP('2008-09-24 17:33:00','YYYY-MM-DD HH24:MI:SS'),100,50001,0,'Y','Y','Y','Y','N','Y','Y',50007,50006,0,0,0,5.000000000000,'POQ',0,50222,102,50000,0,0,0,TO_TIMESTAMP('2008-09-24 17:33:00','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 5:33:29 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Product_Planning WHERE PP_Product_Planning_ID=50221
;

-- Sep 24, 2008 5:33:45 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsIssue,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,TransfertTime,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50004,TO_TIMESTAMP('2008-09-24 17:33:43','YYYY-MM-DD HH24:MI:SS'),100,50001,0,'Y','Y','Y','Y','N','Y','Y',50007,50004,0,0,0,5.000000000000,'POQ',0,50223,102,50000,0,0,0,TO_TIMESTAMP('2008-09-24 17:33:43','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 5:33:54 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Product_Planning WHERE PP_Product_Planning_ID=50220
;

-- Sep 24, 2008 5:34:11 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsIssue,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,TransfertTime,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50005,TO_TIMESTAMP('2008-09-24 17:34:09','YYYY-MM-DD HH24:MI:SS'),100,50001,0,'Y','Y','Y','Y','N','Y','Y',50007,50005,0,0,0,5.000000000000,'POQ',0,50224,102,50000,0,0,0,TO_TIMESTAMP('2008-09-24 17:34:09','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 5:34:33 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsIssue,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,TransfertTime,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,50002,TO_TIMESTAMP('2008-09-24 17:34:31','YYYY-MM-DD HH24:MI:SS'),100,50001,0,'Y','Y','Y','Y','N','Y','Y',50007,50003,0,0,0,5.000000000000,'POQ',0,50225,102,50000,0,0,0,TO_TIMESTAMP('2008-09-24 17:34:31','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 24, 2008 5:34:40 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Product_Planning WHERE PP_Product_Planning_ID=50219
;

-- Sep 24, 2008 5:34:49 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Product_Planning WHERE PP_Product_Planning_ID=50201
;

-- Sep 24, 2008 5:44:29 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Product_Planning WHERE PP_Product_Planning_ID=50124
;

-- Sep 24, 2008 5:45:05 PM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET PP_Product_BOM_ID=145,Updated=TO_TIMESTAMP('2008-09-24 17:45:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50009
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'MOP' AND DocStatus='CL' AND AD_Client_ID=11 AND AD_Org_ID=12 AND M_Warehouse_ID=104 AND S_Resource_ID=50005
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'POR' AND DocStatus='CL' AND AD_Client_ID = 11 AND AD_Org_ID=12 AND M_Warehouse_ID=104 AND S_Resource_ID=50005
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000131 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000131
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000137 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000137
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000051
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000132 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000132
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000138 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000138
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000052
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000133 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000133
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000139 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000139
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000053
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000134 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000134
;

-- Sep 24, 2008 5:45:46 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000054
;

-- Sep 24, 2008 5:45:47 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000135 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:45:47 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000135
;

-- Sep 24, 2008 5:45:47 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000055
;

-- Sep 24, 2008 5:45:47 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000136 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:45:47 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000136
;

-- Sep 24, 2008 5:45:47 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000056
;

-- Sep 24, 2008 5:45:47 PM CDT
-- Manufacturing Demo
UPDATE PP_MRP SET IsAvailable ='Y' WHERE TypeMRP = 'S' AND AD_Client_ID = 11 AND AD_Org_ID=12 AND M_Warehouse_ID=104
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'MOP' AND DocStatus='CL' AND AD_Client_ID=11 AND AD_Org_ID=50005 AND M_Warehouse_ID=50005 AND S_Resource_ID=50005
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'POR' AND DocStatus='CL' AND AD_Client_ID = 11 AND AD_Org_ID=50005 AND M_Warehouse_ID=50005 AND S_Resource_ID=50005
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000140 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000140
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000143 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000143
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000146 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000146
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000057
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000141 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000141
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000144 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000144
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000147 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000147
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000058
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000142 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000142
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000145 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000145
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000148 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000148
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000059
;

-- Sep 24, 2008 5:46:03 PM CDT
-- Manufacturing Demo
UPDATE PP_MRP SET IsAvailable ='Y' WHERE TypeMRP = 'S' AND AD_Client_ID = 11 AND AD_Org_ID=50005 AND M_Warehouse_ID=50005
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'MOP' AND DocStatus='CL' AND AD_Client_ID=11 AND AD_Org_ID=50002 AND M_Warehouse_ID=50003 AND S_Resource_ID=50005
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'POR' AND DocStatus='CL' AND AD_Client_ID = 11 AND AD_Org_ID=50002 AND M_Warehouse_ID=50003 AND S_Resource_ID=50005
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000149 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000149
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000152 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000152
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000155 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000155
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000060
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000150 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000150
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000153 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000153
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000156 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000156
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000061
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000151 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000151
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000154 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000154
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000157 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000157
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000062
;

-- Sep 24, 2008 5:46:15 PM CDT
-- Manufacturing Demo
UPDATE PP_MRP SET IsAvailable ='Y' WHERE TypeMRP = 'S' AND AD_Client_ID = 11 AND AD_Org_ID=50002 AND M_Warehouse_ID=50003
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'MOP' AND DocStatus='CL' AND AD_Client_ID=11 AND AD_Org_ID=50004 AND M_Warehouse_ID=50004 AND S_Resource_ID=50005
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'POR' AND DocStatus='CL' AND AD_Client_ID = 11 AND AD_Org_ID=50004 AND M_Warehouse_ID=50004 AND S_Resource_ID=50005
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000177 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000177
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000180 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000180
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000183 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000183
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000069
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000178 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000178
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000181 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000181
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000184 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000184
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000070
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000179 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000179
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000182 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000182
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000185 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000185
;

-- Sep 24, 2008 5:46:25 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000071
;

-- Sep 24, 2008 5:46:26 PM CDT
-- Manufacturing Demo
UPDATE PP_MRP SET IsAvailable ='Y' WHERE TypeMRP = 'S' AND AD_Client_ID = 11 AND AD_Org_ID=50004 AND M_Warehouse_ID=50004
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'MOP' AND DocStatus='CL' AND AD_Client_ID=11 AND AD_Org_ID=50006 AND M_Warehouse_ID=50006 AND S_Resource_ID=50005
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'POR' AND DocStatus='CL' AND AD_Client_ID = 11 AND AD_Org_ID=50006 AND M_Warehouse_ID=50006 AND S_Resource_ID=50005
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000168 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000168
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000171 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000171
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000174 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000174
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000066
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000169 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000169
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000172 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000172
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000175 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000175
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000067
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000170 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000170
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000173 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000173
;

-- Sep 24, 2008 5:46:33 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000176 AND AD_Client_ID = 11
;

-- Sep 24, 2008 5:46:34 PM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000176
;

-- Sep 24, 2008 5:46:34 PM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000068
;

-- Sep 24, 2008 5:46:34 PM CDT
-- Manufacturing Demo
UPDATE PP_MRP SET IsAvailable ='Y' WHERE TypeMRP = 'S' AND AD_Client_ID = 11 AND AD_Org_ID=50006 AND M_Warehouse_ID=50006
;

-- Sep 24, 2008 6:05:55 PM CDT
-- Manufacturing Demo
UPDATE S_Resource SET PlanningHorizon=120,Updated=TO_TIMESTAMP('2008-09-24 18:05:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE S_Resource_ID=50005
;

-- Sep 24, 2008 6:06:03 PM CDT
-- Manufacturing Demo
UPDATE S_Resource SET PlanningHorizon=120,Updated=TO_TIMESTAMP('2008-09-24 18:06:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE S_Resource_ID=50000
;

-- Sep 24, 2008 6:25:58 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Product_Planning (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DD_NetworkDistribution_ID,DeliveryTime_Promised,IsActive,IsCreatePlan,IsIssue,IsMPS,IsPhantom,IsRequiredDRP,IsRequiredMRP,M_Product_ID,M_Warehouse_ID,Order_Max,Order_Min,Order_Pack,Order_Period,Order_Policy,Order_Qty,PP_Product_Planning_ID,Planner_ID,S_Resource_ID,SafetyStock,TimeFence,TransfertTime,Updated,UpdatedBy,WorkingTime,Yield) VALUES (11,12,TO_TIMESTAMP('2008-09-24 18:25:56','YYYY-MM-DD HH24:MI:SS'),100,50000,0,'Y','Y','Y','Y','N','Y','Y',145,104,0,0,0,5.000000000000,'POQ',0,50226,101,50005,0,0,0,TO_TIMESTAMP('2008-09-24 18:25:56','YYYY-MM-DD HH24:MI:SS'),100,0,0)
;

-- Sep 25, 2008 2:03:59 AM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsPurchased='N',Updated=TO_TIMESTAMP('2008-09-25 02:03:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=145
;

-- Sep 25, 2008 2:05:36 AM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsPurchased='N',Updated=TO_TIMESTAMP('2008-09-25 02:05:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=133
;

-- Sep 25, 2008 2:06:11 AM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsPurchased='N',Updated=TO_TIMESTAMP('2008-09-25 02:06:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50007
;

-- Sep 25, 2008 2:06:16 AM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsVerified='Y', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-25 02:06:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50007
;

-- Sep 25, 2008 2:06:16 AM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsVerified='Y', LowLevel=1,Updated=TO_TIMESTAMP('2008-09-25 02:06:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50008
;

-- Sep 25, 2008 2:06:16 AM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsVerified='Y', LowLevel=1,Updated=TO_TIMESTAMP('2008-09-25 02:06:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50014
;

-- Sep 25, 2008 2:06:28 AM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsPurchased='N',Updated=TO_TIMESTAMP('2008-09-25 02:06:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=136
;

-- Sep 25, 2008 2:06:30 AM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsVerified='Y', LowLevel=0,Updated=TO_TIMESTAMP('2008-09-25 02:06:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=136
;

-- Sep 25, 2008 2:06:30 AM CDT
-- Manufacturing Demo
UPDATE M_Product SET IsVerified='Y', LowLevel=1,Updated=TO_TIMESTAMP('2008-09-25 02:06:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE M_Product_ID=50013
;

-- Sep 25, 2008 2:07:55 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'MOP' AND DocStatus='CL' AND AD_Client_ID=11 AND AD_Org_ID=50000 AND M_Warehouse_ID=50001 AND S_Resource_ID=50005
;

-- Sep 25, 2008 2:07:55 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'POR' AND DocStatus='CL' AND AD_Client_ID = 11 AND AD_Org_ID=50000 AND M_Warehouse_ID=50001 AND S_Resource_ID=50005
;

-- Sep 25, 2008 2:08:00 AM CDT
-- Manufacturing Demo
DELETE FROM M_RequisitionLine WHERE M_RequisitionLine_ID=1000159
;

-- Sep 25, 2008 2:08:00 AM CDT
-- Manufacturing Demo
UPDATE M_Requisition SET TotalLines=(SELECT COALESCE(SUM(LineNetAmt),0) FROM M_RequisitionLine rl WHERE M_Requisition.M_Requisition_ID=rl.M_Requisition_ID) WHERE M_Requisition_ID=1000159
;

-- Sep 25, 2008 2:08:00 AM CDT
-- Manufacturing Demo
DELETE FROM M_Requisition WHERE M_Requisition_ID=1000159
;

-- Sep 25, 2008 2:08:00 AM CDT
-- Manufacturing Demo
DELETE FROM M_RequisitionLine WHERE M_RequisitionLine_ID=1000160
;

-- Sep 25, 2008 2:08:00 AM CDT
-- Manufacturing Demo
UPDATE M_Requisition SET TotalLines=(SELECT COALESCE(SUM(LineNetAmt),0) FROM M_RequisitionLine rl WHERE M_Requisition.M_Requisition_ID=rl.M_Requisition_ID) WHERE M_Requisition_ID=1000160
;

-- Sep 25, 2008 2:08:00 AM CDT
-- Manufacturing Demo
DELETE FROM M_Requisition WHERE M_Requisition_ID=1000160
;

-- Sep 25, 2008 2:08:00 AM CDT
-- Manufacturing Demo
DELETE FROM M_RequisitionLine WHERE M_RequisitionLine_ID=1000161
;

-- Sep 25, 2008 2:08:00 AM CDT
-- Manufacturing Demo
UPDATE M_Requisition SET TotalLines=(SELECT COALESCE(SUM(LineNetAmt),0) FROM M_RequisitionLine rl WHERE M_Requisition.M_Requisition_ID=rl.M_Requisition_ID) WHERE M_Requisition_ID=1000161
;

-- Sep 25, 2008 2:08:00 AM CDT
-- Manufacturing Demo
DELETE FROM M_Requisition WHERE M_Requisition_ID=1000161
;

-- Sep 25, 2008 2:08:00 AM CDT
-- Manufacturing Demo
DELETE FROM M_RequisitionLine WHERE M_RequisitionLine_ID=1000162
;

-- Sep 25, 2008 2:08:00 AM CDT
-- Manufacturing Demo
UPDATE M_Requisition SET TotalLines=(SELECT COALESCE(SUM(LineNetAmt),0) FROM M_RequisitionLine rl WHERE M_Requisition.M_Requisition_ID=rl.M_Requisition_ID) WHERE M_Requisition_ID=1000162
;

-- Sep 25, 2008 2:08:00 AM CDT
-- Manufacturing Demo
DELETE FROM M_Requisition WHERE M_Requisition_ID=1000162
;

-- Sep 25, 2008 2:08:00 AM CDT
-- Manufacturing Demo
DELETE FROM M_RequisitionLine WHERE M_RequisitionLine_ID=1000163
;

-- Sep 25, 2008 2:08:00 AM CDT
-- Manufacturing Demo
UPDATE M_Requisition SET TotalLines=(SELECT COALESCE(SUM(LineNetAmt),0) FROM M_RequisitionLine rl WHERE M_Requisition.M_Requisition_ID=rl.M_Requisition_ID) WHERE M_Requisition_ID=1000163
;

-- Sep 25, 2008 2:08:00 AM CDT
-- Manufacturing Demo
DELETE FROM M_Requisition WHERE M_Requisition_ID=1000163
;

-- Sep 25, 2008 2:08:01 AM CDT
-- Manufacturing Demo
DELETE FROM M_RequisitionLine WHERE M_RequisitionLine_ID=1000164
;

-- Sep 25, 2008 2:08:01 AM CDT
-- Manufacturing Demo
UPDATE M_Requisition SET TotalLines=(SELECT COALESCE(SUM(LineNetAmt),0) FROM M_RequisitionLine rl WHERE M_Requisition.M_Requisition_ID=rl.M_Requisition_ID) WHERE M_Requisition_ID=1000164
;

-- Sep 25, 2008 2:08:01 AM CDT
-- Manufacturing Demo
DELETE FROM M_Requisition WHERE M_Requisition_ID=1000164
;

-- Sep 25, 2008 2:08:01 AM CDT
-- Manufacturing Demo
UPDATE PP_MRP SET IsAvailable ='Y' WHERE TypeMRP = 'S' AND AD_Client_ID = 11 AND AD_Org_ID=50000 AND M_Warehouse_ID=50001
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'MOP' AND DocStatus='CL' AND AD_Client_ID=11 AND AD_Org_ID=12 AND M_Warehouse_ID=104 AND S_Resource_ID=50005
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'POR' AND DocStatus='CL' AND AD_Client_ID = 11 AND AD_Org_ID=12 AND M_Warehouse_ID=104 AND S_Resource_ID=50005
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000297 AND AD_Client_ID = 11
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000297
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000299 AND AD_Client_ID = 11
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000299
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000300 AND AD_Client_ID = 11
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000300
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000302 AND AD_Client_ID = 11
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000302
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000303 AND AD_Client_ID = 11
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000303
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000114
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000298 AND AD_Client_ID = 11
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000298
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000301 AND AD_Client_ID = 11
;

-- Sep 25, 2008 2:09:24 AM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000301
;

-- Sep 25, 2008 2:09:25 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000304 AND AD_Client_ID = 11
;

-- Sep 25, 2008 2:09:25 AM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000304
;

-- Sep 25, 2008 2:09:25 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE DD_OrderLine_ID = 1000305 AND AD_Client_ID = 11
;

-- Sep 25, 2008 2:09:25 AM CDT
-- Manufacturing Demo
DELETE FROM DD_OrderLine WHERE DD_OrderLine_ID=1000305
;

-- Sep 25, 2008 2:09:25 AM CDT
-- Manufacturing Demo
DELETE FROM DD_Order WHERE DD_Order_ID=1000115
;

-- Sep 25, 2008 2:09:25 AM CDT
-- Manufacturing Demo
UPDATE PP_MRP SET IsAvailable ='Y' WHERE TypeMRP = 'S' AND AD_Client_ID = 11 AND AD_Org_ID=12 AND M_Warehouse_ID=104
;

-- Sep 25, 2008 2:10:21 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'MOP' AND DocStatus='CL' AND AD_Client_ID=11 AND AD_Org_ID=12 AND M_Warehouse_ID=104 AND S_Resource_ID=50005
;

-- Sep 25, 2008 2:10:21 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'POR' AND DocStatus='CL' AND AD_Client_ID = 11 AND AD_Org_ID=12 AND M_Warehouse_ID=104 AND S_Resource_ID=50005
;

-- Sep 25, 2008 2:10:21 AM CDT
-- Manufacturing Demo
UPDATE PP_MRP SET IsAvailable ='Y' WHERE TypeMRP = 'S' AND AD_Client_ID = 11 AND AD_Org_ID=12 AND M_Warehouse_ID=104
;

-- Sep 25, 2008 2:11:15 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'MOP' AND DocStatus='CL' AND AD_Client_ID=11 AND AD_Org_ID=12 AND M_Warehouse_ID=104 AND S_Resource_ID=50005
;

-- Sep 25, 2008 2:11:15 AM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'POR' AND DocStatus='CL' AND AD_Client_ID = 11 AND AD_Org_ID=12 AND M_Warehouse_ID=104 AND S_Resource_ID=50005
;

-- Sep 25, 2008 2:11:15 AM CDT
-- Manufacturing Demo
UPDATE PP_MRP SET IsAvailable ='Y' WHERE TypeMRP = 'S' AND AD_Client_ID = 11 AND AD_Org_ID=12 AND M_Warehouse_ID=104
;

-- Sep 25, 2008 9:28:43 AM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=50016, PP_Product_BOM_ID=50006,Updated=TO_TIMESTAMP('2008-09-25 09:28:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50038
;

-- Sep 25, 2008 9:42:31 AM CDT
-- Manufacturing Demo
INSERT INTO AD_Workflow (AD_Client_ID,AD_Org_ID,AD_Workflow_ID,AccessLevel,Author,Cost,Created,CreatedBy,DocumentNo,Duration,DurationUnit,EntityType,IsActive,IsDefault,IsValid,"limit",MovingTime,Name,Priority,ProcessType,PublishStatus,QtyBatchSize,QueuingTime,SetupTime,Updated,UpdatedBy,ValidateWorkflow,Value,Version,WaitingTime,WorkflowType,WorkingTime) VALUES (11,0,50018,'7','Victor Perez',0,TO_TIMESTAMP('2008-09-25 09:42:22','YYYY-MM-DD HH24:MI:SS'),100,'10000003',0,'m','EE01','Y','N','N',0,0,'Patio Furniture Set',0,'CF','R',1.000000000000,0,0,TO_TIMESTAMP('2008-09-25 09:42:22','YYYY-MM-DD HH24:MI:SS'),100,'N','PatioSet',0,0,'M',0)
;

-- Sep 25, 2008 9:42:31 AM CDT
-- Manufacturing Demo
INSERT INTO AD_Workflow_Trl (AD_Language,AD_Workflow_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Workflow_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Workflow t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Workflow_ID=50018 AND EXISTS (SELECT * FROM AD_Workflow_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Workflow_ID!=t.AD_Workflow_ID)
;

-- Sep 25, 2008 9:42:31 AM CDT
-- Manufacturing Demo
INSERT INTO AD_Workflow_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (11,0,102,50018,TO_TIMESTAMP('2008-09-25 09:42:31','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-09-25 09:42:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 25, 2008 9:46:59 AM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,"action",Cost,Created,CreatedBy,DocAction,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,IsMilestone,IsSubcontracting,JoinElement,"limit",MovingTime,Name,OverlapUnits,Priority,QueuingTime,S_Resource_ID,SetupTime,SplitElement,UnitsCycles,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (11,0,50093,50018,'D',0,TO_TIMESTAMP('2008-09-25 09:46:58','YYYY-MM-DD HH24:MI:SS'),100,'CO',20,0,'EE01','Y','Y','N','N','X',0,0,'Assembly',0,0,0,50001,0,'X',0,TO_TIMESTAMP('2008-09-25 09:46:58','YYYY-MM-DD HH24:MI:SS'),100,'10',0,0,0,0,0)
;

-- Sep 25, 2008 9:46:59 AM CDT
-- Manufacturing Demo
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50093 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Sep 25, 2008 9:47:07 AM CDT
-- Manufacturing Demo
UPDATE AD_Workflow SET AD_WF_Node_ID=50093, IsValid='Y',Updated=TO_TIMESTAMP('2008-09-25 09:47:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Workflow_ID=50018
;

-- Sep 25, 2008 9:47:27 AM CDT
-- Manufacturing Demo
UPDATE PP_Product_Planning SET AD_Workflow_ID=50018,Updated=TO_TIMESTAMP('2008-09-25 09:47:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Product_Planning_ID=50009
;

-- Sep 25, 2008 5:56:05 PM CDT
-- Manufacturing Demo
UPDATE S_Resource SET DailyCapacity=8.000000000000,Updated=TO_TIMESTAMP('2008-09-25 17:56:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE S_Resource_ID=50001
;

-- Sep 25, 2008 5:56:10 PM CDT
-- Manufacturing Demo
UPDATE S_Resource SET DailyCapacity=8.000000000000,Updated=TO_TIMESTAMP('2008-09-25 17:56:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE S_Resource_ID=50003
;

-- Sep 25, 2008 5:56:15 PM CDT
-- Manufacturing Demo
UPDATE S_Resource SET DailyCapacity=8.000000000000,Updated=TO_TIMESTAMP('2008-09-25 17:56:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE S_Resource_ID=50008
;

-- Sep 25, 2008 5:56:20 PM CDT
-- Manufacturing Demo
UPDATE S_Resource SET DailyCapacity=8.000000000000,Updated=TO_TIMESTAMP('2008-09-25 17:56:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE S_Resource_ID=50009
;

-- Sep 25, 2008 5:56:29 PM CDT
-- Manufacturing Demo
UPDATE S_Resource SET DailyCapacity=8.000000000000,Updated=TO_TIMESTAMP('2008-09-25 17:56:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE S_Resource_ID=50004
;

-- Sep 25, 2008 5:56:33 PM CDT
-- Manufacturing Demo
UPDATE S_Resource SET DailyCapacity=8.000000000000,Updated=TO_TIMESTAMP('2008-09-25 17:56:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE S_Resource_ID=50007
;

-- Sep 25, 2008 5:56:37 PM CDT
-- Manufacturing Demo
UPDATE S_Resource SET DailyCapacity=8.000000000000,Updated=TO_TIMESTAMP('2008-09-25 17:56:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE S_Resource_ID=50006
;

-- Sep 25, 2008 5:56:42 PM CDT
-- Manufacturing Demo
UPDATE S_Resource SET DailyCapacity=8.000000000000,Updated=TO_TIMESTAMP('2008-09-25 17:56:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE S_Resource_ID=50002
;

-- Sep 25, 2008 6:02:03 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'MOP' AND DocStatus='CL' AND AD_Client_ID=11 AND AD_Org_ID=50001 AND M_Warehouse_ID=50002 AND S_Resource_ID=50000
;

-- Sep 25, 2008 6:02:03 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE OrderType = 'POR' AND DocStatus='CL' AND AD_Client_ID = 11 AND AD_Org_ID=50001 AND M_Warehouse_ID=50002 AND S_Resource_ID=50000
;

-- Sep 25, 2008 6:02:05 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Order_Node WHERE PP_Order_Node_ID=1000000
;

-- Sep 25, 2008 6:02:05 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Order_Workflow WHERE PP_Order_Workflow_ID=1000000
;

-- Sep 25, 2008 6:02:06 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Order_BOMLine WHERE PP_Order_BOMLine_ID=1000000
;

-- Sep 25, 2008 6:02:06 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Order_BOMLine WHERE PP_Order_BOMLine_ID=1000001
;

-- Sep 25, 2008 6:02:06 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Order_BOM WHERE PP_Order_BOM_ID=1000000
;

-- Sep 25, 2008 6:02:06 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE PP_Order_ID = 1000000 AND AD_Client_ID = 11
;

-- Sep 25, 2008 6:02:06 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Order WHERE PP_Order_ID=1000000
;

-- Sep 25, 2008 6:02:06 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Order_Node WHERE PP_Order_Node_ID=1000001
;

-- Sep 25, 2008 6:02:07 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Order_Workflow WHERE PP_Order_Workflow_ID=1000001
;

-- Sep 25, 2008 6:02:07 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Order_BOMLine WHERE PP_Order_BOMLine_ID=1000002
;

-- Sep 25, 2008 6:02:07 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Order_BOMLine WHERE PP_Order_BOMLine_ID=1000003
;

-- Sep 25, 2008 6:02:07 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Order_BOM WHERE PP_Order_BOM_ID=1000001
;

-- Sep 25, 2008 6:02:07 PM CDT
-- Manufacturing Demo
DELETE FROM PP_MRP WHERE PP_Order_ID = 1000001 AND AD_Client_ID = 11
;

-- Sep 25, 2008 6:02:07 PM CDT
-- Manufacturing Demo
DELETE FROM PP_Order WHERE PP_Order_ID=1000001
;

-- Sep 25, 2008 6:02:07 PM CDT
-- Manufacturing Demo
UPDATE PP_MRP SET IsAvailable ='Y' WHERE TypeMRP = 'S' AND AD_Client_ID = 11 AND AD_Org_ID=50001 AND M_Warehouse_ID=50002
;

-- Sep 25, 2008 6:02:17 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Note (AD_Client_ID,AD_Message_ID,AD_Note_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,IsActive,Processed,Processing,Record_ID,Reference,TextMsg,Updated,UpdatedBy) VALUES (11,53013,50000,50006,53043,TO_TIMESTAMP('2008-09-25 18:02:07','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N',1001224,'Fertilizer#50 Fertilizer #50','Expedite 

Indicates that a scheduled supply order is due after is needed and should be rescheduled to an earlier date or demand rescheduled to a later date.',TO_TIMESTAMP('2008-09-25 18:02:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 25, 2008 6:02:18 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Note (AD_Client_ID,AD_Message_ID,AD_Note_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,IsActive,Processed,Processing,Record_ID,Reference,TextMsg,Updated,UpdatedBy) VALUES (11,53013,50001,50006,53043,TO_TIMESTAMP('2008-09-25 18:02:17','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N',1001234,'Fertilizer#50 Fertilizer #50','Expedite 

Indicates that a scheduled supply order is due after is needed and should be rescheduled to an earlier date or demand rescheduled to a later date.',TO_TIMESTAMP('2008-09-25 18:02:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 25, 2008 6:02:19 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Note (AD_Client_ID,AD_Message_ID,AD_Note_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,IsActive,Processed,Processing,Record_ID,Reference,TextMsg,Updated,UpdatedBy) VALUES (11,53013,50002,50006,53043,TO_TIMESTAMP('2008-09-25 18:02:18','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N',1001228,'Fertilizer#50 Fertilizer #50','Expedite 

Indicates that a scheduled supply order is due after is needed and should be rescheduled to an earlier date or demand rescheduled to a later date.',TO_TIMESTAMP('2008-09-25 18:02:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 25, 2008 6:02:21 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Note (AD_Client_ID,AD_Message_ID,AD_Note_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,IsActive,Processed,Processing,Record_ID,Reference,TextMsg,Updated,UpdatedBy) VALUES (11,53013,50003,50006,53043,TO_TIMESTAMP('2008-09-25 18:02:19','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N',1001230,'Fertilizer#50 Fertilizer #50','Expedite 

Indicates that a scheduled supply order is due after is needed and should be rescheduled to an earlier date or demand rescheduled to a later date.',TO_TIMESTAMP('2008-09-25 18:02:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 25, 2008 6:02:22 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Note (AD_Client_ID,AD_Message_ID,AD_Note_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,IsActive,Processed,Processing,Record_ID,Reference,TextMsg,Updated,UpdatedBy) VALUES (11,53013,50004,50006,53043,TO_TIMESTAMP('2008-09-25 18:02:21','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N',1001232,'Fertilizer#50 Fertilizer #50','Expedite 

Indicates that a scheduled supply order is due after is needed and should be rescheduled to an earlier date or demand rescheduled to a later date.',TO_TIMESTAMP('2008-09-25 18:02:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 25, 2008 6:02:23 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Order (AD_Client_ID,AD_Org_ID,AD_Workflow_ID,C_DocTypeTarget_ID,C_DocType_ID,C_UOM_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateStartSchedule,DocAction,DocStatus,DocumentNo,IsActive,IsApproved,IsPrinted,IsSOTrx,IsSelected,Line,M_Product_ID,M_Warehouse_ID,PP_Order_ID,PP_Product_BOM_ID,Planner_ID,Posted,PriorityRule,Processed,Processing,QtyBatchSize,QtyBatchs,QtyDelivered,QtyEntered,QtyOrdered,QtyReject,QtyScrap,S_Resource_ID,ScheduleType,Updated,UpdatedBy,Yield) VALUES (11,50006,50017,50002,50002,100,TO_TIMESTAMP('2008-09-25 18:02:22','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-08-17 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-25 18:02:02','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-17 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-16 00:00:00','YYYY-MM-DD HH24:MI:SS'),'CO','DR','8000','Y','N','N','N','N',10,136,50002,50000,50004,101,'N','5','N','N',100.000000000000,1,0,100.000000000000,100.000000000000,0,0,50000,'D',TO_TIMESTAMP('2008-09-25 18:02:22','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 25, 2008 6:02:25 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Order_BOM (AD_Client_ID,AD_Org_ID,BOMType,BOMUse,C_UOM_ID,Created,CreatedBy,Description,DocumentNo,IsActive,M_Product_ID,Name,PP_Order_BOM_ID,PP_Order_ID,Processing,Updated,UpdatedBy,ValidFrom,Value) VALUES (11,50006,'A','M',100,TO_TIMESTAMP('2008-09-25 18:02:24','YYYY-MM-DD HH24:MI:SS'),100,'50 # Bag of Lawn Fertilizer','50003','Y',136,'Fertilizer #50',50000,50000,'N',TO_TIMESTAMP('2008-09-25 18:02:24','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Fertilizer#50')
;

-- Sep 25, 2008 6:02:27 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Order_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,M_Warehouse_ID,PP_Order_BOMLine_ID,PP_Order_BOM_ID,PP_Order_ID,QtyBOM,QtyBatch,QtyDelivered,QtyPost,QtyReject,QtyRequiered,QtyReserved,QtyScrap,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,50006,0,50001,'CO',TO_TIMESTAMP('2008-09-25 18:02:25','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','1',0,10,50008,50002,50000,50000,50000,50.000000000000,0,0,0,0,5050.50505051,0,0,1.000000000000,TO_TIMESTAMP('2008-09-25 18:02:25','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22 00:00:00','YYYY-MM-DD HH24:MI:SS'))
;

-- Sep 25, 2008 6:02:29 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,DocStatus,IsActive,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,PP_Order_BOMLine_ID,PP_Order_ID,Qty,S_Resource_ID,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-25 18:02:27','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-08-17 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-25 18:02:02','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-17 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-25 18:02:27','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-16 00:00:00','YYYY-MM-DD HH24:MI:SS'),'DR','Y',50008,50002,'8000','MOP',50061,50000,50000,5050.50505051,50000,'D',TO_TIMESTAMP('2008-09-25 18:02:27','YYYY-MM-DD HH24:MI:SS'),100,'50045')
;

-- Sep 25, 2008 6:02:30 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Order_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,M_Warehouse_ID,PP_Order_BOMLine_ID,PP_Order_BOM_ID,PP_Order_ID,QtyBOM,QtyBatch,QtyDelivered,QtyPost,QtyReject,QtyRequiered,QtyReserved,QtyScrap,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,50006,0,100,'PK',TO_TIMESTAMP('2008-09-25 18:02:29','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','1',0,20,50013,50002,50001,50000,50000,1.000000000000,0,0,0,0,100.000000000000000000000000,0,0,0,TO_TIMESTAMP('2008-09-25 18:02:29','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22 00:00:00','YYYY-MM-DD HH24:MI:SS'))
;

-- Sep 25, 2008 6:02:31 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,DocStatus,IsActive,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,PP_Order_BOMLine_ID,PP_Order_ID,Qty,S_Resource_ID,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-25 18:02:30','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-08-17 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-25 18:02:02','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-17 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-25 18:02:30','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-16 00:00:00','YYYY-MM-DD HH24:MI:SS'),'DR','Y',50013,50002,'8000','MOP',50062,50001,50000,100.000000000000000000000000,50000,'D',TO_TIMESTAMP('2008-09-25 18:02:30','YYYY-MM-DD HH24:MI:SS'),100,'50046')
;

-- Sep 25, 2008 6:02:33 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Order_Workflow (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,AccessLevel,Author,Cost,Created,CreatedBy,Description,DocumentNo,Duration,DurationUnit,EntityType,IsActive,IsDefault,"limit",MovingTime,Name,PP_Order_ID,PP_Order_Workflow_ID,Priority,ProcessType,PublishStatus,QtyBatchSize,QueuingTime,S_Resource_ID,SetupTime,Updated,UpdatedBy,ValidateWorkflow,Value,Version,WaitingTime,WorkflowType,WorkingTime) VALUES (11,50006,50092,50017,'7','Victor Perez',0,TO_TIMESTAMP('2008-09-25','YYYY-MM-DD'),100,'Production Line to Packing the Fertilizer Bag 50 and 70 kg ','50000',0,'s','EE01','Y','N',0,0,'Fertilizer Packing Process',50000,50000,0,'DR','R',200.000000000000,0,50006,0,TO_TIMESTAMP('2008-09-25 18:02:32','YYYY-MM-DD HH24:MI:SS'),100,'N','Fertilizer Packing ',0,0,'M',0)
;

-- Sep 25, 2008 6:02:34 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Order_Node (Name,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,"action",Cost,Created,CreatedBy,DocAction,Duration,DurationRequiered,IsActive,IsCentrallyMaintained,JoinElement,"limit",MovingTime,PP_Order_Workflow_ID,Priority,QueuingTime,S_Resource_ID,SetupTime,SetupTimeRequiered,SplitElement,Updated,UpdatedBy,Value,WaitingTime,WorkingTime,XPosition,AD_Client_ID,YPosition,EntityType,PP_Order_Node_ID,PP_Order_ID) VALUES ('Packing Process',50006,50092,50017,'D',0,TO_TIMESTAMP('2008-09-25','YYYY-MM-DD'),100,'CO',3,300,'Y','Y','X',0,2,50000,0,0,50006,0,0,'X',TO_TIMESTAMP('2008-09-25 18:02:33','YYYY-MM-DD HH24:MI:SS'),100,'10',1,0,0,11,0,'EE01',50000,50000)
;

-- Sep 25, 2008 6:02:35 PM CDT
-- Manufacturing Demo
UPDATE PP_Order_Workflow SET PP_Order_Node_ID=50000,Updated=TO_TIMESTAMP('2008-09-25 18:02:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Order_Workflow_ID=50000
;

-- Sep 25, 2008 6:02:36 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,DocStatus,IsActive,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,PP_Order_ID,Qty,S_Resource_ID,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-25 18:02:35','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-08-16 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-25 18:02:02','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-17 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-25 18:02:35','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-16 00:00:00','YYYY-MM-DD HH24:MI:SS'),'DR','Y',136,50002,'8000','MOP',50063,50000,100.000000000000,50000,'S',TO_TIMESTAMP('2008-09-25 18:02:35','YYYY-MM-DD HH24:MI:SS'),100,'50047')
;

-- Sep 25, 2008 6:02:36 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Note (AD_Client_ID,AD_Message_ID,AD_Note_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,IsActive,Processed,Processing,Record_ID,Reference,TextMsg,Updated,UpdatedBy) VALUES (11,53013,50005,50006,53043,TO_TIMESTAMP('2008-09-25 18:02:36','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N',1001226,'Fertilizer#50 Fertilizer #50','Expedite 

Indicates that a scheduled supply order is due after is needed and should be rescheduled to an earlier date or demand rescheduled to a later date.',TO_TIMESTAMP('2008-09-25 18:02:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 25, 2008 6:02:37 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Note (AD_Client_ID,AD_Message_ID,AD_Note_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,IsActive,Processed,Processing,Record_ID,Reference,TextMsg,Updated,UpdatedBy) VALUES (11,53013,50006,50006,53043,TO_TIMESTAMP('2008-09-25 18:02:36','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N',1001236,'Fertilizer#70 Fertilizer #70','Expedite 

Indicates that a scheduled supply order is due after is needed and should be rescheduled to an earlier date or demand rescheduled to a later date.',TO_TIMESTAMP('2008-09-25 18:02:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 25, 2008 6:02:38 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Note (AD_Client_ID,AD_Message_ID,AD_Note_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,IsActive,Processed,Processing,Record_ID,Reference,TextMsg,Updated,UpdatedBy) VALUES (11,53013,50007,50006,53043,TO_TIMESTAMP('2008-09-25 18:02:37','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N',1001238,'Fertilizer#70 Fertilizer #70','Expedite 

Indicates that a scheduled supply order is due after is needed and should be rescheduled to an earlier date or demand rescheduled to a later date.',TO_TIMESTAMP('2008-09-25 18:02:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 25, 2008 6:02:39 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Order (AD_Client_ID,AD_Org_ID,AD_Workflow_ID,C_DocTypeTarget_ID,C_DocType_ID,C_UOM_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateStartSchedule,DocAction,DocStatus,DocumentNo,IsActive,IsApproved,IsPrinted,IsSOTrx,IsSelected,Line,M_Product_ID,M_Warehouse_ID,PP_Order_ID,PP_Product_BOM_ID,Planner_ID,Posted,PriorityRule,Processed,Processing,QtyBatchSize,QtyBatchs,QtyDelivered,QtyEntered,QtyOrdered,QtyReject,QtyScrap,S_Resource_ID,ScheduleType,Updated,UpdatedBy,Yield) VALUES (11,50006,50017,50002,50002,100,TO_TIMESTAMP('2008-09-25 18:02:38','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-08-17 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-25 18:02:02','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-17 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-16 00:00:00','YYYY-MM-DD HH24:MI:SS'),'CO','DR','8001','Y','N','N','N','N',10,50007,50002,50001,50005,101,'N','5','N','N',100.000000000000,1,0,100.000000000000,100.000000000000,0,0,50000,'D',TO_TIMESTAMP('2008-09-25 18:02:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 25, 2008 6:02:40 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Order_BOM (AD_Client_ID,AD_Org_ID,BOMType,BOMUse,C_UOM_ID,Created,CreatedBy,Description,DocumentNo,IsActive,M_Product_ID,Name,PP_Order_BOM_ID,PP_Order_ID,Processing,Updated,UpdatedBy,ValidFrom,Value) VALUES (11,50006,'A','M',100,TO_TIMESTAMP('2008-09-25 18:02:39','YYYY-MM-DD HH24:MI:SS'),100,'70 # Bag of Lawn Fertilizer','50004','Y',50007,'Fertilizer #70',50001,50001,'N',TO_TIMESTAMP('2008-09-25 18:02:39','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22 00:00:00','YYYY-MM-DD HH24:MI:SS'),'Fertilizer#70')
;

-- Sep 25, 2008 6:02:41 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Order_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,M_Warehouse_ID,PP_Order_BOMLine_ID,PP_Order_BOM_ID,PP_Order_ID,QtyBOM,QtyBatch,QtyDelivered,QtyPost,QtyReject,QtyRequiered,QtyReserved,QtyScrap,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,50006,0,50001,'CO',TO_TIMESTAMP('2008-09-25 18:02:40','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','1',0,10,50008,50002,50002,50001,50001,70.000000000000,0,0,0,0,7070.70707071,0,0,1.000000000000,TO_TIMESTAMP('2008-09-25 18:02:40','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22 00:00:00','YYYY-MM-DD HH24:MI:SS'))
;

-- Sep 25, 2008 6:02:42 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,DocStatus,IsActive,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,PP_Order_BOMLine_ID,PP_Order_ID,Qty,S_Resource_ID,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-25 18:02:41','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-08-17 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-25 18:02:02','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-17 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-25 18:02:41','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-16 00:00:00','YYYY-MM-DD HH24:MI:SS'),'DR','Y',50008,50002,'8001','MOP',50064,50002,50001,7070.70707071,50000,'D',TO_TIMESTAMP('2008-09-25 18:02:41','YYYY-MM-DD HH24:MI:SS'),100,'50048')
;

-- Sep 25, 2008 6:02:43 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Order_BOMLine (AD_Client_ID,AD_Org_ID,Assay,C_UOM_ID,ComponentType,Created,CreatedBy,Forecast,IsActive,IsCritical,IsQtyPercentage,IssueMethod,LeadTimeOffset,Line,M_Product_ID,M_Warehouse_ID,PP_Order_BOMLine_ID,PP_Order_BOM_ID,PP_Order_ID,QtyBOM,QtyBatch,QtyDelivered,QtyPost,QtyReject,QtyRequiered,QtyReserved,QtyScrap,Scrap,Updated,UpdatedBy,ValidFrom) VALUES (11,50006,0,100,'PK',TO_TIMESTAMP('2008-09-25 18:02:42','YYYY-MM-DD HH24:MI:SS'),100,0,'Y','N','N','1',0,20,50014,50002,50003,50001,50001,1.000000000000,0,0,0,0,100.000000000000000000000000,0,0,0,TO_TIMESTAMP('2008-09-25 18:02:42','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2007-09-22 00:00:00','YYYY-MM-DD HH24:MI:SS'))
;

-- Sep 25, 2008 6:02:44 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,DocStatus,IsActive,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,PP_Order_BOMLine_ID,PP_Order_ID,Qty,S_Resource_ID,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-25 18:02:43','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-08-17 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-25 18:02:02','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-17 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-25 18:02:43','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-16 00:00:00','YYYY-MM-DD HH24:MI:SS'),'DR','Y',50014,50002,'8001','MOP',50065,50003,50001,100.000000000000000000000000,50000,'D',TO_TIMESTAMP('2008-09-25 18:02:43','YYYY-MM-DD HH24:MI:SS'),100,'50049')
;

-- Sep 25, 2008 6:02:45 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Order_Workflow (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,AccessLevel,Author,Cost,Created,CreatedBy,Description,DocumentNo,Duration,DurationUnit,EntityType,IsActive,IsDefault,"limit",MovingTime,Name,PP_Order_ID,PP_Order_Workflow_ID,Priority,ProcessType,PublishStatus,QtyBatchSize,QueuingTime,S_Resource_ID,SetupTime,Updated,UpdatedBy,ValidateWorkflow,Value,Version,WaitingTime,WorkflowType,WorkingTime) VALUES (11,50006,50092,50017,'7','Victor Perez',0,TO_TIMESTAMP('2008-09-25','YYYY-MM-DD'),100,'Production Line to Packing the Fertilizer Bag 50 and 70 kg ','50001',0,'s','EE01','Y','N',0,0,'Fertilizer Packing Process',50001,50001,0,'DR','R',200.000000000000,0,50006,0,TO_TIMESTAMP('2008-09-25 18:02:44','YYYY-MM-DD HH24:MI:SS'),100,'N','Fertilizer Packing ',0,0,'M',0)
;

-- Sep 25, 2008 6:02:46 PM CDT
-- Manufacturing Demo
INSERT INTO PP_Order_Node (Name,AD_Org_ID,AD_WF_Node_ID,AD_Workflow_ID,"action",Cost,Created,CreatedBy,DocAction,Duration,DurationRequiered,IsActive,IsCentrallyMaintained,JoinElement,"limit",MovingTime,PP_Order_Workflow_ID,Priority,QueuingTime,S_Resource_ID,SetupTime,SetupTimeRequiered,SplitElement,Updated,UpdatedBy,Value,WaitingTime,WorkingTime,XPosition,AD_Client_ID,YPosition,EntityType,PP_Order_Node_ID,PP_Order_ID) VALUES ('Packing Process',50006,50092,50017,'D',0,TO_TIMESTAMP('2008-09-25','YYYY-MM-DD'),100,'CO',3,300,'Y','Y','X',0,2,50001,0,0,50006,0,0,'X',TO_TIMESTAMP('2008-09-25 18:02:45','YYYY-MM-DD HH24:MI:SS'),100,'10',1,0,0,11,0,'EE01',50001,50001)
;

-- Sep 25, 2008 6:02:46 PM CDT
-- Manufacturing Demo
UPDATE PP_Order_Workflow SET PP_Order_Node_ID=50001,Updated=TO_TIMESTAMP('2008-09-25 18:02:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_Order_Workflow_ID=50001
;

-- Sep 25, 2008 6:02:47 PM CDT
-- Manufacturing Demo
INSERT INTO PP_MRP (AD_Client_ID,AD_Org_ID,Created,CreatedBy,DateFinishSchedule,DateOrdered,DatePromised,DateSimulation,DateStartSchedule,DocStatus,IsActive,M_Product_ID,M_Warehouse_ID,Name,OrderType,PP_MRP_ID,PP_Order_ID,Qty,S_Resource_ID,TypeMRP,Updated,UpdatedBy,Value) VALUES (11,50006,TO_TIMESTAMP('2008-09-25 18:02:46','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2008-08-16 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-25 18:02:02','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-17 00:00:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-09-25 18:02:46','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2008-08-16 00:00:00','YYYY-MM-DD HH24:MI:SS'),'DR','Y',50007,50002,'8001','MOP',50066,50001,100.000000000000,50000,'S',TO_TIMESTAMP('2008-09-25 18:02:46','YYYY-MM-DD HH24:MI:SS'),100,'50050')
;

-- Sep 25, 2008 6:02:48 PM CDT
-- Manufacturing Demo
INSERT INTO AD_Note (AD_Client_ID,AD_Message_ID,AD_Note_ID,AD_Org_ID,AD_Table_ID,Created,CreatedBy,IsActive,Processed,Processing,Record_ID,Reference,TextMsg,Updated,UpdatedBy) VALUES (11,53013,50008,50006,53043,TO_TIMESTAMP('2008-09-25 18:02:47','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N',1001240,'Fertilizer#70 Fertilizer #70','Expedite 

Indicates that a scheduled supply order is due after is needed and should be rescheduled to an earlier date or demand rescheduled to a later date.',TO_TIMESTAMP('2008-09-25 18:02:47','YYYY-MM-DD HH24:MI:SS'),100)
;



