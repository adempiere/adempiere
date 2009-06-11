-- Jun 11, 2009 12:06:07 PM ECT
-- Manufacturing By Product
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53481,53225,TO_DATE('2009-06-11 12:06:05','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Co-Product',TO_DATE('2009-06-11 12:06:05','YYYY-MM-DD HH24:MI:SS'),0,'CP')
;

-- Jun 11, 2009 12:06:07 PM ECT
-- Manufacturing By Product
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53481 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jun 11, 2009 12:06:16 PM ECT
-- Manufacturing By Product
UPDATE AD_Ref_List SET Name='By-Product',Updated=TO_DATE('2009-06-11 12:06:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53257
;

-- Jun 11, 2009 12:06:16 PM ECT
-- Manufacturing By Product
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53257
;

