-- Nov 21, 2008 2:17:03 PM MYT
-- [ 2319302 ] Can't create new asset
UPDATE AD_Column SET DefaultValue='N',Updated=TO_TIMESTAMP('2008-11-21 14:17:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=8061
;

-- Nov 21, 2008 2:17:10 PM MYT
-- [ 2319302 ] Can't create new assets
insert into t_alter_column values('a_asset','Processing','CHAR(1)',null,'N')
;
