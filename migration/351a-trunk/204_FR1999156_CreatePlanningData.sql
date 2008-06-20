-- Jun 20, 2008 3:24:52 PM CDT
-- Fixed Libero Issue
UPDATE AD_Column SET AD_Val_Rule_ID=52002,Updated=TO_DATE('2008-06-20 15:24:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53400
;

-- Jun 20, 2008 3:27:23 PM CDT
-- Fixed Libero Issue
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2008-06-20 15:27:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53400
;

-- Jun 20, 2008 3:30:05 PM CDT
-- Fixed Libero Issue
UPDATE AD_Process_Para SET AD_Element_ID=1777, AD_Val_Rule_ID=52002,Updated=TO_DATE('2008-06-20 15:30:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53035
;

-- Jun 20, 2008 3:32:17 PM CDT
-- Fixed Libero Issue
UPDATE AD_Process_Para SET AD_Val_Rule_ID=52003,Updated=TO_DATE('2008-06-20 15:32:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53036
;

-- Jun 20, 2008 3:35:42 PM CDT
-- Fixed Libero Issue
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53340,0,53010,53183,30,'DD_NetworkDistribution_ID',TO_DATE('2008-06-20 15:35:33','YYYY-MM-DD HH24:MI:SS'),0,'Network Distribution','EE01',22,NULL,'Y','Y','N','N','Network Distribution',75,TO_DATE('2008-06-20 15:35:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 20, 2008 3:35:42 PM CDT
-- Fixed Libero Issue
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53183 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 20, 2008 3:37:05 PM CDT
-- Fixed Libero Issue
UPDATE AD_Column SET AD_Reference_ID=30,Updated=TO_DATE('2008-06-20 15:37:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53660
;

-- Jun 20, 2008 3:38:45 PM CDT
-- Fixed Libero Issue
UPDATE AD_Process_Para SET AD_Reference_ID=19,Updated=TO_DATE('2008-06-20 15:38:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53183
;

