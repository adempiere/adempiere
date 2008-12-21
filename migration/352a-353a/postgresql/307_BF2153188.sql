-- 08.10.2008 16:13:52 EEST
-- 
UPDATE AD_Column SET DefaultValue='0', IsMandatory='Y',Updated=TO_TIMESTAMP('2008-10-08 16:13:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53408
;

-- 08.10.2008 16:13:55 EEST
-- 
insert into t_alter_column values('m_product','LowLevel','NUMERIC(10)',null,'0')
;

-- 08.10.2008 16:13:56 EEST
-- 
UPDATE M_Product SET LowLevel=0 WHERE LowLevel IS NULL
;

-- 08.10.2008 16:13:56 EEST
-- 
insert into t_alter_column values('m_product','LowLevel',null,'NOT NULL',null)
;

