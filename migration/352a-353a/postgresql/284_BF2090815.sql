-- Sep 3, 2008 12:46:45 PM EEST
-- 
UPDATE AD_Column SET AD_Reference_ID=22, DefaultValue='100', IsMandatory='Y', ValueMin='0',Updated=TO_TIMESTAMP('2008-09-03 12:46:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53272
;

-- Sep 3, 2008 12:46:49 PM EEST
-- 
insert into t_alter_column values('s_resource','PercentUtilization','NUMERIC',null,'100')
;

-- Sep 3, 2008 12:46:49 PM EEST
-- 
UPDATE S_Resource SET PercentUtilization=100 WHERE PercentUtilization IS NULL
;

-- Sep 3, 2008 12:46:49 PM EEST
-- 
insert into t_alter_column values('s_resource','PercentUtilization',null,'NOT NULL',null)
;

-- Sep 3, 2008 12:46:49 PM EEST
-- 
ALTER TABLE S_Resource ADD CONSTRAINT CHK_COL_53272 CHECK (PercentUtilization>=0)
;

