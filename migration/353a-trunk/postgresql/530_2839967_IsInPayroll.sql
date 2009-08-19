-- 18-ago-2009 18:11:44 ECT
-- Bug 2839967 - Cannot change IsInPayroll
UPDATE AD_Column SET DefaultValue='N', IsMandatory='Y',Updated=TO_TIMESTAMP('2009-08-18 18:11:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=56294
;

insert into t_alter_column values('ad_user','IsInPayroll','CHAR(1)',null,'N')
;

UPDATE AD_User SET IsInPayroll='N' WHERE IsInPayroll IS NULL
;

insert into t_alter_column values('ad_user','IsInPayroll',null,'NOT NULL',null)
;

