-- Jan 24, 2008 2:25:48 PM CST
-- 1877902 - Implement  JSR 223: Scripting 
UPDATE AD_Column SET Callout='@script:beanshell:FillDescriptionWithName',Updated=TO_TIMESTAMP('2008-01-24 14:25:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54249
;

UPDATE AD_Ref_List SET Value='S', Name='JSR 223 Scripting APIs',Updated=TO_TIMESTAMP('2008-01-24 14:26:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53290
;

UPDATE AD_Ref_List SET Value='Q',Updated=TO_TIMESTAMP('2008-01-24 14:27:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53292
;

UPDATE AD_Ref_List SET Value='S', Name='JSR 223 Scripting APIs',Updated=TO_TIMESTAMP('2008-01-24 14:27:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53290
;

UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53290
;

DELETE FROM AD_Ref_List_Trl WHERE AD_Ref_List_ID=53298
;

DELETE FROM AD_Ref_List WHERE AD_Ref_List_ID=53298
;

UPDATE AD_Field SET DisplayLogic='@RuleType@=Q',Updated=TO_TIMESTAMP('2008-01-24 14:28:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54338
;

UPDATE AD_Rule SET RuleType='S',Updated=TO_TIMESTAMP('2008-01-24 14:28:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Rule_ID=50000
;

