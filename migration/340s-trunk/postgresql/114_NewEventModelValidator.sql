-- Mar 5, 2008 3:39:11 AM CST
-- Default comment for updating dictionary
UPDATE AD_SysConfig SET Value='New Event Model Validator',Updated=TO_TIMESTAMP('2008-03-05 03:39:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_SysConfig_ID=50003
;

-- Mar 5, 2008 3:39:42 AM CST
-- New Event Model Validator
UPDATE AD_SysConfig SET Value='user',Updated=TO_TIMESTAMP('2008-03-05 03:39:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_SysConfig_ID=50001
;

-- Mar 5, 2008 3:39:46 AM CST
-- New Event Model Validator
UPDATE AD_SysConfig SET Value='password',Updated=TO_TIMESTAMP('2008-03-05 03:39:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_SysConfig_ID=50002
;

-- Mar 5, 2008 3:39:53 AM CST
-- New Event Model Validator
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53337,53237,TO_TIMESTAMP('2008-03-05 03:39:52','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Table After New Replication',TO_TIMESTAMP('2008-03-05 03:39:52','YYYY-MM-DD HH24:MI:SS'),0,'TANR')
;

-- Mar 5, 2008 3:39:53 AM CST
-- New Event Model Validator
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53337 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Mar 5, 2008 3:40:44 AM CST
-- New Event Model Validator
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53338,53237,TO_TIMESTAMP('2008-03-05 03:40:43','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Table After Change Replication',TO_TIMESTAMP('2008-03-05 03:40:43','YYYY-MM-DD HH24:MI:SS'),0,'TACR')
;

-- Mar 5, 2008 3:40:44 AM CST
-- New Event Model Validator
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53338 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Mar 5, 2008 3:41:37 AM CST
-- New Event Model Validator
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53339,53237,TO_TIMESTAMP('2008-03-05 03:41:36','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Table Before Delete Replication',TO_TIMESTAMP('2008-03-05 03:41:36','YYYY-MM-DD HH24:MI:SS'),0,'TBDR')
;

-- Mar 5, 2008 3:41:37 AM CST
-- New Event Model Validator
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53339 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

