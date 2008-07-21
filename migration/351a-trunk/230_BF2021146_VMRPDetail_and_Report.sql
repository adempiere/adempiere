-- Jul 16, 2008 3:40:38 PM CDT
-- MRP Detail Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,113,0,53018,53211,19,130,'AD_Org_ID',TO_DATE('2008-07-16 15:40:13','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','Organization',10,TO_DATE('2008-07-16 15:40:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 16, 2008 3:40:38 PM CDT
-- MRP Detail Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53211 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 16, 2008 3:42:51 PM CDT
-- MRP Detail Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1777,0,53018,53212,19,52002,'Resource',TO_DATE('2008-07-16 15:42:44','YYYY-MM-DD HH24:MI:SS'),100,'Resource','EE01',10,'Resource','Y','Y','N','N','Resource',10,TO_DATE('2008-07-16 15:42:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 16, 2008 3:42:51 PM CDT
-- MRP Detail Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53212 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 16, 2008 3:47:18 PM CDT
-- MRP Detail Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1514,0,53018,53213,17,154,'Priority',TO_DATE('2008-07-16 15:47:16','YYYY-MM-DD HH24:MI:SS'),100,'Priority','EE01',10,'Priority','Y','Y','N','N','Priority',70,TO_DATE('2008-07-16 15:47:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 16, 2008 3:47:18 PM CDT
-- MRP Detail Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53213 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 16, 2008 3:49:11 PM CDT
-- MRP Detail Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,459,0,53018,53214,19,189,'M_Warehouse_ID',TO_DATE('2008-07-16 15:49:05','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01',22,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','N','N','Warehouse',80,TO_DATE('2008-07-16 15:49:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 16, 2008 3:49:11 PM CDT
-- MRP Detail Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53214 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 16, 2008 3:54:22 PM CDT
-- MRP Detail Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,454,0,53018,53215,30,'Product',TO_DATE('2008-07-16 15:54:14','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','EE01',22,'Identifies an item which is either purchased or sold in this organization.','Y','Y','N','N','Product',90,TO_DATE('2008-07-16 15:54:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 16, 2008 3:54:22 PM CDT
-- MRP Detail Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53215 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 16, 2008 3:55:17 PM CDT
-- MRP Detail Report
UPDATE AD_Reference SET Name='_TypeMRP',Updated=TO_DATE('2008-07-16 15:55:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53229
;

-- Jul 16, 2008 3:55:17 PM CDT
-- MRP Detail Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53229
;

-- Jul 16, 2008 3:55:44 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List SET Name='Forecast',Updated=TO_DATE('2008-07-16 15:55:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53275
;

-- Jul 16, 2008 3:55:44 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53275
;

-- Jul 16, 2008 3:56:01 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List SET Name='Manufacturing Order',Updated=TO_DATE('2008-07-16 15:56:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53276
;

-- Jul 16, 2008 3:56:01 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53276
;

-- Jul 16, 2008 3:56:16 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List SET Name='Purchase Order',Updated=TO_DATE('2008-07-16 15:56:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53277
;

-- Jul 16, 2008 3:56:16 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53277
;

-- Jul 16, 2008 3:57:10 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List SET Name='Material Requisition',Updated=TO_DATE('2008-07-16 15:57:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53278
;

-- Jul 16, 2008 3:57:10 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53278
;

-- Jul 16, 2008 3:57:25 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List SET Name='Orden de Venta',Updated=TO_DATE('2008-07-16 15:57:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53279
;

-- Jul 16, 2008 3:57:25 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53279
;

-- Jul 16, 2008 4:00:16 PM CDT
-- MRP Detail Report
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53435,53229,TO_DATE('2008-07-16 16:00:10','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','Distribution Order',TO_DATE('2008-07-16 16:00:10','YYYY-MM-DD HH24:MI:SS'),100,'DOO')
;

-- Jul 16, 2008 4:00:16 PM CDT
-- MRP Detail Report
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53435 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jul 16, 2008 4:00:24 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List SET Name='Sales Order',Updated=TO_DATE('2008-07-16 16:00:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53279
;

-- Jul 16, 2008 4:00:24 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53279
;

-- Jul 16, 2008 4:01:47 PM CDT
-- MRP Detail Report
UPDATE AD_Reference SET Name='_PP_MRP Type',Updated=TO_DATE('2008-07-16 16:01:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53230
;

-- Jul 16, 2008 4:01:47 PM CDT
-- MRP Detail Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53230
;

-- Jul 16, 2008 4:01:55 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List SET Name='Demand',Updated=TO_DATE('2008-07-16 16:01:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53280
;

-- Jul 16, 2008 4:01:55 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53280
;

-- Jul 16, 2008 4:02:07 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List SET Name='Supply',Updated=TO_DATE('2008-07-16 16:02:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53281
;

-- Jul 16, 2008 4:02:07 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53281
;

-- Jul 16, 2008 4:14:52 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_DATE('2008-07-16 16:14:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53058
;

-- Jul 16, 2008 4:15:43 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET AD_Reference_ID=17, AD_Reference_Value_ID=319,Updated=TO_DATE('2008-07-16 16:15:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53059
;

-- Jul 16, 2008 4:20:49 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET ColumnName='S_Resource_ID',Updated=TO_DATE('2008-07-16 16:20:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53212
;

-- Jul 16, 2008 4:21:05 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=20,Updated=TO_DATE('2008-07-16 16:21:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53212
;

-- Jul 16, 2008 4:21:17 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=30,Updated=TO_DATE('2008-07-16 16:21:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53214
;

-- Jul 16, 2008 4:21:54 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=40,Updated=TO_DATE('2008-07-16 16:21:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53060
;

-- Jul 16, 2008 4:22:04 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=50,Updated=TO_DATE('2008-07-16 16:22:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53057
;

-- Jul 16, 2008 4:22:33 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=60,Updated=TO_DATE('2008-07-16 16:22:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53215
;

-- Jul 16, 2008 4:22:49 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=70,Updated=TO_DATE('2008-07-16 16:22:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53056
;

-- Jul 16, 2008 4:22:55 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=80,Updated=TO_DATE('2008-07-16 16:22:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53058
;

-- Jul 16, 2008 4:23:11 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=90,Updated=TO_DATE('2008-07-16 16:23:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53215
;

-- Jul 16, 2008 4:23:15 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=100,Updated=TO_DATE('2008-07-16 16:23:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53055
;

-- Jul 16, 2008 4:23:23 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=110,Updated=TO_DATE('2008-07-16 16:23:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53055
;

-- Jul 16, 2008 4:23:36 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=60,Updated=TO_DATE('2008-07-16 16:23:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53059
;

-- Jul 16, 2008 4:26:01 PM CDT
-- MRP Detail Report
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,290,0,53018,53217,10,'DocumentNo',TO_DATE('2008-07-16 16:25:51','YYYY-MM-DD HH24:MI:SS'),100,'Document sequence number of the document','EE01',40,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".','Y','Y','N','N','Document No',100,TO_DATE('2008-07-16 16:25:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 16, 2008 4:26:01 PM CDT
-- MRP Detail Report
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53217 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jul 16, 2008 4:28:28 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET ColumnName='M_Product_ID',Updated=TO_DATE('2008-07-16 16:28:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53215
;

-- Jul 16, 2008 4:28:50 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET FieldLength=22,Updated=TO_DATE('2008-07-16 16:28:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53217
;

-- Jul 16, 2008 4:29:53 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=60,Updated=TO_DATE('2008-07-16 16:29:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53056
;

-- Jul 16, 2008 4:30:01 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=70,Updated=TO_DATE('2008-07-16 16:30:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53058
;

-- Jul 16, 2008 4:30:06 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=80,Updated=TO_DATE('2008-07-16 16:30:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53059
;

-- Jul 16, 2008 4:30:12 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=90,Updated=TO_DATE('2008-07-16 16:30:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53213
;

-- Jul 16, 2008 4:30:27 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=100,Updated=TO_DATE('2008-07-16 16:30:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53215
;

-- Jul 16, 2008 4:30:31 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=110,Updated=TO_DATE('2008-07-16 16:30:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53217
;

-- Jul 16, 2008 4:30:38 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET SeqNo=120,Updated=TO_DATE('2008-07-16 16:30:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53055
;

-- Jul 16, 2008 4:30:59 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET Description='Is MPS',Updated=TO_DATE('2008-07-16 16:30:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53059
;

-- Jul 16, 2008 4:30:59 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53059
;

-- Jul 16, 2008 4:01:55 PM CDT
-- MRP Detail Report
UPDATE AD_Ref_List SET EntityType='EE01',Updated=TO_DATE('2008-07-16 16:01:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53229
;

-- Jul 16, 2008 4:47:31 PM CDT
-- MRP Detail Report
UPDATE AD_Reference SET Name='_MRP Order Type',Updated=TO_DATE('2008-07-16 16:47:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53229
;

-- Jul 16, 2008 4:47:31 PM CDT
-- MRP Detail Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53229
;

-- Jul 16, 2008 4:47:37 PM CDT
-- MRP Detail Report
UPDATE AD_Column SET AD_Element_ID=52020, AD_Reference_ID=17, AD_Reference_Value_ID=53229, ColumnName='OrderType', Description=NULL, Help=NULL, Name='OrderType',Updated=TO_DATE('2008-07-16 16:47:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54055
;

-- Jul 16, 2008 4:47:37 PM CDT
-- MRP Detail Report
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=54055
;

-- Jul 16, 2008 4:47:37 PM CDT
-- MRP Detail Report
UPDATE AD_Field SET Name='OrderType', Description=NULL, Help=NULL WHERE AD_Column_ID=54055 AND IsCentrallyMaintained='Y'
;

-- Jul 16, 2008 4:49:15 PM CDT
-- MRP Detail Report
UPDATE AD_Reference SET Description='MRP type can be a Demand or Supply', Name='_Type MRP',Updated=TO_DATE('2008-07-16 16:49:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53230
;

-- Jul 16, 2008 4:49:15 PM CDT
-- MRP Detail Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53230
;

-- Jul 16, 2008 4:49:18 PM CDT
-- MRP Detail Report
UPDATE AD_Column SET AD_Element_ID=53282, AD_Reference_ID=17, AD_Reference_Value_ID=53230, ColumnName='TypeMRP', Description=NULL, Help=NULL, Name='TypeMRP',Updated=TO_DATE('2008-07-16 16:49:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54054
;

-- Jul 16, 2008 4:49:18 PM CDT
-- MRP Detail Report
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=54054
;

-- Jul 16, 2008 4:49:18 PM CDT
-- MRP Detail Report
UPDATE AD_Field SET Name='TypeMRP', Description=NULL, Help=NULL WHERE AD_Column_ID=54054 AND IsCentrallyMaintained='Y'
;

-- Jul 16, 2008 4:50:46 PM CDT
-- MRP Detail Report
UPDATE AD_Column SET AD_Element_ID=52020, AD_Reference_ID=17, AD_Reference_Value_ID=53229, ColumnName='OrderType', Description=NULL, Help=NULL, Name='OrderType',Updated=TO_DATE('2008-07-16 16:50:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53440
;

-- Jul 16, 2008 4:50:46 PM CDT
-- MRP Detail Report
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=53440
;

-- Jul 16, 2008 4:50:46 PM CDT
-- MRP Detail Report
UPDATE AD_Field SET Name='OrderType', Description=NULL, Help=NULL WHERE AD_Column_ID=53440 AND IsCentrallyMaintained='Y'
;

-- Jul 16, 2008 4:59:27 PM CDT
-- MRP Detail Report
UPDATE AD_Column SET AD_Element_ID=53282, ColumnName='TypeMRP', Description=NULL, Help=NULL, Name='TypeMRP',Updated=TO_DATE('2008-07-16 16:59:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53439
;

-- Jul 16, 2008 4:59:27 PM CDT
-- MRP Detail Report
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=53439
;

-- Jul 16, 2008 4:59:27 PM CDT
-- MRP Detail Report
UPDATE AD_Field SET Name='TypeMRP', Description=NULL, Help=NULL WHERE AD_Column_ID=53439 AND IsCentrallyMaintained='Y'
;

-- Jul 16, 2008 5:00:22 PM CDT
-- MRP Detail Report
UPDATE AD_Reference SET Name='_MRP Type',Updated=TO_DATE('2008-07-16 17:00:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53230
;

-- Jul 16, 2008 5:00:23 PM CDT
-- MRP Detail Report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53230
;

-- Jul 16, 2008 5:02:13 PM CDT
-- MRP Detail Report
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=53230,Updated=TO_DATE('2008-07-16 17:02:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53439
;

ALTER TABLE PP_MRP RENAME COLUMN Type TO OrderType;

ALTER TABLE PP_MRP MODIFY (OrderType NVARCHAR2(3));

ALTER TABLE PP_MRP MODIFY (TypeMRP CHAR(1));

create or replace FUNCTION documentNo
    (
      p_PP_MRP_ID IN pp_mrp.pp_mrp_id%TYPE DEFAULT 0)
    RETURN pp_mrp.value%TYPE
    /*************************************************************************
    * Function documentNofunc - PL/SQL equivalent to pljava SQLJ function
    * Author: Tony Snook (tspc)
    ************************************************************************/
  AS
    v_DocumentNo pp_mrp.value%TYPE := '';

    CURSOR cur
    IS
       SELECT ordertype, m_forecast_id, c_order_id, dd_order_id, pp_order_id, m_requisition_id
        FROM pp_mrp
        WHERE pp_mrp_id = p_pp_mrp_id;   

    rec cur%ROWTYPE;

  BEGIN
    -- If NO id return null
    IF p_PP_MRP_ID = 0 THEN
      CLOSE cur;
      RETURN '';
    END IF;

    CASE
      WHEN rec.ordertype = 'FTC' THEN
         SELECT f.Name INTO v_DocumentNo FROM M_Forecast f WHERE f.M_Forecast_ID=rec.M_Forecast_ID;
      WHEN rec.ordertype = 'POO' THEN
         SELECT co.DocumentNo INTO v_DocumentNo FROM C_Order co WHERE co.C_Order_ID=rec.C_Order_ID;
      WHEN rec.ordertype = 'DOO' THEN
         SELECT do.DocumentNo INTO v_DocumentNo FROM DD_Order DO WHERE do.DD_Order_ID=rec.DD_Order_ID;
      WHEN rec.ordertype = 'SOO' THEN
         SELECT co.DocumentNo INTO v_DocumentNo FROM C_Order co WHERE co.C_Order_ID=rec.C_Order_ID;
      WHEN rec.ordertype = 'MOP' THEN
         SELECT po.DocumentNo INTO v_DocumentNo FROM PP_Order po WHERE po.PP_Order_ID=rec.PP_Order_ID;
      WHEN rec.ordertype = 'POR' THEN
         SELECT r.DocumentNo INTO v_DocumentNo FROM M_Requisition r WHERE r.M_Requisition_ID=rec.M_Requisition_ID;
      ELSE
        v_documentno := '';
    END CASE;

    CLOSE cur;
    --
    RETURN v_DocumentNo;
  END documentNo;
/

DROP VIEW rv_pp_mrp;

CREATE OR REPLACE VIEW rv_pp_mrp AS 
SELECT 
mrp.ad_client_id,
mrp.ad_org_id,
mrp.created,
mrp.createdby,
mrp.isactive,
mrp.updated,
mrp.updatedby,
pp.ismps,
mrp.c_order_id,
mrp.c_orderline_id,
mrp.dateordered,
mrp.dateconfirm,
mrp.datepromised,
mrp.datestartschedule,
mrp.datefinishschedule,
mrp.datestart,
mrp.datesimulation,
mrp.docstatus,
mrp.m_forecast_id,
mrp.m_forecastline_id,
mrp.value,
mrp.m_product_id,
mrp.m_requisition_id,
mrp.m_requisitionline_id,
mrp.m_warehouse_id,
mrp.pp_order_id,
mrp.qty,
mrp.name,
mrp.s_resource_id,
mrp.priority,
mrp.ordertype,
mrp.typemrp,
documentNo(mrp.pp_mrp_id) AS documentNo
FROM pp_mrp mrp
LEFT JOIN pp_product_planning pp ON pp.m_product_id = mrp.m_product_id AND mrp.m_warehouse_id = pp.m_warehouse_id;

DROP VIEW rv_pp_operation_activity;

CREATE OR REPLACE VIEW rv_pp_operation_activity AS 
SELECT n.ad_client_id,
n.ad_org_id,
n.created,
n.createdby,
n.isactive,
n.updated,
n.updatedby,
n.pp_order_id,
n.docstatus,
n.value,
n.s_resource_id,
n.durationrequiered,
n.durationreal,
n.durationrequiered - n.durationreal AS duration,
n.qtydelivered,
n.qtyreject,
n.qtyscrap,
n.datestartschedule,
n.datefinishschedule
FROM pp_order_node n;

DROP VIEW rv_pp_order_bomline;

CREATE OR REPLACE VIEW rv_pp_order_bomline AS 
SELECT 
obl.ad_client_id,
obl.ad_org_id,
obl.createdby,
obl.updatedby,
obl.updated,
obl.created,
obl.isactive,
obl.pp_order_bom_id,
obl.pp_order_bomline_id,
obl.pp_order_id,
obl.iscritical, 
obl.componenttype,
obl.m_product_id,
obl.c_uom_id,
round(obl.qtyrequiered, 4) AS qtyrequiered,
round(bomqtyreserved(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyreserved,
round(bomqtyavailable(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyavailable, 
round(bomqtyonhand(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyonhand,
obl.m_warehouse_id,
round(obl.qtybom, 4) AS qtybom,
obl.isqtypercentage,
round(obl.qtybatch, 4) AS qtybatch, 
CASE WHEN o.qtybatchs = 0 THEN 1 ELSE round(obl.qtyrequiered / o.qtybatchs, 4) END AS qtybatchsize
FROM pp_order_bomline obl
JOIN pp_order o ON o.pp_order_id = obl.pp_order_id;

DROP VIEW rv_pp_order_receipt_issue;

DROP VIEW rv_pp_order_storage;

CREATE OR REPLACE VIEW rv_pp_order_storage AS 
SELECT 
obl.ad_client_id,
obl.ad_org_id,
obl.createdby,
obl.updatedby,
obl.updated,
obl.created,
obl.isactive,
obl.pp_order_bom_id,
obl.pp_order_bomline_id,
obl.pp_order_id,
obl.iscritical,
obl.m_product_id,
( SELECT p.name FROM m_product p WHERE p.m_product_id = o.m_product_id) AS name,
obl.c_uom_id,
s.qtyonhand,
round(obl.qtyrequiered, 4) AS qtyrequiered, 
CASE WHEN o.qtybatchs = 0 THEN 1 ELSE round(obl.qtyrequiered / o.qtybatchs, 4) END AS qtybatchsize,
round(bomqtyreserved(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyreserved,
round(bomqtyavailable(obl.m_product_id, obl.m_warehouse_id,0), 4) AS qtyavailable,
obl.m_warehouse_id,
obl.qtybom,
obl.isqtypercentage,
round(obl.qtybatch, 4) AS qtybatch,
obl.m_attributesetinstance_id,
l.m_locator_id,
l.x,
l.y,
l.z
FROM pp_order_bomline obl
JOIN pp_order o ON o.pp_order_id = obl.pp_order_id
LEFT JOIN m_storage s ON s.m_product_id = obl.m_product_id AND s.qtyonhand <> 0 AND obl.m_warehouse_id = (( SELECT ld.m_warehouse_id FROM m_locator ld WHERE s.m_locator_id = ld.m_locator_id))
LEFT JOIN m_locator l ON l.m_locator_id = s.m_locator_id
;

CREATE OR REPLACE VIEW rv_pp_order_receipt_issue AS 
SELECT obl.pp_order_bomline_id,
obl.iscritical,
p.value,
obl.m_product_id,
mos.name AS productname,
mos.m_attributesetinstance_id,
asi.description AS instancename,
mos.c_uom_id,
u.name AS uomname,
obl.qtyrequiered,
obl.qtyreserved AS qtyreserved_order,
mos.qtyonhand,
mos.qtyreserved AS qtyreserved_storage,
mos.qtyavailable,
mos.m_locator_id,
mos.m_warehouse_id,
w.name AS warehousename,
mos.qtybom,
mos.isqtypercentage,
mos.qtybatch,
obl.componenttype,
mos.qtyrequiered - obl.qtydelivered AS qtyopen,
obl.pp_order_id
FROM rv_pp_order_storage mos
JOIN pp_order_bomline obl ON mos.pp_order_bomline_id = obl.pp_order_bomline_id
JOIN m_attributesetinstance asi ON mos.m_attributesetinstance_id = asi.m_attributesetinstance_id
JOIN c_uom u ON mos.c_uom_id = u.c_uom_id
JOIN m_product p ON mos.m_product_id = p.m_product_id
JOIN m_warehouse w ON mos.m_warehouse_id = w.m_warehouse_id;

DROP VIEW rv_pp_order_transactions;

CREATE OR REPLACE VIEW rv_pp_order_transactions AS 
SELECT DISTINCT 
o.ad_client_id, 
o.ad_org_id, 
o.isactive, 
o.created, 
o.createdby, 
o.updatedby, 
o.updated, 
o.documentno, 
ol.m_product_id,
mt.m_locator_id,
mt.movementdate,
o.pp_order_id,
o.qtydelivered,
o.qtyscrap,
ol.qtydelivered AS qtydeliveredline,
o.qtydelivered * ol.qtybatch / 100 AS qtyissueshouldbe,
ol.qtyscrap AS qtyscrapline,
o.qtyscrap * ol.qtybatch / 100 AS qtyissuescrapshouldbe,
mt.createdby AS createdbyissue,
mt.updatedby AS updatedbyissue,
( SELECT sum(t.movementqty) AS sum FROM m_transaction t WHERE t.pp_order_bomline_id = ol.pp_order_bomline_id) AS qtytodeliver,
(o.qtydelivered + o.qtyscrap) * ol.qtybatch / 100 + (( SELECT sum(t.movementqty) AS sum FROM m_transaction t WHERE t.pp_order_bomline_id = ol.pp_order_bomline_id)) AS differenceqty,
o.issotrx,
o.dateordered
FROM pp_order o
JOIN pp_order_bomline ol ON ol.pp_order_id = o.pp_order_id
LEFT JOIN m_transaction mt ON mt.pp_order_bomline_id = ol.pp_order_bomline_id
;

DROP VIEW rv_pp_order;

CREATE OR REPLACE VIEW rv_pp_order AS 
SELECT 
o.ad_client_id,
o.ad_org_id,
o.isactive,
o.created,
o.createdby,
o.updated,
o.updatedby,
o.pp_order_id,
o.documentno,
o.docstatus,
o.m_warehouse_id,
o.m_product_id,
o.qtyentered,
o.qtyreject,
o.qtyscrap,
o.qtybatchs,
o.qtybatchsize,
o.dateordered,
o.datepromised,
o.datestart,
o.datestartschedule,
o.datefinish,
o.datefinishschedule,
o.dateconfirm,
o.datedelivered,
o.lot,
o.pp_product_bom_id,
o.ad_workflow_id,
( SELECT p.weight FROM m_product p WHERE p.m_product_id = o.m_product_id) AS weight,
o.c_doctypetarget_id,
o.m_attributesetinstance_id,
o.planner_id,
o.priorityrule,
o.ad_orgtrx_id,
o.user1_id,
o.user2_id,
o.c_doctype_id,
o.line,
o.description,
o.s_resource_id,
o.floatbefored,
o.floatafter,
o.c_uom_id,
o.qtyordered,
o.qtydelivered,
o.yield,
o.c_project_id,
o.c_campaign_id,
o.c_activity_id,
o.isapproved,
o.isprinted,
o.isselected, 
o.processed, 
o.assay, 
o.isqtypercentage, 
o.ordertype, 
o.issotrx, 
o.scheduletype, 
o.serno
FROM pp_order o;

DROP VIEW rv_pp_product_bomline;

CREATE OR REPLACE VIEW rv_pp_product_bomline AS 
SELECT 
t.seqno,
t.levelno,
t.levels,
t.ad_client_id,
t.ad_org_id,
t.createdby,
t.updatedby,
t.updated,
t.created,
t.ad_pinstance_id,
bl.isactive,
bl.pp_product_bom_id,
bl.pp_product_bomline_id,
bl.description, bl.iscritical,
bl.componenttype,
t.m_product_id,
bl.c_uom_id,
bl.issuemethod,
bl.line,
bl.m_attributesetinstance_id,
bl.scrap,
bl.validfrom,
bl.validto,
bl.qtybom,
bl.qtybatch,
bl.isqtypercentage
FROM pp_product_bomline bl
RIGHT JOIN t_bomline t ON t.pp_product_bomline_id = bl.pp_product_bomline_id
;


UPDATE AD_Column SET EntityType='EE01' WHERE AD_Table_ID=53021;

UPDATE AD_Table SET EntityType='EE01' WHERE AD_Table_ID=53021;

-- Jul 16, 2008 5:18:24 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET AD_Element_ID=52020, AD_Reference_Value_ID=53229, ColumnName='OrderType', Description='Order Type', Help='Order Type', Name='Order Type',Updated=TO_DATE('2008-07-16 17:18:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53058
;

-- Jul 16, 2008 5:18:24 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53058
;

-- Jul 16, 2008 5:18:35 PM CDT
-- MRP Detail Report
UPDATE AD_Process_Para SET AD_Reference_Value_ID=53230,Updated=TO_DATE('2008-07-16 17:18:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53056
;