-- Mar 8, 2010 8:56:27 PM COT
-- FR2962094_Finish implementation of weighted average costing
INSERT INTO C_ElementValue (AccountSign,AccountType,AD_Client_ID,AD_Org_ID,C_Element_ID,C_ElementValue_ID,Created,CreatedBy,Description,IsActive,IsBankAccount,IsDocControlled,IsForeignCurrency,IsSummary,Name,PostActual,PostBudget,PostEncumbrance,PostStatistical,Updated,UpdatedBy,Value) VALUES ('N','E',11,0,105,50017,TO_TIMESTAMP('2010-03-08 20:56:26','YYYY-MM-DD HH24:MI:SS'),100,'Account for Average Cost Variance','Y','N','Y','N','N','Average Cost Variance','Y','Y','Y','Y',TO_TIMESTAMP('2010-03-08 20:56:26','YYYY-MM-DD HH24:MI:SS'),100,'58800')
;

-- Mar 8, 2010 8:56:27 PM COT
INSERT INTO C_ElementValue_Trl (AD_Language,C_ElementValue_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_ElementValue_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_ElementValue t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_ElementValue_ID=50017 AND NOT EXISTS (SELECT * FROM C_ElementValue_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.C_ElementValue_ID=t.C_ElementValue_ID)
;

-- Mar 8, 2010 8:56:27 PM COT
INSERT INTO AD_TreeNode (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID, 0, 'Y', CURRENT_TIMESTAMP, 100, CURRENT_TIMESTAMP, 100,t.AD_Tree_ID, 50017, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND EXISTS (SELECT * FROM C_Element ae WHERE ae.C_Element_ID=105 AND t.AD_Tree_ID=ae.AD_Tree_ID) AND NOT EXISTS (SELECT * FROM AD_TreeNode e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50017)
;

-- Mar 8, 2010 8:56:34 PM COT
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=101 AND Node_ID=444
;

-- Mar 8, 2010 8:56:34 PM COT
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=101 AND Node_ID=445
;

-- Mar 8, 2010 8:56:34 PM COT
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=101 AND Node_ID=635
;

-- Mar 8, 2010 8:56:34 PM COT
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=101 AND Node_ID=50001
;

-- Mar 8, 2010 8:56:34 PM COT
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=101 AND Node_ID=50002
;

-- Mar 8, 2010 8:56:34 PM COT
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=101 AND Node_ID=50003
;

-- Mar 8, 2010 8:56:34 PM COT
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=101 AND Node_ID=50004
;

-- Mar 8, 2010 8:56:34 PM COT
UPDATE AD_TreeNode SET Parent_ID=443, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=101 AND Node_ID=50017
;

-- Mar 8, 2010 8:58:19 PM COT
INSERT INTO C_ValidCombination (Account_ID,AD_Client_ID,AD_Org_ID,C_AcctSchema_ID,Combination,Created,CreatedBy,C_ValidCombination_ID,Description,IsActive,IsFullyQualified,Updated,UpdatedBy) VALUES (50017,11,11,101,'HQ-58800-_-_-_-_',TO_TIMESTAMP('2010-03-08 20:58:18','YYYY-MM-DD HH24:MI:SS'),100,50013,'HQ-Average Cost Variance-_-_-_-_','Y','Y',TO_TIMESTAMP('2010-03-08 20:58:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 8, 2010 8:58:32 PM COT
UPDATE C_AcctSchema_Default SET P_AverageCostVariance_Acct=50013,Updated=TO_TIMESTAMP('2010-03-08 20:58:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_AcctSchema_ID=101
;

UPDATE M_Product_Category_Acct SET P_AverageCostVariance_Acct=50013,Updated=TO_TIMESTAMP('2010-03-08 20:58:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_AcctSchema_ID=101;

UPDATE M_Product_Acct SET P_AverageCostVariance_Acct=50013,Updated=TO_TIMESTAMP('2010-03-08 20:58:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_AcctSchema_ID=101;
