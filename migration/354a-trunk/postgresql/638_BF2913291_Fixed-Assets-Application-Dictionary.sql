-- Dec 12, 2009 3:21:34 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_Window SET WindowType='M',Updated=TO_TIMESTAMP('2009-12-12 15:21:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Window_ID=53059
;

-- Dec 12, 2009 3:31:23 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_Table SET Name='Depreciation Table Detail',Updated=TO_TIMESTAMP('2009-12-12 15:31:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53113
;

-- Dec 12, 2009 3:31:23 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_Table_Trl SET IsTranslated='N' WHERE AD_Table_ID=53113
;

-- Dec 12, 2009 3:32:16 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_Element SET Name='Depreciation Table Detail', PrintName='Depreciation Table Detail',Updated=TO_TIMESTAMP('2009-12-12 15:32:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53476
;

-- Dec 12, 2009 3:32:16 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53476
;

-- Dec 12, 2009 3:32:16 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_Column SET ColumnName='A_Depreciation_Table_Detail_ID', Name='Depreciation Table Detail', Description=NULL, Help=NULL WHERE AD_Element_ID=53476
;

-- Dec 12, 2009 3:32:16 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Table_Detail_ID', Name='Depreciation Table Detail', Description=NULL, Help=NULL, AD_Element_ID=53476 WHERE UPPER(ColumnName)='A_DEPRECIATION_TABLE_DETAIL_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Dec 12, 2009 3:32:16 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_Process_Para SET ColumnName='A_Depreciation_Table_Detail_ID', Name='Depreciation Table Detail', Description=NULL, Help=NULL WHERE AD_Element_ID=53476 AND IsCentrallyMaintained='Y'
;

-- Dec 12, 2009 3:32:16 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_Field SET Name='Depreciation Table Detail', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53476) AND IsCentrallyMaintained='Y'
;

-- Dec 12, 2009 3:32:17 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_PrintFormatItem SET PrintName='Depreciation Table Detail', Name='Depreciation Table Detail' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53476)
;

-- Dec 12, 2009 3:33:56 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-12-12 15:33:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56216
;

-- Dec 12, 2009 3:34:23 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_Field SET DisplayLength=60,Updated=TO_TIMESTAMP('2009-12-12 15:34:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=56220
;

-- Dec 12, 2009 3:35:00 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56221
;

-- Dec 12, 2009 3:35:00 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56218
;

-- Dec 12, 2009 3:35:00 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56219
;

-- Dec 12, 2009 3:35:00 PM CET
-- BF [2913291= Fixed Assets Application Dictionary stabilization
-- https://sourceforge.net/tracker/index.php?func=detail&aid=2913291&group_id=176962&atid=879332
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56220
;

