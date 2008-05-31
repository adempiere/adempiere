-- Jan 5, 2008 11:15:30 AM EST
-- Fix column PP_Product_BOMLine.Line default value
UPDATE AD_Column SET DefaultValue='@SQL=SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM PP_Product_BOMLine WHERE PP_Product_BOM_ID=@PP_Product_BOM_ID@',Updated=TO_TIMESTAMP('2008-01-05 11:15:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53361
;
