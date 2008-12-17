-- Jul 4, 2008 6:11:43 PM EST
-- Default comment for updating dictionary
UPDATE AD_Process SET Help='This process copies BOM Lines from the Selected BOM to the Current BOM. The BOM being copied to must have any existing BOM Lines',
Updated=TO_DATE('2008-07-04 18:11:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53004
;

-- Jul 5, 2008 4:51:47 PM EST
-- Default comment for updating dictionary
UPDATE AD_Field SET IsCentrallyMaintained='N', Name='Copy BOM Lines From', Description='Copy BOM Lines from an exising BOM', Help='Copy BOM Lines from an exising BOM. The BOM being copied to, must not have any existing BOM Lines.',Updated=TO_DATE('2008-07-05 16:51:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=53480
;

-- Jul 5, 2008 4:51:47 PM EST
-- Default comment for updating dictionary
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=53480
;
