-- 29/09/2008 11:32:25
-- [ 2135012 ] Asset Window is not editable
UPDATE AD_Column SET DefaultValue='N',Updated=TO_TIMESTAMP('2008-09-29 11:32:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=8061
;

-- 29/09/2008 11:33:08
-- [ 2135012 ] Asset Window is not editable
insert into t_alter_column values('a_asset','Processing','CHAR(1)',null,'N')
;

