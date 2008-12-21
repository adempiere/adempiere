-- Aug 5, 2008 10:14:58 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para SET IsRange='Y',Updated=TO_TIMESTAMP('2008-08-05 22:14:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=484
;

-- Aug 5, 2008 10:16:37 PM CDT
-- DRP Functionality
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2182,0,271,53241,20,'ConsolidateDocument',TO_TIMESTAMP('2008-08-05 22:16:27','YYYY-MM-DD HH24:MI:SS'),0,'Consolidate Lines into one Document','EE01',1,'Y','Y','N','N','Consolidate to one Document',30,TO_TIMESTAMP('2008-08-05 22:16:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 5, 2008 10:16:37 PM CDT
-- DRP Functionality
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53241 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Aug 5, 2008 10:16:49 PM CDT
-- DRP Functionality
UPDATE AD_Process_Para SET SeqNo=40,Updated=TO_TIMESTAMP('2008-08-05 22:16:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=486
;

