-- Dec 8, 2008 9:17:03 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53712,0,'CostCollectorType',TO_TIMESTAMP('2008-12-08 21:16:59','YYYY-MM-DD HH24:MI:SS'),0,'Transaction Type for Manufacturing Management','EE01','Y','Cost Collector Type','Cost Collector Type',TO_TIMESTAMP('2008-12-08 21:16:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 8, 2008 9:17:03 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53712 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 8, 2008 9:20:29 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,VFormat,ValidationType) VALUES (0,0,53287,TO_TIMESTAMP('2008-12-08 21:20:27','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N','PP_CostCollectorType Transaction Manufacturing Management',TO_TIMESTAMP('2008-12-08 21:20:27','YYYY-MM-DD HH24:MI:SS'),0,' ','L')
;

-- Dec 8, 2008 9:20:29 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53287 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Dec 8, 2008 9:32:54 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53442,53287,TO_TIMESTAMP('2008-12-08 21:32:51','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Receipt Material',TO_TIMESTAMP('2008-12-08 21:32:51','YYYY-MM-DD HH24:MI:SS'),0,'100')
;

-- Dec 8, 2008 9:32:54 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53442 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Dec 8, 2008 9:34:41 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53443,53287,TO_TIMESTAMP('2008-12-08 21:34:40','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Component Issue',TO_TIMESTAMP('2008-12-08 21:34:40','YYYY-MM-DD HH24:MI:SS'),0,'110')
;

-- Dec 8, 2008 9:34:41 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53443 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Dec 8, 2008 9:38:41 PM ECT
-- Cost Collector Type Manufacturing Management
UPDATE AD_Ref_List SET Name='Material Receipt',Updated=TO_TIMESTAMP('2008-12-08 21:38:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53442
;

-- Dec 8, 2008 9:38:41 PM ECT
-- Cost Collector Type Manufacturing Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53442
;

-- Dec 8, 2008 9:40:03 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53444,53287,TO_TIMESTAMP('2008-12-08 21:40:02','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Usege Variance',TO_TIMESTAMP('2008-12-08 21:40:02','YYYY-MM-DD HH24:MI:SS'),0,'120')
;

-- Dec 8, 2008 9:40:03 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53444 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Dec 8, 2008 9:43:53 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53445,53287,TO_TIMESTAMP('2008-12-08 21:43:52','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Method Change Variance',TO_TIMESTAMP('2008-12-08 21:43:52','YYYY-MM-DD HH24:MI:SS'),0,'130')
;

-- Dec 8, 2008 9:43:53 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53445 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Dec 8, 2008 9:44:06 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53446,53287,TO_TIMESTAMP('2008-12-08 21:44:05','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Rate Variance',TO_TIMESTAMP('2008-12-08 21:44:05','YYYY-MM-DD HH24:MI:SS'),0,'140')
;

-- Dec 8, 2008 9:44:06 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53446 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Dec 8, 2008 9:44:23 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53447,53287,TO_TIMESTAMP('2008-12-08 21:44:22','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Mix Variance',TO_TIMESTAMP('2008-12-08 21:44:22','YYYY-MM-DD HH24:MI:SS'),0,'150')
;

-- Dec 8, 2008 9:44:23 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53447 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Dec 8, 2008 9:52:18 PM ECT
-- Cost Collector Type Manufacturing Management
UPDATE AD_Column SET AD_Element_ID=53712, AD_Reference_Value_ID=53287, ColumnName='CostCollectorType', Description='Transaction Type for Manufacturing Management', Help=NULL, Name='Cost Collector Type',Updated=TO_TIMESTAMP('2008-12-08 21:52:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53827
;

-- Dec 8, 2008 9:52:18 PM ECT
-- Cost Collector Type Manufacturing Management
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=53827
;

-- Dec 8, 2008 9:52:19 PM ECT
-- Cost Collector Type Manufacturing Management
UPDATE AD_Field SET Name='Cost Collector Type', Description='Transaction Type for Manufacturing Management', Help=NULL WHERE AD_Column_ID=53827 AND IsCentrallyMaintained='Y'
;

-- Dec 8, 2008 10:01:03 PM ECT
-- Cost Collector Type Manufacturing Management
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=54082
;

-- Dec 8, 2008 10:01:04 PM ECT
-- Cost Collector Type Manufacturing Management
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=54106
;

-- Dec 8, 2008 10:01:04 PM ECT
-- Cost Collector Type Manufacturing Management
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=54107
;

-- Dec 8, 2008 10:01:04 PM ECT
-- Cost Collector Type Manufacturing Management
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=54108
;

-- Dec 8, 2008 10:01:04 PM ECT
-- Cost Collector Type Manufacturing Management
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=54109
;

-- Dec 8, 2008 10:01:04 PM ECT
-- Cost Collector Type Manufacturing Management
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=54110
;

-- Dec 8, 2008 10:01:19 PM ECT
-- Cost Collector Type Manufacturing Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2008-12-08 22:01:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54082
;

-- Dec 8, 2008 10:02:35 PM ECT
-- Cost Collector Type Manufacturing Management
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2008-12-08 22:02:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54082
;

-- Dec 8, 2008 10:03:21 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53448,53287,TO_TIMESTAMP('2008-12-08 22:03:20','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Activity Control Report',TO_TIMESTAMP('2008-12-08 22:03:20','YYYY-MM-DD HH24:MI:SS'),0,'160')
;

-- Dec 8, 2008 10:03:21 PM ECT
-- Cost Collector Type Manufacturing Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53448 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Dec 8, 2008 10:07:24 PM ECT
-- Cost Collector Type Manufacturing Management
DELETE FROM AD_Ref_List_Trl WHERE AD_Ref_List_ID=53231
;

-- Dec 8, 2008 10:07:24 PM ECT
-- Cost Collector Type Manufacturing Management
DELETE FROM AD_Ref_List WHERE AD_Ref_List_ID=53231
;

-- Dec 8, 2008 10:07:33 PM ECT
-- Cost Collector Type Manufacturing Management
DELETE FROM AD_Ref_List_Trl WHERE AD_Ref_List_ID=53233
;

-- Dec 8, 2008 10:07:33 PM ECT
-- Cost Collector Type Manufacturing Management
DELETE FROM AD_Ref_List WHERE AD_Ref_List_ID=53233
;

-- Dec 8, 2008 10:07:42 PM ECT
-- Cost Collector Type Manufacturing Management
DELETE FROM AD_Ref_List_Trl WHERE AD_Ref_List_ID=53234
;

-- Dec 8, 2008 10:07:42 PM ECT
-- Cost Collector Type Manufacturing Management
DELETE FROM AD_Ref_List WHERE AD_Ref_List_ID=53234
;

-- Dec 8, 2008 10:07:49 PM ECT
-- Cost Collector Type Manufacturing Management
DELETE FROM AD_Ref_List_Trl WHERE AD_Ref_List_ID=53236
;

-- Dec 8, 2008 10:07:49 PM ECT
-- Cost Collector Type Manufacturing Management
DELETE FROM AD_Ref_List WHERE AD_Ref_List_ID=53236
;

-- Dec 8, 2008 10:07:53 PM ECT
-- Cost Collector Type Manufacturing Management
DELETE FROM AD_Ref_List_Trl WHERE AD_Ref_List_ID=53237
;

-- Dec 8, 2008 10:07:53 PM ECT
-- Cost Collector Type Manufacturing Management
DELETE FROM AD_Ref_List WHERE AD_Ref_List_ID=53237
;

-- Dec 8, 2008 10:07:57 PM ECT
-- Cost Collector Type Manufacturing Management
DELETE FROM AD_Ref_List_Trl WHERE AD_Ref_List_ID=53238
;

-- Dec 8, 2008 10:07:57 PM ECT
-- Cost Collector Type Manufacturing Management
DELETE FROM AD_Ref_List WHERE AD_Ref_List_ID=53238
;

-- Dec 8, 2008 10:08:27 PM ECT
-- Cost Collector Type Manufacturing Management
UPDATE AD_Ref_Table SET WhereClause='C_DocType.AD_Client_ID=@#AD_Client_ID@  AND C_DocType.DocBaseType IN  (''MOP'',''MOF'',''MQO'') ',Updated=TO_TIMESTAMP('2008-12-08 22:08:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53233
;

ALTER TABLE PP_Cost_Collector ADD COLUMN CostCollectorType VARCHAR(3);

update PP_Cost_Collector set CostCollectorType='100' where MovementType in ('P+','W+');

update PP_Cost_Collector set CostCollectorType='110' where CostCollectorType is null;

alter table PP_Cost_Collector drop column MovementType;

-- 09.12.2008 19:27:54 EET
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET FieldLength=3,Updated=TO_TIMESTAMP('2008-12-09 19:27:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53827
;
-- 09.12.2008 19:32:27 EET
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-12-09 19:32:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53827
;

-- 09.12.2008 19:32:30 EET
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
insert into t_alter_column values('pp_cost_collector','CostCollectorType','VARCHAR(3)',null,'NULL')
;

-- 09.12.2008 19:32:30 EET
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
insert into t_alter_column values('pp_cost_collector','CostCollectorType',null,'NOT NULL',null)
;

