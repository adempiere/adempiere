-- Feb 24, 2010 4:03:13 PM EST
-- Payment selection due date parameter
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,DisplayLogic,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2000,0,156,53404,15,'DueDate',TO_TIMESTAMP('2010-02-24 16:03:03','YYYY-MM-DD HH24:MI:SS'),100,'Date when the payment is due','@OnlyDue@=Y','D',7,'Date when the payment is due without deductions or discount','Y','Y','N','N','Due Date',45,TO_TIMESTAMP('2010-02-24 16:03:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 24, 2010 4:03:13 PM EST
-- Payment selection due date parameter
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53404 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

