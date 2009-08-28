-- Aug 28, 2009 2:54:51 PM EEST
-- BF [2833929] - Organization Type help text message
UPDATE AD_Element SET Description='Organization Type',Updated=TO_DATE('2009-08-28 14:54:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2355
;

-- Aug 28, 2009 2:54:51 PM EEST
-- BF [2833929] - Organization Type help text message
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2355
;

-- Aug 28, 2009 2:54:51 PM EEST
-- BF [2833929] - Organization Type help text message
UPDATE AD_Column SET ColumnName='AD_OrgType_ID', Name='Organization Type', Description='Organization Type', Help='Organization Type allows you to categorize your organizations for reporting purposes' WHERE AD_Element_ID=2355
;

-- Aug 28, 2009 2:54:51 PM EEST
-- BF [2833929] - Organization Type help text message
UPDATE AD_Process_Para SET ColumnName='AD_OrgType_ID', Name='Organization Type', Description='Organization Type', Help='Organization Type allows you to categorize your organizations for reporting purposes', AD_Element_ID=2355 WHERE UPPER(ColumnName)='AD_ORGTYPE_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 28, 2009 2:54:51 PM EEST
-- BF [2833929] - Organization Type help text message
UPDATE AD_Process_Para SET ColumnName='AD_OrgType_ID', Name='Organization Type', Description='Organization Type', Help='Organization Type allows you to categorize your organizations for reporting purposes' WHERE AD_Element_ID=2355 AND IsCentrallyMaintained='Y'
;

-- Aug 28, 2009 2:54:51 PM EEST
-- BF [2833929] - Organization Type help text message
UPDATE AD_Field SET Name='Organization Type', Description='Organization Type', Help='Organization Type allows you to categorize your organizations for reporting purposes' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=2355) AND IsCentrallyMaintained='Y'
;

