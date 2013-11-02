-- Dec 15, 2012 5:28:44 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Form (AccessLevel,AD_Client_ID,AD_Form_ID,AD_Org_ID,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,Name,Updated,UpdatedBy) VALUES ('1',0,53027,0,'org.adempiere.apps.form.VInvoiceGenFromShipment',TO_DATE('2012-12-15 17:28:31','YYYY-MM-DD HH24:MI:SS'),100,'Select shipments and generate invoices','U','Generate Invoices from Shipments.  
Select the shipmens to generate the invoice for.','Y','N','Generate Invoices from Shipments (manual)',TO_DATE('2012-12-15 17:28:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 15, 2012 5:28:44 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Form_Trl (AD_Language,AD_Form_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Form_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Form t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Form_ID=53027 AND NOT EXISTS (SELECT * FROM AD_Form_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Form_ID=t.AD_Form_ID)
;

-- Dec 15, 2012 5:33:19 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Menu (Action,AD_Client_ID,AD_Form_ID,AD_Menu_ID,AD_Org_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('X',0,53027,53474,0,TO_DATE('2012-12-15 17:33:18','YYYY-MM-DD HH24:MI:SS'),100,'Select shipments and generate invoices','U','Y','Y','N','Y','N','Generate Invoices from Shipments (manual)',TO_DATE('2012-12-15 17:33:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 15, 2012 5:33:19 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53474 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Dec 15, 2012 5:33:19 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID, 0, 'Y', SysDate, 100, SysDate, 100,t.AD_Tree_ID, 53474, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53474)
;

-- Dec 15, 2012 5:33:27 PM CET
-- Generate Invoices from Shipments
UPDATE AD_TreeNodeMM SET Parent_ID=458, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=217
;

-- Dec 15, 2012 5:33:27 PM CET
-- Generate Invoices from Shipments
UPDATE AD_TreeNodeMM SET Parent_ID=458, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=192
;

-- Dec 15, 2012 5:33:27 PM CET
-- Generate Invoices from Shipments
UPDATE AD_TreeNodeMM SET Parent_ID=458, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53474
;

-- Dec 15, 2012 5:33:27 PM CET
-- Generate Invoices from Shipments
UPDATE AD_TreeNodeMM SET Parent_ID=458, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=178
;

-- Dec 15, 2012 5:33:27 PM CET
-- Generate Invoices from Shipments
UPDATE AD_TreeNodeMM SET Parent_ID=458, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=377
;

-- Dec 15, 2012 5:33:27 PM CET
-- Generate Invoices from Shipments
UPDATE AD_TreeNodeMM SET Parent_ID=458, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=347
;

-- Dec 15, 2012 5:35:57 PM CET
-- Generate Invoices from Shipments
UPDATE AD_Menu SET IsSOTrx='Y',Updated=TO_DATE('2012-12-15 17:35:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=217
;

-- Dec 15, 2012 5:53:20 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,CopyFromProcess,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('1',0,0,53345,'org.adempiere.process.InvoiceGenerateFromShipment','N',TO_DATE('2012-12-15 17:53:17','YYYY-MM-DD HH24:MI:SS'),100,'Generate Invoices from Shipments','U','Y','N','N','N','N','Generate Invoices from Shipments','Y',0,0,TO_DATE('2012-12-15 17:53:17','YYYY-MM-DD HH24:MI:SS'),100,'C_Invoice_Generate_from_Shipment')
;

-- Dec 15, 2012 5:53:20 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53345 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Dec 15, 2012 5:54:14 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,267,0,53345,53837,15,'DateInvoiced',TO_DATE('2012-12-15 17:54:13','YYYY-MM-DD HH24:MI:SS'),100,'@#Date@','U',1,'Y','Y','N','N','Date Invoiced',10,TO_DATE('2012-12-15 17:54:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 15, 2012 5:54:14 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53837 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Dec 15, 2012 5:56:20 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,113,0,53345,53838,18,130,'AD_Org_ID',TO_DATE('2012-12-15 17:56:19','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','U',0,'Y','Y','Y','N','Organization',20,TO_DATE('2012-12-15 17:56:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 15, 2012 5:56:20 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53838 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Dec 15, 2012 5:57:56 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1025,0,53345,53839,30,337,'M_InOut_ID',TO_DATE('2012-12-15 17:57:55','YYYY-MM-DD HH24:MI:SS'),100,'U',0,'Y','Y','N','N','Shipment',30,TO_DATE('2012-12-15 17:57:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 15, 2012 5:57:56 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53839 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Dec 15, 2012 5:59:24 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,187,0,53345,53840,30,173,'C_BPartner_ID',TO_DATE('2012-12-15 17:59:23','YYYY-MM-DD HH24:MI:SS'),100,'U',0,'Y','Y','N','N','Business Partner',40,TO_DATE('2012-12-15 17:59:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 15, 2012 5:59:24 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53840 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Dec 15, 2012 6:00:52 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,287,0,53345,53841,17,135,219,'DocAction',TO_DATE('2012-12-15 18:00:50','YYYY-MM-DD HH24:MI:SS'),100,'CO','U',0,'Y','Y','Y','N','Document Action',50,TO_DATE('2012-12-15 18:00:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 15, 2012 6:00:52 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53841 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Dec 15, 2012 6:01:26 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2182,0,53345,53842,20,'ConsolidateDocument',TO_DATE('2012-12-15 18:01:25','YYYY-MM-DD HH24:MI:SS'),100,'Y','U',1,'Y','Y','N','N','Consolidate to one Document',60,TO_DATE('2012-12-15 18:01:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 15, 2012 6:01:26 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53842 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Dec 15, 2012 6:04:20 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Menu (Action,AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('P',0,53475,0,53345,TO_DATE('2012-12-15 18:04:19','YYYY-MM-DD HH24:MI:SS'),100,'Select shipments and generate invoices','U','Y','Y','N','Y','N','Generate Invoices from Shipments',TO_DATE('2012-12-15 18:04:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 15, 2012 6:04:20 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53475 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- Dec 15, 2012 6:04:20 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID, 0, 'Y', SysDate, 100, SysDate, 100,t.AD_Tree_ID, 53475, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53475)
;

-- Dec 15, 2012 6:04:24 PM CET
-- Generate Invoices from Shipments
UPDATE AD_TreeNodeMM SET Parent_ID=458, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=217
;

-- Dec 15, 2012 6:04:24 PM CET
-- Generate Invoices from Shipments
UPDATE AD_TreeNodeMM SET Parent_ID=458, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=192
;

-- Dec 15, 2012 6:04:24 PM CET
-- Generate Invoices from Shipments
UPDATE AD_TreeNodeMM SET Parent_ID=458, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53474
;

-- Dec 15, 2012 6:04:24 PM CET
-- Generate Invoices from Shipments
UPDATE AD_TreeNodeMM SET Parent_ID=458, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53475
;

-- Dec 15, 2012 6:04:24 PM CET
-- Generate Invoices from Shipments
UPDATE AD_TreeNodeMM SET Parent_ID=458, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=178
;

-- Dec 15, 2012 6:04:24 PM CET
-- Generate Invoices from Shipments
UPDATE AD_TreeNodeMM SET Parent_ID=458, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=377
;

-- Dec 15, 2012 6:04:24 PM CET
-- Generate Invoices from Shipments
UPDATE AD_TreeNodeMM SET Parent_ID=458, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=347
;

