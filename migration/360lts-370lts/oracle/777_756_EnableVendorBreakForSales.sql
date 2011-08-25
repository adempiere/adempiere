-- Oct 1, 2009 8:36:58 PM COT
-- Enable ProductPriceVendorBreak for Customers also (Sales)
UPDATE AD_Table SET Name='Product Price Break',Updated=TO_DATE('2009-10-01 20:36:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53172
;

-- Oct 1, 2009 8:36:58 PM COT
UPDATE AD_Table_Trl SET IsTranslated='N' WHERE AD_Table_ID=53172
;

-- Oct 1, 2009 8:37:10 PM COT
UPDATE AD_Element SET Name='Product Price Break', PrintName='Product Price Break',Updated=TO_DATE('2009-10-01 20:37:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53796
;

-- Oct 1, 2009 8:37:10 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53796
;

-- Oct 1, 2009 8:37:10 PM COT
UPDATE AD_Column SET ColumnName='M_ProductPriceVendorBreak_ID', Name='Product Price Break', Description=NULL, Help=NULL WHERE AD_Element_ID=53796
;

-- Oct 1, 2009 8:37:10 PM COT
UPDATE AD_Process_Para SET ColumnName='M_ProductPriceVendorBreak_ID', Name='Product Price Break', Description=NULL, Help=NULL, AD_Element_ID=53796 WHERE UPPER(ColumnName)='M_PRODUCTPRICEVENDORBREAK_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Oct 1, 2009 8:37:10 PM COT
UPDATE AD_Process_Para SET ColumnName='M_ProductPriceVendorBreak_ID', Name='Product Price Break', Description=NULL, Help=NULL WHERE AD_Element_ID=53796 AND IsCentrallyMaintained='Y'
;

-- Oct 1, 2009 8:37:10 PM COT
UPDATE AD_Field SET Name='Product Price Break', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53796) AND IsCentrallyMaintained='Y'
;

-- Oct 1, 2009 8:37:10 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Product Price Break', Name='Product Price Break' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53796)
;

-- Oct 1, 2009 8:37:44 PM COT
UPDATE AD_Column SET AD_Reference_Value_ID=138,Updated=TO_DATE('2009-10-01 20:37:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56928
;

-- Oct 1, 2009 8:37:58 PM COT
UPDATE AD_Tab SET Name='Product Price Break',Updated=TO_DATE('2009-10-01 20:37:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53197
;

-- Oct 1, 2009 8:37:58 PM COT
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53197
;

