-- Nov 12, 2008 4:08:48 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Depreciation Calculation Type', PrintName='Depreciation Calculation Type',Updated=TO_TIMESTAMP('2008-11-12 16:08:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53588
;

-- Nov 12, 2008 4:08:48 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53588
;

-- Nov 12, 2008 4:08:48 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Depreciation_Calc_Type', Name='Depreciation Calculation Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53588
;

-- Nov 12, 2008 4:08:48 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Calc_Type', Name='Depreciation Calculation Type', Description=NULL, Help=NULL, AD_Element_ID=53588 WHERE UPPER(ColumnName)='A_DEPRECIATION_CALC_TYPE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:08:48 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Calc_Type', Name='Depreciation Calculation Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53588 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:08:48 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Depreciation Calculation Type', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53588) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:08:48 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Calculation Type', Name='Depreciation Calculation Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53588)
;

-- Nov 12, 2008 4:17:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Split Percentage', PrintName='Split Percentage',Updated=TO_TIMESTAMP('2008-11-12 16:17:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53541
;

-- Nov 12, 2008 4:17:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53541
;

-- Nov 12, 2008 4:17:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Split_Percent', Name='Split Percentage', Description=NULL, Help=NULL WHERE AD_Element_ID=53541
;

-- Nov 12, 2008 4:17:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Split_Percent', Name='Split Percentage', Description=NULL, Help=NULL, AD_Element_ID=53541 WHERE UPPER(ColumnName)='A_SPLIT_PERCENT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:17:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Split_Percent', Name='Split Percentage', Description=NULL, Help=NULL WHERE AD_Element_ID=53541 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:17:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Split Percentage', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53541) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:17:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Split Percentage', Name='Split Percentage' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53541)
;

-- Nov 12, 2008 4:17:55 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Asset Cost Account', PrintName='Asset Cost Account',Updated=TO_TIMESTAMP('2008-11-12 16:17:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53549
;

-- Nov 12, 2008 4:17:55 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53549
;

-- Nov 12, 2008 4:17:55 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Asset_Acct', Name='Asset Cost Account', Description=NULL, Help=NULL WHERE AD_Element_ID=53549
;

-- Nov 12, 2008 4:17:55 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Asset_Acct', Name='Asset Cost Account', Description=NULL, Help=NULL, AD_Element_ID=53549 WHERE UPPER(ColumnName)='A_ASSET_ACCT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:17:55 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Asset_Acct', Name='Asset Cost Account', Description=NULL, Help=NULL WHERE AD_Element_ID=53549 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:17:55 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Asset Cost Account', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53549) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:17:55 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Asset Cost Account', Name='Asset Cost Account' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53549)
;

-- Nov 12, 2008 4:20:14 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Accumulated Depreciation', PrintName='Accumulated Depreciation',Updated=TO_TIMESTAMP('2008-11-12 16:20:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53548
;

-- Nov 12, 2008 4:20:14 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53548
;

-- Nov 12, 2008 4:20:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Accumdepreciation_Acct', Name='Accumulated Depreciation', Description=NULL, Help=NULL WHERE AD_Element_ID=53548
;

-- Nov 12, 2008 4:20:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Accumdepreciation_Acct', Name='Accumulated Depreciation', Description=NULL, Help=NULL, AD_Element_ID=53548 WHERE UPPER(ColumnName)='A_ACCUMDEPRECIATION_ACCT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:20:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Accumdepreciation_Acct', Name='Accumulated Depreciation', Description=NULL, Help=NULL WHERE AD_Element_ID=53548 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:20:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Accumulated Depreciation', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53548) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:20:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Accumulated Depreciation', Name='Accumulated Depreciation' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53548)
;

-- Nov 12, 2008 4:20:37 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Depreciation Expense Account', PrintName='Depreciation Expense Account',Updated=TO_TIMESTAMP('2008-11-12 16:20:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53530
;

-- Nov 12, 2008 4:20:37 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53530
;

-- Nov 12, 2008 4:20:37 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Depreciation_Acct', Name='Depreciation Expense Account', Description=NULL, Help=NULL WHERE AD_Element_ID=53530
;

-- Nov 12, 2008 4:20:37 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Acct', Name='Depreciation Expense Account', Description=NULL, Help=NULL, AD_Element_ID=53530 WHERE UPPER(ColumnName)='A_DEPRECIATION_ACCT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:20:37 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Acct', Name='Depreciation Expense Account', Description=NULL, Help=NULL WHERE AD_Element_ID=53530 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:20:37 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Depreciation Expense Account', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53530) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:20:37 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Expense Account', Name='Depreciation Expense Account' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53530)
;

-- Nov 12, 2008 4:21:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Disposal Revenue', PrintName='Disposal Revenue',Updated=TO_TIMESTAMP('2008-11-12 16:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53539
;

-- Nov 12, 2008 4:21:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53539
;

-- Nov 12, 2008 4:21:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Disposal_Revenue', Name='Disposal Revenue', Description=NULL, Help=NULL WHERE AD_Element_ID=53539
;

-- Nov 12, 2008 4:21:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposal_Revenue', Name='Disposal Revenue', Description=NULL, Help=NULL, AD_Element_ID=53539 WHERE UPPER(ColumnName)='A_DISPOSAL_REVENUE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:21:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposal_Revenue', Name='Disposal Revenue', Description=NULL, Help=NULL WHERE AD_Element_ID=53539 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:21:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Disposal Revenue', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53539) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:21:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Disposal Revenue', Name='Disposal Revenue' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53539)
;

-- Nov 12, 2008 4:21:18 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Loss on Disposal', PrintName='Loss on Disposal',Updated=TO_TIMESTAMP('2008-11-12 16:21:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53540
;

-- Nov 12, 2008 4:21:18 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53540
;

-- Nov 12, 2008 4:21:18 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Disposal_Loss', Name='Loss on Disposal', Description=NULL, Help=NULL WHERE AD_Element_ID=53540
;

-- Nov 12, 2008 4:21:18 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposal_Loss', Name='Loss on Disposal', Description=NULL, Help=NULL, AD_Element_ID=53540 WHERE UPPER(ColumnName)='A_DISPOSAL_LOSS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:21:18 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposal_Loss', Name='Loss on Disposal', Description=NULL, Help=NULL WHERE AD_Element_ID=53540 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:21:18 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Loss on Disposal', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53540) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:21:18 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Loss on Disposal', Name='Loss on Disposal' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53540)
;

-- Nov 12, 2008 4:21:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Revaluation Calculation Method', PrintName='Revaluation Calculation Method',Updated=TO_TIMESTAMP('2008-11-12 16:21:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53509
;

-- Nov 12, 2008 4:21:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53509
;

-- Nov 12, 2008 4:21:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Reval_Cal_Method', Name='Revaluation Calculation Method', Description=NULL, Help=NULL WHERE AD_Element_ID=53509
;

-- Nov 12, 2008 4:21:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Reval_Cal_Method', Name='Revaluation Calculation Method', Description=NULL, Help=NULL, AD_Element_ID=53509 WHERE UPPER(ColumnName)='A_REVAL_CAL_METHOD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:21:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Reval_Cal_Method', Name='Revaluation Calculation Method', Description=NULL, Help=NULL WHERE AD_Element_ID=53509 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:21:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Revaluation Calculation Method', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53509) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:21:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Revaluation Calculation Method', Name='Revaluation Calculation Method' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53509)
;

-- Nov 12, 2008 4:22:10 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Revaluation Cost Offset for Current Year', PrintName='Revaluation Cost Offset for Current Year',Updated=TO_TIMESTAMP('2008-11-12 16:22:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53534
;

-- Nov 12, 2008 4:22:10 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53534
;

-- Nov 12, 2008 4:22:10 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Reval_Cost_Offset', Name='Revaluation Cost Offset for Current Year', Description=NULL, Help=NULL WHERE AD_Element_ID=53534
;

-- Nov 12, 2008 4:22:10 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Reval_Cost_Offset', Name='Revaluation Cost Offset for Current Year', Description=NULL, Help=NULL, AD_Element_ID=53534 WHERE UPPER(ColumnName)='A_REVAL_COST_OFFSET' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:22:10 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Reval_Cost_Offset', Name='Revaluation Cost Offset for Current Year', Description=NULL, Help=NULL WHERE AD_Element_ID=53534 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:22:10 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Revaluation Cost Offset for Current Year', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53534) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:22:10 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Revaluation Cost Offset for Current Year', Name='Revaluation Cost Offset for Current Year' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53534)
;

-- Nov 12, 2008 4:22:35 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Revaluation Cost Offset for Prior Year', PrintName='Revaluation Cost Offset for Prior Year',Updated=TO_TIMESTAMP('2008-11-12 16:22:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53533
;

-- Nov 12, 2008 4:22:35 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53533
;

-- Nov 12, 2008 4:22:35 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Reval_Cost_Offset_Prior', Name='Revaluation Cost Offset for Prior Year', Description=NULL, Help=NULL WHERE AD_Element_ID=53533
;

-- Nov 12, 2008 4:22:35 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Reval_Cost_Offset_Prior', Name='Revaluation Cost Offset for Prior Year', Description=NULL, Help=NULL, AD_Element_ID=53533 WHERE UPPER(ColumnName)='A_REVAL_COST_OFFSET_PRIOR' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:22:35 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Reval_Cost_Offset_Prior', Name='Revaluation Cost Offset for Prior Year', Description=NULL, Help=NULL WHERE AD_Element_ID=53533 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:22:35 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Revaluation Cost Offset for Prior Year', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53533) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:22:35 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Revaluation Cost Offset for Prior Year', Name='Revaluation Cost Offset for Prior Year' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53533)
;

-- Nov 12, 2008 4:24:09 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Revaluation Accumulated Depreciation Offset for Current Year', PrintName='Revaluation Accumulated Depreciation Offset for Current Year',Updated=TO_TIMESTAMP('2008-11-12 16:24:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53536
;

-- Nov 12, 2008 4:24:09 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53536
;

-- Nov 12, 2008 4:24:09 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Reval_Accumdep_Offset_Cur', Name='Revaluation Accumulated Depreciation Offset for Current Year', Description=NULL, Help=NULL WHERE AD_Element_ID=53536
;

-- Nov 12, 2008 4:24:09 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Reval_Accumdep_Offset_Cur', Name='Revaluation Accumulated Depreciation Offset for Current Year', Description=NULL, Help=NULL, AD_Element_ID=53536 WHERE UPPER(ColumnName)='A_REVAL_ACCUMDEP_OFFSET_CUR' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:24:09 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Reval_Accumdep_Offset_Cur', Name='Revaluation Accumulated Depreciation Offset for Current Year', Description=NULL, Help=NULL WHERE AD_Element_ID=53536 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:24:09 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Revaluation Accumulated Depreciation Offset for Current Year', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53536) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:24:09 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Revaluation Accumulated Depreciation Offset for Current Year', Name='Revaluation Accumulated Depreciation Offset for Current Year' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53536)
;

-- Nov 12, 2008 4:24:31 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Revaluation Accumulated Depreciation Offset for Prior Year', PrintName='Revaluation Accumulated Depreciation Offset for Prior Year',Updated=TO_TIMESTAMP('2008-11-12 16:24:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53535
;

-- Nov 12, 2008 4:24:31 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53535
;

-- Nov 12, 2008 4:24:31 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Reval_Accumdep_Offset_Prior', Name='Revaluation Accumulated Depreciation Offset for Prior Year', Description=NULL, Help=NULL WHERE AD_Element_ID=53535
;

-- Nov 12, 2008 4:24:31 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Reval_Accumdep_Offset_Prior', Name='Revaluation Accumulated Depreciation Offset for Prior Year', Description=NULL, Help=NULL, AD_Element_ID=53535 WHERE UPPER(ColumnName)='A_REVAL_ACCUMDEP_OFFSET_PRIOR' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:24:31 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Reval_Accumdep_Offset_Prior', Name='Revaluation Accumulated Depreciation Offset for Prior Year', Description=NULL, Help=NULL WHERE AD_Element_ID=53535 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:24:31 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Revaluation Accumulated Depreciation Offset for Prior Year', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53535) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:24:31 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Revaluation Accumulated Depreciation Offset for Prior Year', Name='Revaluation Accumulated Depreciation Offset for Prior Year' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53535)
;

-- Nov 12, 2008 4:24:51 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Revaluation Expense Offs', PrintName='Revaluation Expense Offs',Updated=TO_TIMESTAMP('2008-11-12 16:24:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53542
;

-- Nov 12, 2008 4:24:51 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53542
;

-- Nov 12, 2008 4:24:51 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Reval_Depexp_Offset', Name='Revaluation Expense Offs', Description=NULL, Help=NULL WHERE AD_Element_ID=53542
;

-- Nov 12, 2008 4:24:51 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Reval_Depexp_Offset', Name='Revaluation Expense Offs', Description=NULL, Help=NULL, AD_Element_ID=53542 WHERE UPPER(ColumnName)='A_REVAL_DEPEXP_OFFSET' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:24:51 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Reval_Depexp_Offset', Name='Revaluation Expense Offs', Description=NULL, Help=NULL WHERE AD_Element_ID=53542 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:24:51 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Revaluation Expense Offs', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53542) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:24:51 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Revaluation Expense Offs', Name='Revaluation Expense Offs' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53542)
;

-- Nov 12, 2008 4:25:13 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Asset ID', PrintName='Asset ID',Updated=TO_TIMESTAMP('2008-11-12 16:25:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53528
;

-- Nov 12, 2008 4:25:13 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53528
;

-- Nov 12, 2008 4:25:13 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Parent_Asset_ID', Name='Asset ID', Description=NULL, Help=NULL WHERE AD_Element_ID=53528
;

-- Nov 12, 2008 4:25:13 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Parent_Asset_ID', Name='Asset ID', Description=NULL, Help=NULL, AD_Element_ID=53528 WHERE UPPER(ColumnName)='A_PARENT_ASSET_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:25:13 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Parent_Asset_ID', Name='Asset ID', Description=NULL, Help=NULL WHERE AD_Element_ID=53528 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:25:13 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Asset ID', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53528) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:25:13 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Asset ID', Name='Asset ID' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53528)
;

-- Nov 12, 2008 4:25:33 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Original Qty', PrintName='Original Qty',Updated=TO_TIMESTAMP('2008-11-12 16:25:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53529
;

-- Nov 12, 2008 4:25:33 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53529
;

-- Nov 12, 2008 4:25:33 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_QTY_Original', Name='Original Qty', Description=NULL, Help=NULL WHERE AD_Element_ID=53529
;

-- Nov 12, 2008 4:25:33 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_QTY_Original', Name='Original Qty', Description=NULL, Help=NULL, AD_Element_ID=53529 WHERE UPPER(ColumnName)='A_QTY_ORIGINAL' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:25:33 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_QTY_Original', Name='Original Qty', Description=NULL, Help=NULL WHERE AD_Element_ID=53529 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:25:33 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Original Qty', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53529) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:25:33 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Original Qty', Name='Original Qty' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53529)
;

-- Nov 12, 2008 4:25:49 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Current Qty', PrintName='Current Qty',Updated=TO_TIMESTAMP('2008-11-12 16:25:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53494
;

-- Nov 12, 2008 4:25:49 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53494
;

-- Nov 12, 2008 4:25:49 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_QTY_Current', Name='Current Qty', Description=NULL, Help=NULL WHERE AD_Element_ID=53494
;

-- Nov 12, 2008 4:25:49 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_QTY_Current', Name='Current Qty', Description=NULL, Help=NULL, AD_Element_ID=53494 WHERE UPPER(ColumnName)='A_QTY_CURRENT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:25:49 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_QTY_Current', Name='Current Qty', Description=NULL, Help=NULL WHERE AD_Element_ID=53494 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:25:49 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Current Qty', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53494) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:25:49 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Current Qty', Name='Current Qty' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53494)
;

-- Nov 12, 2008 4:26:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Depreciation Type', PrintName='Depreciation Type',Updated=TO_TIMESTAMP('2008-11-12 16:26:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53473
;

-- Nov 12, 2008 4:26:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53473
;

-- Nov 12, 2008 4:26:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Depreciation_ID', Name='Depreciation Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53473
;

-- Nov 12, 2008 4:26:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_ID', Name='Depreciation Type', Description=NULL, Help=NULL, AD_Element_ID=53473 WHERE UPPER(ColumnName)='A_DEPRECIATION_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:26:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_ID', Name='Depreciation Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53473 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:26:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Depreciation Type', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53473) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:26:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Type', Name='Depreciation Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53473)
;

-- Nov 12, 2008 4:26:36 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Depreciation Calculation Type', PrintName='Depreciation Calculation Type',Updated=TO_TIMESTAMP('2008-11-12 16:26:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53544
;

-- Nov 12, 2008 4:26:36 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53544
;

-- Nov 12, 2008 4:26:36 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Depreciation_Method_ID', Name='Depreciation Calculation Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53544
;

-- Nov 12, 2008 4:26:36 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Method_ID', Name='Depreciation Calculation Type', Description=NULL, Help=NULL, AD_Element_ID=53544 WHERE UPPER(ColumnName)='A_DEPRECIATION_METHOD_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:26:36 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Method_ID', Name='Depreciation Calculation Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53544 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:26:36 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Depreciation Calculation Type', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53544) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:26:36 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Calculation Type', Name='Depreciation Calculation Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53544)
;

-- Nov 12, 2008 4:27:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Convention Type', PrintName='Convention Type',Updated=TO_TIMESTAMP('2008-11-12 16:27:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53546
;

-- Nov 12, 2008 4:27:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53546
;

-- Nov 12, 2008 4:27:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Depreciation_Conv_ID', Name='Convention Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53546
;

-- Nov 12, 2008 4:27:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Conv_ID', Name='Convention Type', Description=NULL, Help=NULL, AD_Element_ID=53546 WHERE UPPER(ColumnName)='A_DEPRECIATION_CONV_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:27:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Conv_ID', Name='Convention Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53546 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:27:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Convention Type', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53546) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:27:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Convention Type', Name='Convention Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53546)
;

-- Nov 12, 2008 4:31:02 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Period Start', PrintName='Period Start',Updated=TO_TIMESTAMP('2008-11-12 16:31:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53537
;

-- Nov 12, 2008 4:31:02 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53537
;

-- Nov 12, 2008 4:31:02 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Period_Start', Name='Period Start', Description=NULL, Help=NULL WHERE AD_Element_ID=53537
;

-- Nov 12, 2008 4:31:02 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Period_Start', Name='Period Start', Description=NULL, Help=NULL, AD_Element_ID=53537 WHERE UPPER(ColumnName)='A_PERIOD_START' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:31:02 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Period_Start', Name='Period Start', Description=NULL, Help=NULL WHERE AD_Element_ID=53537 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:31:02 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Period Start', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53537) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:31:03 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Period Start', Name='Period Start' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53537)
;

-- Nov 12, 2008 4:31:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Period End', PrintName='Period End',Updated=TO_TIMESTAMP('2008-11-12 16:31:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53538
;

-- Nov 12, 2008 4:31:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53538
;

-- Nov 12, 2008 4:31:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Period_End', Name='Period End', Description=NULL, Help=NULL WHERE AD_Element_ID=53538
;

-- Nov 12, 2008 4:31:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Period_End', Name='Period End', Description=NULL, Help=NULL, AD_Element_ID=53538 WHERE UPPER(ColumnName)='A_PERIOD_END' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:31:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Period_End', Name='Period End', Description=NULL, Help=NULL WHERE AD_Element_ID=53538 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:31:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Period End', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53538) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:31:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Period End', Name='Period End' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53538)
;

-- Nov 12, 2008 4:31:38 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Salvage Value', PrintName='Salvage Value',Updated=TO_TIMESTAMP('2008-11-12 16:31:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53493
;

-- Nov 12, 2008 4:31:38 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53493
;

-- Nov 12, 2008 4:31:38 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Salvage_Value', Name='Salvage Value', Description=NULL, Help=NULL WHERE AD_Element_ID=53493
;

-- Nov 12, 2008 4:31:38 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Salvage_Value', Name='Salvage Value', Description=NULL, Help=NULL, AD_Element_ID=53493 WHERE UPPER(ColumnName)='A_SALVAGE_VALUE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:31:38 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Salvage_Value', Name='Salvage Value', Description=NULL, Help=NULL WHERE AD_Element_ID=53493 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:31:38 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Salvage Value', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53493) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:31:39 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Salvage Value', Name='Salvage Value' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53493)
;

-- Nov 12, 2008 4:32:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Capital vs Expense', PrintName='Capital vs Expense',Updated=TO_TIMESTAMP('2008-11-12 16:32:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53641
;

-- Nov 12, 2008 4:32:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53641
;

-- Nov 12, 2008 4:32:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_CapvsExp', Name='Capital vs Expense', Description=NULL, Help=NULL WHERE AD_Element_ID=53641
;

-- Nov 12, 2008 4:32:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_CapvsExp', Name='Capital vs Expense', Description=NULL, Help=NULL, AD_Element_ID=53641 WHERE UPPER(ColumnName)='A_CAPVSEXP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:32:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_CapvsExp', Name='Capital vs Expense', Description=NULL, Help=NULL WHERE AD_Element_ID=53641 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:32:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Capital vs Expense', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53641) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:32:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Capital vs Expense', Name='Capital vs Expense' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53641)
;

-- Nov 12, 2008 4:32:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Source of Entry', PrintName='Source of Entry',Updated=TO_TIMESTAMP('2008-11-12 16:32:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53640
;

-- Nov 12, 2008 4:32:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53640
;

-- Nov 12, 2008 4:32:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_SourceType', Name='Source of Entry', Description=NULL, Help=NULL WHERE AD_Element_ID=53640
;

-- Nov 12, 2008 4:32:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_SourceType', Name='Source of Entry', Description=NULL, Help=NULL, AD_Element_ID=53640 WHERE UPPER(ColumnName)='A_SOURCETYPE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:32:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_SourceType', Name='Source of Entry', Description=NULL, Help=NULL WHERE AD_Element_ID=53640 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:32:20 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Source of Entry', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53640) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:32:21 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Source of Entry', Name='Source of Entry' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53640)
;

-- Nov 12, 2008 4:33:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Quantity', PrintName='Quantity',Updated=TO_TIMESTAMP('2008-11-12 16:33:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53494
;

-- Nov 12, 2008 4:33:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53494
;

-- Nov 12, 2008 4:33:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_QTY_Current', Name='Quantity', Description=NULL, Help=NULL WHERE AD_Element_ID=53494
;

-- Nov 12, 2008 4:33:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_QTY_Current', Name='Quantity', Description=NULL, Help=NULL, AD_Element_ID=53494 WHERE UPPER(ColumnName)='A_QTY_CURRENT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:33:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_QTY_Current', Name='Quantity', Description=NULL, Help=NULL WHERE AD_Element_ID=53494 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:33:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Quantity', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53494) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:33:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Quantity', Name='Quantity' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53494)
;

-- Nov 12, 2008 4:33:56 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Leasor, Mortgage Company, or Rentor', PrintName='Leasor, Mortgage Company, or Rentor',Updated=TO_TIMESTAMP('2008-11-12 16:33:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53593
;

-- Nov 12, 2008 4:33:56 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53593
;

-- Nov 12, 2008 4:33:56 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Finance_Meth', Name='Leasor, Mortgage Company, or Rentor', Description=NULL, Help=NULL WHERE AD_Element_ID=53593
;

-- Nov 12, 2008 4:33:56 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Finance_Meth', Name='Leasor, Mortgage Company, or Rentor', Description=NULL, Help=NULL, AD_Element_ID=53593 WHERE UPPER(ColumnName)='A_FINANCE_METH' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:33:56 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Finance_Meth', Name='Leasor, Mortgage Company, or Rentor', Description=NULL, Help=NULL WHERE AD_Element_ID=53593 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:33:56 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Leasor, Mortgage Company, or Rentor', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53593) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:33:56 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Leasor, Mortgage Company, or Rentor', Name='Leasor, Mortgage Company, or Rentor' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53593)
;

-- Nov 12, 2008 4:34:24 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Contract Date', PrintName='Contract Date',Updated=TO_TIMESTAMP('2008-11-12 16:34:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53602
;

-- Nov 12, 2008 4:34:24 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53602
;

-- Nov 12, 2008 4:34:24 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Contract_Date', Name='Contract Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53602
;

-- Nov 12, 2008 4:34:24 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Contract_Date', Name='Contract Date', Description=NULL, Help=NULL, AD_Element_ID=53602 WHERE UPPER(ColumnName)='A_CONTRACT_DATE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:34:24 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Contract_Date', Name='Contract Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53602 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:34:24 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Contract Date', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53602) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:34:24 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Contract Date', Name='Contract Date' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53602)
;

-- Nov 12, 2008 4:34:39 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Contract Expiration Date', PrintName='Contract Expiration Date',Updated=TO_TIMESTAMP('2008-11-12 16:34:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53601
;

-- Nov 12, 2008 4:34:39 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53601
;

-- Nov 12, 2008 4:34:39 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Expired_Date', Name='Contract Expiration Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53601
;

-- Nov 12, 2008 4:34:39 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Expired_Date', Name='Contract Expiration Date', Description=NULL, Help=NULL, AD_Element_ID=53601 WHERE UPPER(ColumnName)='A_EXPIRED_DATE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:34:39 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Expired_Date', Name='Contract Expiration Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53601 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:34:39 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Contract Expiration Date', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53601) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:34:39 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Contract Expiration Date', Name='Contract Expiration Date' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53601)
;

-- Nov 12, 2008 4:34:57 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Monthly Payment', PrintName='Monthly Payment',Updated=TO_TIMESTAMP('2008-11-12 16:34:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53600
;

-- Nov 12, 2008 4:34:57 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53600
;

-- Nov 12, 2008 4:34:57 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Monthly_Payment', Name='Monthly Payment', Description=NULL, Help=NULL WHERE AD_Element_ID=53600
;

-- Nov 12, 2008 4:34:57 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Monthly_Payment', Name='Monthly Payment', Description=NULL, Help=NULL, AD_Element_ID=53600 WHERE UPPER(ColumnName)='A_MONTHLY_PAYMENT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:34:57 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Monthly_Payment', Name='Monthly Payment', Description=NULL, Help=NULL WHERE AD_Element_ID=53600 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:34:57 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Monthly Payment', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53600) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:34:57 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Monthly Payment', Name='Monthly Payment' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53600)
;

-- Nov 12, 2008 4:35:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Payment Due Date', PrintName='Payment Due Date',Updated=TO_TIMESTAMP('2008-11-12 16:35:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53595
;

-- Nov 12, 2008 4:35:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53595
;

-- Nov 12, 2008 4:35:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Due_On', Name='Payment Due Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53595
;

-- Nov 12, 2008 4:35:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Due_On', Name='Payment Due Date', Description=NULL, Help=NULL, AD_Element_ID=53595 WHERE UPPER(ColumnName)='A_DUE_ON' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:35:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Due_On', Name='Payment Due Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53595 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:35:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Payment Due Date', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53595) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:35:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Payment Due Date', Name='Payment Due Date' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53595)
;

-- Nov 12, 2008 4:35:44 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Purchase Option', PrintName='Purchase Option',Updated=TO_TIMESTAMP('2008-11-12 16:35:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53596
;

-- Nov 12, 2008 4:35:44 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53596
;

-- Nov 12, 2008 4:35:44 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Purchase_Option', Name='Purchase Option', Description=NULL, Help=NULL WHERE AD_Element_ID=53596
;

-- Nov 12, 2008 4:35:44 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Purchase_Option', Name='Purchase Option', Description=NULL, Help=NULL, AD_Element_ID=53596 WHERE UPPER(ColumnName)='A_PURCHASE_OPTION' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:35:44 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Purchase_Option', Name='Purchase Option', Description=NULL, Help=NULL WHERE AD_Element_ID=53596 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:35:44 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Purchase Option', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53596) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:35:44 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Purchase Option', Name='Purchase Option' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53596)
;

-- Nov 12, 2008 4:36:02 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Option Purchase Price', PrintName='Option Purchase Price',Updated=TO_TIMESTAMP('2008-11-12 16:36:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53598
;

-- Nov 12, 2008 4:36:02 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53598
;

-- Nov 12, 2008 4:36:02 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Purchase_Price', Name='Option Purchase Price', Description=NULL, Help=NULL WHERE AD_Element_ID=53598
;

-- Nov 12, 2008 4:36:02 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Purchase_Price', Name='Option Purchase Price', Description=NULL, Help=NULL, AD_Element_ID=53598 WHERE UPPER(ColumnName)='A_PURCHASE_PRICE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:36:02 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Purchase_Price', Name='Option Purchase Price', Description=NULL, Help=NULL WHERE AD_Element_ID=53598 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:36:02 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Option Purchase Price', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53598) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:36:02 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Option Purchase Price', Name='Option Purchase Price' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53598)
;

-- Nov 12, 2008 4:36:23 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Purchase Option Credit', PrintName='Purchase Option Credit',Updated=TO_TIMESTAMP('2008-11-12 16:36:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53599
;

-- Nov 12, 2008 4:36:23 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53599
;

-- Nov 12, 2008 4:36:23 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Purchase_Option_Credit', Name='Purchase Option Credit', Description=NULL, Help=NULL WHERE AD_Element_ID=53599
;

-- Nov 12, 2008 4:36:23 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Purchase_Option_Credit', Name='Purchase Option Credit', Description=NULL, Help=NULL, AD_Element_ID=53599 WHERE UPPER(ColumnName)='A_PURCHASE_OPTION_CREDIT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:36:23 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Purchase_Option_Credit', Name='Purchase Option Credit', Description=NULL, Help=NULL WHERE AD_Element_ID=53599 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:36:23 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Purchase Option Credit', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53599) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:36:23 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Purchase Option Credit', Name='Purchase Option Credit' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53599)
;

-- Nov 12, 2008 4:36:43 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Purchase Option Credit %', PrintName='Purchase Option Credit %',Updated=TO_TIMESTAMP('2008-11-12 16:36:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53597
;

-- Nov 12, 2008 4:36:43 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53597
;

-- Nov 12, 2008 4:36:43 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Purchase_Option_Credit_Per', Name='Purchase Option Credit %', Description=NULL, Help=NULL WHERE AD_Element_ID=53597
;

-- Nov 12, 2008 4:36:43 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Purchase_Option_Credit_Per', Name='Purchase Option Credit %', Description=NULL, Help=NULL, AD_Element_ID=53597 WHERE UPPER(ColumnName)='A_PURCHASE_OPTION_CREDIT_PER' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:36:43 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Purchase_Option_Credit_Per', Name='Purchase Option Credit %', Description=NULL, Help=NULL WHERE AD_Element_ID=53597 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:36:43 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Purchase Option Credit %', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53597) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:36:43 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Purchase Option Credit %', Name='Purchase Option Credit %' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53597)
;

-- Nov 12, 2008 4:37:59 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Issuing Agency', PrintName='Issuing Agency',Updated=TO_TIMESTAMP('2008-11-12 16:37:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53617
;

-- Nov 12, 2008 4:37:59 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53617
;

-- Nov 12, 2008 4:37:59 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Issuing_Agency', Name='Issuing Agency', Description=NULL, Help=NULL WHERE AD_Element_ID=53617
;

-- Nov 12, 2008 4:38:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Issuing_Agency', Name='Issuing Agency', Description=NULL, Help=NULL, AD_Element_ID=53617 WHERE UPPER(ColumnName)='A_ISSUING_AGENCY' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:38:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Issuing_Agency', Name='Issuing Agency', Description=NULL, Help=NULL WHERE AD_Element_ID=53617 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:38:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Issuing Agency', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53617) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:38:00 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Issuing Agency', Name='Issuing Agency' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53617)
;

-- Nov 12, 2008 4:38:16 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='License No', PrintName='License No',Updated=TO_TIMESTAMP('2008-11-12 16:38:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53616
;

-- Nov 12, 2008 4:38:16 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53616
;

-- Nov 12, 2008 4:38:16 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_License_No', Name='License No', Description=NULL, Help=NULL WHERE AD_Element_ID=53616
;

-- Nov 12, 2008 4:38:16 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_License_No', Name='License No', Description=NULL, Help=NULL, AD_Element_ID=53616 WHERE UPPER(ColumnName)='A_LICENSE_NO' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:38:16 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_License_No', Name='License No', Description=NULL, Help=NULL WHERE AD_Element_ID=53616 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:38:16 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='License No', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53616) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:38:16 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='License No', Name='License No' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53616)
;

-- Nov 12, 2008 4:38:43 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='License Fee', PrintName='License Fee',Updated=TO_TIMESTAMP('2008-11-12 16:38:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53614
;

-- Nov 12, 2008 4:38:43 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53614
;

-- Nov 12, 2008 4:38:43 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_License_Fee', Name='License Fee', Description=NULL, Help=NULL WHERE AD_Element_ID=53614
;

-- Nov 12, 2008 4:38:43 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_License_Fee', Name='License Fee', Description=NULL, Help=NULL, AD_Element_ID=53614 WHERE UPPER(ColumnName)='A_LICENSE_FEE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:38:43 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_License_Fee', Name='License Fee', Description=NULL, Help=NULL WHERE AD_Element_ID=53614 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:38:43 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='License Fee', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53614) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:38:43 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='License Fee', Name='License Fee' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53614)
;

-- Nov 12, 2008 4:39:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Renewal Date', PrintName='Renewal Date',Updated=TO_TIMESTAMP('2008-11-12 16:39:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53615
;

-- Nov 12, 2008 4:39:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53615
;

-- Nov 12, 2008 4:39:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Renewal_Date', Name='Renewal Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53615
;

-- Nov 12, 2008 4:39:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Renewal_Date', Name='Renewal Date', Description=NULL, Help=NULL, AD_Element_ID=53615 WHERE UPPER(ColumnName)='A_RENEWAL_DATE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:39:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Renewal_Date', Name='Renewal Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53615 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:39:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Renewal Date', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53615) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:39:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Renewal Date', Name='Renewal Date' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53615)
;

-- Nov 12, 2008 4:39:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Insurance Company', PrintName='Insurance Company',Updated=TO_TIMESTAMP('2008-11-12 16:39:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53622
;

-- Nov 12, 2008 4:39:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53622
;

-- Nov 12, 2008 4:39:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Insurance_Co', Name='Insurance Company', Description=NULL, Help=NULL WHERE AD_Element_ID=53622
;

-- Nov 12, 2008 4:39:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Insurance_Co', Name='Insurance Company', Description=NULL, Help=NULL, AD_Element_ID=53622 WHERE UPPER(ColumnName)='A_INSURANCE_CO' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:39:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Insurance_Co', Name='Insurance Company', Description=NULL, Help=NULL WHERE AD_Element_ID=53622 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:39:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Insurance Company', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53622) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:39:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Insurance Company', Name='Insurance Company' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53622)
;

-- Nov 12, 2008 4:39:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Policy Number', PrintName='Policy Number',Updated=TO_TIMESTAMP('2008-11-12 16:39:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53620
;

-- Nov 12, 2008 4:39:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53620
;

-- Nov 12, 2008 4:39:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Policy_No', Name='Policy Number', Description=NULL, Help=NULL WHERE AD_Element_ID=53620
;

-- Nov 12, 2008 4:39:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Policy_No', Name='Policy Number', Description=NULL, Help=NULL, AD_Element_ID=53620 WHERE UPPER(ColumnName)='A_POLICY_NO' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:39:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Policy_No', Name='Policy Number', Description=NULL, Help=NULL WHERE AD_Element_ID=53620 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:39:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Policy Number', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53620) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:39:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Policy Number', Name='Policy Number' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53620)
;

-- Nov 12, 2008 4:40:06 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Policy Renewal Date', PrintName='Policy Renewal Date',Updated=TO_TIMESTAMP('2008-11-12 16:40:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53615
;

-- Nov 12, 2008 4:40:06 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53615
;

-- Nov 12, 2008 4:40:06 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Renewal_Date', Name='Policy Renewal Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53615
;

-- Nov 12, 2008 4:40:06 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Renewal_Date', Name='Policy Renewal Date', Description=NULL, Help=NULL, AD_Element_ID=53615 WHERE UPPER(ColumnName)='A_RENEWAL_DATE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:40:06 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Renewal_Date', Name='Policy Renewal Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53615 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:40:06 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Policy Renewal Date', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53615) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:40:06 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Policy Renewal Date', Name='Policy Renewal Date' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53615)
;

-- Nov 12, 2008 4:40:26 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Insurance Premium', PrintName='Insurance Premium',Updated=TO_TIMESTAMP('2008-11-12 16:40:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53623
;

-- Nov 12, 2008 4:40:26 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53623
;

-- Nov 12, 2008 4:40:26 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Ins_Premium', Name='Insurance Premium', Description=NULL, Help=NULL WHERE AD_Element_ID=53623
;

-- Nov 12, 2008 4:40:26 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Ins_Premium', Name='Insurance Premium', Description=NULL, Help=NULL, AD_Element_ID=53623 WHERE UPPER(ColumnName)='A_INS_PREMIUM' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:40:26 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Ins_Premium', Name='Insurance Premium', Description=NULL, Help=NULL WHERE AD_Element_ID=53623 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:40:26 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Insurance Premium', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53623) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:40:26 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Insurance Premium', Name='Insurance Premium' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53623)
;

-- Nov 12, 2008 4:40:52 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Replacement Costs', PrintName='Replacement Costs',Updated=TO_TIMESTAMP('2008-11-12 16:40:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53621
;

-- Nov 12, 2008 4:40:52 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53621
;

-- Nov 12, 2008 4:40:52 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Replace_Cost', Name='Replacement Costs', Description=NULL, Help=NULL WHERE AD_Element_ID=53621
;

-- Nov 12, 2008 4:40:52 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Replace_Cost', Name='Replacement Costs', Description=NULL, Help=NULL, AD_Element_ID=53621 WHERE UPPER(ColumnName)='A_REPLACE_COST' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:40:52 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Replace_Cost', Name='Replacement Costs', Description=NULL, Help=NULL WHERE AD_Element_ID=53621 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:40:52 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Replacement Costs', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53621) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:40:52 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Replacement Costs', Name='Replacement Costs' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53621)
;

-- Nov 12, 2008 4:41:13 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Insured Value', PrintName='Insured Value',Updated=TO_TIMESTAMP('2008-11-12 16:41:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53619
;

-- Nov 12, 2008 4:41:13 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53619
;

-- Nov 12, 2008 4:41:13 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Ins_Value', Name='Insured Value', Description=NULL, Help=NULL WHERE AD_Element_ID=53619
;

-- Nov 12, 2008 4:41:13 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Ins_Value', Name='Insured Value', Description=NULL, Help=NULL, AD_Element_ID=53619 WHERE UPPER(ColumnName)='A_INS_VALUE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:41:13 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Ins_Value', Name='Insured Value', Description=NULL, Help=NULL WHERE AD_Element_ID=53619 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:41:13 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Insured Value', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53619) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:41:13 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Insured Value', Name='Insured Value' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53619)
;

-- Nov 12, 2008 4:41:58 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Tax Entity', PrintName='Tax Entity',Updated=TO_TIMESTAMP('2008-11-12 16:41:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53591
;

-- Nov 12, 2008 4:41:58 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53591
;

-- Nov 12, 2008 4:41:58 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Tax_Entity', Name='Tax Entity', Description=NULL, Help=NULL WHERE AD_Element_ID=53591
;

-- Nov 12, 2008 4:41:58 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Tax_Entity', Name='Tax Entity', Description=NULL, Help=NULL, AD_Element_ID=53591 WHERE UPPER(ColumnName)='A_TAX_ENTITY' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:41:58 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Tax_Entity', Name='Tax Entity', Description=NULL, Help=NULL WHERE AD_Element_ID=53591 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:41:58 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Tax Entity', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53591) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:41:58 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Tax Entity', Name='Tax Entity' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53591)
;

-- Nov 12, 2008 4:42:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Purchased New?', PrintName='Purchased New?',Updated=TO_TIMESTAMP('2008-11-12 16:42:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53592
;

-- Nov 12, 2008 4:42:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53592
;

-- Nov 12, 2008 4:42:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_New_Used', Name='Purchased New?', Description=NULL, Help=NULL WHERE AD_Element_ID=53592
;

-- Nov 12, 2008 4:42:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_New_Used', Name='Purchased New?', Description=NULL, Help=NULL, AD_Element_ID=53592 WHERE UPPER(ColumnName)='A_NEW_USED' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:42:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_New_Used', Name='Purchased New?', Description=NULL, Help=NULL WHERE AD_Element_ID=53592 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:42:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Purchased New?', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53592) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:42:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Purchased New?', Name='Purchased New?' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53592)
;

-- Nov 12, 2008 4:43:07 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Finance Method', PrintName='Finance Method',Updated=TO_TIMESTAMP('2008-11-12 16:43:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53593
;

-- Nov 12, 2008 4:43:07 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53593
;

-- Nov 12, 2008 4:43:07 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Finance_Meth', Name='Finance Method', Description=NULL, Help=NULL WHERE AD_Element_ID=53593
;

-- Nov 12, 2008 4:43:07 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Finance_Meth', Name='Finance Method', Description=NULL, Help=NULL, AD_Element_ID=53593 WHERE UPPER(ColumnName)='A_FINANCE_METH' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:43:07 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Finance_Meth', Name='Finance Method', Description=NULL, Help=NULL WHERE AD_Element_ID=53593 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:43:07 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Finance Method', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53593) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:43:07 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Finance Method', Name='Finance Method' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53593)
;

-- Nov 12, 2008 4:43:22 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Investment Credit', PrintName='Investment Credit',Updated=TO_TIMESTAMP('2008-11-12 16:43:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53590
;

-- Nov 12, 2008 4:43:22 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53590
;

-- Nov 12, 2008 4:43:22 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Investment_CR', Name='Investment Credit', Description=NULL, Help=NULL WHERE AD_Element_ID=53590
;

-- Nov 12, 2008 4:43:22 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Investment_CR', Name='Investment Credit', Description=NULL, Help=NULL, AD_Element_ID=53590 WHERE UPPER(ColumnName)='A_INVESTMENT_CR' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:43:22 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Investment_CR', Name='Investment Credit', Description=NULL, Help=NULL WHERE AD_Element_ID=53590 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:43:22 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Investment Credit', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53590) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:43:22 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Investment Credit', Name='Investment Credit' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53590)
;

-- Nov 12, 2008 4:44:49 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Asset Related?', PrintName='Asset Related?',Updated=TO_TIMESTAMP('2008-11-12 16:44:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53646
;

-- Nov 12, 2008 4:44:49 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53646
;

-- Nov 12, 2008 4:44:49 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_CreateAsset', Name='Asset Related?', Description=NULL, Help=NULL WHERE AD_Element_ID=53646
;

-- Nov 12, 2008 4:44:49 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_CreateAsset', Name='Asset Related?', Description=NULL, Help=NULL, AD_Element_ID=53646 WHERE UPPER(ColumnName)='A_CREATEASSET' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:44:49 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_CreateAsset', Name='Asset Related?', Description=NULL, Help=NULL WHERE AD_Element_ID=53646 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:44:49 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Asset Related?', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53646) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:44:49 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Asset Related?', Name='Asset Related?' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53646)
;

-- Nov 12, 2008 4:45:04 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Entry Type', PrintName='Entry Type',Updated=TO_TIMESTAMP('2008-11-12 16:45:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53484
;

-- Nov 12, 2008 4:45:04 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53484
;

-- Nov 12, 2008 4:45:04 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Entry_Type', Name='Entry Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53484
;

-- Nov 12, 2008 4:45:04 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Entry_Type', Name='Entry Type', Description=NULL, Help=NULL, AD_Element_ID=53484 WHERE UPPER(ColumnName)='A_ENTRY_TYPE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:45:04 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Entry_Type', Name='Entry Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53484 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:45:04 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Entry Type', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53484) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:45:04 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Entry Type', Name='Entry Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53484)
;

-- Nov 12, 2008 4:46:11 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Depreciation Code', PrintName='Depreciation Code',Updated=TO_TIMESTAMP('2008-11-12 16:46:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53480
;

-- Nov 12, 2008 4:46:11 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53480
;

-- Nov 12, 2008 4:46:11 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Depreciation_Table_Code', Name='Depreciation Code', Description=NULL, Help=NULL WHERE AD_Element_ID=53480
;

-- Nov 12, 2008 4:46:11 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Table_Code', Name='Depreciation Code', Description=NULL, Help=NULL, AD_Element_ID=53480 WHERE UPPER(ColumnName)='A_DEPRECIATION_TABLE_CODE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:46:11 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Table_Code', Name='Depreciation Code', Description=NULL, Help=NULL WHERE AD_Element_ID=53480 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:46:11 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Depreciation Code', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53480) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:46:11 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Code', Name='Depreciation Code' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53480)
;

-- Nov 12, 2008 4:46:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Period/Yearly', PrintName='Period/Yearly',Updated=TO_TIMESTAMP('2008-11-12 16:46:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53482
;

-- Nov 12, 2008 4:46:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53482
;

-- Nov 12, 2008 4:46:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Term', Name='Period/Yearly', Description=NULL, Help=NULL WHERE AD_Element_ID=53482
;

-- Nov 12, 2008 4:46:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Term', Name='Period/Yearly', Description=NULL, Help=NULL, AD_Element_ID=53482 WHERE UPPER(ColumnName)='A_TERM' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:46:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Term', Name='Period/Yearly', Description=NULL, Help=NULL WHERE AD_Element_ID=53482 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:46:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Period/Yearly', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53482) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:46:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Period/Yearly', Name='Period/Yearly' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53482)
;

-- Nov 12, 2008 4:46:41 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Type', PrintName='Type',Updated=TO_TIMESTAMP('2008-11-12 16:46:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53478
;

-- Nov 12, 2008 4:46:41 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53478
;

-- Nov 12, 2008 4:46:41 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Table_Rate_Type', Name='Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53478
;

-- Nov 12, 2008 4:46:41 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Table_Rate_Type', Name='Type', Description=NULL, Help=NULL, AD_Element_ID=53478 WHERE UPPER(ColumnName)='A_TABLE_RATE_TYPE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:46:41 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Table_Rate_Type', Name='Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53478 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:46:41 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Type', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53478) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:46:41 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Type', Name='Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53478)
;

-- Nov 12, 2008 4:47:07 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Period/Yearly', PrintName='Period/Yearly',Updated=TO_TIMESTAMP('2008-11-12 16:47:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53477
;

-- Nov 12, 2008 4:47:07 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53477
;

-- Nov 12, 2008 4:47:07 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Period', Name='Period/Yearly', Description=NULL, Help=NULL WHERE AD_Element_ID=53477
;

-- Nov 12, 2008 4:47:07 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Period', Name='Period/Yearly', Description=NULL, Help=NULL, AD_Element_ID=53477 WHERE UPPER(ColumnName)='A_PERIOD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:47:07 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Period', Name='Period/Yearly', Description=NULL, Help=NULL WHERE AD_Element_ID=53477 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:47:07 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Period/Yearly', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53477) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:47:07 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Period/Yearly', Name='Period/Yearly' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53477)
;

-- Nov 12, 2008 4:47:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Rate', PrintName='Rate',Updated=TO_TIMESTAMP('2008-11-12 16:47:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53479
;

-- Nov 12, 2008 4:47:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53479
;

-- Nov 12, 2008 4:47:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Depreciation_Rate', Name='Rate', Description=NULL, Help=NULL WHERE AD_Element_ID=53479
;

-- Nov 12, 2008 4:47:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Rate', Name='Rate', Description=NULL, Help=NULL, AD_Element_ID=53479 WHERE UPPER(ColumnName)='A_DEPRECIATION_RATE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:47:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Rate', Name='Rate', Description=NULL, Help=NULL WHERE AD_Element_ID=53479 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:47:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Rate', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53479) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:47:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Rate', Name='Rate' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53479)
;

-- Nov 12, 2008 4:48:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='End Aset ID', PrintName='End Aset ID',Updated=TO_TIMESTAMP('2008-11-12 16:48:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53505
;

-- Nov 12, 2008 4:48:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53505
;

-- Nov 12, 2008 4:48:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_End_Asset_ID', Name='End Aset ID', Description=NULL, Help=NULL WHERE AD_Element_ID=53505
;

-- Nov 12, 2008 4:48:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_End_Asset_ID', Name='End Aset ID', Description=NULL, Help=NULL, AD_Element_ID=53505 WHERE UPPER(ColumnName)='A_END_ASSET_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:48:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_End_Asset_ID', Name='End Aset ID', Description=NULL, Help=NULL WHERE AD_Element_ID=53505 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:48:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='End Aset ID', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53505) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:48:15 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='End Aset ID', Name='End Aset ID' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53505)
;

-- Nov 12, 2008 4:51:41 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Old Asset Cost Acct', PrintName='Old Asset Cost Acct',Updated=TO_TIMESTAMP('2008-11-12 16:51:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53565
;

-- Nov 12, 2008 4:51:41 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53565
;

-- Nov 12, 2008 4:51:41 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Asset_Acct_Str', Name='Old Asset Cost Acct', Description=NULL, Help=NULL WHERE AD_Element_ID=53565
;

-- Nov 12, 2008 4:51:41 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Asset_Acct_Str', Name='Old Asset Cost Acct', Description=NULL, Help=NULL, AD_Element_ID=53565 WHERE UPPER(ColumnName)='A_ASSET_ACCT_STR' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:51:41 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Asset_Acct_Str', Name='Old Asset Cost Acct', Description=NULL, Help=NULL WHERE AD_Element_ID=53565 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:51:41 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Old Asset Cost Acct', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53565) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:51:41 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Old Asset Cost Acct', Name='Old Asset Cost Acct' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53565)
;

-- Nov 12, 2008 4:51:57 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='New Asset Cost Acct', PrintName='New Asset Cost Acct',Updated=TO_TIMESTAMP('2008-11-12 16:51:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53557
;

-- Nov 12, 2008 4:51:57 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53557
;

-- Nov 12, 2008 4:51:57 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Asset_Acct_New', Name='New Asset Cost Acct', Description=NULL, Help=NULL WHERE AD_Element_ID=53557
;

-- Nov 12, 2008 4:51:57 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Asset_Acct_New', Name='New Asset Cost Acct', Description=NULL, Help=NULL, AD_Element_ID=53557 WHERE UPPER(ColumnName)='A_ASSET_ACCT_NEW' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:51:57 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Asset_Acct_New', Name='New Asset Cost Acct', Description=NULL, Help=NULL WHERE AD_Element_ID=53557 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:51:57 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='New Asset Cost Acct', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53557) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:51:57 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='New Asset Cost Acct', Name='New Asset Cost Acct' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53557)
;

-- Nov 12, 2008 4:52:22 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Old Accum Depreciation Acct', PrintName='Old Accum Depreciation Acct',Updated=TO_TIMESTAMP('2008-11-12 16:52:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53567
;

-- Nov 12, 2008 4:52:22 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53567
;

-- Nov 12, 2008 4:52:22 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Accumdepreciation_Acct_Str', Name='Old Accum Depreciation Acct', Description=NULL, Help=NULL WHERE AD_Element_ID=53567
;

-- Nov 12, 2008 4:52:22 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Accumdepreciation_Acct_Str', Name='Old Accum Depreciation Acct', Description=NULL, Help=NULL, AD_Element_ID=53567 WHERE UPPER(ColumnName)='A_ACCUMDEPRECIATION_ACCT_STR' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:52:22 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Accumdepreciation_Acct_Str', Name='Old Accum Depreciation Acct', Description=NULL, Help=NULL WHERE AD_Element_ID=53567 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:52:22 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Old Accum Depreciation Acct', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53567) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:52:22 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Old Accum Depreciation Acct', Name='Old Accum Depreciation Acct' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53567)
;

-- Nov 12, 2008 4:53:30 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='New Accum Depreciation Acct', PrintName='New Accum Depreciation Acct',Updated=TO_TIMESTAMP('2008-11-12 16:53:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53566
;

-- Nov 12, 2008 4:53:30 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53566
;

-- Nov 12, 2008 4:53:30 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Accumdepreciation_Acct_New', Name='New Accum Depreciation Acct', Description=NULL, Help=NULL WHERE AD_Element_ID=53566
;

-- Nov 12, 2008 4:53:30 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Accumdepreciation_Acct_New', Name='New Accum Depreciation Acct', Description=NULL, Help=NULL, AD_Element_ID=53566 WHERE UPPER(ColumnName)='A_ACCUMDEPRECIATION_ACCT_NEW' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:53:30 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Accumdepreciation_Acct_New', Name='New Accum Depreciation Acct', Description=NULL, Help=NULL WHERE AD_Element_ID=53566 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:53:30 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='New Accum Depreciation Acct', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53566) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:53:30 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='New Accum Depreciation Acct', Name='New Accum Depreciation Acct' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53566)
;

-- Nov 12, 2008 4:54:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Old Depreciation Exp Acct', PrintName='Old Depreciation Exp Acct',Updated=TO_TIMESTAMP('2008-11-12 16:54:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53558
;

-- Nov 12, 2008 4:54:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53558
;

-- Nov 12, 2008 4:54:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Depreciation_Acct_Str', Name='Old Depreciation Exp Acct', Description=NULL, Help=NULL WHERE AD_Element_ID=53558
;

-- Nov 12, 2008 4:54:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Acct_Str', Name='Old Depreciation Exp Acct', Description=NULL, Help=NULL, AD_Element_ID=53558 WHERE UPPER(ColumnName)='A_DEPRECIATION_ACCT_STR' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:54:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Acct_Str', Name='Old Depreciation Exp Acct', Description=NULL, Help=NULL WHERE AD_Element_ID=53558 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:54:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Old Depreciation Exp Acct', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53558) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:54:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Old Depreciation Exp Acct', Name='Old Depreciation Exp Acct' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53558)
;

-- Nov 12, 2008 4:54:30 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='New Depreciation Exp Acct', PrintName='New Depreciation Exp Acct',Updated=TO_TIMESTAMP('2008-11-12 16:54:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53564
;

-- Nov 12, 2008 4:54:30 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53564
;

-- Nov 12, 2008 4:54:30 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Depreciation_Acct_New', Name='New Depreciation Exp Acct', Description=NULL, Help=NULL WHERE AD_Element_ID=53564
;

-- Nov 12, 2008 4:54:30 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Acct_New', Name='New Depreciation Exp Acct', Description=NULL, Help=NULL, AD_Element_ID=53564 WHERE UPPER(ColumnName)='A_DEPRECIATION_ACCT_NEW' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:54:30 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Acct_New', Name='New Depreciation Exp Acct', Description=NULL, Help=NULL WHERE AD_Element_ID=53564 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:54:30 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='New Depreciation Exp Acct', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53564) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:54:30 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='New Depreciation Exp Acct', Name='New Depreciation Exp Acct' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53564)
;

-- Nov 12, 2008 4:54:51 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Old Disposal Revenue', PrintName='Old Disposal Revenue',Updated=TO_TIMESTAMP('2008-11-12 16:54:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53561
;

-- Nov 12, 2008 4:54:51 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53561
;

-- Nov 12, 2008 4:54:51 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Disposal_Revenue_Str', Name='Old Disposal Revenue', Description=NULL, Help=NULL WHERE AD_Element_ID=53561
;

-- Nov 12, 2008 4:54:51 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposal_Revenue_Str', Name='Old Disposal Revenue', Description=NULL, Help=NULL, AD_Element_ID=53561 WHERE UPPER(ColumnName)='A_DISPOSAL_REVENUE_STR' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:54:51 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposal_Revenue_Str', Name='Old Disposal Revenue', Description=NULL, Help=NULL WHERE AD_Element_ID=53561 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:54:51 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Old Disposal Revenue', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53561) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:54:51 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Old Disposal Revenue', Name='Old Disposal Revenue' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53561)
;

-- Nov 12, 2008 4:55:11 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='New Disposal Revenue', PrintName='New Disposal Revenue',Updated=TO_TIMESTAMP('2008-11-12 16:55:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53562
;

-- Nov 12, 2008 4:55:11 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53562
;

-- Nov 12, 2008 4:55:11 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Disposal_Revenue_New', Name='New Disposal Revenue', Description=NULL, Help=NULL WHERE AD_Element_ID=53562
;

-- Nov 12, 2008 4:55:11 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposal_Revenue_New', Name='New Disposal Revenue', Description=NULL, Help=NULL, AD_Element_ID=53562 WHERE UPPER(ColumnName)='A_DISPOSAL_REVENUE_NEW' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:55:11 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposal_Revenue_New', Name='New Disposal Revenue', Description=NULL, Help=NULL WHERE AD_Element_ID=53562 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:55:11 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='New Disposal Revenue', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53562) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:55:11 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='New Disposal Revenue', Name='New Disposal Revenue' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53562)
;

-- Nov 12, 2008 4:55:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Old Disposal Loss', PrintName='Old Disposal Loss',Updated=TO_TIMESTAMP('2008-11-12 16:55:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53563
;

-- Nov 12, 2008 4:55:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53563
;

-- Nov 12, 2008 4:55:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Disposal_Loss_Str', Name='Old Disposal Loss', Description=NULL, Help=NULL WHERE AD_Element_ID=53563
;

-- Nov 12, 2008 4:55:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposal_Loss_Str', Name='Old Disposal Loss', Description=NULL, Help=NULL, AD_Element_ID=53563 WHERE UPPER(ColumnName)='A_DISPOSAL_LOSS_STR' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:55:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposal_Loss_Str', Name='Old Disposal Loss', Description=NULL, Help=NULL WHERE AD_Element_ID=53563 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:55:32 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Old Disposal Loss', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53563) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:55:33 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Old Disposal Loss', Name='Old Disposal Loss' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53563)
;

-- Nov 12, 2008 4:56:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='New Disposal Loss', PrintName='New Disposal Loss',Updated=TO_TIMESTAMP('2008-11-12 16:56:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53559
;

-- Nov 12, 2008 4:56:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53559
;

-- Nov 12, 2008 4:56:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Disposal_Loss_New', Name='New Disposal Loss', Description=NULL, Help=NULL WHERE AD_Element_ID=53559
;

-- Nov 12, 2008 4:56:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposal_Loss_New', Name='New Disposal Loss', Description=NULL, Help=NULL, AD_Element_ID=53559 WHERE UPPER(ColumnName)='A_DISPOSAL_LOSS_NEW' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:56:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposal_Loss_New', Name='New Disposal Loss', Description=NULL, Help=NULL WHERE AD_Element_ID=53559 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:56:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='New Disposal Loss', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53559) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:56:01 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='New Disposal Loss', Name='New Disposal Loss' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53559)
;

-- Nov 12, 2008 4:56:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Transfer Balance Sheet', PrintName='Transfer Balance Sheet',Updated=TO_TIMESTAMP('2008-11-12 16:56:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53560
;

-- Nov 12, 2008 4:56:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53560
;

-- Nov 12, 2008 4:56:27 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Transfer_Balance', Name='Transfer Balance Sheet', Description=NULL, Help=NULL WHERE AD_Element_ID=53560
;

-- Nov 12, 2008 4:56:28 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Transfer_Balance', Name='Transfer Balance Sheet', Description=NULL, Help=NULL, AD_Element_ID=53560 WHERE UPPER(ColumnName)='A_TRANSFER_BALANCE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:56:28 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Transfer_Balance', Name='Transfer Balance Sheet', Description=NULL, Help=NULL WHERE AD_Element_ID=53560 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:56:28 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Transfer Balance Sheet', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53560) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:56:28 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Transfer Balance Sheet', Name='Transfer Balance Sheet' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53560)
;

-- Nov 12, 2008 4:56:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Transfer IS Balance', PrintName='Transfer IS Balance',Updated=TO_TIMESTAMP('2008-11-12 16:56:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53522
;

-- Nov 12, 2008 4:56:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53522
;

-- Nov 12, 2008 4:56:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Transfer_Balance_IS', Name='Transfer IS Balance', Description=NULL, Help=NULL WHERE AD_Element_ID=53522
;

-- Nov 12, 2008 4:56:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Transfer_Balance_IS', Name='Transfer IS Balance', Description=NULL, Help=NULL, AD_Element_ID=53522 WHERE UPPER(ColumnName)='A_TRANSFER_BALANCE_IS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:56:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Transfer_Balance_IS', Name='Transfer IS Balance', Description=NULL, Help=NULL WHERE AD_Element_ID=53522 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:56:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Transfer IS Balance', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53522) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:56:47 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Transfer IS Balance', Name='Transfer IS Balance' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53522)
;

-- Nov 12, 2008 4:57:52 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Split Type', PrintName='Split Type',Updated=TO_TIMESTAMP('2008-11-12 16:57:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53521
;

-- Nov 12, 2008 4:57:52 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53521
;

-- Nov 12, 2008 4:57:52 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Split_Type', Name='Split Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53521
;

-- Nov 12, 2008 4:57:52 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Split_Type', Name='Split Type', Description=NULL, Help=NULL, AD_Element_ID=53521 WHERE UPPER(ColumnName)='A_SPLIT_TYPE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:57:52 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Split_Type', Name='Split Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53521 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:57:52 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Split Type', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53521) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:57:52 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Split Type', Name='Split Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53521)
;

-- Nov 12, 2008 4:58:21 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='To Asset ID', PrintName='To Asset ID',Updated=TO_TIMESTAMP('2008-11-12 16:58:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53525
;

-- Nov 12, 2008 4:58:21 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53525
;

-- Nov 12, 2008 4:58:21 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Asset_ID_To', Name='To Asset ID', Description=NULL, Help=NULL WHERE AD_Element_ID=53525
;

-- Nov 12, 2008 4:58:21 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Asset_ID_To', Name='To Asset ID', Description=NULL, Help=NULL, AD_Element_ID=53525 WHERE UPPER(ColumnName)='A_ASSET_ID_TO' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:58:21 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Asset_ID_To', Name='To Asset ID', Description=NULL, Help=NULL WHERE AD_Element_ID=53525 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:58:21 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='To Asset ID', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53525) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:58:21 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='To Asset ID', Name='To Asset ID' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53525)
;

-- Nov 12, 2008 4:58:40 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Transfer Balance IS', PrintName='Transfer Balance IS',Updated=TO_TIMESTAMP('2008-11-12 16:58:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53522
;

-- Nov 12, 2008 4:58:40 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53522
;

-- Nov 12, 2008 4:58:40 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Transfer_Balance_IS', Name='Transfer Balance IS', Description=NULL, Help=NULL WHERE AD_Element_ID=53522
;

-- Nov 12, 2008 4:58:40 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Transfer_Balance_IS', Name='Transfer Balance IS', Description=NULL, Help=NULL, AD_Element_ID=53522 WHERE UPPER(ColumnName)='A_TRANSFER_BALANCE_IS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 4:58:40 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Transfer_Balance_IS', Name='Transfer Balance IS', Description=NULL, Help=NULL WHERE AD_Element_ID=53522 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:58:40 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Transfer Balance IS', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53522) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 4:58:40 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Transfer Balance IS', Name='Transfer Balance IS' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53522)
;

-- Nov 12, 2008 5:00:03 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Disposed Reason Code', PrintName='Disposed Reason Code',Updated=TO_TIMESTAMP('2008-11-12 17:00:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53552
;

-- Nov 12, 2008 5:00:03 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53552
;

-- Nov 12, 2008 5:00:03 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Disposed_Reason', Name='Disposed Reason Code', Description=NULL, Help=NULL WHERE AD_Element_ID=53552
;

-- Nov 12, 2008 5:00:03 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposed_Reason', Name='Disposed Reason Code', Description=NULL, Help=NULL, AD_Element_ID=53552 WHERE UPPER(ColumnName)='A_DISPOSED_REASON' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 5:00:03 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposed_Reason', Name='Disposed Reason Code', Description=NULL, Help=NULL WHERE AD_Element_ID=53552 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 5:00:03 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Disposed Reason Code', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53552) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 5:00:04 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Disposed Reason Code', Name='Disposed Reason Code' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53552)
;

-- Nov 12, 2008 5:00:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Disposal Method', PrintName='Disposal Method',Updated=TO_TIMESTAMP('2008-11-12 17:00:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53554
;

-- Nov 12, 2008 5:00:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53554
;

-- Nov 12, 2008 5:00:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Disposed_Method', Name='Disposal Method', Description=NULL, Help=NULL WHERE AD_Element_ID=53554
;

-- Nov 12, 2008 5:00:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposed_Method', Name='Disposal Method', Description=NULL, Help=NULL, AD_Element_ID=53554 WHERE UPPER(ColumnName)='A_DISPOSED_METHOD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 5:00:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposed_Method', Name='Disposal Method', Description=NULL, Help=NULL WHERE AD_Element_ID=53554 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 5:00:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Disposal Method', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53554) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 5:00:46 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Disposal Method', Name='Disposal Method' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53554)
;

-- Nov 12, 2008 5:01:08 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element SET Name='Disposed Date', PrintName='Disposed Date',Updated=TO_TIMESTAMP('2008-11-12 17:01:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53551
;

-- Nov 12, 2008 5:01:08 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53551
;

-- Nov 12, 2008 5:01:08 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Column SET ColumnName='A_Disposed_Date', Name='Disposed Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53551
;

-- Nov 12, 2008 5:01:08 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposed_Date', Name='Disposed Date', Description=NULL, Help=NULL, AD_Element_ID=53551 WHERE UPPER(ColumnName)='A_DISPOSED_DATE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 12, 2008 5:01:08 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Process_Para SET ColumnName='A_Disposed_Date', Name='Disposed Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53551 AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 5:01:08 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_Field SET Name='Disposed Date', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53551) AND IsCentrallyMaintained='Y'
;

-- Nov 12, 2008 5:01:08 PM GMT+08:00
-- Fixed Asset - Terminology error.
UPDATE AD_PrintFormatItem SET PrintName='Disposed Date', Name='Disposed Date' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53551)
;
