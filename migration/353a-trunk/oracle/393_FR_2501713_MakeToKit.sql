-- Jan 12, 2009 12:08:51 AM ECT
-- New BOM Type for KIT
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53450,347,TO_DATE('2009-01-12 00:08:45','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','Make-To-Kit',TO_DATE('2009-01-12 00:08:45','YYYY-MM-DD HH24:MI:SS'),100,'K')
;

-- Jan 12, 2009 12:08:51 AM ECT
-- New BOM Type for KIT
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53450 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jan 12, 2009 12:09:09 AM ECT
-- New BOM Type for KIT
UPDATE AD_Ref_List SET IsActive='N',Updated=TO_DATE('2009-01-12 00:09:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=746
;

-- Jan 12, 2009 12:09:12 AM ECT
-- New BOM Type for KIT
UPDATE AD_Ref_List SET IsActive='N',Updated=TO_DATE('2009-01-12 00:09:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=747
;

-- Jan 12, 2009 12:09:17 AM ECT
-- New BOM Type for KIT
UPDATE AD_Ref_List SET IsActive='N',Updated=TO_DATE('2009-01-12 00:09:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=744
;

-- Jan 12, 2009 12:09:21 AM ECT
-- New BOM Type for KIT
UPDATE AD_Ref_List SET IsActive='N',Updated=TO_DATE('2009-01-12 00:09:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=745
;

-- Jan 12, 2009 12:35:52 AM ECT
-- New BOM Type for KIT
UPDATE AD_Ref_List SET Description='Create a Manufacturing Order, Receipt the finish product and issue the Components automaticaly ',Updated=TO_DATE('2009-01-12 00:35:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53450
;

-- Jan 12, 2009 12:35:52 AM ECT
-- New BOM Type for KIT
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53450
;
