-- 06.05.2009 23:57:08 EEST
-- -
INSERT INTO AD_Process_Para (AD_Process_Para_ID,AD_Client_ID,Updated,IsActive,Created,UpdatedBy,AD_Org_ID,CreatedBy,FieldLength,Name,IsCentrallyMaintained,IsRange,Description,AD_Process_ID,EntityType,ColumnName,IsMandatory,SeqNo,AD_Reference_ID,AD_Element_ID,DefaultValue) VALUES (53312,0,TO_TIMESTAMP('2009-05-06 23:57:07','YYYY-MM-DD HH24:MI:SS'),'Y',TO_TIMESTAMP('2009-05-06 23:57:07','YYYY-MM-DD HH24:MI:SS'),0,0,0,1,'Only Validate Data','Y','N','Validate the date and do not process',194,'D','IsValidateOnly','Y',30,20,2168,'N')
;

-- 06.05.2009 23:57:08 EEST
-- -
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Name,Description,Help, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Name,t.Description,t.Help, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53312 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

