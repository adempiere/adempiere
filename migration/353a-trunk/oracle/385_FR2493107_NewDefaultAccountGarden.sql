-- Jan 1, 2009 11:54:02 AM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue (AD_Client_ID,AD_Org_ID,AccountSign,AccountType,C_ElementValue_ID,C_Element_ID,Created,CreatedBy,Description,IsActive,IsBankAccount,IsDocControlled,IsForeignCurrency,IsSummary,Name,PostActual,PostBudget,PostEncumbrance,PostStatistical,Updated,UpdatedBy,Value) VALUES (11,11,'N','E',50001,105,TO_DATE('2009-01-01 11:54:00','YYYY-MM-DD HH24:MI:SS'),100,'Account for Using Variance','Y','N','Y','N','N','Using Variance','Y','Y','Y','Y',TO_DATE('2009-01-01 11:54:00','YYYY-MM-DD HH24:MI:SS'),100,'58310')
;

-- Jan 1, 2009 11:54:02 AM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue_Trl (AD_Language,C_ElementValue_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_ElementValue_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_ElementValue t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_ElementValue_ID=50001 AND EXISTS (SELECT * FROM C_ElementValue_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_ElementValue_ID!=t.C_ElementValue_ID)
;

-- Jan 1, 2009 11:54:02 AM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 50001, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=105 AND t.AD_Tree_ID=ae.AD_Tree_ID) AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50001)
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=444
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=445
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=635
;

-- Jan 1, 2009 11:54:09 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50001
;

-- Jan 1, 2009 11:54:36 AM ECT
-- Accounting Manufacturing Management
UPDATE C_ElementValue SET Value='58400',Updated=TO_DATE('2009-01-01 11:54:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ElementValue_ID=50001
;

-- Jan 1, 2009 11:55:11 AM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue (AD_Client_ID,AD_Org_ID,AccountSign,AccountType,C_ElementValue_ID,C_Element_ID,Created,CreatedBy,Description,IsActive,IsBankAccount,IsDocControlled,IsForeignCurrency,IsSummary,Name,PostActual,PostBudget,PostEncumbrance,PostStatistical,Updated,UpdatedBy,Value) VALUES (11,11,'N','E',50002,105,TO_DATE('2009-01-01 11:55:10','YYYY-MM-DD HH24:MI:SS'),100,'Account for Method Change Variance','Y','N','Y','N','N','Method Change Variance','Y','Y','Y','Y',TO_DATE('2009-01-01 11:55:10','YYYY-MM-DD HH24:MI:SS'),100,'58500')
;

-- Jan 1, 2009 11:55:11 AM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue_Trl (AD_Language,C_ElementValue_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_ElementValue_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_ElementValue t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_ElementValue_ID=50002 AND EXISTS (SELECT * FROM C_ElementValue_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_ElementValue_ID!=t.C_ElementValue_ID)
;

-- Jan 1, 2009 11:55:11 AM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 50002, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=105 AND t.AD_Tree_ID=ae.AD_Tree_ID) AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50002)
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=444
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=445
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=635
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50001
;

-- Jan 1, 2009 11:55:16 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50002
;

-- Jan 1, 2009 11:57:07 AM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue (AD_Client_ID,AD_Org_ID,AccountSign,AccountType,C_ElementValue_ID,C_Element_ID,Created,CreatedBy,Description,IsActive,IsBankAccount,IsDocControlled,IsForeignCurrency,IsSummary,Name,PostActual,PostBudget,PostEncumbrance,PostStatistical,Updated,UpdatedBy,Value) VALUES (11,11,'N','E',50003,105,TO_DATE('2009-01-01 11:56:54','YYYY-MM-DD HH24:MI:SS'),100,'Account for Rate Variance','Y','N','Y','N','N','Rate Variance','Y','Y','Y','Y',TO_DATE('2009-01-01 11:56:54','YYYY-MM-DD HH24:MI:SS'),100,'58600')
;

-- Jan 1, 2009 11:57:07 AM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue_Trl (AD_Language,C_ElementValue_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_ElementValue_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_ElementValue t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_ElementValue_ID=50003 AND EXISTS (SELECT * FROM C_ElementValue_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_ElementValue_ID!=t.C_ElementValue_ID)
;

-- Jan 1, 2009 11:57:07 AM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 50003, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=105 AND t.AD_Tree_ID=ae.AD_Tree_ID) AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50003)
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=444
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=445
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=635
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50001
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50002
;

-- Jan 1, 2009 11:57:19 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50003
;

-- Jan 1, 2009 11:58:17 AM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue (AD_Client_ID,AD_Org_ID,AccountSign,AccountType,C_ElementValue_ID,C_Element_ID,Created,CreatedBy,Description,IsActive,IsBankAccount,IsDocControlled,IsForeignCurrency,IsSummary,Name,PostActual,PostBudget,PostEncumbrance,PostStatistical,Updated,UpdatedBy,Value) VALUES (11,11,'N','E',50004,105,TO_DATE('2009-01-01 11:58:12','YYYY-MM-DD HH24:MI:SS'),100,'Account for Rate Variance','Y','N','Y','N','N','Mix Variance','Y','Y','Y','Y',TO_DATE('2009-01-01 11:58:12','YYYY-MM-DD HH24:MI:SS'),100,'58700')
;

-- Jan 1, 2009 11:58:17 AM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue_Trl (AD_Language,C_ElementValue_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_ElementValue_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_ElementValue t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_ElementValue_ID=50004 AND EXISTS (SELECT * FROM C_ElementValue_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_ElementValue_ID!=t.C_ElementValue_ID)
;

-- Jan 1, 2009 11:58:17 AM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 50004, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=105 AND t.AD_Tree_ID=ae.AD_Tree_ID) AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50004)
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=444
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=445
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=635
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50001
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50002
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50003
;

-- Jan 1, 2009 11:58:22 AM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50004
;

-- Jan 1, 2009 12:00:39 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue (AD_Client_ID,AD_Org_ID,AccountSign,AccountType,C_ElementValue_ID,C_Element_ID,Created,CreatedBy,Description,IsActive,IsBankAccount,IsDocControlled,IsForeignCurrency,IsSummary,Name,PostActual,PostBudget,PostEncumbrance,PostStatistical,Updated,UpdatedBy,Value) VALUES (11,11,'N','A',50005,105,TO_DATE('2009-01-01 12:00:37','YYYY-MM-DD HH24:MI:SS'),100,'Work In Process Account','Y','N','Y','N','N','Work In Process','Y','Y','Y','Y',TO_DATE('2009-01-01 12:00:37','YYYY-MM-DD HH24:MI:SS'),100,'14130')
;

-- Jan 1, 2009 12:00:39 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue_Trl (AD_Language,C_ElementValue_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_ElementValue_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_ElementValue t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_ElementValue_ID=50005 AND EXISTS (SELECT * FROM C_ElementValue_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_ElementValue_ID!=t.C_ElementValue_ID)
;

-- Jan 1, 2009 12:00:39 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 50005, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=105 AND t.AD_Tree_ID=ae.AD_Tree_ID) AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50005)
;

-- Jan 1, 2009 12:00:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:00:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:00:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:00:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:00:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:00:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:00:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:00:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:00:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:00:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:00:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:00:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:00:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=541, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=776
;

-- Jan 1, 2009 12:00:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=541, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=742
;

-- Jan 1, 2009 12:00:45 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=541, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50005
;

-- Jan 1, 2009 12:04:25 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue (AD_Client_ID,AD_Org_ID,AccountSign,AccountType,C_ElementValue_ID,C_Element_ID,Created,CreatedBy,Description,IsActive,IsBankAccount,IsDocControlled,IsForeignCurrency,IsSummary,Name,PostActual,PostBudget,PostEncumbrance,PostStatistical,Updated,UpdatedBy,Value) VALUES (11,11,'N','A',50006,105,TO_DATE('2009-01-01 12:04:23','YYYY-MM-DD HH24:MI:SS'),100,'Floor Stock Account','Y','N','N','N','N','Floor Stock','Y','Y','Y','Y',TO_DATE('2009-01-01 12:04:23','YYYY-MM-DD HH24:MI:SS'),100,'14140')
;

-- Jan 1, 2009 12:04:25 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue_Trl (AD_Language,C_ElementValue_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_ElementValue_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_ElementValue t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_ElementValue_ID=50006 AND EXISTS (SELECT * FROM C_ElementValue_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_ElementValue_ID!=t.C_ElementValue_ID)
;

-- Jan 1, 2009 12:04:25 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 50006, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=105 AND t.AD_Tree_ID=ae.AD_Tree_ID) AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50006)
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=541, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=776
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=541, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=742
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=541, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50005
;

-- Jan 1, 2009 12:04:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=541, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50006
;

-- Jan 1, 2009 12:09:27 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue (AD_Client_ID,AD_Org_ID,AccountSign,AccountType,C_ElementValue_ID,C_Element_ID,Created,CreatedBy,Description,IsActive,IsBankAccount,IsDocControlled,IsForeignCurrency,IsSummary,Name,PostActual,PostBudget,PostEncumbrance,PostStatistical,Updated,UpdatedBy,Value) VALUES (11,11,'N','E',50007,105,TO_DATE('2009-01-01 12:09:17','YYYY-MM-DD HH24:MI:SS'),100,'Labor Absorbed account','Y','N','N','N','N','Labor (Absorbed)','Y','Y','Y','Y',TO_DATE('2009-01-01 12:09:17','YYYY-MM-DD HH24:MI:SS'),100,'60140')
;

-- Jan 1, 2009 12:09:27 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue_Trl (AD_Language,C_ElementValue_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_ElementValue_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_ElementValue t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_ElementValue_ID=50007 AND EXISTS (SELECT * FROM C_ElementValue_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_ElementValue_ID!=t.C_ElementValue_ID)
;

-- Jan 1, 2009 12:09:27 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 50007, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=105 AND t.AD_Tree_ID=ae.AD_Tree_ID) AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50007)
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=772
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=636
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=451
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=452
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50007
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=453
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=637
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=454
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=455
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=638
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=456
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=457
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=458
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=639
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=459
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=460
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=461
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=462
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=463
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=19, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=464
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=20, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=465
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=21, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=466
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=22, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=467
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=23, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=468
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=24, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=469
;

-- Jan 1, 2009 12:09:48 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=25, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=470
;

-- Jan 1, 2009 12:09:49 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=26, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=471
;

-- Jan 1, 2009 12:09:49 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=27, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=472
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=772
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=636
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=451
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50007
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=452
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=453
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=637
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=454
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=455
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=638
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=456
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=457
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=458
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=639
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=459
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=460
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=461
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=462
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=463
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=19, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=464
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=20, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=465
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=21, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=466
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=22, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=467
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=23, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=468
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=24, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=469
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=25, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=470
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=26, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=471
;

-- Jan 1, 2009 12:09:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=27, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=472
;

-- Jan 1, 2009 12:15:10 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue (AD_Client_ID,AD_Org_ID,AccountSign,AccountType,C_ElementValue_ID,C_Element_ID,Created,CreatedBy,IsActive,IsBankAccount,IsDocControlled,IsForeignCurrency,IsSummary,Name,PostActual,PostBudget,PostEncumbrance,PostStatistical,Updated,UpdatedBy,Value) VALUES (11,11,'N','E',50008,105,TO_DATE('2009-01-01 12:15:08','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','N','Y','Expense (Absorbed)','Y','Y','Y','Y',TO_DATE('2009-01-01 12:15:08','YYYY-MM-DD HH24:MI:SS'),100,'83')
;

-- Jan 1, 2009 12:15:10 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue_Trl (AD_Language,C_ElementValue_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_ElementValue_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_ElementValue t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_ElementValue_ID=50008 AND EXISTS (SELECT * FROM C_ElementValue_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_ElementValue_ID!=t.C_ElementValue_ID)
;

-- Jan 1, 2009 12:15:10 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 50008, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=105 AND t.AD_Tree_ID=ae.AD_Tree_ID) AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50008)
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=772
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=636
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=451
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=452
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=453
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=637
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=454
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=455
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=638
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=456
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=457
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=458
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=639
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=459
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=460
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=461
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=462
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=463
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=464
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=19, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=465
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=20, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=466
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=21, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=467
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=22, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=468
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=23, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=469
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=24, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=470
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=25, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=471
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=450, SeqNo=26, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=472
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=731, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50007
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=731, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=732
;

-- Jan 1, 2009 12:16:02 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=731, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=733
;

-- Jan 1, 2009 12:16:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=731, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=732
;

-- Jan 1, 2009 12:16:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=731, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=733
;

-- Jan 1, 2009 12:16:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=50008, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50007
;

-- Jan 1, 2009 12:16:51 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue (AD_Client_ID,AD_Org_ID,AccountSign,AccountType,C_ElementValue_ID,C_Element_ID,Created,CreatedBy,Description,IsActive,IsBankAccount,IsDocControlled,IsForeignCurrency,IsSummary,Name,PostActual,PostBudget,PostEncumbrance,PostStatistical,Updated,UpdatedBy,Value) VALUES (11,11,'N','E',50009,105,TO_DATE('2009-01-01 12:16:45','YYYY-MM-DD HH24:MI:SS'),100,'Burden Absorbed account','Y','N','N','N','N','Burden (Absorbed)','Y','Y','Y','Y',TO_DATE('2009-01-01 12:16:45','YYYY-MM-DD HH24:MI:SS'),100,'83200')
;

-- Jan 1, 2009 12:16:51 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue_Trl (AD_Language,C_ElementValue_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_ElementValue_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_ElementValue t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_ElementValue_ID=50009 AND EXISTS (SELECT * FROM C_ElementValue_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_ElementValue_ID!=t.C_ElementValue_ID)
;

-- Jan 1, 2009 12:16:51 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 50009, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=105 AND t.AD_Tree_ID=ae.AD_Tree_ID) AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50009)
;

-- Jan 1, 2009 12:16:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:16:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:16:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:16:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:16:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:16:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:16:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:16:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:16:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:16:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:16:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:16:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:16:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:16:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=50008, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50009
;

-- Jan 1, 2009 12:16:54 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=50008, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50007
;

-- Jan 1, 2009 12:16:57 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=50008, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50009
;

-- Jan 1, 2009 12:16:57 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=50008, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50007
;

-- Jan 1, 2009 12:16:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=50008, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50007
;

-- Jan 1, 2009 12:16:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=50008, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50009
;

-- Jan 1, 2009 12:17:35 PM ECT
-- Accounting Manufacturing Management
UPDATE C_ElementValue SET Value='83100',Updated=TO_DATE('2009-01-01 12:17:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ElementValue_ID=50007
;

-- Jan 1, 2009 12:18:52 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:18:52 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:18:52 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:18:52 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:18:52 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:18:52 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:18:52 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:18:52 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:18:52 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:18:52 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:18:52 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:18:52 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:18:52 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:18:56 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:18:56 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:18:56 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:18:56 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:18:56 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:18:56 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:18:56 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:18:56 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:18:56 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:18:56 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:18:56 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:18:56 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:18:56 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:18:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:18:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:18:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:18:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:18:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:18:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:18:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:18:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:18:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:18:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:18:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:18:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:18:59 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:19:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:19:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:19:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:19:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:19:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:19:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:19:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:19:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:19:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:19:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:19:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:19:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:19:09 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:19:12 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:19:12 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:19:12 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:19:12 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:19:12 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:19:12 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:19:12 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:19:12 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:19:12 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:19:12 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:19:12 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:19:12 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:19:12 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:19:15 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:19:15 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:19:15 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:19:15 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:19:15 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:19:15 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:19:15 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:19:15 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:19:15 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:19:15 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:19:15 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:19:15 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:19:15 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:19:20 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:19:20 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:19:20 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:19:20 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:19:20 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:19:20 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:19:20 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:19:20 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:19:20 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:19:20 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:19:20 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:19:20 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:19:20 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:19:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:19:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:19:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:19:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:19:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:19:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:19:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:19:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:19:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:19:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:19:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:19:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:19:22 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:19:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:19:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:19:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:19:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:19:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:19:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:19:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:19:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:19:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:19:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:19:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:19:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:19:25 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:19:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:19:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:19:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:19:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:19:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:19:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:19:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:19:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:19:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:19:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:19:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:19:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:19:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:19:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:19:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:19:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:19:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:19:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:19:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:19:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:19:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:19:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:19:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:19:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:19:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:19:30 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:19:33 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:19:33 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:19:33 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:19:33 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:19:33 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:19:33 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:19:33 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:19:33 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:19:33 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:19:33 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:19:33 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:19:33 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:19:33 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:19:36 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:19:36 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:19:36 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:19:36 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:19:36 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:19:36 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:19:36 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:19:36 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:19:36 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:19:36 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:19:36 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:19:36 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:19:36 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:21:42 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue (AD_Client_ID,AD_Org_ID,AccountSign,AccountType,C_ElementValue_ID,C_Element_ID,Created,CreatedBy,Description,IsActive,IsBankAccount,IsDocControlled,IsForeignCurrency,IsSummary,Name,PostActual,PostBudget,PostEncumbrance,PostStatistical,Updated,UpdatedBy,Value) VALUES (11,11,'N','E',50010,105,TO_DATE('2009-01-01 12:21:42','YYYY-MM-DD HH24:MI:SS'),100,'Cost Of Production Account','Y','N','Y','N','N','Cost Of Production','Y','Y','Y','Y',TO_DATE('2009-01-01 12:21:42','YYYY-MM-DD HH24:MI:SS'),100,'55000')
;

-- Jan 1, 2009 12:21:42 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue_Trl (AD_Language,C_ElementValue_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_ElementValue_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_ElementValue t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_ElementValue_ID=50010 AND EXISTS (SELECT * FROM C_ElementValue_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_ElementValue_ID!=t.C_ElementValue_ID)
;

-- Jan 1, 2009 12:21:42 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 50010, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=105 AND t.AD_Tree_ID=ae.AD_Tree_ID) AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50010)
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=430
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=431
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=781
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=780
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=778
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=432
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=433
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=434
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50010
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=435
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=438
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=634
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=443
;

-- Jan 1, 2009 12:21:46 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=446
;

-- Jan 1, 2009 12:22:46 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue (AD_Client_ID,AD_Org_ID,AccountSign,AccountType,C_ElementValue_ID,C_Element_ID,Created,CreatedBy,Description,IsActive,IsBankAccount,IsDocControlled,IsForeignCurrency,IsSummary,Name,PostActual,PostBudget,PostEncumbrance,PostStatistical,Updated,UpdatedBy,Value) VALUES (11,11,'N','E',50011,105,TO_DATE('2009-01-01 12:22:45','YYYY-MM-DD HH24:MI:SS'),100,'Outsite Processing Account','Y','N','Y','N','N','Outsite Processing (Subcontract)','Y','Y','Y','Y',TO_DATE('2009-01-01 12:22:45','YYYY-MM-DD HH24:MI:SS'),100,'56000')
;

-- Jan 1, 2009 12:22:46 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue_Trl (AD_Language,C_ElementValue_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_ElementValue_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_ElementValue t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_ElementValue_ID=50011 AND EXISTS (SELECT * FROM C_ElementValue_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_ElementValue_ID!=t.C_ElementValue_ID)
;

-- Jan 1, 2009 12:22:46 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 50011, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=105 AND t.AD_Tree_ID=ae.AD_Tree_ID) AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50011)
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=430
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=431
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=781
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=780
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=778
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=432
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=433
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=434
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50010
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50011
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=435
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=438
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=634
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=443
;

-- Jan 1, 2009 12:22:50 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=446
;

-- Jan 1, 2009 12:23:38 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ValidCombination (AD_Client_ID,AD_Org_ID,Account_ID,C_AcctSchema_ID,C_ValidCombination_ID,Combination,Created,CreatedBy,Description,IsActive,IsFullyQualified,Updated,UpdatedBy) VALUES (11,11,50005,101,50001,'HQ-14130-_-_-_-_',TO_DATE('2009-01-01 12:23:36','YYYY-MM-DD HH24:MI:SS'),100,'HQ-Work In Process-_-_-_-_','Y','Y',TO_DATE('2009-01-01 12:23:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 1, 2009 12:23:59 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ValidCombination (AD_Client_ID,AD_Org_ID,Account_ID,C_AcctSchema_ID,C_ValidCombination_ID,Combination,Created,CreatedBy,Description,IsActive,IsFullyQualified,Updated,UpdatedBy) VALUES (11,11,50006,101,50002,'HQ-14140-_-_-_-_',TO_DATE('2009-01-01 12:23:58','YYYY-MM-DD HH24:MI:SS'),100,'HQ-Floor Stock-_-_-_-_','Y','Y',TO_DATE('2009-01-01 12:23:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 1, 2009 12:24:25 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ValidCombination (AD_Client_ID,AD_Org_ID,Account_ID,C_AcctSchema_ID,C_ValidCombination_ID,Combination,Created,CreatedBy,Description,IsActive,IsFullyQualified,Updated,UpdatedBy) VALUES (11,11,50002,101,50003,'HQ-58500-_-_-_-_',TO_DATE('2009-01-01 12:24:24','YYYY-MM-DD HH24:MI:SS'),100,'HQ-Method Change Variance-_-_-_-_','Y','Y',TO_DATE('2009-01-01 12:24:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 1, 2009 12:24:56 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ValidCombination (AD_Client_ID,AD_Org_ID,Account_ID,C_AcctSchema_ID,C_ValidCombination_ID,Combination,Created,CreatedBy,Description,IsActive,IsFullyQualified,Updated,UpdatedBy) VALUES (11,11,50001,101,50004,'HQ-58400-_-_-_-_',TO_DATE('2009-01-01 12:24:54','YYYY-MM-DD HH24:MI:SS'),100,'HQ-Using Variance-_-_-_-_','Y','Y',TO_DATE('2009-01-01 12:24:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 1, 2009 12:25:26 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ValidCombination (AD_Client_ID,AD_Org_ID,Account_ID,C_AcctSchema_ID,C_ValidCombination_ID,Combination,Created,CreatedBy,Description,IsActive,IsFullyQualified,Updated,UpdatedBy) VALUES (11,11,50003,101,50005,'HQ-58600-_-_-_-_',TO_DATE('2009-01-01 12:25:25','YYYY-MM-DD HH24:MI:SS'),100,'HQ-Rate Variance-_-_-_-_','Y','Y',TO_DATE('2009-01-01 12:25:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 1, 2009 12:25:43 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ValidCombination (AD_Client_ID,AD_Org_ID,Account_ID,C_AcctSchema_ID,C_ValidCombination_ID,Combination,Created,CreatedBy,Description,IsActive,IsFullyQualified,Updated,UpdatedBy) VALUES (11,11,50004,101,50006,'HQ-58700-_-_-_-_',TO_DATE('2009-01-01 12:25:42','YYYY-MM-DD HH24:MI:SS'),100,'HQ-Mix Variance-_-_-_-_','Y','Y',TO_DATE('2009-01-01 12:25:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 1, 2009 12:26:05 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ValidCombination (AD_Client_ID,AD_Org_ID,Account_ID,C_AcctSchema_ID,C_ValidCombination_ID,Combination,Created,CreatedBy,Description,IsActive,IsFullyQualified,Updated,UpdatedBy) VALUES (11,11,50007,101,50007,'HQ-83100-_-_-_-_',TO_DATE('2009-01-01 12:26:05','YYYY-MM-DD HH24:MI:SS'),100,'HQ-Labor (Absorbed)-_-_-_-_','Y','Y',TO_DATE('2009-01-01 12:26:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 1, 2009 12:26:21 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ValidCombination (AD_Client_ID,AD_Org_ID,Account_ID,C_AcctSchema_ID,C_ValidCombination_ID,Combination,Created,CreatedBy,Description,IsActive,IsFullyQualified,Updated,UpdatedBy) VALUES (11,11,50009,101,50008,'HQ-83200-_-_-_-_',TO_DATE('2009-01-01 12:26:19','YYYY-MM-DD HH24:MI:SS'),100,'HQ-Burden (Absorbed)-_-_-_-_','Y','Y',TO_DATE('2009-01-01 12:26:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 1, 2009 12:27:02 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ValidCombination (AD_Client_ID,AD_Org_ID,Account_ID,C_AcctSchema_ID,C_ValidCombination_ID,Combination,Created,CreatedBy,Description,IsActive,IsFullyQualified,Updated,UpdatedBy) VALUES (11,11,50010,101,50009,'HQ-55000-_-_-_-_',TO_DATE('2009-01-01 12:27:01','YYYY-MM-DD HH24:MI:SS'),100,'HQ-Cost Of Production-_-_-_-_','Y','Y',TO_DATE('2009-01-01 12:27:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 1, 2009 12:27:47 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ValidCombination (AD_Client_ID,AD_Org_ID,Account_ID,C_AcctSchema_ID,C_ValidCombination_ID,Combination,Created,CreatedBy,Description,IsActive,IsFullyQualified,Updated,UpdatedBy) VALUES (11,11,50011,101,50010,'HQ-56000-_-_-_-_',TO_DATE('2009-01-01 12:27:46','YYYY-MM-DD HH24:MI:SS'),100,'HQ-Outsite Processing (Subcontract)-_-_-_-_','Y','Y',TO_DATE('2009-01-01 12:27:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 1, 2009 12:31:39 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue (AD_Client_ID,AD_Org_ID,AccountSign,AccountType,C_ElementValue_ID,C_Element_ID,Created,CreatedBy,IsActive,IsBankAccount,IsDocControlled,IsForeignCurrency,IsSummary,Name,PostActual,PostBudget,PostEncumbrance,PostStatistical,Updated,UpdatedBy,Value) VALUES (11,11,'N','M',50014,105,TO_DATE('2009-01-01 12:31:38','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','N','N','Overhead (Applied)','Y','Y','Y','Y',TO_DATE('2009-01-01 12:31:38','YYYY-MM-DD HH24:MI:SS'),100,'913')
;

-- Jan 1, 2009 12:31:39 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue_Trl (AD_Language,C_ElementValue_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_ElementValue_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_ElementValue t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_ElementValue_ID=50014 AND EXISTS (SELECT * FROM C_ElementValue_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_ElementValue_ID!=t.C_ElementValue_ID)
;

-- Jan 1, 2009 12:31:39 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 50014, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=105 AND t.AD_Tree_ID=ae.AD_Tree_ID) AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50014)
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=731, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=732
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=731, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=733
;

-- Jan 1, 2009 12:31:44 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=731, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50014
;

-- Jan 1, 2009 12:36:57 PM ECT
-- Accounting Manufacturing Management
UPDATE C_ElementValue SET Value='51110',Updated=TO_DATE('2009-01-01 12:36:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ElementValue_ID=50010
;

-- Jan 1, 2009 12:36:57 PM ECT
-- Accounting Manufacturing Management
UPDATE C_ValidCombination SET Combination='HQ-51110-_-_-_-_', Description='HQ-Cost Of Production-_-_-_-_',Updated=TO_DATE('2009-01-01 12:36:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ValidCombination_ID=50009
;

-- Jan 1, 2009 12:37:08 PM ECT
-- Accounting Manufacturing Management
UPDATE C_ElementValue SET Value='51120',Updated=TO_DATE('2009-01-01 12:37:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ElementValue_ID=50011
;

-- Jan 1, 2009 12:37:08 PM ECT
-- Accounting Manufacturing Management
UPDATE C_ValidCombination SET Combination='HQ-51120-_-_-_-_', Description='HQ-Outsite Processing (Subcontract)-_-_-_-_',Updated=TO_DATE('2009-01-01 12:37:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ValidCombination_ID=50010
;

-- Jan 1, 2009 12:37:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=430
;

-- Jan 1, 2009 12:37:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50010
;

-- Jan 1, 2009 12:37:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=431
;

-- Jan 1, 2009 12:37:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=781
;

-- Jan 1, 2009 12:37:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=780
;

-- Jan 1, 2009 12:37:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=778
;

-- Jan 1, 2009 12:37:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=432
;

-- Jan 1, 2009 12:37:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=433
;

-- Jan 1, 2009 12:37:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=434
;

-- Jan 1, 2009 12:37:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50011
;

-- Jan 1, 2009 12:37:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=435
;

-- Jan 1, 2009 12:37:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=438
;

-- Jan 1, 2009 12:37:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=634
;

-- Jan 1, 2009 12:37:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=443
;

-- Jan 1, 2009 12:37:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=446
;

-- Jan 1, 2009 12:37:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=430
;

-- Jan 1, 2009 12:37:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50011
;

-- Jan 1, 2009 12:37:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50010
;

-- Jan 1, 2009 12:37:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=431
;

-- Jan 1, 2009 12:37:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=781
;

-- Jan 1, 2009 12:37:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=780
;

-- Jan 1, 2009 12:37:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=778
;

-- Jan 1, 2009 12:37:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=432
;

-- Jan 1, 2009 12:37:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=433
;

-- Jan 1, 2009 12:37:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=434
;

-- Jan 1, 2009 12:37:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=435
;

-- Jan 1, 2009 12:37:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=438
;

-- Jan 1, 2009 12:37:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=634
;

-- Jan 1, 2009 12:37:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=443
;

-- Jan 1, 2009 12:37:31 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=446
;

-- Jan 1, 2009 12:37:45 PM ECT
-- Accounting Manufacturing Management
UPDATE C_ElementValue SET Value='51130',Updated=TO_DATE('2009-01-01 12:37:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ElementValue_ID=50011
;

-- Jan 1, 2009 12:37:45 PM ECT
-- Accounting Manufacturing Management
UPDATE C_ValidCombination SET Combination='HQ-51130-_-_-_-_', Description='HQ-Outsite Processing (Subcontract)-_-_-_-_',Updated=TO_DATE('2009-01-01 12:37:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_ValidCombination_ID=50010
;

-- Jan 1, 2009 12:38:19 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue (AD_Client_ID,AD_Org_ID,AccountSign,AccountType,C_ElementValue_ID,C_Element_ID,Created,CreatedBy,Description,IsActive,IsBankAccount,IsDocControlled,IsForeignCurrency,IsSummary,Name,PostActual,PostBudget,PostEncumbrance,PostStatistical,Updated,UpdatedBy,Value) VALUES (11,11,'N','E',50016,105,TO_DATE('2009-01-01 12:38:19','YYYY-MM-DD HH24:MI:SS'),100,'Scrap Account','Y','N','Y','N','N','Scrap','Y','Y','Y','Y',TO_DATE('2009-01-01 12:38:19','YYYY-MM-DD HH24:MI:SS'),100,'51120')
;

-- Jan 1, 2009 12:38:19 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ElementValue_Trl (AD_Language,C_ElementValue_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_ElementValue_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_ElementValue t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_ElementValue_ID=50016 AND EXISTS (SELECT * FROM C_ElementValue_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_ElementValue_ID!=t.C_ElementValue_ID)
;

-- Jan 1, 2009 12:38:19 PM ECT
-- Accounting Manufacturing Management
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 50016, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=105 AND t.AD_Tree_ID=ae.AD_Tree_ID) AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50016)
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=506
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=584
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=624
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=632
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=429
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=449
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=783
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=704
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=716
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50008
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=728
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=731
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=734
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=430
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50016
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50011
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=50010
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=431
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=781
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=780
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=778
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=432
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=433
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=434
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=435
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=438
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=634
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=443
;

-- Jan 1, 2009 12:38:27 PM ECT
-- Accounting Manufacturing Management
UPDATE AD_TreeNode SET Parent_ID=429, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=101 AND Node_ID=446
;

-- Jan 1, 2009 12:38:53 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ValidCombination (AD_Client_ID,AD_Org_ID,Account_ID,C_AcctSchema_ID,C_ValidCombination_ID,Combination,Created,CreatedBy,Description,IsActive,IsFullyQualified,Updated,UpdatedBy) VALUES (11,11,50014,101,50011,'HQ-913-_-_-_-_',TO_DATE('2009-01-01 12:38:52','YYYY-MM-DD HH24:MI:SS'),100,'HQ-Overhead (Applied)-_-_-_-_','Y','Y',TO_DATE('2009-01-01 12:38:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 1, 2009 12:39:14 PM ECT
-- Accounting Manufacturing Management
INSERT INTO C_ValidCombination (AD_Client_ID,AD_Org_ID,Account_ID,C_AcctSchema_ID,C_ValidCombination_ID,Combination,Created,CreatedBy,Description,IsActive,IsFullyQualified,Updated,UpdatedBy) VALUES (11,11,50016,101,50012,'HQ-51120-_-_-_-_',TO_DATE('2009-01-01 12:39:13','YYYY-MM-DD HH24:MI:SS'),100,'HQ-Scrap-_-_-_-_','Y','Y',TO_DATE('2009-01-01 12:39:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jan 1, 2009 12:39:19 PM ECT
-- Accounting Manufacturing Management
UPDATE C_AcctSchema_Default SET P_Burden_Acct=50008, P_CostOfProduction_Acct=50009, P_FloorStock_Acct=50002, P_Labor_Acct=50007, P_MethodChangeVariance_Acct=50003, P_MixVariance_Acct=50006, P_OutsideProcessing_Acct=50010, P_Overhead_Acct=50011, P_RateVariance_Acct=50005, P_Scrap_Acct=50012, P_UsageVariance_Acct=50004, P_WIP_Acct=50001,Updated=TO_DATE('2009-01-01 12:39:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_AcctSchema_ID=101
;

UPDATE M_Product  SET AD_Org_ID= 0 WHERE AD_Client_ID=11 AND AD_Org_ID<>0;
