-- Mar 29, 2010 3:54:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET ColumnName='AD_Backup_ID', Name='AD_Backup_ID', PrintName='AD_Backup_ID',Updated=TO_TIMESTAMP('2010-03-29 15:54:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50016
;

-- Mar 29, 2010 3:54:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50016
;

-- Mar 29, 2010 3:54:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Backup_ID', Name='AD_Backup_ID', Description=NULL, Help=NULL WHERE AD_Element_ID=50016
;

-- Mar 29, 2010 3:54:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Backup_ID', Name='AD_Backup_ID', Description=NULL, Help=NULL, AD_Element_ID=50016 WHERE UPPER(ColumnName)='AD_BACKUP_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 3:54:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Backup_ID', Name='AD_Backup_ID', Description=NULL, Help=NULL WHERE AD_Element_ID=50016 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 3:54:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='AD_Backup_ID', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50016) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 3:54:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='AD_Backup_ID', Name='AD_Backup_ID' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50016)
;

-- Mar 29, 2010 3:58:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset', PrintName='Asset',Updated=TO_TIMESTAMP('2010-03-29 15:58:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53644
;

-- Mar 29, 2010 3:58:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53644
;

-- Mar 29, 2010 3:58:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='I_Asset_ID', Name='Asset', Description=NULL, Help=NULL WHERE AD_Element_ID=53644
;

-- Mar 29, 2010 3:58:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='I_Asset_ID', Name='Asset', Description=NULL, Help=NULL, AD_Element_ID=53644 WHERE UPPER(ColumnName)='I_ASSET_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 3:58:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='I_Asset_ID', Name='Asset', Description=NULL, Help=NULL WHERE AD_Element_ID=53644 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 3:58:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53644) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 3:58:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset', Name='Asset' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53644)
;

-- Mar 29, 2010 3:58:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='FA Journal', PrintName='FA Journal',Updated=TO_TIMESTAMP('2010-03-29 15:58:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53502
;

-- Mar 29, 2010 3:58:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53502
;

-- Mar 29, 2010 3:58:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='I_FAJournal_ID', Name='FA Journal', Description=NULL, Help=NULL WHERE AD_Element_ID=53502
;

-- Mar 29, 2010 3:58:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='I_FAJournal_ID', Name='FA Journal', Description=NULL, Help=NULL, AD_Element_ID=53502 WHERE UPPER(ColumnName)='I_FAJOURNAL_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 3:58:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='I_FAJournal_ID', Name='FA Journal', Description=NULL, Help=NULL WHERE AD_Element_ID=53502 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 3:58:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='FA Journal', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53502) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 3:58:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='FA Journal', Name='FA Journal' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53502)
;

-- Mar 29, 2010 4:02:47 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Account Number', PrintName='Account Number',Updated=TO_TIMESTAMP('2010-03-29 16:02:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53486
;

-- Mar 29, 2010 4:02:47 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53486
;

-- Mar 29, 2010 4:02:47 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Account_Number', Name='Account Number', Description=NULL, Help=NULL WHERE AD_Element_ID=53486
;

-- Mar 29, 2010 4:02:47 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Account_Number', Name='Account Number', Description=NULL, Help=NULL, AD_Element_ID=53486 WHERE UPPER(ColumnName)='A_ACCOUNT_NUMBER' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:02:47 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Account_Number', Name='Account Number', Description=NULL, Help=NULL WHERE AD_Element_ID=53486 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:02:47 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Account Number', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53486) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:02:47 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Account Number', Name='Account Number' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53486)
;

-- Mar 29, 2010 4:03:14 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Accumulated Depreciation', PrintName='Accumulated Depreciation',Updated=TO_TIMESTAMP('2010-03-29 16:03:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53488
;

-- Mar 29, 2010 4:03:14 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53488
;

-- Mar 29, 2010 4:03:14 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Accumulated_Depr', Name='Accumulated Depreciation', Description=NULL, Help=NULL WHERE AD_Element_ID=53488
;

-- Mar 29, 2010 4:03:14 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Accumulated_Depr', Name='Accumulated Depreciation', Description=NULL, Help=NULL, AD_Element_ID=53488 WHERE UPPER(ColumnName)='A_ACCUMULATED_DEPR' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:03:14 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Accumulated_Depr', Name='Accumulated Depreciation', Description=NULL, Help=NULL WHERE AD_Element_ID=53488 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:03:14 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Accumulated Depreciation', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53488) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:03:14 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Accumulated Depreciation', Name='Accumulated Depreciation' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53488)
;

-- Mar 29, 2010 4:03:27 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Amount Split', PrintName='Amount Split',Updated=TO_TIMESTAMP('2010-03-29 16:03:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53518
;

-- Mar 29, 2010 4:03:27 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53518
;

-- Mar 29, 2010 4:03:27 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Amount_Split', Name='Amount Split', Description=NULL, Help=NULL WHERE AD_Element_ID=53518
;

-- Mar 29, 2010 4:03:27 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Amount_Split', Name='Amount Split', Description=NULL, Help=NULL, AD_Element_ID=53518 WHERE UPPER(ColumnName)='A_AMOUNT_SPLIT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:03:27 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Amount_Split', Name='Amount Split', Description=NULL, Help=NULL WHERE AD_Element_ID=53518 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:03:27 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Amount Split', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53518) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:03:27 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Amount Split', Name='Amount Split' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53518)
;

-- Mar 29, 2010 4:03:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Acct.', PrintName='Asset Acct.',Updated=TO_TIMESTAMP('2010-03-29 16:03:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53519
;

-- Mar 29, 2010 4:03:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53519
;

-- Mar 29, 2010 4:03:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Acct_ID', Name='Asset Acct.', Description=NULL, Help=NULL WHERE AD_Element_ID=53519
;

-- Mar 29, 2010 4:03:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Acct_ID', Name='Asset Acct.', Description=NULL, Help=NULL, AD_Element_ID=53519 WHERE UPPER(ColumnName)='A_ASSET_ACCT_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:03:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Acct_ID', Name='Asset Acct.', Description=NULL, Help=NULL WHERE AD_Element_ID=53519 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:03:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Acct.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53519) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:03:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Acct.', Name='Asset Acct.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53519)
;

-- Mar 29, 2010 4:04:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Addition', PrintName='Asset Addition',Updated=TO_TIMESTAMP('2010-03-29 16:04:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53612
;

-- Mar 29, 2010 4:04:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53612
;

-- Mar 29, 2010 4:04:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Addition_ID', Name='Asset Addition', Description=NULL, Help=NULL WHERE AD_Element_ID=53612
;

-- Mar 29, 2010 4:04:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Addition_ID', Name='Asset Addition', Description=NULL, Help=NULL, AD_Element_ID=53612 WHERE UPPER(ColumnName)='A_ASSET_ADDITION_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:04:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Addition_ID', Name='Asset Addition', Description=NULL, Help=NULL WHERE AD_Element_ID=53612 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:04:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Addition', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53612) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:04:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Addition', Name='Asset Addition' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53612)
;

-- Mar 29, 2010 4:04:35 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Change', PrintName='Asset Change',Updated=TO_TIMESTAMP('2010-03-29 16:04:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53603
;

-- Mar 29, 2010 4:04:35 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53603
;

-- Mar 29, 2010 4:04:35 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Change_ID', Name='Asset Change', Description=NULL, Help=NULL WHERE AD_Element_ID=53603
;

-- Mar 29, 2010 4:04:35 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Change_ID', Name='Asset Change', Description=NULL, Help=NULL, AD_Element_ID=53603 WHERE UPPER(ColumnName)='A_ASSET_CHANGE_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:04:35 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Change_ID', Name='Asset Change', Description=NULL, Help=NULL WHERE AD_Element_ID=53603 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:04:35 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Change', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53603) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:04:35 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Change', Name='Asset Change' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53603)
;

-- Mar 29, 2010 4:04:45 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Cost', PrintName='Asset Cost',Updated=TO_TIMESTAMP('2010-03-29 16:04:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53489
;

-- Mar 29, 2010 4:04:45 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53489
;

-- Mar 29, 2010 4:04:45 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Cost', Name='Asset Cost', Description=NULL, Help=NULL WHERE AD_Element_ID=53489
;

-- Mar 29, 2010 4:04:45 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Cost', Name='Asset Cost', Description=NULL, Help=NULL, AD_Element_ID=53489 WHERE UPPER(ColumnName)='A_ASSET_COST' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:04:45 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Cost', Name='Asset Cost', Description=NULL, Help=NULL WHERE AD_Element_ID=53489 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:04:45 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Cost', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53489) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:04:45 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Cost', Name='Asset Cost' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53489)
;

-- Mar 29, 2010 4:05:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Creation Date', PrintName='Asset Creation Date',Updated=TO_TIMESTAMP('2010-03-29 16:05:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53526
;

-- Mar 29, 2010 4:05:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53526
;

-- Mar 29, 2010 4:05:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_CreateDate', Name='Asset Creation Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53526
;

-- Mar 29, 2010 4:05:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_CreateDate', Name='Asset Creation Date', Description=NULL, Help=NULL, AD_Element_ID=53526 WHERE UPPER(ColumnName)='A_ASSET_CREATEDATE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:05:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_CreateDate', Name='Asset Creation Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53526 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:05:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Creation Date', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53526) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:05:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Creation Date', Name='Asset Creation Date' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53526)
;

-- Mar 29, 2010 4:05:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Disposed Asset', PrintName='Disposed Asset',Updated=TO_TIMESTAMP('2010-03-29 16:05:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53550
;

-- Mar 29, 2010 4:05:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53550
;

-- Mar 29, 2010 4:05:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Disposed_ID', Name='Disposed Asset', Description=NULL, Help=NULL WHERE AD_Element_ID=53550
;

-- Mar 29, 2010 4:05:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Disposed_ID', Name='Disposed Asset', Description=NULL, Help=NULL, AD_Element_ID=53550 WHERE UPPER(ColumnName)='A_ASSET_DISPOSED_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:05:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Disposed_ID', Name='Disposed Asset', Description=NULL, Help=NULL WHERE AD_Element_ID=53550 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:05:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Disposed Asset', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53550) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:05:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Disposed Asset', Name='Disposed Asset' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53550)
;

-- Mar 29, 2010 4:06:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Group Acct.', PrintName='Asset Group Acct.',Updated=TO_TIMESTAMP('2010-03-29 16:06:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53587
;

-- Mar 29, 2010 4:06:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53587
;

-- Mar 29, 2010 4:06:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Group_Acct_ID', Name='Asset Group Acct.', Description=NULL, Help=NULL WHERE AD_Element_ID=53587
;

-- Mar 29, 2010 4:06:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Group_Acct_ID', Name='Asset Group Acct.', Description=NULL, Help=NULL, AD_Element_ID=53587 WHERE UPPER(ColumnName)='A_ASSET_GROUP_ACCT_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:06:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Group_Acct_ID', Name='Asset Group Acct.', Description=NULL, Help=NULL WHERE AD_Element_ID=53587 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:06:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Group Acct.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53587) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:06:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Group Acct.', Name='Asset Group Acct.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53587)
;

-- Mar 29, 2010 4:06:33 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Info Fin.', PrintName='Asset Info Fin.',Updated=TO_TIMESTAMP('2010-03-29 16:06:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53594
;

-- Mar 29, 2010 4:06:33 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53594
;

-- Mar 29, 2010 4:06:33 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Info_Fin_ID', Name='Asset Info Fin.', Description=NULL, Help=NULL WHERE AD_Element_ID=53594
;

-- Mar 29, 2010 4:06:33 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Info_Fin_ID', Name='Asset Info Fin.', Description=NULL, Help=NULL, AD_Element_ID=53594 WHERE UPPER(ColumnName)='A_ASSET_INFO_FIN_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:06:33 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Info_Fin_ID', Name='Asset Info Fin.', Description=NULL, Help=NULL WHERE AD_Element_ID=53594 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:06:33 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Info Fin.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53594) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:06:33 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Info Fin.', Name='Asset Info Fin.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53594)
;

-- Mar 29, 2010 4:06:48 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Info Ins.', PrintName='Asset Info Ins.',Updated=TO_TIMESTAMP('2010-03-29 16:06:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53618
;

-- Mar 29, 2010 4:06:48 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53618
;

-- Mar 29, 2010 4:06:48 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Info_Ins_ID', Name='Asset Info Ins.', Description=NULL, Help=NULL WHERE AD_Element_ID=53618
;

-- Mar 29, 2010 4:06:48 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Info_Ins_ID', Name='Asset Info Ins.', Description=NULL, Help=NULL, AD_Element_ID=53618 WHERE UPPER(ColumnName)='A_ASSET_INFO_INS_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:06:48 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Info_Ins_ID', Name='Asset Info Ins.', Description=NULL, Help=NULL WHERE AD_Element_ID=53618 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:06:48 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Info Ins.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53618) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:06:48 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Info Ins.', Name='Asset Info Ins.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53618)
;

-- Mar 29, 2010 4:07:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Info Lic.', PrintName='Asset Info Lic.',Updated=TO_TIMESTAMP('2010-03-29 16:07:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53613
;

-- Mar 29, 2010 4:07:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53613
;

-- Mar 29, 2010 4:07:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Info_Lic_ID', Name='Asset Info Lic.', Description=NULL, Help=NULL WHERE AD_Element_ID=53613
;

-- Mar 29, 2010 4:07:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Info_Lic_ID', Name='Asset Info Lic.', Description=NULL, Help=NULL, AD_Element_ID=53613 WHERE UPPER(ColumnName)='A_ASSET_INFO_LIC_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:07:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Info_Lic_ID', Name='Asset Info Lic.', Description=NULL, Help=NULL WHERE AD_Element_ID=53613 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:07:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Info Lic.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53613) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:07:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Info Lic.', Name='Asset Info Lic.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53613)
;

-- Mar 29, 2010 4:07:58 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Info Oth.', PrintName='Asset Info Oth.',Updated=TO_TIMESTAMP('2010-03-29 16:07:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53624
;

-- Mar 29, 2010 4:07:58 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53624
;

-- Mar 29, 2010 4:07:58 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Info_Oth_ID', Name='Asset Info Oth.', Description=NULL, Help=NULL WHERE AD_Element_ID=53624
;

-- Mar 29, 2010 4:07:58 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Info_Oth_ID', Name='Asset Info Oth.', Description=NULL, Help=NULL, AD_Element_ID=53624 WHERE UPPER(ColumnName)='A_ASSET_INFO_OTH_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:07:58 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Info_Oth_ID', Name='Asset Info Oth.', Description=NULL, Help=NULL WHERE AD_Element_ID=53624 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:07:58 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Info Oth.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53624) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:07:58 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Info Oth.', Name='Asset Info Oth.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53624)
;

-- Mar 29, 2010 4:08:10 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Info Tax', PrintName='Asset Info Tax',Updated=TO_TIMESTAMP('2010-03-29 16:08:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53589
;

-- Mar 29, 2010 4:08:10 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53589
;

-- Mar 29, 2010 4:08:10 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Info_Tax_ID', Name='Asset Info Tax', Description=NULL, Help=NULL WHERE AD_Element_ID=53589
;

-- Mar 29, 2010 4:08:10 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Info_Tax_ID', Name='Asset Info Tax', Description=NULL, Help=NULL, AD_Element_ID=53589 WHERE UPPER(ColumnName)='A_ASSET_INFO_TAX_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:08:10 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Info_Tax_ID', Name='Asset Info Tax', Description=NULL, Help=NULL WHERE AD_Element_ID=53589 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:08:10 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Info Tax', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53589) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:08:10 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Info Tax', Name='Asset Info Tax' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53589)
;

-- Mar 29, 2010 4:08:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Life Current Year', PrintName='Asset Life Current Year',Updated=TO_TIMESTAMP('2010-03-29 16:08:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53490
;

-- Mar 29, 2010 4:08:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53490
;

-- Mar 29, 2010 4:08:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Life_Current_Year', Name='Asset Life Current Year', Description=NULL, Help=NULL WHERE AD_Element_ID=53490
;

-- Mar 29, 2010 4:08:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Life_Current_Year', Name='Asset Life Current Year', Description=NULL, Help=NULL, AD_Element_ID=53490 WHERE UPPER(ColumnName)='A_ASSET_LIFE_CURRENT_YEAR' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:08:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Life_Current_Year', Name='Asset Life Current Year', Description=NULL, Help=NULL WHERE AD_Element_ID=53490 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:08:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Life Current Year', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53490) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:08:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Life Current Year', Name='Asset Life Current Year' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53490)
;

-- Mar 29, 2010 4:08:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Life Years', PrintName='Asset Life Years',Updated=TO_TIMESTAMP('2010-03-29 16:08:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53497
;

-- Mar 29, 2010 4:08:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53497
;

-- Mar 29, 2010 4:08:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Life_Years', Name='Asset Life Years', Description=NULL, Help=NULL WHERE AD_Element_ID=53497
;

-- Mar 29, 2010 4:08:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Life_Years', Name='Asset Life Years', Description=NULL, Help=NULL, AD_Element_ID=53497 WHERE UPPER(ColumnName)='A_ASSET_LIFE_YEARS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:08:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Life_Years', Name='Asset Life Years', Description=NULL, Help=NULL WHERE AD_Element_ID=53497 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:08:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Life Years', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53497) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:08:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Life Years', Name='Asset Life Years' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53497)
;

-- Mar 29, 2010 4:08:51 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Reval. Date', PrintName='Asset Reval. Date',Updated=TO_TIMESTAMP('2010-03-29 16:08:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53527
;

-- Mar 29, 2010 4:08:51 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53527
;

-- Mar 29, 2010 4:08:51 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_RevalDate', Name='Asset Reval. Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53527
;

-- Mar 29, 2010 4:08:51 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_RevalDate', Name='Asset Reval. Date', Description=NULL, Help=NULL, AD_Element_ID=53527 WHERE UPPER(ColumnName)='A_ASSET_REVALDATE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:08:51 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_RevalDate', Name='Asset Reval. Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53527 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:08:51 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Reval. Date', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53527) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:08:51 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Reval. Date', Name='Asset Reval. Date' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53527)
;

-- Mar 29, 2010 4:09:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Reval. Entry', PrintName='Asset Reval. Entry',Updated=TO_TIMESTAMP('2010-03-29 16:09:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53507
;

-- Mar 29, 2010 4:09:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53507
;

-- Mar 29, 2010 4:09:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Reval_Entry_ID', Name='Asset Reval. Entry', Description=NULL, Help=NULL WHERE AD_Element_ID=53507
;

-- Mar 29, 2010 4:09:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Reval_Entry_ID', Name='Asset Reval. Entry', Description=NULL, Help=NULL, AD_Element_ID=53507 WHERE UPPER(ColumnName)='A_ASSET_REVAL_ENTRY_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:09:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Reval_Entry_ID', Name='Asset Reval. Entry', Description=NULL, Help=NULL WHERE AD_Element_ID=53507 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:09:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Reval. Entry', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53507) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:09:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Reval. Entry', Name='Asset Reval. Entry' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53507)
;

-- Mar 29, 2010 4:09:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Reval Index', PrintName='Asset Reval Index',Updated=TO_TIMESTAMP('2010-03-29 16:09:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53513
;

-- Mar 29, 2010 4:09:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53513
;

-- Mar 29, 2010 4:09:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Reval_Index_ID', Name='Asset Reval Index', Description=NULL, Help=NULL WHERE AD_Element_ID=53513
;

-- Mar 29, 2010 4:09:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Reval_Index_ID', Name='Asset Reval Index', Description=NULL, Help=NULL, AD_Element_ID=53513 WHERE UPPER(ColumnName)='A_ASSET_REVAL_INDEX_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:09:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Reval_Index_ID', Name='Asset Reval Index', Description=NULL, Help=NULL WHERE AD_Element_ID=53513 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:09:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Reval Index', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53513) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:09:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Reval Index', Name='Asset Reval Index' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53513)
;

-- Mar 29, 2010 4:09:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Split', PrintName='Asset Split',Updated=TO_TIMESTAMP('2010-03-29 16:09:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53517
;

-- Mar 29, 2010 4:09:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53517
;

-- Mar 29, 2010 4:09:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Split_ID', Name='Asset Split', Description=NULL, Help=NULL WHERE AD_Element_ID=53517
;

-- Mar 29, 2010 4:09:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Split_ID', Name='Asset Split', Description=NULL, Help=NULL, AD_Element_ID=53517 WHERE UPPER(ColumnName)='A_ASSET_SPLIT_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:09:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Split_ID', Name='Asset Split', Description=NULL, Help=NULL WHERE AD_Element_ID=53517 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:09:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Split', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53517) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:09:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Split', Name='Asset Split' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53517)
;

-- Mar 29, 2010 4:09:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Spread', PrintName='Asset Spread',Updated=TO_TIMESTAMP('2010-03-29 16:09:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53547
;

-- Mar 29, 2010 4:09:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53547
;

-- Mar 29, 2010 4:09:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Spread_ID', Name='Asset Spread', Description=NULL, Help=NULL WHERE AD_Element_ID=53547
;

-- Mar 29, 2010 4:09:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Spread_ID', Name='Asset Spread', Description=NULL, Help=NULL, AD_Element_ID=53547 WHERE UPPER(ColumnName)='A_ASSET_SPREAD_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:09:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Spread_ID', Name='Asset Spread', Description=NULL, Help=NULL WHERE AD_Element_ID=53547 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:09:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Spread', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53547) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:09:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Spread', Name='Asset Spread' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53547)
;

-- Mar 29, 2010 4:09:59 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Spread Type', PrintName='Asset Spread Type',Updated=TO_TIMESTAMP('2010-03-29 16:09:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53569
;

-- Mar 29, 2010 4:09:59 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53569
;

-- Mar 29, 2010 4:09:59 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Spread_Type', Name='Asset Spread Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53569
;

-- Mar 29, 2010 4:09:59 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Spread_Type', Name='Asset Spread Type', Description=NULL, Help=NULL, AD_Element_ID=53569 WHERE UPPER(ColumnName)='A_ASSET_SPREAD_TYPE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:10:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Spread_Type', Name='Asset Spread Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53569 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:10:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Spread Type', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53569) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:10:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Spread Type', Name='Asset Spread Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53569)
;

-- Mar 29, 2010 4:10:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Trade', PrintName='Asset Trade',Updated=TO_TIMESTAMP('2010-03-29 16:10:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53555
;

-- Mar 29, 2010 4:10:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53555
;

-- Mar 29, 2010 4:10:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Trade_ID', Name='Asset Trade', Description=NULL, Help=NULL WHERE AD_Element_ID=53555
;

-- Mar 29, 2010 4:10:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Trade_ID', Name='Asset Trade', Description=NULL, Help=NULL, AD_Element_ID=53555 WHERE UPPER(ColumnName)='A_ASSET_TRADE_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:10:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Trade_ID', Name='Asset Trade', Description=NULL, Help=NULL WHERE AD_Element_ID=53555 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:10:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Trade', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53555) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:10:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Trade', Name='Asset Trade' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53555)
;

-- Mar 29, 2010 4:10:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Transfer', PrintName='Asset Transfer',Updated=TO_TIMESTAMP('2010-03-29 16:10:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53556
;

-- Mar 29, 2010 4:10:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53556
;

-- Mar 29, 2010 4:10:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Transfer_ID', Name='Asset Transfer', Description=NULL, Help=NULL WHERE AD_Element_ID=53556
;

-- Mar 29, 2010 4:10:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Transfer_ID', Name='Asset Transfer', Description=NULL, Help=NULL, AD_Element_ID=53556 WHERE UPPER(ColumnName)='A_ASSET_TRANSFER_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:10:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Transfer_ID', Name='Asset Transfer', Description=NULL, Help=NULL WHERE AD_Element_ID=53556 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:10:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Transfer', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53556) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:10:26 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Transfer', Name='Asset Transfer' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53556)
;

-- Mar 29, 2010 4:10:50 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Use', PrintName='Asset Use',Updated=TO_TIMESTAMP('2010-03-29 16:10:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53642
;

-- Mar 29, 2010 4:10:50 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53642
;

-- Mar 29, 2010 4:10:50 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Asset_Use_ID', Name='Asset Use', Description=NULL, Help=NULL WHERE AD_Element_ID=53642
;

-- Mar 29, 2010 4:10:50 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Use_ID', Name='Asset Use', Description=NULL, Help=NULL, AD_Element_ID=53642 WHERE UPPER(ColumnName)='A_ASSET_USE_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:10:50 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Asset_Use_ID', Name='Asset Use', Description=NULL, Help=NULL WHERE AD_Element_ID=53642 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:10:50 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Use', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53642) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:10:50 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Use', Name='Asset Use' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53642)
;

-- Mar 29, 2010 4:11:02 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Base Amount', PrintName='Base Amount',Updated=TO_TIMESTAMP('2010-03-29 16:11:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53498
;

-- Mar 29, 2010 4:11:02 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53498
;

-- Mar 29, 2010 4:11:02 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Base_Amount', Name='Base Amount', Description=NULL, Help=NULL WHERE AD_Element_ID=53498
;

-- Mar 29, 2010 4:11:02 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Base_Amount', Name='Base Amount', Description=NULL, Help=NULL, AD_Element_ID=53498 WHERE UPPER(ColumnName)='A_BASE_AMOUNT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:11:02 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Base_Amount', Name='Base Amount', Description=NULL, Help=NULL WHERE AD_Element_ID=53498 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:11:02 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Base Amount', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53498) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:11:02 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Base Amount', Name='Base Amount' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53498)
;

-- Mar 29, 2010 4:11:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Calc. Accumulated Depr.', PrintName='Calc. Accumulated Depr.',Updated=TO_TIMESTAMP('2010-03-29 16:11:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53499
;

-- Mar 29, 2010 4:11:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53499
;

-- Mar 29, 2010 4:11:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Calc_Accumulated_Depr', Name='Calc. Accumulated Depr.', Description=NULL, Help=NULL WHERE AD_Element_ID=53499
;

-- Mar 29, 2010 4:11:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Calc_Accumulated_Depr', Name='Calc. Accumulated Depr.', Description=NULL, Help=NULL, AD_Element_ID=53499 WHERE UPPER(ColumnName)='A_CALC_ACCUMULATED_DEPR' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:11:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Calc_Accumulated_Depr', Name='Calc. Accumulated Depr.', Description=NULL, Help=NULL WHERE AD_Element_ID=53499 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:11:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Calc. Accumulated Depr.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53499) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:11:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Calc. Accumulated Depr.', Name='Calc. Accumulated Depr.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53499)
;

-- Mar 29, 2010 4:11:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Activity Value', PrintName='Activity Value',Updated=TO_TIMESTAMP('2010-03-29 16:11:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53222
;

-- Mar 29, 2010 4:11:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53222
;

-- Mar 29, 2010 4:11:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='ActivityValue', Name='Activity Value', Description=NULL, Help=NULL WHERE AD_Element_ID=53222
;

-- Mar 29, 2010 4:11:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='ActivityValue', Name='Activity Value', Description=NULL, Help=NULL, AD_Element_ID=53222 WHERE UPPER(ColumnName)='ACTIVITYVALUE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:11:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='ActivityValue', Name='Activity Value', Description=NULL, Help=NULL WHERE AD_Element_ID=53222 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:11:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Activity Value', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53222) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:11:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Activity Value', Name='Activity Value' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53222)
;

-- Mar 29, 2010 4:12:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Curr. Dep. Exp.', PrintName='Curr. Dep. Exp.',Updated=TO_TIMESTAMP('2010-03-29 16:12:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53500
;

-- Mar 29, 2010 4:12:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53500
;

-- Mar 29, 2010 4:12:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Curr_Dep_Exp', Name='Curr. Dep. Exp.', Description=NULL, Help=NULL WHERE AD_Element_ID=53500
;

-- Mar 29, 2010 4:12:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Curr_Dep_Exp', Name='Curr. Dep. Exp.', Description=NULL, Help=NULL, AD_Element_ID=53500 WHERE UPPER(ColumnName)='A_CURR_DEP_EXP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:12:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Curr_Dep_Exp', Name='Curr. Dep. Exp.', Description=NULL, Help=NULL WHERE AD_Element_ID=53500 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:12:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Curr. Dep. Exp.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53500) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:12:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Curr. Dep. Exp.', Name='Curr. Dep. Exp.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53500)
;

-- Mar 29, 2010 4:12:16 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Current Period', PrintName='Current Period',Updated=TO_TIMESTAMP('2010-03-29 16:12:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53501
;

-- Mar 29, 2010 4:12:16 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53501
;

-- Mar 29, 2010 4:12:16 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Current_Period', Name='Current Period', Description=NULL, Help=NULL WHERE AD_Element_ID=53501
;

-- Mar 29, 2010 4:12:16 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Current_Period', Name='Current Period', Description=NULL, Help=NULL, AD_Element_ID=53501 WHERE UPPER(ColumnName)='A_CURRENT_PERIOD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:12:16 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Current_Period', Name='Current Period', Description=NULL, Help=NULL WHERE AD_Element_ID=53501 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:12:16 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Current Period', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53501) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:12:16 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Current Period', Name='Current Period' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53501)
;

-- Mar 29, 2010 4:12:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Backup', PrintName='Backup',Updated=TO_TIMESTAMP('2010-03-29 16:12:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50016
;

-- Mar 29, 2010 4:12:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50016
;

-- Mar 29, 2010 4:12:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Backup_ID', Name='Backup', Description=NULL, Help=NULL WHERE AD_Element_ID=50016
;

-- Mar 29, 2010 4:12:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Backup_ID', Name='Backup', Description=NULL, Help=NULL, AD_Element_ID=50016 WHERE UPPER(ColumnName)='AD_BACKUP_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:12:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Backup_ID', Name='Backup', Description=NULL, Help=NULL WHERE AD_Element_ID=50016 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:12:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Backup', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50016) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:12:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Backup', Name='Backup' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50016)
;

-- Mar 29, 2010 4:12:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Depreciation Build', PrintName='Depreciation Build',Updated=TO_TIMESTAMP('2010-03-29 16:12:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53568
;

-- Mar 29, 2010 4:12:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53568
;

-- Mar 29, 2010 4:12:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Depreciation_Build_ID', Name='Depreciation Build', Description=NULL, Help=NULL WHERE AD_Element_ID=53568
;

-- Mar 29, 2010 4:12:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Build_ID', Name='Depreciation Build', Description=NULL, Help=NULL, AD_Element_ID=53568 WHERE UPPER(ColumnName)='A_DEPRECIATION_BUILD_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:12:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Build_ID', Name='Depreciation Build', Description=NULL, Help=NULL WHERE AD_Element_ID=53568 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:12:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Depreciation Build', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53568) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:12:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Build', Name='Depreciation Build' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53568)
;

-- Mar 29, 2010 4:13:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Depreciation Convention', PrintName='Depreciation Convention',Updated=TO_TIMESTAMP('2010-03-29 16:13:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53584
;

-- Mar 29, 2010 4:13:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53584
;

-- Mar 29, 2010 4:13:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Depreciation_Convention_ID', Name='Depreciation Convention', Description=NULL, Help=NULL WHERE AD_Element_ID=53584
;

-- Mar 29, 2010 4:13:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Convention_ID', Name='Depreciation Convention', Description=NULL, Help=NULL, AD_Element_ID=53584 WHERE UPPER(ColumnName)='A_DEPRECIATION_CONVENTION_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:13:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Convention_ID', Name='Depreciation Convention', Description=NULL, Help=NULL WHERE AD_Element_ID=53584 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:13:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Depreciation Convention', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53584) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:13:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Convention', Name='Depreciation Convention' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53584)
;

-- Mar 29, 2010 4:13:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Depreciation Entry', PrintName='Depreciation Entry',Updated=TO_TIMESTAMP('2010-03-29 16:13:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53516
;

-- Mar 29, 2010 4:13:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53516
;

-- Mar 29, 2010 4:13:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Depreciation_Entry_ID', Name='Depreciation Entry', Description=NULL, Help=NULL WHERE AD_Element_ID=53516
;

-- Mar 29, 2010 4:13:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Entry_ID', Name='Depreciation Entry', Description=NULL, Help=NULL, AD_Element_ID=53516 WHERE UPPER(ColumnName)='A_DEPRECIATION_ENTRY_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:13:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Entry_ID', Name='Depreciation Entry', Description=NULL, Help=NULL WHERE AD_Element_ID=53516 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:13:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Depreciation Entry', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53516) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:13:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Entry', Name='Depreciation Entry' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53516)
;

-- Mar 29, 2010 4:13:34 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Depreciation Exp.', PrintName='Depreciation Exp.',Updated=TO_TIMESTAMP('2010-03-29 16:13:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53483
;

-- Mar 29, 2010 4:13:34 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53483
;

-- Mar 29, 2010 4:13:34 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Depreciation_Exp_ID', Name='Depreciation Exp.', Description=NULL, Help=NULL WHERE AD_Element_ID=53483
;

-- Mar 29, 2010 4:13:34 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Exp_ID', Name='Depreciation Exp.', Description=NULL, Help=NULL, AD_Element_ID=53483 WHERE UPPER(ColumnName)='A_DEPRECIATION_EXP_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:13:34 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Exp_ID', Name='Depreciation Exp.', Description=NULL, Help=NULL WHERE AD_Element_ID=53483 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:13:34 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Depreciation Exp.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53483) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:13:34 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Exp.', Name='Depreciation Exp.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53483)
;

-- Mar 29, 2010 4:14:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Depreciation Forecast', PrintName='Depreciation Forecast',Updated=TO_TIMESTAMP('2010-03-29 16:14:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53504
;

-- Mar 29, 2010 4:14:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53504
;

-- Mar 29, 2010 4:14:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Depreciation_Forecast_ID', Name='Depreciation Forecast', Description=NULL, Help=NULL WHERE AD_Element_ID=53504
;

-- Mar 29, 2010 4:14:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Forecast_ID', Name='Depreciation Forecast', Description=NULL, Help=NULL, AD_Element_ID=53504 WHERE UPPER(ColumnName)='A_DEPRECIATION_FORECAST_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:14:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Forecast_ID', Name='Depreciation Forecast', Description=NULL, Help=NULL WHERE AD_Element_ID=53504 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:14:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Depreciation Forecast', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53504) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:14:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Forecast', Name='Depreciation Forecast' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53504)
;

-- Mar 29, 2010 4:14:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Depreciation Manual Amount', PrintName='Depreciation Manual Amount',Updated=TO_TIMESTAMP('2010-03-29 16:14:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53545
;

-- Mar 29, 2010 4:14:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53545
;

-- Mar 29, 2010 4:14:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Depreciation_Manual_Amount', Name='Depreciation Manual Amount', Description=NULL, Help=NULL WHERE AD_Element_ID=53545
;

-- Mar 29, 2010 4:14:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Manual_Amount', Name='Depreciation Manual Amount', Description=NULL, Help=NULL, AD_Element_ID=53545 WHERE UPPER(ColumnName)='A_DEPRECIATION_MANUAL_AMOUNT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:14:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Manual_Amount', Name='Depreciation Manual Amount', Description=NULL, Help=NULL WHERE AD_Element_ID=53545 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:14:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Depreciation Manual Amount', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53545) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:14:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Manual Amount', Name='Depreciation Manual Amount' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53545)
;

-- Mar 29, 2010 4:14:51 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Depreciation Manual Period', PrintName='Depreciation Manual Period',Updated=TO_TIMESTAMP('2010-03-29 16:14:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53531
;

-- Mar 29, 2010 4:14:51 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53531
;

-- Mar 29, 2010 4:14:51 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Depreciation_Manual_Period', Name='Depreciation Manual Period', Description=NULL, Help=NULL WHERE AD_Element_ID=53531
;

-- Mar 29, 2010 4:14:51 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Manual_Period', Name='Depreciation Manual Period', Description=NULL, Help=NULL, AD_Element_ID=53531 WHERE UPPER(ColumnName)='A_DEPRECIATION_MANUAL_PERIOD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:14:51 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Manual_Period', Name='Depreciation Manual Period', Description=NULL, Help=NULL WHERE AD_Element_ID=53531 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:14:51 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Depreciation Manual Period', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53531) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:14:51 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Manual Period', Name='Depreciation Manual Period' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53531)
;

-- Mar 29, 2010 4:15:13 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Depreciation Table Header', PrintName='Depreciation Table Header',Updated=TO_TIMESTAMP('2010-03-29 16:15:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53481
;

-- Mar 29, 2010 4:15:13 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53481
;

-- Mar 29, 2010 4:15:13 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Depreciation_Table_Header_ID', Name='Depreciation Table Header', Description=NULL, Help=NULL WHERE AD_Element_ID=53481
;

-- Mar 29, 2010 4:15:13 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Table_Header_ID', Name='Depreciation Table Header', Description=NULL, Help=NULL, AD_Element_ID=53481 WHERE UPPER(ColumnName)='A_DEPRECIATION_TABLE_HEADER_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:15:13 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Table_Header_ID', Name='Depreciation Table Header', Description=NULL, Help=NULL WHERE AD_Element_ID=53481 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:15:13 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Depreciation Table Header', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53481) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:15:13 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Table Header', Name='Depreciation Table Header' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53481)
;

-- Mar 29, 2010 4:15:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Depreciation Variable Perc.', PrintName='Depreciation Variable Perc.',Updated=TO_TIMESTAMP('2010-03-29 16:15:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53543
;

-- Mar 29, 2010 4:15:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53543
;

-- Mar 29, 2010 4:15:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Depreciation_Variable_Perc', Name='Depreciation Variable Perc.', Description=NULL, Help=NULL WHERE AD_Element_ID=53543
;

-- Mar 29, 2010 4:15:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Variable_Perc', Name='Depreciation Variable Perc.', Description=NULL, Help=NULL, AD_Element_ID=53543 WHERE UPPER(ColumnName)='A_DEPRECIATION_VARIABLE_PERC' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:15:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Variable_Perc', Name='Depreciation Variable Perc.', Description=NULL, Help=NULL WHERE AD_Element_ID=53543 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:15:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Depreciation Variable Perc.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53543) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:15:25 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Variable Perc.', Name='Depreciation Variable Perc.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53543)
;

-- Mar 29, 2010 4:15:39 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Depreciation Workfile', PrintName='Depreciation Workfile',Updated=TO_TIMESTAMP('2010-03-29 16:15:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53487
;

-- Mar 29, 2010 4:15:39 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53487
;

-- Mar 29, 2010 4:15:39 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Depreciation_Workfile_ID', Name='Depreciation Workfile', Description=NULL, Help=NULL WHERE AD_Element_ID=53487
;

-- Mar 29, 2010 4:15:39 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Workfile_ID', Name='Depreciation Workfile', Description=NULL, Help=NULL, AD_Element_ID=53487 WHERE UPPER(ColumnName)='A_DEPRECIATION_WORKFILE_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:15:39 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Workfile_ID', Name='Depreciation Workfile', Description=NULL, Help=NULL WHERE AD_Element_ID=53487 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:15:39 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Depreciation Workfile', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53487) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:15:39 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Workfile', Name='Depreciation Workfile' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53487)
;

-- Mar 29, 2010 4:15:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Disposal Gain', PrintName='Disposal Gain',Updated=TO_TIMESTAMP('2010-03-29 16:15:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53532
;

-- Mar 29, 2010 4:15:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53532
;

-- Mar 29, 2010 4:15:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Disposal_Gain', Name='Disposal Gain', Description=NULL, Help=NULL WHERE AD_Element_ID=53532
;

-- Mar 29, 2010 4:15:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Disposal_Gain', Name='Disposal Gain', Description=NULL, Help=NULL, AD_Element_ID=53532 WHERE UPPER(ColumnName)='A_DISPOSAL_GAIN' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:15:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Disposal_Gain', Name='Disposal Gain', Description=NULL, Help=NULL WHERE AD_Element_ID=53532 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:15:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Disposal Gain', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53532) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:15:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Disposal Gain', Name='Disposal Gain' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53532)
;

-- Mar 29, 2010 4:16:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Original', PrintName='Original',Updated=TO_TIMESTAMP('2010-03-29 16:16:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50015
;

-- Mar 29, 2010 4:16:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50015
;

-- Mar 29, 2010 4:16:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Original_ID', Name='Original', Description=NULL, Help=NULL WHERE AD_Element_ID=50015
;

-- Mar 29, 2010 4:16:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Original_ID', Name='Original', Description=NULL, Help=NULL, AD_Element_ID=50015 WHERE UPPER(ColumnName)='AD_ORIGINAL_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:16:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Original_ID', Name='Original', Description=NULL, Help=NULL WHERE AD_Element_ID=50015 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:16:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Original', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50015) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:16:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Original', Name='Original' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50015)
;

-- Mar 29, 2010 4:16:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='New Package Code', PrintName='New Package Code',Updated=TO_TIMESTAMP('2010-03-29 16:16:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50029
;

-- Mar 29, 2010 4:16:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50029
;

-- Mar 29, 2010 4:16:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Package_Code_New', Name='New Package Code', Description=NULL, Help=NULL WHERE AD_Element_ID=50029
;

-- Mar 29, 2010 4:16:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Code_New', Name='New Package Code', Description=NULL, Help=NULL, AD_Element_ID=50029 WHERE UPPER(ColumnName)='AD_PACKAGE_CODE_NEW' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:16:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Code_New', Name='New Package Code', Description=NULL, Help=NULL WHERE AD_Element_ID=50029 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:16:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='New Package Code', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50029) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:16:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='New Package Code', Name='New Package Code' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50029)
;

-- Mar 29, 2010 4:16:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Old Package Code', PrintName='Old Package Code',Updated=TO_TIMESTAMP('2010-03-29 16:16:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50030
;

-- Mar 29, 2010 4:16:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50030
;

-- Mar 29, 2010 4:16:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Package_Code_Old', Name='Old Package Code', Description=NULL, Help=NULL WHERE AD_Element_ID=50030
;

-- Mar 29, 2010 4:16:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Code_Old', Name='Old Package Code', Description=NULL, Help=NULL, AD_Element_ID=50030 WHERE UPPER(ColumnName)='AD_PACKAGE_CODE_OLD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:16:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Code_Old', Name='Old Package Code', Description=NULL, Help=NULL WHERE AD_Element_ID=50030 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:16:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Old Package Code', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50030) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:16:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Old Package Code', Name='Old Package Code' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50030)
;

-- Mar 29, 2010 4:17:47 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET PrintName='Package Directory',Updated=TO_TIMESTAMP('2010-03-29 16:17:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50033
;

-- Mar 29, 2010 4:17:47 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50033
;

-- Mar 29, 2010 4:17:47 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Package Directory', Name='Package Directory' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50033)
;

-- Mar 29, 2010 4:18:08 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Common Package Exp.', PrintName='Common Package Exp.',Updated=TO_TIMESTAMP('2010-03-29 16:18:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50031
;

-- Mar 29, 2010 4:18:08 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50031
;

-- Mar 29, 2010 4:18:08 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Package_Exp_Common_ID', Name='Common Package Exp.', Description=NULL, Help=NULL WHERE AD_Element_ID=50031
;

-- Mar 29, 2010 4:18:08 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Exp_Common_ID', Name='Common Package Exp.', Description=NULL, Help=NULL, AD_Element_ID=50031 WHERE UPPER(ColumnName)='AD_PACKAGE_EXP_COMMON_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:18:08 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Exp_Common_ID', Name='Common Package Exp.', Description=NULL, Help=NULL WHERE AD_Element_ID=50031 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:18:08 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Common Package Exp.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50031) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:18:08 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Common Package Exp.', Name='Common Package Exp.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50031)
;

-- Mar 29, 2010 4:18:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Package Exp. Detail', PrintName='Package Exp. Detail',Updated=TO_TIMESTAMP('2010-03-29 16:18:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50023
;

-- Mar 29, 2010 4:18:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50023
;

-- Mar 29, 2010 4:18:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Package_Exp_Detail_ID', Name='Package Exp. Detail', Description=NULL, Help=NULL WHERE AD_Element_ID=50023
;

-- Mar 29, 2010 4:18:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Exp_Detail_ID', Name='Package Exp. Detail', Description=NULL, Help=NULL, AD_Element_ID=50023 WHERE UPPER(ColumnName)='AD_PACKAGE_EXP_DETAIL_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:18:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Exp_Detail_ID', Name='Package Exp. Detail', Description=NULL, Help=NULL WHERE AD_Element_ID=50023 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:18:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Package Exp. Detail', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50023) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:18:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Package Exp. Detail', Name='Package Exp. Detail' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50023)
;

-- Mar 29, 2010 4:18:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Package Exp.', PrintName='Package Exp.',Updated=TO_TIMESTAMP('2010-03-29 16:18:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50018
;

-- Mar 29, 2010 4:18:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50018
;

-- Mar 29, 2010 4:18:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Package_Exp_ID', Name='Package Exp.', Description=NULL, Help=NULL WHERE AD_Element_ID=50018
;

-- Mar 29, 2010 4:18:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Exp_ID', Name='Package Exp.', Description=NULL, Help=NULL, AD_Element_ID=50018 WHERE UPPER(ColumnName)='AD_PACKAGE_EXP_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:18:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Exp_ID', Name='Package Exp.', Description=NULL, Help=NULL WHERE AD_Element_ID=50018 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:18:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Package Exp.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50018) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:18:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Package Exp.', Name='Package Exp.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50018)
;

-- Mar 29, 2010 4:19:32 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Imp. Package Backup', PrintName='Imp. Package Backup',Updated=TO_TIMESTAMP('2010-03-29 16:19:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50009
;

-- Mar 29, 2010 4:19:32 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50009
;

-- Mar 29, 2010 4:19:32 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Package_Imp_Backup_ID', Name='Imp. Package Backup', Description=NULL, Help=NULL WHERE AD_Element_ID=50009
;

-- Mar 29, 2010 4:19:32 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Imp_Backup_ID', Name='Imp. Package Backup', Description=NULL, Help=NULL, AD_Element_ID=50009 WHERE UPPER(ColumnName)='AD_PACKAGE_IMP_BACKUP_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:19:32 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Imp_Backup_ID', Name='Imp. Package Backup', Description=NULL, Help=NULL WHERE AD_Element_ID=50009 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:19:32 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Imp. Package Backup', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50009) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:19:32 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Imp. Package Backup', Name='Imp. Package Backup' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50009)
;

-- Mar 29, 2010 4:19:52 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Package Imp. Bck. Directory', PrintName='Package Imp. Bck. Directory',Updated=TO_TIMESTAMP('2010-03-29 16:19:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50014
;

-- Mar 29, 2010 4:19:52 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50014
;

-- Mar 29, 2010 4:19:52 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Package_Imp_Bck_Dir', Name='Package Imp. Bck. Directory', Description=NULL, Help=NULL WHERE AD_Element_ID=50014
;

-- Mar 29, 2010 4:19:52 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Imp_Bck_Dir', Name='Package Imp. Bck. Directory', Description=NULL, Help=NULL, AD_Element_ID=50014 WHERE UPPER(ColumnName)='AD_PACKAGE_IMP_BCK_DIR' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:19:52 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Imp_Bck_Dir', Name='Package Imp. Bck. Directory', Description=NULL, Help=NULL WHERE AD_Element_ID=50014 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:19:52 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Package Imp. Bck. Directory', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50014) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:19:52 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Package Imp. Bck. Directory', Name='Package Imp. Bck. Directory' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50014)
;

-- Mar 29, 2010 4:20:10 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Imp. Package Detail', PrintName='Imp. Package Detail',Updated=TO_TIMESTAMP('2010-03-29 16:20:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50010
;

-- Mar 29, 2010 4:20:10 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50010
;

-- Mar 29, 2010 4:20:10 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Package_Imp_Detail_ID', Name='Imp. Package Detail', Description=NULL, Help=NULL WHERE AD_Element_ID=50010
;

-- Mar 29, 2010 4:20:10 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Imp_Detail_ID', Name='Imp. Package Detail', Description=NULL, Help=NULL, AD_Element_ID=50010 WHERE UPPER(ColumnName)='AD_PACKAGE_IMP_DETAIL_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:20:10 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Imp_Detail_ID', Name='Imp. Package Detail', Description=NULL, Help=NULL WHERE AD_Element_ID=50010 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:20:10 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Imp. Package Detail', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50010) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:20:11 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Imp. Package Detail', Name='Imp. Package Detail' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50010)
;

-- Mar 29, 2010 4:20:27 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Package Imp.', PrintName='Package Imp.',Updated=TO_TIMESTAMP('2010-03-29 16:20:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50013
;

-- Mar 29, 2010 4:20:27 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50013
;

-- Mar 29, 2010 4:20:27 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Package_Imp_ID', Name='Package Imp.', Description=NULL, Help=NULL WHERE AD_Element_ID=50013
;

-- Mar 29, 2010 4:20:27 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Imp_ID', Name='Package Imp.', Description=NULL, Help=NULL, AD_Element_ID=50013 WHERE UPPER(ColumnName)='AD_PACKAGE_IMP_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:20:27 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Imp_ID', Name='Package Imp.', Description=NULL, Help=NULL WHERE AD_Element_ID=50013 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:20:27 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Package Imp.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50013) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:20:27 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Package Imp.', Name='Package Imp.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50013)
;

-- Mar 29, 2010 4:21:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET ColumnName='AD_Package_Imp_Inst_ID', Name='Package Imp. Inst.', PrintName='Package Imp. Inst.',Updated=TO_TIMESTAMP('2010-03-29 16:21:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50001
;

-- Mar 29, 2010 4:21:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50001
;

-- Mar 29, 2010 4:21:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Package_Imp_Inst_ID', Name='Package Imp. Inst.', Description=NULL, Help=NULL WHERE AD_Element_ID=50001
;

-- Mar 29, 2010 4:21:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Imp_Inst_ID', Name='Package Imp. Inst.', Description=NULL, Help=NULL, AD_Element_ID=50001 WHERE UPPER(ColumnName)='AD_PACKAGE_IMP_INST_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:21:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Imp_Inst_ID', Name='Package Imp. Inst.', Description=NULL, Help=NULL WHERE AD_Element_ID=50001 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:21:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Package Imp. Inst.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50001) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:21:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Package Imp. Inst.', Name='Package Imp. Inst.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50001)
;

-- Mar 29, 2010 4:23:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Package Imp. Org. Dir.', PrintName='Package Imp. Org. Dir.',Updated=TO_TIMESTAMP('2010-03-29 16:23:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50011
;

-- Mar 29, 2010 4:23:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50011
;

-- Mar 29, 2010 4:23:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Package_Imp_Org_Dir', Name='Package Imp. Org. Dir.', Description=NULL, Help=NULL WHERE AD_Element_ID=50011
;

-- Mar 29, 2010 4:23:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Imp_Org_Dir', Name='Package Imp. Org. Dir.', Description=NULL, Help=NULL, AD_Element_ID=50011 WHERE UPPER(ColumnName)='AD_PACKAGE_IMP_ORG_DIR' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:23:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Imp_Org_Dir', Name='Package Imp. Org. Dir.', Description=NULL, Help=NULL WHERE AD_Element_ID=50011 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:23:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Package Imp. Org. Dir.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50011) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:23:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Package Imp. Org. Dir.', Name='Package Imp. Org. Dir.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50011)
;

-- Mar 29, 2010 4:23:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Package Imp. Proc.', PrintName='Package Imp. Proc.',Updated=TO_TIMESTAMP('2010-03-29 16:23:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50034
;

-- Mar 29, 2010 4:23:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50034
;

-- Mar 29, 2010 4:23:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Package_Imp_Proc_ID', Name='Package Imp. Proc.', Description=NULL, Help=NULL WHERE AD_Element_ID=50034
;

-- Mar 29, 2010 4:23:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Imp_Proc_ID', Name='Package Imp. Proc.', Description=NULL, Help=NULL, AD_Element_ID=50034 WHERE UPPER(ColumnName)='AD_PACKAGE_IMP_PROC_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:23:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Imp_Proc_ID', Name='Package Imp. Proc.', Description=NULL, Help=NULL WHERE AD_Element_ID=50034 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:23:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Package Imp. Proc.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50034) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:23:53 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Package Imp. Proc.', Name='Package Imp. Proc.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50034)
;

-- Mar 29, 2010 4:24:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET PrintName='Package Source Type',Updated=TO_TIMESTAMP('2010-03-29 16:24:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50036
;

-- Mar 29, 2010 4:24:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50036
;

-- Mar 29, 2010 4:24:05 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Package Source Type', Name='Package Source Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50036)
;

-- Mar 29, 2010 4:24:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Package Type', PrintName='Package Type',Updated=TO_TIMESTAMP('2010-03-29 16:24:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=50019
;

-- Mar 29, 2010 4:24:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=50019
;

-- Mar 29, 2010 4:24:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_Package_Type', Name='Package Type', Description=NULL, Help=NULL WHERE AD_Element_ID=50019
;

-- Mar 29, 2010 4:24:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Type', Name='Package Type', Description=NULL, Help=NULL, AD_Element_ID=50019 WHERE UPPER(ColumnName)='AD_PACKAGE_TYPE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:24:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_Package_Type', Name='Package Type', Description=NULL, Help=NULL WHERE AD_Element_ID=50019 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:24:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Package Type', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=50019) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:24:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Package Type', Name='Package Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=50019)
;

-- Mar 29, 2010 4:24:35 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Search Definition', PrintName='Search Definition',Updated=TO_TIMESTAMP('2010-03-29 16:24:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53774
;

-- Mar 29, 2010 4:24:35 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53774
;

-- Mar 29, 2010 4:24:35 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AD_SearchDefinition_ID', Name='Search Definition', Description=NULL, Help=NULL WHERE AD_Element_ID=53774
;

-- Mar 29, 2010 4:24:35 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_SearchDefinition_ID', Name='Search Definition', Description=NULL, Help=NULL, AD_Element_ID=53774 WHERE UPPER(ColumnName)='AD_SEARCHDEFINITION_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:24:35 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AD_SearchDefinition_ID', Name='Search Definition', Description=NULL, Help=NULL WHERE AD_Element_ID=53774 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:24:35 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Search Definition', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53774) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:24:35 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Search Definition', Name='Search Definition' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53774)
;

-- Mar 29, 2010 4:24:58 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Effective Date', PrintName='Effective Date',Updated=TO_TIMESTAMP('2010-03-29 16:24:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53508
;

-- Mar 29, 2010 4:24:58 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53508
;

-- Mar 29, 2010 4:24:58 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Effective_Date', Name='Effective Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53508
;

-- Mar 29, 2010 4:24:58 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Effective_Date', Name='Effective Date', Description=NULL, Help=NULL, AD_Element_ID=53508 WHERE UPPER(ColumnName)='A_EFFECTIVE_DATE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:24:58 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Effective_Date', Name='Effective Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53508 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:24:58 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Effective Date', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53508) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:24:58 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Effective Date', Name='Effective Date' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53508)
;

-- Mar 29, 2010 4:26:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Original Percent', PrintName='Original Percent',Updated=TO_TIMESTAMP('2010-03-29 16:26:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53520
;

-- Mar 29, 2010 4:26:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53520
;

-- Mar 29, 2010 4:26:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Percent_Original', Name='Original Percent', Description=NULL, Help=NULL WHERE AD_Element_ID=53520
;

-- Mar 29, 2010 4:26:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Percent_Original', Name='Original Percent', Description=NULL, Help=NULL, AD_Element_ID=53520 WHERE UPPER(ColumnName)='A_PERCENT_ORIGINAL' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:26:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Percent_Original', Name='Original Percent', Description=NULL, Help=NULL WHERE AD_Element_ID=53520 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:26:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Original Percent', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53520) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:26:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Original Percent', Name='Original Percent' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53520)
;

-- Mar 29, 2010 4:26:28 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Period 1', PrintName='Period 1',Updated=TO_TIMESTAMP('2010-03-29 16:26:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53583
;

-- Mar 29, 2010 4:26:28 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53583
;

-- Mar 29, 2010 4:26:28 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_1', Name='Period 1', Description=NULL, Help=NULL WHERE AD_Element_ID=53583
;

-- Mar 29, 2010 4:26:28 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_1', Name='Period 1', Description=NULL, Help=NULL, AD_Element_ID=53583 WHERE UPPER(ColumnName)='A_PERIOD_1' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:26:28 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_1', Name='Period 1', Description=NULL, Help=NULL WHERE AD_Element_ID=53583 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:26:28 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Period 1', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53583) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:26:28 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Period 1', Name='Period 1' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53583)
;

-- Mar 29, 2010 4:26:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Period 10', PrintName='Period 10',Updated=TO_TIMESTAMP('2010-03-29 16:26:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53570
;

-- Mar 29, 2010 4:26:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53570
;

-- Mar 29, 2010 4:26:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_10', Name='Period 10', Description=NULL, Help=NULL WHERE AD_Element_ID=53570
;

-- Mar 29, 2010 4:26:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_10', Name='Period 10', Description=NULL, Help=NULL, AD_Element_ID=53570 WHERE UPPER(ColumnName)='A_PERIOD_10' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:26:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_10', Name='Period 10', Description=NULL, Help=NULL WHERE AD_Element_ID=53570 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:26:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Period 10', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53570) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:26:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Period 10', Name='Period 10' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53570)
;

-- Mar 29, 2010 4:26:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Period 11', PrintName='Period 11',Updated=TO_TIMESTAMP('2010-03-29 16:26:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53582
;

-- Mar 29, 2010 4:26:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53582
;

-- Mar 29, 2010 4:26:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_11', Name='Period 11', Description=NULL, Help=NULL WHERE AD_Element_ID=53582
;

-- Mar 29, 2010 4:26:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_11', Name='Period 11', Description=NULL, Help=NULL, AD_Element_ID=53582 WHERE UPPER(ColumnName)='A_PERIOD_11' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:26:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_11', Name='Period 11', Description=NULL, Help=NULL WHERE AD_Element_ID=53582 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:26:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Period 11', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53582) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:26:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Period 11', Name='Period 11' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53582)
;

-- Mar 29, 2010 4:27:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Period 12', PrintName='Period 12',Updated=TO_TIMESTAMP('2010-03-29 16:27:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53571
;

-- Mar 29, 2010 4:27:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53571
;

-- Mar 29, 2010 4:27:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_12', Name='Period 12', Description=NULL, Help=NULL WHERE AD_Element_ID=53571
;

-- Mar 29, 2010 4:27:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_12', Name='Period 12', Description=NULL, Help=NULL, AD_Element_ID=53571 WHERE UPPER(ColumnName)='A_PERIOD_12' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:27:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_12', Name='Period 12', Description=NULL, Help=NULL WHERE AD_Element_ID=53571 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Period 12', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53571) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:00 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Period 12', Name='Period 12' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53571)
;

-- Mar 29, 2010 4:27:07 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Period 13', PrintName='Period 13',Updated=TO_TIMESTAMP('2010-03-29 16:27:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53581
;

-- Mar 29, 2010 4:27:07 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53581
;

-- Mar 29, 2010 4:27:07 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_13', Name='Period 13', Description=NULL, Help=NULL WHERE AD_Element_ID=53581
;

-- Mar 29, 2010 4:27:07 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_13', Name='Period 13', Description=NULL, Help=NULL, AD_Element_ID=53581 WHERE UPPER(ColumnName)='A_PERIOD_13' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:27:07 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_13', Name='Period 13', Description=NULL, Help=NULL WHERE AD_Element_ID=53581 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:07 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Period 13', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53581) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:07 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Period 13', Name='Period 13' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53581)
;

-- Mar 29, 2010 4:27:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Period 14', PrintName='Period 14',Updated=TO_TIMESTAMP('2010-03-29 16:27:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53572
;

-- Mar 29, 2010 4:27:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53572
;

-- Mar 29, 2010 4:27:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_14', Name='Period 14', Description=NULL, Help=NULL WHERE AD_Element_ID=53572
;

-- Mar 29, 2010 4:27:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_14', Name='Period 14', Description=NULL, Help=NULL, AD_Element_ID=53572 WHERE UPPER(ColumnName)='A_PERIOD_14' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:27:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_14', Name='Period 14', Description=NULL, Help=NULL WHERE AD_Element_ID=53572 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Period 14', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53572) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Period 14', Name='Period 14' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53572)
;

-- Mar 29, 2010 4:27:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Period 2', PrintName='Period 2',Updated=TO_TIMESTAMP('2010-03-29 16:27:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53580
;

-- Mar 29, 2010 4:27:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53580
;

-- Mar 29, 2010 4:27:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_2', Name='Period 2', Description=NULL, Help=NULL WHERE AD_Element_ID=53580
;

-- Mar 29, 2010 4:27:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_2', Name='Period 2', Description=NULL, Help=NULL, AD_Element_ID=53580 WHERE UPPER(ColumnName)='A_PERIOD_2' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:27:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_2', Name='Period 2', Description=NULL, Help=NULL WHERE AD_Element_ID=53580 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Period 2', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53580) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:23 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Period 2', Name='Period 2' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53580)
;

-- Mar 29, 2010 4:27:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Period 3', PrintName='Period 3',Updated=TO_TIMESTAMP('2010-03-29 16:27:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53573
;

-- Mar 29, 2010 4:27:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53573
;

-- Mar 29, 2010 4:27:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_3', Name='Period 3', Description=NULL, Help=NULL WHERE AD_Element_ID=53573
;

-- Mar 29, 2010 4:27:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_3', Name='Period 3', Description=NULL, Help=NULL, AD_Element_ID=53573 WHERE UPPER(ColumnName)='A_PERIOD_3' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:27:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_3', Name='Period 3', Description=NULL, Help=NULL WHERE AD_Element_ID=53573 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Period 3', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53573) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Period 3', Name='Period 3' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53573)
;

-- Mar 29, 2010 4:27:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Period 4', PrintName='Period 4',Updated=TO_TIMESTAMP('2010-03-29 16:27:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53579
;

-- Mar 29, 2010 4:27:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53579
;

-- Mar 29, 2010 4:27:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_4', Name='Period 4', Description=NULL, Help=NULL WHERE AD_Element_ID=53579
;

-- Mar 29, 2010 4:27:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_4', Name='Period 4', Description=NULL, Help=NULL, AD_Element_ID=53579 WHERE UPPER(ColumnName)='A_PERIOD_4' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:27:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_4', Name='Period 4', Description=NULL, Help=NULL WHERE AD_Element_ID=53579 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Period 4', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53579) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Period 4', Name='Period 4' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53579)
;

-- Mar 29, 2010 4:27:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Period 5', PrintName='Period 5',Updated=TO_TIMESTAMP('2010-03-29 16:27:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53574
;

-- Mar 29, 2010 4:27:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53574
;

-- Mar 29, 2010 4:27:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_5', Name='Period 5', Description=NULL, Help=NULL WHERE AD_Element_ID=53574
;

-- Mar 29, 2010 4:27:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_5', Name='Period 5', Description=NULL, Help=NULL, AD_Element_ID=53574 WHERE UPPER(ColumnName)='A_PERIOD_5' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:27:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_5', Name='Period 5', Description=NULL, Help=NULL WHERE AD_Element_ID=53574 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Period 5', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53574) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Period 5', Name='Period 5' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53574)
;

-- Mar 29, 2010 4:27:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Period 6', PrintName='Period 6',Updated=TO_TIMESTAMP('2010-03-29 16:27:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53578
;

-- Mar 29, 2010 4:27:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53578
;

-- Mar 29, 2010 4:27:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_6', Name='Period 6', Description=NULL, Help=NULL WHERE AD_Element_ID=53578
;

-- Mar 29, 2010 4:27:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_6', Name='Period 6', Description=NULL, Help=NULL, AD_Element_ID=53578 WHERE UPPER(ColumnName)='A_PERIOD_6' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:27:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_6', Name='Period 6', Description=NULL, Help=NULL WHERE AD_Element_ID=53578 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Period 6', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53578) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:27:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Period 6', Name='Period 6' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53578)
;

-- Mar 29, 2010 4:28:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Period 7', PrintName='Period 7',Updated=TO_TIMESTAMP('2010-03-29 16:28:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53575
;

-- Mar 29, 2010 4:28:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53575
;

-- Mar 29, 2010 4:28:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_7', Name='Period 7', Description=NULL, Help=NULL WHERE AD_Element_ID=53575
;

-- Mar 29, 2010 4:28:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_7', Name='Period 7', Description=NULL, Help=NULL, AD_Element_ID=53575 WHERE UPPER(ColumnName)='A_PERIOD_7' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:28:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_7', Name='Period 7', Description=NULL, Help=NULL WHERE AD_Element_ID=53575 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:28:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Period 7', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53575) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:28:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Period 7', Name='Period 7' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53575)
;

-- Mar 29, 2010 4:28:09 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Period 8', PrintName='Period 8',Updated=TO_TIMESTAMP('2010-03-29 16:28:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53577
;

-- Mar 29, 2010 4:28:09 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53577
;

-- Mar 29, 2010 4:28:09 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_8', Name='Period 8', Description=NULL, Help=NULL WHERE AD_Element_ID=53577
;

-- Mar 29, 2010 4:28:09 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_8', Name='Period 8', Description=NULL, Help=NULL, AD_Element_ID=53577 WHERE UPPER(ColumnName)='A_PERIOD_8' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:28:09 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_8', Name='Period 8', Description=NULL, Help=NULL WHERE AD_Element_ID=53577 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:28:09 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Period 8', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53577) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:28:09 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Period 8', Name='Period 8' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53577)
;

-- Mar 29, 2010 4:28:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Period 9', PrintName='Period 9',Updated=TO_TIMESTAMP('2010-03-29 16:28:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53576
;

-- Mar 29, 2010 4:28:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53576
;

-- Mar 29, 2010 4:28:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_9', Name='Period 9', Description=NULL, Help=NULL WHERE AD_Element_ID=53576
;

-- Mar 29, 2010 4:28:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_9', Name='Period 9', Description=NULL, Help=NULL, AD_Element_ID=53576 WHERE UPPER(ColumnName)='A_PERIOD_9' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:28:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_9', Name='Period 9', Description=NULL, Help=NULL WHERE AD_Element_ID=53576 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:28:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Period 9', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53576) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:28:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Period 9', Name='Period 9' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53576)
;

-- Mar 29, 2010 4:28:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Forecast Period', PrintName='Forecast Period',Updated=TO_TIMESTAMP('2010-03-29 16:28:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53491
;

-- Mar 29, 2010 4:28:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53491
;

-- Mar 29, 2010 4:28:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_Forecast', Name='Forecast Period', Description=NULL, Help=NULL WHERE AD_Element_ID=53491
;

-- Mar 29, 2010 4:28:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_Forecast', Name='Forecast Period', Description=NULL, Help=NULL, AD_Element_ID=53491 WHERE UPPER(ColumnName)='A_PERIOD_FORECAST' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:28:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_Forecast', Name='Forecast Period', Description=NULL, Help=NULL WHERE AD_Element_ID=53491 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:28:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Forecast Period', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53491) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:28:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Forecast Period', Name='Forecast Period' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53491)
;

-- Mar 29, 2010 4:29:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Period Posted', PrintName='Period Posted',Updated=TO_TIMESTAMP('2010-03-29 16:29:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53495
;

-- Mar 29, 2010 4:29:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53495
;

-- Mar 29, 2010 4:29:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Period_Posted', Name='Period Posted', Description=NULL, Help=NULL WHERE AD_Element_ID=53495
;

-- Mar 29, 2010 4:29:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_Posted', Name='Period Posted', Description=NULL, Help=NULL, AD_Element_ID=53495 WHERE UPPER(ColumnName)='A_PERIOD_POSTED' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:29:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Period_Posted', Name='Period Posted', Description=NULL, Help=NULL WHERE AD_Element_ID=53495 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:29:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Period Posted', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53495) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:29:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Period Posted', Name='Period Posted' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53495)
;

-- Mar 29, 2010 4:29:24 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Prior. Year Accumulated Depr.', PrintName='Prior. Year Accumulated Depr.',Updated=TO_TIMESTAMP('2010-03-29 16:29:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53492
;

-- Mar 29, 2010 4:29:24 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53492
;

-- Mar 29, 2010 4:29:24 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Prior_Year_Accumulated_Depr', Name='Prior. Year Accumulated Depr.', Description=NULL, Help=NULL WHERE AD_Element_ID=53492
;

-- Mar 29, 2010 4:29:24 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Prior_Year_Accumulated_Depr', Name='Prior. Year Accumulated Depr.', Description=NULL, Help=NULL, AD_Element_ID=53492 WHERE UPPER(ColumnName)='A_PRIOR_YEAR_ACCUMULATED_DEPR' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:29:24 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Prior_Year_Accumulated_Depr', Name='Prior. Year Accumulated Depr.', Description=NULL, Help=NULL WHERE AD_Element_ID=53492 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:29:24 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Prior. Year Accumulated Depr.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53492) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:29:24 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Prior. Year Accumulated Depr.', Name='Prior. Year Accumulated Depr.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53492)
;

-- Mar 29, 2010 4:29:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Proceeds', PrintName='Proceeds',Updated=TO_TIMESTAMP('2010-03-29 16:29:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53553
;

-- Mar 29, 2010 4:29:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53553
;

-- Mar 29, 2010 4:29:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Proceeds', Name='Proceeds', Description=NULL, Help=NULL WHERE AD_Element_ID=53553
;

-- Mar 29, 2010 4:29:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Proceeds', Name='Proceeds', Description=NULL, Help=NULL, AD_Element_ID=53553 WHERE UPPER(ColumnName)='A_PROCEEDS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:29:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Proceeds', Name='Proceeds', Description=NULL, Help=NULL WHERE AD_Element_ID=53553 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:29:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Proceeds', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53553) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:29:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Proceeds', Name='Proceeds' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53553)
;

-- Mar 29, 2010 4:29:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Processed', PrintName='Processed',Updated=TO_TIMESTAMP('2010-03-29 16:29:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53647
;

-- Mar 29, 2010 4:29:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53647
;

-- Mar 29, 2010 4:29:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Processed', Name='Processed', Description=NULL, Help=NULL WHERE AD_Element_ID=53647
;

-- Mar 29, 2010 4:29:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Processed', Name='Processed', Description=NULL, Help=NULL, AD_Element_ID=53647 WHERE UPPER(ColumnName)='A_PROCESSED' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:29:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Processed', Name='Processed', Description=NULL, Help=NULL WHERE AD_Element_ID=53647 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:29:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Processed', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53647) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:29:37 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Processed', Name='Processed' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53647)
;

-- Mar 29, 2010 4:29:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Qty. Split', PrintName='Qty. Split',Updated=TO_TIMESTAMP('2010-03-29 16:29:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53523
;

-- Mar 29, 2010 4:29:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53523
;

-- Mar 29, 2010 4:29:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_QTY_Split', Name='Qty. Split', Description=NULL, Help=NULL WHERE AD_Element_ID=53523
;

-- Mar 29, 2010 4:29:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_QTY_Split', Name='Qty. Split', Description=NULL, Help=NULL, AD_Element_ID=53523 WHERE UPPER(ColumnName)='A_QTY_SPLIT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:29:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_QTY_Split', Name='Qty. Split', Description=NULL, Help=NULL WHERE AD_Element_ID=53523 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:29:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Qty. Split', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53523) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:29:54 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Qty. Split', Name='Qty. Split' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53523)
;

-- Mar 29, 2010 4:30:11 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Reval. Code', PrintName='Reval. Code',Updated=TO_TIMESTAMP('2010-03-29 16:30:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53515
;

-- Mar 29, 2010 4:30:11 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53515
;

-- Mar 29, 2010 4:30:11 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Reval_Code', Name='Reval. Code', Description=NULL, Help=NULL WHERE AD_Element_ID=53515
;

-- Mar 29, 2010 4:30:11 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Reval_Code', Name='Reval. Code', Description=NULL, Help=NULL, AD_Element_ID=53515 WHERE UPPER(ColumnName)='A_REVAL_CODE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:30:11 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Reval_Code', Name='Reval. Code', Description=NULL, Help=NULL WHERE AD_Element_ID=53515 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:30:11 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Reval. Code', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53515) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:30:11 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Reval. Code', Name='Reval. Code' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53515)
;

-- Mar 29, 2010 4:30:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Reval. Effective Date', PrintName='Reval. Effective Date',Updated=TO_TIMESTAMP('2010-03-29 16:30:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53511
;

-- Mar 29, 2010 4:30:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53511
;

-- Mar 29, 2010 4:30:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Reval_Effective_Date', Name='Reval. Effective Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53511
;

-- Mar 29, 2010 4:30:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Reval_Effective_Date', Name='Reval. Effective Date', Description=NULL, Help=NULL, AD_Element_ID=53511 WHERE UPPER(ColumnName)='A_REVAL_EFFECTIVE_DATE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:30:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Reval_Effective_Date', Name='Reval. Effective Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53511 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:30:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Reval. Effective Date', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53511) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:30:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Reval. Effective Date', Name='Reval. Effective Date' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53511)
;

-- Mar 29, 2010 4:31:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Reval. Multiplier', PrintName='Reval. Multiplier',Updated=TO_TIMESTAMP('2010-03-29 16:31:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53510
;

-- Mar 29, 2010 4:31:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53510
;

-- Mar 29, 2010 4:31:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Reval_Multiplier', Name='Reval. Multiplier', Description=NULL, Help=NULL WHERE AD_Element_ID=53510
;

-- Mar 29, 2010 4:31:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Reval_Multiplier', Name='Reval. Multiplier', Description=NULL, Help=NULL, AD_Element_ID=53510 WHERE UPPER(ColumnName)='A_REVAL_MULTIPLIER' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:31:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Reval_Multiplier', Name='Reval. Multiplier', Description=NULL, Help=NULL WHERE AD_Element_ID=53510 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:31:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Reval. Multiplier', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53510) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:31:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Reval. Multiplier', Name='Reval. Multiplier' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53510)
;

-- Mar 29, 2010 4:31:11 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Reval. Rate', PrintName='Reval. Rate',Updated=TO_TIMESTAMP('2010-03-29 16:31:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53514
;

-- Mar 29, 2010 4:31:11 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53514
;

-- Mar 29, 2010 4:31:11 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Reval_Rate', Name='Reval. Rate', Description=NULL, Help=NULL WHERE AD_Element_ID=53514
;

-- Mar 29, 2010 4:31:11 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Reval_Rate', Name='Reval. Rate', Description=NULL, Help=NULL, AD_Element_ID=53514 WHERE UPPER(ColumnName)='A_REVAL_RATE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:31:11 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Reval_Rate', Name='Reval. Rate', Description=NULL, Help=NULL WHERE AD_Element_ID=53514 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:31:11 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Reval. Rate', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53514) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:31:11 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Reval. Rate', Name='Reval. Rate' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53514)
;

-- Mar 29, 2010 4:31:21 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Rev. Code', PrintName='Rev. Code',Updated=TO_TIMESTAMP('2010-03-29 16:31:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53512
;

-- Mar 29, 2010 4:31:21 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53512
;

-- Mar 29, 2010 4:31:21 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Rev_Code', Name='Rev. Code', Description=NULL, Help=NULL WHERE AD_Element_ID=53512
;

-- Mar 29, 2010 4:31:21 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Rev_Code', Name='Rev. Code', Description=NULL, Help=NULL, AD_Element_ID=53512 WHERE UPPER(ColumnName)='A_REV_CODE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:31:21 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Rev_Code', Name='Rev. Code', Description=NULL, Help=NULL WHERE AD_Element_ID=53512 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:31:21 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Rev. Code', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53512) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:31:21 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Rev. Code', Name='Rev. Code' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53512)
;

-- Mar 29, 2010 4:31:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Accum. Depreciation Amt.', PrintName='Asset Accum. Depreciation Amt.',Updated=TO_TIMESTAMP('2010-03-29 16:31:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53605
;

-- Mar 29, 2010 4:31:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53605
;

-- Mar 29, 2010 4:31:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AssetAccumDepreciationAmt', Name='Asset Accum. Depreciation Amt.', Description=NULL, Help=NULL WHERE AD_Element_ID=53605
;

-- Mar 29, 2010 4:31:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AssetAccumDepreciationAmt', Name='Asset Accum. Depreciation Amt.', Description=NULL, Help=NULL, AD_Element_ID=53605 WHERE UPPER(ColumnName)='ASSETACCUMDEPRECIATIONAMT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:31:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AssetAccumDepreciationAmt', Name='Asset Accum. Depreciation Amt.', Description=NULL, Help=NULL WHERE AD_Element_ID=53605 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:31:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Accum. Depreciation Amt.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53605) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:31:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Accum. Depreciation Amt.', Name='Asset Accum. Depreciation Amt.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53605)
;

-- Mar 29, 2010 4:32:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Asset Book value amt.', PrintName='Asset Book value amt.',Updated=TO_TIMESTAMP('2010-03-29 16:32:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53604
;

-- Mar 29, 2010 4:32:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53604
;

-- Mar 29, 2010 4:32:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='AssetBookValueAmt', Name='Asset Book value amt.', Description=NULL, Help=NULL WHERE AD_Element_ID=53604
;

-- Mar 29, 2010 4:32:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AssetBookValueAmt', Name='Asset Book value amt.', Description=NULL, Help=NULL, AD_Element_ID=53604 WHERE UPPER(ColumnName)='ASSETBOOKVALUEAMT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:32:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='AssetBookValueAmt', Name='Asset Book value amt.', Description=NULL, Help=NULL WHERE AD_Element_ID=53604 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:32:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Asset Book value amt.', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53604) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:32:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Asset Book value amt.', Name='Asset Book value amt.' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53604)
;

-- Mar 29, 2010 4:32:22 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Start Asset', PrintName='Start Asset',Updated=TO_TIMESTAMP('2010-03-29 16:32:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53506
;

-- Mar 29, 2010 4:32:22 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53506
;

-- Mar 29, 2010 4:32:22 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_Start_Asset_ID', Name='Start Asset', Description=NULL, Help=NULL WHERE AD_Element_ID=53506
;

-- Mar 29, 2010 4:32:22 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Start_Asset_ID', Name='Start Asset', Description=NULL, Help=NULL, AD_Element_ID=53506 WHERE UPPER(ColumnName)='A_START_ASSET_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:32:22 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_Start_Asset_ID', Name='Start Asset', Description=NULL, Help=NULL WHERE AD_Element_ID=53506 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:32:22 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Start Asset', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53506) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:32:22 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Start Asset', Name='Start Asset' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53506)
;

-- Mar 29, 2010 4:32:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User 1', PrintName='User 1',Updated=TO_TIMESTAMP('2010-03-29 16:32:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53625
;

-- Mar 29, 2010 4:32:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53625
;

-- Mar 29, 2010 4:32:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_User1', Name='User 1', Description=NULL, Help=NULL WHERE AD_Element_ID=53625
;

-- Mar 29, 2010 4:32:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User1', Name='User 1', Description=NULL, Help=NULL, AD_Element_ID=53625 WHERE UPPER(ColumnName)='A_USER1' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:32:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User1', Name='User 1', Description=NULL, Help=NULL WHERE AD_Element_ID=53625 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:32:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User 1', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53625) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:32:38 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User 1', Name='User 1' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53625)
;

-- Mar 29, 2010 4:32:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User 10', PrintName='User 10',Updated=TO_TIMESTAMP('2010-03-29 16:32:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53626
;

-- Mar 29, 2010 4:32:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53626
;

-- Mar 29, 2010 4:32:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_User10', Name='User 10', Description=NULL, Help=NULL WHERE AD_Element_ID=53626
;

-- Mar 29, 2010 4:32:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User10', Name='User 10', Description=NULL, Help=NULL, AD_Element_ID=53626 WHERE UPPER(ColumnName)='A_USER10' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:32:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User10', Name='User 10', Description=NULL, Help=NULL WHERE AD_Element_ID=53626 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:32:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User 10', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53626) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:32:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User 10', Name='User 10' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53626)
;

-- Mar 29, 2010 4:32:57 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User 11', PrintName='User 11',Updated=TO_TIMESTAMP('2010-03-29 16:32:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53627
;

-- Mar 29, 2010 4:32:57 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53627
;

-- Mar 29, 2010 4:32:57 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_User11', Name='User 11', Description=NULL, Help=NULL WHERE AD_Element_ID=53627
;

-- Mar 29, 2010 4:32:57 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User11', Name='User 11', Description=NULL, Help=NULL, AD_Element_ID=53627 WHERE UPPER(ColumnName)='A_USER11' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:32:57 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User11', Name='User 11', Description=NULL, Help=NULL WHERE AD_Element_ID=53627 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:32:57 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User 11', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53627) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:32:57 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User 11', Name='User 11' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53627)
;

-- Mar 29, 2010 4:33:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User 12', PrintName='User 12',Updated=TO_TIMESTAMP('2010-03-29 16:33:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53628
;

-- Mar 29, 2010 4:33:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53628
;

-- Mar 29, 2010 4:33:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_User12', Name='User 12', Description=NULL, Help=NULL WHERE AD_Element_ID=53628
;

-- Mar 29, 2010 4:33:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User12', Name='User 12', Description=NULL, Help=NULL, AD_Element_ID=53628 WHERE UPPER(ColumnName)='A_USER12' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:33:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User12', Name='User 12', Description=NULL, Help=NULL WHERE AD_Element_ID=53628 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User 12', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53628) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:04 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User 12', Name='User 12' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53628)
;

-- Mar 29, 2010 4:33:12 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User 13', PrintName='User 13',Updated=TO_TIMESTAMP('2010-03-29 16:33:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53629
;

-- Mar 29, 2010 4:33:12 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53629
;

-- Mar 29, 2010 4:33:12 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_User13', Name='User 13', Description=NULL, Help=NULL WHERE AD_Element_ID=53629
;

-- Mar 29, 2010 4:33:12 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User13', Name='User 13', Description=NULL, Help=NULL, AD_Element_ID=53629 WHERE UPPER(ColumnName)='A_USER13' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:33:12 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User13', Name='User 13', Description=NULL, Help=NULL WHERE AD_Element_ID=53629 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:12 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User 13', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53629) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:12 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User 13', Name='User 13' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53629)
;

-- Mar 29, 2010 4:33:19 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User 14', PrintName='User 14',Updated=TO_TIMESTAMP('2010-03-29 16:33:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53630
;

-- Mar 29, 2010 4:33:19 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53630
;

-- Mar 29, 2010 4:33:19 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_User14', Name='User 14', Description=NULL, Help=NULL WHERE AD_Element_ID=53630
;

-- Mar 29, 2010 4:33:19 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User14', Name='User 14', Description=NULL, Help=NULL, AD_Element_ID=53630 WHERE UPPER(ColumnName)='A_USER14' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:33:19 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User14', Name='User 14', Description=NULL, Help=NULL WHERE AD_Element_ID=53630 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:19 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User 14', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53630) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:19 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User 14', Name='User 14' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53630)
;

-- Mar 29, 2010 4:33:26 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User 15', PrintName='User 15',Updated=TO_TIMESTAMP('2010-03-29 16:33:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53631
;

-- Mar 29, 2010 4:33:26 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53631
;

-- Mar 29, 2010 4:33:26 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_User15', Name='User 15', Description=NULL, Help=NULL WHERE AD_Element_ID=53631
;

-- Mar 29, 2010 4:33:26 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User15', Name='User 15', Description=NULL, Help=NULL, AD_Element_ID=53631 WHERE UPPER(ColumnName)='A_USER15' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:33:26 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User15', Name='User 15', Description=NULL, Help=NULL WHERE AD_Element_ID=53631 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:26 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User 15', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53631) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:27 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User 15', Name='User 15' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53631)
;

-- Mar 29, 2010 4:33:33 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User 2', PrintName='User 2',Updated=TO_TIMESTAMP('2010-03-29 16:33:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53632
;

-- Mar 29, 2010 4:33:33 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53632
;

-- Mar 29, 2010 4:33:33 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_User2', Name='User 2', Description=NULL, Help=NULL WHERE AD_Element_ID=53632
;

-- Mar 29, 2010 4:33:33 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User2', Name='User 2', Description=NULL, Help=NULL, AD_Element_ID=53632 WHERE UPPER(ColumnName)='A_USER2' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:33:33 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User2', Name='User 2', Description=NULL, Help=NULL WHERE AD_Element_ID=53632 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:33 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User 2', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53632) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:33 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User 2', Name='User 2' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53632)
;

-- Mar 29, 2010 4:33:39 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User 3', PrintName='User 3',Updated=TO_TIMESTAMP('2010-03-29 16:33:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53633
;

-- Mar 29, 2010 4:33:39 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53633
;

-- Mar 29, 2010 4:33:39 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_User3', Name='User 3', Description=NULL, Help=NULL WHERE AD_Element_ID=53633
;

-- Mar 29, 2010 4:33:39 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User3', Name='User 3', Description=NULL, Help=NULL, AD_Element_ID=53633 WHERE UPPER(ColumnName)='A_USER3' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:33:39 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User3', Name='User 3', Description=NULL, Help=NULL WHERE AD_Element_ID=53633 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:39 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User 3', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53633) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:39 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User 3', Name='User 3' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53633)
;

-- Mar 29, 2010 4:33:45 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User 4', PrintName='User 4',Updated=TO_TIMESTAMP('2010-03-29 16:33:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53634
;

-- Mar 29, 2010 4:33:45 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53634
;

-- Mar 29, 2010 4:33:45 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_User4', Name='User 4', Description=NULL, Help=NULL WHERE AD_Element_ID=53634
;

-- Mar 29, 2010 4:33:45 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User4', Name='User 4', Description=NULL, Help=NULL, AD_Element_ID=53634 WHERE UPPER(ColumnName)='A_USER4' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:33:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User4', Name='User 4', Description=NULL, Help=NULL WHERE AD_Element_ID=53634 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User 4', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53634) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User 4', Name='User 4' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53634)
;

-- Mar 29, 2010 4:33:55 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User 5', PrintName='User 5',Updated=TO_TIMESTAMP('2010-03-29 16:33:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53635
;

-- Mar 29, 2010 4:33:55 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53635
;

-- Mar 29, 2010 4:33:55 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_User5', Name='User 5', Description=NULL, Help=NULL WHERE AD_Element_ID=53635
;

-- Mar 29, 2010 4:33:55 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User5', Name='User 5', Description=NULL, Help=NULL, AD_Element_ID=53635 WHERE UPPER(ColumnName)='A_USER5' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:33:55 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User5', Name='User 5', Description=NULL, Help=NULL WHERE AD_Element_ID=53635 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:55 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User 5', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53635) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:33:55 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User 5', Name='User 5' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53635)
;

-- Mar 29, 2010 4:34:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User 6', PrintName='User 6',Updated=TO_TIMESTAMP('2010-03-29 16:34:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53636
;

-- Mar 29, 2010 4:34:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53636
;

-- Mar 29, 2010 4:34:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_User6', Name='User 6', Description=NULL, Help=NULL WHERE AD_Element_ID=53636
;

-- Mar 29, 2010 4:34:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User6', Name='User 6', Description=NULL, Help=NULL, AD_Element_ID=53636 WHERE UPPER(ColumnName)='A_USER6' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:34:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User6', Name='User 6', Description=NULL, Help=NULL WHERE AD_Element_ID=53636 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:34:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User 6', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53636) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:34:01 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User 6', Name='User 6' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53636)
;

-- Mar 29, 2010 4:34:09 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User 7', PrintName='User 7',Updated=TO_TIMESTAMP('2010-03-29 16:34:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53637
;

-- Mar 29, 2010 4:34:09 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53637
;

-- Mar 29, 2010 4:34:09 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_User7', Name='User 7', Description=NULL, Help=NULL WHERE AD_Element_ID=53637
;

-- Mar 29, 2010 4:34:09 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User7', Name='User 7', Description=NULL, Help=NULL, AD_Element_ID=53637 WHERE UPPER(ColumnName)='A_USER7' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:34:09 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User7', Name='User 7', Description=NULL, Help=NULL WHERE AD_Element_ID=53637 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:34:09 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User 7', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53637) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:34:09 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User 7', Name='User 7' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53637)
;

-- Mar 29, 2010 4:34:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User 8', PrintName='User 8',Updated=TO_TIMESTAMP('2010-03-29 16:34:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53638
;

-- Mar 29, 2010 4:34:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53638
;

-- Mar 29, 2010 4:34:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_User8', Name='User 8', Description=NULL, Help=NULL WHERE AD_Element_ID=53638
;

-- Mar 29, 2010 4:34:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User8', Name='User 8', Description=NULL, Help=NULL, AD_Element_ID=53638 WHERE UPPER(ColumnName)='A_USER8' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:34:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User8', Name='User 8', Description=NULL, Help=NULL WHERE AD_Element_ID=53638 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:34:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User 8', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53638) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:34:15 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User 8', Name='User 8' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53638)
;

-- Mar 29, 2010 4:34:22 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User 9', PrintName='User 9',Updated=TO_TIMESTAMP('2010-03-29 16:34:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53639
;

-- Mar 29, 2010 4:34:22 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53639
;

-- Mar 29, 2010 4:34:22 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='A_User9', Name='User 9', Description=NULL, Help=NULL WHERE AD_Element_ID=53639
;

-- Mar 29, 2010 4:34:22 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User9', Name='User 9', Description=NULL, Help=NULL, AD_Element_ID=53639 WHERE UPPER(ColumnName)='A_USER9' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:34:22 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='A_User9', Name='User 9', Description=NULL, Help=NULL WHERE AD_Element_ID=53639 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:34:22 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User 9', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53639) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:34:22 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User 9', Name='User 9' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53639)
;

-- Mar 29, 2010 4:34:40 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Binary Data',Updated=TO_TIMESTAMP('2010-03-29 16:34:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=174
;

-- Mar 29, 2010 4:34:40 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=174
;

-- Mar 29, 2010 4:34:40 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='BinaryData', Name='Binary Data', Description='Binary Data', Help='The Binary field stores binary data.' WHERE AD_Element_ID=174
;

-- Mar 29, 2010 4:34:40 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='BinaryData', Name='Binary Data', Description='Binary Data', Help='The Binary field stores binary data.', AD_Element_ID=174 WHERE UPPER(ColumnName)='BINARYDATA' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:34:40 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='BinaryData', Name='Binary Data', Description='Binary Data', Help='The Binary field stores binary data.' WHERE AD_Element_ID=174 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:34:40 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Binary Data', Description='Binary Data', Help='The Binary field stores binary data.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=174) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:34:40 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Binary', Name='Binary Data' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=174)
;

-- Mar 29, 2010 4:35:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Date Confirm',Updated=TO_TIMESTAMP('2010-03-29 16:35:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53277
;

-- Mar 29, 2010 4:35:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53277
;

-- Mar 29, 2010 4:35:30 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='DateConfirm', Name='Date Confirm', Description='Date Confirm of this Order', Help=NULL WHERE AD_Element_ID=53277
;

-- Mar 29, 2010 4:35:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='DateConfirm', Name='Date Confirm', Description='Date Confirm of this Order', Help=NULL, AD_Element_ID=53277 WHERE UPPER(ColumnName)='DATECONFIRM' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:35:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='DateConfirm', Name='Date Confirm', Description='Date Confirm of this Order', Help=NULL WHERE AD_Element_ID=53277 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:35:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Date Confirm', Description='Date Confirm of this Order', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53277) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:35:31 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Date Confirm', Name='Date Confirm' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53277)
;

-- Mar 29, 2010 4:36:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Find', PrintName='Find',Updated=TO_TIMESTAMP('2010-03-29 16:36:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1453
;

-- Mar 29, 2010 4:36:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1453
;

-- Mar 29, 2010 4:36:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='Find_ID', Name='Find', Description=NULL, Help=NULL WHERE AD_Element_ID=1453
;

-- Mar 29, 2010 4:36:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='Find_ID', Name='Find', Description=NULL, Help=NULL, AD_Element_ID=1453 WHERE UPPER(ColumnName)='FIND_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:36:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='Find_ID', Name='Find', Description=NULL, Help=NULL WHERE AD_Element_ID=1453 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:36:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Find', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=1453) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:36:36 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Find', Name='Find' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=1453)
;

-- Mar 29, 2010 4:38:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Node', PrintName='Node',Updated=TO_TIMESTAMP('2010-03-29 16:38:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=472
;

-- Mar 29, 2010 4:38:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=472
;

-- Mar 29, 2010 4:38:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='Node_ID', Name='Node', Description=NULL, Help=NULL WHERE AD_Element_ID=472
;

-- Mar 29, 2010 4:38:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='Node_ID', Name='Node', Description=NULL, Help=NULL, AD_Element_ID=472 WHERE UPPER(ColumnName)='NODE_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:38:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='Node_ID', Name='Node', Description=NULL, Help=NULL WHERE AD_Element_ID=472 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:38:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Node', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=472) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:38:46 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Node', Name='Node' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=472)
;

-- Mar 29, 2010 4:39:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Additional Zip', PrintName='Additional Zip',Updated=TO_TIMESTAMP('2010-03-29 16:39:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=513
;

-- Mar 29, 2010 4:39:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=513
;

-- Mar 29, 2010 4:39:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='Postal_Add', Name='Additional Zip', Description='Additional ZIP or Postal code', Help='The Additional ZIP or Postal Code identifies, if appropriate, any additional Postal Code information.' WHERE AD_Element_ID=513
;

-- Mar 29, 2010 4:39:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='Postal_Add', Name='Additional Zip', Description='Additional ZIP or Postal code', Help='The Additional ZIP or Postal Code identifies, if appropriate, any additional Postal Code information.', AD_Element_ID=513 WHERE UPPER(ColumnName)='POSTAL_ADD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:39:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='Postal_Add', Name='Additional Zip', Description='Additional ZIP or Postal code', Help='The Additional ZIP or Postal Code identifies, if appropriate, any additional Postal Code information.' WHERE AD_Element_ID=513 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:39:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Additional Zip', Description='Additional ZIP or Postal code', Help='The Additional ZIP or Postal Code identifies, if appropriate, any additional Postal Code information.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=513) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:39:49 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Additional Zip', Name='Additional Zip' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=513)
;

-- Mar 29, 2010 4:40:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='QM Specification Line', PrintName='QM Specification Line',Updated=TO_TIMESTAMP('2010-03-29 16:40:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53315
;

-- Mar 29, 2010 4:40:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53315
;

-- Mar 29, 2010 4:40:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='QM_SpecificationLine_ID', Name='QM Specification Line', Description=NULL, Help=NULL WHERE AD_Element_ID=53315
;

-- Mar 29, 2010 4:40:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='QM_SpecificationLine_ID', Name='QM Specification Line', Description=NULL, Help=NULL, AD_Element_ID=53315 WHERE UPPER(ColumnName)='QM_SPECIFICATIONLINE_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:40:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='QM_SpecificationLine_ID', Name='QM Specification Line', Description=NULL, Help=NULL WHERE AD_Element_ID=53315 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:40:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='QM Specification Line', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53315) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:40:17 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='QM Specification Line', Name='QM Specification Line' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53315)
;

-- Mar 29, 2010 4:41:07 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='User PIN', PrintName='User PIN',Updated=TO_TIMESTAMP('2010-03-29 16:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=52023
;

-- Mar 29, 2010 4:41:07 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=52023
;

-- Mar 29, 2010 4:41:07 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='UserPIN', Name='User PIN', Description=NULL, Help=NULL WHERE AD_Element_ID=52023
;

-- Mar 29, 2010 4:41:07 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='UserPIN', Name='User PIN', Description=NULL, Help=NULL, AD_Element_ID=52023 WHERE UPPER(ColumnName)='USERPIN' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:41:07 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='UserPIN', Name='User PIN', Description=NULL, Help=NULL WHERE AD_Element_ID=52023 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:41:07 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='User PIN', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=52023) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:41:07 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='User PIN', Name='User PIN' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=52023)
;

-- Mar 29, 2010 4:41:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Date', PrintName='Date',Updated=TO_TIMESTAMP('2010-03-29 16:41:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1484
;

-- Mar 29, 2010 4:41:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1484
;

-- Mar 29, 2010 4:41:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='V_Date', Name='Date', Description=NULL, Help=NULL WHERE AD_Element_ID=1484
;

-- Mar 29, 2010 4:41:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='V_Date', Name='Date', Description=NULL, Help=NULL, AD_Element_ID=1484 WHERE UPPER(ColumnName)='V_DATE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:41:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='V_Date', Name='Date', Description=NULL, Help=NULL WHERE AD_Element_ID=1484 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:41:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Date', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=1484) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:41:18 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Date', Name='Date' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=1484)
;

-- Mar 29, 2010 4:42:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Number', PrintName='Number',Updated=TO_TIMESTAMP('2010-03-29 16:42:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1485
;

-- Mar 29, 2010 4:42:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1485
;

-- Mar 29, 2010 4:42:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='V_Number', Name='Number', Description=NULL, Help=NULL WHERE AD_Element_ID=1485
;

-- Mar 29, 2010 4:42:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='V_Number', Name='Number', Description=NULL, Help=NULL, AD_Element_ID=1485 WHERE UPPER(ColumnName)='V_NUMBER' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:42:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='V_Number', Name='Number', Description=NULL, Help=NULL WHERE AD_Element_ID=1485 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:42:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Number', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=1485) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:42:03 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Number', Name='Number' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=1485)
;

-- Mar 29, 2010 4:42:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='String', PrintName='String',Updated=TO_TIMESTAMP('2010-03-29 16:42:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1486
;

-- Mar 29, 2010 4:42:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1486
;

-- Mar 29, 2010 4:42:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='V_String', Name='String', Description=NULL, Help=NULL WHERE AD_Element_ID=1486
;

-- Mar 29, 2010 4:42:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='V_String', Name='String', Description=NULL, Help=NULL, AD_Element_ID=1486 WHERE UPPER(ColumnName)='V_STRING' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:42:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='V_String', Name='String', Description=NULL, Help=NULL WHERE AD_Element_ID=1486 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:42:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='String', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=1486) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:42:43 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='String', Name='String' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=1486)
;

-- Mar 29, 2010 4:42:59 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element SET Name='Basket', PrintName='Basket',Updated=TO_TIMESTAMP('2010-03-29 16:42:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1446
;

-- Mar 29, 2010 4:42:59 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1446
;

-- Mar 29, 2010 4:42:59 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Column SET ColumnName='W_Basket_ID', Name='Basket', Description='Web Basket', Help='Temporary Web Basket' WHERE AD_Element_ID=1446
;

-- Mar 29, 2010 4:42:59 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='W_Basket_ID', Name='Basket', Description='Web Basket', Help='Temporary Web Basket', AD_Element_ID=1446 WHERE UPPER(ColumnName)='W_BASKET_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 29, 2010 4:42:59 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Process_Para SET ColumnName='W_Basket_ID', Name='Basket', Description='Web Basket', Help='Temporary Web Basket' WHERE AD_Element_ID=1446 AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:42:59 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_Field SET Name='Basket', Description='Web Basket', Help='Temporary Web Basket' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=1446) AND IsCentrallyMaintained='Y'
;

-- Mar 29, 2010 4:42:59 PM CEST
-- BF [2913291] - Fixed Assets Application dictionary stabilization
UPDATE AD_PrintFormatItem SET PrintName='Basket', Name='Basket' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=1446)
;

