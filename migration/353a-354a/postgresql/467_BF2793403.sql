-- 18.05.2009 18:06:45 EEST
-- -
UPDATE AD_Column SET FieldLength=255,Updated=TO_TIMESTAMP('2009-05-18 18:06:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11404
;

-- 18.05.2009 18:06:55 EEST
-- -
insert into t_alter_column values('ad_scheduler_para','ParameterDefault','VARCHAR(255)',null,'NULL')
;

