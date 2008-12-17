-- Aug 1, 2008 2:16:27 PM COT
-- 2035176 - Immediate Bank Transfer
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('3',0,0,53152,'org.adempiere.process.ImmediateBankTransfer',TO_DATE('2008-08-01 14:16:25','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','N','Immediate Bank Transfer','Y',0,0,TO_DATE('2008-08-01 14:16:25','YYYY-MM-DD HH24:MI:SS'),100,'ImmediateBankTransfer')
;

-- Aug 1, 2008 2:16:27 PM COT
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53152 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Aug 1, 2008 2:16:27 PM COT
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53152,0,TO_DATE('2008-08-01 14:16:27','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-08-01 14:16:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 1, 2008 2:16:27 PM COT
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53152,102,TO_DATE('2008-08-01 14:16:27','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-08-01 14:16:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 1, 2008 2:16:27 PM COT
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53152,103,TO_DATE('2008-08-01 14:16:27','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-08-01 14:16:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 1, 2008 2:16:27 PM COT
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53152,50002,TO_DATE('2008-08-01 14:16:27','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-08-01 14:16:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 1, 2008 2:16:27 PM COT
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53152,50001,TO_DATE('2008-08-01 14:16:27','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_DATE('2008-08-01 14:16:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 1, 2008 2:20:59 PM COT
INSERT INTO AD_Menu (Action,AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('P',0,53187,0,53152,TO_DATE('2008-08-01 14:20:59','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','Bank Transfer',TO_DATE('2008-08-01 14:20:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 1, 2008 2:21:00 PM COT
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53187 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Aug 1, 2008 2:21:00 PM COT
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', SysDate, 0, SysDate, 0,t.AD_Tree_ID, 53187, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53187)
;

-- Aug 1, 2008 2:23:09 PM COT
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53283,TO_DATE('2008-08-01 14:23:08','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','C_BankAccount',TO_DATE('2008-08-01 14:23:08','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- Aug 1, 2008 2:23:09 PM COT
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53283 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Aug 1, 2008 2:23:49 PM COT
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,3074,3077,0,53283,297,TO_DATE('2008-08-01 14:23:49','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N',TO_DATE('2008-08-01 14:23:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 1, 2008 2:24:43 PM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53152,53232,18,53283,'From_C_BankAccount_ID',TO_DATE('2008-08-01 14:24:42','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','Y','N','Bank Account From',10,TO_DATE('2008-08-01 14:24:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 1, 2008 2:24:44 PM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53232 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Aug 1, 2008 2:24:59 PM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53152,53233,18,53283,'To_C_BankAccount_ID',TO_DATE('2008-08-01 14:24:59','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','Y','N','Bank Account To',20,TO_DATE('2008-08-01 14:24:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 1, 2008 2:24:59 PM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53233 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Aug 1, 2008 2:26:21 PM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53152,53234,19,'C_CashBook_ID',TO_DATE('2008-08-01 14:26:21','YYYY-MM-DD HH24:MI:SS'),100,'Cash Book for recording petty cash transactions','D',0,'The Cash Book identifies a unique cash book.  The cash book is used to record cash transactions.','Y','Y','Y','N','Intermediate Cash Book',30,TO_DATE('2008-08-01 14:26:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 1, 2008 2:26:21 PM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53234 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Aug 1, 2008 2:27:29 PM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1367,0,53152,53235,12,'Amount',TO_DATE('2008-08-01 14:27:29','YYYY-MM-DD HH24:MI:SS'),100,'U',0,'Y','Y','Y','N','Amount in a defined currency',40,TO_DATE('2008-08-01 14:27:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 1, 2008 2:27:29 PM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53235 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Aug 1, 2008 2:27:32 PM COT
UPDATE AD_Process_Para SET EntityType='D',Updated=TO_DATE('2008-08-01 14:27:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53235
;

-- Aug 1, 2008 2:28:55 PM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,469,0,53152,53236,10,'Name',TO_DATE('2008-08-01 14:28:55','YYYY-MM-DD HH24:MI:SS'),100,'D',60,'Y','Y','Y','N','Name',50,TO_DATE('2008-08-01 14:28:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 1, 2008 2:28:55 PM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53236 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Aug 1, 2008 2:29:14 PM COT
UPDATE AD_Process_Para SET FieldLength=22,Updated=TO_DATE('2008-08-01 14:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53235
;

-- Aug 1, 2008 2:29:18 PM COT
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_DATE('2008-08-01 14:29:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53234
;

-- Aug 1, 2008 2:29:58 PM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,275,0,53152,53237,10,'Description',TO_DATE('2008-08-01 14:29:57','YYYY-MM-DD HH24:MI:SS'),100,'D',255,'Y','Y','N','N','Description',60,TO_DATE('2008-08-01 14:29:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 1, 2008 2:29:58 PM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53237 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Aug 1, 2008 2:30:26 PM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1434,0,53152,53238,15,'StatementDate',TO_DATE('2008-08-01 14:30:25','YYYY-MM-DD HH24:MI:SS'),100,'@#Date@','U',7,'Y','Y','Y','N','StatementDate',70,TO_DATE('2008-08-01 14:30:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 1, 2008 2:30:26 PM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53238 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Aug 1, 2008 2:30:53 PM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,263,0,53152,53239,15,'DateAcct',TO_DATE('2008-08-01 14:30:52','YYYY-MM-DD HH24:MI:SS'),100,'@#Date@','D',0,'Y','Y','N','N','DateAcct',80,TO_DATE('2008-08-01 14:30:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Aug 1, 2008 2:30:53 PM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53239 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Aug 1, 2008 2:31:01 PM COT
UPDATE AD_Process_Para SET Description='Accounting Date', Help='The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.',Updated=TO_DATE('2008-08-01 14:31:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53239
;

-- Aug 1, 2008 2:31:01 PM COT
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53239
;

-- Aug 1, 2008 2:31:15 PM COT
UPDATE AD_Process_Para SET Description='Date of the statement', EntityType='D', Help='The Statement Date field defines the date of the statement.',Updated=TO_DATE('2008-08-01 14:31:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53238
;

-- Aug 1, 2008 2:31:15 PM COT
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53238
;

-- Aug 1, 2008 2:31:18 PM COT
UPDATE AD_Process_Para SET IsMandatory='Y',Updated=TO_DATE('2008-08-01 14:31:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53239
;

-- Aug 1, 2008 2:31:37 PM COT
UPDATE AD_Process_Para SET Description='Optional short description of the record', Help='A description is limited to 255 characters.',Updated=TO_DATE('2008-08-01 14:31:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53237
;

-- Aug 1, 2008 2:31:37 PM COT
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53237
;

-- Aug 1, 2008 2:31:53 PM COT
UPDATE AD_Process_Para SET Description='Alphanumeric identifier of the entity', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.',Updated=TO_DATE('2008-08-01 14:31:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53236
;

-- Aug 1, 2008 2:31:53 PM COT
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53236
;

-- Aug 1, 2008 2:32:24 PM COT
UPDATE AD_Process_Para SET AD_Element_ID=NULL, Description='Amount to be transferred', Name='Transfer Amount',Updated=TO_DATE('2008-08-01 14:32:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53235
;

-- Aug 1, 2008 2:32:24 PM COT
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53235
;

-- Aug 1, 2008 2:32:39 PM COT
UPDATE AD_Process_Para SET SeqNo=30,Updated=TO_DATE('2008-08-01 14:32:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53235
;

-- Aug 1, 2008 2:32:43 PM COT
UPDATE AD_Process_Para SET SeqNo=40,Updated=TO_DATE('2008-08-01 14:32:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53234
;

-- Aug 1, 2008 2:33:22 PM COT
UPDATE AD_Process_Trl SET IsTranslated='Y',Name='Transferencia Bancaria Inmediata',Updated=TO_DATE('2008-08-01 14:33:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53152 AND AD_Language LIKE 'es_%'
;

-- Aug 1, 2008 2:33:34 PM COT
UPDATE AD_Process_Para_Trl SET IsTranslated='Y',Name='Cuenta Bancaria Desde',Updated=TO_DATE('2008-08-01 14:33:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53232 AND AD_Language LIKE 'es_%'
;

-- Aug 1, 2008 2:33:41 PM COT
UPDATE AD_Process_Para_Trl SET IsTranslated='Y',Name='Cuenta Bancaria Hacia',Updated=TO_DATE('2008-08-01 14:33:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53233 AND AD_Language LIKE 'es_%'
;

-- Aug 1, 2008 2:33:50 PM COT
UPDATE AD_Process_Para_Trl SET IsTranslated='Y',Name='Cantidad a transferir',Updated=TO_DATE('2008-08-01 14:33:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53235 AND AD_Language LIKE 'es_%'
;

-- Aug 1, 2008 2:34:03 PM COT
UPDATE AD_Process_Para_Trl SET IsTranslated='Y',Name='Caja menor intermedia',Updated=TO_DATE('2008-08-01 14:34:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53234 AND AD_Language LIKE 'es_%'
;

-- Aug 1, 2008 2:34:11 PM COT
UPDATE AD_Process_Para_Trl SET Description=NULL,Help=NULL,Updated=TO_DATE('2008-08-01 14:34:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53234 AND AD_Language LIKE 'es_%'
;

-- Aug 1, 2008 2:34:18 PM COT
UPDATE AD_Process_Para_Trl SET IsTranslated='Y',Name='Nombre',Updated=TO_DATE('2008-08-01 14:34:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53236 AND AD_Language LIKE 'es_%'
;

-- Aug 1, 2008 2:34:24 PM COT
UPDATE AD_Process_Para_Trl SET IsTranslated='Y',Name='Descripción',Updated=TO_DATE('2008-08-01 14:34:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53237 AND AD_Language LIKE 'es_%'
;

-- Aug 1, 2008 2:34:31 PM COT
UPDATE AD_Process_Para_Trl SET IsTranslated='Y',Name='Fecha Transferencia',Updated=TO_DATE('2008-08-01 14:34:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53238 AND AD_Language LIKE 'es_%'
;

-- Aug 1, 2008 2:34:40 PM COT
UPDATE AD_Process_Para_Trl SET IsTranslated='Y',Name='Fecha contable',Updated=TO_DATE('2008-08-01 14:34:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53239 AND AD_Language LIKE 'es_%'
;


-- 2035176 - Immediate Bank Transfer
-- MOVE MENU

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=218
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=153
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=263
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=166
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=203
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=236
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=183
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=160
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=278
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=345
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53014
;

UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53108
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=241
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=288
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=432
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=243
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=413
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=538
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=462
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=505
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=235
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=511
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=245
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=251
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=246
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=509
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=510
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=496
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=497
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=304
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=255
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=19, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=286
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=20, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=287
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=21, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=438
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=22, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=234
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=23, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=244
;

UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=24, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53187
;

