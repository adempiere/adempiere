-- 17/12/2008 11:50:06
-- Add date range to fact acct reset
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,DisplayLogic,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,263,0,176,53263,15,'DateAcct',TO_TIMESTAMP('2008-12-17 11:49:54','YYYY-MM-DD HH24:MI:SS'),100,'Optional account date range','@DeletePosting@=Y','D',0,'Only documents within this date range which are also in open periods will be reset.','Y','N','N','Y','Account Date',40,TO_TIMESTAMP('2008-12-17 11:49:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 17/12/2008 11:50:06
-- Add date range to fact acct reset
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53263 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

