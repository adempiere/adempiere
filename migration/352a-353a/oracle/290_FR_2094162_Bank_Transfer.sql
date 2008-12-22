-- 4/09/2008 07:13:13 PM CDT
-- Transfer Bank
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES (0,0,53153,'3','org.eevolution.process.BankTransfer',TO_DATE('2008-09-04 19:13:03','YYYY-MM-DD HH24:MI:SS'),100,'C','Y','N','N','N','N','Bank Transfer','Y',1,4,TO_DATE('2008-09-04 19:13:03','YYYY-MM-DD HH24:MI:SS'),100,'BankTransfer')
;

-- 4/09/2008 07:13:13 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53153 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- 4/09/2008 07:13:13 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53153,0,TO_DATE('2008-09-04 19:13:13','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-04 19:13:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 4/09/2008 07:13:13 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53153,102,TO_DATE('2008-09-04 19:13:13','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-04 19:13:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 4/09/2008 07:13:13 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53153,103,TO_DATE('2008-09-04 19:13:13','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-04 19:13:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 4/09/2008 07:13:13 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53153,50002,TO_DATE('2008-09-04 19:13:13','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-04 19:13:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 4/09/2008 07:13:13 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53153,50001,TO_DATE('2008-09-04 19:13:13','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-09-04 19:13:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 4/09/2008 07:14:31 PM CDT
-- Transfer Bank
UPDATE AD_Menu SET Name='Bank Transfer via Cash',Updated=TO_DATE('2008-09-04 19:14:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53187
;

-- 4/09/2008 07:14:31 PM CDT
-- Transfer Bank
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53187
;

-- 4/09/2008 07:15:10 PM CDT
-- Transfer Bank
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Action,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53190,0,53153,'P',TO_DATE('2008-09-04 19:15:08','YYYY-MM-DD HH24:MI:SS'),100,'C','Y','N','N','N','Bank Transfer via Payment',TO_DATE('2008-09-04 19:15:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 4/09/2008 07:15:10 PM CDT
-- Transfer Bank
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53190 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- 4/09/2008 07:15:10 PM CDT
-- Transfer Bank
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 53190, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53190)
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=241
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=288
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=432
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=243
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=413
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=538
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=462
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=505
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=235
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=511
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=245
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=251
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=246
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=509
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=510
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=496
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=497
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=304
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=255
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=19, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=286
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=20, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=287
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=21, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=438
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=22, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=234
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=23, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=244
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=24, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53187
;

-- 4/09/2008 07:15:14 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=25, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53190
;

-- Sep 4, 2008 7:16:38 PM CDT
-- Transfer Bank
UPDATE AD_Process SET Value='C_BankStatement BankTransfer',Updated=TO_DATE('2008-09-04 19:16:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53153
;

-- Sep 4, 2008 7:20:15 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53153,53246,18,53283,'From_C_BankAccount_ID',TO_DATE('2008-09-04 19:20:14','YYYY-MM-DD HH24:MI:SS'),0,'D',0,'Y','Y','N','N','Bank Account From',10,TO_DATE('2008-09-04 19:20:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 4, 2008 7:20:15 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53246 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 4, 2008 7:20:20 PM CDT
-- Transfer Bank
UPDATE AD_Process SET EntityType='D',Updated=TO_DATE('2008-09-04 19:20:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53153
;

-- Sep 4, 2008 7:22:29 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53153,53247,18,53283,'To_C_BankAccount_ID',TO_DATE('2008-09-04 19:22:28','YYYY-MM-DD HH24:MI:SS'),0,'D',10,'Y','Y','N','N','Bank Account To',20,TO_DATE('2008-09-04 19:22:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 4, 2008 7:22:29 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53247 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 4, 2008 7:22:35 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_DATE('2008-09-04 19:22:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53246
;

-- Sep 4, 2008 7:23:37 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53153,53248,12,'Amount',TO_DATE('2008-09-04 19:23:36','YYYY-MM-DD HH24:MI:SS'),0,'Amount to be transferred','D',22,'Y','Y','N','N','Transfer Amount',30,TO_DATE('2008-09-04 19:23:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 4, 2008 7:23:37 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53248 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 4, 2008 7:25:41 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,187,0,53153,53249,30,'C_BPartner_ID',TO_DATE('2008-09-04 19:25:38','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','D',22,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','Business Partner ',40,TO_DATE('2008-09-04 19:25:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 4, 2008 7:25:41 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53249 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 4, 2008 7:26:49 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,968,0,53153,53250,19,'C_Charge_ID',TO_DATE('2008-09-04 19:26:41','YYYY-MM-DD HH24:MI:SS'),0,'Additional document charges','D',22,'The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','Y','N','Charge',40,TO_DATE('2008-09-04 19:26:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 4, 2008 7:26:49 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53250 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 4, 2008 7:27:09 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET SeqNo=30,Updated=TO_DATE('2008-09-04 19:27:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53249
;

-- Sep 4, 2008 7:27:24 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET SeqNo=60,Updated=TO_DATE('2008-09-04 19:27:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53248
;

-- Sep 4, 2008 7:29:09 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,290,0,53153,53251,10,'DocumentNo',TO_DATE('2008-09-04 19:29:08','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document','D',40,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".','Y','Y','Y','N','Document No',50,TO_DATE('2008-09-04 19:29:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 4, 2008 7:29:09 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53251 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 4, 2008 7:33:17 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,275,0,53153,53252,10,'Description',TO_DATE('2008-09-04 19:33:13','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','D',255,'A description is limited to 255 characters.','Y','Y','N','N','Description',70,TO_DATE('2008-09-04 19:33:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 4, 2008 7:33:17 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53252 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 4, 2008 7:34:53 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1434,0,53153,53253,15,'StatementDate',TO_DATE('2008-09-04 19:34:52','YYYY-MM-DD HH24:MI:SS'),0,'@#Date@','Date of the statement','D',7,'The Statement Date field defines the date of the statement.','Y','Y','Y','N','StatementDate',80,TO_DATE('2008-09-04 19:34:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 4, 2008 7:34:53 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53253 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 4, 2008 7:36:24 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET FieldLength=7,Updated=TO_DATE('2008-09-04 19:36:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53239
;

-- Sep 4, 2008 7:36:37 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,263,0,53153,53254,15,'DateAcct',TO_DATE('2008-09-04 19:36:36','YYYY-MM-DD HH24:MI:SS'),0,'@#Date@','Accounting Date','D',7,'The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.','Y','Y','Y','N','Account Date',90,TO_DATE('2008-09-04 19:36:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 4, 2008 7:36:37 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53254 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 4, 2008 7:38:45 PM CDT
-- Transfer Bank
UPDATE AD_Menu SET EntityType='D',Updated=TO_DATE('2008-09-04 19:38:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53190
;

-- Sep 4, 2008 7:49:55 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET IsMandatory='Y',Updated=TO_DATE('2008-09-04 19:49:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53246
;

-- Sep 4, 2008 7:50:03 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET IsMandatory='Y',Updated=TO_DATE('2008-09-04 19:50:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53247
;

-- Sep 4, 2008 7:50:38 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET IsMandatory='Y',Updated=TO_DATE('2008-09-04 19:50:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53248
;

-- Sep 4, 2008 7:55:20 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,DisplayLogic,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2278,0,53153,53255,19,'C_ConversionType_ID',TO_DATE('2008-09-04 19:55:19','YYYY-MM-DD HH24:MI:SS'),0,'Currency Conversion Rate Type','@C_Currency_ID@!@$C_Currency_ID@','D',22,'The Currency Conversion Rate Type lets you define different type of rates, e.g. Spot, Corporate and/or Sell/Buy rates.','Y','Y','N','N','Currency Type',30,TO_DATE('2008-09-04 19:55:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 4, 2008 7:55:20 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53255 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 4, 2008 7:55:31 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET SeqNo=40,Updated=TO_DATE('2008-09-04 19:55:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53255
;

-- Sep 4, 2008 7:56:53 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,DisplayLogic,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,193,0,53153,53256,19,'C_Currency_ID',TO_DATE('2008-09-04 19:56:53','YYYY-MM-DD HH24:MI:SS'),0,'The Currency for this record',NULL,'D',22,'Indicates the Currency to be used when processing or reporting on this record','Y','Y','Y','N','Currency',40,TO_DATE('2008-09-04 19:56:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 4, 2008 7:56:53 PM CDT
-- Transfer Bank
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53256 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Sep 4, 2008 7:57:12 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET SeqNo=50,Updated=TO_DATE('2008-09-04 19:57:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53256
;

-- Sep 4, 2008 7:57:15 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET SeqNo=60,Updated=TO_DATE('2008-09-04 19:57:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53250
;

-- Sep 4, 2008 7:57:17 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET SeqNo=70,Updated=TO_DATE('2008-09-04 19:57:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53251
;

-- Sep 4, 2008 7:57:20 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET SeqNo=80,Updated=TO_DATE('2008-09-04 19:57:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53248
;

-- Sep 4, 2008 7:57:25 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET SeqNo=90,Updated=TO_DATE('2008-09-04 19:57:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53252
;

-- Sep 4, 2008 7:57:31 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET SeqNo=100,Updated=TO_DATE('2008-09-04 19:57:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53253
;

-- Sep 4, 2008 7:57:35 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET SeqNo=110,Updated=TO_DATE('2008-09-04 19:57:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53254
;

-- Sep 4, 2008 7:58:50 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET SeqNo=40,Updated=TO_DATE('2008-09-04 19:58:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53256
;

-- Sep 4, 2008 7:58:54 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET SeqNo=50,Updated=TO_DATE('2008-09-04 19:58:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53255
;

-- Sep 4, 2008 8:13:10 PM CDT
-- Transfer Bank
UPDATE AD_Process SET Description='Bank Transfer let money tranfer between Banks',Updated=TO_DATE('2008-09-04 20:13:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53153
;

-- Sep 4, 2008 8:13:10 PM CDT
-- Transfer Bank
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53153
;

-- Sep 4, 2008 8:13:10 PM CDT
-- Transfer Bank
UPDATE AD_Menu SET Description='Bank Transfer let money tranfer between Banks', IsActive='Y', Name='Bank Transfer',Updated=TO_DATE('2008-09-04 20:13:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53190
;

-- Sep 4, 2008 8:13:10 PM CDT
-- Transfer Bank
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53190
;

-- Sep 4, 2008 8:20:11 PM CDT
-- Transfer Bank
UPDATE AD_Process_Para SET FieldLength=30,Updated=TO_DATE('2008-09-04 20:20:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53251
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=241
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=288
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=432
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=243
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=413
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=538
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=462
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=505
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=235
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=511
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=245
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=251
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=246
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=509
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=510
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=496
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=497
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=304
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=255
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=19, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=286
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=20, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=287
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=21, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=438
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=22, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=234
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=23, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=244
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=24, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53190
;

-- Sep 4, 2008 8:34:54 PM CDT
-- Transfer Bank
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=25, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53187
;

