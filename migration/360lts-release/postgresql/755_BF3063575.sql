-- 06-dic-2010 11:24:48 COT
-- BF3063575-Name size in I_Import does not match M_Prroduct
UPDATE AD_Column SET FieldLength=255,Updated=TO_TIMESTAMP('2010-12-06 11:24:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3330
;

-- 06-dic-2010 11:25:01 COT
INSERT INTO t_alter_column values('m_product_trl','Name','VARCHAR(255)',null,null)
;

-- 06-dic-2010 11:25:35 COT
UPDATE AD_Column SET FieldLength=255,Updated=TO_TIMESTAMP('2010-12-06 11:25:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=7819
;

-- 06-dic-2010 11:25:38 COT
INSERT INTO t_alter_column values('i_product','Name','VARCHAR(255)',null,'NULL')
;

