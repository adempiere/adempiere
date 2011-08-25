-- 11-24-2010 11:51:52 AM CST
-- correct BOM Import
UPDATE AD_Column SET DefaultValue='N',Updated=TO_TIMESTAMP('2010-11-24 11:51:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60000
;

-- 11-24-2010 11:51:57 AM CST
-- correct BOM Import
INSERT INTO t_alter_column values('i_product_bom','I_IsImported','CHAR(1)',null,'N')
;

-- 11-24-2010 11:51:58 AM CST
-- correct BOM Import
UPDATE I_Product_BOM SET I_IsImported='N' WHERE I_IsImported IS NULL
;

