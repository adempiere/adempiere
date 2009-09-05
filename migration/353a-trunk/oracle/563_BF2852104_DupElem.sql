-- Sep 4, 2009 10:46:58 PM COT
-- FR-2852104-Duplicated elements found
UPDATE AD_Column SET AD_Element_ID=913, ColumnName='I_IsImported', Description='Has this import been processed', Help='The Imported check box indicates if this import has been processed.', Name='Imported',Updated=TO_DATE('2009-09-04 22:46:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56027
;

-- Sep 4, 2009 10:46:58 PM COT
-- FR-2852104-Duplicated elements found
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56027
;

-- Sep 4, 2009 10:46:58 PM COT
-- FR-2852104-Duplicated elements found
UPDATE AD_Field SET Name='Imported', Description='Has this import been processed', Help='The Imported check box indicates if this import has been processed.' WHERE AD_Column_ID=56027 AND IsCentrallyMaintained='Y'
;

-- Sep 4, 2009 10:47:09 PM COT
-- FR-2852104-Duplicated elements found
DELETE  FROM  AD_Element_Trl WHERE AD_Element_ID=53645
;

-- Sep 4, 2009 10:47:09 PM COT
-- FR-2852104-Duplicated elements found
DELETE FROM AD_Element WHERE AD_Element_ID=53645
;

-- Sep 4, 2009 10:48:15 PM COT
-- FR-2852104-Duplicated elements found
UPDATE AD_Column SET AD_Element_ID=1949, ColumnName='VersionNo', Description='Version Number', Help=NULL, Name='Version No',Updated=TO_DATE('2009-09-04 22:48:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55861
;

-- Sep 4, 2009 10:48:15 PM COT
-- FR-2852104-Duplicated elements found
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=55861
;

-- Sep 4, 2009 10:48:15 PM COT
-- FR-2852104-Duplicated elements found
UPDATE AD_Field SET Name='Version No', Description='Version Number', Help=NULL WHERE AD_Column_ID=55861 AND IsCentrallyMaintained='Y'
;

-- Sep 4, 2009 10:48:20 PM COT
-- FR-2852104-Duplicated elements found
DELETE  FROM  AD_Element_Trl WHERE AD_Element_ID=53607
;

-- Sep 4, 2009 10:48:20 PM COT
-- FR-2852104-Duplicated elements found
DELETE FROM AD_Element WHERE AD_Element_ID=53607
;

-- Sep 4, 2009 10:48:56 PM COT
-- FR-2852104-Duplicated elements found
UPDATE AD_Column SET AD_Element_ID=2438, ColumnName='TextMsg', Description='Text Message', Help=NULL, Name='Text Message',Updated=TO_DATE('2009-09-04 22:48:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55739
;

-- Sep 4, 2009 10:48:56 PM COT
-- FR-2852104-Duplicated elements found
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=55739
;

-- Sep 4, 2009 10:48:56 PM COT
-- FR-2852104-Duplicated elements found
UPDATE AD_Field SET Name='Text Message', Description='Text Message', Help=NULL WHERE AD_Column_ID=55739 AND IsCentrallyMaintained='Y'
;

-- Sep 4, 2009 10:49:00 PM COT
-- FR-2852104-Duplicated elements found
DELETE  FROM  AD_Element_Trl WHERE AD_Element_ID=53586
;

-- Sep 4, 2009 10:49:00 PM COT
-- FR-2852104-Duplicated elements found
DELETE FROM AD_Element WHERE AD_Element_ID=53586
;

