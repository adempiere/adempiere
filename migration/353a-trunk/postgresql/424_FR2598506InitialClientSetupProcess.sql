-- Feb 14, 2009 10:26:49 AM COT
-- FR2598506 Implement Initial Client Setup
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('4',0,0,53161,'org.adempiere.process.InitialClientSetup',TO_TIMESTAMP('2009-02-14 10:26:47','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','N','Initial Client Setup Process','Y',0,0,TO_TIMESTAMP('2009-02-14 10:26:47','YYYY-MM-DD HH24:MI:SS'),100,'InitialClientSetup')
;

-- Feb 14, 2009 10:26:49 AM COT
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53161 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Feb 14, 2009 10:26:49 AM COT
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53161,50002,TO_TIMESTAMP('2009-02-14 10:26:49','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2009-02-14 10:26:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:26:49 AM COT
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53161,0,TO_TIMESTAMP('2009-02-14 10:26:49','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2009-02-14 10:26:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:26:49 AM COT
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53161,102,TO_TIMESTAMP('2009-02-14 10:26:49','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2009-02-14 10:26:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:26:49 AM COT
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53161,103,TO_TIMESTAMP('2009-02-14 10:26:49','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2009-02-14 10:26:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:26:49 AM COT
INSERT INTO AD_Process_Access (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,53161,50001,TO_TIMESTAMP('2009-02-14 10:26:49','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2009-02-14 10:26:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:27:04 AM COT
INSERT INTO AD_Menu ("action",AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('P',0,53202,0,53161,TO_TIMESTAMP('2009-02-14 10:27:03','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','Initial Client Setup Process',TO_TIMESTAMP('2009-02-14 10:27:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:27:04 AM COT
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53202 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Feb 14, 2009 10:27:04 AM COT
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 53202, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53202)
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=156, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=225
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=156, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53202
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=156, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=261
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=156, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=148
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=156, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=529
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=156, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=397
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=156, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=531
;

-- Feb 14, 2009 10:27:08 AM COT
UPDATE AD_TreeNodeMM SET Parent_ID=156, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=530
;

-- Feb 14, 2009 10:28:55 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53161,53282,10,'ClientName',TO_TIMESTAMP('2009-02-14 10:28:54','YYYY-MM-DD HH24:MI:SS'),100,'D',60,'Y','Y','Y','N','Client Name',10,TO_TIMESTAMP('2009-02-14 10:28:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:28:55 AM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53282 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 14, 2009 10:29:18 AM COT
UPDATE AD_Process_Para SET DefaultValue='client',Updated=TO_TIMESTAMP('2009-02-14 10:29:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53282
;

-- Feb 14, 2009 10:29:55 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53161,53283,10,'OrgName',TO_TIMESTAMP('2009-02-14 10:29:55','YYYY-MM-DD HH24:MI:SS'),100,'org','D',60,'Y','Y','Y','N','Organization Name',10,TO_TIMESTAMP('2009-02-14 10:29:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:29:55 AM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53283 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 14, 2009 10:30:44 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53161,53284,10,'AdminUserName',TO_TIMESTAMP('2009-02-14 10:30:43','YYYY-MM-DD HH24:MI:SS'),100,'clientAdmin','D',60,'Y','Y','Y','N','Administrative User Name',20,TO_TIMESTAMP('2009-02-14 10:30:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:30:44 AM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53284 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 14, 2009 10:31:12 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53161,53285,10,'NormalUserName',TO_TIMESTAMP('2009-02-14 10:31:12','YYYY-MM-DD HH24:MI:SS'),100,'clientUser','D',60,'Y','Y','Y','N','Normal User Name',40,TO_TIMESTAMP('2009-02-14 10:31:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:31:12 AM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53285 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 14, 2009 10:31:19 AM COT
UPDATE AD_Process_Para SET SeqNo=20,Updated=TO_TIMESTAMP('2009-02-14 10:31:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53283
;

-- Feb 14, 2009 10:31:21 AM COT
UPDATE AD_Process_Para SET SeqNo=30,Updated=TO_TIMESTAMP('2009-02-14 10:31:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53284
;

-- Feb 14, 2009 10:38:20 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,193,0,53161,53286,19,'C_Currency_ID',TO_TIMESTAMP('2009-02-14 10:38:19','YYYY-MM-DD HH24:MI:SS'),100,'@SQL=SELECT MIN(cu.C_Currency_ID) AS DefaultValue FROM C_Currency cu JOIN C_Country co ON (co.C_Currency_ID = cu.C_Currency_ID) JOIN AD_Language l ON (co.AD_Language = l.AD_Language) WHERE l.AD_Language = ''@#AD_Language@''','The Currency for this record','D',0,'Indicates the Currency to be used when processing or reporting on this record','Y','Y','Y','N','Currency',50,TO_TIMESTAMP('2009-02-14 10:38:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:38:20 AM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53286 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 14, 2009 10:44:07 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,192,0,53161,53287,19,'C_Country_ID',TO_TIMESTAMP('2009-02-14 10:44:06','YYYY-MM-DD HH24:MI:SS'),100,'@SQL=SELECT MIN(co.C_Country_ID) AS DefaultValue FROM C_Country co JOIN AD_Language l ON (co.AD_Language = l.AD_Language) WHERE l.AD_Language = ''@#AD_Language@''','Country','D',0,'The Country defines a Country.  Each Country must be defined before it can be used in any document.','Y','Y','Y','N','Country',60,TO_TIMESTAMP('2009-02-14 10:44:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:44:07 AM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53287 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 14, 2009 10:45:57 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,209,0,53161,53288,19,153,'C_Region_ID',TO_TIMESTAMP('2009-02-14 10:45:57','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Y','N','N','Region',70,TO_TIMESTAMP('2009-02-14 10:45:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:45:57 AM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53288 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 14, 2009 10:47:58 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53161,53289,10,'CityName',TO_TIMESTAMP('2009-02-14 10:47:58','YYYY-MM-DD HH24:MI:SS'),100,'D',60,'Y','Y','N','N','City Name',80,TO_TIMESTAMP('2009-02-14 10:47:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:47:58 AM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53289 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 14, 2009 10:49:19 AM COT
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52045,'C_City.C_Region_ID=@C_Region_ID@',TO_TIMESTAMP('2009-02-14 10:49:17','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_City of Region','S',TO_TIMESTAMP('2009-02-14 10:49:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:49:29 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1830,0,53161,53290,19,52045,'C_City_ID',TO_TIMESTAMP('2009-02-14 10:49:28','YYYY-MM-DD HH24:MI:SS'),100,'D',60,'Y','Y','N','N','City',90,TO_TIMESTAMP('2009-02-14 10:49:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:49:29 AM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53290 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 14, 2009 10:52:43 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53161,53291,20,'IsUseBPDimension',TO_TIMESTAMP('2009-02-14 10:52:42','YYYY-MM-DD HH24:MI:SS'),100,'Y','Use BP accounting dimension','D',0,'Define if this client will use business partner accounting dimension.  This can be changed later in Accounting Schema window of the client.','Y','Y','Y','N','BP Accounting',100,TO_TIMESTAMP('2009-02-14 10:52:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:52:43 AM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53291 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 14, 2009 10:53:18 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53161,53292,20,'IsUseBPDimension',TO_TIMESTAMP('2009-02-14 10:53:17','YYYY-MM-DD HH24:MI:SS'),100,'Y','Use Product accounting dimension','D',0,'Define if this client will use product accounting dimension.  This can be changed later in Accounting Schema window of the client.','Y','Y','Y','N','Product Accounting',110,TO_TIMESTAMP('2009-02-14 10:53:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:53:18 AM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53292 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 14, 2009 10:53:54 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53161,53293,20,'IsUseProjectDimension',TO_TIMESTAMP('2009-02-14 10:53:54','YYYY-MM-DD HH24:MI:SS'),100,'N','Use Project accounting dimension','D',0,'Define if this client will use project accounting dimension.  This can be changed later in Accounting Schema window of the client.','Y','Y','Y','N','Project Accounting',120,TO_TIMESTAMP('2009-02-14 10:53:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:53:54 AM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53293 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 14, 2009 10:54:01 AM COT
UPDATE AD_Process_Para SET ColumnName='IsUseProductDimension',Updated=TO_TIMESTAMP('2009-02-14 10:54:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53292
;

-- Feb 14, 2009 10:54:27 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53161,53294,20,'IsUseCampaignDimension',TO_TIMESTAMP('2009-02-14 10:54:26','YYYY-MM-DD HH24:MI:SS'),100,'N','Use Campaign accounting dimension','D',0,'Define if this client will use campaign accounting dimension.  This can be changed later in Accounting Schema window of the client.','Y','Y','Y','N','Campaign Accounting',130,TO_TIMESTAMP('2009-02-14 10:54:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:54:27 AM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53294 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 14, 2009 10:54:56 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53161,53295,20,'IsUseSalesRegionDimension',TO_TIMESTAMP('2009-02-14 10:54:56','YYYY-MM-DD HH24:MI:SS'),100,'N','Use Sales Region accounting dimension','D',0,'Define if this client will use sales region accounting dimension.  This can be changed later in Accounting Schema window of the client.','Y','Y','Y','N','Sales Region Accounting',140,TO_TIMESTAMP('2009-02-14 10:54:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:54:56 AM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53295 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 14, 2009 10:55:43 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53161,53296,39,'CoAFile',TO_TIMESTAMP('2009-02-14 10:55:42','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Y','Y','N','Chart of Accounts File',150,TO_TIMESTAMP('2009-02-14 10:55:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 14, 2009 10:55:43 AM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53296 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Feb 14, 2009 10:56:19 AM COT
UPDATE AD_Process_Para SET Description='Location of the chart of accounts to be used with this client.  At this stage just the default accounts will be created.',Updated=TO_TIMESTAMP('2009-02-14 10:56:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53296
;

-- Feb 14, 2009 10:56:19 AM COT
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53296
;

