-- May 31, 2008 4:10:49 PM CDT
-- Fix Libero
UPDATE AD_Element SET ColumnName='Implosion', Name='Implosion',Updated=TO_TIMESTAMP('2008-05-31 16:10:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53466
;

-- May 31, 2008 4:10:49 PM CDT
-- Fix Libero
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53466
;

-- May 31, 2008 4:10:49 PM CDT
-- Fix Libero
UPDATE AD_Column SET ColumnName='Implosion', Name='Implosion', Description='Implosion of a Bill of Materials refers to finding all the BOM''''s in which a component is used.', Help='Commonly called a Where-Used report.' WHERE AD_Element_ID=53466
;

-- May 31, 2008 4:10:49 PM CDT
-- Fix Libero
UPDATE AD_Field SET Name='Implosion', Description='Implosion of a Bill of Materials refers to finding all the BOM''''s in which a component is used.', Help='Commonly called a Where-Used report.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53466) AND IsCentrallyMaintained='Y'
;

-- May 31, 2008 4:10:49 PM CDT
-- Fix Libero
UPDATE AD_Process_Para SET ColumnName='Implosion', Name='Implosion', Description='Implosion of a Bill of Materials refers to finding all the BOM''''s in which a component is used.', Help='Commonly called a Where-Used report.', AD_Element_ID=53466 WHERE UPPER(ColumnName)='IMPLOSION' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 31, 2008 4:10:49 PM CDT
-- Fix Libero
UPDATE AD_Process_Para SET ColumnName='Implosion', Name='Implosion', Description='Implosion of a Bill of Materials refers to finding all the BOM''''s in which a component is used.', Help='Commonly called a Where-Used report.' WHERE AD_Element_ID=53466 AND IsCentrallyMaintained='Y'
;

-- May 31, 2008 4:10:49 PM CDT
-- Fix Libero
UPDATE AD_PrintFormatItem SET PrintName='Implosion', Name='Implosion' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53466)
;

-- May 31, 2008 4:10:49 PM CDT
-- Fix Libero
UPDATE AD_PrintFormatItem SET PrintName='Implosion', Name='Implosion' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53466)
;

-- May 31, 2008 4:14:43 PM CDT
-- Fix Libero
ALTER TABLE T_BOMLine ADD COLUMN Implosion CHAR(1) DEFAULT 'N' CHECK (Implosion IN ('Y','N'))
;

