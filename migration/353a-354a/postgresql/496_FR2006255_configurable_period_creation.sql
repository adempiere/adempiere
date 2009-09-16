-- 26/06/2008 16:29:22
-- Period creation improvements
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,574,0,100,53203,15,'StartDate',TO_TIMESTAMP('2008-06-26 16:29:19','YYYY-MM-DD HH24:MI:SS'),100,'D',0,'Y','Y','N','N','Start Date',10,TO_TIMESTAMP('2008-06-26 16:29:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 26/06/2008 16:29:22
-- Period creation improvements
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53203 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 26/06/2008 16:32:25
-- Period creation improvements
UPDATE AD_Process SET Description='Create 12 standard calendar periods.', Help='Creates 12 calendar month long standard periods from the specified start date.  If no start date is specified, 1st of Jan will be used.',Updated=TO_TIMESTAMP('2008-06-26 16:32:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=100
;

-- 26/06/2008 16:32:25
-- Period creation improvements
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=100
;

-- 26/06/2008 17:18:13
-- Period creation improvements
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,100,53205,10,'DateFormat',TO_TIMESTAMP('2008-06-26 17:18:12','YYYY-MM-DD HH24:MI:SS'),100,'MMM-yy','D',0,'Y','Y','N','N','Period Name Format',20,TO_TIMESTAMP('2008-06-26 17:18:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 26/06/2008 17:18:13
-- Period creation improvements
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53205 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 26/06/2008 17:19:53
-- Period creation improvements
UPDATE AD_Process SET Help='Creates 12 calendar month long standard periods from the specified start date.  If no start date is specified, 1st of Jan will be used.  The period name will be generated from the start date of each period using the java SimpleDateFormat pattern provided. ',Updated=TO_TIMESTAMP('2008-06-26 17:19:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=100
;

-- 26/06/2008 17:19:53
-- Period creation improvements
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=100
;

