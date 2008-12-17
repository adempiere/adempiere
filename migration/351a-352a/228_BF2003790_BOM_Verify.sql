-- Jul 7, 2008 1:24:00 PM EST
-- Default comment for updating dictionary
UPDATE AD_Process SET Classname='org.eevolution.process.PP_Product_BOM_Check', Value='PP_Product_BOM',Updated=TO_DATE('2008-07-07 13:24:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=136
;

-- Jul 7, 2008 1:26:22 PM EST
-- Default comment for updating dictionary
UPDATE AD_Process SET Description='Verify BOM Structure and Update Low Level',Updated=TO_DATE('2008-07-07 13:26:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=136
;

-- Jul 7, 2008 1:26:22 PM EST
-- Default comment for updating dictionary
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=136
;

-- Jul 7, 2008 1:28:22 PM EST
-- Default comment for updating dictionary
UPDATE AD_Field SET Description='Verify BOM Structure and Update Low Level', DisplayLogic='@IsSummary@=''N'' & @IsBOM@=''Y''',Updated=TO_DATE('2008-07-07 13:28:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=3747
;

-- Jul 7, 2008 1:28:22 PM EST
-- Default comment for updating dictionary
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=3747
;

