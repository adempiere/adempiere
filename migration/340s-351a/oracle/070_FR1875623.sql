-- Jan 19, 2008 10:44:04 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
UPDATE AD_Ref_List SET Name='Distribution Order',Updated=TO_DATE('2008-01-19 22:44:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53241
;

-- Jan 19, 2008 10:44:04 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53241
;

-- Jan 19, 2008 10:45:02 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
INSERT INTO AD_Ref_List (Created,CreatedBy,Updated,UpdatedBy,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Value,Name,IsActive,AD_Client_ID,EntityType) VALUES (TO_DATE('2008-01-19 22:45:00','YYYY-MM-DD HH24:MI:SS'),0,TO_DATE('2008-01-19 22:45:00','YYYY-MM-DD HH24:MI:SS'),0,0,53287,329,'DOO','Distribution Order','Y',0,'EE01')
;

-- Jan 19, 2008 10:45:02 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53287 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

