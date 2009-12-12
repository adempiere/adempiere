-- Dec 12, 2009 10:54:58 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Element SET EntityType='D', Name='Order Source', PrintName='Order Source',Updated=TO_TIMESTAMP('2009-12-12 22:54:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53942
;

-- Dec 12, 2009 10:54:58 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53942
;

-- Dec 12, 2009 10:54:58 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Column SET ColumnName='C_OrderSource_ID', Name='Order Source', Description=NULL, Help=NULL WHERE AD_Element_ID=53942
;

-- Dec 12, 2009 10:54:58 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Process_Para SET ColumnName='C_OrderSource_ID', Name='Order Source', Description=NULL, Help=NULL, AD_Element_ID=53942 WHERE UPPER(ColumnName)='C_ORDERSOURCE_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Dec 12, 2009 10:54:58 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Process_Para SET ColumnName='C_OrderSource_ID', Name='Order Source', Description=NULL, Help=NULL WHERE AD_Element_ID=53942 AND IsCentrallyMaintained='Y'
;

-- Dec 12, 2009 10:54:58 PM CET
-- FF [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Field SET Name='Order Source', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53942) AND IsCentrallyMaintained='Y'
;

-- Dec 12, 2009 10:54:58 PM CET
-- FR [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_PrintFormatItem SET PrintName='Order Source', Name='Order Source' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53942)
;


-- Dec 12, 2009 11:06:42 PM CET
-- FR [2913307] - New table: Order Source
-- https://sourceforge.net/tracker/?func=detail&aid=2913307&group_id=176962&atid=883808
UPDATE AD_Tab SET IsSingleRow='Y',Updated=TO_TIMESTAMP('2009-12-12 23:06:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=186
;