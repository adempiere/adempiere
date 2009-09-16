-- Aug 1, 2009 3:01:46 PM EEST
-- FR2830806 - IsAllowLogging human readable
UPDATE AD_Element SET Name='Allow Logging', PrintName='Allow Logging',Updated=TO_TIMESTAMP('2009-08-01 15:01:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53669
;

-- Aug 1, 2009 3:01:46 PM EEST
-- FR2830806 - IsAllowLogging human readable
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53669
;

-- Aug 1, 2009 3:01:46 PM EEST
-- FR2830806 - IsAllowLogging human readable
UPDATE AD_Column SET ColumnName='IsAllowLogging', Name='Allow Logging', Description='Determine if a column must be recorded into the change log', Help=NULL WHERE AD_Element_ID=53669
;

-- Aug 1, 2009 3:01:46 PM EEST
-- FR2830806 - IsAllowLogging human readable
UPDATE AD_Process_Para SET ColumnName='IsAllowLogging', Name='Allow Logging', Description='Determine if a column must be recorded into the change log', Help=NULL, AD_Element_ID=53669 WHERE UPPER(ColumnName)='ISALLOWLOGGING' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 1, 2009 3:01:46 PM EEST
-- FR2830806 - IsAllowLogging human readable
UPDATE AD_Process_Para SET ColumnName='IsAllowLogging', Name='Allow Logging', Description='Determine if a column must be recorded into the change log', Help=NULL WHERE AD_Element_ID=53669 AND IsCentrallyMaintained='Y'
;

-- Aug 1, 2009 3:01:46 PM EEST
-- FR2830806 - IsAllowLogging human readable
UPDATE AD_Field SET Name='Allow Logging', Description='Determine if a column must be recorded into the change log', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53669) AND IsCentrallyMaintained='Y'
;

-- Aug 1, 2009 3:01:46 PM EEST
-- FR2830806 - IsAllowLogging human readable
UPDATE AD_PrintFormatItem SET PrintName='Allow Logging', Name='Allow Logging' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53669)
;

