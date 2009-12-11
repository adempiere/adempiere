-- Dec 10, 2009 8:24:38 PM COT
-- 2793242_Accounting Fact Summary
UPDATE AD_Process SET Name='Recalculate Cube',Updated=TO_DATE('2009-12-10 20:24:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53166
;

-- Dec 10, 2009 8:24:38 PM COT
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53166
;

-- Dec 10, 2009 8:24:39 PM COT
UPDATE AD_Menu SET Description='Recalculate summary facts based on report cube definitions.', IsActive='Y', Name='Recalculate Cube',Updated=TO_DATE('2009-12-10 20:24:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53215
;

-- Dec 10, 2009 8:24:39 PM COT
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53215
;

-- Dec 10, 2009 8:26:26 PM COT
UPDATE AD_Field SET DisplayLogic='@$Element_BP@=Y',Updated=TO_DATE('2009-12-10 20:26:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57014
;

-- Dec 10, 2009 8:26:37 PM COT
UPDATE AD_Field SET DisplayLogic='@$Element_PR@=Y',Updated=TO_DATE('2009-12-10 20:26:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57025
;

-- Dec 10, 2009 8:26:42 PM COT
UPDATE AD_Field SET DisplayLogic='@$Element_PJ@=Y',Updated=TO_DATE('2009-12-10 20:26:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57026
;

-- Dec 10, 2009 8:26:54 PM COT
UPDATE AD_Field SET DisplayLogic='@$Element_SR@=Y',Updated=TO_DATE('2009-12-10 20:26:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57030
;

-- Dec 10, 2009 8:27:11 PM COT
UPDATE AD_Field SET DisplayLogic='@$Element_MC@=Y',Updated=TO_DATE('2009-12-10 20:27:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57016
;

-- Dec 10, 2009 8:27:25 PM COT
UPDATE AD_Field SET DisplayLogic='@$Element_TO@=Y',Updated=TO_DATE('2009-12-10 20:27:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57023
;

-- Dec 10, 2009 8:27:42 PM COT
UPDATE AD_Field SET DisplayLogic='@$Element_LF@=Y',Updated=TO_DATE('2009-12-10 20:27:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57020
;

-- Dec 10, 2009 8:27:52 PM COT
UPDATE AD_Field SET DisplayLogic='@$Element_LT@=Y',Updated=TO_DATE('2009-12-10 20:27:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57021
;

-- Dec 10, 2009 8:28:00 PM COT
UPDATE AD_Field SET DisplayLogic='@$Element_AC@=Y ',Updated=TO_DATE('2009-12-10 20:28:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57013
;

-- Dec 10, 2009 8:28:10 PM COT
UPDATE AD_Field SET DisplayLogic='@$Element_U1@=Y',Updated=TO_DATE('2009-12-10 20:28:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57032
;

-- Dec 10, 2009 8:28:21 PM COT
UPDATE AD_Field SET DisplayLogic='@$Element_U2@=Y',Updated=TO_DATE('2009-12-10 20:28:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57033
;

-- Dec 10, 2009 8:28:24 PM COT
UPDATE AD_Field SET DisplayLogic='@$Element_X1@=Y',Updated=TO_DATE('2009-12-10 20:28:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57386
;

-- Dec 10, 2009 8:28:31 PM COT
UPDATE AD_Field SET DisplayLogic='@$Element_X2@=Y',Updated=TO_DATE('2009-12-10 20:28:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57387
;

-- Dec 10, 2009 8:28:45 PM COT
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_DATE('2009-12-10 20:28:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57027
;

-- Dec 10, 2009 8:29:11 PM COT
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_DATE('2009-12-10 20:29:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57031
;

-- Dec 10, 2009 8:29:21 PM COT
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_DATE('2009-12-10 20:29:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57019
;

