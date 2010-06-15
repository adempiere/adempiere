-- Aug 14, 2009 1:27:08 PM ECT
-- Improve the Application Dictionary
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53503,126,TO_TIMESTAMP('2009-08-14 13:26:35','YYYY-MM-DD HH24:MI:SS'),100,'EE05','Y','Broadcast',TO_TIMESTAMP('2009-08-14 13:26:35','YYYY-MM-DD HH24:MI:SS'),100,'B')
;

-- Aug 14, 2009 1:27:09 PM ECT
-- Improve the Application Dictionary
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53503 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Aug 14, 2009 1:27:40 PM ECT
-- Improve the Application Dictionary
UPDATE AD_Ref_List SET Description='Broadcast ->>',Updated=TO_TIMESTAMP('2009-08-14 13:27:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53503
;

-- Aug 14, 2009 1:27:40 PM ECT
-- Improve the Application Dictionary
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53503
;

-- Aug 14, 2009 1:38:32 PM ECT
-- Improve the Application Dictionary
UPDATE AD_Ref_List SET Description='Broadcast <<->>',Updated=TO_TIMESTAMP('2009-08-14 13:38:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53503
;

-- Aug 14, 2009 1:38:32 PM ECT
-- Improve the Application Dictionary
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53503
;

