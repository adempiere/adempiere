-- 03.06.2008 12:09:57 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Field SET DefaultValue='Y', IsReadOnly='Y',Updated=TO_TIMESTAMP('2008-06-03 12:09:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53299
;

-- 03.06.2008 12:12:50 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2008-06-03 12:12:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53300
;

-- 03.06.2008 12:12:54 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2008-06-03 12:12:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53301
;

-- 03.06.2008 12:13:00 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2008-06-03 12:13:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53302
;

-- 03.06.2008 12:13:03 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2008-06-03 12:13:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53303
;

-- 03.06.2008 12:13:06 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2008-06-03 12:13:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53304
;

-- 03.06.2008 12:14:17 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=53296
;

-- 03.06.2008 12:14:17 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=53292
;

-- 03.06.2008 12:14:17 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=53291
;

-- 03.06.2008 12:14:17 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=53294
;

-- 03.06.2008 12:15:42 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Element SET Name='Manufacturing Resource', PrintName='Manufacturing Resource',Updated=TO_TIMESTAMP('2008-06-03 12:15:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53232
;

-- 03.06.2008 12:15:42 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53232
;

-- 03.06.2008 12:15:42 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Column SET ColumnName='IsManufacturingResource', Name='Manufacturing Resource', Description=NULL, Help=NULL WHERE AD_Element_ID=53232
;

-- 03.06.2008 12:15:42 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Field SET Name='Manufacturing Resource', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53232) AND IsCentrallyMaintained='Y'
;

-- 03.06.2008 12:15:42 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Process_Para SET ColumnName='IsManufacturingResource', Name='Manufacturing Resource', Description=NULL, Help=NULL, AD_Element_ID=53232 WHERE UPPER(ColumnName)='ISMANUFACTURINGRESOURCE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- 03.06.2008 12:15:42 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Process_Para SET ColumnName='IsManufacturingResource', Name='Manufacturing Resource', Description=NULL, Help=NULL WHERE AD_Element_ID=53232 AND IsCentrallyMaintained='Y'
;

-- 03.06.2008 12:15:42 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_PrintFormatItem SET PrintName='Manufacturing Resource', Name='Manufacturing Resource' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53232)
;

-- 03.06.2008 12:15:42 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_PrintFormatItem SET PrintName='Manufacturing Resource', Name='Manufacturing Resource' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53232)
;

-- 03.06.2008 12:17:25 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Element_Trl SET IsTranslated='Y',Name='Resursa de productie',PrintName='Resursa de productie',Updated=TO_TIMESTAMP('2008-06-03 12:17:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53232 AND AD_Language='ro_RO'
;

-- 03.06.2008 12:19:17 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Element SET Name='Manufacturing Resource Type', PrintName='Manufacturing Resource Type',Updated=TO_TIMESTAMP('2008-06-03 12:19:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53233
;

-- 03.06.2008 12:19:17 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53233
;

-- 03.06.2008 12:19:17 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Column SET ColumnName='ManufacturingResourceType', Name='Manufacturing Resource Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53233
;

-- 03.06.2008 12:19:17 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Field SET Name='Manufacturing Resource Type', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53233) AND IsCentrallyMaintained='Y'
;

-- 03.06.2008 12:19:17 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Process_Para SET ColumnName='ManufacturingResourceType', Name='Manufacturing Resource Type', Description=NULL, Help=NULL, AD_Element_ID=53233 WHERE UPPER(ColumnName)='MANUFACTURINGRESOURCETYPE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- 03.06.2008 12:19:17 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Process_Para SET ColumnName='ManufacturingResourceType', Name='Manufacturing Resource Type', Description=NULL, Help=NULL WHERE AD_Element_ID=53233 AND IsCentrallyMaintained='Y'
;

-- 03.06.2008 12:19:17 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_PrintFormatItem SET PrintName='Manufacturing Resource Type', Name='Manufacturing Resource Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53233)
;

-- 03.06.2008 12:19:17 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_PrintFormatItem SET PrintName='Manufacturing Resource Type', Name='Manufacturing Resource Type' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53233)
;

-- 03.06.2008 12:19:27 EEST
-- Fix Manufacturing Resource Window
UPDATE AD_Element_Trl SET IsTranslated='Y',Name='Tip resursa productie',PrintName='Tip resursa productie',Updated=TO_TIMESTAMP('2008-06-03 12:19:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53233 AND AD_Language='ro_RO'
;

