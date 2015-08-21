-- Aug 21, 2015 9:42:57 AM VET
-- POS Integration
UPDATE AD_Element_Trl SET Name='Tipo Doc. Venta al por mayor',PrintName='Tipo Doc. Venta al por mayor',Updated=TO_TIMESTAMP('2015-08-21 09:42:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1000013 AND AD_Language='es_MX'
;

-- Aug 21, 2015 9:43:14 AM VET
-- POS Integration
UPDATE AD_Element SET Name='Doc Type Wholesale', PrintName='Doc Type Wholesale',Updated=TO_TIMESTAMP('2015-08-21 09:43:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1000013
;

-- Aug 21, 2015 9:43:14 AM VET
-- POS Integration
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1000013
;

-- Aug 21, 2015 9:43:14 AM VET
-- POS Integration
UPDATE AD_Column SET ColumnName='C_DocTypewholesale_ID', Name='Doc Type Wholesale', Description=NULL, Help=NULL WHERE AD_Element_ID=1000013
;

-- Aug 21, 2015 9:43:14 AM VET
-- POS Integration
UPDATE AD_Process_Para SET ColumnName='C_DocTypewholesale_ID', Name='Doc Type Wholesale', Description=NULL, Help=NULL, AD_Element_ID=1000013 WHERE UPPER(ColumnName)='C_DOCTYPEWHOLESALE_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 21, 2015 9:43:14 AM VET
-- POS Integration
UPDATE AD_Process_Para SET ColumnName='C_DocTypewholesale_ID', Name='Doc Type Wholesale', Description=NULL, Help=NULL WHERE AD_Element_ID=1000013 AND IsCentrallyMaintained='Y'
;

-- Aug 21, 2015 9:43:14 AM VET
-- POS Integration
UPDATE AD_Field SET Name='Doc Type Wholesale', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=1000013) AND IsCentrallyMaintained='Y'
;

-- Aug 21, 2015 9:43:14 AM VET
-- POS Integration
UPDATE AD_PrintFormatItem SET PrintName='Doc Type Wholesale', Name='Doc Type Wholesale' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=1000013)
;

-- Aug 21, 2015 9:43:42 AM VET
-- POS Integration
UPDATE AD_Element_Trl SET Name='Lista de Precio Venta al por mayor',PrintName='Lista de Precio Venta al por mayor',Updated=TO_TIMESTAMP('2015-08-21 09:43:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1000012 AND AD_Language='es_MX'
;

-- Aug 21, 2015 9:44:03 AM VET
-- POS Integration
UPDATE AD_Element SET Name='Price List Wholesale', PrintName='Price List Wholesale',Updated=TO_TIMESTAMP('2015-08-21 09:44:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1000012
;

-- Aug 21, 2015 9:44:03 AM VET
-- POS Integration
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1000012
;

-- Aug 21, 2015 9:44:03 AM VET
-- POS Integration
UPDATE AD_Column SET ColumnName='M_PriceListWholesale_ID', Name='Price List Wholesale', Description=NULL, Help=NULL WHERE AD_Element_ID=1000012
;

-- Aug 21, 2015 9:44:03 AM VET
-- POS Integration
UPDATE AD_Process_Para SET ColumnName='M_PriceListWholesale_ID', Name='Price List Wholesale', Description=NULL, Help=NULL, AD_Element_ID=1000012 WHERE UPPER(ColumnName)='M_PRICELISTWHOLESALE_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 21, 2015 9:44:03 AM VET
-- POS Integration
UPDATE AD_Process_Para SET ColumnName='M_PriceListWholesale_ID', Name='Price List Wholesale', Description=NULL, Help=NULL WHERE AD_Element_ID=1000012 AND IsCentrallyMaintained='Y'
;

-- Aug 21, 2015 9:44:03 AM VET
-- POS Integration
UPDATE AD_Field SET Name='Price List Wholesale', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=1000012) AND IsCentrallyMaintained='Y'
;

-- Aug 21, 2015 9:44:03 AM VET
-- POS Integration
UPDATE AD_PrintFormatItem SET PrintName='Price List Wholesale', Name='Price List Wholesale' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=1000012)
;

