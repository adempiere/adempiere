-- Mar 2, 2009 4:54:03 PM COT
-- Bug 2655587 Multi-org not supported in Aging
UPDATE AD_Process_Para SET SeqNo=20,Updated=TO_TIMESTAMP('2009-03-02 16:54:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53144
;

-- Mar 2, 2009 4:54:09 PM COT
UPDATE AD_Process_Para SET SeqNo=30,Updated=TO_TIMESTAMP('2009-03-02 16:54:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=396
;

-- Mar 2, 2009 4:54:14 PM COT
UPDATE AD_Process_Para SET SeqNo=40,Updated=TO_TIMESTAMP('2009-03-02 16:54:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=401
;

-- Mar 2, 2009 4:54:17 PM COT
UPDATE AD_Process_Para SET SeqNo=60,Updated=TO_TIMESTAMP('2009-03-02 16:54:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=397
;

-- Mar 2, 2009 4:54:22 PM COT
UPDATE AD_Process_Para SET SeqNo=70,Updated=TO_TIMESTAMP('2009-03-02 16:54:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=399
;

-- Mar 2, 2009 4:54:24 PM COT
UPDATE AD_Process_Para SET SeqNo=80,Updated=TO_TIMESTAMP('2009-03-02 16:54:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=400
;

-- Mar 2, 2009 7:34:42 PM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,113,0,238,53297,19,'AD_Org_ID',TO_TIMESTAMP('2009-03-02 19:34:41','YYYY-MM-DD HH24:MI:SS'),100,'-1','Organizational entity within client','D',0,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','Organization',60,TO_TIMESTAMP('2009-03-02 19:34:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Mar 2, 2009 7:34:42 PM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53297 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Mar 2, 2009 8:15:22 PM COT
UPDATE AD_Process_Para SET AD_Val_Rule_ID=130,Updated=TO_TIMESTAMP('2009-03-02 20:15:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53297
;

