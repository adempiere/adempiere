-- 02.10.2008 15:22:20 EEST
-- -
UPDATE AD_Column SET AD_Element_ID=1299, ColumnName='Classname', Description='Java Classname', FieldLength=255, Help='The Classname identifies the Java classname used by this report or process.', Name='Classname',Updated=TO_DATE('2008-10-02 15:22:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52068
;

-- 02.10.2008 15:22:21 EEST
-- -
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=52068
;

-- 02.10.2008 15:22:21 EEST
-- -
UPDATE AD_Field SET Name='Classname', Description='Java Classname', Help='The Classname identifies the Java classname used by this report or process.' WHERE AD_Column_ID=52068 AND IsCentrallyMaintained='Y'
;

-- 02.10.2008 15:23:16 EEST
-- -
DELETE  FROM  AD_Element_Trl WHERE AD_Element_ID=52025
;

-- 02.10.2008 15:23:17 EEST
-- -
DELETE FROM AD_Element WHERE AD_Element_ID=52025
;

