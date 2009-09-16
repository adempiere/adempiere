-- Jul 17, 2009 1:44:55 PM COT
-- Fix dictionary payroll problems
UPDATE AD_Field SET SeqNo=70,Updated=TO_TIMESTAMP('2009-07-17 13:44:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56307
;

-- Jul 17, 2009 1:47:09 PM COT
-- Fix dictionary payroll problems
UPDATE AD_Tab SET Name='Concept Category',Updated=TO_TIMESTAMP('2009-07-17 13:47:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53119
;

-- Jul 17, 2009 1:47:09 PM COT
-- Fix dictionary payroll problems
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53119
;

