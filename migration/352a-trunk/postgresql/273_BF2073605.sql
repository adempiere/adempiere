-- 25/08/2008 16:00:57
-- 
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-08-25 16:00:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54742
;

-- 25/08/2008 16:13:53
-- 
insert into t_alter_column values('hr_employee','C_BPartner_ID','NUMERIC(10)',null,'NULL')
;

-- 25/08/2008 16:13:53
-- 
insert into t_alter_column values('hr_employee','C_BPartner_ID',null,'NOT NULL',null)
;