-- Dec 1, 2009 6:39:49 PM CET
-- FR [2906975] - RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,113,0,53189,53364,19,'AD_Orga_ID',TO_DATE('2009-12-01 18:39:49','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','Organization',10,TO_DATE('2009-12-01 18:39:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 1, 2009 6:39:49 PM CET
-- FR [2906975] - RV_STORAGE_PER_PRODUCT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53364 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Dec 1, 2009 6:40:10 PM CET
-- FR [2906975] - RV_STORAGE_PER_PRODUCT
UPDATE AD_Process_Para SET ColumnName='AD_Org_ID',Updated=TO_DATE('2009-12-01 18:40:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53364
;

-- Dec 1, 2009 6:40:27 PM CET
-- FR [2906975] - RV_STORAGE_PER_PRODUCT
UPDATE AD_Process_Para SET SeqNo=20,Updated=TO_DATE('2009-12-01 18:40:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53362
;

-- Dec 1, 2009 6:40:29 PM CET
-- FR [2906975] - RV_STORAGE_PER_PRODUCT
UPDATE AD_Process_Para SET SeqNo=30,Updated=TO_DATE('2009-12-01 18:40:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53363
;

