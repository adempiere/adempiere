ALTER TABLE HR_Concept RENAME COLUMN IsReadWrite TO IsSaveinHistoric;
ALTER TABLE HR_Concept RENAME COLUMN IsRegistered TO IsManual;
ALTER TABLE HR_Movement RENAME COLUMN IsRegistered TO IsManual;

-- Oct 25, 2010 11:25:10 AM CDT
-- Payroll
UPDATE AD_Column SET AD_Element_ID=1474, ColumnName='IsManual', DefaultValue='''N''', Description='This is a manual process', Help='The Manual check box indicates if the process will done manually.', Name='Manual',Updated=TO_TIMESTAMP('2010-10-25 11:25:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55031
;

-- Oct 25, 2010 11:25:10 AM CDT
-- Payroll
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=55031
;

-- Oct 25, 2010 11:25:10 AM CDT
-- Payroll
UPDATE AD_Field SET Name='Manual', Description='This is a manual process', Help='The Manual check box indicates if the process will done manually.' WHERE AD_Column_ID=55031 AND IsCentrallyMaintained='Y'
;

-- Oct 25, 2010 11:26:06 AM CDT
-- Payroll
UPDATE AD_Column SET AD_Element_ID=1474, ColumnName='IsManual', DefaultValue='''N''', Description='This is a manual process', Help='The Manual check box indicates if the process will done manually.', Name='Manual',Updated=TO_TIMESTAMP('2010-10-25 11:26:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54829
;

-- Oct 25, 2010 11:26:06 AM CDT
-- Payroll
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=54829
;

-- Oct 25, 2010 11:26:06 AM CDT
-- Payroll
UPDATE AD_Field SET Name='Manual', Description='This is a manual process', Help='The Manual check box indicates if the process will done manually.' WHERE AD_Column_ID=54829 AND IsCentrallyMaintained='Y'
;

-- Oct 25, 2010 11:27:02 AM CDT
-- Payroll
UPDATE AD_Column SET AD_Element_ID=53684, ColumnName='IsSaveInHistoric', DefaultValue='''Y''', Description=NULL, Help=NULL, Name='Save In Historic',Updated=TO_TIMESTAMP('2010-10-25 11:27:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54815
;

-- Oct 25, 2010 11:27:02 AM CDT
-- Payroll
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=54815
;

-- Oct 25, 2010 11:27:02 AM CDT
-- Payroll
UPDATE AD_Field SET Name='Save In Historic', Description=NULL, Help=NULL WHERE AD_Column_ID=54815 AND IsCentrallyMaintained='Y'
;

-- Oct 25, 2010 11:27:20 AM CDT
-- Payroll
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_TIMESTAMP('2010-10-25 11:27:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54815
;

