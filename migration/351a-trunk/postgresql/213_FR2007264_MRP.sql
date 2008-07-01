-- Jun 30, 2008 7:42:57 PM CDT
-- Improve MRP
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,113,0,53014,53206,19,202,'AD_Org_ID',TO_TIMESTAMP('2008-06-30 19:42:51','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','Organization',10,TO_TIMESTAMP('2008-06-30 19:42:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 30, 2008 7:42:57 PM CDT
-- Improve MRP
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53206 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 30, 2008 7:44:28 PM CDT
-- Improve MRP
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1777,0,53014,53207,19,52002,'S_Resource_ID',TO_TIMESTAMP('2008-06-30 19:44:25','YYYY-MM-DD HH24:MI:SS'),0,'Resource','EE01',22,'Y','Y','Y','N','Resource',20,TO_TIMESTAMP('2008-06-30 19:44:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 30, 2008 7:44:28 PM CDT
-- Improve MRP
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53207 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 30, 2008 7:46:01 PM CDT
-- Improve MRP
UPDATE AD_Process_Para SET AD_Val_Rule_ID=189,Updated=TO_TIMESTAMP('2008-06-30 19:46:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53133
;

-- Jun 30, 2008 7:46:15 PM CDT
-- Improve MRP
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,459,0,53014,53208,19,189,'M_Warehouse_ID',TO_TIMESTAMP('2008-06-30 19:46:13','YYYY-MM-DD HH24:MI:SS'),0,'Storage Warehouse and Service Point','EE01',22,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','N','N','Warehouse',30,TO_TIMESTAMP('2008-06-30 19:46:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 30, 2008 7:46:15 PM CDT
-- Improve MRP
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53208 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

