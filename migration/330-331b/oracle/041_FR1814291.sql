SET DEFINE OFF

-- Dec 1, 2007 1:51:24 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_REF_LIST SET NAME='PO Commitment & Reservation',Updated=TO_DATE('2007-12-01 01:51:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=785
/

-- Dec 1, 2007 1:51:24 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_REF_LIST_TRL SET NAME = 'Compromisos Compra y Reservas', IsTranslated='Y' WHERE AD_Ref_List_ID=785 AND AD_LANGUAGE LIKE 'es_%'
/

-- Dec 1, 2007 1:52:54 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_REF_LIST SET NAME='PO Commitment only',Updated=TO_DATE('2007-12-01 01:52:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=784
/

-- Dec 1, 2007 1:52:54 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_REF_LIST_TRL SET NAME = 'Solamente Compromiso Compras', IsTranslated='Y' WHERE AD_Ref_List_ID=784 AND AD_LANGUAGE LIKE 'es_%'
/

-- Dec 1, 2007 1:53:53 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO AD_REF_LIST (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,NAME,Updated,UpdatedBy,VALUE) VALUES (0,0,53223,359,TO_DATE('2007-12-01 01:53:45','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','PO/SO Commitment & Reservation',TO_DATE('2007-12-01 01:53:45','YYYY-MM-DD HH24:MI:SS'),100,'A')
/

-- Dec 1, 2007 1:53:53 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO AD_REF_LIST_TRL (AD_LANGUAGE,AD_Ref_List_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Ref_List_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_REF_LIST t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53223 AND EXISTS (SELECT * FROM AD_REF_LIST_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
/

-- Dec 1, 2007 1:52:54 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_REF_LIST_TRL SET NAME = 'Compromisos (ambos) y Reservas', IsTranslated='Y' WHERE AD_Ref_List_ID=53223 AND AD_LANGUAGE LIKE 'es_%'
/

-- Dec 1, 2007 1:54:46 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO AD_REF_LIST (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,NAME,Updated,UpdatedBy,VALUE) VALUES (0,0,53224,359,TO_DATE('2007-12-01 01:54:31','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','SO Commitment only',TO_DATE('2007-12-01 01:54:31','YYYY-MM-DD HH24:MI:SS'),100,'S')
/

-- Dec 1, 2007 1:54:46 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO AD_REF_LIST_TRL (AD_LANGUAGE,AD_Ref_List_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Ref_List_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_REF_LIST t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53224 AND EXISTS (SELECT * FROM AD_REF_LIST_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
/

-- Dec 1, 2007 1:52:54 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_REF_LIST_TRL SET NAME = 'Solamente Compromiso Ventas', IsTranslated='Y' WHERE AD_Ref_List_ID=53224 AND AD_LANGUAGE LIKE 'es_%'
/

-- Dec 1, 2007 1:55:44 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO AD_REF_LIST (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,NAME,Updated,UpdatedBy,VALUE) VALUES (0,0,53225,359,TO_DATE('2007-12-01 01:55:35','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','PO/SO Commitment',TO_DATE('2007-12-01 01:55:35','YYYY-MM-DD HH24:MI:SS'),100,'O')
/

-- Dec 1, 2007 1:55:44 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO AD_REF_LIST_TRL (AD_LANGUAGE,AD_Ref_List_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Ref_List_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_REF_LIST t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53225 AND EXISTS (SELECT * FROM AD_REF_LIST_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
/

-- Dec 1, 2007 1:52:54 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_REF_LIST_TRL SET NAME = 'Solamente Compromisos (ambos)', IsTranslated='Y' WHERE AD_Ref_List_ID=53225 AND AD_LANGUAGE LIKE 'es_%'
/

-- Dec 1, 2007 1:58:18 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO AD_ELEMENT (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,NAME,PrintName,Updated,UpdatedBy) VALUES (0,53228,0,'CommitmentOffsetSales_Acct',TO_DATE('2007-12-01 01:58:04','YYYY-MM-DD HH24:MI:SS'),100,'Budgetary Commitment Offset Account for Sales','D','The Commitment Offset Account is used for posting Commitments Sales and Reservations.  It is usually an off-balance sheet and gain-and-loss account.','Y','Commitment Offset Sales','Commitment Offset Sales',TO_DATE('2007-12-01 01:58:04','YYYY-MM-DD HH24:MI:SS'),100)
/

-- Dec 1, 2007 1:58:18 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO AD_ELEMENT_TRL (AD_LANGUAGE,AD_Element_ID, Description,Help,NAME,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Element_ID, t.Description,t.Help,t.NAME,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_ELEMENT t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53228 AND EXISTS (SELECT * FROM AD_ELEMENT_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Element_ID!=t.AD_Element_ID)
/

-- Dec 1, 2007 1:59:04 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,53267,53228,0,25,266,'CommitmentOffsetSales_Acct',TO_DATE('2007-12-01 01:58:56','YYYY-MM-DD HH24:MI:SS'),100,'Budgetary Commitment Offset Account for Sales','D',10,'The Commitment Offset Account is used for posting Commitments Sales and Reservations.  It is usually an off-balance sheet and gain-and-loss account.','Y','N','N','N','N','N','N','N','N','N','Y','Commitment Offset Sales',TO_DATE('2007-12-01 01:58:56','YYYY-MM-DD HH24:MI:SS'),100,0)
/

-- Dec 1, 2007 1:59:04 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=53267 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
/

-- Dec 1, 2007 1:59:26 AM COT
-- FR 1814291 - Sales Commitment Offset
ALTER TABLE C_ACCTSCHEMA_GL ADD CommitmentOffsetSales_Acct NUMBER(10)
/

-- FR 1814291 - Sales Commitment Offset
UPDATE C_ACCTSCHEMA_GL SET CommitmentOffsetSales_Acct = CommitmentOffset_Acct
/

-- Dec 1, 2007 2:00:11 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_COLUMN SET IsMandatory='Y',Updated=TO_DATE('2007-12-01 02:00:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53267
/

-- Dec 1, 2007 2:00:11 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_FIELD SET NAME='Commitment Offset Sales', Description='Budgetary Commitment Offset Account for Sales', Help='The Commitment Offset Account is used for posting Commitments Sales and Reservations.  It is usually an off-balance sheet and gain-and-loss account.' WHERE AD_Column_ID=53267 AND IsCentrallyMaintained='Y'
/

-- Dec 1, 2007 2:00:20 AM COT
-- FR 1814291 - Sales Commitment Offset
ALTER TABLE C_ACCTSCHEMA_GL MODIFY CommitmentOffsetSales_Acct NOT NULL
/

-- Dec 1, 2007 2:02:34 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_FIELD SET IsSameLine='N', SeqNo=160,Updated=TO_DATE('2007-12-01 02:02:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=12375
/

-- Dec 1, 2007 2:03:04 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,Updated,UpdatedBy) VALUES (0,53267,53282,0,200,TO_DATE('2007-12-01 02:02:56','YYYY-MM-DD HH24:MI:SS'),100,'Budgetary Commitment Offset Account for Sales',10,'D','The Commitment Offset Account is used for posting Commitments Sales and Reservations.  It is usually an off-balance sheet and gain-and-loss account.','Y','Y','Y','N','N','N','N','Y','Commitment Offset Sales',170,TO_DATE('2007-12-01 02:02:56','YYYY-MM-DD HH24:MI:SS'),100)
/

-- Dec 1, 2007 2:03:04 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=53282 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
/

-- Dec 1, 2007 2:55:04 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO C_ELEMENTVALUE (AD_Client_ID,AD_Org_ID,AccountSign,AccountType,C_ElementValue_ID,C_Element_ID,Created,CreatedBy,IsActive,IsBankAccount,IsDocControlled,IsForeignCurrency,IsSummary,NAME,PostActual,PostBudget,PostEncumbrance,PostStatistical,Updated,UpdatedBy,VALUE) VALUES (11,0,'N','M',50000,105,TO_DATE('2007-12-01 02:55:03','YYYY-MM-DD HH24:MI:SS'),100,'Y','N','N','N','N','SO Commitment','Y','Y','Y','Y',TO_DATE('2007-12-01 02:55:03','YYYY-MM-DD HH24:MI:SS'),100,'953')
/

-- Dec 1, 2007 2:55:04 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO C_ELEMENTVALUE_TRL (AD_LANGUAGE,C_ElementValue_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.C_ElementValue_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, C_ELEMENTVALUE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_ElementValue_ID=50000 AND EXISTS (SELECT * FROM C_ELEMENTVALUE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.C_ElementValue_ID!=t.C_ElementValue_ID)
/

-- Dec 1, 2007 2:55:04 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO AD_TREENODE (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SYSDATE, 0, SYSDATE, 0,t.AD_Tree_ID, 50000, 0, 999 FROM AD_TREE t WHERE t.AD_Client_ID=11 AND t.IsActive='Y' AND EXISTS (SELECT * FROM C_ELEMENT ae WHERE ae.C_Element_ID=105 AND t.AD_Tree_ID=ae.AD_Tree_ID) AND NOT EXISTS (SELECT * FROM AD_TREENODE e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=50000)
/

-- Dec 1, 2007 2:55:08 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=0, SeqNo=0, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=506
/

-- Dec 1, 2007 2:55:08 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=0, SeqNo=1, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=584
/

-- Dec 1, 2007 2:55:08 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=0, SeqNo=2, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=624
/

-- Dec 1, 2007 2:55:08 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=0, SeqNo=3, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=632
/

-- Dec 1, 2007 2:55:08 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=0, SeqNo=4, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=429
/

-- Dec 1, 2007 2:55:08 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=0, SeqNo=5, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=449
/

-- Dec 1, 2007 2:55:08 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=0, SeqNo=6, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=783
/

-- Dec 1, 2007 2:55:08 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=0, SeqNo=7, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=704
/

-- Dec 1, 2007 2:55:08 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=0, SeqNo=8, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=716
/

-- Dec 1, 2007 2:55:08 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=0, SeqNo=9, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=728
/

-- Dec 1, 2007 2:55:08 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=0, SeqNo=10, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=731
/

-- Dec 1, 2007 2:55:08 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=0, SeqNo=11, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=734
/

-- Dec 1, 2007 2:55:08 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=734, SeqNo=0, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=735
/

-- Dec 1, 2007 2:55:09 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=734, SeqNo=1, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=736
/

-- Dec 1, 2007 2:55:09 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=734, SeqNo=2, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=50000
/

-- Dec 1, 2007 2:55:09 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE AD_TREENODE SET Parent_ID=734, SeqNo=3, Updated=SYSDATE WHERE AD_Tree_ID=101 AND Node_ID=737
/

-- Dec 1, 2007 2:55:54 AM COT
-- FR 1814291 - Sales Commitment Offset
INSERT INTO C_VALIDCOMBINATION (AD_Client_ID,AD_Org_ID,Account_ID,C_AcctSchema_ID,C_ValidCombination_ID,Combination,Created,CreatedBy,Description,IsActive,IsFullyQualified,Updated,UpdatedBy) VALUES (11,11,50000,101,50000,'HQ-953-_-_-_-_',TO_DATE('2007-12-01 02:55:54','YYYY-MM-DD HH24:MI:SS'),100,'HQ-SO Commitment-_-_-_-_','Y','Y',TO_DATE('2007-12-01 02:55:54','YYYY-MM-DD HH24:MI:SS'),100)
/

-- Dec 1, 2007 2:56:01 AM COT
-- FR 1814291 - Sales Commitment Offset
UPDATE C_ACCTSCHEMA_GL SET CommitmentOffsetSales_Acct=50000,Updated=TO_DATE('2007-12-01 02:56:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_AcctSchema_ID=101
/

COMMIT
/
