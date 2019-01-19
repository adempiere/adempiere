-- Jan 20, 2016 9:41:10 PM VET
-- POS Contribution
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,214,54626,TO_TIMESTAMP('2016-01-20 21:41:08','YYYY-MM-DD HH24:MI:SS'),100,'C','Y','Credit Memo',TO_TIMESTAMP('2016-01-20 21:41:08','YYYY-MM-DD HH24:MI:SS'),100,'M')
;

-- Jan 20, 2016 9:41:10 PM VET
-- POS Contribution
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=54626 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Jan 20, 2016 9:41:32 PM VET
-- POS Contribution
UPDATE AD_Ref_List_Trl SET Name='Nota de crédito',Updated=TO_TIMESTAMP('2016-01-20 21:41:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=54626 AND AD_Language='es_MX'
;

-- Jan 20, 2016 9:42:26 PM VET
-- POS Contribution
UPDATE AD_Val_Rule SET Code='AD_Ref_List.Value <> ''X'' AND AD_Ref_List.Value <> ''M''', Name='Tender Type - not Cash - not Credit Memo',Updated=TO_TIMESTAMP('2016-01-20 21:42:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52078
;

-- Jan 20, 2016 9:45:17 PM VET
-- POS Contribution
UPDATE AD_Val_Rule SET Code='AD_Ref_List.Value <> ''X''',Updated=TO_TIMESTAMP('2016-01-20 21:45:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52078
;

-- Jan 20, 2016 9:45:33 PM VET
-- POS Contribution
UPDATE AD_Val_Rule SET Code='AD_Ref_List.Value <> ''X'' AND AD_Ref_List.Value <> ''M''',Updated=TO_TIMESTAMP('2016-01-20 21:45:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52078
;

