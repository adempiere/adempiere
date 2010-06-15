-- 22-sep-2009 14:51:53 COT
-- FR2857076-User Element 1 and 2 completion
UPDATE AD_Process_Para SET SeqNo=100,Updated=TO_DATE('2009-09-22 14:51:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=547
;

-- 22-sep-2009 14:52:08 COT
UPDATE AD_Process_Para SET SeqNo=110,Updated=TO_DATE('2009-09-22 14:52:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=547
;

-- 22-sep-2009 14:52:11 COT
UPDATE AD_Process_Para SET SeqNo=120,Updated=TO_DATE('2009-09-22 14:52:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=685
;

-- 22-sep-2009 14:52:14 COT
UPDATE AD_Process_Para SET SeqNo=130,Updated=TO_DATE('2009-09-22 14:52:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53313
;

-- 22-sep-2009 14:52:41 COT
UPDATE AD_Process_Para SET SeqNo=110,Updated=TO_DATE('2009-09-22 14:52:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53122
;

-- 22-sep-2009 14:52:45 COT
UPDATE AD_Process_Para SET SeqNo=120,Updated=TO_DATE('2009-09-22 14:52:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53124
;

-- 22-sep-2009 14:53:25 COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53821,0,53063,53354,18,53299,52050,'PA_ReportCube_ID',TO_DATE('2009-09-22 14:53:24','YYYY-MM-DD HH24:MI:SS'),100,'@PA_ReportCube_ID@','Define reporting cube for pre-calculation of summary accounting data.','D',0,'Summary data will be generated for each period of the selected calendar, grouped by the selected dimensions..','Y','Y','N','N','Report Cube',130,TO_DATE('2009-09-22 14:53:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 22-sep-2009 14:53:25 COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53354 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 22-sep-2009 14:55:27 COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,DisplayLogic,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,613,0,53063,53355,18,134,'User1_ID',TO_DATE('2009-09-22 14:55:26','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #1','@$Element_U1@=Y','D',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','User List 1',90,TO_DATE('2009-09-22 14:55:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 22-sep-2009 14:55:27 COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53355 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 22-sep-2009 14:55:33 COT
UPDATE AD_Process_Para SET FieldLength=10,Updated=TO_DATE('2009-09-22 14:55:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53354
;

-- 22-sep-2009 14:56:16 COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,DisplayLogic,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,614,0,53063,53356,18,137,'User2_ID',TO_DATE('2009-09-22 14:56:15','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #2','@$Element_U2@=Y','D',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','User List 2',100,TO_DATE('2009-09-22 14:56:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 22-sep-2009 14:56:16 COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53356 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 22-sep-2009 14:56:53 COT
UPDATE AD_Process_Para SET DisplayLogic='@$Element_MC@=''Y''',Updated=TO_DATE('2009-09-22 14:56:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53121
;

-- 22-sep-2009 14:57:10 COT
UPDATE AD_Process_Para SET DisplayLogic='@$Element_AY@=''Y''',Updated=TO_DATE('2009-09-22 14:57:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53119
;

-- 22-sep-2009 14:57:17 COT
UPDATE AD_Process_Para SET DisplayLogic='@$Element_PJ@=''Y''',Updated=TO_DATE('2009-09-22 14:57:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53118
;

-- 22-sep-2009 14:58:03 COT
UPDATE AD_Process_Para SET DisplayLogic='@$Element_PJ@=''Y''',Updated=TO_DATE('2009-09-22 14:58:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=289
;

-- 22-sep-2009 14:58:09 COT
UPDATE AD_Process_Para SET DisplayLogic='@$Element_AY@=''Y''',Updated=TO_DATE('2009-09-22 14:58:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=290
;

-- 22-sep-2009 14:58:17 COT
UPDATE AD_Process_Para SET DisplayLogic='@$Element_MC@=''Y''',Updated=TO_DATE('2009-09-22 14:58:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=292
;

-- 22-sep-2009 14:58:59 COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,DisplayLogic,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,613,0,202,53357,18,134,'User1_ID',TO_DATE('2009-09-22 14:58:58','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #1','@$Element_U1@=Y','D',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','User List 1',90,TO_DATE('2009-09-22 14:58:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 22-sep-2009 14:58:59 COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53357 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 22-sep-2009 14:59:23 COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,DisplayLogic,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,614,0,202,53358,18,137,'User2_ID',TO_DATE('2009-09-22 14:59:22','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #2','@$Element_U2@=Y','D',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','User List 2',100,TO_DATE('2009-09-22 14:59:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 22-sep-2009 14:59:23 COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53358 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 22-sep-2009 14:59:47 COT
UPDATE AD_Process_Para SET DisplayLogic='@$Element_PJ@=''Y''',Updated=TO_DATE('2009-09-22 14:59:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=299
;

-- 22-sep-2009 14:59:52 COT
UPDATE AD_Process_Para SET DisplayLogic='@$Element_AY@=''Y''',Updated=TO_DATE('2009-09-22 14:59:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=457
;

-- 22-sep-2009 15:00:01 COT
UPDATE AD_Process_Para SET DisplayLogic='@$Element_MC@=''Y''',Updated=TO_DATE('2009-09-22 15:00:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=458
;

-- 22-sep-2009 15:00:13 COT
UPDATE AD_Process_Para SET SeqNo=130,Updated=TO_DATE('2009-09-22 15:00:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=687
;

-- 22-sep-2009 15:01:05 COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,DisplayLogic,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,613,0,204,53359,18,134,'User1_ID',TO_DATE('2009-09-22 15:01:03','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #1','@$Element_U1@=Y','D',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','User List 1',110,TO_DATE('2009-09-22 15:01:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 22-sep-2009 15:01:05 COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53359 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 22-sep-2009 15:01:26 COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,DisplayLogic,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,614,0,204,53360,18,137,'User2_ID',TO_DATE('2009-09-22 15:01:25','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #2','@$Element_U2@=Y','D',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','N','N','User List 2',120,TO_DATE('2009-09-22 15:01:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 22-sep-2009 15:01:26 COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53360 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

