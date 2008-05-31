-- May 26, 2008 10:33:25 AM CEST
-- [1972559] Addition to Order Batch Process - kthiemann@adempiere.org
INSERT INTO AD_Process_Para (AD_Org_ID,AD_Reference_Value_ID,UpdatedBy,Updated,SeqNo,Name,IsRange,IsMandatory,IsCentrallyMaintained,IsActive,Help,FieldLength,EntityType,Description,CreatedBy,Created,ColumnName,AD_Reference_ID,AD_Process_Para_ID,AD_Process_ID,AD_Element_ID,AD_Client_ID) VALUES (0,319,100,TO_TIMESTAMP('2008-05-26 10:33:14','YYYY-MM-DD HH24:MI:SS'),30,'Delivered','N','N','Y','Y',NULL,1,'D',NULL,100,TO_TIMESTAMP('2008-05-26 10:33:14','YYYY-MM-DD HH24:MI:SS'),'IsDelivered',17,53153,315,367,0)
;

-- May 26, 2008 10:33:25 AM CEST
-- [1972559] Addition to Order Batch Process - kthiemann@adempiere.org
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Name,Help,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Name,t.Help,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53153 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- May 26, 2008 10:34:11 AM CEST
-- [1972559] Addition to Order Batch Process - kthiemann@adempiere.org
UPDATE AD_Process_Para SET Description='The order is delivered.',Updated=TO_TIMESTAMP('2008-05-26 10:34:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53153
;

-- May 26, 2008 10:34:11 AM CEST
-- [1972559] Addition to Order Batch Process - kthiemann@adempiere.org
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53153
;

-- May 26, 2008 10:34:57 AM CEST
-- [1972559] Addition to Order Batch Process - kthiemann@adempiere.org
INSERT INTO AD_Process_Para (AD_Org_ID,AD_Reference_Value_ID,UpdatedBy,Updated,SeqNo,Name,IsRange,IsMandatory,IsCentrallyMaintained,IsActive,FieldLength,EntityType,Description,CreatedBy,Created,ColumnName,AD_Reference_ID,AD_Process_Para_ID,AD_Process_ID,AD_Element_ID,AD_Client_ID) VALUES (0,319,100,TO_TIMESTAMP('2008-05-26 10:34:57','YYYY-MM-DD HH24:MI:SS'),30,'Invoiced','N','N','Y','Y',1,'D','The order is invoiced.',100,TO_TIMESTAMP('2008-05-26 10:34:57','YYYY-MM-DD HH24:MI:SS'),'IsInvoiced',17,53154,315,387,0)
;

-- May 26, 2008 10:34:58 AM CEST
-- [1972559] Addition to Order Batch Process - kthiemann@adempiere.org
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Name,Help,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Name,t.Help,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53154 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

