-- Apr 8, 2008 10:26:21 PM CDT
-- Libero HR & Payroll
UPDATE AD_Window SET Description='Payroll Process', Name='Payroll Process',Updated=TO_DATE('2008-04-08 22:26:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Window_ID=53037
;

-- Apr 8, 2008 10:26:22 PM CDT
-- Libero HR & Payroll
UPDATE AD_Window_Trl SET IsTranslated='N' WHERE AD_Window_ID=53037
;

-- Apr 8, 2008 10:26:22 PM CDT
-- Libero HR & Payroll
UPDATE AD_Menu SET AD_Window_ID=NULL, Description='Payroll Process', IsActive='Y', Name='Payroll Process',Updated=TO_DATE('2008-04-08 22:26:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53116
;

-- Apr 8, 2008 10:26:22 PM CDT
-- Libero HR & Payroll
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53116
;

-- Apr 8, 2008 10:27:25 PM CDT
-- Libero HR & Payroll
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53127,0,53037,'W',TO_DATE('2008-04-08 22:27:21','YYYY-MM-DD HH24:MI:SS'),0,'The Payroll Processing is used to processing a Payroll, you can calculate for a Employee or All Employees','EE02','Y','N','N','N','Payroll Process',TO_DATE('2008-04-08 22:27:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 8, 2008 10:27:25 PM CDT
-- Libero HR & Payroll
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53127 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Apr 8, 2008 10:27:25 PM CDT
-- Libero HR & Payroll
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 53127, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53127)
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=53114, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53118
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=53114, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53115
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=53114, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53119
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=53114, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53120
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=53114, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53117
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=53114, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53123
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=53114, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53127
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=53114, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53116
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=53114, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53121
;

-- Apr 8, 2008 10:27:32 PM CDT
-- Libero HR & Payroll
UPDATE AD_TreeNodeMM SET Parent_ID=53114, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53122
;

-- Apr 8, 2008 10:45:03 PM CDT
-- Libero HR & Payroll
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,Description,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52021,'C_DocType.DocBaseType=''HRP''',TO_DATE('2008-04-08 22:45:01','YYYY-MM-DD HH24:MI:SS'),0,'Document Type for Payroll','EE02','Y','C_DocType Payroll','S',TO_DATE('2008-04-08 22:45:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Apr 8, 2008 10:45:39 PM CDT
-- Libero HR & Payroll
UPDATE AD_Column SET AD_Val_Rule_ID=52021,Updated=TO_DATE('2008-04-08 22:45:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54863
;

-- Apr 8, 2008 10:45:39 PM CDT
-- Libero HR & Payroll
UPDATE AD_Field SET Name='Target Document Type', Description='Target document type for conversing documents', Help='You can convert document types (e.g. from Offer to Order or Invoice).  The conversion is then reflected in the current type.  This processing is initiated by selecting the appropriate Document Action.' WHERE AD_Column_ID=54863 AND IsCentrallyMaintained='Y'
;

-- Apr 8, 2008 10:45:51 PM CDT
-- Libero HR & Payroll
UPDATE AD_Column SET AD_Val_Rule_ID=52021,Updated=TO_DATE('2008-04-08 22:45:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54858
;

-- Apr 8, 2008 10:45:51 PM CDT
-- Libero HR & Payroll
UPDATE AD_Field SET Name='Document Type', Description='Document type or rules', Help='The Document Type determines document sequence and processing rules' WHERE AD_Column_ID=54858 AND IsCentrallyMaintained='Y'
;

-- Apr 8, 2008 10:50:36 PM CDT
-- Libero HR & Payroll
INSERT INTO GL_Category (AD_Client_ID,AD_Org_ID,CategoryType,Created,CreatedBy,GL_Category_ID,IsActive,IsDefault,Name,Updated,UpdatedBy) VALUES (11,0,'D',TO_DATE('2008-04-08 22:50:35','YYYY-MM-DD HH24:MI:SS'),100,50000,'Y','N','Payroll',TO_DATE('2008-04-08 22:50:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 8, 2008 10:51:38 PM CDT
-- Libero HR & Payroll
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53130,TO_DATE('2008-04-08 22:51:37','YYYY-MM-DD HH24:MI:SS'),100,100000,300000,1,'Y','N','Y','N','Payroll','N',300000,TO_DATE('2008-04-08 22:51:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 8, 2008 10:51:57 PM CDT
-- Libero HR & Payroll
INSERT INTO C_DocType (AD_Client_ID,AD_Org_ID,C_DocType_ID,Created,CreatedBy,DocBaseType,DocNoSequence_ID,DocumentCopies,GL_Category_ID,HasCharges,HasProforma,IsActive,IsCreateCounter,IsDefault,IsDefaultCounterDoc,IsDocNoControlled,IsInTransit,IsIndexed,IsOverwriteDateOnComplete,IsOverwriteSeqOnComplete,IsPickQAConfirm,IsSOTrx,IsShipConfirm,IsSplitWhenDifference,Name,PrintName,Updated,UpdatedBy) VALUES (11,0,50000,TO_DATE('2008-04-08 22:51:57','YYYY-MM-DD HH24:MI:SS'),100,'HRP',53130,1,50000,'N','N','Y','Y','N','N','Y','N','Y','N','N','N','N','N','N','Payroll','Payroll',TO_DATE('2008-04-08 22:51:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 8, 2008 10:51:58 PM CDT
-- Libero HR & Payroll
INSERT INTO C_DocType_Trl (AD_Language,C_DocType_ID, DocumentNote,Name,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_DocType_ID, t.DocumentNote,t.Name,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_DocType t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_DocType_ID=50000 AND EXISTS (SELECT * FROM C_DocType_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_DocType_ID!=t.C_DocType_ID)
;

-- Apr 8, 2008 10:51:58 PM CDT
-- Libero HR & Payroll
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', SysDate,100, SysDate,100, doctype.C_DocType_ID, action.AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List action ON (action.AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) WHERE client.AD_Client_ID=11 AND doctype.C_DocType_ID=50000 AND rol.IsManual='N')
;

-- Apr 8, 2008 10:52:46 PM CDT
-- Libero HR & Payroll
UPDATE AD_Ref_List SET Name='Payroll',Updated=TO_DATE('2008-04-08 22:52:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53240
;

-- Apr 8, 2008 10:52:46 PM CDT
-- Libero HR & Payroll
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53240
;

