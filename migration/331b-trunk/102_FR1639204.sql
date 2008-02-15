-- Feb 14, 2008 11:57:23 PM COT
-- 1639204 - Delete Old Notes is deleting all notes
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2407,0,241,53130,11,'KeepLogDays',TO_DATE('2008-02-14 23:57:19','YYYY-MM-DD HH24:MI:SS'),100,'7','Number of days to keep the log entries','U',0,'Older Log entries may be deleted','Y','Y','N','N','Days to keep Log',20,TO_DATE('2008-02-14 23:57:19','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53130 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

INSERT INTO AD_Scheduler_Para (AD_Client_ID,AD_Org_ID,AD_Process_Para_ID,AD_Scheduler_ID,Created,CreatedBy,IsActive,ParameterDefault,Updated,UpdatedBy) VALUES (0,0,53130,100,TO_DATE('2008-02-14 23:57:39','YYYY-MM-DD HH24:MI:SS'),100,'Y','7',TO_DATE('2008-02-14 23:57:39','YYYY-MM-DD HH24:MI:SS'),100)
;

UPDATE AD_Column SET IsParent='Y', IsUpdateable='N',Updated=TO_DATE('2008-02-14 23:59:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=11396
;